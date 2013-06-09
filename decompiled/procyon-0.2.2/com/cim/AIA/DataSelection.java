package com.cim.AIA;

import java.awt.event.*;
import java.awt.*;

public abstract class DataSelection extends CheckboxMenuItem
{
    protected AnimationWindow animationWindow;
    protected boolean reInitialiseAlgorithm;
    protected boolean clearAlgorithmState;
    
    public DataSelection(final String name, final boolean state, final AnimationWindow animWindow) {
        super(name, state);
        this.reInitialiseAlgorithm = true;
        this.clearAlgorithmState = true;
        this.animationWindow = animWindow;
        this.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent e) {
                DataSelection.this.animationWindow.resetDataSelectionCheckBoxes();
                final Copyable temp = DataSelection.this.getData();
                if (!temp.isEmpty()) {
                    DataSelection.this.setState(true);
                    DataSelection.this.animationWindow.setCurrentData(temp, DataSelection.this.reInitialiseAlgorithm, DataSelection.this.clearAlgorithmState);
                }
                String cmd = e.paramString();
                final int i1 = cmd.indexOf("item=") + 5;
                final int i2 = cmd.indexOf(",", i1);
                cmd = cmd.substring(i1, i2);
                final Log l1 = new Log(4, 9, null, name);
                if (DataSelection.this.animationWindow.getThread().getLogger() != null) {
                    DataSelection.this.animationWindow.getThread().getLogger().addLog(l1);
                }
                if (Logger.DEBUG) {
                    System.err.println("Logging data selection item=" + cmd);
                }
            }
        });
    }
    
    public abstract Copyable getData();
    
    public void setClearAlgorithmState(final boolean clearAlgorithmState) {
        this.clearAlgorithmState = clearAlgorithmState;
    }
    
    public void setReinitialiseAlgorithm(final boolean reInitialiseAlgorithm) {
        this.reInitialiseAlgorithm = reInitialiseAlgorithm;
    }
}
