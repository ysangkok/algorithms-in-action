public class RadixTrieMultiExternalNode extends RadixTrieMultiNode {
    private com.cim.AIA.DataItem dataItem;
    final protected static int NODE_X_GAP = 24;
    final private static int radius = 9;
    private com.cim.AIA.HierarchyTree hierarchyTree;
    private java.awt.Color backgroundColor;
    private java.awt.Color currentBackgroundColor;
    private int level;
    private static RadixTrieMultiExternalNode newestNode;
    private static RadixTrieMultiExternalNode compareNode;
    final private static boolean isDrawFeet = 0;
    
    RadixTrieMultiExternalNode(com.cim.AIA.DataItem a)
    {
        super();
        java.awt.Color a0 = RadixTrieMultiColors.DEFAULT_NODE_COLOR;
        this.backgroundColor = a0;
        java.awt.Color a1 = this.backgroundColor;
        this.currentBackgroundColor = a1;
        this.dataItem = a;
        com.cim.AIA.HierarchyTree a2 = new com.cim.AIA.HierarchyTree();
        this.hierarchyTree = a2;
        StringBuilder a3 = new StringBuilder();
        StringBuilder a4 = a3.append("");
        int i = a.getKey();
        StringBuilder a5 = a4.append(i);
        String s = a5.toString();
        com.cim.AIA.Node a6 = new com.cim.AIA.Node((Object)s, 0);
        com.cim.AIA.HierarchyTree a7 = this.hierarchyTree;
        a7.add(a6);
        java.awt.Point a8 = new java.awt.Point(0, 0);
        this.position = a8;
        RadixTrieMultiExternalNode.newestNode = this;
    }
    
    public static void clearNewestNode()
    {
        RadixTrieMultiExternalNode.newestNode = null;
    }
    
    public static void clearCompareNode()
    {
        RadixTrieMultiExternalNode.compareNode = null;
    }
    
    public static void setCompareNode(RadixTrieMultiExternalNode a)
    {
        RadixTrieMultiExternalNode.compareNode = a;
    }
    
    public void setLevel(int i)
    {
        this.level = i;
    }
    
    public int getLevel()
    {
        int i = this.level;
        return i;
    }
    
    public RadixTrieMultiInternalNode getParent()
    {
        RadixTrieMultiInternalNode a = this.parent;
        return a;
    }
    
    public int getKey()
    {
        com.cim.AIA.DataItem a = this.dataItem;
        int i = a.getKey();
        return i;
    }
    
    public int getRadius()
    {
        return 9;
    }
    
    public java.awt.Point getPosition()
    {
        java.awt.Point a = this.position;
        java.awt.Point a0 = new java.awt.Point(a);
        return a0;
    }
    
    public void setPosition(java.awt.Point a)
    {
        java.awt.Point a0 = new java.awt.Point();
        RadixTrieMultiExternalNode.upperLeft = a0;
        java.awt.Point a1 = RadixTrieMultiExternalNode.upperLeft;
        int i = a.x;
        int i0 = i - 9;
        a1.x = i0;
        java.awt.Point a2 = RadixTrieMultiExternalNode.upperLeft;
        int i1 = a.y;
        int i2 = i1 - 9;
        a2.y = i2;
        java.awt.Point a3 = new java.awt.Point();
        RadixTrieMultiExternalNode.lowerRight = a3;
        java.awt.Point a4 = RadixTrieMultiExternalNode.lowerRight;
        int i3 = a.x;
        int i4 = i3 + 9;
        a4.x = i4;
        java.awt.Point a5 = RadixTrieMultiExternalNode.lowerRight;
        int i5 = a.y;
        int i6 = i5 + 9;
        a5.y = i6;
        java.awt.Point a6 = new java.awt.Point(a);
        this.position = a6;
    }
    
    protected synchronized java.awt.Point layout(int i, int i0)
    {
        int i1 = i + 12;
        java.awt.Point a = new java.awt.Point(i1, i0);
        this.position = a;
        java.awt.Point a0 = this.position;
        int i2 = a0.x;
        int i3 = i2 - 9;
        java.awt.Point a1 = RadixTrieMultiExternalNode.upperLeft;
        int i4 = a1.x;
        if(i3 < i4)
        {
            java.awt.Point a2 = RadixTrieMultiExternalNode.upperLeft;
            java.awt.Point a3 = this.position;
            int i5 = a3.x;
            int i6 = i5 - 9;
            a2.x = i6;
        }
        java.awt.Point a4 = this.position;
        int i7 = a4.y;
        int i8 = i7 - 9;
        java.awt.Point a5 = RadixTrieMultiExternalNode.upperLeft;
        int i9 = a5.y;
        if(i8 < i9)
        {
            java.awt.Point a6 = RadixTrieMultiExternalNode.upperLeft;
            java.awt.Point a7 = this.position;
            int i10 = a7.y;
            int i11 = i10 - 9;
            a6.y = i11;
        }
        java.awt.Point a8 = this.position;
        int i12 = a8.x;
        int i13 = i12 + 9;
        java.awt.Point a9 = RadixTrieMultiExternalNode.lowerRight;
        int i14 = a9.x;
        if(i13 > i14)
        {
            java.awt.Point a10 = RadixTrieMultiExternalNode.lowerRight;
            java.awt.Point a11 = this.position;
            int i15 = a11.x;
            int i16 = i15 + 9;
            a10.x = i16;
        }
        java.awt.Point a12 = this.position;
        int i17 = a12.y;
        int i18 = i17 + 9;
        java.awt.Point a13 = RadixTrieMultiExternalNode.lowerRight;
        int i19 = a13.y;
        if(i18 > i19)
        {
            java.awt.Point a14 = RadixTrieMultiExternalNode.lowerRight;
            java.awt.Point a15 = this.position;
            int i20 = a15.y;
            int i21 = i20 + 9;
            a14.y = i21;
        }
        int i22 = i + 24;
        int i23 = i0 - 40;
        java.awt.Point a16 = new java.awt.Point(i22, i23);
        return a16;
    }
    
    public java.awt.Rectangle getRectangle()
    {
        java.awt.Point a = this.position;
        this.setPosition(a);
        java.awt.Point a0 = RadixTrieMultiExternalNode.upperLeft;
        int i = a0.x;
        java.awt.Point a1 = RadixTrieMultiExternalNode.upperLeft;
        int i0 = a1.y;
        java.awt.Point a2 = RadixTrieMultiExternalNode.lowerRight;
        int i1 = a2.x;
        java.awt.Point a3 = RadixTrieMultiExternalNode.upperLeft;
        int i2 = a3.x;
        int i3 = i1 - i2;
        java.awt.Point a4 = RadixTrieMultiExternalNode.lowerRight;
        int i4 = a4.y;
        java.awt.Point a5 = RadixTrieMultiExternalNode.upperLeft;
        int i5 = a5.y;
        int i6 = i4 - i5;
        java.awt.Rectangle a6 = new java.awt.Rectangle(i, i0, i3, i6);
        return a6;
    }
    
    public void setBackgroundColor(java.awt.Color a)
    {
        this.backgroundColor = a;
    }
    
    public void draw(java.awt.Graphics a)
    {
        RadixTrieMultiInternalNode a0 = this.parent;
        if(a0 == null)
        {
            this.drawNode(a);
        }
    }
    
    public void drawNode(java.awt.Graphics a)
    {
        StringBuilder a0 = new StringBuilder();
        StringBuilder a1 = a0.append("");
        com.cim.AIA.DataItem a2 = this.dataItem;
        int i = a2.getKey();
        StringBuilder a3 = a1.append(i);
        String s = a3.toString();
        RadixTrieMultiExternalNode a4 = RadixTrieMultiExternalNode.newestNode;
        label1: {
            label0: {
                if(a4 != this)
                {
                    break label0;
                }
                java.awt.Color a5 = RadixTrieMultiColors.NEW_NODE_COLOR;
                this.currentBackgroundColor = a5;
                break label1;
            }
            RadixTrieMultiExternalNode a6 = RadixTrieMultiExternalNode.compareNode;
            if(a6 != this)
            {
                java.awt.Color a7 = this.backgroundColor;
                this.currentBackgroundColor = a7;
            }
            else
            {
                java.awt.Color a8 = RadixTrieMultiColors.COMPARE_NODE_COLOR;
                this.currentBackgroundColor = a8;
            }
        }
        java.awt.Color a9 = this.currentBackgroundColor;
        java.awt.Color a10 = a9.brighter();
        a.setColor(a10);
        java.awt.Point a11 = this.position;
        int i0 = a11.x;
        int i1 = i0 - 9;
        int i2 = i1 - 1;
        java.awt.Point a12 = this.position;
        int i3 = a12.y;
        int i4 = i3 - 9;
        int i5 = i4 - 1;
        a.fillOval(i2, i5, 18, 18);
        java.awt.Color a13 = this.currentBackgroundColor;
        java.awt.Color a14 = a13.darker();
        a.setColor(a14);
        java.awt.Point a15 = this.position;
        int i6 = a15.x;
        int i7 = i6 - 9;
        int i8 = i7 + 1;
        java.awt.Point a16 = this.position;
        int i9 = a16.y;
        int i10 = i9 - 9;
        int i11 = i10 + 1;
        a.fillOval(i8, i11, 18, 18);
        java.awt.Color a17 = this.currentBackgroundColor;
        a.setColor(a17);
        java.awt.Point a18 = this.position;
        int i12 = a18.x;
        int i13 = i12 - 9;
        int i14 = i13 + 1;
        java.awt.Point a19 = this.position;
        int i15 = a19.y;
        int i16 = i15 - 9;
        int i17 = i16 + 1;
        a.fillOval(i14, i17, 16, 16);
        java.awt.Color a20 = java.awt.Color.black;
        a.setColor(a20);
        java.awt.Point a21 = this.position;
        int i18 = a21.x;
        int i19 = i18 - 9;
        int i20 = i19 - 1;
        java.awt.Point a22 = this.position;
        int i21 = a22.y;
        int i22 = i21 - 9;
        int i23 = i22 - 1;
        a.drawOval(i20, i23, 20, 20);
        java.awt.Point a23 = this.position;
        int i24 = a23.x;
        java.awt.FontMetrics a24 = a.getFontMetrics();
        int i25 = a24.stringWidth(s);
        int i26 = i25 / 2;
        int i27 = i24 - i26;
        java.awt.Point a25 = this.position;
        int i28 = a25.y;
        java.awt.FontMetrics a26 = a.getFontMetrics();
        int i29 = a26.getHeight();
        int i30 = i29 / 3;
        int i31 = i28 + i30;
        a.drawString(s, i27, i31);
    }
    
    public com.cim.AIA.HierarchyTree getHierarchyTree()
    {
        com.cim.AIA.HierarchyTree a = this.hierarchyTree;
        return a;
    }
}