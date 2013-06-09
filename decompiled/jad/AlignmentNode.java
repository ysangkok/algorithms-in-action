// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AlignmentNode.java

import com.cim.AIA.Line;
import com.cim.AIA.Node;
import java.awt.*;

public class AlignmentNode extends Node
{

    public AlignmentNode(Object value, int sequenceNumber)
    {
        this(value, sequenceNumber, 10, 10);
    }

    public AlignmentNode(Object value, int sequenceNumber, int aWidth, int aHeight)
    {
        super(value, sequenceNumber);
        AWidth = 0;
        BWidth = 0;
        traceUp = false;
        highlightUp = false;
        traceLeft = false;
        highlightLeft = false;
        traceDiag = false;
        highlightDiag = false;
        traceA = false;
        highlightA = false;
        traceB = false;
        highlightB = false;
        traceC = false;
        highlightC = false;
        arrowHeight = aHeight;
        arrowWidth = aWidth;
        drawTraceBacks = true;
        arrowHighlightColor = DEFAULT_ARROW_HIGHLIGHT;
        arrowColor = DEFAULT_ARROW_COLOR;
        arrayHighlightColor = DEFAULT_ARRAY_HIGHLIGHT;
        arrayActiveColor = DEFAULT_ARRAY_ACTIVE;
        arrayInactiveColor = DEFAULT_ARRAY_INACTIVE;
        arrayDisabledColor = DEFAULT_ARRAY_DISABLE;
        isDisabled = false;
        bufferHeight = 0;
        bufferWidth = 0;
        haveChoice = false;
        fontSizeDelta = 4;
        saveColor = backgroundColor;
    }

    public void draw(Graphics g)
    {
        int tempWidth = arrowWidth;
        int tempHeight = arrowHeight;
        arrowWidth = 0;
        arrowHeight = 0;
        pos.x += tempWidth;
        pos.y += tempHeight;
        if(!isVisible)
            return;
        float hsb[] = Color.RGBtoHSB(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue(), null);
        if((double)hsb[2] < 0.65000000000000002D)
            setTextColor(Color.white);
        else
            setTextColor(Color.black);
        super.draw(g);
        int midX = pos.x + getWidth() / 2;
        int midY = pos.y + getHeight() / 2;
        if(originalFont == null)
            originalFont = new Font(g.getFont().getName(), g.getFont().getStyle(), g.getFont().getSize());
        if(newFont == null)
            newFont = new Font(g.getFont().getName(), g.getFont().getStyle(), g.getFont().getSize() - fontSizeDelta);
        g.setFont(newFont);
        if(AWidth == 0)
            AWidth = g.getFontMetrics().stringWidth("A");
        if(BWidth == 0)
            BWidth = g.getFontMetrics().stringWidth("B");
        if(haveChoice)
        {
            if(highlightA)
                g.setColor(arrayHighlightColor);
            else
            if(traceA)
                g.setColor(arrayActiveColor);
            else
                g.setColor(arrayInactiveColor);
            g.drawString("A", pos.x + AWidth / 2, pos.y);
            if(highlightB)
                g.setColor(arrayHighlightColor);
            else
            if(traceB)
                g.setColor(arrayActiveColor);
            else
                g.setColor(arrayInactiveColor);
            g.drawString("B", pos.x + AWidth / 2 + AWidth, pos.y);
            if(highlightC)
                g.setColor(arrayHighlightColor);
            else
            if(traceC)
                g.setColor(arrayActiveColor);
            else
                g.setColor(arrayInactiveColor);
            g.drawString("C", pos.x + AWidth / 2 + AWidth + BWidth, pos.y);
        }
        g.setColor(Color.black);
        g.setFont(originalFont);
        if(drawTraceBacks)
        {
            if(traceUp)
            {
                Line tUp = new Line(midX, pos.y - bufferHeight, midX, pos.y - tempHeight - bufferHeight);
                if(highlightUp)
                    tUp.setColor(arrowHighlightColor);
                else
                    tUp.setColor(arrowColor);
                tUp.showArrowHead(true);
                tUp.setDistanceFromStartForArrowHead(-3);
                tUp.draw(g);
            }
            if(traceLeft)
            {
                Line tLeft = new Line(pos.x - bufferWidth, midY, pos.x - tempWidth - bufferWidth, midY);
                if(highlightLeft)
                    tLeft.setColor(arrowHighlightColor);
                else
                    tLeft.setColor(arrowColor);
                tLeft.showArrowHead(true);
                tLeft.setDistanceFromStartForArrowHead(-3);
                tLeft.draw(g);
            }
            if(traceDiag)
            {
                Line tDiag = new Line(pos.x, pos.y, pos.x - tempWidth - bufferWidth, pos.y - tempHeight - bufferHeight);
                if(highlightDiag)
                    tDiag.setColor(arrowHighlightColor);
                else
                    tDiag.setColor(arrowColor);
                tDiag.showArrowHead(true);
                tDiag.setDistanceFromStartForArrowHead(-3);
                tDiag.draw(g);
            }
        }
        pos.x -= tempWidth;
        pos.y -= tempHeight;
        arrowWidth = tempWidth;
        arrowHeight = tempHeight;
    }

    public Color getArrayActiveColor()
    {
        return arrayActiveColor;
    }

    public Color getArrayHighlightColor()
    {
        return arrayHighlightColor;
    }

    public Color getArrayInactiveColor()
    {
        return arrayInactiveColor;
    }

    public Color getArrowColor()
    {
        return arrowColor;
    }

    public int getArrowHeight()
    {
        return arrowHeight;
    }

    public Color getArrowHighlightColor()
    {
        return arrowHighlightColor;
    }

    public int getArrowWidth()
    {
        return arrowWidth;
    }

    public int getBufferHeight()
    {
        return bufferHeight;
    }

    public int getBufferWidth()
    {
        return bufferWidth;
    }

    public boolean getDrawTraceBacks()
    {
        return drawTraceBacks;
    }

    public int getHeight()
    {
        return super.getHeight() + arrowHeight;
    }

    public int getRealHeight()
    {
        return super.getHeight() + arrowHeight + bufferHeight;
    }

    public int getRealWidth()
    {
        return super.getWidth() + arrowWidth + bufferWidth;
    }

    public boolean getTraceA()
    {
        return traceA;
    }

    public boolean getTraceB()
    {
        return traceB;
    }

    public boolean getTraceC()
    {
        return traceC;
    }

    public boolean getTraceDiag()
    {
        return traceDiag;
    }

    public boolean getTraceLeft()
    {
        return traceLeft;
    }

    public boolean getTraceUp()
    {
        return traceUp;
    }

    public int getWidth()
    {
        return super.getWidth() + arrowWidth;
    }

    public void highlightA()
    {
        highlightA = true;
    }

    public void highlightB()
    {
        highlightB = true;
    }

    public void highlightC()
    {
        highlightC = true;
    }

    public void highlightDiag()
    {
        highlightDiag = true;
    }

    public void highlightLeft()
    {
        highlightLeft = true;
    }

    public void highlightUp()
    {
        highlightUp = true;
    }

    public void setArrayActiveColor(Color c)
    {
        arrayActiveColor = c;
    }

    public void setArrayHighlightColor(Color c)
    {
        arrayHighlightColor = c;
    }

    public void setArrayInactiveColor(Color c)
    {
        arrayInactiveColor = c;
    }

    public void setArrowColor(Color c)
    {
        arrowColor = c;
    }

    public void setArrowHeight(int x)
    {
        arrowHeight = x;
    }

    public void setArrowHighlightColor(Color c)
    {
        arrowHighlightColor = c;
    }

    public void setArrowWidth(int x)
    {
        arrowWidth = x;
    }

    public void setBackgroundColor(Color color)
    {
        super.setBackgroundColor(color);
    }

    public void setBufferHeight(int x)
    {
        bufferHeight = x;
    }

    public void setBufferWidth(int x)
    {
        bufferWidth = x;
    }

    public void setDrawTraceBacks(boolean state)
    {
        drawTraceBacks = state;
    }

    public void setFontSizeDelta(int c)
    {
        fontSizeDelta = c;
    }

    public void setHaveChoice(boolean state)
    {
        haveChoice = state;
    }

    public void setIsDisabled(boolean state)
    {
        isDisabled = state;
        if(state)
        {
            saveColor = backgroundColor;
            setBackgroundColor(arrayDisabledColor);
        } else
        {
            setBackgroundColor(saveColor);
        }
    }

    public void setTraceA(boolean state)
    {
        traceA = state;
    }

    public void setTraceB(boolean state)
    {
        traceB = state;
    }

    public void setTraceC(boolean state)
    {
        traceC = state;
    }

    public void setTraceDiag(boolean state)
    {
        traceDiag = state;
    }

    public void setTraceLeft(boolean state)
    {
        traceLeft = state;
    }

    public void setTraceUp(boolean state)
    {
        traceUp = state;
    }

    public void unHighlightA()
    {
        highlightA = false;
    }

    public void unHighlightB()
    {
        highlightB = false;
    }

    public void unHighlightC()
    {
        highlightC = false;
    }

    public void unHighlightDiag()
    {
        highlightDiag = false;
    }

    public void unHighlightLeft()
    {
        highlightLeft = false;
    }

    public void unHighlightUp()
    {
        highlightUp = false;
    }

    public static final Color DEFAULT_ARROW_COLOR;
    public static final Color DEFAULT_ARROW_HIGHLIGHT;
    public static final Color DEFAULT_ARRAY_HIGHLIGHT;
    public static final Color DEFAULT_ARRAY_ACTIVE;
    public static final Color DEFAULT_ARRAY_INACTIVE;
    public static final Color DEFAULT_ARRAY_DISABLE;
    private static Font originalFont = null;
    private static Font newFont = null;
    protected boolean traceUp;
    protected boolean highlightUp;
    protected boolean traceLeft;
    protected boolean highlightLeft;
    protected boolean traceDiag;
    protected boolean highlightDiag;
    protected boolean drawTraceBacks;
    protected boolean haveChoice;
    protected boolean traceA;
    protected boolean highlightA;
    protected boolean traceB;
    protected boolean highlightB;
    protected boolean traceC;
    protected boolean highlightC;
    protected Color arrayHighlightColor;
    protected Color arrayActiveColor;
    protected Color arrayInactiveColor;
    protected Color arrowHighlightColor;
    protected Color arrowColor;
    protected Color arrayDisabledColor;
    protected int bufferHeight;
    protected int bufferWidth;
    protected int arrowHeight;
    protected int arrowWidth;
    protected boolean isDisabled;
    protected Color saveColor;
    protected int fontSizeDelta;
    private int AWidth;
    private int BWidth;

    static 
    {
        DEFAULT_ARROW_COLOR = Color.black;
        DEFAULT_ARROW_HIGHLIGHT = Color.red;
        DEFAULT_ARRAY_HIGHLIGHT = Color.red;
        DEFAULT_ARRAY_ACTIVE = Color.black;
        DEFAULT_ARRAY_INACTIVE = Color.lightGray;
        DEFAULT_ARRAY_DISABLE = Color.gray;
    }
}
