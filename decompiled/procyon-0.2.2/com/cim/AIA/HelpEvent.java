package com.cim.AIA;

import java.awt.*;

public class HelpEvent extends AWTEvent
{
    private static final long serialVersionUID = 6360812080089190584L;
    protected String topic;
    protected String instructions;
    
    public HelpEvent(final Object source, final String topic, final String instructions) {
        super(source, 10);
        this.topic = "";
        this.instructions = "";
        this.topic = topic;
        this.instructions = instructions;
    }
    
    public String getInstructions() {
        return this.instructions;
    }
    
    public String getTopic() {
        return this.topic;
    }
}
