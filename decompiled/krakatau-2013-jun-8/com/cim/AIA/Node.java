package com.cim.AIA;

public class Node extends com.cim.AIA.Element {
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
        com.cim.AIA.Node.DEFAULT_BACKGROUND_COLOR = a;
    }
    
    public Node(Object a, int i)
    {
        super(i, a);
        java.awt.Point a0 = new java.awt.Point(0, 0);
        this.pos = a0;
        this.width = 20;
        this.height = 20;
        this.showObject = true;
        this.isVisible = true;
        java.awt.Color a1 = com.cim.AIA.Node.DEFAULT_HIGHLIGHT_COLOR;
        this.highlightColor = a1;
        this.oldBackgroundColor = null;
        java.awt.Color a2 = com.cim.AIA.Node.DEFAULT_BACKGROUND_COLOR;
        this.backgroundColor = a2;
        java.awt.Color a3 = com.cim.AIA.Node.DEFAULT_BORDER_COLOR;
        this.ringColor = a3;
        java.awt.Color a4 = com.cim.AIA.Node.DEFAULT_TEXT_COLOR;
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
            int i1 = i0 + 1;
            java.awt.Point a4 = this.pos;
            int i2 = a4.y;
            int i3 = i2 + 1;
            int i4 = this.getWidth();
            int i5 = i4 - 1;
            int i6 = this.getHeight();
            int i7 = i6 - 1;
            a.draw3DRect(i1, i3, i5, i7, false);
            java.awt.Color a5 = this.backgroundColor;
            a.setColor(a5);
            java.awt.Point a6 = this.pos;
            int i8 = a6.x;
            int i9 = i8 + 2;
            java.awt.Point a7 = this.pos;
            int i10 = a7.y;
            int i11 = i10 + 2;
            int i12 = this.getWidth();
            int i13 = i12 - 2;
            int i14 = this.getHeight();
            int i15 = i14 - 2;
            a.fill3DRect(i9, i11, i13, i15, true);
            int i16 = this.showObject?1:0;
            if(i16 != 0)
            {
                java.awt.Color a8 = this.textColor;
                a.setColor(a8);
                String s = this.getDisplayString();
                java.awt.Point a9 = this.pos;
                int i17 = a9.x;
                int i18 = this.getWidth();
                int i19 = i18 / 2;
                int i20 = i17 + i19;
                int i21 = a0.stringWidth(s);
                int i22 = i21 / 2;
                int i23 = i20 - i22;
                java.awt.Point a10 = this.pos;
                int i24 = a10.y;
                int i25 = this.getHeight();
                int i26 = i25 / 2;
                int i27 = i24 + i26;
                int i28 = a0.getHeight();
                int i29 = i28 / 2;
                int i30 = i27 + i29;
                int i31 = i30 - 2;
                a.drawString(s, i23, i31);
            }
            int i32 = this.drawMarkers?1:0;
            label2: {
                int i33 = 0;
                if(i32 == 0)
                {
                    break label2;
                }
                java.awt.Point a11 = this.pos;
                int i34 = a11.y;
                int i35 = this.markersBelowValue?1:0;
                if(i35 == 0)
                {
                    java.awt.FontMetrics a12 = a.getFontMetrics();
                    int i36 = a12.getHeight();
                    int i37 = i34 + i36;
                    i33 = i37;
                }
                else
                {
                    int i38 = this.getHeight();
                    int i39 = i34 + i38;
                    i33 = i39;
                }
                int i40 = i33;
                int i41 = 0;
                while(true)
                {
                    int i42 = 0;
                    java.util.Vector a13 = this.markers;
                    int i43 = a13.size();
                    if(i41 >= i43)
                    {
                        break;
                    }
                    java.util.Vector a14 = this.markers;
                    Object a15 = a14.elementAt(i41);
                    com.cim.AIA.StringMarker dummy = (com.cim.AIA.StringMarker)a15;
                    com.cim.AIA.StringMarker a16 = (com.cim.AIA.StringMarker)a15;
                    int i44 = this.markersBelowValue?1:0;
                    if(i44 == 0)
                    {
                        int i45 = a16.getHeight(a);
                        int i46 = this.additionalMarkerSpacing;
                        int i47 = i45 + i46;
                        int i48 = i40 - i47;
                        i42 = i48;
                    }
                    else
                    {
                        int i49 = a16.getHeight(a);
                        int i50 = this.additionalMarkerSpacing;
                        int i51 = i49 + i50;
                        int i52 = i40 + i51;
                        i42 = i52;
                    }
                    java.awt.Point a17 = this.pos;
                    int i53 = a17.x;
                    int i54 = this.getWidth();
                    int i55 = i54 / 2;
                    int i56 = i53 + i55;
                    java.awt.Point a18 = new java.awt.Point(i56, i42);
                    a16.setPosition(a18);
                    a16.setCenter(true);
                    a16.draw(a);
                    int i57 = i41 + 1;
                    i40 = i42;
                    i41 = i57;
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
            com.cim.AIA.Node dummy = (com.cim.AIA.Node)a0;
            com.cim.AIA.Node a1 = (com.cim.AIA.Node)a0;
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
        int i1 = this.getWidth();
        int i2 = this.getHeight();
        java.awt.Rectangle a2 = new java.awt.Rectangle(i, i0, i1, i2);
        int i3 = a2.contains(a)?1:0;
        com.cim.AIA.Node a3 = i3 == 0?null:this;
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
        com.cim.AIA.Node.DEFAULT_BACKGROUND_COLOR = a;
        java.awt.Color a0 = java.awt.Color.black;
        com.cim.AIA.Node.DEFAULT_BORDER_COLOR = a0;
        java.awt.Color a1 = java.awt.Color.black;
        com.cim.AIA.Node.DEFAULT_TEXT_COLOR = a1;
        java.awt.Color a2 = java.awt.Color.yellow;
        com.cim.AIA.Node.DEFAULT_HIGHLIGHT_COLOR = a2;
    }
}