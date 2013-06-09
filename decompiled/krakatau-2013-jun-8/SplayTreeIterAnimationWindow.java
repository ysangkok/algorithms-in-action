public class SplayTreeIterAnimationWindow extends com.cim.AIA.AnimationWindow implements java.awt.event.ItemListener {
    final protected static String BUILD_DATE = "Build Date";
    final protected static String BUILD_BY = "Your Initial.Your Surname";
    final protected static String FRAME_TITLE;
    final public static int[] DEFAULT_DATA;
    final public static int[] SORTED_DATA;
    final public static String SORTED_LABAL;
    final private static int MINIMUM_NUMBER_OF_ELEMENTS = 5;
    final private static int MAXIMUM_NUMBER_OF_ELEMENTS = 20;
    final private static int MIN_SIZE = 3;
    final private static int MAX_SIZE = 99;
    
    public String getAlgorithmName()
    {
        return "AIA: SplayTree";
    }
    
    public SplayTreeIterAnimationWindow(com.cim.AIA.AlgorithmCanvas a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1)
    {
        super(a, a0, a1);
        this.buildDate = "Build Date";
        this.buildBy = "Your Initial.Your Surname";
        String s = SplayTreeIterAnimationWindow.FRAME_TITLE;
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
        String s10 = localization.Messages.getString("SplayTreeIterAnimationWindow.4");
        String s11 = localization.Messages.getString("SplayTreeIterAnimationWindow.5");
        com.cim.AIA.RestartButton a7 = new com.cim.AIA.RestartButton(s10, s11, (com.cim.AIA.AnimationWindow)this, a0);
        a7.setReinitialiseAlgorithm(false);
        a7.setClearAlgorithmState(false);
        a7.setStoreAlgorithmState(true);
        a7.setForceAlgorithmStore(true);
        this.addControlButton((com.cim.AIA.ControlButton)a7);
        String s12 = localization.Messages.getString("SplayTreeIterAnimationWindow.6");
        java.awt.Checkbox a8 = new java.awt.Checkbox(s12, true);
        a8.addItemListener((java.awt.event.ItemListener)this);
        java.awt.Panel a9 = this.controlPanel;
        java.awt.Component a10 = a9.add((java.awt.Component)a8);
        String s13 = SplayTreeIterAnimationWindow.DEFAULT_LABEL;
        int[] a11 = SplayTreeIterAnimationWindow.DEFAULT_DATA;
        com.cim.AIA.PresetIntArrayDataSelection a12 = new com.cim.AIA.PresetIntArrayDataSelection(s13, true, (com.cim.AIA.AnimationWindow)this, a11);
        a12.setReinitialiseAlgorithm(true);
        a12.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a12);
        String s14 = SplayTreeIterAnimationWindow.SORTED_LABEL;
        int[] a13 = SplayTreeIterAnimationWindow.SORTED_DATA;
        com.cim.AIA.PresetIntArrayDataSelection a14 = new com.cim.AIA.PresetIntArrayDataSelection(s14, false, (com.cim.AIA.AnimationWindow)this, a13);
        a12.setReinitialiseAlgorithm(true);
        a12.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a14);
        String s15 = SplayTreeIterAnimationWindow.RANDOM_LABEL;
        com.cim.AIA.RandomIntArrayDataSelection a15 = new com.cim.AIA.RandomIntArrayDataSelection(s15, false, (com.cim.AIA.AnimationWindow)this, 5, 20, 3, 99);
        a15.setReinitialiseAlgorithm(true);
        a15.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a15);
        StringBuilder a16 = new StringBuilder();
        String s16 = SplayTreeIterAnimationWindow.USER_SELECTION_LABEL;
        StringBuilder a17 = a16.append(s16);
        StringBuilder a18 = a17.append("...");
        String s17 = a18.toString();
        String s18 = SplayTreeIterAnimationWindow.USER_SELECTION_LABEL;
        com.cim.AIA.UserSelectionIntArrayDataSelection a19 = new com.cim.AIA.UserSelectionIntArrayDataSelection(s17, false, (com.cim.AIA.AnimationWindow)this, s18, 3, 99, 5, 20);
        a19.setReinitialiseAlgorithm(true);
        a19.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a19);
        SplayTreeIterThread dummy = (SplayTreeIterThread)a0;
        SplayTreeIterThread a20 = (SplayTreeIterThread)a0;
        com.cim.AIA.Algorithm a21 = a20.getAlgorithm();
        com.cim.AIA.ControlListener dummy0 = (com.cim.AIA.ControlListener)a21;
        this.addControlListener((com.cim.AIA.ControlListener)a21);
    }
    
    public void itemStateChanged(java.awt.event.ItemEvent a)
    {
        int i = a.getStateChange();
        label1: {
            label0: {
                if(i != 1)
                {
                    break label0;
                }
                com.cim.AIA.AlgorithmCanvas a0 = this.canvas;
                SplayTreeIterCanvas dummy = (SplayTreeIterCanvas)a0;
                SplayTreeIterCanvas a1 = (SplayTreeIterCanvas)a0;
                a1.setIsShowTween(true);
                break label1;
            }
            int i0 = a.getStateChange();
            if(i0 == 2)
            {
                com.cim.AIA.AlgorithmCanvas a2 = this.canvas;
                SplayTreeIterCanvas dummy0 = (SplayTreeIterCanvas)a2;
                SplayTreeIterCanvas a3 = (SplayTreeIterCanvas)a2;
                a3.setIsShowTween(false);
            }
        }
    }
    
    static
    {
        String s = localization.Messages.getString("SplayTreeIterAnimationWindow.2");
        SplayTreeIterAnimationWindow.FRAME_TITLE = s;
        int[] a = new int[9];
        a[0] = 4;
        a[1] = 7;
        a[2] = 15;
        a[3] = 1;
        a[4] = 30;
        a[5] = 10;
        a[6] = 1;
        a[7] = 2;
        a[8] = 3;
        SplayTreeIterAnimationWindow.DEFAULT_DATA = a;
        int[] a0 = new int[29];
        a0[0] = 4;
        a0[1] = 7;
        a0[2] = 8;
        a0[3] = 10;
        a0[4] = 13;
        a0[5] = 15;
        a0[6] = 16;
        a0[7] = 17;
        a0[8] = 18;
        a0[9] = 20;
        a0[10] = 21;
        a0[11] = 22;
        a0[12] = 23;
        a0[13] = 24;
        a0[14] = 25;
        a0[15] = 26;
        a0[16] = 27;
        a0[17] = 28;
        a0[18] = 29;
        a0[19] = 30;
        a0[20] = 31;
        a0[21] = 32;
        a0[22] = 1;
        a0[23] = 5;
        a0[24] = 6;
        a0[25] = 9;
        a0[26] = 14;
        a0[27] = 19;
        a0[28] = 3;
        SplayTreeIterAnimationWindow.SORTED_DATA = a0;
        String s0 = localization.Messages.getString("SplayTreeIterAnimationWindow.3");
        SplayTreeIterAnimationWindow.SORTED_LABAL = s0;
    }
}