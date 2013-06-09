package com.cim.AIA;

abstract public interface Selectable {
    abstract public boolean equals(com.cim.AIA.Selectable arg);
    
    
    abstract public int getIdentifier();
    
    
    abstract public com.cim.AIA.Selectable getItemAt(java.awt.Point arg);
    
    
    abstract public void highlight();
    
    
    abstract public void unHighlight();
}