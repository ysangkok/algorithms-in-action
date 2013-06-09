public class RadixTrieAnimationWindow extends com.cim.AIA.AnimationWindow {
    final protected static String BUILD_DATE = "Build Date";
    final protected static String BUILD_BY = "Your Initial.Your Surname";
    final protected static String FRAME_TITLE;
    final public static int[] DEFAULT_DATA;
    final private static int MINIMUM_NUMBER_OF_ELEMENTS = 5;
    final private static int MAXIMUM_NUMBER_OF_ELEMENTS = 20;
    final private static int MIN_SIZE = 3;
    final private static int MAX_SIZE = 99;
    
    public String getAlgorithmName()
    {
        return "AIA: RadixTrie";
    }
    
    public RadixTrieAnimationWindow(com.cim.AIA.AlgorithmCanvas a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1)
    {
        super(a, a0, a1);
        this.buildDate = "Build Date";
        this.buildBy = "Your Initial.Your Surname";
        String s = RadixTrieAnimationWindow.FRAME_TITLE;
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
        String s10 = localization.Messages.getString("RadixTrieAnimationWindow.3");
        String s11 = localization.Messages.getString("RadixTrieAnimationWindow.4");
        com.cim.AIA.RestartButton a7 = new com.cim.AIA.RestartButton(s10, s11, (com.cim.AIA.AnimationWindow)this, a0);
        a7.setReinitialiseAlgorithm(false);
        a7.setClearAlgorithmState(false);
        a7.setStoreAlgorithmState(true);
        a7.setForceAlgorithmStore(true);
        this.addControlButton((com.cim.AIA.ControlButton)a7);
        String s12 = RadixTrieAnimationWindow.DEFAULT_LABEL;
        int[] a8 = RadixTrieAnimationWindow.DEFAULT_DATA;
        com.cim.AIA.PresetIntArrayDataSelection a9 = new com.cim.AIA.PresetIntArrayDataSelection(s12, true, (com.cim.AIA.AnimationWindow)this, a8);
        a9.setReinitialiseAlgorithm(true);
        a9.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a9);
        String s13 = RadixTrieAnimationWindow.RANDOM_LABEL;
        com.cim.AIA.RandomIntArrayDataSelection a10 = new com.cim.AIA.RandomIntArrayDataSelection(s13, false, (com.cim.AIA.AnimationWindow)this, 5, 20, 3, 99);
        a10.setReinitialiseAlgorithm(true);
        a10.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a10);
        StringBuilder a11 = new StringBuilder();
        String s14 = RadixTrieAnimationWindow.USER_SELECTION_LABEL;
        StringBuilder a12 = a11.append(s14);
        StringBuilder a13 = a12.append("...");
        String s15 = a13.toString();
        String s16 = RadixTrieAnimationWindow.USER_SELECTION_LABEL;
        com.cim.AIA.UserSelectionIntArrayDataSelection a14 = new com.cim.AIA.UserSelectionIntArrayDataSelection(s15, false, (com.cim.AIA.AnimationWindow)this, s16, 3, 99, 5, 20);
        a14.setReinitialiseAlgorithm(true);
        a14.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a14);
        RadixTrieThread dummy = (RadixTrieThread)a0;
        RadixTrieThread a15 = (RadixTrieThread)a0;
        com.cim.AIA.Algorithm a16 = a15.getAlgorithm();
        com.cim.AIA.ControlListener dummy0 = (com.cim.AIA.ControlListener)a16;
        this.addControlListener((com.cim.AIA.ControlListener)a16);
    }
    
    static
    {
        String s = localization.Messages.getString("RadixTrieAnimationWindow.2");
        RadixTrieAnimationWindow.FRAME_TITLE = s;
        int[] a = new int[8];
        a[0] = 1;
        a[1] = 21;
        a[2] = 20;
        a[3] = 16;
        a[4] = 17;
        a[5] = 31;
        a[6] = 30;
        a[7] = 15;
        RadixTrieAnimationWindow.DEFAULT_DATA = a;
    }
}