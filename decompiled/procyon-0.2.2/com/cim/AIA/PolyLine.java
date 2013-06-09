package com.cim.AIA;

public class PolyLine
{
    public int dx;
    public int dy;
    public PolyLine link;
    
    public PolyLine(final int dx, final int dy, final PolyLine link) {
        super();
        this.dx = dx;
        this.dy = dy;
        this.link = link;
    }
}
