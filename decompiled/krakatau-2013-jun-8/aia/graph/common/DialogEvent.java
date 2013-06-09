package aia.graph.common;

public class DialogEvent extends java.awt.Event {
    final private static long serialVersionUID = -6900256021942182817L;
    private String m_value;
    
    DialogEvent(Object a, int i, Object a0)
    {
        super(a, i, a0);
        this.m_value = null;
        String dummy = (String)a0;
        String s = (String)a0;
        this.m_value = s;
    }
    
    public String getValue()
    {
        String s = this.m_value;
        return s;
    }
}