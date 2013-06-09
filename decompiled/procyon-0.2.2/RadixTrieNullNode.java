import com.cim.AIA.*;
import java.awt.*;

public class RadixTrieNullNode extends RadixTrieNode
{
    static final int NODE_X_GAP = 15;
    
    public RadixTrieNullNode(final RadixTrieInternalNode parent) {
        super();
        this.parent = parent;
        this.setIsLinkedIn();
        this.position = new Point();
    }
    
    public RadixTrieInternalNode getParent() {
        return this.parent;
    }
    
    public int getRadius() {
        return 0;
    }
    
    public void setPosition(final Point point) {
        this.position = new Point(point);
    }
    
    public Point getPosition() {
        return this.position;
    }
    
    protected Point layout(final int x, final int y) {
        this.position = new Point(x + 7, y);
        return new Point(x + 15, y - 20);
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
