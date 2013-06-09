// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MSTKGraphCanvasCommonExt.java

import aia.graph.common.*;
import com.cim.AIA.*;
import java.awt.*;
import java.util.Vector;
import localization.Messages;

public class MSTKGraphCanvasCommonExt extends GraphMapsCanvasExt
{

    MSTKGraphCanvasCommonExt(MSTKGraphMapsCanvasExt p_canvas, MSTKGraphMaps p_algorithm)
    {
        vecAdjacencyMatrix = null;
        MATRIX_X = 50;
        MATRIX_Y = 130;
        MATRIX_WIDTH = 0;
        MATRIX_HEIGHT = 0;
        earrAdjacencyStructure = null;
        STRUCTURE_X = 30;
        STRUCTURE_Y = 130;
        STRUCTURE_HEIGHT = 0;
        STRUCTURE_WIDTH = 0;
        LINK_LENGTH = 20;
        m_frmMatrixEdit = null;
        m_dlgMatrixEdit = null;
        m_MatrixEditNode = null;
        m_canvas = p_canvas;
        m_algorithm = p_algorithm;
    }

    public void setMatrix(Vector p_matrix, int matrix_x, int matrix_y)
    {
        MATRIX_X = matrix_x;
        MATRIX_Y = matrix_y;
        vecAdjacencyMatrix = p_matrix;
        if(vecAdjacencyMatrix != null)
        {
            for(int i = 0; i < vecAdjacencyMatrix.size(); i++)
            {
                ElementArray elementArray = (ElementArray)vecAdjacencyMatrix.elementAt(i);
                MATRIX_WIDTH = elementArray.getWidth();
                elementArray.setLocation(MATRIX_X, MATRIX_Y + elementArray.getHeight() * i);
                if(m_algorithm.getMatrixShowing())
                    m_canvas.addSelectable(elementArray);
            }

            MSTKGraphMapsCanvasExt _tmp = m_canvas;
            MATRIX_HEIGHT = vecAdjacencyMatrix.size() * 20;
        }
    }

    public Vector getMatrix()
    {
        return vecAdjacencyMatrix;
    }

    public int getMatrixWidth()
    {
        return MATRIX_WIDTH;
    }

    public int getMatrixHeight()
    {
        return MATRIX_HEIGHT;
    }

    public int getMatrixX()
    {
        return MATRIX_X;
    }

    public int getMatrixY()
    {
        return MATRIX_Y;
    }

    public int getStructureX()
    {
        return STRUCTURE_X;
    }

    public int getStructureY()
    {
        return STRUCTURE_Y;
    }

    public void setStructure(ElementArray p_array, int structure_x, int structure_y, int link_len)
    {
        if(m_algorithm.getMatrixShowing())
        {
            STRUCTURE_X = structure_x;
            if(STRUCTURE_X + MATRIX_WIDTH < 120)
                STRUCTURE_X = 120 - MATRIX_WIDTH;
        } else
        {
            STRUCTURE_X = MATRIX_X;
        }
        STRUCTURE_Y = structure_y;
        LINK_LENGTH = link_len;
        earrAdjacencyStructure = p_array;
        if(earrAdjacencyStructure != null)
        {
            STRUCTURE_WIDTH = earrAdjacencyStructure.getWidth();
            if(vecAdjacencyMatrix != null)
                if(m_algorithm.getMatrixShowing())
                    earrAdjacencyStructure.setLocation(MATRIX_WIDTH + MATRIX_X + STRUCTURE_X, STRUCTURE_Y);
                else
                    earrAdjacencyStructure.setLocation(STRUCTURE_X, STRUCTURE_Y);
            STRUCTURE_X = earrAdjacencyStructure.getLocation().x;
            calculateStructureHeight();
        }
    }

    public int getStructureHeight()
    {
        return STRUCTURE_HEIGHT;
    }

    public int getStructureWeight()
    {
        return STRUCTURE_WIDTH;
    }

    private void calculateStructureHeight()
    {
        int max_height = 0;
        int current_list_height = 0;
        STRUCTURE_HEIGHT = 0;
        if(earrAdjacencyStructure == null)
            return;
        for(int i = 0; i < m_algorithm.getNumberOfNodes(); i++)
        {
            GraphNode root = m_algorithm.getAdjacentNode(i);
            MSTKGraphMapsCanvasExt _tmp = m_canvas;
            current_list_height = 20;
            do
            {
                if(root == null)
                    break;
                MSTKGraphMapsCanvasExt _tmp1 = m_canvas;
                current_list_height += 20 + LINK_LENGTH;
                if(root.getKey() == -1)
                    break;
                root = root.getNext();
            } while(true);
            if(current_list_height > max_height)
                max_height = current_list_height;
        }

        STRUCTURE_HEIGHT = max_height;
    }

    public ElementArray getStructure()
    {
        return earrAdjacencyStructure;
    }

    public void adjustCanvasSize()
    {
        int canvas_width = 440;
        int canvas_height = 360;
        int max_graph_width = 0;
        int max_graph_height = 0;
        if(vecAdjacencyMatrix != null && m_algorithm.getMatrixShowing())
        {
            if(MATRIX_X + MATRIX_WIDTH + 20 > canvas_width)
                canvas_width = MATRIX_X + MATRIX_WIDTH + 20;
            if(MATRIX_Y + MATRIX_HEIGHT + 20 > canvas_height)
                canvas_height = MATRIX_Y + MATRIX_HEIGHT + 20;
        }
        if(earrAdjacencyStructure != null && m_algorithm.getStructureShowing())
        {
            if(STRUCTURE_X + STRUCTURE_WIDTH + 20 > canvas_width)
                canvas_width = STRUCTURE_X + STRUCTURE_WIDTH + 20;
            if(STRUCTURE_Y + STRUCTURE_HEIGHT + 20 > canvas_height)
                canvas_height = STRUCTURE_Y + STRUCTURE_HEIGHT + 20;
        }
        m_canvas.setSize(canvas_width, canvas_height);
    }

    public void processDialogEvent(String sourceDialog, String value)
    {
        if(m_algorithm != null && !m_algorithm.getIsRunning() && value != null && sourceDialog.equals(Messages.getString("MSTKGraphCanvasCommonExt.0")))
            m_algorithm.editLink(m_MatrixEditNode, Integer.parseInt(value));
        if(m_algorithm != null)
            m_canvas.processRepaintEvent(new RepaintEvent(m_canvas, m_algorithm));
    }

    public void editMatrix(GraphMapsNode p_Node)
    {
        if(m_algorithm != null && !m_algorithm.getIsRunning())
        {
            m_MatrixEditNode = p_Node;
            m_frmMatrixEdit = new Frame();
            m_frmMatrixEdit.setSize(100, 70);
            m_frmMatrixEdit.setLocation(0, 0);
            m_dlgMatrixEdit = new InputDialog(m_canvas, m_frmMatrixEdit, Messages.getString("MSTKGraphCanvasCommonExt.1"), true, Messages.getString("MSTKGraphCanvasCommonExt.2"), p_Node.getIntValue());
            m_dlgMatrixEdit.show();
        }
    }

    public void drawAdjacencyMatrix(Graphics g)
    {
        if(vecAdjacencyMatrix == null)
            return;
        if(!m_algorithm.getMatrixShowing())
            return;
        FontMetrics fm = g.getFontMetrics();
        g.setColor(Color.blue);
        g.drawString(Messages.getString("MSTKGraphCanvasCommonExt.3"), MATRIX_X, MATRIX_Y - fm.getHeight());
        MSTKGraphMapsCanvasExt _tmp = m_canvas;
        g.drawString(Messages.getString("MSTKGraphCanvasCommonExt.4"), MATRIX_X - fm.stringWidth(Messages.getString("MSTKGraphCanvasCommonExt.5")), (MATRIX_Y + 20) - fm.getMaxDescent());
        g.drawString(Messages.getString("MSTKGraphCanvasCommonExt.6"), MATRIX_X, MATRIX_Y - fm.getHeight() * 2);
        g.setColor(m_canvas.getTextColor());
        for(int i = 0; i < vecAdjacencyMatrix.size(); i++)
        {
            String label = Integer.toString(i);
            MSTKGraphMapsCanvasExt _tmp1 = m_canvas;
            MSTKGraphMapsCanvasExt _tmp2 = m_canvas;
            g.drawString(label, MATRIX_X + 20 * i + (20 - fm.stringWidth(label)) / 2, MATRIX_Y - fm.getMaxDescent());
            MSTKGraphMapsCanvasExt _tmp3 = m_canvas;
            g.drawString(label, MATRIX_X - fm.stringWidth(label), (MATRIX_Y + 20 * (i + 1)) - fm.getMaxDescent());
            ElementArray elementArray = (ElementArray)vecAdjacencyMatrix.elementAt(i);
            elementArray.draw(g);
            m_canvas.drawMatrixAssociatedElements(g, elementArray, i);
        }

    }

    public void drawAdjacencyStructure(Graphics g)
    {
        if(earrAdjacencyStructure == null)
            return;
        if(!m_algorithm.getStructureShowing())
            return;
        g.setColor(Color.blue);
        FontMetrics fm = g.getFontMetrics();
        g.drawString(Messages.getString("MSTKGraphCanvasCommonExt.7"), earrAdjacencyStructure.getLocation().x, STRUCTURE_Y - fm.getHeight() * 2);
        g.setColor(m_canvas.getTextColor());
        earrAdjacencyStructure.draw(g);
label0:
        for(int i = 0; i < m_algorithm.getNumberOfNodes(); i++)
        {
            GraphMapsNode rootNode = (GraphMapsNode)earrAdjacencyStructure.getElement(i);
            GraphNode root = m_algorithm.getAdjacentNode(i);
            int level = 1;
            do
            {
                if(root == null)
                    continue label0;
                MSTKGraphMapsCanvasExt _tmp = m_canvas;
                MSTKGraphMapsCanvasExt _tmp1 = m_canvas;
                MSTKGraphMapsCanvasExt _tmp2 = m_canvas;
                MSTKGraphMapsCanvasExt _tmp3 = m_canvas;
                MSTKGraphMapsCanvasExt _tmp4 = m_canvas;
                Line line = new Line(rootNode.getX() + 20 / 2, rootNode.getY() + (20 + LINK_LENGTH) * (level - 1) + 20, rootNode.getX() + 20 / 2, rootNode.getY() + (20 + LINK_LENGTH) * level);
                line.showArrowHead(true);
                line.setDistanceFromStartForArrowHead(-3);
                line.draw(g);
                GraphMapsNode node = new GraphMapsNode(new Integer(root.getKey()), root.getKey(), 3);
                MSTKGraphMapsCanvasExt _tmp5 = m_canvas;
                node.setPosition(new Point(rootNode.getX(), rootNode.getY() + (20 + LINK_LENGTH) * level));
                m_canvas.setStructureNodeAppearance(node, rootNode);
                m_canvas.drawStructureAssociatedElements(g, node, rootNode);
                node.draw(g);
                level++;
                if(root.getKey() == -1)
                    continue label0;
                root = root.getNext();
            } while(true);
        }

    }

    protected Vector vecAdjacencyMatrix;
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
}
