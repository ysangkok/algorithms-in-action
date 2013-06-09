package com.cim.AIA;

class UserSelectionDataDialog$6 implements java.awt.event.KeyListener {
    final com.cim.AIA.UserSelectionDataDialog this$0;
    
    UserSelectionDataDialog$6(com.cim.AIA.UserSelectionDataDialog a)
    {
        this.this$0 = a;
        super();
    }
    
    public void keyPressed(java.awt.event.KeyEvent a)
    {
        int i = a.getKeyCode();
        label1: {
            label0: {
                if(i != 10)
                {
                    break label0;
                }
                com.cim.AIA.UserSelectionDataDialog a0 = this.this$0;
                com.cim.AIA.UserSelectionDataDialog$ElementArrayCanvas a1 = a0.elementArrayCanvas;
                int i0 = a1.getNumberOfElements();
                com.cim.AIA.UserSelectionDataDialog a2 = this.this$0;
                int i1 = a2.maxNumberOfElements;
                if(i0 != i1)
                {
                    com.cim.AIA.UserSelectionDataDialog a3 = this.this$0;
                    a3.addInput(false, true);
                    break label1;
                }
                else
                {
                    com.cim.AIA.UserSelectionDataDialog a4 = this.this$0;
                    a4.modifyInput(false);
                    break label1;
                }
            }
            com.cim.AIA.UserSelectionDataDialog a5 = this.this$0;
            int i2 = a5.handleAKeyEvent(a, false)?1:0;
            label2: {
                if(i2 == 0)
                {
                    break label2;
                }
                break label1;
            }
            int i3 = a.getKeyChar();
            int i4 = a.getKeyCode();
            int i5 = Character.isDigit((char)i3)?1:0;
            if(i5 != 0)
            {
                break label1;
            }
            if(i4 == 37)
            {
                break label1;
            }
            if(i4 == 39)
            {
                break label1;
            }
            if(i4 == 8)
            {
                break label1;
            }
            if(i4 != 127)
            {
                java.awt.Toolkit a6 = java.awt.Toolkit.getDefaultToolkit();
                a6.beep();
                a.consume();
            }
        }
    }
    
    public void keyReleased(java.awt.event.KeyEvent a)
    {
    }
    
    public void keyTyped(java.awt.event.KeyEvent a)
    {
    }
}