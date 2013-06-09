package com.cim.AIA;

import java.io.*;

public class Polygon implements Serializable
{
    private static final long serialVersionUID = -6017181366775444717L;
    public PolyLine lower_head;
    public PolyLine lower_tail;
    public PolyLine upper_head;
    public PolyLine upper_tail;
}
