import java.awt.event.*;
import com.cim.AIA.*;
import java.awt.*;

public class PartitionMethod extends CheckboxMenuItem
{
    private static final long serialVersionUID = 5016744444942225166L;
    protected LadderTree ladderTree;
    protected QuickSort quickSort;
    protected int partitionMethod;
    protected QuickSortAnimationWindow animWindow;
    protected AlgorithmWindow algoWindow;
    
    public PartitionMethod(final String name, final boolean state, final QuickSort aQuickSort, final String dataDir, final int aPartitionMethod, final String filename, final QuickSortAnimationWindow quickSortAnimationWindow, final AlgorithmWindow algorithmWindow) {
        super(name, state);
        this.quickSort = aQuickSort;
        this.partitionMethod = aPartitionMethod;
        this.animWindow = quickSortAnimationWindow;
        this.algoWindow = algorithmWindow;
        this.ladderTree = CodeCanvas.getLadderTreeFromFile(dataDir, filename, this$0.getLogger(), this$0.getBreakPoint());
        this.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent e) {
                PartitionMethod.this.animWindow.resetPartitionButtons();
                PartitionMethod.this.setState(true);
                PartitionMethod.this.quickSort.setPartitionMethod(PartitionMethod.this.partitionMethod);
                PartitionMethod.this.algoWindow.setLadderTree(PartitionMethod.this.ladderTree);
            }
        });
    }
}
