public class BoyerMooreSkipTable implements com.cim.AIA.Drawable {
    protected int size;
    protected int[] data;
    protected com.cim.AIA.Node[] dataNode;
    protected java.awt.Point location;
    protected int XGap;
    protected int YGap;
    protected int stringWidth;
    protected int stringHeight;
    protected int nodeWidth;
    protected int nodeHeight;
    protected int trueHeight;
    protected String infoStart;
    protected String infoEnd;
    protected boolean noInfo;
    
    public BoyerMooreSkipTable(int i)
    {
        super();
        java.awt.Point a = new java.awt.Point();
        this.location = a;
        String s = localization.Messages.getString("BoyerMooreSkipTable.0");
        this.infoStart = s;
        this.infoEnd = "] =";
        this.setLocation(0, 0);
        this.size = 27;
        this.initialise();
    }
    
    public void draw(java.awt.Graphics a)
    {
        int i = this.noInfo?1:0;
        if(i != 0)
        {
            this.reInit();
        }
        java.awt.Point a0 = this.location;
        int i0 = a0.x;
        java.awt.Point a1 = this.location;
        int i1 = a1.y;
        int i2 = i0;
        int i3 = 0;
        while(true)
        {
            if(i3 >= 13)
            {
                break;
            }
            else
            {
                com.cim.AIA.Node[] a2 = this.dataNode;
                com.cim.AIA.Node a3 = a2[i3];
                int i4 = this.stringHeight;
                int i5 = i1 + i4;
                java.awt.Point a4 = new java.awt.Point(i2, i5);
                a3.setPosition(a4);
                com.cim.AIA.Node[] a5 = this.dataNode;
                com.cim.AIA.Node a6 = a5[i3];
                a6.draw(a);
                StringBuilder a7 = new StringBuilder();
                StringBuilder a8 = a7.append("");
                int i6 = i3 + 65;
                int i7 = (char)i6;
                StringBuilder a9 = a8.append((char)i7);
                String s = a9.toString();
                int i8 = this.stringHeight;
                int i9 = i1 + i8;
                a.drawString(s, i2, i9);
                int i10 = this.XGap;
                int i11 = i2 + i10;
                int i12 = this.nodeWidth;
                int i13 = i11 + i12;
                int i14 = i3 + 1;
                i2 = i13;
                i3 = i14;
            }
        }
        int i15 = this.stringHeight;
        int i16 = i1 + i15;
        int i17 = this.nodeHeight;
        int i18 = i16 + i17;
        int i19 = this.YGap;
        int i20 = i18 + i19;
        java.awt.Point a10 = this.location;
        int i21 = a10.x;
        int i22 = i21;
        int i23 = 13;
        while(true)
        {
            if(i23 >= 26)
            {
                int i24 = this.stringHeight;
                int i25 = i20 + i24;
                int i26 = this.nodeHeight;
                int i27 = i25 + i26;
                int i28 = this.YGap;
                int i29 = i27 + i28;
                java.awt.Point a11 = this.location;
                int i30 = a11.x;
                com.cim.AIA.Node[] a12 = this.dataNode;
                com.cim.AIA.Node a13 = a12[26];
                int i31 = this.stringHeight;
                int i32 = i29 + i31;
                java.awt.Point a14 = new java.awt.Point(i30, i32);
                a13.setPosition(a14);
                com.cim.AIA.Node[] a15 = this.dataNode;
                com.cim.AIA.Node a16 = a15[26];
                a16.draw(a);
                String s0 = localization.Messages.getString("BoyerMooreSkipTable.1");
                int i33 = this.stringHeight;
                int i34 = i29 + i33;
                a.drawString(s0, i30, i34);
                return;
            }
            else
            {
                com.cim.AIA.Node[] a17 = this.dataNode;
                com.cim.AIA.Node a18 = a17[i23];
                int i35 = this.stringHeight;
                int i36 = i20 + i35;
                java.awt.Point a19 = new java.awt.Point(i22, i36);
                a18.setPosition(a19);
                com.cim.AIA.Node[] a20 = this.dataNode;
                com.cim.AIA.Node a21 = a20[i23];
                a21.draw(a);
                StringBuilder a22 = new StringBuilder();
                StringBuilder a23 = a22.append("");
                int i37 = i23 + 65;
                int i38 = (char)i37;
                StringBuilder a24 = a23.append((char)i38);
                String s1 = a24.toString();
                int i39 = this.stringHeight;
                int i40 = i20 + i39;
                a.drawString(s1, i22, i40);
                int i41 = this.XGap;
                int i42 = i22 + i41;
                int i43 = this.nodeWidth;
                int i44 = i42 + i43;
                int i45 = i23 + 1;
                i22 = i44;
                i23 = i45;
            }
        }
    }
    
    public void draw(java.awt.Graphics a, java.awt.Point a0)
    {
        this.setLocation(a0);
        this.draw(a);
    }
    
    public int get(int i)
    {
        int i0 = this.translateIndex(i);
        int[] a = this.data;
        int i1 = a[i0];
        return i1;
    }
    
    public int getHeight()
    {
        int i = this.noInfo?1:0;
        if(i != 0)
        {
            this.reInit();
        }
        int i0 = this.stringHeight;
        int i1 = this.nodeHeight;
        int i2 = i0 + i1;
        int i3 = this.YGap;
        int i4 = i2 + i3;
        int i5 = this.stringHeight;
        int i6 = i4 + i5;
        int i7 = this.nodeHeight;
        int i8 = i6 + i7;
        int i9 = this.YGap;
        int i10 = i8 + i9;
        int i11 = this.stringHeight;
        int i12 = i10 + i11;
        int i13 = this.nodeHeight;
        int i14 = i12 + i13;
        return i14;
    }
    
    public java.awt.Point getLocation()
    {
        java.awt.Point a = this.location;
        return a;
    }
    
    public int getWidth()
    {
        int i = this.noInfo?1:0;
        if(i != 0)
        {
            this.reInit();
        }
        int i0 = this.XGap;
        int i1 = this.nodeWidth;
        int i2 = i0 + i1;
        int i3 = i2 * 13;
        return i3;
    }
    
    public void highlight(int i)
    {
        int i0 = this.translateIndex(i);
        com.cim.AIA.Node[] a = this.dataNode;
        com.cim.AIA.Node a0 = a[i0];
        a0.highlight();
    }
    
    private void initialise()
    {
        this.XGap = 5;
        this.YGap = 5;
        int i = this.size;
        int[] a = new int[i];
        this.data = a;
        int i0 = this.size;
        com.cim.AIA.Node[] a0 = new com.cim.AIA.Node[i0];
        this.dataNode = a0;
        int i1 = 0;
        while(true)
        {
            int i2 = this.size;
            if(i1 >= i2)
            {
                this.noInfo = true;
                return;
            }
            else
            {
                com.cim.AIA.Node[] a1 = this.dataNode;
                com.cim.AIA.Node a2 = new com.cim.AIA.Node((Object)"", 0);
                a1[i1] = a2;
                int[] a3 = this.data;
                a3[i1] = 0;
                int i3 = i1 + 1;
                i1 = i3;
            }
        }
    }
    
    public void reInit()
    {
        BoyerMooreCanvas a = BoyerMooreApplet.theBoyerMooreCanvas;
        java.awt.Graphics a0 = a.getGraphics();
        java.awt.FontMetrics a1 = a0.getFontMetrics();
        int i = a1.getHeight();
        this.stringHeight = i;
        com.cim.AIA.Node a2 = new com.cim.AIA.Node((Object)"", 0);
        int i0 = a2.getWidth();
        this.nodeWidth = i0;
        int i1 = a2.getHeight();
        this.nodeHeight = i1;
        this.noInfo = false;
    }
    
    public void set(int i, int i0)
    {
        int i1 = this.translateIndex(i);
        int[] a = this.data;
        a[i1] = i0;
        com.cim.AIA.Node[] a0 = this.dataNode;
        StringBuilder a1 = new StringBuilder();
        StringBuilder a2 = a1.append("");
        StringBuilder a3 = a2.append(i0);
        String s = a3.toString();
        com.cim.AIA.Node a4 = new com.cim.AIA.Node((Object)s, 0);
        a0[i1] = a4;
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
        this.setLocation(i, i0);
    }
    
    private int translateIndex(int i)
    {
        int i0 = 0;
        label0: {
            if(i <= 25)
            {
                i0 = i;
                break label0;
            }
            if(i < 65)
            {
                i0 = i;
                break label0;
            }
            if(i > 90)
            {
                i0 = i;
            }
            else
            {
                int i1 = i - 65;
                i0 = i1;
            }
        }
        int i2 = i0 != 32?i0:26;
        return i2;
    }
    
    public void unHighlight(int i)
    {
        int i0 = this.translateIndex(i);
        com.cim.AIA.Node[] a = this.dataNode;
        com.cim.AIA.Node a0 = a[i0];
        a0.unHighlight();
    }
    
    public void unHighlightAll()
    {
        int i = 0;
        while(true)
        {
            int i0 = this.size;
            if(i >= i0)
            {
                return;
            }
            else
            {
                com.cim.AIA.Node[] a = this.dataNode;
                com.cim.AIA.Node a0 = a[i];
                a0.unHighlight();
                int i1 = i + 1;
                i = i1;
            }
        }
    }
}