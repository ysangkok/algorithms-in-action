package com.cim.AIA;

public class SelectionEvent extends java.awt.AWTEvent {
    final private static long serialVersionUID = 6750604326903031514L;
    public Object paramObj;
    
    public SelectionEvent(Object a)
    {
        super(a, 10);
        this.paramObj = null;
    }
    
    public SelectionEvent(Object a, Object a0)
    {
        super(a, 10);
        this.paramObj = null;
        this.paramObj = a0;
    }
}