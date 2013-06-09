import localization.*;
import java.awt.*;
import com.cim.AIA.*;
import aia.graph.common.*;

public class MSTKGraphMapsCanvasExt extends GraphMapsCanvasExt implements GraphDialogEventHandler
{
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
    public static int QUEUE_X;
    public static int QUEUE_Y;
    protected int SAMP_QUEUE_MEM_X;
    protected int SAMP_QUEUE_MEM_Y;
    protected Font normalFont;
    protected Color textColor;
    public static final int NODE_WIDTH = 20;
    public static final int NODE_HEIGHT = 20;
    
    MSTKGraphMapsCanvasExt() {
        super();
        this.commons = null;
        this.MATRIX_X = 50;
        this.MATRIX_Y = 145;
        this.STRUCTURE_X = 30;
        this.STRUCTURE_Y = 145;
        this.LINK_LENGTH = 20;
        this.EDGES_ADDED_X = 30;
        this.EDGES_ADDED_Y = 90;
        this.earrQueue = null;
        this.SAMP_QUEUE_MEM_X = 180;
        this.SAMP_QUEUE_MEM_Y = 7;
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
    
    public void processDialogEvent(final String sourceDialog, final String value) {
        this.commons.processDialogEvent(sourceDialog, value);
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
        this.drawCurrentEdge(g);
        this.drawQueue(g);
        this.drawEdgesAddedCounter(g);
    }
    
    private void drawCurrentEdge(final Graphics g) {
        if (this.graphMaps == null) {
            return;
        }
        final TwinNode node = this.graphMaps.getCurrentEdgeNode();
        if (node != null) {
            final FontMetrics fm = g.getFontMetrics();
            node.setPosition(new Point(MSTKGraphMapsCanvasExt.QUEUE_X - 50, MSTKGraphMapsCanvasExt.QUEUE_Y));
            node.draw(g);
        }
    }
    
    public Color getTextColor() {
        return this.textColor;
    }
    
    public void drawMatrixAssociatedElements(final Graphics g, final ElementArray elementArray, final int index) {
    }
    
    public void drawStructureAssociatedElements(final Graphics g, final GraphMapsNode node, final GraphMapsNode rootNode) {
    }
    
    public void setStructureNodeAppearance(final GraphMapsNode node, final GraphMapsNode rootNode) {
        if (node.getIntValue() != -1) {
            node.setBackgroundColor(this.graphMaps.getUnvisitedNodeColor());
            node.setTextColor(this.graphMaps.getTextColor());
        }
        else {
            node.setBackgroundColor(Color.white);
            node.setTextColor(Color.white);
        }
    }
    
    private void drawQueue(final Graphics g) {
        final FontMetrics fm = g.getFontMetrics();
        g.setColor(Color.black);
        g.drawString(Messages.getString("MSTKGraphMapsCanvasExt.0"), MSTKGraphMapsCanvasExt.QUEUE_X, MSTKGraphMapsCanvasExt.QUEUE_Y + 20 - fm.getMaxDescent());
        g.drawString(Messages.getString("MSTKGraphMapsCanvasExt.1"), MSTKGraphMapsCanvasExt.QUEUE_X + fm.stringWidth(Messages.getString("MSTKGraphMapsCanvasExt.2")), MSTKGraphMapsCanvasExt.QUEUE_Y + 20 + fm.getHeight());
        if (this.earrQueue != null) {
            this.earrQueue.draw(g);
            if (this.graphMaps.isOverFlow()) {
                g.setColor(Color.black);
                g.drawString("...", MSTKGraphMapsCanvasExt.QUEUE_X + 100 + this.earrQueue.getWidth() - this.earrQueue.getElementWidth() + 10, MSTKGraphMapsCanvasExt.QUEUE_Y + 20);
            }
        }
    }
    
    private void drawEdgesAddedCounter(final Graphics g) {
        final FontMetrics fm = g.getFontMetrics();
        g.setColor(Color.black);
        g.drawString(Messages.getString("MSTKGraphMapsCanvasExt.4"), this.EDGES_ADDED_X, this.EDGES_ADDED_Y + fm.getHeight());
        final int edgesAdded = this.graphMaps.getEdgesAdded();
        final MSTKGraphMaps graphMaps = this.graphMaps;
        if (edgesAdded != -10) {
            g.drawString(Integer.toString(this.graphMaps.getEdgesAdded()), this.EDGES_ADDED_X + fm.stringWidth(Messages.getString("MSTKGraphMapsCanvasExt.5")), this.EDGES_ADDED_Y + fm.getHeight());
        }
    }
    
    public void processRepaintEvent(final RepaintEvent e) {
        final int matrix_width = 0;
        if (e.paramObj != null) {
            this.drawables.removeAllElements();
            this.graphMaps = (MSTKGraphMaps)((MSTKGraphMaps)e.paramObj);
            if (this.commons == null) {
                this.commons = new MSTKGraphCanvasCommonExt(this, this.graphMaps);
            }
            this.removeAllSelectables();
            this.commons.setMatrix(this.graphMaps.getAdjacencyMatrix(), this.MATRIX_X, this.MATRIX_Y);
            this.commons.setStructure(this.graphMaps.getAdjacencyStructure(), this.STRUCTURE_X, this.STRUCTURE_Y, this.LINK_LENGTH);
            this.earrQueue = this.graphMaps.getQueue();
            if (this.earrQueue != null) {
                this.earrQueue.setLocation(MSTKGraphMapsCanvasExt.QUEUE_X + 100, MSTKGraphMapsCanvasExt.QUEUE_Y);
            }
            this.commons.adjustCanvasSize();
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
    
    static {
        MSTKGraphMapsCanvasExt.QUEUE_X = 80;
        MSTKGraphMapsCanvasExt.QUEUE_Y = 40;
    }
}
