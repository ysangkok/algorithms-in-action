public class SkipNode {
    protected SkipNode[] next;
    protected Object data;
    protected int levels;
    protected com.cim.AIA.Node[] nodes;
    protected com.cim.AIA.Line[] nextNodesLines;
    protected java.awt.Point pos;
    protected int height;
    
    public SkipNode(Object a, int i, int i0)
    {
        super();
        java.awt.Point a0 = new java.awt.Point(0, 0);
        this.pos = a0;
        this.height = 0;
        SkipNode[] a1 = new SkipNode[i0];
        this.next = a1;
        int i1 = 0;
        while(true)
        {
            if(i1 >= i0)
            {
                break;
            }
            else
            {
                SkipNode[] a2 = this.next;
                a2[i1] = null;
                int i2 = i1 + 1;
                i1 = i2;
            }
        }
        this.data = a;
        this.levels = i0;
        int i3 = this.levels;
        int i4 = i3 + 1;
        com.cim.AIA.Node[] a3 = new com.cim.AIA.Node[i4];
        this.nodes = a3;
        int i5 = this.levels;
        int i6 = i5 + 1;
        com.cim.AIA.Line[] a4 = new com.cim.AIA.Line[i6];
        this.nextNodesLines = a4;
        com.cim.AIA.Node[] a5 = this.nodes;
        Object a6 = this.data;
        com.cim.AIA.Node a7 = new com.cim.AIA.Node(a6, i);
        a5[0] = a7;
        com.cim.AIA.Node[] a8 = this.nodes;
        com.cim.AIA.Node a9 = a8[0];
        a9.setMarkersBelowValue(false);
        int i7 = 1;
        while(true)
        {
            com.cim.AIA.Node[] a10 = this.nodes;
            int i8 = a10.length;
            if(i7 >= i8)
            {
                break;
            }
            else
            {
                com.cim.AIA.Node[] a11 = this.nodes;
                String s = localization.Messages.getString("SkipNode.0");
                com.cim.AIA.Node a12 = new com.cim.AIA.Node((Object)s, i);
                a11[i7] = a12;
                com.cim.AIA.Node[] a13 = this.nodes;
                com.cim.AIA.Node a14 = a13[i7];
                a14.showObject(false);
                com.cim.AIA.Node[] a15 = this.nodes;
                com.cim.AIA.Node a16 = a15[i7];
                a16.setMarkersBelowValue(false);
                int i9 = i7 + 1;
                i7 = i9;
            }
        }
        int i10 = 0;
        while(true)
        {
            com.cim.AIA.Line[] a17 = this.nextNodesLines;
            int i11 = a17.length;
            if(i10 >= i11)
            {
                int i12 = this.levels;
                int i13 = i12 + 1;
                com.cim.AIA.Node[] a18 = this.nodes;
                com.cim.AIA.Node a19 = a18[0];
                int i14 = a19.getHeight();
                int i15 = i13 * i14;
                this.height = i15;
                return;
            }
            else
            {
                com.cim.AIA.Line[] a20 = this.nextNodesLines;
                java.awt.Point a21 = new java.awt.Point(0, 0);
                java.awt.Point a22 = new java.awt.Point(0, 0);
                com.cim.AIA.Line a23 = new com.cim.AIA.Line(a21, a22);
                a20[i10] = a23;
                int i16 = i10 + 1;
                i10 = i16;
            }
        }
    }
    
    public java.awt.Point getPosition()
    {
        java.awt.Point a = this.pos;
        return a;
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
    
    public int getHeight()
    {
        int i = this.height;
        return i;
    }
    
    public void incrementHeight(int i)
    {
        int i0 = this.height;
        int i1 = i0 + i;
        this.height = i1;
    }
}