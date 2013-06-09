// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StraightRadixSortThread.java

import com.cim.AIA.*;

public class StraightRadixSortThread extends AlgorithmThread
{

    public StraightRadixSortThread(Copyable data, AlgorithmWindow algorithmWindow)
    {
        super(data, algorithmWindow);
    }

    public Algorithm createAlgorithm(Copyable data)
    {
        return new StraightRadixSort(this, data.copy());
    }
}
