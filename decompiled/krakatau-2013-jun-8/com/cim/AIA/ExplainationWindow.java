package com.cim.AIA;

public class ExplainationWindow extends java.awt.Frame implements com.cim.AIA.Helpable, com.cim.AIA.ExplainationListener, com.cim.AIA.ExitListener, com.cim.AIA.ModeListener, com.cim.AIA.ColorSelectionListener, com.cim.AIA.FontSelectionListener {
    final private static long serialVersionUID = -1482890918509768301L;
    final protected static String LOGO_IMAGE_NAME = "logo.gif";
    final protected static String TITLE_IMAGE_NAME = "title.gif";
    protected static String JAR_FILE_NAME;
    protected static String imgDir;
    protected java.awt.TextArea basicInfoTextArea;
    protected java.awt.TextArea infoTextArea;
    protected java.awt.ScrollPane sp;
    protected java.awt.Panel mainPanel;
    protected java.awt.Label label1;
    protected java.awt.Label label2;
    protected java.applet.Applet applet;
    protected java.awt.Image logoImage;
    protected java.awt.Image titleImage;
    protected com.cim.AIA.ImageCanvas logoCanvas;
    protected com.cim.AIA.ImageCanvas titleCanvas;
    
    public ExplainationWindow(String s, String s0, String s1)
    {
        super(s);
        this.logoImage = null;
        this.titleImage = null;
        java.awt.Color a = java.awt.Color.white;
        this.setBackground(a);
        java.awt.ScrollPane a0 = new java.awt.ScrollPane(0);
        this.sp = a0;
        java.awt.ScrollPane a1 = this.sp;
        java.awt.Component a2 = this.add((java.awt.Component)a1);
        java.awt.Panel a3 = new java.awt.Panel();
        java.awt.ScrollPane a4 = this.sp;
        java.awt.Component a5 = a4.add((java.awt.Component)a3);
        java.awt.GridBagLayout a6 = new java.awt.GridBagLayout();
        a3.setLayout((java.awt.LayoutManager)a6);
        java.awt.GridBagConstraints a7 = new java.awt.GridBagConstraints();
        a7.gridwidth = 0;
        a7.anchor = 10;
        a7.fill = 1;
        a7.ipadx = 0;
        a7.ipady = 0;
        a7.weightx = 0.0;
        a7.weighty = 0.0;
        java.awt.Font a8 = new java.awt.Font("Courier", 1, 12);
        java.awt.Label a9 = new java.awt.Label(s0, 0);
        this.label1 = a9;
        java.awt.Label a10 = this.label1;
        a10.setFont(a8);
        java.awt.Label a11 = this.label1;
        a6.setConstraints((java.awt.Component)a11, a7);
        java.awt.Label a12 = this.label1;
        java.awt.Component a13 = a3.add((java.awt.Component)a12);
        a7.weightx = 4.0;
        a7.weighty = 4.0;
        java.awt.TextArea a14 = new java.awt.TextArea("", 15, 30, 1);
        this.basicInfoTextArea = a14;
        java.awt.TextArea a15 = new java.awt.TextArea("", 5, 30, 1);
        this.infoTextArea = a15;
        java.awt.TextArea a16 = this.basicInfoTextArea;
        a16.setEditable(false);
        java.awt.TextArea a17 = this.infoTextArea;
        a17.setEditable(false);
        java.awt.TextArea a18 = this.basicInfoTextArea;
        a6.setConstraints((java.awt.Component)a18, a7);
        java.awt.TextArea a19 = this.basicInfoTextArea;
        java.awt.Component a20 = a3.add((java.awt.Component)a19);
        a7.weightx = 0.0;
        a7.weighty = 0.0;
        java.awt.Label a21 = new java.awt.Label(s1, 0);
        this.label2 = a21;
        java.awt.Label a22 = this.label2;
        a22.setFont(a8);
        java.awt.Label a23 = this.label2;
        a6.setConstraints((java.awt.Component)a23, a7);
        java.awt.Label a24 = this.label2;
        java.awt.Component a25 = a3.add((java.awt.Component)a24);
        a7.weightx = 3.0;
        a7.weighty = 3.0;
        java.awt.TextArea a26 = this.infoTextArea;
        a6.setConstraints((java.awt.Component)a26, a7);
        java.awt.TextArea a27 = this.infoTextArea;
        java.awt.Component a28 = a3.add((java.awt.Component)a27);
        com.cim.AIA.ConfigurationManager a29 = com.cim.AIA.ConfigurationManager.getConfigurationManager();
        a29.addColorSelectionListener((com.cim.AIA.ColorSelectionListener)this);
        com.cim.AIA.ConfigurationManager a30 = com.cim.AIA.ConfigurationManager.getConfigurationManager();
        a30.addFontSelectionListener((com.cim.AIA.FontSelectionListener)this);
    }
    
    public ExplainationWindow(String s, String s0, String s1, java.applet.Applet a)
    {
        java.net.URL a0 = null;
        super(s);
        this.logoImage = null;
        this.titleImage = null;
        java.awt.MediaTracker a1 = new java.awt.MediaTracker((java.awt.Component)this);
        java.net.URL a2 = a.getDocumentBase();
        String s2 = a2.toString();
        int i = s2.length();
        int i0 = i - 1;
        int i1 = i0;
        while(true)
        {
            int i2 = s2.charAt(i1);
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
        String s3 = s2.substring(0, i1);
        StringBuilder a3 = new StringBuilder();
        StringBuilder a4 = a3.append(s3);
        StringBuilder a5 = a4.append("/");
        String s4 = a5.toString();
        try
        {
            java.net.URL a6 = new java.net.URL(s4);
            a0 = a6;
        }
        catch(Exception ignoredException)
        {
            a0 = a2;
        }
        com.cim.AIA.AlgorithmApplet dummy = (com.cim.AIA.AlgorithmApplet)a;
        com.cim.AIA.AlgorithmApplet a7 = (com.cim.AIA.AlgorithmApplet)a;
        label43: {
            label70: {
                java.net.MalformedURLException a8 = null;
                label1: {
                    String s5 = null;
                    StringBuilder a9 = null;
                    String s6 = null;
                    StringBuilder a10 = null;
                    String s7 = null;
                    StringBuilder a11 = null;
                    String s8 = null;
                    Class a12 = null;
                    java.io.InputStream a13 = null;
                    StringBuilder a14 = null;
                    java.net.URL a15 = null;
                    StringBuilder a16 = null;
                    StringBuilder a17 = null;
                    StringBuilder a18 = null;
                    String s9 = null;
                    java.net.URL a19 = null;
                    java.awt.Image a20 = null;
                    java.awt.Image a21 = null;
                    com.cim.AIA.logoProcessor a22 = null;
                    Class a23 = null;
                    java.io.InputStream a24 = null;
                    int i4 = 0;
                    java.net.URL a25 = null;
                    java.awt.Image a26 = null;
                    java.awt.Image a27 = null;
                    Class a28 = null;
                    java.io.InputStream a29 = null;
                    StringBuilder a30 = null;
                    java.net.URL a31 = null;
                    StringBuilder a32 = null;
                    StringBuilder a33 = null;
                    StringBuilder a34 = null;
                    String s10 = null;
                    java.net.URL a35 = null;
                    java.awt.Image a36 = null;
                    java.awt.Image a37 = null;
                    Class a38 = null;
                    java.io.InputStream a39 = null;
                    com.cim.AIA.titleProcessor a40 = null;
                    int i5 = 0;
                    java.net.URL a41 = null;
                    java.awt.Image a42 = null;
                    StringBuilder a43 = null;
                    StringBuilder a44 = null;
                    StringBuilder a45 = null;
                    String s11 = null;
                    java.io.PrintStream a46 = null;
                    StringBuilder a47 = null;
                    StringBuilder a48 = null;
                    StringBuilder a49 = null;
                    String s12 = null;
                    label0: try
                    {
                        s5 = a7.imageDirectory;
                        break label0;
                    }
                    catch(java.net.MalformedURLException a50)
                    {
                        a8 = a50;
                        break label1;
                    }
                    label2: try
                    {
                        com.cim.AIA.ExplainationWindow.imgDir = s5;
                        break label2;
                    }
                    catch(java.net.MalformedURLException a51)
                    {
                        a8 = a51;
                        break label1;
                    }
                    label3: try
                    {
                        a9 = new StringBuilder();
                        break label3;
                    }
                    catch(java.net.MalformedURLException a52)
                    {
                        a8 = a52;
                        break label1;
                    }
                    label4: try
                    {
                        s6 = a0.toString();
                        break label4;
                    }
                    catch(java.net.MalformedURLException a53)
                    {
                        a8 = a53;
                        break label1;
                    }
                    label5: try
                    {
                        a10 = a9.append(s6);
                        break label5;
                    }
                    catch(java.net.MalformedURLException a54)
                    {
                        a8 = a54;
                        break label1;
                    }
                    label6: try
                    {
                        s7 = com.cim.AIA.ExplainationWindow.imgDir;
                        break label6;
                    }
                    catch(java.net.MalformedURLException a55)
                    {
                        a8 = a55;
                        break label1;
                    }
                    label7: try
                    {
                        a11 = a10.append(s7);
                        break label7;
                    }
                    catch(java.net.MalformedURLException a56)
                    {
                        a8 = a56;
                        break label1;
                    }
                    label8: try
                    {
                        s8 = a11.toString();
                        break label8;
                    }
                    catch(java.net.MalformedURLException a57)
                    {
                        a8 = a57;
                        break label1;
                    }
                    label9: try
                    {
                        com.cim.AIA.ExplainationWindow.imgDir = s8;
                        break label9;
                    }
                    catch(java.net.MalformedURLException a58)
                    {
                        a8 = a58;
                        break label1;
                    }
                    label10: try
                    {
                        a12 = ((Object)a).getClass();
                        break label10;
                    }
                    catch(java.net.MalformedURLException a59)
                    {
                        a8 = a59;
                        break label1;
                    }
                    label11: try
                    {
                        a13 = a12.getResourceAsStream("images/logo.gif");
                        break label11;
                    }
                    catch(java.net.MalformedURLException a60)
                    {
                        a8 = a60;
                        break label1;
                    }
                    label12: try
                    {
                        a14 = new StringBuilder();
                        break label12;
                    }
                    catch(java.net.MalformedURLException a61)
                    {
                        a8 = a61;
                        break label1;
                    }
                    label13: try
                    {
                        a15 = a.getCodeBase();
                        break label13;
                    }
                    catch(java.net.MalformedURLException a62)
                    {
                        a8 = a62;
                        break label1;
                    }
                    label14: try
                    {
                        a16 = a14.append((Object)a15);
                        break label14;
                    }
                    catch(java.net.MalformedURLException a63)
                    {
                        a8 = a63;
                        break label1;
                    }
                    label15: try
                    {
                        a17 = a16.append("images/");
                        break label15;
                    }
                    catch(java.net.MalformedURLException a64)
                    {
                        a8 = a64;
                        break label1;
                    }
                    label16: try
                    {
                        a18 = a17.append("logo.gif");
                        break label16;
                    }
                    catch(java.net.MalformedURLException a65)
                    {
                        a8 = a65;
                        break label1;
                    }
                    label17: try
                    {
                        s9 = a18.toString();
                        break label17;
                    }
                    catch(java.net.MalformedURLException a66)
                    {
                        a8 = a66;
                        break label1;
                    }
                    label18: try
                    {
                        a19 = new java.net.URL(s9);
                        break label18;
                    }
                    catch(java.net.MalformedURLException a67)
                    {
                        a8 = a67;
                        break label1;
                    }
                    label19: try
                    {
                        a20 = com.cim.AIA.StreamImage.getImage(a13);
                        break label19;
                    }
                    catch(java.net.MalformedURLException a68)
                    {
                        a8 = a68;
                        break label1;
                    }
                    label20: try
                    {
                        this.logoImage = a20;
                        break label20;
                    }
                    catch(java.net.MalformedURLException a69)
                    {
                        a8 = a69;
                        break label1;
                    }
                    label21: try
                    {
                        a21 = this.logoImage;
                        break label21;
                    }
                    catch(java.net.MalformedURLException a70)
                    {
                        a8 = a70;
                        break label1;
                    }
                    label22: {
                        java.io.PrintStream a71 = null;
                        StringBuilder a72 = null;
                        StringBuilder a73 = null;
                        String s13 = null;
                        StringBuilder a74 = null;
                        String s14 = null;
                        Object a75 = null;
                        java.awt.Image a76 = null;
                        if(a21 != null)
                        {
                            break label22;
                        }
                        try
                        {
                            a71 = System.out;
                        }
                        catch(java.net.MalformedURLException a77)
                        {
                            a8 = a77;
                            break label1;
                        }
                        try
                        {
                            a72 = new StringBuilder();
                        }
                        catch(java.net.MalformedURLException a78)
                        {
                            a8 = a78;
                            break label1;
                        }
                        try
                        {
                            a73 = a72.append("Logo Image is loading from local...");
                        }
                        catch(java.net.MalformedURLException a79)
                        {
                            a8 = a79;
                            break label1;
                        }
                        try
                        {
                            s13 = a19.toString();
                        }
                        catch(java.net.MalformedURLException a80)
                        {
                            a8 = a80;
                            break label1;
                        }
                        try
                        {
                            a74 = a73.append(s13);
                        }
                        catch(java.net.MalformedURLException a81)
                        {
                            a8 = a81;
                            break label1;
                        }
                        try
                        {
                            s14 = a74.toString();
                        }
                        catch(java.net.MalformedURLException a82)
                        {
                            a8 = a82;
                            break label1;
                        }
                        try
                        {
                            a71.println(s14);
                        }
                        catch(java.net.MalformedURLException a83)
                        {
                            a8 = a83;
                            break label1;
                        }
                        try
                        {
                            a75 = a.getAppletContext();
                        }
                        catch(java.net.MalformedURLException a84)
                        {
                            a8 = a84;
                            break label1;
                        }
                        try
                        {
                            a76 = ((java.applet.AppletContext)a75).getImage(a19);
                        }
                        catch(java.net.MalformedURLException a85)
                        {
                            a8 = a85;
                            break label1;
                        }
                        try
                        {
                            this.logoImage = a76;
                        }
                        catch(java.net.MalformedURLException a86)
                        {
                            a8 = a86;
                            break label1;
                        }
                    }
                    label23: try
                    {
                        a22 = new com.cim.AIA.logoProcessor(a19);
                        break label23;
                    }
                    catch(java.net.MalformedURLException a87)
                    {
                        a8 = a87;
                        break label1;
                    }
                    label24: try
                    {
                        a23 = ((Object)a).getClass();
                        break label24;
                    }
                    catch(java.net.MalformedURLException a88)
                    {
                        a8 = a88;
                        break label1;
                    }
                    label25: try
                    {
                        a24 = a23.getResourceAsStream("images/logo.gif");
                        break label25;
                    }
                    catch(java.net.MalformedURLException a89)
                    {
                        a8 = a89;
                        break label1;
                    }
                    label26: try
                    {
                        i4 = a22.checkFile(a24)?1:0;
                        break label26;
                    }
                    catch(java.net.MalformedURLException a90)
                    {
                        a8 = a90;
                        break label1;
                    }
                    label27: {
                        java.net.URL a91 = null;
                        java.net.URL a92 = null;
                        if(i4 != 0)
                        {
                            a25 = a92;
                            break label27;
                        }
                        label29: {
                            java.net.URL a93 = null;
                            label28: {
                                Object a94 = null;
                                java.awt.Image a95 = null;
                                try
                                {
                                    java.net.URL a96 = null;
                                    try
                                    {
                                        a96 = null;
                                        a91 = new java.net.URL("http://ww2.cs.mu.oz.au/muaia/images/logo.gif");
                                    }
                                    catch(SecurityException ignoredException0)
                                    {
                                        a93 = a96;
                                        break label28;
                                    }
                                }
                                catch(java.net.MalformedURLException a97)
                                {
                                    a8 = a97;
                                    break label1;
                                }
                                try
                                {
                                    try
                                    {
                                        a94 = a.getAppletContext();
                                    }
                                    catch(SecurityException ignoredException1)
                                    {
                                        a93 = a91;
                                        break label28;
                                    }
                                }
                                catch(java.net.MalformedURLException a98)
                                {
                                    a8 = a98;
                                    break label1;
                                }
                                try
                                {
                                    try
                                    {
                                        a95 = ((java.applet.AppletContext)a94).getImage(a91);
                                    }
                                    catch(SecurityException ignoredException2)
                                    {
                                        a93 = a91;
                                        break label28;
                                    }
                                }
                                catch(java.net.MalformedURLException a99)
                                {
                                    a8 = a99;
                                    break label1;
                                }
                                try
                                {
                                    try
                                    {
                                        this.logoImage = a95;
                                        break label29;
                                    }
                                    catch(SecurityException ignoredException3)
                                    {
                                        a93 = a91;
                                    }
                                }
                                catch(java.net.MalformedURLException a100)
                                {
                                    a8 = a100;
                                    break label1;
                                }
                            }
                            a25 = a93;
                            break label27;
                        }
                        a25 = a91;
                    }
                    label30: try
                    {
                        a26 = this.logoImage;
                        break label30;
                    }
                    catch(java.net.MalformedURLException a101)
                    {
                        a8 = a101;
                        break label1;
                    }
                    label31: {
                        StringBuilder a102 = null;
                        StringBuilder a103 = null;
                        StringBuilder a104 = null;
                        String s15 = null;
                        java.io.PrintStream a105 = null;
                        StringBuilder a106 = null;
                        StringBuilder a107 = null;
                        StringBuilder a108 = null;
                        String s16 = null;
                        if(a26 != null)
                        {
                            break label31;
                        }
                        label32: try
                        {
                            a102 = new StringBuilder();
                            break label32;
                        }
                        catch(java.net.MalformedURLException a109)
                        {
                            a8 = a109;
                            break label1;
                        }
                        label33: try
                        {
                            a103 = a102.append("com.cim.AIA.ExplainationWindow: Unable to load image: ");
                            break label33;
                        }
                        catch(java.net.MalformedURLException a110)
                        {
                            a8 = a110;
                            break label1;
                        }
                        label34: try
                        {
                            a104 = a103.append((Object)a25);
                            break label34;
                        }
                        catch(java.net.MalformedURLException a111)
                        {
                            a8 = a111;
                            break label1;
                        }
                        label35: try
                        {
                            s15 = a104.toString();
                            break label35;
                        }
                        catch(java.net.MalformedURLException a112)
                        {
                            a8 = a112;
                            break label1;
                        }
                        label36: try
                        {
                            a.showStatus(s15);
                            break label36;
                        }
                        catch(java.net.MalformedURLException a113)
                        {
                            a8 = a113;
                            break label1;
                        }
                        label37: try
                        {
                            a105 = System.out;
                            break label37;
                        }
                        catch(java.net.MalformedURLException a114)
                        {
                            a8 = a114;
                            break label1;
                        }
                        label38: try
                        {
                            a106 = new StringBuilder();
                            break label38;
                        }
                        catch(java.net.MalformedURLException a115)
                        {
                            a8 = a115;
                            break label1;
                        }
                        label39: try
                        {
                            a107 = a106.append("com.cim.AIA.ExplainationWindow: Unable to load image: ");
                            break label39;
                        }
                        catch(java.net.MalformedURLException a116)
                        {
                            a8 = a116;
                            break label1;
                        }
                        label40: try
                        {
                            a108 = a107.append((Object)a25);
                            break label40;
                        }
                        catch(java.net.MalformedURLException a117)
                        {
                            a8 = a117;
                            break label1;
                        }
                        label41: try
                        {
                            s16 = a108.toString();
                            break label41;
                        }
                        catch(java.net.MalformedURLException a118)
                        {
                            a8 = a118;
                            break label1;
                        }
                        label42: try
                        {
                            a105.println(s16);
                            break label42;
                        }
                        catch(java.net.MalformedURLException a119)
                        {
                            a8 = a119;
                            break label1;
                        }
                        break label43;
                    }
                    label44: try
                    {
                        a27 = this.logoImage;
                        break label44;
                    }
                    catch(java.net.MalformedURLException a120)
                    {
                        a8 = a120;
                        break label1;
                    }
                    label45: try
                    {
                        a1.addImage(a27, 0);
                        break label45;
                    }
                    catch(java.net.MalformedURLException a121)
                    {
                        a8 = a121;
                        break label1;
                    }
                    label46: try
                    {
                        a28 = ((Object)a).getClass();
                        break label46;
                    }
                    catch(java.net.MalformedURLException a122)
                    {
                        a8 = a122;
                        break label1;
                    }
                    label47: try
                    {
                        a29 = a28.getResourceAsStream("images/title.gif");
                        break label47;
                    }
                    catch(java.net.MalformedURLException a123)
                    {
                        a8 = a123;
                        break label1;
                    }
                    label48: try
                    {
                        a30 = new StringBuilder();
                        break label48;
                    }
                    catch(java.net.MalformedURLException a124)
                    {
                        a8 = a124;
                        break label1;
                    }
                    label49: try
                    {
                        a31 = a.getCodeBase();
                        break label49;
                    }
                    catch(java.net.MalformedURLException a125)
                    {
                        a8 = a125;
                        break label1;
                    }
                    label50: try
                    {
                        a32 = a30.append((Object)a31);
                        break label50;
                    }
                    catch(java.net.MalformedURLException a126)
                    {
                        a8 = a126;
                        break label1;
                    }
                    label51: try
                    {
                        a33 = a32.append("images/");
                        break label51;
                    }
                    catch(java.net.MalformedURLException a127)
                    {
                        a8 = a127;
                        break label1;
                    }
                    label52: try
                    {
                        a34 = a33.append("title.gif");
                        break label52;
                    }
                    catch(java.net.MalformedURLException a128)
                    {
                        a8 = a128;
                        break label1;
                    }
                    label53: try
                    {
                        s10 = a34.toString();
                        break label53;
                    }
                    catch(java.net.MalformedURLException a129)
                    {
                        a8 = a129;
                        break label1;
                    }
                    label54: try
                    {
                        a35 = new java.net.URL(s10);
                        break label54;
                    }
                    catch(java.net.MalformedURLException a130)
                    {
                        a8 = a130;
                        break label1;
                    }
                    label55: try
                    {
                        a36 = com.cim.AIA.StreamImage.getImage(a29);
                        break label55;
                    }
                    catch(java.net.MalformedURLException a131)
                    {
                        a8 = a131;
                        break label1;
                    }
                    label56: try
                    {
                        this.titleImage = a36;
                        break label56;
                    }
                    catch(java.net.MalformedURLException a132)
                    {
                        a8 = a132;
                        break label1;
                    }
                    label57: try
                    {
                        a37 = this.titleImage;
                        break label57;
                    }
                    catch(java.net.MalformedURLException a133)
                    {
                        a8 = a133;
                        break label1;
                    }
                    label58: {
                        java.io.PrintStream a134 = null;
                        StringBuilder a135 = null;
                        StringBuilder a136 = null;
                        String s17 = null;
                        StringBuilder a137 = null;
                        String s18 = null;
                        Object a138 = null;
                        java.awt.Image a139 = null;
                        if(a37 != null)
                        {
                            break label58;
                        }
                        try
                        {
                            a134 = System.out;
                        }
                        catch(java.net.MalformedURLException a140)
                        {
                            a8 = a140;
                            break label1;
                        }
                        try
                        {
                            a135 = new StringBuilder();
                        }
                        catch(java.net.MalformedURLException a141)
                        {
                            a8 = a141;
                            break label1;
                        }
                        try
                        {
                            a136 = a135.append("TitleImage is loading from local...");
                        }
                        catch(java.net.MalformedURLException a142)
                        {
                            a8 = a142;
                            break label1;
                        }
                        try
                        {
                            s17 = a35.toString();
                        }
                        catch(java.net.MalformedURLException a143)
                        {
                            a8 = a143;
                            break label1;
                        }
                        try
                        {
                            a137 = a136.append(s17);
                        }
                        catch(java.net.MalformedURLException a144)
                        {
                            a8 = a144;
                            break label1;
                        }
                        try
                        {
                            s18 = a137.toString();
                        }
                        catch(java.net.MalformedURLException a145)
                        {
                            a8 = a145;
                            break label1;
                        }
                        try
                        {
                            a134.println(s18);
                        }
                        catch(java.net.MalformedURLException a146)
                        {
                            a8 = a146;
                            break label1;
                        }
                        try
                        {
                            a138 = a.getAppletContext();
                        }
                        catch(java.net.MalformedURLException a147)
                        {
                            a8 = a147;
                            break label1;
                        }
                        try
                        {
                            a139 = ((java.applet.AppletContext)a138).getImage(a35);
                        }
                        catch(java.net.MalformedURLException a148)
                        {
                            a8 = a148;
                            break label1;
                        }
                        try
                        {
                            this.titleImage = a139;
                        }
                        catch(java.net.MalformedURLException a149)
                        {
                            a8 = a149;
                            break label1;
                        }
                    }
                    label59: try
                    {
                        a38 = ((Object)a).getClass();
                        break label59;
                    }
                    catch(java.net.MalformedURLException a150)
                    {
                        a8 = a150;
                        break label1;
                    }
                    label60: try
                    {
                        a39 = a38.getResourceAsStream("images/title.gif");
                        break label60;
                    }
                    catch(java.net.MalformedURLException a151)
                    {
                        a8 = a151;
                        break label1;
                    }
                    label61: try
                    {
                        a40 = new com.cim.AIA.titleProcessor(a25);
                        break label61;
                    }
                    catch(java.net.MalformedURLException a152)
                    {
                        a8 = a152;
                        break label1;
                    }
                    label62: try
                    {
                        i5 = a40.checkFile(a39)?1:0;
                        break label62;
                    }
                    catch(java.net.MalformedURLException a153)
                    {
                        a8 = a153;
                        break label1;
                    }
                    label63: {
                        java.net.URL a154 = null;
                        if(i5 != 0)
                        {
                            a41 = a25;
                            break label63;
                        }
                        label65: {
                            java.net.URL a155 = null;
                            label64: {
                                Object a156 = null;
                                java.awt.Image a157 = null;
                                try
                                {
                                    try
                                    {
                                        a154 = new java.net.URL("http://ww2.cs.mu.oz.au/muaia/images/title.gif");
                                    }
                                    catch(SecurityException ignoredException4)
                                    {
                                        a155 = a25;
                                        break label64;
                                    }
                                }
                                catch(java.net.MalformedURLException a158)
                                {
                                    a8 = a158;
                                    break label1;
                                }
                                try
                                {
                                    try
                                    {
                                        a156 = a.getAppletContext();
                                    }
                                    catch(SecurityException ignoredException5)
                                    {
                                        a155 = a154;
                                        break label64;
                                    }
                                }
                                catch(java.net.MalformedURLException a159)
                                {
                                    a8 = a159;
                                    break label1;
                                }
                                try
                                {
                                    try
                                    {
                                        a157 = ((java.applet.AppletContext)a156).getImage(a154);
                                    }
                                    catch(SecurityException ignoredException6)
                                    {
                                        a155 = a154;
                                        break label64;
                                    }
                                }
                                catch(java.net.MalformedURLException a160)
                                {
                                    a8 = a160;
                                    break label1;
                                }
                                try
                                {
                                    try
                                    {
                                        this.titleImage = a157;
                                        break label65;
                                    }
                                    catch(SecurityException ignoredException7)
                                    {
                                        a155 = a154;
                                    }
                                }
                                catch(java.net.MalformedURLException a161)
                                {
                                    a8 = a161;
                                    break label1;
                                }
                            }
                            a41 = a155;
                            break label63;
                        }
                        a41 = a154;
                    }
                    label66: try
                    {
                        a42 = this.titleImage;
                        break label66;
                    }
                    catch(java.net.MalformedURLException a162)
                    {
                        a8 = a162;
                        break label1;
                    }
                    label67: {
                        java.awt.Image a163 = null;
                        if(a42 == null)
                        {
                            break label67;
                        }
                        label68: try
                        {
                            a163 = this.titleImage;
                            break label68;
                        }
                        catch(java.net.MalformedURLException a164)
                        {
                            a8 = a164;
                            break label1;
                        }
                        label69: try
                        {
                            a1.addImage(a163, 0);
                            break label69;
                        }
                        catch(java.net.MalformedURLException a165)
                        {
                            a8 = a165;
                            break label1;
                        }
                        break label70;
                    }
                    label71: try
                    {
                        a43 = new StringBuilder();
                        break label71;
                    }
                    catch(java.net.MalformedURLException a166)
                    {
                        a8 = a166;
                        break label1;
                    }
                    label72: try
                    {
                        a44 = a43.append("com.cim.AIA.ExplainationWindow: Unable to load image: ");
                        break label72;
                    }
                    catch(java.net.MalformedURLException a167)
                    {
                        a8 = a167;
                        break label1;
                    }
                    label73: try
                    {
                        a45 = a44.append((Object)a41);
                        break label73;
                    }
                    catch(java.net.MalformedURLException a168)
                    {
                        a8 = a168;
                        break label1;
                    }
                    label74: try
                    {
                        s11 = a45.toString();
                        break label74;
                    }
                    catch(java.net.MalformedURLException a169)
                    {
                        a8 = a169;
                        break label1;
                    }
                    label75: try
                    {
                        a.showStatus(s11);
                        break label75;
                    }
                    catch(java.net.MalformedURLException a170)
                    {
                        a8 = a170;
                        break label1;
                    }
                    label76: try
                    {
                        a46 = System.out;
                        break label76;
                    }
                    catch(java.net.MalformedURLException a171)
                    {
                        a8 = a171;
                        break label1;
                    }
                    label77: try
                    {
                        a47 = new StringBuilder();
                        break label77;
                    }
                    catch(java.net.MalformedURLException a172)
                    {
                        a8 = a172;
                        break label1;
                    }
                    label78: try
                    {
                        a48 = a47.append("com.cim.AIA.ExplainationWindow: Unable to load image: ");
                        break label78;
                    }
                    catch(java.net.MalformedURLException a173)
                    {
                        a8 = a173;
                        break label1;
                    }
                    label79: try
                    {
                        a49 = a48.append((Object)a41);
                        break label79;
                    }
                    catch(java.net.MalformedURLException a174)
                    {
                        a8 = a174;
                        break label1;
                    }
                    label80: try
                    {
                        s12 = a49.toString();
                        break label80;
                    }
                    catch(java.net.MalformedURLException a175)
                    {
                        a8 = a175;
                        break label1;
                    }
                    label81: try
                    {
                        a46.println(s12);
                        break label81;
                    }
                    catch(java.net.MalformedURLException a176)
                    {
                        a8 = a176;
                        break label1;
                    }
                    break label43;
                }
                StringBuilder a177 = new StringBuilder();
                StringBuilder a178 = a177.append("com.cim.AIA.ExplainationWindow: Invalid URL: ");
                StringBuilder a179 = a178.append((Object)a8);
                String s19 = a179.toString();
                a.showStatus(s19);
                java.io.PrintStream a180 = System.out;
                StringBuilder a181 = new StringBuilder();
                StringBuilder a182 = a181.append("com.cim.AIA.ExplainationWindow: Invalid URL: ");
                StringBuilder a183 = a182.append((Object)a8);
                String s20 = a183.toString();
                a180.println(s20);
            }
            try
            {
                a1.waitForAll();
            }
            catch(InterruptedException ignoredException8)
            {
            }
            java.awt.Image a184 = this.logoImage;
            com.cim.AIA.ImageCanvas a185 = new com.cim.AIA.ImageCanvas(a184, (java.awt.Dimension)null);
            this.logoCanvas = a185;
            java.awt.Image a186 = this.titleImage;
            com.cim.AIA.ImageCanvas a187 = new com.cim.AIA.ImageCanvas(a186, (java.awt.Dimension)null);
            this.titleCanvas = a187;
            java.awt.Color a188 = java.awt.Color.white;
            this.setBackground(a188);
            java.awt.Panel a189 = new java.awt.Panel();
            this.mainPanel = a189;
            java.awt.Panel a190 = new java.awt.Panel();
            java.awt.GridBagLayout a191 = new java.awt.GridBagLayout();
            java.awt.GridBagConstraints a192 = new java.awt.GridBagConstraints();
            a190.setLayout((java.awt.LayoutManager)a191);
            java.awt.Color a193 = java.awt.Color.gray;
            java.awt.Color a194 = a193.brighter();
            a190.setBackground(a194);
            this.buildConstraints(a192, 0, 0, 1, 1, 20, 100);
            java.awt.Insets a195 = new java.awt.Insets(0, 5, 5, 0);
            a192.insets = a195;
            a192.anchor = 17;
            a192.fill = 0;
            com.cim.AIA.ImageCanvas a196 = this.logoCanvas;
            a191.setConstraints((java.awt.Component)a196, a192);
            com.cim.AIA.ImageCanvas a197 = this.logoCanvas;
            java.awt.Component a198 = a190.add((java.awt.Component)a197);
            this.buildConstraints(a192, 1, 0, 1, 1, 80, 100);
            java.awt.Insets a199 = new java.awt.Insets(0, 2, 0, 0);
            a192.insets = a199;
            com.cim.AIA.ImageCanvas a200 = this.titleCanvas;
            a191.setConstraints((java.awt.Component)a200, a192);
            com.cim.AIA.ImageCanvas a201 = this.titleCanvas;
            java.awt.Component a202 = a190.add((java.awt.Component)a201);
            java.awt.GridBagLayout a203 = new java.awt.GridBagLayout();
            java.awt.Panel a204 = this.mainPanel;
            a204.setLayout((java.awt.LayoutManager)a203);
            java.awt.GridBagConstraints a205 = new java.awt.GridBagConstraints();
            a205.gridwidth = 0;
            a205.anchor = 10;
            a205.fill = 1;
            a205.ipadx = 0;
            a205.ipady = 0;
            a205.weightx = 0.0;
            a205.weighty = 0.0;
            java.awt.Font a206 = new java.awt.Font("Courier", 1, 12);
            java.awt.Label a207 = new java.awt.Label(s0, 0);
            this.label1 = a207;
            java.awt.Label a208 = this.label1;
            a208.setFont(a206);
            a203.setConstraints((java.awt.Component)a190, a205);
            java.awt.Panel a209 = this.mainPanel;
            java.awt.Component a210 = a209.add((java.awt.Component)a190);
            java.awt.Label a211 = this.label1;
            a203.setConstraints((java.awt.Component)a211, a205);
            java.awt.Panel a212 = this.mainPanel;
            java.awt.Label a213 = this.label1;
            java.awt.Component a214 = a212.add((java.awt.Component)a213);
            a205.weightx = 4.0;
            a205.weighty = 4.0;
            java.awt.TextArea a215 = new java.awt.TextArea("", 15, 30, 1);
            this.basicInfoTextArea = a215;
            java.awt.TextArea a216 = new java.awt.TextArea("", 5, 30, 1);
            this.infoTextArea = a216;
            java.awt.TextArea a217 = this.basicInfoTextArea;
            a217.setEditable(false);
            java.awt.TextArea a218 = this.infoTextArea;
            a218.setEditable(false);
            java.awt.TextArea a219 = this.basicInfoTextArea;
            a203.setConstraints((java.awt.Component)a219, a205);
            java.awt.Panel a220 = this.mainPanel;
            java.awt.TextArea a221 = this.basicInfoTextArea;
            java.awt.Component a222 = a220.add((java.awt.Component)a221);
            a205.weightx = 0.0;
            a205.weighty = 0.0;
            java.awt.Label a223 = new java.awt.Label(s1, 0);
            this.label2 = a223;
            java.awt.Label a224 = this.label2;
            a224.setFont(a206);
            java.awt.Label a225 = this.label2;
            a203.setConstraints((java.awt.Component)a225, a205);
            java.awt.Panel a226 = this.mainPanel;
            java.awt.Label a227 = this.label2;
            java.awt.Component a228 = a226.add((java.awt.Component)a227);
            a205.weightx = 3.0;
            a205.weighty = 3.0;
            java.awt.TextArea a229 = this.infoTextArea;
            a203.setConstraints((java.awt.Component)a229, a205);
            java.awt.Panel a230 = this.mainPanel;
            java.awt.TextArea a231 = this.infoTextArea;
            java.awt.Component a232 = a230.add((java.awt.Component)a231);
            java.awt.Panel a233 = this.mainPanel;
            java.awt.Component a234 = this.add((java.awt.Component)a233);
            com.cim.AIA.ConfigurationManager a235 = com.cim.AIA.ConfigurationManager.getConfigurationManager();
            a235.addColorSelectionListener((com.cim.AIA.ColorSelectionListener)this);
            com.cim.AIA.ConfigurationManager a236 = com.cim.AIA.ConfigurationManager.getConfigurationManager();
            a236.addFontSelectionListener((com.cim.AIA.FontSelectionListener)this);
        }
    }
    
    public void addHelpListener(com.cim.AIA.HelpListener a)
    {
        com.cim.AIA.ImageCanvas a0 = this.logoCanvas;
        Object a1 = a;
        a0.addHelpListener((com.cim.AIA.HelpListener)a1);
        com.cim.AIA.ImageCanvas a2 = this.titleCanvas;
        a2.addHelpListener((com.cim.AIA.HelpListener)a1);
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
        this.basicInfoTextArea = null;
        this.infoTextArea = null;
        java.awt.ScrollPane a = this.sp;
        if(a != null)
        {
            java.awt.ScrollPane a0 = this.sp;
            a0.removeAll();
        }
        this.sp = null;
        java.awt.Panel a1 = this.mainPanel;
        if(a1 != null)
        {
            java.awt.Panel a2 = this.mainPanel;
            a2.removeAll();
        }
        this.mainPanel = null;
        this.label1 = null;
        this.label2 = null;
        this.applet = null;
        java.awt.Image a3 = this.logoImage;
        if(a3 != null)
        {
            java.awt.Image a4 = this.logoImage;
            a4.flush();
        }
        this.logoImage = null;
        java.awt.Image a5 = this.titleImage;
        if(a5 != null)
        {
            java.awt.Image a6 = this.titleImage;
            a6.flush();
        }
        this.titleImage = null;
        com.cim.AIA.ImageCanvas a7 = this.logoCanvas;
        if(a7 != null)
        {
            com.cim.AIA.ImageCanvas a8 = this.logoCanvas;
            a8.cleanUp();
        }
        this.logoCanvas = null;
        com.cim.AIA.ImageCanvas a9 = this.titleCanvas;
        if(a9 != null)
        {
            com.cim.AIA.ImageCanvas a10 = this.titleCanvas;
            a10.cleanUp();
        }
        this.titleCanvas = null;
    }
    
    public String getClassName()
    {
        return "ExplainationWindow";
    }
    
    public void initialiseMainData(String s, String s0)
    {
        label2: {
            java.net.URL a = null;
            java.io.BufferedReader a0 = null;
            label1: {
                java.net.MalformedURLException a1 = null;
                label0: {
                    StringBuilder a2 = null;
                    StringBuilder a3 = null;
                    StringBuilder a4 = null;
                    String s1 = null;
                    try
                    {
                        a2 = new StringBuilder();
                    }
                    catch(java.net.MalformedURLException a5)
                    {
                        a1 = a5;
                        break label0;
                    }
                    try
                    {
                        a3 = a2.append(s);
                    }
                    catch(java.net.MalformedURLException a6)
                    {
                        a1 = a6;
                        break label0;
                    }
                    try
                    {
                        a4 = a3.append(s0);
                    }
                    catch(java.net.MalformedURLException a7)
                    {
                        a1 = a7;
                        break label0;
                    }
                    try
                    {
                        s1 = a4.toString();
                    }
                    catch(java.net.MalformedURLException a8)
                    {
                        a1 = a8;
                        break label0;
                    }
                    try
                    {
                        a = new java.net.URL(s1);
                        break label1;
                    }
                    catch(java.net.MalformedURLException a9)
                    {
                        a1 = a9;
                    }
                }
                java.io.PrintStream a10 = System.out;
                StringBuilder a11 = new StringBuilder();
                StringBuilder a12 = a11.append("Malformed URL: ExplainationWindow: ");
                StringBuilder a13 = a12.append((Object)a1);
                String s2 = a13.toString();
                a10.println(s2);
                break label2;
            }
            label5: {
                java.io.IOException a14 = null;
                label4: {
                    java.io.FileNotFoundException a15 = null;
                    label3: {
                        java.net.URLConnection a16 = null;
                        java.io.InputStream a17 = null;
                        java.io.InputStreamReader a18 = null;
                        try
                        {
                            try
                            {
                                a16 = a.openConnection();
                            }
                            catch(java.io.FileNotFoundException a19)
                            {
                                a15 = a19;
                                break label3;
                            }
                        }
                        catch(java.io.IOException a20)
                        {
                            a14 = a20;
                            break label4;
                        }
                        try
                        {
                            try
                            {
                                a17 = a16.getInputStream();
                            }
                            catch(java.io.FileNotFoundException a21)
                            {
                                a15 = a21;
                                break label3;
                            }
                        }
                        catch(java.io.IOException a22)
                        {
                            a14 = a22;
                            break label4;
                        }
                        try
                        {
                            try
                            {
                                a18 = new java.io.InputStreamReader(a17, "UTF8");
                            }
                            catch(java.io.FileNotFoundException a23)
                            {
                                a15 = a23;
                                break label3;
                            }
                        }
                        catch(java.io.IOException a24)
                        {
                            a14 = a24;
                            break label4;
                        }
                        try
                        {
                            try
                            {
                                a0 = new java.io.BufferedReader((java.io.Reader)a18);
                                break label5;
                            }
                            catch(java.io.FileNotFoundException a25)
                            {
                                a15 = a25;
                            }
                        }
                        catch(java.io.IOException a26)
                        {
                            a14 = a26;
                            break label4;
                        }
                    }
                    java.io.PrintStream a27 = System.out;
                    StringBuilder a28 = new StringBuilder();
                    StringBuilder a29 = a28.append("FileNotFound: ExplainationWindow: ");
                    StringBuilder a30 = a29.append((Object)a15);
                    String s3 = a30.toString();
                    a27.println(s3);
                    break label2;
                }
                java.io.PrintStream a31 = System.out;
                StringBuilder a32 = new StringBuilder();
                StringBuilder a33 = a32.append("com.cim.AIA.ExplainationWindow: IOEXCEPTION");
                StringBuilder a34 = a33.append((Object)a14);
                String s4 = a34.toString();
                a31.println(s4);
                break label2;
            }
            this.setMainText("");
            try
            {
                while(true)
                {
                    String s5 = a0.readLine();
                    if(s5 != null)
                    {
                        java.awt.TextArea a35 = this.basicInfoTextArea;
                        StringBuilder a36 = new StringBuilder();
                        StringBuilder a37 = a36.append(s5);
                        StringBuilder a38 = a37.append("\n");
                        String s6 = a38.toString();
                        a35.append(s6);
                    }
                    else
                    {
                        break;
                    }
                }
            }
            catch(java.io.IOException a39)
            {
                java.io.PrintStream a40 = System.out;
                StringBuilder a41 = new StringBuilder();
                StringBuilder a42 = a41.append("com.cim.AIA.ExplainationWindow: IOEXCEPTION");
                StringBuilder a43 = a42.append((Object)a39);
                String s7 = a43.toString();
                a40.println(s7);
            }
        }
    }
    
    public void modeNormal(com.cim.AIA.ModeEvent a)
    {
        this.setVisible(true);
    }
    
    public void modeOther(com.cim.AIA.ModeEvent a)
    {
        this.setVisible(true);
    }
    
    public void modeQuiz(com.cim.AIA.ModeEvent a)
    {
        this.setVisible(true);
    }
    
    public void modeSelfTest(com.cim.AIA.ModeEvent a)
    {
        this.setVisible(false);
    }
    
    public void processColorSelection(com.cim.AIA.ColorSelection a)
    {
        String s = a.getAtributeName();
        String s0 = com.cim.AIA.ColorSelection.BACKGROUND;
        int i = s.equalsIgnoreCase(s0)?1:0;
        label1: {
            label0: {
                if(i == 0)
                {
                    break label0;
                }
                java.awt.TextArea a0 = this.basicInfoTextArea;
                java.awt.Color a1 = a.getColor();
                a0.setBackground(a1);
                java.awt.TextArea a2 = this.infoTextArea;
                java.awt.Color a3 = a.getColor();
                a2.setBackground(a3);
                java.awt.Label a4 = this.label1;
                java.awt.Color a5 = a.getColor();
                a4.setBackground(a5);
                java.awt.Label a6 = this.label2;
                java.awt.Color a7 = a.getColor();
                a6.setBackground(a7);
                break label1;
            }
            String s1 = com.cim.AIA.ColorSelection.TEXT_COLOR;
            int i0 = s.equalsIgnoreCase(s1)?1:0;
            if(i0 != 0)
            {
                java.awt.TextArea a8 = this.basicInfoTextArea;
                java.awt.Color a9 = a.getColor();
                a8.setForeground(a9);
                java.awt.TextArea a10 = this.infoTextArea;
                java.awt.Color a11 = a.getColor();
                a10.setForeground(a11);
                java.awt.Label a12 = this.label1;
                java.awt.Color a13 = a.getColor();
                a12.setForeground(a13);
                java.awt.Label a14 = this.label2;
                java.awt.Color a15 = a.getColor();
                a14.setForeground(a15);
            }
        }
    }
    
    public void processExitEvent(com.cim.AIA.ExitEvent a)
    {
        this.setVisible(false);
        this.removeAll();
        this.dispose();
        this.cleanUp();
    }
    
    public void processExplainationEvent(com.cim.AIA.ExplainationEvent a)
    {
        String s = a.getTitle();
        String s0 = a.getExplaination();
        this.setSubInfoText(s, s0);
    }
    
    public void processFontSelection(com.cim.AIA.FontSelection a)
    {
        String s = a.getAtributeName();
        int i = s.equalsIgnoreCase("Normal Font")?1:0;
        if(i != 0)
        {
            java.awt.TextArea a0 = this.infoTextArea;
            java.awt.Font a1 = a.getFont();
            a0.setFont(a1);
            java.awt.TextArea a2 = this.basicInfoTextArea;
            java.awt.Font a3 = a.getFont();
            a2.setFont(a3);
            java.awt.Font a4 = a.getFont();
            java.awt.Label a5 = this.label1;
            String s0 = a4.getName();
            int i0 = a4.getSize();
            java.awt.Font a6 = new java.awt.Font(s0, 1, i0);
            a5.setFont(a6);
            java.awt.Label a7 = this.label2;
            String s1 = a4.getName();
            int i1 = a4.getSize();
            java.awt.Font a8 = new java.awt.Font(s1, 1, i1);
            a7.setFont(a8);
        }
    }
    
    public void removeHelpListener(com.cim.AIA.HelpListener a)
    {
        com.cim.AIA.ImageCanvas a0 = this.logoCanvas;
        Object a1 = a;
        a0.removeHelpListener((com.cim.AIA.HelpListener)a1);
        com.cim.AIA.ImageCanvas a2 = this.titleCanvas;
        a2.removeHelpListener((com.cim.AIA.HelpListener)a1);
    }
    
    public void setMainText(String s)
    {
        java.awt.TextArea a = this.basicInfoTextArea;
        a.setText(s);
    }
    
    public void setMainTitle(String s)
    {
        String s0 = s.replace((char)9, (char)32);
        java.util.StringTokenizer a = new java.util.StringTokenizer(s, " ");
        String s1 = " ";
        while(true)
        {
            int i = a.hasMoreTokens()?1:0;
            if(i == 0)
            {
                java.awt.Label a0 = this.label1;
                a0.setText(s1);
                return;
            }
            else
            {
                StringBuilder a1 = new StringBuilder();
                StringBuilder a2 = a1.append(s1);
                String s2 = a.nextToken();
                StringBuilder a3 = a2.append(s2);
                StringBuilder a4 = a3.append(" ");
                String s3 = a4.toString();
                s1 = s3;
            }
        }
    }
    
    public void setSubInfoText(String s, String s0)
    {
        String s1 = s.replace((char)9, (char)32);
        java.util.StringTokenizer a = new java.util.StringTokenizer(s, " ");
        String s2 = " ";
        while(true)
        {
            int i = a.hasMoreTokens()?1:0;
            if(i == 0)
            {
                break;
            }
            else
            {
                StringBuilder a0 = new StringBuilder();
                StringBuilder a1 = a0.append(s2);
                String s3 = a.nextToken();
                StringBuilder a2 = a1.append(s3);
                StringBuilder a3 = a2.append(" ");
                String s4 = a3.toString();
                s2 = s4;
            }
        }
        java.awt.Label a4 = this.label2;
        String s5 = a4.getText();
        int i0 = s5.compareTo(s2);
        if(i0 != 0)
        {
            java.awt.Label a5 = this.label2;
            a5.setText(s2);
        }
        java.awt.TextArea a6 = this.infoTextArea;
        String s6 = a6.getText();
        int i1 = s6.compareTo(s0);
        if(i1 != 0)
        {
            java.awt.TextArea a7 = this.infoTextArea;
            a7.setText(s0);
        }
    }
    
    static
    {
        com.cim.AIA.ExplainationWindow.JAR_FILE_NAME = "aia.jar";
    }
}