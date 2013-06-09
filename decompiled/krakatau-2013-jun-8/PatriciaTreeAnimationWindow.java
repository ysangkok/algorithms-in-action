public class PatriciaTreeAnimationWindow extends com.cim.AIA.AnimationWindow {
    final private static long serialVersionUID = -3663749469815675406L;
    final protected static String BUILD_DATE = "Build Date";
    final protected static String BUILD_BY = "Your Initial.Your Surname";
    final protected static String FRAME_TITLE;
    final private static int MINIMUM_NUMBER_OF_ELEMENTS = 5;
    final private static int MAXIMUM_NUMBER_OF_ELEMENTS = 30;
    final private static int MIN_SIZE = 3;
    final private static int MAX_SIZE = 99;
    final public static int[] DEFAULT_DATA;
    
    public String getAlgorithmName()
    {
        return "AIA: PatriciaTree";
    }
    
    public PatriciaTreeAnimationWindow(com.cim.AIA.AlgorithmCanvas a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1)
    {
        super(a, a0, a1);
        this.buildDate = "Build Date";
        this.buildBy = "Your Initial.Your Surname";
        String s = PatriciaTreeAnimationWindow.FRAME_TITLE;
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
        String s10 = localization.Messages.getString("PatriciaTreeAnimationWindow.1");
        String s11 = localization.Messages.getString("PatriciaTreeAnimationWindow.0");
        com.cim.AIA.RestartButton a7 = new com.cim.AIA.RestartButton(s10, s11, (com.cim.AIA.AnimationWindow)this, a0);
        a7.setReinitialiseAlgorithm(false);
        a7.setClearAlgorithmState(false);
        a7.setStoreAlgorithmState(true);
        a7.setForceAlgorithmStore(true);
        this.addControlButton((com.cim.AIA.ControlButton)a7);
        PatriciaTreeThread dummy = (PatriciaTreeThread)a0;
        PatriciaTreeThread a8 = (PatriciaTreeThread)a0;
        com.cim.AIA.Algorithm a9 = a8.getAlgorithm();
        com.cim.AIA.ControlListener dummy0 = (com.cim.AIA.ControlListener)a9;
        this.addControlListener((com.cim.AIA.ControlListener)a9);
        String s12 = PatriciaTreeAnimationWindow.DEFAULT_LABEL;
        int[] a10 = PatriciaTreeAnimationWindow.DEFAULT_DATA;
        com.cim.AIA.PresetIntArrayDataSelection a11 = new com.cim.AIA.PresetIntArrayDataSelection(s12, true, (com.cim.AIA.AnimationWindow)this, a10);
        a11.setReinitialiseAlgorithm(false);
        a11.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a11);
        String s13 = PatriciaTreeAnimationWindow.RANDOM_LABEL;
        com.cim.AIA.RandomIntArrayDataSelection a12 = new com.cim.AIA.RandomIntArrayDataSelection(s13, false, (com.cim.AIA.AnimationWindow)this, 5, 30, 3, 99);
        a12.setReinitialiseAlgorithm(false);
        a12.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a12);
        StringBuilder a13 = new StringBuilder();
        String s14 = PatriciaTreeAnimationWindow.USER_SELECTION_LABEL;
        StringBuilder a14 = a13.append(s14);
        StringBuilder a15 = a14.append("...");
        String s15 = a15.toString();
        String s16 = PatriciaTreeAnimationWindow.USER_SELECTION_LABEL;
        com.cim.AIA.UserSelectionIntArrayDataSelection a16 = new com.cim.AIA.UserSelectionIntArrayDataSelection(s15, false, (com.cim.AIA.AnimationWindow)this, s16, 3, 99, 5, 30);
        a16.setReinitialiseAlgorithm(false);
        a16.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a16);
    }
    
    static
    {
        String s = localization.Messages.getString("PatriciaTreeAnimationWindow.2");
        PatriciaTreeAnimationWindow.FRAME_TITLE = s;
        int[] a = new int[18];
        a[0] = 1;
        a[1] = 31;
        a[2] = 30;
        a[3] = 28;
        a[4] = 27;
        a[5] = 21;
        a[6] = 24;
        a[7] = 23;
        a[8] = 20;
        a[9] = 17;
        a[10] = 19;
        a[11] = 25;
        a[12] = 18;
        a[13] = 29;
        a[14] = 26;
        a[15] = 2;
        a[16] = 4;
        a[17] = 5;
        PatriciaTreeAnimationWindow.DEFAULT_DATA = a;
    }
}