import java.awt.*;
import com.cim.AIA.*;

public class RadixTrieMultiExternalNode extends RadixTrieMultiNode
{
    private DataItem dataItem;
    protected static final int NODE_X_GAP = 24;
    private static final int radius = 9;
    private HierarchyTree hierarchyTree;
    private Color backgroundColor;
    private Color currentBackgroundColor;
    private int level;
    private static RadixTrieMultiExternalNode newestNode;
    private static RadixTrieMultiExternalNode compareNode;
    private static final boolean isDrawFeet = 0;
    
    RadixTrieMultiExternalNode(final DataItem dataItem) {
        super();
        this.backgroundColor = RadixTrieMultiColors.DEFAULT_NODE_COLOR;
        this.currentBackgroundColor = this.backgroundColor;
        this.dataItem = dataItem;
        this.hierarchyTree = new HierarchyTree();
        final Node node = new Node("" + dataItem.getKey(), 0);
        this.hierarchyTree.add(node);
        this.position = new Point(0, 0);
        RadixTrieMultiExternalNode.newestNode = this;
    }
    
    public static void clearNewestNode() {
        RadixTrieMultiExternalNode.newestNode = null;
    }
    
    public static void clearCompareNode() {
        RadixTrieMultiExternalNode.compareNode = null;
    }
    
    public static void setCompareNode(final RadixTrieMultiExternalNode newCompareNode) {
        RadixTrieMultiExternalNode.compareNode = newCompareNode;
    }
    
    public void setLevel(final int level) {
        this.level = level;
    }
    
    public int getLevel() {
        return this.level;
    }
    
    public RadixTrieMultiInternalNode getParent() {
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
        RadixTrieMultiExternalNode.upperLeft = new Point();
        RadixTrieMultiExternalNode.upperLeft.x = position.x - 9;
        RadixTrieMultiExternalNode.upperLeft.y = position.y - 9;
        RadixTrieMultiExternalNode.lowerRight = new Point();
        RadixTrieMultiExternalNode.lowerRight.x = position.x + 9;
        RadixTrieMultiExternalNode.lowerRight.y = position.y + 9;
        this.position = new Point(position);
    }
    
    protected synchronized Point layout(final int x, final int y) {
        this.position = new Point(x + 12, y);
        if (this.position.x - 9 < RadixTrieMultiExternalNode.upperLeft.x) {
            RadixTrieMultiExternalNode.upperLeft.x = this.position.x - 9;
        }
        if (this.position.y - 9 < RadixTrieMultiExternalNode.upperLeft.y) {
            RadixTrieMultiExternalNode.upperLeft.y = this.position.y - 9;
        }
        if (this.position.x + 9 > RadixTrieMultiExternalNode.lowerRight.x) {
            RadixTrieMultiExternalNode.lowerRight.x = this.position.x + 9;
        }
        if (this.position.y + 9 > RadixTrieMultiExternalNode.lowerRight.y) {
            RadixTrieMultiExternalNode.lowerRight.y = this.position.y + 9;
        }
        return new Point(x + 24, y - 40);
    }
    
    public Rectangle getRectangle() {
        this.setPosition(this.position);
        return new Rectangle(RadixTrieMultiExternalNode.upperLeft.x, RadixTrieMultiExternalNode.upperLeft.y, RadixTrieMultiExternalNode.lowerRight.x - RadixTrieMultiExternalNode.upperLeft.x, RadixTrieMultiExternalNode.lowerRight.y - RadixTrieMultiExternalNode.upperLeft.y);
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
        if (RadixTrieMultiExternalNode.newestNode == this) {
            this.currentBackgroundColor = RadixTrieMultiColors.NEW_NODE_COLOR;
        }
        else if (RadixTrieMultiExternalNode.compareNode == this) {
            this.currentBackgroundColor = RadixTrieMultiColors.COMPARE_NODE_COLOR;
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
