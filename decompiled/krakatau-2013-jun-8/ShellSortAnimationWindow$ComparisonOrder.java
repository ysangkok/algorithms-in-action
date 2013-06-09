public class ShellSortAnimationWindow$ComparisonOrder extends java.awt.CheckboxMenuItem {
    final private static long serialVersionUID = 8837469967096307285L;
    protected com.cim.AIA.LadderTree ladderTree;
    protected ShellSort shellSort;
    protected int comparisonOrder;
    protected ShellSortAnimationWindow animWindow;
    protected com.cim.AIA.AlgorithmWindow algoWindow;
    final ShellSortAnimationWindow this$0;
    
    public ShellSortAnimationWindow$ComparisonOrder(ShellSortAnimationWindow a, String s, boolean b, ShellSort a0, String s0, int i, String s1, ShellSortAnimationWindow a1, com.cim.AIA.AlgorithmWindow a2)
    {
        this.this$0 = a;
        int i0 = b?1:0;
        super(s, i0 != 0);
        this.shellSort = a0;
        this.comparisonOrder = i;
        this.animWindow = a1;
        this.algoWindow = a2;
        com.cim.AIA.Logger a3 = a.getLogger();
        com.cim.AIA.BreakPoint a4 = a.getBreakPoint();
        com.cim.AIA.LadderTree a5 = com.cim.AIA.CodeCanvas.getLadderTreeFromFile(s0, s1, a3, a4);
        this.ladderTree = a5;
        ShellSortAnimationWindow$ComparisonOrder$1 a6 = new ShellSortAnimationWindow$ComparisonOrder$1(this, a);
        this.addItemListener((java.awt.event.ItemListener)a6);
    }
}