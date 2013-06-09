// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RedBlackTreeAnimationWindow.java

import com.cim.common.MessageDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import localization.Messages;

class val.redBlackTree
    implements ActionListener
{

    public void actionPerformed(ActionEvent e)
    {
        if(RedBlackTreeAnimationWindow.access$000(RedBlackTreeAnimationWindow.this))
        {
            MessageDialog msg = new MessageDialog(Messages.getString("RedBlackTreeAnimationWindow.14"), true, true);
            msg.setTitle(Messages.getString("RedBlackTreeAnimationWindow.15"));
            msg.setVisible(true);
            return;
        }
        if(RedBlackTreeAnimationWindow.access$100(RedBlackTreeAnimationWindow.this))
        {
            val$redBlackTree.leftRotateRequest();
        } else
        {
            MessageDialog msg = new MessageDialog(Messages.getString("RedBlackTreeAnimationWindow.16"), true, true);
            msg.setTitle(Messages.getString("RedBlackTreeAnimationWindow.17"));
            msg.setVisible(true);
            return;
        }
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
