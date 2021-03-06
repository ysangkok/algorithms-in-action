// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DigitalSearchTreeThread.java

import com.cim.AIA.*;

public class DigitalSearchTreeThread extends AlgorithmThread
{

    public DigitalSearchTreeThread(Copyable data, AlgorithmWindow algorithmWindow)
    {
        super(data, algorithmWindow);
    }

    public Algorithm createAlgorithm(Copyable data)
    {
        return new DigitalSearchTree(this, data.copy());
    }

    public AlgorithmWindow getWindow()
    {
        return super.getAlgorithmWindow();
    }

    public Algorithm getAlgorithm()
    {
        return super.getAlgorithm();
    }
}
