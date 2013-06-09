// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AlignmentMinimum.java

import com.cim.AIA.Drawable;
import com.cim.AIA.Node;
import java.awt.*;
import java.util.Vector;

public class AlignmentMinimum
    implements Drawable
{

    public AlignmentMinimum()
    {
        this("");
    }

    public AlignmentMinimum(String label)
    {
        DEFAULT_NODE_COLOR = new Color(0, 200, 0);
        DEFAULT_HIGHLIGHT_COLOR = Color.green;
        yGap = 5;
        xGap = 5;
        labelXGap = 2;
        lineWidth = 4;
        nodeColor = DEFAULT_NODE_COLOR;
        highlightColor = DEFAULT_HIGHLIGHT_COLOR;
        nodeWidth = 0;
        nodeHeight = 0;
        location = new Point();
        topLine = new Point();
        bottomLine = new Point();
        theData = new Vector();
        theString = new Vector();
        theLabel = label;
    }

    public void add()
    {
        Node tempNode = new Node("", 0);
        nodeWidth = tempNode.getWidth();
        String tempStr = "";
        add(tempNode, tempStr);
    }

    public void add(int n, int x)
    {
        Node tempNode;
        String tempStr;
        if(n == -5000)
        {
            tempNode = new Node(new String("-Inf"), 0);
            if(x >= 0)
                tempStr = (new StringBuilder()).append("+ ").append(x).append(" = -Inf").toString();
            else
                tempStr = (new StringBuilder()).append("- ").append(-x).append(" = -Inf").toString();
        } else
        if(n == 5000)
        {
            tempNode = new Node(new String("Inf"), 0);
            if(x >= 0)
                tempStr = (new StringBuilder()).append("+ ").append(x).append(" = Inf").toString();
            else
                tempStr = (new StringBuilder()).append("- ").append(-x).append(" = Inf").toString();
        } else
        {
            tempNode = new Node(new Integer(n), 0);
            if(x >= 0)
                tempStr = (new StringBuilder()).append("+ ").append(x).append(" = ").append(x + n).toString();
            else
                tempStr = (new StringBuilder()).append("- ").append(-x).append(" = ").append(x + n).toString();
        }
        nodeWidth = tempNode.getWidth();
        nodeHeight = tempNode.getHeight();
        add(tempNode, tempStr);
    }

    public void add(Node n, String str)
    {
        n.setSequence(0);
        n.setBackgroundColor(nodeColor);
        n.setHighlightColor(highlightColor);
        theData.addElement(n);
        theString.addElement(str);
    }

    public void draw(Graphics g)
    {
        setLabelWidth(g);
        positionNodes();
        drawNodes(g);
        drawStrings(g);
        drawLines(g);
        drawLabel(g);
    }

    public void draw(Graphics g, Point pnt)
    {
        setLocation(pnt);
        draw(g);
    }

    protected void drawLabel(Graphics g)
    {
        if(theData.size() <= 0)
        {
            return;
        } else
        {
            g.drawString(theLabel, location.x, topLine.y + (bottomLine.y - topLine.y) / 2 + g.getFontMetrics().getAscent() / 2);
            return;
        }
    }

    protected void drawLines(Graphics g)
    {
        int xPos = location.x;
        xPos = xPos + labelWidth + labelXGap;
        if(theData.size() > 0)
        {
            Node tempNode = (Node)theData.elementAt(0);
            topLine.y = tempNode.getY();
            topLine.y = topLine.y + tempNode.getHeight() / 2;
            tempNode = (Node)theData.elementAt(theData.size() - 1);
            bottomLine.y = tempNode.getY();
            bottomLine.y = bottomLine.y + tempNode.getHeight() / 2;
            g.drawLine(xPos, topLine.y + (bottomLine.y - topLine.y) / 2, xPos + lineWidth, topLine.y + (bottomLine.y - topLine.y) / 2);
            xPos += lineWidth;
            g.drawLine(xPos, topLine.y, xPos, bottomLine.y);
            for(int i = 0; i < theData.size(); i++)
            {
                tempNode = (Node)theData.elementAt(i);
                int yPos = tempNode.getY() + tempNode.getHeight() / 2;
                g.drawLine(xPos, yPos, xPos + lineWidth, yPos);
            }

        }
    }

    protected void drawNodes(Graphics g)
    {
        for(int i = 0; i < theData.size(); i++)
        {
            Node tempNode = (Node)theData.elementAt(i);
            if(tempNode.getIdentifier() == 1)
                tempNode.highlight();
            else
                tempNode.unHighlight();
            tempNode.draw(g);
        }

    }

    protected void drawStrings(Graphics g)
    {
        for(int i = 0; i < theString.size(); i++)
        {
            Node tempNode = (Node)theData.elementAt(i);
            String tempString = (String)theString.elementAt(i);
            int xPos = tempNode.getX();
            int yPos = tempNode.getY() + tempNode.getHeight() / 2;
            if(tempNode.getIdentifier() == 1)
            {
                g.setColor(highlightColor);
                g.fillRect(xPos + nodeWidth + xGap, yPos - g.getFontMetrics().getAscent() / 2, g.getFontMetrics().stringWidth(tempString), g.getFontMetrics().getAscent());
            }
            g.setColor(Color.black);
            g.drawString(tempString, xPos + nodeWidth + xGap, yPos + g.getFontMetrics().getAscent() / 2);
        }

    }

    public int getHeight()
    {
        return nodeHeight * theData.size() + yGap * (theData.size() - 1);
    }

    public Point getLocation()
    {
        return location;
    }

    public int getSize()
    {
        return theData.size();
    }

    public void highlight(int index)
    {
        Node tempNode = (Node)theData.elementAt(index);
        tempNode.setSequence(1);
    }

    protected void positionNodes()
    {
        int xPos = location.x;
        int yPos = location.y;
        for(int i = 0; i < theData.size(); i++)
        {
            Node tempNode = (Node)theData.elementAt(i);
            tempNode.setX(xPos + labelWidth + labelXGap + lineWidth + lineWidth + xGap);
            tempNode.setY(yPos + i * (yGap + tempNode.getHeight()));
        }

    }

    public void set(int index)
    {
        Node tempNode = new Node("", 0);
        nodeWidth = tempNode.getWidth();
        String tempStr = "";
        set(index, tempNode, tempStr);
    }

    public void set(int index, int n, int x)
    {
        Node tempNode;
        String tempStr;
        if(n == -5000)
        {
            tempNode = new Node(new String("-Inf"), 0);
            if(x >= 0)
                tempStr = (new StringBuilder()).append("+ ").append(x).append(" = -Inf").toString();
            else
                tempStr = (new StringBuilder()).append("- ").append(-x).append(" = -Inf").toString();
        } else
        if(n == 5000)
        {
            tempNode = new Node(new String("Inf"), 0);
            if(x >= 0)
                tempStr = (new StringBuilder()).append("+ ").append(x).append(" = Inf").toString();
            else
                tempStr = (new StringBuilder()).append("- ").append(-x).append(" = Inf").toString();
        } else
        {
            tempNode = new Node(new Integer(n), 0);
            if(x >= 0)
                tempStr = (new StringBuilder()).append("+ ").append(x).append(" = ").append(x + n).toString();
            else
                tempStr = (new StringBuilder()).append("- ").append(-x).append(" = ").append(x + n).toString();
        }
        nodeWidth = tempNode.getWidth();
        nodeHeight = tempNode.getHeight();
        set(index, tempNode, tempStr);
    }

    public void set(int index, Node n, String str)
    {
        n.setSequence(0);
        n.setBackgroundColor(nodeColor);
        n.setHighlightColor(highlightColor);
        theData.setElementAt(n, index);
        theString.setElementAt(str, index);
    }

    protected void setLabelWidth(Graphics g)
    {
        labelWidth = g.getFontMetrics().stringWidth(theLabel);
    }

    public void setLocation(int xPos, int yPos)
    {
        location.x = xPos;
        location.y = yPos;
    }

    public void setLocation(Point newPnt)
    {
        location = newPnt;
    }

    public void unHighlight()
    {
        for(int i = 0; i < theData.size(); i++)
        {
            Node tempNode = (Node)theData.elementAt(i);
            tempNode.setSequence(0);
        }

    }

    public void unHighlight(int index)
    {
        Node tempNode = (Node)theData.elementAt(index);
        tempNode.setSequence(0);
    }

    protected final int DEFAULT_Y_GAP = 5;
    protected final int DEFAULT_X_GAP = 5;
    protected final int DEFAULT_LABEL_X_GAP = 2;
    protected final int DEFAULT_LINE_WIDTH = 4;
    protected final Color DEFAULT_NODE_COLOR;
    protected final Color DEFAULT_HIGHLIGHT_COLOR;
    protected String theLabel;
    protected Vector theData;
    protected Vector theString;
    protected int yGap;
    protected int xGap;
    protected int labelXGap;
    protected int lineWidth;
    protected Color nodeColor;
    protected Color highlightColor;
    protected int nodeWidth;
    protected int nodeHeight;
    protected int labelWidth;
    protected Point location;
    protected Point topLine;
    protected Point bottomLine;
}
