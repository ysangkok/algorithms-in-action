public class RadixTrieIter extends com.cim.AIA.Algorithm implements com.cim.AIA.MethodSelectionListener, com.cim.AIA.ControlListener {
    final private static String BUILD_MODE_LABEL;
    final private static String SEARCH_MODE_LABEL;
    final private static int BUILD_MODE = 11;
    final private static int SEARCH_MODE = 12;
    private int nextMode;
    private int executingMode;
    public boolean isBackMode;
    final private static int DEFAULT_ELEMENT_WIDTH = 20;
    private RadixTrieIterDigitalElementArray insertDataBitArray;
    private RadixTrieIterDigitalElementArray searchDataBitArray;
    private RadixTrieIterDigitalElementArray compareDataBitArray;
    private RadixTrieIterDigitalElementArray finalCompareDataBitArray;
    private java.util.Vector insertedData;
    private RadixTrieIterNode savedTree;
    private int insertPos;
    private int searchPos;
    private RadixTrieIterNode head;
    private com.cim.AIA.ElementArray insertData;
    private com.cim.AIA.ElementArray searchData;
    private com.cim.AIA.ElementArray newSearchData;
    private com.cim.AIA.ElementArray newInsertData;
    int mostSignificantBit;
    private RadixTrieIterNode currentNode;
    private RadixTrieIterNode pathNode;
    private int currentLevel;
    
    public RadixTrieIter(com.cim.AIA.AlgorithmThread a, Object a0)
    {
        super(a, a0);
        this.isBackMode = false;
        RadixTrieIterNullNode a1 = new RadixTrieIterNullNode((RadixTrieIterInternalNode)null);
        this.savedTree = a1;
        RadixTrieIterNullNode a2 = new RadixTrieIterNullNode((RadixTrieIterInternalNode)null);
        this.head = a2;
        this.mostSignificantBit = 0;
        this.currentLevel = -1;
        this.executingMode = 11;
        int i = this.executingMode;
        this.nextMode = i;
        this.changeData(a0);
        com.cim.AIA.ElementArray a3 = this.newInsertData;
        this.insertData = a3;
        com.cim.AIA.ElementArray a4 = this.newSearchData;
        this.searchData = a4;
    }
    
    public void reInitialise(Object a)
    {
        this.changeData(a);
        this.mostSignificantBit = 0;
        int i = this.executingMode;
        label1: {
            label0: {
                if(i == 11)
                {
                    break label0;
                }
                int i0 = this.nextMode;
                if(i0 == 11)
                {
                    break label0;
                }
                com.cim.AIA.ElementArray a0 = this.insertData;
                if(a0 != null)
                {
                    break label1;
                }
            }
            com.cim.AIA.ElementArray a1 = this.newInsertData;
            this.insertData = a1;
        }
        com.cim.AIA.ElementArray a2 = this.newSearchData;
        this.searchData = a2;
        int i1 = 0;
        while(true)
        {
            com.cim.AIA.ElementArray a3 = this.insertData;
            int i2 = a3.getSize();
            if(i1 >= i2)
            {
                break;
            }
            com.cim.AIA.ElementArray a4 = this.insertData;
            com.cim.AIA.Element a5 = a4.getElement(i1);
            Object a6 = a5.getObject();
            Integer dummy = (Integer)a6;
            Integer a7 = (Integer)a6;
            int i3 = a7.intValue();
            String s = Integer.toBinaryString(i3);
            int i4 = this.mostSignificantBit;
            int i5 = s.length();
            if(i4 < i5)
            {
                int i6 = s.length();
                this.mostSignificantBit = i6;
            }
            int i7 = i1 + 1;
            i1 = i7;
        }
        int i8 = 0;
        while(true)
        {
            com.cim.AIA.ElementArray a8 = this.searchData;
            int i9 = a8.getSize();
            if(i8 >= i9)
            {
                break;
            }
            com.cim.AIA.ElementArray a9 = this.searchData;
            com.cim.AIA.Element a10 = a9.getElement(i8);
            Object a11 = a10.getObject();
            Integer dummy0 = (Integer)a11;
            Integer a12 = (Integer)a11;
            int i10 = a12.intValue();
            String s0 = Integer.toBinaryString(i10);
            int i11 = this.mostSignificantBit;
            int i12 = s0.length();
            if(i11 < i12)
            {
                int i13 = s0.length();
                this.mostSignificantBit = i13;
            }
            int i14 = i8 + 1;
            i8 = i14;
        }
        int i15 = this.mostSignificantBit;
        int i16 = i15 - 1;
        this.mostSignificantBit = i16;
        int i17 = this.executingMode;
        label3: {
            label2: {
                if(i17 != 11)
                {
                    break label2;
                }
                RadixTrieIterNullNode a13 = new RadixTrieIterNullNode((RadixTrieIterInternalNode)null);
                this.savedTree = a13;
                this.searchPos = 0;
                this.insertPos = 0;
                break label3;
            }
            int i18 = this.executingMode;
            if(i18 == 12)
            {
                this.searchPos = 0;
            }
        }
        RadixTrieIterNode a14 = this.savedTree;
        this.head = a14;
        java.util.Vector a15 = new java.util.Vector();
        this.insertedData = a15;
        java.util.Vector a16 = this.insertedData;
        RadixTrieIterNode a17 = this.head;
        this.radixTrieToVector(a16, a17);
        this.currentLevel = -1;
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
        com.cim.AIA.ElementArray a4 = new com.cim.AIA.ElementArray(0, 0);
        this.newSearchData = a4;
        com.cim.AIA.ElementArray a5 = this.newSearchData;
        a5.setColumGap(0);
        com.cim.AIA.ElementArray a6 = this.newSearchData;
        a6.setElementWidth(20);
        int i = 0;
        while(true)
        {
            int[] dummy = (int[])a;
            int[] a7 = (int[])a;
            int i0 = a7.length;
            if(i >= i0)
            {
                return;
            }
            com.cim.AIA.ElementArray a8 = this.newInsertData;
            int[] dummy0 = (int[])a;
            int[] a9 = (int[])a;
            int i1 = a9[i];
            Integer a10 = new Integer(i1);
            com.cim.AIA.Node a11 = new com.cim.AIA.Node((Object)a10, i);
            a8.add((Object)a11, i);
            com.cim.AIA.ElementArray a12 = this.newInsertData;
            com.cim.AIA.Element a13 = a12.getElement(i);
            com.cim.AIA.Node dummy1 = (com.cim.AIA.Node)a13;
            com.cim.AIA.Node a14 = (com.cim.AIA.Node)a13;
            java.awt.Color a15 = RadixTrieIterColors.INSERT_ACTIVE_COLOR;
            a14.setBackgroundColor(a15);
            int i2 = this.isBackMode?1:0;
            label1: {
                label0: {
                    if(i2 == 0)
                    {
                        break label0;
                    }
                    com.cim.AIA.ElementArray a16 = this.searchData;
                    com.cim.AIA.Element a17 = a16.getElement(i);
                    Object a18 = a17.getObject();
                    Integer dummy2 = (Integer)a18;
                    Integer a19 = (Integer)a18;
                    int i3 = a19.intValue();
                    com.cim.AIA.ElementArray a20 = this.newSearchData;
                    Integer a21 = new Integer(i3);
                    com.cim.AIA.Node a22 = new com.cim.AIA.Node((Object)a21, i);
                    a20.add((Object)a22, i);
                    break label1;
                }
                int i4 = i % 2;
                if(i4 != 0)
                {
                    com.cim.AIA.ElementArray a23 = this.newSearchData;
                    int[] dummy3 = (int[])a;
                    int[] a24 = (int[])a;
                    int i5 = a24[i];
                    Integer a25 = new Integer(i5);
                    com.cim.AIA.Node a26 = new com.cim.AIA.Node((Object)a25, i);
                    a23.add((Object)a26, i);
                }
                else
                {
                    com.cim.AIA.ElementArray a27 = this.newSearchData;
                    int i6 = a0.nextInt();
                    int i7 = Math.abs(i6);
                    int i8 = i7 % 98;
                    int i9 = i8 + 1;
                    Integer a28 = new Integer(i9);
                    com.cim.AIA.Node a29 = new com.cim.AIA.Node((Object)a28, i);
                    a27.add((Object)a29, i);
                }
            }
            com.cim.AIA.ElementArray a30 = this.newSearchData;
            com.cim.AIA.Element a31 = a30.getElement(i);
            com.cim.AIA.Node dummy4 = (com.cim.AIA.Node)a31;
            com.cim.AIA.Node a32 = (com.cim.AIA.Node)a31;
            java.awt.Color a33 = RadixTrieIterColors.SEARCH_ACTIVE_COLOR;
            a32.setBackgroundColor(a33);
            int i10 = i + 1;
            i = i10;
        }
    }
    
    protected void initialiseMethods(String s, com.cim.AIA.MethodSelectable a)
    {
        Object a0 = a;
        String s0 = RadixTrieIter.BUILD_MODE_LABEL;
        String s1 = localization.Messages.getString("RadixTrieIter.2");
        com.cim.AIA.Logger a1 = this.getLogger();
        com.cim.AIA.BreakPoint a2 = this.getBreakPoint();
        com.cim.AIA.MethodSelection a3 = new com.cim.AIA.MethodSelection(s0, s, s1, "3a", a1, a2);
        String s2 = RadixTrieIter.SEARCH_MODE_LABEL;
        String s3 = localization.Messages.getString("RadixTrieIter.3");
        com.cim.AIA.Logger a4 = this.getLogger();
        com.cim.AIA.BreakPoint a5 = this.getBreakPoint();
        com.cim.AIA.MethodSelection a6 = new com.cim.AIA.MethodSelection(s2, s, s3, "5a", a4, a5);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelection(a3, true);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelection(a6, false);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelectionListener((com.cim.AIA.MethodSelectionListener)this);
    }
    
    public void processMethodSelectionEvent(com.cim.AIA.MethodSelectionEvent a)
    {
        String s = RadixTrieIter.BUILD_MODE_LABEL;
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
            String s1 = RadixTrieIter.SEARCH_MODE_LABEL;
            String s2 = a.getMethodName();
            int i0 = s1.compareTo(s2);
            if(i0 == 0)
            {
                this.nextMode = 12;
            }
        }
    }
    
    private RadixTrieIterDigitalElementArray initialiseBitElementArray(int i)
    {
        int i0 = this.mostSignificantBit;
        RadixTrieIterDigitalElementArray a = new RadixTrieIterDigitalElementArray(i, i0);
        return a;
    }
    
    public RadixTrieIterNode getHead()
    {
        RadixTrieIterNode a = this.head;
        return a;
    }
    
    public java.util.Vector getInsertedData()
    {
        java.util.Vector a = this.insertedData;
        return a;
    }
    
    public RadixTrieIterDigitalElementArray getInsertBitArray()
    {
        RadixTrieIterDigitalElementArray a = null;
        int i = this.executingMode;
        if(i != 11)
        {
            a = null;
        }
        else
        {
            RadixTrieIterDigitalElementArray a0 = this.insertDataBitArray;
            a = a0;
        }
        return a;
    }
    
    public RadixTrieIterDigitalElementArray getSearchBitArray()
    {
        RadixTrieIterDigitalElementArray a = null;
        int i = this.executingMode;
        if(i != 12)
        {
            a = null;
        }
        else
        {
            RadixTrieIterDigitalElementArray a0 = this.searchDataBitArray;
            a = a0;
        }
        return a;
    }
    
    public RadixTrieIterDigitalElementArray getFinalCompareBitArray()
    {
        RadixTrieIterDigitalElementArray a = null;
        int i = this.executingMode;
        if(i != 12)
        {
            a = null;
        }
        else
        {
            RadixTrieIterDigitalElementArray a0 = this.finalCompareDataBitArray;
            a = a0;
        }
        return a;
    }
    
    public RadixTrieIterDigitalElementArray getCompareBitArray()
    {
        RadixTrieIterDigitalElementArray a = this.compareDataBitArray;
        return a;
    }
    
    public com.cim.AIA.ElementArray getInsertData()
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
    
    public com.cim.AIA.ElementArray getSearchData()
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
    
    public RadixTrieIterNode getCurrentNode()
    {
        RadixTrieIterNode a = this.head;
        this.unHighlightPath(a);
        RadixTrieIterNode a0 = this.pathNode;
        this.highlightPath(a0);
        RadixTrieIterNode a1 = this.currentNode;
        return a1;
    }
    
    public int getMostSignificantBit()
    {
        int i = this.mostSignificantBit;
        return i;
    }
    
    public int getCurrentLevel()
    {
        int i = this.currentLevel;
        return i;
    }
    
    private void unHighlightPath(RadixTrieIterNode a)
    {
        label0: {
            if(a == null)
            {
                break label0;
            }
            a.setIsOnPath(false);
            int i = a instanceof RadixTrieIterInternalNode?1:0;
            if(i != 0)
            {
                RadixTrieIterInternalNode dummy = (RadixTrieIterInternalNode)a;
                RadixTrieIterInternalNode a0 = (RadixTrieIterInternalNode)a;
                RadixTrieIterNode a1 = a0.getLeft();
                this.unHighlightPath(a1);
                RadixTrieIterInternalNode dummy0 = (RadixTrieIterInternalNode)a;
                RadixTrieIterInternalNode a2 = (RadixTrieIterInternalNode)a;
                RadixTrieIterNode a3 = a2.getRight();
                this.unHighlightPath(a3);
            }
        }
    }
    
    private void highlightPath(RadixTrieIterNode a)
    {
        if(a != null)
        {
            a.setIsOnPath(true);
            RadixTrieIterInternalNode a0 = a.getParent();
            this.highlightPath((RadixTrieIterNode)a0);
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
    
    private void printTrie(RadixTrieIterNode a)
    {
        int i = a instanceof RadixTrieIterInternalNode?1:0;
        if(i != 0)
        {
            RadixTrieIterInternalNode dummy = (RadixTrieIterInternalNode)a;
            RadixTrieIterInternalNode a0 = (RadixTrieIterInternalNode)a;
            RadixTrieIterNode a1 = a0.getLeft();
            this.printTrie(a1);
            RadixTrieIterInternalNode dummy0 = (RadixTrieIterInternalNode)a;
            RadixTrieIterInternalNode a2 = (RadixTrieIterInternalNode)a;
            RadixTrieIterNode a3 = a2.getRight();
            this.printTrie(a3);
        }
        int i0 = a instanceof RadixTrieIterExternalNode?1:0;
        if(i0 != 0)
        {
            java.io.PrintStream a4 = System.out;
            StringBuilder a5 = new StringBuilder();
            RadixTrieIterExternalNode dummy1 = (RadixTrieIterExternalNode)a;
            RadixTrieIterExternalNode a6 = (RadixTrieIterExternalNode)a;
            int i1 = a6.getKey();
            StringBuilder a7 = a5.append(i1);
            StringBuilder a8 = a7.append(" ");
            RadixTrieIterExternalNode dummy2 = (RadixTrieIterExternalNode)a;
            RadixTrieIterExternalNode a9 = (RadixTrieIterExternalNode)a;
            int i2 = a9.getKey();
            String s = Integer.toBinaryString(i2);
            StringBuilder a10 = a8.append(s);
            String s0 = a10.toString();
            a4.println(s0);
        }
    }
    
    private void radixTrieToVector(java.util.Vector a, RadixTrieIterNode a0)
    {
        label1: {
            label0: {
                int i = a0 instanceof RadixTrieIterInternalNode?1:0;
                if(i == 0)
                {
                    break label0;
                }
                RadixTrieIterInternalNode dummy = (RadixTrieIterInternalNode)a0;
                RadixTrieIterInternalNode a1 = (RadixTrieIterInternalNode)a0;
                RadixTrieIterNode a2 = a1.getLeft();
                this.radixTrieToVector(a, a2);
                RadixTrieIterInternalNode dummy0 = (RadixTrieIterInternalNode)a0;
                RadixTrieIterInternalNode a3 = (RadixTrieIterInternalNode)a0;
                RadixTrieIterNode a4 = a3.getRight();
                this.radixTrieToVector(a, a4);
                break label1;
            }
            int i0 = a0 instanceof RadixTrieIterExternalNode?1:0;
            if(i0 != 0)
            {
                a.addElement((Object)a0);
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
    
    protected void run()
    {
        int i = this.executingMode;
        label1: {
            label0: {
                int i0 = 0;
                switch(i){
                    case 12: {
                        i0 = this.searchPos;
                        break;
                    }
                    case 11: {
                        break label0;
                    }
                }
                int i1 = i0;
                while(true)
                {
                    com.cim.AIA.ElementArray a = this.searchData;
                    int i2 = a.getSize();
                    if(i1 >= i2)
                    {
                        break label1;
                    }
                    com.cim.AIA.ElementArray a0 = this.searchData;
                    com.cim.AIA.Element a1 = a0.getElement(i1);
                    com.cim.AIA.Node dummy = (com.cim.AIA.Node)a1;
                    com.cim.AIA.Node a2 = (com.cim.AIA.Node)a1;
                    Object a3 = a2.getObject();
                    String s = a3.toString();
                    Object a4 = a2.getObject();
                    Integer dummy0 = (Integer)a4;
                    Integer a5 = (Integer)a4;
                    int i3 = a5.intValue();
                    com.cim.AIA.DataItem a6 = new com.cim.AIA.DataItem(s, i3);
                    int i4 = a6.getKey();
                    RadixTrieIterDigitalElementArray a7 = this.initialiseBitElementArray(i4);
                    this.searchDataBitArray = a7;
                    RadixTrieIterDigitalElementArray a8 = this.searchDataBitArray;
                    java.awt.Color a9 = RadixTrieIterColors.SEARCH_BIT_ACTIVE_COLOR;
                    java.awt.Color a10 = RadixTrieIterColors.SEARCH_BIT_HIGHLIGHT_COLOR;
                    a8.setColors(a9, a10);
                    int i5 = this.enabled?1:0;
                    label2: {
                        if(i5 == 0)
                        {
                            break label2;
                        }
                        java.awt.Color a11 = RadixTrieIterColors.SEARCH_HIGHLIGHT_COLOR;
                        a2.setBackgroundColor(a11);
                        RadixTrieIterNode a12 = this.head;
                        int i6 = this.search(a12, a6, 0)?1:0;
                        if(i6 == 0)
                        {
                            com.cim.AIA.StringMarker a13 = new com.cim.AIA.StringMarker("N");
                            a2.addMarker(a13);
                            com.cim.AIA.StringMarker a14 = new com.cim.AIA.StringMarker("o");
                            a2.addMarker(a14);
                            com.cim.AIA.StringMarker a15 = new com.cim.AIA.StringMarker("t");
                            a2.addMarker(a15);
                            com.cim.AIA.StringMarker a16 = new com.cim.AIA.StringMarker(" ");
                            a2.addMarker(a16);
                            com.cim.AIA.StringMarker a17 = new com.cim.AIA.StringMarker("F");
                            a2.addMarker(a17);
                            com.cim.AIA.StringMarker a18 = new com.cim.AIA.StringMarker("o");
                            a2.addMarker(a18);
                            com.cim.AIA.StringMarker a19 = new com.cim.AIA.StringMarker("u");
                            a2.addMarker(a19);
                            com.cim.AIA.StringMarker a20 = new com.cim.AIA.StringMarker("n");
                            a2.addMarker(a20);
                            com.cim.AIA.StringMarker a21 = new com.cim.AIA.StringMarker("d");
                            a2.addMarker(a21);
                        }
                        else
                        {
                            com.cim.AIA.StringMarker a22 = new com.cim.AIA.StringMarker("F");
                            a2.addMarker(a22);
                            com.cim.AIA.StringMarker a23 = new com.cim.AIA.StringMarker("o");
                            a2.addMarker(a23);
                            com.cim.AIA.StringMarker a24 = new com.cim.AIA.StringMarker("u");
                            a2.addMarker(a24);
                            com.cim.AIA.StringMarker a25 = new com.cim.AIA.StringMarker("n");
                            a2.addMarker(a25);
                            com.cim.AIA.StringMarker a26 = new com.cim.AIA.StringMarker("d");
                            a2.addMarker(a26);
                        }
                        this.clearPointers();
                        RadixTrieIterExternalNode.clearCompareNode();
                        java.util.Vector a27 = new java.util.Vector();
                        this.insertedData = a27;
                        java.util.Vector a28 = this.insertedData;
                        RadixTrieIterNode a29 = this.head;
                        this.radixTrieToVector(a28, a29);
                        java.awt.Color a30 = RadixTrieIterColors.SEARCH_DONE_COLOR;
                        a2.setBackgroundColor(a30);
                        int i7 = this.searchPos;
                        int i8 = i7 + 1;
                        this.searchPos = i8;
                    }
                    this.searchDataBitArray = null;
                    int i9 = i1 + 1;
                    i1 = i9;
                }
            }
            java.util.Vector a31 = new java.util.Vector();
            this.insertedData = a31;
            java.util.Vector a32 = this.insertedData;
            RadixTrieIterNode a33 = this.head;
            this.radixTrieToVector(a32, a33);
            int i10 = this.insertPos;
            int i11 = i10;
            while(true)
            {
                com.cim.AIA.ElementArray a34 = this.insertData;
                int i12 = a34.getSize();
                if(i11 >= i12)
                {
                    break;
                }
                com.cim.AIA.ElementArray a35 = this.insertData;
                com.cim.AIA.Element a36 = a35.getElement(i11);
                Object a37 = a36.getObject();
                String s0 = a37.toString();
                com.cim.AIA.ElementArray a38 = this.insertData;
                com.cim.AIA.Element a39 = a38.getElement(i11);
                Object a40 = a39.getObject();
                Integer dummy1 = (Integer)a40;
                Integer a41 = (Integer)a40;
                int i13 = a41.intValue();
                com.cim.AIA.DataItem a42 = new com.cim.AIA.DataItem(s0, i13);
                int i14 = a42.getKey();
                RadixTrieIterDigitalElementArray a43 = this.initialiseBitElementArray(i14);
                this.insertDataBitArray = a43;
                RadixTrieIterDigitalElementArray a44 = this.insertDataBitArray;
                java.awt.Color a45 = RadixTrieIterColors.INSERT_BIT_ACTIVE_COLOR;
                java.awt.Color a46 = RadixTrieIterColors.INSERT_BIT_HIGHLIGHT_COLOR;
                a44.setColors(a45, a46);
                try
                {
                    int i15 = this.enabled?1:0;
                    if(i15 != 0)
                    {
                        com.cim.AIA.ElementArray a47 = this.insertData;
                        com.cim.AIA.Element a48 = a47.getElement(i11);
                        com.cim.AIA.Node dummy2 = (com.cim.AIA.Node)a48;
                        com.cim.AIA.Node a49 = (com.cim.AIA.Node)a48;
                        java.awt.Color a50 = RadixTrieIterColors.INSERT_HIGHLIGHT_COLOR;
                        a49.setBackgroundColor(a50);
                        RadixTrieIterNode a51 = this.head;
                        RadixTrieIterNode a52 = this.insert(a51, a42, 0, true, i11);
                        this.head = a52;
                        this.clearPointers();
                        RadixTrieIterExternalNode.clearNewestNode();
                        RadixTrieIterExternalNode.clearCompareNode();
                        java.util.Vector a53 = new java.util.Vector();
                        this.insertedData = a53;
                        java.util.Vector a54 = this.insertedData;
                        RadixTrieIterNode a55 = this.head;
                        this.radixTrieToVector(a54, a55);
                        int i16 = this.insertPos;
                        int i17 = i16 + 1;
                        this.insertPos = i17;
                        com.cim.AIA.ElementArray a56 = this.insertData;
                        com.cim.AIA.Element a57 = a56.getElement(i11);
                        com.cim.AIA.Node dummy3 = (com.cim.AIA.Node)a57;
                        com.cim.AIA.Node a58 = (com.cim.AIA.Node)a57;
                        java.awt.Color a59 = RadixTrieIterColors.INSERT_DONE_COLOR;
                        a58.setBackgroundColor(a59);
                    }
                }
                catch(Exception a60)
                {
                    this.clearPointers();
                    java.io.PrintStream a61 = System.out;
                    StringBuilder a62 = new StringBuilder();
                    StringBuilder a63 = a62.append((Object)a60);
                    StringBuilder a64 = a63.append("Already inserted exception caught");
                    String s1 = a64.toString();
                    a61.println(s1);
                }
                this.insertDataBitArray = null;
                int i18 = i11 + 1;
                i11 = i18;
            }
        }
    }
    
    private RadixTrieIterNode insert(RadixTrieIterNode a, com.cim.AIA.DataItem a0, int i, boolean b, int i0)
    {
        RadixTrieIterNode a1 = null;
        this.currentNode = a;
        RadixTrieIterNode a2 = this.currentNode;
        this.pathNode = a2;
        this.setPosition("3.0");
        if(a == null)
        {
            RuntimeException a3 = new RuntimeException("trie should not be null");
            throw a3;
        }
        this.setPosition("3.1.1");
        label1: {
            RadixTrieIterNode a4 = null;
            label0: {
                int i1 = a instanceof RadixTrieIterNullNode?1:0;
                if(i1 == 0)
                {
                    break label0;
                }
                RadixTrieIterExternalNode a5 = new RadixTrieIterExternalNode(a0);
                a5.setIsLinkedIn();
                a5.setLevel(0);
                this.pathNode = a5;
                this.head = a5;
                this.currentNode = a5;
                this.setPosition("3.1.1.1");
                a1 = a5;
                break label1;
            }
            this.currentLevel = 0;
            RadixTrieIterDigitalElementArray a6 = this.insertDataBitArray;
            a6.unHighlight();
            RadixTrieIterDigitalElementArray a7 = this.insertDataBitArray;
            int i2 = this.currentLevel;
            int i3 = (byte)i2;
            a7.highlightBit((byte)i3);
            this.setPosition("3.2.2");
            while(true)
            {
                RadixTrieIterNode a8 = this.currentNode;
                int i4 = a8 instanceof RadixTrieIterInternalNode?1:0;
                if(i4 == 0)
                {
                    break;
                }
                this.setPosition("3.3.1");
                this.setPosition("3.3.1.2.1");
                int i5 = this.currentLevel;
                int i6 = a0.getKey();
                int i7 = this.getBit(i5, i6);
                if(i7 != 0)
                {
                    this.setPosition("3.3.1.2.2");
                    RadixTrieIterNode a9 = this.currentNode;
                    RadixTrieIterInternalNode dummy = (RadixTrieIterInternalNode)a9;
                    RadixTrieIterInternalNode a10 = (RadixTrieIterInternalNode)a9;
                    RadixTrieIterNode a11 = a10.getRight();
                    this.currentNode = a11;
                    RadixTrieIterNode a12 = this.currentNode;
                    this.pathNode = a12;
                    this.setPosition("3.3.1.2.2.1");
                }
                else
                {
                    RadixTrieIterNode a13 = this.currentNode;
                    RadixTrieIterInternalNode dummy0 = (RadixTrieIterInternalNode)a13;
                    RadixTrieIterInternalNode a14 = (RadixTrieIterInternalNode)a13;
                    RadixTrieIterNode a15 = a14.getLeft();
                    this.currentNode = a15;
                    RadixTrieIterNode a16 = this.currentNode;
                    this.pathNode = a16;
                    this.setPosition("3.3.1.2.1.1");
                }
                RadixTrieIterNode a17 = this.currentNode;
                int i8 = a17 instanceof RadixTrieIterInternalNode?1:0;
                if(i8 == 0)
                {
                    int i9 = this.currentLevel;
                    int i10 = i9 + 1;
                    this.currentLevel = i10;
                    this.setPosition("3.3.1.4");
                }
                else
                {
                    int i11 = this.currentLevel;
                    int i12 = i11 + 1;
                    this.currentLevel = i12;
                    RadixTrieIterDigitalElementArray a18 = this.insertDataBitArray;
                    a18.unHighlight();
                    RadixTrieIterDigitalElementArray a19 = this.insertDataBitArray;
                    int i13 = this.currentLevel;
                    int i14 = (byte)i13;
                    a19.highlightBit((byte)i14);
                    this.setPosition("3.3.1.4");
                }
            }
            this.setPosition("3.3.1");
            this.setPosition("3.3.2");
            RadixTrieIterNode a20 = this.currentNode;
            RadixTrieIterInternalNode a21 = a20.getParent();
            this.pathNode = a21;
            this.setPosition("3.4.1");
            RadixTrieIterNode a22 = this.currentNode;
            label2: {
                int i15 = a22 instanceof RadixTrieIterNullNode?1:0;
                if(i15 == 0)
                {
                    break label2;
                }
                RadixTrieIterExternalNode a23 = new RadixTrieIterExternalNode(a0);
                int i16 = this.currentLevel;
                int i17 = i16 - 1;
                int i18 = a0.getKey();
                int i19 = this.getBit(i17, i18);
                if(i19 != 0)
                {
                    RadixTrieIterNode a24 = this.currentNode;
                    RadixTrieIterInternalNode a25 = a24.getParent();
                    a25.setRight((RadixTrieIterNode)a23);
                }
                else
                {
                    RadixTrieIterNode a26 = this.currentNode;
                    RadixTrieIterInternalNode a27 = a26.getParent();
                    a27.setLeft((RadixTrieIterNode)a23);
                }
                int i20 = this.currentLevel;
                a23.setLevel(i20);
                a23.setIsLinkedIn();
                this.setPosition("3.4.1.1");
                a1 = a;
                break label1;
            }
            this.setPosition("3.4.2");
            RadixTrieIterNode a28 = this.currentNode;
            RadixTrieIterExternalNode dummy1 = (RadixTrieIterExternalNode)a28;
            RadixTrieIterExternalNode a29 = (RadixTrieIterExternalNode)a28;
            int i21 = a29.getKey();
            int i22 = a0.getKey();
            label3: {
                if(i21 != i22)
                {
                    break label3;
                }
                this.setPosition("3.4.2.1");
                this.setPosition("3.4.2.2");
                RadixTrieIterDigitalElementArray a30 = this.insertDataBitArray;
                String s = localization.Messages.getString("RadixTrieIter.4");
                com.cim.AIA.StringMarker a31 = new com.cim.AIA.StringMarker(s);
                a30.addMarker(a31);
                this.compareDataBitArray = null;
                this.setPosition("3.4.2.2.1");
                RadixTrieIterDigitalElementArray a32 = this.insertDataBitArray;
                a32.removeAllMarkers();
                a1 = a;
                break label1;
            }
            this.setPosition("3.4.2.1");
            RadixTrieIterDigitalElementArray a33 = this.initialiseBitElementArray(i21);
            this.compareDataBitArray = a33;
            RadixTrieIterDigitalElementArray a34 = this.compareDataBitArray;
            java.awt.Color a35 = RadixTrieIterColors.COMPARE_BIT_ACTIVE_COLOR;
            java.awt.Color a36 = RadixTrieIterColors.COMPARE_BIT_HIGHLIGHT_COLOR;
            a34.setColors(a35, a36);
            RadixTrieIterNode a37 = this.currentNode;
            RadixTrieIterExternalNode dummy2 = (RadixTrieIterExternalNode)a37;
            RadixTrieIterExternalNode a38 = (RadixTrieIterExternalNode)a37;
            RadixTrieIterExternalNode.setCompareNode(a38);
            RadixTrieIterInternalNode a39 = new RadixTrieIterInternalNode();
            RadixTrieIterNode a40 = this.currentNode;
            RadixTrieIterExternalNode dummy3 = (RadixTrieIterExternalNode)a40;
            RadixTrieIterExternalNode a41 = (RadixTrieIterExternalNode)a40;
            a39.setIsLinkedIn();
            int i23 = this.currentLevel;
            int i24 = i23 - 1;
            int i25 = a0.getKey();
            int i26 = this.getBit(i24, i25);
            label5: {
                label4: {
                    if(i26 == 0)
                    {
                        break label4;
                    }
                    RadixTrieIterNode a42 = this.currentNode;
                    RadixTrieIterInternalNode a43 = a42.getParent();
                    if(a43 == null)
                    {
                        this.head = a39;
                        a4 = a39;
                        break label5;
                    }
                    else
                    {
                        RadixTrieIterNode a44 = this.currentNode;
                        RadixTrieIterInternalNode a45 = a44.getParent();
                        a45.setRight((RadixTrieIterNode)a39);
                        a4 = a;
                        break label5;
                    }
                }
                RadixTrieIterNode a46 = this.currentNode;
                RadixTrieIterInternalNode a47 = a46.getParent();
                if(a47 == null)
                {
                    this.head = a39;
                    a4 = a39;
                }
                else
                {
                    RadixTrieIterNode a48 = this.currentNode;
                    RadixTrieIterInternalNode a49 = a48.getParent();
                    a49.setLeft((RadixTrieIterNode)a39);
                    a4 = a;
                }
            }
            this.currentNode = a39;
            RadixTrieIterDigitalElementArray a50 = this.compareDataBitArray;
            a50.unHighlight();
            RadixTrieIterDigitalElementArray a51 = this.insertDataBitArray;
            a51.unHighlight();
            RadixTrieIterDigitalElementArray a52 = this.compareDataBitArray;
            int i27 = this.currentLevel;
            int i28 = (byte)i27;
            a52.highlightBit((byte)i28);
            RadixTrieIterDigitalElementArray a53 = this.insertDataBitArray;
            int i29 = this.currentLevel;
            int i30 = (byte)i29;
            a53.highlightBit((byte)i30);
            this.setPosition("3.5.1");
            RadixTrieIterInternalNode a54 = a39;
            while(true)
            {
                int i31 = this.currentLevel;
                int i32 = this.getBit(i31, i21);
                int i33 = this.currentLevel;
                int i34 = a0.getKey();
                int i35 = this.getBit(i33, i34);
                if(i32 != i35)
                {
                    break;
                }
                this.setPosition("3.5.2");
                int i36 = this.currentLevel;
                int i37 = a0.getKey();
                int i38 = this.getBit(i36, i37);
                if(i38 != 0)
                {
                    this.setPosition("3.5.2.2");
                    this.setPosition("3.5.2.3");
                    int i39 = this.currentLevel;
                    int i40 = i39 + 1;
                    this.currentLevel = i40;
                    RadixTrieIterDigitalElementArray a55 = this.compareDataBitArray;
                    a55.unHighlight();
                    RadixTrieIterDigitalElementArray a56 = this.insertDataBitArray;
                    a56.unHighlight();
                    RadixTrieIterDigitalElementArray a57 = this.compareDataBitArray;
                    int i41 = this.currentLevel;
                    int i42 = (byte)i41;
                    a57.highlightBit((byte)i42);
                    RadixTrieIterDigitalElementArray a58 = this.insertDataBitArray;
                    int i43 = this.currentLevel;
                    int i44 = (byte)i43;
                    a58.highlightBit((byte)i44);
                    this.setPosition("3.5.2.3.1");
                    RadixTrieIterInternalNode a59 = new RadixTrieIterInternalNode();
                    this.pathNode = a59;
                    a54.setRight((RadixTrieIterNode)a59);
                    RadixTrieIterNode a60 = a54.getRight();
                    RadixTrieIterInternalNode dummy4 = (RadixTrieIterInternalNode)a60;
                    RadixTrieIterInternalNode a61 = (RadixTrieIterInternalNode)a60;
                    a59.setIsLinkedIn();
                    this.currentNode = a61;
                    this.setPosition("3.5.2.3.2");
                    a54 = a61;
                }
                else
                {
                    this.setPosition("3.5.2.2");
                    int i45 = this.currentLevel;
                    int i46 = i45 + 1;
                    this.currentLevel = i46;
                    RadixTrieIterDigitalElementArray a62 = this.compareDataBitArray;
                    a62.unHighlight();
                    RadixTrieIterDigitalElementArray a63 = this.insertDataBitArray;
                    a63.unHighlight();
                    RadixTrieIterDigitalElementArray a64 = this.compareDataBitArray;
                    int i47 = this.currentLevel;
                    int i48 = (byte)i47;
                    a64.highlightBit((byte)i48);
                    RadixTrieIterDigitalElementArray a65 = this.insertDataBitArray;
                    int i49 = this.currentLevel;
                    int i50 = (byte)i49;
                    a65.highlightBit((byte)i50);
                    this.setPosition("3.5.2.2.1");
                    RadixTrieIterInternalNode a66 = new RadixTrieIterInternalNode();
                    this.pathNode = a66;
                    a54.setLeft((RadixTrieIterNode)a66);
                    RadixTrieIterNode a67 = a54.getLeft();
                    RadixTrieIterInternalNode dummy5 = (RadixTrieIterInternalNode)a67;
                    RadixTrieIterInternalNode a68 = (RadixTrieIterInternalNode)a67;
                    a66.setIsLinkedIn();
                    this.currentNode = a68;
                    this.setPosition("3.5.2.2.2");
                    a54 = a68;
                }
            }
            this.setPosition("3.5.2");
            this.setPosition("3.5.3");
            int i51 = this.currentLevel;
            int i52 = a0.getKey();
            int i53 = this.getBit(i51, i52);
            if(i53 != 0)
            {
                this.setPosition("3.4.2.1.2.1");
                a54.setLeft((RadixTrieIterNode)a41);
                int i54 = this.currentLevel;
                int i55 = i54 + 1;
                a41.setLevel(i55);
                java.util.Vector a69 = new java.util.Vector();
                this.insertedData = a69;
                java.util.Vector a70 = this.insertedData;
                RadixTrieIterNode a71 = this.head;
                this.radixTrieToVector(a70, a71);
                this.setPosition("3.4.2.1.2.2");
                this.setPosition("3.4.2.1.3.1");
                this.setPosition("3.4.2.1.3.3");
                RadixTrieIterExternalNode a72 = new RadixTrieIterExternalNode(a0);
                int i56 = this.currentLevel;
                int i57 = i56 + 1;
                a72.setLevel(i57);
                a72.setIsLinkedIn();
                a54.setRight((RadixTrieIterNode)a72);
                java.util.Vector a73 = new java.util.Vector();
                this.insertedData = a73;
                java.util.Vector a74 = this.insertedData;
                RadixTrieIterNode a75 = this.head;
                this.radixTrieToVector(a74, a75);
                this.setPosition("3.4.2.1.3.4");
                RadixTrieIterDigitalElementArray a76 = this.compareDataBitArray;
                a76.unHighlight();
                RadixTrieIterDigitalElementArray a77 = this.insertDataBitArray;
                a77.unHighlight();
            }
            else
            {
                this.setPosition("3.4.2.1.2.1");
                this.setPosition("3.4.2.1.2.3");
                a54.setRight((RadixTrieIterNode)a41);
                int i58 = this.currentLevel;
                int i59 = i58 + 1;
                a41.setLevel(i59);
                java.util.Vector a78 = new java.util.Vector();
                this.insertedData = a78;
                java.util.Vector a79 = this.insertedData;
                RadixTrieIterNode a80 = this.head;
                this.radixTrieToVector(a79, a80);
                this.setPosition("3.4.2.1.2.4");
                this.setPosition("3.4.2.1.3.1");
                RadixTrieIterExternalNode a81 = new RadixTrieIterExternalNode(a0);
                int i60 = this.currentLevel;
                int i61 = i60 + 1;
                a81.setLevel(i61);
                a81.setIsLinkedIn();
                a54.setLeft((RadixTrieIterNode)a81);
                java.util.Vector a82 = new java.util.Vector();
                this.insertedData = a82;
                java.util.Vector a83 = this.insertedData;
                RadixTrieIterNode a84 = this.head;
                this.radixTrieToVector(a83, a84);
                this.setPosition("3.4.2.1.3.2");
                RadixTrieIterDigitalElementArray a85 = this.compareDataBitArray;
                a85.unHighlight();
                RadixTrieIterDigitalElementArray a86 = this.insertDataBitArray;
                a86.unHighlight();
            }
            this.setPosition("3.4.2.1.4");
            a1 = a4;
        }
        return a1;
    }
    
    private boolean search(RadixTrieIterNode a, com.cim.AIA.DataItem a0, int i)
    {
        int i0 = 0;
        this.setPosition("5");
        this.currentLevel = 0;
        RadixTrieIterDigitalElementArray a1 = this.searchDataBitArray;
        a1.unHighlight();
        RadixTrieIterDigitalElementArray a2 = this.searchDataBitArray;
        int i1 = this.currentLevel;
        int i2 = (byte)i1;
        a2.highlightBit((byte)i2);
        this.currentNode = a;
        RadixTrieIterNode a3 = this.currentNode;
        this.pathNode = a3;
        this.setPosition("5.1.1");
        while(true)
        {
            RadixTrieIterNode a4 = this.currentNode;
            int i3 = a4 instanceof RadixTrieIterInternalNode?1:0;
            if(i3 == 0)
            {
                break;
            }
            this.setPosition("5.1.2");
            int i4 = this.currentLevel;
            int i5 = a0.getKey();
            int i6 = this.getBit(i4, i5);
            if(i6 != 0)
            {
                this.setPosition("5.1.2.1.1");
                this.setPosition("5.1.2.1.2");
                RadixTrieIterNode a5 = this.currentNode;
                RadixTrieIterInternalNode dummy = (RadixTrieIterInternalNode)a5;
                RadixTrieIterInternalNode a6 = (RadixTrieIterInternalNode)a5;
                RadixTrieIterNode a7 = a6.getRight();
                this.currentNode = a7;
                RadixTrieIterNode a8 = this.currentNode;
                this.pathNode = a8;
                this.setPosition("5.1.2.1.2.1");
            }
            else
            {
                this.setPosition("5.1.2.1.1");
                RadixTrieIterNode a9 = this.currentNode;
                RadixTrieIterInternalNode dummy0 = (RadixTrieIterInternalNode)a9;
                RadixTrieIterInternalNode a10 = (RadixTrieIterInternalNode)a9;
                RadixTrieIterNode a11 = a10.getLeft();
                this.currentNode = a11;
                RadixTrieIterNode a12 = this.currentNode;
                this.pathNode = a12;
                this.setPosition("5.1.2.1.1.1");
            }
            int i7 = this.currentLevel;
            int i8 = i7 + 1;
            this.currentLevel = i8;
            RadixTrieIterDigitalElementArray a13 = this.searchDataBitArray;
            a13.unHighlight();
            RadixTrieIterDigitalElementArray a14 = this.searchDataBitArray;
            int i9 = this.currentLevel;
            int i10 = (byte)i9;
            a14.highlightBit((byte)i10);
            this.setPosition("5.1.2.2");
        }
        this.setPosition("5.1.2");
        this.setPosition("5.1.3");
        RadixTrieIterNode a15 = this.currentNode;
        int i11 = a15 instanceof RadixTrieIterExternalNode?1:0;
        if(i11 != 0)
        {
            RadixTrieIterNode a16 = this.currentNode;
            RadixTrieIterExternalNode dummy1 = (RadixTrieIterExternalNode)a16;
            RadixTrieIterExternalNode a17 = (RadixTrieIterExternalNode)a16;
            int i12 = a17.getKey();
            int i13 = this.mostSignificantBit;
            RadixTrieIterDigitalElementArray a18 = new RadixTrieIterDigitalElementArray(i12, i13, false);
            this.finalCompareDataBitArray = a18;
            RadixTrieIterDigitalElementArray a19 = this.finalCompareDataBitArray;
            java.awt.Color a20 = RadixTrieIterColors.COMPARE_BIT_HIGHLIGHT_COLOR;
            java.awt.Color a21 = RadixTrieIterColors.COMPARE_BIT_HIGHLIGHT_COLOR;
            a19.setColors(a20, a21);
        }
        this.setPosition("5.2");
        RadixTrieIterNode a22 = this.currentNode;
        label1: {
            label0: {
                int i14 = a22 instanceof RadixTrieIterExternalNode?1:0;
                if(i14 == 0)
                {
                    break label0;
                }
                RadixTrieIterNode a23 = this.currentNode;
                RadixTrieIterExternalNode dummy2 = (RadixTrieIterExternalNode)a23;
                RadixTrieIterExternalNode a24 = (RadixTrieIterExternalNode)a23;
                int i15 = a24.getKey();
                int i16 = a0.getKey();
                if(i15 != i16)
                {
                    break label0;
                }
                this.setPosition("5.2.1");
                RadixTrieIterDigitalElementArray a25 = this.searchDataBitArray;
                a25.unHighlight();
                this.setPosition("5.2.1.1");
                i0 = 1;
                break label1;
            }
            this.setPosition("5.2.1");
            this.setPosition("5.2.2");
            RadixTrieIterDigitalElementArray a26 = this.searchDataBitArray;
            a26.unHighlight();
            this.setPosition("5.2.2.1");
            i0 = 0;
        }
        return i0 != 0;
    }
    
    private void save()
    {
        RadixTrieIterNode a = this.head;
        this.savedTree = a;
    }
    
    protected void storeState(boolean b)
    {
        this.save();
    }
    
    protected void clearState()
    {
        RadixTrieIterNullNode a = new RadixTrieIterNullNode((RadixTrieIterInternalNode)null);
        this.head = a;
    }
    
    private byte getBit(int i, int i0)
    {
        int i1 = this.mostSignificantBit;
        int i2 = i1 - i;
        int i3 = i0 >> i2;
        int i4 = i3 % 2;
        int i5 = (byte)i4;
        return (byte)i5;
    }
    
    private void setPosition(String s, boolean b)
    {
        if(!b)
        {
            String s0 = localization.Messages.getString("RadixTrieIter.5");
            this.setPosition(s0);
        }
        else
        {
            this.setPosition(s);
        }
    }
    
    private void clearPointers()
    {
        this.currentNode = null;
        RadixTrieIterNode a = this.currentNode;
        this.pathNode = a;
        this.insertDataBitArray = null;
        this.searchDataBitArray = null;
        this.compareDataBitArray = null;
        this.finalCompareDataBitArray = null;
        java.util.Vector a0 = new java.util.Vector();
        this.insertedData = a0;
        java.util.Vector a1 = this.insertedData;
        RadixTrieIterNode a2 = this.head;
        this.radixTrieToVector(a1, a2);
        this.currentLevel = -1;
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
        String s = localization.Messages.getString("RadixTrieIter.0");
        RadixTrieIter.BUILD_MODE_LABEL = s;
        String s0 = localization.Messages.getString("RadixTrieIter.1");
        RadixTrieIter.SEARCH_MODE_LABEL = s0;
    }
}