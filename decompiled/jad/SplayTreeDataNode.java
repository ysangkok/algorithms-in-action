// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SplayTreeDataNode.java

import com.cim.AIA.Line;
import java.awt.*;

public class SplayTreeDataNode extends SplayTreeNode
{

    public SplayTreeDataNode(SplayTreeDataItem dataItem)
    {
        currentBackgroundColor = BACKGROUND_COLOR;
        this.dataItem = dataItem;
        newestNode = this;
        position = new Point();
        left = new SplayTreeNullNode();
        left.setParent(this);
        leftLink = new Line(position, new Point());
        right = new SplayTreeNullNode();
        right.setParent(this);
        rightLink = new Line(position, new Point());
    }

    public SplayTreeNode getCopy()
    {
        SplayTreeDataNode copyNode = new SplayTreeDataNode(dataItem);
        copyNode.position = new Point(position);
        copyNode.left = left.getCopy();
        copyNode.left.setParent(copyNode);
        copyNode.rightLink = new Line(copyNode.position, new Point());
        copyNode.right = right.getCopy();
        copyNode.right.setParent(copyNode);
        copyNode.rightLink = new Line(copyNode.position, new Point());
        return copyNode;
    }

    public void setIsFound()
    {
        foundNode = this;
    }

    public static void clearNodeColors()
    {
        foundNode = null;
        newestNode = null;
    }

    public int getKey()
    {
        return dataItem.getKey();
    }

    public Line getLeftLink()
    {
        return leftLink;
    }

    public Line getRightLink()
    {
        return rightLink;
    }

    public SplayTreeNode getLeft()
    {
        return left;
    }

    public void setLeft(SplayTreeNode left)
    {
        this.left = left;
        left.setParent(this);
    }

    public SplayTreeNode getRight()
    {
        return right;
    }

    public void setRight(SplayTreeNode right)
    {
        this.right = right;
        right.setParent(this);
    }

    public void setParent(SplayTreeDataNode parent)
    {
        this.parent = parent;
    }

    public SplayTreeDataNode getParent()
    {
        return parent;
    }

    public Point getPosition()
    {
        return position;
    }

    public void setUpperLeft(Point position)
    {
        layout(position.x, position.y);
        lowerRight = new Point(position);
        upperLeft = new Point(position.x, position.y);
        lowerRight.x = layout(position.x, position.y).x;
    }

    public void setNodePosition(Point position)
    {
        this.position.x = position.x;
        this.position.y = position.y;
    }

    public void setPosition(Point position)
    {
        layout(position.x, position.y);
        setUpperLeft(new Point(position.x - (this.position.x - position.x), position.y - (this.position.y - position.y)));
    }

    protected Point layout(int x, int y)
    {
        Point newPosition = new Point(x, y);
        if(left != null)
        {
            newPosition = left.layout(x, y + 7 + 15);
            newPosition.x += 0;
        }
        newPosition.y = y;
        newPosition.x += 15;
        position.x = newPosition.x - 7;
        position.y = newPosition.y + 7;
        if(right != null)
            newPosition = right.layout(newPosition.x + 0, newPosition.y + 15 + 7);
        else
        if(left == null && position.y + 7 > lowerRight.y)
            lowerRight.y = position.y + 7;
        leftLink.setStartPosition(position);
        leftLink.setEndPosition(left.getPosition());
        rightLink.setStartPosition(position);
        rightLink.setEndPosition(right.getPosition());
        return new Point(newPosition.x, position.y);
    }

    public void draw(Graphics g)
    {
        if(left != null)
        {
            if(left.getIsOnPath())
            {
                leftLink.setColor(Color.red);
                leftLink.showAsThick(true);
            } else
            if(left.getIsOnSwitchPath())
            {
                leftLink.setColor(Color.blue);
                leftLink.showAsThick(true);
            } else
            {
                leftLink.setColor(Color.black);
                leftLink.showAsThick(false);
            }
            leftLink.draw(g);
            left.draw(g);
        }
        if(right != null)
        {
            if(right.getIsOnPath())
            {
                rightLink.setColor(Color.red);
                rightLink.showAsThick(true);
            } else
            if(right.getIsOnSwitchPath())
            {
                rightLink.setColor(Color.blue);
                rightLink.showAsThick(true);
            } else
            {
                rightLink.setColor(Color.black);
                rightLink.showAsThick(false);
            }
            rightLink.draw(g);
            right.draw(g);
        }
        drawNode(g);
    }

    public void drawNode(Graphics g)
    {
        String label = (new StringBuilder()).append("").append(dataItem.getKey()).toString();
        if(newestNode == this)
            currentBackgroundColor = SplayTreeColors.NEW_NODE_COLOR;
        else
        if(foundNode == this)
            currentBackgroundColor = SplayTreeColors.FOUND_NODE_COLOR;
        else
            currentBackgroundColor = BACKGROUND_COLOR;
        g.setColor(currentBackgroundColor.brighter());
        g.fillOval(position.x - 7 - 1, position.y - 7 - 1, 15, 15);
        g.setColor(currentBackgroundColor.darker());
        g.fillOval((position.x - 7) + 1, (position.y - 7) + 1, 15, 15);
        g.setColor(currentBackgroundColor);
        g.fillOval((position.x - 7) + 1, (position.y - 7) + 1, 13, 13);
        g.setColor(Color.black);
        g.drawOval(position.x - 7 - 1, position.y - 7 - 1, 17, 17);
        g.drawString(label, position.x - g.getFontMetrics().stringWidth(label) / 2, position.y + g.getFontMetrics().getHeight() / 3);
    }

    public void shiftEntire(int deltaX, int deltaY)
    {
        setPosition(new Point(position.x + deltaX, position.y + deltaY));
    }

    public int getY()
    {
        return position.y;
    }

    public int getX()
    {
        return position.x;
    }

    public void setY(int y)
    {
        position.y = y;
        leftLink.setIntY1(y);
        rightLink.setIntY1(y);
        leftLink.setEndPosition(left.getPosition());
        rightLink.setEndPosition(right.getPosition());
        updateParentLink();
    }

    public void setX(int x)
    {
        position.x = x;
        leftLink.setIntX1(x);
        rightLink.setIntX1(x);
        leftLink.setEndPosition(left.getPosition());
        rightLink.setEndPosition(right.getPosition());
        updateParentLink();
    }

    private void updateParentLink()
    {
        if(parent != null)
            if(parent.left == this)
                parent.leftLink.setEndPosition(getPosition());
            else
                parent.rightLink.setEndPosition(getPosition());
    }

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

    static 
    {
        BACKGROUND_COLOR = SplayTreeColors.DEFAULT_NODE_COLOR;
    }
}
