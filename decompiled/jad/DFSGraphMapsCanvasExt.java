// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DFSGraphMapsCanvasExt.java

import aia.graph.common.*;
import com.cim.AIA.*;
import java.awt.*;
import java.util.Vector;
import localization.Messages;

public class DFSGraphMapsCanvasExt extends GraphMapsCanvasExt
    implements GraphDialogEventHandler
{

    DFSGraphMapsCanvasExt()
    {
        commons = null;
        MATRIX_X = 50;
        MATRIX_Y = 130;
        STRUCTURE_X = 30;
        STRUCTURE_Y = 130;
        LINK_LENGTH = 20;
        earrSeqArray = null;
        SEQ_ARRAY_X = 140;
        SEQ_ARRAY_Y = 20;
        SEQ_NUM_X = 140;
        SEQ_NUM_Y = 90;
        tweens = new MultipleTween(this);
        addSelectionListener(new DragSelectionListener(this));
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

    public void editMatrix(GraphMapsNode p_Node)
    {
        commons.editMatrix(p_Node);
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
            commons.drawAdjacencyMatrix(g);
            commons.drawAdjacencyStructure(g);
            drawSeqArray(g);
            drawSeqNumber(g);
            return;
        }
    }

    public Color getTextColor()
    {
        return textColor;
    }

    public void drawMatrixAssociatedElements(Graphics g1, ElementArray elementarray, int i)
    {
    }

    public void drawStructureAssociatedElements(Graphics g1, GraphMapsNode graphmapsnode, GraphMapsNode graphmapsnode1)
    {
    }

    public void setStructureNodeAppearance(GraphMapsNode node, GraphMapsNode rootNode)
    {
        if(graphMaps == null)
            return;
        if(node.getIntValue() != -1)
        {
            node.setBackgroundColor(graphMaps.getUnvisitedNodeColor());
            node.setTextColor(graphMaps.getTextColor());
            GraphMapsNode adj_node = graphMaps.getAdjNode();
            if(adj_node != null)
                if(adj_node.getFrom() == rootNode.getIntValue() && adj_node.getTo() == node.getIntValue())
                    node.setBackgroundColor(graphMaps.getAdjacentNodeColor());
                else
                if(adj_node.getFrom() == rootNode.getIntValue())
                    node.setBackgroundColor(graphMaps.getAdjacentNodesColor());
        } else
        {
            node.setBackgroundColor(Color.white);
            node.setTextColor(Color.white);
        }
    }

    private void drawSeqNumber(Graphics g)
    {
        FontMetrics fm = g.getFontMetrics();
        g.setColor(Color.black);
        if(graphMaps == null)
            return;
        int seq_num = graphMaps.getSeqNumber();
        if(seq_num != -10)
            g.drawString((new StringBuilder()).append(Messages.getString("DFSGraphMapsCanvasExt.0")).append(Integer.toString(seq_num)).toString(), SEQ_NUM_X - fm.stringWidth(Messages.getString("DFSGraphMapsCanvasExt.1")), SEQ_NUM_Y);
    }

    private void drawSeqArray(Graphics g)
    {
        int nK = -1;
        FontMetrics fm = g.getFontMetrics();
        g.setColor(Color.black);
        g.drawString(Messages.getString("DFSGraphMapsCanvasExt.2"), SEQ_ARRAY_X - fm.stringWidth(Messages.getString("DFSGraphMapsCanvasExt.3")), (SEQ_ARRAY_Y + 20) - fm.getMaxDescent());
        if(earrSeqArray != null)
        {
            earrSeqArray.draw(g);
            g.setColor(Color.black);
            for(int i = 0; i < earrSeqArray.getSize(); i++)
            {
                GraphMapsNode node = (GraphMapsNode)earrSeqArray.getElement(i);
                g.drawString(Integer.toString(i), node.getX() + (20 - fm.stringWidth(Integer.toString(i))) / 2, node.getY() - fm.getMaxDescent());
            }

            for(int i = 0; i < earrSeqArray.getSize(); i++)
            {
                GraphMapsNode node = (GraphMapsNode)earrSeqArray.getElement(i);
                String strVisitStatus = graphMaps.getVisitStatus(i);
                g.drawString(strVisitStatus, node.getX() + (20 - fm.stringWidth(strVisitStatus)) / 2, node.getY() + 20 + fm.getHeight());
            }

            if(graphMaps.getKMarker() != -10)
            {
                nK = graphMaps.getKMarker();
                GraphMapsNode node = (GraphMapsNode)earrSeqArray.getElement(nK);
                g.setColor(Color.black);
                g.drawString("k", node.getX() + 10, node.getY() + 20 + 10 + fm.getHeight() * 2);
                Line line = new Line(node.getX() + 10, node.getY() + 20 + 10 + fm.getHeight(), node.getX() + 10, node.getY() + 20 + fm.getHeight());
                line.showArrowHead(true);
                line.setDistanceFromStartForArrowHead(-3);
                line.draw(g);
            }
            if(graphMaps.getAdjNode() != null && graphMaps.getAdjNode().getTo() >= 0)
            {
                GraphMapsNode node = (GraphMapsNode)earrSeqArray.getElement(graphMaps.getAdjNode().getTo());
                g.setColor(Color.black);
                if(nK != -1 && nK == graphMaps.getAdjNode().getTo())
                    g.drawString(",a", node.getX() + 10 + fm.stringWidth("k"), node.getY() + 20 + 10 + fm.getHeight() * 2);
                else
                    g.drawString(Messages.getString("DFSGraphMapsCanvasExt.7"), node.getX() + 10, node.getY() + 20 + 10 + fm.getHeight() * 2);
                Line line = new Line(node.getX() + 10, node.getY() + 20 + 10 + fm.getHeight(), node.getX() + 10, node.getY() + 20 + fm.getHeight());
                line.showArrowHead(true);
                line.setDistanceFromStartForArrowHead(-3);
                line.draw(g);
            }
        }
    }

    public void processRepaintEvent(RepaintEvent e)
    {
        if(e.paramObj != null)
        {
            drawables.removeAllElements();
            graphMaps = (DFSGraphMaps)(DFSGraphMaps)e.paramObj;
            if(commons == null)
                commons = new DFSGraphCanvasCommonExt(this, graphMaps);
            removeAllSelectables();
            commons.setMatrix(graphMaps.getAdjacencyMatrix(), MATRIX_X, MATRIX_Y);
            commons.setStructure(graphMaps.getAdjacencyStructure(), STRUCTURE_X, STRUCTURE_Y, LINK_LENGTH);
            commons.adjustCanvasSize();
            earrSeqArray = graphMaps.getSeqArray();
            if(earrSeqArray != null)
            {
                earrSeqArray.setLocation(SEQ_ARRAY_X, SEQ_ARRAY_Y);
                if(earrSeqArray.getWidth() + SEQ_ARRAY_X > getSize().width)
                    setSize(earrSeqArray.getWidth() + SEQ_ARRAY_X, getSize().height);
            }
            graphMaps.removeAllAnimationRequests();
            repaint();
        }
    }

    protected DFSGraphMaps graphMaps;
    protected DFSGraphCanvasCommonExt commons;
    protected int MATRIX_X;
    protected int MATRIX_Y;
    protected int STRUCTURE_X;
    protected int STRUCTURE_Y;
    protected int LINK_LENGTH;
    protected ElementArray earrSeqArray;
    protected int SEQ_ARRAY_X;
    protected int SEQ_ARRAY_Y;
    protected int SEQ_NUM_X;
    protected int SEQ_NUM_Y;
    protected Font normalFont;
    protected Color textColor;
    public static final int NODE_WIDTH = 20;
    public static final int NODE_HEIGHT = 20;
}
