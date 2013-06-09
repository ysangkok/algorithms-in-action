public class KMPNextTable implements com.cim.AIA.Drawable {
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
    protected int labelIndex;
    protected boolean highlightIndex;
    protected java.awt.Color labelColor;
    protected java.awt.Color defaultHighlightColor;
    protected java.awt.Color highlightColor;
    
    public KMPNextTable(int i)
    {
        super();
        java.awt.Point a = new java.awt.Point();
        this.location = a;
        String s = localization.Messages.getString("KMPNextTable.0");
        this.infoStart = s;
        this.infoEnd = "] =";
        this.noInfo = true;
        java.awt.Color a0 = java.awt.Color.red;
        this.labelColor = a0;
        java.awt.Color a1 = new java.awt.Color(255, 90, 90);
        this.defaultHighlightColor = a1;
        java.awt.Color a2 = new java.awt.Color(255, 90, 90);
        this.highlightColor = a2;
        this.setLocation(0, 0);
        this.size = i;
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
        int i2 = i1;
        int i3 = 0;
        while(true)
        {
            int i4 = this.size;
            if(i3 >= i4)
            {
                return;
            }
            int i5 = this.highlightIndex?1:0;
            label2: {
                label1: {
                    label0: {
                        if(i5 == 0)
                        {
                            break label0;
                        }
                        int i6 = this.labelIndex;
                        if(i3 == i6)
                        {
                            break label1;
                        }
                    }
                    StringBuilder a2 = new StringBuilder();
                    String s = this.infoStart;
                    StringBuilder a3 = a2.append(s);
                    StringBuilder a4 = a3.append("");
                    StringBuilder a5 = a4.append(i3);
                    String s0 = this.infoEnd;
                    StringBuilder a6 = a5.append(s0);
                    String s1 = a6.toString();
                    int i7 = this.stringHeight;
                    int i8 = i2 + i7;
                    a.drawString(s1, i0, i8);
                    break label2;
                }
                java.awt.Color a7 = a.getColor();
                java.awt.Color a8 = this.labelColor;
                a.setColor(a8);
                StringBuilder a9 = new StringBuilder();
                String s2 = this.infoStart;
                StringBuilder a10 = a9.append(s2);
                StringBuilder a11 = a10.append("");
                StringBuilder a12 = a11.append(i3);
                String s3 = this.infoEnd;
                StringBuilder a13 = a12.append(s3);
                String s4 = a13.toString();
                int i9 = this.stringHeight;
                int i10 = i2 + i9;
                a.drawString(s4, i0, i10);
                a.setColor(a7);
            }
            com.cim.AIA.Node[] a14 = this.dataNode;
            com.cim.AIA.Node a15 = a14[i3];
            int i11 = this.stringWidth;
            int i12 = i0 + i11;
            int i13 = this.XGap;
            int i14 = i12 + i13;
            java.awt.Point a16 = new java.awt.Point(i14, i2);
            a15.setPosition(a16);
            com.cim.AIA.Node[] a17 = this.dataNode;
            com.cim.AIA.Node a18 = a17[i3];
            a18.draw(a);
            int i15 = this.trueHeight;
            int i16 = i2 + i15;
            int i17 = this.YGap;
            int i18 = i16 + i17;
            int i19 = i3 + 1;
            i2 = i18;
            i3 = i19;
        }
    }
    
    public void draw(java.awt.Graphics a, java.awt.Point a0)
    {
        this.setLocation(a0);
        this.draw(a);
    }
    
    public int get(int i)
    {
        int[] a = this.data;
        int i0 = a[i];
        return i0;
    }
    
    public int getHeight()
    {
        int i = this.noInfo?1:0;
        if(i != 0)
        {
            this.reInit();
        }
        int i0 = this.trueHeight;
        int i1 = this.YGap;
        int i2 = i0 + i1;
        int i3 = this.size;
        int i4 = i2 * i3;
        return i4;
    }
    
    public java.awt.Point getLocation()
    {
        java.awt.Point a = this.location;
        return a;
    }
    
    public java.awt.Point getNodePosition(int i)
    {
        java.awt.Point a = new java.awt.Point();
        java.awt.Point a0 = this.location;
        int i0 = a0.x;
        int i1 = this.stringWidth;
        int i2 = i0 + i1;
        int i3 = this.XGap;
        int i4 = i2 + i3;
        a.x = i4;
        java.awt.Point a1 = this.location;
        int i5 = a1.y;
        int i6 = this.trueHeight;
        int i7 = this.YGap;
        int i8 = i6 + i7;
        int i9 = i8 * i;
        int i10 = i5 + i9;
        a.y = i10;
        return a;
    }
    
    public int getWidth()
    {
        int i = this.noInfo?1:0;
        if(i != 0)
        {
            this.reInit();
        }
        int i0 = this.stringWidth;
        int i1 = this.XGap;
        int i2 = i0 + i1;
        int i3 = this.nodeWidth;
        int i4 = i2 + i3;
        return i4;
    }
    
    public int getY()
    {
        java.awt.Point a = this.location;
        int i = a.y;
        return i;
    }
    
    public void highlight(int i)
    {
        com.cim.AIA.Node[] a = this.dataNode;
        com.cim.AIA.Node a0 = a[i];
        a0.highlight();
    }
    
    public void highlightIndex()
    {
        this.highlightIndex = true;
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
                this.highlightIndex = false;
                this.labelIndex = 0;
                return;
            }
            else
            {
                com.cim.AIA.Node[] a1 = this.dataNode;
                com.cim.AIA.Node a2 = new com.cim.AIA.Node((Object)"", 0);
                a1[i1] = a2;
                com.cim.AIA.Node[] a3 = this.dataNode;
                com.cim.AIA.Node a4 = a3[i1];
                java.awt.Color a5 = this.highlightColor;
                a4.setHighlightColor(a5);
                int i3 = i1 + 1;
                i1 = i3;
            }
        }
    }
    
    public void reInit()
    {
        KMPCanvas a = KMPApplet.theKMPCanvas;
        java.awt.Graphics a0 = a.getGraphics();
        java.awt.FontMetrics a1 = a0.getFontMetrics();
        int i = a1.getHeight();
        this.stringHeight = i;
        KMPCanvas a2 = KMPApplet.theKMPCanvas;
        java.awt.Graphics a3 = a2.getGraphics();
        java.awt.FontMetrics a4 = a3.getFontMetrics();
        StringBuilder a5 = new StringBuilder();
        String s = this.infoStart;
        StringBuilder a6 = a5.append(s);
        StringBuilder a7 = a6.append("");
        int i0 = this.size;
        StringBuilder a8 = a7.append(i0);
        String s0 = this.infoEnd;
        StringBuilder a9 = a8.append(s0);
        String s1 = a9.toString();
        int i1 = a4.stringWidth(s1);
        this.stringWidth = i1;
        com.cim.AIA.Node a10 = new com.cim.AIA.Node((Object)"", 0);
        int i2 = a10.getWidth();
        this.nodeWidth = i2;
        int i3 = a10.getHeight();
        this.nodeHeight = i3;
        int i4 = this.nodeHeight;
        int i5 = this.stringHeight;
        int i6 = Math.max(i4, i5);
        this.trueHeight = i6;
        this.noInfo = false;
    }
    
    public void restoreHighlightColor(java.awt.Color a)
    {
        java.awt.Color a0 = this.defaultHighlightColor;
        this.highlightColor = a0;
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
                com.cim.AIA.Node[] a1 = this.dataNode;
                com.cim.AIA.Node a2 = a1[i];
                java.awt.Color a3 = this.highlightColor;
                a2.setHighlightColor(a3);
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    public void set(int i, int i0)
    {
        int[] a = this.data;
        a[i] = i0;
        com.cim.AIA.Node[] a0 = this.dataNode;
        StringBuilder a1 = new StringBuilder();
        StringBuilder a2 = a1.append("");
        StringBuilder a3 = a2.append(i0);
        String s = a3.toString();
        com.cim.AIA.Node a4 = new com.cim.AIA.Node((Object)s, 0);
        a0[i] = a4;
        com.cim.AIA.Node[] a5 = this.dataNode;
        com.cim.AIA.Node a6 = a5[i];
        java.awt.Color a7 = this.highlightColor;
        a6.setHighlightColor(a7);
    }
    
    public void setHighlightColor(java.awt.Color a)
    {
        this.highlightColor = a;
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
                com.cim.AIA.Node[] a0 = this.dataNode;
                com.cim.AIA.Node a1 = a0[i];
                java.awt.Color a2 = this.highlightColor;
                a1.setHighlightColor(a2);
                int i1 = i + 1;
                i = i1;
            }
        }
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
        this.setLocation(i, i0);
    }
    
    public void unHighlight(int i)
    {
        com.cim.AIA.Node[] a = this.dataNode;
        com.cim.AIA.Node a0 = a[i];
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
    
    public void unHighlightIndex()
    {
        this.highlightIndex = false;
    }
}