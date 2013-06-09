package aia.graph.common;

import java.util.*;
import localization.*;
import java.awt.*;
import com.cim.AIA.*;

public abstract class GraphMapsCanvasExt extends AlgorithmCanvas implements GraphDialogEventHandler
{
    public static int QUEUE_X;
    public static int QUEUE_Y;
    public static final int NODE_WIDTH = 20;
    public static final int NODE_HEIGHT = 20;
    protected GraphMaps graphMaps;
    protected GraphCanvasCommonExt commons;
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
    protected int SAMP_QUEUE_MEM_X;
    protected int SAMP_QUEUE_MEM_Y;
    protected Font normalFont;
    protected Color textColor;
    
    public GraphMapsCanvasExt() {
        super();
        this.commons = null;
        this.MATRIX_X = 50;
        this.MATRIX_Y = 182;
        this.STRUCTURE_X = 30;
        this.STRUCTURE_Y = 182;
        this.LINK_LENGTH = 20;
        this.earrSeqArray = null;
        this.SEQ_ARRAY_X = 140;
        this.SEQ_ARRAY_Y = 72;
        this.SEQ_NUM_X = 140;
        this.SEQ_NUM_Y = 142;
        this.earrQueue = null;
        this.SAMP_QUEUE_MEM_X = 180;
        this.SAMP_QUEUE_MEM_Y = 7;
    }
    
    public void displayAlgorithm(final Graphics g) {
        if (this.normalFont != null) {
            g.setFont(this.normalFont);
        }
        if (this.textColor != null) {
            g.setColor(this.textColor);
        }
        if (this.commons == null) {
            return;
        }
        this.commons.drawAdjacencyMatrix(g);
        this.commons.drawAdjacencyStructure(g);
        this.drawCurrentQueueNode(g);
        this.drawQueue(g);
        this.drawSeqArray(g);
        this.drawSeqNumber(g);
    }
    
    private void drawAdjacencyStructure(final Graphics g) {
    }
    
    private void drawAdjacencyMatrix(final Graphics g) {
    }
    
    public void drawCurrentQueueNode(final Graphics g) {
        if (this.graphMaps == null) {
            return;
        }
        final GraphMapsNode node = this.graphMaps.getCurrentQueueNode();
        if (node != null) {
            node.setPosition(new Point(GraphMapsCanvasExt.QUEUE_X - 50, GraphMapsCanvasExt.QUEUE_Y));
            node.draw(g);
        }
    }
    
    public void drawMatrixAssociatedElements(final Graphics g, final ElementArray elementArray, final int index) {
    }
    
    private void drawQueue(final Graphics g) {
        final FontMetrics fm = g.getFontMetrics();
        g.setColor(Color.black);
        g.drawString(Messages.getString("GraphMapsCanvasExt.2"), GraphMapsCanvasExt.QUEUE_X, GraphMapsCanvasExt.QUEUE_Y + 20 - fm.getMaxDescent());
        if (this.earrQueue != null) {
            this.earrQueue.draw(g);
            if (this.graphMaps != null && this.graphMaps.isQueueOverFlow()) {
                g.setColor(Color.black);
                g.drawString("...", this.earrQueue.getLocation().x + this.earrQueue.getWidth() - this.earrQueue.getElementWidth() + 10, this.earrQueue.getLocation().y + 20);
            }
        }
    }
    
    private void drawSeqArray(final Graphics g) {
        int nK = -1;
        final FontMetrics fm = g.getFontMetrics();
        g.setColor(Color.black);
        g.drawString(Messages.getString("GraphMapsCanvasExt.4"), this.SEQ_ARRAY_X - fm.stringWidth(Messages.getString("GraphMapsCanvasExt.5")), this.SEQ_ARRAY_Y + 20 - fm.getMaxDescent());
        if (this.earrSeqArray != null) {
            this.earrSeqArray.draw(g);
            g.setColor(Color.black);
            int i;
            GraphMapsNode node;
            for (i = 0; i < this.earrSeqArray.getSize(); ++i) {
                node = (GraphMapsNode)this.earrSeqArray.getElement(i);
                g.drawString(Integer.toString(i), node.getX() + (20 - fm.stringWidth(Integer.toString(i))) / 2, node.getY() - fm.getMaxDescent());
            }
            i = 0;
            while (i < this.earrSeqArray.getSize()) {
                node = (GraphMapsNode)this.earrSeqArray.getElement(i);
                int p_key = 0;
                final String strVisitStatus = this.graphMaps.getVisitStatus(p_key);
                g.drawString(strVisitStatus, node.getX() + (20 - fm.stringWidth(strVisitStatus)) / 2, node.getY() + 20 + fm.getHeight());
                ++p_key;
            }
            GraphMapsNode graphMapsNode;
            Line line;
            if (this.graphMaps.getKMarker() != -10) {
                nK = this.graphMaps.getKMarker();
                graphMapsNode = (GraphMapsNode)this.earrSeqArray.getElement(nK);
                g.setColor(Color.black);
                g.drawString("k", graphMapsNode.getX() + 10, graphMapsNode.getY() + 20 + 10 + fm.getHeight() * 2);
                line = new Line(graphMapsNode.getX() + 10, graphMapsNode.getY() + 20 + 10 + fm.getHeight(), graphMapsNode.getX() + 10, graphMapsNode.getY() + 20 + fm.getHeight());
                line.showArrowHead(true);
                line.setDistanceFromStartForArrowHead(-3);
                line.draw(g);
            }
            if (this.graphMaps.getAdjNode() != null && this.graphMaps.getAdjNode().getTo() >= 0) {
                graphMapsNode = (GraphMapsNode)this.earrSeqArray.getElement(this.graphMaps.getAdjNode().getTo());
                g.setColor(Color.black);
                if (nK != -1 && nK == this.graphMaps.getAdjNode().getTo()) {
                    g.drawString(",a", graphMapsNode.getX() + 10 + fm.stringWidth("k"), graphMapsNode.getY() + 20 + 10 + fm.getHeight() * 2);
                }
                else {
                    g.drawString("a", graphMapsNode.getX() + 10, graphMapsNode.getY() + 20 + 10 + fm.getHeight() * 2);
                }
                line = new Line(graphMapsNode.getX() + 10, graphMapsNode.getY() + 20 + 10 + fm.getHeight(), graphMapsNode.getX() + 10, graphMapsNode.getY() + 20 + fm.getHeight());
                line.showArrowHead(true);
                line.setDistanceFromStartForArrowHead(-3);
                line.draw(g);
            }
        }
    }
    
    private void drawSeqNumber(final Graphics g) {
        final FontMetrics fm = g.getFontMetrics();
        g.setColor(Color.black);
        if (this.graphMaps == null) {
            return;
        }
        final int seq_num = this.graphMaps.getSeqNumber();
        if (seq_num != -10) {
            g.drawString(Messages.getString("GraphMapsCanvasExt.0") + Integer.toString(seq_num), this.SEQ_NUM_X - fm.stringWidth(Messages.getString("GraphMapsCanvasExt.1")), this.SEQ_NUM_Y);
        }
    }
    
    public void drawStructureAssociatedElements(final Graphics g, final GraphMapsNode node, final GraphMapsNode rootNode) {
    }
    
    public void editMatrix(final GraphMapsNode p_Node) {
        this.commons.editMatrix(p_Node);
    }
    
    public Color getTextColor() {
        return this.textColor;
    }
    
    protected void handleColorSelection(final ColorSelection colorSelection) {
        final String atribute = colorSelection.getAtributeName();
        if (atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR)) {
            this.textColor = colorSelection.getColor();
        }
    }
    
    protected void handleFontSelection(final FontSelection fontSelection) {
        final String atribute = fontSelection.getAtributeName();
        if (atribute.equalsIgnoreCase("Normal Font")) {
            this.normalFont = fontSelection.getFont();
        }
    }
    
    public void processDialogEvent(final String p_sourceDialog, final String value) {
        this.commons.processDialogEvent(p_sourceDialog, value);
    }
    
    public void processRepaintEvent(final RepaintEvent e) {
        if (e.paramObj != null) {
            this.drawables.removeAllElements();
            this.graphMaps = (GraphMaps)((GraphMaps)e.paramObj);
            if (this.commons == null) {
                this.commons = new GraphCanvasCommonExt(this, this.graphMaps);
            }
            this.removeAllSelectables();
            this.commons.setMatrix(this.graphMaps.getAdjacencyMatrix(), this.MATRIX_X, this.MATRIX_Y);
            this.commons.setStructure(this.graphMaps.getAdjacencyStructure(), this.STRUCTURE_X, this.STRUCTURE_Y, this.LINK_LENGTH);
            this.earrQueue = this.graphMaps.getQueue();
            if (this.earrQueue != null) {
                this.earrQueue.setLocation(GraphMapsCanvasExt.QUEUE_X + 40, GraphMapsCanvasExt.QUEUE_Y);
            }
            this.commons.adjustCanvasSize();
            this.earrSeqArray = this.graphMaps.getSeqArray();
            if (this.earrSeqArray != null) {
                this.earrSeqArray.setLocation(this.SEQ_ARRAY_X, this.SEQ_ARRAY_Y);
                if (this.earrSeqArray.getWidth() + this.SEQ_ARRAY_X > this.getSize().width) {
                    this.setSize(this.earrSeqArray.getWidth() + this.SEQ_ARRAY_X, this.getSize().height);
                }
            }
            final MultipleTween multipleTween = this.graphMaps.generateTweens(this, this.earrQueue, this.numberOfTweenSteps);
            if (multipleTween != null) {
                this.addTween(multipleTween);
            }
            if (this.tweens.getNumberOfTweens() > 0) {
                this.tweens.run();
            }
            this.graphMaps.removeAllAnimationRequests();
            this.repaint();
        }
    }
    
    private void adjustCanvasSize() {
    }
    
    private void setStructure(final ElementArray adjacencyStructure, final int structure_x2, final int structure_y2, final int link_length2) {
    }
    
    private void setMatrix(final Vector<ElementArray> adjacencyMatrix, final int matrix_x2, final int matrix_y2) {
    }
    
    public void setStructureNodeAppearance(final GraphMapsNode node, final GraphMapsNode rootNode) {
        if (this.graphMaps == null) {
            return;
        }
        if (node.getIntValue() != -1) {
            node.setBackgroundColor(this.graphMaps.getUnvisitedNodeColor());
            node.setTextColor(this.graphMaps.getTextColor());
            final GraphMapsNode adj_node = this.graphMaps.getAdjNode();
            if (adj_node != null) {
                if (adj_node.getFrom() == rootNode.getIntValue() && adj_node.getTo() == node.getIntValue()) {
                    node.setBackgroundColor(this.graphMaps.getAdjacentNodeColor());
                }
                else if (adj_node.getFrom() == rootNode.getIntValue()) {
                    node.setBackgroundColor(this.graphMaps.getAdjacentNodesColor());
                }
            }
        }
        else {
            node.setBackgroundColor(Color.white);
            node.setTextColor(Color.white);
        }
    }
    
    static {
        GraphMapsCanvasExt.QUEUE_X = 80;
        GraphMapsCanvasExt.QUEUE_Y = 32;
    }
}
