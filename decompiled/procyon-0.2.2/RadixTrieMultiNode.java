import java.awt.*;
import com.cim.AIA.*;

public abstract class RadixTrieMultiNode implements Drawable
{
    static final int NODE_Y_GAP = 40;
    protected static Point upperLeft;
    protected static Point lowerRight;
    static final int NULL_WIDTH = 8;
    static final int NULL_HEIGHT = 13;
    private boolean isLinkedIn;
    private boolean isOnPath;
    protected Point position;
    protected RadixTrieMultiInternalNode parent;
    
    public RadixTrieMultiNode() {
        super();
        this.isLinkedIn = false;
        this.isOnPath = false;
    }
    
    public abstract RadixTrieMultiInternalNode getParent();
    
    public final void setIsLinkedIn() {
        this.isLinkedIn = true;
    }
    
    public final boolean getIsLinkedIn() {
        return this.isLinkedIn;
    }
    
    public void setIsOnPath(final boolean isOnPath) {
        this.isOnPath = isOnPath;
    }
    
    public boolean getIsOnPath() {
        return this.isOnPath;
    }
    
    public abstract int getRadius();
    
    public abstract void setPosition(final Point p0);
    
    public abstract Point getPosition();
    
    protected abstract Point layout(final int p0, final int p1);
    
    public abstract Rectangle getRectangle();
    
    public abstract HierarchyTree getHierarchyTree();
    
    public abstract void setBackgroundColor(final Color p0);
    
    public abstract void draw(final Graphics p0);
    
    public void draw(final Graphics g, final Point point) {
        this.setPosition(point);
        this.draw(g);
    }
    
    public abstract void drawNode(final Graphics p0);
}
