import com.cim.AIA.*;
import java.awt.*;

public class SplayTreeDataNode extends SplayTreeNode
{
    static final int NODE_Y_GAP = 7;
    static final int NODE_X_GAP = 0;
    static final int NODE_WIDTH = 15;
    static final int NODE_HEIGHT = 15;
    private Point position;
    private static SplayTreeNode newestNode;
    private static SplayTreeNode foundNode;
    private Color currentBackgroundColor;
    private static final Color BACKGROUND_COLOR;
    private SplayTreeDataItem dataItem;
    private SplayTreeDataNode parent;
    private SplayTreeNode left;
    private SplayTreeNode right;
    private Line leftLink;
    private Line rightLink;
    
    public SplayTreeDataNode(final SplayTreeDataItem dataItem) {
        super();
        this.currentBackgroundColor = SplayTreeDataNode.BACKGROUND_COLOR;
        this.dataItem = dataItem;
        SplayTreeDataNode.newestNode = this;
        this.position = new Point();
        this.left = new SplayTreeNullNode();
        this.left.setParent(this);
        this.leftLink = new Line(this.position, new Point());
        this.right = new SplayTreeNullNode();
        this.right.setParent(this);
        this.rightLink = new Line(this.position, new Point());
    }
    
    public SplayTreeNode getCopy() {
        final SplayTreeDataNode splayTreeDataNode;
        final SplayTreeDataNode copyNode = splayTreeDataNode = new SplayTreeDataNode(this.dataItem);
        splayTreeDataNode.position = new Point(this.position);
        copyNode.left = this.left.getCopy();
        copyNode.left.setParent(copyNode);
        copyNode.rightLink = new Line(copyNode.position, new Point());
        copyNode.right = this.right.getCopy();
        copyNode.right.setParent(copyNode);
        copyNode.rightLink = new Line(copyNode.position, new Point());
        return copyNode;
    }
    
    public void setIsFound() {
        SplayTreeDataNode.foundNode = this;
    }
    
    public static void clearNodeColors() {
        SplayTreeDataNode.foundNode = null;
        SplayTreeDataNode.newestNode = null;
    }
    
    public int getKey() {
        return this.dataItem.getKey();
    }
    
    public Line getLeftLink() {
        return this.leftLink;
    }
    
    public Line getRightLink() {
        return this.rightLink;
    }
    
    public SplayTreeNode getLeft() {
        return this.left;
    }
    
    public void setLeft(final SplayTreeNode left) {
        this.left = left;
        left.setParent(this);
    }
    
    public SplayTreeNode getRight() {
        return this.right;
    }
    
    public void setRight(final SplayTreeNode right) {
        this.right = right;
        right.setParent(this);
    }
    
    public void setParent(final SplayTreeDataNode parent) {
        this.parent = parent;
    }
    
    public SplayTreeDataNode getParent() {
        return this.parent;
    }
    
    public Point getPosition() {
        return this.position;
    }
    
    public void setUpperLeft(final Point position) {
        this.layout(position.x, position.y);
        SplayTreeDataNode.lowerRight = new Point(position);
        SplayTreeDataNode.upperLeft = new Point(position.x, position.y);
        SplayTreeDataNode.lowerRight.x = this.layout(position.x, position.y).x;
    }
    
    public void setNodePosition(final Point position) {
        this.position.x = position.x;
        this.position.y = position.y;
    }
    
    public void setPosition(final Point position) {
        this.layout(position.x, position.y);
        this.setUpperLeft(new Point(position.x - (this.position.x - position.x), position.y - (this.position.y - position.y)));
    }
    
    protected Point layout(final int x, final int y) {
        Point newPosition = new Point(x, y);
        if (this.left != null) {
            final Point layout;
            newPosition = (layout = this.left.layout(x, y + 7 + 15));
            layout.x = layout.x + 0;
        }
        newPosition.y = y;
        final Point point = newPosition;
        point.x = point.x + 15;
        this.position.x = newPosition.x - 7;
        this.position.y = newPosition.y + 7;
        if (this.right != null) {
            newPosition = this.right.layout(newPosition.x + 0, newPosition.y + 15 + 7);
        }
        else if (this.left == null && this.position.y + 7 > SplayTreeDataNode.lowerRight.y) {
            SplayTreeDataNode.lowerRight.y = this.position.y + 7;
        }
        this.leftLink.setStartPosition(this.position);
        this.leftLink.setEndPosition(this.left.getPosition());
        this.rightLink.setStartPosition(this.position);
        this.rightLink.setEndPosition(this.right.getPosition());
        return new Point(newPosition.x, this.position.y);
    }
    
    public void draw(final Graphics g) {
        if (this.left != null) {
            if (this.left.getIsOnPath()) {
                this.leftLink.setColor(Color.red);
                this.leftLink.showAsThick(true);
            }
            else if (this.left.getIsOnSwitchPath()) {
                this.leftLink.setColor(Color.blue);
                this.leftLink.showAsThick(true);
            }
            else {
                this.leftLink.setColor(Color.black);
                this.leftLink.showAsThick(false);
            }
            this.leftLink.draw(g);
            this.left.draw(g);
        }
        if (this.right != null) {
            if (this.right.getIsOnPath()) {
                this.rightLink.setColor(Color.red);
                this.rightLink.showAsThick(true);
            }
            else if (this.right.getIsOnSwitchPath()) {
                this.rightLink.setColor(Color.blue);
                this.rightLink.showAsThick(true);
            }
            else {
                this.rightLink.setColor(Color.black);
                this.rightLink.showAsThick(false);
            }
            this.rightLink.draw(g);
            this.right.draw(g);
        }
        this.drawNode(g);
    }
    
    public void drawNode(final Graphics g) {
        final String label = "" + this.dataItem.getKey();
        if (SplayTreeDataNode.newestNode == this) {
            this.currentBackgroundColor = SplayTreeColors.NEW_NODE_COLOR;
        }
        else if (SplayTreeDataNode.foundNode == this) {
            this.currentBackgroundColor = SplayTreeColors.FOUND_NODE_COLOR;
        }
        else {
            this.currentBackgroundColor = SplayTreeDataNode.BACKGROUND_COLOR;
        }
        g.setColor(this.currentBackgroundColor.brighter());
        g.fillOval(this.position.x - 7 - 1, this.position.y - 7 - 1, 15, 15);
        g.setColor(this.currentBackgroundColor.darker());
        g.fillOval(this.position.x - 7 + 1, this.position.y - 7 + 1, 15, 15);
        g.setColor(this.currentBackgroundColor);
        g.fillOval(this.position.x - 7 + 1, this.position.y - 7 + 1, 13, 13);
        g.setColor(Color.black);
        g.drawOval(this.position.x - 7 - 1, this.position.y - 7 - 1, 17, 17);
        g.drawString(label, this.position.x - g.getFontMetrics().stringWidth(label) / 2, this.position.y + g.getFontMetrics().getHeight() / 3);
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
        this.leftLink.setIntY1(y);
        this.rightLink.setIntY1(y);
        this.leftLink.setEndPosition(this.left.getPosition());
        this.rightLink.setEndPosition(this.right.getPosition());
        this.updateParentLink();
    }
    
    public void setX(final int x) {
        this.position.x = x;
        this.leftLink.setIntX1(x);
        this.rightLink.setIntX1(x);
        this.leftLink.setEndPosition(this.left.getPosition());
        this.rightLink.setEndPosition(this.right.getPosition());
        this.updateParentLink();
    }
    
    private void updateParentLink() {
        if (this.parent != null) {
            if (this.parent.left == this) {
                this.parent.leftLink.setEndPosition(this.getPosition());
            }
            else {
                this.parent.rightLink.setEndPosition(this.getPosition());
            }
        }
    }
    
    static {
        BACKGROUND_COLOR = SplayTreeColors.DEFAULT_NODE_COLOR;
    }
}
