import java.util.*;
import java.awt.*;
import com.cim.AIA.*;

public class AlignmentMinimum implements Drawable
{
    protected final int DEFAULT_Y_GAP = 5;
    protected final int DEFAULT_X_GAP = 5;
    protected final int DEFAULT_LABEL_X_GAP = 2;
    protected final int DEFAULT_LINE_WIDTH = 4;
    protected final Color DEFAULT_NODE_COLOR;
    protected final Color DEFAULT_HIGHLIGHT_COLOR;
    protected String theLabel;
    protected Vector<Node> theData;
    protected Vector<String> theString;
    protected int yGap;
    protected int xGap;
    protected int labelXGap;
    protected int lineWidth;
    protected Color nodeColor;
    protected Color highlightColor;
    protected int nodeWidth;
    protected int nodeHeight;
    protected int labelWidth;
    protected Point location;
    protected Point topLine;
    protected Point bottomLine;
    
    public AlignmentMinimum() {
        this("");
    }
    
    public AlignmentMinimum(final String label) {
        super();
        this.DEFAULT_Y_GAP = 5;
        this.DEFAULT_X_GAP = 5;
        this.DEFAULT_LABEL_X_GAP = 2;
        this.DEFAULT_LINE_WIDTH = 4;
        this.DEFAULT_NODE_COLOR = new Color(0, 200, 0);
        this.DEFAULT_HIGHLIGHT_COLOR = Color.green;
        this.yGap = 5;
        this.xGap = 5;
        this.labelXGap = 2;
        this.lineWidth = 4;
        this.nodeColor = this.DEFAULT_NODE_COLOR;
        this.highlightColor = this.DEFAULT_HIGHLIGHT_COLOR;
        this.nodeWidth = 0;
        this.nodeHeight = 0;
        this.location = new Point();
        this.topLine = new Point();
        this.bottomLine = new Point();
        this.theData = new Vector();
        this.theString = new Vector();
        this.theLabel = label;
    }
    
    public void add() {
        final Node tempNode = new Node("", 0);
        this.nodeWidth = tempNode.getWidth();
        final String tempStr = "";
        this.add(tempNode, tempStr);
    }
    
    public void add(final int n, final int x) {
        Node tempNode;
        String tempStr;
        if (n == -5000) {
            tempNode = new Node(new String("-Inf"), 0);
            if (x >= 0) {
                tempStr = "+ " + x + " = -Inf";
            }
            else {
                tempStr = "- " + -x + " = -Inf";
            }
        }
        else if (n == 5000) {
            tempNode = new Node(new String("Inf"), 0);
            if (x >= 0) {
                tempStr = "+ " + x + " = Inf";
            }
            else {
                tempStr = "- " + -x + " = Inf";
            }
        }
        else {
            tempNode = new Node(new Integer(n), 0);
            if (x >= 0) {
                tempStr = "+ " + x + " = " + (x + n);
            }
            else {
                tempStr = "- " + -x + " = " + (x + n);
            }
        }
        this.nodeWidth = tempNode.getWidth();
        this.nodeHeight = tempNode.getHeight();
        this.add(tempNode, tempStr);
    }
    
    public void add(final Node n, final String str) {
        n.setSequence(0);
        n.setBackgroundColor(this.nodeColor);
        n.setHighlightColor(this.highlightColor);
        this.theData.addElement(n);
        this.theString.addElement(str);
    }
    
    public void draw(final Graphics g) {
        this.setLabelWidth(g);
        this.positionNodes();
        this.drawNodes(g);
        this.drawStrings(g);
        this.drawLines(g);
        this.drawLabel(g);
    }
    
    public void draw(final Graphics g, final Point pnt) {
        this.setLocation(pnt);
        this.draw(g);
    }
    
    protected void drawLabel(final Graphics g) {
        if (this.theData.size() <= 0) {
            return;
        }
        g.drawString(this.theLabel, this.location.x, this.topLine.y + (this.bottomLine.y - this.topLine.y) / 2 + g.getFontMetrics().getAscent() / 2);
    }
    
    protected void drawLines(final Graphics g) {
        int xPos = this.location.x;
        xPos = xPos + this.labelWidth + this.labelXGap;
        if (this.theData.size() > 0) {
            Node tempNode = (Node)this.theData.elementAt(0);
            this.topLine.y = tempNode.getY();
            this.topLine.y = this.topLine.y + tempNode.getHeight() / 2;
            tempNode = (Node)this.theData.elementAt(this.theData.size() - 1);
            this.bottomLine.y = tempNode.getY();
            this.bottomLine.y = this.bottomLine.y + tempNode.getHeight() / 2;
            g.drawLine(xPos, this.topLine.y + (this.bottomLine.y - this.topLine.y) / 2, xPos + this.lineWidth, this.topLine.y + (this.bottomLine.y - this.topLine.y) / 2);
            xPos = xPos + this.lineWidth;
            g.drawLine(xPos, this.topLine.y, xPos, this.bottomLine.y);
            for (int i = 0; i < this.theData.size(); ++i) {
                tempNode = (Node)this.theData.elementAt(i);
                final int yPos = tempNode.getY() + tempNode.getHeight() / 2;
                g.drawLine(xPos, yPos, xPos + this.lineWidth, yPos);
            }
        }
    }
    
    protected void drawNodes(final Graphics g) {
        for (int i = 0; i < this.theData.size(); ++i) {
            final Node tempNode = (Node)this.theData.elementAt(i);
            if (tempNode.getIdentifier() == 1) {
                tempNode.highlight();
            }
            else {
                tempNode.unHighlight();
            }
            tempNode.draw(g);
        }
    }
    
    protected void drawStrings(final Graphics g) {
        for (int i = 0; i < this.theString.size(); ++i) {
            final Node tempNode = (Node)this.theData.elementAt(i);
            final String tempString = (String)this.theString.elementAt(i);
            final int xPos = tempNode.getX();
            final int yPos = tempNode.getY() + tempNode.getHeight() / 2;
            if (tempNode.getIdentifier() == 1) {
                g.setColor(this.highlightColor);
                g.fillRect(xPos + this.nodeWidth + this.xGap, yPos - g.getFontMetrics().getAscent() / 2, g.getFontMetrics().stringWidth(tempString), g.getFontMetrics().getAscent());
            }
            g.setColor(Color.black);
            g.drawString(tempString, xPos + this.nodeWidth + this.xGap, yPos + g.getFontMetrics().getAscent() / 2);
        }
    }
    
    public int getHeight() {
        return this.nodeHeight * this.theData.size() + this.yGap * (this.theData.size() - 1);
    }
    
    public Point getLocation() {
        return this.location;
    }
    
    public int getSize() {
        return this.theData.size();
    }
    
    public void highlight(final int index) {
        final Node tempNode = (Node)this.theData.elementAt(index);
        tempNode.setSequence(1);
    }
    
    protected void positionNodes() {
        final int xPos = this.location.x;
        final int yPos = this.location.y;
        for (int i = 0; i < this.theData.size(); ++i) {
            final Node tempNode = (Node)this.theData.elementAt(i);
            tempNode.setX(xPos + this.labelWidth + this.labelXGap + this.lineWidth + this.lineWidth + this.xGap);
            tempNode.setY(yPos + i * (this.yGap + tempNode.getHeight()));
        }
    }
    
    public void set(final int index) {
        final Node tempNode = new Node("", 0);
        this.nodeWidth = tempNode.getWidth();
        final String tempStr = "";
        this.set(index, tempNode, tempStr);
    }
    
    public void set(final int index, final int n, final int x) {
        Node tempNode;
        String tempStr;
        if (n == -5000) {
            tempNode = new Node(new String("-Inf"), 0);
            if (x >= 0) {
                tempStr = "+ " + x + " = -Inf";
            }
            else {
                tempStr = "- " + -x + " = -Inf";
            }
        }
        else if (n == 5000) {
            tempNode = new Node(new String("Inf"), 0);
            if (x >= 0) {
                tempStr = "+ " + x + " = Inf";
            }
            else {
                tempStr = "- " + -x + " = Inf";
            }
        }
        else {
            tempNode = new Node(new Integer(n), 0);
            if (x >= 0) {
                tempStr = "+ " + x + " = " + (x + n);
            }
            else {
                tempStr = "- " + -x + " = " + (x + n);
            }
        }
        this.nodeWidth = tempNode.getWidth();
        this.nodeHeight = tempNode.getHeight();
        this.set(index, tempNode, tempStr);
    }
    
    public void set(final int index, final Node n, final String str) {
        n.setSequence(0);
        n.setBackgroundColor(this.nodeColor);
        n.setHighlightColor(this.highlightColor);
        this.theData.setElementAt(n, index);
        this.theString.setElementAt(str, index);
    }
    
    protected void setLabelWidth(final Graphics g) {
        this.labelWidth = g.getFontMetrics().stringWidth(this.theLabel);
    }
    
    public void setLocation(final int xPos, final int yPos) {
        this.location.x = xPos;
        this.location.y = yPos;
    }
    
    public void setLocation(final Point newPnt) {
        this.location = newPnt;
    }
    
    public void unHighlight() {
        for (int i = 0; i < this.theData.size(); ++i) {
            final Node tempNode = (Node)this.theData.elementAt(i);
            tempNode.setSequence(0);
        }
    }
    
    public void unHighlight(final int index) {
        final Node tempNode = (Node)this.theData.elementAt(index);
        tempNode.setSequence(0);
    }
}
