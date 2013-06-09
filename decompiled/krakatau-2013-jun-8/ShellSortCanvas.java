public class ShellSortCanvas extends com.cim.AIA.AlgorithmCanvas {
    final private static long serialVersionUID = 1130991115387495544L;
    final protected static String I_MARKER = "i";
    final protected static String J_MARKER = "J";
    final protected static String FIRST_POS_MARKER;
    protected com.cim.AIA.StringMarker iMarker;
    protected com.cim.AIA.StringMarker jMarker;
    protected int xBuffer;
    protected int yBuffer;
    protected int boxWidth;
    protected ShellSort shellSort;
    protected com.cim.AIA.ElementArray elementArray;
    protected int value;
    protected int hValue;
    protected int firstPosValue;
    protected java.awt.Color backgroundColor;
    protected java.awt.Color textColor;
    protected boolean needInitialisation;
    protected com.cim.AIA.Line firstPosLine;
    protected com.cim.AIA.Line[] horizontalLines;
    protected com.cim.AIA.Line[] verticalPointerLines;
    
    public ShellSortCanvas()
    {
        super();
        this.xBuffer = 80;
        this.yBuffer = 180;
        this.boxWidth = 20;
        this.value = -1;
        this.hValue = -1;
        this.firstPosValue = -1;
        java.awt.Color a = java.awt.Color.white;
        this.backgroundColor = a;
        java.awt.Color a0 = java.awt.Color.black;
        this.textColor = a0;
        this.needInitialisation = false;
    }
    
    public void displayAlgorithm(java.awt.Graphics a)
    {
        int i = this.needInitialisation?1:0;
        label1: {
            label0: {
                if(i == 0)
                {
                    break label0;
                }
                ShellSort a0 = this.shellSort;
                com.cim.AIA.RepaintEvent a1 = new com.cim.AIA.RepaintEvent((Object)this, (Object)a0);
                this.processRepaintEvent(a1);
                this.needInitialisation = false;
                break label1;
            }
            ShellSort a2 = this.shellSort;
            label2: {
                if(a2 == null)
                {
                    break label2;
                }
                com.cim.AIA.ElementArray a3 = this.elementArray;
                a3.draw(a);
                com.cim.AIA.StringMarker a4 = this.iMarker;
                if(a4 != null)
                {
                    com.cim.AIA.StringMarker a5 = this.iMarker;
                    a5.draw(a);
                }
                com.cim.AIA.StringMarker a6 = this.jMarker;
                if(a6 != null)
                {
                    com.cim.AIA.StringMarker a7 = this.jMarker;
                    a7.draw(a);
                }
                java.util.Vector a8 = ShellSort.drawLine;
                int i0 = a8.size();
                label3: {
                    if(i0 <= 0)
                    {
                        break label3;
                    }
                    int i1 = 0;
                    while(true)
                    {
                        java.util.Vector a9 = ShellSort.drawLine;
                        int i2 = a9.size();
                        int i3 = i2 - 1;
                        if(i1 >= i3)
                        {
                            break;
                        }
                        com.cim.AIA.Line[] a10 = this.horizontalLines;
                        com.cim.AIA.Line a11 = a10[i1];
                        if(a11 != null)
                        {
                            com.cim.AIA.Line[] a12 = this.horizontalLines;
                            com.cim.AIA.Line a13 = a12[i1];
                            a13.draw(a);
                        }
                        int i4 = i1 + 1;
                        i1 = i4;
                    }
                    int i5 = 0;
                    while(true)
                    {
                        java.util.Vector a14 = ShellSort.drawLine;
                        int i6 = a14.size();
                        if(i5 >= i6)
                        {
                            break;
                        }
                        com.cim.AIA.Line[] a15 = this.verticalPointerLines;
                        com.cim.AIA.Line a16 = a15[i5];
                        if(a16 != null)
                        {
                            com.cim.AIA.Line[] a17 = this.verticalPointerLines;
                            com.cim.AIA.Line a18 = a17[i5];
                            a18.draw(a);
                        }
                        int i7 = i5 + 1;
                        i5 = i7;
                    }
                }
                com.cim.AIA.Line a19 = this.firstPosLine;
                if(a19 != null)
                {
                    com.cim.AIA.Line a20 = this.firstPosLine;
                    a20.draw(a);
                }
                com.cim.AIA.ElementArray a21 = this.elementArray;
                java.awt.Point a22 = a21.getLocation();
                int i8 = a22.x;
                com.cim.AIA.ElementArray a23 = this.elementArray;
                int i9 = a23.getWidth();
                int i10 = i8 + i9;
                com.cim.AIA.ElementArray a24 = this.elementArray;
                int i11 = a24.getElementWidth();
                int i12 = i10 + i11;
                com.cim.AIA.ElementArray a25 = this.elementArray;
                int i13 = a25.getColumGap();
                int i14 = i12 + i13;
                int i15 = this.yBuffer;
                java.awt.FontMetrics a26 = a.getFontMetrics();
                int i16 = a26.getHeight();
                int i17 = i15 + i16;
                java.awt.Point a27 = new java.awt.Point(i14, i17);
                int i18 = a27.x;
                int i19 = a27.y;
                int i20 = this.boxWidth;
                int i21 = this.boxWidth;
                a.drawRect(i18, i19, i20, i21);
                java.awt.Color a28 = this.textColor;
                a.setColor(a28);
                String s = localization.Messages.getString("ShellSortCanvas.1");
                int i22 = a27.x;
                int i23 = this.boxWidth;
                int i24 = i23 / 2;
                int i25 = i22 + i24;
                java.awt.FontMetrics a29 = a.getFontMetrics();
                String s0 = localization.Messages.getString("ShellSortCanvas.2");
                int i26 = a29.stringWidth(s0);
                int i27 = i26 / 2;
                int i28 = i25 - i27;
                int i29 = a27.y;
                a.drawString(s, i28, i29);
                ShellSort a30 = this.shellSort;
                int i30 = a30.getVal();
                if(i30 != -10)
                {
                    java.awt.Color a31 = ShellSort.activeArrayColor;
                    a.setColor(a31);
                    int i31 = a27.x;
                    int i32 = a27.y;
                    int i33 = this.boxWidth;
                    int i34 = this.boxWidth;
                    a.fillRect(i31, i32, i33, i34);
                    java.awt.Color a32 = this.textColor;
                    a.setColor(a32);
                    ShellSort a33 = this.shellSort;
                    int i35 = a33.getVal();
                    this.value = i35;
                    StringBuilder a34 = new StringBuilder();
                    StringBuilder a35 = a34.append("");
                    int i36 = this.value;
                    StringBuilder a36 = a35.append(i36);
                    String s1 = a36.toString();
                    int i37 = a27.x;
                    int i38 = this.boxWidth;
                    int i39 = i38 / 2;
                    int i40 = i37 + i39;
                    java.awt.FontMetrics a37 = a.getFontMetrics();
                    StringBuilder a38 = new StringBuilder();
                    StringBuilder a39 = a38.append("");
                    int i41 = this.value;
                    StringBuilder a40 = a39.append(i41);
                    String s2 = a40.toString();
                    int i42 = a37.stringWidth(s2);
                    int i43 = i42 / 2;
                    int i44 = i40 - i43;
                    int i45 = a27.y;
                    int i46 = this.boxWidth;
                    int i47 = i46 / 2;
                    int i48 = i45 + i47;
                    java.awt.FontMetrics a41 = a.getFontMetrics();
                    int i49 = a41.getHeight();
                    int i50 = i49 / 2;
                    int i51 = i48 + i50;
                    a.drawString(s1, i44, i51);
                }
                com.cim.AIA.ElementArray a42 = this.elementArray;
                java.awt.Point a43 = a42.getLocation();
                int i52 = a43.x;
                com.cim.AIA.ElementArray a44 = this.elementArray;
                int i53 = a44.getWidth();
                int i54 = i52 + i53;
                com.cim.AIA.ElementArray a45 = this.elementArray;
                int i55 = a45.getElementWidth();
                int i56 = i54 + i55;
                com.cim.AIA.ElementArray a46 = this.elementArray;
                int i57 = a46.getColumGap();
                int i58 = i56 + i57;
                int i59 = this.yBuffer;
                int i60 = i59 - 40;
                java.awt.FontMetrics a47 = a.getFontMetrics();
                int i61 = a47.getHeight();
                int i62 = i60 + i61;
                java.awt.Point a48 = new java.awt.Point(i58, i62);
                int i63 = a48.x;
                int i64 = a48.y;
                int i65 = this.boxWidth;
                int i66 = this.boxWidth;
                a.drawRect(i63, i64, i65, i66);
                java.awt.Color a49 = this.textColor;
                a.setColor(a49);
                int i67 = a27.x;
                int i68 = this.boxWidth;
                int i69 = i68 / 2;
                int i70 = i67 + i69;
                java.awt.FontMetrics a50 = a.getFontMetrics();
                int i71 = a50.stringWidth("h");
                int i72 = i71 / 2;
                int i73 = i70 - i72;
                int i74 = a48.y;
                a.drawString("h", i73, i74);
                ShellSort a51 = this.shellSort;
                int i75 = a51.getH();
                if(i75 != -10)
                {
                    ShellSort a52 = this.shellSort;
                    int i76 = a52.getH();
                    this.hValue = i76;
                    StringBuilder a53 = new StringBuilder();
                    StringBuilder a54 = a53.append("");
                    int i77 = this.hValue;
                    StringBuilder a55 = a54.append(i77);
                    String s3 = a55.toString();
                    int i78 = a48.x;
                    int i79 = this.boxWidth;
                    int i80 = i79 / 2;
                    int i81 = i78 + i80;
                    java.awt.FontMetrics a56 = a.getFontMetrics();
                    StringBuilder a57 = new StringBuilder();
                    StringBuilder a58 = a57.append("");
                    int i82 = this.hValue;
                    StringBuilder a59 = a58.append(i82);
                    String s4 = a59.toString();
                    int i83 = a56.stringWidth(s4);
                    int i84 = i83 / 2;
                    int i85 = i81 - i84;
                    int i86 = a48.y;
                    int i87 = this.boxWidth;
                    int i88 = i87 / 2;
                    int i89 = i86 + i88;
                    java.awt.FontMetrics a60 = a.getFontMetrics();
                    int i90 = a60.getHeight();
                    int i91 = i90 / 2;
                    int i92 = i89 + i91;
                    a.drawString(s3, i85, i92);
                }
                ShellSort a61 = this.shellSort;
                int i93 = a61.getComparisonOrder();
                if(i93 != 10)
                {
                    break label2;
                }
                com.cim.AIA.ElementArray a62 = this.elementArray;
                java.awt.Point a63 = a62.getLocation();
                int i94 = a63.x;
                com.cim.AIA.ElementArray a64 = this.elementArray;
                int i95 = a64.getWidth();
                int i96 = i94 + i95;
                com.cim.AIA.ElementArray a65 = this.elementArray;
                int i97 = a65.getElementWidth();
                int i98 = i96 + i97;
                com.cim.AIA.ElementArray a66 = this.elementArray;
                int i99 = a66.getColumGap();
                int i100 = i98 + i99;
                int i101 = this.yBuffer;
                int i102 = i101 - 80;
                java.awt.FontMetrics a67 = a.getFontMetrics();
                int i103 = a67.getHeight();
                int i104 = i102 + i103;
                java.awt.Point a68 = new java.awt.Point(i100, i104);
                int i105 = a68.x;
                int i106 = a68.y;
                int i107 = this.boxWidth;
                int i108 = this.boxWidth;
                a.drawRect(i105, i106, i107, i108);
                java.awt.Color a69 = this.textColor;
                a.setColor(a69);
                String s5 = localization.Messages.getString("ShellSortCanvas.3");
                int i109 = a68.x;
                int i110 = this.boxWidth;
                int i111 = i110 / 2;
                int i112 = i109 + i111;
                java.awt.FontMetrics a70 = a.getFontMetrics();
                String s6 = localization.Messages.getString("ShellSortCanvas.4");
                int i113 = a70.stringWidth(s6);
                int i114 = i113 / 2;
                int i115 = i112 - i114;
                int i116 = a68.y;
                a.drawString(s5, i115, i116);
                ShellSort a71 = this.shellSort;
                int i117 = a71.getFirstPos();
                if(i117 != -10)
                {
                    ShellSort a72 = this.shellSort;
                    int i118 = a72.getFirstPos();
                    this.firstPosValue = i118;
                    StringBuilder a73 = new StringBuilder();
                    StringBuilder a74 = a73.append("");
                    int i119 = this.firstPosValue;
                    StringBuilder a75 = a74.append(i119);
                    String s7 = a75.toString();
                    int i120 = a68.x;
                    int i121 = this.boxWidth;
                    int i122 = i121 / 2;
                    int i123 = i120 + i122;
                    java.awt.FontMetrics a76 = a.getFontMetrics();
                    StringBuilder a77 = new StringBuilder();
                    StringBuilder a78 = a77.append("");
                    int i124 = this.firstPosValue;
                    StringBuilder a79 = a78.append(i124);
                    String s8 = a79.toString();
                    int i125 = a76.stringWidth(s8);
                    int i126 = i125 / 2;
                    int i127 = i123 - i126;
                    int i128 = a68.y;
                    int i129 = this.boxWidth;
                    int i130 = i129 / 2;
                    int i131 = i128 + i130;
                    java.awt.FontMetrics a80 = a.getFontMetrics();
                    int i132 = a80.getHeight();
                    int i133 = i132 / 2;
                    int i134 = i131 + i133;
                    a.drawString(s7, i127, i134);
                }
            }
        }
    }
    
    protected com.cim.AIA.Line drawHorizontalLine(int i, int i0)
    {
        com.cim.AIA.ElementArray a = this.elementArray;
        com.cim.AIA.Element a0 = a.getElement(i);
        java.awt.Point a1 = a0.getPosition();
        com.cim.AIA.ElementArray a2 = this.elementArray;
        com.cim.AIA.Element a3 = a2.getElement(i0);
        java.awt.Point a4 = a3.getPosition();
        int i1 = a1.x;
        com.cim.AIA.ElementArray a5 = this.elementArray;
        int i2 = a5.getElementWidth();
        int i3 = i2 / 2;
        int i4 = i1 + i3;
        int i5 = a1.y;
        int i6 = i5 + 30;
        int i7 = a4.x;
        com.cim.AIA.ElementArray a6 = this.elementArray;
        int i8 = a6.getElementWidth();
        int i9 = i8 / 2;
        int i10 = i7 + i9;
        int i11 = a4.y;
        int i12 = i11 + 30;
        com.cim.AIA.Line a7 = new com.cim.AIA.Line(i4, i6, i10, i12);
        return a7;
    }
    
    protected com.cim.AIA.Line drawLinePointers(int i)
    {
        com.cim.AIA.ElementArray a = this.elementArray;
        com.cim.AIA.Element a0 = a.getElement(i);
        java.awt.Point a1 = a0.getPosition();
        int i0 = a1.x;
        com.cim.AIA.ElementArray a2 = this.elementArray;
        int i1 = a2.getElementWidth();
        int i2 = i1 / 2;
        int i3 = i0 + i2;
        int i4 = a1.y;
        int i5 = i4 + 30;
        int i6 = a1.x;
        com.cim.AIA.ElementArray a3 = this.elementArray;
        int i7 = a3.getElementWidth();
        int i8 = i7 / 2;
        int i9 = i6 + i8;
        int i10 = a1.y;
        int i11 = i10 + 20;
        com.cim.AIA.Line a4 = new com.cim.AIA.Line(i3, i5, i9, i11);
        return a4;
    }
    
    protected void handleColorSelection(com.cim.AIA.ColorSelection a)
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
                this.backgroundColor = a0;
                break label1;
            }
            String s1 = com.cim.AIA.ColorSelection.TEXT_COLOR;
            int i0 = s.equalsIgnoreCase(s1)?1:0;
            if(i0 != 0)
            {
                java.awt.Color a1 = a.getColor();
                this.textColor = a1;
            }
        }
    }
    
    protected void handleFontSelection(com.cim.AIA.FontSelection a)
    {
        String s = a.getAtributeName();
        int i = s.equalsIgnoreCase("Normal Font")?1:0;
        if(i != 0)
        {
            java.awt.Font a0 = a.getFont();
            this.setFont(a0);
        }
    }
    
    protected void initialise()
    {
        java.awt.Dimension a = this.getSize();
        com.cim.AIA.ElementArray a0 = this.elementArray;
        int i = a0.getWidth();
        int i0 = i + 50;
        a.width = i0;
        this.setSize(a);
        com.cim.AIA.ElementArray a1 = this.elementArray;
        java.awt.Dimension a2 = this.getSize();
        int i1 = a2.width;
        int i2 = i1 / 2;
        com.cim.AIA.ElementArray a3 = this.elementArray;
        int i3 = a3.getWidth();
        int i4 = i3 / 2;
        int i5 = i2 - i4;
        a1.setLocation(i5, 200);
        ShellSort a4 = this.shellSort;
        int i6 = a4.getI();
        com.cim.AIA.StringMarker a5 = this.initialiseMarker(i6, "i", 3);
        this.iMarker = a5;
        ShellSort a6 = this.shellSort;
        int i7 = a6.getJ();
        com.cim.AIA.StringMarker a7 = this.initialiseMarker(i7, "J", 4);
        this.jMarker = a7;
        ShellSort a8 = this.shellSort;
        int i8 = a8.getFirstPos();
        String s = ShellSortCanvas.FIRST_POS_MARKER;
        com.cim.AIA.Line a9 = this.initialiseLine(i8, s);
        this.firstPosLine = a9;
        java.util.Vector a10 = ShellSort.drawLine;
        int i9 = a10.size();
        label0: {
            if(i9 <= 0)
            {
                break label0;
            }
            int i10 = 0;
            while(true)
            {
                java.util.Vector a11 = ShellSort.drawLine;
                int i11 = a11.size();
                int i12 = i11 - 1;
                if(i10 >= i12)
                {
                    break;
                }
                else
                {
                    com.cim.AIA.Line[] a12 = this.horizontalLines;
                    java.util.Vector a13 = ShellSort.drawLine;
                    Object a14 = a13.elementAt(i10);
                    Integer dummy = (Integer)a14;
                    Integer a15 = (Integer)a14;
                    int i13 = a15.intValue();
                    java.util.Vector a16 = ShellSort.drawLine;
                    int i14 = i10 + 1;
                    Object a17 = a16.elementAt(i14);
                    Integer dummy0 = (Integer)a17;
                    Integer a18 = (Integer)a17;
                    int i15 = a18.intValue();
                    com.cim.AIA.Line a19 = this.drawHorizontalLine(i13, i15);
                    a12[i10] = a19;
                    int i16 = i10 + 1;
                    i10 = i16;
                }
            }
            int i17 = 0;
            while(true)
            {
                java.util.Vector a20 = ShellSort.drawLine;
                int i18 = a20.size();
                if(i17 >= i18)
                {
                    break;
                }
                else
                {
                    com.cim.AIA.Line[] a21 = this.verticalPointerLines;
                    java.util.Vector a22 = ShellSort.drawLine;
                    Object a23 = a22.elementAt(i17);
                    Integer dummy1 = (Integer)a23;
                    Integer a24 = (Integer)a23;
                    int i19 = a24.intValue();
                    com.cim.AIA.Line a25 = this.drawLinePointers(i19);
                    a21[i17] = a25;
                    int i20 = i17 + 1;
                    i17 = i20;
                }
            }
        }
    }
    
    protected com.cim.AIA.Line initialiseLine(int i, String s)
    {
        com.cim.AIA.Line a = null;
        label2: {
            com.cim.AIA.VerticalBar a0 = null;
            label1: {
                label0: {
                    if(i == -10)
                    {
                        break label0;
                    }
                    com.cim.AIA.ElementArray a1 = this.elementArray;
                    com.cim.AIA.Element a2 = a1.getElement(i);
                    com.cim.AIA.VerticalBar dummy = (com.cim.AIA.VerticalBar)a2;
                    a0 = (com.cim.AIA.VerticalBar)a2;
                    if(a0 != null)
                    {
                        break label1;
                    }
                }
                a = null;
                break label2;
            }
            java.awt.Point a3 = a0.getPosition();
            int i0 = a3.x;
            int i1 = a0.getWidth();
            int i2 = i1 / 2;
            int i3 = i0 + i2;
            java.awt.Point a4 = a0.getPosition();
            int i4 = a4.y;
            int i5 = a0.getHeight();
            int i6 = i4 - i5;
            java.awt.Point a5 = new java.awt.Point(i3, i6);
            java.awt.Point a6 = a0.getPosition();
            int i7 = a6.x;
            int i8 = a0.getWidth();
            int i9 = i8 / 2;
            int i10 = i7 + i9;
            java.awt.Point a7 = new java.awt.Point(i10, 50);
            com.cim.AIA.Line a8 = new com.cim.AIA.Line(a7, a5);
            a8.showArrowHead(true);
            a8.setDistanceFromStartForArrowHead(-3);
            a8.setLabel(s);
            a8.setDistanceFromStartForLabel(-1);
            java.awt.Graphics a9 = this.getGraphics();
            java.awt.FontMetrics a10 = a9.getFontMetrics();
            int i11 = a10.stringWidth(s);
            int i12 = -1 * i11;
            int i13 = i12 / 2;
            a8.setXLabelOffset(i13);
            java.awt.Graphics a11 = this.getGraphics();
            java.awt.FontMetrics a12 = a11.getFontMetrics();
            int i14 = a12.getHeight();
            int i15 = -1 * i14;
            a8.setYLabelOffset(i15);
            a = a8;
        }
        return a;
    }
    
    protected com.cim.AIA.StringMarker initialiseMarker(int i, String s, int i0)
    {
        com.cim.AIA.StringMarker a = null;
        label2: {
            com.cim.AIA.VerticalBar a0 = null;
            label1: {
                label0: {
                    if(i == -10)
                    {
                        break label0;
                    }
                    com.cim.AIA.ElementArray a1 = this.elementArray;
                    com.cim.AIA.Element a2 = a1.getElement(i);
                    com.cim.AIA.VerticalBar dummy = (com.cim.AIA.VerticalBar)a2;
                    a0 = (com.cim.AIA.VerticalBar)a2;
                    if(a0 != null)
                    {
                        break label1;
                    }
                }
                a = null;
                break label2;
            }
            java.awt.Point a3 = a0.getPosition();
            int i1 = a3.x;
            int i2 = a0.getWidth();
            int i3 = i2 / 2;
            int i4 = i1 + i3;
            java.awt.Point a4 = a0.getPosition();
            int i5 = a4.y;
            int i6 = i5 + 5;
            java.awt.Graphics a5 = this.getGraphics();
            java.awt.FontMetrics a6 = a5.getFontMetrics();
            int i7 = a6.getHeight();
            int i8 = i0 * i7;
            int i9 = i6 + i8;
            java.awt.Point a7 = new java.awt.Point(i4, i9);
            com.cim.AIA.StringMarker a8 = new com.cim.AIA.StringMarker(s, a7);
            java.awt.Color a9 = this.textColor;
            a8.setColor(a9);
            java.awt.Color a10 = this.backgroundColor;
            a8.setBackgroundColor(a10);
            a = a8;
        }
        return a;
    }
    
    public void processRepaintEvent(com.cim.AIA.RepaintEvent a)
    {
        Object a0 = a.paramObj;
        label3: {
            label2: {
                label0: {
                    if(a0 == null)
                    {
                        break label0;
                    }
                    Object a1 = a.paramObj;
                    ShellSort dummy = (ShellSort)a1;
                    ShellSort a2 = (ShellSort)a1;
                    this.shellSort = a2;
                    this.iMarker = null;
                    this.jMarker = null;
                    java.util.Vector a3 = ShellSort.drawLine;
                    int i = a3.size();
                    label1: {
                        if(i <= 0)
                        {
                            break label1;
                        }
                        java.util.Vector a4 = ShellSort.drawLine;
                        int i0 = a4.size();
                        int i1 = i0 - 1;
                        com.cim.AIA.Line[] a5 = new com.cim.AIA.Line[i1];
                        this.horizontalLines = a5;
                        java.util.Vector a6 = ShellSort.drawLine;
                        int i2 = a6.size();
                        com.cim.AIA.Line[] a7 = new com.cim.AIA.Line[i2];
                        this.verticalPointerLines = a7;
                        int i3 = 0;
                        while(true)
                        {
                            java.util.Vector a8 = ShellSort.drawLine;
                            int i4 = a8.size();
                            int i5 = i4 - 1;
                            if(i3 >= i5)
                            {
                                break;
                            }
                            else
                            {
                                com.cim.AIA.Line[] a9 = this.horizontalLines;
                                a9[i3] = null;
                                int i6 = i3 + 1;
                                i3 = i6;
                            }
                        }
                        int i7 = 0;
                        while(true)
                        {
                            java.util.Vector a10 = ShellSort.drawLine;
                            int i8 = a10.size();
                            if(i7 >= i8)
                            {
                                break;
                            }
                            else
                            {
                                com.cim.AIA.Line[] a11 = this.verticalPointerLines;
                                a11[i7] = null;
                                int i9 = i7 + 1;
                                i7 = i9;
                            }
                        }
                    }
                    java.awt.Graphics a12 = this.getGraphics();
                    if(a12 == null)
                    {
                        break label2;
                    }
                    ShellSort a13 = this.shellSort;
                    java.awt.Graphics a14 = this.getGraphics();
                    com.cim.AIA.ElementArray a15 = a13.getElementArray(a14);
                    this.elementArray = a15;
                    this.initialise();
                    ShellSort a16 = this.shellSort;
                    com.cim.AIA.ElementArray a17 = this.elementArray;
                    int i10 = this.numberOfTweenSteps;
                    com.cim.AIA.MultipleTween a18 = a16.generateTweens((com.cim.AIA.Paintable)this, (Object)a17, i10);
                    this.addTween((com.cim.AIA.Tween)a18);
                    com.cim.AIA.MultipleTween a19 = this.tweens;
                    int i11 = a19.getNumberOfTweens();
                    if(i11 > 0)
                    {
                        com.cim.AIA.MultipleTween a20 = this.tweens;
                        a20.run();
                    }
                    ShellSort a21 = this.shellSort;
                    a21.removeAllAnimationRequests();
                }
                this.repaint();
                break label3;
            }
            this.needInitialisation = true;
        }
    }
    
    static
    {
        String s = localization.Messages.getString("ShellSortCanvas.0");
        ShellSortCanvas.FIRST_POS_MARKER = s;
    }
}