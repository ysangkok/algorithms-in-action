package com.cim.common;

public class MultiLineLabel extends com.cim.common.BasicCanvas {
    final private static long serialVersionUID = -1696824653160308291L;
    final public static int LEFT = 0;
    final public static int CENTER = 1;
    final public static int RIGHT = 2;
    protected String[] lines;
    protected int num_lines;
    protected int margin_width;
    protected int margin_height;
    protected int line_height;
    protected int line_ascent;
    protected int[] line_widths;
    protected int max_width;
    protected int alignment;
    protected java.awt.Container label_parent;
    java.awt.Color background_color;
    
    public MultiLineLabel(String s)
    {
        this(s, 10, 10, 0);
    }
    
    public MultiLineLabel(String s, java.applet.Applet a)
    {
        this(s, 10, 10, 0);
        this.label_parent = a;
    }
    
    public MultiLineLabel(String s, java.awt.Frame a)
    {
        this(s, 10, 10, 0);
        this.label_parent = a;
    }
    
    public MultiLineLabel(String s, int i)
    {
        this(s, 10, 10, i);
    }
    
    public MultiLineLabel(String s, int i, int i0)
    {
        this(s, i, i0, 0);
    }
    
    public MultiLineLabel(String s, int i, int i0, int i1)
    {
        super();
        this.alignment = 0;
        java.awt.Color a = java.awt.Color.black;
        this.setForeground(a);
        this.newLabel(s);
        this.margin_width = i;
        this.margin_height = i0;
        this.alignment = i1;
    }
    
    public void addNotify()
    {
        ((com.cim.common.BasicCanvas)this).addNotify();
        this.measure();
    }
    
    public void display(java.awt.Graphics a)
    {
        java.awt.Color a0 = this.getForeground();
        java.awt.Dimension a1 = this.getSize();
        java.awt.Color a2 = this.getBackground();
        a.setColor(a2);
        int i = a1.width;
        int i0 = a1.height;
        a.fillRect(0, 0, i, i0);
        a.setColor(a0);
        java.awt.Dimension a3 = this.getSize();
        int i1 = this.line_ascent;
        int i2 = a3.height;
        int i3 = this.num_lines;
        int i4 = this.line_height;
        int i5 = i3 * i4;
        int i6 = i2 - i5;
        int i7 = i6 / 2;
        int i8 = i1 + i7;
        int i9 = i8;
        int i10 = 0;
        while(true)
        {
            int i11 = 0;
            int i12 = this.num_lines;
            if(i10 >= i12)
            {
                return;
            }
            int i13 = this.alignment;
            switch(i13){
                case 2: {
                    int i14 = a3.width;
                    int i15 = this.margin_width;
                    int i16 = i14 - i15;
                    int[] a4 = this.line_widths;
                    int i17 = a4[i10];
                    int i18 = i16 - i17;
                    i11 = i18;
                    break;
                }
                case 0: {
                    i11 = 0;
                    break;
                }
                default: {
                    int i19 = a3.width;
                    int[] a5 = this.line_widths;
                    int i20 = a5[i10];
                    int i21 = i19 - i20;
                    int i22 = i21 / 2;
                    i11 = i22;
                }
            }
            String[] a6 = this.lines;
            String s = a6[i10];
            a.drawString(s, i11, i9);
            int i23 = i10 + 1;
            int i24 = this.line_height;
            int i25 = i9 + i24;
            i9 = i25;
            i10 = i23;
        }
    }
    
    protected String get_label()
    {
        String[] a = this.lines;
        String s = a[0];
        return s;
    }
    
    public int getAlignment()
    {
        int i = this.alignment;
        return i;
    }
    
    protected String[] getLabel()
    {
        String[] a = this.lines;
        return a;
    }
    
    public int getMarginHeight()
    {
        int i = this.margin_height;
        return i;
    }
    
    public int getMarginWidth()
    {
        int i = this.margin_width;
        return i;
    }
    
    public java.awt.Dimension getMinimumSize()
    {
        int i = this.max_width;
        int i0 = this.num_lines;
        int i1 = this.line_height;
        int i2 = i0 * i1;
        java.awt.Dimension a = new java.awt.Dimension(i, i2);
        return a;
    }
    
    public java.awt.Dimension getPreferredSize()
    {
        int i = this.max_width;
        int i0 = this.margin_width;
        int i1 = 2 * i0;
        int i2 = i + i1;
        int i3 = this.num_lines;
        int i4 = this.line_height;
        int i5 = i3 * i4;
        int i6 = this.margin_height;
        int i7 = 2 * i6;
        int i8 = i5 + i7;
        java.awt.Dimension a = new java.awt.Dimension(i2, i8);
        return a;
    }
    
    protected void measure()
    {
        java.awt.Font a = this.getFont();
        java.awt.FontMetrics a0 = this.getFontMetrics(a);
        label1: {
            label0: {
                if(a0 != null)
                {
                    break label0;
                }
                break label1;
            }
            int i = a0.getHeight();
            this.line_height = i;
            int i0 = a0.getAscent();
            this.line_ascent = i0;
            this.max_width = 0;
            int i1 = 0;
            while(true)
            {
                int i2 = this.num_lines;
                if(i1 >= i2)
                {
                    break;
                }
                int[] a1 = this.line_widths;
                String[] a2 = this.lines;
                String s = a2[i1];
                int i3 = a0.stringWidth(s);
                a1[i1] = i3;
                int[] a3 = this.line_widths;
                int i4 = a3[i1];
                int i5 = this.max_width;
                if(i4 > i5)
                {
                    int[] a4 = this.line_widths;
                    int i6 = a4[i1];
                    this.max_width = i6;
                }
                int i7 = i1 + 1;
                i1 = i7;
            }
        }
    }
    
    public void newLabel(String s)
    {
        java.util.StringTokenizer a = new java.util.StringTokenizer(s, "\n");
        int i = a.countTokens();
        this.num_lines = i;
        int i0 = this.num_lines;
        String[] a0 = new String[i0];
        this.lines = a0;
        int i1 = this.num_lines;
        int[] a1 = new int[i1];
        this.line_widths = a1;
        int i2 = 0;
        while(true)
        {
            int i3 = this.num_lines;
            if(i2 >= i3)
            {
                return;
            }
            else
            {
                String[] a2 = this.lines;
                String s0 = a.nextToken();
                a2[i2] = s0;
                int i4 = i2 + 1;
                i2 = i4;
            }
        }
    }
    
    public void repaint()
    {
        java.awt.Color a = this.background_color;
        if(a != null)
        {
            java.awt.Color a0 = this.background_color;
            this.setBackground(a0);
        }
    }
    
    public void setAlignment(int i)
    {
        this.alignment = i;
        this.repaint();
    }
    
    public void setFont(java.awt.Font a)
    {
        ((com.cim.common.BasicCanvas)this).setFont(a);
        this.measure();
        this.repaint();
    }
    
    public void setForeground(java.awt.Color a)
    {
        ((com.cim.common.BasicCanvas)this).setForeground(a);
        this.repaint();
    }
    
    public void setLabel(String s)
    {
        this.newLabel(s);
        this.measure();
        this.repaint();
    }
    
    public void setMarginHeight(int i)
    {
        this.margin_height = i;
        this.repaint();
    }
    
    public void setMarginWidth(int i)
    {
        this.margin_width = i;
        this.repaint();
    }
    
    public void setText(String s)
    {
        this.setLabel(s);
    }
}