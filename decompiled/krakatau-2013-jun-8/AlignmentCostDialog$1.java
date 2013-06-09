class AlignmentCostDialog$1 implements java.awt.event.ActionListener {
    final AlignmentCostDialog this$0;
    
    AlignmentCostDialog$1(AlignmentCostDialog a)
    {
        this.this$0 = a;
        super();
    }
    
    public void actionPerformed(java.awt.event.ActionEvent a)
    {
        AlignmentCostDialog a0 = this.this$0;
        a0.cancelWasPressed = false;
        AlignmentCostDialog a1 = this.this$0;
        int i = a1.checkLength()?1:0;
        if(i != 0)
        {
            AlignmentCostDialog a2 = this.this$0;
            a2.setVisible(false);
        }
    }
}