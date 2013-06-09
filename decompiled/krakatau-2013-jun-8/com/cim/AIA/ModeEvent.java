package com.cim.AIA;

public class ModeEvent extends java.awt.AWTEvent {
    final private static long serialVersionUID = -8488888882434467226L;
    final public static int MODE_NORMAL = 12356;
    final public static int MODE_SELF_TEST = 12357;
    final public static int MODE_QUIZ = 12358;
    protected int type;
    
    public ModeEvent(Object a, int i)
    {
        super(a, 10);
        this.type = i;
    }
    
    public int getType()
    {
        int i = this.type;
        return i;
    }
}