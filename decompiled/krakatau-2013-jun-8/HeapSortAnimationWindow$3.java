class HeapSortAnimationWindow$3 implements java.awt.event.ItemListener {
    final HeapSortAnimationWindow this$0;
    
    HeapSortAnimationWindow$3(HeapSortAnimationWindow a)
    {
        this.this$0 = a;
        super();
    }
    
    public void itemStateChanged(java.awt.event.ItemEvent a)
    {
        HeapSortAnimationWindow a0 = this.this$0;
        HeapSortCanvas a1 = a0.heapSortCanvas;
        HeapSortAnimationWindow a2 = this.this$0;
        int i = a2.easyMode?1:0;
        a1.setMarkersEnabled(i != 0);
    }
}