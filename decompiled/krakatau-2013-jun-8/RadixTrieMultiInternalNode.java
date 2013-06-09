public class RadixTrieMultiInternalNode extends RadixTrieMultiNode {
    private java.util.Vector radixTrieNodes;
    final protected static int NODE_X_GAP = 8;
    final private static int radius = 3;
    final private static int nullWidth = 6;
    private com.cim.AIA.HierarchyTree hierarchyTree;
    private java.awt.Color backgroundColor;
    private java.awt.Color DEFAULT_COLOR;
    
    RadixTrieMultiInternalNode()
    {
        super();
        java.util.Vector a = new java.util.Vector();
        this.radixTrieNodes = a;
        java.awt.Color a0 = java.awt.Color.orange;
        this.backgroundColor = a0;
        java.awt.Color a1 = java.awt.Color.black;
        this.DEFAULT_COLOR = a1;
        java.awt.Point a2 = new java.awt.Point(0, 0);
        this.position = a2;
        com.cim.AIA.HierarchyTree a3 = new com.cim.AIA.HierarchyTree();
        this.hierarchyTree = a3;
        com.cim.AIA.Node a4 = new com.cim.AIA.Node((Object)"", 0);
        int i = RadixTrieMulti.NO_OF_NODES;
        int i0 = i * 3;
        a4.setWidth(i0);
        a4.setHeight(6);
        com.cim.AIA.HierarchyTree a5 = this.hierarchyTree;
        a5.add(a4);
        int i1 = 0;
        while(true)
        {
            int i2 = RadixTrieMulti.NO_OF_NODES;
            if(i1 >= i2)
            {
                break;
            }
            else
            {
                com.cim.AIA.HierarchyTree a6 = this.hierarchyTree;
                com.cim.AIA.HierarchyTree a7 = new com.cim.AIA.HierarchyTree();
                a6.addChild((com.cim.AIA.Tree)a7);
                int i3 = i1 + 1;
                i1 = i3;
            }
        }
        com.cim.AIA.HierarchyTree a8 = this.hierarchyTree;
        a8.setDrawBorder(false);
        int i4 = 0;
        while(true)
        {
            int i5 = RadixTrieMulti.NO_OF_NODES;
            if(i4 >= i5)
            {
                return;
            }
            else
            {
                java.util.Vector a9 = this.radixTrieNodes;
                RadixTrieMultiNullNode a10 = new RadixTrieMultiNullNode(this);
                a9.addElement((Object)a10);
                int i6 = i4 + 1;
                i4 = i6;
            }
        }
    }
    
    public RadixTrieMultiInternalNode getParent()
    {
        RadixTrieMultiInternalNode a = this.parent;
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
        java.awt.Point a0 = null;
        java.awt.Point a1 = new java.awt.Point(a);
        java.awt.Point a2 = new java.awt.Point();
        RadixTrieMultiInternalNode.upperLeft = a2;
        java.awt.Point a3 = RadixTrieMultiInternalNode.upperLeft;
        int i = a.y;
        int i0 = i - 3;
        a3.y = i0;
        java.awt.Point a4 = RadixTrieMultiInternalNode.upperLeft;
        int i1 = a.x;
        int i2 = i1 - 3;
        a4.x = i2;
        java.awt.Point a5 = RadixTrieMultiInternalNode.upperLeft;
        java.awt.Point a6 = new java.awt.Point(a5);
        java.awt.Point a7 = new java.awt.Point();
        RadixTrieMultiInternalNode.lowerRight = a7;
        java.awt.Point a8 = RadixTrieMultiInternalNode.lowerRight;
        int i3 = a.y;
        int i4 = i3 + 3;
        a8.y = i4;
        java.awt.Point a9 = RadixTrieMultiInternalNode.lowerRight;
        int i5 = a.x;
        int i6 = i5 + 3;
        a9.x = i6;
        java.awt.Point a10 = RadixTrieMultiInternalNode.lowerRight;
        java.awt.Point a11 = new java.awt.Point(a10);
        java.awt.Point a12 = new java.awt.Point(a);
        this.position = a12;
        java.awt.Point a13 = a1;
        int i7 = 0;
        while(true)
        {
            java.awt.Point a14 = null;
            java.awt.Point a15 = null;
            int i8 = RadixTrieMulti.NO_OF_NODES;
            a0 = a13;
            int i9 = i8 / 2;
            java.awt.Point a16 = a0;
            if(i7 >= i9)
            {
                break;
            }
            else
            {
                a14 = a16;
            }
            java.util.Vector a17 = this.radixTrieNodes;
            java.awt.Point a18 = a14;
            Object a19 = a17.elementAt(i7);
            java.awt.Point a20 = a18;
            RadixTrieMultiNode dummy = (RadixTrieMultiNode)a19;
            RadixTrieMultiNode a21 = (RadixTrieMultiNode)a19;
            java.awt.Point a22 = a20;
            java.awt.Point a23 = a22;
            if(a21 == null)
            {
                a15 = a23;
            }
            else
            {
                int i10 = a22.x;
                int i11 = a22.y;
                int i12 = i11 + 40;
                java.awt.Point a24 = a21.layout(i10, i12);
                a15 = a24;
            }
            int i13 = i7 + 1;
            a13 = a15;
            i7 = i13;
        }
        java.awt.Point a25 = new java.awt.Point(a6);
        RadixTrieMultiInternalNode.upperLeft = a25;
        java.awt.Point a26 = new java.awt.Point(a11);
        RadixTrieMultiInternalNode.lowerRight = a26;
        int i14 = a.x;
        int i15 = a0.x;
        int i16 = a.x;
        int i17 = i15 - i16;
        int i18 = i14 - i17;
        a.x = i18;
        java.awt.Point a27 = RadixTrieMultiInternalNode.lowerRight;
        int i19 = a.x;
        int i20 = i19 + 3;
        a27.x = i20;
        java.awt.Point a28 = RadixTrieMultiInternalNode.upperLeft;
        int i21 = a.x;
        int i22 = i21 - 3;
        a28.x = i22;
        java.awt.Point a29 = a;
        int i23 = 0;
        while(true)
        {
            java.awt.Point a30 = null;
            java.awt.Point a31 = null;
            int i24 = RadixTrieMulti.NO_OF_NODES;
            java.awt.Point a32 = a29;
            java.awt.Point a33 = a32;
            if(i23 >= i24)
            {
                return;
            }
            else
            {
                a30 = a33;
            }
            java.util.Vector a34 = this.radixTrieNodes;
            java.awt.Point a35 = a30;
            Object a36 = a34.elementAt(i23);
            java.awt.Point a37 = a35;
            RadixTrieMultiNode dummy0 = (RadixTrieMultiNode)a36;
            RadixTrieMultiNode a38 = (RadixTrieMultiNode)a36;
            java.awt.Point a39 = a37;
            java.awt.Point a40 = a39;
            if(a38 == null)
            {
                a31 = a40;
            }
            else
            {
                int i25 = a39.x;
                int i26 = a39.y;
                int i27 = i26 + 40;
                java.awt.Point a41 = a38.layout(i25, i27);
                a31 = a41;
            }
            int i28 = i23 + 1;
            a29 = a31;
            i23 = i28;
        }
    }
    
    protected synchronized java.awt.Point layout(int i, int i0)
    {
        java.awt.Point a = new java.awt.Point(i, i0);
        java.awt.Point a0 = new java.awt.Point(i, i0);
        int i1 = a.x;
        int i2 = i1 - 3;
        java.awt.Point a1 = RadixTrieMultiInternalNode.upperLeft;
        int i3 = a1.x;
        if(i2 < i3)
        {
            java.awt.Point a2 = RadixTrieMultiInternalNode.upperLeft;
            int i4 = a.x;
            int i5 = i4 - 3;
            a2.x = i5;
        }
        int i6 = a.y;
        int i7 = i6 - 3;
        java.awt.Point a3 = RadixTrieMultiInternalNode.upperLeft;
        int i8 = a3.y;
        if(i7 < i8)
        {
            java.awt.Point a4 = RadixTrieMultiInternalNode.upperLeft;
            int i9 = a.y;
            int i10 = i9 - 3;
            a4.y = i10;
        }
        int i11 = a.x;
        int i12 = i11 + 3;
        java.awt.Point a5 = RadixTrieMultiInternalNode.lowerRight;
        int i13 = a5.x;
        if(i12 > i13)
        {
            java.awt.Point a6 = RadixTrieMultiInternalNode.lowerRight;
            int i14 = a.x;
            int i15 = i14 + 3;
            a6.x = i15;
        }
        int i16 = a.y;
        int i17 = i16 + 3;
        java.awt.Point a7 = RadixTrieMultiInternalNode.lowerRight;
        int i18 = a7.y;
        if(i17 > i18)
        {
            java.awt.Point a8 = RadixTrieMultiInternalNode.lowerRight;
            int i19 = a.y;
            int i20 = i19 + 3;
            a8.y = i20;
        }
        java.awt.Point a9 = a;
        int i21 = 0;
        while(true)
        {
            int i22 = RadixTrieMulti.NO_OF_NODES;
            if(i21 >= i22)
            {
                java.awt.Point a10 = this.position;
                java.awt.Point a11 = RadixTrieMultiInternalNode.lowerRight;
                int i23 = a11.x;
                int i24 = i23 - i;
                int i25 = i24 / 2;
                int i26 = i25 + i;
                a10.x = i26;
                java.awt.Point a12 = this.position;
                a12.y = i0;
                java.awt.Point a13 = RadixTrieMultiInternalNode.lowerRight;
                int i27 = a13.x;
                int i28 = i27 + 3;
                int i29 = i28 + 8;
                int i30 = a9.y;
                int i31 = i30 - 40;
                java.awt.Point a14 = new java.awt.Point(i29, i31);
                return a14;
            }
            else
            {
                java.util.Vector a15 = this.radixTrieNodes;
                Object a16 = a15.elementAt(i21);
                RadixTrieMultiNode dummy = (RadixTrieMultiNode)a16;
                RadixTrieMultiNode a17 = (RadixTrieMultiNode)a16;
                int i32 = a9.x;
                int i33 = a9.y;
                int i34 = i33 + 40;
                java.awt.Point a18 = a17.layout(i32, i34);
                int i35 = i21 + 1;
                a9 = a18;
                i21 = i35;
            }
        }
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
        java.awt.Point a2 = RadixTrieMultiInternalNode.upperLeft;
        int i = a2.x;
        java.awt.Point a3 = RadixTrieMultiInternalNode.upperLeft;
        int i0 = a3.y;
        java.awt.Point a4 = RadixTrieMultiInternalNode.lowerRight;
        int i1 = a4.x;
        java.awt.Point a5 = RadixTrieMultiInternalNode.upperLeft;
        int i2 = a5.x;
        int i3 = i1 - i2;
        java.awt.Point a6 = RadixTrieMultiInternalNode.lowerRight;
        int i4 = a6.y;
        java.awt.Point a7 = RadixTrieMultiInternalNode.upperLeft;
        int i5 = a7.y;
        int i6 = i4 - i5;
        java.awt.Rectangle a8 = new java.awt.Rectangle(i, i0, i3, i6);
        return a8;
    }
    
    public void draw(java.awt.Graphics a)
    {
        java.awt.Color a0 = this.DEFAULT_COLOR;
        a.setColor(a0);
        java.awt.Point a1 = this.position;
        java.awt.Point a2 = new java.awt.Point(a1);
        int i = a2.x;
        int i0 = i - 8;
        a2.x = i0;
        int i1 = RadixTrieMulti.NO_OF_NODES;
        int i2 = i1 - 1;
        int i3 = 16 / i2;
        int i4 = 0;
        while(true)
        {
            int i5 = RadixTrieMulti.NO_OF_NODES;
            if(i4 >= i5)
            {
                break;
            }
            java.util.Vector a3 = this.radixTrieNodes;
            Object a4 = a3.elementAt(i4);
            RadixTrieMultiNode dummy = (RadixTrieMultiNode)a4;
            RadixTrieMultiNode a5 = (RadixTrieMultiNode)a4;
            label1: {
                label0: {
                    if(a5 != null)
                    {
                        break label0;
                    }
                    java.awt.Point a6 = this.position;
                    int i6 = a6.x;
                    java.awt.Point a7 = this.position;
                    int i7 = a7.y;
                    int i8 = a2.x;
                    int i9 = i3 * i4;
                    int i10 = i8 + i9;
                    int i11 = a2.y;
                    int i12 = i11 + 13;
                    a.drawLine(i6, i7, i10, i12);
                    break label1;
                }
                int i13 = a5.getIsLinkedIn()?1:0;
                label2: {
                    com.cim.AIA.Line a8 = null;
                    if(i13 == 0)
                    {
                        break label2;
                    }
                    int i14 = a5.getIsOnPath()?1:0;
                    if(i14 == 0)
                    {
                        java.awt.Point a9 = this.position;
                        int i15 = a9.x;
                        java.awt.Point a10 = this.position;
                        int i16 = a10.y;
                        java.awt.Point a11 = a5.position;
                        int i17 = a11.x;
                        java.awt.Point a12 = a5.position;
                        int i18 = a12.y;
                        java.awt.Color a13 = java.awt.Color.black;
                        com.cim.AIA.Line a14 = new com.cim.AIA.Line(i15, i16, i17, i18, a13);
                        a8 = a14;
                    }
                    else
                    {
                        java.awt.Point a15 = this.position;
                        int i19 = a15.x;
                        java.awt.Point a16 = this.position;
                        int i20 = a16.y;
                        java.awt.Point a17 = a5.position;
                        int i21 = a17.x;
                        java.awt.Point a18 = a5.position;
                        int i22 = a18.y;
                        java.awt.Color a19 = java.awt.Color.red;
                        com.cim.AIA.Line a20 = new com.cim.AIA.Line(i19, i20, i21, i22, a19);
                        a20.showAsThick(true);
                        a8 = a20;
                    }
                    a8.draw(a);
                }
                a5.draw(a);
            }
            int i23 = i4 + 1;
            i4 = i23;
        }
        RadixTrieMultiInternalNode a21 = this.parent;
        if(a21 == null)
        {
            this.drawNode(a);
        }
        int i24 = 0;
        while(true)
        {
            int i25 = RadixTrieMulti.NO_OF_NODES;
            if(i24 >= i25)
            {
                return;
            }
            java.util.Vector a22 = this.radixTrieNodes;
            Object a23 = a22.elementAt(i24);
            RadixTrieMultiNode dummy0 = (RadixTrieMultiNode)a23;
            RadixTrieMultiNode a24 = (RadixTrieMultiNode)a23;
            if(a24 != null)
            {
                a24.drawNode(a);
            }
            int i26 = i24 + 1;
            i24 = i26;
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
    
    public void setChild(RadixTrieMultiNode a, int i)
    {
        a.parent = this;
        java.util.Vector a0 = this.radixTrieNodes;
        a0.removeElementAt(i);
        java.util.Vector a1 = this.radixTrieNodes;
        a1.insertElementAt((Object)a, i);
        com.cim.AIA.HierarchyTree a2 = this.hierarchyTree;
        com.cim.AIA.HierarchyTree a3 = this.hierarchyTree;
        com.cim.AIA.Tree a4 = a3.getChild(i);
        a2.removeChild(a4);
        com.cim.AIA.HierarchyTree a5 = this.hierarchyTree;
        com.cim.AIA.HierarchyTree a6 = a.getHierarchyTree();
        a5.insertChildAt((com.cim.AIA.Tree)a6, i);
    }
    
    public void setBackgroundColor(java.awt.Color a)
    {
        this.backgroundColor = a;
    }
    
    public RadixTrieMultiNode getChild(int i)
    {
        java.util.Vector a = this.radixTrieNodes;
        Object a0 = a.elementAt(i);
        RadixTrieMultiNode dummy = (RadixTrieMultiNode)a0;
        RadixTrieMultiNode a1 = (RadixTrieMultiNode)a0;
        return a1;
    }
    
    public com.cim.AIA.HierarchyTree getHierarchyTree()
    {
        com.cim.AIA.HierarchyTree a = this.hierarchyTree;
        return a;
    }
}