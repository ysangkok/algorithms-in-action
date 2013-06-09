public class RedBlackTreeAnimationWindow extends com.cim.AIA.AnimationWindow implements com.cim.AIA.ControlListener {
    final private static long serialVersionUID = 4700934169171786038L;
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
    private boolean everStarted;
    private boolean isRunning;
    private int maxDuration;
    protected java.awt.Dialog tuteDialog;
    protected RedBlackTreeCanvas tuteCanvas;
    protected com.cim.AIA.HierarchyTree tuteTree;
    protected java.awt.Panel panel;
    protected RedBlackTreeCanvas codeCanvas;
    protected java.awt.Panel controlPanel;
    protected java.awt.Panel speedPanel;
    protected java.awt.Scrollbar speedBar;
    java.awt.Button rotateLeft;
    java.awt.Button rotateRight;
    java.awt.Button updateTree;
    java.awt.Button closeBtn;
    protected java.awt.Dialog dialog;
    protected RedBlackTreeCanvas algorithmCanvas;
    protected com.cim.AIA.HierarchyTree hierarchyTree;
    
    public String getAlgorithmName()
    {
        return "AIA: RedBlackTree";
    }
    
    public RedBlackTreeAnimationWindow(com.cim.AIA.AlgorithmCanvas a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1)
    {
        super(a, a0, a1);
        this.minimumNumberOfElements = 1;
        this.maximumNumberOfElements = 20;
        this.maxSize = 99;
        this.minSize = 0;
        this.everStarted = false;
        this.isRunning = false;
        this.maxDuration = 6000;
        java.awt.Panel a2 = new java.awt.Panel();
        this.panel = a2;
        RedBlackTreeCanvas a3 = new RedBlackTreeCanvas();
        this.codeCanvas = a3;
        java.awt.Panel a4 = new java.awt.Panel();
        this.controlPanel = a4;
        java.awt.Panel a5 = new java.awt.Panel();
        this.speedPanel = a5;
        String s = localization.Messages.getString("RedBlackTreeAnimationWindow.10");
        java.awt.Button a6 = new java.awt.Button(s);
        this.rotateLeft = a6;
        String s0 = localization.Messages.getString("RedBlackTreeAnimationWindow.11");
        java.awt.Button a7 = new java.awt.Button(s0);
        this.rotateRight = a7;
        String s1 = localization.Messages.getString("RedBlackTreeAnimationWindow.12");
        java.awt.Button a8 = new java.awt.Button(s1);
        this.updateTree = a8;
        String s2 = localization.Messages.getString("RedBlackTreeAnimationWindow.13");
        java.awt.Button a9 = new java.awt.Button(s2);
        this.closeBtn = a9;
        this.buildDate = "28/05/99";
        this.buildBy = "T.Witham";
        String s3 = RedBlackTreeAnimationWindow.FRAME_TITLE;
        this.frameTitle = s3;
        com.cim.AIA.SelfTestModeSelection a10 = this.selfTestMode;
        a10.setEnabled(false);
        String s4 = com.cim.AIA.AnimationWindow.STEP_LABEL;
        String s5 = com.cim.AIA.AnimationWindow.STEP_INSTRUCTIONS;
        com.cim.AIA.StepButton a11 = new com.cim.AIA.StepButton(s4, s5, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a11);
        String s6 = com.cim.AIA.AnimationWindow.BACK_LABEL;
        String s7 = com.cim.AIA.AnimationWindow.BACK_INSTRUCTIONS;
        com.cim.AIA.BackButton a12 = new com.cim.AIA.BackButton(s6, s7, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a12);
        String s8 = com.cim.AIA.AnimationWindow.RUN_LABEL;
        String s9 = com.cim.AIA.AnimationWindow.RUN_INSTRUCTIONS;
        com.cim.AIA.RunButton a13 = new com.cim.AIA.RunButton(s8, s9, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a13);
        String s10 = com.cim.AIA.AnimationWindow.PAUSE_LABEL;
        String s11 = com.cim.AIA.AnimationWindow.PAUSE_INSTRUCTIONS;
        com.cim.AIA.PauseButton a14 = new com.cim.AIA.PauseButton(s10, s11, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a14);
        String s12 = com.cim.AIA.AnimationWindow.RESET_LABEL;
        String s13 = com.cim.AIA.AnimationWindow.RESET_INSTRUCTIONS;
        com.cim.AIA.ResetButton a15 = new com.cim.AIA.ResetButton(s12, s13, (com.cim.AIA.AnimationWindow)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a15);
        String s14 = localization.Messages.getString("RedBlackTreeAnimationWindow.3");
        String s15 = localization.Messages.getString("RedBlackTreeAnimationWindow.4");
        com.cim.AIA.RestartButton a16 = new com.cim.AIA.RestartButton(s14, s15, (com.cim.AIA.AnimationWindow)this, a0);
        a16.setReinitialiseAlgorithm(false);
        a16.setClearAlgorithmState(false);
        a16.setStoreAlgorithmState(true);
        a16.setForceAlgorithmStore(true);
        this.addControlButton((com.cim.AIA.ControlButton)a16);
        String s16 = RedBlackTreeAnimationWindow.DEFAULT_LABEL;
        int[] a17 = RedBlackTreeAnimationWindow.DEFAULT_DATA;
        com.cim.AIA.PresetIntArrayDataSelection a18 = new com.cim.AIA.PresetIntArrayDataSelection(s16, true, (com.cim.AIA.AnimationWindow)this, a17);
        a18.setReinitialiseAlgorithm(true);
        a18.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a18);
        String s17 = RedBlackTreeAnimationWindow.SORTED_LABEL;
        int[] a19 = RedBlackTreeAnimationWindow.SORTED_DATA;
        com.cim.AIA.PresetIntArrayDataSelection a20 = new com.cim.AIA.PresetIntArrayDataSelection(s17, false, (com.cim.AIA.AnimationWindow)this, a19);
        a20.setReinitialiseAlgorithm(true);
        a20.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a20);
        String s18 = RedBlackTreeAnimationWindow.REVERSE_SORTED_LABEL;
        int[] a21 = RedBlackTreeAnimationWindow.REVERSE_SORTED_DATA;
        com.cim.AIA.PresetIntArrayDataSelection a22 = new com.cim.AIA.PresetIntArrayDataSelection(s18, false, (com.cim.AIA.AnimationWindow)this, a21);
        a22.setReinitialiseAlgorithm(true);
        a22.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a22);
        String s19 = RedBlackTreeAnimationWindow.SAME_LABEL;
        int i = this.minimumNumberOfElements;
        int i0 = this.maximumNumberOfElements;
        int i1 = this.minSize;
        int i2 = this.maxSize;
        com.cim.AIA.RandomSameIntArrayDataSelection a23 = new com.cim.AIA.RandomSameIntArrayDataSelection(s19, false, (com.cim.AIA.AnimationWindow)this, i, i0, i1, i2);
        a23.setReinitialiseAlgorithm(true);
        a23.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a23);
        String s20 = RedBlackTreeAnimationWindow.RANDOM_LABEL;
        int i3 = this.minimumNumberOfElements;
        int i4 = this.maximumNumberOfElements;
        int i5 = this.minSize;
        int i6 = this.maxSize;
        com.cim.AIA.RandomIntArrayDataSelection a24 = new com.cim.AIA.RandomIntArrayDataSelection(s20, false, (com.cim.AIA.AnimationWindow)this, i3, i4, i5, i6);
        a24.setReinitialiseAlgorithm(true);
        a24.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a24);
        StringBuilder a25 = new StringBuilder();
        String s21 = RedBlackTreeAnimationWindow.USER_SELECTION_LABEL;
        StringBuilder a26 = a25.append(s21);
        StringBuilder a27 = a26.append("...");
        String s22 = a27.toString();
        String s23 = RedBlackTreeAnimationWindow.USER_SELECTION_LABEL;
        int i7 = this.minSize;
        int i8 = this.maxSize;
        int i9 = this.minimumNumberOfElements;
        int i10 = this.maximumNumberOfElements;
        com.cim.AIA.UserSelectionIntArrayDataSelection a28 = new com.cim.AIA.UserSelectionIntArrayDataSelection(s22, false, (com.cim.AIA.AnimationWindow)this, s23, i7, i8, i9, i10);
        a28.setReinitialiseAlgorithm(true);
        a28.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a28);
        this.addControlListener((com.cim.AIA.ControlListener)this);
    }
    
    private void buildConstraints(java.awt.GridBagConstraints a, int i, int i0, int i1, int i2, int i3, int i4)
    {
        a.gridx = i;
        a.gridy = i0;
        a.gridwidth = i1;
        a.gridheight = i2;
        double d = (double)i3;
        a.weightx = d;
        double d0 = (double)i4;
        a.weighty = d0;
    }
    
    public void controlBack(com.cim.AIA.ControlEvent a)
    {
    }
    
    public void controlOther(com.cim.AIA.ControlEvent a)
    {
    }
    
    public void controlPause(com.cim.AIA.ControlEvent a)
    {
        this.isRunning = false;
        this.everStarted = true;
    }
    
    public void controlReset(com.cim.AIA.ControlEvent a)
    {
        this.isRunning = false;
        this.everStarted = false;
    }
    
    public void controlRestart(com.cim.AIA.ControlEvent a)
    {
        this.isRunning = false;
        this.everStarted = false;
    }
    
    public void controlRun(com.cim.AIA.ControlEvent a)
    {
        this.isRunning = true;
        this.everStarted = false;
    }
    
    public void controlStep(com.cim.AIA.ControlEvent a)
    {
        this.everStarted = true;
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
        this.initialiseTuteMenuItem();
        this.initialiseHelpMenuItem();
    }
    
    protected void initialiseTuteMenuItem()
    {
        String s = localization.Messages.getString("RedBlackTreeAnimationWindow.6");
        java.awt.Menu a = new java.awt.Menu(s);
        String s0 = localization.Messages.getString("RedBlackTreeAnimationWindow.7");
        java.awt.MenuItem a0 = new java.awt.MenuItem(s0);
        RedBlackTreeAnimationWindow$1 a1 = new RedBlackTreeAnimationWindow$1(this);
        a0.addActionListener((java.awt.event.ActionListener)a1);
        java.awt.MenuItem a2 = a.add(a0);
        java.awt.MenuBar a3 = this.menuBar;
        java.awt.Menu a4 = a3.add(a);
    }
    
    protected void initialiseViewMenuItem()
    {
        String s = localization.Messages.getString("RedBlackTreeAnimationWindow.8");
        java.awt.Menu a = new java.awt.Menu(s);
        String s0 = localization.Messages.getString("RedBlackTreeAnimationWindow.1");
        java.awt.MenuItem a0 = new java.awt.MenuItem(s0);
        RedBlackTreeAnimationWindow$2 a1 = new RedBlackTreeAnimationWindow$2(this);
        a0.addActionListener((java.awt.event.ActionListener)a1);
        java.awt.MenuItem a2 = a.add(a0);
        java.awt.MenuBar a3 = this.menuBar;
        java.awt.Menu a4 = a3.add(a);
    }
    
    protected void showAs234Tree()
    {
        com.cim.AIA.Algorithm a = this.getAlgorithm();
        RedBlackTree dummy = (RedBlackTree)a;
        RedBlackTree a0 = (RedBlackTree)a;
        label0: {
            if(a0 == null)
            {
                break label0;
            }
            java.awt.Dialog a1 = this.dialog;
            if(a1 == null)
            {
                java.awt.Frame a2 = new java.awt.Frame();
                String s = localization.Messages.getString("RedBlackTreeAnimationWindow.25");
                java.awt.Dialog a3 = new java.awt.Dialog(a2, s, false);
                this.dialog = a3;
                java.awt.Dialog a4 = this.dialog;
                a4.setSize(400, 400);
                RedBlackTreeCanvas a5 = new RedBlackTreeCanvas();
                this.algorithmCanvas = a5;
                RedBlackTreeCanvas a6 = this.algorithmCanvas;
                a6.setShowAs234Only(true);
                RedBlackTreeCanvas a7 = this.algorithmCanvas;
                a7.setShowTute(false);
                java.awt.ScrollPane a8 = new java.awt.ScrollPane();
                RedBlackTreeCanvas a9 = this.algorithmCanvas;
                a9.setParent((java.awt.Container)a8);
                RedBlackTreeCanvas a10 = this.algorithmCanvas;
                java.awt.Component a11 = a8.add((java.awt.Component)a10);
                java.awt.Dialog a12 = this.dialog;
                java.awt.Component a13 = a12.add((java.awt.Component)a8);
                java.awt.Dialog a14 = this.dialog;
                RedBlackTreeAnimationWindow$3 a15 = new RedBlackTreeAnimationWindow$3(this);
                a14.addWindowListener((java.awt.event.WindowListener)a15);
                com.cim.AIA.AlgorithmThread a16 = this.thread;
                RedBlackTreeCanvas a17 = this.algorithmCanvas;
                a16.addRepaintListener((com.cim.AIA.RepaintListener)a17);
                com.cim.AIA.AlgorithmThread a18 = this.thread;
                a18.repaint();
            }
            java.awt.Dialog a19 = this.dialog;
            a19.setVisible(true);
        }
    }
    
    protected void showTute()
    {
        com.cim.AIA.Algorithm a = this.getAlgorithm();
        RedBlackTree dummy = (RedBlackTree)a;
        RedBlackTree a0 = (RedBlackTree)a;
        label0: {
            if(a0 == null)
            {
                break label0;
            }
            java.awt.Dialog a1 = this.tuteDialog;
            if(a1 == null)
            {
                com.cim.AIA.AlgorithmThread a2 = this.thread;
                java.awt.Button a3 = this.rotateLeft;
                RedBlackTreeAnimationWindow$4 a4 = new RedBlackTreeAnimationWindow$4(this, a0);
                a3.addActionListener((java.awt.event.ActionListener)a4);
                java.awt.Button a5 = this.rotateRight;
                RedBlackTreeAnimationWindow$5 a6 = new RedBlackTreeAnimationWindow$5(this, a0);
                a5.addActionListener((java.awt.event.ActionListener)a6);
                java.awt.Button a7 = this.updateTree;
                RedBlackTreeAnimationWindow$6 a8 = new RedBlackTreeAnimationWindow$6(this, a0, a2);
                a7.addActionListener((java.awt.event.ActionListener)a8);
                java.awt.Button a9 = this.closeBtn;
                RedBlackTreeAnimationWindow$7 a10 = new RedBlackTreeAnimationWindow$7(this);
                a9.addActionListener((java.awt.event.ActionListener)a10);
                java.awt.Panel a11 = this.controlPanel;
                java.awt.Button a12 = this.rotateLeft;
                java.awt.Component a13 = a11.add((java.awt.Component)a12);
                java.awt.Panel a14 = this.controlPanel;
                java.awt.Button a15 = this.rotateRight;
                java.awt.Component a16 = a14.add((java.awt.Component)a15);
                java.awt.Panel a17 = this.controlPanel;
                java.awt.Button a18 = this.updateTree;
                java.awt.Component a19 = a17.add((java.awt.Component)a18);
                java.awt.Panel a20 = this.controlPanel;
                java.awt.Button a21 = this.closeBtn;
                java.awt.Component a22 = a20.add((java.awt.Component)a21);
                java.awt.Panel a23 = this.controlPanel;
                java.awt.Color a24 = java.awt.Color.lightGray;
                a23.setBackground(a24);
                java.awt.Frame a25 = new java.awt.Frame();
                String s = localization.Messages.getString("RedBlackTreeAnimationWindow.22");
                java.awt.Dialog a26 = new java.awt.Dialog(a25, s, false);
                this.tuteDialog = a26;
                java.awt.Dialog a27 = this.tuteDialog;
                a27.setSize(400, 600);
                RedBlackTreeCanvas a28 = new RedBlackTreeCanvas();
                this.tuteCanvas = a28;
                RedBlackTreeCanvas a29 = this.tuteCanvas;
                a29.setShowAs234Only(false);
                RedBlackTreeCanvas a30 = this.tuteCanvas;
                a30.setShowTute(true);
                java.awt.ScrollPane a31 = new java.awt.ScrollPane();
                RedBlackTreeCanvas a32 = this.tuteCanvas;
                a32.setParent((java.awt.Container)a31);
                RedBlackTreeCanvas a33 = this.tuteCanvas;
                a33.setSize(400, 400);
                RedBlackTreeCanvas a34 = this.tuteCanvas;
                java.awt.Component a35 = a31.add((java.awt.Component)a34);
                RedBlackTreeCanvas a36 = this.codeCanvas;
                a36.setShowAs234Only(false);
                RedBlackTreeCanvas a37 = this.codeCanvas;
                a37.setShowTute(false);
                RedBlackTreeCanvas a38 = this.codeCanvas;
                a38.setShowCode(true);
                java.awt.ScrollPane a39 = new java.awt.ScrollPane();
                RedBlackTreeCanvas a40 = this.codeCanvas;
                a40.setParent((java.awt.Container)a39);
                RedBlackTreeCanvas a41 = this.codeCanvas;
                a41.setSize(400, 400);
                RedBlackTreeCanvas a42 = this.codeCanvas;
                java.awt.Component a43 = a39.add((java.awt.Component)a42);
                java.awt.Panel a44 = this.speedPanel;
                java.awt.BorderLayout a45 = new java.awt.BorderLayout();
                a44.setLayout((java.awt.LayoutManager)a45);
                java.awt.Panel a46 = this.speedPanel;
                java.awt.Color a47 = java.awt.Color.lightGray;
                a46.setBackground(a47);
                String s0 = localization.Messages.getString("RedBlackTreeAnimationWindow.23");
                java.awt.Label a48 = new java.awt.Label(s0, 1);
                java.awt.Panel a49 = this.speedPanel;
                a49.add((java.awt.Component)a48, (Object)"West");
                int i = this.maxDuration;
                int i0 = i / 2;
                int i1 = this.maxDuration;
                java.awt.Scrollbar a50 = new java.awt.Scrollbar(0, i0, 1, 1, i1);
                this.speedBar = a50;
                java.awt.Scrollbar a51 = this.speedBar;
                RedBlackTreeAnimationWindow$8 a52 = new RedBlackTreeAnimationWindow$8(this, a0);
                a51.addAdjustmentListener((java.awt.event.AdjustmentListener)a52);
                java.awt.Panel a53 = this.speedPanel;
                java.awt.Scrollbar a54 = this.speedBar;
                a53.add((java.awt.Component)a54, (Object)"Center");
                String s1 = localization.Messages.getString("RedBlackTreeAnimationWindow.24");
                java.awt.Label a55 = new java.awt.Label(s1, 1);
                java.awt.Panel a56 = this.speedPanel;
                a56.add((java.awt.Component)a55, (Object)"East");
                java.awt.GridBagLayout a57 = new java.awt.GridBagLayout();
                java.awt.GridBagConstraints a58 = new java.awt.GridBagConstraints();
                a58.fill = 1;
                java.awt.Panel a59 = this.panel;
                a59.setLayout((java.awt.LayoutManager)a57);
                this.buildConstraints(a58, 0, 0, 1, 1, 100, 9);
                java.awt.Panel a60 = this.controlPanel;
                a57.setConstraints((java.awt.Component)a60, a58);
                java.awt.Panel a61 = this.panel;
                java.awt.Panel a62 = this.controlPanel;
                java.awt.Component a63 = a61.add((java.awt.Component)a62);
                this.buildConstraints(a58, 0, 1, 1, 1, 100, 60);
                a57.setConstraints((java.awt.Component)a31, a58);
                java.awt.Panel a64 = this.panel;
                java.awt.Component a65 = a64.add((java.awt.Component)a31);
                this.buildConstraints(a58, 0, 2, 1, 1, 100, 30);
                a57.setConstraints((java.awt.Component)a39, a58);
                java.awt.Panel a66 = this.panel;
                java.awt.Component a67 = a66.add((java.awt.Component)a39);
                this.buildConstraints(a58, 0, 3, 1, 1, 100, 1);
                java.awt.Panel a68 = this.speedPanel;
                a57.setConstraints((java.awt.Component)a68, a58);
                java.awt.Panel a69 = this.panel;
                java.awt.Panel a70 = this.speedPanel;
                java.awt.Component a71 = a69.add((java.awt.Component)a70);
                java.awt.Dialog a72 = this.tuteDialog;
                java.awt.Panel a73 = this.panel;
                java.awt.Component a74 = a72.add((java.awt.Component)a73);
                java.awt.Dialog a75 = this.tuteDialog;
                RedBlackTreeAnimationWindow$9 a76 = new RedBlackTreeAnimationWindow$9(this);
                a75.addWindowListener((java.awt.event.WindowListener)a76);
                com.cim.AIA.AlgorithmThread a77 = this.thread;
                RedBlackTreeCanvas a78 = this.codeCanvas;
                a77.addRepaintListener((com.cim.AIA.RepaintListener)a78);
                com.cim.AIA.AlgorithmThread a79 = this.thread;
                RedBlackTreeCanvas a80 = this.tuteCanvas;
                a79.addRepaintListener((com.cim.AIA.RepaintListener)a80);
                com.cim.AIA.AlgorithmThread a81 = this.thread;
                a81.repaint();
            }
            a0.buildTuteTree();
            java.awt.Dialog a82 = this.tuteDialog;
            a82.setVisible(true);
            com.cim.AIA.AlgorithmThread a83 = this.thread;
            a83.repaint();
        }
    }
    
    static boolean access$000(RedBlackTreeAnimationWindow a)
    {
        int i = a.isRunning?1:0;
        return i != 0;
    }
    
    static boolean access$100(RedBlackTreeAnimationWindow a)
    {
        int i = a.everStarted?1:0;
        return i != 0;
    }
    
    static int access$200(RedBlackTreeAnimationWindow a)
    {
        int i = a.maxDuration;
        return i;
    }
    
    static
    {
        String s = localization.Messages.getString("RedBlackTreeAnimationWindow.2");
        RedBlackTreeAnimationWindow.FRAME_TITLE = s;
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
        RedBlackTreeAnimationWindow.DEFAULT_DATA = a;
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
        RedBlackTreeAnimationWindow.SORTED_DATA = a0;
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
        RedBlackTreeAnimationWindow.REVERSE_SORTED_DATA = a1;
    }
}