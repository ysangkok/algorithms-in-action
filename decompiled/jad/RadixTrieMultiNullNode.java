// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RadixTrieMultiNullNode.java

import com.cim.AIA.HierarchyTree;
import java.awt.*;

public class RadixTrieMultiNullNode extends RadixTrieMultiNode
{

    public RadixTrieMultiNullNode(RadixTrieMultiInternalNode parent)
    {
        this.parent = parent;
        setIsLinkedIn();
        position = new Point();
    }

    public RadixTrieMultiInternalNode getParent()
    {
        return parent;
    }

    public int getRadius()
    {
        return 0;
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

    public Point getPosition()
    {
        return new Point(position);
    }

    protected Point layout(int x, int y)
    {
        position = new Point(x + 9, y);
        if(position.x - 9 < upperLeft.x)
            upperLeft.x = position.x - 9;
        if(position.y - 9 < upperLeft.y)
            upperLeft.y = position.y - 9;
        if(position.x + 9 > lowerRight.x)
            lowerRight.x = position.x + 9;
        if(position.y + 9 > lowerRight.y)
            lowerRight.y = position.y + 9;
        return new Point(x + 18, y - 40);
    }

    public synchronized Rectangle getRectangle()
    {
        return new Rectangle();
    }

    public HierarchyTree getHierarchyTree()
    {
        return null;
    }

    public void setBackgroundColor(Color color1)
    {
    }

    public void draw(Graphics g1)
    {
    }

    public void drawNode(Graphics g1)
    {
    }

    static final int NODE_X_GAP = 18;
    private static final int radius = 9;
}
