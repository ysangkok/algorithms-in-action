public class BoyerMooreString implements com.cim.AIA.Drawable, com.cim.AIA.Moveable {
    protected String theString;
    protected int XGap;
    protected boolean needPosition;
    protected java.awt.Point location;
    protected int length;
    protected String theLabel;
    protected boolean showLabel;
    protected boolean highLabel;
    protected boolean notDrawnYet;
    protected int labelIndex;
    protected java.awt.Color labelColor;
    protected int arrowLabelIndex;
    protected int arrowLabelTarget;
    protected int xArrowIndex;
    protected int xArrowTarget;
    protected java.awt.Color highlightColor;
    protected boolean[] highlightTable;
    
    public BoyerMooreString(String s)
    {
        super();
        java.awt.Point a = new java.awt.Point();
        this.location = a;
        java.awt.Color a0 = java.awt.Color.red;
        this.labelColor = a0;
        java.awt.Color a1 = java.awt.Color.green;
        this.highlightColor = a1;
        this.theString = s;
        this.initialise();
    }
    
    public void draw(java.awt.Graphics a)
    {
        java.awt.Point a0 = this.location;
        int i = a0.x;
        java.awt.Point a1 = this.location;
        int i0 = a1.y;
        java.awt.FontMetrics a2 = a.getFontMetrics();
        int i1 = this.notDrawnYet?1:0;
        if(i1 != 0)
        {
            this.notDrawnYet = false;
            int i2 = this.xArrowIndex;
            int i3 = i2 + 100;
            this.xArrowIndex = i3;
        }
        this.xArrowTarget = i;
        int i4 = this.xArrowTarget;
        int i5 = this.XGap;
        int i6 = this.arrowLabelTarget;
        int i7 = i5 * i6;
        int i8 = i4 + i7;
        int i9 = a2.charWidth((char)70);
        int i10 = i9 / 2;
        int i11 = i8 + i10;
        this.xArrowTarget = i11;
        int i12 = i;
        int i13 = 0;
        while(true)
        {
            String s = this.theString;
            int i14 = s.length();
            if(i13 >= i14)
            {
                break;
            }
            String s0 = this.theString;
            int i15 = s0.charAt(i13);
            int i16 = this.XGap;
            int i17 = a2.charWidth((char)i15);
            int i18 = i16 - i17;
            int i19 = i18 / 2;
            boolean[] a3 = this.highlightTable;
            int i20 = a3[i13]?1:0;
            if(i20 != 0)
            {
                java.awt.Color a4 = a.getColor();
                java.awt.Color a5 = this.highlightColor;
                a.setColor(a5);
                int i21 = i12 + i19;
                java.awt.FontMetrics a6 = a.getFontMetrics();
                int i22 = a6.getHeight();
                int i23 = i0 - i22;
                java.awt.FontMetrics a7 = a.getFontMetrics();
                int i24 = a7.charWidth((char)i15);
                java.awt.FontMetrics a8 = a.getFontMetrics();
                int i25 = a8.getHeight();
                a.fillRect(i21, i23, i24, i25);
                a.setColor(a4);
            }
            StringBuilder a9 = new StringBuilder();
            StringBuilder a10 = a9.append("");
            StringBuilder a11 = a10.append((char)i15);
            String s1 = a11.toString();
            int i26 = i12 + i19;
            a.drawString(s1, i26, i0);
            int i27 = this.showLabel?1:0;
            label0: {
                if(i27 == 0)
                {
                    break label0;
                }
                int i28 = this.labelIndex;
                if(i28 != i13)
                {
                    break label0;
                }
                java.awt.Color a12 = a.getColor();
                java.awt.Color a13 = this.labelColor;
                a.setColor(a13);
                int i29 = this.highLabel?1:0;
                if(i29 == 0)
                {
                    StringBuilder a14 = new StringBuilder();
                    String s2 = this.theLabel;
                    StringBuilder a15 = a14.append(s2);
                    StringBuilder a16 = a15.append(" = ");
                    int i30 = this.labelIndex;
                    StringBuilder a17 = a16.append(i30);
                    String s3 = a17.toString();
                    int i31 = i12 + i19;
                    int i32 = this.getHeight();
                    int i33 = i0 + i32;
                    a.drawString(s3, i31, i33);
                }
                else
                {
                    StringBuilder a18 = new StringBuilder();
                    String s4 = this.theLabel;
                    StringBuilder a19 = a18.append(s4);
                    StringBuilder a20 = a19.append(" = ");
                    int i34 = this.labelIndex;
                    StringBuilder a21 = a20.append(i34);
                    String s5 = a21.toString();
                    int i35 = i12 + i19;
                    int i36 = this.getHeight();
                    int i37 = i0 - i36;
                    a.drawString(s5, i35, i37);
                }
                a.setColor(a12);
            }
            int i38 = this.XGap;
            int i39 = i12 + i38;
            int i40 = i13 + 1;
            i12 = i39;
            i13 = i40;
        }
        int i41 = this.arrowLabelTarget;
        label1: {
            if(i41 == -1)
            {
                break label1;
            }
            java.awt.Color a22 = a.getColor();
            java.awt.Color a23 = this.labelColor;
            a.setColor(a23);
            int i42 = this.highLabel?1:0;
            if(i42 == 0)
            {
                int i43 = this.xArrowIndex;
                int i44 = a2.charWidth((char)70);
                int i45 = i44 / 2;
                int i46 = i43 + i45;
                int i47 = this.getHeight();
                int i48 = i47 * 1;
                int i49 = i0 + i48;
                int i50 = this.xArrowTarget;
                int i51 = a2.charWidth((char)70);
                int i52 = i51 / 2;
                int i53 = i50 + i52;
                com.cim.AIA.Line a24 = new com.cim.AIA.Line(i46, i49, i53, i0);
                StringBuilder a25 = new StringBuilder();
                String s6 = this.theLabel;
                StringBuilder a26 = a25.append(s6);
                StringBuilder a27 = a26.append(" = ");
                int i54 = this.arrowLabelTarget;
                StringBuilder a28 = a27.append(i54);
                String s7 = a28.toString();
                int i55 = this.xArrowIndex;
                int i56 = a2.charWidth((char)70);
                int i57 = i56 / 2;
                int i58 = i55 + i57;
                int i59 = this.getHeight();
                int i60 = i59 * 2;
                int i61 = i0 + i60;
                a.drawString(s7, i58, i61);
                a24.showArrowHead(true);
                int i62 = a24.getLength();
                a24.setDistanceFromStartForArrowHead(i62);
                a24.draw(a);
            }
            else
            {
                int i63 = this.xArrowIndex;
                int i64 = this.getHeight();
                int i65 = i64 * 2;
                int i66 = i0 - i65;
                int i67 = this.xArrowTarget;
                int i68 = a2.charWidth((char)70);
                int i69 = i68 / 2;
                int i70 = i67 + i69;
                int i71 = this.getHeight();
                int i72 = i0 - i71;
                com.cim.AIA.Line a29 = new com.cim.AIA.Line(i63, i66, i70, i72);
                StringBuilder a30 = new StringBuilder();
                String s8 = this.theLabel;
                StringBuilder a31 = a30.append(s8);
                StringBuilder a32 = a31.append(" = ");
                int i73 = this.arrowLabelTarget;
                StringBuilder a33 = a32.append(i73);
                String s9 = a33.toString();
                int i74 = this.xArrowIndex;
                int i75 = a2.charWidth((char)70);
                int i76 = i75 / 2;
                int i77 = i74 + i76;
                int i78 = this.getHeight();
                int i79 = i78 * 2;
                int i80 = i0 - i79;
                a.drawString(s9, i77, i80);
                a29.showArrowHead(true);
                int i81 = a29.getLength();
                a29.setDistanceFromStartForArrowHead(i81);
                a29.draw(a);
            }
            a.setColor(a22);
        }
    }
    
    public void draw(java.awt.Graphics a, java.awt.Point a0)
    {
        this.setLocation(a0);
        this.draw(a);
    }
    
    public int getHeight()
    {
        BoyerMooreCanvas a = BoyerMooreApplet.theBoyerMooreCanvas;
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
        boolean[] a = this.highlightTable;
        a[i] = true;
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
                boolean[] a = this.highlightTable;
                a[i1] = true;
                int i2 = i1 + 1;
                i1 = i2;
            }
        }
    }
    
    private void initialise()
    {
        this.setLocation(0, 0);
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
        this.arrowLabelIndex = 0;
        this.arrowLabelTarget = -1;
        this.xArrowIndex = -1;
        this.notDrawnYet = true;
        int i1 = 0;
        while(true)
        {
            int i2 = this.length;
            if(i1 >= i2)
            {
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
    
    public void jumpArrowLabelIndex(int i)
    {
        int i0 = this.xArrowIndex;
        if(i0 != -1)
        {
            int i1 = this.xArrowIndex;
            int i2 = this.XGap;
            int i3 = i * i2;
            int i4 = i1 + i3;
            this.xArrowIndex = i4;
        }
        else
        {
            this.setArrowLabelIndex(i);
        }
    }
    
    public int length()
    {
        int i = this.length;
        return i;
    }
    
    public void setArrowLabelIndex(int i)
    {
        this.arrowLabelIndex = i;
        int i0 = this.XGap;
        int i1 = i * i0;
        java.awt.Point a = this.location;
        int i2 = a.x;
        int i3 = i1 + i2;
        this.xArrowIndex = i3;
    }
    
    public void setArrowLabelTarget(int i)
    {
        this.arrowLabelTarget = i;
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
        boolean[] a = this.highlightTable;
        a[i] = false;
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
                boolean[] a = this.highlightTable;
                a[i1] = false;
                int i2 = i1 + 1;
                i1 = i2;
            }
        }
    }
    
    public void unHighlightAll()
    {
        int i = 0;
        while(true)
        {
            int i0 = this.length;
            if(i >= i0)
            {
                return;
            }
            else
            {
                boolean[] a = this.highlightTable;
                a[i] = false;
                int i1 = i + 1;
                i = i1;
            }
        }
    }
}