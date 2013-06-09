// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RadixExchangeSortCanvas.java

import com.cim.AIA.*;
import java.awt.*;

public class RadixExchangeSortCanvas extends AlgorithmCanvas
{

    public RadixExchangeSortCanvas()
    {
        xBuffer = 60;
        yBuffer = 150;
        gapBetweenArrayAndRadix = 70;
        textColor = Color.black;
        tweening = false;
    }

    public void displayAlgorithm(Graphics g)
    {
        if(radixExchangeSort != null && elementArray != null)
        {
            layoutAlgorithm(g);
            if(iMarker != null)
                iMarker.draw(g);
            if(jMarker != null)
                jMarker.draw(g);
            radixExchangeSort.draw(g, new Point(xBuffer, yBuffer + gapBetweenArrayAndRadix));
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
            int width = xBuffer + radixExchangeSort.getWidth(g);
            int height = yBuffer + gapBetweenArrayAndRadix + radixExchangeSort.getHeight(g);
            setSize(width, height);
            xBuffer = getSize().width / 2 - radixExchangeSort.getWidth(g) / 2;
            elementArray.setLocation(radixExchangeSort.getArrayBuffer(g) + xBuffer, yBuffer);
        }
    }

    public void processRepaintEvent(RepaintEvent e)
    {
        if(e.paramObj != null)
        {
            radixExchangeSort = (RadixExchangeSort)(RadixExchangeSort)e.paramObj;
            Graphics g = getGraphics();
            Point tempPoint = new Point(xBuffer, yBuffer);
            if(g != null)
                tempPoint.x += radixExchangeSort.getArrayBuffer(g);
            elementArray = radixExchangeSort.getElementArray(tempPoint);
            addTween(radixExchangeSort.generateTweens(this, elementArray, numberOfTweenSteps));
            radixExchangeSort.removeAllAnimationRequests();
            iMarker = jMarker = null;
            RadixExchangeSort active = radixExchangeSort.getActive();
            if(active != null && g != null)
            {
                if(active.getI() != -10)
                {
                    Element element = elementArray.getElement(active.getI());
                    if(element != null)
                    {
                        Point pos = element.getPosition();
                        Point iPoint = new Point(pos.x + element.getWidth() / 2, pos.y + 3 * g.getFontMetrics().getHeight());
                        iMarker = new StringMarker("i", iPoint);
                        iMarker.setBackgroundColor(getBackground());
                        iMarker.setColor(textColor);
                    }
                }
                if(active.getJ() != -10)
                {
                    Element element = elementArray.getElement(active.getJ());
                    if(element != null)
                    {
                        Point pos = element.getPosition();
                        Point jPoint = new Point(pos.x + element.getWidth() / 2, pos.y + 4 * g.getFontMetrics().getHeight());
                        jMarker = new StringMarker("J", jPoint);
                        jMarker.setBackgroundColor(getBackground());
                        jMarker.setColor(textColor);
                    }
                }
            }
            tweening = true;
            tweens.run();
            tweening = false;
        }
        repaint();
    }

    private static final long serialVersionUID = 0x3424adf3a2fe4985L;
    protected int xBuffer;
    protected int yBuffer;
    protected int gapBetweenArrayAndRadix;
    protected Color textColor;
    protected RadixExchangeSort radixExchangeSort;
    protected ElementArray elementArray;
    protected boolean tweening;
    protected StringMarker iMarker;
    protected StringMarker jMarker;
}
