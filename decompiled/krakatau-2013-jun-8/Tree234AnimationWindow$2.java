class Tree234AnimationWindow$2 implements java.awt.event.WindowListener {
    final Tree234AnimationWindow this$0;
    
    Tree234AnimationWindow$2(Tree234AnimationWindow a)
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
        Tree234AnimationWindow a0 = this.this$0;
        java.awt.Dialog a1 = a0.dialog;
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