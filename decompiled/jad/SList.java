// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SList.java

import com.cim.AIA.Line;
import com.cim.AIA.Node;
import java.awt.Point;
import localization.Messages;

public class SList
{

    public SList(Object value, int key)
    {
        pos = new Point(0, 0);
        height = 0;
        next = null;
        data = value;
        node = new Node(data, key);
        node.setMarkersBelowValue(false);
        nextNode = new Node(Messages.getString("SList.0"), key);
        nextNode.showObject(false);
        height = 2 * node.getHeight();
    }

    public Point getPosition()
    {
        return pos;
    }

    public void setPosition(Point pos)
    {
        this.pos.x = pos.x;
        this.pos.y = pos.y;
    }

    public int getHeight()
    {
        return height;
    }

    public void incrementHeight(int increment)
    {
        height += increment;
    }

    protected SList next;
    protected Object data;
    protected Node node;
    protected Node nextNode;
    protected Line nextNodeLine;
    protected Point pos;
    protected int height;
}
