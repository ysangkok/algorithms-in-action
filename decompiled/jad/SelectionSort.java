// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SelectionSort.java

import com.cim.AIA.*;
import java.awt.*;
import java.util.Vector;
import localization.Messages;

public class SelectionSort extends Algorithm
    implements ColorSelectionListener
{

    public SelectionSort(AlgorithmThread algorithmThread, Object data)
    {
        super(algorithmThread, data);
        recordOfI = 0;
        swapRequests = new Vector();
        questions = new Vector();
        i = -10;
        j = -10;
        minPosition = -10;
        textColor = Color.black;
        arrayColor = Color.blue;
        finishedArrayColor = Color.gray;
        backgroundColor = Color.white;
        initialise((int[])(int[])data);
        ConfigurationManager cm = ConfigurationManager.getConfigurationManager();
        ColorSelection arrayColorSelection = new ColorSelection(arrayColor, ARRAY_COLOR);
        arrayColorSelection.addClass(this);
        ColorSelection finishedArrayColorSelection = new ColorSelection(finishedArrayColor, FINISHED_ARRAY_COLOR);
        finishedArrayColorSelection.addClass(this);
        cm.addColorSelection(arrayColorSelection);
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
        Vector questionsToAsk = new Vector();
        for(int i = 0; i < questions.size(); i++)
        {
            SwapRequest swapRequest = (SwapRequest)questions.elementAt(i);
            questionsToAsk.addElement(new SelectionSwapQuestion(swapRequest.getFirstId(), swapRequest.getSecondId()));
        }

        return questionsToAsk;
    }

    public MultipleTween generateTweens(Paintable paintable, Object parameter, int numberOfSteps)
    {
        MultipleTween tweens = new MultipleTween(paintable);
        ElementArray elementArray = (ElementArray)(ElementArray)parameter;
        for(int i = 0; i < swapRequests.size(); i++)
        {
            SwapRequest swapRequest = (SwapRequest)swapRequests.elementAt(i);
            VerticalBar first = (VerticalBar)(VerticalBar)elementArray.getElement(swapRequest.getFirstId());
            VerticalBar second = (VerticalBar)(VerticalBar)elementArray.getElement(swapRequest.getSecondId());
            tweens.add(new MoveTween(null, first, second.getPosition(), first.getPosition(), false, numberOfSteps));
            tweens.add(new MoveTween(null, second, first.getPosition(), second.getPosition(), false, numberOfSteps));
        }

        return tweens;
    }

    public String getClassName()
    {
        return "SelectionSortCanvas";
    }

    public ElementArray getElementArray(Graphics g)
    {
        ElementArray elementArray = new ElementArray(0, 0);
        elementArray.setElementWidth(g.getFontMetrics().stringWidth("8") + 2);
        elementArray.setAllHaveSameWidth(true);
        for(int i = 0; i < data.length; i++)
        {
            VerticalBar verticalBar = new VerticalBar(i, data[i], arrayColor, new Point(0, 0));
            if(i < recordOfI)
                verticalBar.setColor(finishedArrayColor);
            verticalBar.setMarkersAboveValue(false);
            verticalBar.setTextColor(textColor);
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

    public int getI()
    {
        return i;
    }

    public int getJ()
    {
        return j;
    }

    public int getMinPosition()
    {
        return minPosition;
    }

    protected boolean hasQuestions()
    {
        return questions.size() > 0;
    }

    protected void initialise(int data[])
    {
        this.data = data;
        createDuplicateLabels();
        i = -10;
        j = -10;
        recordOfI = 0;
        minPosition = -10;
    }

    protected void makeQuestion(int i)
    {
        int minPosition = i;
        for(int j = i; j < data.length; j++)
            if(data[j] < data[minPosition])
                minPosition = j;

        questions.addElement(new SwapRequest(i, minPosition));
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
        if(atribute.equalsIgnoreCase(FINISHED_ARRAY_COLOR))
            finishedArrayColor = colorSelection.getColor();
    }

    public void reInitialise(Object data)
    {
        initialise((int[])(int[])data);
    }

    protected void removeAllAnimationRequests()
    {
        swapRequests.removeAllElements();
    }

    protected void removeAllQuestions()
    {
        questions.removeAllElements();
    }

    protected void run()
    {
        setPosition("1");
        for(i = 0; i < data.length; i++)
        {
            recordOfI = i;
            makeQuestion(i);
            askQuestionsWithoutSetPosition();
            setPosition("2");
            minPosition = i;
            setPosition("3");
            setPosition("4");
            for(j = i + 1; j < data.length; j++)
            {
                setPosition("5");
                setPosition("6");
                if(data[j] < data[minPosition])
                {
                    minPosition = j;
                    setPosition("7");
                }
            }

            setPosition("5");
            j = -10;
            setPosition("8");
            setPosition("10");
            swap(i, minPosition);
            setPosition("13");
            minPosition = -10;
        }

        recordOfI = data.length;
        i = -10;
        setPosition("15");
    }

    protected void swap(int v, int w)
    {
        int temp = data[v];
        data[v] = data[w];
        data[w] = temp;
        String tempLabel = duplicateLabels[v];
        duplicateLabels[v] = duplicateLabels[w];
        duplicateLabels[w] = tempLabel;
        if(v != w)
            swapRequests.addElement(new SwapRequest(v, w));
    }

    public static final int MARKER_NOT_READY = -10;
    protected static final String ARRAY_COLOR = Messages.getString("SelectionSort.0");
    protected static final String FINISHED_ARRAY_COLOR = Messages.getString("SelectionSort.1");
    protected int data[];
    protected int recordOfI;
    protected Vector swapRequests;
    protected Vector questions;
    protected String duplicateLabels[];
    protected int i;
    protected int j;
    protected int minPosition;
    protected Color textColor;
    protected Color arrayColor;
    protected Color finishedArrayColor;
    protected Color backgroundColor;

}
