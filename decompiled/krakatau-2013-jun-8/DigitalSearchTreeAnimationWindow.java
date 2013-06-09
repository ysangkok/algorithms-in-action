public class DigitalSearchTreeAnimationWindow extends com.cim.AIA.AnimationWindow {
    final protected static String BUILD_DATE;
    final protected static String BUILD_BY;
    final protected static String FRAME_TITLE;
    final protected static int NUMBER_OF_ELEMENTS = 10;
    final private static int MINIMUM_NUMBER_OF_ELEMENTS = 5;
    final private static int MAXIMUM_NUMBER_OF_ELEMENTS = 31;
    final public static int[] DEFAULT_DATA;
    final public static int[] SORTED_DATA;
    final public static int[] SKEWED_DATA;
    final public static int[] EXPONENTIAL_DATA;
    final public static String EXPONENTIAL_LABEL;
    final public static String SKEWED_LABEL;
    final protected static int MAX_SIZE = 99;
    final protected static int MIN_SIZE = 1;
    
    public String getAlgorithmName()
    {
        return "AIA: DigitalSearchTree";
    }
    
    public DigitalSearchTreeAnimationWindow(com.cim.AIA.AlgorithmCanvas a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1)
    {
        super(a, a0, a1);
        String s = DigitalSearchTreeAnimationWindow.BUILD_DATE;
        this.buildDate = s;
        String s0 = DigitalSearchTreeAnimationWindow.BUILD_BY;
        this.buildBy = s0;
        String s1 = DigitalSearchTreeAnimationWindow.FRAME_TITLE;
        this.frameTitle = s1;
        String s2 = DigitalSearchTreeAnimationWindow.DEFAULT_LABEL;
        int[] a2 = DigitalSearchTreeAnimationWindow.DEFAULT_DATA;
        com.cim.AIA.PresetIntArrayDataSelection a3 = new com.cim.AIA.PresetIntArrayDataSelection(s2, true, (com.cim.AIA.AnimationWindow)this, a2);
        a3.setReinitialiseAlgorithm(false);
        a3.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a3);
        String s3 = DigitalSearchTreeAnimationWindow.RANDOM_LABEL;
        com.cim.AIA.RandomIntArrayDataSelection a4 = new com.cim.AIA.RandomIntArrayDataSelection(s3, false, (com.cim.AIA.AnimationWindow)this, 5, 31, 1, 99);
        a4.setReinitialiseAlgorithm(false);
        a4.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a4);
        String s4 = DigitalSearchTreeAnimationWindow.SORTED_LABEL;
        int[] a5 = DigitalSearchTreeAnimationWindow.SORTED_DATA;
        com.cim.AIA.PresetIntArrayDataSelection a6 = new com.cim.AIA.PresetIntArrayDataSelection(s4, false, (com.cim.AIA.AnimationWindow)this, a5);
        a6.setReinitialiseAlgorithm(false);
        a6.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a6);
        String s5 = DigitalSearchTreeAnimationWindow.SKEWED_LABEL;
        int[] a7 = DigitalSearchTreeAnimationWindow.SKEWED_DATA;
        com.cim.AIA.PresetIntArrayDataSelection a8 = new com.cim.AIA.PresetIntArrayDataSelection(s5, false, (com.cim.AIA.AnimationWindow)this, a7);
        a8.setReinitialiseAlgorithm(false);
        a8.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a8);
        String s6 = DigitalSearchTreeAnimationWindow.EXPONENTIAL_LABEL;
        int[] a9 = DigitalSearchTreeAnimationWindow.EXPONENTIAL_DATA;
        com.cim.AIA.PresetIntArrayDataSelection a10 = new com.cim.AIA.PresetIntArrayDataSelection(s6, false, (com.cim.AIA.AnimationWindow)this, a9);
        a10.setReinitialiseAlgorithm(false);
        a10.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a10);
        StringBuilder a11 = new StringBuilder();
        String s7 = DigitalSearchTreeAnimationWindow.USER_SELECTION_LABEL;
        StringBuilder a12 = a11.append(s7);
        StringBuilder a13 = a12.append("...");
        String s8 = a13.toString();
        String s9 = DigitalSearchTreeAnimationWindow.USER_SELECTION_LABEL;
        com.cim.AIA.UserSelectionIntArrayDataSelection a14 = new com.cim.AIA.UserSelectionIntArrayDataSelection(s8, false, (com.cim.AIA.AnimationWindow)this, s9, 1, 99, 5, 31);
        a14.setReinitialiseAlgorithm(false);
        a14.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a14);
        String s10 = com.cim.AIA.AnimationWindow.STEP_LABEL;
        String s11 = com.cim.AIA.AnimationWindow.STEP_INSTRUCTIONS;
        com.cim.AIA.StepButton a15 = new com.cim.AIA.StepButton(s10, s11, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a15);
        String s12 = com.cim.AIA.AnimationWindow.BACK_LABEL;
        String s13 = com.cim.AIA.AnimationWindow.BACK_INSTRUCTIONS;
        com.cim.AIA.BackButton a16 = new com.cim.AIA.BackButton(s12, s13, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a16);
        String s14 = com.cim.AIA.AnimationWindow.RUN_LABEL;
        String s15 = com.cim.AIA.AnimationWindow.RUN_INSTRUCTIONS;
        com.cim.AIA.RunButton a17 = new com.cim.AIA.RunButton(s14, s15, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a17);
        String s16 = com.cim.AIA.AnimationWindow.PAUSE_LABEL;
        String s17 = com.cim.AIA.AnimationWindow.PAUSE_INSTRUCTIONS;
        com.cim.AIA.PauseButton a18 = new com.cim.AIA.PauseButton(s16, s17, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a18);
        String s18 = com.cim.AIA.AnimationWindow.RESET_LABEL;
        String s19 = com.cim.AIA.AnimationWindow.RESET_INSTRUCTIONS;
        com.cim.AIA.ResetButton a19 = new com.cim.AIA.ResetButton(s18, s19, (com.cim.AIA.AnimationWindow)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a19);
        String s20 = localization.Messages.getString("DigitalSearchTreeAnimationWindow.6");
        String s21 = localization.Messages.getString("DigitalSearchTreeAnimationWindow.7");
        com.cim.AIA.RestartButton a20 = new com.cim.AIA.RestartButton(s20, s21, (com.cim.AIA.AnimationWindow)this, a0);
        a20.setReinitialiseAlgorithm(false);
        a20.setClearAlgorithmState(false);
        a20.setStoreAlgorithmState(true);
        a20.setForceAlgorithmStore(true);
        this.addControlButton((com.cim.AIA.ControlButton)a20);
        DigitalSearchTreeThread dummy = (DigitalSearchTreeThread)a0;
        DigitalSearchTreeThread a21 = (DigitalSearchTreeThread)a0;
        com.cim.AIA.Algorithm a22 = a21.getAlgorithm();
        com.cim.AIA.ControlListener dummy0 = (com.cim.AIA.ControlListener)a22;
        this.addControlListener((com.cim.AIA.ControlListener)a22);
    }
    
    static
    {
        String s = localization.Messages.getString("DigitalSearchTreeAnimationWindow.0");
        DigitalSearchTreeAnimationWindow.BUILD_DATE = s;
        String s0 = localization.Messages.getString("DigitalSearchTreeAnimationWindow.1");
        DigitalSearchTreeAnimationWindow.BUILD_BY = s0;
        String s1 = localization.Messages.getString("DigitalSearchTreeAnimationWindow.2");
        DigitalSearchTreeAnimationWindow.FRAME_TITLE = s1;
        int[] a = new int[15];
        a[0] = 50;
        a[1] = 25;
        a[2] = 75;
        a[3] = 12;
        a[4] = 37;
        a[5] = 62;
        a[6] = 87;
        a[7] = 6;
        a[8] = 18;
        a[9] = 31;
        a[10] = 43;
        a[11] = 56;
        a[12] = 68;
        a[13] = 81;
        a[14] = 93;
        DigitalSearchTreeAnimationWindow.DEFAULT_DATA = a;
        int[] a0 = new int[31];
        DigitalSearchTreeAnimationWindow.SORTED_DATA = a0;
        int[] a1 = new int[16];
        a1[0] = 57;
        a1[1] = 61;
        a1[2] = 54;
        a1[3] = 53;
        a1[4] = 49;
        a1[5] = 56;
        a1[6] = 52;
        a1[7] = 62;
        a1[8] = 58;
        a1[9] = 55;
        a1[10] = 48;
        a1[11] = 60;
        a1[12] = 50;
        a1[13] = 59;
        a1[14] = 51;
        a1[15] = 63;
        DigitalSearchTreeAnimationWindow.SKEWED_DATA = a1;
        int[] a2 = new int[10];
        DigitalSearchTreeAnimationWindow.EXPONENTIAL_DATA = a2;
        String s2 = localization.Messages.getString("DigitalSearchTreeAnimationWindow.3");
        DigitalSearchTreeAnimationWindow.EXPONENTIAL_LABEL = s2;
        String s3 = localization.Messages.getString("DigitalSearchTreeAnimationWindow.4");
        DigitalSearchTreeAnimationWindow.SKEWED_LABEL = s3;
        int i = 0;
        while(true)
        {
            if(i >= 31)
            {
                break;
            }
            else
            {
                int[] a3 = DigitalSearchTreeAnimationWindow.SORTED_DATA;
                int i0 = i + 1;
                a3[i] = i0;
                int i1 = i + 1;
                i = i1;
            }
        }
        int i2 = 0;
        while(true)
        {
            if(i2 >= 10)
            {
                return;
            }
            else
            {
                int[] a4 = DigitalSearchTreeAnimationWindow.EXPONENTIAL_DATA;
                int i3 = 10 - i2;
                int i4 = i3 - 1;
                int i5 = 1 << i4;
                a4[i2] = i5;
                int i6 = i2 + 1;
                i2 = i6;
            }
        }
    }
}