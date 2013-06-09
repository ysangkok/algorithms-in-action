public class RadixTrieMultiNullNode extends RadixTrieMultiNode {
    final static int NODE_X_GAP = 18;
    final private static int radius = 9;
    
    public RadixTrieMultiNullNode(RadixTrieMultiInternalNode a)
    {
        super();
        this.parent = a;
        this.setIsLinkedIn();
        java.awt.Point a0 = new java.awt.Point();
        this.position = a0;
    }
    
    public RadixTrieMultiInternalNode getParent()
    {
        RadixTrieMultiInternalNode a = this.parent;
        return a;
    }
    
    public int getRadius()
    {
        return 0;
    }
    
    public void setPosition(java.awt.Point a)
    {
        java.awt.Point a0 = new java.awt.Point();
        RadixTrieMultiNullNode.upperLeft = a0;
        java.awt.Point a1 = RadixTrieMultiNullNode.upperLeft;
        int i = a.x;
        int i0 = i - 9;
        a1.x = i0;
        java.awt.Point a2 = RadixTrieMultiNullNode.upperLeft;
        int i1 = a.y;
        int i2 = i1 - 9;
        a2.y = i2;
        java.awt.Point a3 = new java.awt.Point();
        RadixTrieMultiNullNode.lowerRight = a3;
        java.awt.Point a4 = RadixTrieMultiNullNode.lowerRight;
        int i3 = a.x;
        int i4 = i3 + 9;
        a4.x = i4;
        java.awt.Point a5 = RadixTrieMultiNullNode.lowerRight;
        int i5 = a.y;
        int i6 = i5 + 9;
        a5.y = i6;
        java.awt.Point a6 = new java.awt.Point(a);
        this.position = a6;
    }
    
    public java.awt.Point getPosition()
    {
        java.awt.Point a = this.position;
        java.awt.Point a0 = new java.awt.Point(a);
        return a0;
    }
    
    protected java.awt.Point layout(int i, int i0)
    {
        int i1 = i + 9;
        java.awt.Point a = new java.awt.Point(i1, i0);
        this.position = a;
        java.awt.Point a0 = this.position;
        int i2 = a0.x;
        int i3 = i2 - 9;
        java.awt.Point a1 = RadixTrieMultiNullNode.upperLeft;
        int i4 = a1.x;
        if(i3 < i4)
        {
            java.awt.Point a2 = RadixTrieMultiNullNode.upperLeft;
            java.awt.Point a3 = this.position;
            int i5 = a3.x;
            int i6 = i5 - 9;
            a2.x = i6;
        }
        java.awt.Point a4 = this.position;
        int i7 = a4.y;
        int i8 = i7 - 9;
        java.awt.Point a5 = RadixTrieMultiNullNode.upperLeft;
        int i9 = a5.y;
        if(i8 < i9)
        {
            java.awt.Point a6 = RadixTrieMultiNullNode.upperLeft;
            java.awt.Point a7 = this.position;
            int i10 = a7.y;
            int i11 = i10 - 9;
            a6.y = i11;
        }
        java.awt.Point a8 = this.position;
        int i12 = a8.x;
        int i13 = i12 + 9;
        java.awt.Point a9 = RadixTrieMultiNullNode.lowerRight;
        int i14 = a9.x;
        if(i13 > i14)
        {
            java.awt.Point a10 = RadixTrieMultiNullNode.lowerRight;
            java.awt.Point a11 = this.position;
            int i15 = a11.x;
            int i16 = i15 + 9;
            a10.x = i16;
        }
        java.awt.Point a12 = this.position;
        int i17 = a12.y;
        int i18 = i17 + 9;
        java.awt.Point a13 = RadixTrieMultiNullNode.lowerRight;
        int i19 = a13.y;
        if(i18 > i19)
        {
            java.awt.Point a14 = RadixTrieMultiNullNode.lowerRight;
            java.awt.Point a15 = this.position;
            int i20 = a15.y;
            int i21 = i20 + 9;
            a14.y = i21;
        }
        int i22 = i + 18;
        int i23 = i0 - 40;
        java.awt.Point a16 = new java.awt.Point(i22, i23);
        return a16;
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