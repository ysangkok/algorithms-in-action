package com.cim.AIA;

public class MethodSelectionEvent extends java.awt.AWTEvent {
    final private static long serialVersionUID = -8969156830190400918L;
    protected String methodName;
    
    public MethodSelectionEvent(Object a, String s)
    {
        super(a, 10);
        this.methodName = s;
    }
    
    public String getMethodName()
    {
        String s = this.methodName;
        return s;
    }
}