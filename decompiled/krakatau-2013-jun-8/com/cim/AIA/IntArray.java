package com.cim.AIA;

public class IntArray implements com.cim.AIA.Copyable {
    protected int[] data;
    
    public IntArray(int[] a)
    {
        super();
        this.data = a;
    }
    
    public Object copy()
    {
        int[] a = this.data;
        int i = a.length;
        int[] a0 = new int[i];
        int[] a1 = this.data;
        int i0 = a0.length;
        System.arraycopy((Object)a1, 0, (Object)a0, 0, i0);
        return a0;
    }
    
    public boolean isEmpty()
    {
        int i = 0;
        int[] a = this.data;
        label1: {
            label0: {
                if(a != null)
                {
                    break label0;
                }
                i = 1;
                break label1;
            }
            int[] a0 = this.data;
            int i0 = a0.length;
            i = i0 != 0?0:1;
        }
        return i != 0;
    }
}