public class BinarySearchTree extends com.cim.AIA.Algorithm implements com.cim.AIA.MethodSelectionListener, com.cim.AIA.ControlListener {
    final private static byte shrinkFactor = 2;
    final private static java.awt.Color TEXT_COLOR;
    final private static java.awt.Color INSERT_HIGHLIGHT_COLOR;
    final private static java.awt.Color INSERT_ACTIVE_COLOR;
    final private static java.awt.Color INSERT_DONE_COLOR;
    final private static java.awt.Color SEARCH_HIGHLIGHT_COLOR;
    final private static java.awt.Color SEARCH_ACTIVE_COLOR;
    final private static java.awt.Color SEARCH_DONE_COLOR;
    final private static java.awt.Color TREE_HIGHLIGHT_COLOR;
    final private static java.awt.Color TREE_ACTIVE_COLOR;
    final private static java.awt.Color TREE_RING_COLOR;
    final private static java.awt.Color TREE_NULL_COLOR;
    final private static java.awt.Color PATH_COLOR;
    public static NewBackButton backButton;
    final private static int BUILD_MODE = 11;
    final private static int SEARCH_MODE = 12;
    final private static int DELETE_MODE = 13;
    final private static String BUILD_MODE_LABEL;
    final private static String SEARCH_MODE_LABEL;
    final private static String DELETE_MODE_LABEL;
    final protected static String PARENT_LABEL;
    final protected static String PTR_LABEL;
    final protected static String DATAITEMPTR_LABEL;
    final protected static String REPLACEMENT_LABEL;
    private int[] insertData;
    private int insertPos;
    private int[] searchData;
    private BinarySearchTree$BinaryTree root;
    private BinarySearchTree$BinaryTree saveRoot;
    private com.cim.AIA.Node searchNode;
    private com.cim.AIA.Node deleteNode;
    private com.cim.AIA.Node saveDeleteNode;
    private java.awt.Color[] insertDataColor;
    private java.awt.Color[] searchDataColor;
    private com.cim.AIA.ElementArray searchArray;
    private com.cim.AIA.ElementArray insertArray;
    private java.util.Vector moveRequests;
    private java.util.Vector newLinks;
    private com.cim.AIA.Node parentNode;
    private com.cim.AIA.Node ptrNode;
    private com.cim.AIA.Node dataItemNode;
    private com.cim.AIA.Node replaceNode;
    private com.cim.AIA.Node nullNode;
    private com.cim.AIA.ExtendedHierarchyTree ptrTree;
    private boolean isBackMode;
    private int executingMode;
    private int nextMode;
    
    public BinarySearchTree(com.cim.AIA.AlgorithmThread a, Object a0)
    {
        super(a, a0);
        java.util.Vector a1 = new java.util.Vector();
        this.moveRequests = a1;
        java.util.Vector a2 = new java.util.Vector();
        this.newLinks = a2;
        this.isBackMode = false;
        this.executingMode = 11;
        int i = this.executingMode;
        this.nextMode = i;
        int[] dummy = (int[])a0;
        int[] a3 = (int[])a0;
        this.insertData = a3;
        int[] dummy0 = (int[])a0;
        int[] a4 = (int[])a0;
        this.searchData = a4;
        this.root = null;
        this.initialise();
    }
    
    private com.cim.AIA.ExtendedHierarchyTree buildHierarchyTree(BinarySearchTree$BinaryTree a, int i)
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
            BinarySearchTree$BinaryTree a8 = a.leftChild;
            label2: {
                label1: {
                    if(a8 == null)
                    {
                        break label1;
                    }
                    BinarySearchTree$BinaryTree a9 = a.leftChild;
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
            BinarySearchTree$BinaryTree a17 = a.rightChild;
            label3: {
                if(a17 == null)
                {
                    break label3;
                }
                BinarySearchTree$BinaryTree a18 = a.rightChild;
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
    
    protected void changeData(Object a)
    {
        int[] a0 = this.insertData;
        int[] dummy = (int[])a;
        int[] dummy0 = (int[])a;
        int[] a1 = (int[])a;
        this.insertData = a1;
        int[] dummy1 = (int[])a;
        int[] a2 = (int[])a;
        this.searchData = a2;
        this.initialise();
    }
    
    protected void clearState()
    {
        this.saveRoot = null;
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
            java.awt.Color a1 = BinarySearchTree.PATH_COLOR;
            a.setParentLineColor(a1);
            java.util.Vector a2 = a.getNodes();
            int i = a2.size();
            if(i > 0)
            {
                java.util.Vector a3 = a.getNodes();
                Object a4 = a3.elementAt(0);
                com.cim.AIA.Node dummy = (com.cim.AIA.Node)a4;
                com.cim.AIA.Node a5 = (com.cim.AIA.Node)a4;
                java.awt.Color a6 = BinarySearchTree.PATH_COLOR;
                a5.setTextColor(a6);
                java.util.Vector a7 = a.getNodes();
                Object a8 = a7.elementAt(0);
                com.cim.AIA.Node dummy0 = (com.cim.AIA.Node)a8;
                com.cim.AIA.Node a9 = (com.cim.AIA.Node)a8;
                java.awt.Color a10 = BinarySearchTree.PATH_COLOR;
                a9.setRingColor(a10);
            }
            com.cim.AIA.Tree a11 = a.getParent();
            com.cim.AIA.ExtendedHierarchyTree dummy1 = (com.cim.AIA.ExtendedHierarchyTree)a11;
            com.cim.AIA.ExtendedHierarchyTree a12 = (com.cim.AIA.ExtendedHierarchyTree)a11;
            this.colorHierarchyTree(a12);
        }
    }
    
    public void controlBack(com.cim.AIA.ControlEvent a)
    {
        this.isBackMode = true;
    }
    
    public void controlOther(com.cim.AIA.ControlEvent a)
    {
        this.isBackMode = false;
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
    
    public void controlStep(com.cim.AIA.ControlEvent a)
    {
        this.isBackMode = false;
    }
    
    protected void delete()
    {
        this.setPosition("4.0");
        BinarySearchTree$BinaryTree a = this.root;
        label26: {
            label3: {
                label2: {
                    label4: {
                        label0: {
                            if(a == null)
                            {
                                break label0;
                            }
                            int i = this.isBackMode?1:0;
                            label1: {
                                if(i != 0)
                                {
                                    break label1;
                                }
                                int i0 = this.enabled?1:0;
                                if(i0 == 0)
                                {
                                    break label2;
                                }
                                DeleteSelection.setEnabled(true);
                                while(true)
                                {
                                    com.cim.AIA.Node a0 = DeleteSelection.getNode();
                                    if(a0 != null)
                                    {
                                        break;
                                    }
                                    int i1 = this.enabled?1:0;
                                    if(i1 == 0)
                                    {
                                        break label3;
                                    }
                                    try
                                    {
                                        Thread.sleep(1000L);
                                    }
                                    catch(Exception ignoredException)
                                    {
                                    }
                                }
                                com.cim.AIA.Node a1 = DeleteSelection.getNode();
                                this.deleteNode = a1;
                                DeleteSelection.setEnabled(false);
                                int i2 = this.enabled?1:0;
                                if(i2 == 0)
                                {
                                    break label4;
                                }
                            }
                            int[] a2 = this.insertData;
                            com.cim.AIA.Node a3 = this.deleteNode;
                            int i3 = a3.getIdentifier();
                            int i4 = a2[i3];
                            BinarySearchTree$BinaryTree a4 = this.root;
                            BinarySearchTree$BinaryTree a5 = this.saveTree(a4);
                            this.saveRoot = a5;
                            BinarySearchTree$BinaryTree a6 = this.root;
                            BinarySearchTree$BinaryTree a7 = this.root;
                            com.cim.AIA.Node a8 = a7.node;
                            this.ptrNode = a8;
                            this.setPosition("4.1.1");
                            BinarySearchTree$BinaryTree a9 = this.root;
                            com.cim.AIA.Node a10 = a9.node;
                            this.parentNode = a10;
                            BinarySearchTree$BinaryTree a11 = a6;
                            BinarySearchTree$BinaryTree a12 = a6;
                            while(true)
                            {
                                int i5 = a11.data;
                                if(i5 == i4)
                                {
                                    break;
                                }
                                this.setPosition("4.2.1");
                                com.cim.AIA.Node a13 = this.ptrNode;
                                this.parentNode = a13;
                                this.setPosition("4.2.1.1");
                                this.setPosition("4.2.1.2");
                                int i6 = a11.data;
                                if(i6 <= i4)
                                {
                                    this.setPosition("4.2.1.3");
                                    com.cim.AIA.Node a14 = a11.rightNode;
                                    this.ptrNode = a14;
                                    BinarySearchTree$BinaryTree a15 = a11.rightChild;
                                    this.setPosition("4.2.1.3.1");
                                    a11 = a15;
                                    a12 = a11;
                                }
                                else
                                {
                                    com.cim.AIA.Node a16 = a11.leftNode;
                                    this.ptrNode = a16;
                                    BinarySearchTree$BinaryTree a17 = a11.leftChild;
                                    this.setPosition("4.2.1.2.1");
                                    a11 = a17;
                                    a12 = a11;
                                }
                            }
                            this.setPosition("4.2.1");
                            this.setPosition("4.2.2");
                            this.setPosition("4.2.3");
                            com.cim.AIA.Node a18 = this.ptrNode;
                            this.dataItemNode = a18;
                            com.cim.AIA.Node a19 = this.ptrNode;
                            this.parentNode = a19;
                            this.setPosition("4.2.4");
                            BinarySearchTree$BinaryTree a20 = a11.rightChild;
                            label5: {
                                BinarySearchTree$BinaryTree a21 = null;
                                BinarySearchTree$BinaryTree a22 = null;
                                if(a20 != null)
                                {
                                    break label5;
                                }
                                com.cim.AIA.Node a23 = a11.leftNode;
                                this.ptrNode = a23;
                                BinarySearchTree$BinaryTree a24 = a11.leftChild;
                                label7: {
                                    label6: {
                                        if(a24 != null)
                                        {
                                            break label6;
                                        }
                                        this.setPosition("4.3.1.3.2");
                                        this.setPosition("4.3.1.3.3");
                                        a21 = null;
                                        a22 = a11;
                                        break label7;
                                    }
                                    this.setPosition("4.3.1.3.1");
                                    BinarySearchTree$BinaryTree a25 = a24;
                                    BinarySearchTree$BinaryTree a26 = a11;
                                    while(true)
                                    {
                                        BinarySearchTree$BinaryTree a27 = a25.rightChild;
                                        if(a27 == null)
                                        {
                                            break;
                                        }
                                        else
                                        {
                                            this.setPosition("4.3.1.3.2");
                                            com.cim.AIA.Node a28 = this.ptrNode;
                                            this.parentNode = a28;
                                            this.setPosition("4.3.1.3.2.1");
                                            com.cim.AIA.Node a29 = a25.rightNode;
                                            this.ptrNode = a29;
                                            BinarySearchTree$BinaryTree a30 = a25.rightChild;
                                            this.setPosition("4.3.1.3.2.2");
                                            a25 = a30;
                                            a26 = a25;
                                        }
                                    }
                                    this.setPosition("4.3.1.3.2");
                                    this.setPosition("4.3.1.3.3");
                                    a21 = a25;
                                    a22 = a26;
                                }
                                com.cim.AIA.Node a31 = this.ptrNode;
                                this.replaceNode = a31;
                                this.setPosition("4.3.1.4.1");
                                label9: {
                                    label8: {
                                        if(a22 == a11)
                                        {
                                            break label8;
                                        }
                                        java.util.Vector a32 = this.newLinks;
                                        com.cim.AIA.Node a33 = this.parentNode;
                                        com.cim.AIA.Node a34 = a21.leftNode;
                                        NewLinks a35 = new NewLinks(a33, true, a34);
                                        a32.addElement((Object)a35);
                                        this.setPosition("4.3.1.4.1.1");
                                        java.util.Vector a36 = this.newLinks;
                                        com.cim.AIA.Node a37 = this.ptrNode;
                                        com.cim.AIA.Node a38 = a11.leftNode;
                                        NewLinks a39 = new NewLinks(a37, false, a38);
                                        a36.addElement((Object)a39);
                                        this.setPosition("4.3.1.4.1.2");
                                        java.util.Vector a40 = this.newLinks;
                                        com.cim.AIA.Node a41 = this.ptrNode;
                                        com.cim.AIA.Node a42 = a11.rightNode;
                                        NewLinks a43 = new NewLinks(a41, true, a42);
                                        a40.addElement((Object)a43);
                                        this.setPosition("4.3.1.4.1.3");
                                        break label9;
                                    }
                                    if(a22 == a11)
                                    {
                                    }
                                }
                                this.dataItemNode = null;
                                int i7 = a12.data;
                                label11: {
                                    label10: {
                                        if(i7 != i4)
                                        {
                                            break label10;
                                        }
                                        this.setPosition("4.3.1.5.1");
                                        break label11;
                                    }
                                    BinarySearchTree$BinaryTree a44 = a12.leftChild;
                                    label13: {
                                        label12: {
                                            if(a44 == null)
                                            {
                                                break label12;
                                            }
                                            BinarySearchTree$BinaryTree a45 = a12.leftChild;
                                            int i8 = a45.data;
                                            if(i4 == i8)
                                            {
                                                break label13;
                                            }
                                        }
                                        this.setPosition("4.3.1.5.1");
                                        this.setPosition("4.3.1.5.2");
                                        java.util.Vector a46 = this.newLinks;
                                        com.cim.AIA.Node a47 = a12.node;
                                        com.cim.AIA.Node a48 = this.ptrNode;
                                        NewLinks a49 = new NewLinks(a47, true, a48);
                                        a46.addElement((Object)a49);
                                        this.setPosition("4.3.1.5.3");
                                        break label11;
                                    }
                                    this.setPosition("4.3.1.5.1");
                                    java.util.Vector a50 = this.newLinks;
                                    com.cim.AIA.Node a51 = a12.node;
                                    com.cim.AIA.Node a52 = this.ptrNode;
                                    NewLinks a53 = new NewLinks(a51, false, a52);
                                    a50.addElement((Object)a53);
                                    this.setPosition("4.3.1.5.2");
                                }
                                java.util.Vector a54 = this.newLinks;
                                a54.removeAllElements();
                                label15: {
                                    label14: {
                                        if(a22 == a11)
                                        {
                                            break label14;
                                        }
                                        BinarySearchTree$BinaryTree a55 = a21.leftChild;
                                        a22.rightChild = a55;
                                        com.cim.AIA.Node a56 = a21.leftNode;
                                        a22.rightNode = a56;
                                        com.cim.AIA.Node a57 = a11.leftNode;
                                        a21.leftNode = a57;
                                        com.cim.AIA.Node a58 = a11.rightNode;
                                        a21.rightNode = a58;
                                        BinarySearchTree$BinaryTree a59 = a11.leftChild;
                                        a21.leftChild = a59;
                                        BinarySearchTree$BinaryTree a60 = a11.rightChild;
                                        a21.rightChild = a60;
                                        break label15;
                                    }
                                    if(a22 != a11)
                                    {
                                        break label15;
                                    }
                                    if(a21 != null)
                                    {
                                        com.cim.AIA.Node a61 = a11.rightNode;
                                        a21.rightNode = a61;
                                        BinarySearchTree$BinaryTree a62 = a11.rightChild;
                                        a21.rightChild = a62;
                                    }
                                }
                                int i9 = a12.data;
                                label16: {
                                    if(i9 != i4)
                                    {
                                        break label16;
                                    }
                                    this.root = a21;
                                    BinarySearchTree$BinaryTree a63 = this.root;
                                    if(a63 != null)
                                    {
                                        BinarySearchTree$BinaryTree a64 = this.root;
                                        com.cim.AIA.Node a65 = this.ptrNode;
                                        a64.node = a65;
                                        BinarySearchTree$BinaryTree a66 = this.root;
                                        com.cim.AIA.Node a67 = a66.node;
                                        a67.setMarkersBelowValue(false);
                                        BinarySearchTree$BinaryTree a68 = this.root;
                                        com.cim.AIA.Node a69 = a68.node;
                                        String s = localization.Messages.getString("BinarySearchTree.47");
                                        com.cim.AIA.StringMarker a70 = new com.cim.AIA.StringMarker(s);
                                        a69.addMarker(a70);
                                    }
                                    this.parentNode = null;
                                    this.ptrNode = null;
                                    this.replaceNode = null;
                                    this.setPosition("4.3.1.5.1.1");
                                    break label0;
                                }
                                BinarySearchTree$BinaryTree a71 = a12.leftChild;
                                label18: {
                                    label17: {
                                        if(a71 == null)
                                        {
                                            break label17;
                                        }
                                        BinarySearchTree$BinaryTree a72 = a12.leftChild;
                                        int i10 = a72.data;
                                        if(i4 == i10)
                                        {
                                            break label18;
                                        }
                                    }
                                    a12.rightChild = a21;
                                    com.cim.AIA.Node a73 = this.ptrNode;
                                    a12.rightNode = a73;
                                    this.parentNode = null;
                                    this.ptrNode = null;
                                    this.replaceNode = null;
                                    this.setPosition("4.3.1.5.3.1");
                                    break label0;
                                }
                                a12.leftChild = a21;
                                com.cim.AIA.Node a74 = this.ptrNode;
                                a12.leftNode = a74;
                                this.parentNode = null;
                                this.ptrNode = null;
                                this.replaceNode = null;
                                String s0 = localization.Messages.getString("BinarySearchTree.49");
                                this.setPosition(s0);
                                break label0;
                            }
                            BinarySearchTree$BinaryTree a75 = a11.rightChild;
                            if(a75 == null)
                            {
                                break label0;
                            }
                            com.cim.AIA.Node a76 = a11.rightNode;
                            this.ptrNode = a76;
                            BinarySearchTree$BinaryTree a77 = a11.rightChild;
                            this.setPosition("4.3.2.3.1");
                            BinarySearchTree$BinaryTree a78 = a77;
                            BinarySearchTree$BinaryTree a79 = a11;
                            while(true)
                            {
                                BinarySearchTree$BinaryTree a80 = a78.leftChild;
                                if(a80 == null)
                                {
                                    break;
                                }
                                else
                                {
                                    this.setPosition("4.3.2.3.2");
                                    com.cim.AIA.Node a81 = this.ptrNode;
                                    this.parentNode = a81;
                                    this.setPosition("4.3.2.3.2.1");
                                    com.cim.AIA.Node a82 = a78.leftNode;
                                    this.ptrNode = a82;
                                    BinarySearchTree$BinaryTree a83 = a78.leftChild;
                                    this.setPosition("4.3.2.3.2.2");
                                    a78 = a83;
                                    a79 = a78;
                                }
                            }
                            this.setPosition("4.3.2.3.2");
                            this.setPosition("4.3.2.3.3");
                            com.cim.AIA.Node a84 = this.ptrNode;
                            this.replaceNode = a84;
                            this.setPosition("4.3.2.4.1");
                            if(a79 == a11)
                            {
                                this.setPosition("4.3.2.4.2");
                                java.util.Vector a85 = this.newLinks;
                                com.cim.AIA.Node a86 = this.ptrNode;
                                com.cim.AIA.Node a87 = a11.leftNode;
                                NewLinks a88 = new NewLinks(a86, false, a87);
                                a85.addElement((Object)a88);
                                this.setPosition("4.3.2.4.2.1");
                            }
                            else
                            {
                                com.cim.AIA.Node a89 = this.parentNode;
                                com.cim.AIA.Node a90 = a78.rightNode;
                                NewLinks a91 = new NewLinks(a89, false, a90);
                                java.util.Vector a92 = this.newLinks;
                                a92.addElement((Object)a91);
                                this.setPosition("4.3.2.4.1.1");
                                com.cim.AIA.Node a93 = this.ptrNode;
                                com.cim.AIA.Node a94 = a11.leftNode;
                                NewLinks a95 = new NewLinks(a93, false, a94);
                                java.util.Vector a96 = this.newLinks;
                                a96.addElement((Object)a95);
                                this.setPosition("4.3.2.4.1.2");
                                com.cim.AIA.Node a97 = this.ptrNode;
                                com.cim.AIA.Node a98 = a11.rightNode;
                                NewLinks a99 = new NewLinks(a97, true, a98);
                                java.util.Vector a100 = this.newLinks;
                                a100.addElement((Object)a99);
                                this.setPosition("4.3.2.4.1.3");
                            }
                            this.dataItemNode = null;
                            int i11 = a12.data;
                            label20: {
                                label19: {
                                    if(i11 != i4)
                                    {
                                        break label19;
                                    }
                                    this.setPosition("4.3.2.5.1");
                                    break label20;
                                }
                                BinarySearchTree$BinaryTree a101 = a12.leftChild;
                                label22: {
                                    label21: {
                                        if(a101 == null)
                                        {
                                            break label21;
                                        }
                                        BinarySearchTree$BinaryTree a102 = a12.leftChild;
                                        int i12 = a102.data;
                                        if(i4 == i12)
                                        {
                                            break label22;
                                        }
                                    }
                                    this.setPosition("4.3.2.5.1");
                                    this.setPosition("4.3.2.5.2");
                                    java.util.Vector a103 = this.newLinks;
                                    com.cim.AIA.Node a104 = a12.node;
                                    com.cim.AIA.Node a105 = this.ptrNode;
                                    NewLinks a106 = new NewLinks(a104, true, a105);
                                    a103.addElement((Object)a106);
                                    this.setPosition("4.3.2.5.3");
                                    break label20;
                                }
                                this.setPosition("4.3.2.5.1");
                                java.util.Vector a107 = this.newLinks;
                                com.cim.AIA.Node a108 = a12.node;
                                com.cim.AIA.Node a109 = this.ptrNode;
                                NewLinks a110 = new NewLinks(a108, false, a109);
                                a107.addElement((Object)a110);
                                this.setPosition("4.3.2.5.2");
                            }
                            java.util.Vector a111 = this.newLinks;
                            a111.removeAllElements();
                            if(a79 == a11)
                            {
                                com.cim.AIA.Node a112 = a11.leftNode;
                                a78.leftNode = a112;
                                BinarySearchTree$BinaryTree a113 = a11.leftChild;
                                a78.leftChild = a113;
                            }
                            else
                            {
                                BinarySearchTree$BinaryTree a114 = a78.rightChild;
                                a79.leftChild = a114;
                                com.cim.AIA.Node a115 = a78.rightNode;
                                a79.leftNode = a115;
                                com.cim.AIA.Node a116 = a11.leftNode;
                                a78.leftNode = a116;
                                com.cim.AIA.Node a117 = a11.rightNode;
                                a78.rightNode = a117;
                                BinarySearchTree$BinaryTree a118 = a11.leftChild;
                                a78.leftChild = a118;
                                BinarySearchTree$BinaryTree a119 = a11.rightChild;
                                a78.rightChild = a119;
                            }
                            int i13 = a12.data;
                            label23: {
                                if(i13 != i4)
                                {
                                    break label23;
                                }
                                this.root = a78;
                                BinarySearchTree$BinaryTree a120 = this.root;
                                if(a120 != null)
                                {
                                    BinarySearchTree$BinaryTree a121 = this.root;
                                    com.cim.AIA.Node a122 = this.ptrNode;
                                    a121.node = a122;
                                    BinarySearchTree$BinaryTree a123 = this.root;
                                    com.cim.AIA.Node a124 = a123.node;
                                    a124.setMarkersBelowValue(false);
                                    BinarySearchTree$BinaryTree a125 = this.root;
                                    com.cim.AIA.Node a126 = a125.node;
                                    String s1 = localization.Messages.getString("BinarySearchTree.69");
                                    com.cim.AIA.StringMarker a127 = new com.cim.AIA.StringMarker(s1);
                                    a126.addMarker(a127);
                                }
                                this.parentNode = null;
                                this.ptrNode = null;
                                this.replaceNode = null;
                                this.setPosition("4.3.2.5.1.1");
                                break label0;
                            }
                            BinarySearchTree$BinaryTree a128 = a12.leftChild;
                            label25: {
                                label24: {
                                    if(a128 == null)
                                    {
                                        break label24;
                                    }
                                    BinarySearchTree$BinaryTree a129 = a12.leftChild;
                                    int i14 = a129.data;
                                    if(i4 == i14)
                                    {
                                        break label25;
                                    }
                                }
                                a12.rightChild = a78;
                                com.cim.AIA.Node a130 = this.ptrNode;
                                a12.rightNode = a130;
                                this.parentNode = null;
                                this.ptrNode = null;
                                this.replaceNode = null;
                                this.setPosition("4.3.2.5.3.1");
                                break label0;
                            }
                            a12.leftChild = a78;
                            com.cim.AIA.Node a131 = this.ptrNode;
                            a12.leftNode = a131;
                            this.parentNode = null;
                            this.ptrNode = null;
                            this.replaceNode = null;
                            this.setPosition("4.3.2.5.2.1");
                        }
                    }
                }
                break label26;
            }
            DeleteSelection.setEnabled(false);
        }
    }
    
    protected java.util.Vector generateQuestions()
    {
        return null;
    }
    
    public com.cim.AIA.MultipleTween generateTweens(com.cim.AIA.Paintable a, Object a0, int i)
    {
        Object a1 = a;
        com.cim.AIA.MultipleTween a2 = new com.cim.AIA.MultipleTween((com.cim.AIA.Paintable)a1);
        int i0 = 0;
        while(true)
        {
            java.util.Vector a3 = this.moveRequests;
            int i1 = a3.size();
            if(i0 >= i1)
            {
                return a2;
            }
            else
            {
                java.util.Vector a4 = this.moveRequests;
                Object a5 = a4.elementAt(i0);
                MoveRequest dummy = (MoveRequest)a5;
                MoveRequest a6 = (MoveRequest)a5;
                com.cim.AIA.Node a7 = a6.getSource();
                com.cim.AIA.Node a8 = a6.getDest();
                java.awt.Point a9 = a7.getPosition();
                java.awt.Point a10 = a8.getPosition();
                com.cim.AIA.MoveTween a11 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)a1, (com.cim.AIA.Moveable)a7, a9, a10, false, i);
                a2.add((com.cim.AIA.Tween)a11);
                int i2 = i0 + 1;
                i0 = i2;
            }
        }
    }
    
    public com.cim.AIA.Node getDataItemNode()
    {
        com.cim.AIA.Node a = this.dataItemNode;
        return a;
    }
    
    public com.cim.AIA.Node getdeleteNode()
    {
        com.cim.AIA.Node a = null;
        com.cim.AIA.Node a0 = this.deleteNode;
        if(a0 != null)
        {
            int[] a1 = this.insertData;
            com.cim.AIA.Node a2 = this.deleteNode;
            int i = a2.getIdentifier();
            int i0 = a1[i];
            Integer a3 = new Integer(i0);
            com.cim.AIA.Node a4 = new com.cim.AIA.Node((Object)a3, 0);
            String s = localization.Messages.getString("BinarySearchTree.7");
            com.cim.AIA.StringMarker a5 = new com.cim.AIA.StringMarker(s);
            a4.addMarker(a5);
            a = a4;
        }
        else
        {
            a = null;
        }
        return a;
    }
    
    public com.cim.AIA.ExtendedHierarchyTree getHierarchyTree()
    {
        this.ptrTree = null;
        com.cim.AIA.ExtendedHierarchyTree a = new com.cim.AIA.ExtendedHierarchyTree();
        BinarySearchTree$BinaryTree a0 = this.root;
        com.cim.AIA.ExtendedHierarchyTree a1 = this.makeHierarchyTree(a, a0);
        com.cim.AIA.ExtendedHierarchyTree a2 = this.ptrTree;
        this.colorHierarchyTree(a2);
        return a1;
    }
    
    public com.cim.AIA.ElementArray getInsertElementArray()
    {
        int i = 0;
        while(true)
        {
            com.cim.AIA.ElementArray a = this.insertArray;
            int i0 = a.getSize();
            if(i >= i0)
            {
                com.cim.AIA.ElementArray a0 = this.insertArray;
                return a0;
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
                java.awt.Color a9 = BinarySearchTree.SEARCH_HIGHLIGHT_COLOR;
                a8.setHighlightColor(a9);
                com.cim.AIA.ElementArray a10 = this.insertArray;
                com.cim.AIA.Element a11 = a10.getElement(i);
                com.cim.AIA.Node dummy1 = (com.cim.AIA.Node)a11;
                com.cim.AIA.Node a12 = (com.cim.AIA.Node)a11;
                java.awt.Color a13 = BinarySearchTree.TEXT_COLOR;
                a12.setTextColor(a13);
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    public java.util.Vector getNewLinks()
    {
        java.util.Vector a = this.newLinks;
        return a;
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
    
    public com.cim.AIA.Node getReplaceNode()
    {
        com.cim.AIA.Node a = this.replaceNode;
        return a;
    }
    
    public com.cim.AIA.ElementArray getSearchElementArray()
    {
        int i = 0;
        while(true)
        {
            com.cim.AIA.ElementArray a = this.searchArray;
            int i0 = a.getSize();
            if(i >= i0)
            {
                com.cim.AIA.ElementArray a0 = this.searchArray;
                return a0;
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
                java.awt.Color a9 = BinarySearchTree.SEARCH_HIGHLIGHT_COLOR;
                a8.setHighlightColor(a9);
                com.cim.AIA.ElementArray a10 = this.searchArray;
                com.cim.AIA.Element a11 = a10.getElement(i);
                com.cim.AIA.Node dummy1 = (com.cim.AIA.Node)a11;
                com.cim.AIA.Node a12 = (com.cim.AIA.Node)a11;
                java.awt.Color a13 = BinarySearchTree.TEXT_COLOR;
                a12.setTextColor(a13);
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    protected boolean hasQuestions()
    {
        return false;
    }
    
    private void initialise()
    {
        this.parentNode = null;
        this.ptrNode = null;
        int i = this.isBackMode?1:0;
        if(i == 0)
        {
            this.deleteNode = null;
        }
        BinarySearchTree$BinaryTree a = this.root;
        this.resetTreeColor(a);
        java.util.Vector a0 = this.newLinks;
        a0.removeAllElements();
        com.cim.AIA.Node a1 = new com.cim.AIA.Node((Object)"", 0);
        this.nullNode = a1;
        com.cim.AIA.Node a2 = this.nullNode;
        java.awt.Color a3 = BinarySearchTree.TREE_NULL_COLOR;
        a2.setBackgroundColor(a3);
        com.cim.AIA.Node a4 = this.nullNode;
        java.awt.Color a5 = BinarySearchTree.TEXT_COLOR;
        a4.setTextColor(a5);
        com.cim.AIA.Node a6 = this.nullNode;
        com.cim.AIA.Node a7 = this.nullNode;
        int i0 = a7.getWidth();
        int i1 = i0 / 2;
        a6.setWidth(i1);
        com.cim.AIA.Node a8 = this.nullNode;
        com.cim.AIA.Node a9 = this.nullNode;
        int i2 = a9.getHeight();
        int i3 = i2 / 2;
        a8.setHeight(i3);
        NewBackButton a10 = BinarySearchTree.backButton;
        if(a10 != null)
        {
            NewBackButton a11 = BinarySearchTree.backButton;
            a11.switchOff(false);
        }
    }
    
    protected void initialiseMethods(String s, com.cim.AIA.MethodSelectable a)
    {
        Object a0 = a;
        String s0 = BinarySearchTree.BUILD_MODE_LABEL;
        String s1 = localization.Messages.getString("BinarySearchTree.91");
        com.cim.AIA.Logger a1 = this.getLogger();
        com.cim.AIA.BreakPoint a2 = this.getBreakPoint();
        com.cim.AIA.MethodSelection a3 = new com.cim.AIA.MethodSelection(s0, s, s1, "2.1s", a1, a2);
        String s2 = BinarySearchTree.SEARCH_MODE_LABEL;
        String s3 = localization.Messages.getString("BinarySearchTree.92");
        com.cim.AIA.Logger a4 = this.getLogger();
        com.cim.AIA.BreakPoint a5 = this.getBreakPoint();
        com.cim.AIA.MethodSelection a6 = new com.cim.AIA.MethodSelection(s2, s, s3, "3s", a4, a5);
        String s4 = BinarySearchTree.DELETE_MODE_LABEL;
        String s5 = localization.Messages.getString("BinarySearchTree.93");
        com.cim.AIA.Logger a7 = this.getLogger();
        com.cim.AIA.BreakPoint a8 = this.getBreakPoint();
        com.cim.AIA.MethodSelection a9 = new com.cim.AIA.MethodSelection(s4, s, s5, "4s", a7, a8);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelection(a3, true);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelection(a6, false);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelection(a9, false);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelectionListener((com.cim.AIA.MethodSelectionListener)this);
    }
    
    protected void insert()
    {
        int[] a = this.insertData;
        int i = a.length;
        {
            label1: {
                label0: {
                    if(i <= 0)
                    {
                        break label0;
                    }
                    int i0 = this.insertPos;
                    if(i0 == 0)
                    {
                        java.awt.Color[] a0 = this.insertDataColor;
                        java.awt.Color a1 = BinarySearchTree.INSERT_HIGHLIGHT_COLOR;
                        a0[0] = a1;
                        int[] a2 = this.insertData;
                        int i1 = a2[0];
                        BinarySearchTree$BinaryTree a3 = new BinarySearchTree$BinaryTree(this, i1, 0);
                        this.root = a3;
                        this.setPosition("1.1");
                        java.awt.Color[] a4 = this.insertDataColor;
                        java.awt.Color a5 = BinarySearchTree.INSERT_DONE_COLOR;
                        a4[0] = a5;
                        BinarySearchTree$BinaryTree a6 = this.root;
                        com.cim.AIA.Node a7 = a6.node;
                        java.awt.Color a8 = BinarySearchTree.TREE_ACTIVE_COLOR;
                        a7.setBackgroundColor(a8);
                        BinarySearchTree$BinaryTree a9 = this.root;
                        com.cim.AIA.Node a10 = a9.node;
                        a10.setMarkersBelowValue(false);
                        BinarySearchTree$BinaryTree a11 = this.root;
                        com.cim.AIA.Node a12 = a11.node;
                        String s = localization.Messages.getString("BinarySearchTree.118");
                        com.cim.AIA.StringMarker a13 = new com.cim.AIA.StringMarker(s);
                        a12.addMarker(a13);
                        int i2 = this.insertPos;
                        int i3 = i2 + 1;
                        this.insertPos = i3;
                    }
                    int i4 = this.insertPos;
                    int i5 = i4;
                    while(true)
                    {
                        int[] a14 = this.insertData;
                        int i6 = a14.length;
                        if(i5 >= i6)
                        {
                            break;
                        }
                        java.awt.Color[] a15 = this.insertDataColor;
                        java.awt.Color a16 = BinarySearchTree.INSERT_HIGHLIGHT_COLOR;
                        a15[i5] = a16;
                        this.setPosition("2");
                        BinarySearchTree$BinaryTree a17 = this.root;
                        BinarySearchTree$BinaryTree a18 = this.root;
                        com.cim.AIA.Node a19 = a18.node;
                        this.ptrNode = a19;
                        this.parentNode = a19;
                        this.setPosition("2.1.1.1");
                        BinarySearchTree$BinaryTree a20 = a17;
                        BinarySearchTree$BinaryTree a21 = a17;
                        while(true)
                        {
                            if(a20 == null)
                            {
                                break;
                            }
                            this.setPosition("2.1.2.1");
                            com.cim.AIA.Node a22 = this.ptrNode;
                            this.parentNode = a22;
                            this.setPosition("2.1.2.1.1");
                            this.setPosition("2.1.2.1.2");
                            int[] a23 = this.insertData;
                            int i7 = a23[i5];
                            int i8 = a20.data;
                            if(i7 >= i8)
                            {
                                this.setPosition("2.1.2.1.3");
                                com.cim.AIA.Node a24 = a20.rightNode;
                                this.ptrNode = a24;
                                BinarySearchTree$BinaryTree a25 = a20.rightChild;
                                this.setPosition("2.1.2.1.3.1");
                                a20 = a25;
                                a21 = a20;
                            }
                            else
                            {
                                com.cim.AIA.Node a26 = a20.leftNode;
                                this.ptrNode = a26;
                                BinarySearchTree$BinaryTree a27 = a20.leftChild;
                                this.setPosition("2.1.2.1.2.1");
                                a20 = a27;
                                a21 = a20;
                            }
                        }
                        this.setPosition("2.1.2.2");
                        this.setPosition("2.1.2.1");
                        this.ptrNode = null;
                        this.parentNode = null;
                        this.setPosition("2.1.3.1");
                        int i9 = this.enabled?1:0;
                        if(i9 == 0)
                        {
                            break label1;
                        }
                        int[] a28 = this.insertData;
                        int i10 = a28[i5];
                        int i11 = a21.data;
                        if(i10 >= i11)
                        {
                            this.setPosition("2.1.3.2");
                            int[] a29 = this.insertData;
                            int i12 = a29[i5];
                            BinarySearchTree$BinaryTree a30 = new BinarySearchTree$BinaryTree(this, i12, i5);
                            a21.rightChild = a30;
                            BinarySearchTree$BinaryTree a31 = a21.rightChild;
                            com.cim.AIA.Node a32 = a31.node;
                            a21.rightNode = a32;
                            this.setPosition("2.1.3.2.1");
                        }
                        else
                        {
                            int[] a33 = this.insertData;
                            int i13 = a33[i5];
                            BinarySearchTree$BinaryTree a34 = new BinarySearchTree$BinaryTree(this, i13, i5);
                            a21.leftChild = a34;
                            BinarySearchTree$BinaryTree a35 = a21.leftChild;
                            com.cim.AIA.Node a36 = a35.node;
                            a21.leftNode = a36;
                            this.setPosition("2.1.3.1.1");
                        }
                        BinarySearchTree$BinaryTree a37 = this.root;
                        this.resetTreeColor(a37);
                        java.awt.Color[] a38 = this.insertDataColor;
                        java.awt.Color a39 = BinarySearchTree.INSERT_DONE_COLOR;
                        a38[i5] = a39;
                        int i14 = this.insertPos;
                        int i15 = i14 + 1;
                        this.insertPos = i15;
                        int i16 = i5 + 1;
                        i5 = i16;
                    }
                }
            }
        }
    }
    
    public boolean isBuildMode()
    {
        int i = this.executingMode;
        int i0 = i != 11?0:1;
        return i0 != 0;
    }
    
    public boolean isDeleteMode()
    {
        int i = this.executingMode;
        int i0 = i != 13?0:1;
        return i0 != 0;
    }
    
    public boolean isSearchMode()
    {
        int i = this.executingMode;
        int i0 = i != 12?0:1;
        return i0 != 0;
    }
    
    private com.cim.AIA.ExtendedHierarchyTree makeHierarchyTree(com.cim.AIA.ExtendedHierarchyTree a, BinarySearchTree$BinaryTree a0)
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
            BinarySearchTree$BinaryTree a6 = a0.leftChild;
            label3: {
                label2: {
                    if(a6 == null)
                    {
                        break label2;
                    }
                    BinarySearchTree$BinaryTree a7 = a0.leftChild;
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
            BinarySearchTree$BinaryTree a13 = a0.rightChild;
            label5: {
                label4: {
                    if(a13 == null)
                    {
                        break label4;
                    }
                    BinarySearchTree$BinaryTree a14 = a0.rightChild;
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
    
    protected void makeQuestion(int i)
    {
    }
    
    protected void move(com.cim.AIA.Node a, com.cim.AIA.Node a0)
    {
        java.util.Vector a1 = this.moveRequests;
        MoveRequest a2 = new MoveRequest(a, a0);
        a1.addElement((Object)a2);
    }
    
    public void processMethodSelectionEvent(com.cim.AIA.MethodSelectionEvent a)
    {
        String s = BinarySearchTree.BUILD_MODE_LABEL;
        String s0 = a.getMethodName();
        int i = s.compareTo(s0);
        if(i == 0)
        {
            this.nextMode = 11;
        }
        String s1 = BinarySearchTree.SEARCH_MODE_LABEL;
        String s2 = a.getMethodName();
        int i0 = s1.compareTo(s2);
        if(i0 == 0)
        {
            this.nextMode = 12;
        }
        String s3 = BinarySearchTree.DELETE_MODE_LABEL;
        String s4 = a.getMethodName();
        int i1 = s3.compareTo(s4);
        if(i1 == 0)
        {
            this.nextMode = 13;
        }
    }
    
    public void reInitialise(Object a)
    {
        int[] dummy = (int[])a;
        int[] a0 = (int[])a;
        this.insertData = a0;
        int[] dummy0 = (int[])a;
        int[] a1 = (int[])a;
        this.searchData = a1;
        int i = this.executingMode;
        if(i == 11)
        {
            this.saveRoot = null;
        }
        BinarySearchTree$BinaryTree a2 = this.saveRoot;
        BinarySearchTree$BinaryTree a3 = this.saveTree(a2);
        this.root = a3;
        com.cim.AIA.Node a4 = this.saveDeleteNode;
        this.deleteNode = a4;
        BinarySearchTree$BinaryTree a5 = this.root;
        if(a5 != null)
        {
            BinarySearchTree$BinaryTree a6 = this.root;
            com.cim.AIA.Node a7 = a6.node;
            a7.setMarkersBelowValue(false);
            BinarySearchTree$BinaryTree a8 = this.root;
            com.cim.AIA.Node a9 = a8.node;
            String s = localization.Messages.getString("BinarySearchTree.8");
            com.cim.AIA.StringMarker a10 = new com.cim.AIA.StringMarker(s);
            a9.addMarker(a10);
        }
        this.insertPos = 0;
        int[] a11 = this.insertData;
        int i0 = a11.length;
        java.awt.Color[] a12 = new java.awt.Color[i0];
        this.insertDataColor = a12;
        int[] a13 = this.insertData;
        int i1 = a13.length;
        java.awt.Color[] a14 = new java.awt.Color[i1];
        this.searchDataColor = a14;
        int i2 = 0;
        while(true)
        {
            int[] a15 = this.insertData;
            int i3 = a15.length;
            if(i2 >= i3)
            {
                break;
            }
            else
            {
                java.awt.Color[] a16 = this.insertDataColor;
                java.awt.Color a17 = BinarySearchTree.INSERT_ACTIVE_COLOR;
                a16[i2] = a17;
                int i4 = i2 + 1;
                i2 = i4;
            }
        }
        com.cim.AIA.ElementArray a18 = new com.cim.AIA.ElementArray(0, 0);
        this.insertArray = a18;
        com.cim.AIA.ElementArray a19 = this.insertArray;
        a19.setColumGap(0);
        com.cim.AIA.ElementArray a20 = this.insertArray;
        a20.setElementWidth(20);
        int i5 = 0;
        while(true)
        {
            int[] a21 = this.insertData;
            int i6 = a21.length;
            if(i5 >= i6)
            {
                break;
            }
            else
            {
                int[] a22 = this.insertData;
                int i7 = a22[i5];
                Integer a23 = new Integer(i7);
                com.cim.AIA.Node a24 = new com.cim.AIA.Node((Object)a23, i5);
                java.awt.Color[] a25 = this.insertDataColor;
                java.awt.Color a26 = a25[i5];
                a24.setBackgroundColor(a26);
                java.awt.Color a27 = BinarySearchTree.INSERT_HIGHLIGHT_COLOR;
                a24.setHighlightColor(a27);
                java.awt.Color a28 = BinarySearchTree.TEXT_COLOR;
                a24.setTextColor(a28);
                com.cim.AIA.ElementArray a29 = this.insertArray;
                a29.setValue(i5, (com.cim.AIA.Element)a24);
                int i8 = i5 + 1;
                i5 = i8;
            }
        }
        int i9 = 0;
        while(true)
        {
            int[] a30 = this.searchData;
            int i10 = a30.length;
            if(i9 >= i10)
            {
                break;
            }
            else
            {
                java.awt.Color[] a31 = this.searchDataColor;
                java.awt.Color a32 = BinarySearchTree.SEARCH_ACTIVE_COLOR;
                a31[i9] = a32;
                int i11 = i9 + 1;
                i9 = i11;
            }
        }
        int i12 = this.isBackMode?1:0;
        label1: {
            label0: {
                if(i12 == 0)
                {
                    break label0;
                }
                int i13 = 0;
                while(true)
                {
                    int[] a33 = this.searchData;
                    int i14 = a33.length;
                    if(i13 >= i14)
                    {
                        break label1;
                    }
                    else
                    {
                        com.cim.AIA.ElementArray a34 = this.searchArray;
                        com.cim.AIA.Element a35 = a34.getElement(i13);
                        com.cim.AIA.Node dummy1 = (com.cim.AIA.Node)a35;
                        com.cim.AIA.Node a36 = (com.cim.AIA.Node)a35;
                        a36.removeAllMarkers();
                        int i15 = i13 + 1;
                        i13 = i15;
                    }
                }
            }
            com.cim.AIA.ElementArray a37 = new com.cim.AIA.ElementArray(0, 0);
            this.searchArray = a37;
            com.cim.AIA.ElementArray a38 = this.searchArray;
            a38.setColumGap(0);
            com.cim.AIA.ElementArray a39 = this.searchArray;
            a39.setElementWidth(20);
            int i16 = 0;
            while(true)
            {
                int[] a40 = this.searchData;
                int i17 = a40.length;
                if(i16 >= i17)
                {
                    break;
                }
                else
                {
                    int[] a41 = this.searchData;
                    int i18 = a41[i16];
                    Integer a42 = new Integer(i18);
                    com.cim.AIA.Node a43 = new com.cim.AIA.Node((Object)a42, i16);
                    java.awt.Color[] a44 = this.searchDataColor;
                    java.awt.Color a45 = a44[i16];
                    a43.setBackgroundColor(a45);
                    java.awt.Color a46 = BinarySearchTree.SEARCH_HIGHLIGHT_COLOR;
                    a43.setHighlightColor(a46);
                    java.awt.Color a47 = BinarySearchTree.TEXT_COLOR;
                    a43.setTextColor(a47);
                    com.cim.AIA.ElementArray a48 = this.searchArray;
                    a48.setValue(i16, (com.cim.AIA.Element)a43);
                    int i19 = i16 + 1;
                    i16 = i19;
                }
            }
        }
        this.initialise();
    }
    
    protected void removeAllAnimationRequests()
    {
        java.util.Vector a = this.moveRequests;
        a.removeAllElements();
    }
    
    protected void removeAllQuestions()
    {
    }
    
    private void resetTreeColor(BinarySearchTree$BinaryTree a)
    {
        if(a != null)
        {
            com.cim.AIA.Node a0 = a.node;
            java.awt.Color a1 = BinarySearchTree.TREE_ACTIVE_COLOR;
            a0.setBackgroundColor(a1);
            com.cim.AIA.Node a2 = a.node;
            java.awt.Color a3 = BinarySearchTree.TREE_RING_COLOR;
            a2.setRingColor(a3);
            com.cim.AIA.Node a4 = a.node;
            java.awt.Color a5 = BinarySearchTree.TEXT_COLOR;
            a4.setTextColor(a5);
            BinarySearchTree$BinaryTree a6 = a.leftChild;
            this.resetTreeColor(a6);
            BinarySearchTree$BinaryTree a7 = a.rightChild;
            this.resetTreeColor(a7);
        }
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
            this.setPosition("0");
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
                label4: {
                    if(i1 != 12)
                    {
                        break label4;
                    }
                    this.search();
                    break label3;
                }
                int i2 = this.executingMode;
                if(i2 == 13)
                {
                    this.delete();
                }
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
    
    protected BinarySearchTree$BinaryTree saveTree(BinarySearchTree$BinaryTree a)
    {
        BinarySearchTree$BinaryTree a0 = null;
        label1: {
            label0: {
                if(a != null)
                {
                    break label0;
                }
                a0 = null;
                break label1;
            }
            com.cim.AIA.Node a1 = a.node;
            int i = a1.getIdentifier();
            int i0 = a.data;
            BinarySearchTree$BinaryTree a2 = new BinarySearchTree$BinaryTree(this, i0, i);
            com.cim.AIA.Node a3 = this.deleteNode;
            com.cim.AIA.Node a4 = a.node;
            if(a3 == a4)
            {
                com.cim.AIA.Node a5 = a2.node;
                this.saveDeleteNode = a5;
            }
            BinarySearchTree$BinaryTree a6 = a.leftChild;
            BinarySearchTree$BinaryTree a7 = this.saveTree(a6);
            a2.leftChild = a7;
            BinarySearchTree$BinaryTree a8 = a.rightChild;
            BinarySearchTree$BinaryTree a9 = this.saveTree(a8);
            a2.rightChild = a9;
            BinarySearchTree$BinaryTree a10 = a2.leftChild;
            if(a10 != null)
            {
                BinarySearchTree$BinaryTree a11 = a2.leftChild;
                com.cim.AIA.Node a12 = a11.node;
                a2.leftNode = a12;
            }
            BinarySearchTree$BinaryTree a13 = a2.rightChild;
            if(a13 != null)
            {
                BinarySearchTree$BinaryTree a14 = a2.rightChild;
                com.cim.AIA.Node a15 = a14.node;
                a2.rightNode = a15;
            }
            a0 = a2;
        }
        return a0;
    }
    
    protected void search()
    {
        this.makeQuestion(13);
        this.setPosition("3.0");
        BinarySearchTree$BinaryTree a = this.root;
        label7: {
            label5: {
                label6: {
                    label3: {
                        label2: {
                            label4: {
                                label0: {
                                    if(a == null)
                                    {
                                        break label0;
                                    }
                                    int i = this.isBackMode?1:0;
                                    label1: {
                                        if(i != 0)
                                        {
                                            break label1;
                                        }
                                        int i0 = this.enabled?1:0;
                                        if(i0 == 0)
                                        {
                                            break label2;
                                        }
                                        com.cim.AIA.SearchSelection.setEnabled(true);
                                        while(true)
                                        {
                                            com.cim.AIA.Node a0 = com.cim.AIA.SearchSelection.getNode();
                                            if(a0 != null)
                                            {
                                                break;
                                            }
                                            int i1 = this.enabled?1:0;
                                            if(i1 == 0)
                                            {
                                                break label3;
                                            }
                                            try
                                            {
                                                Thread.sleep(1000L);
                                            }
                                            catch(Exception ignoredException)
                                            {
                                            }
                                        }
                                        com.cim.AIA.Node a1 = com.cim.AIA.SearchSelection.getNode();
                                        this.searchNode = a1;
                                        com.cim.AIA.SearchSelection.setEnabled(false);
                                        int i2 = this.enabled?1:0;
                                        if(i2 == 0)
                                        {
                                            break label4;
                                        }
                                    }
                                    java.awt.Color[] a2 = this.searchDataColor;
                                    com.cim.AIA.Node a3 = this.searchNode;
                                    int i3 = a3.getIdentifier();
                                    java.awt.Color a4 = BinarySearchTree.SEARCH_HIGHLIGHT_COLOR;
                                    a2[i3] = a4;
                                    com.cim.AIA.Node a5 = this.searchNode;
                                    String s = a5.getDisplayString();
                                    Integer a6 = Integer.valueOf(s);
                                    int i4 = a6.intValue();
                                    BinarySearchTree$BinaryTree a7 = this.root;
                                    BinarySearchTree$BinaryTree a8 = this.root;
                                    com.cim.AIA.Node a9 = a8.node;
                                    this.ptrNode = a9;
                                    this.setPosition("3.1.1");
                                    BinarySearchTree$BinaryTree a10 = a7;
                                    while(true)
                                    {
                                        if(a10 == null)
                                        {
                                            break;
                                        }
                                        this.setPosition("3.2");
                                        this.setPosition("3.2.1");
                                        int i5 = a10.data;
                                        if(i5 == i4)
                                        {
                                            break label5;
                                        }
                                        this.setPosition("3.2.2.1.2");
                                        int i6 = a10.data;
                                        if(i4 >= i6)
                                        {
                                            this.setPosition("3.2.2.1.3");
                                            com.cim.AIA.Node a11 = a10.rightNode;
                                            this.ptrNode = a11;
                                            BinarySearchTree$BinaryTree a12 = a10.rightChild;
                                            this.setPosition("3.2.2.1.3.1");
                                            a10 = a12;
                                        }
                                        else
                                        {
                                            com.cim.AIA.Node a13 = a10.leftNode;
                                            this.ptrNode = a13;
                                            BinarySearchTree$BinaryTree a14 = a10.leftChild;
                                            this.setPosition("3.2.2.1.2.1");
                                            a10 = a14;
                                        }
                                    }
                                    this.setPosition("3.2");
                                    BinarySearchTree$BinaryTree a15 = this.root;
                                    this.resetTreeColor(a15);
                                    int i7 = this.enabled?1:0;
                                    if(i7 == 0)
                                    {
                                        break label6;
                                    }
                                    com.cim.AIA.Node a16 = this.searchNode;
                                    com.cim.AIA.StringMarker a17 = new com.cim.AIA.StringMarker("N");
                                    a16.addMarker(a17);
                                    com.cim.AIA.Node a18 = this.searchNode;
                                    com.cim.AIA.StringMarker a19 = new com.cim.AIA.StringMarker("o");
                                    a18.addMarker(a19);
                                    com.cim.AIA.Node a20 = this.searchNode;
                                    com.cim.AIA.StringMarker a21 = new com.cim.AIA.StringMarker("t");
                                    a20.addMarker(a21);
                                    com.cim.AIA.Node a22 = this.searchNode;
                                    com.cim.AIA.StringMarker a23 = new com.cim.AIA.StringMarker(" ");
                                    a22.addMarker(a23);
                                    com.cim.AIA.Node a24 = this.searchNode;
                                    com.cim.AIA.StringMarker a25 = new com.cim.AIA.StringMarker("F");
                                    a24.addMarker(a25);
                                    com.cim.AIA.Node a26 = this.searchNode;
                                    com.cim.AIA.StringMarker a27 = new com.cim.AIA.StringMarker("o");
                                    a26.addMarker(a27);
                                    com.cim.AIA.Node a28 = this.searchNode;
                                    com.cim.AIA.StringMarker a29 = new com.cim.AIA.StringMarker("u");
                                    a28.addMarker(a29);
                                    com.cim.AIA.Node a30 = this.searchNode;
                                    com.cim.AIA.StringMarker a31 = new com.cim.AIA.StringMarker("n");
                                    a30.addMarker(a31);
                                    com.cim.AIA.Node a32 = this.searchNode;
                                    com.cim.AIA.StringMarker a33 = new com.cim.AIA.StringMarker("d");
                                    a32.addMarker(a33);
                                    java.awt.Color[] a34 = this.searchDataColor;
                                    com.cim.AIA.Node a35 = this.searchNode;
                                    int i8 = a35.getIdentifier();
                                    java.awt.Color a36 = BinarySearchTree.SEARCH_DONE_COLOR;
                                    a34[i8] = a36;
                                    this.setPosition("3.3.3");
                                }
                            }
                        }
                        break label7;
                    }
                    com.cim.AIA.SearchSelection.setEnabled(false);
                    break label7;
                }
                java.awt.Color[] a37 = this.searchDataColor;
                com.cim.AIA.Node a38 = this.searchNode;
                int i9 = a38.getIdentifier();
                java.awt.Color a39 = BinarySearchTree.SEARCH_ACTIVE_COLOR;
                a37[i9] = a39;
                break label7;
            }
            BinarySearchTree$BinaryTree a40 = this.root;
            this.resetTreeColor(a40);
            int i10 = this.enabled?1:0;
            if(i10 != 0)
            {
                com.cim.AIA.Node a41 = this.searchNode;
                com.cim.AIA.StringMarker a42 = new com.cim.AIA.StringMarker("F");
                a41.addMarker(a42);
                com.cim.AIA.Node a43 = this.searchNode;
                com.cim.AIA.StringMarker a44 = new com.cim.AIA.StringMarker("o");
                a43.addMarker(a44);
                com.cim.AIA.Node a45 = this.searchNode;
                com.cim.AIA.StringMarker a46 = new com.cim.AIA.StringMarker("u");
                a45.addMarker(a46);
                com.cim.AIA.Node a47 = this.searchNode;
                com.cim.AIA.StringMarker a48 = new com.cim.AIA.StringMarker("n");
                a47.addMarker(a48);
                com.cim.AIA.Node a49 = this.searchNode;
                com.cim.AIA.StringMarker a50 = new com.cim.AIA.StringMarker("d");
                a49.addMarker(a50);
                java.awt.Color[] a51 = this.searchDataColor;
                com.cim.AIA.Node a52 = this.searchNode;
                int i11 = a52.getIdentifier();
                java.awt.Color a53 = BinarySearchTree.SEARCH_DONE_COLOR;
                a51[i11] = a53;
                this.setPosition("3.2.1.1");
            }
            else
            {
                java.awt.Color[] a54 = this.searchDataColor;
                com.cim.AIA.Node a55 = this.searchNode;
                int i12 = a55.getIdentifier();
                java.awt.Color a56 = BinarySearchTree.SEARCH_ACTIVE_COLOR;
                a54[i12] = a56;
            }
        }
    }
    
    protected void setEnabled(boolean b)
    {
        this.enabled = b;
    }
    
    protected void storeState(boolean b)
    {
        int i = this.executingMode;
        if(i != 13)
        {
            BinarySearchTree$BinaryTree a = this.root;
            BinarySearchTree$BinaryTree a0 = this.saveTree(a);
            this.saveRoot = a0;
            BinarySearchTree$BinaryTree a1 = this.saveRoot;
            com.cim.AIA.Node a2 = a1.node;
            a2.setMarkersBelowValue(false);
            BinarySearchTree$BinaryTree a3 = this.saveRoot;
            com.cim.AIA.Node a4 = a3.node;
            String s = localization.Messages.getString("BinarySearchTree.117");
            com.cim.AIA.StringMarker a5 = new com.cim.AIA.StringMarker(s);
            a4.addMarker(a5);
            com.cim.AIA.Node a6 = this.saveDeleteNode;
            this.deleteNode = a6;
        }
        else
        {
            BinarySearchTree$BinaryTree a7 = this.saveRoot;
            com.cim.AIA.Node a8 = a7.node;
            a8.setMarkersBelowValue(false);
            BinarySearchTree$BinaryTree a9 = this.saveRoot;
            com.cim.AIA.Node a10 = a9.node;
            String s0 = localization.Messages.getString("BinarySearchTree.116");
            com.cim.AIA.StringMarker a11 = new com.cim.AIA.StringMarker(s0);
            a10.addMarker(a11);
            com.cim.AIA.Node a12 = this.saveDeleteNode;
            this.deleteNode = a12;
        }
    }
    
    static java.awt.Color access$000()
    {
        java.awt.Color a = BinarySearchTree.TREE_HIGHLIGHT_COLOR;
        return a;
    }
    
    static java.awt.Color access$100()
    {
        java.awt.Color a = BinarySearchTree.TEXT_COLOR;
        return a;
    }
    
    static java.awt.Color access$200()
    {
        java.awt.Color a = BinarySearchTree.PATH_COLOR;
        return a;
    }
    
    static java.awt.Color access$300()
    {
        java.awt.Color a = BinarySearchTree.TREE_NULL_COLOR;
        return a;
    }
    
    static
    {
        java.awt.Color a = java.awt.Color.black;
        BinarySearchTree.TEXT_COLOR = a;
        java.awt.Color a0 = new java.awt.Color(0, 255, 0);
        BinarySearchTree.INSERT_HIGHLIGHT_COLOR = a0;
        java.awt.Color a1 = new java.awt.Color(64, 191, 64);
        BinarySearchTree.INSERT_ACTIVE_COLOR = a1;
        java.awt.Color a2 = new java.awt.Color(96, 127, 96);
        BinarySearchTree.INSERT_DONE_COLOR = a2;
        java.awt.Color a3 = new java.awt.Color(64, 255, 255);
        BinarySearchTree.SEARCH_HIGHLIGHT_COLOR = a3;
        java.awt.Color a4 = new java.awt.Color(64, 191, 191);
        BinarySearchTree.SEARCH_ACTIVE_COLOR = a4;
        java.awt.Color a5 = new java.awt.Color(0, 127, 127);
        BinarySearchTree.SEARCH_DONE_COLOR = a5;
        java.awt.Color a6 = new java.awt.Color(255, 255, 0);
        BinarySearchTree.TREE_HIGHLIGHT_COLOR = a6;
        java.awt.Color a7 = new java.awt.Color(191, 191, 64);
        BinarySearchTree.TREE_ACTIVE_COLOR = a7;
        java.awt.Color a8 = java.awt.Color.black;
        BinarySearchTree.TREE_RING_COLOR = a8;
        java.awt.Color a9 = java.awt.Color.blue;
        BinarySearchTree.TREE_NULL_COLOR = a9;
        java.awt.Color a10 = java.awt.Color.red;
        BinarySearchTree.PATH_COLOR = a10;
        String s = localization.Messages.getString("BinarySearchTree.0");
        BinarySearchTree.BUILD_MODE_LABEL = s;
        String s0 = localization.Messages.getString("BinarySearchTree.1");
        BinarySearchTree.SEARCH_MODE_LABEL = s0;
        String s1 = localization.Messages.getString("BinarySearchTree.2");
        BinarySearchTree.DELETE_MODE_LABEL = s1;
        String s2 = localization.Messages.getString("BinarySearchTree.3");
        BinarySearchTree.PARENT_LABEL = s2;
        String s3 = localization.Messages.getString("BinarySearchTree.4");
        BinarySearchTree.PTR_LABEL = s3;
        String s4 = localization.Messages.getString("BinarySearchTree.5");
        BinarySearchTree.DATAITEMPTR_LABEL = s4;
        String s5 = localization.Messages.getString("BinarySearchTree.6");
        BinarySearchTree.REPLACEMENT_LABEL = s5;
    }
}