import com.cim.AIA.*;
import java.util.*;
import java.awt.*;
import aia.graph.common.*;

public class TransclosureGraphMapsCanvasExt extends GraphMapsCanvasExt implements GraphDialogEventHandler
{
    protected TransclosureGraphMaps graphMaps;
    protected TransclosureGraphCanvasCommonExt commons;
    protected int MATRIX_X;
    protected int MATRIX_Y;
    protected Font normalFont;
    protected Color textColor;
    public static final int NODE_WIDTH = 20;
    public static final int NODE_HEIGHT = 20;
    protected Vector<E> vecNodes;
    
    TransclosureGraphMapsCanvasExt() {
        super();
        this.commons = null;
        this.MATRIX_X = 50;
        this.MATRIX_Y = 50;
        this.vecNodes = null;
        this.tweens = new MultipleTween(this);
        this.addSelectionListener(new DragSelectionListener(this));
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
        this.drawLabel(g);
    }
    
    private void drawLabel(final Graphics g) {
        if (this.commons.getMatrix() == null || this.graphMaps == null) {
            return;
        }
        if (!this.graphMaps.getMatrixShowing()) {
            return;
        }
        final Font oldFont = g.getFont();
        g.setFont(new Font("Arial", 1, 8));
        final FontMetrics fm = g.getFontMetrics();
        g.setColor(Color.black);
        final int x = this.graphMaps.getX();
        final int y = this.graphMaps.getY();
        final int j = this.graphMaps.getJ();
        if (x == this.graphMaps.getNumberOfNodes() && y == this.graphMaps.getNumberOfNodes()) {
            g.drawString("x  y", this.MATRIX_X + this.commons.getMatrixWidth() + 5, this.MATRIX_Y + 20 * x + (20 + fm.getHeight()) / 2);
        }
        else {
            g.drawString("x", this.MATRIX_X + this.commons.getMatrixWidth() + 5, this.MATRIX_Y + 20 * x + (20 + fm.getHeight()) / 2);
            g.drawString("y", this.MATRIX_X + 20 * y + (20 - fm.stringWidth("y")) / 2, this.MATRIX_Y + this.commons.getMatrixHeight() + fm.getHeight());
        }
        if (this.graphMaps.isInSecondLoop()) {
            if (j != y) {
                g.drawString("j", this.MATRIX_X + 20 * j + (20 - fm.stringWidth("j")) / 2, this.MATRIX_Y + this.commons.getMatrixHeight() + fm.getHeight());
            }
            else {
                g.drawString("y", this.MATRIX_X + 20 * j + (20 - fm.stringWidth("y")) / 2, this.MATRIX_Y + this.commons.getMatrixHeight() + fm.getHeight());
                g.drawString("j", this.MATRIX_X + 20 * j + (20 - fm.stringWidth("j")) / 2, this.MATRIX_Y + this.commons.getMatrixHeight() + 2 * fm.getHeight());
            }
            if (x != y) {
                g.drawString("y", this.MATRIX_X + this.commons.getMatrixWidth() + 5, this.MATRIX_Y + 20 * y + (20 + fm.getHeight()) / 2);
            }
            else {
                g.drawString("x  y", this.MATRIX_X + this.commons.getMatrixWidth() + 5, this.MATRIX_Y + 20 * y + (20 + fm.getHeight()) / 2);
            }
        }
        g.setFont(oldFont);
    }
    
    public void drawMatrixAssociatedElements(final Graphics g, final ElementArray elementArray, final int index) {
    }
    
    public void setStructureNodeAppearance(final GraphMapsNode node, final GraphMapsNode rootNode) {
    }
    
    public void drawStructureAssociatedElements(final Graphics g, final GraphMapsNode node, final GraphMapsNode rootNode) {
    }
    
    public void processRepaintEvent(final RepaintEvent e) {
        if (e.paramObj != null) {
            this.drawables.removeAllElements();
            this.graphMaps = (TransclosureGraphMaps)((TransclosureGraphMaps)e.paramObj);
            if (this.commons == null) {
                this.commons = new TransclosureGraphCanvasCommonExt(this, this.graphMaps);
            }
            this.removeAllSelectables();
            this.commons.setMatrix(this.graphMaps.getAdjacencyMatrix(), this.MATRIX_X, this.MATRIX_Y);
            this.commons.adjustCanvasSize();
            this.repaint();
        }
    }
}
