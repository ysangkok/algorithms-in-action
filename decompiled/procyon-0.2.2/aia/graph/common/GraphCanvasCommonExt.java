package aia.graph.common;

import localization.*;
import java.awt.*;
import com.cim.AIA.*;
import java.util.*;

public class GraphCanvasCommonExt implements GraphDialogEventHandler
{
    protected Vector<ElementArray> vecAdjacencyMatrix;
    protected int MATRIX_X;
    protected int MATRIX_Y;
    protected int MATRIX_WIDTH;
    protected int MATRIX_HEIGHT;
    protected ElementArray earrAdjacencyStructure;
    protected int STRUCTURE_X;
    protected int STRUCTURE_Y;
    protected int STRUCTURE_HEIGHT;
    protected int STRUCTURE_WIDTH;
    protected int LINK_LENGTH;
    protected Frame m_frmMatrixEdit;
    protected InputDialog m_dlgMatrixEdit;
    protected GraphMapsNode m_MatrixEditNode;
    protected GraphMapsCanvasExt m_canvas;
    protected GraphMaps m_algorithm;
    
    public GraphCanvasCommonExt() {
        super();
        this.vecAdjacencyMatrix = null;
        this.MATRIX_X = 50;
        this.MATRIX_Y = 130;
        this.MATRIX_WIDTH = 0;
        this.MATRIX_HEIGHT = 0;
        this.earrAdjacencyStructure = null;
        this.STRUCTURE_X = 30;
        this.STRUCTURE_Y = 130;
        this.STRUCTURE_HEIGHT = 0;
        this.STRUCTURE_WIDTH = 0;
        this.LINK_LENGTH = 20;
        this.m_frmMatrixEdit = null;
        this.m_dlgMatrixEdit = null;
        this.m_MatrixEditNode = null;
    }
    
    public GraphCanvasCommonExt(final GraphMapsCanvasExt p_canvas, final GraphMaps p_algorithm) {
        super();
        this.vecAdjacencyMatrix = null;
        this.MATRIX_X = 50;
        this.MATRIX_Y = 130;
        this.MATRIX_WIDTH = 0;
        this.MATRIX_HEIGHT = 0;
        this.earrAdjacencyStructure = null;
        this.STRUCTURE_X = 30;
        this.STRUCTURE_Y = 130;
        this.STRUCTURE_HEIGHT = 0;
        this.STRUCTURE_WIDTH = 0;
        this.LINK_LENGTH = 20;
        this.m_frmMatrixEdit = null;
        this.m_dlgMatrixEdit = null;
        this.m_MatrixEditNode = null;
        this.m_canvas = p_canvas;
        this.m_algorithm = p_algorithm;
    }
    
    public void adjustCanvasSize() {
        int canvas_width = 440;
        int canvas_height = 360;
        if (this.vecAdjacencyMatrix != null && this.m_algorithm.getMatrixShowing()) {
            if (this.MATRIX_X + this.MATRIX_WIDTH + 20 > canvas_width) {
                canvas_width = this.MATRIX_X + this.MATRIX_WIDTH + 20;
            }
            if (this.MATRIX_Y + this.MATRIX_HEIGHT + 20 > canvas_height) {
                canvas_height = this.MATRIX_Y + this.MATRIX_HEIGHT + 20;
            }
        }
        if (this.earrAdjacencyStructure != null && this.m_algorithm.getStructureShowing()) {
            if (this.STRUCTURE_X + this.STRUCTURE_WIDTH + 20 > canvas_width) {
                canvas_width = this.STRUCTURE_X + this.STRUCTURE_WIDTH + 20;
            }
            if (this.STRUCTURE_Y + this.STRUCTURE_HEIGHT + 20 > canvas_height) {
                canvas_height = this.STRUCTURE_Y + this.STRUCTURE_HEIGHT + 20;
            }
        }
        this.m_canvas.setSize(canvas_width, canvas_height);
    }
    
    private void calculateStructureHeight() {
        int max_height = 0;
        int current_list_height = 0;
        this.STRUCTURE_HEIGHT = 0;
        if (this.earrAdjacencyStructure == null) {
            return;
        }
        for (int i = 0; i < this.m_algorithm.getNumberOfNodes(); ++i) {
            GraphNode root = this.m_algorithm.getAdjacentNode(i);
            current_list_height = 20;
            while (root != null) {
                current_list_height = current_list_height + (20 + this.LINK_LENGTH);
                if (root.getKey() == -1) {
                    break;
                }
                root = root.getNext();
            }
            if (current_list_height > max_height) {
                max_height = current_list_height;
            }
        }
        this.STRUCTURE_HEIGHT = max_height;
    }
    
    public void drawAdjacencyMatrix(final Graphics g) {
        if (this.vecAdjacencyMatrix == null) {
            return;
        }
        if (!this.m_algorithm.getMatrixShowing()) {
            return;
        }
        final FontMetrics fm = g.getFontMetrics();
        g.setColor(Color.blue);
        g.drawString(Messages.getString("GraphCanvasCommonExt.3"), this.MATRIX_X, this.MATRIX_Y - fm.getHeight());
        g.drawString(Messages.getString("GraphCanvasCommonExt.4"), this.MATRIX_X - fm.stringWidth(Messages.getString("GraphCanvasCommonExt.5")), this.MATRIX_Y + 20 - fm.getMaxDescent());
        g.drawString(Messages.getString("GraphCanvasCommonExt.6"), this.MATRIX_X, this.MATRIX_Y - fm.getHeight() * 2);
        g.setColor(this.m_canvas.getTextColor());
        for (int i = 0; i < this.vecAdjacencyMatrix.size(); ++i) {
            final String label = Integer.toString(i);
            g.drawString(label, this.MATRIX_X + 20 * i + (20 - fm.stringWidth(label)) / 2, this.MATRIX_Y - fm.getMaxDescent());
            g.drawString(label, this.MATRIX_X - fm.stringWidth(label), this.MATRIX_Y + 20 * (i + 1) - fm.getMaxDescent());
            final ElementArray elementArray = (ElementArray)this.vecAdjacencyMatrix.elementAt(i);
            elementArray.draw(g);
            this.m_canvas.drawMatrixAssociatedElements(g, elementArray, i);
        }
    }
    
    public void drawAdjacencyStructure(final Graphics g) {
        if (this.earrAdjacencyStructure == null) {
            return;
        }
        if (!this.m_algorithm.getStructureShowing()) {
            return;
        }
        g.setColor(Color.blue);
        final FontMetrics fm = g.getFontMetrics();
        g.drawString(Messages.getString("GraphCanvasCommonExt.7"), this.earrAdjacencyStructure.getLocation().x, this.STRUCTURE_Y - fm.getHeight() * 2);
        g.setColor(this.m_canvas.getTextColor());
        this.earrAdjacencyStructure.draw(g);
        for (int i = 0; i < this.m_algorithm.getNumberOfNodes(); ++i) {
            final GraphMapsNode rootNode = (GraphMapsNode)this.earrAdjacencyStructure.getElement(i);
            GraphNode root = this.m_algorithm.getAdjacentNode(i);
            int level = 1;
            while (root != null) {
                final Line line = new Line(rootNode.getX() + 10, rootNode.getY() + (20 + this.LINK_LENGTH) * (level - 1) + 20, rootNode.getX() + 10, rootNode.getY() + (20 + this.LINK_LENGTH) * level);
                line.showArrowHead(true);
                line.setDistanceFromStartForArrowHead(-3);
                line.draw(g);
                final GraphMapsNode graphMapsNode;
                final GraphMapsNode node = graphMapsNode = new GraphMapsNode(new Integer(root.getKey()), root.getKey(), 3);
                graphMapsNode.setPosition(new Point(rootNode.getX(), rootNode.getY() + (20 + this.LINK_LENGTH) * level));
                this.m_canvas.setStructureNodeAppearance(node, rootNode);
                this.m_canvas.drawStructureAssociatedElements(g, node, rootNode);
                node.draw(g);
                ++level;
                if (root.getKey() == -1) {
                    break;
                }
                root = root.getNext();
            }
        }
    }
    
    public void editMatrix(final GraphMapsNode p_Node) {
        if (this.m_algorithm != null && !this.m_algorithm.getIsRunning()) {
            this.m_MatrixEditNode = p_Node;
            this.m_frmMatrixEdit = new Frame();
            this.m_frmMatrixEdit.setSize(100, 70);
            this.m_frmMatrixEdit.setLocation(0, 0);
            this.m_dlgMatrixEdit = new InputDialog(this.m_canvas, this.m_frmMatrixEdit, Messages.getString("GraphCanvasCommonExt.1"), true, Messages.getString("GraphCanvasCommonExt.2"), p_Node.getIntValue());
            this.m_dlgMatrixEdit.show();
        }
    }
    
    public Vector<ElementArray> getMatrix() {
        return this.vecAdjacencyMatrix;
    }
    
    public int getMatrixHeight() {
        return this.MATRIX_HEIGHT;
    }
    
    public int getMatrixWidth() {
        return this.MATRIX_WIDTH;
    }
    
    public int getMatrixX() {
        return this.MATRIX_X;
    }
    
    public int getMatrixY() {
        return this.MATRIX_Y;
    }
    
    public ElementArray getStructure() {
        return this.earrAdjacencyStructure;
    }
    
    public int getStructureHeight() {
        return this.STRUCTURE_HEIGHT;
    }
    
    public int getStructureWeight() {
        return this.STRUCTURE_WIDTH;
    }
    
    public int getStructureX() {
        return this.STRUCTURE_X;
    }
    
    public int getStructureY() {
        return this.STRUCTURE_Y;
    }
    
    public void processDialogEvent(final String sourceDialog, final String value) {
        if (this.m_algorithm != null && !this.m_algorithm.getIsRunning() && value != null && sourceDialog.equals(Messages.getString("GraphCanvasCommonExt.0"))) {
            this.m_algorithm.editLink(this.m_MatrixEditNode, Integer.parseInt(value));
        }
        if (this.m_algorithm != null) {
            this.m_canvas.processRepaintEvent(new RepaintEvent(this.m_canvas, this.m_algorithm));
        }
    }
    
    public void setMatrix(final Vector<ElementArray> p_matrix, final int matrix_x, final int matrix_y) {
        this.MATRIX_X = matrix_x;
        this.MATRIX_Y = matrix_y;
        this.vecAdjacencyMatrix = p_matrix;
        if (this.vecAdjacencyMatrix != null) {
            for (int i = 0; i < this.vecAdjacencyMatrix.size(); ++i) {
                final ElementArray elementArray = (ElementArray)this.vecAdjacencyMatrix.elementAt(i);
                this.MATRIX_WIDTH = elementArray.getWidth();
                elementArray.setLocation(this.MATRIX_X, this.MATRIX_Y + elementArray.getHeight() * i);
                if (this.m_algorithm.getMatrixShowing()) {
                    this.m_canvas.addSelectable(elementArray);
                }
            }
            this.MATRIX_HEIGHT = this.vecAdjacencyMatrix.size() * 20;
        }
    }
    
    public void setStructure(final ElementArray p_array, final int structure_x, final int structure_y, final int link_len) {
        if (this.m_algorithm.getMatrixShowing()) {
            this.STRUCTURE_X = structure_x;
            if (this.STRUCTURE_X + this.MATRIX_WIDTH < 120) {
                this.STRUCTURE_X = 120 - this.MATRIX_WIDTH;
            }
        }
        else {
            this.STRUCTURE_X = this.MATRIX_X;
        }
        this.STRUCTURE_Y = structure_y;
        this.LINK_LENGTH = link_len;
        this.earrAdjacencyStructure = p_array;
        if (this.earrAdjacencyStructure != null) {
            this.STRUCTURE_WIDTH = this.earrAdjacencyStructure.getWidth();
            if (this.vecAdjacencyMatrix != null) {
                if (this.m_algorithm.getMatrixShowing()) {
                    this.earrAdjacencyStructure.setLocation(this.MATRIX_WIDTH + this.MATRIX_X + this.STRUCTURE_X, this.STRUCTURE_Y);
                }
                else {
                    this.earrAdjacencyStructure.setLocation(this.STRUCTURE_X, this.STRUCTURE_Y);
                }
            }
            this.STRUCTURE_X = this.earrAdjacencyStructure.getLocation().x;
            this.calculateStructureHeight();
        }
    }
}
