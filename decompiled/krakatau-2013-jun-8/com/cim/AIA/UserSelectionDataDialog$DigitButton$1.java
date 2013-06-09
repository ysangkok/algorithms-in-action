package com.cim.AIA;

class UserSelectionDataDialog$DigitButton$1 implements java.awt.event.ActionListener {
    final com.cim.AIA.UserSelectionDataDialog val$this$0;
    final com.cim.AIA.UserSelectionDataDialog$DigitButton this$1;
    
    UserSelectionDataDialog$DigitButton$1(com.cim.AIA.UserSelectionDataDialog$DigitButton a, com.cim.AIA.UserSelectionDataDialog a0)
    {
        this.this$1 = a;
        this.val$this$0 = a0;
        super();
    }
    
    public void actionPerformed(java.awt.event.ActionEvent a)
    {
        com.cim.AIA.UserSelectionDataDialog$DigitButton a0 = this.this$1;
        com.cim.AIA.UserSelectionDataDialog$KeyPadPanel a1 = a0.keyPadPanel;
        com.cim.AIA.UserSelectionDataDialog$DigitButton a2 = this.this$1;
        com.cim.AIA.UserSelectionDataDialog$KeyPadPanel a3 = a2.keyPadPanel;
        int i = a3.getCurrentValue();
        com.cim.AIA.UserSelectionDataDialog$DigitButton a4 = this.this$1;
        int i0 = a4.value;
        a1.setCurrentValue(i, i0);
        com.cim.AIA.UserSelectionDataDialog$DigitButton a5 = this.this$1;
        java.awt.TextField a6 = a5.label;
        a6.requestFocus();
    }
}