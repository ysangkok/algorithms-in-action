// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HeapSortCanvas.java

import com.cim.AIA.*;
import java.awt.*;

public class HeapSortCanvas extends AlgorithmCanvas
{

    public HeapSortCanvas()
    {
        xBuffer = 40;
        yBuffer = 150;
        displayedBefore = false;
        heapSortWidth = -1;
        iPoint = null;
        jPoint = null;
        kPoint = null;
        iNode = null;
        jNode = null;
        kNode = null;
        gapBetweenArrayAndTree = 100;
        drawMarkers = true;
        textColor = Color.black;
    }

    public void displayAlgorithm(Graphics g)
    {
        if(elementArray != null && hierarchyTree != null)
        {
            elementArray.draw(g);
            hierarchyTree.draw(g);
            if(!drawMarkers)
                return;
            g.setColor(textColor);
            if(iPoint != null)
                g.drawString("i", iPoint.x - g.getFontMetrics().stringWidth("i") / 2, iPoint.y + 2 * g.getFontMetrics().getHeight());
            if(jPoint != null)
                g.drawString("J", jPoint.x - g.getFontMetrics().stringWidth("J") / 2, jPoint.y + 3 * g.getFontMetrics().getHeight());
            if(kPoint != null)
                g.drawString("k", kPoint.x - g.getFontMetrics().stringWidth("k") / 2, kPoint.y + 1 * g.getFontMetrics().getHeight());
            if(kNode != null)
            {
                Line line = new Line(kNode.getPosition().x + kNode.getWidth() / 2, (gapBetweenArrayAndTree + yBuffer) - 1 * g.getFontMetrics().getHeight(), kNode.getPosition().x + kNode.getWidth() / 2, kNode.getPosition().y);
                if(line.getX() != 0 && line.getY() != 0)
                {
                    line.showArrowHead(true);
                    line.setDistanceFromStartForArrowHead(-3);
                    line.draw(g);
                    g.drawString("k", (kNode.getPosition().x + kNode.getWidth() / 2) - g.getFontMetrics().stringWidth("k") / 2, (gapBetweenArrayAndTree + yBuffer) - 1 * g.getFontMetrics().getHeight());
                }
            }
            if(iNode != null)
            {
                Line line = new Line(getSize().width / 3, yBuffer + gapBetweenArrayAndTree + hierarchyTree.getTotalHeight() + g.getFontMetrics().getHeight(), iNode.getPosition().x + iNode.getWidth() / 2, iNode.getPosition().y + iNode.getHeight());
                if(line.getX() != 0 && line.getY() != 0)
                {
                    line.showArrowHead(true);
                    line.setDistanceFromStartForArrowHead(-3);
                    line.draw(g);
                    g.drawString("i", getSize().width / 3 - g.getFontMetrics().stringWidth("i") / 2, gapBetweenArrayAndTree + yBuffer + hierarchyTree.getTotalHeight() + 2 * g.getFontMetrics().getHeight());
                }
            }
            if(jNode != null)
            {
                Line line = new Line((2 * getSize().width) / 3, yBuffer + gapBetweenArrayAndTree + hierarchyTree.getTotalHeight() + g.getFontMetrics().getHeight(), jNode.getPosition().x + jNode.getWidth() / 2, jNode.getPosition().y + jNode.getHeight());
                if(line.getX() != 0 && line.getY() != 0)
                {
                    line.showArrowHead(true);
                    line.setDistanceFromStartForArrowHead(-3);
                    line.draw(g);
                    g.drawString("J", (2 * getSize().width) / 3 - g.getFontMetrics().stringWidth("J") / 2, gapBetweenArrayAndTree + yBuffer + hierarchyTree.getTotalHeight() + 2 * g.getFontMetrics().getHeight());
                }
            }
        }
    }

    protected void handleColorSelection(ColorSelection colorSelection)
    {
        String atribute = colorSelection.getAtributeName();
        if(atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR))
            textColor = colorSelection.getColor();
    }

    protected void handleFontSelection(FontSelection fontselection)
    {
    }

    public void processRepaintEvent(RepaintEvent e)
    {
        if(e.paramObj != null)
        {
            iPoint = jPoint = kPoint = null;
            iNode = jNode = kNode = null;
            heapSort = (HeapSort)(HeapSort)e.paramObj;
            removeAllSelectables();
            elementArray = heapSort.getElementArray(new Point(xBuffer, yBuffer));
            hierarchyTree = heapSort.getHierarchyTree();
            addSelectable(elementArray);
            addSelectable(hierarchyTree);
            int elementArrayWidth = elementArray.getWidth();
            int width = elementArrayWidth + 2 * xBuffer;
            int height = 3 * yBuffer;
            setSize(width, height);
            heapSortWidth = getSize().width;
            elementArray.setLocation(heapSortWidth / 2 - elementArrayWidth / 2, yBuffer);
            hierarchyTree.plantTree(heapSortWidth / 2, gapBetweenArrayAndTree + yBuffer);
            if(heapSort.getI() != -10)
            {
                VerticalBar iElement = (VerticalBar)(VerticalBar)elementArray.getElement(heapSort.getI());
                if(iElement != null)
                    iPoint = new Point(iElement.getPosition().x + elementArray.getElementWidth() / 2, iElement.getPosition().y + iElement.getRealOffsetForValue(getGraphics()));
                iNode = heapSort.getNode(heapSort.getI());
            }
            if(heapSort.getJ() != -10)
            {
                VerticalBar jElement = (VerticalBar)(VerticalBar)elementArray.getElement(heapSort.getJ());
                if(jElement != null)
                    jPoint = new Point(jElement.getPosition().x + elementArray.getElementWidth() / 2, jElement.getPosition().y + jElement.getRealOffsetForValue(getGraphics()));
                jNode = heapSort.getNode(heapSort.getJ());
            }
            if(heapSort.getK() != -10)
            {
                VerticalBar kElement = (VerticalBar)(VerticalBar)elementArray.getElement(heapSort.getK());
                if(kElement != null)
                    kPoint = new Point(kElement.getPosition().x + elementArray.getElementWidth() / 2, kElement.getRealOffsetForValue(getGraphics()));
                kNode = heapSort.getNode(heapSort.getK());
            }
            addTween(heapSort.generateTweens(this, elementArray, numberOfTweenSteps));
            heapSort.removeAllAnimationRequests();
            tweens.run();
            repaint();
            heapSort.unHighlightAll();
        }
    }

    public void setMarkersEnabled(boolean state)
    {
        drawMarkers = state;
        repaint();
    }

    private static final long serialVersionUID = 0xfd1d150601b636e3L;
    protected HeapSort heapSort;
    protected HierarchyTree hierarchyTree;
    protected ElementArray elementArray;
    protected int xBuffer;
    protected int yBuffer;
    protected boolean displayedBefore;
    protected int heapSortWidth;
    protected Point iPoint;
    protected Point jPoint;
    protected Point kPoint;
    protected Node iNode;
    protected Node jNode;
    protected Node kNode;
    protected int gapBetweenArrayAndTree;
    protected boolean drawMarkers;
    protected Color textColor;
}
