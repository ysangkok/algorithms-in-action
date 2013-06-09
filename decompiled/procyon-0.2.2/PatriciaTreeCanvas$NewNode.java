import localization.*;
import java.awt.*;
import com.cim.AIA.*;

public protected class NewNode implements Moveable
{
    Point currentPosition;
    Node body;
    Node dataNode;
    Boolean isSelfLinkLeft;
    Boolean isSelfLinkRight;
    Node destinationBody;
    Node destinationDataNode;
    PatriciaNode patriciaNode;
    boolean isDrawLabel;
    boolean hasBeenTweened;
    
    NewNode(final PatriciaNode patriciaNode, final Point position) {
        super();
        this.isDrawLabel = true;
        this.init(patriciaNode, position);
    }
    
    protected void drawNewNode(final Graphics g) {
        final Point bodyLocation = this.currentPosition;
        this.body.setPosition(bodyLocation);
        this.body.draw(g);
        final Point itemLocation = new Point();
        itemLocation.x = bodyLocation.x + this.body.getWidth();
        itemLocation.y = bodyLocation.y;
        this.dataNode.setPosition(itemLocation);
        this.dataNode.draw(g);
        if (this.isDrawLabel) {
            g.drawString(Messages.getString("PatriciaTreeCanvas.16"), itemLocation.x - g.getFontMetrics().stringWidth(Messages.getString("PatriciaTreeCanvas.17")) / 2, itemLocation.y);
        }
        final int width = this.body.getWidth();
        final int height = this.body.getHeight();
        int startAngle;
        int arcAngle;
        int x;
        int y;
        Line line;
        if (this.isSelfLinkLeft != null) {
            if (this.isSelfLinkLeft.booleanValue()) {
                startAngle = 90;
                arcAngle = 270;
                x = bodyLocation.x - width / 2;
                y = bodyLocation.y + height / 2;
                PatriciaTreeCanvas.access$000(PatriciaTreeCanvas.this, g, false, x, y, width, height, startAngle, arcAngle);
                PatriciaTreeCanvas.access$100(PatriciaTreeCanvas.this, g, new Point(x + width / 2, y), 15);
            }
            else {
                line = new Line(bodyLocation.x, bodyLocation.y + height, bodyLocation.x - width / 2, bodyLocation.y + height + width / 2);
                line.showArrowHead(true);
                line.setDistanceFromStartForArrowHead(-3);
                line.draw(g);
            }
        }
        if (this.isSelfLinkRight != null) {
            if (this.isSelfLinkRight.booleanValue()) {
                startAngle = 180;
                arcAngle = 270;
                x = itemLocation.x + this.dataNode.getWidth() - width / 2;
                y = itemLocation.y + height / 2;
                PatriciaTreeCanvas.access$100(PatriciaTreeCanvas.this, g, new Point(x + width / 2, y), 175);
                PatriciaTreeCanvas.access$000(PatriciaTreeCanvas.this, g, false, x, y, width, height, startAngle, arcAngle);
            }
            else {
                line = new Line(itemLocation.x + width, itemLocation.y + height, itemLocation.x + width + width / 2, itemLocation.y + height + width / 2);
                line.showArrowHead(true);
                line.setDistanceFromStartForArrowHead(-3);
                line.draw(g);
            }
        }
    }
    
    public HierarchyTree getHierarchyTree() {
        return this.patriciaNode.getHierarchyTree();
    }
    
    public Point getPosition() {
        return this.currentPosition;
    }
    
    public int getX() {
        return this.currentPosition.x;
    }
    
    public int getY() {
        return this.currentPosition.y;
    }
    
    private void init(final PatriciaNode patriciaNode, final Point position) {
        this.hasBeenTweened = false;
        this.patriciaNode = patriciaNode;
        this.destinationBody = patriciaNode.getBody();
        this.destinationDataNode = patriciaNode.getDataItem().getNode();
        this.currentPosition = new Point(position);
        this.body = new Node(patriciaNode.getBody().getObject(), 0);
        this.body.setBackgroundColor(PatriciaTreeColors.DIFFERENTIATING_COLOR);
        this.dataNode = new Node(patriciaNode.getDataItem().getNode().getObject(), 0);
        this.dataNode.setBackgroundColor(PatriciaTreeColors.INSERT_BIT_ACTIVE_COLOR);
        if (patriciaNode.getLeft() == patriciaNode) {
            this.isSelfLinkLeft = new Boolean(true);
        }
        else if (patriciaNode.getLeft() != null) {
            this.isSelfLinkLeft = new Boolean(false);
        }
        if (patriciaNode.getRight() == patriciaNode) {
            this.isSelfLinkRight = new Boolean(true);
        }
        else if (patriciaNode.getRight() != null) {
            this.isSelfLinkRight = new Boolean(false);
        }
    }
    
    public void setNode(final PatriciaNode patriciaNode, final Point position) {
        if (this.patriciaNode != patriciaNode) {
            this.init(patriciaNode, position);
        }
    }
    
    public void setX(final int newX) {
        this.isDrawLabel = false;
        this.currentPosition.x = newX;
    }
    
    public void setY(final int newY) {
        this.isDrawLabel = false;
        this.currentPosition.y = newY;
    }
    
    public void shiftEntire(final int x, final int y) {
        this.isDrawLabel = false;
        final Point currentPosition = this.currentPosition;
        currentPosition.x = currentPosition.x + x;
        final Point currentPosition2 = this.currentPosition;
        currentPosition2.y = currentPosition2.y + x;
    }
}
