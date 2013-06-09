// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MinSpanTreePrimGraphMapsCanvasExt.java

import aia.graph.common.*;
import com.cim.AIA.*;
import java.awt.*;
import java.util.Vector;
import localization.Messages;

public class MinSpanTreePrimGraphMapsCanvasExt extends GraphMapsCanvasExt
    implements GraphDialogEventHandler
{

    MinSpanTreePrimGraphMapsCanvasExt()
    {
        commons = null;
        MATRIX_X = 50;
        MATRIX_Y = 202;
        STRUCTURE_X = 30;
        STRUCTURE_Y = 202;
        LINK_LENGTH = 20;
        earrSeqArray = null;
        SEQ_ARRAY_X = 140;
        SEQ_ARRAY_Y = 92;
        SEQ_NUM_X = 140;
        SEQ_NUM_Y = 162;
        earrQueue = null;
        SAMP_QUEUE_MEM_X = 180;
        SAMP_QUEUE_MEM_Y = 7;
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

    public void processDialogEvent(String sourceDialog, String value)
    {
        commons.processDialogEvent(sourceDialog, value);
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
            drawCurrentPQNode(g);
            drawQueue(g);
            drawSampleQueueMember(g);
            drawSeqArray(g);
            drawSeqNumber(g);
            return;
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
            g.drawString((new StringBuilder()).append(Messages.getString("MinSpanTreePrimGraphMapsCanvasExt.0")).append(Integer.toString(seq_num)).toString(), SEQ_NUM_X - fm.stringWidth(Messages.getString("MinSpanTreePrimGraphMapsCanvasExt.1")), SEQ_NUM_Y);
    }

    private void drawCurrentPQNode(Graphics g)
    {
        if(graphMaps == null)
            return;
        TwinNode node = graphMaps.getCurrentPQNode();
        if(node != null)
        {
            node.setPosition(new Point(QUEUE_X - 50, QUEUE_Y));
            node.draw(g);
        }
    }

    private void drawSampleQueueMember(Graphics g)
    {
        TwinNode node = new TwinNode(new Integer(1), new Integer(0), 0);
        FontMetrics fm = g.getFontMetrics();
        node.setBackgroundColorLeft(graphMaps.getQueueNodeColorLeft());
        node.setBackgroundColorRight(graphMaps.getQueueNodeColorRight());
        node.setWidth(40);
        node.setHeight(20);
        node.setPosition(new Point(SAMP_QUEUE_MEM_X, SAMP_QUEUE_MEM_Y));
        node.draw(g);
        g.setColor(Color.black);
        g.drawLine(SAMP_QUEUE_MEM_X + 40, SAMP_QUEUE_MEM_Y + 10, SAMP_QUEUE_MEM_X + 60, SAMP_QUEUE_MEM_Y + 10);
        g.drawString(Messages.getString("MinSpanTreePrimGraphMapsCanvasExt.2"), SAMP_QUEUE_MEM_X + 60 + 2, SAMP_QUEUE_MEM_Y + (20 + fm.getHeight()) / 2);
        g.drawLine(SAMP_QUEUE_MEM_X + 10, SAMP_QUEUE_MEM_Y, SAMP_QUEUE_MEM_X + 10, SAMP_QUEUE_MEM_Y - 5);
        g.drawLine(SAMP_QUEUE_MEM_X + 10, SAMP_QUEUE_MEM_Y - 5, SAMP_QUEUE_MEM_X + 60 + fm.stringWidth(Messages.getString("MinSpanTreePrimGraphMapsCanvasExt.3")) + fm.stringWidth(Messages.getString("MinSpanTreePrimGraphMapsCanvasExt.4")) / 2, SAMP_QUEUE_MEM_Y - 5);
        g.drawLine(SAMP_QUEUE_MEM_X + 60 + fm.stringWidth(Messages.getString("MinSpanTreePrimGraphMapsCanvasExt.5")) + fm.stringWidth(Messages.getString("MinSpanTreePrimGraphMapsCanvasExt.6")) / 2, SAMP_QUEUE_MEM_Y - 5, SAMP_QUEUE_MEM_X + 60 + fm.stringWidth(Messages.getString("MinSpanTreePrimGraphMapsCanvasExt.7")) + fm.stringWidth(Messages.getString("MinSpanTreePrimGraphMapsCanvasExt.8")) / 2, SAMP_QUEUE_MEM_Y);
        g.drawString(Messages.getString("MinSpanTreePrimGraphMapsCanvasExt.9"), SAMP_QUEUE_MEM_X + 60 + 20 + fm.stringWidth(Messages.getString("MinSpanTreePrimGraphMapsCanvasExt.10")), SAMP_QUEUE_MEM_Y + (20 + fm.getHeight()) / 2);
        g.drawString(Messages.getString("MinSpanTreePrimGraphMapsCanvasExt.11"), SAMP_QUEUE_MEM_X - fm.stringWidth(Messages.getString("MinSpanTreePrimGraphMapsCanvasExt.12")) - 5, SAMP_QUEUE_MEM_Y + (20 + fm.getHeight()) / 2);
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

    private void drawQueue(Graphics g)
    {
        FontMetrics fm = g.getFontMetrics();
        g.setColor(Color.black);
        g.drawString(Messages.getString("MinSpanTreePrimGraphMapsCanvasExt.13"), QUEUE_X, (QUEUE_Y + 20) - fm.getMaxDescent());
        g.drawString(Messages.getString("MinSpanTreePrimGraphMapsCanvasExt.14"), QUEUE_X + fm.stringWidth(Messages.getString("MinSpanTreePrimGraphMapsCanvasExt.15")), QUEUE_Y + 20 + fm.getHeight());
        if(earrQueue != null)
        {
            earrQueue.draw(g);
            if(graphMaps.isOverFlow())
            {
                g.setColor(Color.black);
                g.drawString("...", ((QUEUE_X + 100 + earrQueue.getWidth()) - earrQueue.getElementWidth()) + 10, QUEUE_Y + 20);
            }
        }
    }

    private void drawSeqArray(Graphics g)
    {
        int nK = -1;
        FontMetrics fm = g.getFontMetrics();
        g.setColor(Color.black);
        g.drawString("Visit Sequence: ", SEQ_ARRAY_X - fm.stringWidth("Visit Sequence:"), (SEQ_ARRAY_Y + 20) - fm.getMaxDescent());
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
                    g.drawString("a", node.getX() + 10, node.getY() + 20 + 10 + fm.getHeight() * 2);
                Line line = new Line(node.getX() + 10, node.getY() + 20 + 10 + fm.getHeight(), node.getX() + 10, node.getY() + 20 + fm.getHeight());
                line.showArrowHead(true);
                line.setDistanceFromStartForArrowHead(-3);
                line.draw(g);
            }
        }
    }

    public void processRepaintEvent(RepaintEvent e)
    {
        int matrix_width = 0;
        if(e.paramObj != null)
        {
            drawables.removeAllElements();
            graphMaps = (MinSpanTreePrimGraphMaps)(MinSpanTreePrimGraphMaps)e.paramObj;
            if(commons == null)
                commons = new MinSpanTreePrimGraphCanvasCommonExt(this, graphMaps);
            removeAllSelectables();
            commons.setMatrix(graphMaps.getAdjacencyMatrix(), MATRIX_X, MATRIX_Y);
            commons.setStructure(graphMaps.getAdjacencyStructure(), STRUCTURE_X, STRUCTURE_Y, LINK_LENGTH);
            earrQueue = graphMaps.getQueue();
            if(earrQueue != null)
                earrQueue.setLocation(QUEUE_X + 100, QUEUE_Y);
            commons.adjustCanvasSize();
            earrSeqArray = graphMaps.getSeqArray();
            if(earrSeqArray != null)
            {
                earrSeqArray.setLocation(SEQ_ARRAY_X, SEQ_ARRAY_Y);
                if(earrSeqArray.getWidth() + SEQ_ARRAY_X > getSize().width)
                    setSize(earrSeqArray.getWidth() + SEQ_ARRAY_X, getSize().height);
            }
            MultipleTween multipleTween = graphMaps.generateTweens(this, earrQueue, numberOfTweenSteps);
            if(multipleTween != null)
                addTween(multipleTween);
            if(tweens.getNumberOfTweens() > 0)
                tweens.run();
            graphMaps.removeAllAnimationRequests();
            repaint();
        }
    }

    protected MinSpanTreePrimGraphMaps graphMaps;
    protected MinSpanTreePrimGraphCanvasCommonExt commons;
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
    protected ElementArray earrQueue;
    public static int QUEUE_X = 80;
    public static int QUEUE_Y = 32;
    protected int SAMP_QUEUE_MEM_X;
    protected int SAMP_QUEUE_MEM_Y;
    protected Font normalFont;
    protected Color textColor;
    public static final int NODE_WIDTH = 20;
    public static final int NODE_HEIGHT = 20;

}
