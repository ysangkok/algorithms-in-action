package com.cim.AIA;

public class RepaintEvent extends java.awt.AWTEvent {
    final private static long serialVersionUID = -3117146185357250859L;
    public Object paramObj;
    
    public RepaintEvent(Object a)
    {
        super(a, 10);
        this.paramObj = null;
    }
    
    public RepaintEvent(Object a, Object a0)
    {
        super(a, 10);
        this.paramObj = null;
        this.paramObj = a0;
    }
}