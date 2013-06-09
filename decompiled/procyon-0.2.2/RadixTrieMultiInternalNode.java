import java.awt.*;
import com.cim.AIA.*;
import java.util.*;

public class RadixTrieMultiInternalNode extends RadixTrieMultiNode
{
    private Vector<E> radixTrieNodes;
    protected static final int NODE_X_GAP = 8;
    private static final int radius = 3;
    private static final int nullWidth = 6;
    private HierarchyTree hierarchyTree;
    private Color backgroundColor;
    private Color DEFAULT_COLOR;
    
    RadixTrieMultiInternalNode() {
        super();
        this.radixTrieNodes = new Vector();
        this.backgroundColor = Color.orange;
        this.DEFAULT_COLOR = Color.black;
        this.position = new Point(0, 0);
        this.hierarchyTree = new HierarchyTree();
        final Node node = new Node("", 0);
        node.setWidth(RadixTrieMulti.NO_OF_NODES * 3);
        node.setHeight(6);
        this.hierarchyTree.add(node);
        for (int i = 0; i < RadixTrieMulti.NO_OF_NODES; ++i) {
            this.hierarchyTree.addChild(new HierarchyTree());
        }
        this.hierarchyTree.setDrawBorder(false);
        for (int i = 0; i < RadixTrieMulti.NO_OF_NODES; ++i) {
            this.radixTrieNodes.addElement(new RadixTrieMultiNullNode(this));
        }
    }
    
    public RadixTrieMultiInternalNode getParent() {
        return this.parent;
    }
    
    public int getRadius() {
        return 3;
    }
    
    public Point getPosition() {
        return new Point(this.position);
    }
    
    public void setPosition(Point position) {
        Point currentPosition = new Point(position);
        RadixTrieMultiInternalNode.upperLeft = new Point();
        RadixTrieMultiInternalNode.upperLeft.y = position.y - 3;
        RadixTrieMultiInternalNode.upperLeft.x = position.x - 3;
        final Point saveUpperLeft = new Point(RadixTrieMultiInternalNode.upperLeft);
        RadixTrieMultiInternalNode.lowerRight = new Point();
        RadixTrieMultiInternalNode.lowerRight.y = position.y + 3;
        RadixTrieMultiInternalNode.lowerRight.x = position.x + 3;
        final Point saveLowerRight = new Point(RadixTrieMultiInternalNode.lowerRight);
        this.position = new Point(position);
        RadixTrieMultiNode n;
        for (int i = 0; i < RadixTrieMulti.NO_OF_NODES / 2; ++i) {
            n = (RadixTrieMultiNode)this.radixTrieNodes.elementAt(i);
            if (n != null) {
                currentPosition = n.layout(currentPosition.x, currentPosition.y + 40);
            }
        }
        RadixTrieMultiInternalNode.upperLeft = new Point(saveUpperLeft);
        RadixTrieMultiInternalNode.lowerRight = new Point(saveLowerRight);
        position.x = position.x - (currentPosition.x - position.x);
        RadixTrieMultiInternalNode.lowerRight.x = position.x + 3;
        RadixTrieMultiInternalNode.upperLeft.x = position.x - 3;
        for (int i = 0; i < RadixTrieMulti.NO_OF_NODES; ++i) {
            n = (RadixTrieMultiNode)this.radixTrieNodes.elementAt(i);
            if (n != null) {
                position = n.layout(position.x, position.y + 40);
            }
        }
    }
    
    protected synchronized Point layout(final int x, final int y) {
        Point position = new Point(x, y);
        Point lastPosition = new Point(x, y);
        if (position.x - 3 < RadixTrieMultiInternalNode.upperLeft.x) {
            RadixTrieMultiInternalNode.upperLeft.x = position.x - 3;
        }
        if (position.y - 3 < RadixTrieMultiInternalNode.upperLeft.y) {
            RadixTrieMultiInternalNode.upperLeft.y = position.y - 3;
        }
        if (position.x + 3 > RadixTrieMultiInternalNode.lowerRight.x) {
            RadixTrieMultiInternalNode.lowerRight.x = position.x + 3;
        }
        if (position.y + 3 > RadixTrieMultiInternalNode.lowerRight.y) {
            RadixTrieMultiInternalNode.lowerRight.y = position.y + 3;
        }
        for (int i = 0; i < RadixTrieMulti.NO_OF_NODES; ++i) {
            final RadixTrieMultiNode n = (RadixTrieMultiNode)this.radixTrieNodes.elementAt(i);
            lastPosition = position;
            position = n.layout(position.x, position.y + 40);
        }
        this.position.x = (RadixTrieMultiInternalNode.lowerRight.x - x) / 2 + x;
        this.position.y = y;
        return new Point(RadixTrieMultiInternalNode.lowerRight.x + 3 + 8, position.y - 40);
    }
    
    public Rectangle getRectangle() {
        if (this.position == null) {
            this.position = new Point();
        }
        this.setPosition(this.position);
        return new Rectangle(RadixTrieMultiInternalNode.upperLeft.x, RadixTrieMultiInternalNode.upperLeft.y, RadixTrieMultiInternalNode.lowerRight.x - RadixTrieMultiInternalNode.upperLeft.x, RadixTrieMultiInternalNode.lowerRight.y - RadixTrieMultiInternalNode.upperLeft.y);
    }
    
    public void draw(final Graphics g) {
        g.setColor(this.DEFAULT_COLOR);
        final Point currentPosition = new Point(this.position);
        currentPosition.x = currentPosition.x - 8;
        final int increment = 16 / (RadixTrieMulti.NO_OF_NODES - 1);
        RadixTrieMultiNode n;
        for (int i = 0; i < RadixTrieMulti.NO_OF_NODES; ++i) {
            n = (RadixTrieMultiNode)this.radixTrieNodes.elementAt(i);
            if (n != null) {
                if (n.getIsLinkedIn()) {
                    Line nLink;
                    if (n.getIsOnPath()) {
                        nLink = new Line(this.position.x, this.position.y, n.position.x, n.position.y, Color.red);
                        nLink.showAsThick(true);
                    }
                    else {
                        nLink = new Line(this.position.x, this.position.y, n.position.x, n.position.y, Color.black);
                    }
                    nLink.draw(g);
                }
                n.draw(g);
            }
            else {
                g.drawLine(this.position.x, this.position.y, currentPosition.x + increment * i, currentPosition.y + 13);
            }
        }
        if (this.parent == null) {
            this.drawNode(g);
        }
        for (int i = 0; i < RadixTrieMulti.NO_OF_NODES; ++i) {
            n = (RadixTrieMultiNode)this.radixTrieNodes.elementAt(i);
            if (n != null) {
                n.drawNode(g);
            }
        }
    }
    
    public void drawNode(final Graphics g) {
        g.setColor(this.backgroundColor.brighter());
        g.fillOval(this.position.x - 3 - 1, this.position.y - 3 - 1, 6, 6);
        g.setColor(this.backgroundColor.darker());
        g.fillOval(this.position.x - 3 + 1, this.position.y - 3 + 1, 6, 6);
        g.setColor(this.backgroundColor);
        g.fillOval(this.position.x - 3, this.position.y - 3, 6, 6);
        g.setColor(this.DEFAULT_COLOR);
    }
    
    public void setChild(final RadixTrieMultiNode node, final int child) {
        node.parent = this;
        this.radixTrieNodes.removeElementAt(child);
        this.radixTrieNodes.insertElementAt(node, child);
        this.hierarchyTree.removeChild(this.hierarchyTree.getChild(child));
        this.hierarchyTree.insertChildAt(node.getHierarchyTree(), child);
    }
    
    public void setBackgroundColor(final Color color) {
        this.backgroundColor = color;
    }
    
    public RadixTrieMultiNode getChild(final int child) {
        return (RadixTrieMultiNode)this.radixTrieNodes.elementAt(child);
    }
    
    public HierarchyTree getHierarchyTree() {
        return this.hierarchyTree;
    }
}
