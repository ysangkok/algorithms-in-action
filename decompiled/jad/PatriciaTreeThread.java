// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PatriciaTreeThread.java

import com.cim.AIA.*;

public class PatriciaTreeThread extends AlgorithmThread
{

    public PatriciaTreeThread(Copyable data, AlgorithmWindow algorithmWindow)
    {
        super(data, algorithmWindow);
    }

    public Algorithm createAlgorithm(Copyable data)
    {
        return new PatriciaTree(this, data.copy());
    }

    public Algorithm getAlgorithm()
    {
        return super.getAlgorithm();
    }

    public AlgorithmWindow getWindow()
    {
        return algorithmWindow;
    }
}
