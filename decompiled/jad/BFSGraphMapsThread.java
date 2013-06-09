// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BFSGraphMapsThread.java

import com.cim.AIA.*;

public class BFSGraphMapsThread extends AlgorithmThread
{

    public BFSGraphMapsThread(Copyable data, AlgorithmWindow algorithmWindow)
    {
        super(data, algorithmWindow);
    }

    public Algorithm createAlgorithm(Copyable data)
    {
        return new BFSGraphMaps(this, data.copy());
    }
}
