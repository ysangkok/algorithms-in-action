package com.cim.AIA;

class UserSelectionStringDataDialog$1 implements java.awt.event.ActionListener {
    final com.cim.AIA.UserSelectionStringDataDialog this$0;
    
    UserSelectionStringDataDialog$1(com.cim.AIA.UserSelectionStringDataDialog a)
    {
        this.this$0 = a;
        super();
    }
    
    public void actionPerformed(java.awt.event.ActionEvent a)
    {
        com.cim.AIA.UserSelectionStringDataDialog a0 = this.this$0;
        a0.cancelWasPressed = false;
        com.cim.AIA.UserSelectionStringDataDialog a1 = this.this$0;
        int i = a1.checkLength()?1:0;
        label0: {
            if(i == 0)
            {
                break label0;
            }
            com.cim.AIA.UserSelectionStringDataDialog a2 = this.this$0;
            int i0 = a2.checkParams()?1:0;
            if(i0 != 0)
            {
                com.cim.AIA.UserSelectionStringDataDialog a3 = this.this$0;
                a3.setVisible(false);
            }
        }
    }
}