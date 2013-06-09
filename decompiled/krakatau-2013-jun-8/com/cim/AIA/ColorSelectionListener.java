package com.cim.AIA;

abstract public interface ColorSelectionListener implements com.cim.AIA.ConfigurationSelectionListener {
    abstract public void processColorSelection(com.cim.AIA.ColorSelection arg);
    
    
    abstract public void repaint();
}