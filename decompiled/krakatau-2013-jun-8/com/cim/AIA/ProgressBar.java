package com.cim.AIA;

public class ProgressBar extends java.awt.Canvas {
    final private static long serialVersionUID = 3328266732264135126L;
    final protected static int HEIGHT = 20;
    final protected static int GAP = 3;
    protected int max;
    protected int step;
    protected int mark;
    protected java.awt.Image offscreen;
    
    public ProgressBar(int i, int i0)
    {
        super();
        java.awt.Color a = java.awt.Color.white;
        this.setBackground(a);
        this.max = i;
        this.step = i0;
    }
    
    public void draw(java.awt.Graphics a)
    {
        java.awt.Dimension a0 = this.getSize();
        int i = a0.width;
        java.awt.Dimension a1 = this.getSize();
        int i0 = a1.height;
        int i1 = this.max;
        int i2 = this.step;
        int i3 = i1 / i2;
        int i4 = i3 - 1;
        int i5 = i4 * 3;
        int i6 = i - i5;
        int i7 = i6 / i3;
        java.awt.Color a2 = java.awt.Color.black;
        a.setColor(a2);
        int i8 = i3 * i7;
        int i9 = i - i8;
        int i10 = i4 * 3;
        int i11 = i9 - i10;
        int i12 = i11 / 2;
        int i13 = i12 < 0?0:i12;
        int i14 = i13;
        int i15 = 0;
        while(true)
        {
            int i16 = this.max;
            if(i15 >= i16)
            {
                return;
            }
            int i17 = this.mark;
            if(i15 >= i17)
            {
                java.awt.Color a3 = java.awt.Color.black;
                a.setColor(a3);
                a.fillRect(i14, 0, i7, i0);
            }
            else
            {
                java.awt.Color a4 = new java.awt.Color(10, 87, 159);
                a.setColor(a4);
                a.fill3DRect(i14, 0, i7, i0, true);
            }
            int i18 = i7 + 3;
            int i19 = i14 + i18;
            int i20 = i15 + 1;
            i14 = i19;
            i15 = i20;
        }
    }
    
    public java.awt.Dimension getMinimumSize()
    {
        java.awt.Dimension a = new java.awt.Dimension(20, 20);
        return a;
    }
    
    public void paint(java.awt.Graphics a)
    {
        java.awt.Image a0 = this.offscreen;
        if(a0 == null)
        {
            this.draw(a);
        }
        else
        {
            java.awt.Image a1 = this.offscreen;
            int i = a.drawImage(a1, 0, 0, (java.awt.image.ImageObserver)this)?1:0;
        }
    }
    
    public void setProgressMark(int i)
    {
        this.mark = i;
    }
    
    public void update(java.awt.Graphics a)
    {
        java.awt.Dimension a0 = this.getSize();
        int i = a0.width;
        java.awt.Dimension a1 = this.getSize();
        int i0 = a1.height;
        java.awt.Image a2 = this.createImage(i, i0);
        this.offscreen = a2;
        java.awt.Image a3 = this.offscreen;
        java.awt.Graphics a4 = a3.getGraphics();
        this.draw(a4);
        this.paint(a);
    }
}