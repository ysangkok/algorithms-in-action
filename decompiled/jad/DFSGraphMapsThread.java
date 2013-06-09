// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DFSGraphMapsThread.java

import com.cim.AIA.*;

public class DFSGraphMapsThread extends AlgorithmThread
{

    public DFSGraphMapsThread(Copyable data, AlgorithmWindow algorithmWindow)
    {
        super(data, algorithmWindow);
    }

    public Algorithm createAlgorithm(Copyable data)
    {
        return new DFSGraphMaps(this, data.copy());
    }
}
