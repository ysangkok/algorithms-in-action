// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StraightRadixSort.java

import com.cim.AIA.*;
import java.awt.Color;
import java.awt.Point;
import java.util.Vector;
import localization.Messages;

public class StraightRadixSort extends Algorithm
    implements ColorSelectionListener
{

    public static void main(String argv[])
    {
        int data[] = {
            2, 4, 5, 6, 6, 3, 2, 1
        };
        StraightRadixSort distCount = new StraightRadixSort(null, data);
        distCount.run();
    }

    public StraightRadixSort(AlgorithmThread algorithmThread, Object data)
    {
        super(algorithmThread, data);
        allowGetAuxiliaryArray = false;
        dataArrow = -10;
        auxiliaryArrow = -10;
        dataMarker = -10;
        symbolMarker = -10;
        auxiliaryMarker = -10;
        nodeColor = Color.orange;
        textColor = Color.black;
        highlightColor = Color.yellow;
        activeColor = Color.red;
        waitingColor = Color.pink;
        processedColor = Color.gray;
        background = Color.white;
        drawSymbolArrayAndLines = true;
        bit = -10;
        bitLength = 0;
        colorStrings = (new String[] {
            NODE_COLOR, HIGHLIGHT_COLOR, WAITING_COLOR, ACTIVE_COLOR, PROCESSED_COLOR
        });
        splitMarkers = new Vector();
        this.data = (int[])(int[])data;
        Color colorSelection[] = {
            nodeColor, highlightColor, waitingColor, activeColor, processedColor
        };
        initialise();
        ConfigurationManager cm = ConfigurationManager.getConfigurationManager();
        for(int i = 0; i < colorStrings.length; i++)
        {
            ColorSelection nodeSelection = new ColorSelection(colorSelection[i], colorStrings[i]);
            nodeSelection.addClass(this);
            cm.addColorSelection(nodeSelection);
        }

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

    public int getAuxiliaryArrow()
    {
        return auxiliaryArrow;
    }

    public ElementArray getAuxiliaryElementArray()
    {
        if(!allowGetAuxiliaryArray)
            return null;
        if(auxiliary == null || auxiliary.length <= 0)
            return null;
        ElementArray elementArray = new ElementArray(0, 0);
        elementArray.setColumGap(0);
        elementArray.setElementWidth(25);
        for(int i = 0; i < auxiliary.length; i++)
        {
            Node node = new Node(new Integer(0), i);
            node.setBackgroundColor(nodeColor);
            node.setTextColor(textColor);
            node.setAdditionalMarkerSpacing(4);
            if(extraAuxiliaryData != null && extraAuxiliaryData[i] != -10)
                node.setObject(new Integer(extraAuxiliaryData[i]));
            if(auxiliary[i] == -10)
                node.showObject(false);
            if(auxiliaryMarker != -10 && auxiliaryMarker == i)
            {
                node.setHighlightColor(highlightColor);
                node.highlight();
            }
            node.addMarker(new StringMarker((new StringBuilder()).append(i).append("").toString(), new Point(), textColor, background));
            if(auxiliaryDuplicateLabels != null && auxiliaryDuplicateLabels[i] != null)
                node.addMarker(new StringMarker((new StringBuilder()).append(auxiliaryDuplicateLabels[i]).append("").toString(), new Point(), textColor, background));
            if(extraAuxiliaryData != null && extraAuxiliaryData[i] != -10)
            {
                for(int j = 0; j < auxiliaryDataToSort[i].length; j++)
                {
                    Color color = getColor(j);
                    node.addMarker(new StringMarker((new StringBuilder()).append(auxiliaryDataToSort[i][j]).append("").toString(), new Point(), textColor, color));
                }

            }
            elementArray.setValue(i, node);
        }

        return elementArray;
    }

    public int getBit()
    {
        return bit;
    }

    protected int[] getBit(int number, int bitLength)
    {
        String binaryString = "";
        for(int i = 0; i < bitLength; i++)
            binaryString = (new StringBuilder()).append(binaryString).append("0").toString();

        String binary = Integer.toBinaryString(number);
        binaryString = (new StringBuilder()).append(binaryString.substring(0, binaryString.length() - binary.length())).append(binary).toString();
        int tempData[] = new int[bitLength];
        for(int i = 0; i < binaryString.length(); i++)
        {
            String numberString = (new Character(binaryString.charAt(i))).toString();
            try
            {
                int temp = Integer.parseInt(numberString);
                tempData[i] = temp;
            }
            catch(NumberFormatException e)
            {
                displayMessage((new StringBuilder()).append("RadixExchangeSort.getBit Error in ").append(numberString).append(" ").append(e).toString());
                return null;
            }
        }

        return tempData;
    }

    protected int getBit(int number, int position, int bitLength)
    {
        int temp[] = getBit(number, bitLength);
        if(temp != null && position >= 0 && position < temp.length)
            return temp[position];
        else
            return -1;
    }

    public int getBitLength()
    {
        return bitLength;
    }

    public String getClassName()
    {
        return "StraightRadixSort";
    }

    public Color getColor(int bit)
    {
        Color color = background;
        if(bit >= 0 && bit < colors.length)
            switch(colors[bit])
            {
            default:
                break;

            case 1: // '\001'
                color = background;
                break;

            case 2: // '\002'
                color = waitingColor;
                break;

            case 3: // '\003'
                color = activeColor;
                break;

            case 4: // '\004'
                color = waitingColor;
                if(this.bit < 0)
                    color = processedColor;
                break;
            }
        return color;
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
        elementArray.setColumGap(5);
        elementArray.setElementWidth(20);
        for(int i = 0; i < dataToSort.length; i++)
        {
            VerticalBar element = new VerticalBar(i, data[i], nodeColor, Color.black, new Point(0, 0));
            element.setTextColor(textColor);
            element.setDrawValueMarker(false);
            element.setAdditionalMarkerSpacing(4);
            element.setThickness(2);
            element.setScaleFactor(5F);
            if(dataMarker != -10 && i == dataMarker)
                element.setColor(highlightColor);
            element.addMarker(new StringMarker((new StringBuilder()).append(data[i]).append("").toString(), new Point(), textColor, background));
            if(dataDuplicateLabels != null && dataDuplicateLabels[i] != null)
                element.addMarker(new StringMarker((new StringBuilder()).append(dataDuplicateLabels[i]).append("").toString(), new Point(), textColor, background));
            for(int j = 0; j < dataToSort[i].length; j++)
            {
                Color color = getColor(j);
                element.addMarker(new StringMarker((new StringBuilder()).append(dataToSort[i][j]).append("").toString(), new Point(), textColor, color));
            }

            elementArray.setValue(i, element);
        }

        return elementArray;
    }

    public Vector getSplitValues()
    {
        return splitMarkers;
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
        splitMarkers = new Vector();
        int maxPosition = 0;
        if(data != null && data.length > 0)
        {
            for(int i = 0; i < data.length; i++)
                if(data[i] > data[maxPosition])
                    maxPosition = i;

        }
        symbolArray = new int[2];
        if(data.length > 0)
            bitLength = Integer.toBinaryString(data[maxPosition]).length();
        dataToSort = new int[data.length][];
        for(int i = 0; i < dataToSort.length; i++)
            dataToSort[i] = new int[bitLength];

        for(int i = 0; i < dataToSort.length; i++)
            dataToSort[i] = getBit(data[i], bitLength);

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
        bit = -10;
        colors = new int[bitLength];
        for(int i = 0; i < colors.length; i++)
            colors[i] = 1;

    }

    public void processColorSelection(ColorSelection colorSelection)
    {
        String atribute = colorSelection.getAtributeName();
        for(int i = 0; i < colorStrings.length; i++)
            if(atribute.equalsIgnoreCase(colorStrings[i]))
                switch(i)
                {
                case 0: // '\0'
                    nodeColor = colorSelection.getColor();
                    break;

                case 1: // '\001'
                    highlightColor = colorSelection.getColor();
                    break;

                case 2: // '\002'
                    waitingColor = colorSelection.getColor();
                    break;

                case 3: // '\003'
                    activeColor = colorSelection.getColor();
                    break;

                case 4: // '\004'
                    processedColor = colorSelection.getColor();
                    break;
                }

        if(atribute.equalsIgnoreCase(ColorSelection.BACKGROUND))
            background = colorSelection.getColor();
        else
        if(atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR))
            textColor = colorSelection.getColor();
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
        for(int i = 0; i < colors.length; i++)
            colors[i] = 2;

        setPosition("0");
        for(bit = bitLength - 1; bit >= 0; bit--)
        {
            colors[bit] = 3;
            drawSymbolArrayAndLines = true;
            allowGetAuxiliaryArray = false;
            auxiliaryDuplicateLabels = new String[dataDuplicateLabels.length];
            extraAuxiliaryData = new int[dataDuplicateLabels.length];
            auxiliaryDataToSort = new int[dataDuplicateLabels.length][];
            for(int i = 0; i < auxiliaryDataToSort.length; i++)
                auxiliaryDataToSort[i] = new int[bitLength];

            for(int i = 0; i < extraAuxiliaryData.length; i++)
                extraAuxiliaryData[i] = -10;

            setPosition("6");
            setPosition("6.1");
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
            for(int i = 0; i < dataToSort.length; i++)
            {
                dataArrow = i;
                setPosition("1.2.1");
                symbolMarker = dataToSort[i][bit];
                symbolArray[dataToSort[i][bit]]++;
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

            symbolMarker = -10;
            setPosition("1.3.2");
            setPosition("1.4");
            symbolArray[1] = symbolArray[0];
            symbolMarker = 1;
            setPosition("1.4.1");
            symbolArray[0] = 0;
            symbolMarker = 0;
            setPosition("1.4.2");
            symbolMarker = -10;
            allowGetAuxiliaryArray = true;
            setPosition("2");
            for(int i = 0; i < dataToSort.length; i++)
            {
                dataArrow = i;
                setPosition("2.1");
                setPosition("3.1");
                symbolMarker = dataToSort[i][bit];
                setPosition("3.1.1");
                auxiliary[symbolArray[dataToSort[i][bit]]] = dataToSort[i][bit];
                auxiliaryMarker = symbolArray[dataToSort[i][bit]];
                auxiliaryDuplicateLabels[auxiliaryMarker] = dataDuplicateLabels[i];
                extraAuxiliaryData[auxiliaryMarker] = data[i];
                for(int j = 0; j < dataToSort[i].length; j++)
                    auxiliaryDataToSort[auxiliaryMarker][j] = dataToSort[i][j];

                setPosition("3.1.1.1");
                auxiliaryMarker = -10;
                setPosition("3.2");
                setPosition("3.2.1");
                symbolArray[dataToSort[i][bit]]++;
                setPosition("3.2.1.1");
                symbolMarker = -10;
            }

            dataArrow = -10;
            dataMarker = -10;
            drawSymbolArrayAndLines = false;
            for(int i = 0; i < symbolArray.length; i++)
                symbolArray[i] = -10;

            splitMarkers.removeAllElements();
            setPosition("5");
            for(int i = 0; i < auxiliary.length; i++)
            {
                auxiliaryMarker = i;
                auxiliaryArrow = i;
                setPosition("5.1");
                dataMarker = i;
                dataToSort[i] = getBit(extraAuxiliaryData[i], bitLength);
                dataDuplicateLabels[i] = auxiliaryDuplicateLabels[i];
                data[i] = extraAuxiliaryData[i];
                setPosition("5.1.1");
                auxiliaryMarker = -10;
                dataMarker = -10;
            }

            auxiliaryArrow = -10;
            auxiliaryMarker = -10;
            setPosition("5.2");
            for(int i = 0; i < auxiliary.length; i++)
                auxiliary[i] = -10;

            colors[bit] = 4;
            allowGetAuxiliaryArray = false;
            for(int b = bit; b < bitLength; b++)
            {
                int lastValue = -1;
                for(int i = 0; i < dataToSort.length; i++)
                {
                    if(lastValue == -1)
                    {
                        lastValue = dataToSort[i][b];
                        continue;
                    }
                    if(dataToSort[i][b] != lastValue)
                    {
                        splitMarkers.addElement(new SplitMarker(b, i));
                        lastValue = dataToSort[i][b];
                    }
                }

            }

            setPosition("7");
        }

        setPosition("8");
    }

    public static final int MARKER_SPACING = 4;
    public static final int BORDER_THICKNESS = 2;
    public static final int SCALE_FACTOR = 5;
    public static final int COLUM_GAP = 5;
    public static final int COLUM_WIDTH = 20;
    public static final int ADDITIONAL_MARKERS = 2;
    protected static final int CLEAR = 1;
    protected static final int WAITING = 2;
    protected static final int ACTIVE = 3;
    protected static final int PROCESSED = 4;
    public static final int EMPTY_MARKER = -10;
    protected static final String HIGHLIGHT_COLOR = Messages.getString("StraightRadixSort.0");
    protected static final String NODE_COLOR = Messages.getString("StraightRadixSort.1");
    protected static final String ACTIVE_COLOR = Messages.getString("StraightRadixSort.2");
    protected static final String WAITING_COLOR = Messages.getString("StraightRadixSort.3");
    protected static final String PROCESSED_COLOR = Messages.getString("StraightRadixSort.4");
    protected boolean allowGetAuxiliaryArray;
    protected int data[];
    protected int dataToSort[][];
    protected int symbolArray[];
    protected int auxiliary[];
    protected int dataArrow;
    protected int auxiliaryArrow;
    protected int dataMarker;
    protected int symbolMarker;
    protected int auxiliaryMarker;
    protected Color nodeColor;
    protected Color textColor;
    protected Color highlightColor;
    protected Color activeColor;
    protected Color waitingColor;
    protected Color processedColor;
    protected Color background;
    protected boolean drawSymbolArrayAndLines;
    protected String dataDuplicateLabels[];
    protected String auxiliaryDuplicateLabels[];
    protected int extraAuxiliaryData[];
    protected int auxiliaryDataToSort[][];
    protected int bit;
    protected int colors[];
    protected int bitLength;
    protected String colorStrings[];
    protected Vector splitMarkers;

}
