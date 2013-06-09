package com.cim.AIA;

public class PolyLine {
    public int dx;
    public int dy;
    public com.cim.AIA.PolyLine link;
    
    public PolyLine(int i, int i0, com.cim.AIA.PolyLine a)
    {
        super();
        this.dx = i;
        this.dy = i0;
        this.link = a;
    }
}