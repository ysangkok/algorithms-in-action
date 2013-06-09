// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RadixTrieIterNode.java

import com.cim.AIA.Drawable;
import com.cim.AIA.HierarchyTree;
import java.awt.*;

public abstract class RadixTrieIterNode
    implements Drawable
{

    public RadixTrieIterNode()
    {
        isLinkedIn = false;
        isOnPath = false;
    }

    public abstract RadixTrieIterInternalNode getParent();

    public final void setIsLinkedIn()
    {
        isLinkedIn = true;
    }

    public final boolean getIsLinkedIn()
    {
        return isLinkedIn;
    }

    public void setIsOnPath(boolean isOnPath)
    {
        this.isOnPath = isOnPath;
    }

    public boolean getIsOnPath()
    {
        return isOnPath;
    }

    public abstract int getRadius();

    public abstract void setPosition(Point point);

    public abstract Point getPosition();

    protected abstract Point layout(int i, int j);

    public abstract Rectangle getRectangle();

    public abstract HierarchyTree getHierarchyTree();

    public abstract void setBackgroundColor(Color color);

    public abstract void draw(Graphics g);

    public void draw(Graphics g, Point point)
    {
        setPosition(point);
        draw(g);
    }

    public abstract void drawNode(Graphics g);

    static final int NODE_Y_GAP = 20;
    protected static Point upperLeft;
    protected static Point lowerRight;
    static final int NULL_WIDTH = 8;
    static final int NULL_HEIGHT = 13;
    private boolean isLinkedIn;
    private boolean isOnPath;
    protected Point position;
    protected RadixTrieIterInternalNode parent;
}
