public class KMPString implements com.cim.AIA.Drawable, com.cim.AIA.Moveable {
    protected String theString;
    protected int XGap;
    protected boolean needPosition;
    protected java.awt.Point location;
    protected int length;
    protected String theLabel;
    protected boolean showLabel;
    protected boolean highLabel;
    protected int labelIndex;
    protected java.awt.Color labelColor;
    protected java.awt.Color highlightColor;
    protected boolean[] highlightTable;
    protected boolean highlightMinusOne;
    
    public KMPString(String s)
    {
        super();
        java.awt.Point a = new java.awt.Point();
        this.location = a;
        java.awt.Color a0 = java.awt.Color.red;
        this.labelColor = a0;
        java.awt.Color a1 = java.awt.Color.green;
        this.highlightColor = a1;
        this.setLocation(0, 0);
        this.theString = s;
        this.initialise();
    }
    
    public void draw(java.awt.Graphics a)
    {
        java.awt.Point a0 = this.location;
        int i = a0.x;
        int i0 = this.XGap;
        int i1 = i + i0;
        java.awt.Point a1 = this.location;
        int i2 = a1.y;
        java.awt.FontMetrics a2 = a.getFontMetrics();
        int i3 = i1;
        int i4 = 0;
        while(true)
        {
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            String s = this.theString;
            int i8 = s.length();
            if(i4 >= i8)
            {
                return;
            }
            String s0 = this.theString;
            int i9 = s0.charAt(i4);
            int i10 = this.XGap;
            int i11 = a2.charWidth((char)i9);
            label0: {
                int i12 = i10 - i11;
                i5 = i12 / 2;
                if(i4 != 0)
                {
                    i6 = i4;
                    break label0;
                }
                int i13 = this.highlightMinusOne?1:0;
                if(i13 == 0)
                {
                    i6 = 0;
                }
                else
                {
                    java.awt.Color a3 = a.getColor();
                    java.awt.Color a4 = this.highlightColor;
                    a.setColor(a4);
                    int i14 = i3 + i5;
                    int i15 = this.XGap;
                    int i16 = i14 - i15;
                    java.awt.FontMetrics a5 = a.getFontMetrics();
                    int i17 = a5.getHeight();
                    int i18 = i2 - i17;
                    java.awt.FontMetrics a6 = a.getFontMetrics();
                    int i19 = a6.charWidth((char)i9);
                    java.awt.FontMetrics a7 = a.getFontMetrics();
                    int i20 = a7.getHeight();
                    a.fillRect(i16, i18, i19, i20);
                    a.setColor(a3);
                    i6 = 0;
                }
            }
            boolean[] a8 = this.highlightTable;
            int i21 = a8[i6]?1:0;
            if(i21 != 0)
            {
                java.awt.Color a9 = a.getColor();
                java.awt.Color a10 = this.highlightColor;
                a.setColor(a10);
                int i22 = i3 + i5;
                java.awt.FontMetrics a11 = a.getFontMetrics();
                int i23 = a11.getHeight();
                int i24 = i2 - i23;
                java.awt.FontMetrics a12 = a.getFontMetrics();
                int i25 = a12.charWidth((char)i9);
                java.awt.FontMetrics a13 = a.getFontMetrics();
                int i26 = a13.getHeight();
                a.fillRect(i22, i24, i25, i26);
                a.setColor(a9);
            }
            StringBuilder a14 = new StringBuilder();
            StringBuilder a15 = a14.append("");
            StringBuilder a16 = a15.append((char)i9);
            String s1 = a16.toString();
            int i27 = i3 + i5;
            a.drawString(s1, i27, i2);
            int i28 = this.showLabel?1:0;
            label1: {
                if(i28 == 0)
                {
                    break label1;
                }
                int i29 = this.labelIndex;
                if(i29 != i6)
                {
                    break label1;
                }
                java.awt.Color a17 = a.getColor();
                java.awt.Color a18 = this.labelColor;
                a.setColor(a18);
                int i30 = this.highLabel?1:0;
                if(i30 == 0)
                {
                    String s2 = this.theLabel;
                    int i31 = i3 + i5;
                    int i32 = this.getHeight();
                    int i33 = i2 + i32;
                    a.drawString(s2, i31, i33);
                }
                else
                {
                    String s3 = this.theLabel;
                    int i34 = i3 + i5;
                    int i35 = this.getHeight();
                    int i36 = i2 - i35;
                    a.drawString(s3, i34, i36);
                }
                a.setColor(a17);
            }
            int i37 = this.showLabel?1:0;
            label2: {
                if(i37 == 0)
                {
                    i7 = i6;
                    break label2;
                }
                int i38 = this.labelIndex;
                if(i38 != -1)
                {
                    i7 = i6;
                    break label2;
                }
                if(i6 != 0)
                {
                    i7 = i6;
                    break label2;
                }
                java.awt.Color a19 = a.getColor();
                java.awt.Color a20 = this.labelColor;
                a.setColor(a20);
                int i39 = this.highLabel?1:0;
                if(i39 == 0)
                {
                    String s4 = this.theLabel;
                    int i40 = i3 + i5;
                    int i41 = this.XGap;
                    int i42 = i40 - i41;
                    int i43 = this.getHeight();
                    int i44 = i2 + i43;
                    a.drawString(s4, i42, i44);
                }
                else
                {
                    String s5 = this.theLabel;
                    int i45 = i3 + i5;
                    int i46 = this.XGap;
                    int i47 = i45 - i46;
                    int i48 = this.getHeight();
                    int i49 = i2 - i48;
                    a.drawString(s5, i47, i49);
                }
                a.setColor(a19);
                i7 = 0;
            }
            int i50 = this.XGap;
            int i51 = i3 + i50;
            int i52 = i7 + 1;
            i3 = i51;
            i4 = i52;
        }
    }
    
    public void draw(java.awt.Graphics a, java.awt.Point a0)
    {
        this.setLocation(a0);
        this.draw(a);
    }
    
    public int getHeight()
    {
        KMPCanvas a = KMPApplet.theKMPCanvas;
        java.awt.Graphics a0 = a.getGraphics();
        java.awt.FontMetrics a1 = a0.getFontMetrics();
        int i = a1.getHeight();
        return i;
    }
    
    public java.awt.Point getLocation()
    {
        java.awt.Point a = this.location;
        return a;
    }
    
    public boolean getShowLabel()
    {
        int i = this.showLabel?1:0;
        return i != 0;
    }
    
    public String getString()
    {
        String s = this.theString;
        return s;
    }
    
    public int getWidth()
    {
        String s = this.theString;
        int i = s.length();
        int i0 = this.XGap;
        int i1 = i * i0;
        return i1;
    }
    
    public int getX()
    {
        java.awt.Point a = this.location;
        int i = a.x;
        return i;
    }
    
    public int getY()
    {
        java.awt.Point a = this.location;
        int i = a.y;
        return i;
    }
    
    public void highlight(int i)
    {
        if(i >= 0)
        {
            boolean[] a = this.highlightTable;
            a[i] = true;
        }
        if(i == -1)
        {
            this.highlightMinusOne = true;
        }
    }
    
    public void highlight(int i, int i0)
    {
        int i1 = i;
        while(true)
        {
            if(i1 >= i0)
            {
                return;
            }
            else
            {
                this.highlight(i1);
                int i2 = i1 + 1;
                i1 = i2;
            }
        }
    }
    
    private void initialise()
    {
        this.XGap = 20;
        this.needPosition = true;
        String s = this.theString;
        int i = s.length();
        this.length = i;
        int i0 = this.length;
        boolean[] a = new boolean[i0];
        this.highlightTable = a;
        this.theLabel = null;
        this.showLabel = false;
        this.highLabel = true;
        int i1 = 0;
        while(true)
        {
            int i2 = this.length;
            if(i1 >= i2)
            {
                this.highlightMinusOne = false;
                return;
            }
            else
            {
                boolean[] a0 = this.highlightTable;
                a0[i1] = false;
                int i3 = i1 + 1;
                i1 = i3;
            }
        }
    }
    
    public int length()
    {
        int i = this.length;
        return i;
    }
    
    public void setHighLabel(boolean b)
    {
        this.highLabel = b;
    }
    
    public void setHighlightColor(java.awt.Color a)
    {
        this.highlightColor = a;
    }
    
    public void setLabel(String s)
    {
        this.theLabel = s;
    }
    
    public void setLabelIndex(int i)
    {
        this.labelIndex = i;
    }
    
    public void setLocation(int i, int i0)
    {
        java.awt.Point a = this.location;
        a.x = i;
        java.awt.Point a0 = this.location;
        a0.y = i0;
    }
    
    public void setLocation(java.awt.Point a)
    {
        this.location = a;
    }
    
    public void setPosition(int i, int i0)
    {
        int i1 = this.needPosition?1:0;
        if(i1 != 0)
        {
            java.awt.Point a = this.location;
            int i2 = a.x;
            int i3 = i + i2;
            java.awt.Point a0 = this.location;
            int i4 = a0.y;
            int i5 = i0 + i4;
            this.setLocation(i3, i5);
            this.needPosition = false;
        }
    }
    
    public void setShowLabel(boolean b)
    {
        this.showLabel = b;
    }
    
    public void setX(int i)
    {
        java.awt.Point a = this.location;
        a.x = i;
    }
    
    public void setY(int i)
    {
        java.awt.Point a = this.location;
        a.y = i;
    }
    
    public void shiftEntire(int i, int i0)
    {
        java.awt.Point a = this.location;
        java.awt.Point a0 = this.location;
        int i1 = a0.x;
        int i2 = i1 + i;
        a.x = i2;
        java.awt.Point a1 = this.location;
        java.awt.Point a2 = this.location;
        int i3 = a2.y;
        int i4 = i3 + i0;
        a1.y = i4;
    }
    
    public void unHighlight(int i)
    {
        if(i >= 0)
        {
            boolean[] a = this.highlightTable;
            a[i] = false;
        }
        if(i == -1)
        {
            this.highlightMinusOne = false;
        }
    }
    
    public void unHighlight(int i, int i0)
    {
        int i1 = i;
        while(true)
        {
            if(i1 >= i0)
            {
                return;
            }
            else
            {
                this.unHighlight(i1);
                int i2 = i1 + 1;
                i1 = i2;
            }
        }
    }
    
    public void unHighlightAll()
    {
        int i = this.length;
        this.unHighlight(-1, i);
    }
}