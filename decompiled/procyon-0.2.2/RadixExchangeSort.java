import localization.*;
import java.util.*;
import java.awt.*;
import com.cim.AIA.*;

public class RadixExchangeSort extends Algorithm implements ColorSelectionListener
{
    protected static final int ARROW_LENGTH = 30;
    protected static int currentBit;
    protected static final String MSB_LABEL;
    protected static final String LSB_LABEL;
    protected static final String BIT_LABEL;
    protected static String[] duplicateLabels;
    public static final String I_MARKER = "i";
    public static final String J_MARKER = "J";
    public static Color WAITING_COLOR;
    public static Color HIGHLIGHT_COLOR;
    public static Color DOING_CHILDREN_COLOR;
    protected static final String ACTIVE;
    protected static final String PROCESSED;
    protected static final String FINISHED;
    protected static final String WAITING;
    public static Color ACTIVE_COLOR;
    public static Color FINISHED_COLOR;
    public static Color BACKGROUND;
    public static Color TEXT_COLOR;
    protected static final boolean DEBUG_MODE = 0;
    public static final int MARKER_NOT_READY = -10;
    protected int columSpacing;
    protected int columWidth;
    protected int xTextBuffer;
    protected int yTextBuffer;
    protected RadixExchangeSort leftChild;
    protected RadixExchangeSort rightChild;
    protected boolean active;
    protected boolean processed;
    protected boolean doingChildren;
    protected int[] data;
    protected int left;
    protected int right;
    protected int bit;
    protected int bitLength;
    protected int i;
    protected int j;
    protected Vector<SwapRequest> swapRequests;
    
    protected RadixExchangeSort(final AlgorithmThread algorithmThread, final int[] data, final boolean newSearch) {
        super(algorithmThread, data);
        this.columSpacing = 10;
        this.columWidth = 10;
        this.xTextBuffer = 20;
        this.yTextBuffer = 2;
        this.leftChild = null;
        this.rightChild = null;
        this.active = false;
        this.processed = false;
        this.doingChildren = false;
        this.i = -10;
        this.j = -10;
        this.swapRequests = new Vector();
        this.data = data;
        this.initialise(newSearch);
    }
    
    public RadixExchangeSort(final AlgorithmThread algorithmThread, final Object data) {
        super(algorithmThread, data);
        this.columSpacing = 10;
        this.columWidth = 10;
        this.xTextBuffer = 20;
        this.yTextBuffer = 2;
        this.leftChild = null;
        this.rightChild = null;
        this.active = false;
        this.processed = false;
        this.doingChildren = false;
        this.i = -10;
        this.j = -10;
        this.swapRequests = new Vector();
        this.data = (int[])((int[])data);
        this.initialise(true);
        final ConfigurationManager cm = ConfigurationManager.getConfigurationManager();
        final ColorSelection activeSelection = new ColorSelection(RadixExchangeSort.ACTIVE_COLOR, RadixExchangeSort.ACTIVE);
        activeSelection.addClass(this);
        cm.addColorSelection(activeSelection);
        final ColorSelection processedSelection = new ColorSelection(RadixExchangeSort.DOING_CHILDREN_COLOR, RadixExchangeSort.PROCESSED);
        processedSelection.addClass(this);
        cm.addColorSelection(processedSelection);
        final ColorSelection finishedSelection = new ColorSelection(RadixExchangeSort.FINISHED_COLOR, RadixExchangeSort.FINISHED);
        finishedSelection.addClass(this);
        cm.addColorSelection(finishedSelection);
        final ColorSelection waitingSelection = new ColorSelection(RadixExchangeSort.WAITING_COLOR, RadixExchangeSort.WAITING);
        waitingSelection.addClass(this);
        cm.addColorSelection(waitingSelection);
        cm.addColorSelectionListener(this);
    }
    
    protected void calculateColors(final Color[] colors, final int[] depthArray, final int depth) {
        for (int i = this.left; i <= this.right; ++i) {
            if (i >= 0 && i < colors.length) {
                if (colors[i] == null) {
                    colors[i] = this.getColor();
                    if (i >= 0 && i < depthArray.length) {
                        depthArray[i] = depth;
                    }
                }
                else if (i >= 0 && i < depthArray.length && depth > depthArray[i]) {
                    colors[i] = this.getColor();
                    depthArray[i] = depth;
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
        RadixExchangeSort.duplicateLabels = DuplicateLabel.createDuplicateLabels(temp);
    }
    
    public void display() {
        System.out.print(Messages.getString("RadixExchangeSort.10") + this.left + Messages.getString("RadixExchangeSort.11") + this.right + " || ");
        for (int i = 0; i < this.data.length; ++i) {
            System.out.print(this.data[i] + " ");
        }
        System.out.println("");
    }
    
    public void draw(final Graphics g, final Point initialPoint) {
        final Point pos = new Point(initialPoint.x + g.getFontMetrics().stringWidth(RadixExchangeSort.MSB_LABEL) + this.xTextBuffer, initialPoint.y);
        final ElementArray binaryElementArray = new ElementArray(pos.x, pos.y + this.yTextBuffer + this.bitLength * (g.getFontMetrics().getHeight() + this.yTextBuffer));
        for (int i = 0; i < this.data.length; ++i) {
            final BinaryElement binaryElement = new BinaryElement(i, RadixExchangeSort.BACKGROUND, RadixExchangeSort.BACKGROUND, this.data[i], new Point(0, 0), this.yTextBuffer, this.bitLength);
            binaryElement.setTextColor(RadixExchangeSort.TEXT_COLOR);
            binaryElementArray.setValue(i, binaryElement);
        }
        binaryElementArray.draw(g);
        g.setColor(RadixExchangeSort.TEXT_COLOR);
        for (int v = 0; v < this.bitLength; ++v) {
            g.drawString("" + v, (this.getRight() + 1) * (this.columWidth + this.columSpacing) + pos.x + this.xTextBuffer - g.getFontMetrics().stringWidth("" + v) / 2, pos.y + (this.bitLength - v) * (g.getFontMetrics().getHeight() + this.yTextBuffer));
        }
        g.drawString(RadixExchangeSort.MSB_LABEL, pos.x - g.getFontMetrics().stringWidth(RadixExchangeSort.MSB_LABEL) - this.xTextBuffer, pos.y + (g.getFontMetrics().getHeight() + this.yTextBuffer));
        g.drawString(RadixExchangeSort.LSB_LABEL, pos.x - g.getFontMetrics().stringWidth(RadixExchangeSort.LSB_LABEL) - this.xTextBuffer, pos.y + this.bitLength * (g.getFontMetrics().getHeight() + this.yTextBuffer));
        g.drawString(RadixExchangeSort.BIT_LABEL, (this.getRight() + 1) * (this.columWidth + this.columSpacing) + pos.x + this.xTextBuffer - g.getFontMetrics().stringWidth(RadixExchangeSort.BIT_LABEL) / 2, pos.y);
        if (RadixExchangeSort.currentBit >= 0) {
            final Line line = new Line(this.data.length * (this.columWidth + this.columSpacing) + pos.x + this.xTextBuffer + (this.columWidth + this.columSpacing) + 30, pos.y + (this.bitLength - RadixExchangeSort.currentBit) * (g.getFontMetrics().getHeight() + this.yTextBuffer) - g.getFontMetrics().getHeight() / 2, this.data.length * (this.columWidth + this.columSpacing) + pos.x + this.xTextBuffer + (this.columWidth + this.columSpacing), pos.y + (this.bitLength - RadixExchangeSort.currentBit) * (g.getFontMetrics().getHeight() + this.yTextBuffer) - g.getFontMetrics().getHeight() / 2);
            line.showArrowHead(true);
            line.setDistanceFromStartForArrowHead(-3);
            line.setArrowHeadWidth(5);
            g.setColor(Color.black);
            line.draw(g);
        }
        final Point point2;
        final Point point = point2 = new Point(pos.x, pos.y);
        point2.y = point2.y + (this.bitLength + 2) * (g.getFontMetrics().getHeight() + this.yTextBuffer);
        this.draw(g, point, pos);
    }
    
    protected void draw(final Graphics g, final Point pos, final Point recPoint) {
        g.setColor(this.getColor());
        g.fillRect(this.getLeft() * (this.columWidth + this.columSpacing) + pos.x - this.columWidth / 2, pos.y - g.getFontMetrics().getHeight(), (1 + this.getRight() - this.getLeft()) * (this.columWidth + this.columSpacing), g.getFontMetrics().getHeight());
        g.setColor(RadixExchangeSort.TEXT_COLOR);
        for (int i = this.getLeft(); i <= this.getRight(); ++i) {
            g.drawString(this.data[i] + "", i * (this.columWidth + this.columSpacing) + pos.x + this.columWidth / 2 - g.getFontMetrics().stringWidth(this.data[i] + "") / 2, pos.y);
        }
        if (this.isActive() && !this.isDoingChildren() && !this.isFinished()) {
            if (this.getI() != -10) {
                final StringMarker tempIMarker = new StringMarker("i", new Point(this.getI() * (this.columWidth + this.columSpacing) + pos.x + this.columWidth / 2, pos.y + g.getFontMetrics().getHeight() + this.yTextBuffer), RadixExchangeSort.TEXT_COLOR, RadixExchangeSort.ACTIVE_COLOR);
                tempIMarker.useDefaultWidth(false);
                tempIMarker.setWidth(this.columWidth);
                tempIMarker.draw(g);
            }
            if (this.getJ() != -10) {
                final StringMarker tempJMarker = new StringMarker("J", new Point(this.getJ() * (this.columWidth + this.columSpacing) + pos.x + this.columWidth / 2, pos.y + 2 * (g.getFontMetrics().getHeight() + this.yTextBuffer)), RadixExchangeSort.TEXT_COLOR, RadixExchangeSort.ACTIVE_COLOR);
                tempJMarker.useDefaultWidth(false);
                tempJMarker.setWidth(this.columWidth);
                tempJMarker.draw(g);
            }
            if (this.getRight() >= this.getLeft() && this.bit >= 0) {
                g.setColor(Color.black);
                g.drawRect(this.getLeft() * (this.columWidth + this.columSpacing) + recPoint.x - this.columWidth / 2, recPoint.y + (this.bitLength - this.bit) * (g.getFontMetrics().getHeight() + this.yTextBuffer) - g.getFontMetrics().getHeight() + this.yTextBuffer, (1 + this.getRight() - this.getLeft()) * (this.columWidth + this.columSpacing), g.getFontMetrics().getHeight());
            }
        }
        if (this.getLeftChild() != null) {
            this.getLeftChild().draw(g, new Point(pos.x, pos.y + 2 * g.getFontMetrics().getHeight() + 2 * this.yTextBuffer), recPoint);
        }
        if (this.getRightChild() != null) {
            this.getRightChild().draw(g, new Point(pos.x, pos.y + 2 * g.getFontMetrics().getHeight() + 2 * this.yTextBuffer), recPoint);
        }
    }
    
    public Vector<Object> generateQuestions() {
        return null;
    }
    
    protected void generateTweens(final MultipleTween tweens, final ElementArray elementArray, final int numberOfSteps) {
        for (int i = 0; i < this.swapRequests.size(); ++i) {
            final SwapRequest swapRequest = (SwapRequest)this.swapRequests.elementAt(i);
            final Element first = elementArray.getElement(swapRequest.getFirstId());
            final Element second = elementArray.getElement(swapRequest.getSecondId());
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
        final ElementArray elementArray = (ElementArray)((ElementArray)parameter);
        this.generateTweens(tweens, elementArray, numberOfSteps);
        return tweens;
    }
    
    public RadixExchangeSort getActive() {
        if (this.isActive() && !this.isDoingChildren()) {
            return this;
        }
        RadixExchangeSort temp = null;
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
    
    public int getArrayBuffer(final Graphics g) {
        return g.getFontMetrics().stringWidth(RadixExchangeSort.MSB_LABEL) + this.xTextBuffer;
    }
    
    protected int getBit(final int number, final int position, final int bitLength) {
        String binaryString = "";
        for (int i = 0; i < bitLength; ++i) {
            binaryString = binaryString + "0";
        }
        final String binary = Integer.toBinaryString(number);
        binaryString = binaryString.substring(0, binaryString.length() - binary.length()) + binary;
        String reverseString = "";
        for (int j = binaryString.length() - 1; j >= 0; --j) {
            reverseString = reverseString + String.valueOf(binaryString.charAt(j));
        }
        this.displayMessage("RadixExchangeSort.get Trying to make length " + bitLength + Messages.getString("RadixExchangeSort.19") + Integer.toBinaryString(number) + Messages.getString("RadixExchangeSort.20") + number + Messages.getString("RadixExchangeSort.21") + binaryString + Messages.getString("RadixExchangeSort.22") + reverseString);
        binaryString = reverseString;
        if (position >= 0 && position < binaryString.length()) {
            final String numberString = new Character(binaryString.charAt(position)).toString();
            try {
                final int temp = Integer.parseInt(numberString);
                this.displayMessage(Messages.getString("RadixExchangeSort.23") + number + Messages.getString("RadixExchangeSort.24") + binaryString + Messages.getString("RadixExchangeSort.25") + position + Messages.getString("RadixExchangeSort.26") + temp + Messages.getString("RadixExchangeSort.27") + bitLength);
                return temp;
            }
            catch (NumberFormatException e) {
                this.displayMessage(Messages.getString("RadixExchangeSort.28") + numberString + " " + e);
            }
        }
        this.displayMessage(Messages.getString("RadixExchangeSort.30") + position + Messages.getString("RadixExchangeSort.31") + number + Messages.getString("RadixExchangeSort.32") + binaryString);
        return -1;
    }
    
    public String getClassName() {
        return Messages.getString("RadixExchangeSort.65");
    }
    
    protected Color getColor() {
        Color color;
        if (this.isFinished()) {
            color = RadixExchangeSort.FINISHED_COLOR;
        }
        else if (this.isDoingChildren()) {
            color = RadixExchangeSort.DOING_CHILDREN_COLOR;
        }
        else if (this.isActive()) {
            color = RadixExchangeSort.ACTIVE_COLOR;
        }
        else {
            color = RadixExchangeSort.WAITING_COLOR;
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
    
    public ElementArray getElementArray(final Point location) {
        final ElementArray elementArray = new ElementArray(location.x, location.y);
        elementArray.setColumGap(this.columSpacing);
        elementArray.setElementWidth(this.columWidth);
        final Color[] colors = new Color[this.data.length];
        final int[] depthArray = new int[this.data.length];
        this.calculateColors(colors, depthArray, 0);
        for (int i = 0; i < this.data.length; ++i) {
            final VerticalBar verticalBar = new VerticalBar(i, this.data[i], colors[i], new Point(0, 0));
            verticalBar.setTextColor(RadixExchangeSort.TEXT_COLOR);
            elementArray.setValue(i, verticalBar);
            ((VerticalBar)elementArray.getElement(i)).setMarkersAboveValue(false);
            ((VerticalBar)elementArray.getElement(i)).setOffsetForValue(1);
            if (RadixExchangeSort.duplicateLabels[i] != null) {
                final StringMarker stringMarker = new StringMarker("" + this.data[i] + RadixExchangeSort.duplicateLabels[i]);
                stringMarker.setColor(RadixExchangeSort.TEXT_COLOR);
                stringMarker.setBackgroundColor(RadixExchangeSort.BACKGROUND);
                elementArray.getElement(i).addMarker(stringMarker);
            }
        }
        return elementArray;
    }
    
    public int getHeight(final Graphics g) {
        return this.getDepth(1) * (2 * (g.getFontMetrics().getHeight() + this.yTextBuffer)) + 2 * (this.yTextBuffer + g.getFontMetrics().getHeight()) + (this.bitLength + 2) * (g.getFontMetrics().getHeight() + this.yTextBuffer);
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
    
    public RadixExchangeSort getLeftChild() {
        return this.leftChild;
    }
    
    public int getRight() {
        return this.right;
    }
    
    public RadixExchangeSort getRightChild() {
        return this.rightChild;
    }
    
    public int getWidth(final Graphics g) {
        return this.data.length * (this.columWidth + this.columSpacing) + this.xTextBuffer + (this.columWidth + this.columSpacing) + 30 + (g.getFontMetrics().stringWidth(RadixExchangeSort.MSB_LABEL) + this.xTextBuffer);
    }
    
    public boolean hasQuestions() {
        return false;
    }
    
    protected void initialise(final boolean newSearch) {
        this.debugMode = false;
        this.active = false;
        this.processed = false;
        this.leftChild = null;
        this.rightChild = null;
        if (newSearch) {
            this.left = 0;
            this.right = this.data.length - 1;
            int max = 0;
            int maxPosition = 0;
            if (this.data.length > 0) {
                max = this.data[0];
            }
            for (int i = 0; i < this.data.length; ++i) {
                if (this.data[i] > max) {
                    max = this.data[i];
                    maxPosition = i;
                }
            }
            if (this.data.length > 0) {
                this.bitLength = Integer.toBinaryString(this.data[maxPosition]).length();
                this.bit = this.bitLength - 1;
            }
            this.createDuplicateLabels(this.data);
        }
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
    
    public void processColorSelection(final ColorSelection colorSelection) {
        final String atribute = colorSelection.getAtributeName();
        if (atribute.equalsIgnoreCase(ColorSelection.BACKGROUND)) {
            RadixExchangeSort.BACKGROUND = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR)) {
            RadixExchangeSort.TEXT_COLOR = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(RadixExchangeSort.ACTIVE)) {
            RadixExchangeSort.ACTIVE_COLOR = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(RadixExchangeSort.PROCESSED)) {
            RadixExchangeSort.DOING_CHILDREN_COLOR = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(RadixExchangeSort.FINISHED)) {
            RadixExchangeSort.FINISHED_COLOR = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(RadixExchangeSort.WAITING)) {
            RadixExchangeSort.WAITING_COLOR = colorSelection.getColor();
        }
    }
    
    public void reInitialise(final Object data) {
        this.data = (int[])((int[])data);
        this.initialise(true);
        this.setPosition("");
    }
    
    public void removeAllAnimationRequests() {
        this.swapRequests.removeAllElements();
        if (this.leftChild != null) {
            this.leftChild.removeAllAnimationRequests();
        }
        if (this.rightChild != null) {
            this.rightChild.removeAllAnimationRequests();
        }
    }
    
    public void removeAllQuestions() {
    }
    
    public void run() {
        if (!this.enabled) {
            return;
        }
        this.active = true;
        RadixExchangeSort.currentBit = this.bit;
        this.setPosition("1");
        this.setPosition("3");
        this.displayMessage(Messages.getString("RadixExchangeSort.35") + this.right + Messages.getString("RadixExchangeSort.36") + this.left + Messages.getString("RadixExchangeSort.37") + this.bit + Messages.getString("RadixExchangeSort.38") + this.bitLength);
        if (this.right > this.left && this.bit >= 0) {
            this.setPosition("3.1");
            this.i = this.left;
            this.j = this.right;
            this.setPosition("3.1.1");
            this.setPosition("3.1.2");
            while (this.i != this.j) {
                this.setPosition("3.1.2.1");
                while (this.getBit(this.data[this.i], this.bit, this.bitLength) == 0 && this.i < this.j) {
                    this.setPosition("3.1.2.1.1");
                    this.i = this.i + 1;
                    this.setPosition("3.1.2.1.1.1");
                }
                this.setPosition("3.1.2.1.2");
                this.setPosition("3.1.2.2");
                while (this.getBit(this.data[this.j], this.bit, this.bitLength) == 1 && this.j > this.i) {
                    this.setPosition("3.1.2.2.1");
                    this.j = this.j - 1;
                    this.setPosition("3.1.2.2.1.1");
                }
                this.setPosition("3.1.2.2.2");
                this.swap(this.i, this.j);
                this.setPosition("3.1.2.3");
                if (this.i != this.j) {
                    this.setPosition("3.1.2");
                }
            }
            this.setPosition("3.1.4");
            if (this.getBit(this.data[this.right], this.bit, this.bitLength) == 0) {
                this.j = this.j + 1;
                this.setPosition("3.1.4.1");
            }
            this.setPosition("3.1.4.2");
            this.leftChild = new RadixExchangeSort(this.algorithmThread, this.data, false);
            this.leftChild.enabled = this.enabled;
            this.leftChild.left = this.left;
            this.leftChild.right = this.j - 1;
            this.leftChild.bit = this.bit - 1;
            this.leftChild.bitLength = this.bitLength;
            if (this.algorithmThread == null) {
                System.out.println(Messages.getString("RadixExchangeSort.55"));
            }
            if (this.leftChild.algorithmThread == null) {
                System.out.println(Messages.getString("RadixExchangeSort.56"));
            }
            this.rightChild = new RadixExchangeSort(this.algorithmThread, this.data, false);
            this.rightChild.enabled = this.enabled;
            this.rightChild.left = this.j;
            this.rightChild.right = this.right;
            this.rightChild.bit = this.bit - 1;
            this.rightChild.bitLength = this.bitLength;
            this.doingChildren = true;
            this.setPosition("3.2");
            this.setPosition("3.2.1");
            this.displayMessage(Messages.getString("RadixExchangeSort.59"));
            this.leftChild.run();
            this.leftChild.processed = true;
            RadixExchangeSort.currentBit = this.bit;
            this.setPosition("3.3");
            this.setPosition("3.3.1");
            this.displayMessage(Messages.getString("RadixExchangeSort.62"));
            this.rightChild.run();
            this.rightChild.processed = true;
        }
        this.processed = true;
        RadixExchangeSort.currentBit = this.bit;
        this.setPosition("5");
    }
    
    public void setEnabled(final boolean state) {
        this.enabled = state;
        if (this.rightChild != null) {
            this.rightChild.setEnabled(state);
        }
        if (this.leftChild != null) {
            this.leftChild.setEnabled(state);
        }
    }
    
    protected void swap(final int j, final int i) {
        final int temp = this.data[i];
        this.data[i] = this.data[j];
        this.data[j] = temp;
        if (i != j) {
            this.swapRequests.addElement(new SwapRequest(j, i));
        }
    }
    
    static {
        RadixExchangeSort.currentBit = -1;
        MSB_LABEL = Messages.getString("RadixExchangeSort.0");
        LSB_LABEL = Messages.getString("RadixExchangeSort.1");
        BIT_LABEL = Messages.getString("RadixExchangeSort.2");
        RadixExchangeSort.WAITING_COLOR = Color.pink;
        RadixExchangeSort.HIGHLIGHT_COLOR = Color.green;
        RadixExchangeSort.DOING_CHILDREN_COLOR = Color.red.darker();
        ACTIVE = Messages.getString("RadixExchangeSort.5");
        PROCESSED = Messages.getString("RadixExchangeSort.6");
        FINISHED = Messages.getString("RadixExchangeSort.7");
        WAITING = Messages.getString("RadixExchangeSort.8");
        RadixExchangeSort.ACTIVE_COLOR = Color.red;
        RadixExchangeSort.FINISHED_COLOR = Color.gray;
        RadixExchangeSort.BACKGROUND = Color.white;
        RadixExchangeSort.TEXT_COLOR = Color.black;
    }
}
