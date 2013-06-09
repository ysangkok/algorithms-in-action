// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KMP.java

import com.cim.AIA.*;
import java.awt.Color;
import java.awt.Point;
import java.util.Vector;
import localization.Messages;

public class KMP extends Algorithm
    implements ColorSelectionListener
{

    public KMP(AlgorithmThread algorithmThread, Object data)
    {
        super(algorithmThread, data);
        kmpMoveRequest = new Vector();
        textColor = Color.black;
        highlightColor = Color.yellow;
        nodeColor = Color.orange;
        background = Color.white;
        drawSymbolArrayAndLines = true;
        this.data = (String[])(String[])data;
        initialise();
        currentVariation = 1;
        ConfigurationManager cm = ConfigurationManager.getConfigurationManager();
        ColorSelection highlightSelection = new ColorSelection(highlightColor, HIGHLIGHT_COLOR);
        highlightSelection.addClass(this);
        cm.addColorSelection(highlightSelection);
        ColorSelection nodeSelection = new ColorSelection(nodeColor, NODE_COLOR);
        nodeSelection.addClass(this);
        cm.addColorSelection(nodeSelection);
        cm.addColorSelectionListener(this);
    }

    protected void animMove(Point from, Point to)
    {
        willAnim = true;
        animFrom = from;
        animTo = to;
    }

    protected void calculateNextTable(String pattern)
    {
        int M = pattern.length();
        fakeNextTable[0] = -1;
        nextTable[0] = -1;
        int i = 0;
        int j = -1;
        while(i < M) 
        {
            for(; j >= 0 && pattern.charAt(i) != pattern.charAt(j); j = nextTable[j]);
            i++;
            j++;
            nextTable[i] = j;
            fakeNextTable[i] = j;
        }
    }

    protected void calculateOptNextTable(String pattern)
    {
        int M = pattern.length();
        nextTable[0] = -1;
        int i = 0;
        int j = -1;
        while(i < M) 
        {
            for(; j >= 0 && pattern.charAt(i) != pattern.charAt(j); j = nextTable[j]);
            i++;
            j++;
            if(i < M && pattern.charAt(i) == pattern.charAt(j))
                nextTable[i] = nextTable[j];
            else
                nextTable[i] = j;
        }
    }

    protected Vector generateQuestions()
    {
        return null;
    }

    public MultipleTween generateTweens(Paintable paintable, Object parameter, int numberOfSteps)
    {
        MultipleTween tweens = new MultipleTween(paintable);
        for(int i = 0; i < kmpMoveRequest.size(); i++)
        {
            KMPMoveRequest temp = (KMPMoveRequest)kmpMoveRequest.elementAt(i);
            tweens.add(new MoveTween(null, temp.theString, temp.from, temp.to, true, numberOfSteps));
        }

        if(willAnim)
            tweens.add(new MoveTween(null, animNode, animFrom, animTo, true, numberOfSteps));
        return tweens;
    }

    public Node getAnimNode()
    {
        return animNode;
    }

    public String getClassName()
    {
        return "KMP";
    }

    public Node getIPointer()
    {
        if(showIPointer)
        {
            iPointer = new Node((new StringBuilder()).append("").append(theI).toString(), 0);
            return iPointer;
        } else
        {
            return null;
        }
    }

    public Node getJPointer()
    {
        if(showJPointer)
        {
            jPointer = new Node((new StringBuilder()).append("").append(theJ).toString(), 0);
            if(highlightJPointer)
                jPointer.highlight();
            return jPointer;
        } else
        {
            return null;
        }
    }

    public int getKMPResult()
    {
        return kmpResult;
    }

    public KMPString getKMPString1()
    {
        return theKMPString1;
    }

    public KMPString getKMPString2()
    {
        return theKMPString2;
    }

    public KMPNextTable getNextTable()
    {
        return theNextTable;
    }

    public KMPString getTestKMPString1()
    {
        return testKMPString1;
    }

    public KMPString getTestKMPString2()
    {
        return testKMPString2;
    }

    protected boolean hasQuestions()
    {
        return false;
    }

    protected void initialise()
    {
        theKMPString1 = new KMPString((new StringBuilder()).append(data[0]).append(" ").toString());
        theKMPString2 = new KMPString((new StringBuilder()).append(data[1]).append(" ").toString());
        theNextTable = null;
        testKMPString1 = null;
        testKMPString1 = null;
        jPointer = null;
        iPointer = null;
        animNode = null;
        showJPointer = false;
        showIPointer = false;
        highlightJPointer = false;
        willAnim = false;
        kmpResult = -1;
    }

    protected void kmpMove(KMPString theString, int deltaX, int deltaY)
    {
        KMPMoveRequest temp = new KMPMoveRequest(theString, theString.getX(), theString.getY(), theString.getX() + deltaX, theString.getY() + deltaY);
        theString.setLocation(theString.getX() + deltaX, theString.getY() + deltaY);
        kmpMoveRequest.addElement(temp);
    }

    protected void kmpNoOpt()
    {
        String text = data[1];
        String pattern = data[0];
        int size = pattern.length();
        int N = text.length();
        setPosition("0");
        theNextTable = new KMPNextTable(size);
        fakeNextTable = new int[size + 1];
        nextTable = new int[size + 1];
        calculateNextTable(pattern);
        theNextTable.highlightIndex();
        theNextTable.setLabelIndex(0);
        theNextTable.set(0, -1);
        setPosition("1.2.1");
        for(int count = 1; count < size; count++)
        {
            theNextTable.setLabelIndex(count);
            setPosition("1.2.2");
            testKMPString1 = new KMPString(pattern.substring(0, count));
            testKMPString2 = new KMPString(pattern.substring(0, count));
            theKMPString1.highlight(count - 1);
            setPosition("1.2.2.1");
            kmpMove(testKMPString2, 20, 0);
            setPosition("1.2.2.2.0");
            testKMPString1.setHighlightColor(new Color(255, 90, 90));
            testKMPString2.setHighlightColor(new Color(255, 90, 90));
            for(int ntemp = 1; ntemp < count - fakeNextTable[count]; ntemp++)
            {
                testKMPString2.highlight(0, testKMPString2.length - ntemp);
                testKMPString1.highlight(ntemp, testKMPString1.length);
                setPosition("1.2.2.2.1");
                kmpMove(testKMPString2, 20, 0);
                testKMPString1.unHighlightAll();
                testKMPString2.unHighlightAll();
                setPosition("1.2.2.2.2");
            }

            testKMPString1.setHighlightColor(Color.green);
            testKMPString2.setHighlightColor(Color.green);
            if(nextTable[count] > 0)
            {
                testKMPString2.highlight(0, nextTable[count]);
                testKMPString1.highlight(testKMPString1.length() - nextTable[count], testKMPString1.length());
            }
            setPosition("1.2.2.2.1");
            setPosition("1.2.2.2.3");
            theNextTable.set(count, nextTable[count]);
            testKMPString1.unHighlightAll();
            testKMPString2.unHighlightAll();
            setPosition("1.2.2.3");
        }

        theNextTable.unHighlightIndex();
        setPosition("1.2.2");
        theKMPString1.unHighlightAll();
        testKMPString1 = null;
        testKMPString2 = null;
        removeAllAnimationRequests();
        setPosition("1.2.3");
    }

    protected void kmpSearch()
    {
        String text = data[1];
        String pattern = data[0];
        int size = pattern.length();
        int N = text.length();
        int M = pattern.length();
        theKMPString2.setLabel("i");
        theKMPString2.setShowLabel(true);
        theKMPString2.setHighLabel(false);
        theKMPString2.setLabelIndex(0);
        int i = 0;
        setPosition("2.1.1");
        theKMPString1.setLabel("j");
        theKMPString1.setShowLabel(true);
        theKMPString1.setLabelIndex(0);
        int j = 0;
        showJPointer = true;
        theJ = j;
        setPosition("2.1.2");
        while(i < N && j < M) 
        {
            setPosition("2.3");
            if(text.charAt(i) != pattern.charAt(j))
            {
                theNextTable.highlightIndex();
                theNextTable.setLabelIndex(j);
                theKMPString1.setHighlightColor(new Color(255, 90, 90));
                theKMPString2.setHighlightColor(new Color(255, 90, 90));
                theKMPString1.highlight(j);
                theKMPString2.highlight(i);
                setPosition("2.3.1");
                theNextTable.highlight(j);
                int newJ = nextTable[j];
                theJ = newJ;
                theKMPString1.setLabelIndex(newJ);
                animNode = new Node((new StringBuilder()).append("").append(newJ).toString(), 0);
                animNode.setPosition(theNextTable.getNodePosition(j));
                if(jPointer != null)
                    animMove(theNextTable.getNodePosition(j), jPointer.getPosition());
                setPosition("2.3.1.2.1");
                kmpMove(theKMPString1, (j - newJ) * 20, 0);
                j = newJ;
                theKMPString1.unHighlightAll();
                theKMPString2.unHighlightAll();
                theNextTable.setLabelIndex(j);
                theNextTable.unHighlightAll();
                setPosition("2.3.1.2.2");
                if(newJ == -1)
                {
                    j = 0;
                    theJ = j;
                    i++;
                    theKMPString1.setLabelIndex(j);
                    theKMPString2.setLabelIndex(i);
                    theNextTable.setLabelIndex(j);
                }
                setPosition("2.3.1.3");
            } else
            {
                theKMPString1.setHighlightColor(Color.green);
                theKMPString2.setHighlightColor(Color.green);
                theKMPString1.highlight(j);
                theKMPString2.highlight(i);
                setPosition("2.3.1");
                setPosition("2.3.2");
                i++;
                theKMPString2.setLabelIndex(i);
                j++;
                theJ = j;
                theKMPString1.setLabelIndex(j);
                setPosition("2.3.2.1.1");
            }
            theNextTable.unHighlightIndex();
            theKMPString1.unHighlightAll();
            theKMPString2.unHighlightAll();
        }
        setPosition("2.3");
        theNextTable.unHighlightIndex();
        setPosition("2.3.3");
        removeAllAnimationRequests();
        theKMPString1.setHighlightColor(Color.green);
        theKMPString2.setHighlightColor(Color.green);
        if(j == M)
        {
            setPosition("2.4.1");
            theKMPString2.highlight(i - M, i);
            kmpResult = 1;
            setPosition("2.4.1.1");
        } else
        {
            setPosition("2.4.1");
            setPosition("2.4.2");
            kmpResult = 0;
            setPosition("2.4.2.1");
        }
        showJPointer = false;
        setPosition("3");
    }

    protected void kmpWithOpt()
    {
        String text = data[1];
        String pattern = data[0];
        int size = pattern.length();
        int N = text.length();
        int M = pattern.length();
        setPosition("0");
        theNextTable = new KMPNextTable(size);
        fakeNextTable = new int[size + 1];
        nextTable = new int[size + 1];
        calculateNextTable(pattern);
        calculateOptNextTable(pattern);
        theNextTable.highlightIndex();
        theNextTable.setLabelIndex(0);
        theNextTable.set(0, -1);
        setPosition("1.2.1");
        theNextTable.setHighlightColor(Node.DEFAULT_HIGHLIGHT_COLOR);
        for(int count = 1; count < size; count++)
        {
            theNextTable.setLabelIndex(count);
            theI = count;
            setPosition("1.2.2");
            testKMPString1 = new KMPString(pattern.substring(0, count));
            testKMPString2 = new KMPString(pattern.substring(0, count));
            theKMPString1.highlight(0, count);
            showIPointer = true;
            setPosition("1.2.2.1");
            kmpMove(testKMPString2, 20, 0);
            setPosition("1.2.2.2.0");
            testKMPString1.setHighlightColor(new Color(255, 90, 90));
            testKMPString2.setHighlightColor(new Color(255, 90, 90));
            for(int ntemp = 1; ntemp < count - fakeNextTable[count]; ntemp++)
            {
                testKMPString2.highlight(0, testKMPString2.length - ntemp);
                testKMPString1.highlight(ntemp, testKMPString1.length);
                setPosition("1.2.2.2.1");
                kmpMove(testKMPString2, 20, 0);
                testKMPString1.unHighlightAll();
                testKMPString2.unHighlightAll();
                setPosition("1.2.2.2.2");
            }

            testKMPString1.setHighlightColor(Color.green);
            testKMPString2.setHighlightColor(Color.green);
            if(fakeNextTable[count] > 0)
            {
                testKMPString2.highlight(0, fakeNextTable[count]);
                testKMPString1.highlight(testKMPString1.length() - fakeNextTable[count], testKMPString1.length());
            }
            setPosition("1.2.2.2.1");
            setPosition("1.2.2.2.3");
            theJ = fakeNextTable[count];
            showJPointer = true;
            theI = count;
            setPosition("1.2.2.3");
            theKMPString1.unHighlightAll();
            if(pattern.charAt(theI) == pattern.charAt(theJ))
            {
                theKMPString1.setHighlightColor(Color.green);
                theKMPString1.highlight(count);
                theKMPString1.highlight(theJ);
                setPosition("1.2.2.5");
                theNextTable.set(count, nextTable[count]);
                theNextTable.highlight(count);
                theNextTable.highlight(theJ);
                setPosition("1.2.2.6");
            } else
            {
                theKMPString1.setHighlightColor(new Color(255, 90, 90));
                theKMPString1.highlight(count);
                theKMPString1.highlight(theJ);
                setPosition("1.2.2.5");
                setPosition("1.2.2.7");
                theNextTable.set(count, nextTable[count]);
                theNextTable.highlight(count);
                highlightJPointer = true;
                setPosition("1.2.2.8");
            }
            showJPointer = false;
            highlightJPointer = false;
            theNextTable.unHighlightAll();
            theKMPString1.setHighlightColor(Color.green);
            theKMPString1.unHighlightAll();
        }

        theNextTable.unHighlightIndex();
        setPosition("1.2.2");
        theKMPString1.unHighlightAll();
        testKMPString1 = null;
        testKMPString2 = null;
        removeAllAnimationRequests();
        showJPointer = false;
        showIPointer = false;
        setPosition("1.2.3");
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
        this.data = (String[])(String[])data;
        initialise();
    }

    protected void removeAllAnimationRequests()
    {
        kmpMoveRequest.removeAllElements();
        willAnim = false;
        animNode = null;
    }

    protected void removeAllQuestions()
    {
    }

    protected void run()
    {
        if(currentVariation == 1)
            kmpNoOpt();
        if(currentVariation == 2)
            kmpWithOpt();
        kmpSearch();
    }

    public void setVariation(int state)
    {
        currentVariation = state;
    }

    public static final int EMPTY_MARKER = -10;
    public static final int NOOPT = 1;
    public static final int WITHOPT = 2;
    public static int currentVariation;
    protected static final String HIGHLIGHT_COLOR = Messages.getString("KMP.0");
    protected static final String NODE_COLOR = Messages.getString("KMP.1");
    protected String data[];
    protected KMPString theKMPString1;
    protected KMPString theKMPString2;
    protected KMPString testKMPString1;
    protected KMPString testKMPString2;
    protected KMPNextTable theNextTable;
    protected Node jPointer;
    protected Node iPointer;
    protected Node animNode;
    protected int kmpResult;
    protected int theJ;
    protected int theI;
    protected boolean showIPointer;
    protected boolean showJPointer;
    protected boolean highlightJPointer;
    protected int nextTable[];
    protected int fakeNextTable[];
    protected Vector kmpMoveRequest;
    protected boolean willAnim;
    protected Point animFrom;
    protected Point animTo;
    protected Color textColor;
    protected Color highlightColor;
    protected Color nodeColor;
    protected Color background;
    protected boolean drawSymbolArrayAndLines;

}
