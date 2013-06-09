// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SplayTreeNullNode.java

import com.cim.AIA.Line;
import java.awt.Graphics;
import java.awt.Point;

public class SplayTreeNullNode extends SplayTreeNode
{

    public SplayTreeNullNode()
    {
        position = new Point();
    }

    public SplayTreeNode getCopy()
    {
        SplayTreeNullNode copyNode = new SplayTreeNullNode();
        copyNode.position = new Point(position);
        return copyNode;
    }

    public void setParent(SplayTreeDataNode parent)
    {
        this.parent = parent;
    }

    public SplayTreeDataNode getParent()
    {
        return parent;
    }

    public void setNodePosition(Point position)
    {
        this.position.x = position.x;
        this.position.y = position.y;
    }

    public Point getPosition()
    {
        return position;
    }

    public void setUpperLeft(Point position)
    {
        setPosition(position);
    }

    public void setPosition(Point position)
    {
        this.position = position;
        lowerRight = new Point(position);
        upperLeft = new Point(position);
    }

    protected Point layout(int x, int y)
    {
        position.x = x + 0;
        position.y = y + 5;
        if(position.y > lowerRight.y)
            lowerRight.y = position.y;
        return new Point(x + 0, y);
    }

    public void draw(Graphics g)
    {
        drawNode(g);
    }

    public void drawNode(Graphics g1)
    {
    }

    public void shiftEntire(int deltaX, int deltaY)
    {
        setPosition(new Point(position.x + deltaX, position.y + deltaY));
    }

    public int getY()
    {
        return position.y;
    }

    public int getX()
    {
        return position.x;
    }

    public void setY(int y)
    {
        position.y = y;
        updateParentLink();
    }

    public void setX(int x)
    {
        position.x = x;
        updateParentLink();
    }

    private void updateParentLink()
    {
        if(parent != null)
            if(parent.getLeft() == this)
                parent.getLeftLink().setEndPosition(getPosition());
            else
                parent.getRightLink().setEndPosition(getPosition());
    }

    static final int NODE_Y_GAP = 0;
    static final int NODE_X_GAP = 0;
    static final int NODE_WIDTH = 0;
    static final int NODE_HEIGHT = 5;
    private Point position;
    private SplayTreeDataNode parent;
}
