package com.cim.AIA;

class ImageCanvas extends java.awt.Canvas implements com.cim.AIA.Helpable {
    final private static long serialVersionUID = 6090991999894247359L;
    protected java.util.Vector helpListeners;
    protected String instructions;
    private java.awt.Dimension imageSize;
    private java.awt.Image image;
    
    public ImageCanvas(java.awt.Image a, java.awt.Dimension a0)
    {
        super();
        java.util.Vector a1 = new java.util.Vector();
        this.helpListeners = a1;
        this.instructions = "Developed by L.Stern, L.Naish and H.Sondergaard at The University of Melbourne. (C) 2001";
        this.image = a;
        java.awt.Image a2 = this.image;
        if(a2 == null)
        {
            java.awt.Dimension a3 = new java.awt.Dimension(50, 50);
            this.imageSize = a3;
        }
        else
        {
            java.awt.Image a4 = this.image;
            int i = a4.getWidth((java.awt.image.ImageObserver)this);
            java.awt.Image a5 = this.image;
            int i0 = a5.getHeight((java.awt.image.ImageObserver)this);
            java.awt.Dimension a6 = new java.awt.Dimension(i, i0);
            this.imageSize = a6;
        }
        com.cim.AIA.ImageCanvas$1 a7 = new com.cim.AIA.ImageCanvas$1(this);
        this.addMouseListener((java.awt.event.MouseListener)a7);
    }
    
    public void addHelpListener(com.cim.AIA.HelpListener a)
    {
        java.util.Vector a0 = this.helpListeners;
        Object a1 = a;
        a0.addElement(a1);
    }
    
    public void cleanUp()
    {
        java.util.Vector a = this.helpListeners;
        if(a != null)
        {
            java.util.Vector a0 = this.helpListeners;
            a0.removeAllElements();
        }
        this.helpListeners = null;
        java.awt.Image a1 = this.image;
        if(a1 != null)
        {
            java.awt.Image a2 = this.image;
            a2.flush();
        }
        this.image = null;
    }
    
    public java.awt.Dimension getSize()
    {
        java.awt.Dimension a = this.imageSize;
        return a;
    }
    
    protected void help(com.cim.AIA.HelpEvent a)
    {
        int i = 0;
        while(true)
        {
            java.util.Vector a0 = this.helpListeners;
            int i0 = a0.size();
            if(i >= i0)
            {
                return;
            }
            else
            {
                java.util.Vector a1 = this.helpListeners;
                Object a2 = a1.elementAt(i);
                com.cim.AIA.HelpListener dummy = (com.cim.AIA.HelpListener)a2;
                com.cim.AIA.HelpListener dummy0 = (com.cim.AIA.HelpListener)a2;
                ((com.cim.AIA.HelpListener)a2).processHelpEvent(a);
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    public void paint(java.awt.Graphics a)
    {
        java.awt.Image a0 = this.image;
        if(a0 != null)
        {
            java.awt.Image a1 = this.image;
            int i = a.drawImage(a1, 0, 0, (java.awt.image.ImageObserver)this)?1:0;
        }
    }
    
    public void removeHelpListener(com.cim.AIA.HelpListener a)
    {
        java.util.Vector a0 = this.helpListeners;
        Object a1 = a;
        int i = a0.removeElement(a1)?1:0;
    }
}