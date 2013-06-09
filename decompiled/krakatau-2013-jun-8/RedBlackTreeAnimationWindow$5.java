class RedBlackTreeAnimationWindow$5 implements java.awt.event.ActionListener {
    final RedBlackTree val$redBlackTree;
    final RedBlackTreeAnimationWindow this$0;
    
    RedBlackTreeAnimationWindow$5(RedBlackTreeAnimationWindow a, RedBlackTree a0)
    {
        this.this$0 = a;
        this.val$redBlackTree = a0;
        super();
    }
    
    public void actionPerformed(java.awt.event.ActionEvent a)
    {
        RedBlackTreeAnimationWindow a0 = this.this$0;
        int i = RedBlackTreeAnimationWindow.access$000(a0)?1:0;
        label1: {
            label0: {
                if(i == 0)
                {
                    break label0;
                }
                String s = localization.Messages.getString("RedBlackTreeAnimationWindow.18");
                com.cim.common.MessageDialog a1 = new com.cim.common.MessageDialog(s, true, true);
                String s0 = localization.Messages.getString("RedBlackTreeAnimationWindow.19");
                a1.setTitle(s0);
                a1.setVisible(true);
                break label1;
            }
            RedBlackTreeAnimationWindow a2 = this.this$0;
            int i0 = RedBlackTreeAnimationWindow.access$100(a2)?1:0;
            if(i0 == 0)
            {
                String s1 = localization.Messages.getString("RedBlackTreeAnimationWindow.20");
                com.cim.common.MessageDialog a3 = new com.cim.common.MessageDialog(s1, true, true);
                String s2 = localization.Messages.getString("RedBlackTreeAnimationWindow.21");
                a3.setTitle(s2);
                a3.setVisible(true);
            }
            else
            {
                RedBlackTree a4 = this.val$redBlackTree;
                a4.rightRotateRequest();
            }
        }
    }
}