// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BinarySearchTreeCanvas.java

import com.cim.AIA.*;
import java.awt.*;
import java.util.Vector;
import localization.Messages;

public class BinarySearchTreeCanvas extends AlgorithmCanvas
{

    public BinarySearchTreeCanvas()
    {
    }

    public void displayAlgorithm(Graphics g)
    {
        g.setColor(TEXT_COLOR);
        if(insertElementArray != null)
        {
            insertElementArray.draw(g);
            g.drawString(INSERT_LABEL, getParentSize().width / 2 - g.getFontMetrics().stringWidth(INSERT_LABEL) / 2, insertElementArray.getLocation().y);
        }
        if(searchElementArray != null)
        {
            searchElementArray.draw(g);
            g.drawString(SEARCH_LABEL, getParentSize().width / 2 - g.getFontMetrics().stringWidth(SEARCH_LABEL) / 2, searchElementArray.getLocation().y);
        }
        if(deleteNode != null)
            deleteNode.draw(g);
        if(hierarchyTree != null)
        {
            hierarchyTree.draw(g);
            if(parent != null)
            {
                Point point1 = new Point(getSize().width / 4, g.getFontMetrics().getHeight() + hierarchyTree.getPosition().y);
                Line parentLine = new Line(point1.x, point1.y - g.getFontMetrics().getHeight() / 2, parent.getX(), parent.getY());
                parentLine.setLabel(BinarySearchTree.PARENT_LABEL);
                parentLine.setDistanceFromStartForArrowHead(-3);
                parentLine.setDistanceFromStartForLabel(-1);
                parentLine.setXLabelOffset(-g.getFontMetrics().stringWidth(BinarySearchTree.PARENT_LABEL) / 2);
                parentLine.showArrowHead(true);
                parentLine.setColor(POINTER_COLOR);
                parentLine.draw(g);
            }
            if(ptr != null)
            {
                Point point2 = new Point(getSize().width / 4, g.getFontMetrics().getHeight() + hierarchyTree.getPosition().y + 20);
                Line ptrLine = new Line(point2.x, point2.y - g.getFontMetrics().getHeight() / 2, ptr.getX(), ptr.getY());
                ptrLine.setLabel(BinarySearchTree.PTR_LABEL);
                ptrLine.setDistanceFromStartForArrowHead(-3);
                ptrLine.setDistanceFromStartForLabel(-1);
                ptrLine.setXLabelOffset(-g.getFontMetrics().stringWidth(BinarySearchTree.PTR_LABEL) / 2);
                ptrLine.showArrowHead(true);
                ptrLine.setColor(POINTER_COLOR);
                ptrLine.draw(g);
            }
            if(dataItem != null)
            {
                Point point3 = new Point((3 * getSize().width) / 4, g.getFontMetrics().getHeight() + hierarchyTree.getPosition().y);
                Line dataItemLine = new Line(point3.x, point3.y - g.getFontMetrics().getHeight() / 2, dataItem.getX() + dataItem.getWidth(), dataItem.getY());
                dataItemLine.setLabel(BinarySearchTree.DATAITEMPTR_LABEL);
                dataItemLine.setDistanceFromStartForArrowHead(-3);
                dataItemLine.setDistanceFromStartForLabel(-1);
                dataItemLine.setXLabelOffset(-g.getFontMetrics().stringWidth(BinarySearchTree.PTR_LABEL) / 2);
                dataItemLine.showArrowHead(true);
                dataItemLine.setColor(POINTER_COLOR);
                dataItemLine.draw(g);
            }
            if(replace != null)
            {
                Point point4 = new Point((3 * getSize().width) / 4, g.getFontMetrics().getHeight() + hierarchyTree.getPosition().y + 20);
                Line replaceLine = new Line(point4.x, point4.y - g.getFontMetrics().getHeight() / 2, replace.getX() + replace.getWidth(), replace.getY());
                replaceLine.setLabel(BinarySearchTree.REPLACEMENT_LABEL);
                replaceLine.setDistanceFromStartForArrowHead(-3);
                replaceLine.setDistanceFromStartForLabel(-1);
                replaceLine.setXLabelOffset(-g.getFontMetrics().stringWidth(BinarySearchTree.PTR_LABEL) / 2);
                replaceLine.showArrowHead(true);
                replaceLine.setColor(POINTER_COLOR);
                replaceLine.draw(g);
            }
            for(int i = 0; i < newLinks.size(); i++)
            {
                NewLinks n = (NewLinks)newLinks.elementAt(i);
                Line l = n.getLine();
                l.draw(g);
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
        removeSelectionListener(DeleteSelection.getInstance());
        if(e.paramObj != null)
        {
            int windowTop = 30;
            int windowCenterX = getParentSize().width / 2;
            BinarySearchTree binarySearchTree = (BinarySearchTree)e.paramObj;
            hierarchyTree = binarySearchTree.getHierarchyTree();
            if(binarySearchTree.isBuildMode())
                insertElementArray = binarySearchTree.getInsertElementArray();
            else
                insertElementArray = null;
            if(binarySearchTree.isSearchMode())
            {
                addSelectionListener(SearchSelection.getInstance());
                searchElementArray = binarySearchTree.getSearchElementArray();
            } else
            {
                searchElementArray = null;
            }
            if(binarySearchTree.isDeleteMode())
            {
                addSelectionListener(DeleteSelection.getInstance());
                deleteNode = binarySearchTree.getdeleteNode();
            } else
            {
                deleteNode = null;
            }
            if(deleteNode != null)
            {
                deleteNode.setX(40);
                deleteNode.setY(10);
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
            if(hierarchyTree != null)
            {
                int windowWidth = getParentSize().width;
                hierarchyTree.plantTree(windowCenterX, windowTop);
                int leftPoint = hierarchyTree.getRectangle().x;
                if(leftPoint < 0)
                    hierarchyTree.plantTree(windowCenterX - leftPoint, windowTop);
                parent = binarySearchTree.getParentNode();
                ptr = binarySearchTree.getPtrNode();
                dataItem = binarySearchTree.getDataItemNode();
                replace = binarySearchTree.getReplaceNode();
                newLinks = binarySearchTree.getNewLinks();
                windowTop += 30 + hierarchyTree.getRectangle().height;
                addSelectable(hierarchyTree);
            }
            if(searchElementArray != null)
            {
                searchElementArray.setElementWidth(20);
                searchElementArray.setAllHaveSameWidth(true);
                int windowWidth = getParentSize().width;
                int windowHeight = getParentSize().height;
                int elementWidth = searchElementArray.getWidth();
                if(elementWidth < windowWidth)
                    searchElementArray.setLocation(windowCenterX - searchElementArray.getWidth() / 2, windowTop);
                else
                    searchElementArray.setLocation(0, windowTop);
                addSelectable(searchElementArray);
            }
            addTween(binarySearchTree.generateTweens(this, null, numberOfTweenSteps));
            if(tweens.getNumberOfTweens() > 0)
                tweens.run();
            binarySearchTree.removeAllAnimationRequests();
        }
        repaint();
    }

    private static final long serialVersionUID = 0x1914ec9ce7417af9L;
    private static final Color POINTER_COLOR;
    private static final Color TEXT_COLOR;
    private static final String INSERT_LABEL = Messages.getString("BinarySearchTreeCanvas.0");
    private static final String SEARCH_LABEL = Messages.getString("BinarySearchTreeCanvas.1");
    private static final int GAP = 30;
    private static final int ELEMENT_WIDTH = 20;
    private ExtendedHierarchyTree hierarchyTree;
    private ElementArray insertElementArray;
    private ElementArray searchElementArray;
    private Node deleteNode;
    private Node parent;
    private Node ptr;
    private Node dataItem;
    private Node replace;
    private Vector newLinks;

    static 
    {
        POINTER_COLOR = Color.gray;
        TEXT_COLOR = Color.black;
    }
}
