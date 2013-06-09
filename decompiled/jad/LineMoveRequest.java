// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LineMoveRequest.java

import java.awt.Point;

public class LineMoveRequest
{

    public LineMoveRequest()
    {
        fromPositionInsertedId = -1;
        toPositionInsertedId = -1;
        parentPositionInsertedId = -1;
        x1PositionInsertedId = -1;
        x2PositionInsertedId = -1;
        fromPoint = null;
        toPoint = null;
        basePoint = null;
    }

    public LineMoveRequest(int fromPositionInsertedId, int toPositionInsertedId)
    {
        this.fromPositionInsertedId = -1;
        this.toPositionInsertedId = -1;
        parentPositionInsertedId = -1;
        x1PositionInsertedId = -1;
        x2PositionInsertedId = -1;
        fromPoint = null;
        toPoint = null;
        basePoint = null;
        this.fromPositionInsertedId = fromPositionInsertedId;
        this.toPositionInsertedId = toPositionInsertedId;
    }

    public LineMoveRequest(int fromPositionInsertedId, int toPositionInsertedId, int parentPositionInsertedId)
    {
        this.fromPositionInsertedId = -1;
        this.toPositionInsertedId = -1;
        this.parentPositionInsertedId = -1;
        x1PositionInsertedId = -1;
        x2PositionInsertedId = -1;
        fromPoint = null;
        toPoint = null;
        basePoint = null;
        this.fromPositionInsertedId = fromPositionInsertedId;
        this.toPositionInsertedId = toPositionInsertedId;
        this.parentPositionInsertedId = parentPositionInsertedId;
    }

    public LineMoveRequest(Point fromPoint, Point toPoint)
    {
        fromPositionInsertedId = -1;
        toPositionInsertedId = -1;
        parentPositionInsertedId = -1;
        x1PositionInsertedId = -1;
        x2PositionInsertedId = -1;
        this.fromPoint = null;
        this.toPoint = null;
        basePoint = null;
        this.fromPoint = fromPoint;
        this.toPoint = toPoint;
    }

    public Point getBasePoint()
    {
        return basePoint;
    }

    public Point getFromPoint()
    {
        return fromPoint;
    }

    public int getFromPositionInsertedId()
    {
        return fromPositionInsertedId;
    }

    public String getLabel()
    {
        return label;
    }

    public int getParentPositionInsertedId()
    {
        return parentPositionInsertedId;
    }

    public Point getToPoint()
    {
        return toPoint;
    }

    public int getToPositionInsertedId()
    {
        return toPositionInsertedId;
    }

    public int getX1PositionInsertedId()
    {
        return x1PositionInsertedId;
    }

    public int getX2PositionInsertedId()
    {
        return x2PositionInsertedId;
    }

    public void setBasePoint(Point basePoint)
    {
        this.basePoint = basePoint;
    }

    public void setFromPoint(Point fromPoint)
    {
        this.fromPoint = fromPoint;
    }

    public void setFromPositionInsertedId(int fromPositionInsertedId)
    {
        this.fromPositionInsertedId = fromPositionInsertedId;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public void setToPoint(Point toPoint)
    {
        this.toPoint = toPoint;
    }

    public void setToPositionInsertedId(int toPositionInsertedId)
    {
        this.toPositionInsertedId = toPositionInsertedId;
    }

    public void setX1PositionInsertedId(int x1PositionInsertedId)
    {
        this.x1PositionInsertedId = x1PositionInsertedId;
    }

    public void setX2PositionInsertedId(int x2PositionInsertedId)
    {
        this.x2PositionInsertedId = x2PositionInsertedId;
    }

    public static final int IS_X1_POSITION_INSERTED_ID = -45;
    public static final int IS_X2_POSITION_INSERTED_ID = -46;
    public static final int LEFT_NULL_CHILD = -50;
    public static final int RIGHT_NULL_CHILD = -51;
    protected int fromPositionInsertedId;
    protected int toPositionInsertedId;
    protected int parentPositionInsertedId;
    protected int x1PositionInsertedId;
    protected int x2PositionInsertedId;
    protected Point fromPoint;
    protected Point toPoint;
    protected Point basePoint;
    protected String label;
}
