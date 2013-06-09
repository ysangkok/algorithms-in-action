public class SList {
    protected SList next;
    protected Object data;
    protected com.cim.AIA.Node node;
    protected com.cim.AIA.Node nextNode;
    protected com.cim.AIA.Line nextNodeLine;
    protected java.awt.Point pos;
    protected int height;
    
    public SList(Object a, int i)
    {
        super();
        java.awt.Point a0 = new java.awt.Point(0, 0);
        this.pos = a0;
        this.height = 0;
        this.next = null;
        this.data = a;
        Object a1 = this.data;
        com.cim.AIA.Node a2 = new com.cim.AIA.Node(a1, i);
        this.node = a2;
        com.cim.AIA.Node a3 = this.node;
        a3.setMarkersBelowValue(false);
        String s = localization.Messages.getString("SList.0");
        com.cim.AIA.Node a4 = new com.cim.AIA.Node((Object)s, i);
        this.nextNode = a4;
        com.cim.AIA.Node a5 = this.nextNode;
        a5.showObject(false);
        com.cim.AIA.Node a6 = this.node;
        int i0 = a6.getHeight();
        int i1 = 2 * i0;
        this.height = i1;
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