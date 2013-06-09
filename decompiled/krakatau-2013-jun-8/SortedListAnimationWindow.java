public class SortedListAnimationWindow extends com.cim.AIA.AnimationWindow {
    final protected static String BUILD_DATE = "Build Date";
    final protected static String BUILD_BY = "Your Initial.Your Surname";
    final protected static String FRAME_TITLE;
    final protected static int NUMBER_OF_ELEMENTS = 10;
    final private static int MINIMUM_OF_ELEMENTS = 5;
    final private static int MAXIMUM_OF_ELEMENTS = 31;
    final protected static int MAX_SIZE = 99;
    final protected static int MIN_SIZE = 1;
    final public static int[] DEFAULT_DATA;
    final public static int[] SORTED_DATA;
    final public static int[] REVERSE_SORTED_DATA;
    protected int minimumNumberOfElements;
    protected int maximumNumberOfElements;
    protected int maxSize;
    protected int minSize;
    
    public String getAlgorithmName()
    {
        return "AIA: SortedList";
    }
    
    public SortedListAnimationWindow(com.cim.AIA.AlgorithmCanvas a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1)
    {
        super(a, a0, a1);
        this.minimumNumberOfElements = 1;
        this.maximumNumberOfElements = 20;
        this.maxSize = 99;
        this.minSize = 0;
        this.buildDate = "Build Date";
        this.buildBy = "Your Initial.Your Surname";
        String s = SortedListAnimationWindow.FRAME_TITLE;
        this.frameTitle = s;
        String s0 = com.cim.AIA.AnimationWindow.STEP_LABEL;
        String s1 = com.cim.AIA.AnimationWindow.STEP_INSTRUCTIONS;
        com.cim.AIA.StepButton a2 = new com.cim.AIA.StepButton(s0, s1, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a2);
        String s2 = com.cim.AIA.AnimationWindow.BACK_LABEL;
        String s3 = com.cim.AIA.AnimationWindow.BACK_INSTRUCTIONS;
        com.cim.AIA.BackButton a3 = new com.cim.AIA.BackButton(s2, s3, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a3);
        String s4 = com.cim.AIA.AnimationWindow.RUN_LABEL;
        String s5 = com.cim.AIA.AnimationWindow.RUN_INSTRUCTIONS;
        com.cim.AIA.RunButton a4 = new com.cim.AIA.RunButton(s4, s5, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a4);
        String s6 = com.cim.AIA.AnimationWindow.PAUSE_LABEL;
        String s7 = com.cim.AIA.AnimationWindow.PAUSE_INSTRUCTIONS;
        com.cim.AIA.PauseButton a5 = new com.cim.AIA.PauseButton(s6, s7, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a5);
        String s8 = com.cim.AIA.AnimationWindow.RESET_LABEL;
        String s9 = com.cim.AIA.AnimationWindow.RESET_INSTRUCTIONS;
        com.cim.AIA.ResetButton a6 = new com.cim.AIA.ResetButton(s8, s9, (com.cim.AIA.AnimationWindow)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a6);
        String s10 = localization.Messages.getString("SortedListAnimationWindow.3");
        String s11 = localization.Messages.getString("SortedListAnimationWindow.4");
        com.cim.AIA.RestartButton a7 = new com.cim.AIA.RestartButton(s10, s11, (com.cim.AIA.AnimationWindow)this, a0);
        a7.setReinitialiseAlgorithm(false);
        a7.setClearAlgorithmState(false);
        a7.setStoreAlgorithmState(true);
        a7.setForceAlgorithmStore(true);
        this.addControlButton((com.cim.AIA.ControlButton)a7);
        String s12 = localization.Messages.getString("SortedListAnimationWindow.5");
        String s13 = localization.Messages.getString("SortedListAnimationWindow.6");
        ChangeModeButton a8 = new ChangeModeButton(s12, s13, (com.cim.AIA.AnimationWindow)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a8);
        String s14 = SortedListAnimationWindow.DEFAULT_LABEL;
        int[] a9 = SortedListAnimationWindow.DEFAULT_DATA;
        com.cim.AIA.PresetIntArrayDataSelection a10 = new com.cim.AIA.PresetIntArrayDataSelection(s14, true, (com.cim.AIA.AnimationWindow)this, a9);
        a10.setReinitialiseAlgorithm(false);
        a10.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a10);
        String s15 = SortedListAnimationWindow.RANDOM_LABEL;
        com.cim.AIA.RandomIntArrayDataSelection a11 = new com.cim.AIA.RandomIntArrayDataSelection(s15, false, (com.cim.AIA.AnimationWindow)this, 5, 31, 1, 99);
        a11.setReinitialiseAlgorithm(false);
        a11.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a11);
        String s16 = SortedListAnimationWindow.SORTED_LABEL;
        int[] a12 = SortedListAnimationWindow.SORTED_DATA;
        com.cim.AIA.PresetIntArrayDataSelection a13 = new com.cim.AIA.PresetIntArrayDataSelection(s16, false, (com.cim.AIA.AnimationWindow)this, a12);
        a13.setReinitialiseAlgorithm(false);
        a13.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a13);
        StringBuilder a14 = new StringBuilder();
        String s17 = SortedListAnimationWindow.USER_SELECTION_LABEL;
        StringBuilder a15 = a14.append(s17);
        StringBuilder a16 = a15.append("...");
        String s18 = a16.toString();
        String s19 = SortedListAnimationWindow.USER_SELECTION_LABEL;
        com.cim.AIA.UserSelectionIntArrayDataSelection a17 = new com.cim.AIA.UserSelectionIntArrayDataSelection(s18, false, (com.cim.AIA.AnimationWindow)this, s19, 1, 99, 5, 31);
        a17.setReinitialiseAlgorithm(false);
        a17.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a17);
    }
    
    public void printHello()
    {
        java.io.PrintStream a = System.out;
        a.println("hello");
    }
    
    static
    {
        String s = localization.Messages.getString("SortedListAnimationWindow.2");
        SortedListAnimationWindow.FRAME_TITLE = s;
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
        SortedListAnimationWindow.DEFAULT_DATA = a;
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
        SortedListAnimationWindow.SORTED_DATA = a0;
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
        SortedListAnimationWindow.REVERSE_SORTED_DATA = a1;
    }
}