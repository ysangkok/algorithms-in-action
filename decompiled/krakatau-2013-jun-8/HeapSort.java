public class HeapSort extends com.cim.AIA.Algorithm implements com.cim.AIA.ColorSelectionListener {
    final public static String I_MARKER = "i";
    final public static String J_MARKER = "J";
    final public static String K_MARKER = "k";
    final public static int MARKER_NOT_READY = -10;
    protected java.awt.Color doneColor;
    protected java.awt.Color activeColor;
    protected java.awt.Color background;
    protected java.awt.Color textColor;
    protected java.awt.Color highlightColor;
    protected String finishedLabel;
    protected String activeLabel;
    protected String highlightLabel;
    protected com.cim.AIA.ElementArray elementArray;
    protected int columSpacing;
    protected int columWidth;
    protected int[] elements;
    protected java.awt.Color[] colors;
    protected int N;
    protected int i;
    protected int j;
    protected int k;
    protected java.util.Vector swapRequests;
    protected java.util.Vector questions;
    protected String[] duplicateLabels;
    protected com.cim.AIA.Node[] nodes;
    
    public HeapSort(com.cim.AIA.AlgorithmThread a, Object a0)
    {
        super(a, a0);
        java.awt.Color a1 = java.awt.Color.gray;
        this.doneColor = a1;
        java.awt.Color a2 = com.cim.AIA.CodeCanvas.DEFAULT_HIGHLIGHT_COLOR;
        this.activeColor = a2;
        java.awt.Color a3 = java.awt.Color.white;
        this.background = a3;
        java.awt.Color a4 = java.awt.Color.black;
        this.textColor = a4;
        java.awt.Color a5 = java.awt.Color.yellow;
        this.highlightColor = a5;
        String s = localization.Messages.getString("HeapSort.0");
        this.finishedLabel = s;
        String s0 = localization.Messages.getString("HeapSort.1");
        this.activeLabel = s0;
        String s1 = localization.Messages.getString("HeapSort.2");
        this.highlightLabel = s1;
        this.columSpacing = 12;
        this.columWidth = 7;
        this.N = -10;
        this.i = -10;
        this.j = -10;
        this.k = -10;
        java.util.Vector a6 = new java.util.Vector();
        this.swapRequests = a6;
        java.util.Vector a7 = new java.util.Vector();
        this.questions = a7;
        int[] dummy = (int[])a0;
        int[] a8 = (int[])a0;
        int i = a8.length;
        int i0 = i + 1;
        int[] a9 = new int[i0];
        this.elements = a9;
        int[] a10 = this.elements;
        int i1 = a8.length;
        System.arraycopy((Object)a8, 0, (Object)a10, 1, i1);
        int[] a11 = this.elements;
        int i2 = a11.length;
        java.awt.Color[] a12 = new java.awt.Color[i2];
        this.colors = a12;
        int i3 = 0;
        while(true)
        {
            java.awt.Color[] a13 = this.colors;
            int i4 = a13.length;
            if(i3 >= i4)
            {
                int[] a14 = this.elements;
                this.createDuplicateLabels(a14);
                com.cim.AIA.ConfigurationManager a15 = com.cim.AIA.ConfigurationManager.getConfigurationManager();
                java.awt.Color a16 = this.activeColor;
                String s2 = this.activeLabel;
                com.cim.AIA.ColorSelection a17 = new com.cim.AIA.ColorSelection(a16, s2);
                a17.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
                a15.addColorSelection(a17);
                java.awt.Color a18 = this.doneColor;
                String s3 = this.finishedLabel;
                com.cim.AIA.ColorSelection a19 = new com.cim.AIA.ColorSelection(a18, s3);
                a19.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
                a15.addColorSelection(a19);
                java.awt.Color a20 = this.highlightColor;
                String s4 = this.highlightLabel;
                com.cim.AIA.ColorSelection a21 = new com.cim.AIA.ColorSelection(a20, s4);
                a21.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
                a15.addColorSelection(a21);
                a15.addColorSelectionListener((com.cim.AIA.ColorSelectionListener)this);
                return;
            }
            else
            {
                java.awt.Color[] a22 = this.colors;
                java.awt.Color a23 = this.activeColor;
                a22[i3] = a23;
                int i5 = i3 + 1;
                i3 = i5;
            }
        }
    }
    
    protected void createDuplicateLabels(int[] a)
    {
        int i = a.length;
        Integer[] a0 = new Integer[i];
        int i0 = 0;
        while(true)
        {
            int i1 = a.length;
            if(i0 >= i1)
            {
                String[] a1 = com.cim.AIA.DuplicateLabel.createDuplicateLabels((Object[])a0);
                this.duplicateLabels = a1;
                return;
            }
            else
            {
                int i2 = a[i0];
                Integer a2 = new Integer(i2);
                a0[i0] = a2;
                int i3 = i0 + 1;
                i0 = i3;
            }
        }
    }
    
    public java.util.Vector generateQuestions()
    {
        java.util.Vector a = new java.util.Vector();
        int i = 0;
        while(true)
        {
            java.util.Vector a0 = this.questions;
            int i0 = a0.size();
            if(i >= i0)
            {
                return a;
            }
            else
            {
                java.util.Vector a1 = this.questions;
                Object a2 = a1.elementAt(i);
                a.addElement(a2);
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    protected void generateTweens(com.cim.AIA.MultipleTween a, com.cim.AIA.ElementArray a0, int i)
    {
        int i0 = 0;
        while(true)
        {
            java.util.Vector a1 = this.swapRequests;
            int i1 = a1.size();
            if(i0 >= i1)
            {
                return;
            }
            else
            {
                java.util.Vector a2 = this.swapRequests;
                Object a3 = a2.elementAt(i0);
                com.cim.AIA.SwapRequest dummy = (com.cim.AIA.SwapRequest)a3;
                com.cim.AIA.SwapRequest a4 = (com.cim.AIA.SwapRequest)a3;
                int i2 = a4.getFirstId();
                com.cim.AIA.Element a5 = a0.getElement(i2);
                int i3 = a4.getSecondId();
                com.cim.AIA.Element a6 = a0.getElement(i3);
                java.awt.Point a7 = a6.getPosition();
                java.awt.Point a8 = a5.getPosition();
                com.cim.AIA.MoveTween a9 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Moveable)a5, a7, a8, false, i);
                a.add((com.cim.AIA.Tween)a9);
                java.awt.Point a10 = a5.getPosition();
                java.awt.Point a11 = a6.getPosition();
                com.cim.AIA.MoveTween a12 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Moveable)a6, a10, a11, false, i);
                a.add((com.cim.AIA.Tween)a12);
                int i4 = i0 + 1;
                i0 = i4;
            }
        }
    }
    
    public com.cim.AIA.MultipleTween generateTweens(com.cim.AIA.Paintable a, Object a0, int i)
    {
        Object a1 = a;
        com.cim.AIA.MultipleTween a2 = new com.cim.AIA.MultipleTween((com.cim.AIA.Paintable)a1);
        com.cim.AIA.ElementArray dummy = (com.cim.AIA.ElementArray)a0;
        com.cim.AIA.ElementArray a3 = (com.cim.AIA.ElementArray)a0;
        this.generateTweens(a2, a3, i);
        return a2;
    }
    
    public String getClassName()
    {
        String s = localization.Messages.getString("HeapSort.3");
        return s;
    }
    
    public com.cim.AIA.ElementArray getElementArray(java.awt.Point a)
    {
        int i = a.x;
        int i0 = a.y;
        com.cim.AIA.ElementArray a0 = new com.cim.AIA.ElementArray(i, i0);
        this.elementArray = a0;
        com.cim.AIA.ElementArray a1 = this.elementArray;
        int i1 = this.columSpacing;
        a1.setColumGap(i1);
        com.cim.AIA.ElementArray a2 = this.elementArray;
        int i2 = this.columWidth;
        a2.setElementWidth(i2);
        int i3 = 1;
        while(true)
        {
            int[] a3 = this.elements;
            int i4 = a3.length;
            if(i3 >= i4)
            {
                com.cim.AIA.ElementArray a4 = this.elementArray;
                return a4;
            }
            int i5 = i3 - 1;
            int[] a5 = this.elements;
            int i6 = a5[i3];
            java.awt.Color[] a6 = this.colors;
            java.awt.Color a7 = a6[i3];
            java.awt.Point a8 = new java.awt.Point(0, 0);
            com.cim.AIA.VerticalBar a9 = new com.cim.AIA.VerticalBar(i5, i6, a7, a8);
            java.awt.Color a10 = this.textColor;
            a9.setTextColor(a10);
            java.awt.Color a11 = this.highlightColor;
            a9.setHighlightColor(a11);
            com.cim.AIA.ElementArray a12 = this.elementArray;
            int i7 = i3 - 1;
            a12.setValue(i7, (com.cim.AIA.Element)a9);
            com.cim.AIA.ElementArray a13 = this.elementArray;
            int i8 = i3 - 1;
            com.cim.AIA.Element a14 = a13.getElement(i8);
            com.cim.AIA.VerticalBar dummy = (com.cim.AIA.VerticalBar)a14;
            com.cim.AIA.VerticalBar a15 = (com.cim.AIA.VerticalBar)a14;
            a15.setMarkersAboveValue(false);
            com.cim.AIA.ElementArray a16 = this.elementArray;
            int i9 = i3 - 1;
            com.cim.AIA.Element a17 = a16.getElement(i9);
            com.cim.AIA.VerticalBar dummy0 = (com.cim.AIA.VerticalBar)a17;
            com.cim.AIA.VerticalBar a18 = (com.cim.AIA.VerticalBar)a17;
            a18.setOffsetForValue(1);
            String[] a19 = this.duplicateLabels;
            String s = a19[i3];
            if(s != null)
            {
                StringBuilder a20 = new StringBuilder();
                StringBuilder a21 = a20.append("");
                int[] a22 = this.elements;
                int i10 = a22[i3];
                StringBuilder a23 = a21.append(i10);
                String[] a24 = this.duplicateLabels;
                String s0 = a24[i3];
                StringBuilder a25 = a23.append(s0);
                String s1 = a25.toString();
                com.cim.AIA.StringMarker a26 = new com.cim.AIA.StringMarker(s1);
                java.awt.Color a27 = this.background;
                a26.setBackgroundColor(a27);
                java.awt.Color a28 = this.textColor;
                a26.setColor(a28);
                com.cim.AIA.ElementArray a29 = this.elementArray;
                int i11 = i3 - 1;
                com.cim.AIA.Element a30 = a29.getElement(i11);
                a30.addMarker(a26);
            }
            int i12 = i3 + 1;
            i3 = i12;
        }
    }
    
    public com.cim.AIA.HierarchyTree getHierarchyTree()
    {
        int[] a = this.elements;
        int i = a.length;
        com.cim.AIA.Node[] a0 = new com.cim.AIA.Node[i];
        this.nodes = a0;
        int[] a1 = this.elements;
        int i0 = a1.length;
        com.cim.AIA.HierarchyTree[] a2 = new com.cim.AIA.HierarchyTree[i0];
        com.cim.AIA.HierarchyTree a3 = null;
        int i1 = 1;
        while(true)
        {
            com.cim.AIA.HierarchyTree a4 = null;
            com.cim.AIA.HierarchyTree a5 = null;
            com.cim.AIA.HierarchyTree a6 = null;
            int i2 = 0;
            int i3 = 0;
            com.cim.AIA.HierarchyTree a7 = null;
            int i4 = 0;
            int i5 = 0;
            com.cim.AIA.HierarchyTree a8 = null;
            int i6 = 0;
            com.cim.AIA.HierarchyTree a9 = null;
            com.cim.AIA.HierarchyTree a10 = null;
            int[] a11 = this.elements;
            com.cim.AIA.HierarchyTree a12 = a3;
            int i7 = a11.length;
            com.cim.AIA.HierarchyTree a13 = a12;
            int i8 = i7 / 2;
            int i9 = i8 + 1;
            com.cim.AIA.HierarchyTree a14 = a13;
            if(i1 >= i9)
            {
                com.cim.AIA.HierarchyTree a15 = a2[1];
                return a15;
            }
            else
            {
                a4 = a14;
            }
            com.cim.AIA.HierarchyTree a16 = a4;
            if(a4 != null)
            {
                a5 = a16;
            }
            else
            {
                int[] a17 = this.elements;
                int i10 = a17[i1];
                Integer a18 = new Integer(i10);
                int i11 = i1 - 1;
                com.cim.AIA.Node a19 = new com.cim.AIA.Node((Object)a18, i11);
                java.awt.Color[] a20 = this.colors;
                java.awt.Color a21 = a20[i1];
                a19.setBackgroundColor(a21);
                java.awt.Color a22 = this.textColor;
                a19.setTextColor(a22);
                java.awt.Color a23 = this.highlightColor;
                a19.setHighlightColor(a23);
                com.cim.AIA.HierarchyTree a24 = new com.cim.AIA.HierarchyTree(a19);
                com.cim.AIA.Line a25 = a24.getLine();
                a25.showArrowHead(false);
                a2[i1] = a24;
                com.cim.AIA.Node[] a26 = this.nodes;
                a26[i1] = a19;
                a5 = a24;
            }
            int i12 = i1 * 2;
            int i13 = i12 + 0;
            int i14 = i1 * 2;
            int i15 = i14 + 1;
            int[] a27 = this.elements;
            com.cim.AIA.HierarchyTree a28 = a5;
            int i16 = a27.length;
            com.cim.AIA.HierarchyTree a29 = a28;
            com.cim.AIA.HierarchyTree a30 = a29;
            com.cim.AIA.HierarchyTree a31 = a29;
            if(i13 >= i16)
            {
                a6 = a31;
                i2 = 0;
                i3 = 1;
            }
            else
            {
                com.cim.AIA.HierarchyTree a32 = a30;
                int[] a33 = this.elements;
                com.cim.AIA.HierarchyTree a34 = a32;
                int i17 = a33[i13];
                com.cim.AIA.HierarchyTree a35 = a34;
                a6 = a35;
                i2 = i17;
                i3 = 0;
            }
            int[] a36 = this.elements;
            com.cim.AIA.HierarchyTree a37 = a6;
            int i18 = a36.length;
            com.cim.AIA.HierarchyTree a38 = a37;
            com.cim.AIA.HierarchyTree a39 = a38;
            com.cim.AIA.HierarchyTree a40 = a38;
            if(i15 >= i18)
            {
                a7 = a40;
                i4 = 0;
                i5 = 1;
            }
            else
            {
                com.cim.AIA.HierarchyTree a41 = a39;
                int[] a42 = this.elements;
                com.cim.AIA.HierarchyTree a43 = a41;
                int i19 = a42[i15];
                com.cim.AIA.HierarchyTree a44 = a43;
                a7 = a44;
                i4 = i19;
                i5 = 0;
            }
            label0: {
                com.cim.AIA.HierarchyTree a45 = null;
                com.cim.AIA.HierarchyTree a46 = a7;
                com.cim.AIA.HierarchyTree a47 = a7;
                if(i3 != 0)
                {
                    a8 = a47;
                    i6 = i5;
                    break label0;
                }
                else
                {
                    a45 = a46;
                }
                com.cim.AIA.HierarchyTree a48 = a45;
                com.cim.AIA.HierarchyTree a49 = a48;
                Integer a50 = new Integer(i2);
                com.cim.AIA.HierarchyTree a51 = a49;
                int i20 = i13 - 1;
                com.cim.AIA.Node a52 = new com.cim.AIA.Node((Object)a50, i20);
                com.cim.AIA.HierarchyTree a53 = a51;
                java.awt.Color[] a54 = this.colors;
                com.cim.AIA.HierarchyTree a55 = a53;
                java.awt.Color a56 = a54[i13];
                com.cim.AIA.HierarchyTree a57 = a55;
                a52.setBackgroundColor(a56);
                com.cim.AIA.HierarchyTree a58 = a57;
                java.awt.Color a59 = this.textColor;
                com.cim.AIA.HierarchyTree a60 = a58;
                a52.setTextColor(a59);
                com.cim.AIA.HierarchyTree a61 = a60;
                java.awt.Color a62 = this.highlightColor;
                com.cim.AIA.HierarchyTree a63 = a61;
                a52.setHighlightColor(a62);
                com.cim.AIA.HierarchyTree a64 = a63;
                com.cim.AIA.HierarchyTree a65 = a64;
                com.cim.AIA.HierarchyTree a66 = new com.cim.AIA.HierarchyTree((com.cim.AIA.Tree)a65, a52);
                com.cim.AIA.HierarchyTree a67 = a65;
                a2[i13] = a66;
                com.cim.AIA.HierarchyTree a68 = a67;
                com.cim.AIA.HierarchyTree a69 = a2[i13];
                com.cim.AIA.HierarchyTree a70 = a68;
                com.cim.AIA.Line a71 = a69.getLine();
                com.cim.AIA.HierarchyTree a72 = a70;
                a71.showArrowHead(false);
                com.cim.AIA.HierarchyTree a73 = a72;
                com.cim.AIA.HierarchyTree a74 = a2[i13];
                com.cim.AIA.HierarchyTree a75 = a73;
                a73.addChild((com.cim.AIA.Tree)a74);
                com.cim.AIA.HierarchyTree a76 = a75;
                com.cim.AIA.Node[] a77 = this.nodes;
                com.cim.AIA.HierarchyTree a78 = a76;
                a77[i13] = a52;
                com.cim.AIA.HierarchyTree a79 = a78;
                com.cim.AIA.HierarchyTree a80 = a79;
                com.cim.AIA.HierarchyTree a81 = a79;
                if(i5 == 0)
                {
                    a8 = a81;
                    i6 = i5;
                }
                else
                {
                    com.cim.AIA.HierarchyTree a82 = a80;
                    com.cim.AIA.HierarchyTree a83 = a82;
                    com.cim.AIA.HierarchyTree a84 = a83;
                    Integer a85 = new Integer(i2);
                    com.cim.AIA.HierarchyTree a86 = a84;
                    com.cim.AIA.Node a87 = new com.cim.AIA.Node((Object)a85, -1);
                    com.cim.AIA.HierarchyTree a88 = a86;
                    a87.setIsVisible(false);
                    com.cim.AIA.HierarchyTree a89 = a88;
                    com.cim.AIA.HierarchyTree a90 = a89;
                    com.cim.AIA.HierarchyTree a91 = new com.cim.AIA.HierarchyTree((com.cim.AIA.Tree)a90, a87);
                    com.cim.AIA.HierarchyTree a92 = a90;
                    a92.addChild((com.cim.AIA.Tree)a91);
                    a8 = a92;
                    i6 = 1;
                }
            }
            com.cim.AIA.HierarchyTree a93 = a8;
            com.cim.AIA.HierarchyTree a94 = a8;
            if(i6 != 0)
            {
                a9 = a94;
            }
            else
            {
                com.cim.AIA.HierarchyTree a95 = a93;
                com.cim.AIA.HierarchyTree a96 = a95;
                com.cim.AIA.HierarchyTree a97 = a96;
                Integer a98 = new Integer(i4);
                com.cim.AIA.HierarchyTree a99 = a97;
                int i21 = i15 - 1;
                com.cim.AIA.Node a100 = new com.cim.AIA.Node((Object)a98, i21);
                com.cim.AIA.HierarchyTree a101 = a99;
                java.awt.Color[] a102 = this.colors;
                com.cim.AIA.HierarchyTree a103 = a101;
                java.awt.Color a104 = a102[i15];
                com.cim.AIA.HierarchyTree a105 = a103;
                a100.setBackgroundColor(a104);
                com.cim.AIA.HierarchyTree a106 = a105;
                java.awt.Color a107 = this.textColor;
                com.cim.AIA.HierarchyTree a108 = a106;
                a100.setTextColor(a107);
                com.cim.AIA.HierarchyTree a109 = a108;
                java.awt.Color a110 = this.highlightColor;
                com.cim.AIA.HierarchyTree a111 = a109;
                a100.setHighlightColor(a110);
                com.cim.AIA.HierarchyTree a112 = a111;
                com.cim.AIA.HierarchyTree a113 = a112;
                com.cim.AIA.HierarchyTree a114 = new com.cim.AIA.HierarchyTree((com.cim.AIA.Tree)a113, a100);
                com.cim.AIA.HierarchyTree a115 = a113;
                a2[i15] = a114;
                com.cim.AIA.HierarchyTree a116 = a115;
                com.cim.AIA.HierarchyTree a117 = a2[i15];
                com.cim.AIA.HierarchyTree a118 = a116;
                com.cim.AIA.Line a119 = a117.getLine();
                com.cim.AIA.HierarchyTree a120 = a118;
                a119.showArrowHead(false);
                com.cim.AIA.HierarchyTree a121 = a120;
                com.cim.AIA.HierarchyTree a122 = a2[i15];
                com.cim.AIA.HierarchyTree a123 = a121;
                a121.addChild((com.cim.AIA.Tree)a122);
                com.cim.AIA.HierarchyTree a124 = a123;
                com.cim.AIA.Node[] a125 = this.nodes;
                com.cim.AIA.HierarchyTree a126 = a124;
                a125[i15] = a100;
                a9 = a126;
            }
            int i22 = i1 + 1;
            int[] a127 = this.elements;
            com.cim.AIA.HierarchyTree a128 = a9;
            int i23 = a127.length;
            com.cim.AIA.HierarchyTree a129 = a128;
            com.cim.AIA.HierarchyTree a130 = a129;
            if(i22 >= i23)
            {
                a10 = a130;
            }
            else
            {
                int i24 = i1 + 1;
                com.cim.AIA.HierarchyTree a131 = a2[i24];
                a10 = a131;
            }
            int i25 = i1 + 1;
            a3 = a10;
            i1 = i25;
        }
    }
    
    public int getI()
    {
        int i = this.i;
        int i0 = i - 1;
        return i0;
    }
    
    protected int getIndexOfLargestChild(int i, int i0)
    {
        int i1 = 0;
        label0: {
            int i2 = 2 * i;
            if(i2 >= i0)
            {
                i1 = i2;
                break label0;
            }
            int[] a = this.elements;
            int i3 = a[i2];
            int[] a0 = this.elements;
            int i4 = i2 + 1;
            int i5 = a0[i4];
            if(i3 >= i5)
            {
                i1 = i2;
            }
            else
            {
                int i6 = i2 + 1;
                i1 = i6;
            }
        }
        return i1;
    }
    
    public int getJ()
    {
        int i = this.j;
        int i0 = i - 1;
        return i0;
    }
    
    public int getK()
    {
        int i = this.k;
        int i0 = i - 1;
        return i0;
    }
    
    public com.cim.AIA.Node getNode(int i)
    {
        com.cim.AIA.Node a = null;
        com.cim.AIA.Node[] a0 = this.nodes;
        label1: {
            label0: {
                if(a0 != null)
                {
                    break label0;
                }
                a = null;
                break label1;
            }
            label3: {
                label2: {
                    int i0 = i + 1;
                    if(i0 < 0)
                    {
                        break label2;
                    }
                    int i1 = i + 1;
                    com.cim.AIA.Node[] a1 = this.nodes;
                    int i2 = a1.length;
                    if(i1 < i2)
                    {
                        break label3;
                    }
                }
                a = null;
                break label1;
            }
            com.cim.AIA.Node[] a2 = this.nodes;
            int i3 = i + 1;
            com.cim.AIA.Node a3 = a2[i3];
            a = a3;
        }
        return a;
    }
    
    public boolean hasQuestions()
    {
        java.util.Vector a = this.questions;
        int i = a.size();
        int i0 = i <= 0?0:1;
        return i0 != 0;
    }
    
    protected void makeJQuestion(int i)
    {
        com.cim.AIA.ElementArray a = this.elementArray;
        if(a != null)
        {
            IAndJPositionQuestion a0 = new IAndJPositionQuestion();
            String s = IAndJPositionQuestion.QUESTION_J_LABEL;
            a0.setInstructions(s);
            int i0 = i - 1;
            Integer a1 = new Integer(i0);
            a0.addAnswer(a1);
            java.util.Vector a2 = this.questions;
            a2.addElement((Object)a0);
        }
    }
    
    protected void makeSwapQuestion(int i, int i0)
    {
        com.cim.AIA.ElementArray a = this.elementArray;
        if(a != null)
        {
            IAndJPositionQuestion a0 = new IAndJPositionQuestion();
            String s = IAndJPositionQuestion.QUESTION_SWAP_LABEL;
            a0.setInstructions(s);
            int i1 = i - 1;
            Integer a1 = new Integer(i1);
            a0.addAnswer(a1);
            int i2 = i0 - 1;
            Integer a2 = new Integer(i2);
            a0.addAnswer(a2);
            java.util.Vector a3 = this.questions;
            a3.addElement((Object)a0);
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
            String s2 = this.activeLabel;
            int i1 = s.equalsIgnoreCase(s2)?1:0;
            label3: {
                if(i1 == 0)
                {
                    break label3;
                }
                int i2 = 0;
                while(true)
                {
                    java.awt.Color[] a2 = this.colors;
                    int i3 = a2.length;
                    if(i2 >= i3)
                    {
                        break;
                    }
                    java.awt.Color[] a3 = this.colors;
                    java.awt.Color a4 = a3[i2];
                    java.awt.Color a5 = this.activeColor;
                    if(a4 == a5)
                    {
                        java.awt.Color[] a6 = this.colors;
                        java.awt.Color a7 = a.getColor();
                        a6[i2] = a7;
                    }
                    int i4 = i2 + 1;
                    i2 = i4;
                    continue;
                }
                java.awt.Color a8 = a.getColor();
                this.activeColor = a8;
                break label1;
            }
            String s3 = this.finishedLabel;
            int i5 = s.equalsIgnoreCase(s3)?1:0;
            label4: {
                if(i5 == 0)
                {
                    break label4;
                }
                int i6 = 0;
                while(true)
                {
                    java.awt.Color[] a9 = this.colors;
                    int i7 = a9.length;
                    if(i6 >= i7)
                    {
                        break;
                    }
                    java.awt.Color[] a10 = this.colors;
                    java.awt.Color a11 = a10[i6];
                    java.awt.Color a12 = this.doneColor;
                    if(a11 == a12)
                    {
                        java.awt.Color[] a13 = this.colors;
                        java.awt.Color a14 = a.getColor();
                        a13[i6] = a14;
                    }
                    int i8 = i6 + 1;
                    i6 = i8;
                    continue;
                }
                java.awt.Color a15 = a.getColor();
                this.doneColor = a15;
                break label1;
            }
            String s4 = this.highlightLabel;
            int i9 = s.equalsIgnoreCase(s4)?1:0;
            if(i9 == 0)
            {
                break label1;
            }
            int i10 = 0;
            while(true)
            {
                java.awt.Color[] a16 = this.colors;
                int i11 = a16.length;
                if(i10 >= i11)
                {
                    break;
                }
                java.awt.Color[] a17 = this.colors;
                java.awt.Color a18 = a17[i10];
                java.awt.Color a19 = this.highlightColor;
                if(a18 == a19)
                {
                    java.awt.Color[] a20 = this.colors;
                    java.awt.Color a21 = a.getColor();
                    a20[i10] = a21;
                }
                int i12 = i10 + 1;
                i10 = i12;
            }
            java.awt.Color a22 = a.getColor();
            this.highlightColor = a22;
        }
    }
    
    public void reInitialise(Object a)
    {
        int[] dummy = (int[])a;
        int[] a0 = (int[])a;
        int i = a0.length;
        int i0 = i + 1;
        int[] a1 = new int[i0];
        this.elements = a1;
        this.elementArray = null;
        int[] a2 = this.elements;
        int i1 = a0.length;
        System.arraycopy((Object)a0, 0, (Object)a2, 1, i1);
        int[] a3 = this.elements;
        int i2 = a3.length;
        java.awt.Color[] a4 = new java.awt.Color[i2];
        this.colors = a4;
        int i3 = 0;
        while(true)
        {
            java.awt.Color[] a5 = this.colors;
            int i4 = a5.length;
            if(i3 >= i4)
            {
                this.N = -10;
                this.k = -10;
                this.j = -10;
                this.i = -10;
                int[] a6 = this.elements;
                this.createDuplicateLabels(a6);
                this.setPosition("");
                return;
            }
            else
            {
                java.awt.Color[] a7 = this.colors;
                java.awt.Color a8 = this.activeColor;
                a7[i3] = a8;
                int i5 = i3 + 1;
                i3 = i5;
            }
        }
    }
    
    public void removeAllAnimationRequests()
    {
        java.util.Vector a = this.swapRequests;
        a.removeAllElements();
    }
    
    public void removeAllQuestions()
    {
        java.util.Vector a = this.questions;
        a.removeAllElements();
    }
    
    public void run()
    {
        int i = this.enabled?1:0;
        label1: {
            label0: {
                if(i != 0)
                {
                    break label0;
                }
                break label1;
            }
            this.setPosition("0");
            int[] a = this.elements;
            int i0 = a.length;
            int i1 = i0 - 1;
            this.N = i1;
            this.setPosition("1");
            this.setPosition("1.0");
            this.setPosition("1.1");
            int i2 = this.N;
            int i3 = i2 / 2;
            this.k = i3;
            while(true)
            {
                int i4 = this.k;
                if(i4 <= 0)
                {
                    break;
                }
                this.setPosition("1.1.1");
                this.setPosition("1.2");
                int i5 = this.k;
                this.i = i5;
                this.setPosition("1.2.1");
                while(true)
                {
                    int i6 = this.i;
                    int i7 = 2 * i6;
                    int i8 = this.N;
                    label2: {
                        if(i7 > i8)
                        {
                            break label2;
                        }
                        int i9 = this.i;
                        int i10 = this.N;
                        int i11 = this.getIndexOfLargestChild(i9, i10);
                        this.makeJQuestion(i11);
                        this.setPosition("1.2.2");
                        int i12 = this.i;
                        int i13 = this.N;
                        int i14 = this.getIndexOfLargestChild(i12, i13);
                        this.j = i14;
                        this.setPosition("1.2.1.2");
                        int[] a0 = this.elements;
                        int i15 = this.i;
                        int i16 = a0[i15];
                        int[] a1 = this.elements;
                        int i17 = this.j;
                        int i18 = a1[i17];
                        if(i16 < i18)
                        {
                            int i19 = this.i;
                            int i20 = this.j;
                            this.makeSwapQuestion(i19, i20);
                            this.askQuestionsWithoutSetPosition();
                            int i21 = this.i;
                            int i22 = this.j;
                            this.swap(i21, i22);
                            this.setPosition("1.2.1.5");
                            int i23 = this.j;
                            this.i = i23;
                            this.setPosition("1.2.1.6");
                            continue;
                        }
                        this.setPosition("1.2.1.3");
                    }
                    this.j = -10;
                    this.i = -10;
                    this.setPosition("1.2.3");
                    int i24 = this.k;
                    int i25 = i24 - 1;
                    this.k = i25;
                }
            }
            this.k = -10;
            this.setPosition("1.a");
            while(true)
            {
                int i26 = this.N;
                if(i26 <= 1)
                {
                    break;
                }
                this.setPosition("3");
                this.setPosition("3.0");
                this.setPosition("3.1");
                int i27 = this.N;
                this.makeSwapQuestion(1, i27);
                this.askQuestionsWithoutSetPosition();
                int i28 = this.N;
                this.swap(1, i28);
                this.setPosition("3.1.1");
                this.setPosition("3.2");
                java.awt.Color[] a2 = this.colors;
                int i29 = this.N;
                java.awt.Color a3 = this.doneColor;
                a2[i29] = a3;
                int i30 = this.N;
                int i31 = i30 - 1;
                this.N = i31;
                this.setPosition("3.2.1");
                this.setPosition("3.3");
                this.setPosition("3.3.1");
                this.i = 1;
                this.setPosition("3.3.1.1");
                while(true)
                {
                    int i32 = this.i;
                    int i33 = 2 * i32;
                    int i34 = this.N;
                    label3: {
                        if(i33 > i34)
                        {
                            break label3;
                        }
                        int i35 = this.i;
                        int i36 = this.N;
                        int i37 = this.getIndexOfLargestChild(i35, i36);
                        this.makeJQuestion(i37);
                        this.setPosition("3.3.1.2");
                        int i38 = this.i;
                        int i39 = this.N;
                        int i40 = this.getIndexOfLargestChild(i38, i39);
                        this.j = i40;
                        this.setPosition("3.3.1.2.1");
                        int[] a4 = this.elements;
                        int i41 = this.i;
                        int i42 = a4[i41];
                        int[] a5 = this.elements;
                        int i43 = this.j;
                        int i44 = a5[i43];
                        if(i42 < i44)
                        {
                            int i45 = this.i;
                            int i46 = this.j;
                            this.makeSwapQuestion(i45, i46);
                            this.askQuestionsWithoutSetPosition();
                            int i47 = this.i;
                            int i48 = this.j;
                            this.swap(i47, i48);
                            this.setPosition("3.3.1.2.4");
                            int i49 = this.j;
                            this.i = i49;
                            this.setPosition("3.3.1.2.5");
                            continue;
                        }
                        this.setPosition("3.3.1.2.2");
                    }
                    this.j = -10;
                    this.i = -10;
                    this.setPosition("3.3.1.2.6");
                }
            }
            this.setPosition("4");
        }
    }
    
    protected void swap(int i, int i0)
    {
        int[] a = this.elements;
        int i1 = a[i0];
        int[] a0 = this.elements;
        int[] a1 = this.elements;
        int i2 = a1[i];
        a0[i0] = i2;
        int[] a2 = this.elements;
        a2[i] = i1;
        java.awt.Color[] a3 = this.colors;
        java.awt.Color a4 = a3[i0];
        java.awt.Color[] a5 = this.colors;
        java.awt.Color[] a6 = this.colors;
        java.awt.Color a7 = a6[i];
        a5[i0] = a7;
        java.awt.Color[] a8 = this.colors;
        a8[i] = a4;
        String[] a9 = this.duplicateLabels;
        String s = a9[i0];
        String[] a10 = this.duplicateLabels;
        String[] a11 = this.duplicateLabels;
        String s0 = a11[i];
        a10[i0] = s0;
        String[] a12 = this.duplicateLabels;
        a12[i] = s;
        if(i0 != i)
        {
            java.util.Vector a13 = this.swapRequests;
            int i3 = i - 1;
            int i4 = i0 - 1;
            com.cim.AIA.SwapRequest a14 = new com.cim.AIA.SwapRequest(i3, i4);
            a13.addElement((Object)a14);
        }
    }
    
    public void unHighlightAll()
    {
        com.cim.AIA.ElementArray a = this.elementArray;
        label0: {
            if(a == null)
            {
                break label0;
            }
            int i = 1;
            while(true)
            {
                int[] a0 = this.elements;
                int i0 = a0.length;
                if(i >= i0)
                {
                    break;
                }
                com.cim.AIA.ElementArray a1 = this.elementArray;
                int i1 = i - 1;
                com.cim.AIA.Element a2 = a1.getElement(i1);
                if(a2 != null)
                {
                    com.cim.AIA.ElementArray a3 = this.elementArray;
                    int i2 = i - 1;
                    com.cim.AIA.Element a4 = a3.getElement(i2);
                    a4.unHighlight();
                }
                int i3 = i + 1;
                i = i3;
            }
        }
    }
}