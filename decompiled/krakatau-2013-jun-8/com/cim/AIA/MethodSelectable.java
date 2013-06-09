package com.cim.AIA;

abstract public interface MethodSelectable {
    abstract public void addMethodSelection(com.cim.AIA.MethodSelection arg, boolean arg0);
    
    
    abstract public void addMethodSelectionListener(com.cim.AIA.MethodSelectionListener arg);
    
    
    abstract public void removeMethodSelection(com.cim.AIA.MethodSelection arg);
    
    
    abstract public void removeMethodSelectionListener(com.cim.AIA.MethodSelectionListener arg);
}