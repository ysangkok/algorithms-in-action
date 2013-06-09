// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DFSGraphMapsCanvas.java

import aia.graph.common.*;
import com.cim.AIA.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class DFSGraphMapsCanvas extends AlgorithmCanvas
    implements GraphDialogEventHandler
{

    DFSGraphMapsCanvas()
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

    public void processDialogEvent(String p_sourceDialog, String value)
    {
        commons.processDialogEvent(p_sourceDialog, value);
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

    public void drawNodeAssociatedElements(Graphics g, GraphMapsNode node)
    {
        if(graphMaps == null)
            return;
        Font oldFont = g.getFont();
        g.setFont(new Font("Arial", 1, 10));
        int px = node.getX();
        int py = node.getY();
        int pgx = commons.getGraphX();
        int pgy = commons.getGraphY();
        FontMetrics fm = g.getFontMetrics();
        if(graphMaps.getKMarker() == node.getIntValue())
        {
            g.setColor(Color.black);
            g.drawString("k", pgx, pgy);
            g.setColor(Color.lightGray);
            Line line = new Line(pgx + fm.stringWidth("k") / 2, pgy, px, py);
            line.setColor(Color.lightGray);
            line.showArrowHead(true);
            line.setDistanceFromStartForArrowHead(-3);
            line.draw(g);
        }
        if(graphMaps.getAdjNode() != null && graphMaps.getAdjNode().getTo() == node.getIntValue())
        {
            g.setColor(Color.black);
            g.drawString("adj_node", px - fm.stringWidth("adj_node") - 10, py);
            g.setColor(Color.lightGray);
            Line line = new Line(px - 10, py, px, py);
            line.setColor(Color.lightGray);
            line.showArrowHead(true);
            line.setDistanceFromStartForArrowHead(-3);
            line.draw(g);
        }
        g.setFont(oldFont);
    }

    public void processRepaintEvent(RepaintEvent e)
    {
        if(e.paramObj != null)
        {
            drawables.removeAllElements();
            graphMaps = (DFSGraphMaps)(DFSGraphMaps)e.paramObj;
            if(commons == null)
                commons = new DFSGraphCanvasCommon(this, graphMaps);
            removeAllSelectables();
            commons.setNodes(graphMaps.getNodes(), 30);
            commons.setLinks(graphMaps.getLinks());
            commons.adjustCanvasSize();
            graphMaps.removeAllAnimationRequests();
            repaint();
        }
    }

    protected DFSGraphMaps graphMaps;
    protected DFSGraphCanvasCommon commons;
    protected Font normalFont;
    protected Color textColor;
    public static final int GRAPH_X = 30;
    public static final int GRAPH_Y = 30;
    public static final int NODE_WIDTH = 20;
    public static final int NODE_HEIGHT = 20;
}
