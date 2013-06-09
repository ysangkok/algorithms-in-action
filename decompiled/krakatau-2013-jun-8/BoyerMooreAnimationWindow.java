public class BoyerMooreAnimationWindow extends com.cim.AIA.AnimationWindow {
    final private static long serialVersionUID = -3974653996398494171L;
    final protected static String FRAME_TITLE;
    final public static int MINIMUM_NUMBER_OF_ELEMENTS = 5;
    final public static int MAXIMUM_NUMBER_OF_ELEMENTS = 5;
    final public static int MAX_SIZE = 4;
    final public static int MIN_SIZE = 0;
    final public static String[] DEFAULT_DATA_1;
    final public static String[] DEFAULT_DATA_2;
    final public static String[] DEFAULT_DATA_3;
    final public static int MAX_LENGTH = 25;
    final public static int MIN_LENGTH = 3;
    protected com.cim.AIA.PresetStringArrayDataSelection ds1;
    protected com.cim.AIA.PresetStringArrayDataSelection ds2;
    protected com.cim.AIA.PresetStringArrayDataSelection ds3;
    
    public String getAlgorithmName()
    {
        return "BoyerMoore String Search";
    }
    
    public BoyerMooreAnimationWindow(com.cim.AIA.AlgorithmCanvas a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1)
    {
        super(a, a0, a1);
        String s = BoyerMooreAnimationWindow.FRAME_TITLE;
        this.frameTitle = s;
        String s0 = localization.Messages.getString("BoyerMooreAnimationWindow.7");
        String[] a2 = BoyerMooreAnimationWindow.DEFAULT_DATA_1;
        com.cim.AIA.PresetStringArrayDataSelection a3 = new com.cim.AIA.PresetStringArrayDataSelection(s0, true, (com.cim.AIA.AnimationWindow)this, a2);
        this.ds1 = a3;
        String s1 = localization.Messages.getString("BoyerMooreAnimationWindow.8");
        String[] a4 = BoyerMooreAnimationWindow.DEFAULT_DATA_2;
        com.cim.AIA.PresetStringArrayDataSelection a5 = new com.cim.AIA.PresetStringArrayDataSelection(s1, false, (com.cim.AIA.AnimationWindow)this, a4);
        this.ds2 = a5;
        com.cim.AIA.PresetStringArrayDataSelection a6 = this.ds1;
        this.addDataSelection((com.cim.AIA.DataSelection)a6);
        com.cim.AIA.PresetStringArrayDataSelection a7 = this.ds2;
        this.addDataSelection((com.cim.AIA.DataSelection)a7);
        StringBuilder a8 = new StringBuilder();
        String s2 = BoyerMooreAnimationWindow.USER_SELECTION_LABEL;
        StringBuilder a9 = a8.append(s2);
        StringBuilder a10 = a9.append("...");
        String s3 = a10.toString();
        String s4 = BoyerMooreAnimationWindow.USER_SELECTION_LABEL;
        String s5 = localization.Messages.getString("BoyerMooreAnimationWindow.10");
        String s6 = localization.Messages.getString("BoyerMooreAnimationWindow.11");
        com.cim.AIA.UserSelectionStringDataSelection a11 = new com.cim.AIA.UserSelectionStringDataSelection(s3, false, (com.cim.AIA.AnimationWindow)this, s4, 3, 25, s5, s6, 49);
        this.addDataSelection((com.cim.AIA.DataSelection)a11);
        String s7 = com.cim.AIA.AnimationWindow.STEP_LABEL;
        String s8 = com.cim.AIA.AnimationWindow.STEP_INSTRUCTIONS;
        com.cim.AIA.StepButton a12 = new com.cim.AIA.StepButton(s7, s8, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a12);
        String s9 = com.cim.AIA.AnimationWindow.BACK_LABEL;
        String s10 = com.cim.AIA.AnimationWindow.BACK_INSTRUCTIONS;
        com.cim.AIA.BackButton a13 = new com.cim.AIA.BackButton(s9, s10, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a13);
        String s11 = com.cim.AIA.AnimationWindow.RUN_LABEL;
        String s12 = com.cim.AIA.AnimationWindow.RUN_INSTRUCTIONS;
        com.cim.AIA.RunButton a14 = new com.cim.AIA.RunButton(s11, s12, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a14);
        String s13 = com.cim.AIA.AnimationWindow.PAUSE_LABEL;
        String s14 = com.cim.AIA.AnimationWindow.PAUSE_LABEL;
        com.cim.AIA.PauseButton a15 = new com.cim.AIA.PauseButton(s13, s14, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a15);
        String s15 = com.cim.AIA.AnimationWindow.RESET_LABEL;
        String s16 = com.cim.AIA.AnimationWindow.RESET_INSTRUCTIONS;
        com.cim.AIA.ResetButton a16 = new com.cim.AIA.ResetButton(s15, s16, (com.cim.AIA.AnimationWindow)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a16);
        com.cim.AIA.SelfTestModeSelection a17 = this.selfTestMode;
        a17.setEnabled(false);
    }
    
    static
    {
        String s = localization.Messages.getString("BoyerMooreAnimationWindow.0");
        BoyerMooreAnimationWindow.FRAME_TITLE = s;
        String[] a = new String[2];
        String s0 = localization.Messages.getString("BoyerMooreAnimationWindow.1");
        a[0] = s0;
        String s1 = localization.Messages.getString("BoyerMooreAnimationWindow.2");
        a[1] = s1;
        BoyerMooreAnimationWindow.DEFAULT_DATA_1 = a;
        String[] a0 = new String[2];
        String s2 = localization.Messages.getString("BoyerMooreAnimationWindow.3");
        a0[0] = s2;
        String s3 = localization.Messages.getString("BoyerMooreAnimationWindow.4");
        a0[1] = s3;
        BoyerMooreAnimationWindow.DEFAULT_DATA_2 = a0;
        String[] a1 = new String[2];
        String s4 = localization.Messages.getString("BoyerMooreAnimationWindow.5");
        a1[0] = s4;
        String s5 = localization.Messages.getString("BoyerMooreAnimationWindow.6");
        a1[1] = s5;
        BoyerMooreAnimationWindow.DEFAULT_DATA_3 = a1;
    }
}