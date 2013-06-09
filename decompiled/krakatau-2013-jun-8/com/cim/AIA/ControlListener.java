package com.cim.AIA;

abstract public interface ControlListener {
    abstract public void controlBack(com.cim.AIA.ControlEvent arg);
    
    
    abstract public void controlOther(com.cim.AIA.ControlEvent arg);
    
    
    abstract public void controlPause(com.cim.AIA.ControlEvent arg);
    
    
    abstract public void controlReset(com.cim.AIA.ControlEvent arg);
    
    
    abstract public void controlRestart(com.cim.AIA.ControlEvent arg);
    
    
    abstract public void controlRun(com.cim.AIA.ControlEvent arg);
    
    
    abstract public void controlStep(com.cim.AIA.ControlEvent arg);
}