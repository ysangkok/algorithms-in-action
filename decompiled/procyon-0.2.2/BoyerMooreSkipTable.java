import localization.*;
import java.awt.*;
import com.cim.AIA.*;

public class BoyerMooreSkipTable implements Drawable
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
    
    public BoyerMooreSkipTable(final int size) {
        super();
        this.location = new Point();
        this.infoStart = Messages.getString("BoyerMooreSkipTable.0");
        this.infoEnd = "] =";
        this.setLocation(0, 0);
        this.size = 27;
        this.initialise();
    }
    
    public void draw(final Graphics g) {
        if (this.noInfo) {
            this.reInit();
        }
        int xLoc = this.location.x;
        int yLoc = this.location.y;
        for (int i = 0; i < 13; ++i) {
            this.dataNode[i].setPosition(new Point(xLoc, yLoc + this.stringHeight));
            this.dataNode[i].draw(g);
            g.drawString("" + (char)(i + 65), xLoc, yLoc + this.stringHeight);
            xLoc = xLoc + this.XGap + this.nodeWidth;
        }
        yLoc = yLoc + this.stringHeight + this.nodeHeight + this.YGap;
        xLoc = this.location.x;
        for (int i = 13; i < 26; ++i) {
            this.dataNode[i].setPosition(new Point(xLoc, yLoc + this.stringHeight));
            this.dataNode[i].draw(g);
            g.drawString("" + (char)(i + 65), xLoc, yLoc + this.stringHeight);
            xLoc = xLoc + this.XGap + this.nodeWidth;
        }
        yLoc = yLoc + this.stringHeight + this.nodeHeight + this.YGap;
        xLoc = this.location.x;
        this.dataNode[26].setPosition(new Point(xLoc, yLoc + this.stringHeight));
        this.dataNode[26].draw(g);
        g.drawString(Messages.getString("BoyerMooreSkipTable.1"), xLoc, yLoc + this.stringHeight);
    }
    
    public void draw(final Graphics g, final Point pnt) {
        this.setLocation(pnt);
        this.draw(g);
    }
    
    public int get(int index) {
        index = this.translateIndex(index);
        return this.data[index];
    }
    
    public int getHeight() {
        if (this.noInfo) {
            this.reInit();
        }
        return this.stringHeight + this.nodeHeight + this.YGap + this.stringHeight + this.nodeHeight + this.YGap + this.stringHeight + this.nodeHeight;
    }
    
    public Point getLocation() {
        return this.location;
    }
    
    public int getWidth() {
        if (this.noInfo) {
            this.reInit();
        }
        return (this.XGap + this.nodeWidth) * 13;
    }
    
    public void highlight(int index) {
        index = this.translateIndex(index);
        this.dataNode[index].highlight();
    }
    
    private void initialise() {
        this.XGap = 5;
        this.YGap = 5;
        this.data = new int[this.size];
        this.dataNode = new Node[this.size];
        for (int i = 0; i < this.size; ++i) {
            this.dataNode[i] = new Node("", 0);
            this.data[i] = 0;
        }
        this.noInfo = true;
    }
    
    public void reInit() {
        this.stringHeight = BoyerMooreApplet.theBoyerMooreCanvas.getGraphics().getFontMetrics().getHeight();
        final Node temp = new Node("", 0);
        this.nodeWidth = temp.getWidth();
        this.nodeHeight = temp.getHeight();
        this.noInfo = false;
    }
    
    public void set(int index, final int theData) {
        index = this.translateIndex(index);
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
    
    private int translateIndex(int index) {
        if (index > 25 && index >= 65 && index <= 90) {
            index = index - 65;
        }
        if (index == 32) {
            index = 26;
        }
        return index;
    }
    
    public void unHighlight(int index) {
        index = this.translateIndex(index);
        this.dataNode[index].unHighlight();
    }
    
    public void unHighlightAll() {
        for (int i = 0; i < this.size; ++i) {
            this.dataNode[i].unHighlight();
        }
    }
}
