public class SplayTree extends com.cim.AIA.Algorithm implements com.cim.AIA.MethodSelectionListener, com.cim.AIA.ControlListener {
    final private static String BUILD_MODE_LABEL;
    final private static String SEARCH_MODE_LABEL;
    final private static int BUILD_MODE = 11;
    final private static int SEARCH_MODE = 12;
    private int nextMode;
    private int executingMode;
    final private static int DEFAULT_ELEMENT_WIDTH = 20;
    private SplayTreeNode currentNode;
    private SplayTreeNode switchPath;
    private java.util.Vector tweenVectorVector;
    private boolean isTweenFast;
    private boolean isBackMode;
    private SplayTreeCodeStack codeStack;
    private Boolean wasFound;
    private SplayTreeNode head;
    private SplayTreeNode savedTree;
    private SplayTreeNode startingTree;
    private com.cim.AIA.ElementArray insertData;
    private com.cim.AIA.ElementArray newInsertData;
    private com.cim.AIA.ElementArray searchData;
    private com.cim.AIA.ElementArray newSearchData;
    private int insertPos;
    private int lastInsertPos;
    
    public SplayTree(com.cim.AIA.AlgorithmThread a, Object a0)
    {
        super(a, a0);
        this.isBackMode = false;
        SplayTreeNullNode a1 = new SplayTreeNullNode();
        this.head = a1;
        SplayTreeNullNode a2 = new SplayTreeNullNode();
        this.savedTree = a2;
        this.executingMode = 11;
        int i = this.executingMode;
        this.nextMode = i;
        this.changeData(a0);
        com.cim.AIA.ElementArray a3 = this.newInsertData;
        this.insertData = a3;
        com.cim.AIA.ElementArray a4 = this.newSearchData;
        this.searchData = a4;
        SplayTreeCodeStack a5 = new SplayTreeCodeStack();
        this.codeStack = a5;
    }
    
    public void reInitialise(Object a)
    {
        this.tweenVectorVector = null;
        int i = this.isBackMode?1:0;
        if(i == 0)
        {
            this.changeData(a);
            com.cim.AIA.ElementArray a0 = this.newInsertData;
            this.insertData = a0;
            com.cim.AIA.ElementArray a1 = this.newSearchData;
            this.searchData = a1;
        }
        this.resetColors();
        int i0 = this.executingMode;
        label0: {
            if(i0 != 11)
            {
                break label0;
            }
            int i1 = this.isBackMode?1:0;
            if(i1 == 0)
            {
                SplayTreeNullNode a2 = new SplayTreeNullNode();
                this.savedTree = a2;
                this.insertPos = 0;
            }
        }
        int i2 = this.executingMode;
        label1: {
            if(i2 != 12)
            {
                break label1;
            }
            int i3 = this.isBackMode?1:0;
            if(i3 == 0)
            {
                SplayTreeNode a3 = this.savedTree;
                SplayTreeNode a4 = a3.getCopy();
                this.head = a4;
            }
        }
        int i4 = this.isBackMode?1:0;
        label2: {
            if(i4 == 0)
            {
                break label2;
            }
            int i5 = this.executingMode;
            label4: {
                label3: {
                    if(i5 != 11)
                    {
                        break label3;
                    }
                    SplayTreeNode a5 = this.startingTree;
                    SplayTreeNode a6 = a5.getCopy();
                    this.head = a6;
                    break label4;
                }
                int i6 = this.executingMode;
                if(i6 == 12)
                {
                    SplayTreeNode a7 = this.savedTree;
                    SplayTreeNode a8 = a7.getCopy();
                    this.head = a8;
                }
            }
            int i7 = this.lastInsertPos;
            this.insertPos = i7;
        }
    }
    
    protected void resetColors()
    {
        com.cim.AIA.ElementArray a = this.insertData;
        label0: {
            if(a == null)
            {
                break label0;
            }
            int i = this.insertPos;
            int i0 = i;
            while(true)
            {
                com.cim.AIA.ElementArray a0 = this.insertData;
                int i1 = a0.getSize();
                if(i0 >= i1)
                {
                    break;
                }
                else
                {
                    com.cim.AIA.ElementArray a1 = this.insertData;
                    com.cim.AIA.Element a2 = a1.getElement(i0);
                    com.cim.AIA.Node dummy = (com.cim.AIA.Node)a2;
                    com.cim.AIA.Node a3 = (com.cim.AIA.Node)a2;
                    java.awt.Color a4 = SplayTreeColors.INSERT_ACTIVE_COLOR;
                    a3.setBackgroundColor(a4);
                    int i2 = i0 + 1;
                    i0 = i2;
                }
            }
        }
        com.cim.AIA.ElementArray a5 = this.searchData;
        label1: {
            if(a5 == null)
            {
                break label1;
            }
            int i3 = 0;
            while(true)
            {
                com.cim.AIA.ElementArray a6 = this.searchData;
                int i4 = a6.getSize();
                if(i3 >= i4)
                {
                    break;
                }
                else
                {
                    com.cim.AIA.ElementArray a7 = this.searchData;
                    com.cim.AIA.Element a8 = a7.getElement(i3);
                    com.cim.AIA.Node dummy0 = (com.cim.AIA.Node)a8;
                    com.cim.AIA.Node a9 = (com.cim.AIA.Node)a8;
                    java.awt.Color a10 = SplayTreeColors.SEARCH_ACTIVE_COLOR;
                    a9.setBackgroundColor(a10);
                    com.cim.AIA.ElementArray a11 = this.searchData;
                    com.cim.AIA.Element a12 = a11.getElement(i3);
                    com.cim.AIA.Node dummy1 = (com.cim.AIA.Node)a12;
                    com.cim.AIA.Node a13 = (com.cim.AIA.Node)a12;
                    a13.removeAllMarkers();
                    int i5 = i3 + 1;
                    i3 = i5;
                }
            }
        }
    }
    
    protected void changeData(Object a)
    {
        this.tweenVectorVector = null;
        java.util.Random a0 = new java.util.Random();
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
            com.cim.AIA.ElementArray a1 = new com.cim.AIA.ElementArray(0, 0);
            this.newInsertData = a1;
            com.cim.AIA.ElementArray a2 = this.newInsertData;
            a2.setColumGap(0);
            com.cim.AIA.ElementArray a3 = this.newInsertData;
            a3.setElementWidth(20);
            int i1 = 0;
            while(true)
            {
                int[] dummy = (int[])a;
                int[] a4 = (int[])a;
                int i2 = a4.length;
                if(i1 >= i2)
                {
                    break;
                }
                else
                {
                    com.cim.AIA.ElementArray a5 = this.newInsertData;
                    int[] dummy0 = (int[])a;
                    int[] a6 = (int[])a;
                    int i3 = a6[i1];
                    Integer a7 = new Integer(i3);
                    com.cim.AIA.Node a8 = new com.cim.AIA.Node((Object)a7, i1);
                    a5.add((Object)a8, i1);
                    com.cim.AIA.ElementArray a9 = this.newInsertData;
                    com.cim.AIA.Element a10 = a9.getElement(i1);
                    com.cim.AIA.Node dummy1 = (com.cim.AIA.Node)a10;
                    com.cim.AIA.Node a11 = (com.cim.AIA.Node)a10;
                    java.awt.Color a12 = SplayTreeColors.INSERT_ACTIVE_COLOR;
                    a11.setBackgroundColor(a12);
                    int i4 = i1 + 1;
                    i1 = i4;
                }
            }
        }
        com.cim.AIA.ElementArray a13 = new com.cim.AIA.ElementArray(0, 0);
        this.newSearchData = a13;
        com.cim.AIA.ElementArray a14 = this.newSearchData;
        a14.setColumGap(0);
        com.cim.AIA.ElementArray a15 = this.newSearchData;
        a15.setElementWidth(20);
        int i5 = 0;
        while(true)
        {
            int[] dummy2 = (int[])a;
            int[] a16 = (int[])a;
            int i6 = a16.length;
            if(i5 >= i6)
            {
                return;
            }
            int i7 = i5 % 2;
            if(i7 != 0)
            {
                com.cim.AIA.ElementArray a17 = this.newSearchData;
                int[] dummy3 = (int[])a;
                int[] a18 = (int[])a;
                int i8 = a18[i5];
                Integer a19 = new Integer(i8);
                com.cim.AIA.Node a20 = new com.cim.AIA.Node((Object)a19, i5);
                a17.add((Object)a20, i5);
            }
            else
            {
                com.cim.AIA.ElementArray a21 = this.newSearchData;
                int i9 = a0.nextInt();
                int i10 = Math.abs(i9);
                int i11 = i10 % 98;
                int i12 = i11 + 1;
                Integer a22 = new Integer(i12);
                com.cim.AIA.Node a23 = new com.cim.AIA.Node((Object)a22, i5);
                a21.add((Object)a23, i5);
            }
            com.cim.AIA.ElementArray a24 = this.newSearchData;
            com.cim.AIA.Element a25 = a24.getElement(i5);
            com.cim.AIA.Node dummy4 = (com.cim.AIA.Node)a25;
            com.cim.AIA.Node a26 = (com.cim.AIA.Node)a25;
            java.awt.Color a27 = SplayTreeColors.SEARCH_ACTIVE_COLOR;
            a26.setBackgroundColor(a27);
            int i13 = i5 + 1;
            i5 = i13;
        }
    }
    
    private void save()
    {
        java.io.PrintStream a = System.out;
        a.println("Saving tree");
        this.resetColors();
        int i = this.executingMode;
        label1: {
            label0: {
                if(i != 11)
                {
                    break label0;
                }
                SplayTreeNode a0 = this.head;
                SplayTreeNode a1 = a0.getCopy();
                this.savedTree = a1;
                break label1;
            }
            int i0 = this.executingMode;
            if(i0 == 12)
            {
                SplayTreeNode a2 = this.savedTree;
                SplayTreeNode a3 = a2.getCopy();
                this.head = a3;
            }
        }
    }
    
    protected void storeState(boolean b)
    {
        this.save();
    }
    
    protected void clearState()
    {
        SplayTreeNullNode a = new SplayTreeNullNode();
        this.head = a;
    }
    
    protected void initialiseMethods(String s, com.cim.AIA.MethodSelectable a)
    {
        Object a0 = a;
        String s0 = SplayTree.BUILD_MODE_LABEL;
        String s1 = localization.Messages.getString("SplayTree.2");
        com.cim.AIA.Logger a1 = this.getLogger();
        com.cim.AIA.BreakPoint a2 = this.getBreakPoint();
        com.cim.AIA.MethodSelection a3 = new com.cim.AIA.MethodSelection(s0, s, s1, "3a", a1, a2);
        String s2 = SplayTree.SEARCH_MODE_LABEL;
        String s3 = localization.Messages.getString("SplayTree.3");
        com.cim.AIA.Logger a4 = this.getLogger();
        com.cim.AIA.BreakPoint a5 = this.getBreakPoint();
        com.cim.AIA.MethodSelection a6 = new com.cim.AIA.MethodSelection(s2, s, s3, "5a", a4, a5);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelection(a3, true);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelection(a6, false);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelectionListener((com.cim.AIA.MethodSelectionListener)this);
    }
    
    public void processMethodSelectionEvent(com.cim.AIA.MethodSelectionEvent a)
    {
        String s = SplayTree.BUILD_MODE_LABEL;
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
            String s1 = SplayTree.SEARCH_MODE_LABEL;
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
            SplayTreeNode a0 = this.savedTree;
            this.head = a0;
        }
        int i2 = this.nextMode;
        label2: {
            if(i2 != 12)
            {
                break label2;
            }
            SplayTreeNode a1 = this.savedTree;
            if(a1 != null)
            {
                SplayTreeNode a2 = this.savedTree;
                SplayTreeNode a3 = a2.getCopy();
                this.head = a3;
            }
        }
    }
    
    public com.cim.AIA.MultipleTween generateTweens(com.cim.AIA.Paintable a, Object a0, int i)
    {
        return null;
    }
    
    public SplayTreeCodeStack getCodeStack()
    {
        SplayTreeCodeStack a = this.codeStack;
        return a;
    }
    
    public java.util.Vector getTweens(com.cim.AIA.Paintable a, Object a0, int i)
    {
        Object a1 = a;
        java.util.Vector a2 = new java.util.Vector();
        java.util.Vector a3 = this.tweenVectorVector;
        label0: {
            if(a3 == null)
            {
                break label0;
            }
            int i0 = 0;
            while(true)
            {
                java.util.Vector a4 = this.tweenVectorVector;
                int i1 = a4.size();
                if(i0 >= i1)
                {
                    break;
                }
                java.util.Vector a5 = this.tweenVectorVector;
                Object a6 = a5.elementAt(i0);
                java.util.Vector dummy = (java.util.Vector)a6;
                java.util.Vector a7 = (java.util.Vector)a6;
                label1: {
                    int i2 = 0;
                    if(a7 == null)
                    {
                        break label1;
                    }
                    com.cim.AIA.MultipleTween a8 = new com.cim.AIA.MultipleTween((com.cim.AIA.Paintable)a1);
                    Object a9 = a7.elementAt(0);
                    int i3 = a9 instanceof java.util.Hashtable?1:0;
                    if(i3 == 0)
                    {
                        i2 = 0;
                    }
                    else
                    {
                        Object a10 = a7.elementAt(0);
                        java.util.Hashtable dummy0 = (java.util.Hashtable)a10;
                        java.util.Hashtable a11 = (java.util.Hashtable)a10;
                        a2.addElement((Object)a11);
                        i2 = 1;
                    }
                    while(true)
                    {
                        int i4 = a7.size();
                        if(i2 >= i4)
                        {
                            break;
                        }
                        else
                        {
                            Object a12 = a7.elementAt(i2);
                            SplayTree$MoveMoveable dummy1 = (SplayTree$MoveMoveable)a12;
                            SplayTree$MoveMoveable a13 = (SplayTree$MoveMoveable)a12;
                            Object a14 = SplayTree$MoveMoveable.access$000(a13);
                            Object a15 = a7.elementAt(i2);
                            SplayTree$MoveMoveable dummy2 = (SplayTree$MoveMoveable)a15;
                            SplayTree$MoveMoveable a16 = (SplayTree$MoveMoveable)a15;
                            java.awt.Point a17 = SplayTree$MoveMoveable.access$100(a16);
                            Object a18 = a7.elementAt(i2);
                            SplayTree$MoveMoveable dummy3 = (SplayTree$MoveMoveable)a18;
                            SplayTree$MoveMoveable a19 = (SplayTree$MoveMoveable)a18;
                            java.awt.Point a20 = SplayTree$MoveMoveable.access$200(a19);
                            Object a21 = a7.elementAt(i2);
                            SplayTree$MoveMoveable dummy4 = (SplayTree$MoveMoveable)a21;
                            SplayTree$MoveMoveable a22 = (SplayTree$MoveMoveable)a21;
                            int i5 = SplayTree$MoveMoveable.access$300(a22)?1:0;
                            com.cim.AIA.MoveTween a23 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)a1, (com.cim.AIA.Moveable)a14, a17, a20, i5 != 0, i);
                            a8.add((com.cim.AIA.Tween)a23);
                            int i6 = i2 + 1;
                            i2 = i6;
                        }
                    }
                    int i7 = a8.getNumberOfTweens();
                    if(i7 > 0)
                    {
                        a2.addElement((Object)a8);
                    }
                }
                int i8 = i0 + 1;
                i0 = i8;
            }
        }
        this.tweenVectorVector = null;
        int i9 = a2.size();
        java.util.Vector a24 = i9 <= 0?null:a2;
        return a24;
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
    
    public SplayTreeNode getHead()
    {
        SplayTreeNode a = this.head;
        this.unHighlightPath(a);
        SplayTreeNode a0 = this.switchPath;
        this.highlightSwitchPath(a0);
        SplayTreeNode a1 = this.currentNode;
        this.highlightPath(a1);
        SplayTreeNode a2 = this.head;
        return a2;
    }
    
    public void highlightSwitchPath(SplayTreeNode a)
    {
        if(a != null)
        {
            a.setIsOnSwitchPath(true);
            SplayTreeDataNode a0 = a.getParent();
            this.highlightSwitchPath((SplayTreeNode)a0);
        }
    }
    
    public void highlightPath(SplayTreeNode a)
    {
        if(a != null)
        {
            a.setIsOnPath(true);
            SplayTreeDataNode a0 = a.getParent();
            this.highlightPath((SplayTreeNode)a0);
        }
    }
    
    private void unHighlightPath(SplayTreeNode a)
    {
        label0: {
            if(a == null)
            {
                break label0;
            }
            a.setIsOnPath(false);
            a.setIsOnSwitchPath(false);
            int i = a instanceof SplayTreeDataNode?1:0;
            if(i != 0)
            {
                SplayTreeDataNode dummy = (SplayTreeDataNode)a;
                SplayTreeDataNode a0 = (SplayTreeDataNode)a;
                SplayTreeNode a1 = a0.getLeft();
                this.unHighlightPath(a1);
                SplayTreeDataNode dummy0 = (SplayTreeDataNode)a;
                SplayTreeDataNode a2 = (SplayTreeDataNode)a;
                SplayTreeNode a3 = a2.getRight();
                this.unHighlightPath(a3);
            }
        }
    }
    
    public SplayTreeNode getCurrentNode()
    {
        SplayTreeNode a = this.currentNode;
        return a;
    }
    
    protected void run(boolean b)
    {
        if(b)
        {
            int i = this.nextMode;
            this.executingMode = i;
            SplayTreeDataNode.clearNodeColors();
        }
        this.run();
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
            SplayTreeNode a = this.savedTree;
            if(a == null)
            {
                SplayTreeNullNode a0 = new SplayTreeNullNode();
                this.startingTree = a0;
            }
            else
            {
                SplayTreeNode a1 = this.savedTree;
                SplayTreeNode a2 = a1.getCopy();
                this.startingTree = a2;
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
                    com.cim.AIA.ElementArray a3 = this.searchData;
                    int i4 = a3.getSize();
                    if(i3 >= i4)
                    {
                        break label2;
                    }
                    com.cim.AIA.ElementArray a4 = this.searchData;
                    com.cim.AIA.Element a5 = a4.getElement(i3);
                    com.cim.AIA.Node dummy = (com.cim.AIA.Node)a5;
                    com.cim.AIA.Node a6 = (com.cim.AIA.Node)a5;
                    Object a7 = a6.getObject();
                    String s = a7.toString();
                    Object a8 = a6.getObject();
                    Integer dummy0 = (Integer)a8;
                    Integer a9 = (Integer)a8;
                    int i5 = a9.intValue();
                    SplayTreeDataItem a10 = new SplayTreeDataItem(s, i5);
                    int i6 = this.enabled?1:0;
                    label3: {
                        if(i6 == 0)
                        {
                            break label3;
                        }
                        java.awt.Color a11 = SplayTreeColors.SEARCH_HIGHLIGHT_COLOR;
                        a6.setBackgroundColor(a11);
                        this.wasFound = null;
                        SplayTreeNode a12 = this.head;
                        this.currentNode = a12;
                        SplayTreeNode a13 = this.head;
                        SplayTreeNode a14 = this.search(a13, a10, (Boolean)null);
                        this.head = a14;
                        this.currentNode = null;
                        Boolean a15 = this.wasFound;
                        label4: {
                            if(a15 == null)
                            {
                                break label4;
                            }
                            Boolean a16 = this.wasFound;
                            int i7 = a16.booleanValue()?1:0;
                            if(i7 == 0)
                            {
                                com.cim.AIA.StringMarker a17 = new com.cim.AIA.StringMarker("N");
                                a6.addMarker(a17);
                                com.cim.AIA.StringMarker a18 = new com.cim.AIA.StringMarker("o");
                                a6.addMarker(a18);
                                com.cim.AIA.StringMarker a19 = new com.cim.AIA.StringMarker("t");
                                a6.addMarker(a19);
                                com.cim.AIA.StringMarker a20 = new com.cim.AIA.StringMarker(" ");
                                a6.addMarker(a20);
                                com.cim.AIA.StringMarker a21 = new com.cim.AIA.StringMarker("F");
                                a6.addMarker(a21);
                                com.cim.AIA.StringMarker a22 = new com.cim.AIA.StringMarker("o");
                                a6.addMarker(a22);
                                com.cim.AIA.StringMarker a23 = new com.cim.AIA.StringMarker("u");
                                a6.addMarker(a23);
                                com.cim.AIA.StringMarker a24 = new com.cim.AIA.StringMarker("n");
                                a6.addMarker(a24);
                                com.cim.AIA.StringMarker a25 = new com.cim.AIA.StringMarker("d");
                                a6.addMarker(a25);
                            }
                            else
                            {
                                com.cim.AIA.StringMarker a26 = new com.cim.AIA.StringMarker("F");
                                a6.addMarker(a26);
                                com.cim.AIA.StringMarker a27 = new com.cim.AIA.StringMarker("o");
                                a6.addMarker(a27);
                                com.cim.AIA.StringMarker a28 = new com.cim.AIA.StringMarker("u");
                                a6.addMarker(a28);
                                com.cim.AIA.StringMarker a29 = new com.cim.AIA.StringMarker("n");
                                a6.addMarker(a29);
                                com.cim.AIA.StringMarker a30 = new com.cim.AIA.StringMarker("d");
                                a6.addMarker(a30);
                            }
                        }
                        java.awt.Color a31 = SplayTreeColors.SEARCH_DONE_COLOR;
                        a6.setBackgroundColor(a31);
                    }
                    this.clearPointers();
                    int i8 = i3 + 1;
                    i3 = i8;
                }
            }
            int i9 = i2;
            while(true)
            {
                com.cim.AIA.ElementArray a32 = this.insertData;
                int i10 = a32.getSize();
                if(i9 >= i10)
                {
                    break;
                }
                this.setPosition("2a");
                com.cim.AIA.ElementArray a33 = this.insertData;
                com.cim.AIA.Element a34 = a33.getElement(i9);
                Object a35 = a34.getObject();
                String s0 = a35.toString();
                com.cim.AIA.ElementArray a36 = this.insertData;
                com.cim.AIA.Element a37 = a36.getElement(i9);
                Object a38 = a37.getObject();
                Integer dummy1 = (Integer)a38;
                Integer a39 = (Integer)a38;
                int i11 = a39.intValue();
                SplayTreeDataItem a40 = new SplayTreeDataItem(s0, i11);
                int i12 = this.enabled?1:0;
                label5: {
                    if(i12 == 0)
                    {
                        break label5;
                    }
                    com.cim.AIA.ElementArray a41 = this.insertData;
                    com.cim.AIA.Element a42 = a41.getElement(i9);
                    com.cim.AIA.Node dummy2 = (com.cim.AIA.Node)a42;
                    com.cim.AIA.Node a43 = (com.cim.AIA.Node)a42;
                    java.awt.Color a44 = SplayTreeColors.INSERT_HIGHLIGHT_COLOR;
                    a43.setBackgroundColor(a44);
                    SplayTreeNode a45 = this.head;
                    this.currentNode = a45;
                    SplayTreeNode a46 = this.head;
                    SplayTreeNode a47 = a46.getCopy();
                    this.savedTree = a47;
                    SplayTreeNode a48 = this.head;
                    SplayTreeNode a49 = this.insert(a48, a40, (Boolean)null);
                    this.head = a49;
                    this.currentNode = null;
                    com.cim.AIA.ElementArray a50 = this.insertData;
                    com.cim.AIA.Element a51 = a50.getElement(i9);
                    com.cim.AIA.Node dummy3 = (com.cim.AIA.Node)a51;
                    com.cim.AIA.Node a52 = (com.cim.AIA.Node)a51;
                    java.awt.Color a53 = SplayTreeColors.INSERT_DONE_COLOR;
                    a52.setBackgroundColor(a53);
                    int i13 = this.enabled?1:0;
                    if(i13 == 0)
                    {
                        SplayTreeNode a54 = this.savedTree;
                        SplayTreeNode a55 = a54.getCopy();
                        this.head = a55;
                    }
                    else
                    {
                        int i14 = this.insertPos;
                        int i15 = i14 + 1;
                        this.insertPos = i15;
                    }
                }
                this.clearPointers();
                int i16 = i9 + 1;
                i9 = i16;
            }
        }
    }
    
    private SplayTreeDataNode rotateRight(SplayTreeNode a)
    {
        SplayTreeDataNode a0 = null;
        SplayTreeDataNode dummy = (SplayTreeDataNode)a;
        SplayTreeDataNode a1 = (SplayTreeDataNode)a;
        SplayTreeNode a2 = a1.getLeft();
        label1: {
            label0: {
                int i = a2 instanceof SplayTreeNullNode?1:0;
                if(i == 0)
                {
                    break label0;
                }
                SplayTreeDataNode dummy0 = (SplayTreeDataNode)a;
                SplayTreeDataNode a3 = (SplayTreeDataNode)a;
                a0 = a3;
                break label1;
            }
            java.util.Vector a4 = this.tweenVectorVector;
            if(a4 == null)
            {
                java.util.Vector a5 = new java.util.Vector();
                this.tweenVectorVector = a5;
            }
            java.util.Vector a6 = new java.util.Vector();
            SplayTreeDataNode a7 = a.getParent();
            SplayTreeDataNode dummy1 = (SplayTreeDataNode)a;
            SplayTreeDataNode a8 = (SplayTreeDataNode)a;
            SplayTreeNode a9 = a8.getLeft();
            SplayTreeDataNode dummy2 = (SplayTreeDataNode)a9;
            SplayTreeDataNode a10 = (SplayTreeDataNode)a9;
            label2: {
                if(a7 == null)
                {
                    break label2;
                }
                SplayTreeNode a11 = a7.getLeft();
                if(a11 != a)
                {
                    com.cim.AIA.Line a12 = a7.getRightLink();
                    java.awt.Point a13 = a.getPosition();
                    java.awt.Point a14 = new java.awt.Point(a13);
                    java.awt.Point a15 = a10.getPosition();
                    java.awt.Point a16 = new java.awt.Point(a15);
                    SplayTree$MoveMoveable a17 = new SplayTree$MoveMoveable(this, (com.cim.AIA.Moveable)a12, a14, a16, false);
                    a6.addElement((Object)a17);
                }
                else
                {
                    com.cim.AIA.Line a18 = a7.getLeftLink();
                    java.awt.Point a19 = a.getPosition();
                    java.awt.Point a20 = new java.awt.Point(a19);
                    java.awt.Point a21 = a10.getPosition();
                    java.awt.Point a22 = new java.awt.Point(a21);
                    SplayTree$MoveMoveable a23 = new SplayTree$MoveMoveable(this, (com.cim.AIA.Moveable)a18, a20, a22, false);
                    a6.addElement((Object)a23);
                }
            }
            SplayTreeDataNode dummy3 = (SplayTreeDataNode)a;
            SplayTreeDataNode a24 = (SplayTreeDataNode)a;
            com.cim.AIA.Line a25 = a24.getLeftLink();
            SplayTreeDataNode dummy4 = (SplayTreeDataNode)a;
            SplayTreeDataNode a26 = (SplayTreeDataNode)a;
            SplayTreeNode a27 = a26.getLeft();
            java.awt.Point a28 = a27.getPosition();
            java.awt.Point a29 = new java.awt.Point(a28);
            SplayTreeNode a30 = a10.getRight();
            java.awt.Point a31 = a30.getPosition();
            java.awt.Point a32 = new java.awt.Point(a31);
            SplayTree$MoveMoveable a33 = new SplayTree$MoveMoveable(this, (com.cim.AIA.Moveable)a25, a29, a32, false);
            a6.addElement((Object)a33);
            com.cim.AIA.Line a34 = a10.getRightLink();
            SplayTreeNode a35 = a10.getRight();
            java.awt.Point a36 = a35.getPosition();
            java.awt.Point a37 = new java.awt.Point(a36);
            java.awt.Point a38 = a.getPosition();
            java.awt.Point a39 = new java.awt.Point(a38);
            SplayTree$MoveMoveable a40 = new SplayTree$MoveMoveable(this, (com.cim.AIA.Moveable)a34, a37, a39, false);
            a6.addElement((Object)a40);
            java.util.Vector a41 = this.tweenVectorVector;
            a41.addElement((Object)a6);
            int i0 = this.isTweenFast?1:0;
            this.animate("$This key doesn't exist###");
            this.isTweenFast = i0 != 0;
            java.util.Vector a42 = this.tweenVectorVector;
            if(a42 == null)
            {
                java.util.Vector a43 = new java.util.Vector();
                this.tweenVectorVector = a43;
            }
            java.util.Vector a44 = new java.util.Vector();
            java.awt.Point a45 = a.getPosition();
            java.awt.Point a46 = new java.awt.Point(a45);
            java.awt.Point a47 = a.getPosition();
            int i1 = a47.x;
            SplayTreeDataNode dummy5 = (SplayTreeDataNode)a;
            SplayTreeDataNode a48 = (SplayTreeDataNode)a;
            SplayTreeNode a49 = a48.getLeft();
            java.awt.Point a50 = a49.getPosition();
            int i2 = a50.y;
            java.awt.Point a51 = new java.awt.Point(i1, i2);
            SplayTree$MoveMoveable a52 = new SplayTree$MoveMoveable(this, (com.cim.AIA.Moveable)a, a46, a51, false);
            a44.addElement((Object)a52);
            SplayTreeDataNode dummy6 = (SplayTreeDataNode)a;
            SplayTreeDataNode a53 = (SplayTreeDataNode)a;
            SplayTreeNode a54 = a53.getRight();
            java.awt.Point a55 = a54.getPosition();
            java.awt.Point a56 = new java.awt.Point(a55);
            java.awt.Point a57 = a54.getPosition();
            SplayTree$MoveMoveable a58 = new SplayTree$MoveMoveable(this, (com.cim.AIA.Moveable)a54, a56, a57, true);
            a44.addElement((Object)a58);
            SplayTreeNode a59 = a10.getLeft();
            SplayTreeNode a60 = a10.getLeft();
            java.awt.Point a61 = a60.getPosition();
            java.awt.Point a62 = new java.awt.Point(a61);
            SplayTreeNode a63 = a10.getLeft();
            java.awt.Point a64 = a63.getPosition();
            int i3 = a64.x;
            SplayTreeDataNode dummy7 = (SplayTreeDataNode)a;
            SplayTreeDataNode a65 = (SplayTreeDataNode)a;
            SplayTreeNode a66 = a65.getLeft();
            java.awt.Point a67 = a66.getPosition();
            int i4 = a67.y;
            java.awt.Point a68 = new java.awt.Point(i3, i4);
            SplayTree$MoveMoveable a69 = new SplayTree$MoveMoveable(this, (com.cim.AIA.Moveable)a59, a62, a68, true);
            a44.addElement((Object)a69);
            java.awt.Point a70 = a10.getPosition();
            java.awt.Point a71 = new java.awt.Point(a70);
            java.awt.Point a72 = a10.getPosition();
            int i5 = a72.x;
            java.awt.Point a73 = a.getPosition();
            int i6 = a73.y;
            java.awt.Point a74 = new java.awt.Point(i5, i6);
            SplayTree$MoveMoveable a75 = new SplayTree$MoveMoveable(this, (com.cim.AIA.Moveable)a10, a71, a74, false);
            a44.addElement((Object)a75);
            java.util.Vector a76 = this.tweenVectorVector;
            a76.addElement((Object)a44);
            SplayTreeDataNode dummy8 = (SplayTreeDataNode)a;
            SplayTreeDataNode a77 = (SplayTreeDataNode)a;
            SplayTreeNode a78 = a10.getRight();
            a77.setLeft(a78);
            a10.setRight(a);
            a10.setParent(a7);
            a0 = a10;
        }
        return a0;
    }
    
    private SplayTreeDataNode rotateLeft(SplayTreeNode a)
    {
        SplayTreeDataNode a0 = null;
        SplayTreeDataNode dummy = (SplayTreeDataNode)a;
        SplayTreeDataNode a1 = (SplayTreeDataNode)a;
        SplayTreeNode a2 = a1.getRight();
        label1: {
            label0: {
                int i = a2 instanceof SplayTreeNullNode?1:0;
                if(i == 0)
                {
                    break label0;
                }
                SplayTreeDataNode dummy0 = (SplayTreeDataNode)a;
                SplayTreeDataNode a3 = (SplayTreeDataNode)a;
                a0 = a3;
                break label1;
            }
            java.util.Vector a4 = this.tweenVectorVector;
            if(a4 == null)
            {
                java.util.Vector a5 = new java.util.Vector();
                this.tweenVectorVector = a5;
            }
            java.util.Vector a6 = new java.util.Vector();
            SplayTreeDataNode a7 = a.getParent();
            SplayTreeDataNode dummy1 = (SplayTreeDataNode)a;
            SplayTreeDataNode a8 = (SplayTreeDataNode)a;
            SplayTreeNode a9 = a8.getRight();
            SplayTreeDataNode dummy2 = (SplayTreeDataNode)a9;
            SplayTreeDataNode a10 = (SplayTreeDataNode)a9;
            label2: {
                if(a7 == null)
                {
                    break label2;
                }
                SplayTreeNode a11 = a7.getLeft();
                if(a11 != a)
                {
                    com.cim.AIA.Line a12 = a7.getRightLink();
                    java.awt.Point a13 = a.getPosition();
                    java.awt.Point a14 = new java.awt.Point(a13);
                    java.awt.Point a15 = a10.getPosition();
                    java.awt.Point a16 = new java.awt.Point(a15);
                    SplayTree$MoveMoveable a17 = new SplayTree$MoveMoveable(this, (com.cim.AIA.Moveable)a12, a14, a16, false);
                    a6.addElement((Object)a17);
                }
                else
                {
                    com.cim.AIA.Line a18 = a7.getLeftLink();
                    java.awt.Point a19 = a.getPosition();
                    java.awt.Point a20 = new java.awt.Point(a19);
                    java.awt.Point a21 = a10.getPosition();
                    java.awt.Point a22 = new java.awt.Point(a21);
                    SplayTree$MoveMoveable a23 = new SplayTree$MoveMoveable(this, (com.cim.AIA.Moveable)a18, a20, a22, false);
                    a6.addElement((Object)a23);
                }
            }
            SplayTreeDataNode dummy3 = (SplayTreeDataNode)a;
            SplayTreeDataNode a24 = (SplayTreeDataNode)a;
            com.cim.AIA.Line a25 = a24.getRightLink();
            SplayTreeDataNode dummy4 = (SplayTreeDataNode)a;
            SplayTreeDataNode a26 = (SplayTreeDataNode)a;
            SplayTreeNode a27 = a26.getRight();
            java.awt.Point a28 = a27.getPosition();
            java.awt.Point a29 = new java.awt.Point(a28);
            SplayTreeNode a30 = a10.getLeft();
            java.awt.Point a31 = a30.getPosition();
            java.awt.Point a32 = new java.awt.Point(a31);
            SplayTree$MoveMoveable a33 = new SplayTree$MoveMoveable(this, (com.cim.AIA.Moveable)a25, a29, a32, false);
            a6.addElement((Object)a33);
            com.cim.AIA.Line a34 = a10.getLeftLink();
            SplayTreeNode a35 = a10.getLeft();
            java.awt.Point a36 = a35.getPosition();
            java.awt.Point a37 = new java.awt.Point(a36);
            java.awt.Point a38 = a.getPosition();
            java.awt.Point a39 = new java.awt.Point(a38);
            SplayTree$MoveMoveable a40 = new SplayTree$MoveMoveable(this, (com.cim.AIA.Moveable)a34, a37, a39, false);
            a6.addElement((Object)a40);
            java.util.Vector a41 = this.tweenVectorVector;
            a41.addElement((Object)a6);
            int i0 = this.isTweenFast?1:0;
            this.animate("$This key doesn't exist###");
            this.isTweenFast = i0 != 0;
            java.util.Vector a42 = this.tweenVectorVector;
            if(a42 == null)
            {
                java.util.Vector a43 = new java.util.Vector();
                this.tweenVectorVector = a43;
            }
            java.util.Vector a44 = new java.util.Vector();
            java.awt.Point a45 = a.getPosition();
            java.awt.Point a46 = new java.awt.Point(a45);
            java.awt.Point a47 = a.getPosition();
            int i1 = a47.x;
            SplayTreeDataNode dummy5 = (SplayTreeDataNode)a;
            SplayTreeDataNode a48 = (SplayTreeDataNode)a;
            SplayTreeNode a49 = a48.getRight();
            java.awt.Point a50 = a49.getPosition();
            int i2 = a50.y;
            java.awt.Point a51 = new java.awt.Point(i1, i2);
            SplayTree$MoveMoveable a52 = new SplayTree$MoveMoveable(this, (com.cim.AIA.Moveable)a, a46, a51, false);
            a44.addElement((Object)a52);
            SplayTreeDataNode dummy6 = (SplayTreeDataNode)a;
            SplayTreeDataNode a53 = (SplayTreeDataNode)a;
            SplayTreeNode a54 = a53.getLeft();
            java.awt.Point a55 = a54.getPosition();
            java.awt.Point a56 = new java.awt.Point(a55);
            java.awt.Point a57 = a54.getPosition();
            SplayTree$MoveMoveable a58 = new SplayTree$MoveMoveable(this, (com.cim.AIA.Moveable)a54, a56, a57, true);
            a44.addElement((Object)a58);
            SplayTreeNode a59 = a10.getRight();
            SplayTreeNode a60 = a10.getRight();
            java.awt.Point a61 = a60.getPosition();
            java.awt.Point a62 = new java.awt.Point(a61);
            SplayTreeNode a63 = a10.getRight();
            java.awt.Point a64 = a63.getPosition();
            int i3 = a64.x;
            SplayTreeDataNode dummy7 = (SplayTreeDataNode)a;
            SplayTreeDataNode a65 = (SplayTreeDataNode)a;
            SplayTreeNode a66 = a65.getRight();
            java.awt.Point a67 = a66.getPosition();
            int i4 = a67.y;
            java.awt.Point a68 = new java.awt.Point(i3, i4);
            SplayTree$MoveMoveable a69 = new SplayTree$MoveMoveable(this, (com.cim.AIA.Moveable)a59, a62, a68, true);
            a44.addElement((Object)a69);
            java.awt.Point a70 = a10.getPosition();
            java.awt.Point a71 = new java.awt.Point(a70);
            java.awt.Point a72 = a10.getPosition();
            int i5 = a72.x;
            java.awt.Point a73 = a.getPosition();
            int i6 = a73.y;
            java.awt.Point a74 = new java.awt.Point(i5, i6);
            SplayTree$MoveMoveable a75 = new SplayTree$MoveMoveable(this, (com.cim.AIA.Moveable)a10, a71, a74, false);
            a44.addElement((Object)a75);
            java.util.Vector a76 = this.tweenVectorVector;
            a76.addElement((Object)a44);
            SplayTreeDataNode dummy8 = (SplayTreeDataNode)a;
            SplayTreeDataNode a77 = (SplayTreeDataNode)a;
            SplayTreeNode a78 = a10.getLeft();
            a77.setRight(a78);
            a10.setLeft(a);
            a10.setParent(a7);
            a0 = a10;
        }
        return a0;
    }
    
    private SplayTreeNode insert(SplayTreeNode a, SplayTreeDataItem a0, Boolean a1)
    {
        this.switchPath = null;
        this.currentNode = a;
        SplayTreeCodeStack a2 = this.codeStack;
        a2.push("insertCode");
        this.setPosition("3");
        if(a == null)
        {
            RuntimeException a3 = new RuntimeException("insert: Node should never be null");
            throw a3;
        }
        SplayTreeDataNode a4 = a.getParent();
        this.setPosition("3.1");
        label13: {
            SplayTreeDataNode a5 = null;
            label1: {
                label0: {
                    int i = a instanceof SplayTreeNullNode?1:0;
                    if(i == 0)
                    {
                        break label0;
                    }
                    SplayTreeDataNode a6 = new SplayTreeDataNode(a0);
                    this.linkEarly(a4, (SplayTreeNode)a6, a1);
                    this.currentNode = a6;
                    SplayTreeCodeStack a7 = this.codeStack;
                    a7.stepCodeLine();
                    this.setPosition("3.1.1");
                    this.switchPath = null;
                    SplayTreeCodeStack a8 = this.codeStack;
                    a8.pop();
                    a5 = a6;
                    break label1;
                }
                SplayTreeDataNode dummy = (SplayTreeDataNode)a;
                SplayTreeDataNode a9 = (SplayTreeDataNode)a;
                int i0 = a0.getKey();
                int i1 = a9.getKey();
                if(i0 >= i1)
                {
                    SplayTreeNode a10 = a9.getRight();
                    this.switchPath = a10;
                }
                else
                {
                    SplayTreeNode a11 = a9.getLeft();
                    this.switchPath = a11;
                }
                SplayTreeNode a12 = this.switchPath;
                label2: {
                    int i2 = a12 instanceof SplayTreeDataNode?1:0;
                    if(i2 == 0)
                    {
                        break label2;
                    }
                    int i3 = a0.getKey();
                    SplayTreeNode a13 = this.switchPath;
                    SplayTreeDataNode dummy0 = (SplayTreeDataNode)a13;
                    SplayTreeDataNode a14 = (SplayTreeDataNode)a13;
                    int i4 = a14.getKey();
                    if(i3 >= i4)
                    {
                        SplayTreeNode a15 = this.switchPath;
                        SplayTreeDataNode dummy1 = (SplayTreeDataNode)a15;
                        SplayTreeDataNode a16 = (SplayTreeDataNode)a15;
                        SplayTreeNode a17 = a16.getRight();
                        this.switchPath = a17;
                    }
                    else
                    {
                        SplayTreeNode a18 = this.switchPath;
                        SplayTreeDataNode dummy2 = (SplayTreeDataNode)a18;
                        SplayTreeDataNode a19 = (SplayTreeDataNode)a18;
                        SplayTreeNode a20 = a19.getLeft();
                        this.switchPath = a20;
                    }
                }
                this.setPosition("3.2");
                SplayTreeNode a21 = a9.getLeft();
                label5: {
                    label4: {
                        label3: {
                            int i5 = a21 instanceof SplayTreeNullNode?1:0;
                            if(i5 == 0)
                            {
                                break label3;
                            }
                            int i6 = a0.getKey();
                            int i7 = a9.getKey();
                            if(i6 < i7)
                            {
                                break label4;
                            }
                        }
                        SplayTreeNode a22 = a9.getRight();
                        int i8 = a22 instanceof SplayTreeNullNode?1:0;
                        if(i8 == 0)
                        {
                            break label5;
                        }
                        int i9 = a0.getKey();
                        int i10 = a9.getKey();
                        if(i9 < i10)
                        {
                            break label5;
                        }
                    }
                    SplayTreeCodeStack a23 = this.codeStack;
                    a23.setCode("terminalCode");
                    this.setPosition("TERMINAL");
                    int i11 = a0.getKey();
                    int i12 = a9.getKey();
                    label6: {
                        if(i11 < i12)
                        {
                            break label6;
                        }
                        this.setPosition("TERMINAL.3");
                        this.setPosition("TERMINAL.3.1");
                        SplayTreeDataNode a24 = new SplayTreeDataNode(a0);
                        a9.setRight((SplayTreeNode)a24);
                        int i13 = this.isBackMode?1:0;
                        if(i13 == 0)
                        {
                            ((com.cim.AIA.Algorithm)this).repaint();
                        }
                        this.currentNode = a9;
                        SplayTreeCodeStack a25 = this.codeStack;
                        a25.stepCodeLine();
                        this.setPosition("TERMINAL.3.2");
                        int i14 = this.isExpanded("TERMINAL")?1:0;
                        int i15 = i14 != 0?0:1;
                        this.isTweenFast = i15 != 0;
                        SplayTreeDataNode a26 = this.rotateLeft((SplayTreeNode)a9);
                        this.currentNode = a26;
                        this.linkEarly(a4, (SplayTreeNode)a26, a1);
                        this.animate("$This key doesn't exist###");
                        SplayTreeCodeStack a27 = this.codeStack;
                        a27.stepCodeLine();
                        this.setPosition("TERMINAL.3.3");
                        this.algorithmSleep();
                        SplayTreeCodeStack a28 = this.codeStack;
                        a28.pop();
                        this.switchPath = null;
                        a5 = a26;
                        break label1;
                    }
                    this.setPosition("TERMINAL.3.1", "TERMINAL.1", false);
                    this.setPosition("TERMINAL.2.1");
                    SplayTreeDataNode a29 = new SplayTreeDataNode(a0);
                    a9.setLeft((SplayTreeNode)a29);
                    int i16 = this.isBackMode?1:0;
                    if(i16 == 0)
                    {
                        ((com.cim.AIA.Algorithm)this).repaint();
                    }
                    this.currentNode = a9;
                    SplayTreeCodeStack a30 = this.codeStack;
                    a30.stepCodeLine();
                    this.setPosition("TERMINAL.3.2", "TERMINAL.1", false);
                    this.setPosition("TERMINAL.2.2");
                    int i17 = this.isExpanded("TERMINAL")?1:0;
                    int i18 = i17 != 0?0:1;
                    this.isTweenFast = i18 != 0;
                    SplayTreeDataNode a31 = this.rotateRight((SplayTreeNode)a9);
                    this.currentNode = a31;
                    this.linkEarly(a4, (SplayTreeNode)a31, a1);
                    this.animate("$This key doesn't exist###");
                    SplayTreeCodeStack a32 = this.codeStack;
                    a32.stepCodeLine();
                    this.setPosition("TERMINAL.3.3", "TERMINAL.1", false);
                    this.setPosition("TERMINAL.2.3");
                    this.algorithmSleep();
                    SplayTreeCodeStack a33 = this.codeStack;
                    a33.pop();
                    this.switchPath = null;
                    a5 = a31;
                    break label1;
                }
                int i19 = a0.getKey();
                int i20 = a9.getKey();
                label9: {
                    label8: {
                        label7: {
                            if(i19 >= i20)
                            {
                                break label7;
                            }
                            int i21 = a0.getKey();
                            SplayTreeNode a34 = a9.getLeft();
                            SplayTreeDataNode dummy3 = (SplayTreeDataNode)a34;
                            SplayTreeDataNode a35 = (SplayTreeDataNode)a34;
                            int i22 = a35.getKey();
                            if(i21 < i22)
                            {
                                break label8;
                            }
                        }
                        int i23 = a0.getKey();
                        int i24 = a9.getKey();
                        if(i23 < i24)
                        {
                            break label9;
                        }
                        int i25 = a0.getKey();
                        SplayTreeNode a36 = a9.getRight();
                        SplayTreeDataNode dummy4 = (SplayTreeDataNode)a36;
                        SplayTreeDataNode a37 = (SplayTreeDataNode)a36;
                        int i26 = a37.getKey();
                        if(i25 < i26)
                        {
                            break label9;
                        }
                    }
                    SplayTreeCodeStack a38 = this.codeStack;
                    a38.setCode("zigZigCode");
                    this.setPosition("ZIGZIG");
                    this.setPosition("ZIGZIG.2");
                    int i27 = a0.getKey();
                    int i28 = a9.getKey();
                    label10: {
                        if(i27 < i28)
                        {
                            break label10;
                        }
                        this.setPosition("ZIGZIG.3");
                        SplayTreeNode a39 = a9.getRight();
                        SplayTreeDataNode dummy5 = (SplayTreeDataNode)a39;
                        SplayTreeDataNode a40 = (SplayTreeDataNode)a39;
                        this.setPosition("ZIGZIG.3.1");
                        SplayTreeNode a41 = a40.getRight();
                        Boolean a42 = new Boolean(false);
                        SplayTreeNode a43 = this.insert(a41, a0, a42);
                        a40.setRight(a43);
                        this.currentNode = a9;
                        SplayTreeCodeStack a44 = this.codeStack;
                        a44.stepCodeLine();
                        this.setPosition("ZIGZIG.3.2");
                        int i29 = this.isExpanded("ZIGZIG")?1:0;
                        int i30 = i29 != 0?0:1;
                        this.isTweenFast = i30 != 0;
                        SplayTreeDataNode a45 = this.rotateLeft((SplayTreeNode)a9);
                        this.currentNode = a45;
                        this.linkEarly(a4, (SplayTreeNode)a45, a1);
                        this.animate("$This key doesn't exist###");
                        SplayTreeCodeStack a46 = this.codeStack;
                        a46.stepCodeLine();
                        this.setPosition("ZIGZIG.3.3");
                        int i31 = this.isExpanded("ZIGZIG")?1:0;
                        int i32 = i31 != 0?0:1;
                        this.isTweenFast = i32 != 0;
                        SplayTreeDataNode a47 = this.rotateLeft((SplayTreeNode)a45);
                        this.currentNode = a47;
                        this.linkEarly(a4, (SplayTreeNode)a47, a1);
                        this.animate("$This key doesn't exist###");
                        SplayTreeCodeStack a48 = this.codeStack;
                        a48.stepCodeLine();
                        this.setPosition("ZIGZIG.3.4");
                        this.algorithmSleep();
                        SplayTreeCodeStack a49 = this.codeStack;
                        a49.pop();
                        this.switchPath = null;
                        a5 = a47;
                        break label1;
                    }
                    SplayTreeNode a50 = a9.getLeft();
                    SplayTreeDataNode dummy6 = (SplayTreeDataNode)a50;
                    SplayTreeDataNode a51 = (SplayTreeDataNode)a50;
                    this.setPosition("ZIGZIG.3.1", "ZIGZIG.1", false);
                    this.setPosition("ZIGZIG.2.1");
                    SplayTreeNode a52 = a51.getLeft();
                    Boolean a53 = new Boolean(true);
                    SplayTreeNode a54 = this.insert(a52, a0, a53);
                    a51.setLeft(a54);
                    this.currentNode = a9;
                    SplayTreeCodeStack a55 = this.codeStack;
                    a55.stepCodeLine();
                    this.setPosition("ZIGZIG.3.2", "ZIGZIG.1", false);
                    this.setPosition("ZIGZIG.2.2");
                    int i33 = this.isExpanded("ZIGZIG")?1:0;
                    int i34 = i33 != 0?0:1;
                    this.isTweenFast = i34 != 0;
                    SplayTreeDataNode a56 = this.rotateRight((SplayTreeNode)a9);
                    this.currentNode = a56;
                    this.linkEarly(a4, (SplayTreeNode)a56, a1);
                    this.animate("$This key doesn't exist###");
                    SplayTreeCodeStack a57 = this.codeStack;
                    a57.stepCodeLine();
                    this.setPosition("ZIGZIG.3.3", "ZIGZIG.1", false);
                    this.setPosition("ZIGZIG.2.3");
                    int i35 = this.isExpanded("ZIGZIG")?1:0;
                    int i36 = i35 != 0?0:1;
                    this.isTweenFast = i36 != 0;
                    SplayTreeDataNode a58 = this.rotateRight((SplayTreeNode)a56);
                    this.currentNode = a58;
                    this.linkEarly(a4, (SplayTreeNode)a58, a1);
                    this.animate("$This key doesn't exist###");
                    SplayTreeCodeStack a59 = this.codeStack;
                    a59.stepCodeLine();
                    this.setPosition("ZIGZIG.3.4", "ZIGZIG.1", false);
                    this.setPosition("ZIGZIG.2.4");
                    this.algorithmSleep();
                    SplayTreeCodeStack a60 = this.codeStack;
                    a60.pop();
                    this.switchPath = null;
                    a5 = a58;
                    break label1;
                }
                int i37 = a0.getKey();
                int i38 = a9.getKey();
                label12: {
                    label11: {
                        if(i37 >= i38)
                        {
                            break label11;
                        }
                        int i39 = a0.getKey();
                        SplayTreeNode a61 = a9.getLeft();
                        SplayTreeDataNode dummy7 = (SplayTreeDataNode)a61;
                        SplayTreeDataNode a62 = (SplayTreeDataNode)a61;
                        int i40 = a62.getKey();
                        if(i39 >= i40)
                        {
                            break label12;
                        }
                    }
                    int i41 = a0.getKey();
                    int i42 = a9.getKey();
                    if(i41 < i42)
                    {
                        break label13;
                    }
                    int i43 = a0.getKey();
                    SplayTreeNode a63 = a9.getRight();
                    SplayTreeDataNode dummy8 = (SplayTreeDataNode)a63;
                    SplayTreeDataNode a64 = (SplayTreeDataNode)a63;
                    int i44 = a64.getKey();
                    if(i43 >= i44)
                    {
                        break label13;
                    }
                }
                SplayTreeCodeStack a65 = this.codeStack;
                a65.setCode("zigZagCode");
                this.setPosition("ZIGZAG");
                this.setPosition("ZIGZAG.2");
                int i45 = a0.getKey();
                int i46 = a9.getKey();
                label14: {
                    if(i45 < i46)
                    {
                        break label14;
                    }
                    this.setPosition("ZIGZAG.3");
                    SplayTreeNode a66 = a9.getRight();
                    SplayTreeDataNode dummy9 = (SplayTreeDataNode)a66;
                    SplayTreeDataNode a67 = (SplayTreeDataNode)a66;
                    this.setPosition("ZIGZAG.3.1");
                    SplayTreeNode a68 = a67.getLeft();
                    Boolean a69 = new Boolean(true);
                    SplayTreeNode a70 = this.insert(a68, a0, a69);
                    a67.setLeft(a70);
                    this.currentNode = a9;
                    SplayTreeCodeStack a71 = this.codeStack;
                    a71.stepCodeLine();
                    this.setPosition("ZIGZAG.3.2");
                    int i47 = this.isExpanded("ZIGZAG")?1:0;
                    int i48 = i47 != 0?0:1;
                    this.isTweenFast = i48 != 0;
                    SplayTreeDataNode a72 = this.rotateRight((SplayTreeNode)a67);
                    a9.setRight((SplayTreeNode)a72);
                    this.animate("$This key doesn't exist###");
                    SplayTreeCodeStack a73 = this.codeStack;
                    a73.stepCodeLine();
                    this.setPosition("ZIGZAG.3.3");
                    int i49 = this.isExpanded("ZIGZAG")?1:0;
                    int i50 = i49 != 0?0:1;
                    this.isTweenFast = i50 != 0;
                    SplayTreeDataNode a74 = this.rotateLeft((SplayTreeNode)a9);
                    this.currentNode = a74;
                    this.linkEarly(a4, (SplayTreeNode)a74, a1);
                    this.animate("$This key doesn't exist###");
                    SplayTreeCodeStack a75 = this.codeStack;
                    a75.stepCodeLine();
                    this.setPosition("ZIGZAG.3.4");
                    this.algorithmSleep();
                    SplayTreeCodeStack a76 = this.codeStack;
                    a76.pop();
                    this.switchPath = null;
                    a5 = a74;
                    break label1;
                }
                SplayTreeNode a77 = a9.getLeft();
                SplayTreeDataNode dummy10 = (SplayTreeDataNode)a77;
                SplayTreeDataNode a78 = (SplayTreeDataNode)a77;
                this.setPosition("ZIGZAG.3.1", "ZIGZAG.1", false);
                this.setPosition("ZIGZAG.2.1");
                SplayTreeNode a79 = a78.getRight();
                Boolean a80 = new Boolean(false);
                SplayTreeNode a81 = this.insert(a79, a0, a80);
                a78.setRight(a81);
                this.currentNode = a9;
                SplayTreeCodeStack a82 = this.codeStack;
                a82.stepCodeLine();
                this.setPosition("ZIGZAG.3.2", "ZIGZAG.1", false);
                this.setPosition("ZIGZAG.2.2");
                int i51 = this.isExpanded("ZIGZAG")?1:0;
                int i52 = i51 != 0?0:1;
                this.isTweenFast = i52 != 0;
                SplayTreeDataNode a83 = this.rotateLeft((SplayTreeNode)a78);
                a9.setLeft((SplayTreeNode)a83);
                this.animate("$This key doesn't exist###");
                SplayTreeCodeStack a84 = this.codeStack;
                a84.stepCodeLine();
                this.setPosition("ZIGZAG.3.3", "ZIGZAG.1", false);
                this.setPosition("ZIGZAG.2.3");
                int i53 = this.isExpanded("ZIGZAG")?1:0;
                int i54 = i53 != 0?0:1;
                this.isTweenFast = i54 != 0;
                SplayTreeDataNode a85 = this.rotateRight((SplayTreeNode)a9);
                this.currentNode = a85;
                this.linkEarly(a4, (SplayTreeNode)a85, a1);
                this.animate("$This key doesn't exist###");
                SplayTreeCodeStack a86 = this.codeStack;
                a86.stepCodeLine();
                this.setPosition("ZIGZAG.3.4", "ZIGZAG.1", false);
                this.setPosition("ZIGZAG.2.4");
                this.algorithmSleep();
                SplayTreeCodeStack a87 = this.codeStack;
                a87.pop();
                this.switchPath = null;
                a5 = a85;
            }
            return a5;
        }
        RuntimeException a88 = new RuntimeException("This case should be unreachable");
        throw a88;
    }
    
    private SplayTreeNode search(SplayTreeNode a, SplayTreeDataItem a0, Boolean a1)
    {
        SplayTreeDataNode.clearNodeColors();
        this.currentNode = a;
        SplayTreeCodeStack a2 = this.codeStack;
        a2.push("searchCode");
        this.setPosition("5");
        if(a == null)
        {
            RuntimeException a3 = new RuntimeException("insert: Node should never be null");
            throw a3;
        }
        SplayTreeDataNode a4 = a.getParent();
        this.setPosition("5.1");
        label15: {
            SplayTreeNode a5 = null;
            label1: {
                label0: {
                    int i = a instanceof SplayTreeNullNode?1:0;
                    if(i == 0)
                    {
                        break label0;
                    }
                    Boolean a6 = new Boolean(false);
                    this.wasFound = a6;
                    this.setPosition("5.1.1");
                    SplayTreeCodeStack a7 = this.codeStack;
                    a7.stepCodeLine();
                    this.setPosition("5.1.2");
                    this.switchPath = null;
                    this.algorithmSleep();
                    SplayTreeCodeStack a8 = this.codeStack;
                    a8.pop();
                    a5 = a;
                    break label1;
                }
                SplayTreeDataNode dummy = (SplayTreeDataNode)a;
                SplayTreeDataNode a9 = (SplayTreeDataNode)a;
                this.setPosition("Found.1");
                int i0 = a9.getKey();
                int i1 = a0.getKey();
                label2: {
                    if(i0 != i1)
                    {
                        break label2;
                    }
                    SplayTreeCodeStack a10 = this.codeStack;
                    a10.setCode("found");
                    Boolean a11 = new Boolean(true);
                    this.wasFound = a11;
                    a9.setIsFound();
                    SplayTreeCodeStack a12 = this.codeStack;
                    a12.stepCodeLine();
                    this.setPosition("Found.1.1");
                    SplayTreeCodeStack a13 = this.codeStack;
                    a13.stepCodeLine();
                    this.setPosition("Found.1.2");
                    this.switchPath = null;
                    this.algorithmSleep();
                    SplayTreeCodeStack a14 = this.codeStack;
                    a14.pop();
                    a5 = a9;
                    break label1;
                }
                this.setPosition("Found.2");
                SplayTreeNode a15 = a9.getLeft();
                label3: {
                    int i2 = a15 instanceof SplayTreeDataNode?1:0;
                    if(i2 == 0)
                    {
                        break label3;
                    }
                    int i3 = a0.getKey();
                    SplayTreeNode a16 = a9.getLeft();
                    SplayTreeDataNode dummy0 = (SplayTreeDataNode)a16;
                    SplayTreeDataNode a17 = (SplayTreeDataNode)a16;
                    int i4 = a17.getKey();
                    if(i3 != i4)
                    {
                        break label3;
                    }
                    SplayTreeCodeStack a18 = this.codeStack;
                    a18.setCode("found rotate");
                    SplayTreeCodeStack a19 = this.codeStack;
                    a19.stepCodeLine();
                    this.setPosition("Found.2.1");
                    Boolean a20 = new Boolean(true);
                    this.wasFound = a20;
                    SplayTreeNode a21 = a9.getLeft();
                    SplayTreeDataNode dummy1 = (SplayTreeDataNode)a21;
                    SplayTreeDataNode a22 = (SplayTreeDataNode)a21;
                    a22.setIsFound();
                    SplayTreeCodeStack a23 = this.codeStack;
                    a23.stepCodeLine();
                    this.setPosition("Found.2.2");
                    int i5 = this.isExpanded("Success")?1:0;
                    int i6 = i5 != 0?0:1;
                    this.isTweenFast = i6 != 0;
                    SplayTreeDataNode a24 = this.rotateRight((SplayTreeNode)a9);
                    this.linkEarly(a4, (SplayTreeNode)a24, a1);
                    this.animate("$This key doesn't exist###");
                    this.currentNode = a24;
                    this.switchPath = null;
                    SplayTreeCodeStack a25 = this.codeStack;
                    a25.stepCodeLine();
                    this.setPosition("Found.2.3");
                    this.algorithmSleep();
                    SplayTreeCodeStack a26 = this.codeStack;
                    a26.pop();
                    a5 = a24;
                    break label1;
                }
                SplayTreeNode a27 = a9.getRight();
                label4: {
                    int i7 = a27 instanceof SplayTreeDataNode?1:0;
                    if(i7 == 0)
                    {
                        break label4;
                    }
                    int i8 = a0.getKey();
                    SplayTreeNode a28 = a9.getRight();
                    SplayTreeDataNode dummy2 = (SplayTreeDataNode)a28;
                    SplayTreeDataNode a29 = (SplayTreeDataNode)a28;
                    int i9 = a29.getKey();
                    if(i8 != i9)
                    {
                        break label4;
                    }
                    SplayTreeCodeStack a30 = this.codeStack;
                    a30.setCode("found rotate");
                    SplayTreeCodeStack a31 = this.codeStack;
                    a31.stepCodeLine();
                    this.setPosition("Found.2.1");
                    Boolean a32 = new Boolean(true);
                    this.wasFound = a32;
                    SplayTreeNode a33 = a9.getRight();
                    SplayTreeDataNode dummy3 = (SplayTreeDataNode)a33;
                    SplayTreeDataNode a34 = (SplayTreeDataNode)a33;
                    a34.setIsFound();
                    SplayTreeCodeStack a35 = this.codeStack;
                    a35.stepCodeLine();
                    this.setPosition("Found.2.2");
                    int i10 = this.isExpanded("Success")?1:0;
                    int i11 = i10 != 0?0:1;
                    this.isTweenFast = i11 != 0;
                    SplayTreeDataNode a36 = this.rotateLeft((SplayTreeNode)a9);
                    this.linkEarly(a4, (SplayTreeNode)a36, a1);
                    this.animate("$This key doesn't exist###");
                    this.currentNode = a36;
                    this.switchPath = null;
                    SplayTreeCodeStack a37 = this.codeStack;
                    a37.stepCodeLine();
                    this.setPosition("Found.2.3");
                    this.algorithmSleep();
                    SplayTreeCodeStack a38 = this.codeStack;
                    a38.pop();
                    a5 = a36;
                    break label1;
                }
                int i12 = a0.getKey();
                int i13 = a9.getKey();
                if(i12 >= i13)
                {
                    SplayTreeNode a39 = a9.getRight();
                    this.switchPath = a39;
                }
                else
                {
                    SplayTreeNode a40 = a9.getLeft();
                    this.switchPath = a40;
                }
                SplayTreeNode a41 = this.switchPath;
                label5: {
                    int i14 = a41 instanceof SplayTreeDataNode?1:0;
                    if(i14 == 0)
                    {
                        break label5;
                    }
                    int i15 = a0.getKey();
                    SplayTreeNode a42 = this.switchPath;
                    SplayTreeDataNode dummy4 = (SplayTreeDataNode)a42;
                    SplayTreeDataNode a43 = (SplayTreeDataNode)a42;
                    int i16 = a43.getKey();
                    if(i15 >= i16)
                    {
                        SplayTreeNode a44 = this.switchPath;
                        SplayTreeDataNode dummy5 = (SplayTreeDataNode)a44;
                        SplayTreeDataNode a45 = (SplayTreeDataNode)a44;
                        SplayTreeNode a46 = a45.getRight();
                        this.switchPath = a46;
                    }
                    else
                    {
                        SplayTreeNode a47 = this.switchPath;
                        SplayTreeDataNode dummy6 = (SplayTreeDataNode)a47;
                        SplayTreeDataNode a48 = (SplayTreeDataNode)a47;
                        SplayTreeNode a49 = a48.getLeft();
                        this.switchPath = a49;
                    }
                }
                this.setPosition("5.2");
                SplayTreeNode a50 = a9.getLeft();
                label8: {
                    label7: {
                        label6: {
                            int i17 = a50 instanceof SplayTreeNullNode?1:0;
                            if(i17 == 0)
                            {
                                break label6;
                            }
                            int i18 = a0.getKey();
                            int i19 = a9.getKey();
                            if(i18 < i19)
                            {
                                break label7;
                            }
                        }
                        SplayTreeNode a51 = a9.getRight();
                        int i20 = a51 instanceof SplayTreeNullNode?1:0;
                        if(i20 == 0)
                        {
                            break label8;
                        }
                        int i21 = a0.getKey();
                        int i22 = a9.getKey();
                        if(i21 <= i22)
                        {
                            break label8;
                        }
                    }
                    this.setPosition("TERMINAL");
                    Boolean a52 = new Boolean(false);
                    this.wasFound = a52;
                    this.setPosition("TERMINAL.1");
                    this.setPosition("TERMINAL.2");
                    this.switchPath = null;
                    this.algorithmSleep();
                    SplayTreeCodeStack a53 = this.codeStack;
                    a53.pop();
                    a5 = a9;
                    break label1;
                }
                int i23 = a0.getKey();
                int i24 = a9.getKey();
                label11: {
                    label10: {
                        label9: {
                            if(i23 >= i24)
                            {
                                break label9;
                            }
                            int i25 = a0.getKey();
                            SplayTreeNode a54 = a9.getLeft();
                            SplayTreeDataNode dummy7 = (SplayTreeDataNode)a54;
                            SplayTreeDataNode a55 = (SplayTreeDataNode)a54;
                            int i26 = a55.getKey();
                            if(i25 < i26)
                            {
                                break label10;
                            }
                        }
                        int i27 = a0.getKey();
                        int i28 = a9.getKey();
                        if(i27 < i28)
                        {
                            break label11;
                        }
                        int i29 = a0.getKey();
                        SplayTreeNode a56 = a9.getRight();
                        SplayTreeDataNode dummy8 = (SplayTreeDataNode)a56;
                        SplayTreeDataNode a57 = (SplayTreeDataNode)a56;
                        int i30 = a57.getKey();
                        if(i29 < i30)
                        {
                            break label11;
                        }
                    }
                    SplayTreeCodeStack a58 = this.codeStack;
                    a58.setCode("zigZigSearchCode");
                    this.setPosition("ZIGZIG");
                    this.setPosition("ZIGZIG.2");
                    int i31 = a0.getKey();
                    int i32 = a9.getKey();
                    label12: {
                        if(i31 < i32)
                        {
                            break label12;
                        }
                        this.setPosition("ZIGZIG.3");
                        SplayTreeNode a59 = a9.getRight();
                        SplayTreeDataNode dummy9 = (SplayTreeDataNode)a59;
                        SplayTreeDataNode a60 = (SplayTreeDataNode)a59;
                        this.setPosition("ZIGZIG.3.1");
                        SplayTreeNode a61 = a60.getRight();
                        Boolean a62 = new Boolean(false);
                        SplayTreeNode a63 = this.search(a61, a0, a62);
                        a60.setRight(a63);
                        this.currentNode = a9;
                        SplayTreeCodeStack a64 = this.codeStack;
                        a64.stepCodeLine();
                        this.setPosition("ZIGZIG.3.2");
                        int i33 = this.isExpanded("ZIGZIG")?1:0;
                        int i34 = i33 != 0?0:1;
                        this.isTweenFast = i34 != 0;
                        SplayTreeDataNode a65 = this.rotateLeft((SplayTreeNode)a9);
                        this.currentNode = a65;
                        this.linkEarly(a4, (SplayTreeNode)a65, a1);
                        this.animate("$This key doesn't exist###");
                        SplayTreeCodeStack a66 = this.codeStack;
                        a66.stepCodeLine();
                        this.setPosition("ZIGZIG.3.3");
                        int i35 = this.isExpanded("ZIGZIG")?1:0;
                        int i36 = i35 != 0?0:1;
                        this.isTweenFast = i36 != 0;
                        SplayTreeDataNode a67 = this.rotateLeft((SplayTreeNode)a65);
                        this.currentNode = a67;
                        this.linkEarly(a4, (SplayTreeNode)a67, a1);
                        this.animate("$This key doesn't exist###");
                        SplayTreeCodeStack a68 = this.codeStack;
                        a68.stepCodeLine();
                        this.setPosition("ZIGZIG.3.4");
                        this.algorithmSleep();
                        SplayTreeCodeStack a69 = this.codeStack;
                        a69.pop();
                        a5 = a67;
                        break label1;
                    }
                    SplayTreeNode a70 = a9.getLeft();
                    SplayTreeDataNode dummy10 = (SplayTreeDataNode)a70;
                    SplayTreeDataNode a71 = (SplayTreeDataNode)a70;
                    this.setPosition("ZIGZIG.3.1", "ZIGZIG.1", false);
                    this.setPosition("ZIGZIG.2.1");
                    SplayTreeNode a72 = a71.getLeft();
                    Boolean a73 = new Boolean(true);
                    SplayTreeNode a74 = this.search(a72, a0, a73);
                    a71.setLeft(a74);
                    this.currentNode = a9;
                    SplayTreeCodeStack a75 = this.codeStack;
                    a75.stepCodeLine();
                    this.setPosition("ZIGZIG.3.2", "ZIGZIG.1", false);
                    this.setPosition("ZIGZIG.2.2");
                    int i37 = this.isExpanded("ZIGZIG")?1:0;
                    int i38 = i37 != 0?0:1;
                    this.isTweenFast = i38 != 0;
                    SplayTreeDataNode a76 = this.rotateRight((SplayTreeNode)a9);
                    this.currentNode = a76;
                    this.linkEarly(a4, (SplayTreeNode)a76, a1);
                    this.animate("$This key doesn't exist###");
                    SplayTreeCodeStack a77 = this.codeStack;
                    a77.stepCodeLine();
                    this.setPosition("ZIGZIG.3.3", "ZIGZIG.1", false);
                    this.setPosition("ZIGZIG.2.3");
                    int i39 = this.isExpanded("ZIGZIG")?1:0;
                    int i40 = i39 != 0?0:1;
                    this.isTweenFast = i40 != 0;
                    SplayTreeDataNode a78 = this.rotateRight((SplayTreeNode)a76);
                    this.currentNode = a78;
                    this.linkEarly(a4, (SplayTreeNode)a78, a1);
                    this.animate("$This key doesn't exist###");
                    SplayTreeCodeStack a79 = this.codeStack;
                    a79.stepCodeLine();
                    this.setPosition("ZIGZIG.3.4", "ZIGZIG.1", false);
                    this.setPosition("ZIGZIG.2.4");
                    this.algorithmSleep();
                    SplayTreeCodeStack a80 = this.codeStack;
                    a80.pop();
                    a5 = a78;
                    break label1;
                }
                int i41 = a0.getKey();
                int i42 = a9.getKey();
                label14: {
                    label13: {
                        if(i41 >= i42)
                        {
                            break label13;
                        }
                        int i43 = a0.getKey();
                        SplayTreeNode a81 = a9.getLeft();
                        SplayTreeDataNode dummy11 = (SplayTreeDataNode)a81;
                        SplayTreeDataNode a82 = (SplayTreeDataNode)a81;
                        int i44 = a82.getKey();
                        if(i43 >= i44)
                        {
                            break label14;
                        }
                    }
                    int i45 = a0.getKey();
                    int i46 = a9.getKey();
                    if(i45 < i46)
                    {
                        break label15;
                    }
                    int i47 = a0.getKey();
                    SplayTreeNode a83 = a9.getRight();
                    SplayTreeDataNode dummy12 = (SplayTreeDataNode)a83;
                    SplayTreeDataNode a84 = (SplayTreeDataNode)a83;
                    int i48 = a84.getKey();
                    if(i47 >= i48)
                    {
                        break label15;
                    }
                }
                SplayTreeCodeStack a85 = this.codeStack;
                a85.setCode("zigZagSearchCode");
                this.setPosition("ZIGZAG");
                this.setPosition("ZIGZAG.2");
                int i49 = a0.getKey();
                int i50 = a9.getKey();
                label16: {
                    if(i49 < i50)
                    {
                        break label16;
                    }
                    this.setPosition("ZIGZAG.3");
                    SplayTreeNode a86 = a9.getRight();
                    SplayTreeDataNode dummy13 = (SplayTreeDataNode)a86;
                    SplayTreeDataNode a87 = (SplayTreeDataNode)a86;
                    this.setPosition("ZIGZAG.3.1");
                    SplayTreeNode a88 = a87.getLeft();
                    Boolean a89 = new Boolean(true);
                    SplayTreeNode a90 = this.search(a88, a0, a89);
                    a87.setLeft(a90);
                    this.currentNode = a9;
                    SplayTreeCodeStack a91 = this.codeStack;
                    a91.stepCodeLine();
                    this.setPosition("ZIGZAG.3.2");
                    int i51 = this.isExpanded("ZIGZAG")?1:0;
                    int i52 = i51 != 0?0:1;
                    this.isTweenFast = i52 != 0;
                    SplayTreeDataNode a92 = this.rotateRight((SplayTreeNode)a87);
                    a9.setRight((SplayTreeNode)a92);
                    this.animate("$This key doesn't exist###");
                    SplayTreeCodeStack a93 = this.codeStack;
                    a93.stepCodeLine();
                    this.setPosition("ZIGZAG.3.3");
                    int i53 = this.isExpanded("ZIGZAG")?1:0;
                    int i54 = i53 != 0?0:1;
                    this.isTweenFast = i54 != 0;
                    SplayTreeDataNode a94 = this.rotateLeft((SplayTreeNode)a9);
                    this.currentNode = a94;
                    this.linkEarly(a4, (SplayTreeNode)a94, a1);
                    this.animate("$This key doesn't exist###");
                    SplayTreeCodeStack a95 = this.codeStack;
                    a95.stepCodeLine();
                    this.setPosition("ZIGZAG.3.4");
                    this.algorithmSleep();
                    SplayTreeCodeStack a96 = this.codeStack;
                    a96.pop();
                    a5 = a94;
                    break label1;
                }
                SplayTreeNode a97 = a9.getLeft();
                SplayTreeDataNode dummy14 = (SplayTreeDataNode)a97;
                SplayTreeDataNode a98 = (SplayTreeDataNode)a97;
                this.setPosition("ZIGZAG.3.1", "ZIGZAG.1", false);
                this.setPosition("ZIGZAG.2.1");
                SplayTreeNode a99 = a98.getRight();
                Boolean a100 = new Boolean(false);
                SplayTreeNode a101 = this.search(a99, a0, a100);
                a98.setRight(a101);
                this.currentNode = a9;
                SplayTreeCodeStack a102 = this.codeStack;
                a102.stepCodeLine();
                this.setPosition("ZIGZAG.3.2", "ZIGZAG.1", false);
                this.setPosition("ZIGZAG.2.2");
                int i55 = this.isExpanded("ZIGZAG")?1:0;
                int i56 = i55 != 0?0:1;
                this.isTweenFast = i56 != 0;
                SplayTreeDataNode a103 = this.rotateLeft((SplayTreeNode)a98);
                a9.setLeft((SplayTreeNode)a103);
                this.animate("$This key doesn't exist###");
                SplayTreeCodeStack a104 = this.codeStack;
                a104.stepCodeLine();
                this.setPosition("ZIGZAG.3.3", "ZIGZAG.1", false);
                this.setPosition("ZIGZAG.2.3");
                int i57 = this.isExpanded("ZIGZAG")?1:0;
                int i58 = i57 != 0?0:1;
                this.isTweenFast = i58 != 0;
                SplayTreeDataNode a105 = this.rotateRight((SplayTreeNode)a9);
                this.currentNode = a105;
                this.linkEarly(a4, (SplayTreeNode)a105, a1);
                this.animate("$This key doesn't exist###");
                SplayTreeCodeStack a106 = this.codeStack;
                a106.stepCodeLine();
                this.setPosition("ZIGZAG.3.4", "ZIGZAG.1", false);
                this.setPosition("ZIGZAG.2.4");
                this.algorithmSleep();
                SplayTreeCodeStack a107 = this.codeStack;
                a107.pop();
                a5 = a105;
            }
            return a5;
        }
        RuntimeException a108 = new RuntimeException("This case should be unreachable");
        throw a108;
    }
    
    private void linkEarly(SplayTreeDataNode a, SplayTreeNode a0, Boolean a1)
    {
        label1: {
            label0: {
                if(a1 != null)
                {
                    break label0;
                }
                this.head = a0;
                break label1;
            }
            int i = a1.booleanValue()?1:0;
            if(i == 0)
            {
                a.setRight(a0);
            }
            else
            {
                a.setLeft(a0);
            }
        }
    }
    
    private void printTree(SplayTreeNode a)
    {
        int i = a instanceof SplayTreeDataNode?1:0;
        if(i != 0)
        {
            SplayTreeDataNode dummy = (SplayTreeDataNode)a;
            SplayTreeDataNode a0 = (SplayTreeDataNode)a;
            SplayTreeNode a1 = a0.getLeft();
            this.printTree(a1);
            java.io.PrintStream a2 = System.out;
            SplayTreeDataNode dummy0 = (SplayTreeDataNode)a;
            SplayTreeDataNode a3 = (SplayTreeDataNode)a;
            int i0 = a3.getKey();
            a2.println(i0);
            SplayTreeDataNode dummy1 = (SplayTreeDataNode)a;
            SplayTreeDataNode a4 = (SplayTreeDataNode)a;
            SplayTreeNode a5 = a4.getRight();
            this.printTree(a5);
        }
    }
    
    private void setPosition(String s, String s0, boolean b)
    {
        int i = this.isExpanded(s0)?1:0;
        int i0 = b?1:0;
        if(i != i0)
        {
            this.setPosition("This key does not exist");
        }
        else
        {
            this.setPosition(s);
        }
    }
    
    private boolean isExpanded(String s)
    {
        com.cim.AIA.AlgorithmThread a = this.algorithmThread;
        SplayTreeThread dummy = (SplayTreeThread)a;
        SplayTreeThread a0 = (SplayTreeThread)a;
        com.cim.AIA.AlgorithmWindow a1 = a0.getAlgorithmWindow();
        int i = a1.isExpanded(s)?1:0;
        return i != 0;
    }
    
    public boolean isTweenFast()
    {
        int i = this.isTweenFast?1:0;
        return i != 0;
    }
    
    private void animate(String s)
    {
        this.setPosition(s);
        int i = this.isBackMode?1:0;
        if(i != 0)
        {
            this.tweenVectorVector = null;
        }
        else
        {
            ((com.cim.AIA.Algorithm)this).repaint();
        }
        this.isTweenFast = false;
    }
    
    private void clearPointers()
    {
        this.currentNode = null;
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
    
    private void algorithmSleep()
    {
        int i = this.isBackMode?1:0;
        label0: {
            if(i != 0)
            {
                break label0;
            }
            ((com.cim.AIA.Algorithm)this).repaint();
            try
            {
                com.cim.AIA.AlgorithmThread a = this.algorithmThread;
                com.cim.AIA.AlgorithmThread a0 = this.algorithmThread;
                SplayTreeThread dummy = (SplayTreeThread)a0;
                SplayTreeThread a1 = (SplayTreeThread)a0;
                int i0 = a1.getRunSleepDuration();
                int i1 = i0 / 3;
                long j = (long)i1;
                com.cim.AIA.AlgorithmThread.sleep(j);
            }
            catch(Exception ignoredException)
            {
            }
        }
    }
    
    static
    {
        String s = localization.Messages.getString("SplayTree.0");
        SplayTree.BUILD_MODE_LABEL = s;
        String s0 = localization.Messages.getString("SplayTree.1");
        SplayTree.SEARCH_MODE_LABEL = s0;
    }
}