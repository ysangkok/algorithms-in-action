package com.cim.AIA;

import java.awt.*;

public class RoundNode extends Node
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
    protected Color backgroundColor;
    protected Color ringColor;
    protected Color textColor;
    protected boolean drawMarkers;
    protected boolean markersBelowValue;
    protected int additionalMarkerSpacing;
    
    public static void setDefaultBackgroundColor(final Color color) {
        RoundNode.DEFAULT_BACKGROUND_COLOR = color;
    }
    
    public RoundNode(final Object value, final int sequenceNumber) {
        super(value, sequenceNumber);
        this.pos = new Point(0, 0);
        this.width = 20;
        this.height = 20;
        this.showObject = true;
        this.isVisible = true;
        this.highlightColor = RoundNode.DEFAULT_HIGHLIGHT_COLOR;
        this.oldBackgroundColor = null;
        this.backgroundColor = RoundNode.DEFAULT_BACKGROUND_COLOR;
        this.ringColor = RoundNode.DEFAULT_BORDER_COLOR;
        this.textColor = RoundNode.DEFAULT_TEXT_COLOR;
        this.drawMarkers = true;
        this.markersBelowValue = true;
        this.additionalMarkerSpacing = 0;
    }
    
    public void draw(final Graphics g) {
        if (!this.isVisible) {
            return;
        }
        final FontMetrics metrics = g.getFontMetrics();
        final Color oldColor = g.getColor();
        g.setColor(this.ringColor);
        g.drawOval(this.pos.x, this.pos.y, this.width, this.height);
        g.drawOval(this.pos.x + 1, this.pos.y + 1, this.width, this.height);
        g.drawOval(this.pos.x + 1, this.pos.y + 1, this.width - 1, this.height - 1);
        g.setColor(this.backgroundColor);
        g.fillOval(this.pos.x + 2, this.pos.y + 2, this.width - 2, this.height - 2);
        if (this.showObject) {
            g.setColor(this.textColor);
            final String string = this.getDisplayString();
            g.drawString(string, this.pos.x + this.width / 2 - metrics.stringWidth(string) / 2, this.pos.y + this.height / 2 + metrics.getHeight() / 2 - 2);
        }
        if (this.drawMarkers) {
            int y = this.pos.y;
            if (this.markersBelowValue) {
                y = y + this.height;
            }
            else {
                y = y + g.getFontMetrics().getHeight();
            }
            for (int i = 0; i < this.markers.size(); ++i) {
                final StringMarker string2 = (StringMarker)this.markers.elementAt(i);
                if (this.markersBelowValue) {
                    y = y + (string2.getHeight(g) + this.additionalMarkerSpacing);
                }
                else {
                    y = y - (string2.getHeight(g) + this.additionalMarkerSpacing);
                }
                final Point point = new Point(this.pos.x + this.width / 2, y);
                string2.setPosition(point);
                string2.setCenter(true);
                string2.draw(g);
            }
        }
        g.setColor(oldColor);
    }
    
    public void draw(final Graphics g, final Point pos) {
        this.setPosition(pos);
        this.draw(g);
    }
    
    public boolean equals(final Selectable selectable) {
        if (selectable instanceof Node) {
            final RoundNode node = (RoundNode)selectable;
            return this.getIdentifier() == node.getIdentifier();
        }
        return false;
    }
    
    public int getAdditionalMarkerSpacing() {
        return this.additionalMarkerSpacing;
    }
    
    public Color getBackgroundColor() {
        return this.backgroundColor;
    }
    
    public String getDisplayString() {
        return this.value.toString();
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public Color getHighlightColor() {
        return this.highlightColor;
    }
    
    public boolean getIsVisible() {
        return this.isVisible;
    }
    
    public Selectable getItemAt(final Point point) {
        if (new Rectangle(this.pos.x, this.pos.y, this.width, this.height).contains(point)) {
            return this;
        }
        return null;
    }
    
    public Point getPosition() {
        return this.pos;
    }
    
    public Color getRingColor() {
        return this.ringColor;
    }
    
    public boolean getShowObject() {
        return this.showObject;
    }
    
    public Color getTextColor() {
        return this.textColor;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getX() {
        return this.pos.x;
    }
    
    public int getY() {
        return this.pos.y;
    }
    
    public void highlight() {
        if (this.backgroundColor != this.highlightColor) {
            this.oldBackgroundColor = this.backgroundColor;
            this.backgroundColor = this.highlightColor;
        }
    }
    
    public void setAdditionalMarkerSpacing(final int additionalMarkerSpacing) {
        this.additionalMarkerSpacing = additionalMarkerSpacing;
    }
    
    public void setBackgroundColor(final Color color) {
        this.backgroundColor = color;
    }
    
    public void setDrawMarkers(final boolean state) {
        this.drawMarkers = state;
    }
    
    public void setHeight(final int height) {
        this.height = height;
    }
    
    public void setHighlightColor(final Color color) {
        this.highlightColor = color;
    }
    
    public void setIsVisible(final boolean isVisible) {
        this.isVisible = isVisible;
    }
    
    public void setMarkersBelowValue(final boolean state) {
        this.markersBelowValue = state;
    }
    
    public void setPosition(final Point pos) {
        this.pos.x = pos.x;
        this.pos.y = pos.y;
    }
    
    public void setRingColor(final Color color) {
        this.ringColor = color;
    }
    
    public void setTextColor(final Color color) {
        this.textColor = color;
    }
    
    public void setWidth(final int width) {
        this.width = width;
    }
    
    public void setX(final int x) {
        this.pos.x = x;
    }
    
    public void setY(final int y) {
        this.pos.y = y;
    }
    
    public void shiftEntire(final int xDelta, final int yDelta) {
        final Point pos = this.pos;
        pos.x = pos.x + xDelta;
        final Point pos2 = this.pos;
        pos2.y = pos2.y + yDelta;
    }
    
    public void showObject(final boolean visible) {
        this.showObject = visible;
    }
    
    public void unHighlight() {
        if (this.oldBackgroundColor != null) {
            this.backgroundColor = this.oldBackgroundColor;
            this.oldBackgroundColor = null;
        }
    }
    
    static {
        RoundNode.DEFAULT_BACKGROUND_COLOR = Color.orange;
        DEFAULT_BORDER_COLOR = Color.black;
        DEFAULT_TEXT_COLOR = Color.black;
        DEFAULT_HIGHLIGHT_COLOR = Color.yellow;
    }
}
