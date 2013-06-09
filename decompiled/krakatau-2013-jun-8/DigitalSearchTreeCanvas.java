public class DigitalSearchTreeCanvas extends com.cim.AIA.AlgorithmCanvas {
    private com.cim.AIA.ExtendedHierarchyTree hierarchyTree;
    private com.cim.AIA.ElementArray insertElementArray;
    private com.cim.AIA.ElementArray searchElementArray;
    private com.cim.AIA.ElementArray bitElementArray;
    private com.cim.AIA.Node parent;
    private com.cim.AIA.Node ptr;
    private com.cim.AIA.Node currentBit;
    private com.cim.AIA.Node highlightedNode;
    private com.cim.AIA.Node bitValueNode;
    final private static java.awt.Color POINTER_COLOR;
    final private static java.awt.Color TEXT_COLOR;
    final private static String BIT_ARRAY_LABEL;
    final private static String SEARCH_LABEL;
    private int approximateBitElementGap;
    final private static int GAP = 30;
    final private static int ELEMENT_WIDTH = 20;
    final private static int BIT_LINE_LENGTH = 20;
    
    public DigitalSearchTreeCanvas()
    {
        super();
        this.approximateBitElementGap = 0;
    }
    
    public void displayAlgorithm(java.awt.Graphics a)
    {
        java.awt.Color a0 = DigitalSearchTreeCanvas.TEXT_COLOR;
        a.setColor(a0);
        com.cim.AIA.ElementArray a1 = this.insertElementArray;
        if(a1 != null)
        {
            com.cim.AIA.ElementArray a2 = this.insertElementArray;
            a2.draw(a);
        }
        com.cim.AIA.ElementArray a3 = this.bitElementArray;
        label0: {
            if(a3 == null)
            {
                break label0;
            }
            com.cim.AIA.Node a4 = this.highlightedNode;
            if(a4 == null)
            {
                break label0;
            }
            String s = DigitalSearchTreeCanvas.BIT_ARRAY_LABEL;
            java.awt.Dimension a5 = this.getParentSize();
            int i = a5.width;
            int i0 = i / 2;
            java.awt.FontMetrics a6 = a.getFontMetrics();
            String s0 = DigitalSearchTreeCanvas.BIT_ARRAY_LABEL;
            int i1 = a6.stringWidth(s0);
            int i2 = i1 / 2;
            int i3 = i0 - i2;
            com.cim.AIA.ElementArray a7 = this.bitElementArray;
            java.awt.Point a8 = a7.getLocation();
            int i4 = a8.y;
            com.cim.AIA.ElementArray a9 = this.bitElementArray;
            int i5 = a9.getHeight();
            int i6 = i4 + i5;
            java.awt.FontMetrics a10 = a.getFontMetrics();
            int i7 = a10.getHeight();
            int i8 = i6 + i7;
            a.drawString(s, i3, i8);
            com.cim.AIA.Node a11 = this.currentBit;
            if(a11 != null)
            {
                com.cim.AIA.Node a12 = this.currentBit;
                int i9 = a12.getX();
                com.cim.AIA.Node a13 = this.currentBit;
                int i10 = a13.getWidth();
                int i11 = i10 / 2;
                int i12 = i9 + i11;
                com.cim.AIA.Node a14 = this.currentBit;
                int i13 = a14.getY();
                com.cim.AIA.Node a15 = this.currentBit;
                int i14 = a15.getHeight();
                int i15 = i13 + i14;
                java.awt.Point a16 = new java.awt.Point(i12, i15);
                int i16 = a16.x;
                int i17 = a16.y;
                int i18 = i17 + 20;
                int i19 = a16.x;
                int i20 = a16.y;
                com.cim.AIA.Line a17 = new com.cim.AIA.Line(i16, i18, i19, i20);
                String s1 = DigitalSearchTree.BIT_LABEL;
                a17.setLabel(s1);
                a17.setDistanceFromStartForArrowHead(-3);
                a17.setDistanceFromStartForLabel(-1);
                java.awt.FontMetrics a18 = a.getFontMetrics();
                String s2 = DigitalSearchTree.BIT_LABEL;
                int i21 = a18.stringWidth(s2);
                int i22 = 0 - i21;
                int i23 = i22 / 2;
                a17.setXLabelOffset(i23);
                a17.showArrowHead(true);
                java.awt.Color a19 = DigitalSearchTreeCanvas.POINTER_COLOR;
                a17.setColor(a19);
                a17.draw(a);
            }
            com.cim.AIA.ElementArray a20 = this.bitElementArray;
            java.awt.Point a21 = a20.getLocation();
            int i24 = a21.x;
            com.cim.AIA.ElementArray a22 = this.bitElementArray;
            int i25 = a22.getWidth();
            int i26 = i24 + i25;
            int i27 = i26 + 15;
            java.awt.FontMetrics a23 = a.getFontMetrics();
            int i28 = a23.stringWidth("=");
            int i29 = i28 / 2;
            int i30 = i27 - i29;
            com.cim.AIA.ElementArray a24 = this.bitElementArray;
            java.awt.Point a25 = a24.getLocation();
            int i31 = a25.y;
            com.cim.AIA.ElementArray a26 = this.bitElementArray;
            int i32 = a26.getHeight();
            int i33 = i32 / 2;
            int i34 = i31 + i33;
            java.awt.FontMetrics a27 = a.getFontMetrics();
            int i35 = a27.getHeight();
            int i36 = i35 / 2;
            int i37 = i34 + i36;
            a.drawString("=", i30, i37);
            com.cim.AIA.Node a28 = this.bitValueNode;
            if(a28 != null)
            {
                com.cim.AIA.Node a29 = this.bitValueNode;
                a29.draw(a);
            }
            com.cim.AIA.ElementArray a30 = this.bitElementArray;
            a30.draw(a);
        }
        com.cim.AIA.ElementArray a31 = this.searchElementArray;
        if(a31 != null)
        {
            com.cim.AIA.ElementArray a32 = this.searchElementArray;
            a32.draw(a);
            String s3 = DigitalSearchTreeCanvas.SEARCH_LABEL;
            java.awt.Dimension a33 = this.getParentSize();
            int i38 = a33.width;
            int i39 = i38 / 2;
            java.awt.FontMetrics a34 = a.getFontMetrics();
            String s4 = DigitalSearchTreeCanvas.SEARCH_LABEL;
            int i40 = a34.stringWidth(s4);
            int i41 = i40 / 2;
            int i42 = i39 - i41;
            com.cim.AIA.ElementArray a35 = this.searchElementArray;
            java.awt.Point a36 = a35.getLocation();
            int i43 = a36.y;
            a.drawString(s3, i42, i43);
        }
        com.cim.AIA.ExtendedHierarchyTree a37 = this.hierarchyTree;
        label1: {
            if(a37 == null)
            {
                break label1;
            }
            com.cim.AIA.ExtendedHierarchyTree a38 = this.hierarchyTree;
            a38.draw(a);
            com.cim.AIA.Node a39 = this.parent;
            if(a39 != null)
            {
                java.awt.Dimension a40 = this.getSize();
                int i44 = a40.width;
                int i45 = i44 / 4;
                java.awt.FontMetrics a41 = a.getFontMetrics();
                int i46 = a41.getHeight();
                com.cim.AIA.ExtendedHierarchyTree a42 = this.hierarchyTree;
                java.awt.Point a43 = a42.getPosition();
                int i47 = a43.y;
                int i48 = i46 + i47;
                java.awt.Point a44 = new java.awt.Point(i45, i48);
                int i49 = a44.x;
                java.awt.FontMetrics a45 = a.getFontMetrics();
                String s5 = DigitalSearchTree.PARENT_LABEL;
                int i50 = a45.stringWidth(s5);
                int i51 = i49 + i50;
                int i52 = a44.y;
                java.awt.FontMetrics a46 = a.getFontMetrics();
                int i53 = a46.getHeight();
                int i54 = i53 / 2;
                int i55 = i52 - i54;
                com.cim.AIA.Node a47 = this.parent;
                int i56 = a47.getX();
                com.cim.AIA.Node a48 = this.parent;
                int i57 = a48.getY();
                com.cim.AIA.Line a49 = new com.cim.AIA.Line(i51, i55, i56, i57);
                String s6 = DigitalSearchTree.PARENT_LABEL;
                a49.setLabel(s6);
                a49.setDistanceFromStartForArrowHead(-3);
                a49.setDistanceFromStartForLabel(-1);
                java.awt.FontMetrics a50 = a.getFontMetrics();
                String s7 = DigitalSearchTree.PARENT_LABEL;
                int i58 = a50.stringWidth(s7);
                int i59 = 0 - i58;
                int i60 = i59 / 2;
                a49.setXLabelOffset(i60);
                a49.showArrowHead(true);
                java.awt.Color a51 = DigitalSearchTreeCanvas.POINTER_COLOR;
                a49.setColor(a51);
                a49.draw(a);
            }
            com.cim.AIA.Node a52 = this.ptr;
            if(a52 == null)
            {
                break label1;
            }
            java.awt.Dimension a53 = this.getSize();
            int i61 = a53.width;
            int i62 = 3 * i61;
            int i63 = i62 / 4;
            java.awt.FontMetrics a54 = a.getFontMetrics();
            int i64 = a54.getHeight();
            com.cim.AIA.ExtendedHierarchyTree a55 = this.hierarchyTree;
            java.awt.Point a56 = a55.getPosition();
            int i65 = a56.y;
            int i66 = i64 + i65;
            java.awt.Point a57 = new java.awt.Point(i63, i66);
            int i67 = a57.x;
            int i68 = a57.y;
            java.awt.FontMetrics a58 = a.getFontMetrics();
            int i69 = a58.getHeight();
            int i70 = i69 / 2;
            int i71 = i68 - i70;
            com.cim.AIA.Node a59 = this.ptr;
            int i72 = a59.getX();
            com.cim.AIA.Node a60 = this.ptr;
            int i73 = a60.getWidth();
            int i74 = i72 + i73;
            com.cim.AIA.Node a61 = this.ptr;
            int i75 = a61.getY();
            com.cim.AIA.Line a62 = new com.cim.AIA.Line(i67, i71, i74, i75);
            String s8 = DigitalSearchTree.ptrLabel;
            a62.setLabel(s8);
            a62.setDistanceFromStartForArrowHead(-3);
            a62.setDistanceFromStartForLabel(-1);
            java.awt.FontMetrics a63 = a.getFontMetrics();
            String s9 = DigitalSearchTree.ptrLabel;
            int i76 = a63.stringWidth(s9);
            int i77 = 0 - i76;
            int i78 = i77 / 2;
            a62.setXLabelOffset(i78);
            a62.showArrowHead(true);
            java.awt.Color a64 = DigitalSearchTreeCanvas.POINTER_COLOR;
            a62.setColor(a64);
            double d = a62.getY2();
            int i79 = d > 0.0?1:d == 0.0?0:-1;
            if(i79 != 0)
            {
                a62.draw(a);
            }
        }
    }
    
    protected void handleColorSelection(com.cim.AIA.ColorSelection a)
    {
    }
    
    protected void handleFontSelection(com.cim.AIA.FontSelection a)
    {
    }
    
    public void processRepaintEvent(com.cim.AIA.RepaintEvent a)
    {
        this.removeAllSelectables();
        com.cim.AIA.SearchSelection a0 = com.cim.AIA.SearchSelection.getInstance();
        this.removeSelectionListener((com.cim.AIA.SelectionListener)a0);
        Object a1 = a.paramObj;
        label0: {
            int i = 0;
            int i0 = 0;
            if(a1 == null)
            {
                break label0;
            }
            java.awt.Dimension a2 = this.getParentSize();
            int i1 = a2.width;
            int i2 = i1 / 2;
            Object a3 = a.paramObj;
            DigitalSearchTree dummy = (DigitalSearchTree)a3;
            DigitalSearchTree a4 = (DigitalSearchTree)a3;
            com.cim.AIA.ExtendedHierarchyTree a5 = a4.getHierarchyTree();
            this.hierarchyTree = a5;
            int i3 = a4.isBuildMode()?1:0;
            if(i3 == 0)
            {
                this.insertElementArray = null;
            }
            else
            {
                com.cim.AIA.ElementArray a6 = a4.getInsertElementArray();
                this.insertElementArray = a6;
            }
            com.cim.AIA.ElementArray a7 = a4.getBitElementArray();
            this.bitElementArray = a7;
            com.cim.AIA.Node a8 = a4.getHighlightedNode();
            this.highlightedNode = a8;
            int i4 = a4.isSearchMode()?1:0;
            if(i4 == 0)
            {
                this.searchElementArray = null;
            }
            else
            {
                com.cim.AIA.SearchSelection a9 = com.cim.AIA.SearchSelection.getInstance();
                this.addSelectionListener((com.cim.AIA.SelectionListener)a9);
                com.cim.AIA.ElementArray a10 = a4.getSearchElementArray();
                this.searchElementArray = a10;
            }
            com.cim.AIA.ElementArray a11 = this.insertElementArray;
            label1: {
                if(a11 == null)
                {
                    i = 30;
                    break label1;
                }
                com.cim.AIA.ElementArray a12 = this.insertElementArray;
                a12.setElementWidth(20);
                com.cim.AIA.ElementArray a13 = this.insertElementArray;
                a13.setAllHaveSameWidth(true);
                java.awt.Dimension a14 = this.getParentSize();
                int i5 = a14.width;
                com.cim.AIA.ElementArray a15 = this.insertElementArray;
                int i6 = a15.getWidth();
                if(i6 >= i5)
                {
                    com.cim.AIA.ElementArray a16 = this.insertElementArray;
                    a16.setLocation(0, 30);
                }
                else
                {
                    com.cim.AIA.ElementArray a17 = this.insertElementArray;
                    com.cim.AIA.ElementArray a18 = this.insertElementArray;
                    int i7 = a18.getWidth();
                    int i8 = i7 / 2;
                    int i9 = i2 - i8;
                    a17.setLocation(i9, 30);
                }
                com.cim.AIA.ElementArray a19 = this.insertElementArray;
                int i10 = a19.getHeight();
                int i11 = i10 + 30;
                int i12 = 30 + i11;
                com.cim.AIA.ElementArray a20 = this.bitElementArray;
                label2: {
                    if(a20 == null)
                    {
                        break label2;
                    }
                    com.cim.AIA.Node a21 = this.highlightedNode;
                    if(a21 == null)
                    {
                        break label2;
                    }
                    com.cim.AIA.Node a22 = a4.getHighlightedNode();
                    this.highlightedNode = a22;
                    com.cim.AIA.Node a23 = this.highlightedNode;
                    Object a24 = a23.getObject();
                    com.cim.AIA.Node a25 = new com.cim.AIA.Node(a24, 0);
                    this.bitValueNode = a25;
                    com.cim.AIA.Node a26 = this.bitValueNode;
                    com.cim.AIA.Node a27 = this.highlightedNode;
                    java.awt.Color a28 = a27.getBackgroundColor();
                    a26.setBackgroundColor(a28);
                    com.cim.AIA.Node a29 = a4.getBitNode();
                    this.currentBit = a29;
                    com.cim.AIA.ElementArray a30 = this.bitElementArray;
                    a30.setElementWidth(20);
                    com.cim.AIA.ElementArray a31 = this.bitElementArray;
                    a31.setAllHaveSameWidth(true);
                    com.cim.AIA.ElementArray a32 = this.bitElementArray;
                    int i13 = a32.getWidth();
                    if(i13 >= i5)
                    {
                        com.cim.AIA.ElementArray a33 = this.bitElementArray;
                        a33.setLocation(0, i12);
                    }
                    else
                    {
                        com.cim.AIA.ElementArray a34 = this.bitElementArray;
                        com.cim.AIA.ElementArray a35 = this.bitElementArray;
                        int i14 = a35.getWidth();
                        int i15 = i14 / 2;
                        int i16 = i2 - i15;
                        a34.setLocation(i16, i12);
                    }
                    com.cim.AIA.Node a36 = this.bitValueNode;
                    com.cim.AIA.ElementArray a37 = this.bitElementArray;
                    java.awt.Point a38 = a37.getLocation();
                    int i17 = a38.x;
                    int i18 = i17 + i13;
                    int i19 = i18 + 30;
                    com.cim.AIA.ElementArray a39 = this.bitElementArray;
                    java.awt.Point a40 = a39.getLocation();
                    int i20 = a40.y;
                    java.awt.Point a41 = new java.awt.Point(i19, i20);
                    a36.setPosition(a41);
                    com.cim.AIA.ElementArray a42 = this.bitElementArray;
                    int i21 = a42.getHeight();
                    int i22 = i21 + 30;
                    int i23 = i22 + 20;
                    this.approximateBitElementGap = i23;
                }
                int i24 = this.approximateBitElementGap;
                int i25 = i12 + i24;
                i = i25;
            }
            com.cim.AIA.ExtendedHierarchyTree a43 = this.hierarchyTree;
            label4: {
                label3: {
                    if(a43 != null)
                    {
                        break label3;
                    }
                    com.cim.AIA.Node a44 = new com.cim.AIA.Node((Object)"", 0);
                    this.ptr = null;
                    this.parent = null;
                    java.awt.Color a45 = java.awt.Color.white;
                    a44.setBackgroundColor(a45);
                    com.cim.AIA.ExtendedHierarchyTree a46 = new com.cim.AIA.ExtendedHierarchyTree(a44);
                    this.hierarchyTree = a46;
                    com.cim.AIA.ExtendedHierarchyTree a47 = this.hierarchyTree;
                    a47.plantTree(i2, i);
                    com.cim.AIA.ExtendedHierarchyTree a48 = this.hierarchyTree;
                    java.awt.Rectangle a49 = a48.getRectangle();
                    int i26 = a49.height;
                    int i27 = 30 + i26;
                    int i28 = i + i27;
                    i0 = i28;
                    break label4;
                }
                java.awt.Dimension a50 = this.getParentSize();
                int i29 = a50.width;
                com.cim.AIA.ExtendedHierarchyTree a51 = this.hierarchyTree;
                a51.plantTree(i2, i);
                com.cim.AIA.ExtendedHierarchyTree a52 = this.hierarchyTree;
                java.awt.Rectangle a53 = a52.getRectangle();
                int i30 = a53.x;
                if(i30 < 0)
                {
                    com.cim.AIA.ExtendedHierarchyTree a54 = this.hierarchyTree;
                    int i31 = i2 - i30;
                    a54.plantTree(i31, i);
                }
                com.cim.AIA.Node a55 = a4.getParentNode();
                this.parent = a55;
                com.cim.AIA.Node a56 = a4.getPtrNode();
                this.ptr = a56;
                com.cim.AIA.ExtendedHierarchyTree a57 = this.hierarchyTree;
                java.awt.Rectangle a58 = a57.getRectangle();
                int i32 = a58.height;
                int i33 = 30 + i32;
                int i34 = i + i33;
                i0 = i34;
            }
            com.cim.AIA.ElementArray a59 = this.searchElementArray;
            if(a59 == null)
            {
                break label0;
            }
            java.awt.Dimension a60 = this.getParentSize();
            int i35 = a60.width;
            java.awt.Dimension a61 = this.getParentSize();
            int i36 = a61.height;
            com.cim.AIA.ElementArray a62 = this.bitElementArray;
            label5: {
                if(a62 == null)
                {
                    break label5;
                }
                com.cim.AIA.Node a63 = this.highlightedNode;
                if(a63 == null)
                {
                    break label5;
                }
                com.cim.AIA.Node a64 = a4.getBitNode();
                this.currentBit = a64;
                com.cim.AIA.Node a65 = this.highlightedNode;
                Object a66 = a65.getObject();
                com.cim.AIA.Node a67 = new com.cim.AIA.Node(a66, 0);
                this.bitValueNode = a67;
                com.cim.AIA.Node a68 = this.bitValueNode;
                com.cim.AIA.Node a69 = this.highlightedNode;
                java.awt.Color a70 = a69.getBackgroundColor();
                a68.setBackgroundColor(a70);
                com.cim.AIA.ElementArray a71 = this.bitElementArray;
                a71.setElementWidth(20);
                com.cim.AIA.ElementArray a72 = this.bitElementArray;
                a72.setAllHaveSameWidth(true);
                com.cim.AIA.ElementArray a73 = this.bitElementArray;
                int i37 = a73.getWidth();
                if(i37 >= i35)
                {
                    com.cim.AIA.ElementArray a74 = this.bitElementArray;
                    a74.setLocation(0, i0);
                }
                else
                {
                    com.cim.AIA.ElementArray a75 = this.bitElementArray;
                    com.cim.AIA.ElementArray a76 = this.bitElementArray;
                    int i38 = a76.getWidth();
                    int i39 = i38 / 2;
                    int i40 = i2 - i39;
                    a75.setLocation(i40, i0);
                }
                com.cim.AIA.Node a77 = this.bitValueNode;
                com.cim.AIA.ElementArray a78 = this.bitElementArray;
                java.awt.Point a79 = a78.getLocation();
                int i41 = a79.x;
                int i42 = i41 + i37;
                int i43 = i42 + 30;
                com.cim.AIA.ElementArray a80 = this.bitElementArray;
                java.awt.Point a81 = a80.getLocation();
                int i44 = a81.y;
                java.awt.Point a82 = new java.awt.Point(i43, i44);
                a77.setPosition(a82);
                com.cim.AIA.ElementArray a83 = this.bitElementArray;
                int i45 = a83.getHeight();
                int i46 = i45 + 30;
                int i47 = i46 + 20;
                this.approximateBitElementGap = i47;
            }
            int i48 = this.approximateBitElementGap;
            int i49 = i0 + i48;
            com.cim.AIA.ElementArray a84 = this.searchElementArray;
            a84.setElementWidth(20);
            com.cim.AIA.ElementArray a85 = this.searchElementArray;
            a85.setAllHaveSameWidth(true);
            com.cim.AIA.ElementArray a86 = this.searchElementArray;
            int i50 = a86.getWidth();
            if(i50 >= i35)
            {
                com.cim.AIA.ElementArray a87 = this.searchElementArray;
                a87.setLocation(0, i49);
            }
            else
            {
                com.cim.AIA.ElementArray a88 = this.searchElementArray;
                com.cim.AIA.ElementArray a89 = this.searchElementArray;
                int i51 = a89.getWidth();
                int i52 = i51 / 2;
                int i53 = i2 - i52;
                a88.setLocation(i53, i49);
            }
            com.cim.AIA.ElementArray a90 = this.searchElementArray;
            int i54 = a90.getHeight();
            com.cim.AIA.ElementArray a91 = this.searchElementArray;
            this.addSelectable((com.cim.AIA.Selectable)a91);
        }
        this.repaint();
    }
    
    static
    {
        java.awt.Color a = java.awt.Color.gray;
        DigitalSearchTreeCanvas.POINTER_COLOR = a;
        java.awt.Color a0 = java.awt.Color.black;
        DigitalSearchTreeCanvas.TEXT_COLOR = a0;
        String s = localization.Messages.getString("DigitalSearchTreeCanvas.0");
        DigitalSearchTreeCanvas.BIT_ARRAY_LABEL = s;
        String s0 = localization.Messages.getString("DigitalSearchTreeCanvas.1");
        DigitalSearchTreeCanvas.SEARCH_LABEL = s0;
    }
}