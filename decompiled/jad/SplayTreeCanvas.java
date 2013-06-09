// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SplayTreeCanvas.java

import com.cim.AIA.*;
import java.awt.*;
import java.util.Hashtable;
import java.util.Vector;
import localization.Messages;

public class SplayTreeCanvas extends AlgorithmCanvas
{

    public SplayTreeCanvas()
    {
        isShowTween = true;
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
        if(currentNode != null)
        {
            String splayPointer = Messages.getString("SplayTreeCanvas.0");
            drawLine(g, new Point(g.getFontMetrics().stringWidth(splayPointer), top), currentNode.getPosition(), splayPointer);
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
            SplayTree splayTree = (SplayTree)(SplayTree)e.paramObj;
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
            codeStack = splayTree.getCodeStack();
            if(codeStack != null)
                codeStack.setPosition(new Point(getParentSize().width - codeStack.getWidth(), getParentSize().height - gapWidth));
            repaint();
            Vector multiTweensArray = splayTree.getTweens(this, null, numberOfTweenSteps);
            if(splayTree.isTweenFast())
                super.setTweenSpeed(tweenSpeed / 3);
            else
                super.setTweenSpeed(tweenSpeed);
            if(isShowTween && multiTweensArray != null)
            {
                for(int i = 0; i < multiTweensArray.size(); i++)
                {
                    Object element = multiTweensArray.elementAt(i);
                    if(!(element instanceof Hashtable))
                        if(element instanceof MultipleTween)
                            addTween((MultipleTween)element);
                        else
                            throw new RuntimeException("Vector of unexpected type");
                    if(tweens.getNumberOfTweens() > 0)
                        tweens.run();
                }

            }
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
        line.setColor(SplayTreeColors.POINTER_COLOR);
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
    private SplayTreeCodeStack codeStack;
    private SplayTreeNode head;
    private ElementArray insertData;
    private ElementArray searchData;
    private SplayTreeNode currentNode;
    private boolean isShowTween;
}
