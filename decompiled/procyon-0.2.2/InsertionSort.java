import localization.*;
import java.util.*;
import java.awt.*;
import com.cim.AIA.*;

public class InsertionSort extends Algorithm implements ColorSelectionListener
{
    public static final int MARKER_NOT_READY = -10;
    public static Color activeArrayColor;
    protected static final String ARRAY_COLOR;
    protected static final String ACTIVE_ARRAY_COLOR;
    protected static final String PROCESSED_ARRAY_COLOR;
    protected static final String FINISHED_ARRAY_COLOR;
    protected static boolean dontPaintJMarker;
    protected static boolean dontPaintValMarker;
    protected static boolean dontPaintElementMarker;
    protected static boolean dontPaintVerticalBarJ;
    protected static boolean paintProcessedArrayI;
    protected static boolean paintActiveValEarly;
    protected static Vector<SwapRequest> moveRequests;
    protected int[] data;
    protected int recordOfI;
    protected int recordOfJ;
    protected String[] duplicateLabels;
    protected int i;
    protected int j;
    protected int val;
    protected int valPosition;
    protected int finalPosition;
    protected int elementPosition;
    protected Color textColor;
    protected Color arrayColor;
    protected Color processedArrayColor;
    protected Color finishedArrayColor;
    protected Color backgroundColor;
    protected boolean finished;
    
    public InsertionSort(final AlgorithmThread algorithmThread, final Object data) {
        super(algorithmThread, data);
        this.recordOfI = 0;
        this.recordOfJ = 0;
        this.i = -10;
        this.j = -10;
        this.val = -10;
        this.valPosition = -10;
        this.finalPosition = -10;
        this.elementPosition = -10;
        this.textColor = Color.black;
        this.arrayColor = Color.pink;
        this.processedArrayColor = Color.red.darker();
        this.finishedArrayColor = Color.gray;
        this.backgroundColor = Color.white;
        this.finished = false;
        this.initialise((int[])((int[])data));
        final ConfigurationManager cm = ConfigurationManager.getConfigurationManager();
        final ColorSelection arrayColorSelection = new ColorSelection(this.arrayColor, InsertionSort.ARRAY_COLOR);
        arrayColorSelection.addClass(this);
        final ColorSelection activeArrayColorSelection = new ColorSelection(InsertionSort.activeArrayColor, InsertionSort.ACTIVE_ARRAY_COLOR);
        arrayColorSelection.addClass(this);
        final ColorSelection processedArrayColorSelection = new ColorSelection(this.processedArrayColor, InsertionSort.PROCESSED_ARRAY_COLOR);
        processedArrayColorSelection.addClass(this);
        final ColorSelection finishedArrayColorSelection = new ColorSelection(this.finishedArrayColor, InsertionSort.FINISHED_ARRAY_COLOR);
        finishedArrayColorSelection.addClass(this);
        cm.addColorSelection(arrayColorSelection);
        cm.addColorSelection(activeArrayColorSelection);
        cm.addColorSelection(processedArrayColorSelection);
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
    
    protected Vector<Object> generateQuestions() {
        return null;
    }
    
    protected void generateTweens(final MultipleTween tweens, final ElementArray elementArray, final int numberOfSteps) {
        for (int i = 0; i < InsertionSort.moveRequests.size(); ++i) {
            final SwapRequest moveRequest = (SwapRequest)InsertionSort.moveRequests.elementAt(i);
            final VerticalBar from = (VerticalBar)((VerticalBar)elementArray.getElement(moveRequest.getFirstId()));
            from.setColor(this.processedArrayColor);
            final VerticalBar to = (VerticalBar)((VerticalBar)elementArray.getElement(moveRequest.getSecondId()));
            if (i == InsertionSort.moveRequests.size() - 1) {
                to.setHeight(0);
                to.setThickness(0);
                to.setDrawValueMarker(false);
            }
            tweens.add(new MoveTween(null, from, to.getPosition(), from.getPosition(), false, numberOfSteps));
            tweens.add(new MoveTween(null, to, from.getPosition(), to.getPosition(), false, numberOfSteps));
        }
    }
    
    public MultipleTween generateTweens(final Paintable paintable, final Object parameter, final int numberOfSteps) {
        final MultipleTween tweens = new MultipleTween(paintable);
        final ElementArray elementArray = (ElementArray)parameter;
        this.generateTweens(tweens, elementArray, numberOfSteps);
        return tweens;
    }
    
    public String getClassName() {
        return Messages.getString("InsertionSort.4");
    }
    
    public boolean getDontPaintElementMarker() {
        return InsertionSort.dontPaintElementMarker;
    }
    
    public boolean getDontPaintJMarker() {
        return InsertionSort.dontPaintJMarker;
    }
    
    public boolean getDontPaintValMarker() {
        return InsertionSort.dontPaintValMarker;
    }
    
    public ElementArray getElementArray(final Graphics g) {
        final ElementArray elementArray = new ElementArray(0, 0);
        elementArray.setElementWidth(g.getFontMetrics().stringWidth("8") + 2);
        elementArray.setAllHaveSameWidth(true);
        for (int i = 0; i < this.data.length; ++i) {
            VerticalBar verticalBar = new VerticalBar(i, -10, this.backgroundColor, this.backgroundColor, new Point(0, 0));
            if (InsertionSort.dontPaintVerticalBarJ) {
                if (i != this.getJ()) {
                    verticalBar = new VerticalBar(i, this.data[i], this.arrayColor, new Point(0, 0));
                    if (this.recordOfI != 1 && i < this.recordOfI) {
                        verticalBar.setColor(this.processedArrayColor);
                    }
                    if (i == this.recordOfI && InsertionSort.paintProcessedArrayI) {
                        verticalBar.setColor(this.processedArrayColor);
                    }
                    if (InsertionSort.paintActiveValEarly && i == this.getI()) {
                        verticalBar.setColor(InsertionSort.activeArrayColor);
                    }
                    if (i == this.getFinalPosition()) {
                        verticalBar.setColor(InsertionSort.activeArrayColor);
                    }
                    if (i == this.getValPosition()) {
                        verticalBar.setColor(InsertionSort.activeArrayColor);
                    }
                    if (this.finished) {
                        verticalBar.setColor(this.finishedArrayColor);
                    }
                    verticalBar.setMarkersAboveValue(false);
                    verticalBar.setTextColor(this.textColor);
                }
                else {
                    verticalBar.setDrawValueMarker(false);
                }
            }
            else {
                verticalBar = new VerticalBar(i, this.data[i], this.arrayColor, new Point(0, 0));
                if (this.recordOfI != 1 && i < this.recordOfI) {
                    verticalBar.setColor(this.processedArrayColor);
                }
                if (i == this.recordOfI && InsertionSort.paintProcessedArrayI) {
                    verticalBar.setColor(this.processedArrayColor);
                }
                if (InsertionSort.paintActiveValEarly && i == this.getI()) {
                    verticalBar.setColor(InsertionSort.activeArrayColor);
                }
                if (i == this.getFinalPosition()) {
                    verticalBar.setColor(InsertionSort.activeArrayColor);
                }
                if (i == this.getValPosition()) {
                    verticalBar.setColor(InsertionSort.activeArrayColor);
                }
                if (this.finished) {
                    verticalBar.setColor(this.finishedArrayColor);
                }
                verticalBar.setMarkersAboveValue(false);
                verticalBar.setTextColor(this.textColor);
            }
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
    
    public int getElementPosition() {
        return this.elementPosition;
    }
    
    public int getFinalPosition() {
        return this.finalPosition;
    }
    
    public int getI() {
        return this.i;
    }
    
    public int getJ() {
        return this.j;
    }
    
    public int getVal() {
        return this.val;
    }
    
    public int getValPosition() {
        return this.valPosition;
    }
    
    protected boolean hasQuestions() {
        return false;
    }
    
    protected void initialise(final int[] data) {
        this.data = data;
        this.createDuplicateLabels();
        this.i = -10;
        this.j = -10;
        this.recordOfI = 0;
        this.recordOfJ = 0;
        this.val = -10;
        InsertionSort.dontPaintVerticalBarJ = false;
        InsertionSort.paintProcessedArrayI = false;
        InsertionSort.paintActiveValEarly = false;
        InsertionSort.dontPaintValMarker = false;
        this.valPosition = -10;
        this.finalPosition = -10;
        this.elementPosition = -10;
        this.finished = false;
    }
    
    public void processColorSelection(final ColorSelection colorSelection) {
        final String atribute = colorSelection.getAtributeName();
        if (atribute.equalsIgnoreCase(ColorSelection.BACKGROUND)) {
            this.backgroundColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR)) {
            this.textColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(InsertionSort.ARRAY_COLOR)) {
            this.arrayColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(InsertionSort.ACTIVE_ARRAY_COLOR)) {
            InsertionSort.activeArrayColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(InsertionSort.PROCESSED_ARRAY_COLOR)) {
            this.processedArrayColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(InsertionSort.FINISHED_ARRAY_COLOR)) {
            this.finishedArrayColor = colorSelection.getColor();
        }
    }
    
    public void reInitialise(final Object data) {
        this.initialise((int[])((int[])data));
        final int i = -10;
        this.val = i;
        this.j = i;
        this.i = i;
        final int valPosition = -10;
        this.elementPosition = valPosition;
        this.finalPosition = valPosition;
        this.valPosition = valPosition;
        this.setPosition("");
    }
    
    protected void removeAllAnimationRequests() {
        InsertionSort.moveRequests.removeAllElements();
    }
    
    protected void removeAllQuestions() {
    }
    
    protected void run() {
        this.setPosition("1");
        this.i = 1;
        this.recordOfI = this.i;
        InsertionSort.paintActiveValEarly = false;
        InsertionSort.dontPaintValMarker = true;
        if (!((InsertionSortThread)this.algorithmThread).getWindow().isExpanded("3.1")) {
            InsertionSort.paintActiveValEarly = true;
        }
        this.setPosition("3");
        while (this.i < this.data.length) {
            InsertionSort.paintProcessedArrayI = false;
            this.j = -10;
            if (!((InsertionSortThread)this.algorithmThread).getWindow().isExpanded("3.1")) {
                InsertionSort.dontPaintJMarker = true;
            }
            this.val = this.data[this.i];
            this.valPosition = this.i;
            this.setPosition("3.1.1.1");
            this.j = this.i;
            if (((InsertionSortThread)this.algorithmThread).getWindow().isExpanded("3.1.2") && ((InsertionSortThread)this.algorithmThread).getWindow().isExpanded("3.1")) {
                InsertionSort.dontPaintJMarker = false;
            }
            else {
                InsertionSort.dontPaintJMarker = true;
            }
            InsertionSort.dontPaintVerticalBarJ = false;
            this.setPosition("3.1.2.1");
            if (((InsertionSortThread)this.algorithmThread).getWindow().isExpanded("3.1.2.2") && ((InsertionSortThread)this.algorithmThread).getWindow().isExpanded("3.1")) {
                InsertionSort.dontPaintJMarker = false;
            }
            else {
                InsertionSort.dontPaintJMarker = true;
            }
            InsertionSort.dontPaintValMarker = false;
            this.elementPosition = this.j - 1;
            this.setPosition("3.1.2.2.a");
            while (this.j > 0 && this.data[this.j - 1] > this.val) {
                if (InsertionSort.dontPaintVerticalBarJ == true) {
                    InsertionSort.dontPaintVerticalBarJ = false;
                }
                this.swap(this.j, this.j - 1);
                this.valPosition = -10;
                InsertionSort.dontPaintValMarker = true;
                this.elementPosition = -10;
                this.setPosition("3.1.2.2.1");
                this.j = this.j - 1;
                if (((InsertionSortThread)this.algorithmThread).getWindow().isExpanded("3.1.2.2")) {
                    InsertionSort.dontPaintVerticalBarJ = true;
                }
                InsertionSort.paintProcessedArrayI = true;
                this.setPosition("3.1.2.2.2");
                InsertionSort.dontPaintValMarker = false;
                this.elementPosition = this.j - 1;
                this.setPosition("3.1.2.2.a");
            }
            InsertionSort.dontPaintValMarker = true;
            this.elementPosition = -10;
            this.setPosition("3.1.2.2.3");
            InsertionSort.dontPaintVerticalBarJ = false;
            this.finalPosition = this.j;
            this.data[this.j] = this.val;
            if (((InsertionSortThread)this.algorithmThread).getWindow().isExpanded("3.1.3") && ((InsertionSortThread)this.algorithmThread).getWindow().isExpanded("3.1")) {
                InsertionSort.dontPaintJMarker = false;
            }
            else {
                InsertionSort.dontPaintJMarker = true;
            }
            this.setPosition("3.1.3.1");
            this.finalPosition = -10;
            this.val = -10;
            this.j = -10;
            this.i = this.i + 1;
            this.recordOfI = this.i;
            InsertionSort.paintProcessedArrayI = false;
            if (!((InsertionSortThread)this.algorithmThread).getWindow().isExpanded("3.1")) {
                InsertionSort.paintActiveValEarly = true;
            }
            else {
                InsertionSort.paintActiveValEarly = false;
            }
            if (this.i != this.data.length) {
                this.setPosition("3");
            }
        }
        this.recordOfI = this.data.length;
        this.i = -10;
        this.finished = true;
    }
    
    protected void swap(final int j, final int i) {
        final int temp = this.data[i];
        this.data[i] = this.data[j];
        this.data[j] = temp;
        final String tempLabel = this.duplicateLabels[i];
        this.duplicateLabels[i] = this.duplicateLabels[j];
        this.duplicateLabels[j] = tempLabel;
        if (i != j) {
            InsertionSort.moveRequests.addElement(new SwapRequest(j, i));
        }
    }
    
    static {
        InsertionSort.activeArrayColor = Color.red;
        ARRAY_COLOR = Messages.getString("InsertionSort.0");
        ACTIVE_ARRAY_COLOR = Messages.getString("InsertionSort.1");
        PROCESSED_ARRAY_COLOR = Messages.getString("InsertionSort.2");
        FINISHED_ARRAY_COLOR = Messages.getString("InsertionSort.3");
        InsertionSort.dontPaintJMarker = false;
        InsertionSort.dontPaintValMarker = false;
        InsertionSort.dontPaintElementMarker = false;
        InsertionSort.dontPaintVerticalBarJ = false;
        InsertionSort.paintProcessedArrayI = false;
        InsertionSort.paintActiveValEarly = false;
        InsertionSort.moveRequests = new Vector();
    }
}
