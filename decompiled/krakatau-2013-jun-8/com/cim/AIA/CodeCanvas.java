package com.cim.AIA;

public class CodeCanvas extends com.cim.common.BasicCanvas implements java.awt.event.MouseListener, java.awt.event.MouseMotionListener, com.cim.AIA.Explainable, com.cim.AIA.ExplainationListener, com.cim.AIA.Helpable, com.cim.AIA.HelpListener, com.cim.AIA.ColorSelectionListener, com.cim.AIA.FontSelectionListener {
    final private static long serialVersionUID = -977061214392447714L;
    protected static String minmaxLabel;
    protected static com.cim.AIA.LadderTree minmaxParent;
    protected static boolean inMinMax;
    protected static boolean isLabelMinMax;
    protected static boolean isFirstMinMax;
    protected static boolean isLastMinMax;
    protected static com.cim.AIA.AlgorithmLine firstInstance;
    protected static com.cim.AIA.AlgorithmLine labelInstance;
    protected static com.cim.AIA.AlgorithmLine lastInstance;
    final protected static String START_MINMAX = "%[";
    final protected static String END_MINMAX = "]%";
    final public static String OPEN_FOLDER_HELP_TITLE;
    final public static java.awt.Color DEFAULT_HIGHLIGHT_COLOR;
    final public static String OPEN_FOLDER_INSTRUCTIONS;
    final public static String CLOSE_FOLDER_HELP_TITLE;
    final public static String CLOSE_FOLDER_INSTRUCTIONS;
    final public static String LINE_HELP_TITLE;
    final public static String LINE_INSTRUCTIONS;
    final protected static String START_SEPARATOR = "$";
    final protected static String STOP_SEPARATOR = "@";
    final protected static String ALWAYS_OPEN_MARKER = "~";
    final protected static String EXPAND_AS_OWN_MARKER = "`";
    final protected static String OPEN_FOLDER_IMAGE_NAME = "open.gif";
    final protected static String CLOSED_FOLDER_IMAGE_NAME = "folder.gif";
    final protected static String LOGO_IMAGE_NAME = "logo.gif";
    protected static String JAR_FILE_NAME;
    final protected static int POSITION_HIGHLIGHT_LEVEL = 1;
    final protected static int EXPLAIN_HIGHLIGHT_LEVEL = 2;
    final protected static int BREAKPOINT_HIGHLIGHT_LEVEL = 3;
    protected static String CODE_POSITION_MARKER;
    protected static String EXPLANATION_POSITION_MARKER;
    protected static String COMMENT_LINE_COLOR;
    protected static String BREAKPOINT_COLOR;
    final protected int MINMAX_XGAP;
    final protected int MINMAX_LINELENGTH;
    protected java.awt.Color commentColor;
    protected java.awt.Color codeColor;
    protected java.awt.Color backgroundColor;
    protected java.awt.Color highlightColor;
    protected java.awt.Color explainColor;
    protected java.awt.Color breakPointColor;
    protected java.util.Vector explainationListeners;
    protected java.util.Vector helpListeners;
    protected com.cim.AIA.LadderTree ladderTree;
    protected String fileName;
    protected String dataPath;
    protected String expandedImageName;
    protected String collapsedImageName;
    public java.awt.Image logoImage;
    protected java.awt.Image expandedImage;
    protected java.awt.Image collapsedImage;
    public java.awt.Dimension logoImageSize;
    protected java.awt.Dimension expandedImageSize;
    protected java.awt.Dimension collapsedImageSize;
    protected String normalFontName;
    protected int normalFontSize;
    protected java.awt.Font normalFont;
    com.cim.AIA.BreakPoint breakpoint;
    final protected int imageBuffer;
    final protected int ySpacing;
    protected java.awt.Container parent;
    protected String currentPosition;
    
    public static com.cim.AIA.LadderTree getLadderTreeFromFile(String s, String s0, com.cim.AIA.Logger a, com.cim.AIA.BreakPoint a0)
    {
        com.cim.AIA.LadderTree a1 = null;
        label2: {
            java.net.URL a2 = null;
            java.io.BufferedReader a3 = null;
            label1: {
                java.net.MalformedURLException a4 = null;
                label0: {
                    StringBuilder a5 = null;
                    StringBuilder a6 = null;
                    StringBuilder a7 = null;
                    String s1 = null;
                    try
                    {
                        a5 = new StringBuilder();
                    }
                    catch(java.net.MalformedURLException a8)
                    {
                        a4 = a8;
                        break label0;
                    }
                    try
                    {
                        a6 = a5.append(s);
                    }
                    catch(java.net.MalformedURLException a9)
                    {
                        a4 = a9;
                        break label0;
                    }
                    try
                    {
                        a7 = a6.append(s0);
                    }
                    catch(java.net.MalformedURLException a10)
                    {
                        a4 = a10;
                        break label0;
                    }
                    try
                    {
                        s1 = a7.toString();
                    }
                    catch(java.net.MalformedURLException a11)
                    {
                        a4 = a11;
                        break label0;
                    }
                    try
                    {
                        a2 = new java.net.URL(s1);
                        break label1;
                    }
                    catch(java.net.MalformedURLException a12)
                    {
                        a4 = a12;
                    }
                }
                java.io.PrintStream a13 = System.out;
                StringBuilder a14 = new StringBuilder();
                StringBuilder a15 = a14.append("Malformed URL: CodeCanvas: ");
                StringBuilder a16 = a15.append((Object)a4);
                String s2 = a16.toString();
                a13.println(s2);
                a1 = null;
                break label2;
            }
            label5: {
                java.io.IOException a17 = null;
                label4: {
                    java.io.FileNotFoundException a18 = null;
                    label3: {
                        java.net.URLConnection a19 = null;
                        java.io.InputStream a20 = null;
                        java.io.InputStreamReader a21 = null;
                        try
                        {
                            try
                            {
                                a19 = a2.openConnection();
                            }
                            catch(java.io.FileNotFoundException a22)
                            {
                                a18 = a22;
                                break label3;
                            }
                        }
                        catch(java.io.IOException a23)
                        {
                            a17 = a23;
                            break label4;
                        }
                        try
                        {
                            try
                            {
                                a20 = a19.getInputStream();
                            }
                            catch(java.io.FileNotFoundException a24)
                            {
                                a18 = a24;
                                break label3;
                            }
                        }
                        catch(java.io.IOException a25)
                        {
                            a17 = a25;
                            break label4;
                        }
                        try
                        {
                            try
                            {
                                a21 = new java.io.InputStreamReader(a20);
                            }
                            catch(java.io.FileNotFoundException a26)
                            {
                                a18 = a26;
                                break label3;
                            }
                        }
                        catch(java.io.IOException a27)
                        {
                            a17 = a27;
                            break label4;
                        }
                        try
                        {
                            try
                            {
                                a3 = new java.io.BufferedReader((java.io.Reader)a21);
                                break label5;
                            }
                            catch(java.io.FileNotFoundException a28)
                            {
                                a18 = a28;
                            }
                        }
                        catch(java.io.IOException a29)
                        {
                            a17 = a29;
                            break label4;
                        }
                    }
                    java.io.PrintStream a30 = System.out;
                    StringBuilder a31 = new StringBuilder();
                    StringBuilder a32 = a31.append("com.cim.AIA.CodeCanvas: FileNotFound: ");
                    StringBuilder a33 = a32.append((Object)a18);
                    String s3 = a33.toString();
                    a30.println(s3);
                    a1 = null;
                    break label2;
                }
                java.io.PrintStream a34 = System.out;
                StringBuilder a35 = new StringBuilder();
                StringBuilder a36 = a35.append("com.cim.AUA.CodeCanvas: IO EXCEPTION: ");
                StringBuilder a37 = a36.append((Object)a17);
                String s4 = a37.toString();
                a34.println(s4);
                a1 = null;
                break label2;
            }
            com.cim.AIA.LadderTree a38 = null;
            com.cim.AIA.LadderTree a39 = null;
            int i = 0;
            int i0 = 0;
            int i1 = 0;
            try
            {
                com.cim.AIA.LadderTree a40 = null;
                while(true)
                {
                    com.cim.AIA.LadderTree a41 = null;
                    com.cim.AIA.LadderTree a42 = null;
                    int i2 = 0;
                    int i3 = 0;
                    int i4 = 0;
                    com.cim.AIA.LadderTree a43 = null;
                    com.cim.AIA.LadderTree a44 = null;
                    int i5 = 0;
                    int i6 = 0;
                    int i7 = 0;
                    com.cim.AIA.LadderTree a45 = null;
                    com.cim.AIA.LadderTree a46 = null;
                    String s5 = null;
                    int i8 = 0;
                    int i9 = 0;
                    int i10 = 0;
                    com.cim.AIA.LadderTree a47 = null;
                    com.cim.AIA.LadderTree a48 = null;
                    String s6 = null;
                    int i11 = 0;
                    int i12 = 0;
                    int i13 = 0;
                    com.cim.AIA.LadderTree a49 = null;
                    com.cim.AIA.LadderTree a50 = null;
                    String s7 = null;
                    int i14 = 0;
                    int i15 = 0;
                    int i16 = 0;
                    com.cim.AIA.LadderTree a51 = null;
                    com.cim.AIA.LadderTree a52 = null;
                    String s8 = null;
                    int i17 = 0;
                    int i18 = 0;
                    int i19 = 0;
                    com.cim.AIA.LadderTree a53 = null;
                    com.cim.AIA.LadderTree a54 = null;
                    String s9 = null;
                    int i20 = 0;
                    int i21 = 0;
                    int i22 = 0;
                    int i23 = 0;
                    com.cim.AIA.LadderTree a55 = null;
                    com.cim.AIA.LadderTree a56 = null;
                    String s10 = null;
                    int i24 = 0;
                    int i25 = 0;
                    int i26 = 0;
                    com.cim.AIA.LadderTree a57 = null;
                    com.cim.AIA.LadderTree a58 = null;
                    int i27 = 0;
                    int i28 = 0;
                    int i29 = 0;
                    com.cim.AIA.LadderTree a59 = null;
                    com.cim.AIA.LadderTree a60 = null;
                    int i30 = 0;
                    int i31 = 0;
                    int i32 = 0;
                    com.cim.AIA.LadderTree a61 = null;
                    com.cim.AIA.LadderTree a62 = null;
                    int i33 = 0;
                    int i34 = 0;
                    int i35 = 0;
                    String s11 = a3.readLine();
                    a40 = a38;
                    com.cim.AIA.LadderTree a63 = a39;
                    int i36 = i;
                    int i37 = i0;
                    int i38 = i1;
                    com.cim.AIA.LadderTree a64 = a40;
                    com.cim.AIA.LadderTree a65 = a63;
                    int i39 = i36;
                    int i40 = i37;
                    int i41 = i38;
                    if(s11 != null)
                    {
                        a41 = a64;
                        a42 = a65;
                        i2 = i39;
                        i3 = i40;
                        i4 = i41;
                    }
                    else
                    {
                        break;
                    }
                    String s12 = com.cim.AIA.CodeCanvas.replaceString(s11, "\t", "");
                    com.cim.AIA.LadderTree a66 = a41;
                    com.cim.AIA.LadderTree a67 = a42;
                    int i42 = i2;
                    int i43 = i3;
                    int i44 = i4;
                    int i45 = s12.length();
                    com.cim.AIA.LadderTree a68 = a66;
                    com.cim.AIA.LadderTree a69 = a67;
                    int i46 = i42;
                    int i47 = i43;
                    int i48 = i44;
                    com.cim.AIA.LadderTree a70 = a68;
                    com.cim.AIA.LadderTree a71 = a69;
                    int i49 = i46;
                    int i50 = i47;
                    int i51 = i48;
                    com.cim.AIA.LadderTree a72 = a68;
                    com.cim.AIA.LadderTree a73 = a69;
                    int i52 = i46;
                    int i53 = i47;
                    int i54 = i48;
                    if(i45 != 0)
                    {
                        a43 = a72;
                        a44 = a73;
                        i5 = i52;
                        i6 = i53;
                        i7 = i54;
                    }
                    else
                    {
                        com.cim.AIA.LadderTree a74 = a70;
                        com.cim.AIA.LadderTree a75 = a71;
                        int i55 = i49;
                        int i56 = i50;
                        int i57 = i51;
                        a38 = a74;
                        a39 = a75;
                        i = i55;
                        i0 = i56;
                        i1 = i57;
                        continue;
                    }
                    int i58 = s12.indexOf("%[");
                    com.cim.AIA.LadderTree a76 = a43;
                    com.cim.AIA.LadderTree a77 = a44;
                    int i59 = i5;
                    int i60 = i6;
                    int i61 = i7;
                    com.cim.AIA.LadderTree a78 = a76;
                    com.cim.AIA.LadderTree a79 = a77;
                    int i62 = i59;
                    int i63 = i60;
                    int i64 = i61;
                    com.cim.AIA.LadderTree a80 = a76;
                    com.cim.AIA.LadderTree a81 = a77;
                    int i65 = i59;
                    int i66 = i60;
                    int i67 = i61;
                    if(i58 == -1)
                    {
                        a45 = a80;
                        a46 = a81;
                        s5 = s12;
                        i8 = i65;
                        i9 = i66;
                        i10 = i67;
                    }
                    else
                    {
                        com.cim.AIA.LadderTree a82 = a78;
                        com.cim.AIA.LadderTree a83 = a79;
                        int i68 = i62;
                        int i69 = i63;
                        int i70 = i64;
                        String s13 = com.cim.AIA.CodeCanvas.replaceString(s12, "%[", "");
                        com.cim.AIA.LadderTree a84 = a82;
                        com.cim.AIA.LadderTree a85 = a83;
                        int i71 = i68;
                        int i72 = i69;
                        int i73 = i70;
                        com.cim.AIA.CodeCanvas.minmaxParent = a85;
                        com.cim.AIA.LadderTree a86 = a84;
                        com.cim.AIA.LadderTree a87 = a85;
                        int i74 = i71;
                        int i75 = i72;
                        int i76 = i73;
                        com.cim.AIA.CodeCanvas.inMinMax = true;
                        com.cim.AIA.LadderTree a88 = a86;
                        com.cim.AIA.LadderTree a89 = a87;
                        int i77 = i74;
                        int i78 = i75;
                        int i79 = i76;
                        com.cim.AIA.CodeCanvas.isLabelMinMax = true;
                        com.cim.AIA.LadderTree a90 = a88;
                        com.cim.AIA.LadderTree a91 = a89;
                        int i80 = i77;
                        int i81 = i78;
                        int i82 = i79;
                        com.cim.AIA.CodeCanvas.isFirstMinMax = false;
                        com.cim.AIA.LadderTree a92 = a90;
                        com.cim.AIA.LadderTree a93 = a91;
                        int i83 = i80;
                        int i84 = i81;
                        int i85 = i82;
                        com.cim.AIA.CodeCanvas.isLastMinMax = false;
                        a45 = a92;
                        a46 = a93;
                        s5 = s13;
                        i8 = i83;
                        i9 = i84;
                        i10 = i85;
                    }
                    int i86 = s5.indexOf("]%");
                    com.cim.AIA.LadderTree a94 = a45;
                    com.cim.AIA.LadderTree a95 = a46;
                    int i87 = i8;
                    int i88 = i9;
                    int i89 = i10;
                    com.cim.AIA.LadderTree a96 = a94;
                    com.cim.AIA.LadderTree a97 = a95;
                    int i90 = i87;
                    int i91 = i88;
                    int i92 = i89;
                    com.cim.AIA.LadderTree a98 = a94;
                    com.cim.AIA.LadderTree a99 = a95;
                    int i93 = i87;
                    int i94 = i88;
                    int i95 = i89;
                    if(i86 == -1)
                    {
                        a47 = a98;
                        a48 = a99;
                        s6 = s5;
                        i11 = i93;
                        i12 = i94;
                        i13 = i95;
                    }
                    else
                    {
                        com.cim.AIA.LadderTree a100 = a96;
                        com.cim.AIA.LadderTree a101 = a97;
                        int i96 = i90;
                        int i97 = i91;
                        int i98 = i92;
                        String s14 = com.cim.AIA.CodeCanvas.replaceString(s5, "]%", "");
                        com.cim.AIA.LadderTree a102 = a100;
                        com.cim.AIA.LadderTree a103 = a101;
                        int i99 = i96;
                        int i100 = i97;
                        int i101 = i98;
                        com.cim.AIA.CodeCanvas.isLastMinMax = true;
                        a47 = a102;
                        a48 = a103;
                        s6 = s14;
                        i11 = i99;
                        i12 = i100;
                        i13 = i101;
                    }
                    int i102 = s6.indexOf("~");
                    com.cim.AIA.LadderTree a104 = a47;
                    com.cim.AIA.LadderTree a105 = a48;
                    int i103 = i11;
                    int i104 = i12;
                    int i105 = i13;
                    com.cim.AIA.LadderTree a106 = a104;
                    com.cim.AIA.LadderTree a107 = a105;
                    int i106 = i103;
                    int i107 = i105;
                    com.cim.AIA.LadderTree a108 = a104;
                    com.cim.AIA.LadderTree a109 = a105;
                    int i108 = i103;
                    int i109 = i104;
                    int i110 = i105;
                    if(i102 == -1)
                    {
                        a49 = a108;
                        a50 = a109;
                        s7 = s6;
                        i14 = i108;
                        i15 = i109;
                        i16 = i110;
                    }
                    else
                    {
                        com.cim.AIA.LadderTree a110 = a106;
                        com.cim.AIA.LadderTree a111 = a107;
                        int i111 = i106;
                        int i112 = i107;
                        String s15 = com.cim.AIA.CodeCanvas.replaceString(s6, "~", "");
                        com.cim.AIA.LadderTree a112 = a110;
                        com.cim.AIA.LadderTree a113 = a111;
                        int i113 = i111;
                        int i114 = i112;
                        a49 = a112;
                        a50 = a113;
                        s7 = s15;
                        i14 = i113;
                        i15 = 1;
                        i16 = i114;
                    }
                    int i115 = s7.indexOf("`");
                    com.cim.AIA.LadderTree a114 = a49;
                    com.cim.AIA.LadderTree a115 = a50;
                    int i116 = i14;
                    int i117 = i15;
                    int i118 = i16;
                    com.cim.AIA.LadderTree a116 = a114;
                    com.cim.AIA.LadderTree a117 = a115;
                    int i119 = i116;
                    int i120 = i117;
                    com.cim.AIA.LadderTree a118 = a114;
                    com.cim.AIA.LadderTree a119 = a115;
                    int i121 = i116;
                    int i122 = i117;
                    int i123 = i118;
                    if(i115 == -1)
                    {
                        a51 = a118;
                        a52 = a119;
                        s8 = s7;
                        i17 = i121;
                        i18 = i122;
                        i19 = i123;
                    }
                    else
                    {
                        com.cim.AIA.LadderTree a120 = a116;
                        com.cim.AIA.LadderTree a121 = a117;
                        int i124 = i119;
                        int i125 = i120;
                        String s16 = com.cim.AIA.CodeCanvas.replaceString(s7, "`", "");
                        com.cim.AIA.LadderTree a122 = a120;
                        com.cim.AIA.LadderTree a123 = a121;
                        int i126 = i124;
                        int i127 = i125;
                        a51 = a122;
                        a52 = a123;
                        s8 = s16;
                        i17 = i126;
                        i18 = i127;
                        i19 = 1;
                    }
                    int i128 = s8.indexOf("@");
                    com.cim.AIA.LadderTree a124 = a51;
                    com.cim.AIA.LadderTree a125 = a52;
                    int i129 = i17;
                    int i130 = i18;
                    int i131 = i19;
                    com.cim.AIA.LadderTree a126 = a124;
                    com.cim.AIA.LadderTree a127 = a125;
                    int i132 = i129;
                    int i133 = i130;
                    int i134 = i131;
                    com.cim.AIA.LadderTree a128 = a124;
                    com.cim.AIA.LadderTree a129 = a125;
                    int i135 = i129;
                    int i136 = i130;
                    int i137 = i131;
                    if(i128 == -1)
                    {
                        a53 = a128;
                        a54 = a129;
                        s9 = s8;
                        i20 = i135;
                        i21 = 0;
                        i22 = i136;
                        i23 = i137;
                    }
                    else
                    {
                        com.cim.AIA.LadderTree a130 = a126;
                        com.cim.AIA.LadderTree a131 = a127;
                        int i138 = i132;
                        int i139 = i133;
                        int i140 = i134;
                        String s17 = com.cim.AIA.CodeCanvas.replaceString(s8, "@", "");
                        com.cim.AIA.LadderTree a132 = a130;
                        com.cim.AIA.LadderTree a133 = a131;
                        int i141 = i138;
                        int i142 = i139;
                        int i143 = i140;
                        a53 = a132;
                        a54 = a133;
                        s9 = s17;
                        i20 = i141;
                        i21 = 1;
                        i22 = i142;
                        i23 = i143;
                    }
                    int i144 = s9.indexOf("$");
                    com.cim.AIA.LadderTree a134 = a53;
                    com.cim.AIA.LadderTree a135 = a54;
                    int i145 = i20;
                    int i146 = i22;
                    int i147 = i23;
                    com.cim.AIA.LadderTree a136 = a134;
                    com.cim.AIA.LadderTree a137 = a135;
                    int i148 = i146;
                    int i149 = i147;
                    com.cim.AIA.LadderTree a138 = a134;
                    com.cim.AIA.LadderTree a139 = a135;
                    int i150 = i145;
                    int i151 = i146;
                    int i152 = i147;
                    if(i144 == -1)
                    {
                        a55 = a138;
                        a56 = a139;
                        s10 = s9;
                        i24 = i150;
                        i25 = i151;
                        i26 = i152;
                    }
                    else
                    {
                        com.cim.AIA.LadderTree a140 = a136;
                        com.cim.AIA.LadderTree a141 = a137;
                        int i153 = i148;
                        int i154 = i149;
                        String s18 = com.cim.AIA.CodeCanvas.replaceString(s9, "$", "");
                        com.cim.AIA.LadderTree a142 = a140;
                        com.cim.AIA.LadderTree a143 = a141;
                        int i155 = i153;
                        int i156 = i154;
                        a55 = a142;
                        a56 = a143;
                        s10 = s18;
                        i24 = 1;
                        i25 = i155;
                        i26 = i156;
                    }
                    label6: {
                        com.cim.AIA.LadderTree a144 = null;
                        com.cim.AIA.LadderTree a145 = null;
                        int i157 = 0;
                        int i158 = 0;
                        int i159 = 0;
                        com.cim.AIA.LadderTree a146 = a55;
                        com.cim.AIA.LadderTree a147 = a56;
                        int i160 = i24;
                        int i161 = i25;
                        int i162 = i26;
                        com.cim.AIA.LadderTree a148 = a55;
                        com.cim.AIA.LadderTree a149 = a56;
                        int i163 = i24;
                        int i164 = i25;
                        int i165 = i26;
                        if(i21 == 0)
                        {
                            a57 = a148;
                            a58 = a149;
                            i27 = i163;
                            i28 = i164;
                            i29 = i165;
                            break label6;
                        }
                        else
                        {
                            a144 = a146;
                            a145 = a147;
                            i157 = i160;
                            i158 = i161;
                            i159 = i162;
                        }
                        com.cim.AIA.Tree a150 = a145.getParent();
                        com.cim.AIA.LadderTree a151 = a144;
                        com.cim.AIA.LadderTree a152 = a145;
                        int i166 = i157;
                        int i167 = i158;
                        int i168 = i159;
                        com.cim.AIA.LadderTree dummy = (com.cim.AIA.LadderTree)a150;
                        com.cim.AIA.LadderTree a153 = (com.cim.AIA.LadderTree)a150;
                        com.cim.AIA.LadderTree a154 = a151;
                        com.cim.AIA.LadderTree a155 = a152;
                        int i169 = i166;
                        int i170 = i167;
                        int i171 = i168;
                        com.cim.AIA.LadderTree a156 = a154;
                        int i172 = i169;
                        int i173 = i170;
                        int i174 = i171;
                        com.cim.AIA.LadderTree a157 = a154;
                        com.cim.AIA.LadderTree a158 = a155;
                        int i175 = i169;
                        int i176 = i170;
                        int i177 = i171;
                        if(a153 == null)
                        {
                            a57 = a157;
                            a58 = a158;
                            i27 = i175;
                            i28 = i176;
                            i29 = i177;
                        }
                        else
                        {
                            com.cim.AIA.LadderTree a159 = a156;
                            int i178 = i172;
                            int i179 = i173;
                            int i180 = i174;
                            com.cim.AIA.Tree a160 = a155.getParent();
                            com.cim.AIA.LadderTree a161 = a159;
                            int i181 = i178;
                            int i182 = i179;
                            int i183 = i180;
                            com.cim.AIA.LadderTree dummy0 = (com.cim.AIA.LadderTree)a160;
                            com.cim.AIA.LadderTree a162 = (com.cim.AIA.LadderTree)a160;
                            com.cim.AIA.LadderTree a163 = a161;
                            int i184 = i181;
                            int i185 = i182;
                            int i186 = i183;
                            a57 = a163;
                            a58 = a162;
                            i27 = i184;
                            i28 = i185;
                            i29 = i186;
                        }
                    }
                    com.cim.AIA.LadderTree a164 = a57;
                    com.cim.AIA.LadderTree a165 = a58;
                    int i187 = i27;
                    int i188 = i28;
                    int i189 = i29;
                    java.util.StringTokenizer a166 = new java.util.StringTokenizer(s10, " ");
                    com.cim.AIA.LadderTree a167 = a164;
                    com.cim.AIA.LadderTree a168 = a165;
                    int i190 = i187;
                    int i191 = i188;
                    int i192 = i189;
                    com.cim.AIA.LadderTree a169 = a167;
                    com.cim.AIA.LadderTree a170 = a168;
                    String s19 = "";
                    int i193 = i190;
                    int i194 = i191;
                    int i195 = i192;
                    while(true)
                    {
                        int i196 = a166.hasMoreTokens()?1:0;
                        com.cim.AIA.LadderTree a171 = a169;
                        com.cim.AIA.LadderTree a172 = a170;
                        int i197 = i193;
                        int i198 = i194;
                        int i199 = i195;
                        com.cim.AIA.LadderTree a173 = a171;
                        com.cim.AIA.LadderTree a174 = a172;
                        int i200 = i197;
                        int i201 = i198;
                        int i202 = i199;
                        com.cim.AIA.LadderTree a175 = a171;
                        com.cim.AIA.LadderTree a176 = a172;
                        int i203 = i197;
                        int i204 = i198;
                        int i205 = i199;
                        if(i196 == 0)
                        {
                            a59 = a175;
                            a60 = a176;
                            i30 = i203;
                            i31 = i204;
                            i32 = i205;
                            break;
                        }
                        else
                        {
                            com.cim.AIA.LadderTree a177 = a173;
                            com.cim.AIA.LadderTree a178 = a174;
                            int i206 = i200;
                            int i207 = i201;
                            int i208 = i202;
                            com.cim.AIA.LadderTree a179 = a177;
                            com.cim.AIA.LadderTree a180 = a178;
                            int i209 = i206;
                            int i210 = i207;
                            int i211 = i208;
                            StringBuilder a181 = new StringBuilder();
                            com.cim.AIA.LadderTree a182 = a179;
                            com.cim.AIA.LadderTree a183 = a180;
                            int i212 = i209;
                            int i213 = i210;
                            int i214 = i211;
                            StringBuilder a184 = a181.append(s19);
                            com.cim.AIA.LadderTree a185 = a182;
                            com.cim.AIA.LadderTree a186 = a183;
                            int i215 = i212;
                            int i216 = i213;
                            int i217 = i214;
                            String s20 = a166.nextToken();
                            com.cim.AIA.LadderTree a187 = a185;
                            com.cim.AIA.LadderTree a188 = a186;
                            int i218 = i215;
                            int i219 = i216;
                            int i220 = i217;
                            StringBuilder a189 = a184.append(s20);
                            com.cim.AIA.LadderTree a190 = a187;
                            com.cim.AIA.LadderTree a191 = a188;
                            int i221 = i218;
                            int i222 = i219;
                            int i223 = i220;
                            StringBuilder a192 = a189.append(" ");
                            com.cim.AIA.LadderTree a193 = a190;
                            com.cim.AIA.LadderTree a194 = a191;
                            int i224 = i221;
                            int i225 = i222;
                            int i226 = i223;
                            String s21 = a192.toString();
                            com.cim.AIA.LadderTree a195 = a193;
                            com.cim.AIA.LadderTree a196 = a194;
                            int i227 = i224;
                            int i228 = i225;
                            int i229 = i226;
                            a169 = a195;
                            a170 = a196;
                            s19 = s21;
                            i193 = i227;
                            i194 = i228;
                            i195 = i229;
                        }
                    }
                    int i230 = s19.length();
                    com.cim.AIA.LadderTree a197 = a59;
                    com.cim.AIA.LadderTree a198 = a60;
                    int i231 = i30;
                    int i232 = i31;
                    int i233 = i32;
                    label7: {
                        com.cim.AIA.LadderTree a199 = null;
                        com.cim.AIA.LadderTree a200 = null;
                        int i234 = 0;
                        com.cim.AIA.LadderTree a201 = null;
                        com.cim.AIA.LadderTree a202 = null;
                        int i235 = 0;
                        com.cim.AIA.LadderTree a203 = null;
                        com.cim.AIA.LadderTree a204 = null;
                        int i236 = 0;
                        com.cim.AIA.LadderTree a205 = null;
                        com.cim.AIA.LadderTree a206 = null;
                        int i237 = 0;
                        com.cim.AIA.LadderTree a207 = a197;
                        com.cim.AIA.LadderTree a208 = a198;
                        int i238 = i233;
                        com.cim.AIA.LadderTree a209 = a197;
                        com.cim.AIA.LadderTree a210 = a198;
                        int i239 = i231;
                        int i240 = i232;
                        int i241 = i233;
                        if(i230 == 0)
                        {
                            a61 = a209;
                            a62 = a210;
                            i33 = i239;
                            i34 = i240;
                            i35 = i241;
                            break label7;
                        }
                        else
                        {
                            a199 = a207;
                            a200 = a208;
                            i234 = i238;
                        }
                        com.cim.AIA.LadderTree a211 = a199;
                        com.cim.AIA.LadderTree a212 = a200;
                        int i242 = i234;
                        com.cim.AIA.AlgorithmLine a213 = new com.cim.AIA.AlgorithmLine(s19);
                        com.cim.AIA.LadderTree a214 = a211;
                        com.cim.AIA.LadderTree a215 = a212;
                        int i243 = i242;
                        int i244 = com.cim.AIA.CodeCanvas.inMinMax?1:0;
                        com.cim.AIA.LadderTree a216 = a214;
                        com.cim.AIA.LadderTree a217 = a215;
                        int i245 = i243;
                        label8: {
                            com.cim.AIA.LadderTree a218 = null;
                            com.cim.AIA.LadderTree a219 = null;
                            int i246 = 0;
                            com.cim.AIA.LadderTree a220 = null;
                            com.cim.AIA.LadderTree a221 = null;
                            int i247 = 0;
                            com.cim.AIA.LadderTree a222 = null;
                            com.cim.AIA.LadderTree a223 = null;
                            int i248 = 0;
                            com.cim.AIA.LadderTree a224 = null;
                            com.cim.AIA.LadderTree a225 = null;
                            int i249 = 0;
                            com.cim.AIA.LadderTree a226 = null;
                            com.cim.AIA.LadderTree a227 = null;
                            int i250 = 0;
                            com.cim.AIA.LadderTree a228 = a216;
                            com.cim.AIA.LadderTree a229 = a217;
                            int i251 = i245;
                            com.cim.AIA.LadderTree a230 = a216;
                            com.cim.AIA.LadderTree a231 = a217;
                            int i252 = i245;
                            if(i244 == 0)
                            {
                                a201 = a230;
                                a202 = a231;
                                i235 = i252;
                                break label8;
                            }
                            else
                            {
                                a218 = a228;
                                a219 = a229;
                                i246 = i251;
                            }
                            int i253 = com.cim.AIA.CodeCanvas.isLabelMinMax?1:0;
                            com.cim.AIA.LadderTree a232 = a218;
                            com.cim.AIA.LadderTree a233 = a219;
                            int i254 = i246;
                            label10: {
                                com.cim.AIA.LadderTree a234 = null;
                                com.cim.AIA.LadderTree a235 = null;
                                int i255 = 0;
                                label9: {
                                    com.cim.AIA.LadderTree a236 = null;
                                    com.cim.AIA.LadderTree a237 = null;
                                    int i256 = 0;
                                    com.cim.AIA.LadderTree a238 = a232;
                                    com.cim.AIA.LadderTree a239 = a233;
                                    int i257 = i254;
                                    com.cim.AIA.LadderTree a240 = a232;
                                    com.cim.AIA.LadderTree a241 = a233;
                                    int i258 = i254;
                                    if(i253 == 0)
                                    {
                                        a234 = a240;
                                        a235 = a241;
                                        i255 = i258;
                                        break label9;
                                    }
                                    else
                                    {
                                        a236 = a238;
                                        a237 = a239;
                                        i256 = i257;
                                    }
                                    a213.setIsLabelMinMax(true);
                                    com.cim.AIA.LadderTree a242 = a236;
                                    com.cim.AIA.LadderTree a243 = a237;
                                    int i259 = i256;
                                    String s22 = a213.getLabel();
                                    com.cim.AIA.LadderTree a244 = a242;
                                    com.cim.AIA.LadderTree a245 = a243;
                                    int i260 = i259;
                                    com.cim.AIA.CodeCanvas.minmaxLabel = s22;
                                    com.cim.AIA.LadderTree a246 = a244;
                                    com.cim.AIA.LadderTree a247 = a245;
                                    int i261 = i260;
                                    com.cim.AIA.CodeCanvas.labelInstance = a213;
                                    com.cim.AIA.LadderTree a248 = a246;
                                    com.cim.AIA.LadderTree a249 = a247;
                                    int i262 = i261;
                                    com.cim.AIA.CodeCanvas.isLabelMinMax = false;
                                    com.cim.AIA.LadderTree a250 = a248;
                                    com.cim.AIA.LadderTree a251 = a249;
                                    int i263 = i262;
                                    com.cim.AIA.CodeCanvas.isFirstMinMax = true;
                                    com.cim.AIA.LadderTree a252 = a250;
                                    com.cim.AIA.LadderTree a253 = a251;
                                    int i264 = i263;
                                    a220 = a252;
                                    a221 = a253;
                                    i247 = i264;
                                    break label10;
                                }
                                int i265 = com.cim.AIA.CodeCanvas.isFirstMinMax?1:0;
                                com.cim.AIA.LadderTree a254 = a234;
                                com.cim.AIA.LadderTree a255 = a235;
                                int i266 = i255;
                                com.cim.AIA.LadderTree a256 = a254;
                                com.cim.AIA.LadderTree a257 = a255;
                                int i267 = i266;
                                com.cim.AIA.LadderTree a258 = a254;
                                com.cim.AIA.LadderTree a259 = a255;
                                int i268 = i266;
                                if(i265 == 0)
                                {
                                    a220 = a258;
                                    a221 = a259;
                                    i247 = i268;
                                }
                                else
                                {
                                    com.cim.AIA.LadderTree a260 = a256;
                                    com.cim.AIA.LadderTree a261 = a257;
                                    int i269 = i267;
                                    a213.setIsFirstMinMax(true);
                                    com.cim.AIA.LadderTree a262 = a260;
                                    com.cim.AIA.LadderTree a263 = a261;
                                    int i270 = i269;
                                    com.cim.AIA.CodeCanvas.firstInstance = a213;
                                    com.cim.AIA.LadderTree a264 = a262;
                                    com.cim.AIA.LadderTree a265 = a263;
                                    int i271 = i270;
                                    com.cim.AIA.CodeCanvas.isFirstMinMax = false;
                                    a220 = a264;
                                    a221 = a265;
                                    i247 = i271;
                                }
                            }
                            com.cim.AIA.LadderTree a266 = com.cim.AIA.CodeCanvas.minmaxParent;
                            com.cim.AIA.LadderTree a267 = a220;
                            com.cim.AIA.LadderTree a268 = a221;
                            int i272 = i247;
                            com.cim.AIA.LadderTree a269 = a267;
                            com.cim.AIA.LadderTree a270 = a268;
                            int i273 = i272;
                            com.cim.AIA.LadderTree a271 = a267;
                            com.cim.AIA.LadderTree a272 = a268;
                            int i274 = i272;
                            if(a221 != a266)
                            {
                                a222 = a271;
                                a223 = a272;
                                i248 = i274;
                            }
                            else
                            {
                                com.cim.AIA.LadderTree a273 = a269;
                                com.cim.AIA.LadderTree a274 = a270;
                                int i275 = i273;
                                com.cim.AIA.CodeCanvas.lastInstance = a213;
                                a222 = a273;
                                a223 = a274;
                                i248 = i275;
                            }
                            a213.setIsMinMax(true);
                            com.cim.AIA.LadderTree a275 = a222;
                            com.cim.AIA.LadderTree a276 = a223;
                            int i276 = i248;
                            String s23 = com.cim.AIA.CodeCanvas.minmaxLabel;
                            com.cim.AIA.LadderTree a277 = a275;
                            com.cim.AIA.LadderTree a278 = a276;
                            int i277 = i276;
                            a213.setMinMaxLabel(s23);
                            com.cim.AIA.LadderTree a279 = a277;
                            com.cim.AIA.LadderTree a280 = a278;
                            int i278 = i277;
                            com.cim.AIA.AlgorithmLine a281 = com.cim.AIA.CodeCanvas.firstInstance;
                            com.cim.AIA.LadderTree a282 = a279;
                            com.cim.AIA.LadderTree a283 = a280;
                            int i279 = i278;
                            a213.setFirstInstance(a281);
                            com.cim.AIA.LadderTree a284 = a282;
                            com.cim.AIA.LadderTree a285 = a283;
                            int i280 = i279;
                            com.cim.AIA.AlgorithmLine a286 = com.cim.AIA.CodeCanvas.labelInstance;
                            com.cim.AIA.LadderTree a287 = a284;
                            com.cim.AIA.LadderTree a288 = a285;
                            int i281 = i280;
                            a213.setLabelInstance(a286);
                            com.cim.AIA.LadderTree a289 = a287;
                            com.cim.AIA.LadderTree a290 = a288;
                            int i282 = i281;
                            int i283 = com.cim.AIA.CodeCanvas.isLastMinMax?1:0;
                            com.cim.AIA.LadderTree a291 = a289;
                            com.cim.AIA.LadderTree a292 = a290;
                            int i284 = i282;
                            com.cim.AIA.LadderTree a293 = a291;
                            com.cim.AIA.LadderTree a294 = a292;
                            int i285 = i284;
                            com.cim.AIA.LadderTree a295 = a291;
                            com.cim.AIA.LadderTree a296 = a292;
                            int i286 = i284;
                            if(i283 == 0)
                            {
                                a201 = a295;
                                a202 = a296;
                                i235 = i286;
                                break label8;
                            }
                            else
                            {
                                a224 = a293;
                                a225 = a294;
                                i249 = i285;
                            }
                            com.cim.AIA.LadderTree a297 = com.cim.AIA.CodeCanvas.minmaxParent;
                            com.cim.AIA.LadderTree a298 = a224;
                            com.cim.AIA.LadderTree a299 = a225;
                            int i287 = i249;
                            com.cim.AIA.LadderTree a300 = a298;
                            com.cim.AIA.LadderTree a301 = a299;
                            int i288 = i287;
                            com.cim.AIA.LadderTree a302 = a298;
                            com.cim.AIA.LadderTree a303 = a299;
                            int i289 = i287;
                            if(a225 != a297)
                            {
                                com.cim.AIA.LadderTree a304 = a302;
                                com.cim.AIA.LadderTree a305 = a303;
                                int i290 = i289;
                                com.cim.AIA.AlgorithmLine a306 = com.cim.AIA.CodeCanvas.lastInstance;
                                com.cim.AIA.LadderTree a307 = a304;
                                com.cim.AIA.LadderTree a308 = a305;
                                int i291 = i290;
                                a306.setIsLastMinMax(true);
                                a226 = a307;
                                a227 = a308;
                                i250 = i291;
                            }
                            else
                            {
                                com.cim.AIA.LadderTree a309 = a300;
                                com.cim.AIA.LadderTree a310 = a301;
                                int i292 = i288;
                                a213.setIsLastMinMax(true);
                                com.cim.AIA.LadderTree a311 = a309;
                                com.cim.AIA.LadderTree a312 = a310;
                                int i293 = i292;
                                a226 = a311;
                                a227 = a312;
                                i250 = i293;
                            }
                            com.cim.AIA.CodeCanvas.isLastMinMax = false;
                            com.cim.AIA.LadderTree a313 = a226;
                            com.cim.AIA.LadderTree a314 = a227;
                            int i294 = i250;
                            com.cim.AIA.CodeCanvas.inMinMax = false;
                            a201 = a313;
                            a202 = a314;
                            i235 = i294;
                        }
                        com.cim.AIA.LadderTree a315 = a201;
                        com.cim.AIA.LadderTree a316 = a202;
                        int i295 = i235;
                        com.cim.AIA.LadderTree a317 = new com.cim.AIA.LadderTree(a316, a213, a, a0);
                        com.cim.AIA.LadderTree a318 = a315;
                        com.cim.AIA.LadderTree a319 = a316;
                        int i296 = i295;
                        label11: {
                            com.cim.AIA.LadderTree a320 = null;
                            int i297 = 0;
                            com.cim.AIA.LadderTree a321 = null;
                            int i298 = 0;
                            com.cim.AIA.LadderTree a322 = a318;
                            int i299 = i296;
                            com.cim.AIA.LadderTree a323 = a318;
                            com.cim.AIA.LadderTree a324 = a319;
                            int i300 = i296;
                            if(i231 == 0)
                            {
                                a203 = a323;
                                a204 = a324;
                                i236 = i300;
                                break label11;
                            }
                            else
                            {
                                a320 = a322;
                                i297 = i299;
                            }
                            int i301 = i297;
                            com.cim.AIA.LadderTree a325 = a320;
                            int i302 = i297;
                            if(a319 != null)
                            {
                                a321 = a325;
                                i298 = i302;
                            }
                            else
                            {
                                int i303 = i301;
                                a321 = a317;
                                i298 = i303;
                            }
                            a203 = a321;
                            a204 = a317;
                            i236 = i298;
                        }
                        com.cim.AIA.LadderTree a326 = a203;
                        com.cim.AIA.LadderTree a327 = a204;
                        int i304 = i236;
                        com.cim.AIA.LadderTree a328 = a203;
                        com.cim.AIA.LadderTree a329 = a204;
                        int i305 = i236;
                        if(i232 == 0)
                        {
                            a205 = a328;
                            a206 = a329;
                            i237 = i305;
                        }
                        else
                        {
                            com.cim.AIA.LadderTree a330 = a326;
                            com.cim.AIA.LadderTree a331 = a327;
                            int i306 = i304;
                            a317.setAlwaysExpanded(true);
                            com.cim.AIA.LadderTree a332 = a330;
                            com.cim.AIA.LadderTree a333 = a331;
                            int i307 = i306;
                            a205 = a332;
                            a206 = a333;
                            i237 = i307;
                        }
                        com.cim.AIA.LadderTree a334 = a205;
                        com.cim.AIA.LadderTree a335 = a206;
                        com.cim.AIA.LadderTree a336 = a205;
                        com.cim.AIA.LadderTree a337 = a206;
                        int i308 = i237;
                        if(i237 == 0)
                        {
                            a61 = a336;
                            a62 = a337;
                            i33 = 0;
                            i34 = 0;
                            i35 = i308;
                        }
                        else
                        {
                            com.cim.AIA.LadderTree a338 = a334;
                            com.cim.AIA.LadderTree a339 = a335;
                            a317.setExpandAsOwn(true);
                            com.cim.AIA.LadderTree a340 = a338;
                            com.cim.AIA.LadderTree a341 = a339;
                            a61 = a340;
                            a62 = a341;
                            i33 = 0;
                            i34 = 0;
                            i35 = 0;
                        }
                    }
                    a38 = a61;
                    a39 = a62;
                    i = i33;
                    i0 = i34;
                    i1 = i35;
                }
                a1 = a40;
            }
            catch(java.io.IOException a342)
            {
                java.io.PrintStream a343 = System.out;
                StringBuilder a344 = new StringBuilder();
                StringBuilder a345 = a344.append("com.cim.AIA.CodeCanavs: IOException: ");
                StringBuilder a346 = a345.append((Object)a342);
                String s24 = a346.toString();
                a343.println(s24);
                a1 = null;
            }
        }
        return a1;
    }
    
    protected static String replaceString(String s, String s0, String s1)
    {
        String s2 = null;
        int i = s.indexOf(s0);
        if(i != -1)
        {
            String s3 = s.substring(0, i);
            int i0 = s0.length();
            int i1 = i + i0;
            String s4 = s.substring(i1);
            StringBuilder a = new StringBuilder();
            StringBuilder a0 = a.append(s3);
            StringBuilder a1 = a0.append(s1);
            StringBuilder a2 = a1.append(s4);
            String s5 = a2.toString();
            String s6 = com.cim.AIA.CodeCanvas.replaceString(s5, s0, s1);
            s2 = s6;
        }
        else
        {
            s2 = s;
        }
        return s2;
    }
    
    public CodeCanvas(java.applet.Applet a, String s, String s0, String s1)
    {
        this(a, s, s0, s1, "open.gif", "folder.gif");
    }
    
    public CodeCanvas(java.applet.Applet a, String s, String s0, String s1, String s2, String s3)
    {
        super();
        this.MINMAX_XGAP = 2;
        this.MINMAX_LINELENGTH = 5;
        java.awt.Color a0 = java.awt.Color.blue;
        this.commentColor = a0;
        java.awt.Color a1 = java.awt.Color.black;
        this.codeColor = a1;
        java.awt.Color a2 = java.awt.Color.white;
        this.backgroundColor = a2;
        java.awt.Color a3 = com.cim.AIA.CodeCanvas.DEFAULT_HIGHLIGHT_COLOR;
        this.highlightColor = a3;
        java.awt.Color a4 = java.awt.Color.gray;
        this.explainColor = a4;
        java.awt.Color a5 = java.awt.Color.red;
        this.breakPointColor = a5;
        java.util.Vector a6 = new java.util.Vector();
        this.explainationListeners = a6;
        java.util.Vector a7 = new java.util.Vector();
        this.helpListeners = a7;
        this.logoImage = null;
        this.expandedImage = null;
        this.collapsedImage = null;
        this.normalFontName = "Courier";
        this.normalFontSize = 12;
        String s4 = this.normalFontName;
        int i = this.normalFontSize;
        java.awt.Font a8 = new java.awt.Font(s4, 0, i);
        this.normalFont = a8;
        this.imageBuffer = 5;
        this.ySpacing = 2;
        this.currentPosition = "";
        this.dataPath = s;
        this.fileName = s0;
        this.expandedImageName = s2;
        this.collapsedImageName = s3;
        com.cim.AIA.ConfigurationManager a9 = com.cim.AIA.ConfigurationManager.getConfigurationManager();
        java.awt.Color a10 = this.highlightColor;
        String s5 = com.cim.AIA.CodeCanvas.CODE_POSITION_MARKER;
        com.cim.AIA.ColorSelection a11 = new com.cim.AIA.ColorSelection(a10, s5);
        a11.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        a9.addColorSelection(a11);
        java.awt.Color a12 = this.explainColor;
        String s6 = com.cim.AIA.CodeCanvas.EXPLANATION_POSITION_MARKER;
        com.cim.AIA.ColorSelection a13 = new com.cim.AIA.ColorSelection(a12, s6);
        a13.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        a9.addColorSelection(a13);
        java.awt.Color a14 = this.breakPointColor;
        String s7 = com.cim.AIA.CodeCanvas.BREAKPOINT_COLOR;
        com.cim.AIA.ColorSelection a15 = new com.cim.AIA.ColorSelection(a14, s7);
        a13.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        a9.addColorSelection(a15);
        java.awt.Color a16 = this.commentColor;
        String s8 = com.cim.AIA.CodeCanvas.COMMENT_LINE_COLOR;
        com.cim.AIA.ColorSelection a17 = new com.cim.AIA.ColorSelection(a16, s8);
        a17.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        a9.addColorSelection(a17);
        a9.addColorSelectionListener((com.cim.AIA.ColorSelectionListener)this);
        a9.addFontSelectionListener((com.cim.AIA.FontSelectionListener)this);
        java.awt.MediaTracker a18 = new java.awt.MediaTracker((java.awt.Component)this);
        label31: {
            com.cim.AIA.Logger a19 = null;
            label88: {
                java.net.MalformedURLException a20 = null;
                label1: {
                    Class a21 = null;
                    StringBuilder a22 = null;
                    StringBuilder a23 = null;
                    StringBuilder a24 = null;
                    String s9 = null;
                    java.io.InputStream a25 = null;
                    StringBuilder a26 = null;
                    java.net.URL a27 = null;
                    StringBuilder a28 = null;
                    StringBuilder a29 = null;
                    StringBuilder a30 = null;
                    String s10 = null;
                    java.net.URL a31 = null;
                    java.awt.Image a32 = null;
                    java.awt.Image a33 = null;
                    java.awt.Image a34 = null;
                    java.awt.Image a35 = null;
                    Class a36 = null;
                    StringBuilder a37 = null;
                    StringBuilder a38 = null;
                    StringBuilder a39 = null;
                    String s11 = null;
                    java.io.InputStream a40 = null;
                    StringBuilder a41 = null;
                    java.net.URL a42 = null;
                    StringBuilder a43 = null;
                    StringBuilder a44 = null;
                    StringBuilder a45 = null;
                    String s12 = null;
                    java.net.URL a46 = null;
                    java.awt.Image a47 = null;
                    java.awt.Image a48 = null;
                    java.awt.Image a49 = null;
                    java.awt.Image a50 = null;
                    Class a51 = null;
                    java.io.InputStream a52 = null;
                    StringBuilder a53 = null;
                    java.net.URL a54 = null;
                    StringBuilder a55 = null;
                    StringBuilder a56 = null;
                    StringBuilder a57 = null;
                    String s13 = null;
                    java.net.URL a58 = null;
                    java.awt.Image a59 = null;
                    java.awt.Image a60 = null;
                    Class a61 = null;
                    java.io.InputStream a62 = null;
                    com.cim.AIA.logoProcessor a63 = null;
                    java.awt.Image a64 = null;
                    StringBuilder a65 = null;
                    StringBuilder a66 = null;
                    StringBuilder a67 = null;
                    String s14 = null;
                    java.io.PrintStream a68 = null;
                    StringBuilder a69 = null;
                    StringBuilder a70 = null;
                    StringBuilder a71 = null;
                    String s15 = null;
                    label0: try
                    {
                        a21 = ((Object)a).getClass();
                        break label0;
                    }
                    catch(java.net.MalformedURLException a72)
                    {
                        a20 = a72;
                        break label1;
                    }
                    label2: try
                    {
                        a22 = new StringBuilder();
                        break label2;
                    }
                    catch(java.net.MalformedURLException a73)
                    {
                        a20 = a73;
                        break label1;
                    }
                    label3: try
                    {
                        a23 = a22.append("images/");
                        break label3;
                    }
                    catch(java.net.MalformedURLException a74)
                    {
                        a20 = a74;
                        break label1;
                    }
                    label4: try
                    {
                        a24 = a23.append(s2);
                        break label4;
                    }
                    catch(java.net.MalformedURLException a75)
                    {
                        a20 = a75;
                        break label1;
                    }
                    label5: try
                    {
                        s9 = a24.toString();
                        break label5;
                    }
                    catch(java.net.MalformedURLException a76)
                    {
                        a20 = a76;
                        break label1;
                    }
                    label6: try
                    {
                        a25 = a21.getResourceAsStream(s9);
                        break label6;
                    }
                    catch(java.net.MalformedURLException a77)
                    {
                        a20 = a77;
                        break label1;
                    }
                    label7: try
                    {
                        a26 = new StringBuilder();
                        break label7;
                    }
                    catch(java.net.MalformedURLException a78)
                    {
                        a20 = a78;
                        break label1;
                    }
                    label8: try
                    {
                        a27 = a.getCodeBase();
                        break label8;
                    }
                    catch(java.net.MalformedURLException a79)
                    {
                        a20 = a79;
                        break label1;
                    }
                    label9: try
                    {
                        a28 = a26.append((Object)a27);
                        break label9;
                    }
                    catch(java.net.MalformedURLException a80)
                    {
                        a20 = a80;
                        break label1;
                    }
                    label10: try
                    {
                        a29 = a28.append("images/");
                        break label10;
                    }
                    catch(java.net.MalformedURLException a81)
                    {
                        a20 = a81;
                        break label1;
                    }
                    label11: try
                    {
                        a30 = a29.append(s2);
                        break label11;
                    }
                    catch(java.net.MalformedURLException a82)
                    {
                        a20 = a82;
                        break label1;
                    }
                    label12: try
                    {
                        s10 = a30.toString();
                        break label12;
                    }
                    catch(java.net.MalformedURLException a83)
                    {
                        a20 = a83;
                        break label1;
                    }
                    label13: try
                    {
                        a31 = new java.net.URL(s10);
                        break label13;
                    }
                    catch(java.net.MalformedURLException a84)
                    {
                        a20 = a84;
                        break label1;
                    }
                    label14: try
                    {
                        a32 = com.cim.AIA.StreamImage.getImage(a25);
                        break label14;
                    }
                    catch(java.net.MalformedURLException a85)
                    {
                        a20 = a85;
                        break label1;
                    }
                    label15: try
                    {
                        this.expandedImage = a32;
                        break label15;
                    }
                    catch(java.net.MalformedURLException a86)
                    {
                        a20 = a86;
                        break label1;
                    }
                    label16: try
                    {
                        a33 = this.expandedImage;
                        break label16;
                    }
                    catch(java.net.MalformedURLException a87)
                    {
                        a20 = a87;
                        break label1;
                    }
                    label17: {
                        java.io.PrintStream a88 = null;
                        StringBuilder a89 = null;
                        StringBuilder a90 = null;
                        String s16 = null;
                        StringBuilder a91 = null;
                        String s17 = null;
                        Object a92 = null;
                        java.awt.Image a93 = null;
                        if(a33 != null)
                        {
                            break label17;
                        }
                        try
                        {
                            a88 = System.out;
                        }
                        catch(java.net.MalformedURLException a94)
                        {
                            a20 = a94;
                            break label1;
                        }
                        try
                        {
                            a89 = new StringBuilder();
                        }
                        catch(java.net.MalformedURLException a95)
                        {
                            a20 = a95;
                            break label1;
                        }
                        try
                        {
                            a90 = a89.append("Expanded Image is loading from local...");
                        }
                        catch(java.net.MalformedURLException a96)
                        {
                            a20 = a96;
                            break label1;
                        }
                        try
                        {
                            s16 = a31.toString();
                        }
                        catch(java.net.MalformedURLException a97)
                        {
                            a20 = a97;
                            break label1;
                        }
                        try
                        {
                            a91 = a90.append(s16);
                        }
                        catch(java.net.MalformedURLException a98)
                        {
                            a20 = a98;
                            break label1;
                        }
                        try
                        {
                            s17 = a91.toString();
                        }
                        catch(java.net.MalformedURLException a99)
                        {
                            a20 = a99;
                            break label1;
                        }
                        try
                        {
                            a88.println(s17);
                        }
                        catch(java.net.MalformedURLException a100)
                        {
                            a20 = a100;
                            break label1;
                        }
                        try
                        {
                            a92 = a.getAppletContext();
                        }
                        catch(java.net.MalformedURLException a101)
                        {
                            a20 = a101;
                            break label1;
                        }
                        try
                        {
                            a93 = ((java.applet.AppletContext)a92).getImage(a31);
                        }
                        catch(java.net.MalformedURLException a102)
                        {
                            a20 = a102;
                            break label1;
                        }
                        try
                        {
                            this.expandedImage = a93;
                        }
                        catch(java.net.MalformedURLException a103)
                        {
                            a20 = a103;
                            break label1;
                        }
                    }
                    label18: try
                    {
                        a34 = this.expandedImage;
                        break label18;
                    }
                    catch(java.net.MalformedURLException a104)
                    {
                        a20 = a104;
                        break label1;
                    }
                    label19: {
                        StringBuilder a105 = null;
                        StringBuilder a106 = null;
                        StringBuilder a107 = null;
                        String s18 = null;
                        java.io.PrintStream a108 = null;
                        StringBuilder a109 = null;
                        StringBuilder a110 = null;
                        StringBuilder a111 = null;
                        String s19 = null;
                        if(a34 != null)
                        {
                            break label19;
                        }
                        label20: try
                        {
                            a105 = new StringBuilder();
                            break label20;
                        }
                        catch(java.net.MalformedURLException a112)
                        {
                            a20 = a112;
                            break label1;
                        }
                        label21: try
                        {
                            a106 = a105.append("com.cim.AIA.CodeCanvas: Unable to load image: ");
                            break label21;
                        }
                        catch(java.net.MalformedURLException a113)
                        {
                            a20 = a113;
                            break label1;
                        }
                        label22: try
                        {
                            a107 = a106.append((Object)a31);
                            break label22;
                        }
                        catch(java.net.MalformedURLException a114)
                        {
                            a20 = a114;
                            break label1;
                        }
                        label23: try
                        {
                            s18 = a107.toString();
                            break label23;
                        }
                        catch(java.net.MalformedURLException a115)
                        {
                            a20 = a115;
                            break label1;
                        }
                        label24: try
                        {
                            a.showStatus(s18);
                            break label24;
                        }
                        catch(java.net.MalformedURLException a116)
                        {
                            a20 = a116;
                            break label1;
                        }
                        label25: try
                        {
                            a108 = System.out;
                            break label25;
                        }
                        catch(java.net.MalformedURLException a117)
                        {
                            a20 = a117;
                            break label1;
                        }
                        label26: try
                        {
                            a109 = new StringBuilder();
                            break label26;
                        }
                        catch(java.net.MalformedURLException a118)
                        {
                            a20 = a118;
                            break label1;
                        }
                        label27: try
                        {
                            a110 = a109.append("com.cim.AIA.CodeCanvas:  Unable to load image: ");
                            break label27;
                        }
                        catch(java.net.MalformedURLException a119)
                        {
                            a20 = a119;
                            break label1;
                        }
                        label28: try
                        {
                            a111 = a110.append((Object)a31);
                            break label28;
                        }
                        catch(java.net.MalformedURLException a120)
                        {
                            a20 = a120;
                            break label1;
                        }
                        label29: try
                        {
                            s19 = a111.toString();
                            break label29;
                        }
                        catch(java.net.MalformedURLException a121)
                        {
                            a20 = a121;
                            break label1;
                        }
                        label30: try
                        {
                            a108.println(s19);
                            break label30;
                        }
                        catch(java.net.MalformedURLException a122)
                        {
                            a20 = a122;
                            break label1;
                        }
                        break label31;
                    }
                    label32: try
                    {
                        a35 = this.expandedImage;
                        break label32;
                    }
                    catch(java.net.MalformedURLException a123)
                    {
                        a20 = a123;
                        break label1;
                    }
                    label33: try
                    {
                        a18.addImage(a35, 0);
                        break label33;
                    }
                    catch(java.net.MalformedURLException a124)
                    {
                        a20 = a124;
                        break label1;
                    }
                    label34: try
                    {
                        a36 = ((Object)a).getClass();
                        break label34;
                    }
                    catch(java.net.MalformedURLException a125)
                    {
                        a20 = a125;
                        break label1;
                    }
                    label35: try
                    {
                        a37 = new StringBuilder();
                        break label35;
                    }
                    catch(java.net.MalformedURLException a126)
                    {
                        a20 = a126;
                        break label1;
                    }
                    label36: try
                    {
                        a38 = a37.append("images/");
                        break label36;
                    }
                    catch(java.net.MalformedURLException a127)
                    {
                        a20 = a127;
                        break label1;
                    }
                    label37: try
                    {
                        a39 = a38.append(s3);
                        break label37;
                    }
                    catch(java.net.MalformedURLException a128)
                    {
                        a20 = a128;
                        break label1;
                    }
                    label38: try
                    {
                        s11 = a39.toString();
                        break label38;
                    }
                    catch(java.net.MalformedURLException a129)
                    {
                        a20 = a129;
                        break label1;
                    }
                    label39: try
                    {
                        a40 = a36.getResourceAsStream(s11);
                        break label39;
                    }
                    catch(java.net.MalformedURLException a130)
                    {
                        a20 = a130;
                        break label1;
                    }
                    label40: try
                    {
                        a41 = new StringBuilder();
                        break label40;
                    }
                    catch(java.net.MalformedURLException a131)
                    {
                        a20 = a131;
                        break label1;
                    }
                    label41: try
                    {
                        a42 = a.getCodeBase();
                        break label41;
                    }
                    catch(java.net.MalformedURLException a132)
                    {
                        a20 = a132;
                        break label1;
                    }
                    label42: try
                    {
                        a43 = a41.append((Object)a42);
                        break label42;
                    }
                    catch(java.net.MalformedURLException a133)
                    {
                        a20 = a133;
                        break label1;
                    }
                    label43: try
                    {
                        a44 = a43.append("images/");
                        break label43;
                    }
                    catch(java.net.MalformedURLException a134)
                    {
                        a20 = a134;
                        break label1;
                    }
                    label44: try
                    {
                        a45 = a44.append(s3);
                        break label44;
                    }
                    catch(java.net.MalformedURLException a135)
                    {
                        a20 = a135;
                        break label1;
                    }
                    label45: try
                    {
                        s12 = a45.toString();
                        break label45;
                    }
                    catch(java.net.MalformedURLException a136)
                    {
                        a20 = a136;
                        break label1;
                    }
                    label46: try
                    {
                        a46 = new java.net.URL(s12);
                        break label46;
                    }
                    catch(java.net.MalformedURLException a137)
                    {
                        a20 = a137;
                        break label1;
                    }
                    label47: try
                    {
                        a47 = com.cim.AIA.StreamImage.getImage(a40);
                        break label47;
                    }
                    catch(java.net.MalformedURLException a138)
                    {
                        a20 = a138;
                        break label1;
                    }
                    label48: try
                    {
                        this.collapsedImage = a47;
                        break label48;
                    }
                    catch(java.net.MalformedURLException a139)
                    {
                        a20 = a139;
                        break label1;
                    }
                    label49: try
                    {
                        a48 = this.collapsedImage;
                        break label49;
                    }
                    catch(java.net.MalformedURLException a140)
                    {
                        a20 = a140;
                        break label1;
                    }
                    label50: {
                        java.io.PrintStream a141 = null;
                        StringBuilder a142 = null;
                        StringBuilder a143 = null;
                        String s20 = null;
                        StringBuilder a144 = null;
                        String s21 = null;
                        Object a145 = null;
                        java.awt.Image a146 = null;
                        if(a48 != null)
                        {
                            break label50;
                        }
                        try
                        {
                            a141 = System.out;
                        }
                        catch(java.net.MalformedURLException a147)
                        {
                            a20 = a147;
                            break label1;
                        }
                        try
                        {
                            a142 = new StringBuilder();
                        }
                        catch(java.net.MalformedURLException a148)
                        {
                            a20 = a148;
                            break label1;
                        }
                        try
                        {
                            a143 = a142.append("Collapsed Image is loading from local...");
                        }
                        catch(java.net.MalformedURLException a149)
                        {
                            a20 = a149;
                            break label1;
                        }
                        try
                        {
                            s20 = a46.toString();
                        }
                        catch(java.net.MalformedURLException a150)
                        {
                            a20 = a150;
                            break label1;
                        }
                        try
                        {
                            a144 = a143.append(s20);
                        }
                        catch(java.net.MalformedURLException a151)
                        {
                            a20 = a151;
                            break label1;
                        }
                        try
                        {
                            s21 = a144.toString();
                        }
                        catch(java.net.MalformedURLException a152)
                        {
                            a20 = a152;
                            break label1;
                        }
                        try
                        {
                            a141.println(s21);
                        }
                        catch(java.net.MalformedURLException a153)
                        {
                            a20 = a153;
                            break label1;
                        }
                        try
                        {
                            a145 = a.getAppletContext();
                        }
                        catch(java.net.MalformedURLException a154)
                        {
                            a20 = a154;
                            break label1;
                        }
                        try
                        {
                            a146 = ((java.applet.AppletContext)a145).getImage(a46);
                        }
                        catch(java.net.MalformedURLException a155)
                        {
                            a20 = a155;
                            break label1;
                        }
                        try
                        {
                            this.collapsedImage = a146;
                        }
                        catch(java.net.MalformedURLException a156)
                        {
                            a20 = a156;
                            break label1;
                        }
                    }
                    label51: try
                    {
                        a49 = this.collapsedImage;
                        break label51;
                    }
                    catch(java.net.MalformedURLException a157)
                    {
                        a20 = a157;
                        break label1;
                    }
                    label52: {
                        StringBuilder a158 = null;
                        StringBuilder a159 = null;
                        StringBuilder a160 = null;
                        String s22 = null;
                        java.io.PrintStream a161 = null;
                        StringBuilder a162 = null;
                        StringBuilder a163 = null;
                        StringBuilder a164 = null;
                        String s23 = null;
                        if(a49 != null)
                        {
                            break label52;
                        }
                        label53: try
                        {
                            a158 = new StringBuilder();
                            break label53;
                        }
                        catch(java.net.MalformedURLException a165)
                        {
                            a20 = a165;
                            break label1;
                        }
                        label54: try
                        {
                            a159 = a158.append("com.cim.AIA.CodeCanvas: Unable to load image: ");
                            break label54;
                        }
                        catch(java.net.MalformedURLException a166)
                        {
                            a20 = a166;
                            break label1;
                        }
                        label55: try
                        {
                            a160 = a159.append((Object)a46);
                            break label55;
                        }
                        catch(java.net.MalformedURLException a167)
                        {
                            a20 = a167;
                            break label1;
                        }
                        label56: try
                        {
                            s22 = a160.toString();
                            break label56;
                        }
                        catch(java.net.MalformedURLException a168)
                        {
                            a20 = a168;
                            break label1;
                        }
                        label57: try
                        {
                            a.showStatus(s22);
                            break label57;
                        }
                        catch(java.net.MalformedURLException a169)
                        {
                            a20 = a169;
                            break label1;
                        }
                        label58: try
                        {
                            a161 = System.out;
                            break label58;
                        }
                        catch(java.net.MalformedURLException a170)
                        {
                            a20 = a170;
                            break label1;
                        }
                        label59: try
                        {
                            a162 = new StringBuilder();
                            break label59;
                        }
                        catch(java.net.MalformedURLException a171)
                        {
                            a20 = a171;
                            break label1;
                        }
                        label60: try
                        {
                            a163 = a162.append("com.cim.AIA.CodeCanvas: Unable to load image: ");
                            break label60;
                        }
                        catch(java.net.MalformedURLException a172)
                        {
                            a20 = a172;
                            break label1;
                        }
                        label61: try
                        {
                            a164 = a163.append((Object)a46);
                            break label61;
                        }
                        catch(java.net.MalformedURLException a173)
                        {
                            a20 = a173;
                            break label1;
                        }
                        label62: try
                        {
                            s23 = a164.toString();
                            break label62;
                        }
                        catch(java.net.MalformedURLException a174)
                        {
                            a20 = a174;
                            break label1;
                        }
                        label63: try
                        {
                            a161.println(s23);
                            break label63;
                        }
                        catch(java.net.MalformedURLException a175)
                        {
                            a20 = a175;
                            break label1;
                        }
                        break label31;
                    }
                    label64: try
                    {
                        a50 = this.collapsedImage;
                        break label64;
                    }
                    catch(java.net.MalformedURLException a176)
                    {
                        a20 = a176;
                        break label1;
                    }
                    label65: try
                    {
                        a18.addImage(a50, 0);
                        break label65;
                    }
                    catch(java.net.MalformedURLException a177)
                    {
                        a20 = a177;
                        break label1;
                    }
                    label66: try
                    {
                        a51 = ((Object)a).getClass();
                        break label66;
                    }
                    catch(java.net.MalformedURLException a178)
                    {
                        a20 = a178;
                        break label1;
                    }
                    label67: try
                    {
                        a52 = a51.getResourceAsStream("images/logo.gif");
                        break label67;
                    }
                    catch(java.net.MalformedURLException a179)
                    {
                        a20 = a179;
                        break label1;
                    }
                    label68: try
                    {
                        a53 = new StringBuilder();
                        break label68;
                    }
                    catch(java.net.MalformedURLException a180)
                    {
                        a20 = a180;
                        break label1;
                    }
                    label69: try
                    {
                        a54 = a.getCodeBase();
                        break label69;
                    }
                    catch(java.net.MalformedURLException a181)
                    {
                        a20 = a181;
                        break label1;
                    }
                    label70: try
                    {
                        a55 = a53.append((Object)a54);
                        break label70;
                    }
                    catch(java.net.MalformedURLException a182)
                    {
                        a20 = a182;
                        break label1;
                    }
                    label71: try
                    {
                        a56 = a55.append("images/");
                        break label71;
                    }
                    catch(java.net.MalformedURLException a183)
                    {
                        a20 = a183;
                        break label1;
                    }
                    label72: try
                    {
                        a57 = a56.append("logo.gif");
                        break label72;
                    }
                    catch(java.net.MalformedURLException a184)
                    {
                        a20 = a184;
                        break label1;
                    }
                    label73: try
                    {
                        s13 = a57.toString();
                        break label73;
                    }
                    catch(java.net.MalformedURLException a185)
                    {
                        a20 = a185;
                        break label1;
                    }
                    label74: try
                    {
                        a58 = new java.net.URL(s13);
                        break label74;
                    }
                    catch(java.net.MalformedURLException a186)
                    {
                        a20 = a186;
                        break label1;
                    }
                    label75: try
                    {
                        a59 = com.cim.AIA.StreamImage.getImage(a52);
                        break label75;
                    }
                    catch(java.net.MalformedURLException a187)
                    {
                        a20 = a187;
                        break label1;
                    }
                    label76: try
                    {
                        this.logoImage = a59;
                        break label76;
                    }
                    catch(java.net.MalformedURLException a188)
                    {
                        a20 = a188;
                        break label1;
                    }
                    label77: try
                    {
                        a60 = this.logoImage;
                        break label77;
                    }
                    catch(java.net.MalformedURLException a189)
                    {
                        a20 = a189;
                        break label1;
                    }
                    label78: {
                        java.io.PrintStream a190 = null;
                        StringBuilder a191 = null;
                        StringBuilder a192 = null;
                        String s24 = null;
                        StringBuilder a193 = null;
                        String s25 = null;
                        Object a194 = null;
                        java.awt.Image a195 = null;
                        if(a60 != null)
                        {
                            break label78;
                        }
                        try
                        {
                            a190 = System.out;
                        }
                        catch(java.net.MalformedURLException a196)
                        {
                            a20 = a196;
                            break label1;
                        }
                        try
                        {
                            a191 = new StringBuilder();
                        }
                        catch(java.net.MalformedURLException a197)
                        {
                            a20 = a197;
                            break label1;
                        }
                        try
                        {
                            a192 = a191.append("Logo Image is loading from local...");
                        }
                        catch(java.net.MalformedURLException a198)
                        {
                            a20 = a198;
                            break label1;
                        }
                        try
                        {
                            s24 = a58.toString();
                        }
                        catch(java.net.MalformedURLException a199)
                        {
                            a20 = a199;
                            break label1;
                        }
                        try
                        {
                            a193 = a192.append(s24);
                        }
                        catch(java.net.MalformedURLException a200)
                        {
                            a20 = a200;
                            break label1;
                        }
                        try
                        {
                            s25 = a193.toString();
                        }
                        catch(java.net.MalformedURLException a201)
                        {
                            a20 = a201;
                            break label1;
                        }
                        try
                        {
                            a190.println(s25);
                        }
                        catch(java.net.MalformedURLException a202)
                        {
                            a20 = a202;
                            break label1;
                        }
                        try
                        {
                            a194 = a.getAppletContext();
                        }
                        catch(java.net.MalformedURLException a203)
                        {
                            a20 = a203;
                            break label1;
                        }
                        try
                        {
                            a195 = ((java.applet.AppletContext)a194).getImage(a58);
                        }
                        catch(java.net.MalformedURLException a204)
                        {
                            a20 = a204;
                            break label1;
                        }
                        try
                        {
                            this.logoImage = a195;
                        }
                        catch(java.net.MalformedURLException a205)
                        {
                            a20 = a205;
                            break label1;
                        }
                    }
                    label79: try
                    {
                        a61 = ((Object)a).getClass();
                        break label79;
                    }
                    catch(java.net.MalformedURLException a206)
                    {
                        a20 = a206;
                        break label1;
                    }
                    label80: try
                    {
                        a62 = a61.getResourceAsStream("images/logo.gif");
                        break label80;
                    }
                    catch(java.net.MalformedURLException a207)
                    {
                        a20 = a207;
                        break label1;
                    }
                    label81: try
                    {
                        a63 = new com.cim.AIA.logoProcessor(a58);
                        break label81;
                    }
                    catch(java.net.MalformedURLException a208)
                    {
                        a20 = a208;
                        break label1;
                    }
                    {
                        label83: {
                            label82: {
                                int i0 = 0;
                                java.net.URL a209 = null;
                                Object a210 = null;
                                java.awt.Image a211 = null;
                                try
                                {
                                    try
                                    {
                                        i0 = a63.checkFile(a62)?1:0;
                                    }
                                    catch(SecurityException ignoredException)
                                    {
                                        break label82;
                                    }
                                }
                                catch(java.net.MalformedURLException a212)
                                {
                                    a20 = a212;
                                    break label1;
                                }
                                if(i0 != 0)
                                {
                                    break label83;
                                }
                                try
                                {
                                    try
                                    {
                                        a209 = new java.net.URL("http://ww2.cs.mu.oz.au/muaia/images/logo.gif");
                                    }
                                    catch(SecurityException ignoredException0)
                                    {
                                        break label82;
                                    }
                                }
                                catch(java.net.MalformedURLException a213)
                                {
                                    a20 = a213;
                                    break label1;
                                }
                                try
                                {
                                    try
                                    {
                                        a210 = a.getAppletContext();
                                    }
                                    catch(SecurityException ignoredException1)
                                    {
                                        break label82;
                                    }
                                }
                                catch(java.net.MalformedURLException a214)
                                {
                                    a20 = a214;
                                    break label1;
                                }
                                try
                                {
                                    try
                                    {
                                        a211 = ((java.applet.AppletContext)a210).getImage(a209);
                                    }
                                    catch(SecurityException ignoredException2)
                                    {
                                        break label82;
                                    }
                                }
                                catch(java.net.MalformedURLException a215)
                                {
                                    a20 = a215;
                                    break label1;
                                }
                                try
                                {
                                    try
                                    {
                                        this.logoImage = a211;
                                    }
                                    catch(SecurityException ignoredException3)
                                    {
                                    }
                                }
                                catch(java.net.MalformedURLException a216)
                                {
                                    a20 = a216;
                                    break label1;
                                }
                            }
                        }
                    }
                    label84: try
                    {
                        a64 = this.logoImage;
                        break label84;
                    }
                    catch(java.net.MalformedURLException a217)
                    {
                        a20 = a217;
                        break label1;
                    }
                    label85: {
                        java.awt.Image a218 = null;
                        if(a64 == null)
                        {
                            break label85;
                        }
                        label86: try
                        {
                            a218 = this.logoImage;
                            break label86;
                        }
                        catch(java.net.MalformedURLException a219)
                        {
                            a20 = a219;
                            break label1;
                        }
                        label87: try
                        {
                            a18.addImage(a218, 0);
                            break label87;
                        }
                        catch(java.net.MalformedURLException a220)
                        {
                            a20 = a220;
                            break label1;
                        }
                        break label88;
                    }
                    label89: try
                    {
                        a65 = new StringBuilder();
                        break label89;
                    }
                    catch(java.net.MalformedURLException a221)
                    {
                        a20 = a221;
                        break label1;
                    }
                    label90: try
                    {
                        a66 = a65.append("com.cim.AIA.CodeCanvas: Unable to load image: ");
                        break label90;
                    }
                    catch(java.net.MalformedURLException a222)
                    {
                        a20 = a222;
                        break label1;
                    }
                    label91: try
                    {
                        a67 = a66.append((Object)a58);
                        break label91;
                    }
                    catch(java.net.MalformedURLException a223)
                    {
                        a20 = a223;
                        break label1;
                    }
                    label92: try
                    {
                        s14 = a67.toString();
                        break label92;
                    }
                    catch(java.net.MalformedURLException a224)
                    {
                        a20 = a224;
                        break label1;
                    }
                    label93: try
                    {
                        a.showStatus(s14);
                        break label93;
                    }
                    catch(java.net.MalformedURLException a225)
                    {
                        a20 = a225;
                        break label1;
                    }
                    label94: try
                    {
                        a68 = System.out;
                        break label94;
                    }
                    catch(java.net.MalformedURLException a226)
                    {
                        a20 = a226;
                        break label1;
                    }
                    label95: try
                    {
                        a69 = new StringBuilder();
                        break label95;
                    }
                    catch(java.net.MalformedURLException a227)
                    {
                        a20 = a227;
                        break label1;
                    }
                    label96: try
                    {
                        a70 = a69.append("com.cim.AIA.CodeCanvas: Unable to load image: ");
                        break label96;
                    }
                    catch(java.net.MalformedURLException a228)
                    {
                        a20 = a228;
                        break label1;
                    }
                    label97: try
                    {
                        a71 = a70.append((Object)a58);
                        break label97;
                    }
                    catch(java.net.MalformedURLException a229)
                    {
                        a20 = a229;
                        break label1;
                    }
                    label98: try
                    {
                        s15 = a71.toString();
                        break label98;
                    }
                    catch(java.net.MalformedURLException a230)
                    {
                        a20 = a230;
                        break label1;
                    }
                    label99: try
                    {
                        a68.println(s15);
                        break label99;
                    }
                    catch(java.net.MalformedURLException a231)
                    {
                        a20 = a231;
                        break label1;
                    }
                    break label31;
                }
                StringBuilder a232 = new StringBuilder();
                StringBuilder a233 = a232.append("com.cim.AIA.CodeCanvas: Invalid URL: ");
                StringBuilder a234 = a233.append((Object)a20);
                String s26 = a234.toString();
                a.showStatus(s26);
                java.io.PrintStream a235 = System.out;
                StringBuilder a236 = new StringBuilder();
                StringBuilder a237 = a236.append("com.cim.AIA.CodeCanavas: Invalid URL: ");
                StringBuilder a238 = a237.append((Object)a20);
                String s27 = a238.toString();
                a235.println(s27);
            }
            try
            {
                a18.waitForAll();
            }
            catch(InterruptedException ignoredException4)
            {
            }
            java.awt.Image a239 = this.expandedImage;
            int i1 = a239.getWidth((java.awt.image.ImageObserver)this);
            java.awt.Image a240 = this.expandedImage;
            int i2 = a240.getHeight((java.awt.image.ImageObserver)this);
            java.awt.Dimension a241 = new java.awt.Dimension(i1, i2);
            this.expandedImageSize = a241;
            java.awt.Image a242 = this.collapsedImage;
            int i3 = a242.getWidth((java.awt.image.ImageObserver)this);
            java.awt.Image a243 = this.collapsedImage;
            int i4 = a243.getHeight((java.awt.image.ImageObserver)this);
            java.awt.Dimension a244 = new java.awt.Dimension(i3, i4);
            this.collapsedImageSize = a244;
            java.awt.Image a245 = this.logoImage;
            int i5 = a245.getWidth((java.awt.image.ImageObserver)this);
            java.awt.Image a246 = this.logoImage;
            int i6 = a246.getHeight((java.awt.image.ImageObserver)this);
            java.awt.Dimension a247 = new java.awt.Dimension(i5, i6);
            this.logoImageSize = a247;
            Class a248 = ((Object)a).getClass();
            int i7 = com.cim.AIA.AlgorithmApplet.class.isAssignableFrom(a248)?1:0;
            if(i7 == 0)
            {
                com.cim.AIA.BreakPoint a249 = new com.cim.AIA.BreakPoint();
                this.breakpoint = a249;
                a19 = null;
            }
            else
            {
                com.cim.AIA.AlgorithmApplet dummy = (com.cim.AIA.AlgorithmApplet)a;
                com.cim.AIA.AlgorithmApplet a250 = (com.cim.AIA.AlgorithmApplet)a;
                com.cim.AIA.Logger a251 = a250.getLogger();
                com.cim.AIA.AlgorithmApplet dummy0 = (com.cim.AIA.AlgorithmApplet)a;
                com.cim.AIA.AlgorithmApplet a252 = (com.cim.AIA.AlgorithmApplet)a;
                com.cim.AIA.BreakPoint a253 = a252.getBreakPoint();
                this.breakpoint = a253;
                a19 = a251;
            }
            com.cim.AIA.BreakPoint a254 = this.breakpoint;
            this.initialiseTree(s, s0, a19, a254);
            this.addMouseListener((java.awt.event.MouseListener)this);
            this.addMouseMotionListener((java.awt.event.MouseMotionListener)this);
        }
    }
    
    protected CodeCanvas(java.awt.Image a, java.awt.Image a0, com.cim.AIA.Logger a1, com.cim.AIA.BreakPoint a2)
    {
        super();
        this.MINMAX_XGAP = 2;
        this.MINMAX_LINELENGTH = 5;
        java.awt.Color a3 = java.awt.Color.blue;
        this.commentColor = a3;
        java.awt.Color a4 = java.awt.Color.black;
        this.codeColor = a4;
        java.awt.Color a5 = java.awt.Color.white;
        this.backgroundColor = a5;
        java.awt.Color a6 = com.cim.AIA.CodeCanvas.DEFAULT_HIGHLIGHT_COLOR;
        this.highlightColor = a6;
        java.awt.Color a7 = java.awt.Color.gray;
        this.explainColor = a7;
        java.awt.Color a8 = java.awt.Color.red;
        this.breakPointColor = a8;
        java.util.Vector a9 = new java.util.Vector();
        this.explainationListeners = a9;
        java.util.Vector a10 = new java.util.Vector();
        this.helpListeners = a10;
        this.logoImage = null;
        this.expandedImage = null;
        this.collapsedImage = null;
        this.normalFontName = "Courier";
        this.normalFontSize = 12;
        String s = this.normalFontName;
        int i = this.normalFontSize;
        java.awt.Font a11 = new java.awt.Font(s, 0, i);
        this.normalFont = a11;
        this.imageBuffer = 5;
        this.ySpacing = 2;
        this.currentPosition = "";
        this.expandedImage = a;
        this.collapsedImage = a0;
        this.breakpoint = a2;
        java.awt.Image a12 = this.expandedImage;
        int i0 = a12.getWidth((java.awt.image.ImageObserver)this);
        java.awt.Image a13 = this.expandedImage;
        int i1 = a13.getHeight((java.awt.image.ImageObserver)this);
        java.awt.Dimension a14 = new java.awt.Dimension(i0, i1);
        this.expandedImageSize = a14;
        java.awt.Image a15 = this.collapsedImage;
        int i2 = a15.getWidth((java.awt.image.ImageObserver)this);
        java.awt.Image a16 = this.collapsedImage;
        int i3 = a16.getHeight((java.awt.image.ImageObserver)this);
        java.awt.Dimension a17 = new java.awt.Dimension(i2, i3);
        this.collapsedImageSize = a17;
        this.addMouseListener((java.awt.event.MouseListener)this);
        this.addMouseMotionListener((java.awt.event.MouseMotionListener)this);
        com.cim.AIA.ConfigurationManager a18 = com.cim.AIA.ConfigurationManager.getConfigurationManager();
        a18.addColorSelectionListener((com.cim.AIA.ColorSelectionListener)this);
        a18.addFontSelectionListener((com.cim.AIA.FontSelectionListener)this);
        com.cim.AIA.AlgorithmLine a19 = new com.cim.AIA.AlgorithmLine("", "", "");
        com.cim.AIA.LadderTree a20 = new com.cim.AIA.LadderTree((com.cim.AIA.LadderTree)null, a19, a1, a2);
        this.ladderTree = a20;
    }
    
    public void addExplainationListener(com.cim.AIA.ExplainationListener a)
    {
        java.util.Vector a0 = this.explainationListeners;
        Object a1 = a;
        a0.addElement(a1);
    }
    
    public void addHelpListener(com.cim.AIA.HelpListener a)
    {
        java.util.Vector a0 = this.helpListeners;
        Object a1 = a;
        a0.addElement(a1);
    }
    
    public void cleanUp()
    {
        java.util.Vector a = this.explainationListeners;
        if(a != null)
        {
            java.util.Vector a0 = this.explainationListeners;
            a0.removeAllElements();
        }
        this.explainationListeners = null;
        java.util.Vector a1 = this.helpListeners;
        if(a1 != null)
        {
            java.util.Vector a2 = this.helpListeners;
            a2.removeAllElements();
        }
        this.helpListeners = null;
        this.ladderTree = null;
        java.awt.Image a3 = this.logoImage;
        if(a3 != null)
        {
            java.awt.Image a4 = this.logoImage;
            a4.flush();
        }
        this.logoImage = null;
        java.awt.Image a5 = this.expandedImage;
        if(a5 != null)
        {
            java.awt.Image a6 = this.expandedImage;
            a6.flush();
        }
        this.expandedImage = null;
        java.awt.Image a7 = this.collapsedImage;
        if(a7 != null)
        {
            java.awt.Image a8 = this.collapsedImage;
            a8.flush();
        }
        this.collapsedImage = null;
        this.normalFont = null;
        this.parent = null;
        this.breakpoint = null;
    }
    
    public void collapseEntireTree()
    {
        com.cim.AIA.LadderTree a = this.ladderTree;
        a.expandEntireLadderTree(false);
        this.repaint();
    }
    
    public boolean containsKey(String s)
    {
        com.cim.AIA.LadderTree a = this.ladderTree;
        int i = a.containsKey(s)?1:0;
        return i != 0;
    }
    
    public void display(java.awt.Graphics a)
    {
        com.cim.AIA.LadderTree a0 = this.ladderTree;
        a0.displayTree(a, this);
    }
    
    public void drawAlgorithmLine(java.awt.Graphics a, com.cim.AIA.AlgorithmLine a0, java.awt.Point a1, boolean b, boolean b0)
    {
        this.drawAlgorithmLine(a, a0, a1, b, b0, true);
    }
    
    public void drawAlgorithmLine(java.awt.Graphics a, com.cim.AIA.AlgorithmLine a0, java.awt.Point a1, boolean b, boolean b0, boolean b1)
    {
        int i = a0.getIsLabelMinMax()?1:0;
        int i0 = b?1:0;
        int i1 = b0?1:0;
        int i2 = b1?1:0;
        label1: {
            int i3 = 0;
            label0: {
                if(i == 0)
                {
                    break label0;
                }
                break label1;
            }
            java.awt.Font a2 = this.normalFont;
            a.setFont(a2);
            java.awt.FontMetrics a3 = a.getFontMetrics();
            int i4 = a3.getHeight();
            java.awt.Dimension a4 = this.expandedImageSize;
            int i5 = a4.height;
            int i6 = Math.max(i4, i5);
            label2: {
                if(i1 == 0)
                {
                    i3 = i0;
                    break label2;
                }
                if(i0 == 0)
                {
                    java.awt.Image a5 = this.collapsedImage;
                    int i7 = a1.x;
                    java.awt.Dimension a6 = this.expandedImageSize;
                    int i8 = a6.width;
                    int i9 = i7 - i8;
                    int i10 = i9 - 5;
                    int i11 = a1.y;
                    int i12 = a.drawImage(a5, i10, i11, (java.awt.image.ImageObserver)null)?1:0;
                    i3 = 0;
                }
                else
                {
                    java.awt.Image a7 = this.expandedImage;
                    int i13 = a1.x;
                    java.awt.Dimension a8 = this.expandedImageSize;
                    int i14 = a8.width;
                    int i15 = i13 - i14;
                    int i16 = i15 - 5;
                    int i17 = a1.y;
                    int i18 = a.drawImage(a7, i16, i17, (java.awt.image.ImageObserver)null)?1:0;
                    i3 = i0;
                }
            }
            String s = a0.getLabel();
            int i19 = a1.x;
            int i20 = i6 / 2;
            int i21 = i4 / 2;
            int i22 = i20 + i21;
            int i23 = a1.y;
            int i24 = i22 + i23;
            java.awt.Point a9 = new java.awt.Point(i19, i24);
            int i25 = a0.getIsMinMax()?1:0;
            label3: {
                if(i25 == 0)
                {
                    break label3;
                }
                int i26 = a9.x;
                java.awt.FontMetrics a10 = a.getFontMetrics();
                String s0 = a0.getMinMaxLabel();
                int i27 = a10.stringWidth(s0);
                int i28 = i27 + 5;
                int i29 = i26 + i28;
                a9.x = i29;
                int i30 = a9.x;
                int i31 = i30 + 14;
                a9.x = i31;
                int i32 = a0.getIsFirstMinMax()?1:0;
                if(i32 != 0)
                {
                    int i33 = a9.x;
                    int i34 = a1.y;
                    java.awt.Point a11 = new java.awt.Point(i33, i34);
                    a0.setLocation(a11);
                }
            }
            java.awt.Color a12 = this.backgroundColor;
            a.setColor(a12);
            int i35 = a0.isHighlighted()?1:0;
            label4: {
                if(i35 == 0)
                {
                    break label4;
                }
                int i36 = a0.getHighlightLevel();
                switch(i36){
                    case 3: {
                        java.awt.Color a13 = this.breakPointColor;
                        a.setColor(a13);
                        break;
                    }
                    case 2: {
                        java.awt.Color a14 = this.explainColor;
                        a.setColor(a14);
                        break;
                    }
                    case 1: {
                        java.awt.Color a15 = this.highlightColor;
                        a.setColor(a15);
                        break;
                    }
                }
            }
            label7: {
                label6: {
                    label5: {
                        if(i3 == 0)
                        {
                            break label5;
                        }
                        if(i1 != 0)
                        {
                            break label6;
                        }
                    }
                    int i37 = a9.x;
                    int i38 = a1.y;
                    java.awt.FontMetrics a16 = a.getFontMetrics();
                    int i39 = a16.stringWidth(s);
                    java.awt.FontMetrics a17 = a.getFontMetrics();
                    int i40 = a17.getHeight();
                    a.fillRect(i37, i38, i39, i40);
                    break label7;
                }
                int i41 = a9.x;
                int i42 = a1.y;
                java.awt.FontMetrics a18 = a.getFontMetrics();
                int i43 = a18.stringWidth(s);
                int i44 = this.getXBuffer();
                int i45 = i43 + i44;
                java.awt.FontMetrics a19 = a.getFontMetrics();
                int i46 = a19.getHeight();
                a.fillRect(i41, i42, i45, i46);
            }
            int i47 = a0.getIsMinMax()?1:0;
            label8: {
                if(i47 == 0)
                {
                    break label8;
                }
                java.awt.Color a20 = this.codeColor;
                a.setColor(a20);
                if(i2 != 0)
                {
                    int i48 = a9.x;
                    int i49 = i48 - 2;
                    int i50 = i49 - 5;
                    int i51 = a1.y;
                    java.awt.FontMetrics a21 = a.getFontMetrics();
                    int i52 = a21.getAscent();
                    int i53 = i51 + i52;
                    int i54 = a9.x;
                    int i55 = i54 - 2;
                    int i56 = a1.y;
                    java.awt.FontMetrics a22 = a.getFontMetrics();
                    int i57 = a22.getAscent();
                    int i58 = i56 + i57;
                    a.drawLine(i50, i53, i55, i58);
                }
                int i59 = a0.getIsLastMinMax()?1:0;
                if(i59 == 0)
                {
                    break label8;
                }
                com.cim.AIA.AlgorithmLine a23 = a0.getFirstInstance();
                java.awt.Point a24 = a23.getLocation();
                int i60 = a1.y;
                int i61 = a24.y;
                int i62 = i60 - i61;
                int i63 = i62 / 2;
                int i64 = a24.y;
                int i65 = i63 + i64;
                java.awt.FontMetrics a25 = a.getFontMetrics();
                int i66 = a25.getAscent();
                int i67 = i65 + i66;
                int i68 = a9.x;
                int i69 = i68 - 2;
                int i70 = i69 - 5;
                int i71 = a24.y;
                java.awt.FontMetrics a26 = a.getFontMetrics();
                int i72 = a26.getAscent();
                int i73 = i71 + i72;
                int i74 = a9.x;
                int i75 = i74 - 2;
                int i76 = i75 - 5;
                int i77 = a1.y;
                java.awt.FontMetrics a27 = a.getFontMetrics();
                int i78 = a27.getAscent();
                int i79 = i77 + i78;
                a.drawLine(i70, i73, i76, i79);
                int i80 = a9.x;
                int i81 = i80 - 2;
                int i82 = i81 - 5;
                int i83 = i82 - 5;
                int i84 = a9.x;
                int i85 = i84 - 2;
                int i86 = i85 - 5;
                a.drawLine(i83, i67, i86, i67);
                java.awt.Color a28 = this.backgroundColor;
                a.setColor(a28);
                com.cim.AIA.AlgorithmLine a29 = a0.getLabelInstance();
                int i87 = a29.isHighlighted()?1:0;
                label9: {
                    if(i87 == 0)
                    {
                        break label9;
                    }
                    com.cim.AIA.AlgorithmLine a30 = a0.getLabelInstance();
                    int i88 = a30.getHighlightLevel();
                    switch(i88){
                        case 2: {
                            java.awt.Color a31 = this.explainColor;
                            a.setColor(a31);
                            break;
                        }
                        case 1: {
                            java.awt.Color a32 = this.highlightColor;
                            a.setColor(a32);
                            break;
                        }
                    }
                }
                int i89 = a1.x;
                int i90 = a1.y;
                int i91 = a24.y;
                int i92 = i90 - i91;
                int i93 = i92 / 2;
                int i94 = a24.y;
                int i95 = i93 + i94;
                java.awt.FontMetrics a33 = a.getFontMetrics();
                String s1 = a0.getMinMaxLabel();
                int i96 = a33.stringWidth(s1);
                java.awt.FontMetrics a34 = a.getFontMetrics();
                int i97 = a34.getHeight();
                a.fillRect(i89, i95, i96, i97);
                java.awt.Color a35 = this.codeColor;
                a.setColor(a35);
                com.cim.AIA.AlgorithmLine a36 = a0.getLabelInstance();
                int i98 = a1.x;
                int i99 = a1.y;
                int i100 = a24.y;
                int i101 = i99 - i100;
                int i102 = i101 / 2;
                int i103 = a24.y;
                int i104 = i102 + i103;
                java.awt.Point a37 = new java.awt.Point(i98, i104);
                a36.setLocation(a37);
                String s2 = a0.getMinMaxLabel();
                int i105 = a1.x;
                java.awt.FontMetrics a38 = a.getFontMetrics();
                int i106 = a38.getAscent();
                int i107 = i106 / 2;
                int i108 = i67 + i107;
                a.drawString(s2, i105, i108);
            }
            label11: {
                label10: {
                    if(i3 == 0)
                    {
                        break label10;
                    }
                    if(i1 == 0)
                    {
                        break label10;
                    }
                    java.awt.Color a39 = this.commentColor;
                    a.setColor(a39);
                    int i109 = a9.x;
                    int i110 = a9.y;
                    a.drawString("//", i109, i110);
                    int i111 = a9.x;
                    int i112 = this.getXBuffer();
                    int i113 = i111 + i112;
                    int i114 = a9.y;
                    a.drawString(s, i113, i114);
                    break label11;
                }
                java.awt.Color a40 = this.codeColor;
                a.setColor(a40);
                int i115 = a0.isComment()?1:0;
                if(i115 != 0)
                {
                    java.awt.Color a41 = this.commentColor;
                    a.setColor(a41);
                }
                int i116 = a9.x;
                int i117 = a9.y;
                a.drawString(s, i116, i117);
            }
        }
    }
    
    public void expandEntireTree()
    {
        com.cim.AIA.LadderTree a = this.ladderTree;
        a.expandEntireLadderTree(true);
        this.repaint();
    }
    
    public java.awt.Color getBackgroundColor()
    {
        java.awt.Color a = this.backgroundColor;
        return a;
    }
    
    public String getClassName()
    {
        return "CodeCanvas";
    }
    
    public java.awt.Image getCollapsedImage()
    {
        java.awt.Image a = this.collapsedImage;
        return a;
    }
    
    public java.awt.Dimension getCollapsedImageSize()
    {
        java.awt.Dimension a = this.collapsedImageSize;
        return a;
    }
    
    public java.awt.Image getExpandedImage()
    {
        java.awt.Image a = this.expandedImage;
        return a;
    }
    
    public java.awt.Dimension getExpandedImageSize()
    {
        java.awt.Dimension a = this.expandedImageSize;
        return a;
    }
    
    public int getHeightForAnAlgorithmLine()
    {
        java.awt.Graphics a = this.getGraphics();
        java.awt.Font a0 = this.normalFont;
        a.setFont(a0);
        java.awt.FontMetrics a1 = a.getFontMetrics();
        int i = a1.getHeight();
        return i;
    }
    
    public int getImageBuffer()
    {
        return 5;
    }
    
    protected com.cim.AIA.LadderTree getLadderTree(String s)
    {
        com.cim.AIA.LadderTree a = this.ladderTree;
        com.cim.AIA.LadderTree a0 = a.getLadderTree(s);
        return a0;
    }
    
    public int getMinMaxLineLength()
    {
        return 5;
    }
    
    public int getMinMaxXGap()
    {
        return 2;
    }
    
    public String getPosition()
    {
        String s = this.currentPosition;
        return s;
    }
    
    public synchronized String getWhatHighlighted(String s)
    {
        com.cim.AIA.LadderTree a = this.ladderTree;
        String s0 = a.getWhatHighlighted(s);
        return s0;
    }
    
    public int getWidthOf(com.cim.AIA.AlgorithmLine a)
    {
        int i = 0;
        java.awt.Graphics a0 = this.getGraphics();
        if(a0 != null)
        {
            java.awt.Font a1 = this.normalFont;
            a0.setFont(a1);
            java.awt.FontMetrics a2 = a0.getFontMetrics();
            String s = a.getLabel();
            int i0 = a2.stringWidth(s);
            i = i0;
        }
        else
        {
            i = 0;
        }
        return i;
    }
    
    public int getWidthOf(String s)
    {
        int i = 0;
        java.awt.Graphics a = this.getGraphics();
        if(a != null)
        {
            java.awt.Font a0 = this.normalFont;
            a.setFont(a0);
            java.awt.FontMetrics a1 = a.getFontMetrics();
            int i0 = a1.stringWidth(s);
            i = i0;
        }
        else
        {
            i = 0;
        }
        return i;
    }
    
    public int getXBuffer()
    {
        java.awt.Graphics a = this.getGraphics();
        java.awt.FontMetrics a0 = a.getFontMetrics();
        int i = a0.stringWidth("/t");
        java.awt.Dimension a1 = this.expandedImageSize;
        int i0 = a1.width;
        java.awt.Dimension a2 = this.collapsedImageSize;
        int i1 = a2.width;
        int i2 = Math.max(i0, i1);
        int i3 = i + i2;
        return i3;
    }
    
    public int getYBuffer()
    {
        int i = this.getHeightForAnAlgorithmLine();
        int i0 = i + 2;
        return i0;
    }
    
    public void initialiseTree(String s, com.cim.AIA.Logger a, com.cim.AIA.BreakPoint a0)
    {
        String s0 = this.dataPath;
        this.initialiseTree(s0, s, a, a0);
    }
    
    public void initialiseTree(String s, String s0, com.cim.AIA.Logger a, com.cim.AIA.BreakPoint a0)
    {
        com.cim.AIA.LadderTree a1 = com.cim.AIA.CodeCanvas.getLadderTreeFromFile(s, s0, a, a0);
        if(a1 != null)
        {
            this.ladderTree = a1;
            com.cim.AIA.LadderTree a2 = this.ladderTree;
            String s1 = this.currentPosition;
            a2.setPosition(s1);
        }
    }
    
    public boolean isExpandable(String s)
    {
        com.cim.AIA.LadderTree a = this.ladderTree;
        int i = a.isExpandable(s)?1:0;
        return i != 0;
    }
    
    public boolean isExpanded(String s)
    {
        com.cim.AIA.LadderTree a = this.ladderTree;
        int i = a.isExpanded(s)?1:0;
        return i != 0;
    }
    
    public synchronized boolean isVisible(String s)
    {
        com.cim.AIA.LadderTree a = this.ladderTree;
        int i = a.isVisible(s)?1:0;
        return i != 0;
    }
    
    public void mouseClicked(java.awt.event.MouseEvent a)
    {
    }
    
    public void mouseDragged(java.awt.event.MouseEvent a)
    {
    }
    
    public void mouseEntered(java.awt.event.MouseEvent a)
    {
    }
    
    public void mouseExited(java.awt.event.MouseEvent a)
    {
    }
    
    public void mouseMoved(java.awt.event.MouseEvent a)
    {
        com.cim.AIA.LadderTree a0 = this.ladderTree;
        com.cim.AIA.LadderTree a1 = a0.processMouseEvent(a, this, false);
    }
    
    public void mousePressed(java.awt.event.MouseEvent a)
    {
        com.cim.AIA.LadderTree a0 = this.ladderTree;
        com.cim.AIA.LadderTree a1 = a0.processMouseEvent(a, this, true);
        label1: {
            label0: {
                if(a1 == null)
                {
                    break label0;
                }
                this.ladderTree = a1;
                this.repaint();
                break label1;
            }
            com.cim.AIA.BreakPoint a2 = this.breakpoint;
            if(a2 == null)
            {
                break label1;
            }
            com.cim.AIA.BreakPoint a3 = this.breakpoint;
            int i = a3.inMutex?1:0;
            if(i != 0)
            {
                com.cim.AIA.BreakPoint a4 = this.breakpoint;
                a4.processMutex((com.cim.AIA.AlgorithmLine)null);
            }
        }
    }
    
    public void mouseReleased(java.awt.event.MouseEvent a)
    {
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
                java.awt.Color a0 = a.getColor();
                this.backgroundColor = a0;
                break label1;
            }
            String s1 = com.cim.AIA.CodeCanvas.CODE_POSITION_MARKER;
            int i0 = s.equalsIgnoreCase(s1)?1:0;
            label2: {
                if(i0 == 0)
                {
                    break label2;
                }
                java.awt.Color a1 = a.getColor();
                this.highlightColor = a1;
                break label1;
            }
            String s2 = com.cim.AIA.CodeCanvas.EXPLANATION_POSITION_MARKER;
            int i1 = s.equalsIgnoreCase(s2)?1:0;
            label3: {
                if(i1 == 0)
                {
                    break label3;
                }
                java.awt.Color a2 = a.getColor();
                this.explainColor = a2;
                break label1;
            }
            String s3 = com.cim.AIA.CodeCanvas.COMMENT_LINE_COLOR;
            int i2 = s.equalsIgnoreCase(s3)?1:0;
            label4: {
                if(i2 == 0)
                {
                    break label4;
                }
                java.awt.Color a3 = a.getColor();
                this.commentColor = a3;
                break label1;
            }
            String s4 = com.cim.AIA.ColorSelection.TEXT_COLOR;
            int i3 = s.equalsIgnoreCase(s4)?1:0;
            label5: {
                if(i3 == 0)
                {
                    break label5;
                }
                java.awt.Color a4 = a.getColor();
                this.codeColor = a4;
                break label1;
            }
            String s5 = com.cim.AIA.CodeCanvas.BREAKPOINT_COLOR;
            int i4 = s.equalsIgnoreCase(s5)?1:0;
            if(i4 != 0)
            {
                java.awt.Color a5 = a.getColor();
                this.breakPointColor = a5;
            }
        }
    }
    
    public void processExplainationEvent(com.cim.AIA.ExplainationEvent a)
    {
        int i = 0;
        while(true)
        {
            java.util.Vector a0 = this.explainationListeners;
            int i0 = a0.size();
            if(i >= i0)
            {
                return;
            }
            else
            {
                java.util.Vector a1 = this.explainationListeners;
                Object a2 = a1.elementAt(i);
                com.cim.AIA.ExplainationListener dummy = (com.cim.AIA.ExplainationListener)a2;
                ((com.cim.AIA.ExplainationListener)a2).processExplainationEvent(a);
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    public void processFontSelection(com.cim.AIA.FontSelection a)
    {
        String s = a.getAtributeName();
        int i = s.equalsIgnoreCase("Normal Font")?1:0;
        if(i != 0)
        {
            java.awt.Font a0 = a.getFont();
            this.normalFont = a0;
        }
    }
    
    public void processHelpEvent(com.cim.AIA.HelpEvent a)
    {
        int i = 0;
        while(true)
        {
            java.util.Vector a0 = this.helpListeners;
            int i0 = a0.size();
            if(i >= i0)
            {
                return;
            }
            else
            {
                java.util.Vector a1 = this.helpListeners;
                Object a2 = a1.elementAt(i);
                com.cim.AIA.HelpListener dummy = (com.cim.AIA.HelpListener)a2;
                ((com.cim.AIA.HelpListener)a2).processHelpEvent(a);
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    public void removeExplainationListener(com.cim.AIA.ExplainationListener a)
    {
        java.util.Vector a0 = this.explainationListeners;
        Object a1 = a;
        int i = a0.removeElement(a1)?1:0;
    }
    
    public void removeHelpListener(com.cim.AIA.HelpListener a)
    {
        java.util.Vector a0 = this.helpListeners;
        Object a1 = a;
        int i = a0.removeElement(a1)?1:0;
    }
    
    public void setLadderTree(com.cim.AIA.LadderTree a)
    {
        this.ladderTree = a;
        com.cim.AIA.BreakPoint a0 = this.breakpoint;
        if(a0 != null)
        {
            com.cim.AIA.BreakPoint a1 = this.breakpoint;
            a1.reInit();
        }
        this.repaint();
    }
    
    public void setParent(java.awt.Container a)
    {
        this.parent = a;
    }
    
    public void setPosition(String s)
    {
        com.cim.AIA.LadderTree a = this.ladderTree;
        if(a != null)
        {
            com.cim.AIA.LadderTree a0 = this.ladderTree;
            a0.setPosition(s);
            this.currentPosition = s;
            this.repaint();
        }
        else
        {
            java.io.PrintStream a1 = System.out;
            a1.println("com.cim.AIA.CodeCanvas.setPosition: LadderTree is null. Unable to set position");
        }
    }
    
    public void setSize(int i, int i0)
    {
        java.awt.Container a = this.parent;
        if(a == null)
        {
            ((com.cim.common.BasicCanvas)this).setSize(i, i0);
        }
        else
        {
            java.awt.Container a0 = this.parent;
            java.awt.Dimension a1 = a0.getSize();
            int i1 = a1.width;
            int i2 = Math.max(i1, i);
            java.awt.Container a2 = this.parent;
            java.awt.Dimension a3 = a2.getSize();
            int i3 = a3.height;
            int i4 = Math.max(i3, i0);
            ((com.cim.common.BasicCanvas)this).setSize(i2, i4);
        }
    }
    
    protected boolean shouldRepaint(String s)
    {
        int i = 0;
        com.cim.AIA.LadderTree a = this.ladderTree;
        com.cim.AIA.LadderTree a0 = a.getLadderTree(s);
        label4: {
            label1: {
                label0: {
                    if(a0 == null)
                    {
                        break label0;
                    }
                    int i0 = a0.isVisible()?1:0;
                    if(i0 != 0)
                    {
                        break label1;
                    }
                    com.cim.AIA.Tree a1 = a0.getParent();
                    com.cim.AIA.LadderTree dummy = (com.cim.AIA.LadderTree)a1;
                    com.cim.AIA.LadderTree a2 = (com.cim.AIA.LadderTree)a1;
                    com.cim.AIA.LadderTree a3 = a2;
                    while(true)
                    {
                        if(a3 == null)
                        {
                            break;
                        }
                        int i1 = a3.isExpandable()?1:0;
                        label2: {
                            if(i1 == 0)
                            {
                                break label2;
                            }
                            int i2 = a3.isVisible()?1:0;
                            if(i2 == 0)
                            {
                                break label2;
                            }
                            int i3 = a3.isExpanded()?1:0;
                            if(i3 != 0)
                            {
                                break label2;
                            }
                            com.cim.AIA.AlgorithmLine a4 = a0.getAlgorithmLine();
                            com.cim.AIA.AlgorithmLine a5 = a3.getAlgorithmLine();
                            String s0 = a5.getKey();
                            int i4 = a4.isLastLine(s0)?1:0;
                            label3: {
                                if(i4 == 0)
                                {
                                    break label3;
                                }
                                i = 1;
                                break label4;
                            }
                            int i5 = 0;
                            while(true)
                            {
                                java.util.Vector a6 = a3.children;
                                int i6 = a6.size();
                                if(i5 >= i6)
                                {
                                    break label2;
                                }
                                java.util.Vector a7 = a3.children;
                                Object a8 = a7.elementAt(i5);
                                com.cim.AIA.LadderTree dummy0 = (com.cim.AIA.LadderTree)a8;
                                com.cim.AIA.LadderTree a9 = (com.cim.AIA.LadderTree)a8;
                                int i7 = a9.isVisible()?1:0;
                                label5: {
                                    if(i7 == 0)
                                    {
                                        break label5;
                                    }
                                    com.cim.AIA.AlgorithmLine a10 = a0.getAlgorithmLine();
                                    com.cim.AIA.AlgorithmLine a11 = a9.getAlgorithmLine();
                                    String s1 = a11.getKey();
                                    int i8 = a10.isLastLine(s1)?1:0;
                                    if(i8 != 0)
                                    {
                                        break;
                                    }
                                }
                                int i9 = i5 + 1;
                                i5 = i9;
                                continue;
                            }
                            i = 1;
                            break label4;
                        }
                        com.cim.AIA.Tree a12 = a3.getParent();
                        com.cim.AIA.LadderTree dummy1 = (com.cim.AIA.LadderTree)a12;
                        com.cim.AIA.LadderTree a13 = (com.cim.AIA.LadderTree)a12;
                        a3 = a13;
                    }
                }
                i = 0;
                break label4;
            }
            i = 0;
        }
        return i != 0;
    }
    
    static
    {
        com.cim.AIA.CodeCanvas.inMinMax = false;
        com.cim.AIA.CodeCanvas.isLabelMinMax = false;
        com.cim.AIA.CodeCanvas.isFirstMinMax = false;
        com.cim.AIA.CodeCanvas.isLastMinMax = false;
        com.cim.AIA.CodeCanvas.firstInstance = null;
        com.cim.AIA.CodeCanvas.labelInstance = null;
        com.cim.AIA.CodeCanvas.lastInstance = null;
        String s = localization.Messages.getString("CodeCanvas.2");
        com.cim.AIA.CodeCanvas.OPEN_FOLDER_HELP_TITLE = s;
        java.awt.Color a = new java.awt.Color(140, 204, 0);
        com.cim.AIA.CodeCanvas.DEFAULT_HIGHLIGHT_COLOR = a;
        StringBuilder a0 = new StringBuilder();
        String s0 = localization.Messages.getString("CodeCanvas.3");
        StringBuilder a1 = a0.append(s0);
        String s1 = localization.Messages.getString("CodeCanvas.4");
        StringBuilder a2 = a1.append(s1);
        String s2 = a2.toString();
        com.cim.AIA.CodeCanvas.OPEN_FOLDER_INSTRUCTIONS = s2;
        String s3 = localization.Messages.getString("CodeCanvas.5");
        com.cim.AIA.CodeCanvas.CLOSE_FOLDER_HELP_TITLE = s3;
        StringBuilder a3 = new StringBuilder();
        String s4 = localization.Messages.getString("CodeCanvas.6");
        StringBuilder a4 = a3.append(s4);
        String s5 = localization.Messages.getString("CodeCanvas.7");
        StringBuilder a5 = a4.append(s5);
        String s6 = a5.toString();
        com.cim.AIA.CodeCanvas.CLOSE_FOLDER_INSTRUCTIONS = s6;
        String s7 = localization.Messages.getString("CodeCanvas.8");
        com.cim.AIA.CodeCanvas.LINE_HELP_TITLE = s7;
        StringBuilder a6 = new StringBuilder();
        String s8 = localization.Messages.getString("CodeCanvas.9");
        StringBuilder a7 = a6.append(s8);
        String s9 = localization.Messages.getString("CodeCanvas.10");
        StringBuilder a8 = a7.append(s9);
        String s10 = a8.toString();
        com.cim.AIA.CodeCanvas.LINE_INSTRUCTIONS = s10;
        com.cim.AIA.CodeCanvas.JAR_FILE_NAME = "aia.jar";
        String s11 = localization.Messages.getString("CodeCanvas.21");
        com.cim.AIA.CodeCanvas.CODE_POSITION_MARKER = s11;
        String s12 = localization.Messages.getString("CodeCanvas.22");
        com.cim.AIA.CodeCanvas.EXPLANATION_POSITION_MARKER = s12;
        String s13 = localization.Messages.getString("CodeCanvas.23");
        com.cim.AIA.CodeCanvas.COMMENT_LINE_COLOR = s13;
        String s14 = localization.Messages.getString("CodeCanvas.24");
        com.cim.AIA.CodeCanvas.BREAKPOINT_COLOR = s14;
    }
}