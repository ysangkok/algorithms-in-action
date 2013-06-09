// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KMPNextTable.java

import com.cim.AIA.Drawable;
import com.cim.AIA.Node;
import java.awt.*;
import localization.Messages;

public class KMPNextTable
    implements Drawable
{

    public KMPNextTable(int size)
    {
        location = new Point();
        infoStart = Messages.getString("KMPNextTable.0");
        infoEnd = "] =";
        noInfo = true;
        labelColor = Color.red;
        defaultHighlightColor = new Color(255, 90, 90);
        highlightColor = new Color(255, 90, 90);
        setLocation(0, 0);
        this.size = size;
        initialise();
    }

    public void draw(Graphics g)
    {
        if(noInfo)
            reInit();
        int xLoc = location.x;
        int yLoc = location.y;
        for(int i = 0; i < size; i++)
        {
            if(highlightIndex && i == labelIndex)
            {
                Color saveColor = g.getColor();
                g.setColor(labelColor);
                g.drawString((new StringBuilder()).append(infoStart).append("").append(i).append(infoEnd).toString(), xLoc, yLoc + stringHeight);
                g.setColor(saveColor);
            } else
            {
                g.drawString((new StringBuilder()).append(infoStart).append("").append(i).append(infoEnd).toString(), xLoc, yLoc + stringHeight);
            }
            dataNode[i].setPosition(new Point(xLoc + stringWidth + XGap, yLoc));
            dataNode[i].draw(g);
            yLoc = yLoc + trueHeight + YGap;
        }

    }

    public void draw(Graphics g, Point pnt)
    {
        setLocation(pnt);
        draw(g);
    }

    public int get(int index)
    {
        return data[index];
    }

    public int getHeight()
    {
        if(noInfo)
            reInit();
        return (trueHeight + YGap) * size;
    }

    public Point getLocation()
    {
        return location;
    }

    public Point getNodePosition(int index)
    {
        Point temp = new Point();
        temp.x = location.x + stringWidth + XGap;
        temp.y = location.y + (trueHeight + YGap) * index;
        return temp;
    }

    public int getWidth()
    {
        if(noInfo)
            reInit();
        return stringWidth + XGap + nodeWidth;
    }

    public int getY()
    {
        return location.y;
    }

    public void highlight(int index)
    {
        dataNode[index].highlight();
    }

    public void highlightIndex()
    {
        highlightIndex = true;
    }

    private void initialise()
    {
        XGap = 5;
        YGap = 5;
        data = new int[size];
        dataNode = new Node[size];
        for(int i = 0; i < size; i++)
        {
            dataNode[i] = new Node("", 0);
            dataNode[i].setHighlightColor(highlightColor);
        }

        noInfo = true;
        highlightIndex = false;
        labelIndex = 0;
    }

    public void reInit()
    {
        stringHeight = KMPApplet.theKMPCanvas.getGraphics().getFontMetrics().getHeight();
        stringWidth = KMPApplet.theKMPCanvas.getGraphics().getFontMetrics().stringWidth((new StringBuilder()).append(infoStart).append("").append(size).append(infoEnd).toString());
        Node temp = new Node("", 0);
        nodeWidth = temp.getWidth();
        nodeHeight = temp.getHeight();
        trueHeight = Math.max(nodeHeight, stringHeight);
        noInfo = false;
    }

    public void restoreHighlightColor(Color c)
    {
        highlightColor = defaultHighlightColor;
        for(int i = 0; i < size; i++)
            dataNode[i].setHighlightColor(highlightColor);

    }

    public void set(int index, int theData)
    {
        data[index] = theData;
        dataNode[index] = new Node((new StringBuilder()).append("").append(theData).toString(), 0);
        dataNode[index].setHighlightColor(highlightColor);
    }

    public void setHighlightColor(Color c)
    {
        highlightColor = c;
        for(int i = 0; i < size; i++)
            dataNode[i].setHighlightColor(highlightColor);

    }

    public void setLabelIndex(int s)
    {
        labelIndex = s;
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

    public void setPosition(int xPos, int yPos)
    {
        setLocation(xPos, yPos);
    }

    public void unHighlight(int index)
    {
        dataNode[index].unHighlight();
    }

    public void unHighlightAll()
    {
        for(int i = 0; i < size; i++)
            dataNode[i].unHighlight();

    }

    public void unHighlightIndex()
    {
        highlightIndex = false;
    }

    protected int size;
    protected int data[];
    protected Node dataNode[];
    protected Point location;
    protected int XGap;
    protected int YGap;
    protected int stringWidth;
    protected int stringHeight;
    protected int nodeWidth;
    protected int nodeHeight;
    protected int trueHeight;
    protected String infoStart;
    protected String infoEnd;
    protected boolean noInfo;
    protected int labelIndex;
    protected boolean highlightIndex;
    protected Color labelColor;
    protected Color defaultHighlightColor;
    protected Color highlightColor;
}
