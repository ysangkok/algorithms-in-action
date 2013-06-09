// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RadixTrieIterNullNode.java

import com.cim.AIA.HierarchyTree;
import java.awt.*;

public class RadixTrieIterNullNode extends RadixTrieIterNode
{

    public RadixTrieIterNullNode(RadixTrieIterInternalNode parent)
    {
        this.parent = parent;
        setIsLinkedIn();
        position = new Point();
    }

    public RadixTrieIterInternalNode getParent()
    {
        return parent;
    }

    public int getRadius()
    {
        return 0;
    }

    public void setPosition(Point point)
    {
        position = new Point(point);
    }

    public Point getPosition()
    {
        return position;
    }

    protected Point layout(int x, int y)
    {
        position = new Point(x + 7, y);
        return new Point(x + 15, y - 20);
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

    static final int NODE_X_GAP = 15;
}
