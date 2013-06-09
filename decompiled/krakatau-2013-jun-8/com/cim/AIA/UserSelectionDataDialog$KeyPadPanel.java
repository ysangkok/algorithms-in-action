package com.cim.AIA;

class UserSelectionDataDialog$KeyPadPanel extends java.awt.Panel {
    final private static long serialVersionUID = 1013169127766911662L;
    protected com.cim.AIA.UserSelectionDataDialog$DigitButton[] buttons;
    protected java.awt.TextField label;
    protected int maximumValue;
    protected int minimumValue;
    final com.cim.AIA.UserSelectionDataDialog this$0;
    
    public UserSelectionDataDialog$KeyPadPanel(com.cim.AIA.UserSelectionDataDialog a, java.awt.TextField a0, int i, int i0)
    {
        this.this$0 = a;
        super();
        com.cim.AIA.UserSelectionDataDialog$DigitButton[] a1 = new com.cim.AIA.UserSelectionDataDialog$DigitButton[10];
        this.buttons = a1;
        this.maximumValue = 0;
        this.minimumValue = 0;
        this.label = a0;
        this.minimumValue = i;
        this.maximumValue = i0;
        java.awt.GridLayout a2 = new java.awt.GridLayout(1, 10);
        this.setLayout((java.awt.LayoutManager)a2);
        int i1 = 0;
        while(true)
        {
            com.cim.AIA.UserSelectionDataDialog$DigitButton[] a3 = this.buttons;
            int i2 = a3.length;
            if(i1 >= i2)
            {
                return;
            }
            else
            {
                com.cim.AIA.UserSelectionDataDialog$DigitButton[] a4 = this.buttons;
                com.cim.AIA.UserSelectionDataDialog$DigitButton a5 = new com.cim.AIA.UserSelectionDataDialog$DigitButton(a, i1, this);
                a4[i1] = a5;
                com.cim.AIA.UserSelectionDataDialog$DigitButton[] a6 = this.buttons;
                com.cim.AIA.UserSelectionDataDialog$DigitButton a7 = a6[i1];
                java.awt.Component a8 = this.add((java.awt.Component)a7);
                int i3 = i1 + 1;
                i1 = i3;
            }
        }
    }
    
    public void adjustButtons()
    {
        int i = this.getCurrentValue();
        int i0 = this.maximumValue;
        if(i > i0)
        {
            this.allowInput(false);
        }
        else
        {
            this.allowInput(true);
        }
    }
    
    protected void allowInput(boolean b)
    {
        int i = b?1:0;
        int i0 = 0;
        while(true)
        {
            com.cim.AIA.UserSelectionDataDialog$DigitButton[] a = this.buttons;
            int i1 = i;
            int i2 = a.length;
            int i3 = i1;
            int i4 = i3;
            if(i0 >= i2)
            {
                return;
            }
            else
            {
                int i5 = i4;
                com.cim.AIA.UserSelectionDataDialog$DigitButton[] a0 = this.buttons;
                int i6 = i5;
                com.cim.AIA.UserSelectionDataDialog$DigitButton a1 = a0[i0];
                int i7 = i6;
                a1.setEnabled(i7 != 0);
                int i8 = i7;
                int i9 = i0 + 1;
                i = i8;
                i0 = i9;
            }
        }
    }
    
    public int getCurrentValue()
    {
        int i = 0;
        java.awt.TextField a = this.label;
        String s = a.getText();
        try
        {
            int i0 = Integer.parseInt(s);
            i = i0;
        }
        catch(NumberFormatException ignoredException)
        {
            i = 0;
        }
        return i;
    }
    
    public boolean isValid()
    {
        int i = 0;
        java.awt.TextField a = this.label;
        label1: {
            label0: {
                if(a != null)
                {
                    break label0;
                }
                i = 0;
                break label1;
            }
            java.awt.TextField a0 = this.label;
            String s = a0.getText();
            try
            {
                int i0 = Integer.parseInt(s);
                i = 1;
            }
            catch(NumberFormatException ignoredException)
            {
                i = 0;
            }
        }
        return i != 0;
    }
    
    public void setCurrentValue(int i, int i0)
    {
        java.awt.TextField a = this.label;
        int i1 = a.getCaretPosition();
        label1: {
            label0: {
                if(i != 0)
                {
                    break label0;
                }
                if(i0 != 0)
                {
                    break label0;
                }
                java.awt.TextField a0 = this.label;
                String s = localization.Messages.getString("UserSelectionDataDialog.16");
                a0.setText(s);
                break label1;
            }
            java.awt.TextField a1 = this.label;
            String s0 = a1.getText();
            String s1 = s0.substring(0, i1);
            int i2 = s0.length();
            String s2 = s0.substring(i1, i2);
            java.awt.TextField a2 = this.label;
            StringBuilder a3 = new StringBuilder();
            String s3 = localization.Messages.getString("UserSelectionDataDialog.15");
            StringBuilder a4 = a3.append(s3);
            StringBuilder a5 = a4.append(s1);
            StringBuilder a6 = a5.append(i0);
            StringBuilder a7 = a6.append(s2);
            String s4 = a7.toString();
            a2.setText(s4);
        }
        java.awt.TextField a8 = this.label;
        int i3 = i1 + 1;
        a8.setCaretPosition(i3);
        this.adjustButtons();
    }
}