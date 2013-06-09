class RedBlackTreeAnimationWindow$6 implements java.awt.event.ActionListener {
    final RedBlackTree val$redBlackTree;
    final com.cim.AIA.AlgorithmThread val$t1;
    final RedBlackTreeAnimationWindow this$0;
    
    RedBlackTreeAnimationWindow$6(RedBlackTreeAnimationWindow a, RedBlackTree a0, com.cim.AIA.AlgorithmThread a1)
    {
        this.this$0 = a;
        this.val$redBlackTree = a0;
        this.val$t1 = a1;
        super();
    }
    
    public void actionPerformed(java.awt.event.ActionEvent a)
    {
        RedBlackTree a0 = this.val$redBlackTree;
        a0.updateTuteTree();
        com.cim.AIA.AlgorithmThread a1 = this.val$t1;
        a1.repaint();
    }
}