import localization.*;
import java.awt.*;
import java.util.*;
import com.cim.AIA.*;

public class SelectionSort extends Algorithm implements ColorSelectionListener
{
    public static final int MARKER_NOT_READY = -10;
    protected static final String ARRAY_COLOR;
    protected static final String FINISHED_ARRAY_COLOR;
    protected int[] data;
    protected int recordOfI;
    protected Vector<SwapRequest> swapRequests;
    protected Vector<SwapRequest> questions;
    protected String[] duplicateLabels;
    protected int i;
    protected int j;
    protected int minPosition;
    protected Color textColor;
    protected Color arrayColor;
    protected Color finishedArrayColor;
    protected Color backgroundColor;
    
    public SelectionSort(final AlgorithmThread algorithmThread, final Object data) {
        super(algorithmThread, data);
        this.recordOfI = 0;
        this.swapRequests = new Vector();
        this.questions = new Vector();
        this.i = -10;
        this.j = -10;
        this.minPosition = -10;
        this.textColor = Color.black;
        this.arrayColor = Color.blue;
        this.finishedArrayColor = Color.gray;
        this.backgroundColor = Color.white;
        this.initialise((int[])((int[])data));
        final ConfigurationManager cm = ConfigurationManager.getConfigurationManager();
        final ColorSelection arrayColorSelection = new ColorSelection(this.arrayColor, SelectionSort.ARRAY_COLOR);
        arrayColorSelection.addClass(this);
        final ColorSelection finishedArrayColorSelection = new ColorSelection(this.finishedArrayColor, SelectionSort.FINISHED_ARRAY_COLOR);
        finishedArrayColorSelection.addClass(this);
        cm.addColorSelection(arrayColorSelection);
        cm.addColorSelection(finishedArrayColorSelection);
        cm.addColorSelectionListener(this);
    }
    
    protected void createDuplicateLabels() {
        final Integer[] temp = new Integer[this.data.length];
        for (int i = 0; i < temp.length; ++i) {
            temp[i] = new Integer(this.data[i]);
        }
        this.duplicateLabels = DuplicateLabel.createDuplicateLabels(temp);
    }
    
    protected Vector<SelectionSwapQuestion> generateQuestions() {
        final Vector<SelectionSwapQuestion> questionsToAsk = new Vector();
        for (int i = 0; i < this.questions.size(); ++i) {
            final SwapRequest swapRequest = (SwapRequest)this.questions.elementAt(i);
            questionsToAsk.addElement(new SelectionSwapQuestion(swapRequest.getFirstId(), swapRequest.getSecondId()));
        }
        return questionsToAsk;
    }
    
    public MultipleTween generateTweens(final Paintable paintable, final Object parameter, final int numberOfSteps) {
        final MultipleTween tweens = new MultipleTween(paintable);
        final ElementArray elementArray = (ElementArray)((ElementArray)parameter);
        for (int i = 0; i < this.swapRequests.size(); ++i) {
            final SwapRequest swapRequest = (SwapRequest)this.swapRequests.elementAt(i);
            final VerticalBar first = (VerticalBar)((VerticalBar)elementArray.getElement(swapRequest.getFirstId()));
            final VerticalBar second = (VerticalBar)((VerticalBar)elementArray.getElement(swapRequest.getSecondId()));
            tweens.add(new MoveTween(null, first, second.getPosition(), first.getPosition(), false, numberOfSteps));
            tweens.add(new MoveTween(null, second, first.getPosition(), second.getPosition(), false, numberOfSteps));
        }
        return tweens;
    }
    
    public String getClassName() {
        return "SelectionSortCanvas";
    }
    
    public ElementArray getElementArray(final Graphics g) {
        final ElementArray elementArray = new ElementArray(0, 0);
        elementArray.setElementWidth(g.getFontMetrics().stringWidth("8") + 2);
        elementArray.setAllHaveSameWidth(true);
        for (int i = 0; i < this.data.length; ++i) {
            final VerticalBar verticalBar = new VerticalBar(i, this.data[i], this.arrayColor, new Point(0, 0));
            if (i < this.recordOfI) {
                verticalBar.setColor(this.finishedArrayColor);
            }
            verticalBar.setMarkersAboveValue(false);
            verticalBar.setTextColor(this.textColor);
            elementArray.setValue(i, verticalBar);
            if (this.duplicateLabels[i] != null) {
                final StringMarker stringMarker = new StringMarker("" + this.data[i] + this.duplicateLabels[i]);
                stringMarker.setBackgroundColor(this.backgroundColor);
                stringMarker.setColor(this.textColor);
                elementArray.getElement(i).addMarker(stringMarker);
            }
        }
        return elementArray;
    }
    
    public int getI() {
        return this.i;
    }
    
    public int getJ() {
        return this.j;
    }
    
    public int getMinPosition() {
        return this.minPosition;
    }
    
    protected boolean hasQuestions() {
        return this.questions.size() > 0;
    }
    
    protected void initialise(final int[] data) {
        this.data = data;
        this.createDuplicateLabels();
        this.i = -10;
        this.j = -10;
        this.recordOfI = 0;
        this.minPosition = -10;
    }
    
    protected void makeQuestion(final int i) {
        int minPosition = i;
        for (int j = i; j < this.data.length; ++j) {
            if (this.data[j] < this.data[minPosition]) {
                minPosition = j;
            }
        }
        this.questions.addElement(new SwapRequest(i, minPosition));
    }
    
    public void processColorSelection(final ColorSelection colorSelection) {
        final String atribute = colorSelection.getAtributeName();
        if (atribute.equalsIgnoreCase(ColorSelection.BACKGROUND)) {
            this.backgroundColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR)) {
            this.textColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(SelectionSort.ARRAY_COLOR)) {
            this.arrayColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(SelectionSort.FINISHED_ARRAY_COLOR)) {
            this.finishedArrayColor = colorSelection.getColor();
        }
    }
    
    public void reInitialise(final Object data) {
        this.initialise((int[])((int[])data));
    }
    
    protected void removeAllAnimationRequests() {
        this.swapRequests.removeAllElements();
    }
    
    protected void removeAllQuestions() {
        this.questions.removeAllElements();
    }
    
    protected void run() {
        this.setPosition("1");
        this.i = 0;
        while (this.i < this.data.length) {
            this.recordOfI = this.i;
            this.makeQuestion(this.i);
            this.askQuestionsWithoutSetPosition();
            this.setPosition("2");
            this.minPosition = this.i;
            this.setPosition("3");
            this.setPosition("4");
            this.j = this.i + 1;
            while (this.j < this.data.length) {
                this.setPosition("5");
                this.setPosition("6");
                if (this.data[this.j] < this.data[this.minPosition]) {
                    this.minPosition = this.j;
                    this.setPosition("7");
                }
                this.j = this.j + 1;
            }
            this.setPosition("5");
            this.j = -10;
            this.setPosition("8");
            this.setPosition("10");
            this.swap(this.i, this.minPosition);
            this.setPosition("13");
            this.minPosition = -10;
            this.i = this.i + 1;
        }
        this.recordOfI = this.data.length;
        this.i = -10;
        this.setPosition("15");
    }
    
    protected void swap(final int v, final int w) {
        final int temp = this.data[v];
        this.data[v] = this.data[w];
        this.data[w] = temp;
        final String tempLabel = this.duplicateLabels[v];
        this.duplicateLabels[v] = this.duplicateLabels[w];
        this.duplicateLabels[w] = tempLabel;
        if (v != w) {
            this.swapRequests.addElement(new SwapRequest(v, w));
        }
    }
    
    static {
        ARRAY_COLOR = Messages.getString("SelectionSort.0");
        FINISHED_ARRAY_COLOR = Messages.getString("SelectionSort.1");
    }
}
