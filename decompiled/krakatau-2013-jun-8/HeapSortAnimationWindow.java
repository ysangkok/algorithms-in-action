public class HeapSortAnimationWindow extends com.cim.AIA.AnimationWindow {
    final private static long serialVersionUID = -8859365854964303907L;
    final protected static String BUILD_DATE = "25/05/1999";
    final protected static String BUILD_BY = "T.Witham";
    final public static String FRAME_TITLE;
    final public static int MINIMUM_NUMBER_OF_ELEMENTS = 3;
    final public static int MAXIMUM_NUMBER_OF_ELEMENTS = 20;
    final public static int MAX_SIZE = 99;
    final public static int MIN_SIZE = 1;
    final public static int[] DEFAULT_DATA;
    final public static int[] SORTED_DATA;
    final public static int[] REVERSE_SORTED_DATA;
    final protected static String DATA_LABEL;
    final protected static String PREDICITION_DIFFICULTY_LABEL;
    final protected static String EASY_LABEL;
    final protected static String HARD_LABEL;
    protected java.awt.CheckboxMenuItem easy;
    protected java.awt.CheckboxMenuItem hard;
    protected HeapSortCanvas heapSortCanvas;
    protected boolean easyMode;
    
    public String getAlgorithmName()
    {
        return "AIA: Heapsort";
    }
    
    public HeapSortAnimationWindow(com.cim.AIA.AlgorithmCanvas a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1)
    {
        super(a, a0, a1);
        this.easyMode = true;
        this.buildDate = "25/05/1999";
        this.buildBy = "T.Witham";
        String s = HeapSortAnimationWindow.FRAME_TITLE;
        this.frameTitle = s;
        com.cim.common.RadioMenu a2 = this.dataMenu;
        String s0 = HeapSortAnimationWindow.DATA_LABEL;
        a2.setLabel(s0);
        String s1 = HeapSortAnimationWindow.SORTED_LABEL;
        int[] a3 = HeapSortAnimationWindow.SORTED_DATA;
        com.cim.AIA.PresetIntArrayDataSelection a4 = new com.cim.AIA.PresetIntArrayDataSelection(s1, false, (com.cim.AIA.AnimationWindow)this, a3);
        this.addDataSelection((com.cim.AIA.DataSelection)a4);
        String s2 = HeapSortAnimationWindow.REVERSE_SORTED_LABEL;
        int[] a5 = HeapSortAnimationWindow.REVERSE_SORTED_DATA;
        com.cim.AIA.PresetIntArrayDataSelection a6 = new com.cim.AIA.PresetIntArrayDataSelection(s2, false, (com.cim.AIA.AnimationWindow)this, a5);
        this.addDataSelection((com.cim.AIA.DataSelection)a6);
        String s3 = HeapSortAnimationWindow.SAME_LABEL;
        com.cim.AIA.RandomSameIntArrayDataSelection a7 = new com.cim.AIA.RandomSameIntArrayDataSelection(s3, false, (com.cim.AIA.AnimationWindow)this, 3, 20, 1, 99);
        this.addDataSelection((com.cim.AIA.DataSelection)a7);
        String s4 = HeapSortAnimationWindow.RANDOM_LABEL;
        com.cim.AIA.RandomIntArrayDataSelection a8 = new com.cim.AIA.RandomIntArrayDataSelection(s4, true, (com.cim.AIA.AnimationWindow)this, 3, 20, 1, 99);
        this.addDataSelection((com.cim.AIA.DataSelection)a8);
        StringBuilder a9 = new StringBuilder();
        String s5 = HeapSortAnimationWindow.USER_SELECTION_LABEL;
        StringBuilder a10 = a9.append(s5);
        StringBuilder a11 = a10.append("...");
        String s6 = a11.toString();
        String s7 = HeapSortAnimationWindow.USER_SELECTION_LABEL;
        com.cim.AIA.UserSelectionIntArrayDataSelection a12 = new com.cim.AIA.UserSelectionIntArrayDataSelection(s6, false, (com.cim.AIA.AnimationWindow)this, s7, 1, 99, 3, 20);
        this.addDataSelection((com.cim.AIA.DataSelection)a12);
        String s8 = com.cim.AIA.AnimationWindow.STEP_LABEL;
        String s9 = com.cim.AIA.AnimationWindow.STEP_INSTRUCTIONS;
        com.cim.AIA.StepButton a13 = new com.cim.AIA.StepButton(s8, s9, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a13);
        String s10 = com.cim.AIA.AnimationWindow.BACK_LABEL;
        String s11 = com.cim.AIA.AnimationWindow.BACK_INSTRUCTIONS;
        com.cim.AIA.BackButton a14 = new com.cim.AIA.BackButton(s10, s11, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a14);
        String s12 = com.cim.AIA.AnimationWindow.RUN_LABEL;
        String s13 = com.cim.AIA.AnimationWindow.RUN_INSTRUCTIONS;
        com.cim.AIA.RunButton a15 = new com.cim.AIA.RunButton(s12, s13, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a15);
        String s14 = com.cim.AIA.AnimationWindow.PAUSE_LABEL;
        String s15 = com.cim.AIA.AnimationWindow.PAUSE_INSTRUCTIONS;
        com.cim.AIA.PauseButton a16 = new com.cim.AIA.PauseButton(s14, s15, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a16);
        String s16 = com.cim.AIA.AnimationWindow.RESET_LABEL;
        String s17 = com.cim.AIA.AnimationWindow.RESET_INSTRUCTIONS;
        com.cim.AIA.ResetButton a17 = new com.cim.AIA.ResetButton(s16, s17, (com.cim.AIA.AnimationWindow)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a17);
    }
    
    protected void easyMode()
    {
        this.easyMode = true;
        java.awt.CheckboxMenuItem a = this.hard;
        int i = this.easyMode?1:0;
        int i0 = i != 0?0:1;
        a.setState(i0 != 0);
        java.awt.CheckboxMenuItem a0 = this.easy;
        int i1 = this.easyMode?1:0;
        a0.setState(i1 != 0);
        com.cim.AIA.SelfTestModeSelection a1 = this.selfTestMode;
        int i2 = a1.getState()?1:0;
        if(i2 != 0)
        {
            HeapSortCanvas a2 = this.heapSortCanvas;
            int i3 = this.easyMode?1:0;
            a2.setMarkersEnabled(i3 != 0);
        }
    }
    
    protected void hardMode()
    {
        this.easyMode = false;
        java.awt.CheckboxMenuItem a = this.hard;
        int i = this.easyMode?1:0;
        int i0 = i != 0?0:1;
        a.setState(i0 != 0);
        java.awt.CheckboxMenuItem a0 = this.easy;
        int i1 = this.easyMode?1:0;
        a0.setState(i1 != 0);
        com.cim.AIA.SelfTestModeSelection a1 = this.selfTestMode;
        int i2 = a1.getState()?1:0;
        if(i2 != 0)
        {
            HeapSortCanvas a2 = this.heapSortCanvas;
            int i3 = this.easyMode?1:0;
            a2.setMarkersEnabled(i3 != 0);
        }
    }
    
    protected void initialiseMenuBar()
    {
        java.awt.MenuBar a = new java.awt.MenuBar();
        this.menuBar = a;
        java.awt.MenuBar a0 = this.menuBar;
        this.setMenuBar(a0);
        this.initialiseAlgorithmMenuItem();
        this.initialiseModeSelectionMenuItem();
        this.initialiseQuestionLevel();
        this.initialiseDataSelectionMenuItem();
        this.initialiseHelpMenuItem();
    }
    
    protected void initialiseQuestionLevel()
    {
        String s = HeapSortAnimationWindow.PREDICITION_DIFFICULTY_LABEL;
        com.cim.common.RadioMenu a = new com.cim.common.RadioMenu(s);
        String s0 = HeapSortAnimationWindow.EASY_LABEL;
        int i = this.easyMode?1:0;
        java.awt.CheckboxMenuItem a0 = new java.awt.CheckboxMenuItem(s0, i != 0);
        this.easy = a0;
        String s1 = HeapSortAnimationWindow.HARD_LABEL;
        int i0 = this.easyMode?1:0;
        int i1 = i0 != 0?0:1;
        java.awt.CheckboxMenuItem a1 = new java.awt.CheckboxMenuItem(s1, i1 != 0);
        this.hard = a1;
        com.cim.AIA.AlgorithmCanvas a2 = this.canvas;
        HeapSortCanvas dummy = (HeapSortCanvas)a2;
        HeapSortCanvas a3 = (HeapSortCanvas)a2;
        this.heapSortCanvas = a3;
        java.awt.CheckboxMenuItem a4 = this.easy;
        HeapSortAnimationWindow$1 a5 = new HeapSortAnimationWindow$1(this);
        a4.addItemListener((java.awt.event.ItemListener)a5);
        java.awt.CheckboxMenuItem a6 = this.hard;
        HeapSortAnimationWindow$2 a7 = new HeapSortAnimationWindow$2(this);
        a6.addItemListener((java.awt.event.ItemListener)a7);
        com.cim.AIA.SelfTestModeSelection a8 = this.selfTestMode;
        HeapSortAnimationWindow$3 a9 = new HeapSortAnimationWindow$3(this);
        a8.addItemListener((java.awt.event.ItemListener)a9);
        com.cim.AIA.SelfTestModeSelection a10 = this.selfTestMode;
        int i2 = a10.getState()?1:0;
        if(i2 != 0)
        {
            HeapSortCanvas a11 = this.heapSortCanvas;
            int i3 = this.easyMode?1:0;
            a11.setMarkersEnabled(i3 != 0);
        }
        java.awt.CheckboxMenuItem a12 = this.easy;
        java.awt.MenuItem a13 = a.add((java.awt.MenuItem)a12);
        java.awt.CheckboxMenuItem a14 = this.hard;
        java.awt.MenuItem a15 = a.add((java.awt.MenuItem)a14);
        com.cim.common.RadioMenu a16 = this.modeMenu;
        a16.addSeparator();
        com.cim.common.RadioMenu a17 = this.modeMenu;
        java.awt.MenuItem a18 = a17.add((java.awt.MenuItem)a);
    }
    
    public void setNormalMode()
    {
        com.cim.AIA.ModeEvent a = new com.cim.AIA.ModeEvent((Object)this, 12356);
        this.informModeListeners(a);
        com.cim.AIA.AlgorithmCanvas a0 = this.canvas;
        HeapSortCanvas dummy = (HeapSortCanvas)a0;
        HeapSortCanvas a1 = (HeapSortCanvas)a0;
        a1.setMarkersEnabled(true);
        com.cim.AIA.AlgorithmThread a2 = this.thread;
        a2.setHandleQuestions(false);
    }
    
    static
    {
        String s = localization.Messages.getString("HeapSortAnimationWindow.2");
        HeapSortAnimationWindow.FRAME_TITLE = s;
        int[] a = new int[15];
        a[0] = 5;
        a[1] = 95;
        a[2] = 10;
        a[3] = 90;
        a[4] = 15;
        a[5] = 85;
        a[6] = 20;
        a[7] = 80;
        a[8] = 25;
        a[9] = 75;
        a[10] = 30;
        a[11] = 70;
        a[12] = 35;
        a[13] = 65;
        a[14] = 40;
        HeapSortAnimationWindow.DEFAULT_DATA = a;
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
        HeapSortAnimationWindow.SORTED_DATA = a0;
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
        HeapSortAnimationWindow.REVERSE_SORTED_DATA = a1;
        String s0 = localization.Messages.getString("HeapSortAnimationWindow.3");
        HeapSortAnimationWindow.DATA_LABEL = s0;
        String s1 = localization.Messages.getString("HeapSortAnimationWindow.4");
        HeapSortAnimationWindow.PREDICITION_DIFFICULTY_LABEL = s1;
        String s2 = localization.Messages.getString("HeapSortAnimationWindow.5");
        HeapSortAnimationWindow.EASY_LABEL = s2;
        String s3 = localization.Messages.getString("HeapSortAnimationWindow.6");
        HeapSortAnimationWindow.HARD_LABEL = s3;
    }
}