// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Alignment.java

import com.cim.AIA.*;
import java.awt.Color;
import java.awt.Point;
import java.io.PrintStream;
import java.util.Stack;
import java.util.Vector;
import localization.Messages;

public class Alignment extends Algorithm
    implements ColorSelectionListener
{

    protected static void setRunningMode(String mode)
    {
        if(mode == null)
        {
            runningMode = 1;
            return;
        }
        if(mode.equals("GLOBALDIST"))
            runningMode = 1;
        if(mode.equals("GLOBALSIM"))
            runningMode = 2;
        if(mode.equals("LOCALSIM"))
            runningMode = 3;
    }

    public Alignment(AlgorithmThread algorithmThread, Object data)
    {
        super(algorithmThread, data);
        tempNode = new AlignmentNode("X", 1);
        the3D = new AlignmentMatrix3D();
        theAlignmentKey = null;
        textColor = Color.black;
        highlightColor = DEFAULT_HIGHLIGHT_COLOR;
        nodeColor = DEFAULT_NODE_COLOR;
        background = Color.white;
        drawSymbolArrayAndLines = true;
        reInitialise(data);
        currentVariation = 1;
        ConfigurationManager cm = ConfigurationManager.getConfigurationManager();
        ColorSelection highlightSelection = new ColorSelection(highlightColor, HIGHLIGHT_COLOR);
        highlightSelection.addClass(this);
        cm.addColorSelection(highlightSelection);
        ColorSelection nodeSelection = new ColorSelection(nodeColor, NODE_COLOR);
        nodeSelection.addClass(this);
        cm.addColorSelection(nodeSelection);
        cm.addColorSelectionListener(this);
        if(runningMode == 1)
        {
            setMatchCost(0);
            setMismatchCost(4);
            setSpaceCost(2);
            setInitialGapCost(2);
            setRunningGapCost(1);
        } else
        {
            setMatchCost(1);
            setMismatchCost(-4);
            setSpaceCost(-2);
            setInitialGapCost(-2);
            setRunningGapCost(-1);
        }
    }

    protected void changeData(Object data)
    {
        super.changeData(data);
    }

    protected void clearState()
    {
        super.clearState();
    }

    protected void gapFindPathInteractive(int x, int y, int a, Stack path)
    {
        path.push(new Point(x, y));
        path.push(new Integer(a));
        int newX = 0;
        int newY = 0;
        aMatrix.setXIndexHighlight(x);
        aMatrix.setYIndexHighlight(y);
        aMatrix.setInternalBoxCoords(x, y);
        bMatrix.setXIndexHighlight(x);
        bMatrix.setYIndexHighlight(y);
        bMatrix.setInternalBoxCoords(x, y);
        cMatrix.setXIndexHighlight(x);
        cMatrix.setYIndexHighlight(y);
        cMatrix.setInternalBoxCoords(x, y);
        AlignmentMatrix selectedMatrix;
        if(a == 1)
        {
            newX = x - 1;
            newY = y - 1;
            selectedMatrix = aMatrix;
        } else
        if(a == 2)
        {
            newX = x;
            newY = y - 1;
            selectedMatrix = bMatrix;
        } else
        {
            newX = x - 1;
            newY = y;
            selectedMatrix = cMatrix;
        }
        selectedMatrix.setIsSelected(true);
        AlignmentNode aNode = selectedMatrix.get(x, y);
        aNode.setBackgroundColor(DEFAULT_CURRENT_COLOR);
        aNode.setRingColor(HIGHLIGHT_RING_COLOR);
        the3D.initialiseColor();
        the3D.disable(2, 2);
        the3D.darkenX(a - 1);
        the3D.fillColor(2, 2, a - 1, DEFAULT_CURRENT_COLOR);
        the3D.highlightX(2, 2, a - 1);
        setPosition("3.3");
        if(!aNode.getTraceUp() && !aNode.getTraceDiag() && !aNode.getTraceLeft())
        {
            aNode.highlight();
            aNode.setRingColor(DEFAULT_RING_COLOR);
            aMatrix.setXIndexHighlight(-1);
            aMatrix.setYIndexHighlight(-1);
            bMatrix.setXIndexHighlight(-1);
            bMatrix.setYIndexHighlight(-1);
            cMatrix.setXIndexHighlight(-1);
            cMatrix.setYIndexHighlight(-1);
            aMatrix.setDrawInternalBox(false);
            bMatrix.setDrawInternalBox(false);
            cMatrix.setDrawInternalBox(false);
            selectedMatrix.setIsSelected(false);
            setPosition("3.4.0");
            path.pop();
            path.pop();
            return;
        }
        int _3DX;
        int _3DY;
        if(a == 1)
        {
            setPosition("3.3.1");
            aMatrix.setXIndexHighlight(x - 1);
            bMatrix.setXIndexHighlight(x - 1);
            cMatrix.setXIndexHighlight(x - 1);
            aMatrix.setYIndexHighlight(y - 1);
            bMatrix.setYIndexHighlight(y - 1);
            cMatrix.setYIndexHighlight(y - 1);
            aNode.highlightDiag();
            aMatrix.get(x - 1, y - 1).setRingColor(SELECT_RING_COLOR);
            bMatrix.get(x - 1, y - 1).setRingColor(SELECT_RING_COLOR);
            cMatrix.get(x - 1, y - 1).setRingColor(SELECT_RING_COLOR);
            _3DX = 1;
            _3DY = 1;
            the3D.highlightX(_3DX, _3DY);
            setPosition("3.3.1.1");
        } else
        if(a == 2)
        {
            setPosition("3.3.1");
            setPosition("3.3.2");
            aMatrix.setYIndexHighlight(y - 1);
            bMatrix.setYIndexHighlight(y - 1);
            cMatrix.setYIndexHighlight(y - 1);
            aNode.highlightUp();
            aMatrix.get(x, y - 1).setRingColor(SELECT_RING_COLOR);
            bMatrix.get(x, y - 1).setRingColor(SELECT_RING_COLOR);
            cMatrix.get(x, y - 1).setRingColor(SELECT_RING_COLOR);
            _3DX = 2;
            _3DY = 1;
            the3D.highlightX(_3DX, _3DY);
            setPosition("3.3.2.1");
        } else
        {
            setPosition("3.3.1");
            setPosition("3.3.2");
            setPosition("3.3.3");
            aMatrix.setXIndexHighlight(x - 1);
            bMatrix.setXIndexHighlight(x - 1);
            cMatrix.setXIndexHighlight(x - 1);
            aNode.highlightLeft();
            aMatrix.get(x - 1, y).setRingColor(SELECT_RING_COLOR);
            bMatrix.get(x - 1, y).setRingColor(SELECT_RING_COLOR);
            cMatrix.get(x - 1, y).setRingColor(SELECT_RING_COLOR);
            _3DX = 1;
            _3DY = 2;
            the3D.highlightX(_3DX, _3DY);
            setPosition("3.3.3.1");
        }
        if(aNode.getTraceA())
        {
            setPosition("3.3.4.1.1");
            aNode.highlightA();
            aMatrix.get(newX, newY).setBackgroundColor(DEFAULT_NODE_COLOR);
            aMatrix.get(newX, newY).setRingColor(DEFAULT_RING_COLOR);
            the3D.fillColor(_3DX, _3DY, 0, DEFAULT_NODE_COLOR);
            the3D.unHighlightX(_3DX, _3DY, 0);
            setPosition("3.3.4.1.2");
        } else
        {
            setPosition("3.3.4.1.1");
            setPosition("3.3.4.1.3");
            aMatrix.get(newX, newY).setIsDisabled(true);
            aMatrix.get(newX, newY).setRingColor(DEFAULT_RING_COLOR);
            the3D.disable(_3DX, _3DY, 0);
            the3D.unHighlightX(_3DX, _3DY, 0);
            setPosition("3.3.4.1.4");
        }
        if(aNode.getTraceB())
        {
            setPosition("3.3.4.1.5");
            aNode.highlightB();
            bMatrix.get(newX, newY).setBackgroundColor(DEFAULT_NODE_COLOR);
            bMatrix.get(newX, newY).setRingColor(DEFAULT_RING_COLOR);
            the3D.fillColor(_3DX, _3DY, 1, DEFAULT_NODE_COLOR);
            the3D.unHighlightX(_3DX, _3DY, 1);
            setPosition("3.3.4.1.6");
        } else
        {
            setPosition("3.3.4.1.5");
            setPosition("3.3.4.1.7");
            bMatrix.get(newX, newY).setIsDisabled(true);
            bMatrix.get(newX, newY).setRingColor(DEFAULT_RING_COLOR);
            the3D.disable(_3DX, _3DY, 1);
            the3D.unHighlightX(_3DX, _3DY, 1);
            setPosition("3.3.4.1.8");
        }
        if(aNode.getTraceC())
        {
            setPosition("3.3.4.1.9");
            aNode.highlightC();
            cMatrix.get(newX, newY).setBackgroundColor(DEFAULT_NODE_COLOR);
            cMatrix.get(newX, newY).setRingColor(DEFAULT_RING_COLOR);
            the3D.fillColor(_3DX, _3DY, 2, DEFAULT_NODE_COLOR);
            the3D.unHighlightX(_3DX, _3DY, 2);
            setPosition("3.3.4.1.10");
        } else
        {
            setPosition("3.3.4.1.9");
            setPosition("3.3.4.1.11");
            cMatrix.get(newX, newY).setIsDisabled(true);
            cMatrix.get(newX, newY).setRingColor(DEFAULT_RING_COLOR);
            the3D.disable(_3DX, _3DY, 2);
            the3D.unHighlightX(_3DX, _3DY, 2);
            setPosition("3.3.4.1.12");
        }
        aNode.unHighlightA();
        aNode.unHighlightB();
        aNode.unHighlightC();
        if(aNode.getTraceA())
        {
            aNode.highlightA();
            aMatrix.get(newX, newY).setBackgroundColor(DEFAULT_SELECTED_COLOR);
            cMatrix.get(newX, newY).setIsDisabled(true);
            bMatrix.get(newX, newY).setIsDisabled(true);
            the3D.fillColor(_3DX, _3DY, 0, DEFAULT_SELECTED_COLOR);
            setPosition("3.3.4.2");
            setPosition("3.3.4.3");
            aNode.setRingColor(DEFAULT_RING_COLOR);
            aMatrix.get(newX, newY).setBackgroundColor(DEFAULT_CURRENT_COLOR);
            aMatrix.get(newX, newY).setRingColor(HIGHLIGHT_RING_COLOR);
            aMatrix.setIsSelected(true);
            aNode.highlight();
            selectedMatrix.setIsSelected(false);
            aMatrix.setIsSelected(true);
            the3D.disable(_3DX, _3DY);
            the3D.fillColor(_3DX, _3DY, 0, DEFAULT_CURRENT_COLOR);
            the3D.unDarkenX(a - 1);
            the3D.darkenX(0);
            the3D.highlightX(_3DX, _3DY, 0);
            the3D.fillColor(2, 2, a - 1, highlightColor);
            the3D.unHighlightX(2, 2, a - 1);
            setPosition("3.3.4.4");
            gapFindPathInteractive(newX, newY, 1, path);
        } else
        if(aNode.getTraceB())
        {
            aNode.highlightB();
            bMatrix.get(newX, newY).setBackgroundColor(DEFAULT_SELECTED_COLOR);
            cMatrix.get(newX, newY).setIsDisabled(true);
            aMatrix.get(newX, newY).setIsDisabled(true);
            the3D.fillColor(_3DX, _3DY, 1, DEFAULT_SELECTED_COLOR);
            setPosition("3.3.4.2");
            setPosition("3.3.4.3");
            setPosition("3.3.4.5");
            aNode.setRingColor(DEFAULT_RING_COLOR);
            bMatrix.get(newX, newY).setBackgroundColor(DEFAULT_CURRENT_COLOR);
            bMatrix.get(newX, newY).setRingColor(HIGHLIGHT_RING_COLOR);
            bMatrix.setIsSelected(true);
            aNode.highlight();
            selectedMatrix.setIsSelected(false);
            bMatrix.setIsSelected(true);
            the3D.disable(_3DX, _3DY);
            the3D.fillColor(_3DX, _3DY, 1, DEFAULT_CURRENT_COLOR);
            the3D.unDarkenX(a - 1);
            the3D.darkenX(1);
            the3D.highlightX(_3DX, _3DY, 1);
            the3D.fillColor(2, 2, a - 1, highlightColor);
            the3D.unHighlightX(2, 2, a - 1);
            setPosition("3.3.4.6");
            gapFindPathInteractive(newX, newY, 2, path);
        } else
        if(aNode.getTraceC())
        {
            aNode.highlightC();
            cMatrix.get(newX, newY).setBackgroundColor(DEFAULT_SELECTED_COLOR);
            aMatrix.get(newX, newY).setIsDisabled(true);
            bMatrix.get(newX, newY).setIsDisabled(true);
            the3D.fillColor(_3DX, _3DY, 2, DEFAULT_SELECTED_COLOR);
            setPosition("3.3.4.2");
            setPosition("3.3.4.3");
            setPosition("3.3.4.5");
            setPosition("3.3.4.7");
            aNode.setRingColor(DEFAULT_RING_COLOR);
            cMatrix.get(newX, newY).setBackgroundColor(DEFAULT_CURRENT_COLOR);
            cMatrix.get(newX, newY).setRingColor(HIGHLIGHT_RING_COLOR);
            cMatrix.setIsSelected(true);
            aNode.highlight();
            selectedMatrix.setIsSelected(false);
            cMatrix.setIsSelected(true);
            the3D.disable(_3DX, _3DY);
            the3D.fillColor(_3DX, _3DY, 2, DEFAULT_CURRENT_COLOR);
            the3D.unDarkenX(a - 1);
            the3D.darkenX(2);
            the3D.highlightX(_3DX, _3DY, 2);
            the3D.fillColor(2, 2, a - 1, highlightColor);
            the3D.unHighlightX(2, 2, a - 1);
            setPosition("3.3.4.8");
            gapFindPathInteractive(newX, newY, 3, path);
        }
        path.pop();
        path.pop();
        aMatrix.unHighlight();
        aMatrix.unHighlightTrace();
        aMatrix.setAllColor(DEFAULT_NODE_COLOR, Color.black);
        bMatrix.unHighlight();
        bMatrix.unHighlightTrace();
        bMatrix.setAllColor(DEFAULT_NODE_COLOR, Color.black);
        cMatrix.unHighlight();
        cMatrix.unHighlightTrace();
        cMatrix.setAllColor(DEFAULT_NODE_COLOR, Color.black);
    }

    protected void gapFindPathNonInteractive(int x, int y, int a, Stack path)
    {
        aMatrix.unHighlight();
        aMatrix.unHighlightTrace();
        aMatrix.setAllColor(DEFAULT_NODE_COLOR, Color.black);
        bMatrix.unHighlight();
        bMatrix.unHighlightTrace();
        bMatrix.setAllColor(DEFAULT_NODE_COLOR, Color.black);
        cMatrix.unHighlight();
        cMatrix.unHighlightTrace();
        cMatrix.setAllColor(DEFAULT_NODE_COLOR, Color.black);
        path.push(new Point(x, y));
        path.push(new Integer(a));
        int newX = 0;
        int newY = 0;
        AlignmentMatrix selectedMatrix;
        if(a == 1)
        {
            newX = x - 1;
            newY = y - 1;
            selectedMatrix = aMatrix;
        } else
        if(a == 2)
        {
            newX = x;
            newY = y - 1;
            selectedMatrix = bMatrix;
        } else
        {
            newX = x - 1;
            newY = y;
            selectedMatrix = cMatrix;
        }
        AlignmentNode aNode = selectedMatrix.get(x, y);
        aNode.highlight();
        if(!aNode.getTraceUp() && !aNode.getTraceDiag() && !aNode.getTraceLeft())
        {
            string1EA = new ElementArray(0, 0);
            string2EA = new ElementArray(0, 0);
            string1EA.setColumGap(0);
            string2EA.setColumGap(0);
            string1EA.setElementWidth(20);
            string2EA.setElementWidth(20);
            int prevTrace = a;
            int trace = 0;
            int count = 0;
            aMatrix.unHighlight();
            aMatrix.unHighlightTrace();
            aMatrix.setAllColor(IRRELEVANT_COLOR, IRRELEVANT_ARROW_COLOR);
            bMatrix.unHighlight();
            bMatrix.unHighlightTrace();
            bMatrix.setAllColor(IRRELEVANT_COLOR, IRRELEVANT_ARROW_COLOR);
            cMatrix.unHighlight();
            cMatrix.unHighlightTrace();
            cMatrix.setAllColor(IRRELEVANT_COLOR, IRRELEVANT_ARROW_COLOR);
            path.pop();
            path.push(new Integer(0));
            Stack newPath = (Stack)path.clone();
            do
            {
                if(newPath.empty())
                    break;
                AlignmentNode tempNode = null;
                if(trace != 0)
                    prevTrace = trace;
                trace = ((Integer)newPath.pop()).intValue();
                Point pnt = (Point)newPath.pop();
                if(trace == 1)
                {
                    selectedMatrix = aMatrix;
                    tempNode = aMatrix.get(pnt.x, pnt.y);
                    tempNode.highlight();
                }
                if(trace == 2)
                {
                    selectedMatrix = bMatrix;
                    tempNode = bMatrix.get(pnt.x, pnt.y);
                    tempNode.highlight();
                }
                if(trace == 3)
                {
                    selectedMatrix = cMatrix;
                    tempNode = cMatrix.get(pnt.x, pnt.y);
                    tempNode.highlight();
                }
                if(trace == 0)
                {
                    if(prevTrace == 1)
                        aMatrix.get(pnt.x, pnt.y).highlight();
                    if(prevTrace == 2)
                        bMatrix.get(pnt.x, pnt.y).highlight();
                    if(prevTrace == 3)
                        cMatrix.get(pnt.x, pnt.y).highlight();
                }
                if(trace == 1)
                {
                    string1EA.setValue(count, new Node((new StringBuilder()).append(data[0].charAt(pnt.x - 1)).append("").toString(), 0));
                    string2EA.setValue(count, new Node((new StringBuilder()).append(data[1].charAt(pnt.y - 1)).append("").toString(), 0));
                    tempNode.highlightDiag();
                    if(prevTrace == 1)
                        tempNode.highlightA();
                    if(prevTrace == 2)
                        tempNode.highlightB();
                    if(prevTrace == 3)
                        tempNode.highlightC();
                    if(prevTrace != trace)
                    {
                        selectedMatrix.get(pnt.x - 1, pnt.y - 1).setIsDisabled(true);
                        tempNode.unHighlightDiag();
                        tempNode.setArrowColor(Color.black);
                    }
                    count++;
                }
                if(trace == 3)
                {
                    string1EA.setValue(count, new Node((new StringBuilder()).append(data[0].charAt(pnt.x - 1)).append("").toString(), 0));
                    string2EA.setValue(count, new Node("-", 0));
                    tempNode.highlightLeft();
                    if(prevTrace == 1)
                        tempNode.highlightA();
                    if(prevTrace == 2)
                        tempNode.highlightB();
                    if(prevTrace == 3)
                        tempNode.highlightC();
                    if(prevTrace != trace)
                    {
                        selectedMatrix.get(pnt.x - 1, pnt.y).setIsDisabled(true);
                        tempNode.unHighlightLeft();
                        tempNode.setArrowColor(Color.black);
                    }
                    count++;
                }
                if(trace == 2)
                {
                    string1EA.setValue(count, new Node("-", 0));
                    string2EA.setValue(count, new Node((new StringBuilder()).append(data[1].charAt(pnt.y - 1)).append("").toString(), 0));
                    tempNode.highlightUp();
                    if(prevTrace == 1)
                        tempNode.highlightA();
                    if(prevTrace == 2)
                        tempNode.highlightB();
                    if(prevTrace == 3)
                        tempNode.highlightC();
                    if(prevTrace != trace)
                    {
                        selectedMatrix.get(pnt.x, pnt.y - 1).setIsDisabled(true);
                        tempNode.unHighlightUp();
                        tempNode.setArrowColor(Color.black);
                    }
                    count++;
                }
            } while(true);
            if(isFirstAlignment)
            {
                isFirstAlignment = false;
                setPosition("3.4");
                setPosition("3.5");
            } else
            {
                setPosition("3.5.1");
                setPosition("3.5");
            }
        }
        if(aNode.getTraceA())
        {
            aNode.highlightA();
            gapFindPathNonInteractive(newX, newY, 1, path);
        }
        if(aNode.getTraceB())
        {
            aNode.highlightB();
            gapFindPathNonInteractive(newX, newY, 2, path);
        }
        if(aNode.getTraceC())
        {
            aNode.highlightC();
            gapFindPathNonInteractive(newX, newY, 3, path);
        }
        path.pop();
        path.pop();
    }

    protected Vector generateQuestions()
    {
        return null;
    }

    public MultipleTween generateTweens(Paintable paintable, Object parameter, int numberOfSteps)
    {
        return null;
    }

    public AlignmentMatrix3D get3D()
    {
        return the3D;
    }

    public AlignmentMatrix getAMatrix()
    {
        if(currentVariation == 2)
            return aMatrix;
        else
            return null;
    }

    public AlignmentMatrix getBMatrix()
    {
        if(currentVariation == 2)
            return bMatrix;
        else
            return null;
    }

    public String getClassName()
    {
        return Messages.getString("Alignment.244");
    }

    public AlignmentMatrix getCMatrix()
    {
        if(currentVariation == 2)
            return cMatrix;
        else
            return null;
    }

    public int getInitialGapCost()
    {
        return initialGapCost;
    }

    public AlignmentKey getKey()
    {
        return theAlignmentKey;
    }

    public int getMatchCost()
    {
        return matchCost;
    }

    public AlignmentMatrix getMatrix()
    {
        if(currentVariation == 1)
            return theMatrix;
        else
            return null;
    }

    public AlignmentMinimum getMinimum()
    {
        return theMinimum;
    }

    public int getMismatchCost()
    {
        return mismatchCost;
    }

    public AlignmentNode getNode()
    {
        return tempNode;
    }

    public int getRunningGapCost()
    {
        return runningGapCost;
    }

    public int getSpaceCost()
    {
        return spaceCost;
    }

    public ElementArray getString1EA()
    {
        return string2EA;
    }

    public ElementArray getString2EA()
    {
        return string1EA;
    }

    protected void globalDistGapAlignment()
    {
        int posInf = 5000;
        aMatrix.add(0, 0, 0);
        bMatrix.add(0, 0, 0);
        cMatrix.add(0, 0, 0);
        setPosition("1.1");
        for(int i = 1; i < xSize; i++)
        {
            bMatrix.add(posInf, i, 0);
            cMatrix.add(initialGapCost + runningGapCost * i, i, 0);
            AlignmentNode tempNode = cMatrix.get(i, 0);
            tempNode.setTraceLeft(true);
            tempNode.setTraceC(true);
            aMatrix.add(posInf, i, 0);
        }

        setPosition("1.2");
        for(int i = 1; i < ySize; i++)
        {
            bMatrix.add(initialGapCost + runningGapCost * i, 0, i);
            AlignmentNode tempNode = bMatrix.get(0, i);
            tempNode.setTraceUp(true);
            tempNode.setTraceB(true);
            cMatrix.add(posInf, 0, i);
            aMatrix.add(posInf, 0, i);
        }

        setPosition("1.3");
        for(int y = 1; y < ySize; y++)
        {
            aMatrix.setYIndexHighlight(y);
            bMatrix.setYIndexHighlight(y);
            cMatrix.setYIndexHighlight(y);
            setPosition("2.1");
            for(int x = 1; x < xSize; x++)
            {
                aMatrix.setXIndexHighlight(x);
                bMatrix.setXIndexHighlight(x);
                cMatrix.setXIndexHighlight(x);
                setPosition("2.1.1");
                theMinimum = new AlignmentMinimum(Messages.getString("Alignment.227"));
                theMinimum.add();
                theMinimum.add();
                theMinimum.add();
                AlignmentNode tempNode = aMatrix.get(x, y - 1);
                tempNode.highlight();
                theMinimum.set(0, aMatrix.valueAt(x, y - 1), initialGapCost + runningGapCost);
                setPosition("2.1.1.1.2");
                tempNode.unHighlight();
                tempNode = bMatrix.get(x, y - 1);
                tempNode.highlight();
                theMinimum.set(1, bMatrix.valueAt(x, y - 1), runningGapCost);
                setPosition("2.1.1.1.4");
                tempNode.unHighlight();
                tempNode = cMatrix.get(x, y - 1);
                tempNode.highlight();
                theMinimum.set(2, cMatrix.valueAt(x, y - 1), initialGapCost + runningGapCost);
                setPosition("2.1.1.1.6");
                tempNode.unHighlight();
                int a = aMatrix.valueAt(x, y - 1) + initialGapCost + runningGapCost;
                int b = bMatrix.valueAt(x, y - 1) + runningGapCost;
                int c = cMatrix.valueAt(x, y - 1) + initialGapCost + runningGapCost;
                int min = Math.min(a, Math.min(b, c));
                bMatrix.add(min, x, y);
                tempNode = bMatrix.get(x, y);
                tempNode.setTraceUp(true);
                if(min == a)
                {
                    tempNode.highlightA();
                    aMatrix.get(x, y - 1).highlight();
                    theMinimum.highlight(0);
                    tempNode.setTraceA(true);
                } else
                {
                    aMatrix.get(x, y - 1).setIsDisabled(true);
                    tempNode.setTraceA(false);
                }
                if(min == b)
                {
                    tempNode.highlightB();
                    bMatrix.get(x, y - 1).highlight();
                    theMinimum.highlight(1);
                    tempNode.setTraceB(true);
                } else
                {
                    bMatrix.get(x, y - 1).setIsDisabled(true);
                    tempNode.setTraceB(false);
                }
                if(min == c)
                {
                    tempNode.highlightC();
                    cMatrix.get(x, y - 1).highlight();
                    theMinimum.highlight(2);
                    tempNode.setTraceC(true);
                } else
                {
                    cMatrix.get(x, y - 1).setIsDisabled(true);
                    tempNode.setTraceC(false);
                }
                tempNode.highlight();
                tempNode.setRingColor(HIGHLIGHT_RING_COLOR);
                setPosition("2.1.1.1");
                tempNode.unHighlightA();
                tempNode.unHighlightB();
                tempNode.unHighlightC();
                tempNode.setRingColor(DEFAULT_RING_COLOR);
                tempNode.unHighlight();
                aMatrix.get(x, y - 1).setIsDisabled(false);
                aMatrix.get(x, y - 1).unHighlight();
                bMatrix.get(x, y - 1).setIsDisabled(false);
                bMatrix.get(x, y - 1).unHighlight();
                cMatrix.get(x, y - 1).setIsDisabled(false);
                cMatrix.get(x, y - 1).unHighlight();
                theMinimum = null;
                theMinimum = new AlignmentMinimum(Messages.getString("Alignment.232"));
                theMinimum.add();
                theMinimum.add();
                theMinimum.add();
                tempNode = aMatrix.get(x - 1, y);
                tempNode.highlight();
                theMinimum.set(0, aMatrix.valueAt(x - 1, y), initialGapCost + runningGapCost);
                setPosition("2.1.1.2.2");
                tempNode.unHighlight();
                tempNode = bMatrix.get(x - 1, y);
                tempNode.highlight();
                theMinimum.set(1, bMatrix.valueAt(x - 1, y), initialGapCost + runningGapCost);
                setPosition("2.1.1.2.4");
                tempNode.unHighlight();
                tempNode = cMatrix.get(x - 1, y);
                tempNode.highlight();
                theMinimum.set(2, cMatrix.valueAt(x - 1, y), runningGapCost);
                setPosition("2.1.1.2.6");
                tempNode.unHighlight();
                a = aMatrix.valueAt(x - 1, y) + initialGapCost + runningGapCost;
                b = bMatrix.valueAt(x - 1, y) + initialGapCost + runningGapCost;
                c = cMatrix.valueAt(x - 1, y) + runningGapCost;
                min = Math.min(a, Math.min(b, c));
                cMatrix.add(min, x, y);
                tempNode = cMatrix.get(x, y);
                tempNode.setTraceLeft(true);
                if(min == a)
                {
                    tempNode.highlightA();
                    aMatrix.get(x - 1, y).highlight();
                    theMinimum.highlight(0);
                    tempNode.setTraceA(true);
                } else
                {
                    aMatrix.get(x - 1, y).setIsDisabled(true);
                    tempNode.setTraceA(false);
                }
                if(min == b)
                {
                    tempNode.highlightB();
                    bMatrix.get(x - 1, y).highlight();
                    theMinimum.highlight(1);
                    tempNode.setTraceB(true);
                } else
                {
                    bMatrix.get(x - 1, y).setIsDisabled(true);
                    tempNode.setTraceB(false);
                }
                if(min == c)
                {
                    tempNode.highlightC();
                    cMatrix.get(x - 1, y).highlight();
                    theMinimum.highlight(2);
                    tempNode.setTraceC(true);
                } else
                {
                    cMatrix.get(x - 1, y).setIsDisabled(true);
                    tempNode.setTraceC(false);
                }
                tempNode.highlight();
                tempNode.setRingColor(HIGHLIGHT_RING_COLOR);
                setPosition("2.1.1.2");
                tempNode.unHighlightA();
                tempNode.unHighlightB();
                tempNode.unHighlightC();
                tempNode.setRingColor(DEFAULT_RING_COLOR);
                tempNode.unHighlight();
                aMatrix.get(x - 1, y).setIsDisabled(false);
                aMatrix.get(x - 1, y).unHighlight();
                bMatrix.get(x - 1, y).setIsDisabled(false);
                bMatrix.get(x - 1, y).unHighlight();
                cMatrix.get(x - 1, y).setIsDisabled(false);
                cMatrix.get(x - 1, y).unHighlight();
                theMinimum = null;
                int t;
                if(data[0].charAt(x - 1) == data[1].charAt(y - 1))
                    t = matchCost;
                else
                    t = mismatchCost;
                theMinimum = new AlignmentMinimum(Messages.getString("Alignment.237"));
                theMinimum.add();
                theMinimum.add();
                theMinimum.add();
                tempNode = aMatrix.get(x - 1, y - 1);
                tempNode.highlight();
                theMinimum.set(0, aMatrix.valueAt(x - 1, y - 1), t);
                setPosition("2.1.1.3.1");
                tempNode.unHighlight();
                tempNode = bMatrix.get(x - 1, y - 1);
                tempNode.highlight();
                theMinimum.set(1, bMatrix.valueAt(x - 1, y - 1), t);
                setPosition("2.1.1.3.2");
                tempNode.unHighlight();
                tempNode = cMatrix.get(x - 1, y - 1);
                tempNode.highlight();
                theMinimum.set(2, cMatrix.valueAt(x - 1, y - 1), t);
                setPosition("2.1.1.3.3");
                tempNode.unHighlight();
                a = aMatrix.valueAt(x - 1, y - 1);
                b = bMatrix.valueAt(x - 1, y - 1);
                c = cMatrix.valueAt(x - 1, y - 1);
                min = Math.min(a, Math.min(b, c));
                aMatrix.add(min + t, x, y);
                tempNode = aMatrix.get(x, y);
                tempNode.setTraceDiag(true);
                if(min == a)
                {
                    tempNode.highlightA();
                    aMatrix.get(x - 1, y - 1).highlight();
                    theMinimum.highlight(0);
                    tempNode.setTraceA(true);
                } else
                {
                    aMatrix.get(x - 1, y - 1).setIsDisabled(true);
                    tempNode.setTraceA(false);
                }
                if(min == b)
                {
                    tempNode.highlightB();
                    bMatrix.get(x - 1, y - 1).highlight();
                    theMinimum.highlight(1);
                    tempNode.setTraceB(true);
                } else
                {
                    bMatrix.get(x - 1, y - 1).setIsDisabled(true);
                    tempNode.setTraceB(false);
                }
                if(min == c)
                {
                    tempNode.highlightC();
                    cMatrix.get(x - 1, y - 1).highlight();
                    theMinimum.highlight(2);
                    tempNode.setTraceC(true);
                } else
                {
                    cMatrix.get(x - 1, y - 1).setIsDisabled(true);
                    tempNode.setTraceC(false);
                }
                tempNode.highlight();
                tempNode.setRingColor(HIGHLIGHT_RING_COLOR);
                setPosition("2.1.1.3");
                tempNode.unHighlightA();
                tempNode.unHighlightB();
                tempNode.unHighlightC();
                tempNode.setRingColor(DEFAULT_RING_COLOR);
                tempNode.unHighlight();
                aMatrix.get(x - 1, y - 1).setIsDisabled(false);
                aMatrix.get(x - 1, y - 1).unHighlight();
                bMatrix.get(x - 1, y - 1).setIsDisabled(false);
                bMatrix.get(x - 1, y - 1).unHighlight();
                cMatrix.get(x - 1, y - 1).setIsDisabled(false);
                cMatrix.get(x - 1, y - 1).unHighlight();
                theMinimum = null;
            }

            setPosition("2.1.2");
        }

        setPosition("2.2");
    }

    protected void globalDistNoGapAlignment()
    {
        for(int i = 0; i < xSize; i++)
        {
            theMatrix.setXIndexHighlight(i);
            theMatrix.setYIndexHighlight(0);
            setPosition("1.1.1");
            theMatrix.add(i * spaceCost, i, 0);
            if(i != 0)
            {
                AlignmentNode tempNode = theMatrix.get(i, 0);
                tempNode.setTraceLeft(true);
            }
            setPosition("1.1.2");
        }

        theMatrix.setXIndexHighlight(-1);
        theMatrix.setYIndexHighlight(-1);
        setPosition("1.1.1");
        setPosition("1.1.4");
        for(int i = 0; i < ySize; i++)
        {
            theMatrix.setXIndexHighlight(0);
            theMatrix.setYIndexHighlight(i);
            setPosition("1.1.5");
            theMatrix.add(i * spaceCost, 0, i);
            if(i != 0)
            {
                AlignmentNode tempNode = theMatrix.get(0, i);
                tempNode.setTraceUp(true);
            }
            setPosition("1.1.6");
        }

        theMatrix.setXIndexHighlight(-1);
        theMatrix.setYIndexHighlight(-1);
        setPosition("1.1.5");
        setPosition("1.1.8");
        for(int y = 1; y < ySize; y++)
        {
            theMatrix.setYIndexHighlight(y);
            setPosition("2.1");
            for(int x = 1; x < xSize; x++)
            {
                theMatrix.setXIndexHighlight(x);
                setPosition("2.1.1");
                theMinimum = new AlignmentMinimum(Messages.getString("Alignment.214"));
                theMinimum.add();
                theMinimum.add();
                theMinimum.add();
                int t;
                if(data[0].charAt(x - 1) == data[1].charAt(y - 1))
                    t = matchCost;
                else
                    t = mismatchCost;
                AlignmentNode tempNode = theMatrix.get(x - 1, y);
                tempNode.highlight();
                theMinimum.set(0, theMatrix.valueAt(x - 1, y), spaceCost);
                setPosition("2.1.1.3");
                tempNode = theMatrix.get(x, y - 1);
                tempNode.highlight();
                theMinimum.set(1, theMatrix.valueAt(x, y - 1), spaceCost);
                setPosition("2.1.1.4");
                tempNode = theMatrix.get(x - 1, y - 1);
                tempNode.highlight();
                theMinimum.set(2, theMatrix.valueAt(x - 1, y - 1), t);
                setPosition("2.1.1.5");
                int min = theMatrix.valueAt(x - 1, y - 1) + t;
                if(theMatrix.valueAt(x - 1, y) + spaceCost < min)
                    min = theMatrix.valueAt(x - 1, y) + spaceCost;
                if(theMatrix.valueAt(x, y - 1) + spaceCost < min)
                    min = theMatrix.valueAt(x, y - 1) + spaceCost;
                theMatrix.add(min, x, y);
                tempNode = theMatrix.get(x, y);
                theMatrix.unHighlight();
                if(min == theMatrix.valueAt(x - 1, y - 1) + t)
                {
                    tempNode.setTraceDiag(true);
                    theMatrix.get(x - 1, y - 1).highlight();
                    theMinimum.highlight(2);
                }
                if(min == theMatrix.valueAt(x - 1, y) + spaceCost)
                {
                    tempNode.setTraceLeft(true);
                    theMatrix.get(x - 1, y).highlight();
                    theMinimum.highlight(0);
                }
                if(min == theMatrix.valueAt(x, y - 1) + spaceCost)
                {
                    tempNode.setTraceUp(true);
                    theMatrix.get(x, y - 1).highlight();
                    theMinimum.highlight(1);
                }
                setPosition("2.1.1.2");
                theMatrix.unHighlight();
                theMinimum.unHighlight();
                theMinimum = null;
            }

            setPosition("2.1.1");
        }

        setPosition("2.1");
        theMatrix.setXIndexHighlight(-1);
        theMatrix.setYIndexHighlight(-1);
        setPosition("2.2");
    }

    protected void globalSimGapAlignment()
    {
        theMatrix.add(0, 0, 0);
        System.out.println(Messages.getString("Alignment.0"));
        for(int i = 1; i < xSize; i++)
        {
            E[i][0] = initialGapCost + runningGapCost * i;
            theMatrix.add(E[i][0], i, 0);
            AlignmentNode tempNode = theMatrix.get(i, 0);
            tempNode.setTraceLeft(true);
        }

        printE();
        System.out.println(Messages.getString("Alignment.278"));
        for(int i = 1; i < ySize; i++)
        {
            F[0][i] = initialGapCost + runningGapCost * i;
            theMatrix.add(F[0][i], 0, i);
            AlignmentNode tempNode = theMatrix.get(0, i);
            tempNode.setTraceUp(true);
        }

        printF();
        setPosition("1.1");
        for(int y = 1; y < ySize; y++)
        {
            setPosition("2.1");
            for(int x = 1; x < xSize; x++)
            {
                System.out.println("---------------");
                System.out.println((new StringBuilder()).append("").append(x).append(", ").append(y).toString());
                int e = E[x - 1][y];
                System.out.println((new StringBuilder()).append("E(i,j-1) = ").append(e).toString());
                System.out.println((new StringBuilder()).append("V(i,j-1)-Wg = ").append(theMatrix.valueAt(x, y - 1) + initialGapCost).toString());
                if(theMatrix.valueAt(x - 1, y) + initialGapCost > e || !theMatrix.get(x - 1, y).getTraceLeft())
                    e = theMatrix.valueAt(x - 1, y) + initialGapCost;
                e += runningGapCost;
                E[x][y] = e;
                System.out.println((new StringBuilder()).append("E(i,j) = ").append(e).toString());
                printE();
                int f = F[x][y - 1];
                System.out.println((new StringBuilder()).append("F(i-1,j) = ").append(f).toString());
                System.out.println((new StringBuilder()).append("V(i-1,j)-Wg = ").append(theMatrix.valueAt(x - 1, y) + initialGapCost).toString());
                if(theMatrix.valueAt(x, y - 1) + initialGapCost > f || !theMatrix.get(x, y - 1).getTraceUp())
                    f = theMatrix.valueAt(x, y - 1) + initialGapCost;
                f += runningGapCost;
                F[x][y] = f;
                System.out.println((new StringBuilder()).append("F(i,j) = ").append(f).toString());
                printF();
                System.out.println((new StringBuilder()).append("char 1 = ").append(data[0].charAt(x - 1)).append(" char 2 = ").append(data[1].charAt(y - 1)).toString());
                int t;
                if(data[0].charAt(x - 1) == data[1].charAt(y - 1))
                    t = matchCost;
                else
                    t = mismatchCost;
                int g = theMatrix.valueAt(x - 1, y - 1) + t;
                System.out.println((new StringBuilder()).append("G(i,j) = ").append(g).toString());
                int max = g;
                if(f > max)
                    max = f;
                if(e > max)
                    max = e;
                System.out.println((new StringBuilder()).append("V(i,j) = ").append(max).toString());
                theMatrix.add(max, x, y);
                printV();
                AlignmentNode tempNode = theMatrix.get(x, y);
                if(max == g)
                    tempNode.setTraceDiag(true);
                if(max == f)
                    tempNode.setTraceLeft(true);
                if(max == e)
                    tempNode.setTraceUp(true);
                setPosition("2.1.1.1");
            }

        }

        setPosition("2.2");
        printE();
        printF();
        printV();
    }

    protected void globalSimGapAlignment2()
    {
        int negInf = -99;
        theMatrix.add(0, 0, 0);
        E[0][0] = negInf;
        F[0][0] = negInf;
        for(int i = 1; i < xSize; i++)
        {
            E[i][0] = negInf;
            F[i][0] = initialGapCost + runningGapCost * i;
            theMatrix.add(negInf, i, 0);
        }

        for(int i = 1; i < ySize; i++)
        {
            E[0][i] = initialGapCost + runningGapCost * i;
            F[0][i] = negInf;
            theMatrix.add(negInf, 0, i);
        }

        printE();
        System.out.println("C() = ");
        printF();
        setPosition("1.1");
        for(int y = 1; y < ySize; y++)
        {
            setPosition("2.1");
            for(int x = 1; x < xSize; x++)
            {
                System.out.println("---------------");
                System.out.println((new StringBuilder()).append("").append(x).append(", ").append(y).toString());
                int a = theMatrix.valueAt(x, y - 1);
                a = a + initialGapCost + runningGapCost;
                int b = E[x][y - 1];
                b += runningGapCost;
                int c = F[x][y - 1];
                c = c + initialGapCost + runningGapCost;
                int max = a;
                if(b > max)
                    max = b;
                if(c > max)
                    max = c;
                E[x][y] = max;
                if(max == a)
                {
                    EA[x][y] = 1;
                    System.out.println(Messages.getString("Alignment.146"));
                } else
                {
                    EA[x][y] = 0;
                }
                if(max == b)
                {
                    System.out.println(Messages.getString("Alignment.147"));
                    EB[x][y] = 1;
                } else
                {
                    EB[x][y] = 0;
                }
                if(max == c)
                {
                    System.out.println(Messages.getString("Alignment.148"));
                    EC[x][y] = 1;
                } else
                {
                    EC[x][y] = 0;
                }
                System.out.println((new StringBuilder()).append("B(i,j) = ").append(max).toString());
                printE();
                a = theMatrix.valueAt(x - 1, y);
                a = a + initialGapCost + runningGapCost;
                b = E[x - 1][y];
                b = b + initialGapCost + runningGapCost;
                c = F[x - 1][y];
                c += runningGapCost;
                max = a;
                if(b > max)
                    max = b;
                if(c > max)
                    max = c;
                F[x][y] = max;
                if(max == a)
                {
                    System.out.println(Messages.getString("Alignment.150"));
                    FA[x][y] = 1;
                } else
                {
                    FA[x][y] = 0;
                }
                if(max == b)
                {
                    System.out.println(Messages.getString("Alignment.151"));
                    FB[x][y] = 1;
                } else
                {
                    FB[x][y] = 0;
                }
                if(max == c)
                {
                    System.out.println(Messages.getString("Alignment.279"));
                    FC[x][y] = 1;
                } else
                {
                    FC[x][y] = 0;
                }
                System.out.println((new StringBuilder()).append("C(i,j) = ").append(max).toString());
                printF();
                System.out.println((new StringBuilder()).append("char 1 = ").append(data[0].charAt(x - 1)).append(" char 2 = ").append(data[1].charAt(y - 1)).toString());
                int t;
                if(data[0].charAt(x - 1) == data[1].charAt(y - 1))
                    t = matchCost;
                else
                    t = mismatchCost;
                a = theMatrix.valueAt(x - 1, y - 1);
                b = E[x - 1][y - 1];
                c = F[x - 1][y - 1];
                max = a;
                if(b > max)
                    max = b;
                if(c > max)
                    max = c;
                if(max == a)
                {
                    System.out.println(Messages.getString("Alignment.156"));
                    GA[x][y] = 1;
                } else
                {
                    GA[x][y] = 0;
                }
                if(max == b)
                {
                    System.out.println(Messages.getString("Alignment.157"));
                    GB[x][y] = 1;
                } else
                {
                    GB[x][y] = 0;
                }
                if(max == c)
                {
                    System.out.println(Messages.getString("Alignment.158"));
                    GC[x][y] = 1;
                } else
                {
                    GC[x][y] = 0;
                }
                max += t;
                System.out.println((new StringBuilder()).append("V(i,j) = ").append(max).toString());
                theMatrix.add(max, x, y);
                printV();
                AlignmentNode tempNode = theMatrix.get(x, y);
                if(max - t == a)
                    tempNode.setTraceDiag(true);
                if(max - t == c)
                    tempNode.setTraceLeft(true);
                if(max - t == b)
                    tempNode.setTraceUp(true);
                setPosition("2.1.1.1");
            }

        }

        setPosition("2.2");
        printE();
        printF();
        printV();
    }

    protected void globalSimGapAlignment3()
    {
        aMatrix.setAllColor(DEFAULT_NODE_COLOR, AlignmentNode.DEFAULT_ARROW_COLOR);
        bMatrix.setAllColor(DEFAULT_NODE_COLOR, AlignmentNode.DEFAULT_ARROW_COLOR);
        cMatrix.setAllColor(DEFAULT_NODE_COLOR, AlignmentNode.DEFAULT_ARROW_COLOR);
        int negInf = -5000;
        aMatrix.add(0, 0, 0);
        bMatrix.add(0, 0, 0);
        cMatrix.add(0, 0, 0);
        setPosition("1.1");
        for(int i = 1; i < xSize; i++)
        {
            bMatrix.add(negInf, i, 0);
            cMatrix.add(initialGapCost + runningGapCost * i, i, 0);
            AlignmentNode tempNode = cMatrix.get(i, 0);
            tempNode.setTraceLeft(true);
            tempNode.setTraceC(true);
            aMatrix.add(negInf, i, 0);
        }

        setPosition("1.2");
        for(int i = 1; i < ySize; i++)
        {
            bMatrix.add(initialGapCost + runningGapCost * i, 0, i);
            AlignmentNode tempNode = bMatrix.get(0, i);
            tempNode.setTraceUp(true);
            tempNode.setTraceB(true);
            cMatrix.add(negInf, 0, i);
            aMatrix.add(negInf, 0, i);
        }

        setPosition("1.3");
        for(int y = 1; y < ySize; y++)
        {
            aMatrix.setYIndexHighlight(y);
            bMatrix.setYIndexHighlight(y);
            cMatrix.setYIndexHighlight(y);
            setPosition("2.1");
            for(int x = 1; x < xSize; x++)
            {
                aMatrix.setXIndexHighlight(x);
                bMatrix.setXIndexHighlight(x);
                cMatrix.setXIndexHighlight(x);
                setPosition("2.1.1");
                theMinimum = new AlignmentMinimum(Messages.getString("Alignment.281"));
                theMinimum.add();
                theMinimum.add();
                theMinimum.add();
                AlignmentNode tempNode = aMatrix.get(x, y - 1);
                tempNode.highlight();
                theMinimum.set(0, aMatrix.valueAt(x, y - 1), initialGapCost + runningGapCost);
                setPosition("2.1.1.1.2");
                tempNode.unHighlight();
                tempNode = bMatrix.get(x, y - 1);
                tempNode.highlight();
                theMinimum.set(1, bMatrix.valueAt(x, y - 1), runningGapCost);
                setPosition("2.1.1.1.4");
                tempNode.unHighlight();
                tempNode = cMatrix.get(x, y - 1);
                tempNode.highlight();
                theMinimum.set(2, cMatrix.valueAt(x, y - 1), initialGapCost + runningGapCost);
                setPosition("2.1.1.1.6");
                tempNode.unHighlight();
                int a = aMatrix.valueAt(x, y - 1) + initialGapCost + runningGapCost;
                int b = bMatrix.valueAt(x, y - 1) + runningGapCost;
                int c = cMatrix.valueAt(x, y - 1) + initialGapCost + runningGapCost;
                int max = Math.max(a, Math.max(b, c));
                bMatrix.add(max, x, y);
                tempNode = bMatrix.get(x, y);
                tempNode.setTraceUp(true);
                if(max == a)
                {
                    tempNode.highlightA();
                    aMatrix.get(x, y - 1).highlight();
                    theMinimum.highlight(0);
                    tempNode.setTraceA(true);
                } else
                {
                    aMatrix.get(x, y - 1).setIsDisabled(true);
                    tempNode.setTraceA(false);
                }
                if(max == b)
                {
                    tempNode.highlightB();
                    bMatrix.get(x, y - 1).highlight();
                    theMinimum.highlight(1);
                    tempNode.setTraceB(true);
                } else
                {
                    bMatrix.get(x, y - 1).setIsDisabled(true);
                    tempNode.setTraceB(false);
                }
                if(max == c)
                {
                    tempNode.highlightC();
                    cMatrix.get(x, y - 1).highlight();
                    theMinimum.highlight(2);
                    tempNode.setTraceC(true);
                } else
                {
                    cMatrix.get(x, y - 1).setIsDisabled(true);
                    tempNode.setTraceC(false);
                }
                tempNode.highlight();
                tempNode.setRingColor(HIGHLIGHT_RING_COLOR);
                setPosition("2.1.1.1");
                tempNode.unHighlightA();
                tempNode.unHighlightB();
                tempNode.unHighlightC();
                tempNode.setRingColor(DEFAULT_RING_COLOR);
                tempNode.unHighlight();
                aMatrix.get(x, y - 1).setIsDisabled(false);
                aMatrix.get(x, y - 1).unHighlight();
                bMatrix.get(x, y - 1).setIsDisabled(false);
                bMatrix.get(x, y - 1).unHighlight();
                cMatrix.get(x, y - 1).setIsDisabled(false);
                cMatrix.get(x, y - 1).unHighlight();
                theMinimum = null;
                theMinimum = new AlignmentMinimum(Messages.getString("Alignment.280"));
                theMinimum.add();
                theMinimum.add();
                theMinimum.add();
                tempNode = aMatrix.get(x - 1, y);
                tempNode.highlight();
                theMinimum.set(0, aMatrix.valueAt(x - 1, y), initialGapCost + runningGapCost);
                setPosition("2.1.1.2.2");
                tempNode.unHighlight();
                tempNode = bMatrix.get(x - 1, y);
                tempNode.highlight();
                theMinimum.set(1, bMatrix.valueAt(x - 1, y), initialGapCost + runningGapCost);
                setPosition("2.1.1.2.4");
                tempNode.unHighlight();
                tempNode = cMatrix.get(x - 1, y);
                tempNode.highlight();
                theMinimum.set(2, cMatrix.valueAt(x - 1, y), runningGapCost);
                setPosition("2.1.1.2.6");
                tempNode.unHighlight();
                a = aMatrix.valueAt(x - 1, y) + initialGapCost + runningGapCost;
                b = bMatrix.valueAt(x - 1, y) + initialGapCost + runningGapCost;
                c = cMatrix.valueAt(x - 1, y) + runningGapCost;
                max = Math.max(a, Math.max(b, c));
                cMatrix.add(max, x, y);
                tempNode = cMatrix.get(x, y);
                tempNode.setTraceLeft(true);
                if(max == a)
                {
                    tempNode.highlightA();
                    aMatrix.get(x - 1, y).highlight();
                    theMinimum.highlight(0);
                    tempNode.setTraceA(true);
                } else
                {
                    aMatrix.get(x - 1, y).setIsDisabled(true);
                    tempNode.setTraceA(false);
                }
                if(max == b)
                {
                    tempNode.highlightB();
                    bMatrix.get(x - 1, y).highlight();
                    theMinimum.highlight(1);
                    tempNode.setTraceB(true);
                } else
                {
                    bMatrix.get(x - 1, y).setIsDisabled(true);
                    tempNode.setTraceB(false);
                }
                if(max == c)
                {
                    tempNode.highlightC();
                    cMatrix.get(x - 1, y).highlight();
                    theMinimum.highlight(2);
                    tempNode.setTraceC(true);
                } else
                {
                    cMatrix.get(x - 1, y).setIsDisabled(true);
                    tempNode.setTraceC(false);
                }
                tempNode.highlight();
                tempNode.setRingColor(HIGHLIGHT_RING_COLOR);
                setPosition("2.1.1.2");
                tempNode.unHighlightA();
                tempNode.unHighlightB();
                tempNode.unHighlightC();
                tempNode.setRingColor(DEFAULT_RING_COLOR);
                tempNode.unHighlight();
                aMatrix.get(x - 1, y).setIsDisabled(false);
                aMatrix.get(x - 1, y).unHighlight();
                bMatrix.get(x - 1, y).setIsDisabled(false);
                bMatrix.get(x - 1, y).unHighlight();
                cMatrix.get(x - 1, y).setIsDisabled(false);
                cMatrix.get(x - 1, y).unHighlight();
                theMinimum = null;
                int t;
                if(data[0].charAt(x - 1) == data[1].charAt(y - 1))
                    t = matchCost;
                else
                    t = mismatchCost;
                theMinimum = new AlignmentMinimum("Maximum");
                theMinimum.add();
                theMinimum.add();
                theMinimum.add();
                tempNode = aMatrix.get(x - 1, y - 1);
                tempNode.highlight();
                theMinimum.set(0, aMatrix.valueAt(x - 1, y - 1), t);
                setPosition("2.1.1.3.1");
                tempNode.unHighlight();
                tempNode = bMatrix.get(x - 1, y - 1);
                tempNode.highlight();
                theMinimum.set(1, bMatrix.valueAt(x - 1, y - 1), t);
                setPosition("2.1.1.3.2");
                tempNode.unHighlight();
                tempNode = cMatrix.get(x - 1, y - 1);
                tempNode.highlight();
                theMinimum.set(2, cMatrix.valueAt(x - 1, y - 1), t);
                setPosition("2.1.1.3.3");
                tempNode.unHighlight();
                a = aMatrix.valueAt(x - 1, y - 1);
                b = bMatrix.valueAt(x - 1, y - 1);
                c = cMatrix.valueAt(x - 1, y - 1);
                max = Math.max(a, Math.max(b, c));
                aMatrix.add(max + t, x, y);
                tempNode = aMatrix.get(x, y);
                tempNode.setTraceDiag(true);
                if(max == a)
                {
                    tempNode.highlightA();
                    aMatrix.get(x - 1, y - 1).highlight();
                    theMinimum.highlight(0);
                    tempNode.setTraceA(true);
                } else
                {
                    aMatrix.get(x - 1, y - 1).setIsDisabled(true);
                    tempNode.setTraceA(false);
                }
                if(max == b)
                {
                    tempNode.highlightB();
                    bMatrix.get(x - 1, y - 1).highlight();
                    theMinimum.highlight(1);
                    tempNode.setTraceB(true);
                } else
                {
                    bMatrix.get(x - 1, y - 1).setIsDisabled(true);
                    tempNode.setTraceB(false);
                }
                if(max == c)
                {
                    tempNode.highlightC();
                    cMatrix.get(x - 1, y - 1).highlight();
                    theMinimum.highlight(2);
                    tempNode.setTraceC(true);
                } else
                {
                    cMatrix.get(x - 1, y - 1).setIsDisabled(true);
                    tempNode.setTraceC(false);
                }
                tempNode.highlight();
                tempNode.setRingColor(HIGHLIGHT_RING_COLOR);
                setPosition("2.1.1.3");
                tempNode.unHighlightA();
                tempNode.unHighlightB();
                tempNode.unHighlightC();
                tempNode.setRingColor(DEFAULT_RING_COLOR);
                tempNode.unHighlight();
                aMatrix.get(x - 1, y - 1).setIsDisabled(false);
                aMatrix.get(x - 1, y - 1).unHighlight();
                bMatrix.get(x - 1, y - 1).setIsDisabled(false);
                bMatrix.get(x - 1, y - 1).unHighlight();
                cMatrix.get(x - 1, y - 1).setIsDisabled(false);
                cMatrix.get(x - 1, y - 1).unHighlight();
                theMinimum = null;
            }

            setPosition("2.1.2");
        }

        setPosition("2.2");
    }

    protected void globalSimNoGapAlignment()
    {
        for(int i = 0; i < xSize; i++)
        {
            theMatrix.setXIndexHighlight(i);
            theMatrix.setYIndexHighlight(0);
            setPosition("1.1.1");
            theMatrix.add(i * spaceCost, i, 0);
            if(i != 0)
            {
                AlignmentNode tempNode = theMatrix.get(i, 0);
                tempNode.setTraceLeft(true);
            }
            setPosition("1.1.2");
        }

        theMatrix.setXIndexHighlight(-1);
        theMatrix.setYIndexHighlight(-1);
        setPosition("1.1.1");
        setPosition("1.1.4");
        for(int i = 0; i < ySize; i++)
        {
            theMatrix.setXIndexHighlight(0);
            theMatrix.setYIndexHighlight(i);
            setPosition("1.1.5");
            theMatrix.add(i * spaceCost, 0, i);
            if(i != 0)
            {
                AlignmentNode tempNode = theMatrix.get(0, i);
                tempNode.setTraceUp(true);
            }
            setPosition("1.1.6");
        }

        theMatrix.setXIndexHighlight(-1);
        theMatrix.setYIndexHighlight(-1);
        setPosition("1.1.5");
        setPosition("1.1.8");
        for(int y = 1; y < ySize; y++)
        {
            theMatrix.setYIndexHighlight(y);
            setPosition("2.1");
            for(int x = 1; x < xSize; x++)
            {
                theMatrix.setXIndexHighlight(x);
                setPosition("2.1.1");
                theMinimum = new AlignmentMinimum(Messages.getString("Alignment.110"));
                theMinimum.add();
                theMinimum.add();
                theMinimum.add();
                int t;
                if(data[0].charAt(x - 1) == data[1].charAt(y - 1))
                    t = matchCost;
                else
                    t = mismatchCost;
                AlignmentNode tempNode = theMatrix.get(x - 1, y);
                tempNode.highlight();
                theMinimum.set(0, theMatrix.valueAt(x - 1, y), spaceCost);
                setPosition("2.1.1.3");
                tempNode = theMatrix.get(x, y - 1);
                tempNode.highlight();
                theMinimum.set(1, theMatrix.valueAt(x, y - 1), spaceCost);
                setPosition("2.1.1.4");
                tempNode = theMatrix.get(x - 1, y - 1);
                tempNode.highlight();
                theMinimum.set(2, theMatrix.valueAt(x - 1, y - 1), t);
                setPosition("2.1.1.5");
                int max = theMatrix.valueAt(x - 1, y - 1) + t;
                if(theMatrix.valueAt(x - 1, y) + spaceCost > max)
                    max = theMatrix.valueAt(x - 1, y) + spaceCost;
                if(theMatrix.valueAt(x, y - 1) + spaceCost > max)
                    max = theMatrix.valueAt(x, y - 1) + spaceCost;
                theMatrix.add(max, x, y);
                tempNode = theMatrix.get(x, y);
                theMatrix.unHighlight();
                if(max == theMatrix.valueAt(x - 1, y - 1) + t)
                {
                    tempNode.setTraceDiag(true);
                    theMatrix.get(x - 1, y - 1).highlight();
                    theMinimum.highlight(2);
                }
                if(max == theMatrix.valueAt(x - 1, y) + spaceCost)
                {
                    tempNode.setTraceLeft(true);
                    theMatrix.get(x - 1, y).highlight();
                    theMinimum.highlight(0);
                }
                if(max == theMatrix.valueAt(x, y - 1) + spaceCost)
                {
                    tempNode.setTraceUp(true);
                    theMatrix.get(x, y - 1).highlight();
                    theMinimum.highlight(1);
                }
                setPosition("2.1.1.2");
                theMatrix.unHighlight();
                theMinimum.unHighlight();
                theMinimum = null;
            }

            setPosition("2.1.1");
        }

        setPosition("2.1");
        theMatrix.setXIndexHighlight(-1);
        theMatrix.setYIndexHighlight(-1);
        setPosition("2.2");
    }

    protected boolean hasQuestions()
    {
        return false;
    }

    protected void initialise()
    {
        String temp = data[0];
        data[0] = data[1];
        data[1] = temp;
        theMatrix = new AlignmentMatrix(data[0].length() + 1, data[1].length() + 1, data[0], data[1]);
        aMatrix = new AlignmentMatrix(data[0].length() + 1, data[1].length() + 1, data[0], data[1]);
        bMatrix = new AlignmentMatrix(data[0].length() + 1, data[1].length() + 1, data[0], data[1]);
        cMatrix = new AlignmentMatrix(data[0].length() + 1, data[1].length() + 1, data[0], data[1]);
        aMatrix.setIsGapMode(true);
        aMatrix.setMatrixName("A");
        aMatrix.setDrawName(true);
        aMatrix.setInternalBoxSize(2, 2);
        bMatrix.setIsGapMode(true);
        bMatrix.setMatrixName("B");
        bMatrix.setDrawName(true);
        bMatrix.setInternalBoxSize(2, 2);
        cMatrix.setIsGapMode(true);
        cMatrix.setMatrixName("C");
        cMatrix.setDrawName(true);
        cMatrix.setInternalBoxSize(2, 2);
        xSize = theMatrix.getXSize();
        ySize = theMatrix.getYSize();
        E = new int[xSize][ySize];
        F = new int[xSize][ySize];
        string1EA = null;
        string2EA = null;
        theMinimum = null;
        theAlignmentKey = null;
        the3D.initialiseColor();
        the3D.setVisible(false);
    }

    protected void localSimGapAlignment()
    {
        aMatrix.add(0, 0, 0);
        bMatrix.add(0, 0, 0);
        cMatrix.add(0, 0, 0);
        for(int i = 1; i < xSize; i++)
        {
            cMatrix.add(0, i, 0);
            aMatrix.add(0, i, 0);
            bMatrix.add(0, i, 0);
        }

        for(int i = 1; i < ySize; i++)
        {
            bMatrix.add(0, 0, i);
            cMatrix.add(0, 0, i);
            aMatrix.add(0, 0, i);
        }

        setPosition("1.1");
        for(int y = 1; y < ySize; y++)
        {
            setPosition("2.1");
            for(int x = 1; x < xSize; x++)
            {
                int a = aMatrix.valueAt(x, y - 1);
                a = a + initialGapCost + runningGapCost;
                int b = bMatrix.valueAt(x, y - 1);
                b += runningGapCost;
                int c = cMatrix.valueAt(x, y - 1);
                c = c + initialGapCost + runningGapCost;
                int max = a;
                if(b > max)
                    max = b;
                if(c > max)
                    max = c;
                bMatrix.add(max, x, y);
                AlignmentNode tempNode = bMatrix.get(x, y);
                tempNode.setTraceUp(true);
                if(max == a)
                    tempNode.setTraceA(true);
                else
                    tempNode.setTraceA(false);
                if(max == b)
                    tempNode.setTraceB(true);
                else
                    tempNode.setTraceB(false);
                if(max == c)
                    tempNode.setTraceC(true);
                else
                    tempNode.setTraceC(false);
                a = aMatrix.valueAt(x - 1, y);
                a = a + initialGapCost + runningGapCost;
                b = bMatrix.valueAt(x - 1, y);
                b = b + initialGapCost + runningGapCost;
                c = cMatrix.valueAt(x - 1, y);
                c += runningGapCost;
                max = a;
                if(b > max)
                    max = b;
                if(c > max)
                    max = c;
                cMatrix.add(max, x, y);
                tempNode = cMatrix.get(x, y);
                tempNode.setTraceLeft(true);
                if(max == a)
                    tempNode.setTraceA(true);
                else
                    tempNode.setTraceA(false);
                if(max == b)
                    tempNode.setTraceB(true);
                else
                    tempNode.setTraceB(false);
                if(max == c)
                    tempNode.setTraceC(true);
                else
                    tempNode.setTraceC(false);
                int t;
                if(data[0].charAt(x - 1) == data[1].charAt(y - 1))
                    t = matchCost;
                else
                    t = mismatchCost;
                a = aMatrix.valueAt(x - 1, y - 1);
                b = bMatrix.valueAt(x - 1, y - 1);
                c = cMatrix.valueAt(x - 1, y - 1);
                max = a;
                if(b > max)
                    max = b;
                if(c > max)
                    max = c;
                aMatrix.add(max + t, x, y);
                tempNode = aMatrix.get(x, y);
                tempNode.setTraceDiag(true);
                if(max == a)
                    tempNode.setTraceA(true);
                else
                    tempNode.setTraceA(false);
                if(max == b)
                    tempNode.setTraceB(true);
                else
                    tempNode.setTraceB(false);
                if(max == c)
                    tempNode.setTraceC(true);
                else
                    tempNode.setTraceC(false);
                setPosition("2.1.1.1");
            }

        }

        setPosition("2.2");
    }

    protected void localSimNoGapAlignment()
    {
        for(int i = 0; i < xSize; i++)
        {
            theMatrix.setXIndexHighlight(i);
            theMatrix.setYIndexHighlight(0);
            setPosition("1.1.1");
            theMatrix.add(0, i, 0);
            setPosition("1.1.2");
        }

        theMatrix.setXIndexHighlight(-1);
        theMatrix.setYIndexHighlight(-1);
        setPosition("1.1.1");
        setPosition("1.1.4");
        for(int i = 0; i < ySize; i++)
        {
            theMatrix.setXIndexHighlight(0);
            theMatrix.setYIndexHighlight(i);
            setPosition("1.1.5");
            theMatrix.add(0, 0, i);
            setPosition("1.1.6");
        }

        theMatrix.setXIndexHighlight(-1);
        theMatrix.setYIndexHighlight(-1);
        setPosition("1.1.5");
        setPosition("1.1.8");
        for(int y = 1; y < ySize; y++)
        {
            theMatrix.setYIndexHighlight(y);
            setPosition("2.1");
            for(int x = 1; x < xSize; x++)
            {
                int d = spaceCost;
                theMatrix.setXIndexHighlight(x);
                setPosition("2.1.1");
                theMinimum = new AlignmentMinimum(Messages.getString("Alignment.191"));
                theMinimum.add();
                theMinimum.add();
                theMinimum.add();
                theMinimum.add();
                int t;
                if(data[0].charAt(x - 1) == data[1].charAt(y - 1))
                    t = matchCost;
                else
                    t = mismatchCost;
                tempNode = theMatrix.get(x - 1, y);
                tempNode.highlight();
                theMinimum.set(0, theMatrix.valueAt(x - 1, y), spaceCost);
                setPosition("2.1.1.3");
                tempNode = theMatrix.get(x, y - 1);
                tempNode.highlight();
                theMinimum.set(1, theMatrix.valueAt(x, y - 1), spaceCost);
                setPosition("2.1.1.4");
                tempNode = theMatrix.get(x - 1, y - 1);
                tempNode.highlight();
                theMinimum.set(2, theMatrix.valueAt(x - 1, y - 1), t);
                setPosition("2.1.1.5");
                theMinimum.set(3, 0, 0);
                setPosition("2.1.1.6");
                int max = 0;
                if(theMatrix.valueAt(x - 1, y - 1) + t > max)
                    max = theMatrix.valueAt(x - 1, y - 1) + t;
                if(theMatrix.valueAt(x - 1, y) + d > max)
                    max = theMatrix.valueAt(x - 1, y) + d;
                if(theMatrix.valueAt(x, y - 1) + d > max)
                    max = theMatrix.valueAt(x, y - 1) + d;
                theMatrix.add(max, x, y);
                tempNode = theMatrix.get(x, y);
                theMatrix.unHighlight();
                if(max == theMatrix.valueAt(x - 1, y - 1) + t)
                {
                    theMatrix.get(x - 1, y - 1).highlight();
                    theMinimum.highlight(2);
                    tempNode.setTraceDiag(true);
                }
                if(max == theMatrix.valueAt(x - 1, y) + d)
                {
                    tempNode.setTraceLeft(true);
                    theMatrix.get(x - 1, y).highlight();
                    theMinimum.highlight(0);
                }
                if(max == theMatrix.valueAt(x, y - 1) + d)
                {
                    tempNode.setTraceUp(true);
                    theMatrix.get(x, y - 1).highlight();
                    theMinimum.highlight(1);
                }
                if(max == 0)
                    theMinimum.highlight(3);
                setPosition("2.1.1.2");
                theMatrix.unHighlight();
                theMinimum.unHighlight();
                theMinimum = null;
            }

            setPosition("2.1.1");
        }

        setPosition("2.1");
        theMatrix.setXIndexHighlight(-1);
        theMatrix.setYIndexHighlight(-1);
        setPosition("2.2");
    }

    public void moveX(int value)
    {
        the3D.moveX(value);
        repaint();
    }

    protected void noGapFindPathInteractive(int x, int y, Stack path)
    {
        path.push(new Point(x, y));
        AlignmentNode aNode = theMatrix.get(x, y);
        aNode.highlight();
        aNode.setRingColor(HIGHLIGHT_RING_COLOR);
        setPosition("3.3");
        if(!aNode.getTraceLeft() && !aNode.getTraceDiag() && !aNode.getTraceUp())
        {
            setPosition("3.4");
            path.pop();
            path.pop();
            return;
        }
        if(aNode.getTraceLeft())
        {
            setPosition("3.3.1");
            theMatrix.setXIndexHighlight(x - 1);
            theMatrix.get(x - 1, y).setRingColor(HIGHLIGHT_RING_COLOR);
            aNode.setRingColor(DEFAULT_RING_COLOR);
            aNode.highlightLeft();
            setPosition("3.3.1.2");
            path.push("L");
            noGapFindPathInteractive(x - 1, y, path);
        } else
        if(aNode.getTraceDiag())
        {
            setPosition("3.3.1");
            setPosition("3.3.2");
            theMatrix.setXIndexHighlight(x - 1);
            setPosition("3.3.2.2");
            theMatrix.setYIndexHighlight(y - 1);
            theMatrix.get(x - 1, y - 1).setRingColor(HIGHLIGHT_RING_COLOR);
            aNode.setRingColor(DEFAULT_RING_COLOR);
            aNode.highlightDiag();
            setPosition("3.3.2.3");
            path.push("D");
            noGapFindPathInteractive(x - 1, y - 1, path);
        } else
        if(aNode.getTraceUp())
        {
            setPosition("3.3.1");
            setPosition("3.3.2");
            setPosition("3.3.3");
            theMatrix.setYIndexHighlight(y - 1);
            theMatrix.get(x, y - 1).setRingColor(HIGHLIGHT_RING_COLOR);
            aNode.setRingColor(DEFAULT_RING_COLOR);
            aNode.highlightUp();
            setPosition("3.3.3.2");
            path.push("U");
            noGapFindPathInteractive(x, y - 1, path);
        }
        path.pop();
        path.pop();
    }

    protected void noGapFindPathNonInteractive(int x, int y, Stack path)
    {
        path.push(new Point(x, y));
        AlignmentNode aNode = theMatrix.get(x, y);
        if(!aNode.getTraceLeft() && !aNode.getTraceDiag() && !aNode.getTraceUp())
        {
            string1EA = new ElementArray(0, 0);
            string2EA = new ElementArray(0, 0);
            string1EA.setColumGap(0);
            string2EA.setColumGap(0);
            string1EA.setElementWidth(20);
            string2EA.setElementWidth(20);
            String trace = "";
            int count = 0;
            theMatrix.unHighlight();
            theMatrix.unHighlightTrace();
            theMatrix.setAllColor(DEFAULT_NODE_COLOR, AlignmentNode.DEFAULT_ARROW_COLOR);
            for(Stack newPath = (Stack)path.clone(); !newPath.empty();)
            {
                Point pnt = (Point)newPath.pop();
                AlignmentNode tempNode = theMatrix.get(pnt.x, pnt.y);
                tempNode.highlight();
                if(trace == "D")
                {
                    string1EA.setValue(count, new Node((new StringBuilder()).append(data[0].charAt(pnt.x - 1)).append("").toString(), 0));
                    string2EA.setValue(count, new Node((new StringBuilder()).append(data[1].charAt(pnt.y - 1)).append("").toString(), 0));
                    tempNode.highlightDiag();
                    count++;
                }
                if(trace == "L")
                {
                    string1EA.setValue(count, new Node((new StringBuilder()).append(data[0].charAt(pnt.x - 1)).append("").toString(), 0));
                    string2EA.setValue(count, new Node("-", 0));
                    tempNode.highlightLeft();
                    count++;
                }
                if(trace == "U")
                {
                    string1EA.setValue(count, new Node("-", 0));
                    string2EA.setValue(count, new Node((new StringBuilder()).append(data[1].charAt(pnt.y - 1)).append("").toString(), 0));
                    tempNode.highlightUp();
                    count++;
                }
                trace = (String)newPath.pop();
            }

            if(isFirstAlignment)
            {
                isFirstAlignment = false;
                setPosition("3.5");
                setPosition("3.6");
            } else
            {
                setPosition("3.6.1");
                setPosition("3.6");
            }
        }
        if(aNode.getTraceLeft())
        {
            path.push("L");
            noGapFindPathNonInteractive(x - 1, y, path);
        }
        if(aNode.getTraceDiag())
        {
            path.push("D");
            noGapFindPathNonInteractive(x - 1, y - 1, path);
        }
        if(aNode.getTraceUp())
        {
            path.push("U");
            noGapFindPathNonInteractive(x, y - 1, path);
        }
        path.pop();
        path.pop();
    }

    protected void printD()
    {
        System.out.println("D() = ");
        for(int x = 0; x < xSize; x++)
        {
            for(int y = 0; y < ySize; y++)
            {
                String str = (new StringBuilder()).append(D[x][y]).append("").toString();
                String space1 = new String();
                if(str.length() == 1)
                    space1 = new String("   ");
                if(str.length() == 2)
                    space1 = new String("  ");
                if(str.length() == 3)
                    space1 = new String(" ");
                System.out.print((new StringBuilder()).append(str).append(space1).toString());
            }

            System.out.println();
        }

    }

    protected void printE()
    {
        System.out.println("E() = ");
        for(int y = 0; y < ySize; y++)
        {
            for(int x = 0; x < xSize; x++)
            {
                String str = (new StringBuilder()).append(E[x][y]).append("").toString();
                if(EA[x][y] == 1)
                    str = (new StringBuilder()).append(str).append("A").toString();
                if(EB[x][y] == 1)
                    str = (new StringBuilder()).append(str).append("B").toString();
                if(EC[x][y] == 1)
                    str = (new StringBuilder()).append(str).append("C").toString();
                String space1 = new String();
                if(str.length() == 1)
                    space1 = new String("      ");
                if(str.length() == 2)
                    space1 = new String("     ");
                if(str.length() == 3)
                    space1 = new String("    ");
                if(str.length() == 4)
                    space1 = new String("   ");
                if(str.length() == 5)
                    space1 = new String("  ");
                if(str.length() == 6)
                    space1 = new String(" ");
                System.out.print((new StringBuilder()).append(str).append(space1).toString());
            }

            System.out.println();
        }

    }

    protected void printF()
    {
        System.out.println("F() = ");
        for(int y = 0; y < ySize; y++)
        {
            for(int x = 0; x < xSize; x++)
            {
                String str = (new StringBuilder()).append(F[x][y]).append("").toString();
                String space1 = new String();
                if(FA[x][y] == 1)
                    str = (new StringBuilder()).append(str).append("A").toString();
                if(FB[x][y] == 1)
                    str = (new StringBuilder()).append(str).append("B").toString();
                if(FC[x][y] == 1)
                    str = (new StringBuilder()).append(str).append("C").toString();
                if(str.length() == 1)
                    space1 = new String("      ");
                if(str.length() == 2)
                    space1 = new String("     ");
                if(str.length() == 3)
                    space1 = new String("    ");
                if(str.length() == 4)
                    space1 = new String("   ");
                if(str.length() == 5)
                    space1 = new String("  ");
                if(str.length() == 6)
                    space1 = new String(" ");
                System.out.print((new StringBuilder()).append(str).append(space1).toString());
            }

            System.out.println();
        }

    }

    protected void printV()
    {
        System.out.println("V() = ");
        for(int y = 0; y < ySize; y++)
        {
            for(int x = 0; x < xSize; x++)
            {
                String str = (new StringBuilder()).append(theMatrix.valueAt(x, y)).append("").toString();
                String space1 = new String();
                if(GA[x][y] == 1)
                    str = (new StringBuilder()).append(str).append("A").toString();
                if(GB[x][y] == 1)
                    str = (new StringBuilder()).append(str).append("B").toString();
                if(GC[x][y] == 1)
                    str = (new StringBuilder()).append(str).append("C").toString();
                if(str.length() == 1)
                    space1 = new String(Messages.getString("Alignment.277"));
                if(str.length() == 2)
                    space1 = new String("     ");
                if(str.length() == 3)
                    space1 = new String("    ");
                if(str.length() == 4)
                    space1 = new String("   ");
                if(str.length() == 5)
                    space1 = new String("  ");
                if(str.length() == 6)
                    space1 = new String(" ");
                System.out.print((new StringBuilder()).append(str).append(space1).toString());
            }

            System.out.println();
        }

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
        {
            nodeColor = colorSelection.getColor();
            Node.setDefaultBackgroundColor(nodeColor);
        }
    }

    public void reInitialise(Object data)
    {
        this.data = (String[])(String[])data;
        initialise();
    }

    protected void removeAllAnimationRequests()
    {
    }

    protected void removeAllQuestions()
    {
    }

    public void rotateX(int value)
    {
        the3D.rotateX(value - xRotate);
        xRotate = value;
        repaint();
    }

    public void rotateY(int value)
    {
        the3D.rotateY(value - yRotate);
        yRotate = value;
        repaint();
    }

    public void rotateZ(int value)
    {
        the3D.rotateZ(value - zRotate);
        zRotate = value;
        repaint();
    }

    protected void run()
    {
        setPosition("0");
        if(runningMode == 1 && currentVariation == 1)
        {
            globalDistNoGapAlignment();
            traceBack(xSize - 1, ySize - 1);
            setPosition("4.0");
        } else
        if(runningMode == 1 && currentVariation == 2)
        {
            globalDistGapAlignment();
            int min = aMatrix.valueAt(xSize - 1, ySize - 1);
            int minPtr = 1;
            if(bMatrix.valueAt(xSize - 1, ySize - 1) < min)
            {
                minPtr = 2;
                min = bMatrix.valueAt(xSize - 1, ySize - 1);
            }
            if(cMatrix.valueAt(xSize - 1, ySize - 1) < min)
            {
                minPtr = 3;
                min = cMatrix.valueAt(xSize - 1, ySize - 1);
            }
            traceBack(xSize - 1, ySize - 1, minPtr);
            setPosition("4.0");
        } else
        if(runningMode == 2 && currentVariation == 1)
        {
            globalSimNoGapAlignment();
            traceBack(xSize - 1, ySize - 1);
            setPosition("4.0");
        } else
        if(runningMode == 2 && currentVariation == 2)
        {
            globalSimGapAlignment3();
            int max = aMatrix.valueAt(xSize - 1, ySize - 1);
            int maxPtr = 1;
            if(bMatrix.valueAt(xSize - 1, ySize - 1) > max)
            {
                maxPtr = 2;
                max = bMatrix.valueAt(xSize - 1, ySize - 1);
            }
            if(cMatrix.valueAt(xSize - 1, ySize - 1) > max)
            {
                maxPtr = 3;
                max = cMatrix.valueAt(xSize - 1, ySize - 1);
            }
            traceBack(xSize - 1, ySize - 1, maxPtr);
            setPosition("4.0");
        } else
        if(runningMode == 3 && currentVariation == 1)
        {
            localSimNoGapAlignment();
            traceBack(theMatrix.getMaxX(), theMatrix.getMaxY());
            setPosition("4.0");
        } else
        if(runningMode == 3 && currentVariation == 2)
        {
            localSimGapAlignment();
            traceBack(theMatrix.getMaxX(), theMatrix.getMaxY());
        }
    }

    public void setInitialGapCost(int cost)
    {
        initialGapCost = cost;
    }

    public void setMatchCost(int cost)
    {
        matchCost = cost;
    }

    public void setMismatchCost(int cost)
    {
        mismatchCost = cost;
    }

    public void setRunningGapCost(int cost)
    {
        runningGapCost = cost;
    }

    public void setSpaceCost(int cost)
    {
        spaceCost = cost;
    }

    public void setVariation(int state)
    {
        currentVariation = state;
    }

    protected void storeState(boolean forceStore)
    {
        super.storeState(forceStore);
    }

    protected void traceBack(int x, int y)
    {
        isFirstAlignment = true;
        Stack queue = new Stack();
        queue.push("");
        theMatrix.setXIndexHighlight(x);
        theMatrix.setYIndexHighlight(y);
        setPosition("3.1");
        noGapFindPathInteractive(x, y, queue);
        queue = new Stack();
        queue.push("");
        noGapFindPathNonInteractive(x, y, queue);
    }

    protected void traceBack(int x, int y, int a)
    {
        isFirstAlignment = true;
        Stack queue = new Stack();
        theAlignmentKey = new AlignmentKey();
        the3D.initialiseColor();
        the3D.setVisible(true);
        the3D.fillColor(2, 2, 0, highlightColor);
        the3D.fillColor(2, 2, 1, highlightColor);
        the3D.fillColor(2, 2, 2, highlightColor);
        aMatrix.setInternalBoxCoords(x, y);
        aMatrix.setDrawInternalBox(true);
        aMatrix.setXIndexHighlight(x);
        aMatrix.setYIndexHighlight(y);
        bMatrix.setInternalBoxCoords(x, y);
        bMatrix.setDrawInternalBox(true);
        bMatrix.setXIndexHighlight(x);
        bMatrix.setYIndexHighlight(y);
        cMatrix.setInternalBoxCoords(x, y);
        cMatrix.setDrawInternalBox(true);
        cMatrix.setXIndexHighlight(x);
        cMatrix.setYIndexHighlight(y);
        aMatrix.get(x, y).highlight();
        bMatrix.get(x, y).highlight();
        cMatrix.get(x, y).highlight();
        setPosition("3.1.1");
        if(a != 1)
        {
            aMatrix.unHighlight();
            aMatrix.unHighlightTrace();
            aMatrix.setAllColor(IRRELEVANT_COLOR, IRRELEVANT_ARROW_COLOR);
            the3D.fillColor(2, 2, 0, Color.white);
        }
        if(a != 2)
        {
            bMatrix.unHighlight();
            bMatrix.unHighlightTrace();
            bMatrix.setAllColor(IRRELEVANT_COLOR, IRRELEVANT_ARROW_COLOR);
            the3D.fillColor(2, 2, 1, Color.white);
        }
        if(a != 3)
        {
            cMatrix.unHighlight();
            cMatrix.unHighlightTrace();
            cMatrix.setAllColor(IRRELEVANT_COLOR, IRRELEVANT_ARROW_COLOR);
            the3D.fillColor(2, 2, 2, Color.white);
        }
        if(a == 1)
            aMatrix.setIsSelected(true);
        if(a == 2)
            bMatrix.setIsSelected(true);
        if(a == 3)
            cMatrix.setIsSelected(true);
        the3D.highlightX(a - 1);
        setPosition("3.2");
        the3D.unHighlightX(a - 1);
        aMatrix.unHighlight();
        aMatrix.unHighlightTrace();
        aMatrix.setAllColor(IRRELEVANT_COLOR, IRRELEVANT_ARROW_COLOR);
        bMatrix.unHighlight();
        bMatrix.unHighlightTrace();
        bMatrix.setAllColor(IRRELEVANT_COLOR, IRRELEVANT_ARROW_COLOR);
        cMatrix.unHighlight();
        cMatrix.unHighlightTrace();
        cMatrix.setAllColor(IRRELEVANT_COLOR, IRRELEVANT_ARROW_COLOR);
        aMatrix.get(x, y).setIsDisabled(true);
        bMatrix.get(x, y).setIsDisabled(true);
        cMatrix.get(x, y).setIsDisabled(true);
        gapFindPathInteractive(x, y, a, queue);
        theAlignmentKey = null;
        the3D.setVisible(false);
        queue = new Stack();
        gapFindPathNonInteractive(x, y, a, queue);
    }

    public void translateX(int value)
    {
        the3D.translateX(value);
        repaint();
    }

    public void translateY(int value)
    {
        the3D.translateY(value);
        repaint();
    }

    public void translateZ(int value)
    {
        the3D.translateZ(value);
        repaint();
    }

    public static final int EMPTY_MARKER = -10;
    public static final int SIM_DEFAULT_MATCHCOST = 1;
    public static final int SIM_DEFAULT_MISMATCHCOST = -4;
    public static final int SIM_DEFAULT_SPACECOST = -2;
    public static final int SIM_DEFAULT_INITIALGAPCOST = -2;
    public static final int SIM_DEFAULT_RUNNINGGAPCOST = -1;
    public static final int DIST_DEFAULT_MATCHCOST = 0;
    public static final int DIST_DEFAULT_MISMATCHCOST = 4;
    public static final int DIST_DEFAULT_SPACECOST = 2;
    public static final int DIST_DEFAULT_INITIALGAPCOST = 2;
    public static final int DIST_DEFAULT_RUNNINGGAPCOST = 1;
    public static final Color SteelBlue1;
    public static final Color SteelBlue2;
    public static final Color SteelBlue3;
    public static final Color SteelBlue4;
    public static final Color DEFAULT_NODE_COLOR;
    public static final Color DEFAULT_SELECTED_COLOR;
    public static final Color DEFAULT_HIGHLIGHT_COLOR;
    public static final Color DEFAULT_CURRENT_COLOR;
    public static final Color HIGHLIGHT_RING_COLOR = new Color(255, 0, 0);
    public static final Color DEFAULT_RING_COLOR = new Color(0, 0, 0);
    public static final Color SELECT_RING_COLOR = new Color(255, 0, 0);
    public static final Color IRRELEVANT_ARROW_COLOR;
    public static final Color IRRELEVANT_COLOR;
    public static final int NOGAPS = 1;
    public static final int WITHGAPS = 2;
    public static final int GLOBALDIST = 1;
    public static final int GLOBALSIM = 2;
    public static final int LOCALSIM = 3;
    public static int currentVariation;
    public static int runningMode;
    protected static final String HIGHLIGHT_COLOR = Messages.getString("Alignment.1");
    protected static final String NODE_COLOR = Messages.getString("Alignment.2");
    protected static boolean isFirstAlignment;
    protected static int xRotate = -65;
    protected static int yRotate = 0;
    protected static int zRotate = 0;
    protected String data[];
    protected AlignmentNode tempNode;
    protected int D[][];
    protected int E[][];
    protected byte EA[][];
    protected byte EB[][];
    protected byte EC[][];
    protected int F[][];
    protected byte FA[][];
    protected byte FB[][];
    protected byte FC[][];
    protected byte GA[][];
    protected byte GB[][];
    protected byte GC[][];
    protected AlignmentMatrix theMatrix;
    protected AlignmentMatrix aMatrix;
    protected AlignmentMatrix bMatrix;
    protected AlignmentMatrix cMatrix;
    protected AlignmentMatrix3D the3D;
    protected AlignmentKey theAlignmentKey;
    protected ElementArray string1EA;
    protected ElementArray string2EA;
    protected AlignmentMinimum theMinimum;
    protected int xSize;
    protected int ySize;
    protected int matchCost;
    protected int mismatchCost;
    protected int spaceCost;
    protected int initialGapCost;
    protected int runningGapCost;
    protected Color textColor;
    protected Color highlightColor;
    protected Color nodeColor;
    protected Color background;
    protected boolean drawSymbolArrayAndLines;

    static 
    {
        SteelBlue1 = new Color(99, 184, 255);
        SteelBlue2 = new Color(92, 172, 238);
        SteelBlue3 = new Color(79, 148, 205);
        SteelBlue4 = new Color(54, 100, 139);
        DEFAULT_NODE_COLOR = SteelBlue4;
        DEFAULT_SELECTED_COLOR = SteelBlue3;
        DEFAULT_HIGHLIGHT_COLOR = SteelBlue2;
        DEFAULT_CURRENT_COLOR = SteelBlue1;
        IRRELEVANT_ARROW_COLOR = Color.gray;
        IRRELEVANT_COLOR = Color.white;
    }
}
