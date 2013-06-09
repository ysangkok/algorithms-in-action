class QuickSortAnimationWindow$PartitionMethod$1 implements java.awt.event.ItemListener {
    final QuickSortAnimationWindow val$this$0;
    final QuickSortAnimationWindow$PartitionMethod this$1;
    
    QuickSortAnimationWindow$PartitionMethod$1(QuickSortAnimationWindow$PartitionMethod a, QuickSortAnimationWindow a0)
    {
        this.this$1 = a;
        this.val$this$0 = a0;
        super();
    }
    
    public void itemStateChanged(java.awt.event.ItemEvent a)
    {
        QuickSortAnimationWindow$PartitionMethod a0 = this.this$1;
        QuickSortAnimationWindow a1 = a0.animWindow;
        a1.resetPartitionButtons();
        QuickSortAnimationWindow$PartitionMethod a2 = this.this$1;
        a2.setState(true);
        QuickSortAnimationWindow$PartitionMethod a3 = this.this$1;
        QuickSort a4 = a3.quickSort;
        QuickSortAnimationWindow$PartitionMethod a5 = this.this$1;
        int i = a5.partitionMethod;
        a4.setPartitionMethod(i);
        QuickSortAnimationWindow$PartitionMethod a6 = this.this$1;
        com.cim.AIA.AlgorithmWindow a7 = a6.algoWindow;
        QuickSortAnimationWindow$PartitionMethod a8 = this.this$1;
        com.cim.AIA.LadderTree a9 = a8.ladderTree;
        a7.setLadderTree(a9);
    }
}