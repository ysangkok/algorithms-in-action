import java.awt.*;
import com.cim.AIA.*;

public class RadixTrieExternalNode extends RadixTrieNode
{
    private RadixTrieDataItem dataItem;
    protected static final int NODE_X_GAP = 18;
    private static final int radius = 9;
    private HierarchyTree hierarchyTree;
    private Color backgroundColor;
    private Color currentBackgroundColor;
    private int level;
    private static RadixTrieExternalNode newestNode;
    private static RadixTrieExternalNode compareNode;
    private static final boolean isDrawFeet = 0;
    
    RadixTrieExternalNode(final RadixTrieDataItem dataItem) {
        super();
        this.backgroundColor = RadixTrieColors.DEFAULT_NODE_COLOR;
        this.currentBackgroundColor = this.backgroundColor;
        this.dataItem = dataItem;
        this.hierarchyTree = new HierarchyTree();
        final Node node = new Node("" + dataItem.getKey(), 0);
        this.hierarchyTree.add(node);
        this.position = new Point(0, 0);
        RadixTrieExternalNode.newestNode = this;
    }
    
    public static void clearNewestNode() {
        RadixTrieExternalNode.newestNode = null;
    }
    
    public static void clearCompareNode() {
        RadixTrieExternalNode.compareNode = null;
    }
    
    public static void setCompareNode(final RadixTrieExternalNode newCompareNode) {
        RadixTrieExternalNode.compareNode = newCompareNode;
    }
    
    public void setLevel(final int level) {
        this.level = level;
    }
    
    public int getLevel() {
        return this.level;
    }
    
    public RadixTrieInternalNode getParent() {
        return this.parent;
    }
    
    public int getKey() {
        return this.dataItem.getKey();
    }
    
    public int getRadius() {
        return 9;
    }
    
    public Point getPosition() {
        return new Point(this.position);
    }
    
    public void setPosition(final Point position) {
        RadixTrieExternalNode.upperLeft = new Point();
        RadixTrieExternalNode.upperLeft.x = position.x - 9;
        RadixTrieExternalNode.upperLeft.y = position.y - 9;
        RadixTrieExternalNode.lowerRight = new Point();
        RadixTrieExternalNode.lowerRight.x = position.x + 9;
        RadixTrieExternalNode.lowerRight.y = position.y + 9;
        this.position = new Point(position);
    }
    
    protected synchronized Point layout(final int x, final int y) {
        this.position = new Point(x + 9, y);
        if (this.position.x - 9 < RadixTrieExternalNode.upperLeft.x) {
            RadixTrieExternalNode.upperLeft.x = this.position.x - 9;
        }
        if (this.position.y - 9 < RadixTrieExternalNode.upperLeft.y) {
            RadixTrieExternalNode.upperLeft.y = this.position.y - 9;
        }
        if (this.position.x + 9 > RadixTrieExternalNode.lowerRight.x) {
            RadixTrieExternalNode.lowerRight.x = this.position.x + 9;
        }
        if (this.position.y + 9 > RadixTrieExternalNode.lowerRight.y) {
            RadixTrieExternalNode.lowerRight.y = this.position.y + 9;
        }
        return new Point(x + 18, y - 20);
    }
    
    public Rectangle getRectangle() {
        this.setPosition(this.position);
        return new Rectangle(RadixTrieExternalNode.upperLeft.x, RadixTrieExternalNode.upperLeft.y, RadixTrieExternalNode.lowerRight.x - RadixTrieExternalNode.upperLeft.x, RadixTrieExternalNode.lowerRight.y - RadixTrieExternalNode.upperLeft.y);
    }
    
    public void setBackgroundColor(final Color color) {
        this.backgroundColor = color;
    }
    
    public void draw(final Graphics g) {
        if (this.parent == null) {
            this.drawNode(g);
        }
    }
    
    public void drawNode(final Graphics g) {
        final String label = "" + this.dataItem.getKey();
        if (RadixTrieExternalNode.newestNode == this) {
            this.currentBackgroundColor = RadixTrieColors.NEW_NODE_COLOR;
        }
        else if (RadixTrieExternalNode.compareNode == this) {
            this.currentBackgroundColor = RadixTrieColors.COMPARE_NODE_COLOR;
        }
        else {
            this.currentBackgroundColor = this.backgroundColor;
        }
        g.setColor(this.currentBackgroundColor.brighter());
        g.fillOval(this.position.x - 9 - 1, this.position.y - 9 - 1, 18, 18);
        g.setColor(this.currentBackgroundColor.darker());
        g.fillOval(this.position.x - 9 + 1, this.position.y - 9 + 1, 18, 18);
        g.setColor(this.currentBackgroundColor);
        g.fillOval(this.position.x - 9 + 1, this.position.y - 9 + 1, 16, 16);
        g.setColor(Color.black);
        g.drawOval(this.position.x - 9 - 1, this.position.y - 9 - 1, 20, 20);
        g.drawString(label, this.position.x - g.getFontMetrics().stringWidth(label) / 2, this.position.y + g.getFontMetrics().getHeight() / 3);
    }
    
    public HierarchyTree getHierarchyTree() {
        return this.hierarchyTree;
    }
}
