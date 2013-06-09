// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PatriciaTreeIterNode.java

import com.cim.AIA.*;
import java.awt.*;

public class PatriciaTreeIterNode
    implements Drawable
{

    PatriciaTreeIterNode()
    {
        isSubHead = false;
        leftIsKnown = true;
        rightIsKnown = true;
        virtual = false;
        bit = -1;
        dataItem = new PatriciaTreeIterDataItem();
        body = new Node("-1", dataItem.getKey());
        body.setHeight(14);
        body.setWidth(15);
        body.setBackgroundColor(TREE_BIT_COLOR);
        body.setMarkersBelowValue(false);
        right = this;
        left = this;
        clearNewNode();
    }

    PatriciaTreeIterNode(PatriciaTreeIterDataItem dataItem, byte bit)
    {
        isSubHead = false;
        leftIsKnown = true;
        rightIsKnown = true;
        virtual = false;
        this.bit = bit;
        this.dataItem = dataItem;
        body = new Node(new Integer(bit), dataItem.getKey());
        body.setHeight(14);
        body.setWidth(15);
        body.setBackgroundColor(TREE_BIT_COLOR);
        clearNewNode();
        newestNode = this;
    }

    public PatriciaTreeIterNode copy()
    {
        return copy(this, this);
    }

    public static PatriciaTreeIterNode copy(PatriciaTreeIterNode root, PatriciaTreeIterNode currentRoot)
    {
        PatriciaTreeIterNode newRoot = new PatriciaTreeIterNode(root.getDataItem(), root.getBit());
        PatriciaTreeIterNode left = root.getLeft();
        PatriciaTreeIterNode right = root.getRight();
        if(left.getBit() > root.getBit())
        {
            newRoot.setLeft(copy(left, currentRoot));
        } else
        {
            PatriciaTreeIterNode v1 = new PatriciaTreeIterNode(left.getDataItem(), left.getBit());
            v1.setVirtual(true);
            newRoot.setLeft(v1);
        }
        if(right.getBit() > root.getBit())
        {
            newRoot.setRight(copy(right, currentRoot));
        } else
        {
            PatriciaTreeIterNode v1 = new PatriciaTreeIterNode(right.getDataItem(), right.getBit());
            v1.setVirtual(true);
            newRoot.setRight(v1);
        }
        if(root == currentRoot)
            newRoot = virtualToReal(newRoot, newRoot);
        newRoot.clearNewNode();
        return newRoot;
    }

    private static PatriciaTreeIterNode virtualToReal(PatriciaTreeIterNode root, PatriciaTreeIterNode currentRoot)
    {
        if(root.getLeft().getVirtual())
        {
            PatriciaTreeIterNode realLeft = findNode(currentRoot, root.getLeft().getDataItem());
            root.setLeft(realLeft);
        } else
        {
            root.setLeft(virtualToReal(root.getLeft(), currentRoot));
        }
        if(root.getRight().getVirtual())
        {
            PatriciaTreeIterNode realRight = findNode(currentRoot, root.getRight().getDataItem());
            root.setRight(realRight);
        } else
        {
            root.setRight(virtualToReal(root.getRight(), currentRoot));
        }
        return root;
    }

    private static PatriciaTreeIterNode findNode(PatriciaTreeIterNode root, PatriciaTreeIterDataItem dataItem)
    {
        PatriciaTreeIterNode result = null;
        if(root.getDataItem() == dataItem)
            return root;
        if(root.getLeft().getBit() > root.getBit())
        {
            result = findNode(root.getLeft(), dataItem);
            if(result != null)
                return result;
        }
        if(root.getRight().getBit() > root.getBit())
            return findNode(root.getRight(), dataItem);
        else
            return null;
    }

    public boolean isTreeEmpty()
    {
        return right == this && left == this;
    }

    public void clearNewNode()
    {
        if(newestNode != null)
            newestNode.body.setBackgroundColor(TREE_BIT_COLOR);
        newestNode = null;
    }

    public static PatriciaTreeIterNode getNewNode()
    {
        return newestNode;
    }

    public void setHierarchyTree(HierarchyTree newTree)
    {
        hierarchyTree = newTree;
    }

    public HierarchyTree getHierarchyTree()
    {
        return hierarchyTree;
    }

    public void setLeftIsKnown(boolean leftIsKnown)
    {
        this.leftIsKnown = leftIsKnown;
    }

    public void setRightIsKnown(boolean rightIsKnown)
    {
        this.rightIsKnown = rightIsKnown;
    }

    public void setLeft(PatriciaTreeIterNode node)
    {
        left = node;
        if(left.bit > bit)
            left.parent = this;
        calculateDepths();
    }

    public void setRight(PatriciaTreeIterNode node)
    {
        right = node;
        if(right.bit > bit)
            right.parent = this;
        calculateDepths();
    }

    public void setBit(byte bit)
    {
        this.bit = bit;
    }

    public byte getBit()
    {
        return bit;
    }

    public boolean getVirtual()
    {
        return virtual;
    }

    public void setVirtual(boolean state)
    {
        virtual = state;
    }

    public PatriciaTreeIterNode getLeft()
    {
        return left;
    }

    public PatriciaTreeIterNode getRight()
    {
        return right;
    }

    public PatriciaTreeIterDataItem getDataItem()
    {
        return dataItem;
    }

    public Node getBody()
    {
        return body;
    }

    public void draw(Graphics g, Point position)
    {
        setPosition(position);
        draw(g);
    }

    public void draw(Graphics g1)
    {
    }

    public Point getPosition()
    {
        return position;
    }

    public void setPosition(Point position)
    {
        layout(position.x - 8, position.y);
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

    PatriciaTreeIterNode getSubHead()
    {
        PatriciaTreeIterNode subHead;
        for(subHead = this; subHead.parent != null && !subHead.isSubHead; subHead = subHead.parent);
        return subHead;
    }

    public int getHeight()
    {
        PatriciaTreeIterNode head;
        for(head = this; head.parent != null; head = head.parent);
        return head.getDepth() * 10;
    }

    private int getWidth()
    {
        return rightEdge() - leftEdge();
    }

    private void calculateDepths()
    {
        PatriciaTreeIterNode head;
        for(head = this; head.parent != null; head = head.parent);
        head.getDepth();
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

    private PatriciaTreeIterNode left;
    private PatriciaTreeIterNode right;
    private HierarchyTree hierarchyTree;
    private byte bit;
    private PatriciaTreeIterDataItem dataItem;
    private Node body;
    boolean isSubHead;
    boolean leftIsKnown;
    boolean rightIsKnown;
    private boolean virtual;
    Point position;
    int depth;
    static final int xGap = 8;
    static final int yGap = 10;
    static final int radius = 3;
    static final int newNodeRadius = 5;
    private PatriciaTreeIterNode parent;
    static PatriciaTreeIterNode newestNode;
    private static final Color TREE_BIT_COLOR = new Color(200, 255, 200);
    private static final Color TREE_BIT_HIGHLIGHT_COLOR = new Color(255, 255, 192);

}
