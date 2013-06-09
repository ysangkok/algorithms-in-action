package aia.graph.common;

import java.awt.*;
import com.cim.AIA.*;

public class TwinNode extends Element
{
    public static Color DEFAULT_BACKGROUND_COLOR;
    public static final Color DEFAULT_BORDER_COLOR;
    public static final Color DEFAULT_TEXT_COLOR;
    public static final Color DEFAULT_HIGHLIGHT_COLOR;
    protected Point pos;
    protected int width;
    protected int height;
    protected boolean showObject;
    protected boolean isVisible;
    protected Color highlightColor;
    protected Color oldBackgroundColor;
    protected Color backgroundColorLeft;
    protected Color backgroundColorRight;
    protected Color ringColor;
    protected Color textColor;
    protected boolean drawMarkers;
    protected boolean markersBelowValue;
    protected int additionalMarkerSpacing;
    protected Integer m_l_value;
    protected Integer m_r_value;
    protected Integer m_priority;
    
    public TwinNode(final Object l_value, final Object r_value, final int sequenceNumber) {
        super(sequenceNumber, l_value);
        this.pos = new Point(0, 0);
        this.width = 40;
        this.height = 20;
        this.showObject = true;
        this.isVisible = true;
        this.highlightColor = TwinNode.DEFAULT_HIGHLIGHT_COLOR;
        this.oldBackgroundColor = null;
        this.backgroundColorLeft = new Color(84, 183, 183);
        this.backgroundColorRight = new Color(55, 121, 121);
        this.ringColor = TwinNode.DEFAULT_BORDER_COLOR;
        this.textColor = TwinNode.DEFAULT_TEXT_COLOR;
        this.drawMarkers = true;
        this.markersBelowValue = true;
        this.additionalMarkerSpacing = 0;
        this.m_l_value = null;
        this.m_r_value = null;
        this.m_priority = null;
        this.m_l_value = (Integer)l_value;
        this.m_r_value = (Integer)r_value;
    }
    
    public int getLValue() {
        if (this.m_l_value == null) {
            return -1;
        }
        return this.m_l_value.intValue();
    }
    
    public int getRValue() {
        if (this.m_r_value == null) {
            return -1;
        }
        return this.m_r_value.intValue();
    }
    
    public static void setDefaultBackgroundColor(final Color color) {
        TwinNode.DEFAULT_BACKGROUND_COLOR = color;
    }
    
    public void showObject(final boolean visible) {
        this.showObject = visible;
    }
    
    public boolean getShowObject() {
        return this.showObject;
    }
    
    public void setBackgroundColor(final Color color) {
    }
    
    public void setBackgroundColorLeft(final Color color) {
        this.backgroundColorLeft = color;
    }
    
    public void setBackgroundColorRight(final Color color) {
        this.backgroundColorRight = color;
    }
    
    public void setHighlightColor(final Color color) {
        this.highlightColor = color;
    }
    
    public void setTextColor(final Color color) {
        this.textColor = color;
    }
    
    public void setRingColor(final Color color) {
        this.ringColor = color;
    }
    
    public Color getRingColor() {
        return this.ringColor;
    }
    
    public Color getBackgroundColor() {
        return null;
    }
    
    public Color getBackgroundColorLeft() {
        return this.backgroundColorLeft;
    }
    
    public Color getBackgroundColorRight() {
        return this.backgroundColorRight;
    }
    
    public Color getHighlightColor() {
        return this.highlightColor;
    }
    
    public Color getTextColor() {
        return this.textColor;
    }
    
    public String getDisplayString() {
        return this.value.toString();
    }
    
    public String getLDisplayString() {
        if (this.m_l_value == null) {
            return "n";
        }
        return this.m_l_value.toString();
    }
    
    public String getRDisplayString() {
        if (this.m_r_value == null) {
            return "n";
        }
        return this.m_r_value.toString();
    }
    
    public void setWidth(final int width) {
        this.width = width;
    }
    
    public void setHeight(final int height) {
        this.height = height;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public void setIsVisible(final boolean isVisible) {
        this.isVisible = isVisible;
    }
    
    public boolean getIsVisible() {
        return this.isVisible;
    }
    
    public void setAdditionalMarkerSpacing(final int additionalMarkerSpacing) {
        this.additionalMarkerSpacing = additionalMarkerSpacing;
    }
    
    public int getAdditionalMarkerSpacing() {
        return this.additionalMarkerSpacing;
    }
    
    public void setMarkersBelowValue(final boolean state) {
        this.markersBelowValue = state;
    }
    
    public void setDrawMarkers(final boolean state) {
        this.drawMarkers = state;
    }
    
    public void setPriority(final int p_priority) {
        this.m_priority = new Integer(p_priority);
    }
    
    public void setPosition(final Point pos) {
        this.pos.x = pos.x;
        this.pos.y = pos.y;
    }
    
    public Point getPosition() {
        return this.pos;
    }
    
    public void draw(final Graphics g, final Point pos) {
        this.setPosition(pos);
        this.draw(g);
    }
    
    public void draw(final Graphics g) {
        if (!this.isVisible) {
            return;
        }
        final FontMetrics metrics = g.getFontMetrics();
        final Color oldColor = g.getColor();
        g.setColor(this.backgroundColorLeft);
        g.fill3DRect(this.pos.x + 1, this.pos.y + 1, this.getWidth() / 2 - 1, this.getHeight() - 1, true);
        g.setColor(this.backgroundColorRight);
        g.fill3DRect(this.pos.x + this.getWidth() / 2 + 2, this.pos.y + 1, this.getWidth() / 2 - 1, this.getHeight() - 1, true);
        g.setColor(Color.black);
        g.drawRect(this.pos.x, this.pos.y, this.getWidth(), this.getHeight());
        g.drawRect(this.pos.x - 1, this.pos.y - 1, this.getWidth() + 2, this.getHeight() + 2);
        g.drawLine(this.pos.x + this.getWidth() / 2, this.pos.y, this.pos.x + this.getWidth() / 2, this.pos.y + this.getHeight());
        if (this.showObject) {
            g.setColor(this.textColor);
            String string = this.getLDisplayString();
            g.drawString(string, this.pos.x + this.getWidth() / 4 - metrics.stringWidth(string) / 2, this.pos.y + this.getHeight() / 2 + metrics.getHeight() / 2 - 2);
            string = this.getRDisplayString();
            g.drawString(string, this.pos.x + 3 * this.getWidth() / 4 - metrics.stringWidth(string) / 2, this.pos.y + this.getHeight() / 2 + metrics.getHeight() / 2 - 2);
        }
        if (this.m_priority != null) {
            int y = this.pos.y;
            final String priorityString = this.m_priority.toString();
            y = y + this.getHeight();
            g.setColor(Color.white);
            g.fillRect(this.pos.x + (this.getWidth() - metrics.stringWidth(priorityString)) / 2 - 2, y, metrics.stringWidth(priorityString) + 4, metrics.getHeight() + 2);
            g.setColor(Color.black);
            g.drawRect(this.pos.x + (this.getWidth() - metrics.stringWidth(priorityString)) / 2 - 2, y, metrics.stringWidth(priorityString) + 4, metrics.getHeight() + 2);
            y = y + metrics.getHeight();
            g.drawString(priorityString, this.pos.x + (this.getWidth() - metrics.stringWidth(priorityString)) / 2, y);
        }
        g.setColor(oldColor);
    }
    
    public void setX(final int x) {
        this.pos.x = x;
    }
    
    public void setY(final int y) {
        this.pos.y = y;
    }
    
    public int getX() {
        return this.pos.x;
    }
    
    public int getY() {
        return this.pos.y;
    }
    
    public void shiftEntire(final int xDelta, final int yDelta) {
        final Point pos = this.pos;
        pos.x = pos.x + xDelta;
        final Point pos2 = this.pos;
        pos2.y = pos2.y + yDelta;
    }
    
    public Selectable getItemAt(final Point point) {
        if (new Rectangle(this.pos.x, this.pos.y, this.getWidth(), this.getHeight()).contains(point)) {
            return this;
        }
        return null;
    }
    
    public boolean equals(final Selectable selectable) {
        if (selectable instanceof Node) {
            final Node node = (Node)selectable;
            return this.getIdentifier() == node.getIdentifier();
        }
        return false;
    }
    
    public void highlight() {
    }
    
    public void unHighlight() {
    }
    
    static {
        TwinNode.DEFAULT_BACKGROUND_COLOR = Color.orange;
        DEFAULT_BORDER_COLOR = Color.black;
        DEFAULT_TEXT_COLOR = Color.black;
        DEFAULT_HIGHLIGHT_COLOR = Color.yellow;
    }
}
