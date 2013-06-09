import java.awt.*;

public class SplayTreeNullNode extends SplayTreeNode
{
    static final int NODE_Y_GAP = 0;
    static final int NODE_X_GAP = 0;
    static final int NODE_WIDTH = 0;
    static final int NODE_HEIGHT = 5;
    private Point position;
    private SplayTreeDataNode parent;
    
    public SplayTreeNullNode() {
        super();
        this.position = new Point();
    }
    
    public SplayTreeNode getCopy() {
        final SplayTreeNullNode splayTreeNullNode;
        final SplayTreeNullNode copyNode = splayTreeNullNode = new SplayTreeNullNode();
        splayTreeNullNode.position = new Point(this.position);
        return copyNode;
    }
    
    public void setParent(final SplayTreeDataNode parent) {
        this.parent = parent;
    }
    
    public SplayTreeDataNode getParent() {
        return this.parent;
    }
    
    public void setNodePosition(final Point position) {
        this.position.x = position.x;
        this.position.y = position.y;
    }
    
    public Point getPosition() {
        return this.position;
    }
    
    public void setUpperLeft(final Point position) {
        this.setPosition(position);
    }
    
    public void setPosition(final Point position) {
        this.position = position;
        SplayTreeNullNode.lowerRight = new Point(position);
        SplayTreeNullNode.upperLeft = new Point(position);
    }
    
    protected Point layout(final int x, final int y) {
        this.position.x = x + 0;
        this.position.y = y + 5;
        if (this.position.y > SplayTreeNullNode.lowerRight.y) {
            SplayTreeNullNode.lowerRight.y = this.position.y;
        }
        return new Point(x + 0, y);
    }
    
    public void draw(final Graphics g) {
        this.drawNode(g);
    }
    
    public void drawNode(final Graphics g) {
    }
    
    public void shiftEntire(final int deltaX, final int deltaY) {
        this.setPosition(new Point(this.position.x + deltaX, this.position.y + deltaY));
    }
    
    public int getY() {
        return this.position.y;
    }
    
    public int getX() {
        return this.position.x;
    }
    
    public void setY(final int y) {
        this.position.y = y;
        this.updateParentLink();
    }
    
    public void setX(final int x) {
        this.position.x = x;
        this.updateParentLink();
    }
    
    private void updateParentLink() {
        if (this.parent != null) {
            if (this.parent.getLeft() == this) {
                this.parent.getLeftLink().setEndPosition(this.getPosition());
            }
            else {
                this.parent.getRightLink().setEndPosition(this.getPosition());
            }
        }
    }
}
