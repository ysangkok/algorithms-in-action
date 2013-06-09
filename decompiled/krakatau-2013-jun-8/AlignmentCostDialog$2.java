class AlignmentCostDialog$2 implements java.awt.event.ActionListener {
    final AlignmentCostDialog this$0;
    
    AlignmentCostDialog$2(AlignmentCostDialog a)
    {
        this.this$0 = a;
        super();
    }
    
    public void actionPerformed(java.awt.event.ActionEvent a)
    {
        AlignmentCostDialog a0 = this.this$0;
        a0.cancelWasPressed = true;
        AlignmentCostDialog a1 = this.this$0;
        a1.setVisible(false);
    }
}