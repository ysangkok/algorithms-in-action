import java.awt.event.*;
import com.cim.AIA.*;
import java.awt.*;
import aia.graph.common.*;

public class MinSpanTreePrimGraphMapsCanvas extends GraphMapsCanvas implements GraphDialogEventHandler
{
    protected GraphMaps graphMaps;
    protected MinSpanTreePrimGraphCanvasCommon commons;
    protected Font normalFont;
    protected Color textColor;
    public static final int GRAPH_X = 30;
    public static final int GRAPH_Y = 330;
    public static final int NODE_WIDTH = 20;
    public static final int NODE_HEIGHT = 20;
    
    MinSpanTreePrimGraphMapsCanvas() {
        super();
        this.commons = null;
        this.tweens = new MultipleTween(this);
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
    
    public void processMouseMotionEvent(final MouseEvent evt) {
        this.commons.mouseMoved(evt.getX(), evt.getY());
    }
    
    public void mouseReleased(final MouseEvent evt) {
        this.commons.mouseReleased();
    }
    
    public void mousePressed(final MouseEvent evt) {
        this.commons.mousePressed(evt.getX(), evt.getY());
        super.mousePressed(evt);
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
        this.commons.drawLinks(g);
        this.commons.drawNodes(g);
    }
    
    public Color getTextColor() {
        return this.textColor;
    }
    
    public void drawLinkAssociatedElements(final Graphics g, final Link link, final Line line) {
    }
    
    public void drawNodeAssociatedElements(final Graphics g, final GraphMapsNode node) {
        if (this.graphMaps == null) {
            return;
        }
        final Font oldFont = g.getFont();
        g.setFont(new Font("Arial", 1, 10));
        final int px = node.getX();
        final int py = node.getY();
        final int pgx = this.commons.getGraphX();
        final int pgy = this.commons.getGraphY();
        final FontMetrics fm = g.getFontMetrics();
        Line line;
        if (this.graphMaps.getKMarker() == node.getIntValue()) {
            g.setColor(Color.black);
            g.drawString("k", pgx, pgy);
            g.setColor(Color.lightGray);
            line = new Line(pgx + fm.stringWidth("k") / 2, pgy, px, py);
            line.setColor(Color.lightGray);
            line.showArrowHead(true);
            line.setDistanceFromStartForArrowHead(-3);
            line.draw(g);
        }
        if (this.graphMaps.getAdjNode() != null && this.graphMaps.getAdjNode().getTo() == node.getIntValue()) {
            g.setColor(Color.black);
            g.drawString("adj_node", px - fm.stringWidth("adj_node") - 10, py);
            g.setColor(Color.lightGray);
            line = new Line(px - 10, py, px, py);
            line.setColor(Color.lightGray);
            line.showArrowHead(true);
            line.setDistanceFromStartForArrowHead(-3);
            line.draw(g);
        }
        g.setFont(oldFont);
    }
    
    public void processRepaintEvent(final RepaintEvent e) {
        if (e.paramObj != null) {
            this.drawables.removeAllElements();
            this.graphMaps = (GraphMaps)((GraphMaps)e.paramObj);
            if (this.commons == null) {
                this.commons = new MinSpanTreePrimGraphCanvasCommon(this, this.graphMaps);
            }
            this.removeAllSelectables();
            this.commons.setNodes(this.graphMaps.getNodes(), 30);
            this.commons.setLinks(this.graphMaps.getLinks());
            this.commons.adjustCanvasSize();
            this.graphMaps.removeAllAnimationRequests();
            this.repaint();
        }
    }
}
