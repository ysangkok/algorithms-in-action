// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AlignmentKey.java

import com.cim.AIA.Drawable;
import com.cim.AIA.Node;
import java.awt.*;
import localization.Messages;

public class AlignmentKey
    implements Drawable
{

    public AlignmentKey()
    {
        location = new Point();
        data = new String[6];
        dataNode = new Node[6];
        runningWidth = new int[2];
        setLocation(0, 0);
        data[0] = new String(Messages.getString("AlignmentKey.0"));
        dataNode[0] = new Node("", 0);
        dataNode[0].setBackgroundColor(AlignmentNode.DEFAULT_ARRAY_DISABLE);
        data[1] = new String(Messages.getString("AlignmentKey.2"));
        dataNode[1] = new Node("", 0);
        dataNode[1].setBackgroundColor(Alignment.DEFAULT_CURRENT_COLOR);
        dataNode[1].setRingColor(Alignment.HIGHLIGHT_RING_COLOR);
        data[2] = new String(Messages.getString("AlignmentKey.4"));
        dataNode[2] = new Node("", 0);
        dataNode[2].setBackgroundColor(Alignment.IRRELEVANT_COLOR);
        dataNode[2].setRingColor(Alignment.HIGHLIGHT_RING_COLOR);
        data[3] = new String(Messages.getString("AlignmentKey.6"));
        dataNode[3] = new Node("", 0);
        dataNode[3].setBackgroundColor(Alignment.DEFAULT_NODE_COLOR);
        data[4] = new String(Messages.getString("AlignmentKey.8"));
        dataNode[4] = new Node("", 0);
        dataNode[4].setBackgroundColor(Alignment.DEFAULT_SELECTED_COLOR);
        data[5] = new String(Messages.getString("AlignmentKey.10"));
        dataNode[5] = new Node("", 0);
        dataNode[5].setBackgroundColor(Alignment.DEFAULT_HIGHLIGHT_COLOR);
    }

    public void draw(Graphics g)
    {
        int counter = 0;
        int xPos = location.x;
        g.setColor(Color.black);
        for(int i = 0; i < 2; i++)
        {
            int yPos = location.y;
            for(int j = 0; j < numPerColumn && counter < 6; j++)
            {
                dataNode[counter].setPosition(new Point(xPos, yPos));
                dataNode[counter].draw(g);
                g.drawString(data[counter], xPos + 10 + dataNode[counter].getWidth(), yPos + g.getFontMetrics().getAscent() / 2 + dataNode[counter].getHeight() / 2);
                yPos += dataNode[counter].getHeight();
                counter++;
            }

            xPos = xPos + 10 + runningWidth[i];
        }

    }

    public void draw(Graphics g, Point pnt)
    {
        setLocation(pnt);
        draw(g);
    }

    public int getHeight()
    {
        Node testNode = new Node("", 0);
        return testNode.getHeight() * numPerColumn;
    }

    public Point getLocation()
    {
        return location;
    }

    public int getWidth()
    {
        int counter = 0;
        int currentWidth = 0;
        for(int i = 0; i < 2; i++)
        {
            for(int j = 0; j < numPerColumn && counter < 6; j++)
            {
                int tempWidth = dataNode[counter].getWidth() + AlignmentApplet.theAlignmentCanvas.getGraphics().getFontMetrics().stringWidth(data[counter]) + 10;
                if(tempWidth > runningWidth[i])
                    runningWidth[i] = tempWidth;
                counter++;
            }

            currentWidth += runningWidth[i];
        }

        return currentWidth;
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

    protected final int X_GAP = 10;
    protected final int numOfEntries = 6;
    protected final int numOfColumns = 2;
    protected final int numPerColumn = (int)Math.ceil(3D);
    protected Point location;
    protected String data[];
    protected Node dataNode[];
    protected int runningWidth[];
}
