// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DFSGraphCanvasCommon.java

import aia.graph.common.*;
import com.cim.AIA.Line;
import com.cim.AIA.RepaintEvent;
import java.awt.*;
import java.util.Vector;
import localization.Messages;

public class DFSGraphCanvasCommon extends GraphCanvasCommon
{

    DFSGraphCanvasCommon(DFSGraphMapsCanvas p_canvas, DFSGraphMaps p_algorithm)
    {
        GRAPH_X = 30;
        GRAPH_Y = 30;
        vecNodes = null;
        vecLinks = null;
        m_dragNodeIndex = -1;
        m_frmEditLink = null;
        m_dlgEditLink = null;
        m_frmKeyChange = null;
        m_dlgKeyChange = null;
        m_KeyChangeNode = null;
        m_canvas = p_canvas;
        m_algorithm = p_algorithm;
    }

    public int getGraphX()
    {
        return GRAPH_X;
    }

    public int getGraphY()
    {
        return GRAPH_Y;
    }

    public Vector getNodes()
    {
        return vecNodes;
    }

    public Vector getLinks()
    {
        return vecLinks;
    }

    public void setNodes(Vector p_nodes, int graph_x)
    {
        vecNodes = p_nodes;
        GRAPH_X = graph_x;
        if(vecNodes != null)
        {
            for(int i = 0; i < vecNodes.size(); i++)
            {
                GraphMapsNode node = (GraphMapsNode)vecNodes.elementAt(i);
                node.setPosition(new Point(node.getX() + GRAPH_X, node.getY() + GRAPH_Y));
                m_canvas.addSelectable(node);
            }

        }
    }

    public void setLinks(Vector p_links)
    {
        vecLinks = p_links;
    }

    public void adjustCanvasSize()
    {
        int canvas_width = 440;
        int canvas_height = 360;
        int max_graph_width = 0;
        int max_graph_height = 0;
        m_canvas.setSize(canvas_width, canvas_height);
        if(vecNodes == null)
            return;
        for(int i = 0; i < vecNodes.size(); i++)
        {
            GraphMapsNode node = (GraphMapsNode)vecNodes.elementAt(i);
            DFSGraphMapsCanvas _tmp = m_canvas;
            if(node.getX() + 20 > max_graph_width)
            {
                DFSGraphMapsCanvas _tmp1 = m_canvas;
                max_graph_width = node.getX() + 20;
            }
            DFSGraphMapsCanvas _tmp2 = m_canvas;
            if(node.getY() + 20 > max_graph_height)
            {
                DFSGraphMapsCanvas _tmp3 = m_canvas;
                max_graph_height = node.getY() + 20;
            }
        }

        if(max_graph_width > canvas_width)
            canvas_width = max_graph_width;
        if(max_graph_height > canvas_height)
            canvas_height = max_graph_height;
        m_canvas.setSize(canvas_width, canvas_height);
    }

    public void processDialogEvent(String sourceDialog, String value)
    {
        if(m_algorithm != null && !m_algorithm.getIsRunning() && value != null)
            if(sourceDialog.equals(Messages.getString("DFSGraphCanvasCommon.0")))
            {
                m_algorithm.keyChange(m_KeyChangeNode.getIntValue(), Integer.parseInt(value));
                m_algorithm.setKeyChangeMode(false);
            } else
            if(sourceDialog.equals(Messages.getString("DFSGraphCanvasCommon.1")))
            {
                m_algorithm.setLinkWeight(Integer.parseInt(value));
                m_algorithm.endEditLink();
            }
        if(m_algorithm != null)
        {
            if(m_algorithm.getKeyChangeMode())
                m_algorithm.setKeyChangeMode(false);
            if(m_algorithm.editLinkStarted())
                m_algorithm.endEditLink();
            m_canvas.processRepaintEvent(new RepaintEvent(m_canvas, m_algorithm));
        }
    }

    public void mouseMoved(int px, int py)
    {
        if(m_dragNodeIndex == -1)
            return;
        DFSGraphMapsCanvas _tmp = m_canvas;
        int x = px - 20 / 2;
        DFSGraphMapsCanvas _tmp1 = m_canvas;
        int y = py - 20 / 2;
        if(m_algorithm != null && !m_algorithm.getIsRunning())
            m_algorithm.setNodePosition(m_dragNodeIndex, x - GRAPH_X, y - GRAPH_Y);
    }

    public void mouseReleased()
    {
        if(m_dragNodeIndex != -1)
        {
            m_dragNodeIndex = -1;
            m_canvas.processRepaintEvent(new RepaintEvent(m_canvas, m_algorithm));
        }
    }

    public void mousePressed(int px, int py)
    {
        if(m_algorithm != null && !m_algorithm.getIsRunning() && m_algorithm.getInsertionMode())
        {
            m_algorithm.insertNewNode(px - GRAPH_X, py - GRAPH_Y);
            m_algorithm.setInsertionMode(false);
        } else
        if(m_algorithm != null && vecNodes != null)
        {
            for(int i = 0; i < vecNodes.size(); i++)
            {
                GraphMapsNode node = (GraphMapsNode)vecNodes.elementAt(i);
                if(px > node.getX() && px < node.getX() + node.getWidth() && py > node.getY() && py < node.getY() + node.getHeight())
                {
                    if(m_algorithm.getKeyChangeMode())
                    {
                        m_KeyChangeNode = node;
                        m_frmKeyChange = new Frame();
                        m_frmKeyChange.setSize(100, 20);
                        m_dlgKeyChange = new InputDialog(m_canvas, m_frmKeyChange, Messages.getString("DFSGraphCanvasCommon.2"), true, Messages.getString("DFSGraphCanvasCommon.3"), node.getIntValue());
                        m_dlgKeyChange.show();
                    } else
                    if(m_algorithm.getDeleteMode())
                    {
                        m_algorithm.deleteNode(node.getIntValue());
                        m_algorithm.setDeleteMode(false);
                    } else
                    if(m_algorithm.editLinkStarted())
                        switch(m_algorithm.getEditLinkStep())
                        {
                        case 1: // '\001'
                            m_algorithm.setStartNode(node.getIntValue());
                            break;

                        case 2: // '\002'
                            m_algorithm.setEndNode(node.getIntValue());
                            if(m_algorithm.needPromptForLinkWeight())
                            {
                                m_frmEditLink = new Frame();
                                m_frmEditLink.setSize(100, 20);
                                m_dlgEditLink = new InputDialog(m_canvas, m_frmEditLink, Messages.getString("DFSGraphCanvasCommon.4"), true, Messages.getString("DFSGraphCanvasCommon.5"), m_algorithm.getCurrentLinkLinkWeight());
                                m_dlgEditLink.show();
                            }
                            break;
                        }
                    else
                        m_dragNodeIndex = node.getIntValue();
                    return;
                }
            }

        }
    }

    public void drawLinks(Graphics g)
    {
        if(vecLinks == null || vecLinks.size() == 0)
            return;
        for(int i = 0; i < vecLinks.size(); i++)
        {
            Link link = (Link)vecLinks.elementAt(i);
            NodeInfo start = link.getStart();
            NodeInfo end = link.getEnd();
            DFSGraphMapsCanvas _tmp = m_canvas;
            DFSGraphMapsCanvas _tmp1 = m_canvas;
            DFSGraphMapsCanvas _tmp2 = m_canvas;
            DFSGraphMapsCanvas _tmp3 = m_canvas;
            Line line = new Line(start.getX() + 20 / 2 + GRAPH_X, start.getY() + 20 / 2 + GRAPH_Y, end.getX() + 20 / 2 + GRAPH_X, end.getY() + 20 / 2 + GRAPH_Y);
            if(!link.getOmniDirectional())
            {
                line.showArrowHead(true);
                line.setDistanceFromStartForArrowHead(line.getLength() - 15);
            }
            line.setColor(link.getLinkColor());
            if(link.getHighlighted())
                line.showAsThick(true);
            if(link.useWeight())
            {
                line.setLabel(Integer.toString(link.getWeight()));
                line.setXLabelOffset(5);
                line.setYLabelOffset(5);
                line.setTextColor(Color.blue);
            }
            line.setDistanceFromStartForLabel(-2);
            line.draw(g);
        }

        for(int i = 0; i < vecLinks.size(); i++)
        {
            Link link = (Link)vecLinks.elementAt(i);
            NodeInfo start = link.getStart();
            NodeInfo end = link.getEnd();
            DFSGraphMapsCanvas _tmp4 = m_canvas;
            DFSGraphMapsCanvas _tmp5 = m_canvas;
            DFSGraphMapsCanvas _tmp6 = m_canvas;
            DFSGraphMapsCanvas _tmp7 = m_canvas;
            Line line = new Line(start.getX() + 20 / 2 + GRAPH_X, start.getY() + 20 / 2 + GRAPH_Y, end.getX() + 20 / 2 + GRAPH_X, end.getY() + 20 / 2 + GRAPH_Y);
            m_canvas.drawLinkAssociatedElements(g, link, line);
        }

    }

    public void drawNodes(Graphics g)
    {
        if(vecNodes == null || vecNodes.size() == 0)
            return;
        for(int i = 0; i < vecNodes.size(); i++)
        {
            GraphMapsNode node = (GraphMapsNode)vecNodes.elementAt(i);
            if(m_dragNodeIndex != -1 && node.getIntValue() == m_dragNodeIndex)
                node.setBackgroundColor(m_algorithm.getHighlightColor());
            node.draw(g);
        }

        for(int i = 0; i < vecNodes.size(); i++)
        {
            GraphMapsNode node = (GraphMapsNode)vecNodes.elementAt(i);
            m_canvas.drawNodeAssociatedElements(g, node);
        }

    }

    protected int GRAPH_X;
    protected int GRAPH_Y;
    protected Vector vecNodes;
    protected Vector vecLinks;
    protected int m_dragNodeIndex;
    protected Frame m_frmEditLink;
    protected InputDialog m_dlgEditLink;
    protected Frame m_frmKeyChange;
    protected InputDialog m_dlgKeyChange;
    protected GraphMapsNode m_KeyChangeNode;
    protected DFSGraphMapsCanvas m_canvas;
    protected DFSGraphMaps m_algorithm;
}
