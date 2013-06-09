package com.cim.AIA;

public interface ControlListener
{
    void controlBack(final ControlEvent p0);
    
    void controlOther(final ControlEvent p0);
    
    void controlPause(final ControlEvent p0);
    
    void controlReset(final ControlEvent p0);
    
    void controlRestart(final ControlEvent p0);
    
    void controlRun(final ControlEvent p0);
    
    void controlStep(final ControlEvent p0);
}
