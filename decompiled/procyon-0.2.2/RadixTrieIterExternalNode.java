import java.awt.*;
import com.cim.AIA.*;

public class RadixTrieIterExternalNode extends RadixTrieIterNode
{
    private DataItem dataItem;
    protected static final int NODE_X_GAP = 18;
    private static final int radius = 9;
    private HierarchyTree hierarchyTree;
    private Color backgroundColor;
    private Color currentBackgroundColor;
    private int level;
    private static RadixTrieIterExternalNode newestNode;
    private static RadixTrieIterExternalNode compareNode;
    private static final boolean isDrawFeet = 0;
    
    RadixTrieIterExternalNode(final DataItem dataItem) {
        super();
        this.backgroundColor = RadixTrieIterColors.DEFAULT_NODE_COLOR;
        this.currentBackgroundColor = this.backgroundColor;
        this.dataItem = dataItem;
        this.hierarchyTree = new HierarchyTree();
        final Node node = new Node("" + dataItem.getKey(), 0);
        this.hierarchyTree.add(node);
        this.position = new Point(0, 0);
        RadixTrieIterExternalNode.newestNode = this;
    }
    
    public static void clearNewestNode() {
        RadixTrieIterExternalNode.newestNode = null;
    }
    
    public static void clearCompareNode() {
        RadixTrieIterExternalNode.compareNode = null;
    }
    
    public static void setCompareNode(final RadixTrieIterExternalNode newCompareNode) {
        RadixTrieIterExternalNode.compareNode = newCompareNode;
    }
    
    public void setLevel(final int level) {
        this.level = level;
    }
    
    public int getLevel() {
        return this.level;
    }
    
    public RadixTrieIterInternalNode getParent() {
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
        RadixTrieIterExternalNode.upperLeft = new Point();
        RadixTrieIterExternalNode.upperLeft.x = position.x - 9;
        RadixTrieIterExternalNode.upperLeft.y = position.y - 9;
        RadixTrieIterExternalNode.lowerRight = new Point();
        RadixTrieIterExternalNode.lowerRight.x = position.x + 9;
        RadixTrieIterExternalNode.lowerRight.y = position.y + 9;
        this.position = new Point(position);
    }
    
    protected synchronized Point layout(final int x, final int y) {
        this.position = new Point(x + 9, y);
        if (this.position.x - 9 < RadixTrieIterExternalNode.upperLeft.x) {
            RadixTrieIterExternalNode.upperLeft.x = this.position.x - 9;
        }
        if (this.position.y - 9 < RadixTrieIterExternalNode.upperLeft.y) {
            RadixTrieIterExternalNode.upperLeft.y = this.position.y - 9;
        }
        if (this.position.x + 9 > RadixTrieIterExternalNode.lowerRight.x) {
            RadixTrieIterExternalNode.lowerRight.x = this.position.x + 9;
        }
        if (this.position.y + 9 > RadixTrieIterExternalNode.lowerRight.y) {
            RadixTrieIterExternalNode.lowerRight.y = this.position.y + 9;
        }
        return new Point(x + 18, y - 20);
    }
    
    public Rectangle getRectangle() {
        this.setPosition(this.position);
        return new Rectangle(RadixTrieIterExternalNode.upperLeft.x, RadixTrieIterExternalNode.upperLeft.y, RadixTrieIterExternalNode.lowerRight.x - RadixTrieIterExternalNode.upperLeft.x, RadixTrieIterExternalNode.lowerRight.y - RadixTrieIterExternalNode.upperLeft.y);
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
        if (RadixTrieIterExternalNode.newestNode == this) {
            this.currentBackgroundColor = RadixTrieIterColors.NEW_NODE_COLOR;
        }
        else if (RadixTrieIterExternalNode.compareNode == this) {
            this.currentBackgroundColor = RadixTrieIterColors.COMPARE_NODE_COLOR;
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
