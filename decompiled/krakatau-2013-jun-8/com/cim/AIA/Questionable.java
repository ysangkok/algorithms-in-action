package com.cim.AIA;

abstract public interface Questionable {
    abstract public void addSelectable(com.cim.AIA.Selectable arg);
    
    
    abstract public void addSelectionListener(com.cim.AIA.SelectionListener arg);
    
    
    abstract public void removeAllHighlight();
    
    
    abstract public void removeSelectable(com.cim.AIA.Selectable arg);
    
    
    abstract public void removeSelectionListener(com.cim.AIA.SelectionListener arg);
}