// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InsertionSort.java

import com.cim.AIA.*;
import java.awt.*;
import java.util.Vector;
import localization.Messages;

public class InsertionSort extends Algorithm
    implements ColorSelectionListener
{

    public InsertionSort(AlgorithmThread algorithmThread, Object data)
    {
        super(algorithmThread, data);
        recordOfI = 0;
        recordOfJ = 0;
        i = -10;
        j = -10;
        val = -10;
        valPosition = -10;
        finalPosition = -10;
        elementPosition = -10;
        textColor = Color.black;
        arrayColor = Color.pink;
        processedArrayColor = Color.red.darker();
        finishedArrayColor = Color.gray;
        backgroundColor = Color.white;
        finished = false;
        initialise((int[])(int[])data);
        ConfigurationManager cm = ConfigurationManager.getConfigurationManager();
        ColorSelection arrayColorSelection = new ColorSelection(arrayColor, ARRAY_COLOR);
        arrayColorSelection.addClass(this);
        ColorSelection activeArrayColorSelection = new ColorSelection(activeArrayColor, ACTIVE_ARRAY_COLOR);
        arrayColorSelection.addClass(this);
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
        for(int i = 0; i < moveRequests.size(); i++)
        {
            SwapRequest moveRequest = (SwapRequest)moveRequests.elementAt(i);
            VerticalBar from = (VerticalBar)(VerticalBar)elementArray.getElement(moveRequest.getFirstId());
            from.setColor(processedArrayColor);
            VerticalBar to = (VerticalBar)(VerticalBar)elementArray.getElement(moveRequest.getSecondId());
            if(i == moveRequests.size() - 1)
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
        return Messages.getString("InsertionSort.4");
    }

    public boolean getDontPaintElementMarker()
    {
        return dontPaintElementMarker;
    }

    public boolean getDontPaintJMarker()
    {
        return dontPaintJMarker;
    }

    public boolean getDontPaintValMarker()
    {
        return dontPaintValMarker;
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
                    verticalBar = new VerticalBar(i, data[i], arrayColor, new Point(0, 0));
                    if(recordOfI != 1 && i < recordOfI)
                        verticalBar.setColor(processedArrayColor);
                    if(i == recordOfI && paintProcessedArrayI)
                        verticalBar.setColor(processedArrayColor);
                    if(paintActiveValEarly && i == getI())
                        verticalBar.setColor(activeArrayColor);
                    if(i == getFinalPosition())
                        verticalBar.setColor(activeArrayColor);
                    if(i == getValPosition())
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
                verticalBar = new VerticalBar(i, data[i], arrayColor, new Point(0, 0));
                if(recordOfI != 1 && i < recordOfI)
                    verticalBar.setColor(processedArrayColor);
                if(i == recordOfI && paintProcessedArrayI)
                    verticalBar.setColor(processedArrayColor);
                if(paintActiveValEarly && i == getI())
                    verticalBar.setColor(activeArrayColor);
                if(i == getFinalPosition())
                    verticalBar.setColor(activeArrayColor);
                if(i == getValPosition())
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

    public int getElementPosition()
    {
        return elementPosition;
    }

    public int getFinalPosition()
    {
        return finalPosition;
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

    public int getValPosition()
    {
        return valPosition;
    }

    protected boolean hasQuestions()
    {
        return false;
    }

    protected void initialise(int data[])
    {
        this.data = data;
        createDuplicateLabels();
        i = -10;
        j = -10;
        recordOfI = 0;
        recordOfJ = 0;
        val = -10;
        dontPaintVerticalBarJ = false;
        paintProcessedArrayI = false;
        paintActiveValEarly = false;
        dontPaintValMarker = false;
        valPosition = -10;
        finalPosition = -10;
        elementPosition = -10;
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
            arrayColor = colorSelection.getColor();
        else
        if(atribute.equalsIgnoreCase(ACTIVE_ARRAY_COLOR))
            activeArrayColor = colorSelection.getColor();
        else
        if(atribute.equalsIgnoreCase(PROCESSED_ARRAY_COLOR))
            processedArrayColor = colorSelection.getColor();
        else
        if(atribute.equalsIgnoreCase(FINISHED_ARRAY_COLOR))
            finishedArrayColor = colorSelection.getColor();
    }

    public void reInitialise(Object data)
    {
        initialise((int[])(int[])data);
        i = j = val = -10;
        valPosition = finalPosition = elementPosition = -10;
        setPosition("");
    }

    protected void removeAllAnimationRequests()
    {
        moveRequests.removeAllElements();
    }

    protected void removeAllQuestions()
    {
    }

    protected void run()
    {
        setPosition("1");
        i = 1;
        recordOfI = i;
        paintActiveValEarly = false;
        dontPaintValMarker = true;
        if(!((InsertionSortThread)algorithmThread).getWindow().isExpanded("3.1"))
            paintActiveValEarly = true;
        setPosition("3");
        do
        {
            if(i >= data.length)
                break;
            paintProcessedArrayI = false;
            j = -10;
            if(!((InsertionSortThread)algorithmThread).getWindow().isExpanded("3.1"))
                dontPaintJMarker = true;
            val = data[i];
            valPosition = i;
            setPosition("3.1.1.1");
            j = i;
            if(((InsertionSortThread)algorithmThread).getWindow().isExpanded("3.1.2") && ((InsertionSortThread)algorithmThread).getWindow().isExpanded("3.1"))
                dontPaintJMarker = false;
            else
                dontPaintJMarker = true;
            dontPaintVerticalBarJ = false;
            setPosition("3.1.2.1");
            if(((InsertionSortThread)algorithmThread).getWindow().isExpanded("3.1.2.2") && ((InsertionSortThread)algorithmThread).getWindow().isExpanded("3.1"))
                dontPaintJMarker = false;
            else
                dontPaintJMarker = true;
            dontPaintValMarker = false;
            elementPosition = j - 1;
            setPosition("3.1.2.2.a");
            while(j > 0 && data[j - 1] > val) 
            {
                if(dontPaintVerticalBarJ)
                    dontPaintVerticalBarJ = false;
                swap(j, j - 1);
                valPosition = -10;
                dontPaintValMarker = true;
                elementPosition = -10;
                setPosition("3.1.2.2.1");
                j--;
                if(((InsertionSortThread)algorithmThread).getWindow().isExpanded("3.1.2.2"))
                    dontPaintVerticalBarJ = true;
                paintProcessedArrayI = true;
                setPosition("3.1.2.2.2");
                dontPaintValMarker = false;
                elementPosition = j - 1;
                setPosition("3.1.2.2.a");
            }
            dontPaintValMarker = true;
            elementPosition = -10;
            setPosition("3.1.2.2.3");
            dontPaintVerticalBarJ = false;
            finalPosition = j;
            data[j] = val;
            if(((InsertionSortThread)algorithmThread).getWindow().isExpanded("3.1.3") && ((InsertionSortThread)algorithmThread).getWindow().isExpanded("3.1"))
                dontPaintJMarker = false;
            else
                dontPaintJMarker = true;
            setPosition("3.1.3.1");
            finalPosition = -10;
            val = -10;
            j = -10;
            i++;
            recordOfI = i;
            paintProcessedArrayI = false;
            if(!((InsertionSortThread)algorithmThread).getWindow().isExpanded("3.1"))
                paintActiveValEarly = true;
            else
                paintActiveValEarly = false;
            if(i != data.length)
                setPosition("3");
        } while(true);
        recordOfI = data.length;
        i = -10;
        finished = true;
    }

    protected void swap(int j, int i)
    {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
        String tempLabel = duplicateLabels[i];
        duplicateLabels[i] = duplicateLabels[j];
        duplicateLabels[j] = tempLabel;
        if(i != j)
            moveRequests.addElement(new SwapRequest(j, i));
    }

    public static final int MARKER_NOT_READY = -10;
    public static Color activeArrayColor;
    protected static final String ARRAY_COLOR = Messages.getString("InsertionSort.0");
    protected static final String ACTIVE_ARRAY_COLOR = Messages.getString("InsertionSort.1");
    protected static final String PROCESSED_ARRAY_COLOR = Messages.getString("InsertionSort.2");
    protected static final String FINISHED_ARRAY_COLOR = Messages.getString("InsertionSort.3");
    protected static boolean dontPaintJMarker = false;
    protected static boolean dontPaintValMarker = false;
    protected static boolean dontPaintElementMarker = false;
    protected static boolean dontPaintVerticalBarJ = false;
    protected static boolean paintProcessedArrayI = false;
    protected static boolean paintActiveValEarly = false;
    protected static Vector moveRequests = new Vector();
    protected int data[];
    protected int recordOfI;
    protected int recordOfJ;
    protected String duplicateLabels[];
    protected int i;
    protected int j;
    protected int val;
    protected int valPosition;
    protected int finalPosition;
    protected int elementPosition;
    protected Color textColor;
    protected Color arrayColor;
    protected Color processedArrayColor;
    protected Color finishedArrayColor;
    protected Color backgroundColor;
    protected boolean finished;

    static 
    {
        activeArrayColor = Color.red;
    }
}
