public class RadixTrieMultiAnimationWindow extends com.cim.AIA.AnimationWindow {
    final protected static String BUILD_DATE = "Build Date";
    final protected static String BUILD_BY = "Your Initial.Your Surname";
    final protected static String FRAME_TITLE;
    final public static int[] DEFAULT_DATA;
    final private static int MINIMUM_NUMBER_OF_ELEMENTS = 5;
    final private static int MAXIMUM_NUMBER_OF_ELEMENTS = 20;
    final private static int MIN_SIZE = 1;
    final private static int MAX_SIZE = 99;
    java.awt.CheckboxMenuItem mnu1Bit;
    java.awt.CheckboxMenuItem mnu2Bit;
    java.awt.CheckboxMenuItem mnu3Bit;
    
    public String getAlgorithmName()
    {
        return "AIA: RadixTrieMulti";
    }
    
    public RadixTrieMultiAnimationWindow(com.cim.AIA.AlgorithmCanvas a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1)
    {
        super(a, a0, a1);
        java.awt.CheckboxMenuItem a2 = new java.awt.CheckboxMenuItem("1 Bit");
        this.mnu1Bit = a2;
        java.awt.CheckboxMenuItem a3 = new java.awt.CheckboxMenuItem("2 Bit");
        this.mnu2Bit = a3;
        java.awt.CheckboxMenuItem a4 = new java.awt.CheckboxMenuItem("3 Bit");
        this.mnu3Bit = a4;
        this.buildDate = "Build Date";
        this.buildBy = "Your Initial.Your Surname";
        String s = RadixTrieMultiAnimationWindow.FRAME_TITLE;
        this.frameTitle = s;
        String s0 = com.cim.AIA.AnimationWindow.STEP_LABEL;
        String s1 = com.cim.AIA.AnimationWindow.STEP_INSTRUCTIONS;
        com.cim.AIA.StepButton a5 = new com.cim.AIA.StepButton(s0, s1, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a5);
        String s2 = com.cim.AIA.AnimationWindow.BACK_LABEL;
        String s3 = com.cim.AIA.AnimationWindow.BACK_INSTRUCTIONS;
        com.cim.AIA.BackButton a6 = new com.cim.AIA.BackButton(s2, s3, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a6);
        String s4 = com.cim.AIA.AnimationWindow.RUN_LABEL;
        String s5 = com.cim.AIA.AnimationWindow.RUN_INSTRUCTIONS;
        com.cim.AIA.RunButton a7 = new com.cim.AIA.RunButton(s4, s5, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a7);
        String s6 = com.cim.AIA.AnimationWindow.PAUSE_LABEL;
        String s7 = com.cim.AIA.AnimationWindow.PAUSE_INSTRUCTIONS;
        com.cim.AIA.PauseButton a8 = new com.cim.AIA.PauseButton(s6, s7, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a8);
        String s8 = com.cim.AIA.AnimationWindow.RESET_LABEL;
        String s9 = com.cim.AIA.AnimationWindow.RESET_INSTRUCTIONS;
        com.cim.AIA.ResetButton a9 = new com.cim.AIA.ResetButton(s8, s9, (com.cim.AIA.AnimationWindow)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a9);
        String s10 = localization.Messages.getString("RadixTrieMultiAnimationWindow.1");
        String s11 = localization.Messages.getString("RadixTrieMultiAnimationWindow.2");
        com.cim.AIA.RestartButton a10 = new com.cim.AIA.RestartButton(s10, s11, (com.cim.AIA.AnimationWindow)this, a0);
        a10.setReinitialiseAlgorithm(false);
        a10.setClearAlgorithmState(false);
        a10.setStoreAlgorithmState(true);
        a10.setForceAlgorithmStore(true);
        this.addControlButton((com.cim.AIA.ControlButton)a10);
        String s12 = RadixTrieMultiAnimationWindow.DEFAULT_LABEL;
        int[] a11 = RadixTrieMultiAnimationWindow.DEFAULT_DATA;
        com.cim.AIA.PresetIntArrayDataSelection a12 = new com.cim.AIA.PresetIntArrayDataSelection(s12, true, (com.cim.AIA.AnimationWindow)this, a11);
        a12.setReinitialiseAlgorithm(true);
        a12.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a12);
        String s13 = RadixTrieMultiAnimationWindow.RANDOM_LABEL;
        com.cim.AIA.RandomIntArrayDataSelection a13 = new com.cim.AIA.RandomIntArrayDataSelection(s13, false, (com.cim.AIA.AnimationWindow)this, 5, 20, 1, 99);
        a13.setReinitialiseAlgorithm(true);
        a13.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a13);
        StringBuilder a14 = new StringBuilder();
        String s14 = RadixTrieMultiAnimationWindow.USER_SELECTION_LABEL;
        StringBuilder a15 = a14.append(s14);
        StringBuilder a16 = a15.append("...");
        String s15 = a16.toString();
        String s16 = RadixTrieMultiAnimationWindow.USER_SELECTION_LABEL;
        com.cim.AIA.UserSelectionIntArrayDataSelection a17 = new com.cim.AIA.UserSelectionIntArrayDataSelection(s15, false, (com.cim.AIA.AnimationWindow)this, s16, 1, 99, 5, 20);
        a17.setReinitialiseAlgorithm(true);
        a17.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a17);
        RadixTrieMultiThread dummy = (RadixTrieMultiThread)a0;
        RadixTrieMultiThread a18 = (RadixTrieMultiThread)a0;
        com.cim.AIA.Algorithm a19 = a18.getAlgorithm();
        com.cim.AIA.ControlListener dummy0 = (com.cim.AIA.ControlListener)a19;
        this.addControlListener((com.cim.AIA.ControlListener)a19);
    }
    
    protected void initialiseMenuBar()
    {
        java.awt.MenuBar a = new java.awt.MenuBar();
        this.menuBar = a;
        java.awt.MenuBar a0 = this.menuBar;
        this.setMenuBar(a0);
        this.initialiseAlgorithmMenuItem();
        this.initialiseModeSelectionMenuItem();
        this.initialiseDataSelectionMenuItem();
        this.initialiseDataBitsMenuItem();
        this.initialiseHelpMenuItem();
    }
    
    protected void initialiseDataBitsMenuItem()
    {
        String s = localization.Messages.getString("RadixTrieMultiAnimationWindow.3");
        com.cim.common.RadioMenu a = new com.cim.common.RadioMenu(s, true);
        RadixTrieMulti.mnuLink = a;
        java.awt.CheckboxMenuItem a0 = this.mnu1Bit;
        RadixTrieMultiAnimationWindow$1 a1 = new RadixTrieMultiAnimationWindow$1(this);
        a0.addItemListener((java.awt.event.ItemListener)a1);
        java.awt.CheckboxMenuItem a2 = this.mnu2Bit;
        RadixTrieMultiAnimationWindow$2 a3 = new RadixTrieMultiAnimationWindow$2(this);
        a2.addItemListener((java.awt.event.ItemListener)a3);
        java.awt.CheckboxMenuItem a4 = this.mnu3Bit;
        RadixTrieMultiAnimationWindow$3 a5 = new RadixTrieMultiAnimationWindow$3(this);
        a4.addItemListener((java.awt.event.ItemListener)a5);
        java.awt.CheckboxMenuItem a6 = this.mnu2Bit;
        a6.setState(true);
        com.cim.common.RadioMenu a7 = RadixTrieMulti.mnuLink;
        java.awt.CheckboxMenuItem a8 = this.mnu1Bit;
        java.awt.MenuItem a9 = a7.add((java.awt.MenuItem)a8);
        com.cim.common.RadioMenu a10 = RadixTrieMulti.mnuLink;
        java.awt.CheckboxMenuItem a11 = this.mnu2Bit;
        java.awt.MenuItem a12 = a10.add((java.awt.MenuItem)a11);
        com.cim.common.RadioMenu a13 = RadixTrieMulti.mnuLink;
        java.awt.CheckboxMenuItem a14 = this.mnu3Bit;
        java.awt.MenuItem a15 = a13.add((java.awt.MenuItem)a14);
        java.awt.MenuBar a16 = this.menuBar;
        com.cim.common.RadioMenu a17 = RadixTrieMulti.mnuLink;
        java.awt.Menu a18 = a16.add((java.awt.Menu)a17);
    }
    
    protected void oneBit()
    {
        java.awt.CheckboxMenuItem a = this.mnu2Bit;
        a.setState(false);
        java.awt.CheckboxMenuItem a0 = this.mnu3Bit;
        a0.setState(false);
        this.resetThread(true, true, false, false);
        RadixTrieMulti.NO_OF_NODES = 2;
        RadixTrieMulti.NO_OF_BITS = 1;
        this.resetThread(true, true, false, false);
    }
    
    protected void twoBit()
    {
        java.awt.CheckboxMenuItem a = this.mnu1Bit;
        a.setState(false);
        java.awt.CheckboxMenuItem a0 = this.mnu3Bit;
        a0.setState(false);
        this.resetThread(true, true, false, false);
        RadixTrieMulti.NO_OF_NODES = 4;
        RadixTrieMulti.NO_OF_BITS = 2;
        this.resetThread(true, true, false, false);
    }
    
    protected void threeBit()
    {
        java.awt.CheckboxMenuItem a = this.mnu1Bit;
        a.setState(false);
        java.awt.CheckboxMenuItem a0 = this.mnu2Bit;
        a0.setState(false);
        this.resetThread(true, true, false, false);
        RadixTrieMulti.NO_OF_NODES = 8;
        RadixTrieMulti.NO_OF_BITS = 3;
        this.resetThread(true, true, false, false);
    }
    
    static
    {
        String s = localization.Messages.getString("RadixTrieMultiAnimationWindow.0");
        RadixTrieMultiAnimationWindow.FRAME_TITLE = s;
        int[] a = new int[8];
        a[0] = 1;
        a[1] = 21;
        a[2] = 20;
        a[3] = 16;
        a[4] = 17;
        a[5] = 31;
        a[6] = 30;
        a[7] = 15;
        RadixTrieMultiAnimationWindow.DEFAULT_DATA = a;
    }
}