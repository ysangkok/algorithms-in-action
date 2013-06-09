public class PatriciaTreeIterCanvas extends com.cim.AIA.AlgorithmCanvas {
    private com.cim.AIA.HierarchyTree PatriciaTree;
    private com.cim.AIA.ElementArray insertElementArray;
    private com.cim.AIA.ElementArray searchElementArray;
    private com.cim.AIA.DigitalElementArray compareDataBitArray;
    private com.cim.AIA.DigitalElementArray dataItemBitArray;
    private com.cim.AIA.Node searchPositionNode;
    private PatriciaTreeIterNode insertPositionNode;
    private com.cim.AIA.Node insertionPoint;
    private PatriciaTreeIterNode growNode;
    private PatriciaTreeIterCanvas$NewNode newNode;
    private com.cim.AIA.Node compKeyNode;
    private com.cim.AIA.Node keyFoundNode;
    private Integer differentiatingBit;
    private Integer currentBit;
    private Boolean isHighlightLoop;
    private com.cim.AIA.Node lastNode;
    private java.util.Vector allEndNodes;
    private int windowTop;
    private static int PatriciaTreeYPosition;
    final private static String DATA_BIT_ARRAY_LABEL;
    final private static String COMPARE_BIT_ARRAY_LABEL;
    private static int numberOfTweens;
    private static com.cim.AIA.Node upLinkNode;
    private PatriciaTreeIterNode patriciaNode;
    
    public PatriciaTreeIterCanvas()
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
        com.cim.AIA.Node a11 = this.insertionPoint;
        if(a11 != null)
        {
            java.awt.Dimension a12 = this.getSize();
            int i = a12.width;
            int i0 = i / 4;
            java.awt.FontMetrics a13 = a.getFontMetrics();
            int i1 = a13.getHeight();
            com.cim.AIA.HierarchyTree a14 = this.PatriciaTree;
            java.awt.Point a15 = a14.getPosition();
            int i2 = a15.y;
            int i3 = i1 + i2;
            java.awt.Point a16 = new java.awt.Point(i0, i3);
            com.cim.AIA.Node a17 = this.insertionPoint;
            int i4 = a17.getX();
            com.cim.AIA.Node a18 = this.insertionPoint;
            int i5 = a18.getY();
            java.awt.Point a19 = new java.awt.Point(i4, i5);
            String s = localization.Messages.getString("PatriciaTreeIterCanvas.2");
            this.drawLine(a, a16, a19, s);
        }
        com.cim.AIA.Node a20 = this.searchPositionNode;
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
            String s0 = localization.Messages.getString("PatriciaTreeIterCanvas.3");
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
            String s1 = localization.Messages.getString("PatriciaTreeIterCanvas.4");
            this.drawLine(a, a37, a41, s1);
        }
        PatriciaTreeIterNode a42 = this.patriciaNode;
        int i23 = this.windowTop;
        java.awt.Point a43 = new java.awt.Point(10, i23);
        a42.setPosition(a43);
        PatriciaTreeIterNode a44 = this.patriciaNode;
        java.awt.Dimension a45 = this.getParentSize();
        int i24 = a45.height;
        PatriciaTreeIterNode a46 = this.patriciaNode;
        int i25 = a46.getHeight();
        int i26 = i24 - i25;
        int i27 = i26 - 30;
        java.awt.Point a47 = new java.awt.Point(10, i27);
        a44.setPosition(a47);
        PatriciaTreeIterNode a48 = this.patriciaNode;
        if(a48 != null)
        {
            PatriciaTreeIterNode a49 = this.patriciaNode;
            a49.draw(a);
            String s2 = localization.Messages.getString("PatriciaTreeIterCanvas.5");
            PatriciaTreeIterNode a50 = this.patriciaNode;
            com.cim.AIA.Node a51 = a50.getBody();
            java.awt.Point a52 = a51.getPosition();
            int i28 = a52.x;
            java.awt.FontMetrics a53 = a.getFontMetrics();
            int i29 = a53.stringWidth(s2);
            int i30 = i29 / 2;
            int i31 = i28 - i30;
            PatriciaTreeIterNode a54 = this.patriciaNode;
            com.cim.AIA.Node a55 = a54.getBody();
            int i32 = a55.getWidth();
            int i33 = i32 / 2;
            int i34 = i31 + i33;
            PatriciaTreeIterNode a56 = this.patriciaNode;
            com.cim.AIA.Node a57 = a56.getBody();
            java.awt.Point a58 = a57.getPosition();
            int i35 = a58.y;
            java.awt.FontMetrics a59 = a.getFontMetrics();
            int i36 = a59.getHeight();
            int i37 = i36 / 2;
            int i38 = i35 - i37;
            java.awt.Point a60 = new java.awt.Point(i34, i38);
            PatriciaTreeIterNode a61 = this.patriciaNode;
            com.cim.AIA.Node a62 = a61.getBody();
            java.awt.Point a63 = a62.getPosition();
            int i39 = a63.x;
            PatriciaTreeIterNode a64 = this.patriciaNode;
            com.cim.AIA.Node a65 = a64.getBody();
            int i40 = a65.getWidth();
            int i41 = i40 / 2;
            int i42 = i39 + i41;
            PatriciaTreeIterNode a66 = this.patriciaNode;
            com.cim.AIA.Node a67 = a66.getBody();
            java.awt.Point a68 = a67.getPosition();
            int i43 = a68.y;
            java.awt.Point a69 = new java.awt.Point(i42, i43);
            this.drawLine(a, a60, a69, s2);
            String s3 = localization.Messages.getString("PatriciaTreeIterCanvas.6");
            PatriciaTreeIterNode a70 = this.patriciaNode;
            PatriciaTreeIterDataItem a71 = a70.getDataItem();
            com.cim.AIA.Node a72 = a71.getNode();
            java.awt.Point a73 = a72.getPosition();
            int i44 = a73.x;
            java.awt.FontMetrics a74 = a.getFontMetrics();
            int i45 = a74.stringWidth(s3);
            int i46 = i45 / 2;
            int i47 = i44 + i46;
            PatriciaTreeIterNode a75 = this.patriciaNode;
            PatriciaTreeIterDataItem a76 = a75.getDataItem();
            com.cim.AIA.Node a77 = a76.getNode();
            int i48 = a77.getWidth();
            int i49 = i48 / 2;
            int i50 = i47 + i49;
            PatriciaTreeIterNode a78 = this.patriciaNode;
            PatriciaTreeIterDataItem a79 = a78.getDataItem();
            com.cim.AIA.Node a80 = a79.getNode();
            java.awt.Point a81 = a80.getPosition();
            int i51 = a81.y;
            java.awt.FontMetrics a82 = a.getFontMetrics();
            int i52 = a82.getHeight();
            int i53 = i52 / 2;
            int i54 = i51 - i53;
            java.awt.Point a83 = new java.awt.Point(i50, i54);
            PatriciaTreeIterNode a84 = this.patriciaNode;
            PatriciaTreeIterDataItem a85 = a84.getDataItem();
            com.cim.AIA.Node a86 = a85.getNode();
            java.awt.Point a87 = a86.getPosition();
            int i55 = a87.x;
            PatriciaTreeIterNode a88 = this.patriciaNode;
            PatriciaTreeIterDataItem a89 = a88.getDataItem();
            com.cim.AIA.Node a90 = a89.getNode();
            int i56 = a90.getWidth();
            int i57 = i56 / 2;
            int i58 = i55 + i57;
            PatriciaTreeIterNode a91 = this.patriciaNode;
            PatriciaTreeIterDataItem a92 = a91.getDataItem();
            com.cim.AIA.Node a93 = a92.getNode();
            java.awt.Point a94 = a93.getPosition();
            int i59 = a94.y;
            java.awt.Point a95 = new java.awt.Point(i58, i59);
            this.drawLine(a, a83, a95, s3);
        }
        PatriciaTreeIterNode a96 = this.insertPositionNode;
        label0: {
            if(a96 == null)
            {
                break label0;
            }
            PatriciaTreeIterNode a97 = this.insertPositionNode;
            java.awt.Point a98 = a97.getPosition();
            if(a98 != null)
            {
                PatriciaTreeIterNode a99 = this.insertPositionNode;
                com.cim.AIA.Node a100 = a99.getBody();
                a100.draw(a);
                java.awt.Dimension a101 = this.getSize();
                int i60 = a101.width;
                int i61 = i60 / 6;
                com.cim.AIA.HierarchyTree a102 = this.PatriciaTree;
                java.awt.Point a103 = a102.getPosition();
                int i62 = a103.y;
                java.awt.Point a104 = new java.awt.Point(i61, i62);
                PatriciaTreeIterNode a105 = this.insertPositionNode;
                com.cim.AIA.Node a106 = a105.getBody();
                int i63 = a106.getX();
                PatriciaTreeIterNode a107 = this.insertPositionNode;
                com.cim.AIA.Node a108 = a107.getBody();
                int i64 = a108.getY();
                java.awt.Point a109 = new java.awt.Point(i63, i64);
                String s4 = localization.Messages.getString("PatriciaTreeIterCanvas.7");
                this.drawLine(a, a104, a109, s4);
                PatriciaTreeIterNode a110 = this.insertPositionNode;
                java.awt.Point a111 = a110.getPosition();
                int i65 = a111.x;
                PatriciaTreeIterNode a112 = this.insertPositionNode;
                java.awt.Point a113 = a112.getPosition();
                int i66 = a113.y;
                int i67 = i66 - 10;
                java.awt.Point a114 = new java.awt.Point(i65, i67);
                String s5 = localization.Messages.getString("PatriciaTreeIterCanvas.8");
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
            String s6 = localization.Messages.getString("PatriciaTreeIterCanvas.9");
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
            java.awt.Color a124 = PatriciaTreeIterColors.DIFFERENTIATING_COLOR;
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
            String s7 = localization.Messages.getString("PatriciaTreeIterCanvas.10");
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
            java.awt.Color a134 = PatriciaTreeIterColors.CURRENT_BIT_COLOR;
            a127.setBackgroundColor(a134);
            a127.draw(a);
        }
        PatriciaTreeIterCanvas$NewNode a135 = this.newNode;
        label3: {
            if(a135 == null)
            {
                break label3;
            }
            PatriciaTreeIterCanvas$NewNode a136 = this.newNode;
            int i77 = a136.hasBeenTweened?1:0;
            if(i77 == 0)
            {
                PatriciaTreeIterCanvas$NewNode a137 = this.newNode;
                a137.drawNewNode(a);
            }
        }
        com.cim.AIA.Node a138 = PatriciaTreeIterCanvas.upLinkNode;
        if(a138 != null)
        {
            com.cim.AIA.Node a139 = PatriciaTreeIterCanvas.upLinkNode;
            a139.draw(a);
            String s8 = localization.Messages.getString("PatriciaTreeIterCanvas.11");
            com.cim.AIA.Node a140 = PatriciaTreeIterCanvas.upLinkNode;
            java.awt.Point a141 = a140.getPosition();
            int i78 = a141.x;
            java.awt.FontMetrics a142 = a.getFontMetrics();
            String s9 = localization.Messages.getString("PatriciaTreeIterCanvas.12");
            int i79 = a142.stringWidth(s9);
            int i80 = i79 / 2;
            int i81 = i78 - i80;
            com.cim.AIA.Node a143 = PatriciaTreeIterCanvas.upLinkNode;
            int i82 = a143.getWidth();
            int i83 = i82 / 2;
            int i84 = i81 + i83;
            com.cim.AIA.Node a144 = PatriciaTreeIterCanvas.upLinkNode;
            java.awt.Point a145 = a144.getPosition();
            int i85 = a145.y;
            a.drawString(s8, i84, i85);
        }
    }
    
    protected synchronized com.cim.AIA.MultipleTween getMoveTweens()
    {
        com.cim.AIA.MultipleTween a = null;
        PatriciaTreeIterCanvas$NewNode a0 = this.newNode;
        label0: {
            com.cim.AIA.MultipleTween a1 = null;
            if(a0 == null)
            {
                a = a1;
                break label0;
            }
            PatriciaTreeIterCanvas$NewNode a2 = this.newNode;
            com.cim.AIA.HierarchyTree a3 = a2.getHierarchyTree();
            com.cim.AIA.MultipleTween a4 = null;
            if(a3 == null)
            {
                a = a4;
                break label0;
            }
            PatriciaTreeIterCanvas$NewNode a5 = this.newNode;
            int i = a5.hasBeenTweened?1:0;
            com.cim.AIA.MultipleTween a6 = null;
            if(i != 0)
            {
                a = a6;
            }
            else
            {
                com.cim.AIA.MultipleTween a7 = new com.cim.AIA.MultipleTween((com.cim.AIA.Paintable)this);
                int i0 = PatriciaTreeIterCanvas.numberOfTweens;
                int i1 = i0 + 1;
                PatriciaTreeIterCanvas.numberOfTweens = i1;
                PatriciaTreeIterNode a8 = this.growNode;
                com.cim.AIA.Node a9 = a8.getBody();
                int i2 = this.numberOfTweenSteps;
                com.cim.AIA.InsertTween a10 = new com.cim.AIA.InsertTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Sizeable)a9, i2);
                a7.add((com.cim.AIA.Tween)a10);
                PatriciaTreeIterNode a11 = this.growNode;
                PatriciaTreeIterDataItem a12 = a11.getDataItem();
                com.cim.AIA.Node a13 = a12.getNode();
                int i3 = this.numberOfTweenSteps;
                com.cim.AIA.InsertTween a14 = new com.cim.AIA.InsertTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Sizeable)a13, i3);
                a7.add((com.cim.AIA.Tween)a14);
                PatriciaTreeIterCanvas$NewNode a15 = this.newNode;
                a15.hasBeenTweened = true;
                a = a7;
            }
        }
        return a;
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
        java.awt.Color a4 = PatriciaTreeIterColors.POINTER_COLOR;
        a2.setColor(a4);
        a2.draw(a);
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
                int i4 = i3 + 6;
                com.cim.AIA.Node a7 = a4[0];
                java.awt.Point a8 = a7.getPosition();
                java.awt.Point a9 = new java.awt.Point(a8);
                int i5 = a9.y;
                int i6 = i4 / 2;
                int i7 = i5 + i6;
                a9.y = i7;
                int i8 = a9.x;
                int i9 = i2 / 2;
                int i10 = i8 + i9;
                a9.x = i10;
                com.cim.AIA.Node a10 = a4[1];
                label1: {
                    int i11 = 0;
                    int i12 = 0;
                    int i13 = 0;
                    int i14 = 0;
                    int i15 = 0;
                    int i16 = 0;
                    int i17 = 0;
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
                                com.cim.AIA.Node a13 = a4[1];
                                if(a12 != a13)
                                {
                                    break label2;
                                }
                                Boolean a14 = this.isHighlightLoop;
                                int i18 = a14.booleanValue()?1:0;
                                if(i18 == 1)
                                {
                                    break label3;
                                }
                            }
                            i11 = 0;
                            break label4;
                        }
                        i11 = 1;
                    }
                    com.cim.AIA.Node a15 = a4[1];
                    java.awt.Point a16 = a15.getPosition();
                    java.awt.Point a17 = new java.awt.Point(a16);
                    int i19 = a17.y;
                    int i20 = i4 / 2;
                    int i21 = i19 + i20;
                    a17.y = i21;
                    int i22 = a17.x;
                    int i23 = i2 / 2;
                    int i24 = i22 + i23;
                    a17.x = i24;
                    int i25 = a17.x;
                    int i26 = a9.x;
                    label5: {
                        if(i25 <= i26)
                        {
                            break label5;
                        }
                        int i27 = a9.y;
                        int i28 = a17.y;
                        int i29 = i27 - i28;
                        int i30 = Math.abs(i29);
                        int i31 = i4 / 2;
                        int i32 = i30 - i31;
                        int i33 = a9.x;
                        int i34 = i33 - 10;
                        int i35 = i2 / 2;
                        int i36 = i34 - i35;
                        int i37 = a9.y;
                        int i38 = i37 - i32;
                        this.drawArc(a, i11 != 0, i36, i38, 20, i32, 180, 90);
                        int i39 = i32 / 2;
                        int i40 = i38 + i39;
                        int i41 = i40 + 1;
                        java.awt.Point a18 = new java.awt.Point(i36, i41);
                        double d = (double)i32;
                        double d0 = d * 4.5;
                        double d1 = (double)20;
                        double d2 = d0 / d1;
                        double d3 = Math.atan(d2);
                        double d4 = d3 * 180.0;
                        double d5 = d4 / 3.141592653589793;
                        int i42 = (int)d5;
                        int i43 = 180 - i42;
                        this.drawArrowHead(a, a18, i43);
                        int i44 = a18.x;
                        int i45 = a17.x;
                        int i46 = i44 - i45;
                        int i47 = Math.abs(i46);
                        int i48 = i47 * 2;
                        int i49 = i48 - i2;
                        int i50 = a18.y;
                        int i51 = a17.y;
                        int i52 = i50 - i51;
                        int i53 = Math.abs(i52);
                        int i54 = i53 * 2;
                        int i55 = a18.x;
                        int i56 = a18.y;
                        int i57 = i54 / 2;
                        int i58 = i56 - i57;
                        this.drawArc(a, i11 != 0, i55, i58, i49, i54, 90, 90);
                        break label1;
                    }
                    int i59 = a17.x;
                    int i60 = a9.x;
                    label7: {
                        label6: {
                            if(i59 != i60)
                            {
                                break label6;
                            }
                            int i61 = a17.y;
                            int i62 = a9.y;
                            if(i61 != i62)
                            {
                                break label6;
                            }
                            int i63 = a9.x;
                            int i64 = i2 / 2;
                            int i65 = i63 - i64;
                            int i66 = i65 - 10;
                            int i67 = a9.y;
                            int i68 = i66 + 10;
                            int i69 = a17.y;
                            java.awt.Point a19 = new java.awt.Point(i68, i69);
                            this.drawArrowHead(a, a19, 15);
                            i12 = i66;
                            i13 = i67;
                            i14 = 20;
                            i15 = i4;
                            i16 = 90;
                            i17 = 270;
                            break label7;
                        }
                        int i70 = a9.x;
                        int i71 = a17.x;
                        int i72 = i70 - i71;
                        int i73 = Math.abs(i72);
                        int i74 = i73 * 2;
                        int i75 = i74 - i2;
                        int i76 = a9.y;
                        int i77 = a17.y;
                        int i78 = i76 - i77;
                        int i79 = Math.abs(i78);
                        int i80 = i79 * 2;
                        int i81 = i80 - i4;
                        int i82 = a9.x;
                        int i83 = i75 / 2;
                        int i84 = i82 - i83;
                        int i85 = i2 / 2;
                        int i86 = i84 - i85;
                        int i87 = a9.y;
                        int i88 = i87 - i81;
                        int i89 = a17.x;
                        int i90 = a17.y;
                        int i91 = i4 / 2;
                        int i92 = i90 + i91;
                        java.awt.Point a20 = new java.awt.Point(i89, i92);
                        this.drawArrowHead(a, a20, 90);
                        i12 = i86;
                        i13 = i88;
                        i14 = i75;
                        i15 = i81;
                        i16 = 180;
                        i17 = 90;
                    }
                    this.drawArc(a, i11 != 0, i12, i13, i14, i15, i16, i17);
                }
                com.cim.AIA.Node a21 = a4[2];
                label8: {
                    int i93 = 0;
                    int i94 = 0;
                    int i95 = 0;
                    int i96 = 0;
                    int i97 = 0;
                    int i98 = 0;
                    int i99 = 0;
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
                                com.cim.AIA.Node a24 = a4[2];
                                if(a23 != a24)
                                {
                                    break label9;
                                }
                                Boolean a25 = this.isHighlightLoop;
                                int i100 = a25.booleanValue()?1:0;
                                if(i100 == 0)
                                {
                                    break label10;
                                }
                            }
                            i93 = 0;
                            break label11;
                        }
                        i93 = 1;
                    }
                    com.cim.AIA.Node a26 = a4[2];
                    java.awt.Point a27 = a26.getPosition();
                    java.awt.Point a28 = new java.awt.Point(a27);
                    int i101 = a28.y;
                    int i102 = i4 / 2;
                    int i103 = i101 + i102;
                    a28.y = i103;
                    int i104 = a28.x;
                    int i105 = i2 / 2;
                    int i106 = i104 + i105;
                    a28.x = i106;
                    int i107 = a28.x;
                    int i108 = a9.x;
                    label12: {
                        if(i107 >= i108)
                        {
                            break label12;
                        }
                        int i109 = a9.y;
                        int i110 = a28.y;
                        int i111 = i109 - i110;
                        int i112 = Math.abs(i111);
                        int i113 = i4 / 2;
                        int i114 = i112 - i113;
                        int i115 = a9.x;
                        int i116 = i115 - 10;
                        int i117 = i2 / 2;
                        int i118 = i116 + i117;
                        int i119 = a9.y;
                        int i120 = i119 - i114;
                        this.drawArc(a, i93 != 0, i118, i120, 20, i114, 270, 90);
                        int i121 = i118 + 20;
                        int i122 = i114 / 2;
                        int i123 = i120 + i122;
                        int i124 = i123 + 1;
                        java.awt.Point a29 = new java.awt.Point(i121, i124);
                        double d6 = (double)i114;
                        double d7 = d6 * 4.5;
                        double d8 = (double)20;
                        double d9 = d7 / d8;
                        double d10 = Math.atan(d9);
                        double d11 = d10 * 180.0;
                        double d12 = d11 / 3.141592653589793;
                        int i125 = (int)d12;
                        this.drawArrowHead(a, a29, i125);
                        int i126 = a29.x;
                        int i127 = a28.x;
                        int i128 = i126 - i127;
                        int i129 = Math.abs(i128);
                        int i130 = i129 * 2;
                        int i131 = i130 - i2;
                        int i132 = a29.y;
                        int i133 = a28.y;
                        int i134 = i132 - i133;
                        int i135 = Math.abs(i134);
                        int i136 = i135 * 2;
                        int i137 = a29.x;
                        int i138 = i137 - i131;
                        int i139 = a29.y;
                        int i140 = i136 / 2;
                        int i141 = i139 - i140;
                        this.drawArc(a, i93 != 0, i138, i141, i131, i136, 0, 90);
                        break label8;
                    }
                    int i142 = a28.x;
                    int i143 = a9.x;
                    label14: {
                        label13: {
                            if(i142 != i143)
                            {
                                break label13;
                            }
                            int i144 = a28.y;
                            int i145 = a9.y;
                            if(i144 != i145)
                            {
                                break label13;
                            }
                            int i146 = a9.x;
                            int i147 = i2 / 2;
                            int i148 = i146 + i147;
                            int i149 = i148 - 10;
                            int i150 = a9.y;
                            int i151 = i149 + 10;
                            int i152 = a28.y;
                            java.awt.Point a30 = new java.awt.Point(i151, i152);
                            this.drawArrowHead(a, a30, 175);
                            i94 = i149;
                            i95 = i150;
                            i96 = 20;
                            i97 = i4;
                            i98 = 180;
                            i99 = 270;
                            break label14;
                        }
                        int i153 = a9.x;
                        int i154 = a28.x;
                        int i155 = i153 - i154;
                        int i156 = Math.abs(i155);
                        int i157 = i156 * 2;
                        int i158 = i157 - i2;
                        int i159 = a9.y;
                        int i160 = a28.y;
                        int i161 = i159 - i160;
                        int i162 = Math.abs(i161);
                        int i163 = i162 * 2;
                        int i164 = i163 - i4;
                        int i165 = a9.x;
                        int i166 = i158 / 2;
                        int i167 = i165 - i166;
                        int i168 = i2 / 2;
                        int i169 = i167 + i168;
                        int i170 = a9.y;
                        int i171 = i170 - i164;
                        int i172 = a28.x;
                        int i173 = a28.y;
                        int i174 = i4 / 2;
                        int i175 = i173 + i174;
                        java.awt.Point a31 = new java.awt.Point(i172, i175);
                        this.drawArrowHead(a, a31, 90);
                        i94 = i169;
                        i95 = i171;
                        i96 = i158;
                        i97 = i164;
                        i98 = 270;
                        i99 = 90;
                    }
                    this.drawArc(a, i93 != 0, i94, i95, i96, i97, i98, i99);
                }
                int i176 = i + 1;
                i = i176;
            }
        }
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
            PatriciaTreeIter dummy = (PatriciaTreeIter)a2;
            PatriciaTreeIter a3 = (PatriciaTreeIter)a2;
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
                String s = PatriciaTreeIterCanvas.DATA_BIT_ARRAY_LABEL;
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
                    String s0 = localization.Messages.getString("PatriciaTreeIterCanvas.15");
                    com.cim.AIA.Node a31 = new com.cim.AIA.Node((Object)s0, 0);
                    PatriciaTreeIterCanvas.upLinkNode = a31;
                    com.cim.AIA.Node a32 = PatriciaTreeIterCanvas.upLinkNode;
                    java.awt.Color a33 = PatriciaTreeIterColors.DEFAULT_BACKGROUND_COLOR;
                    a32.setBackgroundColor(a33);
                    com.cim.AIA.Node a34 = PatriciaTreeIterCanvas.upLinkNode;
                    a34.setPosition(a1);
                    break label2;
                }
                Boolean a35 = a3.getIsFollowedUpLink();
                int i31 = a35.booleanValue()?1:0;
                if(i31 == 0)
                {
                    String s1 = localization.Messages.getString("PatriciaTreeIterCanvas.14");
                    com.cim.AIA.Node a36 = new com.cim.AIA.Node((Object)s1, 0);
                    PatriciaTreeIterCanvas.upLinkNode = a36;
                    com.cim.AIA.Node a37 = PatriciaTreeIterCanvas.upLinkNode;
                    java.awt.Color a38 = PatriciaTreeIterColors.NO_COLOR;
                    a37.setBackgroundColor(a38);
                    com.cim.AIA.Node a39 = PatriciaTreeIterCanvas.upLinkNode;
                    a39.setPosition(a1);
                }
                else
                {
                    String s2 = localization.Messages.getString("PatriciaTreeIterCanvas.13");
                    com.cim.AIA.Node a40 = new com.cim.AIA.Node((Object)s2, 0);
                    PatriciaTreeIterCanvas.upLinkNode = a40;
                    com.cim.AIA.Node a41 = PatriciaTreeIterCanvas.upLinkNode;
                    java.awt.Color a42 = PatriciaTreeIterColors.YES_COLOR;
                    a41.setBackgroundColor(a42);
                    com.cim.AIA.Node a43 = PatriciaTreeIterCanvas.upLinkNode;
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
                String s3 = PatriciaTreeIterCanvas.COMPARE_BIT_ARRAY_LABEL;
                a49.setLabel(s3);
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
            com.cim.AIA.ExtendedHierarchyTree a53 = a3.getHierarchyTree();
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
                int i42 = PatriciaTreeIterCanvas.PatriciaTreeYPosition;
                if(i41 > i42)
                {
                    int i43 = this.windowTop;
                    PatriciaTreeIterCanvas.PatriciaTreeYPosition = i43;
                }
                com.cim.AIA.HierarchyTree a56 = this.PatriciaTree;
                int i44 = PatriciaTreeIterCanvas.PatriciaTreeYPosition;
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
                int i55 = PatriciaTreeIterCanvas.PatriciaTreeYPosition;
                com.cim.AIA.HierarchyTree a71 = this.PatriciaTree;
                java.awt.Rectangle a72 = a71.getRectangle();
                int i56 = a72.height;
                int i57 = i55 + i56;
                this.windowTop = i57;
            }
            com.cim.AIA.Node a73 = a3.getInsertionPoint();
            this.insertionPoint = a73;
            com.cim.AIA.Node a74 = a3.getSearchPositionNode();
            this.searchPositionNode = a74;
            PatriciaTreeIterNode a75 = a3.getInsertPositionNode();
            this.insertPositionNode = a75;
            com.cim.AIA.Node a76 = a3.getCompKeyNode();
            this.compKeyNode = a76;
            com.cim.AIA.Node a77 = a3.getKeyFoundNode();
            this.keyFoundNode = a77;
            PatriciaTreeIterNode a78 = a3.getNewNode();
            label5: {
                java.awt.Point a79 = null;
                label4: {
                    if(a78 != null)
                    {
                        break label4;
                    }
                    this.newNode = null;
                    break label5;
                }
                this.growNode = a78;
                com.cim.AIA.ElementArray a80 = this.insertElementArray;
                if(a80 == null)
                {
                    java.awt.Point a81 = new java.awt.Point(20, 60);
                    a79 = a81;
                }
                else
                {
                    com.cim.AIA.ElementArray a82 = this.insertElementArray;
                    java.awt.Point a83 = a82.getLocation();
                    int i58 = a83.y;
                    com.cim.AIA.ElementArray a84 = this.insertElementArray;
                    int i59 = a84.getHeight();
                    int i60 = i58 + i59;
                    int i61 = i60 + 25;
                    java.awt.Point a85 = new java.awt.Point(20, i61);
                    a79 = a85;
                }
                PatriciaTreeIterCanvas$NewNode a86 = new PatriciaTreeIterCanvas$NewNode(this, a78, a79);
                this.newNode = a86;
            }
            com.cim.AIA.HierarchyTree a87 = this.PatriciaTree;
            java.awt.Rectangle a88 = a87.getRectangle();
            int i62 = a88.width;
            com.cim.AIA.HierarchyTree a89 = this.PatriciaTree;
            java.awt.Rectangle a90 = a89.getRectangle();
            int i63 = a90.x;
            int i64 = i62 + i63;
            java.awt.Dimension a91 = this.getParentSize();
            int i65 = a91.height;
            this.setSize(i64, i65);
            Boolean a92 = a3.getIsHighlightLoop();
            this.isHighlightLoop = a92;
            com.cim.AIA.Node a93 = a3.getLastNode();
            this.lastNode = a93;
            java.util.Vector a94 = a3.getAllEndNodes();
            this.allEndNodes = a94;
            PatriciaTreeIterNode a95 = a3.getHeadNode();
            this.patriciaNode = a95;
            this.repaint();
            PatriciaTreeIterCanvas$NewNode a96 = this.newNode;
            label6: {
                if(a96 == null)
                {
                    break label6;
                }
                com.cim.AIA.MultipleTween a97 = this.getMoveTweens();
                if(a97 != null)
                {
                    this.addTween((com.cim.AIA.Tween)a97);
                }
            }
            int i66 = PatriciaTreeIterCanvas.numberOfTweens;
            if(i66 > 0)
            {
                PatriciaTreeIterCanvas.numberOfTweens = 0;
                com.cim.AIA.MultipleTween a98 = this.tweens;
                Thread a99 = new Thread((Runnable)a98);
                a99.start();
            }
            this.repaint();
        }
    }
    
    private void drawArc(java.awt.Graphics a, boolean b, int i, int i0, int i1, int i2, int i3, int i4)
    {
        java.awt.Color a0 = a.getColor();
        int i5 = b?1:0;
        if(i5 == 0)
        {
            java.awt.Color a1 = PatriciaTreeIterColors.UPLINK_COLOR;
            a.setColor(a1);
            a.drawArc(i, i0, i1, i2, i3, i4);
        }
        else
        {
            java.awt.Color a2 = PatriciaTreeIterColors.SEARCH_PATH_COLOR;
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
    
    protected void handleColorSelection(com.cim.AIA.ColorSelection a)
    {
    }
    
    protected void handleFontSelection(com.cim.AIA.FontSelection a)
    {
    }
    
    static void access$000(PatriciaTreeIterCanvas a, java.awt.Graphics a0, boolean b, int i, int i0, int i1, int i2, int i3, int i4)
    {
        a.drawArc(a0, b, i, i0, i1, i2, i3, i4);
    }
    
    static void access$100(PatriciaTreeIterCanvas a, java.awt.Graphics a0, java.awt.Point a1, int i)
    {
        a.drawArrowHead(a0, a1, i);
    }
    
    static
    {
        PatriciaTreeIterCanvas.PatriciaTreeYPosition = 0;
        String s = localization.Messages.getString("PatriciaTreeIterCanvas.0");
        PatriciaTreeIterCanvas.DATA_BIT_ARRAY_LABEL = s;
        String s0 = localization.Messages.getString("PatriciaTreeIterCanvas.1");
        PatriciaTreeIterCanvas.COMPARE_BIT_ARRAY_LABEL = s0;
        PatriciaTreeIterCanvas.numberOfTweens = 0;
    }
}