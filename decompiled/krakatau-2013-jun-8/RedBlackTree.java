public class RedBlackTree extends com.cim.AIA.Algorithm implements com.cim.AIA.MethodSelectionListener {
    final protected static java.awt.Color tuteNodeColor;
    final protected static java.awt.Color redBlackTreeNodeColor;
    final protected static java.awt.Color tuteHighLightColor;
    final protected static java.awt.Color tuteSelectColor;
    protected static int POS;
    final protected static int BUILD_MODE = 11;
    final protected static int SEARCH_MODE = 12;
    final protected static String BUILD_MODE_LABEL;
    final protected static String SEARCH_MODE_LABEL;
    protected RotateAnimation animThread;
    protected RedBlack tuteTree;
    protected com.cim.AIA.HierarchyTree tuteHTree;
    protected boolean noHTreeUpdate;
    protected boolean removeSaveLine;
    protected int[] data;
    protected boolean[] searchResults;
    protected int position;
    protected int executingMode;
    protected int nextMode;
    protected RedBlack tree;
    protected RedBlack baseTree;
    protected int basePosition;
    protected boolean canStoreState;
    protected java.util.Vector lineMoveRequests;
    protected java.util.Vector insertRequests;
    protected java.util.Vector deleteRequests;
    protected java.util.Vector deleter;
    protected com.cim.AIA.Node animationNode;
    protected com.cim.AIA.Node growNode;
    protected com.cim.AIA.HierarchyTree parentNode;
    protected com.cim.AIA.HierarchyTree grandParentNode;
    protected com.cim.AIA.HierarchyTree greatGrandParentNode;
    protected com.cim.AIA.HierarchyTree ptrNode;
    private com.cim.common.MessageDialog messageDialog;
    private com.cim.AIA.AlgorithmThread theThread;
    public boolean update234;
    protected RedBlack findNodePtr;
    protected RedBlack findNodeParent;
    private boolean doRightRotate;
    
    public RedBlackTree(com.cim.AIA.AlgorithmThread a, Object a0)
    {
        super(a, a0);
        RotateAnimation a1 = new RotateAnimation();
        this.animThread = a1;
        this.noHTreeUpdate = false;
        this.removeSaveLine = false;
        this.position = -1;
        this.executingMode = 11;
        int i = this.executingMode;
        this.nextMode = i;
        this.canStoreState = false;
        java.util.Vector a2 = new java.util.Vector();
        this.lineMoveRequests = a2;
        java.util.Vector a3 = new java.util.Vector();
        this.insertRequests = a3;
        java.util.Vector a4 = new java.util.Vector();
        this.deleteRequests = a4;
        java.util.Vector a5 = new java.util.Vector();
        this.deleter = a5;
        this.animationNode = null;
        this.growNode = null;
        this.update234 = true;
        this.findNodePtr = null;
        this.findNodeParent = null;
        this.theThread = a;
        int[] dummy = (int[])a0;
        int[] a6 = (int[])a0;
        this.data = a6;
        this.baseTree = null;
        this.basePosition = 0;
        RotateAnimation a7 = this.animThread;
        a7.start();
        this.initialise();
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
                java.util.Vector a1 = this.deleter;
                int i0 = a1.size();
                if(i >= i0)
                {
                    break;
                }
                java.util.Vector a2 = this.deleter;
                Object a3 = a2.elementAt(i);
                com.cim.AIA.Node dummy = (com.cim.AIA.Node)a3;
                com.cim.AIA.Node a4 = (com.cim.AIA.Node)a3;
                label4: {
                    label2: {
                        if(a4 != a0)
                        {
                            break label2;
                        }
                        com.cim.AIA.Tree a5 = a.getParent();
                        label3: {
                            if(a5 != null)
                            {
                                break label3;
                            }
                            int i1 = a.numberOfElements();
                            if(i1 <= 1)
                            {
                                break label2;
                            }
                        }
                        java.util.Vector a6 = this.insertRequests;
                        a6.addElement((Object)a0);
                        java.util.Vector a7 = this.deleter;
                        a7.removeElementAt(i);
                        Object a8 = a4.getObject();
                        int i2 = a4.getIdentifier();
                        com.cim.AIA.Node a9 = new com.cim.AIA.Node(a8, i2);
                        this.animationNode = a9;
                        com.cim.AIA.Node a10 = this.animationNode;
                        java.awt.Color a11 = java.awt.Color.green;
                        a10.setBackgroundColor(a11);
                        break label4;
                    }
                    if(a4 == a0)
                    {
                        java.util.Vector a12 = this.deleter;
                        a12.removeElementAt(i);
                        java.util.Vector a13 = this.insertRequests;
                        a13.addElement((Object)a0);
                        Object a14 = a4.getObject();
                        int i3 = a4.getIdentifier();
                        com.cim.AIA.Node a15 = new com.cim.AIA.Node(a14, i3);
                        this.animationNode = a15;
                        com.cim.AIA.Node a16 = this.animationNode;
                        java.awt.Color a17 = java.awt.Color.green;
                        a16.setBackgroundColor(a17);
                    }
                }
                int i4 = i + 1;
                i = i4;
            }
            int i5 = a.getNumberOfChildren();
            int i6 = i5 - 1;
            int i7 = i6;
            while(true)
            {
                if(i7 < 0)
                {
                    break;
                }
                else
                {
                    com.cim.AIA.Tree a18 = a.getChild(i7);
                    com.cim.AIA.HierarchyTree dummy0 = (com.cim.AIA.HierarchyTree)a18;
                    com.cim.AIA.HierarchyTree a19 = (com.cim.AIA.HierarchyTree)a18;
                    this.balanceHierarchyTree(a19);
                    int i8 = i7 + -1;
                    i7 = i8;
                }
            }
            java.util.Vector a20 = a.getChildren();
            int i9 = a20.size();
            int i10 = i9 - 1;
            int i11 = i10;
            while(true)
            {
                int i12 = 0;
                int i13 = 0;
                if(i11 < 0)
                {
                    break;
                }
                Object a21 = a20.elementAt(i11);
                com.cim.AIA.HierarchyTree dummy1 = (com.cim.AIA.HierarchyTree)a21;
                com.cim.AIA.HierarchyTree a22 = (com.cim.AIA.HierarchyTree)a21;
                java.util.Vector a23 = a22.getNodes();
                int i14 = 0;
                while(true)
                {
                    int i15 = a23.size();
                    if(i14 >= i15)
                    {
                        i12 = 1;
                        break;
                    }
                    Object a24 = a23.elementAt(i14);
                    com.cim.AIA.Node dummy2 = (com.cim.AIA.Node)a24;
                    com.cim.AIA.Node a25 = (com.cim.AIA.Node)a24;
                    java.awt.Color a26 = a25.getHighlightColor();
                    java.awt.Color a27 = java.awt.Color.red;
                    if(a26 == a27)
                    {
                        int i16 = i14 + 1;
                        i14 = i16;
                        continue;
                    }
                    i12 = 0;
                    break;
                }
                label5: {
                    int i17 = 0;
                    if(i12 == 0)
                    {
                        i13 = i11;
                        break label5;
                    }
                    label7: {
                        label6: {
                            if(i11 == 0)
                            {
                                break label6;
                            }
                            int i18 = 0;
                            while(true)
                            {
                                int i19 = a23.size();
                                if(i18 >= i19)
                                {
                                    i17 = i11;
                                    break label7;
                                }
                                Object a28 = a23.elementAt(i18);
                                com.cim.AIA.Node dummy3 = (com.cim.AIA.Node)a28;
                                com.cim.AIA.Node a29 = (com.cim.AIA.Node)a28;
                                int i20 = a29.getIsVisible()?1:0;
                                if(i20 != 0)
                                {
                                    a.add(a29);
                                }
                                java.util.Vector a30 = a22.getChildren();
                                int i21 = 0;
                                while(true)
                                {
                                    int i22 = a30.size();
                                    if(i21 >= i22)
                                    {
                                        int i23 = i18 + 1;
                                        i18 = i23;
                                    }
                                    else
                                    {
                                        Object a31 = a30.elementAt(i21);
                                        com.cim.AIA.HierarchyTree dummy4 = (com.cim.AIA.HierarchyTree)a31;
                                        com.cim.AIA.HierarchyTree a32 = (com.cim.AIA.HierarchyTree)a31;
                                        a.addChild((com.cim.AIA.Tree)a32);
                                        int i24 = i21 + 1;
                                        i21 = i24;
                                    }
                                }
                            }
                        }
                        int i25 = a23.size();
                        int i26 = i25 - 1;
                        int i27 = i26;
                        while(true)
                        {
                            if(i27 < 0)
                            {
                                break;
                            }
                            Object a33 = a23.elementAt(i27);
                            com.cim.AIA.Node dummy5 = (com.cim.AIA.Node)a33;
                            com.cim.AIA.Node a34 = (com.cim.AIA.Node)a33;
                            int i28 = a34.getIsVisible()?1:0;
                            if(i28 != 0)
                            {
                                a.add(a34, 0);
                            }
                            java.util.Vector a35 = a22.getChildren();
                            int i29 = a35.size();
                            int i30 = i29 - 1;
                            int i31 = i30;
                            while(true)
                            {
                                if(i31 < 0)
                                {
                                    int i32 = i27 + -1;
                                    i27 = i32;
                                }
                                else
                                {
                                    Object a36 = a35.elementAt(i31);
                                    com.cim.AIA.HierarchyTree dummy6 = (com.cim.AIA.HierarchyTree)a36;
                                    com.cim.AIA.HierarchyTree a37 = (com.cim.AIA.HierarchyTree)a36;
                                    a.insertChildAt((com.cim.AIA.Tree)a37, 0);
                                    int i33 = i31 + -1;
                                    i31 = i33;
                                }
                            }
                        }
                        i17 = 0;
                    }
                    a.removeChild((com.cim.AIA.Tree)a22);
                    i13 = i17;
                }
                int i34 = i13 + -1;
                i11 = i34;
            }
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
    
    public void buildTuteTree()
    {
        int[] a = new int[15];
        a[0] = 75;
        a[1] = 5;
        a[2] = 70;
        a[3] = 10;
        a[4] = 65;
        a[5] = 15;
        a[6] = 60;
        a[7] = 20;
        a[8] = 55;
        a[9] = 25;
        a[10] = 50;
        a[11] = 30;
        a[12] = 45;
        a[13] = 35;
        a[14] = 40;
        int i = a[0];
        RedBlack a0 = new RedBlack(i, (RedBlack)null, (RedBlack)null, false);
        this.tuteTree = a0;
        int i0 = 1;
        while(true)
        {
            if(i0 >= 15)
            {
                return;
            }
            else
            {
                RedBlack a1 = this.tuteTree;
                int i1 = a[i0];
                a1.insert(i1);
                int i2 = i0 + 1;
                i0 = i2;
            }
        }
    }
    
    protected void changeData(Object a)
    {
        int[] dummy = (int[])a;
        int[] a0 = (int[])a;
        this.data = a0;
        this.position = -1;
        this.save();
    }
    
    protected void clearState()
    {
        this.baseTree = null;
        this.basePosition = 0;
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
    
    public void drawAllParentLines(RedBlack a)
    {
        if(a != null)
        {
            a.setDrawParentLine(true);
            RedBlack a0 = a.leftChild;
            this.drawAllParentLines(a0);
            RedBlack a1 = a.rightChild;
            this.drawAllParentLines(a1);
        }
    }
    
    protected RedBlack findNode(RedBlack a, com.cim.AIA.HierarchyTree a0, com.cim.AIA.Node a1)
    {
        RedBlack a2 = null;
        label2: {
            label1: {
                label0: {
                    if(a == null)
                    {
                        break label0;
                    }
                    if(a0 != null)
                    {
                        break label1;
                    }
                }
                a2 = null;
                break label2;
            }
            com.cim.AIA.Node a3 = a0.getNodeAt(1);
            label3: {
                if(a3 != a1)
                {
                    break label3;
                }
                this.findNodePtr = a;
                a2 = a;
                break label2;
            }
            com.cim.AIA.HierarchyTree a4 = a0.getLeftChild();
            com.cim.AIA.HierarchyTree a5 = a0.getRightChild();
            RedBlack a6 = a.leftChild;
            RedBlack a7 = this.findNode(a6, a4, a1);
            RedBlack a8 = a.rightChild;
            RedBlack a9 = this.findNode(a8, a5, a1);
            if(a7 != null)
            {
                this.findNodeParent = a;
            }
            if(a9 != null)
            {
                this.findNodeParent = a;
            }
            a2 = null;
        }
        return a2;
    }
    
    protected java.util.Vector generateQuestions()
    {
        return null;
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
                com.cim.AIA.Sizeable dummy = (com.cim.AIA.Sizeable)a6;
                com.cim.AIA.InsertTween a7 = new com.cim.AIA.InsertTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Sizeable)a6, i);
                a3.add((com.cim.AIA.Tween)a7);
                int i2 = i0 + 1;
                i0 = i2;
            }
        }
        int i3 = 0;
        while(true)
        {
            java.util.Vector a8 = this.deleteRequests;
            int i4 = a8.size();
            if(i3 >= i4)
            {
                break;
            }
            else
            {
                java.util.Vector a9 = this.deleteRequests;
                Object a10 = a9.elementAt(i3);
                com.cim.AIA.Node dummy0 = (com.cim.AIA.Node)a10;
                com.cim.AIA.Node a11 = (com.cim.AIA.Node)a10;
                com.cim.AIA.DeleteTween a12 = new com.cim.AIA.DeleteTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Sizeable)a11, i);
                a3.add((com.cim.AIA.Tween)a12);
                int i5 = i3 + 1;
                i3 = i5;
            }
        }
        java.util.Vector a13 = this.insertRequests;
        int i6 = a13.size();
        label2: {
            label1: {
                label0: {
                    if(i6 > 0)
                    {
                        break label0;
                    }
                    java.util.Vector a14 = this.deleteRequests;
                    int i7 = a14.size();
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
    
    public com.cim.AIA.Node getAnimationNode()
    {
        com.cim.AIA.Node a = this.animationNode;
        return a;
    }
    
    public Thread getAnimThread()
    {
        RotateAnimation a = this.animThread;
        return a;
    }
    
    public com.cim.AIA.HierarchyTree getBalancedHierarchyTree(int i)
    {
        RedBlack a = this.tree;
        RedBlack a0 = this.tree;
        com.cim.AIA.HierarchyTree a1 = this.getHierarchyTree((com.cim.AIA.HierarchyTree)null, a, a0, 0, i, false, true);
        this.balanceHierarchyTree(a1);
        java.awt.Color a2 = java.awt.Color.green;
        this.colorTree(a1, a2);
        return a1;
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
    
    public boolean getEnabled()
    {
        int i = this.enabled?1:0;
        return i != 0;
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
    
    public com.cim.AIA.Node getGrowNode()
    {
        com.cim.AIA.Node a = this.growNode;
        return a;
    }
    
    protected com.cim.AIA.HierarchyTree getHierarchyTree(com.cim.AIA.HierarchyTree a, RedBlack a0, RedBlack a1, int i, int i0, boolean b, boolean b0)
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
            int i12 = RedBlack.deletePtr;
            int i13 = i11 != i12?0:1;
            int i14 = i13 & i3;
            if(i14 != 0)
            {
                java.util.Vector a9 = this.deleter;
                a9.addElement((Object)a3);
            }
            int i15 = a0.redNode?1:0;
            if(i15 == 0)
            {
                java.awt.Color a10 = java.awt.Color.gray;
                a3.setHighlightColor(a10);
            }
            else
            {
                java.awt.Color a11 = java.awt.Color.red;
                a3.setHighlightColor(a11);
            }
            java.awt.Color a12 = RedBlackTree.redBlackTreeNodeColor;
            a3.setBackgroundColor(a12);
            com.cim.AIA.HierarchyTree a13 = new com.cim.AIA.HierarchyTree((com.cim.AIA.Tree)a, a3);
            if(i3 != 0)
            {
                i4 = i3;
            }
            else
            {
                a13.setDrawBorder(false);
                i4 = 0;
            }
            label2: {
                if(i1 == 0)
                {
                    break label2;
                }
                RedBlack a14 = a1.ptr;
                label3: {
                    if(a14 == null)
                    {
                        break label3;
                    }
                    RedBlack a15 = a1.ptr;
                    if(a15 == a0)
                    {
                        this.ptrNode = a13;
                    }
                }
                RedBlack a16 = a1.parent;
                label4: {
                    if(a16 == null)
                    {
                        break label4;
                    }
                    RedBlack a17 = a1.parent;
                    if(a17 == a0)
                    {
                        this.parentNode = a13;
                    }
                }
                RedBlack a18 = a1.grandParent;
                label5: {
                    if(a18 == null)
                    {
                        break label5;
                    }
                    RedBlack a19 = a1.grandParent;
                    if(a19 == a0)
                    {
                        this.grandParentNode = a13;
                    }
                }
                RedBlack a20 = a1.greatGrandParent;
                if(a20 == null)
                {
                    break label2;
                }
                RedBlack a21 = a1.greatGrandParent;
                if(a21 == a0)
                {
                    this.greatGrandParentNode = a13;
                }
            }
            RedBlack a22 = a0.leftChild;
            label7: {
                label6: {
                    if(a22 != null)
                    {
                        break label6;
                    }
                    RedBlack a23 = a0.rightChild;
                    if(a23 == null)
                    {
                        break label6;
                    }
                    Integer a24 = new Integer(0);
                    com.cim.AIA.Node a25 = new com.cim.AIA.Node((Object)a24, -1);
                    a25.setWidth(i0);
                    a25.setIsVisible(false);
                    com.cim.AIA.HierarchyTree a26 = new com.cim.AIA.HierarchyTree((com.cim.AIA.Tree)a13, a25);
                    a13.addChild((com.cim.AIA.Tree)a26);
                    break label7;
                }
                RedBlack a27 = a0.leftChild;
                if(a27 != null)
                {
                    RedBlack a28 = a0.leftChild;
                    int i16 = i + 1;
                    com.cim.AIA.HierarchyTree a29 = this.getHierarchyTree(a13, a28, a1, i16, i0, i1 != 0, i4 != 0);
                    a13.addChild((com.cim.AIA.Tree)a29);
                }
            }
            RedBlack a30 = a0.rightChild;
            label9: {
                label8: {
                    if(a30 != null)
                    {
                        break label8;
                    }
                    RedBlack a31 = a0.leftChild;
                    if(a31 == null)
                    {
                        break label8;
                    }
                    Integer a32 = new Integer(0);
                    com.cim.AIA.Node a33 = new com.cim.AIA.Node((Object)a32, -1);
                    a33.setWidth(i0);
                    a33.setIsVisible(false);
                    com.cim.AIA.HierarchyTree a34 = new com.cim.AIA.HierarchyTree((com.cim.AIA.Tree)a13, a33);
                    a13.addChild((com.cim.AIA.Tree)a34);
                    break label9;
                }
                RedBlack a35 = a0.rightChild;
                if(a35 != null)
                {
                    RedBlack a36 = a0.rightChild;
                    int i17 = i + 1;
                    com.cim.AIA.HierarchyTree a37 = this.getHierarchyTree(a13, a36, a1, i17, i0, i1 != 0, i4 != 0);
                    a13.addChild((com.cim.AIA.Tree)a37);
                }
            }
            label10: {
                if(a == null)
                {
                    break label10;
                }
                int i18 = a0.redNode?1:0;
                if(i18 == 0)
                {
                    java.awt.Color a38 = java.awt.Color.black;
                    a13.setParentLineColor(a38);
                }
                else
                {
                    java.awt.Color a39 = java.awt.Color.red;
                    a13.setParentLineColor(a39);
                    com.cim.AIA.Line a40 = a13.getLine();
                    a40.showAsThick(true);
                    a40.setLineThickness(6);
                }
            }
            a2 = a13;
        }
        return a2;
    }
    
    public com.cim.AIA.HierarchyTree getHierarchyTree(int i)
    {
        RedBlack a = this.tree;
        RedBlack a0 = this.tree;
        com.cim.AIA.HierarchyTree a1 = this.getHierarchyTree((com.cim.AIA.HierarchyTree)null, a, a0, 0, i, true, false);
        return a1;
    }
    
    public java.util.Vector getLineMoveRequests()
    {
        java.util.Vector a = this.lineMoveRequests;
        return a;
    }
    
    public com.cim.AIA.HierarchyTree getParentNode()
    {
        com.cim.AIA.HierarchyTree a = this.parentNode;
        return a;
    }
    
    public com.cim.AIA.HierarchyTree getPtrNode()
    {
        com.cim.AIA.HierarchyTree a = this.ptrNode;
        return a;
    }
    
    public boolean getRemoveSaveLine()
    {
        int i = this.removeSaveLine?1:0;
        return i != 0;
    }
    
    protected com.cim.AIA.HierarchyTree getTuteTree(com.cim.AIA.HierarchyTree a, RedBlack a0, RedBlack a1, int i, int i0)
    {
        com.cim.AIA.HierarchyTree a2 = null;
        label1: {
            label0: {
                if(a0 != null)
                {
                    break label0;
                }
                a2 = null;
                break label1;
            }
            int i1 = a0.getDataItem();
            Integer a3 = new Integer(i1);
            int i2 = a0.getPositionInserted();
            com.cim.AIA.Node a4 = new com.cim.AIA.Node((Object)a3, i2);
            a4.setWidth(i0);
            java.awt.Color a5 = RedBlackTree.tuteNodeColor;
            a4.setBackgroundColor(a5);
            int i3 = a0.getPositionInserted();
            int i4 = RedBlack.ptrP;
            if(i3 == i4)
            {
                java.awt.Color a6 = RedBlackTree.tuteSelectColor;
                a4.setBackgroundColor(a6);
                String s = localization.Messages.getString("RedBlackTree.3");
                com.cim.AIA.StringMarker a7 = new com.cim.AIA.StringMarker(s);
                a4.addMarker(a7);
            }
            int i5 = a0.getPositionInserted();
            int i6 = RedBlack.ptrC;
            if(i5 == i6)
            {
                java.awt.Color a8 = RedBlackTree.tuteSelectColor;
                a4.setBackgroundColor(a8);
                String s0 = localization.Messages.getString("RedBlackTree.2");
                com.cim.AIA.StringMarker a9 = new com.cim.AIA.StringMarker(s0);
                a4.addMarker(a9);
            }
            com.cim.AIA.HierarchyTree a10 = new com.cim.AIA.HierarchyTree((com.cim.AIA.Tree)a, a4);
            int i7 = a0.getDrawParentLine()?1:0;
            if(i7 == 0)
            {
                a10.setDrawParentLine(false);
            }
            RedBlack a11 = a0.leftChild;
            label3: {
                label2: {
                    if(a11 != null)
                    {
                        break label2;
                    }
                    RedBlack a12 = a0.rightChild;
                    if(a12 == null)
                    {
                        break label2;
                    }
                    Integer a13 = new Integer(0);
                    com.cim.AIA.Node a14 = new com.cim.AIA.Node((Object)a13, -1);
                    int i8 = i0 / 4;
                    a14.setWidth(i8);
                    a14.setIsVisible(false);
                    com.cim.AIA.HierarchyTree a15 = new com.cim.AIA.HierarchyTree((com.cim.AIA.Tree)a10, a14);
                    a10.addChild((com.cim.AIA.Tree)a15);
                    break label3;
                }
                RedBlack a16 = a0.leftChild;
                if(a16 != null)
                {
                    RedBlack a17 = a0.leftChild;
                    int i9 = i + 1;
                    com.cim.AIA.HierarchyTree a18 = this.getTuteTree(a10, a17, a1, i9, i0);
                    a10.addChild((com.cim.AIA.Tree)a18);
                }
            }
            RedBlack a19 = a0.rightChild;
            label5: {
                label4: {
                    if(a19 != null)
                    {
                        break label4;
                    }
                    RedBlack a20 = a0.leftChild;
                    if(a20 == null)
                    {
                        break label4;
                    }
                    Integer a21 = new Integer(0);
                    com.cim.AIA.Node a22 = new com.cim.AIA.Node((Object)a21, -1);
                    int i10 = i0 / 4;
                    a22.setWidth(i10);
                    a22.setIsVisible(false);
                    com.cim.AIA.HierarchyTree a23 = new com.cim.AIA.HierarchyTree((com.cim.AIA.Tree)a10, a22);
                    a10.addChild((com.cim.AIA.Tree)a23);
                    break label5;
                }
                RedBlack a24 = a0.rightChild;
                if(a24 != null)
                {
                    RedBlack a25 = a0.rightChild;
                    int i11 = i + 1;
                    com.cim.AIA.HierarchyTree a26 = this.getTuteTree(a10, a25, a1, i11, i0);
                    a10.addChild((com.cim.AIA.Tree)a26);
                }
            }
            RedBlack a27 = a0.rightChild;
            label6: {
                if(a27 != null)
                {
                    break label6;
                }
                RedBlack a28 = a0.leftChild;
                if(a28 == null)
                {
                    Integer a29 = new Integer(0);
                    com.cim.AIA.Node a30 = new com.cim.AIA.Node((Object)a29, -1);
                    Integer a31 = new Integer(0);
                    com.cim.AIA.Node a32 = new com.cim.AIA.Node((Object)a31, -1);
                    int i12 = i0 / 4;
                    a30.setWidth(i12);
                    int i13 = i0 / 4;
                    a32.setWidth(i13);
                    a30.setIsVisible(false);
                    a32.setIsVisible(false);
                    com.cim.AIA.HierarchyTree a33 = new com.cim.AIA.HierarchyTree((com.cim.AIA.Tree)a10, a30);
                    a10.addChild((com.cim.AIA.Tree)a33);
                    com.cim.AIA.HierarchyTree a34 = new com.cim.AIA.HierarchyTree((com.cim.AIA.Tree)a10, a32);
                    a10.addChild((com.cim.AIA.Tree)a34);
                }
            }
            label7: {
                if(a == null)
                {
                    break label7;
                }
                int i14 = a0.getHighLightParent()?1:0;
                if(i14 == 0)
                {
                    java.awt.Color a35 = java.awt.Color.black;
                    a10.setParentLineColor(a35);
                }
                else
                {
                    java.awt.Color a36 = RedBlackTree.tuteHighLightColor;
                    a10.setParentLineColor(a36);
                }
            }
            a2 = a10;
        }
        return a2;
    }
    
    public com.cim.AIA.HierarchyTree getTuteTree(int i)
    {
        com.cim.AIA.HierarchyTree a = this.tuteHTree;
        label1: {
            label0: {
                if(a == null)
                {
                    break label0;
                }
                int i0 = this.noHTreeUpdate?1:0;
                if(i0 != 0)
                {
                    break label1;
                }
            }
            RedBlack a0 = this.tuteTree;
            RedBlack a1 = this.tuteTree;
            com.cim.AIA.HierarchyTree a2 = this.getTuteTree((com.cim.AIA.HierarchyTree)null, a0, a1, 0, i);
            this.tuteHTree = a2;
        }
        com.cim.AIA.HierarchyTree a3 = this.tuteHTree;
        return a3;
    }
    
    protected boolean hasQuestions()
    {
        return false;
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
            RedBlack a = this.baseTree;
            if(a == null)
            {
                this.tree = null;
            }
            else
            {
                RedBlack a0 = this.baseTree;
                RedBlack a1 = a0.copy();
                this.tree = a1;
            }
        }
        java.util.Vector a2 = new java.util.Vector();
        this.insertRequests = a2;
        java.util.Vector a3 = new java.util.Vector();
        this.deleteRequests = a3;
        java.util.Vector a4 = new java.util.Vector();
        this.lineMoveRequests = a4;
        java.util.Vector a5 = new java.util.Vector();
        this.deleter = a5;
        this.ptrNode = null;
        this.greatGrandParentNode = null;
        this.grandParentNode = null;
        this.parentNode = null;
        this.position = -1;
        int i1 = this.basePosition;
        RedBlack.POSITION = i1;
    }
    
    protected void initialiseMethods(String s, com.cim.AIA.MethodSelectable a)
    {
        Object a0 = a;
        String s0 = RedBlackTree.BUILD_MODE_LABEL;
        String s1 = localization.Messages.getString("RedBlackTree.91");
        com.cim.AIA.Logger a1 = this.getLogger();
        com.cim.AIA.BreakPoint a2 = this.getBreakPoint();
        com.cim.AIA.MethodSelection a3 = new com.cim.AIA.MethodSelection(s0, s, s1, "2.2a", a1, a2);
        String s2 = RedBlackTree.SEARCH_MODE_LABEL;
        String s3 = localization.Messages.getString("RedBlackTree.92");
        com.cim.AIA.Logger a4 = this.getLogger();
        com.cim.AIA.BreakPoint a5 = this.getBreakPoint();
        com.cim.AIA.MethodSelection a6 = new com.cim.AIA.MethodSelection(s2, s, s3, "4", a4, a5);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelection(a3, true);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelection(a6, false);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelectionListener((com.cim.AIA.MethodSelectionListener)this);
    }
    
    public void insert(int i)
    {
        RedBlack a = this.tree;
        label3: {
            label1: {
                label0: {
                    if(a == null)
                    {
                        break label0;
                    }
                    RedBlack a0 = this.tree;
                    a0.insert(i, this);
                    break label1;
                }
                this.setPosition("3");
                this.setPosition("3.1");
                this.setPosition("3.1.1");
                int i0 = this.enabled?1:0;
                label2: {
                    if(i0 != 0)
                    {
                        break label2;
                    }
                    break label3;
                }
                RedBlack a1 = new RedBlack(i, (RedBlack)null, (RedBlack)null, false);
                this.tree = a1;
                this.setPosition("3.3");
                this.setPosition("3.3.4");
                this.setPosition("3.4");
                this.setPosition("3.4.4");
                this.setPosition("3.5");
            }
            this.resetMarkers();
        }
    }
    
    public void leftRotateRequest()
    {
        RedBlack a = this.tuteTree;
        label1: {
            label0: {
                if(a != null)
                {
                    break label0;
                }
                break label1;
            }
            int i = this.enabled?1:0;
            label2: {
                if(i != 0)
                {
                    break label2;
                }
                break label1;
            }
            this.doRightRotate = false;
            this.noHTreeUpdate = true;
            com.cim.common.MessageDialog a0 = this.messageDialog;
            if(a0 != null)
            {
                com.cim.common.MessageDialog a1 = this.messageDialog;
                a1.dispose();
            }
            String s = localization.Messages.getString("RedBlackTree.10");
            com.cim.common.MessageDialog a2 = new com.cim.common.MessageDialog(s, false, false);
            this.messageDialog = a2;
            com.cim.common.MessageDialog a3 = this.messageDialog;
            String s0 = localization.Messages.getString("RedBlackTree.11");
            a3.setTitle(s0);
            com.cim.common.MessageDialog a4 = this.messageDialog;
            a4.setVisible(true);
            NodeSelection a5 = NodeSelection.getInstance(this);
            NodeSelection.setEnabled(true);
        }
    }
    
    public void processMethodSelectionEvent(com.cim.AIA.MethodSelectionEvent a)
    {
        String s = RedBlackTree.BUILD_MODE_LABEL;
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
            String s1 = RedBlackTree.SEARCH_MODE_LABEL;
            String s2 = a.getMethodName();
            int i0 = s1.compareTo(s2);
            if(i0 == 0)
            {
                this.nextMode = 12;
            }
        }
    }
    
    public void processNodeSelection()
    {
        com.cim.common.MessageDialog a = this.messageDialog;
        if(a != null)
        {
            com.cim.common.MessageDialog a0 = this.messageDialog;
            a0.setVisible(false);
            com.cim.common.MessageDialog a1 = this.messageDialog;
            a1.dispose();
        }
        com.cim.AIA.Node a2 = NodeSelection.getNode();
        this.findNodeParent = null;
        this.findNodePtr = null;
        RedBlack a3 = this.tuteTree;
        com.cim.AIA.HierarchyTree a4 = this.tuteHTree;
        RedBlack a5 = this.findNode(a3, a4, a2);
        NodeSelection.setEnabled(false);
        this.noHTreeUpdate = false;
        RedBlack a6 = this.findNodePtr;
        int i = this.doRightRotate?1:0;
        label2: {
            label0: {
                if(i != 0)
                {
                    break label0;
                }
                RedBlack a7 = a6.rightChild;
                label1: {
                    if(a7 != null)
                    {
                        break label1;
                    }
                    String s = localization.Messages.getString("RedBlackTree.4");
                    com.cim.common.MessageDialog a8 = new com.cim.common.MessageDialog(s, true, true);
                    String s0 = localization.Messages.getString("RedBlackTree.5");
                    a8.setTitle(s0);
                    a8.setVisible(true);
                    this.noHTreeUpdate = false;
                    break label2;
                }
                a6.setHighLightParent(true);
                RedBlack a9 = a6.rightChild;
                a9.setHighLightParent(true);
                RedBlack a10 = a6.rightChild;
                RedBlack a11 = a10.leftChild;
                if(a11 != null)
                {
                    RedBlack a12 = a6.rightChild;
                    RedBlack a13 = a12.leftChild;
                    a13.setHighLightParent(true);
                }
                RotateAnimation a14 = this.animThread;
                com.cim.AIA.AlgorithmThread a15 = this.theThread;
                RedBlackTreeThread dummy = (RedBlackTreeThread)a15;
                RedBlackTreeThread a16 = (RedBlackTreeThread)a15;
                java.util.Vector a17 = this.lineMoveRequests;
                RedBlack a18 = this.tuteTree;
                RedBlack a19 = this.findNodePtr;
                RedBlack a20 = this.findNodeParent;
                a14.initialise(a16, this, a17, a18, a19, a20);
                RotateAnimation a21 = this.animThread;
                a21.rotateLeft();
                RotateAnimation a22 = this.animThread;
                a22.setEnabled(true);
            }
            int i0 = this.doRightRotate?1:0;
            label4: {
                label3: {
                    if(i0 == 0)
                    {
                        break label3;
                    }
                    RedBlack a23 = a6.leftChild;
                    if(a23 == null)
                    {
                        break label4;
                    }
                    a6.setHighLightParent(true);
                    RedBlack a24 = a6.leftChild;
                    a24.setHighLightParent(true);
                    RedBlack a25 = a6.leftChild;
                    RedBlack a26 = a25.rightChild;
                    if(a26 != null)
                    {
                        RedBlack a27 = a6.leftChild;
                        RedBlack a28 = a27.rightChild;
                        a28.setHighLightParent(true);
                    }
                    RotateAnimation a29 = this.animThread;
                    com.cim.AIA.AlgorithmThread a30 = this.theThread;
                    RedBlackTreeThread dummy0 = (RedBlackTreeThread)a30;
                    RedBlackTreeThread a31 = (RedBlackTreeThread)a30;
                    java.util.Vector a32 = this.lineMoveRequests;
                    RedBlack a33 = this.tuteTree;
                    RedBlack a34 = this.findNodePtr;
                    RedBlack a35 = this.findNodeParent;
                    a29.initialise(a31, this, a32, a33, a34, a35);
                    RotateAnimation a36 = this.animThread;
                    a36.rotateRight();
                    RotateAnimation a37 = this.animThread;
                    a37.setEnabled(true);
                }
                break label2;
            }
            String s1 = localization.Messages.getString("RedBlackTree.6");
            com.cim.common.MessageDialog a38 = new com.cim.common.MessageDialog(s1, true, true);
            String s2 = localization.Messages.getString("RedBlackTree.7");
            a38.setTitle(s2);
            a38.setVisible(true);
            this.noHTreeUpdate = false;
        }
    }
    
    public void reInitialise(Object a)
    {
        int[] dummy = (int[])a;
        int[] a0 = (int[])a;
        this.data = a0;
        this.initialise();
    }
    
    protected void removeAllAnimationRequests()
    {
        java.util.Vector a = new java.util.Vector();
        this.insertRequests = a;
        java.util.Vector a0 = new java.util.Vector();
        this.deleteRequests = a0;
        RedBlack a1 = this.tree;
        this.removeRedBlackTreeDeletes(a1);
        RedBlack a2 = this.tree;
        this.removeRedBlackTreeInserts(a2);
        this.growNode = null;
        this.animationNode = null;
    }
    
    protected void removeAllQuestions()
    {
    }
    
    protected void removeRedBlackTreeDeletes(RedBlack a)
    {
        label0: {
            if(a == null)
            {
                break label0;
            }
            a.deleteNode = false;
            RedBlack.deletePtr = -1;
            RedBlack a0 = a.getLeftChild();
            if(a0 != null)
            {
                RedBlack a1 = a.getLeftChild();
                this.removeRedBlackTreeDeletes(a1);
            }
            RedBlack a2 = a.getRightChild();
            if(a2 != null)
            {
                RedBlack a3 = a.getRightChild();
                this.removeRedBlackTreeDeletes(a3);
            }
        }
    }
    
    protected void removeRedBlackTreeInserts(RedBlack a)
    {
        label0: {
            if(a == null)
            {
                break label0;
            }
            a.newNode = false;
            RedBlack a0 = a.getLeftChild();
            if(a0 != null)
            {
                RedBlack a1 = a.getLeftChild();
                this.removeRedBlackTreeInserts(a1);
            }
            RedBlack a2 = a.getRightChild();
            if(a2 != null)
            {
                RedBlack a3 = a.getRightChild();
                this.removeRedBlackTreeInserts(a3);
            }
        }
    }
    
    public void resetAllHighLightParent(RedBlack a)
    {
        if(a != null)
        {
            a.setHighLightParent(false);
            RedBlack a0 = a.leftChild;
            this.resetAllHighLightParent(a0);
            RedBlack a1 = a.rightChild;
            this.resetAllHighLightParent(a1);
        }
    }
    
    protected void resetMarkers()
    {
        this.ptrNode = null;
        this.greatGrandParentNode = null;
        this.grandParentNode = null;
        this.parentNode = null;
        RedBlack a = this.tree;
        if(a != null)
        {
            RedBlack a0 = this.tree;
            a0.resetMarkers();
        }
    }
    
    public void rightRotateRequest()
    {
        RedBlack a = this.tuteTree;
        label1: {
            label0: {
                if(a != null)
                {
                    break label0;
                }
                break label1;
            }
            int i = this.enabled?1:0;
            label2: {
                if(i != 0)
                {
                    break label2;
                }
                break label1;
            }
            this.doRightRotate = true;
            this.noHTreeUpdate = true;
            com.cim.common.MessageDialog a0 = this.messageDialog;
            if(a0 != null)
            {
                com.cim.common.MessageDialog a1 = this.messageDialog;
                a1.dispose();
            }
            String s = localization.Messages.getString("RedBlackTree.8");
            com.cim.common.MessageDialog a2 = new com.cim.common.MessageDialog(s, false, false);
            this.messageDialog = a2;
            com.cim.common.MessageDialog a3 = this.messageDialog;
            String s0 = localization.Messages.getString("RedBlackTree.9");
            a3.setTitle(s0);
            com.cim.common.MessageDialog a4 = this.messageDialog;
            a4.setVisible(true);
            NodeSelection a5 = NodeSelection.getInstance(this);
            NodeSelection.setEnabled(true);
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
    
    private void save()
    {
        RedBlack a = this.tree;
        if(a == null)
        {
            this.baseTree = null;
        }
        else
        {
            RedBlack a0 = this.tree;
            RedBlack a1 = a0.copy();
            this.baseTree = a1;
        }
        int i = RedBlack.POSITION;
        this.basePosition = i;
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
            RedBlack a4 = this.tree;
            if(a4 == null)
            {
                boolean[] a5 = this.searchResults;
                a5[i3] = false;
            }
            else
            {
                boolean[] a6 = this.searchResults;
                RedBlack a7 = this.tree;
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
    
    public void setAnimDuration(int i)
    {
        RotateAnimation a = this.animThread;
        a.setDuration(i);
    }
    
    public void setCodePosition(String s)
    {
        this.setPosition(s);
    }
    
    public void setRemoveSaveLine(boolean b)
    {
        this.removeSaveLine = b;
    }
    
    public void setTuteTree(RedBlack a)
    {
        this.tuteTree = a;
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
    
    public void updateTuteTree()
    {
        this.noHTreeUpdate = false;
        RedBlack a = this.tree;
        if(a == null)
        {
            this.tuteTree = null;
        }
        else
        {
            RedBlack a0 = this.tree;
            RedBlack a1 = a0.copy();
            this.tuteTree = a1;
        }
    }
    
    static
    {
        java.awt.Color a = java.awt.Color.lightGray;
        RedBlackTree.tuteNodeColor = a;
        java.awt.Color a0 = java.awt.Color.lightGray;
        RedBlackTree.redBlackTreeNodeColor = a0;
        java.awt.Color a1 = java.awt.Color.red;
        RedBlackTree.tuteHighLightColor = a1;
        java.awt.Color a2 = java.awt.Color.yellow;
        RedBlackTree.tuteSelectColor = a2;
        RedBlackTree.POS = 0;
        String s = localization.Messages.getString("RedBlackTree.0");
        RedBlackTree.BUILD_MODE_LABEL = s;
        String s0 = localization.Messages.getString("RedBlackTree.1");
        RedBlackTree.SEARCH_MODE_LABEL = s0;
    }
}