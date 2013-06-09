// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MyElementArrayShell.java

import com.cim.AIA.*;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;

public class MyElementArrayShell
    implements Drawable, Selectable, ObjectContainer
{

    public MyElementArrayShell(int xBuffer, int yAxis)
    {
        columGap = 10;
        sequenceNumber = 1;
        elementWidth = 10;
        allHaveSameWidth = true;
        location = new Point(xBuffer, yAxis);
        elements = new Vector();
    }

    public void add(Object object, int position)
    {
        if(object instanceof Element)
            setValue(position, (Element)object);
    }

    public void draw(Graphics g)
    {
        for(int i = 0; i < elements.size(); i++)
            if(!isEmpty(i))
                ((Element)elements.elementAt(i)).draw(g);

    }

    public void draw(Graphics g, Point p)
    {
        setLocation(p.x, p.y);
        draw(g);
    }

    public boolean equals(Selectable selectable)
    {
        if(selectable instanceof MyElementArrayShell)
        {
            MyElementArrayShell elementArray = (MyElementArrayShell)selectable;
            if(elementArray.getIdentifier() != getIdentifier())
                return false;
            if(elements.size() != elementArray.elements.size())
                return false;
            for(int i = 0; i < elements.size(); i++)
            {
                Element one = (Element)elements.elementAt(i);
                Element two = (Element)elementArray.elements.elementAt(i);
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

    public int getColumGap()
    {
        return columGap;
    }

    public Element getElement(int id)
    {
        if(id >= 0 && id < elements.size())
            return (Element)elements.elementAt(id);
        else
            return null;
    }

    public int getElementWidth()
    {
        return elementWidth;
    }

    public int getHeight()
    {
        int maxHeight = 0;
        for(int i = 0; i < elements.size(); i++)
        {
            Element element = (Element)elements.elementAt(i);
            if(element != null && element.getHeight() > maxHeight)
                maxHeight = element.getHeight();
        }

        return maxHeight;
    }

    public int getIdentifier()
    {
        return sequenceNumber;
    }

    public Selectable getItemAt(Point point)
    {
        for(int i = 0; i < elements.size(); i++)
        {
            Element element = (Element)elements.elementAt(i);
            if(element != null && element.getItemAt(point) != null)
                return element;
        }

        return null;
    }

    public Point getLocation()
    {
        return location;
    }

    public int getSize()
    {
        return elements.size();
    }

    public int getWidth()
    {
        if(allHaveSameWidth)
            return elements.size() * elementWidth + (elements.size() - 1) * columGap;
        int width = 0;
        for(int i = 0; i < elements.size(); i++)
        {
            Element element = (Element)elements.elementAt(i);
            width += element.getWidth();
        }

        return width + (elements.size() - 1) * columGap;
    }

    public void highlight()
    {
        for(int i = 0; i < elements.size(); i++)
            ((Element)elements.elementAt(i)).highlight();

    }

    public boolean isEmpty(int id)
    {
        return elements.elementAt(id) == null;
    }

    public void remove(Object object)
    {
        if(object instanceof Element)
            removeElement((Element)object);
    }

    public void removeElement(Element element)
    {
        if(elements.contains(element))
            elements.removeElement(element);
    }

    public void removeElement(int id)
    {
        if(id >= 0 && id < elements.size())
            elements.removeElementAt(id);
    }

    public void setAllHaveSameWidth(boolean state)
    {
        allHaveSameWidth = state;
        if(state)
        {
            for(int i = 0; i < elements.size(); i++)
                if(!isEmpty(i))
                {
                    Element element = (Element)elements.elementAt(i);
                    element.setWidth(elementWidth);
                }

        }
    }

    public void setColumGap(int columGap)
    {
        this.columGap = columGap;
    }

    public void setElementWidth(int elementWidth)
    {
        this.elementWidth = elementWidth;
        for(int i = 0; i < elements.size(); i++)
            if(!isEmpty(i))
            {
                Element element = (Element)elements.elementAt(i);
                element.setWidth(elementWidth);
            }

    }

    public void setLocation(int xBuffer, int yAxis)
    {
        location.x = xBuffer;
        location.y = yAxis;
        if(allHaveSameWidth)
        {
            for(int id = 0; id < elements.size(); id++)
            {
                Element element = (Element)elements.elementAt(id);
                if(element != null)
                    element.setPosition(new Point(location.x + id * (columGap + elementWidth), location.y));
            }

        } else
        {
            int width = 0;
            for(int id = 0; id < elements.size(); id++)
            {
                Element element = (Element)elements.elementAt(id);
                if(element != null)
                {
                    element.setPosition(new Point(location.x + id * columGap + width, location.y));
                    width += element.getWidth();
                }
            }

        }
    }

    public void setSequence(int sequenceNumber)
    {
        this.sequenceNumber = sequenceNumber;
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

    public void unHighlight()
    {
        for(int i = 0; i < elements.size(); i++)
            ((Element)elements.elementAt(i)).unHighlight();

    }

    public static final int DEFAULT_COLUM_WIDTH = 10;
    public static final int DEFAULT_COLUM_GAP = 10;
    protected int columGap;
    protected int sequenceNumber;
    protected int elementWidth;
    protected boolean allHaveSameWidth;
    protected Vector elements;
    protected Point location;
}
