// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KMPString.java

import com.cim.AIA.Drawable;
import com.cim.AIA.Moveable;
import java.awt.*;

public class KMPString
    implements Drawable, Moveable
{

    public KMPString(String str)
    {
        location = new Point();
        labelColor = Color.red;
        highlightColor = Color.green;
        setLocation(0, 0);
        theString = str;
        initialise();
    }

    public void draw(Graphics g)
    {
        int xLoc = location.x + XGap;
        int yLoc = location.y;
        FontMetrics theFM = g.getFontMetrics();
        for(int counter = 0; counter < theString.length(); counter++)
        {
            char ch = theString.charAt(counter);
            int temp = (XGap - theFM.charWidth(ch)) / 2;
            if(counter == 0 && highlightMinusOne)
            {
                Color saveColor = g.getColor();
                g.setColor(highlightColor);
                g.fillRect((xLoc + temp) - XGap, yLoc - g.getFontMetrics().getHeight(), g.getFontMetrics().charWidth(ch), g.getFontMetrics().getHeight());
                g.setColor(saveColor);
            }
            if(highlightTable[counter])
            {
                Color saveColor = g.getColor();
                g.setColor(highlightColor);
                g.fillRect(xLoc + temp, yLoc - g.getFontMetrics().getHeight(), g.getFontMetrics().charWidth(ch), g.getFontMetrics().getHeight());
                g.setColor(saveColor);
            }
            g.drawString((new StringBuilder()).append("").append(ch).toString(), xLoc + temp, yLoc);
            if(showLabel && labelIndex == counter)
            {
                Color saveColor = g.getColor();
                g.setColor(labelColor);
                if(highLabel)
                    g.drawString(theLabel, xLoc + temp, yLoc - getHeight());
                else
                    g.drawString(theLabel, xLoc + temp, yLoc + getHeight());
                g.setColor(saveColor);
            }
            if(showLabel && labelIndex == -1 && counter == 0)
            {
                Color saveColor = g.getColor();
                g.setColor(labelColor);
                if(highLabel)
                    g.drawString(theLabel, (xLoc + temp) - XGap, yLoc - getHeight());
                else
                    g.drawString(theLabel, (xLoc + temp) - XGap, yLoc + getHeight());
                g.setColor(saveColor);
            }
            xLoc += XGap;
        }

    }

    public void draw(Graphics g, Point pnt)
    {
        setLocation(pnt);
        draw(g);
    }

    public int getHeight()
    {
        return KMPApplet.theKMPCanvas.getGraphics().getFontMetrics().getHeight();
    }

    public Point getLocation()
    {
        return location;
    }

    public boolean getShowLabel()
    {
        return showLabel;
    }

    public String getString()
    {
        return theString;
    }

    public int getWidth()
    {
        return theString.length() * XGap;
    }

    public int getX()
    {
        return location.x;
    }

    public int getY()
    {
        return location.y;
    }

    public void highlight(int index)
    {
        if(index >= 0)
            highlightTable[index] = true;
        if(index == -1)
            highlightMinusOne = true;
    }

    public void highlight(int index1, int index2)
    {
        for(int i = index1; i < index2; i++)
            highlight(i);

    }

    private void initialise()
    {
        XGap = 20;
        needPosition = true;
        length = theString.length();
        highlightTable = new boolean[length];
        theLabel = null;
        showLabel = false;
        highLabel = true;
        for(int i = 0; i < length; i++)
            highlightTable[i] = false;

        highlightMinusOne = false;
    }

    public int length()
    {
        return length;
    }

    public void setHighLabel(boolean state)
    {
        highLabel = state;
    }

    public void setHighlightColor(Color c)
    {
        highlightColor = c;
    }

    public void setLabel(String label)
    {
        theLabel = label;
    }

    public void setLabelIndex(int index)
    {
        labelIndex = index;
    }

    public void setLocation(int xPos, int yPos)
    {
        location.x = xPos;
        location.y = yPos;
    }

    public void setLocation(Point newPnt)
    {
        location = newPnt;
    }

    public void setPosition(int xPos, int yPos)
    {
        if(needPosition)
        {
            setLocation(xPos + location.x, yPos + location.y);
            needPosition = false;
        }
    }

    public void setShowLabel(boolean state)
    {
        showLabel = state;
    }

    public void setX(int x)
    {
        location.x = x;
    }

    public void setY(int y)
    {
        location.y = y;
    }

    public void shiftEntire(int x, int y)
    {
        location.x = location.x + x;
        location.y = location.y + y;
    }

    public void unHighlight(int index)
    {
        if(index >= 0)
            highlightTable[index] = false;
        if(index == -1)
            highlightMinusOne = false;
    }

    public void unHighlight(int index1, int index2)
    {
        for(int i = index1; i < index2; i++)
            unHighlight(i);

    }

    public void unHighlightAll()
    {
        unHighlight(-1, length);
    }

    protected String theString;
    protected int XGap;
    protected boolean needPosition;
    protected Point location;
    protected int length;
    protected String theLabel;
    protected boolean showLabel;
    protected boolean highLabel;
    protected int labelIndex;
    protected Color labelColor;
    protected Color highlightColor;
    protected boolean highlightTable[];
    protected boolean highlightMinusOne;
}
