// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Polygon3D.java

import java.util.Vector;

public class Polygon3D
{

    public Polygon3D()
    {
        points = new Vector();
    }

    public void addPoint(Point3D thePoint)
    {
        points.addElement(thePoint);
    }

    public Point3D getPoint(int index)
    {
        return (Point3D)points.elementAt(index);
    }

    public int size()
    {
        return points.size();
    }

    Vector points;
}
