package com.cim.AIA;

import java.awt.event.*;
import java.awt.*;

public abstract class ModeSelection extends CheckboxMenuItem implements ModeListener
{
    protected Modeable modeable;
    
    public ModeSelection(final String name, final boolean state, final Modeable modeable) {
        super(name, state);
        this.modeable = modeable;
        this.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent e) {
                ModeSelection.this.activate();
            }
        });
    }
    
    protected void activate() {
        this.modeable.informModeListeners(this.getModeEvent());
        this.initialiseMode();
    }
    
    protected abstract ModeEvent getModeEvent();
    
    protected abstract void initialiseMode();
}
