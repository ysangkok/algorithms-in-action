package com.cim.AIA;

abstract public interface Repaintable {
    abstract public void addRepaintListener(com.cim.AIA.RepaintListener arg);
    
    
    abstract public void removeRepaintListener(com.cim.AIA.RepaintListener arg);
    
    
    abstract public void repaint();
}