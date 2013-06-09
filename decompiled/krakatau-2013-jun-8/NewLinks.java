public class NewLinks {
    final private static java.awt.Color LINECOLOR;
    private com.cim.AIA.Node srcNode;
    private com.cim.AIA.Node destNode;
    private boolean direction;
    
    public NewLinks(com.cim.AIA.Node a, boolean b, com.cim.AIA.Node a0)
    {
        super();
        int i = b?1:0;
        this.srcNode = a;
        this.destNode = a0;
        this.direction = i != 0;
    }
    
    public com.cim.AIA.Line getLine()
    {
        int i = 0;
        int i0 = this.direction?1:0;
        if(i0 != 0)
        {
            com.cim.AIA.Node a = this.srcNode;
            int i1 = a.getX();
            com.cim.AIA.Node a0 = this.srcNode;
            int i2 = a0.getWidth();
            int i3 = i1 + i2;
            i = i3;
        }
        else
        {
            com.cim.AIA.Node a1 = this.srcNode;
            int i4 = a1.getX();
            i = i4;
        }
        com.cim.AIA.Node a2 = this.srcNode;
        int i5 = a2.getY();
        com.cim.AIA.Node a3 = this.srcNode;
        int i6 = a3.getHeight();
        int i7 = i5 + i6;
        com.cim.AIA.Node a4 = this.destNode;
        int i8 = a4.getX();
        com.cim.AIA.Node a5 = this.destNode;
        int i9 = a5.getWidth();
        int i10 = i9 / 2;
        int i11 = i8 + i10;
        com.cim.AIA.Node a6 = this.destNode;
        int i12 = a6.getY();
        com.cim.AIA.Line a7 = new com.cim.AIA.Line(i, i7, i11, i12);
        a7.showArrowHead(true);
        a7.showAsDotted(true);
        a7.setDistanceFromStartForArrowHead(-3);
        java.awt.Color a8 = NewLinks.LINECOLOR;
        a7.setColor(a8);
        return a7;
    }
    
    static
    {
        java.awt.Color a = java.awt.Color.blue;
        NewLinks.LINECOLOR = a;
    }
}