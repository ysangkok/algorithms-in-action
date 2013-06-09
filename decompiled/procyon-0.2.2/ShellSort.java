import localization.*;
import java.awt.*;
import java.util.*;
import com.cim.AIA.*;

public class ShellSort extends Algorithm implements ColorSelectionListener
{
    public static final int MARKER_NOT_READY = -10;
    public static final int H_SUBFILES = 10;
    public static final int SINGLE_PASS = 11;
    protected static int currentComparisonOrder;
    protected static String[] duplicateLabels;
    public static Vector<Integer> drawLine;
    public static Color activeArrayColor;
    protected static final String ARRAY_COLOR;
    protected static final String ACTIVE_ARRAY_COLOR;
    protected static final String PROCESSED_ARRAY_COLOR;
    protected static final String FINISHED_ARRAY_COLOR;
    protected static Vector<SwapRequest> swapRequests;
    protected static boolean dontPaintVerticalBarI;
    protected static boolean dontPaintVerticalBarJ;
    protected static boolean finished;
    protected static boolean paintProcessedArrayI;
    protected int[] data;
    protected Color[] colors;
    protected int i;
    protected int j;
    protected int h;
    protected int val;
    protected int firstPos;
    protected int finalPosition;
    protected int recordOfI;
    protected int recordOfH;
    protected Color textColor;
    protected Color arrayColor;
    protected Color processedArrayColor;
    protected Color finishedArrayColor;
    protected Color backgroundColor;
    
    public ShellSort(final AlgorithmThread algorithmThread, final Object data) {
        super(algorithmThread, data);
        this.i = -10;
        this.j = -10;
        this.h = -10;
        this.val = -10;
        this.firstPos = -10;
        this.finalPosition = -10;
        this.recordOfI = -10;
        this.recordOfH = -10;
        this.textColor = Color.black;
        this.arrayColor = Color.pink;
        this.processedArrayColor = Color.red.darker();
        this.finishedArrayColor = Color.gray;
        this.backgroundColor = Color.white;
        this.initialise((int[])((int[])data));
        final ConfigurationManager cm = ConfigurationManager.getConfigurationManager();
        final ColorSelection arrayColorSelection = new ColorSelection(this.arrayColor, ShellSort.ARRAY_COLOR);
        arrayColorSelection.addClass(this);
        final ColorSelection activeArrayColorSelection = new ColorSelection(ShellSort.activeArrayColor, ShellSort.ACTIVE_ARRAY_COLOR);
        activeArrayColorSelection.addClass(this);
        final ColorSelection processedArrayColorSelection = new ColorSelection(this.processedArrayColor, ShellSort.PROCESSED_ARRAY_COLOR);
        processedArrayColorSelection.addClass(this);
        final ColorSelection finishedArrayColorSelection = new ColorSelection(this.finishedArrayColor, ShellSort.FINISHED_ARRAY_COLOR);
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
        ShellSort.duplicateLabels = DuplicateLabel.createDuplicateLabels(temp);
    }
    
    protected Vector<E> generateQuestions() {
        return null;
    }
    
    protected void generateTweens(final MultipleTween tweens, final ElementArray elementArray, final int numberOfSteps) {
        for (int i = 0; i < ShellSort.swapRequests.size(); ++i) {
            final SwapRequest swapRequest = (SwapRequest)ShellSort.swapRequests.elementAt(i);
            final VerticalBar from = (VerticalBar)((VerticalBar)elementArray.getElement(swapRequest.getFirstId()));
            if (((ShellSortThread)this.algorithmThread).getWindow().isExpanded("7.2")) {
                from.setColor(this.processedArrayColor);
            }
            final VerticalBar to = (VerticalBar)((VerticalBar)elementArray.getElement(swapRequest.getSecondId()));
            if (((ShellSortThread)this.algorithmThread).getWindow().isExpanded("7.2") && i == ShellSort.swapRequests.size() - 1) {
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
        return Messages.getString("ShellSort.4");
    }
    
    public int getComparisonOrder() {
        return ShellSort.currentComparisonOrder;
    }
    
    public ElementArray getElementArray(final Graphics g) {
        final ElementArray elementArray = new ElementArray(0, 0);
        elementArray.setElementWidth(g.getFontMetrics().stringWidth("8") + 2);
        elementArray.setAllHaveSameWidth(true);
        for (int i = 0; i < this.data.length; ++i) {
            VerticalBar verticalBar = new VerticalBar(i, -10, this.backgroundColor, this.backgroundColor, new Point(0, 0));
            if (ShellSort.dontPaintVerticalBarJ) {
                if (i != this.getJ()) {
                    verticalBar = new VerticalBar(i, this.data[i], this.colors[i], new Point(0, 0));
                    if (this.recordOfI != 1 && this.getH() == 1 && i < this.recordOfI) {
                        verticalBar.setColor(this.processedArrayColor);
                    }
                    if (this.getH() == 1 && i == this.recordOfI && ShellSort.paintProcessedArrayI) {
                        verticalBar.setColor(this.processedArrayColor);
                    }
                    if (i == this.getFinalPosition()) {
                        verticalBar.setColor(ShellSort.activeArrayColor);
                    }
                    if (ShellSort.finished) {
                        verticalBar.setColor(this.finishedArrayColor);
                    }
                    verticalBar.setMarkersAboveValue(false);
                    verticalBar.setTextColor(this.textColor);
                }
                else {
                    verticalBar.setDrawValueMarker(false);
                }
            }
            else if (ShellSort.dontPaintVerticalBarI) {
                if (i != this.getI()) {
                    verticalBar = new VerticalBar(i, this.data[i], this.colors[i], new Point(0, 0));
                    if (this.recordOfI != 1 && this.getH() == 1 && i < this.recordOfI) {
                        verticalBar.setColor(this.processedArrayColor);
                    }
                    if (this.getH() == 1 && i == this.recordOfI && ShellSort.paintProcessedArrayI) {
                        verticalBar.setColor(this.processedArrayColor);
                    }
                    if (i == this.getFinalPosition()) {
                        verticalBar.setColor(ShellSort.activeArrayColor);
                    }
                    if (ShellSort.finished) {
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
                verticalBar = new VerticalBar(i, this.data[i], this.colors[i], new Point(0, 0));
                if (this.recordOfI != 1 && this.getH() == 1 && i < this.recordOfI) {
                    verticalBar.setColor(this.processedArrayColor);
                }
                if (this.getH() == 1 && i == this.recordOfI && ShellSort.paintProcessedArrayI) {
                    verticalBar.setColor(this.processedArrayColor);
                }
                if (i == this.getFinalPosition()) {
                    verticalBar.setColor(ShellSort.activeArrayColor);
                }
                if (ShellSort.finished) {
                    verticalBar.setColor(this.finishedArrayColor);
                }
                verticalBar.setMarkersAboveValue(false);
                verticalBar.setTextColor(this.textColor);
            }
            elementArray.setValue(i, verticalBar);
            if (ShellSort.duplicateLabels[i] != null) {
                final StringMarker stringMarker = new StringMarker("" + this.data[i] + ShellSort.duplicateLabels[i]);
                stringMarker.setBackgroundColor(this.backgroundColor);
                stringMarker.setColor(this.textColor);
                elementArray.getElement(i).addMarker(stringMarker);
            }
        }
        return elementArray;
    }
    
    public int getFinalPosition() {
        return this.finalPosition;
    }
    
    public int getFirstPos() {
        return this.firstPos;
    }
    
    public int getH() {
        return this.h;
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
    
    protected boolean hasQuestions() {
        return false;
    }
    
    protected void initialise(final int[] data) {
        this.data = data;
        this.colors = new Color[data.length];
        for (int i = 0; i < this.colors.length; ++i) {
            this.colors[i] = this.arrayColor;
        }
        this.createDuplicateLabels();
        this.i = -10;
        this.j = -10;
        this.recordOfI = -10;
        this.recordOfH = -10;
        ShellSort.paintProcessedArrayI = false;
        this.finalPosition = -10;
        ShellSort.finished = false;
    }
    
    public void processColorSelection(final ColorSelection colorSelection) {
        final String atribute = colorSelection.getAtributeName();
        if (atribute.equalsIgnoreCase(ColorSelection.BACKGROUND)) {
            this.backgroundColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR)) {
            this.textColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(ShellSort.ARRAY_COLOR)) {
            for (int i = 0; i < this.colors.length; ++i) {
                if (this.colors[i] == this.arrayColor) {
                    this.colors[i] = colorSelection.getColor();
                }
            }
            this.arrayColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(ShellSort.ACTIVE_ARRAY_COLOR)) {
            for (int i = 0; i < this.colors.length; ++i) {
                if (this.colors[i] == ShellSort.activeArrayColor) {
                    this.colors[i] = colorSelection.getColor();
                }
            }
            ShellSort.activeArrayColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(ShellSort.PROCESSED_ARRAY_COLOR)) {
            for (int i = 0; i < this.colors.length; ++i) {
                if (this.colors[i] == this.processedArrayColor) {
                    this.colors[i] = colorSelection.getColor();
                }
            }
            this.processedArrayColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(ShellSort.FINISHED_ARRAY_COLOR)) {
            for (int i = 0; i < this.colors.length; ++i) {
                if (this.colors[i] == this.finishedArrayColor) {
                    this.colors[i] = colorSelection.getColor();
                }
            }
            this.finishedArrayColor = colorSelection.getColor();
        }
    }
    
    public void reInitialise(final Object data) {
        this.initialise((int[])((int[])data));
        final int i = -10;
        this.val = i;
        this.j = i;
        this.i = i;
    }
    
    protected void removeAllAnimationRequests() {
        ShellSort.swapRequests.removeAllElements();
    }
    
    protected void removeAllQuestions() {
    }
    
    protected void run() {
        this.setPosition("1");
        this.h = 1;
        while (this.h <= this.data.length / 4) {
            this.h = 3 * this.h + 1;
        }
        this.setPosition("5");
        while (this.h > 0) {
            for (int k = 0; k < this.colors.length; ++k) {
                this.colors[k] = this.arrayColor;
            }
            this.recordOfI = 0;
            this.setPosition("7");
            switch (ShellSort.currentComparisonOrder) {
                case 10: {
                    this.recordOfH = this.h;
                    this.firstPos = 0;
                    if (ShellSort.currentComparisonOrder != 10) {
                        this.h = 3 * this.h + 1;
                        this.firstPos = -10;
                        break;
                    }
                    this.setPosition("7.5");
                    while (this.firstPos < this.h) {
                        this.i = this.firstPos + this.h;
                        this.recordOfI = this.i;
                        if (ShellSort.currentComparisonOrder != 10) {
                            this.h = 3 * this.h + 1;
                            this.firstPos = -10;
                            break;
                        }
                        this.setPosition("7.5.2");
                        while (this.i < this.data.length) {
                            ShellSort.paintProcessedArrayI = false;
                            this.val = this.data[this.i];
                            this.colors[this.i] = ShellSort.activeArrayColor;
                            if (ShellSort.currentComparisonOrder != 10) {
                                this.h = 3 * this.h + 1;
                                this.firstPos = -10;
                                break;
                            }
                            this.setPosition("7.5.3.1");
                            this.j = this.i;
                            if (ShellSort.currentComparisonOrder != 10) {
                                this.h = 3 * this.h + 1;
                                this.firstPos = -10;
                                break;
                            }
                            this.setPosition("7.5.3.2");
                            ShellSort.drawLine.addElement(new Integer(this.j));
                            ShellSort.drawLine.addElement(new Integer(this.j - this.h));
                            if (ShellSort.currentComparisonOrder != 10) {
                                this.h = 3 * this.h + 1;
                                this.firstPos = -10;
                                break;
                            }
                            this.setPosition("7.5.3.3");
                            while (this.j >= this.h && this.data[this.j - this.h] > this.val) {
                                this.swap(this.j, this.j - this.h);
                                if (ShellSort.currentComparisonOrder != 10) {
                                    this.h = 3 * this.h + 1;
                                    this.firstPos = -10;
                                    break;
                                }
                                this.setPosition("7.5.3.4.1");
                                this.j = this.j - this.h;
                                if (((ShellSortThread)this.algorithmThread).getWindow().isExpanded("7.5.1")) {
                                    ShellSort.dontPaintVerticalBarJ = true;
                                }
                                ShellSort.paintProcessedArrayI = true;
                                if (ShellSort.currentComparisonOrder != 10) {
                                    this.h = 3 * this.h + 1;
                                    this.firstPos = -10;
                                    break;
                                }
                                this.setPosition("7.5.3.4.2");
                                if (this.j >= this.h) {
                                    ShellSort.drawLine.addElement(new Integer(this.j - this.h));
                                }
                                if (ShellSort.currentComparisonOrder != 10) {
                                    this.h = 3 * this.h + 1;
                                    this.firstPos = -10;
                                    break;
                                }
                                this.setPosition("7.5.3.3");
                                if (ShellSort.dontPaintVerticalBarJ == true) {
                                    ShellSort.dontPaintVerticalBarJ = false;
                                }
                            }
                            if (this.j >= this.h && this.data[this.j - this.h] <= this.val) {
                                this.colors[this.j - this.h] = this.processedArrayColor;
                            }
                            this.data[this.j] = this.val;
                            this.finalPosition = this.j;
                            if (ShellSort.currentComparisonOrder != 10) {
                                if (this.h == this.recordOfH) {
                                    this.h = 3 * this.h + 1;
                                }
                                if (ShellSort.drawLine.size() > 0) {
                                    ShellSort.drawLine.removeAllElements();
                                }
                                this.firstPos = -10;
                                break;
                            }
                            this.setPosition("7.5.3.6");
                            this.finalPosition = -10;
                            this.colors[this.j] = this.processedArrayColor;
                            this.j = -10;
                            this.val = -10;
                            ShellSort.drawLine.removeAllElements();
                            this.i = this.i + this.h;
                            this.recordOfI = this.i;
                            ShellSort.paintProcessedArrayI = false;
                            if (ShellSort.currentComparisonOrder != 10) {
                                if (this.h == this.recordOfH) {
                                    this.h = 3 * this.h + 1;
                                }
                                if (ShellSort.drawLine.size() > 0) {
                                    ShellSort.drawLine.removeAllElements();
                                }
                                this.firstPos = -10;
                                break;
                            }
                            if (this.i < this.data.length) {
                                this.setPosition("7.5.2");
                            }
                        }
                        if (ShellSort.currentComparisonOrder != 10) {
                            if (this.h == this.recordOfH) {
                                this.h = 3 * this.h + 1;
                            }
                            if (ShellSort.drawLine.size() > 0) {
                                ShellSort.drawLine.removeAllElements();
                            }
                            this.firstPos = -10;
                            this.i = -10;
                            this.j = -10;
                            this.finalPosition = -10;
                            if (ShellSort.dontPaintVerticalBarJ == true) {
                                ShellSort.dontPaintVerticalBarJ = false;
                                break;
                            }
                            break;
                        }
                        else {
                            this.setPosition("7.5.4");
                            this.recordOfI = this.data.length;
                            this.i = -10;
                            this.firstPos = this.firstPos + 1;
                            if (ShellSort.currentComparisonOrder != 10) {
                                if (this.h == this.recordOfH) {
                                    this.h = 3 * this.h + 1;
                                }
                                if (ShellSort.drawLine.size() > 0) {
                                    ShellSort.drawLine.removeAllElements();
                                }
                                this.firstPos = -10;
                                this.i = -10;
                                this.j = -10;
                                this.finalPosition = -10;
                                if (ShellSort.dontPaintVerticalBarJ == true) {
                                    ShellSort.dontPaintVerticalBarJ = false;
                                    break;
                                }
                                break;
                            }
                            else if (this.firstPos != this.h) {
                                this.setPosition("7.5");
                            }
                        }
                    }
                    this.setPosition("7.6");
                    this.firstPos = -10;
                    break;
                }
                case 11: {
                    this.recordOfH = this.h;
                    this.i = this.h;
                    this.recordOfI = this.i;
                    if (ShellSort.currentComparisonOrder != 11) {
                        this.h = 3 * this.h + 1;
                        break;
                    }
                    this.setPosition("7.4");
                    while (this.i < this.data.length) {
                        ShellSort.paintProcessedArrayI = false;
                        this.val = this.data[this.i];
                        this.colors[this.i] = ShellSort.activeArrayColor;
                        if (ShellSort.currentComparisonOrder != 11) {
                            this.h = 3 * this.h + 1;
                            break;
                        }
                        this.setPosition("7.4.2.1");
                        this.j = this.i;
                        if (ShellSort.currentComparisonOrder != 11) {
                            this.h = 3 * this.h + 1;
                            break;
                        }
                        this.setPosition("7.4.2.2");
                        ShellSort.drawLine.addElement(new Integer(this.j));
                        ShellSort.drawLine.addElement(new Integer(this.j - this.h));
                        if (ShellSort.currentComparisonOrder != 11) {
                            this.h = 3 * this.h + 1;
                            break;
                        }
                        this.setPosition("7.4.2.3");
                        while (this.j >= this.h && this.data[this.j - this.h] > this.val) {
                            this.swap(this.j, this.j - this.h);
                            if (ShellSort.currentComparisonOrder != 11) {
                                this.h = 3 * this.h + 1;
                                break;
                            }
                            this.setPosition("7.4.2.4.1");
                            this.j = this.j - this.h;
                            if (((ShellSortThread)this.algorithmThread).getWindow().isExpanded("7.4.1")) {
                                ShellSort.dontPaintVerticalBarJ = true;
                            }
                            ShellSort.paintProcessedArrayI = true;
                            if (ShellSort.currentComparisonOrder != 11) {
                                this.h = 3 * this.h + 1;
                                break;
                            }
                            this.setPosition("7.4.2.4.2");
                            if (this.j >= this.h) {
                                ShellSort.drawLine.addElement(new Integer(this.j - this.h));
                            }
                            if (ShellSort.currentComparisonOrder != 11) {
                                this.h = 3 * this.h + 1;
                                break;
                            }
                            this.setPosition("7.4.2.3");
                            if (ShellSort.dontPaintVerticalBarJ == true) {
                                ShellSort.dontPaintVerticalBarJ = false;
                            }
                        }
                        if (this.j >= this.h && this.data[this.j - this.h] <= this.val) {
                            this.colors[this.j - this.h] = this.processedArrayColor;
                        }
                        this.data[this.j] = this.val;
                        this.finalPosition = this.j;
                        if (ShellSort.currentComparisonOrder != 11) {
                            if (this.h == this.recordOfH) {
                                this.h = 3 * this.h + 1;
                            }
                            if (ShellSort.drawLine.size() > 0) {
                                ShellSort.drawLine.removeAllElements();
                                break;
                            }
                            break;
                        }
                        else {
                            this.setPosition("7.4.2.6");
                            this.finalPosition = -10;
                            this.colors[this.j] = this.processedArrayColor;
                            this.j = -10;
                            this.val = -10;
                            ShellSort.drawLine.removeAllElements();
                            this.i = this.i + 1;
                            this.recordOfI = this.i;
                            ShellSort.paintProcessedArrayI = false;
                            if (ShellSort.currentComparisonOrder != 11) {
                                if (this.h == this.recordOfH) {
                                    this.h = 3 * this.h + 1;
                                }
                                if (ShellSort.drawLine.size() > 0) {
                                    ShellSort.drawLine.removeAllElements();
                                    break;
                                }
                                break;
                            }
                            else if (this.i != this.data.length) {
                                this.setPosition("7.4");
                            }
                        }
                    }
                    if (ShellSort.currentComparisonOrder == 11) {
                        this.setPosition("7.5");
                        this.recordOfI = this.data.length;
                        this.i = -10;
                        break;
                    }
                    if (this.h == this.recordOfH) {
                        this.h = 3 * this.h + 1;
                    }
                    if (ShellSort.drawLine.size() > 0) {
                        ShellSort.drawLine.removeAllElements();
                    }
                    this.i = -10;
                    this.j = -10;
                    this.finalPosition = -10;
                    if (ShellSort.dontPaintVerticalBarJ == true) {
                        ShellSort.dontPaintVerticalBarJ = false;
                        break;
                    }
                    break;
                }
            }
            this.h = this.h / 3;
        }
        this.h = -10;
        ShellSort.finished = true;
    }
    
    public void setComparisonOrder(final int comparisonOrder) {
        ShellSort.currentComparisonOrder = comparisonOrder;
    }
    
    protected void swap(final int j, final int i) {
        final int temp = this.data[i];
        this.data[i] = this.data[j];
        this.data[j] = temp;
        final Color[] colors = this.colors;
        final Color[] colors2 = this.colors;
        final Color processedArrayColor = this.processedArrayColor;
        colors2[j] = processedArrayColor;
        colors[i] = processedArrayColor;
        final String tempLabel = ShellSort.duplicateLabels[i];
        ShellSort.duplicateLabels[i] = ShellSort.duplicateLabels[j];
        ShellSort.duplicateLabels[j] = tempLabel;
        if (i != j) {
            ShellSort.swapRequests.addElement(new SwapRequest(j, i));
        }
    }
    
    static {
        ShellSort.currentComparisonOrder = 10;
        ShellSort.drawLine = new Vector();
        ShellSort.activeArrayColor = Color.red;
        ARRAY_COLOR = Messages.getString("ShellSort.0");
        ACTIVE_ARRAY_COLOR = Messages.getString("ShellSort.1");
        PROCESSED_ARRAY_COLOR = Messages.getString("ShellSort.2");
        FINISHED_ARRAY_COLOR = Messages.getString("ShellSort.3");
        ShellSort.swapRequests = new Vector();
        ShellSort.dontPaintVerticalBarI = false;
        ShellSort.dontPaintVerticalBarJ = false;
        ShellSort.finished = false;
        ShellSort.paintProcessedArrayI = false;
    }
}
