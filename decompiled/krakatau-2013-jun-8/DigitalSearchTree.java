public class DigitalSearchTree extends com.cim.AIA.Algorithm implements com.cim.AIA.MethodSelectionListener, com.cim.AIA.ControlListener {
    private int[] insertData;
    private int[] newInsertData;
    private int insertPos;
    private int[] searchData;
    private int[] newSearchData;
    private int mostSignificantBit;
    private java.awt.Color[] bitDataColor;
    private java.awt.Color[] insertDataColor;
    private java.awt.Color[] searchDataColor;
    private com.cim.AIA.ElementArray bitArray;
    private com.cim.AIA.ElementArray searchArray;
    private com.cim.AIA.ElementArray insertArray;
    private boolean isBackMode;
    final private static int BUILD_MODE = 11;
    final private static int SEARCH_MODE = 12;
    private boolean canStoreState;
    final private static java.awt.Color TEXT_COLOR;
    final private static java.awt.Color INSERT_HIGHLIGHT_COLOR;
    final private static java.awt.Color INSERT_ACTIVE_COLOR;
    final private static java.awt.Color INSERT_DONE_COLOR;
    final private static java.awt.Color BIT_INSERT_HIGHLIGHT_COLOR;
    final private static java.awt.Color BIT_INSERT_ACTIVE_COLOR;
    final private static java.awt.Color SEARCH_HIGHLIGHT_COLOR;
    final private static java.awt.Color SEARCH_ACTIVE_COLOR;
    final private static java.awt.Color SEARCH_DONE_COLOR;
    final private static java.awt.Color BIT_SEARCH_HIGHLIGHT_COLOR;
    final private static java.awt.Color BIT_SEARCH_ACTIVE_COLOR;
    final private static java.awt.Color TREE_HIGHLIGHT_COLOR;
    final private static java.awt.Color TREE_ACTIVE_COLOR;
    final private static java.awt.Color TREE_DONE_COLOR;
    final private static java.awt.Color TREE_RING_COLOR;
    final private static java.awt.Color TREE_NULL_COLOR;
    final private static java.awt.Color PATH_COLOR;
    private com.cim.AIA.Node parentNode;
    private com.cim.AIA.Node ptrNode;
    private com.cim.AIA.Node bitNode;
    private com.cim.AIA.Node searchNode;
    private com.cim.AIA.ExtendedHierarchyTree ptrTree;
    final private static String BUILD_MODE_LABEL;
    final private static String SEARCH_MODE_LABEL;
    final private static String DELETE_MODE_LABEL;
    final protected static String PARENT_LABEL;
    final protected static String BIT_LABEL;
    final protected static String PTR_LABEL;
    final protected static String INSERTION_LABEL;
    protected static String ptrLabel;
    private int executingMode;
    private int nextMode;
    private DigitalSearchTree$DigitalTree root;
    private DigitalSearchTree$DigitalTree savedTree;
    
    public DigitalSearchTree(com.cim.AIA.AlgorithmThread a, Object a0)
    {
        super(a, a0);
        this.mostSignificantBit = 0;
        this.isBackMode = false;
        this.canStoreState = false;
        this.savedTree = null;
        this.executingMode = 11;
        int i = this.executingMode;
        this.nextMode = i;
        int[] dummy = (int[])a0;
        int[] a1 = (int[])a0;
        this.insertData = a1;
        this.newInsertData = a1;
        java.util.Random a2 = new java.util.Random();
        int[] a3 = this.insertData;
        int i0 = a3.length;
        int[] a4 = new int[i0];
        this.searchData = a4;
        int i1 = 0;
        while(true)
        {
            int[] a5 = this.insertData;
            int i2 = a5.length;
            if(i1 >= i2)
            {
                int[] a6 = this.searchData;
                this.newSearchData = a6;
                this.root = null;
                this.initialise();
                return;
            }
            int i3 = i1 % 2;
            if(i3 != 0)
            {
                int[] a7 = this.searchData;
                int[] a8 = this.insertData;
                int i4 = a8[i1];
                a7[i1] = i4;
            }
            else
            {
                int[] a9 = this.searchData;
                int i5 = a2.nextInt();
                int i6 = Math.abs(i5);
                int i7 = i6 % 98;
                int i8 = i7 + 1;
                a9[i1] = i8;
            }
            int i9 = i1 + 1;
            i1 = i9;
        }
    }
    
    protected void setEnabled(boolean b)
    {
        this.enabled = b;
    }
    
    private void colorHierarchyTree(com.cim.AIA.ExtendedHierarchyTree a)
    {
        label0: {
            if(a == null)
            {
                break label0;
            }
            com.cim.AIA.Line a0 = a.getLine();
            a0.showAsThick(true);
            java.awt.Color a1 = DigitalSearchTree.PATH_COLOR;
            a.setParentLineColor(a1);
            java.util.Vector a2 = a.getNodes();
            int i = a2.size();
            if(i > 0)
            {
                java.util.Vector a3 = a.getNodes();
                Object a4 = a3.elementAt(0);
                com.cim.AIA.Node dummy = (com.cim.AIA.Node)a4;
                com.cim.AIA.Node a5 = (com.cim.AIA.Node)a4;
                java.awt.Color a6 = DigitalSearchTree.PATH_COLOR;
                a5.setTextColor(a6);
                java.util.Vector a7 = a.getNodes();
                Object a8 = a7.elementAt(0);
                com.cim.AIA.Node dummy0 = (com.cim.AIA.Node)a8;
                com.cim.AIA.Node a9 = (com.cim.AIA.Node)a8;
                java.awt.Color a10 = DigitalSearchTree.PATH_COLOR;
                a9.setRingColor(a10);
            }
            com.cim.AIA.Tree a11 = a.getParent();
            com.cim.AIA.ExtendedHierarchyTree dummy1 = (com.cim.AIA.ExtendedHierarchyTree)a11;
            com.cim.AIA.ExtendedHierarchyTree a12 = (com.cim.AIA.ExtendedHierarchyTree)a11;
            this.colorHierarchyTree(a12);
        }
    }
    
    private com.cim.AIA.ExtendedHierarchyTree makeHierarchyTree(com.cim.AIA.ExtendedHierarchyTree a, DigitalSearchTree$DigitalTree a0)
    {
        com.cim.AIA.ExtendedHierarchyTree a1 = null;
        label1: {
            label0: {
                if(a0 != null)
                {
                    break label0;
                }
                a1 = null;
                break label1;
            }
            com.cim.AIA.Node a2 = a0.node;
            com.cim.AIA.ExtendedHierarchyTree a3 = new com.cim.AIA.ExtendedHierarchyTree((com.cim.AIA.Tree)a, a2);
            com.cim.AIA.Node a4 = a0.node;
            com.cim.AIA.Node a5 = this.ptrNode;
            if(a4 == a5)
            {
                this.ptrTree = a3;
            }
            DigitalSearchTree$DigitalTree a6 = a0.leftChild;
            label3: {
                label2: {
                    if(a6 == null)
                    {
                        break label2;
                    }
                    DigitalSearchTree$DigitalTree a7 = a0.leftChild;
                    com.cim.AIA.ExtendedHierarchyTree a8 = this.makeHierarchyTree(a3, a7);
                    a3.addChild((com.cim.AIA.Tree)a8);
                    break label3;
                }
                com.cim.AIA.Node a9 = a0.leftNode;
                com.cim.AIA.ExtendedHierarchyTree a10 = new com.cim.AIA.ExtendedHierarchyTree((com.cim.AIA.Tree)a3, a9);
                a3.addChild((com.cim.AIA.Tree)a10);
                com.cim.AIA.Node a11 = a0.leftNode;
                com.cim.AIA.Node a12 = this.ptrNode;
                if(a11 == a12)
                {
                    this.ptrTree = a10;
                }
            }
            DigitalSearchTree$DigitalTree a13 = a0.rightChild;
            label5: {
                label4: {
                    if(a13 == null)
                    {
                        break label4;
                    }
                    DigitalSearchTree$DigitalTree a14 = a0.rightChild;
                    com.cim.AIA.ExtendedHierarchyTree a15 = this.makeHierarchyTree(a3, a14);
                    a3.addChild((com.cim.AIA.Tree)a15);
                    break label5;
                }
                com.cim.AIA.Node a16 = a0.rightNode;
                com.cim.AIA.ExtendedHierarchyTree a17 = new com.cim.AIA.ExtendedHierarchyTree((com.cim.AIA.Tree)a3, a16);
                a3.addChild((com.cim.AIA.Tree)a17);
                com.cim.AIA.Node a18 = a0.rightNode;
                com.cim.AIA.Node a19 = this.ptrNode;
                if(a18 == a19)
                {
                    this.ptrTree = a17;
                }
            }
            a1 = a3;
        }
        return a1;
    }
    
    private com.cim.AIA.ExtendedHierarchyTree buildHierarchyTree(DigitalSearchTree$DigitalTree a, int i)
    {
        com.cim.AIA.ExtendedHierarchyTree a0 = null;
        label0: {
            com.cim.AIA.ExtendedHierarchyTree a1 = null;
            if(a == null)
            {
                a0 = a1;
                break label0;
            }
            com.cim.AIA.ExtendedHierarchyTree a2 = new com.cim.AIA.ExtendedHierarchyTree();
            com.cim.AIA.Node a3 = a.node;
            a2.add(a3);
            com.cim.AIA.Node a4 = a.node;
            a2.add(a4);
            java.awt.Color a5 = java.awt.Color.white;
            a2.setBorderColor(a5);
            com.cim.AIA.Node a6 = a.node;
            com.cim.AIA.Node a7 = this.ptrNode;
            if(a6 == a7)
            {
                this.ptrTree = a2;
            }
            DigitalSearchTree$DigitalTree a8 = a.leftChild;
            label2: {
                label1: {
                    if(a8 == null)
                    {
                        break label1;
                    }
                    DigitalSearchTree$DigitalTree a9 = a.leftChild;
                    com.cim.AIA.ExtendedHierarchyTree a10 = this.buildHierarchyTree(a9, 0);
                    a2.addChild((com.cim.AIA.Tree)a10);
                    break label2;
                }
                com.cim.AIA.Node a11 = a.leftNode;
                com.cim.AIA.ExtendedHierarchyTree a12 = new com.cim.AIA.ExtendedHierarchyTree(a11);
                com.cim.AIA.ExtendedHierarchyTree a13 = new com.cim.AIA.ExtendedHierarchyTree();
                com.cim.AIA.Node a14 = a.leftNode;
                a13.add(a14);
                a2.addChild((com.cim.AIA.Tree)a13);
                com.cim.AIA.Node a15 = a.leftNode;
                com.cim.AIA.Node a16 = this.ptrNode;
                if(a15 == a16)
                {
                    this.ptrTree = a13;
                }
            }
            DigitalSearchTree$DigitalTree a17 = a.rightChild;
            label3: {
                if(a17 == null)
                {
                    break label3;
                }
                DigitalSearchTree$DigitalTree a18 = a.rightChild;
                com.cim.AIA.ExtendedHierarchyTree a19 = this.buildHierarchyTree(a18, 0);
                a2.addChild((com.cim.AIA.Tree)a19);
                a0 = a2;
                break label0;
            }
            com.cim.AIA.Node a20 = a.rightNode;
            com.cim.AIA.ExtendedHierarchyTree a21 = new com.cim.AIA.ExtendedHierarchyTree(a20);
            com.cim.AIA.ExtendedHierarchyTree a22 = new com.cim.AIA.ExtendedHierarchyTree();
            com.cim.AIA.Node a23 = a.rightNode;
            a22.add(a23);
            a2.addChild((com.cim.AIA.Tree)a22);
            com.cim.AIA.Node a24 = a.rightNode;
            com.cim.AIA.Node a25 = this.ptrNode;
            if(a24 == a25)
            {
                this.ptrTree = a22;
            }
            a2.addChild((com.cim.AIA.Tree)a22);
            a0 = a2;
        }
        return a0;
    }
    
    public com.cim.AIA.MultipleTween generateTweens(com.cim.AIA.Paintable a, Object a0, int i)
    {
        return null;
    }
    
    public com.cim.AIA.ExtendedHierarchyTree getHierarchyTree()
    {
        this.ptrTree = null;
        DigitalSearchTree$DigitalTree a = this.root;
        com.cim.AIA.ExtendedHierarchyTree a0 = this.buildHierarchyTree(a, 0);
        com.cim.AIA.ExtendedHierarchyTree a1 = new com.cim.AIA.ExtendedHierarchyTree();
        DigitalSearchTree$DigitalTree a2 = this.root;
        com.cim.AIA.ExtendedHierarchyTree a3 = this.makeHierarchyTree(a1, a2);
        com.cim.AIA.ExtendedHierarchyTree a4 = this.ptrTree;
        this.colorHierarchyTree(a4);
        return a3;
    }
    
    public com.cim.AIA.ElementArray getInsertElementArray()
    {
        com.cim.AIA.ElementArray a = this.insertArray;
        label0: {
            if(a == null)
            {
                break label0;
            }
            int i = 0;
            while(true)
            {
                com.cim.AIA.ElementArray a0 = this.insertArray;
                int i0 = a0.getSize();
                if(i >= i0)
                {
                    break;
                }
                else
                {
                    com.cim.AIA.ElementArray a1 = this.insertArray;
                    com.cim.AIA.Element a2 = a1.getElement(i);
                    com.cim.AIA.Node dummy = (com.cim.AIA.Node)a2;
                    com.cim.AIA.Node a3 = (com.cim.AIA.Node)a2;
                    java.awt.Color[] a4 = this.insertDataColor;
                    java.awt.Color a5 = a4[i];
                    a3.setBackgroundColor(a5);
                    com.cim.AIA.ElementArray a6 = this.insertArray;
                    com.cim.AIA.Element a7 = a6.getElement(i);
                    com.cim.AIA.Node dummy0 = (com.cim.AIA.Node)a7;
                    com.cim.AIA.Node a8 = (com.cim.AIA.Node)a7;
                    java.awt.Color a9 = DigitalSearchTree.INSERT_HIGHLIGHT_COLOR;
                    a8.setHighlightColor(a9);
                    com.cim.AIA.ElementArray a10 = this.insertArray;
                    com.cim.AIA.Element a11 = a10.getElement(i);
                    com.cim.AIA.Node dummy1 = (com.cim.AIA.Node)a11;
                    com.cim.AIA.Node a12 = (com.cim.AIA.Node)a11;
                    java.awt.Color a13 = DigitalSearchTree.TEXT_COLOR;
                    a12.setTextColor(a13);
                    int i1 = i + 1;
                    i = i1;
                }
            }
        }
        com.cim.AIA.ElementArray a14 = this.insertArray;
        return a14;
    }
    
    public com.cim.AIA.ElementArray getSearchElementArray()
    {
        com.cim.AIA.ElementArray a = this.searchArray;
        label0: {
            if(a == null)
            {
                break label0;
            }
            int i = 0;
            while(true)
            {
                com.cim.AIA.ElementArray a0 = this.searchArray;
                int i0 = a0.getSize();
                if(i >= i0)
                {
                    break;
                }
                else
                {
                    com.cim.AIA.ElementArray a1 = this.searchArray;
                    com.cim.AIA.Element a2 = a1.getElement(i);
                    com.cim.AIA.Node dummy = (com.cim.AIA.Node)a2;
                    com.cim.AIA.Node a3 = (com.cim.AIA.Node)a2;
                    java.awt.Color[] a4 = this.searchDataColor;
                    java.awt.Color a5 = a4[i];
                    a3.setBackgroundColor(a5);
                    com.cim.AIA.ElementArray a6 = this.searchArray;
                    com.cim.AIA.Element a7 = a6.getElement(i);
                    com.cim.AIA.Node dummy0 = (com.cim.AIA.Node)a7;
                    com.cim.AIA.Node a8 = (com.cim.AIA.Node)a7;
                    java.awt.Color a9 = DigitalSearchTree.SEARCH_HIGHLIGHT_COLOR;
                    a8.setHighlightColor(a9);
                    com.cim.AIA.ElementArray a10 = this.searchArray;
                    com.cim.AIA.Element a11 = a10.getElement(i);
                    com.cim.AIA.Node dummy1 = (com.cim.AIA.Node)a11;
                    com.cim.AIA.Node a12 = (com.cim.AIA.Node)a11;
                    java.awt.Color a13 = DigitalSearchTree.TEXT_COLOR;
                    a12.setTextColor(a13);
                    int i1 = i + 1;
                    i = i1;
                }
            }
        }
        com.cim.AIA.ElementArray a14 = this.searchArray;
        return a14;
    }
    
    public com.cim.AIA.ElementArray getBitElementArray()
    {
        com.cim.AIA.ElementArray a = this.bitArray;
        label0: {
            if(a == null)
            {
                break label0;
            }
            int i = 0;
            while(true)
            {
                com.cim.AIA.ElementArray a0 = this.bitArray;
                int i0 = a0.getSize();
                if(i >= i0)
                {
                    break;
                }
                else
                {
                    com.cim.AIA.ElementArray a1 = this.bitArray;
                    com.cim.AIA.Element a2 = a1.getElement(i);
                    com.cim.AIA.Node dummy = (com.cim.AIA.Node)a2;
                    com.cim.AIA.Node a3 = (com.cim.AIA.Node)a2;
                    java.awt.Color[] a4 = this.bitDataColor;
                    com.cim.AIA.ElementArray a5 = this.bitArray;
                    int i1 = a5.getSize();
                    int i2 = i1 - i;
                    int i3 = i2 - 1;
                    java.awt.Color a6 = a4[i3];
                    a3.setBackgroundColor(a6);
                    com.cim.AIA.ElementArray a7 = this.bitArray;
                    com.cim.AIA.Element a8 = a7.getElement(i);
                    com.cim.AIA.Node dummy0 = (com.cim.AIA.Node)a8;
                    com.cim.AIA.Node a9 = (com.cim.AIA.Node)a8;
                    java.awt.Color a10 = DigitalSearchTree.TEXT_COLOR;
                    a9.setTextColor(a10);
                    int i4 = i + 1;
                    i = i4;
                }
            }
        }
        com.cim.AIA.ElementArray a11 = this.bitArray;
        return a11;
    }
    
    public com.cim.AIA.Node getParentNode()
    {
        com.cim.AIA.Node a = this.parentNode;
        return a;
    }
    
    public com.cim.AIA.Node getPtrNode()
    {
        com.cim.AIA.Node a = this.ptrNode;
        return a;
    }
    
    public com.cim.AIA.Node getBitNode()
    {
        return null;
    }
    
    public com.cim.AIA.Node getHighlightedNode()
    {
        com.cim.AIA.Node a = null;
        com.cim.AIA.ElementArray a0 = this.insertArray;
        label1: {
            label0: {
                com.cim.AIA.Node a1 = null;
                if(a0 == null)
                {
                    break label0;
                }
                int i = this.executingMode;
                if(i != 11)
                {
                    break label0;
                }
                com.cim.AIA.Node a2 = null;
                int i0 = 0;
                while(true)
                {
                    com.cim.AIA.Node a3 = null;
                    com.cim.AIA.Node a4 = null;
                    com.cim.AIA.ElementArray a5 = this.insertArray;
                    com.cim.AIA.Node a6 = a2;
                    int i1 = a5.getSize();
                    a1 = a6;
                    com.cim.AIA.Node a7 = a1;
                    if(i0 >= i1)
                    {
                        break;
                    }
                    else
                    {
                        a3 = a7;
                    }
                    com.cim.AIA.ElementArray a8 = this.insertArray;
                    com.cim.AIA.Node a9 = a3;
                    com.cim.AIA.Element a10 = a8.getElement(i0);
                    com.cim.AIA.Node a11 = a9;
                    com.cim.AIA.Node dummy = (com.cim.AIA.Node)a10;
                    com.cim.AIA.Node a12 = (com.cim.AIA.Node)a10;
                    com.cim.AIA.Node a13 = a11;
                    java.awt.Color a14 = a12.getBackgroundColor();
                    com.cim.AIA.Node a15 = a13;
                    java.awt.Color a16 = DigitalSearchTree.INSERT_HIGHLIGHT_COLOR;
                    com.cim.AIA.Node a17 = a15;
                    com.cim.AIA.Node a18 = a17;
                    if(a14 != a16)
                    {
                        a4 = a18;
                    }
                    else
                    {
                        com.cim.AIA.ElementArray a19 = this.insertArray;
                        com.cim.AIA.Element a20 = a19.getElement(i0);
                        com.cim.AIA.Node dummy0 = (com.cim.AIA.Node)a20;
                        com.cim.AIA.Node a21 = (com.cim.AIA.Node)a20;
                        a4 = a21;
                    }
                    int i2 = i0 + 1;
                    a2 = a4;
                    i0 = i2;
                    continue;
                }
                a = a1;
                break label1;
            }
            com.cim.AIA.ElementArray a22 = this.searchArray;
            com.cim.AIA.Node a23 = null;
            if(a22 == null)
            {
                a = a23;
                break label1;
            }
            int i3 = this.executingMode;
            com.cim.AIA.Node a24 = null;
            if(i3 != 12)
            {
                a = a24;
            }
            else
            {
                com.cim.AIA.Node a25 = this.searchNode;
                a = a25;
            }
        }
        return a;
    }
    
    public boolean isBuildMode()
    {
        int i = this.executingMode;
        int i0 = i != 11?0:1;
        return i0 != 0;
    }
    
    public boolean isSearchMode()
    {
        int i = this.executingMode;
        int i0 = i != 12?0:1;
        return i0 != 0;
    }
    
    protected boolean hasQuestions()
    {
        return false;
    }
    
    protected java.util.Vector generateQuestions()
    {
        return null;
    }
    
    private void initialise()
    {
        this.parentNode = null;
        this.ptrNode = null;
        DigitalSearchTree$DigitalTree a = this.root;
        this.resetTreeColor(a);
    }
    
    protected void initialiseMethods(String s, com.cim.AIA.MethodSelectable a)
    {
        Object a0 = a;
        String s0 = DigitalSearchTree.BUILD_MODE_LABEL;
        String s1 = localization.Messages.getString("DigitalSearchTree.7");
        com.cim.AIA.Logger a1 = this.getLogger();
        com.cim.AIA.BreakPoint a2 = this.getBreakPoint();
        com.cim.AIA.MethodSelection a3 = new com.cim.AIA.MethodSelection(s0, s, s1, "3.1", a1, a2);
        String s2 = DigitalSearchTree.SEARCH_MODE_LABEL;
        String s3 = localization.Messages.getString("DigitalSearchTree.8");
        com.cim.AIA.Logger a4 = this.getLogger();
        com.cim.AIA.BreakPoint a5 = this.getBreakPoint();
        com.cim.AIA.MethodSelection a6 = new com.cim.AIA.MethodSelection(s2, s, s3, "5", a4, a5);
        String s4 = DigitalSearchTree.DELETE_MODE_LABEL;
        String s5 = localization.Messages.getString("DigitalSearchTree.9");
        com.cim.AIA.Logger a7 = this.getLogger();
        com.cim.AIA.BreakPoint a8 = this.getBreakPoint();
        com.cim.AIA.MethodSelection a9 = new com.cim.AIA.MethodSelection(s4, s, s5, "7", a7, a8);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelection(a3, true);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelection(a6, false);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelectionListener((com.cim.AIA.MethodSelectionListener)this);
    }
    
    public void processMethodSelectionEvent(com.cim.AIA.MethodSelectionEvent a)
    {
        String s = DigitalSearchTree.BUILD_MODE_LABEL;
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
            String s1 = DigitalSearchTree.SEARCH_MODE_LABEL;
            String s2 = a.getMethodName();
            int i0 = s1.compareTo(s2);
            if(i0 == 0)
            {
                this.nextMode = 12;
            }
        }
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
        DigitalSearchTree$DigitalTree a = this.root;
        this.savedTree = a;
    }
    
    protected void clearState()
    {
    }
    
    public void reInitialise(Object a)
    {
        int[] a0 = this.newInsertData;
        this.insertData = a0;
        int i = this.isBackMode?1:0;
        if(i == 0)
        {
            int[] a1 = this.newSearchData;
            this.searchData = a1;
        }
        int i0 = this.isBackMode?1:0;
        label0: {
            if(i0 == 0)
            {
                break label0;
            }
            int i1 = this.executingMode;
            if(i1 == 12)
            {
                com.cim.AIA.Node a2 = this.searchNode;
                a2.removeAllMarkers();
            }
        }
        this.mostSignificantBit = 0;
        int i2 = 0;
        while(true)
        {
            int[] a3 = this.insertData;
            int i3 = a3.length;
            if(i2 >= i3)
            {
                break;
            }
            int[] a4 = this.insertData;
            int i4 = a4[i2];
            String s = Integer.toBinaryString(i4);
            int i5 = this.mostSignificantBit;
            int i6 = s.length();
            if(i5 < i6)
            {
                int i7 = s.length();
                this.mostSignificantBit = i7;
            }
            int i8 = i2 + 1;
            i2 = i8;
        }
        int i9 = this.mostSignificantBit;
        int i10 = i9 - 1;
        this.mostSignificantBit = i10;
        int i11 = this.executingMode;
        if(i11 == 11)
        {
            this.savedTree = null;
        }
        DigitalSearchTree$DigitalTree a5 = this.savedTree;
        this.root = a5;
        this.insertPos = 0;
        int[] a6 = this.insertData;
        int i12 = a6.length;
        java.awt.Color[] a7 = new java.awt.Color[i12];
        this.insertDataColor = a7;
        int i13 = 0;
        while(true)
        {
            int[] a8 = this.insertData;
            int i14 = a8.length;
            if(i13 >= i14)
            {
                break;
            }
            else
            {
                java.awt.Color[] a9 = this.insertDataColor;
                java.awt.Color a10 = DigitalSearchTree.INSERT_ACTIVE_COLOR;
                a9[i13] = a10;
                int i15 = i13 + 1;
                i13 = i15;
            }
        }
        com.cim.AIA.ElementArray a11 = new com.cim.AIA.ElementArray(0, 0);
        this.insertArray = a11;
        com.cim.AIA.ElementArray a12 = this.insertArray;
        a12.setColumGap(0);
        com.cim.AIA.ElementArray a13 = this.insertArray;
        a13.setElementWidth(20);
        int i16 = 0;
        while(true)
        {
            int[] a14 = this.insertData;
            int i17 = a14.length;
            if(i16 >= i17)
            {
                break;
            }
            else
            {
                int[] a15 = this.insertData;
                int i18 = a15[i16];
                Integer a16 = new Integer(i18);
                com.cim.AIA.Node a17 = new com.cim.AIA.Node((Object)a16, i16);
                java.awt.Color[] a18 = this.insertDataColor;
                java.awt.Color a19 = a18[i16];
                a17.setBackgroundColor(a19);
                java.awt.Color a20 = DigitalSearchTree.INSERT_HIGHLIGHT_COLOR;
                a17.setHighlightColor(a20);
                java.awt.Color a21 = DigitalSearchTree.TEXT_COLOR;
                a17.setTextColor(a21);
                com.cim.AIA.ElementArray a22 = this.insertArray;
                a22.setValue(i16, (com.cim.AIA.Element)a17);
                int i19 = i16 + 1;
                i16 = i19;
            }
        }
        int i20 = this.isBackMode?1:0;
        label1: {
            if(i20 != 0)
            {
                break label1;
            }
            int[] a23 = this.searchData;
            int i21 = a23.length;
            java.awt.Color[] a24 = new java.awt.Color[i21];
            this.searchDataColor = a24;
            int i22 = 0;
            while(true)
            {
                int[] a25 = this.searchData;
                int i23 = a25.length;
                if(i22 >= i23)
                {
                    break;
                }
                else
                {
                    java.awt.Color[] a26 = this.searchDataColor;
                    java.awt.Color a27 = DigitalSearchTree.SEARCH_ACTIVE_COLOR;
                    a26[i22] = a27;
                    int i24 = i22 + 1;
                    i22 = i24;
                }
            }
            com.cim.AIA.ElementArray a28 = new com.cim.AIA.ElementArray(0, 0);
            this.searchArray = a28;
            com.cim.AIA.ElementArray a29 = this.searchArray;
            a29.setColumGap(0);
            com.cim.AIA.ElementArray a30 = this.searchArray;
            a30.setElementWidth(20);
            int i25 = 0;
            while(true)
            {
                int[] a31 = this.searchData;
                int i26 = a31.length;
                if(i25 >= i26)
                {
                    break;
                }
                else
                {
                    int[] a32 = this.searchData;
                    int i27 = a32[i25];
                    Integer a33 = new Integer(i27);
                    com.cim.AIA.Node a34 = new com.cim.AIA.Node((Object)a33, i25);
                    java.awt.Color[] a35 = this.searchDataColor;
                    java.awt.Color a36 = a35[i25];
                    a34.setBackgroundColor(a36);
                    java.awt.Color a37 = DigitalSearchTree.SEARCH_HIGHLIGHT_COLOR;
                    a34.setHighlightColor(a37);
                    java.awt.Color a38 = DigitalSearchTree.TEXT_COLOR;
                    a34.setTextColor(a38);
                    com.cim.AIA.ElementArray a39 = this.searchArray;
                    a39.setValue(i25, (com.cim.AIA.Element)a34);
                    int i28 = i25 + 1;
                    i25 = i28;
                }
            }
        }
        this.initialise();
    }
    
    protected void changeData(Object a)
    {
        int[] dummy = (int[])a;
        int[] a0 = (int[])a;
        this.newInsertData = a0;
        java.util.Random a1 = new java.util.Random();
        int[] a2 = this.insertData;
        int i = a2.length;
        int[] a3 = new int[i];
        this.newSearchData = a3;
        int i0 = 0;
        while(true)
        {
            int[] a4 = this.insertData;
            int i1 = a4.length;
            if(i0 >= i1)
            {
                this.save();
                this.initialise();
                return;
            }
            int i2 = i0 % 2;
            if(i2 != 0)
            {
                int[] a5 = this.newSearchData;
                int[] a6 = this.insertData;
                int i3 = a6[i0];
                a5[i0] = i3;
            }
            else
            {
                int[] a7 = this.newSearchData;
                int i4 = a1.nextInt();
                int i5 = Math.abs(i4);
                int i6 = i5 % 98;
                int i7 = i6 + 1;
                a7[i0] = i7;
            }
            int i8 = i0 + 1;
            i0 = i8;
        }
    }
    
    protected void removeAllAnimationRequests()
    {
    }
    
    protected void removeAllQuestions()
    {
    }
    
    protected void makeQuestion(int i)
    {
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
    
    protected void run()
    {
        int i = this.enabled?1:0;
        label1: {
            label0: {
                if(i != 0)
                {
                    break label0;
                }
                break label1;
            }
            int i0 = this.executingMode;
            label3: {
                label2: {
                    if(i0 != 11)
                    {
                        break label2;
                    }
                    this.insert();
                    break label3;
                }
                int i1 = this.executingMode;
                if(i1 != 12)
                {
                    break label3;
                }
                this.setPosition("5.0");
                while(true)
                {
                    int i2 = this.enabled?1:0;
                    if(i2 == 0)
                    {
                        break;
                    }
                    else
                    {
                        this.search();
                    }
                }
            }
        }
    }
    
    protected void search()
    {
        DigitalSearchTree$DigitalTree a = this.root;
        label3: {
            label6: {
                label5: {
                    label0: {
                        if(a == null)
                        {
                            break label0;
                        }
                        int i = this.mostSignificantBit;
                        int i0 = this.isBackMode?1:0;
                        label1: {
                            if(i0 != 0)
                            {
                                break label1;
                            }
                            int i1 = this.enabled?1:0;
                            label2: {
                                if(i1 != 0)
                                {
                                    break label2;
                                }
                                break label3;
                            }
                            com.cim.AIA.SearchSelection.setEnabled(true);
                            label4: {
                                while(true)
                                {
                                    com.cim.AIA.Node a0 = com.cim.AIA.SearchSelection.getNode();
                                    if(a0 != null)
                                    {
                                        break label4;
                                    }
                                    int i2 = this.enabled?1:0;
                                    if(i2 == 0)
                                    {
                                        break;
                                    }
                                    try
                                    {
                                        Thread.sleep(1000L);
                                        continue;
                                    }
                                    catch(Exception ignoredException)
                                    {
                                        continue;
                                    }
                                }
                                com.cim.AIA.SearchSelection.setEnabled(false);
                                break label3;
                            }
                            com.cim.AIA.Node a1 = com.cim.AIA.SearchSelection.getNode();
                            this.searchNode = a1;
                            com.cim.AIA.SearchSelection.setEnabled(false);
                            int i3 = this.enabled?1:0;
                            if(i3 != 0)
                            {
                                break label1;
                            }
                            break label3;
                        }
                        java.awt.Color[] a2 = this.searchDataColor;
                        com.cim.AIA.Node a3 = this.searchNode;
                        int i4 = a3.getIdentifier();
                        java.awt.Color a4 = DigitalSearchTree.SEARCH_HIGHLIGHT_COLOR;
                        a2[i4] = a4;
                        com.cim.AIA.Node a5 = this.searchNode;
                        String s = a5.getDisplayString();
                        Integer a6 = Integer.valueOf(s);
                        int i5 = a6.intValue();
                        int i6 = this.mostSignificantBit;
                        int i7 = i6 + 1;
                        java.awt.Color[] a7 = new java.awt.Color[i7];
                        this.bitDataColor = a7;
                        com.cim.AIA.ElementArray a8 = new com.cim.AIA.ElementArray(0, 0);
                        this.bitArray = a8;
                        com.cim.AIA.ElementArray a9 = this.bitArray;
                        a9.setColumGap(0);
                        com.cim.AIA.ElementArray a10 = this.bitArray;
                        a10.setElementWidth(20);
                        int i8 = this.mostSignificantBit;
                        int i9 = i8;
                        while(true)
                        {
                            if(i9 < 0)
                            {
                                break;
                            }
                            else
                            {
                                java.awt.Color[] a11 = this.bitDataColor;
                                java.awt.Color a12 = DigitalSearchTree.BIT_SEARCH_ACTIVE_COLOR;
                                a11[i9] = a12;
                                int i10 = this.bitValue(i5, i9);
                                Integer a13 = new Integer(i10);
                                com.cim.AIA.Node a14 = new com.cim.AIA.Node((Object)a13, i9);
                                java.awt.Color[] a15 = this.bitDataColor;
                                java.awt.Color a16 = a15[i9];
                                a14.setBackgroundColor(a16);
                                java.awt.Color a17 = DigitalSearchTree.BIT_SEARCH_HIGHLIGHT_COLOR;
                                a14.setHighlightColor(a17);
                                java.awt.Color a18 = DigitalSearchTree.TEXT_COLOR;
                                a14.setTextColor(a18);
                                com.cim.AIA.ElementArray a19 = this.bitArray;
                                int i11 = this.mostSignificantBit;
                                int i12 = i11 - i9;
                                a19.setValue(i12, (com.cim.AIA.Element)a14);
                                int i13 = i9 + -1;
                                i9 = i13;
                            }
                        }
                        DigitalSearchTree$DigitalTree a20 = this.root;
                        DigitalSearchTree$DigitalTree a21 = this.root;
                        com.cim.AIA.Node a22 = a21.node;
                        this.ptrNode = a22;
                        this.setPosition("5.1.1");
                        DigitalSearchTree$DigitalTree a23 = a20;
                        int i14 = i;
                        while(true)
                        {
                            DigitalSearchTree$DigitalTree a24 = null;
                            if(a23 == null)
                            {
                                break;
                            }
                            java.awt.Color[] a25 = this.bitDataColor;
                            java.awt.Color a26 = DigitalSearchTree.BIT_SEARCH_HIGHLIGHT_COLOR;
                            a25[i14] = a26;
                            com.cim.AIA.ElementArray a27 = this.bitArray;
                            int i15 = this.mostSignificantBit;
                            int i16 = i15 - i14;
                            com.cim.AIA.Element a28 = a27.getElement(i16);
                            com.cim.AIA.Node dummy = (com.cim.AIA.Node)a28;
                            com.cim.AIA.Node a29 = (com.cim.AIA.Node)a28;
                            this.bitNode = a29;
                            this.setPosition("5.2.1");
                            int i17 = a23.data;
                            if(i17 == i5)
                            {
                                break label5;
                            }
                            this.setPosition("5.2.2");
                            this.setPosition("5.2.2.1.1");
                            int i18 = this.bitValue(i5, i14);
                            if(i18 != 0)
                            {
                                this.setPosition("5.2.2.1.2");
                                com.cim.AIA.Node a30 = a23.rightNode;
                                this.ptrNode = a30;
                                DigitalSearchTree$DigitalTree a31 = a23.rightChild;
                                this.setPosition("5.2.2.1.2.1");
                                a24 = a31;
                            }
                            else
                            {
                                com.cim.AIA.Node a32 = a23.leftNode;
                                this.ptrNode = a32;
                                DigitalSearchTree$DigitalTree a33 = a23.leftChild;
                                this.setPosition("5.2.2.1.1.1");
                                a24 = a33;
                            }
                            java.awt.Color[] a34 = this.bitDataColor;
                            java.awt.Color a35 = DigitalSearchTree.BIT_SEARCH_ACTIVE_COLOR;
                            a34[i14] = a35;
                            int i19 = i14 + -1;
                            a23 = a24;
                            i14 = i19;
                        }
                        java.awt.Color[] a36 = this.bitDataColor;
                        int i20 = i14 + 1;
                        java.awt.Color a37 = DigitalSearchTree.BIT_SEARCH_HIGHLIGHT_COLOR;
                        a36[i20] = a37;
                        DigitalSearchTree$DigitalTree a38 = this.root;
                        this.resetTreeColor(a38);
                        int i21 = this.enabled?1:0;
                        if(i21 == 0)
                        {
                            break label6;
                        }
                        com.cim.AIA.Node a39 = this.searchNode;
                        com.cim.AIA.StringMarker a40 = new com.cim.AIA.StringMarker("N");
                        a39.addMarker(a40);
                        com.cim.AIA.Node a41 = this.searchNode;
                        com.cim.AIA.StringMarker a42 = new com.cim.AIA.StringMarker("o");
                        a41.addMarker(a42);
                        com.cim.AIA.Node a43 = this.searchNode;
                        com.cim.AIA.StringMarker a44 = new com.cim.AIA.StringMarker("t");
                        a43.addMarker(a44);
                        com.cim.AIA.Node a45 = this.searchNode;
                        com.cim.AIA.StringMarker a46 = new com.cim.AIA.StringMarker(" ");
                        a45.addMarker(a46);
                        com.cim.AIA.Node a47 = this.searchNode;
                        com.cim.AIA.StringMarker a48 = new com.cim.AIA.StringMarker("F");
                        a47.addMarker(a48);
                        com.cim.AIA.Node a49 = this.searchNode;
                        com.cim.AIA.StringMarker a50 = new com.cim.AIA.StringMarker("o");
                        a49.addMarker(a50);
                        com.cim.AIA.Node a51 = this.searchNode;
                        com.cim.AIA.StringMarker a52 = new com.cim.AIA.StringMarker("u");
                        a51.addMarker(a52);
                        com.cim.AIA.Node a53 = this.searchNode;
                        com.cim.AIA.StringMarker a54 = new com.cim.AIA.StringMarker("n");
                        a53.addMarker(a54);
                        com.cim.AIA.Node a55 = this.searchNode;
                        com.cim.AIA.StringMarker a56 = new com.cim.AIA.StringMarker("d");
                        a55.addMarker(a56);
                        java.awt.Color[] a57 = this.searchDataColor;
                        com.cim.AIA.Node a58 = this.searchNode;
                        int i22 = a58.getIdentifier();
                        java.awt.Color a59 = DigitalSearchTree.SEARCH_DONE_COLOR;
                        a57[i22] = a59;
                        this.setPosition("5.3");
                    }
                    int i23 = this.isBackMode?1:0;
                    if(i23 == 0)
                    {
                        this.searchNode = null;
                    }
                    this.bitArray = null;
                    break label3;
                }
                DigitalSearchTree$DigitalTree a60 = this.root;
                this.resetTreeColor(a60);
                int i24 = this.enabled?1:0;
                if(i24 != 0)
                {
                    com.cim.AIA.Node a61 = this.searchNode;
                    com.cim.AIA.StringMarker a62 = new com.cim.AIA.StringMarker("F");
                    a61.addMarker(a62);
                    com.cim.AIA.Node a63 = this.searchNode;
                    com.cim.AIA.StringMarker a64 = new com.cim.AIA.StringMarker("o");
                    a63.addMarker(a64);
                    com.cim.AIA.Node a65 = this.searchNode;
                    com.cim.AIA.StringMarker a66 = new com.cim.AIA.StringMarker("u");
                    a65.addMarker(a66);
                    com.cim.AIA.Node a67 = this.searchNode;
                    com.cim.AIA.StringMarker a68 = new com.cim.AIA.StringMarker("n");
                    a67.addMarker(a68);
                    com.cim.AIA.Node a69 = this.searchNode;
                    com.cim.AIA.StringMarker a70 = new com.cim.AIA.StringMarker("d");
                    a69.addMarker(a70);
                    java.awt.Color[] a71 = this.searchDataColor;
                    com.cim.AIA.Node a72 = this.searchNode;
                    int i25 = a72.getIdentifier();
                    java.awt.Color a73 = DigitalSearchTree.SEARCH_DONE_COLOR;
                    a71[i25] = a73;
                    this.setPosition("5.2.1.1");
                    this.bitArray = null;
                    break label3;
                }
                else
                {
                    java.awt.Color[] a74 = this.searchDataColor;
                    com.cim.AIA.Node a75 = this.searchNode;
                    int i26 = a75.getIdentifier();
                    java.awt.Color a76 = DigitalSearchTree.SEARCH_ACTIVE_COLOR;
                    a74[i26] = a76;
                    this.bitArray = null;
                    break label3;
                }
            }
            java.awt.Color[] a77 = this.searchDataColor;
            com.cim.AIA.Node a78 = this.searchNode;
            int i27 = a78.getIdentifier();
            java.awt.Color a79 = DigitalSearchTree.SEARCH_ACTIVE_COLOR;
            a77[i27] = a79;
            int i28 = this.isBackMode?1:0;
            if(i28 == 0)
            {
                this.searchNode = null;
            }
            this.bitArray = null;
        }
    }
    
    protected void insert()
    {
        this.setPosition("1");
        int[] a = this.insertData;
        int i = a.length;
        label0: {
            if(i <= 0)
            {
                break label0;
            }
            int i0 = this.insertPos;
            if(i0 == 0)
            {
                java.awt.Color[] a0 = this.insertDataColor;
                java.awt.Color a1 = DigitalSearchTree.INSERT_HIGHLIGHT_COLOR;
                a0[0] = a1;
                int[] a2 = this.insertData;
                int i1 = a2[0];
                DigitalSearchTree$DigitalTree a3 = new DigitalSearchTree$DigitalTree(this, i1, 0);
                this.root = a3;
                java.awt.Color[] a4 = this.insertDataColor;
                java.awt.Color a5 = DigitalSearchTree.INSERT_DONE_COLOR;
                a4[0] = a5;
                DigitalSearchTree$DigitalTree a6 = this.root;
                com.cim.AIA.Node a7 = a6.node;
                java.awt.Color a8 = DigitalSearchTree.TREE_ACTIVE_COLOR;
                a7.setBackgroundColor(a8);
                DigitalSearchTree$DigitalTree a9 = this.root;
                com.cim.AIA.Node a10 = a9.node;
                a10.setMarkersBelowValue(false);
                DigitalSearchTree$DigitalTree a11 = this.root;
                com.cim.AIA.Node a12 = a11.node;
                a12.removeAllMarkers();
                int i2 = this.insertPos;
                int i3 = i2 + 1;
                this.insertPos = i3;
            }
            this.setPosition("2.0");
            this.setPosition("2.1");
            this.setPosition("2.2");
            int i4 = this.insertPos;
            int i5 = i4;
            while(true)
            {
                int[] a13 = this.insertData;
                int i6 = a13.length;
                if(i5 >= i6)
                {
                    break;
                }
                else
                {
                    this.insertItem(i5);
                    int i7 = i5 + 1;
                    i5 = i7;
                }
            }
        }
    }
    
    protected void insertItem(int i)
    {
        int i0 = this.mostSignificantBit;
        int i1 = i0 + 1;
        this.setPosition("3");
        int i2 = this.mostSignificantBit;
        int i3 = i2 + 1;
        java.awt.Color[] a = new java.awt.Color[i3];
        this.bitDataColor = a;
        com.cim.AIA.ElementArray a0 = new com.cim.AIA.ElementArray(0, 0);
        this.bitArray = a0;
        com.cim.AIA.ElementArray a1 = this.bitArray;
        a1.setColumGap(0);
        com.cim.AIA.ElementArray a2 = this.bitArray;
        a2.setElementWidth(20);
        int i4 = this.mostSignificantBit;
        int i5 = i4;
        while(true)
        {
            if(i5 < 0)
            {
                break;
            }
            else
            {
                java.awt.Color[] a3 = this.bitDataColor;
                java.awt.Color a4 = DigitalSearchTree.BIT_INSERT_ACTIVE_COLOR;
                a3[i5] = a4;
                int[] a5 = this.insertData;
                int i6 = a5[i];
                int i7 = this.bitValue(i6, i5);
                Integer a6 = new Integer(i7);
                com.cim.AIA.Node a7 = new com.cim.AIA.Node((Object)a6, i5);
                java.awt.Color[] a8 = this.bitDataColor;
                java.awt.Color a9 = a8[i5];
                a7.setBackgroundColor(a9);
                java.awt.Color a10 = DigitalSearchTree.BIT_INSERT_HIGHLIGHT_COLOR;
                a7.setHighlightColor(a10);
                java.awt.Color a11 = DigitalSearchTree.TEXT_COLOR;
                a7.setTextColor(a11);
                com.cim.AIA.ElementArray a12 = this.bitArray;
                int i8 = this.mostSignificantBit;
                int i9 = i8 - i5;
                a12.setValue(i9, (com.cim.AIA.Element)a7);
                int i10 = i5 + -1;
                i5 = i10;
            }
        }
        java.awt.Color[] a13 = this.insertDataColor;
        java.awt.Color a14 = DigitalSearchTree.INSERT_HIGHLIGHT_COLOR;
        a13[i] = a14;
        DigitalSearchTree$DigitalTree a15 = this.root;
        DigitalSearchTree$DigitalTree a16 = this.root;
        com.cim.AIA.Node a17 = a16.node;
        this.ptrNode = a17;
        this.setPosition("3.1.0.1.1");
        int i11 = i1;
        DigitalSearchTree$DigitalTree a18 = a15;
        DigitalSearchTree$DigitalTree a19 = a15;
        label1: {
            label0: {
                while(true)
                {
                    if(a18 == null)
                    {
                        break label0;
                    }
                    this.setPosition("3.1.1.1");
                    com.cim.AIA.Node a20 = this.ptrNode;
                    this.parentNode = a20;
                    this.setPosition("3.1.1.2.1");
                    this.setPosition("3.1.1.2.10.1");
                    int[] a21 = this.insertData;
                    int i12 = a21[i];
                    int i13 = a18.data;
                    if(i12 == i13)
                    {
                        break;
                    }
                    int i14 = i11 + -1;
                    int i15 = this.mostSignificantBit;
                    if(i14 < i15)
                    {
                        java.awt.Color[] a22 = this.bitDataColor;
                        int i16 = i14 + 1;
                        java.awt.Color a23 = DigitalSearchTree.BIT_INSERT_ACTIVE_COLOR;
                        a22[i16] = a23;
                    }
                    int i17 = this.isExpanded("3.1.1")?1:0;
                    if(i17 != 0)
                    {
                        java.awt.Color[] a24 = this.bitDataColor;
                        java.awt.Color a25 = DigitalSearchTree.BIT_INSERT_HIGHLIGHT_COLOR;
                        a24[i14] = a25;
                    }
                    else
                    {
                        java.awt.Color[] a26 = this.bitDataColor;
                        java.awt.Color a27 = DigitalSearchTree.BIT_INSERT_ACTIVE_COLOR;
                        a26[i14] = a27;
                    }
                    com.cim.AIA.ElementArray a28 = this.bitArray;
                    int i18 = this.mostSignificantBit;
                    int i19 = i18 - i14;
                    com.cim.AIA.Element a29 = a28.getElement(i19);
                    com.cim.AIA.Node dummy = (com.cim.AIA.Node)a29;
                    com.cim.AIA.Node a30 = (com.cim.AIA.Node)a29;
                    this.bitNode = a30;
                    this.setPosition("3.1.1.2.4");
                    int[] a31 = this.insertData;
                    int i20 = a31[i];
                    int i21 = this.bitValue(i20, i14);
                    if(i21 != 0)
                    {
                        com.cim.AIA.Node a32 = a18.rightNode;
                        this.ptrNode = a32;
                        DigitalSearchTree$DigitalTree a33 = a18.rightChild;
                        this.setPosition("3.1.1.2.3");
                        i11 = i14;
                        a18 = a33;
                        a19 = a18;
                        continue;
                    }
                    else
                    {
                        com.cim.AIA.Node a34 = a18.leftNode;
                        this.ptrNode = a34;
                        DigitalSearchTree$DigitalTree a35 = a18.leftChild;
                        this.setPosition("3.1.1.2.2");
                        i11 = i14;
                        a18 = a35;
                        a19 = a18;
                        continue;
                    }
                }
                String s = DigitalSearchTree.INSERTION_LABEL;
                DigitalSearchTree.ptrLabel = s;
                this.setPosition("3.1.1.2.10.1.1");
                this.setPosition("3.1.1.2.10.1.2");
                java.awt.Color[] a36 = this.insertDataColor;
                java.awt.Color a37 = DigitalSearchTree.INSERT_ACTIVE_COLOR;
                a36[i] = a37;
                break label1;
            }
            this.setPosition("3.1.1.1");
            String s0 = DigitalSearchTree.INSERTION_LABEL;
            DigitalSearchTree.ptrLabel = s0;
            com.cim.AIA.ElementArray a38 = this.bitArray;
            com.cim.AIA.Node a39 = this.parentNode;
            int i22 = this.isExpanded("3.1.2")?1:0;
            if(i22 == 0)
            {
                this.parentNode = null;
            }
            this.setPosition("3.1.1.3");
            this.parentNode = a39;
            this.bitArray = a38;
            int i23 = this.enabled?1:0;
            label2: {
                if(i23 != 0)
                {
                    break label2;
                }
                String s1 = DigitalSearchTree.PTR_LABEL;
                DigitalSearchTree.ptrLabel = s1;
                this.ptrNode = null;
                this.bitArray = null;
                break label1;
            }
            com.cim.AIA.ElementArray a40 = this.bitArray;
            int i24 = this.mostSignificantBit;
            int i25 = i24 - i11;
            com.cim.AIA.Element a41 = a40.getElement(i25);
            com.cim.AIA.Node dummy0 = (com.cim.AIA.Node)a41;
            com.cim.AIA.Node a42 = (com.cim.AIA.Node)a41;
            this.bitNode = a42;
            int i26 = this.isExpanded("3.1.2")?1:0;
            if(i26 != 0)
            {
                java.awt.Color[] a43 = this.bitDataColor;
                java.awt.Color a44 = DigitalSearchTree.BIT_INSERT_HIGHLIGHT_COLOR;
                a43[i11] = a44;
            }
            else
            {
                this.parentNode = null;
                java.awt.Color[] a45 = this.bitDataColor;
                java.awt.Color a46 = DigitalSearchTree.BIT_INSERT_ACTIVE_COLOR;
                a45[i11] = a46;
            }
            this.setPosition("3.1.2.1");
            int[] a47 = this.insertData;
            int i27 = a47[i];
            int i28 = this.bitValue(i27, i11);
            if(i28 != 0)
            {
                this.setPosition("3.1.2.2");
                int[] a48 = this.insertData;
                int i29 = a48[i];
                Integer a49 = new Integer(i29);
                com.cim.AIA.Node a50 = new com.cim.AIA.Node((Object)a49, 0);
                a19.rightNode = a50;
                com.cim.AIA.Node a51 = a19.rightNode;
                java.awt.Color a52 = DigitalSearchTree.TREE_HIGHLIGHT_COLOR;
                a51.setBackgroundColor(a52);
                this.setPosition("3.1.2.2.1");
                int[] a53 = this.insertData;
                int i30 = a53[i];
                DigitalSearchTree$DigitalTree a54 = new DigitalSearchTree$DigitalTree(this, i30, i);
                a19.rightChild = a54;
                DigitalSearchTree$DigitalTree a55 = a19.rightChild;
                com.cim.AIA.Node a56 = a55.node;
                com.cim.AIA.StringMarker a57 = new com.cim.AIA.StringMarker(" 1");
                a56.addMarker(a57);
                DigitalSearchTree$DigitalTree a58 = a19.rightChild;
                com.cim.AIA.Node a59 = a58.node;
                a19.rightNode = a59;
            }
            else
            {
                int[] a60 = this.insertData;
                int i31 = a60[i];
                Integer a61 = new Integer(i31);
                com.cim.AIA.Node a62 = new com.cim.AIA.Node((Object)a61, 0);
                a19.leftNode = a62;
                com.cim.AIA.Node a63 = a19.leftNode;
                java.awt.Color a64 = DigitalSearchTree.TREE_HIGHLIGHT_COLOR;
                a63.setBackgroundColor(a64);
                this.setPosition("3.1.2.1.1");
                int[] a65 = this.insertData;
                int i32 = a65[i];
                DigitalSearchTree$DigitalTree a66 = new DigitalSearchTree$DigitalTree(this, i32, i);
                a19.leftChild = a66;
                DigitalSearchTree$DigitalTree a67 = a19.leftChild;
                com.cim.AIA.Node a68 = a67.node;
                com.cim.AIA.StringMarker a69 = new com.cim.AIA.StringMarker("0 ");
                a68.addMarker(a69);
                DigitalSearchTree$DigitalTree a70 = a19.leftChild;
                com.cim.AIA.Node a71 = a70.node;
                a19.leftNode = a71;
            }
            String s2 = DigitalSearchTree.PTR_LABEL;
            DigitalSearchTree.ptrLabel = s2;
            this.ptrNode = null;
            this.parentNode = null;
            this.bitNode = null;
            this.bitArray = null;
            DigitalSearchTree$DigitalTree a72 = this.root;
            this.resetTreeColor(a72);
            java.awt.Color[] a73 = this.insertDataColor;
            java.awt.Color a74 = DigitalSearchTree.INSERT_DONE_COLOR;
            a73[i] = a74;
            int i33 = this.insertPos;
            int i34 = i33 + 1;
            this.insertPos = i34;
        }
    }
    
    public boolean isExpanded(String s)
    {
        com.cim.AIA.AlgorithmThread a = this.algorithmThread;
        DigitalSearchTreeThread dummy = (DigitalSearchTreeThread)a;
        DigitalSearchTreeThread a0 = (DigitalSearchTreeThread)a;
        com.cim.AIA.AlgorithmWindow a1 = a0.getWindow();
        int i = a1.isExpanded(s)?1:0;
        return i != 0;
    }
    
    private int bitValue(int i, int i0)
    {
        int i1 = i >> i0;
        int i2 = i1 % 2;
        return i2;
    }
    
    private void resetTreeColor(DigitalSearchTree$DigitalTree a)
    {
        if(a != null)
        {
            com.cim.AIA.Node a0 = a.node;
            java.awt.Color a1 = DigitalSearchTree.TREE_ACTIVE_COLOR;
            a0.setBackgroundColor(a1);
            com.cim.AIA.Node a2 = a.node;
            java.awt.Color a3 = DigitalSearchTree.TREE_RING_COLOR;
            a2.setRingColor(a3);
            com.cim.AIA.Node a4 = a.node;
            java.awt.Color a5 = DigitalSearchTree.TEXT_COLOR;
            a4.setTextColor(a5);
            DigitalSearchTree$DigitalTree a6 = a.leftChild;
            this.resetTreeColor(a6);
            DigitalSearchTree$DigitalTree a7 = a.rightChild;
            this.resetTreeColor(a7);
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
    
    static java.awt.Color access$000()
    {
        java.awt.Color a = DigitalSearchTree.TREE_HIGHLIGHT_COLOR;
        return a;
    }
    
    static java.awt.Color access$100()
    {
        java.awt.Color a = DigitalSearchTree.TEXT_COLOR;
        return a;
    }
    
    static java.awt.Color access$200()
    {
        java.awt.Color a = DigitalSearchTree.PATH_COLOR;
        return a;
    }
    
    static java.awt.Color access$300()
    {
        java.awt.Color a = DigitalSearchTree.TREE_NULL_COLOR;
        return a;
    }
    
    static
    {
        java.awt.Color a = java.awt.Color.black;
        DigitalSearchTree.TEXT_COLOR = a;
        java.awt.Color a0 = new java.awt.Color(0, 210, 0);
        DigitalSearchTree.INSERT_HIGHLIGHT_COLOR = a0;
        java.awt.Color a1 = new java.awt.Color(64, 191, 64);
        DigitalSearchTree.INSERT_ACTIVE_COLOR = a1;
        java.awt.Color a2 = new java.awt.Color(96, 127, 96);
        DigitalSearchTree.INSERT_DONE_COLOR = a2;
        java.awt.Color a3 = new java.awt.Color(200, 255, 200);
        DigitalSearchTree.BIT_INSERT_HIGHLIGHT_COLOR = a3;
        java.awt.Color a4 = DigitalSearchTree.INSERT_HIGHLIGHT_COLOR;
        DigitalSearchTree.BIT_INSERT_ACTIVE_COLOR = a4;
        java.awt.Color a5 = new java.awt.Color(64, 255, 255);
        DigitalSearchTree.SEARCH_HIGHLIGHT_COLOR = a5;
        java.awt.Color a6 = new java.awt.Color(64, 191, 191);
        DigitalSearchTree.SEARCH_ACTIVE_COLOR = a6;
        java.awt.Color a7 = new java.awt.Color(0, 127, 127);
        DigitalSearchTree.SEARCH_DONE_COLOR = a7;
        java.awt.Color a8 = new java.awt.Color(255, 255, 200);
        DigitalSearchTree.BIT_SEARCH_HIGHLIGHT_COLOR = a8;
        java.awt.Color a9 = DigitalSearchTree.SEARCH_HIGHLIGHT_COLOR;
        DigitalSearchTree.BIT_SEARCH_ACTIVE_COLOR = a9;
        java.awt.Color a10 = new java.awt.Color(255, 255, 0);
        DigitalSearchTree.TREE_HIGHLIGHT_COLOR = a10;
        java.awt.Color a11 = new java.awt.Color(191, 191, 64);
        DigitalSearchTree.TREE_ACTIVE_COLOR = a11;
        java.awt.Color a12 = new java.awt.Color(191, 191, 0);
        DigitalSearchTree.TREE_DONE_COLOR = a12;
        java.awt.Color a13 = java.awt.Color.black;
        DigitalSearchTree.TREE_RING_COLOR = a13;
        java.awt.Color a14 = java.awt.Color.blue;
        DigitalSearchTree.TREE_NULL_COLOR = a14;
        java.awt.Color a15 = java.awt.Color.red;
        DigitalSearchTree.PATH_COLOR = a15;
        String s = localization.Messages.getString("DigitalSearchTree.0");
        DigitalSearchTree.BUILD_MODE_LABEL = s;
        String s0 = localization.Messages.getString("DigitalSearchTree.1");
        DigitalSearchTree.SEARCH_MODE_LABEL = s0;
        String s1 = localization.Messages.getString("DigitalSearchTree.2");
        DigitalSearchTree.DELETE_MODE_LABEL = s1;
        String s2 = localization.Messages.getString("DigitalSearchTree.3");
        DigitalSearchTree.PARENT_LABEL = s2;
        String s3 = localization.Messages.getString("DigitalSearchTree.4");
        DigitalSearchTree.BIT_LABEL = s3;
        String s4 = localization.Messages.getString("DigitalSearchTree.5");
        DigitalSearchTree.PTR_LABEL = s4;
        String s5 = localization.Messages.getString("DigitalSearchTree.6");
        DigitalSearchTree.INSERTION_LABEL = s5;
        String s6 = DigitalSearchTree.PTR_LABEL;
        DigitalSearchTree.ptrLabel = s6;
    }
}