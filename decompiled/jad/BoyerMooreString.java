// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BoyerMooreString.java

import com.cim.AIA.*;
import java.awt.*;

public class BoyerMooreString
    implements Drawable, Moveable
{

    public BoyerMooreString(String str)
    {
        location = new Point();
        labelColor = Color.red;
        highlightColor = Color.green;
        theString = str;
        initialise();
    }

    public void draw(Graphics g)
    {
        int xLoc = location.x;
        int yLoc = location.y;
        FontMetrics theFM = g.getFontMetrics();
        if(notDrawnYet)
        {
            notDrawnYet = false;
            xArrowIndex = xArrowIndex + 100;
        }
        xArrowTarget = xLoc;
        xArrowTarget = xArrowTarget + XGap * arrowLabelTarget + theFM.charWidth('F') / 2;
        for(int counter = 0; counter < theString.length(); counter++)
        {
            char ch = theString.charAt(counter);
            int temp = (XGap - theFM.charWidth(ch)) / 2;
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
                    g.drawString((new StringBuilder()).append(theLabel).append(" = ").append(labelIndex).toString(), xLoc + temp, yLoc - getHeight());
                else
                    g.drawString((new StringBuilder()).append(theLabel).append(" = ").append(labelIndex).toString(), xLoc + temp, yLoc + getHeight());
                g.setColor(saveColor);
            }
            xLoc += XGap;
        }

        if(arrowLabelTarget != -1)
        {
            Color saveColor = g.getColor();
            g.setColor(labelColor);
            if(highLabel)
            {
                Line arrLine = new Line(xArrowIndex, yLoc - getHeight() * 2, xArrowTarget + theFM.charWidth('F') / 2, yLoc - getHeight());
                g.drawString((new StringBuilder()).append(theLabel).append(" = ").append(arrowLabelTarget).toString(), xArrowIndex + theFM.charWidth('F') / 2, yLoc - getHeight() * 2);
                arrLine.showArrowHead(true);
                arrLine.setDistanceFromStartForArrowHead(arrLine.getLength());
                arrLine.draw(g);
            } else
            {
                Line arrLine = new Line(xArrowIndex + theFM.charWidth('F') / 2, yLoc + getHeight() * 1, xArrowTarget + theFM.charWidth('F') / 2, yLoc);
                g.drawString((new StringBuilder()).append(theLabel).append(" = ").append(arrowLabelTarget).toString(), xArrowIndex + theFM.charWidth('F') / 2, yLoc + getHeight() * 2);
                arrLine.showArrowHead(true);
                arrLine.setDistanceFromStartForArrowHead(arrLine.getLength());
                arrLine.draw(g);
            }
            g.setColor(saveColor);
        }
    }

    public void draw(Graphics g, Point pnt)
    {
        setLocation(pnt);
        draw(g);
    }

    public int getHeight()
    {
        return BoyerMooreApplet.theBoyerMooreCanvas.getGraphics().getFontMetrics().getHeight();
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
        highlightTable[index] = true;
    }

    public void highlight(int index1, int index2)
    {
        for(int i = index1; i < index2; i++)
            highlightTable[i] = true;

    }

    private void initialise()
    {
        setLocation(0, 0);
        XGap = 20;
        needPosition = true;
        length = theString.length();
        highlightTable = new boolean[length];
        theLabel = null;
        showLabel = false;
        highLabel = true;
        arrowLabelIndex = 0;
        arrowLabelTarget = -1;
        xArrowIndex = -1;
        notDrawnYet = true;
        for(int i = 0; i < length; i++)
            highlightTable[i] = false;

    }

    public void jumpArrowLabelIndex(int jump)
    {
        if(xArrowIndex == -1)
            setArrowLabelIndex(jump);
        else
            xArrowIndex = xArrowIndex + jump * XGap;
    }

    public int length()
    {
        return length;
    }

    public void setArrowLabelIndex(int index)
    {
        arrowLabelIndex = index;
        xArrowIndex = index * XGap + location.x;
    }

    public void setArrowLabelTarget(int index)
    {
        arrowLabelTarget = index;
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
        highlightTable[index] = false;
    }

    public void unHighlight(int index1, int index2)
    {
        for(int i = index1; i < index2; i++)
            highlightTable[i] = false;

    }

    public void unHighlightAll()
    {
        for(int i = 0; i < length; i++)
            highlightTable[i] = false;

    }

    protected String theString;
    protected int XGap;
    protected boolean needPosition;
    protected Point location;
    protected int length;
    protected String theLabel;
    protected boolean showLabel;
    protected boolean highLabel;
    protected boolean notDrawnYet;
    protected int labelIndex;
    protected Color labelColor;
    protected int arrowLabelIndex;
    protected int arrowLabelTarget;
    protected int xArrowIndex;
    protected int xArrowTarget;
    protected Color highlightColor;
    protected boolean highlightTable[];
}
