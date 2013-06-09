public class PatriciaTree extends com.cim.AIA.Algorithm implements com.cim.AIA.MethodSelectionListener, com.cim.AIA.ControlListener {
    final protected static int BUILD_MODE = 11;
    final protected static int SEARCH_MODE = 12;
    final private static String BUILD_MODE_LABEL;
    final private static String SEARCH_MODE_LABEL;
    private static Boolean isHighlightLoop;
    final private static int DEFAULT_ELEMENT_WIDTH = 20;
    private int executingMode;
    private int nextMode;
    private int mostSignificantBit;
    private com.cim.AIA.ElementArray insertData;
    private com.cim.AIA.ElementArray newInsertData;
    private com.cim.AIA.ElementArray searchData;
    private com.cim.AIA.DigitalElementArray dataItemBitArray;
    private com.cim.AIA.DigitalElementArray compareDataBitArray;
    private com.cim.AIA.Node compKeyNode;
    private com.cim.AIA.Node keyFoundNode;
    private PatriciaNode head;
    private PatriciaNode lastNode;
    private PatriciaNode currentSearchTree;
    private PatriciaTree$Path currentPath;
    private PatriciaNode currentInsertTree;
    private Integer differentiatingBit;
    private Integer currentBit;
    private Boolean isFollowedUpLink;
    private java.util.Vector allEndNodes;
    
    public PatriciaTree(com.cim.AIA.AlgorithmThread a, Object a0)
    {
        super(a, a0);
        this.executingMode = 11;
        int i = this.executingMode;
        this.nextMode = i;
        this.mostSignificantBit = 0;
        com.cim.AIA.ElementArray a1 = new com.cim.AIA.ElementArray(0, 0);
        this.insertData = a1;
        com.cim.AIA.ElementArray a2 = this.insertData;
        a2.setColumGap(0);
        com.cim.AIA.ElementArray a3 = this.insertData;
        a3.setElementWidth(20);
        int i0 = 0;
        while(true)
        {
            int[] dummy = (int[])a0;
            int[] a4 = (int[])a0;
            int i1 = a4.length;
            if(i0 >= i1)
            {
                break;
            }
            else
            {
                com.cim.AIA.ElementArray a5 = this.insertData;
                int[] dummy0 = (int[])a0;
                int[] a6 = (int[])a0;
                int i2 = a6[i0];
                Integer a7 = new Integer(i2);
                com.cim.AIA.Node a8 = new com.cim.AIA.Node((Object)a7, i0);
                a5.add((Object)a8, i0);
                com.cim.AIA.ElementArray a9 = this.insertData;
                com.cim.AIA.Element a10 = a9.getElement(i0);
                com.cim.AIA.Node dummy1 = (com.cim.AIA.Node)a10;
                com.cim.AIA.Node a11 = (com.cim.AIA.Node)a10;
                java.awt.Color a12 = PatriciaTreeColors.INSERT_ACTIVE_COLOR;
                a11.setBackgroundColor(a12);
                int i3 = i0 + 1;
                i0 = i3;
            }
        }
        com.cim.AIA.ElementArray a13 = this.insertData;
        this.newInsertData = a13;
        java.util.Random a14 = new java.util.Random();
        com.cim.AIA.ElementArray a15 = new com.cim.AIA.ElementArray(0, 0);
        this.searchData = a15;
        com.cim.AIA.ElementArray a16 = this.searchData;
        a16.setColumGap(0);
        com.cim.AIA.ElementArray a17 = this.searchData;
        a17.setElementWidth(20);
        int i4 = 0;
        while(true)
        {
            int[] dummy2 = (int[])a0;
            int[] a18 = (int[])a0;
            int i5 = a18.length;
            if(i4 >= i5)
            {
                PatriciaNode a19 = new PatriciaNode();
                this.head = a19;
                PatriciaNode a20 = this.head;
                PatriciaNode a21 = this.head;
                a20.setLeft(a21);
                PatriciaNode a22 = this.head;
                PatriciaNode a23 = this.head;
                a22.setRight(a23);
                return;
            }
            int i6 = i4 % 2;
            if(i6 != 0)
            {
                com.cim.AIA.ElementArray a24 = this.searchData;
                int[] dummy3 = (int[])a0;
                int[] a25 = (int[])a0;
                int i7 = a25[i4];
                Integer a26 = new Integer(i7);
                com.cim.AIA.Node a27 = new com.cim.AIA.Node((Object)a26, i4);
                a24.add((Object)a27, i4);
            }
            else
            {
                com.cim.AIA.ElementArray a28 = this.searchData;
                int i8 = a14.nextInt();
                int i9 = Math.abs(i8);
                int i10 = i9 % 98;
                int i11 = i10 + 1;
                Integer a29 = new Integer(i11);
                com.cim.AIA.Node a30 = new com.cim.AIA.Node((Object)a29, i4);
                a28.add((Object)a30, i4);
            }
            com.cim.AIA.ElementArray a31 = this.searchData;
            com.cim.AIA.Element a32 = a31.getElement(i4);
            com.cim.AIA.Node dummy4 = (com.cim.AIA.Node)a32;
            com.cim.AIA.Node a33 = (com.cim.AIA.Node)a32;
            java.awt.Color a34 = PatriciaTreeColors.SEARCH_ACTIVE_COLOR;
            a33.setBackgroundColor(a34);
            int i12 = i4 + 1;
            i4 = i12;
        }
    }
    
    public com.cim.AIA.HierarchyTree buildHierarchyTree(PatriciaNode a, java.awt.Color a0)
    {
        com.cim.AIA.Node a1 = a.getBody();
        java.awt.Color a2 = PatriciaTreeColors.DEFAULT_RING_COLOR;
        a1.setRingColor(a2);
        com.cim.AIA.Node a3 = a.getBody();
        java.awt.Color a4 = PatriciaTreeColors.DEFAULT_TEXT_COLOR;
        a3.setTextColor(a4);
        com.cim.AIA.HierarchyTree a5 = new com.cim.AIA.HierarchyTree();
        com.cim.AIA.Line a6 = a5.getLine();
        a6.showArrowHead(false);
        com.cim.AIA.Node a7 = a.getBody();
        a5.add(a7);
        PatriciaTreeDataItem a8 = a.getDataItem();
        com.cim.AIA.Node a9 = a8.getNode();
        a5.add(a9);
        a.setHierarchyTree(a5);
        PatriciaNode a10 = a.getLeft();
        int i = a10.getBit();
        int i0 = a.getBit();
        label1: {
            label0: {
                if(i <= i0)
                {
                    break label0;
                }
                PatriciaNode a11 = a.getLeft();
                com.cim.AIA.HierarchyTree a12 = this.buildHierarchyTree(a11, a0);
                a5.addChild((com.cim.AIA.Tree)a12);
                break label1;
            }
            PatriciaNode a13 = a.getRight();
            int i1 = a13.getBit();
            int i2 = a.getBit();
            if(i1 <= i2)
            {
                com.cim.AIA.HierarchyTree a14 = new com.cim.AIA.HierarchyTree();
                a5.addChild((com.cim.AIA.Tree)a14);
            }
            else
            {
                com.cim.AIA.HierarchyTree a15 = new com.cim.AIA.HierarchyTree();
                com.cim.AIA.Node a16 = new com.cim.AIA.Node((Object)"", 0);
                a16.setIsVisible(false);
                a15.add(a16);
                a5.addChild((com.cim.AIA.Tree)a15);
            }
        }
        PatriciaNode a17 = a.getRight();
        int i3 = a17.getBit();
        int i4 = a.getBit();
        label3: {
            label2: {
                if(i3 <= i4)
                {
                    break label2;
                }
                PatriciaNode a18 = a.getRight();
                com.cim.AIA.HierarchyTree a19 = this.buildHierarchyTree(a18, a0);
                a5.addChild((com.cim.AIA.Tree)a19);
                break label3;
            }
            PatriciaNode a20 = a.getLeft();
            int i5 = a20.getBit();
            int i6 = a.getBit();
            if(i5 <= i6)
            {
                com.cim.AIA.HierarchyTree a21 = new com.cim.AIA.HierarchyTree();
                a5.addChild((com.cim.AIA.Tree)a21);
            }
            else
            {
                com.cim.AIA.HierarchyTree a22 = new com.cim.AIA.HierarchyTree();
                com.cim.AIA.Node a23 = new com.cim.AIA.Node((Object)"", 0);
                a23.setIsVisible(false);
                a22.add(a23);
                a5.addChild((com.cim.AIA.Tree)a22);
            }
        }
        return a5;
    }
    
    protected void changeData(Object a)
    {
        com.cim.AIA.ElementArray a0 = new com.cim.AIA.ElementArray(0, 0);
        this.newInsertData = a0;
        com.cim.AIA.ElementArray a1 = this.newInsertData;
        a1.setColumGap(0);
        com.cim.AIA.ElementArray a2 = this.newInsertData;
        a2.setElementWidth(20);
        int i = 0;
        while(true)
        {
            int[] dummy = (int[])a;
            int[] a3 = (int[])a;
            int i0 = a3.length;
            if(i >= i0)
            {
                return;
            }
            else
            {
                com.cim.AIA.ElementArray a4 = this.newInsertData;
                int[] dummy0 = (int[])a;
                int[] a5 = (int[])a;
                int i1 = a5[i];
                Integer a6 = new Integer(i1);
                com.cim.AIA.Node a7 = new com.cim.AIA.Node((Object)a6, i);
                a4.add((Object)a7, i);
                com.cim.AIA.ElementArray a8 = this.newInsertData;
                com.cim.AIA.Element a9 = a8.getElement(i);
                com.cim.AIA.Node dummy1 = (com.cim.AIA.Node)a9;
                com.cim.AIA.Node a10 = (com.cim.AIA.Node)a9;
                java.awt.Color a11 = PatriciaTreeColors.INSERT_ACTIVE_COLOR;
                a10.setBackgroundColor(a11);
                int i2 = i + 1;
                i = i2;
            }
        }
    }
    
    private void clearPointers()
    {
        this.isFollowedUpLink = null;
        this.currentPath = null;
        this.currentBit = null;
        this.dataItemBitArray = null;
        this.compareDataBitArray = null;
        com.cim.AIA.Node a = this.compKeyNode;
        if(a != null)
        {
            com.cim.AIA.Node a0 = this.compKeyNode;
            a0.unHighlight();
            this.compKeyNode = null;
        }
        this.keyFoundNode = null;
        this.differentiatingBit = null;
    }
    
    protected void clearState()
    {
        PatriciaNode a = new PatriciaNode();
        this.head = a;
        PatriciaNode a0 = this.head;
        PatriciaNode a1 = this.head;
        a0.setLeft(a1);
        PatriciaNode a2 = this.head;
        PatriciaNode a3 = this.head;
        a2.setRight(a3);
    }
    
    private void colorHierarchyTree(com.cim.AIA.HierarchyTree a, PatriciaTree$Path a0)
    {
        PatriciaTree.isHighlightLoop = null;
        label1: {
            com.cim.AIA.HierarchyTree a1 = null;
            label0: {
                if(a != null)
                {
                    break label0;
                }
                break label1;
            }
            com.cim.AIA.Line a2 = a.getLine();
            a2.showAsThick(true);
            java.awt.Color a3 = PatriciaTreeColors.SEARCH_PATH_COLOR;
            a.setParentLineColor(a3);
            com.cim.AIA.Node a4 = a.getFirstElement();
            if(a4 != null)
            {
                com.cim.AIA.Node a5 = a.getFirstElement();
                java.awt.Color a6 = PatriciaTreeColors.SEARCH_PATH_COLOR;
                a5.setRingColor(a6);
                com.cim.AIA.Node a7 = a.getFirstElement();
                java.awt.Color a8 = PatriciaTreeColors.SEARCH_PATH_COLOR;
                a7.setTextColor(a8);
            }
            label2: {
                if(a0 != null)
                {
                    break label2;
                }
                break label1;
            }
            int i = a0.isLeft?1:0;
            if(i == 0)
            {
                com.cim.AIA.HierarchyTree a9 = a.getRightChild();
                a1 = a9;
            }
            else
            {
                com.cim.AIA.HierarchyTree a10 = a.getLeftChild();
                a1 = a10;
            }
            PatriciaTree$Path a11 = a0.next;
            this.colorHierarchyTree(a1, a11);
            label3: {
                if(a1 == null)
                {
                    break label3;
                }
                int i0 = a1.getNumberOfChildren();
                if(i0 == 0)
                {
                    int i1 = a0.isLeft?1:0;
                    Boolean a12 = new Boolean(i1 != 0);
                    PatriciaTree.isHighlightLoop = a12;
                }
            }
        }
    }
    
    public void controlBack(com.cim.AIA.ControlEvent a)
    {
        int i = this.executingMode;
        if(i == 11)
        {
            this.clearState();
        }
    }
    
    public void controlOther(com.cim.AIA.ControlEvent a)
    {
    }
    
    public void controlPause(com.cim.AIA.ControlEvent a)
    {
    }
    
    public void controlReset(com.cim.AIA.ControlEvent a)
    {
    }
    
    public void controlRestart(com.cim.AIA.ControlEvent a)
    {
    }
    
    public void controlRun(com.cim.AIA.ControlEvent a)
    {
    }
    
    public void controlStep(com.cim.AIA.ControlEvent a)
    {
    }
    
    public void findAllEndNodes(PatriciaNode a)
    {
        com.cim.AIA.Node[] a0 = new com.cim.AIA.Node[3];
        com.cim.AIA.Node a1 = a.getBody();
        a0[0] = a1;
        int i = a.getBit();
        PatriciaNode a2 = a.getLeft();
        int i0 = a2.getBit();
        if(i < i0)
        {
            PatriciaNode a3 = a.getLeft();
            this.findAllEndNodes(a3);
        }
        else
        {
            PatriciaNode a4 = a.getLeft();
            com.cim.AIA.Node a5 = a4.getBody();
            a0[1] = a5;
        }
        int i1 = a.getBit();
        PatriciaNode a6 = a.getRight();
        int i2 = a6.getBit();
        if(i1 < i2)
        {
            PatriciaNode a7 = a.getRight();
            this.findAllEndNodes(a7);
        }
        else
        {
            PatriciaNode a8 = a.getRight();
            com.cim.AIA.Node a9 = a8.getBody();
            a0[2] = a9;
        }
        int i3 = a.getBit();
        PatriciaNode a10 = a.getLeft();
        int i4 = a10.getBit();
        label1: {
            label0: {
                if(i3 >= i4)
                {
                    break label0;
                }
                int i5 = a.getBit();
                PatriciaNode a11 = a.getRight();
                int i6 = a11.getBit();
                if(i5 < i6)
                {
                    break label1;
                }
            }
            java.util.Vector a12 = this.allEndNodes;
            a12.addElement((Object)a0);
        }
    }
    
    protected java.util.Vector generateQuestions()
    {
        return null;
    }
    
    public com.cim.AIA.MultipleTween generateTweens(com.cim.AIA.Paintable a, Object a0, int i)
    {
        return null;
    }
    
    public java.util.Vector getAllEndNodes()
    {
        java.util.Vector a = this.allEndNodes;
        if(a == null)
        {
            java.util.Vector a0 = new java.util.Vector();
            this.allEndNodes = a0;
        }
        java.util.Vector a1 = this.allEndNodes;
        a1.removeAllElements();
        PatriciaNode a2 = this.head;
        this.findAllEndNodes(a2);
        java.util.Vector a3 = this.allEndNodes;
        return a3;
    }
    
    private int getBit(int i, byte a)
    {
        int i0 = this.mostSignificantBit;
        int i1 = a;
        int i2 = i0 - i1;
        int i3 = i >> i2;
        int i4 = i3 % 2;
        return i4;
    }
    
    public com.cim.AIA.DigitalElementArray getCompareBitArray()
    {
        com.cim.AIA.DigitalElementArray a = this.compareDataBitArray;
        return a;
    }
    
    public com.cim.AIA.Node getCompKeyNode()
    {
        com.cim.AIA.Node a = this.compKeyNode;
        return a;
    }
    
    public Integer getCurrentBit()
    {
        Integer a = this.currentBit;
        return a;
    }
    
    public com.cim.AIA.DigitalElementArray getDataItemBitArray()
    {
        com.cim.AIA.DigitalElementArray a = this.dataItemBitArray;
        return a;
    }
    
    public Integer getDifferentiatingBit()
    {
        Integer a = this.differentiatingBit;
        return a;
    }
    
    public PatriciaNode getHeadNode()
    {
        PatriciaNode a = this.head;
        return a;
    }
    
    public com.cim.AIA.HierarchyTree getHierarchyTree()
    {
        PatriciaNode a = this.head;
        java.awt.Color a0 = PatriciaTreeColors.DEFAULT_TREE_COLOR;
        com.cim.AIA.HierarchyTree a1 = this.buildHierarchyTree(a, a0);
        PatriciaTree$Path a2 = this.currentPath;
        this.colorHierarchyTree(a1, a2);
        return a1;
    }
    
    public java.util.Vector getInsertedItemsArray()
    {
        java.util.Vector a = new java.util.Vector();
        PatriciaNode a0 = this.head;
        PatriciaNode a1 = a0.getLeft();
        PatriciaNode a2 = this.head;
        if(a1 != a2)
        {
            PatriciaNode a3 = this.head;
            PatriciaNode a4 = a3.getLeft();
            this.printNodes("", a4, a);
        }
        int i = 0;
        while(true)
        {
            int i0 = a.size();
            if(i >= i0)
            {
                return a;
            }
            else
            {
                java.io.PrintStream a5 = System.out;
                Object a6 = a.elementAt(i);
                PatriciaTreeInserted dummy = (PatriciaTreeInserted)a6;
                PatriciaTreeInserted a7 = (PatriciaTreeInserted)a6;
                String s = a7.getPattern();
                a5.println(s);
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    public com.cim.AIA.ElementArray getInsertElementArray()
    {
        com.cim.AIA.ElementArray a = null;
        int i = this.executingMode;
        if(i != 11)
        {
            a = null;
        }
        else
        {
            com.cim.AIA.ElementArray a0 = this.insertData;
            a = a0;
        }
        return a;
    }
    
    public PatriciaNode getInsertPositionNode()
    {
        PatriciaNode a = this.currentInsertTree;
        return a;
    }
    
    public Boolean getIsFollowedUpLink()
    {
        Boolean a = this.isFollowedUpLink;
        return a;
    }
    
    public Boolean getIsHighlightLoop()
    {
        Boolean a = PatriciaTree.isHighlightLoop;
        return a;
    }
    
    public com.cim.AIA.Node getKeyFoundNode()
    {
        com.cim.AIA.Node a = this.keyFoundNode;
        return a;
    }
    
    public com.cim.AIA.Node getLastNode()
    {
        com.cim.AIA.Node a = null;
        PatriciaNode a0 = this.lastNode;
        if(a0 == null)
        {
            a = null;
        }
        else
        {
            PatriciaNode a1 = this.lastNode;
            com.cim.AIA.Node a2 = a1.getBody();
            a = a2;
        }
        return a;
    }
    
    public PatriciaNode getNewNode()
    {
        PatriciaNode a = PatriciaNode.getNewNode();
        return a;
    }
    
    public com.cim.AIA.ElementArray getSearchElementArray()
    {
        com.cim.AIA.ElementArray a = null;
        int i = this.executingMode;
        if(i != 12)
        {
            a = null;
        }
        else
        {
            com.cim.AIA.ElementArray a0 = this.searchData;
            a = a0;
        }
        return a;
    }
    
    public com.cim.AIA.Node getSearchPositionNode()
    {
        com.cim.AIA.Node a = null;
        PatriciaNode a0 = this.currentSearchTree;
        if(a0 == null)
        {
            a = null;
        }
        else
        {
            PatriciaNode a1 = this.currentSearchTree;
            com.cim.AIA.Node a2 = a1.getBody();
            a = a2;
        }
        return a;
    }
    
    protected boolean hasQuestions()
    {
        return false;
    }
    
    private com.cim.AIA.DigitalElementArray initialiseBitElementArray(int i)
    {
        int i0 = this.mostSignificantBit;
        com.cim.AIA.DigitalElementArray a = new com.cim.AIA.DigitalElementArray(i, i0);
        return a;
    }
    
    protected void initialiseCanvases(String s, com.cim.AIA.MultiAlgorithmWindow a)
    {
        String s0 = localization.Messages.getString("PatriciaTree.44");
        a.addCodeCanvas(s, s0, "3.1", "5.1hide");
        String s1 = localization.Messages.getString("PatriciaTree.22");
        a.addCodeCanvas(s, s1, "3.2", "3.2hide");
        String s2 = localization.Messages.getString("PatriciaTree.44");
        a.addCodeCanvas(s, s2, "5.1s", "5.1shide");
        java.awt.Dimension a0 = new java.awt.Dimension(350, 200);
        String s3 = localization.Messages.getString("PatriciaTree.44");
        a.setSize(a0, s3);
        java.awt.Point a1 = new java.awt.Point(235, 420);
        String s4 = localization.Messages.getString("PatriciaTree.44");
        a.setLocation(a1, s4);
        java.awt.Point a2 = new java.awt.Point(235, 280);
        String s5 = localization.Messages.getString("PatriciaTree.22");
        a.setLocation(a2, s5);
        java.awt.Dimension a3 = new java.awt.Dimension(350, 430);
        String s6 = localization.Messages.getString("PatriciaTree.22");
        a.setSize(a3, s6);
    }
    
    protected void initialiseMethods(String s, com.cim.AIA.MethodSelectable a)
    {
        Object a0 = a;
        String s0 = PatriciaTree.BUILD_MODE_LABEL;
        String s1 = localization.Messages.getString("PatriciaTree.2");
        com.cim.AIA.Logger a1 = this.getLogger();
        com.cim.AIA.BreakPoint a2 = this.getBreakPoint();
        com.cim.AIA.MethodSelection a3 = new com.cim.AIA.MethodSelection(s0, s, s1, "3", a1, a2);
        String s2 = PatriciaTree.SEARCH_MODE_LABEL;
        String s3 = localization.Messages.getString("PatriciaTree.4");
        com.cim.AIA.Logger a4 = this.getLogger();
        com.cim.AIA.BreakPoint a5 = this.getBreakPoint();
        com.cim.AIA.MethodSelection a6 = new com.cim.AIA.MethodSelection(s2, s, s3, "5", a4, a5);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelection(a3, true);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelection(a6, false);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelectionListener((com.cim.AIA.MethodSelectionListener)this);
    }
    
    private void insert(PatriciaNode a, PatriciaTreeDataItem a0)
    {
        this.setPosition("3");
        PatriciaTree$Path a1 = new PatriciaTree$Path(this, true);
        this.currentPath = a1;
        int i = a0.getKey();
        com.cim.AIA.DigitalElementArray a2 = this.initialiseBitElementArray(i);
        this.dataItemBitArray = a2;
        com.cim.AIA.DigitalElementArray a3 = this.dataItemBitArray;
        java.awt.Color a4 = PatriciaTreeColors.INSERT_BIT_ACTIVE_COLOR;
        java.awt.Color a5 = PatriciaTreeColors.INSERT_BIT_HIGHLIGHT_COLOR;
        a3.setColors(a4, a5);
        this.setPosition("3.1.1");
        PatriciaNode a6 = a.getLeft();
        int i0 = a0.getKey();
        PatriciaTreeDataItem a7 = this.searchKey(a6, i0, (byte)-1);
        int i1 = a7.getKey();
        this.setPosition("5.1hide");
        this.currentPath = null;
        PatriciaNode a8 = this.currentSearchTree;
        if(a8 != null)
        {
            PatriciaNode a9 = this.currentSearchTree;
            PatriciaTreeDataItem a10 = a9.getDataItem();
            com.cim.AIA.Node a11 = a10.getNode();
            this.compKeyNode = a11;
            com.cim.AIA.Node a12 = this.compKeyNode;
            a12.highlight();
        }
        this.currentSearchTree = null;
        this.setPosition("3.1.2.1");
        int i2 = a0.getKey();
        label1: {
            label0: {
                if(i1 != i2)
                {
                    break label0;
                }
                this.clearPointers();
                break label1;
            }
            com.cim.AIA.DigitalElementArray a13 = this.initialiseBitElementArray(i1);
            this.compareDataBitArray = a13;
            com.cim.AIA.DigitalElementArray a14 = this.compareDataBitArray;
            java.awt.Color a15 = PatriciaTreeColors.DATA_ITEM_COLOR;
            java.awt.Color a16 = PatriciaTreeColors.DATA_ITEM_HIGHLIGHT_COLOR;
            a14.setColors(a15, a16);
            this.setPosition("3.1.2.2");
            com.cim.AIA.DigitalElementArray a17 = this.dataItemBitArray;
            a17.highlightBit((byte)0);
            com.cim.AIA.DigitalElementArray a18 = this.compareDataBitArray;
            a18.highlightBit((byte)0);
            this.setPosition("3.1.2.3");
            int i3 = 0;
            while(true)
            {
                int i4 = this.getBit(i1, (byte)i3);
                int i5 = a0.getKey();
                int i6 = this.getBit(i5, (byte)i3);
                if(i4 != i6)
                {
                    break;
                }
                else
                {
                    int i7 = i3 + 1;
                    int i8 = (byte)i7;
                    com.cim.AIA.DigitalElementArray a19 = this.dataItemBitArray;
                    a19.unHighlight();
                    com.cim.AIA.DigitalElementArray a20 = this.dataItemBitArray;
                    a20.highlightBit((byte)i8);
                    com.cim.AIA.DigitalElementArray a21 = this.compareDataBitArray;
                    a21.unHighlight();
                    com.cim.AIA.DigitalElementArray a22 = this.compareDataBitArray;
                    a22.highlightBit((byte)i8);
                    int i9 = this.isExpanded("3.1.2.3")?1:0;
                    this.setPosition("3.1.2.3.1", i9 != 0);
                    i3 = i8;
                }
            }
            com.cim.AIA.DigitalElementArray a23 = this.compareDataBitArray;
            java.awt.Color a24 = PatriciaTreeColors.DIFFERENTIATING_COLOR;
            a23.highlightNumber((byte)i3, a24);
            Integer a25 = new Integer(i3);
            this.differentiatingBit = a25;
            int i10 = this.isExpanded("3.1")?1:0;
            if(i10 == 0)
            {
                com.cim.AIA.DigitalElementArray a26 = this.dataItemBitArray;
                a26.unHighlight();
                this.compareDataBitArray = null;
                com.cim.AIA.Node a27 = this.compKeyNode;
                a27.unHighlight();
                this.compKeyNode = null;
            }
            this.setPosition("3.1.2.4");
            int i11 = this.enabled?1:0;
            label2: {
                if(i11 != 0)
                {
                    break label2;
                }
                this.clearPointers();
                break label1;
            }
            com.cim.AIA.DigitalElementArray a28 = this.dataItemBitArray;
            a28.unHighlight();
            this.compareDataBitArray = null;
            com.cim.AIA.Node a29 = this.compKeyNode;
            if(a29 != null)
            {
                com.cim.AIA.Node a30 = this.compKeyNode;
                a30.unHighlight();
                this.compKeyNode = null;
            }
            PatriciaTree$Path a31 = new PatriciaTree$Path(this, true);
            this.currentPath = a31;
            int i12 = this.isExpanded("3.2")?1:0;
            if(i12 == 0)
            {
                PatriciaNode a32 = a.getLeft();
                PatriciaNode a33 = this.insertKey(a32, a0, (byte)i3, a, true);
                a.setLeft(a33);
                this.currentPath = null;
                this.currentInsertTree = null;
                this.setPosition("3.2.1");
            }
            else
            {
                this.setPosition("3.2.1");
                PatriciaNode a34 = a.getLeft();
                PatriciaNode a35 = this.insertKey(a34, a0, (byte)i3, a, true);
                a.setLeft(a35);
            }
            this.setPosition("3.4.1.3b");
            this.setPosition("3.2hide");
            this.dataItemBitArray = null;
            this.currentPath = null;
            this.differentiatingBit = null;
            this.currentInsertTree = null;
        }
    }
    
    private PatriciaNode insertKey(PatriciaNode a, PatriciaTreeDataItem a0, byte a1, PatriciaNode a2, boolean b)
    {
        int i = 0;
        PatriciaNode a3 = null;
        this.setPosition("3.4.1", b);
        int i0 = a1;
        int i1 = b?1:0;
        this.currentInsertTree = a;
        int i2 = a.getBit();
        Integer a4 = new Integer(i2);
        this.currentBit = a4;
        this.setPosition("3.4.1.0", i1 != 0);
        Integer a5 = new Integer(i0);
        this.differentiatingBit = a5;
        int i3 = a.getBit();
        label2: {
            label1: {
                label0: {
                    if(i3 >= i0)
                    {
                        break label0;
                    }
                    int i4 = a.getBit();
                    int i5 = a2.getBit();
                    if(i4 > i5)
                    {
                        break label1;
                    }
                }
                i = 1;
                break label2;
            }
            i = 0;
        }
        Boolean a6 = new Boolean(i != 0);
        this.isFollowedUpLink = a6;
        this.setPosition("3.4.1.1", i1 != 0);
        int i6 = a.getBit();
        label5: {
            label4: {
                label3: {
                    if(i6 >= i0)
                    {
                        break label3;
                    }
                    int i7 = a.getBit();
                    int i8 = a2.getBit();
                    if(i7 > i8)
                    {
                        break label4;
                    }
                }
                PatriciaNode a7 = new PatriciaNode(a0, (byte)i0);
                this.setPosition("3.4.1.1.1.1", i1 != 0);
                int i9 = a0.getKey();
                com.cim.AIA.DigitalElementArray a8 = this.initialiseBitElementArray(i9);
                this.dataItemBitArray = a8;
                com.cim.AIA.DigitalElementArray a9 = this.dataItemBitArray;
                java.awt.Color a10 = PatriciaTreeColors.INSERT_BIT_ACTIVE_COLOR;
                java.awt.Color a11 = PatriciaTreeColors.INSERT_BIT_HIGHLIGHT_COLOR;
                a9.setColors(a10, a11);
                com.cim.AIA.DigitalElementArray a12 = this.dataItemBitArray;
                a12.highlightBit((byte)i0);
                this.setPosition("3.4.1.1.1.2.1", i1 != 0);
                int i10 = a0.getKey();
                int i11 = this.getBit(i10, (byte)i0);
                if(i11 != 0)
                {
                    this.setPosition("3.4.1.1.1.2.2", i1 != 0);
                    a7.setRight(a7);
                    this.setPosition("3.4.1.1.1.2.2.1", i1 != 0);
                    a7.setLeft(a);
                    this.setPosition("3.4.1.1.1.2.2.2", i1 != 0);
                    com.cim.AIA.DigitalElementArray a13 = this.dataItemBitArray;
                    a13.unHighlight();
                }
                else
                {
                    a7.setLeft(a7);
                    this.setPosition("3.4.1.1.1.2.1.1", i1 != 0);
                    a7.setRight(a);
                    this.setPosition("3.4.1.1.1.2.1.2", i1 != 0);
                    com.cim.AIA.DigitalElementArray a14 = this.dataItemBitArray;
                    a14.unHighlight();
                }
                int i12 = this.enabled?1:0;
                if(i12 != 0)
                {
                    this.setPosition("3.4.1.1.1.3", i1 != 0);
                    this.currentBit = null;
                    this.isFollowedUpLink = null;
                    a3 = a7;
                    break label5;
                }
                else
                {
                    this.clearPointers();
                    a3 = a;
                    break label5;
                }
            }
            this.setPosition("3.4.1.2", i1 != 0);
            this.isFollowedUpLink = null;
            int i13 = a0.getKey();
            com.cim.AIA.DigitalElementArray a15 = this.initialiseBitElementArray(i13);
            this.dataItemBitArray = a15;
            com.cim.AIA.DigitalElementArray a16 = this.dataItemBitArray;
            java.awt.Color a17 = PatriciaTreeColors.INSERT_BIT_ACTIVE_COLOR;
            java.awt.Color a18 = PatriciaTreeColors.INSERT_BIT_HIGHLIGHT_COLOR;
            a16.setColors(a17, a18);
            com.cim.AIA.DigitalElementArray a19 = this.dataItemBitArray;
            int i14 = a.getBit();
            a19.highlightBit((byte)i14);
            this.setPosition("3.4.1.2.1.2", i1 != 0);
            int i15 = a0.getKey();
            int i16 = a.getBit();
            int i17 = this.getBit(i15, (byte)i16);
            label7: {
                label6: {
                    if(i17 == 0)
                    {
                        break label6;
                    }
                    com.cim.AIA.DigitalElementArray a20 = this.dataItemBitArray;
                    a20.unHighlight();
                    PatriciaTree$Path a21 = this.currentPath;
                    a21.add(false);
                    this.lastNode = a;
                    a.setRightIsKnown(false);
                    this.setPosition("3.4.1.2.1.3", i1 != 0);
                    this.currentBit = null;
                    int i18 = this.isExpanded("3.4.1.2.1")?1:0;
                    if(i18 == 0)
                    {
                        PatriciaNode a22 = a.getRight();
                        PatriciaNode a23 = this.insertKey(a22, a0, (byte)i0, a, false);
                        a.setRightIsKnown(true);
                        a.setRight(a23);
                        break label7;
                    }
                    else
                    {
                        PatriciaNode a24 = a.getRight();
                        PatriciaNode a25 = this.insertKey(a24, a0, (byte)i0, a, true);
                        a.setRightIsKnown(true);
                        a.setRight(a25);
                        break label7;
                    }
                }
                com.cim.AIA.DigitalElementArray a26 = this.dataItemBitArray;
                a26.unHighlight();
                PatriciaTree$Path a27 = this.currentPath;
                a27.add(true);
                this.lastNode = a;
                a.setLeftIsKnown(false);
                this.setPosition("3.4.1.2.1.2.1", i1 != 0);
                this.currentBit = null;
                int i19 = this.isExpanded("3.4.1.2.1")?1:0;
                if(i19 == 0)
                {
                    PatriciaNode a28 = a.getLeft();
                    PatriciaNode a29 = this.insertKey(a28, a0, (byte)i0, a, false);
                    a.setLeftIsKnown(true);
                    a.setLeft(a29);
                }
                else
                {
                    PatriciaNode a30 = a.getLeft();
                    PatriciaNode a31 = this.insertKey(a30, a0, (byte)i0, a, true);
                    a.setLeftIsKnown(true);
                    a.setLeft(a31);
                }
            }
            this.currentInsertTree = a;
            this.setPosition("3.4.1.2.1.4", i1 != 0);
            this.currentBit = null;
            a3 = a;
        }
        return a3;
    }
    
    private boolean isExpanded(String s)
    {
        com.cim.AIA.AlgorithmThread a = this.algorithmThread;
        PatriciaTreeThread dummy = (PatriciaTreeThread)a;
        PatriciaTreeThread a0 = (PatriciaTreeThread)a;
        com.cim.AIA.AlgorithmWindow a1 = a0.getWindow();
        int i = a1.isExpanded(s)?1:0;
        return i != 0;
    }
    
    public void printNodes(String s, PatriciaNode a, java.util.Vector a0)
    {
        PatriciaNode a1 = a.getLeft();
        int i = a1.getBit();
        int i0 = a.getBit();
        label1: {
            label0: {
                if(i <= i0)
                {
                    break label0;
                }
                StringBuilder a2 = new StringBuilder();
                StringBuilder a3 = a2.append(s);
                StringBuilder a4 = a3.append("0");
                String s0 = a4.toString();
                int i1 = a.getBit();
                int i2 = i1 + 1;
                String s1 = s0;
                int i3 = i2;
                while(true)
                {
                    PatriciaNode a5 = a.getLeft();
                    int i4 = a5.getBit();
                    if(i3 >= i4)
                    {
                        break;
                    }
                    else
                    {
                        StringBuilder a6 = new StringBuilder();
                        StringBuilder a7 = a6.append(s1);
                        StringBuilder a8 = a7.append("*");
                        String s2 = a8.toString();
                        int i5 = i3 + 1;
                        s1 = s2;
                        i3 = i5;
                        continue;
                    }
                }
                PatriciaNode a9 = a.getLeft();
                this.printNodes(s1, a9, a0);
                break label1;
            }
            StringBuilder a10 = new StringBuilder();
            StringBuilder a11 = a10.append(s);
            StringBuilder a12 = a11.append("0");
            String s3 = a12.toString();
            int i6 = a.getBit();
            int i7 = i6 + 1;
            String s4 = s3;
            int i8 = i7;
            while(true)
            {
                int i9 = this.mostSignificantBit;
                if(i8 > i9)
                {
                    break;
                }
                else
                {
                    StringBuilder a13 = new StringBuilder();
                    StringBuilder a14 = a13.append(s4);
                    StringBuilder a15 = a14.append("*");
                    String s5 = a15.toString();
                    int i10 = i8 + 1;
                    s4 = s5;
                    i8 = i10;
                }
            }
            java.io.PrintStream a16 = System.out;
            StringBuilder a17 = new StringBuilder();
            String s6 = localization.Messages.getString("PatriciaTree.26");
            StringBuilder a18 = a17.append(s6);
            StringBuilder a19 = a18.append(s4);
            String s7 = localization.Messages.getString("PatriciaTree.27");
            StringBuilder a20 = a19.append(s7);
            PatriciaNode a21 = a.getLeft();
            PatriciaTreeDataItem a22 = a21.getDataItem();
            int i11 = a22.getKey();
            StringBuilder a23 = a20.append(i11);
            String s8 = a23.toString();
            a16.println(s8);
            PatriciaNode a24 = a.getLeft();
            PatriciaTreeDataItem a25 = a24.getDataItem();
            int i12 = a25.getKey();
            PatriciaTreeInserted a26 = new PatriciaTreeInserted(s4, i12);
            a0.addElement((Object)a26);
        }
        PatriciaNode a27 = a.getRight();
        int i13 = a27.getBit();
        int i14 = a.getBit();
        label3: {
            label2: {
                if(i13 <= i14)
                {
                    break label2;
                }
                StringBuilder a28 = new StringBuilder();
                StringBuilder a29 = a28.append(s);
                StringBuilder a30 = a29.append("1");
                String s9 = a30.toString();
                int i15 = a.getBit();
                int i16 = i15 + 1;
                String s10 = s9;
                int i17 = i16;
                while(true)
                {
                    PatriciaNode a31 = a.getRight();
                    int i18 = a31.getBit();
                    if(i17 >= i18)
                    {
                        break;
                    }
                    else
                    {
                        StringBuilder a32 = new StringBuilder();
                        StringBuilder a33 = a32.append(s10);
                        StringBuilder a34 = a33.append("*");
                        String s11 = a34.toString();
                        int i19 = i17 + 1;
                        s10 = s11;
                        i17 = i19;
                        continue;
                    }
                }
                PatriciaNode a35 = a.getRight();
                this.printNodes(s10, a35, a0);
                break label3;
            }
            StringBuilder a36 = new StringBuilder();
            StringBuilder a37 = a36.append(s);
            StringBuilder a38 = a37.append("1");
            String s12 = a38.toString();
            int i20 = a.getBit();
            int i21 = i20 + 1;
            String s13 = s12;
            int i22 = i21;
            while(true)
            {
                int i23 = this.mostSignificantBit;
                if(i22 > i23)
                {
                    break;
                }
                else
                {
                    StringBuilder a39 = new StringBuilder();
                    StringBuilder a40 = a39.append(s13);
                    StringBuilder a41 = a40.append("*");
                    String s14 = a41.toString();
                    int i24 = i22 + 1;
                    s13 = s14;
                    i22 = i24;
                }
            }
            java.io.PrintStream a42 = System.out;
            StringBuilder a43 = new StringBuilder();
            String s15 = localization.Messages.getString("PatriciaTree.32");
            StringBuilder a44 = a43.append(s15);
            StringBuilder a45 = a44.append(s13);
            String s16 = localization.Messages.getString("PatriciaTree.33");
            StringBuilder a46 = a45.append(s16);
            PatriciaNode a47 = a.getRight();
            PatriciaTreeDataItem a48 = a47.getDataItem();
            int i25 = a48.getKey();
            StringBuilder a49 = a46.append(i25);
            String s17 = a49.toString();
            a42.println(s17);
            PatriciaNode a50 = a.getRight();
            PatriciaTreeDataItem a51 = a50.getDataItem();
            int i26 = a51.getKey();
            PatriciaTreeInserted a52 = new PatriciaTreeInserted(s13, i26);
            a0.addElement((Object)a52);
        }
    }
    
    public void processMethodSelectionEvent(com.cim.AIA.MethodSelectionEvent a)
    {
        String s = PatriciaTree.BUILD_MODE_LABEL;
        String s0 = a.getMethodName();
        int i = s.compareTo(s0);
        label1: {
            label0: {
                if(i != 0)
                {
                    break label0;
                }
                java.io.PrintStream a0 = System.out;
                String s1 = localization.Messages.getString("PatriciaTree.19");
                a0.println(s1);
                this.nextMode = 11;
                break label1;
            }
            String s2 = PatriciaTree.SEARCH_MODE_LABEL;
            String s3 = a.getMethodName();
            int i0 = s2.compareTo(s3);
            if(i0 == 0)
            {
                java.io.PrintStream a1 = System.out;
                String s4 = localization.Messages.getString("PatriciaTree.20");
                a1.println(s4);
                this.nextMode = 12;
            }
        }
    }
    
    public void reInitialise(Object a)
    {
        this.mostSignificantBit = 0;
        com.cim.AIA.ElementArray a0 = this.newInsertData;
        this.insertData = a0;
        int i = 0;
        while(true)
        {
            com.cim.AIA.ElementArray a1 = this.insertData;
            int i0 = a1.getSize();
            if(i >= i0)
            {
                int i1 = this.mostSignificantBit;
                int i2 = i1 - 1;
                this.mostSignificantBit = i2;
                return;
            }
            com.cim.AIA.ElementArray a2 = this.insertData;
            com.cim.AIA.Element a3 = a2.getElement(i);
            Object a4 = a3.getObject();
            Integer dummy = (Integer)a4;
            Integer a5 = (Integer)a4;
            int i3 = a5.intValue();
            String s = Integer.toBinaryString(i3);
            int i4 = this.mostSignificantBit;
            int i5 = s.length();
            if(i4 < i5)
            {
                int i6 = s.length();
                this.mostSignificantBit = i6;
            }
            int i7 = i + 1;
            i = i7;
        }
    }
    
    protected void removeAllAnimationRequests()
    {
    }
    
    protected void removeAllQuestions()
    {
    }
    
    protected void run()
    {
        int i = this.executingMode;
        label1: {
            int i0 = 0;
            label0: {
                int i1 = 0;
                switch(i){
                    case 12: {
                        i1 = 0;
                        break;
                    }
                    case 11: {
                        i0 = 0;
                        break label0;
                    }
                }
                while(true)
                {
                    com.cim.AIA.ElementArray a = this.searchData;
                    int i2 = a.getSize();
                    if(i1 >= i2)
                    {
                        break label1;
                    }
                    else
                    {
                        com.cim.AIA.ElementArray a0 = this.searchData;
                        com.cim.AIA.Element a1 = a0.getElement(i1);
                        Object a2 = a1.getObject();
                        com.cim.AIA.Node a3 = new com.cim.AIA.Node(a2, i1);
                        com.cim.AIA.ElementArray a4 = this.searchData;
                        com.cim.AIA.Element a5 = a4.getElement(i1);
                        Object a6 = a5.getObject();
                        Integer dummy = (Integer)a6;
                        Integer a7 = (Integer)a6;
                        int i3 = a7.intValue();
                        PatriciaTreeDataItem a8 = new PatriciaTreeDataItem(a3, i3);
                        com.cim.AIA.ElementArray a9 = this.searchData;
                        com.cim.AIA.Element a10 = a9.getElement(i1);
                        com.cim.AIA.Node dummy0 = (com.cim.AIA.Node)a10;
                        com.cim.AIA.Node a11 = (com.cim.AIA.Node)a10;
                        java.awt.Color a12 = PatriciaTreeColors.SEARCH_HIGHLIGHT_COLOR;
                        a11.setBackgroundColor(a12);
                        PatriciaNode a13 = this.head;
                        PatriciaTreeDataItem a14 = this.search(a13, a8);
                        com.cim.AIA.ElementArray a15 = this.searchData;
                        com.cim.AIA.Element a16 = a15.getElement(i1);
                        com.cim.AIA.Node dummy1 = (com.cim.AIA.Node)a16;
                        com.cim.AIA.Node a17 = (com.cim.AIA.Node)a16;
                        java.awt.Color a18 = PatriciaTreeColors.SEARCH_DONE_COLOR;
                        a17.setBackgroundColor(a18);
                        int i4 = i1 + 1;
                        i1 = i4;
                    }
                }
            }
            while(true)
            {
                com.cim.AIA.ElementArray a19 = this.insertData;
                int i5 = a19.getSize();
                if(i0 >= i5)
                {
                    break;
                }
                else
                {
                    com.cim.AIA.ElementArray a20 = this.insertData;
                    com.cim.AIA.Element a21 = a20.getElement(i0);
                    Object a22 = a21.getObject();
                    com.cim.AIA.Node a23 = new com.cim.AIA.Node(a22, i0);
                    com.cim.AIA.ElementArray a24 = this.insertData;
                    com.cim.AIA.Element a25 = a24.getElement(i0);
                    Object a26 = a25.getObject();
                    Integer dummy2 = (Integer)a26;
                    Integer a27 = (Integer)a26;
                    int i6 = a27.intValue();
                    PatriciaTreeDataItem a28 = new PatriciaTreeDataItem(a23, i6);
                    com.cim.AIA.ElementArray a29 = this.insertData;
                    com.cim.AIA.Element a30 = a29.getElement(i0);
                    com.cim.AIA.Node dummy3 = (com.cim.AIA.Node)a30;
                    com.cim.AIA.Node a31 = (com.cim.AIA.Node)a30;
                    java.awt.Color a32 = PatriciaTreeColors.INSERT_HIGHLIGHT_COLOR;
                    a31.setBackgroundColor(a32);
                    PatriciaNode a33 = this.head;
                    this.insert(a33, a28);
                    PatriciaNode.clearNewNode();
                    com.cim.AIA.ElementArray a34 = this.insertData;
                    com.cim.AIA.Element a35 = a34.getElement(i0);
                    com.cim.AIA.Node dummy4 = (com.cim.AIA.Node)a35;
                    com.cim.AIA.Node a36 = (com.cim.AIA.Node)a35;
                    java.awt.Color a37 = PatriciaTreeColors.INSERT_DONE_COLOR;
                    a36.setBackgroundColor(a37);
                    int i7 = i0 + 1;
                    i0 = i7;
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
    
    private PatriciaTreeDataItem search(PatriciaNode a, PatriciaTreeDataItem a0)
    {
        int i = a0.getKey();
        com.cim.AIA.DigitalElementArray a1 = this.initialiseBitElementArray(i);
        this.dataItemBitArray = a1;
        com.cim.AIA.DigitalElementArray a2 = this.dataItemBitArray;
        java.awt.Color a3 = PatriciaTreeColors.SEARCH_BIT_ACTIVE_COLOR;
        java.awt.Color a4 = PatriciaTreeColors.SEARCH_BIT_HIGHLIGHT_COLOR;
        a2.setColors(a3, a4);
        PatriciaTree$Path a5 = new PatriciaTree$Path(this, true);
        this.currentPath = a5;
        PatriciaNode a6 = a.getLeft();
        int i0 = a0.getKey();
        PatriciaTreeDataItem a7 = this.searchKey(a6, i0, (byte)-1);
        this.currentPath = null;
        this.currentSearchTree = null;
        com.cim.AIA.Node a8 = a7.getNode();
        this.keyFoundNode = a8;
        this.dataItemBitArray = null;
        this.keyFoundNode = null;
        int i1 = a0.getKey();
        int i2 = a7.getKey();
        PatriciaTreeDataItem a9 = i1 != i2?null:a7;
        return a9;
    }
    
    private PatriciaTreeDataItem searchKey(PatriciaNode a, int i, byte a0)
    {
        PatriciaTreeDataItem a1 = null;
        this.currentSearchTree = a;
        int i0 = a0;
        com.cim.AIA.DigitalElementArray a2 = this.dataItemBitArray;
        a2.unHighlight();
        PatriciaTree$Path a3 = this.currentPath;
        if(a3 == null)
        {
            RuntimeException a4 = new RuntimeException("search path should have been initialised");
            throw a4;
        }
        int i1 = a.getBit();
        int i2 = i1 > i0?0:1;
        Boolean a5 = new Boolean(i2 != 0);
        this.isFollowedUpLink = a5;
        this.setPosition("5.1.1");
        int i3 = a.getBit();
        label1: {
            label0: {
                if(i3 > i0)
                {
                    break label0;
                }
                this.setPosition("5.1.1.1");
                this.isFollowedUpLink = null;
                PatriciaTreeDataItem a6 = a.getDataItem();
                a1 = a6;
                break label1;
            }
            com.cim.AIA.DigitalElementArray a7 = this.dataItemBitArray;
            int i4 = a.getBit();
            a7.highlightBit((byte)i4);
            this.setPosition("5.1.2");
            this.isFollowedUpLink = null;
            this.setPosition("5.1.3");
            int i5 = a.getBit();
            int i6 = this.getBit(i, (byte)i5);
            if(i6 != 0)
            {
                this.setPosition("5.1.4");
                PatriciaTree$Path a8 = this.currentPath;
                a8.add(false);
                this.lastNode = a;
                PatriciaNode a9 = a.getRight();
                int i7 = a.getBit();
                PatriciaTreeDataItem a10 = this.searchKey(a9, i, (byte)i7);
                a1 = a10;
            }
            else
            {
                this.setPosition("5.1.3.1");
                PatriciaTree$Path a11 = this.currentPath;
                a11.add(true);
                this.lastNode = a;
                PatriciaNode a12 = a.getLeft();
                int i8 = a.getBit();
                PatriciaTreeDataItem a13 = this.searchKey(a12, i, (byte)i8);
                a1 = a13;
            }
        }
        return a1;
    }
    
    private void setPosition(String s, boolean b)
    {
        if(!b)
        {
            this.setPosition("This key doesn't exist");
        }
        else
        {
            this.setPosition(s);
        }
    }
    
    protected void storeState(boolean b)
    {
    }
    
    static
    {
        String s = localization.Messages.getString("PatriciaTree.0");
        PatriciaTree.BUILD_MODE_LABEL = s;
        String s0 = localization.Messages.getString("PatriciaTree.1");
        PatriciaTree.SEARCH_MODE_LABEL = s0;
    }
}