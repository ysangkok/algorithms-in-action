import localization.*;
import java.util.*;
import java.awt.*;
import com.cim.AIA.*;

public class HeapSort extends Algorithm implements ColorSelectionListener
{
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
    protected int[] elements;
    protected Color[] colors;
    protected int N;
    protected int i;
    protected int j;
    protected int k;
    protected Vector<SwapRequest> swapRequests;
    protected Vector<IAndJPositionQuestion> questions;
    protected String[] duplicateLabels;
    protected Node[] nodes;
    
    public HeapSort(final AlgorithmThread algorithmThread, final Object data) {
        super(algorithmThread, data);
        this.doneColor = Color.gray;
        this.activeColor = CodeCanvas.DEFAULT_HIGHLIGHT_COLOR;
        this.background = Color.white;
        this.textColor = Color.black;
        this.highlightColor = Color.yellow;
        this.finishedLabel = Messages.getString("HeapSort.0");
        this.activeLabel = Messages.getString("HeapSort.1");
        this.highlightLabel = Messages.getString("HeapSort.2");
        this.columSpacing = 12;
        this.columWidth = 7;
        this.N = -10;
        this.i = -10;
        this.j = -10;
        this.k = -10;
        this.swapRequests = new Vector();
        this.questions = new Vector();
        final int[] temp = (int[])((int[])data);
        this.elements = new int[temp.length + 1];
        System.arraycopy(temp, 0, this.elements, 1, temp.length);
        this.colors = new Color[this.elements.length];
        for (int i = 0; i < this.colors.length; ++i) {
            this.colors[i] = this.activeColor;
        }
        this.createDuplicateLabels(this.elements);
        final ConfigurationManager cm = ConfigurationManager.getConfigurationManager();
        final ColorSelection activeSelection = new ColorSelection(this.activeColor, this.activeLabel);
        activeSelection.addClass(this);
        cm.addColorSelection(activeSelection);
        final ColorSelection finishedSelection = new ColorSelection(this.doneColor, this.finishedLabel);
        finishedSelection.addClass(this);
        cm.addColorSelection(finishedSelection);
        final ColorSelection highlightSelection = new ColorSelection(this.highlightColor, this.highlightLabel);
        highlightSelection.addClass(this);
        cm.addColorSelection(highlightSelection);
        cm.addColorSelectionListener(this);
    }
    
    protected void createDuplicateLabels(final int[] data) {
        final Integer[] temp = new Integer[data.length];
        for (int i = 0; i < data.length; ++i) {
            temp[i] = new Integer(data[i]);
        }
        this.duplicateLabels = DuplicateLabel.createDuplicateLabels(temp);
    }
    
    public Vector<IAndJPositionQuestion> generateQuestions() {
        final Vector<IAndJPositionQuestion> tempQuestions = new Vector();
        for (int i = 0; i < this.questions.size(); ++i) {
            tempQuestions.addElement(this.questions.elementAt(i));
        }
        return tempQuestions;
    }
    
    protected void generateTweens(final MultipleTween tweens, final ElementArray elementArray, final int numberOfSteps) {
        for (int i = 0; i < this.swapRequests.size(); ++i) {
            final SwapRequest swapRequest = (SwapRequest)this.swapRequests.elementAt(i);
            final Element first = elementArray.getElement(swapRequest.getFirstId());
            final Element second = elementArray.getElement(swapRequest.getSecondId());
            tweens.add(new MoveTween(null, first, second.getPosition(), first.getPosition(), false, numberOfSteps));
            tweens.add(new MoveTween(null, second, first.getPosition(), second.getPosition(), false, numberOfSteps));
        }
    }
    
    public MultipleTween generateTweens(final Paintable paintable, final Object parameter, final int numberOfSteps) {
        final MultipleTween tweens = new MultipleTween(paintable);
        final ElementArray elementArray = (ElementArray)((ElementArray)parameter);
        this.generateTweens(tweens, elementArray, numberOfSteps);
        return tweens;
    }
    
    public String getClassName() {
        return Messages.getString("HeapSort.3");
    }
    
    public ElementArray getElementArray(final Point location) {
        this.elementArray = new ElementArray(location.x, location.y);
        this.elementArray.setColumGap(this.columSpacing);
        this.elementArray.setElementWidth(this.columWidth);
        for (int i = 1; i < this.elements.length; ++i) {
            final VerticalBar verticalBar = new VerticalBar(i - 1, this.elements[i], this.colors[i], new Point(0, 0));
            verticalBar.setTextColor(this.textColor);
            verticalBar.setHighlightColor(this.highlightColor);
            this.elementArray.setValue(i - 1, verticalBar);
            ((VerticalBar)((VerticalBar)this.elementArray.getElement(i - 1))).setMarkersAboveValue(false);
            ((VerticalBar)((VerticalBar)this.elementArray.getElement(i - 1))).setOffsetForValue(1);
            if (this.duplicateLabels[i] != null) {
                final StringMarker stringMarker = new StringMarker("" + this.elements[i] + this.duplicateLabels[i]);
                stringMarker.setBackgroundColor(this.background);
                stringMarker.setColor(this.textColor);
                this.elementArray.getElement(i - 1).addMarker(stringMarker);
            }
        }
        return this.elementArray;
    }
    
    public HierarchyTree getHierarchyTree() {
        this.nodes = new Node[this.elements.length];
        final HierarchyTree[] tempTree = new HierarchyTree[this.elements.length];
        HierarchyTree tree = null;
        for (int i = 1; i < this.elements.length / 2 + 1; ++i) {
            if (tree == null) {
                final Node tempNode = new Node(new Integer(this.elements[i]), i - 1);
                tempNode.setBackgroundColor(this.colors[i]);
                tempNode.setTextColor(this.textColor);
                tempNode.setHighlightColor(this.highlightColor);
                tree = new HierarchyTree(tempNode);
                tree.getLine().showArrowHead(false);
                tempTree[i] = tree;
                this.nodes[i] = tempNode;
            }
            final int j = i * 2 + 0;
            final int k = i * 2 + 1;
            int leftChild = 0;
            int rightChild = 0;
            boolean leftChildNull = true;
            boolean rightChildNull = true;
            if (j < this.elements.length) {
                leftChild = this.elements[j];
                leftChildNull = false;
            }
            if (k < this.elements.length) {
                rightChild = this.elements[k];
                rightChildNull = false;
            }
            Node tempNode2;
            if (!leftChildNull) {
                tempNode2 = new Node(new Integer(leftChild), j - 1);
                tempNode2.setBackgroundColor(this.colors[j]);
                tempNode2.setTextColor(this.textColor);
                tempNode2.setHighlightColor(this.highlightColor);
                tempTree[j] = new HierarchyTree(tree, tempNode2);
                tempTree[j].getLine().showArrowHead(false);
                tree.addChild(tempTree[j]);
                this.nodes[j] = tempNode2;
                if (rightChildNull) {
                    final Node tempMockNode = new Node(new Integer(leftChild), -1);
                    tempMockNode.setIsVisible(false);
                    final HierarchyTree tempHierarchyTree = new HierarchyTree(tree, tempMockNode);
                    tree.addChild(tempHierarchyTree);
                }
            }
            if (!rightChildNull) {
                tempNode2 = new Node(new Integer(rightChild), k - 1);
                tempNode2.setBackgroundColor(this.colors[k]);
                tempNode2.setTextColor(this.textColor);
                tempNode2.setHighlightColor(this.highlightColor);
                tempTree[k] = new HierarchyTree(tree, tempNode2);
                tempTree[k].getLine().showArrowHead(false);
                tree.addChild(tempTree[k]);
                this.nodes[k] = tempNode2;
            }
            if (i + 1 < this.elements.length) {
                tree = tempTree[i + 1];
            }
        }
        tree = tempTree[1];
        return tree;
    }
    
    public int getI() {
        return this.i - 1;
    }
    
    protected int getIndexOfLargestChild(final int position, final int N) {
        int largestIndex = 2 * position;
        if (largestIndex < N && this.elements[largestIndex] < this.elements[largestIndex + 1]) {
            ++largestIndex;
        }
        return largestIndex;
    }
    
    public int getJ() {
        return this.j - 1;
    }
    
    public int getK() {
        return this.k - 1;
    }
    
    public Node getNode(final int index) {
        if (this.nodes == null) {
            return null;
        }
        if (index + 1 >= 0 && index + 1 < this.nodes.length) {
            return this.nodes[index + 1];
        }
        return null;
    }
    
    public boolean hasQuestions() {
        return this.questions.size() > 0;
    }
    
    protected void makeJQuestion(final int answerForJ) {
        if (this.elementArray != null) {
            final IAndJPositionQuestion question = new IAndJPositionQuestion();
            question.setInstructions(IAndJPositionQuestion.QUESTION_J_LABEL);
            question.addAnswer(new Integer(answerForJ - 1));
            this.questions.addElement(question);
        }
    }
    
    protected void makeSwapQuestion(final int i, final int j) {
        if (this.elementArray != null) {
            final IAndJPositionQuestion question = new IAndJPositionQuestion();
            question.setInstructions(IAndJPositionQuestion.QUESTION_SWAP_LABEL);
            question.addAnswer(new Integer(i - 1));
            question.addAnswer(new Integer(j - 1));
            this.questions.addElement(question);
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
        else if (atribute.equalsIgnoreCase(this.activeLabel)) {
            for (int i = 0; i < this.colors.length; ++i) {
                if (this.colors[i] == this.activeColor) {
                    this.colors[i] = colorSelection.getColor();
                }
            }
            this.activeColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(this.finishedLabel)) {
            for (int i = 0; i < this.colors.length; ++i) {
                if (this.colors[i] == this.doneColor) {
                    this.colors[i] = colorSelection.getColor();
                }
            }
            this.doneColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(this.highlightLabel)) {
            for (int i = 0; i < this.colors.length; ++i) {
                if (this.colors[i] == this.highlightColor) {
                    this.colors[i] = colorSelection.getColor();
                }
            }
            this.highlightColor = colorSelection.getColor();
        }
    }
    
    public void reInitialise(final Object data) {
        final int[] temp = (int[])((int[])data);
        this.elements = new int[temp.length + 1];
        this.elementArray = null;
        System.arraycopy(temp, 0, this.elements, 1, temp.length);
        this.colors = new Color[this.elements.length];
        for (int i = 0; i < this.colors.length; ++i) {
            this.colors[i] = this.activeColor;
        }
        final int n = -10;
        this.N = n;
        this.k = n;
        this.j = n;
        this.i = n;
        this.createDuplicateLabels(this.elements);
        this.setPosition("");
    }
    
    public void removeAllAnimationRequests() {
        this.swapRequests.removeAllElements();
    }
    
    public void removeAllQuestions() {
        this.questions.removeAllElements();
    }
    
    public void run() {
        if (!this.enabled) {
            return;
        }
        this.setPosition("0");
        this.N = this.elements.length - 1;
        this.setPosition("1");
        this.setPosition("1.0");
        this.setPosition("1.1");
        this.k = this.N / 2;
        while (this.k > 0) {
            this.setPosition("1.1.1");
            this.setPosition("1.2");
            this.i = this.k;
            this.setPosition("1.2.1");
            while (2 * this.i <= this.N) {
                this.makeJQuestion(this.getIndexOfLargestChild(this.i, this.N));
                this.setPosition("1.2.2");
                this.j = this.getIndexOfLargestChild(this.i, this.N);
                this.setPosition("1.2.1.2");
                if (this.elements[this.i] >= this.elements[this.j]) {
                    this.setPosition("1.2.1.3");
                    break;
                }
                this.makeSwapQuestion(this.i, this.j);
                this.askQuestionsWithoutSetPosition();
                this.swap(this.i, this.j);
                this.setPosition("1.2.1.5");
                this.i = this.j;
                this.setPosition("1.2.1.6");
            }
            final int n = -10;
            this.j = n;
            this.i = n;
            this.setPosition("1.2.3");
            this.k = this.k - 1;
        }
        this.k = -10;
        this.setPosition("1.a");
        while (this.N > 1) {
            this.setPosition("3");
            this.setPosition("3.0");
            this.setPosition("3.1");
            this.makeSwapQuestion(1, this.N);
            this.askQuestionsWithoutSetPosition();
            this.swap(1, this.N);
            this.setPosition("3.1.1");
            this.setPosition("3.2");
            this.colors[this.N] = this.doneColor;
            this.N = this.N - 1;
            this.setPosition("3.2.1");
            this.setPosition("3.3");
            this.setPosition("3.3.1");
            this.i = 1;
            this.setPosition("3.3.1.1");
            while (2 * this.i <= this.N) {
                this.makeJQuestion(this.getIndexOfLargestChild(this.i, this.N));
                this.setPosition("3.3.1.2");
                this.j = this.getIndexOfLargestChild(this.i, this.N);
                this.setPosition("3.3.1.2.1");
                if (this.elements[this.i] >= this.elements[this.j]) {
                    this.setPosition("3.3.1.2.2");
                    break;
                }
                this.makeSwapQuestion(this.i, this.j);
                this.askQuestionsWithoutSetPosition();
                this.swap(this.i, this.j);
                this.setPosition("3.3.1.2.4");
                this.i = this.j;
                this.setPosition("3.3.1.2.5");
            }
            final int n2 = -10;
            this.j = n2;
            this.i = n2;
            this.setPosition("3.3.1.2.6");
        }
        this.setPosition("4");
    }
    
    protected void swap(final int j, final int i) {
        final int temp = this.elements[i];
        this.elements[i] = this.elements[j];
        this.elements[j] = temp;
        final Color tempColor = this.colors[i];
        this.colors[i] = this.colors[j];
        this.colors[j] = tempColor;
        final String tempLabel = this.duplicateLabels[i];
        this.duplicateLabels[i] = this.duplicateLabels[j];
        this.duplicateLabels[j] = tempLabel;
        if (i != j) {
            this.swapRequests.addElement(new SwapRequest(j - 1, i - 1));
        }
    }
    
    public void unHighlightAll() {
        if (this.elementArray != null) {
            for (int i = 1; i < this.elements.length; ++i) {
                if (this.elementArray.getElement(i - 1) != null) {
                    this.elementArray.getElement(i - 1).unHighlight();
                }
            }
        }
    }
}
