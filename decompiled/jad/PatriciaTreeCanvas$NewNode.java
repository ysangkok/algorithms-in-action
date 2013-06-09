// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PatriciaTreeCanvas.java

import com.cim.AIA.*;
import java.awt.*;
import localization.Messages;

protected class init
    implements Moveable
{

    protected void drawNewNode(Graphics g)
    {
        Point bodyLocation = currentPosition;
        body.setPosition(bodyLocation);
        body.draw(g);
        Point itemLocation = new Point();
        itemLocation.x = bodyLocation.x + body.getWidth();
        itemLocation.y = bodyLocation.y;
        dataNode.setPosition(itemLocation);
        dataNode.draw(g);
        if(isDrawLabel)
            g.drawString(Messages.getString("PatriciaTreeCanvas.16"), itemLocation.x - g.getFontMetrics().stringWidth(Messages.getString("PatriciaTreeCanvas.17")) / 2, itemLocation.y);
        int width = body.getWidth();
        int height = body.getHeight();
        if(isSelfLinkLeft != null)
            if(isSelfLinkLeft.booleanValue())
            {
                int startAngle = 90;
                int arcAngle = 270;
                int x = bodyLocation.x - width / 2;
                int y = bodyLocation.y + height / 2;
                PatriciaTreeCanvas.access$000(PatriciaTreeCanvas.this, g, false, x, y, width, height, startAngle, arcAngle);
                PatriciaTreeCanvas.access$100(PatriciaTreeCanvas.this, g, new Point(x + width / 2, y), 15);
            } else
            {
                Line line = new Line(bodyLocation.x, bodyLocation.y + height, bodyLocation.x - width / 2, bodyLocation.y + height + width / 2);
                line.showArrowHead(true);
                line.setDistanceFromStartForArrowHead(-3);
                line.draw(g);
            }
        if(isSelfLinkRight != null)
            if(isSelfLinkRight.booleanValue())
            {
                int startAngle = 180;
                int arcAngle = 270;
                int x = (itemLocation.x + dataNode.getWidth()) - width / 2;
                int y = itemLocation.y + height / 2;
                PatriciaTreeCanvas.access$100(PatriciaTreeCanvas.this, g, new Point(x + width / 2, y), 175);
                PatriciaTreeCanvas.access$000(PatriciaTreeCanvas.this, g, false, x, y, width, height, startAngle, arcAngle);
            } else
            {
                Line line = new Line(itemLocation.x + width, itemLocation.y + height, itemLocation.x + width + width / 2, itemLocation.y + height + width / 2);
                line.showArrowHead(true);
                line.setDistanceFromStartForArrowHead(-3);
                line.draw(g);
            }
    }

    public HierarchyTree getHierarchyTree()
    {
        return patriciaNode.getHierarchyTree();
    }

    public Point getPosition()
    {
        return currentPosition;
    }

    public int getX()
    {
        return currentPosition.x;
    }

    public int getY()
    {
        return currentPosition.y;
    }

    private void init(PatriciaNode patriciaNode, Point position)
    {
        hasBeenTweened = false;
        this.patriciaNode = patriciaNode;
        destinationBody = patriciaNode.getBody();
        destinationDataNode = patriciaNode.getDataItem().getNode();
        currentPosition = new Point(position);
        body = new Node(patriciaNode.getBody().getObject(), 0);
        body.setBackgroundColor(PatriciaTreeColors.DIFFERENTIATING_COLOR);
        dataNode = new Node(patriciaNode.getDataItem().getNode().getObject(), 0);
        dataNode.setBackgroundColor(PatriciaTreeColors.INSERT_BIT_ACTIVE_COLOR);
        if(patriciaNode.getLeft() == patriciaNode)
            isSelfLinkLeft = new Boolean(true);
        else
        if(patriciaNode.getLeft() != null)
            isSelfLinkLeft = new Boolean(false);
        if(patriciaNode.getRight() == patriciaNode)
            isSelfLinkRight = new Boolean(true);
        else
        if(patriciaNode.getRight() != null)
            isSelfLinkRight = new Boolean(false);
    }

    public void setNode(PatriciaNode patriciaNode, Point position)
    {
        if(this.patriciaNode != patriciaNode)
            init(patriciaNode, position);
    }

    public void setX(int newX)
    {
        isDrawLabel = false;
        currentPosition.x = newX;
    }

    public void setY(int newY)
    {
        isDrawLabel = false;
        currentPosition.y = newY;
    }

    public void shiftEntire(int x, int y)
    {
        isDrawLabel = false;
        currentPosition.x += x;
        currentPosition.y += x;
    }

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
    final PatriciaTreeCanvas this$0;

    (PatriciaNode patriciaNode, Point position)
    {
        this$0 = PatriciaTreeCanvas.this;
        super();
        isDrawLabel = true;
        init(patriciaNode, position);
    }
}
