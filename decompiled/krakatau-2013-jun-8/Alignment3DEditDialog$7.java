class Alignment3DEditDialog$7 implements java.awt.event.AdjustmentListener {
    final Alignment val$algorithm;
    final Alignment3DEditDialog this$0;
    
    Alignment3DEditDialog$7(Alignment3DEditDialog a, Alignment a0)
    {
        this.this$0 = a;
        this.val$algorithm = a0;
        super();
    }
    
    public void adjustmentValueChanged(java.awt.event.AdjustmentEvent a)
    {
        Alignment a0 = this.val$algorithm;
        Alignment3DEditDialog a1 = this.this$0;
        java.awt.Scrollbar a2 = a1.yTranslate;
        int i = a2.getValue();
        a0.translateY(i);
    }
}