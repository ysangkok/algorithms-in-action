package com.cim.AIA;

public interface FontSelectionListener extends ConfigurationSelectionListener
{
    void processFontSelection(final FontSelection p0);
    
    void repaint();
}
