import java.awt.*;
import com.cim.AIA.*;

public abstract class SplayTreeNode implements Drawable, Moveable
{
    protected static Point upperLeft;
    protected static Point lowerRight;
    private boolean isOnPath;
    private boolean isOnSwitchPath;
    
    public SplayTreeNode() {
        super();
        this.isOnPath = false;
        this.isOnSwitchPath = false;
    }
    
    public abstract SplayTreeNode getCopy();
    
    public abstract void setUpperLeft(final Point p0);
    
    public abstract void setNodePosition(final Point p0);
    
    public abstract void setPosition(final Point p0);
    
    public abstract Point getPosition();
    
    public abstract void setParent(final SplayTreeDataNode p0);
    
    public abstract SplayTreeDataNode getParent();
    
    public final Rectangle getRectangle() {
        if (SplayTreeNode.upperLeft != null && SplayTreeNode.lowerRight != null) {
            return new Rectangle(SplayTreeNode.upperLeft.x, SplayTreeNode.upperLeft.y, SplayTreeNode.lowerRight.x - SplayTreeNode.upperLeft.x, SplayTreeNode.lowerRight.y - SplayTreeNode.upperLeft.y);
        }
        return null;
    }
    
    public final boolean getIsOnSwitchPath() {
        return this.isOnSwitchPath;
    }
    
    public final void setIsOnSwitchPath(final boolean isOnSwitchPath) {
        this.isOnSwitchPath = isOnSwitchPath;
    }
    
    public final boolean getIsOnPath() {
        return this.isOnPath;
    }
    
    public final void setIsOnPath(final boolean isOnPath) {
        this.isOnPath = isOnPath;
    }
    
    protected abstract Point layout(final int p0, final int p1);
    
    public abstract void draw(final Graphics p0);
    
    public final void draw(final Graphics g, final Point point) {
        this.setPosition(point);
        this.draw(g);
    }
    
    public abstract void drawNode(final Graphics p0);
}
