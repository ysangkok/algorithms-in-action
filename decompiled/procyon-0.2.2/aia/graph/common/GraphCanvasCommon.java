package aia.graph.common;

import localization.*;
import com.cim.AIA.*;
import java.awt.*;
import java.util.*;

public class GraphCanvasCommon
{
    protected int GRAPH_X;
    protected int GRAPH_Y;
    protected Vector<GraphMapsNode> vecNodes;
    protected Vector<Link> vecLinks;
    protected int m_dragNodeIndex;
    protected Frame m_frmEditLink;
    protected InputDialog m_dlgEditLink;
    protected Frame m_frmKeyChange;
    protected InputDialog m_dlgKeyChange;
    protected GraphMapsNode m_KeyChangeNode;
    protected GraphMapsCanvas m_canvas;
    protected GraphMaps m_algorithm;
    
    GraphCanvasCommon(final GraphMapsCanvas p_canvas, final GraphMaps p_algorithm) {
        super();
        this.GRAPH_X = 30;
        this.GRAPH_Y = 30;
        this.vecNodes = null;
        this.vecLinks = null;
        this.m_dragNodeIndex = -1;
        this.m_frmEditLink = null;
        this.m_dlgEditLink = null;
        this.m_frmKeyChange = null;
        this.m_dlgKeyChange = null;
        this.m_KeyChangeNode = null;
        this.m_canvas = p_canvas;
        this.m_algorithm = p_algorithm;
    }
    
    public GraphCanvasCommon() {
        super();
        this.GRAPH_X = 30;
        this.GRAPH_Y = 30;
        this.vecNodes = null;
        this.vecLinks = null;
        this.m_dragNodeIndex = -1;
        this.m_frmEditLink = null;
        this.m_dlgEditLink = null;
        this.m_frmKeyChange = null;
        this.m_dlgKeyChange = null;
        this.m_KeyChangeNode = null;
    }
    
    public void adjustCanvasSize() {
        int canvas_width = 440;
        int canvas_height = 360;
        int max_graph_width = 0;
        int max_graph_height = 0;
        this.m_canvas.setSize(canvas_width, canvas_height);
        if (this.vecNodes == null) {
            return;
        }
        for (int i = 0; i < this.vecNodes.size(); ++i) {
            final GraphMapsNode node = (GraphMapsNode)this.vecNodes.elementAt(i);
            if (node.getX() + 20 > max_graph_width) {
                max_graph_width = node.getX() + 20;
            }
            if (node.getY() + 20 > max_graph_height) {
                max_graph_height = node.getY() + 20;
            }
        }
        if (max_graph_width > canvas_width) {
            canvas_width = max_graph_width;
        }
        if (max_graph_height > canvas_height) {
            canvas_height = max_graph_height;
        }
        this.m_canvas.setSize(canvas_width, canvas_height);
    }
    
    public void drawLinks(final Graphics g) {
        if (this.vecLinks == null || this.vecLinks.size() == 0) {
            return;
        }
        Link link;
        NodeInfo start;
        NodeInfo end;
        Line line;
        for (int i = 0; i < this.vecLinks.size(); ++i) {
            link = (Link)this.vecLinks.elementAt(i);
            start = link.getStart();
            end = link.getEnd();
            line = new Line(start.getX() + 10 + this.GRAPH_X, start.getY() + 10 + this.GRAPH_Y, end.getX() + 10 + this.GRAPH_X, end.getY() + 10 + this.GRAPH_Y);
            if (!link.getOmniDirectional()) {
                line.showArrowHead(true);
                line.setDistanceFromStartForArrowHead(line.getLength() - 15);
            }
            line.setColor(link.getLinkColor());
            if (link.getHighlighted()) {
                line.showAsThick(true);
            }
            if (link.useWeight()) {
                line.setLabel(Integer.toString(link.getWeight()));
                line.setXLabelOffset(5);
                line.setYLabelOffset(5);
                line.setTextColor(Color.blue);
            }
            line.setDistanceFromStartForLabel(-2);
            line.draw(g);
        }
        for (int i = 0; i < this.vecLinks.size(); ++i) {
            link = (Link)this.vecLinks.elementAt(i);
            start = link.getStart();
            end = link.getEnd();
            line = new Line(start.getX() + 10 + this.GRAPH_X, start.getY() + 10 + this.GRAPH_Y, end.getX() + 10 + this.GRAPH_X, end.getY() + 10 + this.GRAPH_Y);
            this.m_canvas.drawLinkAssociatedElements(g, link, line);
        }
    }
    
    public void drawNodes(final Graphics g) {
        if (this.vecNodes == null || this.vecNodes.size() == 0) {
            return;
        }
        GraphMapsNode node;
        for (int i = 0; i < this.vecNodes.size(); ++i) {
            node = (GraphMapsNode)this.vecNodes.elementAt(i);
            if (this.m_dragNodeIndex != -1 && node.getIntValue() == this.m_dragNodeIndex) {
                node.setBackgroundColor(this.m_algorithm.getHighlightColor());
            }
            node.draw(g);
        }
        for (int i = 0; i < this.vecNodes.size(); ++i) {
            node = (GraphMapsNode)this.vecNodes.elementAt(i);
            this.m_canvas.drawNodeAssociatedElements(g, node);
        }
    }
    
    public int getGraphX() {
        return this.GRAPH_X;
    }
    
    public int getGraphY() {
        return this.GRAPH_Y;
    }
    
    public Vector<Link> getLinks() {
        return this.vecLinks;
    }
    
    public Vector<?> getNodes() {
        return this.vecNodes;
    }
    
    public void mouseMoved(final int px, final int py) {
        if (this.m_dragNodeIndex == -1) {
            return;
        }
        final int x = px - 10;
        final int y = py - 10;
        if (this.m_algorithm != null && !this.m_algorithm.getIsRunning()) {
            this.m_algorithm.setNodePosition(this.m_dragNodeIndex, x - this.GRAPH_X, y - this.GRAPH_Y);
        }
    }
    
    public void mousePressed(final int px, final int py) {
        if (this.m_algorithm != null && !this.m_algorithm.getIsRunning() && this.m_algorithm.getInsertionMode()) {
            this.m_algorithm.insertNewNode(px - this.GRAPH_X, py - this.GRAPH_Y);
            this.m_algorithm.setInsertionMode(false);
        }
        else if (this.m_algorithm != null && this.vecNodes != null) {
            for (int i = 0; i < this.vecNodes.size(); ++i) {
                final GraphMapsNode node = (GraphMapsNode)this.vecNodes.elementAt(i);
                if (px > node.getX() && px < node.getX() + node.getWidth() && py > node.getY() && py < node.getY() + node.getHeight()) {
                    if (this.m_algorithm.getKeyChangeMode()) {
                        this.m_KeyChangeNode = node;
                        this.m_frmKeyChange = new Frame();
                        this.m_frmKeyChange.setSize(100, 20);
                        this.m_dlgKeyChange = new InputDialog(this.m_canvas, this.m_frmKeyChange, Messages.getString("GraphCanvasCommon.2"), true, Messages.getString("GraphCanvasCommon.3"), node.getIntValue());
                        this.m_dlgKeyChange.show();
                    }
                    else if (this.m_algorithm.getDeleteMode()) {
                        this.m_algorithm.deleteNode(node.getIntValue());
                        this.m_algorithm.setDeleteMode(false);
                    }
                    else if (this.m_algorithm.editLinkStarted()) {
                        switch (this.m_algorithm.getEditLinkStep()) {
                            case 1: {
                                this.m_algorithm.setStartNode(node.getIntValue());
                                break;
                            }
                            case 2: {
                                this.m_algorithm.setEndNode(node.getIntValue());
                                if (this.m_algorithm.needPromptForLinkWeight()) {
                                    this.m_frmEditLink = new Frame();
                                    this.m_frmEditLink.setSize(100, 20);
                                    this.m_dlgEditLink = new InputDialog(this.m_canvas, this.m_frmEditLink, Messages.getString("GraphCanvasCommon.4"), true, Messages.getString("GraphCanvasCommon.5"), this.m_algorithm.getCurrentLinkLinkWeight());
                                    this.m_dlgEditLink.show();
                                    break;
                                }
                                break;
                            }
                        }
                    }
                    else {
                        this.m_dragNodeIndex = node.getIntValue();
                    }
                    return;
                }
            }
        }
    }
    
    public void mouseReleased() {
        if (this.m_dragNodeIndex != -1) {
            this.m_dragNodeIndex = -1;
            this.m_canvas.processRepaintEvent(new RepaintEvent(this.m_canvas, this.m_algorithm));
        }
    }
    
    public void processDialogEvent(final String sourceDialog, final String value) {
        if (this.m_algorithm != null && !this.m_algorithm.getIsRunning() && value != null) {
            if (sourceDialog.equals(Messages.getString("GraphCanvasCommon.0"))) {
                this.m_algorithm.keyChange(this.m_KeyChangeNode.getIntValue(), Integer.parseInt(value));
                this.m_algorithm.setKeyChangeMode(false);
            }
            else if (sourceDialog.equals(Messages.getString("GraphCanvasCommon.1"))) {
                this.m_algorithm.setLinkWeight(Integer.parseInt(value));
                this.m_algorithm.endEditLink();
            }
        }
        if (this.m_algorithm != null) {
            if (this.m_algorithm.getKeyChangeMode()) {
                this.m_algorithm.setKeyChangeMode(false);
            }
            if (this.m_algorithm.editLinkStarted()) {
                this.m_algorithm.endEditLink();
            }
            this.m_canvas.processRepaintEvent(new RepaintEvent(this.m_canvas, this.m_algorithm));
        }
    }
    
    public void setLinks(final Vector<Link> p_links) {
        this.vecLinks = p_links;
    }
    
    public void setNodes(final Vector<GraphMapsNode> p_nodes, final int graph_x) {
        this.vecNodes = p_nodes;
        this.GRAPH_X = graph_x;
        if (this.vecNodes != null) {
            for (int i = 0; i < this.vecNodes.size(); ++i) {
                final GraphMapsNode graphMapsNode;
                final GraphMapsNode node = graphMapsNode = (GraphMapsNode)this.vecNodes.elementAt(i);
                graphMapsNode.setPosition(new Point(node.getX() + this.GRAPH_X, node.getY() + this.GRAPH_Y));
                this.m_canvas.addSelectable(node);
            }
        }
    }
}
