public class RadixExchangeSort extends com.cim.AIA.Algorithm implements com.cim.AIA.ColorSelectionListener {
    final protected static int ARROW_LENGTH = 30;
    protected static int currentBit;
    final protected static String MSB_LABEL;
    final protected static String LSB_LABEL;
    final protected static String BIT_LABEL;
    protected static String[] duplicateLabels;
    final public static String I_MARKER = "i";
    final public static String J_MARKER = "J";
    public static java.awt.Color WAITING_COLOR;
    public static java.awt.Color HIGHLIGHT_COLOR;
    public static java.awt.Color DOING_CHILDREN_COLOR;
    final protected static String ACTIVE;
    final protected static String PROCESSED;
    final protected static String FINISHED;
    final protected static String WAITING;
    public static java.awt.Color ACTIVE_COLOR;
    public static java.awt.Color FINISHED_COLOR;
    public static java.awt.Color BACKGROUND;
    public static java.awt.Color TEXT_COLOR;
    final protected static boolean DEBUG_MODE = 0;
    final public static int MARKER_NOT_READY = -10;
    protected int columSpacing;
    protected int columWidth;
    protected int xTextBuffer;
    protected int yTextBuffer;
    protected RadixExchangeSort leftChild;
    protected RadixExchangeSort rightChild;
    protected boolean active;
    protected boolean processed;
    protected boolean doingChildren;
    protected int[] data;
    protected int left;
    protected int right;
    protected int bit;
    protected int bitLength;
    protected int i;
    protected int j;
    protected java.util.Vector swapRequests;
    
    protected RadixExchangeSort(com.cim.AIA.AlgorithmThread a, int[] a0, boolean b)
    {
        super(a, (Object)a0);
        int i = b?1:0;
        this.columSpacing = 10;
        this.columWidth = 10;
        this.xTextBuffer = 20;
        this.yTextBuffer = 2;
        this.leftChild = null;
        this.rightChild = null;
        this.active = false;
        this.processed = false;
        this.doingChildren = false;
        this.i = -10;
        this.j = -10;
        java.util.Vector a1 = new java.util.Vector();
        this.swapRequests = a1;
        this.data = a0;
        this.initialise(i != 0);
    }
    
    public RadixExchangeSort(com.cim.AIA.AlgorithmThread a, Object a0)
    {
        super(a, a0);
        this.columSpacing = 10;
        this.columWidth = 10;
        this.xTextBuffer = 20;
        this.yTextBuffer = 2;
        this.leftChild = null;
        this.rightChild = null;
        this.active = false;
        this.processed = false;
        this.doingChildren = false;
        this.i = -10;
        this.j = -10;
        java.util.Vector a1 = new java.util.Vector();
        this.swapRequests = a1;
        int[] dummy = (int[])a0;
        int[] a2 = (int[])a0;
        this.data = a2;
        this.initialise(true);
        com.cim.AIA.ConfigurationManager a3 = com.cim.AIA.ConfigurationManager.getConfigurationManager();
        java.awt.Color a4 = RadixExchangeSort.ACTIVE_COLOR;
        String s = RadixExchangeSort.ACTIVE;
        com.cim.AIA.ColorSelection a5 = new com.cim.AIA.ColorSelection(a4, s);
        a5.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        a3.addColorSelection(a5);
        java.awt.Color a6 = RadixExchangeSort.DOING_CHILDREN_COLOR;
        String s0 = RadixExchangeSort.PROCESSED;
        com.cim.AIA.ColorSelection a7 = new com.cim.AIA.ColorSelection(a6, s0);
        a7.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        a3.addColorSelection(a7);
        java.awt.Color a8 = RadixExchangeSort.FINISHED_COLOR;
        String s1 = RadixExchangeSort.FINISHED;
        com.cim.AIA.ColorSelection a9 = new com.cim.AIA.ColorSelection(a8, s1);
        a9.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        a3.addColorSelection(a9);
        java.awt.Color a10 = RadixExchangeSort.WAITING_COLOR;
        String s2 = RadixExchangeSort.WAITING;
        com.cim.AIA.ColorSelection a11 = new com.cim.AIA.ColorSelection(a10, s2);
        a11.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        a3.addColorSelection(a11);
        a3.addColorSelectionListener((com.cim.AIA.ColorSelectionListener)this);
    }
    
    protected void calculateColors(java.awt.Color[] a, int[] a0, int i)
    {
        int i0 = this.left;
        int i1 = i0;
        while(true)
        {
            int i2 = this.right;
            if(i1 > i2)
            {
                break;
            }
            label0: {
                if(i1 < 0)
                {
                    break label0;
                }
                int i3 = a.length;
                if(i1 >= i3)
                {
                    break label0;
                }
                java.awt.Color a1 = a[i1];
                label1: {
                    if(a1 == null)
                    {
                        break label1;
                    }
                    int i4 = a0.length;
                    if(i1 >= i4)
                    {
                        break label0;
                    }
                    int i5 = a0[i1];
                    if(i <= i5)
                    {
                        break label0;
                    }
                    else
                    {
                        java.awt.Color a2 = this.getColor();
                        a[i1] = a2;
                        a0[i1] = i;
                        break label0;
                    }
                }
                java.awt.Color a3 = this.getColor();
                a[i1] = a3;
                int i6 = a0.length;
                if(i1 < i6)
                {
                    a0[i1] = i;
                }
            }
            int i7 = i1 + 1;
            i1 = i7;
        }
        RadixExchangeSort a4 = this.leftChild;
        if(a4 != null)
        {
            RadixExchangeSort a5 = this.leftChild;
            int i8 = i + 1;
            a5.calculateColors(a, a0, i8);
        }
        RadixExchangeSort a6 = this.rightChild;
        if(a6 != null)
        {
            RadixExchangeSort a7 = this.rightChild;
            int i9 = i + 1;
            a7.calculateColors(a, a0, i9);
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
                RadixExchangeSort.duplicateLabels = a1;
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
    
    public void display()
    {
        java.io.PrintStream a = System.out;
        StringBuilder a0 = new StringBuilder();
        String s = localization.Messages.getString("RadixExchangeSort.10");
        StringBuilder a1 = a0.append(s);
        int i = this.left;
        StringBuilder a2 = a1.append(i);
        String s0 = localization.Messages.getString("RadixExchangeSort.11");
        StringBuilder a3 = a2.append(s0);
        int i0 = this.right;
        StringBuilder a4 = a3.append(i0);
        StringBuilder a5 = a4.append(" || ");
        String s1 = a5.toString();
        a.print(s1);
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
                String s2 = a12.toString();
                a8.print(s2);
                int i4 = i1 + 1;
                i1 = i4;
            }
        }
    }
    
    public void draw(java.awt.Graphics a, java.awt.Point a0)
    {
        int i = a0.x;
        java.awt.FontMetrics a1 = a.getFontMetrics();
        String s = RadixExchangeSort.MSB_LABEL;
        int i0 = a1.stringWidth(s);
        int i1 = i + i0;
        int i2 = this.xTextBuffer;
        int i3 = i1 + i2;
        int i4 = a0.y;
        java.awt.Point a2 = new java.awt.Point(i3, i4);
        int i5 = a2.x;
        int i6 = a2.y;
        int i7 = this.yTextBuffer;
        int i8 = i6 + i7;
        int i9 = this.bitLength;
        java.awt.FontMetrics a3 = a.getFontMetrics();
        int i10 = a3.getHeight();
        int i11 = this.yTextBuffer;
        int i12 = i10 + i11;
        int i13 = i9 * i12;
        int i14 = i8 + i13;
        com.cim.AIA.ElementArray a4 = new com.cim.AIA.ElementArray(i5, i14);
        int i15 = 0;
        while(true)
        {
            int[] a5 = this.data;
            int i16 = a5.length;
            if(i15 >= i16)
            {
                break;
            }
            else
            {
                java.awt.Color a6 = RadixExchangeSort.BACKGROUND;
                java.awt.Color a7 = RadixExchangeSort.BACKGROUND;
                int[] a8 = this.data;
                int i17 = a8[i15];
                java.awt.Point a9 = new java.awt.Point(0, 0);
                int i18 = this.yTextBuffer;
                int i19 = this.bitLength;
                com.cim.AIA.BinaryElement a10 = new com.cim.AIA.BinaryElement(i15, a6, a7, i17, a9, i18, i19);
                java.awt.Color a11 = RadixExchangeSort.TEXT_COLOR;
                a10.setTextColor(a11);
                a4.setValue(i15, (com.cim.AIA.Element)a10);
                int i20 = i15 + 1;
                i15 = i20;
            }
        }
        a4.draw(a);
        java.awt.Color a12 = RadixExchangeSort.TEXT_COLOR;
        a.setColor(a12);
        int i21 = 0;
        while(true)
        {
            int i22 = this.bitLength;
            if(i21 >= i22)
            {
                break;
            }
            else
            {
                StringBuilder a13 = new StringBuilder();
                StringBuilder a14 = a13.append("");
                StringBuilder a15 = a14.append(i21);
                String s0 = a15.toString();
                int i23 = this.getRight();
                int i24 = i23 + 1;
                int i25 = this.columWidth;
                int i26 = this.columSpacing;
                int i27 = i25 + i26;
                int i28 = i24 * i27;
                int i29 = a2.x;
                int i30 = i28 + i29;
                int i31 = this.xTextBuffer;
                int i32 = i30 + i31;
                java.awt.FontMetrics a16 = a.getFontMetrics();
                StringBuilder a17 = new StringBuilder();
                StringBuilder a18 = a17.append("");
                StringBuilder a19 = a18.append(i21);
                String s1 = a19.toString();
                int i33 = a16.stringWidth(s1);
                int i34 = i33 / 2;
                int i35 = i32 - i34;
                int i36 = a2.y;
                int i37 = this.bitLength;
                int i38 = i37 - i21;
                java.awt.FontMetrics a20 = a.getFontMetrics();
                int i39 = a20.getHeight();
                int i40 = this.yTextBuffer;
                int i41 = i39 + i40;
                int i42 = i38 * i41;
                int i43 = i36 + i42;
                a.drawString(s0, i35, i43);
                int i44 = i21 + 1;
                i21 = i44;
            }
        }
        String s2 = RadixExchangeSort.MSB_LABEL;
        int i45 = a2.x;
        java.awt.FontMetrics a21 = a.getFontMetrics();
        String s3 = RadixExchangeSort.MSB_LABEL;
        int i46 = a21.stringWidth(s3);
        int i47 = i45 - i46;
        int i48 = this.xTextBuffer;
        int i49 = i47 - i48;
        int i50 = a2.y;
        java.awt.FontMetrics a22 = a.getFontMetrics();
        int i51 = a22.getHeight();
        int i52 = this.yTextBuffer;
        int i53 = i51 + i52;
        int i54 = i50 + i53;
        a.drawString(s2, i49, i54);
        String s4 = RadixExchangeSort.LSB_LABEL;
        int i55 = a2.x;
        java.awt.FontMetrics a23 = a.getFontMetrics();
        String s5 = RadixExchangeSort.LSB_LABEL;
        int i56 = a23.stringWidth(s5);
        int i57 = i55 - i56;
        int i58 = this.xTextBuffer;
        int i59 = i57 - i58;
        int i60 = a2.y;
        int i61 = this.bitLength;
        java.awt.FontMetrics a24 = a.getFontMetrics();
        int i62 = a24.getHeight();
        int i63 = this.yTextBuffer;
        int i64 = i62 + i63;
        int i65 = i61 * i64;
        int i66 = i60 + i65;
        a.drawString(s4, i59, i66);
        String s6 = RadixExchangeSort.BIT_LABEL;
        int i67 = this.getRight();
        int i68 = i67 + 1;
        int i69 = this.columWidth;
        int i70 = this.columSpacing;
        int i71 = i69 + i70;
        int i72 = i68 * i71;
        int i73 = a2.x;
        int i74 = i72 + i73;
        int i75 = this.xTextBuffer;
        int i76 = i74 + i75;
        java.awt.FontMetrics a25 = a.getFontMetrics();
        String s7 = RadixExchangeSort.BIT_LABEL;
        int i77 = a25.stringWidth(s7);
        int i78 = i77 / 2;
        int i79 = i76 - i78;
        int i80 = a2.y;
        a.drawString(s6, i79, i80);
        int i81 = RadixExchangeSort.currentBit;
        if(i81 >= 0)
        {
            int[] a26 = this.data;
            int i82 = a26.length;
            int i83 = this.columWidth;
            int i84 = this.columSpacing;
            int i85 = i83 + i84;
            int i86 = i82 * i85;
            int i87 = a2.x;
            int i88 = i86 + i87;
            int i89 = this.xTextBuffer;
            int i90 = i88 + i89;
            int i91 = this.columWidth;
            int i92 = this.columSpacing;
            int i93 = i91 + i92;
            int i94 = i90 + i93;
            int i95 = i94 + 30;
            int i96 = a2.y;
            int i97 = this.bitLength;
            int i98 = RadixExchangeSort.currentBit;
            int i99 = i97 - i98;
            java.awt.FontMetrics a27 = a.getFontMetrics();
            int i100 = a27.getHeight();
            int i101 = this.yTextBuffer;
            int i102 = i100 + i101;
            int i103 = i99 * i102;
            int i104 = i96 + i103;
            java.awt.FontMetrics a28 = a.getFontMetrics();
            int i105 = a28.getHeight();
            int i106 = i105 / 2;
            int i107 = i104 - i106;
            int[] a29 = this.data;
            int i108 = a29.length;
            int i109 = this.columWidth;
            int i110 = this.columSpacing;
            int i111 = i109 + i110;
            int i112 = i108 * i111;
            int i113 = a2.x;
            int i114 = i112 + i113;
            int i115 = this.xTextBuffer;
            int i116 = i114 + i115;
            int i117 = this.columWidth;
            int i118 = this.columSpacing;
            int i119 = i117 + i118;
            int i120 = i116 + i119;
            int i121 = a2.y;
            int i122 = this.bitLength;
            int i123 = RadixExchangeSort.currentBit;
            int i124 = i122 - i123;
            java.awt.FontMetrics a30 = a.getFontMetrics();
            int i125 = a30.getHeight();
            int i126 = this.yTextBuffer;
            int i127 = i125 + i126;
            int i128 = i124 * i127;
            int i129 = i121 + i128;
            java.awt.FontMetrics a31 = a.getFontMetrics();
            int i130 = a31.getHeight();
            int i131 = i130 / 2;
            int i132 = i129 - i131;
            com.cim.AIA.Line a32 = new com.cim.AIA.Line(i95, i107, i120, i132);
            a32.showArrowHead(true);
            a32.setDistanceFromStartForArrowHead(-3);
            a32.setArrowHeadWidth(5);
            java.awt.Color a33 = java.awt.Color.black;
            a.setColor(a33);
            a32.draw(a);
        }
        int i133 = a2.x;
        int i134 = a2.y;
        java.awt.Point a34 = new java.awt.Point(i133, i134);
        int i135 = a34.y;
        int i136 = this.bitLength;
        int i137 = i136 + 2;
        java.awt.FontMetrics a35 = a.getFontMetrics();
        int i138 = a35.getHeight();
        int i139 = this.yTextBuffer;
        int i140 = i138 + i139;
        int i141 = i137 * i140;
        int i142 = i135 + i141;
        a34.y = i142;
        this.draw(a, a34, a2);
    }
    
    protected void draw(java.awt.Graphics a, java.awt.Point a0, java.awt.Point a1)
    {
        java.awt.Color a2 = this.getColor();
        a.setColor(a2);
        int i = this.getLeft();
        int i0 = this.columWidth;
        int i1 = this.columSpacing;
        int i2 = i0 + i1;
        int i3 = i * i2;
        int i4 = a0.x;
        int i5 = i3 + i4;
        int i6 = this.columWidth;
        int i7 = i6 / 2;
        int i8 = i5 - i7;
        int i9 = a0.y;
        java.awt.FontMetrics a3 = a.getFontMetrics();
        int i10 = a3.getHeight();
        int i11 = i9 - i10;
        int i12 = this.getRight();
        int i13 = 1 + i12;
        int i14 = this.getLeft();
        int i15 = i13 - i14;
        int i16 = this.columWidth;
        int i17 = this.columSpacing;
        int i18 = i16 + i17;
        int i19 = i15 * i18;
        java.awt.FontMetrics a4 = a.getFontMetrics();
        int i20 = a4.getHeight();
        a.fillRect(i8, i11, i19, i20);
        java.awt.Color a5 = RadixExchangeSort.TEXT_COLOR;
        a.setColor(a5);
        int i21 = this.getLeft();
        int i22 = i21;
        while(true)
        {
            int i23 = this.getRight();
            if(i22 > i23)
            {
                break;
            }
            else
            {
                StringBuilder a6 = new StringBuilder();
                int[] a7 = this.data;
                int i24 = a7[i22];
                StringBuilder a8 = a6.append(i24);
                StringBuilder a9 = a8.append("");
                String s = a9.toString();
                int i25 = this.columWidth;
                int i26 = this.columSpacing;
                int i27 = i25 + i26;
                int i28 = i22 * i27;
                int i29 = a0.x;
                int i30 = i28 + i29;
                int i31 = this.columWidth;
                int i32 = i31 / 2;
                int i33 = i30 + i32;
                java.awt.FontMetrics a10 = a.getFontMetrics();
                StringBuilder a11 = new StringBuilder();
                int[] a12 = this.data;
                int i34 = a12[i22];
                StringBuilder a13 = a11.append(i34);
                StringBuilder a14 = a13.append("");
                String s0 = a14.toString();
                int i35 = a10.stringWidth(s0);
                int i36 = i35 / 2;
                int i37 = i33 - i36;
                int i38 = a0.y;
                a.drawString(s, i37, i38);
                int i39 = i22 + 1;
                i22 = i39;
            }
        }
        int i40 = this.isActive()?1:0;
        label0: {
            if(i40 == 0)
            {
                break label0;
            }
            int i41 = this.isDoingChildren()?1:0;
            if(i41 != 0)
            {
                break label0;
            }
            int i42 = this.isFinished()?1:0;
            if(i42 != 0)
            {
                break label0;
            }
            int i43 = this.getI();
            if(i43 != -10)
            {
                int i44 = this.getI();
                int i45 = this.columWidth;
                int i46 = this.columSpacing;
                int i47 = i45 + i46;
                int i48 = i44 * i47;
                int i49 = a0.x;
                int i50 = i48 + i49;
                int i51 = this.columWidth;
                int i52 = i51 / 2;
                int i53 = i50 + i52;
                int i54 = a0.y;
                java.awt.FontMetrics a15 = a.getFontMetrics();
                int i55 = a15.getHeight();
                int i56 = i54 + i55;
                int i57 = this.yTextBuffer;
                int i58 = i56 + i57;
                java.awt.Point a16 = new java.awt.Point(i53, i58);
                java.awt.Color a17 = RadixExchangeSort.TEXT_COLOR;
                java.awt.Color a18 = RadixExchangeSort.ACTIVE_COLOR;
                com.cim.AIA.StringMarker a19 = new com.cim.AIA.StringMarker("i", a16, a17, a18);
                a19.useDefaultWidth(false);
                int i59 = this.columWidth;
                a19.setWidth(i59);
                a19.draw(a);
            }
            int i60 = this.getJ();
            if(i60 != -10)
            {
                int i61 = this.getJ();
                int i62 = this.columWidth;
                int i63 = this.columSpacing;
                int i64 = i62 + i63;
                int i65 = i61 * i64;
                int i66 = a0.x;
                int i67 = i65 + i66;
                int i68 = this.columWidth;
                int i69 = i68 / 2;
                int i70 = i67 + i69;
                int i71 = a0.y;
                java.awt.FontMetrics a20 = a.getFontMetrics();
                int i72 = a20.getHeight();
                int i73 = this.yTextBuffer;
                int i74 = i72 + i73;
                int i75 = 2 * i74;
                int i76 = i71 + i75;
                java.awt.Point a21 = new java.awt.Point(i70, i76);
                java.awt.Color a22 = RadixExchangeSort.TEXT_COLOR;
                java.awt.Color a23 = RadixExchangeSort.ACTIVE_COLOR;
                com.cim.AIA.StringMarker a24 = new com.cim.AIA.StringMarker("J", a21, a22, a23);
                a24.useDefaultWidth(false);
                int i77 = this.columWidth;
                a24.setWidth(i77);
                a24.draw(a);
            }
            int i78 = this.getRight();
            int i79 = this.getLeft();
            if(i78 < i79)
            {
                break label0;
            }
            int i80 = this.bit;
            if(i80 >= 0)
            {
                java.awt.Color a25 = java.awt.Color.black;
                a.setColor(a25);
                int i81 = this.getLeft();
                int i82 = this.columWidth;
                int i83 = this.columSpacing;
                int i84 = i82 + i83;
                int i85 = i81 * i84;
                int i86 = a1.x;
                int i87 = i85 + i86;
                int i88 = this.columWidth;
                int i89 = i88 / 2;
                int i90 = i87 - i89;
                int i91 = a1.y;
                int i92 = this.bitLength;
                int i93 = this.bit;
                int i94 = i92 - i93;
                java.awt.FontMetrics a26 = a.getFontMetrics();
                int i95 = a26.getHeight();
                int i96 = this.yTextBuffer;
                int i97 = i95 + i96;
                int i98 = i94 * i97;
                int i99 = i91 + i98;
                java.awt.FontMetrics a27 = a.getFontMetrics();
                int i100 = a27.getHeight();
                int i101 = i99 - i100;
                int i102 = this.yTextBuffer;
                int i103 = i101 + i102;
                int i104 = this.getRight();
                int i105 = 1 + i104;
                int i106 = this.getLeft();
                int i107 = i105 - i106;
                int i108 = this.columWidth;
                int i109 = this.columSpacing;
                int i110 = i108 + i109;
                int i111 = i107 * i110;
                java.awt.FontMetrics a28 = a.getFontMetrics();
                int i112 = a28.getHeight();
                a.drawRect(i90, i103, i111, i112);
            }
        }
        RadixExchangeSort a29 = this.getLeftChild();
        if(a29 != null)
        {
            RadixExchangeSort a30 = this.getLeftChild();
            int i113 = a0.x;
            int i114 = a0.y;
            java.awt.FontMetrics a31 = a.getFontMetrics();
            int i115 = a31.getHeight();
            int i116 = 2 * i115;
            int i117 = i114 + i116;
            int i118 = this.yTextBuffer;
            int i119 = 2 * i118;
            int i120 = i117 + i119;
            java.awt.Point a32 = new java.awt.Point(i113, i120);
            a30.draw(a, a32, a1);
        }
        RadixExchangeSort a33 = this.getRightChild();
        if(a33 != null)
        {
            RadixExchangeSort a34 = this.getRightChild();
            int i121 = a0.x;
            int i122 = a0.y;
            java.awt.FontMetrics a35 = a.getFontMetrics();
            int i123 = a35.getHeight();
            int i124 = 2 * i123;
            int i125 = i122 + i124;
            int i126 = this.yTextBuffer;
            int i127 = 2 * i126;
            int i128 = i125 + i127;
            java.awt.Point a36 = new java.awt.Point(i121, i128);
            a34.draw(a, a36, a1);
        }
    }
    
    public java.util.Vector generateQuestions()
    {
        return null;
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
                break;
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
        RadixExchangeSort a13 = this.leftChild;
        if(a13 != null)
        {
            RadixExchangeSort a14 = this.leftChild;
            a14.generateTweens(a, a0, i);
        }
        RadixExchangeSort a15 = this.rightChild;
        if(a15 != null)
        {
            RadixExchangeSort a16 = this.rightChild;
            a16.generateTweens(a, a0, i);
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
    
    public RadixExchangeSort getActive()
    {
        RadixExchangeSort a = null;
        int i = this.isActive()?1:0;
        label1: {
            RadixExchangeSort a0 = null;
            RadixExchangeSort a1 = null;
            label0: {
                if(i == 0)
                {
                    break label0;
                }
                int i0 = this.isDoingChildren()?1:0;
                if(i0 != 0)
                {
                    break label0;
                }
                a = this;
                break label1;
            }
            RadixExchangeSort a2 = this.leftChild;
            RadixExchangeSort a3 = null;
            if(a2 == null)
            {
                a0 = a3;
            }
            else
            {
                RadixExchangeSort a4 = this.leftChild;
                RadixExchangeSort a5 = a4.getActive();
                a0 = a5;
            }
            label2: {
                if(a0 == null)
                {
                    break label2;
                }
                a = a0;
                break label1;
            }
            RadixExchangeSort a6 = this.rightChild;
            RadixExchangeSort a7 = null;
            if(a6 == null)
            {
                a1 = a7;
            }
            else
            {
                RadixExchangeSort a8 = this.rightChild;
                RadixExchangeSort a9 = a8.getActive();
                a1 = a9;
            }
            a = a1 == null?null:a1;
        }
        return a;
    }
    
    public int getArrayBuffer(java.awt.Graphics a)
    {
        java.awt.FontMetrics a0 = a.getFontMetrics();
        String s = RadixExchangeSort.MSB_LABEL;
        int i = a0.stringWidth(s);
        int i0 = this.xTextBuffer;
        int i1 = i + i0;
        return i1;
    }
    
    protected int getBit(int i, int i0, int i1)
    {
        int i2 = 0;
        String s = "";
        int i3 = 0;
        while(true)
        {
            if(i3 >= i1)
            {
                break;
            }
            else
            {
                StringBuilder a = new StringBuilder();
                StringBuilder a0 = a.append(s);
                StringBuilder a1 = a0.append("0");
                String s0 = a1.toString();
                int i4 = i3 + 1;
                s = s0;
                i3 = i4;
            }
        }
        String s1 = Integer.toBinaryString(i);
        StringBuilder a2 = new StringBuilder();
        int i5 = s.length();
        int i6 = s1.length();
        int i7 = i5 - i6;
        String s2 = s.substring(0, i7);
        StringBuilder a3 = a2.append(s2);
        StringBuilder a4 = a3.append(s1);
        String s3 = a4.toString();
        int i8 = s3.length();
        int i9 = i8 - 1;
        String s4 = "";
        int i10 = i9;
        while(true)
        {
            if(i10 < 0)
            {
                break;
            }
            else
            {
                StringBuilder a5 = new StringBuilder();
                StringBuilder a6 = a5.append(s4);
                int i11 = s3.charAt(i10);
                String s5 = String.valueOf((char)i11);
                StringBuilder a7 = a6.append(s5);
                String s6 = a7.toString();
                int i12 = i10 + -1;
                s4 = s6;
                i10 = i12;
            }
        }
        StringBuilder a8 = new StringBuilder();
        StringBuilder a9 = a8.append("RadixExchangeSort.get Trying to make length ");
        StringBuilder a10 = a9.append(i1);
        String s7 = localization.Messages.getString("RadixExchangeSort.19");
        StringBuilder a11 = a10.append(s7);
        String s8 = Integer.toBinaryString(i);
        StringBuilder a12 = a11.append(s8);
        String s9 = localization.Messages.getString("RadixExchangeSort.20");
        StringBuilder a13 = a12.append(s9);
        StringBuilder a14 = a13.append(i);
        String s10 = localization.Messages.getString("RadixExchangeSort.21");
        StringBuilder a15 = a14.append(s10);
        StringBuilder a16 = a15.append(s3);
        String s11 = localization.Messages.getString("RadixExchangeSort.22");
        StringBuilder a17 = a16.append(s11);
        StringBuilder a18 = a17.append(s4);
        String s12 = a18.toString();
        this.displayMessage(s12);
        label21: {
            label0: {
                NumberFormatException a19 = null;
                if(i0 < 0)
                {
                    break label0;
                }
                int i13 = s4.length();
                if(i0 >= i13)
                {
                    break label0;
                }
                int i14 = s4.charAt(i0);
                Character a20 = new Character((char)i14);
                String s13 = a20.toString();
                label2: {
                    int i15 = 0;
                    StringBuilder a21 = null;
                    String s14 = null;
                    StringBuilder a22 = null;
                    StringBuilder a23 = null;
                    String s15 = null;
                    StringBuilder a24 = null;
                    StringBuilder a25 = null;
                    String s16 = null;
                    StringBuilder a26 = null;
                    StringBuilder a27 = null;
                    String s17 = null;
                    StringBuilder a28 = null;
                    StringBuilder a29 = null;
                    String s18 = null;
                    StringBuilder a30 = null;
                    StringBuilder a31 = null;
                    String s19 = null;
                    label1: try
                    {
                        i15 = Integer.parseInt(s13);
                        break label1;
                    }
                    catch(NumberFormatException a32)
                    {
                        a19 = a32;
                        break label2;
                    }
                    label3: try
                    {
                        a21 = new StringBuilder();
                        break label3;
                    }
                    catch(NumberFormatException a33)
                    {
                        a19 = a33;
                        break label2;
                    }
                    label4: try
                    {
                        s14 = localization.Messages.getString("RadixExchangeSort.23");
                        break label4;
                    }
                    catch(NumberFormatException a34)
                    {
                        a19 = a34;
                        break label2;
                    }
                    label5: try
                    {
                        a22 = a21.append(s14);
                        break label5;
                    }
                    catch(NumberFormatException a35)
                    {
                        a19 = a35;
                        break label2;
                    }
                    label6: try
                    {
                        a23 = a22.append(i);
                        break label6;
                    }
                    catch(NumberFormatException a36)
                    {
                        a19 = a36;
                        break label2;
                    }
                    label7: try
                    {
                        s15 = localization.Messages.getString("RadixExchangeSort.24");
                        break label7;
                    }
                    catch(NumberFormatException a37)
                    {
                        a19 = a37;
                        break label2;
                    }
                    label8: try
                    {
                        a24 = a23.append(s15);
                        break label8;
                    }
                    catch(NumberFormatException a38)
                    {
                        a19 = a38;
                        break label2;
                    }
                    label9: try
                    {
                        a25 = a24.append(s4);
                        break label9;
                    }
                    catch(NumberFormatException a39)
                    {
                        a19 = a39;
                        break label2;
                    }
                    label10: try
                    {
                        s16 = localization.Messages.getString("RadixExchangeSort.25");
                        break label10;
                    }
                    catch(NumberFormatException a40)
                    {
                        a19 = a40;
                        break label2;
                    }
                    label11: try
                    {
                        a26 = a25.append(s16);
                        break label11;
                    }
                    catch(NumberFormatException a41)
                    {
                        a19 = a41;
                        break label2;
                    }
                    label12: try
                    {
                        a27 = a26.append(i0);
                        break label12;
                    }
                    catch(NumberFormatException a42)
                    {
                        a19 = a42;
                        break label2;
                    }
                    label13: try
                    {
                        s17 = localization.Messages.getString("RadixExchangeSort.26");
                        break label13;
                    }
                    catch(NumberFormatException a43)
                    {
                        a19 = a43;
                        break label2;
                    }
                    label14: try
                    {
                        a28 = a27.append(s17);
                        break label14;
                    }
                    catch(NumberFormatException a44)
                    {
                        a19 = a44;
                        break label2;
                    }
                    label15: try
                    {
                        a29 = a28.append(i15);
                        break label15;
                    }
                    catch(NumberFormatException a45)
                    {
                        a19 = a45;
                        break label2;
                    }
                    label16: try
                    {
                        s18 = localization.Messages.getString("RadixExchangeSort.27");
                        break label16;
                    }
                    catch(NumberFormatException a46)
                    {
                        a19 = a46;
                        break label2;
                    }
                    label17: try
                    {
                        a30 = a29.append(s18);
                        break label17;
                    }
                    catch(NumberFormatException a47)
                    {
                        a19 = a47;
                        break label2;
                    }
                    label18: try
                    {
                        a31 = a30.append(i1);
                        break label18;
                    }
                    catch(NumberFormatException a48)
                    {
                        a19 = a48;
                        break label2;
                    }
                    label19: try
                    {
                        s19 = a31.toString();
                        break label19;
                    }
                    catch(NumberFormatException a49)
                    {
                        a19 = a49;
                        break label2;
                    }
                    label20: try
                    {
                        this.displayMessage(s19);
                        break label20;
                    }
                    catch(NumberFormatException a50)
                    {
                        a19 = a50;
                        break label2;
                    }
                    i2 = i15;
                    break label21;
                }
                StringBuilder a51 = new StringBuilder();
                String s20 = localization.Messages.getString("RadixExchangeSort.28");
                StringBuilder a52 = a51.append(s20);
                StringBuilder a53 = a52.append(s13);
                StringBuilder a54 = a53.append(" ");
                StringBuilder a55 = a54.append((Object)a19);
                String s21 = a55.toString();
                this.displayMessage(s21);
            }
            StringBuilder a56 = new StringBuilder();
            String s22 = localization.Messages.getString("RadixExchangeSort.30");
            StringBuilder a57 = a56.append(s22);
            StringBuilder a58 = a57.append(i0);
            String s23 = localization.Messages.getString("RadixExchangeSort.31");
            StringBuilder a59 = a58.append(s23);
            StringBuilder a60 = a59.append(i);
            String s24 = localization.Messages.getString("RadixExchangeSort.32");
            StringBuilder a61 = a60.append(s24);
            StringBuilder a62 = a61.append(s4);
            String s25 = a62.toString();
            this.displayMessage(s25);
            i2 = -1;
        }
        return i2;
    }
    
    public String getClassName()
    {
        String s = localization.Messages.getString("RadixExchangeSort.65");
        return s;
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
                java.awt.Color a0 = RadixExchangeSort.FINISHED_COLOR;
                a = a0;
                break label1;
            }
            int i0 = this.isDoingChildren()?1:0;
            label2: {
                if(i0 == 0)
                {
                    break label2;
                }
                java.awt.Color a1 = RadixExchangeSort.DOING_CHILDREN_COLOR;
                a = a1;
                break label1;
            }
            int i1 = this.isActive()?1:0;
            if(i1 == 0)
            {
                java.awt.Color a2 = RadixExchangeSort.WAITING_COLOR;
                a = a2;
            }
            else
            {
                java.awt.Color a3 = RadixExchangeSort.ACTIVE_COLOR;
                a = a3;
            }
        }
        return a;
    }
    
    public int[] getData()
    {
        int[] a = this.data;
        return a;
    }
    
    protected int getDepth(int i)
    {
        int i0 = 0;
        RadixExchangeSort a = this.leftChild;
        label1: {
            label0: {
                if(a == null)
                {
                    break label0;
                }
                RadixExchangeSort a0 = this.rightChild;
                if(a0 == null)
                {
                    break label0;
                }
                RadixExchangeSort a1 = this.leftChild;
                int i1 = i + 1;
                int i2 = a1.getDepth(i1);
                RadixExchangeSort a2 = this.rightChild;
                int i3 = i + 1;
                int i4 = a2.getDepth(i3);
                int i5 = Math.max(i2, i4);
                i0 = i5;
                break label1;
            }
            RadixExchangeSort a3 = this.leftChild;
            label2: {
                if(a3 == null)
                {
                    break label2;
                }
                RadixExchangeSort a4 = this.leftChild;
                int i6 = i + 1;
                int i7 = a4.getDepth(i6);
                i0 = i7;
                break label1;
            }
            RadixExchangeSort a5 = this.rightChild;
            if(a5 == null)
            {
                i0 = i;
            }
            else
            {
                RadixExchangeSort a6 = this.rightChild;
                int i8 = i + 1;
                int i9 = a6.getDepth(i8);
                i0 = i9;
            }
        }
        return i0;
    }
    
    public com.cim.AIA.ElementArray getElementArray(java.awt.Point a)
    {
        int i = a.x;
        int i0 = a.y;
        com.cim.AIA.ElementArray a0 = new com.cim.AIA.ElementArray(i, i0);
        int i1 = this.columSpacing;
        a0.setColumGap(i1);
        int i2 = this.columWidth;
        a0.setElementWidth(i2);
        int[] a1 = this.data;
        int i3 = a1.length;
        java.awt.Color[] a2 = new java.awt.Color[i3];
        int[] a3 = this.data;
        int i4 = a3.length;
        int[] a4 = new int[i4];
        this.calculateColors(a2, a4, 0);
        int i5 = 0;
        while(true)
        {
            int[] a5 = this.data;
            int i6 = a5.length;
            if(i5 >= i6)
            {
                return a0;
            }
            int[] a6 = this.data;
            int i7 = a6[i5];
            java.awt.Color a7 = a2[i5];
            java.awt.Point a8 = new java.awt.Point(0, 0);
            com.cim.AIA.VerticalBar a9 = new com.cim.AIA.VerticalBar(i5, i7, a7, a8);
            java.awt.Color a10 = RadixExchangeSort.TEXT_COLOR;
            a9.setTextColor(a10);
            a0.setValue(i5, (com.cim.AIA.Element)a9);
            com.cim.AIA.Element a11 = a0.getElement(i5);
            com.cim.AIA.VerticalBar dummy = (com.cim.AIA.VerticalBar)a11;
            com.cim.AIA.VerticalBar a12 = (com.cim.AIA.VerticalBar)a11;
            a12.setMarkersAboveValue(false);
            com.cim.AIA.Element a13 = a0.getElement(i5);
            com.cim.AIA.VerticalBar dummy0 = (com.cim.AIA.VerticalBar)a13;
            com.cim.AIA.VerticalBar a14 = (com.cim.AIA.VerticalBar)a13;
            a14.setOffsetForValue(1);
            String[] a15 = RadixExchangeSort.duplicateLabels;
            String s = a15[i5];
            if(s != null)
            {
                StringBuilder a16 = new StringBuilder();
                StringBuilder a17 = a16.append("");
                int[] a18 = this.data;
                int i8 = a18[i5];
                StringBuilder a19 = a17.append(i8);
                String[] a20 = RadixExchangeSort.duplicateLabels;
                String s0 = a20[i5];
                StringBuilder a21 = a19.append(s0);
                String s1 = a21.toString();
                com.cim.AIA.StringMarker a22 = new com.cim.AIA.StringMarker(s1);
                java.awt.Color a23 = RadixExchangeSort.TEXT_COLOR;
                a22.setColor(a23);
                java.awt.Color a24 = RadixExchangeSort.BACKGROUND;
                a22.setBackgroundColor(a24);
                com.cim.AIA.Element a25 = a0.getElement(i5);
                a25.addMarker(a22);
            }
            int i9 = i5 + 1;
            i5 = i9;
        }
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
        int i5 = this.yTextBuffer;
        java.awt.FontMetrics a1 = a.getFontMetrics();
        int i6 = a1.getHeight();
        int i7 = i5 + i6;
        int i8 = 2 * i7;
        int i9 = i4 + i8;
        int i10 = this.bitLength;
        int i11 = i10 + 2;
        java.awt.FontMetrics a2 = a.getFontMetrics();
        int i12 = a2.getHeight();
        int i13 = this.yTextBuffer;
        int i14 = i12 + i13;
        int i15 = i11 * i14;
        int i16 = i9 + i15;
        return i16;
    }
    
    public int getI()
    {
        int i = this.i;
        return i;
    }
    
    public int getJ()
    {
        int i = this.j;
        return i;
    }
    
    public int getLeft()
    {
        int i = this.left;
        return i;
    }
    
    public RadixExchangeSort getLeftChild()
    {
        RadixExchangeSort a = this.leftChild;
        return a;
    }
    
    public int getRight()
    {
        int i = this.right;
        return i;
    }
    
    public RadixExchangeSort getRightChild()
    {
        RadixExchangeSort a = this.rightChild;
        return a;
    }
    
    public int getWidth(java.awt.Graphics a)
    {
        int[] a0 = this.data;
        int i = a0.length;
        int i0 = this.columWidth;
        int i1 = this.columSpacing;
        int i2 = i0 + i1;
        int i3 = i * i2;
        int i4 = this.xTextBuffer;
        int i5 = i3 + i4;
        int i6 = this.columWidth;
        int i7 = this.columSpacing;
        int i8 = i6 + i7;
        int i9 = i5 + i8;
        int i10 = i9 + 30;
        java.awt.FontMetrics a1 = a.getFontMetrics();
        String s = RadixExchangeSort.MSB_LABEL;
        int i11 = a1.stringWidth(s);
        int i12 = this.xTextBuffer;
        int i13 = i11 + i12;
        int i14 = i10 + i13;
        return i14;
    }
    
    public boolean hasQuestions()
    {
        return false;
    }
    
    protected void initialise(boolean b)
    {
        this.debugMode = false;
        int i = b?1:0;
        this.active = false;
        this.processed = false;
        this.leftChild = null;
        this.rightChild = null;
        label0: {
            int i0 = 0;
            int i1 = 0;
            if(i == 0)
            {
                break label0;
            }
            this.left = 0;
            int[] a = this.data;
            int i2 = a.length;
            int i3 = i2 - 1;
            this.right = i3;
            int[] a0 = this.data;
            int i4 = a0.length;
            if(i4 <= 0)
            {
                i0 = 0;
            }
            else
            {
                int[] a1 = this.data;
                int i5 = a1[0];
                i0 = i5;
            }
            int i6 = i0;
            int i7 = 0;
            int i8 = 0;
            while(true)
            {
                int i9 = 0;
                int i10 = 0;
                int i11 = 0;
                int i12 = 0;
                int[] a2 = this.data;
                int i13 = i6;
                int i14 = i7;
                int i15 = a2.length;
                int i16 = i13;
                i1 = i14;
                int i17 = i16;
                int i18 = i1;
                if(i8 >= i15)
                {
                    break;
                }
                else
                {
                    i9 = i17;
                    i10 = i18;
                }
                int[] a3 = this.data;
                int i19 = i9;
                int i20 = i10;
                int i21 = a3[i8];
                int i22 = i19;
                int i23 = i20;
                int i24 = i22;
                int i25 = i23;
                if(i21 <= i22)
                {
                    i11 = i24;
                    i12 = i25;
                }
                else
                {
                    int[] a4 = this.data;
                    int i26 = a4[i8];
                    i11 = i26;
                    i12 = i8;
                }
                int i27 = i8 + 1;
                i6 = i11;
                i7 = i12;
                i8 = i27;
            }
            int[] a5 = this.data;
            int i28 = a5.length;
            if(i28 > 0)
            {
                int[] a6 = this.data;
                int i29 = a6[i1];
                String s = Integer.toBinaryString(i29);
                int i30 = s.length();
                this.bitLength = i30;
                int i31 = this.bitLength;
                int i32 = i31 - 1;
                this.bit = i32;
            }
            int[] a7 = this.data;
            this.createDuplicateLabels(a7);
        }
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
                RadixExchangeSort.BACKGROUND = a0;
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
                RadixExchangeSort.TEXT_COLOR = a1;
                break label1;
            }
            String s2 = RadixExchangeSort.ACTIVE;
            int i1 = s.equalsIgnoreCase(s2)?1:0;
            label3: {
                if(i1 == 0)
                {
                    break label3;
                }
                java.awt.Color a2 = a.getColor();
                RadixExchangeSort.ACTIVE_COLOR = a2;
                break label1;
            }
            String s3 = RadixExchangeSort.PROCESSED;
            int i2 = s.equalsIgnoreCase(s3)?1:0;
            label4: {
                if(i2 == 0)
                {
                    break label4;
                }
                java.awt.Color a3 = a.getColor();
                RadixExchangeSort.DOING_CHILDREN_COLOR = a3;
                break label1;
            }
            String s4 = RadixExchangeSort.FINISHED;
            int i3 = s.equalsIgnoreCase(s4)?1:0;
            label5: {
                if(i3 == 0)
                {
                    break label5;
                }
                java.awt.Color a4 = a.getColor();
                RadixExchangeSort.FINISHED_COLOR = a4;
                break label1;
            }
            String s5 = RadixExchangeSort.WAITING;
            int i4 = s.equalsIgnoreCase(s5)?1:0;
            if(i4 != 0)
            {
                java.awt.Color a5 = a.getColor();
                RadixExchangeSort.WAITING_COLOR = a5;
            }
        }
    }
    
    public void reInitialise(Object a)
    {
        int[] dummy = (int[])a;
        int[] a0 = (int[])a;
        this.data = a0;
        this.initialise(true);
        this.setPosition("");
    }
    
    public void removeAllAnimationRequests()
    {
        java.util.Vector a = this.swapRequests;
        a.removeAllElements();
        RadixExchangeSort a0 = this.leftChild;
        if(a0 != null)
        {
            RadixExchangeSort a1 = this.leftChild;
            a1.removeAllAnimationRequests();
        }
        RadixExchangeSort a2 = this.rightChild;
        if(a2 != null)
        {
            RadixExchangeSort a3 = this.rightChild;
            a3.removeAllAnimationRequests();
        }
    }
    
    public void removeAllQuestions()
    {
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
            this.active = true;
            int i0 = this.bit;
            RadixExchangeSort.currentBit = i0;
            this.setPosition("1");
            this.setPosition("3");
            StringBuilder a = new StringBuilder();
            String s = localization.Messages.getString("RadixExchangeSort.35");
            StringBuilder a0 = a.append(s);
            int i1 = this.right;
            StringBuilder a1 = a0.append(i1);
            String s0 = localization.Messages.getString("RadixExchangeSort.36");
            StringBuilder a2 = a1.append(s0);
            int i2 = this.left;
            StringBuilder a3 = a2.append(i2);
            String s1 = localization.Messages.getString("RadixExchangeSort.37");
            StringBuilder a4 = a3.append(s1);
            int i3 = this.bit;
            StringBuilder a5 = a4.append(i3);
            String s2 = localization.Messages.getString("RadixExchangeSort.38");
            StringBuilder a6 = a5.append(s2);
            int i4 = this.bitLength;
            StringBuilder a7 = a6.append(i4);
            String s3 = a7.toString();
            this.displayMessage(s3);
            int i5 = this.right;
            int i6 = this.left;
            label2: {
                if(i5 <= i6)
                {
                    break label2;
                }
                int i7 = this.bit;
                if(i7 < 0)
                {
                    break label2;
                }
                this.setPosition("3.1");
                int i8 = this.left;
                this.i = i8;
                int i9 = this.right;
                this.j = i9;
                this.setPosition("3.1.1");
                this.setPosition("3.1.2");
                while(true)
                {
                    int i10 = this.i;
                    int i11 = this.j;
                    if(i10 == i11)
                    {
                        break;
                    }
                    this.setPosition("3.1.2.1");
                    while(true)
                    {
                        int[] a8 = this.data;
                        int i12 = this.i;
                        int i13 = a8[i12];
                        int i14 = this.bit;
                        int i15 = this.bitLength;
                        int i16 = this.getBit(i13, i14, i15);
                        if(i16 != 0)
                        {
                            break;
                        }
                        int i17 = this.i;
                        int i18 = this.j;
                        if(i17 >= i18)
                        {
                            break;
                        }
                        else
                        {
                            this.setPosition("3.1.2.1.1");
                            int i19 = this.i;
                            int i20 = i19 + 1;
                            this.i = i20;
                            this.setPosition("3.1.2.1.1.1");
                        }
                    }
                    this.setPosition("3.1.2.1.2");
                    this.setPosition("3.1.2.2");
                    while(true)
                    {
                        int[] a9 = this.data;
                        int i21 = this.j;
                        int i22 = a9[i21];
                        int i23 = this.bit;
                        int i24 = this.bitLength;
                        int i25 = this.getBit(i22, i23, i24);
                        if(i25 != 1)
                        {
                            break;
                        }
                        int i26 = this.j;
                        int i27 = this.i;
                        if(i26 <= i27)
                        {
                            break;
                        }
                        else
                        {
                            this.setPosition("3.1.2.2.1");
                            int i28 = this.j;
                            int i29 = i28 - 1;
                            this.j = i29;
                            this.setPosition("3.1.2.2.1.1");
                        }
                    }
                    this.setPosition("3.1.2.2.2");
                    int i30 = this.i;
                    int i31 = this.j;
                    this.swap(i30, i31);
                    this.setPosition("3.1.2.3");
                    int i32 = this.i;
                    int i33 = this.j;
                    if(i32 == i33)
                    {
                    }
                    else
                    {
                        this.setPosition("3.1.2");
                    }
                }
                this.setPosition("3.1.4");
                int[] a10 = this.data;
                int i34 = this.right;
                int i35 = a10[i34];
                int i36 = this.bit;
                int i37 = this.bitLength;
                int i38 = this.getBit(i35, i36, i37);
                if(i38 == 0)
                {
                    int i39 = this.j;
                    int i40 = i39 + 1;
                    this.j = i40;
                    this.setPosition("3.1.4.1");
                }
                this.setPosition("3.1.4.2");
                com.cim.AIA.AlgorithmThread a11 = this.algorithmThread;
                int[] a12 = this.data;
                RadixExchangeSort a13 = new RadixExchangeSort(a11, a12, false);
                this.leftChild = a13;
                RadixExchangeSort a14 = this.leftChild;
                int i41 = this.enabled?1:0;
                a14.enabled = i41 != 0;
                RadixExchangeSort a15 = this.leftChild;
                int i42 = this.left;
                a15.left = i42;
                RadixExchangeSort a16 = this.leftChild;
                int i43 = this.j;
                int i44 = i43 - 1;
                a16.right = i44;
                RadixExchangeSort a17 = this.leftChild;
                int i45 = this.bit;
                int i46 = i45 - 1;
                a17.bit = i46;
                RadixExchangeSort a18 = this.leftChild;
                int i47 = this.bitLength;
                a18.bitLength = i47;
                com.cim.AIA.AlgorithmThread a19 = this.algorithmThread;
                if(a19 == null)
                {
                    java.io.PrintStream a20 = System.out;
                    String s4 = localization.Messages.getString("RadixExchangeSort.55");
                    a20.println(s4);
                }
                RadixExchangeSort a21 = this.leftChild;
                com.cim.AIA.AlgorithmThread a22 = a21.algorithmThread;
                if(a22 == null)
                {
                    java.io.PrintStream a23 = System.out;
                    String s5 = localization.Messages.getString("RadixExchangeSort.56");
                    a23.println(s5);
                }
                com.cim.AIA.AlgorithmThread a24 = this.algorithmThread;
                int[] a25 = this.data;
                RadixExchangeSort a26 = new RadixExchangeSort(a24, a25, false);
                this.rightChild = a26;
                RadixExchangeSort a27 = this.rightChild;
                int i48 = this.enabled?1:0;
                a27.enabled = i48 != 0;
                RadixExchangeSort a28 = this.rightChild;
                int i49 = this.j;
                a28.left = i49;
                RadixExchangeSort a29 = this.rightChild;
                int i50 = this.right;
                a29.right = i50;
                RadixExchangeSort a30 = this.rightChild;
                int i51 = this.bit;
                int i52 = i51 - 1;
                a30.bit = i52;
                RadixExchangeSort a31 = this.rightChild;
                int i53 = this.bitLength;
                a31.bitLength = i53;
                this.doingChildren = true;
                this.setPosition("3.2");
                this.setPosition("3.2.1");
                String s6 = localization.Messages.getString("RadixExchangeSort.59");
                this.displayMessage(s6);
                RadixExchangeSort a32 = this.leftChild;
                a32.run();
                RadixExchangeSort a33 = this.leftChild;
                a33.processed = true;
                int i54 = this.bit;
                RadixExchangeSort.currentBit = i54;
                this.setPosition("3.3");
                this.setPosition("3.3.1");
                String s7 = localization.Messages.getString("RadixExchangeSort.62");
                this.displayMessage(s7);
                RadixExchangeSort a34 = this.rightChild;
                a34.run();
                RadixExchangeSort a35 = this.rightChild;
                a35.processed = true;
            }
            this.processed = true;
            int i55 = this.bit;
            RadixExchangeSort.currentBit = i55;
            this.setPosition("5");
        }
    }
    
    public void setEnabled(boolean b)
    {
        this.enabled = b;
        int i = b?1:0;
        RadixExchangeSort a = this.rightChild;
        if(a != null)
        {
            RadixExchangeSort a0 = this.rightChild;
            a0.setEnabled(i != 0);
        }
        RadixExchangeSort a1 = this.leftChild;
        if(a1 != null)
        {
            RadixExchangeSort a2 = this.leftChild;
            a2.setEnabled(i != 0);
        }
    }
    
    protected void swap(int i, int i0)
    {
        int[] a = this.data;
        int i1 = a[i0];
        int[] a0 = this.data;
        int[] a1 = this.data;
        int i2 = a1[i];
        a0[i0] = i2;
        int[] a2 = this.data;
        a2[i] = i1;
        if(i0 != i)
        {
            java.util.Vector a3 = this.swapRequests;
            com.cim.AIA.SwapRequest a4 = new com.cim.AIA.SwapRequest(i, i0);
            a3.addElement((Object)a4);
        }
    }
    
    static
    {
        RadixExchangeSort.currentBit = -1;
        String s = localization.Messages.getString("RadixExchangeSort.0");
        RadixExchangeSort.MSB_LABEL = s;
        String s0 = localization.Messages.getString("RadixExchangeSort.1");
        RadixExchangeSort.LSB_LABEL = s0;
        String s1 = localization.Messages.getString("RadixExchangeSort.2");
        RadixExchangeSort.BIT_LABEL = s1;
        java.awt.Color a = java.awt.Color.pink;
        RadixExchangeSort.WAITING_COLOR = a;
        java.awt.Color a0 = java.awt.Color.green;
        RadixExchangeSort.HIGHLIGHT_COLOR = a0;
        java.awt.Color a1 = java.awt.Color.red;
        java.awt.Color a2 = a1.darker();
        RadixExchangeSort.DOING_CHILDREN_COLOR = a2;
        String s2 = localization.Messages.getString("RadixExchangeSort.5");
        RadixExchangeSort.ACTIVE = s2;
        String s3 = localization.Messages.getString("RadixExchangeSort.6");
        RadixExchangeSort.PROCESSED = s3;
        String s4 = localization.Messages.getString("RadixExchangeSort.7");
        RadixExchangeSort.FINISHED = s4;
        String s5 = localization.Messages.getString("RadixExchangeSort.8");
        RadixExchangeSort.WAITING = s5;
        java.awt.Color a3 = java.awt.Color.red;
        RadixExchangeSort.ACTIVE_COLOR = a3;
        java.awt.Color a4 = java.awt.Color.gray;
        RadixExchangeSort.FINISHED_COLOR = a4;
        java.awt.Color a5 = java.awt.Color.white;
        RadixExchangeSort.BACKGROUND = a5;
        java.awt.Color a6 = java.awt.Color.black;
        RadixExchangeSort.TEXT_COLOR = a6;
    }
}