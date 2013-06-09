// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MyVertBar.java

import com.cim.AIA.VerticalBar;
import java.awt.*;

public class MyVertBar extends VerticalBar
{

    public MyVertBar(int seq, int val, Color color, Point p)
    {
        super(seq, val, color, p);
        _isEmpty = false;
    }

    public MyVertBar(int seq, Integer val, Color color, Point p)
    {
        this(seq, val.intValue(), color, p);
    }

    public void draw(Graphics g)
    {
        super.draw(g);
    }

    public boolean isEmpty()
    {
        return _isEmpty;
    }

    public void setIsEmpty(boolean val)
    {
        _isEmpty = val;
    }

    private boolean _isEmpty;
}
