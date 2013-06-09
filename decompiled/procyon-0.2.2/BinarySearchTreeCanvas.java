import localization.*;
import java.util.*;
import java.awt.*;
import com.cim.AIA.*;

public class BinarySearchTreeCanvas extends AlgorithmCanvas
{
    private static final long serialVersionUID = 1807329509121293049L;
    private static final Color POINTER_COLOR;
    private static final Color TEXT_COLOR;
    private static final String INSERT_LABEL;
    private static final String SEARCH_LABEL;
    private static final int GAP = 30;
    private static final int ELEMENT_WIDTH = 20;
    private ExtendedHierarchyTree hierarchyTree;
    private ElementArray insertElementArray;
    private ElementArray searchElementArray;
    private Node deleteNode;
    private Node parent;
    private Node ptr;
    private Node dataItem;
    private Node replace;
    private Vector<NewLinks> newLinks;
    
    public void displayAlgorithm(final Graphics g) {
        g.setColor(BinarySearchTreeCanvas.TEXT_COLOR);
        if (this.insertElementArray != null) {
            this.insertElementArray.draw(g);
            g.drawString(BinarySearchTreeCanvas.INSERT_LABEL, this.getParentSize().width / 2 - g.getFontMetrics().stringWidth(BinarySearchTreeCanvas.INSERT_LABEL) / 2, this.insertElementArray.getLocation().y);
        }
        if (this.searchElementArray != null) {
            this.searchElementArray.draw(g);
            g.drawString(BinarySearchTreeCanvas.SEARCH_LABEL, this.getParentSize().width / 2 - g.getFontMetrics().stringWidth(BinarySearchTreeCanvas.SEARCH_LABEL) / 2, this.searchElementArray.getLocation().y);
        }
        if (this.deleteNode != null) {
            this.deleteNode.draw(g);
        }
        if (this.hierarchyTree != null) {
            this.hierarchyTree.draw(g);
            if (this.parent != null) {
                final Point point1 = new Point(this.getSize().width / 4, g.getFontMetrics().getHeight() + this.hierarchyTree.getPosition().y);
                final Line parentLine = new Line(point1.x, point1.y - g.getFontMetrics().getHeight() / 2, this.parent.getX(), this.parent.getY());
                parentLine.setLabel(BinarySearchTree.PARENT_LABEL);
                parentLine.setDistanceFromStartForArrowHead(-3);
                parentLine.setDistanceFromStartForLabel(-1);
                parentLine.setXLabelOffset(-g.getFontMetrics().stringWidth(BinarySearchTree.PARENT_LABEL) / 2);
                parentLine.showArrowHead(true);
                parentLine.setColor(BinarySearchTreeCanvas.POINTER_COLOR);
                parentLine.draw(g);
            }
            if (this.ptr != null) {
                final Point point2 = new Point(this.getSize().width / 4, g.getFontMetrics().getHeight() + this.hierarchyTree.getPosition().y + 20);
                final Line ptrLine = new Line(point2.x, point2.y - g.getFontMetrics().getHeight() / 2, this.ptr.getX(), this.ptr.getY());
                ptrLine.setLabel(BinarySearchTree.PTR_LABEL);
                ptrLine.setDistanceFromStartForArrowHead(-3);
                ptrLine.setDistanceFromStartForLabel(-1);
                ptrLine.setXLabelOffset(-g.getFontMetrics().stringWidth(BinarySearchTree.PTR_LABEL) / 2);
                ptrLine.showArrowHead(true);
                ptrLine.setColor(BinarySearchTreeCanvas.POINTER_COLOR);
                ptrLine.draw(g);
            }
            if (this.dataItem != null) {
                final Point point3 = new Point(3 * this.getSize().width / 4, g.getFontMetrics().getHeight() + this.hierarchyTree.getPosition().y);
                final Line dataItemLine = new Line(point3.x, point3.y - g.getFontMetrics().getHeight() / 2, this.dataItem.getX() + this.dataItem.getWidth(), this.dataItem.getY());
                dataItemLine.setLabel(BinarySearchTree.DATAITEMPTR_LABEL);
                dataItemLine.setDistanceFromStartForArrowHead(-3);
                dataItemLine.setDistanceFromStartForLabel(-1);
                dataItemLine.setXLabelOffset(-g.getFontMetrics().stringWidth(BinarySearchTree.PTR_LABEL) / 2);
                dataItemLine.showArrowHead(true);
                dataItemLine.setColor(BinarySearchTreeCanvas.POINTER_COLOR);
                dataItemLine.draw(g);
            }
            if (this.replace != null) {
                final Point point4 = new Point(3 * this.getSize().width / 4, g.getFontMetrics().getHeight() + this.hierarchyTree.getPosition().y + 20);
                final Line replaceLine = new Line(point4.x, point4.y - g.getFontMetrics().getHeight() / 2, this.replace.getX() + this.replace.getWidth(), this.replace.getY());
                replaceLine.setLabel(BinarySearchTree.REPLACEMENT_LABEL);
                replaceLine.setDistanceFromStartForArrowHead(-3);
                replaceLine.setDistanceFromStartForLabel(-1);
                replaceLine.setXLabelOffset(-g.getFontMetrics().stringWidth(BinarySearchTree.PTR_LABEL) / 2);
                replaceLine.showArrowHead(true);
                replaceLine.setColor(BinarySearchTreeCanvas.POINTER_COLOR);
                replaceLine.draw(g);
            }
            for (int i = 0; i < this.newLinks.size(); ++i) {
                final NewLinks n = (NewLinks)this.newLinks.elementAt(i);
                final Line l = n.getLine();
                l.draw(g);
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
        this.removeSelectionListener(DeleteSelection.getInstance());
        if (e.paramObj != null) {
            int windowTop = 30;
            final int windowCenterX = this.getParentSize().width / 2;
            final BinarySearchTree binarySearchTree = (BinarySearchTree)e.paramObj;
            this.hierarchyTree = binarySearchTree.getHierarchyTree();
            if (binarySearchTree.isBuildMode()) {
                this.insertElementArray = binarySearchTree.getInsertElementArray();
            }
            else {
                this.insertElementArray = null;
            }
            if (binarySearchTree.isSearchMode()) {
                this.addSelectionListener(SearchSelection.getInstance());
                this.searchElementArray = binarySearchTree.getSearchElementArray();
            }
            else {
                this.searchElementArray = null;
            }
            if (binarySearchTree.isDeleteMode()) {
                this.addSelectionListener(DeleteSelection.getInstance());
                this.deleteNode = binarySearchTree.getdeleteNode();
            }
            else {
                this.deleteNode = null;
            }
            if (this.deleteNode != null) {
                this.deleteNode.setX(40);
                this.deleteNode.setY(10);
            }
            int windowWidth;
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
            }
            if (this.hierarchyTree != null) {
                windowWidth = this.getParentSize().width;
                this.hierarchyTree.plantTree(windowCenterX, windowTop);
                final int leftPoint = this.hierarchyTree.getRectangle().x;
                if (leftPoint < 0) {
                    this.hierarchyTree.plantTree(windowCenterX - leftPoint, windowTop);
                }
                this.parent = binarySearchTree.getParentNode();
                this.ptr = binarySearchTree.getPtrNode();
                this.dataItem = binarySearchTree.getDataItemNode();
                this.replace = binarySearchTree.getReplaceNode();
                this.newLinks = binarySearchTree.getNewLinks();
                windowTop = windowTop + (30 + this.hierarchyTree.getRectangle().height);
                this.addSelectable(this.hierarchyTree);
            }
            if (this.searchElementArray != null) {
                this.searchElementArray.setElementWidth(20);
                this.searchElementArray.setAllHaveSameWidth(true);
                windowWidth = this.getParentSize().width;
                final int windowHeight = this.getParentSize().height;
                if (this.searchElementArray.getWidth() < windowWidth) {
                    this.searchElementArray.setLocation(windowCenterX - this.searchElementArray.getWidth() / 2, windowTop);
                }
                else {
                    this.searchElementArray.setLocation(0, windowTop);
                }
                this.addSelectable(this.searchElementArray);
            }
            this.addTween(binarySearchTree.generateTweens(this, null, this.numberOfTweenSteps));
            if (this.tweens.getNumberOfTweens() > 0) {
                this.tweens.run();
            }
            binarySearchTree.removeAllAnimationRequests();
        }
        this.repaint();
    }
    
    static {
        POINTER_COLOR = Color.gray;
        TEXT_COLOR = Color.black;
        INSERT_LABEL = Messages.getString("BinarySearchTreeCanvas.0");
        SEARCH_LABEL = Messages.getString("BinarySearchTreeCanvas.1");
    }
}
