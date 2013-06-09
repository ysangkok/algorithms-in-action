import localization.*;
import java.awt.*;
import com.cim.AIA.*;
import java.util.*;
import aia.graph.common.*;

public class MSTKGraphCanvasCommonExt extends GraphMapsCanvasExt
{
    protected Vector<E> vecAdjacencyMatrix;
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
    protected MSTKGraphMapsCanvasExt m_canvas;
    protected MSTKGraphMaps m_algorithm;
    
    MSTKGraphCanvasCommonExt(final MSTKGraphMapsCanvasExt p_canvas, final MSTKGraphMaps p_algorithm) {
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
    
    public void setMatrix(final Vector<E> p_matrix, final int matrix_x, final int matrix_y) {
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
            final int size = this.vecAdjacencyMatrix.size();
            final MSTKGraphMapsCanvasExt canvas = this.m_canvas;
            this.MATRIX_HEIGHT = size * 20;
        }
    }
    
    public Vector<E> getMatrix() {
        return this.vecAdjacencyMatrix;
    }
    
    public int getMatrixWidth() {
        return this.MATRIX_WIDTH;
    }
    
    public int getMatrixHeight() {
        return this.MATRIX_HEIGHT;
    }
    
    public int getMatrixX() {
        return this.MATRIX_X;
    }
    
    public int getMatrixY() {
        return this.MATRIX_Y;
    }
    
    public int getStructureX() {
        return this.STRUCTURE_X;
    }
    
    public int getStructureY() {
        return this.STRUCTURE_Y;
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
    
    public int getStructureHeight() {
        return this.STRUCTURE_HEIGHT;
    }
    
    public int getStructureWeight() {
        return this.STRUCTURE_WIDTH;
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
            final MSTKGraphMapsCanvasExt canvas = this.m_canvas;
            current_list_height = 20;
            while (root != null) {
                final int n = current_list_height;
                final MSTKGraphMapsCanvasExt canvas2 = this.m_canvas;
                current_list_height = n + (20 + this.LINK_LENGTH);
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
    
    public ElementArray getStructure() {
        return this.earrAdjacencyStructure;
    }
    
    public void adjustCanvasSize() {
        int canvas_width = 440;
        int canvas_height = 360;
        final int max_graph_width = 0;
        final int max_graph_height = 0;
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
    
    public void processDialogEvent(final String sourceDialog, final String value) {
        if (this.m_algorithm != null && !this.m_algorithm.getIsRunning() && value != null && sourceDialog.equals(Messages.getString("MSTKGraphCanvasCommonExt.0"))) {
            this.m_algorithm.editLink(this.m_MatrixEditNode, Integer.parseInt(value));
        }
        if (this.m_algorithm != null) {
            this.m_canvas.processRepaintEvent(new RepaintEvent(this.m_canvas, this.m_algorithm));
        }
    }
    
    public void editMatrix(final GraphMapsNode p_Node) {
        if (this.m_algorithm != null && !this.m_algorithm.getIsRunning()) {
            this.m_MatrixEditNode = p_Node;
            this.m_frmMatrixEdit = new Frame();
            this.m_frmMatrixEdit.setSize(100, 70);
            this.m_frmMatrixEdit.setLocation(0, 0);
            this.m_dlgMatrixEdit = new InputDialog(this.m_canvas, this.m_frmMatrixEdit, Messages.getString("MSTKGraphCanvasCommonExt.1"), true, Messages.getString("MSTKGraphCanvasCommonExt.2"), p_Node.getIntValue());
            this.m_dlgMatrixEdit.show();
        }
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
        g.drawString(Messages.getString("MSTKGraphCanvasCommonExt.3"), this.MATRIX_X, this.MATRIX_Y - fm.getHeight());
        final String string = Messages.getString("MSTKGraphCanvasCommonExt.4");
        final int n = this.MATRIX_X - fm.stringWidth(Messages.getString("MSTKGraphCanvasCommonExt.5"));
        final int matrix_Y = this.MATRIX_Y;
        final MSTKGraphMapsCanvasExt canvas = this.m_canvas;
        g.drawString(string, n, matrix_Y + 20 - fm.getMaxDescent());
        g.drawString(Messages.getString("MSTKGraphCanvasCommonExt.6"), this.MATRIX_X, this.MATRIX_Y - fm.getHeight() * 2);
        g.setColor(this.m_canvas.getTextColor());
        for (int i = 0; i < this.vecAdjacencyMatrix.size(); ++i) {
            final String string2;
            final String label = string2 = Integer.toString(i);
            final int matrix_X = this.MATRIX_X;
            final MSTKGraphMapsCanvasExt canvas2 = this.m_canvas;
            final int n2 = matrix_X + 20 * i;
            final MSTKGraphMapsCanvasExt canvas3 = this.m_canvas;
            g.drawString(string2, n2 + (20 - fm.stringWidth(label)) / 2, this.MATRIX_Y - fm.getMaxDescent());
            final String s = label;
            final int n3 = this.MATRIX_X - fm.stringWidth(label);
            final int matrix_Y2 = this.MATRIX_Y;
            final MSTKGraphMapsCanvasExt canvas4 = this.m_canvas;
            g.drawString(s, n3, matrix_Y2 + 20 * (i + 1) - fm.getMaxDescent());
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
        g.drawString(Messages.getString("MSTKGraphCanvasCommonExt.7"), this.earrAdjacencyStructure.getLocation().x, this.STRUCTURE_Y - fm.getHeight() * 2);
        g.setColor(this.m_canvas.getTextColor());
        this.earrAdjacencyStructure.draw(g);
        for (int i = 0; i < this.m_algorithm.getNumberOfNodes(); ++i) {
            final GraphMapsNode rootNode = (GraphMapsNode)this.earrAdjacencyStructure.getElement(i);
            GraphNode root = this.m_algorithm.getAdjacentNode(i);
            int level = 1;
            while (root != null) {
                final Line line2 = new(com.cim.AIA.Line.class);
                final int x = rootNode.getX();
                final MSTKGraphMapsCanvasExt canvas = this.m_canvas;
                final int x1 = x + 20 / 2;
                final int y = rootNode.getY();
                final MSTKGraphMapsCanvasExt canvas2 = this.m_canvas;
                final int n = y + (20 + this.LINK_LENGTH) * (level - 1);
                final MSTKGraphMapsCanvasExt canvas3 = this.m_canvas;
                final int y1 = n + 20;
                int x2 = rootNode.getX();
                final MSTKGraphMapsCanvasExt canvas4 = this.m_canvas;
                x2 = x2 + 20 / 2;
                final int y2 = rootNode.getY();
                final MSTKGraphMapsCanvasExt canvas5 = this.m_canvas;
                new Line(x1, y1, x2, y2 + (20 + this.LINK_LENGTH) * level);
                final Line line = line2;
                line.showArrowHead(true);
                line.setDistanceFromStartForArrowHead(-3);
                line.draw(g);
                final GraphMapsNode graphMapsNode;
                final GraphMapsNode node = graphMapsNode = new GraphMapsNode(new Integer(root.getKey()), root.getKey(), 3);
                final Point position = new(java.awt.Point.class);
                final int x3 = rootNode.getX();
                final int y3 = rootNode.getY();
                final MSTKGraphMapsCanvasExt canvas6 = this.m_canvas;
                new Point(x3, y3 + (20 + this.LINK_LENGTH) * level);
                graphMapsNode.setPosition(position);
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
}
