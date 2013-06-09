// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ShellSortThread.java

import com.cim.AIA.*;

public class ShellSortThread extends AlgorithmThread
{

    public ShellSortThread(Copyable data, AlgorithmWindow algorithmWindow)
    {
        super(data, algorithmWindow);
    }

    public Algorithm createAlgorithm(Copyable data)
    {
        return new ShellSort(this, data.copy());
    }

    public AlgorithmWindow getWindow()
    {
        return super.getAlgorithmWindow();
    }
}
