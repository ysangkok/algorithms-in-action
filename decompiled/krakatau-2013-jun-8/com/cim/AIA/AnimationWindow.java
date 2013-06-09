package com.cim.AIA;

abstract public class AnimationWindow extends java.awt.Frame implements com.cim.AIA.FinishListener, com.cim.AIA.ExitListener, com.cim.AIA.Exitable, com.cim.AIA.Helpable, java.awt.event.KeyListener, java.awt.event.WindowListener, java.awt.event.MouseMotionListener, com.cim.AIA.Controlable, com.cim.AIA.Modeable {
    final public static String DEFAULT_LABEL;
    final public static String SORTED_LABEL;
    final public static String REVERSE_SORTED_LABEL;
    final public static String SAME_LABEL;
    final public static String RANDOM_LABEL;
    final public static String USER_SELECTION_LABEL;
    final public static String NORMAL_MODE_LABEL;
    final public static String USER_PREDICTION_MODE_LABEL;
    final public static String QUIZ_MODE_LABEL;
    final public static String STEP_LABEL;
    final public static String BACK_LABEL;
    final public static String RUN_LABEL;
    final public static String PAUSE_LABEL;
    final public static String RESET_LABEL;
    final public static String STEP_INSTRUCTIONS;
    final public static String BACK_INSTRUCTIONS;
    final public static String RUN_INSTRUCTIONS;
    final public static String PAUSE_INSTRUCTIONS;
    final public static String RESET_INSTRUCTIONS;
    final public static int START_EVENT = 123128;
    final public static int FINISH_EVENT = 123129;
    protected static String JAR_FILE_NAME;
    final protected static String LOGO_IMAGE_NAME = "smalllogo.gif";
    protected static String imgDir;
    protected String buildDate;
    protected String buildBy;
    public String frameTitle;
    protected String slowerLabel;
    protected String fasterLabel;
    protected String algorithmMenuLabel;
    protected String configurationLabel;
    protected String exitLabel;
    protected String problemSelectionLabel;
    protected String modeSelectionLabel;
    protected String helpLabel;
    protected String aboutLabel;
    protected java.util.Vector controlButtons;
    protected int maxSpeed;
    protected java.awt.MenuBar menuBar;
    protected java.awt.Menu algorithmMenu;
    protected com.cim.common.RadioMenu dataMenu;
    protected com.cim.common.RadioMenu modeMenu;
    protected java.awt.Menu help;
    protected java.awt.MenuItem exit;
    protected java.awt.MenuItem configuration;
    protected java.awt.MenuItem about;
    protected java.awt.Scrollbar speedBar;
    protected java.awt.Panel controlPanel;
    protected com.cim.AIA.AlgorithmThread thread;
    protected com.cim.AIA.AlgorithmCanvas canvas;
    protected com.cim.AIA.Copyable currentData;
    protected java.util.Vector dataSelections;
    protected java.util.Vector modeSelections;
    protected java.util.Vector exitListeners;
    protected java.awt.ScrollPane scrollPane;
    protected java.util.Vector helpListeners;
    protected java.util.Vector controlListeners;
    protected java.util.Vector modeListeners;
    protected boolean canStepForward;
    protected java.applet.Applet applet;
    protected com.cim.AIA.NormalModeSelection normalMode;
    protected com.cim.AIA.SelfTestModeSelection selfTestMode;
    protected java.awt.Image logoImage;
    protected com.cim.AIA.ImageCanvas logoCanvas;
    
    public AnimationWindow(com.cim.AIA.AlgorithmCanvas a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1)
    {
        java.net.URL a2 = null;
        super();
        this.buildDate = "dd/mm/yyyy";
        this.buildBy = "Initial Surname";
        this.frameTitle = "AIA: AlgorithmName";
        String s = localization.Messages.getString("AnimationWindow.24");
        this.slowerLabel = s;
        String s0 = localization.Messages.getString("AnimationWindow.25");
        this.fasterLabel = s0;
        String s1 = localization.Messages.getString("AnimationWindow.26");
        this.algorithmMenuLabel = s1;
        String s2 = localization.Messages.getString("AnimationWindow.27");
        this.configurationLabel = s2;
        String s3 = localization.Messages.getString("AnimationWindow.28");
        this.exitLabel = s3;
        String s4 = localization.Messages.getString("AnimationWindow.29");
        this.problemSelectionLabel = s4;
        String s5 = localization.Messages.getString("AnimationWindow.30");
        this.modeSelectionLabel = s5;
        String s6 = localization.Messages.getString("AnimationWindow.31");
        this.helpLabel = s6;
        String s7 = localization.Messages.getString("AnimationWindow.32");
        this.aboutLabel = s7;
        java.util.Vector a3 = new java.util.Vector();
        this.controlButtons = a3;
        this.maxSpeed = 4000;
        java.util.Vector a4 = new java.util.Vector();
        this.dataSelections = a4;
        java.util.Vector a5 = new java.util.Vector();
        this.modeSelections = a5;
        java.util.Vector a6 = new java.util.Vector();
        this.exitListeners = a6;
        java.util.Vector a7 = new java.util.Vector();
        this.helpListeners = a7;
        java.util.Vector a8 = new java.util.Vector();
        this.controlListeners = a8;
        java.util.Vector a9 = new java.util.Vector();
        this.modeListeners = a9;
        this.canStepForward = true;
        this.logoImage = null;
        this.applet = a1;
        this.canvas = a;
        this.thread = a0;
        java.awt.ScrollPane a10 = new java.awt.ScrollPane(0);
        this.scrollPane = a10;
        String s8 = this.algorithmMenuLabel;
        java.awt.Menu a11 = new java.awt.Menu(s8);
        this.algorithmMenu = a11;
        String s9 = this.configurationLabel;
        java.awt.MenuItem a12 = new java.awt.MenuItem(s9);
        this.configuration = a12;
        String s10 = this.exitLabel;
        java.awt.MenuItem a13 = new java.awt.MenuItem(s10);
        this.exit = a13;
        String s11 = this.problemSelectionLabel;
        com.cim.common.RadioMenu a14 = new com.cim.common.RadioMenu(s11, true);
        this.dataMenu = a14;
        String s12 = this.modeSelectionLabel;
        com.cim.common.RadioMenu a15 = new com.cim.common.RadioMenu(s12, true);
        this.modeMenu = a15;
        String s13 = this.helpLabel;
        java.awt.Menu a16 = new java.awt.Menu(s13);
        this.help = a16;
        StringBuilder a17 = new StringBuilder();
        String s14 = this.aboutLabel;
        StringBuilder a18 = a17.append(s14);
        StringBuilder a19 = a18.append("...");
        String s15 = a19.toString();
        java.awt.MenuItem a20 = new java.awt.MenuItem(s15);
        this.about = a20;
        java.awt.Panel a21 = new java.awt.Panel();
        this.controlPanel = a21;
        java.awt.MediaTracker a22 = new java.awt.MediaTracker((java.awt.Component)this);
        java.net.URL a23 = a1.getDocumentBase();
        String s16 = a23.toString();
        int i = s16.length();
        int i0 = i - 1;
        int i1 = i0;
        while(true)
        {
            int i2 = s16.charAt(i1);
            if(i2 == 47)
            {
                break;
            }
            else
            {
                int i3 = i1 + -1;
                i1 = i3;
            }
        }
        String s17 = s16.substring(0, i1);
        StringBuilder a24 = new StringBuilder();
        StringBuilder a25 = a24.append(s17);
        StringBuilder a26 = a25.append("/");
        String s18 = a26.toString();
        try
        {
            java.net.URL a27 = new java.net.URL(s18);
            a2 = a27;
        }
        catch(Exception ignoredException)
        {
            a2 = a23;
        }
        com.cim.AIA.AlgorithmApplet dummy = (com.cim.AIA.AlgorithmApplet)a1;
        com.cim.AIA.AlgorithmApplet a28 = (com.cim.AIA.AlgorithmApplet)a1;
        label46: {
            label34: {
                java.net.MalformedURLException a29 = null;
                label1: {
                    String s19 = null;
                    StringBuilder a30 = null;
                    String s20 = null;
                    StringBuilder a31 = null;
                    String s21 = null;
                    StringBuilder a32 = null;
                    String s22 = null;
                    StringBuilder a33 = null;
                    java.net.URL a34 = null;
                    StringBuilder a35 = null;
                    StringBuilder a36 = null;
                    StringBuilder a37 = null;
                    String s23 = null;
                    java.net.URL a38 = null;
                    Class a39 = null;
                    java.io.InputStream a40 = null;
                    java.awt.Image a41 = null;
                    java.awt.Image a42 = null;
                    Class a43 = null;
                    java.io.InputStream a44 = null;
                    com.cim.AIA.smalllogoProcessor a45 = null;
                    int i4 = 0;
                    java.net.URL a46 = null;
                    java.awt.Image a47 = null;
                    StringBuilder a48 = null;
                    StringBuilder a49 = null;
                    StringBuilder a50 = null;
                    String s24 = null;
                    java.io.PrintStream a51 = null;
                    StringBuilder a52 = null;
                    StringBuilder a53 = null;
                    StringBuilder a54 = null;
                    String s25 = null;
                    label0: try
                    {
                        s19 = a28.imageDirectory;
                        break label0;
                    }
                    catch(java.net.MalformedURLException a55)
                    {
                        a29 = a55;
                        break label1;
                    }
                    label2: try
                    {
                        com.cim.AIA.AnimationWindow.imgDir = s19;
                        break label2;
                    }
                    catch(java.net.MalformedURLException a56)
                    {
                        a29 = a56;
                        break label1;
                    }
                    label3: try
                    {
                        a30 = new StringBuilder();
                        break label3;
                    }
                    catch(java.net.MalformedURLException a57)
                    {
                        a29 = a57;
                        break label1;
                    }
                    label4: try
                    {
                        s20 = a2.toString();
                        break label4;
                    }
                    catch(java.net.MalformedURLException a58)
                    {
                        a29 = a58;
                        break label1;
                    }
                    label5: try
                    {
                        a31 = a30.append(s20);
                        break label5;
                    }
                    catch(java.net.MalformedURLException a59)
                    {
                        a29 = a59;
                        break label1;
                    }
                    label6: try
                    {
                        s21 = com.cim.AIA.AnimationWindow.imgDir;
                        break label6;
                    }
                    catch(java.net.MalformedURLException a60)
                    {
                        a29 = a60;
                        break label1;
                    }
                    label7: try
                    {
                        a32 = a31.append(s21);
                        break label7;
                    }
                    catch(java.net.MalformedURLException a61)
                    {
                        a29 = a61;
                        break label1;
                    }
                    label8: try
                    {
                        s22 = a32.toString();
                        break label8;
                    }
                    catch(java.net.MalformedURLException a62)
                    {
                        a29 = a62;
                        break label1;
                    }
                    label9: try
                    {
                        com.cim.AIA.AnimationWindow.imgDir = s22;
                        break label9;
                    }
                    catch(java.net.MalformedURLException a63)
                    {
                        a29 = a63;
                        break label1;
                    }
                    label10: try
                    {
                        a33 = new StringBuilder();
                        break label10;
                    }
                    catch(java.net.MalformedURLException a64)
                    {
                        a29 = a64;
                        break label1;
                    }
                    label11: try
                    {
                        a34 = a1.getCodeBase();
                        break label11;
                    }
                    catch(java.net.MalformedURLException a65)
                    {
                        a29 = a65;
                        break label1;
                    }
                    label12: try
                    {
                        a35 = a33.append((Object)a34);
                        break label12;
                    }
                    catch(java.net.MalformedURLException a66)
                    {
                        a29 = a66;
                        break label1;
                    }
                    label13: try
                    {
                        a36 = a35.append("images/");
                        break label13;
                    }
                    catch(java.net.MalformedURLException a67)
                    {
                        a29 = a67;
                        break label1;
                    }
                    label14: try
                    {
                        a37 = a36.append("smalllogo.gif");
                        break label14;
                    }
                    catch(java.net.MalformedURLException a68)
                    {
                        a29 = a68;
                        break label1;
                    }
                    label15: try
                    {
                        s23 = a37.toString();
                        break label15;
                    }
                    catch(java.net.MalformedURLException a69)
                    {
                        a29 = a69;
                        break label1;
                    }
                    label16: try
                    {
                        a38 = new java.net.URL(s23);
                        break label16;
                    }
                    catch(java.net.MalformedURLException a70)
                    {
                        a29 = a70;
                        break label1;
                    }
                    label17: try
                    {
                        a39 = ((Object)a1).getClass();
                        break label17;
                    }
                    catch(java.net.MalformedURLException a71)
                    {
                        a29 = a71;
                        break label1;
                    }
                    label18: try
                    {
                        a40 = a39.getResourceAsStream("images/smalllogo.gif");
                        break label18;
                    }
                    catch(java.net.MalformedURLException a72)
                    {
                        a29 = a72;
                        break label1;
                    }
                    label19: try
                    {
                        a41 = com.cim.AIA.StreamImage.getImage(a40);
                        break label19;
                    }
                    catch(java.net.MalformedURLException a73)
                    {
                        a29 = a73;
                        break label1;
                    }
                    label20: try
                    {
                        this.logoImage = a41;
                        break label20;
                    }
                    catch(java.net.MalformedURLException a74)
                    {
                        a29 = a74;
                        break label1;
                    }
                    label21: try
                    {
                        a42 = this.logoImage;
                        break label21;
                    }
                    catch(java.net.MalformedURLException a75)
                    {
                        a29 = a75;
                        break label1;
                    }
                    label22: {
                        java.io.PrintStream a76 = null;
                        StringBuilder a77 = null;
                        StringBuilder a78 = null;
                        String s26 = null;
                        StringBuilder a79 = null;
                        String s27 = null;
                        Object a80 = null;
                        java.awt.Image a81 = null;
                        if(a42 != null)
                        {
                            break label22;
                        }
                        try
                        {
                            a76 = System.out;
                        }
                        catch(java.net.MalformedURLException a82)
                        {
                            a29 = a82;
                            break label1;
                        }
                        try
                        {
                            a77 = new StringBuilder();
                        }
                        catch(java.net.MalformedURLException a83)
                        {
                            a29 = a83;
                            break label1;
                        }
                        try
                        {
                            a78 = a77.append("Logo Image is loading from local...");
                        }
                        catch(java.net.MalformedURLException a84)
                        {
                            a29 = a84;
                            break label1;
                        }
                        try
                        {
                            s26 = a38.toString();
                        }
                        catch(java.net.MalformedURLException a85)
                        {
                            a29 = a85;
                            break label1;
                        }
                        try
                        {
                            a79 = a78.append(s26);
                        }
                        catch(java.net.MalformedURLException a86)
                        {
                            a29 = a86;
                            break label1;
                        }
                        try
                        {
                            s27 = a79.toString();
                        }
                        catch(java.net.MalformedURLException a87)
                        {
                            a29 = a87;
                            break label1;
                        }
                        try
                        {
                            a76.println(s27);
                        }
                        catch(java.net.MalformedURLException a88)
                        {
                            a29 = a88;
                            break label1;
                        }
                        try
                        {
                            a80 = a1.getAppletContext();
                        }
                        catch(java.net.MalformedURLException a89)
                        {
                            a29 = a89;
                            break label1;
                        }
                        try
                        {
                            a81 = ((java.applet.AppletContext)a80).getImage(a38);
                        }
                        catch(java.net.MalformedURLException a90)
                        {
                            a29 = a90;
                            break label1;
                        }
                        try
                        {
                            this.logoImage = a81;
                        }
                        catch(java.net.MalformedURLException a91)
                        {
                            a29 = a91;
                            break label1;
                        }
                    }
                    label23: try
                    {
                        a43 = ((Object)a1).getClass();
                        break label23;
                    }
                    catch(java.net.MalformedURLException a92)
                    {
                        a29 = a92;
                        break label1;
                    }
                    label24: try
                    {
                        a44 = a43.getResourceAsStream("images/smalllogo.gif");
                        break label24;
                    }
                    catch(java.net.MalformedURLException a93)
                    {
                        a29 = a93;
                        break label1;
                    }
                    label25: try
                    {
                        a45 = new com.cim.AIA.smalllogoProcessor((java.net.URL)null);
                        break label25;
                    }
                    catch(java.net.MalformedURLException a94)
                    {
                        a29 = a94;
                        break label1;
                    }
                    label26: try
                    {
                        i4 = a45.checkFile(a44)?1:0;
                        break label26;
                    }
                    catch(java.net.MalformedURLException a95)
                    {
                        a29 = a95;
                        break label1;
                    }
                    label27: {
                        java.net.URL a96 = null;
                        java.net.URL a97 = null;
                        if(i4 != 0)
                        {
                            a46 = a97;
                            break label27;
                        }
                        label29: {
                            java.net.URL a98 = null;
                            label28: {
                                Object a99 = null;
                                java.awt.Image a100 = null;
                                try
                                {
                                    java.net.URL a101 = null;
                                    try
                                    {
                                        a101 = null;
                                        a96 = new java.net.URL("http://ww2.cs.mu.oz.au/muaia/images/smalllogo.gif");
                                    }
                                    catch(SecurityException ignoredException0)
                                    {
                                        a98 = a101;
                                        break label28;
                                    }
                                }
                                catch(java.net.MalformedURLException a102)
                                {
                                    a29 = a102;
                                    break label1;
                                }
                                try
                                {
                                    try
                                    {
                                        a99 = a1.getAppletContext();
                                    }
                                    catch(SecurityException ignoredException1)
                                    {
                                        a98 = a96;
                                        break label28;
                                    }
                                }
                                catch(java.net.MalformedURLException a103)
                                {
                                    a29 = a103;
                                    break label1;
                                }
                                try
                                {
                                    try
                                    {
                                        a100 = ((java.applet.AppletContext)a99).getImage(a96);
                                    }
                                    catch(SecurityException ignoredException2)
                                    {
                                        a98 = a96;
                                        break label28;
                                    }
                                }
                                catch(java.net.MalformedURLException a104)
                                {
                                    a29 = a104;
                                    break label1;
                                }
                                try
                                {
                                    try
                                    {
                                        this.logoImage = a100;
                                        break label29;
                                    }
                                    catch(SecurityException ignoredException3)
                                    {
                                        a98 = a96;
                                    }
                                }
                                catch(java.net.MalformedURLException a105)
                                {
                                    a29 = a105;
                                    break label1;
                                }
                            }
                            a46 = a98;
                            break label27;
                        }
                        a46 = a96;
                    }
                    label30: try
                    {
                        a47 = this.logoImage;
                        break label30;
                    }
                    catch(java.net.MalformedURLException a106)
                    {
                        a29 = a106;
                        break label1;
                    }
                    label31: {
                        java.awt.Image a107 = null;
                        if(a47 == null)
                        {
                            break label31;
                        }
                        label32: try
                        {
                            a107 = this.logoImage;
                            break label32;
                        }
                        catch(java.net.MalformedURLException a108)
                        {
                            a29 = a108;
                            break label1;
                        }
                        label33: try
                        {
                            a22.addImage(a107, 0);
                            break label33;
                        }
                        catch(java.net.MalformedURLException a109)
                        {
                            a29 = a109;
                            break label1;
                        }
                        break label34;
                    }
                    label35: try
                    {
                        a48 = new StringBuilder();
                        break label35;
                    }
                    catch(java.net.MalformedURLException a110)
                    {
                        a29 = a110;
                        break label1;
                    }
                    label36: try
                    {
                        a49 = a48.append("com.cim.AIA.AnimationWindow: Unable to load image: ");
                        break label36;
                    }
                    catch(java.net.MalformedURLException a111)
                    {
                        a29 = a111;
                        break label1;
                    }
                    label37: try
                    {
                        a50 = a49.append((Object)a46);
                        break label37;
                    }
                    catch(java.net.MalformedURLException a112)
                    {
                        a29 = a112;
                        break label1;
                    }
                    label38: try
                    {
                        s24 = a50.toString();
                        break label38;
                    }
                    catch(java.net.MalformedURLException a113)
                    {
                        a29 = a113;
                        break label1;
                    }
                    label39: try
                    {
                        a1.showStatus(s24);
                        break label39;
                    }
                    catch(java.net.MalformedURLException a114)
                    {
                        a29 = a114;
                        break label1;
                    }
                    label40: try
                    {
                        a51 = System.out;
                        break label40;
                    }
                    catch(java.net.MalformedURLException a115)
                    {
                        a29 = a115;
                        break label1;
                    }
                    label41: try
                    {
                        a52 = new StringBuilder();
                        break label41;
                    }
                    catch(java.net.MalformedURLException a116)
                    {
                        a29 = a116;
                        break label1;
                    }
                    label42: try
                    {
                        a53 = a52.append("com.cim.AIA.AnimationWindow: Unable to load image: ");
                        break label42;
                    }
                    catch(java.net.MalformedURLException a117)
                    {
                        a29 = a117;
                        break label1;
                    }
                    label43: try
                    {
                        a54 = a53.append((Object)a46);
                        break label43;
                    }
                    catch(java.net.MalformedURLException a118)
                    {
                        a29 = a118;
                        break label1;
                    }
                    label44: try
                    {
                        s25 = a54.toString();
                        break label44;
                    }
                    catch(java.net.MalformedURLException a119)
                    {
                        a29 = a119;
                        break label1;
                    }
                    label45: try
                    {
                        a51.println(s25);
                        break label45;
                    }
                    catch(java.net.MalformedURLException a120)
                    {
                        a29 = a120;
                        break label1;
                    }
                    break label46;
                }
                StringBuilder a121 = new StringBuilder();
                StringBuilder a122 = a121.append("com.cim.AIA.AnimationWindow: Invalid URL: ");
                StringBuilder a123 = a122.append((Object)a29);
                String s28 = a123.toString();
                a1.showStatus(s28);
                java.io.PrintStream a124 = System.out;
                StringBuilder a125 = new StringBuilder();
                StringBuilder a126 = a125.append("com.cim.AIA.AnimationWindow: Invalid URL: ");
                StringBuilder a127 = a126.append((Object)a29);
                String s29 = a127.toString();
                a124.println(s29);
            }
            try
            {
                a22.waitForAll();
            }
            catch(InterruptedException ignoredException4)
            {
            }
            java.awt.Image a128 = this.logoImage;
            com.cim.AIA.ImageCanvas a129 = new com.cim.AIA.ImageCanvas(a128, (java.awt.Dimension)null);
            this.logoCanvas = a129;
            this.initialiseDefaultModeMenu();
            this.addKeyListener((java.awt.event.KeyListener)this);
            a.addKeyListener((java.awt.event.KeyListener)this);
            this.addWindowListener((java.awt.event.WindowListener)this);
            com.cim.AIA.AnimationWindow$1 a130 = new com.cim.AIA.AnimationWindow$1(this);
            this.addMouseListener((java.awt.event.MouseListener)a130);
            this.addMouseMotionListener((java.awt.event.MouseMotionListener)this);
            a.addMouseMotionListener((java.awt.event.MouseMotionListener)this);
        }
    }
    
    public void addControlButton(com.cim.AIA.ControlButton a)
    {
        java.util.Vector a0 = this.controlButtons;
        a0.addElement((Object)a);
        this.addControlListener((com.cim.AIA.ControlListener)a);
        int i = 0;
        while(true)
        {
            java.util.Vector a1 = this.helpListeners;
            int i0 = a1.size();
            if(i >= i0)
            {
                java.awt.Panel a2 = this.controlPanel;
                java.awt.Component a3 = a2.add((java.awt.Component)a);
                return;
            }
            java.util.Vector a4 = this.helpListeners;
            Object a5 = a4.elementAt(i);
            com.cim.AIA.HelpListener dummy = (com.cim.AIA.HelpListener)a5;
            java.util.Vector a6 = a.helpListeners;
            int i1 = a6.contains(a5)?1:0;
            if(i1 == 0)
            {
                a.addHelpListener((com.cim.AIA.HelpListener)a5);
            }
            int i2 = i + 1;
            i = i2;
        }
    }
    
    public void addControlListener(com.cim.AIA.ControlListener a)
    {
        java.util.Vector a0 = this.controlListeners;
        Object a1 = a;
        a0.addElement(a1);
    }
    
    public void addDataSelection(com.cim.AIA.DataSelection a)
    {
        com.cim.common.RadioMenu a0 = this.dataMenu;
        java.awt.MenuItem a1 = a0.add((java.awt.MenuItem)a);
        java.util.Vector a2 = this.dataSelections;
        a2.addElement((Object)a);
    }
    
    public void addExitListener(com.cim.AIA.ExitListener a)
    {
        java.util.Vector a0 = this.exitListeners;
        Object a1 = a;
        a0.addElement(a1);
    }
    
    public void addHelpListener(com.cim.AIA.HelpListener a)
    {
        java.util.Vector a0 = this.helpListeners;
        Object a1 = a;
        a0.addElement(a1);
        com.cim.AIA.ImageCanvas a2 = this.logoCanvas;
        label0: {
            if(a2 == null)
            {
                break label0;
            }
            com.cim.AIA.ImageCanvas a3 = this.logoCanvas;
            java.util.Vector a4 = a3.helpListeners;
            int i = a4.contains(a1)?1:0;
            if(i == 0)
            {
                com.cim.AIA.ImageCanvas a5 = this.logoCanvas;
                a5.addHelpListener((com.cim.AIA.HelpListener)a1);
            }
        }
        int i0 = 0;
        while(true)
        {
            java.util.Vector a6 = this.controlButtons;
            int i1 = a6.size();
            if(i0 >= i1)
            {
                return;
            }
            java.util.Vector a7 = this.controlButtons;
            Object a8 = a7.elementAt(i0);
            com.cim.AIA.ControlButton dummy = (com.cim.AIA.ControlButton)a8;
            com.cim.AIA.ControlButton a9 = (com.cim.AIA.ControlButton)a8;
            java.util.Vector a10 = a9.helpListeners;
            int i2 = a10.contains(a1)?1:0;
            if(i2 == 0)
            {
                a9.addHelpListener((com.cim.AIA.HelpListener)a1);
            }
            int i3 = i0 + 1;
            i0 = i3;
        }
    }
    
    public void addModeListener(com.cim.AIA.ModeListener a)
    {
        java.util.Vector a0 = this.modeListeners;
        Object a1 = a;
        a0.addElement(a1);
    }
    
    public void addModeSelection(com.cim.AIA.ModeSelection a)
    {
        com.cim.common.RadioMenu a0 = this.modeMenu;
        java.awt.MenuItem a1 = a0.add((java.awt.MenuItem)a);
        java.util.Vector a2 = this.modeSelections;
        a2.addElement((Object)a);
        this.addModeListener((com.cim.AIA.ModeListener)a);
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
    
    public void cleanUp()
    {
        java.util.Vector a = this.controlButtons;
        if(a != null)
        {
            java.util.Vector a0 = this.controlButtons;
            a0.removeAllElements();
        }
        this.controlButtons = null;
        java.awt.MenuBar a1 = this.menuBar;
        label0: {
            if(a1 == null)
            {
                break label0;
            }
            int i = 0;
            while(true)
            {
                java.awt.MenuBar a2 = this.menuBar;
                int i0 = a2.getMenuCount();
                if(i >= i0)
                {
                    break;
                }
                else
                {
                    java.awt.MenuBar a3 = this.menuBar;
                    a3.remove(i);
                    int i1 = i + 1;
                    i = i1;
                }
            }
        }
        this.menuBar = null;
        java.awt.Menu a4 = this.algorithmMenu;
        label1: {
            if(a4 == null)
            {
                break label1;
            }
            int i2 = 0;
            while(true)
            {
                java.awt.Menu a5 = this.algorithmMenu;
                int i3 = a5.getItemCount();
                if(i2 >= i3)
                {
                    break;
                }
                else
                {
                    java.awt.Menu a6 = this.algorithmMenu;
                    a6.remove(i2);
                    int i4 = i2 + 1;
                    i2 = i4;
                }
            }
        }
        this.algorithmMenu = null;
        java.awt.Menu a7 = this.help;
        label2: {
            if(a7 == null)
            {
                break label2;
            }
            int i5 = 0;
            while(true)
            {
                java.awt.Menu a8 = this.help;
                int i6 = a8.getItemCount();
                if(i5 >= i6)
                {
                    break;
                }
                else
                {
                    java.awt.Menu a9 = this.help;
                    a9.remove(i5);
                    int i7 = i5 + 1;
                    i5 = i7;
                }
            }
        }
        this.help = null;
        com.cim.common.RadioMenu a10 = this.dataMenu;
        label3: {
            if(a10 == null)
            {
                break label3;
            }
            int i8 = 0;
            while(true)
            {
                com.cim.common.RadioMenu a11 = this.dataMenu;
                int i9 = a11.getItemCount();
                if(i8 >= i9)
                {
                    break;
                }
                else
                {
                    com.cim.common.RadioMenu a12 = this.dataMenu;
                    a12.remove(i8);
                    int i10 = i8 + 1;
                    i8 = i10;
                }
            }
        }
        this.dataMenu = null;
        com.cim.common.RadioMenu a13 = this.modeMenu;
        label4: {
            if(a13 == null)
            {
                break label4;
            }
            int i11 = 0;
            while(true)
            {
                com.cim.common.RadioMenu a14 = this.modeMenu;
                int i12 = a14.getItemCount();
                if(i11 >= i12)
                {
                    break;
                }
                else
                {
                    com.cim.common.RadioMenu a15 = this.modeMenu;
                    a15.remove(i11);
                    int i13 = i11 + 1;
                    i11 = i13;
                }
            }
        }
        this.modeMenu = null;
        this.exit = null;
        this.configuration = null;
        this.about = null;
        this.speedBar = null;
        java.awt.Panel a16 = this.controlPanel;
        if(a16 != null)
        {
            java.awt.Panel a17 = this.controlPanel;
            a17.removeAll();
        }
        this.controlPanel = null;
        java.awt.ScrollPane a18 = this.scrollPane;
        if(a18 != null)
        {
            java.awt.ScrollPane a19 = this.scrollPane;
            a19.removeAll();
        }
        this.scrollPane = null;
        this.thread = null;
        com.cim.AIA.AlgorithmCanvas a20 = this.canvas;
        if(a20 != null)
        {
            com.cim.AIA.AlgorithmCanvas a21 = this.canvas;
            a21.cleanUp();
        }
        this.canvas = null;
        this.currentData = null;
        java.util.Vector a22 = this.dataSelections;
        if(a22 != null)
        {
            java.util.Vector a23 = this.dataSelections;
            a23.removeAllElements();
        }
        this.dataSelections = null;
        java.util.Vector a24 = this.modeSelections;
        if(a24 != null)
        {
            java.util.Vector a25 = this.modeSelections;
            a25.removeAllElements();
        }
        this.modeSelections = null;
        java.util.Vector a26 = this.helpListeners;
        if(a26 != null)
        {
            java.util.Vector a27 = this.helpListeners;
            a27.removeAllElements();
        }
        this.helpListeners = null;
        java.util.Vector a28 = this.controlListeners;
        if(a28 != null)
        {
            java.util.Vector a29 = this.controlListeners;
            a29.removeAllElements();
        }
        this.controlListeners = null;
        java.util.Vector a30 = this.modeListeners;
        if(a30 != null)
        {
            java.util.Vector a31 = this.modeListeners;
            a31.removeAllElements();
        }
        this.modeListeners = null;
        this.applet = null;
        this.normalMode = null;
        this.selfTestMode = null;
        java.awt.Image a32 = this.logoImage;
        if(a32 != null)
        {
            java.awt.Image a33 = this.logoImage;
            a33.flush();
        }
        this.logoImage = null;
        com.cim.AIA.ImageCanvas a34 = this.logoCanvas;
        if(a34 != null)
        {
            com.cim.AIA.ImageCanvas a35 = this.logoCanvas;
            a35.cleanUp();
        }
        this.logoCanvas = null;
    }
    
    public void exit()
    {
        com.cim.AIA.AlgorithmThread a = this.thread;
        if(a != null)
        {
            com.cim.AIA.AlgorithmThread a0 = this.thread;
            a0.endThread();
        }
        com.cim.AIA.ExitEvent a1 = new com.cim.AIA.ExitEvent((Object)this);
        java.util.Vector a2 = this.exitListeners;
        label0: {
            if(a2 == null)
            {
                break label0;
            }
            int i = 0;
            while(true)
            {
                java.util.Vector a3 = this.exitListeners;
                if(a3 == null)
                {
                    break;
                }
                java.util.Vector a4 = this.exitListeners;
                int i0 = a4.size();
                if(i >= i0)
                {
                    break;
                }
                else
                {
                    java.util.Vector a5 = this.exitListeners;
                    Object a6 = a5.elementAt(i);
                    com.cim.AIA.ExitListener dummy = (com.cim.AIA.ExitListener)a6;
                    ((com.cim.AIA.ExitListener)a6).processExitEvent(a1);
                    int i1 = i + 1;
                    i = i1;
                }
            }
            java.util.Vector a7 = this.exitListeners;
            a7.removeAllElements();
            this.exitListeners = null;
        }
    }
    
    protected com.cim.AIA.Algorithm getAlgorithm()
    {
        com.cim.AIA.AlgorithmThread a = this.thread;
        com.cim.AIA.Algorithm a0 = a.getAlgorithm();
        return a0;
    }
    
    protected com.cim.AIA.AlgorithmWindow getAlgorithmWindow()
    {
        com.cim.AIA.AlgorithmThread a = this.thread;
        com.cim.AIA.AlgorithmWindow a0 = a.getAlgorithmWindow();
        return a0;
    }
    
    public com.cim.AIA.BreakPoint getBreakPoint()
    {
        com.cim.AIA.AlgorithmThread a = this.thread;
        com.cim.AIA.BreakPoint a0 = a.getBreakPoint();
        return a0;
    }
    
    final public String getBuildInfo()
    {
        StringBuilder a = new StringBuilder();
        String s = this.frameTitle;
        StringBuilder a0 = a.append(s);
        StringBuilder a1 = a0.append(". Build Date: ");
        String s0 = this.buildDate;
        StringBuilder a2 = a1.append(s0);
        StringBuilder a3 = a2.append(" (");
        String s1 = this.buildBy;
        StringBuilder a4 = a3.append(s1);
        StringBuilder a5 = a4.append(") \n");
        StringBuilder a6 = a5.append("Copyright (c) 1998-99 Department of Computer Science, ");
        StringBuilder a7 = a6.append("Melbourne University");
        StringBuilder a8 = a7.append("\n");
        StringBuilder a9 = a8.append("By Commercial Interactive Media ");
        StringBuilder a10 = a9.append("(http://www.cim.com.au info@cim.com.au)");
        String s2 = a10.toString();
        return s2;
    }
    
    public java.awt.Container getContainer()
    {
        java.awt.ScrollPane a = this.scrollPane;
        return a;
    }
    
    public Object getCurrentData()
    {
        Object a = this.currentData;
        Object a0 = ((com.cim.AIA.Copyable)a).copy();
        return a0;
    }
    
    public com.cim.AIA.Logger getLogger()
    {
        com.cim.AIA.AlgorithmThread a = this.thread;
        com.cim.AIA.Logger a0 = a.getLogger();
        return a0;
    }
    
    public com.cim.AIA.AlgorithmThread getThread()
    {
        com.cim.AIA.AlgorithmThread a = this.thread;
        return a;
    }
    
    public void informControlListeners(com.cim.AIA.ControlEvent a)
    {
        this.requestFocus();
        int i = a.getType();
        int i0 = 0;
        while(true)
        {
            java.util.Vector a0 = this.controlListeners;
            int i1 = a0.size();
            if(i0 >= i1)
            {
                return;
            }
            java.util.Vector a1 = this.controlListeners;
            Object a2 = a1.elementAt(i0);
            com.cim.AIA.ControlListener dummy = (com.cim.AIA.ControlListener)a2;
            switch(i){
                case 123128: {
                    ((com.cim.AIA.ControlListener)a2).controlRestart(a);
                    break;
                }
                case 123127: {
                    ((com.cim.AIA.ControlListener)a2).controlRun(a);
                    break;
                }
                case 123126: {
                    ((com.cim.AIA.ControlListener)a2).controlReset(a);
                    break;
                }
                case 123125: {
                    ((com.cim.AIA.ControlListener)a2).controlPause(a);
                    break;
                }
                case 123124: {
                    ((com.cim.AIA.ControlListener)a2).controlBack(a);
                    break;
                }
                case 123123: {
                    ((com.cim.AIA.ControlListener)a2).controlStep(a);
                    break;
                }
                default: {
                    ((com.cim.AIA.ControlListener)a2).controlOther(a);
                }
            }
            int i2 = i0 + 1;
            i0 = i2;
        }
    }
    
    public void informModeListeners(com.cim.AIA.ModeEvent a)
    {
        int i = a.getType();
        int i0 = 0;
        while(true)
        {
            java.util.Vector a0 = this.modeListeners;
            int i1 = a0.size();
            if(i0 >= i1)
            {
                return;
            }
            java.util.Vector a1 = this.modeListeners;
            Object a2 = a1.elementAt(i0);
            com.cim.AIA.ModeListener dummy = (com.cim.AIA.ModeListener)a2;
            switch(i){
                case 12358: {
                    ((com.cim.AIA.ModeListener)a2).modeQuiz(a);
                    break;
                }
                case 12357: {
                    ((com.cim.AIA.ModeListener)a2).modeSelfTest(a);
                    break;
                }
                case 12356: {
                    ((com.cim.AIA.ModeListener)a2).modeNormal(a);
                    break;
                }
                default: {
                    ((com.cim.AIA.ModeListener)a2).modeOther(a);
                }
            }
            int i2 = i0 + 1;
            i0 = i2;
        }
    }
    
    public void init()
    {
        String s = this.frameTitle;
        this.setTitle(s);
        this.initialiseMenuBar();
        java.awt.Panel a = new java.awt.Panel();
        java.awt.BorderLayout a0 = new java.awt.BorderLayout();
        a.setLayout((java.awt.LayoutManager)a0);
        java.awt.Panel a1 = new java.awt.Panel();
        java.awt.GridBagLayout a2 = new java.awt.GridBagLayout();
        java.awt.GridBagConstraints a3 = new java.awt.GridBagConstraints();
        a1.setLayout((java.awt.LayoutManager)a2);
        java.awt.Color a4 = java.awt.Color.gray;
        java.awt.Color a5 = a4.brighter();
        a1.setBackground(a5);
        this.buildConstraints(a3, 0, 0, 1, 1, 5, 100);
        java.awt.Insets a6 = new java.awt.Insets(0, 5, 5, 0);
        a3.insets = a6;
        a3.anchor = 17;
        a3.fill = 0;
        com.cim.AIA.ImageCanvas a7 = this.logoCanvas;
        a2.setConstraints((java.awt.Component)a7, a3);
        com.cim.AIA.ImageCanvas a8 = this.logoCanvas;
        java.awt.Component a9 = a1.add((java.awt.Component)a8);
        java.awt.Panel a10 = this.controlPanel;
        java.awt.Color a11 = java.awt.Color.gray;
        java.awt.Color a12 = a11.brighter();
        a10.setBackground(a12);
        this.buildConstraints(a3, 1, 0, 1, 1, 95, 100);
        java.awt.Insets a13 = new java.awt.Insets(0, 0, 0, 0);
        a3.insets = a13;
        a3.anchor = 10;
        java.awt.Panel a14 = this.controlPanel;
        a2.setConstraints((java.awt.Component)a14, a3);
        java.awt.Panel a15 = this.controlPanel;
        java.awt.Component a16 = a1.add((java.awt.Component)a15);
        this.resetButtons();
        a.add((java.awt.Component)a1, (Object)"North");
        a.addMouseMotionListener((java.awt.event.MouseMotionListener)this);
        java.awt.ScrollPane a17 = this.scrollPane;
        com.cim.AIA.AlgorithmCanvas a18 = this.canvas;
        java.awt.Component a19 = a17.add((java.awt.Component)a18);
        java.awt.ScrollPane a20 = this.scrollPane;
        a.add((java.awt.Component)a20, (Object)"Center");
        java.awt.GridBagLayout a21 = new java.awt.GridBagLayout();
        java.awt.GridBagConstraints a22 = new java.awt.GridBagConstraints();
        java.awt.Panel a23 = new java.awt.Panel();
        java.awt.Color a24 = java.awt.Color.gray;
        java.awt.Color a25 = a24.brighter();
        a23.setBackground(a25);
        a23.setLayout((java.awt.LayoutManager)a21);
        java.awt.Insets a26 = new java.awt.Insets(0, 0, 0, 0);
        a22.insets = a26;
        String s0 = this.slowerLabel;
        java.awt.Label a27 = new java.awt.Label(s0, 1);
        a21.setConstraints((java.awt.Component)a27, a22);
        java.awt.Component a28 = a23.add((java.awt.Component)a27);
        int i = this.maxSpeed;
        int i0 = i / 2;
        int i1 = this.maxSpeed;
        java.awt.Scrollbar a29 = new java.awt.Scrollbar(0, i0, 1, 1, i1);
        this.speedBar = a29;
        com.cim.AIA.Logger a30 = this.getLogger();
        java.awt.Scrollbar a31 = this.speedBar;
        int i2 = a31.getValue();
        a30.setSpeed(i2);
        java.awt.Scrollbar a32 = this.speedBar;
        com.cim.AIA.AnimationWindow$2 a33 = new com.cim.AIA.AnimationWindow$2(this);
        a32.addAdjustmentListener((java.awt.event.AdjustmentListener)a33);
        com.cim.AIA.AlgorithmThread a34 = this.thread;
        int i3 = this.maxSpeed;
        java.awt.Scrollbar a35 = this.speedBar;
        int i4 = a35.getValue();
        int i5 = i3 - i4;
        a34.setRunSleepDuration(i5);
        com.cim.AIA.AlgorithmCanvas a36 = this.canvas;
        int i6 = this.maxSpeed;
        java.awt.Scrollbar a37 = this.speedBar;
        int i7 = a37.getValue();
        int i8 = i6 - i7;
        a36.setTweenSpeed(i8);
        a22.fill = 2;
        a22.weightx = 10.0;
        java.awt.Scrollbar a38 = this.speedBar;
        a21.setConstraints((java.awt.Component)a38, a22);
        java.awt.Scrollbar a39 = this.speedBar;
        java.awt.Component a40 = a23.add((java.awt.Component)a39);
        String s1 = this.fasterLabel;
        java.awt.Label a41 = new java.awt.Label(s1, 1);
        a22.weightx = 0.0;
        a22.fill = 0;
        a21.setConstraints((java.awt.Component)a41, a22);
        java.awt.Component a42 = a23.add((java.awt.Component)a41);
        a.add((java.awt.Component)a23, (Object)"South");
        java.awt.Component a43 = this.add((java.awt.Component)a);
    }
    
    protected void initialiseAlgorithmMenuItem()
    {
        java.awt.MenuItem a = this.configuration;
        com.cim.AIA.AnimationWindow$3 a0 = new com.cim.AIA.AnimationWindow$3(this);
        a.addActionListener((java.awt.event.ActionListener)a0);
        java.awt.Menu a1 = this.algorithmMenu;
        java.awt.MenuItem a2 = this.configuration;
        java.awt.MenuItem a3 = a1.add(a2);
        java.awt.Menu a4 = this.algorithmMenu;
        a4.addSeparator();
        java.awt.MenuItem a5 = this.exit;
        com.cim.AIA.AnimationWindow$4 a6 = new com.cim.AIA.AnimationWindow$4(this);
        a5.addActionListener((java.awt.event.ActionListener)a6);
        java.awt.Menu a7 = this.algorithmMenu;
        java.awt.MenuItem a8 = this.exit;
        java.awt.MenuItem a9 = a7.add(a8);
        java.awt.MenuBar a10 = this.menuBar;
        java.awt.Menu a11 = this.algorithmMenu;
        java.awt.Menu a12 = a10.add(a11);
    }
    
    protected void initialiseDataSelectionMenuItem()
    {
        java.awt.MenuBar a = this.menuBar;
        com.cim.common.RadioMenu a0 = this.dataMenu;
        java.awt.Menu a1 = a.add((java.awt.Menu)a0);
    }
    
    protected void initialiseDefaultModeMenu()
    {
        String s = com.cim.AIA.AnimationWindow.NORMAL_MODE_LABEL;
        com.cim.AIA.AlgorithmThread a = this.thread;
        com.cim.AIA.NormalModeSelection a0 = new com.cim.AIA.NormalModeSelection(s, true, this, a);
        this.normalMode = a0;
        String s0 = com.cim.AIA.AnimationWindow.USER_PREDICTION_MODE_LABEL;
        com.cim.AIA.AlgorithmThread a1 = this.thread;
        com.cim.AIA.SelfTestModeSelection a2 = new com.cim.AIA.SelfTestModeSelection(s0, false, this, a1);
        this.selfTestMode = a2;
        com.cim.AIA.NormalModeSelection a3 = this.normalMode;
        this.addModeSelection((com.cim.AIA.ModeSelection)a3);
        com.cim.AIA.SelfTestModeSelection a4 = this.selfTestMode;
        this.addModeSelection((com.cim.AIA.ModeSelection)a4);
    }
    
    protected void initialiseHelpMenuItem()
    {
        java.awt.Menu a = this.help;
        a.addSeparator();
        java.awt.MenuItem a0 = this.about;
        com.cim.AIA.AnimationWindow$5 a1 = new com.cim.AIA.AnimationWindow$5(this);
        a0.addActionListener((java.awt.event.ActionListener)a1);
        java.awt.Menu a2 = this.help;
        java.awt.MenuItem a3 = this.about;
        java.awt.MenuItem a4 = a2.add(a3);
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
        this.initialiseHelpMenuItem();
    }
    
    protected void initialiseModeSelectionMenuItem()
    {
        java.awt.MenuBar a = this.menuBar;
        com.cim.common.RadioMenu a0 = this.modeMenu;
        java.awt.Menu a1 = a.add((java.awt.Menu)a0);
    }
    
    public void keyPressed(java.awt.event.KeyEvent a)
    {
        this.processAKeyEvent(a);
    }
    
    public void keyReleased(java.awt.event.KeyEvent a)
    {
    }
    
    public void keyTyped(java.awt.event.KeyEvent a)
    {
    }
    
    public void mouseDragged(java.awt.event.MouseEvent a)
    {
    }
    
    public void mouseMoved(java.awt.event.MouseEvent a)
    {
        this.requestFocus();
    }
    
    protected void processAKeyEvent(java.awt.event.KeyEvent a)
    {
        int i = a.getKeyCode();
        label2: {
            label1: {
                label0: {
                    if(i == 40)
                    {
                        break label0;
                    }
                    if(i != 39)
                    {
                        break label1;
                    }
                }
                int i0 = this.canStepForward?1:0;
                if(i0 == 0)
                {
                    break label1;
                }
                com.cim.AIA.ControlEvent a0 = new com.cim.AIA.ControlEvent((Object)this, 123123);
                this.informControlListeners(a0);
                com.cim.AIA.AlgorithmThread a1 = this.thread;
                a1.stepMode();
                break label2;
            }
            label3: {
                if(i == 38)
                {
                    break label3;
                }
                if(i != 37)
                {
                    break label2;
                }
            }
            this.canStepForward = true;
            com.cim.AIA.ControlEvent a2 = new com.cim.AIA.ControlEvent((Object)this, 123124);
            this.informControlListeners(a2);
            com.cim.AIA.AlgorithmThread a3 = this.thread;
            a3.backMode();
        }
    }
    
    public void processExitEvent(com.cim.AIA.ExitEvent a)
    {
        this.setVisible(false);
        this.removeMouseMotionListener((java.awt.event.MouseMotionListener)this);
        com.cim.AIA.AlgorithmCanvas a0 = this.canvas;
        a0.removeMouseMotionListener((java.awt.event.MouseMotionListener)this);
        this.removeAll();
        this.cleanUp();
        this.dispose();
    }
    
    public void processFinishEvent(com.cim.AIA.FinishEvent a)
    {
        this.canStepForward = false;
        com.cim.AIA.ControlEvent a0 = new com.cim.AIA.ControlEvent((Object)this, 123129);
        this.informControlListeners(a0);
    }
    
    public void removeControlListener(com.cim.AIA.ControlListener a)
    {
        java.util.Vector a0 = this.controlListeners;
        Object a1 = a;
        int i = a0.removeElement(a1)?1:0;
    }
    
    public void removeExitListener(com.cim.AIA.ExitListener a)
    {
        java.util.Vector a0 = this.exitListeners;
        Object a1 = a;
        int i = a0.removeElement(a1)?1:0;
    }
    
    public void removeHelpListener(com.cim.AIA.HelpListener a)
    {
        java.util.Vector a0 = this.helpListeners;
        Object a1 = a;
        int i = a0.removeElement(a1)?1:0;
        com.cim.AIA.ImageCanvas a2 = this.logoCanvas;
        java.util.Vector a3 = a2.helpListeners;
        int i0 = a3.contains(a1)?1:0;
        if(i0 != 0)
        {
            com.cim.AIA.ImageCanvas a4 = this.logoCanvas;
            a4.removeHelpListener((com.cim.AIA.HelpListener)a1);
        }
        int i1 = 0;
        while(true)
        {
            java.util.Vector a5 = this.controlButtons;
            int i2 = a5.size();
            if(i1 >= i2)
            {
                return;
            }
            java.util.Vector a6 = this.controlButtons;
            Object a7 = a6.elementAt(i1);
            com.cim.AIA.ControlButton dummy = (com.cim.AIA.ControlButton)a7;
            com.cim.AIA.ControlButton a8 = (com.cim.AIA.ControlButton)a7;
            java.util.Vector a9 = a8.helpListeners;
            int i3 = a9.contains(a1)?1:0;
            if(i3 != 0)
            {
                a8.removeHelpListener((com.cim.AIA.HelpListener)a1);
            }
            int i4 = i1 + 1;
            i1 = i4;
        }
    }
    
    public void removeModeListener(com.cim.AIA.ModeListener a)
    {
        java.util.Vector a0 = this.modeListeners;
        Object a1 = a;
        int i = a0.removeElement(a1)?1:0;
    }
    
    protected void resetButtons()
    {
        com.cim.AIA.ControlEvent a = new com.cim.AIA.ControlEvent((Object)this, 123128);
        this.informControlListeners(a);
    }
    
    protected void resetDataSelectionCheckBoxes()
    {
        this.resetButtons();
        int i = 0;
        while(true)
        {
            java.util.Vector a = this.dataSelections;
            int i0 = a.size();
            if(i >= i0)
            {
                return;
            }
            else
            {
                java.util.Vector a0 = this.dataSelections;
                Object a1 = a0.elementAt(i);
                com.cim.AIA.DataSelection dummy = (com.cim.AIA.DataSelection)a1;
                com.cim.AIA.DataSelection a2 = (com.cim.AIA.DataSelection)a1;
                a2.setState(false);
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    protected void resetModeCheckBoxes()
    {
        int i = 0;
        while(true)
        {
            java.util.Vector a = this.modeSelections;
            int i0 = a.size();
            if(i >= i0)
            {
                return;
            }
            else
            {
                java.util.Vector a0 = this.modeSelections;
                Object a1 = a0.elementAt(i);
                com.cim.AIA.ModeSelection dummy = (com.cim.AIA.ModeSelection)a1;
                com.cim.AIA.ModeSelection a2 = (com.cim.AIA.ModeSelection)a1;
                a2.setState(false);
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    public void resetThread()
    {
        this.canStepForward = true;
        com.cim.AIA.AlgorithmThread a = this.thread;
        Object a0 = this.currentData;
        a.setData((com.cim.AIA.Copyable)a0);
    }
    
    public void resetThread(boolean b, boolean b0, boolean b1, boolean b2)
    {
        this.canStepForward = true;
        int i = b?1:0;
        int i0 = b0?1:0;
        int i1 = b1?1:0;
        int i2 = b2?1:0;
        com.cim.AIA.AlgorithmThread a = this.thread;
        Object a0 = this.currentData;
        a.setData((com.cim.AIA.Copyable)a0, i != 0, i0 != 0, i1 != 0, i2 != 0);
    }
    
    public void setCurrentData(com.cim.AIA.Copyable a)
    {
        this.setCurrentData(a, true, true);
    }
    
    public void setCurrentData(com.cim.AIA.Copyable a, boolean b, boolean b0)
    {
        this.currentData = a;
        Object a0 = a;
        int i = b?1:0;
        int i0 = b0?1:0;
        com.cim.AIA.AlgorithmThread a1 = this.thread;
        a1.setData((com.cim.AIA.Copyable)a0, i != 0, i0 != 0, false, false);
    }
    
    public void windowActivated(java.awt.event.WindowEvent a)
    {
    }
    
    public void windowClosed(java.awt.event.WindowEvent a)
    {
    }
    
    public void windowClosing(java.awt.event.WindowEvent a)
    {
        this.exit();
    }
    
    public void windowDeactivated(java.awt.event.WindowEvent a)
    {
    }
    
    public void windowDeiconified(java.awt.event.WindowEvent a)
    {
    }
    
    public void windowIconified(java.awt.event.WindowEvent a)
    {
    }
    
    public void windowOpened(java.awt.event.WindowEvent a)
    {
    }
    
    abstract public String getAlgorithmName();
    
    
    static
    {
        String s = localization.Messages.getString("AnimationWindow.3");
        com.cim.AIA.AnimationWindow.DEFAULT_LABEL = s;
        String s0 = localization.Messages.getString("AnimationWindow.4");
        com.cim.AIA.AnimationWindow.SORTED_LABEL = s0;
        String s1 = localization.Messages.getString("AnimationWindow.5");
        com.cim.AIA.AnimationWindow.REVERSE_SORTED_LABEL = s1;
        String s2 = localization.Messages.getString("AnimationWindow.6");
        com.cim.AIA.AnimationWindow.SAME_LABEL = s2;
        String s3 = localization.Messages.getString("AnimationWindow.7");
        com.cim.AIA.AnimationWindow.RANDOM_LABEL = s3;
        String s4 = localization.Messages.getString("AnimationWindow.8");
        com.cim.AIA.AnimationWindow.USER_SELECTION_LABEL = s4;
        String s5 = localization.Messages.getString("AnimationWindow.9");
        com.cim.AIA.AnimationWindow.NORMAL_MODE_LABEL = s5;
        String s6 = localization.Messages.getString("AnimationWindow.10");
        com.cim.AIA.AnimationWindow.USER_PREDICTION_MODE_LABEL = s6;
        String s7 = localization.Messages.getString("AnimationWindow.11");
        com.cim.AIA.AnimationWindow.QUIZ_MODE_LABEL = s7;
        String s8 = localization.Messages.getString("AnimationWindow.12");
        com.cim.AIA.AnimationWindow.STEP_LABEL = s8;
        String s9 = localization.Messages.getString("AnimationWindow.13");
        com.cim.AIA.AnimationWindow.BACK_LABEL = s9;
        String s10 = localization.Messages.getString("AnimationWindow.14");
        com.cim.AIA.AnimationWindow.RUN_LABEL = s10;
        String s11 = localization.Messages.getString("AnimationWindow.15");
        com.cim.AIA.AnimationWindow.PAUSE_LABEL = s11;
        String s12 = localization.Messages.getString("AnimationWindow.16");
        com.cim.AIA.AnimationWindow.RESET_LABEL = s12;
        String s13 = localization.Messages.getString("AnimationWindow.17");
        com.cim.AIA.AnimationWindow.STEP_INSTRUCTIONS = s13;
        String s14 = localization.Messages.getString("AnimationWindow.18");
        com.cim.AIA.AnimationWindow.BACK_INSTRUCTIONS = s14;
        StringBuilder a = new StringBuilder();
        String s15 = localization.Messages.getString("AnimationWindow.19");
        StringBuilder a0 = a.append(s15);
        String s16 = localization.Messages.getString("AnimationWindow.20");
        StringBuilder a1 = a0.append(s16);
        String s17 = localization.Messages.getString("AnimationWindow.21");
        StringBuilder a2 = a1.append(s17);
        String s18 = a2.toString();
        com.cim.AIA.AnimationWindow.RUN_INSTRUCTIONS = s18;
        String s19 = localization.Messages.getString("AnimationWindow.22");
        com.cim.AIA.AnimationWindow.PAUSE_INSTRUCTIONS = s19;
        String s20 = localization.Messages.getString("AnimationWindow.23");
        com.cim.AIA.AnimationWindow.RESET_INSTRUCTIONS = s20;
        com.cim.AIA.AnimationWindow.JAR_FILE_NAME = "aia.jar";
    }
}