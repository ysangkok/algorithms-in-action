package com.cim.AIA;

public class ColorSelection extends com.cim.AIA.ConfigurationSelection {
    final public static String NORMAL_COLOR;
    final public static String BACKGROUND;
    final public static String TEXT_COLOR;
    protected java.awt.Color color;
    protected String atributeName;
    protected java.awt.Color oldColor;
    protected java.awt.Color originalColor;
    
    public ColorSelection(java.awt.Color a, String s)
    {
        super();
        this.color = a;
        java.awt.Color a0 = this.color;
        int i = a0.getRed();
        java.awt.Color a1 = this.color;
        int i0 = a1.getGreen();
        java.awt.Color a2 = this.color;
        int i1 = a2.getBlue();
        java.awt.Color a3 = new java.awt.Color(i, i0, i1);
        this.originalColor = a3;
        this.atributeName = s;
        java.awt.Color a4 = this.color;
        this.oldColor = a4;
    }
    
    protected void accept()
    {
        java.awt.Color a = this.color;
        this.oldColor = a;
    }
    
    protected void backup()
    {
        java.awt.Color a = this.color;
        int i = a.getRed();
        java.awt.Color a0 = this.color;
        int i0 = a0.getGreen();
        java.awt.Color a1 = this.color;
        int i1 = a1.getBlue();
        java.awt.Color a2 = new java.awt.Color(i, i0, i1);
        this.oldColor = a2;
    }
    
    protected boolean changed()
    {
        java.awt.Color a = this.oldColor;
        java.awt.Color a0 = this.color;
        int i = a == a0?0:1;
        return i != 0;
    }
    
    public String getAtributeName()
    {
        String s = this.atributeName;
        return s;
    }
    
    public java.awt.Color getColor()
    {
        java.awt.Color a = this.color;
        return a;
    }
    
    protected void restore()
    {
        java.awt.Color a = this.oldColor;
        this.color = a;
    }
    
    public void restoreOriginal()
    {
        java.awt.Color a = this.originalColor;
        this.color = a;
    }
    
    protected void setColor(java.awt.Color a)
    {
        this.color = a;
    }
    
    static
    {
        String s = localization.Messages.getString("ColorSelection.0");
        com.cim.AIA.ColorSelection.NORMAL_COLOR = s;
        String s0 = localization.Messages.getString("ColorSelection.1");
        com.cim.AIA.ColorSelection.BACKGROUND = s0;
        String s1 = localization.Messages.getString("ColorSelection.2");
        com.cim.AIA.ColorSelection.TEXT_COLOR = s1;
    }
}