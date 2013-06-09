class ShellSortAnimationWindow$ComparisonOrder$1 implements java.awt.event.ItemListener {
    final ShellSortAnimationWindow val$this$0;
    final ShellSortAnimationWindow$ComparisonOrder this$1;
    
    ShellSortAnimationWindow$ComparisonOrder$1(ShellSortAnimationWindow$ComparisonOrder a, ShellSortAnimationWindow a0)
    {
        this.this$1 = a;
        this.val$this$0 = a0;
        super();
    }
    
    public void itemStateChanged(java.awt.event.ItemEvent a)
    {
        ShellSortAnimationWindow$ComparisonOrder a0 = this.this$1;
        ShellSortAnimationWindow a1 = a0.animWindow;
        a1.resetComparisonButtons();
        ShellSortAnimationWindow$ComparisonOrder a2 = this.this$1;
        a2.setState(true);
        ShellSortAnimationWindow$ComparisonOrder a3 = this.this$1;
        ShellSort a4 = a3.shellSort;
        ShellSortAnimationWindow$ComparisonOrder a5 = this.this$1;
        int i = a5.comparisonOrder;
        a4.setComparisonOrder(i);
        ShellSortAnimationWindow$ComparisonOrder a6 = this.this$1;
        com.cim.AIA.AlgorithmWindow a7 = a6.algoWindow;
        ShellSortAnimationWindow$ComparisonOrder a8 = this.this$1;
        com.cim.AIA.LadderTree a9 = a8.ladderTree;
        a7.setLadderTree(a9);
    }
}