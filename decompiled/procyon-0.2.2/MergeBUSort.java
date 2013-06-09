import localization.*;
import java.util.*;
import java.awt.*;
import com.cim.AIA.*;

public class MergeBUSort extends Algorithm implements ColorSelectionListener
{
    protected static String[] duplicateLabels;
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
    private static final Color HONEY_DEW;
    private static final Color CANTALOUPE;
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
    public static final int MARKER_NOT_READY = -10;
    public static final int RIGHT_PARTITION = 10;
    public static final int RANDOM_PARTITION = 11;
    public static final int MIDDLE_OF_THREE_RANDOM = 12;
    public static final int MIDDLE_OF_THREE = 13;
    public int[] some;
    protected static final boolean DEBUG_MODE = 0;
    protected static int currentPartitionMethod;
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
    private int offsetIntoA;
    private int segmentSize;
    protected int left;
    protected int right;
    protected boolean started;
    protected boolean active;
    protected boolean processed;
    protected boolean doingChildren;
    protected static boolean needtoresetSeed;
    private static int maxHeight;
    private static int maxWidth;
    private Color tint;
    private HashMap<String, Integer> markMap;
    private int markCompareMap;
    private int markCompareMapB;
    private int markCompareMapC;
    private Color markCompareColorB;
    private Color markCompareColorC;
    private Color markCompareColor;
    private String[] arrays;
    
    private static Color tintLeft(final Color left) {
        final float[] rgbComponents;
        final float[] aRGBA = rgbComponents = left.getRGBComponents(new float[4]);
        final int n = 0;
        rgbComponents[n] = rgbComponents[n] * 1.5f;
        aRGBA[0] = Math.min(aRGBA[0], 1.0f);
        final float[] array = aRGBA;
        final int n2 = 1;
        array[n2] = array[n2] * 0.9f;
        aRGBA[1] = Math.min(aRGBA[1], 1.0f);
        return new Color(204, 0, 204);
    }
    
    private static Color tintRight(final Color right) {
        return Color.MAGENTA;
    }
    
    public MergeBUSort(final AlgorithmThread algorithmThread, final Object data) {
        super(algorithmThread, data);
        this.columSpacing = 12;
        this.columWidth = 7;
        this.xTextBuffer = 20;
        this.yTextBuffer = 2;
        this.xBoxPadding = 4;
        this.yBoxPadding = 2;
        this.some = new int[] { 1, 2, 3, 4 };
        this.random = new Random(12345678L);
        this.offsetIntoA = 0;
        this.segmentSize = -1;
        this.started = false;
        this.active = false;
        this.processed = false;
        this.doingChildren = false;
        this.markCompareMap = -1;
        this.markCompareMapB = -1;
        this.markCompareMapC = -1;
        this.arrays = new String[] { "A", "B" };
        this.data = (int[])((int[])data);
        this.offsetIntoA = 0;
        this.A = new Integer[this.data.length];
        for (int i = 0; i < this.data.length; ++i) {
            this.A[i] = new Integer(this.data[i]);
        }
        this.markMap = new HashMap();
        final ConfigurationManager cm = ConfigurationManager.getConfigurationManager();
        final ColorSelection partitionSelection = new ColorSelection(MergeBUSort.PARTITION_COLOR, MergeBUSort.PARTITION);
        partitionSelection.addClass(this);
        cm.addColorSelection(partitionSelection);
        cm.addColorSelectionListener(this);
        final ColorSelection activeSelection = new ColorSelection(MergeBUSort.ACTIVE_COLOR, MergeBUSort.ACTIVE);
        activeSelection.addClass(this);
        cm.addColorSelection(activeSelection);
        final ColorSelection processedSelection = new ColorSelection(MergeBUSort.DOING_CHILDREN_COLOR, MergeBUSort.PROCESSED);
        processedSelection.addClass(this);
        cm.addColorSelection(processedSelection);
        final ColorSelection finishedSelection = new ColorSelection(MergeBUSort.FINISHED_COLOR, MergeBUSort.FINISHED);
        finishedSelection.addClass(this);
        cm.addColorSelection(finishedSelection);
        final ColorSelection waitingSelection = new ColorSelection(MergeBUSort.WAITING_COLOR, MergeBUSort.WAITING);
        waitingSelection.addClass(this);
        cm.addColorSelection(waitingSelection);
        final ColorSelection highlightSelection = new ColorSelection(MergeBUSort.HIGHLIGHT_COLOR, MergeBUSort.highlightLabel);
        highlightSelection.addClass(this);
        cm.addColorSelection(highlightSelection);
        cm.addColorSelectionListener(this);
        final ColorSelection selectionSelection = new ColorSelection(MergeBUSort.selectionColor, MergeBUSort.selectionLabel);
        selectionSelection.addClass(this);
        cm.addColorSelection(selectionSelection);
        cm.addColorSelectionListener(this);
        this.initialise(true);
    }
    
    protected void initialise(final boolean newSearch) {
        this.debugMode = false;
        this.active = false;
        this.processed = false;
        if (newSearch) {
            this.left = 0;
            this.right = this.data.length - 1;
            this.createDuplicateLabels(this.data);
        }
    }
    
    protected void removeAllAnimationRequests() {
    }
    
    private void setOffsetIntoA(final int k) {
        this.offsetIntoA = k;
    }
    
    private int offsetIntoA() {
        return this.offsetIntoA;
    }
    
    void mergeBU(final boolean showRecursion) {
        this.setPosition("7", showRecursion);
        final int offset = this.offsetIntoA();
        int i = 0;
        int j = 0;
        int k = 0;
        this.setPosition("7.1", showRecursion && this.isExpanded("7"));
        final int p = this.B.length;
        final int q = this.C.length;
        this.setMark("A", k + offset);
        this.setMark("B", i);
        this.setMark("C", j);
        this.setPosition("7.1.1.1", showRecursion && this.isExpanded("7"));
        final boolean showWhileLoop = showRecursion && this.isExpanded("7.1.1");
        while (i < p && j < q) {
            this.setMark("A", k + offset);
            this.setMark("B", i);
            this.setMark("C", j);
            final boolean showCompareAndCopy = showWhileLoop && this.isExpanded("7.1.1.1.1");
            this.setCompareMark("B", i, Color.CYAN, showCompareAndCopy);
            this.setPosition("7.1.1.1.1", showWhileLoop);
            this.setCompareMark("C", j, Color.YELLOW, showCompareAndCopy);
            this.setPosition("7.1.1.1.1.1", showWhileLoop);
            if (this.B[i] <= this.C[j]) {
                this.setCompareMark(k + offset, Color.CYAN);
                this.setMark("A", k + offset);
                this.setPosition("7.1.1.1.1.2", showCompareAndCopy);
                this.A[k + offset] = this.B[i];
                this.setPosition("7.1.1.1.1.2b", showCompareAndCopy);
                ++i;
                this.setMark("B", i);
            }
            else {
                this.setCompareMark(k + offset, Color.YELLOW);
                this.setMark("A", k + offset);
                this.setPosition("7.1.1.1.1.3", showCompareAndCopy);
                this.A[k + offset] = this.C[j];
                this.setPosition("7.1.1.1.1.3c", showCompareAndCopy);
                ++j;
                this.setMark("C", j);
            }
            this.clearCompareMark("B");
            this.clearCompareMark();
            this.clearCompareMark("C");
            ++k;
            this.setMark("A", k + offset);
            this.setPosition("7.1.1.1.2", showWhileLoop);
        }
        this.setPosition("7.1.1.2", showRecursion && this.isExpanded("7"));
        final boolean showCopyLeftOvers = showRecursion && this.isExpanded("7") && this.isExpanded("7.1.1.2");
        this.setMark("A", k + offset);
        this.setPosition("7.1.1.2.1", showCopyLeftOvers);
        this.setMark("A", k + offset);
        if (i == p) {
            this.setMark("C", j);
            this.setPosition("7.1.1.2.2", showCopyLeftOvers);
            final boolean showCopyRestOfC = showCopyLeftOvers && this.isExpanded("7.1.1.2.2");
            this.setPosition("7.1.1.2.2.1", showCopyRestOfC);
            this.setMark("C", j);
            for (; j < q; ++j, this.setMark("C", j), this.setPosition("7.1.1.2.3", showCopyRestOfC), this.setMark("A", k + offset - 1), this.setPosition("7.1.1.2.4", showCopyRestOfC), this.setPosition("7.1.1.2.5", showCopyRestOfC), this.setPosition("7.1.1.2.2.1", showCopyRestOfC)) {
                this.setMark("C", j);
                this.setCompareMark("A", k + offset, Color.LIGHT_GRAY, showCopyRestOfC);
                this.setCompareMark("C", j, Color.LIGHT_GRAY, showCopyRestOfC);
                this.setMark("A", k + offset);
                this.A[k + offset] = this.C[j];
                ++k;
            }
            this.clearCompareMark("B");
            this.clearCompareMark();
            this.clearCompareMark("C");
        }
        else {
            this.setPosition("7.1.1.2.6", showCopyLeftOvers);
            this.setMark("B", i);
            this.setPosition("7.1.1.2.7", showCopyLeftOvers);
            final boolean showCopyRestOfB = showCopyLeftOvers && this.isExpanded("7.1.1.2.7");
            this.setPosition("7.1.1.2.8", showCopyLeftOvers);
            for (; i < p; ++i, this.setMark("B", i), this.setMark("A", k + offset), this.setPosition("7.1.1.2.9", showCopyRestOfB), this.setPosition("7.1.1.2.10", showCopyRestOfB), this.setPosition("7.1.1.2.11", showCopyRestOfB), this.setPosition("7.1.1.2.8", showCopyRestOfB)) {
                this.setMark("B", i);
                this.setCompareMark("A", k + offset, Color.LIGHT_GRAY, showCopyRestOfB);
                this.setCompareMark("B", i, Color.LIGHT_GRAY, showCopyRestOfB);
                this.setMark("A", k + offset);
                this.A[k + offset] = this.B[i];
                ++k;
            }
            this.clearCompareMark("B");
            this.clearCompareMark();
            this.clearCompareMark("C");
        }
    }
    
    protected void createDuplicateLabels(final int[] data) {
        final Integer[] temp = new Integer[data.length];
        for (int i = 0; i < data.length; ++i) {
            temp[i] = new Integer(data[i]);
        }
        MergeBUSort.duplicateLabels = DuplicateLabel.createDuplicateLabels(temp);
    }
    
    public MergeBUSort(final AlgorithmThread algorithmThread, final Integer[] data) {
        super(algorithmThread, data);
        this.columSpacing = 12;
        this.columWidth = 7;
        this.xTextBuffer = 20;
        this.yTextBuffer = 2;
        this.xBoxPadding = 4;
        this.yBoxPadding = 2;
        this.some = new int[] { 1, 2, 3, 4 };
        this.random = new Random(12345678L);
        this.offsetIntoA = 0;
        this.segmentSize = -1;
        this.started = false;
        this.active = false;
        this.processed = false;
        this.doingChildren = false;
        this.markCompareMap = -1;
        this.markCompareMapB = -1;
        this.markCompareMapC = -1;
        this.arrays = new String[] { "A", "B" };
        this.A = data;
        this.debugMode = false;
        this.markMap = new HashMap();
        this.offsetIntoA = 0;
    }
    
    public void reInitialise(final Object data) {
        this.data = (int[])((int[])data);
        this.initialise(true);
        this.setPosition("");
        this.segmentSize = -1;
        this.A = new Integer[this.data.length];
        for (int i = 0; i < this.data.length; ++i) {
            this.A[i] = new Integer(this.data[i]);
        }
        this.setOffsetIntoA(0);
        this.B = null;
        for (int i = 0; i < this.arrays.length; ++i) {
            this.markMap.put(this.arrays[i], new Integer(-1));
        }
        this.markCompareMap = -1;
    }
    
    public void processColorSelection(final ColorSelection colorSelection) {
        final String atribute = colorSelection.getAtributeName();
        if (atribute.equalsIgnoreCase(ColorSelection.BACKGROUND)) {
            MergeBUSort.BACKGROUND = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR)) {
            MergeBUSort.TEXT_COLOR = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(MergeBUSort.PARTITION)) {
            MergeBUSort.PARTITION_COLOR = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(MergeBUSort.ACTIVE)) {
            MergeBUSort.ACTIVE_COLOR = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(MergeBUSort.PROCESSED)) {
            MergeBUSort.DOING_CHILDREN_COLOR = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(MergeBUSort.FINISHED)) {
            MergeBUSort.FINISHED_COLOR = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(MergeBUSort.WAITING)) {
            MergeBUSort.WAITING_COLOR = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(MergeBUSort.highlightLabel)) {
            MergeBUSort.HIGHLIGHT_COLOR = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(MergeBUSort.selectionLabel)) {
            MergeBUSort.selectionColor = colorSelection.getColor();
        }
    }
    
    public String getClassName() {
        return "MergeBUSort";
    }
    
    protected void addHighlight(final int id) {
        MergeBUSort.highlightedElements.put(new Integer(id), new Boolean(true));
    }
    
    protected boolean isHighlighted(final int id) {
        return MergeBUSort.highlightedElements.get(new Integer(id)) != null;
    }
    
    protected void removeAllHighlight() {
        MergeBUSort.highlightedElements.clear();
    }
    
    protected void removeHighlight(final int d) {
        MergeBUSort.highlightedElements.remove(new Integer(d));
    }
    
    public int getLeft() {
        return this.left;
    }
    
    public int getRight() {
        return this.right;
    }
    
    public void display() {
        System.out.print("left: " + this.left + " right: " + this.right + " || ");
        for (int i = 0; i < this.data.length; ++i) {
            System.out.print(this.data[i] + " ");
        }
        System.out.println("");
    }
    
    public int[] getData() {
        return this.data;
    }
    
    public boolean isActive() {
        return !this.isFinished() && this.active;
    }
    
    public boolean isDoingChildren() {
        return this.isActive() && this.doingChildren;
    }
    
    public boolean isFinished() {
        return this.processed;
    }
    
    public boolean haveStarted() {
        return this.started;
    }
    
    public void resetRandomSeed() {
        MergeBUSort.needtoresetSeed = true;
    }
    
    public boolean isExpanded(final String key) {
        return ((MergeBUSortThread)this.algorithmThread).getWindow().isExpanded(key);
    }
    
    public void setPosition(final String key, final boolean isShowRecursion) {
        if (isShowRecursion) {
            this.setPosition(key);
        }
    }
    
    public void run() {
        if (MergeBUSort.needtoresetSeed) {
            this.random.setSeed(12345678L);
            MergeBUSort.needtoresetSeed = false;
        }
        this.runMergeBUSort(true);
    }
    
    public void setCurrentTint(final Color c) {
        this.tint = c;
    }
    
    public Color getCurrentTint() {
        if (this.tint != null) {
            return this.tint;
        }
        return MergeBUSort.ACTIVE_COLOR;
    }
    
    public void setCompareMark(final int index, final Color c) {
        this.markCompareMap = index;
        this.markCompareColor = c;
    }
    
    public void setCompareMark(final String var, final int index, final Color c, final boolean doIt) {
        if (!doIt) {
            return;
        }
        if (var == "B") {
            this.markCompareMapB = index;
            this.markCompareColorB = c;
            return;
        }
        if (var == "C") {
            this.markCompareMapC = index;
            this.markCompareColorC = c;
            return;
        }
        if (var == "A") {
            this.markCompareMap = index;
            this.markCompareColor = c;
            return;
        }
    }
    
    public Integer getCompareMark(final String var) {
        if (var == "B") {
            return new Integer(this.markCompareMapB);
        }
        if (var == "C") {
            return new Integer(this.markCompareMapC);
        }
        if (var == "A") {
            return new Integer(this.markCompareMap);
        }
        return new Integer(-1);
    }
    
    public void setCompareMark(final int index, final Color c, final boolean doIt) {
        if (doIt) {
            this.setCompareMark(index, c);
        }
    }
    
    public void clearCompareMark(final String var) {
        this.setCompareMark(var, -1, Color.CYAN, true);
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
    
    public Integer[] subArray(final Integer[] src, final int startIndex, final int length) {
        final Integer[] ret = new Integer[Math.min(length, src.length - startIndex)];
        for (int i = 0; i < ret.length; ++i) {
            ret[i] = src[i + startIndex];
        }
        return ret;
    }
    
    public void runMergeBUSort(final boolean showRecursion) {
        this.started = true;
        if (!this.enabled) {
            this.processed = true;
            return;
        }
        this.active = true;
        this.setPosition("2");
        this.setPosition("3");
        for (int segsize = 1; segsize < this.A.length; segsize = segsize * 2) {
            this.segmentSize = segsize;
            this.setPosition("4");
            this.setPosition("5");
            int i = 0;
            while (i < this.A.length) {
                this.setOffsetIntoA(i);
                this.setPosition("6");
                this.B = this.subArray(this.A, i, segsize);
                this.setPosition("6.1");
                this.C = this.subArray(this.A, i + segsize, segsize);
                this.setPosition("7");
                this.mergeBU(showRecursion);
                this.B = null;
                this.C = null;
                i = i + 2 * segsize;
            }
            this.setPosition("last");
        }
    }
    
    protected void makeQuestion() {
    }
    
    protected void removeAllQuestions() {
    }
    
    protected boolean hasQuestions() {
        return false;
    }
    
    public Vector<E> generateQuestions() {
        final Vector<E> temp = new Vector();
        return temp;
    }
    
    public MultipleTween generateTweens(final Paintable paintable, final Object parameter, final int numberOfSteps) {
        return null;
    }
    
    protected void generateTweens(final MultipleTween tweens, final MyElementArray elementArray, final int numberOfSteps) {
    }
    
    protected Color getColor() {
        Color color;
        if (this.isFinished()) {
            color = MergeBUSort.FINISHED_COLOR;
        }
        else if (this.isDoingChildren()) {
            color = MergeBUSort.DOING_CHILDREN_COLOR;
        }
        else if (this.isActive()) {
            color = MergeBUSort.ACTIVE_COLOR;
        }
        else {
            color = MergeBUSort.WAITING_COLOR;
        }
        return color;
    }
    
    protected void calculateColors(final Color[] colors, final int[] depthArray, final int depth) {
    }
    
    public MergeBUSort getActive() {
        return null;
    }
    
    protected int getDepth(final int depth) {
        return 1;
    }
    
    public int getHeight(final Graphics g) {
        return 1;
    }
    
    protected void draw(final Graphics g, final Point pos) {
    }
    
    public void drawInProgress(final Point pos, final Graphics g, final Integer[] curr, final String var) {
        final MyElementArray dataArray = new MyElementArray(pos.x, pos.y);
        dataArray.setColumGap(this.columSpacing);
        dataArray.setElementWidth(this.columWidth);
        final Color[] colors = new Color[curr.length];
        final int[] depthArray = new int[curr.length];
        this.calculateColors(colors, depthArray, 0);
        for (int i = 0; i < curr.length; ++i) {
            Integer val = new Integer(0);
            boolean isEmptyPlaceHolder = true;
            if (curr[i] != null) {
                val = curr[i];
                isEmptyPlaceHolder = false;
            }
            final MyVertBar verticalBar = new MyVertBar(i, val, this.getCurrentTint(), new Point(0, 0));
            verticalBar.setIsEmpty(isEmptyPlaceHolder);
            verticalBar.setTextColor(MergeBUSort.TEXT_COLOR);
            verticalBar.setHighlightColor(MergeBUSort.selectionColor);
            dataArray.setValue(i, verticalBar);
            ((VerticalBar)((VerticalBar)dataArray.getElement(i))).setMarkersAboveValue(false);
            ((VerticalBar)((VerticalBar)dataArray.getElement(i))).setOffsetForValue(1);
        }
        final Rectangle r = g.getClipBounds();
        if (this.segmentSize > 0) {
            for (int j = 0; j < curr.length; ++j) {
                dataArray.setElemTintMark(j, (var != "B") ? MergeBUSort.HONEY_DEW : MergeBUSort.CANTALOUPE);
            }
        }
        dataArray.setLocation((r.width - dataArray.getWidth()) / 2, pos.y);
        final Integer mark = this.getMark(var);
        if (mark != null && mark != -1) {
            dataArray.setMark("", mark.intValue());
        }
        else {
            dataArray.clearMarks();
        }
        final Color c = (var != "B") ? this.markCompareColorC : this.markCompareColorB;
        if (this.getCompareMark(var) != -1) {
            dataArray.setCompareMark(this.getCompareMark(var).intValue(), c);
        }
        else {
            dataArray.clearCompareMarks();
        }
        dataArray.draw(g);
    }
    
    public void drawTree(final Point pos, final Graphics g) {
        final MyElementArray dataArray = new MyElementArray(pos.x, pos.y);
        dataArray.setColumGap(this.columSpacing);
        dataArray.setElementWidth(this.columWidth);
        final Color[] colors = new Color[this.A.length];
        final int[] depthArray = new int[this.A.length];
        this.calculateColors(colors, depthArray, 0);
        for (int i = 0; i < this.A.length; ++i) {
            final MyVertBar verticalBar = new MyVertBar(i, this.A[i], this.getCurrentTint(), new Point(0, 0));
            verticalBar.setTextColor(MergeBUSort.TEXT_COLOR);
            verticalBar.setHighlightColor(MergeBUSort.selectionColor);
            dataArray.setValue(i, verticalBar);
            ((VerticalBar)((VerticalBar)dataArray.getElement(i))).setMarkersAboveValue(false);
            ((VerticalBar)((VerticalBar)dataArray.getElement(i))).setOffsetForValue(1);
        }
        if (MergeBUSort.maxHeight < 0) {
            MergeBUSort.maxHeight = dataArray.getHeight();
        }
        if (MergeBUSort.maxWidth < 0) {
            MergeBUSort.maxWidth = dataArray.getWidth();
        }
        final int dy = MergeBUSort.maxHeight + 30 + 25;
        final int dx = dataArray.getWidth() / 2 + 10;
        final Rectangle r = g.getClipBounds();
        if (this.segmentSize > 0) {
            dataArray.setInsetRectangle(this.offsetIntoA, this.offsetIntoA + 2 * this.segmentSize - 1);
            for (int j = 0; j < this.segmentSize; ++j) {
                dataArray.setElemTintMark(j + this.offsetIntoA, MergeBUSort.HONEY_DEW);
            }
            for (int trun = Math.min(this.segmentSize, this.A.length - this.offsetIntoA), k = 0; k < trun; ++k) {
                dataArray.setElemTintMark(k + this.offsetIntoA, MergeBUSort.CANTALOUPE);
            }
        }
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
        if (this.B != null) {
            this.drawInProgress(pos, leftGraphics, this.B, "B");
        }
        if (this.C != null) {
            this.drawInProgress(pos, rightGraphics, this.C, "C");
        }
    }
    
    static {
        selectionLabel = Messages.getString("MergeBUSort.0");
        highlightLabel = Messages.getString("MergeBUSort.1");
        ACTIVE = Messages.getString("MergeBUSort.2");
        PROCESSED = Messages.getString("MergeBUSort.3");
        FINISHED = Messages.getString("MergeBUSort.4");
        WAITING = Messages.getString("MergeBUSort.5");
        PARTITION = Messages.getString("MergeBUSort.6");
        MergeBUSort.selectionColor = Color.yellow;
        MergeBUSort.BACKGROUND = Color.white;
        MergeBUSort.TEXT_COLOR = Color.black;
        HONEY_DEW = new Color(204, 255, 102);
        CANTALOUPE = new Color(255, 204, 102);
        MergeBUSort.PARTITION_COLOR = Color.yellow;
        MergeBUSort.WAITING_COLOR = Color.pink;
        MergeBUSort.HIGHLIGHT_COLOR = Color.green;
        MergeBUSort.DOING_CHILDREN_COLOR = Color.red.darker();
        MergeBUSort.ACTIVE_COLOR = Color.MAGENTA;
        MergeBUSort.FINISHED_COLOR = Color.gray;
        MergeBUSort.currentPartitionMethod = 10;
        MergeBUSort.swapRequests = new Vector();
        MergeBUSort.questions = new Vector();
        MergeBUSort.highlightedElements = new Hashtable();
        MergeBUSort.needtoresetSeed = false;
        MergeBUSort.maxHeight = -1;
        MergeBUSort.maxWidth = -1;
    }
}
