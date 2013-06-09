package com.cim.AIA;

import java.awt.*;

public class SelectionEvent extends AWTEvent
{
    private static final long serialVersionUID = 6750604326903031514L;
    public Object paramObj;
    
    public SelectionEvent(final Object source) {
        super(source, 10);
        this.paramObj = null;
    }
    
    public SelectionEvent(final Object source, final Object paramObj) {
        super(source, 10);
        this.paramObj = null;
        this.paramObj = paramObj;
    }
}
