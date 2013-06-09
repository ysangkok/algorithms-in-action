// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KMPMoveRequest.java

import java.awt.Point;

public class KMPMoveRequest
{

    public KMPMoveRequest(KMPString theString, int fromX, int fromY, int toX, int toY)
    {
        from = new Point();
        to = new Point();
        this.theString = theString;
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
        from.x = fromX;
        from.y = fromY;
        to.x = toX;
        to.y = toY;
    }

    public Point from;
    public Point to;
    public int fromX;
    public int fromY;
    public int toX;
    public int toY;
    public KMPString theString;
}
