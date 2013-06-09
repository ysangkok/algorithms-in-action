package com.cim.AIA;

abstract public interface Controlable {
    abstract public void addControlListener(com.cim.AIA.ControlListener arg);
    
    
    abstract public void informControlListeners(com.cim.AIA.ControlEvent arg);
    
    
    abstract public void removeControlListener(com.cim.AIA.ControlListener arg);
}