package com.cim.AIA;

import java.awt.*;

public class MethodSelectionEvent extends AWTEvent
{
    private static final long serialVersionUID = -8969156830190400918L;
    protected String methodName;
    
    public MethodSelectionEvent(final Object source, final String methodName) {
        super(source, 10);
        this.methodName = methodName;
    }
    
    public String getMethodName() {
        return this.methodName;
    }
}
