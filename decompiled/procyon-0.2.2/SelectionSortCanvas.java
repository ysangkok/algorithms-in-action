import localization.*;
import java.awt.*;
import com.cim.AIA.*;

public class SelectionSortCanvas extends AlgorithmCanvas
{
    private static final long serialVersionUID = 6116512715591967439L;
    protected static final String I_MARKER = "i";
    protected static final String J_MARKER = "j";
    protected static final String MIN_MARKER;
    protected SelectionSort selectionSort;
    protected ElementArray elementArray;
    protected StringMarker iMarker;
    protected StringMarker jMarker;
    protected Line line;
    protected Color backgroundColor;
    protected Color textColor;
    protected boolean needInitialisation;
    
    public SelectionSortCanvas() {
        super();
        this.backgroundColor = Color.white;
        this.textColor = Color.black;
        this.needInitialisation = false;
    }
    
    public void displayAlgorithm(final Graphics g) {
        if (this.needInitialisation) {
            this.processRepaintEvent(new RepaintEvent(this, this.selectionSort));
            this.needInitialisation = false;
            return;
        }
        if (this.selectionSort != null) {
            this.elementArray.draw(g);
            if (this.iMarker != null) {
                this.iMarker.draw(g);
            }
            if (this.jMarker != null) {
                this.jMarker.draw(g);
            }
            if (this.line != null) {
                this.line.draw(g);
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
        this.iMarker = this.initialiseMarker(this.selectionSort.getI(), "i", 3);
        this.jMarker = this.initialiseMarker(this.selectionSort.getJ(), "j", 4);
        this.line = this.initialiseLine(this.selectionSort.getMinPosition());
    }
    
    protected Line initialiseLine(final int pos) {
        if (pos != -10) {
            final VerticalBar element = (VerticalBar)this.elementArray.getElement(pos);
            if (element != null) {
                final Point to = new Point(element.getPosition().x + element.getWidth() / 2, element.getPosition().y - element.getHeight());
                final Point from = new Point(element.getPosition().x + element.getWidth() / 2, 50);
                final Line line = new Line(from, to);
                line.showArrowHead(true);
                line.setDistanceFromStartForArrowHead(-3);
                line.setLabel(SelectionSortCanvas.MIN_MARKER);
                line.setDistanceFromStartForLabel(-1);
                line.setXLabelOffset(-1 * this.getGraphics().getFontMetrics().stringWidth(SelectionSortCanvas.MIN_MARKER) / 2);
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
            this.selectionSort = (SelectionSort)((SelectionSort)e.paramObj);
            final Object o = null;
            this.iMarker = o;
            this.jMarker = o;
            this.line = null;
            this.removeAllSelectables();
            if (this.getGraphics() == null) {
                this.needInitialisation = true;
                return;
            }
            this.elementArray = this.selectionSort.getElementArray(this.getGraphics());
            this.addSelectable(this.elementArray);
            this.initialise();
            this.addTween(this.selectionSort.generateTweens(this, this.elementArray, this.numberOfTweenSteps));
            if (this.tweens.getNumberOfTweens() > 0) {
                this.tweens.run();
            }
            this.selectionSort.removeAllAnimationRequests();
            this.repaint();
        }
    }
    
    static {
        MIN_MARKER = Messages.getString("SelectionSortCanvas.2");
    }
}
