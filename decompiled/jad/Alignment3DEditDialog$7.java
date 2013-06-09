// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Alignment3DEditDialog.java

import java.awt.Scrollbar;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

class val.algorithm
    implements AdjustmentListener
{

    public void adjustmentValueChanged(AdjustmentEvent e)
    {
        val$algorithm.translateY(yTranslate.getValue());
    }

    final Alignment val$algorithm;
    final Alignment3DEditDialog this$0;

    tEvent()
    {
        this$0 = final_alignment3deditdialog;
        val$algorithm = Alignment.this;
        super();
    }
}
