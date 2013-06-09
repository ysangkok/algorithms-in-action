package com.cim.AIA;

public class RandomIntArrayDataSelection extends com.cim.AIA.DataSelection {
    final private static long serialVersionUID = -8838631254913670936L;
    protected static java.util.Random random;
    protected int minimumNumberOfElements;
    protected int maximumNumberOfElements;
    protected int maxRandomValue;
    protected int minRandomValue;
    
    public RandomIntArrayDataSelection(String s, boolean b, com.cim.AIA.AnimationWindow a, int i, int i0, int i1, int i2)
    {
        super(s, b, a);
        this.maximumNumberOfElements = i0;
        if(i >= i0)
        {
            this.minimumNumberOfElements = i0;
        }
        else
        {
            this.minimumNumberOfElements = i;
        }
        this.maxRandomValue = i2;
        if(i1 >= i2)
        {
            this.minRandomValue = i2;
        }
        else
        {
            this.minRandomValue = i1;
        }
    }
    
    public com.cim.AIA.Copyable getData()
    {
        java.util.Random a = com.cim.AIA.RandomIntArrayDataSelection.random;
        float f = a.nextFloat();
        int i = this.maximumNumberOfElements;
        int i0 = this.minimumNumberOfElements;
        int i1 = i - i0;
        int i2 = i1 + 1;
        float f0 = (float)i2;
        float f1 = f * f0;
        int i3 = (int)f1;
        int i4 = this.minimumNumberOfElements;
        int i5 = i3 + i4;
        int[] a0 = new int[i5];
        int i6 = 0;
        while(true)
        {
            int i7 = a0.length;
            if(i6 >= i7)
            {
                com.cim.AIA.IntArray a1 = new com.cim.AIA.IntArray(a0);
                return (com.cim.AIA.Copyable)a1;
            }
            else
            {
                java.util.Random a2 = com.cim.AIA.RandomIntArrayDataSelection.random;
                float f2 = a2.nextFloat();
                int i8 = this.maxRandomValue;
                int i9 = this.minRandomValue;
                int i10 = i8 - i9;
                int i11 = i10 + 1;
                float f3 = (float)i11;
                float f4 = f2 * f3;
                int i12 = (int)f4;
                int i13 = this.minRandomValue;
                int i14 = i12 + i13;
                a0[i6] = i14;
                int i15 = i6 + 1;
                i6 = i15;
            }
        }
    }
    
    static
    {
        java.util.Random a = new java.util.Random();
        com.cim.AIA.RandomIntArrayDataSelection.random = a;
    }
}