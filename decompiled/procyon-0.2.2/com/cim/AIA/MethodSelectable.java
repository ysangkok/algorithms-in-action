package com.cim.AIA;

public interface MethodSelectable
{
    void addMethodSelection(final MethodSelection p0, final boolean p1);
    
    void addMethodSelectionListener(final MethodSelectionListener p0);
    
    void removeMethodSelection(final MethodSelection p0);
    
    void removeMethodSelectionListener(final MethodSelectionListener p0);
}
