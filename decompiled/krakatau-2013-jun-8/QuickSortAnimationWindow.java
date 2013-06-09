public class QuickSortAnimationWindow extends com.cim.AIA.AnimationWindow {
    final private static long serialVersionUID = 7836354882428936517L;
    final protected static String BUILD_DATE = "1/10/1999";
    final protected static String BUILD_BY = "T.Witham";
    final protected static String FRAME_TITLE;
    final public static String PARTITION;
    final public static String RIGHT_PARTITION;
    final public static String RANDOM_PARTITION;
    final public static String MIDDLE_OF_THREE_RANDOM_PARTITION;
    final public static String MIDDLE_OF_THREE_PARTITION;
    final public static int[] DEFAULT_DATA;
    final public static int[] SORTED_DATA;
    final public static int[] REVERSE_SORTED_DATA;
    protected java.util.Vector partitionMethods;
    protected int minimumNumberOfElements;
    protected int maximumNumberOfElements;
    protected int maxSize;
    protected int minSize;
    
    public String getAlgorithmName()
    {
        return "AIA: Quicksort";
    }
    
    public QuickSortAnimationWindow(com.cim.AIA.AlgorithmCanvas a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1)
    {
        super(a, a0, a1);
        java.util.Vector a2 = new java.util.Vector();
        this.partitionMethods = a2;
        this.minimumNumberOfElements = 3;
        this.maximumNumberOfElements = 20;
        this.maxSize = 99;
        this.minSize = 1;
        this.buildDate = "1/10/1999";
        this.buildBy = "T.Witham";
        String s = QuickSortAnimationWindow.FRAME_TITLE;
        this.frameTitle = s;
        String s0 = QuickSortAnimationWindow.DEFAULT_LABEL;
        int[] a3 = QuickSortAnimationWindow.DEFAULT_DATA;
        com.cim.AIA.PresetIntArrayDataSelection a4 = new com.cim.AIA.PresetIntArrayDataSelection(s0, true, (com.cim.AIA.AnimationWindow)this, a3);
        this.addDataSelection((com.cim.AIA.DataSelection)a4);
        String s1 = QuickSortAnimationWindow.SORTED_LABEL;
        int[] a5 = QuickSortAnimationWindow.SORTED_DATA;
        com.cim.AIA.PresetIntArrayDataSelection a6 = new com.cim.AIA.PresetIntArrayDataSelection(s1, false, (com.cim.AIA.AnimationWindow)this, a5);
        this.addDataSelection((com.cim.AIA.DataSelection)a6);
        String s2 = QuickSortAnimationWindow.REVERSE_SORTED_LABEL;
        int[] a7 = QuickSortAnimationWindow.REVERSE_SORTED_DATA;
        com.cim.AIA.PresetIntArrayDataSelection a8 = new com.cim.AIA.PresetIntArrayDataSelection(s2, false, (com.cim.AIA.AnimationWindow)this, a7);
        this.addDataSelection((com.cim.AIA.DataSelection)a8);
        String s3 = QuickSortAnimationWindow.SAME_LABEL;
        int i = this.minimumNumberOfElements;
        int i0 = this.maximumNumberOfElements;
        int i1 = this.minSize;
        int i2 = this.maxSize;
        com.cim.AIA.RandomSameIntArrayDataSelection a9 = new com.cim.AIA.RandomSameIntArrayDataSelection(s3, false, (com.cim.AIA.AnimationWindow)this, i, i0, i1, i2);
        this.addDataSelection((com.cim.AIA.DataSelection)a9);
        String s4 = QuickSortAnimationWindow.RANDOM_LABEL;
        int i3 = this.minimumNumberOfElements;
        int i4 = this.maximumNumberOfElements;
        int i5 = this.minSize;
        int i6 = this.maxSize;
        com.cim.AIA.RandomIntArrayDataSelection a10 = new com.cim.AIA.RandomIntArrayDataSelection(s4, false, (com.cim.AIA.AnimationWindow)this, i3, i4, i5, i6);
        this.addDataSelection((com.cim.AIA.DataSelection)a10);
        StringBuilder a11 = new StringBuilder();
        String s5 = QuickSortAnimationWindow.USER_SELECTION_LABEL;
        StringBuilder a12 = a11.append(s5);
        StringBuilder a13 = a12.append("...");
        String s6 = a13.toString();
        String s7 = QuickSortAnimationWindow.USER_SELECTION_LABEL;
        int i7 = this.minSize;
        int i8 = this.maxSize;
        int i9 = this.minimumNumberOfElements;
        int i10 = this.maximumNumberOfElements;
        com.cim.AIA.UserSelectionIntArrayDataSelection a14 = new com.cim.AIA.UserSelectionIntArrayDataSelection(s6, false, (com.cim.AIA.AnimationWindow)this, s7, i7, i8, i9, i10);
        this.addDataSelection((com.cim.AIA.DataSelection)a14);
        String s8 = com.cim.AIA.AnimationWindow.STEP_LABEL;
        String s9 = com.cim.AIA.AnimationWindow.STEP_INSTRUCTIONS;
        com.cim.AIA.StepButton a15 = new com.cim.AIA.StepButton(s8, s9, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a15);
        String s10 = com.cim.AIA.AnimationWindow.BACK_LABEL;
        String s11 = com.cim.AIA.AnimationWindow.BACK_INSTRUCTIONS;
        com.cim.AIA.BackButton a16 = new com.cim.AIA.BackButton(s10, s11, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a16);
        String s12 = com.cim.AIA.AnimationWindow.RUN_LABEL;
        String s13 = com.cim.AIA.AnimationWindow.RUN_INSTRUCTIONS;
        com.cim.AIA.RunButton a17 = new com.cim.AIA.RunButton(s12, s13, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a17);
        String s14 = com.cim.AIA.AnimationWindow.PAUSE_LABEL;
        String s15 = com.cim.AIA.AnimationWindow.PAUSE_INSTRUCTIONS;
        com.cim.AIA.PauseButton a18 = new com.cim.AIA.PauseButton(s14, s15, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a18);
        String s16 = com.cim.AIA.AnimationWindow.RESET_LABEL;
        String s17 = com.cim.AIA.AnimationWindow.RESET_INSTRUCTIONS;
        com.cim.AIA.ResetButton a19 = new com.cim.AIA.ResetButton(s16, s17, (com.cim.AIA.AnimationWindow)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a19);
        StringBuilder a20 = new StringBuilder();
        String s18 = QuickSortAnimationWindow.QUIZ_MODE_LABEL;
        StringBuilder a21 = a20.append(s18);
        StringBuilder a22 = a21.append("...");
        String s19 = a22.toString();
        String s20 = localization.Messages.getString("QuickSortAnimationWindow.6");
        StringBuilder a23 = new StringBuilder();
        java.net.URL a24 = a1.getCodeBase();
        String s21 = a24.toString();
        StringBuilder a25 = a23.append(s21);
        StringBuilder a26 = a25.append("images/");
        String s22 = a26.toString();
        com.cim.AIA.QuizModeSelection a27 = new com.cim.AIA.QuizModeSelection(s19, false, (com.cim.AIA.AnimationWindow)this, a0, a1, s20, "quicksort.quiz", s22, "correct.gif", "incorrect.gif");
        this.addModeSelection((com.cim.AIA.ModeSelection)a27);
    }
    
    protected void initialiseMenuBar()
    {
        java.awt.MenuBar a = new java.awt.MenuBar();
        this.menuBar = a;
        java.awt.MenuBar a0 = this.menuBar;
        this.setMenuBar(a0);
        this.initialiseAlgorithmMenuItem();
        this.initialiseModeSelectionMenuItem();
        this.initialiseDataSelectionMenuItem();
        this.initialisePartitionMenuItem();
        this.initialiseHelpMenuItem();
    }
    
    protected void initialisePartitionMenuItem()
    {
        String s = QuickSortAnimationWindow.PARTITION;
        com.cim.common.RadioMenu a = new com.cim.common.RadioMenu(s, true);
        com.cim.AIA.Algorithm a0 = this.getAlgorithm();
        QuickSort dummy = (QuickSort)a0;
        QuickSort a1 = (QuickSort)a0;
        java.util.Vector a2 = this.partitionMethods;
        String s0 = QuickSortAnimationWindow.RIGHT_PARTITION;
        java.applet.Applet a3 = this.applet;
        java.net.URL a4 = a3.getCodeBase();
        String s1 = a4.toString();
        String s2 = QuickSortApplet.RightPartitionFileName;
        com.cim.AIA.AlgorithmWindow a5 = this.getAlgorithmWindow();
        QuickSortAnimationWindow$PartitionMethod a6 = new QuickSortAnimationWindow$PartitionMethod(this, s0, true, a1, s1, 10, s2, this, a5);
        a2.addElement((Object)a6);
        java.util.Vector a7 = this.partitionMethods;
        String s3 = QuickSortAnimationWindow.RANDOM_PARTITION;
        java.applet.Applet a8 = this.applet;
        java.net.URL a9 = a8.getCodeBase();
        String s4 = a9.toString();
        String s5 = QuickSortApplet.RandomPartitionFileName;
        com.cim.AIA.AlgorithmWindow a10 = this.getAlgorithmWindow();
        QuickSortAnimationWindow$PartitionMethod a11 = new QuickSortAnimationWindow$PartitionMethod(this, s3, false, a1, s4, 11, s5, this, a10);
        a7.addElement((Object)a11);
        java.util.Vector a12 = this.partitionMethods;
        String s6 = QuickSortAnimationWindow.MIDDLE_OF_THREE_RANDOM_PARTITION;
        java.applet.Applet a13 = this.applet;
        java.net.URL a14 = a13.getCodeBase();
        String s7 = a14.toString();
        String s8 = QuickSortApplet.MiddleOf3RandomPartitionFileName;
        com.cim.AIA.AlgorithmWindow a15 = this.getAlgorithmWindow();
        QuickSortAnimationWindow$PartitionMethod a16 = new QuickSortAnimationWindow$PartitionMethod(this, s6, false, a1, s7, 12, s8, this, a15);
        a12.addElement((Object)a16);
        java.util.Vector a17 = this.partitionMethods;
        String s9 = QuickSortAnimationWindow.MIDDLE_OF_THREE_PARTITION;
        java.applet.Applet a18 = this.applet;
        java.net.URL a19 = a18.getCodeBase();
        String s10 = a19.toString();
        String s11 = QuickSortApplet.MiddleOf3PartitionFileName;
        com.cim.AIA.AlgorithmWindow a20 = this.getAlgorithmWindow();
        QuickSortAnimationWindow$PartitionMethod a21 = new QuickSortAnimationWindow$PartitionMethod(this, s9, false, a1, s10, 13, s11, this, a20);
        a17.addElement((Object)a21);
        int i = 0;
        while(true)
        {
            java.util.Vector a22 = this.partitionMethods;
            int i0 = a22.size();
            if(i >= i0)
            {
                QuickSortAnimationWindow$1 a23 = new QuickSortAnimationWindow$1(this);
                a.addActionListener((java.awt.event.ActionListener)a23);
                java.awt.MenuBar a24 = this.menuBar;
                java.awt.Menu a25 = a24.add((java.awt.Menu)a);
                return;
            }
            else
            {
                java.util.Vector a26 = this.partitionMethods;
                Object a27 = a26.elementAt(i);
                java.awt.MenuItem dummy0 = (java.awt.MenuItem)a27;
                java.awt.MenuItem a28 = (java.awt.MenuItem)a27;
                java.awt.MenuItem a29 = a.add(a28);
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    public void resetPartitionButtons()
    {
        int i = 0;
        while(true)
        {
            java.util.Vector a = this.partitionMethods;
            int i0 = a.size();
            if(i >= i0)
            {
                return;
            }
            else
            {
                java.util.Vector a0 = this.partitionMethods;
                Object a1 = a0.elementAt(i);
                QuickSortAnimationWindow$PartitionMethod dummy = (QuickSortAnimationWindow$PartitionMethod)a1;
                QuickSortAnimationWindow$PartitionMethod a2 = (QuickSortAnimationWindow$PartitionMethod)a1;
                a2.setState(false);
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    public void resetThread()
    {
        com.cim.AIA.AlgorithmThread a = this.thread;
        QuickSortThread dummy = (QuickSortThread)a;
        QuickSortThread a0 = (QuickSortThread)a;
        a0.resetSeed();
        ((com.cim.AIA.AnimationWindow)this).resetThread();
    }
    
    public void setNormalMode()
    {
        com.cim.AIA.ModeEvent a = new com.cim.AIA.ModeEvent((Object)this, 12356);
        this.informModeListeners(a);
        com.cim.AIA.AlgorithmThread a0 = this.thread;
        a0.setHandleQuestions(false);
    }
    
    static
    {
        String s = localization.Messages.getString("QuickSortAnimationWindow.0");
        QuickSortAnimationWindow.FRAME_TITLE = s;
        String s0 = localization.Messages.getString("QuickSortAnimationWindow.1");
        QuickSortAnimationWindow.PARTITION = s0;
        String s1 = localization.Messages.getString("QuickSortAnimationWindow.2");
        QuickSortAnimationWindow.RIGHT_PARTITION = s1;
        String s2 = localization.Messages.getString("QuickSortAnimationWindow.3");
        QuickSortAnimationWindow.RANDOM_PARTITION = s2;
        String s3 = localization.Messages.getString("QuickSortAnimationWindow.4");
        QuickSortAnimationWindow.MIDDLE_OF_THREE_RANDOM_PARTITION = s3;
        String s4 = localization.Messages.getString("QuickSortAnimationWindow.5");
        QuickSortAnimationWindow.MIDDLE_OF_THREE_PARTITION = s4;
        int[] a = new int[15];
        a[0] = 75;
        a[1] = 5;
        a[2] = 70;
        a[3] = 10;
        a[4] = 65;
        a[5] = 15;
        a[6] = 60;
        a[7] = 20;
        a[8] = 55;
        a[9] = 25;
        a[10] = 50;
        a[11] = 30;
        a[12] = 45;
        a[13] = 35;
        a[14] = 40;
        QuickSortAnimationWindow.DEFAULT_DATA = a;
        int[] a0 = new int[15];
        a0[0] = 5;
        a0[1] = 10;
        a0[2] = 15;
        a0[3] = 20;
        a0[4] = 25;
        a0[5] = 30;
        a0[6] = 35;
        a0[7] = 40;
        a0[8] = 45;
        a0[9] = 50;
        a0[10] = 55;
        a0[11] = 60;
        a0[12] = 65;
        a0[13] = 70;
        a0[14] = 75;
        QuickSortAnimationWindow.SORTED_DATA = a0;
        int[] a1 = new int[15];
        a1[0] = 75;
        a1[1] = 70;
        a1[2] = 65;
        a1[3] = 60;
        a1[4] = 55;
        a1[5] = 50;
        a1[6] = 45;
        a1[7] = 40;
        a1[8] = 35;
        a1[9] = 30;
        a1[10] = 25;
        a1[11] = 20;
        a1[12] = 15;
        a1[13] = 10;
        a1[14] = 5;
        QuickSortAnimationWindow.REVERSE_SORTED_DATA = a1;
    }
}