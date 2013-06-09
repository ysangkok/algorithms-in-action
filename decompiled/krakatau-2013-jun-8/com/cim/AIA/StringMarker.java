package com.cim.AIA;

public class StringMarker implements com.cim.AIA.Moveable, com.cim.AIA.Drawable {
    final protected static java.awt.Color DEFAULT_COLOR;
    final protected static java.awt.Color DEFAULT_BACKGROUND_COLOR;
    protected String string;
    protected java.awt.Point position;
    protected java.awt.Color color;
    protected java.awt.Color backgroundColor;
    protected boolean doCenter;
    protected boolean useDefaultHeight;
    protected boolean useDefaultWidth;
    protected int width;
    protected int height;
    
    public StringMarker(String s)
    {
        java.awt.Point a = new java.awt.Point(0, 0);
        java.awt.Color a0 = com.cim.AIA.StringMarker.DEFAULT_COLOR;
        java.awt.Color a1 = com.cim.AIA.StringMarker.DEFAULT_BACKGROUND_COLOR;
        this(s, a, a0, a1);
    }
    
    public StringMarker(String s, java.awt.Point a)
    {
        java.awt.Color a0 = com.cim.AIA.StringMarker.DEFAULT_COLOR;
        java.awt.Color a1 = com.cim.AIA.StringMarker.DEFAULT_BACKGROUND_COLOR;
        this(s, a, a0, a1);
    }
    
    public StringMarker(String s, java.awt.Point a, java.awt.Color a0, java.awt.Color a1)
    {
        super();
        this.doCenter = true;
        this.useDefaultHeight = true;
        this.useDefaultWidth = true;
        this.string = s;
        this.position = a;
        this.color = a0;
        this.backgroundColor = a1;
    }
    
    public void draw(java.awt.Graphics a)
    {
        java.awt.Color a0 = this.backgroundColor;
        a.setColor(a0);
        int i = this.getHeight(a);
        int i0 = this.getWidth(a);
        int i1 = this.doCenter?1:0;
        if(i1 == 0)
        {
            java.awt.Point a1 = this.position;
            int i2 = a1.x;
            java.awt.Point a2 = this.position;
            int i3 = a2.y;
            int i4 = i3 - i;
            a.fillRect(i2, i4, i0, i);
        }
        else
        {
            java.awt.Point a3 = this.position;
            int i5 = a3.x;
            int i6 = i0 / 2;
            int i7 = i5 - i6;
            java.awt.Point a4 = this.position;
            int i8 = a4.y;
            int i9 = i8 - i;
            a.fillRect(i7, i9, i0, i);
        }
        java.awt.Color a5 = this.color;
        a.setColor(a5);
        int i10 = this.doCenter?1:0;
        if(i10 == 0)
        {
            String s = this.string;
            java.awt.Point a6 = this.position;
            int i11 = a6.x;
            java.awt.Point a7 = this.position;
            int i12 = a7.y;
            a.drawString(s, i11, i12);
        }
        else
        {
            String s0 = this.string;
            java.awt.Point a8 = this.position;
            int i13 = a8.x;
            java.awt.FontMetrics a9 = a.getFontMetrics();
            String s1 = this.string;
            int i14 = a9.stringWidth(s1);
            int i15 = i14 / 2;
            int i16 = i13 - i15;
            java.awt.Point a10 = this.position;
            int i17 = a10.y;
            a.drawString(s0, i16, i17);
        }
    }
    
    public void draw(java.awt.Graphics a, java.awt.Point a0)
    {
        this.setPosition(a0);
        this.draw(a);
    }
    
    public int getHeight(java.awt.Graphics a)
    {
        int i = 0;
        int i0 = this.useDefaultHeight?1:0;
        if(i0 == 0)
        {
            int i1 = this.height;
            i = i1;
        }
        else
        {
            java.awt.FontMetrics a0 = a.getFontMetrics();
            int i2 = a0.getHeight();
            i = i2;
        }
        return i;
    }
    
    public java.awt.Point getPosition()
    {
        java.awt.Point a = this.position;
        return a;
    }
    
    public int getWidth(java.awt.Graphics a)
    {
        int i = 0;
        int i0 = this.useDefaultWidth?1:0;
        if(i0 == 0)
        {
            int i1 = this.width;
            i = i1;
        }
        else
        {
            java.awt.FontMetrics a0 = a.getFontMetrics();
            String s = this.string;
            int i2 = a0.stringWidth(s);
            i = i2;
        }
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
    
    public void setBackgroundColor(java.awt.Color a)
    {
        this.backgroundColor = a;
    }
    
    public void setCenter(boolean b)
    {
        this.doCenter = b;
    }
    
    public void setColor(java.awt.Color a)
    {
        this.color = a;
    }
    
    public void setHeight(int i)
    {
        this.height = i;
    }
    
    public void setPosition(java.awt.Point a)
    {
        this.position = a;
    }
    
    public void setString(String s)
    {
        this.string = s;
    }
    
    public void setWidth(int i)
    {
        this.width = i;
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
    
    public void useDefaultHeight(boolean b)
    {
        this.useDefaultHeight = b;
    }
    
    public void useDefaultWidth(boolean b)
    {
        this.useDefaultWidth = b;
    }
    
    static
    {
        java.awt.Color a = java.awt.Color.black;
        com.cim.AIA.StringMarker.DEFAULT_COLOR = a;
        java.awt.Color a0 = java.awt.Color.white;
        com.cim.AIA.StringMarker.DEFAULT_BACKGROUND_COLOR = a0;
    }
}