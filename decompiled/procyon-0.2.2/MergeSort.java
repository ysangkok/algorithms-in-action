import localization.*;
import java.util.*;
import java.awt.*;
import com.cim.AIA.*;

public class MergeSort extends Algorithm implements ColorSelectionListener
{
    protected static final String selectionLabel;
    protected static final String highlightLabel;
    protected static final String ACTIVE;
    protected static final String PROCESSED;
    protected static final String FINISHED;
    protected static final String WAITING;
    protected static final String PARTITION;
    protected static Color selectionColor;
    protected static Color BACKGROUND;
    protected static Color TEXT_COLOR;
    public static Color PARTITION_COLOR;
    public static Color WAITING_COLOR;
    public static Color HIGHLIGHT_COLOR;
    public static Color DOING_CHILDREN_COLOR;
    public static Color ACTIVE_COLOR;
    public static Color FINISHED_COLOR;
    protected int columSpacing;
    protected int columWidth;
    protected int xTextBuffer;
    protected int yTextBuffer;
    protected int xBoxPadding;
    protected int yBoxPadding;
    public int[] some;
    protected static final boolean DEBUG_MODE = 0;
    protected static final long aSeed = 12345678L;
    protected Random random;
    protected static Vector<E> swapRequests;
    protected static Vector<E> questions;
    protected static MyElementArray elementArray;
    protected static Hashtable<Integer, Boolean> highlightedElements;
    protected int[] data;
    protected Integer[] A;
    protected Integer[] B;
    protected Integer[] C;
    private static final Color kBHighlightColor;
    private static final Color kCHighlightColor;
    private static final Color kCopyBackColor;
    protected int numberOfComparisions;
    protected int numberOfSwaps;
    protected MergeSort leftChild;
    protected MergeSort rightChild;
    protected int left;
    protected int right;
    protected boolean started;
    protected boolean active;
    protected boolean processed;
    protected static boolean needtoresetSeed;
    private static int maxHeight;
    private static int maxWidth;
    private boolean copyDone;
    private Color tint;
    private HashMap<String, Integer> markMap;
    private int markCompareMap;
    private Color markCompareColor;
    private String[] arrays;
    private AlgorithmThread algThread;
    
    private static Color tintLeft(final Color left) {
        return new Color(204, 0, 204);
    }
    
    private static Color tintRight(final Color right) {
        return Color.MAGENTA;
    }
    
    public MergeSort(final AlgorithmThread algorithmThread, final Object data) {
        super(algorithmThread, data);
        this.columSpacing = 12;
        this.columWidth = 7;
        this.xTextBuffer = 20;
        this.yTextBuffer = 2;
        this.xBoxPadding = 4;
        this.yBoxPadding = 2;
        this.some = new int[] { 1, 2, 3, 4 };
        this.random = new Random(12345678L);
        this.numberOfComparisions = 0;
        this.numberOfSwaps = 0;
        this.started = false;
        this.active = false;
        this.processed = false;
        this.copyDone = false;
        this.markCompareMap = -1;
        this.arrays = new String[] { "A", "B", "C" };
        this.data = (int[])((int[])data);
        this.algThread = algorithmThread;
        this.A = new Integer[this.data.length];
        for (int i = 0; i < this.data.length; ++i) {
            this.A[i] = new Integer(this.data[i]);
        }
        this.markMap = new HashMap();
        final ConfigurationManager cm = ConfigurationManager.getConfigurationManager();
        final ColorSelection partitionSelection = new ColorSelection(MergeSort.PARTITION_COLOR, MergeSort.PARTITION);
        partitionSelection.addClass(this);
        cm.addColorSelection(partitionSelection);
        cm.addColorSelectionListener(this);
        final ColorSelection activeSelection = new ColorSelection(MergeSort.ACTIVE_COLOR, MergeSort.ACTIVE);
        activeSelection.addClass(this);
        cm.addColorSelection(activeSelection);
        final ColorSelection processedSelection = new ColorSelection(MergeSort.DOING_CHILDREN_COLOR, MergeSort.PROCESSED);
        processedSelection.addClass(this);
        cm.addColorSelection(processedSelection);
        final ColorSelection finishedSelection = new ColorSelection(MergeSort.FINISHED_COLOR, MergeSort.FINISHED);
        finishedSelection.addClass(this);
        cm.addColorSelection(finishedSelection);
        final ColorSelection waitingSelection = new ColorSelection(MergeSort.WAITING_COLOR, MergeSort.WAITING);
        waitingSelection.addClass(this);
        cm.addColorSelection(waitingSelection);
        final ColorSelection highlightSelection = new ColorSelection(MergeSort.HIGHLIGHT_COLOR, MergeSort.highlightLabel);
        highlightSelection.addClass(this);
        cm.addColorSelection(highlightSelection);
        cm.addColorSelectionListener(this);
        final ColorSelection selectionSelection = new ColorSelection(MergeSort.selectionColor, MergeSort.selectionLabel);
        selectionSelection.addClass(this);
        cm.addColorSelection(selectionSelection);
        cm.addColorSelectionListener(this);
        this.initialise(true);
    }
    
    protected void initialise(final boolean newSearch) {
        this.debugMode = false;
        this.active = false;
        this.processed = false;
        this.leftChild = null;
        this.rightChild = null;
        this.numberOfComparisions = 0;
        this.numberOfSwaps = 0;
    }
    
    protected void removeAllAnimationRequests() {
    }
    
    void merge(final boolean showRecursion) {
        this.setPosition("7", showRecursion);
        int i = 0;
        int j = 0;
        int k = 0;
        this.setPosition("7.1", showRecursion && this.isExpanded("7"));
        final int p = this.B.length;
        final int q = this.C.length;
        this.setMark("A", k);
        this.leftChild.setMark("A", i);
        this.rightChild.setMark("A", j);
        this.setPosition("7.1.1.1", showRecursion && this.isExpanded("7"));
        final boolean showWhileLoop = showRecursion && this.isExpanded("7.1.1");
        while (i < p && j < q) {
            this.setMark("A", k);
            final boolean showCompareAndCopy = showWhileLoop && this.isExpanded("7.1.1.1.1");
            this.leftChild.setCompareMark(i, MergeSort.kBHighlightColor, showCompareAndCopy);
            this.setPosition("7.1.1.1.1", showWhileLoop);
            this.rightChild.setCompareMark(j, MergeSort.kCHighlightColor);
            this.setPosition("7.1.1.1.1.1", showWhileLoop);
            if (this.B[i] <= this.C[j]) {
                this.setCompareMark(k, MergeSort.kBHighlightColor);
                this.setMark("A", k);
                this.setPosition("7.1.1.1.1.2", showCompareAndCopy);
                this.A[k] = this.B[i];
                this.setPosition("7.1.1.1.1.2b", showCompareAndCopy);
                ++i;
                this.leftChild.setMark("A", i);
            }
            else {
                this.setCompareMark(k, MergeSort.kCHighlightColor);
                this.setMark("A", k);
                this.setPosition("7.1.1.1.1.3", showCompareAndCopy);
                this.A[k] = this.C[j];
                this.setPosition("7.1.1.1.1.3c", showCompareAndCopy);
                ++j;
                this.rightChild.setMark("A", j);
            }
            this.leftChild.clearCompareMark();
            this.clearCompareMark();
            this.rightChild.clearCompareMark();
            ++k;
            this.setMark("A", k);
            this.setPosition("7.1.1.1.2", showWhileLoop);
        }
        this.setPosition("7.1.1.2", showRecursion && this.isExpanded("7"));
        final boolean showCopyLeftOvers = showRecursion && this.isExpanded("7") && this.isExpanded("7.1.1.2");
        this.setMark("A", k);
        this.setPosition("7.1.1.2.1", showCopyLeftOvers);
        this.setMark("A", k);
        if (i == p) {
            this.setPosition("7.1.1.2.2", showCopyLeftOvers);
            final boolean showCopyRestOfC = showCopyLeftOvers && this.isExpanded("7.1.1.2.2");
            this.setPosition("7.1.1.2.2.1", showCopyRestOfC);
            for (; j < q; ++j, this.setPosition("7.1.1.2.3", showCopyRestOfC), this.setMark("A", k - 1), this.setPosition("7.1.1.2.4", showCopyRestOfC), this.setPosition("7.1.1.2.5", showCopyRestOfC), this.setPosition("7.1.1.2.2.1", showCopyRestOfC)) {
                this.setCompareMark(k, MergeSort.kCopyBackColor);
                this.rightChild.setCompareMark(j, MergeSort.kCopyBackColor);
                this.setMark("A", k);
                this.A[k] = this.C[j];
                ++k;
            }
            this.leftChild.clearCompareMark();
            this.clearCompareMark();
            this.rightChild.clearCompareMark();
        }
        else {
            this.setPosition("7.1.1.2.6", showCopyLeftOvers);
            this.setPosition("7.1.1.2.7", showCopyLeftOvers);
            final boolean showCopyRestOfB = showCopyLeftOvers && this.isExpanded("7.1.1.2.7");
            this.setPosition("7.1.1.2.8", showCopyLeftOvers);
            for (; i < p; ++i, this.setMark("A", k), this.setPosition("7.1.1.2.9", showCopyRestOfB), this.setPosition("7.1.1.2.10", showCopyRestOfB), this.setPosition("7.1.1.2.11", showCopyRestOfB), this.setPosition("7.1.1.2.8", showCopyRestOfB)) {
                this.setCompareMark(k, MergeSort.kCopyBackColor);
                this.leftChild.setCompareMark(i, MergeSort.kCopyBackColor);
                this.setMark("A", k);
                this.A[k] = this.B[i];
                ++k;
            }
            this.leftChild.clearCompareMark();
            this.clearCompareMark();
            this.rightChild.clearCompareMark();
            this.clearMark("A");
        }
        this.clearMark("A");
    }
    
    public MergeSort(final AlgorithmThread algorithmThread, final Integer[] data) {
        super(algorithmThread, data);
        this.columSpacing = 12;
        this.columWidth = 7;
        this.xTextBuffer = 20;
        this.yTextBuffer = 2;
        this.xBoxPadding = 4;
        this.yBoxPadding = 2;
        this.some = new int[] { 1, 2, 3, 4 };
        this.random = new Random(12345678L);
        this.numberOfComparisions = 0;
        this.numberOfSwaps = 0;
        this.started = false;
        this.active = false;
        this.processed = false;
        this.copyDone = false;
        this.markCompareMap = -1;
        this.arrays = new String[] { "A", "B", "C" };
        this.A = data;
        this.debugMode = false;
        this.markMap = new HashMap();
    }
    
    public void reInitialise(final Object data) {
        this.data = (int[])((int[])data);
        this.initialise(true);
        this.setPosition("");
        this.A = new Integer[this.data.length];
        for (int i = 0; i < this.data.length; ++i) {
            this.A[i] = new Integer(this.data[i]);
        }
        this.leftChild = null;
        this.rightChild = null;
        this.B = null;
        this.C = null;
        for (int i = 0; i < this.arrays.length; ++i) {
            this.markMap.put(this.arrays[i], new Integer(-1));
        }
        this.markCompareMap = -1;
        this.copyDone = false;
    }
    
    public void processColorSelection(final ColorSelection colorSelection) {
        final String atribute = colorSelection.getAtributeName();
        if (atribute.equalsIgnoreCase(ColorSelection.BACKGROUND)) {
            MergeSort.BACKGROUND = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR)) {
            MergeSort.TEXT_COLOR = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(MergeSort.PARTITION)) {
            MergeSort.PARTITION_COLOR = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(MergeSort.ACTIVE)) {
            MergeSort.ACTIVE_COLOR = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(MergeSort.PROCESSED)) {
            MergeSort.DOING_CHILDREN_COLOR = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(MergeSort.FINISHED)) {
            MergeSort.FINISHED_COLOR = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(MergeSort.WAITING)) {
            MergeSort.WAITING_COLOR = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(MergeSort.highlightLabel)) {
            MergeSort.HIGHLIGHT_COLOR = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(MergeSort.selectionLabel)) {
            MergeSort.selectionColor = colorSelection.getColor();
        }
    }
    
    public String getClassName() {
        return "MergeSort";
    }
    
    protected void addHighlight(final int id) {
        MergeSort.highlightedElements.put(new Integer(id), new Boolean(true));
    }
    
    protected boolean isHighlighted(final int id) {
        return MergeSort.highlightedElements.get(new Integer(id)) != null;
    }
    
    protected void removeAllHighlight() {
        MergeSort.highlightedElements.clear();
    }
    
    protected void removeHighlight(final int d) {
        MergeSort.highlightedElements.remove(new Integer(d));
    }
    
    public int getLeft() {
        return this.left;
    }
    
    public int getRight() {
        return this.right;
    }
    
    public MergeSort getLeftChild() {
        return this.leftChild;
    }
    
    public MergeSort getRightChild() {
        return this.rightChild;
    }
    
    public void display() {
    }
    
    public int[] getData() {
        return this.data;
    }
    
    public boolean isFinished() {
        return this.processed;
    }
    
    public boolean haveStarted() {
        return this.started;
    }
    
    public void resetRandomSeed() {
        MergeSort.needtoresetSeed = true;
    }
    
    public boolean isExpanded(final String key) {
        return ((MergeSortThread)this.algorithmThread).getWindow().isExpanded(key);
    }
    
    public void setPosition(final String key, final boolean isShowRecursion) {
        if (isShowRecursion) {
            this.setPosition(key);
        }
    }
    
    public void run() {
        if (MergeSort.needtoresetSeed) {
            this.random.setSeed(12345678L);
            MergeSort.needtoresetSeed = false;
        }
        this.runMergeSort(true);
    }
    
    public void setCurrentTint(final Color c) {
        this.tint = c;
    }
    
    public Color getCurrentTint() {
        if (this.tint != null) {
            return this.tint;
        }
        return MergeSort.ACTIVE_COLOR;
    }
    
    public void setCompareMark(final int index, final Color c) {
        this.markCompareMap = index;
        this.markCompareColor = c;
    }
    
    public void setCompareMark(final int index, final Color c, final boolean doIt) {
        if (doIt) {
            this.setCompareMark(index, c);
        }
    }
    
    public void clearCompareMark() {
        this.markCompareMap = -1;
    }
    
    protected Integer getCompareMark() {
        return new Integer(this.markCompareMap);
    }
    
    protected void setMark(final String var, final int index) {
        if (this.markMap != null) {
            this.markMap.put(var, new Integer(index));
        }
    }
    
    protected void clearMark(final String var) {
        if (this.markMap != null) {
            this.markMap.put(var, new Integer(-1));
        }
    }
    
    protected Integer getMark(final String var) {
        if (this.markMap == null) {
            return (Integer)null;
        }
        if (this.markMap.containsKey(var)) {
            return (Integer)this.markMap.get(var);
        }
        return (Integer)null;
    }
    
    protected void doCopy(final boolean showRecursion) {
        final boolean showLeftCopy = showRecursion && this.isExpanded("4.4");
        this.setMark("A", 0);
        this.setMark("B", 0);
        this.setPosition("4.4.1", showLeftCopy);
        this.setPosition("4.4.2", showLeftCopy);
        for (int i = 0; i < this.B.length; ++i) {
            this.setMark("A", i);
            this.setMark("B", i);
            this.setPosition("4.4.2.1", showLeftCopy);
            this.B[i] = this.A[i];
            this.setPosition("4.4.2.2", showLeftCopy);
        }
        this.clearMark("B");
        final boolean showRightCopy = showRecursion && this.isExpanded("4.5");
        this.setPosition("4.5", showRecursion);
        this.setPosition("4.5.1", showRightCopy);
        this.setPosition("4.5.2", showRightCopy);
        this.setPosition("4.5.3", showRightCopy);
        for (int j = this.B.length; j < this.A.length; ++j) {
            this.setMark("A", j);
            this.setMark("C", j - this.B.length);
            this.C[j - this.B.length] = this.A[j];
            this.setPosition("4.5.3.1", showRightCopy);
            this.setPosition("4.5.3.2", showRightCopy);
            this.setPosition("4.5.3.3", showRightCopy);
        }
        this.clearMark("C");
        this.clearMark("A");
    }
    
    public void runMergeSort(final boolean showRecursion) {
        this.started = true;
        if (!this.enabled) {
            this.processed = true;
            return;
        }
        this.active = true;
        this.setPosition("1", showRecursion);
        this.setPosition("3", showRecursion);
        if (this.A.length > 1) {
            this.setPosition("4", showRecursion);
            final boolean showSplitIntoHalves = this.isExpanded("4") && showRecursion;
            this.setPosition("4.1", showSplitIntoHalves);
            final List<Integer> aList = Arrays.asList(this.A);
            final int halfWay = (int)Math.round(Math.floor((double)this.A.length / 2.0));
            this.setPosition("4.2", showSplitIntoHalves);
            this.B = new Integer[halfWay];
            this.setPosition("4.3", showSplitIntoHalves);
            this.C = new Integer[this.A.length - halfWay];
            this.setPosition("4.4", showSplitIntoHalves);
            this.doCopy(showSplitIntoHalves);
            this.copyDone = true;
            this.leftChild = new MergeSort(this.algorithmThread, this.B);
            this.rightChild = new MergeSort(this.algorithmThread, this.C);
            final boolean showLeftRecursion = this.isExpanded("5") && showRecursion;
            this.setPosition("5", showRecursion);
            this.setPosition("5.1", showLeftRecursion);
            this.leftChild.runMergeSort(showLeftRecursion);
            this.leftChild.processed = true;
            final boolean showRightRecursion = this.isExpanded("6") && showRecursion;
            this.setPosition("6", showRecursion);
            this.setPosition("6.1", showRightRecursion);
            this.rightChild.runMergeSort(showRightRecursion);
            this.rightChild.processed = true;
            this.merge(showRecursion);
            this.rightChild = null;
            this.leftChild = null;
            this.processed = true;
        }
    }
    
    protected void makeQuestion() {
    }
    
    protected void removeAllQuestions() {
        MergeSort.questions.removeAllElements();
    }
    
    protected boolean hasQuestions() {
        return false;
    }
    
    public Vector<E> generateQuestions() {
        return null;
    }
    
    public MultipleTween generateTweens(final Paintable paintable, final Object parameter, final int numberOfSteps) {
        return null;
    }
    
    protected void generateTweens(final MultipleTween tweens, final MyElementArray elementArray, final int numberOfSteps) {
    }
    
    public int getNumberOfSwaps() {
        int temp = this.numberOfSwaps;
        if (this.leftChild != null) {
            temp = temp + this.leftChild.getNumberOfSwaps();
        }
        if (this.rightChild != null) {
            temp = temp + this.rightChild.getNumberOfSwaps();
        }
        return temp;
    }
    
    public int getNumberOfComparisions() {
        int temp = this.numberOfComparisions;
        if (this.leftChild != null) {
            temp = temp + this.leftChild.getNumberOfComparisions();
        }
        if (this.rightChild != null) {
            temp = temp + this.rightChild.getNumberOfComparisions();
        }
        return temp;
    }
    
    protected int getDepth(final int depth) {
        if (this.leftChild != null && this.rightChild != null) {
            return Math.max(this.leftChild.getDepth(depth + 1), this.rightChild.getDepth(depth + 1));
        }
        if (this.leftChild != null) {
            return this.leftChild.getDepth(depth + 1);
        }
        if (this.rightChild != null) {
            return this.rightChild.getDepth(depth + 1);
        }
        return depth;
    }
    
    public int getWidth(final Graphics g) {
        return (1 + this.getRight() - this.getLeft()) * (this.columWidth + this.columSpacing);
    }
    
    public int getHeight(final Graphics g) {
        return this.getDepth(1) * (2 * (g.getFontMetrics().getHeight() + this.yTextBuffer));
    }
    
    public void drawInProgress(final Point pos, final Graphics g, final Integer[] curr, final String var) {
        final MyElementArray dataArray = new MyElementArray(pos.x, pos.y);
        dataArray.setColumGap(this.columSpacing);
        dataArray.setElementWidth(this.columWidth);
        final int[] depthArray = new int[curr.length];
        for (int i = 0; i < curr.length; ++i) {
            Integer val = new Integer(0);
            boolean isEmptyPlaceHolder = true;
            if (curr[i] != null) {
                val = curr[i];
                isEmptyPlaceHolder = false;
            }
            final MyVertBar verticalBar = new MyVertBar(i, val, this.getCurrentTint(), new Point(0, 0));
            verticalBar.setIsEmpty(isEmptyPlaceHolder);
            verticalBar.setTextColor(MergeSort.TEXT_COLOR);
            verticalBar.setHighlightColor(MergeSort.selectionColor);
            dataArray.setValue(i, verticalBar);
            ((VerticalBar)((VerticalBar)dataArray.getElement(i))).setMarkersAboveValue(false);
            ((VerticalBar)((VerticalBar)dataArray.getElement(i))).setOffsetForValue(1);
        }
        final Rectangle r = g.getClipBounds();
        dataArray.setLocation((r.width - dataArray.getWidth()) / 2, pos.y);
        final Integer mark = this.getMark(var);
        if (mark != null && mark != -1) {
            dataArray.setMark("", mark.intValue());
        }
        else {
            dataArray.clearMarks();
        }
        dataArray.draw(g);
    }
    
    public void drawTree(final Point pos, final Graphics g) {
        final MyElementArray dataArray = new MyElementArray(pos.x, pos.y);
        dataArray.setColumGap(this.columSpacing);
        dataArray.setElementWidth(this.columWidth);
        for (int i = 0; i < this.A.length; ++i) {
            final MyVertBar verticalBar = new MyVertBar(i, this.A[i], this.getCurrentTint(), new Point(0, 0));
            verticalBar.setTextColor(MergeSort.TEXT_COLOR);
            verticalBar.setHighlightColor(MergeSort.selectionColor);
            dataArray.setValue(i, verticalBar);
            ((VerticalBar)((VerticalBar)dataArray.getElement(i))).setMarkersAboveValue(false);
            ((VerticalBar)((VerticalBar)dataArray.getElement(i))).setOffsetForValue(1);
        }
        if (MergeSort.maxHeight < 0) {
            MergeSort.maxHeight = dataArray.getHeight();
        }
        if (MergeSort.maxWidth < 0) {
            MergeSort.maxWidth = dataArray.getWidth();
        }
        final int dy = MergeSort.maxHeight + 30 + 25;
        final int dx = dataArray.getWidth() / 2 + 10;
        final Rectangle r = g.getClipBounds();
        dataArray.setLocation((r.width - dataArray.getWidth()) / 2, pos.y);
        final Integer aMark = this.getMark("A");
        if (aMark != null && aMark != -1) {
            dataArray.setMark("", aMark.intValue());
        }
        else {
            dataArray.clearMarks();
        }
        if (this.getCompareMark() != -1) {
            dataArray.setCompareMark(this.getCompareMark().intValue(), this.markCompareColor);
        }
        else {
            dataArray.clearCompareMarks();
        }
        dataArray.draw(g);
        final Graphics leftGraphics = g.create(0, dy, r.width / 2, r.height - dy);
        final Graphics rightGraphics = g.create(r.width / 2, dy, r.width / 2, r.height - dy);
        if (this.leftChild != null) {
            this.leftChild.setCurrentTint(tintLeft(this.getCurrentTint()));
            this.leftChild.drawTree(pos, leftGraphics);
        }
        else if (this.B != null && !this.copyDone) {
            this.drawInProgress(pos, leftGraphics, this.B, "B");
        }
        if (this.rightChild != null) {
            this.rightChild.setCurrentTint(tintRight(this.getCurrentTint()));
            this.rightChild.drawTree(pos, rightGraphics);
        }
        else if (this.C != null && !this.copyDone) {
            this.drawInProgress(pos, rightGraphics, this.C, "C");
        }
    }
    
    static {
        selectionLabel = Messages.getString("MergeSort.0");
        highlightLabel = Messages.getString("MergeSort.1");
        ACTIVE = Messages.getString("MergeSort.2");
        PROCESSED = Messages.getString("MergeSort.3");
        FINISHED = Messages.getString("MergeSort.4");
        WAITING = Messages.getString("MergeSort.5");
        PARTITION = Messages.getString("MergeSort.6");
        MergeSort.selectionColor = Color.yellow;
        MergeSort.BACKGROUND = Color.white;
        MergeSort.TEXT_COLOR = Color.black;
        MergeSort.PARTITION_COLOR = Color.yellow;
        MergeSort.WAITING_COLOR = Color.pink;
        MergeSort.HIGHLIGHT_COLOR = Color.green;
        MergeSort.DOING_CHILDREN_COLOR = Color.red.darker();
        MergeSort.ACTIVE_COLOR = Color.MAGENTA;
        MergeSort.FINISHED_COLOR = Color.gray;
        MergeSort.swapRequests = new Vector();
        MergeSort.questions = new Vector();
        MergeSort.highlightedElements = new Hashtable();
        kBHighlightColor = Color.CYAN;
        kCHighlightColor = Color.YELLOW;
        kCopyBackColor = Color.LIGHT_GRAY;
        MergeSort.needtoresetSeed = false;
        MergeSort.maxHeight = -1;
        MergeSort.maxWidth = -1;
    }
}
