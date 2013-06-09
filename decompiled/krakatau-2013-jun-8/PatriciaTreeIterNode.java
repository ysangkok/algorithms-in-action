public class PatriciaTreeIterNode implements com.cim.AIA.Drawable {
    private PatriciaTreeIterNode left;
    private PatriciaTreeIterNode right;
    private com.cim.AIA.HierarchyTree hierarchyTree;
    private byte bit;
    private PatriciaTreeIterDataItem dataItem;
    private com.cim.AIA.Node body;
    boolean isSubHead;
    boolean leftIsKnown;
    boolean rightIsKnown;
    private boolean virtual;
    java.awt.Point position;
    int depth;
    final static int xGap = 8;
    final static int yGap = 10;
    final static int radius = 3;
    final static int newNodeRadius = 5;
    private PatriciaTreeIterNode parent;
    static PatriciaTreeIterNode newestNode;
    final private static java.awt.Color TREE_BIT_COLOR;
    final private static java.awt.Color TREE_BIT_HIGHLIGHT_COLOR;
    
    PatriciaTreeIterNode()
    {
        super();
        this.isSubHead = false;
        this.leftIsKnown = true;
        this.rightIsKnown = true;
        this.virtual = false;
        this.bit = (byte)-1;
        PatriciaTreeIterDataItem a = new PatriciaTreeIterDataItem();
        this.dataItem = a;
        PatriciaTreeIterDataItem a0 = this.dataItem;
        int i = a0.getKey();
        com.cim.AIA.Node a1 = new com.cim.AIA.Node((Object)"-1", i);
        this.body = a1;
        com.cim.AIA.Node a2 = this.body;
        a2.setHeight(14);
        com.cim.AIA.Node a3 = this.body;
        a3.setWidth(15);
        com.cim.AIA.Node a4 = this.body;
        java.awt.Color a5 = PatriciaTreeIterNode.TREE_BIT_COLOR;
        a4.setBackgroundColor(a5);
        com.cim.AIA.Node a6 = this.body;
        a6.setMarkersBelowValue(false);
        this.right = this;
        this.left = this;
        this.clearNewNode();
    }
    
    PatriciaTreeIterNode(PatriciaTreeIterDataItem a, byte a0)
    {
        super();
        int i = a0;
        this.isSubHead = false;
        this.leftIsKnown = true;
        this.rightIsKnown = true;
        this.virtual = false;
        this.bit = (byte)i;
        this.dataItem = a;
        Integer a1 = new Integer(i);
        int i0 = a.getKey();
        com.cim.AIA.Node a2 = new com.cim.AIA.Node((Object)a1, i0);
        this.body = a2;
        com.cim.AIA.Node a3 = this.body;
        a3.setHeight(14);
        com.cim.AIA.Node a4 = this.body;
        a4.setWidth(15);
        com.cim.AIA.Node a5 = this.body;
        java.awt.Color a6 = PatriciaTreeIterNode.TREE_BIT_COLOR;
        a5.setBackgroundColor(a6);
        this.clearNewNode();
        PatriciaTreeIterNode.newestNode = this;
    }
    
    public PatriciaTreeIterNode copy()
    {
        PatriciaTreeIterNode a = PatriciaTreeIterNode.copy(this, this);
        return a;
    }
    
    public static PatriciaTreeIterNode copy(PatriciaTreeIterNode a, PatriciaTreeIterNode a0)
    {
        PatriciaTreeIterNode a1 = null;
        PatriciaTreeIterDataItem a2 = a.getDataItem();
        int i = a.getBit();
        PatriciaTreeIterNode a3 = new PatriciaTreeIterNode(a2, (byte)i);
        PatriciaTreeIterNode a4 = a.getLeft();
        PatriciaTreeIterNode a5 = a.getRight();
        int i0 = a4.getBit();
        int i1 = a.getBit();
        if(i0 <= i1)
        {
            PatriciaTreeIterDataItem a6 = a4.getDataItem();
            int i2 = a4.getBit();
            PatriciaTreeIterNode a7 = new PatriciaTreeIterNode(a6, (byte)i2);
            a7.setVirtual(true);
            a3.setLeft(a7);
        }
        else
        {
            PatriciaTreeIterNode a8 = PatriciaTreeIterNode.copy(a4, a0);
            a3.setLeft(a8);
        }
        int i3 = a5.getBit();
        int i4 = a.getBit();
        if(i3 <= i4)
        {
            PatriciaTreeIterDataItem a9 = a5.getDataItem();
            int i5 = a5.getBit();
            PatriciaTreeIterNode a10 = new PatriciaTreeIterNode(a9, (byte)i5);
            a10.setVirtual(true);
            a3.setRight(a10);
        }
        else
        {
            PatriciaTreeIterNode a11 = PatriciaTreeIterNode.copy(a5, a0);
            a3.setRight(a11);
        }
        if(a != a0)
        {
            a1 = a3;
        }
        else
        {
            PatriciaTreeIterNode a12 = PatriciaTreeIterNode.virtualToReal(a3, a3);
            a1 = a12;
        }
        a1.clearNewNode();
        return a1;
    }
    
    private static PatriciaTreeIterNode virtualToReal(PatriciaTreeIterNode a, PatriciaTreeIterNode a0)
    {
        PatriciaTreeIterNode a1 = a.getLeft();
        int i = a1.getVirtual()?1:0;
        if(i == 0)
        {
            PatriciaTreeIterNode a2 = a.getLeft();
            PatriciaTreeIterNode a3 = PatriciaTreeIterNode.virtualToReal(a2, a0);
            a.setLeft(a3);
        }
        else
        {
            PatriciaTreeIterNode a4 = a.getLeft();
            PatriciaTreeIterDataItem a5 = a4.getDataItem();
            PatriciaTreeIterNode a6 = PatriciaTreeIterNode.findNode(a0, a5);
            a.setLeft(a6);
        }
        PatriciaTreeIterNode a7 = a.getRight();
        int i0 = a7.getVirtual()?1:0;
        if(i0 == 0)
        {
            PatriciaTreeIterNode a8 = a.getRight();
            PatriciaTreeIterNode a9 = PatriciaTreeIterNode.virtualToReal(a8, a0);
            a.setRight(a9);
        }
        else
        {
            PatriciaTreeIterNode a10 = a.getRight();
            PatriciaTreeIterDataItem a11 = a10.getDataItem();
            PatriciaTreeIterNode a12 = PatriciaTreeIterNode.findNode(a0, a11);
            a.setRight(a12);
        }
        return a;
    }
    
    private static PatriciaTreeIterNode findNode(PatriciaTreeIterNode a, PatriciaTreeIterDataItem a0)
    {
        PatriciaTreeIterNode a1 = null;
        PatriciaTreeIterDataItem a2 = a.getDataItem();
        label1: {
            label0: {
                if(a2 != a0)
                {
                    break label0;
                }
                a1 = a;
                break label1;
            }
            PatriciaTreeIterNode a3 = a.getLeft();
            int i = a3.getBit();
            int i0 = a.getBit();
            label2: {
                if(i <= i0)
                {
                    break label2;
                }
                PatriciaTreeIterNode a4 = a.getLeft();
                PatriciaTreeIterNode a5 = PatriciaTreeIterNode.findNode(a4, a0);
                if(a5 == null)
                {
                    break label2;
                }
                a1 = a5;
                break label1;
            }
            PatriciaTreeIterNode a6 = a.getRight();
            int i1 = a6.getBit();
            int i2 = a.getBit();
            if(i1 <= i2)
            {
                a1 = null;
            }
            else
            {
                PatriciaTreeIterNode a7 = a.getRight();
                PatriciaTreeIterNode a8 = PatriciaTreeIterNode.findNode(a7, a0);
                a1 = a8;
            }
        }
        return a1;
    }
    
    public boolean isTreeEmpty()
    {
        int i = 0;
        PatriciaTreeIterNode a = this.right;
        label2: {
            label1: {
                label0: {
                    if(a != this)
                    {
                        break label0;
                    }
                    PatriciaTreeIterNode a0 = this.left;
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
    
    public void clearNewNode()
    {
        PatriciaTreeIterNode a = PatriciaTreeIterNode.newestNode;
        if(a != null)
        {
            PatriciaTreeIterNode a0 = PatriciaTreeIterNode.newestNode;
            com.cim.AIA.Node a1 = a0.body;
            java.awt.Color a2 = PatriciaTreeIterNode.TREE_BIT_COLOR;
            a1.setBackgroundColor(a2);
        }
        PatriciaTreeIterNode.newestNode = null;
    }
    
    public static PatriciaTreeIterNode getNewNode()
    {
        PatriciaTreeIterNode a = PatriciaTreeIterNode.newestNode;
        return a;
    }
    
    public void setHierarchyTree(com.cim.AIA.HierarchyTree a)
    {
        this.hierarchyTree = a;
    }
    
    public com.cim.AIA.HierarchyTree getHierarchyTree()
    {
        com.cim.AIA.HierarchyTree a = this.hierarchyTree;
        return a;
    }
    
    public void setLeftIsKnown(boolean b)
    {
        this.leftIsKnown = b;
    }
    
    public void setRightIsKnown(boolean b)
    {
        this.rightIsKnown = b;
    }
    
    public void setLeft(PatriciaTreeIterNode a)
    {
        this.left = a;
        PatriciaTreeIterNode a0 = this.left;
        int i = a0.bit;
        int i0 = this.bit;
        if(i > i0)
        {
            PatriciaTreeIterNode a1 = this.left;
            a1.parent = this;
        }
        this.calculateDepths();
    }
    
    public void setRight(PatriciaTreeIterNode a)
    {
        this.right = a;
        PatriciaTreeIterNode a0 = this.right;
        int i = a0.bit;
        int i0 = this.bit;
        if(i > i0)
        {
            PatriciaTreeIterNode a1 = this.right;
            a1.parent = this;
        }
        this.calculateDepths();
    }
    
    public void setBit(byte a)
    {
        this.bit = a;
    }
    
    public byte getBit()
    {
        int i = this.bit;
        return (byte)i;
    }
    
    public boolean getVirtual()
    {
        int i = this.virtual?1:0;
        return i != 0;
    }
    
    public void setVirtual(boolean b)
    {
        this.virtual = b;
    }
    
    public PatriciaTreeIterNode getLeft()
    {
        PatriciaTreeIterNode a = this.left;
        return a;
    }
    
    public PatriciaTreeIterNode getRight()
    {
        PatriciaTreeIterNode a = this.right;
        return a;
    }
    
    public PatriciaTreeIterDataItem getDataItem()
    {
        PatriciaTreeIterDataItem a = this.dataItem;
        return a;
    }
    
    public com.cim.AIA.Node getBody()
    {
        com.cim.AIA.Node a = this.body;
        return a;
    }
    
    public void draw(java.awt.Graphics a, java.awt.Point a0)
    {
        this.setPosition(a0);
        this.draw(a);
    }
    
    public void draw(java.awt.Graphics a)
    {
    }
    
    public java.awt.Point getPosition()
    {
        java.awt.Point a = this.position;
        return a;
    }
    
    public void setPosition(java.awt.Point a)
    {
        int i = a.x;
        int i0 = i - 8;
        int i1 = a.y;
        java.awt.Point a0 = this.layout(i0, i1);
    }
    
    private java.awt.Point layout(int i, int i0)
    {
        java.awt.Point a = null;
        java.awt.Point a0 = null;
        java.awt.Point a1 = new java.awt.Point(i, i0);
        PatriciaTreeIterNode a2 = this.left;
        int i1 = a2.bit;
        int i2 = this.bit;
        if(i1 <= i2)
        {
            a = a1;
        }
        else
        {
            PatriciaTreeIterNode a3 = this.left;
            int i3 = i0 + 10;
            java.awt.Point a4 = a3.layout(i, i3);
            a = a4;
        }
        int i4 = a.x;
        int i5 = i4 + 8;
        a.x = i5;
        this.position = a;
        PatriciaTreeIterNode a5 = this.right;
        int i6 = a5.bit;
        int i7 = this.bit;
        if(i6 <= i7)
        {
            a0 = a;
        }
        else
        {
            PatriciaTreeIterNode a6 = this.right;
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
        PatriciaTreeIterNode a = this.left;
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
            PatriciaTreeIterNode a1 = this.left;
            int i4 = a1.leftEdge();
            i = i4;
        }
        return i;
    }
    
    private int rightEdge()
    {
        int i = 0;
        PatriciaTreeIterNode a = this.right;
        int i0 = a.bit;
        int i1 = this.bit;
        label1: {
            label0: {
                if(i0 <= i1)
                {
                    break label0;
                }
                PatriciaTreeIterNode a0 = this.right;
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
    
    PatriciaTreeIterNode getSubHead()
    {
        PatriciaTreeIterNode a = this;
        while(true)
        {
            PatriciaTreeIterNode a0 = a.parent;
            label0: {
                if(a0 == null)
                {
                    break label0;
                }
                int i = a.isSubHead?1:0;
                if(i == 0)
                {
                    PatriciaTreeIterNode a1 = a.parent;
                    a = a1;
                    continue;
                }
            }
            return a;
        }
    }
    
    public int getHeight()
    {
        PatriciaTreeIterNode a = this;
        while(true)
        {
            PatriciaTreeIterNode a0 = a.parent;
            if(a0 == null)
            {
                int i = a.getDepth();
                int i0 = i * 10;
                return i0;
            }
            else
            {
                PatriciaTreeIterNode a1 = a.parent;
                a = a1;
            }
        }
    }
    
    private int getWidth()
    {
        int i = this.rightEdge();
        int i0 = this.leftEdge();
        int i1 = i - i0;
        return i1;
    }
    
    private void calculateDepths()
    {
        PatriciaTreeIterNode a = this;
        while(true)
        {
            PatriciaTreeIterNode a0 = a.parent;
            if(a0 == null)
            {
                int i = a.getDepth();
                return;
            }
            else
            {
                PatriciaTreeIterNode a1 = a.parent;
                a = a1;
            }
        }
    }
    
    private int getDepth()
    {
        int i = 0;
        int i0 = 0;
        PatriciaTreeIterNode a = this.left;
        label0: {
            if(a == null)
            {
                i = 0;
                break label0;
            }
            PatriciaTreeIterNode a0 = this.left;
            int i1 = a0.bit;
            int i2 = this.bit;
            if(i1 <= i2)
            {
                i = 0;
            }
            else
            {
                PatriciaTreeIterNode a1 = this.left;
                int i3 = a1.getDepth();
                i = i3;
            }
        }
        PatriciaTreeIterNode a2 = this.right;
        label1: {
            if(a2 == null)
            {
                i0 = 0;
                break label1;
            }
            PatriciaTreeIterNode a3 = this.right;
            int i4 = a3.bit;
            int i5 = this.bit;
            if(i4 <= i5)
            {
                i0 = 0;
            }
            else
            {
                PatriciaTreeIterNode a4 = this.right;
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
    
    static
    {
        java.awt.Color a = new java.awt.Color(200, 255, 200);
        PatriciaTreeIterNode.TREE_BIT_COLOR = a;
        java.awt.Color a0 = new java.awt.Color(255, 255, 192);
        PatriciaTreeIterNode.TREE_BIT_HIGHLIGHT_COLOR = a0;
    }
}