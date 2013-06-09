// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MSTKGraphMapsCanvasExt.java

import aia.graph.common.*;
import com.cim.AIA.*;
import java.awt.*;
import java.util.Vector;
import localization.Messages;

public class MSTKGraphMapsCanvasExt extends GraphMapsCanvasExt
    implements GraphDialogEventHandler
{

    MSTKGraphMapsCanvasExt()
    {
        commons = null;
        MATRIX_X = 50;
        MATRIX_Y = 145;
        STRUCTURE_X = 30;
        STRUCTURE_Y = 145;
        LINK_LENGTH = 20;
        EDGES_ADDED_X = 30;
        EDGES_ADDED_Y = 90;
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
            drawCurrentEdge(g);
            drawQueue(g);
            drawEdgesAddedCounter(g);
            return;
        }
    }

    private void drawCurrentEdge(Graphics g)
    {
        if(graphMaps == null)
            return;
        TwinNode node = graphMaps.getCurrentEdgeNode();
        if(node != null)
        {
            FontMetrics fm = g.getFontMetrics();
            node.setPosition(new Point(QUEUE_X - 50, QUEUE_Y));
            node.draw(g);
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
        if(node.getIntValue() != -1)
        {
            node.setBackgroundColor(graphMaps.getUnvisitedNodeColor());
            node.setTextColor(graphMaps.getTextColor());
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
        g.drawString(Messages.getString("MSTKGraphMapsCanvasExt.0"), QUEUE_X, (QUEUE_Y + 20) - fm.getMaxDescent());
        g.drawString(Messages.getString("MSTKGraphMapsCanvasExt.1"), QUEUE_X + fm.stringWidth(Messages.getString("MSTKGraphMapsCanvasExt.2")), QUEUE_Y + 20 + fm.getHeight());
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

    private void drawEdgesAddedCounter(Graphics g)
    {
        FontMetrics fm = g.getFontMetrics();
        g.setColor(Color.black);
        g.drawString(Messages.getString("MSTKGraphMapsCanvasExt.4"), EDGES_ADDED_X, EDGES_ADDED_Y + fm.getHeight());
        MSTKGraphMaps _tmp = graphMaps;
        if(graphMaps.getEdgesAdded() != -10)
            g.drawString(Integer.toString(graphMaps.getEdgesAdded()), EDGES_ADDED_X + fm.stringWidth(Messages.getString("MSTKGraphMapsCanvasExt.5")), EDGES_ADDED_Y + fm.getHeight());
    }

    public void processRepaintEvent(RepaintEvent e)
    {
        int matrix_width = 0;
        if(e.paramObj != null)
        {
            drawables.removeAllElements();
            graphMaps = (MSTKGraphMaps)(MSTKGraphMaps)e.paramObj;
            if(commons == null)
                commons = new MSTKGraphCanvasCommonExt(this, graphMaps);
            removeAllSelectables();
            commons.setMatrix(graphMaps.getAdjacencyMatrix(), MATRIX_X, MATRIX_Y);
            commons.setStructure(graphMaps.getAdjacencyStructure(), STRUCTURE_X, STRUCTURE_Y, LINK_LENGTH);
            earrQueue = graphMaps.getQueue();
            if(earrQueue != null)
                earrQueue.setLocation(QUEUE_X + 100, QUEUE_Y);
            commons.adjustCanvasSize();
            MultipleTween multipleTween = graphMaps.generateTweens(this, earrQueue, numberOfTweenSteps);
            if(multipleTween != null)
                addTween(multipleTween);
            if(tweens.getNumberOfTweens() > 0)
                tweens.run();
            graphMaps.removeAllAnimationRequests();
            repaint();
        }
    }

    protected MSTKGraphMaps graphMaps;
    protected MSTKGraphCanvasCommonExt commons;
    protected int MATRIX_X;
    protected int MATRIX_Y;
    protected int STRUCTURE_X;
    protected int STRUCTURE_Y;
    protected int LINK_LENGTH;
    protected int EDGES_ADDED_X;
    protected int EDGES_ADDED_Y;
    protected ElementArray earrQueue;
    public static int QUEUE_X = 80;
    public static int QUEUE_Y = 40;
    protected int SAMP_QUEUE_MEM_X;
    protected int SAMP_QUEUE_MEM_Y;
    protected Font normalFont;
    protected Color textColor;
    public static final int NODE_WIDTH = 20;
    public static final int NODE_HEIGHT = 20;

}
