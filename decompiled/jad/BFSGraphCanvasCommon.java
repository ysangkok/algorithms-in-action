// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BFSGraphCanvasCommon.java

import aia.graph.common.*;
import com.cim.AIA.RepaintEvent;
import java.awt.Frame;
import java.awt.Point;
import java.util.Vector;
import localization.Messages;

public class BFSGraphCanvasCommon extends GraphCanvasCommon
{

    BFSGraphCanvasCommon(GraphMapsCanvas p_canvas, GraphMaps p_algorithm)
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
            GraphMapsCanvas _tmp = m_canvas;
            if(node.getX() + 20 > max_graph_width)
            {
                GraphMapsCanvas _tmp1 = m_canvas;
                max_graph_width = node.getX() + 20;
            }
            GraphMapsCanvas _tmp2 = m_canvas;
            if(node.getY() + 20 > max_graph_height)
            {
                GraphMapsCanvas _tmp3 = m_canvas;
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
            if(sourceDialog.equals(Messages.getString("BFSGraphCanvasCommon.0")))
            {
                m_algorithm.keyChange(m_KeyChangeNode.getIntValue(), Integer.parseInt(value));
                m_algorithm.setKeyChangeMode(false);
            } else
            if(sourceDialog.equals(Messages.getString("BFSGraphCanvasCommon.1")))
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
    protected GraphMapsCanvas m_canvas;
    protected GraphMaps m_algorithm;
}
