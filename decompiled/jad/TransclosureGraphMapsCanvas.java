// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TransclosureGraphMapsCanvas.java

import aia.graph.common.*;
import com.cim.AIA.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class TransclosureGraphMapsCanvas extends GraphMapsCanvas
    implements GraphDialogEventHandler
{

    TransclosureGraphMapsCanvas()
    {
        commons = null;
        vecNodes = null;
        m_linkJustAdded = new Line(0, 0, 0, 0);
        m_linkJustAddedVisible = false;
        tweens = new MultipleTween(this);
    }

    public Color getTextColor()
    {
        return textColor;
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
            return;
        commons.drawLinks(g);
        if(m_linkJustAddedVisible)
            m_linkJustAdded.draw(g);
        commons.drawNodes(g);
    }

    private GraphMapsNode findNode(int node_key)
    {
        for(int i = 0; i < vecNodes.size(); i++)
        {
            GraphMapsNode node = (GraphMapsNode)vecNodes.elementAt(i);
            if(node.getIntValue() == node_key)
                return node;
        }

        return null;
    }

    public void drawLinkAssociatedElements(Graphics g1, Link link1, Line line1)
    {
    }

    public void drawNodeAssociatedElements(Graphics g, GraphMapsNode node)
    {
        String strNodeLabel = null;
        if(graphMaps == null)
            return;
        Font oldFont = g.getFont();
        g.setFont(new Font("Arial", 1, 10));
        int px = node.getX();
        int py = node.getY();
        int pw = node.getWidth();
        int pgx = commons.getGraphX();
        int pgy = commons.getGraphY();
        FontMetrics fm = g.getFontMetrics();
        if(graphMaps.getX() == node.getIntValue())
            strNodeLabel = "x";
        if(graphMaps.getY() == node.getIntValue())
            if(strNodeLabel != null)
                strNodeLabel = (new StringBuilder()).append(strNodeLabel).append(",y").toString();
            else
                strNodeLabel = "y";
        if(graphMaps.getJ() == node.getIntValue())
            if(strNodeLabel != null)
                strNodeLabel = (new StringBuilder()).append(strNodeLabel).append(",j").toString();
            else
                strNodeLabel = "j";
        if(strNodeLabel != null)
        {
            g.setColor(Color.black);
            g.drawString(strNodeLabel, px - fm.stringWidth(strNodeLabel) - 10, py);
            g.setColor(Color.lightGray);
            Line line = new Line(px - 10, py, px, py);
            line.setColor(Color.lightGray);
            line.showArrowHead(true);
            line.setDistanceFromStartForArrowHead(-3);
            line.draw(g);
        }
    }

    public void processRepaintEvent(RepaintEvent e)
    {
        if(e.paramObj != null)
        {
            drawables.removeAllElements();
            m_linkJustAddedVisible = false;
            graphMaps = (TransclosureGraphMaps)(TransclosureGraphMaps)e.paramObj;
            if(commons == null)
                commons = new TransclosureGraphCanvasCommon(this, graphMaps);
            removeAllSelectables();
            vecNodes = graphMaps.getNodes();
            commons.setNodes(vecNodes, 30);
            vecNodes = commons.getNodes();
            commons.setLinks(graphMaps.getLinks());
            commons.adjustCanvasSize();
            MultipleTween multipleTween = graphMaps.generateTweens(this, m_linkJustAdded, vecNodes, numberOfTweenSteps);
            if(multipleTween != null)
                addTween(multipleTween);
            if(tweens.getNumberOfTweens() > 0)
            {
                m_linkJustAddedVisible = true;
                tweens.run();
            }
            graphMaps.removeAllAnimationRequests();
            repaint();
        }
    }

    protected TransclosureGraphMaps graphMaps;
    protected TransclosureGraphCanvasCommon commons;
    protected Font normalFont;
    protected Color textColor;
    public static final int GRAPH_X = 30;
    public static final int GRAPH_Y = 30;
    public static final int NODE_WIDTH = 20;
    public static final int NODE_HEIGHT = 20;
    protected Vector vecNodes;
    protected Line m_linkJustAdded;
    protected boolean m_linkJustAddedVisible;
}
