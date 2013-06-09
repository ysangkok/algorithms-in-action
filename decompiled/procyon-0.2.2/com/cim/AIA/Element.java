package com.cim.AIA;

import java.awt.*;
import java.util.*;

public abstract class Element implements Drawable, Moveable, Selectable, Sizeable
{
    protected Vector<StringMarker> markers;
    protected int sequenceNumber;
    protected Object value;
    
    public Element(final int sequenceNumber, final Object value) {
        super();
        this.markers = new Vector();
        this.sequenceNumber = 1;
        this.value = value;
        this.sequenceNumber = sequenceNumber;
    }
    
    public void addMarker(final StringMarker marker) {
        this.markers.addElement(marker);
    }
    
    public int getIdentifier() {
        return this.sequenceNumber;
    }
    
    public Object getObject() {
        return this.value;
    }
    
    public abstract Point getPosition();
    
    public void removeAllMarkers() {
        this.markers.removeAllElements();
    }
    
    public void removeMarker(final StringMarker marker) {
        this.markers.removeElement(marker);
    }
    
    public void setObject(final Object value) {
        this.value = value;
    }
    
    public abstract void setPosition(final Point p0);
    
    public void setSequence(final int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }
}
