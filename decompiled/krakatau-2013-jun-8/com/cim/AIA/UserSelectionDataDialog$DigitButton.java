package com.cim.AIA;

class UserSelectionDataDialog$DigitButton extends java.awt.Button {
    final private static long serialVersionUID = -1509632018780586504L;
    protected int value;
    protected com.cim.AIA.UserSelectionDataDialog$KeyPadPanel keyPadPanel;
    protected java.awt.TextField label;
    final com.cim.AIA.UserSelectionDataDialog this$0;
    
    public UserSelectionDataDialog$DigitButton(com.cim.AIA.UserSelectionDataDialog a, int i, com.cim.AIA.UserSelectionDataDialog$KeyPadPanel a0)
    {
        this.this$0 = a;
        StringBuilder a1 = new StringBuilder();
        String s = localization.Messages.getString("UserSelectionDataDialog.17");
        StringBuilder a2 = a1.append(s);
        StringBuilder a3 = a2.append(i);
        String s0 = a3.toString();
        super(s0);
        this.value = 0;
        this.value = i;
        this.keyPadPanel = a0;
        java.awt.TextField a4 = a0.label;
        this.label = a4;
        com.cim.AIA.UserSelectionDataDialog$DigitButton$1 a5 = new com.cim.AIA.UserSelectionDataDialog$DigitButton$1(this, a);
        this.addActionListener((java.awt.event.ActionListener)a5);
    }
}