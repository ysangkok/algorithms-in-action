import localization.*;
import java.awt.*;
import com.cim.AIA.*;

public class QuickSortCanvas extends AlgorithmCanvas
{
    private static final long serialVersionUID = 3008138147945152127L;
    protected Color textColor;
    protected int xBuffer;
    protected int yBuffer;
    protected int gapBetweenArrayAndQuickSort;
    protected int spacingBetweenInformationAndQuickSort;
    protected int boxWidth;
    protected int activeYBuffer;
    protected QuickSort quickSort;
    protected MyElementArrayQS elementArray;
    protected int iWidth;
    protected int jWidth;
    protected Point iPoint;
    protected Point jPoint;
    protected int pValue;
    protected boolean pValueReady;
    protected Point activeLeft;
    protected Point activeRight;
    protected Point oldIPoint;
    protected Point oldJPoint;
    protected boolean tweening;
    
    public QuickSortCanvas() {
        super();
        this.textColor = Color.black;
        this.xBuffer = 60;
        this.yBuffer = 150;
        this.gapBetweenArrayAndQuickSort = 120;
        this.spacingBetweenInformationAndQuickSort = 10;
        this.boxWidth = 20;
        this.activeYBuffer = 5;
        this.iWidth = 0;
        this.jWidth = 0;
        this.iPoint = null;
        this.jPoint = null;
        this.pValue = -1;
        this.pValueReady = false;
        this.activeLeft = null;
        this.activeRight = null;
        this.tweening = false;
    }
    
    protected void calculateMarkerPosition() {
        final Object o = null;
        this.jPoint = o;
        this.iPoint = o;
        final Object o2 = null;
        this.activeRight = o2;
        this.activeLeft = o2;
        final QuickSort active = this.quickSort.getActive();
        if (active != null) {
            Element element;
            Point pos;
            if (active.getI() != -10) {
                element = this.elementArray.getElement(active.getI());
                if (element != null) {
                    this.iWidth = element.getWidth();
                    pos = element.getPosition();
                    this.iPoint = new Point(pos.x, pos.y);
                }
            }
            if (active.getJ() != -10) {
                element = this.elementArray.getElement(active.getJ());
                if (element != null) {
                    this.jWidth = element.getWidth();
                    pos = element.getPosition();
                    this.jPoint = new Point(pos.x, pos.y);
                }
            }
            if (active.getRight() != -10) {
                element = this.elementArray.getElement(active.getRight());
                if (element != null) {
                    pos = element.getPosition();
                    this.activeRight = new Point(pos.x + this.elementArray.getElementWidth() + this.elementArray.getColumGap() / 2, pos.y);
                }
            }
            if (active.getLeft() != -10) {
                element = this.elementArray.getElement(active.getLeft());
                if (element != null) {
                    pos = element.getPosition();
                    this.activeLeft = new Point(pos.x - this.elementArray.getColumGap() / 2, pos.y);
                }
            }
        }
    }
    
    public void displayAlgorithm(final Graphics g) {
        if (this.quickSort != null && this.elementArray != null) {
            if (this.quickSort.doDrawRectangles()) {
                this.layoutAlgorithm(g);
                g.setColor(this.textColor);
                Point position = this.iPoint;
                if (position != null) {
                    g.drawString("i", position.x + this.iWidth / 2 - g.getFontMetrics().stringWidth("i") / 2, position.y + 3 * g.getFontMetrics().getHeight());
                }
                position = this.jPoint;
                if (position != null) {
                    g.drawString("J", position.x + this.jWidth / 2 - g.getFontMetrics().stringWidth("J") / 2, position.y + 4 * g.getFontMetrics().getHeight());
                }
                final String informationString = Messages.getString("QuickSortCanvas.0") + this.quickSort.getNumberOfSwaps() + Messages.getString("QuickSortCanvas.6") + this.quickSort.getNumberOfComparisions();
                g.drawString(informationString, this.xBuffer, this.yBuffer + this.gapBetweenArrayAndQuickSort - g.getFontMetrics().getHeight() - this.spacingBetweenInformationAndQuickSort);
                g.setColor(Color.black);
                if (this.activeLeft != null && this.activeRight != null && this.activeRight.x != this.activeLeft.x) {
                    g.drawRect(this.activeLeft.x, this.activeYBuffer, this.activeRight.x - this.activeLeft.x, this.yBuffer - 2);
                }
                final Point boxPoint = new Point(this.xBuffer + this.elementArray.getWidth() + this.elementArray.getElementWidth() + this.elementArray.getColumGap(), this.yBuffer + this.gapBetweenArrayAndQuickSort - g.getFontMetrics().getHeight());
                g.drawRect(boxPoint.x, boxPoint.y, this.boxWidth, this.boxWidth);
                g.setColor(this.textColor);
                g.drawString("P", boxPoint.x + this.boxWidth / 2 - g.getFontMetrics().stringWidth("P") / 2, boxPoint.y);
                if (this.pValueReady) {
                    g.drawString("" + this.pValue, boxPoint.x + this.boxWidth / 2 - g.getFontMetrics().stringWidth("" + this.pValue) / 2, boxPoint.y + this.boxWidth / 2 + g.getFontMetrics().getHeight() / 2);
                }
                this.quickSort.draw(g, new Point(this.xBuffer, this.yBuffer + this.gapBetweenArrayAndQuickSort));
            }
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
            final int width = this.quickSort.getWidth(g) + this.elementArray.getElementWidth() + this.elementArray.getColumGap() + this.boxWidth;
            final int height = this.yBuffer + this.gapBetweenArrayAndQuickSort + this.quickSort.getHeight(g);
            this.setSize(width, height);
            this.xBuffer = this.getSize().width / 2 - this.quickSort.getWidth(g) / 2;
            this.elementArray.setLocation(this.xBuffer, this.yBuffer);
        }
        this.pValue = -1;
        this.pValueReady = false;
        final QuickSort active = this.quickSort.getActive();
        if (active != null && active.getPValue() != -10) {
            this.pValueReady = true;
            this.pValue = active.getPValue();
        }
    }
    
    public void processRepaintEvent(final RepaintEvent e) {
        if (e.paramObj != null) {
            this.quickSort = (QuickSort)((QuickSort)e.paramObj);
            this.removeAllSelectables();
            this.elementArray = this.quickSort.getElementArray(new Point(this.xBuffer, this.yBuffer));
            this.addSelectable(this.elementArray);
            this.addTween(this.quickSort.generateTweens(this, this.elementArray, this.numberOfTweenSteps));
            this.quickSort.removeAllAnimationRequests();
            this.calculateMarkerPosition();
            this.tweening = true;
            this.tweens.run();
            this.tweening = false;
        }
        this.repaint();
    }
}
