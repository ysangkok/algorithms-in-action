public class Tree234Tree extends com.cim.AIA.Algorithm implements com.cim.AIA.MethodSelectionListener, com.cim.AIA.ControlListener {
    protected static int POS;
    final private java.awt.Color T234_NODE_COLOR;
    final private java.awt.Color T234_COMPARE_COLOR;
    final private java.awt.Color T234_SPLIT_COLOR;
    final private java.awt.Color RB_NODE_COLOR;
    final private java.awt.Color PATH_COLOR;
    public boolean isBackMode;
    final protected static int BUILD_MODE = 11;
    final protected static int SEARCH_MODE = 12;
    protected int[] data;
    protected boolean[] searchResults;
    protected int position;
    protected int executingMode;
    protected int nextMode;
    protected Tree234RedBlack tree;
    protected Tree234RedBlack baseTree;
    protected int basePosition;
    protected boolean canStoreState;
    protected java.util.Vector insertRequests;
    protected java.util.Vector deleteRequests;
    protected java.util.Vector deleter;
    protected java.util.Vector spliter;
    protected java.util.Vector comparer;
    protected com.cim.AIA.Node animationNode;
    protected com.cim.AIA.Node growNode;
    protected com.cim.AIA.HierarchyTree parentNode;
    protected com.cim.AIA.HierarchyTree grandParentNode;
    protected com.cim.AIA.HierarchyTree greatGrandParentNode;
    protected com.cim.AIA.HierarchyTree ptrNode;
    protected com.cim.AIA.HierarchyTree pathNode;
    final protected static String BUILD_MODE_LABEL;
    final protected static String SEARCH_MODE_LABEL;
    public boolean update234;
    
    public Tree234Tree(com.cim.AIA.AlgorithmThread a, Object a0)
    {
        super(a, a0);
        java.awt.Color a1 = java.awt.Color.lightGray;
        this.T234_NODE_COLOR = a1;
        java.awt.Color a2 = java.awt.Color.red;
        this.T234_COMPARE_COLOR = a2;
        java.awt.Color a3 = java.awt.Color.green;
        this.T234_SPLIT_COLOR = a3;
        java.awt.Color a4 = java.awt.Color.lightGray;
        this.RB_NODE_COLOR = a4;
        java.awt.Color a5 = java.awt.Color.green;
        this.PATH_COLOR = a5;
        this.isBackMode = false;
        this.position = -1;
        this.executingMode = 11;
        int i = this.executingMode;
        this.nextMode = i;
        this.canStoreState = false;
        java.util.Vector a6 = new java.util.Vector();
        this.insertRequests = a6;
        java.util.Vector a7 = new java.util.Vector();
        this.deleteRequests = a7;
        java.util.Vector a8 = new java.util.Vector();
        this.deleter = a8;
        java.util.Vector a9 = new java.util.Vector();
        this.spliter = a9;
        java.util.Vector a10 = new java.util.Vector();
        this.comparer = a10;
        this.animationNode = null;
        this.growNode = null;
        this.update234 = true;
        int[] dummy = (int[])a0;
        int[] a11 = (int[])a0;
        this.data = a11;
        this.baseTree = null;
        this.basePosition = 0;
        this.initialise();
    }
    
    protected void initialiseMethods(String s, com.cim.AIA.MethodSelectable a)
    {
        Object a0 = a;
        String s0 = Tree234Tree.BUILD_MODE_LABEL;
        String s1 = localization.Messages.getString("Tree234Tree.2");
        com.cim.AIA.Logger a1 = this.getLogger();
        com.cim.AIA.BreakPoint a2 = this.getBreakPoint();
        com.cim.AIA.MethodSelection a3 = new com.cim.AIA.MethodSelection(s0, s, s1, "2.2a", a1, a2);
        String s2 = Tree234Tree.SEARCH_MODE_LABEL;
        String s3 = localization.Messages.getString("Tree234Tree.4");
        com.cim.AIA.Logger a4 = this.getLogger();
        com.cim.AIA.BreakPoint a5 = this.getBreakPoint();
        com.cim.AIA.MethodSelection a6 = new com.cim.AIA.MethodSelection(s2, s, s3, "4", a4, a5);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelection(a3, true);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelection(a6, false);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelectionListener((com.cim.AIA.MethodSelectionListener)this);
    }
    
    public void processMethodSelectionEvent(com.cim.AIA.MethodSelectionEvent a)
    {
        String s = Tree234Tree.BUILD_MODE_LABEL;
        String s0 = a.getMethodName();
        int i = s.compareTo(s0);
        label1: {
            label0: {
                if(i != 0)
                {
                    break label0;
                }
                this.nextMode = 11;
                break label1;
            }
            String s1 = Tree234Tree.SEARCH_MODE_LABEL;
            String s2 = a.getMethodName();
            int i0 = s1.compareTo(s2);
            if(i0 == 0)
            {
                this.nextMode = 12;
            }
        }
    }
    
    public com.cim.AIA.HierarchyTree getPtrNode()
    {
        com.cim.AIA.HierarchyTree a = this.ptrNode;
        return a;
    }
    
    public com.cim.AIA.HierarchyTree getParentNode()
    {
        com.cim.AIA.HierarchyTree a = this.parentNode;
        return a;
    }
    
    public com.cim.AIA.HierarchyTree getGrandParentNode()
    {
        com.cim.AIA.HierarchyTree a = this.grandParentNode;
        return a;
    }
    
    public com.cim.AIA.HierarchyTree getGreatGrandParentNode()
    {
        com.cim.AIA.HierarchyTree a = this.greatGrandParentNode;
        return a;
    }
    
    public com.cim.AIA.Node getAnimationNode()
    {
        com.cim.AIA.Node a = this.animationNode;
        return a;
    }
    
    public com.cim.AIA.Node getGrowNode()
    {
        com.cim.AIA.Node a = this.growNode;
        return a;
    }
    
    public com.cim.AIA.HierarchyTree getHierarchyTree(int i)
    {
        Tree234RedBlack a = this.tree;
        Tree234RedBlack a0 = this.tree;
        com.cim.AIA.HierarchyTree a1 = this.getHierarchyTree((com.cim.AIA.HierarchyTree)null, a, a0, 0, i, true, false);
        return a1;
    }
    
    protected com.cim.AIA.HierarchyTree getHierarchyTree(com.cim.AIA.HierarchyTree a, Tree234RedBlack a0, Tree234RedBlack a1, int i, int i0, boolean b, boolean b0)
    {
        com.cim.AIA.HierarchyTree a2 = null;
        label1: {
            int i1 = 0;
            int i2 = 0;
            int i3 = 0;
            com.cim.AIA.Node a3 = null;
            int i4 = 0;
            label0: {
                i1 = b?1:0;
                i2 = b0?1:0;
                if(a0 != null)
                {
                    break label0;
                }
                a2 = null;
                break label1;
            }
            if(i2 != 0)
            {
                int i5 = a0.getDataItem();
                Integer a4 = new Integer(i5);
                int i6 = a0.getPositionInserted();
                com.cim.AIA.Node a5 = new com.cim.AIA.Node((Object)a4, i6);
                i3 = i2;
                a3 = a5;
            }
            else
            {
                int i7 = a0.getDataItem();
                Integer a6 = new Integer(i7);
                int i8 = a0.getPositionInserted();
                com.cim.AIA.RoundNode a7 = new com.cim.AIA.RoundNode((Object)a6, i8);
                i3 = 0;
                a3 = a7;
            }
            a3.setWidth(i0);
            int i9 = a0.newNode?1:0;
            int i10 = i9 & i3;
            if(i10 != 0)
            {
                java.util.Vector a8 = this.insertRequests;
                a8.addElement((Object)a3);
            }
            int i11 = a0.getPositionInserted();
            int i12 = Tree234RedBlack.deletePtr;
            int i13 = i11 != i12?0:1;
            int i14 = i13 & i3;
            if(i14 != 0)
            {
                java.util.Vector a9 = this.deleter;
                a9.addElement((Object)a3);
            }
            int i15 = a0.getPositionInserted();
            int i16 = Tree234RedBlack.comparePtr;
            int i17 = i15 != i16?0:1;
            int i18 = i17 & i3;
            if(i18 != 0)
            {
                java.util.Vector a10 = this.comparer;
                a10.addElement((Object)a3);
            }
            int i19 = a0.getPositionInserted();
            int i20 = Tree234RedBlack.splitPtr;
            int i21 = i19 != i20?0:1;
            int i22 = i21 & i3;
            if(i22 != 0)
            {
                java.util.Vector a11 = this.spliter;
                a11.addElement((Object)a3);
            }
            int i23 = a0.getPositionInserted();
            int i24 = Tree234RedBlack.splitPtr2;
            int i25 = i23 != i24?0:1;
            int i26 = i25 & i3;
            if(i26 != 0)
            {
                java.util.Vector a12 = this.spliter;
                a12.addElement((Object)a3);
            }
            int i27 = a0.getPositionInserted();
            int i28 = Tree234RedBlack.splitPtr3;
            int i29 = i27 != i28?0:1;
            int i30 = i29 & i3;
            if(i30 != 0)
            {
                java.util.Vector a13 = this.spliter;
                a13.addElement((Object)a3);
            }
            int i31 = a0.redNode?1:0;
            if(i31 == 0)
            {
                java.awt.Color a14 = java.awt.Color.gray;
                a3.setHighlightColor(a14);
            }
            else
            {
                java.awt.Color a15 = java.awt.Color.red;
                a3.setHighlightColor(a15);
            }
            java.awt.Color a16 = this.RB_NODE_COLOR;
            a3.setBackgroundColor(a16);
            com.cim.AIA.HierarchyTree a17 = new com.cim.AIA.HierarchyTree((com.cim.AIA.Tree)a, a3);
            if(i3 != 0)
            {
                i4 = i3;
            }
            else
            {
                a17.setDrawBorder(false);
                i4 = 0;
            }
            Tree234RedBlack a18 = a1.ptr;
            label3: {
                label2: {
                    if(a18 != null)
                    {
                        break label2;
                    }
                    Tree234RedBlack a19 = a1.parent;
                    if(a19 != a0)
                    {
                        break label3;
                    }
                    else
                    {
                        this.pathNode = a17;
                        break label3;
                    }
                }
                Tree234RedBlack a20 = a1.ptr;
                if(a20 == a0)
                {
                    this.pathNode = a17;
                }
            }
            label4: {
                if(i1 == 0)
                {
                    break label4;
                }
                Tree234RedBlack a21 = a1.ptr;
                label5: {
                    if(a21 == null)
                    {
                        break label5;
                    }
                    Tree234RedBlack a22 = a1.ptr;
                    if(a22 == a0)
                    {
                        this.ptrNode = a17;
                    }
                }
                Tree234RedBlack a23 = a1.parent;
                label6: {
                    if(a23 == null)
                    {
                        break label6;
                    }
                    Tree234RedBlack a24 = a1.parent;
                    if(a24 == a0)
                    {
                        this.parentNode = a17;
                    }
                }
                Tree234RedBlack a25 = a1.grandParent;
                label7: {
                    if(a25 == null)
                    {
                        break label7;
                    }
                    Tree234RedBlack a26 = a1.grandParent;
                    if(a26 == a0)
                    {
                        this.grandParentNode = a17;
                    }
                }
                Tree234RedBlack a27 = a1.greatGrandParent;
                if(a27 == null)
                {
                    break label4;
                }
                Tree234RedBlack a28 = a1.greatGrandParent;
                if(a28 == a0)
                {
                    this.greatGrandParentNode = a17;
                }
            }
            Tree234RedBlack a29 = a0.leftChild;
            label9: {
                label8: {
                    if(a29 != null)
                    {
                        break label8;
                    }
                    Tree234RedBlack a30 = a0.rightChild;
                    if(a30 == null)
                    {
                        break label8;
                    }
                    Integer a31 = new Integer(0);
                    com.cim.AIA.Node a32 = new com.cim.AIA.Node((Object)a31, -1);
                    a32.setWidth(i0);
                    a32.setIsVisible(false);
                    com.cim.AIA.HierarchyTree a33 = new com.cim.AIA.HierarchyTree((com.cim.AIA.Tree)a17, a32);
                    a17.addChild((com.cim.AIA.Tree)a33);
                    break label9;
                }
                Tree234RedBlack a34 = a0.leftChild;
                if(a34 != null)
                {
                    Tree234RedBlack a35 = a0.leftChild;
                    int i32 = i + 1;
                    com.cim.AIA.HierarchyTree a36 = this.getHierarchyTree(a17, a35, a1, i32, i0, i1 != 0, i4 != 0);
                    a17.addChild((com.cim.AIA.Tree)a36);
                }
            }
            Tree234RedBlack a37 = a0.rightChild;
            label11: {
                label10: {
                    if(a37 != null)
                    {
                        break label10;
                    }
                    Tree234RedBlack a38 = a0.leftChild;
                    if(a38 == null)
                    {
                        break label10;
                    }
                    Integer a39 = new Integer(0);
                    com.cim.AIA.Node a40 = new com.cim.AIA.Node((Object)a39, -1);
                    a40.setWidth(i0);
                    a40.setIsVisible(false);
                    com.cim.AIA.HierarchyTree a41 = new com.cim.AIA.HierarchyTree((com.cim.AIA.Tree)a17, a40);
                    a17.addChild((com.cim.AIA.Tree)a41);
                    break label11;
                }
                Tree234RedBlack a42 = a0.rightChild;
                if(a42 != null)
                {
                    Tree234RedBlack a43 = a0.rightChild;
                    int i33 = i + 1;
                    com.cim.AIA.HierarchyTree a44 = this.getHierarchyTree(a17, a43, a1, i33, i0, i1 != 0, i4 != 0);
                    a17.addChild((com.cim.AIA.Tree)a44);
                }
            }
            label12: {
                if(a == null)
                {
                    break label12;
                }
                int i34 = a0.redNode?1:0;
                if(i34 == 0)
                {
                    java.awt.Color a45 = java.awt.Color.black;
                    a17.setParentLineColor(a45);
                }
                else
                {
                    java.awt.Color a46 = java.awt.Color.red;
                    a17.setParentLineColor(a46);
                    com.cim.AIA.Line a47 = a17.getLine();
                    a47.showAsThick(true);
                    a47.setLineThickness(6);
                }
            }
            a2 = a17;
        }
        return a2;
    }
    
    public com.cim.AIA.HierarchyTree getBalancedHierarchyTree(int i)
    {
        Tree234RedBlack a = this.tree;
        Tree234RedBlack a0 = this.tree;
        com.cim.AIA.HierarchyTree a1 = this.getHierarchyTree((com.cim.AIA.HierarchyTree)null, a, a0, 0, i, false, true);
        com.cim.AIA.HierarchyTree a2 = this.pathNode;
        this.colorHierarchyTree(a2);
        java.awt.Color a3 = this.T234_NODE_COLOR;
        this.colorTree(a1, a3);
        this.balanceHierarchyTree(a1);
        return a1;
    }
    
    private void colorHierarchyTree(com.cim.AIA.HierarchyTree a)
    {
        if(a != null)
        {
            com.cim.AIA.Line a0 = a.getLine();
            a0.showAsThick(true);
            java.awt.Color a1 = this.PATH_COLOR;
            a.setParentLineColor(a1);
            com.cim.AIA.Tree a2 = a.getParent();
            com.cim.AIA.HierarchyTree dummy = (com.cim.AIA.HierarchyTree)a2;
            com.cim.AIA.HierarchyTree a3 = (com.cim.AIA.HierarchyTree)a2;
            this.colorHierarchyTree(a3);
        }
    }
    
    public void colorTree(com.cim.AIA.HierarchyTree a, java.awt.Color a0)
    {
        label1: {
            label0: {
                if(a != null)
                {
                    break label0;
                }
                break label1;
            }
            java.util.Vector a1 = a.getNodes();
            int i = 0;
            while(true)
            {
                int i0 = a1.size();
                if(i >= i0)
                {
                    break;
                }
                else
                {
                    Object a2 = a1.elementAt(i);
                    com.cim.AIA.Node dummy = (com.cim.AIA.Node)a2;
                    com.cim.AIA.Node a3 = (com.cim.AIA.Node)a2;
                    a3.setBackgroundColor(a0);
                    int i1 = i + 1;
                    i = i1;
                }
            }
            int i2 = a.getNumberOfChildren();
            int i3 = i2 - 1;
            int i4 = i3;
            while(true)
            {
                if(i4 < 0)
                {
                    break;
                }
                else
                {
                    com.cim.AIA.Tree a4 = a.getChild(i4);
                    com.cim.AIA.HierarchyTree dummy0 = (com.cim.AIA.HierarchyTree)a4;
                    com.cim.AIA.HierarchyTree a5 = (com.cim.AIA.HierarchyTree)a4;
                    this.colorTree(a5, a0);
                    int i5 = i4 + -1;
                    i4 = i5;
                }
            }
        }
    }
    
    protected void balanceHierarchyTree(com.cim.AIA.HierarchyTree a)
    {
        label1: {
            label0: {
                if(a != null)
                {
                    break label0;
                }
                break label1;
            }
            com.cim.AIA.Node a0 = a.getNodeAt(1);
            int i = 0;
            while(true)
            {
                java.util.Vector a1 = this.comparer;
                int i0 = a1.size();
                if(i >= i0)
                {
                    break;
                }
                java.util.Vector a2 = this.comparer;
                Object a3 = a2.elementAt(i);
                com.cim.AIA.Node dummy = (com.cim.AIA.Node)a3;
                com.cim.AIA.Node a4 = (com.cim.AIA.Node)a3;
                if(a4 == a0)
                {
                    java.util.Vector a5 = this.comparer;
                    a5.removeElementAt(i);
                    java.awt.Color a6 = this.T234_COMPARE_COLOR;
                    a4.setTextColor(a6);
                    java.awt.Color a7 = this.T234_COMPARE_COLOR;
                    a4.setRingColor(a7);
                }
                int i1 = i + 1;
                i = i1;
            }
            int i2 = 0;
            while(true)
            {
                java.util.Vector a8 = this.spliter;
                int i3 = a8.size();
                if(i2 >= i3)
                {
                    break;
                }
                java.util.Vector a9 = this.spliter;
                Object a10 = a9.elementAt(i2);
                com.cim.AIA.Node dummy0 = (com.cim.AIA.Node)a10;
                com.cim.AIA.Node a11 = (com.cim.AIA.Node)a10;
                if(a11 == a0)
                {
                    java.util.Vector a12 = this.spliter;
                    a12.removeElementAt(i2);
                    java.awt.Color a13 = this.T234_SPLIT_COLOR;
                    a11.setBackgroundColor(a13);
                }
                int i4 = i2 + 1;
                i2 = i4;
            }
            int i5 = 0;
            while(true)
            {
                java.util.Vector a14 = this.deleter;
                int i6 = a14.size();
                if(i5 >= i6)
                {
                    break;
                }
                java.util.Vector a15 = this.deleter;
                Object a16 = a15.elementAt(i5);
                com.cim.AIA.Node dummy1 = (com.cim.AIA.Node)a16;
                com.cim.AIA.Node a17 = (com.cim.AIA.Node)a16;
                label4: {
                    label2: {
                        if(a17 != a0)
                        {
                            break label2;
                        }
                        com.cim.AIA.Tree a18 = a.getParent();
                        label3: {
                            if(a18 != null)
                            {
                                break label3;
                            }
                            int i7 = a.numberOfElements();
                            if(i7 <= 1)
                            {
                                break label2;
                            }
                        }
                        java.util.Vector a19 = this.insertRequests;
                        a19.addElement((Object)a0);
                        java.util.Vector a20 = this.deleter;
                        a20.removeElementAt(i5);
                        Object a21 = a17.getObject();
                        int i8 = a17.getIdentifier();
                        com.cim.AIA.Node a22 = new com.cim.AIA.Node(a21, i8);
                        this.animationNode = a22;
                        com.cim.AIA.Node a23 = this.animationNode;
                        java.awt.Color a24 = this.T234_SPLIT_COLOR;
                        a23.setBackgroundColor(a24);
                        break label4;
                    }
                    if(a17 == a0)
                    {
                        java.util.Vector a25 = this.deleter;
                        a25.removeElementAt(i5);
                        java.util.Vector a26 = this.insertRequests;
                        a26.addElement((Object)a0);
                        Object a27 = a17.getObject();
                        int i9 = a17.getIdentifier();
                        com.cim.AIA.Node a28 = new com.cim.AIA.Node(a27, i9);
                        this.animationNode = a28;
                        com.cim.AIA.Node a29 = this.animationNode;
                        java.awt.Color a30 = this.T234_SPLIT_COLOR;
                        a29.setBackgroundColor(a30);
                    }
                }
                int i10 = i5 + 1;
                i5 = i10;
            }
            int i11 = a.getNumberOfChildren();
            int i12 = i11 - 1;
            int i13 = i12;
            while(true)
            {
                if(i13 < 0)
                {
                    break;
                }
                else
                {
                    com.cim.AIA.Tree a31 = a.getChild(i13);
                    com.cim.AIA.HierarchyTree dummy2 = (com.cim.AIA.HierarchyTree)a31;
                    com.cim.AIA.HierarchyTree a32 = (com.cim.AIA.HierarchyTree)a31;
                    this.balanceHierarchyTree(a32);
                    int i14 = i13 + -1;
                    i13 = i14;
                }
            }
            java.util.Vector a33 = a.getChildren();
            int i15 = a33.size();
            int i16 = i15 - 1;
            int i17 = i16;
            while(true)
            {
                int i18 = 0;
                int i19 = 0;
                if(i17 < 0)
                {
                    break;
                }
                Object a34 = a33.elementAt(i17);
                com.cim.AIA.HierarchyTree dummy3 = (com.cim.AIA.HierarchyTree)a34;
                com.cim.AIA.HierarchyTree a35 = (com.cim.AIA.HierarchyTree)a34;
                java.util.Vector a36 = a35.getNodes();
                int i20 = 0;
                while(true)
                {
                    int i21 = a36.size();
                    if(i20 >= i21)
                    {
                        i18 = 1;
                        break;
                    }
                    Object a37 = a36.elementAt(i20);
                    com.cim.AIA.Node dummy4 = (com.cim.AIA.Node)a37;
                    com.cim.AIA.Node a38 = (com.cim.AIA.Node)a37;
                    java.awt.Color a39 = a38.getHighlightColor();
                    java.awt.Color a40 = java.awt.Color.red;
                    if(a39 == a40)
                    {
                        int i22 = i20 + 1;
                        i20 = i22;
                        continue;
                    }
                    i18 = 0;
                    break;
                }
                label5: {
                    int i23 = 0;
                    if(i18 == 0)
                    {
                        i19 = i17;
                        break label5;
                    }
                    label7: {
                        label6: {
                            if(i17 == 0)
                            {
                                break label6;
                            }
                            int i24 = 0;
                            while(true)
                            {
                                int i25 = a36.size();
                                if(i24 >= i25)
                                {
                                    i23 = i17;
                                    break label7;
                                }
                                Object a41 = a36.elementAt(i24);
                                com.cim.AIA.Node dummy5 = (com.cim.AIA.Node)a41;
                                com.cim.AIA.Node a42 = (com.cim.AIA.Node)a41;
                                int i26 = a42.getIsVisible()?1:0;
                                if(i26 != 0)
                                {
                                    a.add(a42);
                                }
                                java.util.Vector a43 = a35.getChildren();
                                int i27 = 0;
                                while(true)
                                {
                                    int i28 = a43.size();
                                    if(i27 >= i28)
                                    {
                                        int i29 = i24 + 1;
                                        i24 = i29;
                                    }
                                    else
                                    {
                                        Object a44 = a43.elementAt(i27);
                                        com.cim.AIA.HierarchyTree dummy6 = (com.cim.AIA.HierarchyTree)a44;
                                        com.cim.AIA.HierarchyTree a45 = (com.cim.AIA.HierarchyTree)a44;
                                        a.addChild((com.cim.AIA.Tree)a45);
                                        int i30 = i27 + 1;
                                        i27 = i30;
                                    }
                                }
                            }
                        }
                        int i31 = a36.size();
                        int i32 = i31 - 1;
                        int i33 = i32;
                        while(true)
                        {
                            if(i33 < 0)
                            {
                                break;
                            }
                            Object a46 = a36.elementAt(i33);
                            com.cim.AIA.Node dummy7 = (com.cim.AIA.Node)a46;
                            com.cim.AIA.Node a47 = (com.cim.AIA.Node)a46;
                            int i34 = a47.getIsVisible()?1:0;
                            if(i34 != 0)
                            {
                                a.add(a47, 0);
                            }
                            java.util.Vector a48 = a35.getChildren();
                            int i35 = a48.size();
                            int i36 = i35 - 1;
                            int i37 = i36;
                            while(true)
                            {
                                if(i37 < 0)
                                {
                                    int i38 = i33 + -1;
                                    i33 = i38;
                                }
                                else
                                {
                                    Object a49 = a48.elementAt(i37);
                                    com.cim.AIA.HierarchyTree dummy8 = (com.cim.AIA.HierarchyTree)a49;
                                    com.cim.AIA.HierarchyTree a50 = (com.cim.AIA.HierarchyTree)a49;
                                    a.insertChildAt((com.cim.AIA.Tree)a50, 0);
                                    int i39 = i37 + -1;
                                    i37 = i39;
                                }
                            }
                        }
                        i23 = 0;
                    }
                    a.removeChild((com.cim.AIA.Tree)a35);
                    i19 = i23;
                }
                int i40 = i19 + -1;
                i17 = i40;
            }
        }
    }
    
    protected void initialise()
    {
        int i = this.executingMode;
        label1: {
            label0: {
                if(i == 11)
                {
                    break label0;
                }
                int i0 = this.nextMode;
                if(i0 != 11)
                {
                    break label1;
                }
            }
            Tree234RedBlack a = this.baseTree;
            if(a == null)
            {
                this.tree = null;
            }
            else
            {
                Tree234RedBlack a0 = this.baseTree;
                Tree234RedBlack a1 = a0.copy();
                this.tree = a1;
            }
        }
        java.util.Vector a2 = new java.util.Vector();
        this.insertRequests = a2;
        java.util.Vector a3 = new java.util.Vector();
        this.deleteRequests = a3;
        java.util.Vector a4 = new java.util.Vector();
        this.deleter = a4;
        java.util.Vector a5 = new java.util.Vector();
        this.spliter = a5;
        java.util.Vector a6 = new java.util.Vector();
        this.comparer = a6;
        this.ptrNode = null;
        this.greatGrandParentNode = null;
        this.grandParentNode = null;
        this.parentNode = null;
        this.position = -1;
        int i1 = this.basePosition;
        Tree234RedBlack.POSITION = i1;
    }
    
    public void reInitialise(Object a)
    {
        int[] dummy = (int[])a;
        int[] a0 = (int[])a;
        this.data = a0;
        this.initialise();
    }
    
    protected void changeData(Object a)
    {
        int[] dummy = (int[])a;
        int[] a0 = (int[])a;
        this.data = a0;
        this.position = -1;
        this.save();
    }
    
    protected void storeState(boolean b)
    {
        int i = this.canStoreState?1:0;
        int i0 = b?1:0;
        label1: {
            label0: {
                if(i != 0)
                {
                    break label0;
                }
                if(i0 == 0)
                {
                    break label1;
                }
            }
            this.save();
        }
    }
    
    private void save()
    {
        Tree234RedBlack a = this.tree;
        if(a == null)
        {
            this.baseTree = null;
        }
        else
        {
            Tree234RedBlack a0 = this.tree;
            Tree234RedBlack a1 = a0.copy();
            this.baseTree = a1;
        }
        int i = Tree234RedBlack.POSITION;
        this.basePosition = i;
    }
    
    protected void clearState()
    {
        this.baseTree = null;
        this.basePosition = 0;
    }
    
    public com.cim.AIA.MultipleTween generateTweens(com.cim.AIA.Paintable a, Object a0, int i)
    {
        com.cim.AIA.MultipleTween a1 = null;
        Object a2 = a;
        com.cim.AIA.MultipleTween a3 = new com.cim.AIA.MultipleTween((com.cim.AIA.Paintable)a2);
        int i0 = 0;
        while(true)
        {
            java.util.Vector a4 = this.insertRequests;
            int i1 = a4.size();
            if(i0 >= i1)
            {
                break;
            }
            else
            {
                java.util.Vector a5 = this.insertRequests;
                Object a6 = a5.elementAt(i0);
                com.cim.AIA.Node dummy = (com.cim.AIA.Node)a6;
                com.cim.AIA.Node a7 = (com.cim.AIA.Node)a6;
                com.cim.AIA.InsertTween a8 = new com.cim.AIA.InsertTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Sizeable)a7, i);
                a3.add((com.cim.AIA.Tween)a8);
                int i2 = i0 + 1;
                i0 = i2;
            }
        }
        int i3 = 0;
        while(true)
        {
            java.util.Vector a9 = this.deleteRequests;
            int i4 = a9.size();
            if(i3 >= i4)
            {
                break;
            }
            else
            {
                java.util.Vector a10 = this.deleteRequests;
                Object a11 = a10.elementAt(i3);
                com.cim.AIA.Node dummy0 = (com.cim.AIA.Node)a11;
                com.cim.AIA.Node a12 = (com.cim.AIA.Node)a11;
                com.cim.AIA.DeleteTween a13 = new com.cim.AIA.DeleteTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Sizeable)a12, i);
                a3.add((com.cim.AIA.Tween)a13);
                int i5 = i3 + 1;
                i3 = i5;
            }
        }
        java.util.Vector a14 = this.insertRequests;
        int i6 = a14.size();
        label2: {
            label1: {
                label0: {
                    if(i6 > 0)
                    {
                        break label0;
                    }
                    java.util.Vector a15 = this.deleteRequests;
                    int i7 = a15.size();
                    if(i7 <= 0)
                    {
                        break label1;
                    }
                }
                a1 = a3;
                break label2;
            }
            a1 = null;
        }
        return a1;
    }
    
    protected java.util.Vector generateQuestions()
    {
        return null;
    }
    
    protected boolean hasQuestions()
    {
        return false;
    }
    
    protected void removeAllQuestions()
    {
    }
    
    protected void removeAllAnimationRequests()
    {
        java.util.Vector a = new java.util.Vector();
        this.insertRequests = a;
        java.util.Vector a0 = new java.util.Vector();
        this.deleteRequests = a0;
        Tree234RedBlack a1 = this.tree;
        this.removeTree234TreeDeletes(a1);
        Tree234RedBlack a2 = this.tree;
        this.removeTree234TreeInserts(a2);
        this.growNode = null;
        this.animationNode = null;
    }
    
    protected void removeTree234TreeInserts(Tree234RedBlack a)
    {
        label0: {
            if(a == null)
            {
                break label0;
            }
            a.newNode = false;
            Tree234RedBlack a0 = a.getLeftChild();
            if(a0 != null)
            {
                Tree234RedBlack a1 = a.getLeftChild();
                this.removeTree234TreeInserts(a1);
            }
            Tree234RedBlack a2 = a.getRightChild();
            if(a2 != null)
            {
                Tree234RedBlack a3 = a.getRightChild();
                this.removeTree234TreeInserts(a3);
            }
        }
    }
    
    protected void removeTree234TreeDeletes(Tree234RedBlack a)
    {
        label0: {
            if(a == null)
            {
                break label0;
            }
            a.deleteNode = false;
            Tree234RedBlack.deletePtr = -1;
            Tree234RedBlack a0 = a.getLeftChild();
            if(a0 != null)
            {
                Tree234RedBlack a1 = a.getLeftChild();
                this.removeTree234TreeDeletes(a1);
            }
            Tree234RedBlack a2 = a.getRightChild();
            if(a2 != null)
            {
                Tree234RedBlack a3 = a.getRightChild();
                this.removeTree234TreeDeletes(a3);
            }
        }
    }
    
    protected void run()
    {
        int i = this.executingMode;
        switch(i){
            case 12: {
                this.search();
                break;
            }
            case 11: {
                this.buildTree();
                break;
            }
        }
    }
    
    protected void run(boolean b)
    {
        if(b)
        {
            int i = this.nextMode;
            this.executingMode = i;
        }
        this.run();
    }
    
    public void setCodePosition(String s)
    {
        this.setPosition(s);
    }
    
    public void search()
    {
        int[] a = this.data;
        int i = a.length;
        boolean[] a0 = new boolean[i];
        this.searchResults = a0;
        int i0 = 0;
        while(true)
        {
            boolean[] a1 = this.searchResults;
            int i1 = a1.length;
            if(i0 >= i1)
            {
                break;
            }
            else
            {
                boolean[] a2 = this.searchResults;
                a2[i0] = false;
                int i2 = i0 + 1;
                i0 = i2;
            }
        }
        int i3 = 0;
        while(true)
        {
            int[] a3 = this.data;
            int i4 = a3.length;
            label2: {
                label1: {
                    label0: {
                        if(i3 < i4)
                        {
                            break label0;
                        }
                        this.position = -1;
                        break label1;
                    }
                    int i5 = this.enabled?1:0;
                    if(i5 != 0)
                    {
                        break label2;
                    }
                }
                return;
            }
            this.position = i3;
            Tree234RedBlack a4 = this.tree;
            if(a4 == null)
            {
                boolean[] a5 = this.searchResults;
                a5[i3] = false;
            }
            else
            {
                boolean[] a6 = this.searchResults;
                Tree234RedBlack a7 = this.tree;
                int[] a8 = this.data;
                int i6 = a8[i3];
                int i7 = a7.search(i6, this)?1:0;
                a6[i3] = i7 != 0;
                this.resetMarkers();
            }
            int i8 = i3 + 1;
            i3 = i8;
        }
    }
    
    public boolean getEnabled()
    {
        int i = this.enabled?1:0;
        return i != 0;
    }
    
    protected void resetMarkers()
    {
        this.ptrNode = null;
        this.greatGrandParentNode = null;
        this.grandParentNode = null;
        this.parentNode = null;
        Tree234RedBlack a = this.tree;
        if(a != null)
        {
            Tree234RedBlack a0 = this.tree;
            a0.resetMarkers();
        }
    }
    
    public com.cim.AIA.ElementArray getDataElementArray()
    {
        com.cim.AIA.ElementArray a = new com.cim.AIA.ElementArray(0, 0);
        int i = 0;
        while(true)
        {
            int[] a0 = this.data;
            int i0 = a0.length;
            if(i >= i0)
            {
                return a;
            }
            StringBuilder a1 = new StringBuilder();
            StringBuilder a2 = a1.append("");
            int[] a3 = this.data;
            int i1 = a3[i];
            StringBuilder a4 = a2.append(i1);
            String s = a4.toString();
            com.cim.AIA.Node a5 = new com.cim.AIA.Node((Object)s, i);
            int i2 = this.position;
            if(i == i2)
            {
                a5.highlight();
            }
            int i3 = this.executingMode;
            label0: {
                if(i3 != 12)
                {
                    break label0;
                }
                boolean[] a6 = this.searchResults;
                int i4 = a6.length;
                if(i >= i4)
                {
                    break label0;
                }
                int i5 = this.position;
                if(i >= i5)
                {
                    break label0;
                }
                boolean[] a7 = this.searchResults;
                int i6 = a7[i]?1:0;
                String s0 = i6 == 0?"F":"T";
                com.cim.AIA.StringMarker a8 = new com.cim.AIA.StringMarker(s0);
                a5.addMarker(a8);
            }
            a.setValue(i, (com.cim.AIA.Element)a5);
            int i7 = i + 1;
            i = i7;
        }
    }
    
    public void insert(int i)
    {
        Tree234RedBlack a = this.tree;
        label3: {
            label1: {
                label0: {
                    if(a == null)
                    {
                        break label0;
                    }
                    Tree234RedBlack a0 = this.tree;
                    a0.insert(i, this);
                    break label1;
                }
                this.setPosition("3");
                this.setPosition("3.1.1");
                this.setPosition("3.2.0.1");
                int i0 = this.enabled?1:0;
                label2: {
                    if(i0 != 0)
                    {
                        break label2;
                    }
                    break label3;
                }
                Tree234RedBlack a1 = new Tree234RedBlack(i, (Tree234RedBlack)null, (Tree234RedBlack)null, false);
                this.tree = a1;
                this.setPosition("3.3");
                this.setPosition("3.5");
            }
            this.resetMarkers();
        }
    }
    
    public void buildTree()
    {
        this.canStoreState = false;
        this.setPosition("2");
        this.setPosition("2.1");
        int i = this.basePosition;
        int i0 = i;
        label1: {
            label0: {
                while(true)
                {
                    int[] a = this.data;
                    int i1 = a.length;
                    if(i0 >= i1)
                    {
                        break label0;
                    }
                    int i2 = this.enabled?1:0;
                    if(i2 != 0)
                    {
                        this.position = i0;
                        this.setPosition("2.2");
                        int[] a0 = this.data;
                        int i3 = a0[i0];
                        this.insert(i3);
                        int i4 = i0 + 1;
                        i0 = i4;
                        continue;
                    }
                    else
                    {
                        break;
                    }
                }
                break label1;
            }
            this.position = -1;
            this.setPosition("2.3");
            int i5 = this.enabled?1:0;
            if(i5 != 0)
            {
                this.canStoreState = true;
            }
        }
    }
    
    public void controlStep(com.cim.AIA.ControlEvent a)
    {
        this.isBackMode = false;
    }
    
    public void controlBack(com.cim.AIA.ControlEvent a)
    {
        this.isBackMode = true;
    }
    
    public void controlPause(com.cim.AIA.ControlEvent a)
    {
        this.isBackMode = false;
    }
    
    public void controlReset(com.cim.AIA.ControlEvent a)
    {
        this.isBackMode = false;
    }
    
    public void controlRestart(com.cim.AIA.ControlEvent a)
    {
        this.isBackMode = false;
    }
    
    public void controlRun(com.cim.AIA.ControlEvent a)
    {
        this.isBackMode = false;
    }
    
    public void controlOther(com.cim.AIA.ControlEvent a)
    {
        this.isBackMode = false;
    }
    
    static
    {
        Tree234Tree.POS = 0;
        String s = localization.Messages.getString("Tree234Tree.0");
        Tree234Tree.BUILD_MODE_LABEL = s;
        String s0 = localization.Messages.getString("Tree234Tree.1");
        Tree234Tree.SEARCH_MODE_LABEL = s0;
    }
}