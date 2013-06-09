import java.awt.*;
import com.cim.AIA.*;

public class PatriciaTreeIterNode implements Drawable
{
    private PatriciaTreeIterNode left;
    private PatriciaTreeIterNode right;
    private HierarchyTree hierarchyTree;
    private byte bit;
    private PatriciaTreeIterDataItem dataItem;
    private Node body;
    boolean isSubHead;
    boolean leftIsKnown;
    boolean rightIsKnown;
    private boolean virtual;
    Point position;
    int depth;
    static final int xGap = 8;
    static final int yGap = 10;
    static final int radius = 3;
    static final int newNodeRadius = 5;
    private PatriciaTreeIterNode parent;
    static PatriciaTreeIterNode newestNode;
    private static final Color TREE_BIT_COLOR;
    private static final Color TREE_BIT_HIGHLIGHT_COLOR;
    
    PatriciaTreeIterNode() {
        super();
        this.isSubHead = false;
        this.leftIsKnown = true;
        this.rightIsKnown = true;
        this.virtual = false;
        this.bit = -1;
        this.dataItem = new PatriciaTreeIterDataItem();
        this.body = new Node("-1", this.dataItem.getKey());
        this.body.setHeight(14);
        this.body.setWidth(15);
        this.body.setBackgroundColor(PatriciaTreeIterNode.TREE_BIT_COLOR);
        this.body.setMarkersBelowValue(false);
        this.right = this;
        this.left = this;
        this.clearNewNode();
    }
    
    PatriciaTreeIterNode(final PatriciaTreeIterDataItem dataItem, final byte bit) {
        super();
        this.isSubHead = false;
        this.leftIsKnown = true;
        this.rightIsKnown = true;
        this.virtual = false;
        this.bit = bit;
        this.dataItem = dataItem;
        this.body = new Node(new Integer(bit), dataItem.getKey());
        this.body.setHeight(14);
        this.body.setWidth(15);
        this.body.setBackgroundColor(PatriciaTreeIterNode.TREE_BIT_COLOR);
        this.clearNewNode();
        PatriciaTreeIterNode.newestNode = this;
    }
    
    public PatriciaTreeIterNode copy() {
        return copy(this, this);
    }
    
    public static PatriciaTreeIterNode copy(final PatriciaTreeIterNode root, final PatriciaTreeIterNode currentRoot) {
        PatriciaTreeIterNode newRoot = new PatriciaTreeIterNode(root.getDataItem(), root.getBit());
        final PatriciaTreeIterNode left = root.getLeft();
        final PatriciaTreeIterNode right = root.getRight();
        PatriciaTreeIterNode v1;
        if (left.getBit() > root.getBit()) {
            newRoot.setLeft(copy(left, currentRoot));
        }
        else {
            v1 = new PatriciaTreeIterNode(left.getDataItem(), left.getBit());
            v1.setVirtual(true);
            newRoot.setLeft(v1);
        }
        if (right.getBit() > root.getBit()) {
            newRoot.setRight(copy(right, currentRoot));
        }
        else {
            v1 = new PatriciaTreeIterNode(right.getDataItem(), right.getBit());
            v1.setVirtual(true);
            newRoot.setRight(v1);
        }
        if (root == currentRoot) {
            newRoot = virtualToReal(newRoot, newRoot);
        }
        newRoot.clearNewNode();
        return newRoot;
    }
    
    private static PatriciaTreeIterNode virtualToReal(final PatriciaTreeIterNode root, final PatriciaTreeIterNode currentRoot) {
        if (root.getLeft().getVirtual()) {
            final PatriciaTreeIterNode realLeft = findNode(currentRoot, root.getLeft().getDataItem());
            root.setLeft(realLeft);
        }
        else {
            root.setLeft(virtualToReal(root.getLeft(), currentRoot));
        }
        if (root.getRight().getVirtual()) {
            final PatriciaTreeIterNode realRight = findNode(currentRoot, root.getRight().getDataItem());
            root.setRight(realRight);
        }
        else {
            root.setRight(virtualToReal(root.getRight(), currentRoot));
        }
        return root;
    }
    
    private static PatriciaTreeIterNode findNode(final PatriciaTreeIterNode root, final PatriciaTreeIterDataItem dataItem) {
        PatriciaTreeIterNode result = null;
        if (root.getDataItem() == dataItem) {
            return root;
        }
        if (root.getLeft().getBit() > root.getBit()) {
            result = findNode(root.getLeft(), dataItem);
            if (result != null) {
                return result;
            }
        }
        if (root.getRight().getBit() > root.getBit()) {
            return findNode(root.getRight(), dataItem);
        }
        return null;
    }
    
    public boolean isTreeEmpty() {
        return this.right == this && this.left == this;
    }
    
    public void clearNewNode() {
        if (PatriciaTreeIterNode.newestNode != null) {
            PatriciaTreeIterNode.newestNode.body.setBackgroundColor(PatriciaTreeIterNode.TREE_BIT_COLOR);
        }
        PatriciaTreeIterNode.newestNode = null;
    }
    
    public static PatriciaTreeIterNode getNewNode() {
        return PatriciaTreeIterNode.newestNode;
    }
    
    public void setHierarchyTree(final HierarchyTree newTree) {
        this.hierarchyTree = newTree;
    }
    
    public HierarchyTree getHierarchyTree() {
        return this.hierarchyTree;
    }
    
    public void setLeftIsKnown(final boolean leftIsKnown) {
        this.leftIsKnown = leftIsKnown;
    }
    
    public void setRightIsKnown(final boolean rightIsKnown) {
        this.rightIsKnown = rightIsKnown;
    }
    
    public void setLeft(final PatriciaTreeIterNode node) {
        this.left = node;
        if (this.left.bit > this.bit) {
            this.left.parent = this;
        }
        this.calculateDepths();
    }
    
    public void setRight(final PatriciaTreeIterNode node) {
        this.right = node;
        if (this.right.bit > this.bit) {
            this.right.parent = this;
        }
        this.calculateDepths();
    }
    
    public void setBit(final byte bit) {
        this.bit = bit;
    }
    
    public byte getBit() {
        return this.bit;
    }
    
    public boolean getVirtual() {
        return this.virtual;
    }
    
    public void setVirtual(final boolean state) {
        this.virtual = state;
    }
    
    public PatriciaTreeIterNode getLeft() {
        return this.left;
    }
    
    public PatriciaTreeIterNode getRight() {
        return this.right;
    }
    
    public PatriciaTreeIterDataItem getDataItem() {
        return this.dataItem;
    }
    
    public Node getBody() {
        return this.body;
    }
    
    public void draw(final Graphics g, final Point position) {
        this.setPosition(position);
        this.draw(g);
    }
    
    public void draw(final Graphics g) {
    }
    
    public Point getPosition() {
        return this.position;
    }
    
    public void setPosition(final Point position) {
        this.layout(position.x - 8, position.y);
    }
    
    private Point layout(final int x, final int y) {
        Point position = new Point(x, y);
        if (this.left.bit > this.bit) {
            position = this.left.layout(x, y + 10);
        }
        final Point point = position;
        point.x = point.x + 8;
        this.position = position;
        if (this.right.bit > this.bit) {
            position = this.right.layout(position.x, position.y + 10);
        }
        return new Point(position.x, position.y - 10);
    }
    
    private int leftEdge() {
        if (this.left.bit > this.bit) {
            return this.left.leftEdge();
        }
        return this.position.x - 3;
    }
    
    private int rightEdge() {
        if (this.right.bit > this.bit) {
            return this.right.rightEdge();
        }
        if (!this.rightIsKnown) {
            return this.position.x + 6 + 4;
        }
        return this.position.x + 3;
    }
    
    PatriciaTreeIterNode getSubHead() {
        PatriciaTreeIterNode subHead = this;
        while (subHead.parent != null && !subHead.isSubHead) {
            subHead = subHead.parent;
        }
        return subHead;
    }
    
    public int getHeight() {
        PatriciaTreeIterNode head = this;
        while (head.parent != null) {
            head = head.parent;
        }
        return head.getDepth() * 10;
    }
    
    private int getWidth() {
        return this.rightEdge() - this.leftEdge();
    }
    
    private void calculateDepths() {
        PatriciaTreeIterNode head = this;
        while (head.parent != null) {
            head = head.parent;
        }
        head.getDepth();
    }
    
    private int getDepth() {
        int leftDepth = 0;
        int rightDepth = 0;
        if (this.left != null && this.left.bit > this.bit) {
            leftDepth = this.left.getDepth();
        }
        if (this.right != null && this.right.bit > this.bit) {
            rightDepth = this.right.getDepth();
        }
        this.depth = Math.max(leftDepth, rightDepth) + 1;
        return this.depth;
    }
    
    static {
        TREE_BIT_COLOR = new Color(200, 255, 200);
        TREE_BIT_HIGHLIGHT_COLOR = new Color(255, 255, 192);
    }
}
