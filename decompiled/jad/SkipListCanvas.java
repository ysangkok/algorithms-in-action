// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SkipListCanvas.java

import com.cim.AIA.*;
import java.awt.*;
import java.io.PrintStream;
import java.util.Random;
import localization.Messages;

public class SkipListCanvas extends AlgorithmCanvas
{

    public SkipListCanvas()
    {
        drawDiff = false;
        drawFinal = false;
        drawI = false;
        drawJ = false;
        drawElement = false;
        runTween = false;
        dataCondition = false;
        displayDataCondition = false;
        random = new Random();
        boxPoint = new Point(0, 0);
        dataLine = null;
    }

    public synchronized void displayAlgorithm(Graphics g)
    {
        if(displayDataCondition)
        {
            if(dataConditionNode != null)
                dataConditionNode.draw(g);
            if(nextDataNode != null)
                nextDataNode.draw(g);
            if(dataItemNode != null)
                dataItemNode.draw(g);
            g.drawString(DATA_COND_LABEL, dataConditionNode.getX() - g.getFontMetrics().stringWidth(DATA_COND_LABEL), dataConditionNode.getY() + (3 * dataConditionNode.getHeight()) / 4);
        }
        if(dataLine != null)
            dataLine.draw(g);
        if(newElement != null && drawElement)
        {
            for(int i = 0; i < newElement.nodes.length; i++)
                newElement.nodes[i].draw(g);

        }
        if(insertElementArray != null)
            insertElementArray.draw(g);
        if(searchElementArray != null)
        {
            searchElementArray.draw(g);
            g.drawString(SEARCH_LABEL, getParentSize().width / 2 - g.getFontMetrics().stringWidth(SEARCH_LABEL) / 2, searchElementArray.getLocation().y);
        }
        if(skipNode != null)
        {
            String levelString = (new StringBuilder()).append(Messages.getString("SkipListCanvas.8")).append(String.valueOf(no_of_levels)).toString();
            g.drawString(levelString, levelPoint.x, levelPoint.y);
            draw(g, skipNode);
            draw(g);
            for(int i = 2; i <= no_of_levels; i++)
                draw(g, i);

            if(ptr != null)
            {
                Line ptrLine = new Line(ptr.getX() + ptr.getWidth() + 10, ptr.getY() + 40, ptr.getX() + ptr.getWidth(), ptr.getY() + ptr.getHeight());
                ptrLine.setLabel(PTR_LABEL);
                ptrLine.setDistanceFromStartForArrowHead(-3);
                ptrLine.setDistanceFromStartForLabel(-1);
                ptrLine.setXLabelOffset(-g.getFontMetrics().stringWidth(PTR_LABEL) / 2);
                ptrLine.setYLabelOffset(g.getFontMetrics().getHeight());
                ptrLine.showArrowHead(true);
                ptrLine.setColor(POINTER_COLOR);
                ptrLine.draw(g);
            }
            if(tailptr != null)
            {
                Line tailptrLine = new Line(tailptr.getX() - 10, tailptr.getY() + 40, tailptr.getX(), tailptr.getY() + tailptr.getHeight());
                tailptrLine.setLabel(TAILPTR_LABEL);
                tailptrLine.setDistanceFromStartForArrowHead(-3);
                tailptrLine.setDistanceFromStartForLabel(-1);
                tailptrLine.setXLabelOffset(-g.getFontMetrics().stringWidth(TAILPTR_LABEL) / 2);
                tailptrLine.setYLabelOffset(g.getFontMetrics().getHeight());
                tailptrLine.showArrowHead(true);
                tailptrLine.setColor(POINTER_COLOR);
                tailptrLine.draw(g);
            }
            if(drawJ)
            {
                Point point1 = new Point(skipNode.nodes[0].getX() - 15, skipNode.nodes[0].getY() - jlevel * skipNode.nodes[0].getHeight() - skipNode.nodes[0].getHeight() / 2);
                Point point2 = new Point(skipNode.nodes[0].getX(), skipNode.nodes[0].getY() - jlevel * skipNode.nodes[0].getHeight() - skipNode.nodes[0].getHeight() / 2);
                Line jLine = new Line(point1, point2);
                jLine.showArrowHead(true);
                jLine.setDistanceFromStartForArrowHead(-3);
                jLine.setLabel(J_PTR);
                jLine.setDistanceFromStartForLabel(-1);
                jLine.setXLabelOffset(-1 * getGraphics().getFontMetrics().stringWidth(J_PTR));
                jLine.setYLabelOffset(1 * getGraphics().getFontMetrics().getHeight());
                jLine.setColor(POINTER_COLOR);
                jLine.draw(g);
            }
            if(drawI)
            {
                Point point1 = new Point(skipNode.nodes[0].getX() - 15, skipNode.nodes[0].getY() - ilevel * skipNode.nodes[0].getHeight() - skipNode.nodes[0].getHeight() / 2);
                Point point2 = new Point(skipNode.nodes[0].getX(), skipNode.nodes[0].getY() - ilevel * skipNode.nodes[0].getHeight() - skipNode.nodes[0].getHeight() / 2);
                Line iLine = new Line(point1, point2);
                iLine.showArrowHead(true);
                iLine.setDistanceFromStartForArrowHead(-3);
                iLine.setLabel(I_PTR);
                iLine.setDistanceFromStartForLabel(-1);
                iLine.setXLabelOffset(-1 * getGraphics().getFontMetrics().stringWidth(I_PTR));
                iLine.setYLabelOffset(1 * getGraphics().getFontMetrics().getHeight());
                iLine.setColor(POINTER_COLOR);
                iLine.draw(g);
            }
            g.drawRect(boxPoint.x, boxPoint.y, 20, 20);
            String newNodeLevel = Messages.getString("SkipListCanvas.9");
            g.drawString(newNodeLevel, boxPoint.x - g.getFontMetrics().stringWidth(newNodeLevel), boxPoint.y + 15);
        }
        if(drawDiff)
        {
            g.setColor(TEXT_COLOR);
            int windowCenterX = getParentSize().width / 2;
            for(test = random.nextInt(); test > maxLevel; test = random.nextInt());
            String string = String.valueOf(test);
            String printString = string.substring(string.length() - 1, string.length());
            g.drawString(printString, (boxPoint.x + 10) - g.getFontMetrics().stringWidth(printString) / 2, (boxPoint.y + 20) - 5);
        }
        if(drawFinal)
            g.drawString(String.valueOf(currentNewNodeLevel), (boxPoint.x + 10) - g.getFontMetrics().stringWidth(String.valueOf(currentNewNodeLevel)) / 2, (boxPoint.y + 20) - 5);
    }

    protected MultipleTween generateTween(SkipNode toNode)
    {
        MultipleTween multipleTweens = new MultipleTween(this);
        for(int i = 0; i < newElement.nodes.length; i++)
        {
            newElement.nodes[i].setIsVisible(false);
            MoveTween fromTween = new MoveTween(null, newElement.nodes[i], toNode.nodes[i].getPosition(), newElement.nodes[i].getPosition(), true, numberOfTweenSteps);
            multipleTweens.add(fromTween);
            MoveTween toTween = new MoveTween(null, toNode.nodes[i], newElement.nodes[i].getPosition(), toNode.nodes[i].getPosition(), true, numberOfTweenSteps);
            multipleTweens.add(toTween);
        }

        return multipleTweens;
    }

    protected Line initialiseLine(String label, Node node)
    {
        Point from = new Point(node.getX() + node.getWidth() / 2, node.getY() + 60);
        Point to = new Point(node.getX() + node.getWidth() / 2, node.getY() + node.getHeight());
        Line line = new Line(from, to);
        line.setLabel(label);
        line.setDistanceFromStartForArrowHead(-3);
        line.setDistanceFromStartForLabel(-1);
        line.setXLabelOffset(-getGraphics().getFontMetrics().stringWidth(label) / 2);
        line.setYLabelOffset(getGraphics().getFontMetrics().getHeight());
        line.showArrowHead(true);
        line.setColor(POINTER_COLOR);
        return line;
    }

    public void processRepaintEvent(RepaintEvent e)
    {
        removeAllSelectables();
        if(e.paramObj != null)
        {
            int windowTop = 30;
            int windowCenterX = getParentSize().width / 2;
            SkipList skipList = (SkipList)(SkipList)e.paramObj;
            skipNode = skipList.getHead();
            no_of_levels = skipList.getLevels();
            if(skipList.isBuildMode())
                insertElementArray = skipList.getInsertElementArray();
            else
                insertElementArray = null;
            if(skipList.isSearchMode())
            {
                addSelectionListener(SearchSelection.getInstance());
                searchElementArray = skipList.getSearchElementArray();
            } else
            {
                searchElementArray = null;
            }
            if(insertElementArray != null)
            {
                insertElementArray.setElementWidth(20);
                insertElementArray.setAllHaveSameWidth(true);
                int windowWidth = getParentSize().width;
                int elementWidth = insertElementArray.getWidth();
                if(elementWidth < windowWidth)
                    insertElementArray.setLocation(windowCenterX - insertElementArray.getWidth() / 2, windowTop);
                else
                    insertElementArray.setLocation(0, windowTop);
                windowTop += insertElementArray.getHeight() + 30;
            }
            if(skipNode != null)
            {
                int windowWidth = getParentSize().width;
                levelPoint = new Point(30, windowTop);
                dataConditionPoint = new Point((3 * getParentSize().width) / 4, windowTop);
                Point point = new Point(60, windowTop + skipNode.getHeight());
                skipNode.nodes[0].setPosition(point);
                for(int m = 1; m < skipNode.nodes.length; m++)
                    skipNode.nodes[m].setPosition(new Point(skipNode.nodes[0].getX(), skipNode.nodes[0].getY() - m * skipNode.nodes[0].getHeight()));

                SkipNode pointerNode = skipNode;
                for(int posCounter = 40; pointerNode.next[0] != null; posCounter += 40)
                {
                    pointerNode = pointerNode.next[0];
                    pointerNode.nodes[0].setPosition(new Point(point.x + posCounter, point.y));
                    pointerNode.nodes[1].setPosition(new Point(point.x + posCounter, point.y - pointerNode.nodes[0].getHeight()));
                }

                for(int i = 1; i < skipNode.next.length; i++)
                    for(pointerNode = skipNode; pointerNode.next[i] != null;)
                    {
                        pointerNode = pointerNode.next[i];
                        pointerNode.nodes[i + 1].setPosition(new Point(pointerNode.nodes[0].getX(), pointerNode.nodes[0].getY() - (i + 1) * pointerNode.nodes[0].getHeight()));
                    }


                ptr = skipList.getPtrNode();
                tailptr = skipList.getTailPtrNode();
                drawLevel = skipList.doDrawBoxLevel();
                drawFinal = skipList.doDrawBoxFinalLevel();
                dataCondition = skipList.getDataCondition();
                displayDataCondition = skipList.doDisplayDataCondition();
                drawI = skipList.doDrawIPointer();
                ilevel = skipList.getIPointer();
                drawJ = skipList.doDrawJPointer();
                jlevel = skipList.getJPointer();
                currentNewNodeLevel = skipList.getNewNodeLevel();
                maxLevel = skipList.getMaxLevel();
                drawElement = skipList.doDrawNewElement();
                runTween = skipList.doRunTween();
                boxPoint.setLocation(130, windowTop + skipNode.getHeight() + 80);
                windowTop += skipNode.getHeight() + 110;
                if(skipList.getDataNode() != null)
                    dataLine = initialiseLine(DATA_LABEL, skipList.getDataNode());
                else
                    dataLine = null;
                if(skipList.getDataItem() != null)
                {
                    String string = String.valueOf(skipList.getDataItem());
                    dataItemNode = new Node(string, 0);
                    dataItemNode.setBackgroundColor(getBackground());
                    dataItemNode.setPosition(new Point(dataConditionPoint.x - getGraphics().getFontMetrics().stringWidth(DATA_COND_LABEL) / 4, dataConditionPoint.y));
                }
                if(skipList.getNextData() != null)
                {
                    String string = String.valueOf(skipList.getNextData());
                    nextDataNode = new Node(string, 0);
                    nextDataNode.setWidth(20);
                    nextDataNode.setBackgroundColor(getBackground());
                    nextDataNode.setPosition(new Point(dataConditionPoint.x - 3 * (getGraphics().getFontMetrics().stringWidth(DATA_COND_LABEL) / 4), dataConditionPoint.y));
                }
                if(skipList.getNextNull())
                {
                    dataConditionNode = new Node(Messages.getString("SkipListCanvas.10"), 0);
                    dataConditionNode.setWidth(getGraphics().getFontMetrics().stringWidth(Messages.getString("SkipListCanvas.11")));
                    dataConditionNode.setPosition(new Point(dataConditionPoint.x, dataConditionPoint.y - dataConditionNode.getHeight()));
                    nextDataNode = new Node(Messages.getString("SkipListCanvas.12"), 0);
                    nextDataNode.setWidth(getGraphics().getFontMetrics().stringWidth(Messages.getString("SkipListCanvas.13")));
                    nextDataNode.setBackgroundColor(getBackground());
                    nextDataNode.setPosition(new Point(dataConditionPoint.x - 3 * (getGraphics().getFontMetrics().stringWidth(DATA_COND_LABEL) / 4), dataConditionPoint.y));
                } else
                if(dataCondition)
                {
                    dataConditionNode = new Node(Messages.getString("SkipListCanvas.14"), 0);
                    dataConditionNode.setWidth(20);
                    dataConditionNode.setBackgroundColor(YES_COLOR);
                    dataConditionNode.setPosition(new Point(dataConditionPoint.x, dataConditionPoint.y - dataConditionNode.getHeight()));
                } else
                {
                    dataConditionNode = new Node(Messages.getString("SkipListCanvas.15"), 0);
                    dataConditionNode.setWidth(20);
                    dataConditionNode.setBackgroundColor(NO_COLOR);
                    dataConditionNode.setPosition(new Point(dataConditionPoint.x, dataConditionPoint.y - dataConditionNode.getHeight()));
                }
                if(drawElement)
                {
                    newElement = skipList.getNewElement();
                    newElement.nodes[0].setPosition(new Point(boxPoint.x + 30, (boxPoint.y + newElement.getHeight()) - 30));
                    for(int p = 1; p < newElement.nodes.length; p++)
                    {
                        Point prev_point = newElement.nodes[p - 1].getPosition();
                        newElement.nodes[p].setPosition(new Point(prev_point.x, prev_point.y - newElement.nodes[p - 1].getHeight()));
                    }

                    windowTop = newElement.nodes[0].getY() + 10;
                }
                if(skipList.doRunTween())
                {
                    SkipNode finalNewElement = skipList.getFinalNewElement();
                    addTween(generateTween(finalNewElement));
                }
                if(tweens.getNumberOfTweens() > 0)
                    tweens.run();
            }
            if(searchElementArray != null)
            {
                searchElementArray.setElementWidth(20);
                searchElementArray.setAllHaveSameWidth(true);
                int windowWidth = getParentSize().width;
                int elementWidth = searchElementArray.getWidth();
                if(elementWidth < windowWidth)
                    searchElementArray.setLocation(windowCenterX - searchElementArray.getWidth() / 2, windowTop);
                else
                    searchElementArray.setLocation(0, windowTop);
                windowTop += searchElementArray.getHeight() + 30;
                addSelectable(searchElementArray);
            }
            if(drawLevel)
            {
                for(int i = 0; i < 10; i++)
                {
                    drawDiff = true;
                    repaint();
                    try
                    {
                        Thread.sleep(60L);
                    }
                    catch(InterruptedException f)
                    {
                        System.out.println(f);
                    }
                }

                drawDiff = false;
                drawFinal = true;
            }
        }
        repaint();
    }

    protected void handleColorSelection(ColorSelection colorselection)
    {
    }

    protected void handleFontSelection(FontSelection fontselection)
    {
    }

    public void draw(Graphics g, SkipNode head)
    {
        Point point = new Point(head.nodes[0].getPosition());
        head.nodes[0].draw(g);
        for(int i = 1; i < head.nodes.length; i++)
        {
            head.nodes[i].draw(g);
            if(head.next[i - 1] == null)
                continue;
            head.nextNodesLines[i].setStartPosition(new Point(head.nodes[i].getX() + head.nodes[i].getWidth() / 2, head.nodes[i].getY() + head.nodes[i].getHeight() / 2));
            head.nextNodesLines[i].setEndPosition(new Point(head.next[i - 1].nodes[0].getX(), (head.next[i - 1].nodes[0].getY() - i * head.nodes[i].getHeight()) + head.nodes[i].getHeight() / 2));
            head.nextNodesLines[i].setDistanceFromStartForArrowHead(-3);
            head.nextNodesLines[i].showArrowHead(true);
            if(head.nextNodesLines[i].getColor() != Color.red && head.nextNodesLines[i].getColor() != Color.white)
                head.nextNodesLines[i].setColor(TEXT_COLOR);
            if(head.nextNodesLines[i].getColor() == Color.white)
                head.nextNodesLines[i].setColor(getBackground());
            head.nextNodesLines[i].draw(g);
        }

    }

    public void draw(Graphics g)
    {
        SkipNode newSkipNode = skipNode.next[0];
        int counter = 0;
        Point point = new Point(skipNode.nodes[0].getX() + 40, skipNode.nodes[0].getY());
        while(newSkipNode != null) 
        {
            newSkipNode.nodes[0].draw(g);
            point.move(point.x, point.y - newSkipNode.nodes[0].getHeight());
            newSkipNode.nodes[1].draw(g);
            if(newSkipNode.next[0] != null)
            {
                newSkipNode.nextNodesLines[1].setStartPosition(new Point(point.x + newSkipNode.nodes[0].getWidth() / 2, point.y + newSkipNode.nodes[0].getHeight() / 2));
                newSkipNode.nextNodesLines[1].setEndPosition(new Point(point.x + 40, point.y + newSkipNode.nodes[0].getHeight() / 2));
                newSkipNode.nextNodesLines[1].setDistanceFromStartForArrowHead(-3);
                newSkipNode.nextNodesLines[1].showArrowHead(true);
                if(newSkipNode.nextNodesLines[1].getColor() != Color.red && newSkipNode.nextNodesLines[1].getColor() != Color.white)
                    newSkipNode.nextNodesLines[1].setColor(TEXT_COLOR);
                if(newSkipNode.nextNodesLines[1].getColor() == Color.white)
                    newSkipNode.nextNodesLines[1].setColor(getBackground());
                newSkipNode.nextNodesLines[1].draw(g);
            }
            newSkipNode = newSkipNode.next[0];
            counter++;
            point.move(point.x + 40, skipNode.nodes[0].getY());
            if(newSkipNode != null);
        }
    }

    public void draw(Graphics g, int atLevel)
    {
        SkipNode newSkipNode = skipNode.next[atLevel - 1];
        Point point = new Point(0, 0);
        if(newSkipNode != null)
            point = new Point(newSkipNode.nodes[0].getX(), skipNode.nodes[0].getY() - atLevel * skipNode.nodes[0].getHeight());
        do
        {
            if(newSkipNode == null)
                break;
            newSkipNode.nodes[atLevel].draw(g);
            if(newSkipNode.next[atLevel - 1] != null)
            {
                newSkipNode.nextNodesLines[atLevel].setStartPosition(new Point(newSkipNode.nodes[atLevel].getX() + newSkipNode.nodes[atLevel].getWidth() / 2, newSkipNode.nodes[atLevel].getY() + newSkipNode.nodes[atLevel].getHeight() / 2));
                newSkipNode.nextNodesLines[atLevel].setEndPosition(new Point(newSkipNode.next[atLevel - 1].nodes[0].getX(), (newSkipNode.next[atLevel - 1].nodes[0].getY() - atLevel * newSkipNode.nodes[atLevel].getHeight()) + newSkipNode.nodes[atLevel].getHeight() / 2));
                newSkipNode.nextNodesLines[atLevel].setDistanceFromStartForArrowHead(-3);
                newSkipNode.nextNodesLines[atLevel].showArrowHead(true);
                if(newSkipNode.nextNodesLines[atLevel].getColor() != Color.red && newSkipNode.nextNodesLines[atLevel].getColor() != Color.white)
                    newSkipNode.nextNodesLines[atLevel].setColor(TEXT_COLOR);
                if(newSkipNode.nextNodesLines[atLevel].getColor() == Color.white)
                    newSkipNode.nextNodesLines[atLevel].setColor(getBackground());
                newSkipNode.nextNodesLines[atLevel].draw(g);
            }
            newSkipNode = newSkipNode.next[atLevel - 1];
            if(newSkipNode != null)
                point.move(newSkipNode.nodes[0].getX(), newSkipNode.nodes[0].getY() - atLevel * newSkipNode.nodes[0].getHeight());
        } while(true);
    }

    private static SkipNode skipNode;
    private SkipNode newElement;
    private ElementArray insertElementArray;
    private ElementArray searchElementArray;
    private static int no_of_levels;
    private int ilevel;
    private int jlevel;
    private int currentNewNodeLevel;
    private int maxLevel;
    private boolean drawLevel;
    private boolean drawDiff;
    private boolean drawFinal;
    private boolean drawI;
    private boolean drawJ;
    private boolean drawElement;
    private boolean runTween;
    private boolean dataCondition;
    private boolean displayDataCondition;
    private static int test;
    private Random random;
    private Node ptr;
    private Node tailptr;
    private Node highlightedNode;
    private Node dataConditionNode;
    private Node nextDataNode;
    private Node dataItemNode;
    private static final Color YES_COLOR;
    private static final Color NO_COLOR;
    private static final Color POINTER_COLOR;
    private static final Color TEXT_COLOR;
    private static final String SEARCH_LABEL = Messages.getString("SkipListCanvas.0");
    private static final String PTR_LABEL = Messages.getString("SkipListCanvas.1");
    private static final String TAILPTR_LABEL = Messages.getString("SkipListCanvas.2");
    private static final String SAVEPTR_LABEL = Messages.getString("SkipListCanvas.3");
    private static final String I_PTR = Messages.getString("SkipListCanvas.4");
    private static final String J_PTR = Messages.getString("SkipListCanvas.5");
    private static final String DATA_COND_LABEL = Messages.getString("SkipListCanvas.6");
    private static final String DATA_LABEL = Messages.getString("SkipListCanvas.7");
    private static final int GAP = 30;
    private static final int ELEMENT_WIDTH = 20;
    private static final int boxWidth = 20;
    private Point boxPoint;
    private Point levelPoint;
    private Point dataConditionPoint;
    private Line dataLine;

    static 
    {
        YES_COLOR = Color.red;
        NO_COLOR = Color.magenta;
        POINTER_COLOR = Color.gray;
        TEXT_COLOR = Color.black;
    }
}
