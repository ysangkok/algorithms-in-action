// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HeapSort.java

import com.cim.AIA.*;
import java.awt.Color;
import java.awt.Point;
import java.util.Vector;
import localization.Messages;

public class HeapSort extends Algorithm
    implements ColorSelectionListener
{

    public HeapSort(AlgorithmThread algorithmThread, Object data)
    {
        super(algorithmThread, data);
        doneColor = Color.gray;
        activeColor = CodeCanvas.DEFAULT_HIGHLIGHT_COLOR;
        background = Color.white;
        textColor = Color.black;
        highlightColor = Color.yellow;
        finishedLabel = Messages.getString("HeapSort.0");
        activeLabel = Messages.getString("HeapSort.1");
        highlightLabel = Messages.getString("HeapSort.2");
        columSpacing = 12;
        columWidth = 7;
        N = -10;
        this.i = -10;
        j = -10;
        k = -10;
        swapRequests = new Vector();
        questions = new Vector();
        int temp[] = (int[])(int[])data;
        elements = new int[temp.length + 1];
        System.arraycopy(temp, 0, elements, 1, temp.length);
        colors = new Color[elements.length];
        for(int i = 0; i < colors.length; i++)
            colors[i] = activeColor;

        createDuplicateLabels(elements);
        ConfigurationManager cm = ConfigurationManager.getConfigurationManager();
        ColorSelection activeSelection = new ColorSelection(activeColor, activeLabel);
        activeSelection.addClass(this);
        cm.addColorSelection(activeSelection);
        ColorSelection finishedSelection = new ColorSelection(doneColor, finishedLabel);
        finishedSelection.addClass(this);
        cm.addColorSelection(finishedSelection);
        ColorSelection highlightSelection = new ColorSelection(highlightColor, highlightLabel);
        highlightSelection.addClass(this);
        cm.addColorSelection(highlightSelection);
        cm.addColorSelectionListener(this);
    }

    protected void createDuplicateLabels(int data[])
    {
        Integer temp[] = new Integer[data.length];
        for(int i = 0; i < data.length; i++)
            temp[i] = new Integer(data[i]);

        duplicateLabels = DuplicateLabel.createDuplicateLabels(temp);
    }

    public Vector generateQuestions()
    {
        Vector tempQuestions = new Vector();
        for(int i = 0; i < questions.size(); i++)
            tempQuestions.addElement(questions.elementAt(i));

        return tempQuestions;
    }

    protected void generateTweens(MultipleTween tweens, ElementArray elementArray, int numberOfSteps)
    {
        for(int i = 0; i < swapRequests.size(); i++)
        {
            SwapRequest swapRequest = (SwapRequest)swapRequests.elementAt(i);
            Element first = elementArray.getElement(swapRequest.getFirstId());
            Element second = elementArray.getElement(swapRequest.getSecondId());
            tweens.add(new MoveTween(null, first, second.getPosition(), first.getPosition(), false, numberOfSteps));
            tweens.add(new MoveTween(null, second, first.getPosition(), second.getPosition(), false, numberOfSteps));
        }

    }

    public MultipleTween generateTweens(Paintable paintable, Object parameter, int numberOfSteps)
    {
        MultipleTween tweens = new MultipleTween(paintable);
        ElementArray elementArray = (ElementArray)(ElementArray)parameter;
        generateTweens(tweens, elementArray, numberOfSteps);
        return tweens;
    }

    public String getClassName()
    {
        return Messages.getString("HeapSort.3");
    }

    public ElementArray getElementArray(Point location)
    {
        elementArray = new ElementArray(location.x, location.y);
        elementArray.setColumGap(columSpacing);
        elementArray.setElementWidth(columWidth);
        for(int i = 1; i < elements.length; i++)
        {
            VerticalBar verticalBar = new VerticalBar(i - 1, elements[i], colors[i], new Point(0, 0));
            verticalBar.setTextColor(textColor);
            verticalBar.setHighlightColor(highlightColor);
            elementArray.setValue(i - 1, verticalBar);
            ((VerticalBar)(VerticalBar)elementArray.getElement(i - 1)).setMarkersAboveValue(false);
            ((VerticalBar)(VerticalBar)elementArray.getElement(i - 1)).setOffsetForValue(1);
            if(duplicateLabels[i] != null)
            {
                StringMarker stringMarker = new StringMarker((new StringBuilder()).append("").append(elements[i]).append(duplicateLabels[i]).toString());
                stringMarker.setBackgroundColor(background);
                stringMarker.setColor(textColor);
                elementArray.getElement(i - 1).addMarker(stringMarker);
            }
        }

        return elementArray;
    }

    public HierarchyTree getHierarchyTree()
    {
        nodes = new Node[elements.length];
        HierarchyTree tempTree[] = new HierarchyTree[elements.length];
        HierarchyTree tree = null;
        for(int i = 1; i < elements.length / 2 + 1; i++)
        {
            if(tree == null)
            {
                Node tempNode = new Node(new Integer(elements[i]), i - 1);
                tempNode.setBackgroundColor(colors[i]);
                tempNode.setTextColor(textColor);
                tempNode.setHighlightColor(highlightColor);
                tree = new HierarchyTree(tempNode);
                tree.getLine().showArrowHead(false);
                tempTree[i] = tree;
                nodes[i] = tempNode;
            }
            int j = i * 2 + 0;
            int k = i * 2 + 1;
            int leftChild = 0;
            int rightChild = 0;
            boolean leftChildNull = true;
            boolean rightChildNull = true;
            if(j < elements.length)
            {
                leftChild = elements[j];
                leftChildNull = false;
            }
            if(k < elements.length)
            {
                rightChild = elements[k];
                rightChildNull = false;
            }
            if(!leftChildNull)
            {
                Node tempNode = new Node(new Integer(leftChild), j - 1);
                tempNode.setBackgroundColor(colors[j]);
                tempNode.setTextColor(textColor);
                tempNode.setHighlightColor(highlightColor);
                tempTree[j] = new HierarchyTree(tree, tempNode);
                tempTree[j].getLine().showArrowHead(false);
                tree.addChild(tempTree[j]);
                nodes[j] = tempNode;
                if(rightChildNull)
                {
                    Node tempMockNode = new Node(new Integer(leftChild), -1);
                    tempMockNode.setIsVisible(false);
                    HierarchyTree tempHierarchyTree = new HierarchyTree(tree, tempMockNode);
                    tree.addChild(tempHierarchyTree);
                }
            }
            if(!rightChildNull)
            {
                Node tempNode = new Node(new Integer(rightChild), k - 1);
                tempNode.setBackgroundColor(colors[k]);
                tempNode.setTextColor(textColor);
                tempNode.setHighlightColor(highlightColor);
                tempTree[k] = new HierarchyTree(tree, tempNode);
                tempTree[k].getLine().showArrowHead(false);
                tree.addChild(tempTree[k]);
                nodes[k] = tempNode;
            }
            if(i + 1 < elements.length)
                tree = tempTree[i + 1];
        }

        tree = tempTree[1];
        return tree;
    }

    public int getI()
    {
        return i - 1;
    }

    protected int getIndexOfLargestChild(int position, int N)
    {
        int largestIndex = 2 * position;
        if(largestIndex < N && elements[largestIndex] < elements[largestIndex + 1])
            largestIndex++;
        return largestIndex;
    }

    public int getJ()
    {
        return j - 1;
    }

    public int getK()
    {
        return k - 1;
    }

    public Node getNode(int index)
    {
        if(nodes == null)
            return null;
        if(index + 1 >= 0 && index + 1 < nodes.length)
            return nodes[index + 1];
        else
            return null;
    }

    public boolean hasQuestions()
    {
        return questions.size() > 0;
    }

    protected void makeJQuestion(int answerForJ)
    {
        if(elementArray != null)
        {
            IAndJPositionQuestion question = new IAndJPositionQuestion();
            question.setInstructions(IAndJPositionQuestion.QUESTION_J_LABEL);
            question.addAnswer(new Integer(answerForJ - 1));
            questions.addElement(question);
        }
    }

    protected void makeSwapQuestion(int i, int j)
    {
        if(elementArray != null)
        {
            IAndJPositionQuestion question = new IAndJPositionQuestion();
            question.setInstructions(IAndJPositionQuestion.QUESTION_SWAP_LABEL);
            question.addAnswer(new Integer(i - 1));
            question.addAnswer(new Integer(j - 1));
            questions.addElement(question);
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
        if(atribute.equalsIgnoreCase(activeLabel))
        {
            for(int i = 0; i < colors.length; i++)
                if(colors[i] == activeColor)
                    colors[i] = colorSelection.getColor();

            activeColor = colorSelection.getColor();
        } else
        if(atribute.equalsIgnoreCase(finishedLabel))
        {
            for(int i = 0; i < colors.length; i++)
                if(colors[i] == doneColor)
                    colors[i] = colorSelection.getColor();

            doneColor = colorSelection.getColor();
        } else
        if(atribute.equalsIgnoreCase(highlightLabel))
        {
            for(int i = 0; i < colors.length; i++)
                if(colors[i] == highlightColor)
                    colors[i] = colorSelection.getColor();

            highlightColor = colorSelection.getColor();
        }
    }

    public void reInitialise(Object data)
    {
        int temp[] = (int[])(int[])data;
        elements = new int[temp.length + 1];
        elementArray = null;
        System.arraycopy(temp, 0, elements, 1, temp.length);
        colors = new Color[elements.length];
        for(int i = 0; i < colors.length; i++)
            colors[i] = activeColor;

        this.i = j = k = N = -10;
        createDuplicateLabels(elements);
        setPosition("");
    }

    public void removeAllAnimationRequests()
    {
        swapRequests.removeAllElements();
    }

    public void removeAllQuestions()
    {
        questions.removeAllElements();
    }

    public void run()
    {
        if(!enabled)
            return;
        setPosition("0");
        N = elements.length - 1;
        setPosition("1");
        setPosition("1.0");
        setPosition("1.1");
        for(k = N / 2; k > 0; k--)
        {
            setPosition("1.1.1");
            setPosition("1.2");
            i = k;
            setPosition("1.2.1");
            do
            {
                if(2 * i > N)
                    break;
                makeJQuestion(getIndexOfLargestChild(i, N));
                setPosition("1.2.2");
                j = getIndexOfLargestChild(i, N);
                setPosition("1.2.1.2");
                if(elements[i] >= elements[j])
                {
                    setPosition("1.2.1.3");
                    break;
                }
                makeSwapQuestion(i, j);
                askQuestionsWithoutSetPosition();
                swap(i, j);
                setPosition("1.2.1.5");
                i = j;
                setPosition("1.2.1.6");
            } while(true);
            i = j = -10;
            setPosition("1.2.3");
        }

        k = -10;
        setPosition("1.a");
        while(N > 1) 
        {
            setPosition("3");
            setPosition("3.0");
            setPosition("3.1");
            makeSwapQuestion(1, N);
            askQuestionsWithoutSetPosition();
            swap(1, N);
            setPosition("3.1.1");
            setPosition("3.2");
            colors[N] = doneColor;
            N--;
            setPosition("3.2.1");
            setPosition("3.3");
            setPosition("3.3.1");
            i = 1;
            setPosition("3.3.1.1");
            do
            {
                if(2 * i > N)
                    break;
                makeJQuestion(getIndexOfLargestChild(i, N));
                setPosition("3.3.1.2");
                j = getIndexOfLargestChild(i, N);
                setPosition("3.3.1.2.1");
                if(elements[i] >= elements[j])
                {
                    setPosition("3.3.1.2.2");
                    break;
                }
                makeSwapQuestion(i, j);
                askQuestionsWithoutSetPosition();
                swap(i, j);
                setPosition("3.3.1.2.4");
                i = j;
                setPosition("3.3.1.2.5");
            } while(true);
            i = j = -10;
            setPosition("3.3.1.2.6");
        }
        setPosition("4");
    }

    protected void swap(int j, int i)
    {
        int temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
        Color tempColor = colors[i];
        colors[i] = colors[j];
        colors[j] = tempColor;
        String tempLabel = duplicateLabels[i];
        duplicateLabels[i] = duplicateLabels[j];
        duplicateLabels[j] = tempLabel;
        if(i != j)
            swapRequests.addElement(new SwapRequest(j - 1, i - 1));
    }

    public void unHighlightAll()
    {
        if(elementArray != null)
        {
            for(int i = 1; i < elements.length; i++)
                if(elementArray.getElement(i - 1) != null)
                    elementArray.getElement(i - 1).unHighlight();

        }
    }

    public static final String I_MARKER = "i";
    public static final String J_MARKER = "J";
    public static final String K_MARKER = "k";
    public static final int MARKER_NOT_READY = -10;
    protected Color doneColor;
    protected Color activeColor;
    protected Color background;
    protected Color textColor;
    protected Color highlightColor;
    protected String finishedLabel;
    protected String activeLabel;
    protected String highlightLabel;
    protected ElementArray elementArray;
    protected int columSpacing;
    protected int columWidth;
    protected int elements[];
    protected Color colors[];
    protected int N;
    protected int i;
    protected int j;
    protected int k;
    protected Vector swapRequests;
    protected Vector questions;
    protected String duplicateLabels[];
    protected Node nodes[];
}
