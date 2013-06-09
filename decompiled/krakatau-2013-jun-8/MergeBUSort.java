public class MergeBUSort extends com.cim.AIA.Algorithm implements com.cim.AIA.ColorSelectionListener {
    protected static String[] duplicateLabels;
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
    final private static java.awt.Color HONEY_DEW;
    final private static java.awt.Color CANTALOUPE;
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
    final public static int MARKER_NOT_READY = -10;
    final public static int RIGHT_PARTITION = 10;
    final public static int RANDOM_PARTITION = 11;
    final public static int MIDDLE_OF_THREE_RANDOM = 12;
    final public static int MIDDLE_OF_THREE = 13;
    public int[] some;
    final protected static boolean DEBUG_MODE = 0;
    protected static int currentPartitionMethod;
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
    private int offsetIntoA;
    private int segmentSize;
    protected int left;
    protected int right;
    protected boolean started;
    protected boolean active;
    protected boolean processed;
    protected boolean doingChildren;
    protected static boolean needtoresetSeed;
    private static int maxHeight;
    private static int maxWidth;
    private java.awt.Color tint;
    private java.util.HashMap markMap;
    private int markCompareMap;
    private int markCompareMapB;
    private int markCompareMapC;
    private java.awt.Color markCompareColorB;
    private java.awt.Color markCompareColorC;
    private java.awt.Color markCompareColor;
    private String[] arrays;
    
    private static java.awt.Color tintLeft(java.awt.Color a)
    {
        float[] a0 = new float[4];
        float[] a1 = a.getRGBComponents(a0);
        float f = a1[0];
        float f0 = f * 1.5f;
        a1[0] = f0;
        float f1 = a1[0];
        float f2 = Math.min(f1, 1.0f);
        a1[0] = f2;
        float f3 = a1[1];
        float f4 = f3 * 0.8999999761581421f;
        a1[1] = f4;
        float f5 = a1[1];
        float f6 = Math.min(f5, 1.0f);
        a1[1] = f6;
        java.awt.Color a2 = new java.awt.Color(204, 0, 204);
        return a2;
    }
    
    private static java.awt.Color tintRight(java.awt.Color a)
    {
        java.awt.Color a0 = java.awt.Color.MAGENTA;
        return a0;
    }
    
    public MergeBUSort(com.cim.AIA.AlgorithmThread a, Object a0)
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
        this.offsetIntoA = 0;
        this.segmentSize = -1;
        this.started = false;
        this.active = false;
        this.processed = false;
        this.doingChildren = false;
        this.markCompareMap = -1;
        this.markCompareMapB = -1;
        this.markCompareMapC = -1;
        String[] a3 = new String[2];
        a3[0] = "A";
        a3[1] = "B";
        this.arrays = a3;
        int[] dummy = (int[])a0;
        int[] a4 = (int[])a0;
        this.data = a4;
        this.offsetIntoA = 0;
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
                java.awt.Color a10 = MergeBUSort.PARTITION_COLOR;
                String s = MergeBUSort.PARTITION;
                com.cim.AIA.ColorSelection a11 = new com.cim.AIA.ColorSelection(a10, s);
                a11.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
                a9.addColorSelection(a11);
                a9.addColorSelectionListener((com.cim.AIA.ColorSelectionListener)this);
                java.awt.Color a12 = MergeBUSort.ACTIVE_COLOR;
                String s0 = MergeBUSort.ACTIVE;
                com.cim.AIA.ColorSelection a13 = new com.cim.AIA.ColorSelection(a12, s0);
                a13.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
                a9.addColorSelection(a13);
                java.awt.Color a14 = MergeBUSort.DOING_CHILDREN_COLOR;
                String s1 = MergeBUSort.PROCESSED;
                com.cim.AIA.ColorSelection a15 = new com.cim.AIA.ColorSelection(a14, s1);
                a15.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
                a9.addColorSelection(a15);
                java.awt.Color a16 = MergeBUSort.FINISHED_COLOR;
                String s2 = MergeBUSort.FINISHED;
                com.cim.AIA.ColorSelection a17 = new com.cim.AIA.ColorSelection(a16, s2);
                a17.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
                a9.addColorSelection(a17);
                java.awt.Color a18 = MergeBUSort.WAITING_COLOR;
                String s3 = MergeBUSort.WAITING;
                com.cim.AIA.ColorSelection a19 = new com.cim.AIA.ColorSelection(a18, s3);
                a19.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
                a9.addColorSelection(a19);
                java.awt.Color a20 = MergeBUSort.HIGHLIGHT_COLOR;
                String s4 = MergeBUSort.highlightLabel;
                com.cim.AIA.ColorSelection a21 = new com.cim.AIA.ColorSelection(a20, s4);
                a21.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
                a9.addColorSelection(a21);
                a9.addColorSelectionListener((com.cim.AIA.ColorSelectionListener)this);
                java.awt.Color a22 = MergeBUSort.selectionColor;
                String s5 = MergeBUSort.selectionLabel;
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
        int i = b?1:0;
        this.active = false;
        this.processed = false;
        if(i != 0)
        {
            this.left = 0;
            int[] a = this.data;
            int i0 = a.length;
            int i1 = i0 - 1;
            this.right = i1;
            int[] a0 = this.data;
            this.createDuplicateLabels(a0);
        }
    }
    
    protected void removeAllAnimationRequests()
    {
    }
    
    private void setOffsetIntoA(int i)
    {
        this.offsetIntoA = i;
    }
    
    private int offsetIntoA()
    {
        int i = this.offsetIntoA;
        return i;
    }
    
    void mergeBU(boolean b)
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
        int i7 = this.offsetIntoA();
        label2: {
            label1: {
                label0: {
                    if(i6 == 0)
                    {
                        break label0;
                    }
                    int i8 = this.isExpanded("7")?1:0;
                    if(i8 != 0)
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
        int i9 = a.length;
        Integer[] a0 = this.C;
        int i10 = a0.length;
        int i11 = 0 + i7;
        this.setMark("A", i11);
        this.setMark("B", 0);
        this.setMark("C", 0);
        label5: {
            label4: {
                label3: {
                    if(i6 == 0)
                    {
                        break label3;
                    }
                    int i12 = this.isExpanded("7")?1:0;
                    if(i12 != 0)
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
                    int i13 = this.isExpanded("7.1.1")?1:0;
                    if(i13 != 0)
                    {
                        break label7;
                    }
                }
                i1 = 0;
                break label8;
            }
            i1 = 1;
        }
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        int i17 = i1;
        while(true)
        {
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
            int i29 = 0;
            int i30 = 0;
            int i31 = i14;
            int i32 = i15;
            int i33 = i17;
            if(i14 >= i9)
            {
                i2 = i14;
                i3 = i15;
                break;
            }
            else
            {
                i18 = i31;
                i19 = i32;
                i20 = i33;
            }
            int i34 = i18;
            int i35 = i19;
            int i36 = i20;
            if(i19 >= i10)
            {
                i2 = i18;
                i3 = i19;
                break;
            }
            else
            {
                i21 = i34;
                i22 = i35;
                i23 = i36;
            }
            int i37 = i16 + i7;
            this.setMark("A", i37);
            int i38 = i21;
            int i39 = i22;
            int i40 = i23;
            this.setMark("B", i38);
            int i41 = i38;
            int i42 = i39;
            int i43 = i40;
            this.setMark("C", i42);
            int i44 = i41;
            int i45 = i42;
            int i46 = i43;
            label11: {
                int i47 = 0;
                int i48 = 0;
                label10: {
                    int i49 = 0;
                    int i50 = 0;
                    int i51 = 0;
                    label9: {
                        int i52 = 0;
                        int i53 = 0;
                        int i54 = i44;
                        int i55 = i45;
                        int i56 = i44;
                        int i57 = i45;
                        int i58 = i46;
                        if(i46 == 0)
                        {
                            i49 = i56;
                            i50 = i57;
                            i51 = i58;
                            break label9;
                        }
                        else
                        {
                            i52 = i54;
                            i53 = i55;
                        }
                        int i59 = this.isExpanded("7.1.1.1.1")?1:0;
                        int i60 = i52;
                        int i61 = i53;
                        int i62 = i60;
                        int i63 = i61;
                        int i64 = i60;
                        int i65 = i61;
                        if(i59 == 0)
                        {
                            i49 = i64;
                            i50 = i65;
                            i51 = 1;
                        }
                        else
                        {
                            i47 = i62;
                            i48 = i63;
                            break label10;
                        }
                    }
                    i24 = 0;
                    i25 = i49;
                    i26 = i50;
                    i27 = i51;
                    break label11;
                }
                i24 = 1;
                i25 = i47;
                i26 = i48;
                i27 = 1;
            }
            java.awt.Color a1 = java.awt.Color.CYAN;
            int i66 = i25;
            int i67 = i26;
            int i68 = i27;
            this.setCompareMark("B", i25, a1, i24 != 0);
            int i69 = i66;
            int i70 = i67;
            int i71 = i68;
            this.setPosition("7.1.1.1.1", i71 != 0);
            int i72 = i69;
            int i73 = i70;
            int i74 = i71;
            java.awt.Color a2 = java.awt.Color.YELLOW;
            int i75 = i72;
            int i76 = i73;
            int i77 = i74;
            this.setCompareMark("C", i73, a2, i24 != 0);
            int i78 = i75;
            int i79 = i76;
            int i80 = i77;
            this.setPosition("7.1.1.1.1.1", i80 != 0);
            int i81 = i78;
            int i82 = i79;
            int i83 = i80;
            Integer[] a3 = this.B;
            int i84 = i81;
            int i85 = i82;
            int i86 = i83;
            Integer a4 = a3[i84];
            int i87 = i84;
            int i88 = i85;
            int i89 = i86;
            int i90 = a4.intValue();
            int i91 = i87;
            int i92 = i88;
            int i93 = i89;
            Integer[] a5 = this.C;
            int i94 = i91;
            int i95 = i92;
            int i96 = i93;
            Integer a6 = a5[i95];
            int i97 = i94;
            int i98 = i95;
            int i99 = i96;
            int i100 = a6.intValue();
            int i101 = i97;
            int i102 = i98;
            int i103 = i99;
            int i104 = i102;
            int i105 = i103;
            int i106 = i101;
            int i107 = i103;
            if(i90 > i100)
            {
                int i108 = i106;
                int i109 = i107;
                int i110 = i16 + i7;
                java.awt.Color a7 = java.awt.Color.YELLOW;
                int i111 = i108;
                int i112 = i109;
                this.setCompareMark(i110, a7);
                int i113 = i111;
                int i114 = i112;
                int i115 = i16 + i7;
                this.setMark("A", i115);
                int i116 = i113;
                int i117 = i114;
                this.setPosition("7.1.1.1.1.3", i24 != 0);
                int i118 = i116;
                int i119 = i117;
                Integer[] a8 = this.A;
                int i120 = i118;
                int i121 = i119;
                int i122 = i16 + i7;
                Integer[] a9 = this.C;
                int i123 = i120;
                int i124 = i121;
                Integer a10 = a9[i102];
                int i125 = i123;
                int i126 = i124;
                a8[i122] = a10;
                int i127 = i125;
                int i128 = i126;
                this.setPosition("7.1.1.1.1.3c", i24 != 0);
                int i129 = i127;
                int i130 = i128;
                int i131 = i102 + 1;
                this.setMark("C", i131);
                i28 = i129;
                i29 = i131;
                i30 = i130;
            }
            else
            {
                int i132 = i104;
                int i133 = i105;
                int i134 = i16 + i7;
                java.awt.Color a11 = java.awt.Color.CYAN;
                int i135 = i132;
                int i136 = i133;
                this.setCompareMark(i134, a11);
                int i137 = i135;
                int i138 = i136;
                int i139 = i16 + i7;
                this.setMark("A", i139);
                int i140 = i137;
                int i141 = i138;
                this.setPosition("7.1.1.1.1.2", i24 != 0);
                int i142 = i140;
                int i143 = i141;
                Integer[] a12 = this.A;
                int i144 = i142;
                int i145 = i143;
                int i146 = i16 + i7;
                Integer[] a13 = this.B;
                int i147 = i144;
                int i148 = i145;
                Integer a14 = a13[i101];
                int i149 = i147;
                int i150 = i148;
                a12[i146] = a14;
                int i151 = i149;
                int i152 = i150;
                this.setPosition("7.1.1.1.1.2b", i24 != 0);
                int i153 = i151;
                int i154 = i152;
                int i155 = i101 + 1;
                this.setMark("B", i155);
                int i156 = i153;
                int i157 = i154;
                i28 = i155;
                i29 = i156;
                i30 = i157;
            }
            this.clearCompareMark("B");
            int i158 = i28;
            int i159 = i29;
            int i160 = i30;
            this.clearCompareMark();
            int i161 = i158;
            int i162 = i159;
            int i163 = i160;
            this.clearCompareMark("C");
            int i164 = i161;
            int i165 = i162;
            int i166 = i163;
            int i167 = i16 + 1;
            int i168 = i167 + i7;
            this.setMark("A", i168);
            int i169 = i164;
            int i170 = i165;
            int i171 = i166;
            this.setPosition("7.1.1.1.2", i171 != 0);
            int i172 = i169;
            int i173 = i170;
            int i174 = i171;
            i14 = i172;
            i15 = i173;
            i16 = i167;
            i17 = i174;
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
        int i178 = i16 + i7;
        this.setMark("A", i178);
        this.setPosition("7.1.1.2.1", i5 != 0);
        int i179 = i16 + i7;
        this.setMark("A", i179);
        label22: {
            int i180 = 0;
            label18: {
                int i181 = 0;
                int i182 = 0;
                if(i2 == i9)
                {
                    break label18;
                }
                this.setPosition("7.1.1.2.6", i5 != 0);
                this.setMark("B", i2);
                this.setPosition("7.1.1.2.7", i5 != 0);
                label21: {
                    label20: {
                        int i183 = 0;
                        label19: {
                            if(i5 == 0)
                            {
                                i183 = i5;
                                break label19;
                            }
                            int i184 = this.isExpanded("7.1.1.2.7")?1:0;
                            if(i184 == 0)
                            {
                                i183 = 1;
                            }
                            else
                            {
                                break label20;
                            }
                        }
                        i181 = 0;
                        i182 = i183;
                        break label21;
                    }
                    i181 = 1;
                    i182 = 1;
                }
                this.setPosition("7.1.1.2.8", i182 != 0);
                int i185 = i2;
                int i186 = i16;
                while(true)
                {
                    if(i185 >= i9)
                    {
                        break;
                    }
                    else
                    {
                        this.setMark("B", i185);
                        int i187 = i186 + i7;
                        java.awt.Color a15 = java.awt.Color.LIGHT_GRAY;
                        this.setCompareMark("A", i187, a15, i181 != 0);
                        java.awt.Color a16 = java.awt.Color.LIGHT_GRAY;
                        this.setCompareMark("B", i185, a16, i181 != 0);
                        int i188 = i186 + i7;
                        this.setMark("A", i188);
                        Integer[] a17 = this.A;
                        int i189 = i186 + i7;
                        Integer[] a18 = this.B;
                        Integer a19 = a18[i185];
                        a17[i189] = a19;
                        int i190 = i186 + 1;
                        int i191 = i185 + 1;
                        this.setMark("B", i191);
                        int i192 = i190 + i7;
                        this.setMark("A", i192);
                        this.setPosition("7.1.1.2.9", i181 != 0);
                        this.setPosition("7.1.1.2.10", i181 != 0);
                        this.setPosition("7.1.1.2.11", i181 != 0);
                        this.setPosition("7.1.1.2.8", i181 != 0);
                        i185 = i191;
                        i186 = i190;
                        continue;
                    }
                }
                this.clearCompareMark("B");
                this.clearCompareMark();
                this.clearCompareMark("C");
                break label22;
            }
            this.setMark("C", i3);
            this.setPosition("7.1.1.2.2", i5 != 0);
            label25: {
                label24: {
                    label23: {
                        if(i5 == 0)
                        {
                            break label23;
                        }
                        int i193 = this.isExpanded("7.1.1.2.2")?1:0;
                        if(i193 != 0)
                        {
                            break label24;
                        }
                    }
                    i180 = 0;
                    break label25;
                }
                i180 = 1;
            }
            this.setPosition("7.1.1.2.2.1", i180 != 0);
            this.setMark("C", i3);
            int i194 = i3;
            int i195 = i16;
            while(true)
            {
                if(i194 >= i10)
                {
                    break;
                }
                else
                {
                    this.setMark("C", i194);
                    int i196 = i195 + i7;
                    java.awt.Color a20 = java.awt.Color.LIGHT_GRAY;
                    this.setCompareMark("A", i196, a20, i180 != 0);
                    java.awt.Color a21 = java.awt.Color.LIGHT_GRAY;
                    this.setCompareMark("C", i194, a21, i180 != 0);
                    int i197 = i195 + i7;
                    this.setMark("A", i197);
                    Integer[] a22 = this.A;
                    int i198 = i195 + i7;
                    Integer[] a23 = this.C;
                    Integer a24 = a23[i194];
                    a22[i198] = a24;
                    int i199 = i195 + 1;
                    int i200 = i194 + 1;
                    this.setMark("C", i200);
                    this.setPosition("7.1.1.2.3", i180 != 0);
                    int i201 = i199 + i7;
                    int i202 = i201 - 1;
                    this.setMark("A", i202);
                    this.setPosition("7.1.1.2.4", i180 != 0);
                    this.setPosition("7.1.1.2.5", i180 != 0);
                    this.setPosition("7.1.1.2.2.1", i180 != 0);
                    i194 = i200;
                    i195 = i199;
                }
            }
            this.clearCompareMark("B");
            this.clearCompareMark();
            this.clearCompareMark("C");
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
                MergeBUSort.duplicateLabels = a1;
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
    
    public MergeBUSort(com.cim.AIA.AlgorithmThread a, Integer[] a0)
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
        this.offsetIntoA = 0;
        this.segmentSize = -1;
        this.started = false;
        this.active = false;
        this.processed = false;
        this.doingChildren = false;
        this.markCompareMap = -1;
        this.markCompareMapB = -1;
        this.markCompareMapC = -1;
        String[] a3 = new String[2];
        a3[0] = "A";
        a3[1] = "B";
        this.arrays = a3;
        this.A = a0;
        this.debugMode = false;
        java.util.HashMap a4 = new java.util.HashMap();
        this.markMap = a4;
        this.offsetIntoA = 0;
    }
    
    public void reInitialise(Object a)
    {
        int[] dummy = (int[])a;
        int[] a0 = (int[])a;
        this.data = a0;
        this.initialise(true);
        this.setPosition("");
        this.segmentSize = -1;
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
        this.setOffsetIntoA(0);
        this.B = null;
        int i4 = 0;
        while(true)
        {
            String[] a7 = this.arrays;
            int i5 = a7.length;
            if(i4 >= i5)
            {
                this.markCompareMap = -1;
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
                MergeBUSort.BACKGROUND = a0;
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
                MergeBUSort.TEXT_COLOR = a1;
                break label1;
            }
            String s2 = MergeBUSort.PARTITION;
            int i1 = s.equalsIgnoreCase(s2)?1:0;
            label3: {
                if(i1 == 0)
                {
                    break label3;
                }
                java.awt.Color a2 = a.getColor();
                MergeBUSort.PARTITION_COLOR = a2;
                break label1;
            }
            String s3 = MergeBUSort.ACTIVE;
            int i2 = s.equalsIgnoreCase(s3)?1:0;
            label4: {
                if(i2 == 0)
                {
                    break label4;
                }
                java.awt.Color a3 = a.getColor();
                MergeBUSort.ACTIVE_COLOR = a3;
                break label1;
            }
            String s4 = MergeBUSort.PROCESSED;
            int i3 = s.equalsIgnoreCase(s4)?1:0;
            label5: {
                if(i3 == 0)
                {
                    break label5;
                }
                java.awt.Color a4 = a.getColor();
                MergeBUSort.DOING_CHILDREN_COLOR = a4;
                break label1;
            }
            String s5 = MergeBUSort.FINISHED;
            int i4 = s.equalsIgnoreCase(s5)?1:0;
            label6: {
                if(i4 == 0)
                {
                    break label6;
                }
                java.awt.Color a5 = a.getColor();
                MergeBUSort.FINISHED_COLOR = a5;
                break label1;
            }
            String s6 = MergeBUSort.WAITING;
            int i5 = s.equalsIgnoreCase(s6)?1:0;
            label7: {
                if(i5 == 0)
                {
                    break label7;
                }
                java.awt.Color a6 = a.getColor();
                MergeBUSort.WAITING_COLOR = a6;
                break label1;
            }
            String s7 = MergeBUSort.highlightLabel;
            int i6 = s.equalsIgnoreCase(s7)?1:0;
            label8: {
                if(i6 == 0)
                {
                    break label8;
                }
                java.awt.Color a7 = a.getColor();
                MergeBUSort.HIGHLIGHT_COLOR = a7;
                break label1;
            }
            String s8 = MergeBUSort.selectionLabel;
            int i7 = s.equalsIgnoreCase(s8)?1:0;
            if(i7 != 0)
            {
                java.awt.Color a8 = a.getColor();
                MergeBUSort.selectionColor = a8;
            }
        }
    }
    
    public String getClassName()
    {
        return "MergeBUSort";
    }
    
    protected void addHighlight(int i)
    {
        java.util.Hashtable a = MergeBUSort.highlightedElements;
        Integer a0 = new Integer(i);
        Boolean a1 = new Boolean(true);
        Object a2 = a.put((Object)a0, (Object)a1);
    }
    
    protected boolean isHighlighted(int i)
    {
        java.util.Hashtable a = MergeBUSort.highlightedElements;
        Integer a0 = new Integer(i);
        Object a1 = a.get((Object)a0);
        int i0 = a1 == null?0:1;
        return i0 != 0;
    }
    
    protected void removeAllHighlight()
    {
        java.util.Hashtable a = MergeBUSort.highlightedElements;
        a.clear();
    }
    
    protected void removeHighlight(int i)
    {
        java.util.Hashtable a = MergeBUSort.highlightedElements;
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
    
    public void display()
    {
        java.io.PrintStream a = System.out;
        StringBuilder a0 = new StringBuilder();
        StringBuilder a1 = a0.append("left: ");
        int i = this.left;
        StringBuilder a2 = a1.append(i);
        StringBuilder a3 = a2.append(" right: ");
        int i0 = this.right;
        StringBuilder a4 = a3.append(i0);
        StringBuilder a5 = a4.append(" || ");
        String s = a5.toString();
        a.print(s);
        int i1 = 0;
        while(true)
        {
            int[] a6 = this.data;
            int i2 = a6.length;
            if(i1 >= i2)
            {
                java.io.PrintStream a7 = System.out;
                a7.println("");
                return;
            }
            else
            {
                java.io.PrintStream a8 = System.out;
                StringBuilder a9 = new StringBuilder();
                int[] a10 = this.data;
                int i3 = a10[i1];
                StringBuilder a11 = a9.append(i3);
                StringBuilder a12 = a11.append(" ");
                String s0 = a12.toString();
                a8.print(s0);
                int i4 = i1 + 1;
                i1 = i4;
            }
        }
    }
    
    public int[] getData()
    {
        int[] a = this.data;
        return a;
    }
    
    public boolean isActive()
    {
        int i = 0;
        int i0 = this.isFinished()?1:0;
        if(i0 == 0)
        {
            int i1 = this.active?1:0;
            i = i1;
        }
        else
        {
            i = 0;
        }
        return i != 0;
    }
    
    public boolean isDoingChildren()
    {
        int i = 0;
        int i0 = this.isActive()?1:0;
        if(i0 == 0)
        {
            i = 0;
        }
        else
        {
            int i1 = this.doingChildren?1:0;
            i = i1;
        }
        return i != 0;
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
        MergeBUSort.needtoresetSeed = true;
    }
    
    public boolean isExpanded(String s)
    {
        com.cim.AIA.AlgorithmThread a = this.algorithmThread;
        MergeBUSortThread dummy = (MergeBUSortThread)a;
        MergeBUSortThread a0 = (MergeBUSortThread)a;
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
        int i = MergeBUSort.needtoresetSeed?1:0;
        if(i != 0)
        {
            java.util.Random a = this.random;
            a.setSeed(12345678L);
            MergeBUSort.needtoresetSeed = false;
        }
        this.runMergeBUSort(true);
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
            java.awt.Color a1 = MergeBUSort.ACTIVE_COLOR;
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
    
    public void setCompareMark(String s, int i, java.awt.Color a, boolean b)
    {
        label1: {
            label0: {
                if(b)
                {
                    break label0;
                }
                break label1;
            }
            label2: {
                if(s != "B")
                {
                    break label2;
                }
                this.markCompareMapB = i;
                this.markCompareColorB = a;
                break label1;
            }
            label3: {
                if(s != "C")
                {
                    break label3;
                }
                this.markCompareMapC = i;
                this.markCompareColorC = a;
                break label1;
            }
            if(s == "A")
            {
                this.markCompareMap = i;
                this.markCompareColor = a;
            }
        }
    }
    
    public Integer getCompareMark(String s)
    {
        Integer a = null;
        label1: {
            label0: {
                if(s != "B")
                {
                    break label0;
                }
                int i = this.markCompareMapB;
                Integer a0 = new Integer(i);
                a = a0;
                break label1;
            }
            label2: {
                if(s != "C")
                {
                    break label2;
                }
                int i0 = this.markCompareMapC;
                Integer a1 = new Integer(i0);
                a = a1;
                break label1;
            }
            if(s != "A")
            {
                Integer a2 = new Integer(-1);
                a = a2;
            }
            else
            {
                int i1 = this.markCompareMap;
                Integer a3 = new Integer(i1);
                a = a3;
            }
        }
        return a;
    }
    
    public void setCompareMark(int i, java.awt.Color a, boolean b)
    {
        if(b)
        {
            this.setCompareMark(i, a);
        }
    }
    
    public void clearCompareMark(String s)
    {
        java.awt.Color a = java.awt.Color.CYAN;
        this.setCompareMark(s, -1, a, true);
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
    
    public Integer[] subArray(Integer[] a, int i, int i0)
    {
        int i1 = a.length;
        int i2 = i1 - i;
        int i3 = Math.min(i0, i2);
        Integer[] a0 = new Integer[i3];
        int i4 = 0;
        while(true)
        {
            int i5 = a0.length;
            if(i4 >= i5)
            {
                return a0;
            }
            else
            {
                int i6 = i4 + i;
                Integer a1 = a[i6];
                a0[i4] = a1;
                int i7 = i4 + 1;
                i4 = i7;
            }
        }
    }
    
    public void runMergeBUSort(boolean b)
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
            this.setPosition("2");
            this.setPosition("3");
            int i1 = 1;
            while(true)
            {
                Integer[] a = this.A;
                int i2 = a.length;
                if(i1 >= i2)
                {
                    break;
                }
                this.segmentSize = i1;
                this.setPosition("4");
                this.setPosition("5");
                int i3 = 0;
                while(true)
                {
                    Integer[] a0 = this.A;
                    int i4 = a0.length;
                    if(i3 >= i4)
                    {
                        this.setPosition("last");
                        int i5 = i1 * 2;
                        i1 = i5;
                    }
                    else
                    {
                        this.setOffsetIntoA(i3);
                        this.setPosition("6");
                        Integer[] a1 = this.A;
                        Integer[] a2 = this.subArray(a1, i3, i1);
                        this.B = a2;
                        this.setPosition("6.1");
                        Integer[] a3 = this.A;
                        int i6 = i3 + i1;
                        Integer[] a4 = this.subArray(a3, i6, i1);
                        this.C = a4;
                        this.setPosition("7");
                        this.mergeBU(i != 0);
                        this.B = null;
                        this.C = null;
                        int i7 = 2 * i1;
                        int i8 = i3 + i7;
                        i3 = i8;
                    }
                }
            }
        }
    }
    
    protected void makeQuestion()
    {
    }
    
    protected void removeAllQuestions()
    {
    }
    
    protected boolean hasQuestions()
    {
        return false;
    }
    
    public java.util.Vector generateQuestions()
    {
        java.util.Vector a = new java.util.Vector();
        return a;
    }
    
    public com.cim.AIA.MultipleTween generateTweens(com.cim.AIA.Paintable a, Object a0, int i)
    {
        return null;
    }
    
    protected void generateTweens(com.cim.AIA.MultipleTween a, MyElementArray a0, int i)
    {
    }
    
    protected java.awt.Color getColor()
    {
        java.awt.Color a = null;
        int i = this.isFinished()?1:0;
        label1: {
            label0: {
                if(i == 0)
                {
                    break label0;
                }
                java.awt.Color a0 = MergeBUSort.FINISHED_COLOR;
                a = a0;
                break label1;
            }
            int i0 = this.isDoingChildren()?1:0;
            label2: {
                if(i0 == 0)
                {
                    break label2;
                }
                java.awt.Color a1 = MergeBUSort.DOING_CHILDREN_COLOR;
                a = a1;
                break label1;
            }
            int i1 = this.isActive()?1:0;
            if(i1 == 0)
            {
                java.awt.Color a2 = MergeBUSort.WAITING_COLOR;
                a = a2;
            }
            else
            {
                java.awt.Color a3 = MergeBUSort.ACTIVE_COLOR;
                a = a3;
            }
        }
        return a;
    }
    
    protected void calculateColors(java.awt.Color[] a, int[] a0, int i)
    {
    }
    
    public MergeBUSort getActive()
    {
        return null;
    }
    
    protected int getDepth(int i)
    {
        return 1;
    }
    
    public int getHeight(java.awt.Graphics a)
    {
        return 1;
    }
    
    protected void draw(java.awt.Graphics a, java.awt.Point a0)
    {
    }
    
    public void drawInProgress(java.awt.Point a, java.awt.Graphics a0, Integer[] a1, String s)
    {
        java.awt.Color a2 = null;
        int i = a.x;
        int i0 = a.y;
        MyElementArray a3 = new MyElementArray(i, i0);
        int i1 = this.columSpacing;
        a3.setColumGap(i1);
        int i2 = this.columWidth;
        a3.setElementWidth(i2);
        int i3 = a1.length;
        java.awt.Color[] a4 = new java.awt.Color[i3];
        int i4 = a1.length;
        int[] a5 = new int[i4];
        this.calculateColors(a4, a5, 0);
        int i5 = 0;
        while(true)
        {
            Integer a6 = null;
            int i6 = 0;
            int i7 = a1.length;
            if(i5 >= i7)
            {
                break;
            }
            Integer a7 = new Integer(0);
            Integer a8 = a1[i5];
            if(a8 == null)
            {
                a6 = a7;
                i6 = 1;
            }
            else
            {
                Integer a9 = a1[i5];
                a6 = a9;
                i6 = 0;
            }
            java.awt.Color a10 = this.getCurrentTint();
            java.awt.Point a11 = new java.awt.Point(0, 0);
            MyVertBar a12 = new MyVertBar(i5, a6, a10, a11);
            a12.setIsEmpty(i6 != 0);
            java.awt.Color a13 = MergeBUSort.TEXT_COLOR;
            a12.setTextColor(a13);
            java.awt.Color a14 = MergeBUSort.selectionColor;
            a12.setHighlightColor(a14);
            a3.setValue(i5, (com.cim.AIA.Element)a12);
            com.cim.AIA.Element a15 = a3.getElement(i5);
            com.cim.AIA.VerticalBar dummy = (com.cim.AIA.VerticalBar)a15;
            com.cim.AIA.VerticalBar a16 = (com.cim.AIA.VerticalBar)a15;
            a16.setMarkersAboveValue(false);
            com.cim.AIA.Element a17 = a3.getElement(i5);
            com.cim.AIA.VerticalBar dummy0 = (com.cim.AIA.VerticalBar)a17;
            com.cim.AIA.VerticalBar a18 = (com.cim.AIA.VerticalBar)a17;
            a18.setOffsetForValue(1);
            int i8 = i5 + 1;
            i5 = i8;
        }
        java.awt.Rectangle a19 = a0.getClipBounds();
        int i9 = this.segmentSize;
        label0: {
            if(i9 <= 0)
            {
                break label0;
            }
            int i10 = 0;
            while(true)
            {
                java.awt.Color a20 = null;
                int i11 = a1.length;
                if(i10 >= i11)
                {
                    break;
                }
                if(s != "B")
                {
                    java.awt.Color a21 = MergeBUSort.HONEY_DEW;
                    a20 = a21;
                }
                else
                {
                    java.awt.Color a22 = MergeBUSort.CANTALOUPE;
                    a20 = a22;
                }
                a3.setElemTintMark(i10, a20);
                int i12 = i10 + 1;
                i10 = i12;
            }
        }
        int i13 = a19.width;
        int i14 = a3.getWidth();
        int i15 = i13 - i14;
        int i16 = i15 / 2;
        int i17 = a.y;
        a3.setLocation(i16, i17);
        Integer a23 = this.getMark(s);
        label3: {
            label2: {
                label1: {
                    if(a23 == null)
                    {
                        break label1;
                    }
                    int i18 = a23.intValue();
                    if(i18 != -1)
                    {
                        break label2;
                    }
                }
                a3.clearMarks();
                break label3;
            }
            int i19 = a23.intValue();
            a3.setMark("", i19);
        }
        if(s != "B")
        {
            java.awt.Color a24 = this.markCompareColorC;
            a2 = a24;
        }
        else
        {
            java.awt.Color a25 = this.markCompareColorB;
            a2 = a25;
        }
        Integer a26 = this.getCompareMark(s);
        int i20 = a26.intValue();
        if(i20 == -1)
        {
            a3.clearCompareMarks();
        }
        else
        {
            Integer a27 = this.getCompareMark(s);
            int i21 = a27.intValue();
            a3.setCompareMark(i21, a2);
        }
        a3.draw(a0);
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
        Integer[] a2 = this.A;
        int i3 = a2.length;
        java.awt.Color[] a3 = new java.awt.Color[i3];
        Integer[] a4 = this.A;
        int i4 = a4.length;
        int[] a5 = new int[i4];
        this.calculateColors(a3, a5, 0);
        int i5 = 0;
        while(true)
        {
            Integer[] a6 = this.A;
            int i6 = a6.length;
            if(i5 >= i6)
            {
                break;
            }
            else
            {
                Integer[] a7 = this.A;
                Integer a8 = a7[i5];
                java.awt.Color a9 = this.getCurrentTint();
                java.awt.Point a10 = new java.awt.Point(0, 0);
                MyVertBar a11 = new MyVertBar(i5, a8, a9, a10);
                java.awt.Color a12 = MergeBUSort.TEXT_COLOR;
                a11.setTextColor(a12);
                java.awt.Color a13 = MergeBUSort.selectionColor;
                a11.setHighlightColor(a13);
                a1.setValue(i5, (com.cim.AIA.Element)a11);
                com.cim.AIA.Element a14 = a1.getElement(i5);
                com.cim.AIA.VerticalBar dummy = (com.cim.AIA.VerticalBar)a14;
                com.cim.AIA.VerticalBar a15 = (com.cim.AIA.VerticalBar)a14;
                a15.setMarkersAboveValue(false);
                com.cim.AIA.Element a16 = a1.getElement(i5);
                com.cim.AIA.VerticalBar dummy0 = (com.cim.AIA.VerticalBar)a16;
                com.cim.AIA.VerticalBar a17 = (com.cim.AIA.VerticalBar)a16;
                a17.setOffsetForValue(1);
                int i7 = i5 + 1;
                i5 = i7;
            }
        }
        int i8 = MergeBUSort.maxHeight;
        if(i8 < 0)
        {
            int i9 = a1.getHeight();
            MergeBUSort.maxHeight = i9;
        }
        int i10 = MergeBUSort.maxWidth;
        if(i10 < 0)
        {
            int i11 = a1.getWidth();
            MergeBUSort.maxWidth = i11;
        }
        int i12 = MergeBUSort.maxHeight;
        int i13 = i12 + 30;
        int i14 = i13 + 25;
        int i15 = a1.getWidth();
        java.awt.Rectangle a18 = a0.getClipBounds();
        int i16 = this.segmentSize;
        label0: {
            if(i16 <= 0)
            {
                break label0;
            }
            int i17 = this.offsetIntoA;
            int i18 = this.offsetIntoA;
            int i19 = this.segmentSize;
            int i20 = 2 * i19;
            int i21 = i18 + i20;
            int i22 = i21 - 1;
            a1.setInsetRectangle(i17, i22);
            int i23 = 0;
            while(true)
            {
                int i24 = this.segmentSize;
                if(i23 >= i24)
                {
                    break;
                }
                else
                {
                    int i25 = this.offsetIntoA;
                    int i26 = i23 + i25;
                    java.awt.Color a19 = MergeBUSort.HONEY_DEW;
                    a1.setElemTintMark(i26, a19);
                    int i27 = i23 + 1;
                    i23 = i27;
                }
            }
            int i28 = this.segmentSize;
            Integer[] a20 = this.A;
            int i29 = a20.length;
            int i30 = this.offsetIntoA;
            int i31 = i29 - i30;
            int i32 = Math.min(i28, i31);
            int i33 = 0;
            while(true)
            {
                if(i33 >= i32)
                {
                    break;
                }
                else
                {
                    int i34 = this.offsetIntoA;
                    int i35 = i33 + i34;
                    java.awt.Color a21 = MergeBUSort.CANTALOUPE;
                    a1.setElemTintMark(i35, a21);
                    int i36 = i33 + 1;
                    i33 = i36;
                }
            }
        }
        int i37 = a18.width;
        int i38 = a1.getWidth();
        int i39 = i37 - i38;
        int i40 = i39 / 2;
        int i41 = a.y;
        a1.setLocation(i40, i41);
        Integer a22 = this.getMark("A");
        label3: {
            label2: {
                label1: {
                    if(a22 == null)
                    {
                        break label1;
                    }
                    int i42 = a22.intValue();
                    if(i42 != -1)
                    {
                        break label2;
                    }
                }
                a1.clearMarks();
                break label3;
            }
            int i43 = a22.intValue();
            a1.setMark("", i43);
        }
        Integer a23 = this.getCompareMark();
        int i44 = a23.intValue();
        if(i44 == -1)
        {
            a1.clearCompareMarks();
        }
        else
        {
            Integer a24 = this.getCompareMark();
            int i45 = a24.intValue();
            java.awt.Color a25 = this.markCompareColor;
            a1.setCompareMark(i45, a25);
        }
        a1.draw(a0);
        int i46 = a18.width;
        int i47 = i46 / 2;
        int i48 = a18.height;
        int i49 = i48 - i14;
        java.awt.Graphics a26 = a0.create(0, i14, i47, i49);
        int i50 = a18.width;
        int i51 = i50 / 2;
        int i52 = a18.width;
        int i53 = i52 / 2;
        int i54 = a18.height;
        int i55 = i54 - i14;
        java.awt.Graphics a27 = a0.create(i51, i14, i53, i55);
        Integer[] a28 = this.B;
        if(a28 != null)
        {
            Integer[] a29 = this.B;
            this.drawInProgress(a, a26, a29, "B");
        }
        Integer[] a30 = this.C;
        if(a30 != null)
        {
            Integer[] a31 = this.C;
            this.drawInProgress(a, a27, a31, "C");
        }
    }
    
    static
    {
        String s = localization.Messages.getString("MergeBUSort.0");
        MergeBUSort.selectionLabel = s;
        String s0 = localization.Messages.getString("MergeBUSort.1");
        MergeBUSort.highlightLabel = s0;
        String s1 = localization.Messages.getString("MergeBUSort.2");
        MergeBUSort.ACTIVE = s1;
        String s2 = localization.Messages.getString("MergeBUSort.3");
        MergeBUSort.PROCESSED = s2;
        String s3 = localization.Messages.getString("MergeBUSort.4");
        MergeBUSort.FINISHED = s3;
        String s4 = localization.Messages.getString("MergeBUSort.5");
        MergeBUSort.WAITING = s4;
        String s5 = localization.Messages.getString("MergeBUSort.6");
        MergeBUSort.PARTITION = s5;
        java.awt.Color a = java.awt.Color.yellow;
        MergeBUSort.selectionColor = a;
        java.awt.Color a0 = java.awt.Color.white;
        MergeBUSort.BACKGROUND = a0;
        java.awt.Color a1 = java.awt.Color.black;
        MergeBUSort.TEXT_COLOR = a1;
        java.awt.Color a2 = new java.awt.Color(204, 255, 102);
        MergeBUSort.HONEY_DEW = a2;
        java.awt.Color a3 = new java.awt.Color(255, 204, 102);
        MergeBUSort.CANTALOUPE = a3;
        java.awt.Color a4 = java.awt.Color.yellow;
        MergeBUSort.PARTITION_COLOR = a4;
        java.awt.Color a5 = java.awt.Color.pink;
        MergeBUSort.WAITING_COLOR = a5;
        java.awt.Color a6 = java.awt.Color.green;
        MergeBUSort.HIGHLIGHT_COLOR = a6;
        java.awt.Color a7 = java.awt.Color.red;
        java.awt.Color a8 = a7.darker();
        MergeBUSort.DOING_CHILDREN_COLOR = a8;
        java.awt.Color a9 = java.awt.Color.MAGENTA;
        MergeBUSort.ACTIVE_COLOR = a9;
        java.awt.Color a10 = java.awt.Color.gray;
        MergeBUSort.FINISHED_COLOR = a10;
        MergeBUSort.currentPartitionMethod = 10;
        java.util.Vector a11 = new java.util.Vector();
        MergeBUSort.swapRequests = a11;
        java.util.Vector a12 = new java.util.Vector();
        MergeBUSort.questions = a12;
        java.util.Hashtable a13 = new java.util.Hashtable();
        MergeBUSort.highlightedElements = a13;
        MergeBUSort.needtoresetSeed = false;
        MergeBUSort.maxHeight = -1;
        MergeBUSort.maxWidth = -1;
    }
}