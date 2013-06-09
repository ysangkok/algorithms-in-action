import localization.*;
import java.util.*;
import java.awt.*;
import com.cim.AIA.*;

public class QuickSort extends Algorithm implements ColorSelectionListener
{
    protected static String[] duplicateLabels;
    public static final String I_MARKER = "i";
    public static final String J_MARKER = "J";
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
    public static final int MARKER_NOT_READY = -10;
    public static final int RIGHT_PARTITION = 10;
    public static final int RANDOM_PARTITION = 11;
    public static final int MIDDLE_OF_THREE_RANDOM = 12;
    public static final int MIDDLE_OF_THREE = 13;
    protected static final boolean DEBUG_MODE = 0;
    protected static int currentPartitionMethod;
    protected static final long aSeed = 12345678L;
    protected static Vector<SwapRequest> swapRequests;
    protected static Vector<IAndJPositionQuestionQS> questions;
    protected static MyElementArrayQS elementArray;
    protected static Hashtable<Integer, Boolean> highlightedElements;
    protected static boolean needtoresetSeed;
    protected static double percentageChanceForQuestion;
    protected static double percentageChanceForSwapQuestion;
    protected int columSpacing;
    protected int columWidth;
    protected int xTextBuffer;
    protected int yTextBuffer;
    protected int xBoxPadding;
    protected int yBoxPadding;
    protected Random random;
    protected int[] data;
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
    
    public QuickSort(final AlgorithmThread algorithmThread, final int[] data, final int left, final int right) {
        super(algorithmThread, data);
        this.columSpacing = 12;
        this.columWidth = 7;
        this.xTextBuffer = 20;
        this.yTextBuffer = 2;
        this.xBoxPadding = 4;
        this.yBoxPadding = 2;
        this.random = new Random(12345678L);
        this.P = -10;
        this.PPosition = -10;
        this.i = -10;
        this.j = -10;
        this.numberOfComparisions = 0;
        this.numberOfSwaps = 0;
        this.started = false;
        this.active = false;
        this.processed = false;
        this.doingChildren = false;
        this.drawRectangles = false;
        this.data = data;
        this.left = left;
        this.right = right;
        this.debugMode = false;
    }
    
    public QuickSort(final AlgorithmThread algorithmThread, final Object data) {
        super(algorithmThread, data);
        this.columSpacing = 12;
        this.columWidth = 7;
        this.xTextBuffer = 20;
        this.yTextBuffer = 2;
        this.xBoxPadding = 4;
        this.yBoxPadding = 2;
        this.random = new Random(12345678L);
        this.P = -10;
        this.PPosition = -10;
        this.i = -10;
        this.j = -10;
        this.numberOfComparisions = 0;
        this.numberOfSwaps = 0;
        this.started = false;
        this.active = false;
        this.processed = false;
        this.doingChildren = false;
        this.drawRectangles = false;
        this.data = (int[])((int[])data);
        final ConfigurationManager cm = ConfigurationManager.getConfigurationManager();
        final ColorSelection partitionSelection = new ColorSelection(QuickSort.PARTITION_COLOR, QuickSort.PARTITION);
        partitionSelection.addClass(this);
        cm.addColorSelection(partitionSelection);
        cm.addColorSelectionListener(this);
        final ColorSelection activeSelection = new ColorSelection(QuickSort.ACTIVE_COLOR, QuickSort.ACTIVE);
        activeSelection.addClass(this);
        cm.addColorSelection(activeSelection);
        final ColorSelection processedSelection = new ColorSelection(QuickSort.DOING_CHILDREN_COLOR, QuickSort.PROCESSED);
        processedSelection.addClass(this);
        cm.addColorSelection(processedSelection);
        final ColorSelection finishedSelection = new ColorSelection(QuickSort.FINISHED_COLOR, QuickSort.FINISHED);
        finishedSelection.addClass(this);
        cm.addColorSelection(finishedSelection);
        final ColorSelection waitingSelection = new ColorSelection(QuickSort.WAITING_COLOR, QuickSort.WAITING);
        waitingSelection.addClass(this);
        cm.addColorSelection(waitingSelection);
        final ColorSelection highlightSelection = new ColorSelection(QuickSort.HIGHLIGHT_COLOR, QuickSort.highlightLabel);
        highlightSelection.addClass(this);
        cm.addColorSelection(highlightSelection);
        cm.addColorSelectionListener(this);
        final ColorSelection selectionSelection = new ColorSelection(QuickSort.selectionColor, QuickSort.selectionLabel);
        selectionSelection.addClass(this);
        cm.addColorSelection(selectionSelection);
        cm.addColorSelectionListener(this);
        this.initialise(true);
    }
    
    protected void addHighlight(final int id) {
        QuickSort.highlightedElements.put(new Integer(id), new Boolean(true));
    }
    
    protected void calculateColors(final Color[] colors, final int[] depthArray, final int depth) {
        for (int i = this.left; i <= this.right; ++i) {
            if (i >= 0 && i < colors.length) {
                if (colors[i] == null) {
                    colors[i] = this.getColor();
                    if (i == this.PPosition && !this.isDoingChildren() && !this.isFinished()) {
                        colors[i] = QuickSort.PARTITION_COLOR;
                    }
                    if (i >= 0 && i < depthArray.length) {
                        depthArray[i] = depth;
                    }
                }
                else {
                    if (i >= 0 && i < depthArray.length && depth > depthArray[i]) {
                        colors[i] = this.getColor();
                        depthArray[i] = depth;
                    }
                    if (i == this.PPosition && !this.isDoingChildren() && !this.isFinished()) {
                        colors[i] = QuickSort.PARTITION_COLOR;
                    }
                }
            }
        }
        if (this.leftChild != null) {
            this.leftChild.calculateColors(colors, depthArray, depth + 1);
        }
        if (this.rightChild != null) {
            this.rightChild.calculateColors(colors, depthArray, depth + 1);
        }
    }
    
    protected void createDuplicateLabels(final int[] data) {
        final Integer[] temp = new Integer[data.length];
        for (int i = 0; i < data.length; ++i) {
            temp[i] = new Integer(data[i]);
        }
        QuickSort.duplicateLabels = DuplicateLabel.createDuplicateLabels(temp);
    }
    
    public void display() {
        System.out.print(Messages.getString("QuickSort.11") + this.left + Messages.getString("QuickSort.12") + this.right + " || ");
        for (int i = 0; i < this.data.length; ++i) {
            System.out.print(this.data[i] + " ");
        }
        System.out.println("");
    }
    
    public boolean doDrawRectangles() {
        return this.drawRectangles;
    }
    
    protected void draw(final Graphics g, final Point pos) {
        g.setColor(this.getColor());
        g.fillRect(this.getLeft() * (this.columWidth + this.columSpacing) + pos.x - this.columWidth / 2, pos.y - g.getFontMetrics().getHeight(), (1 + this.getRight() - this.getLeft()) * (this.columWidth + this.columSpacing), g.getFontMetrics().getHeight());
        g.setColor(QuickSort.TEXT_COLOR);
        for (int i = this.getLeft(); i <= this.getRight(); ++i) {
            g.drawString(this.data[i] + "", i * (this.columWidth + this.columSpacing) + pos.x + this.columWidth / 2 - g.getFontMetrics().stringWidth(this.data[i] + "") / 2, pos.y);
        }
        if (this.isActive() && !this.isDoingChildren() && !this.isFinished()) {
            final int iMarker = this.getRight() * (this.columWidth + this.columSpacing) + pos.x + this.columWidth / 2;
            final int jMarker = this.getLeft() * (this.columWidth + this.columSpacing) + pos.x + this.columWidth / 2;
            if (this.getI() != -10) {
                g.setColor(QuickSort.TEXT_COLOR);
                g.drawString("i", this.getI() * (this.columWidth + this.columSpacing) + pos.x + this.columWidth / 2 - g.getFontMetrics().stringWidth("i") / 2, pos.y + g.getFontMetrics().getHeight() + this.yTextBuffer);
            }
            if (this.getJ() != -10) {
                g.setColor(QuickSort.TEXT_COLOR);
                g.drawString("J", this.getJ() * (this.columWidth + this.columSpacing) + pos.x + this.columWidth / 2 - g.getFontMetrics().stringWidth("J") / 2, pos.y + 2 * this.yTextBuffer + 2 * g.getFontMetrics().getHeight());
            }
        }
        if (this.getPPosition() != -10) {
            g.setColor(Color.black);
            g.drawRect(this.getPPosition() * (this.columWidth + this.columSpacing) + pos.x - this.xBoxPadding, pos.y - this.yBoxPadding - g.getFontMetrics().getHeight(), this.columWidth + 2 * this.xBoxPadding, g.getFontMetrics().getHeight() + 2 * this.yBoxPadding);
        }
        if (this.getLeftChild() != null) {
            this.getLeftChild().draw(g, new Point(pos.x, pos.y + 2 * g.getFontMetrics().getHeight() + 2 * this.yTextBuffer));
        }
        if (this.getRightChild() != null) {
            this.getRightChild().draw(g, new Point(pos.x, pos.y + 2 * g.getFontMetrics().getHeight() + 2 * this.yTextBuffer));
        }
    }
    
    public Vector<IAndJPositionQuestionQS> generateQuestions() {
        final Vector<IAndJPositionQuestionQS> temp = new Vector();
        for (int i = 0; i < QuickSort.questions.size(); ++i) {
            temp.addElement(QuickSort.questions.elementAt(i));
        }
        return temp;
    }
    
    protected void generateTweens(final MultipleTween tweens, final MyElementArrayQS elementArray, final int numberOfSteps) {
        for (int i = 0; i < QuickSort.swapRequests.size(); ++i) {
            final SwapRequest swapRequest = (SwapRequest)QuickSort.swapRequests.elementAt(i);
            final VerticalBar first = (VerticalBar)((VerticalBar)elementArray.getElement(swapRequest.getFirstId()));
            final VerticalBar second = (VerticalBar)((VerticalBar)elementArray.getElement(swapRequest.getSecondId()));
            tweens.add(new MoveTween(null, first, second.getPosition(), first.getPosition(), false, numberOfSteps));
            tweens.add(new MoveTween(null, second, first.getPosition(), second.getPosition(), false, numberOfSteps));
        }
        if (this.leftChild != null) {
            this.leftChild.generateTweens(tweens, elementArray, numberOfSteps);
        }
        if (this.rightChild != null) {
            this.rightChild.generateTweens(tweens, elementArray, numberOfSteps);
        }
    }
    
    public MultipleTween generateTweens(final Paintable paintable, final Object parameter, final int numberOfSteps) {
        final MultipleTween tweens = new MultipleTween(paintable);
        final MyElementArrayQS elementArray = (MyElementArrayQS)((MyElementArrayQS)parameter);
        this.generateTweens(tweens, elementArray, numberOfSteps);
        return tweens;
    }
    
    public QuickSort getActive() {
        if (this.isActive() && !this.isDoingChildren()) {
            return this;
        }
        QuickSort temp = null;
        if (this.leftChild != null) {
            temp = this.leftChild.getActive();
        }
        if (temp != null) {
            return temp;
        }
        if (this.rightChild != null) {
            temp = this.rightChild.getActive();
        }
        if (temp != null) {
            return temp;
        }
        return null;
    }
    
    public String getClassName() {
        return Messages.getString("QuickSort.10");
    }
    
    protected Color getColor() {
        Color color;
        if (this.isFinished()) {
            color = QuickSort.FINISHED_COLOR;
        }
        else if (this.isDoingChildren()) {
            color = QuickSort.DOING_CHILDREN_COLOR;
        }
        else if (this.isActive()) {
            color = QuickSort.ACTIVE_COLOR;
        }
        else {
            color = QuickSort.WAITING_COLOR;
        }
        return color;
    }
    
    public int[] getData() {
        return this.data;
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
    
    public MyElementArrayQS getElementArray(final Point location) {
        QuickSort.elementArray = new MyElementArrayQS(location.x, location.y);
        QuickSort.elementArray.setColumGap(this.columSpacing);
        QuickSort.elementArray.setElementWidth(this.columWidth);
        final Color[] colors = new Color[this.data.length];
        final int[] depthArray = new int[this.data.length];
        this.calculateColors(colors, depthArray, 0);
        for (int i = 0; i < this.data.length; ++i) {
            if (this.isHighlighted(i)) {
                final VerticalBar verticalBar = new VerticalBar(i, this.data[i], colors[i], QuickSort.HIGHLIGHT_COLOR, new Point(0, 0));
                verticalBar.setTextColor(QuickSort.TEXT_COLOR);
                verticalBar.setHighlightColor(QuickSort.selectionColor);
                QuickSort.elementArray.setValue(i, verticalBar);
            }
            else {
                final VerticalBar verticalBar = new VerticalBar(i, this.data[i], colors[i], new Point(0, 0));
                verticalBar.setTextColor(QuickSort.TEXT_COLOR);
                verticalBar.setHighlightColor(QuickSort.selectionColor);
                QuickSort.elementArray.setValue(i, verticalBar);
            }
            ((VerticalBar)((VerticalBar)QuickSort.elementArray.getElement(i))).setMarkersAboveValue(false);
            ((VerticalBar)((VerticalBar)QuickSort.elementArray.getElement(i))).setOffsetForValue(1);
            if (QuickSort.duplicateLabels[i] != null) {
                final StringMarker stringMarker = new StringMarker("" + this.data[i] + QuickSort.duplicateLabels[i]);
                stringMarker.setColor(QuickSort.TEXT_COLOR);
                stringMarker.setBackgroundColor(QuickSort.BACKGROUND);
                QuickSort.elementArray.getElement(i).addMarker(stringMarker);
            }
        }
        return QuickSort.elementArray;
    }
    
    public int getHeight(final Graphics g) {
        return this.getDepth(1) * (2 * (g.getFontMetrics().getHeight() + this.yTextBuffer));
    }
    
    public int getI() {
        return this.i;
    }
    
    public int getJ() {
        return this.j;
    }
    
    public int getLeft() {
        return this.left;
    }
    
    public QuickSort getLeftChild() {
        return this.leftChild;
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
    
    public int getPPosition() {
        return this.PPosition;
    }
    
    public int getPValue() {
        return this.P;
    }
    
    public int getRight() {
        return this.right;
    }
    
    public QuickSort getRightChild() {
        return this.rightChild;
    }
    
    public int getWidth(final Graphics g) {
        return (1 + this.getRight() - this.getLeft()) * (this.columWidth + this.columSpacing);
    }
    
    protected boolean hasQuestions() {
        return QuickSort.questions.size() >= 1;
    }
    
    public boolean haveStarted() {
        return this.started;
    }
    
    protected void initialise(final boolean newSearch) {
        this.debugMode = false;
        this.active = false;
        this.processed = false;
        this.leftChild = null;
        this.rightChild = null;
        this.P = -10;
        this.PPosition = -10;
        this.i = -10;
        this.j = -10;
        this.numberOfComparisions = 0;
        this.numberOfSwaps = 0;
        this.drawRectangles = false;
        if (newSearch) {
            this.left = 0;
            this.right = this.data.length - 1;
            this.createDuplicateLabels(this.data);
        }
    }
    
    public boolean isActive() {
        return !this.isFinished() && this.active;
    }
    
    public boolean isDoingChildren() {
        return this.isActive() && this.doingChildren;
    }
    
    public boolean isExpanded(final String key) {
        return ((QuickSortThread)this.algorithmThread).getWindow().isExpanded(key);
    }
    
    public boolean isFinished() {
        return this.processed;
    }
    
    protected boolean isHighlighted(final int id) {
        return QuickSort.highlightedElements.get(new Integer(id)) != null;
    }
    
    protected void makeQuestion() {
        if (QuickSort.elementArray != null) {
            final double tempDouble = this.random.nextDouble();
            if (tempDouble > QuickSort.percentageChanceForQuestion) {
                return;
            }
            boolean doIAndJEndUp = true;
            if (this.random.nextDouble() <= QuickSort.percentageChanceForSwapQuestion) {
                doIAndJEndUp = false;
            }
            int iSwapPosition = this.i;
            int jSwapPosition = this.j;
            int[] data;
            do {
                data = this.data;
                ++iSwapPosition;
            }
            while (data[iSwapPosition] <= this.P && iSwapPosition != this.right);
            int[] data2;
            do {
                data2 = this.data;
                --jSwapPosition;
            }
            while (data2[jSwapPosition] >= this.P && jSwapPosition != this.left);
            if (iSwapPosition >= jSwapPosition && !doIAndJEndUp) {
                jSwapPosition = this.right;
            }
            final IAndJPositionQuestionQS question = new IAndJPositionQuestionQS();
            if (doIAndJEndUp) {
                question.setInstructions(IAndJPositionQuestionQS.QUESTION_END_UP_LABEL);
            }
            else {
                question.setInstructions(IAndJPositionQuestionQS.QUESTION_SWAP_LABEL);
            }
            doIAndJEndUp = !doIAndJEndUp;
            question.addAnswer(new Integer(iSwapPosition));
            question.addAnswer(new Integer(jSwapPosition));
            QuickSort.questions.addElement(question);
        }
        else {
            System.out.println("Element Array is null, not adding question");
        }
    }
    
    public void processColorSelection(final ColorSelection colorSelection) {
        final String atribute = colorSelection.getAtributeName();
        if (atribute.equalsIgnoreCase(ColorSelection.BACKGROUND)) {
            QuickSort.BACKGROUND = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR)) {
            QuickSort.TEXT_COLOR = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(QuickSort.PARTITION)) {
            QuickSort.PARTITION_COLOR = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(QuickSort.ACTIVE)) {
            QuickSort.ACTIVE_COLOR = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(QuickSort.PROCESSED)) {
            QuickSort.DOING_CHILDREN_COLOR = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(QuickSort.FINISHED)) {
            QuickSort.FINISHED_COLOR = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(QuickSort.WAITING)) {
            QuickSort.WAITING_COLOR = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(QuickSort.highlightLabel)) {
            QuickSort.HIGHLIGHT_COLOR = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(QuickSort.selectionLabel)) {
            QuickSort.selectionColor = colorSelection.getColor();
        }
    }
    
    public void reInitialise(final Object data) {
        this.data = (int[])((int[])data);
        this.initialise(true);
        this.setPosition("");
    }
    
    protected void removeAllAnimationRequests() {
        QuickSort.swapRequests.removeAllElements();
    }
    
    protected void removeAllHighlight() {
        QuickSort.highlightedElements.clear();
    }
    
    protected void removeAllQuestions() {
        QuickSort.questions.removeAllElements();
    }
    
    public void resetRandomSeed() {
        QuickSort.needtoresetSeed = true;
    }
    
    public void run() {
        if (QuickSort.needtoresetSeed) {
            this.random.setSeed(12345678L);
            QuickSort.needtoresetSeed = false;
        }
        this.runQuickSort(true);
    }
    
    public void runQuickSort(final boolean showRecursion) {
        this.started = true;
        if (!this.enabled) {
            this.processed = true;
            return;
        }
        this.active = true;
        this.setPosition("1", showRecursion);
        this.setPosition("3", showRecursion);
        if (this.right > this.left) {
            if (this.isExpanded("4")) {
                this.drawRectangles = true;
            }
            switch (QuickSort.currentPartitionMethod) {
                case 10: {
                    this.PPosition = this.right;
                    this.P = this.data[this.PPosition];
                    this.setPosition("4.1", showRecursion);
                    break;
                }
                case 11: {
                    this.PPosition = (int)(this.random.nextDouble() * (double)(this.right - this.left) + (double)this.left);
                    this.P = this.data[this.PPosition];
                    this.setPosition("4.1", showRecursion);
                    this.swapValues(this.PPosition, this.right);
                    this.PPosition = this.right;
                    this.setPosition("4.2", showRecursion);
                    break;
                }
                case 12: {
                    final int one = (int)(this.random.nextDouble() * (double)(this.right - this.left) + (double)this.left);
                    final int two = (int)(this.random.nextDouble() * (double)(this.right - this.left) + (double)this.left);
                    final int three = (int)(this.random.nextDouble() * (double)(this.right - this.left) + (double)this.left);
                    int[] tempNumbers;
                    int j;
                    for (tempNumbers = new int[] { one, two, three }, i = 0; i < tempNumbers.length; ++i) {
                        final int i;
                        j = i;
                        while (j < tempNumbers.length) {
                            if (tempNumbers[j] < tempNumbers[i]) {
                                final int temp = tempNumbers[i];
                                tempNumbers[i] = tempNumbers[j];
                                tempNumbers[j] = temp;
                            }
                            ++j;
                        }
                    }
                    this.addHighlight(tempNumbers[0]);
                    this.setPosition("4.1", showRecursion);
                    this.addHighlight(tempNumbers[1]);
                    this.setPosition("4.2", showRecursion);
                    this.addHighlight(tempNumbers[2]);
                    this.setPosition("4.3", showRecursion);
                    for (int i = 0; i < tempNumbers.length; ++i) {
                        j = i;
                        while (j < tempNumbers.length) {
                            if (this.data[tempNumbers[j]] < this.data[tempNumbers[i]]) {
                                this.swapValues(tempNumbers[i], tempNumbers[j]);
                            }
                            ++j;
                        }
                    }
                    this.setPosition("4.4", showRecursion);
                    this.removeAllHighlight();
                    this.addHighlight(tempNumbers[1]);
                    this.PPosition = tempNumbers[1];
                    this.P = this.data[this.PPosition];
                    this.setPosition("4.5", showRecursion);
                    this.removeAllHighlight();
                    this.swapValues(this.PPosition, this.right);
                    this.PPosition = this.right;
                    this.setPosition("4.6", showRecursion);
                    break;
                }
                case 13: {
                    final int one2 = this.left;
                    final int two2 = (this.right + this.left) / 2;
                    final int three2 = this.right;
                    final int[] tempNumbers2 = new int[] { one2, two2, three2 };
                    this.addHighlight(tempNumbers2[0]);
                    this.setPosition("4.1", showRecursion);
                    this.addHighlight(tempNumbers2[1]);
                    this.setPosition("4.2", showRecursion);
                    this.addHighlight(tempNumbers2[2]);
                    this.setPosition("4.3", showRecursion);
                    for (int k = 0; k < tempNumbers2.length; ++k) {
                        int l = k;
                        while (l < tempNumbers2.length) {
                            if (this.data[tempNumbers2[l]] < this.data[tempNumbers2[k]]) {
                                this.swapValues(tempNumbers2[k], tempNumbers2[l]);
                            }
                            ++l;
                        }
                    }
                    this.setPosition("4.4", showRecursion);
                    this.removeAllHighlight();
                    this.addHighlight(tempNumbers2[1]);
                    this.PPosition = tempNumbers2[1];
                    this.P = this.data[this.PPosition];
                    this.setPosition("4.5", showRecursion);
                    this.removeAllHighlight();
                    this.swapValues(this.PPosition, this.right);
                    this.PPosition = this.right;
                    this.setPosition("4.6", showRecursion);
                    break;
                }
            }
            this.drawRectangles = false;
            if (this.isExpanded("5")) {
                this.drawRectangles = true;
            }
            this.i = this.left - 1;
            this.setPosition("5.1.1", showRecursion);
            this.j = this.right;
            this.setPosition("5.1.2", showRecursion);
            this.makeQuestion();
            this.setPosition("5.2", showRecursion);
            while (true) {
                this.i = this.i + 1;
                this.setPosition("5.2.1.1", showRecursion);
                while (this.data[this.i] < this.P) {
                    this.i = this.i + 1;
                    if (this.i == this.right) {
                        break;
                    }
                    this.numberOfComparisions = this.numberOfComparisions + 1;
                    this.setPosition("5.2.1.1", showRecursion);
                }
                this.numberOfComparisions = this.numberOfComparisions + 1;
                this.j = this.j - 1;
                this.setPosition("5.2.2.1", showRecursion);
                while (this.data[this.j] > this.P) {
                    this.j = this.j - 1;
                    if (this.j == this.left - 1) {
                        break;
                    }
                    this.numberOfComparisions = this.numberOfComparisions + 1;
                    this.setPosition("5.2.2.1", showRecursion);
                }
                this.setPosition("5.2.3.1", showRecursion);
                if (this.i >= this.j) {
                    break;
                }
                this.swapValues(this.i, this.j);
                this.setPosition("5.2.5.1", showRecursion);
                this.makeQuestion();
                this.setPosition("5.2", showRecursion);
            }
            this.swapValues(this.i, this.right);
            this.leftChild = new QuickSort(this.algorithmThread, this.data, this.left, this.i - 1);
            this.rightChild = new QuickSort(this.algorithmThread, this.data, this.i + 1, this.right);
            this.setPosition("5.4.1", showRecursion);
            if (this.isExpanded("6")) {
                this.setPosition("6.1", showRecursion);
            }
            this.doingChildren = true;
            if (!this.isExpanded("6") || !showRecursion) {
                this.drawRectangles = false;
                this.leftChild.runQuickSort(false);
            }
            else {
                this.drawRectangles = true;
                this.leftChild.runQuickSort(true);
            }
            this.leftChild.processed = true;
            if (!this.isExpanded("6")) {
                this.setPosition("6.1", showRecursion);
            }
            if (this.isExpanded("7")) {
                this.setPosition("7.1", showRecursion);
            }
            if (!this.isExpanded("7") || !showRecursion) {
                this.drawRectangles = false;
                this.rightChild.runQuickSort(false);
            }
            else {
                this.drawRectangles = true;
                this.rightChild.runQuickSort(true);
            }
            this.rightChild.processed = true;
            if (!this.isExpanded("7")) {
                this.setPosition("7.1", showRecursion);
            }
            this.doingChildren = false;
            this.processed = true;
        }
        this.drawRectangles = false;
        this.processed = true;
    }
    
    public void setPartitionMethod(final int partitionMethod) {
        QuickSort.currentPartitionMethod = partitionMethod;
    }
    
    public void setPosition(final String key, final boolean isShowRecursion) {
        if (isShowRecursion) {
            this.setPosition(key);
        }
    }
    
    protected void swapValues(final int v, final int w) {
        if (v == this.PPosition) {
            this.PPosition = w;
        }
        if (w == this.PPosition) {
            this.PPosition = v;
        }
        this.numberOfSwaps = this.numberOfSwaps + 1;
        final int temp = this.data[v];
        this.data[v] = this.data[w];
        this.data[w] = temp;
        final String tempLabel = QuickSort.duplicateLabels[v];
        QuickSort.duplicateLabels[v] = QuickSort.duplicateLabels[w];
        QuickSort.duplicateLabels[w] = tempLabel;
        if (v != w) {
            QuickSort.swapRequests.addElement(new SwapRequest(w, v));
        }
    }
    
    static {
        selectionLabel = Messages.getString("QuickSort.2");
        highlightLabel = Messages.getString("QuickSort.3");
        ACTIVE = Messages.getString("QuickSort.4");
        PROCESSED = Messages.getString("QuickSort.5");
        FINISHED = Messages.getString("QuickSort.6");
        WAITING = Messages.getString("QuickSort.7");
        PARTITION = Messages.getString("QuickSort.8");
        QuickSort.selectionColor = Color.yellow;
        QuickSort.BACKGROUND = Color.white;
        QuickSort.TEXT_COLOR = Color.black;
        QuickSort.PARTITION_COLOR = Color.yellow;
        QuickSort.WAITING_COLOR = Color.pink;
        QuickSort.HIGHLIGHT_COLOR = Color.green;
        QuickSort.DOING_CHILDREN_COLOR = Color.red.darker();
        QuickSort.ACTIVE_COLOR = Color.red;
        QuickSort.FINISHED_COLOR = Color.gray;
        QuickSort.currentPartitionMethod = 10;
        QuickSort.swapRequests = new Vector();
        QuickSort.questions = new Vector();
        QuickSort.highlightedElements = new Hashtable();
        QuickSort.needtoresetSeed = false;
        QuickSort.percentageChanceForQuestion = 0.25;
        QuickSort.percentageChanceForSwapQuestion = 0.5;
    }
}
