// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RadixTrieInternalNode.java

import com.cim.AIA.*;
import java.awt.*;

public class RadixTrieInternalNode extends RadixTrieNode
{

    RadixTrieInternalNode()
    {
        backgroundColor = Color.orange;
        DEFAULT_COLOR = Color.black;
        position = new Point(0, 0);
        hierarchyTree = new HierarchyTree();
        Node node = new Node("", 0);
        node.setWidth(6);
        node.setHeight(6);
        hierarchyTree.add(node);
        hierarchyTree.addChild(new HierarchyTree());
        hierarchyTree.addChild(new HierarchyTree());
        hierarchyTree.setDrawBorder(false);
        left = new RadixTrieNullNode(this);
        right = new RadixTrieNullNode(this);
    }

    public RadixTrieInternalNode getParent()
    {
        return parent;
    }

    public int getRadius()
    {
        return 3;
    }

    public Point getPosition()
    {
        return new Point(position);
    }

    public void setPosition(Point position)
    {
        upperLeft = new Point();
        upperLeft.y = position.y - 3;
        upperLeft.x = position.x - 3;
        lowerRight = new Point();
        lowerRight.y = position.y + 3;
        lowerRight.x = position.x + 3;
        Point currentPosition;
        if(left != null)
        {
            currentPosition = left.layout(position.x - 8, position.y + 20);
            currentPosition = left.layout((position.x + position.x) - currentPosition.x - 8, position.y + 20);
        }
        this.position = position;
        currentPosition = new Point(position);
        if(right != null)
            right.layout(currentPosition.x + 4, currentPosition.y + 20);
    }

    protected synchronized Point layout(int x, int y)
    {
        Point position = new Point(x, y);
        if(left != null)
            position = left.layout(x, y + 20);
        position.x += 4;
        this.position = position;
        if(position.x - 3 < upperLeft.x)
            upperLeft.x = position.x - 3;
        if(position.y - 3 < upperLeft.y)
            upperLeft.y = position.y - 3;
        if(position.x + 3 > lowerRight.x)
            lowerRight.x = position.x + 3;
        if(position.y + 3 > lowerRight.y)
            lowerRight.y = position.y + 3;
        if(right != null)
            position = right.layout(position.x + 4, position.y + 20);
        return new Point(position.x, position.y - 20);
    }

    public Rectangle getRectangle()
    {
        if(position == null)
            position = new Point();
        setPosition(position);
        return new Rectangle(upperLeft.x, upperLeft.y, lowerRight.x - upperLeft.x, lowerRight.y - upperLeft.y);
    }

    public void draw(Graphics g)
    {
        g.setColor(DEFAULT_COLOR);
        if(left != null)
        {
            if(left.getIsLinkedIn())
            {
                Line leftLink;
                if(left.getIsOnPath())
                {
                    leftLink = new Line(position.x, position.y, left.position.x, left.position.y, Color.red);
                    leftLink.showAsThick(true);
                } else
                {
                    leftLink = new Line(position.x, position.y, left.position.x, left.position.y, Color.black);
                }
                leftLink.draw(g);
            }
            left.draw(g);
        } else
        {
            g.drawLine(position.x, position.y, position.x - 8, position.y + 13);
        }
        if(right != null)
        {
            if(right.getIsLinkedIn())
            {
                Line rightLink;
                if(right.getIsOnPath())
                {
                    rightLink = new Line(position.x, position.y, right.position.x, right.position.y, Color.red);
                    rightLink.showAsThick(true);
                } else
                {
                    rightLink = new Line(position.x, position.y, right.position.x, right.position.y, Color.black);
                }
                rightLink.draw(g);
            }
            right.draw(g);
        } else
        {
            g.drawLine(position.x, position.y, position.x + 8, position.y + 13);
        }
        if(parent == null)
            drawNode(g);
        if(left != null)
            left.drawNode(g);
        if(right != null)
            right.drawNode(g);
    }

    public void drawNode(Graphics g)
    {
        g.setColor(backgroundColor.brighter());
        g.fillOval(position.x - 3 - 1, position.y - 3 - 1, 6, 6);
        g.setColor(backgroundColor.darker());
        g.fillOval((position.x - 3) + 1, (position.y - 3) + 1, 6, 6);
        g.setColor(backgroundColor);
        g.fillOval(position.x - 3, position.y - 3, 6, 6);
        g.setColor(DEFAULT_COLOR);
    }

    public void setLeft(RadixTrieNode left)
    {
        this.left = left;
        this.left.parent = this;
        hierarchyTree.removeChild(hierarchyTree.getChild(0));
        hierarchyTree.insertChildAt(left.getHierarchyTree(), 0);
    }

    public void setRight(RadixTrieNode right)
    {
        this.right = right;
        this.right.parent = this;
        hierarchyTree.removeChild(hierarchyTree.getChild(1));
        hierarchyTree.insertChildAt(right.getHierarchyTree(), 1);
    }

    public void setBackgroundColor(Color color)
    {
        backgroundColor = color;
    }

    public RadixTrieNode getLeft()
    {
        return left;
    }

    public RadixTrieNode getRight()
    {
        return right;
    }

    public HierarchyTree getHierarchyTree()
    {
        return hierarchyTree;
    }

    private RadixTrieNode right;
    private RadixTrieNode left;
    protected static final int NODE_X_GAP = 8;
    private static final int radius = 3;
    private static final int nullWidth = 6;
    private HierarchyTree hierarchyTree;
    private Color backgroundColor;
    private Color DEFAULT_COLOR;
}
