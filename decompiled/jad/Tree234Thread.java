// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Tree234Thread.java

import com.cim.AIA.*;

public class Tree234Thread extends AlgorithmThread
{

    public Tree234Thread(Copyable data, AlgorithmWindow algorithmWindow)
    {
        super(data, algorithmWindow);
    }

    public Algorithm createAlgorithm(Copyable data)
    {
        return new Tree234Tree(this, data.copy());
    }

    public Algorithm getAlgorithm()
    {
        return super.getAlgorithm();
    }
}
