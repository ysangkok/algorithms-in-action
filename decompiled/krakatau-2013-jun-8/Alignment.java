public class Alignment extends com.cim.AIA.Algorithm implements com.cim.AIA.ColorSelectionListener {
    final public static int EMPTY_MARKER = -10;
    final public static int SIM_DEFAULT_MATCHCOST = 1;
    final public static int SIM_DEFAULT_MISMATCHCOST = -4;
    final public static int SIM_DEFAULT_SPACECOST = -2;
    final public static int SIM_DEFAULT_INITIALGAPCOST = -2;
    final public static int SIM_DEFAULT_RUNNINGGAPCOST = -1;
    final public static int DIST_DEFAULT_MATCHCOST = 0;
    final public static int DIST_DEFAULT_MISMATCHCOST = 4;
    final public static int DIST_DEFAULT_SPACECOST = 2;
    final public static int DIST_DEFAULT_INITIALGAPCOST = 2;
    final public static int DIST_DEFAULT_RUNNINGGAPCOST = 1;
    final public static java.awt.Color SteelBlue1;
    final public static java.awt.Color SteelBlue2;
    final public static java.awt.Color SteelBlue3;
    final public static java.awt.Color SteelBlue4;
    final public static java.awt.Color DEFAULT_NODE_COLOR;
    final public static java.awt.Color DEFAULT_SELECTED_COLOR;
    final public static java.awt.Color DEFAULT_HIGHLIGHT_COLOR;
    final public static java.awt.Color DEFAULT_CURRENT_COLOR;
    final public static java.awt.Color HIGHLIGHT_RING_COLOR;
    final public static java.awt.Color DEFAULT_RING_COLOR;
    final public static java.awt.Color SELECT_RING_COLOR;
    final public static java.awt.Color IRRELEVANT_ARROW_COLOR;
    final public static java.awt.Color IRRELEVANT_COLOR;
    final public static int NOGAPS = 1;
    final public static int WITHGAPS = 2;
    final public static int GLOBALDIST = 1;
    final public static int GLOBALSIM = 2;
    final public static int LOCALSIM = 3;
    public static int currentVariation;
    public static int runningMode;
    final protected static String HIGHLIGHT_COLOR;
    final protected static String NODE_COLOR;
    protected static boolean isFirstAlignment;
    protected static int xRotate;
    protected static int yRotate;
    protected static int zRotate;
    protected String[] data;
    protected AlignmentNode tempNode;
    protected int[][] D;
    protected int[][] E;
    protected byte[][] EA;
    protected byte[][] EB;
    protected byte[][] EC;
    protected int[][] F;
    protected byte[][] FA;
    protected byte[][] FB;
    protected byte[][] FC;
    protected byte[][] GA;
    protected byte[][] GB;
    protected byte[][] GC;
    protected AlignmentMatrix theMatrix;
    protected AlignmentMatrix aMatrix;
    protected AlignmentMatrix bMatrix;
    protected AlignmentMatrix cMatrix;
    protected AlignmentMatrix3D the3D;
    protected AlignmentKey theAlignmentKey;
    protected com.cim.AIA.ElementArray string1EA;
    protected com.cim.AIA.ElementArray string2EA;
    protected AlignmentMinimum theMinimum;
    protected int xSize;
    protected int ySize;
    protected int matchCost;
    protected int mismatchCost;
    protected int spaceCost;
    protected int initialGapCost;
    protected int runningGapCost;
    protected java.awt.Color textColor;
    protected java.awt.Color highlightColor;
    protected java.awt.Color nodeColor;
    protected java.awt.Color background;
    protected boolean drawSymbolArrayAndLines;
    
    protected static void setRunningMode(String s)
    {
        label1: {
            label0: {
                if(s != null)
                {
                    break label0;
                }
                Alignment.runningMode = 1;
                break label1;
            }
            int i = s.equals((Object)"GLOBALDIST")?1:0;
            if(i != 0)
            {
                Alignment.runningMode = 1;
            }
            int i0 = s.equals((Object)"GLOBALSIM")?1:0;
            if(i0 != 0)
            {
                Alignment.runningMode = 2;
            }
            int i1 = s.equals((Object)"LOCALSIM")?1:0;
            if(i1 != 0)
            {
                Alignment.runningMode = 3;
            }
        }
    }
    
    public Alignment(com.cim.AIA.AlgorithmThread a, Object a0)
    {
        super(a, a0);
        AlignmentNode a1 = new AlignmentNode((Object)"X", 1);
        this.tempNode = a1;
        AlignmentMatrix3D a2 = new AlignmentMatrix3D();
        this.the3D = a2;
        this.theAlignmentKey = null;
        java.awt.Color a3 = java.awt.Color.black;
        this.textColor = a3;
        java.awt.Color a4 = Alignment.DEFAULT_HIGHLIGHT_COLOR;
        this.highlightColor = a4;
        java.awt.Color a5 = Alignment.DEFAULT_NODE_COLOR;
        this.nodeColor = a5;
        java.awt.Color a6 = java.awt.Color.white;
        this.background = a6;
        this.drawSymbolArrayAndLines = true;
        this.reInitialise(a0);
        Alignment.currentVariation = 1;
        com.cim.AIA.ConfigurationManager a7 = com.cim.AIA.ConfigurationManager.getConfigurationManager();
        java.awt.Color a8 = this.highlightColor;
        String s = Alignment.HIGHLIGHT_COLOR;
        com.cim.AIA.ColorSelection a9 = new com.cim.AIA.ColorSelection(a8, s);
        a9.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        a7.addColorSelection(a9);
        java.awt.Color a10 = this.nodeColor;
        String s0 = Alignment.NODE_COLOR;
        com.cim.AIA.ColorSelection a11 = new com.cim.AIA.ColorSelection(a10, s0);
        a11.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        a7.addColorSelection(a11);
        a7.addColorSelectionListener((com.cim.AIA.ColorSelectionListener)this);
        int i = Alignment.runningMode;
        if(i != 1)
        {
            this.setMatchCost(1);
            this.setMismatchCost(-4);
            this.setSpaceCost(-2);
            this.setInitialGapCost(-2);
            this.setRunningGapCost(-1);
        }
        else
        {
            this.setMatchCost(0);
            this.setMismatchCost(4);
            this.setSpaceCost(2);
            this.setInitialGapCost(2);
            this.setRunningGapCost(1);
        }
    }
    
    protected void changeData(Object a)
    {
        ((com.cim.AIA.Algorithm)this).changeData(a);
    }
    
    protected void clearState()
    {
        ((com.cim.AIA.Algorithm)this).clearState();
    }
    
    protected void gapFindPathInteractive(int i, int i0, int i1, java.util.Stack a)
    {
        int i2 = 0;
        AlignmentMatrix a0 = null;
        int i3 = 0;
        int i4 = 0;
        java.awt.Point a1 = new java.awt.Point(i, i0);
        Object a2 = a.push((Object)a1);
        Integer a3 = new Integer(i1);
        Object a4 = a.push((Object)a3);
        AlignmentMatrix a5 = this.aMatrix;
        a5.setXIndexHighlight(i);
        AlignmentMatrix a6 = this.aMatrix;
        a6.setYIndexHighlight(i0);
        AlignmentMatrix a7 = this.aMatrix;
        a7.setInternalBoxCoords(i, i0);
        AlignmentMatrix a8 = this.bMatrix;
        a8.setXIndexHighlight(i);
        AlignmentMatrix a9 = this.bMatrix;
        a9.setYIndexHighlight(i0);
        AlignmentMatrix a10 = this.bMatrix;
        a10.setInternalBoxCoords(i, i0);
        AlignmentMatrix a11 = this.cMatrix;
        a11.setXIndexHighlight(i);
        AlignmentMatrix a12 = this.cMatrix;
        a12.setYIndexHighlight(i0);
        AlignmentMatrix a13 = this.cMatrix;
        a13.setInternalBoxCoords(i, i0);
        label1: {
            label0: {
                if(i1 != 1)
                {
                    break label0;
                }
                int i5 = i - 1;
                int i6 = i0 - 1;
                AlignmentMatrix a14 = this.aMatrix;
                i2 = 1;
                a0 = a14;
                i3 = i5;
                i4 = i6;
                break label1;
            }
            if(i1 != 2)
            {
                int i7 = i - 1;
                AlignmentMatrix a15 = this.cMatrix;
                i2 = i1;
                a0 = a15;
                i3 = i7;
                i4 = i0;
            }
            else
            {
                int i8 = i0 - 1;
                AlignmentMatrix a16 = this.bMatrix;
                i2 = 2;
                a0 = a16;
                i3 = i;
                i4 = i8;
            }
        }
        a0.setIsSelected(true);
        AlignmentNode a17 = a0.get(i, i0);
        java.awt.Color a18 = Alignment.DEFAULT_CURRENT_COLOR;
        a17.setBackgroundColor(a18);
        java.awt.Color a19 = Alignment.HIGHLIGHT_RING_COLOR;
        a17.setRingColor(a19);
        AlignmentMatrix3D a20 = this.the3D;
        a20.initialiseColor();
        AlignmentMatrix3D a21 = this.the3D;
        a21.disable(2, 2);
        AlignmentMatrix3D a22 = this.the3D;
        int i9 = i2 - 1;
        a22.darkenX(i9);
        AlignmentMatrix3D a23 = this.the3D;
        int i10 = i2 - 1;
        java.awt.Color a24 = Alignment.DEFAULT_CURRENT_COLOR;
        a23.fillColor(2, 2, i10, a24);
        AlignmentMatrix3D a25 = this.the3D;
        int i11 = i2 - 1;
        a25.highlightX(2, 2, i11);
        this.setPosition("3.3");
        int i12 = a17.getTraceUp()?1:0;
        label3: {
            int i13 = 0;
            int i14 = 0;
            int i15 = 0;
            label2: {
                if(i12 != 0)
                {
                    break label2;
                }
                int i16 = a17.getTraceDiag()?1:0;
                if(i16 != 0)
                {
                    break label2;
                }
                int i17 = a17.getTraceLeft()?1:0;
                if(i17 != 0)
                {
                    break label2;
                }
                a17.highlight();
                java.awt.Color a26 = Alignment.DEFAULT_RING_COLOR;
                a17.setRingColor(a26);
                AlignmentMatrix a27 = this.aMatrix;
                a27.setXIndexHighlight(-1);
                AlignmentMatrix a28 = this.aMatrix;
                a28.setYIndexHighlight(-1);
                AlignmentMatrix a29 = this.bMatrix;
                a29.setXIndexHighlight(-1);
                AlignmentMatrix a30 = this.bMatrix;
                a30.setYIndexHighlight(-1);
                AlignmentMatrix a31 = this.cMatrix;
                a31.setXIndexHighlight(-1);
                AlignmentMatrix a32 = this.cMatrix;
                a32.setYIndexHighlight(-1);
                AlignmentMatrix a33 = this.aMatrix;
                a33.setDrawInternalBox(false);
                AlignmentMatrix a34 = this.bMatrix;
                a34.setDrawInternalBox(false);
                AlignmentMatrix a35 = this.cMatrix;
                a35.setDrawInternalBox(false);
                a0.setIsSelected(false);
                this.setPosition("3.4.0");
                Object a36 = a.pop();
                Object a37 = a.pop();
                break label3;
            }
            label5: {
                label4: {
                    if(i2 != 1)
                    {
                        break label4;
                    }
                    this.setPosition("3.3.1");
                    AlignmentMatrix a38 = this.aMatrix;
                    int i18 = i - 1;
                    a38.setXIndexHighlight(i18);
                    AlignmentMatrix a39 = this.bMatrix;
                    int i19 = i - 1;
                    a39.setXIndexHighlight(i19);
                    AlignmentMatrix a40 = this.cMatrix;
                    int i20 = i - 1;
                    a40.setXIndexHighlight(i20);
                    AlignmentMatrix a41 = this.aMatrix;
                    int i21 = i0 - 1;
                    a41.setYIndexHighlight(i21);
                    AlignmentMatrix a42 = this.bMatrix;
                    int i22 = i0 - 1;
                    a42.setYIndexHighlight(i22);
                    AlignmentMatrix a43 = this.cMatrix;
                    int i23 = i0 - 1;
                    a43.setYIndexHighlight(i23);
                    a17.highlightDiag();
                    AlignmentMatrix a44 = this.aMatrix;
                    int i24 = i - 1;
                    int i25 = i0 - 1;
                    AlignmentNode a45 = a44.get(i24, i25);
                    java.awt.Color a46 = Alignment.SELECT_RING_COLOR;
                    a45.setRingColor(a46);
                    AlignmentMatrix a47 = this.bMatrix;
                    int i26 = i - 1;
                    int i27 = i0 - 1;
                    AlignmentNode a48 = a47.get(i26, i27);
                    java.awt.Color a49 = Alignment.SELECT_RING_COLOR;
                    a48.setRingColor(a49);
                    AlignmentMatrix a50 = this.cMatrix;
                    int i28 = i - 1;
                    int i29 = i0 - 1;
                    AlignmentNode a51 = a50.get(i28, i29);
                    java.awt.Color a52 = Alignment.SELECT_RING_COLOR;
                    a51.setRingColor(a52);
                    AlignmentMatrix3D a53 = this.the3D;
                    a53.highlightX(1, 1);
                    this.setPosition("3.3.1.1");
                    i13 = 1;
                    i14 = 1;
                    i15 = 1;
                    break label5;
                }
                if(i2 != 2)
                {
                    this.setPosition("3.3.1");
                    this.setPosition("3.3.2");
                    this.setPosition("3.3.3");
                    AlignmentMatrix a54 = this.aMatrix;
                    int i30 = i - 1;
                    a54.setXIndexHighlight(i30);
                    AlignmentMatrix a55 = this.bMatrix;
                    int i31 = i - 1;
                    a55.setXIndexHighlight(i31);
                    AlignmentMatrix a56 = this.cMatrix;
                    int i32 = i - 1;
                    a56.setXIndexHighlight(i32);
                    a17.highlightLeft();
                    AlignmentMatrix a57 = this.aMatrix;
                    int i33 = i - 1;
                    AlignmentNode a58 = a57.get(i33, i0);
                    java.awt.Color a59 = Alignment.SELECT_RING_COLOR;
                    a58.setRingColor(a59);
                    AlignmentMatrix a60 = this.bMatrix;
                    int i34 = i - 1;
                    AlignmentNode a61 = a60.get(i34, i0);
                    java.awt.Color a62 = Alignment.SELECT_RING_COLOR;
                    a61.setRingColor(a62);
                    AlignmentMatrix a63 = this.cMatrix;
                    int i35 = i - 1;
                    AlignmentNode a64 = a63.get(i35, i0);
                    java.awt.Color a65 = Alignment.SELECT_RING_COLOR;
                    a64.setRingColor(a65);
                    AlignmentMatrix3D a66 = this.the3D;
                    a66.highlightX(1, 2);
                    this.setPosition("3.3.3.1");
                    i13 = i2;
                    i14 = 1;
                    i15 = 2;
                }
                else
                {
                    this.setPosition("3.3.1");
                    this.setPosition("3.3.2");
                    AlignmentMatrix a67 = this.aMatrix;
                    int i36 = i0 - 1;
                    a67.setYIndexHighlight(i36);
                    AlignmentMatrix a68 = this.bMatrix;
                    int i37 = i0 - 1;
                    a68.setYIndexHighlight(i37);
                    AlignmentMatrix a69 = this.cMatrix;
                    int i38 = i0 - 1;
                    a69.setYIndexHighlight(i38);
                    a17.highlightUp();
                    AlignmentMatrix a70 = this.aMatrix;
                    int i39 = i0 - 1;
                    AlignmentNode a71 = a70.get(i, i39);
                    java.awt.Color a72 = Alignment.SELECT_RING_COLOR;
                    a71.setRingColor(a72);
                    AlignmentMatrix a73 = this.bMatrix;
                    int i40 = i0 - 1;
                    AlignmentNode a74 = a73.get(i, i40);
                    java.awt.Color a75 = Alignment.SELECT_RING_COLOR;
                    a74.setRingColor(a75);
                    AlignmentMatrix a76 = this.cMatrix;
                    int i41 = i0 - 1;
                    AlignmentNode a77 = a76.get(i, i41);
                    java.awt.Color a78 = Alignment.SELECT_RING_COLOR;
                    a77.setRingColor(a78);
                    AlignmentMatrix3D a79 = this.the3D;
                    a79.highlightX(2, 1);
                    this.setPosition("3.3.2.1");
                    i13 = 2;
                    i14 = 2;
                    i15 = 1;
                }
            }
            int i42 = a17.getTraceA()?1:0;
            if(i42 == 0)
            {
                this.setPosition("3.3.4.1.1");
                this.setPosition("3.3.4.1.3");
                AlignmentMatrix a80 = this.aMatrix;
                AlignmentNode a81 = a80.get(i3, i4);
                a81.setIsDisabled(true);
                AlignmentMatrix a82 = this.aMatrix;
                AlignmentNode a83 = a82.get(i3, i4);
                java.awt.Color a84 = Alignment.DEFAULT_RING_COLOR;
                a83.setRingColor(a84);
                AlignmentMatrix3D a85 = this.the3D;
                a85.disable(i14, i15, 0);
                AlignmentMatrix3D a86 = this.the3D;
                a86.unHighlightX(i14, i15, 0);
                this.setPosition("3.3.4.1.4");
            }
            else
            {
                this.setPosition("3.3.4.1.1");
                a17.highlightA();
                AlignmentMatrix a87 = this.aMatrix;
                AlignmentNode a88 = a87.get(i3, i4);
                java.awt.Color a89 = Alignment.DEFAULT_NODE_COLOR;
                a88.setBackgroundColor(a89);
                AlignmentMatrix a90 = this.aMatrix;
                AlignmentNode a91 = a90.get(i3, i4);
                java.awt.Color a92 = Alignment.DEFAULT_RING_COLOR;
                a91.setRingColor(a92);
                AlignmentMatrix3D a93 = this.the3D;
                java.awt.Color a94 = Alignment.DEFAULT_NODE_COLOR;
                a93.fillColor(i14, i15, 0, a94);
                AlignmentMatrix3D a95 = this.the3D;
                a95.unHighlightX(i14, i15, 0);
                this.setPosition("3.3.4.1.2");
            }
            int i43 = a17.getTraceB()?1:0;
            if(i43 == 0)
            {
                this.setPosition("3.3.4.1.5");
                this.setPosition("3.3.4.1.7");
                AlignmentMatrix a96 = this.bMatrix;
                AlignmentNode a97 = a96.get(i3, i4);
                a97.setIsDisabled(true);
                AlignmentMatrix a98 = this.bMatrix;
                AlignmentNode a99 = a98.get(i3, i4);
                java.awt.Color a100 = Alignment.DEFAULT_RING_COLOR;
                a99.setRingColor(a100);
                AlignmentMatrix3D a101 = this.the3D;
                a101.disable(i14, i15, 1);
                AlignmentMatrix3D a102 = this.the3D;
                a102.unHighlightX(i14, i15, 1);
                this.setPosition("3.3.4.1.8");
            }
            else
            {
                this.setPosition("3.3.4.1.5");
                a17.highlightB();
                AlignmentMatrix a103 = this.bMatrix;
                AlignmentNode a104 = a103.get(i3, i4);
                java.awt.Color a105 = Alignment.DEFAULT_NODE_COLOR;
                a104.setBackgroundColor(a105);
                AlignmentMatrix a106 = this.bMatrix;
                AlignmentNode a107 = a106.get(i3, i4);
                java.awt.Color a108 = Alignment.DEFAULT_RING_COLOR;
                a107.setRingColor(a108);
                AlignmentMatrix3D a109 = this.the3D;
                java.awt.Color a110 = Alignment.DEFAULT_NODE_COLOR;
                a109.fillColor(i14, i15, 1, a110);
                AlignmentMatrix3D a111 = this.the3D;
                a111.unHighlightX(i14, i15, 1);
                this.setPosition("3.3.4.1.6");
            }
            int i44 = a17.getTraceC()?1:0;
            if(i44 == 0)
            {
                this.setPosition("3.3.4.1.9");
                this.setPosition("3.3.4.1.11");
                AlignmentMatrix a112 = this.cMatrix;
                AlignmentNode a113 = a112.get(i3, i4);
                a113.setIsDisabled(true);
                AlignmentMatrix a114 = this.cMatrix;
                AlignmentNode a115 = a114.get(i3, i4);
                java.awt.Color a116 = Alignment.DEFAULT_RING_COLOR;
                a115.setRingColor(a116);
                AlignmentMatrix3D a117 = this.the3D;
                a117.disable(i14, i15, 2);
                AlignmentMatrix3D a118 = this.the3D;
                a118.unHighlightX(i14, i15, 2);
                this.setPosition("3.3.4.1.12");
            }
            else
            {
                this.setPosition("3.3.4.1.9");
                a17.highlightC();
                AlignmentMatrix a119 = this.cMatrix;
                AlignmentNode a120 = a119.get(i3, i4);
                java.awt.Color a121 = Alignment.DEFAULT_NODE_COLOR;
                a120.setBackgroundColor(a121);
                AlignmentMatrix a122 = this.cMatrix;
                AlignmentNode a123 = a122.get(i3, i4);
                java.awt.Color a124 = Alignment.DEFAULT_RING_COLOR;
                a123.setRingColor(a124);
                AlignmentMatrix3D a125 = this.the3D;
                java.awt.Color a126 = Alignment.DEFAULT_NODE_COLOR;
                a125.fillColor(i14, i15, 2, a126);
                AlignmentMatrix3D a127 = this.the3D;
                a127.unHighlightX(i14, i15, 2);
                this.setPosition("3.3.4.1.10");
            }
            a17.unHighlightA();
            a17.unHighlightB();
            a17.unHighlightC();
            int i45 = a17.getTraceA()?1:0;
            label7: {
                label6: {
                    if(i45 == 0)
                    {
                        break label6;
                    }
                    a17.highlightA();
                    AlignmentMatrix a128 = this.aMatrix;
                    AlignmentNode a129 = a128.get(i3, i4);
                    java.awt.Color a130 = Alignment.DEFAULT_SELECTED_COLOR;
                    a129.setBackgroundColor(a130);
                    AlignmentMatrix a131 = this.cMatrix;
                    AlignmentNode a132 = a131.get(i3, i4);
                    a132.setIsDisabled(true);
                    AlignmentMatrix a133 = this.bMatrix;
                    AlignmentNode a134 = a133.get(i3, i4);
                    a134.setIsDisabled(true);
                    AlignmentMatrix3D a135 = this.the3D;
                    java.awt.Color a136 = Alignment.DEFAULT_SELECTED_COLOR;
                    a135.fillColor(i14, i15, 0, a136);
                    this.setPosition("3.3.4.2");
                    this.setPosition("3.3.4.3");
                    java.awt.Color a137 = Alignment.DEFAULT_RING_COLOR;
                    a17.setRingColor(a137);
                    AlignmentMatrix a138 = this.aMatrix;
                    AlignmentNode a139 = a138.get(i3, i4);
                    java.awt.Color a140 = Alignment.DEFAULT_CURRENT_COLOR;
                    a139.setBackgroundColor(a140);
                    AlignmentMatrix a141 = this.aMatrix;
                    AlignmentNode a142 = a141.get(i3, i4);
                    java.awt.Color a143 = Alignment.HIGHLIGHT_RING_COLOR;
                    a142.setRingColor(a143);
                    AlignmentMatrix a144 = this.aMatrix;
                    a144.setIsSelected(true);
                    a17.highlight();
                    a0.setIsSelected(false);
                    AlignmentMatrix a145 = this.aMatrix;
                    a145.setIsSelected(true);
                    AlignmentMatrix3D a146 = this.the3D;
                    a146.disable(i14, i15);
                    AlignmentMatrix3D a147 = this.the3D;
                    java.awt.Color a148 = Alignment.DEFAULT_CURRENT_COLOR;
                    a147.fillColor(i14, i15, 0, a148);
                    AlignmentMatrix3D a149 = this.the3D;
                    int i46 = i13 - 1;
                    a149.unDarkenX(i46);
                    AlignmentMatrix3D a150 = this.the3D;
                    a150.darkenX(0);
                    AlignmentMatrix3D a151 = this.the3D;
                    a151.highlightX(i14, i15, 0);
                    AlignmentMatrix3D a152 = this.the3D;
                    int i47 = i13 - 1;
                    java.awt.Color a153 = this.highlightColor;
                    a152.fillColor(2, 2, i47, a153);
                    AlignmentMatrix3D a154 = this.the3D;
                    int i48 = i13 - 1;
                    a154.unHighlightX(2, 2, i48);
                    this.setPosition("3.3.4.4");
                    this.gapFindPathInteractive(i3, i4, 1, a);
                    break label7;
                }
                int i49 = a17.getTraceB()?1:0;
                label8: {
                    if(i49 == 0)
                    {
                        break label8;
                    }
                    a17.highlightB();
                    AlignmentMatrix a155 = this.bMatrix;
                    AlignmentNode a156 = a155.get(i3, i4);
                    java.awt.Color a157 = Alignment.DEFAULT_SELECTED_COLOR;
                    a156.setBackgroundColor(a157);
                    AlignmentMatrix a158 = this.cMatrix;
                    AlignmentNode a159 = a158.get(i3, i4);
                    a159.setIsDisabled(true);
                    AlignmentMatrix a160 = this.aMatrix;
                    AlignmentNode a161 = a160.get(i3, i4);
                    a161.setIsDisabled(true);
                    AlignmentMatrix3D a162 = this.the3D;
                    java.awt.Color a163 = Alignment.DEFAULT_SELECTED_COLOR;
                    a162.fillColor(i14, i15, 1, a163);
                    this.setPosition("3.3.4.2");
                    this.setPosition("3.3.4.3");
                    this.setPosition("3.3.4.5");
                    java.awt.Color a164 = Alignment.DEFAULT_RING_COLOR;
                    a17.setRingColor(a164);
                    AlignmentMatrix a165 = this.bMatrix;
                    AlignmentNode a166 = a165.get(i3, i4);
                    java.awt.Color a167 = Alignment.DEFAULT_CURRENT_COLOR;
                    a166.setBackgroundColor(a167);
                    AlignmentMatrix a168 = this.bMatrix;
                    AlignmentNode a169 = a168.get(i3, i4);
                    java.awt.Color a170 = Alignment.HIGHLIGHT_RING_COLOR;
                    a169.setRingColor(a170);
                    AlignmentMatrix a171 = this.bMatrix;
                    a171.setIsSelected(true);
                    a17.highlight();
                    a0.setIsSelected(false);
                    AlignmentMatrix a172 = this.bMatrix;
                    a172.setIsSelected(true);
                    AlignmentMatrix3D a173 = this.the3D;
                    a173.disable(i14, i15);
                    AlignmentMatrix3D a174 = this.the3D;
                    java.awt.Color a175 = Alignment.DEFAULT_CURRENT_COLOR;
                    a174.fillColor(i14, i15, 1, a175);
                    AlignmentMatrix3D a176 = this.the3D;
                    int i50 = i13 - 1;
                    a176.unDarkenX(i50);
                    AlignmentMatrix3D a177 = this.the3D;
                    a177.darkenX(1);
                    AlignmentMatrix3D a178 = this.the3D;
                    a178.highlightX(i14, i15, 1);
                    AlignmentMatrix3D a179 = this.the3D;
                    int i51 = i13 - 1;
                    java.awt.Color a180 = this.highlightColor;
                    a179.fillColor(2, 2, i51, a180);
                    AlignmentMatrix3D a181 = this.the3D;
                    int i52 = i13 - 1;
                    a181.unHighlightX(2, 2, i52);
                    this.setPosition("3.3.4.6");
                    this.gapFindPathInteractive(i3, i4, 2, a);
                    break label7;
                }
                int i53 = a17.getTraceC()?1:0;
                if(i53 != 0)
                {
                    a17.highlightC();
                    AlignmentMatrix a182 = this.cMatrix;
                    AlignmentNode a183 = a182.get(i3, i4);
                    java.awt.Color a184 = Alignment.DEFAULT_SELECTED_COLOR;
                    a183.setBackgroundColor(a184);
                    AlignmentMatrix a185 = this.aMatrix;
                    AlignmentNode a186 = a185.get(i3, i4);
                    a186.setIsDisabled(true);
                    AlignmentMatrix a187 = this.bMatrix;
                    AlignmentNode a188 = a187.get(i3, i4);
                    a188.setIsDisabled(true);
                    AlignmentMatrix3D a189 = this.the3D;
                    java.awt.Color a190 = Alignment.DEFAULT_SELECTED_COLOR;
                    a189.fillColor(i14, i15, 2, a190);
                    this.setPosition("3.3.4.2");
                    this.setPosition("3.3.4.3");
                    this.setPosition("3.3.4.5");
                    this.setPosition("3.3.4.7");
                    java.awt.Color a191 = Alignment.DEFAULT_RING_COLOR;
                    a17.setRingColor(a191);
                    AlignmentMatrix a192 = this.cMatrix;
                    AlignmentNode a193 = a192.get(i3, i4);
                    java.awt.Color a194 = Alignment.DEFAULT_CURRENT_COLOR;
                    a193.setBackgroundColor(a194);
                    AlignmentMatrix a195 = this.cMatrix;
                    AlignmentNode a196 = a195.get(i3, i4);
                    java.awt.Color a197 = Alignment.HIGHLIGHT_RING_COLOR;
                    a196.setRingColor(a197);
                    AlignmentMatrix a198 = this.cMatrix;
                    a198.setIsSelected(true);
                    a17.highlight();
                    a0.setIsSelected(false);
                    AlignmentMatrix a199 = this.cMatrix;
                    a199.setIsSelected(true);
                    AlignmentMatrix3D a200 = this.the3D;
                    a200.disable(i14, i15);
                    AlignmentMatrix3D a201 = this.the3D;
                    java.awt.Color a202 = Alignment.DEFAULT_CURRENT_COLOR;
                    a201.fillColor(i14, i15, 2, a202);
                    AlignmentMatrix3D a203 = this.the3D;
                    int i54 = i13 - 1;
                    a203.unDarkenX(i54);
                    AlignmentMatrix3D a204 = this.the3D;
                    a204.darkenX(2);
                    AlignmentMatrix3D a205 = this.the3D;
                    a205.highlightX(i14, i15, 2);
                    AlignmentMatrix3D a206 = this.the3D;
                    int i55 = i13 - 1;
                    java.awt.Color a207 = this.highlightColor;
                    a206.fillColor(2, 2, i55, a207);
                    AlignmentMatrix3D a208 = this.the3D;
                    int i56 = i13 - 1;
                    a208.unHighlightX(2, 2, i56);
                    this.setPosition("3.3.4.8");
                    this.gapFindPathInteractive(i3, i4, 3, a);
                }
            }
            Object a209 = a.pop();
            Object a210 = a.pop();
            AlignmentMatrix a211 = this.aMatrix;
            a211.unHighlight();
            AlignmentMatrix a212 = this.aMatrix;
            a212.unHighlightTrace();
            AlignmentMatrix a213 = this.aMatrix;
            java.awt.Color a214 = Alignment.DEFAULT_NODE_COLOR;
            java.awt.Color a215 = java.awt.Color.black;
            a213.setAllColor(a214, a215);
            AlignmentMatrix a216 = this.bMatrix;
            a216.unHighlight();
            AlignmentMatrix a217 = this.bMatrix;
            a217.unHighlightTrace();
            AlignmentMatrix a218 = this.bMatrix;
            java.awt.Color a219 = Alignment.DEFAULT_NODE_COLOR;
            java.awt.Color a220 = java.awt.Color.black;
            a218.setAllColor(a219, a220);
            AlignmentMatrix a221 = this.cMatrix;
            a221.unHighlight();
            AlignmentMatrix a222 = this.cMatrix;
            a222.unHighlightTrace();
            AlignmentMatrix a223 = this.cMatrix;
            java.awt.Color a224 = Alignment.DEFAULT_NODE_COLOR;
            java.awt.Color a225 = java.awt.Color.black;
            a223.setAllColor(a224, a225);
        }
    }
    
    protected void gapFindPathNonInteractive(int i, int i0, int i1, java.util.Stack a)
    {
        int i2 = 0;
        AlignmentMatrix a0 = null;
        int i3 = 0;
        int i4 = 0;
        AlignmentMatrix a1 = this.aMatrix;
        a1.unHighlight();
        AlignmentMatrix a2 = this.aMatrix;
        a2.unHighlightTrace();
        AlignmentMatrix a3 = this.aMatrix;
        java.awt.Color a4 = Alignment.DEFAULT_NODE_COLOR;
        java.awt.Color a5 = java.awt.Color.black;
        a3.setAllColor(a4, a5);
        AlignmentMatrix a6 = this.bMatrix;
        a6.unHighlight();
        AlignmentMatrix a7 = this.bMatrix;
        a7.unHighlightTrace();
        AlignmentMatrix a8 = this.bMatrix;
        java.awt.Color a9 = Alignment.DEFAULT_NODE_COLOR;
        java.awt.Color a10 = java.awt.Color.black;
        a8.setAllColor(a9, a10);
        AlignmentMatrix a11 = this.cMatrix;
        a11.unHighlight();
        AlignmentMatrix a12 = this.cMatrix;
        a12.unHighlightTrace();
        AlignmentMatrix a13 = this.cMatrix;
        java.awt.Color a14 = Alignment.DEFAULT_NODE_COLOR;
        java.awt.Color a15 = java.awt.Color.black;
        a13.setAllColor(a14, a15);
        java.awt.Point a16 = new java.awt.Point(i, i0);
        Object a17 = a.push((Object)a16);
        Integer a18 = new Integer(i1);
        Object a19 = a.push((Object)a18);
        label1: {
            label0: {
                if(i1 != 1)
                {
                    break label0;
                }
                int i5 = i - 1;
                int i6 = i0 - 1;
                AlignmentMatrix a20 = this.aMatrix;
                i2 = 1;
                a0 = a20;
                i3 = i5;
                i4 = i6;
                break label1;
            }
            if(i1 != 2)
            {
                int i7 = i - 1;
                AlignmentMatrix a21 = this.cMatrix;
                i2 = i1;
                a0 = a21;
                i3 = i7;
                i4 = i0;
            }
            else
            {
                int i8 = i0 - 1;
                AlignmentMatrix a22 = this.bMatrix;
                i2 = 2;
                a0 = a22;
                i3 = i;
                i4 = i8;
            }
        }
        AlignmentNode a23 = a0.get(i, i0);
        a23.highlight();
        int i9 = a23.getTraceUp()?1:0;
        label2: {
            if(i9 != 0)
            {
                break label2;
            }
            int i10 = a23.getTraceDiag()?1:0;
            if(i10 != 0)
            {
                break label2;
            }
            int i11 = a23.getTraceLeft()?1:0;
            if(i11 != 0)
            {
                break label2;
            }
            com.cim.AIA.ElementArray a24 = new com.cim.AIA.ElementArray(0, 0);
            this.string1EA = a24;
            com.cim.AIA.ElementArray a25 = new com.cim.AIA.ElementArray(0, 0);
            this.string2EA = a25;
            com.cim.AIA.ElementArray a26 = this.string1EA;
            a26.setColumGap(0);
            com.cim.AIA.ElementArray a27 = this.string2EA;
            a27.setColumGap(0);
            com.cim.AIA.ElementArray a28 = this.string1EA;
            a28.setElementWidth(20);
            com.cim.AIA.ElementArray a29 = this.string2EA;
            a29.setElementWidth(20);
            AlignmentMatrix a30 = this.aMatrix;
            a30.unHighlight();
            AlignmentMatrix a31 = this.aMatrix;
            a31.unHighlightTrace();
            AlignmentMatrix a32 = this.aMatrix;
            java.awt.Color a33 = Alignment.IRRELEVANT_COLOR;
            java.awt.Color a34 = Alignment.IRRELEVANT_ARROW_COLOR;
            a32.setAllColor(a33, a34);
            AlignmentMatrix a35 = this.bMatrix;
            a35.unHighlight();
            AlignmentMatrix a36 = this.bMatrix;
            a36.unHighlightTrace();
            AlignmentMatrix a37 = this.bMatrix;
            java.awt.Color a38 = Alignment.IRRELEVANT_COLOR;
            java.awt.Color a39 = Alignment.IRRELEVANT_ARROW_COLOR;
            a37.setAllColor(a38, a39);
            AlignmentMatrix a40 = this.cMatrix;
            a40.unHighlight();
            AlignmentMatrix a41 = this.cMatrix;
            a41.unHighlightTrace();
            AlignmentMatrix a42 = this.cMatrix;
            java.awt.Color a43 = Alignment.IRRELEVANT_COLOR;
            java.awt.Color a44 = Alignment.IRRELEVANT_ARROW_COLOR;
            a42.setAllColor(a43, a44);
            Object a45 = a.pop();
            Integer a46 = new Integer(0);
            Object a47 = a.push((Object)a46);
            Object a48 = a.clone();
            java.util.Stack dummy = (java.util.Stack)a48;
            java.util.Stack a49 = (java.util.Stack)a48;
            AlignmentMatrix a50 = a0;
            int i12 = i2;
            int i13 = 0;
            int i14 = 0;
            while(true)
            {
                AlignmentMatrix a51 = null;
                int i15 = 0;
                int i16 = 0;
                AlignmentMatrix a52 = null;
                int i17 = 0;
                int i18 = 0;
                AlignmentMatrix a53 = null;
                int i19 = 0;
                int i20 = 0;
                int i21 = 0;
                AlignmentNode a54 = null;
                AlignmentMatrix a55 = null;
                int i22 = 0;
                int i23 = 0;
                int i24 = 0;
                AlignmentNode a56 = null;
                AlignmentMatrix a57 = null;
                int i25 = 0;
                int i26 = 0;
                int i27 = 0;
                AlignmentNode a58 = null;
                AlignmentMatrix a59 = null;
                int i28 = 0;
                int i29 = 0;
                int i30 = 0;
                AlignmentMatrix a60 = null;
                int i31 = 0;
                int i32 = 0;
                int i33 = 0;
                AlignmentMatrix a61 = null;
                int i34 = 0;
                int i35 = 0;
                int i36 = 0;
                AlignmentMatrix a62 = null;
                int i37 = 0;
                int i38 = 0;
                int i39 = 0;
                int i40 = a49.empty()?1:0;
                AlignmentMatrix a63 = a50;
                int i41 = i12;
                int i42 = i14;
                AlignmentMatrix a64 = a63;
                int i43 = i41;
                int i44 = i42;
                if(i40 != 0)
                {
                    break;
                }
                else
                {
                    a51 = a64;
                    i15 = i43;
                    i16 = i44;
                }
                AlignmentMatrix a65 = a51;
                int i45 = i16;
                AlignmentMatrix a66 = a51;
                int i46 = i15;
                int i47 = i16;
                if(i13 == 0)
                {
                    a52 = a66;
                    i17 = i46;
                    i18 = i47;
                }
                else
                {
                    AlignmentMatrix a67 = a65;
                    int i48 = i45;
                    a52 = a67;
                    i17 = i13;
                    i18 = i48;
                }
                Object a68 = a49.pop();
                AlignmentMatrix a69 = a52;
                int i49 = i17;
                int i50 = i18;
                Integer dummy0 = (Integer)a68;
                Integer a70 = (Integer)a68;
                AlignmentMatrix a71 = a69;
                int i51 = i49;
                int i52 = i50;
                int i53 = a70.intValue();
                AlignmentMatrix a72 = a71;
                int i54 = i51;
                int i55 = i52;
                Object a73 = a49.pop();
                AlignmentMatrix a74 = a72;
                int i56 = i54;
                int i57 = i55;
                java.awt.Point dummy1 = (java.awt.Point)a73;
                java.awt.Point a75 = (java.awt.Point)a73;
                AlignmentMatrix a76 = a74;
                int i58 = i56;
                int i59 = i57;
                int i60 = i58;
                int i61 = i59;
                AlignmentMatrix a77 = a76;
                int i62 = i58;
                int i63 = i59;
                AlignmentNode a78 = null;
                if(i53 != 1)
                {
                    a53 = a77;
                    i19 = i62;
                    i20 = i53;
                    i21 = i63;
                    a54 = a78;
                }
                else
                {
                    int i64 = i60;
                    int i65 = i61;
                    AlignmentMatrix a79 = this.aMatrix;
                    int i66 = i64;
                    int i67 = i65;
                    AlignmentMatrix a80 = this.aMatrix;
                    int i68 = i66;
                    int i69 = i67;
                    int i70 = a75.x;
                    int i71 = i68;
                    int i72 = i69;
                    int i73 = a75.y;
                    int i74 = i71;
                    int i75 = i72;
                    AlignmentNode a81 = a80.get(i70, i73);
                    int i76 = i74;
                    int i77 = i75;
                    a81.highlight();
                    a53 = a79;
                    i19 = i76;
                    i20 = 1;
                    i21 = i77;
                    a54 = a81;
                }
                int i78 = i19;
                int i79 = i21;
                AlignmentMatrix a82 = a53;
                int i80 = i19;
                int i81 = i21;
                if(i20 != 2)
                {
                    a55 = a82;
                    i22 = i80;
                    i23 = i20;
                    i24 = i81;
                    a56 = a54;
                }
                else
                {
                    int i82 = i78;
                    int i83 = i79;
                    AlignmentMatrix a83 = this.bMatrix;
                    int i84 = i82;
                    int i85 = i83;
                    AlignmentMatrix a84 = this.bMatrix;
                    int i86 = i84;
                    int i87 = i85;
                    int i88 = a75.x;
                    int i89 = i86;
                    int i90 = i87;
                    int i91 = a75.y;
                    int i92 = i89;
                    int i93 = i90;
                    AlignmentNode a85 = a84.get(i88, i91);
                    int i94 = i92;
                    int i95 = i93;
                    a85.highlight();
                    a55 = a83;
                    i22 = i94;
                    i23 = 2;
                    i24 = i95;
                    a56 = a85;
                }
                int i96 = i22;
                int i97 = i24;
                AlignmentMatrix a86 = a55;
                int i98 = i22;
                int i99 = i24;
                if(i23 != 3)
                {
                    a57 = a86;
                    i25 = i98;
                    i26 = i23;
                    i27 = i99;
                    a58 = a56;
                }
                else
                {
                    int i100 = i96;
                    int i101 = i97;
                    AlignmentMatrix a87 = this.cMatrix;
                    int i102 = i100;
                    int i103 = i101;
                    AlignmentMatrix a88 = this.cMatrix;
                    int i104 = i102;
                    int i105 = i103;
                    int i106 = a75.x;
                    int i107 = i104;
                    int i108 = i105;
                    int i109 = a75.y;
                    int i110 = i107;
                    int i111 = i108;
                    AlignmentNode a89 = a88.get(i106, i109);
                    int i112 = i110;
                    int i113 = i111;
                    a89.highlight();
                    a57 = a87;
                    i25 = i112;
                    i26 = 3;
                    i27 = i113;
                    a58 = a89;
                }
                label3: {
                    AlignmentMatrix a90 = null;
                    int i114 = 0;
                    int i115 = 0;
                    AlignmentMatrix a91 = null;
                    int i116 = 0;
                    int i117 = 0;
                    AlignmentMatrix a92 = null;
                    int i118 = 0;
                    int i119 = 0;
                    AlignmentMatrix a93 = a57;
                    int i120 = i25;
                    int i121 = i27;
                    AlignmentMatrix a94 = a57;
                    int i122 = i25;
                    int i123 = i27;
                    if(i26 != 0)
                    {
                        a59 = a94;
                        i28 = i122;
                        i29 = i26;
                        i30 = i123;
                        break label3;
                    }
                    else
                    {
                        a90 = a93;
                        i114 = i120;
                        i115 = i121;
                    }
                    AlignmentMatrix a95 = a90;
                    int i124 = i115;
                    AlignmentMatrix a96 = a90;
                    int i125 = i114;
                    int i126 = i115;
                    if(i114 != 1)
                    {
                        a91 = a96;
                        i116 = i125;
                        i117 = i126;
                    }
                    else
                    {
                        AlignmentMatrix a97 = a95;
                        int i127 = i124;
                        AlignmentMatrix a98 = this.aMatrix;
                        AlignmentMatrix a99 = a97;
                        int i128 = i127;
                        int i129 = a75.x;
                        AlignmentMatrix a100 = a99;
                        int i130 = i128;
                        int i131 = a75.y;
                        AlignmentMatrix a101 = a100;
                        int i132 = i130;
                        AlignmentNode a102 = a98.get(i129, i131);
                        AlignmentMatrix a103 = a101;
                        int i133 = i132;
                        a102.highlight();
                        a91 = a103;
                        i116 = 1;
                        i117 = i133;
                    }
                    AlignmentMatrix a104 = a91;
                    int i134 = i117;
                    AlignmentMatrix a105 = a91;
                    int i135 = i116;
                    int i136 = i117;
                    if(i116 != 2)
                    {
                        a92 = a105;
                        i118 = i135;
                        i119 = i136;
                    }
                    else
                    {
                        AlignmentMatrix a106 = a104;
                        int i137 = i134;
                        AlignmentMatrix a107 = this.bMatrix;
                        AlignmentMatrix a108 = a106;
                        int i138 = i137;
                        int i139 = a75.x;
                        AlignmentMatrix a109 = a108;
                        int i140 = i138;
                        int i141 = a75.y;
                        AlignmentMatrix a110 = a109;
                        int i142 = i140;
                        AlignmentNode a111 = a107.get(i139, i141);
                        AlignmentMatrix a112 = a110;
                        int i143 = i142;
                        a111.highlight();
                        a92 = a112;
                        i118 = 2;
                        i119 = i143;
                    }
                    AlignmentMatrix a113 = a92;
                    int i144 = i119;
                    AlignmentMatrix a114 = a92;
                    int i145 = i118;
                    int i146 = i119;
                    if(i118 != 3)
                    {
                        a59 = a114;
                        i28 = i145;
                        i29 = 0;
                        i30 = i146;
                    }
                    else
                    {
                        AlignmentMatrix a115 = a113;
                        int i147 = i144;
                        AlignmentMatrix a116 = this.cMatrix;
                        AlignmentMatrix a117 = a115;
                        int i148 = i147;
                        int i149 = a75.x;
                        AlignmentMatrix a118 = a117;
                        int i150 = i148;
                        int i151 = a75.y;
                        AlignmentMatrix a119 = a118;
                        int i152 = i150;
                        AlignmentNode a120 = a116.get(i149, i151);
                        AlignmentMatrix a121 = a119;
                        int i153 = i152;
                        a120.highlight();
                        a59 = a121;
                        i28 = 3;
                        i29 = 0;
                        i30 = i153;
                    }
                }
                label4: {
                    AlignmentMatrix a122 = null;
                    int i154 = 0;
                    AlignmentMatrix a123 = null;
                    int i155 = 0;
                    AlignmentMatrix a124 = null;
                    int i156 = 0;
                    AlignmentMatrix a125 = null;
                    int i157 = 0;
                    AlignmentMatrix a126 = null;
                    int i158 = 0;
                    AlignmentMatrix a127 = a59;
                    int i159 = i28;
                    AlignmentMatrix a128 = a59;
                    int i160 = i28;
                    int i161 = i30;
                    if(i29 != 1)
                    {
                        a60 = a128;
                        i31 = i160;
                        i32 = i29;
                        i33 = i161;
                        break label4;
                    }
                    else
                    {
                        a122 = a127;
                        i154 = i159;
                    }
                    com.cim.AIA.ElementArray a129 = this.string1EA;
                    AlignmentMatrix a130 = a122;
                    int i162 = i154;
                    AlignmentMatrix a131 = a130;
                    int i163 = i162;
                    AlignmentMatrix a132 = a131;
                    int i164 = i163;
                    StringBuilder a133 = new StringBuilder();
                    AlignmentMatrix a134 = a132;
                    int i165 = i164;
                    String[] a135 = this.data;
                    AlignmentMatrix a136 = a134;
                    int i166 = i165;
                    String s = a135[0];
                    AlignmentMatrix a137 = a136;
                    int i167 = i166;
                    int i168 = a75.x;
                    AlignmentMatrix a138 = a137;
                    int i169 = i167;
                    int i170 = i168 - 1;
                    int i171 = s.charAt(i170);
                    AlignmentMatrix a139 = a138;
                    int i172 = i169;
                    StringBuilder a140 = a133.append((char)i171);
                    AlignmentMatrix a141 = a139;
                    int i173 = i172;
                    StringBuilder a142 = a140.append("");
                    AlignmentMatrix a143 = a141;
                    int i174 = i173;
                    String s0 = a142.toString();
                    AlignmentMatrix a144 = a143;
                    int i175 = i174;
                    com.cim.AIA.Node a145 = new com.cim.AIA.Node((Object)s0, 0);
                    AlignmentMatrix a146 = a144;
                    int i176 = i175;
                    a129.setValue(i30, (com.cim.AIA.Element)a145);
                    AlignmentMatrix a147 = a146;
                    int i177 = i176;
                    com.cim.AIA.ElementArray a148 = this.string2EA;
                    AlignmentMatrix a149 = a147;
                    int i178 = i177;
                    AlignmentMatrix a150 = a149;
                    int i179 = i178;
                    AlignmentMatrix a151 = a150;
                    int i180 = i179;
                    StringBuilder a152 = new StringBuilder();
                    AlignmentMatrix a153 = a151;
                    int i181 = i180;
                    String[] a154 = this.data;
                    AlignmentMatrix a155 = a153;
                    int i182 = i181;
                    String s1 = a154[1];
                    AlignmentMatrix a156 = a155;
                    int i183 = i182;
                    int i184 = a75.y;
                    AlignmentMatrix a157 = a156;
                    int i185 = i183;
                    int i186 = i184 - 1;
                    int i187 = s1.charAt(i186);
                    AlignmentMatrix a158 = a157;
                    int i188 = i185;
                    StringBuilder a159 = a152.append((char)i187);
                    AlignmentMatrix a160 = a158;
                    int i189 = i188;
                    StringBuilder a161 = a159.append("");
                    AlignmentMatrix a162 = a160;
                    int i190 = i189;
                    String s2 = a161.toString();
                    AlignmentMatrix a163 = a162;
                    int i191 = i190;
                    com.cim.AIA.Node a164 = new com.cim.AIA.Node((Object)s2, 0);
                    AlignmentMatrix a165 = a163;
                    int i192 = i191;
                    a148.setValue(i30, (com.cim.AIA.Element)a164);
                    AlignmentMatrix a166 = a165;
                    int i193 = i192;
                    a58.highlightDiag();
                    AlignmentMatrix a167 = a166;
                    int i194 = i193;
                    AlignmentMatrix a168 = a167;
                    AlignmentMatrix a169 = a167;
                    int i195 = i194;
                    if(i194 != 1)
                    {
                        a123 = a169;
                        i155 = i195;
                    }
                    else
                    {
                        AlignmentMatrix a170 = a168;
                        a58.highlightA();
                        a123 = a170;
                        i155 = 1;
                    }
                    AlignmentMatrix a171 = a123;
                    AlignmentMatrix a172 = a123;
                    int i196 = i155;
                    if(i155 != 2)
                    {
                        a124 = a172;
                        i156 = i196;
                    }
                    else
                    {
                        AlignmentMatrix a173 = a171;
                        a58.highlightB();
                        a124 = a173;
                        i156 = 2;
                    }
                    AlignmentMatrix a174 = a124;
                    AlignmentMatrix a175 = a124;
                    int i197 = i156;
                    if(i156 != 3)
                    {
                        a125 = a175;
                        i157 = i197;
                    }
                    else
                    {
                        AlignmentMatrix a176 = a174;
                        a58.highlightC();
                        a125 = a176;
                        i157 = 3;
                    }
                    AlignmentMatrix a177 = a125;
                    int i198 = i157;
                    AlignmentMatrix a178 = a125;
                    int i199 = i157;
                    if(i157 == 1)
                    {
                        a126 = a178;
                        i158 = i199;
                    }
                    else
                    {
                        AlignmentMatrix a179 = a177;
                        int i200 = i198;
                        int i201 = a75.x;
                        AlignmentMatrix a180 = a179;
                        int i202 = i200;
                        int i203 = i201 - 1;
                        int i204 = a75.y;
                        AlignmentMatrix a181 = a180;
                        int i205 = i202;
                        int i206 = i204 - 1;
                        AlignmentNode a182 = a179.get(i203, i206);
                        AlignmentMatrix a183 = a181;
                        int i207 = i205;
                        a182.setIsDisabled(true);
                        AlignmentMatrix a184 = a183;
                        int i208 = i207;
                        a58.unHighlightDiag();
                        AlignmentMatrix a185 = a184;
                        int i209 = i208;
                        java.awt.Color a186 = java.awt.Color.black;
                        AlignmentMatrix a187 = a185;
                        int i210 = i209;
                        a58.setArrowColor(a186);
                        a126 = a187;
                        i158 = i210;
                    }
                    int i211 = i30 + 1;
                    a60 = a126;
                    i31 = i158;
                    i32 = 1;
                    i33 = i211;
                }
                label5: {
                    AlignmentMatrix a188 = null;
                    int i212 = 0;
                    AlignmentMatrix a189 = null;
                    int i213 = 0;
                    AlignmentMatrix a190 = null;
                    int i214 = 0;
                    AlignmentMatrix a191 = null;
                    int i215 = 0;
                    AlignmentMatrix a192 = null;
                    int i216 = 0;
                    AlignmentMatrix a193 = a60;
                    int i217 = i31;
                    AlignmentMatrix a194 = a60;
                    int i218 = i31;
                    int i219 = i33;
                    if(i32 != 3)
                    {
                        a61 = a194;
                        i34 = i218;
                        i35 = i32;
                        i36 = i219;
                        break label5;
                    }
                    else
                    {
                        a188 = a193;
                        i212 = i217;
                    }
                    com.cim.AIA.ElementArray a195 = this.string1EA;
                    AlignmentMatrix a196 = a188;
                    int i220 = i212;
                    AlignmentMatrix a197 = a196;
                    int i221 = i220;
                    AlignmentMatrix a198 = a197;
                    int i222 = i221;
                    StringBuilder a199 = new StringBuilder();
                    AlignmentMatrix a200 = a198;
                    int i223 = i222;
                    String[] a201 = this.data;
                    AlignmentMatrix a202 = a200;
                    int i224 = i223;
                    String s3 = a201[0];
                    AlignmentMatrix a203 = a202;
                    int i225 = i224;
                    int i226 = a75.x;
                    AlignmentMatrix a204 = a203;
                    int i227 = i225;
                    int i228 = i226 - 1;
                    int i229 = s3.charAt(i228);
                    AlignmentMatrix a205 = a204;
                    int i230 = i227;
                    StringBuilder a206 = a199.append((char)i229);
                    AlignmentMatrix a207 = a205;
                    int i231 = i230;
                    StringBuilder a208 = a206.append("");
                    AlignmentMatrix a209 = a207;
                    int i232 = i231;
                    String s4 = a208.toString();
                    AlignmentMatrix a210 = a209;
                    int i233 = i232;
                    com.cim.AIA.Node a211 = new com.cim.AIA.Node((Object)s4, 0);
                    AlignmentMatrix a212 = a210;
                    int i234 = i233;
                    a195.setValue(i33, (com.cim.AIA.Element)a211);
                    AlignmentMatrix a213 = a212;
                    int i235 = i234;
                    com.cim.AIA.ElementArray a214 = this.string2EA;
                    AlignmentMatrix a215 = a213;
                    int i236 = i235;
                    AlignmentMatrix a216 = a215;
                    int i237 = i236;
                    com.cim.AIA.Node a217 = new com.cim.AIA.Node((Object)"-", 0);
                    AlignmentMatrix a218 = a216;
                    int i238 = i237;
                    a214.setValue(i33, (com.cim.AIA.Element)a217);
                    AlignmentMatrix a219 = a218;
                    int i239 = i238;
                    a58.highlightLeft();
                    AlignmentMatrix a220 = a219;
                    int i240 = i239;
                    AlignmentMatrix a221 = a220;
                    AlignmentMatrix a222 = a220;
                    int i241 = i240;
                    if(i240 != 1)
                    {
                        a189 = a222;
                        i213 = i241;
                    }
                    else
                    {
                        AlignmentMatrix a223 = a221;
                        a58.highlightA();
                        a189 = a223;
                        i213 = 1;
                    }
                    AlignmentMatrix a224 = a189;
                    AlignmentMatrix a225 = a189;
                    int i242 = i213;
                    if(i213 != 2)
                    {
                        a190 = a225;
                        i214 = i242;
                    }
                    else
                    {
                        AlignmentMatrix a226 = a224;
                        a58.highlightB();
                        a190 = a226;
                        i214 = 2;
                    }
                    AlignmentMatrix a227 = a190;
                    AlignmentMatrix a228 = a190;
                    int i243 = i214;
                    if(i214 != 3)
                    {
                        a191 = a228;
                        i215 = i243;
                    }
                    else
                    {
                        AlignmentMatrix a229 = a227;
                        a58.highlightC();
                        a191 = a229;
                        i215 = 3;
                    }
                    AlignmentMatrix a230 = a191;
                    int i244 = i215;
                    AlignmentMatrix a231 = a191;
                    int i245 = i215;
                    if(i215 == 3)
                    {
                        a192 = a231;
                        i216 = i245;
                    }
                    else
                    {
                        AlignmentMatrix a232 = a230;
                        int i246 = i244;
                        int i247 = a75.x;
                        AlignmentMatrix a233 = a232;
                        int i248 = i246;
                        int i249 = i247 - 1;
                        int i250 = a75.y;
                        AlignmentMatrix a234 = a233;
                        int i251 = i248;
                        AlignmentNode a235 = a232.get(i249, i250);
                        AlignmentMatrix a236 = a234;
                        int i252 = i251;
                        a235.setIsDisabled(true);
                        AlignmentMatrix a237 = a236;
                        int i253 = i252;
                        a58.unHighlightLeft();
                        AlignmentMatrix a238 = a237;
                        int i254 = i253;
                        java.awt.Color a239 = java.awt.Color.black;
                        AlignmentMatrix a240 = a238;
                        int i255 = i254;
                        a58.setArrowColor(a239);
                        a192 = a240;
                        i216 = i255;
                    }
                    int i256 = i33 + 1;
                    a61 = a192;
                    i34 = i216;
                    i35 = 3;
                    i36 = i256;
                }
                label6: {
                    AlignmentMatrix a241 = null;
                    int i257 = 0;
                    AlignmentMatrix a242 = null;
                    int i258 = 0;
                    AlignmentMatrix a243 = null;
                    int i259 = 0;
                    AlignmentMatrix a244 = null;
                    int i260 = 0;
                    AlignmentMatrix a245 = null;
                    int i261 = 0;
                    AlignmentMatrix a246 = a61;
                    int i262 = i34;
                    AlignmentMatrix a247 = a61;
                    int i263 = i34;
                    int i264 = i36;
                    if(i35 != 2)
                    {
                        a62 = a247;
                        i37 = i263;
                        i38 = i35;
                        i39 = i264;
                        break label6;
                    }
                    else
                    {
                        a241 = a246;
                        i257 = i262;
                    }
                    com.cim.AIA.ElementArray a248 = this.string1EA;
                    AlignmentMatrix a249 = a241;
                    int i265 = i257;
                    AlignmentMatrix a250 = a249;
                    int i266 = i265;
                    com.cim.AIA.Node a251 = new com.cim.AIA.Node((Object)"-", 0);
                    AlignmentMatrix a252 = a250;
                    int i267 = i266;
                    a248.setValue(i36, (com.cim.AIA.Element)a251);
                    AlignmentMatrix a253 = a252;
                    int i268 = i267;
                    com.cim.AIA.ElementArray a254 = this.string2EA;
                    AlignmentMatrix a255 = a253;
                    int i269 = i268;
                    AlignmentMatrix a256 = a255;
                    int i270 = i269;
                    AlignmentMatrix a257 = a256;
                    int i271 = i270;
                    StringBuilder a258 = new StringBuilder();
                    AlignmentMatrix a259 = a257;
                    int i272 = i271;
                    String[] a260 = this.data;
                    AlignmentMatrix a261 = a259;
                    int i273 = i272;
                    String s5 = a260[1];
                    AlignmentMatrix a262 = a261;
                    int i274 = i273;
                    int i275 = a75.y;
                    AlignmentMatrix a263 = a262;
                    int i276 = i274;
                    int i277 = i275 - 1;
                    int i278 = s5.charAt(i277);
                    AlignmentMatrix a264 = a263;
                    int i279 = i276;
                    StringBuilder a265 = a258.append((char)i278);
                    AlignmentMatrix a266 = a264;
                    int i280 = i279;
                    StringBuilder a267 = a265.append("");
                    AlignmentMatrix a268 = a266;
                    int i281 = i280;
                    String s6 = a267.toString();
                    AlignmentMatrix a269 = a268;
                    int i282 = i281;
                    com.cim.AIA.Node a270 = new com.cim.AIA.Node((Object)s6, 0);
                    AlignmentMatrix a271 = a269;
                    int i283 = i282;
                    a254.setValue(i36, (com.cim.AIA.Element)a270);
                    AlignmentMatrix a272 = a271;
                    int i284 = i283;
                    a58.highlightUp();
                    AlignmentMatrix a273 = a272;
                    int i285 = i284;
                    AlignmentMatrix a274 = a273;
                    AlignmentMatrix a275 = a273;
                    int i286 = i285;
                    if(i285 != 1)
                    {
                        a242 = a275;
                        i258 = i286;
                    }
                    else
                    {
                        AlignmentMatrix a276 = a274;
                        a58.highlightA();
                        a242 = a276;
                        i258 = 1;
                    }
                    AlignmentMatrix a277 = a242;
                    AlignmentMatrix a278 = a242;
                    int i287 = i258;
                    if(i258 != 2)
                    {
                        a243 = a278;
                        i259 = i287;
                    }
                    else
                    {
                        AlignmentMatrix a279 = a277;
                        a58.highlightB();
                        a243 = a279;
                        i259 = 2;
                    }
                    AlignmentMatrix a280 = a243;
                    AlignmentMatrix a281 = a243;
                    int i288 = i259;
                    if(i259 != 3)
                    {
                        a244 = a281;
                        i260 = i288;
                    }
                    else
                    {
                        AlignmentMatrix a282 = a280;
                        a58.highlightC();
                        a244 = a282;
                        i260 = 3;
                    }
                    AlignmentMatrix a283 = a244;
                    int i289 = i260;
                    AlignmentMatrix a284 = a244;
                    int i290 = i260;
                    if(i260 == 2)
                    {
                        a245 = a284;
                        i261 = i290;
                    }
                    else
                    {
                        AlignmentMatrix a285 = a283;
                        int i291 = i289;
                        int i292 = a75.x;
                        AlignmentMatrix a286 = a285;
                        int i293 = i291;
                        int i294 = a75.y;
                        AlignmentMatrix a287 = a286;
                        int i295 = i293;
                        int i296 = i294 - 1;
                        AlignmentNode a288 = a285.get(i292, i296);
                        AlignmentMatrix a289 = a287;
                        int i297 = i295;
                        a288.setIsDisabled(true);
                        AlignmentMatrix a290 = a289;
                        int i298 = i297;
                        a58.unHighlightUp();
                        AlignmentMatrix a291 = a290;
                        int i299 = i298;
                        java.awt.Color a292 = java.awt.Color.black;
                        AlignmentMatrix a293 = a291;
                        int i300 = i299;
                        a58.setArrowColor(a292);
                        a245 = a293;
                        i261 = i300;
                    }
                    int i301 = i36 + 1;
                    a62 = a245;
                    i37 = i261;
                    i38 = 2;
                    i39 = i301;
                }
                a50 = a62;
                i12 = i37;
                i13 = i38;
                i14 = i39;
            }
            int i302 = Alignment.isFirstAlignment?1:0;
            if(i302 == 0)
            {
                this.setPosition("3.5.1");
                this.setPosition("3.5");
            }
            else
            {
                Alignment.isFirstAlignment = false;
                this.setPosition("3.4");
                this.setPosition("3.5");
            }
        }
        int i303 = a23.getTraceA()?1:0;
        if(i303 != 0)
        {
            a23.highlightA();
            this.gapFindPathNonInteractive(i3, i4, 1, a);
        }
        int i304 = a23.getTraceB()?1:0;
        if(i304 != 0)
        {
            a23.highlightB();
            this.gapFindPathNonInteractive(i3, i4, 2, a);
        }
        int i305 = a23.getTraceC()?1:0;
        if(i305 != 0)
        {
            a23.highlightC();
            this.gapFindPathNonInteractive(i3, i4, 3, a);
        }
        Object a294 = a.pop();
        Object a295 = a.pop();
    }
    
    protected java.util.Vector generateQuestions()
    {
        return null;
    }
    
    public com.cim.AIA.MultipleTween generateTweens(com.cim.AIA.Paintable a, Object a0, int i)
    {
        return null;
    }
    
    public AlignmentMatrix3D get3D()
    {
        AlignmentMatrix3D a = this.the3D;
        return a;
    }
    
    public AlignmentMatrix getAMatrix()
    {
        AlignmentMatrix a = null;
        int i = Alignment.currentVariation;
        if(i != 2)
        {
            a = null;
        }
        else
        {
            AlignmentMatrix a0 = this.aMatrix;
            a = a0;
        }
        return a;
    }
    
    public AlignmentMatrix getBMatrix()
    {
        AlignmentMatrix a = null;
        int i = Alignment.currentVariation;
        if(i != 2)
        {
            a = null;
        }
        else
        {
            AlignmentMatrix a0 = this.bMatrix;
            a = a0;
        }
        return a;
    }
    
    public String getClassName()
    {
        String s = localization.Messages.getString("Alignment.244");
        return s;
    }
    
    public AlignmentMatrix getCMatrix()
    {
        AlignmentMatrix a = null;
        int i = Alignment.currentVariation;
        if(i != 2)
        {
            a = null;
        }
        else
        {
            AlignmentMatrix a0 = this.cMatrix;
            a = a0;
        }
        return a;
    }
    
    public int getInitialGapCost()
    {
        int i = this.initialGapCost;
        return i;
    }
    
    public AlignmentKey getKey()
    {
        AlignmentKey a = this.theAlignmentKey;
        return a;
    }
    
    public int getMatchCost()
    {
        int i = this.matchCost;
        return i;
    }
    
    public AlignmentMatrix getMatrix()
    {
        AlignmentMatrix a = null;
        int i = Alignment.currentVariation;
        if(i != 1)
        {
            a = null;
        }
        else
        {
            AlignmentMatrix a0 = this.theMatrix;
            a = a0;
        }
        return a;
    }
    
    public AlignmentMinimum getMinimum()
    {
        AlignmentMinimum a = this.theMinimum;
        return a;
    }
    
    public int getMismatchCost()
    {
        int i = this.mismatchCost;
        return i;
    }
    
    public AlignmentNode getNode()
    {
        AlignmentNode a = this.tempNode;
        return a;
    }
    
    public int getRunningGapCost()
    {
        int i = this.runningGapCost;
        return i;
    }
    
    public int getSpaceCost()
    {
        int i = this.spaceCost;
        return i;
    }
    
    public com.cim.AIA.ElementArray getString1EA()
    {
        com.cim.AIA.ElementArray a = this.string2EA;
        return a;
    }
    
    public com.cim.AIA.ElementArray getString2EA()
    {
        com.cim.AIA.ElementArray a = this.string1EA;
        return a;
    }
    
    protected void globalDistGapAlignment()
    {
        AlignmentMatrix a = this.aMatrix;
        a.add(0, 0, 0);
        AlignmentMatrix a0 = this.bMatrix;
        a0.add(0, 0, 0);
        AlignmentMatrix a1 = this.cMatrix;
        a1.add(0, 0, 0);
        this.setPosition("1.1");
        int i = 1;
        while(true)
        {
            int i0 = this.xSize;
            if(i >= i0)
            {
                break;
            }
            else
            {
                AlignmentMatrix a2 = this.bMatrix;
                a2.add(5000, i, 0);
                AlignmentMatrix a3 = this.cMatrix;
                int i1 = this.initialGapCost;
                int i2 = this.runningGapCost;
                int i3 = i2 * i;
                int i4 = i1 + i3;
                a3.add(i4, i, 0);
                AlignmentMatrix a4 = this.cMatrix;
                AlignmentNode a5 = a4.get(i, 0);
                a5.setTraceLeft(true);
                a5.setTraceC(true);
                AlignmentMatrix a6 = this.aMatrix;
                a6.add(5000, i, 0);
                int i5 = i + 1;
                i = i5;
            }
        }
        this.setPosition("1.2");
        int i6 = 1;
        while(true)
        {
            int i7 = this.ySize;
            if(i6 >= i7)
            {
                break;
            }
            else
            {
                AlignmentMatrix a7 = this.bMatrix;
                int i8 = this.initialGapCost;
                int i9 = this.runningGapCost;
                int i10 = i9 * i6;
                int i11 = i8 + i10;
                a7.add(i11, 0, i6);
                AlignmentMatrix a8 = this.bMatrix;
                AlignmentNode a9 = a8.get(0, i6);
                a9.setTraceUp(true);
                a9.setTraceB(true);
                AlignmentMatrix a10 = this.cMatrix;
                a10.add(5000, 0, i6);
                AlignmentMatrix a11 = this.aMatrix;
                a11.add(5000, 0, i6);
                int i12 = i6 + 1;
                i6 = i12;
            }
        }
        this.setPosition("1.3");
        int i13 = 1;
        label0: while(true)
        {
            int i14 = this.ySize;
            if(i13 >= i14)
            {
                this.setPosition("2.2");
                return;
            }
            AlignmentMatrix a12 = this.aMatrix;
            a12.setYIndexHighlight(i13);
            AlignmentMatrix a13 = this.bMatrix;
            a13.setYIndexHighlight(i13);
            AlignmentMatrix a14 = this.cMatrix;
            a14.setYIndexHighlight(i13);
            this.setPosition("2.1");
            int i15 = 1;
            while(true)
            {
                int i16 = 0;
                int i17 = this.xSize;
                if(i15 >= i17)
                {
                    this.setPosition("2.1.2");
                    int i18 = i13 + 1;
                    i13 = i18;
                    continue label0;
                }
                AlignmentMatrix a15 = this.aMatrix;
                a15.setXIndexHighlight(i15);
                AlignmentMatrix a16 = this.bMatrix;
                a16.setXIndexHighlight(i15);
                AlignmentMatrix a17 = this.cMatrix;
                a17.setXIndexHighlight(i15);
                this.setPosition("2.1.1");
                String s = localization.Messages.getString("Alignment.227");
                AlignmentMinimum a18 = new AlignmentMinimum(s);
                this.theMinimum = a18;
                AlignmentMinimum a19 = this.theMinimum;
                a19.add();
                AlignmentMinimum a20 = this.theMinimum;
                a20.add();
                AlignmentMinimum a21 = this.theMinimum;
                a21.add();
                AlignmentMatrix a22 = this.aMatrix;
                int i19 = i13 - 1;
                AlignmentNode a23 = a22.get(i15, i19);
                a23.highlight();
                AlignmentMinimum a24 = this.theMinimum;
                AlignmentMatrix a25 = this.aMatrix;
                int i20 = i13 - 1;
                int i21 = a25.valueAt(i15, i20);
                int i22 = this.initialGapCost;
                int i23 = this.runningGapCost;
                int i24 = i22 + i23;
                a24.set(0, i21, i24);
                this.setPosition("2.1.1.1.2");
                a23.unHighlight();
                AlignmentMatrix a26 = this.bMatrix;
                int i25 = i13 - 1;
                AlignmentNode a27 = a26.get(i15, i25);
                a27.highlight();
                AlignmentMinimum a28 = this.theMinimum;
                AlignmentMatrix a29 = this.bMatrix;
                int i26 = i13 - 1;
                int i27 = a29.valueAt(i15, i26);
                int i28 = this.runningGapCost;
                a28.set(1, i27, i28);
                this.setPosition("2.1.1.1.4");
                a27.unHighlight();
                AlignmentMatrix a30 = this.cMatrix;
                int i29 = i13 - 1;
                AlignmentNode a31 = a30.get(i15, i29);
                a31.highlight();
                AlignmentMinimum a32 = this.theMinimum;
                AlignmentMatrix a33 = this.cMatrix;
                int i30 = i13 - 1;
                int i31 = a33.valueAt(i15, i30);
                int i32 = this.initialGapCost;
                int i33 = this.runningGapCost;
                int i34 = i32 + i33;
                a32.set(2, i31, i34);
                this.setPosition("2.1.1.1.6");
                a31.unHighlight();
                AlignmentMatrix a34 = this.aMatrix;
                int i35 = i13 - 1;
                int i36 = a34.valueAt(i15, i35);
                int i37 = this.initialGapCost;
                int i38 = i36 + i37;
                int i39 = this.runningGapCost;
                int i40 = i38 + i39;
                AlignmentMatrix a35 = this.bMatrix;
                int i41 = i13 - 1;
                int i42 = a35.valueAt(i15, i41);
                int i43 = this.runningGapCost;
                int i44 = i42 + i43;
                AlignmentMatrix a36 = this.cMatrix;
                int i45 = i13 - 1;
                int i46 = a36.valueAt(i15, i45);
                int i47 = this.initialGapCost;
                int i48 = i46 + i47;
                int i49 = this.runningGapCost;
                int i50 = i48 + i49;
                int i51 = Math.min(i44, i50);
                int i52 = Math.min(i40, i51);
                AlignmentMatrix a37 = this.bMatrix;
                a37.add(i52, i15, i13);
                AlignmentMatrix a38 = this.bMatrix;
                AlignmentNode a39 = a38.get(i15, i13);
                a39.setTraceUp(true);
                if(i52 != i40)
                {
                    AlignmentMatrix a40 = this.aMatrix;
                    int i53 = i13 - 1;
                    AlignmentNode a41 = a40.get(i15, i53);
                    a41.setIsDisabled(true);
                    a39.setTraceA(false);
                }
                else
                {
                    a39.highlightA();
                    AlignmentMatrix a42 = this.aMatrix;
                    int i54 = i13 - 1;
                    AlignmentNode a43 = a42.get(i15, i54);
                    a43.highlight();
                    AlignmentMinimum a44 = this.theMinimum;
                    a44.highlight(0);
                    a39.setTraceA(true);
                }
                if(i52 != i44)
                {
                    AlignmentMatrix a45 = this.bMatrix;
                    int i55 = i13 - 1;
                    AlignmentNode a46 = a45.get(i15, i55);
                    a46.setIsDisabled(true);
                    a39.setTraceB(false);
                }
                else
                {
                    a39.highlightB();
                    AlignmentMatrix a47 = this.bMatrix;
                    int i56 = i13 - 1;
                    AlignmentNode a48 = a47.get(i15, i56);
                    a48.highlight();
                    AlignmentMinimum a49 = this.theMinimum;
                    a49.highlight(1);
                    a39.setTraceB(true);
                }
                if(i52 != i50)
                {
                    AlignmentMatrix a50 = this.cMatrix;
                    int i57 = i13 - 1;
                    AlignmentNode a51 = a50.get(i15, i57);
                    a51.setIsDisabled(true);
                    a39.setTraceC(false);
                }
                else
                {
                    a39.highlightC();
                    AlignmentMatrix a52 = this.cMatrix;
                    int i58 = i13 - 1;
                    AlignmentNode a53 = a52.get(i15, i58);
                    a53.highlight();
                    AlignmentMinimum a54 = this.theMinimum;
                    a54.highlight(2);
                    a39.setTraceC(true);
                }
                a39.highlight();
                java.awt.Color a55 = Alignment.HIGHLIGHT_RING_COLOR;
                a39.setRingColor(a55);
                this.setPosition("2.1.1.1");
                a39.unHighlightA();
                a39.unHighlightB();
                a39.unHighlightC();
                java.awt.Color a56 = Alignment.DEFAULT_RING_COLOR;
                a39.setRingColor(a56);
                a39.unHighlight();
                AlignmentMatrix a57 = this.aMatrix;
                int i59 = i13 - 1;
                AlignmentNode a58 = a57.get(i15, i59);
                a58.setIsDisabled(false);
                AlignmentMatrix a59 = this.aMatrix;
                int i60 = i13 - 1;
                AlignmentNode a60 = a59.get(i15, i60);
                a60.unHighlight();
                AlignmentMatrix a61 = this.bMatrix;
                int i61 = i13 - 1;
                AlignmentNode a62 = a61.get(i15, i61);
                a62.setIsDisabled(false);
                AlignmentMatrix a63 = this.bMatrix;
                int i62 = i13 - 1;
                AlignmentNode a64 = a63.get(i15, i62);
                a64.unHighlight();
                AlignmentMatrix a65 = this.cMatrix;
                int i63 = i13 - 1;
                AlignmentNode a66 = a65.get(i15, i63);
                a66.setIsDisabled(false);
                AlignmentMatrix a67 = this.cMatrix;
                int i64 = i13 - 1;
                AlignmentNode a68 = a67.get(i15, i64);
                a68.unHighlight();
                this.theMinimum = null;
                String s0 = localization.Messages.getString("Alignment.232");
                AlignmentMinimum a69 = new AlignmentMinimum(s0);
                this.theMinimum = a69;
                AlignmentMinimum a70 = this.theMinimum;
                a70.add();
                AlignmentMinimum a71 = this.theMinimum;
                a71.add();
                AlignmentMinimum a72 = this.theMinimum;
                a72.add();
                AlignmentMatrix a73 = this.aMatrix;
                int i65 = i15 - 1;
                AlignmentNode a74 = a73.get(i65, i13);
                a74.highlight();
                AlignmentMinimum a75 = this.theMinimum;
                AlignmentMatrix a76 = this.aMatrix;
                int i66 = i15 - 1;
                int i67 = a76.valueAt(i66, i13);
                int i68 = this.initialGapCost;
                int i69 = this.runningGapCost;
                int i70 = i68 + i69;
                a75.set(0, i67, i70);
                this.setPosition("2.1.1.2.2");
                a74.unHighlight();
                AlignmentMatrix a77 = this.bMatrix;
                int i71 = i15 - 1;
                AlignmentNode a78 = a77.get(i71, i13);
                a78.highlight();
                AlignmentMinimum a79 = this.theMinimum;
                AlignmentMatrix a80 = this.bMatrix;
                int i72 = i15 - 1;
                int i73 = a80.valueAt(i72, i13);
                int i74 = this.initialGapCost;
                int i75 = this.runningGapCost;
                int i76 = i74 + i75;
                a79.set(1, i73, i76);
                this.setPosition("2.1.1.2.4");
                a78.unHighlight();
                AlignmentMatrix a81 = this.cMatrix;
                int i77 = i15 - 1;
                AlignmentNode a82 = a81.get(i77, i13);
                a82.highlight();
                AlignmentMinimum a83 = this.theMinimum;
                AlignmentMatrix a84 = this.cMatrix;
                int i78 = i15 - 1;
                int i79 = a84.valueAt(i78, i13);
                int i80 = this.runningGapCost;
                a83.set(2, i79, i80);
                this.setPosition("2.1.1.2.6");
                a82.unHighlight();
                AlignmentMatrix a85 = this.aMatrix;
                int i81 = i15 - 1;
                int i82 = a85.valueAt(i81, i13);
                int i83 = this.initialGapCost;
                int i84 = i82 + i83;
                int i85 = this.runningGapCost;
                int i86 = i84 + i85;
                AlignmentMatrix a86 = this.bMatrix;
                int i87 = i15 - 1;
                int i88 = a86.valueAt(i87, i13);
                int i89 = this.initialGapCost;
                int i90 = i88 + i89;
                int i91 = this.runningGapCost;
                int i92 = i90 + i91;
                AlignmentMatrix a87 = this.cMatrix;
                int i93 = i15 - 1;
                int i94 = a87.valueAt(i93, i13);
                int i95 = this.runningGapCost;
                int i96 = i94 + i95;
                int i97 = Math.min(i92, i96);
                int i98 = Math.min(i86, i97);
                AlignmentMatrix a88 = this.cMatrix;
                a88.add(i98, i15, i13);
                AlignmentMatrix a89 = this.cMatrix;
                AlignmentNode a90 = a89.get(i15, i13);
                a90.setTraceLeft(true);
                if(i98 != i86)
                {
                    AlignmentMatrix a91 = this.aMatrix;
                    int i99 = i15 - 1;
                    AlignmentNode a92 = a91.get(i99, i13);
                    a92.setIsDisabled(true);
                    a90.setTraceA(false);
                }
                else
                {
                    a90.highlightA();
                    AlignmentMatrix a93 = this.aMatrix;
                    int i100 = i15 - 1;
                    AlignmentNode a94 = a93.get(i100, i13);
                    a94.highlight();
                    AlignmentMinimum a95 = this.theMinimum;
                    a95.highlight(0);
                    a90.setTraceA(true);
                }
                if(i98 != i92)
                {
                    AlignmentMatrix a96 = this.bMatrix;
                    int i101 = i15 - 1;
                    AlignmentNode a97 = a96.get(i101, i13);
                    a97.setIsDisabled(true);
                    a90.setTraceB(false);
                }
                else
                {
                    a90.highlightB();
                    AlignmentMatrix a98 = this.bMatrix;
                    int i102 = i15 - 1;
                    AlignmentNode a99 = a98.get(i102, i13);
                    a99.highlight();
                    AlignmentMinimum a100 = this.theMinimum;
                    a100.highlight(1);
                    a90.setTraceB(true);
                }
                if(i98 != i96)
                {
                    AlignmentMatrix a101 = this.cMatrix;
                    int i103 = i15 - 1;
                    AlignmentNode a102 = a101.get(i103, i13);
                    a102.setIsDisabled(true);
                    a90.setTraceC(false);
                }
                else
                {
                    a90.highlightC();
                    AlignmentMatrix a103 = this.cMatrix;
                    int i104 = i15 - 1;
                    AlignmentNode a104 = a103.get(i104, i13);
                    a104.highlight();
                    AlignmentMinimum a105 = this.theMinimum;
                    a105.highlight(2);
                    a90.setTraceC(true);
                }
                a90.highlight();
                java.awt.Color a106 = Alignment.HIGHLIGHT_RING_COLOR;
                a90.setRingColor(a106);
                this.setPosition("2.1.1.2");
                a90.unHighlightA();
                a90.unHighlightB();
                a90.unHighlightC();
                java.awt.Color a107 = Alignment.DEFAULT_RING_COLOR;
                a90.setRingColor(a107);
                a90.unHighlight();
                AlignmentMatrix a108 = this.aMatrix;
                int i105 = i15 - 1;
                AlignmentNode a109 = a108.get(i105, i13);
                a109.setIsDisabled(false);
                AlignmentMatrix a110 = this.aMatrix;
                int i106 = i15 - 1;
                AlignmentNode a111 = a110.get(i106, i13);
                a111.unHighlight();
                AlignmentMatrix a112 = this.bMatrix;
                int i107 = i15 - 1;
                AlignmentNode a113 = a112.get(i107, i13);
                a113.setIsDisabled(false);
                AlignmentMatrix a114 = this.bMatrix;
                int i108 = i15 - 1;
                AlignmentNode a115 = a114.get(i108, i13);
                a115.unHighlight();
                AlignmentMatrix a116 = this.cMatrix;
                int i109 = i15 - 1;
                AlignmentNode a117 = a116.get(i109, i13);
                a117.setIsDisabled(false);
                AlignmentMatrix a118 = this.cMatrix;
                int i110 = i15 - 1;
                AlignmentNode a119 = a118.get(i110, i13);
                a119.unHighlight();
                this.theMinimum = null;
                String[] a120 = this.data;
                String s1 = a120[0];
                int i111 = i15 - 1;
                int i112 = s1.charAt(i111);
                String[] a121 = this.data;
                String s2 = a121[1];
                int i113 = i13 - 1;
                int i114 = s2.charAt(i113);
                if(i112 != i114)
                {
                    int i115 = this.mismatchCost;
                    i16 = i115;
                }
                else
                {
                    int i116 = this.matchCost;
                    i16 = i116;
                }
                String s3 = localization.Messages.getString("Alignment.237");
                AlignmentMinimum a122 = new AlignmentMinimum(s3);
                this.theMinimum = a122;
                AlignmentMinimum a123 = this.theMinimum;
                a123.add();
                AlignmentMinimum a124 = this.theMinimum;
                a124.add();
                AlignmentMinimum a125 = this.theMinimum;
                a125.add();
                AlignmentMatrix a126 = this.aMatrix;
                int i117 = i15 - 1;
                int i118 = i13 - 1;
                AlignmentNode a127 = a126.get(i117, i118);
                a127.highlight();
                AlignmentMinimum a128 = this.theMinimum;
                AlignmentMatrix a129 = this.aMatrix;
                int i119 = i15 - 1;
                int i120 = i13 - 1;
                int i121 = a129.valueAt(i119, i120);
                a128.set(0, i121, i16);
                this.setPosition("2.1.1.3.1");
                a127.unHighlight();
                AlignmentMatrix a130 = this.bMatrix;
                int i122 = i15 - 1;
                int i123 = i13 - 1;
                AlignmentNode a131 = a130.get(i122, i123);
                a131.highlight();
                AlignmentMinimum a132 = this.theMinimum;
                AlignmentMatrix a133 = this.bMatrix;
                int i124 = i15 - 1;
                int i125 = i13 - 1;
                int i126 = a133.valueAt(i124, i125);
                a132.set(1, i126, i16);
                this.setPosition("2.1.1.3.2");
                a131.unHighlight();
                AlignmentMatrix a134 = this.cMatrix;
                int i127 = i15 - 1;
                int i128 = i13 - 1;
                AlignmentNode a135 = a134.get(i127, i128);
                a135.highlight();
                AlignmentMinimum a136 = this.theMinimum;
                AlignmentMatrix a137 = this.cMatrix;
                int i129 = i15 - 1;
                int i130 = i13 - 1;
                int i131 = a137.valueAt(i129, i130);
                a136.set(2, i131, i16);
                this.setPosition("2.1.1.3.3");
                a135.unHighlight();
                AlignmentMatrix a138 = this.aMatrix;
                int i132 = i15 - 1;
                int i133 = i13 - 1;
                int i134 = a138.valueAt(i132, i133);
                AlignmentMatrix a139 = this.bMatrix;
                int i135 = i15 - 1;
                int i136 = i13 - 1;
                int i137 = a139.valueAt(i135, i136);
                AlignmentMatrix a140 = this.cMatrix;
                int i138 = i15 - 1;
                int i139 = i13 - 1;
                int i140 = a140.valueAt(i138, i139);
                int i141 = Math.min(i137, i140);
                int i142 = Math.min(i134, i141);
                AlignmentMatrix a141 = this.aMatrix;
                int i143 = i142 + i16;
                a141.add(i143, i15, i13);
                AlignmentMatrix a142 = this.aMatrix;
                AlignmentNode a143 = a142.get(i15, i13);
                a143.setTraceDiag(true);
                if(i142 != i134)
                {
                    AlignmentMatrix a144 = this.aMatrix;
                    int i144 = i15 - 1;
                    int i145 = i13 - 1;
                    AlignmentNode a145 = a144.get(i144, i145);
                    a145.setIsDisabled(true);
                    a143.setTraceA(false);
                }
                else
                {
                    a143.highlightA();
                    AlignmentMatrix a146 = this.aMatrix;
                    int i146 = i15 - 1;
                    int i147 = i13 - 1;
                    AlignmentNode a147 = a146.get(i146, i147);
                    a147.highlight();
                    AlignmentMinimum a148 = this.theMinimum;
                    a148.highlight(0);
                    a143.setTraceA(true);
                }
                if(i142 != i137)
                {
                    AlignmentMatrix a149 = this.bMatrix;
                    int i148 = i15 - 1;
                    int i149 = i13 - 1;
                    AlignmentNode a150 = a149.get(i148, i149);
                    a150.setIsDisabled(true);
                    a143.setTraceB(false);
                }
                else
                {
                    a143.highlightB();
                    AlignmentMatrix a151 = this.bMatrix;
                    int i150 = i15 - 1;
                    int i151 = i13 - 1;
                    AlignmentNode a152 = a151.get(i150, i151);
                    a152.highlight();
                    AlignmentMinimum a153 = this.theMinimum;
                    a153.highlight(1);
                    a143.setTraceB(true);
                }
                if(i142 != i140)
                {
                    AlignmentMatrix a154 = this.cMatrix;
                    int i152 = i15 - 1;
                    int i153 = i13 - 1;
                    AlignmentNode a155 = a154.get(i152, i153);
                    a155.setIsDisabled(true);
                    a143.setTraceC(false);
                }
                else
                {
                    a143.highlightC();
                    AlignmentMatrix a156 = this.cMatrix;
                    int i154 = i15 - 1;
                    int i155 = i13 - 1;
                    AlignmentNode a157 = a156.get(i154, i155);
                    a157.highlight();
                    AlignmentMinimum a158 = this.theMinimum;
                    a158.highlight(2);
                    a143.setTraceC(true);
                }
                a143.highlight();
                java.awt.Color a159 = Alignment.HIGHLIGHT_RING_COLOR;
                a143.setRingColor(a159);
                this.setPosition("2.1.1.3");
                a143.unHighlightA();
                a143.unHighlightB();
                a143.unHighlightC();
                java.awt.Color a160 = Alignment.DEFAULT_RING_COLOR;
                a143.setRingColor(a160);
                a143.unHighlight();
                AlignmentMatrix a161 = this.aMatrix;
                int i156 = i15 - 1;
                int i157 = i13 - 1;
                AlignmentNode a162 = a161.get(i156, i157);
                a162.setIsDisabled(false);
                AlignmentMatrix a163 = this.aMatrix;
                int i158 = i15 - 1;
                int i159 = i13 - 1;
                AlignmentNode a164 = a163.get(i158, i159);
                a164.unHighlight();
                AlignmentMatrix a165 = this.bMatrix;
                int i160 = i15 - 1;
                int i161 = i13 - 1;
                AlignmentNode a166 = a165.get(i160, i161);
                a166.setIsDisabled(false);
                AlignmentMatrix a167 = this.bMatrix;
                int i162 = i15 - 1;
                int i163 = i13 - 1;
                AlignmentNode a168 = a167.get(i162, i163);
                a168.unHighlight();
                AlignmentMatrix a169 = this.cMatrix;
                int i164 = i15 - 1;
                int i165 = i13 - 1;
                AlignmentNode a170 = a169.get(i164, i165);
                a170.setIsDisabled(false);
                AlignmentMatrix a171 = this.cMatrix;
                int i166 = i15 - 1;
                int i167 = i13 - 1;
                AlignmentNode a172 = a171.get(i166, i167);
                a172.unHighlight();
                this.theMinimum = null;
                int i168 = i15 + 1;
                i15 = i168;
            }
        }
    }
    
    protected void globalDistNoGapAlignment()
    {
        int i = 0;
        while(true)
        {
            int i0 = this.xSize;
            if(i >= i0)
            {
                break;
            }
            AlignmentMatrix a = this.theMatrix;
            a.setXIndexHighlight(i);
            AlignmentMatrix a0 = this.theMatrix;
            a0.setYIndexHighlight(0);
            this.setPosition("1.1.1");
            AlignmentMatrix a1 = this.theMatrix;
            int i1 = this.spaceCost;
            int i2 = i * i1;
            a1.add(i2, i, 0);
            if(i != 0)
            {
                AlignmentMatrix a2 = this.theMatrix;
                AlignmentNode a3 = a2.get(i, 0);
                a3.setTraceLeft(true);
            }
            this.setPosition("1.1.2");
            int i3 = i + 1;
            i = i3;
        }
        AlignmentMatrix a4 = this.theMatrix;
        a4.setXIndexHighlight(-1);
        AlignmentMatrix a5 = this.theMatrix;
        a5.setYIndexHighlight(-1);
        this.setPosition("1.1.1");
        this.setPosition("1.1.4");
        int i4 = 0;
        while(true)
        {
            int i5 = this.ySize;
            if(i4 >= i5)
            {
                break;
            }
            AlignmentMatrix a6 = this.theMatrix;
            a6.setXIndexHighlight(0);
            AlignmentMatrix a7 = this.theMatrix;
            a7.setYIndexHighlight(i4);
            this.setPosition("1.1.5");
            AlignmentMatrix a8 = this.theMatrix;
            int i6 = this.spaceCost;
            int i7 = i4 * i6;
            a8.add(i7, 0, i4);
            if(i4 != 0)
            {
                AlignmentMatrix a9 = this.theMatrix;
                AlignmentNode a10 = a9.get(0, i4);
                a10.setTraceUp(true);
            }
            this.setPosition("1.1.6");
            int i8 = i4 + 1;
            i4 = i8;
        }
        AlignmentMatrix a11 = this.theMatrix;
        a11.setXIndexHighlight(-1);
        AlignmentMatrix a12 = this.theMatrix;
        a12.setYIndexHighlight(-1);
        this.setPosition("1.1.5");
        this.setPosition("1.1.8");
        int i9 = 1;
        label0: while(true)
        {
            int i10 = this.ySize;
            if(i9 >= i10)
            {
                this.setPosition("2.1");
                AlignmentMatrix a13 = this.theMatrix;
                a13.setXIndexHighlight(-1);
                AlignmentMatrix a14 = this.theMatrix;
                a14.setYIndexHighlight(-1);
                this.setPosition("2.2");
                return;
            }
            AlignmentMatrix a15 = this.theMatrix;
            a15.setYIndexHighlight(i9);
            this.setPosition("2.1");
            int i11 = 1;
            while(true)
            {
                int i12 = 0;
                int i13 = 0;
                int i14 = 0;
                int i15 = this.xSize;
                if(i11 >= i15)
                {
                    this.setPosition("2.1.1");
                    int i16 = i9 + 1;
                    i9 = i16;
                    continue label0;
                }
                AlignmentMatrix a16 = this.theMatrix;
                a16.setXIndexHighlight(i11);
                this.setPosition("2.1.1");
                String s = localization.Messages.getString("Alignment.214");
                AlignmentMinimum a17 = new AlignmentMinimum(s);
                this.theMinimum = a17;
                AlignmentMinimum a18 = this.theMinimum;
                a18.add();
                AlignmentMinimum a19 = this.theMinimum;
                a19.add();
                AlignmentMinimum a20 = this.theMinimum;
                a20.add();
                String[] a21 = this.data;
                String s0 = a21[0];
                int i17 = i11 - 1;
                int i18 = s0.charAt(i17);
                String[] a22 = this.data;
                String s1 = a22[1];
                int i19 = i9 - 1;
                int i20 = s1.charAt(i19);
                if(i18 != i20)
                {
                    int i21 = this.mismatchCost;
                    i12 = i21;
                }
                else
                {
                    int i22 = this.matchCost;
                    i12 = i22;
                }
                AlignmentMatrix a23 = this.theMatrix;
                int i23 = i11 - 1;
                AlignmentNode a24 = a23.get(i23, i9);
                a24.highlight();
                AlignmentMinimum a25 = this.theMinimum;
                AlignmentMatrix a26 = this.theMatrix;
                int i24 = i11 - 1;
                int i25 = a26.valueAt(i24, i9);
                int i26 = this.spaceCost;
                a25.set(0, i25, i26);
                this.setPosition("2.1.1.3");
                AlignmentMatrix a27 = this.theMatrix;
                int i27 = i9 - 1;
                AlignmentNode a28 = a27.get(i11, i27);
                a28.highlight();
                AlignmentMinimum a29 = this.theMinimum;
                AlignmentMatrix a30 = this.theMatrix;
                int i28 = i9 - 1;
                int i29 = a30.valueAt(i11, i28);
                int i30 = this.spaceCost;
                a29.set(1, i29, i30);
                this.setPosition("2.1.1.4");
                AlignmentMatrix a31 = this.theMatrix;
                int i31 = i11 - 1;
                int i32 = i9 - 1;
                AlignmentNode a32 = a31.get(i31, i32);
                a32.highlight();
                AlignmentMinimum a33 = this.theMinimum;
                AlignmentMatrix a34 = this.theMatrix;
                int i33 = i11 - 1;
                int i34 = i9 - 1;
                int i35 = a34.valueAt(i33, i34);
                a33.set(2, i35, i12);
                this.setPosition("2.1.1.5");
                AlignmentMatrix a35 = this.theMatrix;
                int i36 = i11 - 1;
                int i37 = i9 - 1;
                int i38 = a35.valueAt(i36, i37);
                int i39 = i38 + i12;
                AlignmentMatrix a36 = this.theMatrix;
                int i40 = i11 - 1;
                int i41 = a36.valueAt(i40, i9);
                int i42 = this.spaceCost;
                int i43 = i41 + i42;
                if(i43 >= i39)
                {
                    i13 = i39;
                }
                else
                {
                    AlignmentMatrix a37 = this.theMatrix;
                    int i44 = i11 - 1;
                    int i45 = a37.valueAt(i44, i9);
                    int i46 = this.spaceCost;
                    int i47 = i45 + i46;
                    i13 = i47;
                }
                AlignmentMatrix a38 = this.theMatrix;
                int i48 = i9 - 1;
                int i49 = a38.valueAt(i11, i48);
                int i50 = this.spaceCost;
                int i51 = i49 + i50;
                if(i51 >= i13)
                {
                    i14 = i13;
                }
                else
                {
                    AlignmentMatrix a39 = this.theMatrix;
                    int i52 = i9 - 1;
                    int i53 = a39.valueAt(i11, i52);
                    int i54 = this.spaceCost;
                    int i55 = i53 + i54;
                    i14 = i55;
                }
                AlignmentMatrix a40 = this.theMatrix;
                a40.add(i14, i11, i9);
                AlignmentMatrix a41 = this.theMatrix;
                AlignmentNode a42 = a41.get(i11, i9);
                AlignmentMatrix a43 = this.theMatrix;
                a43.unHighlight();
                AlignmentMatrix a44 = this.theMatrix;
                int i56 = i11 - 1;
                int i57 = i9 - 1;
                int i58 = a44.valueAt(i56, i57);
                int i59 = i58 + i12;
                if(i14 == i59)
                {
                    a42.setTraceDiag(true);
                    AlignmentMatrix a45 = this.theMatrix;
                    int i60 = i11 - 1;
                    int i61 = i9 - 1;
                    AlignmentNode a46 = a45.get(i60, i61);
                    a46.highlight();
                    AlignmentMinimum a47 = this.theMinimum;
                    a47.highlight(2);
                }
                AlignmentMatrix a48 = this.theMatrix;
                int i62 = i11 - 1;
                int i63 = a48.valueAt(i62, i9);
                int i64 = this.spaceCost;
                int i65 = i63 + i64;
                if(i14 == i65)
                {
                    a42.setTraceLeft(true);
                    AlignmentMatrix a49 = this.theMatrix;
                    int i66 = i11 - 1;
                    AlignmentNode a50 = a49.get(i66, i9);
                    a50.highlight();
                    AlignmentMinimum a51 = this.theMinimum;
                    a51.highlight(0);
                }
                AlignmentMatrix a52 = this.theMatrix;
                int i67 = i9 - 1;
                int i68 = a52.valueAt(i11, i67);
                int i69 = this.spaceCost;
                int i70 = i68 + i69;
                if(i14 == i70)
                {
                    a42.setTraceUp(true);
                    AlignmentMatrix a53 = this.theMatrix;
                    int i71 = i9 - 1;
                    AlignmentNode a54 = a53.get(i11, i71);
                    a54.highlight();
                    AlignmentMinimum a55 = this.theMinimum;
                    a55.highlight(1);
                }
                this.setPosition("2.1.1.2");
                AlignmentMatrix a56 = this.theMatrix;
                a56.unHighlight();
                AlignmentMinimum a57 = this.theMinimum;
                a57.unHighlight();
                this.theMinimum = null;
                int i72 = i11 + 1;
                i11 = i72;
            }
        }
    }
    
    protected void globalSimGapAlignment()
    {
        AlignmentMatrix a = this.theMatrix;
        a.add(0, 0, 0);
        java.io.PrintStream a0 = System.out;
        String s = localization.Messages.getString("Alignment.0");
        a0.println(s);
        int i = 1;
        while(true)
        {
            int i0 = this.xSize;
            if(i >= i0)
            {
                break;
            }
            else
            {
                int[][] a1 = this.E;
                int[] a2 = a1[i];
                int i1 = this.initialGapCost;
                int i2 = this.runningGapCost;
                int i3 = i2 * i;
                int i4 = i1 + i3;
                a2[0] = i4;
                AlignmentMatrix a3 = this.theMatrix;
                int[][] a4 = this.E;
                int[] a5 = a4[i];
                int i5 = a5[0];
                a3.add(i5, i, 0);
                AlignmentMatrix a6 = this.theMatrix;
                AlignmentNode a7 = a6.get(i, 0);
                a7.setTraceLeft(true);
                int i6 = i + 1;
                i = i6;
            }
        }
        this.printE();
        java.io.PrintStream a8 = System.out;
        String s0 = localization.Messages.getString("Alignment.278");
        a8.println(s0);
        int i7 = 1;
        while(true)
        {
            int i8 = this.ySize;
            if(i7 >= i8)
            {
                break;
            }
            else
            {
                int[][] a9 = this.F;
                int[] a10 = a9[0];
                int i9 = this.initialGapCost;
                int i10 = this.runningGapCost;
                int i11 = i10 * i7;
                int i12 = i9 + i11;
                a10[i7] = i12;
                AlignmentMatrix a11 = this.theMatrix;
                int[][] a12 = this.F;
                int[] a13 = a12[0];
                int i13 = a13[i7];
                a11.add(i13, 0, i7);
                AlignmentMatrix a14 = this.theMatrix;
                AlignmentNode a15 = a14.get(0, i7);
                a15.setTraceUp(true);
                int i14 = i7 + 1;
                i7 = i14;
            }
        }
        this.printF();
        this.setPosition("1.1");
        int i15 = 1;
        label0: while(true)
        {
            int i16 = this.ySize;
            if(i15 >= i16)
            {
                this.setPosition("2.2");
                this.printE();
                this.printF();
                this.printV();
                return;
            }
            this.setPosition("2.1");
            int i17 = 1;
            while(true)
            {
                int i18 = 0;
                int i19 = 0;
                int i20 = 0;
                int i21 = this.xSize;
                if(i17 >= i21)
                {
                    int i22 = i15 + 1;
                    i15 = i22;
                    continue label0;
                }
                java.io.PrintStream a16 = System.out;
                a16.println("---------------");
                java.io.PrintStream a17 = System.out;
                StringBuilder a18 = new StringBuilder();
                StringBuilder a19 = a18.append("");
                StringBuilder a20 = a19.append(i17);
                StringBuilder a21 = a20.append(", ");
                StringBuilder a22 = a21.append(i15);
                String s1 = a22.toString();
                a17.println(s1);
                int[][] a23 = this.E;
                int i23 = i17 - 1;
                int[] a24 = a23[i23];
                int i24 = a24[i15];
                java.io.PrintStream a25 = System.out;
                StringBuilder a26 = new StringBuilder();
                StringBuilder a27 = a26.append("E(i,j-1) = ");
                StringBuilder a28 = a27.append(i24);
                String s2 = a28.toString();
                a25.println(s2);
                java.io.PrintStream a29 = System.out;
                StringBuilder a30 = new StringBuilder();
                StringBuilder a31 = a30.append("V(i,j-1)-Wg = ");
                AlignmentMatrix a32 = this.theMatrix;
                int i25 = i15 - 1;
                int i26 = a32.valueAt(i17, i25);
                int i27 = this.initialGapCost;
                int i28 = i26 + i27;
                StringBuilder a33 = a31.append(i28);
                String s3 = a33.toString();
                a29.println(s3);
                AlignmentMatrix a34 = this.theMatrix;
                int i29 = i17 - 1;
                int i30 = a34.valueAt(i29, i15);
                int i31 = this.initialGapCost;
                label2: {
                    label1: {
                        int i32 = i30 + i31;
                        if(i32 > i24)
                        {
                            break label1;
                        }
                        AlignmentMatrix a35 = this.theMatrix;
                        int i33 = i17 - 1;
                        AlignmentNode a36 = a35.get(i33, i15);
                        int i34 = a36.getTraceLeft()?1:0;
                        if(i34 != 0)
                        {
                            i18 = i24;
                            break label2;
                        }
                    }
                    AlignmentMatrix a37 = this.theMatrix;
                    int i35 = i17 - 1;
                    int i36 = a37.valueAt(i35, i15);
                    int i37 = this.initialGapCost;
                    int i38 = i36 + i37;
                    i18 = i38;
                }
                int i39 = this.runningGapCost;
                int i40 = i18 + i39;
                int[][] a38 = this.E;
                int[] a39 = a38[i17];
                a39[i15] = i40;
                java.io.PrintStream a40 = System.out;
                StringBuilder a41 = new StringBuilder();
                StringBuilder a42 = a41.append("E(i,j) = ");
                StringBuilder a43 = a42.append(i40);
                String s4 = a43.toString();
                a40.println(s4);
                this.printE();
                int[][] a44 = this.F;
                int[] a45 = a44[i17];
                int i41 = i15 - 1;
                int i42 = a45[i41];
                java.io.PrintStream a46 = System.out;
                StringBuilder a47 = new StringBuilder();
                StringBuilder a48 = a47.append("F(i-1,j) = ");
                StringBuilder a49 = a48.append(i42);
                String s5 = a49.toString();
                a46.println(s5);
                java.io.PrintStream a50 = System.out;
                StringBuilder a51 = new StringBuilder();
                StringBuilder a52 = a51.append("V(i-1,j)-Wg = ");
                AlignmentMatrix a53 = this.theMatrix;
                int i43 = i17 - 1;
                int i44 = a53.valueAt(i43, i15);
                int i45 = this.initialGapCost;
                int i46 = i44 + i45;
                StringBuilder a54 = a52.append(i46);
                String s6 = a54.toString();
                a50.println(s6);
                AlignmentMatrix a55 = this.theMatrix;
                int i47 = i15 - 1;
                int i48 = a55.valueAt(i17, i47);
                int i49 = this.initialGapCost;
                label4: {
                    label3: {
                        int i50 = i48 + i49;
                        if(i50 > i42)
                        {
                            break label3;
                        }
                        AlignmentMatrix a56 = this.theMatrix;
                        int i51 = i15 - 1;
                        AlignmentNode a57 = a56.get(i17, i51);
                        int i52 = a57.getTraceUp()?1:0;
                        if(i52 != 0)
                        {
                            i19 = i42;
                            break label4;
                        }
                    }
                    AlignmentMatrix a58 = this.theMatrix;
                    int i53 = i15 - 1;
                    int i54 = a58.valueAt(i17, i53);
                    int i55 = this.initialGapCost;
                    int i56 = i54 + i55;
                    i19 = i56;
                }
                int i57 = this.runningGapCost;
                int i58 = i19 + i57;
                int[][] a59 = this.F;
                int[] a60 = a59[i17];
                a60[i15] = i58;
                java.io.PrintStream a61 = System.out;
                StringBuilder a62 = new StringBuilder();
                StringBuilder a63 = a62.append("F(i,j) = ");
                StringBuilder a64 = a63.append(i58);
                String s7 = a64.toString();
                a61.println(s7);
                this.printF();
                java.io.PrintStream a65 = System.out;
                StringBuilder a66 = new StringBuilder();
                StringBuilder a67 = a66.append("char 1 = ");
                String[] a68 = this.data;
                String s8 = a68[0];
                int i59 = i17 - 1;
                int i60 = s8.charAt(i59);
                StringBuilder a69 = a67.append((char)i60);
                StringBuilder a70 = a69.append(" char 2 = ");
                String[] a71 = this.data;
                String s9 = a71[1];
                int i61 = i15 - 1;
                int i62 = s9.charAt(i61);
                StringBuilder a72 = a70.append((char)i62);
                String s10 = a72.toString();
                a65.println(s10);
                String[] a73 = this.data;
                String s11 = a73[0];
                int i63 = i17 - 1;
                int i64 = s11.charAt(i63);
                String[] a74 = this.data;
                String s12 = a74[1];
                int i65 = i15 - 1;
                int i66 = s12.charAt(i65);
                if(i64 != i66)
                {
                    int i67 = this.mismatchCost;
                    i20 = i67;
                }
                else
                {
                    int i68 = this.matchCost;
                    i20 = i68;
                }
                AlignmentMatrix a75 = this.theMatrix;
                int i69 = i17 - 1;
                int i70 = i15 - 1;
                int i71 = a75.valueAt(i69, i70);
                int i72 = i71 + i20;
                java.io.PrintStream a76 = System.out;
                StringBuilder a77 = new StringBuilder();
                StringBuilder a78 = a77.append("G(i,j) = ");
                StringBuilder a79 = a78.append(i72);
                String s13 = a79.toString();
                a76.println(s13);
                int i73 = i58 <= i72?i72:i58;
                int i74 = i40 <= i73?i73:i40;
                java.io.PrintStream a80 = System.out;
                StringBuilder a81 = new StringBuilder();
                StringBuilder a82 = a81.append("V(i,j) = ");
                StringBuilder a83 = a82.append(i74);
                String s14 = a83.toString();
                a80.println(s14);
                AlignmentMatrix a84 = this.theMatrix;
                a84.add(i74, i17, i15);
                this.printV();
                AlignmentMatrix a85 = this.theMatrix;
                AlignmentNode a86 = a85.get(i17, i15);
                if(i74 == i72)
                {
                    a86.setTraceDiag(true);
                }
                if(i74 == i58)
                {
                    a86.setTraceLeft(true);
                }
                if(i74 == i40)
                {
                    a86.setTraceUp(true);
                }
                this.setPosition("2.1.1.1");
                int i75 = i17 + 1;
                i17 = i75;
            }
        }
    }
    
    protected void globalSimGapAlignment2()
    {
        AlignmentMatrix a = this.theMatrix;
        a.add(0, 0, 0);
        int[][] a0 = this.E;
        int[] a1 = a0[0];
        a1[0] = -99;
        int[][] a2 = this.F;
        int[] a3 = a2[0];
        a3[0] = -99;
        int i = 1;
        while(true)
        {
            int i0 = this.xSize;
            if(i >= i0)
            {
                break;
            }
            else
            {
                int[][] a4 = this.E;
                int[] a5 = a4[i];
                a5[0] = -99;
                int[][] a6 = this.F;
                int[] a7 = a6[i];
                int i1 = this.initialGapCost;
                int i2 = this.runningGapCost;
                int i3 = i2 * i;
                int i4 = i1 + i3;
                a7[0] = i4;
                AlignmentMatrix a8 = this.theMatrix;
                a8.add(-99, i, 0);
                int i5 = i + 1;
                i = i5;
            }
        }
        int i6 = 1;
        while(true)
        {
            int i7 = this.ySize;
            if(i6 >= i7)
            {
                break;
            }
            else
            {
                int[][] a9 = this.E;
                int[] a10 = a9[0];
                int i8 = this.initialGapCost;
                int i9 = this.runningGapCost;
                int i10 = i9 * i6;
                int i11 = i8 + i10;
                a10[i6] = i11;
                int[][] a11 = this.F;
                int[] a12 = a11[0];
                a12[i6] = -99;
                AlignmentMatrix a13 = this.theMatrix;
                a13.add(-99, 0, i6);
                int i12 = i6 + 1;
                i6 = i12;
            }
        }
        this.printE();
        java.io.PrintStream a14 = System.out;
        a14.println("C() = ");
        this.printF();
        this.setPosition("1.1");
        int i13 = 1;
        label0: while(true)
        {
            int i14 = this.ySize;
            if(i13 >= i14)
            {
                this.setPosition("2.2");
                this.printE();
                this.printF();
                this.printV();
                return;
            }
            this.setPosition("2.1");
            int i15 = 1;
            while(true)
            {
                int i16 = 0;
                int i17 = this.xSize;
                if(i15 >= i17)
                {
                    int i18 = i13 + 1;
                    i13 = i18;
                    continue label0;
                }
                java.io.PrintStream a15 = System.out;
                a15.println("---------------");
                java.io.PrintStream a16 = System.out;
                StringBuilder a17 = new StringBuilder();
                StringBuilder a18 = a17.append("");
                StringBuilder a19 = a18.append(i15);
                StringBuilder a20 = a19.append(", ");
                StringBuilder a21 = a20.append(i13);
                String s = a21.toString();
                a16.println(s);
                AlignmentMatrix a22 = this.theMatrix;
                int i19 = i13 - 1;
                int i20 = a22.valueAt(i15, i19);
                int i21 = this.initialGapCost;
                int i22 = i20 + i21;
                int i23 = this.runningGapCost;
                int i24 = i22 + i23;
                int[][] a23 = this.E;
                int[] a24 = a23[i15];
                int i25 = i13 - 1;
                int i26 = a24[i25];
                int i27 = this.runningGapCost;
                int i28 = i26 + i27;
                int[][] a25 = this.F;
                int[] a26 = a25[i15];
                int i29 = i13 - 1;
                int i30 = a26[i29];
                int i31 = this.initialGapCost;
                int i32 = i30 + i31;
                int i33 = this.runningGapCost;
                int i34 = i32 + i33;
                int i35 = i28 <= i24?i24:i28;
                int i36 = i34 <= i35?i35:i34;
                int[][] a27 = this.E;
                int[] a28 = a27[i15];
                a28[i13] = i36;
                if(i36 != i24)
                {
                    Object[] a29 = this.EA;
                    boolean[] a30 = (boolean[])a29[i15];
                    a30[i13] = false;
                }
                else
                {
                    Object[] a31 = this.EA;
                    boolean[] a32 = (boolean[])a31[i15];
                    a32[i13] = true;
                    java.io.PrintStream a33 = System.out;
                    String s0 = localization.Messages.getString("Alignment.146");
                    a33.println(s0);
                }
                if(i36 != i28)
                {
                    Object[] a34 = this.EB;
                    boolean[] a35 = (boolean[])a34[i15];
                    a35[i13] = false;
                }
                else
                {
                    java.io.PrintStream a36 = System.out;
                    String s1 = localization.Messages.getString("Alignment.147");
                    a36.println(s1);
                    Object[] a37 = this.EB;
                    boolean[] a38 = (boolean[])a37[i15];
                    a38[i13] = true;
                }
                if(i36 != i34)
                {
                    Object[] a39 = this.EC;
                    boolean[] a40 = (boolean[])a39[i15];
                    a40[i13] = false;
                }
                else
                {
                    java.io.PrintStream a41 = System.out;
                    String s2 = localization.Messages.getString("Alignment.148");
                    a41.println(s2);
                    Object[] a42 = this.EC;
                    boolean[] a43 = (boolean[])a42[i15];
                    a43[i13] = true;
                }
                java.io.PrintStream a44 = System.out;
                StringBuilder a45 = new StringBuilder();
                StringBuilder a46 = a45.append("B(i,j) = ");
                StringBuilder a47 = a46.append(i36);
                String s3 = a47.toString();
                a44.println(s3);
                this.printE();
                AlignmentMatrix a48 = this.theMatrix;
                int i37 = i15 - 1;
                int i38 = a48.valueAt(i37, i13);
                int i39 = this.initialGapCost;
                int i40 = i38 + i39;
                int i41 = this.runningGapCost;
                int i42 = i40 + i41;
                int[][] a49 = this.E;
                int i43 = i15 - 1;
                int[] a50 = a49[i43];
                int i44 = a50[i13];
                int i45 = this.initialGapCost;
                int i46 = i44 + i45;
                int i47 = this.runningGapCost;
                int i48 = i46 + i47;
                int[][] a51 = this.F;
                int i49 = i15 - 1;
                int[] a52 = a51[i49];
                int i50 = a52[i13];
                int i51 = this.runningGapCost;
                int i52 = i50 + i51;
                int i53 = i48 <= i42?i42:i48;
                int i54 = i52 <= i53?i53:i52;
                int[][] a53 = this.F;
                int[] a54 = a53[i15];
                a54[i13] = i54;
                if(i54 != i42)
                {
                    Object[] a55 = this.FA;
                    boolean[] a56 = (boolean[])a55[i15];
                    a56[i13] = false;
                }
                else
                {
                    java.io.PrintStream a57 = System.out;
                    String s4 = localization.Messages.getString("Alignment.150");
                    a57.println(s4);
                    Object[] a58 = this.FA;
                    boolean[] a59 = (boolean[])a58[i15];
                    a59[i13] = true;
                }
                if(i54 != i48)
                {
                    Object[] a60 = this.FB;
                    boolean[] a61 = (boolean[])a60[i15];
                    a61[i13] = false;
                }
                else
                {
                    java.io.PrintStream a62 = System.out;
                    String s5 = localization.Messages.getString("Alignment.151");
                    a62.println(s5);
                    Object[] a63 = this.FB;
                    boolean[] a64 = (boolean[])a63[i15];
                    a64[i13] = true;
                }
                if(i54 != i52)
                {
                    Object[] a65 = this.FC;
                    boolean[] a66 = (boolean[])a65[i15];
                    a66[i13] = false;
                }
                else
                {
                    java.io.PrintStream a67 = System.out;
                    String s6 = localization.Messages.getString("Alignment.279");
                    a67.println(s6);
                    Object[] a68 = this.FC;
                    boolean[] a69 = (boolean[])a68[i15];
                    a69[i13] = true;
                }
                java.io.PrintStream a70 = System.out;
                StringBuilder a71 = new StringBuilder();
                StringBuilder a72 = a71.append("C(i,j) = ");
                StringBuilder a73 = a72.append(i54);
                String s7 = a73.toString();
                a70.println(s7);
                this.printF();
                java.io.PrintStream a74 = System.out;
                StringBuilder a75 = new StringBuilder();
                StringBuilder a76 = a75.append("char 1 = ");
                String[] a77 = this.data;
                String s8 = a77[0];
                int i55 = i15 - 1;
                int i56 = s8.charAt(i55);
                StringBuilder a78 = a76.append((char)i56);
                StringBuilder a79 = a78.append(" char 2 = ");
                String[] a80 = this.data;
                String s9 = a80[1];
                int i57 = i13 - 1;
                int i58 = s9.charAt(i57);
                StringBuilder a81 = a79.append((char)i58);
                String s10 = a81.toString();
                a74.println(s10);
                String[] a82 = this.data;
                String s11 = a82[0];
                int i59 = i15 - 1;
                int i60 = s11.charAt(i59);
                String[] a83 = this.data;
                String s12 = a83[1];
                int i61 = i13 - 1;
                int i62 = s12.charAt(i61);
                if(i60 != i62)
                {
                    int i63 = this.mismatchCost;
                    i16 = i63;
                }
                else
                {
                    int i64 = this.matchCost;
                    i16 = i64;
                }
                AlignmentMatrix a84 = this.theMatrix;
                int i65 = i15 - 1;
                int i66 = i13 - 1;
                int i67 = a84.valueAt(i65, i66);
                int[][] a85 = this.E;
                int i68 = i15 - 1;
                int[] a86 = a85[i68];
                int i69 = i13 - 1;
                int i70 = a86[i69];
                int[][] a87 = this.F;
                int i71 = i15 - 1;
                int[] a88 = a87[i71];
                int i72 = i13 - 1;
                int i73 = a88[i72];
                int i74 = i70 <= i67?i67:i70;
                int i75 = i73 <= i74?i74:i73;
                if(i75 != i67)
                {
                    Object[] a89 = this.GA;
                    boolean[] a90 = (boolean[])a89[i15];
                    a90[i13] = false;
                }
                else
                {
                    java.io.PrintStream a91 = System.out;
                    String s13 = localization.Messages.getString("Alignment.156");
                    a91.println(s13);
                    Object[] a92 = this.GA;
                    boolean[] a93 = (boolean[])a92[i15];
                    a93[i13] = true;
                }
                if(i75 != i70)
                {
                    Object[] a94 = this.GB;
                    boolean[] a95 = (boolean[])a94[i15];
                    a95[i13] = false;
                }
                else
                {
                    java.io.PrintStream a96 = System.out;
                    String s14 = localization.Messages.getString("Alignment.157");
                    a96.println(s14);
                    Object[] a97 = this.GB;
                    boolean[] a98 = (boolean[])a97[i15];
                    a98[i13] = true;
                }
                if(i75 != i73)
                {
                    Object[] a99 = this.GC;
                    boolean[] a100 = (boolean[])a99[i15];
                    a100[i13] = false;
                }
                else
                {
                    java.io.PrintStream a101 = System.out;
                    String s15 = localization.Messages.getString("Alignment.158");
                    a101.println(s15);
                    Object[] a102 = this.GC;
                    boolean[] a103 = (boolean[])a102[i15];
                    a103[i13] = true;
                }
                int i76 = i75 + i16;
                java.io.PrintStream a104 = System.out;
                StringBuilder a105 = new StringBuilder();
                StringBuilder a106 = a105.append("V(i,j) = ");
                StringBuilder a107 = a106.append(i76);
                String s16 = a107.toString();
                a104.println(s16);
                AlignmentMatrix a108 = this.theMatrix;
                a108.add(i76, i15, i13);
                this.printV();
                AlignmentMatrix a109 = this.theMatrix;
                AlignmentNode a110 = a109.get(i15, i13);
                int i77 = i76 - i16;
                if(i77 == i67)
                {
                    a110.setTraceDiag(true);
                }
                int i78 = i76 - i16;
                if(i78 == i73)
                {
                    a110.setTraceLeft(true);
                }
                int i79 = i76 - i16;
                if(i79 == i70)
                {
                    a110.setTraceUp(true);
                }
                this.setPosition("2.1.1.1");
                int i80 = i15 + 1;
                i15 = i80;
            }
        }
    }
    
    protected void globalSimGapAlignment3()
    {
        AlignmentMatrix a = this.aMatrix;
        java.awt.Color a0 = Alignment.DEFAULT_NODE_COLOR;
        java.awt.Color a1 = AlignmentNode.DEFAULT_ARROW_COLOR;
        a.setAllColor(a0, a1);
        AlignmentMatrix a2 = this.bMatrix;
        java.awt.Color a3 = Alignment.DEFAULT_NODE_COLOR;
        java.awt.Color a4 = AlignmentNode.DEFAULT_ARROW_COLOR;
        a2.setAllColor(a3, a4);
        AlignmentMatrix a5 = this.cMatrix;
        java.awt.Color a6 = Alignment.DEFAULT_NODE_COLOR;
        java.awt.Color a7 = AlignmentNode.DEFAULT_ARROW_COLOR;
        a5.setAllColor(a6, a7);
        AlignmentMatrix a8 = this.aMatrix;
        a8.add(0, 0, 0);
        AlignmentMatrix a9 = this.bMatrix;
        a9.add(0, 0, 0);
        AlignmentMatrix a10 = this.cMatrix;
        a10.add(0, 0, 0);
        this.setPosition("1.1");
        int i = 1;
        while(true)
        {
            int i0 = this.xSize;
            if(i >= i0)
            {
                break;
            }
            else
            {
                AlignmentMatrix a11 = this.bMatrix;
                a11.add(-5000, i, 0);
                AlignmentMatrix a12 = this.cMatrix;
                int i1 = this.initialGapCost;
                int i2 = this.runningGapCost;
                int i3 = i2 * i;
                int i4 = i1 + i3;
                a12.add(i4, i, 0);
                AlignmentMatrix a13 = this.cMatrix;
                AlignmentNode a14 = a13.get(i, 0);
                a14.setTraceLeft(true);
                a14.setTraceC(true);
                AlignmentMatrix a15 = this.aMatrix;
                a15.add(-5000, i, 0);
                int i5 = i + 1;
                i = i5;
            }
        }
        this.setPosition("1.2");
        int i6 = 1;
        while(true)
        {
            int i7 = this.ySize;
            if(i6 >= i7)
            {
                break;
            }
            else
            {
                AlignmentMatrix a16 = this.bMatrix;
                int i8 = this.initialGapCost;
                int i9 = this.runningGapCost;
                int i10 = i9 * i6;
                int i11 = i8 + i10;
                a16.add(i11, 0, i6);
                AlignmentMatrix a17 = this.bMatrix;
                AlignmentNode a18 = a17.get(0, i6);
                a18.setTraceUp(true);
                a18.setTraceB(true);
                AlignmentMatrix a19 = this.cMatrix;
                a19.add(-5000, 0, i6);
                AlignmentMatrix a20 = this.aMatrix;
                a20.add(-5000, 0, i6);
                int i12 = i6 + 1;
                i6 = i12;
            }
        }
        this.setPosition("1.3");
        int i13 = 1;
        label0: while(true)
        {
            int i14 = this.ySize;
            if(i13 >= i14)
            {
                this.setPosition("2.2");
                return;
            }
            AlignmentMatrix a21 = this.aMatrix;
            a21.setYIndexHighlight(i13);
            AlignmentMatrix a22 = this.bMatrix;
            a22.setYIndexHighlight(i13);
            AlignmentMatrix a23 = this.cMatrix;
            a23.setYIndexHighlight(i13);
            this.setPosition("2.1");
            int i15 = 1;
            while(true)
            {
                int i16 = 0;
                int i17 = this.xSize;
                if(i15 >= i17)
                {
                    this.setPosition("2.1.2");
                    int i18 = i13 + 1;
                    i13 = i18;
                    continue label0;
                }
                AlignmentMatrix a24 = this.aMatrix;
                a24.setXIndexHighlight(i15);
                AlignmentMatrix a25 = this.bMatrix;
                a25.setXIndexHighlight(i15);
                AlignmentMatrix a26 = this.cMatrix;
                a26.setXIndexHighlight(i15);
                this.setPosition("2.1.1");
                String s = localization.Messages.getString("Alignment.281");
                AlignmentMinimum a27 = new AlignmentMinimum(s);
                this.theMinimum = a27;
                AlignmentMinimum a28 = this.theMinimum;
                a28.add();
                AlignmentMinimum a29 = this.theMinimum;
                a29.add();
                AlignmentMinimum a30 = this.theMinimum;
                a30.add();
                AlignmentMatrix a31 = this.aMatrix;
                int i19 = i13 - 1;
                AlignmentNode a32 = a31.get(i15, i19);
                a32.highlight();
                AlignmentMinimum a33 = this.theMinimum;
                AlignmentMatrix a34 = this.aMatrix;
                int i20 = i13 - 1;
                int i21 = a34.valueAt(i15, i20);
                int i22 = this.initialGapCost;
                int i23 = this.runningGapCost;
                int i24 = i22 + i23;
                a33.set(0, i21, i24);
                this.setPosition("2.1.1.1.2");
                a32.unHighlight();
                AlignmentMatrix a35 = this.bMatrix;
                int i25 = i13 - 1;
                AlignmentNode a36 = a35.get(i15, i25);
                a36.highlight();
                AlignmentMinimum a37 = this.theMinimum;
                AlignmentMatrix a38 = this.bMatrix;
                int i26 = i13 - 1;
                int i27 = a38.valueAt(i15, i26);
                int i28 = this.runningGapCost;
                a37.set(1, i27, i28);
                this.setPosition("2.1.1.1.4");
                a36.unHighlight();
                AlignmentMatrix a39 = this.cMatrix;
                int i29 = i13 - 1;
                AlignmentNode a40 = a39.get(i15, i29);
                a40.highlight();
                AlignmentMinimum a41 = this.theMinimum;
                AlignmentMatrix a42 = this.cMatrix;
                int i30 = i13 - 1;
                int i31 = a42.valueAt(i15, i30);
                int i32 = this.initialGapCost;
                int i33 = this.runningGapCost;
                int i34 = i32 + i33;
                a41.set(2, i31, i34);
                this.setPosition("2.1.1.1.6");
                a40.unHighlight();
                AlignmentMatrix a43 = this.aMatrix;
                int i35 = i13 - 1;
                int i36 = a43.valueAt(i15, i35);
                int i37 = this.initialGapCost;
                int i38 = i36 + i37;
                int i39 = this.runningGapCost;
                int i40 = i38 + i39;
                AlignmentMatrix a44 = this.bMatrix;
                int i41 = i13 - 1;
                int i42 = a44.valueAt(i15, i41);
                int i43 = this.runningGapCost;
                int i44 = i42 + i43;
                AlignmentMatrix a45 = this.cMatrix;
                int i45 = i13 - 1;
                int i46 = a45.valueAt(i15, i45);
                int i47 = this.initialGapCost;
                int i48 = i46 + i47;
                int i49 = this.runningGapCost;
                int i50 = i48 + i49;
                int i51 = Math.max(i44, i50);
                int i52 = Math.max(i40, i51);
                AlignmentMatrix a46 = this.bMatrix;
                a46.add(i52, i15, i13);
                AlignmentMatrix a47 = this.bMatrix;
                AlignmentNode a48 = a47.get(i15, i13);
                a48.setTraceUp(true);
                if(i52 != i40)
                {
                    AlignmentMatrix a49 = this.aMatrix;
                    int i53 = i13 - 1;
                    AlignmentNode a50 = a49.get(i15, i53);
                    a50.setIsDisabled(true);
                    a48.setTraceA(false);
                }
                else
                {
                    a48.highlightA();
                    AlignmentMatrix a51 = this.aMatrix;
                    int i54 = i13 - 1;
                    AlignmentNode a52 = a51.get(i15, i54);
                    a52.highlight();
                    AlignmentMinimum a53 = this.theMinimum;
                    a53.highlight(0);
                    a48.setTraceA(true);
                }
                if(i52 != i44)
                {
                    AlignmentMatrix a54 = this.bMatrix;
                    int i55 = i13 - 1;
                    AlignmentNode a55 = a54.get(i15, i55);
                    a55.setIsDisabled(true);
                    a48.setTraceB(false);
                }
                else
                {
                    a48.highlightB();
                    AlignmentMatrix a56 = this.bMatrix;
                    int i56 = i13 - 1;
                    AlignmentNode a57 = a56.get(i15, i56);
                    a57.highlight();
                    AlignmentMinimum a58 = this.theMinimum;
                    a58.highlight(1);
                    a48.setTraceB(true);
                }
                if(i52 != i50)
                {
                    AlignmentMatrix a59 = this.cMatrix;
                    int i57 = i13 - 1;
                    AlignmentNode a60 = a59.get(i15, i57);
                    a60.setIsDisabled(true);
                    a48.setTraceC(false);
                }
                else
                {
                    a48.highlightC();
                    AlignmentMatrix a61 = this.cMatrix;
                    int i58 = i13 - 1;
                    AlignmentNode a62 = a61.get(i15, i58);
                    a62.highlight();
                    AlignmentMinimum a63 = this.theMinimum;
                    a63.highlight(2);
                    a48.setTraceC(true);
                }
                a48.highlight();
                java.awt.Color a64 = Alignment.HIGHLIGHT_RING_COLOR;
                a48.setRingColor(a64);
                this.setPosition("2.1.1.1");
                a48.unHighlightA();
                a48.unHighlightB();
                a48.unHighlightC();
                java.awt.Color a65 = Alignment.DEFAULT_RING_COLOR;
                a48.setRingColor(a65);
                a48.unHighlight();
                AlignmentMatrix a66 = this.aMatrix;
                int i59 = i13 - 1;
                AlignmentNode a67 = a66.get(i15, i59);
                a67.setIsDisabled(false);
                AlignmentMatrix a68 = this.aMatrix;
                int i60 = i13 - 1;
                AlignmentNode a69 = a68.get(i15, i60);
                a69.unHighlight();
                AlignmentMatrix a70 = this.bMatrix;
                int i61 = i13 - 1;
                AlignmentNode a71 = a70.get(i15, i61);
                a71.setIsDisabled(false);
                AlignmentMatrix a72 = this.bMatrix;
                int i62 = i13 - 1;
                AlignmentNode a73 = a72.get(i15, i62);
                a73.unHighlight();
                AlignmentMatrix a74 = this.cMatrix;
                int i63 = i13 - 1;
                AlignmentNode a75 = a74.get(i15, i63);
                a75.setIsDisabled(false);
                AlignmentMatrix a76 = this.cMatrix;
                int i64 = i13 - 1;
                AlignmentNode a77 = a76.get(i15, i64);
                a77.unHighlight();
                this.theMinimum = null;
                String s0 = localization.Messages.getString("Alignment.280");
                AlignmentMinimum a78 = new AlignmentMinimum(s0);
                this.theMinimum = a78;
                AlignmentMinimum a79 = this.theMinimum;
                a79.add();
                AlignmentMinimum a80 = this.theMinimum;
                a80.add();
                AlignmentMinimum a81 = this.theMinimum;
                a81.add();
                AlignmentMatrix a82 = this.aMatrix;
                int i65 = i15 - 1;
                AlignmentNode a83 = a82.get(i65, i13);
                a83.highlight();
                AlignmentMinimum a84 = this.theMinimum;
                AlignmentMatrix a85 = this.aMatrix;
                int i66 = i15 - 1;
                int i67 = a85.valueAt(i66, i13);
                int i68 = this.initialGapCost;
                int i69 = this.runningGapCost;
                int i70 = i68 + i69;
                a84.set(0, i67, i70);
                this.setPosition("2.1.1.2.2");
                a83.unHighlight();
                AlignmentMatrix a86 = this.bMatrix;
                int i71 = i15 - 1;
                AlignmentNode a87 = a86.get(i71, i13);
                a87.highlight();
                AlignmentMinimum a88 = this.theMinimum;
                AlignmentMatrix a89 = this.bMatrix;
                int i72 = i15 - 1;
                int i73 = a89.valueAt(i72, i13);
                int i74 = this.initialGapCost;
                int i75 = this.runningGapCost;
                int i76 = i74 + i75;
                a88.set(1, i73, i76);
                this.setPosition("2.1.1.2.4");
                a87.unHighlight();
                AlignmentMatrix a90 = this.cMatrix;
                int i77 = i15 - 1;
                AlignmentNode a91 = a90.get(i77, i13);
                a91.highlight();
                AlignmentMinimum a92 = this.theMinimum;
                AlignmentMatrix a93 = this.cMatrix;
                int i78 = i15 - 1;
                int i79 = a93.valueAt(i78, i13);
                int i80 = this.runningGapCost;
                a92.set(2, i79, i80);
                this.setPosition("2.1.1.2.6");
                a91.unHighlight();
                AlignmentMatrix a94 = this.aMatrix;
                int i81 = i15 - 1;
                int i82 = a94.valueAt(i81, i13);
                int i83 = this.initialGapCost;
                int i84 = i82 + i83;
                int i85 = this.runningGapCost;
                int i86 = i84 + i85;
                AlignmentMatrix a95 = this.bMatrix;
                int i87 = i15 - 1;
                int i88 = a95.valueAt(i87, i13);
                int i89 = this.initialGapCost;
                int i90 = i88 + i89;
                int i91 = this.runningGapCost;
                int i92 = i90 + i91;
                AlignmentMatrix a96 = this.cMatrix;
                int i93 = i15 - 1;
                int i94 = a96.valueAt(i93, i13);
                int i95 = this.runningGapCost;
                int i96 = i94 + i95;
                int i97 = Math.max(i92, i96);
                int i98 = Math.max(i86, i97);
                AlignmentMatrix a97 = this.cMatrix;
                a97.add(i98, i15, i13);
                AlignmentMatrix a98 = this.cMatrix;
                AlignmentNode a99 = a98.get(i15, i13);
                a99.setTraceLeft(true);
                if(i98 != i86)
                {
                    AlignmentMatrix a100 = this.aMatrix;
                    int i99 = i15 - 1;
                    AlignmentNode a101 = a100.get(i99, i13);
                    a101.setIsDisabled(true);
                    a99.setTraceA(false);
                }
                else
                {
                    a99.highlightA();
                    AlignmentMatrix a102 = this.aMatrix;
                    int i100 = i15 - 1;
                    AlignmentNode a103 = a102.get(i100, i13);
                    a103.highlight();
                    AlignmentMinimum a104 = this.theMinimum;
                    a104.highlight(0);
                    a99.setTraceA(true);
                }
                if(i98 != i92)
                {
                    AlignmentMatrix a105 = this.bMatrix;
                    int i101 = i15 - 1;
                    AlignmentNode a106 = a105.get(i101, i13);
                    a106.setIsDisabled(true);
                    a99.setTraceB(false);
                }
                else
                {
                    a99.highlightB();
                    AlignmentMatrix a107 = this.bMatrix;
                    int i102 = i15 - 1;
                    AlignmentNode a108 = a107.get(i102, i13);
                    a108.highlight();
                    AlignmentMinimum a109 = this.theMinimum;
                    a109.highlight(1);
                    a99.setTraceB(true);
                }
                if(i98 != i96)
                {
                    AlignmentMatrix a110 = this.cMatrix;
                    int i103 = i15 - 1;
                    AlignmentNode a111 = a110.get(i103, i13);
                    a111.setIsDisabled(true);
                    a99.setTraceC(false);
                }
                else
                {
                    a99.highlightC();
                    AlignmentMatrix a112 = this.cMatrix;
                    int i104 = i15 - 1;
                    AlignmentNode a113 = a112.get(i104, i13);
                    a113.highlight();
                    AlignmentMinimum a114 = this.theMinimum;
                    a114.highlight(2);
                    a99.setTraceC(true);
                }
                a99.highlight();
                java.awt.Color a115 = Alignment.HIGHLIGHT_RING_COLOR;
                a99.setRingColor(a115);
                this.setPosition("2.1.1.2");
                a99.unHighlightA();
                a99.unHighlightB();
                a99.unHighlightC();
                java.awt.Color a116 = Alignment.DEFAULT_RING_COLOR;
                a99.setRingColor(a116);
                a99.unHighlight();
                AlignmentMatrix a117 = this.aMatrix;
                int i105 = i15 - 1;
                AlignmentNode a118 = a117.get(i105, i13);
                a118.setIsDisabled(false);
                AlignmentMatrix a119 = this.aMatrix;
                int i106 = i15 - 1;
                AlignmentNode a120 = a119.get(i106, i13);
                a120.unHighlight();
                AlignmentMatrix a121 = this.bMatrix;
                int i107 = i15 - 1;
                AlignmentNode a122 = a121.get(i107, i13);
                a122.setIsDisabled(false);
                AlignmentMatrix a123 = this.bMatrix;
                int i108 = i15 - 1;
                AlignmentNode a124 = a123.get(i108, i13);
                a124.unHighlight();
                AlignmentMatrix a125 = this.cMatrix;
                int i109 = i15 - 1;
                AlignmentNode a126 = a125.get(i109, i13);
                a126.setIsDisabled(false);
                AlignmentMatrix a127 = this.cMatrix;
                int i110 = i15 - 1;
                AlignmentNode a128 = a127.get(i110, i13);
                a128.unHighlight();
                this.theMinimum = null;
                String[] a129 = this.data;
                String s1 = a129[0];
                int i111 = i15 - 1;
                int i112 = s1.charAt(i111);
                String[] a130 = this.data;
                String s2 = a130[1];
                int i113 = i13 - 1;
                int i114 = s2.charAt(i113);
                if(i112 != i114)
                {
                    int i115 = this.mismatchCost;
                    i16 = i115;
                }
                else
                {
                    int i116 = this.matchCost;
                    i16 = i116;
                }
                AlignmentMinimum a131 = new AlignmentMinimum("Maximum");
                this.theMinimum = a131;
                AlignmentMinimum a132 = this.theMinimum;
                a132.add();
                AlignmentMinimum a133 = this.theMinimum;
                a133.add();
                AlignmentMinimum a134 = this.theMinimum;
                a134.add();
                AlignmentMatrix a135 = this.aMatrix;
                int i117 = i15 - 1;
                int i118 = i13 - 1;
                AlignmentNode a136 = a135.get(i117, i118);
                a136.highlight();
                AlignmentMinimum a137 = this.theMinimum;
                AlignmentMatrix a138 = this.aMatrix;
                int i119 = i15 - 1;
                int i120 = i13 - 1;
                int i121 = a138.valueAt(i119, i120);
                a137.set(0, i121, i16);
                this.setPosition("2.1.1.3.1");
                a136.unHighlight();
                AlignmentMatrix a139 = this.bMatrix;
                int i122 = i15 - 1;
                int i123 = i13 - 1;
                AlignmentNode a140 = a139.get(i122, i123);
                a140.highlight();
                AlignmentMinimum a141 = this.theMinimum;
                AlignmentMatrix a142 = this.bMatrix;
                int i124 = i15 - 1;
                int i125 = i13 - 1;
                int i126 = a142.valueAt(i124, i125);
                a141.set(1, i126, i16);
                this.setPosition("2.1.1.3.2");
                a140.unHighlight();
                AlignmentMatrix a143 = this.cMatrix;
                int i127 = i15 - 1;
                int i128 = i13 - 1;
                AlignmentNode a144 = a143.get(i127, i128);
                a144.highlight();
                AlignmentMinimum a145 = this.theMinimum;
                AlignmentMatrix a146 = this.cMatrix;
                int i129 = i15 - 1;
                int i130 = i13 - 1;
                int i131 = a146.valueAt(i129, i130);
                a145.set(2, i131, i16);
                this.setPosition("2.1.1.3.3");
                a144.unHighlight();
                AlignmentMatrix a147 = this.aMatrix;
                int i132 = i15 - 1;
                int i133 = i13 - 1;
                int i134 = a147.valueAt(i132, i133);
                AlignmentMatrix a148 = this.bMatrix;
                int i135 = i15 - 1;
                int i136 = i13 - 1;
                int i137 = a148.valueAt(i135, i136);
                AlignmentMatrix a149 = this.cMatrix;
                int i138 = i15 - 1;
                int i139 = i13 - 1;
                int i140 = a149.valueAt(i138, i139);
                int i141 = Math.max(i137, i140);
                int i142 = Math.max(i134, i141);
                AlignmentMatrix a150 = this.aMatrix;
                int i143 = i142 + i16;
                a150.add(i143, i15, i13);
                AlignmentMatrix a151 = this.aMatrix;
                AlignmentNode a152 = a151.get(i15, i13);
                a152.setTraceDiag(true);
                if(i142 != i134)
                {
                    AlignmentMatrix a153 = this.aMatrix;
                    int i144 = i15 - 1;
                    int i145 = i13 - 1;
                    AlignmentNode a154 = a153.get(i144, i145);
                    a154.setIsDisabled(true);
                    a152.setTraceA(false);
                }
                else
                {
                    a152.highlightA();
                    AlignmentMatrix a155 = this.aMatrix;
                    int i146 = i15 - 1;
                    int i147 = i13 - 1;
                    AlignmentNode a156 = a155.get(i146, i147);
                    a156.highlight();
                    AlignmentMinimum a157 = this.theMinimum;
                    a157.highlight(0);
                    a152.setTraceA(true);
                }
                if(i142 != i137)
                {
                    AlignmentMatrix a158 = this.bMatrix;
                    int i148 = i15 - 1;
                    int i149 = i13 - 1;
                    AlignmentNode a159 = a158.get(i148, i149);
                    a159.setIsDisabled(true);
                    a152.setTraceB(false);
                }
                else
                {
                    a152.highlightB();
                    AlignmentMatrix a160 = this.bMatrix;
                    int i150 = i15 - 1;
                    int i151 = i13 - 1;
                    AlignmentNode a161 = a160.get(i150, i151);
                    a161.highlight();
                    AlignmentMinimum a162 = this.theMinimum;
                    a162.highlight(1);
                    a152.setTraceB(true);
                }
                if(i142 != i140)
                {
                    AlignmentMatrix a163 = this.cMatrix;
                    int i152 = i15 - 1;
                    int i153 = i13 - 1;
                    AlignmentNode a164 = a163.get(i152, i153);
                    a164.setIsDisabled(true);
                    a152.setTraceC(false);
                }
                else
                {
                    a152.highlightC();
                    AlignmentMatrix a165 = this.cMatrix;
                    int i154 = i15 - 1;
                    int i155 = i13 - 1;
                    AlignmentNode a166 = a165.get(i154, i155);
                    a166.highlight();
                    AlignmentMinimum a167 = this.theMinimum;
                    a167.highlight(2);
                    a152.setTraceC(true);
                }
                a152.highlight();
                java.awt.Color a168 = Alignment.HIGHLIGHT_RING_COLOR;
                a152.setRingColor(a168);
                this.setPosition("2.1.1.3");
                a152.unHighlightA();
                a152.unHighlightB();
                a152.unHighlightC();
                java.awt.Color a169 = Alignment.DEFAULT_RING_COLOR;
                a152.setRingColor(a169);
                a152.unHighlight();
                AlignmentMatrix a170 = this.aMatrix;
                int i156 = i15 - 1;
                int i157 = i13 - 1;
                AlignmentNode a171 = a170.get(i156, i157);
                a171.setIsDisabled(false);
                AlignmentMatrix a172 = this.aMatrix;
                int i158 = i15 - 1;
                int i159 = i13 - 1;
                AlignmentNode a173 = a172.get(i158, i159);
                a173.unHighlight();
                AlignmentMatrix a174 = this.bMatrix;
                int i160 = i15 - 1;
                int i161 = i13 - 1;
                AlignmentNode a175 = a174.get(i160, i161);
                a175.setIsDisabled(false);
                AlignmentMatrix a176 = this.bMatrix;
                int i162 = i15 - 1;
                int i163 = i13 - 1;
                AlignmentNode a177 = a176.get(i162, i163);
                a177.unHighlight();
                AlignmentMatrix a178 = this.cMatrix;
                int i164 = i15 - 1;
                int i165 = i13 - 1;
                AlignmentNode a179 = a178.get(i164, i165);
                a179.setIsDisabled(false);
                AlignmentMatrix a180 = this.cMatrix;
                int i166 = i15 - 1;
                int i167 = i13 - 1;
                AlignmentNode a181 = a180.get(i166, i167);
                a181.unHighlight();
                this.theMinimum = null;
                int i168 = i15 + 1;
                i15 = i168;
            }
        }
    }
    
    protected void globalSimNoGapAlignment()
    {
        int i = 0;
        while(true)
        {
            int i0 = this.xSize;
            if(i >= i0)
            {
                break;
            }
            AlignmentMatrix a = this.theMatrix;
            a.setXIndexHighlight(i);
            AlignmentMatrix a0 = this.theMatrix;
            a0.setYIndexHighlight(0);
            this.setPosition("1.1.1");
            AlignmentMatrix a1 = this.theMatrix;
            int i1 = this.spaceCost;
            int i2 = i * i1;
            a1.add(i2, i, 0);
            if(i != 0)
            {
                AlignmentMatrix a2 = this.theMatrix;
                AlignmentNode a3 = a2.get(i, 0);
                a3.setTraceLeft(true);
            }
            this.setPosition("1.1.2");
            int i3 = i + 1;
            i = i3;
        }
        AlignmentMatrix a4 = this.theMatrix;
        a4.setXIndexHighlight(-1);
        AlignmentMatrix a5 = this.theMatrix;
        a5.setYIndexHighlight(-1);
        this.setPosition("1.1.1");
        this.setPosition("1.1.4");
        int i4 = 0;
        while(true)
        {
            int i5 = this.ySize;
            if(i4 >= i5)
            {
                break;
            }
            AlignmentMatrix a6 = this.theMatrix;
            a6.setXIndexHighlight(0);
            AlignmentMatrix a7 = this.theMatrix;
            a7.setYIndexHighlight(i4);
            this.setPosition("1.1.5");
            AlignmentMatrix a8 = this.theMatrix;
            int i6 = this.spaceCost;
            int i7 = i4 * i6;
            a8.add(i7, 0, i4);
            if(i4 != 0)
            {
                AlignmentMatrix a9 = this.theMatrix;
                AlignmentNode a10 = a9.get(0, i4);
                a10.setTraceUp(true);
            }
            this.setPosition("1.1.6");
            int i8 = i4 + 1;
            i4 = i8;
        }
        AlignmentMatrix a11 = this.theMatrix;
        a11.setXIndexHighlight(-1);
        AlignmentMatrix a12 = this.theMatrix;
        a12.setYIndexHighlight(-1);
        this.setPosition("1.1.5");
        this.setPosition("1.1.8");
        int i9 = 1;
        label0: while(true)
        {
            int i10 = this.ySize;
            if(i9 >= i10)
            {
                this.setPosition("2.1");
                AlignmentMatrix a13 = this.theMatrix;
                a13.setXIndexHighlight(-1);
                AlignmentMatrix a14 = this.theMatrix;
                a14.setYIndexHighlight(-1);
                this.setPosition("2.2");
                return;
            }
            AlignmentMatrix a15 = this.theMatrix;
            a15.setYIndexHighlight(i9);
            this.setPosition("2.1");
            int i11 = 1;
            while(true)
            {
                int i12 = 0;
                int i13 = 0;
                int i14 = 0;
                int i15 = this.xSize;
                if(i11 >= i15)
                {
                    this.setPosition("2.1.1");
                    int i16 = i9 + 1;
                    i9 = i16;
                    continue label0;
                }
                AlignmentMatrix a16 = this.theMatrix;
                a16.setXIndexHighlight(i11);
                this.setPosition("2.1.1");
                String s = localization.Messages.getString("Alignment.110");
                AlignmentMinimum a17 = new AlignmentMinimum(s);
                this.theMinimum = a17;
                AlignmentMinimum a18 = this.theMinimum;
                a18.add();
                AlignmentMinimum a19 = this.theMinimum;
                a19.add();
                AlignmentMinimum a20 = this.theMinimum;
                a20.add();
                String[] a21 = this.data;
                String s0 = a21[0];
                int i17 = i11 - 1;
                int i18 = s0.charAt(i17);
                String[] a22 = this.data;
                String s1 = a22[1];
                int i19 = i9 - 1;
                int i20 = s1.charAt(i19);
                if(i18 != i20)
                {
                    int i21 = this.mismatchCost;
                    i12 = i21;
                }
                else
                {
                    int i22 = this.matchCost;
                    i12 = i22;
                }
                AlignmentMatrix a23 = this.theMatrix;
                int i23 = i11 - 1;
                AlignmentNode a24 = a23.get(i23, i9);
                a24.highlight();
                AlignmentMinimum a25 = this.theMinimum;
                AlignmentMatrix a26 = this.theMatrix;
                int i24 = i11 - 1;
                int i25 = a26.valueAt(i24, i9);
                int i26 = this.spaceCost;
                a25.set(0, i25, i26);
                this.setPosition("2.1.1.3");
                AlignmentMatrix a27 = this.theMatrix;
                int i27 = i9 - 1;
                AlignmentNode a28 = a27.get(i11, i27);
                a28.highlight();
                AlignmentMinimum a29 = this.theMinimum;
                AlignmentMatrix a30 = this.theMatrix;
                int i28 = i9 - 1;
                int i29 = a30.valueAt(i11, i28);
                int i30 = this.spaceCost;
                a29.set(1, i29, i30);
                this.setPosition("2.1.1.4");
                AlignmentMatrix a31 = this.theMatrix;
                int i31 = i11 - 1;
                int i32 = i9 - 1;
                AlignmentNode a32 = a31.get(i31, i32);
                a32.highlight();
                AlignmentMinimum a33 = this.theMinimum;
                AlignmentMatrix a34 = this.theMatrix;
                int i33 = i11 - 1;
                int i34 = i9 - 1;
                int i35 = a34.valueAt(i33, i34);
                a33.set(2, i35, i12);
                this.setPosition("2.1.1.5");
                AlignmentMatrix a35 = this.theMatrix;
                int i36 = i11 - 1;
                int i37 = i9 - 1;
                int i38 = a35.valueAt(i36, i37);
                int i39 = i38 + i12;
                AlignmentMatrix a36 = this.theMatrix;
                int i40 = i11 - 1;
                int i41 = a36.valueAt(i40, i9);
                int i42 = this.spaceCost;
                int i43 = i41 + i42;
                if(i43 <= i39)
                {
                    i13 = i39;
                }
                else
                {
                    AlignmentMatrix a37 = this.theMatrix;
                    int i44 = i11 - 1;
                    int i45 = a37.valueAt(i44, i9);
                    int i46 = this.spaceCost;
                    int i47 = i45 + i46;
                    i13 = i47;
                }
                AlignmentMatrix a38 = this.theMatrix;
                int i48 = i9 - 1;
                int i49 = a38.valueAt(i11, i48);
                int i50 = this.spaceCost;
                int i51 = i49 + i50;
                if(i51 <= i13)
                {
                    i14 = i13;
                }
                else
                {
                    AlignmentMatrix a39 = this.theMatrix;
                    int i52 = i9 - 1;
                    int i53 = a39.valueAt(i11, i52);
                    int i54 = this.spaceCost;
                    int i55 = i53 + i54;
                    i14 = i55;
                }
                AlignmentMatrix a40 = this.theMatrix;
                a40.add(i14, i11, i9);
                AlignmentMatrix a41 = this.theMatrix;
                AlignmentNode a42 = a41.get(i11, i9);
                AlignmentMatrix a43 = this.theMatrix;
                a43.unHighlight();
                AlignmentMatrix a44 = this.theMatrix;
                int i56 = i11 - 1;
                int i57 = i9 - 1;
                int i58 = a44.valueAt(i56, i57);
                int i59 = i58 + i12;
                if(i14 == i59)
                {
                    a42.setTraceDiag(true);
                    AlignmentMatrix a45 = this.theMatrix;
                    int i60 = i11 - 1;
                    int i61 = i9 - 1;
                    AlignmentNode a46 = a45.get(i60, i61);
                    a46.highlight();
                    AlignmentMinimum a47 = this.theMinimum;
                    a47.highlight(2);
                }
                AlignmentMatrix a48 = this.theMatrix;
                int i62 = i11 - 1;
                int i63 = a48.valueAt(i62, i9);
                int i64 = this.spaceCost;
                int i65 = i63 + i64;
                if(i14 == i65)
                {
                    a42.setTraceLeft(true);
                    AlignmentMatrix a49 = this.theMatrix;
                    int i66 = i11 - 1;
                    AlignmentNode a50 = a49.get(i66, i9);
                    a50.highlight();
                    AlignmentMinimum a51 = this.theMinimum;
                    a51.highlight(0);
                }
                AlignmentMatrix a52 = this.theMatrix;
                int i67 = i9 - 1;
                int i68 = a52.valueAt(i11, i67);
                int i69 = this.spaceCost;
                int i70 = i68 + i69;
                if(i14 == i70)
                {
                    a42.setTraceUp(true);
                    AlignmentMatrix a53 = this.theMatrix;
                    int i71 = i9 - 1;
                    AlignmentNode a54 = a53.get(i11, i71);
                    a54.highlight();
                    AlignmentMinimum a55 = this.theMinimum;
                    a55.highlight(1);
                }
                this.setPosition("2.1.1.2");
                AlignmentMatrix a56 = this.theMatrix;
                a56.unHighlight();
                AlignmentMinimum a57 = this.theMinimum;
                a57.unHighlight();
                this.theMinimum = null;
                int i72 = i11 + 1;
                i11 = i72;
            }
        }
    }
    
    protected boolean hasQuestions()
    {
        return false;
    }
    
    protected void initialise()
    {
        String[] a = this.data;
        String s = a[0];
        String[] a0 = this.data;
        String[] a1 = this.data;
        String s0 = a1[1];
        a0[0] = s0;
        String[] a2 = this.data;
        a2[1] = s;
        String[] a3 = this.data;
        String s1 = a3[0];
        int i = s1.length();
        int i0 = i + 1;
        String[] a4 = this.data;
        String s2 = a4[1];
        int i1 = s2.length();
        int i2 = i1 + 1;
        String[] a5 = this.data;
        String s3 = a5[0];
        String[] a6 = this.data;
        String s4 = a6[1];
        AlignmentMatrix a7 = new AlignmentMatrix(i0, i2, s3, s4);
        this.theMatrix = a7;
        String[] a8 = this.data;
        String s5 = a8[0];
        int i3 = s5.length();
        int i4 = i3 + 1;
        String[] a9 = this.data;
        String s6 = a9[1];
        int i5 = s6.length();
        int i6 = i5 + 1;
        String[] a10 = this.data;
        String s7 = a10[0];
        String[] a11 = this.data;
        String s8 = a11[1];
        AlignmentMatrix a12 = new AlignmentMatrix(i4, i6, s7, s8);
        this.aMatrix = a12;
        String[] a13 = this.data;
        String s9 = a13[0];
        int i7 = s9.length();
        int i8 = i7 + 1;
        String[] a14 = this.data;
        String s10 = a14[1];
        int i9 = s10.length();
        int i10 = i9 + 1;
        String[] a15 = this.data;
        String s11 = a15[0];
        String[] a16 = this.data;
        String s12 = a16[1];
        AlignmentMatrix a17 = new AlignmentMatrix(i8, i10, s11, s12);
        this.bMatrix = a17;
        String[] a18 = this.data;
        String s13 = a18[0];
        int i11 = s13.length();
        int i12 = i11 + 1;
        String[] a19 = this.data;
        String s14 = a19[1];
        int i13 = s14.length();
        int i14 = i13 + 1;
        String[] a20 = this.data;
        String s15 = a20[0];
        String[] a21 = this.data;
        String s16 = a21[1];
        AlignmentMatrix a22 = new AlignmentMatrix(i12, i14, s15, s16);
        this.cMatrix = a22;
        AlignmentMatrix a23 = this.aMatrix;
        a23.setIsGapMode(true);
        AlignmentMatrix a24 = this.aMatrix;
        a24.setMatrixName("A");
        AlignmentMatrix a25 = this.aMatrix;
        a25.setDrawName(true);
        AlignmentMatrix a26 = this.aMatrix;
        a26.setInternalBoxSize(2, 2);
        AlignmentMatrix a27 = this.bMatrix;
        a27.setIsGapMode(true);
        AlignmentMatrix a28 = this.bMatrix;
        a28.setMatrixName("B");
        AlignmentMatrix a29 = this.bMatrix;
        a29.setDrawName(true);
        AlignmentMatrix a30 = this.bMatrix;
        a30.setInternalBoxSize(2, 2);
        AlignmentMatrix a31 = this.cMatrix;
        a31.setIsGapMode(true);
        AlignmentMatrix a32 = this.cMatrix;
        a32.setMatrixName("C");
        AlignmentMatrix a33 = this.cMatrix;
        a33.setDrawName(true);
        AlignmentMatrix a34 = this.cMatrix;
        a34.setInternalBoxSize(2, 2);
        AlignmentMatrix a35 = this.theMatrix;
        int i15 = a35.getXSize();
        this.xSize = i15;
        AlignmentMatrix a36 = this.theMatrix;
        int i16 = a36.getYSize();
        this.ySize = i16;
        int i17 = this.xSize;
        int i18 = this.ySize;
        int[][] a37 = new int[i17][i18];
        this.E = a37;
        int i19 = this.xSize;
        int i20 = this.ySize;
        int[][] a38 = new int[i19][i20];
        this.F = a38;
        this.string1EA = null;
        this.string2EA = null;
        this.theMinimum = null;
        this.theAlignmentKey = null;
        AlignmentMatrix3D a39 = this.the3D;
        a39.initialiseColor();
        AlignmentMatrix3D a40 = this.the3D;
        a40.setVisible(false);
    }
    
    protected void localSimGapAlignment()
    {
        AlignmentMatrix a = this.aMatrix;
        a.add(0, 0, 0);
        AlignmentMatrix a0 = this.bMatrix;
        a0.add(0, 0, 0);
        AlignmentMatrix a1 = this.cMatrix;
        a1.add(0, 0, 0);
        int i = 1;
        while(true)
        {
            int i0 = this.xSize;
            if(i >= i0)
            {
                break;
            }
            else
            {
                AlignmentMatrix a2 = this.cMatrix;
                a2.add(0, i, 0);
                AlignmentMatrix a3 = this.aMatrix;
                a3.add(0, i, 0);
                AlignmentMatrix a4 = this.bMatrix;
                a4.add(0, i, 0);
                int i1 = i + 1;
                i = i1;
            }
        }
        int i2 = 1;
        while(true)
        {
            int i3 = this.ySize;
            if(i2 >= i3)
            {
                break;
            }
            else
            {
                AlignmentMatrix a5 = this.bMatrix;
                a5.add(0, 0, i2);
                AlignmentMatrix a6 = this.cMatrix;
                a6.add(0, 0, i2);
                AlignmentMatrix a7 = this.aMatrix;
                a7.add(0, 0, i2);
                int i4 = i2 + 1;
                i2 = i4;
            }
        }
        this.setPosition("1.1");
        int i5 = 1;
        label0: while(true)
        {
            int i6 = this.ySize;
            if(i5 >= i6)
            {
                this.setPosition("2.2");
                return;
            }
            this.setPosition("2.1");
            int i7 = 1;
            while(true)
            {
                int i8 = 0;
                int i9 = this.xSize;
                if(i7 >= i9)
                {
                    int i10 = i5 + 1;
                    i5 = i10;
                    continue label0;
                }
                AlignmentMatrix a8 = this.aMatrix;
                int i11 = i5 - 1;
                int i12 = a8.valueAt(i7, i11);
                int i13 = this.initialGapCost;
                int i14 = i12 + i13;
                int i15 = this.runningGapCost;
                int i16 = i14 + i15;
                AlignmentMatrix a9 = this.bMatrix;
                int i17 = i5 - 1;
                int i18 = a9.valueAt(i7, i17);
                int i19 = this.runningGapCost;
                int i20 = i18 + i19;
                AlignmentMatrix a10 = this.cMatrix;
                int i21 = i5 - 1;
                int i22 = a10.valueAt(i7, i21);
                int i23 = this.initialGapCost;
                int i24 = i22 + i23;
                int i25 = this.runningGapCost;
                int i26 = i24 + i25;
                int i27 = i20 <= i16?i16:i20;
                int i28 = i26 <= i27?i27:i26;
                AlignmentMatrix a11 = this.bMatrix;
                a11.add(i28, i7, i5);
                AlignmentMatrix a12 = this.bMatrix;
                AlignmentNode a13 = a12.get(i7, i5);
                a13.setTraceUp(true);
                if(i28 != i16)
                {
                    a13.setTraceA(false);
                }
                else
                {
                    a13.setTraceA(true);
                }
                if(i28 != i20)
                {
                    a13.setTraceB(false);
                }
                else
                {
                    a13.setTraceB(true);
                }
                if(i28 != i26)
                {
                    a13.setTraceC(false);
                }
                else
                {
                    a13.setTraceC(true);
                }
                AlignmentMatrix a14 = this.aMatrix;
                int i29 = i7 - 1;
                int i30 = a14.valueAt(i29, i5);
                int i31 = this.initialGapCost;
                int i32 = i30 + i31;
                int i33 = this.runningGapCost;
                int i34 = i32 + i33;
                AlignmentMatrix a15 = this.bMatrix;
                int i35 = i7 - 1;
                int i36 = a15.valueAt(i35, i5);
                int i37 = this.initialGapCost;
                int i38 = i36 + i37;
                int i39 = this.runningGapCost;
                int i40 = i38 + i39;
                AlignmentMatrix a16 = this.cMatrix;
                int i41 = i7 - 1;
                int i42 = a16.valueAt(i41, i5);
                int i43 = this.runningGapCost;
                int i44 = i42 + i43;
                int i45 = i40 <= i34?i34:i40;
                int i46 = i44 <= i45?i45:i44;
                AlignmentMatrix a17 = this.cMatrix;
                a17.add(i46, i7, i5);
                AlignmentMatrix a18 = this.cMatrix;
                AlignmentNode a19 = a18.get(i7, i5);
                a19.setTraceLeft(true);
                if(i46 != i34)
                {
                    a19.setTraceA(false);
                }
                else
                {
                    a19.setTraceA(true);
                }
                if(i46 != i40)
                {
                    a19.setTraceB(false);
                }
                else
                {
                    a19.setTraceB(true);
                }
                if(i46 != i44)
                {
                    a19.setTraceC(false);
                }
                else
                {
                    a19.setTraceC(true);
                }
                String[] a20 = this.data;
                String s = a20[0];
                int i47 = i7 - 1;
                int i48 = s.charAt(i47);
                String[] a21 = this.data;
                String s0 = a21[1];
                int i49 = i5 - 1;
                int i50 = s0.charAt(i49);
                if(i48 != i50)
                {
                    int i51 = this.mismatchCost;
                    i8 = i51;
                }
                else
                {
                    int i52 = this.matchCost;
                    i8 = i52;
                }
                AlignmentMatrix a22 = this.aMatrix;
                int i53 = i7 - 1;
                int i54 = i5 - 1;
                int i55 = a22.valueAt(i53, i54);
                AlignmentMatrix a23 = this.bMatrix;
                int i56 = i7 - 1;
                int i57 = i5 - 1;
                int i58 = a23.valueAt(i56, i57);
                AlignmentMatrix a24 = this.cMatrix;
                int i59 = i7 - 1;
                int i60 = i5 - 1;
                int i61 = a24.valueAt(i59, i60);
                int i62 = i58 <= i55?i55:i58;
                int i63 = i61 <= i62?i62:i61;
                AlignmentMatrix a25 = this.aMatrix;
                int i64 = i63 + i8;
                a25.add(i64, i7, i5);
                AlignmentMatrix a26 = this.aMatrix;
                AlignmentNode a27 = a26.get(i7, i5);
                a27.setTraceDiag(true);
                if(i63 != i55)
                {
                    a27.setTraceA(false);
                }
                else
                {
                    a27.setTraceA(true);
                }
                if(i63 != i58)
                {
                    a27.setTraceB(false);
                }
                else
                {
                    a27.setTraceB(true);
                }
                if(i63 != i61)
                {
                    a27.setTraceC(false);
                }
                else
                {
                    a27.setTraceC(true);
                }
                this.setPosition("2.1.1.1");
                int i65 = i7 + 1;
                i7 = i65;
            }
        }
    }
    
    protected void localSimNoGapAlignment()
    {
        int i = 0;
        while(true)
        {
            int i0 = this.xSize;
            if(i >= i0)
            {
                break;
            }
            else
            {
                AlignmentMatrix a = this.theMatrix;
                a.setXIndexHighlight(i);
                AlignmentMatrix a0 = this.theMatrix;
                a0.setYIndexHighlight(0);
                this.setPosition("1.1.1");
                AlignmentMatrix a1 = this.theMatrix;
                a1.add(0, i, 0);
                this.setPosition("1.1.2");
                int i1 = i + 1;
                i = i1;
            }
        }
        AlignmentMatrix a2 = this.theMatrix;
        a2.setXIndexHighlight(-1);
        AlignmentMatrix a3 = this.theMatrix;
        a3.setYIndexHighlight(-1);
        this.setPosition("1.1.1");
        this.setPosition("1.1.4");
        int i2 = 0;
        while(true)
        {
            int i3 = this.ySize;
            if(i2 >= i3)
            {
                break;
            }
            else
            {
                AlignmentMatrix a4 = this.theMatrix;
                a4.setXIndexHighlight(0);
                AlignmentMatrix a5 = this.theMatrix;
                a5.setYIndexHighlight(i2);
                this.setPosition("1.1.5");
                AlignmentMatrix a6 = this.theMatrix;
                a6.add(0, 0, i2);
                this.setPosition("1.1.6");
                int i4 = i2 + 1;
                i2 = i4;
            }
        }
        AlignmentMatrix a7 = this.theMatrix;
        a7.setXIndexHighlight(-1);
        AlignmentMatrix a8 = this.theMatrix;
        a8.setYIndexHighlight(-1);
        this.setPosition("1.1.5");
        this.setPosition("1.1.8");
        int i5 = 1;
        label0: while(true)
        {
            int i6 = this.ySize;
            if(i5 >= i6)
            {
                this.setPosition("2.1");
                AlignmentMatrix a9 = this.theMatrix;
                a9.setXIndexHighlight(-1);
                AlignmentMatrix a10 = this.theMatrix;
                a10.setYIndexHighlight(-1);
                this.setPosition("2.2");
                return;
            }
            AlignmentMatrix a11 = this.theMatrix;
            a11.setYIndexHighlight(i5);
            this.setPosition("2.1");
            int i7 = 1;
            while(true)
            {
                int i8 = 0;
                int i9 = 0;
                int i10 = 0;
                int i11 = 0;
                int i12 = this.xSize;
                if(i7 >= i12)
                {
                    this.setPosition("2.1.1");
                    int i13 = i5 + 1;
                    i5 = i13;
                    continue label0;
                }
                int i14 = this.spaceCost;
                AlignmentMatrix a12 = this.theMatrix;
                a12.setXIndexHighlight(i7);
                this.setPosition("2.1.1");
                String s = localization.Messages.getString("Alignment.191");
                AlignmentMinimum a13 = new AlignmentMinimum(s);
                this.theMinimum = a13;
                AlignmentMinimum a14 = this.theMinimum;
                a14.add();
                AlignmentMinimum a15 = this.theMinimum;
                a15.add();
                AlignmentMinimum a16 = this.theMinimum;
                a16.add();
                AlignmentMinimum a17 = this.theMinimum;
                a17.add();
                String[] a18 = this.data;
                String s0 = a18[0];
                int i15 = i7 - 1;
                int i16 = s0.charAt(i15);
                String[] a19 = this.data;
                String s1 = a19[1];
                int i17 = i5 - 1;
                int i18 = s1.charAt(i17);
                if(i16 != i18)
                {
                    int i19 = this.mismatchCost;
                    i8 = i19;
                }
                else
                {
                    int i20 = this.matchCost;
                    i8 = i20;
                }
                AlignmentMatrix a20 = this.theMatrix;
                int i21 = i7 - 1;
                AlignmentNode a21 = a20.get(i21, i5);
                this.tempNode = a21;
                AlignmentNode a22 = this.tempNode;
                a22.highlight();
                AlignmentMinimum a23 = this.theMinimum;
                AlignmentMatrix a24 = this.theMatrix;
                int i22 = i7 - 1;
                int i23 = a24.valueAt(i22, i5);
                int i24 = this.spaceCost;
                a23.set(0, i23, i24);
                this.setPosition("2.1.1.3");
                AlignmentMatrix a25 = this.theMatrix;
                int i25 = i5 - 1;
                AlignmentNode a26 = a25.get(i7, i25);
                this.tempNode = a26;
                AlignmentNode a27 = this.tempNode;
                a27.highlight();
                AlignmentMinimum a28 = this.theMinimum;
                AlignmentMatrix a29 = this.theMatrix;
                int i26 = i5 - 1;
                int i27 = a29.valueAt(i7, i26);
                int i28 = this.spaceCost;
                a28.set(1, i27, i28);
                this.setPosition("2.1.1.4");
                AlignmentMatrix a30 = this.theMatrix;
                int i29 = i7 - 1;
                int i30 = i5 - 1;
                AlignmentNode a31 = a30.get(i29, i30);
                this.tempNode = a31;
                AlignmentNode a32 = this.tempNode;
                a32.highlight();
                AlignmentMinimum a33 = this.theMinimum;
                AlignmentMatrix a34 = this.theMatrix;
                int i31 = i7 - 1;
                int i32 = i5 - 1;
                int i33 = a34.valueAt(i31, i32);
                a33.set(2, i33, i8);
                this.setPosition("2.1.1.5");
                AlignmentMinimum a35 = this.theMinimum;
                a35.set(3, 0, 0);
                this.setPosition("2.1.1.6");
                AlignmentMatrix a36 = this.theMatrix;
                int i34 = i7 - 1;
                int i35 = i5 - 1;
                int i36 = a36.valueAt(i34, i35);
                int i37 = i36 + i8;
                if(i37 <= 0)
                {
                    i9 = 0;
                }
                else
                {
                    AlignmentMatrix a37 = this.theMatrix;
                    int i38 = i7 - 1;
                    int i39 = i5 - 1;
                    int i40 = a37.valueAt(i38, i39);
                    int i41 = i40 + i8;
                    i9 = i41;
                }
                AlignmentMatrix a38 = this.theMatrix;
                int i42 = i7 - 1;
                int i43 = a38.valueAt(i42, i5);
                int i44 = i43 + i14;
                if(i44 <= i9)
                {
                    i10 = i9;
                }
                else
                {
                    AlignmentMatrix a39 = this.theMatrix;
                    int i45 = i7 - 1;
                    int i46 = a39.valueAt(i45, i5);
                    int i47 = i46 + i14;
                    i10 = i47;
                }
                AlignmentMatrix a40 = this.theMatrix;
                int i48 = i5 - 1;
                int i49 = a40.valueAt(i7, i48);
                int i50 = i49 + i14;
                if(i50 <= i10)
                {
                    i11 = i10;
                }
                else
                {
                    AlignmentMatrix a41 = this.theMatrix;
                    int i51 = i5 - 1;
                    int i52 = a41.valueAt(i7, i51);
                    int i53 = i52 + i14;
                    i11 = i53;
                }
                AlignmentMatrix a42 = this.theMatrix;
                a42.add(i11, i7, i5);
                AlignmentMatrix a43 = this.theMatrix;
                AlignmentNode a44 = a43.get(i7, i5);
                this.tempNode = a44;
                AlignmentMatrix a45 = this.theMatrix;
                a45.unHighlight();
                AlignmentMatrix a46 = this.theMatrix;
                int i54 = i7 - 1;
                int i55 = i5 - 1;
                int i56 = a46.valueAt(i54, i55);
                int i57 = i56 + i8;
                if(i11 == i57)
                {
                    AlignmentMatrix a47 = this.theMatrix;
                    int i58 = i7 - 1;
                    int i59 = i5 - 1;
                    AlignmentNode a48 = a47.get(i58, i59);
                    a48.highlight();
                    AlignmentMinimum a49 = this.theMinimum;
                    a49.highlight(2);
                    AlignmentNode a50 = this.tempNode;
                    a50.setTraceDiag(true);
                }
                AlignmentMatrix a51 = this.theMatrix;
                int i60 = i7 - 1;
                int i61 = a51.valueAt(i60, i5);
                int i62 = i61 + i14;
                if(i11 == i62)
                {
                    AlignmentNode a52 = this.tempNode;
                    a52.setTraceLeft(true);
                    AlignmentMatrix a53 = this.theMatrix;
                    int i63 = i7 - 1;
                    AlignmentNode a54 = a53.get(i63, i5);
                    a54.highlight();
                    AlignmentMinimum a55 = this.theMinimum;
                    a55.highlight(0);
                }
                AlignmentMatrix a56 = this.theMatrix;
                int i64 = i5 - 1;
                int i65 = a56.valueAt(i7, i64);
                int i66 = i65 + i14;
                if(i11 == i66)
                {
                    AlignmentNode a57 = this.tempNode;
                    a57.setTraceUp(true);
                    AlignmentMatrix a58 = this.theMatrix;
                    int i67 = i5 - 1;
                    AlignmentNode a59 = a58.get(i7, i67);
                    a59.highlight();
                    AlignmentMinimum a60 = this.theMinimum;
                    a60.highlight(1);
                }
                if(i11 == 0)
                {
                    AlignmentMinimum a61 = this.theMinimum;
                    a61.highlight(3);
                }
                this.setPosition("2.1.1.2");
                AlignmentMatrix a62 = this.theMatrix;
                a62.unHighlight();
                AlignmentMinimum a63 = this.theMinimum;
                a63.unHighlight();
                this.theMinimum = null;
                int i68 = i7 + 1;
                i7 = i68;
            }
        }
    }
    
    public void moveX(int i)
    {
        AlignmentMatrix3D a = this.the3D;
        a.moveX(i);
        this.repaint();
    }
    
    protected void noGapFindPathInteractive(int i, int i0, java.util.Stack a)
    {
        java.awt.Point a0 = new java.awt.Point(i, i0);
        Object a1 = a.push((Object)a0);
        AlignmentMatrix a2 = this.theMatrix;
        AlignmentNode a3 = a2.get(i, i0);
        a3.highlight();
        java.awt.Color a4 = Alignment.HIGHLIGHT_RING_COLOR;
        a3.setRingColor(a4);
        this.setPosition("3.3");
        int i1 = a3.getTraceLeft()?1:0;
        label1: {
            label0: {
                if(i1 != 0)
                {
                    break label0;
                }
                int i2 = a3.getTraceDiag()?1:0;
                if(i2 != 0)
                {
                    break label0;
                }
                int i3 = a3.getTraceUp()?1:0;
                if(i3 != 0)
                {
                    break label0;
                }
                this.setPosition("3.4");
                Object a5 = a.pop();
                Object a6 = a.pop();
                break label1;
            }
            int i4 = a3.getTraceLeft()?1:0;
            label3: {
                label2: {
                    if(i4 == 0)
                    {
                        break label2;
                    }
                    this.setPosition("3.3.1");
                    AlignmentMatrix a7 = this.theMatrix;
                    int i5 = i - 1;
                    a7.setXIndexHighlight(i5);
                    AlignmentMatrix a8 = this.theMatrix;
                    int i6 = i - 1;
                    AlignmentNode a9 = a8.get(i6, i0);
                    java.awt.Color a10 = Alignment.HIGHLIGHT_RING_COLOR;
                    a9.setRingColor(a10);
                    java.awt.Color a11 = Alignment.DEFAULT_RING_COLOR;
                    a3.setRingColor(a11);
                    a3.highlightLeft();
                    this.setPosition("3.3.1.2");
                    Object a12 = a.push((Object)"L");
                    int i7 = i - 1;
                    this.noGapFindPathInteractive(i7, i0, a);
                    break label3;
                }
                int i8 = a3.getTraceDiag()?1:0;
                label4: {
                    if(i8 == 0)
                    {
                        break label4;
                    }
                    this.setPosition("3.3.1");
                    this.setPosition("3.3.2");
                    AlignmentMatrix a13 = this.theMatrix;
                    int i9 = i - 1;
                    a13.setXIndexHighlight(i9);
                    this.setPosition("3.3.2.2");
                    AlignmentMatrix a14 = this.theMatrix;
                    int i10 = i0 - 1;
                    a14.setYIndexHighlight(i10);
                    AlignmentMatrix a15 = this.theMatrix;
                    int i11 = i - 1;
                    int i12 = i0 - 1;
                    AlignmentNode a16 = a15.get(i11, i12);
                    java.awt.Color a17 = Alignment.HIGHLIGHT_RING_COLOR;
                    a16.setRingColor(a17);
                    java.awt.Color a18 = Alignment.DEFAULT_RING_COLOR;
                    a3.setRingColor(a18);
                    a3.highlightDiag();
                    this.setPosition("3.3.2.3");
                    Object a19 = a.push((Object)"D");
                    int i13 = i - 1;
                    int i14 = i0 - 1;
                    this.noGapFindPathInteractive(i13, i14, a);
                    break label3;
                }
                int i15 = a3.getTraceUp()?1:0;
                if(i15 != 0)
                {
                    this.setPosition("3.3.1");
                    this.setPosition("3.3.2");
                    this.setPosition("3.3.3");
                    AlignmentMatrix a20 = this.theMatrix;
                    int i16 = i0 - 1;
                    a20.setYIndexHighlight(i16);
                    AlignmentMatrix a21 = this.theMatrix;
                    int i17 = i0 - 1;
                    AlignmentNode a22 = a21.get(i, i17);
                    java.awt.Color a23 = Alignment.HIGHLIGHT_RING_COLOR;
                    a22.setRingColor(a23);
                    java.awt.Color a24 = Alignment.DEFAULT_RING_COLOR;
                    a3.setRingColor(a24);
                    a3.highlightUp();
                    this.setPosition("3.3.3.2");
                    Object a25 = a.push((Object)"U");
                    int i18 = i0 - 1;
                    this.noGapFindPathInteractive(i, i18, a);
                }
            }
            Object a26 = a.pop();
            Object a27 = a.pop();
        }
    }
    
    protected void noGapFindPathNonInteractive(int i, int i0, java.util.Stack a)
    {
        java.awt.Point a0 = new java.awt.Point(i, i0);
        Object a1 = a.push((Object)a0);
        AlignmentMatrix a2 = this.theMatrix;
        AlignmentNode a3 = a2.get(i, i0);
        int i1 = a3.getTraceLeft()?1:0;
        label0: {
            if(i1 != 0)
            {
                break label0;
            }
            int i2 = a3.getTraceDiag()?1:0;
            if(i2 != 0)
            {
                break label0;
            }
            int i3 = a3.getTraceUp()?1:0;
            if(i3 != 0)
            {
                break label0;
            }
            com.cim.AIA.ElementArray a4 = new com.cim.AIA.ElementArray(0, 0);
            this.string1EA = a4;
            com.cim.AIA.ElementArray a5 = new com.cim.AIA.ElementArray(0, 0);
            this.string2EA = a5;
            com.cim.AIA.ElementArray a6 = this.string1EA;
            a6.setColumGap(0);
            com.cim.AIA.ElementArray a7 = this.string2EA;
            a7.setColumGap(0);
            com.cim.AIA.ElementArray a8 = this.string1EA;
            a8.setElementWidth(20);
            com.cim.AIA.ElementArray a9 = this.string2EA;
            a9.setElementWidth(20);
            AlignmentMatrix a10 = this.theMatrix;
            a10.unHighlight();
            AlignmentMatrix a11 = this.theMatrix;
            a11.unHighlightTrace();
            AlignmentMatrix a12 = this.theMatrix;
            java.awt.Color a13 = Alignment.DEFAULT_NODE_COLOR;
            java.awt.Color a14 = AlignmentNode.DEFAULT_ARROW_COLOR;
            a12.setAllColor(a13, a14);
            Object a15 = a.clone();
            java.util.Stack dummy = (java.util.Stack)a15;
            java.util.Stack a16 = (java.util.Stack)a15;
            String s = "";
            int i4 = 0;
            while(true)
            {
                int i5 = 0;
                int i6 = 0;
                int i7 = 0;
                int i8 = 0;
                int i9 = a16.empty()?1:0;
                int i10 = i4;
                int i11 = i10;
                if(i9 != 0)
                {
                    break;
                }
                else
                {
                    i5 = i11;
                }
                Object a17 = a16.pop();
                int i12 = i5;
                java.awt.Point dummy0 = (java.awt.Point)a17;
                java.awt.Point a18 = (java.awt.Point)a17;
                int i13 = i12;
                AlignmentMatrix a19 = this.theMatrix;
                int i14 = i13;
                int i15 = a18.x;
                int i16 = i14;
                int i17 = a18.y;
                int i18 = i16;
                AlignmentNode a20 = a19.get(i15, i17);
                int i19 = i18;
                a20.highlight();
                int i20 = i19;
                int i21 = i20;
                if(s != "D")
                {
                    i6 = i21;
                }
                else
                {
                    com.cim.AIA.ElementArray a21 = this.string1EA;
                    StringBuilder a22 = new StringBuilder();
                    String[] a23 = this.data;
                    String s0 = a23[0];
                    int i22 = a18.x;
                    int i23 = i22 - 1;
                    int i24 = s0.charAt(i23);
                    StringBuilder a24 = a22.append((char)i24);
                    StringBuilder a25 = a24.append("");
                    String s1 = a25.toString();
                    com.cim.AIA.Node a26 = new com.cim.AIA.Node((Object)s1, 0);
                    a21.setValue(i20, (com.cim.AIA.Element)a26);
                    com.cim.AIA.ElementArray a27 = this.string2EA;
                    StringBuilder a28 = new StringBuilder();
                    String[] a29 = this.data;
                    String s2 = a29[1];
                    int i25 = a18.y;
                    int i26 = i25 - 1;
                    int i27 = s2.charAt(i26);
                    StringBuilder a30 = a28.append((char)i27);
                    StringBuilder a31 = a30.append("");
                    String s3 = a31.toString();
                    com.cim.AIA.Node a32 = new com.cim.AIA.Node((Object)s3, 0);
                    a27.setValue(i20, (com.cim.AIA.Element)a32);
                    a20.highlightDiag();
                    int i28 = i20 + 1;
                    i6 = i28;
                }
                int i29 = i6;
                if(s != "L")
                {
                    i7 = i29;
                }
                else
                {
                    com.cim.AIA.ElementArray a33 = this.string1EA;
                    StringBuilder a34 = new StringBuilder();
                    String[] a35 = this.data;
                    String s4 = a35[0];
                    int i30 = a18.x;
                    int i31 = i30 - 1;
                    int i32 = s4.charAt(i31);
                    StringBuilder a36 = a34.append((char)i32);
                    StringBuilder a37 = a36.append("");
                    String s5 = a37.toString();
                    com.cim.AIA.Node a38 = new com.cim.AIA.Node((Object)s5, 0);
                    a33.setValue(i6, (com.cim.AIA.Element)a38);
                    com.cim.AIA.ElementArray a39 = this.string2EA;
                    com.cim.AIA.Node a40 = new com.cim.AIA.Node((Object)"-", 0);
                    a39.setValue(i6, (com.cim.AIA.Element)a40);
                    a20.highlightLeft();
                    int i33 = i6 + 1;
                    i7 = i33;
                }
                int i34 = i7;
                if(s != "U")
                {
                    i8 = i34;
                }
                else
                {
                    com.cim.AIA.ElementArray a41 = this.string1EA;
                    com.cim.AIA.Node a42 = new com.cim.AIA.Node((Object)"-", 0);
                    a41.setValue(i7, (com.cim.AIA.Element)a42);
                    com.cim.AIA.ElementArray a43 = this.string2EA;
                    StringBuilder a44 = new StringBuilder();
                    String[] a45 = this.data;
                    String s6 = a45[1];
                    int i35 = a18.y;
                    int i36 = i35 - 1;
                    int i37 = s6.charAt(i36);
                    StringBuilder a46 = a44.append((char)i37);
                    StringBuilder a47 = a46.append("");
                    String s7 = a47.toString();
                    com.cim.AIA.Node a48 = new com.cim.AIA.Node((Object)s7, 0);
                    a43.setValue(i7, (com.cim.AIA.Element)a48);
                    a20.highlightUp();
                    int i38 = i7 + 1;
                    i8 = i38;
                }
                Object a49 = a16.pop();
                int i39 = i8;
                String dummy1 = (String)a49;
                String s8 = (String)a49;
                int i40 = i39;
                s = s8;
                i4 = i40;
            }
            int i41 = Alignment.isFirstAlignment?1:0;
            if(i41 == 0)
            {
                this.setPosition("3.6.1");
                this.setPosition("3.6");
            }
            else
            {
                Alignment.isFirstAlignment = false;
                this.setPosition("3.5");
                this.setPosition("3.6");
            }
        }
        int i42 = a3.getTraceLeft()?1:0;
        if(i42 != 0)
        {
            Object a50 = a.push((Object)"L");
            int i43 = i - 1;
            this.noGapFindPathNonInteractive(i43, i0, a);
        }
        int i44 = a3.getTraceDiag()?1:0;
        if(i44 != 0)
        {
            Object a51 = a.push((Object)"D");
            int i45 = i - 1;
            int i46 = i0 - 1;
            this.noGapFindPathNonInteractive(i45, i46, a);
        }
        int i47 = a3.getTraceUp()?1:0;
        if(i47 != 0)
        {
            Object a52 = a.push((Object)"U");
            int i48 = i0 - 1;
            this.noGapFindPathNonInteractive(i, i48, a);
        }
        Object a53 = a.pop();
        Object a54 = a.pop();
    }
    
    protected void printD()
    {
        java.io.PrintStream a = System.out;
        a.println("D() = ");
        int i = 0;
        label0: while(true)
        {
            int i0 = this.xSize;
            if(i >= i0)
            {
                return;
            }
            int i1 = 0;
            while(true)
            {
                String s = null;
                String s0 = null;
                String s1 = null;
                int i2 = this.ySize;
                if(i1 >= i2)
                {
                    java.io.PrintStream a0 = System.out;
                    a0.println();
                    int i3 = i + 1;
                    i = i3;
                    continue label0;
                }
                StringBuilder a1 = new StringBuilder();
                int[][] a2 = this.D;
                int[] a3 = a2[i];
                int i4 = a3[i1];
                StringBuilder a4 = a1.append(i4);
                StringBuilder a5 = a4.append("");
                String s2 = a5.toString();
                String s3 = new String();
                int i5 = s2.length();
                if(i5 != 1)
                {
                    s = s3;
                }
                else
                {
                    String s4 = new String("   ");
                    s = s4;
                }
                int i6 = s2.length();
                if(i6 != 2)
                {
                    s0 = s;
                }
                else
                {
                    String s5 = new String("  ");
                    s0 = s5;
                }
                int i7 = s2.length();
                if(i7 != 3)
                {
                    s1 = s0;
                }
                else
                {
                    String s6 = new String(" ");
                    s1 = s6;
                }
                java.io.PrintStream a6 = System.out;
                StringBuilder a7 = new StringBuilder();
                StringBuilder a8 = a7.append(s2);
                StringBuilder a9 = a8.append(s1);
                String s7 = a9.toString();
                a6.print(s7);
                int i8 = i1 + 1;
                i1 = i8;
            }
        }
    }
    
    protected void printE()
    {
        java.io.PrintStream a = System.out;
        a.println("E() = ");
        int i = 0;
        label0: while(true)
        {
            int i0 = this.ySize;
            if(i >= i0)
            {
                return;
            }
            int i1 = 0;
            while(true)
            {
                String s = null;
                String s0 = null;
                String s1 = null;
                String s2 = null;
                String s3 = null;
                String s4 = null;
                String s5 = null;
                String s6 = null;
                String s7 = null;
                int i2 = this.xSize;
                if(i1 >= i2)
                {
                    java.io.PrintStream a0 = System.out;
                    a0.println();
                    int i3 = i + 1;
                    i = i3;
                    continue label0;
                }
                StringBuilder a1 = new StringBuilder();
                int[][] a2 = this.E;
                int[] a3 = a2[i1];
                int i4 = a3[i];
                StringBuilder a4 = a1.append(i4);
                StringBuilder a5 = a4.append("");
                String s8 = a5.toString();
                Object[] a6 = this.EA;
                boolean[] a7 = (boolean[])a6[i1];
                int i5 = a7[i]?1:0;
                if(i5 != 1)
                {
                    s = s8;
                }
                else
                {
                    StringBuilder a8 = new StringBuilder();
                    StringBuilder a9 = a8.append(s8);
                    StringBuilder a10 = a9.append("A");
                    String s9 = a10.toString();
                    s = s9;
                }
                Object[] a11 = this.EB;
                boolean[] a12 = (boolean[])a11[i1];
                int i6 = a12[i]?1:0;
                if(i6 != 1)
                {
                    s0 = s;
                }
                else
                {
                    StringBuilder a13 = new StringBuilder();
                    StringBuilder a14 = a13.append(s);
                    StringBuilder a15 = a14.append("B");
                    String s10 = a15.toString();
                    s0 = s10;
                }
                Object[] a16 = this.EC;
                boolean[] a17 = (boolean[])a16[i1];
                int i7 = a17[i]?1:0;
                if(i7 != 1)
                {
                    s1 = s0;
                }
                else
                {
                    StringBuilder a18 = new StringBuilder();
                    StringBuilder a19 = a18.append(s0);
                    StringBuilder a20 = a19.append("C");
                    String s11 = a20.toString();
                    s1 = s11;
                }
                String s12 = new String();
                int i8 = s1.length();
                if(i8 != 1)
                {
                    s2 = s12;
                }
                else
                {
                    String s13 = new String("      ");
                    s2 = s13;
                }
                int i9 = s1.length();
                if(i9 != 2)
                {
                    s3 = s2;
                }
                else
                {
                    String s14 = new String("     ");
                    s3 = s14;
                }
                int i10 = s1.length();
                if(i10 != 3)
                {
                    s4 = s3;
                }
                else
                {
                    String s15 = new String("    ");
                    s4 = s15;
                }
                int i11 = s1.length();
                if(i11 != 4)
                {
                    s5 = s4;
                }
                else
                {
                    String s16 = new String("   ");
                    s5 = s16;
                }
                int i12 = s1.length();
                if(i12 != 5)
                {
                    s6 = s5;
                }
                else
                {
                    String s17 = new String("  ");
                    s6 = s17;
                }
                int i13 = s1.length();
                if(i13 != 6)
                {
                    s7 = s6;
                }
                else
                {
                    String s18 = new String(" ");
                    s7 = s18;
                }
                java.io.PrintStream a21 = System.out;
                StringBuilder a22 = new StringBuilder();
                StringBuilder a23 = a22.append(s1);
                StringBuilder a24 = a23.append(s7);
                String s19 = a24.toString();
                a21.print(s19);
                int i14 = i1 + 1;
                i1 = i14;
            }
        }
    }
    
    protected void printF()
    {
        java.io.PrintStream a = System.out;
        a.println("F() = ");
        int i = 0;
        label0: while(true)
        {
            int i0 = this.ySize;
            if(i >= i0)
            {
                return;
            }
            int i1 = 0;
            while(true)
            {
                String s = null;
                String s0 = null;
                String s1 = null;
                String s2 = null;
                String s3 = null;
                String s4 = null;
                String s5 = null;
                String s6 = null;
                String s7 = null;
                int i2 = this.xSize;
                if(i1 >= i2)
                {
                    java.io.PrintStream a0 = System.out;
                    a0.println();
                    int i3 = i + 1;
                    i = i3;
                    continue label0;
                }
                StringBuilder a1 = new StringBuilder();
                int[][] a2 = this.F;
                int[] a3 = a2[i1];
                int i4 = a3[i];
                StringBuilder a4 = a1.append(i4);
                StringBuilder a5 = a4.append("");
                String s8 = a5.toString();
                String s9 = new String();
                Object[] a6 = this.FA;
                boolean[] a7 = (boolean[])a6[i1];
                int i5 = a7[i]?1:0;
                if(i5 != 1)
                {
                    s = s8;
                }
                else
                {
                    StringBuilder a8 = new StringBuilder();
                    StringBuilder a9 = a8.append(s8);
                    StringBuilder a10 = a9.append("A");
                    String s10 = a10.toString();
                    s = s10;
                }
                Object[] a11 = this.FB;
                boolean[] a12 = (boolean[])a11[i1];
                int i6 = a12[i]?1:0;
                if(i6 != 1)
                {
                    s0 = s;
                }
                else
                {
                    StringBuilder a13 = new StringBuilder();
                    StringBuilder a14 = a13.append(s);
                    StringBuilder a15 = a14.append("B");
                    String s11 = a15.toString();
                    s0 = s11;
                }
                Object[] a16 = this.FC;
                boolean[] a17 = (boolean[])a16[i1];
                int i7 = a17[i]?1:0;
                if(i7 != 1)
                {
                    s1 = s0;
                }
                else
                {
                    StringBuilder a18 = new StringBuilder();
                    StringBuilder a19 = a18.append(s0);
                    StringBuilder a20 = a19.append("C");
                    String s12 = a20.toString();
                    s1 = s12;
                }
                int i8 = s1.length();
                if(i8 != 1)
                {
                    s2 = s9;
                }
                else
                {
                    String s13 = new String("      ");
                    s2 = s13;
                }
                int i9 = s1.length();
                if(i9 != 2)
                {
                    s3 = s2;
                }
                else
                {
                    String s14 = new String("     ");
                    s3 = s14;
                }
                int i10 = s1.length();
                if(i10 != 3)
                {
                    s4 = s3;
                }
                else
                {
                    String s15 = new String("    ");
                    s4 = s15;
                }
                int i11 = s1.length();
                if(i11 != 4)
                {
                    s5 = s4;
                }
                else
                {
                    String s16 = new String("   ");
                    s5 = s16;
                }
                int i12 = s1.length();
                if(i12 != 5)
                {
                    s6 = s5;
                }
                else
                {
                    String s17 = new String("  ");
                    s6 = s17;
                }
                int i13 = s1.length();
                if(i13 != 6)
                {
                    s7 = s6;
                }
                else
                {
                    String s18 = new String(" ");
                    s7 = s18;
                }
                java.io.PrintStream a21 = System.out;
                StringBuilder a22 = new StringBuilder();
                StringBuilder a23 = a22.append(s1);
                StringBuilder a24 = a23.append(s7);
                String s19 = a24.toString();
                a21.print(s19);
                int i14 = i1 + 1;
                i1 = i14;
            }
        }
    }
    
    protected void printV()
    {
        java.io.PrintStream a = System.out;
        a.println("V() = ");
        int i = 0;
        label0: while(true)
        {
            int i0 = this.ySize;
            if(i >= i0)
            {
                return;
            }
            int i1 = 0;
            while(true)
            {
                String s = null;
                String s0 = null;
                String s1 = null;
                String s2 = null;
                String s3 = null;
                String s4 = null;
                String s5 = null;
                String s6 = null;
                String s7 = null;
                int i2 = this.xSize;
                if(i1 >= i2)
                {
                    java.io.PrintStream a0 = System.out;
                    a0.println();
                    int i3 = i + 1;
                    i = i3;
                    continue label0;
                }
                StringBuilder a1 = new StringBuilder();
                AlignmentMatrix a2 = this.theMatrix;
                int i4 = a2.valueAt(i1, i);
                StringBuilder a3 = a1.append(i4);
                StringBuilder a4 = a3.append("");
                String s8 = a4.toString();
                String s9 = new String();
                Object[] a5 = this.GA;
                boolean[] a6 = (boolean[])a5[i1];
                int i5 = a6[i]?1:0;
                if(i5 != 1)
                {
                    s = s8;
                }
                else
                {
                    StringBuilder a7 = new StringBuilder();
                    StringBuilder a8 = a7.append(s8);
                    StringBuilder a9 = a8.append("A");
                    String s10 = a9.toString();
                    s = s10;
                }
                Object[] a10 = this.GB;
                boolean[] a11 = (boolean[])a10[i1];
                int i6 = a11[i]?1:0;
                if(i6 != 1)
                {
                    s0 = s;
                }
                else
                {
                    StringBuilder a12 = new StringBuilder();
                    StringBuilder a13 = a12.append(s);
                    StringBuilder a14 = a13.append("B");
                    String s11 = a14.toString();
                    s0 = s11;
                }
                Object[] a15 = this.GC;
                boolean[] a16 = (boolean[])a15[i1];
                int i7 = a16[i]?1:0;
                if(i7 != 1)
                {
                    s1 = s0;
                }
                else
                {
                    StringBuilder a17 = new StringBuilder();
                    StringBuilder a18 = a17.append(s0);
                    StringBuilder a19 = a18.append("C");
                    String s12 = a19.toString();
                    s1 = s12;
                }
                int i8 = s1.length();
                if(i8 != 1)
                {
                    s2 = s9;
                }
                else
                {
                    String s13 = localization.Messages.getString("Alignment.277");
                    String s14 = new String(s13);
                    s2 = s14;
                }
                int i9 = s1.length();
                if(i9 != 2)
                {
                    s3 = s2;
                }
                else
                {
                    String s15 = new String("     ");
                    s3 = s15;
                }
                int i10 = s1.length();
                if(i10 != 3)
                {
                    s4 = s3;
                }
                else
                {
                    String s16 = new String("    ");
                    s4 = s16;
                }
                int i11 = s1.length();
                if(i11 != 4)
                {
                    s5 = s4;
                }
                else
                {
                    String s17 = new String("   ");
                    s5 = s17;
                }
                int i12 = s1.length();
                if(i12 != 5)
                {
                    s6 = s5;
                }
                else
                {
                    String s18 = new String("  ");
                    s6 = s18;
                }
                int i13 = s1.length();
                if(i13 != 6)
                {
                    s7 = s6;
                }
                else
                {
                    String s19 = new String(" ");
                    s7 = s19;
                }
                java.io.PrintStream a20 = System.out;
                StringBuilder a21 = new StringBuilder();
                StringBuilder a22 = a21.append(s1);
                StringBuilder a23 = a22.append(s7);
                String s20 = a23.toString();
                a20.print(s20);
                int i14 = i1 + 1;
                i1 = i14;
            }
        }
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
                this.background = a0;
                break label1;
            }
            String s1 = com.cim.AIA.ColorSelection.TEXT_COLOR;
            int i0 = s.equalsIgnoreCase(s1)?1:0;
            label2: {
                if(i0 == 0)
                {
                    break label2;
                }
                java.awt.Color a1 = a.getColor();
                this.textColor = a1;
                break label1;
            }
            String s2 = Alignment.HIGHLIGHT_COLOR;
            int i1 = s.equalsIgnoreCase(s2)?1:0;
            label3: {
                if(i1 == 0)
                {
                    break label3;
                }
                java.awt.Color a2 = a.getColor();
                this.highlightColor = a2;
                break label1;
            }
            String s3 = Alignment.NODE_COLOR;
            int i2 = s.equalsIgnoreCase(s3)?1:0;
            if(i2 != 0)
            {
                java.awt.Color a3 = a.getColor();
                this.nodeColor = a3;
                java.awt.Color a4 = this.nodeColor;
                com.cim.AIA.Node.setDefaultBackgroundColor(a4);
            }
        }
    }
    
    public void reInitialise(Object a)
    {
        String[] dummy = (String[])a;
        String[] a0 = (String[])a;
        this.data = a0;
        this.initialise();
    }
    
    protected void removeAllAnimationRequests()
    {
    }
    
    protected void removeAllQuestions()
    {
    }
    
    public void rotateX(int i)
    {
        AlignmentMatrix3D a = this.the3D;
        int i0 = Alignment.xRotate;
        int i1 = i - i0;
        double d = (double)i1;
        a.rotateX(d);
        Alignment.xRotate = i;
        this.repaint();
    }
    
    public void rotateY(int i)
    {
        AlignmentMatrix3D a = this.the3D;
        int i0 = Alignment.yRotate;
        int i1 = i - i0;
        double d = (double)i1;
        a.rotateY(d);
        Alignment.yRotate = i;
        this.repaint();
    }
    
    public void rotateZ(int i)
    {
        AlignmentMatrix3D a = this.the3D;
        int i0 = Alignment.zRotate;
        int i1 = i - i0;
        double d = (double)i1;
        a.rotateZ(d);
        Alignment.zRotate = i;
        this.repaint();
    }
    
    protected void run()
    {
        this.setPosition("0");
        int i = Alignment.runningMode;
        label1: {
            int i0 = 0;
            int i1 = 0;
            int i2 = 0;
            label0: {
                if(i != 1)
                {
                    break label0;
                }
                int i3 = Alignment.currentVariation;
                if(i3 != 1)
                {
                    break label0;
                }
                this.globalDistNoGapAlignment();
                int i4 = this.xSize;
                int i5 = i4 - 1;
                int i6 = this.ySize;
                int i7 = i6 - 1;
                this.traceBack(i5, i7);
                this.setPosition("4.0");
                break label1;
            }
            int i8 = Alignment.runningMode;
            label2: {
                int i9 = 0;
                int i10 = 0;
                int i11 = 0;
                if(i8 != 1)
                {
                    break label2;
                }
                int i12 = Alignment.currentVariation;
                if(i12 != 2)
                {
                    break label2;
                }
                this.globalDistGapAlignment();
                AlignmentMatrix a = this.aMatrix;
                int i13 = this.xSize;
                int i14 = i13 - 1;
                int i15 = this.ySize;
                int i16 = i15 - 1;
                int i17 = a.valueAt(i14, i16);
                AlignmentMatrix a0 = this.bMatrix;
                int i18 = this.xSize;
                int i19 = i18 - 1;
                int i20 = this.ySize;
                int i21 = i20 - 1;
                int i22 = a0.valueAt(i19, i21);
                if(i22 >= i17)
                {
                    i9 = i17;
                    i10 = 1;
                }
                else
                {
                    AlignmentMatrix a1 = this.bMatrix;
                    int i23 = this.xSize;
                    int i24 = i23 - 1;
                    int i25 = this.ySize;
                    int i26 = i25 - 1;
                    int i27 = a1.valueAt(i24, i26);
                    i9 = i27;
                    i10 = 2;
                }
                AlignmentMatrix a2 = this.cMatrix;
                int i28 = this.xSize;
                int i29 = i28 - 1;
                int i30 = this.ySize;
                int i31 = i30 - 1;
                int i32 = a2.valueAt(i29, i31);
                if(i32 >= i9)
                {
                    i11 = i10;
                }
                else
                {
                    AlignmentMatrix a3 = this.cMatrix;
                    int i33 = this.xSize;
                    int i34 = i33 - 1;
                    int i35 = this.ySize;
                    int i36 = i35 - 1;
                    int i37 = a3.valueAt(i34, i36);
                    i11 = 3;
                }
                int i38 = this.xSize;
                int i39 = i38 - 1;
                int i40 = this.ySize;
                int i41 = i40 - 1;
                this.traceBack(i39, i41, i11);
                this.setPosition("4.0");
                break label1;
            }
            int i42 = Alignment.runningMode;
            label3: {
                if(i42 != 2)
                {
                    break label3;
                }
                int i43 = Alignment.currentVariation;
                if(i43 != 1)
                {
                    break label3;
                }
                this.globalSimNoGapAlignment();
                int i44 = this.xSize;
                int i45 = i44 - 1;
                int i46 = this.ySize;
                int i47 = i46 - 1;
                this.traceBack(i45, i47);
                this.setPosition("4.0");
                break label1;
            }
            int i48 = Alignment.runningMode;
            label5: {
                label4: {
                    if(i48 != 2)
                    {
                        break label4;
                    }
                    int i49 = Alignment.currentVariation;
                    if(i49 == 2)
                    {
                        break label5;
                    }
                }
                int i50 = Alignment.runningMode;
                label6: {
                    if(i50 != 3)
                    {
                        break label6;
                    }
                    int i51 = Alignment.currentVariation;
                    if(i51 != 1)
                    {
                        break label6;
                    }
                    this.localSimNoGapAlignment();
                    AlignmentMatrix a4 = this.theMatrix;
                    int i52 = a4.getMaxX();
                    AlignmentMatrix a5 = this.theMatrix;
                    int i53 = a5.getMaxY();
                    this.traceBack(i52, i53);
                    this.setPosition("4.0");
                    break label1;
                }
                int i54 = Alignment.runningMode;
                if(i54 != 3)
                {
                    break label1;
                }
                int i55 = Alignment.currentVariation;
                if(i55 != 2)
                {
                    break label1;
                }
                else
                {
                    this.localSimGapAlignment();
                    AlignmentMatrix a6 = this.theMatrix;
                    int i56 = a6.getMaxX();
                    AlignmentMatrix a7 = this.theMatrix;
                    int i57 = a7.getMaxY();
                    this.traceBack(i56, i57);
                    break label1;
                }
            }
            this.globalSimGapAlignment3();
            AlignmentMatrix a8 = this.aMatrix;
            int i58 = this.xSize;
            int i59 = i58 - 1;
            int i60 = this.ySize;
            int i61 = i60 - 1;
            int i62 = a8.valueAt(i59, i61);
            AlignmentMatrix a9 = this.bMatrix;
            int i63 = this.xSize;
            int i64 = i63 - 1;
            int i65 = this.ySize;
            int i66 = i65 - 1;
            int i67 = a9.valueAt(i64, i66);
            if(i67 <= i62)
            {
                i0 = i62;
                i1 = 1;
            }
            else
            {
                AlignmentMatrix a10 = this.bMatrix;
                int i68 = this.xSize;
                int i69 = i68 - 1;
                int i70 = this.ySize;
                int i71 = i70 - 1;
                int i72 = a10.valueAt(i69, i71);
                i0 = i72;
                i1 = 2;
            }
            AlignmentMatrix a11 = this.cMatrix;
            int i73 = this.xSize;
            int i74 = i73 - 1;
            int i75 = this.ySize;
            int i76 = i75 - 1;
            int i77 = a11.valueAt(i74, i76);
            if(i77 <= i0)
            {
                i2 = i1;
            }
            else
            {
                AlignmentMatrix a12 = this.cMatrix;
                int i78 = this.xSize;
                int i79 = i78 - 1;
                int i80 = this.ySize;
                int i81 = i80 - 1;
                int i82 = a12.valueAt(i79, i81);
                i2 = 3;
            }
            int i83 = this.xSize;
            int i84 = i83 - 1;
            int i85 = this.ySize;
            int i86 = i85 - 1;
            this.traceBack(i84, i86, i2);
            this.setPosition("4.0");
        }
    }
    
    public void setInitialGapCost(int i)
    {
        this.initialGapCost = i;
    }
    
    public void setMatchCost(int i)
    {
        this.matchCost = i;
    }
    
    public void setMismatchCost(int i)
    {
        this.mismatchCost = i;
    }
    
    public void setRunningGapCost(int i)
    {
        this.runningGapCost = i;
    }
    
    public void setSpaceCost(int i)
    {
        this.spaceCost = i;
    }
    
    public void setVariation(int i)
    {
        Alignment.currentVariation = i;
    }
    
    protected void storeState(boolean b)
    {
        ((com.cim.AIA.Algorithm)this).storeState(b);
    }
    
    protected void traceBack(int i, int i0)
    {
        Alignment.isFirstAlignment = true;
        java.util.Stack a = new java.util.Stack();
        Object a0 = a.push((Object)"");
        AlignmentMatrix a1 = this.theMatrix;
        a1.setXIndexHighlight(i);
        AlignmentMatrix a2 = this.theMatrix;
        a2.setYIndexHighlight(i0);
        this.setPosition("3.1");
        this.noGapFindPathInteractive(i, i0, a);
        java.util.Stack a3 = new java.util.Stack();
        Object a4 = a3.push((Object)"");
        this.noGapFindPathNonInteractive(i, i0, a3);
    }
    
    protected void traceBack(int i, int i0, int i1)
    {
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        Alignment.isFirstAlignment = true;
        java.util.Stack a = new java.util.Stack();
        AlignmentKey a0 = new AlignmentKey();
        this.theAlignmentKey = a0;
        AlignmentMatrix3D a1 = this.the3D;
        a1.initialiseColor();
        AlignmentMatrix3D a2 = this.the3D;
        a2.setVisible(true);
        AlignmentMatrix3D a3 = this.the3D;
        java.awt.Color a4 = this.highlightColor;
        a3.fillColor(2, 2, 0, a4);
        AlignmentMatrix3D a5 = this.the3D;
        java.awt.Color a6 = this.highlightColor;
        a5.fillColor(2, 2, 1, a6);
        AlignmentMatrix3D a7 = this.the3D;
        java.awt.Color a8 = this.highlightColor;
        a7.fillColor(2, 2, 2, a8);
        AlignmentMatrix a9 = this.aMatrix;
        a9.setInternalBoxCoords(i, i0);
        AlignmentMatrix a10 = this.aMatrix;
        a10.setDrawInternalBox(true);
        AlignmentMatrix a11 = this.aMatrix;
        a11.setXIndexHighlight(i);
        AlignmentMatrix a12 = this.aMatrix;
        a12.setYIndexHighlight(i0);
        AlignmentMatrix a13 = this.bMatrix;
        a13.setInternalBoxCoords(i, i0);
        AlignmentMatrix a14 = this.bMatrix;
        a14.setDrawInternalBox(true);
        AlignmentMatrix a15 = this.bMatrix;
        a15.setXIndexHighlight(i);
        AlignmentMatrix a16 = this.bMatrix;
        a16.setYIndexHighlight(i0);
        AlignmentMatrix a17 = this.cMatrix;
        a17.setInternalBoxCoords(i, i0);
        AlignmentMatrix a18 = this.cMatrix;
        a18.setDrawInternalBox(true);
        AlignmentMatrix a19 = this.cMatrix;
        a19.setXIndexHighlight(i);
        AlignmentMatrix a20 = this.cMatrix;
        a20.setYIndexHighlight(i0);
        AlignmentMatrix a21 = this.aMatrix;
        AlignmentNode a22 = a21.get(i, i0);
        a22.highlight();
        AlignmentMatrix a23 = this.bMatrix;
        AlignmentNode a24 = a23.get(i, i0);
        a24.highlight();
        AlignmentMatrix a25 = this.cMatrix;
        AlignmentNode a26 = a25.get(i, i0);
        a26.highlight();
        this.setPosition("3.1.1");
        if(i1 != 1)
        {
            AlignmentMatrix a27 = this.aMatrix;
            a27.unHighlight();
            AlignmentMatrix a28 = this.aMatrix;
            a28.unHighlightTrace();
            AlignmentMatrix a29 = this.aMatrix;
            java.awt.Color a30 = Alignment.IRRELEVANT_COLOR;
            java.awt.Color a31 = Alignment.IRRELEVANT_ARROW_COLOR;
            a29.setAllColor(a30, a31);
            AlignmentMatrix3D a32 = this.the3D;
            java.awt.Color a33 = java.awt.Color.white;
            a32.fillColor(2, 2, 0, a33);
        }
        if(i1 != 2)
        {
            AlignmentMatrix a34 = this.bMatrix;
            a34.unHighlight();
            AlignmentMatrix a35 = this.bMatrix;
            a35.unHighlightTrace();
            AlignmentMatrix a36 = this.bMatrix;
            java.awt.Color a37 = Alignment.IRRELEVANT_COLOR;
            java.awt.Color a38 = Alignment.IRRELEVANT_ARROW_COLOR;
            a36.setAllColor(a37, a38);
            AlignmentMatrix3D a39 = this.the3D;
            java.awt.Color a40 = java.awt.Color.white;
            a39.fillColor(2, 2, 1, a40);
        }
        if(i1 != 3)
        {
            AlignmentMatrix a41 = this.cMatrix;
            a41.unHighlight();
            AlignmentMatrix a42 = this.cMatrix;
            a42.unHighlightTrace();
            AlignmentMatrix a43 = this.cMatrix;
            java.awt.Color a44 = Alignment.IRRELEVANT_COLOR;
            java.awt.Color a45 = Alignment.IRRELEVANT_ARROW_COLOR;
            a43.setAllColor(a44, a45);
            AlignmentMatrix3D a46 = this.the3D;
            java.awt.Color a47 = java.awt.Color.white;
            a46.fillColor(2, 2, 2, a47);
        }
        if(i1 != 1)
        {
            i2 = i1;
        }
        else
        {
            AlignmentMatrix a48 = this.aMatrix;
            a48.setIsSelected(true);
            i2 = 1;
        }
        if(i2 != 2)
        {
            i3 = i2;
        }
        else
        {
            AlignmentMatrix a49 = this.bMatrix;
            a49.setIsSelected(true);
            i3 = 2;
        }
        if(i3 != 3)
        {
            i4 = i3;
        }
        else
        {
            AlignmentMatrix a50 = this.cMatrix;
            a50.setIsSelected(true);
            i4 = 3;
        }
        AlignmentMatrix3D a51 = this.the3D;
        int i5 = i4 - 1;
        a51.highlightX(i5);
        this.setPosition("3.2");
        AlignmentMatrix3D a52 = this.the3D;
        int i6 = i4 - 1;
        a52.unHighlightX(i6);
        AlignmentMatrix a53 = this.aMatrix;
        a53.unHighlight();
        AlignmentMatrix a54 = this.aMatrix;
        a54.unHighlightTrace();
        AlignmentMatrix a55 = this.aMatrix;
        java.awt.Color a56 = Alignment.IRRELEVANT_COLOR;
        java.awt.Color a57 = Alignment.IRRELEVANT_ARROW_COLOR;
        a55.setAllColor(a56, a57);
        AlignmentMatrix a58 = this.bMatrix;
        a58.unHighlight();
        AlignmentMatrix a59 = this.bMatrix;
        a59.unHighlightTrace();
        AlignmentMatrix a60 = this.bMatrix;
        java.awt.Color a61 = Alignment.IRRELEVANT_COLOR;
        java.awt.Color a62 = Alignment.IRRELEVANT_ARROW_COLOR;
        a60.setAllColor(a61, a62);
        AlignmentMatrix a63 = this.cMatrix;
        a63.unHighlight();
        AlignmentMatrix a64 = this.cMatrix;
        a64.unHighlightTrace();
        AlignmentMatrix a65 = this.cMatrix;
        java.awt.Color a66 = Alignment.IRRELEVANT_COLOR;
        java.awt.Color a67 = Alignment.IRRELEVANT_ARROW_COLOR;
        a65.setAllColor(a66, a67);
        AlignmentMatrix a68 = this.aMatrix;
        AlignmentNode a69 = a68.get(i, i0);
        a69.setIsDisabled(true);
        AlignmentMatrix a70 = this.bMatrix;
        AlignmentNode a71 = a70.get(i, i0);
        a71.setIsDisabled(true);
        AlignmentMatrix a72 = this.cMatrix;
        AlignmentNode a73 = a72.get(i, i0);
        a73.setIsDisabled(true);
        this.gapFindPathInteractive(i, i0, i4, a);
        this.theAlignmentKey = null;
        AlignmentMatrix3D a74 = this.the3D;
        a74.setVisible(false);
        java.util.Stack a75 = new java.util.Stack();
        this.gapFindPathNonInteractive(i, i0, i4, a75);
    }
    
    public void translateX(int i)
    {
        AlignmentMatrix3D a = this.the3D;
        double d = (double)i;
        a.translateX(d);
        this.repaint();
    }
    
    public void translateY(int i)
    {
        AlignmentMatrix3D a = this.the3D;
        double d = (double)i;
        a.translateY(d);
        this.repaint();
    }
    
    public void translateZ(int i)
    {
        AlignmentMatrix3D a = this.the3D;
        double d = (double)i;
        a.translateZ(d);
        this.repaint();
    }
    
    static
    {
        java.awt.Color a = new java.awt.Color(99, 184, 255);
        Alignment.SteelBlue1 = a;
        java.awt.Color a0 = new java.awt.Color(92, 172, 238);
        Alignment.SteelBlue2 = a0;
        java.awt.Color a1 = new java.awt.Color(79, 148, 205);
        Alignment.SteelBlue3 = a1;
        java.awt.Color a2 = new java.awt.Color(54, 100, 139);
        Alignment.SteelBlue4 = a2;
        java.awt.Color a3 = Alignment.SteelBlue4;
        Alignment.DEFAULT_NODE_COLOR = a3;
        java.awt.Color a4 = Alignment.SteelBlue3;
        Alignment.DEFAULT_SELECTED_COLOR = a4;
        java.awt.Color a5 = Alignment.SteelBlue2;
        Alignment.DEFAULT_HIGHLIGHT_COLOR = a5;
        java.awt.Color a6 = Alignment.SteelBlue1;
        Alignment.DEFAULT_CURRENT_COLOR = a6;
        java.awt.Color a7 = new java.awt.Color(255, 0, 0);
        Alignment.HIGHLIGHT_RING_COLOR = a7;
        java.awt.Color a8 = new java.awt.Color(0, 0, 0);
        Alignment.DEFAULT_RING_COLOR = a8;
        java.awt.Color a9 = new java.awt.Color(255, 0, 0);
        Alignment.SELECT_RING_COLOR = a9;
        java.awt.Color a10 = java.awt.Color.gray;
        Alignment.IRRELEVANT_ARROW_COLOR = a10;
        java.awt.Color a11 = java.awt.Color.white;
        Alignment.IRRELEVANT_COLOR = a11;
        String s = localization.Messages.getString("Alignment.1");
        Alignment.HIGHLIGHT_COLOR = s;
        String s0 = localization.Messages.getString("Alignment.2");
        Alignment.NODE_COLOR = s0;
        Alignment.xRotate = -65;
        Alignment.yRotate = 0;
        Alignment.zRotate = 0;
    }
}