// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SortedListCanvas.java

import com.cim.AIA.*;
import java.awt.*;
import java.io.PrintStream;
import localization.Messages;

public class SortedListCanvas extends AlgorithmCanvas
{

    public SortedListCanvas()
    {
    }

    public void displayAlgorithm(Graphics g)
    {
        if(insertElementArray != null)
            insertElementArray.draw(g);
        if(searchElementArray != null)
        {
            searchElementArray.draw(g);
            g.drawString(SEARCH_LABEL, getParentSize().width / 2 - g.getFontMetrics().stringWidth(SEARCH_LABEL) / 2, searchElementArray.getLocation().y);
        }
        if(linkedList != null)
        {
            draw(g);
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
        }
    }

    public void processRepaintEvent(RepaintEvent e)
    {
        removeAllSelectables();
        if(e.paramObj != null)
        {
            int windowTop = 30;
            int windowCenterX = getParentSize().width / 2;
            SortedList sortedList = (SortedList)(SortedList)e.paramObj;
            linkedList = sortedList.getHead();
            if(sortedList.isBuildMode())
                insertElementArray = sortedList.getInsertElementArray();
            else
                insertElementArray = null;
            if(sortedList.isSearchMode())
            {
                addSelectionListener(SearchSelection.getInstance());
                searchElementArray = sortedList.getSearchElementArray();
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
            if(linkedList != null)
            {
                int windowWidth = getParentSize().width;
                Point point = new Point(30, windowTop);
                linkedList.node.setPosition(point);
                Point nextpoint = new Point(point.x + linkedList.node.getWidth(), point.y);
                linkedList.nextNode.setPosition(nextpoint);
                ptr = sortedList.getPtrNode();
                tailptr = sortedList.getTailPtrNode();
                windowTop += linkedList.getHeight();
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
        }
        repaint();
    }

    protected void handleColorSelection(ColorSelection colorselection)
    {
    }

    protected void handleFontSelection(FontSelection fontselection)
    {
    }

    public void draw(Graphics g)
    {
        SList newList = linkedList;
        int counter = 0;
        Point point = new Point(linkedList.node.getPosition());
        while(newList != null) 
        {
            System.out.println((new StringBuilder()).append(Messages.getString("SortedListCanvas.5")).append(String.valueOf(counter)).toString());
            Node currentNode = newList.node;
            Node currentNextNode = newList.nextNode;
            currentNode.setPosition(point);
            currentNode.draw(g);
            point.move(point.x + currentNode.getWidth(), point.y);
            currentNextNode.setPosition(point);
            currentNextNode.draw(g);
            if(newList.next != null)
            {
                newList.nextNodeLine = new Line(currentNextNode.getX() + currentNextNode.getWidth() / 2, currentNextNode.getY() + currentNextNode.getHeight() / 2, currentNextNode.getX() + 40, currentNextNode.getY() + currentNextNode.getHeight() / 2);
                newList.nextNodeLine.setDistanceFromStartForArrowHead(-3);
                newList.nextNodeLine.showArrowHead(true);
                newList.nextNodeLine.setColor(TEXT_COLOR);
                newList.nextNodeLine.draw(g);
            }
            newList = newList.next;
            counter++;
            point.move(point.x + 40, point.y);
            if(newList == null)
            {
                System.out.println(Messages.getString("SortedListCanvas.4"));
                return;
            }
            System.out.println("newList.next NOT null");
        }
    }

    private SList linkedList;
    private ElementArray insertElementArray;
    private ElementArray searchElementArray;
    private Node ptr;
    private Node tailptr;
    private Node highlightedNode;
    private static final Color POINTER_COLOR;
    private static final Color TEXT_COLOR;
    private static final String SEARCH_LABEL = Messages.getString("SortedListCanvas.0");
    private static final String PTR_LABEL = Messages.getString("SortedListCanvas.1");
    private static final String TAILPTR_LABEL = Messages.getString("SortedListCanvas.2");
    private static final int GAP = 30;
    private static final int ELEMENT_WIDTH = 20;

    static 
    {
        POINTER_COLOR = Color.gray;
        TEXT_COLOR = Color.black;
    }
}
