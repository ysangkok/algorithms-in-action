package com.cim.AIA;

public class HelpableButton extends java.awt.Button implements com.cim.AIA.Helpable {
    final private static long serialVersionUID = -1701437300541814144L;
    protected java.util.Vector helpListeners;
    protected String title;
    protected String instructions;
    
    public HelpableButton(String s, String s0)
    {
        super(s);
        java.util.Vector a = new java.util.Vector();
        this.helpListeners = a;
        this.title = "";
        this.instructions = "";
        this.title = s;
        this.instructions = s0;
        com.cim.AIA.HelpableButton$1 a0 = new com.cim.AIA.HelpableButton$1(this);
        this.addMouseListener((java.awt.event.MouseListener)a0);
    }
    
    public void addHelpListener(com.cim.AIA.HelpListener a)
    {
        java.util.Vector a0 = this.helpListeners;
        Object a1 = a;
        a0.addElement(a1);
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
                ((com.cim.AIA.HelpListener)a2).processHelpEvent(a);
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    public void removeAllHelpListeners()
    {
        java.util.Vector a = this.helpListeners;
        a.removeAllElements();
    }
    
    public void removeHelpListener(com.cim.AIA.HelpListener a)
    {
        java.util.Vector a0 = this.helpListeners;
        Object a1 = a;
        int i = a0.removeElement(a1)?1:0;
    }
}