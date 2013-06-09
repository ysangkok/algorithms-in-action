import java.awt.event.*;
import com.cim.AIA.*;
import java.util.*;
import java.awt.*;
import aia.graph.common.*;

public class TransclosureGraphMapsCanvas extends GraphMapsCanvas implements GraphDialogEventHandler
{
    protected TransclosureGraphMaps graphMaps;
    protected TransclosureGraphCanvasCommon commons;
    protected Font normalFont;
    protected Color textColor;
    public static final int GRAPH_X = 30;
    public static final int GRAPH_Y = 30;
    public static final int NODE_WIDTH = 20;
    public static final int NODE_HEIGHT = 20;
    protected Vector<E> vecNodes;
    protected Line m_linkJustAdded;
    protected boolean m_linkJustAddedVisible;
    
    TransclosureGraphMapsCanvas() {
        super();
        this.commons = null;
        this.vecNodes = null;
        this.m_linkJustAdded = new Line(0, 0, 0, 0);
        this.m_linkJustAddedVisible = false;
        this.tweens = new MultipleTween(this);
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
        if (this.m_linkJustAddedVisible) {
            this.m_linkJustAdded.draw(g);
        }
        this.commons.drawNodes(g);
    }
    
    private GraphMapsNode findNode(final int node_key) {
        for (int i = 0; i < this.vecNodes.size(); ++i) {
            final GraphMapsNode node = (GraphMapsNode)this.vecNodes.elementAt(i);
            if (node.getIntValue() == node_key) {
                return node;
            }
        }
        return null;
    }
    
    public void drawLinkAssociatedElements(final Graphics g, final Link link, final Line line) {
    }
    
    public void drawNodeAssociatedElements(final Graphics g, final GraphMapsNode node) {
        String strNodeLabel = null;
        if (this.graphMaps == null) {
            return;
        }
        final Font oldFont = g.getFont();
        g.setFont(new Font("Arial", 1, 10));
        final int px = node.getX();
        final int py = node.getY();
        final int pw = node.getWidth();
        final int pgx = this.commons.getGraphX();
        final int pgy = this.commons.getGraphY();
        final FontMetrics fm = g.getFontMetrics();
        if (this.graphMaps.getX() == node.getIntValue()) {
            strNodeLabel = "x";
        }
        if (this.graphMaps.getY() == node.getIntValue()) {
            if (strNodeLabel != null) {
                strNodeLabel = strNodeLabel + ",y";
            }
            else {
                strNodeLabel = "y";
            }
        }
        if (this.graphMaps.getJ() == node.getIntValue()) {
            if (strNodeLabel != null) {
                strNodeLabel = strNodeLabel + ",j";
            }
            else {
                strNodeLabel = "j";
            }
        }
        if (strNodeLabel != null) {
            g.setColor(Color.black);
            g.drawString(strNodeLabel, px - fm.stringWidth(strNodeLabel) - 10, py);
            g.setColor(Color.lightGray);
            final Line line = new Line(px - 10, py, px, py);
            line.setColor(Color.lightGray);
            line.showArrowHead(true);
            line.setDistanceFromStartForArrowHead(-3);
            line.draw(g);
        }
    }
    
    public void processRepaintEvent(final RepaintEvent e) {
        if (e.paramObj != null) {
            this.drawables.removeAllElements();
            this.m_linkJustAddedVisible = false;
            this.graphMaps = (TransclosureGraphMaps)((TransclosureGraphMaps)e.paramObj);
            if (this.commons == null) {
                this.commons = new TransclosureGraphCanvasCommon(this, this.graphMaps);
            }
            this.removeAllSelectables();
            this.vecNodes = this.graphMaps.getNodes();
            this.commons.setNodes(this.vecNodes, 30);
            this.vecNodes = this.commons.getNodes();
            this.commons.setLinks(this.graphMaps.getLinks());
            this.commons.adjustCanvasSize();
            final MultipleTween multipleTween = this.graphMaps.generateTweens(this, this.m_linkJustAdded, this.vecNodes, this.numberOfTweenSteps);
            if (multipleTween != null) {
                this.addTween(multipleTween);
            }
            if (this.tweens.getNumberOfTweens() > 0) {
                this.m_linkJustAddedVisible = true;
                this.tweens.run();
            }
            this.graphMaps.removeAllAnimationRequests();
            this.repaint();
        }
    }
}
