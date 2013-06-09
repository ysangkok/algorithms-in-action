package com.cim.AIA;

import java.awt.*;

public class RepaintEvent extends AWTEvent
{
    private static final long serialVersionUID = -3117146185357250859L;
    public Object paramObj;
    
    public RepaintEvent(final Object source) {
        super(source, 10);
        this.paramObj = null;
    }
    
    public RepaintEvent(final Object source, final Object paramObj) {
        super(source, 10);
        this.paramObj = null;
        this.paramObj = paramObj;
    }
}
