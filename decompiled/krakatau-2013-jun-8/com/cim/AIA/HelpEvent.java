package com.cim.AIA;

public class HelpEvent extends java.awt.AWTEvent {
    final private static long serialVersionUID = 6360812080089190584L;
    protected String topic;
    protected String instructions;
    
    public HelpEvent(Object a, String s, String s0)
    {
        super(a, 10);
        this.topic = "";
        this.instructions = "";
        this.topic = s;
        this.instructions = s0;
    }
    
    public String getInstructions()
    {
        String s = this.instructions;
        return s;
    }
    
    public String getTopic()
    {
        String s = this.topic;
        return s;
    }
}