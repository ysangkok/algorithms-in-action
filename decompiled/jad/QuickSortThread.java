// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QuickSortThread.java

import com.cim.AIA.*;
import java.io.PrintStream;

public class QuickSortThread extends AlgorithmThread
{

    public QuickSortThread(Copyable data, AlgorithmWindow algorithmWindow)
    {
        super(data, algorithmWindow);
    }

    public synchronized void backMode()
    {
        if(Logger.DEBUG)
            System.out.println("in backMode fn (QuickSortThread)");
        ((QuickSort)super.algorithm).resetRandomSeed();
        super.backMode();
    }

    public Algorithm createAlgorithm(Copyable data)
    {
        return new QuickSort(this, data.copy());
    }

    public AlgorithmWindow getWindow()
    {
        return super.getAlgorithmWindow();
    }

    public void resetSeed()
    {
        ((QuickSort)super.algorithm).resetRandomSeed();
    }
}
