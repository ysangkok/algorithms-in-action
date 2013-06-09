import java.awt.*;
import com.cim.AIA.*;

public class RadixExchangeSortCanvas extends AlgorithmCanvas
{
    private static final long serialVersionUID = 3757319251057068421L;
    protected int xBuffer;
    protected int yBuffer;
    protected int gapBetweenArrayAndRadix;
    protected Color textColor;
    protected RadixExchangeSort radixExchangeSort;
    protected ElementArray elementArray;
    protected boolean tweening;
    protected StringMarker iMarker;
    protected StringMarker jMarker;
    
    public RadixExchangeSortCanvas() {
        super();
        this.xBuffer = 60;
        this.yBuffer = 150;
        this.gapBetweenArrayAndRadix = 70;
        this.textColor = Color.black;
        this.tweening = false;
    }
    
    public void displayAlgorithm(final Graphics g) {
        if (this.radixExchangeSort != null && this.elementArray != null) {
            this.layoutAlgorithm(g);
            if (this.iMarker != null) {
                this.iMarker.draw(g);
            }
            if (this.jMarker != null) {
                this.jMarker.draw(g);
            }
            this.radixExchangeSort.draw(g, new Point(this.xBuffer, this.yBuffer + this.gapBetweenArrayAndRadix));
            this.elementArray.draw(g);
        }
    }
    
    protected void handleColorSelection(final ColorSelection colorSelection) {
        final String atribute = colorSelection.getAtributeName();
        if (atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR)) {
            this.textColor = colorSelection.getColor();
        }
    }
    
    protected void handleFontSelection(final FontSelection fontSelection) {
    }
    
    protected void layoutAlgorithm(final Graphics g) {
        if (!this.tweening) {
            final int width = this.xBuffer + this.radixExchangeSort.getWidth(g);
            final int height = this.yBuffer + this.gapBetweenArrayAndRadix + this.radixExchangeSort.getHeight(g);
            this.setSize(width, height);
            this.xBuffer = this.getSize().width / 2 - this.radixExchangeSort.getWidth(g) / 2;
            this.elementArray.setLocation(this.radixExchangeSort.getArrayBuffer(g) + this.xBuffer, this.yBuffer);
        }
    }
    
    public void processRepaintEvent(final RepaintEvent e) {
        if (e.paramObj != null) {
            this.radixExchangeSort = (RadixExchangeSort)((RadixExchangeSort)e.paramObj);
            final Graphics g = this.getGraphics();
            final Point tempPoint = new Point(this.xBuffer, this.yBuffer);
            if (g != null) {
                final Point point = tempPoint;
                point.x = point.x + this.radixExchangeSort.getArrayBuffer(g);
            }
            this.elementArray = this.radixExchangeSort.getElementArray(tempPoint);
            this.addTween(this.radixExchangeSort.generateTweens(this, this.elementArray, this.numberOfTweenSteps));
            this.radixExchangeSort.removeAllAnimationRequests();
            final Object o = null;
            this.jMarker = o;
            this.iMarker = o;
            final RadixExchangeSort active = this.radixExchangeSort.getActive();
            if (active != null && g != null) {
                Element element;
                Point pos;
                if (active.getI() != -10) {
                    element = this.elementArray.getElement(active.getI());
                    if (element != null) {
                        pos = element.getPosition();
                        final Point iPoint = new Point(pos.x + element.getWidth() / 2, pos.y + 3 * g.getFontMetrics().getHeight());
                        this.iMarker = new StringMarker("i", iPoint);
                        this.iMarker.setBackgroundColor(this.getBackground());
                        this.iMarker.setColor(this.textColor);
                    }
                }
                if (active.getJ() != -10) {
                    element = this.elementArray.getElement(active.getJ());
                    if (element != null) {
                        pos = element.getPosition();
                        final Point jPoint = new Point(pos.x + element.getWidth() / 2, pos.y + 4 * g.getFontMetrics().getHeight());
                        this.jMarker = new StringMarker("J", jPoint);
                        this.jMarker.setBackgroundColor(this.getBackground());
                        this.jMarker.setColor(this.textColor);
                    }
                }
            }
            this.tweening = true;
            this.tweens.run();
            this.tweening = false;
        }
        this.repaint();
    }
}
