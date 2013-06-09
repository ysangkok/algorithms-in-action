package com.cim.common;

class MessageDialog$1 extends java.awt.event.WindowAdapter {
    final com.cim.common.MessageDialog this$0;
    
    MessageDialog$1(com.cim.common.MessageDialog a)
    {
        this.this$0 = a;
        super();
    }
    
    public void windowClosing(java.awt.event.WindowEvent a)
    {
        com.cim.common.MessageDialog a0 = this.this$0;
        a0.setVisible(false);
        com.cim.common.MessageDialog a1 = this.this$0;
        a1.dispose();
    }
}