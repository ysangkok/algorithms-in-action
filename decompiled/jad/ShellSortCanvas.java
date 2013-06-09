// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ShellSortCanvas.java

import com.cim.AIA.*;
import java.awt.*;
import java.util.Vector;
import localization.Messages;

public class ShellSortCanvas extends AlgorithmCanvas
{

    public ShellSortCanvas()
    {
        xBuffer = 80;
        yBuffer = 180;
        boxWidth = 20;
        value = -1;
        hValue = -1;
        firstPosValue = -1;
        backgroundColor = Color.white;
        textColor = Color.black;
        needInitialisation = false;
    }

    public void displayAlgorithm(Graphics g)
    {
        if(needInitialisation)
        {
            processRepaintEvent(new RepaintEvent(this, shellSort));
            needInitialisation = false;
            return;
        }
        if(shellSort != null)
        {
            elementArray.draw(g);
            if(iMarker != null)
                iMarker.draw(g);
            if(jMarker != null)
                jMarker.draw(g);
            if(ShellSort.drawLine.size() > 0)
            {
                for(int i = 0; i < ShellSort.drawLine.size() - 1; i++)
                    if(horizontalLines[i] != null)
                        horizontalLines[i].draw(g);

                for(int j = 0; j < ShellSort.drawLine.size(); j++)
                    if(verticalPointerLines[j] != null)
                        verticalPointerLines[j].draw(g);

            }
            if(firstPosLine != null)
                firstPosLine.draw(g);
            Point boxPoint = new Point(elementArray.getLocation().x + elementArray.getWidth() + elementArray.getElementWidth() + elementArray.getColumGap(), yBuffer + g.getFontMetrics().getHeight());
            g.drawRect(boxPoint.x, boxPoint.y, boxWidth, boxWidth);
            g.setColor(textColor);
            g.drawString(Messages.getString("ShellSortCanvas.1"), (boxPoint.x + boxWidth / 2) - g.getFontMetrics().stringWidth(Messages.getString("ShellSortCanvas.2")) / 2, boxPoint.y);
            if(shellSort.getVal() != -10)
            {
                g.setColor(ShellSort.activeArrayColor);
                g.fillRect(boxPoint.x, boxPoint.y, boxWidth, boxWidth);
                g.setColor(textColor);
                value = shellSort.getVal();
                g.drawString((new StringBuilder()).append("").append(value).toString(), (boxPoint.x + boxWidth / 2) - g.getFontMetrics().stringWidth((new StringBuilder()).append("").append(value).toString()) / 2, boxPoint.y + boxWidth / 2 + g.getFontMetrics().getHeight() / 2);
            }
            Point boxPoint1 = new Point(elementArray.getLocation().x + elementArray.getWidth() + elementArray.getElementWidth() + elementArray.getColumGap(), (yBuffer - 40) + g.getFontMetrics().getHeight());
            g.drawRect(boxPoint1.x, boxPoint1.y, boxWidth, boxWidth);
            g.setColor(textColor);
            g.drawString("h", (boxPoint.x + boxWidth / 2) - g.getFontMetrics().stringWidth("h") / 2, boxPoint1.y);
            if(shellSort.getH() != -10)
            {
                hValue = shellSort.getH();
                g.drawString((new StringBuilder()).append("").append(hValue).toString(), (boxPoint1.x + boxWidth / 2) - g.getFontMetrics().stringWidth((new StringBuilder()).append("").append(hValue).toString()) / 2, boxPoint1.y + boxWidth / 2 + g.getFontMetrics().getHeight() / 2);
            }
            if(shellSort.getComparisonOrder() == 10)
            {
                Point boxPoint2 = new Point(elementArray.getLocation().x + elementArray.getWidth() + elementArray.getElementWidth() + elementArray.getColumGap(), (yBuffer - 80) + g.getFontMetrics().getHeight());
                g.drawRect(boxPoint2.x, boxPoint2.y, boxWidth, boxWidth);
                g.setColor(textColor);
                g.drawString(Messages.getString("ShellSortCanvas.3"), (boxPoint2.x + boxWidth / 2) - g.getFontMetrics().stringWidth(Messages.getString("ShellSortCanvas.4")) / 2, boxPoint2.y);
                if(shellSort.getFirstPos() != -10)
                {
                    firstPosValue = shellSort.getFirstPos();
                    g.drawString((new StringBuilder()).append("").append(firstPosValue).toString(), (boxPoint2.x + boxWidth / 2) - g.getFontMetrics().stringWidth((new StringBuilder()).append("").append(firstPosValue).toString()) / 2, boxPoint2.y + boxWidth / 2 + g.getFontMetrics().getHeight() / 2);
                }
            }
        }
    }

    protected Line drawHorizontalLine(int seqNo1, int seqNo2)
    {
        Point from = elementArray.getElement(seqNo1).getPosition();
        Point to = elementArray.getElement(seqNo2).getPosition();
        Line horizontalLine = new Line(from.x + elementArray.getElementWidth() / 2, from.y + 30, to.x + elementArray.getElementWidth() / 2, to.y + 30);
        return horizontalLine;
    }

    protected Line drawLinePointers(int seqNo)
    {
        Point at = elementArray.getElement(seqNo).getPosition();
        Line verticalPointerLine = new Line(at.x + elementArray.getElementWidth() / 2, at.y + 30, at.x + elementArray.getElementWidth() / 2, at.y + 20);
        return verticalPointerLine;
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

    protected void handleFontSelection(FontSelection fontSelection)
    {
        String atribute = fontSelection.getAtributeName();
        if(atribute.equalsIgnoreCase("Normal Font"))
            setFont(fontSelection.getFont());
    }

    protected void initialise()
    {
        Dimension currentSize = getSize();
        currentSize.width = elementArray.getWidth() + 50;
        setSize(currentSize);
        elementArray.setLocation(getSize().width / 2 - elementArray.getWidth() / 2, 200);
        iMarker = initialiseMarker(shellSort.getI(), "i", 3);
        jMarker = initialiseMarker(shellSort.getJ(), "J", 4);
        firstPosLine = initialiseLine(shellSort.getFirstPos(), FIRST_POS_MARKER);
        if(ShellSort.drawLine.size() > 0)
        {
            for(int i = 0; i < ShellSort.drawLine.size() - 1; i++)
                horizontalLines[i] = drawHorizontalLine(((Integer)ShellSort.drawLine.elementAt(i)).intValue(), ((Integer)ShellSort.drawLine.elementAt(i + 1)).intValue());

            for(int j = 0; j < ShellSort.drawLine.size(); j++)
                verticalPointerLines[j] = drawLinePointers(((Integer)ShellSort.drawLine.elementAt(j)).intValue());

        }
    }

    protected Line initialiseLine(int pos, String string)
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
            shellSort = (ShellSort)(ShellSort)e.paramObj;
            jMarker = iMarker = null;
            if(ShellSort.drawLine.size() > 0)
            {
                horizontalLines = new Line[ShellSort.drawLine.size() - 1];
                verticalPointerLines = new Line[ShellSort.drawLine.size()];
                for(int i = 0; i < ShellSort.drawLine.size() - 1; i++)
                    horizontalLines[i] = null;

                for(int j = 0; j < ShellSort.drawLine.size(); j++)
                    verticalPointerLines[j] = null;

            }
            if(getGraphics() == null)
            {
                needInitialisation = true;
                return;
            }
            elementArray = shellSort.getElementArray(getGraphics());
            initialise();
            addTween(shellSort.generateTweens(this, elementArray, numberOfTweenSteps));
            if(tweens.getNumberOfTweens() > 0)
                tweens.run();
            shellSort.removeAllAnimationRequests();
        }
        repaint();
    }

    private static final long serialVersionUID = 0xfb2166d5ccb6478L;
    protected static final String I_MARKER = "i";
    protected static final String J_MARKER = "J";
    protected static final String FIRST_POS_MARKER = Messages.getString("ShellSortCanvas.0");
    protected StringMarker iMarker;
    protected StringMarker jMarker;
    protected int xBuffer;
    protected int yBuffer;
    protected int boxWidth;
    protected ShellSort shellSort;
    protected ElementArray elementArray;
    protected int value;
    protected int hValue;
    protected int firstPosValue;
    protected Color backgroundColor;
    protected Color textColor;
    protected boolean needInitialisation;
    protected Line firstPosLine;
    protected Line horizontalLines[];
    protected Line verticalPointerLines[];

}
