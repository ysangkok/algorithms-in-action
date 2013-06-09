public class RadixTrieMulti extends com.cim.AIA.Algorithm implements com.cim.AIA.MethodSelectionListener, com.cim.AIA.ControlListener {
    public static int NO_OF_NODES;
    public static int NO_OF_BITS;
    public static com.cim.common.RadioMenu mnuLink;
    final private static String BUILD_MODE_LABEL;
    final private static String SEARCH_MODE_LABEL;
    final private static int BUILD_MODE = 11;
    final private static int SEARCH_MODE = 12;
    private int nextMode;
    private int executingMode;
    public boolean isBackMode;
    final private static int DEFAULT_ELEMENT_WIDTH = 20;
    private RadixTrieMultiDigitalElementArray insertDataBitArray;
    private RadixTrieMultiDigitalElementArray searchDataBitArray;
    private RadixTrieMultiDigitalElementArray compareDataBitArray;
    private RadixTrieMultiDigitalElementArray finalCompareDataBitArray;
    private java.util.Vector insertedData;
    private RadixTrieMultiNode savedTree;
    private int insertPos;
    private int searchPos;
    private RadixTrieMultiNode head;
    private com.cim.AIA.ElementArray insertData;
    private com.cim.AIA.ElementArray searchData;
    private com.cim.AIA.ElementArray newSearchData;
    private com.cim.AIA.ElementArray newInsertData;
    int mostSignificantBit;
    private RadixTrieMultiNode currentNode;
    private RadixTrieMultiNode pathNode;
    private int currentLevel;
    
    public RadixTrieMulti(com.cim.AIA.AlgorithmThread a, Object a0)
    {
        super(a, a0);
        this.isBackMode = false;
        RadixTrieMultiNullNode a1 = new RadixTrieMultiNullNode((RadixTrieMultiInternalNode)null);
        this.savedTree = a1;
        RadixTrieMultiNullNode a2 = new RadixTrieMultiNullNode((RadixTrieMultiInternalNode)null);
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
        int i = 0;
        this.changeData(a);
        this.mostSignificantBit = 0;
        int i0 = this.executingMode;
        label1: {
            label0: {
                if(i0 == 11)
                {
                    break label0;
                }
                int i1 = this.nextMode;
                if(i1 == 11)
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
        int i2 = 0;
        while(true)
        {
            com.cim.AIA.ElementArray a3 = this.insertData;
            int i3 = a3.getSize();
            if(i2 >= i3)
            {
                break;
            }
            com.cim.AIA.ElementArray a4 = this.insertData;
            com.cim.AIA.Element a5 = a4.getElement(i2);
            Object a6 = a5.getObject();
            Integer dummy = (Integer)a6;
            Integer a7 = (Integer)a6;
            int i4 = a7.intValue();
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
        int i9 = 0;
        while(true)
        {
            com.cim.AIA.ElementArray a8 = this.searchData;
            int i10 = a8.getSize();
            if(i9 >= i10)
            {
                break;
            }
            com.cim.AIA.ElementArray a9 = this.searchData;
            com.cim.AIA.Element a10 = a9.getElement(i9);
            Object a11 = a10.getObject();
            Integer dummy0 = (Integer)a11;
            Integer a12 = (Integer)a11;
            int i11 = a12.intValue();
            String s0 = Integer.toBinaryString(i11);
            int i12 = this.mostSignificantBit;
            int i13 = s0.length();
            if(i12 < i13)
            {
                int i14 = s0.length();
                this.mostSignificantBit = i14;
            }
            int i15 = i9 + 1;
            i9 = i15;
        }
        int i16 = this.mostSignificantBit;
        int i17 = RadixTrieMulti.NO_OF_BITS;
        int i18 = i16 / i17;
        int i19 = this.mostSignificantBit;
        int i20 = RadixTrieMulti.NO_OF_BITS;
        int i21 = i19 % i20;
        if(i21 <= 0)
        {
            i = i18;
        }
        else
        {
            int i22 = i18 + 1;
            i = i22;
        }
        int i23 = RadixTrieMulti.NO_OF_BITS;
        int i24 = i * i23;
        this.mostSignificantBit = i24;
        int i25 = this.mostSignificantBit;
        int i26 = i25 - 1;
        this.mostSignificantBit = i26;
        int i27 = this.executingMode;
        label4: {
            label3: {
                label2: {
                    if(i27 == 11)
                    {
                        break label2;
                    }
                    int i28 = this.nextMode;
                    if(i28 != 11)
                    {
                        break label3;
                    }
                }
                RadixTrieMultiNullNode a13 = new RadixTrieMultiNullNode((RadixTrieMultiInternalNode)null);
                this.savedTree = a13;
                this.insertPos = 0;
                this.searchPos = 0;
                break label4;
            }
            int i29 = this.executingMode;
            label5: {
                if(i29 == 12)
                {
                    break label5;
                }
                int i30 = this.nextMode;
                if(i30 != 12)
                {
                    break label4;
                }
            }
            this.searchPos = 0;
        }
        RadixTrieMultiNode a14 = this.savedTree;
        this.head = a14;
        java.util.Vector a15 = new java.util.Vector();
        this.insertedData = a15;
        java.util.Vector a16 = this.insertedData;
        RadixTrieMultiNode a17 = this.head;
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
            java.awt.Color a15 = RadixTrieMultiColors.INSERT_ACTIVE_COLOR;
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
            java.awt.Color a33 = RadixTrieMultiColors.SEARCH_ACTIVE_COLOR;
            a32.setBackgroundColor(a33);
            int i10 = i + 1;
            i = i10;
        }
    }
    
    protected void initialiseMethods(String s, com.cim.AIA.MethodSelectable a)
    {
        Object a0 = a;
        String s0 = RadixTrieMulti.BUILD_MODE_LABEL;
        String s1 = localization.Messages.getString("RadixTrieMulti.2");
        com.cim.AIA.Logger a1 = this.getLogger();
        com.cim.AIA.BreakPoint a2 = this.getBreakPoint();
        com.cim.AIA.MethodSelection a3 = new com.cim.AIA.MethodSelection(s0, s, s1, "3a", a1, a2);
        String s2 = RadixTrieMulti.SEARCH_MODE_LABEL;
        String s3 = localization.Messages.getString("RadixTrieMulti.3");
        com.cim.AIA.Logger a4 = this.getLogger();
        com.cim.AIA.BreakPoint a5 = this.getBreakPoint();
        com.cim.AIA.MethodSelection a6 = new com.cim.AIA.MethodSelection(s2, s, s3, "5a", a4, a5);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelection(a3, true);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelection(a6, false);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelectionListener((com.cim.AIA.MethodSelectionListener)this);
    }
    
    public void processMethodSelectionEvent(com.cim.AIA.MethodSelectionEvent a)
    {
        String s = RadixTrieMulti.BUILD_MODE_LABEL;
        String s0 = a.getMethodName();
        int i = s.compareTo(s0);
        label1: {
            label0: {
                if(i != 0)
                {
                    break label0;
                }
                this.nextMode = 11;
                com.cim.common.RadioMenu a0 = RadixTrieMulti.mnuLink;
                a0.setEnabled(true);
                break label1;
            }
            String s1 = RadixTrieMulti.SEARCH_MODE_LABEL;
            String s2 = a.getMethodName();
            int i0 = s1.compareTo(s2);
            if(i0 == 0)
            {
                this.nextMode = 12;
                com.cim.common.RadioMenu a1 = RadixTrieMulti.mnuLink;
                a1.setEnabled(false);
            }
        }
    }
    
    private RadixTrieMultiDigitalElementArray initialiseBitElementArray(int i)
    {
        int i0 = this.mostSignificantBit;
        RadixTrieMultiDigitalElementArray a = new RadixTrieMultiDigitalElementArray(i, i0);
        return a;
    }
    
    public RadixTrieMultiNode getHead()
    {
        RadixTrieMultiNode a = this.head;
        return a;
    }
    
    public java.util.Vector getInsertedData()
    {
        java.util.Vector a = this.insertedData;
        return a;
    }
    
    public RadixTrieMultiDigitalElementArray getInsertBitArray()
    {
        RadixTrieMultiDigitalElementArray a = null;
        int i = this.executingMode;
        if(i != 11)
        {
            a = null;
        }
        else
        {
            RadixTrieMultiDigitalElementArray a0 = this.insertDataBitArray;
            a = a0;
        }
        return a;
    }
    
    public RadixTrieMultiDigitalElementArray getSearchBitArray()
    {
        RadixTrieMultiDigitalElementArray a = null;
        int i = this.executingMode;
        if(i != 12)
        {
            a = null;
        }
        else
        {
            RadixTrieMultiDigitalElementArray a0 = this.searchDataBitArray;
            a = a0;
        }
        return a;
    }
    
    public RadixTrieMultiDigitalElementArray getFinalCompareBitArray()
    {
        RadixTrieMultiDigitalElementArray a = null;
        int i = this.executingMode;
        if(i != 12)
        {
            a = null;
        }
        else
        {
            RadixTrieMultiDigitalElementArray a0 = this.finalCompareDataBitArray;
            a = a0;
        }
        return a;
    }
    
    public RadixTrieMultiDigitalElementArray getCompareBitArray()
    {
        RadixTrieMultiDigitalElementArray a = this.compareDataBitArray;
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
    
    public RadixTrieMultiNode getCurrentNode()
    {
        RadixTrieMultiNode a = this.head;
        this.unHighlightPath(a);
        RadixTrieMultiNode a0 = this.pathNode;
        this.highlightPath(a0);
        RadixTrieMultiNode a1 = this.currentNode;
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
    
    private void unHighlightPath(RadixTrieMultiNode a)
    {
        label0: {
            if(a == null)
            {
                break label0;
            }
            a.setIsOnPath(false);
            int i = a instanceof RadixTrieMultiInternalNode?1:0;
            if(i == 0)
            {
                break label0;
            }
            int i0 = 0;
            while(true)
            {
                int i1 = RadixTrieMulti.NO_OF_NODES;
                if(i0 >= i1)
                {
                    break;
                }
                else
                {
                    RadixTrieMultiInternalNode dummy = (RadixTrieMultiInternalNode)a;
                    RadixTrieMultiInternalNode a0 = (RadixTrieMultiInternalNode)a;
                    RadixTrieMultiNode a1 = a0.getChild(i0);
                    this.unHighlightPath(a1);
                    int i2 = i0 + 1;
                    i0 = i2;
                }
            }
        }
    }
    
    private void highlightPath(RadixTrieMultiNode a)
    {
        if(a != null)
        {
            a.setIsOnPath(true);
            RadixTrieMultiInternalNode a0 = a.getParent();
            this.highlightPath((RadixTrieMultiNode)a0);
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
    
    private void printTrie(RadixTrieMultiNode a)
    {
        label0: {
            int i = a instanceof RadixTrieMultiInternalNode?1:0;
            if(i == 0)
            {
                break label0;
            }
            int i0 = 0;
            while(true)
            {
                int i1 = RadixTrieMulti.NO_OF_NODES;
                if(i0 >= i1)
                {
                    break;
                }
                else
                {
                    RadixTrieMultiInternalNode dummy = (RadixTrieMultiInternalNode)a;
                    RadixTrieMultiInternalNode a0 = (RadixTrieMultiInternalNode)a;
                    RadixTrieMultiNode a1 = a0.getChild(i0);
                    this.printTrie(a1);
                    int i2 = i0 + 1;
                    i0 = i2;
                }
            }
        }
        int i3 = a instanceof RadixTrieMultiExternalNode?1:0;
        if(i3 != 0)
        {
            java.io.PrintStream a2 = System.out;
            StringBuilder a3 = new StringBuilder();
            RadixTrieMultiExternalNode dummy0 = (RadixTrieMultiExternalNode)a;
            RadixTrieMultiExternalNode a4 = (RadixTrieMultiExternalNode)a;
            int i4 = a4.getKey();
            StringBuilder a5 = a3.append(i4);
            StringBuilder a6 = a5.append(" ");
            RadixTrieMultiExternalNode dummy1 = (RadixTrieMultiExternalNode)a;
            RadixTrieMultiExternalNode a7 = (RadixTrieMultiExternalNode)a;
            int i5 = a7.getKey();
            String s = Integer.toBinaryString(i5);
            StringBuilder a8 = a6.append(s);
            String s0 = a8.toString();
            a2.println(s0);
        }
    }
    
    private void radixTrieToVector(java.util.Vector a, RadixTrieMultiNode a0)
    {
        label1: {
            label0: {
                int i = a0 instanceof RadixTrieMultiInternalNode?1:0;
                if(i != 0)
                {
                    break label0;
                }
                int i0 = a0 instanceof RadixTrieMultiExternalNode?1:0;
                if(i0 == 0)
                {
                    break label1;
                }
                else
                {
                    a.addElement((Object)a0);
                    break label1;
                }
            }
            int i1 = 0;
            while(true)
            {
                int i2 = RadixTrieMulti.NO_OF_NODES;
                if(i1 >= i2)
                {
                    break;
                }
                else
                {
                    RadixTrieMultiInternalNode dummy = (RadixTrieMultiInternalNode)a0;
                    RadixTrieMultiInternalNode a1 = (RadixTrieMultiInternalNode)a0;
                    RadixTrieMultiNode a2 = a1.getChild(i1);
                    this.radixTrieToVector(a, a2);
                    int i3 = i1 + 1;
                    i1 = i3;
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
                    RadixTrieMultiDigitalElementArray a7 = this.initialiseBitElementArray(i4);
                    this.searchDataBitArray = a7;
                    RadixTrieMultiDigitalElementArray a8 = this.searchDataBitArray;
                    java.awt.Color a9 = RadixTrieMultiColors.SEARCH_BIT_ACTIVE_COLOR;
                    java.awt.Color a10 = RadixTrieMultiColors.SEARCH_BIT_HIGHLIGHT_COLOR;
                    a8.setColors(a9, a10);
                    int i5 = this.enabled?1:0;
                    label2: {
                        if(i5 == 0)
                        {
                            break label2;
                        }
                        java.awt.Color a11 = RadixTrieMultiColors.SEARCH_HIGHLIGHT_COLOR;
                        a2.setBackgroundColor(a11);
                        RadixTrieMultiNode a12 = this.head;
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
                        RadixTrieMultiExternalNode.clearCompareNode();
                        java.util.Vector a27 = new java.util.Vector();
                        this.insertedData = a27;
                        java.util.Vector a28 = this.insertedData;
                        RadixTrieMultiNode a29 = this.head;
                        this.radixTrieToVector(a28, a29);
                        java.awt.Color a30 = RadixTrieMultiColors.SEARCH_DONE_COLOR;
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
            RadixTrieMultiNode a33 = this.head;
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
                RadixTrieMultiDigitalElementArray a43 = this.initialiseBitElementArray(i14);
                this.insertDataBitArray = a43;
                RadixTrieMultiDigitalElementArray a44 = this.insertDataBitArray;
                java.awt.Color a45 = RadixTrieMultiColors.INSERT_BIT_ACTIVE_COLOR;
                java.awt.Color a46 = RadixTrieMultiColors.INSERT_BIT_HIGHLIGHT_COLOR;
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
                        java.awt.Color a50 = RadixTrieMultiColors.INSERT_HIGHLIGHT_COLOR;
                        a49.setBackgroundColor(a50);
                        RadixTrieMultiNode a51 = this.head;
                        RadixTrieMultiNode a52 = this.insert(a51, a42, 0, true, i11);
                        this.head = a52;
                        this.clearPointers();
                        RadixTrieMultiExternalNode.clearNewestNode();
                        RadixTrieMultiExternalNode.clearCompareNode();
                        java.util.Vector a53 = new java.util.Vector();
                        this.insertedData = a53;
                        java.util.Vector a54 = this.insertedData;
                        RadixTrieMultiNode a55 = this.head;
                        this.radixTrieToVector(a54, a55);
                        int i16 = this.insertPos;
                        int i17 = i16 + 1;
                        this.insertPos = i17;
                        com.cim.AIA.ElementArray a56 = this.insertData;
                        com.cim.AIA.Element a57 = a56.getElement(i11);
                        com.cim.AIA.Node dummy3 = (com.cim.AIA.Node)a57;
                        com.cim.AIA.Node a58 = (com.cim.AIA.Node)a57;
                        java.awt.Color a59 = RadixTrieMultiColors.INSERT_DONE_COLOR;
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
    
    private RadixTrieMultiNode insert(RadixTrieMultiNode a, com.cim.AIA.DataItem a0, int i, boolean b, int i0)
    {
        RadixTrieMultiNode a1 = null;
        this.currentNode = a;
        RadixTrieMultiNode a2 = this.currentNode;
        this.pathNode = a2;
        this.setPosition("3.0");
        if(a == null)
        {
            RuntimeException a3 = new RuntimeException("trie should not be null");
            throw a3;
        }
        this.setPosition("3.1.1");
        label1: {
            RadixTrieMultiNode a4 = null;
            label0: {
                int i1 = a instanceof RadixTrieMultiNullNode?1:0;
                if(i1 == 0)
                {
                    break label0;
                }
                RadixTrieMultiExternalNode a5 = new RadixTrieMultiExternalNode(a0);
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
            RadixTrieMultiDigitalElementArray a6 = this.insertDataBitArray;
            a6.unHighlight();
            int i2 = 0;
            while(true)
            {
                int i3 = RadixTrieMulti.NO_OF_BITS;
                if(i2 >= i3)
                {
                    break;
                }
                else
                {
                    RadixTrieMultiDigitalElementArray a7 = this.insertDataBitArray;
                    int i4 = this.currentLevel;
                    int i5 = i4 + i2;
                    int i6 = (byte)i5;
                    a7.highlightBit((byte)i6);
                    int i7 = i2 + 1;
                    i2 = i7;
                }
            }
            this.setPosition("3.2.2");
            while(true)
            {
                RadixTrieMultiNode a8 = this.currentNode;
                int i8 = a8 instanceof RadixTrieMultiInternalNode?1:0;
                if(i8 == 0)
                {
                    break;
                }
                this.setPosition("3.3.1");
                int i9 = this.currentLevel;
                int i10 = a0.getKey();
                int i11 = this.getBranch(i9, i10);
                this.setPosition("3.3.1.2.1");
                RadixTrieMultiNode a9 = this.currentNode;
                RadixTrieMultiInternalNode dummy = (RadixTrieMultiInternalNode)a9;
                RadixTrieMultiInternalNode a10 = (RadixTrieMultiInternalNode)a9;
                RadixTrieMultiNode a11 = a10.getChild(i11);
                this.currentNode = a11;
                RadixTrieMultiNode a12 = this.currentNode;
                this.pathNode = a12;
                this.setPosition("3.3.1.2.2");
                RadixTrieMultiNode a13 = this.currentNode;
                int i12 = a13 instanceof RadixTrieMultiInternalNode?1:0;
                if(i12 == 0)
                {
                    int i13 = this.currentLevel;
                    int i14 = RadixTrieMulti.NO_OF_BITS;
                    int i15 = i13 + i14;
                    this.currentLevel = i15;
                    this.setPosition("3.3.1.3");
                    continue;
                }
                int i16 = this.currentLevel;
                int i17 = RadixTrieMulti.NO_OF_BITS;
                int i18 = i16 + i17;
                this.currentLevel = i18;
                RadixTrieMultiDigitalElementArray a14 = this.insertDataBitArray;
                a14.unHighlight();
                int i19 = 0;
                while(true)
                {
                    int i20 = RadixTrieMulti.NO_OF_BITS;
                    if(i19 >= i20)
                    {
                        this.setPosition("3.3.1.3");
                    }
                    else
                    {
                        RadixTrieMultiDigitalElementArray a15 = this.insertDataBitArray;
                        int i21 = this.currentLevel;
                        int i22 = i21 + i19;
                        int i23 = (byte)i22;
                        a15.highlightBit((byte)i23);
                        int i24 = i19 + 1;
                        i19 = i24;
                    }
                }
            }
            this.setPosition("3.3.1");
            this.setPosition("3.3.2");
            RadixTrieMultiNode a16 = this.currentNode;
            RadixTrieMultiInternalNode a17 = a16.getParent();
            this.pathNode = a17;
            RadixTrieMultiNode a18 = this.currentNode;
            label2: {
                int i25 = a18 instanceof RadixTrieMultiNullNode?1:0;
                if(i25 == 0)
                {
                    break label2;
                }
                this.setPosition("3.4.1");
                RadixTrieMultiExternalNode a19 = new RadixTrieMultiExternalNode(a0);
                int i26 = this.currentLevel;
                int i27 = RadixTrieMulti.NO_OF_BITS;
                int i28 = i26 - i27;
                int i29 = a0.getKey();
                int i30 = this.getBranch(i28, i29);
                RadixTrieMultiNode a20 = this.currentNode;
                RadixTrieMultiInternalNode a21 = a20.getParent();
                a21.setChild((RadixTrieMultiNode)a19, i30);
                int i31 = this.currentLevel;
                a19.setLevel(i31);
                a19.setIsLinkedIn();
                this.setPosition("3.4.1.1");
                a1 = a;
                break label1;
            }
            this.setPosition("3.4.1");
            this.setPosition("3.4.2");
            RadixTrieMultiNode a22 = this.currentNode;
            RadixTrieMultiExternalNode dummy0 = (RadixTrieMultiExternalNode)a22;
            RadixTrieMultiExternalNode a23 = (RadixTrieMultiExternalNode)a22;
            int i32 = a23.getKey();
            int i33 = a0.getKey();
            label3: {
                if(i32 != i33)
                {
                    break label3;
                }
                this.setPosition("3.4.2.1");
                this.setPosition("3.4.2.2");
                RadixTrieMultiDigitalElementArray a24 = this.insertDataBitArray;
                String s = localization.Messages.getString("RadixTrieMulti.4");
                com.cim.AIA.StringMarker a25 = new com.cim.AIA.StringMarker(s);
                a24.addMarker(a25);
                this.compareDataBitArray = null;
                this.setPosition("3.4.2.2.1");
                RadixTrieMultiDigitalElementArray a26 = this.insertDataBitArray;
                a26.removeAllMarkers();
                a1 = a;
                break label1;
            }
            this.setPosition("3.4.2.1");
            RadixTrieMultiDigitalElementArray a27 = this.initialiseBitElementArray(i32);
            this.compareDataBitArray = a27;
            RadixTrieMultiDigitalElementArray a28 = this.compareDataBitArray;
            java.awt.Color a29 = RadixTrieMultiColors.COMPARE_BIT_ACTIVE_COLOR;
            java.awt.Color a30 = RadixTrieMultiColors.COMPARE_BIT_HIGHLIGHT_COLOR;
            a28.setColors(a29, a30);
            RadixTrieMultiNode a31 = this.currentNode;
            RadixTrieMultiExternalNode dummy1 = (RadixTrieMultiExternalNode)a31;
            RadixTrieMultiExternalNode a32 = (RadixTrieMultiExternalNode)a31;
            RadixTrieMultiExternalNode.setCompareNode(a32);
            RadixTrieMultiInternalNode a33 = new RadixTrieMultiInternalNode();
            a33.parent = null;
            RadixTrieMultiNode a34 = this.currentNode;
            RadixTrieMultiExternalNode dummy2 = (RadixTrieMultiExternalNode)a34;
            RadixTrieMultiExternalNode a35 = (RadixTrieMultiExternalNode)a34;
            int i34 = this.currentLevel;
            int i35 = RadixTrieMulti.NO_OF_BITS;
            int i36 = i34 - i35;
            int i37 = a0.getKey();
            int i38 = this.getBranch(i36, i37);
            RadixTrieMultiNode a36 = this.currentNode;
            RadixTrieMultiInternalNode a37 = a36.getParent();
            if(a37 == null)
            {
                this.head = a33;
                a4 = a33;
            }
            else
            {
                RadixTrieMultiNode a38 = this.currentNode;
                RadixTrieMultiInternalNode a39 = a38.getParent();
                a39.setChild((RadixTrieMultiNode)a33, i38);
                a4 = a;
            }
            a33.setIsLinkedIn();
            this.currentNode = a33;
            RadixTrieMultiDigitalElementArray a40 = this.compareDataBitArray;
            a40.unHighlight();
            RadixTrieMultiDigitalElementArray a41 = this.insertDataBitArray;
            a41.unHighlight();
            int i39 = 0;
            while(true)
            {
                int i40 = RadixTrieMulti.NO_OF_BITS;
                if(i39 >= i40)
                {
                    break;
                }
                else
                {
                    RadixTrieMultiDigitalElementArray a42 = this.compareDataBitArray;
                    int i41 = this.currentLevel;
                    int i42 = i41 + i39;
                    int i43 = (byte)i42;
                    a42.highlightBit((byte)i43);
                    RadixTrieMultiDigitalElementArray a43 = this.insertDataBitArray;
                    int i44 = this.currentLevel;
                    int i45 = i44 + i39;
                    int i46 = (byte)i45;
                    a43.highlightBit((byte)i46);
                    int i47 = i39 + 1;
                    i39 = i47;
                }
            }
            this.setPosition("3.5.1");
            RadixTrieMultiInternalNode a44 = a33;
            while(true)
            {
                int i48 = this.currentLevel;
                int i49 = this.getBranch(i48, i32);
                int i50 = this.currentLevel;
                int i51 = a0.getKey();
                int i52 = this.getBranch(i50, i51);
                if(i49 != i52)
                {
                    break;
                }
                this.setPosition("3.5.2");
                int i53 = this.currentLevel;
                int i54 = a0.getKey();
                int i55 = this.getBranch(i53, i54);
                RadixTrieMultiInternalNode a45 = new RadixTrieMultiInternalNode();
                this.pathNode = a45;
                a44.setChild((RadixTrieMultiNode)a45, i55);
                RadixTrieMultiNode a46 = a44.getChild(i55);
                RadixTrieMultiInternalNode dummy3 = (RadixTrieMultiInternalNode)a46;
                RadixTrieMultiInternalNode a47 = (RadixTrieMultiInternalNode)a46;
                a45.setIsLinkedIn();
                this.currentNode = a47;
                this.setPosition("3.5.2.1.1");
                int i56 = this.currentLevel;
                int i57 = RadixTrieMulti.NO_OF_BITS;
                int i58 = i56 + i57;
                this.currentLevel = i58;
                RadixTrieMultiDigitalElementArray a48 = this.compareDataBitArray;
                a48.unHighlight();
                RadixTrieMultiDigitalElementArray a49 = this.insertDataBitArray;
                a49.unHighlight();
                int i59 = 0;
                while(true)
                {
                    int i60 = RadixTrieMulti.NO_OF_BITS;
                    if(i59 >= i60)
                    {
                        this.setPosition("3.5.2.1.2");
                        a44 = a47;
                    }
                    else
                    {
                        RadixTrieMultiDigitalElementArray a50 = this.compareDataBitArray;
                        int i61 = this.currentLevel;
                        int i62 = i61 + i59;
                        int i63 = (byte)i62;
                        a50.highlightBit((byte)i63);
                        RadixTrieMultiDigitalElementArray a51 = this.insertDataBitArray;
                        int i64 = this.currentLevel;
                        int i65 = i64 + i59;
                        int i66 = (byte)i65;
                        a51.highlightBit((byte)i66);
                        int i67 = i59 + 1;
                        i59 = i67;
                    }
                }
            }
            this.setPosition("3.5.2");
            this.setPosition("3.5.3");
            int i68 = this.currentLevel;
            int i69 = this.getBranch(i68, i32);
            this.setPosition("3.4.2.1.2.1");
            a44.setChild((RadixTrieMultiNode)a35, i69);
            int i70 = this.currentLevel;
            int i71 = RadixTrieMulti.NO_OF_BITS;
            int i72 = i70 + i71;
            a35.setLevel(i72);
            java.util.Vector a52 = new java.util.Vector();
            this.insertedData = a52;
            java.util.Vector a53 = this.insertedData;
            RadixTrieMultiNode a54 = this.head;
            this.radixTrieToVector(a53, a54);
            this.setPosition("3.4.2.1.2.2");
            int i73 = this.currentLevel;
            int i74 = a0.getKey();
            int i75 = this.getBranch(i73, i74);
            this.setPosition("3.4.2.1.3.1");
            RadixTrieMultiExternalNode a55 = new RadixTrieMultiExternalNode(a0);
            int i76 = this.currentLevel;
            int i77 = RadixTrieMulti.NO_OF_BITS;
            int i78 = i76 + i77;
            a55.setLevel(i78);
            a55.setIsLinkedIn();
            a44.setChild((RadixTrieMultiNode)a55, i75);
            java.util.Vector a56 = new java.util.Vector();
            this.insertedData = a56;
            java.util.Vector a57 = this.insertedData;
            RadixTrieMultiNode a58 = this.head;
            this.radixTrieToVector(a57, a58);
            this.setPosition("3.4.2.1.3.2");
            RadixTrieMultiDigitalElementArray a59 = this.compareDataBitArray;
            a59.unHighlight();
            RadixTrieMultiDigitalElementArray a60 = this.insertDataBitArray;
            a60.unHighlight();
            this.setPosition("3.4.2.1.4");
            a1 = a4;
        }
        return a1;
    }
    
    private boolean search(RadixTrieMultiNode a, com.cim.AIA.DataItem a0, int i)
    {
        int i0 = 0;
        this.setPosition("5");
        this.currentLevel = 0;
        RadixTrieMultiDigitalElementArray a1 = this.searchDataBitArray;
        a1.unHighlight();
        int i1 = 0;
        while(true)
        {
            int i2 = RadixTrieMulti.NO_OF_BITS;
            if(i1 >= i2)
            {
                break;
            }
            else
            {
                RadixTrieMultiDigitalElementArray a2 = this.searchDataBitArray;
                int i3 = this.currentLevel;
                int i4 = i3 + i1;
                int i5 = (byte)i4;
                a2.highlightBit((byte)i5);
                int i6 = i1 + 1;
                i1 = i6;
            }
        }
        this.currentNode = a;
        RadixTrieMultiNode a3 = this.currentNode;
        this.pathNode = a3;
        this.setPosition("5.1.1");
        while(true)
        {
            RadixTrieMultiNode a4 = this.currentNode;
            int i7 = a4 instanceof RadixTrieMultiInternalNode?1:0;
            if(i7 == 0)
            {
                break;
            }
            this.setPosition("5.1.2");
            int i8 = this.currentLevel;
            int i9 = a0.getKey();
            int i10 = this.getBranch(i8, i9);
            this.setPosition("5.1.2.1.1");
            RadixTrieMultiNode a5 = this.currentNode;
            RadixTrieMultiInternalNode dummy = (RadixTrieMultiInternalNode)a5;
            RadixTrieMultiInternalNode a6 = (RadixTrieMultiInternalNode)a5;
            RadixTrieMultiNode a7 = a6.getChild(i10);
            this.currentNode = a7;
            RadixTrieMultiNode a8 = this.currentNode;
            this.pathNode = a8;
            this.setPosition("5.1.2.1.2");
            int i11 = this.currentLevel;
            int i12 = RadixTrieMulti.NO_OF_BITS;
            int i13 = i11 + i12;
            this.currentLevel = i13;
            RadixTrieMultiDigitalElementArray a9 = this.searchDataBitArray;
            a9.unHighlight();
            int i14 = 0;
            while(true)
            {
                int i15 = RadixTrieMulti.NO_OF_BITS;
                if(i14 >= i15)
                {
                    this.setPosition("5.1.2.2");
                }
                else
                {
                    RadixTrieMultiDigitalElementArray a10 = this.searchDataBitArray;
                    int i16 = this.currentLevel;
                    int i17 = i16 + i14;
                    int i18 = (byte)i17;
                    a10.highlightBit((byte)i18);
                    int i19 = i14 + 1;
                    i14 = i19;
                }
            }
        }
        this.setPosition("5.1.2");
        this.setPosition("5.1.3");
        RadixTrieMultiNode a11 = this.currentNode;
        int i20 = a11 instanceof RadixTrieMultiExternalNode?1:0;
        if(i20 != 0)
        {
            RadixTrieMultiNode a12 = this.currentNode;
            RadixTrieMultiExternalNode dummy0 = (RadixTrieMultiExternalNode)a12;
            RadixTrieMultiExternalNode a13 = (RadixTrieMultiExternalNode)a12;
            int i21 = a13.getKey();
            int i22 = this.mostSignificantBit;
            RadixTrieMultiDigitalElementArray a14 = new RadixTrieMultiDigitalElementArray(i21, i22, false);
            this.finalCompareDataBitArray = a14;
            RadixTrieMultiDigitalElementArray a15 = this.finalCompareDataBitArray;
            java.awt.Color a16 = RadixTrieMultiColors.COMPARE_BIT_HIGHLIGHT_COLOR;
            java.awt.Color a17 = RadixTrieMultiColors.COMPARE_BIT_HIGHLIGHT_COLOR;
            a15.setColors(a16, a17);
        }
        RadixTrieMultiNode a18 = this.currentNode;
        label1: {
            label0: {
                int i23 = a18 instanceof RadixTrieMultiExternalNode?1:0;
                if(i23 == 0)
                {
                    break label0;
                }
                RadixTrieMultiNode a19 = this.currentNode;
                RadixTrieMultiExternalNode dummy1 = (RadixTrieMultiExternalNode)a19;
                RadixTrieMultiExternalNode a20 = (RadixTrieMultiExternalNode)a19;
                int i24 = a20.getKey();
                int i25 = a0.getKey();
                if(i24 != i25)
                {
                    break label0;
                }
                this.setPosition("5.2.1");
                RadixTrieMultiDigitalElementArray a21 = this.searchDataBitArray;
                a21.unHighlight();
                this.setPosition("5.2.1.1");
                i0 = 1;
                break label1;
            }
            this.setPosition("5.2.1");
            this.setPosition("5.2.2");
            RadixTrieMultiDigitalElementArray a22 = this.searchDataBitArray;
            a22.unHighlight();
            this.setPosition("5.2.2.1");
            i0 = 0;
        }
        return i0 != 0;
    }
    
    private void save()
    {
        RadixTrieMultiNode a = this.head;
        this.savedTree = a;
    }
    
    protected void storeState(boolean b)
    {
        this.save();
    }
    
    protected void clearState()
    {
        RadixTrieMultiNullNode a = new RadixTrieMultiNullNode((RadixTrieMultiInternalNode)null);
        this.head = a;
    }
    
    public int getBranch(int i, int i0)
    {
        int i1 = this.mostSignificantBit;
        int i2 = i1 - i;
        int i3 = RadixTrieMulti.NO_OF_BITS;
        int i4 = i3 - 1;
        int i5 = i2 - i4;
        int i6 = i0 >> i5;
        int i7 = RadixTrieMulti.NO_OF_NODES;
        int i8 = i6 % i7;
        return i8;
    }
    
    private void setPosition(String s, boolean b)
    {
        if(!b)
        {
            String s0 = localization.Messages.getString("RadixTrieMulti.5");
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
        RadixTrieMultiNode a = this.currentNode;
        this.pathNode = a;
        this.insertDataBitArray = null;
        this.searchDataBitArray = null;
        this.compareDataBitArray = null;
        this.finalCompareDataBitArray = null;
        java.util.Vector a0 = new java.util.Vector();
        this.insertedData = a0;
        java.util.Vector a1 = this.insertedData;
        RadixTrieMultiNode a2 = this.head;
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
        RadixTrieMulti.NO_OF_NODES = 4;
        RadixTrieMulti.NO_OF_BITS = 2;
        RadixTrieMulti.mnuLink = null;
        String s = localization.Messages.getString("RadixTrieMulti.0");
        RadixTrieMulti.BUILD_MODE_LABEL = s;
        String s0 = localization.Messages.getString("RadixTrieMulti.1");
        RadixTrieMulti.SEARCH_MODE_LABEL = s0;
    }
}