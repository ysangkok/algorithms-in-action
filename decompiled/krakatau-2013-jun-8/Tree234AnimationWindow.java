public class Tree234AnimationWindow extends com.cim.AIA.AnimationWindow {
    final protected static String BUILD_DATE = "28/05/99";
    final protected static String BUILD_BY = "T.Witham";
    final protected static String FRAME_TITLE;
    final public static int[] DEFAULT_DATA;
    final public static int[] SORTED_DATA;
    final public static int[] REVERSE_SORTED_DATA;
    protected int minimumNumberOfElements;
    protected int maximumNumberOfElements;
    protected int maxSize;
    protected int minSize;
    protected java.awt.Dialog dialog;
    protected Tree234Canvas algorithmCanvas;
    protected com.cim.AIA.HierarchyTree hierarchyTree;
    
    public String getAlgorithmName()
    {
        return "AIA: 234 Tree";
    }
    
    public Tree234AnimationWindow(com.cim.AIA.AlgorithmCanvas a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1)
    {
        super(a, a0, a1);
        this.minimumNumberOfElements = 1;
        this.maximumNumberOfElements = 20;
        this.maxSize = 99;
        this.minSize = 0;
        this.buildDate = "28/05/99";
        this.buildBy = "T.Witham";
        String s = Tree234AnimationWindow.FRAME_TITLE;
        this.frameTitle = s;
        com.cim.AIA.SelfTestModeSelection a2 = this.selfTestMode;
        a2.setEnabled(false);
        String s0 = com.cim.AIA.AnimationWindow.STEP_LABEL;
        String s1 = com.cim.AIA.AnimationWindow.STEP_INSTRUCTIONS;
        com.cim.AIA.StepButton a3 = new com.cim.AIA.StepButton(s0, s1, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a3);
        String s2 = com.cim.AIA.AnimationWindow.BACK_LABEL;
        String s3 = com.cim.AIA.AnimationWindow.BACK_INSTRUCTIONS;
        com.cim.AIA.BackButton a4 = new com.cim.AIA.BackButton(s2, s3, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a4);
        String s4 = com.cim.AIA.AnimationWindow.RUN_LABEL;
        String s5 = com.cim.AIA.AnimationWindow.RUN_INSTRUCTIONS;
        com.cim.AIA.RunButton a5 = new com.cim.AIA.RunButton(s4, s5, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a5);
        String s6 = com.cim.AIA.AnimationWindow.PAUSE_LABEL;
        String s7 = com.cim.AIA.AnimationWindow.PAUSE_INSTRUCTIONS;
        com.cim.AIA.PauseButton a6 = new com.cim.AIA.PauseButton(s6, s7, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a6);
        String s8 = com.cim.AIA.AnimationWindow.RESET_LABEL;
        String s9 = com.cim.AIA.AnimationWindow.RESET_INSTRUCTIONS;
        com.cim.AIA.ResetButton a7 = new com.cim.AIA.ResetButton(s8, s9, (com.cim.AIA.AnimationWindow)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a7);
        String s10 = localization.Messages.getString("Tree234AnimationWindow.3");
        String s11 = localization.Messages.getString("Tree234AnimationWindow.4");
        com.cim.AIA.RestartButton a8 = new com.cim.AIA.RestartButton(s10, s11, (com.cim.AIA.AnimationWindow)this, a0);
        a8.setReinitialiseAlgorithm(false);
        a8.setClearAlgorithmState(false);
        a8.setStoreAlgorithmState(true);
        a8.setForceAlgorithmStore(true);
        this.addControlButton((com.cim.AIA.ControlButton)a8);
        String s12 = Tree234AnimationWindow.DEFAULT_LABEL;
        int[] a9 = Tree234AnimationWindow.DEFAULT_DATA;
        com.cim.AIA.PresetIntArrayDataSelection a10 = new com.cim.AIA.PresetIntArrayDataSelection(s12, true, (com.cim.AIA.AnimationWindow)this, a9);
        a10.setReinitialiseAlgorithm(true);
        a10.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a10);
        String s13 = Tree234AnimationWindow.SORTED_LABEL;
        int[] a11 = Tree234AnimationWindow.SORTED_DATA;
        com.cim.AIA.PresetIntArrayDataSelection a12 = new com.cim.AIA.PresetIntArrayDataSelection(s13, false, (com.cim.AIA.AnimationWindow)this, a11);
        a12.setReinitialiseAlgorithm(true);
        a12.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a12);
        String s14 = Tree234AnimationWindow.REVERSE_SORTED_LABEL;
        int[] a13 = Tree234AnimationWindow.REVERSE_SORTED_DATA;
        com.cim.AIA.PresetIntArrayDataSelection a14 = new com.cim.AIA.PresetIntArrayDataSelection(s14, false, (com.cim.AIA.AnimationWindow)this, a13);
        a14.setReinitialiseAlgorithm(true);
        a14.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a14);
        String s15 = Tree234AnimationWindow.SAME_LABEL;
        int i = this.minimumNumberOfElements;
        int i0 = this.maximumNumberOfElements;
        int i1 = this.minSize;
        int i2 = this.maxSize;
        com.cim.AIA.RandomSameIntArrayDataSelection a15 = new com.cim.AIA.RandomSameIntArrayDataSelection(s15, false, (com.cim.AIA.AnimationWindow)this, i, i0, i1, i2);
        a15.setReinitialiseAlgorithm(true);
        a15.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a15);
        String s16 = Tree234AnimationWindow.RANDOM_LABEL;
        int i3 = this.minimumNumberOfElements;
        int i4 = this.maximumNumberOfElements;
        int i5 = this.minSize;
        int i6 = this.maxSize;
        com.cim.AIA.RandomIntArrayDataSelection a16 = new com.cim.AIA.RandomIntArrayDataSelection(s16, false, (com.cim.AIA.AnimationWindow)this, i3, i4, i5, i6);
        a16.setReinitialiseAlgorithm(true);
        a16.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a16);
        StringBuilder a17 = new StringBuilder();
        String s17 = Tree234AnimationWindow.USER_SELECTION_LABEL;
        StringBuilder a18 = a17.append(s17);
        StringBuilder a19 = a18.append("...");
        String s18 = a19.toString();
        String s19 = Tree234AnimationWindow.USER_SELECTION_LABEL;
        int i7 = this.minSize;
        int i8 = this.maxSize;
        int i9 = this.minimumNumberOfElements;
        int i10 = this.maximumNumberOfElements;
        com.cim.AIA.UserSelectionIntArrayDataSelection a20 = new com.cim.AIA.UserSelectionIntArrayDataSelection(s18, false, (com.cim.AIA.AnimationWindow)this, s19, i7, i8, i9, i10);
        a20.setReinitialiseAlgorithm(true);
        a20.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a20);
        Tree234Thread dummy = (Tree234Thread)a0;
        Tree234Thread a21 = (Tree234Thread)a0;
        com.cim.AIA.Algorithm a22 = a21.getAlgorithm();
        com.cim.AIA.ControlListener dummy0 = (com.cim.AIA.ControlListener)a22;
        this.addControlListener((com.cim.AIA.ControlListener)a22);
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
        this.initialiseViewMenuItem();
        this.initialiseHelpMenuItem();
    }
    
    protected void initialiseViewMenuItem()
    {
        String s = localization.Messages.getString("Tree234AnimationWindow.6");
        java.awt.Menu a = new java.awt.Menu(s);
        String s0 = localization.Messages.getString("Tree234AnimationWindow.7");
        java.awt.MenuItem a0 = new java.awt.MenuItem(s0);
        Tree234AnimationWindow$1 a1 = new Tree234AnimationWindow$1(this);
        a0.addActionListener((java.awt.event.ActionListener)a1);
        java.awt.MenuItem a2 = a.add(a0);
        java.awt.MenuBar a3 = this.menuBar;
        java.awt.Menu a4 = a3.add(a);
    }
    
    protected void showAs234Tree()
    {
        com.cim.AIA.Algorithm a = this.getAlgorithm();
        Tree234Tree dummy = (Tree234Tree)a;
        Tree234Tree a0 = (Tree234Tree)a;
        label0: {
            if(a0 == null)
            {
                break label0;
            }
            java.awt.Dialog a1 = this.dialog;
            if(a1 == null)
            {
                java.awt.Frame a2 = new java.awt.Frame();
                String s = localization.Messages.getString("Tree234AnimationWindow.8");
                java.awt.Dialog a3 = new java.awt.Dialog(a2, s, false);
                this.dialog = a3;
                java.awt.Dialog a4 = this.dialog;
                a4.setSize(400, 400);
                Tree234Canvas a5 = new Tree234Canvas();
                this.algorithmCanvas = a5;
                Tree234Canvas a6 = this.algorithmCanvas;
                a6.setShowAs234Only(false);
                java.awt.ScrollPane a7 = new java.awt.ScrollPane();
                Tree234Canvas a8 = this.algorithmCanvas;
                a8.setParent((java.awt.Container)a7);
                Tree234Canvas a9 = this.algorithmCanvas;
                java.awt.Component a10 = a7.add((java.awt.Component)a9);
                java.awt.Dialog a11 = this.dialog;
                java.awt.Component a12 = a11.add((java.awt.Component)a7);
                java.awt.Dialog a13 = this.dialog;
                Tree234AnimationWindow$2 a14 = new Tree234AnimationWindow$2(this);
                a13.addWindowListener((java.awt.event.WindowListener)a14);
                com.cim.AIA.AlgorithmThread a15 = this.thread;
                Tree234Canvas a16 = this.algorithmCanvas;
                a15.addRepaintListener((com.cim.AIA.RepaintListener)a16);
                com.cim.AIA.AlgorithmThread a17 = this.thread;
                a17.repaint();
            }
            java.awt.Dialog a18 = this.dialog;
            a18.setVisible(true);
        }
    }
    
    static
    {
        String s = localization.Messages.getString("Tree234AnimationWindow.2");
        Tree234AnimationWindow.FRAME_TITLE = s;
        int[] a = new int[15];
        a[0] = 75;
        a[1] = 5;
        a[2] = 70;
        a[3] = 10;
        a[4] = 65;
        a[5] = 15;
        a[6] = 60;
        a[7] = 20;
        a[8] = 55;
        a[9] = 25;
        a[10] = 50;
        a[11] = 30;
        a[12] = 45;
        a[13] = 35;
        a[14] = 40;
        Tree234AnimationWindow.DEFAULT_DATA = a;
        int[] a0 = new int[15];
        a0[0] = 5;
        a0[1] = 10;
        a0[2] = 15;
        a0[3] = 20;
        a0[4] = 25;
        a0[5] = 30;
        a0[6] = 35;
        a0[7] = 40;
        a0[8] = 45;
        a0[9] = 50;
        a0[10] = 55;
        a0[11] = 60;
        a0[12] = 65;
        a0[13] = 70;
        a0[14] = 75;
        Tree234AnimationWindow.SORTED_DATA = a0;
        int[] a1 = new int[15];
        a1[0] = 75;
        a1[1] = 70;
        a1[2] = 65;
        a1[3] = 60;
        a1[4] = 55;
        a1[5] = 50;
        a1[6] = 45;
        a1[7] = 40;
        a1[8] = 35;
        a1[9] = 30;
        a1[10] = 25;
        a1[11] = 20;
        a1[12] = 15;
        a1[13] = 10;
        a1[14] = 5;
        Tree234AnimationWindow.REVERSE_SORTED_DATA = a1;
    }
}