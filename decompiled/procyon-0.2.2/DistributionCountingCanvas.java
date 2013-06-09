import localization.*;
import java.awt.*;
import com.cim.AIA.*;

public class DistributionCountingCanvas extends AlgorithmCanvas
{
    private static final long serialVersionUID = -1002901147034945420L;
    protected ElementArray dataElementArray;
    protected ElementArray symbolElementArray;
    protected ElementArray auxiliaryElementArray;
    protected int xBuffer;
    protected int yBuffer;
    protected int spacingBetweenArrays;
    protected String dataElementArrayLabel;
    protected String symbolElementArrayLabel;
    protected String auxiliaryElementArrayLabel;
    protected int xTextBuffer;
    protected int yTextBuffer;
    protected Font normalFont;
    protected Color textColor;
    protected DistributionCounting distributionCounting;
    
    public DistributionCountingCanvas() {
        super();
        this.xBuffer = 30;
        this.yBuffer = 20;
        this.spacingBetweenArrays = 100;
        this.dataElementArrayLabel = Messages.getString("DistributionCountingCanvas.0");
        this.symbolElementArrayLabel = Messages.getString("DistributionCountingCanvas.1");
        this.auxiliaryElementArrayLabel = Messages.getString("DistributionCountingCanvas.2");
        this.xTextBuffer = 10;
        this.yTextBuffer = 5;
    }
    
    public void displayAlgorithm(final Graphics g) {
        if (this.distributionCounting != null) {
            if (this.normalFont != null) {
                g.setFont(this.normalFont);
            }
            if (this.textColor != null) {
                g.setColor(this.textColor);
            }
            Point point;
            if (this.dataElementArray != null) {
                point = this.dataElementArray.getLocation();
                g.drawString(this.dataElementArrayLabel, point.x + this.dataElementArray.getWidth() + this.xTextBuffer, point.y + this.dataElementArray.getHeight() / 2 + g.getFontMetrics().getHeight() / 3);
                this.setSize(this.dataElementArray.getWidth() + this.xBuffer + g.getFontMetrics().stringWidth(this.auxiliaryElementArrayLabel) + this.xTextBuffer, this.getSize().height);
                this.dataElementArray.draw(g);
            }
            if (this.symbolElementArray != null) {
                point = this.symbolElementArray.getLocation();
                g.drawString(this.symbolElementArrayLabel, point.x + this.symbolElementArray.getWidth() + this.xTextBuffer, point.y + this.symbolElementArray.getHeight() / 2 + g.getFontMetrics().getHeight() / 3);
                this.symbolElementArray.draw(g);
            }
            if (this.auxiliaryElementArray != null) {
                point = this.auxiliaryElementArray.getLocation();
                g.drawString(this.auxiliaryElementArrayLabel, point.x + this.auxiliaryElementArray.getWidth() + this.xTextBuffer, point.y + this.auxiliaryElementArray.getHeight() / 2 + g.getFontMetrics().getHeight() / 3);
                this.auxiliaryElementArray.draw(g);
            }
            final int dataArrow = this.distributionCounting.getDataArrow();
            if (dataArrow != -10) {
                final Element element = this.dataElementArray.getElement(dataArrow);
                if (element != null) {
                    final Point position = element.getPosition();
                    final Line line = new Line(position.x + element.getWidth() / 2, position.y - 20, position.x + element.getWidth() / 2, position.y);
                    line.showArrowHead(true);
                    line.setDistanceFromStartForArrowHead(-3);
                    line.draw(g);
                }
            }
        }
    }
    
    protected void handleColorSelection(final ColorSelection colorSelection) {
        final String atribute = colorSelection.getAtributeName();
        if (atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR)) {
            this.textColor = colorSelection.getColor();
        }
    }
    
    protected void handleFontSelection(final FontSelection fontSelection) {
        final String atribute = fontSelection.getAtributeName();
        if (atribute.equalsIgnoreCase("Normal Font")) {
            this.normalFont = fontSelection.getFont();
        }
    }
    
    public void processRepaintEvent(final RepaintEvent e) {
        if (e.paramObj != null) {
            this.drawables.removeAllElements();
            this.distributionCounting = (DistributionCounting)((DistributionCounting)e.paramObj);
            this.dataElementArray = this.distributionCounting.getDataElementArray();
            if (this.dataElementArray != null) {
                this.dataElementArray.setLocation(this.xBuffer, this.yBuffer);
            }
            this.auxiliaryElementArray = this.distributionCounting.getAuxiliaryElementArray();
            if (this.auxiliaryElementArray != null) {
                this.auxiliaryElementArray.setLocation(this.xBuffer, this.yBuffer + 2 * this.spacingBetweenArrays);
            }
            this.symbolElementArray = this.distributionCounting.getSymbolElementArray();
            if (this.symbolElementArray != null && this.dataElementArray != null) {
                this.symbolElementArray.setLocation(this.xBuffer + (this.dataElementArray.getWidth() / 2 - this.symbolElementArray.getWidth() / 2), this.yBuffer + this.spacingBetweenArrays);
            }
            if (this.symbolElementArray != null && this.auxiliaryElementArray != null) {
                this.distributionCounting.calculateLines(this, this.symbolElementArray, this.auxiliaryElementArray);
            }
            this.repaint();
        }
    }
}
