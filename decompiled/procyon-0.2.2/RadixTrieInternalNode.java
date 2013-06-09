import java.awt.*;
import com.cim.AIA.*;

public class RadixTrieInternalNode extends RadixTrieNode
{
    private RadixTrieNode right;
    private RadixTrieNode left;
    protected static final int NODE_X_GAP = 8;
    private static final int radius = 3;
    private static final int nullWidth = 6;
    private HierarchyTree hierarchyTree;
    private Color backgroundColor;
    private Color DEFAULT_COLOR;
    
    RadixTrieInternalNode() {
        super();
        this.backgroundColor = Color.orange;
        this.DEFAULT_COLOR = Color.black;
        this.position = new Point(0, 0);
        this.hierarchyTree = new HierarchyTree();
        final Node node = new Node("", 0);
        node.setWidth(6);
        node.setHeight(6);
        this.hierarchyTree.add(node);
        this.hierarchyTree.addChild(new HierarchyTree());
        this.hierarchyTree.addChild(new HierarchyTree());
        this.hierarchyTree.setDrawBorder(false);
        this.left = new RadixTrieNullNode(this);
        this.right = new RadixTrieNullNode(this);
    }
    
    public RadixTrieInternalNode getParent() {
        return this.parent;
    }
    
    public int getRadius() {
        return 3;
    }
    
    public Point getPosition() {
        return new Point(this.position);
    }
    
    public void setPosition(final Point position) {
        RadixTrieInternalNode.upperLeft = new Point();
        RadixTrieInternalNode.upperLeft.y = position.y - 3;
        RadixTrieInternalNode.upperLeft.x = position.x - 3;
        RadixTrieInternalNode.lowerRight = new Point();
        RadixTrieInternalNode.lowerRight.y = position.y + 3;
        RadixTrieInternalNode.lowerRight.x = position.x + 3;
        Point currentPosition;
        if (this.left != null) {
            currentPosition = this.left.layout(position.x - 8, position.y + 20);
            currentPosition = this.left.layout(position.x + position.x - currentPosition.x - 8, position.y + 20);
        }
        this.position = position;
        currentPosition = new Point(position);
        if (this.right != null) {
            this.right.layout(currentPosition.x + 4, currentPosition.y + 20);
        }
    }
    
    protected synchronized Point layout(final int x, final int y) {
        Point position = new Point(x, y);
        if (this.left != null) {
            position = this.left.layout(x, y + 20);
        }
        final Point point = position;
        point.x = point.x + 4;
        this.position = position;
        if (position.x - 3 < RadixTrieInternalNode.upperLeft.x) {
            RadixTrieInternalNode.upperLeft.x = position.x - 3;
        }
        if (position.y - 3 < RadixTrieInternalNode.upperLeft.y) {
            RadixTrieInternalNode.upperLeft.y = position.y - 3;
        }
        if (position.x + 3 > RadixTrieInternalNode.lowerRight.x) {
            RadixTrieInternalNode.lowerRight.x = position.x + 3;
        }
        if (position.y + 3 > RadixTrieInternalNode.lowerRight.y) {
            RadixTrieInternalNode.lowerRight.y = position.y + 3;
        }
        if (this.right != null) {
            position = this.right.layout(position.x + 4, position.y + 20);
        }
        return new Point(position.x, position.y - 20);
    }
    
    public Rectangle getRectangle() {
        if (this.position == null) {
            this.position = new Point();
        }
        this.setPosition(this.position);
        return new Rectangle(RadixTrieInternalNode.upperLeft.x, RadixTrieInternalNode.upperLeft.y, RadixTrieInternalNode.lowerRight.x - RadixTrieInternalNode.upperLeft.x, RadixTrieInternalNode.lowerRight.y - RadixTrieInternalNode.upperLeft.y);
    }
    
    public void draw(final Graphics g) {
        g.setColor(this.DEFAULT_COLOR);
        if (this.left != null) {
            if (this.left.getIsLinkedIn()) {
                Line leftLink;
                if (this.left.getIsOnPath()) {
                    leftLink = new Line(this.position.x, this.position.y, this.left.position.x, this.left.position.y, Color.red);
                    leftLink.showAsThick(true);
                }
                else {
                    leftLink = new Line(this.position.x, this.position.y, this.left.position.x, this.left.position.y, Color.black);
                }
                leftLink.draw(g);
            }
            this.left.draw(g);
        }
        else {
            g.drawLine(this.position.x, this.position.y, this.position.x - 8, this.position.y + 13);
        }
        if (this.right != null) {
            if (this.right.getIsLinkedIn()) {
                Line rightLink;
                if (this.right.getIsOnPath()) {
                    rightLink = new Line(this.position.x, this.position.y, this.right.position.x, this.right.position.y, Color.red);
                    rightLink.showAsThick(true);
                }
                else {
                    rightLink = new Line(this.position.x, this.position.y, this.right.position.x, this.right.position.y, Color.black);
                }
                rightLink.draw(g);
            }
            this.right.draw(g);
        }
        else {
            g.drawLine(this.position.x, this.position.y, this.position.x + 8, this.position.y + 13);
        }
        if (this.parent == null) {
            this.drawNode(g);
        }
        if (this.left != null) {
            this.left.drawNode(g);
        }
        if (this.right != null) {
            this.right.drawNode(g);
        }
    }
    
    public void drawNode(final Graphics g) {
        g.setColor(this.backgroundColor.brighter());
        g.fillOval(this.position.x - 3 - 1, this.position.y - 3 - 1, 6, 6);
        g.setColor(this.backgroundColor.darker());
        g.fillOval(this.position.x - 3 + 1, this.position.y - 3 + 1, 6, 6);
        g.setColor(this.backgroundColor);
        g.fillOval(this.position.x - 3, this.position.y - 3, 6, 6);
        g.setColor(this.DEFAULT_COLOR);
    }
    
    public void setLeft(final RadixTrieNode left) {
        this.left = left;
        this.left.parent = this;
        this.hierarchyTree.removeChild(this.hierarchyTree.getChild(0));
        this.hierarchyTree.insertChildAt(left.getHierarchyTree(), 0);
    }
    
    public void setRight(final RadixTrieNode right) {
        this.right = right;
        this.right.parent = this;
        this.hierarchyTree.removeChild(this.hierarchyTree.getChild(1));
        this.hierarchyTree.insertChildAt(right.getHierarchyTree(), 1);
    }
    
    public void setBackgroundColor(final Color color) {
        this.backgroundColor = color;
    }
    
    public RadixTrieNode getLeft() {
        return this.left;
    }
    
    public RadixTrieNode getRight() {
        return this.right;
    }
    
    public HierarchyTree getHierarchyTree() {
        return this.hierarchyTree;
    }
}
