// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QuickSortAnimationWindow.java

import com.cim.AIA.*;
import com.cim.common.RadioMenu;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;
import java.net.URL;
import java.util.Vector;
import localization.Messages;

public class QuickSortAnimationWindow extends AnimationWindow
{
    public class PartitionMethod extends CheckboxMenuItem
    {

        private static final long serialVersionUID = 0x459f0e7e29b20f0eL;
        protected LadderTree ladderTree;
        protected QuickSort quickSort;
        protected int partitionMethod;
        protected QuickSortAnimationWindow animWindow;
        protected AlgorithmWindow algoWindow;
        final QuickSortAnimationWindow this$0;

        public PartitionMethod(String name, boolean state, QuickSort aQuickSort, String dataDir, int aPartitionMethod, String filename, 
                QuickSortAnimationWindow quickSortAnimationWindow, AlgorithmWindow algorithmWindow)
        {
            this$0 = QuickSortAnimationWindow.this;
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
                final PartitionMethod this$1;


// JavaClassFileOutputException: Invalid index accessing method local variables table of <init>
            });
        }
    }


    public String getAlgorithmName()
    {
        return "AIA: Quicksort";
    }

    public QuickSortAnimationWindow(AlgorithmCanvas canvas, AlgorithmThread thread, Applet applet)
    {
        super(canvas, thread, applet);
        partitionMethods = new Vector();
        minimumNumberOfElements = 3;
        maximumNumberOfElements = 20;
        maxSize = 99;
        minSize = 1;
        buildDate = "1/10/1999";
        buildBy = "T.Witham";
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
        addModeSelection(new QuizModeSelection((new StringBuilder()).append(QUIZ_MODE_LABEL).append("...").toString(), false, this, thread, applet, Messages.getString("QuickSortAnimationWindow.6"), "quicksort.quiz", (new StringBuilder()).append(applet.getCodeBase().toString()).append("images/").toString(), "correct.gif", "incorrect.gif"));
    }

    protected void initialiseMenuBar()
    {
        menuBar = new MenuBar();
        setMenuBar(menuBar);
        initialiseAlgorithmMenuItem();
        initialiseModeSelectionMenuItem();
        initialiseDataSelectionMenuItem();
        initialisePartitionMenuItem();
        initialiseHelpMenuItem();
    }

    protected void initialisePartitionMenuItem()
    {
        RadioMenu partitionMethod = new RadioMenu(PARTITION, true);
        QuickSort quickSort = (QuickSort)(QuickSort)getAlgorithm();
        partitionMethods.addElement(new PartitionMethod(RIGHT_PARTITION, true, quickSort, applet.getCodeBase().toString(), 10, QuickSortApplet.RightPartitionFileName, this, getAlgorithmWindow()));
        partitionMethods.addElement(new PartitionMethod(RANDOM_PARTITION, false, quickSort, applet.getCodeBase().toString(), 11, QuickSortApplet.RandomPartitionFileName, this, getAlgorithmWindow()));
        partitionMethods.addElement(new PartitionMethod(MIDDLE_OF_THREE_RANDOM_PARTITION, false, quickSort, applet.getCodeBase().toString(), 12, QuickSortApplet.MiddleOf3RandomPartitionFileName, this, getAlgorithmWindow()));
        partitionMethods.addElement(new PartitionMethod(MIDDLE_OF_THREE_PARTITION, false, quickSort, applet.getCodeBase().toString(), 13, QuickSortApplet.MiddleOf3PartitionFileName, this, getAlgorithmWindow()));
        for(int i = 0; i < partitionMethods.size(); i++)
            partitionMethod.add((MenuItem)partitionMethods.elementAt(i));

        partitionMethod.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                String cmd = e.paramString();
                int i1 = cmd.indexOf("cmd=") + 4;
                int i2 = cmd.indexOf(",", i1);
                cmd = cmd.substring(i1, i2);
                Log l1 = new Log((byte)4, (byte)21, null, cmd);
                if(getLogger() != null)
                    getLogger().addLog(l1);
                if(Logger.DEBUG)
                {
                    System.err.println("Partition method action listener called.");
                    System.err.println((new StringBuilder()).append("paramString=").append(e.paramString()).toString());
                    System.err.println((new StringBuilder()).append("cmd=").append(cmd).toString());
                }
            }

            final QuickSortAnimationWindow this$0;

            
            {
                this$0 = QuickSortAnimationWindow.this;
                super();
            }
        });
        menuBar.add(partitionMethod);
    }

    public void resetPartitionButtons()
    {
        for(int i = 0; i < partitionMethods.size(); i++)
            ((PartitionMethod)partitionMethods.elementAt(i)).setState(false);

    }

    public void resetThread()
    {
        ((QuickSortThread)thread).resetSeed();
        super.resetThread();
    }

    public void setNormalMode()
    {
        informModeListeners(new ModeEvent(this, 12356));
        thread.setHandleQuestions(false);
    }

    private static final long serialVersionUID = 0x6cc0533f2d132145L;
    protected static final String BUILD_DATE = "1/10/1999";
    protected static final String BUILD_BY = "T.Witham";
    protected static final String FRAME_TITLE = Messages.getString("QuickSortAnimationWindow.0");
    public static final String PARTITION = Messages.getString("QuickSortAnimationWindow.1");
    public static final String RIGHT_PARTITION = Messages.getString("QuickSortAnimationWindow.2");
    public static final String RANDOM_PARTITION = Messages.getString("QuickSortAnimationWindow.3");
    public static final String MIDDLE_OF_THREE_RANDOM_PARTITION = Messages.getString("QuickSortAnimationWindow.4");
    public static final String MIDDLE_OF_THREE_PARTITION = Messages.getString("QuickSortAnimationWindow.5");
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
    protected Vector partitionMethods;
    protected int minimumNumberOfElements;
    protected int maximumNumberOfElements;
    protected int maxSize;
    protected int minSize;

}
