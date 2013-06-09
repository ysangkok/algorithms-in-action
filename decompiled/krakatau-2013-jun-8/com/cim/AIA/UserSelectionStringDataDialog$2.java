package com.cim.AIA;

class UserSelectionStringDataDialog$2 implements java.awt.event.ActionListener {
    final com.cim.AIA.UserSelectionStringDataDialog this$0;
    
    UserSelectionStringDataDialog$2(com.cim.AIA.UserSelectionStringDataDialog a)
    {
        this.this$0 = a;
        super();
    }
    
    public void actionPerformed(java.awt.event.ActionEvent a)
    {
        com.cim.AIA.UserSelectionStringDataDialog a0 = this.this$0;
        a0.cancelWasPressed = true;
        com.cim.AIA.UserSelectionStringDataDialog a1 = this.this$0;
        a1.setVisible(false);
    }
}