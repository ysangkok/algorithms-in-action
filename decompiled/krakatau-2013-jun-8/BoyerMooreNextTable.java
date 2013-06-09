public class BoyerMooreNextTable implements com.cim.AIA.Drawable {
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
    
    public BoyerMooreNextTable(int i)
    {
        super();
        java.awt.Point a = new java.awt.Point();
        this.location = a;
        this.infoStart = "nextTable [";
        this.infoEnd = "] =";
        this.noInfo = true;
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
            else
            {
                StringBuilder a2 = new StringBuilder();
                String s = this.infoStart;
                StringBuilder a3 = a2.append(s);
                StringBuilder a4 = a3.append("");
                StringBuilder a5 = a4.append(i3);
                String s0 = this.infoEnd;
                StringBuilder a6 = a5.append(s0);
                String s1 = a6.toString();
                int i5 = this.stringHeight;
                int i6 = i2 + i5;
                a.drawString(s1, i0, i6);
                com.cim.AIA.Node[] a7 = this.dataNode;
                com.cim.AIA.Node a8 = a7[i3];
                int i7 = this.stringWidth;
                int i8 = i0 + i7;
                int i9 = this.XGap;
                int i10 = i8 + i9;
                java.awt.Point a9 = new java.awt.Point(i10, i2);
                a8.setPosition(a9);
                com.cim.AIA.Node[] a10 = this.dataNode;
                com.cim.AIA.Node a11 = a10[i3];
                a11.draw(a);
                int i11 = this.trueHeight;
                int i12 = i2 + i11;
                int i13 = this.YGap;
                int i14 = i12 + i13;
                int i15 = i3 + 1;
                i2 = i14;
                i3 = i15;
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
    
    public void highlight(int i)
    {
        com.cim.AIA.Node[] a = this.dataNode;
        com.cim.AIA.Node a0 = a[i];
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
        BoyerMooreCanvas a2 = BoyerMooreApplet.theBoyerMooreCanvas;
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
}