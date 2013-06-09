// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PatriciaNode.java

import com.cim.AIA.*;
import java.awt.*;
import localization.Messages;

public class PatriciaNode
    implements Drawable
{

    public static void clearNewNode()
    {
        newestNode = null;
    }

    public static PatriciaNode getNewNode()
    {
        return newestNode;
    }

    PatriciaNode()
    {
        isSubHead = false;
        leftIsKnown = true;
        rightIsKnown = true;
        bit = -1;
        dataItem = new PatriciaTreeDataItem();
        body = new Node("-1", dataItem.getKey());
        body.setBackgroundColor(TREE_BIT_COLOR);
        body.setMarkersBelowValue(false);
        right = this;
        left = this;
        clearNewNode();
    }

    PatriciaNode(PatriciaTreeDataItem dataItem, byte bit)
    {
        isSubHead = false;
        leftIsKnown = true;
        rightIsKnown = true;
        this.bit = bit;
        this.dataItem = dataItem;
        body = new Node(new Integer(bit), dataItem.getKey());
        body.setBackgroundColor(TREE_BIT_COLOR);
        clearNewNode();
        newestNode = this;
    }

    private void calculateDepths()
    {
        PatriciaNode head;
        for(head = this; head.parent != null; head = head.parent);
        head.getDepth();
    }

    public void draw(Graphics g)
    {
        if(isTreeEmpty())
            g.drawString(Messages.getString("PatriciaNode.1"), position.x, position.y);
        if(bit < 0)
        {
            left.isSubHead = true;
            if(left.bit > bit)
                left.draw(g);
        } else
        {
            if(newestNode != null && newestNode.parent == null && parent == newestNode)
            {
                int xOffset;
                if(parent.left == this)
                    xOffset = 8;
                else
                    xOffset = -8;
                g.drawLine(position.x + xOffset, position.y - 10, position.x, position.y);
                g.setColor(NEW_NODE_FILL_COLOR);
                g.fillOval((position.x - 5) + xOffset, position.y - 5 - 10, 10, 10);
                g.setColor(NEW_NODE_BORDER_COLOR);
                g.drawOval((position.x - 5) + xOffset, position.y - 5 - 10, 10, 10);
            }
            g.setColor(DEFAULT_COLOR);
            if(left.bit > bit)
            {
                if(leftIsKnown)
                {
                    g.drawLine(position.x, position.y, left.position.x, left.position.y);
                    left.draw(g);
                } else
                {
                    g.setColor(RECTAGLE_COLOR);
                    g.drawRect(left.leftEdge(), left.position.y, left.getWidth(), 10 * depth);
                    g.drawLine(position.x, position.y, left.position.x, left.position.y);
                    left.isSubHead = true;
                    left.draw(g, new Point(getSubHead().rightEdge() + 16, position.y + 10));
                }
            } else
            if(!leftIsKnown)
            {
                g.setColor(RECTAGLE_COLOR);
                g.drawRect(position.x - 3 - 4, position.y + 10, 6, 10 * depth);
                g.drawLine(position.x, position.y, position.x - 4, position.y + 10);
                if(newestNode != null && newestNode.parent == null)
                {
                    g.setColor(NEW_NODE_FILL_COLOR);
                    g.fillOval((getSubHead().rightEdge() - 5) + 16, (position.y - 5) + 10, 6, 10);
                    g.setColor(NEW_NODE_BORDER_COLOR);
                    g.drawOval((getSubHead().rightEdge() - 5) + 16, (position.y - 5) + 10, 10, 10);
                }
                g.setColor(DEFAULT_COLOR);
            }
            if(right.bit > bit)
            {
                if(rightIsKnown)
                {
                    g.drawLine(position.x, position.y, right.position.x, right.position.y);
                    right.draw(g);
                } else
                {
                    g.setColor(RECTAGLE_COLOR);
                    g.drawRect(right.leftEdge(), right.position.y, right.getWidth(), 10 * depth);
                    g.drawLine(position.x, position.y, right.position.x, right.position.y);
                    right.isSubHead = true;
                    right.draw(g, new Point(getSubHead().rightEdge() + 16, position.y + 10));
                }
            } else
            if(!rightIsKnown)
            {
                g.setColor(RECTAGLE_COLOR);
                g.drawRect(position.x + 4, position.y + 10, 6, 10 * depth);
                g.drawLine(position.x, position.y, position.x + 4, position.y + 10);
                if(newestNode != null && newestNode.parent == null)
                {
                    g.setColor(NEW_NODE_FILL_COLOR);
                    g.fillOval((getSubHead().rightEdge() - 5) + 16, (position.y + 10) - 5, 10, 10);
                    g.setColor(NEW_NODE_BORDER_COLOR);
                    g.drawOval((getSubHead().rightEdge() - 5) + 16, (position.y + 10) - 5, 10, 10);
                }
            }
            if(newestNode != this)
            {
                g.setColor(Color.white);
                g.fillOval(position.x - 3, position.y - 3, 6, 6);
                g.setColor(DEFAULT_COLOR);
                g.drawOval(position.x - 3, position.y - 3, 6, 6);
            } else
            {
                g.setColor(NEW_NODE_FILL_COLOR);
                g.fillOval(position.x - 5, position.y - 5, 10, 10);
                g.setColor(NEW_NODE_BORDER_COLOR);
                g.drawOval(position.x - 5, position.y - 5, 10, 10);
            }
        }
        g.setColor(DEFAULT_COLOR);
    }

    public void draw(Graphics g, Point position)
    {
        setPosition(position);
        draw(g);
    }

    public byte getBit()
    {
        return bit;
    }

    public Node getBody()
    {
        return body;
    }

    public PatriciaTreeDataItem getDataItem()
    {
        return dataItem;
    }

    private int getDepth()
    {
        int leftDepth = 0;
        int rightDepth = 0;
        if(left != null && left.bit > bit)
            leftDepth = left.getDepth();
        if(right != null && right.bit > bit)
            rightDepth = right.getDepth();
        depth = Math.max(leftDepth, rightDepth) + 1;
        return depth;
    }

    public int getHeight()
    {
        PatriciaNode head;
        for(head = this; head.parent != null; head = head.parent);
        return head.getDepth() * 10;
    }

    public HierarchyTree getHierarchyTree()
    {
        return hierarchyTree;
    }

    public PatriciaNode getLeft()
    {
        return left;
    }

    public Point getPosition()
    {
        return position;
    }

    public PatriciaNode getRight()
    {
        return right;
    }

    PatriciaNode getSubHead()
    {
        PatriciaNode subHead;
        for(subHead = this; subHead.parent != null && !subHead.isSubHead; subHead = subHead.parent);
        return subHead;
    }

    private int getWidth()
    {
        return rightEdge() - leftEdge();
    }

    public boolean isTreeEmpty()
    {
        return right == this && left == this;
    }

    private Point layout(int x, int y)
    {
        Point position = new Point(x, y);
        if(left.bit > bit)
            position = left.layout(x, y + 10);
        position.x += 8;
        this.position = position;
        if(right.bit > bit)
            position = right.layout(position.x, position.y + 10);
        return new Point(position.x, position.y - 10);
    }

    private int leftEdge()
    {
        if(left.bit > bit)
            return left.leftEdge();
        else
            return position.x - 3;
    }

    private int rightEdge()
    {
        if(right.bit > bit)
            return right.rightEdge();
        if(!rightIsKnown)
            return position.x + 6 + 4;
        else
            return position.x + 3;
    }

    public void setHierarchyTree(HierarchyTree newTree)
    {
        hierarchyTree = newTree;
    }

    public void setLeft(PatriciaNode node)
    {
        left = node;
        if(left.bit > bit)
            left.parent = this;
        calculateDepths();
    }

    public void setLeftIsKnown(boolean leftIsKnown)
    {
        this.leftIsKnown = leftIsKnown;
    }

    public void setPosition(Point position)
    {
        layout(position.x - 8, position.y);
    }

    public void setRight(PatriciaNode node)
    {
        right = node;
        if(right.bit > bit)
            right.parent = this;
        calculateDepths();
    }

    public void setRightIsKnown(boolean rightIsKnown)
    {
        this.rightIsKnown = rightIsKnown;
    }

    static final int xGap = 8;
    static final int yGap = 10;
    static final int radius = 3;
    static final int newNodeRadius = 5;
    static PatriciaNode newestNode;
    private static final Color RECTAGLE_COLOR;
    private static final Color DEFAULT_COLOR;
    private static final Color NEW_NODE_FILL_COLOR;
    private static final Color NEW_NODE_BORDER_COLOR;
    private static final Color TREE_BIT_COLOR;
    private static final Color TREE_BIT_HIGHLIGHT_COLOR = new Color(255, 255, 192);
    private PatriciaNode left;
    private PatriciaNode right;
    private HierarchyTree hierarchyTree;
    private byte bit;
    private PatriciaTreeDataItem dataItem;
    private Node body;
    boolean isSubHead;
    boolean leftIsKnown;
    boolean rightIsKnown;
    Point position;
    int depth;
    private PatriciaNode parent;

    static 
    {
        RECTAGLE_COLOR = Color.gray;
        DEFAULT_COLOR = Color.black;
        NEW_NODE_FILL_COLOR = Color.green;
        NEW_NODE_BORDER_COLOR = Color.red;
        TREE_BIT_COLOR = Color.yellow;
    }
}
