package com.cim.AIA;

import java.awt.*;

public class ExplainationEvent extends AWTEvent
{
    private static final long serialVersionUID = 8058754301963600182L;
    protected String title;
    protected String explaination;
    
    public ExplainationEvent(final Object source) {
        super(source, 10);
        this.title = "";
        this.explaination = "";
    }
    
    public ExplainationEvent(final Object source, final String title, final String explaination) {
        super(source, 10);
        this.title = "";
        this.explaination = "";
        this.title = title;
        this.explaination = explaination;
    }
    
    public String getExplaination() {
        return this.explaination;
    }
    
    public String getTitle() {
        return this.title;
    }
}
