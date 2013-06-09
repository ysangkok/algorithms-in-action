// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BoyerMooreSkipTable.java

import com.cim.AIA.Drawable;
import com.cim.AIA.Node;
import java.awt.*;
import localization.Messages;

public class BoyerMooreSkipTable
    implements Drawable
{

    public BoyerMooreSkipTable(int size)
    {
        location = new Point();
        infoStart = Messages.getString("BoyerMooreSkipTable.0");
        infoEnd = "] =";
        setLocation(0, 0);
        this.size = 27;
        initialise();
    }

    public void draw(Graphics g)
    {
        if(noInfo)
            reInit();
        int xLoc = location.x;
        int yLoc = location.y;
        for(int i = 0; i < 13; i++)
        {
            dataNode[i].setPosition(new Point(xLoc, yLoc + stringHeight));
            dataNode[i].draw(g);
            g.drawString((new StringBuilder()).append("").append((char)(i + 65)).toString(), xLoc, yLoc + stringHeight);
            xLoc = xLoc + XGap + nodeWidth;
        }

        yLoc = yLoc + stringHeight + nodeHeight + YGap;
        xLoc = location.x;
        for(int i = 13; i < 26; i++)
        {
            dataNode[i].setPosition(new Point(xLoc, yLoc + stringHeight));
            dataNode[i].draw(g);
            g.drawString((new StringBuilder()).append("").append((char)(i + 65)).toString(), xLoc, yLoc + stringHeight);
            xLoc = xLoc + XGap + nodeWidth;
        }

        yLoc = yLoc + stringHeight + nodeHeight + YGap;
        xLoc = location.x;
        dataNode[26].setPosition(new Point(xLoc, yLoc + stringHeight));
        dataNode[26].draw(g);
        g.drawString(Messages.getString("BoyerMooreSkipTable.1"), xLoc, yLoc + stringHeight);
    }

    public void draw(Graphics g, Point pnt)
    {
        setLocation(pnt);
        draw(g);
    }

    public int get(int index)
    {
        index = translateIndex(index);
        return data[index];
    }

    public int getHeight()
    {
        if(noInfo)
            reInit();
        return stringHeight + nodeHeight + YGap + stringHeight + nodeHeight + YGap + stringHeight + nodeHeight;
    }

    public Point getLocation()
    {
        return location;
    }

    public int getWidth()
    {
        if(noInfo)
            reInit();
        return (XGap + nodeWidth) * 13;
    }

    public void highlight(int index)
    {
        index = translateIndex(index);
        dataNode[index].highlight();
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
            data[i] = 0;
        }

        noInfo = true;
    }

    public void reInit()
    {
        stringHeight = BoyerMooreApplet.theBoyerMooreCanvas.getGraphics().getFontMetrics().getHeight();
        Node temp = new Node("", 0);
        nodeWidth = temp.getWidth();
        nodeHeight = temp.getHeight();
        noInfo = false;
    }

    public void set(int index, int theData)
    {
        index = translateIndex(index);
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

    private int translateIndex(int index)
    {
        if(index > 25 && index >= 65 && index <= 90)
            index -= 65;
        if(index == 32)
            index = 26;
        return index;
    }

    public void unHighlight(int index)
    {
        index = translateIndex(index);
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
