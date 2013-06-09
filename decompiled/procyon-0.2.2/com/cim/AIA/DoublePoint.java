package com.cim.AIA;

public class DoublePoint
{
    public double x;
    public double y;
    
    public DoublePoint(final double x, final double y) {
        super();
        this.x = x;
        this.y = y;
    }
    
    public String toString() {
        return new String("com.cim.AIA.DoublePoint: x = " + this.x + " y = " + this.y);
    }
}
