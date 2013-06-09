package com.cim.AIA;

public interface ColorSelectionListener extends ConfigurationSelectionListener
{
    void processColorSelection(final ColorSelection p0);
    
    void repaint();
}
