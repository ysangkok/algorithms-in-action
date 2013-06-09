public class QuickSortAnimationWindow$PartitionMethod extends java.awt.CheckboxMenuItem {
    final private static long serialVersionUID = 5016744444942225166L;
    protected com.cim.AIA.LadderTree ladderTree;
    protected QuickSort quickSort;
    protected int partitionMethod;
    protected QuickSortAnimationWindow animWindow;
    protected com.cim.AIA.AlgorithmWindow algoWindow;
    final QuickSortAnimationWindow this$0;
    
    public QuickSortAnimationWindow$PartitionMethod(QuickSortAnimationWindow a, String s, boolean b, QuickSort a0, String s0, int i, String s1, QuickSortAnimationWindow a1, com.cim.AIA.AlgorithmWindow a2)
    {
        this.this$0 = a;
        int i0 = b?1:0;
        super(s, i0 != 0);
        this.quickSort = a0;
        this.partitionMethod = i;
        this.animWindow = a1;
        this.algoWindow = a2;
        com.cim.AIA.Logger a3 = a.getLogger();
        com.cim.AIA.BreakPoint a4 = a.getBreakPoint();
        com.cim.AIA.LadderTree a5 = com.cim.AIA.CodeCanvas.getLadderTreeFromFile(s0, s1, a3, a4);
        this.ladderTree = a5;
        QuickSortAnimationWindow$PartitionMethod$1 a6 = new QuickSortAnimationWindow$PartitionMethod$1(this, a);
        this.addItemListener((java.awt.event.ItemListener)a6);
    }
}