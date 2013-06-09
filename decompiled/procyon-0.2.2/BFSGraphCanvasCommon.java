import com.cim.AIA.*;
import localization.*;
import java.awt.*;
import java.util.*;
import aia.graph.common.*;

public class BFSGraphCanvasCommon extends GraphCanvasCommon
{
    protected int GRAPH_X;
    protected int GRAPH_Y;
    protected Vector<E> vecNodes;
    protected Vector<E> vecLinks;
    protected int m_dragNodeIndex;
    protected Frame m_frmEditLink;
    protected InputDialog m_dlgEditLink;
    protected Frame m_frmKeyChange;
    protected InputDialog m_dlgKeyChange;
    protected GraphMapsNode m_KeyChangeNode;
    protected GraphMapsCanvas m_canvas;
    protected GraphMaps m_algorithm;
    
    BFSGraphCanvasCommon(final GraphMapsCanvas p_canvas, final GraphMaps p_algorithm) {
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
    
    public int getGraphX() {
        return this.GRAPH_X;
    }
    
    public int getGraphY() {
        return this.GRAPH_Y;
    }
    
    public Vector<E> getNodes() {
        return this.vecNodes;
    }
    
    public Vector<E> getLinks() {
        return this.vecLinks;
    }
    
    public void setNodes(final Vector<E> p_nodes, final int graph_x) {
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
    
    public void setLinks(final Vector<E> p_links) {
        this.vecLinks = p_links;
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
            final int x = node.getX();
            final GraphMapsCanvas canvas = this.m_canvas;
            if (x + 20 > max_graph_width) {
                final int x2 = node.getX();
                final GraphMapsCanvas canvas2 = this.m_canvas;
                max_graph_width = x2 + 20;
            }
            final int y = node.getY();
            final GraphMapsCanvas canvas3 = this.m_canvas;
            if (y + 20 > max_graph_height) {
                final int y2 = node.getY();
                final GraphMapsCanvas canvas4 = this.m_canvas;
                max_graph_height = y2 + 20;
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
    
    public void processDialogEvent(final String sourceDialog, final String value) {
        if (this.m_algorithm != null && !this.m_algorithm.getIsRunning() && value != null) {
            if (sourceDialog.equals(Messages.getString("BFSGraphCanvasCommon.0"))) {
                this.m_algorithm.keyChange(this.m_KeyChangeNode.getIntValue(), Integer.parseInt(value));
                this.m_algorithm.setKeyChangeMode(false);
            }
            else if (sourceDialog.equals(Messages.getString("BFSGraphCanvasCommon.1"))) {
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
}
