// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MergeSortThread.java

import com.cim.AIA.*;

public class MergeSortThread extends AlgorithmThread
{

    public MergeSortThread(Copyable data, AlgorithmWindow algorithmWindow)
    {
        super(data, algorithmWindow);
    }

    public Algorithm createAlgorithm(Copyable data)
    {
        return new MergeSort(this, data.copy());
    }

    public AlgorithmWindow getWindow()
    {
        return super.getAlgorithmWindow();
    }

    public synchronized void backMode()
    {
        ((MergeSort)super.algorithm).resetRandomSeed();
        super.backMode();
    }

    public void resetSeed()
    {
        ((MergeSort)super.algorithm).resetRandomSeed();
    }
}
