public class RadixTrieIterNullNode extends RadixTrieIterNode {
    final static int NODE_X_GAP = 15;
    
    public RadixTrieIterNullNode(RadixTrieIterInternalNode a)
    {
        super();
        this.parent = a;
        this.setIsLinkedIn();
        java.awt.Point a0 = new java.awt.Point();
        this.position = a0;
    }
    
    public RadixTrieIterInternalNode getParent()
    {
        RadixTrieIterInternalNode a = this.parent;
        return a;
    }
    
    public int getRadius()
    {
        return 0;
    }
    
    public void setPosition(java.awt.Point a)
    {
        java.awt.Point a0 = new java.awt.Point(a);
        this.position = a0;
    }
    
    public java.awt.Point getPosition()
    {
        java.awt.Point a = this.position;
        return a;
    }
    
    protected java.awt.Point layout(int i, int i0)
    {
        int i1 = i + 7;
        java.awt.Point a = new java.awt.Point(i1, i0);
        this.position = a;
        int i2 = i + 15;
        int i3 = i0 - 20;
        java.awt.Point a0 = new java.awt.Point(i2, i3);
        return a0;
    }
    
    public synchronized java.awt.Rectangle getRectangle()
    {
        java.awt.Rectangle a = new java.awt.Rectangle();
        return a;
    }
    
    public com.cim.AIA.HierarchyTree getHierarchyTree()
    {
        return null;
    }
    
    public void setBackgroundColor(java.awt.Color a)
    {
    }
    
    public void draw(java.awt.Graphics a)
    {
    }
    
    public void drawNode(java.awt.Graphics a)
    {
    }
}