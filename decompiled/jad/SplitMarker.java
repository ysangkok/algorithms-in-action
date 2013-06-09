// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SplitMarker.java


public class SplitMarker
{

    public SplitMarker(int level, int position)
    {
        this.level = level;
        this.position = position;
    }

    public int getLevel()
    {
        return level;
    }

    public int getPosition()
    {
        return position;
    }

    protected int level;
    protected int position;
}
