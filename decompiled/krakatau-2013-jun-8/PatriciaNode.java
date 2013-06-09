public class PatriciaNode implements com.cim.AIA.Drawable {
    final static int xGap = 8;
    final static int yGap = 10;
    final static int radius = 3;
    final static int newNodeRadius = 5;
    static PatriciaNode newestNode;
    final private static java.awt.Color RECTAGLE_COLOR;
    final private static java.awt.Color DEFAULT_COLOR;
    final private static java.awt.Color NEW_NODE_FILL_COLOR;
    final private static java.awt.Color NEW_NODE_BORDER_COLOR;
    final private static java.awt.Color TREE_BIT_COLOR;
    final private static java.awt.Color TREE_BIT_HIGHLIGHT_COLOR;
    private PatriciaNode left;
    private PatriciaNode right;
    private com.cim.AIA.HierarchyTree hierarchyTree;
    private byte bit;
    private PatriciaTreeDataItem dataItem;
    private com.cim.AIA.Node body;
    boolean isSubHead;
    boolean leftIsKnown;
    boolean rightIsKnown;
    java.awt.Point position;
    int depth;
    private PatriciaNode parent;
    
    public static void clearNewNode()
    {
        PatriciaNode.newestNode = null;
    }
    
    public static PatriciaNode getNewNode()
    {
        PatriciaNode a = PatriciaNode.newestNode;
        return a;
    }
    
    PatriciaNode()
    {
        super();
        this.isSubHead = false;
        this.leftIsKnown = true;
        this.rightIsKnown = true;
        this.bit = (byte)-1;
        PatriciaTreeDataItem a = new PatriciaTreeDataItem();
        this.dataItem = a;
        PatriciaTreeDataItem a0 = this.dataItem;
        int i = a0.getKey();
        com.cim.AIA.Node a1 = new com.cim.AIA.Node((Object)"-1", i);
        this.body = a1;
        com.cim.AIA.Node a2 = this.body;
        java.awt.Color a3 = PatriciaNode.TREE_BIT_COLOR;
        a2.setBackgroundColor(a3);
        com.cim.AIA.Node a4 = this.body;
        a4.setMarkersBelowValue(false);
        this.right = this;
        this.left = this;
        PatriciaNode.clearNewNode();
    }
    
    PatriciaNode(PatriciaTreeDataItem a, byte a0)
    {
        super();
        int i = a0;
        this.isSubHead = false;
        this.leftIsKnown = true;
        this.rightIsKnown = true;
        this.bit = (byte)i;
        this.dataItem = a;
        Integer a1 = new Integer(i);
        int i0 = a.getKey();
        com.cim.AIA.Node a2 = new com.cim.AIA.Node((Object)a1, i0);
        this.body = a2;
        com.cim.AIA.Node a3 = this.body;
        java.awt.Color a4 = PatriciaNode.TREE_BIT_COLOR;
        a3.setBackgroundColor(a4);
        PatriciaNode.clearNewNode();
        PatriciaNode.newestNode = this;
    }
    
    private void calculateDepths()
    {
        PatriciaNode a = this;
        while(true)
        {
            PatriciaNode a0 = a.parent;
            if(a0 == null)
            {
                int i = a.getDepth();
                return;
            }
            else
            {
                PatriciaNode a1 = a.parent;
                a = a1;
            }
        }
    }
    
    public void draw(java.awt.Graphics a)
    {
        int i = this.isTreeEmpty()?1:0;
        if(i != 0)
        {
            String s = localization.Messages.getString("PatriciaNode.1");
            java.awt.Point a0 = this.position;
            int i0 = a0.x;
            java.awt.Point a1 = this.position;
            int i1 = a1.y;
            a.drawString(s, i0, i1);
        }
        int i2 = this.bit;
        label1: {
            label0: {
                if(i2 >= 0)
                {
                    break label0;
                }
                PatriciaNode a2 = this.left;
                a2.isSubHead = true;
                PatriciaNode a3 = this.left;
                int i3 = a3.bit;
                int i4 = this.bit;
                if(i3 <= i4)
                {
                    break label1;
                }
                else
                {
                    PatriciaNode a4 = this.left;
                    a4.draw(a);
                    break label1;
                }
            }
            PatriciaNode a5 = PatriciaNode.newestNode;
            label2: {
                if(a5 == null)
                {
                    break label2;
                }
                PatriciaNode a6 = PatriciaNode.newestNode;
                PatriciaNode a7 = a6.parent;
                if(a7 != null)
                {
                    break label2;
                }
                PatriciaNode a8 = this.parent;
                PatriciaNode a9 = PatriciaNode.newestNode;
                if(a8 != a9)
                {
                    break label2;
                }
                PatriciaNode a10 = this.parent;
                PatriciaNode a11 = a10.left;
                int i5 = a11 != this?-8:8;
                java.awt.Point a12 = this.position;
                int i6 = a12.x;
                int i7 = i6 + i5;
                java.awt.Point a13 = this.position;
                int i8 = a13.y;
                int i9 = i8 - 10;
                java.awt.Point a14 = this.position;
                int i10 = a14.x;
                java.awt.Point a15 = this.position;
                int i11 = a15.y;
                a.drawLine(i7, i9, i10, i11);
                java.awt.Color a16 = PatriciaNode.NEW_NODE_FILL_COLOR;
                a.setColor(a16);
                java.awt.Point a17 = this.position;
                int i12 = a17.x;
                int i13 = i12 - 5;
                int i14 = i13 + i5;
                java.awt.Point a18 = this.position;
                int i15 = a18.y;
                int i16 = i15 - 5;
                int i17 = i16 - 10;
                a.fillOval(i14, i17, 10, 10);
                java.awt.Color a19 = PatriciaNode.NEW_NODE_BORDER_COLOR;
                a.setColor(a19);
                java.awt.Point a20 = this.position;
                int i18 = a20.x;
                int i19 = i18 - 5;
                int i20 = i19 + i5;
                java.awt.Point a21 = this.position;
                int i21 = a21.y;
                int i22 = i21 - 5;
                int i23 = i22 - 10;
                a.drawOval(i20, i23, 10, 10);
            }
            java.awt.Color a22 = PatriciaNode.DEFAULT_COLOR;
            a.setColor(a22);
            PatriciaNode a23 = this.left;
            int i24 = a23.bit;
            int i25 = this.bit;
            label4: {
                label3: {
                    if(i24 <= i25)
                    {
                        break label3;
                    }
                    int i26 = this.leftIsKnown?1:0;
                    if(i26 == 0)
                    {
                        java.awt.Color a24 = PatriciaNode.RECTAGLE_COLOR;
                        a.setColor(a24);
                        PatriciaNode a25 = this.left;
                        int i27 = a25.leftEdge();
                        PatriciaNode a26 = this.left;
                        java.awt.Point a27 = a26.position;
                        int i28 = a27.y;
                        PatriciaNode a28 = this.left;
                        int i29 = a28.getWidth();
                        int i30 = this.depth;
                        int i31 = 10 * i30;
                        a.drawRect(i27, i28, i29, i31);
                        java.awt.Point a29 = this.position;
                        int i32 = a29.x;
                        java.awt.Point a30 = this.position;
                        int i33 = a30.y;
                        PatriciaNode a31 = this.left;
                        java.awt.Point a32 = a31.position;
                        int i34 = a32.x;
                        PatriciaNode a33 = this.left;
                        java.awt.Point a34 = a33.position;
                        int i35 = a34.y;
                        a.drawLine(i32, i33, i34, i35);
                        PatriciaNode a35 = this.left;
                        a35.isSubHead = true;
                        PatriciaNode a36 = this.left;
                        PatriciaNode a37 = this.getSubHead();
                        int i36 = a37.rightEdge();
                        int i37 = i36 + 16;
                        java.awt.Point a38 = this.position;
                        int i38 = a38.y;
                        int i39 = i38 + 10;
                        java.awt.Point a39 = new java.awt.Point(i37, i39);
                        a36.draw(a, a39);
                        break label4;
                    }
                    else
                    {
                        java.awt.Point a40 = this.position;
                        int i40 = a40.x;
                        java.awt.Point a41 = this.position;
                        int i41 = a41.y;
                        PatriciaNode a42 = this.left;
                        java.awt.Point a43 = a42.position;
                        int i42 = a43.x;
                        PatriciaNode a44 = this.left;
                        java.awt.Point a45 = a44.position;
                        int i43 = a45.y;
                        a.drawLine(i40, i41, i42, i43);
                        PatriciaNode a46 = this.left;
                        a46.draw(a);
                        break label4;
                    }
                }
                int i44 = this.leftIsKnown?1:0;
                if(i44 != 0)
                {
                    break label4;
                }
                java.awt.Color a47 = PatriciaNode.RECTAGLE_COLOR;
                a.setColor(a47);
                java.awt.Point a48 = this.position;
                int i45 = a48.x;
                int i46 = i45 - 3;
                int i47 = i46 - 4;
                java.awt.Point a49 = this.position;
                int i48 = a49.y;
                int i49 = i48 + 10;
                int i50 = this.depth;
                int i51 = 10 * i50;
                a.drawRect(i47, i49, 6, i51);
                java.awt.Point a50 = this.position;
                int i52 = a50.x;
                java.awt.Point a51 = this.position;
                int i53 = a51.y;
                java.awt.Point a52 = this.position;
                int i54 = a52.x;
                int i55 = i54 - 4;
                java.awt.Point a53 = this.position;
                int i56 = a53.y;
                int i57 = i56 + 10;
                a.drawLine(i52, i53, i55, i57);
                PatriciaNode a54 = PatriciaNode.newestNode;
                label5: {
                    if(a54 == null)
                    {
                        break label5;
                    }
                    PatriciaNode a55 = PatriciaNode.newestNode;
                    PatriciaNode a56 = a55.parent;
                    if(a56 == null)
                    {
                        java.awt.Color a57 = PatriciaNode.NEW_NODE_FILL_COLOR;
                        a.setColor(a57);
                        PatriciaNode a58 = this.getSubHead();
                        int i58 = a58.rightEdge();
                        int i59 = i58 - 5;
                        int i60 = i59 + 16;
                        java.awt.Point a59 = this.position;
                        int i61 = a59.y;
                        int i62 = i61 - 5;
                        int i63 = i62 + 10;
                        a.fillOval(i60, i63, 6, 10);
                        java.awt.Color a60 = PatriciaNode.NEW_NODE_BORDER_COLOR;
                        a.setColor(a60);
                        PatriciaNode a61 = this.getSubHead();
                        int i64 = a61.rightEdge();
                        int i65 = i64 - 5;
                        int i66 = i65 + 16;
                        java.awt.Point a62 = this.position;
                        int i67 = a62.y;
                        int i68 = i67 - 5;
                        int i69 = i68 + 10;
                        a.drawOval(i66, i69, 10, 10);
                    }
                }
                java.awt.Color a63 = PatriciaNode.DEFAULT_COLOR;
                a.setColor(a63);
            }
            PatriciaNode a64 = this.right;
            int i70 = a64.bit;
            int i71 = this.bit;
            label7: {
                label6: {
                    if(i70 <= i71)
                    {
                        break label6;
                    }
                    int i72 = this.rightIsKnown?1:0;
                    if(i72 == 0)
                    {
                        java.awt.Color a65 = PatriciaNode.RECTAGLE_COLOR;
                        a.setColor(a65);
                        PatriciaNode a66 = this.right;
                        int i73 = a66.leftEdge();
                        PatriciaNode a67 = this.right;
                        java.awt.Point a68 = a67.position;
                        int i74 = a68.y;
                        PatriciaNode a69 = this.right;
                        int i75 = a69.getWidth();
                        int i76 = this.depth;
                        int i77 = 10 * i76;
                        a.drawRect(i73, i74, i75, i77);
                        java.awt.Point a70 = this.position;
                        int i78 = a70.x;
                        java.awt.Point a71 = this.position;
                        int i79 = a71.y;
                        PatriciaNode a72 = this.right;
                        java.awt.Point a73 = a72.position;
                        int i80 = a73.x;
                        PatriciaNode a74 = this.right;
                        java.awt.Point a75 = a74.position;
                        int i81 = a75.y;
                        a.drawLine(i78, i79, i80, i81);
                        PatriciaNode a76 = this.right;
                        a76.isSubHead = true;
                        PatriciaNode a77 = this.right;
                        PatriciaNode a78 = this.getSubHead();
                        int i82 = a78.rightEdge();
                        int i83 = i82 + 16;
                        java.awt.Point a79 = this.position;
                        int i84 = a79.y;
                        int i85 = i84 + 10;
                        java.awt.Point a80 = new java.awt.Point(i83, i85);
                        a77.draw(a, a80);
                        break label7;
                    }
                    else
                    {
                        java.awt.Point a81 = this.position;
                        int i86 = a81.x;
                        java.awt.Point a82 = this.position;
                        int i87 = a82.y;
                        PatriciaNode a83 = this.right;
                        java.awt.Point a84 = a83.position;
                        int i88 = a84.x;
                        PatriciaNode a85 = this.right;
                        java.awt.Point a86 = a85.position;
                        int i89 = a86.y;
                        a.drawLine(i86, i87, i88, i89);
                        PatriciaNode a87 = this.right;
                        a87.draw(a);
                        break label7;
                    }
                }
                int i90 = this.rightIsKnown?1:0;
                if(i90 != 0)
                {
                    break label7;
                }
                java.awt.Color a88 = PatriciaNode.RECTAGLE_COLOR;
                a.setColor(a88);
                java.awt.Point a89 = this.position;
                int i91 = a89.x;
                int i92 = i91 + 4;
                java.awt.Point a90 = this.position;
                int i93 = a90.y;
                int i94 = i93 + 10;
                int i95 = this.depth;
                int i96 = 10 * i95;
                a.drawRect(i92, i94, 6, i96);
                java.awt.Point a91 = this.position;
                int i97 = a91.x;
                java.awt.Point a92 = this.position;
                int i98 = a92.y;
                java.awt.Point a93 = this.position;
                int i99 = a93.x;
                int i100 = i99 + 4;
                java.awt.Point a94 = this.position;
                int i101 = a94.y;
                int i102 = i101 + 10;
                a.drawLine(i97, i98, i100, i102);
                PatriciaNode a95 = PatriciaNode.newestNode;
                if(a95 == null)
                {
                    break label7;
                }
                PatriciaNode a96 = PatriciaNode.newestNode;
                PatriciaNode a97 = a96.parent;
                if(a97 == null)
                {
                    java.awt.Color a98 = PatriciaNode.NEW_NODE_FILL_COLOR;
                    a.setColor(a98);
                    PatriciaNode a99 = this.getSubHead();
                    int i103 = a99.rightEdge();
                    int i104 = i103 - 5;
                    int i105 = i104 + 16;
                    java.awt.Point a100 = this.position;
                    int i106 = a100.y;
                    int i107 = i106 + 10;
                    int i108 = i107 - 5;
                    a.fillOval(i105, i108, 10, 10);
                    java.awt.Color a101 = PatriciaNode.NEW_NODE_BORDER_COLOR;
                    a.setColor(a101);
                    PatriciaNode a102 = this.getSubHead();
                    int i109 = a102.rightEdge();
                    int i110 = i109 - 5;
                    int i111 = i110 + 16;
                    java.awt.Point a103 = this.position;
                    int i112 = a103.y;
                    int i113 = i112 + 10;
                    int i114 = i113 - 5;
                    a.drawOval(i111, i114, 10, 10);
                }
            }
            PatriciaNode a104 = PatriciaNode.newestNode;
            if(a104 == this)
            {
                java.awt.Color a105 = PatriciaNode.NEW_NODE_FILL_COLOR;
                a.setColor(a105);
                java.awt.Point a106 = this.position;
                int i115 = a106.x;
                int i116 = i115 - 5;
                java.awt.Point a107 = this.position;
                int i117 = a107.y;
                int i118 = i117 - 5;
                a.fillOval(i116, i118, 10, 10);
                java.awt.Color a108 = PatriciaNode.NEW_NODE_BORDER_COLOR;
                a.setColor(a108);
                java.awt.Point a109 = this.position;
                int i119 = a109.x;
                int i120 = i119 - 5;
                java.awt.Point a110 = this.position;
                int i121 = a110.y;
                int i122 = i121 - 5;
                a.drawOval(i120, i122, 10, 10);
            }
            else
            {
                java.awt.Color a111 = java.awt.Color.white;
                a.setColor(a111);
                java.awt.Point a112 = this.position;
                int i123 = a112.x;
                int i124 = i123 - 3;
                java.awt.Point a113 = this.position;
                int i125 = a113.y;
                int i126 = i125 - 3;
                a.fillOval(i124, i126, 6, 6);
                java.awt.Color a114 = PatriciaNode.DEFAULT_COLOR;
                a.setColor(a114);
                java.awt.Point a115 = this.position;
                int i127 = a115.x;
                int i128 = i127 - 3;
                java.awt.Point a116 = this.position;
                int i129 = a116.y;
                int i130 = i129 - 3;
                a.drawOval(i128, i130, 6, 6);
            }
        }
        java.awt.Color a117 = PatriciaNode.DEFAULT_COLOR;
        a.setColor(a117);
    }
    
    public void draw(java.awt.Graphics a, java.awt.Point a0)
    {
        this.setPosition(a0);
        this.draw(a);
    }
    
    public byte getBit()
    {
        int i = this.bit;
        return (byte)i;
    }
    
    public com.cim.AIA.Node getBody()
    {
        com.cim.AIA.Node a = this.body;
        return a;
    }
    
    public PatriciaTreeDataItem getDataItem()
    {
        PatriciaTreeDataItem a = this.dataItem;
        return a;
    }
    
    private int getDepth()
    {
        int i = 0;
        int i0 = 0;
        PatriciaNode a = this.left;
        label0: {
            if(a == null)
            {
                i = 0;
                break label0;
            }
            PatriciaNode a0 = this.left;
            int i1 = a0.bit;
            int i2 = this.bit;
            if(i1 <= i2)
            {
                i = 0;
            }
            else
            {
                PatriciaNode a1 = this.left;
                int i3 = a1.getDepth();
                i = i3;
            }
        }
        PatriciaNode a2 = this.right;
        label1: {
            if(a2 == null)
            {
                i0 = 0;
                break label1;
            }
            PatriciaNode a3 = this.right;
            int i4 = a3.bit;
            int i5 = this.bit;
            if(i4 <= i5)
            {
                i0 = 0;
            }
            else
            {
                PatriciaNode a4 = this.right;
                int i6 = a4.getDepth();
                i0 = i6;
            }
        }
        int i7 = Math.max(i, i0);
        int i8 = i7 + 1;
        this.depth = i8;
        int i9 = this.depth;
        return i9;
    }
    
    public int getHeight()
    {
        PatriciaNode a = this;
        while(true)
        {
            PatriciaNode a0 = a.parent;
            if(a0 == null)
            {
                int i = a.getDepth();
                int i0 = i * 10;
                return i0;
            }
            else
            {
                PatriciaNode a1 = a.parent;
                a = a1;
            }
        }
    }
    
    public com.cim.AIA.HierarchyTree getHierarchyTree()
    {
        com.cim.AIA.HierarchyTree a = this.hierarchyTree;
        return a;
    }
    
    public PatriciaNode getLeft()
    {
        PatriciaNode a = this.left;
        return a;
    }
    
    public java.awt.Point getPosition()
    {
        java.awt.Point a = this.position;
        return a;
    }
    
    public PatriciaNode getRight()
    {
        PatriciaNode a = this.right;
        return a;
    }
    
    PatriciaNode getSubHead()
    {
        PatriciaNode a = this;
        while(true)
        {
            PatriciaNode a0 = a.parent;
            label0: {
                if(a0 == null)
                {
                    break label0;
                }
                int i = a.isSubHead?1:0;
                if(i == 0)
                {
                    PatriciaNode a1 = a.parent;
                    a = a1;
                    continue;
                }
            }
            return a;
        }
    }
    
    private int getWidth()
    {
        int i = this.rightEdge();
        int i0 = this.leftEdge();
        int i1 = i - i0;
        return i1;
    }
    
    public boolean isTreeEmpty()
    {
        int i = 0;
        PatriciaNode a = this.right;
        label2: {
            label1: {
                label0: {
                    if(a != this)
                    {
                        break label0;
                    }
                    PatriciaNode a0 = this.left;
                    if(a0 == this)
                    {
                        break label1;
                    }
                }
                i = 0;
                break label2;
            }
            i = 1;
        }
        return i != 0;
    }
    
    private java.awt.Point layout(int i, int i0)
    {
        java.awt.Point a = null;
        java.awt.Point a0 = null;
        java.awt.Point a1 = new java.awt.Point(i, i0);
        PatriciaNode a2 = this.left;
        int i1 = a2.bit;
        int i2 = this.bit;
        if(i1 <= i2)
        {
            a = a1;
        }
        else
        {
            PatriciaNode a3 = this.left;
            int i3 = i0 + 10;
            java.awt.Point a4 = a3.layout(i, i3);
            a = a4;
        }
        int i4 = a.x;
        int i5 = i4 + 8;
        a.x = i5;
        this.position = a;
        PatriciaNode a5 = this.right;
        int i6 = a5.bit;
        int i7 = this.bit;
        if(i6 <= i7)
        {
            a0 = a;
        }
        else
        {
            PatriciaNode a6 = this.right;
            int i8 = a.x;
            int i9 = a.y;
            int i10 = i9 + 10;
            java.awt.Point a7 = a6.layout(i8, i10);
            a0 = a7;
        }
        int i11 = a0.x;
        int i12 = a0.y;
        int i13 = i12 - 10;
        java.awt.Point a8 = new java.awt.Point(i11, i13);
        return a8;
    }
    
    private int leftEdge()
    {
        int i = 0;
        PatriciaNode a = this.left;
        int i0 = a.bit;
        int i1 = this.bit;
        if(i0 <= i1)
        {
            java.awt.Point a0 = this.position;
            int i2 = a0.x;
            int i3 = i2 - 3;
            i = i3;
        }
        else
        {
            PatriciaNode a1 = this.left;
            int i4 = a1.leftEdge();
            i = i4;
        }
        return i;
    }
    
    private int rightEdge()
    {
        int i = 0;
        PatriciaNode a = this.right;
        int i0 = a.bit;
        int i1 = this.bit;
        label1: {
            label0: {
                if(i0 <= i1)
                {
                    break label0;
                }
                PatriciaNode a0 = this.right;
                int i2 = a0.rightEdge();
                i = i2;
                break label1;
            }
            int i3 = this.rightIsKnown?1:0;
            if(i3 != 0)
            {
                java.awt.Point a1 = this.position;
                int i4 = a1.x;
                int i5 = i4 + 3;
                i = i5;
            }
            else
            {
                java.awt.Point a2 = this.position;
                int i6 = a2.x;
                int i7 = i6 + 6;
                int i8 = i7 + 4;
                i = i8;
            }
        }
        return i;
    }
    
    public void setHierarchyTree(com.cim.AIA.HierarchyTree a)
    {
        this.hierarchyTree = a;
    }
    
    public void setLeft(PatriciaNode a)
    {
        this.left = a;
        PatriciaNode a0 = this.left;
        int i = a0.bit;
        int i0 = this.bit;
        if(i > i0)
        {
            PatriciaNode a1 = this.left;
            a1.parent = this;
        }
        this.calculateDepths();
    }
    
    public void setLeftIsKnown(boolean b)
    {
        this.leftIsKnown = b;
    }
    
    public void setPosition(java.awt.Point a)
    {
        int i = a.x;
        int i0 = i - 8;
        int i1 = a.y;
        java.awt.Point a0 = this.layout(i0, i1);
    }
    
    public void setRight(PatriciaNode a)
    {
        this.right = a;
        PatriciaNode a0 = this.right;
        int i = a0.bit;
        int i0 = this.bit;
        if(i > i0)
        {
            PatriciaNode a1 = this.right;
            a1.parent = this;
        }
        this.calculateDepths();
    }
    
    public void setRightIsKnown(boolean b)
    {
        this.rightIsKnown = b;
    }
    
    static
    {
        java.awt.Color a = java.awt.Color.gray;
        PatriciaNode.RECTAGLE_COLOR = a;
        java.awt.Color a0 = java.awt.Color.black;
        PatriciaNode.DEFAULT_COLOR = a0;
        java.awt.Color a1 = java.awt.Color.green;
        PatriciaNode.NEW_NODE_FILL_COLOR = a1;
        java.awt.Color a2 = java.awt.Color.red;
        PatriciaNode.NEW_NODE_BORDER_COLOR = a2;
        java.awt.Color a3 = java.awt.Color.yellow;
        PatriciaNode.TREE_BIT_COLOR = a3;
        java.awt.Color a4 = new java.awt.Color(255, 255, 192);
        PatriciaNode.TREE_BIT_HIGHLIGHT_COLOR = a4;
    }
}