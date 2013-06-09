public class PatriciaTreeCanvas extends com.cim.AIA.AlgorithmCanvas {
    final private static long serialVersionUID = 2210531931154424145L;
    private static int PatriciaTreeYPosition;
    final private static String DATA_BIT_ARRAY_LABEL;
    final private static String COMPARE_BIT_ARRAY_LABEL;
    private static int numberOfTweens;
    private static com.cim.AIA.Node upLinkNode;
    private com.cim.AIA.HierarchyTree PatriciaTree;
    private com.cim.AIA.ElementArray insertElementArray;
    private com.cim.AIA.ElementArray searchElementArray;
    private com.cim.AIA.DigitalElementArray compareDataBitArray;
    private com.cim.AIA.DigitalElementArray dataItemBitArray;
    private com.cim.AIA.Node searchPositionNode;
    private PatriciaNode insertPositionNode;
    private PatriciaTreeCanvas$NewNode newNode;
    private com.cim.AIA.Node compKeyNode;
    private com.cim.AIA.Node keyFoundNode;
    private Integer differentiatingBit;
    private Integer currentBit;
    private Boolean isHighlightLoop;
    private com.cim.AIA.Node lastNode;
    private java.util.Vector allEndNodes;
    private int windowTop;
    private PatriciaNode patriciaNode;
    
    public PatriciaTreeCanvas()
    {
        super();
        this.windowTop = 0;
    }
    
    public void displayAlgorithm(java.awt.Graphics a)
    {
        com.cim.AIA.HierarchyTree a0 = this.PatriciaTree;
        if(a0 != null)
        {
            com.cim.AIA.HierarchyTree a1 = this.PatriciaTree;
            a1.draw(a);
            this.drawLoops(a);
        }
        com.cim.AIA.ElementArray a2 = this.insertElementArray;
        if(a2 != null)
        {
            com.cim.AIA.ElementArray a3 = this.insertElementArray;
            a3.draw(a);
        }
        com.cim.AIA.ElementArray a4 = this.searchElementArray;
        if(a4 != null)
        {
            com.cim.AIA.ElementArray a5 = this.searchElementArray;
            a5.draw(a);
        }
        com.cim.AIA.DigitalElementArray a6 = this.dataItemBitArray;
        if(a6 != null)
        {
            com.cim.AIA.DigitalElementArray a7 = this.dataItemBitArray;
            a7.draw(a);
        }
        com.cim.AIA.DigitalElementArray a8 = this.compareDataBitArray;
        if(a8 != null)
        {
            com.cim.AIA.DigitalElementArray a9 = this.compareDataBitArray;
            a9.setIsShowBitNumber(true);
            com.cim.AIA.DigitalElementArray a10 = this.compareDataBitArray;
            a10.draw(a);
        }
        com.cim.AIA.Node a11 = this.searchPositionNode;
        if(a11 != null)
        {
            com.cim.AIA.Node a12 = this.searchPositionNode;
            a12.draw(a);
            java.awt.Dimension a13 = this.getSize();
            int i = a13.width;
            int i0 = i / 4;
            java.awt.FontMetrics a14 = a.getFontMetrics();
            int i1 = a14.getHeight();
            com.cim.AIA.HierarchyTree a15 = this.PatriciaTree;
            java.awt.Point a16 = a15.getPosition();
            int i2 = a16.y;
            int i3 = i1 + i2;
            java.awt.Point a17 = new java.awt.Point(i0, i3);
            com.cim.AIA.Node a18 = this.searchPositionNode;
            int i4 = a18.getX();
            com.cim.AIA.Node a19 = this.searchPositionNode;
            int i5 = a19.getY();
            java.awt.Point a20 = new java.awt.Point(i4, i5);
            String s = localization.Messages.getString("PatriciaTreeCanvas.2");
            this.drawLine(a, a17, a20, s);
        }
        com.cim.AIA.Node a21 = this.compKeyNode;
        if(a21 != null)
        {
            com.cim.AIA.Node a22 = this.compKeyNode;
            a22.draw(a);
            java.awt.Dimension a23 = this.getSize();
            int i6 = a23.width;
            int i7 = i6 / 4;
            java.awt.FontMetrics a24 = a.getFontMetrics();
            int i8 = a24.getHeight();
            com.cim.AIA.HierarchyTree a25 = this.PatriciaTree;
            java.awt.Point a26 = a25.getPosition();
            int i9 = a26.y;
            int i10 = i8 + i9;
            java.awt.Point a27 = new java.awt.Point(i7, i10);
            com.cim.AIA.Node a28 = this.compKeyNode;
            int i11 = a28.getX();
            com.cim.AIA.Node a29 = this.compKeyNode;
            int i12 = a29.getY();
            java.awt.Point a30 = new java.awt.Point(i11, i12);
            String s0 = localization.Messages.getString("PatriciaTreeCanvas.3");
            this.drawLine(a, a27, a30, s0);
        }
        com.cim.AIA.Node a31 = this.keyFoundNode;
        if(a31 != null)
        {
            com.cim.AIA.Node a32 = this.keyFoundNode;
            a32.draw(a);
            java.awt.Dimension a33 = this.getSize();
            int i13 = a33.width;
            int i14 = 3 * i13;
            int i15 = i14 / 4;
            java.awt.FontMetrics a34 = a.getFontMetrics();
            int i16 = a34.getHeight();
            com.cim.AIA.HierarchyTree a35 = this.PatriciaTree;
            java.awt.Point a36 = a35.getPosition();
            int i17 = a36.y;
            int i18 = i16 + i17;
            java.awt.Point a37 = new java.awt.Point(i15, i18);
            com.cim.AIA.Node a38 = this.keyFoundNode;
            int i19 = a38.getX();
            com.cim.AIA.Node a39 = this.keyFoundNode;
            int i20 = a39.getWidth();
            int i21 = i19 + i20;
            com.cim.AIA.Node a40 = this.keyFoundNode;
            int i22 = a40.getY();
            java.awt.Point a41 = new java.awt.Point(i21, i22);
            String s1 = localization.Messages.getString("PatriciaTreeCanvas.4");
            this.drawLine(a, a37, a41, s1);
        }
        PatriciaNode a42 = this.patriciaNode;
        int i23 = this.windowTop;
        java.awt.Point a43 = new java.awt.Point(10, i23);
        a42.setPosition(a43);
        PatriciaNode a44 = this.patriciaNode;
        java.awt.Dimension a45 = this.getParentSize();
        int i24 = a45.height;
        PatriciaNode a46 = this.patriciaNode;
        int i25 = a46.getHeight();
        int i26 = i24 - i25;
        int i27 = i26 - 30;
        java.awt.Point a47 = new java.awt.Point(10, i27);
        a44.setPosition(a47);
        PatriciaNode a48 = this.patriciaNode;
        if(a48 != null)
        {
            PatriciaNode a49 = this.patriciaNode;
            a49.draw(a);
            String s2 = localization.Messages.getString("PatriciaTreeCanvas.5");
            PatriciaNode a50 = this.patriciaNode;
            com.cim.AIA.Node a51 = a50.getBody();
            java.awt.Point a52 = a51.getPosition();
            int i28 = a52.x;
            java.awt.FontMetrics a53 = a.getFontMetrics();
            int i29 = a53.stringWidth(s2);
            int i30 = i29 / 2;
            int i31 = i28 - i30;
            PatriciaNode a54 = this.patriciaNode;
            com.cim.AIA.Node a55 = a54.getBody();
            int i32 = a55.getWidth();
            int i33 = i32 / 2;
            int i34 = i31 + i33;
            PatriciaNode a56 = this.patriciaNode;
            com.cim.AIA.Node a57 = a56.getBody();
            java.awt.Point a58 = a57.getPosition();
            int i35 = a58.y;
            java.awt.FontMetrics a59 = a.getFontMetrics();
            int i36 = a59.getHeight();
            int i37 = i36 / 2;
            int i38 = i35 - i37;
            java.awt.Point a60 = new java.awt.Point(i34, i38);
            PatriciaNode a61 = this.patriciaNode;
            com.cim.AIA.Node a62 = a61.getBody();
            java.awt.Point a63 = a62.getPosition();
            int i39 = a63.x;
            PatriciaNode a64 = this.patriciaNode;
            com.cim.AIA.Node a65 = a64.getBody();
            int i40 = a65.getWidth();
            int i41 = i40 / 2;
            int i42 = i39 + i41;
            PatriciaNode a66 = this.patriciaNode;
            com.cim.AIA.Node a67 = a66.getBody();
            java.awt.Point a68 = a67.getPosition();
            int i43 = a68.y;
            java.awt.Point a69 = new java.awt.Point(i42, i43);
            this.drawLine(a, a60, a69, s2);
            String s3 = localization.Messages.getString("PatriciaTreeCanvas.6");
            PatriciaNode a70 = this.patriciaNode;
            PatriciaTreeDataItem a71 = a70.getDataItem();
            com.cim.AIA.Node a72 = a71.getNode();
            java.awt.Point a73 = a72.getPosition();
            int i44 = a73.x;
            java.awt.FontMetrics a74 = a.getFontMetrics();
            int i45 = a74.stringWidth(s3);
            int i46 = i45 / 2;
            int i47 = i44 + i46;
            PatriciaNode a75 = this.patriciaNode;
            PatriciaTreeDataItem a76 = a75.getDataItem();
            com.cim.AIA.Node a77 = a76.getNode();
            int i48 = a77.getWidth();
            int i49 = i48 / 2;
            int i50 = i47 + i49;
            PatriciaNode a78 = this.patriciaNode;
            PatriciaTreeDataItem a79 = a78.getDataItem();
            com.cim.AIA.Node a80 = a79.getNode();
            java.awt.Point a81 = a80.getPosition();
            int i51 = a81.y;
            java.awt.FontMetrics a82 = a.getFontMetrics();
            int i52 = a82.getHeight();
            int i53 = i52 / 2;
            int i54 = i51 - i53;
            java.awt.Point a83 = new java.awt.Point(i50, i54);
            PatriciaNode a84 = this.patriciaNode;
            PatriciaTreeDataItem a85 = a84.getDataItem();
            com.cim.AIA.Node a86 = a85.getNode();
            java.awt.Point a87 = a86.getPosition();
            int i55 = a87.x;
            PatriciaNode a88 = this.patriciaNode;
            PatriciaTreeDataItem a89 = a88.getDataItem();
            com.cim.AIA.Node a90 = a89.getNode();
            int i56 = a90.getWidth();
            int i57 = i56 / 2;
            int i58 = i55 + i57;
            PatriciaNode a91 = this.patriciaNode;
            PatriciaTreeDataItem a92 = a91.getDataItem();
            com.cim.AIA.Node a93 = a92.getNode();
            java.awt.Point a94 = a93.getPosition();
            int i59 = a94.y;
            java.awt.Point a95 = new java.awt.Point(i58, i59);
            this.drawLine(a, a83, a95, s3);
        }
        PatriciaNode a96 = this.insertPositionNode;
        label0: {
            if(a96 == null)
            {
                break label0;
            }
            PatriciaNode a97 = this.insertPositionNode;
            java.awt.Point a98 = a97.getPosition();
            if(a98 != null)
            {
                PatriciaNode a99 = this.insertPositionNode;
                com.cim.AIA.Node a100 = a99.getBody();
                a100.draw(a);
                java.awt.Dimension a101 = this.getSize();
                int i60 = a101.width;
                int i61 = i60 / 6;
                com.cim.AIA.HierarchyTree a102 = this.PatriciaTree;
                java.awt.Point a103 = a102.getPosition();
                int i62 = a103.y;
                java.awt.Point a104 = new java.awt.Point(i61, i62);
                PatriciaNode a105 = this.insertPositionNode;
                com.cim.AIA.Node a106 = a105.getBody();
                int i63 = a106.getX();
                PatriciaNode a107 = this.insertPositionNode;
                com.cim.AIA.Node a108 = a107.getBody();
                int i64 = a108.getY();
                java.awt.Point a109 = new java.awt.Point(i63, i64);
                String s4 = localization.Messages.getString("PatriciaTreeCanvas.7");
                this.drawLine(a, a104, a109, s4);
                PatriciaNode a110 = this.insertPositionNode;
                java.awt.Point a111 = a110.getPosition();
                int i65 = a111.x;
                PatriciaNode a112 = this.insertPositionNode;
                java.awt.Point a113 = a112.getPosition();
                int i66 = a113.y;
                int i67 = i66 - 10;
                java.awt.Point a114 = new java.awt.Point(i65, i67);
                String s5 = localization.Messages.getString("PatriciaTreeCanvas.8");
                this.drawLine(a, a104, a114, s5);
            }
        }
        Integer a115 = this.differentiatingBit;
        label1: {
            if(a115 == null)
            {
                break label1;
            }
            Integer a116 = this.differentiatingBit;
            com.cim.AIA.Node a117 = new com.cim.AIA.Node((Object)a116, 0);
            String s6 = localization.Messages.getString("PatriciaTreeCanvas.9");
            com.cim.AIA.StringMarker a118 = new com.cim.AIA.StringMarker(s6);
            a117.addMarker(a118);
            com.cim.AIA.HierarchyTree a119 = this.PatriciaTree;
            if(a119 != null)
            {
                java.awt.Dimension a120 = this.getParentSize();
                int i68 = a120.width;
                int i69 = 4 * i68;
                int i70 = i69 / 5;
                com.cim.AIA.HierarchyTree a121 = this.PatriciaTree;
                java.awt.Point a122 = a121.getPosition();
                int i71 = a122.y;
                java.awt.Point a123 = new java.awt.Point(i70, i71);
                a117.setPosition(a123);
            }
            java.awt.Color a124 = PatriciaTreeColors.DIFFERENTIATING_COLOR;
            a117.setBackgroundColor(a124);
            a117.draw(a);
        }
        Integer a125 = this.currentBit;
        label2: {
            if(a125 == null)
            {
                break label2;
            }
            Integer a126 = this.currentBit;
            com.cim.AIA.Node a127 = new com.cim.AIA.Node((Object)a126, 0);
            String s7 = localization.Messages.getString("PatriciaTreeCanvas.10");
            com.cim.AIA.StringMarker a128 = new com.cim.AIA.StringMarker(s7);
            a127.addMarker(a128);
            com.cim.AIA.HierarchyTree a129 = this.PatriciaTree;
            if(a129 != null)
            {
                java.awt.Dimension a130 = this.getParentSize();
                int i72 = a130.width;
                int i73 = 4 * i72;
                int i74 = i73 / 5;
                com.cim.AIA.HierarchyTree a131 = this.PatriciaTree;
                java.awt.Point a132 = a131.getPosition();
                int i75 = a132.y;
                int i76 = i75 + 45;
                java.awt.Point a133 = new java.awt.Point(i74, i76);
                a127.setPosition(a133);
            }
            java.awt.Color a134 = PatriciaTreeColors.CURRENT_BIT_COLOR;
            a127.setBackgroundColor(a134);
            a127.draw(a);
        }
        PatriciaTreeCanvas$NewNode a135 = this.newNode;
        if(a135 != null)
        {
            PatriciaTreeCanvas$NewNode a136 = this.newNode;
            a136.drawNewNode(a);
        }
        com.cim.AIA.Node a137 = PatriciaTreeCanvas.upLinkNode;
        if(a137 != null)
        {
            com.cim.AIA.Node a138 = PatriciaTreeCanvas.upLinkNode;
            a138.draw(a);
            String s8 = localization.Messages.getString("PatriciaTreeCanvas.11");
            com.cim.AIA.Node a139 = PatriciaTreeCanvas.upLinkNode;
            java.awt.Point a140 = a139.getPosition();
            int i77 = a140.x;
            java.awt.FontMetrics a141 = a.getFontMetrics();
            String s9 = localization.Messages.getString("PatriciaTreeCanvas.12");
            int i78 = a141.stringWidth(s9);
            int i79 = i78 / 2;
            int i80 = i77 - i79;
            com.cim.AIA.Node a142 = PatriciaTreeCanvas.upLinkNode;
            int i81 = a142.getWidth();
            int i82 = i81 / 2;
            int i83 = i80 + i82;
            com.cim.AIA.Node a143 = PatriciaTreeCanvas.upLinkNode;
            java.awt.Point a144 = a143.getPosition();
            int i84 = a144.y;
            a.drawString(s8, i83, i84);
        }
    }
    
    private void drawArc(java.awt.Graphics a, boolean b, int i, int i0, int i1, int i2, int i3, int i4)
    {
        java.awt.Color a0 = a.getColor();
        int i5 = b?1:0;
        if(i5 == 0)
        {
            java.awt.Color a1 = PatriciaTreeColors.UPLINK_COLOR;
            a.setColor(a1);
            a.drawArc(i, i0, i1, i2, i3, i4);
        }
        else
        {
            java.awt.Color a2 = PatriciaTreeColors.SEARCH_PATH_COLOR;
            a.setColor(a2);
            a.drawArc(i, i0, i1, i2, i3, i4);
            a.translate(0, 1);
            a.drawArc(i, i0, i1, i2, i3, i4);
            a.translate(1, -1);
            a.drawArc(i, i0, i1, i2, i3, i4);
            a.translate(-1, 0);
        }
        a.setColor(a0);
    }
    
    private void drawArrowHead(java.awt.Graphics a, java.awt.Point a0, int i)
    {
        int[] a1 = new int[4];
        int[] a2 = new int[4];
        a1[0] = 0;
        a2[0] = 0;
        a1[1] = -8;
        a2[1] = -4;
        a1[2] = -4;
        a2[2] = 0;
        int i0 = a1[1];
        a1[3] = i0;
        int i1 = a2[1];
        int i2 = 0 - i1;
        a2[3] = i2;
        double d = (double)i;
        double d0 = d * 3.141592653589793;
        double d1 = d0 / 180.0;
        int i3 = 0;
        while(true)
        {
            if(i3 >= 4)
            {
                a.fillPolygon(a1, a2, 4);
                return;
            }
            else
            {
                int i4 = a1[i3];
                double d2 = (double)i4;
                double d3 = Math.cos(d1);
                double d4 = d2 * d3;
                int i5 = a2[i3];
                double d5 = (double)i5;
                double d6 = Math.sin(d1);
                double d7 = d5 * d6;
                double d8 = d4 + d7;
                int i6 = a2[i3];
                double d9 = (double)i6;
                double d10 = Math.cos(d1);
                double d11 = d9 * d10;
                int i7 = a1[i3];
                double d12 = (double)i7;
                double d13 = Math.sin(d1);
                double d14 = d12 * d13;
                double d15 = d11 - d14;
                long j = Math.round(d8);
                double d16 = (double)j;
                long j0 = Math.round(d15);
                double d17 = (double)j0;
                long j1 = Math.round(d16);
                int i8 = (int)j1;
                int i9 = a0.x;
                int i10 = i8 + i9;
                a1[i3] = i10;
                long j2 = Math.round(d17);
                int i11 = (int)j2;
                int i12 = a0.y;
                int i13 = i11 + i12;
                a2[i3] = i13;
                int i14 = i3 + 1;
                i3 = i14;
            }
        }
    }
    
    private void drawLine(java.awt.Graphics a, java.awt.Point a0, java.awt.Point a1, String s)
    {
        int i = a0.x;
        int i0 = a0.y;
        int i1 = a1.x;
        int i2 = a1.y;
        com.cim.AIA.Line a2 = new com.cim.AIA.Line(i, i0, i1, i2);
        a2.setLabel(s);
        a2.setDistanceFromStartForArrowHead(-3);
        a2.setDistanceFromStartForLabel(-1);
        java.awt.FontMetrics a3 = a.getFontMetrics();
        int i3 = a3.stringWidth(s);
        int i4 = 0 - i3;
        int i5 = i4 / 2;
        a2.setXLabelOffset(i5);
        a2.showArrowHead(true);
        java.awt.Color a4 = PatriciaTreeColors.POINTER_COLOR;
        a2.setColor(a4);
        a2.draw(a);
    }
    
    public void drawLoops(java.awt.Graphics a)
    {
        java.util.Vector a0 = this.allEndNodes;
        label0: {
            if(a0 == null)
            {
                break label0;
            }
            int i = 0;
            while(true)
            {
                java.util.Vector a1 = this.allEndNodes;
                int i0 = a1.size();
                if(i >= i0)
                {
                    break;
                }
                java.util.Vector a2 = this.allEndNodes;
                Object a3 = a2.elementAt(i);
                com.cim.AIA.Node[] dummy = (com.cim.AIA.Node[])a3;
                com.cim.AIA.Node[] a4 = (com.cim.AIA.Node[])a3;
                com.cim.AIA.Node a5 = a4[0];
                int i1 = a5.getWidth();
                int i2 = i1 + 20;
                com.cim.AIA.Node a6 = a4[0];
                int i3 = a6.getHeight();
                com.cim.AIA.Node a7 = a4[0];
                java.awt.Point a8 = a7.getPosition();
                java.awt.Point a9 = new java.awt.Point(a8);
                int i4 = a9.y;
                int i5 = i3 / 2;
                int i6 = i4 + i5;
                a9.y = i6;
                int i7 = a9.x;
                int i8 = i2 / 2;
                int i9 = i7 + i8;
                a9.x = i9;
                com.cim.AIA.Node a10 = a4[1];
                label1: {
                    int i10 = 0;
                    int i11 = 0;
                    int i12 = 0;
                    int i13 = 0;
                    int i14 = 0;
                    int i15 = 0;
                    int i16 = 0;
                    if(a10 == null)
                    {
                        break label1;
                    }
                    Boolean a11 = this.isHighlightLoop;
                    label4: {
                        label3: {
                            label2: {
                                if(a11 == null)
                                {
                                    break label2;
                                }
                                com.cim.AIA.Node a12 = this.lastNode;
                                com.cim.AIA.Node a13 = a4[0];
                                if(a12 != a13)
                                {
                                    break label2;
                                }
                                Boolean a14 = this.isHighlightLoop;
                                int i17 = a14.booleanValue()?1:0;
                                if(i17 == 1)
                                {
                                    break label3;
                                }
                            }
                            i10 = 0;
                            break label4;
                        }
                        i10 = 1;
                    }
                    com.cim.AIA.Node a15 = a4[1];
                    java.awt.Point a16 = a15.getPosition();
                    java.awt.Point a17 = new java.awt.Point(a16);
                    int i18 = a17.y;
                    int i19 = i3 / 2;
                    int i20 = i18 + i19;
                    a17.y = i20;
                    int i21 = a17.x;
                    int i22 = i2 / 2;
                    int i23 = i21 + i22;
                    a17.x = i23;
                    int i24 = a17.x;
                    int i25 = a9.x;
                    label5: {
                        if(i24 <= i25)
                        {
                            break label5;
                        }
                        int i26 = a9.y;
                        int i27 = a17.y;
                        int i28 = i26 - i27;
                        int i29 = Math.abs(i28);
                        int i30 = i3 / 2;
                        int i31 = i29 - i30;
                        int i32 = a9.x;
                        int i33 = i32 - 10;
                        int i34 = i2 / 2;
                        int i35 = i33 - i34;
                        int i36 = a9.y;
                        int i37 = i36 - i31;
                        this.drawArc(a, i10 != 0, i35, i37, 20, i31, 180, 90);
                        int i38 = i31 / 2;
                        int i39 = i37 + i38;
                        int i40 = i39 + 1;
                        java.awt.Point a18 = new java.awt.Point(i35, i40);
                        double d = (double)i31;
                        double d0 = d * 4.5;
                        double d1 = (double)20;
                        double d2 = d0 / d1;
                        double d3 = Math.atan(d2);
                        double d4 = d3 * 180.0;
                        double d5 = d4 / 3.141592653589793;
                        int i41 = (int)d5;
                        int i42 = 180 - i41;
                        this.drawArrowHead(a, a18, i42);
                        int i43 = a18.x;
                        int i44 = a17.x;
                        int i45 = i43 - i44;
                        int i46 = Math.abs(i45);
                        int i47 = i46 * 2;
                        int i48 = i47 - i2;
                        int i49 = a18.y;
                        int i50 = a17.y;
                        int i51 = i49 - i50;
                        int i52 = Math.abs(i51);
                        int i53 = i52 * 2;
                        int i54 = a18.x;
                        int i55 = a18.y;
                        int i56 = i53 / 2;
                        int i57 = i55 - i56;
                        this.drawArc(a, i10 != 0, i54, i57, i48, i53, 90, 90);
                        break label1;
                    }
                    int i58 = a17.x;
                    int i59 = a9.x;
                    label7: {
                        label6: {
                            if(i58 != i59)
                            {
                                break label6;
                            }
                            int i60 = a17.y;
                            int i61 = a9.y;
                            if(i60 != i61)
                            {
                                break label6;
                            }
                            int i62 = a9.x;
                            int i63 = i2 / 2;
                            int i64 = i62 - i63;
                            int i65 = i64 - 10;
                            int i66 = a9.y;
                            int i67 = i65 + 10;
                            int i68 = a17.y;
                            java.awt.Point a19 = new java.awt.Point(i67, i68);
                            this.drawArrowHead(a, a19, 15);
                            i11 = i65;
                            i12 = i66;
                            i13 = 20;
                            i14 = i3;
                            i15 = 90;
                            i16 = 270;
                            break label7;
                        }
                        int i69 = a9.x;
                        int i70 = a17.x;
                        int i71 = i69 - i70;
                        int i72 = Math.abs(i71);
                        int i73 = i72 * 2;
                        int i74 = i73 - i2;
                        int i75 = a9.y;
                        int i76 = a17.y;
                        int i77 = i75 - i76;
                        int i78 = Math.abs(i77);
                        int i79 = i78 * 2;
                        int i80 = i79 - i3;
                        int i81 = a9.x;
                        int i82 = i74 / 2;
                        int i83 = i81 - i82;
                        int i84 = i2 / 2;
                        int i85 = i83 - i84;
                        int i86 = a9.y;
                        int i87 = i86 - i80;
                        int i88 = a17.x;
                        int i89 = a17.y;
                        int i90 = i3 / 2;
                        int i91 = i89 + i90;
                        java.awt.Point a20 = new java.awt.Point(i88, i91);
                        this.drawArrowHead(a, a20, 90);
                        i11 = i85;
                        i12 = i87;
                        i13 = i74;
                        i14 = i80;
                        i15 = 180;
                        i16 = 90;
                    }
                    this.drawArc(a, i10 != 0, i11, i12, i13, i14, i15, i16);
                }
                com.cim.AIA.Node a21 = a4[2];
                label8: {
                    int i92 = 0;
                    int i93 = 0;
                    int i94 = 0;
                    int i95 = 0;
                    int i96 = 0;
                    int i97 = 0;
                    int i98 = 0;
                    if(a21 == null)
                    {
                        break label8;
                    }
                    Boolean a22 = this.isHighlightLoop;
                    label11: {
                        label10: {
                            label9: {
                                if(a22 == null)
                                {
                                    break label9;
                                }
                                com.cim.AIA.Node a23 = this.lastNode;
                                com.cim.AIA.Node a24 = a4[0];
                                if(a23 != a24)
                                {
                                    break label9;
                                }
                                Boolean a25 = this.isHighlightLoop;
                                int i99 = a25.booleanValue()?1:0;
                                if(i99 == 0)
                                {
                                    break label10;
                                }
                            }
                            i92 = 0;
                            break label11;
                        }
                        i92 = 1;
                    }
                    com.cim.AIA.Node a26 = a4[2];
                    java.awt.Point a27 = a26.getPosition();
                    java.awt.Point a28 = new java.awt.Point(a27);
                    int i100 = a28.y;
                    int i101 = i3 / 2;
                    int i102 = i100 + i101;
                    a28.y = i102;
                    int i103 = a28.x;
                    int i104 = i2 / 2;
                    int i105 = i103 + i104;
                    a28.x = i105;
                    int i106 = a28.x;
                    int i107 = a9.x;
                    label12: {
                        if(i106 >= i107)
                        {
                            break label12;
                        }
                        int i108 = a9.y;
                        int i109 = a28.y;
                        int i110 = i108 - i109;
                        int i111 = Math.abs(i110);
                        int i112 = i3 / 2;
                        int i113 = i111 - i112;
                        int i114 = a9.x;
                        int i115 = i114 - 10;
                        int i116 = i2 / 2;
                        int i117 = i115 + i116;
                        int i118 = a9.y;
                        int i119 = i118 - i113;
                        this.drawArc(a, i92 != 0, i117, i119, 20, i113, 270, 90);
                        int i120 = i117 + 20;
                        int i121 = i113 / 2;
                        int i122 = i119 + i121;
                        int i123 = i122 + 1;
                        java.awt.Point a29 = new java.awt.Point(i120, i123);
                        double d6 = (double)i113;
                        double d7 = d6 * 4.5;
                        double d8 = (double)20;
                        double d9 = d7 / d8;
                        double d10 = Math.atan(d9);
                        double d11 = d10 * 180.0;
                        double d12 = d11 / 3.141592653589793;
                        int i124 = (int)d12;
                        this.drawArrowHead(a, a29, i124);
                        int i125 = a29.x;
                        int i126 = a28.x;
                        int i127 = i125 - i126;
                        int i128 = Math.abs(i127);
                        int i129 = i128 * 2;
                        int i130 = i129 - i2;
                        int i131 = a29.y;
                        int i132 = a28.y;
                        int i133 = i131 - i132;
                        int i134 = Math.abs(i133);
                        int i135 = i134 * 2;
                        int i136 = a29.x;
                        int i137 = i136 - i130;
                        int i138 = a29.y;
                        int i139 = i135 / 2;
                        int i140 = i138 - i139;
                        this.drawArc(a, i92 != 0, i137, i140, i130, i135, 0, 90);
                        break label8;
                    }
                    int i141 = a28.x;
                    int i142 = a9.x;
                    label14: {
                        label13: {
                            if(i141 != i142)
                            {
                                break label13;
                            }
                            int i143 = a28.y;
                            int i144 = a9.y;
                            if(i143 != i144)
                            {
                                break label13;
                            }
                            int i145 = a9.x;
                            int i146 = i2 / 2;
                            int i147 = i145 + i146;
                            int i148 = i147 - 10;
                            int i149 = a9.y;
                            int i150 = i148 + 10;
                            int i151 = a28.y;
                            java.awt.Point a30 = new java.awt.Point(i150, i151);
                            this.drawArrowHead(a, a30, 175);
                            i93 = i148;
                            i94 = i149;
                            i95 = 20;
                            i96 = i3;
                            i97 = 180;
                            i98 = 270;
                            break label14;
                        }
                        int i152 = a9.x;
                        int i153 = a28.x;
                        int i154 = i152 - i153;
                        int i155 = Math.abs(i154);
                        int i156 = i155 * 2;
                        int i157 = i156 - i2;
                        int i158 = a9.y;
                        int i159 = a28.y;
                        int i160 = i158 - i159;
                        int i161 = Math.abs(i160);
                        int i162 = i161 * 2;
                        int i163 = i162 - i3;
                        int i164 = a9.x;
                        int i165 = i157 / 2;
                        int i166 = i164 - i165;
                        int i167 = i2 / 2;
                        int i168 = i166 + i167;
                        int i169 = a9.y;
                        int i170 = i169 - i163;
                        int i171 = a28.x;
                        int i172 = a28.y;
                        int i173 = i3 / 2;
                        int i174 = i172 + i173;
                        java.awt.Point a31 = new java.awt.Point(i171, i174);
                        this.drawArrowHead(a, a31, 90);
                        i93 = i168;
                        i94 = i170;
                        i95 = i157;
                        i96 = i163;
                        i97 = 270;
                        i98 = 90;
                    }
                    this.drawArc(a, i92 != 0, i93, i94, i95, i96, i97, i98);
                }
                int i175 = i + 1;
                i = i175;
            }
        }
    }
    
    protected synchronized com.cim.AIA.MultipleTween getMoveTweens()
    {
        com.cim.AIA.MultipleTween a = null;
        PatriciaTreeCanvas$NewNode a0 = this.newNode;
        label0: {
            com.cim.AIA.MultipleTween a1 = null;
            if(a0 == null)
            {
                a = a1;
                break label0;
            }
            PatriciaTreeCanvas$NewNode a2 = this.newNode;
            com.cim.AIA.HierarchyTree a3 = a2.getHierarchyTree();
            com.cim.AIA.MultipleTween a4 = null;
            if(a3 == null)
            {
                a = a4;
                break label0;
            }
            PatriciaTreeCanvas$NewNode a5 = this.newNode;
            int i = a5.hasBeenTweened?1:0;
            com.cim.AIA.MultipleTween a6 = null;
            if(i != 0)
            {
                a = a6;
            }
            else
            {
                com.cim.AIA.MultipleTween a7 = new com.cim.AIA.MultipleTween((com.cim.AIA.Paintable)this);
                int i0 = PatriciaTreeCanvas.numberOfTweens;
                int i1 = i0 + 1;
                PatriciaTreeCanvas.numberOfTweens = i1;
                PatriciaTreeCanvas$NewNode a8 = this.newNode;
                PatriciaTreeCanvas$NewNode a9 = this.newNode;
                java.awt.Point a10 = a9.getPosition();
                PatriciaTreeCanvas$NewNode a11 = this.newNode;
                com.cim.AIA.HierarchyTree a12 = a11.getHierarchyTree();
                java.awt.Point a13 = a12.getPosition();
                int i2 = this.numberOfTweenSteps;
                com.cim.AIA.MoveTween a14 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Moveable)a8, a10, a13, false, i2);
                a7.add((com.cim.AIA.Tween)a14);
                PatriciaTreeCanvas$NewNode a15 = this.newNode;
                a15.hasBeenTweened = true;
                PatriciaNode.clearNewNode();
                a = a7;
            }
        }
        return a;
    }
    
    protected void handleColorSelection(com.cim.AIA.ColorSelection a)
    {
    }
    
    protected void handleFontSelection(com.cim.AIA.FontSelection a)
    {
    }
    
    public void processRepaintEvent(com.cim.AIA.RepaintEvent a)
    {
        Object a0 = a.paramObj;
        label0: {
            java.awt.Point a1 = null;
            if(a0 == null)
            {
                break label0;
            }
            Object a2 = a.paramObj;
            PatriciaTree dummy = (PatriciaTree)a2;
            PatriciaTree a3 = (PatriciaTree)a2;
            this.windowTop = 25;
            java.awt.Dimension a4 = this.getParentSize();
            int i = a4.width;
            int i0 = i / 2;
            com.cim.AIA.ElementArray a5 = a3.getInsertElementArray();
            this.insertElementArray = a5;
            com.cim.AIA.ElementArray a6 = this.insertElementArray;
            if(a6 != null)
            {
                com.cim.AIA.ElementArray a7 = this.insertElementArray;
                com.cim.AIA.ElementArray a8 = this.insertElementArray;
                int i1 = a8.getWidth();
                int i2 = i1 / 2;
                int i3 = i0 - i2;
                int i4 = this.windowTop;
                a7.setLocation(i3, i4);
                int i5 = this.windowTop;
                com.cim.AIA.ElementArray a9 = this.insertElementArray;
                int i6 = a9.getHeight();
                int i7 = i6 + 25;
                int i8 = i5 + i7;
                this.windowTop = i8;
            }
            com.cim.AIA.ElementArray a10 = a3.getSearchElementArray();
            this.searchElementArray = a10;
            com.cim.AIA.ElementArray a11 = this.searchElementArray;
            if(a11 != null)
            {
                com.cim.AIA.ElementArray a12 = this.searchElementArray;
                com.cim.AIA.ElementArray a13 = this.searchElementArray;
                int i9 = a13.getWidth();
                int i10 = i9 / 2;
                int i11 = i0 - i10;
                int i12 = this.windowTop;
                a12.setLocation(i11, i12);
                int i13 = this.windowTop;
                com.cim.AIA.ElementArray a14 = this.searchElementArray;
                int i14 = a14.getHeight();
                int i15 = i14 + 25;
                int i16 = i13 + i15;
                this.windowTop = i16;
            }
            com.cim.AIA.DigitalElementArray a15 = a3.getDataItemBitArray();
            this.dataItemBitArray = a15;
            com.cim.AIA.DigitalElementArray a16 = this.dataItemBitArray;
            if(a16 != null)
            {
                com.cim.AIA.DigitalElementArray a17 = this.dataItemBitArray;
                com.cim.AIA.DigitalElementArray a18 = this.dataItemBitArray;
                int i17 = a18.getWidth();
                int i18 = i17 / 2;
                int i19 = i0 - i18;
                int i20 = this.windowTop;
                java.awt.Point a19 = new java.awt.Point(i19, i20);
                a17.setPosition(a19);
                com.cim.AIA.DigitalElementArray a20 = this.dataItemBitArray;
                String s = PatriciaTreeCanvas.DATA_BIT_ARRAY_LABEL;
                a20.setLabel(s);
                int i21 = this.windowTop;
                com.cim.AIA.DigitalElementArray a21 = this.dataItemBitArray;
                int i22 = a21.getHeight();
                int i23 = i22 + 25;
                int i24 = i21 + i23;
                this.windowTop = i24;
            }
            com.cim.AIA.DigitalElementArray a22 = this.dataItemBitArray;
            if(a22 == null)
            {
                int i25 = this.windowTop;
                java.awt.Point a23 = new java.awt.Point(350, i25);
                a1 = a23;
            }
            else
            {
                com.cim.AIA.DigitalElementArray a24 = this.dataItemBitArray;
                java.awt.Point a25 = a24.getPosition();
                int i26 = a25.x;
                com.cim.AIA.DigitalElementArray a26 = this.dataItemBitArray;
                int i27 = a26.getWidth();
                int i28 = i26 + i27;
                int i29 = i28 + 50;
                com.cim.AIA.DigitalElementArray a27 = this.dataItemBitArray;
                java.awt.Point a28 = a27.getPosition();
                int i30 = a28.y;
                java.awt.Point a29 = new java.awt.Point(i29, i30);
                a1 = a29;
            }
            Boolean a30 = a3.getIsFollowedUpLink();
            label2: {
                label1: {
                    if(a30 != null)
                    {
                        break label1;
                    }
                    com.cim.AIA.Node a31 = new com.cim.AIA.Node((Object)"?", 0);
                    PatriciaTreeCanvas.upLinkNode = a31;
                    com.cim.AIA.Node a32 = PatriciaTreeCanvas.upLinkNode;
                    java.awt.Color a33 = PatriciaTreeColors.DEFAULT_BACKGROUND_COLOR;
                    a32.setBackgroundColor(a33);
                    com.cim.AIA.Node a34 = PatriciaTreeCanvas.upLinkNode;
                    a34.setPosition(a1);
                    break label2;
                }
                Boolean a35 = a3.getIsFollowedUpLink();
                int i31 = a35.booleanValue()?1:0;
                if(i31 == 0)
                {
                    String s0 = localization.Messages.getString("PatriciaTreeCanvas.14");
                    com.cim.AIA.Node a36 = new com.cim.AIA.Node((Object)s0, 0);
                    PatriciaTreeCanvas.upLinkNode = a36;
                    com.cim.AIA.Node a37 = PatriciaTreeCanvas.upLinkNode;
                    java.awt.Color a38 = PatriciaTreeColors.NO_COLOR;
                    a37.setBackgroundColor(a38);
                    com.cim.AIA.Node a39 = PatriciaTreeCanvas.upLinkNode;
                    a39.setPosition(a1);
                }
                else
                {
                    String s1 = localization.Messages.getString("PatriciaTreeCanvas.13");
                    com.cim.AIA.Node a40 = new com.cim.AIA.Node((Object)s1, 0);
                    PatriciaTreeCanvas.upLinkNode = a40;
                    com.cim.AIA.Node a41 = PatriciaTreeCanvas.upLinkNode;
                    java.awt.Color a42 = PatriciaTreeColors.YES_COLOR;
                    a41.setBackgroundColor(a42);
                    com.cim.AIA.Node a43 = PatriciaTreeCanvas.upLinkNode;
                    a43.setPosition(a1);
                }
            }
            com.cim.AIA.DigitalElementArray a44 = a3.getCompareBitArray();
            this.compareDataBitArray = a44;
            com.cim.AIA.DigitalElementArray a45 = this.compareDataBitArray;
            if(a45 != null)
            {
                com.cim.AIA.DigitalElementArray a46 = this.compareDataBitArray;
                com.cim.AIA.DigitalElementArray a47 = this.compareDataBitArray;
                int i32 = a47.getWidth();
                int i33 = i32 / 2;
                int i34 = i0 - i33;
                int i35 = this.windowTop;
                java.awt.Point a48 = new java.awt.Point(i34, i35);
                a46.setPosition(a48);
                com.cim.AIA.DigitalElementArray a49 = this.compareDataBitArray;
                String s2 = PatriciaTreeCanvas.COMPARE_BIT_ARRAY_LABEL;
                a49.setLabel(s2);
                int i36 = this.windowTop;
                com.cim.AIA.DigitalElementArray a50 = this.compareDataBitArray;
                int i37 = a50.getHeight();
                int i38 = i37 + 25;
                int i39 = i36 + i38;
                this.windowTop = i39;
            }
            Integer a51 = a3.getDifferentiatingBit();
            this.differentiatingBit = a51;
            Integer a52 = a3.getCurrentBit();
            this.currentBit = a52;
            com.cim.AIA.HierarchyTree a53 = a3.getHierarchyTree();
            this.PatriciaTree = a53;
            com.cim.AIA.HierarchyTree a54 = this.PatriciaTree;
            label3: {
                if(a54 == null)
                {
                    break label3;
                }
                java.awt.Dimension a55 = this.getParentSize();
                int i40 = a55.width;
                int i41 = this.windowTop;
                int i42 = PatriciaTreeCanvas.PatriciaTreeYPosition;
                if(i41 > i42)
                {
                    int i43 = this.windowTop;
                    PatriciaTreeCanvas.PatriciaTreeYPosition = i43;
                }
                com.cim.AIA.HierarchyTree a56 = this.PatriciaTree;
                int i44 = PatriciaTreeCanvas.PatriciaTreeYPosition;
                a56.plantTree(i0, i44);
                com.cim.AIA.HierarchyTree a57 = this.PatriciaTree;
                java.awt.Rectangle a58 = a57.getRectangle();
                int i45 = a58.x;
                com.cim.AIA.HierarchyTree a59 = this.PatriciaTree;
                java.awt.Rectangle a60 = a59.getRectangle();
                int i46 = a60.width;
                int i47 = i45 + i46;
                if(i47 > i40)
                {
                    com.cim.AIA.HierarchyTree a61 = this.PatriciaTree;
                    int i48 = i47 - i40;
                    int i49 = i0 - i48;
                    com.cim.AIA.HierarchyTree a62 = this.PatriciaTree;
                    java.awt.Point a63 = a62.getPosition();
                    int i50 = a63.y;
                    a61.plantTree(i49, i50);
                }
                com.cim.AIA.HierarchyTree a64 = this.PatriciaTree;
                java.awt.Rectangle a65 = a64.getRectangle();
                int i51 = a65.x;
                if(i51 < 0)
                {
                    com.cim.AIA.HierarchyTree a66 = this.PatriciaTree;
                    com.cim.AIA.HierarchyTree a67 = this.PatriciaTree;
                    java.awt.Point a68 = a67.getPosition();
                    int i52 = a68.x;
                    int i53 = i52 - i51;
                    com.cim.AIA.HierarchyTree a69 = this.PatriciaTree;
                    java.awt.Point a70 = a69.getPosition();
                    int i54 = a70.y;
                    a66.plantTree(i53, i54);
                }
                int i55 = PatriciaTreeCanvas.PatriciaTreeYPosition;
                com.cim.AIA.HierarchyTree a71 = this.PatriciaTree;
                java.awt.Rectangle a72 = a71.getRectangle();
                int i56 = a72.height;
                int i57 = i55 + i56;
                this.windowTop = i57;
            }
            com.cim.AIA.Node a73 = a3.getSearchPositionNode();
            this.searchPositionNode = a73;
            PatriciaNode a74 = a3.getInsertPositionNode();
            this.insertPositionNode = a74;
            com.cim.AIA.Node a75 = a3.getCompKeyNode();
            this.compKeyNode = a75;
            com.cim.AIA.Node a76 = a3.getKeyFoundNode();
            this.keyFoundNode = a76;
            PatriciaNode a77 = a3.getNewNode();
            label5: {
                java.awt.Point a78 = null;
                label4: {
                    if(a77 != null)
                    {
                        break label4;
                    }
                    this.newNode = null;
                    break label5;
                }
                com.cim.AIA.ElementArray a79 = this.insertElementArray;
                if(a79 == null)
                {
                    java.awt.Point a80 = new java.awt.Point(20, 60);
                    a78 = a80;
                }
                else
                {
                    com.cim.AIA.ElementArray a81 = this.insertElementArray;
                    java.awt.Point a82 = a81.getLocation();
                    int i58 = a82.y;
                    com.cim.AIA.ElementArray a83 = this.insertElementArray;
                    int i59 = a83.getHeight();
                    int i60 = i58 + i59;
                    int i61 = i60 + 25;
                    java.awt.Point a84 = new java.awt.Point(20, i61);
                    a78 = a84;
                }
                PatriciaTreeCanvas$NewNode a85 = new PatriciaTreeCanvas$NewNode(this, a77, a78);
                this.newNode = a85;
            }
            com.cim.AIA.HierarchyTree a86 = this.PatriciaTree;
            java.awt.Rectangle a87 = a86.getRectangle();
            int i62 = a87.width;
            com.cim.AIA.HierarchyTree a88 = this.PatriciaTree;
            java.awt.Rectangle a89 = a88.getRectangle();
            int i63 = a89.x;
            int i64 = i62 + i63;
            java.awt.Dimension a90 = this.getParentSize();
            int i65 = a90.height;
            this.setSize(i64, i65);
            Boolean a91 = a3.getIsHighlightLoop();
            this.isHighlightLoop = a91;
            com.cim.AIA.Node a92 = a3.getLastNode();
            this.lastNode = a92;
            java.util.Vector a93 = a3.getAllEndNodes();
            this.allEndNodes = a93;
            PatriciaNode a94 = a3.getHeadNode();
            this.patriciaNode = a94;
            this.repaint();
            PatriciaTreeCanvas$NewNode a95 = this.newNode;
            label6: {
                if(a95 == null)
                {
                    break label6;
                }
                com.cim.AIA.MultipleTween a96 = this.getMoveTweens();
                if(a96 != null)
                {
                    this.addTween((com.cim.AIA.Tween)a96);
                }
            }
            int i66 = PatriciaTreeCanvas.numberOfTweens;
            if(i66 > 0)
            {
                PatriciaTreeCanvas.numberOfTweens = 0;
                com.cim.AIA.MultipleTween a97 = this.tweens;
                Thread a98 = new Thread((Runnable)a97);
                a98.start();
            }
            this.repaint();
        }
    }
    
    static void access$000(PatriciaTreeCanvas a, java.awt.Graphics a0, boolean b, int i, int i0, int i1, int i2, int i3, int i4)
    {
        a.drawArc(a0, b, i, i0, i1, i2, i3, i4);
    }
    
    static void access$100(PatriciaTreeCanvas a, java.awt.Graphics a0, java.awt.Point a1, int i)
    {
        a.drawArrowHead(a0, a1, i);
    }
    
    static
    {
        PatriciaTreeCanvas.PatriciaTreeYPosition = 0;
        String s = localization.Messages.getString("PatriciaTreeCanvas.0");
        PatriciaTreeCanvas.DATA_BIT_ARRAY_LABEL = s;
        String s0 = localization.Messages.getString("PatriciaTreeCanvas.1");
        PatriciaTreeCanvas.COMPARE_BIT_ARRAY_LABEL = s0;
        PatriciaTreeCanvas.numberOfTweens = 0;
    }
}