package com.cim.AIA;

abstract public interface Finishable {
    abstract public void addFinishListener(com.cim.AIA.FinishListener arg);
    
    
    abstract public void removeFinishListener(com.cim.AIA.FinishListener arg);
}