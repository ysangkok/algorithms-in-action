package com.cim.AIA;

public interface Moveable
{
    int getX();
    
    int getY();
    
    void setX(final int p0);
    
    void setY(final int p0);
    
    void shiftEntire(final int p0, final int p1);
}
