package com.cim.AIA;

public interface Modeable
{
    void addModeListener(final ModeListener p0);
    
    void informModeListeners(final ModeEvent p0);
    
    void removeModeListener(final ModeListener p0);
}
