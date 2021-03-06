public class BFSGraphMapsAnimationWindow extends com.cim.AIA.AnimationWindow {
    final private static long serialVersionUID = -468854561379227817L;
    final protected static String FRAME_TITLE;
    final protected static String SELF_TEST_MODE_LABEL;
    public static int MINIMUM_NUMBER_OF_ELEMENTS;
    public static int MAXIMUM_NUMBER_OF_ELEMENTS;
    public static int MAX_SIZE;
    public static int MIN_SIZE;
    final public static int[] DEFAULT_DATA;
    protected com.cim.AIA.UserSelectionIntArrayDataSelection userData;
    protected BFSGraphMapsThread graphMapsThread;
    protected BFSGraphMapsAnimationWindowExt secondWindow;
    protected BFSGraphMapsCanvasExt secondCanvas;
    
    public String getAlgorithmName()
    {
        return "Breadth First Search";
    }
    
    public BFSGraphMapsAnimationWindow(com.cim.AIA.AlgorithmCanvas a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1)
    {
        super(a, a0, a1);
        this.graphMapsThread = null;
        this.secondWindow = null;
        this.secondCanvas = null;
        String s = BFSGraphMapsAnimationWindow.FRAME_TITLE;
        this.frameTitle = s;
        this.buildDate = "10/2/2000";
        this.buildBy = "George Kong";
        String s0 = BFSGraphMapsAnimationWindow.SELF_TEST_MODE_LABEL;
        com.cim.AIA.SelfTestModeSelection a2 = new com.cim.AIA.SelfTestModeSelection(s0, false, (com.cim.AIA.AnimationWindow)this, a0);
        this.selfTestMode = a2;
        String s1 = com.cim.AIA.AnimationWindow.STEP_LABEL;
        String s2 = com.cim.AIA.AnimationWindow.STEP_INSTRUCTIONS;
        com.cim.AIA.StepButton a3 = new com.cim.AIA.StepButton(s1, s2, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a3);
        String s3 = com.cim.AIA.AnimationWindow.BACK_LABEL;
        String s4 = com.cim.AIA.AnimationWindow.BACK_INSTRUCTIONS;
        com.cim.AIA.BackButton a4 = new com.cim.AIA.BackButton(s3, s4, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a4);
        String s5 = com.cim.AIA.AnimationWindow.RUN_LABEL;
        String s6 = com.cim.AIA.AnimationWindow.RUN_INSTRUCTIONS;
        com.cim.AIA.RunButton a5 = new com.cim.AIA.RunButton(s5, s6, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a5);
        String s7 = com.cim.AIA.AnimationWindow.PAUSE_LABEL;
        String s8 = com.cim.AIA.AnimationWindow.PAUSE_INSTRUCTIONS;
        com.cim.AIA.PauseButton a6 = new com.cim.AIA.PauseButton(s7, s8, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a6);
        String s9 = com.cim.AIA.AnimationWindow.RESET_LABEL;
        String s10 = com.cim.AIA.AnimationWindow.RESET_INSTRUCTIONS;
        com.cim.AIA.ResetButton a7 = new com.cim.AIA.ResetButton(s9, s10, (com.cim.AIA.AnimationWindow)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a7);
        aia.graph.common.GraphMapsRestartButton a8 = new aia.graph.common.GraphMapsRestartButton((com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a8);
        aia.graph.common.ZoomInButton a9 = new aia.graph.common.ZoomInButton((com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a9);
        aia.graph.common.ZoomOutButton a10 = new aia.graph.common.ZoomOutButton((com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a10);
        BFSGraphMapsAnimationWindow$1 a11 = new BFSGraphMapsAnimationWindow$1(this);
        this.addControlListener((com.cim.AIA.ControlListener)a11);
        this.loadGraphTemplates();
        StringBuilder a12 = new StringBuilder();
        String s11 = BFSGraphMapsAnimationWindow.USER_SELECTION_LABEL;
        StringBuilder a13 = a12.append(s11);
        StringBuilder a14 = a13.append("...");
        String s12 = a14.toString();
        String s13 = BFSGraphMapsAnimationWindow.USER_SELECTION_LABEL;
        int i = BFSGraphMapsAnimationWindow.MIN_SIZE;
        int i0 = BFSGraphMapsAnimationWindow.MAX_SIZE;
        int i1 = BFSGraphMapsAnimationWindow.MINIMUM_NUMBER_OF_ELEMENTS;
        int i2 = BFSGraphMapsAnimationWindow.MAXIMUM_NUMBER_OF_ELEMENTS;
        com.cim.AIA.UserSelectionIntArrayDataSelection a15 = new com.cim.AIA.UserSelectionIntArrayDataSelection(s12, false, (com.cim.AIA.AnimationWindow)this, s13, i, i0, i1, i2);
        this.userData = a15;
        com.cim.AIA.UserSelectionIntArrayDataSelection a16 = this.userData;
        a16.setReinitialiseAlgorithm(true);
        com.cim.AIA.UserSelectionIntArrayDataSelection a17 = this.userData;
        a17.setClearAlgorithmState(true);
        com.cim.AIA.UserSelectionIntArrayDataSelection a18 = this.userData;
        this.addDataSelection((com.cim.AIA.DataSelection)a18);
        BFSGraphMapsThread dummy = (BFSGraphMapsThread)a0;
        BFSGraphMapsThread a19 = (BFSGraphMapsThread)a0;
        this.graphMapsThread = a19;
        com.cim.AIA.SelfTestModeSelection a20 = this.selfTestMode;
        a20.setEnabled(false);
        BFSGraphMapsCanvasExt a21 = new BFSGraphMapsCanvasExt();
        this.secondCanvas = a21;
        BFSGraphMapsCanvasExt a22 = this.secondCanvas;
        a0.addRepaintListener((com.cim.AIA.RepaintListener)a22);
        BFSGraphMapsCanvasExt a23 = this.secondCanvas;
        BFSGraphMapsAnimationWindowExt a24 = new BFSGraphMapsAnimationWindowExt((com.cim.AIA.AlgorithmCanvas)a23, a0, a1);
        this.secondWindow = a24;
    }
    
    protected void addNewLink()
    {
        com.cim.AIA.Algorithm a = this.getAlgorithm();
        BFSGraphMaps dummy = (BFSGraphMaps)a;
        BFSGraphMaps a0 = (BFSGraphMaps)a;
        this.resetAlgorithmEditModes((aia.graph.common.GraphMaps)a0);
        Integer a1 = new Integer(1);
        a0.startEditLink(a1);
    }
    
    protected void addNode()
    {
        com.cim.AIA.Algorithm a = this.getAlgorithm();
        BFSGraphMaps dummy = (BFSGraphMaps)a;
        BFSGraphMaps a0 = (BFSGraphMaps)a;
        this.resetAlgorithmEditModes((aia.graph.common.GraphMaps)a0);
        a0.setInsertionMode(true);
    }
    
    protected void changeKey()
    {
        com.cim.AIA.Algorithm a = this.getAlgorithm();
        BFSGraphMaps dummy = (BFSGraphMaps)a;
        BFSGraphMaps a0 = (BFSGraphMaps)a;
        this.resetAlgorithmEditModes((aia.graph.common.GraphMaps)a0);
        a0.setKeyChangeMode(true);
    }
    
    protected void deleteLink()
    {
        com.cim.AIA.Algorithm a = this.getAlgorithm();
        BFSGraphMaps dummy = (BFSGraphMaps)a;
        BFSGraphMaps a0 = (BFSGraphMaps)a;
        this.resetAlgorithmEditModes((aia.graph.common.GraphMaps)a0);
        Integer a1 = new Integer(0);
        a0.startEditLink(a1);
    }
    
    protected void deleteNode()
    {
        com.cim.AIA.Algorithm a = this.getAlgorithm();
        BFSGraphMaps dummy = (BFSGraphMaps)a;
        BFSGraphMaps a0 = (BFSGraphMaps)a;
        this.resetAlgorithmEditModes((aia.graph.common.GraphMaps)a0);
        a0.setDeleteMode(true);
    }
    
    public void exit()
    {
        ((com.cim.AIA.AnimationWindow)this).exit();
        BFSGraphMapsAnimationWindowExt a = this.secondWindow;
        a.exit();
        BFSGraphMapsAnimationWindowExt a0 = this.secondWindow;
        a0.dispose();
    }
    
    public void init()
    {
        ((com.cim.AIA.AnimationWindow)this).init();
        BFSGraphMapsAnimationWindowExt a = this.secondWindow;
        a.init();
        BFSGraphMapsAnimationWindowExt a0 = this.secondWindow;
        a0.setSize(440, 368);
        BFSGraphMapsAnimationWindowExt a1 = this.secondWindow;
        a1.setLocation(585, 367);
        BFSGraphMapsAnimationWindowExt a2 = this.secondWindow;
        a2.setVisible(true);
    }
    
    protected void initialiseLinkMenuItem()
    {
        String s = localization.Messages.getString("GraphMapsAnimationWindow.5");
        java.awt.Menu a = new java.awt.Menu(s);
        String s0 = localization.Messages.getString("GraphMapsAnimationWindow.6");
        java.awt.MenuItem a0 = new java.awt.MenuItem(s0);
        String s1 = localization.Messages.getString("GraphMapsAnimationWindow.7");
        java.awt.MenuItem a1 = new java.awt.MenuItem(s1);
        java.awt.MenuItem a2 = new java.awt.MenuItem("-");
        String s2 = localization.Messages.getString("GraphMapsAnimationWindow.9");
        java.awt.MenuItem a3 = new java.awt.MenuItem(s2);
        String s3 = localization.Messages.getString("GraphMapsAnimationWindow.10");
        java.awt.MenuItem a4 = new java.awt.MenuItem(s3);
        BFSGraphMapsAnimationWindow$2 a5 = new BFSGraphMapsAnimationWindow$2(this);
        a0.addActionListener((java.awt.event.ActionListener)a5);
        BFSGraphMapsAnimationWindow$3 a6 = new BFSGraphMapsAnimationWindow$3(this);
        a1.addActionListener((java.awt.event.ActionListener)a6);
        BFSGraphMapsAnimationWindow$4 a7 = new BFSGraphMapsAnimationWindow$4(this);
        a3.addActionListener((java.awt.event.ActionListener)a7);
        BFSGraphMapsAnimationWindow$5 a8 = new BFSGraphMapsAnimationWindow$5(this);
        a4.addActionListener((java.awt.event.ActionListener)a8);
        java.awt.MenuItem a9 = a.add(a0);
        java.awt.MenuItem a10 = a.add(a1);
        java.awt.MenuItem a11 = a.add(a2);
        java.awt.MenuItem a12 = a.add(a3);
        java.awt.MenuItem a13 = a.add(a4);
        java.awt.MenuBar a14 = this.menuBar;
        java.awt.Menu a15 = a14.add(a);
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
    
    protected void initialiseNodesItem()
    {
        String s = localization.Messages.getString("GraphMapsAnimationWindow.11");
        java.awt.Menu a = new java.awt.Menu(s);
        String s0 = localization.Messages.getString("GraphMapsAnimationWindow.12");
        java.awt.MenuItem a0 = new java.awt.MenuItem(s0);
        String s1 = localization.Messages.getString("GraphMapsAnimationWindow.13");
        java.awt.MenuItem a1 = new java.awt.MenuItem(s1);
        String s2 = localization.Messages.getString("GraphMapsAnimationWindow.14");
        java.awt.MenuItem a2 = new java.awt.MenuItem(s2);
        BFSGraphMapsAnimationWindow$6 a3 = new BFSGraphMapsAnimationWindow$6(this);
        a0.addActionListener((java.awt.event.ActionListener)a3);
        BFSGraphMapsAnimationWindow$7 a4 = new BFSGraphMapsAnimationWindow$7(this);
        a1.addActionListener((java.awt.event.ActionListener)a4);
        BFSGraphMapsAnimationWindow$8 a5 = new BFSGraphMapsAnimationWindow$8(this);
        a2.addActionListener((java.awt.event.ActionListener)a5);
        java.awt.MenuItem a6 = a.add(a0);
        java.awt.MenuItem a7 = a.add(a1);
        java.awt.MenuItem a8 = a.add(a2);
        java.awt.MenuBar a9 = this.menuBar;
        java.awt.Menu a10 = a9.add(a);
    }
    
    protected void loadGraphTemplates()
    {
        java.net.URL a = null;
        java.io.InputStream a0 = null;
        label4: {
            label1: {
                java.applet.Applet a1 = null;
                java.net.URL a2 = null;
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
                    a3 = new java.net.URL(a2, "BreadthFirstSearch.data");
                    break label3;
                }
                catch(java.net.MalformedURLException ignoredException1)
                {
                    break label1;
                }
                a = a3;
                break label4;
            }
            java.io.PrintStream a4 = System.out;
            a4.println("Error in URL");
            a = null;
        }
        try
        {
            java.io.InputStream a5 = a.openStream();
            a0 = a5;
        }
        catch(java.io.IOException ignoredException2)
        {
            java.io.PrintStream a6 = System.out;
            a6.println("Error opening template file for reading");
            a0 = null;
        }
        try
        {
            java.io.InputStreamReader a7 = new java.io.InputStreamReader(a0);
            java.io.LineNumberReader a8 = new java.io.LineNumberReader((java.io.Reader)a7);
            while(true)
            {
                String s = a8.readLine();
                if(s == null)
                {
                    break;
                }
                String s0 = s.trim();
                int i = s0.length();
                if(i == 0)
                {
                    continue;
                }
                int i0 = s.charAt(0);
                if(i0 != 35)
                {
                    this.processLine(s);
                }
                else
                {
                }
            }
        }
        catch(java.io.IOException ignoredException3)
        {
            java.io.PrintStream a9 = System.out;
            a9.println("Error reading from template file");
        }
        try
        {
            a0.close();
        }
        catch(java.io.IOException ignoredException4)
        {
            java.io.PrintStream a10 = System.out;
            a10.println("Error closing template file");
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
            a3.println("Error in template file format");
        }
        com.cim.AIA.PresetIntArrayDataSelection a4 = new com.cim.AIA.PresetIntArrayDataSelection(s0, true, (com.cim.AIA.AnimationWindow)this, a1);
        a4.setReinitialiseAlgorithm(true);
        a4.setClearAlgorithmState(true);
        this.addDataSelection((com.cim.AIA.DataSelection)a4);
    }
    
    protected void resetAlgorithmEditModes(aia.graph.common.GraphMaps a)
    {
        a.setKeyChangeMode(false);
        a.setDeleteMode(false);
        a.setInsertionMode(false);
        a.endEditLink();
    }
    
    protected void restartAlgorithm()
    {
        com.cim.AIA.Algorithm a = this.getAlgorithm();
        BFSGraphMaps dummy = (BFSGraphMaps)a;
        BFSGraphMaps a0 = (BFSGraphMaps)a;
        Object a1 = a0.getData();
        com.cim.AIA.IntArray dummy0 = (com.cim.AIA.IntArray)a1;
        com.cim.AIA.IntArray a2 = (com.cim.AIA.IntArray)a1;
        BFSGraphMapsThread a3 = this.graphMapsThread;
        a3.setData((com.cim.AIA.Copyable)a2);
    }
    
    protected void setBackMode()
    {
        com.cim.AIA.Algorithm a = this.getAlgorithm();
        BFSGraphMaps dummy = (BFSGraphMaps)a;
        BFSGraphMaps a0 = (BFSGraphMaps)a;
        if(a0 != null)
        {
            a0.setBackMode();
        }
    }
    
    protected void setOmniDirectional(boolean b)
    {
        com.cim.AIA.Algorithm a = this.getAlgorithm();
        int i = b?1:0;
        BFSGraphMaps dummy = (BFSGraphMaps)a;
        BFSGraphMaps a0 = (BFSGraphMaps)a;
        a0.setOmniDirectional(i != 0);
    }
    
    protected void zoom(boolean b)
    {
        com.cim.AIA.Algorithm a = this.getAlgorithm();
        int i = b?1:0;
        BFSGraphMaps dummy = (BFSGraphMaps)a;
        BFSGraphMaps a0 = (BFSGraphMaps)a;
        if(i == 0)
        {
            a0.zoomOut();
        }
        else
        {
            a0.zoomIn();
        }
    }
    
    static
    {
        String s = localization.Messages.getString("GraphMapsAnimationWindow.0");
        BFSGraphMapsAnimationWindow.FRAME_TITLE = s;
        String s0 = localization.Messages.getString("GraphMapsAnimationWindow.1");
        BFSGraphMapsAnimationWindow.SELF_TEST_MODE_LABEL = s0;
        BFSGraphMapsAnimationWindow.MINIMUM_NUMBER_OF_ELEMENTS = 0;
        BFSGraphMapsAnimationWindow.MAXIMUM_NUMBER_OF_ELEMENTS = 500;
        BFSGraphMapsAnimationWindow.MAX_SIZE = 500;
        BFSGraphMapsAnimationWindow.MIN_SIZE = 0;
        int[] a = new int[48];
        a[0] = 10;
        a[1] = 215;
        a[2] = 35;
        a[3] = 305;
        a[4] = 90;
        a[5] = 340;
        a[6] = 175;
        a[7] = 285;
        a[8] = 175;
        a[9] = 215;
        a[10] = 90;
        a[11] = 240;
        a[12] = 175;
        a[13] = 185;
        a[14] = 175;
        a[15] = 115;
        a[16] = 90;
        a[17] = 130;
        a[18] = 175;
        a[19] = 75;
        a[20] = 175;
        a[21] = 0;
        a[22] = 1;
        a[23] = 1;
        a[24] = 1;
        a[25] = 2;
        a[26] = 1;
        a[27] = 1;
        a[28] = 3;
        a[29] = 1;
        a[30] = 0;
        a[31] = 4;
        a[32] = 1;
        a[33] = 4;
        a[34] = 5;
        a[35] = 1;
        a[36] = 4;
        a[37] = 6;
        a[38] = 1;
        a[39] = 0;
        a[40] = 7;
        a[41] = 1;
        a[42] = 7;
        a[43] = 8;
        a[44] = 1;
        a[45] = 7;
        a[46] = 9;
        a[47] = 1;
        BFSGraphMapsAnimationWindow.DEFAULT_DATA = a;
    }
}