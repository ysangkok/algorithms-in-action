import java.awt.*;
import java.util.*;
import com.cim.AIA.*;

public class MyElementArrayShell implements Drawable, Selectable, ObjectContainer
{
    public static final int DEFAULT_COLUM_WIDTH = 10;
    public static final int DEFAULT_COLUM_GAP = 10;
    protected int columGap;
    protected int sequenceNumber;
    protected int elementWidth;
    protected boolean allHaveSameWidth;
    protected Vector<Element> elements;
    protected Point location;
    
    public MyElementArrayShell(final int xBuffer, final int yAxis) {
        super();
        this.columGap = 10;
        this.sequenceNumber = 1;
        this.elementWidth = 10;
        this.allHaveSameWidth = true;
        this.location = new Point(xBuffer, yAxis);
        this.elements = new Vector();
    }
    
    public void add(final Object object, final int position) {
        if (object instanceof Element) {
            this.setValue(position, (Element)object);
        }
    }
    
    public void draw(final Graphics g) {
        for (int i = 0; i < this.elements.size(); ++i) {
            if (!this.isEmpty(i)) {
                ((Element)this.elements.elementAt(i)).draw(g);
            }
        }
    }
    
    public void draw(final Graphics g, final Point p) {
        this.setLocation(p.x, p.y);
        this.draw(g);
    }
    
    public boolean equals(final Selectable selectable) {
        if (!(selectable instanceof MyElementArrayShell)) {
            return false;
        }
        final MyElementArrayShell elementArray = (MyElementArrayShell)selectable;
        if (elementArray.getIdentifier() != this.getIdentifier()) {
            return false;
        }
        if (this.elements.size() != elementArray.elements.size()) {
            return false;
        }
        for (int i = 0; i < this.elements.size(); ++i) {
            final Element one = (Element)this.elements.elementAt(i);
            final Element two = (Element)elementArray.elements.elementAt(i);
            if (one != null && two != null) {
                if (!one.equals(two)) {
                    return false;
                }
            }
            else if (one != null || two != null) {
                return false;
            }
        }
        return true;
    }
    
    public int getColumGap() {
        return this.columGap;
    }
    
    public Element getElement(final int id) {
        if (id >= 0 && id < this.elements.size()) {
            return (Element)this.elements.elementAt(id);
        }
        return null;
    }
    
    public int getElementWidth() {
        return this.elementWidth;
    }
    
    public int getHeight() {
        int maxHeight = 0;
        for (int i = 0; i < this.elements.size(); ++i) {
            final Element element = (Element)this.elements.elementAt(i);
            if (element != null && element.getHeight() > maxHeight) {
                maxHeight = element.getHeight();
            }
        }
        return maxHeight;
    }
    
    public int getIdentifier() {
        return this.sequenceNumber;
    }
    
    public Selectable getItemAt(final Point point) {
        for (int i = 0; i < this.elements.size(); ++i) {
            final Element element = (Element)this.elements.elementAt(i);
            if (element != null && element.getItemAt(point) != null) {
                return element;
            }
        }
        return null;
    }
    
    public Point getLocation() {
        return this.location;
    }
    
    public int getSize() {
        return this.elements.size();
    }
    
    public int getWidth() {
        if (this.allHaveSameWidth) {
            return this.elements.size() * this.elementWidth + (this.elements.size() - 1) * this.columGap;
        }
        int width = 0;
        for (int i = 0; i < this.elements.size(); ++i) {
            final Element element = (Element)this.elements.elementAt(i);
            width = width + element.getWidth();
        }
        return width + (this.elements.size() - 1) * this.columGap;
    }
    
    public void highlight() {
        for (int i = 0; i < this.elements.size(); ++i) {
            ((Element)this.elements.elementAt(i)).highlight();
        }
    }
    
    public boolean isEmpty(final int id) {
        return this.elements.elementAt(id) == null;
    }
    
    public void remove(final Object object) {
        if (object instanceof Element) {
            this.removeElement((Element)object);
        }
    }
    
    public void removeElement(final Element element) {
        if (this.elements.contains(element)) {
            this.elements.removeElement(element);
        }
    }
    
    public void removeElement(final int id) {
        if (id >= 0 && id < this.elements.size()) {
            this.elements.removeElementAt(id);
        }
    }
    
    public void setAllHaveSameWidth(final boolean state) {
        this.allHaveSameWidth = state;
        if (state) {
            for (int i = 0; i < this.elements.size(); ++i) {
                if (!this.isEmpty(i)) {
                    final Element element = (Element)this.elements.elementAt(i);
                    element.setWidth(this.elementWidth);
                }
            }
        }
    }
    
    public void setColumGap(final int columGap) {
        this.columGap = columGap;
    }
    
    public void setElementWidth(final int elementWidth) {
        this.elementWidth = elementWidth;
        for (int i = 0; i < this.elements.size(); ++i) {
            if (!this.isEmpty(i)) {
                final Element element = (Element)this.elements.elementAt(i);
                element.setWidth(elementWidth);
            }
        }
    }
    
    public void setLocation(final int xBuffer, final int yAxis) {
        this.location.x = xBuffer;
        this.location.y = yAxis;
        if (this.allHaveSameWidth) {
            for (int id = 0; id < this.elements.size(); ++id) {
                final Element element = (Element)this.elements.elementAt(id);
                if (element != null) {
                    element.setPosition(new Point(this.location.x + id * (this.columGap + this.elementWidth), this.location.y));
                }
            }
        }
        else {
            int width = 0;
            for (int id2 = 0; id2 < this.elements.size(); ++id2) {
                final Element element2 = (Element)this.elements.elementAt(id2);
                if (element2 != null) {
                    element2.setPosition(new Point(this.location.x + id2 * this.columGap + width, this.location.y));
                    width = width + element2.getWidth();
                }
            }
        }
    }
    
    public void setSequence(final int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }
    
    public void setValue(final int id, final Element element) {
        if (id >= 0) {
            this.elements.insertElementAt(element, id);
            element.setPosition(new Point(this.location.x + id * (this.columGap + this.elementWidth), this.location.y));
            if (this.allHaveSameWidth) {
                element.setWidth(this.elementWidth);
            }
        }
    }
    
    public void unHighlight() {
        for (int i = 0; i < this.elements.size(); ++i) {
            ((Element)this.elements.elementAt(i)).unHighlight();
        }
    }
}
