package com.cim.AIA;

import java.util.*;
import java.awt.*;

public class HierarchyTree extends Tree implements Drawable, Moveable, Selectable
{
    private static final long serialVersionUID = -7957106999101569662L;
    protected static final Color BORDER_COLOR;
    protected static final Color PARENT_LINE_COLOR;
    protected static final int BASE_HEIGHT = 20;
    protected static FontMetrics metrics;
    protected Color borderColor;
    protected boolean drawBorder;
    protected Color parentLineColor;
    protected Color mainHighlightColor;
    protected Vector<Node> elements;
    protected int height;
    protected int border;
    protected int parentDistance;
    protected Point offset;
    protected Polygon contour;
    protected Point pos;
    protected Line line;
    protected boolean drawParentLine;
    protected boolean recalculateLine;
    protected Point prevPos;
    protected int sequenceNumber;
    protected WTFactory factory;
    
    protected static boolean compare(final HierarchyTree one, final HierarchyTree two) {
        if (one.getIdentifier() != two.getIdentifier()) {
            return false;
        }
        for (int i = 0; i < one.elements.size(); ++i) {
            if (!((Node)one.elements.elementAt(i)).equals((Selectable)two.elements.elementAt(i))) {
                return false;
            }
        }
        for (int i = 0; i < one.children.size(); ++i) {
            if (!((HierarchyTree)one.children.elementAt(i)).equals((HierarchyTree)two.children.elementAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    public HierarchyTree() {
        super(null);
        this.borderColor = HierarchyTree.BORDER_COLOR;
        this.drawBorder = true;
        this.parentLineColor = HierarchyTree.PARENT_LINE_COLOR;
        this.mainHighlightColor = null;
        this.elements = new Vector();
        this.height = 20;
        this.border = 10;
        this.parentDistance = 5;
        this.offset = new Point(0, 0);
        this.contour = new Polygon();
        this.pos = new Point();
        this.line = new Line(0, 0, 0, 0);
        this.drawParentLine = true;
        this.recalculateLine = true;
        this.prevPos = new Point();
        this.sequenceNumber = 1;
        this.factory = new WTFactory();
        this.initialiseLine();
    }
    
    public HierarchyTree(final Node node) {
        super(null);
        this.borderColor = HierarchyTree.BORDER_COLOR;
        this.drawBorder = true;
        this.parentLineColor = HierarchyTree.PARENT_LINE_COLOR;
        this.mainHighlightColor = null;
        this.elements = new Vector();
        this.height = 20;
        this.border = 10;
        this.parentDistance = 5;
        this.offset = new Point(0, 0);
        this.contour = new Polygon();
        this.pos = new Point();
        this.line = new Line(0, 0, 0, 0);
        this.drawParentLine = true;
        this.recalculateLine = true;
        this.prevPos = new Point();
        this.sequenceNumber = 1;
        this.factory = new WTFactory();
        this.elements.addElement(node);
        this.initialiseLine();
    }
    
    public HierarchyTree(final Tree parent) {
        super(parent);
        this.borderColor = HierarchyTree.BORDER_COLOR;
        this.drawBorder = true;
        this.parentLineColor = HierarchyTree.PARENT_LINE_COLOR;
        this.mainHighlightColor = null;
        this.elements = new Vector();
        this.height = 20;
        this.border = 10;
        this.parentDistance = 5;
        this.offset = new Point(0, 0);
        this.contour = new Polygon();
        this.pos = new Point();
        this.line = new Line(0, 0, 0, 0);
        this.drawParentLine = true;
        this.recalculateLine = true;
        this.prevPos = new Point();
        this.sequenceNumber = 1;
        this.factory = new WTFactory();
        this.initialiseLine();
    }
    
    public HierarchyTree(final Tree parent, final Node obj) {
        super(parent);
        this.borderColor = HierarchyTree.BORDER_COLOR;
        this.drawBorder = true;
        this.parentLineColor = HierarchyTree.PARENT_LINE_COLOR;
        this.mainHighlightColor = null;
        this.elements = new Vector();
        this.height = 20;
        this.border = 10;
        this.parentDistance = 5;
        this.offset = new Point(0, 0);
        this.contour = new Polygon();
        this.pos = new Point();
        this.line = new Line(0, 0, 0, 0);
        this.drawParentLine = true;
        this.recalculateLine = true;
        this.prevPos = new Point();
        this.sequenceNumber = 1;
        this.factory = new WTFactory();
        this.elements.addElement(obj);
        this.initialiseLine();
    }
    
    public void add(final Node node) {
        this.elements.addElement(node);
    }
    
    public void add(final Node node, final int position) {
        if (position >= 0 && position <= this.elements.size()) {
            this.elements.insertElementAt(node, position);
        }
        else {
            this.elements.addElement(node);
        }
    }
    
    protected void calculateLine() {
        final Point parentPoint = this.getParentConnectPoint();
        if (parentPoint != null) {
            this.line.setIntX1(parentPoint.x);
            this.line.setIntY1(parentPoint.y + 1);
            this.line.setIntX2(this.pos.x + this.getWidth() / 2);
            this.line.setIntY2(this.pos.y);
        }
        this.line.setColor(this.parentLineColor);
    }
    
    public boolean contains(final int x, final int y) {
        return new Rectangle(this.pos.x, this.pos.y, this.getWidth(), this.height).contains(x, y);
    }
    
    public void displayNode() {
        for (int i = 0; i < this.elements.size(); ++i) {
            final Node node = (Node)this.elements.elementAt(i);
            System.out.print(node.getObject() + " ");
        }
    }
    
    public void draw(final Graphics g) {
        this.factory.paintTree(g, this);
    }
    
    public void draw(final Graphics g, final Point p) {
        this.plantTree(p.x, p.y);
        this.draw(g);
    }
    
    public void drawPartial(final Graphics g) {
        final Color oldColor = g.getColor();
        final Point start = new Point(0, 0);
        start.x = this.pos.x;
        start.y = this.pos.y;
        if (this.mainHighlightColor != null) {
            g.setColor(this.mainHighlightColor);
            g.fill3DRect(this.pos.x - 2, this.pos.y - 2, this.getWidth() + 6, this.height + 6, false);
        }
        final boolean drawNode = this.nodesAreVisible();
        if (this.elements.size() > 0 && drawNode && this.drawBorder) {
            g.setColor(this.borderColor);
            g.draw3DRect(this.pos.x, this.pos.y, this.getWidth() + 1, this.height + 1, false);
        }
        if (drawNode) {
            for (int i = 0; i < this.elements.size(); ++i) {
                final Node node = (Node)this.elements.elementAt(i);
                node.draw(g, start);
                final Point point = start;
                point.x = point.x + node.getWidth();
            }
        }
        g.setColor(oldColor);
    }
    
    public Node elementAt(final int position) {
        return (Node)this.elements.elementAt(position);
    }
    
    public String elementsString() {
        String temp = "";
        for (int i = 0; i < this.elements.size(); ++i) {
            final Node node = (Node)this.elements.elementAt(i);
            temp = temp + node.getObject().toString() + " ";
        }
        return temp;
    }
    
    public boolean equals(final Selectable selectable) {
        return selectable instanceof HierarchyTree && compare(this, (HierarchyTree)selectable);
    }
    
    public boolean getDrawParentLine() {
        return this.drawParentLine;
    }
    
    public Node getFirstElement() {
        for (int i = 0; i < this.elements.size(); ++i) {
            final Node node = (Node)this.elements.elementAt(i);
            if (i == 0) {
                return node;
            }
        }
        return null;
    }
    
    public int getFirstElementPosition() {
        for (int i = 0; i < this.elements.size(); ++i) {
            if (i == 0) {
                return i;
            }
        }
        return 0;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public int getIdentifier() {
        return this.sequenceNumber;
    }
    
    public Selectable getItemAt(final Point point) {
        Selectable temp;
        for (int i = 0; i < this.elements.size(); ++i) {
            final Node node = (Node)this.elements.elementAt(i);
            temp = node.getItemAt(point);
            if (temp != null) {
                return temp;
            }
        }
        for (int i = 0; i < this.children.size(); ++i) {
            final HierarchyTree child = (HierarchyTree)this.children.elementAt(i);
            temp = child.getItemAt(point);
            if (temp != null) {
                return temp;
            }
        }
        return null;
    }
    
    public Node getLastElement() {
        for (int i = 0; i < this.elements.size(); ++i) {
            final Node node = (Node)this.elements.elementAt(i);
            if (i == this.elements.size() - 1) {
                return node;
            }
        }
        return null;
    }
    
    public int getLastElementPosition() {
        for (int i = 0; i < this.elements.size(); ++i) {
            if (i == this.elements.size() - 1) {
                return i;
            }
        }
        return this.elements.size();
    }
    
    public HierarchyTree getLeftChild() {
        for (int i = 0; i < this.children.size(); ++i) {
            final HierarchyTree child = (HierarchyTree)this.children.elementAt(i);
            if (i == 0) {
                return child;
            }
        }
        return null;
    }
    
    public HierarchyTree getLeftSibling() {
        final HierarchyTree parent = (HierarchyTree)this.getParent();
        if (parent != null) {
            int thisLocation = -5;
            for (int i = parent.children.size(); i >= 0; --i) {
                final HierarchyTree child = (HierarchyTree)parent.children.elementAt(i - 1);
                if (child == this) {
                    thisLocation = i;
                }
                if (i == thisLocation - 1) {
                    return child;
                }
            }
        }
        return null;
    }
    
    public Line getLine() {
        if (this.line.equals(new Line(0, 0, 0, 0)) || this.recalculateLine) {
            final Point parentPoint = this.getParentConnectPoint();
            if (parentPoint != null) {
                this.line.setIntX1(parentPoint.x);
                this.line.setIntY1(parentPoint.y + 1);
                this.line.setIntX2(this.pos.x + this.getWidth() / 2);
                this.line.setIntY2(this.pos.y);
            }
            this.line.setColor(this.parentLineColor);
        }
        return this.line;
    }
    
    public Node getMiddleElement() {
        for (int i = 0; i < this.elements.size(); ++i) {
            final Node node = (Node)this.elements.elementAt(i);
            if (i != 0 && i != this.elements.size() - 1) {
                return node;
            }
        }
        return null;
    }
    
    public int getMiddleElementPosition() {
        for (int i = 0; i < this.elements.size(); ++i) {
            if (i != 0 && i != this.elements.size() - 1) {
                return i;
            }
        }
        return 1;
    }
    
    public HierarchyTree getMiddleLeftChild() {
        for (int i = 0; i < this.children.size(); ++i) {
            final HierarchyTree child = (HierarchyTree)this.children.elementAt(i);
            if (i != 0 && i != this.children.size() - 1) {
                return child;
            }
        }
        return null;
    }
    
    public HierarchyTree getMiddleRightChild() {
        for (int i = 0; i < this.children.size(); ++i) {
            final HierarchyTree child = (HierarchyTree)this.children.elementAt(i);
            if (i != 0 && i != this.children.size() - 1 && i != 1) {
                return child;
            }
        }
        return null;
    }
    
    public Node getNodeAt(int position) {
        --position;
        if (position >= 0 && position < this.elements.size()) {
            final Node tempNode = (Node)this.elements.elementAt(position);
            return tempNode;
        }
        return null;
    }
    
    public Vector<Node> getNodes() {
        final Vector<Node> temp = new Vector();
        for (int i = 0; i < this.elements.size(); ++i) {
            temp.addElement(this.elements.elementAt(i));
        }
        return temp;
    }
    
    public Point getParentConnectPoint() {
        if (this.parentTree == null || this.elements.size() == 0) {
            return null;
        }
        final HierarchyTree parent = (HierarchyTree)this.parentTree;
        final Point connectionPoint = new Point(parent.pos.x + parent.getWidth() / 2, parent.pos.y + parent.getHeight());
        if (parent.children.size() == 1) {
            return connectionPoint;
        }
        for (int i = 0; i < parent.children.size(); ++i) {
            if ((HierarchyTree)parent.children.elementAt(i) == this) {
                connectionPoint.x = parent.pos.x + parent.getWidth() * i / (parent.children.size() - 1);
                break;
            }
        }
        return connectionPoint;
    }
    
    public Color getParentLineColor() {
        return this.parentLineColor;
    }
    
    public Point getPosition() {
        return this.pos;
    }
    
    public int getPositionOfNode(final Node node) {
        for (int i = 0; i < this.elements.size(); ++i) {
            if (node == this.elements.elementAt(i)) {
                return i;
            }
        }
        return 0;
    }
    
    public Rectangle getRectangle() {
        final Point left = new Point(this.pos.x, this.pos.y);
        final Point right = new Point(this.pos.x + this.getWidth(), this.pos.y + this.height);
        final Rectangle rectangle = this.getTotalDimensions(this.pos.x, this.pos.y, this.pos.x + this.getWidth(), this.pos.y + this.height);
        rectangle.width = rectangle.width - rectangle.x;
        rectangle.height = rectangle.height - rectangle.y;
        return rectangle;
    }
    
    public HierarchyTree getRightChild() {
        for (int i = 0; i < this.children.size(); ++i) {
            final HierarchyTree child = (HierarchyTree)this.children.elementAt(i);
            if (i == this.children.size() - 1) {
                return child;
            }
        }
        return null;
    }
    
    public HierarchyTree getRightSibling() {
        final HierarchyTree parent = (HierarchyTree)this.getParent();
        if (parent != null) {
            int thisLocation = -5;
            for (int i = 0; i < parent.children.size(); ++i) {
                final HierarchyTree child = (HierarchyTree)parent.children.elementAt(i);
                if (child == this) {
                    thisLocation = i;
                }
                if (i == thisLocation + 1) {
                    return child;
                }
            }
        }
        return null;
    }
    
    public HierarchyTree getRoot() {
        HierarchyTree temp = this;
        while (temp.getParent() != null) {
            temp = (HierarchyTree)temp.getParent();
        }
        return temp;
    }
    
    protected Rectangle getTotalDimensions(final int x1, final int y1, final int x2, final int y2) {
        int tempX1 = x1;
        int tempY1 = y1;
        int tempX2 = x2;
        int tempY2 = y2;
        if (this.pos.x < tempX1) {
            tempX1 = this.pos.x;
        }
        if (this.pos.y < tempY1) {
            tempY1 = this.pos.y;
        }
        if (this.pos.x + this.getWidth() > tempX2) {
            tempX2 = this.pos.x + this.getWidth();
        }
        if (this.pos.y + this.height > tempY2) {
            tempY2 = this.pos.y + this.height;
        }
        for (int i = 0; i < this.children.size(); ++i) {
            final HierarchyTree child = (HierarchyTree)this.children.elementAt(i);
            final Rectangle rectangle = child.getTotalDimensions(tempX1, tempY1, tempX2, tempY2);
            tempX1 = rectangle.x;
            tempY1 = rectangle.y;
            tempX2 = rectangle.width;
            tempY2 = rectangle.height;
        }
        return new Rectangle(tempX1, tempY1, tempX2, tempY2);
    }
    
    public int getTotalHeight() {
        if (this.children.size() > 0) {
            return this.height + this.parentDistance + this.border + ((HierarchyTree)this.children.elementAt(0)).getTotalHeight();
        }
        return this.height;
    }
    
    public int getWidth() {
        int width = 0;
        for (int i = 0; i < this.elements.size(); ++i) {
            final Node tempNode = this.elementAt(i);
            width = width + tempNode.getWidth();
        }
        return width;
    }
    
    public synchronized int getWidthOfNode(final int position) {
        if (position < 0 || position >= this.elements.size()) {
            return 0;
        }
        return ((Node)this.elements.elementAt(position)).getWidth();
    }
    
    public int getX() {
        return this.pos.x;
    }
    
    public synchronized int getX(final int position) {
        int xPosition = this.pos.x;
        for (int i = 0; i < this.elements.size(); ++i) {
            final Node tempNode = (Node)this.elements.elementAt(i);
            if (i == position) {
                return xPosition;
            }
            xPosition = xPosition + tempNode.getWidth();
        }
        return 0;
    }
    
    public synchronized int getX(final Node node) {
        int xPosition = this.pos.x;
        for (int i = 0; i < this.elements.size(); ++i) {
            final Node tempNode = (Node)this.elements.elementAt(i);
            if (tempNode == node) {
                return xPosition;
            }
            xPosition = xPosition + tempNode.getWidth();
        }
        return 0;
    }
    
    public int getXPos() {
        return this.pos.x;
    }
    
    public int getY() {
        return this.pos.y;
    }
    
    public int getY(final Node node) {
        final int yPosition = this.pos.y;
        for (int i = 0; i < this.elements.size(); ++i) {
            if ((Node)this.elements.elementAt(i) == node) {
                return yPosition;
            }
        }
        return 0;
    }
    
    public int getYPos() {
        return this.pos.y;
    }
    
    public void highlight() {
        for (int i = 0; i < this.elements.size(); ++i) {
            final Node node = (Node)this.elements.elementAt(i);
            node.highlight();
        }
        for (int i = 0; i < this.children.size(); ++i) {
            final HierarchyTree child = (HierarchyTree)this.children.elementAt(i);
            child.highlight();
        }
    }
    
    protected void initialiseLine() {
        this.line.showArrowHead(true);
        this.line.setDistanceFromStartForArrowHead(-3);
        this.line.setColor(this.parentLineColor);
    }
    
    public boolean nodesAreVisible() {
        boolean drawNode = false;
        for (int i = 0; i < this.elements.size(); ++i) {
            if (((Node)this.elements.elementAt(i)).getIsVisible()) {
                drawNode = true;
            }
        }
        return drawNode;
    }
    
    public int numberOfElements() {
        return this.elements.size();
    }
    
    public void plantTree(final int midX, final int yBuffer) {
        this.factory.layout(this);
        this.factory.plantTree(this, midX, yBuffer);
    }
    
    public void recalculateLine(final boolean state) {
        this.recalculateLine = state;
    }
    
    public void remove(final Node node) {
        this.elements.removeElement(node);
    }
    
    public void setBorderColor(final Color color) {
        this.borderColor = color;
    }
    
    public void setDrawBorder(final boolean drawBorder) {
        this.drawBorder = drawBorder;
    }
    
    public void setDrawParentLine(final boolean state) {
        this.drawParentLine = state;
    }
    
    public void setHighlight(final Color color) {
        this.mainHighlightColor = color;
    }
    
    public void setParentLineColor(final Color color) {
        this.parentLineColor = color;
    }
    
    public void setPosition(final Point position) {
        this.pos.x = position.x;
        this.pos.y = position.y;
    }
    
    public int setX() {
        return this.pos.x;
    }
    
    public void setX(final int xCoord) {
        this.pos.x = xCoord;
    }
    
    public void setY(final int yCoord) {
        this.pos.y = yCoord;
    }
    
    public void setYEntire(final int yCoord) {
        this.pos.y = yCoord;
        for (int i = 0; i < this.children.size(); ++i) {
            final HierarchyTree child = (HierarchyTree)this.children.elementAt(i);
            child.setY(yCoord + this.height + this.border + this.parentDistance);
        }
    }
    
    public void shiftEntire(final int xDelta, final int yDelta) {
        this.shiftX(xDelta);
        this.shiftY(yDelta);
    }
    
    public void shiftX(final int delta) {
        this.pos.x = this.pos.x + delta;
        for (int i = 0; i < this.children.size(); ++i) {
            final HierarchyTree child = (HierarchyTree)this.children.elementAt(i);
            child.shiftX(delta);
        }
    }
    
    public void shiftY(final int delta) {
        this.pos.y = this.pos.y + delta;
        for (int i = 0; i < this.children.size(); ++i) {
            final HierarchyTree child = (HierarchyTree)this.children.elementAt(i);
            child.shiftY(delta);
        }
    }
    
    public void unHighlight() {
        for (int i = 0; i < this.elements.size(); ++i) {
            final Node node = (Node)this.elements.elementAt(i);
            node.unHighlight();
        }
        for (int i = 0; i < this.children.size(); ++i) {
            final HierarchyTree child = (HierarchyTree)this.children.elementAt(i);
            child.unHighlight();
        }
    }
    
    static {
        BORDER_COLOR = Color.black;
        PARENT_LINE_COLOR = Color.black;
        HierarchyTree.metrics = null;
    }
}
