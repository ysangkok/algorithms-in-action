import java.awt.geom.*;
import java.awt.*;
import java.util.*;
import com.cim.AIA.*;

public class MyElementArray implements Drawable, Selectable, ObjectContainer
{
    public static final int DEFAULT_COLUM_WIDTH = 10;
    public static final int DEFAULT_COLUM_GAP = 10;
    protected int columGap;
    protected int sequenceNumber;
    protected int elementWidth;
    protected boolean allHaveSameWidth;
    protected Vector<E> elements;
    private Vector<E> elemTintMarks;
    protected Point location;
    private String markString;
    private int markIndex;
    private int compareMarkIndex;
    private Color compareColor;
    private int insetRectangleLeftIndex;
    private int insetRectangleRightIndex;
    private boolean insetRectangleSet;
    
    public void setInsetRectangle(final int left, final int right) {
        this.insetRectangleLeftIndex = Math.max(0, left);
        this.insetRectangleRightIndex = Math.min(right, this.elements.size() - 1);
        this.insetRectangleSet = true;
    }
    
    public boolean isInsetRectangleSet() {
        return this.insetRectangleSet;
    }
    
    public void clearInsetRectangle() {
        this.insetRectangleSet = false;
    }
    
    public MyElementArray(final int xBuffer, final int yAxis) {
        super();
        this.columGap = 10;
        this.sequenceNumber = 1;
        this.elementWidth = 10;
        this.allHaveSameWidth = true;
        this.markIndex = -1;
        this.compareMarkIndex = -1;
        this.insetRectangleLeftIndex = -1;
        this.insetRectangleRightIndex = -1;
        this.insetRectangleSet = false;
        this.location = new Point(xBuffer, yAxis);
        this.elements = new Vector();
        this.elemTintMarks = new Vector();
        this.elemTintMarks.setSize(100);
    }
    
    public void setLocation(final int xBuffer, final int yAxis) {
        this.location.x = xBuffer;
        this.location.y = yAxis;
        if (this.allHaveSameWidth) {
            for (int id = 0; id < this.elements.size(); ++id) {
                final Element element = (Element)((Element)this.elements.elementAt(id));
                if (element != null) {
                    element.setPosition(new Point(this.location.x + id * (this.columGap + this.elementWidth), this.location.y));
                }
            }
        }
        else {
            int width = 0;
            for (int id2 = 0; id2 < this.elements.size(); ++id2) {
                final Element element2 = (Element)((Element)this.elements.elementAt(id2));
                if (element2 != null) {
                    element2.setPosition(new Point(this.location.x + id2 * this.columGap + width, this.location.y));
                    width = width + element2.getWidth();
                }
            }
        }
    }
    
    public Point getLocation() {
        return this.location;
    }
    
    public int getColumGap() {
        return this.columGap;
    }
    
    public void setColumGap(final int columGap) {
        this.columGap = columGap;
    }
    
    public int getElementWidth() {
        return this.elementWidth;
    }
    
    public void setSequence(final int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }
    
    public int getIdentifier() {
        return this.sequenceNumber;
    }
    
    public boolean equals(final Selectable selectable) {
        if (!(selectable instanceof MyElementArray)) {
            return false;
        }
        final MyElementArray elementArray = (MyElementArray)selectable;
        if (elementArray.getIdentifier() != this.getIdentifier()) {
            return false;
        }
        if (this.elements.size() != elementArray.elements.size()) {
            return false;
        }
        for (int i = 0; i < this.elements.size(); ++i) {
            final Element one = (Element)((Element)this.elements.elementAt(i));
            final Element two = (Element)((Element)elementArray.elements.elementAt(i));
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
    
    public void setElementWidth(final int elementWidth) {
        this.elementWidth = elementWidth;
        for (int i = 0; i < this.elements.size(); ++i) {
            if (!this.isEmpty(i)) {
                final Element element = (Element)((Element)this.elements.elementAt(i));
                element.setWidth(elementWidth);
            }
        }
    }
    
    public int getSize() {
        return this.elements.size();
    }
    
    public int getHeight() {
        int maxHeight = 0;
        for (int i = 0; i < this.elements.size(); ++i) {
            final Element element = (Element)((Element)this.elements.elementAt(i));
            if (element != null && element.getHeight() > maxHeight) {
                maxHeight = element.getHeight();
            }
        }
        return maxHeight;
    }
    
    public int getWidth() {
        if (this.allHaveSameWidth) {
            return this.elements.size() * this.elementWidth + (this.elements.size() - 1) * this.columGap;
        }
        int width = 0;
        for (int i = 0; i < this.elements.size(); ++i) {
            final Element element = (Element)((Element)this.elements.elementAt(i));
            width = width + element.getWidth();
        }
        return width + (this.elements.size() - 1) * this.columGap;
    }
    
    public Element getElement(final int id) {
        if (id >= 0 && id < this.elements.size()) {
            return (Element)((Element)this.elements.elementAt(id));
        }
        return null;
    }
    
    public boolean isEmpty(final int id) {
        return (Element)((Element)this.elements.elementAt(id)) == null;
    }
    
    public void add(final Object object, final int position) {
        if (object instanceof Element) {
            this.setValue(position, (Element)object);
        }
    }
    
    public void remove(final Object object) {
        if (object instanceof Element) {
            this.removeElement((Element)object);
        }
    }
    
    public void removeElement(final int id) {
        if (id >= 0 && id < this.elements.size()) {
            this.elements.removeElementAt(id);
        }
    }
    
    public void removeElement(final Element element) {
        if (this.elements.contains(element)) {
            this.elements.removeElement(element);
        }
    }
    
    public void setElemTintMark(final int d, final Color c) {
        this.elemTintMarks.insertElementAt(c, d);
    }
    
    public Color getTintForIndex(final int d) {
        if (this.elemTintMarks != null && d < this.elemTintMarks.size()) {
            return (Color)this.elemTintMarks.get(d);
        }
        return null;
    }
    
    public void clearElemTintMarks() {
        if (this.elemTintMarks != null) {
            this.elemTintMarks.clear();
        }
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
    
    public void setAllHaveSameWidth(final boolean state) {
        this.allHaveSameWidth = state;
        if (state) {
            for (int i = 0; i < this.elements.size(); ++i) {
                if (!this.isEmpty(i)) {
                    final Element element = (Element)((Element)this.elements.elementAt(i));
                    element.setWidth(this.elementWidth);
                }
            }
        }
    }
    
    public void draw(final Graphics g, final Point p) {
        this.setLocation(p.x, p.y);
        this.draw(g);
    }
    
    public void setCompareMark(final int index, final Color c) {
        this.compareMarkIndex = index;
        this.compareColor = c;
    }
    
    public void clearCompareMarks() {
        this.compareMarkIndex = -1;
    }
    
    public void setMark(final String mark, final int index) {
        this.markString = mark;
        this.markIndex = index;
    }
    
    public void clearMarks() {
        this.markIndex = -1;
    }
    
    public void draw(final Graphics g) {
        final int _w = (this.columGap + this.elementWidth) * this.elements.size();
        final Rectangle r = new Rectangle(this.location.x - this.columGap / 2, this.location.y + 5, _w, 20);
        ((Graphics2D)g).draw(r);
        if (this.isInsetRectangleSet()) {
            final int insetRectX = this.location.x - this.columGap / 2 + this.insetRectangleLeftIndex * (this.columGap + this.elementWidth);
            final int insetRectY = this.location.y + 5;
            final int insetRectWidth = (this.insetRectangleRightIndex - this.insetRectangleLeftIndex + 1) * (this.columGap + this.elementWidth);
            final int insetRectHeight = 20;
            final Rectangle insetR = new Rectangle(insetRectX, insetRectY, insetRectWidth, insetRectHeight);
            final Stroke s = ((Graphics2D)g).getStroke();
            final Color prev = g.getColor();
            g.setColor(Color.BLUE);
            ((Graphics2D)g).setStroke(new BasicStroke(2.0f));
            ((Graphics2D)g).draw(insetR);
            ((Graphics2D)g).setStroke(s);
            g.setColor(prev);
        }
        for (int i = 0; i < this.elements.size(); ++i) {
            final Rectangle elem = new Rectangle(this.location.x - this.columGap / 2 + i * (this.columGap + this.elementWidth), this.location.y + 5, this.columGap + this.elementWidth, 20);
            final Color prev2 = g.getColor();
            if (this.markIndex == i) {
                final Ellipse2D.Double c = new Ellipse2D.Double((double)elem.x + 3.0, (double)(elem.y + elem.height + 2), (double)this.columGap + (double)this.elementWidth - 6.0, (double)this.columGap + (double)this.elementWidth - 6.0);
                g.setColor(Color.RED);
                ((Graphics2D)g).fill(c);
                g.setColor(prev2);
            }
            final Color c2 = this.getTintForIndex(i);
            if (c2 != null) {
                g.setColor(c2);
                ((Graphics2D)g).fill(elem);
                g.setColor(prev2);
            }
            if (this.compareMarkIndex == i) {
                g.setColor(this.compareColor);
                ((Graphics2D)g).fill(elem);
                g.setColor(prev2);
            }
            else {
                ((Graphics2D)g).draw(elem);
            }
        }
        for (int i = 0; i < this.elements.size(); ++i) {
            if (!this.isEmpty(i)) {
                final MyVertBar currBar = (MyVertBar)((MyVertBar)this.elements.elementAt(i));
                if (!currBar.isEmpty()) {
                    currBar.draw(g);
                }
            }
        }
    }
    
    public void highlight() {
        for (int i = 0; i < this.elements.size(); ++i) {
            ((Element)((Element)this.elements.elementAt(i))).highlight();
        }
    }
    
    public void unHighlight() {
        for (int i = 0; i < this.elements.size(); ++i) {
            ((Element)((Element)this.elements.elementAt(i))).unHighlight();
        }
    }
    
    public Selectable getItemAt(final Point point) {
        for (int i = 0; i < this.elements.size(); ++i) {
            final Element element = (Element)((Element)this.elements.elementAt(i));
            if (element != null && element.getItemAt(point) != null) {
                return element;
            }
        }
        return null;
    }
}
