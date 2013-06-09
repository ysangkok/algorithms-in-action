package com.cim.AIA;

import java.awt.*;

public class FinishEvent extends AWTEvent
{
    private static final long serialVersionUID = 3596478677582710856L;
    public Object paramObj;
    
    public FinishEvent(final Object source) {
        super(source, 10);
        this.paramObj = null;
    }
    
    public FinishEvent(final Object source, final Object paramObj) {
        super(source, 10);
        this.paramObj = null;
        this.paramObj = paramObj;
    }
}
