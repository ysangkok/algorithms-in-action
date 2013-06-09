package com.cim.AIA;

public class FinishEvent extends java.awt.AWTEvent {
    final private static long serialVersionUID = 3596478677582710856L;
    public Object paramObj;
    
    public FinishEvent(Object a)
    {
        super(a, 10);
        this.paramObj = null;
    }
    
    public FinishEvent(Object a, Object a0)
    {
        super(a, 10);
        this.paramObj = null;
        this.paramObj = a0;
    }
}