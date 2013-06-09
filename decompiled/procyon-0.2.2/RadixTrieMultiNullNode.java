import com.cim.AIA.*;
import java.awt.*;

public class RadixTrieMultiNullNode extends RadixTrieMultiNode
{
    static final int NODE_X_GAP = 18;
    private static final int radius = 9;
    
    public RadixTrieMultiNullNode(final RadixTrieMultiInternalNode parent) {
        super();
        this.parent = parent;
        this.setIsLinkedIn();
        this.position = new Point();
    }
    
    public RadixTrieMultiInternalNode getParent() {
        return this.parent;
    }
    
    public int getRadius() {
        return 0;
    }
    
    public void setPosition(final Point position) {
        RadixTrieMultiNullNode.upperLeft = new Point();
        RadixTrieMultiNullNode.upperLeft.x = position.x - 9;
        RadixTrieMultiNullNode.upperLeft.y = position.y - 9;
        RadixTrieMultiNullNode.lowerRight = new Point();
        RadixTrieMultiNullNode.lowerRight.x = position.x + 9;
        RadixTrieMultiNullNode.lowerRight.y = position.y + 9;
        this.position = new Point(position);
    }
    
    public Point getPosition() {
        return new Point(this.position);
    }
    
    protected Point layout(final int x, final int y) {
        this.position = new Point(x + 9, y);
        if (this.position.x - 9 < RadixTrieMultiNullNode.upperLeft.x) {
            RadixTrieMultiNullNode.upperLeft.x = this.position.x - 9;
        }
        if (this.position.y - 9 < RadixTrieMultiNullNode.upperLeft.y) {
            RadixTrieMultiNullNode.upperLeft.y = this.position.y - 9;
        }
        if (this.position.x + 9 > RadixTrieMultiNullNode.lowerRight.x) {
            RadixTrieMultiNullNode.lowerRight.x = this.position.x + 9;
        }
        if (this.position.y + 9 > RadixTrieMultiNullNode.lowerRight.y) {
            RadixTrieMultiNullNode.lowerRight.y = this.position.y + 9;
        }
        return new Point(x + 18, y - 40);
    }
    
    public synchronized Rectangle getRectangle() {
        return new Rectangle();
    }
    
    public HierarchyTree getHierarchyTree() {
        return null;
    }
    
    public void setBackgroundColor(final Color color) {
    }
    
    public void draw(final Graphics g) {
    }
    
    public void drawNode(final Graphics g) {
    }
}
