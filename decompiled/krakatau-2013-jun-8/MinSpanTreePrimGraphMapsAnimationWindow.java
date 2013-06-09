public class MinSpanTreePrimGraphMapsAnimationWindow extends com.cim.AIA.AnimationWindow {
    final protected static String FRAME_TITLE;
    final protected static String SELF_TEST_MODE_LABEL = "Self Test Mode";
    public static int MINIMUM_NUMBER_OF_ELEMENTS;
    public static int MAXIMUM_NUMBER_OF_ELEMENTS;
    public static int MAX_SIZE;
    public static int MIN_SIZE;
    final public static int[] DEFAULT_DATA;
    protected com.cim.AIA.UserSelectionIntArrayDataSelection userData;
    protected MinSpanTreePrimGraphMapsThread graphMapsThread;
    protected MinSpanTreePrimGraphMapsAnimationWindowExt secondWindow;
    protected MinSpanTreePrimGraphMapsCanvasExt secondCanvas;
    
    public String getAlgorithmName()
    {
        return "Minimum Spanning Tree";
    }
    
    public MinSpanTreePrimGraphMapsAnimationWindow(com.cim.AIA.AlgorithmCanvas a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1)
    {
        super(a, a0, a1);
        this.graphMapsThread = null;
        this.secondWindow = null;
        this.secondCanvas = null;
        String s = MinSpanTreePrimGraphMapsAnimationWindow.FRAME_TITLE;
        this.frameTitle = s;
        this.buildDate = "10/2/2000";
        this.buildBy = "George Kong";
        com.cim.AIA.SelfTestModeSelection a2 = new com.cim.AIA.SelfTestModeSelection("Self Test Mode", false, (com.cim.AIA.AnimationWindow)this, a0);
        this.selfTestMode = a2;
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
        aia.graph.common.GraphMapsRestartButton a8 = new aia.graph.common.GraphMapsRestartButton((com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a8);
        aia.graph.common.ZoomInButton a9 = new aia.graph.common.ZoomInButton((com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a9);
        aia.graph.common.ZoomOutButton a10 = new aia.graph.common.ZoomOutButton((com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a10);
        MinSpanTreePrimGraphMapsAnimationWindow$1 a11 = new MinSpanTreePrimGraphMapsAnimationWindow$1(this);
        this.addControlListener((com.cim.AIA.ControlListener)a11);
        this.loadGraphTemplates();
        StringBuilder a12 = new StringBuilder();
        String s10 = MinSpanTreePrimGraphMapsAnimationWindow.USER_SELECTION_LABEL;
        StringBuilder a13 = a12.append(s10);
        StringBuilder a14 = a13.append("...");
        String s11 = a14.toString();
        String s12 = MinSpanTreePrimGraphMapsAnimationWindow.USER_SELECTION_LABEL;
        int i = MinSpanTreePrimGraphMapsAnimationWindow.MIN_SIZE;
        int i0 = MinSpanTreePrimGraphMapsAnimationWindow.MAX_SIZE;
        int i1 = MinSpanTreePrimGraphMapsAnimationWindow.MINIMUM_NUMBER_OF_ELEMENTS;
        int i2 = MinSpanTreePrimGraphMapsAnimationWindow.MAXIMUM_NUMBER_OF_ELEMENTS;
        com.cim.AIA.UserSelectionIntArrayDataSelection a15 = new com.cim.AIA.UserSelectionIntArrayDataSelection(s11, false, (com.cim.AIA.AnimationWindow)this, s12, i, i0, i1, i2);
        this.userData = a15;
        com.cim.AIA.UserSelectionIntArrayDataSelection a16 = this.userData;
        a16.setReinitialiseAlgorithm(true);
        com.cim.AIA.UserSelectionIntArrayDataSelection a17 = this.userData;
        a17.setClearAlgorithmState(true);
        com.cim.AIA.UserSelectionIntArrayDataSelection a18 = this.userData;
        this.addDataSelection((com.cim.AIA.DataSelection)a18);
        com.cim.AIA.Algorithm a19 = this.getAlgorithm();
        MinSpanTreePrimGraphMaps dummy = (MinSpanTreePrimGraphMaps)a19;
        MinSpanTreePrimGraphMapsThread dummy0 = (MinSpanTreePrimGraphMapsThread)a0;
        MinSpanTreePrimGraphMapsThread a20 = (MinSpanTreePrimGraphMapsThread)a0;
        this.graphMapsThread = a20;
        com.cim.AIA.SelfTestModeSelection a21 = this.selfTestMode;
        a21.setEnabled(false);
        MinSpanTreePrimGraphMapsCanvasExt a22 = new MinSpanTreePrimGraphMapsCanvasExt();
        this.secondCanvas = a22;
        MinSpanTreePrimGraphMapsCanvasExt a23 = this.secondCanvas;
        a0.addRepaintListener((com.cim.AIA.RepaintListener)a23);
        MinSpanTreePrimGraphMapsCanvasExt a24 = this.secondCanvas;
        MinSpanTreePrimGraphMapsAnimationWindowExt a25 = new MinSpanTreePrimGraphMapsAnimationWindowExt((com.cim.AIA.AlgorithmCanvas)a24, a0, a1);
        this.secondWindow = a25;
    }
    
    public void init()
    {
        ((com.cim.AIA.AnimationWindow)this).init();
        MinSpanTreePrimGraphMapsAnimationWindowExt a = this.secondWindow;
        a.init();
        MinSpanTreePrimGraphMapsAnimationWindowExt a0 = this.secondWindow;
        a0.setSize(440, 368);
        MinSpanTreePrimGraphMapsAnimationWindowExt a1 = this.secondWindow;
        a1.setLocation(585, 367);
        MinSpanTreePrimGraphMapsAnimationWindowExt a2 = this.secondWindow;
        a2.setVisible(true);
    }
    
    public void exit()
    {
        ((com.cim.AIA.AnimationWindow)this).exit();
        MinSpanTreePrimGraphMapsAnimationWindowExt a = this.secondWindow;
        a.exit();
        MinSpanTreePrimGraphMapsAnimationWindowExt a0 = this.secondWindow;
        a0.dispose();
    }
    
    protected void setBackMode()
    {
        com.cim.AIA.Algorithm a = this.getAlgorithm();
        aia.graph.common.GraphMaps dummy = (aia.graph.common.GraphMaps)a;
        aia.graph.common.GraphMaps a0 = (aia.graph.common.GraphMaps)a;
        if(a0 != null)
        {
            a0.setBackMode();
        }
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
        this.initialiseLinkMenuItem();
        this.initialiseNodesItem();
        this.initialiseHelpMenuItem();
    }
    
    protected void initialiseLinkMenuItem()
    {
        String s = localization.Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.20");
        java.awt.Menu a = new java.awt.Menu(s);
        String s0 = localization.Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.6");
        java.awt.MenuItem a0 = new java.awt.MenuItem(s0);
        String s1 = localization.Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.7");
        java.awt.MenuItem a1 = new java.awt.MenuItem(s1);
        String s2 = localization.Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.8");
        java.awt.MenuItem a2 = new java.awt.MenuItem(s2);
        MinSpanTreePrimGraphMapsAnimationWindow$2 a3 = new MinSpanTreePrimGraphMapsAnimationWindow$2(this);
        a0.addActionListener((java.awt.event.ActionListener)a3);
        MinSpanTreePrimGraphMapsAnimationWindow$3 a4 = new MinSpanTreePrimGraphMapsAnimationWindow$3(this);
        a1.addActionListener((java.awt.event.ActionListener)a4);
        MinSpanTreePrimGraphMapsAnimationWindow$4 a5 = new MinSpanTreePrimGraphMapsAnimationWindow$4(this);
        a2.addActionListener((java.awt.event.ActionListener)a5);
        java.awt.MenuItem a6 = a.add(a0);
        java.awt.MenuItem a7 = a.add(a1);
        java.awt.MenuItem a8 = a.add(a2);
        java.awt.MenuBar a9 = this.menuBar;
        java.awt.Menu a10 = a9.add(a);
    }
    
    protected void initialiseNodesItem()
    {
        String s = localization.Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.9");
        java.awt.Menu a = new java.awt.Menu(s);
        String s0 = localization.Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.10");
        java.awt.MenuItem a0 = new java.awt.MenuItem(s0);
        String s1 = localization.Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.11");
        java.awt.MenuItem a1 = new java.awt.MenuItem(s1);
        String s2 = localization.Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.12");
        java.awt.MenuItem a2 = new java.awt.MenuItem(s2);
        MinSpanTreePrimGraphMapsAnimationWindow$5 a3 = new MinSpanTreePrimGraphMapsAnimationWindow$5(this);
        a0.addActionListener((java.awt.event.ActionListener)a3);
        MinSpanTreePrimGraphMapsAnimationWindow$6 a4 = new MinSpanTreePrimGraphMapsAnimationWindow$6(this);
        a1.addActionListener((java.awt.event.ActionListener)a4);
        MinSpanTreePrimGraphMapsAnimationWindow$7 a5 = new MinSpanTreePrimGraphMapsAnimationWindow$7(this);
        a2.addActionListener((java.awt.event.ActionListener)a5);
        java.awt.MenuItem a6 = a.add(a0);
        java.awt.MenuItem a7 = a.add(a1);
        java.awt.MenuItem a8 = a.add(a2);
        java.awt.MenuBar a9 = this.menuBar;
        java.awt.Menu a10 = a9.add(a);
    }
    
    protected void resetAlgorithmEditModes(aia.graph.common.GraphMaps a)
    {
        a.setKeyChangeMode(false);
        a.setDeleteMode(false);
        a.setInsertionMode(false);
        a.endEditLink();
    }
    
    protected void addNode()
    {
        com.cim.AIA.Algorithm a = this.getAlgorithm();
        aia.graph.common.GraphMaps dummy = (aia.graph.common.GraphMaps)a;
        aia.graph.common.GraphMaps a0 = (aia.graph.common.GraphMaps)a;
        this.resetAlgorithmEditModes(a0);
        a0.setInsertionMode(true);
    }
    
    protected void deleteNode()
    {
        com.cim.AIA.Algorithm a = this.getAlgorithm();
        aia.graph.common.GraphMaps dummy = (aia.graph.common.GraphMaps)a;
        aia.graph.common.GraphMaps a0 = (aia.graph.common.GraphMaps)a;
        this.resetAlgorithmEditModes(a0);
        a0.setDeleteMode(true);
    }
    
    protected void changeKey()
    {
        com.cim.AIA.Algorithm a = this.getAlgorithm();
        aia.graph.common.GraphMaps dummy = (aia.graph.common.GraphMaps)a;
        aia.graph.common.GraphMaps a0 = (aia.graph.common.GraphMaps)a;
        this.resetAlgorithmEditModes(a0);
        a0.setKeyChangeMode(true);
    }
    
    protected void addNewLink()
    {
        com.cim.AIA.Algorithm a = this.getAlgorithm();
        aia.graph.common.GraphMaps dummy = (aia.graph.common.GraphMaps)a;
        aia.graph.common.GraphMaps a0 = (aia.graph.common.GraphMaps)a;
        this.resetAlgorithmEditModes(a0);
        Integer a1 = new Integer(1);
        a0.startEditLink(a1);
    }
    
    protected void deleteLink()
    {
        com.cim.AIA.Algorithm a = this.getAlgorithm();
        aia.graph.common.GraphMaps dummy = (aia.graph.common.GraphMaps)a;
        aia.graph.common.GraphMaps a0 = (aia.graph.common.GraphMaps)a;
        this.resetAlgorithmEditModes(a0);
        Integer a1 = new Integer(0);
        a0.startEditLink(a1);
    }
    
    protected void editLink()
    {
        com.cim.AIA.Algorithm a = this.getAlgorithm();
        aia.graph.common.GraphMaps dummy = (aia.graph.common.GraphMaps)a;
        aia.graph.common.GraphMaps a0 = (aia.graph.common.GraphMaps)a;
        this.resetAlgorithmEditModes(a0);
        a0.startEditLink((Integer)null);
    }
    
    protected void setOmniDirectional(boolean b)
    {
        com.cim.AIA.Algorithm a = this.getAlgorithm();
        int i = b?1:0;
        aia.graph.common.GraphMaps dummy = (aia.graph.common.GraphMaps)a;
        aia.graph.common.GraphMaps a0 = (aia.graph.common.GraphMaps)a;
        a0.setOmniDirectional(i != 0);
    }
    
    protected void zoom(boolean b)
    {
        com.cim.AIA.Algorithm a = this.getAlgorithm();
        int i = b?1:0;
        aia.graph.common.GraphMaps dummy = (aia.graph.common.GraphMaps)a;
        aia.graph.common.GraphMaps a0 = (aia.graph.common.GraphMaps)a;
        if(i == 0)
        {
            a0.zoomOut();
        }
        else
        {
            a0.zoomIn();
        }
    }
    
    protected void restartAlgorithm()
    {
        com.cim.AIA.Algorithm a = this.getAlgorithm();
        aia.graph.common.GraphMaps dummy = (aia.graph.common.GraphMaps)a;
        aia.graph.common.GraphMaps a0 = (aia.graph.common.GraphMaps)a;
        Object a1 = a0.getData();
        com.cim.AIA.IntArray dummy0 = (com.cim.AIA.IntArray)a1;
        com.cim.AIA.IntArray a2 = (com.cim.AIA.IntArray)a1;
        MinSpanTreePrimGraphMapsThread a3 = this.graphMapsThread;
        a3.setData((com.cim.AIA.Copyable)a2);
    }
    
    protected void loadGraphTemplates()
    {
        java.net.URL a = null;
        java.io.InputStream a0 = null;
        label5: {
            label1: {
                java.applet.Applet a1 = null;
                java.net.URL a2 = null;
                String s = null;
                java.net.URL a3 = null;
                label0: try
                {
                    a1 = this.applet;
                    break label0;
                }
                catch(java.net.MalformedURLException ignoredException)
                {
                    break label1;
                }
                label2: try
                {
                    a2 = a1.getCodeBase();
                    break label2;
                }
                catch(java.net.MalformedURLException ignoredException0)
                {
                    break label1;
                }
                label3: try
                {
                    s = localization.Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.13");
                    break label3;
                }
                catch(java.net.MalformedURLException ignoredException1)
                {
                    break label1;
                }
                label4: try
                {
                    a3 = new java.net.URL(a2, s);
                    break label4;
                }
                catch(java.net.MalformedURLException ignoredException2)
                {
                    break label1;
                }
                a = a3;
                break label5;
            }
            java.io.PrintStream a4 = System.out;
            String s0 = localization.Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.14");
            a4.println(s0);
            a = null;
        }
        try
        {
            java.io.InputStream a5 = a.openStream();
            a0 = a5;
        }
        catch(java.io.IOException ignoredException3)
        {
            java.io.PrintStream a6 = System.out;
            String s1 = localization.Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.15");
            a6.println(s1);
            a0 = null;
        }
        try
        {
            java.io.InputStreamReader a7 = new java.io.InputStreamReader(a0);
            java.io.LineNumberReader a8 = new java.io.LineNumberReader((java.io.Reader)a7);
            while(true)
            {
                String s2 = a8.readLine();
                if(s2 == null)
                {
                    break;
                }
                String s3 = s2.trim();
                int i = s3.length();
                if(i == 0)
                {
                    continue;
                }
                int i0 = s2.charAt(0);
                if(i0 != 35)
                {
                    this.processLine(s2);
                }
                else
                {
                }
            }
        }
        catch(java.io.IOException ignoredException4)
        {
            java.io.PrintStream a9 = System.out;
            String s4 = localization.Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.16");
            a9.println(s4);
        }
        try
        {
            a0.close();
        }
        catch(java.io.IOException ignoredException5)
        {
            java.io.PrintStream a10 = System.out;
            String s5 = localization.Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.17");
            a10.println(s5);
        }
    }
    
    private void processLine(String s)
    {
        java.util.Vector a = new java.util.Vector();
        java.util.StringTokenizer a0 = new java.util.StringTokenizer(s, ",");
        String s0 = a0.nextToken();
        while(true)
        {
            int i = a0.hasMoreTokens()?1:0;
            if(i == 0)
            {
                break;
            }
            else
            {
                String s1 = a0.nextToken();
                a.addElement((Object)s1);
            }
        }
        int i0 = a.size();
        int[] a1 = new int[i0];
        int i1 = 0;
        try
        {
            while(true)
            {
                int i2 = a.size();
                if(i1 >= i2)
                {
                    break;
                }
                else
                {
                    Object a2 = a.elementAt(i1);
                    String dummy = (String)a2;
                    String s2 = (String)a2;
                    String s3 = s2.trim();
                    int i3 = Integer.parseInt(s3);
                    a1[i1] = i3;
                    int i4 = i1 + 1;
                    i1 = i4;
                }
            }
        }
        catch(NumberFormatException ignoredException)
        {
            java.io.PrintStream a3 = System.out;
            String s4 = localization.Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.19");
            a3.println(s4);
        }
        com.cim.AIA.PresetIntArrayDataSelection a4 = new com.cim.AIA.PresetIntArrayDataSelection(s0, true, (com.cim.AIA.AnimationWindow)this, a1);
        a4.setReinitialiseAlgorithm(true);
        a4.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a4);
    }
    
    static
    {
        String s = localization.Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.0");
        MinSpanTreePrimGraphMapsAnimationWindow.FRAME_TITLE = s;
        MinSpanTreePrimGraphMapsAnimationWindow.MINIMUM_NUMBER_OF_ELEMENTS = 0;
        MinSpanTreePrimGraphMapsAnimationWindow.MAXIMUM_NUMBER_OF_ELEMENTS = 500;
        MinSpanTreePrimGraphMapsAnimationWindow.MAX_SIZE = 500;
        MinSpanTreePrimGraphMapsAnimationWindow.MIN_SIZE = 0;
        int[] a = new int[69];
        a[0] = 10;
        a[1] = 135;
        a[2] = 35;
        a[3] = 230;
        a[4] = 90;
        a[5] = 290;
        a[6] = 175;
        a[7] = 225;
        a[8] = 175;
        a[9] = 135;
        a[10] = 90;
        a[11] = 165;
        a[12] = 175;
        a[13] = 110;
        a[14] = 175;
        a[15] = 29;
        a[16] = 90;
        a[17] = 45;
        a[18] = 175;
        a[19] = -5;
        a[20] = 175;
        a[21] = 0;
        a[22] = 1;
        a[23] = 8;
        a[24] = 1;
        a[25] = 2;
        a[26] = 6;
        a[27] = 1;
        a[28] = 3;
        a[29] = 5;
        a[30] = 0;
        a[31] = 4;
        a[32] = 8;
        a[33] = 4;
        a[34] = 5;
        a[35] = 4;
        a[36] = 4;
        a[37] = 6;
        a[38] = 3;
        a[39] = 0;
        a[40] = 7;
        a[41] = 10;
        a[42] = 7;
        a[43] = 8;
        a[44] = 13;
        a[45] = 7;
        a[46] = 9;
        a[47] = 9;
        a[48] = 4;
        a[49] = 7;
        a[50] = 9;
        a[51] = 4;
        a[52] = 1;
        a[53] = 12;
        a[54] = 9;
        a[55] = 8;
        a[56] = 7;
        a[57] = 8;
        a[58] = 6;
        a[59] = 12;
        a[60] = 6;
        a[61] = 5;
        a[62] = 9;
        a[63] = 5;
        a[64] = 3;
        a[65] = 7;
        a[66] = 3;
        a[67] = 2;
        a[68] = 12;
        MinSpanTreePrimGraphMapsAnimationWindow.DEFAULT_DATA = a;
    }
}