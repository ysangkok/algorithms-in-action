package com.cim.AIA;

import localization.*;
import java.awt.*;

public class ColorSelection extends ConfigurationSelection
{
    public static final String NORMAL_COLOR;
    public static final String BACKGROUND;
    public static final String TEXT_COLOR;
    protected Color color;
    protected String atributeName;
    protected Color oldColor;
    protected Color originalColor;
    
    public ColorSelection(final Color color, final String atributeName) {
        super();
        this.color = color;
        this.originalColor = new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
        this.atributeName = atributeName;
        this.oldColor = this.color;
    }
    
    protected void accept() {
        this.oldColor = this.color;
    }
    
    protected void backup() {
        this.oldColor = new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    }
    
    protected boolean changed() {
        return this.oldColor != this.color;
    }
    
    public String getAtributeName() {
        return this.atributeName;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    protected void restore() {
        this.color = this.oldColor;
    }
    
    public void restoreOriginal() {
        this.color = this.originalColor;
    }
    
    protected void setColor(final Color color) {
        this.color = color;
    }
    
    static {
        NORMAL_COLOR = Messages.getString("ColorSelection.0");
        BACKGROUND = Messages.getString("ColorSelection.1");
        TEXT_COLOR = Messages.getString("ColorSelection.2");
    }
}
