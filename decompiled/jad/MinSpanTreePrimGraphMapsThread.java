// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MinSpanTreePrimGraphMapsThread.java

import com.cim.AIA.*;

public class MinSpanTreePrimGraphMapsThread extends AlgorithmThread
{

    public MinSpanTreePrimGraphMapsThread(Copyable data, AlgorithmWindow algorithmWindow)
    {
        super(data, algorithmWindow);
    }

    public Algorithm createAlgorithm(Copyable data)
    {
        return new MinSpanTreePrimGraphMaps(this, data.copy());
    }
}
