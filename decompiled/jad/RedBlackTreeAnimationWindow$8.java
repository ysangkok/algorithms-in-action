// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RedBlackTreeAnimationWindow.java

import java.awt.Scrollbar;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

class val.redBlackTree
    implements AdjustmentListener
{

    public void adjustmentValueChanged(AdjustmentEvent e)
    {
        val$redBlackTree.setAnimDuration(RedBlackTreeAnimationWindow.access$200(RedBlackTreeAnimationWindow.this) - speedBar.getValue());
        tuteCanvas.setTweenSpeed(RedBlackTreeAnimationWindow.access$200(RedBlackTreeAnimationWindow.this) - speedBar.getValue());
    }

    final RedBlackTree val$redBlackTree;
    final RedBlackTreeAnimationWindow this$0;

    ()
    {
        this$0 = final_redblacktreeanimationwindow;
        val$redBlackTree = RedBlackTree.this;
        super();
    }
}
