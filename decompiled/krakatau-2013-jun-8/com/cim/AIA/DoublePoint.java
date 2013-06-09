package com.cim.AIA;

public class DoublePoint {
    public double x;
    public double y;
    
    public DoublePoint(double d, double d0)
    {
        super();
        this.x = d;
        this.y = d0;
    }
    
    public String toString()
    {
        StringBuilder a = new StringBuilder();
        StringBuilder a0 = a.append("com.cim.AIA.DoublePoint: x = ");
        double d = this.x;
        StringBuilder a1 = a0.append(d);
        StringBuilder a2 = a1.append(" y = ");
        double d0 = this.y;
        StringBuilder a3 = a2.append(d0);
        String s = a3.toString();
        String s0 = new String(s);
        return s0;
    }
}