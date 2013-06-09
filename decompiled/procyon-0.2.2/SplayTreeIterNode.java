import java.awt.*;
import com.cim.AIA.*;

public abstract class SplayTreeIterNode implements Drawable, Moveable
{
    protected static Point upperLeft;
    protected static Point lowerRight;
    protected static int NO_OF_ELEMENTS;
    protected int elementNumber;
    private boolean isOnPath;
    private boolean isOnSwitchPath;
    
    public SplayTreeIterNode() {
        super();
        this.isOnPath = false;
        this.isOnSwitchPath = false;
    }
    
    public abstract SplayTreeIterNode getCopy();
    
    public abstract void setUpperLeft(final Point p0);
    
    public abstract void setNodePosition(final Point p0);
    
    public abstract void setPosition(final Point p0);
    
    public abstract Point getPosition();
    
    public abstract void setParent(final SplayTreeIterDataNode p0);
    
    public abstract SplayTreeIterDataNode getParent();
    
    public final Rectangle getRectangle() {
        if (SplayTreeIterNode.upperLeft != null && SplayTreeIterNode.lowerRight != null) {
            return new Rectangle(SplayTreeIterNode.upperLeft.x, SplayTreeIterNode.upperLeft.y, SplayTreeIterNode.lowerRight.x - SplayTreeIterNode.upperLeft.x, SplayTreeIterNode.lowerRight.y - SplayTreeIterNode.upperLeft.y);
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
    
    public final int getIdentifier() {
        return this.elementNumber;
    }
    
    protected abstract Point layout(final int p0, final int p1);
    
    public abstract void draw(final Graphics p0);
    
    public final void draw(final Graphics g, final Point point) {
        this.setPosition(point);
        this.draw(g);
    }
    
    public abstract void drawNode(final Graphics p0);
}
