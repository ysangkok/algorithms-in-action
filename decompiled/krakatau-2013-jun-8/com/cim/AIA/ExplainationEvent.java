package com.cim.AIA;

public class ExplainationEvent extends java.awt.AWTEvent {
    final private static long serialVersionUID = 8058754301963600182L;
    protected String title;
    protected String explaination;
    
    public ExplainationEvent(Object a)
    {
        super(a, 10);
        this.title = "";
        this.explaination = "";
    }
    
    public ExplainationEvent(Object a, String s, String s0)
    {
        super(a, 10);
        this.title = "";
        this.explaination = "";
        this.title = s;
        this.explaination = s0;
    }
    
    public String getExplaination()
    {
        String s = this.explaination;
        return s;
    }
    
    public String getTitle()
    {
        String s = this.title;
        return s;
    }
}