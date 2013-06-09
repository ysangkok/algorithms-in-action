// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DistributionCounting.java

import com.cim.AIA.*;
import java.awt.Color;
import java.awt.Point;
import java.util.Vector;
import localization.Messages;

public class DistributionCounting extends Algorithm
    implements ColorSelectionListener
{

    public static void main(String argv[])
    {
        int data[] = {
            2, 4, 5, 6, 6, 3, 2, 1
        };
        DistributionCounting distCount = new DistributionCounting(null, data);
        distCount.run();
    }

    public DistributionCounting(AlgorithmThread algorithmThread, Object data)
    {
        super(algorithmThread, data);
        allowGetAuxiliaryArray = false;
        dataArrow = -10;
        dataMarker = -10;
        symbolMarker = -10;
        auxiliaryMarker = -10;
        textColor = Color.black;
        highlightColor = Color.yellow;
        nodeColor = Color.orange;
        background = Color.white;
        drawSymbolArrayAndLines = true;
        this.data = (int[])(int[])data;
        initialise();
        ConfigurationManager cm = ConfigurationManager.getConfigurationManager();
        ColorSelection highlightSelection = new ColorSelection(highlightColor, HIGHLIGHT_COLOR);
        highlightSelection.addClass(this);
        cm.addColorSelection(highlightSelection);
        ColorSelection nodeSelection = new ColorSelection(nodeColor, NODE_COLOR);
        nodeSelection.addClass(this);
        cm.addColorSelection(nodeSelection);
        cm.addColorSelectionListener(this);
    }

    public synchronized void calculateLines(Paintable paintable, ElementArray symbolElementArray, ElementArray auxiliaryElementArray)
    {
        if(!drawSymbolArrayAndLines)
            return;
        for(int i = 0; i < symbolElementArray.getSize(); i++)
        {
            Node from = (Node)symbolElementArray.getElement(i);
            if(from == null || !from.getShowObject())
                continue;
            Element to = auxiliaryElementArray.getElement(((Integer)(Integer)from.getObject()).intValue());
            if(to == null)
                continue;
            Line line = new Line(0, 0, 0, 0);
            Point fromPoint = new Point(from.getPosition().x + from.getWidth() / 2, from.getPosition().y + from.getHeight());
            Point toPoint = new Point(to.getPosition().x + to.getWidth() / 2, to.getPosition().y);
            line.setStartPosition(fromPoint);
            line.setEndPosition(toPoint);
            line.showArrowHead(true);
            line.setDistanceFromStartForArrowHead(-3);
            if(paintable != null)
                paintable.addDrawable(line);
        }

    }

    protected Vector generateQuestions()
    {
        return null;
    }

    public MultipleTween generateTweens(Paintable paintable, Object parameter, int numberOfSteps)
    {
        return null;
    }

    public ElementArray getAuxiliaryElementArray()
    {
        if(!allowGetAuxiliaryArray)
            return null;
        if(auxiliary == null || auxiliary.length <= 0)
            return null;
        ElementArray elementArray = new ElementArray(0, 0);
        elementArray.setColumGap(0);
        elementArray.setElementWidth(20);
        for(int i = 0; i < auxiliary.length; i++)
        {
            Node node = new Node(new Integer(auxiliary[i]), i);
            node.setBackgroundColor(nodeColor);
            node.setTextColor(textColor);
            if(auxiliary[i] == -10)
                node.showObject(false);
            if(auxiliaryMarker != -10 && auxiliaryMarker == i)
            {
                node.setHighlightColor(highlightColor);
                node.highlight();
            }
            node.addMarker(new StringMarker((new StringBuilder()).append(i).append("").toString(), new Point(), textColor, background));
            if(auxiliaryDuplicateLabels[i] != null)
                node.addMarker(new StringMarker((new StringBuilder()).append(auxiliaryDuplicateLabels[i]).append("").toString(), new Point(), textColor, background));
            elementArray.setValue(i, node);
        }

        return elementArray;
    }

    public String getClassName()
    {
        return Messages.getString("DistributionCounting.2");
    }

    public int getDataArrow()
    {
        return dataArrow;
    }

    public ElementArray getDataElementArray()
    {
        if(data == null || data.length <= 0)
            return null;
        ElementArray elementArray = new ElementArray(0, 0);
        elementArray.setColumGap(0);
        elementArray.setElementWidth(20);
        for(int i = 0; i < data.length; i++)
        {
            Node node = new Node(new Integer(data[i]), i);
            node.setBackgroundColor(nodeColor);
            node.setTextColor(textColor);
            if(data[i] == -10)
                node.showObject(false);
            if(dataMarker != -10 && dataMarker == i)
            {
                node.setHighlightColor(highlightColor);
                node.highlight();
            }
            node.addMarker(new StringMarker((new StringBuilder()).append(i).append("").toString(), new Point(), textColor, background));
            if(dataDuplicateLabels[i] != null)
                node.addMarker(new StringMarker((new StringBuilder()).append(dataDuplicateLabels[i]).append("").toString(), new Point(), textColor, background));
            elementArray.setValue(i, node);
        }

        return elementArray;
    }

    public ElementArray getSymbolElementArray()
    {
        if(symbolArray == null || symbolArray.length <= 0)
            return null;
        if(!drawSymbolArrayAndLines)
            return null;
        ElementArray elementArray = new ElementArray(0, 0);
        elementArray.setColumGap(0);
        elementArray.setElementWidth(20);
        for(int i = 0; i < symbolArray.length; i++)
        {
            Node node = new Node(new Integer(symbolArray[i]), i);
            node.setBackgroundColor(nodeColor);
            node.setTextColor(textColor);
            if(symbolMarker != -10 && symbolMarker == i)
            {
                node.setHighlightColor(highlightColor);
                node.highlight();
            }
            if(symbolArray[i] == -10)
                node.showObject(false);
            node.addMarker(new StringMarker((new StringBuilder()).append(i).append("").toString(), new Point(), textColor, background));
            node.setMarkersBelowValue(false);
            elementArray.setValue(i, node);
        }

        return elementArray;
    }

    protected boolean hasQuestions()
    {
        return false;
    }

    protected void initialise()
    {
        allowGetAuxiliaryArray = false;
        if(data != null && data.length > 0)
        {
            int max = data[0];
            for(int i = 0; i < data.length; i++)
                if(data[i] > max)
                    max = data[i];

            symbolArray = new int[max + 1];
        }
        for(int i = 0; i < symbolArray.length; i++)
            symbolArray[i] = -10;

        auxiliary = new int[data.length];
        for(int i = 0; i < auxiliary.length; i++)
            auxiliary[i] = -10;

        drawSymbolArrayAndLines = true;
        Integer temp[] = new Integer[data.length];
        for(int i = 0; i < data.length; i++)
            temp[i] = new Integer(data[i]);

        dataDuplicateLabels = DuplicateLabel.createDuplicateLabels(temp, true);
        auxiliaryDuplicateLabels = new String[dataDuplicateLabels.length];
    }

    public void processColorSelection(ColorSelection colorSelection)
    {
        String atribute = colorSelection.getAtributeName();
        if(atribute.equalsIgnoreCase(ColorSelection.BACKGROUND))
            background = colorSelection.getColor();
        else
        if(atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR))
            textColor = colorSelection.getColor();
        else
        if(atribute.equalsIgnoreCase(HIGHLIGHT_COLOR))
            highlightColor = colorSelection.getColor();
        else
        if(atribute.equalsIgnoreCase(NODE_COLOR))
            nodeColor = colorSelection.getColor();
    }

    public void reInitialise(Object data)
    {
        this.data = (int[])(int[])data;
        initialise();
    }

    protected void removeAllAnimationRequests()
    {
    }

    protected void removeAllQuestions()
    {
    }

    protected void run()
    {
        setPosition("0");
        setPosition("1");
        setPosition("1.1");
        for(int j = 0; j < symbolArray.length; j++)
        {
            symbolMarker = j;
            setPosition("1.1.1");
            symbolArray[j] = 0;
            setPosition("1.1.1.1");
        }

        symbolMarker = -10;
        setPosition("1.1.2");
        setPosition("1.2");
        for(int i = 0; i < data.length; i++)
        {
            dataArrow = i;
            setPosition("1.2.1");
            symbolMarker = data[i];
            symbolArray[data[i]]++;
            setPosition("1.2.1.1");
            symbolMarker = -10;
        }

        dataArrow = -10;
        setPosition("1.2.2");
        setPosition("1.3");
        for(int j = 1; j < symbolArray.length; j++)
        {
            symbolMarker = j;
            setPosition("1.3.1");
            symbolArray[j] = symbolArray[j - 1] + symbolArray[j];
            setPosition("1.3.1.1");
        }

        setPosition("1.3.2");
        setPosition("1.4");
        for(int j = symbolArray.length - 1; j > 0; j--)
        {
            symbolMarker = j;
            setPosition("1.4.1");
            symbolArray[j] = symbolArray[j - 1];
            setPosition("1.4.1.1");
        }

        symbolMarker = 0;
        symbolArray[0] = 0;
        setPosition("1.4.2");
        symbolMarker = -10;
        allowGetAuxiliaryArray = true;
        setPosition("2");
        for(int i = 0; i < data.length; i++)
        {
            dataMarker = i;
            setPosition("2.1");
            setPosition("3.1");
            symbolMarker = data[i];
            setPosition("3.1.1");
            auxiliary[symbolArray[data[i]]] = data[i];
            auxiliaryMarker = symbolArray[data[i]];
            auxiliaryDuplicateLabels[auxiliaryMarker] = dataDuplicateLabels[i];
            setPosition("3.1.1.1");
            auxiliaryMarker = -10;
            setPosition("3.2");
            setPosition("3.2.1");
            symbolArray[data[i]]++;
            setPosition("3.2.1.1");
            symbolMarker = -10;
        }

        dataMarker = -10;
        drawSymbolArrayAndLines = false;
        setPosition("5");
        for(int i = 0; i < auxiliary.length; i++)
        {
            auxiliaryMarker = i;
            setPosition("5.1");
            dataMarker = i;
            data[i] = auxiliary[i];
            dataDuplicateLabels[i] = auxiliaryDuplicateLabels[i];
            setPosition("5.1.1");
            dataMarker = -10;
        }

        auxiliaryMarker = -10;
        setPosition("5.2");
    }

    public static final int EMPTY_MARKER = -10;
    protected static final String HIGHLIGHT_COLOR = Messages.getString("DistributionCounting.0");
    protected static final String NODE_COLOR = Messages.getString("DistributionCounting.1");
    protected boolean allowGetAuxiliaryArray;
    protected int data[];
    protected int symbolArray[];
    protected int auxiliary[];
    protected int dataArrow;
    protected int dataMarker;
    protected int symbolMarker;
    protected int auxiliaryMarker;
    protected Color textColor;
    protected Color highlightColor;
    protected Color nodeColor;
    protected Color background;
    protected boolean drawSymbolArrayAndLines;
    protected String dataDuplicateLabels[];
    protected String auxiliaryDuplicateLabels[];

}
