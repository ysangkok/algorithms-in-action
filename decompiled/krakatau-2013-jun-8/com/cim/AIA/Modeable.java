package com.cim.AIA;

abstract public interface Modeable {
    abstract public void addModeListener(com.cim.AIA.ModeListener arg);
    
    
    abstract public void informModeListeners(com.cim.AIA.ModeEvent arg);
    
    
    abstract public void removeModeListener(com.cim.AIA.ModeListener arg);
}