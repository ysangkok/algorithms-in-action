public class KMPAnimationWindow extends com.cim.AIA.AnimationWindow {
    final private static long serialVersionUID = 717328955772392850L;
    final protected static String FRAME_TITLE;
    final protected static String VARIATION;
    final protected static String FIRST;
    final protected static String SECOND;
    final public static int MINIMUM_NUMBER_OF_ELEMENTS = 5;
    final public static int MAXIMUM_NUMBER_OF_ELEMENTS = 5;
    final public static int MAX_SIZE = 4;
    final public static int MIN_SIZE = 0;
    final public static String[] DEFAULT_DATA_1;
    final public static String[] DEFAULT_DATA_2;
    final public static String[] DEFAULT_DATA_3;
    final public static int MAX_LENGTH = 12;
    final public static int MIN_LENGTH = 3;
    protected com.cim.AIA.PresetStringArrayDataSelection ds1;
    protected com.cim.AIA.PresetStringArrayDataSelection ds2;
    protected com.cim.AIA.PresetStringArrayDataSelection ds3;
    protected java.util.Vector variationMethods;
    
    public String getAlgorithmName()
    {
        return "KMP String Search";
    }
    
    public KMPAnimationWindow(com.cim.AIA.AlgorithmCanvas a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1)
    {
        super(a, a0, a1);
        java.util.Vector a2 = new java.util.Vector();
        this.variationMethods = a2;
        String s = KMPAnimationWindow.FRAME_TITLE;
        this.frameTitle = s;
        String s0 = localization.Messages.getString("KMPAnimationWindow.10");
        String[] a3 = KMPAnimationWindow.DEFAULT_DATA_1;
        com.cim.AIA.PresetStringArrayDataSelection a4 = new com.cim.AIA.PresetStringArrayDataSelection(s0, true, (com.cim.AIA.AnimationWindow)this, a3);
        this.ds1 = a4;
        String s1 = localization.Messages.getString("KMPAnimationWindow.11");
        String[] a5 = KMPAnimationWindow.DEFAULT_DATA_2;
        com.cim.AIA.PresetStringArrayDataSelection a6 = new com.cim.AIA.PresetStringArrayDataSelection(s1, false, (com.cim.AIA.AnimationWindow)this, a5);
        this.ds2 = a6;
        String s2 = localization.Messages.getString("KMPAnimationWindow.12");
        String[] a7 = KMPAnimationWindow.DEFAULT_DATA_3;
        com.cim.AIA.PresetStringArrayDataSelection a8 = new com.cim.AIA.PresetStringArrayDataSelection(s2, false, (com.cim.AIA.AnimationWindow)this, a7);
        this.ds3 = a8;
        com.cim.AIA.PresetStringArrayDataSelection a9 = this.ds1;
        this.addDataSelection((com.cim.AIA.DataSelection)a9);
        com.cim.AIA.PresetStringArrayDataSelection a10 = this.ds2;
        this.addDataSelection((com.cim.AIA.DataSelection)a10);
        com.cim.AIA.PresetStringArrayDataSelection a11 = this.ds3;
        this.addDataSelection((com.cim.AIA.DataSelection)a11);
        StringBuilder a12 = new StringBuilder();
        String s3 = KMPAnimationWindow.USER_SELECTION_LABEL;
        StringBuilder a13 = a12.append(s3);
        StringBuilder a14 = a13.append("...");
        String s4 = a14.toString();
        String s5 = KMPAnimationWindow.USER_SELECTION_LABEL;
        String s6 = localization.Messages.getString("KMPAnimationWindow.14");
        String s7 = localization.Messages.getString("KMPAnimationWindow.15");
        com.cim.AIA.UserSelectionStringDataSelection a15 = new com.cim.AIA.UserSelectionStringDataSelection(s4, false, (com.cim.AIA.AnimationWindow)this, s5, 3, 12, s6, s7);
        this.addDataSelection((com.cim.AIA.DataSelection)a15);
        String s8 = com.cim.AIA.AnimationWindow.STEP_LABEL;
        String s9 = com.cim.AIA.AnimationWindow.STEP_INSTRUCTIONS;
        com.cim.AIA.StepButton a16 = new com.cim.AIA.StepButton(s8, s9, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a16);
        String s10 = com.cim.AIA.AnimationWindow.BACK_LABEL;
        String s11 = com.cim.AIA.AnimationWindow.BACK_INSTRUCTIONS;
        com.cim.AIA.BackButton a17 = new com.cim.AIA.BackButton(s10, s11, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a17);
        String s12 = com.cim.AIA.AnimationWindow.RUN_LABEL;
        String s13 = com.cim.AIA.AnimationWindow.RUN_INSTRUCTIONS;
        com.cim.AIA.RunButton a18 = new com.cim.AIA.RunButton(s12, s13, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a18);
        String s14 = com.cim.AIA.AnimationWindow.PAUSE_LABEL;
        String s15 = com.cim.AIA.AnimationWindow.PAUSE_LABEL;
        com.cim.AIA.PauseButton a19 = new com.cim.AIA.PauseButton(s14, s15, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a19);
        String s16 = com.cim.AIA.AnimationWindow.RESET_LABEL;
        String s17 = com.cim.AIA.AnimationWindow.RESET_INSTRUCTIONS;
        com.cim.AIA.ResetButton a20 = new com.cim.AIA.ResetButton(s16, s17, (com.cim.AIA.AnimationWindow)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a20);
        com.cim.AIA.SelfTestModeSelection a21 = this.selfTestMode;
        a21.setEnabled(false);
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
        this.initialiseVariationMenuItem();
        this.initialiseHelpMenuItem();
    }
    
    protected void initialiseVariationMenuItem()
    {
        String s = KMPAnimationWindow.VARIATION;
        com.cim.common.RadioMenu a = new com.cim.common.RadioMenu(s);
        com.cim.AIA.Algorithm a0 = this.getAlgorithm();
        KMP dummy = (KMP)a0;
        KMP a1 = (KMP)a0;
        java.util.Vector a2 = this.variationMethods;
        String s0 = KMPAnimationWindow.FIRST;
        java.applet.Applet a3 = this.applet;
        java.net.URL a4 = a3.getCodeBase();
        String s1 = a4.toString();
        String s2 = KMPApplet.FIRST_FILE_NAME;
        com.cim.AIA.AlgorithmWindow a5 = this.getAlgorithmWindow();
        KMPAnimationWindow$VariationMethod a6 = new KMPAnimationWindow$VariationMethod(this, s0, true, a1, s1, 1, s2, this, a5);
        a2.addElement((Object)a6);
        java.util.Vector a7 = this.variationMethods;
        String s3 = KMPAnimationWindow.SECOND;
        java.applet.Applet a8 = this.applet;
        java.net.URL a9 = a8.getCodeBase();
        String s4 = a9.toString();
        String s5 = KMPApplet.SECOND_FILE_NAME;
        com.cim.AIA.AlgorithmWindow a10 = this.getAlgorithmWindow();
        KMPAnimationWindow$VariationMethod a11 = new KMPAnimationWindow$VariationMethod(this, s3, false, a1, s4, 2, s5, this, a10);
        a7.addElement((Object)a11);
        int i = 0;
        while(true)
        {
            java.util.Vector a12 = this.variationMethods;
            int i0 = a12.size();
            if(i >= i0)
            {
                java.awt.MenuBar a13 = this.menuBar;
                java.awt.Menu a14 = a13.add((java.awt.Menu)a);
                return;
            }
            else
            {
                java.util.Vector a15 = this.variationMethods;
                Object a16 = a15.elementAt(i);
                java.awt.MenuItem dummy0 = (java.awt.MenuItem)a16;
                java.awt.MenuItem a17 = (java.awt.MenuItem)a16;
                java.awt.MenuItem a18 = a.add(a17);
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    public void resetVariationButtons()
    {
        int i = 0;
        while(true)
        {
            java.util.Vector a = this.variationMethods;
            int i0 = a.size();
            if(i >= i0)
            {
                return;
            }
            else
            {
                java.util.Vector a0 = this.variationMethods;
                Object a1 = a0.elementAt(i);
                KMPAnimationWindow$VariationMethod dummy = (KMPAnimationWindow$VariationMethod)a1;
                KMPAnimationWindow$VariationMethod a2 = (KMPAnimationWindow$VariationMethod)a1;
                a2.setState(false);
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    static
    {
        String s = localization.Messages.getString("KMPAnimationWindow.0");
        KMPAnimationWindow.FRAME_TITLE = s;
        String s0 = localization.Messages.getString("KMPAnimationWindow.1");
        KMPAnimationWindow.VARIATION = s0;
        String s1 = localization.Messages.getString("KMPAnimationWindow.2");
        KMPAnimationWindow.FIRST = s1;
        String s2 = localization.Messages.getString("KMPAnimationWindow.3");
        KMPAnimationWindow.SECOND = s2;
        String[] a = new String[2];
        String s3 = localization.Messages.getString("KMPAnimationWindow.4");
        a[0] = s3;
        String s4 = localization.Messages.getString("KMPAnimationWindow.5");
        a[1] = s4;
        KMPAnimationWindow.DEFAULT_DATA_1 = a;
        String[] a0 = new String[2];
        String s5 = localization.Messages.getString("KMPAnimationWindow.6");
        a0[0] = s5;
        String s6 = localization.Messages.getString("KMPAnimationWindow.7");
        a0[1] = s6;
        KMPAnimationWindow.DEFAULT_DATA_2 = a0;
        String[] a1 = new String[2];
        String s7 = localization.Messages.getString("KMPAnimationWindow.8");
        a1[0] = s7;
        String s8 = localization.Messages.getString("KMPAnimationWindow.9");
        a1[1] = s8;
        KMPAnimationWindow.DEFAULT_DATA_3 = a1;
    }
}