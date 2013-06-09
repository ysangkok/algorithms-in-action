package com.cim.AIA;

public class VerticalBar extends com.cim.AIA.Element {
    final public static java.awt.Color HIGHLIGHT_COLOR;
    final public static java.awt.Color BORDER_COLOR;
    final public static int BORDER_THICKNESS = 4;
    final public static java.awt.Color TEXT_COLOR;
    final public static int COLUM_Y_SPACING = 0;
    final public static int COLUM_WIDTH = 10;
    protected boolean drawMarkers;
    protected int additionalMarkerSpacing;
    protected boolean drawValueMarker;
    protected boolean markersAboveValue;
    protected int offsetForValue;
    protected java.awt.Color highlightColor;
    protected java.awt.Color textColor;
    protected java.awt.Color borderColor;
    protected java.awt.Color oldColor;
    protected java.awt.Color color;
    protected java.awt.Point position;
    protected int borderThickness;
    protected int columWidth;
    protected int columYSpacing;
    protected float scaleFactor;
    
    public VerticalBar(int i, int i0, java.awt.Color a, java.awt.Color a0, java.awt.Point a1)
    {
        Integer a2 = new Integer(i0);
        super(i, (Object)a2);
        this.drawMarkers = true;
        this.additionalMarkerSpacing = 0;
        this.drawValueMarker = true;
        this.markersAboveValue = true;
        this.offsetForValue = 1;
        java.awt.Color a3 = com.cim.AIA.VerticalBar.HIGHLIGHT_COLOR;
        this.highlightColor = a3;
        java.awt.Color a4 = com.cim.AIA.VerticalBar.TEXT_COLOR;
        this.textColor = a4;
        java.awt.Color a5 = com.cim.AIA.VerticalBar.BORDER_COLOR;
        this.borderColor = a5;
        this.oldColor = null;
        this.borderThickness = 4;
        this.columWidth = 10;
        this.columYSpacing = 0;
        this.scaleFactor = 1.0f;
        this.color = a;
        this.borderColor = a0;
        this.position = a1;
    }
    
    public VerticalBar(int i, int i0, java.awt.Color a, java.awt.Point a0)
    {
        Integer a1 = new Integer(i0);
        super(i, (Object)a1);
        this.drawMarkers = true;
        this.additionalMarkerSpacing = 0;
        this.drawValueMarker = true;
        this.markersAboveValue = true;
        this.offsetForValue = 1;
        java.awt.Color a2 = com.cim.AIA.VerticalBar.HIGHLIGHT_COLOR;
        this.highlightColor = a2;
        java.awt.Color a3 = com.cim.AIA.VerticalBar.TEXT_COLOR;
        this.textColor = a3;
        java.awt.Color a4 = com.cim.AIA.VerticalBar.BORDER_COLOR;
        this.borderColor = a4;
        this.oldColor = null;
        this.borderThickness = 4;
        this.columWidth = 10;
        this.columYSpacing = 0;
        this.scaleFactor = 1.0f;
        this.color = a;
        java.awt.Color a5 = com.cim.AIA.VerticalBar.BORDER_COLOR;
        this.borderColor = a5;
        this.position = a0;
    }
    
    public void draw(java.awt.Graphics a)
    {
        java.awt.Color a0 = a.getColor();
        this.drawElementValue(a);
        java.awt.Color a1 = this.textColor;
        a.setColor(a1);
        int i = this.markersAboveValue?1:0;
        label1: {
            label0: {
                if(i != 0)
                {
                    break label0;
                }
                int i0 = this.drawValueMarker?1:0;
                if(i0 != 0)
                {
                    StringBuilder a2 = new StringBuilder();
                    Object a3 = this.value;
                    Integer dummy = (Integer)a3;
                    Integer a4 = (Integer)a3;
                    int i1 = a4.intValue();
                    StringBuilder a5 = a2.append(i1);
                    StringBuilder a6 = a5.append("");
                    String s = a6.toString();
                    java.awt.Point a7 = this.position;
                    int i2 = a7.x;
                    int i3 = this.columWidth;
                    int i4 = i3 / 2;
                    int i5 = i2 + i4;
                    java.awt.FontMetrics a8 = a.getFontMetrics();
                    StringBuilder a9 = new StringBuilder();
                    Object a10 = this.value;
                    Integer dummy0 = (Integer)a10;
                    Integer a11 = (Integer)a10;
                    int i6 = a11.intValue();
                    StringBuilder a12 = a9.append(i6);
                    StringBuilder a13 = a12.append("");
                    String s0 = a13.toString();
                    int i7 = a8.stringWidth(s0);
                    int i8 = i7 / 2;
                    int i9 = i5 - i8;
                    java.awt.Point a14 = this.position;
                    int i10 = a14.y;
                    int i11 = this.borderThickness;
                    int i12 = i10 + i11;
                    int i13 = this.offsetForValue;
                    java.awt.FontMetrics a15 = a.getFontMetrics();
                    int i14 = a15.getHeight();
                    int i15 = i13 * i14;
                    int i16 = i12 + i15;
                    a.drawString(s, i9, i16);
                }
                int i17 = this.drawMarkers?1:0;
                if(i17 == 0)
                {
                    break label1;
                }
                java.awt.Point a16 = this.position;
                int i18 = a16.y;
                int i19 = this.borderThickness;
                int i20 = i18 + i19;
                int i21 = i20;
                int i22 = 0;
                while(true)
                {
                    java.util.Vector a17 = this.markers;
                    int i23 = a17.size();
                    if(i22 >= i23)
                    {
                        break label1;
                    }
                    else
                    {
                        java.util.Vector a18 = this.markers;
                        Object a19 = a18.elementAt(i22);
                        com.cim.AIA.StringMarker dummy1 = (com.cim.AIA.StringMarker)a19;
                        com.cim.AIA.StringMarker a20 = (com.cim.AIA.StringMarker)a19;
                        int i24 = a20.getHeight(a);
                        int i25 = this.additionalMarkerSpacing;
                        int i26 = i24 + i25;
                        int i27 = i21 + i26;
                        java.awt.Point a21 = this.position;
                        int i28 = a21.x;
                        int i29 = this.columWidth;
                        int i30 = i29 / 2;
                        int i31 = i28 + i30;
                        java.awt.Point a22 = new java.awt.Point(i31, i27);
                        a20.setPosition(a22);
                        a20.setCenter(true);
                        a20.draw(a);
                        int i32 = i22 + 1;
                        i21 = i27;
                        i22 = i32;
                    }
                }
            }
            int i33 = this.drawMarkers?1:0;
            label2: {
                if(i33 == 0)
                {
                    break label2;
                }
                java.awt.Point a23 = this.position;
                int i34 = a23.y;
                int i35 = this.borderThickness;
                int i36 = i34 + i35;
                int i37 = i36;
                int i38 = 0;
                while(true)
                {
                    java.util.Vector a24 = this.markers;
                    int i39 = a24.size();
                    if(i38 >= i39)
                    {
                        break;
                    }
                    else
                    {
                        java.util.Vector a25 = this.markers;
                        Object a26 = a25.elementAt(i38);
                        com.cim.AIA.StringMarker dummy2 = (com.cim.AIA.StringMarker)a26;
                        com.cim.AIA.StringMarker a27 = (com.cim.AIA.StringMarker)a26;
                        int i40 = a27.getHeight(a);
                        int i41 = this.additionalMarkerSpacing;
                        int i42 = i40 + i41;
                        int i43 = i37 + i42;
                        java.awt.Point a28 = this.position;
                        int i44 = a28.x;
                        int i45 = this.columWidth;
                        int i46 = i45 / 2;
                        int i47 = i44 + i46;
                        java.awt.Point a29 = new java.awt.Point(i47, i43);
                        a27.setPosition(a29);
                        a27.setCenter(true);
                        a27.draw(a);
                        int i48 = i38 + 1;
                        i37 = i43;
                        i38 = i48;
                    }
                }
            }
            int i49 = this.offsetForValue;
            java.util.Vector a30 = this.markers;
            int i50 = a30.size();
            label3: {
                if(i49 > i50)
                {
                    break label3;
                }
                int i51 = this.drawValueMarker?1:0;
                if(i51 == 0)
                {
                    break label1;
                }
                else
                {
                    StringBuilder a31 = new StringBuilder();
                    Object a32 = this.value;
                    Integer dummy3 = (Integer)a32;
                    Integer a33 = (Integer)a32;
                    int i52 = a33.intValue();
                    StringBuilder a34 = a31.append(i52);
                    StringBuilder a35 = a34.append("");
                    String s1 = a35.toString();
                    java.awt.Point a36 = this.position;
                    int i53 = a36.x;
                    int i54 = this.columWidth;
                    int i55 = i54 / 2;
                    int i56 = i53 + i55;
                    java.awt.FontMetrics a37 = a.getFontMetrics();
                    StringBuilder a38 = new StringBuilder();
                    Object a39 = this.value;
                    Integer dummy4 = (Integer)a39;
                    Integer a40 = (Integer)a39;
                    int i57 = a40.intValue();
                    StringBuilder a41 = a38.append(i57);
                    StringBuilder a42 = a41.append("");
                    String s2 = a42.toString();
                    int i58 = a37.stringWidth(s2);
                    int i59 = i58 / 2;
                    int i60 = i56 - i59;
                    java.awt.Point a43 = this.position;
                    int i61 = a43.y;
                    int i62 = this.borderThickness;
                    int i63 = i61 + i62;
                    java.util.Vector a44 = this.markers;
                    int i64 = a44.size();
                    int i65 = i64 + 1;
                    java.awt.FontMetrics a45 = a.getFontMetrics();
                    int i66 = a45.getHeight();
                    int i67 = i65 * i66;
                    int i68 = i63 + i67;
                    a.drawString(s1, i60, i68);
                    break label1;
                }
            }
            int i69 = this.drawValueMarker?1:0;
            if(i69 != 0)
            {
                StringBuilder a46 = new StringBuilder();
                Object a47 = this.value;
                Integer dummy5 = (Integer)a47;
                Integer a48 = (Integer)a47;
                int i70 = a48.intValue();
                StringBuilder a49 = a46.append(i70);
                StringBuilder a50 = a49.append("");
                String s3 = a50.toString();
                java.awt.Point a51 = this.position;
                int i71 = a51.x;
                int i72 = this.columWidth;
                int i73 = i72 / 2;
                int i74 = i71 + i73;
                java.awt.FontMetrics a52 = a.getFontMetrics();
                StringBuilder a53 = new StringBuilder();
                Object a54 = this.value;
                Integer dummy6 = (Integer)a54;
                Integer a55 = (Integer)a54;
                int i75 = a55.intValue();
                StringBuilder a56 = a53.append(i75);
                StringBuilder a57 = a56.append("");
                String s4 = a57.toString();
                int i76 = a52.stringWidth(s4);
                int i77 = i76 / 2;
                int i78 = i74 - i77;
                java.awt.Point a58 = this.position;
                int i79 = a58.y;
                int i80 = this.borderThickness;
                int i81 = i79 + i80;
                int i82 = this.offsetForValue;
                java.awt.FontMetrics a59 = a.getFontMetrics();
                int i83 = a59.getHeight();
                int i84 = i82 * i83;
                int i85 = i81 + i84;
                a.drawString(s3, i78, i85);
            }
        }
        a.setColor(a0);
    }
    
    public void draw(java.awt.Graphics a, java.awt.Point a0)
    {
        this.setPosition(a0);
        this.draw(a);
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
        int i4 = this.getHeight();
        int i5 = i3 - i4;
        int i6 = this.borderThickness;
        int i7 = i6 / 2;
        int i8 = i5 - i7;
        int i9 = this.columYSpacing;
        int i10 = i8 - i9;
        int i11 = this.columWidth;
        int i12 = this.borderThickness;
        int i13 = i11 + i12;
        int i14 = this.getHeight();
        int i15 = this.borderThickness;
        int i16 = i14 + i15;
        a.fillRect(i2, i10, i13, i16);
        java.awt.Color a3 = this.color;
        a.setColor(a3);
        java.awt.Point a4 = this.position;
        int i17 = a4.x;
        java.awt.Point a5 = this.position;
        int i18 = a5.y;
        int i19 = this.getHeight();
        int i20 = i18 - i19;
        int i21 = this.columYSpacing;
        int i22 = i20 - i21;
        int i23 = this.columWidth;
        int i24 = this.getHeight();
        a.fillRect(i17, i22, i23, i24);
    }
    
    public boolean equals(com.cim.AIA.Selectable a)
    {
        int i = 0;
        label1: {
            Object a0 = null;
            label0: {
                int i0 = a instanceof com.cim.AIA.VerticalBar?1:0;
                a0 = a;
                if(i0 != 0)
                {
                    break label0;
                }
                i = 0;
                break label1;
            }
            com.cim.AIA.VerticalBar dummy = (com.cim.AIA.VerticalBar)a0;
            com.cim.AIA.VerticalBar a1 = (com.cim.AIA.VerticalBar)a0;
            int i1 = a1.getIdentifier();
            int i2 = this.getIdentifier();
            int i3 = i1 != i2?0:1;
            i = i3;
        }
        return i != 0;
    }
    
    public int getAdditionalMarkerSpacing()
    {
        int i = this.additionalMarkerSpacing;
        return i;
    }
    
    public java.awt.Color getBorderColor()
    {
        java.awt.Color a = this.borderColor;
        return a;
    }
    
    public int getBorderThickness()
    {
        int i = this.borderThickness;
        return i;
    }
    
    public java.awt.Color getColor()
    {
        java.awt.Color a = this.color;
        return a;
    }
    
    public int getColumYSpacing()
    {
        int i = this.columYSpacing;
        return i;
    }
    
    public int getHeight()
    {
        Object a = this.value;
        Integer dummy = (Integer)a;
        Integer a0 = (Integer)a;
        int i = a0.intValue();
        float f = (float)i;
        float f0 = this.scaleFactor;
        float f1 = f * f0;
        int i0 = (int)f1;
        return i0;
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
        int i5 = this.getHeight();
        int i6 = this.borderThickness;
        int i7 = i5 + i6;
        int i8 = this.columYSpacing;
        int i9 = i7 + i8;
        java.awt.FontMetrics a1 = a.getFontMetrics();
        int i10 = a1.getHeight();
        int i11 = this.additionalMarkerSpacing;
        int i12 = i10 + i11;
        int i13 = i * i12;
        int i14 = i9 + i13;
        return i14;
    }
    
    public java.awt.Color getHighlightColor()
    {
        java.awt.Color a = this.highlightColor;
        return a;
    }
    
    public com.cim.AIA.Selectable getItemAt(java.awt.Point a)
    {
        int i = this.borderThickness;
        int i0 = Math.max(4, i);
        java.awt.Point a0 = this.position;
        int i1 = a0.x;
        int i2 = i1 - i0;
        java.awt.Point a1 = this.position;
        int i3 = a1.y;
        Object a2 = this.value;
        Integer dummy = (Integer)a2;
        Integer a3 = (Integer)a2;
        int i4 = a3.intValue();
        int i5 = i3 - i4;
        int i6 = this.columYSpacing;
        int i7 = i5 - i6;
        int i8 = i7 - i0;
        int i9 = this.columWidth;
        int i10 = 2 * i0;
        int i11 = i9 + i10;
        Object a4 = this.value;
        Integer dummy0 = (Integer)a4;
        Integer a5 = (Integer)a4;
        int i12 = a5.intValue();
        int i13 = 2 * i0;
        int i14 = i12 + i13;
        java.awt.Rectangle a6 = new java.awt.Rectangle(i2, i8, i11, i14);
        int i15 = a6.contains(a)?1:0;
        com.cim.AIA.VerticalBar a7 = i15 == 0?null:this;
        return (com.cim.AIA.Selectable)a7;
    }
    
    public java.awt.Point getPosition()
    {
        java.awt.Point a = this.position;
        return a;
    }
    
    public int getRealOffsetForValue(java.awt.Graphics a)
    {
        int i = 0;
        int i0 = this.markersAboveValue?1:0;
        label1: {
            label0: {
                if(i0 != 0)
                {
                    break label0;
                }
                int i1 = this.offsetForValue;
                java.awt.FontMetrics a0 = a.getFontMetrics();
                int i2 = a0.getHeight();
                int i3 = i1 * i2;
                int i4 = this.borderThickness;
                int i5 = i3 + i4;
                i = i5;
                break label1;
            }
            int i6 = this.offsetForValue;
            java.util.Vector a1 = this.markers;
            int i7 = a1.size();
            if(i6 <= i7)
            {
                java.util.Vector a2 = this.markers;
                int i8 = a2.size();
                int i9 = i8 + 1;
                java.awt.FontMetrics a3 = a.getFontMetrics();
                int i10 = a3.getHeight();
                int i11 = i9 * i10;
                int i12 = this.borderThickness;
                int i13 = i11 + i12;
                i = i13;
            }
            else
            {
                int i14 = this.offsetForValue;
                java.awt.FontMetrics a4 = a.getFontMetrics();
                int i15 = a4.getHeight();
                int i16 = i14 * i15;
                int i17 = this.borderThickness;
                int i18 = i16 + i17;
                i = i18;
            }
        }
        return i;
    }
    
    public float getScaleFactor()
    {
        float f = this.scaleFactor;
        return f;
    }
    
    public java.awt.Color getTextColor()
    {
        java.awt.Color a = this.textColor;
        return a;
    }
    
    public int getWidth()
    {
        int i = this.columWidth;
        return i;
    }
    
    public int getX()
    {
        java.awt.Point a = this.position;
        int i = a.x;
        return i;
    }
    
    public int getY()
    {
        java.awt.Point a = this.position;
        int i = a.y;
        return i;
    }
    
    public void highlight()
    {
        java.awt.Color a = this.color;
        java.awt.Color a0 = this.highlightColor;
        label1: {
            label0: {
                if(a != a0)
                {
                    break label0;
                }
                break label1;
            }
            java.awt.Color a1 = this.oldColor;
            label3: {
                label2: {
                    if(a1 != null)
                    {
                        break label2;
                    }
                    java.awt.Color a2 = this.color;
                    this.oldColor = a2;
                    break label3;
                }
                java.awt.Color a3 = this.oldColor;
                java.awt.Color a4 = this.color;
                if(a3 != a4)
                {
                    java.awt.Color a5 = this.borderColor;
                    this.oldColor = a5;
                }
            }
            java.awt.Color a6 = this.highlightColor;
            this.color = a6;
        }
    }
    
    public void setAdditionalMarkerSpacing(int i)
    {
        this.additionalMarkerSpacing = i;
    }
    
    public void setBorderColor(java.awt.Color a)
    {
        this.borderColor = a;
    }
    
    public void setColor(java.awt.Color a)
    {
        this.color = a;
    }
    
    public void setColumYSpacing(int i)
    {
        this.columYSpacing = i;
    }
    
    public void setDrawMarkers(boolean b)
    {
        this.drawMarkers = b;
    }
    
    public void setDrawValueMarker(boolean b)
    {
        this.drawValueMarker = b;
    }
    
    public void setHeight(int i)
    {
        Integer a = new Integer(i);
        this.value = a;
    }
    
    public void setHighlightColor(java.awt.Color a)
    {
        this.highlightColor = a;
    }
    
    public void setMarkersAboveValue(boolean b)
    {
        this.markersAboveValue = b;
    }
    
    public void setObject(Integer a)
    {
        this.value = a;
    }
    
    public void setOffsetForValue(int i)
    {
        this.offsetForValue = i;
    }
    
    public void setPosition(java.awt.Point a)
    {
        java.awt.Point a0 = this.position;
        int i = a.x;
        a0.x = i;
        java.awt.Point a1 = this.position;
        int i0 = a.y;
        a1.y = i0;
    }
    
    public void setScaleFactor(float f)
    {
        this.scaleFactor = f;
    }
    
    public void setTextColor(java.awt.Color a)
    {
        this.textColor = a;
    }
    
    public void setThickness(int i)
    {
        this.borderThickness = i;
    }
    
    public void setWidth(int i)
    {
        this.columWidth = i;
    }
    
    public void setX(int i)
    {
        java.awt.Point a = this.position;
        a.x = i;
    }
    
    public void setY(int i)
    {
        java.awt.Point a = this.position;
        a.y = i;
    }
    
    public void shiftEntire(int i, int i0)
    {
        java.awt.Point a = this.position;
        int i1 = a.x;
        int i2 = i1 + i;
        a.x = i2;
        java.awt.Point a0 = this.position;
        int i3 = a0.y;
        int i4 = i3 + i0;
        a0.y = i4;
    }
    
    public void unHighlight()
    {
        java.awt.Color a = this.oldColor;
        if(a != null)
        {
            java.awt.Color a0 = this.oldColor;
            this.color = a0;
            this.oldColor = null;
        }
    }
    
    static
    {
        java.awt.Color a = java.awt.Color.yellow;
        com.cim.AIA.VerticalBar.HIGHLIGHT_COLOR = a;
        java.awt.Color a0 = java.awt.Color.black;
        com.cim.AIA.VerticalBar.BORDER_COLOR = a0;
        java.awt.Color a1 = java.awt.Color.black;
        com.cim.AIA.VerticalBar.TEXT_COLOR = a1;
    }
}