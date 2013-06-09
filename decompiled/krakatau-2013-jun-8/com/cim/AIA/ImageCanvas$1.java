package com.cim.AIA;

class ImageCanvas$1 implements java.awt.event.MouseListener {
    final com.cim.AIA.ImageCanvas this$0;
    
    ImageCanvas$1(com.cim.AIA.ImageCanvas a)
    {
        this.this$0 = a;
        super();
    }
    
    public void mouseClicked(java.awt.event.MouseEvent a)
    {
    }
    
    public void mouseEntered(java.awt.event.MouseEvent a)
    {
        com.cim.AIA.ImageCanvas a0 = this.this$0;
        com.cim.AIA.ImageCanvas a1 = this.this$0;
        String s = a1.instructions;
        com.cim.AIA.HelpEvent a2 = new com.cim.AIA.HelpEvent((Object)this, "Algorithms in Action", s);
        a0.help(a2);
    }
    
    public void mouseExited(java.awt.event.MouseEvent a)
    {
    }
    
    public void mousePressed(java.awt.event.MouseEvent a)
    {
    }
    
    public void mouseReleased(java.awt.event.MouseEvent a)
    {
    }
}