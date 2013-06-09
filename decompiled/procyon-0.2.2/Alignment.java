import localization.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import com.cim.AIA.*;

public class Alignment extends Algorithm implements ColorSelectionListener
{
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
    public static final Color HIGHLIGHT_RING_COLOR;
    public static final Color DEFAULT_RING_COLOR;
    public static final Color SELECT_RING_COLOR;
    public static final Color IRRELEVANT_ARROW_COLOR;
    public static final Color IRRELEVANT_COLOR;
    public static final int NOGAPS = 1;
    public static final int WITHGAPS = 2;
    public static final int GLOBALDIST = 1;
    public static final int GLOBALSIM = 2;
    public static final int LOCALSIM = 3;
    public static int currentVariation;
    public static int runningMode;
    protected static final String HIGHLIGHT_COLOR;
    protected static final String NODE_COLOR;
    protected static boolean isFirstAlignment;
    protected static int xRotate;
    protected static int yRotate;
    protected static int zRotate;
    protected String[] data;
    protected AlignmentNode tempNode;
    protected int[][] D;
    protected int[][] E;
    protected byte[][] EA;
    protected byte[][] EB;
    protected byte[][] EC;
    protected int[][] F;
    protected byte[][] FA;
    protected byte[][] FB;
    protected byte[][] FC;
    protected byte[][] GA;
    protected byte[][] GB;
    protected byte[][] GC;
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
    
    protected static void setRunningMode(final String mode) {
        if (mode == null) {
            Alignment.runningMode = 1;
            return;
        }
        if (mode.equals("GLOBALDIST")) {
            Alignment.runningMode = 1;
        }
        if (mode.equals("GLOBALSIM")) {
            Alignment.runningMode = 2;
        }
        if (mode.equals("LOCALSIM")) {
            Alignment.runningMode = 3;
        }
    }
    
    public Alignment(final AlgorithmThread algorithmThread, final Object data) {
        super(algorithmThread, data);
        this.tempNode = new AlignmentNode("X", 1);
        this.the3D = new AlignmentMatrix3D();
        this.theAlignmentKey = null;
        this.textColor = Color.black;
        this.highlightColor = Alignment.DEFAULT_HIGHLIGHT_COLOR;
        this.nodeColor = Alignment.DEFAULT_NODE_COLOR;
        this.background = Color.white;
        this.drawSymbolArrayAndLines = true;
        this.reInitialise(data);
        Alignment.currentVariation = 1;
        final ConfigurationManager cm = ConfigurationManager.getConfigurationManager();
        final ColorSelection highlightSelection = new ColorSelection(this.highlightColor, Alignment.HIGHLIGHT_COLOR);
        highlightSelection.addClass(this);
        cm.addColorSelection(highlightSelection);
        final ColorSelection nodeSelection = new ColorSelection(this.nodeColor, Alignment.NODE_COLOR);
        nodeSelection.addClass(this);
        cm.addColorSelection(nodeSelection);
        cm.addColorSelectionListener(this);
        if (Alignment.runningMode == 1) {
            this.setMatchCost(0);
            this.setMismatchCost(4);
            this.setSpaceCost(2);
            this.setInitialGapCost(2);
            this.setRunningGapCost(1);
        }
        else {
            this.setMatchCost(1);
            this.setMismatchCost(-4);
            this.setSpaceCost(-2);
            this.setInitialGapCost(-2);
            this.setRunningGapCost(-1);
        }
    }
    
    protected void changeData(final Object data) {
        super.changeData(data);
    }
    
    protected void clearState() {
        super.clearState();
    }
    
    protected void gapFindPathInteractive(final int x, final int y, final int a, final Stack<Serializable> path) {
        path.push(new Point(x, y));
        path.push(new Integer(a));
        int newX = 0;
        int newY = 0;
        this.aMatrix.setXIndexHighlight(x);
        this.aMatrix.setYIndexHighlight(y);
        this.aMatrix.setInternalBoxCoords(x, y);
        this.bMatrix.setXIndexHighlight(x);
        this.bMatrix.setYIndexHighlight(y);
        this.bMatrix.setInternalBoxCoords(x, y);
        this.cMatrix.setXIndexHighlight(x);
        this.cMatrix.setYIndexHighlight(y);
        this.cMatrix.setInternalBoxCoords(x, y);
        AlignmentMatrix selectedMatrix;
        if (a == 1) {
            newX = x - 1;
            newY = y - 1;
            selectedMatrix = this.aMatrix;
        }
        else if (a == 2) {
            newX = x;
            newY = y - 1;
            selectedMatrix = this.bMatrix;
        }
        else {
            newX = x - 1;
            newY = y;
            selectedMatrix = this.cMatrix;
        }
        selectedMatrix.setIsSelected(true);
        final AlignmentNode aNode = selectedMatrix.get(x, y);
        aNode.setBackgroundColor(Alignment.DEFAULT_CURRENT_COLOR);
        aNode.setRingColor(Alignment.HIGHLIGHT_RING_COLOR);
        this.the3D.initialiseColor();
        this.the3D.disable(2, 2);
        this.the3D.darkenX(a - 1);
        this.the3D.fillColor(2, 2, a - 1, Alignment.DEFAULT_CURRENT_COLOR);
        this.the3D.highlightX(2, 2, a - 1);
        this.setPosition("3.3");
        if (!aNode.getTraceUp() && !aNode.getTraceDiag() && !aNode.getTraceLeft()) {
            aNode.highlight();
            aNode.setRingColor(Alignment.DEFAULT_RING_COLOR);
            this.aMatrix.setXIndexHighlight(-1);
            this.aMatrix.setYIndexHighlight(-1);
            this.bMatrix.setXIndexHighlight(-1);
            this.bMatrix.setYIndexHighlight(-1);
            this.cMatrix.setXIndexHighlight(-1);
            this.cMatrix.setYIndexHighlight(-1);
            this.aMatrix.setDrawInternalBox(false);
            this.bMatrix.setDrawInternalBox(false);
            this.cMatrix.setDrawInternalBox(false);
            selectedMatrix.setIsSelected(false);
            this.setPosition("3.4.0");
            path.pop();
            path.pop();
            return;
        }
        int _3DX;
        int _3DY;
        if (a == 1) {
            this.setPosition("3.3.1");
            this.aMatrix.setXIndexHighlight(x - 1);
            this.bMatrix.setXIndexHighlight(x - 1);
            this.cMatrix.setXIndexHighlight(x - 1);
            this.aMatrix.setYIndexHighlight(y - 1);
            this.bMatrix.setYIndexHighlight(y - 1);
            this.cMatrix.setYIndexHighlight(y - 1);
            aNode.highlightDiag();
            this.aMatrix.get(x - 1, y - 1).setRingColor(Alignment.SELECT_RING_COLOR);
            this.bMatrix.get(x - 1, y - 1).setRingColor(Alignment.SELECT_RING_COLOR);
            this.cMatrix.get(x - 1, y - 1).setRingColor(Alignment.SELECT_RING_COLOR);
            _3DX = 1;
            _3DY = 1;
            this.the3D.highlightX(_3DX, _3DY);
            this.setPosition("3.3.1.1");
        }
        else if (a == 2) {
            this.setPosition("3.3.1");
            this.setPosition("3.3.2");
            this.aMatrix.setYIndexHighlight(y - 1);
            this.bMatrix.setYIndexHighlight(y - 1);
            this.cMatrix.setYIndexHighlight(y - 1);
            aNode.highlightUp();
            this.aMatrix.get(x, y - 1).setRingColor(Alignment.SELECT_RING_COLOR);
            this.bMatrix.get(x, y - 1).setRingColor(Alignment.SELECT_RING_COLOR);
            this.cMatrix.get(x, y - 1).setRingColor(Alignment.SELECT_RING_COLOR);
            _3DX = 2;
            _3DY = 1;
            this.the3D.highlightX(_3DX, _3DY);
            this.setPosition("3.3.2.1");
        }
        else {
            this.setPosition("3.3.1");
            this.setPosition("3.3.2");
            this.setPosition("3.3.3");
            this.aMatrix.setXIndexHighlight(x - 1);
            this.bMatrix.setXIndexHighlight(x - 1);
            this.cMatrix.setXIndexHighlight(x - 1);
            aNode.highlightLeft();
            this.aMatrix.get(x - 1, y).setRingColor(Alignment.SELECT_RING_COLOR);
            this.bMatrix.get(x - 1, y).setRingColor(Alignment.SELECT_RING_COLOR);
            this.cMatrix.get(x - 1, y).setRingColor(Alignment.SELECT_RING_COLOR);
            _3DX = 1;
            _3DY = 2;
            this.the3D.highlightX(_3DX, _3DY);
            this.setPosition("3.3.3.1");
        }
        if (aNode.getTraceA()) {
            this.setPosition("3.3.4.1.1");
            aNode.highlightA();
            this.aMatrix.get(newX, newY).setBackgroundColor(Alignment.DEFAULT_NODE_COLOR);
            this.aMatrix.get(newX, newY).setRingColor(Alignment.DEFAULT_RING_COLOR);
            this.the3D.fillColor(_3DX, _3DY, 0, Alignment.DEFAULT_NODE_COLOR);
            this.the3D.unHighlightX(_3DX, _3DY, 0);
            this.setPosition("3.3.4.1.2");
        }
        else {
            this.setPosition("3.3.4.1.1");
            this.setPosition("3.3.4.1.3");
            this.aMatrix.get(newX, newY).setIsDisabled(true);
            this.aMatrix.get(newX, newY).setRingColor(Alignment.DEFAULT_RING_COLOR);
            this.the3D.disable(_3DX, _3DY, 0);
            this.the3D.unHighlightX(_3DX, _3DY, 0);
            this.setPosition("3.3.4.1.4");
        }
        if (aNode.getTraceB()) {
            this.setPosition("3.3.4.1.5");
            aNode.highlightB();
            this.bMatrix.get(newX, newY).setBackgroundColor(Alignment.DEFAULT_NODE_COLOR);
            this.bMatrix.get(newX, newY).setRingColor(Alignment.DEFAULT_RING_COLOR);
            this.the3D.fillColor(_3DX, _3DY, 1, Alignment.DEFAULT_NODE_COLOR);
            this.the3D.unHighlightX(_3DX, _3DY, 1);
            this.setPosition("3.3.4.1.6");
        }
        else {
            this.setPosition("3.3.4.1.5");
            this.setPosition("3.3.4.1.7");
            this.bMatrix.get(newX, newY).setIsDisabled(true);
            this.bMatrix.get(newX, newY).setRingColor(Alignment.DEFAULT_RING_COLOR);
            this.the3D.disable(_3DX, _3DY, 1);
            this.the3D.unHighlightX(_3DX, _3DY, 1);
            this.setPosition("3.3.4.1.8");
        }
        if (aNode.getTraceC()) {
            this.setPosition("3.3.4.1.9");
            aNode.highlightC();
            this.cMatrix.get(newX, newY).setBackgroundColor(Alignment.DEFAULT_NODE_COLOR);
            this.cMatrix.get(newX, newY).setRingColor(Alignment.DEFAULT_RING_COLOR);
            this.the3D.fillColor(_3DX, _3DY, 2, Alignment.DEFAULT_NODE_COLOR);
            this.the3D.unHighlightX(_3DX, _3DY, 2);
            this.setPosition("3.3.4.1.10");
        }
        else {
            this.setPosition("3.3.4.1.9");
            this.setPosition("3.3.4.1.11");
            this.cMatrix.get(newX, newY).setIsDisabled(true);
            this.cMatrix.get(newX, newY).setRingColor(Alignment.DEFAULT_RING_COLOR);
            this.the3D.disable(_3DX, _3DY, 2);
            this.the3D.unHighlightX(_3DX, _3DY, 2);
            this.setPosition("3.3.4.1.12");
        }
        aNode.unHighlightA();
        aNode.unHighlightB();
        aNode.unHighlightC();
        if (aNode.getTraceA()) {
            aNode.highlightA();
            this.aMatrix.get(newX, newY).setBackgroundColor(Alignment.DEFAULT_SELECTED_COLOR);
            this.cMatrix.get(newX, newY).setIsDisabled(true);
            this.bMatrix.get(newX, newY).setIsDisabled(true);
            this.the3D.fillColor(_3DX, _3DY, 0, Alignment.DEFAULT_SELECTED_COLOR);
            this.setPosition("3.3.4.2");
            this.setPosition("3.3.4.3");
            aNode.setRingColor(Alignment.DEFAULT_RING_COLOR);
            this.aMatrix.get(newX, newY).setBackgroundColor(Alignment.DEFAULT_CURRENT_COLOR);
            this.aMatrix.get(newX, newY).setRingColor(Alignment.HIGHLIGHT_RING_COLOR);
            this.aMatrix.setIsSelected(true);
            aNode.highlight();
            selectedMatrix.setIsSelected(false);
            this.aMatrix.setIsSelected(true);
            this.the3D.disable(_3DX, _3DY);
            this.the3D.fillColor(_3DX, _3DY, 0, Alignment.DEFAULT_CURRENT_COLOR);
            this.the3D.unDarkenX(a - 1);
            this.the3D.darkenX(0);
            this.the3D.highlightX(_3DX, _3DY, 0);
            this.the3D.fillColor(2, 2, a - 1, this.highlightColor);
            this.the3D.unHighlightX(2, 2, a - 1);
            this.setPosition("3.3.4.4");
            this.gapFindPathInteractive(newX, newY, 1, path);
        }
        else if (aNode.getTraceB()) {
            aNode.highlightB();
            this.bMatrix.get(newX, newY).setBackgroundColor(Alignment.DEFAULT_SELECTED_COLOR);
            this.cMatrix.get(newX, newY).setIsDisabled(true);
            this.aMatrix.get(newX, newY).setIsDisabled(true);
            this.the3D.fillColor(_3DX, _3DY, 1, Alignment.DEFAULT_SELECTED_COLOR);
            this.setPosition("3.3.4.2");
            this.setPosition("3.3.4.3");
            this.setPosition("3.3.4.5");
            aNode.setRingColor(Alignment.DEFAULT_RING_COLOR);
            this.bMatrix.get(newX, newY).setBackgroundColor(Alignment.DEFAULT_CURRENT_COLOR);
            this.bMatrix.get(newX, newY).setRingColor(Alignment.HIGHLIGHT_RING_COLOR);
            this.bMatrix.setIsSelected(true);
            aNode.highlight();
            selectedMatrix.setIsSelected(false);
            this.bMatrix.setIsSelected(true);
            this.the3D.disable(_3DX, _3DY);
            this.the3D.fillColor(_3DX, _3DY, 1, Alignment.DEFAULT_CURRENT_COLOR);
            this.the3D.unDarkenX(a - 1);
            this.the3D.darkenX(1);
            this.the3D.highlightX(_3DX, _3DY, 1);
            this.the3D.fillColor(2, 2, a - 1, this.highlightColor);
            this.the3D.unHighlightX(2, 2, a - 1);
            this.setPosition("3.3.4.6");
            this.gapFindPathInteractive(newX, newY, 2, path);
        }
        else if (aNode.getTraceC()) {
            aNode.highlightC();
            this.cMatrix.get(newX, newY).setBackgroundColor(Alignment.DEFAULT_SELECTED_COLOR);
            this.aMatrix.get(newX, newY).setIsDisabled(true);
            this.bMatrix.get(newX, newY).setIsDisabled(true);
            this.the3D.fillColor(_3DX, _3DY, 2, Alignment.DEFAULT_SELECTED_COLOR);
            this.setPosition("3.3.4.2");
            this.setPosition("3.3.4.3");
            this.setPosition("3.3.4.5");
            this.setPosition("3.3.4.7");
            aNode.setRingColor(Alignment.DEFAULT_RING_COLOR);
            this.cMatrix.get(newX, newY).setBackgroundColor(Alignment.DEFAULT_CURRENT_COLOR);
            this.cMatrix.get(newX, newY).setRingColor(Alignment.HIGHLIGHT_RING_COLOR);
            this.cMatrix.setIsSelected(true);
            aNode.highlight();
            selectedMatrix.setIsSelected(false);
            this.cMatrix.setIsSelected(true);
            this.the3D.disable(_3DX, _3DY);
            this.the3D.fillColor(_3DX, _3DY, 2, Alignment.DEFAULT_CURRENT_COLOR);
            this.the3D.unDarkenX(a - 1);
            this.the3D.darkenX(2);
            this.the3D.highlightX(_3DX, _3DY, 2);
            this.the3D.fillColor(2, 2, a - 1, this.highlightColor);
            this.the3D.unHighlightX(2, 2, a - 1);
            this.setPosition("3.3.4.8");
            this.gapFindPathInteractive(newX, newY, 3, path);
        }
        path.pop();
        path.pop();
        this.aMatrix.unHighlight();
        this.aMatrix.unHighlightTrace();
        this.aMatrix.setAllColor(Alignment.DEFAULT_NODE_COLOR, Color.black);
        this.bMatrix.unHighlight();
        this.bMatrix.unHighlightTrace();
        this.bMatrix.setAllColor(Alignment.DEFAULT_NODE_COLOR, Color.black);
        this.cMatrix.unHighlight();
        this.cMatrix.unHighlightTrace();
        this.cMatrix.setAllColor(Alignment.DEFAULT_NODE_COLOR, Color.black);
    }
    
    protected void gapFindPathNonInteractive(final int x, final int y, final int a, final Stack<Serializable> path) {
        this.aMatrix.unHighlight();
        this.aMatrix.unHighlightTrace();
        this.aMatrix.setAllColor(Alignment.DEFAULT_NODE_COLOR, Color.black);
        this.bMatrix.unHighlight();
        this.bMatrix.unHighlightTrace();
        this.bMatrix.setAllColor(Alignment.DEFAULT_NODE_COLOR, Color.black);
        this.cMatrix.unHighlight();
        this.cMatrix.unHighlightTrace();
        this.cMatrix.setAllColor(Alignment.DEFAULT_NODE_COLOR, Color.black);
        path.push(new Point(x, y));
        path.push(new Integer(a));
        int newX = 0;
        int newY = 0;
        AlignmentMatrix selectedMatrix;
        if (a == 1) {
            newX = x - 1;
            newY = y - 1;
            selectedMatrix = this.aMatrix;
        }
        else if (a == 2) {
            newX = x;
            newY = y - 1;
            selectedMatrix = this.bMatrix;
        }
        else {
            newX = x - 1;
            newY = y;
            selectedMatrix = this.cMatrix;
        }
        final AlignmentNode aNode = selectedMatrix.get(x, y);
        aNode.highlight();
        if (!aNode.getTraceUp() && !aNode.getTraceDiag() && !aNode.getTraceLeft()) {
            this.string1EA = new ElementArray(0, 0);
            this.string2EA = new ElementArray(0, 0);
            this.string1EA.setColumGap(0);
            this.string2EA.setColumGap(0);
            this.string1EA.setElementWidth(20);
            this.string2EA.setElementWidth(20);
            int prevTrace = a;
            int trace = 0;
            int count = 0;
            this.aMatrix.unHighlight();
            this.aMatrix.unHighlightTrace();
            this.aMatrix.setAllColor(Alignment.IRRELEVANT_COLOR, Alignment.IRRELEVANT_ARROW_COLOR);
            this.bMatrix.unHighlight();
            this.bMatrix.unHighlightTrace();
            this.bMatrix.setAllColor(Alignment.IRRELEVANT_COLOR, Alignment.IRRELEVANT_ARROW_COLOR);
            this.cMatrix.unHighlight();
            this.cMatrix.unHighlightTrace();
            this.cMatrix.setAllColor(Alignment.IRRELEVANT_COLOR, Alignment.IRRELEVANT_ARROW_COLOR);
            path.pop();
            path.push(new Integer(0));
            final Stack<Serializable> newPath = (Stack<E>)path.clone();
            while (!newPath.empty()) {
                AlignmentNode tempNode = null;
                if (trace != 0) {
                    prevTrace = trace;
                }
                trace = ((Integer)newPath.pop()).intValue();
                final Point pnt = (Point)newPath.pop();
                if (trace == 1) {
                    selectedMatrix = this.aMatrix;
                    tempNode = this.aMatrix.get(pnt.x, pnt.y);
                    tempNode.highlight();
                }
                if (trace == 2) {
                    selectedMatrix = this.bMatrix;
                    tempNode = this.bMatrix.get(pnt.x, pnt.y);
                    tempNode.highlight();
                }
                if (trace == 3) {
                    selectedMatrix = this.cMatrix;
                    tempNode = this.cMatrix.get(pnt.x, pnt.y);
                    tempNode.highlight();
                }
                if (trace == 0) {
                    if (prevTrace == 1) {
                        this.aMatrix.get(pnt.x, pnt.y).highlight();
                    }
                    if (prevTrace == 2) {
                        this.bMatrix.get(pnt.x, pnt.y).highlight();
                    }
                    if (prevTrace == 3) {
                        this.cMatrix.get(pnt.x, pnt.y).highlight();
                    }
                }
                if (trace == 1) {
                    this.string1EA.setValue(count, new Node(this.data[0].charAt(pnt.x - 1) + "", 0));
                    this.string2EA.setValue(count, new Node(this.data[1].charAt(pnt.y - 1) + "", 0));
                    tempNode.highlightDiag();
                    if (prevTrace == 1) {
                        tempNode.highlightA();
                    }
                    if (prevTrace == 2) {
                        tempNode.highlightB();
                    }
                    if (prevTrace == 3) {
                        tempNode.highlightC();
                    }
                    if (prevTrace != trace) {
                        selectedMatrix.get(pnt.x - 1, pnt.y - 1).setIsDisabled(true);
                        tempNode.unHighlightDiag();
                        tempNode.setArrowColor(Color.black);
                    }
                    ++count;
                }
                if (trace == 3) {
                    this.string1EA.setValue(count, new Node(this.data[0].charAt(pnt.x - 1) + "", 0));
                    this.string2EA.setValue(count, new Node("-", 0));
                    tempNode.highlightLeft();
                    if (prevTrace == 1) {
                        tempNode.highlightA();
                    }
                    if (prevTrace == 2) {
                        tempNode.highlightB();
                    }
                    if (prevTrace == 3) {
                        tempNode.highlightC();
                    }
                    if (prevTrace != trace) {
                        selectedMatrix.get(pnt.x - 1, pnt.y).setIsDisabled(true);
                        tempNode.unHighlightLeft();
                        tempNode.setArrowColor(Color.black);
                    }
                    ++count;
                }
                if (trace == 2) {
                    this.string1EA.setValue(count, new Node("-", 0));
                    this.string2EA.setValue(count, new Node(this.data[1].charAt(pnt.y - 1) + "", 0));
                    tempNode.highlightUp();
                    if (prevTrace == 1) {
                        tempNode.highlightA();
                    }
                    if (prevTrace == 2) {
                        tempNode.highlightB();
                    }
                    if (prevTrace == 3) {
                        tempNode.highlightC();
                    }
                    if (prevTrace != trace) {
                        selectedMatrix.get(pnt.x, pnt.y - 1).setIsDisabled(true);
                        tempNode.unHighlightUp();
                        tempNode.setArrowColor(Color.black);
                    }
                    ++count;
                }
            }
            if (Alignment.isFirstAlignment) {
                Alignment.isFirstAlignment = false;
                this.setPosition("3.4");
                this.setPosition("3.5");
            }
            else {
                this.setPosition("3.5.1");
                this.setPosition("3.5");
            }
        }
        if (aNode.getTraceA()) {
            aNode.highlightA();
            this.gapFindPathNonInteractive(newX, newY, 1, path);
        }
        if (aNode.getTraceB()) {
            aNode.highlightB();
            this.gapFindPathNonInteractive(newX, newY, 2, path);
        }
        if (aNode.getTraceC()) {
            aNode.highlightC();
            this.gapFindPathNonInteractive(newX, newY, 3, path);
        }
        path.pop();
        path.pop();
    }
    
    protected Vector<Node> generateQuestions() {
        return null;
    }
    
    public MultipleTween generateTweens(final Paintable paintable, final Object parameter, final int numberOfSteps) {
        return null;
    }
    
    public AlignmentMatrix3D get3D() {
        return this.the3D;
    }
    
    public AlignmentMatrix getAMatrix() {
        if (Alignment.currentVariation == 2) {
            return this.aMatrix;
        }
        return null;
    }
    
    public AlignmentMatrix getBMatrix() {
        if (Alignment.currentVariation == 2) {
            return this.bMatrix;
        }
        return null;
    }
    
    public String getClassName() {
        return Messages.getString("Alignment.244");
    }
    
    public AlignmentMatrix getCMatrix() {
        if (Alignment.currentVariation == 2) {
            return this.cMatrix;
        }
        return null;
    }
    
    public int getInitialGapCost() {
        return this.initialGapCost;
    }
    
    public AlignmentKey getKey() {
        return this.theAlignmentKey;
    }
    
    public int getMatchCost() {
        return this.matchCost;
    }
    
    public AlignmentMatrix getMatrix() {
        if (Alignment.currentVariation == 1) {
            return this.theMatrix;
        }
        return null;
    }
    
    public AlignmentMinimum getMinimum() {
        return this.theMinimum;
    }
    
    public int getMismatchCost() {
        return this.mismatchCost;
    }
    
    public AlignmentNode getNode() {
        return this.tempNode;
    }
    
    public int getRunningGapCost() {
        return this.runningGapCost;
    }
    
    public int getSpaceCost() {
        return this.spaceCost;
    }
    
    public ElementArray getString1EA() {
        return this.string2EA;
    }
    
    public ElementArray getString2EA() {
        return this.string1EA;
    }
    
    protected void globalDistGapAlignment() {
        final int posInf = 5000;
        this.aMatrix.add(0, 0, 0);
        this.bMatrix.add(0, 0, 0);
        this.cMatrix.add(0, 0, 0);
        this.setPosition("1.1");
        AlignmentNode tempNode;
        for (int i = 1; i < this.xSize; ++i) {
            this.bMatrix.add(posInf, i, 0);
            this.cMatrix.add(this.initialGapCost + this.runningGapCost * i, i, 0);
            tempNode = this.cMatrix.get(i, 0);
            tempNode.setTraceLeft(true);
            tempNode.setTraceC(true);
            this.aMatrix.add(posInf, i, 0);
        }
        this.setPosition("1.2");
        for (int i = 1; i < this.ySize; ++i) {
            this.bMatrix.add(this.initialGapCost + this.runningGapCost * i, 0, i);
            tempNode = this.bMatrix.get(0, i);
            tempNode.setTraceUp(true);
            tempNode.setTraceB(true);
            this.cMatrix.add(posInf, 0, i);
            this.aMatrix.add(posInf, 0, i);
        }
        this.setPosition("1.3");
        for (int y = 1; y < this.ySize; ++y) {
            this.aMatrix.setYIndexHighlight(y);
            this.bMatrix.setYIndexHighlight(y);
            this.cMatrix.setYIndexHighlight(y);
            this.setPosition("2.1");
            int x = 1;
            while (x < this.xSize) {
                this.aMatrix.setXIndexHighlight(x);
                this.bMatrix.setXIndexHighlight(x);
                this.cMatrix.setXIndexHighlight(x);
                this.setPosition("2.1.1");
                this.theMinimum = new AlignmentMinimum(Messages.getString("Alignment.227"));
                this.theMinimum.add();
                this.theMinimum.add();
                this.theMinimum.add();
                tempNode = this.aMatrix.get(x, y - 1);
                tempNode.highlight();
                this.theMinimum.set(0, this.aMatrix.valueAt(x, y - 1), this.initialGapCost + this.runningGapCost);
                this.setPosition("2.1.1.1.2");
                tempNode.unHighlight();
                tempNode = this.bMatrix.get(x, y - 1);
                tempNode.highlight();
                this.theMinimum.set(1, this.bMatrix.valueAt(x, y - 1), this.runningGapCost);
                this.setPosition("2.1.1.1.4");
                tempNode.unHighlight();
                tempNode = this.cMatrix.get(x, y - 1);
                tempNode.highlight();
                this.theMinimum.set(2, this.cMatrix.valueAt(x, y - 1), this.initialGapCost + this.runningGapCost);
                this.setPosition("2.1.1.1.6");
                tempNode.unHighlight();
                int a = this.aMatrix.valueAt(x, y - 1) + this.initialGapCost + this.runningGapCost;
                int b = this.bMatrix.valueAt(x, y - 1) + this.runningGapCost;
                int c = this.cMatrix.valueAt(x, y - 1) + this.initialGapCost + this.runningGapCost;
                int min = Math.min(a, Math.min(b, c));
                this.bMatrix.add(min, x, y);
                tempNode = this.bMatrix.get(x, y);
                tempNode.setTraceUp(true);
                if (min == a) {
                    tempNode.highlightA();
                    this.aMatrix.get(x, y - 1).highlight();
                    this.theMinimum.highlight(0);
                    tempNode.setTraceA(true);
                }
                else {
                    this.aMatrix.get(x, y - 1).setIsDisabled(true);
                    tempNode.setTraceA(false);
                }
                if (min == b) {
                    tempNode.highlightB();
                    this.bMatrix.get(x, y - 1).highlight();
                    this.theMinimum.highlight(1);
                    tempNode.setTraceB(true);
                }
                else {
                    this.bMatrix.get(x, y - 1).setIsDisabled(true);
                    tempNode.setTraceB(false);
                }
                if (min == c) {
                    tempNode.highlightC();
                    this.cMatrix.get(x, y - 1).highlight();
                    this.theMinimum.highlight(2);
                    tempNode.setTraceC(true);
                }
                else {
                    this.cMatrix.get(x, y - 1).setIsDisabled(true);
                    tempNode.setTraceC(false);
                }
                tempNode.highlight();
                tempNode.setRingColor(Alignment.HIGHLIGHT_RING_COLOR);
                this.setPosition("2.1.1.1");
                tempNode.unHighlightA();
                tempNode.unHighlightB();
                tempNode.unHighlightC();
                tempNode.setRingColor(Alignment.DEFAULT_RING_COLOR);
                tempNode.unHighlight();
                this.aMatrix.get(x, y - 1).setIsDisabled(false);
                this.aMatrix.get(x, y - 1).unHighlight();
                this.bMatrix.get(x, y - 1).setIsDisabled(false);
                this.bMatrix.get(x, y - 1).unHighlight();
                this.cMatrix.get(x, y - 1).setIsDisabled(false);
                this.cMatrix.get(x, y - 1).unHighlight();
                this.theMinimum = null;
                this.theMinimum = new AlignmentMinimum(Messages.getString("Alignment.232"));
                this.theMinimum.add();
                this.theMinimum.add();
                this.theMinimum.add();
                tempNode = this.aMatrix.get(x - 1, y);
                tempNode.highlight();
                this.theMinimum.set(0, this.aMatrix.valueAt(x - 1, y), this.initialGapCost + this.runningGapCost);
                this.setPosition("2.1.1.2.2");
                tempNode.unHighlight();
                tempNode = this.bMatrix.get(x - 1, y);
                tempNode.highlight();
                this.theMinimum.set(1, this.bMatrix.valueAt(x - 1, y), this.initialGapCost + this.runningGapCost);
                this.setPosition("2.1.1.2.4");
                tempNode.unHighlight();
                tempNode = this.cMatrix.get(x - 1, y);
                tempNode.highlight();
                this.theMinimum.set(2, this.cMatrix.valueAt(x - 1, y), this.runningGapCost);
                this.setPosition("2.1.1.2.6");
                tempNode.unHighlight();
                a = this.aMatrix.valueAt(x - 1, y) + this.initialGapCost + this.runningGapCost;
                b = this.bMatrix.valueAt(x - 1, y) + this.initialGapCost + this.runningGapCost;
                c = this.cMatrix.valueAt(x - 1, y) + this.runningGapCost;
                min = Math.min(a, Math.min(b, c));
                this.cMatrix.add(min, x, y);
                tempNode = this.cMatrix.get(x, y);
                tempNode.setTraceLeft(true);
                if (min == a) {
                    tempNode.highlightA();
                    this.aMatrix.get(x - 1, y).highlight();
                    this.theMinimum.highlight(0);
                    tempNode.setTraceA(true);
                }
                else {
                    this.aMatrix.get(x - 1, y).setIsDisabled(true);
                    tempNode.setTraceA(false);
                }
                if (min == b) {
                    tempNode.highlightB();
                    this.bMatrix.get(x - 1, y).highlight();
                    this.theMinimum.highlight(1);
                    tempNode.setTraceB(true);
                }
                else {
                    this.bMatrix.get(x - 1, y).setIsDisabled(true);
                    tempNode.setTraceB(false);
                }
                if (min == c) {
                    tempNode.highlightC();
                    this.cMatrix.get(x - 1, y).highlight();
                    this.theMinimum.highlight(2);
                    tempNode.setTraceC(true);
                }
                else {
                    this.cMatrix.get(x - 1, y).setIsDisabled(true);
                    tempNode.setTraceC(false);
                }
                tempNode.highlight();
                tempNode.setRingColor(Alignment.HIGHLIGHT_RING_COLOR);
                this.setPosition("2.1.1.2");
                tempNode.unHighlightA();
                tempNode.unHighlightB();
                tempNode.unHighlightC();
                tempNode.setRingColor(Alignment.DEFAULT_RING_COLOR);
                tempNode.unHighlight();
                this.aMatrix.get(x - 1, y).setIsDisabled(false);
                this.aMatrix.get(x - 1, y).unHighlight();
                this.bMatrix.get(x - 1, y).setIsDisabled(false);
                this.bMatrix.get(x - 1, y).unHighlight();
                this.cMatrix.get(x - 1, y).setIsDisabled(false);
                this.cMatrix.get(x - 1, y).unHighlight();
                this.theMinimum = null;
                int t;
                if (this.data[0].charAt(x - 1) == this.data[1].charAt(y - 1)) {
                    t = this.matchCost;
                }
                else {
                    t = this.mismatchCost;
                }
                this.theMinimum = new AlignmentMinimum(Messages.getString("Alignment.237"));
                this.theMinimum.add();
                this.theMinimum.add();
                this.theMinimum.add();
                tempNode = this.aMatrix.get(x - 1, y - 1);
                tempNode.highlight();
                this.theMinimum.set(0, this.aMatrix.valueAt(x - 1, y - 1), t);
                this.setPosition("2.1.1.3.1");
                tempNode.unHighlight();
                tempNode = this.bMatrix.get(x - 1, y - 1);
                tempNode.highlight();
                this.theMinimum.set(1, this.bMatrix.valueAt(x - 1, y - 1), t);
                this.setPosition("2.1.1.3.2");
                tempNode.unHighlight();
                tempNode = this.cMatrix.get(x - 1, y - 1);
                tempNode.highlight();
                this.theMinimum.set(2, this.cMatrix.valueAt(x - 1, y - 1), t);
                this.setPosition("2.1.1.3.3");
                tempNode.unHighlight();
                a = this.aMatrix.valueAt(x - 1, y - 1);
                b = this.bMatrix.valueAt(x - 1, y - 1);
                c = this.cMatrix.valueAt(x - 1, y - 1);
                min = Math.min(a, Math.min(b, c));
                this.aMatrix.add(min + t, x, y);
                tempNode = this.aMatrix.get(x, y);
                tempNode.setTraceDiag(true);
                if (min == a) {
                    tempNode.highlightA();
                    this.aMatrix.get(x - 1, y - 1).highlight();
                    this.theMinimum.highlight(0);
                    tempNode.setTraceA(true);
                }
                else {
                    this.aMatrix.get(x - 1, y - 1).setIsDisabled(true);
                    tempNode.setTraceA(false);
                }
                if (min == b) {
                    tempNode.highlightB();
                    this.bMatrix.get(x - 1, y - 1).highlight();
                    this.theMinimum.highlight(1);
                    tempNode.setTraceB(true);
                }
                else {
                    this.bMatrix.get(x - 1, y - 1).setIsDisabled(true);
                    tempNode.setTraceB(false);
                }
                if (min == c) {
                    tempNode.highlightC();
                    this.cMatrix.get(x - 1, y - 1).highlight();
                    this.theMinimum.highlight(2);
                    tempNode.setTraceC(true);
                }
                else {
                    this.cMatrix.get(x - 1, y - 1).setIsDisabled(true);
                    tempNode.setTraceC(false);
                }
                tempNode.highlight();
                tempNode.setRingColor(Alignment.HIGHLIGHT_RING_COLOR);
                this.setPosition("2.1.1.3");
                tempNode.unHighlightA();
                tempNode.unHighlightB();
                tempNode.unHighlightC();
                tempNode.setRingColor(Alignment.DEFAULT_RING_COLOR);
                tempNode.unHighlight();
                this.aMatrix.get(x - 1, y - 1).setIsDisabled(false);
                this.aMatrix.get(x - 1, y - 1).unHighlight();
                this.bMatrix.get(x - 1, y - 1).setIsDisabled(false);
                this.bMatrix.get(x - 1, y - 1).unHighlight();
                this.cMatrix.get(x - 1, y - 1).setIsDisabled(false);
                this.cMatrix.get(x - 1, y - 1).unHighlight();
                this.theMinimum = null;
                ++x;
            }
            this.setPosition("2.1.2");
        }
        this.setPosition("2.2");
    }
    
    protected void globalDistNoGapAlignment() {
        AlignmentNode tempNode;
        for (int i = 0; i < this.xSize; ++i) {
            this.theMatrix.setXIndexHighlight(i);
            this.theMatrix.setYIndexHighlight(0);
            this.setPosition("1.1.1");
            this.theMatrix.add(i * this.spaceCost, i, 0);
            if (i != 0) {
                tempNode = this.theMatrix.get(i, 0);
                tempNode.setTraceLeft(true);
            }
            this.setPosition("1.1.2");
        }
        this.theMatrix.setXIndexHighlight(-1);
        this.theMatrix.setYIndexHighlight(-1);
        this.setPosition("1.1.1");
        this.setPosition("1.1.4");
        for (int i = 0; i < this.ySize; ++i) {
            this.theMatrix.setXIndexHighlight(0);
            this.theMatrix.setYIndexHighlight(i);
            this.setPosition("1.1.5");
            this.theMatrix.add(i * this.spaceCost, 0, i);
            if (i != 0) {
                tempNode = this.theMatrix.get(0, i);
                tempNode.setTraceUp(true);
            }
            this.setPosition("1.1.6");
        }
        this.theMatrix.setXIndexHighlight(-1);
        this.theMatrix.setYIndexHighlight(-1);
        this.setPosition("1.1.5");
        this.setPosition("1.1.8");
        for (int y = 1; y < this.ySize; ++y) {
            this.theMatrix.setYIndexHighlight(y);
            this.setPosition("2.1");
            int x = 1;
            while (x < this.xSize) {
                this.theMatrix.setXIndexHighlight(x);
                this.setPosition("2.1.1");
                this.theMinimum = new AlignmentMinimum(Messages.getString("Alignment.214"));
                this.theMinimum.add();
                this.theMinimum.add();
                this.theMinimum.add();
                int t;
                if (this.data[0].charAt(x - 1) == this.data[1].charAt(y - 1)) {
                    t = this.matchCost;
                }
                else {
                    t = this.mismatchCost;
                }
                tempNode = this.theMatrix.get(x - 1, y);
                tempNode.highlight();
                this.theMinimum.set(0, this.theMatrix.valueAt(x - 1, y), this.spaceCost);
                this.setPosition("2.1.1.3");
                tempNode = this.theMatrix.get(x, y - 1);
                tempNode.highlight();
                this.theMinimum.set(1, this.theMatrix.valueAt(x, y - 1), this.spaceCost);
                this.setPosition("2.1.1.4");
                tempNode = this.theMatrix.get(x - 1, y - 1);
                tempNode.highlight();
                this.theMinimum.set(2, this.theMatrix.valueAt(x - 1, y - 1), t);
                this.setPosition("2.1.1.5");
                int min = this.theMatrix.valueAt(x - 1, y - 1) + t;
                if (this.theMatrix.valueAt(x - 1, y) + this.spaceCost < min) {
                    min = this.theMatrix.valueAt(x - 1, y) + this.spaceCost;
                }
                if (this.theMatrix.valueAt(x, y - 1) + this.spaceCost < min) {
                    min = this.theMatrix.valueAt(x, y - 1) + this.spaceCost;
                }
                this.theMatrix.add(min, x, y);
                tempNode = this.theMatrix.get(x, y);
                this.theMatrix.unHighlight();
                if (min == this.theMatrix.valueAt(x - 1, y - 1) + t) {
                    tempNode.setTraceDiag(true);
                    this.theMatrix.get(x - 1, y - 1).highlight();
                    this.theMinimum.highlight(2);
                }
                if (min == this.theMatrix.valueAt(x - 1, y) + this.spaceCost) {
                    tempNode.setTraceLeft(true);
                    this.theMatrix.get(x - 1, y).highlight();
                    this.theMinimum.highlight(0);
                }
                if (min == this.theMatrix.valueAt(x, y - 1) + this.spaceCost) {
                    tempNode.setTraceUp(true);
                    this.theMatrix.get(x, y - 1).highlight();
                    this.theMinimum.highlight(1);
                }
                this.setPosition("2.1.1.2");
                this.theMatrix.unHighlight();
                this.theMinimum.unHighlight();
                this.theMinimum = null;
                ++x;
            }
            this.setPosition("2.1.1");
        }
        this.setPosition("2.1");
        this.theMatrix.setXIndexHighlight(-1);
        this.theMatrix.setYIndexHighlight(-1);
        this.setPosition("2.2");
    }
    
    protected void globalSimGapAlignment() {
        this.theMatrix.add(0, 0, 0);
        System.out.println(Messages.getString("Alignment.0"));
        AlignmentNode tempNode;
        for (int i = 1; i < this.xSize; ++i) {
            this.E[i][0] = this.initialGapCost + this.runningGapCost * i;
            this.theMatrix.add(this.E[i][0], i, 0);
            tempNode = this.theMatrix.get(i, 0);
            tempNode.setTraceLeft(true);
        }
        this.printE();
        System.out.println(Messages.getString("Alignment.278"));
        for (int i = 1; i < this.ySize; ++i) {
            this.F[0][i] = this.initialGapCost + this.runningGapCost * i;
            this.theMatrix.add(this.F[0][i], 0, i);
            tempNode = this.theMatrix.get(0, i);
            tempNode.setTraceUp(true);
        }
        this.printF();
        this.setPosition("1.1");
        for (int y = 1; y < this.ySize; ++y) {
            this.setPosition("2.1");
            int x = 1;
            while (x < this.xSize) {
                System.out.println("---------------");
                System.out.println("" + x + ", " + y);
                int e = this.E[x - 1][y];
                System.out.println("E(i,j-1) = " + e);
                System.out.println("V(i,j-1)-Wg = " + (this.theMatrix.valueAt(x, y - 1) + this.initialGapCost));
                if (this.theMatrix.valueAt(x - 1, y) + this.initialGapCost > e || !this.theMatrix.get(x - 1, y).getTraceLeft()) {
                    e = this.theMatrix.valueAt(x - 1, y) + this.initialGapCost;
                }
                e = e + this.runningGapCost;
                this.E[x][y] = e;
                System.out.println("E(i,j) = " + e);
                this.printE();
                int f = this.F[x][y - 1];
                System.out.println("F(i-1,j) = " + f);
                System.out.println("V(i-1,j)-Wg = " + (this.theMatrix.valueAt(x - 1, y) + this.initialGapCost));
                if (this.theMatrix.valueAt(x, y - 1) + this.initialGapCost > f || !this.theMatrix.get(x, y - 1).getTraceUp()) {
                    f = this.theMatrix.valueAt(x, y - 1) + this.initialGapCost;
                }
                f = f + this.runningGapCost;
                this.F[x][y] = f;
                System.out.println("F(i,j) = " + f);
                this.printF();
                System.out.println("char 1 = " + this.data[0].charAt(x - 1) + " char 2 = " + this.data[1].charAt(y - 1));
                int t;
                if (this.data[0].charAt(x - 1) == this.data[1].charAt(y - 1)) {
                    t = this.matchCost;
                }
                else {
                    t = this.mismatchCost;
                }
                final int g = this.theMatrix.valueAt(x - 1, y - 1) + t;
                System.out.println("G(i,j) = " + g);
                int max = g;
                if (f > max) {
                    max = f;
                }
                if (e > max) {
                    max = e;
                }
                System.out.println("V(i,j) = " + max);
                this.theMatrix.add(max, x, y);
                this.printV();
                tempNode = this.theMatrix.get(x, y);
                if (max == g) {
                    tempNode.setTraceDiag(true);
                }
                if (max == f) {
                    tempNode.setTraceLeft(true);
                }
                if (max == e) {
                    tempNode.setTraceUp(true);
                }
                this.setPosition("2.1.1.1");
                ++x;
            }
        }
        this.setPosition("2.2");
        this.printE();
        this.printF();
        this.printV();
    }
    
    protected void globalSimGapAlignment2() {
        final int negInf = -99;
        this.theMatrix.add(0, 0, 0);
        this.E[0][0] = negInf;
        this.F[0][0] = negInf;
        for (int i = 1; i < this.xSize; ++i) {
            this.E[i][0] = negInf;
            this.F[i][0] = this.initialGapCost + this.runningGapCost * i;
            this.theMatrix.add(negInf, i, 0);
        }
        for (int i = 1; i < this.ySize; ++i) {
            this.E[0][i] = this.initialGapCost + this.runningGapCost * i;
            this.F[0][i] = negInf;
            this.theMatrix.add(negInf, 0, i);
        }
        this.printE();
        System.out.println("C() = ");
        this.printF();
        this.setPosition("1.1");
        for (int y = 1; y < this.ySize; ++y) {
            this.setPosition("2.1");
            int x = 1;
            while (x < this.xSize) {
                System.out.println("---------------");
                System.out.println("" + x + ", " + y);
                int a = this.theMatrix.valueAt(x, y - 1);
                a = a + this.initialGapCost + this.runningGapCost;
                int b = this.E[x][y - 1];
                b = b + this.runningGapCost;
                int c = this.F[x][y - 1];
                c = c + this.initialGapCost + this.runningGapCost;
                int max = a;
                if (b > max) {
                    max = b;
                }
                if (c > max) {
                    max = c;
                }
                this.E[x][y] = max;
                if (max == a) {
                    this.EA[x][y] = 1;
                    System.out.println(Messages.getString("Alignment.146"));
                }
                else {
                    this.EA[x][y] = 0;
                }
                if (max == b) {
                    System.out.println(Messages.getString("Alignment.147"));
                    this.EB[x][y] = 1;
                }
                else {
                    this.EB[x][y] = 0;
                }
                if (max == c) {
                    System.out.println(Messages.getString("Alignment.148"));
                    this.EC[x][y] = 1;
                }
                else {
                    this.EC[x][y] = 0;
                }
                System.out.println("B(i,j) = " + max);
                this.printE();
                a = this.theMatrix.valueAt(x - 1, y);
                a = a + this.initialGapCost + this.runningGapCost;
                b = this.E[x - 1][y];
                b = b + this.initialGapCost + this.runningGapCost;
                c = this.F[x - 1][y];
                c = c + this.runningGapCost;
                max = a;
                if (b > max) {
                    max = b;
                }
                if (c > max) {
                    max = c;
                }
                this.F[x][y] = max;
                if (max == a) {
                    System.out.println(Messages.getString("Alignment.150"));
                    this.FA[x][y] = 1;
                }
                else {
                    this.FA[x][y] = 0;
                }
                if (max == b) {
                    System.out.println(Messages.getString("Alignment.151"));
                    this.FB[x][y] = 1;
                }
                else {
                    this.FB[x][y] = 0;
                }
                if (max == c) {
                    System.out.println(Messages.getString("Alignment.279"));
                    this.FC[x][y] = 1;
                }
                else {
                    this.FC[x][y] = 0;
                }
                System.out.println("C(i,j) = " + max);
                this.printF();
                System.out.println("char 1 = " + this.data[0].charAt(x - 1) + " char 2 = " + this.data[1].charAt(y - 1));
                int t;
                if (this.data[0].charAt(x - 1) == this.data[1].charAt(y - 1)) {
                    t = this.matchCost;
                }
                else {
                    t = this.mismatchCost;
                }
                a = this.theMatrix.valueAt(x - 1, y - 1);
                b = this.E[x - 1][y - 1];
                c = this.F[x - 1][y - 1];
                max = a;
                if (b > max) {
                    max = b;
                }
                if (c > max) {
                    max = c;
                }
                if (max == a) {
                    System.out.println(Messages.getString("Alignment.156"));
                    this.GA[x][y] = 1;
                }
                else {
                    this.GA[x][y] = 0;
                }
                if (max == b) {
                    System.out.println(Messages.getString("Alignment.157"));
                    this.GB[x][y] = 1;
                }
                else {
                    this.GB[x][y] = 0;
                }
                if (max == c) {
                    System.out.println(Messages.getString("Alignment.158"));
                    this.GC[x][y] = 1;
                }
                else {
                    this.GC[x][y] = 0;
                }
                max = max + t;
                System.out.println("V(i,j) = " + max);
                this.theMatrix.add(max, x, y);
                this.printV();
                final AlignmentNode tempNode = this.theMatrix.get(x, y);
                if (max - t == a) {
                    tempNode.setTraceDiag(true);
                }
                if (max - t == c) {
                    tempNode.setTraceLeft(true);
                }
                if (max - t == b) {
                    tempNode.setTraceUp(true);
                }
                this.setPosition("2.1.1.1");
                ++x;
            }
        }
        this.setPosition("2.2");
        this.printE();
        this.printF();
        this.printV();
    }
    
    protected void globalSimGapAlignment3() {
        this.aMatrix.setAllColor(Alignment.DEFAULT_NODE_COLOR, AlignmentNode.DEFAULT_ARROW_COLOR);
        this.bMatrix.setAllColor(Alignment.DEFAULT_NODE_COLOR, AlignmentNode.DEFAULT_ARROW_COLOR);
        this.cMatrix.setAllColor(Alignment.DEFAULT_NODE_COLOR, AlignmentNode.DEFAULT_ARROW_COLOR);
        final int negInf = -5000;
        this.aMatrix.add(0, 0, 0);
        this.bMatrix.add(0, 0, 0);
        this.cMatrix.add(0, 0, 0);
        this.setPosition("1.1");
        AlignmentNode tempNode;
        for (int i = 1; i < this.xSize; ++i) {
            this.bMatrix.add(negInf, i, 0);
            this.cMatrix.add(this.initialGapCost + this.runningGapCost * i, i, 0);
            tempNode = this.cMatrix.get(i, 0);
            tempNode.setTraceLeft(true);
            tempNode.setTraceC(true);
            this.aMatrix.add(negInf, i, 0);
        }
        this.setPosition("1.2");
        for (int i = 1; i < this.ySize; ++i) {
            this.bMatrix.add(this.initialGapCost + this.runningGapCost * i, 0, i);
            tempNode = this.bMatrix.get(0, i);
            tempNode.setTraceUp(true);
            tempNode.setTraceB(true);
            this.cMatrix.add(negInf, 0, i);
            this.aMatrix.add(negInf, 0, i);
        }
        this.setPosition("1.3");
        for (int y = 1; y < this.ySize; ++y) {
            this.aMatrix.setYIndexHighlight(y);
            this.bMatrix.setYIndexHighlight(y);
            this.cMatrix.setYIndexHighlight(y);
            this.setPosition("2.1");
            int x = 1;
            while (x < this.xSize) {
                this.aMatrix.setXIndexHighlight(x);
                this.bMatrix.setXIndexHighlight(x);
                this.cMatrix.setXIndexHighlight(x);
                this.setPosition("2.1.1");
                this.theMinimum = new AlignmentMinimum(Messages.getString("Alignment.281"));
                this.theMinimum.add();
                this.theMinimum.add();
                this.theMinimum.add();
                tempNode = this.aMatrix.get(x, y - 1);
                tempNode.highlight();
                this.theMinimum.set(0, this.aMatrix.valueAt(x, y - 1), this.initialGapCost + this.runningGapCost);
                this.setPosition("2.1.1.1.2");
                tempNode.unHighlight();
                tempNode = this.bMatrix.get(x, y - 1);
                tempNode.highlight();
                this.theMinimum.set(1, this.bMatrix.valueAt(x, y - 1), this.runningGapCost);
                this.setPosition("2.1.1.1.4");
                tempNode.unHighlight();
                tempNode = this.cMatrix.get(x, y - 1);
                tempNode.highlight();
                this.theMinimum.set(2, this.cMatrix.valueAt(x, y - 1), this.initialGapCost + this.runningGapCost);
                this.setPosition("2.1.1.1.6");
                tempNode.unHighlight();
                int a = this.aMatrix.valueAt(x, y - 1) + this.initialGapCost + this.runningGapCost;
                int b = this.bMatrix.valueAt(x, y - 1) + this.runningGapCost;
                int c = this.cMatrix.valueAt(x, y - 1) + this.initialGapCost + this.runningGapCost;
                int max = Math.max(a, Math.max(b, c));
                this.bMatrix.add(max, x, y);
                tempNode = this.bMatrix.get(x, y);
                tempNode.setTraceUp(true);
                if (max == a) {
                    tempNode.highlightA();
                    this.aMatrix.get(x, y - 1).highlight();
                    this.theMinimum.highlight(0);
                    tempNode.setTraceA(true);
                }
                else {
                    this.aMatrix.get(x, y - 1).setIsDisabled(true);
                    tempNode.setTraceA(false);
                }
                if (max == b) {
                    tempNode.highlightB();
                    this.bMatrix.get(x, y - 1).highlight();
                    this.theMinimum.highlight(1);
                    tempNode.setTraceB(true);
                }
                else {
                    this.bMatrix.get(x, y - 1).setIsDisabled(true);
                    tempNode.setTraceB(false);
                }
                if (max == c) {
                    tempNode.highlightC();
                    this.cMatrix.get(x, y - 1).highlight();
                    this.theMinimum.highlight(2);
                    tempNode.setTraceC(true);
                }
                else {
                    this.cMatrix.get(x, y - 1).setIsDisabled(true);
                    tempNode.setTraceC(false);
                }
                tempNode.highlight();
                tempNode.setRingColor(Alignment.HIGHLIGHT_RING_COLOR);
                this.setPosition("2.1.1.1");
                tempNode.unHighlightA();
                tempNode.unHighlightB();
                tempNode.unHighlightC();
                tempNode.setRingColor(Alignment.DEFAULT_RING_COLOR);
                tempNode.unHighlight();
                this.aMatrix.get(x, y - 1).setIsDisabled(false);
                this.aMatrix.get(x, y - 1).unHighlight();
                this.bMatrix.get(x, y - 1).setIsDisabled(false);
                this.bMatrix.get(x, y - 1).unHighlight();
                this.cMatrix.get(x, y - 1).setIsDisabled(false);
                this.cMatrix.get(x, y - 1).unHighlight();
                this.theMinimum = null;
                this.theMinimum = new AlignmentMinimum(Messages.getString("Alignment.280"));
                this.theMinimum.add();
                this.theMinimum.add();
                this.theMinimum.add();
                tempNode = this.aMatrix.get(x - 1, y);
                tempNode.highlight();
                this.theMinimum.set(0, this.aMatrix.valueAt(x - 1, y), this.initialGapCost + this.runningGapCost);
                this.setPosition("2.1.1.2.2");
                tempNode.unHighlight();
                tempNode = this.bMatrix.get(x - 1, y);
                tempNode.highlight();
                this.theMinimum.set(1, this.bMatrix.valueAt(x - 1, y), this.initialGapCost + this.runningGapCost);
                this.setPosition("2.1.1.2.4");
                tempNode.unHighlight();
                tempNode = this.cMatrix.get(x - 1, y);
                tempNode.highlight();
                this.theMinimum.set(2, this.cMatrix.valueAt(x - 1, y), this.runningGapCost);
                this.setPosition("2.1.1.2.6");
                tempNode.unHighlight();
                a = this.aMatrix.valueAt(x - 1, y) + this.initialGapCost + this.runningGapCost;
                b = this.bMatrix.valueAt(x - 1, y) + this.initialGapCost + this.runningGapCost;
                c = this.cMatrix.valueAt(x - 1, y) + this.runningGapCost;
                max = Math.max(a, Math.max(b, c));
                this.cMatrix.add(max, x, y);
                tempNode = this.cMatrix.get(x, y);
                tempNode.setTraceLeft(true);
                if (max == a) {
                    tempNode.highlightA();
                    this.aMatrix.get(x - 1, y).highlight();
                    this.theMinimum.highlight(0);
                    tempNode.setTraceA(true);
                }
                else {
                    this.aMatrix.get(x - 1, y).setIsDisabled(true);
                    tempNode.setTraceA(false);
                }
                if (max == b) {
                    tempNode.highlightB();
                    this.bMatrix.get(x - 1, y).highlight();
                    this.theMinimum.highlight(1);
                    tempNode.setTraceB(true);
                }
                else {
                    this.bMatrix.get(x - 1, y).setIsDisabled(true);
                    tempNode.setTraceB(false);
                }
                if (max == c) {
                    tempNode.highlightC();
                    this.cMatrix.get(x - 1, y).highlight();
                    this.theMinimum.highlight(2);
                    tempNode.setTraceC(true);
                }
                else {
                    this.cMatrix.get(x - 1, y).setIsDisabled(true);
                    tempNode.setTraceC(false);
                }
                tempNode.highlight();
                tempNode.setRingColor(Alignment.HIGHLIGHT_RING_COLOR);
                this.setPosition("2.1.1.2");
                tempNode.unHighlightA();
                tempNode.unHighlightB();
                tempNode.unHighlightC();
                tempNode.setRingColor(Alignment.DEFAULT_RING_COLOR);
                tempNode.unHighlight();
                this.aMatrix.get(x - 1, y).setIsDisabled(false);
                this.aMatrix.get(x - 1, y).unHighlight();
                this.bMatrix.get(x - 1, y).setIsDisabled(false);
                this.bMatrix.get(x - 1, y).unHighlight();
                this.cMatrix.get(x - 1, y).setIsDisabled(false);
                this.cMatrix.get(x - 1, y).unHighlight();
                this.theMinimum = null;
                int t;
                if (this.data[0].charAt(x - 1) == this.data[1].charAt(y - 1)) {
                    t = this.matchCost;
                }
                else {
                    t = this.mismatchCost;
                }
                this.theMinimum = new AlignmentMinimum("Maximum");
                this.theMinimum.add();
                this.theMinimum.add();
                this.theMinimum.add();
                tempNode = this.aMatrix.get(x - 1, y - 1);
                tempNode.highlight();
                this.theMinimum.set(0, this.aMatrix.valueAt(x - 1, y - 1), t);
                this.setPosition("2.1.1.3.1");
                tempNode.unHighlight();
                tempNode = this.bMatrix.get(x - 1, y - 1);
                tempNode.highlight();
                this.theMinimum.set(1, this.bMatrix.valueAt(x - 1, y - 1), t);
                this.setPosition("2.1.1.3.2");
                tempNode.unHighlight();
                tempNode = this.cMatrix.get(x - 1, y - 1);
                tempNode.highlight();
                this.theMinimum.set(2, this.cMatrix.valueAt(x - 1, y - 1), t);
                this.setPosition("2.1.1.3.3");
                tempNode.unHighlight();
                a = this.aMatrix.valueAt(x - 1, y - 1);
                b = this.bMatrix.valueAt(x - 1, y - 1);
                c = this.cMatrix.valueAt(x - 1, y - 1);
                max = Math.max(a, Math.max(b, c));
                this.aMatrix.add(max + t, x, y);
                tempNode = this.aMatrix.get(x, y);
                tempNode.setTraceDiag(true);
                if (max == a) {
                    tempNode.highlightA();
                    this.aMatrix.get(x - 1, y - 1).highlight();
                    this.theMinimum.highlight(0);
                    tempNode.setTraceA(true);
                }
                else {
                    this.aMatrix.get(x - 1, y - 1).setIsDisabled(true);
                    tempNode.setTraceA(false);
                }
                if (max == b) {
                    tempNode.highlightB();
                    this.bMatrix.get(x - 1, y - 1).highlight();
                    this.theMinimum.highlight(1);
                    tempNode.setTraceB(true);
                }
                else {
                    this.bMatrix.get(x - 1, y - 1).setIsDisabled(true);
                    tempNode.setTraceB(false);
                }
                if (max == c) {
                    tempNode.highlightC();
                    this.cMatrix.get(x - 1, y - 1).highlight();
                    this.theMinimum.highlight(2);
                    tempNode.setTraceC(true);
                }
                else {
                    this.cMatrix.get(x - 1, y - 1).setIsDisabled(true);
                    tempNode.setTraceC(false);
                }
                tempNode.highlight();
                tempNode.setRingColor(Alignment.HIGHLIGHT_RING_COLOR);
                this.setPosition("2.1.1.3");
                tempNode.unHighlightA();
                tempNode.unHighlightB();
                tempNode.unHighlightC();
                tempNode.setRingColor(Alignment.DEFAULT_RING_COLOR);
                tempNode.unHighlight();
                this.aMatrix.get(x - 1, y - 1).setIsDisabled(false);
                this.aMatrix.get(x - 1, y - 1).unHighlight();
                this.bMatrix.get(x - 1, y - 1).setIsDisabled(false);
                this.bMatrix.get(x - 1, y - 1).unHighlight();
                this.cMatrix.get(x - 1, y - 1).setIsDisabled(false);
                this.cMatrix.get(x - 1, y - 1).unHighlight();
                this.theMinimum = null;
                ++x;
            }
            this.setPosition("2.1.2");
        }
        this.setPosition("2.2");
    }
    
    protected void globalSimNoGapAlignment() {
        AlignmentNode tempNode;
        for (int i = 0; i < this.xSize; ++i) {
            this.theMatrix.setXIndexHighlight(i);
            this.theMatrix.setYIndexHighlight(0);
            this.setPosition("1.1.1");
            this.theMatrix.add(i * this.spaceCost, i, 0);
            if (i != 0) {
                tempNode = this.theMatrix.get(i, 0);
                tempNode.setTraceLeft(true);
            }
            this.setPosition("1.1.2");
        }
        this.theMatrix.setXIndexHighlight(-1);
        this.theMatrix.setYIndexHighlight(-1);
        this.setPosition("1.1.1");
        this.setPosition("1.1.4");
        for (int i = 0; i < this.ySize; ++i) {
            this.theMatrix.setXIndexHighlight(0);
            this.theMatrix.setYIndexHighlight(i);
            this.setPosition("1.1.5");
            this.theMatrix.add(i * this.spaceCost, 0, i);
            if (i != 0) {
                tempNode = this.theMatrix.get(0, i);
                tempNode.setTraceUp(true);
            }
            this.setPosition("1.1.6");
        }
        this.theMatrix.setXIndexHighlight(-1);
        this.theMatrix.setYIndexHighlight(-1);
        this.setPosition("1.1.5");
        this.setPosition("1.1.8");
        for (int y = 1; y < this.ySize; ++y) {
            this.theMatrix.setYIndexHighlight(y);
            this.setPosition("2.1");
            int x = 1;
            while (x < this.xSize) {
                this.theMatrix.setXIndexHighlight(x);
                this.setPosition("2.1.1");
                this.theMinimum = new AlignmentMinimum(Messages.getString("Alignment.110"));
                this.theMinimum.add();
                this.theMinimum.add();
                this.theMinimum.add();
                int t;
                if (this.data[0].charAt(x - 1) == this.data[1].charAt(y - 1)) {
                    t = this.matchCost;
                }
                else {
                    t = this.mismatchCost;
                }
                tempNode = this.theMatrix.get(x - 1, y);
                tempNode.highlight();
                this.theMinimum.set(0, this.theMatrix.valueAt(x - 1, y), this.spaceCost);
                this.setPosition("2.1.1.3");
                tempNode = this.theMatrix.get(x, y - 1);
                tempNode.highlight();
                this.theMinimum.set(1, this.theMatrix.valueAt(x, y - 1), this.spaceCost);
                this.setPosition("2.1.1.4");
                tempNode = this.theMatrix.get(x - 1, y - 1);
                tempNode.highlight();
                this.theMinimum.set(2, this.theMatrix.valueAt(x - 1, y - 1), t);
                this.setPosition("2.1.1.5");
                int max = this.theMatrix.valueAt(x - 1, y - 1) + t;
                if (this.theMatrix.valueAt(x - 1, y) + this.spaceCost > max) {
                    max = this.theMatrix.valueAt(x - 1, y) + this.spaceCost;
                }
                if (this.theMatrix.valueAt(x, y - 1) + this.spaceCost > max) {
                    max = this.theMatrix.valueAt(x, y - 1) + this.spaceCost;
                }
                this.theMatrix.add(max, x, y);
                tempNode = this.theMatrix.get(x, y);
                this.theMatrix.unHighlight();
                if (max == this.theMatrix.valueAt(x - 1, y - 1) + t) {
                    tempNode.setTraceDiag(true);
                    this.theMatrix.get(x - 1, y - 1).highlight();
                    this.theMinimum.highlight(2);
                }
                if (max == this.theMatrix.valueAt(x - 1, y) + this.spaceCost) {
                    tempNode.setTraceLeft(true);
                    this.theMatrix.get(x - 1, y).highlight();
                    this.theMinimum.highlight(0);
                }
                if (max == this.theMatrix.valueAt(x, y - 1) + this.spaceCost) {
                    tempNode.setTraceUp(true);
                    this.theMatrix.get(x, y - 1).highlight();
                    this.theMinimum.highlight(1);
                }
                this.setPosition("2.1.1.2");
                this.theMatrix.unHighlight();
                this.theMinimum.unHighlight();
                this.theMinimum = null;
                ++x;
            }
            this.setPosition("2.1.1");
        }
        this.setPosition("2.1");
        this.theMatrix.setXIndexHighlight(-1);
        this.theMatrix.setYIndexHighlight(-1);
        this.setPosition("2.2");
    }
    
    protected boolean hasQuestions() {
        return false;
    }
    
    protected void initialise() {
        final String temp = this.data[0];
        this.data[0] = this.data[1];
        this.data[1] = temp;
        this.theMatrix = new AlignmentMatrix(this.data[0].length() + 1, this.data[1].length() + 1, this.data[0], this.data[1]);
        this.aMatrix = new AlignmentMatrix(this.data[0].length() + 1, this.data[1].length() + 1, this.data[0], this.data[1]);
        this.bMatrix = new AlignmentMatrix(this.data[0].length() + 1, this.data[1].length() + 1, this.data[0], this.data[1]);
        this.cMatrix = new AlignmentMatrix(this.data[0].length() + 1, this.data[1].length() + 1, this.data[0], this.data[1]);
        this.aMatrix.setIsGapMode(true);
        this.aMatrix.setMatrixName("A");
        this.aMatrix.setDrawName(true);
        this.aMatrix.setInternalBoxSize(2, 2);
        this.bMatrix.setIsGapMode(true);
        this.bMatrix.setMatrixName("B");
        this.bMatrix.setDrawName(true);
        this.bMatrix.setInternalBoxSize(2, 2);
        this.cMatrix.setIsGapMode(true);
        this.cMatrix.setMatrixName("C");
        this.cMatrix.setDrawName(true);
        this.cMatrix.setInternalBoxSize(2, 2);
        this.xSize = this.theMatrix.getXSize();
        this.ySize = this.theMatrix.getYSize();
        this.E = new int[this.xSize][this.ySize];
        this.F = new int[this.xSize][this.ySize];
        this.string1EA = null;
        this.string2EA = null;
        this.theMinimum = null;
        this.theAlignmentKey = null;
        this.the3D.initialiseColor();
        this.the3D.setVisible(false);
    }
    
    protected void localSimGapAlignment() {
        this.aMatrix.add(0, 0, 0);
        this.bMatrix.add(0, 0, 0);
        this.cMatrix.add(0, 0, 0);
        for (int i = 1; i < this.xSize; ++i) {
            this.cMatrix.add(0, i, 0);
            this.aMatrix.add(0, i, 0);
            this.bMatrix.add(0, i, 0);
        }
        for (int i = 1; i < this.ySize; ++i) {
            this.bMatrix.add(0, 0, i);
            this.cMatrix.add(0, 0, i);
            this.aMatrix.add(0, 0, i);
        }
        this.setPosition("1.1");
        for (int y = 1; y < this.ySize; ++y) {
            this.setPosition("2.1");
            int x = 1;
            while (x < this.xSize) {
                int a = this.aMatrix.valueAt(x, y - 1);
                a = a + this.initialGapCost + this.runningGapCost;
                int b = this.bMatrix.valueAt(x, y - 1);
                b = b + this.runningGapCost;
                int c = this.cMatrix.valueAt(x, y - 1);
                c = c + this.initialGapCost + this.runningGapCost;
                int max = a;
                if (b > max) {
                    max = b;
                }
                if (c > max) {
                    max = c;
                }
                this.bMatrix.add(max, x, y);
                AlignmentNode tempNode = this.bMatrix.get(x, y);
                tempNode.setTraceUp(true);
                if (max == a) {
                    tempNode.setTraceA(true);
                }
                else {
                    tempNode.setTraceA(false);
                }
                if (max == b) {
                    tempNode.setTraceB(true);
                }
                else {
                    tempNode.setTraceB(false);
                }
                if (max == c) {
                    tempNode.setTraceC(true);
                }
                else {
                    tempNode.setTraceC(false);
                }
                a = this.aMatrix.valueAt(x - 1, y);
                a = a + this.initialGapCost + this.runningGapCost;
                b = this.bMatrix.valueAt(x - 1, y);
                b = b + this.initialGapCost + this.runningGapCost;
                c = this.cMatrix.valueAt(x - 1, y);
                c = c + this.runningGapCost;
                max = a;
                if (b > max) {
                    max = b;
                }
                if (c > max) {
                    max = c;
                }
                this.cMatrix.add(max, x, y);
                tempNode = this.cMatrix.get(x, y);
                tempNode.setTraceLeft(true);
                if (max == a) {
                    tempNode.setTraceA(true);
                }
                else {
                    tempNode.setTraceA(false);
                }
                if (max == b) {
                    tempNode.setTraceB(true);
                }
                else {
                    tempNode.setTraceB(false);
                }
                if (max == c) {
                    tempNode.setTraceC(true);
                }
                else {
                    tempNode.setTraceC(false);
                }
                int t;
                if (this.data[0].charAt(x - 1) == this.data[1].charAt(y - 1)) {
                    t = this.matchCost;
                }
                else {
                    t = this.mismatchCost;
                }
                a = this.aMatrix.valueAt(x - 1, y - 1);
                b = this.bMatrix.valueAt(x - 1, y - 1);
                c = this.cMatrix.valueAt(x - 1, y - 1);
                max = a;
                if (b > max) {
                    max = b;
                }
                if (c > max) {
                    max = c;
                }
                this.aMatrix.add(max + t, x, y);
                tempNode = this.aMatrix.get(x, y);
                tempNode.setTraceDiag(true);
                if (max == a) {
                    tempNode.setTraceA(true);
                }
                else {
                    tempNode.setTraceA(false);
                }
                if (max == b) {
                    tempNode.setTraceB(true);
                }
                else {
                    tempNode.setTraceB(false);
                }
                if (max == c) {
                    tempNode.setTraceC(true);
                }
                else {
                    tempNode.setTraceC(false);
                }
                this.setPosition("2.1.1.1");
                ++x;
            }
        }
        this.setPosition("2.2");
    }
    
    protected void localSimNoGapAlignment() {
        for (int i = 0; i < this.xSize; ++i) {
            this.theMatrix.setXIndexHighlight(i);
            this.theMatrix.setYIndexHighlight(0);
            this.setPosition("1.1.1");
            this.theMatrix.add(0, i, 0);
            this.setPosition("1.1.2");
        }
        this.theMatrix.setXIndexHighlight(-1);
        this.theMatrix.setYIndexHighlight(-1);
        this.setPosition("1.1.1");
        this.setPosition("1.1.4");
        for (int i = 0; i < this.ySize; ++i) {
            this.theMatrix.setXIndexHighlight(0);
            this.theMatrix.setYIndexHighlight(i);
            this.setPosition("1.1.5");
            this.theMatrix.add(0, 0, i);
            this.setPosition("1.1.6");
        }
        this.theMatrix.setXIndexHighlight(-1);
        this.theMatrix.setYIndexHighlight(-1);
        this.setPosition("1.1.5");
        this.setPosition("1.1.8");
        for (int y = 1; y < this.ySize; ++y) {
            this.theMatrix.setYIndexHighlight(y);
            this.setPosition("2.1");
            int x = 1;
            while (x < this.xSize) {
                final int d = this.spaceCost;
                this.theMatrix.setXIndexHighlight(x);
                this.setPosition("2.1.1");
                this.theMinimum = new AlignmentMinimum(Messages.getString("Alignment.191"));
                this.theMinimum.add();
                this.theMinimum.add();
                this.theMinimum.add();
                this.theMinimum.add();
                int t;
                if (this.data[0].charAt(x - 1) == this.data[1].charAt(y - 1)) {
                    t = this.matchCost;
                }
                else {
                    t = this.mismatchCost;
                }
                this.tempNode = this.theMatrix.get(x - 1, y);
                this.tempNode.highlight();
                this.theMinimum.set(0, this.theMatrix.valueAt(x - 1, y), this.spaceCost);
                this.setPosition("2.1.1.3");
                this.tempNode = this.theMatrix.get(x, y - 1);
                this.tempNode.highlight();
                this.theMinimum.set(1, this.theMatrix.valueAt(x, y - 1), this.spaceCost);
                this.setPosition("2.1.1.4");
                this.tempNode = this.theMatrix.get(x - 1, y - 1);
                this.tempNode.highlight();
                this.theMinimum.set(2, this.theMatrix.valueAt(x - 1, y - 1), t);
                this.setPosition("2.1.1.5");
                this.theMinimum.set(3, 0, 0);
                this.setPosition("2.1.1.6");
                int max = 0;
                if (this.theMatrix.valueAt(x - 1, y - 1) + t > max) {
                    max = this.theMatrix.valueAt(x - 1, y - 1) + t;
                }
                if (this.theMatrix.valueAt(x - 1, y) + d > max) {
                    max = this.theMatrix.valueAt(x - 1, y) + d;
                }
                if (this.theMatrix.valueAt(x, y - 1) + d > max) {
                    max = this.theMatrix.valueAt(x, y - 1) + d;
                }
                this.theMatrix.add(max, x, y);
                this.tempNode = this.theMatrix.get(x, y);
                this.theMatrix.unHighlight();
                if (max == this.theMatrix.valueAt(x - 1, y - 1) + t) {
                    this.theMatrix.get(x - 1, y - 1).highlight();
                    this.theMinimum.highlight(2);
                    this.tempNode.setTraceDiag(true);
                }
                if (max == this.theMatrix.valueAt(x - 1, y) + d) {
                    this.tempNode.setTraceLeft(true);
                    this.theMatrix.get(x - 1, y).highlight();
                    this.theMinimum.highlight(0);
                }
                if (max == this.theMatrix.valueAt(x, y - 1) + d) {
                    this.tempNode.setTraceUp(true);
                    this.theMatrix.get(x, y - 1).highlight();
                    this.theMinimum.highlight(1);
                }
                if (max == 0) {
                    this.theMinimum.highlight(3);
                }
                this.setPosition("2.1.1.2");
                this.theMatrix.unHighlight();
                this.theMinimum.unHighlight();
                this.theMinimum = null;
                ++x;
            }
            this.setPosition("2.1.1");
        }
        this.setPosition("2.1");
        this.theMatrix.setXIndexHighlight(-1);
        this.theMatrix.setYIndexHighlight(-1);
        this.setPosition("2.2");
    }
    
    public void moveX(final int value) {
        this.the3D.moveX(value);
        this.repaint();
    }
    
    protected void noGapFindPathInteractive(final int x, final int y, final Stack<Serializable> path) {
        path.push(new Point(x, y));
        final AlignmentNode aNode = this.theMatrix.get(x, y);
        aNode.highlight();
        aNode.setRingColor(Alignment.HIGHLIGHT_RING_COLOR);
        this.setPosition("3.3");
        if (!aNode.getTraceLeft() && !aNode.getTraceDiag() && !aNode.getTraceUp()) {
            this.setPosition("3.4");
            path.pop();
            path.pop();
            return;
        }
        if (aNode.getTraceLeft()) {
            this.setPosition("3.3.1");
            this.theMatrix.setXIndexHighlight(x - 1);
            this.theMatrix.get(x - 1, y).setRingColor(Alignment.HIGHLIGHT_RING_COLOR);
            aNode.setRingColor(Alignment.DEFAULT_RING_COLOR);
            aNode.highlightLeft();
            this.setPosition("3.3.1.2");
            path.push("L");
            this.noGapFindPathInteractive(x - 1, y, path);
        }
        else if (aNode.getTraceDiag()) {
            this.setPosition("3.3.1");
            this.setPosition("3.3.2");
            this.theMatrix.setXIndexHighlight(x - 1);
            this.setPosition("3.3.2.2");
            this.theMatrix.setYIndexHighlight(y - 1);
            this.theMatrix.get(x - 1, y - 1).setRingColor(Alignment.HIGHLIGHT_RING_COLOR);
            aNode.setRingColor(Alignment.DEFAULT_RING_COLOR);
            aNode.highlightDiag();
            this.setPosition("3.3.2.3");
            path.push("D");
            this.noGapFindPathInteractive(x - 1, y - 1, path);
        }
        else if (aNode.getTraceUp()) {
            this.setPosition("3.3.1");
            this.setPosition("3.3.2");
            this.setPosition("3.3.3");
            this.theMatrix.setYIndexHighlight(y - 1);
            this.theMatrix.get(x, y - 1).setRingColor(Alignment.HIGHLIGHT_RING_COLOR);
            aNode.setRingColor(Alignment.DEFAULT_RING_COLOR);
            aNode.highlightUp();
            this.setPosition("3.3.3.2");
            path.push("U");
            this.noGapFindPathInteractive(x, y - 1, path);
        }
        path.pop();
        path.pop();
    }
    
    protected void noGapFindPathNonInteractive(final int x, final int y, final Stack<Serializable> path) {
        path.push(new Point(x, y));
        final AlignmentNode aNode = this.theMatrix.get(x, y);
        if (!aNode.getTraceLeft() && !aNode.getTraceDiag() && !aNode.getTraceUp()) {
            this.string1EA = new ElementArray(0, 0);
            this.string2EA = new ElementArray(0, 0);
            this.string1EA.setColumGap(0);
            this.string2EA.setColumGap(0);
            this.string1EA.setElementWidth(20);
            this.string2EA.setElementWidth(20);
            String trace = "";
            int count = 0;
            this.theMatrix.unHighlight();
            this.theMatrix.unHighlightTrace();
            this.theMatrix.setAllColor(Alignment.DEFAULT_NODE_COLOR, AlignmentNode.DEFAULT_ARROW_COLOR);
            final Stack<Serializable> newPath = (Stack<E>)path.clone();
            while (!newPath.empty()) {
                final Point pnt = (Point)newPath.pop();
                final AlignmentNode tempNode = this.theMatrix.get(pnt.x, pnt.y);
                tempNode.highlight();
                if (trace == "D") {
                    this.string1EA.setValue(count, new Node(this.data[0].charAt(pnt.x - 1) + "", 0));
                    this.string2EA.setValue(count, new Node(this.data[1].charAt(pnt.y - 1) + "", 0));
                    tempNode.highlightDiag();
                    ++count;
                }
                if (trace == "L") {
                    this.string1EA.setValue(count, new Node(this.data[0].charAt(pnt.x - 1) + "", 0));
                    this.string2EA.setValue(count, new Node("-", 0));
                    tempNode.highlightLeft();
                    ++count;
                }
                if (trace == "U") {
                    this.string1EA.setValue(count, new Node("-", 0));
                    this.string2EA.setValue(count, new Node(this.data[1].charAt(pnt.y - 1) + "", 0));
                    tempNode.highlightUp();
                    ++count;
                }
                trace = (String)newPath.pop();
            }
            if (Alignment.isFirstAlignment) {
                Alignment.isFirstAlignment = false;
                this.setPosition("3.5");
                this.setPosition("3.6");
            }
            else {
                this.setPosition("3.6.1");
                this.setPosition("3.6");
            }
        }
        if (aNode.getTraceLeft()) {
            path.push("L");
            this.noGapFindPathNonInteractive(x - 1, y, path);
        }
        if (aNode.getTraceDiag()) {
            path.push("D");
            this.noGapFindPathNonInteractive(x - 1, y - 1, path);
        }
        if (aNode.getTraceUp()) {
            path.push("U");
            this.noGapFindPathNonInteractive(x, y - 1, path);
        }
        path.pop();
        path.pop();
    }
    
    protected void printD() {
        System.out.println("D() = ");
        for (int x = 0; x < this.xSize; ++x) {
            int y = 0;
            while (y < this.ySize) {
                final String str = this.D[x][y] + "";
                String space1 = new String();
                if (str.length() == 1) {
                    space1 = new String("   ");
                }
                if (str.length() == 2) {
                    space1 = new String("  ");
                }
                if (str.length() == 3) {
                    space1 = new String(" ");
                }
                System.out.print(str + space1);
                ++y;
            }
            System.out.println();
        }
    }
    
    protected void printE() {
        System.out.println("E() = ");
        for (int y = 0; y < this.ySize; ++y) {
            int x = 0;
            while (x < this.xSize) {
                String str = this.E[x][y] + "";
                if (this.EA[x][y] == 1) {
                    str = str + "A";
                }
                if (this.EB[x][y] == 1) {
                    str = str + "B";
                }
                if (this.EC[x][y] == 1) {
                    str = str + "C";
                }
                String space1 = new String();
                if (str.length() == 1) {
                    space1 = new String("      ");
                }
                if (str.length() == 2) {
                    space1 = new String("     ");
                }
                if (str.length() == 3) {
                    space1 = new String("    ");
                }
                if (str.length() == 4) {
                    space1 = new String("   ");
                }
                if (str.length() == 5) {
                    space1 = new String("  ");
                }
                if (str.length() == 6) {
                    space1 = new String(" ");
                }
                System.out.print(str + space1);
                ++x;
            }
            System.out.println();
        }
    }
    
    protected void printF() {
        System.out.println("F() = ");
        for (int y = 0; y < this.ySize; ++y) {
            int x = 0;
            while (x < this.xSize) {
                String str = this.F[x][y] + "";
                String space1 = new String();
                if (this.FA[x][y] == 1) {
                    str = str + "A";
                }
                if (this.FB[x][y] == 1) {
                    str = str + "B";
                }
                if (this.FC[x][y] == 1) {
                    str = str + "C";
                }
                if (str.length() == 1) {
                    space1 = new String("      ");
                }
                if (str.length() == 2) {
                    space1 = new String("     ");
                }
                if (str.length() == 3) {
                    space1 = new String("    ");
                }
                if (str.length() == 4) {
                    space1 = new String("   ");
                }
                if (str.length() == 5) {
                    space1 = new String("  ");
                }
                if (str.length() == 6) {
                    space1 = new String(" ");
                }
                System.out.print(str + space1);
                ++x;
            }
            System.out.println();
        }
    }
    
    protected void printV() {
        System.out.println("V() = ");
        for (int y = 0; y < this.ySize; ++y) {
            int x = 0;
            while (x < this.xSize) {
                String str = this.theMatrix.valueAt(x, y) + "";
                String space1 = new String();
                if (this.GA[x][y] == 1) {
                    str = str + "A";
                }
                if (this.GB[x][y] == 1) {
                    str = str + "B";
                }
                if (this.GC[x][y] == 1) {
                    str = str + "C";
                }
                if (str.length() == 1) {
                    space1 = new String(Messages.getString("Alignment.277"));
                }
                if (str.length() == 2) {
                    space1 = new String("     ");
                }
                if (str.length() == 3) {
                    space1 = new String("    ");
                }
                if (str.length() == 4) {
                    space1 = new String("   ");
                }
                if (str.length() == 5) {
                    space1 = new String("  ");
                }
                if (str.length() == 6) {
                    space1 = new String(" ");
                }
                System.out.print(str + space1);
                ++x;
            }
            System.out.println();
        }
    }
    
    public void processColorSelection(final ColorSelection colorSelection) {
        final String atribute = colorSelection.getAtributeName();
        if (atribute.equalsIgnoreCase(ColorSelection.BACKGROUND)) {
            this.background = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR)) {
            this.textColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(Alignment.HIGHLIGHT_COLOR)) {
            this.highlightColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(Alignment.NODE_COLOR)) {
            this.nodeColor = colorSelection.getColor();
            Node.setDefaultBackgroundColor(this.nodeColor);
        }
    }
    
    public void reInitialise(final Object data) {
        this.data = (String[])((String[])data);
        this.initialise();
    }
    
    protected void removeAllAnimationRequests() {
    }
    
    protected void removeAllQuestions() {
    }
    
    public void rotateX(final int value) {
        this.the3D.rotateX((double)(value - Alignment.xRotate));
        Alignment.xRotate = value;
        this.repaint();
    }
    
    public void rotateY(final int value) {
        this.the3D.rotateY((double)(value - Alignment.yRotate));
        Alignment.yRotate = value;
        this.repaint();
    }
    
    public void rotateZ(final int value) {
        this.the3D.rotateZ((double)(value - Alignment.zRotate));
        Alignment.zRotate = value;
        this.repaint();
    }
    
    protected void run() {
        this.setPosition("0");
        if (Alignment.runningMode == 1 && Alignment.currentVariation == 1) {
            this.globalDistNoGapAlignment();
            this.traceBack(this.xSize - 1, this.ySize - 1);
            this.setPosition("4.0");
        }
        else if (Alignment.runningMode == 1 && Alignment.currentVariation == 2) {
            this.globalDistGapAlignment();
            int min = this.aMatrix.valueAt(this.xSize - 1, this.ySize - 1);
            int minPtr = 1;
            if (this.bMatrix.valueAt(this.xSize - 1, this.ySize - 1) < min) {
                minPtr = 2;
                min = this.bMatrix.valueAt(this.xSize - 1, this.ySize - 1);
            }
            if (this.cMatrix.valueAt(this.xSize - 1, this.ySize - 1) < min) {
                minPtr = 3;
                min = this.cMatrix.valueAt(this.xSize - 1, this.ySize - 1);
            }
            this.traceBack(this.xSize - 1, this.ySize - 1, minPtr);
            this.setPosition("4.0");
        }
        else if (Alignment.runningMode == 2 && Alignment.currentVariation == 1) {
            this.globalSimNoGapAlignment();
            this.traceBack(this.xSize - 1, this.ySize - 1);
            this.setPosition("4.0");
        }
        else if (Alignment.runningMode == 2 && Alignment.currentVariation == 2) {
            this.globalSimGapAlignment3();
            int max = this.aMatrix.valueAt(this.xSize - 1, this.ySize - 1);
            int maxPtr = 1;
            if (this.bMatrix.valueAt(this.xSize - 1, this.ySize - 1) > max) {
                maxPtr = 2;
                max = this.bMatrix.valueAt(this.xSize - 1, this.ySize - 1);
            }
            if (this.cMatrix.valueAt(this.xSize - 1, this.ySize - 1) > max) {
                maxPtr = 3;
                max = this.cMatrix.valueAt(this.xSize - 1, this.ySize - 1);
            }
            this.traceBack(this.xSize - 1, this.ySize - 1, maxPtr);
            this.setPosition("4.0");
        }
        else if (Alignment.runningMode == 3 && Alignment.currentVariation == 1) {
            this.localSimNoGapAlignment();
            this.traceBack(this.theMatrix.getMaxX(), this.theMatrix.getMaxY());
            this.setPosition("4.0");
        }
        else if (Alignment.runningMode == 3 && Alignment.currentVariation == 2) {
            this.localSimGapAlignment();
            this.traceBack(this.theMatrix.getMaxX(), this.theMatrix.getMaxY());
        }
    }
    
    public void setInitialGapCost(final int cost) {
        this.initialGapCost = cost;
    }
    
    public void setMatchCost(final int cost) {
        this.matchCost = cost;
    }
    
    public void setMismatchCost(final int cost) {
        this.mismatchCost = cost;
    }
    
    public void setRunningGapCost(final int cost) {
        this.runningGapCost = cost;
    }
    
    public void setSpaceCost(final int cost) {
        this.spaceCost = cost;
    }
    
    public void setVariation(final int state) {
        Alignment.currentVariation = state;
    }
    
    protected void storeState(final boolean forceStore) {
        super.storeState(forceStore);
    }
    
    protected void traceBack(final int x, final int y) {
        Alignment.isFirstAlignment = true;
        Stack<Serializable> queue = new Stack();
        queue.push("");
        this.theMatrix.setXIndexHighlight(x);
        this.theMatrix.setYIndexHighlight(y);
        this.setPosition("3.1");
        this.noGapFindPathInteractive(x, y, queue);
        queue = new Stack();
        queue.push("");
        this.noGapFindPathNonInteractive(x, y, queue);
    }
    
    protected void traceBack(final int x, final int y, final int a) {
        Alignment.isFirstAlignment = true;
        Stack<Serializable> queue = new Stack();
        this.theAlignmentKey = new AlignmentKey();
        this.the3D.initialiseColor();
        this.the3D.setVisible(true);
        this.the3D.fillColor(2, 2, 0, this.highlightColor);
        this.the3D.fillColor(2, 2, 1, this.highlightColor);
        this.the3D.fillColor(2, 2, 2, this.highlightColor);
        this.aMatrix.setInternalBoxCoords(x, y);
        this.aMatrix.setDrawInternalBox(true);
        this.aMatrix.setXIndexHighlight(x);
        this.aMatrix.setYIndexHighlight(y);
        this.bMatrix.setInternalBoxCoords(x, y);
        this.bMatrix.setDrawInternalBox(true);
        this.bMatrix.setXIndexHighlight(x);
        this.bMatrix.setYIndexHighlight(y);
        this.cMatrix.setInternalBoxCoords(x, y);
        this.cMatrix.setDrawInternalBox(true);
        this.cMatrix.setXIndexHighlight(x);
        this.cMatrix.setYIndexHighlight(y);
        this.aMatrix.get(x, y).highlight();
        this.bMatrix.get(x, y).highlight();
        this.cMatrix.get(x, y).highlight();
        this.setPosition("3.1.1");
        if (a != 1) {
            this.aMatrix.unHighlight();
            this.aMatrix.unHighlightTrace();
            this.aMatrix.setAllColor(Alignment.IRRELEVANT_COLOR, Alignment.IRRELEVANT_ARROW_COLOR);
            this.the3D.fillColor(2, 2, 0, Color.white);
        }
        if (a != 2) {
            this.bMatrix.unHighlight();
            this.bMatrix.unHighlightTrace();
            this.bMatrix.setAllColor(Alignment.IRRELEVANT_COLOR, Alignment.IRRELEVANT_ARROW_COLOR);
            this.the3D.fillColor(2, 2, 1, Color.white);
        }
        if (a != 3) {
            this.cMatrix.unHighlight();
            this.cMatrix.unHighlightTrace();
            this.cMatrix.setAllColor(Alignment.IRRELEVANT_COLOR, Alignment.IRRELEVANT_ARROW_COLOR);
            this.the3D.fillColor(2, 2, 2, Color.white);
        }
        if (a == 1) {
            this.aMatrix.setIsSelected(true);
        }
        if (a == 2) {
            this.bMatrix.setIsSelected(true);
        }
        if (a == 3) {
            this.cMatrix.setIsSelected(true);
        }
        this.the3D.highlightX(a - 1);
        this.setPosition("3.2");
        this.the3D.unHighlightX(a - 1);
        this.aMatrix.unHighlight();
        this.aMatrix.unHighlightTrace();
        this.aMatrix.setAllColor(Alignment.IRRELEVANT_COLOR, Alignment.IRRELEVANT_ARROW_COLOR);
        this.bMatrix.unHighlight();
        this.bMatrix.unHighlightTrace();
        this.bMatrix.setAllColor(Alignment.IRRELEVANT_COLOR, Alignment.IRRELEVANT_ARROW_COLOR);
        this.cMatrix.unHighlight();
        this.cMatrix.unHighlightTrace();
        this.cMatrix.setAllColor(Alignment.IRRELEVANT_COLOR, Alignment.IRRELEVANT_ARROW_COLOR);
        this.aMatrix.get(x, y).setIsDisabled(true);
        this.bMatrix.get(x, y).setIsDisabled(true);
        this.cMatrix.get(x, y).setIsDisabled(true);
        this.gapFindPathInteractive(x, y, a, queue);
        this.theAlignmentKey = null;
        this.the3D.setVisible(false);
        queue = new Stack();
        this.gapFindPathNonInteractive(x, y, a, queue);
    }
    
    public void translateX(final int value) {
        this.the3D.translateX((double)value);
        this.repaint();
    }
    
    public void translateY(final int value) {
        this.the3D.translateY((double)value);
        this.repaint();
    }
    
    public void translateZ(final int value) {
        this.the3D.translateZ((double)value);
        this.repaint();
    }
    
    static {
        SteelBlue1 = new Color(99, 184, 255);
        SteelBlue2 = new Color(92, 172, 238);
        SteelBlue3 = new Color(79, 148, 205);
        SteelBlue4 = new Color(54, 100, 139);
        DEFAULT_NODE_COLOR = Alignment.SteelBlue4;
        DEFAULT_SELECTED_COLOR = Alignment.SteelBlue3;
        DEFAULT_HIGHLIGHT_COLOR = Alignment.SteelBlue2;
        DEFAULT_CURRENT_COLOR = Alignment.SteelBlue1;
        HIGHLIGHT_RING_COLOR = new Color(255, 0, 0);
        DEFAULT_RING_COLOR = new Color(0, 0, 0);
        SELECT_RING_COLOR = new Color(255, 0, 0);
        IRRELEVANT_ARROW_COLOR = Color.gray;
        IRRELEVANT_COLOR = Color.white;
        HIGHLIGHT_COLOR = Messages.getString("Alignment.1");
        NODE_COLOR = Messages.getString("Alignment.2");
        Alignment.xRotate = -65;
        Alignment.yRotate = 0;
        Alignment.zRotate = 0;
    }
}
