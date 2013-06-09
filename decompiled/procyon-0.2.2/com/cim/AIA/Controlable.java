package com.cim.AIA;

public interface Controlable
{
    void addControlListener(final ControlListener p0);
    
    void informControlListeners(final ControlEvent p0);
    
    void removeControlListener(final ControlListener p0);
}
