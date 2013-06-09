public class AlignmentAnimationWindow extends com.cim.AIA.AnimationWindow {
    final private static long serialVersionUID = 9033252273678406227L;
    final protected static String FRAME_TITLE;
    final protected static String VARIATION;
    final protected static String FIRST;
    final protected static String SECOND;
    final public static String[] GLOBAL_DIST_NOGAP_DATA;
    final public static String[] GLOBAL_DIST_GAP_DATA;
    final public static String[] GLOBAL_SIM_NOGAP_DATA;
    final public static String[] GLOBAL_SIM_GAP_DATA;
    final public static String[] LOCAL_SIM_NOGAP_DATA;
    final public static String[] LOCAL_SIM_GAP_DATA;
    final public static String[] GAP_DEFAULT_DATA;
    final public static String[] GLOBAL_DEFAULT_DATA;
    final public static String[] LOCAL_DEFAULT_DATA;
    final public static int MAX_LENGTH = 9;
    final public static int MIN_LENGTH = 3;
    protected com.cim.AIA.PresetStringArrayDataSelection ds1;
    protected com.cim.AIA.PresetStringArrayDataSelection ds2;
    protected com.cim.AIA.PresetStringArrayDataSelection ds3;
    protected com.cim.AIA.PresetStringArrayDataSelection ds4;
    protected com.cim.AIA.PresetStringArrayDataSelection ds5;
    protected com.cim.AIA.PresetStringArrayDataSelection ds6;
    protected java.util.Vector variationMethods;
    
    public String getAlgorithmName()
    {
        return "Introduction to Alignment";
    }
    
    public AlignmentAnimationWindow(com.cim.AIA.AlgorithmCanvas a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1)
    {
        super(a, a0, a1);
        java.util.Vector a2 = new java.util.Vector();
        this.variationMethods = a2;
        String s = AlignmentAnimationWindow.FRAME_TITLE;
        this.frameTitle = s;
        String s0 = localization.Messages.getString("AlignmentAnimationWindow.22");
        int i = Alignment.runningMode;
        int i0 = i != 1?0:1;
        String[] a3 = AlignmentAnimationWindow.GLOBAL_DIST_NOGAP_DATA;
        com.cim.AIA.PresetStringArrayDataSelection a4 = new com.cim.AIA.PresetStringArrayDataSelection(s0, i0 != 0, (com.cim.AIA.AnimationWindow)this, a3);
        this.ds1 = a4;
        String s1 = localization.Messages.getString("AlignmentAnimationWindow.23");
        String[] a5 = AlignmentAnimationWindow.GLOBAL_DIST_GAP_DATA;
        com.cim.AIA.PresetStringArrayDataSelection a6 = new com.cim.AIA.PresetStringArrayDataSelection(s1, false, (com.cim.AIA.AnimationWindow)this, a5);
        this.ds2 = a6;
        String s2 = localization.Messages.getString("AlignmentAnimationWindow.24");
        int i1 = Alignment.runningMode;
        int i2 = i1 != 2?0:1;
        String[] a7 = AlignmentAnimationWindow.GLOBAL_SIM_NOGAP_DATA;
        com.cim.AIA.PresetStringArrayDataSelection a8 = new com.cim.AIA.PresetStringArrayDataSelection(s2, i2 != 0, (com.cim.AIA.AnimationWindow)this, a7);
        this.ds3 = a8;
        String s3 = localization.Messages.getString("AlignmentAnimationWindow.25");
        String[] a9 = AlignmentAnimationWindow.GLOBAL_SIM_GAP_DATA;
        com.cim.AIA.PresetStringArrayDataSelection a10 = new com.cim.AIA.PresetStringArrayDataSelection(s3, false, (com.cim.AIA.AnimationWindow)this, a9);
        this.ds4 = a10;
        String s4 = localization.Messages.getString("AlignmentAnimationWindow.26");
        int i3 = Alignment.runningMode;
        int i4 = i3 != 3?0:1;
        String[] a11 = AlignmentAnimationWindow.LOCAL_SIM_NOGAP_DATA;
        com.cim.AIA.PresetStringArrayDataSelection a12 = new com.cim.AIA.PresetStringArrayDataSelection(s4, i4 != 0, (com.cim.AIA.AnimationWindow)this, a11);
        this.ds5 = a12;
        String s5 = localization.Messages.getString("AlignmentAnimationWindow.27");
        String[] a13 = AlignmentAnimationWindow.LOCAL_SIM_GAP_DATA;
        com.cim.AIA.PresetStringArrayDataSelection a14 = new com.cim.AIA.PresetStringArrayDataSelection(s5, false, (com.cim.AIA.AnimationWindow)this, a13);
        this.ds6 = a14;
        com.cim.AIA.PresetStringArrayDataSelection a15 = this.ds1;
        this.addDataSelection((com.cim.AIA.DataSelection)a15);
        com.cim.AIA.PresetStringArrayDataSelection a16 = this.ds2;
        this.addDataSelection((com.cim.AIA.DataSelection)a16);
        com.cim.AIA.PresetStringArrayDataSelection a17 = this.ds3;
        this.addDataSelection((com.cim.AIA.DataSelection)a17);
        com.cim.AIA.PresetStringArrayDataSelection a18 = this.ds4;
        this.addDataSelection((com.cim.AIA.DataSelection)a18);
        com.cim.AIA.PresetStringArrayDataSelection a19 = this.ds5;
        this.addDataSelection((com.cim.AIA.DataSelection)a19);
        com.cim.AIA.PresetStringArrayDataSelection a20 = this.ds6;
        this.addDataSelection((com.cim.AIA.DataSelection)a20);
        StringBuilder a21 = new StringBuilder();
        String s6 = AlignmentAnimationWindow.USER_SELECTION_LABEL;
        StringBuilder a22 = a21.append(s6);
        StringBuilder a23 = a22.append("...");
        String s7 = a23.toString();
        String s8 = AlignmentAnimationWindow.USER_SELECTION_LABEL;
        com.cim.AIA.UserSelectionStringDataSelection a24 = new com.cim.AIA.UserSelectionStringDataSelection(s7, false, (com.cim.AIA.AnimationWindow)this, s8, 3, 9);
        this.addDataSelection((com.cim.AIA.DataSelection)a24);
        String s9 = com.cim.AIA.AnimationWindow.STEP_LABEL;
        String s10 = com.cim.AIA.AnimationWindow.STEP_INSTRUCTIONS;
        com.cim.AIA.StepButton a25 = new com.cim.AIA.StepButton(s9, s10, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a25);
        String s11 = com.cim.AIA.AnimationWindow.BACK_LABEL;
        String s12 = com.cim.AIA.AnimationWindow.BACK_INSTRUCTIONS;
        com.cim.AIA.BackButton a26 = new com.cim.AIA.BackButton(s11, s12, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a26);
        String s13 = com.cim.AIA.AnimationWindow.RUN_LABEL;
        String s14 = com.cim.AIA.AnimationWindow.RUN_INSTRUCTIONS;
        com.cim.AIA.RunButton a27 = new com.cim.AIA.RunButton(s13, s14, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a27);
        String s15 = com.cim.AIA.AnimationWindow.PAUSE_LABEL;
        String s16 = com.cim.AIA.AnimationWindow.PAUSE_LABEL;
        com.cim.AIA.PauseButton a28 = new com.cim.AIA.PauseButton(s15, s16, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a28);
        String s17 = com.cim.AIA.AnimationWindow.RESET_LABEL;
        String s18 = com.cim.AIA.AnimationWindow.RESET_INSTRUCTIONS;
        com.cim.AIA.ResetButton a29 = new com.cim.AIA.ResetButton(s17, s18, (com.cim.AIA.AnimationWindow)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a29);
        com.cim.AIA.SelfTestModeSelection a30 = this.selfTestMode;
        a30.setEnabled(false);
    }
    
    protected void edit3D()
    {
        com.cim.AIA.Algorithm a = this.getAlgorithm();
        Alignment dummy = (Alignment)a;
        Alignment a0 = (Alignment)a;
        Alignment3DEditDialog a1 = new Alignment3DEditDialog("Rotation parameters", a0);
        a1.setVisible(true);
    }
    
    protected void editCost()
    {
        com.cim.AIA.Algorithm a = this.getAlgorithm();
        Alignment dummy = (Alignment)a;
        Alignment a0 = (Alignment)a;
        String[] a1 = new String[5];
        StringBuilder a2 = new StringBuilder();
        int i = a0.getMatchCost();
        StringBuilder a3 = a2.append(i);
        StringBuilder a4 = a3.append("");
        String s = a4.toString();
        a1[0] = s;
        StringBuilder a5 = new StringBuilder();
        int i0 = a0.getMismatchCost();
        StringBuilder a6 = a5.append(i0);
        StringBuilder a7 = a6.append("");
        String s0 = a7.toString();
        a1[1] = s0;
        StringBuilder a8 = new StringBuilder();
        int i1 = a0.getSpaceCost();
        StringBuilder a9 = a8.append(i1);
        StringBuilder a10 = a9.append("");
        String s1 = a10.toString();
        a1[2] = s1;
        StringBuilder a11 = new StringBuilder();
        int i2 = a0.getInitialGapCost();
        StringBuilder a12 = a11.append(i2);
        StringBuilder a13 = a12.append("");
        String s2 = a13.toString();
        a1[3] = s2;
        StringBuilder a14 = new StringBuilder();
        int i3 = a0.getRunningGapCost();
        StringBuilder a15 = a14.append(i3);
        StringBuilder a16 = a15.append("");
        String s3 = a16.toString();
        a1[4] = s3;
        String s4 = AlignmentCostDialog.DEFAULT_TITLE;
        int i4 = Alignment.currentVariation;
        AlignmentCostDialog a17 = new AlignmentCostDialog(s4, a1, -10, 10, i4);
        a17.setVisible(true);
        int[] a18 = a17.getData();
        int i5 = a18.length;
        if(i5 == 5)
        {
            int i6 = a18[0];
            a0.setMatchCost(i6);
            int i7 = a18[1];
            a0.setMismatchCost(i7);
            int i8 = a18[2];
            a0.setSpaceCost(i8);
            int i9 = a18[3];
            a0.setInitialGapCost(i9);
            int i10 = a18[4];
            a0.setRunningGapCost(i10);
            this.resetThread(true, true, false, false);
        }
        StringBuffer a19 = new StringBuffer();
        StringBuffer a20 = a19.append("[");
        int i11 = 0;
        while(true)
        {
            int i12 = a18.length;
            if(i11 >= i12)
            {
                break;
            }
            if(i11 > 0)
            {
                StringBuffer a21 = a19.append(",");
            }
            int i13 = a18[i11];
            StringBuffer a22 = a19.append(i13);
            int i14 = i11 + 1;
            i11 = i14;
        }
        StringBuffer a23 = a19.append("]");
        String s5 = a19.toString();
        com.cim.AIA.Log a24 = new com.cim.AIA.Log((byte)4, (byte)22, (String)null, s5);
        com.cim.AIA.Logger a25 = this.getLogger();
        if(a25 != null)
        {
            com.cim.AIA.Logger a26 = this.getLogger();
            a26.addLog(a24);
        }
    }
    
    protected void initialise3DMenuItem()
    {
        String s = localization.Messages.getString("AlignmentAnimationWindow.39");
        java.awt.Menu a = new java.awt.Menu(s);
        java.awt.MenuItem a0 = new java.awt.MenuItem("Edit...");
        AlignmentAnimationWindow$1 a1 = new AlignmentAnimationWindow$1(this);
        a.addActionListener((java.awt.event.ActionListener)a1);
        java.awt.MenuItem a2 = a.add(a0);
        java.awt.MenuBar a3 = this.menuBar;
        java.awt.Menu a4 = a3.add(a);
    }
    
    protected void initialiseCostMenuItem()
    {
        String s = localization.Messages.getString("AlignmentAnimationWindow.29");
        java.awt.Menu a = new java.awt.Menu(s);
        String s0 = localization.Messages.getString("AlignmentAnimationWindow.30");
        java.awt.MenuItem a0 = new java.awt.MenuItem(s0);
        AlignmentAnimationWindow$2 a1 = new AlignmentAnimationWindow$2(this);
        a0.addActionListener((java.awt.event.ActionListener)a1);
        java.awt.MenuItem a2 = a.add(a0);
        java.awt.MenuBar a3 = this.menuBar;
        java.awt.Menu a4 = a3.add(a);
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
        this.initialiseCostMenuItem();
        this.initialiseHelpMenuItem();
    }
    
    protected void initialiseVariationMenuItem()
    {
        String s = AlignmentAnimationWindow.VARIATION;
        com.cim.common.RadioMenu a = new com.cim.common.RadioMenu(s, true);
        com.cim.AIA.Algorithm a0 = this.getAlgorithm();
        Alignment dummy = (Alignment)a0;
        Alignment a1 = (Alignment)a0;
        java.util.Vector a2 = this.variationMethods;
        String s0 = AlignmentAnimationWindow.FIRST;
        java.applet.Applet a3 = this.applet;
        java.net.URL a4 = a3.getCodeBase();
        String s1 = a4.toString();
        String s2 = AlignmentApplet.FIRST_FILE_NAME;
        String s3 = AlignmentApplet.FIRST_EXPLANATION_NAME;
        com.cim.AIA.AlgorithmWindow a5 = this.getAlgorithmWindow();
        AlignmentAnimationWindow$VariationMethod a6 = new AlignmentAnimationWindow$VariationMethod(this, s0, true, a1, s1, 1, s2, s3, this, a5);
        a2.addElement((Object)a6);
        int i = Alignment.runningMode;
        if(i != 3)
        {
            java.util.Vector a7 = this.variationMethods;
            String s4 = AlignmentAnimationWindow.SECOND;
            java.applet.Applet a8 = this.applet;
            java.net.URL a9 = a8.getCodeBase();
            String s5 = a9.toString();
            String s6 = AlignmentApplet.SECOND_FILE_NAME;
            String s7 = AlignmentApplet.SECOND_EXPLANATION_NAME;
            com.cim.AIA.AlgorithmWindow a10 = this.getAlgorithmWindow();
            AlignmentAnimationWindow$VariationMethod a11 = new AlignmentAnimationWindow$VariationMethod(this, s4, false, a1, s5, 2, s6, s7, this, a10);
            a7.addElement((Object)a11);
        }
        int i0 = 0;
        while(true)
        {
            java.util.Vector a12 = this.variationMethods;
            int i1 = a12.size();
            if(i0 >= i1)
            {
                java.awt.MenuBar a13 = this.menuBar;
                java.awt.Menu a14 = a13.add((java.awt.Menu)a);
                return;
            }
            else
            {
                java.util.Vector a15 = this.variationMethods;
                Object a16 = a15.elementAt(i0);
                java.awt.MenuItem dummy0 = (java.awt.MenuItem)a16;
                java.awt.MenuItem a17 = (java.awt.MenuItem)a16;
                java.awt.MenuItem a18 = a.add(a17);
                int i2 = i0 + 1;
                i0 = i2;
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
                AlignmentAnimationWindow$VariationMethod dummy = (AlignmentAnimationWindow$VariationMethod)a1;
                AlignmentAnimationWindow$VariationMethod a2 = (AlignmentAnimationWindow$VariationMethod)a1;
                a2.setState(false);
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    static
    {
        String s = localization.Messages.getString("AlignmentAnimationWindow.0");
        AlignmentAnimationWindow.FRAME_TITLE = s;
        String s0 = localization.Messages.getString("AlignmentAnimationWindow.1");
        AlignmentAnimationWindow.VARIATION = s0;
        String s1 = localization.Messages.getString("AlignmentAnimationWindow.2");
        AlignmentAnimationWindow.FIRST = s1;
        String s2 = localization.Messages.getString("AlignmentAnimationWindow.3");
        AlignmentAnimationWindow.SECOND = s2;
        String[] a = new String[2];
        a[0] = "writers";
        a[1] = "vintner";
        AlignmentAnimationWindow.GLOBAL_DIST_NOGAP_DATA = a;
        String[] a0 = new String[2];
        a0[0] = "abcqdew";
        a0[1] = "zxvcdel";
        AlignmentAnimationWindow.GLOBAL_DIST_GAP_DATA = a0;
        String[] a1 = new String[2];
        a1[0] = "writers";
        a1[1] = "vintner";
        AlignmentAnimationWindow.GLOBAL_SIM_NOGAP_DATA = a1;
        String[] a2 = new String[2];
        a2[0] = "abcqdew";
        a2[1] = "zxvcdel";
        AlignmentAnimationWindow.GLOBAL_SIM_GAP_DATA = a2;
        String[] a3 = new String[2];
        a3[0] = "abcxdex";
        a3[1] = "zzzcdey";
        AlignmentAnimationWindow.LOCAL_SIM_NOGAP_DATA = a3;
        String[] a4 = new String[2];
        a4[0] = "praxabcst";
        a4[1] = "xyaxbacsl";
        AlignmentAnimationWindow.LOCAL_SIM_GAP_DATA = a4;
        String[] a5 = new String[2];
        a5[0] = "abcqdew";
        a5[1] = "zxvcdel";
        AlignmentAnimationWindow.GAP_DEFAULT_DATA = a5;
        String[] a6 = new String[2];
        a6[0] = "writers";
        a6[1] = "vintner";
        AlignmentAnimationWindow.GLOBAL_DEFAULT_DATA = a6;
        String[] a7 = new String[2];
        a7[0] = "abcxdex";
        a7[1] = "yyycdey";
        AlignmentAnimationWindow.LOCAL_DEFAULT_DATA = a7;
    }
}