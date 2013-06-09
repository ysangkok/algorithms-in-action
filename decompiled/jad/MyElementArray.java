// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MyElementArray.java

import com.cim.AIA.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Vector;

public class MyElementArray
    implements Drawable, Selectable, ObjectContainer
{

    public void setInsetRectangle(int left, int right)
    {
        insetRectangleLeftIndex = Math.max(0, left);
        insetRectangleRightIndex = Math.min(right, elements.size() - 1);
        insetRectangleSet = true;
    }

    public boolean isInsetRectangleSet()
    {
        return insetRectangleSet;
    }

    public void clearInsetRectangle()
    {
        insetRectangleSet = false;
    }

    public MyElementArray(int xBuffer, int yAxis)
    {
        columGap = 10;
        sequenceNumber = 1;
        elementWidth = 10;
        allHaveSameWidth = true;
        markIndex = -1;
        compareMarkIndex = -1;
        insetRectangleLeftIndex = -1;
        insetRectangleRightIndex = -1;
        insetRectangleSet = false;
        location = new Point(xBuffer, yAxis);
        elements = new Vector();
        elemTintMarks = new Vector();
        elemTintMarks.setSize(100);
    }

    public void setLocation(int xBuffer, int yAxis)
    {
        location.x = xBuffer;
        location.y = yAxis;
        if(allHaveSameWidth)
        {
            for(int id = 0; id < elements.size(); id++)
            {
                Element element = (Element)(Element)elements.elementAt(id);
                if(element != null)
                    element.setPosition(new Point(location.x + id * (columGap + elementWidth), location.y));
            }

        } else
        {
            int width = 0;
            for(int id = 0; id < elements.size(); id++)
            {
                Element element = (Element)(Element)elements.elementAt(id);
                if(element != null)
                {
                    element.setPosition(new Point(location.x + id * columGap + width, location.y));
                    width += element.getWidth();
                }
            }

        }
    }

    public Point getLocation()
    {
        return location;
    }

    public int getColumGap()
    {
        return columGap;
    }

    public void setColumGap(int columGap)
    {
        this.columGap = columGap;
    }

    public int getElementWidth()
    {
        return elementWidth;
    }

    public void setSequence(int sequenceNumber)
    {
        this.sequenceNumber = sequenceNumber;
    }

    public int getIdentifier()
    {
        return sequenceNumber;
    }

    public boolean equals(Selectable selectable)
    {
        if(selectable instanceof MyElementArray)
        {
            MyElementArray elementArray = (MyElementArray)selectable;
            if(elementArray.getIdentifier() != getIdentifier())
                return false;
            if(elements.size() != elementArray.elements.size())
                return false;
            for(int i = 0; i < elements.size(); i++)
            {
                Element one = (Element)(Element)elements.elementAt(i);
                Element two = (Element)(Element)elementArray.elements.elementAt(i);
                if(one != null && two != null)
                {
                    if(!one.equals(two))
                        return false;
                    continue;
                }
                if(one != null || two != null)
                    return false;
            }

            return true;
        } else
        {
            return false;
        }
    }

    public void setElementWidth(int elementWidth)
    {
        this.elementWidth = elementWidth;
        for(int i = 0; i < elements.size(); i++)
            if(!isEmpty(i))
            {
                Element element = (Element)(Element)elements.elementAt(i);
                element.setWidth(elementWidth);
            }

    }

    public int getSize()
    {
        return elements.size();
    }

    public int getHeight()
    {
        int maxHeight = 0;
        for(int i = 0; i < elements.size(); i++)
        {
            Element element = (Element)(Element)elements.elementAt(i);
            if(element != null && element.getHeight() > maxHeight)
                maxHeight = element.getHeight();
        }

        return maxHeight;
    }

    public int getWidth()
    {
        if(allHaveSameWidth)
            return elements.size() * elementWidth + (elements.size() - 1) * columGap;
        int width = 0;
        for(int i = 0; i < elements.size(); i++)
        {
            Element element = (Element)(Element)elements.elementAt(i);
            width += element.getWidth();
        }

        return width + (elements.size() - 1) * columGap;
    }

    public Element getElement(int id)
    {
        if(id >= 0 && id < elements.size())
            return (Element)(Element)elements.elementAt(id);
        else
            return null;
    }

    public boolean isEmpty(int id)
    {
        return (Element)(Element)elements.elementAt(id) == null;
    }

    public void add(Object object, int position)
    {
        if(object instanceof Element)
            setValue(position, (Element)object);
    }

    public void remove(Object object)
    {
        if(object instanceof Element)
            removeElement((Element)object);
    }

    public void removeElement(int id)
    {
        if(id >= 0 && id < elements.size())
            elements.removeElementAt(id);
    }

    public void removeElement(Element element)
    {
        if(elements.contains(element))
            elements.removeElement(element);
    }

    public void setElemTintMark(int d, Color c)
    {
        elemTintMarks.insertElementAt(c, d);
    }

    public Color getTintForIndex(int d)
    {
        if(elemTintMarks != null && d < elemTintMarks.size())
            return (Color)elemTintMarks.get(d);
        else
            return null;
    }

    public void clearElemTintMarks()
    {
        if(elemTintMarks != null)
            elemTintMarks.clear();
    }

    public void setValue(int id, Element element)
    {
        if(id >= 0)
        {
            elements.insertElementAt(element, id);
            element.setPosition(new Point(location.x + id * (columGap + elementWidth), location.y));
            if(allHaveSameWidth)
                element.setWidth(elementWidth);
        }
    }

    public void setAllHaveSameWidth(boolean state)
    {
        allHaveSameWidth = state;
        if(state)
        {
            for(int i = 0; i < elements.size(); i++)
                if(!isEmpty(i))
                {
                    Element element = (Element)(Element)elements.elementAt(i);
                    element.setWidth(elementWidth);
                }

        }
    }

    public void draw(Graphics g, Point p)
    {
        setLocation(p.x, p.y);
        draw(g);
    }

    public void setCompareMark(int index, Color c)
    {
        compareMarkIndex = index;
        compareColor = c;
    }

    public void clearCompareMarks()
    {
        compareMarkIndex = -1;
    }

    public void setMark(String mark, int index)
    {
        markString = mark;
        markIndex = index;
    }

    public void clearMarks()
    {
        markIndex = -1;
    }

    public void draw(Graphics g)
    {
        int _w = (columGap + elementWidth) * elements.size();
        Rectangle r = new Rectangle(location.x - columGap / 2, location.y + 5, _w, 20);
        ((Graphics2D)g).draw(r);
        if(isInsetRectangleSet())
        {
            int insetRectX = (location.x - columGap / 2) + insetRectangleLeftIndex * (columGap + elementWidth);
            int insetRectY = location.y + 5;
            int insetRectWidth = ((insetRectangleRightIndex - insetRectangleLeftIndex) + 1) * (columGap + elementWidth);
            int insetRectHeight = 20;
            Rectangle insetR = new Rectangle(insetRectX, insetRectY, insetRectWidth, insetRectHeight);
            java.awt.Stroke s = ((Graphics2D)g).getStroke();
            Color prev = g.getColor();
            g.setColor(Color.BLUE);
            ((Graphics2D)g).setStroke(new BasicStroke(2.0F));
            ((Graphics2D)g).draw(insetR);
            ((Graphics2D)g).setStroke(s);
            g.setColor(prev);
        }
        for(int i = 0; i < elements.size(); i++)
        {
            Rectangle elem = new Rectangle((location.x - columGap / 2) + i * (columGap + elementWidth), location.y + 5, columGap + elementWidth, 20);
            Color prev = g.getColor();
            Color c;
            if(markIndex == i)
            {
                c = new java.awt.geom.Ellipse2D.Double((double)elem.x + 3D, elem.y + elem.height + 2, ((double)columGap + (double)elementWidth) - 6D, ((double)columGap + (double)elementWidth) - 6D);
                g.setColor(Color.RED);
                ((Graphics2D)g).fill(c);
                g.setColor(prev);
            }
            c = getTintForIndex(i);
            if(c != null)
            {
                g.setColor(c);
                ((Graphics2D)g).fill(elem);
                g.setColor(prev);
            }
            if(compareMarkIndex == i)
            {
                g.setColor(compareColor);
                ((Graphics2D)g).fill(elem);
                g.setColor(prev);
            } else
            {
                ((Graphics2D)g).draw(elem);
            }
        }

        for(int i = 0; i < elements.size(); i++)
        {
            if(isEmpty(i))
                continue;
            MyVertBar currBar = (MyVertBar)(MyVertBar)elements.elementAt(i);
            if(!currBar.isEmpty())
                currBar.draw(g);
        }

    }

    public void highlight()
    {
        for(int i = 0; i < elements.size(); i++)
            ((Element)(Element)elements.elementAt(i)).highlight();

    }

    public void unHighlight()
    {
        for(int i = 0; i < elements.size(); i++)
            ((Element)(Element)elements.elementAt(i)).unHighlight();

    }

    public Selectable getItemAt(Point point)
    {
        for(int i = 0; i < elements.size(); i++)
        {
            Element element = (Element)(Element)elements.elementAt(i);
            if(element != null && element.getItemAt(point) != null)
                return element;
        }

        return null;
    }

    public static final int DEFAULT_COLUM_WIDTH = 10;
    public static final int DEFAULT_COLUM_GAP = 10;
    protected int columGap;
    protected int sequenceNumber;
    protected int elementWidth;
    protected boolean allHaveSameWidth;
    protected Vector elements;
    private Vector elemTintMarks;
    protected Point location;
    private String markString;
    private int markIndex;
    private int compareMarkIndex;
    private Color compareColor;
    private int insetRectangleLeftIndex;
    private int insetRectangleRightIndex;
    private boolean insetRectangleSet;
}
