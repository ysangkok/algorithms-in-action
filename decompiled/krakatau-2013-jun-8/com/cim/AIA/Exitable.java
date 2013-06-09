package com.cim.AIA;

abstract public interface Exitable {
    abstract public void addExitListener(com.cim.AIA.ExitListener arg);
    
    
    abstract public void exit();
    
    
    abstract public void removeExitListener(com.cim.AIA.ExitListener arg);
}