import java.awt.*;
import com.cim.AIA.*;

public class HeapSortCanvas extends AlgorithmCanvas
{
    private static final long serialVersionUID = -207986892246468893L;
    protected HeapSort heapSort;
    protected HierarchyTree hierarchyTree;
    protected ElementArray elementArray;
    protected int xBuffer;
    protected int yBuffer;
    protected boolean displayedBefore;
    protected int heapSortWidth;
    protected Point iPoint;
    protected Point jPoint;
    protected Point kPoint;
    protected Node iNode;
    protected Node jNode;
    protected Node kNode;
    protected int gapBetweenArrayAndTree;
    protected boolean drawMarkers;
    protected Color textColor;
    
    public HeapSortCanvas() {
        super();
        this.xBuffer = 40;
        this.yBuffer = 150;
        this.displayedBefore = false;
        this.heapSortWidth = -1;
        this.iPoint = null;
        this.jPoint = null;
        this.kPoint = null;
        this.iNode = null;
        this.jNode = null;
        this.kNode = null;
        this.gapBetweenArrayAndTree = 100;
        this.drawMarkers = true;
        this.textColor = Color.black;
    }
    
    public void displayAlgorithm(final Graphics g) {
        if (this.elementArray != null && this.hierarchyTree != null) {
            this.elementArray.draw(g);
            this.hierarchyTree.draw(g);
            if (!this.drawMarkers) {
                return;
            }
            g.setColor(this.textColor);
            if (this.iPoint != null) {
                g.drawString("i", this.iPoint.x - g.getFontMetrics().stringWidth("i") / 2, this.iPoint.y + 2 * g.getFontMetrics().getHeight());
            }
            if (this.jPoint != null) {
                g.drawString("J", this.jPoint.x - g.getFontMetrics().stringWidth("J") / 2, this.jPoint.y + 3 * g.getFontMetrics().getHeight());
            }
            if (this.kPoint != null) {
                g.drawString("k", this.kPoint.x - g.getFontMetrics().stringWidth("k") / 2, this.kPoint.y + 1 * g.getFontMetrics().getHeight());
            }
            Line line;
            if (this.kNode != null) {
                line = new Line(this.kNode.getPosition().x + this.kNode.getWidth() / 2, this.gapBetweenArrayAndTree + this.yBuffer - 1 * g.getFontMetrics().getHeight(), this.kNode.getPosition().x + this.kNode.getWidth() / 2, this.kNode.getPosition().y);
                if (line.getX() != 0 && line.getY() != 0) {
                    line.showArrowHead(true);
                    line.setDistanceFromStartForArrowHead(-3);
                    line.draw(g);
                    g.drawString("k", this.kNode.getPosition().x + this.kNode.getWidth() / 2 - g.getFontMetrics().stringWidth("k") / 2, this.gapBetweenArrayAndTree + this.yBuffer - 1 * g.getFontMetrics().getHeight());
                }
            }
            if (this.iNode != null) {
                line = new Line(this.getSize().width / 3, this.yBuffer + this.gapBetweenArrayAndTree + this.hierarchyTree.getTotalHeight() + g.getFontMetrics().getHeight(), this.iNode.getPosition().x + this.iNode.getWidth() / 2, this.iNode.getPosition().y + this.iNode.getHeight());
                if (line.getX() != 0 && line.getY() != 0) {
                    line.showArrowHead(true);
                    line.setDistanceFromStartForArrowHead(-3);
                    line.draw(g);
                    g.drawString("i", this.getSize().width / 3 - g.getFontMetrics().stringWidth("i") / 2, this.gapBetweenArrayAndTree + this.yBuffer + this.hierarchyTree.getTotalHeight() + 2 * g.getFontMetrics().getHeight());
                }
            }
            if (this.jNode != null) {
                line = new Line(2 * this.getSize().width / 3, this.yBuffer + this.gapBetweenArrayAndTree + this.hierarchyTree.getTotalHeight() + g.getFontMetrics().getHeight(), this.jNode.getPosition().x + this.jNode.getWidth() / 2, this.jNode.getPosition().y + this.jNode.getHeight());
                if (line.getX() != 0 && line.getY() != 0) {
                    line.showArrowHead(true);
                    line.setDistanceFromStartForArrowHead(-3);
                    line.draw(g);
                    g.drawString("J", 2 * this.getSize().width / 3 - g.getFontMetrics().stringWidth("J") / 2, this.gapBetweenArrayAndTree + this.yBuffer + this.hierarchyTree.getTotalHeight() + 2 * g.getFontMetrics().getHeight());
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
    }
    
    public void processRepaintEvent(final RepaintEvent e) {
        if (e.paramObj != null) {
            final Object iPoint = null;
            this.kPoint = iPoint;
            this.jPoint = iPoint;
            this.iPoint = iPoint;
            final Object iNode = null;
            this.kNode = iNode;
            this.jNode = iNode;
            this.iNode = iNode;
            this.heapSort = (HeapSort)((HeapSort)e.paramObj);
            this.removeAllSelectables();
            this.elementArray = this.heapSort.getElementArray(new Point(this.xBuffer, this.yBuffer));
            this.hierarchyTree = this.heapSort.getHierarchyTree();
            this.addSelectable(this.elementArray);
            this.addSelectable(this.hierarchyTree);
            final int elementArrayWidth = this.elementArray.getWidth();
            final int width = elementArrayWidth + 2 * this.xBuffer;
            final int height = 3 * this.yBuffer;
            this.setSize(width, height);
            this.heapSortWidth = this.getSize().width;
            this.elementArray.setLocation(this.heapSortWidth / 2 - elementArrayWidth / 2, this.yBuffer);
            this.hierarchyTree.plantTree(this.heapSortWidth / 2, this.gapBetweenArrayAndTree + this.yBuffer);
            if (this.heapSort.getI() != -10) {
                final VerticalBar iElement = (VerticalBar)((VerticalBar)this.elementArray.getElement(this.heapSort.getI()));
                if (iElement != null) {
                    this.iPoint = new Point(iElement.getPosition().x + this.elementArray.getElementWidth() / 2, iElement.getPosition().y + iElement.getRealOffsetForValue(this.getGraphics()));
                }
                this.iNode = this.heapSort.getNode(this.heapSort.getI());
            }
            if (this.heapSort.getJ() != -10) {
                final VerticalBar jElement = (VerticalBar)((VerticalBar)this.elementArray.getElement(this.heapSort.getJ()));
                if (jElement != null) {
                    this.jPoint = new Point(jElement.getPosition().x + this.elementArray.getElementWidth() / 2, jElement.getPosition().y + jElement.getRealOffsetForValue(this.getGraphics()));
                }
                this.jNode = this.heapSort.getNode(this.heapSort.getJ());
            }
            if (this.heapSort.getK() != -10) {
                final VerticalBar kElement = (VerticalBar)((VerticalBar)this.elementArray.getElement(this.heapSort.getK()));
                if (kElement != null) {
                    this.kPoint = new Point(kElement.getPosition().x + this.elementArray.getElementWidth() / 2, kElement.getRealOffsetForValue(this.getGraphics()));
                }
                this.kNode = this.heapSort.getNode(this.heapSort.getK());
            }
            this.addTween(this.heapSort.generateTweens(this, this.elementArray, this.numberOfTweenSteps));
            this.heapSort.removeAllAnimationRequests();
            this.tweens.run();
            this.repaint();
            this.heapSort.unHighlightAll();
        }
    }
    
    public void setMarkersEnabled(final boolean state) {
        this.drawMarkers = state;
        this.repaint();
    }
}
