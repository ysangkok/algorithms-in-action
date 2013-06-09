package com.cim.AIA;

public class ExitEvent extends java.awt.AWTEvent {
    final private static long serialVersionUID = -2007114298458065858L;
    public Object paramObj;
    
    public ExitEvent(Object a)
    {
        super(a, 10);
        this.paramObj = null;
    }
    
    public ExitEvent(Object a, Object a0)
    {
        super(a, 10);
        this.paramObj = null;
        this.paramObj = a0;
    }
}