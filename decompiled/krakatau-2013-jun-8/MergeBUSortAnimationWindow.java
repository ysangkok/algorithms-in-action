public class MergeBUSortAnimationWindow extends com.cim.AIA.AnimationWindow {
    final private static long serialVersionUID = 1L;
    final protected static String BUILD_DATE = "15/2/2007";
    final protected static String BUILD_BY = "M. Tabbara";
    final protected static String FRAME_TITLE;
    protected java.util.Vector partitionMethods;
    protected int minimumNumberOfElements;
    protected int maximumNumberOfElements;
    protected int maxSize;
    protected int minSize;
    final public static int[] DEFAULT_DATA;
    final public static int[] SORTED_DATA;
    final public static int[] REVERSE_SORTED_DATA;
    
    public String getAlgorithmName()
    {
        return "AIA: Bottom-up Mergesort";
    }
    
    public MergeBUSortAnimationWindow(com.cim.AIA.AlgorithmCanvas a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1)
    {
        super(a, a0, a1);
        java.util.Vector a2 = new java.util.Vector();
        this.partitionMethods = a2;
        this.minimumNumberOfElements = 3;
        this.maximumNumberOfElements = 20;
        this.maxSize = 99;
        this.minSize = 1;
        this.buildDate = "15/2/2007";
        this.buildBy = "M. Tabbara";
        String s = MergeBUSortAnimationWindow.FRAME_TITLE;
        this.frameTitle = s;
        String s0 = MergeBUSortAnimationWindow.DEFAULT_LABEL;
        int[] a3 = MergeBUSortAnimationWindow.DEFAULT_DATA;
        com.cim.AIA.PresetIntArrayDataSelection a4 = new com.cim.AIA.PresetIntArrayDataSelection(s0, true, (com.cim.AIA.AnimationWindow)this, a3);
        this.addDataSelection((com.cim.AIA.DataSelection)a4);
        String s1 = MergeBUSortAnimationWindow.SORTED_LABEL;
        int[] a5 = MergeBUSortAnimationWindow.SORTED_DATA;
        com.cim.AIA.PresetIntArrayDataSelection a6 = new com.cim.AIA.PresetIntArrayDataSelection(s1, false, (com.cim.AIA.AnimationWindow)this, a5);
        this.addDataSelection((com.cim.AIA.DataSelection)a6);
        String s2 = MergeBUSortAnimationWindow.REVERSE_SORTED_LABEL;
        int[] a7 = MergeBUSortAnimationWindow.REVERSE_SORTED_DATA;
        com.cim.AIA.PresetIntArrayDataSelection a8 = new com.cim.AIA.PresetIntArrayDataSelection(s2, false, (com.cim.AIA.AnimationWindow)this, a7);
        this.addDataSelection((com.cim.AIA.DataSelection)a8);
        String s3 = MergeBUSortAnimationWindow.SAME_LABEL;
        int i = this.minimumNumberOfElements;
        int i0 = this.maximumNumberOfElements;
        int i1 = this.minSize;
        int i2 = this.maxSize;
        com.cim.AIA.RandomSameIntArrayDataSelection a9 = new com.cim.AIA.RandomSameIntArrayDataSelection(s3, false, (com.cim.AIA.AnimationWindow)this, i, i0, i1, i2);
        this.addDataSelection((com.cim.AIA.DataSelection)a9);
        String s4 = MergeBUSortAnimationWindow.RANDOM_LABEL;
        int i3 = this.minimumNumberOfElements;
        int i4 = this.maximumNumberOfElements;
        int i5 = this.minSize;
        int i6 = this.maxSize;
        com.cim.AIA.RandomIntArrayDataSelection a10 = new com.cim.AIA.RandomIntArrayDataSelection(s4, false, (com.cim.AIA.AnimationWindow)this, i3, i4, i5, i6);
        this.addDataSelection((com.cim.AIA.DataSelection)a10);
        StringBuilder a11 = new StringBuilder();
        String s5 = MergeBUSortAnimationWindow.USER_SELECTION_LABEL;
        StringBuilder a12 = a11.append(s5);
        StringBuilder a13 = a12.append("...");
        String s6 = a13.toString();
        String s7 = MergeBUSortAnimationWindow.USER_SELECTION_LABEL;
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
    }
    
    public void setNormalMode()
    {
        com.cim.AIA.ModeEvent a = new com.cim.AIA.ModeEvent((Object)this, 12356);
        this.informModeListeners(a);
        com.cim.AIA.AlgorithmThread a0 = this.thread;
        a0.setHandleQuestions(false);
    }
    
    protected void initialiseMenuBar()
    {
        java.awt.MenuBar a = new java.awt.MenuBar();
        this.menuBar = a;
        java.awt.MenuBar a0 = this.menuBar;
        this.setMenuBar(a0);
        this.initialiseAlgorithmMenuItem();
        this.initialiseDataSelectionMenuItem();
        this.initialiseHelpMenuItem();
    }
    
    public void resetThread()
    {
        com.cim.AIA.AlgorithmThread a = this.thread;
        MergeBUSortThread dummy = (MergeBUSortThread)a;
        MergeBUSortThread a0 = (MergeBUSortThread)a;
        a0.resetSeed();
        ((com.cim.AIA.AnimationWindow)this).resetThread();
    }
    
    static
    {
        String s = localization.Messages.getString("MergeBUSortAnimationWindow.2");
        MergeBUSortAnimationWindow.FRAME_TITLE = s;
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
        MergeBUSortAnimationWindow.DEFAULT_DATA = a;
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
        MergeBUSortAnimationWindow.SORTED_DATA = a0;
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
        MergeBUSortAnimationWindow.REVERSE_SORTED_DATA = a1;
    }
}