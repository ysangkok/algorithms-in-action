// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QuickSort.java

import com.cim.AIA.*;
import java.awt.*;
import java.io.PrintStream;
import java.util.*;
import localization.Messages;

public class QuickSort extends Algorithm
    implements ColorSelectionListener
{

    public QuickSort(AlgorithmThread algorithmThread, int data[], int left, int right)
    {
        super(algorithmThread, data);
        columSpacing = 12;
        columWidth = 7;
        xTextBuffer = 20;
        yTextBuffer = 2;
        xBoxPadding = 4;
        yBoxPadding = 2;
        random = new Random(0xbc614eL);
        P = -10;
        PPosition = -10;
        i = -10;
        j = -10;
        numberOfComparisions = 0;
        numberOfSwaps = 0;
        started = false;
        active = false;
        processed = false;
        doingChildren = false;
        drawRectangles = false;
        this.data = data;
        this.left = left;
        this.right = right;
        debugMode = false;
    }

    public QuickSort(AlgorithmThread algorithmThread, Object data)
    {
        super(algorithmThread, data);
        columSpacing = 12;
        columWidth = 7;
        xTextBuffer = 20;
        yTextBuffer = 2;
        xBoxPadding = 4;
        yBoxPadding = 2;
        random = new Random(0xbc614eL);
        P = -10;
        PPosition = -10;
        i = -10;
        j = -10;
        numberOfComparisions = 0;
        numberOfSwaps = 0;
        started = false;
        active = false;
        processed = false;
        doingChildren = false;
        drawRectangles = false;
        this.data = (int[])(int[])data;
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

    protected void addHighlight(int id)
    {
        highlightedElements.put(new Integer(id), new Boolean(true));
    }

    protected void calculateColors(Color colors[], int depthArray[], int depth)
    {
        for(int i = left; i <= right; i++)
        {
            if(i < 0 || i >= colors.length)
                continue;
            if(colors[i] == null)
            {
                colors[i] = getColor();
                if(i == PPosition && !isDoingChildren() && !isFinished())
                    colors[i] = PARTITION_COLOR;
                if(i >= 0 && i < depthArray.length)
                    depthArray[i] = depth;
                continue;
            }
            if(i >= 0 && i < depthArray.length && depth > depthArray[i])
            {
                colors[i] = getColor();
                depthArray[i] = depth;
            }
            if(i == PPosition && !isDoingChildren() && !isFinished())
                colors[i] = PARTITION_COLOR;
        }

        if(leftChild != null)
            leftChild.calculateColors(colors, depthArray, depth + 1);
        if(rightChild != null)
            rightChild.calculateColors(colors, depthArray, depth + 1);
    }

    protected void createDuplicateLabels(int data[])
    {
        Integer temp[] = new Integer[data.length];
        for(int i = 0; i < data.length; i++)
            temp[i] = new Integer(data[i]);

        duplicateLabels = DuplicateLabel.createDuplicateLabels(temp);
    }

    public void display()
    {
        System.out.print((new StringBuilder()).append(Messages.getString("QuickSort.11")).append(left).append(Messages.getString("QuickSort.12")).append(right).append(" || ").toString());
        for(int i = 0; i < data.length; i++)
            System.out.print((new StringBuilder()).append(data[i]).append(" ").toString());

        System.out.println("");
    }

    public boolean doDrawRectangles()
    {
        return drawRectangles;
    }

    protected void draw(Graphics g, Point pos)
    {
        g.setColor(getColor());
        g.fillRect((getLeft() * (columWidth + columSpacing) + pos.x) - columWidth / 2, pos.y - g.getFontMetrics().getHeight(), ((1 + getRight()) - getLeft()) * (columWidth + columSpacing), g.getFontMetrics().getHeight());
        g.setColor(TEXT_COLOR);
        for(int i = getLeft(); i <= getRight(); i++)
            g.drawString((new StringBuilder()).append(data[i]).append("").toString(), (i * (columWidth + columSpacing) + pos.x + columWidth / 2) - g.getFontMetrics().stringWidth((new StringBuilder()).append(data[i]).append("").toString()) / 2, pos.y);

        if(isActive() && !isDoingChildren() && !isFinished())
        {
            int iMarker = getRight() * (columWidth + columSpacing) + pos.x + columWidth / 2;
            int jMarker = getLeft() * (columWidth + columSpacing) + pos.x + columWidth / 2;
            if(getI() != -10)
            {
                g.setColor(TEXT_COLOR);
                g.drawString("i", (getI() * (columWidth + columSpacing) + pos.x + columWidth / 2) - g.getFontMetrics().stringWidth("i") / 2, pos.y + g.getFontMetrics().getHeight() + yTextBuffer);
            }
            if(getJ() != -10)
            {
                g.setColor(TEXT_COLOR);
                g.drawString("J", (getJ() * (columWidth + columSpacing) + pos.x + columWidth / 2) - g.getFontMetrics().stringWidth("J") / 2, pos.y + 2 * yTextBuffer + 2 * g.getFontMetrics().getHeight());
            }
        }
        if(getPPosition() != -10)
        {
            g.setColor(Color.black);
            g.drawRect((getPPosition() * (columWidth + columSpacing) + pos.x) - xBoxPadding, pos.y - yBoxPadding - g.getFontMetrics().getHeight(), columWidth + 2 * xBoxPadding, g.getFontMetrics().getHeight() + 2 * yBoxPadding);
        }
        if(getLeftChild() != null)
            getLeftChild().draw(g, new Point(pos.x, pos.y + 2 * g.getFontMetrics().getHeight() + 2 * yTextBuffer));
        if(getRightChild() != null)
            getRightChild().draw(g, new Point(pos.x, pos.y + 2 * g.getFontMetrics().getHeight() + 2 * yTextBuffer));
    }

    public Vector generateQuestions()
    {
        Vector temp = new Vector();
        for(int i = 0; i < questions.size(); i++)
            temp.addElement(questions.elementAt(i));

        return temp;
    }

    protected void generateTweens(MultipleTween tweens, MyElementArrayQS elementArray, int numberOfSteps)
    {
        for(int i = 0; i < swapRequests.size(); i++)
        {
            SwapRequest swapRequest = (SwapRequest)swapRequests.elementAt(i);
            VerticalBar first = (VerticalBar)(VerticalBar)elementArray.getElement(swapRequest.getFirstId());
            VerticalBar second = (VerticalBar)(VerticalBar)elementArray.getElement(swapRequest.getSecondId());
            tweens.add(new MoveTween(null, first, second.getPosition(), first.getPosition(), false, numberOfSteps));
            tweens.add(new MoveTween(null, second, first.getPosition(), second.getPosition(), false, numberOfSteps));
        }

        if(leftChild != null)
            leftChild.generateTweens(tweens, elementArray, numberOfSteps);
        if(rightChild != null)
            rightChild.generateTweens(tweens, elementArray, numberOfSteps);
    }

    public MultipleTween generateTweens(Paintable paintable, Object parameter, int numberOfSteps)
    {
        MultipleTween tweens = new MultipleTween(paintable);
        MyElementArrayQS elementArray = (MyElementArrayQS)(MyElementArrayQS)parameter;
        generateTweens(tweens, elementArray, numberOfSteps);
        return tweens;
    }

    public QuickSort getActive()
    {
        if(isActive() && !isDoingChildren())
            return this;
        QuickSort temp = null;
        if(leftChild != null)
            temp = leftChild.getActive();
        if(temp != null)
            return temp;
        if(rightChild != null)
            temp = rightChild.getActive();
        if(temp != null)
            return temp;
        else
            return null;
    }

    public String getClassName()
    {
        return Messages.getString("QuickSort.10");
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

    public int[] getData()
    {
        return data;
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

    public MyElementArrayQS getElementArray(Point location)
    {
        elementArray = new MyElementArrayQS(location.x, location.y);
        elementArray.setColumGap(columSpacing);
        elementArray.setElementWidth(columWidth);
        Color colors[] = new Color[data.length];
        int depthArray[] = new int[data.length];
        calculateColors(colors, depthArray, 0);
        for(int i = 0; i < data.length; i++)
        {
            if(isHighlighted(i))
            {
                VerticalBar verticalBar = new VerticalBar(i, data[i], colors[i], HIGHLIGHT_COLOR, new Point(0, 0));
                verticalBar.setTextColor(TEXT_COLOR);
                verticalBar.setHighlightColor(selectionColor);
                elementArray.setValue(i, verticalBar);
            } else
            {
                VerticalBar verticalBar = new VerticalBar(i, data[i], colors[i], new Point(0, 0));
                verticalBar.setTextColor(TEXT_COLOR);
                verticalBar.setHighlightColor(selectionColor);
                elementArray.setValue(i, verticalBar);
            }
            ((VerticalBar)(VerticalBar)elementArray.getElement(i)).setMarkersAboveValue(false);
            ((VerticalBar)(VerticalBar)elementArray.getElement(i)).setOffsetForValue(1);
            if(duplicateLabels[i] != null)
            {
                StringMarker stringMarker = new StringMarker((new StringBuilder()).append("").append(data[i]).append(duplicateLabels[i]).toString());
                stringMarker.setColor(TEXT_COLOR);
                stringMarker.setBackgroundColor(BACKGROUND);
                elementArray.getElement(i).addMarker(stringMarker);
            }
        }

        return elementArray;
    }

    public int getHeight(Graphics g)
    {
        return getDepth(1) * (2 * (g.getFontMetrics().getHeight() + yTextBuffer));
    }

    public int getI()
    {
        return i;
    }

    public int getJ()
    {
        return j;
    }

    public int getLeft()
    {
        return left;
    }

    public QuickSort getLeftChild()
    {
        return leftChild;
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

    public int getNumberOfSwaps()
    {
        int temp = numberOfSwaps;
        if(leftChild != null)
            temp += leftChild.getNumberOfSwaps();
        if(rightChild != null)
            temp += rightChild.getNumberOfSwaps();
        return temp;
    }

    public int getPPosition()
    {
        return PPosition;
    }

    public int getPValue()
    {
        return P;
    }

    public int getRight()
    {
        return right;
    }

    public QuickSort getRightChild()
    {
        return rightChild;
    }

    public int getWidth(Graphics g)
    {
        return ((1 + getRight()) - getLeft()) * (columWidth + columSpacing);
    }

    protected boolean hasQuestions()
    {
        return questions.size() >= 1;
    }

    public boolean haveStarted()
    {
        return started;
    }

    protected void initialise(boolean newSearch)
    {
        debugMode = false;
        active = false;
        processed = false;
        leftChild = null;
        rightChild = null;
        P = -10;
        PPosition = -10;
        i = -10;
        j = -10;
        numberOfComparisions = 0;
        numberOfSwaps = 0;
        drawRectangles = false;
        if(newSearch)
        {
            left = 0;
            right = data.length - 1;
            createDuplicateLabels(data);
        }
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

    public boolean isExpanded(String key)
    {
        return ((QuickSortThread)algorithmThread).getWindow().isExpanded(key);
    }

    public boolean isFinished()
    {
        return processed;
    }

    protected boolean isHighlighted(int id)
    {
        return highlightedElements.get(new Integer(id)) != null;
    }

    protected void makeQuestion()
    {
        if(elementArray != null)
        {
            double tempDouble = random.nextDouble();
            if(tempDouble > percentageChanceForQuestion)
                return;
            boolean doIAndJEndUp = true;
            if(random.nextDouble() <= percentageChanceForSwapQuestion)
                doIAndJEndUp = false;
            int iSwapPosition = i;
            int jSwapPosition = j;
            while(data[++iSwapPosition] <= P && iSwapPosition != right) ;
            while(data[--jSwapPosition] >= P && jSwapPosition != left) ;
            if(iSwapPosition >= jSwapPosition && !doIAndJEndUp)
                jSwapPosition = right;
            IAndJPositionQuestionQS question = new IAndJPositionQuestionQS();
            if(doIAndJEndUp)
                question.setInstructions(IAndJPositionQuestionQS.QUESTION_END_UP_LABEL);
            else
                question.setInstructions(IAndJPositionQuestionQS.QUESTION_SWAP_LABEL);
            doIAndJEndUp = !doIAndJEndUp;
            question.addAnswer(new Integer(iSwapPosition));
            question.addAnswer(new Integer(jSwapPosition));
            questions.addElement(question);
        } else
        {
            System.out.println("Element Array is null, not adding question");
        }
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

    public void reInitialise(Object data)
    {
        this.data = (int[])(int[])data;
        initialise(true);
        setPosition("");
    }

    protected void removeAllAnimationRequests()
    {
        swapRequests.removeAllElements();
    }

    protected void removeAllHighlight()
    {
        highlightedElements.clear();
    }

    protected void removeAllQuestions()
    {
        questions.removeAllElements();
    }

    public void resetRandomSeed()
    {
        needtoresetSeed = true;
    }

    public void run()
    {
        if(needtoresetSeed)
        {
            random.setSeed(0xbc614eL);
            needtoresetSeed = false;
        }
        runQuickSort(true);
    }

    public void runQuickSort(boolean showRecursion)
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
        if(right > left)
        {
            if(isExpanded("4"))
                drawRectangles = true;
            switch(currentPartitionMethod)
            {
            default:
                break;

            case 10: // '\n'
                PPosition = right;
                P = data[PPosition];
                setPosition("4.1", showRecursion);
                break;

            case 11: // '\013'
                PPosition = (int)(random.nextDouble() * (double)(right - left) + (double)left);
                P = data[PPosition];
                setPosition("4.1", showRecursion);
                swapValues(PPosition, right);
                PPosition = right;
                setPosition("4.2", showRecursion);
                break;

            case 12: // '\f'
                int one = (int)(random.nextDouble() * (double)(right - left) + (double)left);
                int two = (int)(random.nextDouble() * (double)(right - left) + (double)left);
                int three = (int)(random.nextDouble() * (double)(right - left) + (double)left);
                int tempNumbers[] = {
                    one, two, three
                };
                for(int i = 0; i < tempNumbers.length; i++)
                {
                    for(int j = i; j < tempNumbers.length; j++)
                        if(tempNumbers[j] < tempNumbers[i])
                        {
                            int temp = tempNumbers[i];
                            tempNumbers[i] = tempNumbers[j];
                            tempNumbers[j] = temp;
                        }

                }

                addHighlight(tempNumbers[0]);
                setPosition("4.1", showRecursion);
                addHighlight(tempNumbers[1]);
                setPosition("4.2", showRecursion);
                addHighlight(tempNumbers[2]);
                setPosition("4.3", showRecursion);
                for(int i = 0; i < tempNumbers.length; i++)
                {
                    for(int j = i; j < tempNumbers.length; j++)
                        if(data[tempNumbers[j]] < data[tempNumbers[i]])
                            swapValues(tempNumbers[i], tempNumbers[j]);

                }

                setPosition("4.4", showRecursion);
                removeAllHighlight();
                addHighlight(tempNumbers[1]);
                PPosition = tempNumbers[1];
                P = data[PPosition];
                setPosition("4.5", showRecursion);
                removeAllHighlight();
                swapValues(PPosition, right);
                PPosition = right;
                setPosition("4.6", showRecursion);
                break;

            case 13: // '\r'
                int one2 = left;
                int two2 = (right + left) / 2;
                int three2 = right;
                int tempNumbers2[] = {
                    one2, two2, three2
                };
                addHighlight(tempNumbers2[0]);
                setPosition("4.1", showRecursion);
                addHighlight(tempNumbers2[1]);
                setPosition("4.2", showRecursion);
                addHighlight(tempNumbers2[2]);
                setPosition("4.3", showRecursion);
                for(int i = 0; i < tempNumbers2.length; i++)
                {
                    for(int j = i; j < tempNumbers2.length; j++)
                        if(data[tempNumbers2[j]] < data[tempNumbers2[i]])
                            swapValues(tempNumbers2[i], tempNumbers2[j]);

                }

                setPosition("4.4", showRecursion);
                removeAllHighlight();
                addHighlight(tempNumbers2[1]);
                PPosition = tempNumbers2[1];
                P = data[PPosition];
                setPosition("4.5", showRecursion);
                removeAllHighlight();
                swapValues(PPosition, right);
                PPosition = right;
                setPosition("4.6", showRecursion);
                break;
            }
            drawRectangles = false;
            if(isExpanded("5"))
                drawRectangles = true;
            this.i = left - 1;
            setPosition("5.1.1", showRecursion);
            this.j = right;
            setPosition("5.1.2", showRecursion);
            makeQuestion();
            setPosition("5.2", showRecursion);
            do
            {
                this.i++;
                setPosition("5.2.1.1", showRecursion);
                do
                {
                    if(data[this.i] >= P)
                        break;
                    this.i++;
                    if(this.i == right)
                        break;
                    numberOfComparisions++;
                    setPosition("5.2.1.1", showRecursion);
                } while(true);
                numberOfComparisions++;
                this.j--;
                setPosition("5.2.2.1", showRecursion);
                do
                {
                    if(data[this.j] <= P)
                        break;
                    this.j--;
                    if(this.j == left - 1)
                        break;
                    numberOfComparisions++;
                    setPosition("5.2.2.1", showRecursion);
                } while(true);
                setPosition("5.2.3.1", showRecursion);
                if(this.i >= this.j)
                    break;
                swapValues(this.i, this.j);
                setPosition("5.2.5.1", showRecursion);
                makeQuestion();
                setPosition("5.2", showRecursion);
            } while(true);
            swapValues(this.i, right);
            leftChild = new QuickSort(algorithmThread, data, left, this.i - 1);
            rightChild = new QuickSort(algorithmThread, data, this.i + 1, right);
            setPosition("5.4.1", showRecursion);
            if(isExpanded("6"))
                setPosition("6.1", showRecursion);
            doingChildren = true;
            if(!isExpanded("6") || !showRecursion)
            {
                drawRectangles = false;
                leftChild.runQuickSort(false);
            } else
            {
                drawRectangles = true;
                leftChild.runQuickSort(true);
            }
            leftChild.processed = true;
            if(!isExpanded("6"))
                setPosition("6.1", showRecursion);
            if(isExpanded("7"))
                setPosition("7.1", showRecursion);
            if(!isExpanded("7") || !showRecursion)
            {
                drawRectangles = false;
                rightChild.runQuickSort(false);
            } else
            {
                drawRectangles = true;
                rightChild.runQuickSort(true);
            }
            rightChild.processed = true;
            if(!isExpanded("7"))
                setPosition("7.1", showRecursion);
            doingChildren = false;
            processed = true;
        }
        drawRectangles = false;
        processed = true;
    }

    public void setPartitionMethod(int partitionMethod)
    {
        currentPartitionMethod = partitionMethod;
    }

    public void setPosition(String key, boolean isShowRecursion)
    {
        if(isShowRecursion)
            setPosition(key);
    }

    protected void swapValues(int v, int w)
    {
        if(v == PPosition)
            PPosition = w;
        if(w == PPosition)
            PPosition = v;
        numberOfSwaps++;
        int temp = data[v];
        data[v] = data[w];
        data[w] = temp;
        String tempLabel = duplicateLabels[v];
        duplicateLabels[v] = duplicateLabels[w];
        duplicateLabels[w] = tempLabel;
        if(v != w)
            swapRequests.addElement(new SwapRequest(w, v));
    }

    protected static String duplicateLabels[];
    public static final String I_MARKER = "i";
    public static final String J_MARKER = "J";
    protected static final String selectionLabel = Messages.getString("QuickSort.2");
    protected static final String highlightLabel = Messages.getString("QuickSort.3");
    protected static final String ACTIVE = Messages.getString("QuickSort.4");
    protected static final String PROCESSED = Messages.getString("QuickSort.5");
    protected static final String FINISHED = Messages.getString("QuickSort.6");
    protected static final String WAITING = Messages.getString("QuickSort.7");
    protected static final String PARTITION = Messages.getString("QuickSort.8");
    protected static Color selectionColor;
    protected static Color BACKGROUND;
    protected static Color TEXT_COLOR;
    public static Color PARTITION_COLOR;
    public static Color WAITING_COLOR;
    public static Color HIGHLIGHT_COLOR;
    public static Color DOING_CHILDREN_COLOR;
    public static Color ACTIVE_COLOR;
    public static Color FINISHED_COLOR;
    public static final int MARKER_NOT_READY = -10;
    public static final int RIGHT_PARTITION = 10;
    public static final int RANDOM_PARTITION = 11;
    public static final int MIDDLE_OF_THREE_RANDOM = 12;
    public static final int MIDDLE_OF_THREE = 13;
    protected static final boolean DEBUG_MODE = false;
    protected static int currentPartitionMethod = 10;
    protected static final long aSeed = 0xbc614eL;
    protected static Vector swapRequests = new Vector();
    protected static Vector questions = new Vector();
    protected static MyElementArrayQS elementArray;
    protected static Hashtable highlightedElements = new Hashtable();
    protected static boolean needtoresetSeed = false;
    protected static double percentageChanceForQuestion = 0.25D;
    protected static double percentageChanceForSwapQuestion = 0.5D;
    protected int columSpacing;
    protected int columWidth;
    protected int xTextBuffer;
    protected int yTextBuffer;
    protected int xBoxPadding;
    protected int yBoxPadding;
    protected Random random;
    protected int data[];
    protected int left;
    protected int right;
    protected int P;
    protected int PPosition;
    protected int i;
    protected int j;
    protected int numberOfComparisions;
    protected int numberOfSwaps;
    protected QuickSort leftChild;
    protected QuickSort rightChild;
    protected boolean started;
    protected boolean active;
    protected boolean processed;
    protected boolean doingChildren;
    private boolean drawRectangles;

    static 
    {
        selectionColor = Color.yellow;
        BACKGROUND = Color.white;
        TEXT_COLOR = Color.black;
        PARTITION_COLOR = Color.yellow;
        WAITING_COLOR = Color.pink;
        HIGHLIGHT_COLOR = Color.green;
        DOING_CHILDREN_COLOR = Color.red.darker();
        ACTIVE_COLOR = Color.red;
        FINISHED_COLOR = Color.gray;
    }
}
