// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StraightRadixSortCanvas.java

import com.cim.AIA.*;
import java.awt.*;
import java.util.Vector;
import localization.Messages;

public class StraightRadixSortCanvas extends AlgorithmCanvas
{

    public StraightRadixSortCanvas()
    {
        xBuffer = 50;
        yBuffer = 100;
        spacingBetweenDataAndSymbolArray = 220;
        spacingBetweenSymbolAndAuxiliary = 100;
        dataElementArrayLabel = Messages.getString("StraightRadixSortCanvas.0");
        symbolElementArrayLabel = Messages.getString("StraightRadixSortCanvas.1");
        auxiliaryElementArrayLabel = Messages.getString("StraightRadixSortCanvas.2");
        MSBLabel = Messages.getString("StraightRadixSortCanvas.3");
        LSBLabel = Messages.getString("StraightRadixSortCanvas.4");
        xTextBuffer = 10;
        yTextBuffer = 5;
    }

    public void displayAlgorithm(Graphics g)
    {
        if(straightRadixSort != null)
        {
            if(normalFont != null)
                g.setFont(normalFont);
            if(textColor != null)
                g.setColor(textColor);
            int bitValue = straightRadixSort.getBit();
            if(dataElementArray != null)
            {
                int offsetSpacing = 6;
                int dataArrow = straightRadixSort.getDataArrow();
                setSize(dataElementArray.getWidth() + xBuffer + g.getFontMetrics().stringWidth(auxiliaryElementArrayLabel) + 2 * xTextBuffer, getSize().height);
                Vector splitMarkers = straightRadixSort.getSplitValues();
                displayElementArray(g, dataElementArray, dataElementArrayLabel, offsetSpacing, dataArrow, splitMarkers);
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
                int offsetSpacing = 7 + (4 + g.getFontMetrics().getHeight());
                displayElementArray(g, auxiliaryElementArray, auxiliaryElementArrayLabel, offsetSpacing, -10, null);
                auxiliaryElementArray.draw(g);
            }
            int dataArrow = straightRadixSort.getDataArrow();
            if(dataArrow != -10)
            {
                VerticalBar element = (VerticalBar)dataElementArray.getElement(dataArrow);
                if(element != null)
                {
                    Point elementPosition = element.getPosition();
                    Line line = new Line(elementPosition.x + element.getWidth() / 2, elementPosition.y - element.getHeight() - 2 - 20, elementPosition.x + element.getWidth() / 2, elementPosition.y - element.getHeight() - 2);
                    line.showArrowHead(true);
                    line.setDistanceFromStartForArrowHead(-3);
                    line.draw(g);
                }
            }
            int auxiliaryArrow = straightRadixSort.getAuxiliaryArrow();
            if(auxiliaryArrow != -10)
            {
                Node node = (Node)auxiliaryElementArray.getElement(auxiliaryArrow);
                VerticalBar verticalBar = (VerticalBar)dataElementArray.getElement(auxiliaryArrow);
                if(node != null && verticalBar != null)
                {
                    Point verticalBarPosition = verticalBar.getPosition();
                    Point nodePosition = node.getPosition();
                    Point point = dataElementArray.getLocation();
                    int bitLength = straightRadixSort.getBitLength();
                    Line line = new Line(nodePosition.x + node.getWidth() / 2, nodePosition.y, nodePosition.x + node.getWidth() / 2, ((point.y + 4 + 2) - 2) + (4 + g.getFontMetrics().getHeight()) * (bitLength + 2));
                    line.showArrowHead(true);
                    line.setDistanceFromStartForArrowHead(-3);
                    line.draw(g);
                }
            }
        }
    }

    protected void displayElementArray(Graphics g, ElementArray dataElementArray, String dataElementArrayLabel, int offsetSpacing, int dataArrow, Vector splitMarkers)
    {
        if(dataElementArray != null)
        {
            Point point = dataElementArray.getLocation();
            g.drawString(dataElementArrayLabel, point.x + dataElementArray.getWidth() + xTextBuffer, point.y);
            g.drawString(MSBLabel, point.x - xTextBuffer - g.getFontMetrics().stringWidth(MSBLabel), point.y + offsetSpacing + g.getFontMetrics().getHeight() + (4 + g.getFontMetrics().getHeight()) * 2);
            g.drawString(LSBLabel, point.x - xTextBuffer - g.getFontMetrics().stringWidth(LSBLabel), point.y + offsetSpacing + g.getFontMetrics().getHeight() + (4 + g.getFontMetrics().getHeight()) * ((straightRadixSort.getBitLength() - 1) + 2));
            for(int i = 0; i < straightRadixSort.getBitLength(); i++)
            {
                Color oldColor = g.getColor();
                g.setColor(straightRadixSort.getColor(i));
                g.fillRect(point.x, ((point.y + offsetSpacing) - 2) + (4 + g.getFontMetrics().getHeight()) * (i + 2), dataElementArray.getWidth(), g.getFontMetrics().getHeight() + 4);
                g.setColor(oldColor);
            }

            if(splitMarkers != null)
            {
                int elemWidth = dataElementArray.getElementWidth() + dataElementArray.getColumGap();
                int height = g.getFontMetrics().getHeight() + 4;
                for(int i = 0; i < splitMarkers.size(); i++)
                {
                    SplitMarker splitMarker = (SplitMarker)splitMarkers.elementAt(i);
                    int x = (point.x + splitMarker.getPosition() * elemWidth) - dataElementArray.getColumGap() / 2;
                    int y = ((point.y + offsetSpacing) - 2) + (4 + g.getFontMetrics().getHeight()) * (splitMarker.getLevel() + 2);
                    Line line = new Line(x, y, x, y + height);
                    line.setColor(getBackground());
                    line.draw(g);
                }

            }
            int bitValue = straightRadixSort.getBit();
            if(bitValue != -10 && bitValue >= 0)
            {
                g.setColor(Color.black);
                g.drawRect(point.x, ((point.y + offsetSpacing) - 2) + (4 + g.getFontMetrics().getHeight()) * (bitValue + 2), dataElementArray.getWidth() - 1, g.getFontMetrics().getHeight() + 4);
            }
        }
        int bitValue = straightRadixSort.getBit();
        if(dataArrow != -10 && bitValue != -10 && bitValue >= 0)
        {
            Point point = dataElementArray.getLocation();
            Element element = dataElementArray.getElement(dataArrow);
            if(element != null)
            {
                g.setColor(Color.black);
                Point elementPos = element.getPosition();
                g.drawRect(elementPos.x + 1, ((point.y + 1 + offsetSpacing) - 2) + (4 + g.getFontMetrics().getHeight()) * (bitValue + 2), element.getWidth() - 3, (g.getFontMetrics().getHeight() + 4) - 2);
            }
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

    public void processRepaintEvent(RepaintEvent e)
    {
        if(e.paramObj != null)
        {
            drawables.removeAllElements();
            straightRadixSort = (StraightRadixSort)(StraightRadixSort)e.paramObj;
            dataElementArray = straightRadixSort.getDataElementArray();
            if(dataElementArray != null)
                dataElementArray.setLocation(xBuffer, yBuffer);
            auxiliaryElementArray = straightRadixSort.getAuxiliaryElementArray();
            if(auxiliaryElementArray != null)
                auxiliaryElementArray.setLocation(xBuffer - 2, yBuffer + spacingBetweenDataAndSymbolArray + spacingBetweenSymbolAndAuxiliary);
            symbolElementArray = straightRadixSort.getSymbolElementArray();
            if(symbolElementArray != null && dataElementArray != null)
                symbolElementArray.setLocation(xBuffer + (dataElementArray.getWidth() / 2 - symbolElementArray.getWidth() / 2), yBuffer + spacingBetweenDataAndSymbolArray);
            if(symbolElementArray != null && auxiliaryElementArray != null)
                straightRadixSort.calculateLines(this, symbolElementArray, auxiliaryElementArray);
            repaint();
        }
    }

    private static final long serialVersionUID = 0x4e9c00590d5810afL;
    protected ElementArray dataElementArray;
    protected ElementArray symbolElementArray;
    protected ElementArray auxiliaryElementArray;
    protected int xBuffer;
    protected int yBuffer;
    protected int spacingBetweenDataAndSymbolArray;
    protected int spacingBetweenSymbolAndAuxiliary;
    protected String dataElementArrayLabel;
    protected String symbolElementArrayLabel;
    protected String auxiliaryElementArrayLabel;
    protected String MSBLabel;
    protected String LSBLabel;
    protected int xTextBuffer;
    protected int yTextBuffer;
    protected Font normalFont;
    protected Color textColor;
    protected StraightRadixSort straightRadixSort;
}
