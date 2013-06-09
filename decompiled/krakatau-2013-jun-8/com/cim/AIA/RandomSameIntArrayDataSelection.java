package com.cim.AIA;

public class RandomSameIntArrayDataSelection extends com.cim.AIA.DataSelection {
    final private static long serialVersionUID = -4400211993253155548L;
    protected static java.util.Random random;
    protected int minimumNumberOfElements;
    protected int maximumNumberOfElements;
    protected int maxRandomValue;
    protected int minRandomValue;
    
    public RandomSameIntArrayDataSelection(String s, boolean b, com.cim.AIA.AnimationWindow a, int i, int i0, int i1, int i2)
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
        java.util.Random a = com.cim.AIA.RandomSameIntArrayDataSelection.random;
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
        java.util.Random a1 = com.cim.AIA.RandomSameIntArrayDataSelection.random;
        float f2 = a1.nextFloat();
        int i6 = this.maxRandomValue;
        int i7 = this.minRandomValue;
        int i8 = i6 - i7;
        int i9 = i8 + 1;
        float f3 = (float)i9;
        float f4 = f2 * f3;
        int i10 = (int)f4;
        int i11 = this.minRandomValue;
        int i12 = i10 + i11;
        int i13 = 0;
        while(true)
        {
            int i14 = a0.length;
            if(i13 >= i14)
            {
                com.cim.AIA.IntArray a2 = new com.cim.AIA.IntArray(a0);
                return (com.cim.AIA.Copyable)a2;
            }
            else
            {
                a0[i13] = i12;
                int i15 = i13 + 1;
                i13 = i15;
            }
        }
    }
    
    static
    {
        java.util.Random a = new java.util.Random();
        com.cim.AIA.RandomSameIntArrayDataSelection.random = a;
    }
}