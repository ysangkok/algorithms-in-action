package com.cim.AIA;

public class Polygon implements java.io.Serializable {
    final private static long serialVersionUID = -6017181366775444717L;
    public com.cim.AIA.PolyLine lower_head;
    public com.cim.AIA.PolyLine lower_tail;
    public com.cim.AIA.PolyLine upper_head;
    public com.cim.AIA.PolyLine upper_tail;
    
    public Polygon()
    {
        super();
    }
}