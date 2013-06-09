package com.cim.AIA;

class UserSelectionDataDialog$7 implements java.awt.event.TextListener {
    final com.cim.AIA.UserSelectionDataDialog this$0;
    
    UserSelectionDataDialog$7(com.cim.AIA.UserSelectionDataDialog a)
    {
        this.this$0 = a;
        super();
    }
    
    public void textValueChanged(java.awt.event.TextEvent a)
    {
        com.cim.AIA.UserSelectionDataDialog a0 = this.this$0;
        com.cim.AIA.UserSelectionDataDialog$KeyPadPanel a1 = a0.keyPad;
        a1.adjustButtons();
        com.cim.AIA.UserSelectionDataDialog a2 = this.this$0;
        a2.initialiseComponents();
    }
}