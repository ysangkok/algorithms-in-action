package com.cim.AIA;

abstract public interface FontSelectionListener implements com.cim.AIA.ConfigurationSelectionListener {
    abstract public void processFontSelection(com.cim.AIA.FontSelection arg);
    
    
    abstract public void repaint();
}