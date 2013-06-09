public class StraightRadixSortCanvas extends com.cim.AIA.AlgorithmCanvas {
    final private static long serialVersionUID = 5664402813801205935L;
    protected com.cim.AIA.ElementArray dataElementArray;
    protected com.cim.AIA.ElementArray symbolElementArray;
    protected com.cim.AIA.ElementArray auxiliaryElementArray;
    protected int xBuffer;
    protected int yBuffer;
    protected int spacingBetweenDataAndSymbolArray;
    protected int spacingBetweenSymbolAndAuxiliary;
    protected String dataElementArrayLabel;
    protected String symbolElementArrayLabel;
    protected String auxiliaryElementArrayLabel;
    protected String MSBLabel;
    protected String LSBLabel;
    protected int xTextBuffer;
    protected int yTextBuffer;
    protected java.awt.Font normalFont;
    protected java.awt.Color textColor;
    protected StraightRadixSort straightRadixSort;
    
    public StraightRadixSortCanvas()
    {
        super();
        this.xBuffer = 50;
        this.yBuffer = 100;
        this.spacingBetweenDataAndSymbolArray = 220;
        this.spacingBetweenSymbolAndAuxiliary = 100;
        String s = localization.Messages.getString("StraightRadixSortCanvas.0");
        this.dataElementArrayLabel = s;
        String s0 = localization.Messages.getString("StraightRadixSortCanvas.1");
        this.symbolElementArrayLabel = s0;
        String s1 = localization.Messages.getString("StraightRadixSortCanvas.2");
        this.auxiliaryElementArrayLabel = s1;
        String s2 = localization.Messages.getString("StraightRadixSortCanvas.3");
        this.MSBLabel = s2;
        String s3 = localization.Messages.getString("StraightRadixSortCanvas.4");
        this.LSBLabel = s3;
        this.xTextBuffer = 10;
        this.yTextBuffer = 5;
    }
    
    public void displayAlgorithm(java.awt.Graphics a)
    {
        StraightRadixSort a0 = this.straightRadixSort;
        label0: {
            if(a0 == null)
            {
                break label0;
            }
            java.awt.Font a1 = this.normalFont;
            if(a1 != null)
            {
                java.awt.Font a2 = this.normalFont;
                a.setFont(a2);
            }
            java.awt.Color a3 = this.textColor;
            if(a3 != null)
            {
                java.awt.Color a4 = this.textColor;
                a.setColor(a4);
            }
            StraightRadixSort a5 = this.straightRadixSort;
            int i = a5.getBit();
            com.cim.AIA.ElementArray a6 = this.dataElementArray;
            if(a6 != null)
            {
                StraightRadixSort a7 = this.straightRadixSort;
                int i0 = a7.getDataArrow();
                com.cim.AIA.ElementArray a8 = this.dataElementArray;
                int i1 = a8.getWidth();
                int i2 = this.xBuffer;
                int i3 = i1 + i2;
                java.awt.FontMetrics a9 = a.getFontMetrics();
                String s = this.auxiliaryElementArrayLabel;
                int i4 = a9.stringWidth(s);
                int i5 = i3 + i4;
                int i6 = this.xTextBuffer;
                int i7 = 2 * i6;
                int i8 = i5 + i7;
                java.awt.Dimension a10 = this.getSize();
                int i9 = a10.height;
                this.setSize(i8, i9);
                StraightRadixSort a11 = this.straightRadixSort;
                java.util.Vector a12 = a11.getSplitValues();
                com.cim.AIA.ElementArray a13 = this.dataElementArray;
                String s0 = this.dataElementArrayLabel;
                this.displayElementArray(a, a13, s0, 6, i0, a12);
                com.cim.AIA.ElementArray a14 = this.dataElementArray;
                a14.draw(a);
            }
            com.cim.AIA.ElementArray a15 = this.symbolElementArray;
            if(a15 != null)
            {
                com.cim.AIA.ElementArray a16 = this.symbolElementArray;
                java.awt.Point a17 = a16.getLocation();
                String s1 = this.symbolElementArrayLabel;
                int i10 = a17.x;
                com.cim.AIA.ElementArray a18 = this.symbolElementArray;
                int i11 = a18.getWidth();
                int i12 = i10 + i11;
                int i13 = this.xTextBuffer;
                int i14 = i12 + i13;
                int i15 = a17.y;
                com.cim.AIA.ElementArray a19 = this.symbolElementArray;
                int i16 = a19.getHeight();
                int i17 = i16 / 2;
                int i18 = i15 + i17;
                java.awt.FontMetrics a20 = a.getFontMetrics();
                int i19 = a20.getHeight();
                int i20 = i19 / 3;
                int i21 = i18 + i20;
                a.drawString(s1, i14, i21);
                com.cim.AIA.ElementArray a21 = this.symbolElementArray;
                a21.draw(a);
            }
            com.cim.AIA.ElementArray a22 = this.auxiliaryElementArray;
            if(a22 != null)
            {
                java.awt.FontMetrics a23 = a.getFontMetrics();
                int i22 = a23.getHeight();
                int i23 = 4 + i22;
                int i24 = 7 + i23;
                com.cim.AIA.ElementArray a24 = this.auxiliaryElementArray;
                String s2 = this.auxiliaryElementArrayLabel;
                this.displayElementArray(a, a24, s2, i24, -10, (java.util.Vector)null);
                com.cim.AIA.ElementArray a25 = this.auxiliaryElementArray;
                a25.draw(a);
            }
            StraightRadixSort a26 = this.straightRadixSort;
            int i25 = a26.getDataArrow();
            label1: {
                if(i25 == -10)
                {
                    break label1;
                }
                com.cim.AIA.ElementArray a27 = this.dataElementArray;
                com.cim.AIA.Element a28 = a27.getElement(i25);
                com.cim.AIA.VerticalBar dummy = (com.cim.AIA.VerticalBar)a28;
                com.cim.AIA.VerticalBar a29 = (com.cim.AIA.VerticalBar)a28;
                if(a29 != null)
                {
                    java.awt.Point a30 = a29.getPosition();
                    int i26 = a30.x;
                    int i27 = a29.getWidth();
                    int i28 = i27 / 2;
                    int i29 = i26 + i28;
                    int i30 = a30.y;
                    int i31 = a29.getHeight();
                    int i32 = i30 - i31;
                    int i33 = i32 - 2;
                    int i34 = i33 - 20;
                    int i35 = a30.x;
                    int i36 = a29.getWidth();
                    int i37 = i36 / 2;
                    int i38 = i35 + i37;
                    int i39 = a30.y;
                    int i40 = a29.getHeight();
                    int i41 = i39 - i40;
                    int i42 = i41 - 2;
                    com.cim.AIA.Line a31 = new com.cim.AIA.Line(i29, i34, i38, i42);
                    a31.showArrowHead(true);
                    a31.setDistanceFromStartForArrowHead(-3);
                    a31.draw(a);
                }
            }
            StraightRadixSort a32 = this.straightRadixSort;
            int i43 = a32.getAuxiliaryArrow();
            if(i43 == -10)
            {
                break label0;
            }
            com.cim.AIA.ElementArray a33 = this.auxiliaryElementArray;
            com.cim.AIA.Element a34 = a33.getElement(i43);
            com.cim.AIA.Node dummy0 = (com.cim.AIA.Node)a34;
            com.cim.AIA.Node a35 = (com.cim.AIA.Node)a34;
            com.cim.AIA.ElementArray a36 = this.dataElementArray;
            com.cim.AIA.Element a37 = a36.getElement(i43);
            com.cim.AIA.VerticalBar dummy1 = (com.cim.AIA.VerticalBar)a37;
            com.cim.AIA.VerticalBar a38 = (com.cim.AIA.VerticalBar)a37;
            if(a35 == null)
            {
                break label0;
            }
            if(a38 != null)
            {
                java.awt.Point a39 = a38.getPosition();
                java.awt.Point a40 = a35.getPosition();
                com.cim.AIA.ElementArray a41 = this.dataElementArray;
                java.awt.Point a42 = a41.getLocation();
                StraightRadixSort a43 = this.straightRadixSort;
                int i44 = a43.getBitLength();
                int i45 = a40.x;
                int i46 = a35.getWidth();
                int i47 = i46 / 2;
                int i48 = i45 + i47;
                int i49 = a40.y;
                int i50 = a40.x;
                int i51 = a35.getWidth();
                int i52 = i51 / 2;
                int i53 = i50 + i52;
                int i54 = a42.y;
                int i55 = i54 + 4;
                int i56 = i55 + 2;
                int i57 = i56 - 2;
                java.awt.FontMetrics a44 = a.getFontMetrics();
                int i58 = a44.getHeight();
                int i59 = 4 + i58;
                int i60 = i44 + 2;
                int i61 = i59 * i60;
                int i62 = i57 + i61;
                com.cim.AIA.Line a45 = new com.cim.AIA.Line(i48, i49, i53, i62);
                a45.showArrowHead(true);
                a45.setDistanceFromStartForArrowHead(-3);
                a45.draw(a);
            }
        }
    }
    
    protected void displayElementArray(java.awt.Graphics a, com.cim.AIA.ElementArray a0, String s, int i, int i0, java.util.Vector a1)
    {
        label0: {
            if(a0 == null)
            {
                break label0;
            }
            java.awt.Point a2 = a0.getLocation();
            int i1 = a2.x;
            int i2 = a0.getWidth();
            int i3 = i1 + i2;
            int i4 = this.xTextBuffer;
            int i5 = i3 + i4;
            int i6 = a2.y;
            a.drawString(s, i5, i6);
            String s0 = this.MSBLabel;
            int i7 = a2.x;
            int i8 = this.xTextBuffer;
            int i9 = i7 - i8;
            java.awt.FontMetrics a3 = a.getFontMetrics();
            String s1 = this.MSBLabel;
            int i10 = a3.stringWidth(s1);
            int i11 = i9 - i10;
            int i12 = a2.y;
            int i13 = i12 + i;
            java.awt.FontMetrics a4 = a.getFontMetrics();
            int i14 = a4.getHeight();
            int i15 = i13 + i14;
            java.awt.FontMetrics a5 = a.getFontMetrics();
            int i16 = a5.getHeight();
            int i17 = 4 + i16;
            int i18 = i17 * 2;
            int i19 = i15 + i18;
            a.drawString(s0, i11, i19);
            String s2 = this.LSBLabel;
            int i20 = a2.x;
            int i21 = this.xTextBuffer;
            int i22 = i20 - i21;
            java.awt.FontMetrics a6 = a.getFontMetrics();
            String s3 = this.LSBLabel;
            int i23 = a6.stringWidth(s3);
            int i24 = i22 - i23;
            int i25 = a2.y;
            int i26 = i25 + i;
            java.awt.FontMetrics a7 = a.getFontMetrics();
            int i27 = a7.getHeight();
            int i28 = i26 + i27;
            java.awt.FontMetrics a8 = a.getFontMetrics();
            int i29 = a8.getHeight();
            int i30 = 4 + i29;
            StraightRadixSort a9 = this.straightRadixSort;
            int i31 = a9.getBitLength();
            int i32 = i31 - 1;
            int i33 = i32 + 2;
            int i34 = i30 * i33;
            int i35 = i28 + i34;
            a.drawString(s2, i24, i35);
            int i36 = 0;
            while(true)
            {
                StraightRadixSort a10 = this.straightRadixSort;
                int i37 = a10.getBitLength();
                if(i36 >= i37)
                {
                    break;
                }
                else
                {
                    java.awt.Color a11 = a.getColor();
                    StraightRadixSort a12 = this.straightRadixSort;
                    java.awt.Color a13 = a12.getColor(i36);
                    a.setColor(a13);
                    int i38 = a2.x;
                    int i39 = a2.y;
                    int i40 = i39 + i;
                    int i41 = i40 - 2;
                    java.awt.FontMetrics a14 = a.getFontMetrics();
                    int i42 = a14.getHeight();
                    int i43 = 4 + i42;
                    int i44 = i36 + 2;
                    int i45 = i43 * i44;
                    int i46 = i41 + i45;
                    int i47 = a0.getWidth();
                    java.awt.FontMetrics a15 = a.getFontMetrics();
                    int i48 = a15.getHeight();
                    int i49 = i48 + 4;
                    a.fillRect(i38, i46, i47, i49);
                    a.setColor(a11);
                    int i50 = i36 + 1;
                    i36 = i50;
                }
            }
            label1: {
                if(a1 == null)
                {
                    break label1;
                }
                int i51 = a0.getElementWidth();
                int i52 = a0.getColumGap();
                int i53 = i51 + i52;
                java.awt.FontMetrics a16 = a.getFontMetrics();
                int i54 = a16.getHeight();
                int i55 = i54 + 4;
                int i56 = 0;
                while(true)
                {
                    int i57 = a1.size();
                    if(i56 >= i57)
                    {
                        break;
                    }
                    else
                    {
                        Object a17 = a1.elementAt(i56);
                        SplitMarker dummy = (SplitMarker)a17;
                        SplitMarker a18 = (SplitMarker)a17;
                        int i58 = a2.x;
                        int i59 = a18.getPosition();
                        int i60 = i59 * i53;
                        int i61 = i58 + i60;
                        int i62 = a0.getColumGap();
                        int i63 = i62 / 2;
                        int i64 = i61 - i63;
                        int i65 = a2.y;
                        int i66 = i65 + i;
                        int i67 = i66 - 2;
                        java.awt.FontMetrics a19 = a.getFontMetrics();
                        int i68 = a19.getHeight();
                        int i69 = 4 + i68;
                        int i70 = a18.getLevel();
                        int i71 = i70 + 2;
                        int i72 = i69 * i71;
                        int i73 = i67 + i72;
                        int i74 = i73 + i55;
                        com.cim.AIA.Line a20 = new com.cim.AIA.Line(i64, i73, i64, i74);
                        java.awt.Color a21 = this.getBackground();
                        a20.setColor(a21);
                        a20.draw(a);
                        int i75 = i56 + 1;
                        i56 = i75;
                    }
                }
            }
            StraightRadixSort a22 = this.straightRadixSort;
            int i76 = a22.getBit();
            if(i76 == -10)
            {
                break label0;
            }
            if(i76 >= 0)
            {
                java.awt.Color a23 = java.awt.Color.black;
                a.setColor(a23);
                int i77 = a2.x;
                int i78 = a2.y;
                int i79 = i78 + i;
                int i80 = i79 - 2;
                java.awt.FontMetrics a24 = a.getFontMetrics();
                int i81 = a24.getHeight();
                int i82 = 4 + i81;
                int i83 = i76 + 2;
                int i84 = i82 * i83;
                int i85 = i80 + i84;
                int i86 = a0.getWidth();
                int i87 = i86 - 1;
                java.awt.FontMetrics a25 = a.getFontMetrics();
                int i88 = a25.getHeight();
                int i89 = i88 + 4;
                a.drawRect(i77, i85, i87, i89);
            }
        }
        StraightRadixSort a26 = this.straightRadixSort;
        int i90 = a26.getBit();
        label2: {
            if(i0 == -10)
            {
                break label2;
            }
            if(i90 == -10)
            {
                break label2;
            }
            if(i90 < 0)
            {
                break label2;
            }
            java.awt.Point a27 = a0.getLocation();
            com.cim.AIA.Element a28 = a0.getElement(i0);
            if(a28 != null)
            {
                java.awt.Color a29 = java.awt.Color.black;
                a.setColor(a29);
                java.awt.Point a30 = a28.getPosition();
                int i91 = a30.x;
                int i92 = i91 + 1;
                int i93 = a27.y;
                int i94 = i93 + 1;
                int i95 = i94 + i;
                int i96 = i95 - 2;
                java.awt.FontMetrics a31 = a.getFontMetrics();
                int i97 = a31.getHeight();
                int i98 = 4 + i97;
                int i99 = i90 + 2;
                int i100 = i98 * i99;
                int i101 = i96 + i100;
                int i102 = a28.getWidth();
                int i103 = i102 - 3;
                java.awt.FontMetrics a32 = a.getFontMetrics();
                int i104 = a32.getHeight();
                int i105 = i104 + 4;
                int i106 = i105 - 2;
                a.drawRect(i92, i101, i103, i106);
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
            java.util.Vector a1 = this.drawables;
            a1.removeAllElements();
            Object a2 = a.paramObj;
            StraightRadixSort dummy = (StraightRadixSort)a2;
            StraightRadixSort a3 = (StraightRadixSort)a2;
            this.straightRadixSort = a3;
            StraightRadixSort a4 = this.straightRadixSort;
            com.cim.AIA.ElementArray a5 = a4.getDataElementArray();
            this.dataElementArray = a5;
            com.cim.AIA.ElementArray a6 = this.dataElementArray;
            if(a6 != null)
            {
                com.cim.AIA.ElementArray a7 = this.dataElementArray;
                int i = this.xBuffer;
                int i0 = this.yBuffer;
                a7.setLocation(i, i0);
            }
            StraightRadixSort a8 = this.straightRadixSort;
            com.cim.AIA.ElementArray a9 = a8.getAuxiliaryElementArray();
            this.auxiliaryElementArray = a9;
            com.cim.AIA.ElementArray a10 = this.auxiliaryElementArray;
            if(a10 != null)
            {
                com.cim.AIA.ElementArray a11 = this.auxiliaryElementArray;
                int i1 = this.xBuffer;
                int i2 = i1 - 2;
                int i3 = this.yBuffer;
                int i4 = this.spacingBetweenDataAndSymbolArray;
                int i5 = i3 + i4;
                int i6 = this.spacingBetweenSymbolAndAuxiliary;
                int i7 = i5 + i6;
                a11.setLocation(i2, i7);
            }
            StraightRadixSort a12 = this.straightRadixSort;
            com.cim.AIA.ElementArray a13 = a12.getSymbolElementArray();
            this.symbolElementArray = a13;
            com.cim.AIA.ElementArray a14 = this.symbolElementArray;
            label1: {
                if(a14 == null)
                {
                    break label1;
                }
                com.cim.AIA.ElementArray a15 = this.dataElementArray;
                if(a15 != null)
                {
                    com.cim.AIA.ElementArray a16 = this.symbolElementArray;
                    int i8 = this.xBuffer;
                    com.cim.AIA.ElementArray a17 = this.dataElementArray;
                    int i9 = a17.getWidth();
                    int i10 = i9 / 2;
                    com.cim.AIA.ElementArray a18 = this.symbolElementArray;
                    int i11 = a18.getWidth();
                    int i12 = i11 / 2;
                    int i13 = i10 - i12;
                    int i14 = i8 + i13;
                    int i15 = this.yBuffer;
                    int i16 = this.spacingBetweenDataAndSymbolArray;
                    int i17 = i15 + i16;
                    a16.setLocation(i14, i17);
                }
            }
            com.cim.AIA.ElementArray a19 = this.symbolElementArray;
            label2: {
                if(a19 == null)
                {
                    break label2;
                }
                com.cim.AIA.ElementArray a20 = this.auxiliaryElementArray;
                if(a20 != null)
                {
                    StraightRadixSort a21 = this.straightRadixSort;
                    com.cim.AIA.ElementArray a22 = this.symbolElementArray;
                    com.cim.AIA.ElementArray a23 = this.auxiliaryElementArray;
                    a21.calculateLines((com.cim.AIA.Paintable)this, a22, a23);
                }
            }
            this.repaint();
        }
    }
}