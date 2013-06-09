package com.cim.AIA;

import java.awt.event.*;

public abstract class ControlButton extends HelpableButton implements ControlListener
{
    protected Controlable controlable;
    protected AlgorithmThread thread;
    
    public ControlButton(final String name, final String instructions, final Controlable controlable, final AlgorithmThread algorithmThread) {
        super(name, instructions);
        this.controlable = controlable;
        this.thread = algorithmThread;
        this.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                ControlButton.this.activate();
            }
        });
    }
    
    protected void activate() {
        this.controlable.informControlListeners(this.getControlEvent());
        this.processThread();
    }
    
    protected abstract ControlEvent getControlEvent();
    
    public Logger getLogger() {
        return this.thread.getLogger();
    }
    
    protected abstract void processThread();
}
