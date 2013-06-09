import java.awt.event.*;

class ShellSortAnimationWindow$ComparisonOrder$1 implements ItemListener {
    public void itemStateChanged(final ItemEvent e) {
        ComparisonOrder.this.animWindow.resetComparisonButtons();
        ComparisonOrder.this.setState(true);
        ComparisonOrder.this.shellSort.setComparisonOrder(ComparisonOrder.this.comparisonOrder);
        ComparisonOrder.this.algoWindow.setLadderTree(ComparisonOrder.this.ladderTree);
    }
}