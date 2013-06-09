package com.cim.AIA;

abstract public class AlgorithmApplet extends javax.swing.JApplet implements com.cim.AIA.ExitListener {
    final public static boolean DEBUG = 0;
    private boolean doWindow;
    public String jarFileName;
    public String imageDirectory;
    protected String algorithmWindowName;
    protected String algorithmFileName;
    protected String explanationWindowName;
    protected String explanationTitle;
    protected String explanationFileName;
    protected String explanationCodeTitle;
    protected String helpWindowTitle;
    private String appletInfo;
    protected com.cim.AIA.AlgorithmWindow window;
    protected com.cim.AIA.HelpWindow helpWindow;
    protected com.cim.AIA.ExplainationWindow explainationWindow;
    protected com.cim.AIA.AnimationWindow animWindow;
    protected com.cim.AIA.Algorithm algorithm;
    protected com.cim.AIA.Logger logger;
    protected com.cim.AIA.BreakPoint breakpoint;
    
    public AlgorithmApplet()
    {
        super();
        this.jarFileName = "aia.jar";
        this.imageDirectory = "images/";
        String s = localization.Messages.getString("AlgorithmApplet.2");
        this.algorithmWindowName = s;
        this.algorithmFileName = "algorithm";
        String s0 = localization.Messages.getString("AlgorithmApplet.4");
        this.explanationWindowName = s0;
        String s1 = localization.Messages.getString("AlgorithmApplet.5");
        this.explanationTitle = s1;
        this.explanationFileName = "explanation";
        String s2 = localization.Messages.getString("AlgorithmApplet.7");
        this.explanationCodeTitle = s2;
        String s3 = localization.Messages.getString("AlgorithmApplet.8");
        this.helpWindowTitle = s3;
        this.appletInfo = "";
        com.cim.AIA.Logger a = new com.cim.AIA.Logger();
        this.logger = a;
        com.cim.AIA.BreakPoint a0 = new com.cim.AIA.BreakPoint();
        this.breakpoint = a0;
    }
    
    public void cleanUp()
    {
        this.window = null;
        this.helpWindow = null;
        this.explainationWindow = null;
        this.animWindow = null;
        this.logger = null;
        com.cim.AIA.Algorithm a = this.algorithm;
        a.cleanUp();
        this.algorithm = null;
        com.cim.AIA.BreakPoint a0 = this.breakpoint;
        if(a0 != null)
        {
            com.cim.AIA.BreakPoint a1 = this.breakpoint;
            a1.cleanUp();
        }
        this.breakpoint = null;
    }
    
    public void destroy()
    {
        java.io.PrintStream a = System.out;
        a.println("Destroyed Called");
        com.cim.AIA.AnimationWindow a0 = this.animWindow;
        if(a0 == null)
        {
            java.io.PrintStream a1 = System.err;
            a1.println("Warning: animWindow null in AlgorithmApplet.destroy()");
        }
        else
        {
            com.cim.AIA.AnimationWindow a2 = this.animWindow;
            a2.exit();
        }
    }
    
    public void displayWindowLocations()
    {
        java.io.PrintStream a = System.out;
        StringBuilder a0 = new StringBuilder();
        StringBuilder a1 = a0.append("Animation Window: ");
        com.cim.AIA.AnimationWindow a2 = this.animWindow;
        java.awt.Point a3 = a2.getLocation();
        int i = a3.x;
        StringBuilder a4 = a1.append(i);
        StringBuilder a5 = a4.append(",");
        com.cim.AIA.AnimationWindow a6 = this.animWindow;
        java.awt.Point a7 = a6.getLocation();
        int i0 = a7.y;
        StringBuilder a8 = a5.append(i0);
        StringBuilder a9 = a8.append(" size: ");
        com.cim.AIA.AnimationWindow a10 = this.animWindow;
        java.awt.Dimension a11 = a10.getSize();
        int i1 = a11.width;
        StringBuilder a12 = a9.append(i1);
        StringBuilder a13 = a12.append(" ");
        com.cim.AIA.AnimationWindow a14 = this.animWindow;
        java.awt.Dimension a15 = a14.getSize();
        int i2 = a15.height;
        StringBuilder a16 = a13.append(i2);
        String s = a16.toString();
        a.println(s);
        java.io.PrintStream a17 = System.out;
        StringBuilder a18 = new StringBuilder();
        StringBuilder a19 = a18.append("Explanation Window: ");
        com.cim.AIA.ExplainationWindow a20 = this.explainationWindow;
        java.awt.Point a21 = a20.getLocation();
        int i3 = a21.x;
        StringBuilder a22 = a19.append(i3);
        StringBuilder a23 = a22.append(",");
        com.cim.AIA.ExplainationWindow a24 = this.explainationWindow;
        java.awt.Point a25 = a24.getLocation();
        int i4 = a25.y;
        StringBuilder a26 = a23.append(i4);
        StringBuilder a27 = a26.append(" size: ");
        com.cim.AIA.ExplainationWindow a28 = this.explainationWindow;
        java.awt.Dimension a29 = a28.getSize();
        int i5 = a29.width;
        StringBuilder a30 = a27.append(i5);
        StringBuilder a31 = a30.append(" ");
        com.cim.AIA.ExplainationWindow a32 = this.explainationWindow;
        java.awt.Dimension a33 = a32.getSize();
        int i6 = a33.height;
        StringBuilder a34 = a31.append(i6);
        String s0 = a34.toString();
        a17.println(s0);
        java.io.PrintStream a35 = System.out;
        StringBuilder a36 = new StringBuilder();
        StringBuilder a37 = a36.append("Help Window: ");
        com.cim.AIA.HelpWindow a38 = this.helpWindow;
        java.awt.Point a39 = a38.getLocation();
        int i7 = a39.x;
        StringBuilder a40 = a37.append(i7);
        StringBuilder a41 = a40.append(",");
        com.cim.AIA.HelpWindow a42 = this.helpWindow;
        java.awt.Point a43 = a42.getLocation();
        int i8 = a43.y;
        StringBuilder a44 = a41.append(i8);
        StringBuilder a45 = a44.append(" size: ");
        com.cim.AIA.HelpWindow a46 = this.helpWindow;
        java.awt.Dimension a47 = a46.getSize();
        int i9 = a47.width;
        StringBuilder a48 = a45.append(i9);
        StringBuilder a49 = a48.append(" ");
        com.cim.AIA.HelpWindow a50 = this.helpWindow;
        java.awt.Dimension a51 = a50.getSize();
        int i10 = a51.height;
        StringBuilder a52 = a49.append(i10);
        String s1 = a52.toString();
        a35.println(s1);
        java.io.PrintStream a53 = System.out;
        StringBuilder a54 = new StringBuilder();
        StringBuilder a55 = a54.append("Algorithm Window: ");
        com.cim.AIA.AlgorithmWindow a56 = this.window;
        java.awt.Point a57 = a56.getLocation();
        int i11 = a57.x;
        StringBuilder a58 = a55.append(i11);
        StringBuilder a59 = a58.append(",");
        com.cim.AIA.AlgorithmWindow a60 = this.window;
        java.awt.Point a61 = a60.getLocation();
        int i12 = a61.y;
        StringBuilder a62 = a59.append(i12);
        StringBuilder a63 = a62.append(" size: ");
        com.cim.AIA.AlgorithmWindow a64 = this.window;
        java.awt.Dimension a65 = a64.getSize();
        int i13 = a65.width;
        StringBuilder a66 = a63.append(i13);
        StringBuilder a67 = a66.append(" ");
        com.cim.AIA.AlgorithmWindow a68 = this.window;
        java.awt.Dimension a69 = a68.getSize();
        int i14 = a69.height;
        StringBuilder a70 = a67.append(i14);
        String s2 = a70.toString();
        a53.println(s2);
    }
    
    public void displayWindows()
    {
        int i = this.doWindow?1:0;
        if(i == 0)
        {
            com.cim.AIA.AlgorithmWindow a = this.window;
            a.setVisible(true);
            com.cim.AIA.AlgorithmWindow a0 = this.window;
            a0.openAll();
            com.cim.AIA.AlgorithmWindow a1 = this.window;
            a1.setSize(10, 10);
            com.cim.AIA.AlgorithmWindow a2 = this.window;
            a2.toBack();
            com.cim.AIA.AlgorithmWindow a3 = this.window;
            a3.setResizable(false);
        }
        else
        {
            com.cim.AIA.ExplainationWindow a4 = this.explainationWindow;
            a4.setVisible(true);
            com.cim.AIA.AlgorithmWindow a5 = this.window;
            a5.setVisible(true);
        }
        com.cim.AIA.HelpWindow a6 = this.helpWindow;
        a6.setVisible(true);
        com.cim.AIA.AnimationWindow a7 = this.animWindow;
        a7.setVisible(true);
    }
    
    abstract public com.cim.AIA.AlgorithmCanvas getAlgorithmCanvas();
    
    
    abstract public com.cim.AIA.AlgorithmThread getAlgorithmThread(com.cim.AIA.Copyable arg, com.cim.AIA.AlgorithmWindow arg0);
    
    
    protected com.cim.AIA.AlgorithmWindow getAlgorithmWindow(String s, com.cim.AIA.CodeCanvas a)
    {
        com.cim.AIA.AlgorithmWindow a0 = new com.cim.AIA.AlgorithmWindow(s, a);
        return a0;
    }
    
    abstract public com.cim.AIA.AnimationWindow getAnimationWindow(com.cim.AIA.AlgorithmCanvas arg, com.cim.AIA.AlgorithmThread arg0, java.applet.Applet arg1);
    
    
    final public String getAppletInfo()
    {
        String s = this.appletInfo;
        return s;
    }
    
    public com.cim.AIA.BreakPoint getBreakPoint()
    {
        com.cim.AIA.BreakPoint a = this.breakpoint;
        return a;
    }
    
    abstract public com.cim.AIA.Copyable getInitialData();
    
    
    public com.cim.AIA.Logger getLogger()
    {
        com.cim.AIA.Logger a = this.logger;
        return a;
    }
    
    final public void init()
    {
        int i = 0;
        int i0 = 0;
        int i1 = 0;
        com.cim.AIA.ConfigurationManager.reset();
        String s = this.getParameter("JARFILE");
        if(s != null)
        {
            this.jarFileName = s;
        }
        String s0 = this.getParameter("NOLOG");
        label2: {
            label1: {
                label0: {
                    if(s0 == null)
                    {
                        break label0;
                    }
                    int i2 = s0.equals((Object)"true")?1:0;
                    if(i2 != 0)
                    {
                        break label1;
                    }
                }
                i = 1;
                break label2;
            }
            i = 0;
        }
        String s1 = this.getParameter("NOEXP");
        label5: {
            label4: {
                label3: {
                    if(s1 == null)
                    {
                        break label3;
                    }
                    int i3 = s1.equals((Object)"true")?1:0;
                    if(i3 != 0)
                    {
                        break label4;
                    }
                }
                this.doWindow = true;
                break label5;
            }
            this.doWindow = false;
        }
        String s2 = this.getParameter("NOSTEP");
        label6: {
            if(s2 == null)
            {
                break label6;
            }
            int i4 = s2.equals((Object)"true")?1:0;
            if(i4 != 0)
            {
                com.cim.AIA.StepButton.allDisable(true);
            }
        }
        String s3 = this.getParameter("NOBACK");
        label7: {
            if(s3 == null)
            {
                break label7;
            }
            int i5 = s3.equals((Object)"true")?1:0;
            if(i5 != 0)
            {
                com.cim.AIA.BackButton.allDisable(true);
            }
        }
        String s4 = this.getParameter("NOTRACE");
        label8: {
            if(s4 == null)
            {
                break label8;
            }
            int i6 = s4.equals((Object)"true")?1:0;
            if(i6 == 0)
            {
                break label8;
            }
            int i7 = com.cim.AIA.Logger.DEBUG?1:0;
            if(i7 != 0)
            {
                java.io.PrintStream a = System.err;
                a.println("Turning tracing off.");
            }
            com.cim.AIA.Logger a0 = this.logger;
            a0.setDumpTrace(false);
        }
        String s5 = this.getParameter("OUTPUTMODE");
        if(s5 != null)
        {
            com.cim.AIA.Logger a1 = this.logger;
            a1.setOutputMode(s5);
        }
        String s6 = this.getParameter("CGISCRIPT");
        if(s6 != null)
        {
            com.cim.AIA.Logger a2 = this.logger;
            a2.setCGIScript(s6);
        }
        String s7 = this.getParameter("LOGDIR");
        if(s7 != null)
        {
            com.cim.AIA.Logger a3 = this.logger;
            a3.setLogDir(s7);
        }
        String s8 = this.getParameter("LOGADDINFO");
        if(s8 != null)
        {
            int i8 = Boolean.getBoolean(s8)?1:0;
            com.cim.AIA.Logger.setLogAddInfo(i8 != 0);
        }
        String s9 = this.getParameter("BUFFERSIZE");
        label9: {
            int i9 = 0;
            if(s9 == null)
            {
                break label9;
            }
            try
            {
                int i10 = Integer.parseInt(s9);
                i9 = i10;
            }
            catch(NumberFormatException ignoredException)
            {
                java.io.PrintStream a4 = System.out;
                a4.println("BUFFERSIZE parameter wrong format");
                i9 = -1;
            }
            if(i9 != -1)
            {
                com.cim.AIA.Logger a5 = this.logger;
                a5.setBufferSize(i9);
            }
        }
        String s10 = this.getParameter("EXTRA");
        this.parseParameter(s10);
        String s11 = this.getParameter("COOKIES");
        label12: {
            label11: {
                label10: {
                    if(s11 == null)
                    {
                        break label10;
                    }
                    int i11 = s11.equals((Object)"true")?1:0;
                    if(i11 != 0)
                    {
                        break label11;
                    }
                }
                i0 = 0;
                break label12;
            }
            i0 = 1;
        }
        String s12 = this.getParameter("CUSER");
        String s13 = this.getParameter("NOBREAKPOINT");
        label15: {
            label14: {
                label13: {
                    if(s13 == null)
                    {
                        break label13;
                    }
                    int i12 = s13.equals((Object)"true")?1:0;
                    if(i12 != 0)
                    {
                        break label14;
                    }
                }
                com.cim.AIA.BreakPoint a6 = this.breakpoint;
                a6.enabled = true;
                break label15;
            }
            com.cim.AIA.BreakPoint a7 = this.breakpoint;
            a7.enabled = false;
        }
        String s14 = this.getParameter("ULOG");
        label18: {
            label17: {
                label16: {
                    if(s14 == null)
                    {
                        break label16;
                    }
                    int i13 = s14.equals((Object)"true")?1:0;
                    if(i13 != 0)
                    {
                        break label17;
                    }
                }
                i1 = 0;
                break label18;
            }
            i1 = 1;
        }
        String s15 = this.getParameter("CID");
        label21: {
            String s16 = null;
            java.net.URL a8 = null;
            label19: {
                if(s12 != null)
                {
                    s16 = s12;
                    break label19;
                }
                label20: {
                    if(i0 != 0)
                    {
                        break label20;
                    }
                    java.io.PrintStream a9 = System.out;
                    a9.println("AiA WARNING: Cookies are not enabled, cannot determine user");
                    java.io.PrintStream a10 = System.out;
                    a10.println("             Assuming user=aia");
                    s16 = "aia";
                    break label19;
                }
                java.io.PrintStream a11 = System.out;
                a11.println("AiA ERROR: Cookies must be enabled for this module to operate");
                java.io.PrintStream a12 = System.out;
                a12.println("           Please enable cookies and go to the login screen again");
                break label21;
            }
            java.awt.Frame a13 = new java.awt.Frame();
            String s17 = localization.Messages.getString("AlgorithmApplet.43");
            String s18 = localization.Messages.getString("AlgorithmApplet.44");
            com.cim.AIA.ProgressDialog a14 = new com.cim.AIA.ProgressDialog(a13, s17, s18, false, 10, 1);
            a14.setSize(300, 130);
            java.awt.Toolkit a15 = a13.getToolkit();
            java.awt.Dimension a16 = a15.getScreenSize();
            int i14 = a16.width;
            java.awt.Dimension a17 = a14.getSize();
            int i15 = a17.width;
            int i16 = i14 - i15;
            int i17 = i16 / 2;
            int i18 = a16.height;
            java.awt.Dimension a18 = a14.getSize();
            int i19 = a18.height;
            int i20 = i18 - i19;
            int i21 = i20 / 2;
            a14.setLocation(i17, i21);
            a14.setVisible(true);
            String s19 = this.helpWindowTitle;
            com.cim.AIA.HelpWindow a19 = new com.cim.AIA.HelpWindow(s19);
            this.helpWindow = a19;
            com.cim.AIA.HelpWindow a20 = this.helpWindow;
            a20.setSize(300, 150);
            a14.setProgressMark(1);
            java.net.URL a21 = this.getDocumentBase();
            String s20 = a21.toString();
            int i22 = s20.length();
            int i23 = i22 - 1;
            int i24 = i23;
            while(true)
            {
                int i25 = s20.charAt(i24);
                if(i25 == 47)
                {
                    break;
                }
                else
                {
                    int i26 = i24 + -1;
                    i24 = i26;
                }
            }
            String s21 = s20.substring(0, i24);
            StringBuilder a22 = new StringBuilder();
            StringBuilder a23 = a22.append(s21);
            StringBuilder a24 = a23.append("/");
            String s22 = a24.toString();
            try
            {
                java.net.URL a25 = new java.net.URL(s22);
                a8 = a25;
            }
            catch(Exception ignoredException0)
            {
                a8 = a21;
            }
            java.net.URL a26 = this.getCodeBase();
            String s23 = a26.toString();
            String s24 = this.algorithmFileName;
            StringBuilder a27 = new StringBuilder();
            String s25 = a8.toString();
            StringBuilder a28 = a27.append(s25);
            String s26 = this.imageDirectory;
            StringBuilder a29 = a28.append(s26);
            String s27 = a29.toString();
            com.cim.AIA.CodeCanvas a30 = new com.cim.AIA.CodeCanvas((java.applet.Applet)this, s23, s24, s27);
            a14.setProgressMark(2);
            String s28 = this.algorithmWindowName;
            com.cim.AIA.AlgorithmWindow a31 = this.getAlgorithmWindow(s28, a30);
            this.window = a31;
            com.cim.AIA.AlgorithmWindow a32 = this.window;
            com.cim.AIA.Logger a33 = this.logger;
            a32.setLogger(a33);
            com.cim.AIA.AlgorithmWindow a34 = this.window;
            com.cim.AIA.BreakPoint a35 = this.breakpoint;
            a34.setBreakPoint(a35);
            com.cim.AIA.AlgorithmWindow a36 = this.window;
            com.cim.AIA.HelpWindow a37 = this.helpWindow;
            a36.addHelpListener((com.cim.AIA.HelpListener)a37);
            com.cim.AIA.AlgorithmWindow a38 = this.window;
            a38.setSize(400, 600);
            a14.setProgressMark(3);
            Object a39 = this.getInitialData();
            com.cim.AIA.AlgorithmWindow a40 = this.window;
            com.cim.AIA.AlgorithmThread a41 = this.getAlgorithmThread((com.cim.AIA.Copyable)a39, a40);
            com.cim.AIA.Logger a42 = this.logger;
            a41.setLogger(a42);
            com.cim.AIA.BreakPoint a43 = this.breakpoint;
            a41.setBreakPoint(a43);
            com.cim.AIA.Algorithm a44 = a41.getAlgorithm();
            this.algorithm = a44;
            a14.setProgressMark(4);
            com.cim.AIA.AlgorithmCanvas a45 = this.getAlgorithmCanvas();
            a14.setProgressMark(5);
            a41.addQuestionable((com.cim.AIA.Questionable)a45);
            String s29 = this.explanationWindowName;
            String s30 = this.explanationTitle;
            String s31 = this.explanationCodeTitle;
            com.cim.AIA.ExplainationWindow a46 = new com.cim.AIA.ExplainationWindow(s29, s30, s31, (java.applet.Applet)this);
            this.explainationWindow = a46;
            a14.setProgressMark(6);
            com.cim.AIA.ExplainationWindow a47 = this.explainationWindow;
            java.net.URL a48 = this.getCodeBase();
            String s32 = a48.toString();
            String s33 = this.explanationFileName;
            a47.initialiseMainData(s32, s33);
            com.cim.AIA.ExplainationWindow a49 = this.explainationWindow;
            com.cim.AIA.HelpWindow a50 = this.helpWindow;
            a49.addHelpListener((com.cim.AIA.HelpListener)a50);
            a14.setProgressMark(7);
            com.cim.AIA.AnimationWindow a51 = this.getAnimationWindow(a45, a41, (java.applet.Applet)this);
            this.animWindow = a51;
            com.cim.AIA.Logger a52 = this.logger;
            a52.setEnabled(i != 0);
            java.util.Locale a53 = localization.Messages.getActiveLocale();
            String s34 = a53.getLanguage();
            StringBuilder a54 = new StringBuilder();
            StringBuilder a55 = a54.append(s16);
            StringBuilder a56 = a55.append("@");
            StringBuilder a57 = a56.append(s34);
            String s35 = a57.toString();
            label23: {
                label22: {
                    if(i1 != 0)
                    {
                        break label22;
                    }
                    int i27 = s16.startsWith("[regstud]")?1:0;
                    if(i27 != 0)
                    {
                        break label22;
                    }
                    com.cim.AIA.Logger a58 = this.logger;
                    com.cim.AIA.AnimationWindow a59 = this.animWindow;
                    String s36 = a59.getAlgorithmName();
                    a58.beginGuestLogging(s35, s36);
                    break label23;
                }
                com.cim.AIA.Logger a60 = this.logger;
                com.cim.AIA.AnimationWindow a61 = this.animWindow;
                String s37 = a61.getAlgorithmName();
                a60.beginLogging(s37, s35, s15);
            }
            com.cim.AIA.BreakPoint a62 = this.breakpoint;
            com.cim.AIA.AlgorithmWindow a63 = this.window;
            com.cim.AIA.AnimationWindow a64 = this.animWindow;
            a62.initialise(a41, a63, a64);
            com.cim.AIA.AnimationWindow a65 = this.animWindow;
            com.cim.AIA.HelpWindow a66 = this.helpWindow;
            a65.addHelpListener((com.cim.AIA.HelpListener)a66);
            com.cim.AIA.AnimationWindow a67 = this.animWindow;
            com.cim.AIA.ExplainationWindow a68 = this.explainationWindow;
            a67.addModeListener((com.cim.AIA.ModeListener)a68);
            com.cim.AIA.AnimationWindow a69 = this.animWindow;
            a69.addModeListener((com.cim.AIA.ModeListener)a41);
            com.cim.AIA.AlgorithmWindow a70 = this.window;
            int i28 = a70 instanceof com.cim.AIA.ControlListener?1:0;
            if(i28 != 0)
            {
                com.cim.AIA.AnimationWindow a71 = this.animWindow;
                com.cim.AIA.AlgorithmWindow a72 = this.window;
                com.cim.AIA.ControlListener dummy = (com.cim.AIA.ControlListener)a72;
                com.cim.AIA.ControlListener dummy0 = (com.cim.AIA.ControlListener)a72;
                a71.addControlListener((com.cim.AIA.ControlListener)a72);
            }
            com.cim.AIA.AnimationWindow a73 = this.animWindow;
            com.cim.AIA.Question.setAnimationWindow(a73);
            com.cim.AIA.AnimationWindow a74 = this.animWindow;
            java.awt.Container a75 = a74.getContainer();
            a45.setParent(a75);
            com.cim.AIA.AlgorithmWindow a76 = this.window;
            com.cim.AIA.AnimationWindow a77 = this.animWindow;
            a76.addKeyListener((java.awt.event.KeyListener)a77);
            com.cim.AIA.AnimationWindow a78 = this.animWindow;
            a30.addKeyListener((java.awt.event.KeyListener)a78);
            com.cim.AIA.AlgorithmWindow a79 = this.window;
            com.cim.AIA.AnimationWindow a80 = this.animWindow;
            a79.addWindowListener((java.awt.event.WindowListener)a80);
            com.cim.AIA.AnimationWindow a81 = this.animWindow;
            a81.setSize(600, 600);
            com.cim.AIA.AnimationWindow a82 = this.animWindow;
            a82.init();
            a14.setProgressMark(8);
            com.cim.AIA.AnimationWindow a83 = this.animWindow;
            String s38 = a83.getBuildInfo();
            this.appletInfo = s38;
            com.cim.AIA.AnimationWindow a84 = this.animWindow;
            a41.addFinishListener((com.cim.AIA.FinishListener)a84);
            com.cim.AIA.AlgorithmWindow a85 = this.window;
            com.cim.AIA.ExplainationWindow a86 = this.explainationWindow;
            a85.addExplainationListener((com.cim.AIA.ExplainationListener)a86);
            com.cim.AIA.ExplainationWindow a87 = this.explainationWindow;
            a87.setSize(250, 600);
            a41.addRepaintListener((com.cim.AIA.RepaintListener)a45);
            com.cim.AIA.AnimationWindow a88 = this.animWindow;
            a88.addExitListener((com.cim.AIA.ExitListener)this);
            com.cim.AIA.AnimationWindow a89 = this.animWindow;
            com.cim.AIA.HelpWindow a90 = this.helpWindow;
            a89.addExitListener((com.cim.AIA.ExitListener)a90);
            com.cim.AIA.AnimationWindow a91 = this.animWindow;
            com.cim.AIA.ExplainationWindow a92 = this.explainationWindow;
            a91.addExitListener((com.cim.AIA.ExitListener)a92);
            com.cim.AIA.AnimationWindow a93 = this.animWindow;
            com.cim.AIA.AlgorithmWindow a94 = this.window;
            a93.addExitListener((com.cim.AIA.ExitListener)a94);
            com.cim.AIA.AnimationWindow a95 = this.animWindow;
            com.cim.AIA.AnimationWindow a96 = this.animWindow;
            a95.addExitListener((com.cim.AIA.ExitListener)a96);
            com.cim.AIA.AnimationWindow a97 = this.animWindow;
            a97.addExitListener((com.cim.AIA.ExitListener)a41);
            a14.setProgressMark(9);
            this.otherInitialisation();
            this.initialiseConfiguration();
            a41.start();
            com.cim.AIA.AnimationWindow a98 = this.animWindow;
            Object a99 = this.getInitialData();
            a98.setCurrentData((com.cim.AIA.Copyable)a99);
            a14.setProgressMark(10);
            this.setLocationAndSize();
            a14.setVisible(false);
            this.displayWindows();
            a14.dispose();
            com.cim.AIA.AnimationWindow a100 = this.animWindow;
            a100.requestFocus();
        }
    }
    
    protected void initialiseConfiguration()
    {
        com.cim.AIA.ConfigurationManager a = com.cim.AIA.ConfigurationManager.getConfigurationManager();
        java.awt.Color a0 = java.awt.Color.white;
        String s = com.cim.AIA.ColorSelection.BACKGROUND;
        com.cim.AIA.ColorSelection a1 = new com.cim.AIA.ColorSelection(a0, s);
        a1.appliesToAll();
        a.addColorSelection(a1);
        java.awt.Color a2 = java.awt.Color.black;
        String s0 = com.cim.AIA.ColorSelection.TEXT_COLOR;
        com.cim.AIA.ColorSelection a3 = new com.cim.AIA.ColorSelection(a2, s0);
        a3.appliesToAll();
        a.addColorSelection(a3);
        String s1 = localization.Messages.getString("AlgorithmApplet.1");
        java.awt.Font a4 = new java.awt.Font(s1, 0, 12);
        com.cim.AIA.FontSelection a5 = new com.cim.AIA.FontSelection(a4, "Normal Font");
        a5.appliesToAll();
        a.addFontSelection(a5);
    }
    
    protected void otherInitialisation()
    {
    }
    
    public void parseParameter(String s)
    {
    }
    
    public void processExitEvent(com.cim.AIA.ExitEvent a)
    {
        com.cim.AIA.Logger a0 = this.logger;
        if(a0 != null)
        {
            com.cim.AIA.Logger a1 = this.logger;
            a1.endLogging();
        }
        this.setVisible(false);
        this.stop();
        this.removeAll();
        this.cleanUp();
    }
    
    abstract protected void setLocationAndSize();
}