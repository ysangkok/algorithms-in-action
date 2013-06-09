package com.cim.AIA;

public interface Repaintable
{
    void addRepaintListener(final RepaintListener p0);
    
    void removeRepaintListener(final RepaintListener p0);
    
    void repaint();
}
