package com.cim.AIA;

public class ControlEvent extends java.awt.AWTEvent {
    final private static long serialVersionUID = 7071495172140196245L;
    final public static int STEP_EVENT = 123123;
    final public static int BACK_EVENT = 123124;
    final public static int PAUSE_EVENT = 123125;
    final public static int RESET_EVENT = 123126;
    final public static int RUN_EVENT = 123127;
    final public static int RESTART_EVENT = 123128;
    protected int type;
    
    public ControlEvent(Object a, int i)
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