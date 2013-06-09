package com.cim.AIA;

class UserSelectionDataDialog$5 implements java.awt.event.ActionListener {
    final com.cim.AIA.UserSelectionDataDialog this$0;
    
    UserSelectionDataDialog$5(com.cim.AIA.UserSelectionDataDialog a)
    {
        this.this$0 = a;
        super();
    }
    
    public void actionPerformed(java.awt.event.ActionEvent a)
    {
        com.cim.AIA.UserSelectionDataDialog a0 = this.this$0;
        a0.cancelWasPressed = true;
        com.cim.AIA.UserSelectionDataDialog a1 = this.this$0;
        a1.setVisible(false);
    }
}