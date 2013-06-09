// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MergeBUSort.java

import com.cim.AIA.*;
import java.awt.*;
import java.io.PrintStream;
import java.util.*;
import localization.Messages;

public class MergeBUSort extends Algorithm
    implements ColorSelectionListener
{

    private static Color tintLeft(Color left)
    {
        float aRGBA[] = left.getRGBComponents(new float[4]);
        aRGBA[0] *= 1.5F;
        aRGBA[0] = Math.min(aRGBA[0], 1.0F);
        aRGBA[1] *= 0.9F;
        aRGBA[1] = Math.min(aRGBA[1], 1.0F);
        return new Color(204, 0, 204);
    }

    private static Color tintRight(Color right)
    {
        return Color.MAGENTA;
    }

    public MergeBUSort(AlgorithmThread algorithmThread, Object data)
    {
        super(algorithmThread, data);
        columSpacing = 12;
        columWidth = 7;
        xTextBuffer = 20;
        yTextBuffer = 2;
        xBoxPadding = 4;
        yBoxPadding = 2;
        random = new Random(0xbc614eL);
        offsetIntoA = 0;
        segmentSize = -1;
        started = false;
        active = false;
        processed = false;
        doingChildren = false;
        markCompareMap = -1;
        markCompareMapB = -1;
        markCompareMapC = -1;
        this.data = (int[])(int[])data;
        offsetIntoA = 0;
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
        if(newSearch)
        {
            left = 0;
            right = data.length - 1;
            createDuplicateLabels(data);
        }
    }

    protected void removeAllAnimationRequests()
    {
    }

    private void setOffsetIntoA(int k)
    {
        offsetIntoA = k;
    }

    private int offsetIntoA()
    {
        return offsetIntoA;
    }

    void mergeBU(boolean showRecursion)
    {
        setPosition("7", showRecursion);
        int offset = offsetIntoA();
        int i = 0;
        int j = 0;
        int k = 0;
        setPosition("7.1", showRecursion && isExpanded("7"));
        int p = B.length;
        int q = C.length;
        setMark("A", k + offset);
        setMark("B", i);
        setMark("C", j);
        setPosition("7.1.1.1", showRecursion && isExpanded("7"));
        boolean showWhileLoop = showRecursion && isExpanded("7.1.1");
        while(i < p && j < q) 
        {
            setMark("A", k + offset);
            setMark("B", i);
            setMark("C", j);
            boolean showCompareAndCopy = showWhileLoop && isExpanded("7.1.1.1.1");
            setCompareMark("B", i, Color.CYAN, showCompareAndCopy);
            setPosition("7.1.1.1.1", showWhileLoop);
            setCompareMark("C", j, Color.YELLOW, showCompareAndCopy);
            setPosition("7.1.1.1.1.1", showWhileLoop);
            if(B[i].intValue() <= C[j].intValue())
            {
                setCompareMark(k + offset, Color.CYAN);
                setMark("A", k + offset);
                setPosition("7.1.1.1.1.2", showCompareAndCopy);
                A[k + offset] = B[i];
                setPosition("7.1.1.1.1.2b", showCompareAndCopy);
                i++;
                setMark("B", i);
            } else
            {
                setCompareMark(k + offset, Color.YELLOW);
                setMark("A", k + offset);
                setPosition("7.1.1.1.1.3", showCompareAndCopy);
                A[k + offset] = C[j];
                setPosition("7.1.1.1.1.3c", showCompareAndCopy);
                j++;
                setMark("C", j);
            }
            clearCompareMark("B");
            clearCompareMark();
            clearCompareMark("C");
            k++;
            setMark("A", k + offset);
            setPosition("7.1.1.1.2", showWhileLoop);
        }
        setPosition("7.1.1.2", showRecursion && isExpanded("7"));
        boolean showCopyLeftOvers = showRecursion && isExpanded("7") && isExpanded("7.1.1.2");
        setMark("A", k + offset);
        setPosition("7.1.1.2.1", showCopyLeftOvers);
        setMark("A", k + offset);
        if(i == p)
        {
            setMark("C", j);
            setPosition("7.1.1.2.2", showCopyLeftOvers);
            boolean showCopyRestOfC = showCopyLeftOvers && isExpanded("7.1.1.2.2");
            setPosition("7.1.1.2.2.1", showCopyRestOfC);
            setMark("C", j);
            while(j < q) 
            {
                setMark("C", j);
                setCompareMark("A", k + offset, Color.LIGHT_GRAY, showCopyRestOfC);
                setCompareMark("C", j, Color.LIGHT_GRAY, showCopyRestOfC);
                setMark("A", k + offset);
                A[k + offset] = C[j];
                k++;
                j++;
                setMark("C", j);
                setPosition("7.1.1.2.3", showCopyRestOfC);
                setMark("A", (k + offset) - 1);
                setPosition("7.1.1.2.4", showCopyRestOfC);
                setPosition("7.1.1.2.5", showCopyRestOfC);
                setPosition("7.1.1.2.2.1", showCopyRestOfC);
            }
            clearCompareMark("B");
            clearCompareMark();
            clearCompareMark("C");
        } else
        {
            setPosition("7.1.1.2.6", showCopyLeftOvers);
            setMark("B", i);
            setPosition("7.1.1.2.7", showCopyLeftOvers);
            boolean showCopyRestOfB = showCopyLeftOvers && isExpanded("7.1.1.2.7");
            setPosition("7.1.1.2.8", showCopyLeftOvers);
            while(i < p) 
            {
                setMark("B", i);
                setCompareMark("A", k + offset, Color.LIGHT_GRAY, showCopyRestOfB);
                setCompareMark("B", i, Color.LIGHT_GRAY, showCopyRestOfB);
                setMark("A", k + offset);
                A[k + offset] = B[i];
                k++;
                i++;
                setMark("B", i);
                setMark("A", k + offset);
                setPosition("7.1.1.2.9", showCopyRestOfB);
                setPosition("7.1.1.2.10", showCopyRestOfB);
                setPosition("7.1.1.2.11", showCopyRestOfB);
                setPosition("7.1.1.2.8", showCopyRestOfB);
            }
            clearCompareMark("B");
            clearCompareMark();
            clearCompareMark("C");
        }
    }

    protected void createDuplicateLabels(int data[])
    {
        Integer temp[] = new Integer[data.length];
        for(int i = 0; i < data.length; i++)
            temp[i] = new Integer(data[i]);

        duplicateLabels = DuplicateLabel.createDuplicateLabels(temp);
    }

    public MergeBUSort(AlgorithmThread algorithmThread, Integer data[])
    {
        super(algorithmThread, data);
        columSpacing = 12;
        columWidth = 7;
        xTextBuffer = 20;
        yTextBuffer = 2;
        xBoxPadding = 4;
        yBoxPadding = 2;
        random = new Random(0xbc614eL);
        offsetIntoA = 0;
        segmentSize = -1;
        started = false;
        active = false;
        processed = false;
        doingChildren = false;
        markCompareMap = -1;
        markCompareMapB = -1;
        markCompareMapC = -1;
        A = data;
        debugMode = false;
        markMap = new HashMap();
        offsetIntoA = 0;
    }

    public void reInitialise(Object data)
    {
        this.data = (int[])(int[])data;
        initialise(true);
        setPosition("");
        segmentSize = -1;
        A = new Integer[this.data.length];
        for(int i = 0; i < this.data.length; i++)
            A[i] = new Integer(this.data[i]);

        setOffsetIntoA(0);
        B = null;
        for(int i = 0; i < arrays.length; i++)
            markMap.put(arrays[i], new Integer(-1));

        markCompareMap = -1;
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
        return "MergeBUSort";
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

    public void display()
    {
        System.out.print((new StringBuilder()).append("left: ").append(left).append(" right: ").append(right).append(" || ").toString());
        for(int i = 0; i < data.length; i++)
            System.out.print((new StringBuilder()).append(data[i]).append(" ").toString());

        System.out.println("");
    }

    public int[] getData()
    {
        return data;
    }

    public boolean isActive()
    {
        if(isFinished())
            return false;
        else
            return active;
    }

    public boolean isDoingChildren()
    {
        if(isActive())
            return doingChildren;
        else
            return false;
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
        return ((MergeBUSortThread)algorithmThread).getWindow().isExpanded(key);
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
        runMergeBUSort(true);
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

    public void setCompareMark(String var, int index, Color c, boolean doIt)
    {
        if(!doIt)
            return;
        if(var == "B")
        {
            markCompareMapB = index;
            markCompareColorB = c;
            return;
        }
        if(var == "C")
        {
            markCompareMapC = index;
            markCompareColorC = c;
            return;
        }
        if(var == "A")
        {
            markCompareMap = index;
            markCompareColor = c;
            return;
        } else
        {
            return;
        }
    }

    public Integer getCompareMark(String var)
    {
        if(var == "B")
            return new Integer(markCompareMapB);
        if(var == "C")
            return new Integer(markCompareMapC);
        if(var == "A")
            return new Integer(markCompareMap);
        else
            return new Integer(-1);
    }

    public void setCompareMark(int index, Color c, boolean doIt)
    {
        if(doIt)
            setCompareMark(index, c);
    }

    public void clearCompareMark(String var)
    {
        setCompareMark(var, -1, Color.CYAN, true);
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

    public Integer[] subArray(Integer src[], int startIndex, int length)
    {
        Integer ret[] = new Integer[Math.min(length, src.length - startIndex)];
        for(int i = 0; i < ret.length; i++)
            ret[i] = src[i + startIndex];

        return ret;
    }

    public void runMergeBUSort(boolean showRecursion)
    {
        started = true;
        if(!enabled)
        {
            processed = true;
            return;
        }
        active = true;
        setPosition("2");
        setPosition("3");
        for(int segsize = 1; segsize < A.length; segsize *= 2)
        {
            segmentSize = segsize;
            setPosition("4");
            setPosition("5");
            for(int i = 0; i < A.length; i += 2 * segsize)
            {
                setOffsetIntoA(i);
                setPosition("6");
                B = subArray(A, i, segsize);
                setPosition("6.1");
                C = subArray(A, i + segsize, segsize);
                setPosition("7");
                mergeBU(showRecursion);
                B = null;
                C = null;
            }

            setPosition("last");
        }

    }

    protected void makeQuestion()
    {
    }

    protected void removeAllQuestions()
    {
    }

    protected boolean hasQuestions()
    {
        return false;
    }

    public Vector generateQuestions()
    {
        Vector temp = new Vector();
        return temp;
    }

    public MultipleTween generateTweens(Paintable paintable, Object parameter, int numberOfSteps)
    {
        return null;
    }

    protected void generateTweens(MultipleTween multipletween, MyElementArray myelementarray, int i)
    {
    }

    protected Color getColor()
    {
        Color color;
        if(isFinished())
            color = FINISHED_COLOR;
        else
        if(isDoingChildren())
            color = DOING_CHILDREN_COLOR;
        else
        if(isActive())
            color = ACTIVE_COLOR;
        else
            color = WAITING_COLOR;
        return color;
    }

    protected void calculateColors(Color acolor[], int ai[], int i)
    {
    }

    public MergeBUSort getActive()
    {
        return null;
    }

    protected int getDepth(int depth)
    {
        return 1;
    }

    public int getHeight(Graphics g)
    {
        return 1;
    }

    protected void draw(Graphics g1, Point point)
    {
    }

    public void drawInProgress(Point pos, Graphics g, Integer curr[], String var)
    {
        MyElementArray dataArray = new MyElementArray(pos.x, pos.y);
        dataArray.setColumGap(columSpacing);
        dataArray.setElementWidth(columWidth);
        Color colors[] = new Color[curr.length];
        int depthArray[] = new int[curr.length];
        calculateColors(colors, depthArray, 0);
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
        if(segmentSize > 0)
        {
            for(int i = 0; i < curr.length; i++)
                dataArray.setElemTintMark(i, var != "B" ? HONEY_DEW : CANTALOUPE);

        }
        dataArray.setLocation((r.width - dataArray.getWidth()) / 2, pos.y);
        Integer mark = getMark(var);
        if(mark != null && mark.intValue() != -1)
            dataArray.setMark("", mark.intValue());
        else
            dataArray.clearMarks();
        Color c = var != "B" ? markCompareColorC : markCompareColorB;
        if(getCompareMark(var).intValue() != -1)
            dataArray.setCompareMark(getCompareMark(var).intValue(), c);
        else
            dataArray.clearCompareMarks();
        dataArray.draw(g);
    }

    public void drawTree(Point pos, Graphics g)
    {
        MyElementArray dataArray = new MyElementArray(pos.x, pos.y);
        dataArray.setColumGap(columSpacing);
        dataArray.setElementWidth(columWidth);
        Color colors[] = new Color[A.length];
        int depthArray[] = new int[A.length];
        calculateColors(colors, depthArray, 0);
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
        if(segmentSize > 0)
        {
            dataArray.setInsetRectangle(offsetIntoA, (offsetIntoA + 2 * segmentSize) - 1);
            for(int i = 0; i < segmentSize; i++)
                dataArray.setElemTintMark(i + offsetIntoA, HONEY_DEW);

            int trun = Math.min(segmentSize, A.length - offsetIntoA);
            for(int i = 0; i < trun; i++)
                dataArray.setElemTintMark(i + offsetIntoA, CANTALOUPE);

        }
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
        if(B != null)
            drawInProgress(pos, leftGraphics, B, "B");
        if(C != null)
            drawInProgress(pos, rightGraphics, C, "C");
    }

    protected static String duplicateLabels[];
    protected static final String selectionLabel = Messages.getString("MergeBUSort.0");
    protected static final String highlightLabel = Messages.getString("MergeBUSort.1");
    protected static final String ACTIVE = Messages.getString("MergeBUSort.2");
    protected static final String PROCESSED = Messages.getString("MergeBUSort.3");
    protected static final String FINISHED = Messages.getString("MergeBUSort.4");
    protected static final String WAITING = Messages.getString("MergeBUSort.5");
    protected static final String PARTITION = Messages.getString("MergeBUSort.6");
    protected static Color selectionColor;
    protected static Color BACKGROUND;
    protected static Color TEXT_COLOR;
    private static final Color HONEY_DEW = new Color(204, 255, 102);
    private static final Color CANTALOUPE = new Color(255, 204, 102);
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
    public int some[] = {
        1, 2, 3, 4
    };
    protected static final boolean DEBUG_MODE = false;
    protected static int currentPartitionMethod = 10;
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
    private int offsetIntoA;
    private int segmentSize;
    protected int left;
    protected int right;
    protected boolean started;
    protected boolean active;
    protected boolean processed;
    protected boolean doingChildren;
    protected static boolean needtoresetSeed = false;
    private static int maxHeight = -1;
    private static int maxWidth = -1;
    private Color tint;
    private HashMap markMap;
    private int markCompareMap;
    private int markCompareMapB;
    private int markCompareMapC;
    private Color markCompareColorB;
    private Color markCompareColorC;
    private Color markCompareColor;
    private String arrays[] = {
        "A", "B"
    };

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
    }
}
