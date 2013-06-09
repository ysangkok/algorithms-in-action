// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SelectionSortCanvas.java

import com.cim.AIA.*;
import java.awt.*;
import localization.Messages;

public class SelectionSortCanvas extends AlgorithmCanvas
{

    public SelectionSortCanvas()
    {
        backgroundColor = Color.white;
        textColor = Color.black;
        needInitialisation = false;
    }

    public void displayAlgorithm(Graphics g)
    {
        if(needInitialisation)
        {
            processRepaintEvent(new RepaintEvent(this, selectionSort));
            needInitialisation = false;
            return;
        }
        if(selectionSort != null)
        {
            elementArray.draw(g);
            if(iMarker != null)
                iMarker.draw(g);
            if(jMarker != null)
                jMarker.draw(g);
            if(line != null)
                line.draw(g);
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
        iMarker = initialiseMarker(selectionSort.getI(), "i", 3);
        jMarker = initialiseMarker(selectionSort.getJ(), "j", 4);
        line = initialiseLine(selectionSort.getMinPosition());
    }

    protected Line initialiseLine(int pos)
    {
        if(pos != -10)
        {
            VerticalBar element = (VerticalBar)elementArray.getElement(pos);
            if(element != null)
            {
                Point to = new Point(element.getPosition().x + element.getWidth() / 2, element.getPosition().y - element.getHeight());
                Point from = new Point(element.getPosition().x + element.getWidth() / 2, 50);
                Line line = new Line(from, to);
                line.showArrowHead(true);
                line.setDistanceFromStartForArrowHead(-3);
                line.setLabel(MIN_MARKER);
                line.setDistanceFromStartForLabel(-1);
                line.setXLabelOffset((-1 * getGraphics().getFontMetrics().stringWidth(MIN_MARKER)) / 2);
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
            selectionSort = (SelectionSort)(SelectionSort)e.paramObj;
            jMarker = iMarker = null;
            line = null;
            removeAllSelectables();
            if(getGraphics() == null)
            {
                needInitialisation = true;
                return;
            }
            elementArray = selectionSort.getElementArray(getGraphics());
            addSelectable(elementArray);
            initialise();
            addTween(selectionSort.generateTweens(this, elementArray, numberOfTweenSteps));
            if(tweens.getNumberOfTweens() > 0)
                tweens.run();
            selectionSort.removeAllAnimationRequests();
            repaint();
        }
    }

    private static final long serialVersionUID = 0x54e237e87d04e2cfL;
    protected static final String I_MARKER = "i";
    protected static final String J_MARKER = "j";
    protected static final String MIN_MARKER = Messages.getString("SelectionSortCanvas.2");
    protected SelectionSort selectionSort;
    protected ElementArray elementArray;
    protected StringMarker iMarker;
    protected StringMarker jMarker;
    protected Line line;
    protected Color backgroundColor;
    protected Color textColor;
    protected boolean needInitialisation;

}
