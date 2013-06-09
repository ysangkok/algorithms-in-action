// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MergeBUSortThread.java

import com.cim.AIA.*;

public class MergeBUSortThread extends AlgorithmThread
{

    public MergeBUSortThread(Copyable data, AlgorithmWindow algorithmWindow)
    {
        super(data, algorithmWindow);
    }

    public Algorithm createAlgorithm(Copyable data)
    {
        return new MergeBUSort(this, data.copy());
    }

    public AlgorithmWindow getWindow()
    {
        return super.getAlgorithmWindow();
    }

    public synchronized void backMode()
    {
        ((MergeBUSort)super.algorithm).resetRandomSeed();
        super.backMode();
    }

    public void resetSeed()
    {
        ((MergeBUSort)super.algorithm).resetRandomSeed();
    }
}
