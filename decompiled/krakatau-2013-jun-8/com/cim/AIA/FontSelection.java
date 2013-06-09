package com.cim.AIA;

public class FontSelection extends com.cim.AIA.ConfigurationSelection {
    final public static String NORMAL_FONT = "Normal Font";
    protected java.awt.Font font;
    protected java.awt.Font oldFont;
    protected String atributeName;
    protected java.awt.Font originalFont;
    
    public FontSelection(java.awt.Font a, String s)
    {
        super();
        this.font = a;
        java.awt.Font a0 = this.font;
        this.oldFont = a0;
        this.atributeName = s;
        java.awt.Font a1 = this.font;
        String s0 = a1.getName();
        java.awt.Font a2 = this.font;
        int i = a2.getStyle();
        java.awt.Font a3 = this.font;
        int i0 = a3.getSize();
        java.awt.Font a4 = new java.awt.Font(s0, i, i0);
        this.originalFont = a4;
    }
    
    protected void accept()
    {
        java.awt.Font a = this.font;
        this.oldFont = a;
    }
    
    protected void backup()
    {
        java.awt.Font a = this.font;
        String s = a.getName();
        java.awt.Font a0 = this.font;
        int i = a0.getStyle();
        java.awt.Font a1 = this.font;
        int i0 = a1.getSize();
        java.awt.Font a2 = new java.awt.Font(s, i, i0);
        this.oldFont = a2;
    }
    
    protected boolean changed()
    {
        java.awt.Font a = this.oldFont;
        java.awt.Font a0 = this.font;
        int i = a == a0?0:1;
        return i != 0;
    }
    
    public String getAtributeName()
    {
        String s = this.atributeName;
        return s;
    }
    
    public java.awt.Font getFont()
    {
        java.awt.Font a = this.font;
        return a;
    }
    
    protected void restore()
    {
        java.awt.Font a = this.oldFont;
        this.font = a;
    }
    
    public void restoreOriginal()
    {
        java.awt.Font a = this.originalFont;
        this.font = a;
    }
    
    protected void setFont(java.awt.Font a)
    {
        this.font = a;
    }
}