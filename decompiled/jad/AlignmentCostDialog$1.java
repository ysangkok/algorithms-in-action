// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AlignmentCostDialog.java

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class >
    implements ActionListener
{

    public void actionPerformed(ActionEvent e)
    {
        cancelWasPressed = false;
        if(checkLength())
            setVisible(false);
    }

    final AlignmentCostDialog this$0;

    vent()
    {
        this$0 = AlignmentCostDialog.this;
        super();
    }
}
