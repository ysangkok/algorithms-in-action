package com.cim.AIA;

abstract public class ControlButton extends com.cim.AIA.HelpableButton implements com.cim.AIA.ControlListener {
    protected com.cim.AIA.Controlable controlable;
    protected com.cim.AIA.AlgorithmThread thread;
    
    public ControlButton(String s, String s0, com.cim.AIA.Controlable a, com.cim.AIA.AlgorithmThread a0)
    {
        super(s, s0);
        Object a1 = a;
        this.controlable = (com.cim.AIA.Controlable)a1;
        this.thread = a0;
        com.cim.AIA.ControlButton$1 a2 = new com.cim.AIA.ControlButton$1(this);
        this.addActionListener((java.awt.event.ActionListener)a2);
    }
    
    protected void activate()
    {
        Object a = this.controlable;
        com.cim.AIA.ControlEvent a0 = this.getControlEvent();
        ((com.cim.AIA.Controlable)a).informControlListeners(a0);
        this.processThread();
    }
    
    abstract protected com.cim.AIA.ControlEvent getControlEvent();
    
    
    public com.cim.AIA.Logger getLogger()
    {
        com.cim.AIA.AlgorithmThread a = this.thread;
        com.cim.AIA.Logger a0 = a.getLogger();
        return a0;
    }
    
    abstract protected void processThread();
}