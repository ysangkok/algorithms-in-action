import java.awt.event.*;

class QuickSortAnimationWindow$PartitionMethod$1 implements ItemListener {
    public void itemStateChanged(final ItemEvent e) {
        PartitionMethod.this.animWindow.resetPartitionButtons();
        PartitionMethod.this.setState(true);
        PartitionMethod.this.quickSort.setPartitionMethod(PartitionMethod.this.partitionMethod);
        PartitionMethod.this.algoWindow.setLadderTree(PartitionMethod.this.ladderTree);
    }
}