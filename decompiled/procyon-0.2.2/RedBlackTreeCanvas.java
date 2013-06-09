import localization.*;
import java.awt.*;
import java.util.*;
import com.cim.AIA.*;

public class RedBlackTreeCanvas extends AlgorithmCanvas
{
    private static final long serialVersionUID = -3993390322636833261L;
    protected static final String PTR_LABEL;
    protected static final String PARENT_LABEL;
    protected static final String GRAND_PARENT_LABEL;
    protected static final String GREAT_GRAND_PARENT_LABEL;
    protected static final String[] rotateRight;
    protected static final String[] rotateLeft;
    protected RedBlackTree redBlackTree;
    protected HierarchyTree hierarchyTree;
    protected HierarchyTree tuteTree;
    protected HierarchyTree balancedHierarchyTree;
    protected Vector<Line> saveLine;
    protected Vector<Line> animateLine;
    protected Vector<LineMoveRequest> lineMoveRequests;
    protected Node animationNode;
    protected Node growNode;
    protected ElementArray elementArray;
    protected Hashtable<Integer, Point> oldPositions;
    protected int nodeWidth;
    protected int xTextPadding;
    protected int yBuffer;
    protected int xBuffer;
    protected Line ptrLine;
    protected Line parentLine;
    protected Line grandParentLine;
    protected Line greatGrandParentLine;
    protected boolean showAs234Only;
    protected boolean showTute;
    protected boolean showCode;
    protected int highLightedLine;
    
    public RedBlackTreeCanvas() {
        super();
        this.saveLine = new Vector();
        this.animateLine = new Vector();
        this.nodeWidth = 20;
        this.xTextPadding = 30;
        this.yBuffer = 30;
        this.xBuffer = 30;
        this.showAs234Only = false;
        this.showTute = false;
        this.showCode = false;
        this.highLightedLine = 0;
    }
    
    public void animate234() {
        final Graphics g = this.getGraphics();
        final Hashtable<Integer, Point> currentPositions = this.getPositions(this.balancedHierarchyTree);
        if (this.oldPositions != null) {
            if (this.growNode != null) {
                final Point currentPoint = (Point)this.oldPositions.get(new Integer(this.growNode.getIdentifier()));
                this.growNode.setPosition(currentPoint);
                this.addTween(new DeleteTween(null, this.growNode, this.numberOfTweenSteps));
            }
            if (this.animationNode != null) {
                final Point oldPoint = (Point)this.oldPositions.get(new Integer(this.animationNode.getIdentifier()));
                final Point currentPoint2 = (Point)currentPositions.get(new Integer(this.animationNode.getIdentifier()));
                if (oldPoint != null && currentPoint2 != null && (oldPoint.x != currentPoint2.x || oldPoint.y != currentPoint2.y)) {
                    this.addTween(new MoveTween(null, this.animationNode, oldPoint, currentPoint2, false, this.numberOfTweenSteps));
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
        final MultipleTween multiTweens = this.redBlackTree.generateTweens(this, this.balancedHierarchyTree, this.numberOfTweenSteps);
        if (multiTweens != null) {
            this.addTween(multiTweens);
        }
        if (this.tweens.getNumberOfTweens() > 0) {
            this.tweens.run();
        }
    }
    
    public void animateRB() {
        final Graphics g = this.getGraphics();
        if (g != null) {
            this.initaliseLines(g, 2 * this.yBuffer + this.elementArray.getHeight());
        }
        final Hashtable<Integer, Point> currentPositions = this.getPositions(this.hierarchyTree);
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
    
    public void animateTute() {
        final Graphics g = this.getGraphics();
        final Hashtable<Integer, Point> currentPositions = this.getPositions(this.tuteTree);
        if (this.oldPositions != null) {
            final MultipleTween moveTweens2 = this.getLineTweens(this.tuteTree, this.oldPositions, currentPositions);
            if (moveTweens2.getNumberOfTweens() > 0) {
                this.addTween(moveTweens2);
            }
            final MultipleTween moveTweens = this.getMoveTweens(this.tuteTree, this.oldPositions, currentPositions);
            if (moveTweens.getNumberOfTweens() > 0) {
                this.addTween(moveTweens);
            }
        }
        if (this.tweens.getNumberOfTweens() > 0) {
            this.tweens.run();
        }
        this.oldPositions = currentPositions;
    }
    
    public void display234(final Graphics g) {
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
    
    public void displayAlgorithm(final Graphics g) {
        if (this.redBlackTree != null) {
            if (!this.showAs234Only && !this.showTute && !this.showCode) {
                this.displayRB(g);
            }
            if (this.showAs234Only && !this.showTute) {
                if (this.redBlackTree.update234) {
                    this.display234(g);
                }
                else {
                    g.drawString(Messages.getString("RedBlackTreeCanvas.22"), 20, 20);
                }
            }
            if (!this.showAs234Only && this.showTute) {
                this.displayTute(g);
            }
            if (this.showCode) {
                this.displayCode(g);
            }
        }
    }
    
    public void displayCode(final Graphics g) {
        int XPos = 30;
        int YPos = 30;
        final int Y_INC = 15;
        int lineCounter = 1;
        String tempStr;
        for (int i = 0; i < RedBlackTreeCanvas.rotateLeft.length; ++i) {
            tempStr = RedBlackTreeCanvas.rotateLeft[i];
            if (tempStr.equals("}")) {
                XPos = XPos - 30;
            }
            if (lineCounter == this.highLightedLine) {
                g.setColor(CodeCanvas.DEFAULT_HIGHLIGHT_COLOR);
                g.fillRect(XPos, YPos - Y_INC, g.getFontMetrics().stringWidth(tempStr), g.getFontMetrics().getHeight());
                g.setColor(Color.black);
            }
            g.drawString(tempStr, XPos, YPos);
            if (tempStr.equals("{")) {
                XPos = XPos + 30;
            }
            YPos = YPos + Y_INC;
            ++lineCounter;
        }
        YPos = YPos + Y_INC;
        for (int i = 0; i < RedBlackTreeCanvas.rotateRight.length; ++i) {
            tempStr = RedBlackTreeCanvas.rotateRight[i];
            if (tempStr.equals("}")) {
                XPos = XPos - 30;
            }
            if (lineCounter == this.highLightedLine) {
                g.setColor(CodeCanvas.DEFAULT_HIGHLIGHT_COLOR);
                g.fillRect(XPos, YPos - Y_INC, g.getFontMetrics().stringWidth(tempStr), g.getFontMetrics().getHeight());
                g.setColor(Color.black);
            }
            g.drawString(tempStr, XPos, YPos);
            if (tempStr.equals("{")) {
                XPos = XPos + 30;
            }
            YPos = YPos + Y_INC;
            ++lineCounter;
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
        if (this.elementArray != null) {
            this.elementArray.draw(g);
        }
        if (this.hierarchyTree != null) {
            this.hierarchyTree.draw(g);
        }
    }
    
    public void displayTute(final Graphics g) {
        if (this.tuteTree != null) {
            this.tuteTree.draw(g);
        }
        for (int i = 0; i < this.animateLine.size(); ++i) {
            final Line aLine = (Line)this.animateLine.elementAt(i);
            aLine.draw(g);
        }
    }
    
    protected HierarchyTree findTree(final HierarchyTree root, final int id) {
        if (root == null) {
            return null;
        }
        final Node node = root.getNodeAt(1);
        if (node.getIdentifier() == id) {
            return root;
        }
        final HierarchyTree a = this.findTree(root.getLeftChild(), id);
        if (a != null) {
            return a;
        }
        final HierarchyTree b = this.findTree(root.getRightChild(), id);
        if (b != null) {
            return b;
        }
        return null;
    }
    
    protected MultipleTween getLineTweens(final HierarchyTree hTree, final Hashtable<Integer, Point> oldPositions, final Hashtable<Integer, Point> currentPositions) {
        final MultipleTween multipleTweens = new MultipleTween(this);
        this.animateLine.removeAllElements();
        if (this.redBlackTree.getRemoveSaveLine()) {
            this.saveLine.removeAllElements();
        }
        for (int i = 0; i < this.saveLine.size(); ++i) {
            this.animateLine.addElement(this.saveLine.elementAt(i));
        }
        this.getLineTweens(hTree, oldPositions, currentPositions, multipleTweens);
        return multipleTweens;
    }
    
    protected void getLineTweens(final HierarchyTree hTree, final Hashtable<Integer, Point> oldPositions, final Hashtable<Integer, Point> currentPositions, final MultipleTween multipleTweens) {
        if (hTree != null) {
            for (int i = 0; i < this.lineMoveRequests.size(); ++i) {
                final LineMoveRequest lineMoveRequest = (LineMoveRequest)this.lineMoveRequests.elementAt(i);
                final int toLineId = lineMoveRequest.getToPositionInsertedId();
                final int fromLineId = lineMoveRequest.getFromPositionInsertedId();
                final int parentLineId = lineMoveRequest.getParentPositionInsertedId();
                if (toLineId < 0) {
                    final Point fromPoint = (Point)oldPositions.get(new Integer(fromLineId));
                    final HierarchyTree theTree = this.findTree(hTree, fromLineId);
                    final Line tempLine = theTree.getLine().copy();
                    final Node node = theTree.getNodeAt(1);
                    final Point from = new Point(tempLine.getIntX2(), tempLine.getIntY2());
                    Point to;
                    if (toLineId == -50) {
                        to = new Point(tempLine.getIntX2() - node.getWidth(), tempLine.getIntY2() + 35);
                    }
                    else {
                        to = new Point(tempLine.getIntX2() + node.getWidth(), tempLine.getIntY2() + 35);
                    }
                    final Line tempLine2 = theTree.getLine().copy();
                    tempLine2.setEndPosition(to);
                    final MoveTween moveTween = new MoveTween(null, tempLine, from, to, false, this.numberOfTweenSteps);
                    multipleTweens.add(moveTween);
                    this.animateLine.addElement(tempLine);
                    this.saveLine.addElement(tempLine2);
                }
                else if (fromLineId < 0) {
                    final Point toPoint = (Point)oldPositions.get(new Integer(toLineId));
                    final HierarchyTree theTree = this.findTree(hTree, parentLineId);
                    Line tempLine2;
                    HierarchyTree theTree2;
                    Line tempLine2;
                    if (fromLineId == -50) {
                        theTree2 = theTree.getLeftChild().getLine().copy();
                        tempLine2 = theTree.getLeftChild().getLine().copy();
                    }
                    else {
                        theTree2 = theTree.getRightChild().getLine().copy();
                        tempLine2 = theTree.getRightChild().getLine().copy();
                    }
                    final Node node2 = theTree.getNodeAt(1);
                    final Line parentLine = theTree.getLine().copy();
                    Point from2;
                    if (fromLineId == -50) {
                        from2 = new Point(parentLine.getIntX2() - node2.getWidth(), parentLine.getIntY2() + 40);
                    }
                    else {
                        from2 = new Point(parentLine.getIntX2() + node2.getWidth(), parentLine.getIntY2() + 40);
                    }
                    final Line tempLine3 = new Point(toPoint.x + node2.getWidth() / 2, toPoint.y);
                    theTree2.setEndPosition(from2);
                    tempLine2.setEndPosition(tempLine3);
                    theTree2.setColor(RedBlackTree.tuteHighLightColor);
                    tempLine2.setColor(RedBlackTree.tuteHighLightColor);
                    final MoveTween moveTween2 = new MoveTween(null, theTree2, from2, tempLine3, false, this.numberOfTweenSteps);
                    multipleTweens.add(moveTween2);
                    this.animateLine.addElement(theTree2);
                    this.saveLine.addElement(tempLine2);
                }
                else {
                    final Point fromPoint2 = (Point)oldPositions.get(new Integer(lineMoveRequest.getFromPositionInsertedId()));
                    final Point toPoint2 = (Point)oldPositions.get(new Integer(lineMoveRequest.getToPositionInsertedId()));
                    final HierarchyTree theTree2 = this.findTree(hTree, lineMoveRequest.getFromPositionInsertedId());
                    final Line tempLine4 = theTree2.getLine().copy();
                    final Node node2 = theTree2.getNodeAt(1);
                    final Point from3 = new Point(tempLine4.getIntX2(), tempLine4.getIntY2());
                    final Point to2 = new Point(toPoint2.x + node2.getWidth() / 2, toPoint2.y);
                    final Line tempLine3 = theTree2.getLine().copy();
                    tempLine3.setEndPosition(to2);
                    final MoveTween moveTween2 = new MoveTween(null, tempLine4, from3, to2, false, this.numberOfTweenSteps);
                    multipleTweens.add(moveTween2);
                    this.animateLine.addElement(tempLine4);
                    this.saveLine.addElement(tempLine3);
                }
            }
        }
    }
    
    protected MultipleTween getMoveTweens(final HierarchyTree hTree, final Hashtable<Integer, Point> oldPositions, final Hashtable<Integer, Point> currentPositions) {
        final MultipleTween multipleTweens = new MultipleTween(this);
        this.getMoveTweens(hTree, oldPositions, currentPositions, multipleTweens);
        return multipleTweens;
    }
    
    protected void getMoveTweens(final HierarchyTree hTree, final Hashtable<Integer, Point> oldPositions, final Hashtable<Integer, Point> currentPositions, final MultipleTween multipleTweens) {
        if (hTree != null) {
            final Node node = hTree.getNodeAt(1);
            final Node tNode = hTree.getNodeAt(2);
            if (node != null && tNode == null) {
                final Point oldPoint = (Point)oldPositions.get(new Integer(node.getIdentifier()));
                final Point currentPoint = (Point)currentPositions.get(new Integer(node.getIdentifier()));
                if (oldPoint != null && currentPoint != null && (oldPoint.x != currentPoint.x || oldPoint.y != currentPoint.y)) {
                    multipleTweens.add(new MoveTween(null, hTree, oldPoint, currentPoint, false, this.numberOfTweenSteps));
                }
            }
            final Vector<Tree> children = hTree.getChildren();
            for (int i = 0; i < children.size(); ++i) {
                final HierarchyTree childHTree = (HierarchyTree)((HierarchyTree)children.elementAt(i));
                this.getMoveTweens(childHTree, oldPositions, currentPositions, multipleTweens);
            }
        }
    }
    
    protected Hashtable<Integer, Point> getPositions(final HierarchyTree hTree) {
        final Hashtable<Integer, Point> positions = new Hashtable();
        this.getPositions(hTree, positions);
        return positions;
    }
    
    protected void getPositions(final HierarchyTree hTree, final Hashtable<Integer, Point> positions) {
        if (hTree != null) {
            final Vector<Node> nodes = hTree.getNodes();
            for (int v = 0; v < nodes.size(); ++v) {
                final Node node = (Node)nodes.elementAt(v);
                if (node != null && node.getIsVisible()) {
                    final int w = node.getWidth();
                    positions.put(new Integer(node.getIdentifier()), new Point(hTree.getX() + v * w, hTree.getY()));
                }
            }
            final Vector<Tree> children = hTree.getChildren();
            for (int i = 0; i < children.size(); ++i) {
                final HierarchyTree childHTree = (HierarchyTree)((HierarchyTree)children.elementAt(i));
                this.getPositions(childHTree, positions);
            }
        }
    }
    
    protected void handleColorSelection(final ColorSelection colorSelection) {
    }
    
    protected void handleFontSelection(final FontSelection fontSelection) {
    }
    
    protected void initaliseLines(final Graphics g, final int yPoint) {
        int xPosition = this.getSize().width / 2 - (g.getFontMetrics().stringWidth(RedBlackTreeCanvas.PTR_LABEL + RedBlackTreeCanvas.PARENT_LABEL + RedBlackTreeCanvas.GRAND_PARENT_LABEL + RedBlackTreeCanvas.GREAT_GRAND_PARENT_LABEL) + 3 * this.xTextPadding) / 2;
        final Object o = null;
        this.greatGrandParentLine = o;
        this.grandParentLine = o;
        this.parentLine = o;
        this.ptrLine = o;
        this.ptrLine = this.initialiseLine(g, new Point(xPosition, yPoint), this.redBlackTree.getPtrNode(), RedBlackTreeCanvas.PTR_LABEL);
        xPosition = xPosition + (g.getFontMetrics().stringWidth(RedBlackTreeCanvas.PTR_LABEL) + this.xTextPadding);
        this.parentLine = this.initialiseLine(g, new Point(xPosition, yPoint), this.redBlackTree.getParentNode(), RedBlackTreeCanvas.PARENT_LABEL);
        xPosition = xPosition + (g.getFontMetrics().stringWidth(RedBlackTreeCanvas.PARENT_LABEL) + this.xTextPadding);
        this.grandParentLine = this.initialiseLine(g, new Point(xPosition, yPoint), this.redBlackTree.getGrandParentNode(), RedBlackTreeCanvas.GRAND_PARENT_LABEL);
        xPosition = xPosition + (g.getFontMetrics().stringWidth(RedBlackTreeCanvas.GRAND_PARENT_LABEL) + this.xTextPadding);
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
    
    public void process234() {
        this.balancedHierarchyTree = this.redBlackTree.getBalancedHierarchyTree(this.nodeWidth);
        int width = 0;
        int height = 0;
        if (this.balancedHierarchyTree != null) {
            final Rectangle rectangle = this.balancedHierarchyTree.getRectangle();
            width = rectangle.width;
            height = rectangle.height;
        }
        width = width + 2 * this.xBuffer;
        height = height + 2 * this.yBuffer;
        this.setSize(width, height);
        if (this.balancedHierarchyTree != null) {
            this.balancedHierarchyTree.plantTree(this.getSize().width / 2 - this.nodeWidth / 2, this.yBuffer);
        }
    }
    
    public void processCode() {
        this.highLightedLine = ((RotateAnimation)this.redBlackTree.getAnimThread()).getHighLight();
    }
    
    public void processRB() {
        this.elementArray = this.redBlackTree.getDataElementArray();
        this.elementArray.setAllHaveSameWidth(true);
        this.elementArray.setElementWidth(this.nodeWidth);
        this.elementArray.setColumGap(0);
        this.hierarchyTree = this.redBlackTree.getHierarchyTree(this.nodeWidth);
        if (this.hierarchyTree != null) {
            this.hierarchyTree.plantTree(this.getSize().width / 2 - this.nodeWidth / 2, 3 * this.yBuffer + this.elementArray.getHeight());
        }
        int width = 0;
        int height = 0;
        if (this.hierarchyTree != null) {
            final Rectangle rectangle = this.hierarchyTree.getRectangle();
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
        if (this.hierarchyTree != null) {
            this.hierarchyTree.plantTree(this.getSize().width / 2 - this.nodeWidth / 2, 3 * this.yBuffer + this.elementArray.getHeight());
        }
    }
    
    public void processRepaintEvent(final RepaintEvent e) {
        this.removeAllSelectables();
        if (e.paramObj != null) {
            this.redBlackTree = (RedBlackTree)((RedBlackTree)e.paramObj);
            this.removeSelectionListener(NodeSelection.getInstance(this.redBlackTree));
            this.addSelectionListener(NodeSelection.getInstance(this.redBlackTree));
            if (!this.showAs234Only && !this.showTute && !this.showCode) {
                this.processRB();
                this.animateRB();
            }
            if (this.showAs234Only && !this.showTute) {
                this.process234();
            }
            if (!this.showAs234Only && this.showTute) {
                this.processTute();
                this.animateTute();
            }
            if (this.showCode) {
                this.processCode();
            }
            this.redBlackTree.removeAllAnimationRequests();
            this.repaint();
        }
    }
    
    public void processTute() {
        this.tuteTree = this.redBlackTree.getTuteTree(this.nodeWidth);
        this.lineMoveRequests = this.redBlackTree.getLineMoveRequests();
        if (this.tuteTree != null) {
            this.tuteTree.plantTree(this.getSize().width / 2 - this.nodeWidth / 2, this.yBuffer);
        }
        int width = 0;
        int height = 0;
        if (this.tuteTree != null) {
            final Rectangle rectangle = this.tuteTree.getRectangle();
            width = rectangle.width;
            height = rectangle.height;
        }
        width = width + 2 * this.xBuffer;
        height = height + 2 * this.yBuffer;
        this.setSize(width, height);
        if (this.tuteTree != null) {
            this.tuteTree.plantTree(this.getSize().width / 2 - this.nodeWidth / 2, this.yBuffer);
        }
        if (this.tuteTree != null) {
            this.addSelectable(this.tuteTree);
        }
    }
    
    public void setShowAs234Only(final boolean state) {
        this.showAs234Only = state;
    }
    
    public void setShowCode(final boolean state) {
        this.showCode = state;
    }
    
    public void setShowTute(final boolean state) {
        this.showTute = state;
    }
    
    static {
        PTR_LABEL = Messages.getString("RedBlackTreeCanvas.0");
        PARENT_LABEL = Messages.getString("RedBlackTreeCanvas.1");
        GRAND_PARENT_LABEL = Messages.getString("RedBlackTreeCanvas.2");
        GREAT_GRAND_PARENT_LABEL = Messages.getString("RedBlackTreeCanvas.3");
        rotateRight = new String[] { Messages.getString("RedBlackTreeCanvas.4"), "{", Messages.getString("RedBlackTreeCanvas.6"), Messages.getString("RedBlackTreeCanvas.7"), Messages.getString("RedBlackTreeCanvas.8"), Messages.getString("RedBlackTreeCanvas.9"), "}" };
        rotateLeft = new String[] { Messages.getString("RedBlackTreeCanvas.11"), "{", Messages.getString("RedBlackTreeCanvas.13"), Messages.getString("RedBlackTreeCanvas.14"), Messages.getString("RedBlackTreeCanvas.15"), Messages.getString("RedBlackTreeCanvas.16"), "}" };
    }
}
