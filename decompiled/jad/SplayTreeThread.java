// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SplayTreeThread.java

import com.cim.AIA.*;

public class SplayTreeThread extends AlgorithmThread
{

    public SplayTreeThread(Copyable data, AlgorithmWindow algorithmWindow)
    {
        super(data, algorithmWindow);
    }

    public Algorithm createAlgorithm(Copyable data)
    {
        return new SplayTree(this, data.copy());
    }

    public AlgorithmWindow getAlgorithmWindow()
    {
        return super.getAlgorithmWindow();
    }

    public Algorithm getAlgorithm()
    {
        return super.getAlgorithm();
    }

    public int getRunSleepDuration()
    {
        return runSleepDuration;
    }
}
