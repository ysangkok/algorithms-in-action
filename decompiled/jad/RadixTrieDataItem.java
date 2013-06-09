// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RadixTrieDataItem.java

import com.cim.AIA.*;
import java.awt.*;

public class RadixTrieDataItem extends Element
{

    public RadixTrieDataItem(String label, int key)
    {
        super(2, label);
        highlight = false;
        this.key = key;
    }

    public boolean equals(Selectable selectable)
    {
        if(selectable instanceof RadixTrieDataItem)
            return label.compareTo(((RadixTrieDataItem)selectable).label) == 0;
        else
            return false;
    }

    public void highlight()
    {
        highlight = true;
    }

    public void unHighlight()
    {
        highlight = false;
    }

    public void setPosition(Point position)
    {
        this.position = position;
    }

    public Point getPosition()
    {
        return position;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getKey()
    {
        return key;
    }

    public Node getNode()
    {
        return new Node("", 0);
    }

    public void setX(int x)
    {
        position.x = x;
    }

    public void setY(int y)
    {
        position.y = y;
    }

    public int getX()
    {
        return position.x;
    }

    public int getY()
    {
        return position.y;
    }

    public void shiftEntire(int x, int y)
    {
        position.x += x;
        position.y += y;
    }

    public Selectable getItemAt(Point point)
    {
        if((new Rectangle(position.x, position.y, getWidth(), getHeight())).contains(point))
            return this;
        else
            return null;
    }

    public void draw(Graphics g1, Point point)
    {
    }

    public void draw(Graphics g1)
    {
    }

    private int key;
    private String label;
    private int height;
    private int width;
    Point position;
    boolean highlight;
}
