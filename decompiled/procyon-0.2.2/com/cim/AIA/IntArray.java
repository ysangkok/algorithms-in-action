package com.cim.AIA;

public class IntArray implements Copyable
{
    protected int[] data;
    
    public IntArray(final int[] data) {
        super();
        this.data = data;
    }
    
    public Object copy() {
        final int[] temp = new int[this.data.length];
        System.arraycopy(this.data, 0, temp, 0, temp.length);
        return temp;
    }
    
    public boolean isEmpty() {
        return this.data == null || this.data.length == 0;
    }
}
