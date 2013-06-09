import java.awt.*;
import com.cim.AIA.*;

public class KMPString implements Drawable, Moveable
{
    protected String theString;
    protected int XGap;
    protected boolean needPosition;
    protected Point location;
    protected int length;
    protected String theLabel;
    protected boolean showLabel;
    protected boolean highLabel;
    protected int labelIndex;
    protected Color labelColor;
    protected Color highlightColor;
    protected boolean[] highlightTable;
    protected boolean highlightMinusOne;
    
    public KMPString(final String str) {
        super();
        this.location = new Point();
        this.labelColor = Color.red;
        this.highlightColor = Color.green;
        this.setLocation(0, 0);
        this.theString = str;
        this.initialise();
    }
    
    public void draw(final Graphics g) {
        int xLoc = this.location.x + this.XGap;
        final int yLoc = this.location.y;
        final FontMetrics theFM = g.getFontMetrics();
        for (int counter = 0; counter < this.theString.length(); ++counter) {
            final char ch = this.theString.charAt(counter);
            final int temp = (this.XGap - theFM.charWidth(ch)) / 2;
            Color saveColor;
            if (counter == 0 && this.highlightMinusOne) {
                saveColor = g.getColor();
                g.setColor(this.highlightColor);
                g.fillRect(xLoc + temp - this.XGap, yLoc - g.getFontMetrics().getHeight(), g.getFontMetrics().charWidth(ch), g.getFontMetrics().getHeight());
                g.setColor(saveColor);
            }
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
                    g.drawString(this.theLabel, xLoc + temp, yLoc - this.getHeight());
                }
                else {
                    g.drawString(this.theLabel, xLoc + temp, yLoc + this.getHeight());
                }
                g.setColor(saveColor);
            }
            if (this.showLabel && this.labelIndex == -1 && counter == 0) {
                saveColor = g.getColor();
                g.setColor(this.labelColor);
                if (this.highLabel) {
                    g.drawString(this.theLabel, xLoc + temp - this.XGap, yLoc - this.getHeight());
                }
                else {
                    g.drawString(this.theLabel, xLoc + temp - this.XGap, yLoc + this.getHeight());
                }
                g.setColor(saveColor);
            }
            xLoc = xLoc + this.XGap;
        }
    }
    
    public void draw(final Graphics g, final Point pnt) {
        this.setLocation(pnt);
        this.draw(g);
    }
    
    public int getHeight() {
        return KMPApplet.theKMPCanvas.getGraphics().getFontMetrics().getHeight();
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
        if (index >= 0) {
            this.highlightTable[index] = true;
        }
        if (index == -1) {
            this.highlightMinusOne = true;
        }
    }
    
    public void highlight(final int index1, final int index2) {
        for (int i = index1; i < index2; ++i) {
            this.highlight(i);
        }
    }
    
    private void initialise() {
        this.XGap = 20;
        this.needPosition = true;
        this.length = this.theString.length();
        this.highlightTable = new boolean[this.length];
        this.theLabel = null;
        this.showLabel = false;
        this.highLabel = true;
        for (int i = 0; i < this.length; ++i) {
            this.highlightTable[i] = false;
        }
        this.highlightMinusOne = false;
    }
    
    public int length() {
        return this.length;
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
        if (index >= 0) {
            this.highlightTable[index] = false;
        }
        if (index == -1) {
            this.highlightMinusOne = false;
        }
    }
    
    public void unHighlight(final int index1, final int index2) {
        for (int i = index1; i < index2; ++i) {
            this.unHighlight(i);
        }
    }
    
    public void unHighlightAll() {
        this.unHighlight(-1, this.length);
    }
}
