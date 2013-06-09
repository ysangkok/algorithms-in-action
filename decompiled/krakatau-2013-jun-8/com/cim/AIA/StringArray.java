package com.cim.AIA;

public class StringArray implements com.cim.AIA.Copyable {
    protected String[] data;
    
    public StringArray(String[] a)
    {
        super();
        this.data = a;
    }
    
    public StringArray(String s, String s0)
    {
        super();
        String[] a = new String[2];
        this.data = a;
        String[] a0 = this.data;
        a0[0] = s;
        String[] a1 = this.data;
        a1[1] = s0;
    }
    
    public Object copy()
    {
        String[] a = this.data;
        int i = a.length;
        String[] a0 = new String[i];
        String[] a1 = this.data;
        int i0 = a0.length;
        System.arraycopy((Object)a1, 0, (Object)a0, 0, i0);
        return a0;
    }
    
    public boolean isEmpty()
    {
        int i = 0;
        String[] a = this.data;
        label1: {
            label0: {
                if(a != null)
                {
                    break label0;
                }
                i = 1;
                break label1;
            }
            String[] a0 = this.data;
            int i0 = a0.length;
            label2: {
                if(i0 != 0)
                {
                    break label2;
                }
                i = 1;
                break label1;
            }
            String[] a1 = this.data;
            String s = a1[0];
            label3: {
                if(s != null)
                {
                    break label3;
                }
                i = 1;
                break label1;
            }
            String[] a2 = this.data;
            String s0 = a2[0];
            int i1 = s0.length();
            i = i1 != 0?0:1;
        }
        return i != 0;
    }
}