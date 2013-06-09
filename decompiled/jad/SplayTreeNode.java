// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SplayTreeNode.java

import com.cim.AIA.Drawable;
import com.cim.AIA.Moveable;
import java.awt.*;

public abstract class SplayTreeNode
    implements Drawable, Moveable
{

    public SplayTreeNode()
    {
        isOnPath = false;
        isOnSwitchPath = false;
    }

    public abstract SplayTreeNode getCopy();

    public abstract void setUpperLeft(Point point);

    public abstract void setNodePosition(Point point);

    public abstract void setPosition(Point point);

    public abstract Point getPosition();

    public abstract void setParent(SplayTreeDataNode splaytreedatanode);

    public abstract SplayTreeDataNode getParent();

    public final Rectangle getRectangle()
    {
        if(upperLeft != null && lowerRight != null)
            return new Rectangle(upperLeft.x, upperLeft.y, lowerRight.x - upperLeft.x, lowerRight.y - upperLeft.y);
        else
            return null;
    }

    public final boolean getIsOnSwitchPath()
    {
        return isOnSwitchPath;
    }

    public final void setIsOnSwitchPath(boolean isOnSwitchPath)
    {
        this.isOnSwitchPath = isOnSwitchPath;
    }

    public final boolean getIsOnPath()
    {
        return isOnPath;
    }

    public final void setIsOnPath(boolean isOnPath)
    {
        this.isOnPath = isOnPath;
    }

    protected abstract Point layout(int i, int j);

    public abstract void draw(Graphics g);

    public final void draw(Graphics g, Point point)
    {
        setPosition(point);
        draw(g);
    }

    public abstract void drawNode(Graphics g);

    protected static Point upperLeft;
    protected static Point lowerRight;
    private boolean isOnPath;
    private boolean isOnSwitchPath;
}
