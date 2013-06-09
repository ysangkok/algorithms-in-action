package com.cim.AIA;

abstract public class ModeSelection extends java.awt.CheckboxMenuItem implements com.cim.AIA.ModeListener {
    protected com.cim.AIA.Modeable modeable;
    
    public ModeSelection(String s, boolean b, com.cim.AIA.Modeable a)
    {
        super(s, b);
        Object a0 = a;
        this.modeable = (com.cim.AIA.Modeable)a0;
        com.cim.AIA.ModeSelection$1 a1 = new com.cim.AIA.ModeSelection$1(this);
        this.addItemListener((java.awt.event.ItemListener)a1);
    }
    
    protected void activate()
    {
        Object a = this.modeable;
        com.cim.AIA.ModeEvent a0 = this.getModeEvent();
        ((com.cim.AIA.Modeable)a).informModeListeners(a0);
        this.initialiseMode();
    }
    
    abstract protected com.cim.AIA.ModeEvent getModeEvent();
    
    
    abstract protected void initialiseMode();
}