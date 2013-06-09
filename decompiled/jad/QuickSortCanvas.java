// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QuickSortCanvas.java

import com.cim.AIA.*;
import java.awt.*;
import localization.Messages;

public class QuickSortCanvas extends AlgorithmCanvas
{

    public QuickSortCanvas()
    {
        textColor = Color.black;
        xBuffer = 60;
        yBuffer = 150;
        gapBetweenArrayAndQuickSort = 120;
        spacingBetweenInformationAndQuickSort = 10;
        boxWidth = 20;
        activeYBuffer = 5;
        iWidth = 0;
        jWidth = 0;
        iPoint = null;
        jPoint = null;
        pValue = -1;
        pValueReady = false;
        activeLeft = null;
        activeRight = null;
        tweening = false;
    }

    protected void calculateMarkerPosition()
    {
        iPoint = jPoint = null;
        activeLeft = activeRight = null;
        QuickSort active = quickSort.getActive();
        if(active != null)
        {
            if(active.getI() != -10)
            {
                Element element = elementArray.getElement(active.getI());
                if(element != null)
                {
                    iWidth = element.getWidth();
                    Point pos = element.getPosition();
                    iPoint = new Point(pos.x, pos.y);
                }
            }
            if(active.getJ() != -10)
            {
                Element element = elementArray.getElement(active.getJ());
                if(element != null)
                {
                    jWidth = element.getWidth();
                    Point pos = element.getPosition();
                    jPoint = new Point(pos.x, pos.y);
                }
            }
            if(active.getRight() != -10)
            {
                Element element = elementArray.getElement(active.getRight());
                if(element != null)
                {
                    Point pos = element.getPosition();
                    activeRight = new Point(pos.x + elementArray.getElementWidth() + elementArray.getColumGap() / 2, pos.y);
                }
            }
            if(active.getLeft() != -10)
            {
                Element element = elementArray.getElement(active.getLeft());
                if(element != null)
                {
                    Point pos = element.getPosition();
                    activeLeft = new Point(pos.x - elementArray.getColumGap() / 2, pos.y);
                }
            }
        }
    }

    public void displayAlgorithm(Graphics g)
    {
        if(quickSort != null && elementArray != null)
        {
            if(quickSort.doDrawRectangles())
            {
                layoutAlgorithm(g);
                g.setColor(textColor);
                Point position = iPoint;
                if(position != null)
                    g.drawString("i", (position.x + iWidth / 2) - g.getFontMetrics().stringWidth("i") / 2, position.y + 3 * g.getFontMetrics().getHeight());
                position = jPoint;
                if(position != null)
                    g.drawString("J", (position.x + jWidth / 2) - g.getFontMetrics().stringWidth("J") / 2, position.y + 4 * g.getFontMetrics().getHeight());
                String informationString = (new StringBuilder()).append(Messages.getString("QuickSortCanvas.0")).append(quickSort.getNumberOfSwaps()).append(Messages.getString("QuickSortCanvas.6")).append(quickSort.getNumberOfComparisions()).toString();
                g.drawString(informationString, xBuffer, (yBuffer + gapBetweenArrayAndQuickSort) - g.getFontMetrics().getHeight() - spacingBetweenInformationAndQuickSort);
                g.setColor(Color.black);
                if(activeLeft != null && activeRight != null && activeRight.x != activeLeft.x)
                    g.drawRect(activeLeft.x, activeYBuffer, activeRight.x - activeLeft.x, yBuffer - 2);
                Point boxPoint = new Point(xBuffer + elementArray.getWidth() + elementArray.getElementWidth() + elementArray.getColumGap(), (yBuffer + gapBetweenArrayAndQuickSort) - g.getFontMetrics().getHeight());
                g.drawRect(boxPoint.x, boxPoint.y, boxWidth, boxWidth);
                g.setColor(textColor);
                g.drawString("P", (boxPoint.x + boxWidth / 2) - g.getFontMetrics().stringWidth("P") / 2, boxPoint.y);
                if(pValueReady)
                    g.drawString((new StringBuilder()).append("").append(pValue).toString(), (boxPoint.x + boxWidth / 2) - g.getFontMetrics().stringWidth((new StringBuilder()).append("").append(pValue).toString()) / 2, boxPoint.y + boxWidth / 2 + g.getFontMetrics().getHeight() / 2);
                quickSort.draw(g, new Point(xBuffer, yBuffer + gapBetweenArrayAndQuickSort));
            }
            elementArray.draw(g);
        }
    }

    protected void handleColorSelection(ColorSelection colorSelection)
    {
        String atribute = colorSelection.getAtributeName();
        if(atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR))
            textColor = colorSelection.getColor();
    }

    protected void handleFontSelection(FontSelection fontselection)
    {
    }

    protected void layoutAlgorithm(Graphics g)
    {
        if(!tweening)
        {
            int width = quickSort.getWidth(g) + elementArray.getElementWidth() + elementArray.getColumGap() + boxWidth;
            int height = yBuffer + gapBetweenArrayAndQuickSort + quickSort.getHeight(g);
            setSize(width, height);
            xBuffer = getSize().width / 2 - quickSort.getWidth(g) / 2;
            elementArray.setLocation(xBuffer, yBuffer);
        }
        pValue = -1;
        pValueReady = false;
        QuickSort active = quickSort.getActive();
        if(active != null && active.getPValue() != -10)
        {
            pValueReady = true;
            pValue = active.getPValue();
        }
    }

    public void processRepaintEvent(RepaintEvent e)
    {
        if(e.paramObj != null)
        {
            quickSort = (QuickSort)(QuickSort)e.paramObj;
            removeAllSelectables();
            elementArray = quickSort.getElementArray(new Point(xBuffer, yBuffer));
            addSelectable(elementArray);
            addTween(quickSort.generateTweens(this, elementArray, numberOfTweenSteps));
            quickSort.removeAllAnimationRequests();
            calculateMarkerPosition();
            tweening = true;
            tweens.run();
            tweening = false;
        }
        repaint();
    }

    private static final long serialVersionUID = 0x29bf0db52f927e7fL;
    protected Color textColor;
    protected int xBuffer;
    protected int yBuffer;
    protected int gapBetweenArrayAndQuickSort;
    protected int spacingBetweenInformationAndQuickSort;
    protected int boxWidth;
    protected int activeYBuffer;
    protected QuickSort quickSort;
    protected MyElementArrayQS elementArray;
    protected int iWidth;
    protected int jWidth;
    protected Point iPoint;
    protected Point jPoint;
    protected int pValue;
    protected boolean pValueReady;
    protected Point activeLeft;
    protected Point activeRight;
    protected Point oldIPoint;
    protected Point oldJPoint;
    protected boolean tweening;
}
