import localization.*;
import java.awt.*;
import com.cim.AIA.*;

public class InsertionSortCanvas extends AlgorithmCanvas
{
    private static final long serialVersionUID = -2960790327721856295L;
    protected static final String I_MARKER = "i";
    protected static final String J_MARKER = "J";
    protected static final String VAL_MARKER;
    protected static final String FINAL_MARKER;
    protected static final String ELEMENT_MARKER = "A[j-1]";
    protected int xBuffer;
    protected int yBuffer;
    protected int boxWidth;
    protected InsertionSort insertionSort;
    protected ElementArray elementArray;
    protected StringMarker iMarker;
    protected Line finalLine;
    protected Line iLine;
    protected Line elementLine;
    protected StringMarker jMarker;
    protected int value;
    protected Color backgroundColor;
    protected Color textColor;
    protected boolean needInitialisation;
    protected boolean tweening;
    
    public InsertionSortCanvas() {
        super();
        this.xBuffer = 80;
        this.yBuffer = 180;
        this.boxWidth = 20;
        this.value = -1;
        this.backgroundColor = Color.white;
        this.textColor = Color.black;
        this.needInitialisation = false;
        this.tweening = false;
    }
    
    public void displayAlgorithm(final Graphics g) {
        if (this.needInitialisation) {
            this.processRepaintEvent(new RepaintEvent(this, this.insertionSort));
            this.needInitialisation = false;
            return;
        }
        if (this.insertionSort != null) {
            this.elementArray.draw(g);
            if (this.iMarker != null) {
                this.iMarker.draw(g);
            }
            if (this.jMarker != null && !this.insertionSort.getDontPaintJMarker()) {
                this.jMarker.draw(g);
            }
            if (this.iLine != null) {
                this.iLine.draw(g);
            }
            if (this.finalLine != null) {
                this.finalLine.draw(g);
            }
            if (this.elementLine != null) {
                this.elementLine.draw(g);
            }
            final Point boxPoint = new Point(this.elementArray.getLocation().x + this.elementArray.getWidth() + this.elementArray.getElementWidth() + this.elementArray.getColumGap(), this.yBuffer + g.getFontMetrics().getHeight());
            g.drawRect(boxPoint.x, boxPoint.y, this.boxWidth, this.boxWidth);
            g.setColor(this.textColor);
            g.drawString(Messages.getString("InsertionSortCanvas.2"), boxPoint.x + this.boxWidth / 2 - g.getFontMetrics().stringWidth(Messages.getString("InsertionSortCanvas.3")) / 2, boxPoint.y);
            if (this.insertionSort.getVal() != -10) {
                g.setColor(InsertionSort.activeArrayColor);
                g.fillRect(boxPoint.x, boxPoint.y, this.boxWidth, this.boxWidth);
                g.setColor(this.textColor);
                this.value = this.insertionSort.getVal();
                g.drawString("" + this.value, boxPoint.x + this.boxWidth / 2 - g.getFontMetrics().stringWidth("" + this.value) / 2, boxPoint.y + this.boxWidth / 2 + g.getFontMetrics().getHeight() / 2);
                if (!this.insertionSort.getDontPaintValMarker() && this.elementLine != null) {
                    final Point from = new Point(boxPoint.x + this.boxWidth / 2, 50);
                    final Point to = new Point(boxPoint.x + this.boxWidth / 2, boxPoint.y - this.boxWidth);
                    final Line line = new Line(from, to);
                    line.showArrowHead(true);
                    line.setDistanceFromStartForArrowHead(-3);
                    line.draw(g);
                }
            }
        }
    }
    
    protected void handleColorSelection(final ColorSelection colorSelection) {
        final String atribute = colorSelection.getAtributeName();
        if (atribute.equalsIgnoreCase(ColorSelection.BACKGROUND)) {
            this.backgroundColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR)) {
            this.textColor = colorSelection.getColor();
        }
    }
    
    protected void handleFontSelection(final FontSelection fontSelection) {
    }
    
    protected void initialise() {
        final Dimension currentSize = this.getSize();
        currentSize.width = this.elementArray.getWidth() + 50;
        this.setSize(currentSize);
        this.elementArray.setLocation(this.getSize().width / 2 - this.elementArray.getWidth() / 2, 200);
        this.iMarker = this.initialiseMarker(this.insertionSort.getI(), "i", 3);
        this.jMarker = this.initialiseMarker(this.insertionSort.getJ(), "J", 4);
        this.iLine = this.initialiseLine(this.insertionSort.getI(), "i");
        this.finalLine = this.initialiseLine(this.insertionSort.getFinalPosition(), InsertionSortCanvas.FINAL_MARKER);
        this.elementLine = this.initialiseLine(this.insertionSort.getElementPosition(), "A[j-1]");
    }
    
    protected Line initialiseLine(final int pos, final String string) {
        if (pos != -10) {
            final VerticalBar element = (VerticalBar)this.elementArray.getElement(pos);
            if (element != null) {
                Point to;
                Point from;
                if (string == "i") {
                    to = new Point(element.getPosition().x + element.getWidth() / 2, element.getPosition().y + 20);
                    from = new Point(element.getPosition().x + element.getWidth() / 2, element.getPosition().y + 30);
                }
                else {
                    to = new Point(element.getPosition().x + element.getWidth() / 2, element.getPosition().y - element.getHeight());
                    from = new Point(element.getPosition().x + element.getWidth() / 2, 50);
                }
                final Line line = new Line(from, to);
                line.showArrowHead(true);
                line.setDistanceFromStartForArrowHead(-3);
                if (string != "i") {
                    line.setLabel(string);
                }
                line.setDistanceFromStartForLabel(-1);
                line.setXLabelOffset(-1 * this.getGraphics().getFontMetrics().stringWidth(string) / 2);
                line.setYLabelOffset(-1 * this.getGraphics().getFontMetrics().getHeight());
                return line;
            }
        }
        return null;
    }
    
    protected StringMarker initialiseMarker(final int pos, final String string, final int level) {
        if (pos != -10) {
            final VerticalBar element = (VerticalBar)this.elementArray.getElement(pos);
            if (element != null) {
                final Point temp = new Point(element.getPosition().x + element.getWidth() / 2, element.getPosition().y + 5 + level * this.getGraphics().getFontMetrics().getHeight());
                final StringMarker stringMarker = new StringMarker(string, temp);
                stringMarker.setColor(this.textColor);
                stringMarker.setBackgroundColor(this.backgroundColor);
                return stringMarker;
            }
        }
        return null;
    }
    
    public void processRepaintEvent(final RepaintEvent e) {
        if (e.paramObj != null) {
            this.insertionSort = (InsertionSort)((InsertionSort)e.paramObj);
            final Object o = null;
            this.iMarker = o;
            this.jMarker = o;
            if (this.getGraphics() == null) {
                this.needInitialisation = true;
                return;
            }
            this.elementArray = this.insertionSort.getElementArray(this.getGraphics());
            this.initialise();
            this.addTween(this.insertionSort.generateTweens(this, this.elementArray, this.numberOfTweenSteps));
            this.tweening = true;
            if (this.tweens.getNumberOfTweens() > 0) {
                this.tweens.run();
            }
            this.tweening = false;
            this.insertionSort.removeAllAnimationRequests();
        }
        this.repaint();
    }
    
    static {
        VAL_MARKER = Messages.getString("InsertionSortCanvas.0");
        FINAL_MARKER = Messages.getString("InsertionSortCanvas.1");
    }
}
