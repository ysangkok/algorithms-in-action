package com.cim.AIA;

public class ProgressDialog extends java.awt.Dialog implements java.awt.event.ActionListener {
    final private static long serialVersionUID = 1331451304714434269L;
    protected boolean interactive;
    protected java.awt.Button bt_dismiss;
    protected String msg;
    protected java.awt.Frame component;
    protected com.cim.AIA.ProgressBar progressbar;
    protected int max;
    protected int step;
    protected boolean with_progress;
    
    public ProgressDialog(java.awt.Frame a, String s, String s0, boolean b)
    {
        super(a, s, b);
        int i = b?1:0;
        this.interactive = true;
        this.with_progress = false;
        java.awt.Color a0 = java.awt.Color.white;
        this.setBackground(a0);
        this.component = a;
        this.msg = s0;
        this.interactive = i != 0;
        this.initGUI();
        this.pack();
    }
    
    public ProgressDialog(java.awt.Frame a, String s, String s0, boolean b, int i, int i0)
    {
        super(a, s, b);
        int i1 = b?1:0;
        this.interactive = true;
        this.with_progress = false;
        java.awt.Color a0 = java.awt.Color.white;
        this.setBackground(a0);
        this.component = a;
        this.msg = s0;
        this.interactive = i1 != 0;
        this.max = i;
        this.step = i0;
        this.with_progress = true;
        this.initGUI();
        this.pack();
    }
    
    public void actionPerformed(java.awt.event.ActionEvent a)
    {
        Object a0 = a.getSource();
        java.awt.Button a1 = this.bt_dismiss;
        if(a0 == a1)
        {
            this.close();
        }
    }
    
    public void close()
    {
        this.setVisible(false);
        this.removeAll();
        this.dispose();
    }
    
    protected void initGUI()
    {
        String s = this.msg;
        java.awt.Label a = new java.awt.Label(s, 1);
        java.awt.Color a0 = java.awt.Color.white;
        a.setBackground(a0);
        java.awt.BorderLayout a1 = new java.awt.BorderLayout();
        a1.setHgap(5);
        this.setLayout((java.awt.LayoutManager)a1);
        java.awt.Component a2 = this.add("North", (java.awt.Component)a);
        int i = this.max;
        int i0 = this.step;
        com.cim.AIA.ProgressBar a3 = new com.cim.AIA.ProgressBar(i, i0);
        this.progressbar = a3;
        com.cim.AIA.ProgressBar a4 = this.progressbar;
        a4.setSize(10, 20);
        int i1 = this.with_progress?1:0;
        if(i1 != 0)
        {
            com.cim.AIA.ProgressBar a5 = this.progressbar;
            java.awt.Component a6 = this.add("Center", (java.awt.Component)a5);
        }
        int i2 = this.interactive?1:0;
        if(i2 == 0)
        {
            java.awt.Label a7 = new java.awt.Label("", 1);
            java.awt.Color a8 = java.awt.Color.white;
            a7.setBackground(a8);
            java.awt.Component a9 = this.add("South", (java.awt.Component)a7);
        }
        else
        {
            java.awt.Button a10 = new java.awt.Button("Dismiss");
            this.bt_dismiss = a10;
            java.awt.Button a11 = this.bt_dismiss;
            a11.addActionListener((java.awt.event.ActionListener)this);
            java.awt.Panel a12 = new java.awt.Panel();
            java.awt.FlowLayout a13 = new java.awt.FlowLayout();
            a12.setLayout((java.awt.LayoutManager)a13);
            java.awt.Color a14 = java.awt.Color.white;
            a12.setBackground(a14);
            java.awt.Button a15 = this.bt_dismiss;
            java.awt.Component a16 = a12.add((java.awt.Component)a15);
            java.awt.Component a17 = this.add("Center", (java.awt.Component)a12);
            java.awt.Button a18 = this.bt_dismiss;
            a18.requestFocus();
        }
    }
    
    public void paint()
    {
        com.cim.AIA.ProgressBar a = this.progressbar;
        if(a != null)
        {
            com.cim.AIA.ProgressBar a0 = this.progressbar;
            a0.repaint();
        }
    }
    
    public void setProgressMark(int i)
    {
        com.cim.AIA.ProgressBar a = this.progressbar;
        a.setProgressMark(i);
        com.cim.AIA.ProgressBar a0 = this.progressbar;
        a0.repaint();
    }
}