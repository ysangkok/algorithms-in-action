import localization.*;
import java.util.*;
import java.awt.*;
import com.cim.AIA.*;

public class DistributionCounting extends Algorithm implements ColorSelectionListener
{
    public static final int EMPTY_MARKER = -10;
    protected static final String HIGHLIGHT_COLOR;
    protected static final String NODE_COLOR;
    protected boolean allowGetAuxiliaryArray;
    protected int[] data;
    protected int[] symbolArray;
    protected int[] auxiliary;
    protected int dataArrow;
    protected int dataMarker;
    protected int symbolMarker;
    protected int auxiliaryMarker;
    protected Color textColor;
    protected Color highlightColor;
    protected Color nodeColor;
    protected Color background;
    protected boolean drawSymbolArrayAndLines;
    protected String[] dataDuplicateLabels;
    protected String[] auxiliaryDuplicateLabels;
    
    public static void main(final String[] argv) {
        final int[] data = new int[] { 2, 4, 5, 6, 6, 3, 2, 1 };
        final DistributionCounting distCount = new DistributionCounting(null, data);
        distCount.run();
    }
    
    public DistributionCounting(final AlgorithmThread algorithmThread, final Object data) {
        super(algorithmThread, data);
        this.allowGetAuxiliaryArray = false;
        this.dataArrow = -10;
        this.dataMarker = -10;
        this.symbolMarker = -10;
        this.auxiliaryMarker = -10;
        this.textColor = Color.black;
        this.highlightColor = Color.yellow;
        this.nodeColor = Color.orange;
        this.background = Color.white;
        this.drawSymbolArrayAndLines = true;
        this.data = (int[])((int[])data);
        this.initialise();
        final ConfigurationManager cm = ConfigurationManager.getConfigurationManager();
        final ColorSelection highlightSelection = new ColorSelection(this.highlightColor, DistributionCounting.HIGHLIGHT_COLOR);
        highlightSelection.addClass(this);
        cm.addColorSelection(highlightSelection);
        final ColorSelection nodeSelection = new ColorSelection(this.nodeColor, DistributionCounting.NODE_COLOR);
        nodeSelection.addClass(this);
        cm.addColorSelection(nodeSelection);
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
    
    protected Vector<Object> generateQuestions() {
        return null;
    }
    
    public MultipleTween generateTweens(final Paintable paintable, final Object parameter, final int numberOfSteps) {
        return null;
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
        elementArray.setElementWidth(20);
        for (int i = 0; i < this.auxiliary.length; ++i) {
            final Node node = new Node(new Integer(this.auxiliary[i]), i);
            node.setBackgroundColor(this.nodeColor);
            node.setTextColor(this.textColor);
            if (this.auxiliary[i] == -10) {
                node.showObject(false);
            }
            if (this.auxiliaryMarker != -10 && this.auxiliaryMarker == i) {
                node.setHighlightColor(this.highlightColor);
                node.highlight();
            }
            node.addMarker(new StringMarker(i + "", new Point(), this.textColor, this.background));
            if (this.auxiliaryDuplicateLabels[i] != null) {
                node.addMarker(new StringMarker(this.auxiliaryDuplicateLabels[i] + "", new Point(), this.textColor, this.background));
            }
            elementArray.setValue(i, node);
        }
        return elementArray;
    }
    
    public String getClassName() {
        return Messages.getString("DistributionCounting.2");
    }
    
    public int getDataArrow() {
        return this.dataArrow;
    }
    
    public ElementArray getDataElementArray() {
        if (this.data == null || this.data.length <= 0) {
            return null;
        }
        final ElementArray elementArray = new ElementArray(0, 0);
        elementArray.setColumGap(0);
        elementArray.setElementWidth(20);
        for (int i = 0; i < this.data.length; ++i) {
            final Node node = new Node(new Integer(this.data[i]), i);
            node.setBackgroundColor(this.nodeColor);
            node.setTextColor(this.textColor);
            if (this.data[i] == -10) {
                node.showObject(false);
            }
            if (this.dataMarker != -10 && this.dataMarker == i) {
                node.setHighlightColor(this.highlightColor);
                node.highlight();
            }
            node.addMarker(new StringMarker(i + "", new Point(), this.textColor, this.background));
            if (this.dataDuplicateLabels[i] != null) {
                node.addMarker(new StringMarker(this.dataDuplicateLabels[i] + "", new Point(), this.textColor, this.background));
            }
            elementArray.setValue(i, node);
        }
        return elementArray;
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
        if (this.data != null && this.data.length > 0) {
            int max = this.data[0];
            for (int i = 0; i < this.data.length; ++i) {
                if (this.data[i] > max) {
                    max = this.data[i];
                }
            }
            this.symbolArray = new int[max + 1];
        }
        for (int j = 0; j < this.symbolArray.length; ++j) {
            this.symbolArray[j] = -10;
        }
        this.auxiliary = new int[this.data.length];
        for (int j = 0; j < this.auxiliary.length; ++j) {
            this.auxiliary[j] = -10;
        }
        this.drawSymbolArrayAndLines = true;
        final Integer[] temp = new Integer[this.data.length];
        for (int i = 0; i < this.data.length; ++i) {
            temp[i] = new Integer(this.data[i]);
        }
        this.dataDuplicateLabels = DuplicateLabel.createDuplicateLabels(temp, true);
        this.auxiliaryDuplicateLabels = new String[this.dataDuplicateLabels.length];
    }
    
    public void processColorSelection(final ColorSelection colorSelection) {
        final String atribute = colorSelection.getAtributeName();
        if (atribute.equalsIgnoreCase(ColorSelection.BACKGROUND)) {
            this.background = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR)) {
            this.textColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(DistributionCounting.HIGHLIGHT_COLOR)) {
            this.highlightColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(DistributionCounting.NODE_COLOR)) {
            this.nodeColor = colorSelection.getColor();
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
        this.setPosition("0");
        this.setPosition("1");
        this.setPosition("1.1");
        for (int j = 0; j < this.symbolArray.length; ++j) {
            this.symbolMarker = j;
            this.setPosition("1.1.1");
            this.symbolArray[j] = 0;
            this.setPosition("1.1.1.1");
        }
        this.symbolMarker = -10;
        this.setPosition("1.1.2");
        this.setPosition("1.2");
        for (int i = 0; i < this.data.length; ++i) {
            this.dataArrow = i;
            this.setPosition("1.2.1");
            this.symbolMarker = this.data[i];
            final int[] symbolArray = this.symbolArray;
            final int n = this.data[i];
            symbolArray[n] = symbolArray[n] + 1;
            this.setPosition("1.2.1.1");
            this.symbolMarker = -10;
        }
        this.dataArrow = -10;
        this.setPosition("1.2.2");
        this.setPosition("1.3");
        for (int k = 1; k < this.symbolArray.length; ++k) {
            this.symbolMarker = k;
            this.setPosition("1.3.1");
            this.symbolArray[k] = this.symbolArray[k - 1] + this.symbolArray[k];
            this.setPosition("1.3.1.1");
        }
        this.setPosition("1.3.2");
        this.setPosition("1.4");
        for (int k = this.symbolArray.length - 1; k > 0; --k) {
            this.symbolMarker = k;
            this.setPosition("1.4.1");
            this.symbolArray[k] = this.symbolArray[k - 1];
            this.setPosition("1.4.1.1");
        }
        this.symbolMarker = 0;
        this.symbolArray[0] = 0;
        this.setPosition("1.4.2");
        this.symbolMarker = -10;
        this.allowGetAuxiliaryArray = true;
        this.setPosition("2");
        for (int l = 0; l < this.data.length; ++l) {
            this.dataMarker = l;
            this.setPosition("2.1");
            this.setPosition("3.1");
            this.symbolMarker = this.data[l];
            this.setPosition("3.1.1");
            this.auxiliary[this.symbolArray[this.data[l]]] = this.data[l];
            this.auxiliaryMarker = this.symbolArray[this.data[l]];
            this.auxiliaryDuplicateLabels[this.auxiliaryMarker] = this.dataDuplicateLabels[l];
            this.setPosition("3.1.1.1");
            this.auxiliaryMarker = -10;
            this.setPosition("3.2");
            this.setPosition("3.2.1");
            final int[] symbolArray2 = this.symbolArray;
            final int n2 = this.data[l];
            symbolArray2[n2] = symbolArray2[n2] + 1;
            this.setPosition("3.2.1.1");
            this.symbolMarker = -10;
        }
        this.dataMarker = -10;
        this.drawSymbolArrayAndLines = false;
        this.setPosition("5");
        for (int l = 0; l < this.auxiliary.length; ++l) {
            this.auxiliaryMarker = l;
            this.setPosition("5.1");
            this.dataMarker = l;
            this.data[l] = this.auxiliary[l];
            this.dataDuplicateLabels[l] = this.auxiliaryDuplicateLabels[l];
            this.setPosition("5.1.1");
            this.dataMarker = -10;
        }
        this.auxiliaryMarker = -10;
        this.setPosition("5.2");
    }
    
    static {
        HIGHLIGHT_COLOR = Messages.getString("DistributionCounting.0");
        NODE_COLOR = Messages.getString("DistributionCounting.1");
    }
}
