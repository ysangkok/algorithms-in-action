package com.cim.AIA;

public class BinaryElement extends com.cim.AIA.VerticalBar {
    final public static java.awt.Color BORDER_COLOR;
    protected int bitLength;
    protected int yTextBuffer;
    
    public BinaryElement(int i, java.awt.Color a, java.awt.Color a0, int i0, java.awt.Point a1, int i1, int i2)
    {
        super(i, i0, a, a0, a1);
        this.drawValueMarker = false;
        this.bitLength = i2;
        this.yTextBuffer = i1;
    }
    
    public BinaryElement(int i, java.awt.Color a, int i0, java.awt.Point a0, int i1, int i2)
    {
        java.awt.Color a1 = com.cim.AIA.BinaryElement.BORDER_COLOR;
        super(i, i0, a, a1, a0);
        this.drawValueMarker = false;
        this.bitLength = i2;
        this.yTextBuffer = i1;
    }
    
    protected void drawElementValue(java.awt.Graphics a)
    {
        java.awt.Color a0 = this.borderColor;
        a.setColor(a0);
        java.awt.Point a1 = this.position;
        int i = a1.x;
        int i0 = this.borderThickness;
        int i1 = i0 / 2;
        int i2 = i - i1;
        java.awt.Point a2 = this.position;
        int i3 = a2.y;
        int i4 = this.borderThickness;
        int i5 = i4 / 2;
        int i6 = i3 - i5;
        int i7 = this.columYSpacing;
        int i8 = i6 - i7;
        int i9 = this.yTextBuffer;
        int i10 = i8 - i9;
        java.awt.FontMetrics a3 = a.getFontMetrics();
        int i11 = a3.getHeight();
        int i12 = this.yTextBuffer;
        int i13 = i11 + i12;
        int i14 = this.bitLength;
        int i15 = i13 * i14;
        int i16 = i10 - i15;
        int i17 = this.columWidth;
        int i18 = this.borderThickness;
        int i19 = i17 + i18;
        java.awt.FontMetrics a4 = a.getFontMetrics();
        int i20 = a4.getHeight();
        int i21 = this.yTextBuffer;
        int i22 = i20 + i21;
        int i23 = this.bitLength;
        int i24 = i22 * i23;
        int i25 = this.yTextBuffer;
        int i26 = i24 + i25;
        int i27 = this.borderThickness;
        int i28 = i26 + i27;
        a.fillRect(i2, i16, i19, i28);
        java.awt.Color a5 = this.color;
        a.setColor(a5);
        java.awt.Point a6 = this.position;
        int i29 = a6.x;
        java.awt.Point a7 = this.position;
        int i30 = a7.y;
        int i31 = this.columYSpacing;
        int i32 = i30 - i31;
        int i33 = this.yTextBuffer;
        int i34 = i32 - i33;
        java.awt.FontMetrics a8 = a.getFontMetrics();
        int i35 = a8.getHeight();
        int i36 = this.yTextBuffer;
        int i37 = i35 + i36;
        int i38 = this.bitLength;
        int i39 = i37 * i38;
        int i40 = i34 - i39;
        int i41 = this.columWidth;
        java.awt.FontMetrics a9 = a.getFontMetrics();
        int i42 = a9.getHeight();
        int i43 = this.yTextBuffer;
        int i44 = i42 + i43;
        int i45 = this.bitLength;
        int i46 = i44 * i45;
        int i47 = this.yTextBuffer;
        int i48 = i46 + i47;
        a.fillRect(i29, i40, i41, i48);
        java.awt.Color a10 = this.textColor;
        a.setColor(a10);
        String s = "";
        int i49 = 0;
        while(true)
        {
            int i50 = this.bitLength;
            if(i49 >= i50)
            {
                break;
            }
            else
            {
                StringBuilder a11 = new StringBuilder();
                StringBuilder a12 = a11.append(s);
                StringBuilder a13 = a12.append("0");
                String s0 = a13.toString();
                int i51 = i49 + 1;
                s = s0;
                i49 = i51;
            }
        }
        Object a14 = this.value;
        Integer dummy = (Integer)a14;
        Integer a15 = (Integer)a14;
        int i52 = a15.intValue();
        String s1 = Integer.toBinaryString(i52);
        StringBuilder a16 = new StringBuilder();
        int i53 = s.length();
        int i54 = s1.length();
        int i55 = i53 - i54;
        String s2 = s.substring(0, i55);
        StringBuilder a17 = a16.append(s2);
        StringBuilder a18 = a17.append(s1);
        String s3 = a18.toString();
        int i56 = s3.length();
        int i57 = i56 - 1;
        int i58 = i57;
        while(true)
        {
            if(i58 < 0)
            {
                return;
            }
            else
            {
                StringBuilder a19 = new StringBuilder();
                StringBuilder a20 = a19.append("");
                int i59 = s3.charAt(i58);
                StringBuilder a21 = a20.append((char)i59);
                String s4 = a21.toString();
                java.awt.Point a22 = this.position;
                int i60 = a22.x;
                int i61 = this.columWidth;
                int i62 = i61 / 2;
                int i63 = i60 + i62;
                java.awt.FontMetrics a23 = a.getFontMetrics();
                int i64 = a23.stringWidth(s4);
                int i65 = i64 / 2;
                int i66 = i63 - i65;
                java.awt.Point a24 = this.position;
                int i67 = a24.y;
                int i68 = this.columYSpacing;
                int i69 = i67 - i68;
                int i70 = this.yTextBuffer;
                int i71 = i69 - i70;
                int i72 = this.bitLength;
                int i73 = i72 - i58;
                int i74 = i73 - 1;
                int i75 = this.yTextBuffer;
                java.awt.FontMetrics a25 = a.getFontMetrics();
                int i76 = a25.getHeight();
                int i77 = i75 + i76;
                int i78 = i74 * i77;
                int i79 = i71 - i78;
                a.drawString(s4, i66, i79);
                int i80 = i58 + -1;
                i58 = i80;
            }
        }
    }
    
    public int getHeight(java.awt.Graphics a)
    {
        int i = 0;
        int i0 = this.drawValueMarker?1:0;
        int i1 = i0 == 0?0:1;
        int i2 = this.drawMarkers?1:0;
        if(i2 == 0)
        {
            i = i1;
        }
        else
        {
            java.util.Vector a0 = this.markers;
            int i3 = a0.size();
            int i4 = i1 + i3;
            i = i4;
        }
        int i5 = this.columYSpacing;
        int i6 = this.yTextBuffer;
        int i7 = i5 + i6;
        int i8 = this.bitLength;
        int i9 = i8 - 1;
        int i10 = this.yTextBuffer;
        java.awt.FontMetrics a1 = a.getFontMetrics();
        int i11 = a1.getHeight();
        int i12 = i10 + i11;
        int i13 = i9 * i12;
        int i14 = i7 + i13;
        int i15 = this.borderThickness;
        int i16 = i14 + i15;
        java.awt.FontMetrics a2 = a.getFontMetrics();
        int i17 = a2.getHeight();
        int i18 = i * i17;
        int i19 = i16 + i18;
        return i19;
    }
    
    public void setBitLength(int i)
    {
        this.bitLength = i;
    }
    
    static
    {
        java.awt.Color a = java.awt.Color.white;
        com.cim.AIA.BinaryElement.BORDER_COLOR = a;
    }
}