// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ShellSort.java

import com.cim.AIA.*;
import java.awt.*;
import java.util.Vector;
import localization.Messages;

public class ShellSort extends Algorithm
    implements ColorSelectionListener
{

    public ShellSort(AlgorithmThread algorithmThread, Object data)
    {
        super(algorithmThread, data);
        i = -10;
        j = -10;
        h = -10;
        val = -10;
        firstPos = -10;
        finalPosition = -10;
        recordOfI = -10;
        recordOfH = -10;
        textColor = Color.black;
        arrayColor = Color.pink;
        processedArrayColor = Color.red.darker();
        finishedArrayColor = Color.gray;
        backgroundColor = Color.white;
        initialise((int[])(int[])data);
        ConfigurationManager cm = ConfigurationManager.getConfigurationManager();
        ColorSelection arrayColorSelection = new ColorSelection(arrayColor, ARRAY_COLOR);
        arrayColorSelection.addClass(this);
        ColorSelection activeArrayColorSelection = new ColorSelection(activeArrayColor, ACTIVE_ARRAY_COLOR);
        activeArrayColorSelection.addClass(this);
        ColorSelection processedArrayColorSelection = new ColorSelection(processedArrayColor, PROCESSED_ARRAY_COLOR);
        processedArrayColorSelection.addClass(this);
        ColorSelection finishedArrayColorSelection = new ColorSelection(finishedArrayColor, FINISHED_ARRAY_COLOR);
        finishedArrayColorSelection.addClass(this);
        cm.addColorSelection(arrayColorSelection);
        cm.addColorSelection(activeArrayColorSelection);
        cm.addColorSelection(processedArrayColorSelection);
        cm.addColorSelection(finishedArrayColorSelection);
        cm.addColorSelectionListener(this);
    }

    protected void createDuplicateLabels()
    {
        Integer temp[] = new Integer[data.length];
        for(int i = 0; i < temp.length; i++)
            temp[i] = new Integer(data[i]);

        duplicateLabels = DuplicateLabel.createDuplicateLabels(temp);
    }

    protected Vector generateQuestions()
    {
        return null;
    }

    protected void generateTweens(MultipleTween tweens, ElementArray elementArray, int numberOfSteps)
    {
        for(int i = 0; i < swapRequests.size(); i++)
        {
            SwapRequest swapRequest = (SwapRequest)swapRequests.elementAt(i);
            VerticalBar from = (VerticalBar)(VerticalBar)elementArray.getElement(swapRequest.getFirstId());
            if(((ShellSortThread)algorithmThread).getWindow().isExpanded("7.2"))
                from.setColor(processedArrayColor);
            VerticalBar to = (VerticalBar)(VerticalBar)elementArray.getElement(swapRequest.getSecondId());
            if(((ShellSortThread)algorithmThread).getWindow().isExpanded("7.2") && i == swapRequests.size() - 1)
            {
                to.setHeight(0);
                to.setThickness(0);
                to.setDrawValueMarker(false);
            }
            tweens.add(new MoveTween(null, from, to.getPosition(), from.getPosition(), false, numberOfSteps));
            tweens.add(new MoveTween(null, to, from.getPosition(), to.getPosition(), false, numberOfSteps));
        }

    }

    public MultipleTween generateTweens(Paintable paintable, Object parameter, int numberOfSteps)
    {
        MultipleTween tweens = new MultipleTween(paintable);
        ElementArray elementArray = (ElementArray)parameter;
        generateTweens(tweens, elementArray, numberOfSteps);
        return tweens;
    }

    public String getClassName()
    {
        return Messages.getString("ShellSort.4");
    }

    public int getComparisonOrder()
    {
        return currentComparisonOrder;
    }

    public ElementArray getElementArray(Graphics g)
    {
        ElementArray elementArray = new ElementArray(0, 0);
        elementArray.setElementWidth(g.getFontMetrics().stringWidth("8") + 2);
        elementArray.setAllHaveSameWidth(true);
        for(int i = 0; i < data.length; i++)
        {
            VerticalBar verticalBar = new VerticalBar(i, -10, backgroundColor, backgroundColor, new Point(0, 0));
            if(dontPaintVerticalBarJ)
            {
                if(i != getJ())
                {
                    verticalBar = new VerticalBar(i, data[i], colors[i], new Point(0, 0));
                    if(recordOfI != 1 && getH() == 1 && i < recordOfI)
                        verticalBar.setColor(processedArrayColor);
                    if(getH() == 1 && i == recordOfI && paintProcessedArrayI)
                        verticalBar.setColor(processedArrayColor);
                    if(i == getFinalPosition())
                        verticalBar.setColor(activeArrayColor);
                    if(finished)
                        verticalBar.setColor(finishedArrayColor);
                    verticalBar.setMarkersAboveValue(false);
                    verticalBar.setTextColor(textColor);
                } else
                {
                    verticalBar.setDrawValueMarker(false);
                }
            } else
            if(dontPaintVerticalBarI)
            {
                if(i != getI())
                {
                    verticalBar = new VerticalBar(i, data[i], colors[i], new Point(0, 0));
                    if(recordOfI != 1 && getH() == 1 && i < recordOfI)
                        verticalBar.setColor(processedArrayColor);
                    if(getH() == 1 && i == recordOfI && paintProcessedArrayI)
                        verticalBar.setColor(processedArrayColor);
                    if(i == getFinalPosition())
                        verticalBar.setColor(activeArrayColor);
                    if(finished)
                        verticalBar.setColor(finishedArrayColor);
                    verticalBar.setMarkersAboveValue(false);
                    verticalBar.setTextColor(textColor);
                } else
                {
                    verticalBar.setDrawValueMarker(false);
                }
            } else
            {
                verticalBar = new VerticalBar(i, data[i], colors[i], new Point(0, 0));
                if(recordOfI != 1 && getH() == 1 && i < recordOfI)
                    verticalBar.setColor(processedArrayColor);
                if(getH() == 1 && i == recordOfI && paintProcessedArrayI)
                    verticalBar.setColor(processedArrayColor);
                if(i == getFinalPosition())
                    verticalBar.setColor(activeArrayColor);
                if(finished)
                    verticalBar.setColor(finishedArrayColor);
                verticalBar.setMarkersAboveValue(false);
                verticalBar.setTextColor(textColor);
            }
            elementArray.setValue(i, verticalBar);
            if(duplicateLabels[i] != null)
            {
                StringMarker stringMarker = new StringMarker((new StringBuilder()).append("").append(data[i]).append(duplicateLabels[i]).toString());
                stringMarker.setBackgroundColor(backgroundColor);
                stringMarker.setColor(textColor);
                elementArray.getElement(i).addMarker(stringMarker);
            }
        }

        return elementArray;
    }

    public int getFinalPosition()
    {
        return finalPosition;
    }

    public int getFirstPos()
    {
        return firstPos;
    }

    public int getH()
    {
        return h;
    }

    public int getI()
    {
        return i;
    }

    public int getJ()
    {
        return j;
    }

    public int getVal()
    {
        return val;
    }

    protected boolean hasQuestions()
    {
        return false;
    }

    protected void initialise(int data[])
    {
        this.data = data;
        colors = new Color[data.length];
        for(int i = 0; i < colors.length; i++)
            colors[i] = arrayColor;

        createDuplicateLabels();
        this.i = -10;
        j = -10;
        recordOfI = -10;
        recordOfH = -10;
        paintProcessedArrayI = false;
        finalPosition = -10;
        finished = false;
    }

    public void processColorSelection(ColorSelection colorSelection)
    {
        String atribute = colorSelection.getAtributeName();
        if(atribute.equalsIgnoreCase(ColorSelection.BACKGROUND))
            backgroundColor = colorSelection.getColor();
        else
        if(atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR))
            textColor = colorSelection.getColor();
        else
        if(atribute.equalsIgnoreCase(ARRAY_COLOR))
        {
            for(int i = 0; i < colors.length; i++)
                if(colors[i] == arrayColor)
                    colors[i] = colorSelection.getColor();

            arrayColor = colorSelection.getColor();
        } else
        if(atribute.equalsIgnoreCase(ACTIVE_ARRAY_COLOR))
        {
            for(int i = 0; i < colors.length; i++)
                if(colors[i] == activeArrayColor)
                    colors[i] = colorSelection.getColor();

            activeArrayColor = colorSelection.getColor();
        } else
        if(atribute.equalsIgnoreCase(PROCESSED_ARRAY_COLOR))
        {
            for(int i = 0; i < colors.length; i++)
                if(colors[i] == processedArrayColor)
                    colors[i] = colorSelection.getColor();

            processedArrayColor = colorSelection.getColor();
        } else
        if(atribute.equalsIgnoreCase(FINISHED_ARRAY_COLOR))
        {
            for(int i = 0; i < colors.length; i++)
                if(colors[i] == finishedArrayColor)
                    colors[i] = colorSelection.getColor();

            finishedArrayColor = colorSelection.getColor();
        }
    }

    public void reInitialise(Object data)
    {
        initialise((int[])(int[])data);
        i = j = val = -10;
    }

    protected void removeAllAnimationRequests()
    {
        swapRequests.removeAllElements();
    }

    protected void removeAllQuestions()
    {
    }

    protected void run()
    {
        setPosition("1");
        for(h = 1; h <= data.length / 4; h = 3 * h + 1);
        setPosition("5");
        for(; h > 0; h /= 3)
        {
            for(int k = 0; k < colors.length; k++)
                colors[k] = arrayColor;

            recordOfI = 0;
            setPosition("7");
            switch(currentComparisonOrder)
            {
            default:
                break;

            case 10: // '\n'
                recordOfH = h;
                firstPos = 0;
                if(currentComparisonOrder != 10)
                {
                    h = 3 * h + 1;
                    firstPos = -10;
                } else
                {
                    setPosition("7.5");
                    do
                    {
                        if(firstPos >= h)
                            break;
                        i = firstPos + h;
                        recordOfI = i;
                        if(currentComparisonOrder != 10)
                        {
                            h = 3 * h + 1;
                            firstPos = -10;
                            break;
                        }
                        setPosition("7.5.2");
                        do
                        {
                            if(i >= data.length)
                                break;
                            paintProcessedArrayI = false;
                            val = data[i];
                            colors[i] = activeArrayColor;
                            if(currentComparisonOrder != 10)
                            {
                                h = 3 * h + 1;
                                firstPos = -10;
                                break;
                            }
                            setPosition("7.5.3.1");
                            j = i;
                            if(currentComparisonOrder != 10)
                            {
                                h = 3 * h + 1;
                                firstPos = -10;
                                break;
                            }
                            setPosition("7.5.3.2");
                            drawLine.addElement(new Integer(j));
                            drawLine.addElement(new Integer(j - h));
                            if(currentComparisonOrder != 10)
                            {
                                h = 3 * h + 1;
                                firstPos = -10;
                                break;
                            }
                            setPosition("7.5.3.3");
                            do
                            {
                                if(j < h || data[j - h] <= val)
                                    break;
                                swap(j, j - h);
                                if(currentComparisonOrder != 10)
                                {
                                    h = 3 * h + 1;
                                    firstPos = -10;
                                    break;
                                }
                                setPosition("7.5.3.4.1");
                                j -= h;
                                if(((ShellSortThread)algorithmThread).getWindow().isExpanded("7.5.1"))
                                    dontPaintVerticalBarJ = true;
                                paintProcessedArrayI = true;
                                if(currentComparisonOrder != 10)
                                {
                                    h = 3 * h + 1;
                                    firstPos = -10;
                                    break;
                                }
                                setPosition("7.5.3.4.2");
                                if(j >= h)
                                    drawLine.addElement(new Integer(j - h));
                                if(currentComparisonOrder != 10)
                                {
                                    h = 3 * h + 1;
                                    firstPos = -10;
                                    break;
                                }
                                setPosition("7.5.3.3");
                                if(dontPaintVerticalBarJ)
                                    dontPaintVerticalBarJ = false;
                            } while(true);
                            if(j >= h && data[j - h] <= val)
                                colors[j - h] = processedArrayColor;
                            data[j] = val;
                            finalPosition = j;
                            if(currentComparisonOrder != 10)
                            {
                                if(h == recordOfH)
                                    h = 3 * h + 1;
                                if(drawLine.size() > 0)
                                    drawLine.removeAllElements();
                                firstPos = -10;
                                break;
                            }
                            setPosition("7.5.3.6");
                            finalPosition = -10;
                            colors[j] = processedArrayColor;
                            j = -10;
                            val = -10;
                            drawLine.removeAllElements();
                            i += h;
                            recordOfI = i;
                            paintProcessedArrayI = false;
                            if(currentComparisonOrder != 10)
                            {
                                if(h == recordOfH)
                                    h = 3 * h + 1;
                                if(drawLine.size() > 0)
                                    drawLine.removeAllElements();
                                firstPos = -10;
                                break;
                            }
                            if(i < data.length)
                                setPosition("7.5.2");
                        } while(true);
                        if(currentComparisonOrder != 10)
                        {
                            if(h == recordOfH)
                                h = 3 * h + 1;
                            if(drawLine.size() > 0)
                                drawLine.removeAllElements();
                            firstPos = -10;
                            i = -10;
                            j = -10;
                            finalPosition = -10;
                            if(dontPaintVerticalBarJ)
                                dontPaintVerticalBarJ = false;
                            break;
                        }
                        setPosition("7.5.4");
                        recordOfI = data.length;
                        i = -10;
                        firstPos++;
                        if(currentComparisonOrder != 10)
                        {
                            if(h == recordOfH)
                                h = 3 * h + 1;
                            if(drawLine.size() > 0)
                                drawLine.removeAllElements();
                            firstPos = -10;
                            i = -10;
                            j = -10;
                            finalPosition = -10;
                            if(dontPaintVerticalBarJ)
                                dontPaintVerticalBarJ = false;
                            break;
                        }
                        if(firstPos != h)
                            setPosition("7.5");
                    } while(true);
                    setPosition("7.6");
                    firstPos = -10;
                }
                break;

            case 11: // '\013'
                recordOfH = h;
                i = h;
                recordOfI = i;
                if(currentComparisonOrder != 11)
                {
                    h = 3 * h + 1;
                    break;
                }
                setPosition("7.4");
                do
                {
                    if(i >= data.length)
                        break;
                    paintProcessedArrayI = false;
                    val = data[i];
                    colors[i] = activeArrayColor;
                    if(currentComparisonOrder != 11)
                    {
                        h = 3 * h + 1;
                        break;
                    }
                    setPosition("7.4.2.1");
                    j = i;
                    if(currentComparisonOrder != 11)
                    {
                        h = 3 * h + 1;
                        break;
                    }
                    setPosition("7.4.2.2");
                    drawLine.addElement(new Integer(j));
                    drawLine.addElement(new Integer(j - h));
                    if(currentComparisonOrder != 11)
                    {
                        h = 3 * h + 1;
                        break;
                    }
                    setPosition("7.4.2.3");
                    do
                    {
                        if(j < h || data[j - h] <= val)
                            break;
                        swap(j, j - h);
                        if(currentComparisonOrder != 11)
                        {
                            h = 3 * h + 1;
                            break;
                        }
                        setPosition("7.4.2.4.1");
                        j -= h;
                        if(((ShellSortThread)algorithmThread).getWindow().isExpanded("7.4.1"))
                            dontPaintVerticalBarJ = true;
                        paintProcessedArrayI = true;
                        if(currentComparisonOrder != 11)
                        {
                            h = 3 * h + 1;
                            break;
                        }
                        setPosition("7.4.2.4.2");
                        if(j >= h)
                            drawLine.addElement(new Integer(j - h));
                        if(currentComparisonOrder != 11)
                        {
                            h = 3 * h + 1;
                            break;
                        }
                        setPosition("7.4.2.3");
                        if(dontPaintVerticalBarJ)
                            dontPaintVerticalBarJ = false;
                    } while(true);
                    if(j >= h && data[j - h] <= val)
                        colors[j - h] = processedArrayColor;
                    data[j] = val;
                    finalPosition = j;
                    if(currentComparisonOrder != 11)
                    {
                        if(h == recordOfH)
                            h = 3 * h + 1;
                        if(drawLine.size() > 0)
                            drawLine.removeAllElements();
                        break;
                    }
                    setPosition("7.4.2.6");
                    finalPosition = -10;
                    colors[j] = processedArrayColor;
                    j = -10;
                    val = -10;
                    drawLine.removeAllElements();
                    i++;
                    recordOfI = i;
                    paintProcessedArrayI = false;
                    if(currentComparisonOrder != 11)
                    {
                        if(h == recordOfH)
                            h = 3 * h + 1;
                        if(drawLine.size() > 0)
                            drawLine.removeAllElements();
                        break;
                    }
                    if(i != data.length)
                        setPosition("7.4");
                } while(true);
                if(currentComparisonOrder != 11)
                {
                    if(h == recordOfH)
                        h = 3 * h + 1;
                    if(drawLine.size() > 0)
                        drawLine.removeAllElements();
                    i = -10;
                    j = -10;
                    finalPosition = -10;
                    if(dontPaintVerticalBarJ)
                        dontPaintVerticalBarJ = false;
                } else
                {
                    setPosition("7.5");
                    recordOfI = data.length;
                    i = -10;
                }
                break;
            }
        }

        h = -10;
        finished = true;
    }

    public void setComparisonOrder(int comparisonOrder)
    {
        currentComparisonOrder = comparisonOrder;
    }

    protected void swap(int j, int i)
    {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
        colors[i] = colors[j] = processedArrayColor;
        String tempLabel = duplicateLabels[i];
        duplicateLabels[i] = duplicateLabels[j];
        duplicateLabels[j] = tempLabel;
        if(i != j)
            swapRequests.addElement(new SwapRequest(j, i));
    }

    public static final int MARKER_NOT_READY = -10;
    public static final int H_SUBFILES = 10;
    public static final int SINGLE_PASS = 11;
    protected static int currentComparisonOrder = 10;
    protected static String duplicateLabels[];
    public static Vector drawLine = new Vector();
    public static Color activeArrayColor;
    protected static final String ARRAY_COLOR = Messages.getString("ShellSort.0");
    protected static final String ACTIVE_ARRAY_COLOR = Messages.getString("ShellSort.1");
    protected static final String PROCESSED_ARRAY_COLOR = Messages.getString("ShellSort.2");
    protected static final String FINISHED_ARRAY_COLOR = Messages.getString("ShellSort.3");
    protected static Vector swapRequests = new Vector();
    protected static boolean dontPaintVerticalBarI = false;
    protected static boolean dontPaintVerticalBarJ = false;
    protected static boolean finished = false;
    protected static boolean paintProcessedArrayI = false;
    protected int data[];
    protected Color colors[];
    protected int i;
    protected int j;
    protected int h;
    protected int val;
    protected int firstPos;
    protected int finalPosition;
    protected int recordOfI;
    protected int recordOfH;
    protected Color textColor;
    protected Color arrayColor;
    protected Color processedArrayColor;
    protected Color finishedArrayColor;
    protected Color backgroundColor;

    static 
    {
        activeArrayColor = Color.red;
    }
}
