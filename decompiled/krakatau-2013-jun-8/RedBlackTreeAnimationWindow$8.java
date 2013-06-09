class RedBlackTreeAnimationWindow$8 implements java.awt.event.AdjustmentListener {
    final RedBlackTree val$redBlackTree;
    final RedBlackTreeAnimationWindow this$0;
    
    RedBlackTreeAnimationWindow$8(RedBlackTreeAnimationWindow a, RedBlackTree a0)
    {
        this.this$0 = a;
        this.val$redBlackTree = a0;
        super();
    }
    
    public void adjustmentValueChanged(java.awt.event.AdjustmentEvent a)
    {
        RedBlackTree a0 = this.val$redBlackTree;
        RedBlackTreeAnimationWindow a1 = this.this$0;
        int i = RedBlackTreeAnimationWindow.access$200(a1);
        RedBlackTreeAnimationWindow a2 = this.this$0;
        java.awt.Scrollbar a3 = a2.speedBar;
        int i0 = a3.getValue();
        int i1 = i - i0;
        a0.setAnimDuration(i1);
        RedBlackTreeAnimationWindow a4 = this.this$0;
        RedBlackTreeCanvas a5 = a4.tuteCanvas;
        RedBlackTreeAnimationWindow a6 = this.this$0;
        int i2 = RedBlackTreeAnimationWindow.access$200(a6);
        RedBlackTreeAnimationWindow a7 = this.this$0;
        java.awt.Scrollbar a8 = a7.speedBar;
        int i3 = a8.getValue();
        int i4 = i2 - i3;
        a5.setTweenSpeed(i4);
    }
}