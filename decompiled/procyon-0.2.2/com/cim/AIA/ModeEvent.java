package com.cim.AIA;

import java.awt.*;

public class ModeEvent extends AWTEvent
{
    private static final long serialVersionUID = -8488888882434467226L;
    public static final int MODE_NORMAL = 12356;
    public static final int MODE_SELF_TEST = 12357;
    public static final int MODE_QUIZ = 12358;
    protected int type;
    
    public ModeEvent(final Object source, final int type) {
        super(source, 10);
        this.type = type;
    }
    
    public int getType() {
        return this.type;
    }
}
