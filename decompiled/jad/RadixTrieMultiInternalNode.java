// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RadixTrieMultiInternalNode.java

import com.cim.AIA.*;
import java.awt.*;
import java.util.Vector;

public class RadixTrieMultiInternalNode extends RadixTrieMultiNode
{

    RadixTrieMultiInternalNode()
    {
        radixTrieNodes = new Vector();
        backgroundColor = Color.orange;
        DEFAULT_COLOR = Color.black;
        position = new Point(0, 0);
        hierarchyTree = new HierarchyTree();
        Node node = new Node("", 0);
        node.setWidth(RadixTrieMulti.NO_OF_NODES * 3);
        node.setHeight(6);
        hierarchyTree.add(node);
        for(int i = 0; i < RadixTrieMulti.NO_OF_NODES; i++)
            hierarchyTree.addChild(new HierarchyTree());

        hierarchyTree.setDrawBorder(false);
        for(int i = 0; i < RadixTrieMulti.NO_OF_NODES; i++)
            radixTrieNodes.addElement(new RadixTrieMultiNullNode(this));

    }

    public RadixTrieMultiInternalNode getParent()
    {
        return parent;
    }

    public int getRadius()
    {
        return 3;
    }

    public Point getPosition()
    {
        return new Point(position);
    }

    public void setPosition(Point position)
    {
        Point currentPosition = new Point(position);
        upperLeft = new Point();
        upperLeft.y = position.y - 3;
        upperLeft.x = position.x - 3;
        Point saveUpperLeft = new Point(upperLeft);
        lowerRight = new Point();
        lowerRight.y = position.y + 3;
        lowerRight.x = position.x + 3;
        Point saveLowerRight = new Point(lowerRight);
        this.position = new Point(position);
        for(int i = 0; i < RadixTrieMulti.NO_OF_NODES / 2; i++)
        {
            RadixTrieMultiNode n = (RadixTrieMultiNode)radixTrieNodes.elementAt(i);
            if(n != null)
                currentPosition = n.layout(currentPosition.x, currentPosition.y + 40);
        }

        upperLeft = new Point(saveUpperLeft);
        lowerRight = new Point(saveLowerRight);
        position.x = position.x - (currentPosition.x - position.x);
        lowerRight.x = position.x + 3;
        upperLeft.x = position.x - 3;
        for(int i = 0; i < RadixTrieMulti.NO_OF_NODES; i++)
        {
            RadixTrieMultiNode n = (RadixTrieMultiNode)radixTrieNodes.elementAt(i);
            if(n != null)
                position = n.layout(position.x, position.y + 40);
        }

    }

    protected synchronized Point layout(int x, int y)
    {
        Point position = new Point(x, y);
        Point lastPosition = new Point(x, y);
        if(position.x - 3 < upperLeft.x)
            upperLeft.x = position.x - 3;
        if(position.y - 3 < upperLeft.y)
            upperLeft.y = position.y - 3;
        if(position.x + 3 > lowerRight.x)
            lowerRight.x = position.x + 3;
        if(position.y + 3 > lowerRight.y)
            lowerRight.y = position.y + 3;
        for(int i = 0; i < RadixTrieMulti.NO_OF_NODES; i++)
        {
            RadixTrieMultiNode n = (RadixTrieMultiNode)radixTrieNodes.elementAt(i);
            lastPosition = position;
            position = n.layout(position.x, position.y + 40);
        }

        this.position.x = (lowerRight.x - x) / 2 + x;
        this.position.y = y;
        return new Point(lowerRight.x + 3 + 8, position.y - 40);
    }

    public Rectangle getRectangle()
    {
        if(position == null)
            position = new Point();
        setPosition(position);
        return new Rectangle(upperLeft.x, upperLeft.y, lowerRight.x - upperLeft.x, lowerRight.y - upperLeft.y);
    }

    public void draw(Graphics g)
    {
        g.setColor(DEFAULT_COLOR);
        Point currentPosition = new Point(position);
        currentPosition.x = currentPosition.x - 8;
        int increment = 16 / (RadixTrieMulti.NO_OF_NODES - 1);
        for(int i = 0; i < RadixTrieMulti.NO_OF_NODES; i++)
        {
            RadixTrieMultiNode n = (RadixTrieMultiNode)radixTrieNodes.elementAt(i);
            if(n != null)
            {
                if(n.getIsLinkedIn())
                {
                    Line nLink;
                    if(n.getIsOnPath())
                    {
                        nLink = new Line(position.x, position.y, n.position.x, n.position.y, Color.red);
                        nLink.showAsThick(true);
                    } else
                    {
                        nLink = new Line(position.x, position.y, n.position.x, n.position.y, Color.black);
                    }
                    nLink.draw(g);
                }
                n.draw(g);
            } else
            {
                g.drawLine(position.x, position.y, currentPosition.x + increment * i, currentPosition.y + 13);
            }
        }

        if(parent == null)
            drawNode(g);
        for(int i = 0; i < RadixTrieMulti.NO_OF_NODES; i++)
        {
            RadixTrieMultiNode n = (RadixTrieMultiNode)radixTrieNodes.elementAt(i);
            if(n != null)
                n.drawNode(g);
        }

    }

    public void drawNode(Graphics g)
    {
        g.setColor(backgroundColor.brighter());
        g.fillOval(position.x - 3 - 1, position.y - 3 - 1, 6, 6);
        g.setColor(backgroundColor.darker());
        g.fillOval((position.x - 3) + 1, (position.y - 3) + 1, 6, 6);
        g.setColor(backgroundColor);
        g.fillOval(position.x - 3, position.y - 3, 6, 6);
        g.setColor(DEFAULT_COLOR);
    }

    public void setChild(RadixTrieMultiNode node, int child)
    {
        node.parent = this;
        radixTrieNodes.removeElementAt(child);
        radixTrieNodes.insertElementAt(node, child);
        hierarchyTree.removeChild(hierarchyTree.getChild(child));
        hierarchyTree.insertChildAt(node.getHierarchyTree(), child);
    }

    public void setBackgroundColor(Color color)
    {
        backgroundColor = color;
    }

    public RadixTrieMultiNode getChild(int child)
    {
        return (RadixTrieMultiNode)radixTrieNodes.elementAt(child);
    }

    public HierarchyTree getHierarchyTree()
    {
        return hierarchyTree;
    }

    private Vector radixTrieNodes;
    protected static final int NODE_X_GAP = 8;
    private static final int radius = 3;
    private static final int nullWidth = 6;
    private HierarchyTree hierarchyTree;
    private Color backgroundColor;
    private Color DEFAULT_COLOR;
}
