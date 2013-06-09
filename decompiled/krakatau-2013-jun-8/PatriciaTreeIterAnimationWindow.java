public class PatriciaTreeIterAnimationWindow extends com.cim.AIA.AnimationWindow {
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
    
    public PatriciaTreeIterAnimationWindow(com.cim.AIA.AlgorithmCanvas a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1)
    {
        super(a, a0, a1);
        this.buildDate = "Build Date";
        this.buildBy = "Your Initial.Your Surname";
        String s = PatriciaTreeIterAnimationWindow.FRAME_TITLE;
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
        String s10 = localization.Messages.getString("PatriciaTreeIterAnimationWindow.3");
        String s11 = localization.Messages.getString("PatriciaTreeIterAnimationWindow.4");
        com.cim.AIA.RestartButton a7 = new com.cim.AIA.RestartButton(s10, s11, (com.cim.AIA.AnimationWindow)this, a0);
        a7.setReinitialiseAlgorithm(false);
        a7.setClearAlgorithmState(false);
        a7.setStoreAlgorithmState(true);
        a7.setForceAlgorithmStore(true);
        this.addControlButton((com.cim.AIA.ControlButton)a7);
        PatriciaTreeIterThread dummy = (PatriciaTreeIterThread)a0;
        PatriciaTreeIterThread a8 = (PatriciaTreeIterThread)a0;
        com.cim.AIA.Algorithm a9 = a8.getAlgorithm();
        com.cim.AIA.ControlListener dummy0 = (com.cim.AIA.ControlListener)a9;
        this.addControlListener((com.cim.AIA.ControlListener)a9);
        String s12 = PatriciaTreeIterAnimationWindow.DEFAULT_LABEL;
        int[] a10 = PatriciaTreeIterAnimationWindow.DEFAULT_DATA;
        com.cim.AIA.PresetIntArrayDataSelection a11 = new com.cim.AIA.PresetIntArrayDataSelection(s12, true, (com.cim.AIA.AnimationWindow)this, a10);
        a11.setReinitialiseAlgorithm(false);
        a11.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a11);
        String s13 = PatriciaTreeIterAnimationWindow.RANDOM_LABEL;
        com.cim.AIA.RandomIntArrayDataSelection a12 = new com.cim.AIA.RandomIntArrayDataSelection(s13, false, (com.cim.AIA.AnimationWindow)this, 5, 30, 3, 99);
        a12.setReinitialiseAlgorithm(false);
        a12.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a12);
        StringBuilder a13 = new StringBuilder();
        String s14 = PatriciaTreeIterAnimationWindow.USER_SELECTION_LABEL;
        StringBuilder a14 = a13.append(s14);
        StringBuilder a15 = a14.append("...");
        String s15 = a15.toString();
        String s16 = PatriciaTreeIterAnimationWindow.USER_SELECTION_LABEL;
        com.cim.AIA.UserSelectionIntArrayDataSelection a16 = new com.cim.AIA.UserSelectionIntArrayDataSelection(s15, false, (com.cim.AIA.AnimationWindow)this, s16, 3, 99, 5, 30);
        a16.setReinitialiseAlgorithm(false);
        a16.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a16);
    }
    
    static
    {
        String s = localization.Messages.getString("PatriciaTreeIterAnimationWindow.2");
        PatriciaTreeIterAnimationWindow.FRAME_TITLE = s;
        int[] a = new int[17];
        a[0] = 31;
        a[1] = 30;
        a[2] = 28;
        a[3] = 27;
        a[4] = 21;
        a[5] = 24;
        a[6] = 23;
        a[7] = 20;
        a[8] = 17;
        a[9] = 19;
        a[10] = 25;
        a[11] = 18;
        a[12] = 29;
        a[13] = 26;
        a[14] = 2;
        a[15] = 4;
        a[16] = 5;
        PatriciaTreeIterAnimationWindow.DEFAULT_DATA = a;
    }
}