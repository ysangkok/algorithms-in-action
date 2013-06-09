package com.cim.AIA;

public class StringArray implements Copyable
{
    protected String[] data;
    
    public StringArray(final String[] data) {
        super();
        this.data = data;
    }
    
    public StringArray(final String data, final String data2) {
        super();
        this.data = new String[2];
        this.data[0] = data;
        this.data[1] = data2;
    }
    
    public Object copy() {
        final String[] temp = new String[this.data.length];
        System.arraycopy(this.data, 0, temp, 0, temp.length);
        return temp;
    }
    
    public boolean isEmpty() {
        return this.data == null || this.data.length == 0 || this.data[0] == null || this.data[0].length() == 0;
    }
}
