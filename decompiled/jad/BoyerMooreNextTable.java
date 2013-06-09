// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BoyerMooreNextTable.java

import com.cim.AIA.Drawable;
import com.cim.AIA.Node;
import java.awt.*;

public class BoyerMooreNextTable
    implements Drawable
{

    public BoyerMooreNextTable(int size)
    {
        location = new Point();
        infoStart = "nextTable [";
        infoEnd = "] =";
        noInfo = true;
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
            g.drawString((new StringBuilder()).append(infoStart).append("").append(i).append(infoEnd).toString(), xLoc, yLoc + stringHeight);
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

    public int getWidth()
    {
        if(noInfo)
            reInit();
        return stringWidth + XGap + nodeWidth;
    }

    public void highlight(int index)
    {
        dataNode[index].highlight();
    }

    private void initialise()
    {
        XGap = 5;
        YGap = 5;
        data = new int[size];
        dataNode = new Node[size];
        for(int i = 0; i < size; i++)
            dataNode[i] = new Node("", 0);

        noInfo = true;
    }

    public void reInit()
    {
        stringHeight = BoyerMooreApplet.theBoyerMooreCanvas.getGraphics().getFontMetrics().getHeight();
        stringWidth = BoyerMooreApplet.theBoyerMooreCanvas.getGraphics().getFontMetrics().stringWidth((new StringBuilder()).append(infoStart).append("").append(size).append(infoEnd).toString());
        Node temp = new Node("", 0);
        nodeWidth = temp.getWidth();
        nodeHeight = temp.getHeight();
        trueHeight = Math.max(nodeHeight, stringHeight);
        noInfo = false;
    }

    public void set(int index, int theData)
    {
        data[index] = theData;
        dataNode[index] = new Node((new StringBuilder()).append("").append(theData).toString(), 0);
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
}
