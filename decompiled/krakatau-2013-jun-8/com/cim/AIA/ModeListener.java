package com.cim.AIA;

abstract public interface ModeListener {
    abstract public void modeNormal(com.cim.AIA.ModeEvent arg);
    
    
    abstract public void modeOther(com.cim.AIA.ModeEvent arg);
    
    
    abstract public void modeQuiz(com.cim.AIA.ModeEvent arg);
    
    
    abstract public void modeSelfTest(com.cim.AIA.ModeEvent arg);
}