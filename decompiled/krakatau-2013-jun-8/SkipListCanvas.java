public class SkipListCanvas extends com.cim.AIA.AlgorithmCanvas {
    private static SkipNode skipNode;
    private SkipNode newElement;
    private com.cim.AIA.ElementArray insertElementArray;
    private com.cim.AIA.ElementArray searchElementArray;
    private static int no_of_levels;
    private int ilevel;
    private int jlevel;
    private int currentNewNodeLevel;
    private int maxLevel;
    private boolean drawLevel;
    private boolean drawDiff;
    private boolean drawFinal;
    private boolean drawI;
    private boolean drawJ;
    private boolean drawElement;
    private boolean runTween;
    private boolean dataCondition;
    private boolean displayDataCondition;
    private static int test;
    private java.util.Random random;
    private com.cim.AIA.Node ptr;
    private com.cim.AIA.Node tailptr;
    private com.cim.AIA.Node highlightedNode;
    private com.cim.AIA.Node dataConditionNode;
    private com.cim.AIA.Node nextDataNode;
    private com.cim.AIA.Node dataItemNode;
    final private static java.awt.Color YES_COLOR;
    final private static java.awt.Color NO_COLOR;
    final private static java.awt.Color POINTER_COLOR;
    final private static java.awt.Color TEXT_COLOR;
    final private static String SEARCH_LABEL;
    final private static String PTR_LABEL;
    final private static String TAILPTR_LABEL;
    final private static String SAVEPTR_LABEL;
    final private static String I_PTR;
    final private static String J_PTR;
    final private static String DATA_COND_LABEL;
    final private static String DATA_LABEL;
    final private static int GAP = 30;
    final private static int ELEMENT_WIDTH = 20;
    final private static int boxWidth = 20;
    private java.awt.Point boxPoint;
    private java.awt.Point levelPoint;
    private java.awt.Point dataConditionPoint;
    private com.cim.AIA.Line dataLine;
    
    public SkipListCanvas()
    {
        super();
        this.drawDiff = false;
        this.drawFinal = false;
        this.drawI = false;
        this.drawJ = false;
        this.drawElement = false;
        this.runTween = false;
        this.dataCondition = false;
        this.displayDataCondition = false;
        java.util.Random a = new java.util.Random();
        this.random = a;
        java.awt.Point a0 = new java.awt.Point(0, 0);
        this.boxPoint = a0;
        this.dataLine = null;
    }
    
    public synchronized void displayAlgorithm(java.awt.Graphics a)
    {
        int i = this.displayDataCondition?1:0;
        label0: {
            if(i == 0)
            {
                break label0;
            }
            com.cim.AIA.Node a0 = this.dataConditionNode;
            if(a0 != null)
            {
                com.cim.AIA.Node a1 = this.dataConditionNode;
                a1.draw(a);
            }
            com.cim.AIA.Node a2 = this.nextDataNode;
            if(a2 != null)
            {
                com.cim.AIA.Node a3 = this.nextDataNode;
                a3.draw(a);
            }
            com.cim.AIA.Node a4 = this.dataItemNode;
            if(a4 != null)
            {
                com.cim.AIA.Node a5 = this.dataItemNode;
                a5.draw(a);
            }
            String s = SkipListCanvas.DATA_COND_LABEL;
            com.cim.AIA.Node a6 = this.dataConditionNode;
            int i0 = a6.getX();
            java.awt.FontMetrics a7 = a.getFontMetrics();
            String s0 = SkipListCanvas.DATA_COND_LABEL;
            int i1 = a7.stringWidth(s0);
            int i2 = i0 - i1;
            com.cim.AIA.Node a8 = this.dataConditionNode;
            int i3 = a8.getY();
            com.cim.AIA.Node a9 = this.dataConditionNode;
            int i4 = a9.getHeight();
            int i5 = 3 * i4;
            int i6 = i5 / 4;
            int i7 = i3 + i6;
            a.drawString(s, i2, i7);
        }
        com.cim.AIA.Line a10 = this.dataLine;
        if(a10 != null)
        {
            com.cim.AIA.Line a11 = this.dataLine;
            a11.draw(a);
        }
        SkipNode a12 = this.newElement;
        label1: {
            if(a12 == null)
            {
                break label1;
            }
            int i8 = this.drawElement?1:0;
            if(i8 == 0)
            {
                break label1;
            }
            int i9 = 0;
            while(true)
            {
                SkipNode a13 = this.newElement;
                com.cim.AIA.Node[] a14 = a13.nodes;
                int i10 = a14.length;
                if(i9 >= i10)
                {
                    break;
                }
                else
                {
                    SkipNode a15 = this.newElement;
                    com.cim.AIA.Node[] a16 = a15.nodes;
                    com.cim.AIA.Node a17 = a16[i9];
                    a17.draw(a);
                    int i11 = i9 + 1;
                    i9 = i11;
                }
            }
        }
        com.cim.AIA.ElementArray a18 = this.insertElementArray;
        if(a18 != null)
        {
            com.cim.AIA.ElementArray a19 = this.insertElementArray;
            a19.draw(a);
        }
        com.cim.AIA.ElementArray a20 = this.searchElementArray;
        if(a20 != null)
        {
            com.cim.AIA.ElementArray a21 = this.searchElementArray;
            a21.draw(a);
            String s1 = SkipListCanvas.SEARCH_LABEL;
            java.awt.Dimension a22 = this.getParentSize();
            int i12 = a22.width;
            int i13 = i12 / 2;
            java.awt.FontMetrics a23 = a.getFontMetrics();
            String s2 = SkipListCanvas.SEARCH_LABEL;
            int i14 = a23.stringWidth(s2);
            int i15 = i14 / 2;
            int i16 = i13 - i15;
            com.cim.AIA.ElementArray a24 = this.searchElementArray;
            java.awt.Point a25 = a24.getLocation();
            int i17 = a25.y;
            a.drawString(s1, i16, i17);
        }
        SkipNode a26 = SkipListCanvas.skipNode;
        label2: {
            if(a26 == null)
            {
                break label2;
            }
            StringBuilder a27 = new StringBuilder();
            String s3 = localization.Messages.getString("SkipListCanvas.8");
            StringBuilder a28 = a27.append(s3);
            int i18 = SkipListCanvas.no_of_levels;
            String s4 = String.valueOf(i18);
            StringBuilder a29 = a28.append(s4);
            String s5 = a29.toString();
            java.awt.Point a30 = this.levelPoint;
            int i19 = a30.x;
            java.awt.Point a31 = this.levelPoint;
            int i20 = a31.y;
            a.drawString(s5, i19, i20);
            SkipNode a32 = SkipListCanvas.skipNode;
            this.draw(a, a32);
            this.draw(a);
            int i21 = 2;
            while(true)
            {
                int i22 = SkipListCanvas.no_of_levels;
                if(i21 > i22)
                {
                    break;
                }
                else
                {
                    this.draw(a, i21);
                    int i23 = i21 + 1;
                    i21 = i23;
                }
            }
            com.cim.AIA.Node a33 = this.ptr;
            if(a33 != null)
            {
                com.cim.AIA.Node a34 = this.ptr;
                int i24 = a34.getX();
                com.cim.AIA.Node a35 = this.ptr;
                int i25 = a35.getWidth();
                int i26 = i24 + i25;
                int i27 = i26 + 10;
                com.cim.AIA.Node a36 = this.ptr;
                int i28 = a36.getY();
                int i29 = i28 + 40;
                com.cim.AIA.Node a37 = this.ptr;
                int i30 = a37.getX();
                com.cim.AIA.Node a38 = this.ptr;
                int i31 = a38.getWidth();
                int i32 = i30 + i31;
                com.cim.AIA.Node a39 = this.ptr;
                int i33 = a39.getY();
                com.cim.AIA.Node a40 = this.ptr;
                int i34 = a40.getHeight();
                int i35 = i33 + i34;
                com.cim.AIA.Line a41 = new com.cim.AIA.Line(i27, i29, i32, i35);
                String s6 = SkipListCanvas.PTR_LABEL;
                a41.setLabel(s6);
                a41.setDistanceFromStartForArrowHead(-3);
                a41.setDistanceFromStartForLabel(-1);
                java.awt.FontMetrics a42 = a.getFontMetrics();
                String s7 = SkipListCanvas.PTR_LABEL;
                int i36 = a42.stringWidth(s7);
                int i37 = 0 - i36;
                int i38 = i37 / 2;
                a41.setXLabelOffset(i38);
                java.awt.FontMetrics a43 = a.getFontMetrics();
                int i39 = a43.getHeight();
                a41.setYLabelOffset(i39);
                a41.showArrowHead(true);
                java.awt.Color a44 = SkipListCanvas.POINTER_COLOR;
                a41.setColor(a44);
                a41.draw(a);
            }
            com.cim.AIA.Node a45 = this.tailptr;
            if(a45 != null)
            {
                com.cim.AIA.Node a46 = this.tailptr;
                int i40 = a46.getX();
                int i41 = i40 - 10;
                com.cim.AIA.Node a47 = this.tailptr;
                int i42 = a47.getY();
                int i43 = i42 + 40;
                com.cim.AIA.Node a48 = this.tailptr;
                int i44 = a48.getX();
                com.cim.AIA.Node a49 = this.tailptr;
                int i45 = a49.getY();
                com.cim.AIA.Node a50 = this.tailptr;
                int i46 = a50.getHeight();
                int i47 = i45 + i46;
                com.cim.AIA.Line a51 = new com.cim.AIA.Line(i41, i43, i44, i47);
                String s8 = SkipListCanvas.TAILPTR_LABEL;
                a51.setLabel(s8);
                a51.setDistanceFromStartForArrowHead(-3);
                a51.setDistanceFromStartForLabel(-1);
                java.awt.FontMetrics a52 = a.getFontMetrics();
                String s9 = SkipListCanvas.TAILPTR_LABEL;
                int i48 = a52.stringWidth(s9);
                int i49 = 0 - i48;
                int i50 = i49 / 2;
                a51.setXLabelOffset(i50);
                java.awt.FontMetrics a53 = a.getFontMetrics();
                int i51 = a53.getHeight();
                a51.setYLabelOffset(i51);
                a51.showArrowHead(true);
                java.awt.Color a54 = SkipListCanvas.POINTER_COLOR;
                a51.setColor(a54);
                a51.draw(a);
            }
            int i52 = this.drawJ?1:0;
            if(i52 != 0)
            {
                SkipNode a55 = SkipListCanvas.skipNode;
                com.cim.AIA.Node[] a56 = a55.nodes;
                com.cim.AIA.Node a57 = a56[0];
                int i53 = a57.getX();
                int i54 = i53 - 15;
                SkipNode a58 = SkipListCanvas.skipNode;
                com.cim.AIA.Node[] a59 = a58.nodes;
                com.cim.AIA.Node a60 = a59[0];
                int i55 = a60.getY();
                int i56 = this.jlevel;
                SkipNode a61 = SkipListCanvas.skipNode;
                com.cim.AIA.Node[] a62 = a61.nodes;
                com.cim.AIA.Node a63 = a62[0];
                int i57 = a63.getHeight();
                int i58 = i56 * i57;
                int i59 = i55 - i58;
                SkipNode a64 = SkipListCanvas.skipNode;
                com.cim.AIA.Node[] a65 = a64.nodes;
                com.cim.AIA.Node a66 = a65[0];
                int i60 = a66.getHeight();
                int i61 = i60 / 2;
                int i62 = i59 - i61;
                java.awt.Point a67 = new java.awt.Point(i54, i62);
                SkipNode a68 = SkipListCanvas.skipNode;
                com.cim.AIA.Node[] a69 = a68.nodes;
                com.cim.AIA.Node a70 = a69[0];
                int i63 = a70.getX();
                SkipNode a71 = SkipListCanvas.skipNode;
                com.cim.AIA.Node[] a72 = a71.nodes;
                com.cim.AIA.Node a73 = a72[0];
                int i64 = a73.getY();
                int i65 = this.jlevel;
                SkipNode a74 = SkipListCanvas.skipNode;
                com.cim.AIA.Node[] a75 = a74.nodes;
                com.cim.AIA.Node a76 = a75[0];
                int i66 = a76.getHeight();
                int i67 = i65 * i66;
                int i68 = i64 - i67;
                SkipNode a77 = SkipListCanvas.skipNode;
                com.cim.AIA.Node[] a78 = a77.nodes;
                com.cim.AIA.Node a79 = a78[0];
                int i69 = a79.getHeight();
                int i70 = i69 / 2;
                int i71 = i68 - i70;
                java.awt.Point a80 = new java.awt.Point(i63, i71);
                com.cim.AIA.Line a81 = new com.cim.AIA.Line(a67, a80);
                a81.showArrowHead(true);
                a81.setDistanceFromStartForArrowHead(-3);
                String s10 = SkipListCanvas.J_PTR;
                a81.setLabel(s10);
                a81.setDistanceFromStartForLabel(-1);
                java.awt.Graphics a82 = this.getGraphics();
                java.awt.FontMetrics a83 = a82.getFontMetrics();
                String s11 = SkipListCanvas.J_PTR;
                int i72 = a83.stringWidth(s11);
                int i73 = -1 * i72;
                a81.setXLabelOffset(i73);
                java.awt.Graphics a84 = this.getGraphics();
                java.awt.FontMetrics a85 = a84.getFontMetrics();
                int i74 = a85.getHeight();
                int i75 = 1 * i74;
                a81.setYLabelOffset(i75);
                java.awt.Color a86 = SkipListCanvas.POINTER_COLOR;
                a81.setColor(a86);
                a81.draw(a);
            }
            int i76 = this.drawI?1:0;
            if(i76 != 0)
            {
                SkipNode a87 = SkipListCanvas.skipNode;
                com.cim.AIA.Node[] a88 = a87.nodes;
                com.cim.AIA.Node a89 = a88[0];
                int i77 = a89.getX();
                int i78 = i77 - 15;
                SkipNode a90 = SkipListCanvas.skipNode;
                com.cim.AIA.Node[] a91 = a90.nodes;
                com.cim.AIA.Node a92 = a91[0];
                int i79 = a92.getY();
                int i80 = this.ilevel;
                SkipNode a93 = SkipListCanvas.skipNode;
                com.cim.AIA.Node[] a94 = a93.nodes;
                com.cim.AIA.Node a95 = a94[0];
                int i81 = a95.getHeight();
                int i82 = i80 * i81;
                int i83 = i79 - i82;
                SkipNode a96 = SkipListCanvas.skipNode;
                com.cim.AIA.Node[] a97 = a96.nodes;
                com.cim.AIA.Node a98 = a97[0];
                int i84 = a98.getHeight();
                int i85 = i84 / 2;
                int i86 = i83 - i85;
                java.awt.Point a99 = new java.awt.Point(i78, i86);
                SkipNode a100 = SkipListCanvas.skipNode;
                com.cim.AIA.Node[] a101 = a100.nodes;
                com.cim.AIA.Node a102 = a101[0];
                int i87 = a102.getX();
                SkipNode a103 = SkipListCanvas.skipNode;
                com.cim.AIA.Node[] a104 = a103.nodes;
                com.cim.AIA.Node a105 = a104[0];
                int i88 = a105.getY();
                int i89 = this.ilevel;
                SkipNode a106 = SkipListCanvas.skipNode;
                com.cim.AIA.Node[] a107 = a106.nodes;
                com.cim.AIA.Node a108 = a107[0];
                int i90 = a108.getHeight();
                int i91 = i89 * i90;
                int i92 = i88 - i91;
                SkipNode a109 = SkipListCanvas.skipNode;
                com.cim.AIA.Node[] a110 = a109.nodes;
                com.cim.AIA.Node a111 = a110[0];
                int i93 = a111.getHeight();
                int i94 = i93 / 2;
                int i95 = i92 - i94;
                java.awt.Point a112 = new java.awt.Point(i87, i95);
                com.cim.AIA.Line a113 = new com.cim.AIA.Line(a99, a112);
                a113.showArrowHead(true);
                a113.setDistanceFromStartForArrowHead(-3);
                String s12 = SkipListCanvas.I_PTR;
                a113.setLabel(s12);
                a113.setDistanceFromStartForLabel(-1);
                java.awt.Graphics a114 = this.getGraphics();
                java.awt.FontMetrics a115 = a114.getFontMetrics();
                String s13 = SkipListCanvas.I_PTR;
                int i96 = a115.stringWidth(s13);
                int i97 = -1 * i96;
                a113.setXLabelOffset(i97);
                java.awt.Graphics a116 = this.getGraphics();
                java.awt.FontMetrics a117 = a116.getFontMetrics();
                int i98 = a117.getHeight();
                int i99 = 1 * i98;
                a113.setYLabelOffset(i99);
                java.awt.Color a118 = SkipListCanvas.POINTER_COLOR;
                a113.setColor(a118);
                a113.draw(a);
            }
            java.awt.Point a119 = this.boxPoint;
            int i100 = a119.x;
            java.awt.Point a120 = this.boxPoint;
            int i101 = a120.y;
            a.drawRect(i100, i101, 20, 20);
            String s14 = localization.Messages.getString("SkipListCanvas.9");
            java.awt.Point a121 = this.boxPoint;
            int i102 = a121.x;
            java.awt.FontMetrics a122 = a.getFontMetrics();
            int i103 = a122.stringWidth(s14);
            int i104 = i102 - i103;
            java.awt.Point a123 = this.boxPoint;
            int i105 = a123.y;
            int i106 = i105 + 15;
            a.drawString(s14, i104, i106);
        }
        int i107 = this.drawDiff?1:0;
        label3: {
            if(i107 == 0)
            {
                break label3;
            }
            java.awt.Color a124 = SkipListCanvas.TEXT_COLOR;
            a.setColor(a124);
            java.awt.Dimension a125 = this.getParentSize();
            int i108 = a125.width;
            java.util.Random a126 = this.random;
            int i109 = a126.nextInt();
            SkipListCanvas.test = i109;
            while(true)
            {
                int i110 = SkipListCanvas.test;
                int i111 = this.maxLevel;
                if(i110 <= i111)
                {
                    break;
                }
                else
                {
                    java.util.Random a127 = this.random;
                    int i112 = a127.nextInt();
                    SkipListCanvas.test = i112;
                }
            }
            int i113 = SkipListCanvas.test;
            String s15 = String.valueOf(i113);
            int i114 = s15.length();
            int i115 = i114 - 1;
            int i116 = s15.length();
            String s16 = s15.substring(i115, i116);
            java.awt.Point a128 = this.boxPoint;
            int i117 = a128.x;
            int i118 = i117 + 10;
            java.awt.FontMetrics a129 = a.getFontMetrics();
            int i119 = a129.stringWidth(s16);
            int i120 = i119 / 2;
            int i121 = i118 - i120;
            java.awt.Point a130 = this.boxPoint;
            int i122 = a130.y;
            int i123 = i122 + 20;
            int i124 = i123 - 5;
            a.drawString(s16, i121, i124);
        }
        int i125 = this.drawFinal?1:0;
        if(i125 != 0)
        {
            int i126 = this.currentNewNodeLevel;
            String s17 = String.valueOf(i126);
            java.awt.Point a131 = this.boxPoint;
            int i127 = a131.x;
            int i128 = i127 + 10;
            java.awt.FontMetrics a132 = a.getFontMetrics();
            int i129 = this.currentNewNodeLevel;
            String s18 = String.valueOf(i129);
            int i130 = a132.stringWidth(s18);
            int i131 = i130 / 2;
            int i132 = i128 - i131;
            java.awt.Point a133 = this.boxPoint;
            int i133 = a133.y;
            int i134 = i133 + 20;
            int i135 = i134 - 5;
            a.drawString(s17, i132, i135);
        }
    }
    
    protected com.cim.AIA.MultipleTween generateTween(SkipNode a)
    {
        com.cim.AIA.MultipleTween a0 = new com.cim.AIA.MultipleTween((com.cim.AIA.Paintable)this);
        int i = 0;
        while(true)
        {
            SkipNode a1 = this.newElement;
            com.cim.AIA.Node[] a2 = a1.nodes;
            int i0 = a2.length;
            if(i >= i0)
            {
                return a0;
            }
            else
            {
                SkipNode a3 = this.newElement;
                com.cim.AIA.Node[] a4 = a3.nodes;
                com.cim.AIA.Node a5 = a4[i];
                a5.setIsVisible(false);
                SkipNode a6 = this.newElement;
                com.cim.AIA.Node[] a7 = a6.nodes;
                com.cim.AIA.Node a8 = a7[i];
                com.cim.AIA.Node[] a9 = a.nodes;
                com.cim.AIA.Node a10 = a9[i];
                java.awt.Point a11 = a10.getPosition();
                SkipNode a12 = this.newElement;
                com.cim.AIA.Node[] a13 = a12.nodes;
                com.cim.AIA.Node a14 = a13[i];
                java.awt.Point a15 = a14.getPosition();
                int i1 = this.numberOfTweenSteps;
                com.cim.AIA.MoveTween a16 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Moveable)a8, a11, a15, true, i1);
                a0.add((com.cim.AIA.Tween)a16);
                com.cim.AIA.Node[] a17 = a.nodes;
                com.cim.AIA.Node a18 = a17[i];
                SkipNode a19 = this.newElement;
                com.cim.AIA.Node[] a20 = a19.nodes;
                com.cim.AIA.Node a21 = a20[i];
                java.awt.Point a22 = a21.getPosition();
                com.cim.AIA.Node[] a23 = a.nodes;
                com.cim.AIA.Node a24 = a23[i];
                java.awt.Point a25 = a24.getPosition();
                int i2 = this.numberOfTweenSteps;
                com.cim.AIA.MoveTween a26 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Moveable)a18, a22, a25, true, i2);
                a0.add((com.cim.AIA.Tween)a26);
                int i3 = i + 1;
                i = i3;
            }
        }
    }
    
    protected com.cim.AIA.Line initialiseLine(String s, com.cim.AIA.Node a)
    {
        int i = a.getX();
        int i0 = a.getWidth();
        int i1 = i0 / 2;
        int i2 = i + i1;
        int i3 = a.getY();
        int i4 = i3 + 60;
        java.awt.Point a0 = new java.awt.Point(i2, i4);
        int i5 = a.getX();
        int i6 = a.getWidth();
        int i7 = i6 / 2;
        int i8 = i5 + i7;
        int i9 = a.getY();
        int i10 = a.getHeight();
        int i11 = i9 + i10;
        java.awt.Point a1 = new java.awt.Point(i8, i11);
        com.cim.AIA.Line a2 = new com.cim.AIA.Line(a0, a1);
        a2.setLabel(s);
        a2.setDistanceFromStartForArrowHead(-3);
        a2.setDistanceFromStartForLabel(-1);
        java.awt.Graphics a3 = this.getGraphics();
        java.awt.FontMetrics a4 = a3.getFontMetrics();
        int i12 = a4.stringWidth(s);
        int i13 = 0 - i12;
        int i14 = i13 / 2;
        a2.setXLabelOffset(i14);
        java.awt.Graphics a5 = this.getGraphics();
        java.awt.FontMetrics a6 = a5.getFontMetrics();
        int i15 = a6.getHeight();
        a2.setYLabelOffset(i15);
        a2.showArrowHead(true);
        java.awt.Color a7 = SkipListCanvas.POINTER_COLOR;
        a2.setColor(a7);
        return a2;
    }
    
    public void processRepaintEvent(com.cim.AIA.RepaintEvent a)
    {
        this.removeAllSelectables();
        Object a0 = a.paramObj;
        label0: {
            int i = 0;
            int i0 = 0;
            if(a0 == null)
            {
                break label0;
            }
            java.awt.Dimension a1 = this.getParentSize();
            int i1 = a1.width;
            int i2 = i1 / 2;
            Object a2 = a.paramObj;
            SkipList dummy = (SkipList)a2;
            SkipList a3 = (SkipList)a2;
            SkipNode a4 = a3.getHead();
            SkipListCanvas.skipNode = a4;
            int i3 = a3.getLevels();
            SkipListCanvas.no_of_levels = i3;
            int i4 = a3.isBuildMode()?1:0;
            if(i4 == 0)
            {
                this.insertElementArray = null;
            }
            else
            {
                com.cim.AIA.ElementArray a5 = a3.getInsertElementArray();
                this.insertElementArray = a5;
            }
            int i5 = a3.isSearchMode()?1:0;
            if(i5 == 0)
            {
                this.searchElementArray = null;
            }
            else
            {
                com.cim.AIA.SearchSelection a6 = com.cim.AIA.SearchSelection.getInstance();
                this.addSelectionListener((com.cim.AIA.SelectionListener)a6);
                com.cim.AIA.ElementArray a7 = a3.getSearchElementArray();
                this.searchElementArray = a7;
            }
            com.cim.AIA.ElementArray a8 = this.insertElementArray;
            label1: {
                if(a8 == null)
                {
                    i = 30;
                    break label1;
                }
                com.cim.AIA.ElementArray a9 = this.insertElementArray;
                a9.setElementWidth(20);
                com.cim.AIA.ElementArray a10 = this.insertElementArray;
                a10.setAllHaveSameWidth(true);
                java.awt.Dimension a11 = this.getParentSize();
                int i6 = a11.width;
                com.cim.AIA.ElementArray a12 = this.insertElementArray;
                int i7 = a12.getWidth();
                if(i7 >= i6)
                {
                    com.cim.AIA.ElementArray a13 = this.insertElementArray;
                    a13.setLocation(0, 30);
                }
                else
                {
                    com.cim.AIA.ElementArray a14 = this.insertElementArray;
                    com.cim.AIA.ElementArray a15 = this.insertElementArray;
                    int i8 = a15.getWidth();
                    int i9 = i8 / 2;
                    int i10 = i2 - i9;
                    a14.setLocation(i10, 30);
                }
                com.cim.AIA.ElementArray a16 = this.insertElementArray;
                int i11 = a16.getHeight();
                int i12 = i11 + 30;
                int i13 = 30 + i12;
                i = i13;
            }
            SkipNode a17 = SkipListCanvas.skipNode;
            label2: {
                int i14 = 0;
                if(a17 == null)
                {
                    i0 = i;
                    break label2;
                }
                java.awt.Dimension a18 = this.getParentSize();
                int i15 = a18.width;
                java.awt.Point a19 = new java.awt.Point(30, i);
                this.levelPoint = a19;
                java.awt.Dimension a20 = this.getParentSize();
                int i16 = a20.width;
                int i17 = 3 * i16;
                int i18 = i17 / 4;
                java.awt.Point a21 = new java.awt.Point(i18, i);
                this.dataConditionPoint = a21;
                SkipNode a22 = SkipListCanvas.skipNode;
                int i19 = a22.getHeight();
                int i20 = i + i19;
                java.awt.Point a23 = new java.awt.Point(60, i20);
                SkipNode a24 = SkipListCanvas.skipNode;
                com.cim.AIA.Node[] a25 = a24.nodes;
                com.cim.AIA.Node a26 = a25[0];
                a26.setPosition(a23);
                int i21 = 1;
                while(true)
                {
                    SkipNode a27 = SkipListCanvas.skipNode;
                    com.cim.AIA.Node[] a28 = a27.nodes;
                    int i22 = a28.length;
                    if(i21 >= i22)
                    {
                        break;
                    }
                    else
                    {
                        SkipNode a29 = SkipListCanvas.skipNode;
                        com.cim.AIA.Node[] a30 = a29.nodes;
                        com.cim.AIA.Node a31 = a30[i21];
                        SkipNode a32 = SkipListCanvas.skipNode;
                        com.cim.AIA.Node[] a33 = a32.nodes;
                        com.cim.AIA.Node a34 = a33[0];
                        int i23 = a34.getX();
                        SkipNode a35 = SkipListCanvas.skipNode;
                        com.cim.AIA.Node[] a36 = a35.nodes;
                        com.cim.AIA.Node a37 = a36[0];
                        int i24 = a37.getY();
                        SkipNode a38 = SkipListCanvas.skipNode;
                        com.cim.AIA.Node[] a39 = a38.nodes;
                        com.cim.AIA.Node a40 = a39[0];
                        int i25 = a40.getHeight();
                        int i26 = i21 * i25;
                        int i27 = i24 - i26;
                        java.awt.Point a41 = new java.awt.Point(i23, i27);
                        a31.setPosition(a41);
                        int i28 = i21 + 1;
                        i21 = i28;
                    }
                }
                SkipNode a42 = SkipListCanvas.skipNode;
                SkipNode a43 = a42;
                int i29 = 40;
                while(true)
                {
                    SkipNode[] a44 = a43.next;
                    SkipNode a45 = a44[0];
                    if(a45 == null)
                    {
                        break;
                    }
                    else
                    {
                        SkipNode[] a46 = a43.next;
                        SkipNode a47 = a46[0];
                        com.cim.AIA.Node[] a48 = a47.nodes;
                        com.cim.AIA.Node a49 = a48[0];
                        int i30 = a23.x;
                        int i31 = i30 + i29;
                        int i32 = a23.y;
                        java.awt.Point a50 = new java.awt.Point(i31, i32);
                        a49.setPosition(a50);
                        com.cim.AIA.Node[] a51 = a47.nodes;
                        com.cim.AIA.Node a52 = a51[1];
                        int i33 = a23.x;
                        int i34 = i33 + i29;
                        int i35 = a23.y;
                        com.cim.AIA.Node[] a53 = a47.nodes;
                        com.cim.AIA.Node a54 = a53[0];
                        int i36 = a54.getHeight();
                        int i37 = i35 - i36;
                        java.awt.Point a55 = new java.awt.Point(i34, i37);
                        a52.setPosition(a55);
                        int i38 = i29 + 40;
                        a43 = a47;
                        i29 = i38;
                    }
                }
                int i39 = 1;
                while(true)
                {
                    SkipNode a56 = SkipListCanvas.skipNode;
                    SkipNode[] a57 = a56.next;
                    int i40 = a57.length;
                    if(i39 >= i40)
                    {
                        break;
                    }
                    SkipNode a58 = SkipListCanvas.skipNode;
                    SkipNode a59 = a58;
                    while(true)
                    {
                        SkipNode[] a60 = a59.next;
                        SkipNode a61 = a60[i39];
                        if(a61 == null)
                        {
                            int i41 = i39 + 1;
                            i39 = i41;
                        }
                        else
                        {
                            SkipNode[] a62 = a59.next;
                            SkipNode a63 = a62[i39];
                            com.cim.AIA.Node[] a64 = a63.nodes;
                            int i42 = i39 + 1;
                            com.cim.AIA.Node a65 = a64[i42];
                            com.cim.AIA.Node[] a66 = a63.nodes;
                            com.cim.AIA.Node a67 = a66[0];
                            int i43 = a67.getX();
                            com.cim.AIA.Node[] a68 = a63.nodes;
                            com.cim.AIA.Node a69 = a68[0];
                            int i44 = a69.getY();
                            int i45 = i39 + 1;
                            com.cim.AIA.Node[] a70 = a63.nodes;
                            com.cim.AIA.Node a71 = a70[0];
                            int i46 = a71.getHeight();
                            int i47 = i45 * i46;
                            int i48 = i44 - i47;
                            java.awt.Point a72 = new java.awt.Point(i43, i48);
                            a65.setPosition(a72);
                            a59 = a63;
                        }
                    }
                }
                com.cim.AIA.Node a73 = a3.getPtrNode();
                this.ptr = a73;
                com.cim.AIA.Node a74 = a3.getTailPtrNode();
                this.tailptr = a74;
                int i49 = a3.doDrawBoxLevel()?1:0;
                this.drawLevel = i49 != 0;
                int i50 = a3.doDrawBoxFinalLevel()?1:0;
                this.drawFinal = i50 != 0;
                int i51 = a3.getDataCondition()?1:0;
                this.dataCondition = i51 != 0;
                int i52 = a3.doDisplayDataCondition()?1:0;
                this.displayDataCondition = i52 != 0;
                int i53 = a3.doDrawIPointer()?1:0;
                this.drawI = i53 != 0;
                int i54 = a3.getIPointer();
                this.ilevel = i54;
                int i55 = a3.doDrawJPointer()?1:0;
                this.drawJ = i55 != 0;
                int i56 = a3.getJPointer();
                this.jlevel = i56;
                int i57 = a3.getNewNodeLevel();
                this.currentNewNodeLevel = i57;
                int i58 = a3.getMaxLevel();
                this.maxLevel = i58;
                int i59 = a3.doDrawNewElement()?1:0;
                this.drawElement = i59 != 0;
                int i60 = a3.doRunTween()?1:0;
                this.runTween = i60 != 0;
                java.awt.Point a75 = this.boxPoint;
                SkipNode a76 = SkipListCanvas.skipNode;
                int i61 = a76.getHeight();
                int i62 = i + i61;
                int i63 = i62 + 80;
                a75.setLocation(130, i63);
                SkipNode a77 = SkipListCanvas.skipNode;
                int i64 = a77.getHeight();
                int i65 = i64 + 110;
                int i66 = i + i65;
                com.cim.AIA.Node a78 = a3.getDataNode();
                if(a78 == null)
                {
                    this.dataLine = null;
                }
                else
                {
                    String s = SkipListCanvas.DATA_LABEL;
                    com.cim.AIA.Node a79 = a3.getDataNode();
                    com.cim.AIA.Line a80 = this.initialiseLine(s, a79);
                    this.dataLine = a80;
                }
                Integer a81 = a3.getDataItem();
                if(a81 != null)
                {
                    Integer a82 = a3.getDataItem();
                    String s0 = String.valueOf((Object)a82);
                    com.cim.AIA.Node a83 = new com.cim.AIA.Node((Object)s0, 0);
                    this.dataItemNode = a83;
                    com.cim.AIA.Node a84 = this.dataItemNode;
                    java.awt.Color a85 = this.getBackground();
                    a84.setBackgroundColor(a85);
                    com.cim.AIA.Node a86 = this.dataItemNode;
                    java.awt.Point a87 = this.dataConditionPoint;
                    int i67 = a87.x;
                    java.awt.Graphics a88 = this.getGraphics();
                    java.awt.FontMetrics a89 = a88.getFontMetrics();
                    String s1 = SkipListCanvas.DATA_COND_LABEL;
                    int i68 = a89.stringWidth(s1);
                    int i69 = i68 / 4;
                    int i70 = i67 - i69;
                    java.awt.Point a90 = this.dataConditionPoint;
                    int i71 = a90.y;
                    java.awt.Point a91 = new java.awt.Point(i70, i71);
                    a86.setPosition(a91);
                }
                Integer a92 = a3.getNextData();
                if(a92 != null)
                {
                    Integer a93 = a3.getNextData();
                    String s2 = String.valueOf((Object)a93);
                    com.cim.AIA.Node a94 = new com.cim.AIA.Node((Object)s2, 0);
                    this.nextDataNode = a94;
                    com.cim.AIA.Node a95 = this.nextDataNode;
                    a95.setWidth(20);
                    com.cim.AIA.Node a96 = this.nextDataNode;
                    java.awt.Color a97 = this.getBackground();
                    a96.setBackgroundColor(a97);
                    com.cim.AIA.Node a98 = this.nextDataNode;
                    java.awt.Point a99 = this.dataConditionPoint;
                    int i72 = a99.x;
                    java.awt.Graphics a100 = this.getGraphics();
                    java.awt.FontMetrics a101 = a100.getFontMetrics();
                    String s3 = SkipListCanvas.DATA_COND_LABEL;
                    int i73 = a101.stringWidth(s3);
                    int i74 = i73 / 4;
                    int i75 = 3 * i74;
                    int i76 = i72 - i75;
                    java.awt.Point a102 = this.dataConditionPoint;
                    int i77 = a102.y;
                    java.awt.Point a103 = new java.awt.Point(i76, i77);
                    a98.setPosition(a103);
                }
                int i78 = a3.getNextNull()?1:0;
                label4: {
                    label3: {
                        if(i78 == 0)
                        {
                            break label3;
                        }
                        String s4 = localization.Messages.getString("SkipListCanvas.10");
                        com.cim.AIA.Node a104 = new com.cim.AIA.Node((Object)s4, 0);
                        this.dataConditionNode = a104;
                        com.cim.AIA.Node a105 = this.dataConditionNode;
                        java.awt.Graphics a106 = this.getGraphics();
                        java.awt.FontMetrics a107 = a106.getFontMetrics();
                        String s5 = localization.Messages.getString("SkipListCanvas.11");
                        int i79 = a107.stringWidth(s5);
                        a105.setWidth(i79);
                        com.cim.AIA.Node a108 = this.dataConditionNode;
                        java.awt.Point a109 = this.dataConditionPoint;
                        int i80 = a109.x;
                        java.awt.Point a110 = this.dataConditionPoint;
                        int i81 = a110.y;
                        com.cim.AIA.Node a111 = this.dataConditionNode;
                        int i82 = a111.getHeight();
                        int i83 = i81 - i82;
                        java.awt.Point a112 = new java.awt.Point(i80, i83);
                        a108.setPosition(a112);
                        String s6 = localization.Messages.getString("SkipListCanvas.12");
                        com.cim.AIA.Node a113 = new com.cim.AIA.Node((Object)s6, 0);
                        this.nextDataNode = a113;
                        com.cim.AIA.Node a114 = this.nextDataNode;
                        java.awt.Graphics a115 = this.getGraphics();
                        java.awt.FontMetrics a116 = a115.getFontMetrics();
                        String s7 = localization.Messages.getString("SkipListCanvas.13");
                        int i84 = a116.stringWidth(s7);
                        a114.setWidth(i84);
                        com.cim.AIA.Node a117 = this.nextDataNode;
                        java.awt.Color a118 = this.getBackground();
                        a117.setBackgroundColor(a118);
                        com.cim.AIA.Node a119 = this.nextDataNode;
                        java.awt.Point a120 = this.dataConditionPoint;
                        int i85 = a120.x;
                        java.awt.Graphics a121 = this.getGraphics();
                        java.awt.FontMetrics a122 = a121.getFontMetrics();
                        String s8 = SkipListCanvas.DATA_COND_LABEL;
                        int i86 = a122.stringWidth(s8);
                        int i87 = i86 / 4;
                        int i88 = 3 * i87;
                        int i89 = i85 - i88;
                        java.awt.Point a123 = this.dataConditionPoint;
                        int i90 = a123.y;
                        java.awt.Point a124 = new java.awt.Point(i89, i90);
                        a119.setPosition(a124);
                        break label4;
                    }
                    int i91 = this.dataCondition?1:0;
                    if(i91 == 0)
                    {
                        String s9 = localization.Messages.getString("SkipListCanvas.15");
                        com.cim.AIA.Node a125 = new com.cim.AIA.Node((Object)s9, 0);
                        this.dataConditionNode = a125;
                        com.cim.AIA.Node a126 = this.dataConditionNode;
                        a126.setWidth(20);
                        com.cim.AIA.Node a127 = this.dataConditionNode;
                        java.awt.Color a128 = SkipListCanvas.NO_COLOR;
                        a127.setBackgroundColor(a128);
                        com.cim.AIA.Node a129 = this.dataConditionNode;
                        java.awt.Point a130 = this.dataConditionPoint;
                        int i92 = a130.x;
                        java.awt.Point a131 = this.dataConditionPoint;
                        int i93 = a131.y;
                        com.cim.AIA.Node a132 = this.dataConditionNode;
                        int i94 = a132.getHeight();
                        int i95 = i93 - i94;
                        java.awt.Point a133 = new java.awt.Point(i92, i95);
                        a129.setPosition(a133);
                    }
                    else
                    {
                        String s10 = localization.Messages.getString("SkipListCanvas.14");
                        com.cim.AIA.Node a134 = new com.cim.AIA.Node((Object)s10, 0);
                        this.dataConditionNode = a134;
                        com.cim.AIA.Node a135 = this.dataConditionNode;
                        a135.setWidth(20);
                        com.cim.AIA.Node a136 = this.dataConditionNode;
                        java.awt.Color a137 = SkipListCanvas.YES_COLOR;
                        a136.setBackgroundColor(a137);
                        com.cim.AIA.Node a138 = this.dataConditionNode;
                        java.awt.Point a139 = this.dataConditionPoint;
                        int i96 = a139.x;
                        java.awt.Point a140 = this.dataConditionPoint;
                        int i97 = a140.y;
                        com.cim.AIA.Node a141 = this.dataConditionNode;
                        int i98 = a141.getHeight();
                        int i99 = i97 - i98;
                        java.awt.Point a142 = new java.awt.Point(i96, i99);
                        a138.setPosition(a142);
                    }
                }
                int i100 = this.drawElement?1:0;
                label5: {
                    if(i100 == 0)
                    {
                        i14 = i66;
                        break label5;
                    }
                    SkipNode a143 = a3.getNewElement();
                    this.newElement = a143;
                    SkipNode a144 = this.newElement;
                    com.cim.AIA.Node[] a145 = a144.nodes;
                    com.cim.AIA.Node a146 = a145[0];
                    java.awt.Point a147 = this.boxPoint;
                    int i101 = a147.x;
                    int i102 = i101 + 30;
                    java.awt.Point a148 = this.boxPoint;
                    int i103 = a148.y;
                    SkipNode a149 = this.newElement;
                    int i104 = a149.getHeight();
                    int i105 = i103 + i104;
                    int i106 = i105 - 30;
                    java.awt.Point a150 = new java.awt.Point(i102, i106);
                    a146.setPosition(a150);
                    int i107 = 1;
                    while(true)
                    {
                        SkipNode a151 = this.newElement;
                        com.cim.AIA.Node[] a152 = a151.nodes;
                        int i108 = a152.length;
                        if(i107 >= i108)
                        {
                            break;
                        }
                        else
                        {
                            SkipNode a153 = this.newElement;
                            com.cim.AIA.Node[] a154 = a153.nodes;
                            int i109 = i107 - 1;
                            com.cim.AIA.Node a155 = a154[i109];
                            java.awt.Point a156 = a155.getPosition();
                            SkipNode a157 = this.newElement;
                            com.cim.AIA.Node[] a158 = a157.nodes;
                            com.cim.AIA.Node a159 = a158[i107];
                            int i110 = a156.x;
                            int i111 = a156.y;
                            SkipNode a160 = this.newElement;
                            com.cim.AIA.Node[] a161 = a160.nodes;
                            int i112 = i107 - 1;
                            com.cim.AIA.Node a162 = a161[i112];
                            int i113 = a162.getHeight();
                            int i114 = i111 - i113;
                            java.awt.Point a163 = new java.awt.Point(i110, i114);
                            a159.setPosition(a163);
                            int i115 = i107 + 1;
                            i107 = i115;
                        }
                    }
                    SkipNode a164 = this.newElement;
                    com.cim.AIA.Node[] a165 = a164.nodes;
                    com.cim.AIA.Node a166 = a165[0];
                    int i116 = a166.getY();
                    int i117 = i116 + 10;
                    i14 = i117;
                }
                int i118 = a3.doRunTween()?1:0;
                if(i118 != 0)
                {
                    SkipNode a167 = a3.getFinalNewElement();
                    com.cim.AIA.MultipleTween a168 = this.generateTween(a167);
                    this.addTween((com.cim.AIA.Tween)a168);
                }
                com.cim.AIA.MultipleTween a169 = this.tweens;
                int i119 = a169.getNumberOfTweens();
                if(i119 <= 0)
                {
                    i0 = i14;
                }
                else
                {
                    com.cim.AIA.MultipleTween a170 = this.tweens;
                    a170.run();
                    i0 = i14;
                }
            }
            com.cim.AIA.ElementArray a171 = this.searchElementArray;
            label6: {
                if(a171 == null)
                {
                    break label6;
                }
                com.cim.AIA.ElementArray a172 = this.searchElementArray;
                a172.setElementWidth(20);
                com.cim.AIA.ElementArray a173 = this.searchElementArray;
                a173.setAllHaveSameWidth(true);
                java.awt.Dimension a174 = this.getParentSize();
                int i120 = a174.width;
                com.cim.AIA.ElementArray a175 = this.searchElementArray;
                int i121 = a175.getWidth();
                if(i121 >= i120)
                {
                    com.cim.AIA.ElementArray a176 = this.searchElementArray;
                    a176.setLocation(0, i0);
                }
                else
                {
                    com.cim.AIA.ElementArray a177 = this.searchElementArray;
                    com.cim.AIA.ElementArray a178 = this.searchElementArray;
                    int i122 = a178.getWidth();
                    int i123 = i122 / 2;
                    int i124 = i2 - i123;
                    a177.setLocation(i124, i0);
                }
                com.cim.AIA.ElementArray a179 = this.searchElementArray;
                int i125 = a179.getHeight();
                com.cim.AIA.ElementArray a180 = this.searchElementArray;
                this.addSelectable((com.cim.AIA.Selectable)a180);
            }
            int i126 = this.drawLevel?1:0;
            if(i126 == 0)
            {
                break label0;
            }
            int i127 = 0;
            while(true)
            {
                if(i127 >= 10)
                {
                    break;
                }
                this.drawDiff = true;
                this.repaint();
                try
                {
                    Thread.sleep(60L);
                }
                catch(InterruptedException a181)
                {
                    java.io.PrintStream a182 = System.out;
                    a182.println((Object)a181);
                }
                int i128 = i127 + 1;
                i127 = i128;
            }
            this.drawDiff = false;
            this.drawFinal = true;
        }
        this.repaint();
    }
    
    protected void handleColorSelection(com.cim.AIA.ColorSelection a)
    {
    }
    
    protected void handleFontSelection(com.cim.AIA.FontSelection a)
    {
    }
    
    public void draw(java.awt.Graphics a, SkipNode a0)
    {
        com.cim.AIA.Node[] a1 = a0.nodes;
        com.cim.AIA.Node a2 = a1[0];
        java.awt.Point a3 = a2.getPosition();
        java.awt.Point a4 = new java.awt.Point(a3);
        com.cim.AIA.Node[] a5 = a0.nodes;
        com.cim.AIA.Node a6 = a5[0];
        a6.draw(a);
        int i = 1;
        while(true)
        {
            com.cim.AIA.Node[] a7 = a0.nodes;
            int i0 = a7.length;
            if(i >= i0)
            {
                return;
            }
            com.cim.AIA.Node[] a8 = a0.nodes;
            com.cim.AIA.Node a9 = a8[i];
            a9.draw(a);
            SkipNode[] a10 = a0.next;
            int i1 = i - 1;
            SkipNode a11 = a10[i1];
            label0: {
                if(a11 == null)
                {
                    break label0;
                }
                com.cim.AIA.Line[] a12 = a0.nextNodesLines;
                com.cim.AIA.Line a13 = a12[i];
                com.cim.AIA.Node[] a14 = a0.nodes;
                com.cim.AIA.Node a15 = a14[i];
                int i2 = a15.getX();
                com.cim.AIA.Node[] a16 = a0.nodes;
                com.cim.AIA.Node a17 = a16[i];
                int i3 = a17.getWidth();
                int i4 = i3 / 2;
                int i5 = i2 + i4;
                com.cim.AIA.Node[] a18 = a0.nodes;
                com.cim.AIA.Node a19 = a18[i];
                int i6 = a19.getY();
                com.cim.AIA.Node[] a20 = a0.nodes;
                com.cim.AIA.Node a21 = a20[i];
                int i7 = a21.getHeight();
                int i8 = i7 / 2;
                int i9 = i6 + i8;
                java.awt.Point a22 = new java.awt.Point(i5, i9);
                a13.setStartPosition(a22);
                com.cim.AIA.Line[] a23 = a0.nextNodesLines;
                com.cim.AIA.Line a24 = a23[i];
                SkipNode[] a25 = a0.next;
                int i10 = i - 1;
                SkipNode a26 = a25[i10];
                com.cim.AIA.Node[] a27 = a26.nodes;
                com.cim.AIA.Node a28 = a27[0];
                int i11 = a28.getX();
                SkipNode[] a29 = a0.next;
                int i12 = i - 1;
                SkipNode a30 = a29[i12];
                com.cim.AIA.Node[] a31 = a30.nodes;
                com.cim.AIA.Node a32 = a31[0];
                int i13 = a32.getY();
                com.cim.AIA.Node[] a33 = a0.nodes;
                com.cim.AIA.Node a34 = a33[i];
                int i14 = a34.getHeight();
                int i15 = i * i14;
                int i16 = i13 - i15;
                com.cim.AIA.Node[] a35 = a0.nodes;
                com.cim.AIA.Node a36 = a35[i];
                int i17 = a36.getHeight();
                int i18 = i17 / 2;
                int i19 = i16 + i18;
                java.awt.Point a37 = new java.awt.Point(i11, i19);
                a24.setEndPosition(a37);
                com.cim.AIA.Line[] a38 = a0.nextNodesLines;
                com.cim.AIA.Line a39 = a38[i];
                a39.setDistanceFromStartForArrowHead(-3);
                com.cim.AIA.Line[] a40 = a0.nextNodesLines;
                com.cim.AIA.Line a41 = a40[i];
                a41.showArrowHead(true);
                com.cim.AIA.Line[] a42 = a0.nextNodesLines;
                com.cim.AIA.Line a43 = a42[i];
                java.awt.Color a44 = a43.getColor();
                java.awt.Color a45 = java.awt.Color.red;
                label1: {
                    if(a44 == a45)
                    {
                        break label1;
                    }
                    com.cim.AIA.Line[] a46 = a0.nextNodesLines;
                    com.cim.AIA.Line a47 = a46[i];
                    java.awt.Color a48 = a47.getColor();
                    java.awt.Color a49 = java.awt.Color.white;
                    if(a48 != a49)
                    {
                        com.cim.AIA.Line[] a50 = a0.nextNodesLines;
                        com.cim.AIA.Line a51 = a50[i];
                        java.awt.Color a52 = SkipListCanvas.TEXT_COLOR;
                        a51.setColor(a52);
                    }
                }
                com.cim.AIA.Line[] a53 = a0.nextNodesLines;
                com.cim.AIA.Line a54 = a53[i];
                java.awt.Color a55 = a54.getColor();
                java.awt.Color a56 = java.awt.Color.white;
                if(a55 == a56)
                {
                    com.cim.AIA.Line[] a57 = a0.nextNodesLines;
                    com.cim.AIA.Line a58 = a57[i];
                    java.awt.Color a59 = this.getBackground();
                    a58.setColor(a59);
                }
                com.cim.AIA.Line[] a60 = a0.nextNodesLines;
                com.cim.AIA.Line a61 = a60[i];
                a61.draw(a);
            }
            int i20 = i + 1;
            i = i20;
        }
    }
    
    public void draw(java.awt.Graphics a)
    {
        SkipNode a0 = SkipListCanvas.skipNode;
        SkipNode[] a1 = a0.next;
        SkipNode a2 = a1[0];
        SkipNode a3 = SkipListCanvas.skipNode;
        com.cim.AIA.Node[] a4 = a3.nodes;
        com.cim.AIA.Node a5 = a4[0];
        int i = a5.getX();
        int i0 = i + 40;
        SkipNode a6 = SkipListCanvas.skipNode;
        com.cim.AIA.Node[] a7 = a6.nodes;
        com.cim.AIA.Node a8 = a7[0];
        int i1 = a8.getY();
        java.awt.Point a9 = new java.awt.Point(i0, i1);
        SkipNode a10 = a2;
        while(true)
        {
            if(a10 == null)
            {
                return;
            }
            com.cim.AIA.Node[] a11 = a10.nodes;
            com.cim.AIA.Node a12 = a11[0];
            a12.draw(a);
            int i2 = a9.x;
            int i3 = a9.y;
            com.cim.AIA.Node[] a13 = a10.nodes;
            com.cim.AIA.Node a14 = a13[0];
            int i4 = a14.getHeight();
            int i5 = i3 - i4;
            a9.move(i2, i5);
            com.cim.AIA.Node[] a15 = a10.nodes;
            com.cim.AIA.Node a16 = a15[1];
            a16.draw(a);
            SkipNode[] a17 = a10.next;
            SkipNode a18 = a17[0];
            label0: {
                if(a18 == null)
                {
                    break label0;
                }
                com.cim.AIA.Line[] a19 = a10.nextNodesLines;
                com.cim.AIA.Line a20 = a19[1];
                int i6 = a9.x;
                com.cim.AIA.Node[] a21 = a10.nodes;
                com.cim.AIA.Node a22 = a21[0];
                int i7 = a22.getWidth();
                int i8 = i7 / 2;
                int i9 = i6 + i8;
                int i10 = a9.y;
                com.cim.AIA.Node[] a23 = a10.nodes;
                com.cim.AIA.Node a24 = a23[0];
                int i11 = a24.getHeight();
                int i12 = i11 / 2;
                int i13 = i10 + i12;
                java.awt.Point a25 = new java.awt.Point(i9, i13);
                a20.setStartPosition(a25);
                com.cim.AIA.Line[] a26 = a10.nextNodesLines;
                com.cim.AIA.Line a27 = a26[1];
                int i14 = a9.x;
                int i15 = i14 + 40;
                int i16 = a9.y;
                com.cim.AIA.Node[] a28 = a10.nodes;
                com.cim.AIA.Node a29 = a28[0];
                int i17 = a29.getHeight();
                int i18 = i17 / 2;
                int i19 = i16 + i18;
                java.awt.Point a30 = new java.awt.Point(i15, i19);
                a27.setEndPosition(a30);
                com.cim.AIA.Line[] a31 = a10.nextNodesLines;
                com.cim.AIA.Line a32 = a31[1];
                a32.setDistanceFromStartForArrowHead(-3);
                com.cim.AIA.Line[] a33 = a10.nextNodesLines;
                com.cim.AIA.Line a34 = a33[1];
                a34.showArrowHead(true);
                com.cim.AIA.Line[] a35 = a10.nextNodesLines;
                com.cim.AIA.Line a36 = a35[1];
                java.awt.Color a37 = a36.getColor();
                java.awt.Color a38 = java.awt.Color.red;
                label1: {
                    if(a37 == a38)
                    {
                        break label1;
                    }
                    com.cim.AIA.Line[] a39 = a10.nextNodesLines;
                    com.cim.AIA.Line a40 = a39[1];
                    java.awt.Color a41 = a40.getColor();
                    java.awt.Color a42 = java.awt.Color.white;
                    if(a41 != a42)
                    {
                        com.cim.AIA.Line[] a43 = a10.nextNodesLines;
                        com.cim.AIA.Line a44 = a43[1];
                        java.awt.Color a45 = SkipListCanvas.TEXT_COLOR;
                        a44.setColor(a45);
                    }
                }
                com.cim.AIA.Line[] a46 = a10.nextNodesLines;
                com.cim.AIA.Line a47 = a46[1];
                java.awt.Color a48 = a47.getColor();
                java.awt.Color a49 = java.awt.Color.white;
                if(a48 == a49)
                {
                    com.cim.AIA.Line[] a50 = a10.nextNodesLines;
                    com.cim.AIA.Line a51 = a50[1];
                    java.awt.Color a52 = this.getBackground();
                    a51.setColor(a52);
                }
                com.cim.AIA.Line[] a53 = a10.nextNodesLines;
                com.cim.AIA.Line a54 = a53[1];
                a54.draw(a);
            }
            SkipNode[] a55 = a10.next;
            SkipNode a56 = a55[0];
            int i20 = a9.x;
            int i21 = i20 + 40;
            SkipNode a57 = SkipListCanvas.skipNode;
            com.cim.AIA.Node[] a58 = a57.nodes;
            com.cim.AIA.Node a59 = a58[0];
            int i22 = a59.getY();
            a9.move(i21, i22);
            if(a56 != null)
            {
                a10 = a56;
            }
            else
            {
                a10 = null;
            }
        }
    }
    
    public void draw(java.awt.Graphics a, int i)
    {
        SkipNode a0 = null;
        java.awt.Point a1 = null;
        SkipNode a2 = SkipListCanvas.skipNode;
        SkipNode[] a3 = a2.next;
        int i0 = i - 1;
        SkipNode a4 = a3[i0];
        java.awt.Point a5 = new java.awt.Point(0, 0);
        if(a4 == null)
        {
            a0 = a4;
            a1 = a5;
        }
        else
        {
            com.cim.AIA.Node[] a6 = a4.nodes;
            com.cim.AIA.Node a7 = a6[0];
            int i1 = a7.getX();
            SkipNode a8 = SkipListCanvas.skipNode;
            com.cim.AIA.Node[] a9 = a8.nodes;
            com.cim.AIA.Node a10 = a9[0];
            int i2 = a10.getY();
            SkipNode a11 = SkipListCanvas.skipNode;
            com.cim.AIA.Node[] a12 = a11.nodes;
            com.cim.AIA.Node a13 = a12[0];
            int i3 = a13.getHeight();
            int i4 = i * i3;
            int i5 = i2 - i4;
            java.awt.Point a14 = new java.awt.Point(i1, i5);
            a0 = a4;
            a1 = a14;
        }
        while(true)
        {
            java.awt.Point a15 = null;
            java.awt.Point a16 = null;
            java.awt.Point a17 = a1;
            if(a0 == null)
            {
                return;
            }
            else
            {
                a15 = a17;
            }
            com.cim.AIA.Node[] a18 = a0.nodes;
            java.awt.Point a19 = a15;
            com.cim.AIA.Node a20 = a18[i];
            java.awt.Point a21 = a19;
            a20.draw(a);
            java.awt.Point a22 = a21;
            SkipNode[] a23 = a0.next;
            java.awt.Point a24 = a22;
            int i6 = i - 1;
            SkipNode a25 = a23[i6];
            java.awt.Point a26 = a24;
            label0: {
                java.awt.Point a27 = null;
                java.awt.Point a28 = null;
                java.awt.Point a29 = null;
                java.awt.Point a30 = a26;
                java.awt.Point a31 = a26;
                if(a25 == null)
                {
                    a16 = a31;
                    break label0;
                }
                else
                {
                    a27 = a30;
                }
                com.cim.AIA.Line[] a32 = a0.nextNodesLines;
                java.awt.Point a33 = a27;
                com.cim.AIA.Line a34 = a32[i];
                java.awt.Point a35 = a33;
                java.awt.Point a36 = a35;
                com.cim.AIA.Node[] a37 = a0.nodes;
                java.awt.Point a38 = a36;
                com.cim.AIA.Node a39 = a37[i];
                java.awt.Point a40 = a38;
                int i7 = a39.getX();
                java.awt.Point a41 = a40;
                com.cim.AIA.Node[] a42 = a0.nodes;
                java.awt.Point a43 = a41;
                com.cim.AIA.Node a44 = a42[i];
                java.awt.Point a45 = a43;
                int i8 = a44.getWidth();
                java.awt.Point a46 = a45;
                int i9 = i8 / 2;
                int i10 = i7 + i9;
                com.cim.AIA.Node[] a47 = a0.nodes;
                java.awt.Point a48 = a46;
                com.cim.AIA.Node a49 = a47[i];
                java.awt.Point a50 = a48;
                int i11 = a49.getY();
                java.awt.Point a51 = a50;
                com.cim.AIA.Node[] a52 = a0.nodes;
                java.awt.Point a53 = a51;
                com.cim.AIA.Node a54 = a52[i];
                java.awt.Point a55 = a53;
                int i12 = a54.getHeight();
                java.awt.Point a56 = a55;
                int i13 = i12 / 2;
                int i14 = i11 + i13;
                java.awt.Point a57 = new java.awt.Point(i10, i14);
                java.awt.Point a58 = a56;
                a34.setStartPosition(a57);
                java.awt.Point a59 = a58;
                com.cim.AIA.Line[] a60 = a0.nextNodesLines;
                java.awt.Point a61 = a59;
                com.cim.AIA.Line a62 = a60[i];
                java.awt.Point a63 = a61;
                java.awt.Point a64 = a63;
                SkipNode[] a65 = a0.next;
                java.awt.Point a66 = a64;
                int i15 = i - 1;
                SkipNode a67 = a65[i15];
                java.awt.Point a68 = a66;
                com.cim.AIA.Node[] a69 = a67.nodes;
                java.awt.Point a70 = a68;
                com.cim.AIA.Node a71 = a69[0];
                java.awt.Point a72 = a70;
                int i16 = a71.getX();
                java.awt.Point a73 = a72;
                SkipNode[] a74 = a0.next;
                java.awt.Point a75 = a73;
                int i17 = i - 1;
                SkipNode a76 = a74[i17];
                java.awt.Point a77 = a75;
                com.cim.AIA.Node[] a78 = a76.nodes;
                java.awt.Point a79 = a77;
                com.cim.AIA.Node a80 = a78[0];
                java.awt.Point a81 = a79;
                int i18 = a80.getY();
                java.awt.Point a82 = a81;
                com.cim.AIA.Node[] a83 = a0.nodes;
                java.awt.Point a84 = a82;
                com.cim.AIA.Node a85 = a83[i];
                java.awt.Point a86 = a84;
                int i19 = a85.getHeight();
                java.awt.Point a87 = a86;
                int i20 = i * i19;
                int i21 = i18 - i20;
                com.cim.AIA.Node[] a88 = a0.nodes;
                java.awt.Point a89 = a87;
                com.cim.AIA.Node a90 = a88[i];
                java.awt.Point a91 = a89;
                int i22 = a90.getHeight();
                java.awt.Point a92 = a91;
                int i23 = i22 / 2;
                int i24 = i21 + i23;
                java.awt.Point a93 = new java.awt.Point(i16, i24);
                java.awt.Point a94 = a92;
                a62.setEndPosition(a93);
                java.awt.Point a95 = a94;
                com.cim.AIA.Line[] a96 = a0.nextNodesLines;
                java.awt.Point a97 = a95;
                com.cim.AIA.Line a98 = a96[i];
                java.awt.Point a99 = a97;
                a98.setDistanceFromStartForArrowHead(-3);
                java.awt.Point a100 = a99;
                com.cim.AIA.Line[] a101 = a0.nextNodesLines;
                java.awt.Point a102 = a100;
                com.cim.AIA.Line a103 = a101[i];
                java.awt.Point a104 = a102;
                a103.showArrowHead(true);
                java.awt.Point a105 = a104;
                com.cim.AIA.Line[] a106 = a0.nextNodesLines;
                java.awt.Point a107 = a105;
                com.cim.AIA.Line a108 = a106[i];
                java.awt.Point a109 = a107;
                java.awt.Color a110 = a108.getColor();
                java.awt.Point a111 = a109;
                java.awt.Color a112 = java.awt.Color.red;
                java.awt.Point a113 = a111;
                label1: {
                    java.awt.Point a114 = null;
                    java.awt.Point a115 = a113;
                    java.awt.Point a116 = a113;
                    if(a110 == a112)
                    {
                        a28 = a116;
                        break label1;
                    }
                    else
                    {
                        a114 = a115;
                    }
                    com.cim.AIA.Line[] a117 = a0.nextNodesLines;
                    java.awt.Point a118 = a114;
                    com.cim.AIA.Line a119 = a117[i];
                    java.awt.Point a120 = a118;
                    java.awt.Color a121 = a119.getColor();
                    java.awt.Point a122 = a120;
                    java.awt.Color a123 = java.awt.Color.white;
                    java.awt.Point a124 = a122;
                    java.awt.Point a125 = a124;
                    java.awt.Point a126 = a124;
                    if(a121 == a123)
                    {
                        a28 = a126;
                    }
                    else
                    {
                        java.awt.Point a127 = a125;
                        com.cim.AIA.Line[] a128 = a0.nextNodesLines;
                        java.awt.Point a129 = a127;
                        com.cim.AIA.Line a130 = a128[i];
                        java.awt.Point a131 = a129;
                        java.awt.Color a132 = SkipListCanvas.TEXT_COLOR;
                        java.awt.Point a133 = a131;
                        a130.setColor(a132);
                        a28 = a133;
                    }
                }
                com.cim.AIA.Line[] a134 = a0.nextNodesLines;
                java.awt.Point a135 = a28;
                com.cim.AIA.Line a136 = a134[i];
                java.awt.Point a137 = a135;
                java.awt.Color a138 = a136.getColor();
                java.awt.Point a139 = a137;
                java.awt.Color a140 = java.awt.Color.white;
                java.awt.Point a141 = a139;
                java.awt.Point a142 = a141;
                java.awt.Point a143 = a141;
                if(a138 != a140)
                {
                    a29 = a143;
                }
                else
                {
                    java.awt.Point a144 = a142;
                    com.cim.AIA.Line[] a145 = a0.nextNodesLines;
                    java.awt.Point a146 = a144;
                    com.cim.AIA.Line a147 = a145[i];
                    java.awt.Point a148 = a146;
                    java.awt.Color a149 = this.getBackground();
                    java.awt.Point a150 = a148;
                    a147.setColor(a149);
                    a29 = a150;
                }
                com.cim.AIA.Line[] a151 = a0.nextNodesLines;
                java.awt.Point a152 = a29;
                com.cim.AIA.Line a153 = a151[i];
                java.awt.Point a154 = a152;
                a153.draw(a);
                a16 = a154;
            }
            SkipNode[] a155 = a0.next;
            java.awt.Point a156 = a16;
            int i25 = i - 1;
            SkipNode a157 = a155[i25];
            java.awt.Point a158 = a156;
            java.awt.Point a159 = a158;
            java.awt.Point a160 = a158;
            if(a157 == null)
            {
                a0 = a157;
                a1 = a160;
            }
            else
            {
                java.awt.Point a161 = a159;
                com.cim.AIA.Node[] a162 = a157.nodes;
                java.awt.Point a163 = a161;
                com.cim.AIA.Node a164 = a162[0];
                java.awt.Point a165 = a163;
                int i26 = a164.getX();
                java.awt.Point a166 = a165;
                com.cim.AIA.Node[] a167 = a157.nodes;
                java.awt.Point a168 = a166;
                com.cim.AIA.Node a169 = a167[0];
                java.awt.Point a170 = a168;
                int i27 = a169.getY();
                java.awt.Point a171 = a170;
                com.cim.AIA.Node[] a172 = a157.nodes;
                java.awt.Point a173 = a171;
                com.cim.AIA.Node a174 = a172[0];
                java.awt.Point a175 = a173;
                int i28 = a174.getHeight();
                java.awt.Point a176 = a175;
                int i29 = i * i28;
                int i30 = i27 - i29;
                a161.move(i26, i30);
                java.awt.Point a177 = a176;
                a0 = a157;
                a1 = a177;
            }
        }
    }
    
    static
    {
        java.awt.Color a = java.awt.Color.red;
        SkipListCanvas.YES_COLOR = a;
        java.awt.Color a0 = java.awt.Color.magenta;
        SkipListCanvas.NO_COLOR = a0;
        java.awt.Color a1 = java.awt.Color.gray;
        SkipListCanvas.POINTER_COLOR = a1;
        java.awt.Color a2 = java.awt.Color.black;
        SkipListCanvas.TEXT_COLOR = a2;
        String s = localization.Messages.getString("SkipListCanvas.0");
        SkipListCanvas.SEARCH_LABEL = s;
        String s0 = localization.Messages.getString("SkipListCanvas.1");
        SkipListCanvas.PTR_LABEL = s0;
        String s1 = localization.Messages.getString("SkipListCanvas.2");
        SkipListCanvas.TAILPTR_LABEL = s1;
        String s2 = localization.Messages.getString("SkipListCanvas.3");
        SkipListCanvas.SAVEPTR_LABEL = s2;
        String s3 = localization.Messages.getString("SkipListCanvas.4");
        SkipListCanvas.I_PTR = s3;
        String s4 = localization.Messages.getString("SkipListCanvas.5");
        SkipListCanvas.J_PTR = s4;
        String s5 = localization.Messages.getString("SkipListCanvas.6");
        SkipListCanvas.DATA_COND_LABEL = s5;
        String s6 = localization.Messages.getString("SkipListCanvas.7");
        SkipListCanvas.DATA_LABEL = s6;
    }
}