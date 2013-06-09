import java.awt.*;
import com.cim.AIA.*;

public class BoyerMooreNextTable implements Drawable
{
    protected int size;
    protected int[] data;
    protected Node[] dataNode;
    protected Point location;
    protected int XGap;
    protected int YGap;
    protected int stringWidth;
    protected int stringHeight;
    protected int nodeWidth;
    protected int nodeHeight;
    protected int trueHeight;
    protected String infoStart;
    protected String infoEnd;
    protected boolean noInfo;
    
    public BoyerMooreNextTable(final int size) {
        super();
        this.location = new Point();
        this.infoStart = "nextTable [";
        this.infoEnd = "] =";
        this.noInfo = true;
        this.setLocation(0, 0);
        this.size = size;
        this.initialise();
    }
    
    public void draw(final Graphics g) {
        if (this.noInfo) {
            this.reInit();
        }
        final int xLoc = this.location.x;
        int yLoc = this.location.y;
        for (int i = 0; i < this.size; ++i) {
            g.drawString(this.infoStart + "" + i + this.infoEnd, xLoc, yLoc + this.stringHeight);
            this.dataNode[i].setPosition(new Point(xLoc + this.stringWidth + this.XGap, yLoc));
            this.dataNode[i].draw(g);
            yLoc = yLoc + this.trueHeight + this.YGap;
        }
    }
    
    public void draw(final Graphics g, final Point pnt) {
        this.setLocation(pnt);
        this.draw(g);
    }
    
    public int get(final int index) {
        return this.data[index];
    }
    
    public int getHeight() {
        if (this.noInfo) {
            this.reInit();
        }
        return (this.trueHeight + this.YGap) * this.size;
    }
    
    public Point getLocation() {
        return this.location;
    }
    
    public int getWidth() {
        if (this.noInfo) {
            this.reInit();
        }
        return this.stringWidth + this.XGap + this.nodeWidth;
    }
    
    public void highlight(final int index) {
        this.dataNode[index].highlight();
    }
    
    private void initialise() {
        this.XGap = 5;
        this.YGap = 5;
        this.data = new int[this.size];
        this.dataNode = new Node[this.size];
        for (int i = 0; i < this.size; ++i) {
            this.dataNode[i] = new Node("", 0);
        }
        this.noInfo = true;
    }
    
    public void reInit() {
        this.stringHeight = BoyerMooreApplet.theBoyerMooreCanvas.getGraphics().getFontMetrics().getHeight();
        this.stringWidth = BoyerMooreApplet.theBoyerMooreCanvas.getGraphics().getFontMetrics().stringWidth(this.infoStart + "" + this.size + this.infoEnd);
        final Node temp = new Node("", 0);
        this.nodeWidth = temp.getWidth();
        this.nodeHeight = temp.getHeight();
        this.trueHeight = Math.max(this.nodeHeight, this.stringHeight);
        this.noInfo = false;
    }
    
    public void set(final int index, final int theData) {
        this.data[index] = theData;
        this.dataNode[index] = new Node("" + theData, 0);
    }
    
    public void setLocation(final int xPos, final int yPos) {
        this.location.x = xPos;
        this.location.y = yPos;
    }
    
    public void setLocation(final Point newPnt) {
        this.location = newPnt;
    }
    
    public void setPosition(final int xPos, final int yPos) {
        this.setLocation(xPos, yPos);
    }
    
    public void unHighlight(final int index) {
        this.dataNode[index].unHighlight();
    }
    
    public void unHighlightAll() {
        for (int i = 0; i < this.size; ++i) {
            this.dataNode[i].unHighlight();
        }
    }
}
