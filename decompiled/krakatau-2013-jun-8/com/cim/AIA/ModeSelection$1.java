package com.cim.AIA;

class ModeSelection$1 implements java.awt.event.ItemListener {
    final com.cim.AIA.ModeSelection this$0;
    
    ModeSelection$1(com.cim.AIA.ModeSelection a)
    {
        this.this$0 = a;
        super();
    }
    
    public void itemStateChanged(java.awt.event.ItemEvent a)
    {
        com.cim.AIA.ModeSelection a0 = this.this$0;
        a0.activate();
    }
}