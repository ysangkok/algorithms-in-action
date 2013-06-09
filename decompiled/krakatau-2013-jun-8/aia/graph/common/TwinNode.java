package aia.graph.common;

public class TwinNode extends com.cim.AIA.Element {
    public static java.awt.Color DEFAULT_BACKGROUND_COLOR;
    final public static java.awt.Color DEFAULT_BORDER_COLOR;
    final public static java.awt.Color DEFAULT_TEXT_COLOR;
    final public static java.awt.Color DEFAULT_HIGHLIGHT_COLOR;
    protected java.awt.Point pos;
    protected int width;
    protected int height;
    protected boolean showObject;
    protected boolean isVisible;
    protected java.awt.Color highlightColor;
    protected java.awt.Color oldBackgroundColor;
    protected java.awt.Color backgroundColorLeft;
    protected java.awt.Color backgroundColorRight;
    protected java.awt.Color ringColor;
    protected java.awt.Color textColor;
    protected boolean drawMarkers;
    protected boolean markersBelowValue;
    protected int additionalMarkerSpacing;
    protected Integer m_l_value;
    protected Integer m_r_value;
    protected Integer m_priority;
    
    public TwinNode(Object a, Object a0, int i)
    {
        super(i, a);
        java.awt.Point a1 = new java.awt.Point(0, 0);
        this.pos = a1;
        this.width = 40;
        this.height = 20;
        this.showObject = true;
        this.isVisible = true;
        java.awt.Color a2 = aia.graph.common.TwinNode.DEFAULT_HIGHLIGHT_COLOR;
        this.highlightColor = a2;
        this.oldBackgroundColor = null;
        java.awt.Color a3 = new java.awt.Color(84, 183, 183);
        this.backgroundColorLeft = a3;
        java.awt.Color a4 = new java.awt.Color(55, 121, 121);
        this.backgroundColorRight = a4;
        java.awt.Color a5 = aia.graph.common.TwinNode.DEFAULT_BORDER_COLOR;
        this.ringColor = a5;
        java.awt.Color a6 = aia.graph.common.TwinNode.DEFAULT_TEXT_COLOR;
        this.textColor = a6;
        this.drawMarkers = true;
        this.markersBelowValue = true;
        this.additionalMarkerSpacing = 0;
        this.m_l_value = null;
        this.m_r_value = null;
        this.m_priority = null;
        Integer dummy = (Integer)a;
        Integer a7 = (Integer)a;
        this.m_l_value = a7;
        Integer dummy0 = (Integer)a0;
        Integer a8 = (Integer)a0;
        this.m_r_value = a8;
    }
    
    public int getLValue()
    {
        int i = 0;
        Integer a = this.m_l_value;
        if(a != null)
        {
            Integer a0 = this.m_l_value;
            int i0 = a0.intValue();
            i = i0;
        }
        else
        {
            i = -1;
        }
        return i;
    }
    
    public int getRValue()
    {
        int i = 0;
        Integer a = this.m_r_value;
        if(a != null)
        {
            Integer a0 = this.m_r_value;
            int i0 = a0.intValue();
            i = i0;
        }
        else
        {
            i = -1;
        }
        return i;
    }
    
    public static void setDefaultBackgroundColor(java.awt.Color a)
    {
        aia.graph.common.TwinNode.DEFAULT_BACKGROUND_COLOR = a;
    }
    
    public void showObject(boolean b)
    {
        this.showObject = b;
    }
    
    public boolean getShowObject()
    {
        int i = this.showObject?1:0;
        return i != 0;
    }
    
    public void setBackgroundColor(java.awt.Color a)
    {
    }
    
    public void setBackgroundColorLeft(java.awt.Color a)
    {
        this.backgroundColorLeft = a;
    }
    
    public void setBackgroundColorRight(java.awt.Color a)
    {
        this.backgroundColorRight = a;
    }
    
    public void setHighlightColor(java.awt.Color a)
    {
        this.highlightColor = a;
    }
    
    public void setTextColor(java.awt.Color a)
    {
        this.textColor = a;
    }
    
    public void setRingColor(java.awt.Color a)
    {
        this.ringColor = a;
    }
    
    public java.awt.Color getRingColor()
    {
        java.awt.Color a = this.ringColor;
        return a;
    }
    
    public java.awt.Color getBackgroundColor()
    {
        return null;
    }
    
    public java.awt.Color getBackgroundColorLeft()
    {
        java.awt.Color a = this.backgroundColorLeft;
        return a;
    }
    
    public java.awt.Color getBackgroundColorRight()
    {
        java.awt.Color a = this.backgroundColorRight;
        return a;
    }
    
    public java.awt.Color getHighlightColor()
    {
        java.awt.Color a = this.highlightColor;
        return a;
    }
    
    public java.awt.Color getTextColor()
    {
        java.awt.Color a = this.textColor;
        return a;
    }
    
    public String getDisplayString()
    {
        Object a = this.value;
        String s = a.toString();
        return s;
    }
    
    public String getLDisplayString()
    {
        String s = null;
        Integer a = this.m_l_value;
        if(a != null)
        {
            Integer a0 = this.m_l_value;
            String s0 = a0.toString();
            s = s0;
        }
        else
        {
            s = "n";
        }
        return s;
    }
    
    public String getRDisplayString()
    {
        String s = null;
        Integer a = this.m_r_value;
        if(a != null)
        {
            Integer a0 = this.m_r_value;
            String s0 = a0.toString();
            s = s0;
        }
        else
        {
            s = "n";
        }
        return s;
    }
    
    public void setWidth(int i)
    {
        this.width = i;
    }
    
    public void setHeight(int i)
    {
        this.height = i;
    }
    
    public int getWidth()
    {
        int i = this.width;
        return i;
    }
    
    public int getHeight()
    {
        int i = this.height;
        return i;
    }
    
    public void setIsVisible(boolean b)
    {
        this.isVisible = b;
    }
    
    public boolean getIsVisible()
    {
        int i = this.isVisible?1:0;
        return i != 0;
    }
    
    public void setAdditionalMarkerSpacing(int i)
    {
        this.additionalMarkerSpacing = i;
    }
    
    public int getAdditionalMarkerSpacing()
    {
        int i = this.additionalMarkerSpacing;
        return i;
    }
    
    public void setMarkersBelowValue(boolean b)
    {
        this.markersBelowValue = b;
    }
    
    public void setDrawMarkers(boolean b)
    {
        this.drawMarkers = b;
    }
    
    public void setPriority(int i)
    {
        Integer a = new Integer(i);
        this.m_priority = a;
    }
    
    public void setPosition(java.awt.Point a)
    {
        java.awt.Point a0 = this.pos;
        int i = a.x;
        a0.x = i;
        java.awt.Point a1 = this.pos;
        int i0 = a.y;
        a1.y = i0;
    }
    
    public java.awt.Point getPosition()
    {
        java.awt.Point a = this.pos;
        return a;
    }
    
    public void draw(java.awt.Graphics a, java.awt.Point a0)
    {
        this.setPosition(a0);
        this.draw(a);
    }
    
    public void draw(java.awt.Graphics a)
    {
        int i = this.isVisible?1:0;
        label1: {
            label0: {
                if(i != 0)
                {
                    break label0;
                }
                break label1;
            }
            java.awt.FontMetrics a0 = a.getFontMetrics();
            java.awt.Color a1 = a.getColor();
            java.awt.Color a2 = this.backgroundColorLeft;
            a.setColor(a2);
            java.awt.Point a3 = this.pos;
            int i0 = a3.x;
            int i1 = i0 + 1;
            java.awt.Point a4 = this.pos;
            int i2 = a4.y;
            int i3 = i2 + 1;
            int i4 = this.getWidth();
            int i5 = i4 / 2;
            int i6 = i5 - 1;
            int i7 = this.getHeight();
            int i8 = i7 - 1;
            a.fill3DRect(i1, i3, i6, i8, true);
            java.awt.Color a5 = this.backgroundColorRight;
            a.setColor(a5);
            java.awt.Point a6 = this.pos;
            int i9 = a6.x;
            int i10 = this.getWidth();
            int i11 = i10 / 2;
            int i12 = i9 + i11;
            int i13 = i12 + 2;
            java.awt.Point a7 = this.pos;
            int i14 = a7.y;
            int i15 = i14 + 1;
            int i16 = this.getWidth();
            int i17 = i16 / 2;
            int i18 = i17 - 1;
            int i19 = this.getHeight();
            int i20 = i19 - 1;
            a.fill3DRect(i13, i15, i18, i20, true);
            java.awt.Color a8 = java.awt.Color.black;
            a.setColor(a8);
            java.awt.Point a9 = this.pos;
            int i21 = a9.x;
            java.awt.Point a10 = this.pos;
            int i22 = a10.y;
            int i23 = this.getWidth();
            int i24 = this.getHeight();
            a.drawRect(i21, i22, i23, i24);
            java.awt.Point a11 = this.pos;
            int i25 = a11.x;
            int i26 = i25 - 1;
            java.awt.Point a12 = this.pos;
            int i27 = a12.y;
            int i28 = i27 - 1;
            int i29 = this.getWidth();
            int i30 = i29 + 2;
            int i31 = this.getHeight();
            int i32 = i31 + 2;
            a.drawRect(i26, i28, i30, i32);
            java.awt.Point a13 = this.pos;
            int i33 = a13.x;
            int i34 = this.getWidth();
            int i35 = i34 / 2;
            int i36 = i33 + i35;
            java.awt.Point a14 = this.pos;
            int i37 = a14.y;
            java.awt.Point a15 = this.pos;
            int i38 = a15.x;
            int i39 = this.getWidth();
            int i40 = i39 / 2;
            int i41 = i38 + i40;
            java.awt.Point a16 = this.pos;
            int i42 = a16.y;
            int i43 = this.getHeight();
            int i44 = i42 + i43;
            a.drawLine(i36, i37, i41, i44);
            int i45 = this.showObject?1:0;
            if(i45 != 0)
            {
                java.awt.Color a17 = this.textColor;
                a.setColor(a17);
                String s = this.getLDisplayString();
                java.awt.Point a18 = this.pos;
                int i46 = a18.x;
                int i47 = this.getWidth();
                int i48 = i47 / 4;
                int i49 = i46 + i48;
                int i50 = a0.stringWidth(s);
                int i51 = i50 / 2;
                int i52 = i49 - i51;
                java.awt.Point a19 = this.pos;
                int i53 = a19.y;
                int i54 = this.getHeight();
                int i55 = i54 / 2;
                int i56 = i53 + i55;
                int i57 = a0.getHeight();
                int i58 = i57 / 2;
                int i59 = i56 + i58;
                int i60 = i59 - 2;
                a.drawString(s, i52, i60);
                String s0 = this.getRDisplayString();
                java.awt.Point a20 = this.pos;
                int i61 = a20.x;
                int i62 = this.getWidth();
                int i63 = 3 * i62;
                int i64 = i63 / 4;
                int i65 = i61 + i64;
                int i66 = a0.stringWidth(s0);
                int i67 = i66 / 2;
                int i68 = i65 - i67;
                java.awt.Point a21 = this.pos;
                int i69 = a21.y;
                int i70 = this.getHeight();
                int i71 = i70 / 2;
                int i72 = i69 + i71;
                int i73 = a0.getHeight();
                int i74 = i73 / 2;
                int i75 = i72 + i74;
                int i76 = i75 - 2;
                a.drawString(s0, i68, i76);
            }
            Integer a22 = this.m_priority;
            if(a22 != null)
            {
                java.awt.Point a23 = this.pos;
                int i77 = a23.y;
                Integer a24 = this.m_priority;
                String s1 = a24.toString();
                int i78 = this.getHeight();
                int i79 = i77 + i78;
                java.awt.Color a25 = java.awt.Color.white;
                a.setColor(a25);
                java.awt.Point a26 = this.pos;
                int i80 = a26.x;
                int i81 = this.getWidth();
                int i82 = a0.stringWidth(s1);
                int i83 = i81 - i82;
                int i84 = i83 / 2;
                int i85 = i80 + i84;
                int i86 = i85 - 2;
                int i87 = a0.stringWidth(s1);
                int i88 = i87 + 4;
                int i89 = a0.getHeight();
                int i90 = i89 + 2;
                a.fillRect(i86, i79, i88, i90);
                java.awt.Color a27 = java.awt.Color.black;
                a.setColor(a27);
                java.awt.Point a28 = this.pos;
                int i91 = a28.x;
                int i92 = this.getWidth();
                int i93 = a0.stringWidth(s1);
                int i94 = i92 - i93;
                int i95 = i94 / 2;
                int i96 = i91 + i95;
                int i97 = i96 - 2;
                int i98 = a0.stringWidth(s1);
                int i99 = i98 + 4;
                int i100 = a0.getHeight();
                int i101 = i100 + 2;
                a.drawRect(i97, i79, i99, i101);
                int i102 = a0.getHeight();
                int i103 = i79 + i102;
                java.awt.Point a29 = this.pos;
                int i104 = a29.x;
                int i105 = this.getWidth();
                int i106 = a0.stringWidth(s1);
                int i107 = i105 - i106;
                int i108 = i107 / 2;
                int i109 = i104 + i108;
                a.drawString(s1, i109, i103);
            }
            a.setColor(a1);
        }
    }
    
    public void setX(int i)
    {
        java.awt.Point a = this.pos;
        a.x = i;
    }
    
    public void setY(int i)
    {
        java.awt.Point a = this.pos;
        a.y = i;
    }
    
    public int getX()
    {
        java.awt.Point a = this.pos;
        int i = a.x;
        return i;
    }
    
    public int getY()
    {
        java.awt.Point a = this.pos;
        int i = a.y;
        return i;
    }
    
    public void shiftEntire(int i, int i0)
    {
        java.awt.Point a = this.pos;
        int i1 = a.x;
        int i2 = i1 + i;
        a.x = i2;
        java.awt.Point a0 = this.pos;
        int i3 = a0.y;
        int i4 = i3 + i0;
        a0.y = i4;
    }
    
    public com.cim.AIA.Selectable getItemAt(java.awt.Point a)
    {
        java.awt.Point a0 = this.pos;
        int i = a0.x;
        java.awt.Point a1 = this.pos;
        int i0 = a1.y;
        int i1 = this.getWidth();
        int i2 = this.getHeight();
        java.awt.Rectangle a2 = new java.awt.Rectangle(i, i0, i1, i2);
        int i3 = a2.contains(a)?1:0;
        aia.graph.common.TwinNode a3 = i3 == 0?null:this;
        return (com.cim.AIA.Selectable)a3;
    }
    
    public boolean equals(com.cim.AIA.Selectable a)
    {
        int i = 0;
        label1: {
            Object a0 = null;
            label0: {
                int i0 = a instanceof com.cim.AIA.Node?1:0;
                a0 = a;
                if(i0 != 0)
                {
                    break label0;
                }
                i = 0;
                break label1;
            }
            com.cim.AIA.Node dummy = (com.cim.AIA.Node)a0;
            com.cim.AIA.Node a1 = (com.cim.AIA.Node)a0;
            int i1 = this.getIdentifier();
            int i2 = a1.getIdentifier();
            int i3 = i1 != i2?0:1;
            i = i3;
        }
        return i != 0;
    }
    
    public void highlight()
    {
    }
    
    public void unHighlight()
    {
    }
    
    static
    {
        java.awt.Color a = java.awt.Color.orange;
        aia.graph.common.TwinNode.DEFAULT_BACKGROUND_COLOR = a;
        java.awt.Color a0 = java.awt.Color.black;
        aia.graph.common.TwinNode.DEFAULT_BORDER_COLOR = a0;
        java.awt.Color a1 = java.awt.Color.black;
        aia.graph.common.TwinNode.DEFAULT_TEXT_COLOR = a1;
        java.awt.Color a2 = java.awt.Color.yellow;
        aia.graph.common.TwinNode.DEFAULT_HIGHLIGHT_COLOR = a2;
    }
}