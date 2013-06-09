import java.util.*;
import localization.*;
import java.awt.*;
import com.cim.AIA.*;

public class StraightRadixSortCanvas extends AlgorithmCanvas
{
    private static final long serialVersionUID = 5664402813801205935L;
    protected ElementArray dataElementArray;
    protected ElementArray symbolElementArray;
    protected ElementArray auxiliaryElementArray;
    protected int xBuffer;
    protected int yBuffer;
    protected int spacingBetweenDataAndSymbolArray;
    protected int spacingBetweenSymbolAndAuxiliary;
    protected String dataElementArrayLabel;
    protected String symbolElementArrayLabel;
    protected String auxiliaryElementArrayLabel;
    protected String MSBLabel;
    protected String LSBLabel;
    protected int xTextBuffer;
    protected int yTextBuffer;
    protected Font normalFont;
    protected Color textColor;
    protected StraightRadixSort straightRadixSort;
    
    public StraightRadixSortCanvas() {
        super();
        this.xBuffer = 50;
        this.yBuffer = 100;
        this.spacingBetweenDataAndSymbolArray = 220;
        this.spacingBetweenSymbolAndAuxiliary = 100;
        this.dataElementArrayLabel = Messages.getString("StraightRadixSortCanvas.0");
        this.symbolElementArrayLabel = Messages.getString("StraightRadixSortCanvas.1");
        this.auxiliaryElementArrayLabel = Messages.getString("StraightRadixSortCanvas.2");
        this.MSBLabel = Messages.getString("StraightRadixSortCanvas.3");
        this.LSBLabel = Messages.getString("StraightRadixSortCanvas.4");
        this.xTextBuffer = 10;
        this.yTextBuffer = 5;
    }
    
    public void displayAlgorithm(final Graphics g) {
        if (this.straightRadixSort != null) {
            if (this.normalFont != null) {
                g.setFont(this.normalFont);
            }
            if (this.textColor != null) {
                g.setColor(this.textColor);
            }
            final int bitValue = this.straightRadixSort.getBit();
            if (this.dataElementArray != null) {
                final int offsetSpacing = 6;
                final int dataArrow = this.straightRadixSort.getDataArrow();
                this.setSize(this.dataElementArray.getWidth() + this.xBuffer + g.getFontMetrics().stringWidth(this.auxiliaryElementArrayLabel) + 2 * this.xTextBuffer, this.getSize().height);
                final Vector<SplitMarker> splitMarkers = this.straightRadixSort.getSplitValues();
                this.displayElementArray(g, this.dataElementArray, this.dataElementArrayLabel, offsetSpacing, dataArrow, splitMarkers);
                this.dataElementArray.draw(g);
            }
            if (this.symbolElementArray != null) {
                final Point point = this.symbolElementArray.getLocation();
                g.drawString(this.symbolElementArrayLabel, point.x + this.symbolElementArray.getWidth() + this.xTextBuffer, point.y + this.symbolElementArray.getHeight() / 2 + g.getFontMetrics().getHeight() / 3);
                this.symbolElementArray.draw(g);
            }
            if (this.auxiliaryElementArray != null) {
                final int offsetSpacing2 = 7 + (4 + g.getFontMetrics().getHeight());
                this.displayElementArray(g, this.auxiliaryElementArray, this.auxiliaryElementArrayLabel, offsetSpacing2, -10, null);
                this.auxiliaryElementArray.draw(g);
            }
            final int dataArrow2 = this.straightRadixSort.getDataArrow();
            if (dataArrow2 != -10) {
                final VerticalBar element = (VerticalBar)this.dataElementArray.getElement(dataArrow2);
                if (element != null) {
                    final Point elementPosition = element.getPosition();
                    final Line line = new Line(elementPosition.x + element.getWidth() / 2, elementPosition.y - element.getHeight() - 2 - 20, elementPosition.x + element.getWidth() / 2, elementPosition.y - element.getHeight() - 2);
                    line.showArrowHead(true);
                    line.setDistanceFromStartForArrowHead(-3);
                    line.draw(g);
                }
            }
            final int auxiliaryArrow = this.straightRadixSort.getAuxiliaryArrow();
            if (auxiliaryArrow != -10) {
                final Node node = (Node)this.auxiliaryElementArray.getElement(auxiliaryArrow);
                final VerticalBar verticalBar = (VerticalBar)this.dataElementArray.getElement(auxiliaryArrow);
                if (node != null && verticalBar != null) {
                    final Point verticalBarPosition = verticalBar.getPosition();
                    final Point nodePosition = node.getPosition();
                    final Point point2 = this.dataElementArray.getLocation();
                    final int bitLength = this.straightRadixSort.getBitLength();
                    final Line line2 = new Line(nodePosition.x + node.getWidth() / 2, nodePosition.y, nodePosition.x + node.getWidth() / 2, point2.y + 4 + 2 - 2 + (4 + g.getFontMetrics().getHeight()) * (bitLength + 2));
                    line2.showArrowHead(true);
                    line2.setDistanceFromStartForArrowHead(-3);
                    line2.draw(g);
                }
            }
        }
    }
    
    protected void displayElementArray(final Graphics g, final ElementArray dataElementArray, final String dataElementArrayLabel, final int offsetSpacing, final int dataArrow, final Vector<SplitMarker> splitMarkers) {
        if (dataElementArray != null) {
            final Point point = dataElementArray.getLocation();
            g.drawString(dataElementArrayLabel, point.x + dataElementArray.getWidth() + this.xTextBuffer, point.y);
            g.drawString(this.MSBLabel, point.x - this.xTextBuffer - g.getFontMetrics().stringWidth(this.MSBLabel), point.y + offsetSpacing + g.getFontMetrics().getHeight() + (4 + g.getFontMetrics().getHeight()) * 2);
            g.drawString(this.LSBLabel, point.x - this.xTextBuffer - g.getFontMetrics().stringWidth(this.LSBLabel), point.y + offsetSpacing + g.getFontMetrics().getHeight() + (4 + g.getFontMetrics().getHeight()) * (this.straightRadixSort.getBitLength() - 1 + 2));
            for (int i = 0; i < this.straightRadixSort.getBitLength(); ++i) {
                final Color oldColor = g.getColor();
                g.setColor(this.straightRadixSort.getColor(i));
                g.fillRect(point.x, point.y + offsetSpacing - 2 + (4 + g.getFontMetrics().getHeight()) * (i + 2), dataElementArray.getWidth(), g.getFontMetrics().getHeight() + 4);
                g.setColor(oldColor);
            }
            if (splitMarkers != null) {
                final int elemWidth = dataElementArray.getElementWidth() + dataElementArray.getColumGap();
                final int height = g.getFontMetrics().getHeight() + 4;
                for (int j = 0; j < splitMarkers.size(); ++j) {
                    final SplitMarker splitMarker = (SplitMarker)splitMarkers.elementAt(j);
                    final int x = point.x + splitMarker.getPosition() * elemWidth - dataElementArray.getColumGap() / 2;
                    final int y = point.y + offsetSpacing - 2 + (4 + g.getFontMetrics().getHeight()) * (splitMarker.getLevel() + 2);
                    final Line line = new Line(x, y, x, y + height);
                    line.setColor(this.getBackground());
                    line.draw(g);
                }
            }
            final int bitValue = this.straightRadixSort.getBit();
            if (bitValue != -10 && bitValue >= 0) {
                g.setColor(Color.black);
                g.drawRect(point.x, point.y + offsetSpacing - 2 + (4 + g.getFontMetrics().getHeight()) * (bitValue + 2), dataElementArray.getWidth() - 1, g.getFontMetrics().getHeight() + 4);
            }
        }
        final int bitValue2 = this.straightRadixSort.getBit();
        if (dataArrow != -10 && bitValue2 != -10 && bitValue2 >= 0) {
            final Point point2 = dataElementArray.getLocation();
            final Element element = dataElementArray.getElement(dataArrow);
            if (element != null) {
                g.setColor(Color.black);
                final Point elementPos = element.getPosition();
                g.drawRect(elementPos.x + 1, point2.y + 1 + offsetSpacing - 2 + (4 + g.getFontMetrics().getHeight()) * (bitValue2 + 2), element.getWidth() - 3, g.getFontMetrics().getHeight() + 4 - 2);
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
    }
    
    public void processRepaintEvent(final RepaintEvent e) {
        if (e.paramObj != null) {
            this.drawables.removeAllElements();
            this.straightRadixSort = (StraightRadixSort)((StraightRadixSort)e.paramObj);
            this.dataElementArray = this.straightRadixSort.getDataElementArray();
            if (this.dataElementArray != null) {
                this.dataElementArray.setLocation(this.xBuffer, this.yBuffer);
            }
            this.auxiliaryElementArray = this.straightRadixSort.getAuxiliaryElementArray();
            if (this.auxiliaryElementArray != null) {
                this.auxiliaryElementArray.setLocation(this.xBuffer - 2, this.yBuffer + this.spacingBetweenDataAndSymbolArray + this.spacingBetweenSymbolAndAuxiliary);
            }
            this.symbolElementArray = this.straightRadixSort.getSymbolElementArray();
            if (this.symbolElementArray != null && this.dataElementArray != null) {
                this.symbolElementArray.setLocation(this.xBuffer + (this.dataElementArray.getWidth() / 2 - this.symbolElementArray.getWidth() / 2), this.yBuffer + this.spacingBetweenDataAndSymbolArray);
            }
            if (this.symbolElementArray != null && this.auxiliaryElementArray != null) {
                this.straightRadixSort.calculateLines(this, this.symbolElementArray, this.auxiliaryElementArray);
            }
            this.repaint();
        }
    }
}
