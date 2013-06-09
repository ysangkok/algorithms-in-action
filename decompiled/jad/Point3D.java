// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Point3D.java


public class Point3D
{

    public Point3D()
    {
        this(0.0D, 0.0D, 0.0D, 0.0D);
    }

    public Point3D(double x, double y, double z)
    {
        this(x, y, z, 1.0D);
    }

    public Point3D(double x, double y, double z, double w)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public double x;
    public double y;
    public double z;
    public double w;
}
