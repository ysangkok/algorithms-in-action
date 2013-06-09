// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RedBlackTreeAnimationWindow.java

import com.cim.AIA.AlgorithmThread;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class val.t1
    implements ActionListener
{

    public void actionPerformed(ActionEvent e)
    {
        val$redBlackTree.updateTuteTree();
        val$t1.repaint();
    }

    final RedBlackTree val$redBlackTree;
    final AlgorithmThread val$t1;
    final RedBlackTreeAnimationWindow this$0;

    ()
    {
        this$0 = final_redblacktreeanimationwindow;
        val$redBlackTree = redblacktree;
        val$t1 = AlgorithmThread.this;
        super();
    }
}
