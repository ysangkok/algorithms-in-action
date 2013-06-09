// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BoyerMoore.java

import com.cim.AIA.*;
import java.awt.Color;
import java.util.Vector;
import localization.Messages;

public class BoyerMoore extends Algorithm
    implements ColorSelectionListener
{

    public BoyerMoore(AlgorithmThread algorithmThread, Object data)
    {
        super(algorithmThread, data);
        BoyerMooreMoveRequest = new Vector();
        textColor = Color.black;
        highlightColor = Color.yellow;
        nodeColor = Color.orange;
        background = Color.white;
        drawSymbolArrayAndLines = true;
        this.data = (String[])(String[])data;
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

    protected void BoyerMooreMove(BoyerMooreString theString, int deltaX, int deltaY)
    {
        BoyerMooreMoveRequest temp = new BoyerMooreMoveRequest(theString, theString.getX(), theString.getY(), theString.getX() + deltaX, theString.getY() + deltaY);
        theString.setLocation(theString.getX() + deltaX, theString.getY() + deltaY);
        BoyerMooreMoveRequest.addElement(temp);
    }

    protected void calculateNextTable(String pattern)
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
        for(int i = 0; i < BoyerMooreMoveRequest.size(); i++)
        {
            BoyerMooreMoveRequest temp = (BoyerMooreMoveRequest)BoyerMooreMoveRequest.elementAt(i);
            tweens.add(new MoveTween(null, temp.theString, temp.from, temp.to, true, numberOfSteps));
        }

        return tweens;
    }

    public int getBoyerMooreResult()
    {
        return BoyerMooreResult;
    }

    public BoyerMooreString getBoyerMooreString1()
    {
        return theBoyerMooreString1;
    }

    public BoyerMooreString getBoyerMooreString2()
    {
        return theBoyerMooreString2;
    }

    public String getClassName()
    {
        return "BoyerMoore";
    }

    public Node getMi1N()
    {
        return mi1N;
    }

    public BoyerMooreNextTable getNextTable()
    {
        return theNextTable;
    }

    public Node getPatternN()
    {
        return patternN;
    }

    public Node getSkipN()
    {
        return skipN;
    }

    public BoyerMooreSkipTable getSkipTable()
    {
        return theSkipTable;
    }

    public Node getSkipTableN()
    {
        return skipTableN;
    }

    public String getSkipTableS()
    {
        return skipTableS;
    }

    public BoyerMooreString getTestBoyerMooreString1()
    {
        return testBoyerMooreString1;
    }

    public BoyerMooreString getTestBoyerMooreString2()
    {
        return testBoyerMooreString2;
    }

    protected boolean hasQuestions()
    {
        return false;
    }

    protected void initialise()
    {
        theBoyerMooreString1 = new BoyerMooreString((new StringBuilder()).append(data[0]).append(" ").toString());
        theBoyerMooreString2 = new BoyerMooreString((new StringBuilder()).append(data[1]).append(" ").toString());
        theNextTable = null;
        theSkipTable = null;
        testBoyerMooreString1 = null;
        testBoyerMooreString1 = null;
        BoyerMooreResult = -1;
        skipTableN = null;
        skipTableS = null;
        patternN = null;
        skipN = null;
        mi1N = null;
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
        BoyerMooreMoveRequest.removeAllElements();
    }

    protected void removeAllQuestions()
    {
    }

    protected void run()
    {
        String text = data[1];
        String pattern = data[0];
        int size = pattern.length();
        int alpha = 27;
        int N = text.length();
        int M = pattern.length();
        setPosition("0");
        theSkipTable = new BoyerMooreSkipTable(alpha);
        setPosition("1.1");
        int i;
        for(i = 0; i < alpha; i++)
            theSkipTable.set(i, M);

        setPosition("1.2");
        theBoyerMooreString1.setLabel("j");
        theBoyerMooreString1.setArrowLabelIndex(0);
        theBoyerMooreString1.setArrowLabelTarget(0);
        theBoyerMooreString1.setLabelIndex(0);
        mi1N = new Node((new StringBuilder()).append("").append(M - 0 - 1).toString(), 0);
        setPosition("1.3");
        for(i = 0; i < M; i++)
        {
            theSkipTable.highlight(pattern.charAt(i));
            theBoyerMooreString1.highlight(i);
            setPosition("1.4");
            theSkipTable.set(pattern.charAt(i), M - i - 1);
            theSkipTable.highlight(pattern.charAt(i));
            setPosition("1.4.1");
            theSkipTable.unHighlight(pattern.charAt(i));
            theBoyerMooreString1.unHighlightAll();
            theBoyerMooreString1.setArrowLabelTarget(i + 1);
            theBoyerMooreString1.setArrowLabelIndex(i + 1);
            mi1N = new Node((new StringBuilder()).append("").append(M - i - 2).toString(), 0);
            setPosition("1.3");
        }

        theBoyerMooreString1.setArrowLabelIndex(-1);
        theBoyerMooreString1.setArrowLabelTarget(-1);
        theBoyerMooreString1.setShowLabel(false);
        mi1N = null;
        setPosition("1.5");
        theBoyerMooreString2.setLabel("i");
        theBoyerMooreString2.setHighLabel(false);
        i = M - 1;
        theBoyerMooreString2.setArrowLabelIndex(i);
        theBoyerMooreString2.setArrowLabelTarget(i);
        setPosition("3.1.1");
        theBoyerMooreString1.setLabel("j");
        int j = M - 1;
        theBoyerMooreString1.setArrowLabelIndex(j);
        theBoyerMooreString1.setArrowLabelTarget(j);
        setPosition("3.1.2");
        while(j >= 0) 
        {
            setPosition("3.3");
            if(text.charAt(i) != pattern.charAt(j))
            {
                theBoyerMooreString1.unHighlightAll();
                theBoyerMooreString2.unHighlightAll();
                theBoyerMooreString1.setHighlightColor(new Color(255, 90, 90));
                theBoyerMooreString2.setHighlightColor(new Color(255, 90, 90));
                theBoyerMooreString1.highlight(j);
                theBoyerMooreString2.highlight(i);
                setPosition("3.3.1");
                theSkipTable.highlight(text.charAt(i));
                int tempA = theSkipTable.get(text.charAt(i));
                skipTableS = (new StringBuilder()).append(text.charAt(i)).append("").toString();
                skipTableN = new Node((new StringBuilder()).append("").append(tempA).toString(), 0);
                setPosition("3.3.1.3");
                patternN = new Node((new StringBuilder()).append("").append(M - j).toString(), 0);
                setPosition("3.3.1.5");
                int skip = theSkipTable.get(text.charAt(i));
                if(M - j > skip)
                {
                    patternN.highlight();
                    skip = M - j;
                } else
                {
                    skipTableN.highlight();
                }
                skipN = new Node((new StringBuilder()).append("").append(skip).toString(), 0);
                setPosition("3.3.1.2");
                i += skip;
                theBoyerMooreString2.jumpArrowLabelIndex(skip - (M - j - 1));
                theBoyerMooreString2.setArrowLabelTarget(i);
                theBoyerMooreString1.jumpArrowLabelIndex(skip - (M - j - 1));
                theBoyerMooreString1.setArrowLabelTarget(j);
                setPosition("3.3.1.6");
                skipN = null;
                patternN = null;
                skipTableS = null;
                skipTableN = null;
                setPosition("3.3.1.7");
                if(i >= N)
                {
                    BoyerMooreResult = 0;
                    setPosition("3.3.1.8");
                    setPosition("4");
                    return;
                }
                int oldJ = j;
                j = M - 1;
                theBoyerMooreString1.setArrowLabelTarget(j);
                BoyerMooreMove(theBoyerMooreString1, (skip - (M - oldJ - 1)) * 20, 0);
                setPosition("3.3.1.9");
                theSkipTable.unHighlightAll();
                theBoyerMooreString1.unHighlightAll();
                theBoyerMooreString2.unHighlightAll();
            } else
            {
                theBoyerMooreString1.setHighlightColor(Color.green);
                theBoyerMooreString2.setHighlightColor(Color.green);
                theBoyerMooreString1.highlight(j);
                theBoyerMooreString2.highlight(i);
                setPosition("3.3.1");
                setPosition("3.3.2");
                i--;
                theBoyerMooreString2.setArrowLabelTarget(i);
                setPosition("3.3.2.1.1");
                j--;
                theBoyerMooreString1.setArrowLabelTarget(j);
                setPosition("3.3.2.1.2");
            }
            setPosition("3.3.3");
        }
        setPosition("3.3");
        setPosition("3.3.3");
        removeAllAnimationRequests();
        theBoyerMooreString1.setHighlightColor(Color.green);
        theBoyerMooreString2.setHighlightColor(Color.green);
        BoyerMooreResult = 1;
        setPosition("3.4");
        setPosition("4");
    }

    public static final int EMPTY_MARKER = -10;
    protected static final String HIGHLIGHT_COLOR = Messages.getString("BoyerMoore.0");
    protected static final String NODE_COLOR = Messages.getString("BoyerMoore.1");
    protected String data[];
    protected BoyerMooreString theBoyerMooreString1;
    protected BoyerMooreString theBoyerMooreString2;
    protected BoyerMooreString testBoyerMooreString1;
    protected BoyerMooreString testBoyerMooreString2;
    protected String skipTableS;
    protected Node skipTableN;
    protected Node patternN;
    protected Node skipN;
    protected Node mi1N;
    protected BoyerMooreNextTable theNextTable;
    protected BoyerMooreSkipTable theSkipTable;
    protected int BoyerMooreResult;
    protected int nextTable[];
    protected Vector BoyerMooreMoveRequest;
    protected Color textColor;
    protected Color highlightColor;
    protected Color nodeColor;
    protected Color background;
    protected boolean drawSymbolArrayAndLines;

}
