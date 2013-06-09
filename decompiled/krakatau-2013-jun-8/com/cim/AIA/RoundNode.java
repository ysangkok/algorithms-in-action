package com.cim.AIA;

public class RoundNode extends com.cim.AIA.Node {
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
    protected java.awt.Color backgroundColor;
    protected java.awt.Color ringColor;
    protected java.awt.Color textColor;
    protected boolean drawMarkers;
    protected boolean markersBelowValue;
    protected int additionalMarkerSpacing;
    
    public static void setDefaultBackgroundColor(java.awt.Color a)
    {
        com.cim.AIA.RoundNode.DEFAULT_BACKGROUND_COLOR = a;
    }
    
    public RoundNode(Object a, int i)
    {
        super(a, i);
        java.awt.Point a0 = new java.awt.Point(0, 0);
        this.pos = a0;
        this.width = 20;
        this.height = 20;
        this.showObject = true;
        this.isVisible = true;
        java.awt.Color a1 = com.cim.AIA.RoundNode.DEFAULT_HIGHLIGHT_COLOR;
        this.highlightColor = a1;
        this.oldBackgroundColor = null;
        java.awt.Color a2 = com.cim.AIA.RoundNode.DEFAULT_BACKGROUND_COLOR;
        this.backgroundColor = a2;
        java.awt.Color a3 = com.cim.AIA.RoundNode.DEFAULT_BORDER_COLOR;
        this.ringColor = a3;
        java.awt.Color a4 = com.cim.AIA.RoundNode.DEFAULT_TEXT_COLOR;
        this.textColor = a4;
        this.drawMarkers = true;
        this.markersBelowValue = true;
        this.additionalMarkerSpacing = 0;
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
            java.awt.Color a2 = this.ringColor;
            a.setColor(a2);
            java.awt.Point a3 = this.pos;
            int i0 = a3.x;
            java.awt.Point a4 = this.pos;
            int i1 = a4.y;
            int i2 = this.width;
            int i3 = this.height;
            a.drawOval(i0, i1, i2, i3);
            java.awt.Point a5 = this.pos;
            int i4 = a5.x;
            int i5 = i4 + 1;
            java.awt.Point a6 = this.pos;
            int i6 = a6.y;
            int i7 = i6 + 1;
            int i8 = this.width;
            int i9 = this.height;
            a.drawOval(i5, i7, i8, i9);
            java.awt.Point a7 = this.pos;
            int i10 = a7.x;
            int i11 = i10 + 1;
            java.awt.Point a8 = this.pos;
            int i12 = a8.y;
            int i13 = i12 + 1;
            int i14 = this.width;
            int i15 = i14 - 1;
            int i16 = this.height;
            int i17 = i16 - 1;
            a.drawOval(i11, i13, i15, i17);
            java.awt.Color a9 = this.backgroundColor;
            a.setColor(a9);
            java.awt.Point a10 = this.pos;
            int i18 = a10.x;
            int i19 = i18 + 2;
            java.awt.Point a11 = this.pos;
            int i20 = a11.y;
            int i21 = i20 + 2;
            int i22 = this.width;
            int i23 = i22 - 2;
            int i24 = this.height;
            int i25 = i24 - 2;
            a.fillOval(i19, i21, i23, i25);
            int i26 = this.showObject?1:0;
            if(i26 != 0)
            {
                java.awt.Color a12 = this.textColor;
                a.setColor(a12);
                String s = this.getDisplayString();
                java.awt.Point a13 = this.pos;
                int i27 = a13.x;
                int i28 = this.width;
                int i29 = i28 / 2;
                int i30 = i27 + i29;
                int i31 = a0.stringWidth(s);
                int i32 = i31 / 2;
                int i33 = i30 - i32;
                java.awt.Point a14 = this.pos;
                int i34 = a14.y;
                int i35 = this.height;
                int i36 = i35 / 2;
                int i37 = i34 + i36;
                int i38 = a0.getHeight();
                int i39 = i38 / 2;
                int i40 = i37 + i39;
                int i41 = i40 - 2;
                a.drawString(s, i33, i41);
            }
            int i42 = this.drawMarkers?1:0;
            label2: {
                int i43 = 0;
                if(i42 == 0)
                {
                    break label2;
                }
                java.awt.Point a15 = this.pos;
                int i44 = a15.y;
                int i45 = this.markersBelowValue?1:0;
                if(i45 == 0)
                {
                    java.awt.FontMetrics a16 = a.getFontMetrics();
                    int i46 = a16.getHeight();
                    int i47 = i44 + i46;
                    i43 = i47;
                }
                else
                {
                    int i48 = this.height;
                    int i49 = i44 + i48;
                    i43 = i49;
                }
                int i50 = i43;
                int i51 = 0;
                while(true)
                {
                    int i52 = 0;
                    java.util.Vector a17 = this.markers;
                    int i53 = a17.size();
                    if(i51 >= i53)
                    {
                        break;
                    }
                    java.util.Vector a18 = this.markers;
                    Object a19 = a18.elementAt(i51);
                    com.cim.AIA.StringMarker dummy = (com.cim.AIA.StringMarker)a19;
                    com.cim.AIA.StringMarker a20 = (com.cim.AIA.StringMarker)a19;
                    int i54 = this.markersBelowValue?1:0;
                    if(i54 == 0)
                    {
                        int i55 = a20.getHeight(a);
                        int i56 = this.additionalMarkerSpacing;
                        int i57 = i55 + i56;
                        int i58 = i50 - i57;
                        i52 = i58;
                    }
                    else
                    {
                        int i59 = a20.getHeight(a);
                        int i60 = this.additionalMarkerSpacing;
                        int i61 = i59 + i60;
                        int i62 = i50 + i61;
                        i52 = i62;
                    }
                    java.awt.Point a21 = this.pos;
                    int i63 = a21.x;
                    int i64 = this.width;
                    int i65 = i64 / 2;
                    int i66 = i63 + i65;
                    java.awt.Point a22 = new java.awt.Point(i66, i52);
                    a20.setPosition(a22);
                    a20.setCenter(true);
                    a20.draw(a);
                    int i67 = i51 + 1;
                    i50 = i52;
                    i51 = i67;
                }
            }
            a.setColor(a1);
        }
    }
    
    public void draw(java.awt.Graphics a, java.awt.Point a0)
    {
        this.setPosition(a0);
        this.draw(a);
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
            com.cim.AIA.RoundNode dummy = (com.cim.AIA.RoundNode)a0;
            com.cim.AIA.RoundNode a1 = (com.cim.AIA.RoundNode)a0;
            int i1 = this.getIdentifier();
            int i2 = a1.getIdentifier();
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
    
    public java.awt.Color getBackgroundColor()
    {
        java.awt.Color a = this.backgroundColor;
        return a;
    }
    
    public String getDisplayString()
    {
        Object a = this.value;
        String s = a.toString();
        return s;
    }
    
    public int getHeight()
    {
        int i = this.height;
        return i;
    }
    
    public java.awt.Color getHighlightColor()
    {
        java.awt.Color a = this.highlightColor;
        return a;
    }
    
    public boolean getIsVisible()
    {
        int i = this.isVisible?1:0;
        return i != 0;
    }
    
    public com.cim.AIA.Selectable getItemAt(java.awt.Point a)
    {
        java.awt.Point a0 = this.pos;
        int i = a0.x;
        java.awt.Point a1 = this.pos;
        int i0 = a1.y;
        int i1 = this.width;
        int i2 = this.height;
        java.awt.Rectangle a2 = new java.awt.Rectangle(i, i0, i1, i2);
        int i3 = a2.contains(a)?1:0;
        com.cim.AIA.RoundNode a3 = i3 == 0?null:this;
        return (com.cim.AIA.Selectable)a3;
    }
    
    public java.awt.Point getPosition()
    {
        java.awt.Point a = this.pos;
        return a;
    }
    
    public java.awt.Color getRingColor()
    {
        java.awt.Color a = this.ringColor;
        return a;
    }
    
    public boolean getShowObject()
    {
        int i = this.showObject?1:0;
        return i != 0;
    }
    
    public java.awt.Color getTextColor()
    {
        java.awt.Color a = this.textColor;
        return a;
    }
    
    public int getWidth()
    {
        int i = this.width;
        return i;
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
    
    public void highlight()
    {
        java.awt.Color a = this.backgroundColor;
        java.awt.Color a0 = this.highlightColor;
        if(a != a0)
        {
            java.awt.Color a1 = this.backgroundColor;
            this.oldBackgroundColor = a1;
            java.awt.Color a2 = this.highlightColor;
            this.backgroundColor = a2;
        }
    }
    
    public void setAdditionalMarkerSpacing(int i)
    {
        this.additionalMarkerSpacing = i;
    }
    
    public void setBackgroundColor(java.awt.Color a)
    {
        this.backgroundColor = a;
    }
    
    public void setDrawMarkers(boolean b)
    {
        this.drawMarkers = b;
    }
    
    public void setHeight(int i)
    {
        this.height = i;
    }
    
    public void setHighlightColor(java.awt.Color a)
    {
        this.highlightColor = a;
    }
    
    public void setIsVisible(boolean b)
    {
        this.isVisible = b;
    }
    
    public void setMarkersBelowValue(boolean b)
    {
        this.markersBelowValue = b;
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
    
    public void setRingColor(java.awt.Color a)
    {
        this.ringColor = a;
    }
    
    public void setTextColor(java.awt.Color a)
    {
        this.textColor = a;
    }
    
    public void setWidth(int i)
    {
        this.width = i;
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
    
    public void showObject(boolean b)
    {
        this.showObject = b;
    }
    
    public void unHighlight()
    {
        java.awt.Color a = this.oldBackgroundColor;
        if(a != null)
        {
            java.awt.Color a0 = this.oldBackgroundColor;
            this.backgroundColor = a0;
            this.oldBackgroundColor = null;
        }
    }
    
    static
    {
        java.awt.Color a = java.awt.Color.orange;
        com.cim.AIA.RoundNode.DEFAULT_BACKGROUND_COLOR = a;
        java.awt.Color a0 = java.awt.Color.black;
        com.cim.AIA.RoundNode.DEFAULT_BORDER_COLOR = a0;
        java.awt.Color a1 = java.awt.Color.black;
        com.cim.AIA.RoundNode.DEFAULT_TEXT_COLOR = a1;
        java.awt.Color a2 = java.awt.Color.yellow;
        com.cim.AIA.RoundNode.DEFAULT_HIGHLIGHT_COLOR = a2;
    }
}