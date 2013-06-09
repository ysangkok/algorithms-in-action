// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ShellSortAnimationWindow.java

import com.cim.AIA.*;
import com.cim.common.RadioMenu;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;
import java.util.Vector;
import localization.Messages;

public class ShellSortAnimationWindow extends AnimationWindow
{
    public class ComparisonOrder extends CheckboxMenuItem
    {

        private static final long serialVersionUID = 0x7aa5001cb5f55e55L;
        protected LadderTree ladderTree;
        protected ShellSort shellSort;
        protected int comparisonOrder;
        protected ShellSortAnimationWindow animWindow;
        protected AlgorithmWindow algoWindow;
        final ShellSortAnimationWindow this$0;

        public ComparisonOrder(String name, boolean state, ShellSort aShellSort, String dataDir, int aComparisonOrder, String filename, 
                ShellSortAnimationWindow shellSortAnimationWindow, AlgorithmWindow algorithmWindow)
        {
            this$0 = ShellSortAnimationWindow.this;
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
                final ComparisonOrder this$1;


// JavaClassFileOutputException: Invalid index accessing method local variables table of <init>
            });
        }
    }


    public String getAlgorithmName()
    {
        return "AIA: ShellSort";
    }

    public ShellSortAnimationWindow(AlgorithmCanvas canvas, AlgorithmThread thread, Applet applet)
    {
        super(canvas, thread, applet);
        comparisonOrders = new Vector();
        minimumNumberOfElements = 3;
        maximumNumberOfElements = 20;
        maxSize = 99;
        minSize = 1;
        buildDate = "Build Date";
        buildBy = "Your Initial.Your Surname";
        frameTitle = FRAME_TITLE;
        addDataSelection(new PresetIntArrayDataSelection(DEFAULT_LABEL, true, this, DEFAULT_DATA));
        addDataSelection(new PresetIntArrayDataSelection(SORTED_LABEL, false, this, SORTED_DATA));
        addDataSelection(new PresetIntArrayDataSelection(REVERSE_SORTED_LABEL, false, this, REVERSE_SORTED_DATA));
        addDataSelection(new RandomSameIntArrayDataSelection(SAME_LABEL, false, this, minimumNumberOfElements, maximumNumberOfElements, minSize, maxSize));
        addDataSelection(new RandomIntArrayDataSelection(RANDOM_LABEL, false, this, minimumNumberOfElements, maximumNumberOfElements, minSize, maxSize));
        addDataSelection(new UserSelectionIntArrayDataSelection((new StringBuilder()).append(USER_SELECTION_LABEL).append("...").toString(), false, this, USER_SELECTION_LABEL, minSize, maxSize, minimumNumberOfElements, maximumNumberOfElements));
        addControlButton(new StepButton(AnimationWindow.STEP_LABEL, AnimationWindow.STEP_INSTRUCTIONS, this, thread));
        addControlButton(new BackButton(AnimationWindow.BACK_LABEL, AnimationWindow.BACK_INSTRUCTIONS, this, thread));
        addControlButton(new RunButton(AnimationWindow.RUN_LABEL, AnimationWindow.RUN_INSTRUCTIONS, this, thread));
        addControlButton(new PauseButton(AnimationWindow.PAUSE_LABEL, AnimationWindow.PAUSE_INSTRUCTIONS, this, thread));
        addControlButton(new ResetButton(AnimationWindow.RESET_LABEL, AnimationWindow.RESET_INSTRUCTIONS, this, thread));
    }

    protected void initialiseComparisonMenuItem()
    {
        RadioMenu comparisonOrder = new RadioMenu(COMPARISON, true);
        ShellSort shellSort = (ShellSort)(ShellSort)getAlgorithm();
        comparisonOrders.addElement(new ComparisonOrder(H_SUBFILES, true, shellSort, applet.getCodeBase().toString(), 10, ShellSortApplet.HSubfileComparisonFileName, this, getAlgorithmWindow()));
        comparisonOrders.addElement(new ComparisonOrder(SINGLE_PASS, false, shellSort, applet.getCodeBase().toString(), 11, ShellSortApplet.SinglePassComparisonFileName, this, getAlgorithmWindow()));
        for(int i = 0; i < comparisonOrders.size(); i++)
            comparisonOrder.add((MenuItem)comparisonOrders.elementAt(i));

        menuBar.add(comparisonOrder);
    }

    protected void initialiseMenuBar()
    {
        menuBar = new MenuBar();
        setMenuBar(menuBar);
        initialiseAlgorithmMenuItem();
        initialiseModeSelectionMenuItem();
        initialiseDataSelectionMenuItem();
        initialiseComparisonMenuItem();
        initialiseHelpMenuItem();
    }

    public void resetComparisonButtons()
    {
        for(int i = 0; i < comparisonOrders.size(); i++)
            ((ComparisonOrder)comparisonOrders.elementAt(i)).setState(false);

    }

    private static final long serialVersionUID = 0x766a0a21b19918f0L;
    protected static final String BUILD_DATE = "Build Date";
    protected static final String BUILD_BY = "Your Initial.Your Surname";
    protected static final String FRAME_TITLE = Messages.getString("ShellSortAnimationWindow.2");
    public static final String COMPARISON = Messages.getString("ShellSortAnimationWindow.3");
    public static final String H_SUBFILES = Messages.getString("ShellSortAnimationWindow.4");
    public static final String SINGLE_PASS = Messages.getString("ShellSortAnimationWindow.5");
    public static final int DEFAULT_DATA[] = {
        75, 5, 70, 10, 65, 15, 60, 20, 55, 25, 
        50, 30, 45, 35, 40
    };
    public static final int SORTED_DATA[] = {
        5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 
        55, 60, 65, 70, 75
    };
    public static final int REVERSE_SORTED_DATA[] = {
        75, 70, 65, 60, 55, 50, 45, 40, 35, 30, 
        25, 20, 15, 10, 5
    };
    protected Vector comparisonOrders;
    protected int minimumNumberOfElements;
    protected int maximumNumberOfElements;
    protected int maxSize;
    protected int minSize;

}
