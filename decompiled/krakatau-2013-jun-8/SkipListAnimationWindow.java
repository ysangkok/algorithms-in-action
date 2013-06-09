public class SkipListAnimationWindow extends com.cim.AIA.AnimationWindow {
    final protected static String BUILD_DATE = "Build Date";
    final protected static String BUILD_BY = "Your Initial.Your Surname";
    final protected static String FRAME_TITLE;
    final protected static int NUMBER_OF_ELEMENTS = 10;
    private static int minNoOfElements;
    private static int maxNoOfElements;
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
        return "AIA: SkipList";
    }
    
    public SkipListAnimationWindow(com.cim.AIA.AlgorithmCanvas a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1)
    {
        super(a, a0, a1);
        this.minimumNumberOfElements = 1;
        this.maximumNumberOfElements = 20;
        this.maxSize = 99;
        this.minSize = 0;
        this.buildDate = "Build Date";
        this.buildBy = "Your Initial.Your Surname";
        String s = SkipListAnimationWindow.FRAME_TITLE;
        this.frameTitle = s;
        SkipListThread dummy = (SkipListThread)a0;
        SkipListThread a2 = (SkipListThread)a0;
        com.cim.AIA.Algorithm a3 = a2.getAlgorithm();
        SkipList dummy0 = (SkipList)a3;
        SkipListAnimationWindow.minNoOfElements = 5;
        SkipListThread dummy1 = (SkipListThread)a0;
        SkipListThread a4 = (SkipListThread)a0;
        com.cim.AIA.Algorithm a5 = a4.getAlgorithm();
        SkipList dummy2 = (SkipList)a5;
        SkipListAnimationWindow.maxNoOfElements = 31;
        String s0 = com.cim.AIA.AnimationWindow.STEP_LABEL;
        String s1 = com.cim.AIA.AnimationWindow.STEP_INSTRUCTIONS;
        com.cim.AIA.StepButton a6 = new com.cim.AIA.StepButton(s0, s1, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a6);
        String s2 = com.cim.AIA.AnimationWindow.BACK_LABEL;
        String s3 = com.cim.AIA.AnimationWindow.BACK_INSTRUCTIONS;
        com.cim.AIA.BackButton a7 = new com.cim.AIA.BackButton(s2, s3, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a7);
        String s4 = com.cim.AIA.AnimationWindow.RUN_LABEL;
        String s5 = com.cim.AIA.AnimationWindow.RUN_INSTRUCTIONS;
        com.cim.AIA.RunButton a8 = new com.cim.AIA.RunButton(s4, s5, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a8);
        String s6 = com.cim.AIA.AnimationWindow.PAUSE_LABEL;
        String s7 = com.cim.AIA.AnimationWindow.PAUSE_INSTRUCTIONS;
        com.cim.AIA.PauseButton a9 = new com.cim.AIA.PauseButton(s6, s7, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a9);
        String s8 = com.cim.AIA.AnimationWindow.RESET_LABEL;
        String s9 = com.cim.AIA.AnimationWindow.RESET_INSTRUCTIONS;
        com.cim.AIA.ResetButton a10 = new com.cim.AIA.ResetButton(s8, s9, (com.cim.AIA.AnimationWindow)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a10);
        String s10 = localization.Messages.getString("SkipListAnimationWindow.3");
        String s11 = localization.Messages.getString("SkipListAnimationWindow.4");
        com.cim.AIA.RestartButton a11 = new com.cim.AIA.RestartButton(s10, s11, (com.cim.AIA.AnimationWindow)this, a0);
        a11.setReinitialiseAlgorithm(false);
        a11.setClearAlgorithmState(false);
        a11.setStoreAlgorithmState(true);
        a11.setForceAlgorithmStore(true);
        this.addControlButton((com.cim.AIA.ControlButton)a11);
        SkipListThread dummy3 = (SkipListThread)a0;
        SkipListThread a12 = (SkipListThread)a0;
        com.cim.AIA.Algorithm a13 = a12.getAlgorithm();
        com.cim.AIA.ControlListener dummy4 = (com.cim.AIA.ControlListener)a13;
        this.addControlListener((com.cim.AIA.ControlListener)a13);
        String s12 = SkipListAnimationWindow.DEFAULT_LABEL;
        int[] a14 = SkipListAnimationWindow.DEFAULT_DATA;
        com.cim.AIA.PresetIntArrayDataSelection a15 = new com.cim.AIA.PresetIntArrayDataSelection(s12, true, (com.cim.AIA.AnimationWindow)this, a14);
        a15.setReinitialiseAlgorithm(false);
        a15.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a15);
        String s13 = SkipListAnimationWindow.RANDOM_LABEL;
        int i = SkipListAnimationWindow.minNoOfElements;
        int i0 = SkipListAnimationWindow.maxNoOfElements;
        com.cim.AIA.RandomIntArrayDataSelection a16 = new com.cim.AIA.RandomIntArrayDataSelection(s13, false, (com.cim.AIA.AnimationWindow)this, i, i0, 1, 99);
        a16.setReinitialiseAlgorithm(false);
        a16.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a16);
        String s14 = SkipListAnimationWindow.SORTED_LABEL;
        int[] a17 = SkipListAnimationWindow.SORTED_DATA;
        com.cim.AIA.PresetIntArrayDataSelection a18 = new com.cim.AIA.PresetIntArrayDataSelection(s14, false, (com.cim.AIA.AnimationWindow)this, a17);
        a18.setReinitialiseAlgorithm(false);
        a18.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a18);
        StringBuilder a19 = new StringBuilder();
        String s15 = SkipListAnimationWindow.USER_SELECTION_LABEL;
        StringBuilder a20 = a19.append(s15);
        StringBuilder a21 = a20.append("...");
        String s16 = a21.toString();
        String s17 = SkipListAnimationWindow.USER_SELECTION_LABEL;
        int i1 = SkipListAnimationWindow.minNoOfElements;
        int i2 = SkipListAnimationWindow.maxNoOfElements;
        com.cim.AIA.UserSelectionIntArrayDataSelection a22 = new com.cim.AIA.UserSelectionIntArrayDataSelection(s16, false, (com.cim.AIA.AnimationWindow)this, s17, 1, 99, i1, i2);
        a22.setReinitialiseAlgorithm(false);
        a22.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a22);
    }
    
    public void printHello()
    {
    }
    
    static
    {
        String s = localization.Messages.getString("SkipListAnimationWindow.2");
        SkipListAnimationWindow.FRAME_TITLE = s;
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
        SkipListAnimationWindow.DEFAULT_DATA = a;
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
        SkipListAnimationWindow.SORTED_DATA = a0;
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
        SkipListAnimationWindow.REVERSE_SORTED_DATA = a1;
    }
}