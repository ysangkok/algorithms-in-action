package com.cim.AIA;

import java.awt.*;

public class ControlEvent extends AWTEvent
{
    private static final long serialVersionUID = 7071495172140196245L;
    public static final int STEP_EVENT = 123123;
    public static final int BACK_EVENT = 123124;
    public static final int PAUSE_EVENT = 123125;
    public static final int RESET_EVENT = 123126;
    public static final int RUN_EVENT = 123127;
    public static final int RESTART_EVENT = 123128;
    protected int type;
    
    public ControlEvent(final Object source, final int type) {
        super(source, 10);
        this.type = type;
    }
    
    public int getType() {
        return this.type;
    }
}
