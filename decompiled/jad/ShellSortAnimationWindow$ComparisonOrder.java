// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ShellSortAnimationWindow.java

import com.cim.AIA.*;
import java.awt.CheckboxMenuItem;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ladderTree extends CheckboxMenuItem
{

    private static final long serialVersionUID = 0x7aa5001cb5f55e55L;
    protected LadderTree ladderTree;
    protected ShellSort shellSort;
    protected int comparisonOrder;
    protected ShellSortAnimationWindow animWindow;
    protected AlgorithmWindow algoWindow;
    final ShellSortAnimationWindow this$0;

    public _cls1.val.this._cls0(String name, boolean state, ShellSort aShellSort, String dataDir, int aComparisonOrder, String filename, 
            ShellSortAnimationWindow shellSortAnimationWindow, AlgorithmWindow algorithmWindow)
    {
        this.this$0 = ShellSortAnimationWindow.this;
        super(name, state);
        shellSort = aShellSort;
        comparisonOrder = aComparisonOrder;
        animWindow = shellSortAnimationWindow;
        algoWindow = algorithmWindow;
        ladderTree = CodeCanvas.getLadderTreeFromFile(dataDir, filename, getLogger(), getBreakPoint());
        addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e)
            {
                animWindow.resetComparisonButtons();
                setState(true);
                shellSort.setComparisonOrder(comparisonOrder);
                algoWindow.setLadderTree(ladderTree);
            }

            final ShellSortAnimationWindow val$this$0;
            final ShellSortAnimationWindow.ComparisonOrder this$1;

            
            {
                this$1 = ShellSortAnimationWindow.ComparisonOrder.this;
                this$0 = shellsortanimationwindow;
                super();
            }
        });
    }
}
