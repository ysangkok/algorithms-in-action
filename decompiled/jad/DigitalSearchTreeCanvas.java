// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DigitalSearchTreeCanvas.java

import com.cim.AIA.*;
import java.awt.*;
import localization.Messages;

public class DigitalSearchTreeCanvas extends AlgorithmCanvas
{

    public DigitalSearchTreeCanvas()
    {
        approximateBitElementGap = 0;
    }

    public void displayAlgorithm(Graphics g)
    {
        g.setColor(TEXT_COLOR);
        if(insertElementArray != null)
            insertElementArray.draw(g);
        if(bitElementArray != null && highlightedNode != null)
        {
            g.drawString(BIT_ARRAY_LABEL, getParentSize().width / 2 - g.getFontMetrics().stringWidth(BIT_ARRAY_LABEL) / 2, bitElementArray.getLocation().y + bitElementArray.getHeight() + g.getFontMetrics().getHeight());
            if(currentBit != null)
            {
                Point point = new Point(currentBit.getX() + currentBit.getWidth() / 2, currentBit.getY() + currentBit.getHeight());
                Line bitLine = new Line(point.x, point.y + 20, point.x, point.y);
                bitLine.setLabel(DigitalSearchTree.BIT_LABEL);
                bitLine.setDistanceFromStartForArrowHead(-3);
                bitLine.setDistanceFromStartForLabel(-1);
                bitLine.setXLabelOffset(-g.getFontMetrics().stringWidth(DigitalSearchTree.BIT_LABEL) / 2);
                bitLine.showArrowHead(true);
                bitLine.setColor(POINTER_COLOR);
                bitLine.draw(g);
            }
            g.drawString("=", (bitElementArray.getLocation().x + bitElementArray.getWidth() + 15) - g.getFontMetrics().stringWidth("=") / 2, bitElementArray.getLocation().y + bitElementArray.getHeight() / 2 + g.getFontMetrics().getHeight() / 2);
            if(bitValueNode != null)
                bitValueNode.draw(g);
            bitElementArray.draw(g);
        }
        if(searchElementArray != null)
        {
            searchElementArray.draw(g);
            g.drawString(SEARCH_LABEL, getParentSize().width / 2 - g.getFontMetrics().stringWidth(SEARCH_LABEL) / 2, searchElementArray.getLocation().y);
        }
        if(hierarchyTree != null)
        {
            hierarchyTree.draw(g);
            if(parent != null)
            {
                Point point1 = new Point(getSize().width / 4, g.getFontMetrics().getHeight() + hierarchyTree.getPosition().y);
                Line parentLine = new Line(point1.x + g.getFontMetrics().stringWidth(DigitalSearchTree.PARENT_LABEL), point1.y - g.getFontMetrics().getHeight() / 2, parent.getX(), parent.getY());
                parentLine.setLabel(DigitalSearchTree.PARENT_LABEL);
                parentLine.setDistanceFromStartForArrowHead(-3);
                parentLine.setDistanceFromStartForLabel(-1);
                parentLine.setXLabelOffset(-g.getFontMetrics().stringWidth(DigitalSearchTree.PARENT_LABEL) / 2);
                parentLine.showArrowHead(true);
                parentLine.setColor(POINTER_COLOR);
                parentLine.draw(g);
            }
            if(ptr != null)
            {
                Point point2 = new Point((3 * getSize().width) / 4, g.getFontMetrics().getHeight() + hierarchyTree.getPosition().y);
                Line ptrLine = new Line(point2.x, point2.y - g.getFontMetrics().getHeight() / 2, ptr.getX() + ptr.getWidth(), ptr.getY());
                ptrLine.setLabel(DigitalSearchTree.ptrLabel);
                ptrLine.setDistanceFromStartForArrowHead(-3);
                ptrLine.setDistanceFromStartForLabel(-1);
                ptrLine.setXLabelOffset(-g.getFontMetrics().stringWidth(DigitalSearchTree.ptrLabel) / 2);
                ptrLine.showArrowHead(true);
                ptrLine.setColor(POINTER_COLOR);
                if(ptrLine.getY2() != 0.0D)
                    ptrLine.draw(g);
            }
        }
    }

    protected void handleColorSelection(ColorSelection colorselection)
    {
    }

    protected void handleFontSelection(FontSelection fontselection)
    {
    }

    public void processRepaintEvent(RepaintEvent e)
    {
        removeAllSelectables();
        removeSelectionListener(SearchSelection.getInstance());
        if(e.paramObj != null)
        {
            int windowTop = 30;
            int windowCenterX = getParentSize().width / 2;
            DigitalSearchTree digitalSearchTree = (DigitalSearchTree)e.paramObj;
            hierarchyTree = digitalSearchTree.getHierarchyTree();
            if(digitalSearchTree.isBuildMode())
                insertElementArray = digitalSearchTree.getInsertElementArray();
            else
                insertElementArray = null;
            bitElementArray = digitalSearchTree.getBitElementArray();
            highlightedNode = digitalSearchTree.getHighlightedNode();
            if(digitalSearchTree.isSearchMode())
            {
                addSelectionListener(SearchSelection.getInstance());
                searchElementArray = digitalSearchTree.getSearchElementArray();
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
                if(bitElementArray != null && highlightedNode != null)
                {
                    highlightedNode = digitalSearchTree.getHighlightedNode();
                    bitValueNode = new Node(highlightedNode.getObject(), 0);
                    bitValueNode.setBackgroundColor(highlightedNode.getBackgroundColor());
                    currentBit = digitalSearchTree.getBitNode();
                    bitElementArray.setElementWidth(20);
                    bitElementArray.setAllHaveSameWidth(true);
                    int bitElementWidth = bitElementArray.getWidth();
                    if(bitElementWidth < windowWidth)
                        bitElementArray.setLocation(windowCenterX - bitElementArray.getWidth() / 2, windowTop);
                    else
                        bitElementArray.setLocation(0, windowTop);
                    bitValueNode.setPosition(new Point(bitElementArray.getLocation().x + bitElementWidth + 30, bitElementArray.getLocation().y));
                    approximateBitElementGap = bitElementArray.getHeight() + 30 + 20;
                }
                windowTop += approximateBitElementGap;
            }
            if(hierarchyTree != null)
            {
                int windowWidth = getParentSize().width;
                hierarchyTree.plantTree(windowCenterX, windowTop);
                int leftPoint = hierarchyTree.getRectangle().x;
                if(leftPoint < 0)
                    hierarchyTree.plantTree(windowCenterX - leftPoint, windowTop);
                parent = digitalSearchTree.getParentNode();
                ptr = digitalSearchTree.getPtrNode();
                windowTop += 30 + hierarchyTree.getRectangle().height;
            } else
            {
                Node ghostNode = new Node("", 0);
                ptr = null;
                parent = null;
                ghostNode.setBackgroundColor(Color.white);
                hierarchyTree = new ExtendedHierarchyTree(ghostNode);
                hierarchyTree.plantTree(windowCenterX, windowTop);
                windowTop += 30 + hierarchyTree.getRectangle().height;
            }
            if(searchElementArray != null)
            {
                int windowWidth = getParentSize().width;
                int windowHeight = getParentSize().height;
                if(bitElementArray != null && highlightedNode != null)
                {
                    currentBit = digitalSearchTree.getBitNode();
                    bitValueNode = new Node(highlightedNode.getObject(), 0);
                    bitValueNode.setBackgroundColor(highlightedNode.getBackgroundColor());
                    bitElementArray.setElementWidth(20);
                    bitElementArray.setAllHaveSameWidth(true);
                    int bitElementWidth = bitElementArray.getWidth();
                    if(bitElementWidth < windowWidth)
                        bitElementArray.setLocation(windowCenterX - bitElementArray.getWidth() / 2, windowTop);
                    else
                        bitElementArray.setLocation(0, windowTop);
                    bitValueNode.setPosition(new Point(bitElementArray.getLocation().x + bitElementWidth + 30, bitElementArray.getLocation().y));
                    approximateBitElementGap = bitElementArray.getHeight() + 30 + 20;
                }
                windowTop += approximateBitElementGap;
                searchElementArray.setElementWidth(20);
                searchElementArray.setAllHaveSameWidth(true);
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

    private ExtendedHierarchyTree hierarchyTree;
    private ElementArray insertElementArray;
    private ElementArray searchElementArray;
    private ElementArray bitElementArray;
    private Node parent;
    private Node ptr;
    private Node currentBit;
    private Node highlightedNode;
    private Node bitValueNode;
    private static final Color POINTER_COLOR;
    private static final Color TEXT_COLOR;
    private static final String BIT_ARRAY_LABEL = Messages.getString("DigitalSearchTreeCanvas.0");
    private static final String SEARCH_LABEL = Messages.getString("DigitalSearchTreeCanvas.1");
    private int approximateBitElementGap;
    private static final int GAP = 30;
    private static final int ELEMENT_WIDTH = 20;
    private static final int BIT_LINE_LENGTH = 20;

    static 
    {
        POINTER_COLOR = Color.gray;
        TEXT_COLOR = Color.black;
    }
}
