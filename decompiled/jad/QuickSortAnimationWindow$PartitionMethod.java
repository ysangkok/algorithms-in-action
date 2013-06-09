// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QuickSortAnimationWindow.java

import com.cim.AIA.*;
import java.awt.CheckboxMenuItem;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ladderTree extends CheckboxMenuItem
{

    private static final long serialVersionUID = 0x459f0e7e29b20f0eL;
    protected LadderTree ladderTree;
    protected QuickSort quickSort;
    protected int partitionMethod;
    protected QuickSortAnimationWindow animWindow;
    protected AlgorithmWindow algoWindow;
    final QuickSortAnimationWindow this$0;

    public _cls1.val.this._cls0(String name, boolean state, QuickSort aQuickSort, String dataDir, int aPartitionMethod, String filename, 
            QuickSortAnimationWindow quickSortAnimationWindow, AlgorithmWindow algorithmWindow)
    {
        this.this$0 = QuickSortAnimationWindow.this;
        super(name, state);
        quickSort = aQuickSort;
        partitionMethod = aPartitionMethod;
        animWindow = quickSortAnimationWindow;
        algoWindow = algorithmWindow;
        ladderTree = CodeCanvas.getLadderTreeFromFile(dataDir, filename, getLogger(), getBreakPoint());
        addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e)
            {
                animWindow.resetPartitionButtons();
                setState(true);
                quickSort.setPartitionMethod(partitionMethod);
                algoWindow.setLadderTree(ladderTree);
            }

            final QuickSortAnimationWindow val$this$0;
            final QuickSortAnimationWindow.PartitionMethod this$1;

            
            {
                this$1 = QuickSortAnimationWindow.PartitionMethod.this;
                this$0 = quicksortanimationwindow;
                super();
            }
        });
    }
}
