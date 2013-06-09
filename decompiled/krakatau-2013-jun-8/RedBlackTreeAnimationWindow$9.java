class RedBlackTreeAnimationWindow$9 implements java.awt.event.WindowListener {
    final RedBlackTreeAnimationWindow this$0;
    
    RedBlackTreeAnimationWindow$9(RedBlackTreeAnimationWindow a)
    {
        this.this$0 = a;
        super();
    }
    
    public void windowActivated(java.awt.event.WindowEvent a)
    {
    }
    
    public void windowClosed(java.awt.event.WindowEvent a)
    {
    }
    
    public void windowClosing(java.awt.event.WindowEvent a)
    {
        RedBlackTreeAnimationWindow a0 = this.this$0;
        java.awt.Dialog a1 = a0.tuteDialog;
        a1.setVisible(false);
    }
    
    public void windowDeactivated(java.awt.event.WindowEvent a)
    {
    }
    
    public void windowDeiconified(java.awt.event.WindowEvent a)
    {
    }
    
    public void windowIconified(java.awt.event.WindowEvent a)
    {
    }
    
    public void windowOpened(java.awt.event.WindowEvent a)
    {
    }
}