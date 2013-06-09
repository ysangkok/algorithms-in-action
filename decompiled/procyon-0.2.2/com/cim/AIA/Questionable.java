package com.cim.AIA;

public interface Questionable
{
    void addSelectable(final Selectable p0);
    
    void addSelectionListener(final SelectionListener p0);
    
    void removeAllHighlight();
    
    void removeSelectable(final Selectable p0);
    
    void removeSelectionListener(final SelectionListener p0);
}
