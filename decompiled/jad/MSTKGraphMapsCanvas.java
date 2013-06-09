// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MSTKGraphMapsCanvas.java

import aia.graph.common.*;
import com.cim.AIA.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class MSTKGraphMapsCanvas extends GraphMapsCanvas
    implements GraphDialogEventHandler
{

    MSTKGraphMapsCanvas()
    {
        commons = null;
        tweens = new MultipleTween(this);
    }

    protected void handleColorSelection(ColorSelection colorSelection)
    {
        String atribute = colorSelection.getAtributeName();
        if(atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR))
            textColor = colorSelection.getColor();
    }

    protected void handleFontSelection(FontSelection fontSelection)
    {
        String atribute = fontSelection.getAtributeName();
        if(atribute.equalsIgnoreCase("Normal Font"))
            normalFont = fontSelection.getFont();
    }

    public void processDialogEvent(String sourceDialog, String value)
    {
        commons.processDialogEvent(sourceDialog, value);
    }

    public void processMouseMotionEvent(MouseEvent evt)
    {
        commons.mouseMoved(evt.getX(), evt.getY());
    }

    public void mouseReleased(MouseEvent evt)
    {
        commons.mouseReleased();
    }

    public void mousePressed(MouseEvent evt)
    {
        commons.mousePressed(evt.getX(), evt.getY());
        super.mousePressed(evt);
    }

    public void displayAlgorithm(Graphics g)
    {
        if(normalFont != null)
            g.setFont(normalFont);
        if(textColor != null)
            g.setColor(textColor);
        if(commons == null)
        {
            return;
        } else
        {
            commons.drawLinks(g);
            commons.drawNodes(g);
            return;
        }
    }

    public Color getTextColor()
    {
        return textColor;
    }

    public void drawLinkAssociatedElements(Graphics g1, Link link1, Line line1)
    {
    }

    public void drawNodeAssociatedElements(Graphics g1, GraphMapsNode graphmapsnode)
    {
    }

    public void processRepaintEvent(RepaintEvent e)
    {
        int matrix_width = 0;
        if(e.paramObj != null)
        {
            drawables.removeAllElements();
            graphMaps = (MSTKGraphMaps)(MSTKGraphMaps)e.paramObj;
            if(commons == null)
                commons = new MSTKGraphCanvasCommon(this, graphMaps);
            removeAllSelectables();
            commons.setNodes(graphMaps.getNodes(), 30);
            commons.setLinks(graphMaps.getLinks());
            commons.adjustCanvasSize();
            graphMaps.removeAllAnimationRequests();
            repaint();
        }
    }

    protected MSTKGraphMaps graphMaps;
    protected MSTKGraphCanvasCommon commons;
    protected Font normalFont;
    protected Color textColor;
    public static final int GRAPH_X = 30;
    public static final int GRAPH_Y = 330;
    public static final int NODE_WIDTH = 20;
    public static final int NODE_HEIGHT = 20;
}
