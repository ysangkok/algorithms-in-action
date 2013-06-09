public class PatriciaTreeIter extends com.cim.AIA.Algorithm implements com.cim.AIA.MethodSelectionListener, com.cim.AIA.ControlListener {
    final protected static int BUILD_MODE = 11;
    final protected static int SEARCH_MODE = 12;
    private int executingMode;
    private int nextMode;
    private com.cim.AIA.ExtendedHierarchyTree currentHierarchyTree;
    private int mostSignificantBit;
    final private static String BUILD_MODE_LABEL;
    final private static String SEARCH_MODE_LABEL;
    private com.cim.AIA.ElementArray insertData;
    private com.cim.AIA.ElementArray newInsertData;
    private com.cim.AIA.ElementArray searchData;
    private com.cim.AIA.ElementArray newSearchData;
    private com.cim.AIA.DigitalElementArray dataItemBitArray;
    private com.cim.AIA.DigitalElementArray compareDataBitArray;
    private com.cim.AIA.Node compKeyNode;
    private com.cim.AIA.Node keyFoundNode;
    private Integer searchDataValue;
    private PatriciaTreeIterNode head;
    private int insertPos;
    private int lastInsertPos;
    private boolean isBackMode;
    private PatriciaTreeIterNode savedTree;
    private PatriciaTreeIterNode startingTree;
    private PatriciaTreeIterNode lastNode;
    private PatriciaTreeIterNode currentSearchTree;
    private PatriciaTreeIterNode insertionPoint;
    private PatriciaTreeIter$Path currentPath;
    private PatriciaTreeIterNode currentInsertTree;
    private Integer differentiatingBit;
    private Integer currentBit;
    private static Boolean isHighlightLoop;
    private Boolean isFollowedUpLink;
    final private static int DEFAULT_ELEMENT_WIDTH = 20;
    private static java.awt.Color searchTreeColor;
    private static java.awt.Color postSearchTreeColor;
    private java.util.Vector allEndNodes;
    
    public PatriciaTreeIter(com.cim.AIA.AlgorithmThread a, Object a0)
    {
        super(a, a0);
        this.executingMode = 11;
        int i = this.executingMode;
        this.nextMode = i;
        this.currentHierarchyTree = null;
        this.mostSignificantBit = 0;
        this.insertPos = 0;
        this.lastInsertPos = 0;
        this.isBackMode = false;
        this.savedTree = null;
        this.startingTree = null;
        PatriciaTreeIterNode a1 = new PatriciaTreeIterNode();
        this.savedTree = a1;
        PatriciaTreeIterNode a2 = this.savedTree;
        PatriciaTreeIterNode a3 = this.savedTree;
        a2.setLeft(a3);
        PatriciaTreeIterNode a4 = this.savedTree;
        PatriciaTreeIterNode a5 = this.savedTree;
        a4.setRight(a5);
        com.cim.AIA.ElementArray a6 = new com.cim.AIA.ElementArray(0, 0);
        this.insertData = a6;
        com.cim.AIA.ElementArray a7 = this.insertData;
        a7.setColumGap(0);
        com.cim.AIA.ElementArray a8 = this.insertData;
        a8.setElementWidth(20);
        int i0 = 0;
        while(true)
        {
            int[] dummy = (int[])a0;
            int[] a9 = (int[])a0;
            int i1 = a9.length;
            if(i0 >= i1)
            {
                break;
            }
            else
            {
                com.cim.AIA.ElementArray a10 = this.insertData;
                int[] dummy0 = (int[])a0;
                int[] a11 = (int[])a0;
                int i2 = a11[i0];
                Integer a12 = new Integer(i2);
                com.cim.AIA.Node a13 = new com.cim.AIA.Node((Object)a12, i0);
                a10.add((Object)a13, i0);
                com.cim.AIA.ElementArray a14 = this.insertData;
                com.cim.AIA.Element a15 = a14.getElement(i0);
                com.cim.AIA.Node dummy1 = (com.cim.AIA.Node)a15;
                com.cim.AIA.Node a16 = (com.cim.AIA.Node)a15;
                java.awt.Color a17 = PatriciaTreeIterColors.INSERT_ACTIVE_COLOR;
                a16.setBackgroundColor(a17);
                int i3 = i0 + 1;
                i0 = i3;
            }
        }
        com.cim.AIA.ElementArray a18 = this.insertData;
        this.newInsertData = a18;
        java.util.Random a19 = new java.util.Random();
        com.cim.AIA.ElementArray a20 = new com.cim.AIA.ElementArray(0, 0);
        this.searchData = a20;
        com.cim.AIA.ElementArray a21 = this.searchData;
        a21.setColumGap(0);
        com.cim.AIA.ElementArray a22 = this.searchData;
        a22.setElementWidth(20);
        int i4 = 0;
        while(true)
        {
            int[] dummy2 = (int[])a0;
            int[] a23 = (int[])a0;
            int i5 = a23.length;
            if(i4 >= i5)
            {
                com.cim.AIA.ElementArray a24 = this.searchData;
                this.newSearchData = a24;
                PatriciaTreeIterNode a25 = new PatriciaTreeIterNode();
                this.head = a25;
                PatriciaTreeIterNode a26 = this.head;
                PatriciaTreeIterNode a27 = this.head;
                a26.setLeft(a27);
                PatriciaTreeIterNode a28 = this.head;
                PatriciaTreeIterNode a29 = this.head;
                a28.setRight(a29);
                return;
            }
            int i6 = i4 % 2;
            if(i6 != 0)
            {
                com.cim.AIA.ElementArray a30 = this.searchData;
                int[] dummy3 = (int[])a0;
                int[] a31 = (int[])a0;
                int i7 = a31[i4];
                Integer a32 = new Integer(i7);
                com.cim.AIA.Node a33 = new com.cim.AIA.Node((Object)a32, i4);
                a30.add((Object)a33, i4);
            }
            else
            {
                com.cim.AIA.ElementArray a34 = this.searchData;
                int i8 = a19.nextInt();
                int i9 = Math.abs(i8);
                int i10 = i9 % 98;
                int i11 = i10 + 1;
                Integer a35 = new Integer(i11);
                com.cim.AIA.Node a36 = new com.cim.AIA.Node((Object)a35, i4);
                a34.add((Object)a36, i4);
            }
            com.cim.AIA.ElementArray a37 = this.searchData;
            com.cim.AIA.Element a38 = a37.getElement(i4);
            com.cim.AIA.Node dummy4 = (com.cim.AIA.Node)a38;
            com.cim.AIA.Node a39 = (com.cim.AIA.Node)a38;
            java.awt.Color a40 = PatriciaTreeIterColors.SEARCH_ACTIVE_COLOR;
            a39.setBackgroundColor(a40);
            int i12 = i4 + 1;
            i4 = i12;
        }
    }
    
    protected void initialiseMethods(String s, com.cim.AIA.MethodSelectable a)
    {
        Object a0 = a;
        String s0 = PatriciaTreeIter.BUILD_MODE_LABEL;
        String s1 = localization.Messages.getString("PatriciaTreeIter.2");
        com.cim.AIA.Logger a1 = this.getLogger();
        com.cim.AIA.BreakPoint a2 = this.getBreakPoint();
        com.cim.AIA.MethodSelection a3 = new com.cim.AIA.MethodSelection(s0, s, s1, "3a", a1, a2);
        String s2 = PatriciaTreeIter.SEARCH_MODE_LABEL;
        String s3 = localization.Messages.getString("PatriciaTreeIter.4");
        com.cim.AIA.Logger a4 = this.getLogger();
        com.cim.AIA.BreakPoint a5 = this.getBreakPoint();
        com.cim.AIA.MethodSelection a6 = new com.cim.AIA.MethodSelection(s2, s, s3, "5a", a4, a5);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelection(a3, true);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelection(a6, false);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelectionListener((com.cim.AIA.MethodSelectionListener)this);
    }
    
    public void processMethodSelectionEvent(com.cim.AIA.MethodSelectionEvent a)
    {
        String s = PatriciaTreeIter.BUILD_MODE_LABEL;
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
            String s1 = PatriciaTreeIter.SEARCH_MODE_LABEL;
            String s2 = a.getMethodName();
            int i0 = s1.compareTo(s2);
            if(i0 == 0)
            {
                this.nextMode = 12;
            }
        }
        int i1 = this.nextMode;
        if(i1 == 11)
        {
            PatriciaTreeIterNode a0 = this.savedTree;
            this.head = a0;
        }
        int i2 = this.nextMode;
        label2: {
            if(i2 != 12)
            {
                break label2;
            }
            PatriciaTreeIterNode a1 = this.savedTree;
            if(a1 != null)
            {
                PatriciaTreeIterNode a2 = this.savedTree;
                PatriciaTreeIterNode a3 = a2.copy();
                this.head = a3;
            }
        }
    }
    
    protected void storeState(boolean b)
    {
        int i = this.executingMode;
        label1: {
            label0: {
                if(i != 11)
                {
                    break label0;
                }
                PatriciaTreeIterNode a = this.head;
                PatriciaTreeIterNode a0 = a.copy();
                this.savedTree = a0;
                break label1;
            }
            int i0 = this.executingMode;
            if(i0 == 12)
            {
                PatriciaTreeIterNode a1 = this.savedTree;
                PatriciaTreeIterNode a2 = a1.copy();
                this.head = a2;
            }
        }
    }
    
    protected void clearState()
    {
        this.insertPos = 0;
        PatriciaTreeIterNode a = new PatriciaTreeIterNode();
        this.head = a;
        PatriciaTreeIterNode a0 = this.head;
        PatriciaTreeIterNode a1 = this.head;
        a0.setLeft(a1);
        PatriciaTreeIterNode a2 = this.head;
        PatriciaTreeIterNode a3 = this.head;
        a2.setRight(a3);
    }
    
    public void reInitialise(Object a)
    {
        int i = this.executingMode;
        label0: {
            if(i != 11)
            {
                break label0;
            }
            int i0 = this.isBackMode?1:0;
            if(i0 == 0)
            {
                PatriciaTreeIterNode a0 = new PatriciaTreeIterNode();
                this.savedTree = a0;
                PatriciaTreeIterNode a1 = this.savedTree;
                PatriciaTreeIterNode a2 = this.savedTree;
                a1.setLeft(a2);
                PatriciaTreeIterNode a3 = this.savedTree;
                PatriciaTreeIterNode a4 = this.savedTree;
                a3.setRight(a4);
                this.insertPos = 0;
            }
        }
        int i1 = this.executingMode;
        label1: {
            if(i1 != 12)
            {
                break label1;
            }
            int i2 = this.isBackMode?1:0;
            if(i2 == 0)
            {
                PatriciaTreeIterNode a5 = this.savedTree;
                PatriciaTreeIterNode a6 = a5.copy();
                this.head = a6;
            }
        }
        int i3 = this.isBackMode?1:0;
        label2: {
            if(i3 == 0)
            {
                break label2;
            }
            int i4 = this.executingMode;
            label4: {
                label3: {
                    if(i4 != 11)
                    {
                        break label3;
                    }
                    PatriciaTreeIterNode a7 = this.startingTree;
                    PatriciaTreeIterNode a8 = a7.copy();
                    this.head = a8;
                    break label4;
                }
                int i5 = this.executingMode;
                if(i5 == 12)
                {
                    PatriciaTreeIterNode a9 = this.savedTree;
                    PatriciaTreeIterNode a10 = a9.copy();
                    this.head = a10;
                }
            }
            int i6 = this.lastInsertPos;
            this.insertPos = i6;
        }
        this.mostSignificantBit = 0;
        this.currentSearchTree = null;
        com.cim.AIA.ElementArray a11 = this.newInsertData;
        this.insertData = a11;
        int i7 = 0;
        while(true)
        {
            com.cim.AIA.ElementArray a12 = this.insertData;
            int i8 = a12.getSize();
            if(i7 >= i8)
            {
                break;
            }
            else
            {
                com.cim.AIA.ElementArray a13 = this.insertData;
                com.cim.AIA.Element a14 = a13.getElement(i7);
                com.cim.AIA.Node dummy = (com.cim.AIA.Node)a14;
                com.cim.AIA.Node a15 = (com.cim.AIA.Node)a14;
                java.awt.Color a16 = PatriciaTreeIterColors.INSERT_ACTIVE_COLOR;
                a15.setBackgroundColor(a16);
                int i9 = i7 + 1;
                i7 = i9;
            }
        }
        int i10 = 0;
        while(true)
        {
            com.cim.AIA.ElementArray a17 = this.insertData;
            int i11 = a17.getSize();
            if(i10 >= i11)
            {
                int i12 = this.mostSignificantBit;
                int i13 = i12 - 1;
                this.mostSignificantBit = i13;
                return;
            }
            com.cim.AIA.ElementArray a18 = this.insertData;
            com.cim.AIA.Element a19 = a18.getElement(i10);
            Object a20 = a19.getObject();
            Integer dummy0 = (Integer)a20;
            Integer a21 = (Integer)a20;
            int i14 = a21.intValue();
            String s = Integer.toBinaryString(i14);
            int i15 = this.mostSignificantBit;
            int i16 = s.length();
            if(i15 < i16)
            {
                int i17 = s.length();
                this.mostSignificantBit = i17;
            }
            int i18 = i10 + 1;
            i10 = i18;
        }
    }
    
    protected void changeData(Object a)
    {
        java.util.Random a0 = new java.util.Random();
        com.cim.AIA.ElementArray a1 = new com.cim.AIA.ElementArray(0, 0);
        this.newInsertData = a1;
        com.cim.AIA.ElementArray a2 = this.newInsertData;
        a2.setColumGap(0);
        com.cim.AIA.ElementArray a3 = this.newInsertData;
        a3.setElementWidth(20);
        int i = 0;
        while(true)
        {
            int[] dummy = (int[])a;
            int[] a4 = (int[])a;
            int i0 = a4.length;
            if(i >= i0)
            {
                break;
            }
            else
            {
                com.cim.AIA.ElementArray a5 = this.newInsertData;
                int[] dummy0 = (int[])a;
                int[] a6 = (int[])a;
                int i1 = a6[i];
                Integer a7 = new Integer(i1);
                com.cim.AIA.Node a8 = new com.cim.AIA.Node((Object)a7, i);
                a5.add((Object)a8, i);
                com.cim.AIA.ElementArray a9 = this.newInsertData;
                com.cim.AIA.Element a10 = a9.getElement(i);
                com.cim.AIA.Node dummy1 = (com.cim.AIA.Node)a10;
                com.cim.AIA.Node a11 = (com.cim.AIA.Node)a10;
                java.awt.Color a12 = PatriciaTreeIterColors.INSERT_ACTIVE_COLOR;
                a11.setBackgroundColor(a12);
                int i2 = i + 1;
                i = i2;
            }
        }
        com.cim.AIA.ElementArray a13 = this.newInsertData;
        this.insertData = a13;
        int i3 = 0;
        while(true)
        {
            int i4 = this.insertPos;
            if(i3 >= i4)
            {
                break;
            }
            else
            {
                com.cim.AIA.ElementArray a14 = this.insertData;
                com.cim.AIA.Element a15 = a14.getElement(i3);
                com.cim.AIA.Node dummy2 = (com.cim.AIA.Node)a15;
                com.cim.AIA.Node a16 = (com.cim.AIA.Node)a15;
                java.awt.Color a17 = PatriciaTreeIterColors.INSERT_DONE_COLOR;
                a16.setBackgroundColor(a17);
                int i5 = i3 + 1;
                i3 = i5;
            }
        }
        int i6 = 0;
        while(true)
        {
            com.cim.AIA.ElementArray a18 = this.insertData;
            int i7 = a18.getSize();
            if(i6 >= i7)
            {
                break;
            }
            com.cim.AIA.ElementArray a19 = this.insertData;
            com.cim.AIA.Element a20 = a19.getElement(i6);
            Object a21 = a20.getObject();
            Integer dummy3 = (Integer)a21;
            Integer a22 = (Integer)a21;
            int i8 = a22.intValue();
            String s = Integer.toBinaryString(i8);
            int i9 = this.mostSignificantBit;
            int i10 = s.length();
            if(i9 < i10)
            {
                int i11 = s.length();
                this.mostSignificantBit = i11;
            }
            int i12 = i6 + 1;
            i6 = i12;
        }
        int i13 = this.mostSignificantBit;
        int i14 = i13 - 1;
        this.mostSignificantBit = i14;
        com.cim.AIA.ElementArray a23 = new com.cim.AIA.ElementArray(0, 0);
        this.searchData = a23;
        com.cim.AIA.ElementArray a24 = this.searchData;
        a24.setColumGap(0);
        com.cim.AIA.ElementArray a25 = this.searchData;
        a25.setElementWidth(20);
        int i15 = 0;
        while(true)
        {
            int[] dummy4 = (int[])a;
            int[] a26 = (int[])a;
            int i16 = a26.length;
            if(i15 >= i16)
            {
                com.cim.AIA.ElementArray a27 = this.searchData;
                this.newSearchData = a27;
                return;
            }
            int i17 = i15 % 2;
            if(i17 != 0)
            {
                com.cim.AIA.ElementArray a28 = this.searchData;
                int[] dummy5 = (int[])a;
                int[] a29 = (int[])a;
                int i18 = a29[i15];
                Integer a30 = new Integer(i18);
                com.cim.AIA.Node a31 = new com.cim.AIA.Node((Object)a30, i15);
                a28.add((Object)a31, i15);
            }
            else
            {
                com.cim.AIA.ElementArray a32 = this.searchData;
                int i19 = a0.nextInt();
                int i20 = Math.abs(i19);
                int i21 = i20 % 98;
                int i22 = i21 + 1;
                Integer a33 = new Integer(i22);
                com.cim.AIA.Node a34 = new com.cim.AIA.Node((Object)a33, i15);
                a32.add((Object)a34, i15);
            }
            com.cim.AIA.ElementArray a35 = this.searchData;
            com.cim.AIA.Element a36 = a35.getElement(i15);
            com.cim.AIA.Node dummy6 = (com.cim.AIA.Node)a36;
            com.cim.AIA.Node a37 = (com.cim.AIA.Node)a36;
            java.awt.Color a38 = PatriciaTreeIterColors.SEARCH_ACTIVE_COLOR;
            a37.setBackgroundColor(a38);
            int i23 = i15 + 1;
            i15 = i23;
        }
    }
    
    public com.cim.AIA.MultipleTween generateTweens(com.cim.AIA.Paintable a, Object a0, int i)
    {
        return null;
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
    }
    
    public Integer getDifferentiatingBit()
    {
        Integer a = this.differentiatingBit;
        return a;
    }
    
    public Integer getCurrentBit()
    {
        Integer a = this.currentBit;
        return a;
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
    
    public com.cim.AIA.DigitalElementArray getDataItemBitArray()
    {
        com.cim.AIA.DigitalElementArray a = this.dataItemBitArray;
        return a;
    }
    
    public com.cim.AIA.DigitalElementArray getCompareBitArray()
    {
        com.cim.AIA.DigitalElementArray a = this.compareDataBitArray;
        return a;
    }
    
    private com.cim.AIA.DigitalElementArray initialiseBitElementArray(int i)
    {
        int i0 = this.mostSignificantBit;
        com.cim.AIA.DigitalElementArray a = new com.cim.AIA.DigitalElementArray(i, i0);
        return a;
    }
    
    public java.util.Vector getInsertedItemsArray()
    {
        java.util.Vector a = new java.util.Vector();
        PatriciaTreeIterNode a0 = this.head;
        PatriciaTreeIterNode a1 = a0.getLeft();
        PatriciaTreeIterNode a2 = this.head;
        if(a1 != a2)
        {
            PatriciaTreeIterNode a3 = this.head;
            PatriciaTreeIterNode a4 = a3.getLeft();
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
                Inserted dummy = (Inserted)a6;
                Inserted a7 = (Inserted)a6;
                String s = a7.getPattern();
                a5.println(s);
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    public com.cim.AIA.ExtendedHierarchyTree getHierarchyTree()
    {
        PatriciaTreeIterNode a = this.head;
        java.awt.Color a0 = PatriciaTreeIterColors.DEFAULT_TREE_COLOR;
        com.cim.AIA.ExtendedHierarchyTree a1 = this.buildHierarchyTree(a, a0);
        PatriciaTreeIter$Path a2 = this.currentPath;
        this.colorHierarchyTree(a1, a2);
        return a1;
    }
    
    public com.cim.AIA.Node getSearchPositionNode()
    {
        com.cim.AIA.Node a = null;
        PatriciaTreeIterNode a0 = this.currentSearchTree;
        if(a0 == null)
        {
            a = null;
        }
        else
        {
            PatriciaTreeIterNode a1 = this.currentSearchTree;
            com.cim.AIA.Node a2 = a1.getBody();
            a = a2;
        }
        return a;
    }
    
    public com.cim.AIA.Node getCompKeyNode()
    {
        com.cim.AIA.Node a = this.compKeyNode;
        return a;
    }
    
    public com.cim.AIA.Node getKeyFoundNode()
    {
        com.cim.AIA.Node a = this.keyFoundNode;
        return a;
    }
    
    public com.cim.AIA.Node getInsertionPoint()
    {
        com.cim.AIA.Node a = null;
        PatriciaTreeIterNode a0 = this.insertionPoint;
        if(a0 == null)
        {
            a = null;
        }
        else
        {
            PatriciaTreeIterNode a1 = this.insertionPoint;
            com.cim.AIA.Node a2 = a1.getBody();
            a = a2;
        }
        return a;
    }
    
    public PatriciaTreeIterNode getInsertPositionNode()
    {
        PatriciaTreeIterNode a = this.currentInsertTree;
        return a;
    }
    
    public PatriciaTreeIterNode getNewNode()
    {
        PatriciaTreeIterNode a = PatriciaTreeIterNode.getNewNode();
        return a;
    }
    
    public void printNodes(String s, PatriciaTreeIterNode a, java.util.Vector a0)
    {
        PatriciaTreeIterNode a1 = a.getLeft();
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
                    PatriciaTreeIterNode a5 = a.getLeft();
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
                PatriciaTreeIterNode a9 = a.getLeft();
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
            StringBuilder a18 = a17.append("Pattern: ");
            StringBuilder a19 = a18.append(s4);
            StringBuilder a20 = a19.append(" Node: ");
            PatriciaTreeIterNode a21 = a.getLeft();
            PatriciaTreeIterDataItem a22 = a21.getDataItem();
            int i11 = a22.getKey();
            StringBuilder a23 = a20.append(i11);
            String s6 = a23.toString();
            a16.println(s6);
            PatriciaTreeIterNode a24 = a.getLeft();
            PatriciaTreeIterDataItem a25 = a24.getDataItem();
            int i12 = a25.getKey();
            Inserted a26 = new Inserted(s4, i12);
            a0.addElement((Object)a26);
        }
        PatriciaTreeIterNode a27 = a.getRight();
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
                String s7 = a30.toString();
                int i15 = a.getBit();
                int i16 = i15 + 1;
                String s8 = s7;
                int i17 = i16;
                while(true)
                {
                    PatriciaTreeIterNode a31 = a.getRight();
                    int i18 = a31.getBit();
                    if(i17 >= i18)
                    {
                        break;
                    }
                    else
                    {
                        StringBuilder a32 = new StringBuilder();
                        StringBuilder a33 = a32.append(s8);
                        StringBuilder a34 = a33.append("*");
                        String s9 = a34.toString();
                        int i19 = i17 + 1;
                        s8 = s9;
                        i17 = i19;
                        continue;
                    }
                }
                PatriciaTreeIterNode a35 = a.getRight();
                this.printNodes(s8, a35, a0);
                break label3;
            }
            StringBuilder a36 = new StringBuilder();
            StringBuilder a37 = a36.append(s);
            StringBuilder a38 = a37.append("1");
            String s10 = a38.toString();
            int i20 = a.getBit();
            int i21 = i20 + 1;
            String s11 = s10;
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
                    StringBuilder a40 = a39.append(s11);
                    StringBuilder a41 = a40.append("*");
                    String s12 = a41.toString();
                    int i24 = i22 + 1;
                    s11 = s12;
                    i22 = i24;
                }
            }
            java.io.PrintStream a42 = System.out;
            StringBuilder a43 = new StringBuilder();
            StringBuilder a44 = a43.append("Pattern: ");
            StringBuilder a45 = a44.append(s11);
            StringBuilder a46 = a45.append(" Node: ");
            PatriciaTreeIterNode a47 = a.getRight();
            PatriciaTreeIterDataItem a48 = a47.getDataItem();
            int i25 = a48.getKey();
            StringBuilder a49 = a46.append(i25);
            String s13 = a49.toString();
            a42.println(s13);
            PatriciaTreeIterNode a50 = a.getRight();
            PatriciaTreeIterDataItem a51 = a50.getDataItem();
            int i26 = a51.getKey();
            Inserted a52 = new Inserted(s11, i26);
            a0.addElement((Object)a52);
        }
    }
    
    public PatriciaTreeIterNode getHeadNode()
    {
        PatriciaTreeIterNode a = this.head;
        return a;
    }
    
    public com.cim.AIA.ExtendedHierarchyTree buildHierarchyTree(PatriciaTreeIterNode a, java.awt.Color a0)
    {
        com.cim.AIA.Node a1 = a.getBody();
        java.awt.Color a2 = PatriciaTreeIterColors.DEFAULT_RING_COLOR;
        a1.setRingColor(a2);
        com.cim.AIA.Node a3 = a.getBody();
        java.awt.Color a4 = PatriciaTreeIterColors.DEFAULT_TEXT_COLOR;
        a3.setTextColor(a4);
        com.cim.AIA.ExtendedHierarchyTree a5 = new com.cim.AIA.ExtendedHierarchyTree();
        a5.setDrawBorder(false);
        com.cim.AIA.Line a6 = a5.getLine();
        a6.showArrowHead(false);
        com.cim.AIA.Node a7 = a.getBody();
        a5.add(a7);
        PatriciaTreeIterDataItem a8 = a.getDataItem();
        com.cim.AIA.Node a9 = a8.getNode();
        a5.add(a9);
        a.setHierarchyTree((com.cim.AIA.HierarchyTree)a5);
        PatriciaTreeIterNode a10 = a.getLeft();
        int i = a10.getBit();
        int i0 = a.getBit();
        label1: {
            label0: {
                if(i <= i0)
                {
                    break label0;
                }
                PatriciaTreeIterNode a11 = a.getLeft();
                com.cim.AIA.ExtendedHierarchyTree a12 = this.buildHierarchyTree(a11, a0);
                a5.addChild((com.cim.AIA.Tree)a12);
                break label1;
            }
            PatriciaTreeIterNode a13 = a.getRight();
            int i1 = a13.getBit();
            int i2 = a.getBit();
            if(i1 <= i2)
            {
                com.cim.AIA.ExtendedHierarchyTree a14 = new com.cim.AIA.ExtendedHierarchyTree();
                a5.addChild((com.cim.AIA.Tree)a14);
            }
            else
            {
                com.cim.AIA.ExtendedHierarchyTree a15 = new com.cim.AIA.ExtendedHierarchyTree();
                com.cim.AIA.Node a16 = new com.cim.AIA.Node((Object)"", 0);
                a16.setIsVisible(false);
                a15.add(a16);
                a5.addChild((com.cim.AIA.Tree)a15);
            }
        }
        PatriciaTreeIterNode a17 = a.getRight();
        int i3 = a17.getBit();
        int i4 = a.getBit();
        label3: {
            label2: {
                if(i3 <= i4)
                {
                    break label2;
                }
                PatriciaTreeIterNode a18 = a.getRight();
                com.cim.AIA.ExtendedHierarchyTree a19 = this.buildHierarchyTree(a18, a0);
                a5.addChild((com.cim.AIA.Tree)a19);
                break label3;
            }
            PatriciaTreeIterNode a20 = a.getLeft();
            int i5 = a20.getBit();
            int i6 = a.getBit();
            if(i5 <= i6)
            {
                com.cim.AIA.ExtendedHierarchyTree a21 = new com.cim.AIA.ExtendedHierarchyTree();
                a5.addChild((com.cim.AIA.Tree)a21);
            }
            else
            {
                com.cim.AIA.ExtendedHierarchyTree a22 = new com.cim.AIA.ExtendedHierarchyTree();
                com.cim.AIA.Node a23 = new com.cim.AIA.Node((Object)"", 0);
                a23.setIsVisible(false);
                a22.add(a23);
                a5.addChild((com.cim.AIA.Tree)a22);
            }
        }
        return a5;
    }
    
    private void colorHierarchyTree(com.cim.AIA.ExtendedHierarchyTree a, PatriciaTreeIter$Path a0)
    {
        PatriciaTreeIter.isHighlightLoop = null;
        label1: {
            com.cim.AIA.ExtendedHierarchyTree a1 = null;
            label0: {
                if(a != null)
                {
                    break label0;
                }
                break label1;
            }
            com.cim.AIA.Line a2 = a.getLine();
            a2.showAsThick(true);
            java.awt.Color a3 = PatriciaTreeIterColors.SEARCH_PATH_COLOR;
            a.setParentLineColor(a3);
            com.cim.AIA.Node a4 = a.getFirstElement();
            if(a4 != null)
            {
                com.cim.AIA.Node a5 = a.getFirstElement();
                java.awt.Color a6 = PatriciaTreeIterColors.SEARCH_PATH_COLOR;
                a5.setRingColor(a6);
                com.cim.AIA.Node a7 = a.getFirstElement();
                java.awt.Color a8 = PatriciaTreeIterColors.SEARCH_PATH_COLOR;
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
                com.cim.AIA.ExtendedHierarchyTree dummy = (com.cim.AIA.ExtendedHierarchyTree)a9;
                com.cim.AIA.ExtendedHierarchyTree a10 = (com.cim.AIA.ExtendedHierarchyTree)a9;
                a1 = a10;
            }
            else
            {
                com.cim.AIA.HierarchyTree a11 = a.getLeftChild();
                com.cim.AIA.ExtendedHierarchyTree dummy0 = (com.cim.AIA.ExtendedHierarchyTree)a11;
                com.cim.AIA.ExtendedHierarchyTree a12 = (com.cim.AIA.ExtendedHierarchyTree)a11;
                a1 = a12;
            }
            PatriciaTreeIter$Path a13 = a0.next;
            this.colorHierarchyTree(a1, a13);
            label3: {
                if(a1 == null)
                {
                    break label3;
                }
                int i0 = a1.getNumberOfChildren();
                if(i0 == 0)
                {
                    int i1 = a0.isLeft?1:0;
                    Boolean a14 = new Boolean(i1 != 0);
                    PatriciaTreeIter.isHighlightLoop = a14;
                }
            }
        }
    }
    
    protected void run()
    {
        int i = this.isBackMode?1:0;
        label0: {
            if(i != 0)
            {
                break label0;
            }
            int i0 = this.insertPos;
            this.lastInsertPos = i0;
            PatriciaTreeIterNode a = this.savedTree;
            if(a == null)
            {
                PatriciaTreeIterNode a0 = new PatriciaTreeIterNode();
                this.startingTree = a0;
                PatriciaTreeIterNode a1 = this.startingTree;
                PatriciaTreeIterNode a2 = this.startingTree;
                a1.setLeft(a2);
                PatriciaTreeIterNode a3 = this.startingTree;
                PatriciaTreeIterNode a4 = this.startingTree;
                a3.setRight(a4);
            }
            else
            {
                PatriciaTreeIterNode a5 = this.savedTree;
                PatriciaTreeIterNode a6 = a5.copy();
                this.startingTree = a6;
            }
        }
        int i1 = this.executingMode;
        label2: {
            int i2 = 0;
            label1: {
                int i3 = 0;
                switch(i1){
                    case 12: {
                        i3 = 0;
                        break;
                    }
                    case 11: {
                        i2 = this.insertPos;
                        break label1;
                    }
                }
                while(true)
                {
                    com.cim.AIA.ElementArray a7 = this.searchData;
                    int i4 = a7.getSize();
                    if(i3 >= i4)
                    {
                        break;
                    }
                    com.cim.AIA.ElementArray a8 = this.searchData;
                    com.cim.AIA.Element a9 = a8.getElement(i3);
                    Object a10 = a9.getObject();
                    Integer dummy = (Integer)a10;
                    Integer a11 = (Integer)a10;
                    com.cim.AIA.Node a12 = new com.cim.AIA.Node((Object)a11, i3);
                    com.cim.AIA.ElementArray a13 = this.searchData;
                    com.cim.AIA.Element a14 = a13.getElement(i3);
                    Object a15 = a14.getObject();
                    Integer dummy0 = (Integer)a15;
                    Integer a16 = (Integer)a15;
                    int i5 = a16.intValue();
                    PatriciaTreeIterDataItem a17 = new PatriciaTreeIterDataItem(a12, i5);
                    int i6 = this.enabled?1:0;
                    label3: {
                        if(i6 == 0)
                        {
                            break label3;
                        }
                        com.cim.AIA.ElementArray a18 = this.searchData;
                        com.cim.AIA.Element a19 = a18.getElement(i3);
                        com.cim.AIA.Node dummy1 = (com.cim.AIA.Node)a19;
                        com.cim.AIA.Node a20 = (com.cim.AIA.Node)a19;
                        java.awt.Color a21 = PatriciaTreeIterColors.SEARCH_HIGHLIGHT_COLOR;
                        a20.setBackgroundColor(a21);
                        PatriciaTreeIterNode a22 = this.head;
                        int i7 = this.search(a22, a17)?1:0;
                        if(i7 == 0)
                        {
                            com.cim.AIA.ElementArray a23 = this.searchData;
                            com.cim.AIA.Element a24 = a23.getElement(i3);
                            com.cim.AIA.Node dummy2 = (com.cim.AIA.Node)a24;
                            com.cim.AIA.Node a25 = (com.cim.AIA.Node)a24;
                            com.cim.AIA.StringMarker a26 = new com.cim.AIA.StringMarker("N");
                            a25.addMarker(a26);
                            PatriciaTreeIterNode a27 = this.currentSearchTree;
                            PatriciaTreeIterDataItem a28 = a27.getDataItem();
                            com.cim.AIA.Node a29 = a28.getNode();
                            a29.unHighlight();
                            this.setPosition("5.3.4");
                        }
                        else
                        {
                            com.cim.AIA.ElementArray a30 = this.searchData;
                            com.cim.AIA.Element a31 = a30.getElement(i3);
                            com.cim.AIA.Node dummy3 = (com.cim.AIA.Node)a31;
                            com.cim.AIA.Node a32 = (com.cim.AIA.Node)a31;
                            com.cim.AIA.StringMarker a33 = new com.cim.AIA.StringMarker("F");
                            a32.addMarker(a33);
                            PatriciaTreeIterNode a34 = this.currentSearchTree;
                            PatriciaTreeIterDataItem a35 = a34.getDataItem();
                            com.cim.AIA.Node a36 = a35.getNode();
                            a36.unHighlight();
                            this.setPosition("5.3.2");
                        }
                        com.cim.AIA.ElementArray a37 = this.searchData;
                        com.cim.AIA.Element a38 = a37.getElement(i3);
                        com.cim.AIA.Node dummy4 = (com.cim.AIA.Node)a38;
                        com.cim.AIA.Node a39 = (com.cim.AIA.Node)a38;
                        java.awt.Color a40 = PatriciaTreeIterColors.SEARCH_DONE_COLOR;
                        a39.setBackgroundColor(a40);
                    }
                    int i8 = i3 + 1;
                    i3 = i8;
                }
                int i9 = 0;
                while(true)
                {
                    com.cim.AIA.ElementArray a41 = this.searchData;
                    int i10 = a41.getSize();
                    if(i9 >= i10)
                    {
                        break label2;
                    }
                    else
                    {
                        com.cim.AIA.ElementArray a42 = this.searchData;
                        com.cim.AIA.Element a43 = a42.getElement(i9);
                        com.cim.AIA.Node dummy5 = (com.cim.AIA.Node)a43;
                        com.cim.AIA.Node a44 = (com.cim.AIA.Node)a43;
                        a44.removeAllMarkers();
                        int i11 = i9 + 1;
                        i9 = i11;
                    }
                }
            }
            int i12 = i2;
            while(true)
            {
                com.cim.AIA.ElementArray a45 = this.insertData;
                int i13 = a45.getSize();
                if(i12 >= i13)
                {
                    break;
                }
                com.cim.AIA.ElementArray a46 = this.insertData;
                com.cim.AIA.Element a47 = a46.getElement(i12);
                Object a48 = a47.getObject();
                Integer dummy6 = (Integer)a48;
                Integer a49 = (Integer)a48;
                com.cim.AIA.Node a50 = new com.cim.AIA.Node((Object)a49, i12);
                com.cim.AIA.ElementArray a51 = this.insertData;
                com.cim.AIA.Element a52 = a51.getElement(i12);
                Object a53 = a52.getObject();
                Integer dummy7 = (Integer)a53;
                Integer a54 = (Integer)a53;
                int i14 = a54.intValue();
                PatriciaTreeIterDataItem a55 = new PatriciaTreeIterDataItem(a50, i14);
                int i15 = this.enabled?1:0;
                label4: {
                    if(i15 == 0)
                    {
                        break label4;
                    }
                    com.cim.AIA.ElementArray a56 = this.insertData;
                    com.cim.AIA.Element a57 = a56.getElement(i12);
                    com.cim.AIA.Node dummy8 = (com.cim.AIA.Node)a57;
                    com.cim.AIA.Node a58 = (com.cim.AIA.Node)a57;
                    java.awt.Color a59 = PatriciaTreeIterColors.INSERT_HIGHLIGHT_COLOR;
                    a58.setBackgroundColor(a59);
                    PatriciaTreeIterNode a60 = this.head;
                    PatriciaTreeIterNode a61 = a60.copy();
                    this.savedTree = a61;
                    PatriciaTreeIterNode a62 = this.head;
                    this.insert(a62, a55);
                    int i16 = this.enabled?1:0;
                    if(i16 == 0)
                    {
                        PatriciaTreeIterNode a63 = this.savedTree;
                        PatriciaTreeIterNode a64 = a63.copy();
                        this.head = a64;
                    }
                    else
                    {
                        int i17 = this.insertPos;
                        int i18 = i17 + 1;
                        this.insertPos = i18;
                    }
                    PatriciaTreeIterNode a65 = this.head;
                    a65.clearNewNode();
                    com.cim.AIA.ElementArray a66 = this.insertData;
                    com.cim.AIA.Element a67 = a66.getElement(i12);
                    com.cim.AIA.Node dummy9 = (com.cim.AIA.Node)a67;
                    com.cim.AIA.Node a68 = (com.cim.AIA.Node)a67;
                    java.awt.Color a69 = PatriciaTreeIterColors.INSERT_DONE_COLOR;
                    a68.setBackgroundColor(a69);
                }
                int i19 = i12 + 1;
                i12 = i19;
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
    
    private void insert(PatriciaTreeIterNode a, PatriciaTreeIterDataItem a0)
    {
        this.setPosition("3");
        PatriciaTreeIter$Path a1 = new PatriciaTreeIter$Path(this, true);
        this.currentPath = a1;
        PatriciaTreeIterNode a2 = a.getLeft();
        this.currentSearchTree = a2;
        int i = a0.getKey();
        com.cim.AIA.DigitalElementArray a3 = this.initialiseBitElementArray(i);
        this.dataItemBitArray = a3;
        com.cim.AIA.DigitalElementArray a4 = this.dataItemBitArray;
        java.awt.Color a5 = PatriciaTreeIterColors.INSERT_BIT_ACTIVE_COLOR;
        java.awt.Color a6 = PatriciaTreeIterColors.INSERT_BIT_HIGHLIGHT_COLOR;
        a4.setColors(a5, a6);
        PatriciaTreeIterNode a7 = this.currentSearchTree;
        int i0 = a7.getBit();
        int i1 = a.getBit();
        int i2 = i0 > i1?0:1;
        Boolean a8 = new Boolean(i2 != 0);
        this.isFollowedUpLink = a8;
        PatriciaTreeIterNode a9 = a.getLeft();
        int i3 = a9.getBit();
        com.cim.AIA.DigitalElementArray a10 = this.dataItemBitArray;
        a10.unHighlight();
        if(i3 >= 0)
        {
            com.cim.AIA.DigitalElementArray a11 = this.dataItemBitArray;
            a11.highlightBit((byte)i3);
        }
        this.setPosition("3.1.1");
        int i4 = i3;
        PatriciaTreeIterNode a12 = a;
        while(true)
        {
            PatriciaTreeIterNode a13 = this.currentSearchTree;
            int i5 = a13.getBit();
            int i6 = a12.getBit();
            if(i5 <= i6)
            {
                break;
            }
            this.setPosition("3.2.1.1");
            PatriciaTreeIterNode a14 = this.currentSearchTree;
            int i7 = a0.getKey();
            int i8 = this.getBit(i7, (byte)i4);
            label1: {
                label0: {
                    if(i8 == 0)
                    {
                        break label0;
                    }
                    this.setPosition("3.2.1.1.1.1");
                    this.setPosition("3.2.1.1.1.3");
                    PatriciaTreeIter$Path a15 = this.currentPath;
                    a15.add(false);
                    PatriciaTreeIterNode a16 = this.currentSearchTree;
                    PatriciaTreeIterNode a17 = a16.getRight();
                    this.currentSearchTree = a17;
                    PatriciaTreeIterNode a18 = this.currentSearchTree;
                    int i9 = a18.getBit();
                    int i10 = a14.getBit();
                    int i11 = i9 > i10?0:1;
                    Boolean a19 = new Boolean(i11 != 0);
                    this.isFollowedUpLink = a19;
                    this.setPosition("3.2.1.1.1.4");
                    break label1;
                }
                this.setPosition("3.2.1.1.1.1");
                PatriciaTreeIter$Path a20 = this.currentPath;
                a20.add(true);
                PatriciaTreeIterNode a21 = this.currentSearchTree;
                PatriciaTreeIterNode a22 = a21.getLeft();
                this.currentSearchTree = a22;
                PatriciaTreeIterNode a23 = this.currentSearchTree;
                int i12 = a23.getBit();
                int i13 = a14.getBit();
                int i14 = i12 > i13?0:1;
                Boolean a24 = new Boolean(i14 != 0);
                this.isFollowedUpLink = a24;
                this.setPosition("3.2.1.1.1.2");
            }
            PatriciaTreeIterNode a25 = this.currentSearchTree;
            int i15 = a25.getBit();
            com.cim.AIA.DigitalElementArray a26 = this.dataItemBitArray;
            a26.unHighlight();
            if(i15 >= 0)
            {
                com.cim.AIA.DigitalElementArray a27 = this.dataItemBitArray;
                a27.highlightBit((byte)i15);
            }
            this.setPosition("3.2.1.1.2");
            i4 = i15;
            a12 = a14;
        }
        this.setPosition("3.2.1.1");
        PatriciaTreeIterNode a28 = this.currentSearchTree;
        if(a28 != null)
        {
            PatriciaTreeIterNode a29 = this.currentSearchTree;
            PatriciaTreeIterDataItem a30 = a29.getDataItem();
            com.cim.AIA.Node a31 = a30.getNode();
            a31.highlight();
        }
        this.setPosition("3.2.1.2");
        this.isFollowedUpLink = null;
        this.setPosition("3.2.1.3");
        PatriciaTreeIterNode a32 = this.currentSearchTree;
        PatriciaTreeIterDataItem a33 = a32.getDataItem();
        int i16 = a33.getKey();
        int i17 = a0.getKey();
        label3: {
            label2: {
                if(i16 != i17)
                {
                    break label2;
                }
                this.clearPointers();
                this.setPosition("3.2.1.3.1");
                break label3;
            }
            PatriciaTreeIterNode a34 = this.currentSearchTree;
            PatriciaTreeIterDataItem a35 = a34.getDataItem();
            com.cim.AIA.Node a36 = a35.getNode();
            a36.unHighlight();
            PatriciaTreeIterNode a37 = this.currentSearchTree;
            PatriciaTreeIterDataItem a38 = a37.getDataItem();
            int i18 = a38.getKey();
            com.cim.AIA.DigitalElementArray a39 = this.initialiseBitElementArray(i18);
            this.compareDataBitArray = a39;
            com.cim.AIA.DigitalElementArray a40 = this.compareDataBitArray;
            java.awt.Color a41 = PatriciaTreeIterColors.DATA_ITEM_COLOR;
            java.awt.Color a42 = PatriciaTreeIterColors.DATA_ITEM_HIGHLIGHT_COLOR;
            a40.setColors(a41, a42);
            com.cim.AIA.DigitalElementArray a43 = this.dataItemBitArray;
            a43.unHighlight();
            com.cim.AIA.DigitalElementArray a44 = this.dataItemBitArray;
            a44.highlightBit((byte)0);
            com.cim.AIA.DigitalElementArray a45 = this.compareDataBitArray;
            a45.highlightBit((byte)0);
            this.setPosition("3.2.2.1");
            int i19 = 0;
            while(true)
            {
                int i20 = this.getBit(i18, (byte)i19);
                int i21 = a0.getKey();
                int i22 = this.getBit(i21, (byte)i19);
                if(i20 != i22)
                {
                    break;
                }
                else
                {
                    this.setPosition("3.2.2.2");
                    int i23 = i19 + 1;
                    int i24 = (byte)i23;
                    com.cim.AIA.DigitalElementArray a46 = this.dataItemBitArray;
                    a46.unHighlight();
                    com.cim.AIA.DigitalElementArray a47 = this.dataItemBitArray;
                    a47.highlightBit((byte)i24);
                    com.cim.AIA.DigitalElementArray a48 = this.compareDataBitArray;
                    a48.unHighlight();
                    com.cim.AIA.DigitalElementArray a49 = this.compareDataBitArray;
                    a49.highlightBit((byte)i24);
                    this.setPosition("3.2.2.2.1");
                    i19 = i24;
                }
            }
            com.cim.AIA.DigitalElementArray a50 = this.compareDataBitArray;
            java.awt.Color a51 = PatriciaTreeIterColors.DIFFERENTIATING_COLOR;
            a50.highlightNumber((byte)i19, a51);
            Integer a52 = new Integer(i19);
            this.differentiatingBit = a52;
            this.setPosition("3.2.2.2");
            this.setPosition("3.2.2.3");
            com.cim.AIA.DigitalElementArray a53 = this.dataItemBitArray;
            a53.unHighlight();
            this.compareDataBitArray = null;
            com.cim.AIA.Node a54 = this.compKeyNode;
            if(a54 != null)
            {
                com.cim.AIA.Node a55 = this.compKeyNode;
                a55.unHighlight();
                this.compKeyNode = null;
            }
            PatriciaTreeIter$Path a56 = new PatriciaTreeIter$Path(this, true);
            this.currentPath = a56;
            PatriciaTreeIterNode a57 = a.getLeft();
            this.currentSearchTree = a57;
            PatriciaTreeIterNode a58 = this.currentSearchTree;
            int i25 = a58.getBit();
            int i26 = a.getBit();
            int i27 = i25 > i26?0:1;
            Boolean a59 = new Boolean(i27 != 0);
            this.isFollowedUpLink = a59;
            PatriciaTreeIterNode a60 = this.currentSearchTree;
            int i28 = a60.getBit();
            com.cim.AIA.DigitalElementArray a61 = this.dataItemBitArray;
            a61.unHighlight();
            if(i28 >= 0)
            {
                com.cim.AIA.DigitalElementArray a62 = this.dataItemBitArray;
                a62.highlightBit((byte)i28);
            }
            this.setPosition("3.3.1.2");
            int i29 = i28;
            PatriciaTreeIterNode a63 = a;
            while(true)
            {
                PatriciaTreeIterNode a64 = this.currentSearchTree;
                int i30 = a64.getBit();
                int i31 = a63.getBit();
                if(i30 <= i31)
                {
                    break;
                }
                PatriciaTreeIterNode a65 = this.currentSearchTree;
                int i32 = a65.getBit();
                if(i32 >= i19)
                {
                    break;
                }
                this.setPosition("3.3.2.1");
                PatriciaTreeIterNode a66 = this.currentSearchTree;
                int i33 = a0.getKey();
                int i34 = this.getBit(i33, (byte)i29);
                label5: {
                    label4: {
                        if(i34 == 0)
                        {
                            break label4;
                        }
                        this.setPosition("3.3.2.1.2.1");
                        this.setPosition("3.3.2.1.2.3");
                        PatriciaTreeIter$Path a67 = this.currentPath;
                        a67.add(false);
                        PatriciaTreeIterNode a68 = this.currentSearchTree;
                        PatriciaTreeIterNode a69 = a68.getRight();
                        this.currentSearchTree = a69;
                        PatriciaTreeIterNode a70 = this.currentSearchTree;
                        int i35 = a70.getBit();
                        int i36 = a66.getBit();
                        int i37 = i35 > i36?0:1;
                        Boolean a71 = new Boolean(i37 != 0);
                        this.isFollowedUpLink = a71;
                        this.setPosition("3.3.2.1.2.4");
                        break label5;
                    }
                    this.setPosition("3.3.2.1.2.1");
                    PatriciaTreeIter$Path a72 = this.currentPath;
                    a72.add(true);
                    PatriciaTreeIterNode a73 = this.currentSearchTree;
                    PatriciaTreeIterNode a74 = a73.getLeft();
                    this.currentSearchTree = a74;
                    PatriciaTreeIterNode a75 = this.currentSearchTree;
                    int i38 = a75.getBit();
                    int i39 = a66.getBit();
                    int i40 = i38 > i39?0:1;
                    Boolean a76 = new Boolean(i40 != 0);
                    this.isFollowedUpLink = a76;
                    this.setPosition("3.3.2.1.2.2");
                }
                PatriciaTreeIterNode a77 = this.currentSearchTree;
                int i41 = a77.getBit();
                com.cim.AIA.DigitalElementArray a78 = this.dataItemBitArray;
                a78.unHighlight();
                if(i41 >= 0)
                {
                    com.cim.AIA.DigitalElementArray a79 = this.dataItemBitArray;
                    a79.highlightBit((byte)i41);
                }
                this.setPosition("3.3.2.1.3");
                i29 = i41;
                a63 = a66;
            }
            this.setPosition("3.3.2.1");
            PatriciaTreeIterNode a80 = this.currentSearchTree;
            this.insertionPoint = a80;
            PatriciaTreeIterNode a81 = this.insertionPoint;
            PatriciaTreeIterDataItem a82 = a81.getDataItem();
            com.cim.AIA.Node a83 = a82.getNode();
            a83.highlight();
            this.setPosition("3.3.2.2");
            this.isFollowedUpLink = null;
            PatriciaTreeIterNode a84 = new PatriciaTreeIterNode(a0, (byte)i19);
            PatriciaTreeIterDataItem a85 = a84.getDataItem();
            com.cim.AIA.Node a86 = a85.getNode();
            a86.highlight();
            this.currentPath = null;
            this.setPosition("3.3.3.1");
            int i42 = a0.getKey();
            int i43 = this.getBit(i42, (byte)i19);
            if(i43 != 0)
            {
                this.setPosition("3.3.3.3.1");
                this.setPosition("3.3.3.3.4");
                PatriciaTreeIterNode a87 = this.currentSearchTree;
                a84.setLeft(a87);
                a84.setRight(a84);
                this.setPosition("3.3.3.3.6");
                com.cim.AIA.DigitalElementArray a88 = this.dataItemBitArray;
                a88.unHighlight();
            }
            else
            {
                this.setPosition("3.3.3.3.1");
                a84.setLeft(a84);
                PatriciaTreeIterNode a89 = this.currentSearchTree;
                a84.setRight(a89);
                this.setPosition("3.3.3.3.3");
                com.cim.AIA.DigitalElementArray a90 = this.dataItemBitArray;
                a90.unHighlight();
            }
            PatriciaTreeIterNode a91 = this.insertionPoint;
            PatriciaTreeIterDataItem a92 = a91.getDataItem();
            com.cim.AIA.Node a93 = a92.getNode();
            a93.unHighlight();
            this.insertionPoint = null;
            int i44 = a0.getKey();
            int i45 = a63.getBit();
            int i46 = this.getBit(i44, (byte)i45);
            if(i46 != 0)
            {
                a63.setRight(a84);
            }
            else
            {
                a63.setLeft(a84);
            }
            this.setPosition("3.3.3.4");
            PatriciaTreeIterDataItem a94 = a84.getDataItem();
            com.cim.AIA.Node a95 = a94.getNode();
            a95.unHighlight();
            this.dataItemBitArray = null;
            this.currentPath = null;
            this.differentiatingBit = null;
        }
    }
    
    private PatriciaTreeIterNode insertKey(PatriciaTreeIterNode a, PatriciaTreeIterDataItem a0, byte a1, PatriciaTreeIterNode a2, boolean b)
    {
        int i = 0;
        PatriciaTreeIterNode a3 = null;
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
                PatriciaTreeIterNode a7 = new PatriciaTreeIterNode(a0, (byte)i0);
                this.setPosition("3.4.1.1.1.1", i1 != 0);
                int i9 = a0.getKey();
                com.cim.AIA.DigitalElementArray a8 = this.initialiseBitElementArray(i9);
                this.dataItemBitArray = a8;
                com.cim.AIA.DigitalElementArray a9 = this.dataItemBitArray;
                java.awt.Color a10 = PatriciaTreeIterColors.INSERT_BIT_ACTIVE_COLOR;
                java.awt.Color a11 = PatriciaTreeIterColors.INSERT_BIT_HIGHLIGHT_COLOR;
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
            java.awt.Color a17 = PatriciaTreeIterColors.INSERT_BIT_ACTIVE_COLOR;
            java.awt.Color a18 = PatriciaTreeIterColors.INSERT_BIT_HIGHLIGHT_COLOR;
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
                    PatriciaTreeIter$Path a21 = this.currentPath;
                    a21.add(false);
                    this.lastNode = a;
                    a.setRightIsKnown(false);
                    this.setPosition("3.4.1.2.1.3", i1 != 0);
                    this.currentBit = null;
                    int i18 = this.isExpanded("3.4.1.2.1")?1:0;
                    if(i18 == 0)
                    {
                        PatriciaTreeIterNode a22 = a.getRight();
                        PatriciaTreeIterNode a23 = this.insertKey(a22, a0, (byte)i0, a, false);
                        a.setRightIsKnown(true);
                        a.setRight(a23);
                        break label7;
                    }
                    else
                    {
                        PatriciaTreeIterNode a24 = a.getRight();
                        PatriciaTreeIterNode a25 = this.insertKey(a24, a0, (byte)i0, a, true);
                        a.setRightIsKnown(true);
                        a.setRight(a25);
                        break label7;
                    }
                }
                com.cim.AIA.DigitalElementArray a26 = this.dataItemBitArray;
                a26.unHighlight();
                PatriciaTreeIter$Path a27 = this.currentPath;
                a27.add(true);
                this.lastNode = a;
                a.setLeftIsKnown(false);
                this.setPosition("3.4.1.2.1.2.1", i1 != 0);
                this.currentBit = null;
                int i19 = this.isExpanded("3.4.1.2.1")?1:0;
                if(i19 == 0)
                {
                    PatriciaTreeIterNode a28 = a.getLeft();
                    PatriciaTreeIterNode a29 = this.insertKey(a28, a0, (byte)i0, a, false);
                    a.setLeftIsKnown(true);
                    a.setLeft(a29);
                }
                else
                {
                    PatriciaTreeIterNode a30 = a.getLeft();
                    PatriciaTreeIterNode a31 = this.insertKey(a30, a0, (byte)i0, a, true);
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
    
    private boolean search(PatriciaTreeIterNode a, PatriciaTreeIterDataItem a0)
    {
        int i = 0;
        int i0 = a0.getKey();
        com.cim.AIA.DigitalElementArray a1 = this.initialiseBitElementArray(i0);
        this.dataItemBitArray = a1;
        com.cim.AIA.DigitalElementArray a2 = this.dataItemBitArray;
        java.awt.Color a3 = PatriciaTreeIterColors.SEARCH_BIT_ACTIVE_COLOR;
        java.awt.Color a4 = PatriciaTreeIterColors.SEARCH_BIT_HIGHLIGHT_COLOR;
        a2.setColors(a3, a4);
        this.setPosition("5");
        PatriciaTreeIter$Path a5 = new PatriciaTreeIter$Path(this, true);
        this.currentPath = a5;
        PatriciaTreeIterNode a6 = a.getLeft();
        this.currentSearchTree = a6;
        PatriciaTreeIterNode a7 = a.getLeft();
        int i1 = a7.getBit();
        com.cim.AIA.DigitalElementArray a8 = this.dataItemBitArray;
        a8.unHighlight();
        if(i1 >= 0)
        {
            com.cim.AIA.DigitalElementArray a9 = this.dataItemBitArray;
            a9.highlightBit((byte)i1);
        }
        PatriciaTreeIterNode a10 = this.currentSearchTree;
        int i2 = a10.getBit();
        int i3 = a.getBit();
        int i4 = i2 > i3?0:1;
        Boolean a11 = new Boolean(i4 != 0);
        this.isFollowedUpLink = a11;
        this.setPosition("5.1.1");
        PatriciaTreeIterNode a12 = a;
        int i5 = i1;
        while(true)
        {
            PatriciaTreeIterNode a13 = this.currentSearchTree;
            int i6 = a13.getBit();
            int i7 = a12.getBit();
            if(i6 <= i7)
            {
                break;
            }
            this.setPosition("5.2.1");
            PatriciaTreeIterNode a14 = this.currentSearchTree;
            int i8 = a0.getKey();
            int i9 = this.getBit(i8, (byte)i5);
            label1: {
                label0: {
                    if(i9 == 0)
                    {
                        break label0;
                    }
                    this.setPosition("5.2.1.1");
                    this.setPosition("5.2.1.3");
                    PatriciaTreeIter$Path a15 = this.currentPath;
                    a15.add(false);
                    PatriciaTreeIterNode a16 = this.currentSearchTree;
                    PatriciaTreeIterNode a17 = a16.getRight();
                    this.currentSearchTree = a17;
                    PatriciaTreeIterNode a18 = this.currentSearchTree;
                    int i10 = a18.getBit();
                    int i11 = a14.getBit();
                    int i12 = i10 > i11?0:1;
                    Boolean a19 = new Boolean(i12 != 0);
                    this.isFollowedUpLink = a19;
                    this.setPosition("5.2.1.4");
                    break label1;
                }
                this.setPosition("5.2.1.1");
                PatriciaTreeIter$Path a20 = this.currentPath;
                a20.add(true);
                PatriciaTreeIterNode a21 = this.currentSearchTree;
                PatriciaTreeIterNode a22 = a21.getLeft();
                this.currentSearchTree = a22;
                PatriciaTreeIterNode a23 = this.currentSearchTree;
                int i13 = a23.getBit();
                int i14 = a14.getBit();
                int i15 = i13 > i14?0:1;
                Boolean a24 = new Boolean(i15 != 0);
                this.isFollowedUpLink = a24;
                this.setPosition("5.2.1.2");
            }
            PatriciaTreeIterNode a25 = this.currentSearchTree;
            int i16 = a25.getBit();
            com.cim.AIA.DigitalElementArray a26 = this.dataItemBitArray;
            a26.unHighlight();
            if(i16 >= 0)
            {
                com.cim.AIA.DigitalElementArray a27 = this.dataItemBitArray;
                a27.highlightBit((byte)i16);
            }
            this.setPosition("5.2.1.5");
            a12 = a14;
            i5 = i16;
        }
        this.setPosition("5.2.1");
        PatriciaTreeIterNode a28 = this.currentSearchTree;
        PatriciaTreeIterDataItem a29 = a28.getDataItem();
        com.cim.AIA.Node a30 = a29.getNode();
        a30.highlight();
        this.setPosition("5.2.2");
        this.isFollowedUpLink = null;
        PatriciaTreeIterNode a31 = this.currentSearchTree;
        PatriciaTreeIterDataItem a32 = a31.getDataItem();
        int i17 = a32.getKey();
        int i18 = a0.getKey();
        if(i17 != i18)
        {
            this.setPosition("5.3.1");
            this.setPosition("5.3.3");
            i = 0;
        }
        else
        {
            this.setPosition("5.3.1");
            i = 1;
        }
        return i != 0;
    }
    
    public com.cim.AIA.Node getLastNode()
    {
        com.cim.AIA.Node a = null;
        PatriciaTreeIterNode a0 = this.currentSearchTree;
        if(a0 == null)
        {
            a = null;
        }
        else
        {
            PatriciaTreeIterNode a1 = this.currentSearchTree;
            com.cim.AIA.Node a2 = a1.getBody();
            a = a2;
        }
        return a;
    }
    
    public Boolean getIsHighlightLoop()
    {
        Boolean a = PatriciaTreeIter.isHighlightLoop;
        return a;
    }
    
    public Boolean getIsFollowedUpLink()
    {
        Boolean a = this.isFollowedUpLink;
        return a;
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
        PatriciaTreeIterNode a2 = this.head;
        this.findAllEndNodes(a2);
        java.util.Vector a3 = this.allEndNodes;
        return a3;
    }
    
    public void findAllEndNodes(PatriciaTreeIterNode a)
    {
        com.cim.AIA.Node[] a0 = new com.cim.AIA.Node[3];
        com.cim.AIA.Node a1 = a.getBody();
        a0[0] = a1;
        int i = a.getBit();
        PatriciaTreeIterNode a2 = a.getLeft();
        int i0 = a2.getBit();
        if(i < i0)
        {
            PatriciaTreeIterNode a3 = a.getLeft();
            this.findAllEndNodes(a3);
        }
        else
        {
            PatriciaTreeIterNode a4 = a.getLeft();
            com.cim.AIA.Node a5 = a4.getBody();
            a0[1] = a5;
        }
        int i1 = a.getBit();
        PatriciaTreeIterNode a6 = a.getRight();
        int i2 = a6.getBit();
        if(i1 < i2)
        {
            PatriciaTreeIterNode a7 = a.getRight();
            this.findAllEndNodes(a7);
        }
        else
        {
            PatriciaTreeIterNode a8 = a.getRight();
            com.cim.AIA.Node a9 = a8.getBody();
            a0[2] = a9;
        }
        int i3 = a.getBit();
        PatriciaTreeIterNode a10 = a.getLeft();
        int i4 = a10.getBit();
        label1: {
            label0: {
                if(i3 >= i4)
                {
                    break label0;
                }
                int i5 = a.getBit();
                PatriciaTreeIterNode a11 = a.getRight();
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
    
    private PatriciaTreeIterDataItem searchKey(PatriciaTreeIterNode a, int i, byte a0)
    {
        PatriciaTreeIterDataItem a1 = null;
        this.currentSearchTree = a;
        int i0 = a0;
        com.cim.AIA.DigitalElementArray a2 = this.dataItemBitArray;
        a2.unHighlight();
        PatriciaTreeIter$Path a3 = this.currentPath;
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
                PatriciaTreeIterDataItem a6 = a.getDataItem();
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
                PatriciaTreeIter$Path a8 = this.currentPath;
                a8.add(false);
                this.lastNode = a;
                PatriciaTreeIterNode a9 = a.getRight();
                int i7 = a.getBit();
                PatriciaTreeIterDataItem a10 = this.searchKey(a9, i, (byte)i7);
                a1 = a10;
            }
            else
            {
                this.setPosition("5.1.3.1");
                PatriciaTreeIter$Path a11 = this.currentPath;
                a11.add(true);
                this.lastNode = a;
                PatriciaTreeIterNode a12 = a.getLeft();
                int i8 = a.getBit();
                PatriciaTreeIterDataItem a13 = this.searchKey(a12, i, (byte)i8);
                a1 = a13;
            }
        }
        return a1;
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
    
    private boolean isExpanded(String s)
    {
        com.cim.AIA.AlgorithmThread a = this.algorithmThread;
        PatriciaTreeIterThread dummy = (PatriciaTreeIterThread)a;
        PatriciaTreeIterThread a0 = (PatriciaTreeIterThread)a;
        com.cim.AIA.AlgorithmWindow a1 = a0.getWindow();
        int i = a1.isExpanded(s)?1:0;
        return i != 0;
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
        String s = localization.Messages.getString("PatriciaTreeIter.0");
        PatriciaTreeIter.BUILD_MODE_LABEL = s;
        String s0 = localization.Messages.getString("PatriciaTreeIter.1");
        PatriciaTreeIter.SEARCH_MODE_LABEL = s0;
        java.awt.Color a = java.awt.Color.orange;
        PatriciaTreeIter.searchTreeColor = a;
        java.awt.Color a0 = java.awt.Color.gray;
        PatriciaTreeIter.postSearchTreeColor = a0;
    }
}