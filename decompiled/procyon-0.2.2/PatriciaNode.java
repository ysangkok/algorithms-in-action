import localization.*;
import java.awt.*;
import com.cim.AIA.*;

public class PatriciaNode implements Drawable
{
    static final int xGap = 8;
    static final int yGap = 10;
    static final int radius = 3;
    static final int newNodeRadius = 5;
    static PatriciaNode newestNode;
    private static final Color RECTAGLE_COLOR;
    private static final Color DEFAULT_COLOR;
    private static final Color NEW_NODE_FILL_COLOR;
    private static final Color NEW_NODE_BORDER_COLOR;
    private static final Color TREE_BIT_COLOR;
    private static final Color TREE_BIT_HIGHLIGHT_COLOR;
    private PatriciaNode left;
    private PatriciaNode right;
    private HierarchyTree hierarchyTree;
    private byte bit;
    private PatriciaTreeDataItem dataItem;
    private Node body;
    boolean isSubHead;
    boolean leftIsKnown;
    boolean rightIsKnown;
    Point position;
    int depth;
    private PatriciaNode parent;
    
    public static void clearNewNode() {
        PatriciaNode.newestNode = null;
    }
    
    public static PatriciaNode getNewNode() {
        return PatriciaNode.newestNode;
    }
    
    PatriciaNode() {
        super();
        this.isSubHead = false;
        this.leftIsKnown = true;
        this.rightIsKnown = true;
        this.bit = -1;
        this.dataItem = new PatriciaTreeDataItem();
        this.body = new Node("-1", this.dataItem.getKey());
        this.body.setBackgroundColor(PatriciaNode.TREE_BIT_COLOR);
        this.body.setMarkersBelowValue(false);
        this.right = this;
        this.left = this;
        clearNewNode();
    }
    
    PatriciaNode(final PatriciaTreeDataItem dataItem, final byte bit) {
        super();
        this.isSubHead = false;
        this.leftIsKnown = true;
        this.rightIsKnown = true;
        this.bit = bit;
        this.dataItem = dataItem;
        this.body = new Node(new Integer(bit), dataItem.getKey());
        this.body.setBackgroundColor(PatriciaNode.TREE_BIT_COLOR);
        clearNewNode();
        PatriciaNode.newestNode = this;
    }
    
    private void calculateDepths() {
        PatriciaNode head = this;
        while (head.parent != null) {
            head = head.parent;
        }
        head.getDepth();
    }
    
    public void draw(final Graphics g) {
        if (this.isTreeEmpty()) {
            g.drawString(Messages.getString("PatriciaNode.1"), this.position.x, this.position.y);
        }
        if (this.bit < 0) {
            this.left.isSubHead = true;
            if (this.left.bit > this.bit) {
                this.left.draw(g);
            }
        }
        else {
            if (PatriciaNode.newestNode != null && PatriciaNode.newestNode.parent == null && this.parent == PatriciaNode.newestNode) {
                int xOffset;
                if (this.parent.left == this) {
                    xOffset = 8;
                }
                else {
                    xOffset = -8;
                }
                g.drawLine(this.position.x + xOffset, this.position.y - 10, this.position.x, this.position.y);
                g.setColor(PatriciaNode.NEW_NODE_FILL_COLOR);
                g.fillOval(this.position.x - 5 + xOffset, this.position.y - 5 - 10, 10, 10);
                g.setColor(PatriciaNode.NEW_NODE_BORDER_COLOR);
                g.drawOval(this.position.x - 5 + xOffset, this.position.y - 5 - 10, 10, 10);
            }
            g.setColor(PatriciaNode.DEFAULT_COLOR);
            if (this.left.bit > this.bit) {
                if (this.leftIsKnown) {
                    g.drawLine(this.position.x, this.position.y, this.left.position.x, this.left.position.y);
                    this.left.draw(g);
                }
                else {
                    g.setColor(PatriciaNode.RECTAGLE_COLOR);
                    g.drawRect(this.left.leftEdge(), this.left.position.y, this.left.getWidth(), 10 * this.depth);
                    g.drawLine(this.position.x, this.position.y, this.left.position.x, this.left.position.y);
                    this.left.isSubHead = true;
                    this.left.draw(g, new Point(this.getSubHead().rightEdge() + 16, this.position.y + 10));
                }
            }
            else if (!this.leftIsKnown) {
                g.setColor(PatriciaNode.RECTAGLE_COLOR);
                g.drawRect(this.position.x - 3 - 4, this.position.y + 10, 6, 10 * this.depth);
                g.drawLine(this.position.x, this.position.y, this.position.x - 4, this.position.y + 10);
                if (PatriciaNode.newestNode != null && PatriciaNode.newestNode.parent == null) {
                    g.setColor(PatriciaNode.NEW_NODE_FILL_COLOR);
                    g.fillOval(this.getSubHead().rightEdge() - 5 + 16, this.position.y - 5 + 10, 6, 10);
                    g.setColor(PatriciaNode.NEW_NODE_BORDER_COLOR);
                    g.drawOval(this.getSubHead().rightEdge() - 5 + 16, this.position.y - 5 + 10, 10, 10);
                }
                g.setColor(PatriciaNode.DEFAULT_COLOR);
            }
            if (this.right.bit > this.bit) {
                if (this.rightIsKnown) {
                    g.drawLine(this.position.x, this.position.y, this.right.position.x, this.right.position.y);
                    this.right.draw(g);
                }
                else {
                    g.setColor(PatriciaNode.RECTAGLE_COLOR);
                    g.drawRect(this.right.leftEdge(), this.right.position.y, this.right.getWidth(), 10 * this.depth);
                    g.drawLine(this.position.x, this.position.y, this.right.position.x, this.right.position.y);
                    this.right.isSubHead = true;
                    this.right.draw(g, new Point(this.getSubHead().rightEdge() + 16, this.position.y + 10));
                }
            }
            else if (!this.rightIsKnown) {
                g.setColor(PatriciaNode.RECTAGLE_COLOR);
                g.drawRect(this.position.x + 4, this.position.y + 10, 6, 10 * this.depth);
                g.drawLine(this.position.x, this.position.y, this.position.x + 4, this.position.y + 10);
                if (PatriciaNode.newestNode != null && PatriciaNode.newestNode.parent == null) {
                    g.setColor(PatriciaNode.NEW_NODE_FILL_COLOR);
                    g.fillOval(this.getSubHead().rightEdge() - 5 + 16, this.position.y + 10 - 5, 10, 10);
                    g.setColor(PatriciaNode.NEW_NODE_BORDER_COLOR);
                    g.drawOval(this.getSubHead().rightEdge() - 5 + 16, this.position.y + 10 - 5, 10, 10);
                }
            }
            if (PatriciaNode.newestNode != this) {
                g.setColor(Color.white);
                g.fillOval(this.position.x - 3, this.position.y - 3, 6, 6);
                g.setColor(PatriciaNode.DEFAULT_COLOR);
                g.drawOval(this.position.x - 3, this.position.y - 3, 6, 6);
            }
            else {
                g.setColor(PatriciaNode.NEW_NODE_FILL_COLOR);
                g.fillOval(this.position.x - 5, this.position.y - 5, 10, 10);
                g.setColor(PatriciaNode.NEW_NODE_BORDER_COLOR);
                g.drawOval(this.position.x - 5, this.position.y - 5, 10, 10);
            }
        }
        g.setColor(PatriciaNode.DEFAULT_COLOR);
    }
    
    public void draw(final Graphics g, final Point position) {
        this.setPosition(position);
        this.draw(g);
    }
    
    public byte getBit() {
        return this.bit;
    }
    
    public Node getBody() {
        return this.body;
    }
    
    public PatriciaTreeDataItem getDataItem() {
        return this.dataItem;
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
    
    public int getHeight() {
        PatriciaNode head = this;
        while (head.parent != null) {
            head = head.parent;
        }
        return head.getDepth() * 10;
    }
    
    public HierarchyTree getHierarchyTree() {
        return this.hierarchyTree;
    }
    
    public PatriciaNode getLeft() {
        return this.left;
    }
    
    public Point getPosition() {
        return this.position;
    }
    
    public PatriciaNode getRight() {
        return this.right;
    }
    
    PatriciaNode getSubHead() {
        PatriciaNode subHead = this;
        while (subHead.parent != null && !subHead.isSubHead) {
            subHead = subHead.parent;
        }
        return subHead;
    }
    
    private int getWidth() {
        return this.rightEdge() - this.leftEdge();
    }
    
    public boolean isTreeEmpty() {
        return this.right == this && this.left == this;
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
    
    public void setHierarchyTree(final HierarchyTree newTree) {
        this.hierarchyTree = newTree;
    }
    
    public void setLeft(final PatriciaNode node) {
        this.left = node;
        if (this.left.bit > this.bit) {
            this.left.parent = this;
        }
        this.calculateDepths();
    }
    
    public void setLeftIsKnown(final boolean leftIsKnown) {
        this.leftIsKnown = leftIsKnown;
    }
    
    public void setPosition(final Point position) {
        this.layout(position.x - 8, position.y);
    }
    
    public void setRight(final PatriciaNode node) {
        this.right = node;
        if (this.right.bit > this.bit) {
            this.right.parent = this;
        }
        this.calculateDepths();
    }
    
    public void setRightIsKnown(final boolean rightIsKnown) {
        this.rightIsKnown = rightIsKnown;
    }
    
    static {
        RECTAGLE_COLOR = Color.gray;
        DEFAULT_COLOR = Color.black;
        NEW_NODE_FILL_COLOR = Color.green;
        NEW_NODE_BORDER_COLOR = Color.red;
        TREE_BIT_COLOR = Color.yellow;
        TREE_BIT_HIGHLIGHT_COLOR = new Color(255, 255, 192);
    }
}
