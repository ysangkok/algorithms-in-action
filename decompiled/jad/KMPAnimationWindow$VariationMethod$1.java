// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KMPAnimationWindow.java

import com.cim.AIA.AlgorithmWindow;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class val.this._cls0
    implements ItemListener
{

    public void itemStateChanged(ItemEvent e)
    {
        imWindow.resetVariationButtons();
        tState(true);
        p.setVariation(riationMethod);
        resetThread(true, true, false, false);
        goWindow.setLadderTree(dderTree);
    }

    final KMPAnimationWindow val$this$0;
    final dderTree this$1;

    ()
    {
        this$1 = final_;
        val$this$0 = KMPAnimationWindow.this;
        super();
    }
}
