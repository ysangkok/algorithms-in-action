package com.cim.AIA;

import java.awt.*;

public class VerticalBar extends Element
{
    public static final Color HIGHLIGHT_COLOR;
    public static final Color BORDER_COLOR;
    public static final int BORDER_THICKNESS = 4;
    public static final Color TEXT_COLOR;
    public static final int COLUM_Y_SPACING = 0;
    public static final int COLUM_WIDTH = 10;
    protected boolean drawMarkers;
    protected int additionalMarkerSpacing;
    protected boolean drawValueMarker;
    protected boolean markersAboveValue;
    protected int offsetForValue;
    protected Color highlightColor;
    protected Color textColor;
    protected Color borderColor;
    protected Color oldColor;
    protected Color color;
    protected Point position;
    protected int borderThickness;
    protected int columWidth;
    protected int columYSpacing;
    protected float scaleFactor;
    
    public VerticalBar(final int sequenceNumber, final int value, final Color color, final Color borderColor, final Point position) {
        super(sequenceNumber, new Integer(value));
        this.drawMarkers = true;
        this.additionalMarkerSpacing = 0;
        this.drawValueMarker = true;
        this.markersAboveValue = true;
        this.offsetForValue = 1;
        this.highlightColor = VerticalBar.HIGHLIGHT_COLOR;
        this.textColor = VerticalBar.TEXT_COLOR;
        this.borderColor = VerticalBar.BORDER_COLOR;
        this.oldColor = null;
        this.borderThickness = 4;
        this.columWidth = 10;
        this.columYSpacing = 0;
        this.scaleFactor = 1.0f;
        this.color = color;
        this.borderColor = borderColor;
        this.position = position;
    }
    
    public VerticalBar(final int sequenceNumber, final int value, final Color color, final Point position) {
        super(sequenceNumber, new Integer(value));
        this.drawMarkers = true;
        this.additionalMarkerSpacing = 0;
        this.drawValueMarker = true;
        this.markersAboveValue = true;
        this.offsetForValue = 1;
        this.highlightColor = VerticalBar.HIGHLIGHT_COLOR;
        this.textColor = VerticalBar.TEXT_COLOR;
        this.borderColor = VerticalBar.BORDER_COLOR;
        this.oldColor = null;
        this.borderThickness = 4;
        this.columWidth = 10;
        this.columYSpacing = 0;
        this.scaleFactor = 1.0f;
        this.color = color;
        this.borderColor = VerticalBar.BORDER_COLOR;
        this.position = position;
    }
    
    public void draw(final Graphics g) {
        final Color oldColor = g.getColor();
        this.drawElementValue(g);
        g.setColor(this.textColor);
        if (this.markersAboveValue) {
            if (this.drawMarkers) {
                int y = this.position.y + this.borderThickness;
                for (int i = 0; i < this.markers.size(); ++i) {
                    final StringMarker string = (StringMarker)this.markers.elementAt(i);
                    y = y + (string.getHeight(g) + this.additionalMarkerSpacing);
                    string.setPosition(new Point(this.position.x + this.columWidth / 2, y));
                    string.setCenter(true);
                    string.draw(g);
                }
            }
            if (this.offsetForValue > this.markers.size()) {
                if (this.drawValueMarker) {
                    g.drawString(((Integer)((Integer)this.value)).intValue() + "", this.position.x + this.columWidth / 2 - g.getFontMetrics().stringWidth(((Integer)((Integer)this.value)).intValue() + "") / 2, this.position.y + this.borderThickness + this.offsetForValue * g.getFontMetrics().getHeight());
                }
            }
            else if (this.drawValueMarker) {
                g.drawString(((Integer)((Integer)this.value)).intValue() + "", this.position.x + this.columWidth / 2 - g.getFontMetrics().stringWidth(((Integer)((Integer)this.value)).intValue() + "") / 2, this.position.y + this.borderThickness + (this.markers.size() + 1) * g.getFontMetrics().getHeight());
            }
        }
        else {
            if (this.drawValueMarker) {
                g.drawString(((Integer)((Integer)this.value)).intValue() + "", this.position.x + this.columWidth / 2 - g.getFontMetrics().stringWidth(((Integer)((Integer)this.value)).intValue() + "") / 2, this.position.y + this.borderThickness + this.offsetForValue * g.getFontMetrics().getHeight());
            }
            if (this.drawMarkers) {
                int y = this.position.y + this.borderThickness;
                for (int i = 0; i < this.markers.size(); ++i) {
                    final StringMarker string = (StringMarker)this.markers.elementAt(i);
                    y = y + (string.getHeight(g) + this.additionalMarkerSpacing);
                    string.setPosition(new Point(this.position.x + this.columWidth / 2, y));
                    string.setCenter(true);
                    string.draw(g);
                }
            }
        }
        g.setColor(oldColor);
    }
    
    public void draw(final Graphics g, final Point p) {
        this.setPosition(p);
        this.draw(g);
    }
    
    protected void drawElementValue(final Graphics g) {
        g.setColor(this.borderColor);
        g.fillRect(this.position.x - this.borderThickness / 2, this.position.y - this.getHeight() - this.borderThickness / 2 - this.columYSpacing, this.columWidth + this.borderThickness, this.getHeight() + this.borderThickness);
        g.setColor(this.color);
        g.fillRect(this.position.x, this.position.y - this.getHeight() - this.columYSpacing, this.columWidth, this.getHeight());
    }
    
    public boolean equals(final Selectable selectable) {
        if (selectable instanceof VerticalBar) {
            final VerticalBar element = (VerticalBar)selectable;
            return element.getIdentifier() == this.getIdentifier();
        }
        return false;
    }
    
    public int getAdditionalMarkerSpacing() {
        return this.additionalMarkerSpacing;
    }
    
    public Color getBorderColor() {
        return this.borderColor;
    }
    
    public int getBorderThickness() {
        return this.borderThickness;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public int getColumYSpacing() {
        return this.columYSpacing;
    }
    
    public int getHeight() {
        return (int)((float)((Integer)((Integer)this.value)).intValue() * this.scaleFactor);
    }
    
    public int getHeight(final Graphics g) {
        int noOfMarkers = 0;
        if (this.drawValueMarker) {
            ++noOfMarkers;
        }
        if (this.drawMarkers) {
            noOfMarkers = noOfMarkers + this.markers.size();
        }
        return this.getHeight() + this.borderThickness + this.columYSpacing + noOfMarkers * (g.getFontMetrics().getHeight() + this.additionalMarkerSpacing);
    }
    
    public Color getHighlightColor() {
        return this.highlightColor;
    }
    
    public Selectable getItemAt(final Point point) {
        final int buffer = Math.max(4, this.borderThickness);
        if (new Rectangle(this.position.x - buffer, this.position.y - (Integer)((Integer)this.value) - this.columYSpacing - buffer, this.columWidth + 2 * buffer, (Integer)((Integer)this.value) + 2 * buffer).contains(point)) {
            return this;
        }
        return null;
    }
    
    public Point getPosition() {
        return this.position;
    }
    
    public int getRealOffsetForValue(final Graphics g) {
        if (!this.markersAboveValue) {
            return this.offsetForValue * g.getFontMetrics().getHeight() + this.borderThickness;
        }
        if (this.offsetForValue > this.markers.size()) {
            return this.offsetForValue * g.getFontMetrics().getHeight() + this.borderThickness;
        }
        return (this.markers.size() + 1) * g.getFontMetrics().getHeight() + this.borderThickness;
    }
    
    public float getScaleFactor() {
        return this.scaleFactor;
    }
    
    public Color getTextColor() {
        return this.textColor;
    }
    
    public int getWidth() {
        return this.columWidth;
    }
    
    public int getX() {
        return this.position.x;
    }
    
    public int getY() {
        return this.position.y;
    }
    
    public void highlight() {
        if (this.color == this.highlightColor) {
            return;
        }
        if (this.oldColor == null) {
            this.oldColor = this.color;
        }
        else if (this.oldColor != this.color) {
            this.oldColor = this.borderColor;
        }
        this.color = this.highlightColor;
    }
    
    public void setAdditionalMarkerSpacing(final int additionalMarkerSpacing) {
        this.additionalMarkerSpacing = additionalMarkerSpacing;
    }
    
    public void setBorderColor(final Color color) {
        this.borderColor = color;
    }
    
    public void setColor(final Color color) {
        this.color = color;
    }
    
    public void setColumYSpacing(final int columYSpacing) {
        this.columYSpacing = columYSpacing;
    }
    
    public void setDrawMarkers(final boolean state) {
        this.drawMarkers = state;
    }
    
    public void setDrawValueMarker(final boolean drawValueMarker) {
        this.drawValueMarker = drawValueMarker;
    }
    
    public void setHeight(final int height) {
        this.value = new Integer(height);
    }
    
    public void setHighlightColor(final Color color) {
        this.highlightColor = color;
    }
    
    public void setMarkersAboveValue(final boolean state) {
        this.markersAboveValue = state;
    }
    
    public void setObject(final Integer value) {
        this.value = value;
    }
    
    public void setOffsetForValue(final int offset) {
        this.offsetForValue = offset;
    }
    
    public void setPosition(final Point position) {
        this.position.x = position.x;
        this.position.y = position.y;
    }
    
    public void setScaleFactor(final float scaleFactor) {
        this.scaleFactor = scaleFactor;
    }
    
    public void setTextColor(final Color color) {
        this.textColor = color;
    }
    
    public void setThickness(final int thickness) {
        this.borderThickness = thickness;
    }
    
    public void setWidth(final int width) {
        this.columWidth = width;
    }
    
    public void setX(final int x) {
        this.position.x = x;
    }
    
    public void setY(final int y) {
        this.position.y = y;
    }
    
    public void shiftEntire(final int xDelta, final int yDelta) {
        final Point position = this.position;
        position.x = position.x + xDelta;
        final Point position2 = this.position;
        position2.y = position2.y + yDelta;
    }
    
    public void unHighlight() {
        if (this.oldColor != null) {
            this.color = this.oldColor;
            this.oldColor = null;
        }
    }
    
    static {
        HIGHLIGHT_COLOR = Color.yellow;
        BORDER_COLOR = Color.black;
        TEXT_COLOR = Color.black;
    }
}
