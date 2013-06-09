// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InsertionSortCanvas.java

import com.cim.AIA.*;
import java.awt.*;
import localization.Messages;

public class InsertionSortCanvas extends AlgorithmCanvas
{

    public InsertionSortCanvas()
    {
        xBuffer = 80;
        yBuffer = 180;
        boxWidth = 20;
        value = -1;
        backgroundColor = Color.white;
        textColor = Color.black;
        needInitialisation = false;
        tweening = false;
    }

    public void displayAlgorithm(Graphics g)
    {
        if(needInitialisation)
        {
            processRepaintEvent(new RepaintEvent(this, insertionSort));
            needInitialisation = false;
            return;
        }
        if(insertionSort != null)
        {
            elementArray.draw(g);
            if(iMarker != null)
                iMarker.draw(g);
            if(jMarker != null && !insertionSort.getDontPaintJMarker())
                jMarker.draw(g);
            if(iLine != null)
                iLine.draw(g);
            if(finalLine != null)
                finalLine.draw(g);
            if(elementLine != null)
                elementLine.draw(g);
            Point boxPoint = new Point(elementArray.getLocation().x + elementArray.getWidth() + elementArray.getElementWidth() + elementArray.getColumGap(), yBuffer + g.getFontMetrics().getHeight());
            g.drawRect(boxPoint.x, boxPoint.y, boxWidth, boxWidth);
            g.setColor(textColor);
            g.drawString(Messages.getString("InsertionSortCanvas.2"), (boxPoint.x + boxWidth / 2) - g.getFontMetrics().stringWidth(Messages.getString("InsertionSortCanvas.3")) / 2, boxPoint.y);
            if(insertionSort.getVal() != -10)
            {
                g.setColor(InsertionSort.activeArrayColor);
                g.fillRect(boxPoint.x, boxPoint.y, boxWidth, boxWidth);
                g.setColor(textColor);
                value = insertionSort.getVal();
                g.drawString((new StringBuilder()).append("").append(value).toString(), (boxPoint.x + boxWidth / 2) - g.getFontMetrics().stringWidth((new StringBuilder()).append("").append(value).toString()) / 2, boxPoint.y + boxWidth / 2 + g.getFontMetrics().getHeight() / 2);
                if(!insertionSort.getDontPaintValMarker() && elementLine != null)
                {
                    Point from = new Point(boxPoint.x + boxWidth / 2, 50);
                    Point to = new Point(boxPoint.x + boxWidth / 2, boxPoint.y - boxWidth);
                    Line line = new Line(from, to);
                    line.showArrowHead(true);
                    line.setDistanceFromStartForArrowHead(-3);
                    line.draw(g);
                }
            }
        }
    }

    protected void handleColorSelection(ColorSelection colorSelection)
    {
        String atribute = colorSelection.getAtributeName();
        if(atribute.equalsIgnoreCase(ColorSelection.BACKGROUND))
            backgroundColor = colorSelection.getColor();
        else
        if(atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR))
            textColor = colorSelection.getColor();
    }

    protected void handleFontSelection(FontSelection fontselection)
    {
    }

    protected void initialise()
    {
        Dimension currentSize = getSize();
        currentSize.width = elementArray.getWidth() + 50;
        setSize(currentSize);
        elementArray.setLocation(getSize().width / 2 - elementArray.getWidth() / 2, 200);
        iMarker = initialiseMarker(insertionSort.getI(), "i", 3);
        jMarker = initialiseMarker(insertionSort.getJ(), "J", 4);
        iLine = initialiseLine(insertionSort.getI(), "i");
        finalLine = initialiseLine(insertionSort.getFinalPosition(), FINAL_MARKER);
        elementLine = initialiseLine(insertionSort.getElementPosition(), "A[j-1]");
    }

    protected Line initialiseLine(int pos, String string)
    {
        if(pos != -10)
        {
            VerticalBar element = (VerticalBar)elementArray.getElement(pos);
            if(element != null)
            {
                Point to;
                Point from;
                if(string == "i")
                {
                    to = new Point(element.getPosition().x + element.getWidth() / 2, element.getPosition().y + 20);
                    from = new Point(element.getPosition().x + element.getWidth() / 2, element.getPosition().y + 30);
                } else
                {
                    to = new Point(element.getPosition().x + element.getWidth() / 2, element.getPosition().y - element.getHeight());
                    from = new Point(element.getPosition().x + element.getWidth() / 2, 50);
                }
                Line line = new Line(from, to);
                line.showArrowHead(true);
                line.setDistanceFromStartForArrowHead(-3);
                if(string != "i")
                    line.setLabel(string);
                line.setDistanceFromStartForLabel(-1);
                line.setXLabelOffset((-1 * getGraphics().getFontMetrics().stringWidth(string)) / 2);
                line.setYLabelOffset(-1 * getGraphics().getFontMetrics().getHeight());
                return line;
            }
        }
        return null;
    }

    protected StringMarker initialiseMarker(int pos, String string, int level)
    {
        if(pos != -10)
        {
            VerticalBar element = (VerticalBar)elementArray.getElement(pos);
            if(element != null)
            {
                Point temp = new Point(element.getPosition().x + element.getWidth() / 2, element.getPosition().y + 5 + level * getGraphics().getFontMetrics().getHeight());
                StringMarker stringMarker = new StringMarker(string, temp);
                stringMarker.setColor(textColor);
                stringMarker.setBackgroundColor(backgroundColor);
                return stringMarker;
            }
        }
        return null;
    }

    public void processRepaintEvent(RepaintEvent e)
    {
        if(e.paramObj != null)
        {
            insertionSort = (InsertionSort)(InsertionSort)e.paramObj;
            jMarker = iMarker = null;
            if(getGraphics() == null)
            {
                needInitialisation = true;
                return;
            }
            elementArray = insertionSort.getElementArray(getGraphics());
            initialise();
            addTween(insertionSort.generateTweens(this, elementArray, numberOfTweenSteps));
            tweening = true;
            if(tweens.getNumberOfTweens() > 0)
                tweens.run();
            tweening = false;
            insertionSort.removeAllAnimationRequests();
        }
        repaint();
    }

    private static final long serialVersionUID = 0xd6e928e245b9f2d9L;
    protected static final String I_MARKER = "i";
    protected static final String J_MARKER = "J";
    protected static final String VAL_MARKER = Messages.getString("InsertionSortCanvas.0");
    protected static final String FINAL_MARKER = Messages.getString("InsertionSortCanvas.1");
    protected static final String ELEMENT_MARKER = "A[j-1]";
    protected int xBuffer;
    protected int yBuffer;
    protected int boxWidth;
    protected InsertionSort insertionSort;
    protected ElementArray elementArray;
    protected StringMarker iMarker;
    protected Line finalLine;
    protected Line iLine;
    protected Line elementLine;
    protected StringMarker jMarker;
    protected int value;
    protected Color backgroundColor;
    protected Color textColor;
    protected boolean needInitialisation;
    protected boolean tweening;

}
