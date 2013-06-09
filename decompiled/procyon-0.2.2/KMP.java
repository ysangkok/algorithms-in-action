import localization.*;
import java.awt.*;
import java.util.*;
import com.cim.AIA.*;

public class KMP extends Algorithm implements ColorSelectionListener
{
    public static final int EMPTY_MARKER = -10;
    public static final int NOOPT = 1;
    public static final int WITHOPT = 2;
    public static int currentVariation;
    protected static final String HIGHLIGHT_COLOR;
    protected static final String NODE_COLOR;
    protected String[] data;
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
    protected int[] nextTable;
    protected int[] fakeNextTable;
    protected Vector<KMPMoveRequest> kmpMoveRequest;
    protected boolean willAnim;
    protected Point animFrom;
    protected Point animTo;
    protected Color textColor;
    protected Color highlightColor;
    protected Color nodeColor;
    protected Color background;
    protected boolean drawSymbolArrayAndLines;
    
    public KMP(final AlgorithmThread algorithmThread, final Object data) {
        super(algorithmThread, data);
        this.kmpMoveRequest = new Vector();
        this.textColor = Color.black;
        this.highlightColor = Color.yellow;
        this.nodeColor = Color.orange;
        this.background = Color.white;
        this.drawSymbolArrayAndLines = true;
        this.data = (String[])((String[])data);
        this.initialise();
        KMP.currentVariation = 1;
        final ConfigurationManager cm = ConfigurationManager.getConfigurationManager();
        final ColorSelection highlightSelection = new ColorSelection(this.highlightColor, KMP.HIGHLIGHT_COLOR);
        highlightSelection.addClass(this);
        cm.addColorSelection(highlightSelection);
        final ColorSelection nodeSelection = new ColorSelection(this.nodeColor, KMP.NODE_COLOR);
        nodeSelection.addClass(this);
        cm.addColorSelection(nodeSelection);
        cm.addColorSelectionListener(this);
    }
    
    protected void animMove(final Point from, final Point to) {
        this.willAnim = true;
        this.animFrom = from;
        this.animTo = to;
    }
    
    protected void calculateNextTable(final String pattern) {
        final int M = pattern.length();
        this.fakeNextTable[0] = -1;
        this.nextTable[0] = -1;
        for (int i = 0, j = -1; i < M; ++i, ++j, this.nextTable[i] = j, this.fakeNextTable[i] = j) {
            while (j >= 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = this.nextTable[j];
            }
        }
    }
    
    protected void calculateOptNextTable(final String pattern) {
        final int M = pattern.length();
        this.nextTable[0] = -1;
        int i = 0;
        int j = -1;
        while (i < M) {
            while (j >= 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = this.nextTable[j];
            }
            ++i;
            ++j;
            if (i < M && pattern.charAt(i) == pattern.charAt(j)) {
                this.nextTable[i] = this.nextTable[j];
            }
            else {
                this.nextTable[i] = j;
            }
        }
    }
    
    protected Vector<Object> generateQuestions() {
        return null;
    }
    
    public MultipleTween generateTweens(final Paintable paintable, final Object parameter, final int numberOfSteps) {
        final MultipleTween tweens = new MultipleTween(paintable);
        for (int i = 0; i < this.kmpMoveRequest.size(); ++i) {
            final KMPMoveRequest temp = (KMPMoveRequest)this.kmpMoveRequest.elementAt(i);
            tweens.add(new MoveTween(null, temp.theString, temp.from, temp.to, true, numberOfSteps));
        }
        if (this.willAnim) {
            tweens.add(new MoveTween(null, this.animNode, this.animFrom, this.animTo, true, numberOfSteps));
        }
        return tweens;
    }
    
    public Node getAnimNode() {
        return this.animNode;
    }
    
    public String getClassName() {
        return "KMP";
    }
    
    public Node getIPointer() {
        if (this.showIPointer) {
            this.iPointer = new Node("" + this.theI, 0);
            return this.iPointer;
        }
        return null;
    }
    
    public Node getJPointer() {
        if (this.showJPointer) {
            this.jPointer = new Node("" + this.theJ, 0);
            if (this.highlightJPointer) {
                this.jPointer.highlight();
            }
            return this.jPointer;
        }
        return null;
    }
    
    public int getKMPResult() {
        return this.kmpResult;
    }
    
    public KMPString getKMPString1() {
        return this.theKMPString1;
    }
    
    public KMPString getKMPString2() {
        return this.theKMPString2;
    }
    
    public KMPNextTable getNextTable() {
        return this.theNextTable;
    }
    
    public KMPString getTestKMPString1() {
        return this.testKMPString1;
    }
    
    public KMPString getTestKMPString2() {
        return this.testKMPString2;
    }
    
    protected boolean hasQuestions() {
        return false;
    }
    
    protected void initialise() {
        this.theKMPString1 = new KMPString(this.data[0] + " ");
        this.theKMPString2 = new KMPString(this.data[1] + " ");
        this.theNextTable = null;
        this.testKMPString1 = null;
        this.testKMPString1 = null;
        this.jPointer = null;
        this.iPointer = null;
        this.animNode = null;
        this.showJPointer = false;
        this.showIPointer = false;
        this.highlightJPointer = false;
        this.willAnim = false;
        this.kmpResult = -1;
    }
    
    protected void kmpMove(final KMPString theString, final int deltaX, final int deltaY) {
        final KMPMoveRequest temp = new KMPMoveRequest(theString, theString.getX(), theString.getY(), theString.getX() + deltaX, theString.getY() + deltaY);
        theString.setLocation(theString.getX() + deltaX, theString.getY() + deltaY);
        this.kmpMoveRequest.addElement(temp);
    }
    
    protected void kmpNoOpt() {
        final String text = this.data[1];
        final String pattern = this.data[0];
        final int size = pattern.length();
        final int N = text.length();
        this.setPosition("0");
        this.theNextTable = new KMPNextTable(size);
        this.fakeNextTable = new int[size + 1];
        this.nextTable = new int[size + 1];
        this.calculateNextTable(pattern);
        this.theNextTable.highlightIndex();
        this.theNextTable.setLabelIndex(0);
        this.theNextTable.set(0, -1);
        this.setPosition("1.2.1");
        for (int count = 1; count < size; ++count) {
            this.theNextTable.setLabelIndex(count);
            this.setPosition("1.2.2");
            this.testKMPString1 = new KMPString(pattern.substring(0, count));
            this.testKMPString2 = new KMPString(pattern.substring(0, count));
            this.theKMPString1.highlight(count - 1);
            this.setPosition("1.2.2.1");
            this.kmpMove(this.testKMPString2, 20, 0);
            this.setPosition("1.2.2.2.0");
            this.testKMPString1.setHighlightColor(new Color(255, 90, 90));
            this.testKMPString2.setHighlightColor(new Color(255, 90, 90));
            int ntemp = 1;
            while (ntemp < count - this.fakeNextTable[count]) {
                this.testKMPString2.highlight(0, this.testKMPString2.length - ntemp);
                this.testKMPString1.highlight(ntemp, this.testKMPString1.length);
                this.setPosition("1.2.2.2.1");
                this.kmpMove(this.testKMPString2, 20, 0);
                this.testKMPString1.unHighlightAll();
                this.testKMPString2.unHighlightAll();
                this.setPosition("1.2.2.2.2");
                ++ntemp;
            }
            this.testKMPString1.setHighlightColor(Color.green);
            this.testKMPString2.setHighlightColor(Color.green);
            if (this.nextTable[count] > 0) {
                this.testKMPString2.highlight(0, this.nextTable[count]);
                this.testKMPString1.highlight(this.testKMPString1.length() - this.nextTable[count], this.testKMPString1.length());
            }
            this.setPosition("1.2.2.2.1");
            this.setPosition("1.2.2.2.3");
            this.theNextTable.set(count, this.nextTable[count]);
            this.testKMPString1.unHighlightAll();
            this.testKMPString2.unHighlightAll();
            this.setPosition("1.2.2.3");
        }
        this.theNextTable.unHighlightIndex();
        this.setPosition("1.2.2");
        this.theKMPString1.unHighlightAll();
        this.testKMPString1 = null;
        this.testKMPString2 = null;
        this.removeAllAnimationRequests();
        this.setPosition("1.2.3");
    }
    
    protected void kmpSearch() {
        final String text = this.data[1];
        final String pattern = this.data[0];
        final int size = pattern.length();
        final int N = text.length();
        final int M = pattern.length();
        this.theKMPString2.setLabel("i");
        this.theKMPString2.setShowLabel(true);
        this.theKMPString2.setHighLabel(false);
        this.theKMPString2.setLabelIndex(0);
        int i = 0;
        this.setPosition("2.1.1");
        this.theKMPString1.setLabel("j");
        this.theKMPString1.setShowLabel(true);
        this.theKMPString1.setLabelIndex(0);
        int j = 0;
        this.showJPointer = true;
        this.theJ = j;
        this.setPosition("2.1.2");
        while (i < N && j < M) {
            this.setPosition("2.3");
            if (text.charAt(i) != pattern.charAt(j)) {
                this.theNextTable.highlightIndex();
                this.theNextTable.setLabelIndex(j);
                this.theKMPString1.setHighlightColor(new Color(255, 90, 90));
                this.theKMPString2.setHighlightColor(new Color(255, 90, 90));
                this.theKMPString1.highlight(j);
                this.theKMPString2.highlight(i);
                this.setPosition("2.3.1");
                this.theNextTable.highlight(j);
                final int newJ = this.nextTable[j];
                this.theJ = newJ;
                this.theKMPString1.setLabelIndex(newJ);
                this.animNode = new Node("" + newJ, 0);
                this.animNode.setPosition(this.theNextTable.getNodePosition(j));
                if (this.jPointer != null) {
                    this.animMove(this.theNextTable.getNodePosition(j), this.jPointer.getPosition());
                }
                this.setPosition("2.3.1.2.1");
                this.kmpMove(this.theKMPString1, (j - newJ) * 20, 0);
                j = newJ;
                this.theKMPString1.unHighlightAll();
                this.theKMPString2.unHighlightAll();
                this.theNextTable.setLabelIndex(j);
                this.theNextTable.unHighlightAll();
                this.setPosition("2.3.1.2.2");
                if (newJ == -1) {
                    j = 0;
                    this.theJ = j;
                    ++i;
                    this.theKMPString1.setLabelIndex(j);
                    this.theKMPString2.setLabelIndex(i);
                    this.theNextTable.setLabelIndex(j);
                }
                this.setPosition("2.3.1.3");
            }
            else {
                this.theKMPString1.setHighlightColor(Color.green);
                this.theKMPString2.setHighlightColor(Color.green);
                this.theKMPString1.highlight(j);
                this.theKMPString2.highlight(i);
                this.setPosition("2.3.1");
                this.setPosition("2.3.2");
                ++i;
                this.theKMPString2.setLabelIndex(i);
                ++j;
                this.theJ = j;
                this.theKMPString1.setLabelIndex(j);
                this.setPosition("2.3.2.1.1");
            }
            this.theNextTable.unHighlightIndex();
            this.theKMPString1.unHighlightAll();
            this.theKMPString2.unHighlightAll();
        }
        this.setPosition("2.3");
        this.theNextTable.unHighlightIndex();
        this.setPosition("2.3.3");
        this.removeAllAnimationRequests();
        this.theKMPString1.setHighlightColor(Color.green);
        this.theKMPString2.setHighlightColor(Color.green);
        if (j == M) {
            this.setPosition("2.4.1");
            this.theKMPString2.highlight(i - M, i);
            this.kmpResult = 1;
            this.setPosition("2.4.1.1");
        }
        else {
            this.setPosition("2.4.1");
            this.setPosition("2.4.2");
            this.kmpResult = 0;
            this.setPosition("2.4.2.1");
        }
        this.showJPointer = false;
        this.setPosition("3");
    }
    
    protected void kmpWithOpt() {
        final String text = this.data[1];
        final String pattern = this.data[0];
        final int size = pattern.length();
        final int N = text.length();
        final int M = pattern.length();
        this.setPosition("0");
        this.theNextTable = new KMPNextTable(size);
        this.fakeNextTable = new int[size + 1];
        this.nextTable = new int[size + 1];
        this.calculateNextTable(pattern);
        this.calculateOptNextTable(pattern);
        this.theNextTable.highlightIndex();
        this.theNextTable.setLabelIndex(0);
        this.theNextTable.set(0, -1);
        this.setPosition("1.2.1");
        this.theNextTable.setHighlightColor(Node.DEFAULT_HIGHLIGHT_COLOR);
        for (int count = 1; count < size; ++count) {
            this.theNextTable.setLabelIndex(count);
            this.theI = count;
            this.setPosition("1.2.2");
            this.testKMPString1 = new KMPString(pattern.substring(0, count));
            this.testKMPString2 = new KMPString(pattern.substring(0, count));
            this.theKMPString1.highlight(0, count);
            this.showIPointer = true;
            this.setPosition("1.2.2.1");
            this.kmpMove(this.testKMPString2, 20, 0);
            this.setPosition("1.2.2.2.0");
            this.testKMPString1.setHighlightColor(new Color(255, 90, 90));
            this.testKMPString2.setHighlightColor(new Color(255, 90, 90));
            int ntemp = 1;
            while (ntemp < count - this.fakeNextTable[count]) {
                this.testKMPString2.highlight(0, this.testKMPString2.length - ntemp);
                this.testKMPString1.highlight(ntemp, this.testKMPString1.length);
                this.setPosition("1.2.2.2.1");
                this.kmpMove(this.testKMPString2, 20, 0);
                this.testKMPString1.unHighlightAll();
                this.testKMPString2.unHighlightAll();
                this.setPosition("1.2.2.2.2");
                ++ntemp;
            }
            this.testKMPString1.setHighlightColor(Color.green);
            this.testKMPString2.setHighlightColor(Color.green);
            if (this.fakeNextTable[count] > 0) {
                this.testKMPString2.highlight(0, this.fakeNextTable[count]);
                this.testKMPString1.highlight(this.testKMPString1.length() - this.fakeNextTable[count], this.testKMPString1.length());
            }
            this.setPosition("1.2.2.2.1");
            this.setPosition("1.2.2.2.3");
            this.theJ = this.fakeNextTable[count];
            this.showJPointer = true;
            this.theI = count;
            this.setPosition("1.2.2.3");
            this.theKMPString1.unHighlightAll();
            if (pattern.charAt(this.theI) == pattern.charAt(this.theJ)) {
                this.theKMPString1.setHighlightColor(Color.green);
                this.theKMPString1.highlight(count);
                this.theKMPString1.highlight(this.theJ);
                this.setPosition("1.2.2.5");
                this.theNextTable.set(count, this.nextTable[count]);
                this.theNextTable.highlight(count);
                this.theNextTable.highlight(this.theJ);
                this.setPosition("1.2.2.6");
            }
            else {
                this.theKMPString1.setHighlightColor(new Color(255, 90, 90));
                this.theKMPString1.highlight(count);
                this.theKMPString1.highlight(this.theJ);
                this.setPosition("1.2.2.5");
                this.setPosition("1.2.2.7");
                this.theNextTable.set(count, this.nextTable[count]);
                this.theNextTable.highlight(count);
                this.highlightJPointer = true;
                this.setPosition("1.2.2.8");
            }
            this.showJPointer = false;
            this.highlightJPointer = false;
            this.theNextTable.unHighlightAll();
            this.theKMPString1.setHighlightColor(Color.green);
            this.theKMPString1.unHighlightAll();
        }
        this.theNextTable.unHighlightIndex();
        this.setPosition("1.2.2");
        this.theKMPString1.unHighlightAll();
        this.testKMPString1 = null;
        this.testKMPString2 = null;
        this.removeAllAnimationRequests();
        this.showJPointer = false;
        this.showIPointer = false;
        this.setPosition("1.2.3");
    }
    
    public void processColorSelection(final ColorSelection colorSelection) {
        final String atribute = colorSelection.getAtributeName();
        if (atribute.equalsIgnoreCase(ColorSelection.BACKGROUND)) {
            this.background = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR)) {
            this.textColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(KMP.HIGHLIGHT_COLOR)) {
            this.highlightColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(KMP.NODE_COLOR)) {
            this.nodeColor = colorSelection.getColor();
        }
    }
    
    public void reInitialise(final Object data) {
        this.data = (String[])((String[])data);
        this.initialise();
    }
    
    protected void removeAllAnimationRequests() {
        this.kmpMoveRequest.removeAllElements();
        this.willAnim = false;
        this.animNode = null;
    }
    
    protected void removeAllQuestions() {
    }
    
    protected void run() {
        if (KMP.currentVariation == 1) {
            this.kmpNoOpt();
        }
        if (KMP.currentVariation == 2) {
            this.kmpWithOpt();
        }
        this.kmpSearch();
    }
    
    public void setVariation(final int state) {
        KMP.currentVariation = state;
    }
    
    static {
        HIGHLIGHT_COLOR = Messages.getString("KMP.0");
        NODE_COLOR = Messages.getString("KMP.1");
    }
}
