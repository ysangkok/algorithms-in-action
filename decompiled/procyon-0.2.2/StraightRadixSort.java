import localization.*;
import java.util.*;
import java.awt.*;
import com.cim.AIA.*;

public class StraightRadixSort extends Algorithm implements ColorSelectionListener
{
    public static final int MARKER_SPACING = 4;
    public static final int BORDER_THICKNESS = 2;
    public static final int SCALE_FACTOR = 5;
    public static final int COLUM_GAP = 5;
    public static final int COLUM_WIDTH = 20;
    public static final int ADDITIONAL_MARKERS = 2;
    protected static final int CLEAR = 1;
    protected static final int WAITING = 2;
    protected static final int ACTIVE = 3;
    protected static final int PROCESSED = 4;
    public static final int EMPTY_MARKER = -10;
    protected static final String HIGHLIGHT_COLOR;
    protected static final String NODE_COLOR;
    protected static final String ACTIVE_COLOR;
    protected static final String WAITING_COLOR;
    protected static final String PROCESSED_COLOR;
    protected boolean allowGetAuxiliaryArray;
    protected int[] data;
    protected int[][] dataToSort;
    protected int[] symbolArray;
    protected int[] auxiliary;
    protected int dataArrow;
    protected int auxiliaryArrow;
    protected int dataMarker;
    protected int symbolMarker;
    protected int auxiliaryMarker;
    protected Color nodeColor;
    protected Color textColor;
    protected Color highlightColor;
    protected Color activeColor;
    protected Color waitingColor;
    protected Color processedColor;
    protected Color background;
    protected boolean drawSymbolArrayAndLines;
    protected String[] dataDuplicateLabels;
    protected String[] auxiliaryDuplicateLabels;
    protected int[] extraAuxiliaryData;
    protected int[][] auxiliaryDataToSort;
    protected int bit;
    protected int[] colors;
    protected int bitLength;
    protected String[] colorStrings;
    protected Vector<SplitMarker> splitMarkers;
    
    public static void main(final String[] argv) {
        final int[] data = new int[] { 2, 4, 5, 6, 6, 3, 2, 1 };
        final StraightRadixSort distCount = new StraightRadixSort(null, data);
        distCount.run();
    }
    
    public StraightRadixSort(final AlgorithmThread algorithmThread, final Object data) {
        super(algorithmThread, data);
        this.allowGetAuxiliaryArray = false;
        this.dataArrow = -10;
        this.auxiliaryArrow = -10;
        this.dataMarker = -10;
        this.symbolMarker = -10;
        this.auxiliaryMarker = -10;
        this.nodeColor = Color.orange;
        this.textColor = Color.black;
        this.highlightColor = Color.yellow;
        this.activeColor = Color.red;
        this.waitingColor = Color.pink;
        this.processedColor = Color.gray;
        this.background = Color.white;
        this.drawSymbolArrayAndLines = true;
        this.bit = -10;
        this.bitLength = 0;
        this.colorStrings = new String[] { StraightRadixSort.NODE_COLOR, StraightRadixSort.HIGHLIGHT_COLOR, StraightRadixSort.WAITING_COLOR, StraightRadixSort.ACTIVE_COLOR, StraightRadixSort.PROCESSED_COLOR };
        this.splitMarkers = new Vector();
        this.data = (int[])((int[])data);
        final Color[] colorSelection = new Color[] { this.nodeColor, this.highlightColor, this.waitingColor, this.activeColor, this.processedColor };
        this.initialise();
        final ConfigurationManager cm = ConfigurationManager.getConfigurationManager();
        for (int i = 0; i < this.colorStrings.length; ++i) {
            final ColorSelection nodeSelection = new ColorSelection(colorSelection[i], this.colorStrings[i]);
            nodeSelection.addClass(this);
            cm.addColorSelection(nodeSelection);
        }
        cm.addColorSelectionListener(this);
    }
    
    public synchronized void calculateLines(final Paintable paintable, final ElementArray symbolElementArray, final ElementArray auxiliaryElementArray) {
        if (!this.drawSymbolArrayAndLines) {
            return;
        }
        for (int i = 0; i < symbolElementArray.getSize(); ++i) {
            final Node from = (Node)symbolElementArray.getElement(i);
            if (from != null && from.getShowObject()) {
                final Element to = auxiliaryElementArray.getElement(((Integer)((Integer)from.getObject())).intValue());
                if (to != null) {
                    final Line line = new Line(0, 0, 0, 0);
                    final Point fromPoint = new Point(from.getPosition().x + from.getWidth() / 2, from.getPosition().y + from.getHeight());
                    final Point toPoint = new Point(to.getPosition().x + to.getWidth() / 2, to.getPosition().y);
                    line.setStartPosition(fromPoint);
                    line.setEndPosition(toPoint);
                    line.showArrowHead(true);
                    line.setDistanceFromStartForArrowHead(-3);
                    if (paintable != null) {
                        paintable.addDrawable(line);
                    }
                }
            }
        }
    }
    
    protected Vector<E> generateQuestions() {
        return null;
    }
    
    public MultipleTween generateTweens(final Paintable paintable, final Object parameter, final int numberOfSteps) {
        return null;
    }
    
    public int getAuxiliaryArrow() {
        return this.auxiliaryArrow;
    }
    
    public ElementArray getAuxiliaryElementArray() {
        if (!this.allowGetAuxiliaryArray) {
            return null;
        }
        if (this.auxiliary == null || this.auxiliary.length <= 0) {
            return null;
        }
        final ElementArray elementArray = new ElementArray(0, 0);
        elementArray.setColumGap(0);
        elementArray.setElementWidth(25);
        for (int i = 0; i < this.auxiliary.length; ++i) {
            final Node node = new Node(new Integer(0), i);
            node.setBackgroundColor(this.nodeColor);
            node.setTextColor(this.textColor);
            node.setAdditionalMarkerSpacing(4);
            if (this.extraAuxiliaryData != null && this.extraAuxiliaryData[i] != -10) {
                node.setObject(new Integer(this.extraAuxiliaryData[i]));
            }
            if (this.auxiliary[i] == -10) {
                node.showObject(false);
            }
            if (this.auxiliaryMarker != -10 && this.auxiliaryMarker == i) {
                node.setHighlightColor(this.highlightColor);
                node.highlight();
            }
            node.addMarker(new StringMarker(i + "", new Point(), this.textColor, this.background));
            if (this.auxiliaryDuplicateLabels != null && this.auxiliaryDuplicateLabels[i] != null) {
                node.addMarker(new StringMarker(this.auxiliaryDuplicateLabels[i] + "", new Point(), this.textColor, this.background));
            }
            if (this.extraAuxiliaryData != null && this.extraAuxiliaryData[i] != -10) {
                int j = 0;
                while (j < this.auxiliaryDataToSort[i].length) {
                    final Color color = this.getColor(j);
                    node.addMarker(new StringMarker(this.auxiliaryDataToSort[i][j] + "", new Point(), this.textColor, color));
                    ++j;
                }
            }
            elementArray.setValue(i, node);
        }
        return elementArray;
    }
    
    public int getBit() {
        return this.bit;
    }
    
    protected int[] getBit(final int number, final int bitLength) {
        String binaryString = "";
        for (int i = 0; i < bitLength; ++i) {
            binaryString = binaryString + "0";
        }
        final String binary = Integer.toBinaryString(number);
        binaryString = binaryString.substring(0, binaryString.length() - binary.length()) + binary;
        final int[] tempData = new int[bitLength];
        for (int j = 0; j < binaryString.length(); ++j) {
            final String numberString = new Character(binaryString.charAt(j)).toString();
            try {
                final int temp = Integer.parseInt(numberString);
                tempData[j] = temp;
            }
            catch (NumberFormatException e) {
                this.displayMessage("RadixExchangeSort.getBit Error in " + numberString + " " + e);
                return null;
            }
        }
        return tempData;
    }
    
    protected int getBit(final int number, final int position, final int bitLength) {
        final int[] temp = this.getBit(number, bitLength);
        if (temp != null && position >= 0 && position < temp.length) {
            return temp[position];
        }
        return -1;
    }
    
    public int getBitLength() {
        return this.bitLength;
    }
    
    public String getClassName() {
        return "StraightRadixSort";
    }
    
    public Color getColor(final int bit) {
        Color color = this.background;
        if (bit >= 0 && bit < this.colors.length) {
            switch (this.colors[bit]) {
                case 1: {
                    color = this.background;
                    break;
                }
                case 2: {
                    color = this.waitingColor;
                    break;
                }
                case 3: {
                    color = this.activeColor;
                    break;
                }
                case 4: {
                    color = this.waitingColor;
                    if (this.bit < 0) {
                        color = this.processedColor;
                        break;
                    }
                    break;
                }
            }
        }
        return color;
    }
    
    public int getDataArrow() {
        return this.dataArrow;
    }
    
    public ElementArray getDataElementArray() {
        if (this.data == null || this.data.length <= 0) {
            return null;
        }
        final ElementArray elementArray = new ElementArray(0, 0);
        elementArray.setColumGap(5);
        elementArray.setElementWidth(20);
        for (int i = 0; i < this.dataToSort.length; ++i) {
            final VerticalBar element = new VerticalBar(i, this.data[i], this.nodeColor, Color.black, new Point(0, 0));
            element.setTextColor(this.textColor);
            element.setDrawValueMarker(false);
            element.setAdditionalMarkerSpacing(4);
            element.setThickness(2);
            element.setScaleFactor(5.0f);
            if (this.dataMarker != -10 && i == this.dataMarker) {
                element.setColor(this.highlightColor);
            }
            element.addMarker(new StringMarker(this.data[i] + "", new Point(), this.textColor, this.background));
            if (this.dataDuplicateLabels != null && this.dataDuplicateLabels[i] != null) {
                element.addMarker(new StringMarker(this.dataDuplicateLabels[i] + "", new Point(), this.textColor, this.background));
            }
            int j = 0;
            while (j < this.dataToSort[i].length) {
                final Color color = this.getColor(j);
                element.addMarker(new StringMarker(this.dataToSort[i][j] + "", new Point(), this.textColor, color));
                ++j;
            }
            elementArray.setValue(i, element);
        }
        return elementArray;
    }
    
    public Vector<SplitMarker> getSplitValues() {
        return this.splitMarkers;
    }
    
    public ElementArray getSymbolElementArray() {
        if (this.symbolArray == null || this.symbolArray.length <= 0) {
            return null;
        }
        if (!this.drawSymbolArrayAndLines) {
            return null;
        }
        final ElementArray elementArray = new ElementArray(0, 0);
        elementArray.setColumGap(0);
        elementArray.setElementWidth(20);
        for (int i = 0; i < this.symbolArray.length; ++i) {
            final Node node = new Node(new Integer(this.symbolArray[i]), i);
            node.setBackgroundColor(this.nodeColor);
            node.setTextColor(this.textColor);
            if (this.symbolMarker != -10 && this.symbolMarker == i) {
                node.setHighlightColor(this.highlightColor);
                node.highlight();
            }
            if (this.symbolArray[i] == -10) {
                node.showObject(false);
            }
            node.addMarker(new StringMarker(i + "", new Point(), this.textColor, this.background));
            node.setMarkersBelowValue(false);
            elementArray.setValue(i, node);
        }
        return elementArray;
    }
    
    protected boolean hasQuestions() {
        return false;
    }
    
    protected void initialise() {
        this.allowGetAuxiliaryArray = false;
        this.splitMarkers = new Vector();
        int maxPosition = 0;
        if (this.data != null && this.data.length > 0) {
            for (int i = 0; i < this.data.length; ++i) {
                if (this.data[i] > this.data[maxPosition]) {
                    maxPosition = i;
                }
            }
        }
        this.symbolArray = new int[2];
        if (this.data.length > 0) {
            this.bitLength = Integer.toBinaryString(this.data[maxPosition]).length();
        }
        this.dataToSort = new int[][this.data.length];
        for (int i = 0; i < this.dataToSort.length; ++i) {
            this.dataToSort[i] = new int[this.bitLength];
        }
        for (int i = 0; i < this.dataToSort.length; ++i) {
            this.dataToSort[i] = this.getBit(this.data[i], this.bitLength);
        }
        for (int i = 0; i < this.symbolArray.length; ++i) {
            this.symbolArray[i] = -10;
        }
        this.auxiliary = new int[this.data.length];
        for (int i = 0; i < this.auxiliary.length; ++i) {
            this.auxiliary[i] = -10;
        }
        this.drawSymbolArrayAndLines = true;
        final Integer[] temp = new Integer[this.data.length];
        for (int j = 0; j < this.data.length; ++j) {
            temp[j] = new Integer(this.data[j]);
        }
        this.dataDuplicateLabels = DuplicateLabel.createDuplicateLabels(temp, true);
        this.bit = -10;
        this.colors = new int[this.bitLength];
        for (int j = 0; j < this.colors.length; ++j) {
            this.colors[j] = 1;
        }
    }
    
    public void processColorSelection(final ColorSelection colorSelection) {
        final String atribute = colorSelection.getAtributeName();
        for (int i = 0; i < this.colorStrings.length; ++i) {
            if (atribute.equalsIgnoreCase(this.colorStrings[i])) {
                switch (i) {
                    case 0: {
                        this.nodeColor = colorSelection.getColor();
                        break;
                    }
                    case 1: {
                        this.highlightColor = colorSelection.getColor();
                        break;
                    }
                    case 2: {
                        this.waitingColor = colorSelection.getColor();
                        break;
                    }
                    case 3: {
                        this.activeColor = colorSelection.getColor();
                        break;
                    }
                    case 4: {
                        this.processedColor = colorSelection.getColor();
                        break;
                    }
                }
            }
        }
        if (atribute.equalsIgnoreCase(ColorSelection.BACKGROUND)) {
            this.background = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR)) {
            this.textColor = colorSelection.getColor();
        }
    }
    
    public void reInitialise(final Object data) {
        this.data = (int[])((int[])data);
        this.initialise();
    }
    
    protected void removeAllAnimationRequests() {
    }
    
    protected void removeAllQuestions() {
    }
    
    protected void run() {
        // java.lang.IllegalArgumentException: Argument 'index' must be in the range [0, 0].
        //     at com.strobel.core.VerifyArgument.inRange(VerifyArgument.java:332)
        //     at com.strobel.assembler.ir.Instruction.getOperand(Instruction.java:113)
        //     at com.strobel.assembler.ir.Instruction.accept(Instruction.java:547)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:393)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:896)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:64)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:69)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:618)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:520)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:456)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:441)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:158)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:55)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:188)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:146)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:91)
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static {
        HIGHLIGHT_COLOR = Messages.getString("StraightRadixSort.0");
        NODE_COLOR = Messages.getString("StraightRadixSort.1");
        ACTIVE_COLOR = Messages.getString("StraightRadixSort.2");
        WAITING_COLOR = Messages.getString("StraightRadixSort.3");
        PROCESSED_COLOR = Messages.getString("StraightRadixSort.4");
    }
}
