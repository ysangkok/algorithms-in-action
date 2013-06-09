public class RadixTrieInternalNode extends RadixTrieNode {
    private RadixTrieNode right;
    private RadixTrieNode left;
    final protected static int NODE_X_GAP = 8;
    final private static int radius = 3;
    final private static int nullWidth = 6;
    private com.cim.AIA.HierarchyTree hierarchyTree;
    private java.awt.Color backgroundColor;
    private java.awt.Color DEFAULT_COLOR;
    
    RadixTrieInternalNode()
    {
        super();
        java.awt.Color a = java.awt.Color.orange;
        this.backgroundColor = a;
        java.awt.Color a0 = java.awt.Color.black;
        this.DEFAULT_COLOR = a0;
        java.awt.Point a1 = new java.awt.Point(0, 0);
        this.position = a1;
        com.cim.AIA.HierarchyTree a2 = new com.cim.AIA.HierarchyTree();
        this.hierarchyTree = a2;
        com.cim.AIA.Node a3 = new com.cim.AIA.Node((Object)"", 0);
        a3.setWidth(6);
        a3.setHeight(6);
        com.cim.AIA.HierarchyTree a4 = this.hierarchyTree;
        a4.add(a3);
        com.cim.AIA.HierarchyTree a5 = this.hierarchyTree;
        com.cim.AIA.HierarchyTree a6 = new com.cim.AIA.HierarchyTree();
        a5.addChild((com.cim.AIA.Tree)a6);
        com.cim.AIA.HierarchyTree a7 = this.hierarchyTree;
        com.cim.AIA.HierarchyTree a8 = new com.cim.AIA.HierarchyTree();
        a7.addChild((com.cim.AIA.Tree)a8);
        com.cim.AIA.HierarchyTree a9 = this.hierarchyTree;
        a9.setDrawBorder(false);
        RadixTrieNullNode a10 = new RadixTrieNullNode(this);
        this.left = a10;
        RadixTrieNullNode a11 = new RadixTrieNullNode(this);
        this.right = a11;
    }
    
    public RadixTrieInternalNode getParent()
    {
        RadixTrieInternalNode a = this.parent;
        return a;
    }
    
    public int getRadius()
    {
        return 3;
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
        RadixTrieInternalNode.upperLeft = a0;
        java.awt.Point a1 = RadixTrieInternalNode.upperLeft;
        int i = a.y;
        int i0 = i - 3;
        a1.y = i0;
        java.awt.Point a2 = RadixTrieInternalNode.upperLeft;
        int i1 = a.x;
        int i2 = i1 - 3;
        a2.x = i2;
        java.awt.Point a3 = new java.awt.Point();
        RadixTrieInternalNode.lowerRight = a3;
        java.awt.Point a4 = RadixTrieInternalNode.lowerRight;
        int i3 = a.y;
        int i4 = i3 + 3;
        a4.y = i4;
        java.awt.Point a5 = RadixTrieInternalNode.lowerRight;
        int i5 = a.x;
        int i6 = i5 + 3;
        a5.x = i6;
        RadixTrieNode a6 = this.left;
        if(a6 != null)
        {
            RadixTrieNode a7 = this.left;
            int i7 = a.x;
            int i8 = i7 - 8;
            int i9 = a.y;
            int i10 = i9 + 20;
            java.awt.Point a8 = a7.layout(i8, i10);
            RadixTrieNode a9 = this.left;
            int i11 = a.x;
            int i12 = a.x;
            int i13 = i11 + i12;
            int i14 = a8.x;
            int i15 = i13 - i14;
            int i16 = i15 - 8;
            int i17 = a.y;
            int i18 = i17 + 20;
            java.awt.Point a10 = a9.layout(i16, i18);
        }
        this.position = a;
        java.awt.Point a11 = new java.awt.Point(a);
        RadixTrieNode a12 = this.right;
        if(a12 != null)
        {
            RadixTrieNode a13 = this.right;
            int i19 = a11.x;
            int i20 = i19 + 4;
            int i21 = a11.y;
            int i22 = i21 + 20;
            java.awt.Point a14 = a13.layout(i20, i22);
        }
    }
    
    protected synchronized java.awt.Point layout(int i, int i0)
    {
        java.awt.Point a = null;
        java.awt.Point a0 = null;
        java.awt.Point a1 = new java.awt.Point(i, i0);
        RadixTrieNode a2 = this.left;
        if(a2 == null)
        {
            a = a1;
        }
        else
        {
            RadixTrieNode a3 = this.left;
            int i1 = i0 + 20;
            java.awt.Point a4 = a3.layout(i, i1);
            a = a4;
        }
        int i2 = a.x;
        int i3 = i2 + 4;
        a.x = i3;
        this.position = a;
        int i4 = a.x;
        int i5 = i4 - 3;
        java.awt.Point a5 = RadixTrieInternalNode.upperLeft;
        int i6 = a5.x;
        if(i5 < i6)
        {
            java.awt.Point a6 = RadixTrieInternalNode.upperLeft;
            int i7 = a.x;
            int i8 = i7 - 3;
            a6.x = i8;
        }
        int i9 = a.y;
        int i10 = i9 - 3;
        java.awt.Point a7 = RadixTrieInternalNode.upperLeft;
        int i11 = a7.y;
        if(i10 < i11)
        {
            java.awt.Point a8 = RadixTrieInternalNode.upperLeft;
            int i12 = a.y;
            int i13 = i12 - 3;
            a8.y = i13;
        }
        int i14 = a.x;
        int i15 = i14 + 3;
        java.awt.Point a9 = RadixTrieInternalNode.lowerRight;
        int i16 = a9.x;
        if(i15 > i16)
        {
            java.awt.Point a10 = RadixTrieInternalNode.lowerRight;
            int i17 = a.x;
            int i18 = i17 + 3;
            a10.x = i18;
        }
        int i19 = a.y;
        int i20 = i19 + 3;
        java.awt.Point a11 = RadixTrieInternalNode.lowerRight;
        int i21 = a11.y;
        if(i20 > i21)
        {
            java.awt.Point a12 = RadixTrieInternalNode.lowerRight;
            int i22 = a.y;
            int i23 = i22 + 3;
            a12.y = i23;
        }
        RadixTrieNode a13 = this.right;
        if(a13 == null)
        {
            a0 = a;
        }
        else
        {
            RadixTrieNode a14 = this.right;
            int i24 = a.x;
            int i25 = i24 + 4;
            int i26 = a.y;
            int i27 = i26 + 20;
            java.awt.Point a15 = a14.layout(i25, i27);
            a0 = a15;
        }
        int i28 = a0.x;
        int i29 = a0.y;
        int i30 = i29 - 20;
        java.awt.Point a16 = new java.awt.Point(i28, i30);
        return a16;
    }
    
    public java.awt.Rectangle getRectangle()
    {
        java.awt.Point a = this.position;
        if(a == null)
        {
            java.awt.Point a0 = new java.awt.Point();
            this.position = a0;
        }
        java.awt.Point a1 = this.position;
        this.setPosition(a1);
        java.awt.Point a2 = RadixTrieInternalNode.upperLeft;
        int i = a2.x;
        java.awt.Point a3 = RadixTrieInternalNode.upperLeft;
        int i0 = a3.y;
        java.awt.Point a4 = RadixTrieInternalNode.lowerRight;
        int i1 = a4.x;
        java.awt.Point a5 = RadixTrieInternalNode.upperLeft;
        int i2 = a5.x;
        int i3 = i1 - i2;
        java.awt.Point a6 = RadixTrieInternalNode.lowerRight;
        int i4 = a6.y;
        java.awt.Point a7 = RadixTrieInternalNode.upperLeft;
        int i5 = a7.y;
        int i6 = i4 - i5;
        java.awt.Rectangle a8 = new java.awt.Rectangle(i, i0, i3, i6);
        return a8;
    }
    
    public void draw(java.awt.Graphics a)
    {
        java.awt.Color a0 = this.DEFAULT_COLOR;
        a.setColor(a0);
        RadixTrieNode a1 = this.left;
        label1: {
            label0: {
                if(a1 != null)
                {
                    break label0;
                }
                java.awt.Point a2 = this.position;
                int i = a2.x;
                java.awt.Point a3 = this.position;
                int i0 = a3.y;
                java.awt.Point a4 = this.position;
                int i1 = a4.x;
                int i2 = i1 - 8;
                java.awt.Point a5 = this.position;
                int i3 = a5.y;
                int i4 = i3 + 13;
                a.drawLine(i, i0, i2, i4);
                break label1;
            }
            RadixTrieNode a6 = this.left;
            int i5 = a6.getIsLinkedIn()?1:0;
            label2: {
                com.cim.AIA.Line a7 = null;
                if(i5 == 0)
                {
                    break label2;
                }
                RadixTrieNode a8 = this.left;
                int i6 = a8.getIsOnPath()?1:0;
                if(i6 == 0)
                {
                    java.awt.Point a9 = this.position;
                    int i7 = a9.x;
                    java.awt.Point a10 = this.position;
                    int i8 = a10.y;
                    RadixTrieNode a11 = this.left;
                    java.awt.Point a12 = a11.position;
                    int i9 = a12.x;
                    RadixTrieNode a13 = this.left;
                    java.awt.Point a14 = a13.position;
                    int i10 = a14.y;
                    java.awt.Color a15 = java.awt.Color.black;
                    com.cim.AIA.Line a16 = new com.cim.AIA.Line(i7, i8, i9, i10, a15);
                    a7 = a16;
                }
                else
                {
                    java.awt.Point a17 = this.position;
                    int i11 = a17.x;
                    java.awt.Point a18 = this.position;
                    int i12 = a18.y;
                    RadixTrieNode a19 = this.left;
                    java.awt.Point a20 = a19.position;
                    int i13 = a20.x;
                    RadixTrieNode a21 = this.left;
                    java.awt.Point a22 = a21.position;
                    int i14 = a22.y;
                    java.awt.Color a23 = java.awt.Color.red;
                    com.cim.AIA.Line a24 = new com.cim.AIA.Line(i11, i12, i13, i14, a23);
                    a24.showAsThick(true);
                    a7 = a24;
                }
                a7.draw(a);
            }
            RadixTrieNode a25 = this.left;
            a25.draw(a);
        }
        RadixTrieNode a26 = this.right;
        label4: {
            label3: {
                if(a26 != null)
                {
                    break label3;
                }
                java.awt.Point a27 = this.position;
                int i15 = a27.x;
                java.awt.Point a28 = this.position;
                int i16 = a28.y;
                java.awt.Point a29 = this.position;
                int i17 = a29.x;
                int i18 = i17 + 8;
                java.awt.Point a30 = this.position;
                int i19 = a30.y;
                int i20 = i19 + 13;
                a.drawLine(i15, i16, i18, i20);
                break label4;
            }
            RadixTrieNode a31 = this.right;
            int i21 = a31.getIsLinkedIn()?1:0;
            label5: {
                com.cim.AIA.Line a32 = null;
                if(i21 == 0)
                {
                    break label5;
                }
                RadixTrieNode a33 = this.right;
                int i22 = a33.getIsOnPath()?1:0;
                if(i22 == 0)
                {
                    java.awt.Point a34 = this.position;
                    int i23 = a34.x;
                    java.awt.Point a35 = this.position;
                    int i24 = a35.y;
                    RadixTrieNode a36 = this.right;
                    java.awt.Point a37 = a36.position;
                    int i25 = a37.x;
                    RadixTrieNode a38 = this.right;
                    java.awt.Point a39 = a38.position;
                    int i26 = a39.y;
                    java.awt.Color a40 = java.awt.Color.black;
                    com.cim.AIA.Line a41 = new com.cim.AIA.Line(i23, i24, i25, i26, a40);
                    a32 = a41;
                }
                else
                {
                    java.awt.Point a42 = this.position;
                    int i27 = a42.x;
                    java.awt.Point a43 = this.position;
                    int i28 = a43.y;
                    RadixTrieNode a44 = this.right;
                    java.awt.Point a45 = a44.position;
                    int i29 = a45.x;
                    RadixTrieNode a46 = this.right;
                    java.awt.Point a47 = a46.position;
                    int i30 = a47.y;
                    java.awt.Color a48 = java.awt.Color.red;
                    com.cim.AIA.Line a49 = new com.cim.AIA.Line(i27, i28, i29, i30, a48);
                    a49.showAsThick(true);
                    a32 = a49;
                }
                a32.draw(a);
            }
            RadixTrieNode a50 = this.right;
            a50.draw(a);
        }
        RadixTrieInternalNode a51 = this.parent;
        if(a51 == null)
        {
            this.drawNode(a);
        }
        RadixTrieNode a52 = this.left;
        if(a52 != null)
        {
            RadixTrieNode a53 = this.left;
            a53.drawNode(a);
        }
        RadixTrieNode a54 = this.right;
        if(a54 != null)
        {
            RadixTrieNode a55 = this.right;
            a55.drawNode(a);
        }
    }
    
    public void drawNode(java.awt.Graphics a)
    {
        java.awt.Color a0 = this.backgroundColor;
        java.awt.Color a1 = a0.brighter();
        a.setColor(a1);
        java.awt.Point a2 = this.position;
        int i = a2.x;
        int i0 = i - 3;
        int i1 = i0 - 1;
        java.awt.Point a3 = this.position;
        int i2 = a3.y;
        int i3 = i2 - 3;
        int i4 = i3 - 1;
        a.fillOval(i1, i4, 6, 6);
        java.awt.Color a4 = this.backgroundColor;
        java.awt.Color a5 = a4.darker();
        a.setColor(a5);
        java.awt.Point a6 = this.position;
        int i5 = a6.x;
        int i6 = i5 - 3;
        int i7 = i6 + 1;
        java.awt.Point a7 = this.position;
        int i8 = a7.y;
        int i9 = i8 - 3;
        int i10 = i9 + 1;
        a.fillOval(i7, i10, 6, 6);
        java.awt.Color a8 = this.backgroundColor;
        a.setColor(a8);
        java.awt.Point a9 = this.position;
        int i11 = a9.x;
        int i12 = i11 - 3;
        java.awt.Point a10 = this.position;
        int i13 = a10.y;
        int i14 = i13 - 3;
        a.fillOval(i12, i14, 6, 6);
        java.awt.Color a11 = this.DEFAULT_COLOR;
        a.setColor(a11);
    }
    
    public void setLeft(RadixTrieNode a)
    {
        this.left = a;
        RadixTrieNode a0 = this.left;
        a0.parent = this;
        com.cim.AIA.HierarchyTree a1 = this.hierarchyTree;
        com.cim.AIA.HierarchyTree a2 = this.hierarchyTree;
        com.cim.AIA.Tree a3 = a2.getChild(0);
        a1.removeChild(a3);
        com.cim.AIA.HierarchyTree a4 = this.hierarchyTree;
        com.cim.AIA.HierarchyTree a5 = a.getHierarchyTree();
        a4.insertChildAt((com.cim.AIA.Tree)a5, 0);
    }
    
    public void setRight(RadixTrieNode a)
    {
        this.right = a;
        RadixTrieNode a0 = this.right;
        a0.parent = this;
        com.cim.AIA.HierarchyTree a1 = this.hierarchyTree;
        com.cim.AIA.HierarchyTree a2 = this.hierarchyTree;
        com.cim.AIA.Tree a3 = a2.getChild(1);
        a1.removeChild(a3);
        com.cim.AIA.HierarchyTree a4 = this.hierarchyTree;
        com.cim.AIA.HierarchyTree a5 = a.getHierarchyTree();
        a4.insertChildAt((com.cim.AIA.Tree)a5, 1);
    }
    
    public void setBackgroundColor(java.awt.Color a)
    {
        this.backgroundColor = a;
    }
    
    public RadixTrieNode getLeft()
    {
        RadixTrieNode a = this.left;
        return a;
    }
    
    public RadixTrieNode getRight()
    {
        RadixTrieNode a = this.right;
        return a;
    }
    
    public com.cim.AIA.HierarchyTree getHierarchyTree()
    {
        com.cim.AIA.HierarchyTree a = this.hierarchyTree;
        return a;
    }
}