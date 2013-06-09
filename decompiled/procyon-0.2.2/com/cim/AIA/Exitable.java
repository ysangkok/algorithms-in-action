package com.cim.AIA;

public interface Exitable
{
    void addExitListener(final ExitListener p0);
    
    void exit();
    
    void removeExitListener(final ExitListener p0);
}
