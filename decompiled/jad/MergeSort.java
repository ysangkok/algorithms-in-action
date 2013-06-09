// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MergeSort.java

import com.cim.AIA.*;
import java.awt.*;
import java.util.*;
import localization.Messages;

public class MergeSort extends Algorithm
    implements ColorSelectionListener
{

    private static Color tintLeft(Color left)
    {
        return new Color(204, 0, 204);
    }

    private static Color tintRight(Color right)
    {
        return Color.MAGENTA;
    }

    public MergeSort(AlgorithmThread algorithmThread, Object data)
    {
        super(algorithmThread, data);
        columSpacing = 12;
        columWidth = 7;
        xTextBuffer = 20;
        yTextBuffer = 2;
        xBoxPadding = 4;
        yBoxPadding = 2;
        random = new Random(0xbc614eL);
        numberOfComparisions = 0;
        numberOfSwaps = 0;
        started = false;
        active = false;
        processed = false;
        copyDone = false;
        markCompareMap = -1;
        this.data = (int[])(int[])data;
        algThread = algorithmThread;
        A = new Integer[this.data.length];
        for(int i = 0; i < this.data.length; i++)
            A[i] = new Integer(this.data[i]);

        markMap = new HashMap();
        ConfigurationManager cm = ConfigurationManager.getConfigurationManager();
        ColorSelection partitionSelection = new ColorSelection(PARTITION_COLOR, PARTITION);
        partitionSelection.addClass(this);
        cm.addColorSelection(partitionSelection);
        cm.addColorSelectionListener(this);
        ColorSelection activeSelection = new ColorSelection(ACTIVE_COLOR, ACTIVE);
        activeSelection.addClass(this);
        cm.addColorSelection(activeSelection);
        ColorSelection processedSelection = new ColorSelection(DOING_CHILDREN_COLOR, PROCESSED);
        processedSelection.addClass(this);
        cm.addColorSelection(processedSelection);
        ColorSelection finishedSelection = new ColorSelection(FINISHED_COLOR, FINISHED);
        finishedSelection.addClass(this);
        cm.addColorSelection(finishedSelection);
        ColorSelection waitingSelection = new ColorSelection(WAITING_COLOR, WAITING);
        waitingSelection.addClass(this);
        cm.addColorSelection(waitingSelection);
        ColorSelection highlightSelection = new ColorSelection(HIGHLIGHT_COLOR, highlightLabel);
        highlightSelection.addClass(this);
        cm.addColorSelection(highlightSelection);
        cm.addColorSelectionListener(this);
        ColorSelection selectionSelection = new ColorSelection(selectionColor, selectionLabel);
        selectionSelection.addClass(this);
        cm.addColorSelection(selectionSelection);
        cm.addColorSelectionListener(this);
        initialise(true);
    }

    protected void initialise(boolean newSearch)
    {
        debugMode = false;
        active = false;
        processed = false;
        leftChild = null;
        rightChild = null;
        numberOfComparisions = 0;
        numberOfSwaps = 0;
    }

    protected void removeAllAnimationRequests()
    {
    }

    void merge(boolean showRecursion)
    {
        setPosition("7", showRecursion);
        int i = 0;
        int j = 0;
        int k = 0;
        setPosition("7.1", showRecursion && isExpanded("7"));
        int p = B.length;
        int q = C.length;
        setMark("A", k);
        leftChild.setMark("A", i);
        rightChild.setMark("A", j);
        setPosition("7.1.1.1", showRecursion && isExpanded("7"));
        boolean showWhileLoop = showRecursion && isExpanded("7.1.1");
        while(i < p && j < q) 
        {
            setMark("A", k);
            boolean showCompareAndCopy = showWhileLoop && isExpanded("7.1.1.1.1");
            leftChild.setCompareMark(i, kBHighlightColor, showCompareAndCopy);
            setPosition("7.1.1.1.1", showWhileLoop);
            rightChild.setCompareMark(j, kCHighlightColor);
            setPosition("7.1.1.1.1.1", showWhileLoop);
            if(B[i].intValue() <= C[j].intValue())
            {
                setCompareMark(k, kBHighlightColor);
                setMark("A", k);
                setPosition("7.1.1.1.1.2", showCompareAndCopy);
                A[k] = B[i];
                setPosition("7.1.1.1.1.2b", showCompareAndCopy);
                i++;
                leftChild.setMark("A", i);
            } else
            {
                setCompareMark(k, kCHighlightColor);
                setMark("A", k);
                setPosition("7.1.1.1.1.3", showCompareAndCopy);
                A[k] = C[j];
                setPosition("7.1.1.1.1.3c", showCompareAndCopy);
                j++;
                rightChild.setMark("A", j);
            }
            leftChild.clearCompareMark();
            clearCompareMark();
            rightChild.clearCompareMark();
            k++;
            setMark("A", k);
            setPosition("7.1.1.1.2", showWhileLoop);
        }
        setPosition("7.1.1.2", showRecursion && isExpanded("7"));
        boolean showCopyLeftOvers = showRecursion && isExpanded("7") && isExpanded("7.1.1.2");
        setMark("A", k);
        setPosition("7.1.1.2.1", showCopyLeftOvers);
        setMark("A", k);
        if(i == p)
        {
            setPosition("7.1.1.2.2", showCopyLeftOvers);
            boolean showCopyRestOfC = showCopyLeftOvers && isExpanded("7.1.1.2.2");
            setPosition("7.1.1.2.2.1", showCopyRestOfC);
            while(j < q) 
            {
                setCompareMark(k, kCopyBackColor);
                rightChild.setCompareMark(j, kCopyBackColor);
                setMark("A", k);
                A[k] = C[j];
                k++;
                j++;
                setPosition("7.1.1.2.3", showCopyRestOfC);
                setMark("A", k - 1);
                setPosition("7.1.1.2.4", showCopyRestOfC);
                setPosition("7.1.1.2.5", showCopyRestOfC);
                setPosition("7.1.1.2.2.1", showCopyRestOfC);
            }
            leftChild.clearCompareMark();
            clearCompareMark();
            rightChild.clearCompareMark();
        } else
        {
            setPosition("7.1.1.2.6", showCopyLeftOvers);
            setPosition("7.1.1.2.7", showCopyLeftOvers);
            boolean showCopyRestOfB = showCopyLeftOvers && isExpanded("7.1.1.2.7");
            setPosition("7.1.1.2.8", showCopyLeftOvers);
            while(i < p) 
            {
                setCompareMark(k, kCopyBackColor);
                leftChild.setCompareMark(i, kCopyBackColor);
                setMark("A", k);
                A[k] = B[i];
                k++;
                i++;
                setMark("A", k);
                setPosition("7.1.1.2.9", showCopyRestOfB);
                setPosition("7.1.1.2.10", showCopyRestOfB);
                setPosition("7.1.1.2.11", showCopyRestOfB);
                setPosition("7.1.1.2.8", showCopyRestOfB);
            }
            leftChild.clearCompareMark();
            clearCompareMark();
            rightChild.clearCompareMark();
            clearMark("A");
        }
        clearMark("A");
    }

    public MergeSort(AlgorithmThread algorithmThread, Integer data[])
    {
        super(algorithmThread, data);
        columSpacing = 12;
        columWidth = 7;
        xTextBuffer = 20;
        yTextBuffer = 2;
        xBoxPadding = 4;
        yBoxPadding = 2;
        random = new Random(0xbc614eL);
        numberOfComparisions = 0;
        numberOfSwaps = 0;
        started = false;
        active = false;
        processed = false;
        copyDone = false;
        markCompareMap = -1;
        A = data;
        debugMode = false;
        markMap = new HashMap();
    }

    public void reInitialise(Object data)
    {
        this.data = (int[])(int[])data;
        initialise(true);
        setPosition("");
        A = new Integer[this.data.length];
        for(int i = 0; i < this.data.length; i++)
            A[i] = new Integer(this.data[i]);

        leftChild = null;
        rightChild = null;
        B = null;
        C = null;
        for(int i = 0; i < arrays.length; i++)
            markMap.put(arrays[i], new Integer(-1));

        markCompareMap = -1;
        copyDone = false;
    }

    public void processColorSelection(ColorSelection colorSelection)
    {
        String atribute = colorSelection.getAtributeName();
        if(atribute.equalsIgnoreCase(ColorSelection.BACKGROUND))
            BACKGROUND = colorSelection.getColor();
        else
        if(atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR))
            TEXT_COLOR = colorSelection.getColor();
        else
        if(atribute.equalsIgnoreCase(PARTITION))
            PARTITION_COLOR = colorSelection.getColor();
        else
        if(atribute.equalsIgnoreCase(ACTIVE))
            ACTIVE_COLOR = colorSelection.getColor();
        else
        if(atribute.equalsIgnoreCase(PROCESSED))
            DOING_CHILDREN_COLOR = colorSelection.getColor();
        else
        if(atribute.equalsIgnoreCase(FINISHED))
            FINISHED_COLOR = colorSelection.getColor();
        else
        if(atribute.equalsIgnoreCase(WAITING))
            WAITING_COLOR = colorSelection.getColor();
        else
        if(atribute.equalsIgnoreCase(highlightLabel))
            HIGHLIGHT_COLOR = colorSelection.getColor();
        else
        if(atribute.equalsIgnoreCase(selectionLabel))
            selectionColor = colorSelection.getColor();
    }

    public String getClassName()
    {
        return "MergeSort";
    }

    protected void addHighlight(int id)
    {
        highlightedElements.put(new Integer(id), new Boolean(true));
    }

    protected boolean isHighlighted(int id)
    {
        return highlightedElements.get(new Integer(id)) != null;
    }

    protected void removeAllHighlight()
    {
        highlightedElements.clear();
    }

    protected void removeHighlight(int d)
    {
        highlightedElements.remove(new Integer(d));
    }

    public int getLeft()
    {
        return left;
    }

    public int getRight()
    {
        return right;
    }

    public MergeSort getLeftChild()
    {
        return leftChild;
    }

    public MergeSort getRightChild()
    {
        return rightChild;
    }

    public void display()
    {
    }

    public int[] getData()
    {
        return data;
    }

    public boolean isFinished()
    {
        return processed;
    }

    public boolean haveStarted()
    {
        return started;
    }

    public void resetRandomSeed()
    {
        needtoresetSeed = true;
    }

    public boolean isExpanded(String key)
    {
        return ((MergeSortThread)algorithmThread).getWindow().isExpanded(key);
    }

    public void setPosition(String key, boolean isShowRecursion)
    {
        if(isShowRecursion)
            setPosition(key);
    }

    public void run()
    {
        if(needtoresetSeed)
        {
            random.setSeed(0xbc614eL);
            needtoresetSeed = false;
        }
        runMergeSort(true);
    }

    public void setCurrentTint(Color c)
    {
        tint = c;
    }

    public Color getCurrentTint()
    {
        if(tint != null)
            return tint;
        else
            return ACTIVE_COLOR;
    }

    public void setCompareMark(int index, Color c)
    {
        markCompareMap = index;
        markCompareColor = c;
    }

    public void setCompareMark(int index, Color c, boolean doIt)
    {
        if(doIt)
            setCompareMark(index, c);
    }

    public void clearCompareMark()
    {
        markCompareMap = -1;
    }

    protected Integer getCompareMark()
    {
        return new Integer(markCompareMap);
    }

    protected void setMark(String var, int index)
    {
        if(markMap != null)
            markMap.put(var, new Integer(index));
    }

    protected void clearMark(String var)
    {
        if(markMap != null)
            markMap.put(var, new Integer(-1));
    }

    protected Integer getMark(String var)
    {
        if(markMap == null)
            return (Integer)null;
        if(markMap.containsKey(var))
            return (Integer)markMap.get(var);
        else
            return (Integer)null;
    }

    protected void doCopy(boolean showRecursion)
    {
        boolean showLeftCopy = showRecursion && isExpanded("4.4");
        setMark("A", 0);
        setMark("B", 0);
        setPosition("4.4.1", showLeftCopy);
        setPosition("4.4.2", showLeftCopy);
        for(int i = 0; i < B.length; i++)
        {
            setMark("A", i);
            setMark("B", i);
            setPosition("4.4.2.1", showLeftCopy);
            B[i] = A[i];
            setPosition("4.4.2.2", showLeftCopy);
        }

        clearMark("B");
        boolean showRightCopy = showRecursion && isExpanded("4.5");
        setPosition("4.5", showRecursion);
        setPosition("4.5.1", showRightCopy);
        setPosition("4.5.2", showRightCopy);
        setPosition("4.5.3", showRightCopy);
        for(int i = B.length; i < A.length; i++)
        {
            setMark("A", i);
            setMark("C", i - B.length);
            C[i - B.length] = A[i];
            setPosition("4.5.3.1", showRightCopy);
            setPosition("4.5.3.2", showRightCopy);
            setPosition("4.5.3.3", showRightCopy);
        }

        clearMark("C");
        clearMark("A");
    }

    public void runMergeSort(boolean showRecursion)
    {
        started = true;
        if(!enabled)
        {
            processed = true;
            return;
        }
        active = true;
        setPosition("1", showRecursion);
        setPosition("3", showRecursion);
        if(A.length > 1)
        {
            setPosition("4", showRecursion);
            boolean showSplitIntoHalves = isExpanded("4") && showRecursion;
            setPosition("4.1", showSplitIntoHalves);
            java.util.List aList = Arrays.asList(A);
            int halfWay = (int)Math.round(Math.floor((double)A.length / 2D));
            setPosition("4.2", showSplitIntoHalves);
            B = new Integer[halfWay];
            setPosition("4.3", showSplitIntoHalves);
            C = new Integer[A.length - halfWay];
            setPosition("4.4", showSplitIntoHalves);
            doCopy(showSplitIntoHalves);
            copyDone = true;
            leftChild = new MergeSort(algorithmThread, B);
            rightChild = new MergeSort(algorithmThread, C);
            boolean showLeftRecursion = isExpanded("5") && showRecursion;
            setPosition("5", showRecursion);
            setPosition("5.1", showLeftRecursion);
            leftChild.runMergeSort(showLeftRecursion);
            leftChild.processed = true;
            boolean showRightRecursion = isExpanded("6") && showRecursion;
            setPosition("6", showRecursion);
            setPosition("6.1", showRightRecursion);
            rightChild.runMergeSort(showRightRecursion);
            rightChild.processed = true;
            merge(showRecursion);
            rightChild = null;
            leftChild = null;
            processed = true;
        }
    }

    protected void makeQuestion()
    {
    }

    protected void removeAllQuestions()
    {
        questions.removeAllElements();
    }

    protected boolean hasQuestions()
    {
        return false;
    }

    public Vector generateQuestions()
    {
        return null;
    }

    public MultipleTween generateTweens(Paintable paintable, Object parameter, int numberOfSteps)
    {
        return null;
    }

    protected void generateTweens(MultipleTween multipletween, MyElementArray myelementarray, int i)
    {
    }

    public int getNumberOfSwaps()
    {
        int temp = numberOfSwaps;
        if(leftChild != null)
            temp += leftChild.getNumberOfSwaps();
        if(rightChild != null)
            temp += rightChild.getNumberOfSwaps();
        return temp;
    }

    public int getNumberOfComparisions()
    {
        int temp = numberOfComparisions;
        if(leftChild != null)
            temp += leftChild.getNumberOfComparisions();
        if(rightChild != null)
            temp += rightChild.getNumberOfComparisions();
        return temp;
    }

    protected int getDepth(int depth)
    {
        if(leftChild != null && rightChild != null)
            return Math.max(leftChild.getDepth(depth + 1), rightChild.getDepth(depth + 1));
        if(leftChild != null)
            return leftChild.getDepth(depth + 1);
        if(rightChild != null)
            return rightChild.getDepth(depth + 1);
        else
            return depth;
    }

    public int getWidth(Graphics g)
    {
        return ((1 + getRight()) - getLeft()) * (columWidth + columSpacing);
    }

    public int getHeight(Graphics g)
    {
        return getDepth(1) * (2 * (g.getFontMetrics().getHeight() + yTextBuffer));
    }

    public void drawInProgress(Point pos, Graphics g, Integer curr[], String var)
    {
        MyElementArray dataArray = new MyElementArray(pos.x, pos.y);
        dataArray.setColumGap(columSpacing);
        dataArray.setElementWidth(columWidth);
        int depthArray[] = new int[curr.length];
        for(int i = 0; i < curr.length; i++)
        {
            Integer val = new Integer(0);
            boolean isEmptyPlaceHolder = true;
            if(curr[i] != null)
            {
                val = curr[i];
                isEmptyPlaceHolder = false;
            }
            MyVertBar verticalBar = new MyVertBar(i, val, getCurrentTint(), new Point(0, 0));
            verticalBar.setIsEmpty(isEmptyPlaceHolder);
            verticalBar.setTextColor(TEXT_COLOR);
            verticalBar.setHighlightColor(selectionColor);
            dataArray.setValue(i, verticalBar);
            ((VerticalBar)(VerticalBar)dataArray.getElement(i)).setMarkersAboveValue(false);
            ((VerticalBar)(VerticalBar)dataArray.getElement(i)).setOffsetForValue(1);
        }

        Rectangle r = g.getClipBounds();
        dataArray.setLocation((r.width - dataArray.getWidth()) / 2, pos.y);
        Integer mark = getMark(var);
        if(mark != null && mark.intValue() != -1)
            dataArray.setMark("", mark.intValue());
        else
            dataArray.clearMarks();
        dataArray.draw(g);
    }

    public void drawTree(Point pos, Graphics g)
    {
        MyElementArray dataArray = new MyElementArray(pos.x, pos.y);
        dataArray.setColumGap(columSpacing);
        dataArray.setElementWidth(columWidth);
        for(int i = 0; i < A.length; i++)
        {
            MyVertBar verticalBar = new MyVertBar(i, A[i], getCurrentTint(), new Point(0, 0));
            verticalBar.setTextColor(TEXT_COLOR);
            verticalBar.setHighlightColor(selectionColor);
            dataArray.setValue(i, verticalBar);
            ((VerticalBar)(VerticalBar)dataArray.getElement(i)).setMarkersAboveValue(false);
            ((VerticalBar)(VerticalBar)dataArray.getElement(i)).setOffsetForValue(1);
        }

        if(maxHeight < 0)
            maxHeight = dataArray.getHeight();
        if(maxWidth < 0)
            maxWidth = dataArray.getWidth();
        int dy = maxHeight + 30 + 25;
        int dx = dataArray.getWidth() / 2 + 10;
        Rectangle r = g.getClipBounds();
        dataArray.setLocation((r.width - dataArray.getWidth()) / 2, pos.y);
        Integer aMark = getMark("A");
        if(aMark != null && aMark.intValue() != -1)
            dataArray.setMark("", aMark.intValue());
        else
            dataArray.clearMarks();
        if(getCompareMark().intValue() != -1)
            dataArray.setCompareMark(getCompareMark().intValue(), markCompareColor);
        else
            dataArray.clearCompareMarks();
        dataArray.draw(g);
        Graphics leftGraphics = g.create(0, dy, r.width / 2, r.height - dy);
        Graphics rightGraphics = g.create(r.width / 2, dy, r.width / 2, r.height - dy);
        if(leftChild != null)
        {
            leftChild.setCurrentTint(tintLeft(getCurrentTint()));
            leftChild.drawTree(pos, leftGraphics);
        } else
        if(B != null && !copyDone)
            drawInProgress(pos, leftGraphics, B, "B");
        if(rightChild != null)
        {
            rightChild.setCurrentTint(tintRight(getCurrentTint()));
            rightChild.drawTree(pos, rightGraphics);
        } else
        if(C != null && !copyDone)
            drawInProgress(pos, rightGraphics, C, "C");
    }

    protected static final String selectionLabel = Messages.getString("MergeSort.0");
    protected static final String highlightLabel = Messages.getString("MergeSort.1");
    protected static final String ACTIVE = Messages.getString("MergeSort.2");
    protected static final String PROCESSED = Messages.getString("MergeSort.3");
    protected static final String FINISHED = Messages.getString("MergeSort.4");
    protected static final String WAITING = Messages.getString("MergeSort.5");
    protected static final String PARTITION = Messages.getString("MergeSort.6");
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
    public int some[] = {
        1, 2, 3, 4
    };
    protected static final boolean DEBUG_MODE = false;
    protected static final long aSeed = 0xbc614eL;
    protected Random random;
    protected static Vector swapRequests = new Vector();
    protected static Vector questions = new Vector();
    protected static MyElementArray elementArray;
    protected static Hashtable highlightedElements = new Hashtable();
    protected int data[];
    protected Integer A[];
    protected Integer B[];
    protected Integer C[];
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
    protected static boolean needtoresetSeed = false;
    private static int maxHeight = -1;
    private static int maxWidth = -1;
    private boolean copyDone;
    private Color tint;
    private HashMap markMap;
    private int markCompareMap;
    private Color markCompareColor;
    private String arrays[] = {
        "A", "B", "C"
    };
    private AlgorithmThread algThread;

    static 
    {
        selectionColor = Color.yellow;
        BACKGROUND = Color.white;
        TEXT_COLOR = Color.black;
        PARTITION_COLOR = Color.yellow;
        WAITING_COLOR = Color.pink;
        HIGHLIGHT_COLOR = Color.green;
        DOING_CHILDREN_COLOR = Color.red.darker();
        ACTIVE_COLOR = Color.MAGENTA;
        FINISHED_COLOR = Color.gray;
        kBHighlightColor = Color.CYAN;
        kCHighlightColor = Color.YELLOW;
        kCopyBackColor = Color.LIGHT_GRAY;
    }
}
