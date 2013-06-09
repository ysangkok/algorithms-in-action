// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SkipNode.java

import com.cim.AIA.Line;
import com.cim.AIA.Node;
import java.awt.Point;
import localization.Messages;

public class SkipNode
{

    public SkipNode(Object value, int key, int size)
    {
        pos = new Point(0, 0);
        height = 0;
        next = new SkipNode[size];
        for(int j = 0; j < size; j++)
            next[j] = null;

        data = value;
        levels = size;
        nodes = new Node[levels + 1];
        nextNodesLines = new Line[levels + 1];
        nodes[0] = new Node(data, key);
        nodes[0].setMarkersBelowValue(false);
        for(int i = 1; i < nodes.length; i++)
        {
            nodes[i] = new Node(Messages.getString("SkipNode.0"), key);
            nodes[i].showObject(false);
            nodes[i].setMarkersBelowValue(false);
        }

        for(int j = 0; j < nextNodesLines.length; j++)
            nextNodesLines[j] = new Line(new Point(0, 0), new Point(0, 0));

        height = (levels + 1) * nodes[0].getHeight();
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

    protected SkipNode next[];
    protected Object data;
    protected int levels;
    protected Node nodes[];
    protected Line nextNodesLines[];
    protected Point pos;
    protected int height;
}
