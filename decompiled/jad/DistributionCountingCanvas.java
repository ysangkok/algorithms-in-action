// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DistributionCountingCanvas.java

import com.cim.AIA.*;
import java.awt.*;
import java.util.Vector;
import localization.Messages;

public class DistributionCountingCanvas extends AlgorithmCanvas
{

    public DistributionCountingCanvas()
    {
        xBuffer = 30;
        yBuffer = 20;
        spacingBetweenArrays = 100;
        dataElementArrayLabel = Messages.getString("DistributionCountingCanvas.0");
        symbolElementArrayLabel = Messages.getString("DistributionCountingCanvas.1");
        auxiliaryElementArrayLabel = Messages.getString("DistributionCountingCanvas.2");
        xTextBuffer = 10;
        yTextBuffer = 5;
    }

    public void displayAlgorithm(Graphics g)
    {
        if(distributionCounting != null)
        {
            if(normalFont != null)
                g.setFont(normalFont);
            if(textColor != null)
                g.setColor(textColor);
            if(dataElementArray != null)
            {
                Point point = dataElementArray.getLocation();
                g.drawString(dataElementArrayLabel, point.x + dataElementArray.getWidth() + xTextBuffer, point.y + dataElementArray.getHeight() / 2 + g.getFontMetrics().getHeight() / 3);
                setSize(dataElementArray.getWidth() + xBuffer + g.getFontMetrics().stringWidth(auxiliaryElementArrayLabel) + xTextBuffer, getSize().height);
                dataElementArray.draw(g);
            }
            if(symbolElementArray != null)
            {
                Point point = symbolElementArray.getLocation();
                g.drawString(symbolElementArrayLabel, point.x + symbolElementArray.getWidth() + xTextBuffer, point.y + symbolElementArray.getHeight() / 2 + g.getFontMetrics().getHeight() / 3);
                symbolElementArray.draw(g);
            }
            if(auxiliaryElementArray != null)
            {
                Point point = auxiliaryElementArray.getLocation();
                g.drawString(auxiliaryElementArrayLabel, point.x + auxiliaryElementArray.getWidth() + xTextBuffer, point.y + auxiliaryElementArray.getHeight() / 2 + g.getFontMetrics().getHeight() / 3);
                auxiliaryElementArray.draw(g);
            }
            int dataArrow = distributionCounting.getDataArrow();
            if(dataArrow != -10)
            {
                Element element = dataElementArray.getElement(dataArrow);
                if(element != null)
                {
                    Point position = element.getPosition();
                    Line line = new Line(position.x + element.getWidth() / 2, position.y - 20, position.x + element.getWidth() / 2, position.y);
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
        if(atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR))
            textColor = colorSelection.getColor();
    }

    protected void handleFontSelection(FontSelection fontSelection)
    {
        String atribute = fontSelection.getAtributeName();
        if(atribute.equalsIgnoreCase("Normal Font"))
            normalFont = fontSelection.getFont();
    }

    public void processRepaintEvent(RepaintEvent e)
    {
        if(e.paramObj != null)
        {
            drawables.removeAllElements();
            distributionCounting = (DistributionCounting)(DistributionCounting)e.paramObj;
            dataElementArray = distributionCounting.getDataElementArray();
            if(dataElementArray != null)
                dataElementArray.setLocation(xBuffer, yBuffer);
            auxiliaryElementArray = distributionCounting.getAuxiliaryElementArray();
            if(auxiliaryElementArray != null)
                auxiliaryElementArray.setLocation(xBuffer, yBuffer + 2 * spacingBetweenArrays);
            symbolElementArray = distributionCounting.getSymbolElementArray();
            if(symbolElementArray != null && dataElementArray != null)
                symbolElementArray.setLocation(xBuffer + (dataElementArray.getWidth() / 2 - symbolElementArray.getWidth() / 2), yBuffer + spacingBetweenArrays);
            if(symbolElementArray != null && auxiliaryElementArray != null)
                distributionCounting.calculateLines(this, symbolElementArray, auxiliaryElementArray);
            repaint();
        }
    }

    private static final long serialVersionUID = 0xf214fab86a267874L;
    protected ElementArray dataElementArray;
    protected ElementArray symbolElementArray;
    protected ElementArray auxiliaryElementArray;
    protected int xBuffer;
    protected int yBuffer;
    protected int spacingBetweenArrays;
    protected String dataElementArrayLabel;
    protected String symbolElementArrayLabel;
    protected String auxiliaryElementArrayLabel;
    protected int xTextBuffer;
    protected int yTextBuffer;
    protected Font normalFont;
    protected Color textColor;
    protected DistributionCounting distributionCounting;
}
