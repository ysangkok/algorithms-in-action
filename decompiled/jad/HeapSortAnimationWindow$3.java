// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HeapSortAnimationWindow.java

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class this._cls0
    implements ItemListener
{

    public void itemStateChanged(ItemEvent e)
    {
        heapSortCanvas.setMarkersEnabled(easyMode);
    }

    final HeapSortAnimationWindow this$0;

    r()
    {
        this$0 = HeapSortAnimationWindow.this;
        super();
    }
}
