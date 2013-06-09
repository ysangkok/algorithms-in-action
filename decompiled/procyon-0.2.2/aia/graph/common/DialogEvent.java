package aia.graph.common;

import java.awt.*;

public class DialogEvent extends Event
{
    private static final long serialVersionUID = -6900256021942182817L;
    private String m_value;
    
    DialogEvent(final Object obj, final int id, final Object arg) {
        super(obj, id, arg);
        this.m_value = null;
        this.m_value = (String)arg;
    }
    
    public String getValue() {
        return this.m_value;
    }
}
