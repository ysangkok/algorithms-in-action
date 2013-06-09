import localization.*;
import java.awt.*;
import com.cim.AIA.*;

public class ShellSortCanvas extends AlgorithmCanvas
{
    private static final long serialVersionUID = 1130991115387495544L;
    protected static final String I_MARKER = "i";
    protected static final String J_MARKER = "J";
    protected static final String FIRST_POS_MARKER;
    protected StringMarker iMarker;
    protected StringMarker jMarker;
    protected int xBuffer;
    protected int yBuffer;
    protected int boxWidth;
    protected ShellSort shellSort;
    protected ElementArray elementArray;
    protected int value;
    protected int hValue;
    protected int firstPosValue;
    protected Color backgroundColor;
    protected Color textColor;
    protected boolean needInitialisation;
    protected Line firstPosLine;
    protected Line[] horizontalLines;
    protected Line[] verticalPointerLines;
    
    public ShellSortCanvas() {
        super();
        this.xBuffer = 80;
        this.yBuffer = 180;
        this.boxWidth = 20;
        this.value = -1;
        this.hValue = -1;
        this.firstPosValue = -1;
        this.backgroundColor = Color.white;
        this.textColor = Color.black;
        this.needInitialisation = false;
    }
    
    public void displayAlgorithm(final Graphics g) {
        if (this.needInitialisation) {
            this.processRepaintEvent(new RepaintEvent(this, this.shellSort));
            this.needInitialisation = false;
            return;
        }
        if (this.shellSort != null) {
            this.elementArray.draw(g);
            if (this.iMarker != null) {
                this.iMarker.draw(g);
            }
            if (this.jMarker != null) {
                this.jMarker.draw(g);
            }
            if (ShellSort.drawLine.size() > 0) {
                for (int i = 0; i < ShellSort.drawLine.size() - 1; ++i) {
                    if (this.horizontalLines[i] != null) {
                        this.horizontalLines[i].draw(g);
                    }
                }
                for (int j = 0; j < ShellSort.drawLine.size(); ++j) {
                    if (this.verticalPointerLines[j] != null) {
                        this.verticalPointerLines[j].draw(g);
                    }
                }
            }
            if (this.firstPosLine != null) {
                this.firstPosLine.draw(g);
            }
            final Point boxPoint = new Point(this.elementArray.getLocation().x + this.elementArray.getWidth() + this.elementArray.getElementWidth() + this.elementArray.getColumGap(), this.yBuffer + g.getFontMetrics().getHeight());
            g.drawRect(boxPoint.x, boxPoint.y, this.boxWidth, this.boxWidth);
            g.setColor(this.textColor);
            g.drawString(Messages.getString("ShellSortCanvas.1"), boxPoint.x + this.boxWidth / 2 - g.getFontMetrics().stringWidth(Messages.getString("ShellSortCanvas.2")) / 2, boxPoint.y);
            if (this.shellSort.getVal() != -10) {
                g.setColor(ShellSort.activeArrayColor);
                g.fillRect(boxPoint.x, boxPoint.y, this.boxWidth, this.boxWidth);
                g.setColor(this.textColor);
                this.value = this.shellSort.getVal();
                g.drawString("" + this.value, boxPoint.x + this.boxWidth / 2 - g.getFontMetrics().stringWidth("" + this.value) / 2, boxPoint.y + this.boxWidth / 2 + g.getFontMetrics().getHeight() / 2);
            }
            final Point boxPoint1 = new Point(this.elementArray.getLocation().x + this.elementArray.getWidth() + this.elementArray.getElementWidth() + this.elementArray.getColumGap(), this.yBuffer - 40 + g.getFontMetrics().getHeight());
            g.drawRect(boxPoint1.x, boxPoint1.y, this.boxWidth, this.boxWidth);
            g.setColor(this.textColor);
            g.drawString("h", boxPoint.x + this.boxWidth / 2 - g.getFontMetrics().stringWidth("h") / 2, boxPoint1.y);
            if (this.shellSort.getH() != -10) {
                this.hValue = this.shellSort.getH();
                g.drawString("" + this.hValue, boxPoint1.x + this.boxWidth / 2 - g.getFontMetrics().stringWidth("" + this.hValue) / 2, boxPoint1.y + this.boxWidth / 2 + g.getFontMetrics().getHeight() / 2);
            }
            if (this.shellSort.getComparisonOrder() == 10) {
                final Point boxPoint2 = new Point(this.elementArray.getLocation().x + this.elementArray.getWidth() + this.elementArray.getElementWidth() + this.elementArray.getColumGap(), this.yBuffer - 80 + g.getFontMetrics().getHeight());
                g.drawRect(boxPoint2.x, boxPoint2.y, this.boxWidth, this.boxWidth);
                g.setColor(this.textColor);
                g.drawString(Messages.getString("ShellSortCanvas.3"), boxPoint2.x + this.boxWidth / 2 - g.getFontMetrics().stringWidth(Messages.getString("ShellSortCanvas.4")) / 2, boxPoint2.y);
                if (this.shellSort.getFirstPos() != -10) {
                    this.firstPosValue = this.shellSort.getFirstPos();
                    g.drawString("" + this.firstPosValue, boxPoint2.x + this.boxWidth / 2 - g.getFontMetrics().stringWidth("" + this.firstPosValue) / 2, boxPoint2.y + this.boxWidth / 2 + g.getFontMetrics().getHeight() / 2);
                }
            }
        }
    }
    
    protected Line drawHorizontalLine(final int seqNo1, final int seqNo2) {
        final Point from = this.elementArray.getElement(seqNo1).getPosition();
        final Point to = this.elementArray.getElement(seqNo2).getPosition();
        final Line horizontalLine = new Line(from.x + this.elementArray.getElementWidth() / 2, from.y + 30, to.x + this.elementArray.getElementWidth() / 2, to.y + 30);
        return horizontalLine;
    }
    
    protected Line drawLinePointers(final int seqNo) {
        final Point at = this.elementArray.getElement(seqNo).getPosition();
        final Line verticalPointerLine = new Line(at.x + this.elementArray.getElementWidth() / 2, at.y + 30, at.x + this.elementArray.getElementWidth() / 2, at.y + 20);
        return verticalPointerLine;
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
        final String atribute = fontSelection.getAtributeName();
        if (atribute.equalsIgnoreCase("Normal Font")) {
            this.setFont(fontSelection.getFont());
        }
    }
    
    protected void initialise() {
        final Dimension currentSize = this.getSize();
        currentSize.width = this.elementArray.getWidth() + 50;
        this.setSize(currentSize);
        this.elementArray.setLocation(this.getSize().width / 2 - this.elementArray.getWidth() / 2, 200);
        this.iMarker = this.initialiseMarker(this.shellSort.getI(), "i", 3);
        this.jMarker = this.initialiseMarker(this.shellSort.getJ(), "J", 4);
        this.firstPosLine = this.initialiseLine(this.shellSort.getFirstPos(), ShellSortCanvas.FIRST_POS_MARKER);
        if (ShellSort.drawLine.size() > 0) {
            for (int i = 0; i < ShellSort.drawLine.size() - 1; ++i) {
                this.horizontalLines[i] = this.drawHorizontalLine(((Integer)ShellSort.drawLine.elementAt(i)).intValue(), ((Integer)ShellSort.drawLine.elementAt(i + 1)).intValue());
            }
            for (int j = 0; j < ShellSort.drawLine.size(); ++j) {
                this.verticalPointerLines[j] = this.drawLinePointers(((Integer)ShellSort.drawLine.elementAt(j)).intValue());
            }
        }
    }
    
    protected Line initialiseLine(final int pos, final String string) {
        if (pos != -10) {
            final VerticalBar element = (VerticalBar)this.elementArray.getElement(pos);
            if (element != null) {
                final Point to = new Point(element.getPosition().x + element.getWidth() / 2, element.getPosition().y - element.getHeight());
                final Point from = new Point(element.getPosition().x + element.getWidth() / 2, 50);
                final Line line = new Line(from, to);
                line.showArrowHead(true);
                line.setDistanceFromStartForArrowHead(-3);
                line.setLabel(string);
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
            this.shellSort = (ShellSort)((ShellSort)e.paramObj);
            final Object o = null;
            this.iMarker = o;
            this.jMarker = o;
            if (ShellSort.drawLine.size() > 0) {
                this.horizontalLines = new Line[ShellSort.drawLine.size() - 1];
                this.verticalPointerLines = new Line[ShellSort.drawLine.size()];
                for (int i = 0; i < ShellSort.drawLine.size() - 1; ++i) {
                    this.horizontalLines[i] = null;
                }
                for (int j = 0; j < ShellSort.drawLine.size(); ++j) {
                    this.verticalPointerLines[j] = null;
                }
            }
            if (this.getGraphics() == null) {
                this.needInitialisation = true;
                return;
            }
            this.elementArray = this.shellSort.getElementArray(this.getGraphics());
            this.initialise();
            this.addTween(this.shellSort.generateTweens(this, this.elementArray, this.numberOfTweenSteps));
            if (this.tweens.getNumberOfTweens() > 0) {
                this.tweens.run();
            }
            this.shellSort.removeAllAnimationRequests();
        }
        this.repaint();
    }
    
    static {
        FIRST_POS_MARKER = Messages.getString("ShellSortCanvas.0");
    }
}
