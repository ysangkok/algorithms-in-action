public class MergeSort extends com.cim.AIA.Algorithm implements com.cim.AIA.ColorSelectionListener {
    final protected static String selectionLabel;
    final protected static String highlightLabel;
    final protected static String ACTIVE;
    final protected static String PROCESSED;
    final protected static String FINISHED;
    final protected static String WAITING;
    final protected static String PARTITION;
    protected static java.awt.Color selectionColor;
    protected static java.awt.Color BACKGROUND;
    protected static java.awt.Color TEXT_COLOR;
    public static java.awt.Color PARTITION_COLOR;
    public static java.awt.Color WAITING_COLOR;
    public static java.awt.Color HIGHLIGHT_COLOR;
    public static java.awt.Color DOING_CHILDREN_COLOR;
    public static java.awt.Color ACTIVE_COLOR;
    public static java.awt.Color FINISHED_COLOR;
    protected int columSpacing;
    protected int columWidth;
    protected int xTextBuffer;
    protected int yTextBuffer;
    protected int xBoxPadding;
    protected int yBoxPadding;
    public int[] some;
    final protected static boolean DEBUG_MODE = 0;
    final protected static long aSeed = 12345678L;
    protected java.util.Random random;
    protected static java.util.Vector swapRequests;
    protected static java.util.Vector questions;
    protected static MyElementArray elementArray;
    protected static java.util.Hashtable highlightedElements;
    protected int[] data;
    protected Integer[] A;
    protected Integer[] B;
    protected Integer[] C;
    final private static java.awt.Color kBHighlightColor;
    final private static java.awt.Color kCHighlightColor;
    final private static java.awt.Color kCopyBackColor;
    protected int numberOfComparisions;
    protected int numberOfSwaps;
    protected MergeSort leftChild;
    protected MergeSort rightChild;
    protected int left;
    protected int right;
    protected boolean started;
    protected boolean active;
    protected boolean processed;
    protected static boolean needtoresetSeed;
    private static int maxHeight;
    private static int maxWidth;
    private boolean copyDone;
    private java.awt.Color tint;
    private java.util.HashMap markMap;
    private int markCompareMap;
    private java.awt.Color markCompareColor;
    private String[] arrays;
    private com.cim.AIA.AlgorithmThread algThread;
    
    private static java.awt.Color tintLeft(java.awt.Color a)
    {
        java.awt.Color a0 = new java.awt.Color(204, 0, 204);
        return a0;
    }
    
    private static java.awt.Color tintRight(java.awt.Color a)
    {
        java.awt.Color a0 = java.awt.Color.MAGENTA;
        return a0;
    }
    
    public MergeSort(com.cim.AIA.AlgorithmThread a, Object a0)
    {
        super(a, a0);
        this.columSpacing = 12;
        this.columWidth = 7;
        this.xTextBuffer = 20;
        this.yTextBuffer = 2;
        this.xBoxPadding = 4;
        this.yBoxPadding = 2;
        int[] a1 = new int[4];
        a1[0] = 1;
        a1[1] = 2;
        a1[2] = 3;
        a1[3] = 4;
        this.some = a1;
        java.util.Random a2 = new java.util.Random(12345678L);
        this.random = a2;
        this.numberOfComparisions = 0;
        this.numberOfSwaps = 0;
        this.started = false;
        this.active = false;
        this.processed = false;
        this.copyDone = false;
        this.markCompareMap = -1;
        String[] a3 = new String[3];
        a3[0] = "A";
        a3[1] = "B";
        a3[2] = "C";
        this.arrays = a3;
        int[] dummy = (int[])a0;
        int[] a4 = (int[])a0;
        this.data = a4;
        this.algThread = a;
        int[] a5 = this.data;
        int i = a5.length;
        Integer[] a6 = new Integer[i];
        this.A = a6;
        int i0 = 0;
        while(true)
        {
            int[] a7 = this.data;
            int i1 = a7.length;
            if(i0 >= i1)
            {
                java.util.HashMap a8 = new java.util.HashMap();
                this.markMap = a8;
                com.cim.AIA.ConfigurationManager a9 = com.cim.AIA.ConfigurationManager.getConfigurationManager();
                java.awt.Color a10 = MergeSort.PARTITION_COLOR;
                String s = MergeSort.PARTITION;
                com.cim.AIA.ColorSelection a11 = new com.cim.AIA.ColorSelection(a10, s);
                a11.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
                a9.addColorSelection(a11);
                a9.addColorSelectionListener((com.cim.AIA.ColorSelectionListener)this);
                java.awt.Color a12 = MergeSort.ACTIVE_COLOR;
                String s0 = MergeSort.ACTIVE;
                com.cim.AIA.ColorSelection a13 = new com.cim.AIA.ColorSelection(a12, s0);
                a13.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
                a9.addColorSelection(a13);
                java.awt.Color a14 = MergeSort.DOING_CHILDREN_COLOR;
                String s1 = MergeSort.PROCESSED;
                com.cim.AIA.ColorSelection a15 = new com.cim.AIA.ColorSelection(a14, s1);
                a15.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
                a9.addColorSelection(a15);
                java.awt.Color a16 = MergeSort.FINISHED_COLOR;
                String s2 = MergeSort.FINISHED;
                com.cim.AIA.ColorSelection a17 = new com.cim.AIA.ColorSelection(a16, s2);
                a17.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
                a9.addColorSelection(a17);
                java.awt.Color a18 = MergeSort.WAITING_COLOR;
                String s3 = MergeSort.WAITING;
                com.cim.AIA.ColorSelection a19 = new com.cim.AIA.ColorSelection(a18, s3);
                a19.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
                a9.addColorSelection(a19);
                java.awt.Color a20 = MergeSort.HIGHLIGHT_COLOR;
                String s4 = MergeSort.highlightLabel;
                com.cim.AIA.ColorSelection a21 = new com.cim.AIA.ColorSelection(a20, s4);
                a21.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
                a9.addColorSelection(a21);
                a9.addColorSelectionListener((com.cim.AIA.ColorSelectionListener)this);
                java.awt.Color a22 = MergeSort.selectionColor;
                String s5 = MergeSort.selectionLabel;
                com.cim.AIA.ColorSelection a23 = new com.cim.AIA.ColorSelection(a22, s5);
                a23.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
                a9.addColorSelection(a23);
                a9.addColorSelectionListener((com.cim.AIA.ColorSelectionListener)this);
                this.initialise(true);
                return;
            }
            else
            {
                Integer[] a24 = this.A;
                int[] a25 = this.data;
                int i2 = a25[i0];
                Integer a26 = new Integer(i2);
                a24[i0] = a26;
                int i3 = i0 + 1;
                i0 = i3;
            }
        }
    }
    
    protected void initialise(boolean b)
    {
        this.debugMode = false;
        this.active = false;
        this.processed = false;
        this.leftChild = null;
        this.rightChild = null;
        this.numberOfComparisions = 0;
        this.numberOfSwaps = 0;
    }
    
    protected void removeAllAnimationRequests()
    {
    }
    
    void merge(boolean b)
    {
        int i = 0;
        int i0 = 0;
        int i1 = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        this.setPosition("7", b);
        int i6 = b?1:0;
        label2: {
            label1: {
                label0: {
                    if(i6 == 0)
                    {
                        break label0;
                    }
                    int i7 = this.isExpanded("7")?1:0;
                    if(i7 != 0)
                    {
                        break label1;
                    }
                }
                i = 0;
                break label2;
            }
            i = 1;
        }
        this.setPosition("7.1", i != 0);
        Integer[] a = this.B;
        int i8 = a.length;
        Integer[] a0 = this.C;
        int i9 = a0.length;
        this.setMark("A", 0);
        MergeSort a1 = this.leftChild;
        a1.setMark("A", 0);
        MergeSort a2 = this.rightChild;
        a2.setMark("A", 0);
        label5: {
            label4: {
                label3: {
                    if(i6 == 0)
                    {
                        break label3;
                    }
                    int i10 = this.isExpanded("7")?1:0;
                    if(i10 != 0)
                    {
                        break label4;
                    }
                }
                i0 = 0;
                break label5;
            }
            i0 = 1;
        }
        this.setPosition("7.1.1.1", i0 != 0);
        label8: {
            label7: {
                label6: {
                    if(i6 == 0)
                    {
                        break label6;
                    }
                    int i11 = this.isExpanded("7.1.1")?1:0;
                    if(i11 != 0)
                    {
                        break label7;
                    }
                }
                i1 = 0;
                break label8;
            }
            i1 = 1;
        }
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        int i15 = i1;
        while(true)
        {
            int i16 = 0;
            int i17 = 0;
            int i18 = 0;
            int i19 = 0;
            int i20 = 0;
            int i21 = 0;
            int i22 = 0;
            int i23 = 0;
            int i24 = 0;
            int i25 = 0;
            int i26 = 0;
            int i27 = 0;
            int i28 = 0;
            int i29 = i12;
            int i30 = i13;
            int i31 = i15;
            if(i12 >= i8)
            {
                i2 = i12;
                i3 = i13;
                break;
            }
            else
            {
                i16 = i29;
                i17 = i30;
                i18 = i31;
            }
            int i32 = i16;
            int i33 = i17;
            int i34 = i18;
            if(i17 >= i9)
            {
                i2 = i16;
                i3 = i17;
                break;
            }
            else
            {
                i19 = i32;
                i20 = i33;
                i21 = i34;
            }
            this.setMark("A", i14);
            int i35 = i19;
            int i36 = i20;
            int i37 = i21;
            label11: {
                int i38 = 0;
                int i39 = 0;
                label10: {
                    int i40 = 0;
                    int i41 = 0;
                    int i42 = 0;
                    label9: {
                        int i43 = 0;
                        int i44 = 0;
                        int i45 = i35;
                        int i46 = i36;
                        int i47 = i35;
                        int i48 = i36;
                        int i49 = i37;
                        if(i37 == 0)
                        {
                            i40 = i47;
                            i41 = i48;
                            i42 = i49;
                            break label9;
                        }
                        else
                        {
                            i43 = i45;
                            i44 = i46;
                        }
                        int i50 = this.isExpanded("7.1.1.1.1")?1:0;
                        int i51 = i43;
                        int i52 = i44;
                        int i53 = i51;
                        int i54 = i52;
                        int i55 = i51;
                        int i56 = i52;
                        if(i50 == 0)
                        {
                            i40 = i55;
                            i41 = i56;
                            i42 = 1;
                        }
                        else
                        {
                            i38 = i53;
                            i39 = i54;
                            break label10;
                        }
                    }
                    i22 = 0;
                    i23 = i40;
                    i24 = i41;
                    i25 = i42;
                    break label11;
                }
                i22 = 1;
                i23 = i38;
                i24 = i39;
                i25 = 1;
            }
            MergeSort a3 = this.leftChild;
            int i57 = i23;
            int i58 = i24;
            int i59 = i25;
            java.awt.Color a4 = MergeSort.kBHighlightColor;
            int i60 = i57;
            int i61 = i58;
            int i62 = i59;
            a3.setCompareMark(i57, a4, i22 != 0);
            int i63 = i60;
            int i64 = i61;
            int i65 = i62;
            this.setPosition("7.1.1.1.1", i65 != 0);
            int i66 = i63;
            int i67 = i64;
            int i68 = i65;
            MergeSort a5 = this.rightChild;
            int i69 = i66;
            int i70 = i67;
            int i71 = i68;
            java.awt.Color a6 = MergeSort.kCHighlightColor;
            int i72 = i69;
            int i73 = i70;
            int i74 = i71;
            a5.setCompareMark(i70, a6);
            int i75 = i72;
            int i76 = i73;
            int i77 = i74;
            this.setPosition("7.1.1.1.1.1", i77 != 0);
            int i78 = i75;
            int i79 = i76;
            int i80 = i77;
            Integer[] a7 = this.B;
            int i81 = i78;
            int i82 = i79;
            int i83 = i80;
            Integer a8 = a7[i81];
            int i84 = i81;
            int i85 = i82;
            int i86 = i83;
            int i87 = a8.intValue();
            int i88 = i84;
            int i89 = i85;
            int i90 = i86;
            Integer[] a9 = this.C;
            int i91 = i88;
            int i92 = i89;
            int i93 = i90;
            Integer a10 = a9[i92];
            int i94 = i91;
            int i95 = i92;
            int i96 = i93;
            int i97 = a10.intValue();
            int i98 = i94;
            int i99 = i95;
            int i100 = i96;
            int i101 = i99;
            int i102 = i100;
            int i103 = i98;
            int i104 = i100;
            if(i87 > i97)
            {
                int i105 = i103;
                int i106 = i104;
                java.awt.Color a11 = MergeSort.kCHighlightColor;
                int i107 = i105;
                int i108 = i106;
                this.setCompareMark(i14, a11);
                int i109 = i107;
                int i110 = i108;
                this.setMark("A", i14);
                int i111 = i109;
                int i112 = i110;
                this.setPosition("7.1.1.1.1.3", i22 != 0);
                int i113 = i111;
                int i114 = i112;
                Integer[] a12 = this.A;
                int i115 = i113;
                int i116 = i114;
                Integer[] a13 = this.C;
                int i117 = i115;
                int i118 = i116;
                Integer a14 = a13[i99];
                int i119 = i117;
                int i120 = i118;
                a12[i14] = a14;
                int i121 = i119;
                int i122 = i120;
                this.setPosition("7.1.1.1.1.3c", i22 != 0);
                int i123 = i121;
                int i124 = i122;
                int i125 = i99 + 1;
                MergeSort a15 = this.rightChild;
                int i126 = i123;
                int i127 = i124;
                a15.setMark("A", i125);
                i26 = i126;
                i27 = i125;
                i28 = i127;
            }
            else
            {
                int i128 = i101;
                int i129 = i102;
                java.awt.Color a16 = MergeSort.kBHighlightColor;
                int i130 = i128;
                int i131 = i129;
                this.setCompareMark(i14, a16);
                int i132 = i130;
                int i133 = i131;
                this.setMark("A", i14);
                int i134 = i132;
                int i135 = i133;
                this.setPosition("7.1.1.1.1.2", i22 != 0);
                int i136 = i134;
                int i137 = i135;
                Integer[] a17 = this.A;
                int i138 = i136;
                int i139 = i137;
                Integer[] a18 = this.B;
                int i140 = i138;
                int i141 = i139;
                Integer a19 = a18[i98];
                int i142 = i140;
                int i143 = i141;
                a17[i14] = a19;
                int i144 = i142;
                int i145 = i143;
                this.setPosition("7.1.1.1.1.2b", i22 != 0);
                int i146 = i144;
                int i147 = i145;
                int i148 = i98 + 1;
                MergeSort a20 = this.leftChild;
                int i149 = i146;
                int i150 = i147;
                a20.setMark("A", i148);
                int i151 = i149;
                int i152 = i150;
                i26 = i148;
                i27 = i151;
                i28 = i152;
            }
            MergeSort a21 = this.leftChild;
            int i153 = i26;
            int i154 = i27;
            int i155 = i28;
            a21.clearCompareMark();
            int i156 = i153;
            int i157 = i154;
            int i158 = i155;
            this.clearCompareMark();
            int i159 = i156;
            int i160 = i157;
            int i161 = i158;
            MergeSort a22 = this.rightChild;
            int i162 = i159;
            int i163 = i160;
            int i164 = i161;
            a22.clearCompareMark();
            int i165 = i162;
            int i166 = i163;
            int i167 = i164;
            int i168 = i14 + 1;
            this.setMark("A", i168);
            int i169 = i165;
            int i170 = i166;
            int i171 = i167;
            this.setPosition("7.1.1.1.2", i171 != 0);
            int i172 = i169;
            int i173 = i170;
            int i174 = i171;
            i12 = i172;
            i13 = i173;
            i14 = i168;
            i15 = i174;
        }
        label14: {
            label13: {
                label12: {
                    if(i6 == 0)
                    {
                        break label12;
                    }
                    int i175 = this.isExpanded("7")?1:0;
                    if(i175 != 0)
                    {
                        break label13;
                    }
                }
                i4 = 0;
                break label14;
            }
            i4 = 1;
        }
        this.setPosition("7.1.1.2", i4 != 0);
        label17: {
            label16: {
                label15: {
                    if(i6 == 0)
                    {
                        break label15;
                    }
                    int i176 = this.isExpanded("7")?1:0;
                    if(i176 == 0)
                    {
                        break label15;
                    }
                    int i177 = this.isExpanded("7.1.1.2")?1:0;
                    if(i177 != 0)
                    {
                        break label16;
                    }
                }
                i5 = 0;
                break label17;
            }
            i5 = 1;
        }
        this.setMark("A", i14);
        this.setPosition("7.1.1.2.1", i5 != 0);
        this.setMark("A", i14);
        label22: {
            int i178 = 0;
            int i179 = 0;
            label18: {
                int i180 = 0;
                if(i2 != i8)
                {
                    break label18;
                }
                this.setPosition("7.1.1.2.2", i5 != 0);
                label21: {
                    label20: {
                        label19: {
                            if(i5 == 0)
                            {
                                break label19;
                            }
                            int i181 = this.isExpanded("7.1.1.2.2")?1:0;
                            if(i181 != 0)
                            {
                                break label20;
                            }
                        }
                        i180 = 0;
                        break label21;
                    }
                    i180 = 1;
                }
                this.setPosition("7.1.1.2.2.1", i180 != 0);
                int i182 = i3;
                int i183 = i14;
                while(true)
                {
                    if(i182 >= i9)
                    {
                        break;
                    }
                    else
                    {
                        java.awt.Color a23 = MergeSort.kCopyBackColor;
                        this.setCompareMark(i183, a23);
                        MergeSort a24 = this.rightChild;
                        java.awt.Color a25 = MergeSort.kCopyBackColor;
                        a24.setCompareMark(i182, a25);
                        this.setMark("A", i183);
                        Integer[] a26 = this.A;
                        Integer[] a27 = this.C;
                        Integer a28 = a27[i182];
                        a26[i183] = a28;
                        int i184 = i183 + 1;
                        int i185 = i182 + 1;
                        this.setPosition("7.1.1.2.3", i180 != 0);
                        int i186 = i184 - 1;
                        this.setMark("A", i186);
                        this.setPosition("7.1.1.2.4", i180 != 0);
                        this.setPosition("7.1.1.2.5", i180 != 0);
                        this.setPosition("7.1.1.2.2.1", i180 != 0);
                        i182 = i185;
                        i183 = i184;
                        continue;
                    }
                }
                MergeSort a29 = this.leftChild;
                a29.clearCompareMark();
                this.clearCompareMark();
                MergeSort a30 = this.rightChild;
                a30.clearCompareMark();
                break label22;
            }
            this.setPosition("7.1.1.2.6", i5 != 0);
            this.setPosition("7.1.1.2.7", i5 != 0);
            label25: {
                label24: {
                    int i187 = 0;
                    label23: {
                        if(i5 == 0)
                        {
                            i187 = i5;
                            break label23;
                        }
                        int i188 = this.isExpanded("7.1.1.2.7")?1:0;
                        if(i188 == 0)
                        {
                            i187 = 1;
                        }
                        else
                        {
                            break label24;
                        }
                    }
                    i178 = 0;
                    i179 = i187;
                    break label25;
                }
                i178 = 1;
                i179 = 1;
            }
            this.setPosition("7.1.1.2.8", i179 != 0);
            int i189 = i2;
            int i190 = i14;
            while(true)
            {
                if(i189 >= i8)
                {
                    break;
                }
                else
                {
                    java.awt.Color a31 = MergeSort.kCopyBackColor;
                    this.setCompareMark(i190, a31);
                    MergeSort a32 = this.leftChild;
                    java.awt.Color a33 = MergeSort.kCopyBackColor;
                    a32.setCompareMark(i189, a33);
                    this.setMark("A", i190);
                    Integer[] a34 = this.A;
                    Integer[] a35 = this.B;
                    Integer a36 = a35[i189];
                    a34[i190] = a36;
                    int i191 = i190 + 1;
                    int i192 = i189 + 1;
                    this.setMark("A", i191);
                    this.setPosition("7.1.1.2.9", i178 != 0);
                    this.setPosition("7.1.1.2.10", i178 != 0);
                    this.setPosition("7.1.1.2.11", i178 != 0);
                    this.setPosition("7.1.1.2.8", i178 != 0);
                    i189 = i192;
                    i190 = i191;
                }
            }
            MergeSort a37 = this.leftChild;
            a37.clearCompareMark();
            this.clearCompareMark();
            MergeSort a38 = this.rightChild;
            a38.clearCompareMark();
            this.clearMark("A");
        }
        this.clearMark("A");
    }
    
    public MergeSort(com.cim.AIA.AlgorithmThread a, Integer[] a0)
    {
        super(a, (Object)a0);
        this.columSpacing = 12;
        this.columWidth = 7;
        this.xTextBuffer = 20;
        this.yTextBuffer = 2;
        this.xBoxPadding = 4;
        this.yBoxPadding = 2;
        int[] a1 = new int[4];
        a1[0] = 1;
        a1[1] = 2;
        a1[2] = 3;
        a1[3] = 4;
        this.some = a1;
        java.util.Random a2 = new java.util.Random(12345678L);
        this.random = a2;
        this.numberOfComparisions = 0;
        this.numberOfSwaps = 0;
        this.started = false;
        this.active = false;
        this.processed = false;
        this.copyDone = false;
        this.markCompareMap = -1;
        String[] a3 = new String[3];
        a3[0] = "A";
        a3[1] = "B";
        a3[2] = "C";
        this.arrays = a3;
        this.A = a0;
        this.debugMode = false;
        java.util.HashMap a4 = new java.util.HashMap();
        this.markMap = a4;
    }
    
    public void reInitialise(Object a)
    {
        int[] dummy = (int[])a;
        int[] a0 = (int[])a;
        this.data = a0;
        this.initialise(true);
        this.setPosition("");
        int[] a1 = this.data;
        int i = a1.length;
        Integer[] a2 = new Integer[i];
        this.A = a2;
        int i0 = 0;
        while(true)
        {
            int[] a3 = this.data;
            int i1 = a3.length;
            if(i0 >= i1)
            {
                break;
            }
            else
            {
                Integer[] a4 = this.A;
                int[] a5 = this.data;
                int i2 = a5[i0];
                Integer a6 = new Integer(i2);
                a4[i0] = a6;
                int i3 = i0 + 1;
                i0 = i3;
            }
        }
        this.leftChild = null;
        this.rightChild = null;
        this.B = null;
        this.C = null;
        int i4 = 0;
        while(true)
        {
            String[] a7 = this.arrays;
            int i5 = a7.length;
            if(i4 >= i5)
            {
                this.markCompareMap = -1;
                this.copyDone = false;
                return;
            }
            else
            {
                java.util.HashMap a8 = this.markMap;
                String[] a9 = this.arrays;
                String s = a9[i4];
                Integer a10 = new Integer(-1);
                Object a11 = a8.put((Object)s, (Object)a10);
                int i6 = i4 + 1;
                i4 = i6;
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
                MergeSort.BACKGROUND = a0;
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
                MergeSort.TEXT_COLOR = a1;
                break label1;
            }
            String s2 = MergeSort.PARTITION;
            int i1 = s.equalsIgnoreCase(s2)?1:0;
            label3: {
                if(i1 == 0)
                {
                    break label3;
                }
                java.awt.Color a2 = a.getColor();
                MergeSort.PARTITION_COLOR = a2;
                break label1;
            }
            String s3 = MergeSort.ACTIVE;
            int i2 = s.equalsIgnoreCase(s3)?1:0;
            label4: {
                if(i2 == 0)
                {
                    break label4;
                }
                java.awt.Color a3 = a.getColor();
                MergeSort.ACTIVE_COLOR = a3;
                break label1;
            }
            String s4 = MergeSort.PROCESSED;
            int i3 = s.equalsIgnoreCase(s4)?1:0;
            label5: {
                if(i3 == 0)
                {
                    break label5;
                }
                java.awt.Color a4 = a.getColor();
                MergeSort.DOING_CHILDREN_COLOR = a4;
                break label1;
            }
            String s5 = MergeSort.FINISHED;
            int i4 = s.equalsIgnoreCase(s5)?1:0;
            label6: {
                if(i4 == 0)
                {
                    break label6;
                }
                java.awt.Color a5 = a.getColor();
                MergeSort.FINISHED_COLOR = a5;
                break label1;
            }
            String s6 = MergeSort.WAITING;
            int i5 = s.equalsIgnoreCase(s6)?1:0;
            label7: {
                if(i5 == 0)
                {
                    break label7;
                }
                java.awt.Color a6 = a.getColor();
                MergeSort.WAITING_COLOR = a6;
                break label1;
            }
            String s7 = MergeSort.highlightLabel;
            int i6 = s.equalsIgnoreCase(s7)?1:0;
            label8: {
                if(i6 == 0)
                {
                    break label8;
                }
                java.awt.Color a7 = a.getColor();
                MergeSort.HIGHLIGHT_COLOR = a7;
                break label1;
            }
            String s8 = MergeSort.selectionLabel;
            int i7 = s.equalsIgnoreCase(s8)?1:0;
            if(i7 != 0)
            {
                java.awt.Color a8 = a.getColor();
                MergeSort.selectionColor = a8;
            }
        }
    }
    
    public String getClassName()
    {
        return "MergeSort";
    }
    
    protected void addHighlight(int i)
    {
        java.util.Hashtable a = MergeSort.highlightedElements;
        Integer a0 = new Integer(i);
        Boolean a1 = new Boolean(true);
        Object a2 = a.put((Object)a0, (Object)a1);
    }
    
    protected boolean isHighlighted(int i)
    {
        java.util.Hashtable a = MergeSort.highlightedElements;
        Integer a0 = new Integer(i);
        Object a1 = a.get((Object)a0);
        int i0 = a1 == null?0:1;
        return i0 != 0;
    }
    
    protected void removeAllHighlight()
    {
        java.util.Hashtable a = MergeSort.highlightedElements;
        a.clear();
    }
    
    protected void removeHighlight(int i)
    {
        java.util.Hashtable a = MergeSort.highlightedElements;
        Integer a0 = new Integer(i);
        Object a1 = a.remove((Object)a0);
    }
    
    public int getLeft()
    {
        int i = this.left;
        return i;
    }
    
    public int getRight()
    {
        int i = this.right;
        return i;
    }
    
    public MergeSort getLeftChild()
    {
        MergeSort a = this.leftChild;
        return a;
    }
    
    public MergeSort getRightChild()
    {
        MergeSort a = this.rightChild;
        return a;
    }
    
    public void display()
    {
    }
    
    public int[] getData()
    {
        int[] a = this.data;
        return a;
    }
    
    public boolean isFinished()
    {
        int i = this.processed?1:0;
        return i != 0;
    }
    
    public boolean haveStarted()
    {
        int i = this.started?1:0;
        return i != 0;
    }
    
    public void resetRandomSeed()
    {
        MergeSort.needtoresetSeed = true;
    }
    
    public boolean isExpanded(String s)
    {
        com.cim.AIA.AlgorithmThread a = this.algorithmThread;
        MergeSortThread dummy = (MergeSortThread)a;
        MergeSortThread a0 = (MergeSortThread)a;
        com.cim.AIA.AlgorithmWindow a1 = a0.getWindow();
        int i = a1.isExpanded(s)?1:0;
        return i != 0;
    }
    
    public void setPosition(String s, boolean b)
    {
        if(b)
        {
            this.setPosition(s);
        }
    }
    
    public void run()
    {
        int i = MergeSort.needtoresetSeed?1:0;
        if(i != 0)
        {
            java.util.Random a = this.random;
            a.setSeed(12345678L);
            MergeSort.needtoresetSeed = false;
        }
        this.runMergeSort(true);
    }
    
    public void setCurrentTint(java.awt.Color a)
    {
        this.tint = a;
    }
    
    public java.awt.Color getCurrentTint()
    {
        java.awt.Color a = null;
        java.awt.Color a0 = this.tint;
        if(a0 == null)
        {
            java.awt.Color a1 = MergeSort.ACTIVE_COLOR;
            a = a1;
        }
        else
        {
            java.awt.Color a2 = this.tint;
            a = a2;
        }
        return a;
    }
    
    public void setCompareMark(int i, java.awt.Color a)
    {
        this.markCompareMap = i;
        this.markCompareColor = a;
    }
    
    public void setCompareMark(int i, java.awt.Color a, boolean b)
    {
        if(b)
        {
            this.setCompareMark(i, a);
        }
    }
    
    public void clearCompareMark()
    {
        this.markCompareMap = -1;
    }
    
    protected Integer getCompareMark()
    {
        int i = this.markCompareMap;
        Integer a = new Integer(i);
        return a;
    }
    
    protected void setMark(String s, int i)
    {
        java.util.HashMap a = this.markMap;
        if(a != null)
        {
            java.util.HashMap a0 = this.markMap;
            Integer a1 = new Integer(i);
            Object a2 = a0.put((Object)s, (Object)a1);
        }
    }
    
    protected void clearMark(String s)
    {
        java.util.HashMap a = this.markMap;
        if(a != null)
        {
            java.util.HashMap a0 = this.markMap;
            Integer a1 = new Integer(-1);
            Object a2 = a0.put((Object)s, (Object)a1);
        }
    }
    
    protected Integer getMark(String s)
    {
        Integer a = null;
        java.util.HashMap a0 = this.markMap;
        label1: {
            label0: {
                if(a0 != null)
                {
                    break label0;
                }
                a = null;
                break label1;
            }
            java.util.HashMap a1 = this.markMap;
            int i = a1.containsKey((Object)s)?1:0;
            if(i == 0)
            {
                a = null;
            }
            else
            {
                java.util.HashMap a2 = this.markMap;
                Object a3 = a2.get((Object)s);
                Integer dummy = (Integer)a3;
                Integer a4 = (Integer)a3;
                a = a4;
            }
        }
        return a;
    }
    
    protected void doCopy(boolean b)
    {
        int i = 0;
        int i0 = 0;
        int i1 = 0;
        label2: {
            int i2 = 0;
            label1: {
                int i3 = 0;
                label0: {
                    i2 = b?1:0;
                    int i4 = b?1:0;
                    if(!b)
                    {
                        i3 = i4;
                        break label0;
                    }
                    int i5 = this.isExpanded("4.4")?1:0;
                    if(i5 == 0)
                    {
                        i3 = i2;
                    }
                    else
                    {
                        break label1;
                    }
                }
                i = 0;
                i0 = i3;
                break label2;
            }
            i = 1;
            i0 = i2;
        }
        this.setMark("A", 0);
        this.setMark("B", 0);
        this.setPosition("4.4.1", i != 0);
        this.setPosition("4.4.2", i != 0);
        int i6 = 0;
        while(true)
        {
            Integer[] a = this.B;
            int i7 = a.length;
            if(i6 >= i7)
            {
                break;
            }
            else
            {
                this.setMark("A", i6);
                this.setMark("B", i6);
                this.setPosition("4.4.2.1", i != 0);
                Integer[] a0 = this.B;
                Integer[] a1 = this.A;
                Integer a2 = a1[i6];
                a0[i6] = a2;
                this.setPosition("4.4.2.2", i != 0);
                int i8 = i6 + 1;
                i6 = i8;
            }
        }
        this.clearMark("B");
        label5: {
            label4: {
                label3: {
                    if(i0 == 0)
                    {
                        break label3;
                    }
                    int i9 = this.isExpanded("4.5")?1:0;
                    if(i9 != 0)
                    {
                        break label4;
                    }
                }
                i1 = 0;
                break label5;
            }
            i1 = 1;
        }
        this.setPosition("4.5", i0 != 0);
        this.setPosition("4.5.1", i1 != 0);
        this.setPosition("4.5.2", i1 != 0);
        this.setPosition("4.5.3", i1 != 0);
        Integer[] a3 = this.B;
        int i10 = a3.length;
        int i11 = i10;
        while(true)
        {
            Integer[] a4 = this.A;
            int i12 = a4.length;
            if(i11 >= i12)
            {
                this.clearMark("C");
                this.clearMark("A");
                return;
            }
            else
            {
                this.setMark("A", i11);
                Integer[] a5 = this.B;
                int i13 = a5.length;
                int i14 = i11 - i13;
                this.setMark("C", i14);
                Integer[] a6 = this.C;
                Integer[] a7 = this.B;
                int i15 = a7.length;
                int i16 = i11 - i15;
                Integer[] a8 = this.A;
                Integer a9 = a8[i11];
                a6[i16] = a9;
                this.setPosition("4.5.3.1", i1 != 0);
                this.setPosition("4.5.3.2", i1 != 0);
                this.setPosition("4.5.3.3", i1 != 0);
                int i17 = i11 + 1;
                i11 = i17;
            }
        }
    }
    
    public void runMergeSort(boolean b)
    {
        this.started = true;
        int i = b?1:0;
        int i0 = this.enabled?1:0;
        label1: {
            label0: {
                if(i0 != 0)
                {
                    break label0;
                }
                this.processed = true;
                break label1;
            }
            this.active = true;
            this.setPosition("1", i != 0);
            this.setPosition("3", i != 0);
            Integer[] a = this.A;
            int i1 = a.length;
            label2: {
                int i2 = 0;
                int i3 = 0;
                int i4 = 0;
                if(i1 <= 1)
                {
                    break label2;
                }
                this.setPosition("4", i != 0);
                int i5 = this.isExpanded("4")?1:0;
                label5: {
                    label4: {
                        label3: {
                            if(i5 == 0)
                            {
                                break label3;
                            }
                            if(i != 0)
                            {
                                break label4;
                            }
                        }
                        i2 = 0;
                        break label5;
                    }
                    i2 = 1;
                }
                this.setPosition("4.1", i2 != 0);
                Integer[] a0 = this.A;
                Object a1 = java.util.Arrays.asList((Object[])a0);
                Integer[] a2 = this.A;
                int i6 = a2.length;
                double d = (double)i6;
                double d0 = d / 2.0;
                double d1 = Math.floor(d0);
                long j = Math.round(d1);
                int i7 = (int)j;
                this.setPosition("4.2", i2 != 0);
                Integer[] a3 = new Integer[i7];
                this.B = a3;
                this.setPosition("4.3", i2 != 0);
                Integer[] a4 = this.A;
                int i8 = a4.length;
                int i9 = i8 - i7;
                Integer[] a5 = new Integer[i9];
                this.C = a5;
                this.setPosition("4.4", i2 != 0);
                this.doCopy(i2 != 0);
                this.copyDone = true;
                com.cim.AIA.AlgorithmThread a6 = this.algorithmThread;
                Integer[] a7 = this.B;
                MergeSort a8 = new MergeSort(a6, a7);
                this.leftChild = a8;
                com.cim.AIA.AlgorithmThread a9 = this.algorithmThread;
                Integer[] a10 = this.C;
                MergeSort a11 = new MergeSort(a9, a10);
                this.rightChild = a11;
                int i10 = this.isExpanded("5")?1:0;
                label8: {
                    label7: {
                        label6: {
                            if(i10 == 0)
                            {
                                break label6;
                            }
                            if(i != 0)
                            {
                                break label7;
                            }
                        }
                        i3 = 0;
                        break label8;
                    }
                    i3 = 1;
                }
                this.setPosition("5", i != 0);
                this.setPosition("5.1", i3 != 0);
                MergeSort a12 = this.leftChild;
                a12.runMergeSort(i3 != 0);
                MergeSort a13 = this.leftChild;
                a13.processed = true;
                int i11 = this.isExpanded("6")?1:0;
                label11: {
                    label10: {
                        label9: {
                            if(i11 == 0)
                            {
                                break label9;
                            }
                            if(i != 0)
                            {
                                break label10;
                            }
                        }
                        i4 = 0;
                        break label11;
                    }
                    i4 = 1;
                }
                this.setPosition("6", i != 0);
                this.setPosition("6.1", i4 != 0);
                MergeSort a14 = this.rightChild;
                a14.runMergeSort(i4 != 0);
                MergeSort a15 = this.rightChild;
                a15.processed = true;
                this.merge(i != 0);
                this.rightChild = null;
                this.leftChild = null;
                this.processed = true;
            }
        }
    }
    
    protected void makeQuestion()
    {
    }
    
    protected void removeAllQuestions()
    {
        java.util.Vector a = MergeSort.questions;
        a.removeAllElements();
    }
    
    protected boolean hasQuestions()
    {
        return false;
    }
    
    public java.util.Vector generateQuestions()
    {
        return null;
    }
    
    public com.cim.AIA.MultipleTween generateTweens(com.cim.AIA.Paintable a, Object a0, int i)
    {
        return null;
    }
    
    protected void generateTweens(com.cim.AIA.MultipleTween a, MyElementArray a0, int i)
    {
    }
    
    public int getNumberOfSwaps()
    {
        int i = 0;
        int i0 = 0;
        int i1 = this.numberOfSwaps;
        MergeSort a = this.leftChild;
        if(a == null)
        {
            i = i1;
        }
        else
        {
            MergeSort a0 = this.leftChild;
            int i2 = a0.getNumberOfSwaps();
            int i3 = i1 + i2;
            i = i3;
        }
        MergeSort a1 = this.rightChild;
        if(a1 == null)
        {
            i0 = i;
        }
        else
        {
            MergeSort a2 = this.rightChild;
            int i4 = a2.getNumberOfSwaps();
            int i5 = i + i4;
            i0 = i5;
        }
        return i0;
    }
    
    public int getNumberOfComparisions()
    {
        int i = 0;
        int i0 = 0;
        int i1 = this.numberOfComparisions;
        MergeSort a = this.leftChild;
        if(a == null)
        {
            i = i1;
        }
        else
        {
            MergeSort a0 = this.leftChild;
            int i2 = a0.getNumberOfComparisions();
            int i3 = i1 + i2;
            i = i3;
        }
        MergeSort a1 = this.rightChild;
        if(a1 == null)
        {
            i0 = i;
        }
        else
        {
            MergeSort a2 = this.rightChild;
            int i4 = a2.getNumberOfComparisions();
            int i5 = i + i4;
            i0 = i5;
        }
        return i0;
    }
    
    protected int getDepth(int i)
    {
        int i0 = 0;
        MergeSort a = this.leftChild;
        label1: {
            label0: {
                if(a == null)
                {
                    break label0;
                }
                MergeSort a0 = this.rightChild;
                if(a0 == null)
                {
                    break label0;
                }
                MergeSort a1 = this.leftChild;
                int i1 = i + 1;
                int i2 = a1.getDepth(i1);
                MergeSort a2 = this.rightChild;
                int i3 = i + 1;
                int i4 = a2.getDepth(i3);
                int i5 = Math.max(i2, i4);
                i0 = i5;
                break label1;
            }
            MergeSort a3 = this.leftChild;
            label2: {
                if(a3 == null)
                {
                    break label2;
                }
                MergeSort a4 = this.leftChild;
                int i6 = i + 1;
                int i7 = a4.getDepth(i6);
                i0 = i7;
                break label1;
            }
            MergeSort a5 = this.rightChild;
            if(a5 == null)
            {
                i0 = i;
            }
            else
            {
                MergeSort a6 = this.rightChild;
                int i8 = i + 1;
                int i9 = a6.getDepth(i8);
                i0 = i9;
            }
        }
        return i0;
    }
    
    public int getWidth(java.awt.Graphics a)
    {
        int i = this.getRight();
        int i0 = 1 + i;
        int i1 = this.getLeft();
        int i2 = i0 - i1;
        int i3 = this.columWidth;
        int i4 = this.columSpacing;
        int i5 = i3 + i4;
        int i6 = i2 * i5;
        return i6;
    }
    
    public int getHeight(java.awt.Graphics a)
    {
        int i = this.getDepth(1);
        java.awt.FontMetrics a0 = a.getFontMetrics();
        int i0 = a0.getHeight();
        int i1 = this.yTextBuffer;
        int i2 = i0 + i1;
        int i3 = 2 * i2;
        int i4 = i * i3;
        return i4;
    }
    
    public void drawInProgress(java.awt.Point a, java.awt.Graphics a0, Integer[] a1, String s)
    {
        int i = a.x;
        int i0 = a.y;
        MyElementArray a2 = new MyElementArray(i, i0);
        int i1 = this.columSpacing;
        a2.setColumGap(i1);
        int i2 = this.columWidth;
        a2.setElementWidth(i2);
        int i3 = a1.length;
        int[] a3 = new int[i3];
        int i4 = 0;
        while(true)
        {
            Integer a4 = null;
            int i5 = 0;
            int i6 = a1.length;
            if(i4 >= i6)
            {
                break;
            }
            Integer a5 = new Integer(0);
            Integer a6 = a1[i4];
            if(a6 == null)
            {
                a4 = a5;
                i5 = 1;
            }
            else
            {
                Integer a7 = a1[i4];
                a4 = a7;
                i5 = 0;
            }
            java.awt.Color a8 = this.getCurrentTint();
            java.awt.Point a9 = new java.awt.Point(0, 0);
            MyVertBar a10 = new MyVertBar(i4, a4, a8, a9);
            a10.setIsEmpty(i5 != 0);
            java.awt.Color a11 = MergeSort.TEXT_COLOR;
            a10.setTextColor(a11);
            java.awt.Color a12 = MergeSort.selectionColor;
            a10.setHighlightColor(a12);
            a2.setValue(i4, (com.cim.AIA.Element)a10);
            com.cim.AIA.Element a13 = a2.getElement(i4);
            com.cim.AIA.VerticalBar dummy = (com.cim.AIA.VerticalBar)a13;
            com.cim.AIA.VerticalBar a14 = (com.cim.AIA.VerticalBar)a13;
            a14.setMarkersAboveValue(false);
            com.cim.AIA.Element a15 = a2.getElement(i4);
            com.cim.AIA.VerticalBar dummy0 = (com.cim.AIA.VerticalBar)a15;
            com.cim.AIA.VerticalBar a16 = (com.cim.AIA.VerticalBar)a15;
            a16.setOffsetForValue(1);
            int i7 = i4 + 1;
            i4 = i7;
        }
        java.awt.Rectangle a17 = a0.getClipBounds();
        int i8 = a17.width;
        int i9 = a2.getWidth();
        int i10 = i8 - i9;
        int i11 = i10 / 2;
        int i12 = a.y;
        a2.setLocation(i11, i12);
        Integer a18 = this.getMark(s);
        label2: {
            label1: {
                label0: {
                    if(a18 == null)
                    {
                        break label0;
                    }
                    int i13 = a18.intValue();
                    if(i13 != -1)
                    {
                        break label1;
                    }
                }
                a2.clearMarks();
                break label2;
            }
            int i14 = a18.intValue();
            a2.setMark("", i14);
        }
        a2.draw(a0);
    }
    
    public void drawTree(java.awt.Point a, java.awt.Graphics a0)
    {
        int i = a.x;
        int i0 = a.y;
        MyElementArray a1 = new MyElementArray(i, i0);
        int i1 = this.columSpacing;
        a1.setColumGap(i1);
        int i2 = this.columWidth;
        a1.setElementWidth(i2);
        int i3 = 0;
        while(true)
        {
            Integer[] a2 = this.A;
            int i4 = a2.length;
            if(i3 >= i4)
            {
                break;
            }
            else
            {
                Integer[] a3 = this.A;
                Integer a4 = a3[i3];
                java.awt.Color a5 = this.getCurrentTint();
                java.awt.Point a6 = new java.awt.Point(0, 0);
                MyVertBar a7 = new MyVertBar(i3, a4, a5, a6);
                java.awt.Color a8 = MergeSort.TEXT_COLOR;
                a7.setTextColor(a8);
                java.awt.Color a9 = MergeSort.selectionColor;
                a7.setHighlightColor(a9);
                a1.setValue(i3, (com.cim.AIA.Element)a7);
                com.cim.AIA.Element a10 = a1.getElement(i3);
                com.cim.AIA.VerticalBar dummy = (com.cim.AIA.VerticalBar)a10;
                com.cim.AIA.VerticalBar a11 = (com.cim.AIA.VerticalBar)a10;
                a11.setMarkersAboveValue(false);
                com.cim.AIA.Element a12 = a1.getElement(i3);
                com.cim.AIA.VerticalBar dummy0 = (com.cim.AIA.VerticalBar)a12;
                com.cim.AIA.VerticalBar a13 = (com.cim.AIA.VerticalBar)a12;
                a13.setOffsetForValue(1);
                int i5 = i3 + 1;
                i3 = i5;
            }
        }
        int i6 = MergeSort.maxHeight;
        if(i6 < 0)
        {
            int i7 = a1.getHeight();
            MergeSort.maxHeight = i7;
        }
        int i8 = MergeSort.maxWidth;
        if(i8 < 0)
        {
            int i9 = a1.getWidth();
            MergeSort.maxWidth = i9;
        }
        int i10 = MergeSort.maxHeight;
        int i11 = i10 + 30;
        int i12 = i11 + 25;
        int i13 = a1.getWidth();
        java.awt.Rectangle a14 = a0.getClipBounds();
        int i14 = a14.width;
        int i15 = a1.getWidth();
        int i16 = i14 - i15;
        int i17 = i16 / 2;
        int i18 = a.y;
        a1.setLocation(i17, i18);
        Integer a15 = this.getMark("A");
        label2: {
            label1: {
                label0: {
                    if(a15 == null)
                    {
                        break label0;
                    }
                    int i19 = a15.intValue();
                    if(i19 != -1)
                    {
                        break label1;
                    }
                }
                a1.clearMarks();
                break label2;
            }
            int i20 = a15.intValue();
            a1.setMark("", i20);
        }
        Integer a16 = this.getCompareMark();
        int i21 = a16.intValue();
        if(i21 == -1)
        {
            a1.clearCompareMarks();
        }
        else
        {
            Integer a17 = this.getCompareMark();
            int i22 = a17.intValue();
            java.awt.Color a18 = this.markCompareColor;
            a1.setCompareMark(i22, a18);
        }
        a1.draw(a0);
        int i23 = a14.width;
        int i24 = i23 / 2;
        int i25 = a14.height;
        int i26 = i25 - i12;
        java.awt.Graphics a19 = a0.create(0, i12, i24, i26);
        int i27 = a14.width;
        int i28 = i27 / 2;
        int i29 = a14.width;
        int i30 = i29 / 2;
        int i31 = a14.height;
        int i32 = i31 - i12;
        java.awt.Graphics a20 = a0.create(i28, i12, i30, i32);
        MergeSort a21 = this.leftChild;
        label4: {
            label3: {
                if(a21 == null)
                {
                    break label3;
                }
                MergeSort a22 = this.leftChild;
                java.awt.Color a23 = this.getCurrentTint();
                java.awt.Color a24 = MergeSort.tintLeft(a23);
                a22.setCurrentTint(a24);
                MergeSort a25 = this.leftChild;
                a25.drawTree(a, a19);
                break label4;
            }
            Integer[] a26 = this.B;
            if(a26 == null)
            {
                break label4;
            }
            int i33 = this.copyDone?1:0;
            if(i33 == 0)
            {
                Integer[] a27 = this.B;
                this.drawInProgress(a, a19, a27, "B");
            }
        }
        MergeSort a28 = this.rightChild;
        label6: {
            label5: {
                if(a28 == null)
                {
                    break label5;
                }
                MergeSort a29 = this.rightChild;
                java.awt.Color a30 = this.getCurrentTint();
                java.awt.Color a31 = MergeSort.tintRight(a30);
                a29.setCurrentTint(a31);
                MergeSort a32 = this.rightChild;
                a32.drawTree(a, a20);
                break label6;
            }
            Integer[] a33 = this.C;
            if(a33 == null)
            {
                break label6;
            }
            int i34 = this.copyDone?1:0;
            if(i34 == 0)
            {
                Integer[] a34 = this.C;
                this.drawInProgress(a, a20, a34, "C");
            }
        }
    }
    
    static
    {
        String s = localization.Messages.getString("MergeSort.0");
        MergeSort.selectionLabel = s;
        String s0 = localization.Messages.getString("MergeSort.1");
        MergeSort.highlightLabel = s0;
        String s1 = localization.Messages.getString("MergeSort.2");
        MergeSort.ACTIVE = s1;
        String s2 = localization.Messages.getString("MergeSort.3");
        MergeSort.PROCESSED = s2;
        String s3 = localization.Messages.getString("MergeSort.4");
        MergeSort.FINISHED = s3;
        String s4 = localization.Messages.getString("MergeSort.5");
        MergeSort.WAITING = s4;
        String s5 = localization.Messages.getString("MergeSort.6");
        MergeSort.PARTITION = s5;
        java.awt.Color a = java.awt.Color.yellow;
        MergeSort.selectionColor = a;
        java.awt.Color a0 = java.awt.Color.white;
        MergeSort.BACKGROUND = a0;
        java.awt.Color a1 = java.awt.Color.black;
        MergeSort.TEXT_COLOR = a1;
        java.awt.Color a2 = java.awt.Color.yellow;
        MergeSort.PARTITION_COLOR = a2;
        java.awt.Color a3 = java.awt.Color.pink;
        MergeSort.WAITING_COLOR = a3;
        java.awt.Color a4 = java.awt.Color.green;
        MergeSort.HIGHLIGHT_COLOR = a4;
        java.awt.Color a5 = java.awt.Color.red;
        java.awt.Color a6 = a5.darker();
        MergeSort.DOING_CHILDREN_COLOR = a6;
        java.awt.Color a7 = java.awt.Color.MAGENTA;
        MergeSort.ACTIVE_COLOR = a7;
        java.awt.Color a8 = java.awt.Color.gray;
        MergeSort.FINISHED_COLOR = a8;
        java.util.Vector a9 = new java.util.Vector();
        MergeSort.swapRequests = a9;
        java.util.Vector a10 = new java.util.Vector();
        MergeSort.questions = a10;
        java.util.Hashtable a11 = new java.util.Hashtable();
        MergeSort.highlightedElements = a11;
        java.awt.Color a12 = java.awt.Color.CYAN;
        MergeSort.kBHighlightColor = a12;
        java.awt.Color a13 = java.awt.Color.YELLOW;
        MergeSort.kCHighlightColor = a13;
        java.awt.Color a14 = java.awt.Color.LIGHT_GRAY;
        MergeSort.kCopyBackColor = a14;
        MergeSort.needtoresetSeed = false;
        MergeSort.maxHeight = -1;
        MergeSort.maxWidth = -1;
    }
}