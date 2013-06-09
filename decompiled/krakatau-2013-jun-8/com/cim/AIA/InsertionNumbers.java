package com.cim.AIA;

public class InsertionNumbers {
    protected int[] numbers;
    protected int position;
    
    public InsertionNumbers(int[] a)
    {
        super();
        this.position = 0;
        this.setNumbers(a);
    }
    
    public int getNextNumber()
    {
        int i = 0;
        int i0 = this.isFinished()?1:0;
        if(i0 != 0)
        {
            i = -1;
        }
        else
        {
            int[] a = this.numbers;
            int i1 = this.position;
            int i2 = i1 + 1;
            this.position = i2;
            int i3 = a[i1];
            i = i3;
        }
        return i;
    }
    
    public int[] getNumbersDone()
    {
        int i = this.position;
        int[] a = new int[i];
        int[] a0 = this.numbers;
        int i0 = this.position;
        System.arraycopy((Object)a0, 0, (Object)a, 0, i0);
        return a;
    }
    
    public int[] getNumbersToGo()
    {
        int[] a = this.numbers;
        int i = a.length;
        int i0 = this.position;
        int i1 = i - i0;
        int[] a0 = new int[i1];
        int[] a1 = this.numbers;
        int i2 = this.position;
        int i3 = a0.length;
        System.arraycopy((Object)a1, i2, (Object)a0, 0, i3);
        return a0;
    }
    
    public boolean isFinished()
    {
        int i = this.position;
        int[] a = this.numbers;
        int i0 = a.length;
        int i1 = i < i0?0:1;
        return i1 != 0;
    }
    
    public void setNumbers(int[] a)
    {
        int i = a.length;
        int[] a0 = new int[i];
        this.numbers = a0;
        int[] a1 = this.numbers;
        int i0 = a.length;
        System.arraycopy((Object)a, 0, (Object)a1, 0, i0);
        this.position = 0;
    }
}