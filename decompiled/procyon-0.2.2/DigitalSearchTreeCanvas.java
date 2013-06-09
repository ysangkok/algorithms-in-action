import localization.*;
import java.awt.*;
import com.cim.AIA.*;

public class DigitalSearchTreeCanvas extends AlgorithmCanvas
{
    private ExtendedHierarchyTree hierarchyTree;
    private ElementArray insertElementArray;
    private ElementArray searchElementArray;
    private ElementArray bitElementArray;
    private Node parent;
    private Node ptr;
    private Node currentBit;
    private Node highlightedNode;
    private Node bitValueNode;
    private static final Color POINTER_COLOR;
    private static final Color TEXT_COLOR;
    private static final String BIT_ARRAY_LABEL;
    private static final String SEARCH_LABEL;
    private int approximateBitElementGap;
    private static final int GAP = 30;
    private static final int ELEMENT_WIDTH = 20;
    private static final int BIT_LINE_LENGTH = 20;
    
    public DigitalSearchTreeCanvas() {
        super();
        this.approximateBitElementGap = 0;
    }
    
    public void displayAlgorithm(final Graphics g) {
        g.setColor(DigitalSearchTreeCanvas.TEXT_COLOR);
        if (this.insertElementArray != null) {
            this.insertElementArray.draw(g);
        }
        if (this.bitElementArray != null && this.highlightedNode != null) {
            g.drawString(DigitalSearchTreeCanvas.BIT_ARRAY_LABEL, this.getParentSize().width / 2 - g.getFontMetrics().stringWidth(DigitalSearchTreeCanvas.BIT_ARRAY_LABEL) / 2, this.bitElementArray.getLocation().y + this.bitElementArray.getHeight() + g.getFontMetrics().getHeight());
            if (this.currentBit != null) {
                final Point point = new Point(this.currentBit.getX() + this.currentBit.getWidth() / 2, this.currentBit.getY() + this.currentBit.getHeight());
                final Line bitLine = new Line(point.x, point.y + 20, point.x, point.y);
                bitLine.setLabel(DigitalSearchTree.BIT_LABEL);
                bitLine.setDistanceFromStartForArrowHead(-3);
                bitLine.setDistanceFromStartForLabel(-1);
                bitLine.setXLabelOffset(-g.getFontMetrics().stringWidth(DigitalSearchTree.BIT_LABEL) / 2);
                bitLine.showArrowHead(true);
                bitLine.setColor(DigitalSearchTreeCanvas.POINTER_COLOR);
                bitLine.draw(g);
            }
            g.drawString("=", this.bitElementArray.getLocation().x + this.bitElementArray.getWidth() + 15 - g.getFontMetrics().stringWidth("=") / 2, this.bitElementArray.getLocation().y + this.bitElementArray.getHeight() / 2 + g.getFontMetrics().getHeight() / 2);
            if (this.bitValueNode != null) {
                this.bitValueNode.draw(g);
            }
            this.bitElementArray.draw(g);
        }
        if (this.searchElementArray != null) {
            this.searchElementArray.draw(g);
            g.drawString(DigitalSearchTreeCanvas.SEARCH_LABEL, this.getParentSize().width / 2 - g.getFontMetrics().stringWidth(DigitalSearchTreeCanvas.SEARCH_LABEL) / 2, this.searchElementArray.getLocation().y);
        }
        if (this.hierarchyTree != null) {
            this.hierarchyTree.draw(g);
            if (this.parent != null) {
                final Point point1 = new Point(this.getSize().width / 4, g.getFontMetrics().getHeight() + this.hierarchyTree.getPosition().y);
                final Line parentLine = new Line(point1.x + g.getFontMetrics().stringWidth(DigitalSearchTree.PARENT_LABEL), point1.y - g.getFontMetrics().getHeight() / 2, this.parent.getX(), this.parent.getY());
                parentLine.setLabel(DigitalSearchTree.PARENT_LABEL);
                parentLine.setDistanceFromStartForArrowHead(-3);
                parentLine.setDistanceFromStartForLabel(-1);
                parentLine.setXLabelOffset(-g.getFontMetrics().stringWidth(DigitalSearchTree.PARENT_LABEL) / 2);
                parentLine.showArrowHead(true);
                parentLine.setColor(DigitalSearchTreeCanvas.POINTER_COLOR);
                parentLine.draw(g);
            }
            if (this.ptr != null) {
                final Point point2 = new Point(3 * this.getSize().width / 4, g.getFontMetrics().getHeight() + this.hierarchyTree.getPosition().y);
                final Line ptrLine = new Line(point2.x, point2.y - g.getFontMetrics().getHeight() / 2, this.ptr.getX() + this.ptr.getWidth(), this.ptr.getY());
                ptrLine.setLabel(DigitalSearchTree.ptrLabel);
                ptrLine.setDistanceFromStartForArrowHead(-3);
                ptrLine.setDistanceFromStartForLabel(-1);
                ptrLine.setXLabelOffset(-g.getFontMetrics().stringWidth(DigitalSearchTree.ptrLabel) / 2);
                ptrLine.showArrowHead(true);
                ptrLine.setColor(DigitalSearchTreeCanvas.POINTER_COLOR);
                if (ptrLine.getY2() != 0.0) {
                    ptrLine.draw(g);
                }
            }
        }
    }
    
    protected void handleColorSelection(final ColorSelection colorSelection) {
    }
    
    protected void handleFontSelection(final FontSelection fontSelection) {
    }
    
    public void processRepaintEvent(final RepaintEvent e) {
        this.removeAllSelectables();
        this.removeSelectionListener(SearchSelection.getInstance());
        if (e.paramObj != null) {
            int windowTop = 30;
            final int windowCenterX = this.getParentSize().width / 2;
            final DigitalSearchTree digitalSearchTree = (DigitalSearchTree)e.paramObj;
            this.hierarchyTree = digitalSearchTree.getHierarchyTree();
            if (digitalSearchTree.isBuildMode()) {
                this.insertElementArray = digitalSearchTree.getInsertElementArray();
            }
            else {
                this.insertElementArray = null;
            }
            this.bitElementArray = digitalSearchTree.getBitElementArray();
            this.highlightedNode = digitalSearchTree.getHighlightedNode();
            if (digitalSearchTree.isSearchMode()) {
                this.addSelectionListener(SearchSelection.getInstance());
                this.searchElementArray = digitalSearchTree.getSearchElementArray();
            }
            else {
                this.searchElementArray = null;
            }
            int windowWidth;
            int bitElementWidth;
            if (this.insertElementArray != null) {
                this.insertElementArray.setElementWidth(20);
                this.insertElementArray.setAllHaveSameWidth(true);
                windowWidth = this.getParentSize().width;
                if (this.insertElementArray.getWidth() < windowWidth) {
                    this.insertElementArray.setLocation(windowCenterX - this.insertElementArray.getWidth() / 2, windowTop);
                }
                else {
                    this.insertElementArray.setLocation(0, windowTop);
                }
                windowTop = windowTop + (this.insertElementArray.getHeight() + 30);
                if (this.bitElementArray != null && this.highlightedNode != null) {
                    this.highlightedNode = digitalSearchTree.getHighlightedNode();
                    this.bitValueNode = new Node(this.highlightedNode.getObject(), 0);
                    this.bitValueNode.setBackgroundColor(this.highlightedNode.getBackgroundColor());
                    this.currentBit = digitalSearchTree.getBitNode();
                    this.bitElementArray.setElementWidth(20);
                    this.bitElementArray.setAllHaveSameWidth(true);
                    bitElementWidth = this.bitElementArray.getWidth();
                    if (bitElementWidth < windowWidth) {
                        this.bitElementArray.setLocation(windowCenterX - this.bitElementArray.getWidth() / 2, windowTop);
                    }
                    else {
                        this.bitElementArray.setLocation(0, windowTop);
                    }
                    this.bitValueNode.setPosition(new Point(this.bitElementArray.getLocation().x + bitElementWidth + 30, this.bitElementArray.getLocation().y));
                    this.approximateBitElementGap = this.bitElementArray.getHeight() + 30 + 20;
                }
                windowTop = windowTop + this.approximateBitElementGap;
            }
            if (this.hierarchyTree != null) {
                windowWidth = this.getParentSize().width;
                this.hierarchyTree.plantTree(windowCenterX, windowTop);
                final int leftPoint = this.hierarchyTree.getRectangle().x;
                if (leftPoint < 0) {
                    this.hierarchyTree.plantTree(windowCenterX - leftPoint, windowTop);
                }
                this.parent = digitalSearchTree.getParentNode();
                this.ptr = digitalSearchTree.getPtrNode();
                windowTop = windowTop + (30 + this.hierarchyTree.getRectangle().height);
            }
            else {
                final Node ghostNode = new Node("", 0);
                this.ptr = null;
                this.parent = null;
                ghostNode.setBackgroundColor(Color.white);
                this.hierarchyTree = new ExtendedHierarchyTree(ghostNode);
                this.hierarchyTree.plantTree(windowCenterX, windowTop);
                windowTop = windowTop + (30 + this.hierarchyTree.getRectangle().height);
            }
            if (this.searchElementArray != null) {
                windowWidth = this.getParentSize().width;
                final int windowHeight = this.getParentSize().height;
                if (this.bitElementArray != null && this.highlightedNode != null) {
                    this.currentBit = digitalSearchTree.getBitNode();
                    this.bitValueNode = new Node(this.highlightedNode.getObject(), 0);
                    this.bitValueNode.setBackgroundColor(this.highlightedNode.getBackgroundColor());
                    this.bitElementArray.setElementWidth(20);
                    this.bitElementArray.setAllHaveSameWidth(true);
                    bitElementWidth = this.bitElementArray.getWidth();
                    if (bitElementWidth < windowWidth) {
                        this.bitElementArray.setLocation(windowCenterX - this.bitElementArray.getWidth() / 2, windowTop);
                    }
                    else {
                        this.bitElementArray.setLocation(0, windowTop);
                    }
                    this.bitValueNode.setPosition(new Point(this.bitElementArray.getLocation().x + bitElementWidth + 30, this.bitElementArray.getLocation().y));
                    this.approximateBitElementGap = this.bitElementArray.getHeight() + 30 + 20;
                }
                windowTop = windowTop + this.approximateBitElementGap;
                this.searchElementArray.setElementWidth(20);
                this.searchElementArray.setAllHaveSameWidth(true);
                if (this.searchElementArray.getWidth() < windowWidth) {
                    this.searchElementArray.setLocation(windowCenterX - this.searchElementArray.getWidth() / 2, windowTop);
                }
                else {
                    this.searchElementArray.setLocation(0, windowTop);
                }
                windowTop = windowTop + (this.searchElementArray.getHeight() + 30);
                this.addSelectable(this.searchElementArray);
            }
        }
        this.repaint();
    }
    
    static {
        POINTER_COLOR = Color.gray;
        TEXT_COLOR = Color.black;
        BIT_ARRAY_LABEL = Messages.getString("DigitalSearchTreeCanvas.0");
        SEARCH_LABEL = Messages.getString("DigitalSearchTreeCanvas.1");
    }
}
