package com.cim.AIA;

import java.awt.*;

public interface Selectable
{
    boolean equals(final Selectable p0);
    
    int getIdentifier();
    
    Selectable getItemAt(final Point p0);
    
    void highlight();
    
    void unHighlight();
}
