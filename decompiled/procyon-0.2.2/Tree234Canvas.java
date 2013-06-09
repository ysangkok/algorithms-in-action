import localization.*;
import java.awt.*;
import java.util.*;
import com.cim.AIA.*;

public class Tree234Canvas extends AlgorithmCanvas
{
    protected Tree234Tree redBlackTree;
    protected HierarchyTree hierarchyTree;
    protected HierarchyTree balancedHierarchyTree;
    protected Node animationNode;
    protected Node growNode;
    protected ElementArray elementArray;
    protected Hashtable<K, V> oldPositions;
    protected int nodeWidth;
    protected int xTextPadding;
    protected int yBuffer;
    protected int xBuffer;
    protected Line ptrLine;
    protected Line parentLine;
    protected Line grandParentLine;
    protected Line greatGrandParentLine;
    protected boolean showAs234Only;
    private final int SPEED_UP_FACTOR = 3;
    protected static final String PTR_LABEL = "Ptr";
    protected static final String PARENT_LABEL;
    protected static final String GRAND_PARENT_LABEL;
    protected static final String GREAT_GRAND_PARENT_LABEL;
    
    public Tree234Canvas() {
        super();
        this.nodeWidth = 20;
        this.xTextPadding = 30;
        this.yBuffer = 30;
        this.xBuffer = 30;
        this.showAs234Only = true;
        this.SPEED_UP_FACTOR = 3;
    }
    
    public void setShowAs234Only(final boolean state) {
        this.showAs234Only = state;
    }
    
    public void display234(final Graphics g) {
        if (this.elementArray != null) {
            this.elementArray.draw(g);
        }
        if (this.growNode != null) {
            this.growNode.draw(g);
        }
        if (this.balancedHierarchyTree != null) {
            this.balancedHierarchyTree.draw(g);
        }
        if (this.animationNode != null) {
            this.animationNode.draw(g);
        }
    }
    
    public void displayRB(final Graphics g) {
        if (this.ptrLine != null) {
            this.ptrLine.draw(g);
        }
        if (this.parentLine != null) {
            this.parentLine.draw(g);
        }
        if (this.grandParentLine != null) {
            this.grandParentLine.draw(g);
        }
        if (this.hierarchyTree != null) {
            this.hierarchyTree.draw(g);
        }
    }
    
    public void displayAlgorithm(final Graphics g) {
        if (this.redBlackTree != null) {
            if (!this.showAs234Only) {
                this.displayRB(g);
            }
            else if (this.redBlackTree.update234) {
                this.display234(g);
            }
            else {
                g.drawString(Messages.getString("Tree234Canvas.4"), 20, 20);
            }
        }
    }
    
    protected MultipleTween getMoveTweens(final HierarchyTree hTree, final Hashtable<K, V> oldPositions, final Hashtable<K, V> currentPositions) {
        final MultipleTween multipleTweens = new MultipleTween(this);
        this.getMoveTweens(hTree, oldPositions, currentPositions, multipleTweens);
        return multipleTweens;
    }
    
    protected void getMoveTweens(final HierarchyTree hTree, final Hashtable<K, V> oldPositions, final Hashtable<K, V> currentPositions, final MultipleTween multipleTweens) {
        if (hTree != null) {
            final Node node = hTree.getNodeAt(1);
            final Node tNode = hTree.getNodeAt(2);
            if (node != null && tNode == null) {
                final Point oldPoint = (Point)((Point)oldPositions.get(new Integer(node.getIdentifier())));
                final Point currentPoint = (Point)((Point)currentPositions.get(new Integer(node.getIdentifier())));
                if (oldPoint != null && currentPoint != null && (oldPoint.x != currentPoint.x || oldPoint.y != currentPoint.y)) {
                    multipleTweens.add(new MoveTween(null, hTree, oldPoint, currentPoint, false, this.numberOfTweenSteps / 3));
                }
            }
            final Vector<E> children = hTree.getChildren();
            for (int i = 0; i < children.size(); ++i) {
                final HierarchyTree childHTree = (HierarchyTree)((HierarchyTree)children.elementAt(i));
                this.getMoveTweens(childHTree, oldPositions, currentPositions, multipleTweens);
            }
        }
    }
    
    protected Hashtable<K, V> getPositions(final HierarchyTree hTree) {
        final Hashtable<K, V> positions = new Hashtable();
        this.getPositions(hTree, positions);
        return positions;
    }
    
    protected void getPositions(final HierarchyTree hTree, final Hashtable<K, V> positions) {
        if (hTree != null) {
            final Vector<E> nodes = hTree.getNodes();
            for (int v = 0; v < nodes.size(); ++v) {
                final Node node = (Node)nodes.elementAt(v);
                if (node != null && node.getIsVisible()) {
                    final int w = node.getWidth();
                    positions.put(new Integer(node.getIdentifier()), new Point(hTree.getX() + v * w, hTree.getY()));
                }
            }
            final Vector<E> children = hTree.getChildren();
            for (int i = 0; i < children.size(); ++i) {
                final HierarchyTree childHTree = (HierarchyTree)((HierarchyTree)children.elementAt(i));
                this.getPositions(childHTree, positions);
            }
        }
    }
    
    protected Line initialiseLine(final Graphics g, final Point from, final HierarchyTree tree, final String label) {
        if (tree != null) {
            final Line line = new Line(from.x, from.y, tree.getPosition().x + this.nodeWidth / 2, tree.getPosition().y);
            line.showArrowHead(true);
            line.setLabel(label);
            line.setDistanceFromStartForArrowHead(-3);
            line.setDistanceFromStartForLabel(-1);
            line.setXLabelOffset(-1 * (g.getFontMetrics().stringWidth(label) / 2));
            line.setColor(Color.gray);
            return line;
        }
        return null;
    }
    
    protected void initaliseLines(final Graphics g, final int yPoint) {
        int xPosition = this.getSize().width / 2 - (g.getFontMetrics().stringWidth("Ptr" + Tree234Canvas.PARENT_LABEL + Tree234Canvas.GRAND_PARENT_LABEL + Tree234Canvas.GREAT_GRAND_PARENT_LABEL) + 3 * this.xTextPadding) / 2;
        final Object o = null;
        this.greatGrandParentLine = o;
        this.grandParentLine = o;
        this.parentLine = o;
        this.ptrLine = o;
        this.ptrLine = this.initialiseLine(g, new Point(xPosition, yPoint), this.redBlackTree.getPtrNode(), "Ptr");
        xPosition = xPosition + (g.getFontMetrics().stringWidth("Ptr") + this.xTextPadding);
        this.parentLine = this.initialiseLine(g, new Point(xPosition, yPoint), this.redBlackTree.getParentNode(), Tree234Canvas.PARENT_LABEL);
        xPosition = xPosition + (g.getFontMetrics().stringWidth(Tree234Canvas.PARENT_LABEL) + this.xTextPadding);
        this.grandParentLine = this.initialiseLine(g, new Point(xPosition, yPoint), this.redBlackTree.getGrandParentNode(), Tree234Canvas.GRAND_PARENT_LABEL);
        xPosition = xPosition + (g.getFontMetrics().stringWidth(Tree234Canvas.GRAND_PARENT_LABEL) + this.xTextPadding);
    }
    
    public void processRB() {
        this.hierarchyTree = this.redBlackTree.getHierarchyTree(this.nodeWidth);
        if (this.hierarchyTree != null) {
            this.hierarchyTree.plantTree(this.getSize().width / 2 - this.nodeWidth / 2, this.yBuffer);
        }
        int width = 0;
        int height = 0;
        if (this.hierarchyTree != null) {
            final Rectangle rectangle = this.hierarchyTree.getRectangle();
            width = rectangle.width;
            height = rectangle.height;
        }
        width = width + 2 * this.xBuffer;
        height = height + 2 * this.yBuffer;
        this.setSize(width, height);
        if (this.hierarchyTree != null) {
            this.hierarchyTree.plantTree(this.getSize().width / 2 - this.nodeWidth / 2, 2 * this.yBuffer);
        }
    }
    
    public void animateRB() {
        final Graphics g = this.getGraphics();
        if (g != null) {
            this.initaliseLines(g, this.yBuffer);
        }
        final Hashtable<K, V> currentPositions = this.getPositions(this.hierarchyTree);
        if (this.oldPositions != null) {
            final MultipleTween moveTweens = this.getMoveTweens(this.hierarchyTree, this.oldPositions, currentPositions);
            if (moveTweens.getNumberOfTweens() > 0) {
                this.addTween(moveTweens);
            }
        }
        this.oldPositions = currentPositions;
        if (this.tweens.getNumberOfTweens() > 0) {
            this.tweens.run();
        }
    }
    
    public void process234() {
        this.elementArray = this.redBlackTree.getDataElementArray();
        this.elementArray.setAllHaveSameWidth(true);
        this.elementArray.setElementWidth(this.nodeWidth);
        this.elementArray.setColumGap(0);
        this.balancedHierarchyTree = this.redBlackTree.getBalancedHierarchyTree(this.nodeWidth);
        this.animationNode = this.redBlackTree.getAnimationNode();
        this.growNode = this.redBlackTree.getGrowNode();
        int width = 0;
        int height = 0;
        if (this.balancedHierarchyTree != null) {
            final Rectangle rectangle = this.balancedHierarchyTree.getRectangle();
            width = Math.max(this.elementArray.getWidth(), rectangle.width);
            height = 3 * this.yBuffer + rectangle.height;
        }
        else {
            width = this.elementArray.getWidth();
            height = this.yBuffer + this.elementArray.getHeight();
        }
        width = width + 2 * this.xBuffer;
        height = height + 2 * this.yBuffer;
        this.setSize(width, height);
        this.elementArray.setLocation(this.getSize().width / 2 - this.elementArray.getWidth() / 2, this.yBuffer);
        if (this.balancedHierarchyTree != null) {
            this.balancedHierarchyTree.plantTree(this.getSize().width / 2 - this.nodeWidth / 2, 3 * this.yBuffer + this.elementArray.getHeight());
        }
    }
    
    public void animate234() {
        final Graphics g = this.getGraphics();
        final Hashtable<K, V> currentPositions = this.getPositions(this.balancedHierarchyTree);
        if (this.oldPositions != null) {
            if (this.growNode != null) {
                final Point currentPoint = (Point)((Point)this.oldPositions.get(new Integer(this.growNode.getIdentifier())));
                this.growNode.setPosition(currentPoint);
                this.addTween(new DeleteTween(null, this.growNode, this.numberOfTweenSteps / 3));
            }
            if (this.animationNode != null) {
                final Point oldPoint = (Point)((Point)this.oldPositions.get(new Integer(this.animationNode.getIdentifier())));
                final Point currentPoint2 = (Point)((Point)currentPositions.get(new Integer(this.animationNode.getIdentifier())));
                if (oldPoint != null && currentPoint2 != null && (oldPoint.x != currentPoint2.x || oldPoint.y != currentPoint2.y)) {
                    this.addTween(new MoveTween(null, this.animationNode, oldPoint, currentPoint2, false, this.numberOfTweenSteps / 3));
                }
            }
        }
        if (this.oldPositions != null) {
            final MultipleTween moveTweens = this.getMoveTweens(this.balancedHierarchyTree, this.oldPositions, currentPositions);
            if (moveTweens.getNumberOfTweens() > 0) {
                this.addTween(moveTweens);
            }
        }
        this.oldPositions = currentPositions;
        final MultipleTween multiTweens = this.redBlackTree.generateTweens(this, this.balancedHierarchyTree, this.numberOfTweenSteps / 3);
        if (multiTweens != null) {
            this.addTween(multiTweens);
        }
        if (this.tweens.getNumberOfTweens() > 0) {
            this.tweens.run();
        }
    }
    
    public void processRepaintEvent(final RepaintEvent e) {
        if (e.paramObj != null) {
            this.redBlackTree = (Tree234Tree)((Tree234Tree)e.paramObj);
            if (!this.showAs234Only) {
                this.processRB();
            }
            else {
                this.process234();
                this.animate234();
            }
            this.redBlackTree.removeAllAnimationRequests();
            this.repaint();
        }
    }
    
    protected void handleColorSelection(final ColorSelection colorSelection) {
    }
    
    protected void handleFontSelection(final FontSelection fontSelection) {
    }
    
    static {
        PARENT_LABEL = Messages.getString("Tree234Canvas.1");
        GRAND_PARENT_LABEL = Messages.getString("Tree234Canvas.2");
        GREAT_GRAND_PARENT_LABEL = Messages.getString("Tree234Canvas.3");
    }
}
