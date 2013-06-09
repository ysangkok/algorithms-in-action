import localization.*;
import java.awt.*;
import java.util.*;
import com.cim.AIA.*;

public class BoyerMoore extends Algorithm implements ColorSelectionListener
{
    public static final int EMPTY_MARKER = -10;
    protected static final String HIGHLIGHT_COLOR;
    protected static final String NODE_COLOR;
    protected String[] data;
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
    protected int[] nextTable;
    protected Vector<BoyerMooreMoveRequest> BoyerMooreMoveRequest;
    protected Color textColor;
    protected Color highlightColor;
    protected Color nodeColor;
    protected Color background;
    protected boolean drawSymbolArrayAndLines;
    
    public BoyerMoore(final AlgorithmThread algorithmThread, final Object data) {
        super(algorithmThread, data);
        this.BoyerMooreMoveRequest = new Vector();
        this.textColor = Color.black;
        this.highlightColor = Color.yellow;
        this.nodeColor = Color.orange;
        this.background = Color.white;
        this.drawSymbolArrayAndLines = true;
        this.data = (String[])((String[])data);
        this.initialise();
        final ConfigurationManager cm = ConfigurationManager.getConfigurationManager();
        final ColorSelection highlightSelection = new ColorSelection(this.highlightColor, BoyerMoore.HIGHLIGHT_COLOR);
        highlightSelection.addClass(this);
        cm.addColorSelection(highlightSelection);
        final ColorSelection nodeSelection = new ColorSelection(this.nodeColor, BoyerMoore.NODE_COLOR);
        nodeSelection.addClass(this);
        cm.addColorSelection(nodeSelection);
        cm.addColorSelectionListener(this);
    }
    
    protected void BoyerMooreMove(final BoyerMooreString theString, final int deltaX, final int deltaY) {
        final BoyerMooreMoveRequest temp = new BoyerMooreMoveRequest(theString, theString.getX(), theString.getY(), theString.getX() + deltaX, theString.getY() + deltaY);
        theString.setLocation(theString.getX() + deltaX, theString.getY() + deltaY);
        this.BoyerMooreMoveRequest.addElement(temp);
    }
    
    protected void calculateNextTable(final String pattern) {
        final int M = pattern.length();
        this.nextTable[0] = -1;
        for (int i = 0, j = -1; i < M; ++i, ++j, this.nextTable[i] = j) {
            while (j >= 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = this.nextTable[j];
            }
        }
    }
    
    protected Vector<Object> generateQuestions() {
        return null;
    }
    
    public MultipleTween generateTweens(final Paintable paintable, final Object parameter, final int numberOfSteps) {
        final MultipleTween tweens = new MultipleTween(paintable);
        for (int i = 0; i < this.BoyerMooreMoveRequest.size(); ++i) {
            final BoyerMooreMoveRequest temp = (BoyerMooreMoveRequest)this.BoyerMooreMoveRequest.elementAt(i);
            tweens.add(new MoveTween(null, temp.theString, temp.from, temp.to, true, numberOfSteps));
        }
        return tweens;
    }
    
    public int getBoyerMooreResult() {
        return this.BoyerMooreResult;
    }
    
    public BoyerMooreString getBoyerMooreString1() {
        return this.theBoyerMooreString1;
    }
    
    public BoyerMooreString getBoyerMooreString2() {
        return this.theBoyerMooreString2;
    }
    
    public String getClassName() {
        return "BoyerMoore";
    }
    
    public Node getMi1N() {
        return this.mi1N;
    }
    
    public BoyerMooreNextTable getNextTable() {
        return this.theNextTable;
    }
    
    public Node getPatternN() {
        return this.patternN;
    }
    
    public Node getSkipN() {
        return this.skipN;
    }
    
    public BoyerMooreSkipTable getSkipTable() {
        return this.theSkipTable;
    }
    
    public Node getSkipTableN() {
        return this.skipTableN;
    }
    
    public String getSkipTableS() {
        return this.skipTableS;
    }
    
    public BoyerMooreString getTestBoyerMooreString1() {
        return this.testBoyerMooreString1;
    }
    
    public BoyerMooreString getTestBoyerMooreString2() {
        return this.testBoyerMooreString2;
    }
    
    protected boolean hasQuestions() {
        return false;
    }
    
    protected void initialise() {
        this.theBoyerMooreString1 = new BoyerMooreString(this.data[0] + " ");
        this.theBoyerMooreString2 = new BoyerMooreString(this.data[1] + " ");
        this.theNextTable = null;
        this.theSkipTable = null;
        this.testBoyerMooreString1 = null;
        this.testBoyerMooreString1 = null;
        this.BoyerMooreResult = -1;
        this.skipTableN = null;
        this.skipTableS = null;
        this.patternN = null;
        this.skipN = null;
        this.mi1N = null;
    }
    
    public void processColorSelection(final ColorSelection colorSelection) {
        final String atribute = colorSelection.getAtributeName();
        if (atribute.equalsIgnoreCase(ColorSelection.BACKGROUND)) {
            this.background = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR)) {
            this.textColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(BoyerMoore.HIGHLIGHT_COLOR)) {
            this.highlightColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(BoyerMoore.NODE_COLOR)) {
            this.nodeColor = colorSelection.getColor();
        }
    }
    
    public void reInitialise(final Object data) {
        this.data = (String[])((String[])data);
        this.initialise();
    }
    
    protected void removeAllAnimationRequests() {
        this.BoyerMooreMoveRequest.removeAllElements();
    }
    
    protected void removeAllQuestions() {
    }
    
    protected void run() {
        final String text = this.data[1];
        final String pattern = this.data[0];
        final int size = pattern.length();
        final int alpha = 27;
        final int N = text.length();
        final int M = pattern.length();
        this.setPosition("0");
        this.theSkipTable = new BoyerMooreSkipTable(alpha);
        this.setPosition("1.1");
        for (int i = 0; i < alpha; ++i) {
            this.theSkipTable.set(i, M);
        }
        this.setPosition("1.2");
        this.theBoyerMooreString1.setLabel("j");
        this.theBoyerMooreString1.setArrowLabelIndex(0);
        this.theBoyerMooreString1.setArrowLabelTarget(0);
        this.theBoyerMooreString1.setLabelIndex(0);
        this.mi1N = new Node("" + (M - 0 - 1), 0);
        this.setPosition("1.3");
        for (int i = 0; i < M; ++i) {
            this.theSkipTable.highlight(pattern.charAt(i));
            this.theBoyerMooreString1.highlight(i);
            this.setPosition("1.4");
            this.theSkipTable.set(pattern.charAt(i), M - i - 1);
            this.theSkipTable.highlight(pattern.charAt(i));
            this.setPosition("1.4.1");
            this.theSkipTable.unHighlight(pattern.charAt(i));
            this.theBoyerMooreString1.unHighlightAll();
            this.theBoyerMooreString1.setArrowLabelTarget(i + 1);
            this.theBoyerMooreString1.setArrowLabelIndex(i + 1);
            this.mi1N = new Node("" + (M - i - 2), 0);
            this.setPosition("1.3");
        }
        this.theBoyerMooreString1.setArrowLabelIndex(-1);
        this.theBoyerMooreString1.setArrowLabelTarget(-1);
        this.theBoyerMooreString1.setShowLabel(false);
        this.mi1N = null;
        this.setPosition("1.5");
        this.theBoyerMooreString2.setLabel("i");
        this.theBoyerMooreString2.setHighLabel(false);
        i = M - 1;
        this.theBoyerMooreString2.setArrowLabelIndex(i);
        this.theBoyerMooreString2.setArrowLabelTarget(i);
        this.setPosition("3.1.1");
        this.theBoyerMooreString1.setLabel("j");
        int j = M - 1;
        this.theBoyerMooreString1.setArrowLabelIndex(j);
        this.theBoyerMooreString1.setArrowLabelTarget(j);
        this.setPosition("3.1.2");
        while (j >= 0) {
            this.setPosition("3.3");
            int i = 0;
            if (text.charAt(i) != pattern.charAt(j)) {
                this.theBoyerMooreString1.unHighlightAll();
                this.theBoyerMooreString2.unHighlightAll();
                this.theBoyerMooreString1.setHighlightColor(new Color(255, 90, 90));
                this.theBoyerMooreString2.setHighlightColor(new Color(255, 90, 90));
                this.theBoyerMooreString1.highlight(j);
                this.theBoyerMooreString2.highlight(i);
                this.setPosition("3.3.1");
                this.theSkipTable.highlight(text.charAt(i));
                final int tempA = this.theSkipTable.get(text.charAt(i));
                this.skipTableS = text.charAt(i) + "";
                this.skipTableN = new Node("" + tempA, 0);
                this.setPosition("3.3.1.3");
                this.patternN = new Node("" + (M - j), 0);
                this.setPosition("3.3.1.5");
                int skip = this.theSkipTable.get(text.charAt(i));
                if (M - j > skip) {
                    this.patternN.highlight();
                    skip = M - j;
                }
                else {
                    this.skipTableN.highlight();
                }
                this.skipN = new Node("" + skip, 0);
                this.setPosition("3.3.1.2");
                i = i + skip;
                this.theBoyerMooreString2.jumpArrowLabelIndex(skip - (M - j - 1));
                this.theBoyerMooreString2.setArrowLabelTarget(i);
                this.theBoyerMooreString1.jumpArrowLabelIndex(skip - (M - j - 1));
                this.theBoyerMooreString1.setArrowLabelTarget(j);
                this.setPosition("3.3.1.6");
                this.skipN = null;
                this.patternN = null;
                this.skipTableS = null;
                this.skipTableN = null;
                this.setPosition("3.3.1.7");
                if (i >= N) {
                    this.BoyerMooreResult = 0;
                    this.setPosition("3.3.1.8");
                    this.setPosition("4");
                    return;
                }
                final int oldJ = j;
                j = M - 1;
                this.theBoyerMooreString1.setArrowLabelTarget(j);
                this.BoyerMooreMove(this.theBoyerMooreString1, (skip - (M - oldJ - 1)) * 20, 0);
                this.setPosition("3.3.1.9");
                this.theSkipTable.unHighlightAll();
                this.theBoyerMooreString1.unHighlightAll();
                this.theBoyerMooreString2.unHighlightAll();
            }
            else {
                this.theBoyerMooreString1.setHighlightColor(Color.green);
                this.theBoyerMooreString2.setHighlightColor(Color.green);
                this.theBoyerMooreString1.highlight(j);
                this.theBoyerMooreString2.highlight(i);
                this.setPosition("3.3.1");
                this.setPosition("3.3.2");
                --i;
                this.theBoyerMooreString2.setArrowLabelTarget(i);
                this.setPosition("3.3.2.1.1");
                --j;
                this.theBoyerMooreString1.setArrowLabelTarget(j);
                this.setPosition("3.3.2.1.2");
            }
            this.setPosition("3.3.3");
        }
        this.setPosition("3.3");
        this.setPosition("3.3.3");
        this.removeAllAnimationRequests();
        this.theBoyerMooreString1.setHighlightColor(Color.green);
        this.theBoyerMooreString2.setHighlightColor(Color.green);
        this.BoyerMooreResult = 1;
        this.setPosition("3.4");
        this.setPosition("4");
    }
    
    static {
        HIGHLIGHT_COLOR = Messages.getString("BoyerMoore.0");
        NODE_COLOR = Messages.getString("BoyerMoore.1");
    }
}
