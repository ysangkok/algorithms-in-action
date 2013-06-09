import localization.*;
import java.awt.*;
import com.cim.AIA.*;
import aia.graph.common.*;

public class DFSGraphMapsCanvasExt extends GraphMapsCanvasExt implements GraphDialogEventHandler
{
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
    
    DFSGraphMapsCanvasExt() {
        super();
        this.commons = null;
        this.MATRIX_X = 50;
        this.MATRIX_Y = 130;
        this.STRUCTURE_X = 30;
        this.STRUCTURE_Y = 130;
        this.LINK_LENGTH = 20;
        this.earrSeqArray = null;
        this.SEQ_ARRAY_X = 140;
        this.SEQ_ARRAY_Y = 20;
        this.SEQ_NUM_X = 140;
        this.SEQ_NUM_Y = 90;
        this.tweens = new MultipleTween(this);
        this.addSelectionListener(new DragSelectionListener(this));
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
    
    public void editMatrix(final GraphMapsNode p_Node) {
        this.commons.editMatrix(p_Node);
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
        this.drawSeqArray(g);
        this.drawSeqNumber(g);
    }
    
    public Color getTextColor() {
        return this.textColor;
    }
    
    public void drawMatrixAssociatedElements(final Graphics g, final ElementArray elementArray, final int index) {
    }
    
    public void drawStructureAssociatedElements(final Graphics g, final GraphMapsNode node, final GraphMapsNode rootNode) {
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
    
    private void drawSeqNumber(final Graphics g) {
        final FontMetrics fm = g.getFontMetrics();
        g.setColor(Color.black);
        if (this.graphMaps == null) {
            return;
        }
        final int seq_num = this.graphMaps.getSeqNumber();
        if (seq_num != -10) {
            g.drawString(Messages.getString("DFSGraphMapsCanvasExt.0") + Integer.toString(seq_num), this.SEQ_NUM_X - fm.stringWidth(Messages.getString("DFSGraphMapsCanvasExt.1")), this.SEQ_NUM_Y);
        }
    }
    
    private void drawSeqArray(final Graphics g) {
        int nK = -1;
        final FontMetrics fm = g.getFontMetrics();
        g.setColor(Color.black);
        g.drawString(Messages.getString("DFSGraphMapsCanvasExt.2"), this.SEQ_ARRAY_X - fm.stringWidth(Messages.getString("DFSGraphMapsCanvasExt.3")), this.SEQ_ARRAY_Y + 20 - fm.getMaxDescent());
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
                    g.drawString(Messages.getString("DFSGraphMapsCanvasExt.7"), graphMapsNode.getX() + 10, graphMapsNode.getY() + 20 + 10 + fm.getHeight() * 2);
                }
                line = new Line(graphMapsNode.getX() + 10, graphMapsNode.getY() + 20 + 10 + fm.getHeight(), graphMapsNode.getX() + 10, graphMapsNode.getY() + 20 + fm.getHeight());
                line.showArrowHead(true);
                line.setDistanceFromStartForArrowHead(-3);
                line.draw(g);
            }
        }
    }
    
    public void processRepaintEvent(final RepaintEvent e) {
        if (e.paramObj != null) {
            this.drawables.removeAllElements();
            this.graphMaps = (DFSGraphMaps)((DFSGraphMaps)e.paramObj);
            if (this.commons == null) {
                this.commons = new DFSGraphCanvasCommonExt(this, this.graphMaps);
            }
            this.removeAllSelectables();
            this.commons.setMatrix(this.graphMaps.getAdjacencyMatrix(), this.MATRIX_X, this.MATRIX_Y);
            this.commons.setStructure(this.graphMaps.getAdjacencyStructure(), this.STRUCTURE_X, this.STRUCTURE_Y, this.LINK_LENGTH);
            this.commons.adjustCanvasSize();
            this.earrSeqArray = this.graphMaps.getSeqArray();
            if (this.earrSeqArray != null) {
                this.earrSeqArray.setLocation(this.SEQ_ARRAY_X, this.SEQ_ARRAY_Y);
                if (this.earrSeqArray.getWidth() + this.SEQ_ARRAY_X > this.getSize().width) {
                    this.setSize(this.earrSeqArray.getWidth() + this.SEQ_ARRAY_X, this.getSize().height);
                }
            }
            this.graphMaps.removeAllAnimationRequests();
            this.repaint();
        }
    }
}
