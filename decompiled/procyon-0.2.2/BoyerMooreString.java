import java.awt.*;
import com.cim.AIA.*;

public class BoyerMooreString implements Drawable, Moveable
{
    protected String theString;
    protected int XGap;
    protected boolean needPosition;
    protected Point location;
    protected int length;
    protected String theLabel;
    protected boolean showLabel;
    protected boolean highLabel;
    protected boolean notDrawnYet;
    protected int labelIndex;
    protected Color labelColor;
    protected int arrowLabelIndex;
    protected int arrowLabelTarget;
    protected int xArrowIndex;
    protected int xArrowTarget;
    protected Color highlightColor;
    protected boolean[] highlightTable;
    
    public BoyerMooreString(final String str) {
        super();
        this.location = new Point();
        this.labelColor = Color.red;
        this.highlightColor = Color.green;
        this.theString = str;
        this.initialise();
    }
    
    public void draw(final Graphics g) {
        int xLoc = this.location.x;
        final int yLoc = this.location.y;
        final FontMetrics theFM = g.getFontMetrics();
        if (this.notDrawnYet) {
            this.notDrawnYet = false;
            this.xArrowIndex = this.xArrowIndex + 100;
        }
        this.xArrowTarget = xLoc;
        this.xArrowTarget = this.xArrowTarget + this.XGap * this.arrowLabelTarget + theFM.charWidth('F') / 2;
        Color saveColor;
        for (int counter = 0; counter < this.theString.length(); ++counter) {
            final char ch = this.theString.charAt(counter);
            final int temp = (this.XGap - theFM.charWidth(ch)) / 2;
            if (this.highlightTable[counter]) {
                saveColor = g.getColor();
                g.setColor(this.highlightColor);
                g.fillRect(xLoc + temp, yLoc - g.getFontMetrics().getHeight(), g.getFontMetrics().charWidth(ch), g.getFontMetrics().getHeight());
                g.setColor(saveColor);
            }
            g.drawString("" + ch, xLoc + temp, yLoc);
            if (this.showLabel && this.labelIndex == counter) {
                saveColor = g.getColor();
                g.setColor(this.labelColor);
                if (this.highLabel) {
                    g.drawString(this.theLabel + " = " + this.labelIndex, xLoc + temp, yLoc - this.getHeight());
                }
                else {
                    g.drawString(this.theLabel + " = " + this.labelIndex, xLoc + temp, yLoc + this.getHeight());
                }
                g.setColor(saveColor);
            }
            xLoc = xLoc + this.XGap;
        }
        if (this.arrowLabelTarget != -1) {
            saveColor = g.getColor();
            g.setColor(this.labelColor);
            if (this.highLabel) {
                final Line arrLine = new Line(this.xArrowIndex, yLoc - this.getHeight() * 2, this.xArrowTarget + theFM.charWidth('F') / 2, yLoc - this.getHeight());
                g.drawString(this.theLabel + " = " + this.arrowLabelTarget, this.xArrowIndex + theFM.charWidth('F') / 2, yLoc - this.getHeight() * 2);
                arrLine.showArrowHead(true);
                arrLine.setDistanceFromStartForArrowHead(arrLine.getLength());
                arrLine.draw(g);
            }
            else {
                final Line arrLine = new Line(this.xArrowIndex + theFM.charWidth('F') / 2, yLoc + this.getHeight() * 1, this.xArrowTarget + theFM.charWidth('F') / 2, yLoc);
                g.drawString(this.theLabel + " = " + this.arrowLabelTarget, this.xArrowIndex + theFM.charWidth('F') / 2, yLoc + this.getHeight() * 2);
                arrLine.showArrowHead(true);
                arrLine.setDistanceFromStartForArrowHead(arrLine.getLength());
                arrLine.draw(g);
            }
            g.setColor(saveColor);
        }
    }
    
    public void draw(final Graphics g, final Point pnt) {
        this.setLocation(pnt);
        this.draw(g);
    }
    
    public int getHeight() {
        return BoyerMooreApplet.theBoyerMooreCanvas.getGraphics().getFontMetrics().getHeight();
    }
    
    public Point getLocation() {
        return this.location;
    }
    
    public boolean getShowLabel() {
        return this.showLabel;
    }
    
    public String getString() {
        return this.theString;
    }
    
    public int getWidth() {
        return this.theString.length() * this.XGap;
    }
    
    public int getX() {
        return this.location.x;
    }
    
    public int getY() {
        return this.location.y;
    }
    
    public void highlight(final int index) {
        this.highlightTable[index] = true;
    }
    
    public void highlight(final int index1, final int index2) {
        for (int i = index1; i < index2; ++i) {
            this.highlightTable[i] = true;
        }
    }
    
    private void initialise() {
        this.setLocation(0, 0);
        this.XGap = 20;
        this.needPosition = true;
        this.length = this.theString.length();
        this.highlightTable = new boolean[this.length];
        this.theLabel = null;
        this.showLabel = false;
        this.highLabel = true;
        this.arrowLabelIndex = 0;
        this.arrowLabelTarget = -1;
        this.xArrowIndex = -1;
        this.notDrawnYet = true;
        for (int i = 0; i < this.length; ++i) {
            this.highlightTable[i] = false;
        }
    }
    
    public void jumpArrowLabelIndex(final int jump) {
        if (this.xArrowIndex == -1) {
            this.setArrowLabelIndex(jump);
        }
        else {
            this.xArrowIndex = this.xArrowIndex + jump * this.XGap;
        }
    }
    
    public int length() {
        return this.length;
    }
    
    public void setArrowLabelIndex(final int index) {
        this.arrowLabelIndex = index;
        this.xArrowIndex = index * this.XGap + this.location.x;
    }
    
    public void setArrowLabelTarget(final int index) {
        this.arrowLabelTarget = index;
    }
    
    public void setHighLabel(final boolean state) {
        this.highLabel = state;
    }
    
    public void setHighlightColor(final Color c) {
        this.highlightColor = c;
    }
    
    public void setLabel(final String label) {
        this.theLabel = label;
    }
    
    public void setLabelIndex(final int index) {
        this.labelIndex = index;
    }
    
    public void setLocation(final int xPos, final int yPos) {
        this.location.x = xPos;
        this.location.y = yPos;
    }
    
    public void setLocation(final Point newPnt) {
        this.location = newPnt;
    }
    
    public void setPosition(final int xPos, final int yPos) {
        if (this.needPosition) {
            this.setLocation(xPos + this.location.x, yPos + this.location.y);
            this.needPosition = false;
        }
    }
    
    public void setShowLabel(final boolean state) {
        this.showLabel = state;
    }
    
    public void setX(final int x) {
        this.location.x = x;
    }
    
    public void setY(final int y) {
        this.location.y = y;
    }
    
    public void shiftEntire(final int x, final int y) {
        this.location.x = this.location.x + x;
        this.location.y = this.location.y + y;
    }
    
    public void unHighlight(final int index) {
        this.highlightTable[index] = false;
    }
    
    public void unHighlight(final int index1, final int index2) {
        for (int i = index1; i < index2; ++i) {
            this.highlightTable[i] = false;
        }
    }
    
    public void unHighlightAll() {
        for (int i = 0; i < this.length; ++i) {
            this.highlightTable[i] = false;
        }
    }
}
