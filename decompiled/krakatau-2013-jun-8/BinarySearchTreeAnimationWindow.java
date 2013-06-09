public class BinarySearchTreeAnimationWindow extends com.cim.AIA.AnimationWindow {
    final private static long serialVersionUID = -8803789268810674726L;
    final protected static String BUILD_DATE = "Build Date";
    final protected static String BUILD_BY = "Your Initial.Your Surname";
    final protected static String FRAME_TITLE;
    final protected static int NUMBER_OF_ELEMENTS = 10;
    final private static int MINIMUM_OF_ELEMENTS = 5;
    final private static int MAXIMUM_OF_ELEMENTS = 20;
    final public static int[] DEFAULT_DATA;
    final public static int[] SORTED_DATA;
    final protected static int MAX_SIZE = 99;
    final protected static int MIN_SIZE = 1;
    
    public String getAlgorithmName()
    {
        return "AIA: BinarySearchTree";
    }
    
    public BinarySearchTreeAnimationWindow(com.cim.AIA.AlgorithmCanvas a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1)
    {
        super(a, a0, a1);
        this.buildDate = "Build Date";
        this.buildBy = "Your Initial.Your Surname";
        String s = BinarySearchTreeAnimationWindow.FRAME_TITLE;
        this.frameTitle = s;
        String s0 = BinarySearchTreeAnimationWindow.DEFAULT_LABEL;
        int[] a2 = BinarySearchTreeAnimationWindow.DEFAULT_DATA;
        com.cim.AIA.PresetIntArrayDataSelection a3 = new com.cim.AIA.PresetIntArrayDataSelection(s0, true, (com.cim.AIA.AnimationWindow)this, a2);
        this.addDataSelection((com.cim.AIA.DataSelection)a3);
        String s1 = BinarySearchTreeAnimationWindow.RANDOM_LABEL;
        com.cim.AIA.RandomIntArrayDataSelection a4 = new com.cim.AIA.RandomIntArrayDataSelection(s1, false, (com.cim.AIA.AnimationWindow)this, 5, 20, 1, 99);
        this.addDataSelection((com.cim.AIA.DataSelection)a4);
        String s2 = BinarySearchTreeAnimationWindow.SORTED_LABEL;
        int[] a5 = BinarySearchTreeAnimationWindow.SORTED_DATA;
        com.cim.AIA.PresetIntArrayDataSelection a6 = new com.cim.AIA.PresetIntArrayDataSelection(s2, false, (com.cim.AIA.AnimationWindow)this, a5);
        this.addDataSelection((com.cim.AIA.DataSelection)a6);
        StringBuilder a7 = new StringBuilder();
        String s3 = BinarySearchTreeAnimationWindow.USER_SELECTION_LABEL;
        StringBuilder a8 = a7.append(s3);
        StringBuilder a9 = a8.append("...");
        String s4 = a9.toString();
        String s5 = BinarySearchTreeAnimationWindow.USER_SELECTION_LABEL;
        com.cim.AIA.UserSelectionIntArrayDataSelection a10 = new com.cim.AIA.UserSelectionIntArrayDataSelection(s4, false, (com.cim.AIA.AnimationWindow)this, s5, 1, 99, 5, 20);
        this.addDataSelection((com.cim.AIA.DataSelection)a10);
        String s6 = com.cim.AIA.AnimationWindow.STEP_LABEL;
        String s7 = com.cim.AIA.AnimationWindow.STEP_INSTRUCTIONS;
        com.cim.AIA.StepButton a11 = new com.cim.AIA.StepButton(s6, s7, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a11);
        String s8 = com.cim.AIA.AnimationWindow.BACK_LABEL;
        String s9 = com.cim.AIA.AnimationWindow.BACK_INSTRUCTIONS;
        NewBackButton a12 = new NewBackButton(s8, s9, (com.cim.AIA.AnimationWindow)this, a0);
        BinarySearchTree.backButton = a12;
        NewBackButton a13 = BinarySearchTree.backButton;
        this.addControlButton((com.cim.AIA.ControlButton)a13);
        String s10 = com.cim.AIA.AnimationWindow.RUN_LABEL;
        String s11 = com.cim.AIA.AnimationWindow.RUN_INSTRUCTIONS;
        com.cim.AIA.RunButton a14 = new com.cim.AIA.RunButton(s10, s11, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a14);
        String s12 = com.cim.AIA.AnimationWindow.PAUSE_LABEL;
        String s13 = com.cim.AIA.AnimationWindow.PAUSE_INSTRUCTIONS;
        com.cim.AIA.PauseButton a15 = new com.cim.AIA.PauseButton(s12, s13, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a15);
        String s14 = com.cim.AIA.AnimationWindow.RESET_LABEL;
        String s15 = com.cim.AIA.AnimationWindow.RESET_INSTRUCTIONS;
        com.cim.AIA.ResetButton a16 = new com.cim.AIA.ResetButton(s14, s15, (com.cim.AIA.AnimationWindow)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a16);
        String s16 = localization.Messages.getString("BinarySearchTreeAnimationWindow.4");
        String s17 = localization.Messages.getString("BinarySearchTreeAnimationWindow.5");
        com.cim.AIA.RestartButton a17 = new com.cim.AIA.RestartButton(s16, s17, (com.cim.AIA.AnimationWindow)this, a0);
        a17.setReinitialiseAlgorithm(false);
        a17.setClearAlgorithmState(false);
        a17.setStoreAlgorithmState(true);
        a17.setForceAlgorithmStore(true);
        this.addControlButton((com.cim.AIA.ControlButton)a17);
        BinarySearchTreeThread dummy = (BinarySearchTreeThread)a0;
        BinarySearchTreeThread a18 = (BinarySearchTreeThread)a0;
        com.cim.AIA.Algorithm a19 = a18.getAlgorithm();
        com.cim.AIA.ControlListener dummy0 = (com.cim.AIA.ControlListener)a19;
        this.addControlListener((com.cim.AIA.ControlListener)a19);
    }
    
    static
    {
        String s = localization.Messages.getString("BinarySearchTreeAnimationWindow.2");
        BinarySearchTreeAnimationWindow.FRAME_TITLE = s;
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
        BinarySearchTreeAnimationWindow.DEFAULT_DATA = a;
        int[] a0 = new int[10];
        BinarySearchTreeAnimationWindow.SORTED_DATA = a0;
        int i = 0;
        while(true)
        {
            if(i >= 10)
            {
                return;
            }
            else
            {
                int[] a1 = BinarySearchTreeAnimationWindow.SORTED_DATA;
                int i0 = i * 5;
                int i1 = i0 + 1;
                a1[i] = i1;
                int i2 = i + 1;
                i = i2;
            }
        }
    }
}