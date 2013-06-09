package com.cim.AIA;

import java.awt.*;

public class StringMarker implements Moveable, Drawable
{
    protected static final Color DEFAULT_COLOR;
    protected static final Color DEFAULT_BACKGROUND_COLOR;
    protected String string;
    protected Point position;
    protected Color color;
    protected Color backgroundColor;
    protected boolean doCenter;
    protected boolean useDefaultHeight;
    protected boolean useDefaultWidth;
    protected int width;
    protected int height;
    
    public StringMarker(final String string) {
        this(string, new Point(0, 0), StringMarker.DEFAULT_COLOR, StringMarker.DEFAULT_BACKGROUND_COLOR);
    }
    
    public StringMarker(final String string, final Point position) {
        this(string, position, StringMarker.DEFAULT_COLOR, StringMarker.DEFAULT_BACKGROUND_COLOR);
    }
    
    public StringMarker(final String string, final Point position, final Color color, final Color backgroundColor) {
        super();
        this.doCenter = true;
        this.useDefaultHeight = true;
        this.useDefaultWidth = true;
        this.string = string;
        this.position = position;
        this.color = color;
        this.backgroundColor = backgroundColor;
    }
    
    public void draw(final Graphics g) {
        g.setColor(this.backgroundColor);
        final int tempHeight = this.getHeight(g);
        final int tempWidth = this.getWidth(g);
        if (this.doCenter) {
            g.fillRect(this.position.x - tempWidth / 2, this.position.y - tempHeight, tempWidth, tempHeight);
        }
        else {
            g.fillRect(this.position.x, this.position.y - tempHeight, tempWidth, tempHeight);
        }
        g.setColor(this.color);
        if (this.doCenter) {
            g.drawString(this.string, this.position.x - g.getFontMetrics().stringWidth(this.string) / 2, this.position.y);
        }
        else {
            g.drawString(this.string, this.position.x, this.position.y);
        }
    }
    
    public void draw(final Graphics g, final Point p) {
        this.setPosition(p);
        this.draw(g);
    }
    
    public int getHeight(final Graphics g) {
        if (this.useDefaultHeight) {
            return g.getFontMetrics().getHeight();
        }
        return this.height;
    }
    
    public Point getPosition() {
        return this.position;
    }
    
    public int getWidth(final Graphics g) {
        if (this.useDefaultWidth) {
            return g.getFontMetrics().stringWidth(this.string);
        }
        return this.width;
    }
    
    public int getX() {
        return this.position.x;
    }
    
    public int getY() {
        return this.position.y;
    }
    
    public void setBackgroundColor(final Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    
    public void setCenter(final boolean state) {
        this.doCenter = state;
    }
    
    public void setColor(final Color color) {
        this.color = color;
    }
    
    public void setHeight(final int height) {
        this.height = height;
    }
    
    public void setPosition(final Point position) {
        this.position = position;
    }
    
    public void setString(final String string) {
        this.string = string;
    }
    
    public void setWidth(final int width) {
        this.width = width;
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
    
    public void useDefaultHeight(final boolean state) {
        this.useDefaultHeight = state;
    }
    
    public void useDefaultWidth(final boolean state) {
        this.useDefaultWidth = state;
    }
    
    static {
        DEFAULT_COLOR = Color.black;
        DEFAULT_BACKGROUND_COLOR = Color.white;
    }
}
