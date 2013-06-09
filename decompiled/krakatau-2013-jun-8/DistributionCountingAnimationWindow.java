public class DistributionCountingAnimationWindow extends com.cim.AIA.AnimationWindow {
    final private static long serialVersionUID = -7308464985302398244L;
    final protected static String BUILD_DATE = "15/06/1999";
    final protected static String BUILD_BY = "T.Witham";
    final protected static String FRAME_TITLE;
    final public static int MINIMUM_NUMBER_OF_ELEMENTS = 3;
    final public static int MAXIMUM_NUMBER_OF_ELEMENTS = 20;
    final public static int MAX_SIZE = 4;
    final public static int MIN_SIZE = 0;
    
    public String getAlgorithmName()
    {
        return "AIA: Distribution Counting";
    }
    
    public DistributionCountingAnimationWindow(com.cim.AIA.AlgorithmCanvas a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1)
    {
        super(a, a0, a1);
        this.buildDate = "15/06/1999";
        this.buildBy = "T.Witham";
        String s = DistributionCountingAnimationWindow.FRAME_TITLE;
        this.frameTitle = s;
        String s0 = DistributionCountingAnimationWindow.RANDOM_LABEL;
        com.cim.AIA.RandomIntArrayDataSelection a2 = new com.cim.AIA.RandomIntArrayDataSelection(s0, true, (com.cim.AIA.AnimationWindow)this, 3, 20, 0, 4);
        this.addDataSelection((com.cim.AIA.DataSelection)a2);
        StringBuilder a3 = new StringBuilder();
        String s1 = DistributionCountingAnimationWindow.USER_SELECTION_LABEL;
        StringBuilder a4 = a3.append(s1);
        StringBuilder a5 = a4.append("...");
        String s2 = a5.toString();
        String s3 = DistributionCountingAnimationWindow.USER_SELECTION_LABEL;
        com.cim.AIA.UserSelectionIntArrayDataSelection a6 = new com.cim.AIA.UserSelectionIntArrayDataSelection(s2, false, (com.cim.AIA.AnimationWindow)this, s3, 0, 4, 3, 20);
        this.addDataSelection((com.cim.AIA.DataSelection)a6);
        String s4 = com.cim.AIA.AnimationWindow.STEP_LABEL;
        String s5 = com.cim.AIA.AnimationWindow.STEP_INSTRUCTIONS;
        com.cim.AIA.StepButton a7 = new com.cim.AIA.StepButton(s4, s5, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a7);
        String s6 = com.cim.AIA.AnimationWindow.BACK_LABEL;
        String s7 = com.cim.AIA.AnimationWindow.BACK_INSTRUCTIONS;
        com.cim.AIA.BackButton a8 = new com.cim.AIA.BackButton(s6, s7, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a8);
        String s8 = com.cim.AIA.AnimationWindow.RUN_LABEL;
        String s9 = com.cim.AIA.AnimationWindow.RUN_INSTRUCTIONS;
        com.cim.AIA.RunButton a9 = new com.cim.AIA.RunButton(s8, s9, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a9);
        String s10 = com.cim.AIA.AnimationWindow.PAUSE_LABEL;
        String s11 = com.cim.AIA.AnimationWindow.PAUSE_INSTRUCTIONS;
        com.cim.AIA.PauseButton a10 = new com.cim.AIA.PauseButton(s10, s11, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a10);
        String s12 = com.cim.AIA.AnimationWindow.RESET_LABEL;
        String s13 = com.cim.AIA.AnimationWindow.RESET_INSTRUCTIONS;
        com.cim.AIA.ResetButton a11 = new com.cim.AIA.ResetButton(s12, s13, (com.cim.AIA.AnimationWindow)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a11);
        com.cim.AIA.SelfTestModeSelection a12 = this.selfTestMode;
        a12.setEnabled(false);
    }
    
    static
    {
        String s = localization.Messages.getString("DistributionCountingAnimationWindow.2");
        DistributionCountingAnimationWindow.FRAME_TITLE = s;
    }
}