import java.awt.event.*;
import com.cim.AIA.*;
import java.awt.*;

public class ComparisonOrder extends CheckboxMenuItem
{
    private static final long serialVersionUID = 8837469967096307285L;
    protected LadderTree ladderTree;
    protected ShellSort shellSort;
    protected int comparisonOrder;
    protected ShellSortAnimationWindow animWindow;
    protected AlgorithmWindow algoWindow;
    
    public ComparisonOrder(final String name, final boolean state, final ShellSort aShellSort, final String dataDir, final int aComparisonOrder, final String filename, final ShellSortAnimationWindow shellSortAnimationWindow, final AlgorithmWindow algorithmWindow) {
        super(name, state);
        this.shellSort = aShellSort;
        this.comparisonOrder = aComparisonOrder;
        this.animWindow = shellSortAnimationWindow;
        this.algoWindow = algorithmWindow;
        this.ladderTree = CodeCanvas.getLadderTreeFromFile(dataDir, filename, this$0.getLogger(), this$0.getBreakPoint());
        this.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent e) {
                ComparisonOrder.this.animWindow.resetComparisonButtons();
                ComparisonOrder.this.setState(true);
                ComparisonOrder.this.shellSort.setComparisonOrder(ComparisonOrder.this.comparisonOrder);
                ComparisonOrder.this.algoWindow.setLadderTree(ComparisonOrder.this.ladderTree);
            }
        });
    }
}
