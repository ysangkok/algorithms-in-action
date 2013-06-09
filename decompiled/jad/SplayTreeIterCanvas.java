// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SplayTreeIterCanvas.java

import com.cim.AIA.*;
import java.awt.*;
import java.util.Hashtable;
import java.util.Vector;
import localization.Messages;

public class SplayTreeIterCanvas extends AlgorithmCanvas
{

    public SplayTreeIterCanvas()
    {
        isShowTween = true;
    }

    protected Hashtable getPositions(SplayTreeIterNode head)
    {
        if(head instanceof SplayTreeIterNullNode)
        {
            return null;
        } else
        {
            Hashtable positions = new Hashtable();
            getPositions(head, positions);
            return positions;
        }
    }

    protected void getPositions(SplayTreeIterNode head, Hashtable positions)
    {
        positions.put(head, new Point(head.getPosition()));
        if(!(head instanceof SplayTreeIterNullNode))
        {
            getPositions(((SplayTreeIterDataNode)head).getLeft(), positions);
            getPositions(((SplayTreeIterDataNode)head).getRight(), positions);
        }
    }

    protected MultipleTween getMoveTweens(SplayTreeIterNode head, Hashtable oldPositions, Hashtable currentPositions)
    {
        MultipleTween multipleTweens = new MultipleTween(this);
        getMoveTweens(head, oldPositions, currentPositions, multipleTweens);
        return multipleTweens;
    }

    protected void getMoveTweens(SplayTreeIterNode head, Hashtable oldPositions, Hashtable currentPositions, MultipleTween multipleTweens)
    {
        Point oldPoint = (Point)oldPositions.get(head);
        Point currentPoint = (Point)currentPositions.get(head);
        if(oldPoint != null && currentPoint != null && (oldPoint.x != currentPoint.x || oldPoint.y != currentPoint.y))
            multipleTweens.add(new MoveTween(null, head, oldPoint, currentPoint, false, numberOfTweenSteps));
        if(!(head instanceof SplayTreeIterNullNode))
        {
            getMoveTweens(((SplayTreeIterDataNode)head).getLeft(), oldPositions, currentPositions, multipleTweens);
            getMoveTweens(((SplayTreeIterDataNode)head).getRight(), oldPositions, currentPositions, multipleTweens);
        }
    }

    public void displayAlgorithm(Graphics g)
    {
        int gap = 15;
        int top = gap;
        if(insertData != null)
        {
            insertData.draw(g);
            top = insertData.getLocation().y + insertData.getHeight() + gap;
        }
        if(head != null)
            head.draw(g);
        if(searchData != null)
            searchData.draw(g);
        top += 5;
        if(parentNode != null)
        {
            String parentPointer = Messages.getString("SplayTreeIterCanvas.0");
            drawLine(g, new Point(g.getFontMetrics().stringWidth(parentPointer) / 2 + 10, top), parentNode.getPosition(), parentPointer);
        }
        top = top + g.getFontMetrics().getHeight() + 5;
        if(currentNode != null)
        {
            String splayPointer = Messages.getString("SplayTreeIterCanvas.1");
            drawLine(g, new Point(g.getFontMetrics().stringWidth(splayPointer) / 2 + 10, top), currentNode.getPosition(), splayPointer);
        }
        top = top + g.getFontMetrics().getHeight() + 5;
        if(currentLevel != -1)
        {
            g.drawString(Messages.getString("SplayTreeIterCanvas.2"), 10, top);
            Node newNode = new Node(new Integer(currentLevel), -1);
            newNode.setPosition(new Point(10 + g.getFontMetrics().stringWidth(Messages.getString("SplayTreeIterCanvas.3")), top - g.getFontMetrics().getHeight()));
            newNode.draw(g);
        }
        if(codeStack != null)
            codeStack.draw(g);
    }

    public void paint()
    {
        repaint();
    }

    public void processRepaintEvent(RepaintEvent e)
    {
        if(e.paramObj != null)
        {
            int windowCenterX = getParentSize().width / 2;
            int gapWidth = 25;
            int windowTop = gapWidth;
            SplayTreeIter splayTree = (SplayTreeIter)(SplayTreeIter)e.paramObj;
            insertData = splayTree.getInsertData();
            if(insertData != null)
            {
                insertData.setLocation(windowCenterX - insertData.getWidth() / 2, windowTop);
                windowTop += insertData.getHeight() + gapWidth;
            }
            head = splayTree.getHead();
            if(head != null)
            {
                head.setUpperLeft(new Point(20, windowTop));
                head.setUpperLeft(new Point(windowCenterX - head.getRectangle().width / 2, windowTop));
                windowTop += head.getRectangle().height + gapWidth;
            }
            searchData = splayTree.getSearchData();
            if(searchData != null)
            {
                searchData.setLocation(windowCenterX - searchData.getWidth() / 2, windowTop);
                windowTop += searchData.getHeight() + gapWidth;
            }
            currentNode = splayTree.getCurrentNode();
            parentNode = splayTree.getParentNode();
            currentLevel = splayTree.getCurrentLevel();
            codeStack = splayTree.getCodeStack();
            if(codeStack != null)
                codeStack.setPosition(new Point(getParentSize().width - codeStack.getWidth(), getParentSize().height - gapWidth));
            repaint();
            Vector multiTweensArray = splayTree.getTweens(this, null, numberOfTweenSteps);
            if(splayTree.isTweenFast())
                super.setTweenSpeed(tweenSpeed / 3);
            else
                super.setTweenSpeed(tweenSpeed);
            noMoveTween = splayTree.getNoMoveTween();
            Hashtable currentPositions = getPositions(head);
            if(oldPositions != null && !noMoveTween)
            {
                MultipleTween moveTweens = getMoveTweens(head, oldPositions, currentPositions);
                if(moveTweens.getNumberOfTweens() > 0)
                    addTween(moveTweens);
            }
            oldPositions = currentPositions;
            if(isShowTween && multiTweensArray != null)
            {
                for(int i = 0; i < multiTweensArray.size(); i++)
                {
                    Object element = multiTweensArray.elementAt(i);
                    if(element instanceof Hashtable)
                        continue;
                    if(element instanceof MultipleTween)
                        addTween((MultipleTween)element);
                    else
                        throw new RuntimeException(Messages.getString("SplayTreeIterCanvas.4"));
                }

            }
            if(tweens.getNumberOfTweens() > 0)
                tweens.run();
        }
    }

    private void drawLine(Graphics g, Point start, Point end, String label)
    {
        Line line = new Line(start.x, start.y, end.x, end.y);
        line.setLabel(label);
        line.setDistanceFromStartForArrowHead(-3);
        line.setDistanceFromStartForLabel(-1);
        line.setXLabelOffset(-g.getFontMetrics().stringWidth(label) / 2);
        line.showArrowHead(true);
        line.setColor(SplayTreeIterColors.POINTER_COLOR);
        line.draw(g);
    }

    protected void handleColorSelection(ColorSelection colorselection)
    {
    }

    protected void handleFontSelection(FontSelection fontselection)
    {
    }

    public void setTweenSpeed(int speed)
    {
        tweenSpeed = speed;
        super.setTweenSpeed(speed);
    }

    public void setIsShowTween(boolean isShowTween)
    {
        this.isShowTween = isShowTween;
    }

    private int tweenSpeed;
    protected Hashtable oldPositions;
    private static final int XGAP = 10;
    private static final int YGAP = 5;
    private SplayTreeIterCodeStack codeStack;
    private SplayTreeIterNode head;
    private ElementArray insertData;
    private ElementArray searchData;
    private SplayTreeIterNode currentNode;
    private SplayTreeIterNode parentNode;
    private boolean isShowTween;
    private boolean noMoveTween;
    private int currentLevel;
}
