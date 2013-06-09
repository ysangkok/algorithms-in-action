package com.cim.AIA;

abstract public class Element implements com.cim.AIA.Drawable, com.cim.AIA.Moveable, com.cim.AIA.Selectable, com.cim.AIA.Sizeable {
    protected java.util.Vector markers;
    protected int sequenceNumber;
    protected Object value;
    
    public Element(int i, Object a)
    {
        super();
        java.util.Vector a0 = new java.util.Vector();
        this.markers = a0;
        this.sequenceNumber = 1;
        this.value = a;
        this.sequenceNumber = i;
    }
    
    public void addMarker(com.cim.AIA.StringMarker a)
    {
        java.util.Vector a0 = this.markers;
        a0.addElement((Object)a);
    }
    
    public int getIdentifier()
    {
        int i = this.sequenceNumber;
        return i;
    }
    
    public Object getObject()
    {
        Object a = this.value;
        return a;
    }
    
    abstract public java.awt.Point getPosition();
    
    
    public void removeAllMarkers()
    {
        java.util.Vector a = this.markers;
        a.removeAllElements();
    }
    
    public void removeMarker(com.cim.AIA.StringMarker a)
    {
        java.util.Vector a0 = this.markers;
        int i = a0.removeElement((Object)a)?1:0;
    }
    
    public void setObject(Object a)
    {
        this.value = a;
    }
    
    abstract public void setPosition(java.awt.Point arg);
    
    
    public void setSequence(int i)
    {
        this.sequenceNumber = i;
    }
}