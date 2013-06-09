package com.cim.AIA;

import java.awt.*;

public class FontSelection extends ConfigurationSelection
{
    public static final String NORMAL_FONT = "Normal Font";
    protected Font font;
    protected Font oldFont;
    protected String atributeName;
    protected Font originalFont;
    
    public FontSelection(final Font font, final String atributeName) {
        super();
        this.font = font;
        this.oldFont = this.font;
        this.atributeName = atributeName;
        this.originalFont = new Font(this.font.getName(), this.font.getStyle(), this.font.getSize());
    }
    
    protected void accept() {
        this.oldFont = this.font;
    }
    
    protected void backup() {
        this.oldFont = new Font(this.font.getName(), this.font.getStyle(), this.font.getSize());
    }
    
    protected boolean changed() {
        return this.oldFont != this.font;
    }
    
    public String getAtributeName() {
        return this.atributeName;
    }
    
    public Font getFont() {
        return this.font;
    }
    
    protected void restore() {
        this.font = this.oldFont;
    }
    
    public void restoreOriginal() {
        this.font = this.originalFont;
    }
    
    protected void setFont(final Font font) {
        this.font = font;
    }
}
