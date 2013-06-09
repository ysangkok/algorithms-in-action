public class HeapSortCanvas extends com.cim.AIA.AlgorithmCanvas {
    final private static long serialVersionUID = -207986892246468893L;
    protected HeapSort heapSort;
    protected com.cim.AIA.HierarchyTree hierarchyTree;
    protected com.cim.AIA.ElementArray elementArray;
    protected int xBuffer;
    protected int yBuffer;
    protected boolean displayedBefore;
    protected int heapSortWidth;
    protected java.awt.Point iPoint;
    protected java.awt.Point jPoint;
    protected java.awt.Point kPoint;
    protected com.cim.AIA.Node iNode;
    protected com.cim.AIA.Node jNode;
    protected com.cim.AIA.Node kNode;
    protected int gapBetweenArrayAndTree;
    protected boolean drawMarkers;
    protected java.awt.Color textColor;
    
    public HeapSortCanvas()
    {
        super();
        this.xBuffer = 40;
        this.yBuffer = 150;
        this.displayedBefore = false;
        this.heapSortWidth = -1;
        this.iPoint = null;
        this.jPoint = null;
        this.kPoint = null;
        this.iNode = null;
        this.jNode = null;
        this.kNode = null;
        this.gapBetweenArrayAndTree = 100;
        this.drawMarkers = true;
        java.awt.Color a = java.awt.Color.black;
        this.textColor = a;
    }
    
    public void displayAlgorithm(java.awt.Graphics a)
    {
        com.cim.AIA.ElementArray a0 = this.elementArray;
        {
            label1: {
                label0: {
                    if(a0 == null)
                    {
                        break label0;
                    }
                    com.cim.AIA.HierarchyTree a1 = this.hierarchyTree;
                    if(a1 == null)
                    {
                        break label0;
                    }
                    com.cim.AIA.ElementArray a2 = this.elementArray;
                    a2.draw(a);
                    com.cim.AIA.HierarchyTree a3 = this.hierarchyTree;
                    a3.draw(a);
                    int i = this.drawMarkers?1:0;
                    if(i == 0)
                    {
                        break label1;
                    }
                    java.awt.Color a4 = this.textColor;
                    a.setColor(a4);
                    java.awt.Point a5 = this.iPoint;
                    if(a5 != null)
                    {
                        java.awt.Point a6 = this.iPoint;
                        int i0 = a6.x;
                        java.awt.FontMetrics a7 = a.getFontMetrics();
                        int i1 = a7.stringWidth("i");
                        int i2 = i1 / 2;
                        int i3 = i0 - i2;
                        java.awt.Point a8 = this.iPoint;
                        int i4 = a8.y;
                        java.awt.FontMetrics a9 = a.getFontMetrics();
                        int i5 = a9.getHeight();
                        int i6 = 2 * i5;
                        int i7 = i4 + i6;
                        a.drawString("i", i3, i7);
                    }
                    java.awt.Point a10 = this.jPoint;
                    if(a10 != null)
                    {
                        java.awt.Point a11 = this.jPoint;
                        int i8 = a11.x;
                        java.awt.FontMetrics a12 = a.getFontMetrics();
                        int i9 = a12.stringWidth("J");
                        int i10 = i9 / 2;
                        int i11 = i8 - i10;
                        java.awt.Point a13 = this.jPoint;
                        int i12 = a13.y;
                        java.awt.FontMetrics a14 = a.getFontMetrics();
                        int i13 = a14.getHeight();
                        int i14 = 3 * i13;
                        int i15 = i12 + i14;
                        a.drawString("J", i11, i15);
                    }
                    java.awt.Point a15 = this.kPoint;
                    if(a15 != null)
                    {
                        java.awt.Point a16 = this.kPoint;
                        int i16 = a16.x;
                        java.awt.FontMetrics a17 = a.getFontMetrics();
                        int i17 = a17.stringWidth("k");
                        int i18 = i17 / 2;
                        int i19 = i16 - i18;
                        java.awt.Point a18 = this.kPoint;
                        int i20 = a18.y;
                        java.awt.FontMetrics a19 = a.getFontMetrics();
                        int i21 = a19.getHeight();
                        int i22 = 1 * i21;
                        int i23 = i20 + i22;
                        a.drawString("k", i19, i23);
                    }
                    com.cim.AIA.Node a20 = this.kNode;
                    label2: {
                        if(a20 == null)
                        {
                            break label2;
                        }
                        com.cim.AIA.Node a21 = this.kNode;
                        java.awt.Point a22 = a21.getPosition();
                        int i24 = a22.x;
                        com.cim.AIA.Node a23 = this.kNode;
                        int i25 = a23.getWidth();
                        int i26 = i25 / 2;
                        int i27 = i24 + i26;
                        int i28 = this.gapBetweenArrayAndTree;
                        int i29 = this.yBuffer;
                        int i30 = i28 + i29;
                        java.awt.FontMetrics a24 = a.getFontMetrics();
                        int i31 = a24.getHeight();
                        int i32 = 1 * i31;
                        int i33 = i30 - i32;
                        com.cim.AIA.Node a25 = this.kNode;
                        java.awt.Point a26 = a25.getPosition();
                        int i34 = a26.x;
                        com.cim.AIA.Node a27 = this.kNode;
                        int i35 = a27.getWidth();
                        int i36 = i35 / 2;
                        int i37 = i34 + i36;
                        com.cim.AIA.Node a28 = this.kNode;
                        java.awt.Point a29 = a28.getPosition();
                        int i38 = a29.y;
                        com.cim.AIA.Line a30 = new com.cim.AIA.Line(i27, i33, i37, i38);
                        int i39 = a30.getX();
                        if(i39 == 0)
                        {
                            break label2;
                        }
                        int i40 = a30.getY();
                        if(i40 != 0)
                        {
                            a30.showArrowHead(true);
                            a30.setDistanceFromStartForArrowHead(-3);
                            a30.draw(a);
                            com.cim.AIA.Node a31 = this.kNode;
                            java.awt.Point a32 = a31.getPosition();
                            int i41 = a32.x;
                            com.cim.AIA.Node a33 = this.kNode;
                            int i42 = a33.getWidth();
                            int i43 = i42 / 2;
                            int i44 = i41 + i43;
                            java.awt.FontMetrics a34 = a.getFontMetrics();
                            int i45 = a34.stringWidth("k");
                            int i46 = i45 / 2;
                            int i47 = i44 - i46;
                            int i48 = this.gapBetweenArrayAndTree;
                            int i49 = this.yBuffer;
                            int i50 = i48 + i49;
                            java.awt.FontMetrics a35 = a.getFontMetrics();
                            int i51 = a35.getHeight();
                            int i52 = 1 * i51;
                            int i53 = i50 - i52;
                            a.drawString("k", i47, i53);
                        }
                    }
                    com.cim.AIA.Node a36 = this.iNode;
                    label3: {
                        if(a36 == null)
                        {
                            break label3;
                        }
                        java.awt.Dimension a37 = this.getSize();
                        int i54 = a37.width;
                        int i55 = i54 / 3;
                        int i56 = this.yBuffer;
                        int i57 = this.gapBetweenArrayAndTree;
                        int i58 = i56 + i57;
                        com.cim.AIA.HierarchyTree a38 = this.hierarchyTree;
                        int i59 = a38.getTotalHeight();
                        int i60 = i58 + i59;
                        java.awt.FontMetrics a39 = a.getFontMetrics();
                        int i61 = a39.getHeight();
                        int i62 = i60 + i61;
                        com.cim.AIA.Node a40 = this.iNode;
                        java.awt.Point a41 = a40.getPosition();
                        int i63 = a41.x;
                        com.cim.AIA.Node a42 = this.iNode;
                        int i64 = a42.getWidth();
                        int i65 = i64 / 2;
                        int i66 = i63 + i65;
                        com.cim.AIA.Node a43 = this.iNode;
                        java.awt.Point a44 = a43.getPosition();
                        int i67 = a44.y;
                        com.cim.AIA.Node a45 = this.iNode;
                        int i68 = a45.getHeight();
                        int i69 = i67 + i68;
                        com.cim.AIA.Line a46 = new com.cim.AIA.Line(i55, i62, i66, i69);
                        int i70 = a46.getX();
                        if(i70 == 0)
                        {
                            break label3;
                        }
                        int i71 = a46.getY();
                        if(i71 != 0)
                        {
                            a46.showArrowHead(true);
                            a46.setDistanceFromStartForArrowHead(-3);
                            a46.draw(a);
                            java.awt.Dimension a47 = this.getSize();
                            int i72 = a47.width;
                            int i73 = i72 / 3;
                            java.awt.FontMetrics a48 = a.getFontMetrics();
                            int i74 = a48.stringWidth("i");
                            int i75 = i74 / 2;
                            int i76 = i73 - i75;
                            int i77 = this.gapBetweenArrayAndTree;
                            int i78 = this.yBuffer;
                            int i79 = i77 + i78;
                            com.cim.AIA.HierarchyTree a49 = this.hierarchyTree;
                            int i80 = a49.getTotalHeight();
                            int i81 = i79 + i80;
                            java.awt.FontMetrics a50 = a.getFontMetrics();
                            int i82 = a50.getHeight();
                            int i83 = 2 * i82;
                            int i84 = i81 + i83;
                            a.drawString("i", i76, i84);
                        }
                    }
                    com.cim.AIA.Node a51 = this.jNode;
                    if(a51 == null)
                    {
                        break label0;
                    }
                    java.awt.Dimension a52 = this.getSize();
                    int i85 = a52.width;
                    int i86 = 2 * i85;
                    int i87 = i86 / 3;
                    int i88 = this.yBuffer;
                    int i89 = this.gapBetweenArrayAndTree;
                    int i90 = i88 + i89;
                    com.cim.AIA.HierarchyTree a53 = this.hierarchyTree;
                    int i91 = a53.getTotalHeight();
                    int i92 = i90 + i91;
                    java.awt.FontMetrics a54 = a.getFontMetrics();
                    int i93 = a54.getHeight();
                    int i94 = i92 + i93;
                    com.cim.AIA.Node a55 = this.jNode;
                    java.awt.Point a56 = a55.getPosition();
                    int i95 = a56.x;
                    com.cim.AIA.Node a57 = this.jNode;
                    int i96 = a57.getWidth();
                    int i97 = i96 / 2;
                    int i98 = i95 + i97;
                    com.cim.AIA.Node a58 = this.jNode;
                    java.awt.Point a59 = a58.getPosition();
                    int i99 = a59.y;
                    com.cim.AIA.Node a60 = this.jNode;
                    int i100 = a60.getHeight();
                    int i101 = i99 + i100;
                    com.cim.AIA.Line a61 = new com.cim.AIA.Line(i87, i94, i98, i101);
                    int i102 = a61.getX();
                    if(i102 == 0)
                    {
                        break label0;
                    }
                    int i103 = a61.getY();
                    if(i103 != 0)
                    {
                        a61.showArrowHead(true);
                        a61.setDistanceFromStartForArrowHead(-3);
                        a61.draw(a);
                        java.awt.Dimension a62 = this.getSize();
                        int i104 = a62.width;
                        int i105 = 2 * i104;
                        int i106 = i105 / 3;
                        java.awt.FontMetrics a63 = a.getFontMetrics();
                        int i107 = a63.stringWidth("J");
                        int i108 = i107 / 2;
                        int i109 = i106 - i108;
                        int i110 = this.gapBetweenArrayAndTree;
                        int i111 = this.yBuffer;
                        int i112 = i110 + i111;
                        com.cim.AIA.HierarchyTree a64 = this.hierarchyTree;
                        int i113 = a64.getTotalHeight();
                        int i114 = i112 + i113;
                        java.awt.FontMetrics a65 = a.getFontMetrics();
                        int i115 = a65.getHeight();
                        int i116 = 2 * i115;
                        int i117 = i114 + i116;
                        a.drawString("J", i109, i117);
                    }
                }
            }
        }
    }
    
    protected void handleColorSelection(com.cim.AIA.ColorSelection a)
    {
        String s = a.getAtributeName();
        String s0 = com.cim.AIA.ColorSelection.TEXT_COLOR;
        int i = s.equalsIgnoreCase(s0)?1:0;
        if(i != 0)
        {
            java.awt.Color a0 = a.getColor();
            this.textColor = a0;
        }
    }
    
    protected void handleFontSelection(com.cim.AIA.FontSelection a)
    {
    }
    
    public void processRepaintEvent(com.cim.AIA.RepaintEvent a)
    {
        Object a0 = a.paramObj;
        label0: {
            if(a0 == null)
            {
                break label0;
            }
            this.kPoint = null;
            this.jPoint = null;
            this.iPoint = null;
            this.kNode = null;
            this.jNode = null;
            this.iNode = null;
            Object a1 = a.paramObj;
            HeapSort dummy = (HeapSort)a1;
            HeapSort a2 = (HeapSort)a1;
            this.heapSort = a2;
            this.removeAllSelectables();
            HeapSort a3 = this.heapSort;
            int i = this.xBuffer;
            int i0 = this.yBuffer;
            java.awt.Point a4 = new java.awt.Point(i, i0);
            com.cim.AIA.ElementArray a5 = a3.getElementArray(a4);
            this.elementArray = a5;
            HeapSort a6 = this.heapSort;
            com.cim.AIA.HierarchyTree a7 = a6.getHierarchyTree();
            this.hierarchyTree = a7;
            com.cim.AIA.ElementArray a8 = this.elementArray;
            this.addSelectable((com.cim.AIA.Selectable)a8);
            com.cim.AIA.HierarchyTree a9 = this.hierarchyTree;
            this.addSelectable((com.cim.AIA.Selectable)a9);
            com.cim.AIA.ElementArray a10 = this.elementArray;
            int i1 = a10.getWidth();
            int i2 = this.xBuffer;
            int i3 = 2 * i2;
            int i4 = i1 + i3;
            int i5 = this.yBuffer;
            int i6 = 3 * i5;
            this.setSize(i4, i6);
            java.awt.Dimension a11 = this.getSize();
            int i7 = a11.width;
            this.heapSortWidth = i7;
            com.cim.AIA.ElementArray a12 = this.elementArray;
            int i8 = this.heapSortWidth;
            int i9 = i8 / 2;
            int i10 = i1 / 2;
            int i11 = i9 - i10;
            int i12 = this.yBuffer;
            a12.setLocation(i11, i12);
            com.cim.AIA.HierarchyTree a13 = this.hierarchyTree;
            int i13 = this.heapSortWidth;
            int i14 = i13 / 2;
            int i15 = this.gapBetweenArrayAndTree;
            int i16 = this.yBuffer;
            int i17 = i15 + i16;
            a13.plantTree(i14, i17);
            HeapSort a14 = this.heapSort;
            int i18 = a14.getI();
            label1: {
                if(i18 == -10)
                {
                    break label1;
                }
                com.cim.AIA.ElementArray a15 = this.elementArray;
                HeapSort a16 = this.heapSort;
                int i19 = a16.getI();
                com.cim.AIA.Element a17 = a15.getElement(i19);
                com.cim.AIA.VerticalBar dummy0 = (com.cim.AIA.VerticalBar)a17;
                com.cim.AIA.VerticalBar a18 = (com.cim.AIA.VerticalBar)a17;
                if(a18 != null)
                {
                    java.awt.Point a19 = a18.getPosition();
                    int i20 = a19.x;
                    com.cim.AIA.ElementArray a20 = this.elementArray;
                    int i21 = a20.getElementWidth();
                    int i22 = i21 / 2;
                    int i23 = i20 + i22;
                    java.awt.Point a21 = a18.getPosition();
                    int i24 = a21.y;
                    java.awt.Graphics a22 = this.getGraphics();
                    int i25 = a18.getRealOffsetForValue(a22);
                    int i26 = i24 + i25;
                    java.awt.Point a23 = new java.awt.Point(i23, i26);
                    this.iPoint = a23;
                }
                HeapSort a24 = this.heapSort;
                HeapSort a25 = this.heapSort;
                int i27 = a25.getI();
                com.cim.AIA.Node a26 = a24.getNode(i27);
                this.iNode = a26;
            }
            HeapSort a27 = this.heapSort;
            int i28 = a27.getJ();
            label2: {
                if(i28 == -10)
                {
                    break label2;
                }
                com.cim.AIA.ElementArray a28 = this.elementArray;
                HeapSort a29 = this.heapSort;
                int i29 = a29.getJ();
                com.cim.AIA.Element a30 = a28.getElement(i29);
                com.cim.AIA.VerticalBar dummy1 = (com.cim.AIA.VerticalBar)a30;
                com.cim.AIA.VerticalBar a31 = (com.cim.AIA.VerticalBar)a30;
                if(a31 != null)
                {
                    java.awt.Point a32 = a31.getPosition();
                    int i30 = a32.x;
                    com.cim.AIA.ElementArray a33 = this.elementArray;
                    int i31 = a33.getElementWidth();
                    int i32 = i31 / 2;
                    int i33 = i30 + i32;
                    java.awt.Point a34 = a31.getPosition();
                    int i34 = a34.y;
                    java.awt.Graphics a35 = this.getGraphics();
                    int i35 = a31.getRealOffsetForValue(a35);
                    int i36 = i34 + i35;
                    java.awt.Point a36 = new java.awt.Point(i33, i36);
                    this.jPoint = a36;
                }
                HeapSort a37 = this.heapSort;
                HeapSort a38 = this.heapSort;
                int i37 = a38.getJ();
                com.cim.AIA.Node a39 = a37.getNode(i37);
                this.jNode = a39;
            }
            HeapSort a40 = this.heapSort;
            int i38 = a40.getK();
            label3: {
                if(i38 == -10)
                {
                    break label3;
                }
                com.cim.AIA.ElementArray a41 = this.elementArray;
                HeapSort a42 = this.heapSort;
                int i39 = a42.getK();
                com.cim.AIA.Element a43 = a41.getElement(i39);
                com.cim.AIA.VerticalBar dummy2 = (com.cim.AIA.VerticalBar)a43;
                com.cim.AIA.VerticalBar a44 = (com.cim.AIA.VerticalBar)a43;
                if(a44 != null)
                {
                    java.awt.Point a45 = a44.getPosition();
                    int i40 = a45.x;
                    com.cim.AIA.ElementArray a46 = this.elementArray;
                    int i41 = a46.getElementWidth();
                    int i42 = i41 / 2;
                    int i43 = i40 + i42;
                    java.awt.Graphics a47 = this.getGraphics();
                    int i44 = a44.getRealOffsetForValue(a47);
                    java.awt.Point a48 = new java.awt.Point(i43, i44);
                    this.kPoint = a48;
                }
                HeapSort a49 = this.heapSort;
                HeapSort a50 = this.heapSort;
                int i45 = a50.getK();
                com.cim.AIA.Node a51 = a49.getNode(i45);
                this.kNode = a51;
            }
            HeapSort a52 = this.heapSort;
            com.cim.AIA.ElementArray a53 = this.elementArray;
            int i46 = this.numberOfTweenSteps;
            com.cim.AIA.MultipleTween a54 = a52.generateTweens((com.cim.AIA.Paintable)this, (Object)a53, i46);
            this.addTween((com.cim.AIA.Tween)a54);
            HeapSort a55 = this.heapSort;
            a55.removeAllAnimationRequests();
            com.cim.AIA.MultipleTween a56 = this.tweens;
            a56.run();
            this.repaint();
            HeapSort a57 = this.heapSort;
            a57.unHighlightAll();
        }
    }
    
    public void setMarkersEnabled(boolean b)
    {
        this.drawMarkers = b;
        this.repaint();
    }
}