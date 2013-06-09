public class RadixTrie extends com.cim.AIA.Algorithm implements com.cim.AIA.MethodSelectionListener, com.cim.AIA.ControlListener {
    final private static String BUILD_MODE_LABEL;
    final private static String SEARCH_MODE_LABEL;
    final private static int BUILD_MODE = 11;
    final private static int SEARCH_MODE = 12;
    private int nextMode;
    private int executingMode;
    public boolean isBackMode;
    final private static int DEFAULT_ELEMENT_WIDTH = 20;
    private RadixTrieDigitalElementArray insertDataBitArray;
    private RadixTrieDigitalElementArray searchDataBitArray;
    private RadixTrieDigitalElementArray compareDataBitArray;
    private java.util.Vector insertedData;
    private RadixTrieNode savedTree;
    private int insertPos;
    private int searchPos;
    private RadixTrieNode head;
    private com.cim.AIA.ElementArray insertData;
    private com.cim.AIA.ElementArray searchData;
    private com.cim.AIA.ElementArray newSearchData;
    private com.cim.AIA.ElementArray newInsertData;
    int mostSignificantBit;
    private RadixTrieNode currentNode;
    private RadixTrieNode pathNode;
    
    public RadixTrie(com.cim.AIA.AlgorithmThread a, Object a0)
    {
        super(a, a0);
        this.isBackMode = false;
        RadixTrieNullNode a1 = new RadixTrieNullNode((RadixTrieInternalNode)null);
        this.savedTree = a1;
        RadixTrieNullNode a2 = new RadixTrieNullNode((RadixTrieInternalNode)null);
        this.head = a2;
        this.mostSignificantBit = 0;
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
                RadixTrieNullNode a13 = new RadixTrieNullNode((RadixTrieInternalNode)null);
                this.savedTree = a13;
                this.insertPos = 0;
                break label3;
            }
            int i18 = this.executingMode;
            if(i18 == 12)
            {
                this.searchPos = 0;
            }
        }
        RadixTrieNode a14 = this.savedTree;
        this.head = a14;
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
            java.awt.Color a15 = RadixTrieColors.INSERT_ACTIVE_COLOR;
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
            java.awt.Color a33 = RadixTrieColors.SEARCH_ACTIVE_COLOR;
            a32.setBackgroundColor(a33);
            int i10 = i + 1;
            i = i10;
        }
    }
    
    protected void initialiseMethods(String s, com.cim.AIA.MethodSelectable a)
    {
        Object a0 = a;
        String s0 = RadixTrie.BUILD_MODE_LABEL;
        String s1 = localization.Messages.getString("RadixTrie.2");
        com.cim.AIA.Logger a1 = this.getLogger();
        com.cim.AIA.BreakPoint a2 = this.getBreakPoint();
        com.cim.AIA.MethodSelection a3 = new com.cim.AIA.MethodSelection(s0, s, s1, "3a", a1, a2);
        String s2 = RadixTrie.SEARCH_MODE_LABEL;
        String s3 = localization.Messages.getString("RadixTrie.4");
        com.cim.AIA.Logger a4 = this.getLogger();
        com.cim.AIA.BreakPoint a5 = this.getBreakPoint();
        com.cim.AIA.MethodSelection a6 = new com.cim.AIA.MethodSelection(s2, s, s3, "5a", a4, a5);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelection(a3, true);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelection(a6, false);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelectionListener((com.cim.AIA.MethodSelectionListener)this);
    }
    
    public void processMethodSelectionEvent(com.cim.AIA.MethodSelectionEvent a)
    {
        String s = RadixTrie.BUILD_MODE_LABEL;
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
            String s1 = RadixTrie.SEARCH_MODE_LABEL;
            String s2 = a.getMethodName();
            int i0 = s1.compareTo(s2);
            if(i0 == 0)
            {
                this.nextMode = 12;
            }
        }
    }
    
    private RadixTrieDigitalElementArray initialiseBitElementArray(int i)
    {
        int i0 = this.mostSignificantBit;
        RadixTrieDigitalElementArray a = new RadixTrieDigitalElementArray(i, i0);
        return a;
    }
    
    public RadixTrieNode getHead()
    {
        RadixTrieNode a = this.head;
        return a;
    }
    
    public java.util.Vector getInsertedData()
    {
        java.util.Vector a = this.insertedData;
        return a;
    }
    
    public RadixTrieDigitalElementArray getInsertBitArray()
    {
        RadixTrieDigitalElementArray a = null;
        int i = this.executingMode;
        if(i != 11)
        {
            a = null;
        }
        else
        {
            RadixTrieDigitalElementArray a0 = this.insertDataBitArray;
            a = a0;
        }
        return a;
    }
    
    public RadixTrieDigitalElementArray getSearchBitArray()
    {
        RadixTrieDigitalElementArray a = null;
        int i = this.executingMode;
        if(i != 12)
        {
            a = null;
        }
        else
        {
            RadixTrieDigitalElementArray a0 = this.searchDataBitArray;
            a = a0;
        }
        return a;
    }
    
    public RadixTrieDigitalElementArray getCompareBitArray()
    {
        RadixTrieDigitalElementArray a = this.compareDataBitArray;
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
    
    public RadixTrieNode getCurrentNode()
    {
        RadixTrieNode a = this.head;
        this.unHighlightPath(a);
        RadixTrieNode a0 = this.pathNode;
        this.highlightPath(a0);
        RadixTrieNode a1 = this.currentNode;
        return a1;
    }
    
    public int getMostSignificantBit()
    {
        int i = this.mostSignificantBit;
        return i;
    }
    
    private void unHighlightPath(RadixTrieNode a)
    {
        label0: {
            if(a == null)
            {
                break label0;
            }
            a.setIsOnPath(false);
            int i = a instanceof RadixTrieInternalNode?1:0;
            if(i != 0)
            {
                RadixTrieInternalNode dummy = (RadixTrieInternalNode)a;
                RadixTrieInternalNode a0 = (RadixTrieInternalNode)a;
                RadixTrieNode a1 = a0.getLeft();
                this.unHighlightPath(a1);
                RadixTrieInternalNode dummy0 = (RadixTrieInternalNode)a;
                RadixTrieInternalNode a2 = (RadixTrieInternalNode)a;
                RadixTrieNode a3 = a2.getRight();
                this.unHighlightPath(a3);
            }
        }
    }
    
    private void highlightPath(RadixTrieNode a)
    {
        if(a != null)
        {
            a.setIsOnPath(true);
            RadixTrieInternalNode a0 = a.getParent();
            this.highlightPath((RadixTrieNode)a0);
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
    
    private void printTrie(RadixTrieNode a)
    {
        int i = a instanceof RadixTrieInternalNode?1:0;
        if(i != 0)
        {
            RadixTrieInternalNode dummy = (RadixTrieInternalNode)a;
            RadixTrieInternalNode a0 = (RadixTrieInternalNode)a;
            RadixTrieNode a1 = a0.getLeft();
            this.printTrie(a1);
            RadixTrieInternalNode dummy0 = (RadixTrieInternalNode)a;
            RadixTrieInternalNode a2 = (RadixTrieInternalNode)a;
            RadixTrieNode a3 = a2.getRight();
            this.printTrie(a3);
        }
        int i0 = a instanceof RadixTrieExternalNode?1:0;
        if(i0 != 0)
        {
            java.io.PrintStream a4 = System.out;
            StringBuilder a5 = new StringBuilder();
            RadixTrieExternalNode dummy1 = (RadixTrieExternalNode)a;
            RadixTrieExternalNode a6 = (RadixTrieExternalNode)a;
            int i1 = a6.getKey();
            StringBuilder a7 = a5.append(i1);
            StringBuilder a8 = a7.append(" ");
            RadixTrieExternalNode dummy2 = (RadixTrieExternalNode)a;
            RadixTrieExternalNode a9 = (RadixTrieExternalNode)a;
            int i2 = a9.getKey();
            String s = Integer.toBinaryString(i2);
            StringBuilder a10 = a8.append(s);
            String s0 = a10.toString();
            a4.println(s0);
        }
    }
    
    private void radixTrieToVector(java.util.Vector a, RadixTrieNode a0)
    {
        label1: {
            label0: {
                int i = a0 instanceof RadixTrieInternalNode?1:0;
                if(i == 0)
                {
                    break label0;
                }
                RadixTrieInternalNode dummy = (RadixTrieInternalNode)a0;
                RadixTrieInternalNode a1 = (RadixTrieInternalNode)a0;
                RadixTrieNode a2 = a1.getLeft();
                this.radixTrieToVector(a, a2);
                RadixTrieInternalNode dummy0 = (RadixTrieInternalNode)a0;
                RadixTrieInternalNode a3 = (RadixTrieInternalNode)a0;
                RadixTrieNode a4 = a3.getRight();
                this.radixTrieToVector(a, a4);
                break label1;
            }
            int i0 = a0 instanceof RadixTrieExternalNode?1:0;
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
                    RadixTrieDataItem a6 = new RadixTrieDataItem(s, i3);
                    int i4 = a6.getKey();
                    RadixTrieDigitalElementArray a7 = this.initialiseBitElementArray(i4);
                    this.searchDataBitArray = a7;
                    RadixTrieDigitalElementArray a8 = this.searchDataBitArray;
                    java.awt.Color a9 = RadixTrieColors.SEARCH_BIT_ACTIVE_COLOR;
                    java.awt.Color a10 = RadixTrieColors.SEARCH_BIT_HIGHLIGHT_COLOR;
                    a8.setColors(a9, a10);
                    int i5 = this.enabled?1:0;
                    label2: {
                        if(i5 == 0)
                        {
                            break label2;
                        }
                        java.awt.Color a11 = RadixTrieColors.SEARCH_HIGHLIGHT_COLOR;
                        a2.setBackgroundColor(a11);
                        RadixTrieNode a12 = this.head;
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
                        RadixTrieExternalNode.clearCompareNode();
                        java.util.Vector a27 = new java.util.Vector();
                        this.insertedData = a27;
                        java.util.Vector a28 = this.insertedData;
                        RadixTrieNode a29 = this.head;
                        this.radixTrieToVector(a28, a29);
                        java.awt.Color a30 = RadixTrieColors.SEARCH_DONE_COLOR;
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
            RadixTrieNode a33 = this.head;
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
                RadixTrieDataItem a42 = new RadixTrieDataItem(s0, i13);
                int i14 = a42.getKey();
                RadixTrieDigitalElementArray a43 = this.initialiseBitElementArray(i14);
                this.insertDataBitArray = a43;
                RadixTrieDigitalElementArray a44 = this.insertDataBitArray;
                java.awt.Color a45 = RadixTrieColors.INSERT_BIT_ACTIVE_COLOR;
                java.awt.Color a46 = RadixTrieColors.INSERT_BIT_HIGHLIGHT_COLOR;
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
                        java.awt.Color a50 = RadixTrieColors.INSERT_HIGHLIGHT_COLOR;
                        a49.setBackgroundColor(a50);
                        RadixTrieNode a51 = this.head;
                        RadixTrieNode a52 = this.insert(a51, a42, 0, true);
                        this.head = a52;
                        this.clearPointers();
                        RadixTrieExternalNode.clearNewestNode();
                        RadixTrieExternalNode.clearCompareNode();
                        java.util.Vector a53 = new java.util.Vector();
                        this.insertedData = a53;
                        java.util.Vector a54 = this.insertedData;
                        RadixTrieNode a55 = this.head;
                        this.radixTrieToVector(a54, a55);
                        int i16 = this.insertPos;
                        int i17 = i16 + 1;
                        this.insertPos = i17;
                        com.cim.AIA.ElementArray a56 = this.insertData;
                        com.cim.AIA.Element a57 = a56.getElement(i11);
                        com.cim.AIA.Node dummy3 = (com.cim.AIA.Node)a57;
                        com.cim.AIA.Node a58 = (com.cim.AIA.Node)a57;
                        java.awt.Color a59 = RadixTrieColors.INSERT_DONE_COLOR;
                        a58.setBackgroundColor(a59);
                    }
                }
                catch(Exception ignoredException)
                {
                    this.clearPointers();
                    java.io.PrintStream a60 = System.out;
                    a60.println("Already inserted exception caught");
                }
                this.insertDataBitArray = null;
                int i18 = i11 + 1;
                i11 = i18;
            }
        }
    }
    
    private RadixTrieNode insert(RadixTrieNode a, RadixTrieDataItem a0, int i, boolean b)
    {
        RadixTrieNode a1 = null;
        this.currentNode = a;
        int i0 = b?1:0;
        RadixTrieNode a2 = this.currentNode;
        this.pathNode = a2;
        this.setPosition("3.1", i0 != 0);
        if(a == null)
        {
            RuntimeException a3 = new RuntimeException("trie should not be null");
            throw a3;
        }
        label1: {
            label0: {
                int i1 = a instanceof RadixTrieNullNode?1:0;
                if(i1 == 0)
                {
                    break label0;
                }
                this.setPosition("3.1.1", i0 != 0);
                RadixTrieExternalNode a4 = new RadixTrieExternalNode(a0);
                a4.setIsLinkedIn();
                a4.setLevel(i);
                this.pathNode = a4;
                a1 = a4;
                break label1;
            }
            this.setPosition("3.2", i0 != 0);
            label2: {
                int i2 = a instanceof RadixTrieExternalNode?1:0;
                if(i2 != 0)
                {
                    break label2;
                }
                RadixTrieDigitalElementArray a5 = this.insertDataBitArray;
                int i3 = (byte)i;
                a5.highlightBit((byte)i3);
                this.setPosition("3.3", i0 != 0);
                label3: {
                    int i4 = a instanceof RadixTrieInternalNode?1:0;
                    if(i4 == 0)
                    {
                        break label3;
                    }
                    this.setPosition("3.3.1", i0 != 0);
                    this.setPosition("3.3.1.1", i0 != 0);
                    int i5 = a0.getKey();
                    int i6 = this.getBit(i, i5);
                    if(i6 != 0)
                    {
                        this.setPosition("3.3.1.2", i0 != 0);
                        this.setPosition("3.3.1.2.1", i0 != 0);
                        RadixTrieDigitalElementArray a6 = this.insertDataBitArray;
                        a6.unHighlight();
                        RadixTrieInternalNode dummy = (RadixTrieInternalNode)a;
                        RadixTrieInternalNode a7 = (RadixTrieInternalNode)a;
                        RadixTrieInternalNode dummy0 = (RadixTrieInternalNode)a;
                        RadixTrieInternalNode a8 = (RadixTrieInternalNode)a;
                        RadixTrieNode a9 = a8.getRight();
                        int i7 = i + 1;
                        RadixTrieNode a10 = this.insert(a9, a0, i7, i0 != 0);
                        a7.setRight(a10);
                    }
                    else
                    {
                        this.setPosition("3.3.1.1.1", i0 != 0);
                        RadixTrieDigitalElementArray a11 = this.insertDataBitArray;
                        a11.unHighlight();
                        RadixTrieInternalNode dummy1 = (RadixTrieInternalNode)a;
                        RadixTrieInternalNode a12 = (RadixTrieInternalNode)a;
                        RadixTrieInternalNode dummy2 = (RadixTrieInternalNode)a;
                        RadixTrieInternalNode a13 = (RadixTrieInternalNode)a;
                        RadixTrieNode a14 = a13.getLeft();
                        int i8 = i + 1;
                        RadixTrieNode a15 = this.insert(a14, a0, i8, i0 != 0);
                        a12.setLeft(a15);
                    }
                }
                java.util.Vector a16 = new java.util.Vector();
                this.insertedData = a16;
                java.util.Vector a17 = this.insertedData;
                RadixTrieNode a18 = this.head;
                this.radixTrieToVector(a17, a18);
                this.currentNode = a;
                this.setPosition("3.4", i0 != 0);
                a1 = a;
                break label1;
            }
            RadixTrieExternalNode dummy3 = (RadixTrieExternalNode)a;
            RadixTrieExternalNode a19 = (RadixTrieExternalNode)a;
            int i9 = a19.getKey();
            RadixTrieDigitalElementArray a20 = this.initialiseBitElementArray(i9);
            this.compareDataBitArray = a20;
            RadixTrieDigitalElementArray a21 = this.compareDataBitArray;
            java.awt.Color a22 = RadixTrieColors.COMPARE_BIT_ACTIVE_COLOR;
            java.awt.Color a23 = RadixTrieColors.COMPARE_BIT_HIGHLIGHT_COLOR;
            a21.setColors(a22, a23);
            RadixTrieExternalNode dummy4 = (RadixTrieExternalNode)a;
            RadixTrieExternalNode a24 = (RadixTrieExternalNode)a;
            RadixTrieExternalNode.setCompareNode(a24);
            this.setPosition("3.2.1.1", i0 != 0);
            RadixTrieExternalNode dummy5 = (RadixTrieExternalNode)a;
            RadixTrieExternalNode a25 = (RadixTrieExternalNode)a;
            int i10 = a25.getKey();
            int i11 = a0.getKey();
            if(i10 == i11)
            {
                this.setPosition("3.2.1.1.1", i0 != 0);
                this.compareDataBitArray = null;
                Exception a26 = new Exception("Item already in trie");
                throw a26;
            }
            this.setPosition("3.2.2", i0 != 0);
            this.setPosition("3.2.2.2", i0 != 0);
            this.setPosition("3.2.2.1", i0 != 0);
            RadixTrieInternalNode a27 = new RadixTrieInternalNode();
            RadixTrieInternalNode a28 = a.getParent();
            label5: {
                label4: {
                    if(a28 != null)
                    {
                        break label4;
                    }
                    RadixTrieNode a29 = this.head;
                    if(a != a29)
                    {
                        RuntimeException a30 = new RuntimeException("This case should be unreachable");
                        throw a30;
                    }
                    this.head = a27;
                    break label5;
                }
                RadixTrieInternalNode a31 = a.getParent();
                RadixTrieNode a32 = a31.getLeft();
                if(a32 == a)
                {
                    RadixTrieInternalNode a33 = a.getParent();
                    a33.setLeft((RadixTrieNode)a27);
                }
                RadixTrieInternalNode a34 = a.getParent();
                RadixTrieNode a35 = a34.getRight();
                if(a35 == a)
                {
                    RadixTrieInternalNode a36 = a.getParent();
                    a36.setRight((RadixTrieNode)a27);
                }
            }
            this.currentNode = a27;
            RadixTrieNode a37 = this.currentNode;
            this.pathNode = a37;
            RadixTrieExternalNode dummy6 = (RadixTrieExternalNode)a;
            RadixTrieExternalNode a38 = (RadixTrieExternalNode)a;
            RadixTrieNode a39 = this.makeInternalNode(a0, a38, i, true, a27);
            this.setPosition("3.2.2.3", i0 != 0);
            this.compareDataBitArray = null;
            a39.setIsLinkedIn();
            a1 = a39;
        }
        return a1;
    }
    
    private RadixTrieNode makeInternalNode(RadixTrieDataItem a, RadixTrieExternalNode a0, int i, boolean b, RadixTrieInternalNode a1)
    {
        RadixTrieDigitalElementArray a2 = this.compareDataBitArray;
        int i0 = b?1:0;
        a2.unHighlight();
        RadixTrieDigitalElementArray a3 = this.insertDataBitArray;
        a3.unHighlight();
        RadixTrieDigitalElementArray a4 = this.compareDataBitArray;
        int i1 = (byte)i;
        a4.highlightBit((byte)i1);
        RadixTrieDigitalElementArray a5 = this.insertDataBitArray;
        int i2 = (byte)i;
        a5.highlightBit((byte)i2);
        this.setPosition("1111", i0 != 0);
        this.setPosition("3.2.2.1.1");
        int i3 = a0.getKey();
        int i4 = this.getBit(i, i3);
        int i5 = a.getKey();
        int i6 = this.getBit(i, i5);
        label4: {
            label1: {
                label3: {
                    label2: {
                        label0: {
                            if(i4 == i6)
                            {
                                break label0;
                            }
                            this.setPosition("3.2.2.1.1.2.1", i0 != 0);
                            int i7 = a0.getKey();
                            int i8 = this.getBit(i, i7);
                            if(i8 == 0)
                            {
                                break label1;
                            }
                            this.setPosition("3.2.2.1.1.2.2", i0 != 0);
                            int i9 = a0.getKey();
                            int i10 = this.getBit(i, i9);
                            if(i10 != 1)
                            {
                                break label2;
                            }
                            else
                            {
                                break label3;
                            }
                        }
                        RadixTrieInternalNode a6 = new RadixTrieInternalNode();
                        this.setPosition("3.2.2.1.2", i0 != 0);
                        this.setPosition("3.2.2.1.2.1.1", i0 != 0);
                        int i11 = a0.getKey();
                        int i12 = this.getBit(i, i11);
                        if(i12 == 0)
                        {
                            this.setPosition("3.2.2.1.2.1.1.1.1", i0 != 0);
                            this.currentNode = a6;
                            RadixTrieNode a7 = this.currentNode;
                            this.pathNode = a7;
                            a1.setLeft((RadixTrieNode)a6);
                            int i13 = i + 1;
                            RadixTrieNode a8 = this.makeInternalNode(a, a0, i13, i0 != 0, a6);
                            a1.setLeft(a8);
                        }
                        int i14 = a0.getKey();
                        int i15 = this.getBit(i, i14);
                        if(i15 == 1)
                        {
                            this.setPosition("3.2.2.1.2.1.2", i0 != 0);
                            this.setPosition("3.2.2.1.2.1.2.1.1", i0 != 0);
                            this.currentNode = a6;
                            RadixTrieNode a9 = this.currentNode;
                            this.pathNode = a9;
                            a1.setRight((RadixTrieNode)a6);
                            int i16 = i + 1;
                            RadixTrieNode a10 = this.makeInternalNode(a, a0, i16, i0 != 0, a6);
                            a1.setRight(a10);
                        }
                    }
                    RadixTrieDigitalElementArray a11 = this.compareDataBitArray;
                    a11.unHighlight();
                    RadixTrieDigitalElementArray a12 = this.insertDataBitArray;
                    a12.unHighlight();
                    this.setPosition("3.2.2.1.3");
                    a1.setIsLinkedIn();
                    RadixTrieInternalNode a13 = a1.getParent();
                    this.currentNode = a13;
                    break label4;
                }
                RadixTrieExternalNode a14 = new RadixTrieExternalNode(a);
                int i17 = i + 1;
                a14.setLevel(i17);
                a14.setIsLinkedIn();
                a1.setLeft((RadixTrieNode)a14);
                java.util.Vector a15 = new java.util.Vector();
                this.insertedData = a15;
                java.util.Vector a16 = this.insertedData;
                RadixTrieNode a17 = this.head;
                this.radixTrieToVector(a16, a17);
                this.setPosition("3.2.2.1.1.2.2.1.1", i0 != 0);
                a1.setRight((RadixTrieNode)a0);
                int i18 = i + 1;
                a0.setLevel(i18);
                java.util.Vector a18 = new java.util.Vector();
                this.insertedData = a18;
                java.util.Vector a19 = this.insertedData;
                RadixTrieNode a20 = this.head;
                this.radixTrieToVector(a19, a20);
                this.setPosition("3.2.2.1.1.2.2.1.2", i0 != 0);
                RadixTrieDigitalElementArray a21 = this.compareDataBitArray;
                a21.unHighlight();
                RadixTrieDigitalElementArray a22 = this.insertDataBitArray;
                a22.unHighlight();
                this.setPosition("3.2.2.1.3");
                a1.setIsLinkedIn();
                RadixTrieInternalNode a23 = a1.getParent();
                this.currentNode = a23;
                break label4;
            }
            a1.setLeft((RadixTrieNode)a0);
            int i19 = i + 1;
            a0.setLevel(i19);
            java.util.Vector a24 = new java.util.Vector();
            this.insertedData = a24;
            java.util.Vector a25 = this.insertedData;
            RadixTrieNode a26 = this.head;
            this.radixTrieToVector(a25, a26);
            this.setPosition("3.2.2.1.1.2.1.1.1", i0 != 0);
            RadixTrieExternalNode a27 = new RadixTrieExternalNode(a);
            int i20 = i + 1;
            a27.setLevel(i20);
            a1.setRight((RadixTrieNode)a27);
            java.util.Vector a28 = new java.util.Vector();
            this.insertedData = a28;
            java.util.Vector a29 = this.insertedData;
            RadixTrieNode a30 = this.head;
            this.radixTrieToVector(a29, a30);
            this.setPosition("3.2.2.1.1.2.1.1.2", i0 != 0);
            RadixTrieDigitalElementArray a31 = this.compareDataBitArray;
            a31.unHighlight();
            RadixTrieDigitalElementArray a32 = this.insertDataBitArray;
            a32.unHighlight();
            a27.setIsLinkedIn();
            this.setPosition("3.2.2.1.3");
            a1.setIsLinkedIn();
            RadixTrieInternalNode a33 = a1.getParent();
            this.currentNode = a33;
        }
        return a1;
    }
    
    private boolean search(RadixTrieNode a, RadixTrieDataItem a0, int i)
    {
        int i0 = 0;
        RadixTrieDigitalElementArray a1 = this.searchDataBitArray;
        a1.unHighlight();
        RadixTrieDigitalElementArray a2 = this.searchDataBitArray;
        int i1 = (byte)i;
        a2.highlightBit((byte)i1);
        this.currentNode = a;
        RadixTrieNode a3 = this.currentNode;
        this.pathNode = a3;
        this.setPosition("5");
        this.setPosition("5.1");
        label1: {
            label0: {
                int i2 = a instanceof RadixTrieNullNode?1:0;
                if(i2 == 0)
                {
                    break label0;
                }
                i0 = 0;
                break label1;
            }
            this.setPosition("5.2");
            label2: {
                int i3 = a instanceof RadixTrieExternalNode?1:0;
                if(i3 != 0)
                {
                    break label2;
                }
                this.setPosition("5.3");
                int i4 = a0.getKey();
                int i5 = this.getBit(i, i4);
                if(i5 != 0)
                {
                    this.setPosition("5.4");
                    this.setPosition("5.4.1");
                    RadixTrieInternalNode dummy = (RadixTrieInternalNode)a;
                    RadixTrieInternalNode a4 = (RadixTrieInternalNode)a;
                    RadixTrieNode a5 = a4.getRight();
                    int i6 = i + 1;
                    int i7 = this.search(a5, a0, i6)?1:0;
                    i0 = i7;
                    break label1;
                }
                else
                {
                    this.setPosition("5.3.1");
                    RadixTrieInternalNode dummy0 = (RadixTrieInternalNode)a;
                    RadixTrieInternalNode a6 = (RadixTrieInternalNode)a;
                    RadixTrieNode a7 = a6.getLeft();
                    int i8 = i + 1;
                    int i9 = this.search(a7, a0, i8)?1:0;
                    i0 = i9;
                    break label1;
                }
            }
            this.setPosition("5.2.1");
            RadixTrieExternalNode dummy1 = (RadixTrieExternalNode)a;
            RadixTrieExternalNode a8 = (RadixTrieExternalNode)a;
            int i10 = a8.getKey();
            int i11 = a0.getKey();
            if(i10 != i11)
            {
                this.setPosition("5.2.2");
                RadixTrieDigitalElementArray a9 = this.searchDataBitArray;
                a9.unHighlight();
                i0 = 0;
            }
            else
            {
                this.setPosition("5.2.1.1");
                RadixTrieDigitalElementArray a10 = this.searchDataBitArray;
                a10.unHighlight();
                i0 = 1;
            }
        }
        return i0 != 0;
    }
    
    private void save()
    {
        RadixTrieNode a = this.head;
        this.savedTree = a;
    }
    
    protected void storeState(boolean b)
    {
        this.save();
    }
    
    protected void clearState()
    {
        RadixTrieNullNode a = new RadixTrieNullNode((RadixTrieInternalNode)null);
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
            this.setPosition("This key does not exist");
        }
        else
        {
            this.setPosition(s);
        }
    }
    
    private void clearPointers()
    {
        this.currentNode = null;
        RadixTrieNode a = this.currentNode;
        this.pathNode = a;
        this.insertDataBitArray = null;
        this.searchDataBitArray = null;
        this.compareDataBitArray = null;
        java.util.Vector a0 = new java.util.Vector();
        this.insertedData = a0;
        java.util.Vector a1 = this.insertedData;
        RadixTrieNode a2 = this.head;
        this.radixTrieToVector(a1, a2);
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
        String s = localization.Messages.getString("RadixTrie.0");
        RadixTrie.BUILD_MODE_LABEL = s;
        String s0 = localization.Messages.getString("RadixTrie.1");
        RadixTrie.SEARCH_MODE_LABEL = s0;
    }
}