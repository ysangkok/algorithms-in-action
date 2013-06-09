package com.cim.AIA;

abstract public interface Paintable {
    abstract public void addDrawable(com.cim.AIA.Drawable arg);
    
    
    abstract public void paint();
    
    
    abstract public void removeDrawable(com.cim.AIA.Drawable arg);
}