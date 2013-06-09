import localization.*;
import java.awt.*;
import com.cim.AIA.*;

public class AlignmentKey implements Drawable
{
    protected final int X_GAP = 10;
    protected final int numOfEntries = 6;
    protected final int numOfColumns = 2;
    protected final int numPerColumn;
    protected Point location;
    protected String[] data;
    protected Node[] dataNode;
    protected int[] runningWidth;
    
    public AlignmentKey() {
        super();
        this.X_GAP = 10;
        this.numOfEntries = 6;
        this.numOfColumns = 2;
        this.numPerColumn = (int)Math.ceil(3.0);
        this.location = new Point();
        this.data = new String[6];
        this.dataNode = new Node[6];
        this.runningWidth = new int[2];
        this.setLocation(0, 0);
        this.data[0] = new String(Messages.getString("AlignmentKey.0"));
        this.dataNode[0] = new Node("", 0);
        this.dataNode[0].setBackgroundColor(AlignmentNode.DEFAULT_ARRAY_DISABLE);
        this.data[1] = new String(Messages.getString("AlignmentKey.2"));
        this.dataNode[1] = new Node("", 0);
        this.dataNode[1].setBackgroundColor(Alignment.DEFAULT_CURRENT_COLOR);
        this.dataNode[1].setRingColor(Alignment.HIGHLIGHT_RING_COLOR);
        this.data[2] = new String(Messages.getString("AlignmentKey.4"));
        this.dataNode[2] = new Node("", 0);
        this.dataNode[2].setBackgroundColor(Alignment.IRRELEVANT_COLOR);
        this.dataNode[2].setRingColor(Alignment.HIGHLIGHT_RING_COLOR);
        this.data[3] = new String(Messages.getString("AlignmentKey.6"));
        this.dataNode[3] = new Node("", 0);
        this.dataNode[3].setBackgroundColor(Alignment.DEFAULT_NODE_COLOR);
        this.data[4] = new String(Messages.getString("AlignmentKey.8"));
        this.dataNode[4] = new Node("", 0);
        this.dataNode[4].setBackgroundColor(Alignment.DEFAULT_SELECTED_COLOR);
        this.data[5] = new String(Messages.getString("AlignmentKey.10"));
        this.dataNode[5] = new Node("", 0);
        this.dataNode[5].setBackgroundColor(Alignment.DEFAULT_HIGHLIGHT_COLOR);
    }
    
    public void draw(final Graphics g) {
        int counter = 0;
        int xPos = this.location.x;
        g.setColor(Color.black);
        for (int i = 0; i < 2; ++i) {
            int yPos = this.location.y;
            int j = 0;
            while (j < this.numPerColumn) {
                if (counter >= 6) {
                    break;
                }
                this.dataNode[counter].setPosition(new Point(xPos, yPos));
                this.dataNode[counter].draw(g);
                g.drawString(this.data[counter], xPos + 10 + this.dataNode[counter].getWidth(), yPos + g.getFontMetrics().getAscent() / 2 + this.dataNode[counter].getHeight() / 2);
                yPos = yPos + this.dataNode[counter].getHeight();
                ++counter;
                ++j;
            }
            xPos = xPos + 10 + this.runningWidth[i];
        }
    }
    
    public void draw(final Graphics g, final Point pnt) {
        this.setLocation(pnt);
        this.draw(g);
    }
    
    public int getHeight() {
        final Node testNode = new Node("", 0);
        return testNode.getHeight() * this.numPerColumn;
    }
    
    public Point getLocation() {
        return this.location;
    }
    
    public int getWidth() {
        int counter = 0;
        int currentWidth = 0;
        for (int i = 0; i < 2; ++i) {
            int j = 0;
            while (j < this.numPerColumn) {
                if (counter >= 6) {
                    break;
                }
                final int tempWidth = this.dataNode[counter].getWidth() + AlignmentApplet.theAlignmentCanvas.getGraphics().getFontMetrics().stringWidth(this.data[counter]) + 10;
                if (tempWidth > this.runningWidth[i]) {
                    this.runningWidth[i] = tempWidth;
                }
                ++counter;
                ++j;
            }
            currentWidth = currentWidth + this.runningWidth[i];
        }
        return currentWidth;
    }
    
    public void setLocation(final int xPos, final int yPos) {
        this.location.x = xPos;
        this.location.y = yPos;
    }
    
    public void setLocation(final Point newPnt) {
        this.location = newPnt;
    }
}
