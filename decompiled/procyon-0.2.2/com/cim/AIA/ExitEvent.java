package com.cim.AIA;

import java.awt.*;

public class ExitEvent extends AWTEvent
{
    private static final long serialVersionUID = -2007114298458065858L;
    public Object paramObj;
    
    public ExitEvent(final Object source) {
        super(source, 10);
        this.paramObj = null;
    }
    
    public ExitEvent(final Object source, final Object paramObj) {
        super(source, 10);
        this.paramObj = null;
        this.paramObj = paramObj;
    }
}
