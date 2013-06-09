package com.cim.common;

public class MessageDialog extends java.awt.Dialog implements java.awt.event.ActionListener {
    final private static long serialVersionUID = -5964950788549835537L;
    protected static java.awt.Frame parent;
    protected com.cim.common.MultiLineLabel label;
    protected String stringLabel;
    
    public MessageDialog(String s, boolean b, boolean b0)
    {
        java.awt.Frame a = com.cim.common.MessageDialog.parent;
        int i = b?1:0;
        int i0 = b0?1:0;
        super(a, i != 0);
        com.cim.common.MultiLineLabel a0 = new com.cim.common.MultiLineLabel(s);
        this.label = a0;
        this.stringLabel = s;
        java.awt.Panel a1 = new java.awt.Panel();
        java.awt.Button a2 = new java.awt.Button("Ok");
        a2.addActionListener((java.awt.event.ActionListener)this);
        java.awt.Component a3 = a1.add((java.awt.Component)a2);
        com.cim.common.MultiLineLabel a4 = this.label;
        this.add((java.awt.Component)a4, (Object)"Center");
        if(i0 != 0)
        {
            this.add((java.awt.Component)a1, (Object)"South");
        }
        com.cim.common.MessageDialog$1 a5 = new com.cim.common.MessageDialog$1(this);
        this.addWindowListener((java.awt.event.WindowListener)a5);
        this.pack();
        java.awt.Frame a6 = com.cim.common.MessageDialog.parent;
        java.awt.Point a7 = a6.getLocation();
        java.awt.Frame a8 = com.cim.common.MessageDialog.parent;
        java.awt.Dimension a9 = a8.getSize();
        java.awt.Toolkit a10 = this.getToolkit();
        java.awt.Dimension a11 = a10.getScreenSize();
        int i1 = a11.width;
        java.awt.Dimension a12 = this.getSize();
        int i2 = a12.width;
        int i3 = i1 - i2;
        int i4 = i3 / 2;
        int i5 = a11.height;
        java.awt.Dimension a13 = this.getSize();
        int i6 = a13.height;
        int i7 = i5 - i6;
        int i8 = i7 / 2;
        this.setLocation(i4, i8);
    }
    
    public void actionPerformed(java.awt.event.ActionEvent a)
    {
        this.setVisible(false);
        this.dispose();
    }
    
    public com.cim.common.MessageDialog createNew(String s, boolean b, boolean b0)
    {
        int i = b?1:0;
        int i0 = b0?1:0;
        com.cim.common.MessageDialog a = new com.cim.common.MessageDialog(s, i != 0, i0 != 0);
        return a;
    }
    
    static
    {
        java.awt.Frame a = new java.awt.Frame();
        com.cim.common.MessageDialog.parent = a;
    }
}