// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RadixTrieMultiExternalNode.java

import com.cim.AIA.*;
import java.awt.*;

public class RadixTrieMultiExternalNode extends RadixTrieMultiNode
{

    RadixTrieMultiExternalNode(DataItem dataItem)
    {
        backgroundColor = RadixTrieMultiColors.DEFAULT_NODE_COLOR;
        currentBackgroundColor = backgroundColor;
        this.dataItem = dataItem;
        hierarchyTree = new HierarchyTree();
        Node node = new Node((new StringBuilder()).append("").append(dataItem.getKey()).toString(), 0);
        hierarchyTree.add(node);
        position = new Point(0, 0);
        newestNode = this;
    }

    public static void clearNewestNode()
    {
        newestNode = null;
    }

    public static void clearCompareNode()
    {
        compareNode = null;
    }

    public static void setCompareNode(RadixTrieMultiExternalNode newCompareNode)
    {
        compareNode = newCompareNode;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public int getLevel()
    {
        return level;
    }

    public RadixTrieMultiInternalNode getParent()
    {
        return parent;
    }

    public int getKey()
    {
        return dataItem.getKey();
    }

    public int getRadius()
    {
        return 9;
    }

    public Point getPosition()
    {
        return new Point(position);
    }

    public void setPosition(Point position)
    {
        upperLeft = new Point();
        upperLeft.x = position.x - 9;
        upperLeft.y = position.y - 9;
        lowerRight = new Point();
        lowerRight.x = position.x + 9;
        lowerRight.y = position.y + 9;
        this.position = new Point(position);
    }

    protected synchronized Point layout(int x, int y)
    {
        position = new Point(x + 12, y);
        if(position.x - 9 < upperLeft.x)
            upperLeft.x = position.x - 9;
        if(position.y - 9 < upperLeft.y)
            upperLeft.y = position.y - 9;
        if(position.x + 9 > lowerRight.x)
            lowerRight.x = position.x + 9;
        if(position.y + 9 > lowerRight.y)
            lowerRight.y = position.y + 9;
        return new Point(x + 24, y - 40);
    }

    public Rectangle getRectangle()
    {
        setPosition(position);
        return new Rectangle(upperLeft.x, upperLeft.y, lowerRight.x - upperLeft.x, lowerRight.y - upperLeft.y);
    }

    public void setBackgroundColor(Color color)
    {
        backgroundColor = color;
    }

    public void draw(Graphics g)
    {
        if(parent == null)
            drawNode(g);
    }

    public void drawNode(Graphics g)
    {
        String label = (new StringBuilder()).append("").append(dataItem.getKey()).toString();
        if(newestNode == this)
            currentBackgroundColor = RadixTrieMultiColors.NEW_NODE_COLOR;
        else
        if(compareNode == this)
            currentBackgroundColor = RadixTrieMultiColors.COMPARE_NODE_COLOR;
        else
            currentBackgroundColor = backgroundColor;
        g.setColor(currentBackgroundColor.brighter());
        g.fillOval(position.x - 9 - 1, position.y - 9 - 1, 18, 18);
        g.setColor(currentBackgroundColor.darker());
        g.fillOval((position.x - 9) + 1, (position.y - 9) + 1, 18, 18);
        g.setColor(currentBackgroundColor);
        g.fillOval((position.x - 9) + 1, (position.y - 9) + 1, 16, 16);
        g.setColor(Color.black);
        g.drawOval(position.x - 9 - 1, position.y - 9 - 1, 20, 20);
        g.drawString(label, position.x - g.getFontMetrics().stringWidth(label) / 2, position.y + g.getFontMetrics().getHeight() / 3);
    }

    public HierarchyTree getHierarchyTree()
    {
        return hierarchyTree;
    }

    private DataItem dataItem;
    protected static final int NODE_X_GAP = 24;
    private static final int radius = 9;
    private HierarchyTree hierarchyTree;
    private Color backgroundColor;
    private Color currentBackgroundColor;
    private int level;
    private static RadixTrieMultiExternalNode newestNode;
    private static RadixTrieMultiExternalNode compareNode;
    private static final boolean isDrawFeet = false;
}
