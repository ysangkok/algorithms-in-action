public class BinarySearchTreeCanvas extends com.cim.AIA.AlgorithmCanvas {
    final private static long serialVersionUID = 1807329509121293049L;
    final private static java.awt.Color POINTER_COLOR;
    final private static java.awt.Color TEXT_COLOR;
    final private static String INSERT_LABEL;
    final private static String SEARCH_LABEL;
    final private static int GAP = 30;
    final private static int ELEMENT_WIDTH = 20;
    private com.cim.AIA.ExtendedHierarchyTree hierarchyTree;
    private com.cim.AIA.ElementArray insertElementArray;
    private com.cim.AIA.ElementArray searchElementArray;
    private com.cim.AIA.Node deleteNode;
    private com.cim.AIA.Node parent;
    private com.cim.AIA.Node ptr;
    private com.cim.AIA.Node dataItem;
    private com.cim.AIA.Node replace;
    private java.util.Vector newLinks;
    
    public BinarySearchTreeCanvas()
    {
        super();
    }
    
    public void displayAlgorithm(java.awt.Graphics a)
    {
        java.awt.Color a0 = BinarySearchTreeCanvas.TEXT_COLOR;
        a.setColor(a0);
        com.cim.AIA.ElementArray a1 = this.insertElementArray;
        if(a1 != null)
        {
            com.cim.AIA.ElementArray a2 = this.insertElementArray;
            a2.draw(a);
            String s = BinarySearchTreeCanvas.INSERT_LABEL;
            java.awt.Dimension a3 = this.getParentSize();
            int i = a3.width;
            int i0 = i / 2;
            java.awt.FontMetrics a4 = a.getFontMetrics();
            String s0 = BinarySearchTreeCanvas.INSERT_LABEL;
            int i1 = a4.stringWidth(s0);
            int i2 = i1 / 2;
            int i3 = i0 - i2;
            com.cim.AIA.ElementArray a5 = this.insertElementArray;
            java.awt.Point a6 = a5.getLocation();
            int i4 = a6.y;
            a.drawString(s, i3, i4);
        }
        com.cim.AIA.ElementArray a7 = this.searchElementArray;
        if(a7 != null)
        {
            com.cim.AIA.ElementArray a8 = this.searchElementArray;
            a8.draw(a);
            String s1 = BinarySearchTreeCanvas.SEARCH_LABEL;
            java.awt.Dimension a9 = this.getParentSize();
            int i5 = a9.width;
            int i6 = i5 / 2;
            java.awt.FontMetrics a10 = a.getFontMetrics();
            String s2 = BinarySearchTreeCanvas.SEARCH_LABEL;
            int i7 = a10.stringWidth(s2);
            int i8 = i7 / 2;
            int i9 = i6 - i8;
            com.cim.AIA.ElementArray a11 = this.searchElementArray;
            java.awt.Point a12 = a11.getLocation();
            int i10 = a12.y;
            a.drawString(s1, i9, i10);
        }
        com.cim.AIA.Node a13 = this.deleteNode;
        if(a13 != null)
        {
            com.cim.AIA.Node a14 = this.deleteNode;
            a14.draw(a);
        }
        com.cim.AIA.ExtendedHierarchyTree a15 = this.hierarchyTree;
        label0: {
            if(a15 == null)
            {
                break label0;
            }
            com.cim.AIA.ExtendedHierarchyTree a16 = this.hierarchyTree;
            a16.draw(a);
            com.cim.AIA.Node a17 = this.parent;
            if(a17 != null)
            {
                java.awt.Dimension a18 = this.getSize();
                int i11 = a18.width;
                int i12 = i11 / 4;
                java.awt.FontMetrics a19 = a.getFontMetrics();
                int i13 = a19.getHeight();
                com.cim.AIA.ExtendedHierarchyTree a20 = this.hierarchyTree;
                java.awt.Point a21 = a20.getPosition();
                int i14 = a21.y;
                int i15 = i13 + i14;
                java.awt.Point a22 = new java.awt.Point(i12, i15);
                int i16 = a22.x;
                int i17 = a22.y;
                java.awt.FontMetrics a23 = a.getFontMetrics();
                int i18 = a23.getHeight();
                int i19 = i18 / 2;
                int i20 = i17 - i19;
                com.cim.AIA.Node a24 = this.parent;
                int i21 = a24.getX();
                com.cim.AIA.Node a25 = this.parent;
                int i22 = a25.getY();
                com.cim.AIA.Line a26 = new com.cim.AIA.Line(i16, i20, i21, i22);
                String s3 = BinarySearchTree.PARENT_LABEL;
                a26.setLabel(s3);
                a26.setDistanceFromStartForArrowHead(-3);
                a26.setDistanceFromStartForLabel(-1);
                java.awt.FontMetrics a27 = a.getFontMetrics();
                String s4 = BinarySearchTree.PARENT_LABEL;
                int i23 = a27.stringWidth(s4);
                int i24 = 0 - i23;
                int i25 = i24 / 2;
                a26.setXLabelOffset(i25);
                a26.showArrowHead(true);
                java.awt.Color a28 = BinarySearchTreeCanvas.POINTER_COLOR;
                a26.setColor(a28);
                a26.draw(a);
            }
            com.cim.AIA.Node a29 = this.ptr;
            if(a29 != null)
            {
                java.awt.Dimension a30 = this.getSize();
                int i26 = a30.width;
                int i27 = i26 / 4;
                java.awt.FontMetrics a31 = a.getFontMetrics();
                int i28 = a31.getHeight();
                com.cim.AIA.ExtendedHierarchyTree a32 = this.hierarchyTree;
                java.awt.Point a33 = a32.getPosition();
                int i29 = a33.y;
                int i30 = i28 + i29;
                int i31 = i30 + 20;
                java.awt.Point a34 = new java.awt.Point(i27, i31);
                int i32 = a34.x;
                int i33 = a34.y;
                java.awt.FontMetrics a35 = a.getFontMetrics();
                int i34 = a35.getHeight();
                int i35 = i34 / 2;
                int i36 = i33 - i35;
                com.cim.AIA.Node a36 = this.ptr;
                int i37 = a36.getX();
                com.cim.AIA.Node a37 = this.ptr;
                int i38 = a37.getY();
                com.cim.AIA.Line a38 = new com.cim.AIA.Line(i32, i36, i37, i38);
                String s5 = BinarySearchTree.PTR_LABEL;
                a38.setLabel(s5);
                a38.setDistanceFromStartForArrowHead(-3);
                a38.setDistanceFromStartForLabel(-1);
                java.awt.FontMetrics a39 = a.getFontMetrics();
                String s6 = BinarySearchTree.PTR_LABEL;
                int i39 = a39.stringWidth(s6);
                int i40 = 0 - i39;
                int i41 = i40 / 2;
                a38.setXLabelOffset(i41);
                a38.showArrowHead(true);
                java.awt.Color a40 = BinarySearchTreeCanvas.POINTER_COLOR;
                a38.setColor(a40);
                a38.draw(a);
            }
            com.cim.AIA.Node a41 = this.dataItem;
            if(a41 != null)
            {
                java.awt.Dimension a42 = this.getSize();
                int i42 = a42.width;
                int i43 = 3 * i42;
                int i44 = i43 / 4;
                java.awt.FontMetrics a43 = a.getFontMetrics();
                int i45 = a43.getHeight();
                com.cim.AIA.ExtendedHierarchyTree a44 = this.hierarchyTree;
                java.awt.Point a45 = a44.getPosition();
                int i46 = a45.y;
                int i47 = i45 + i46;
                java.awt.Point a46 = new java.awt.Point(i44, i47);
                int i48 = a46.x;
                int i49 = a46.y;
                java.awt.FontMetrics a47 = a.getFontMetrics();
                int i50 = a47.getHeight();
                int i51 = i50 / 2;
                int i52 = i49 - i51;
                com.cim.AIA.Node a48 = this.dataItem;
                int i53 = a48.getX();
                com.cim.AIA.Node a49 = this.dataItem;
                int i54 = a49.getWidth();
                int i55 = i53 + i54;
                com.cim.AIA.Node a50 = this.dataItem;
                int i56 = a50.getY();
                com.cim.AIA.Line a51 = new com.cim.AIA.Line(i48, i52, i55, i56);
                String s7 = BinarySearchTree.DATAITEMPTR_LABEL;
                a51.setLabel(s7);
                a51.setDistanceFromStartForArrowHead(-3);
                a51.setDistanceFromStartForLabel(-1);
                java.awt.FontMetrics a52 = a.getFontMetrics();
                String s8 = BinarySearchTree.PTR_LABEL;
                int i57 = a52.stringWidth(s8);
                int i58 = 0 - i57;
                int i59 = i58 / 2;
                a51.setXLabelOffset(i59);
                a51.showArrowHead(true);
                java.awt.Color a53 = BinarySearchTreeCanvas.POINTER_COLOR;
                a51.setColor(a53);
                a51.draw(a);
            }
            com.cim.AIA.Node a54 = this.replace;
            if(a54 != null)
            {
                java.awt.Dimension a55 = this.getSize();
                int i60 = a55.width;
                int i61 = 3 * i60;
                int i62 = i61 / 4;
                java.awt.FontMetrics a56 = a.getFontMetrics();
                int i63 = a56.getHeight();
                com.cim.AIA.ExtendedHierarchyTree a57 = this.hierarchyTree;
                java.awt.Point a58 = a57.getPosition();
                int i64 = a58.y;
                int i65 = i63 + i64;
                int i66 = i65 + 20;
                java.awt.Point a59 = new java.awt.Point(i62, i66);
                int i67 = a59.x;
                int i68 = a59.y;
                java.awt.FontMetrics a60 = a.getFontMetrics();
                int i69 = a60.getHeight();
                int i70 = i69 / 2;
                int i71 = i68 - i70;
                com.cim.AIA.Node a61 = this.replace;
                int i72 = a61.getX();
                com.cim.AIA.Node a62 = this.replace;
                int i73 = a62.getWidth();
                int i74 = i72 + i73;
                com.cim.AIA.Node a63 = this.replace;
                int i75 = a63.getY();
                com.cim.AIA.Line a64 = new com.cim.AIA.Line(i67, i71, i74, i75);
                String s9 = BinarySearchTree.REPLACEMENT_LABEL;
                a64.setLabel(s9);
                a64.setDistanceFromStartForArrowHead(-3);
                a64.setDistanceFromStartForLabel(-1);
                java.awt.FontMetrics a65 = a.getFontMetrics();
                String s10 = BinarySearchTree.PTR_LABEL;
                int i76 = a65.stringWidth(s10);
                int i77 = 0 - i76;
                int i78 = i77 / 2;
                a64.setXLabelOffset(i78);
                a64.showArrowHead(true);
                java.awt.Color a66 = BinarySearchTreeCanvas.POINTER_COLOR;
                a64.setColor(a66);
                a64.draw(a);
            }
            int i79 = 0;
            while(true)
            {
                java.util.Vector a67 = this.newLinks;
                int i80 = a67.size();
                if(i79 >= i80)
                {
                    break;
                }
                else
                {
                    java.util.Vector a68 = this.newLinks;
                    Object a69 = a68.elementAt(i79);
                    NewLinks dummy = (NewLinks)a69;
                    NewLinks a70 = (NewLinks)a69;
                    com.cim.AIA.Line a71 = a70.getLine();
                    a71.draw(a);
                    int i81 = i79 + 1;
                    i79 = i81;
                }
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
        DeleteSelection a1 = DeleteSelection.getInstance();
        this.removeSelectionListener((com.cim.AIA.SelectionListener)a1);
        Object a2 = a.paramObj;
        label0: {
            int i = 0;
            int i0 = 0;
            if(a2 == null)
            {
                break label0;
            }
            java.awt.Dimension a3 = this.getParentSize();
            int i1 = a3.width;
            int i2 = i1 / 2;
            Object a4 = a.paramObj;
            BinarySearchTree dummy = (BinarySearchTree)a4;
            BinarySearchTree a5 = (BinarySearchTree)a4;
            com.cim.AIA.ExtendedHierarchyTree a6 = a5.getHierarchyTree();
            this.hierarchyTree = a6;
            int i3 = a5.isBuildMode()?1:0;
            if(i3 == 0)
            {
                this.insertElementArray = null;
            }
            else
            {
                com.cim.AIA.ElementArray a7 = a5.getInsertElementArray();
                this.insertElementArray = a7;
            }
            int i4 = a5.isSearchMode()?1:0;
            if(i4 == 0)
            {
                this.searchElementArray = null;
            }
            else
            {
                com.cim.AIA.SearchSelection a8 = com.cim.AIA.SearchSelection.getInstance();
                this.addSelectionListener((com.cim.AIA.SelectionListener)a8);
                com.cim.AIA.ElementArray a9 = a5.getSearchElementArray();
                this.searchElementArray = a9;
            }
            int i5 = a5.isDeleteMode()?1:0;
            if(i5 == 0)
            {
                this.deleteNode = null;
            }
            else
            {
                DeleteSelection a10 = DeleteSelection.getInstance();
                this.addSelectionListener((com.cim.AIA.SelectionListener)a10);
                com.cim.AIA.Node a11 = a5.getdeleteNode();
                this.deleteNode = a11;
            }
            com.cim.AIA.Node a12 = this.deleteNode;
            if(a12 != null)
            {
                com.cim.AIA.Node a13 = this.deleteNode;
                a13.setX(40);
                com.cim.AIA.Node a14 = this.deleteNode;
                a14.setY(10);
            }
            com.cim.AIA.ElementArray a15 = this.insertElementArray;
            label1: {
                if(a15 == null)
                {
                    i = 30;
                    break label1;
                }
                com.cim.AIA.ElementArray a16 = this.insertElementArray;
                a16.setElementWidth(20);
                com.cim.AIA.ElementArray a17 = this.insertElementArray;
                a17.setAllHaveSameWidth(true);
                java.awt.Dimension a18 = this.getParentSize();
                int i6 = a18.width;
                com.cim.AIA.ElementArray a19 = this.insertElementArray;
                int i7 = a19.getWidth();
                if(i7 >= i6)
                {
                    com.cim.AIA.ElementArray a20 = this.insertElementArray;
                    a20.setLocation(0, 30);
                }
                else
                {
                    com.cim.AIA.ElementArray a21 = this.insertElementArray;
                    com.cim.AIA.ElementArray a22 = this.insertElementArray;
                    int i8 = a22.getWidth();
                    int i9 = i8 / 2;
                    int i10 = i2 - i9;
                    a21.setLocation(i10, 30);
                }
                com.cim.AIA.ElementArray a23 = this.insertElementArray;
                int i11 = a23.getHeight();
                int i12 = i11 + 30;
                int i13 = 30 + i12;
                i = i13;
            }
            com.cim.AIA.ExtendedHierarchyTree a24 = this.hierarchyTree;
            label2: {
                if(a24 == null)
                {
                    i0 = i;
                    break label2;
                }
                java.awt.Dimension a25 = this.getParentSize();
                int i14 = a25.width;
                com.cim.AIA.ExtendedHierarchyTree a26 = this.hierarchyTree;
                a26.plantTree(i2, i);
                com.cim.AIA.ExtendedHierarchyTree a27 = this.hierarchyTree;
                java.awt.Rectangle a28 = a27.getRectangle();
                int i15 = a28.x;
                if(i15 < 0)
                {
                    com.cim.AIA.ExtendedHierarchyTree a29 = this.hierarchyTree;
                    int i16 = i2 - i15;
                    a29.plantTree(i16, i);
                }
                com.cim.AIA.Node a30 = a5.getParentNode();
                this.parent = a30;
                com.cim.AIA.Node a31 = a5.getPtrNode();
                this.ptr = a31;
                com.cim.AIA.Node a32 = a5.getDataItemNode();
                this.dataItem = a32;
                com.cim.AIA.Node a33 = a5.getReplaceNode();
                this.replace = a33;
                java.util.Vector a34 = a5.getNewLinks();
                this.newLinks = a34;
                com.cim.AIA.ExtendedHierarchyTree a35 = this.hierarchyTree;
                java.awt.Rectangle a36 = a35.getRectangle();
                int i17 = a36.height;
                int i18 = 30 + i17;
                int i19 = i + i18;
                com.cim.AIA.ExtendedHierarchyTree a37 = this.hierarchyTree;
                this.addSelectable((com.cim.AIA.Selectable)a37);
                i0 = i19;
            }
            com.cim.AIA.ElementArray a38 = this.searchElementArray;
            label3: {
                if(a38 == null)
                {
                    break label3;
                }
                com.cim.AIA.ElementArray a39 = this.searchElementArray;
                a39.setElementWidth(20);
                com.cim.AIA.ElementArray a40 = this.searchElementArray;
                a40.setAllHaveSameWidth(true);
                java.awt.Dimension a41 = this.getParentSize();
                int i20 = a41.width;
                java.awt.Dimension a42 = this.getParentSize();
                int i21 = a42.height;
                com.cim.AIA.ElementArray a43 = this.searchElementArray;
                int i22 = a43.getWidth();
                if(i22 >= i20)
                {
                    com.cim.AIA.ElementArray a44 = this.searchElementArray;
                    a44.setLocation(0, i0);
                }
                else
                {
                    com.cim.AIA.ElementArray a45 = this.searchElementArray;
                    com.cim.AIA.ElementArray a46 = this.searchElementArray;
                    int i23 = a46.getWidth();
                    int i24 = i23 / 2;
                    int i25 = i2 - i24;
                    a45.setLocation(i25, i0);
                }
                com.cim.AIA.ElementArray a47 = this.searchElementArray;
                this.addSelectable((com.cim.AIA.Selectable)a47);
            }
            int i26 = this.numberOfTweenSteps;
            com.cim.AIA.MultipleTween a48 = a5.generateTweens((com.cim.AIA.Paintable)this, (Object)null, i26);
            this.addTween((com.cim.AIA.Tween)a48);
            com.cim.AIA.MultipleTween a49 = this.tweens;
            int i27 = a49.getNumberOfTweens();
            if(i27 > 0)
            {
                com.cim.AIA.MultipleTween a50 = this.tweens;
                a50.run();
            }
            a5.removeAllAnimationRequests();
        }
        this.repaint();
    }
    
    static
    {
        java.awt.Color a = java.awt.Color.gray;
        BinarySearchTreeCanvas.POINTER_COLOR = a;
        java.awt.Color a0 = java.awt.Color.black;
        BinarySearchTreeCanvas.TEXT_COLOR = a0;
        String s = localization.Messages.getString("BinarySearchTreeCanvas.0");
        BinarySearchTreeCanvas.INSERT_LABEL = s;
        String s0 = localization.Messages.getString("BinarySearchTreeCanvas.1");
        BinarySearchTreeCanvas.SEARCH_LABEL = s0;
    }
}