package com.cim.AIA;

class HelpableButton$1 implements java.awt.event.MouseListener {
    final com.cim.AIA.HelpableButton this$0;
    
    HelpableButton$1(com.cim.AIA.HelpableButton a)
    {
        this.this$0 = a;
        super();
    }
    
    public void mouseClicked(java.awt.event.MouseEvent a)
    {
    }
    
    public void mouseEntered(java.awt.event.MouseEvent a)
    {
        com.cim.AIA.HelpableButton a0 = this.this$0;
        com.cim.AIA.HelpableButton a1 = this.this$0;
        String s = a1.title;
        com.cim.AIA.HelpableButton a2 = this.this$0;
        String s0 = a2.instructions;
        com.cim.AIA.HelpEvent a3 = new com.cim.AIA.HelpEvent((Object)this, s, s0);
        a0.help(a3);
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