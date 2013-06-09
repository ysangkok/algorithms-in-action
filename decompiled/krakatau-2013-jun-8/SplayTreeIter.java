public class SplayTreeIter extends com.cim.AIA.Algorithm implements com.cim.AIA.MethodSelectionListener, com.cim.AIA.ControlListener {
    final private static String BUILD_MODE_LABEL;
    final private static String SEARCH_MODE_LABEL;
    final private static int BUILD_MODE = 11;
    final private static int SEARCH_MODE = 12;
    private int nextMode;
    private int executingMode;
    final private static int DEFAULT_ELEMENT_WIDTH = 20;
    private SplayTreeIterNode currentNode;
    private SplayTreeIterNode parentNode;
    private SplayTreeIterNode switchPath;
    private java.util.Vector tweenVectorVector;
    private boolean isTweenFast;
    private boolean isBackMode;
    private SplayTreeIterCodeStack codeStack;
    private Boolean wasFound;
    private int currentLevel;
    private boolean noMoveTween;
    private boolean drawSwitch;
    private boolean drawParentSwitch;
    private boolean drawPointers;
    private SplayTreeIterNode head;
    private SplayTreeIterNode savedTree;
    private SplayTreeIterNode startingTree;
    private com.cim.AIA.ElementArray insertData;
    private com.cim.AIA.ElementArray newInsertData;
    private com.cim.AIA.ElementArray searchData;
    private com.cim.AIA.ElementArray newSearchData;
    private int insertPos;
    private int lastInsertPos;
    
    public SplayTreeIter(com.cim.AIA.AlgorithmThread a, Object a0)
    {
        super(a, a0);
        this.isBackMode = false;
        this.currentLevel = -1;
        this.noMoveTween = false;
        this.drawSwitch = false;
        this.drawParentSwitch = false;
        this.drawPointers = true;
        SplayTreeIterNullNode a1 = new SplayTreeIterNullNode();
        this.head = a1;
        SplayTreeIterNullNode a2 = new SplayTreeIterNullNode();
        this.savedTree = a2;
        this.executingMode = 11;
        int i = this.executingMode;
        this.nextMode = i;
        this.changeData(a0);
        com.cim.AIA.ElementArray a3 = this.newInsertData;
        this.insertData = a3;
        com.cim.AIA.ElementArray a4 = this.newSearchData;
        this.searchData = a4;
        SplayTreeIterCodeStack a5 = new SplayTreeIterCodeStack();
        this.codeStack = a5;
    }
    
    public void reInitialise(Object a)
    {
        this.noMoveTween = true;
        this.drawPointers = true;
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
                SplayTreeIterNullNode a2 = new SplayTreeIterNullNode();
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
                SplayTreeIterNode a3 = this.savedTree;
                SplayTreeIterNode a4 = a3.getCopy();
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
                    SplayTreeIterNode a5 = this.startingTree;
                    SplayTreeIterNode a6 = a5.getCopy();
                    this.head = a6;
                    break label4;
                }
                int i6 = this.executingMode;
                if(i6 == 12)
                {
                    SplayTreeIterNode a7 = this.savedTree;
                    SplayTreeIterNode a8 = a7.getCopy();
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
                    java.awt.Color a4 = SplayTreeIterColors.INSERT_ACTIVE_COLOR;
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
                    java.awt.Color a10 = SplayTreeIterColors.SEARCH_ACTIVE_COLOR;
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
                    java.awt.Color a12 = SplayTreeIterColors.INSERT_ACTIVE_COLOR;
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
            java.awt.Color a27 = SplayTreeIterColors.SEARCH_ACTIVE_COLOR;
            a26.setBackgroundColor(a27);
            int i13 = i5 + 1;
            i5 = i13;
        }
    }
    
    private void save()
    {
        this.resetColors();
        int i = this.executingMode;
        label1: {
            label0: {
                if(i != 11)
                {
                    break label0;
                }
                SplayTreeIterNode a = this.head;
                SplayTreeIterNode a0 = a.getCopy();
                this.savedTree = a0;
                break label1;
            }
            int i0 = this.executingMode;
            if(i0 == 12)
            {
                SplayTreeIterNode a1 = this.savedTree;
                SplayTreeIterNode a2 = a1.getCopy();
                this.head = a2;
            }
        }
    }
    
    protected void storeState(boolean b)
    {
        this.save();
    }
    
    protected void clearState()
    {
        SplayTreeIterNullNode a = new SplayTreeIterNullNode();
        this.head = a;
    }
    
    protected void initialiseMethods(String s, com.cim.AIA.MethodSelectable a)
    {
        Object a0 = a;
        String s0 = SplayTreeIter.BUILD_MODE_LABEL;
        String s1 = localization.Messages.getString("SplayTreeIter.2");
        com.cim.AIA.Logger a1 = this.getLogger();
        com.cim.AIA.BreakPoint a2 = this.getBreakPoint();
        com.cim.AIA.MethodSelection a3 = new com.cim.AIA.MethodSelection(s0, s, s1, "3a", a1, a2);
        String s2 = SplayTreeIter.SEARCH_MODE_LABEL;
        String s3 = localization.Messages.getString("SplayTreeIter.4");
        com.cim.AIA.Logger a4 = this.getLogger();
        com.cim.AIA.BreakPoint a5 = this.getBreakPoint();
        com.cim.AIA.MethodSelection a6 = new com.cim.AIA.MethodSelection(s2, s, s3, "5a", a4, a5);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelection(a3, true);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelection(a6, false);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelectionListener((com.cim.AIA.MethodSelectionListener)this);
    }
    
    public void processMethodSelectionEvent(com.cim.AIA.MethodSelectionEvent a)
    {
        String s = SplayTreeIter.BUILD_MODE_LABEL;
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
            String s1 = SplayTreeIter.SEARCH_MODE_LABEL;
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
            SplayTreeIterNode a0 = this.savedTree;
            this.head = a0;
        }
        int i2 = this.nextMode;
        label2: {
            if(i2 != 12)
            {
                break label2;
            }
            SplayTreeIterNode a1 = this.savedTree;
            if(a1 != null)
            {
                SplayTreeIterNode a2 = this.savedTree;
                SplayTreeIterNode a3 = a2.getCopy();
                this.head = a3;
            }
        }
    }
    
    public com.cim.AIA.MultipleTween generateTweens(com.cim.AIA.Paintable a, Object a0, int i)
    {
        return null;
    }
    
    public SplayTreeIterCodeStack getCodeStack()
    {
        SplayTreeIterCodeStack a = this.codeStack;
        return a;
    }
    
    public java.util.Vector getTweens(com.cim.AIA.Paintable a, Object a0, int i)
    {
        java.util.Vector a1 = null;
        Object a2 = a;
        java.util.Vector a3 = new java.util.Vector();
        java.util.Vector a4 = this.tweenVectorVector;
        label0: {
            if(a4 == null)
            {
                break label0;
            }
            int i0 = 0;
            while(true)
            {
                java.util.Vector a5 = this.tweenVectorVector;
                int i1 = a5.size();
                if(i0 >= i1)
                {
                    break;
                }
                java.util.Vector a6 = this.tweenVectorVector;
                Object a7 = a6.elementAt(i0);
                java.util.Vector dummy = (java.util.Vector)a7;
                java.util.Vector a8 = (java.util.Vector)a7;
                label1: {
                    int i2 = 0;
                    if(a8 == null)
                    {
                        break label1;
                    }
                    com.cim.AIA.MultipleTween a9 = new com.cim.AIA.MultipleTween((com.cim.AIA.Paintable)a2);
                    Object a10 = a8.elementAt(0);
                    int i3 = a10 instanceof java.util.Hashtable?1:0;
                    if(i3 == 0)
                    {
                        i2 = 0;
                    }
                    else
                    {
                        Object a11 = a8.elementAt(0);
                        java.util.Hashtable dummy0 = (java.util.Hashtable)a11;
                        java.util.Hashtable a12 = (java.util.Hashtable)a11;
                        a3.addElement((Object)a12);
                        i2 = 1;
                    }
                    while(true)
                    {
                        int i4 = a8.size();
                        if(i2 >= i4)
                        {
                            break;
                        }
                        else
                        {
                            Object a13 = a8.elementAt(i2);
                            SplayTreeIter$MoveMoveable dummy1 = (SplayTreeIter$MoveMoveable)a13;
                            SplayTreeIter$MoveMoveable a14 = (SplayTreeIter$MoveMoveable)a13;
                            Object a15 = SplayTreeIter$MoveMoveable.access$000(a14);
                            Object a16 = a8.elementAt(i2);
                            SplayTreeIter$MoveMoveable dummy2 = (SplayTreeIter$MoveMoveable)a16;
                            SplayTreeIter$MoveMoveable a17 = (SplayTreeIter$MoveMoveable)a16;
                            java.awt.Point a18 = SplayTreeIter$MoveMoveable.access$100(a17);
                            Object a19 = a8.elementAt(i2);
                            SplayTreeIter$MoveMoveable dummy3 = (SplayTreeIter$MoveMoveable)a19;
                            SplayTreeIter$MoveMoveable a20 = (SplayTreeIter$MoveMoveable)a19;
                            java.awt.Point a21 = SplayTreeIter$MoveMoveable.access$200(a20);
                            Object a22 = a8.elementAt(i2);
                            SplayTreeIter$MoveMoveable dummy4 = (SplayTreeIter$MoveMoveable)a22;
                            SplayTreeIter$MoveMoveable a23 = (SplayTreeIter$MoveMoveable)a22;
                            int i5 = SplayTreeIter$MoveMoveable.access$300(a23)?1:0;
                            com.cim.AIA.MoveTween a24 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)a2, (com.cim.AIA.Moveable)a15, a18, a21, i5 != 0, i);
                            a9.add((com.cim.AIA.Tween)a24);
                            int i6 = i2 + 1;
                            i2 = i6;
                        }
                    }
                    int i7 = a9.getNumberOfTweens();
                    if(i7 > 0)
                    {
                        a3.addElement((Object)a9);
                    }
                }
                int i8 = i0 + 1;
                i0 = i8;
            }
        }
        this.tweenVectorVector = null;
        int i9 = this.isBackMode?1:0;
        label3: {
            label2: {
                if(i9 == 0)
                {
                    break label2;
                }
                a1 = null;
                break label3;
            }
            int i10 = a3.size();
            a1 = i10 <= 0?null:a3;
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
    
    public SplayTreeIterNode getHead()
    {
        SplayTreeIterNode a = this.head;
        this.unHighlightPath(a);
        SplayTreeIterNode a0 = this.switchPath;
        this.highlightSwitchPath(a0);
        SplayTreeIterNode a1 = this.currentNode;
        this.highlightPath(a1);
        int i = this.drawSwitch?1:0;
        label0: {
            if(i == 0)
            {
                break label0;
            }
            SplayTreeIterNode a2 = this.currentNode;
            if(a2 != null)
            {
                SplayTreeIterNode a3 = this.currentNode;
                a3.setIsOnPath(false);
                SplayTreeIterNode a4 = this.currentNode;
                a4.setIsOnSwitchPath(true);
            }
        }
        int i0 = this.drawParentSwitch?1:0;
        label1: {
            if(i0 == 0)
            {
                break label1;
            }
            SplayTreeIterNode a5 = this.parentNode;
            if(a5 != null)
            {
                SplayTreeIterNode a6 = this.parentNode;
                a6.setIsOnPath(false);
                SplayTreeIterNode a7 = this.parentNode;
                a7.setIsOnSwitchPath(true);
            }
        }
        SplayTreeIterNode a8 = this.head;
        return a8;
    }
    
    public void highlightSwitchPath(SplayTreeIterNode a)
    {
        if(a != null)
        {
            a.setIsOnSwitchPath(true);
            SplayTreeIterDataNode a0 = a.getParent();
            this.highlightSwitchPath((SplayTreeIterNode)a0);
        }
    }
    
    public void highlightPath(SplayTreeIterNode a)
    {
        if(a != null)
        {
            a.setIsOnPath(true);
            SplayTreeIterDataNode a0 = a.getParent();
            this.highlightPath((SplayTreeIterNode)a0);
        }
    }
    
    private void unHighlightPath(SplayTreeIterNode a)
    {
        label0: {
            if(a == null)
            {
                break label0;
            }
            a.setIsOnPath(false);
            a.setIsOnSwitchPath(false);
            int i = a instanceof SplayTreeIterDataNode?1:0;
            if(i != 0)
            {
                SplayTreeIterDataNode dummy = (SplayTreeIterDataNode)a;
                SplayTreeIterDataNode a0 = (SplayTreeIterDataNode)a;
                SplayTreeIterNode a1 = a0.getLeft();
                this.unHighlightPath(a1);
                SplayTreeIterDataNode dummy0 = (SplayTreeIterDataNode)a;
                SplayTreeIterDataNode a2 = (SplayTreeIterDataNode)a;
                SplayTreeIterNode a3 = a2.getRight();
                this.unHighlightPath(a3);
            }
        }
    }
    
    public SplayTreeIterNode getCurrentNode()
    {
        SplayTreeIterNode a = null;
        int i = this.drawPointers?1:0;
        if(i != 0)
        {
            SplayTreeIterNode a0 = this.currentNode;
            a = a0;
        }
        else
        {
            a = null;
        }
        return a;
    }
    
    public SplayTreeIterNode getParentNode()
    {
        SplayTreeIterNode a = null;
        int i = this.drawPointers?1:0;
        if(i != 0)
        {
            SplayTreeIterNode a0 = this.parentNode;
            a = a0;
        }
        else
        {
            a = null;
        }
        return a;
    }
    
    public int getCurrentLevel()
    {
        int i = this.currentLevel;
        return i;
    }
    
    public boolean getNoMoveTween()
    {
        int i = this.noMoveTween?1:0;
        return i != 0;
    }
    
    protected void run(boolean b)
    {
        if(b)
        {
            int i = this.nextMode;
            this.executingMode = i;
            SplayTreeIterDataNode.clearNodeColors();
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
            SplayTreeIterNode a = this.savedTree;
            if(a == null)
            {
                SplayTreeIterNullNode a0 = new SplayTreeIterNullNode();
                this.startingTree = a0;
            }
            else
            {
                SplayTreeIterNode a1 = this.savedTree;
                SplayTreeIterNode a2 = a1.getCopy();
                this.startingTree = a2;
            }
        }
        int i1 = this.executingMode;
        label2: {
            SplayTreeIterNode a3 = null;
            label1: {
                int i2 = 0;
                switch(i1){
                    case 12: {
                        a3 = this.head;
                        break label1;
                    }
                    case 11: {
                        i2 = this.insertPos;
                        break;
                    }
                }
                int i3 = i2;
                while(true)
                {
                    com.cim.AIA.ElementArray a4 = this.insertData;
                    int i4 = a4.getSize();
                    if(i3 >= i4)
                    {
                        break;
                    }
                    this.setPosition("2a");
                    com.cim.AIA.ElementArray a5 = this.insertData;
                    com.cim.AIA.Element a6 = a5.getElement(i3);
                    Object a7 = a6.getObject();
                    String s = a7.toString();
                    com.cim.AIA.ElementArray a8 = this.insertData;
                    com.cim.AIA.Element a9 = a8.getElement(i3);
                    Object a10 = a9.getObject();
                    Integer dummy = (Integer)a10;
                    Integer a11 = (Integer)a10;
                    int i5 = a11.intValue();
                    SplayTreeIterDataItem a12 = new SplayTreeIterDataItem(s, i5);
                    int i6 = this.enabled?1:0;
                    label3: {
                        if(i6 == 0)
                        {
                            break label3;
                        }
                        com.cim.AIA.ElementArray a13 = this.insertData;
                        com.cim.AIA.Element a14 = a13.getElement(i3);
                        com.cim.AIA.Node dummy0 = (com.cim.AIA.Node)a14;
                        com.cim.AIA.Node a15 = (com.cim.AIA.Node)a14;
                        java.awt.Color a16 = SplayTreeIterColors.INSERT_HIGHLIGHT_COLOR;
                        a15.setBackgroundColor(a16);
                        SplayTreeIterNode a17 = this.head;
                        this.currentNode = a17;
                        SplayTreeIterNode a18 = this.head;
                        SplayTreeIterNode a19 = a18.getCopy();
                        this.savedTree = a19;
                        SplayTreeIterNode a20 = this.head;
                        SplayTreeIterNode a21 = this.insert(a20, a12, (Boolean)null);
                        this.head = a21;
                        this.currentLevel = -1;
                        this.parentNode = null;
                        this.currentNode = null;
                        com.cim.AIA.ElementArray a22 = this.insertData;
                        com.cim.AIA.Element a23 = a22.getElement(i3);
                        com.cim.AIA.Node dummy1 = (com.cim.AIA.Node)a23;
                        com.cim.AIA.Node a24 = (com.cim.AIA.Node)a23;
                        java.awt.Color a25 = SplayTreeIterColors.INSERT_DONE_COLOR;
                        a24.setBackgroundColor(a25);
                        int i7 = this.enabled?1:0;
                        if(i7 == 0)
                        {
                            SplayTreeIterNode a26 = this.savedTree;
                            SplayTreeIterNode a27 = a26.getCopy();
                            this.head = a27;
                        }
                        else
                        {
                            int i8 = this.insertPos;
                            int i9 = i8 + 1;
                            this.insertPos = i9;
                        }
                    }
                    this.clearPointers();
                    int i10 = i3 + 1;
                    i3 = i10;
                    continue;
                }
                break label2;
            }
            label4: {
                int i11 = a3 instanceof SplayTreeIterNullNode?1:0;
                if(i11 == 0)
                {
                    break label4;
                }
                break label2;
            }
            int i12 = 0;
            while(true)
            {
                com.cim.AIA.ElementArray a28 = this.searchData;
                int i13 = a28.getSize();
                if(i12 >= i13)
                {
                    break;
                }
                com.cim.AIA.ElementArray a29 = this.searchData;
                com.cim.AIA.Element a30 = a29.getElement(i12);
                com.cim.AIA.Node dummy2 = (com.cim.AIA.Node)a30;
                com.cim.AIA.Node a31 = (com.cim.AIA.Node)a30;
                Object a32 = a31.getObject();
                String s0 = a32.toString();
                Object a33 = a31.getObject();
                Integer dummy3 = (Integer)a33;
                Integer a34 = (Integer)a33;
                int i14 = a34.intValue();
                SplayTreeIterDataItem a35 = new SplayTreeIterDataItem(s0, i14);
                int i15 = this.enabled?1:0;
                label5: {
                    if(i15 == 0)
                    {
                        break label5;
                    }
                    java.awt.Color a36 = SplayTreeIterColors.SEARCH_HIGHLIGHT_COLOR;
                    a31.setBackgroundColor(a36);
                    this.wasFound = null;
                    SplayTreeIterNode a37 = this.head;
                    this.currentNode = a37;
                    SplayTreeIterNode a38 = this.head;
                    SplayTreeIterNode a39 = this.search(a38, a35, (Boolean)null);
                    this.head = a39;
                    this.currentNode = null;
                    Boolean a40 = this.wasFound;
                    label6: {
                        if(a40 == null)
                        {
                            break label6;
                        }
                        Boolean a41 = this.wasFound;
                        int i16 = a41.booleanValue()?1:0;
                        if(i16 == 0)
                        {
                            com.cim.AIA.StringMarker a42 = new com.cim.AIA.StringMarker("N");
                            a31.addMarker(a42);
                            com.cim.AIA.StringMarker a43 = new com.cim.AIA.StringMarker("o");
                            a31.addMarker(a43);
                            com.cim.AIA.StringMarker a44 = new com.cim.AIA.StringMarker("t");
                            a31.addMarker(a44);
                            com.cim.AIA.StringMarker a45 = new com.cim.AIA.StringMarker(" ");
                            a31.addMarker(a45);
                            com.cim.AIA.StringMarker a46 = new com.cim.AIA.StringMarker("F");
                            a31.addMarker(a46);
                            com.cim.AIA.StringMarker a47 = new com.cim.AIA.StringMarker("o");
                            a31.addMarker(a47);
                            com.cim.AIA.StringMarker a48 = new com.cim.AIA.StringMarker("u");
                            a31.addMarker(a48);
                            com.cim.AIA.StringMarker a49 = new com.cim.AIA.StringMarker("n");
                            a31.addMarker(a49);
                            com.cim.AIA.StringMarker a50 = new com.cim.AIA.StringMarker("d");
                            a31.addMarker(a50);
                        }
                        else
                        {
                            com.cim.AIA.StringMarker a51 = new com.cim.AIA.StringMarker("F");
                            a31.addMarker(a51);
                            com.cim.AIA.StringMarker a52 = new com.cim.AIA.StringMarker("o");
                            a31.addMarker(a52);
                            com.cim.AIA.StringMarker a53 = new com.cim.AIA.StringMarker("u");
                            a31.addMarker(a53);
                            com.cim.AIA.StringMarker a54 = new com.cim.AIA.StringMarker("n");
                            a31.addMarker(a54);
                            com.cim.AIA.StringMarker a55 = new com.cim.AIA.StringMarker("d");
                            a31.addMarker(a55);
                        }
                    }
                    this.setPosition("5.4");
                    java.awt.Color a56 = SplayTreeIterColors.SEARCH_DONE_COLOR;
                    a31.setBackgroundColor(a56);
                }
                this.clearPointers();
                int i17 = i12 + 1;
                i12 = i17;
            }
        }
    }
    
    private SplayTreeIterDataNode rotateRight(SplayTreeIterNode a, boolean b)
    {
        SplayTreeIterDataNode a0 = null;
        SplayTreeIterDataNode dummy = (SplayTreeIterDataNode)a;
        SplayTreeIterDataNode a1 = (SplayTreeIterDataNode)a;
        int i = b?1:0;
        SplayTreeIterNode a2 = a1.getLeft();
        label1: {
            int i0 = 0;
            label0: {
                int i1 = a2 instanceof SplayTreeIterNullNode?1:0;
                if(i1 == 0)
                {
                    break label0;
                }
                SplayTreeIterDataNode dummy0 = (SplayTreeIterDataNode)a;
                SplayTreeIterDataNode a3 = (SplayTreeIterDataNode)a;
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
            SplayTreeIterDataNode a7 = a.getParent();
            SplayTreeIterDataNode dummy1 = (SplayTreeIterDataNode)a;
            SplayTreeIterDataNode a8 = (SplayTreeIterDataNode)a;
            SplayTreeIterNode a9 = a8.getLeft();
            SplayTreeIterDataNode dummy2 = (SplayTreeIterDataNode)a9;
            SplayTreeIterDataNode a10 = (SplayTreeIterDataNode)a9;
            label2: {
                if(a7 == null)
                {
                    break label2;
                }
                SplayTreeIterNode a11 = a7.getLeft();
                if(a11 != a)
                {
                    com.cim.AIA.Line a12 = a7.getRightLink();
                    java.awt.Point a13 = a.getPosition();
                    java.awt.Point a14 = new java.awt.Point(a13);
                    java.awt.Point a15 = a10.getPosition();
                    java.awt.Point a16 = new java.awt.Point(a15);
                    SplayTreeIter$MoveMoveable a17 = new SplayTreeIter$MoveMoveable(this, (com.cim.AIA.Moveable)a12, a14, a16, false);
                    a6.addElement((Object)a17);
                }
                else
                {
                    com.cim.AIA.Line a18 = a7.getLeftLink();
                    java.awt.Point a19 = a.getPosition();
                    java.awt.Point a20 = new java.awt.Point(a19);
                    java.awt.Point a21 = a10.getPosition();
                    java.awt.Point a22 = new java.awt.Point(a21);
                    SplayTreeIter$MoveMoveable a23 = new SplayTreeIter$MoveMoveable(this, (com.cim.AIA.Moveable)a18, a20, a22, false);
                    a6.addElement((Object)a23);
                }
            }
            SplayTreeIterDataNode dummy3 = (SplayTreeIterDataNode)a;
            SplayTreeIterDataNode a24 = (SplayTreeIterDataNode)a;
            com.cim.AIA.Line a25 = a24.getLeftLink();
            SplayTreeIterDataNode dummy4 = (SplayTreeIterDataNode)a;
            SplayTreeIterDataNode a26 = (SplayTreeIterDataNode)a;
            SplayTreeIterNode a27 = a26.getLeft();
            java.awt.Point a28 = a27.getPosition();
            java.awt.Point a29 = new java.awt.Point(a28);
            SplayTreeIterNode a30 = a10.getRight();
            java.awt.Point a31 = a30.getPosition();
            java.awt.Point a32 = new java.awt.Point(a31);
            SplayTreeIter$MoveMoveable a33 = new SplayTreeIter$MoveMoveable(this, (com.cim.AIA.Moveable)a25, a29, a32, false);
            a6.addElement((Object)a33);
            com.cim.AIA.Line a34 = a10.getRightLink();
            SplayTreeIterNode a35 = a10.getRight();
            java.awt.Point a36 = a35.getPosition();
            java.awt.Point a37 = new java.awt.Point(a36);
            java.awt.Point a38 = a.getPosition();
            java.awt.Point a39 = new java.awt.Point(a38);
            SplayTreeIter$MoveMoveable a40 = new SplayTreeIter$MoveMoveable(this, (com.cim.AIA.Moveable)a34, a37, a39, false);
            a6.addElement((Object)a40);
            java.util.Vector a41 = this.tweenVectorVector;
            a41.addElement((Object)a6);
            if(i != 0)
            {
                i0 = i;
            }
            else
            {
                java.util.Vector a42 = new java.util.Vector();
                this.tweenVectorVector = a42;
                i0 = 0;
            }
            int i2 = this.isTweenFast?1:0;
            this.animate("$This key doesn't exist###");
            this.isTweenFast = i2 != 0;
            java.util.Vector a43 = this.tweenVectorVector;
            if(a43 == null)
            {
                java.util.Vector a44 = new java.util.Vector();
                this.tweenVectorVector = a44;
            }
            java.util.Vector a45 = new java.util.Vector();
            java.awt.Point a46 = a.getPosition();
            java.awt.Point a47 = new java.awt.Point(a46);
            java.awt.Point a48 = a.getPosition();
            int i3 = a48.x;
            SplayTreeIterDataNode dummy5 = (SplayTreeIterDataNode)a;
            SplayTreeIterDataNode a49 = (SplayTreeIterDataNode)a;
            SplayTreeIterNode a50 = a49.getLeft();
            java.awt.Point a51 = a50.getPosition();
            int i4 = a51.y;
            java.awt.Point a52 = new java.awt.Point(i3, i4);
            SplayTreeIter$MoveMoveable a53 = new SplayTreeIter$MoveMoveable(this, (com.cim.AIA.Moveable)a, a47, a52, false);
            a45.addElement((Object)a53);
            SplayTreeIterDataNode dummy6 = (SplayTreeIterDataNode)a;
            SplayTreeIterDataNode a54 = (SplayTreeIterDataNode)a;
            SplayTreeIterNode a55 = a54.getRight();
            java.awt.Point a56 = a55.getPosition();
            java.awt.Point a57 = new java.awt.Point(a56);
            java.awt.Point a58 = a55.getPosition();
            SplayTreeIter$MoveMoveable a59 = new SplayTreeIter$MoveMoveable(this, (com.cim.AIA.Moveable)a55, a57, a58, true);
            a45.addElement((Object)a59);
            SplayTreeIterNode a60 = a10.getLeft();
            SplayTreeIterNode a61 = a10.getLeft();
            java.awt.Point a62 = a61.getPosition();
            java.awt.Point a63 = new java.awt.Point(a62);
            SplayTreeIterNode a64 = a10.getLeft();
            java.awt.Point a65 = a64.getPosition();
            int i5 = a65.x;
            SplayTreeIterDataNode dummy7 = (SplayTreeIterDataNode)a;
            SplayTreeIterDataNode a66 = (SplayTreeIterDataNode)a;
            SplayTreeIterNode a67 = a66.getLeft();
            java.awt.Point a68 = a67.getPosition();
            int i6 = a68.y;
            java.awt.Point a69 = new java.awt.Point(i5, i6);
            SplayTreeIter$MoveMoveable a70 = new SplayTreeIter$MoveMoveable(this, (com.cim.AIA.Moveable)a60, a63, a69, true);
            a45.addElement((Object)a70);
            java.awt.Point a71 = a10.getPosition();
            java.awt.Point a72 = new java.awt.Point(a71);
            java.awt.Point a73 = a10.getPosition();
            int i7 = a73.x;
            java.awt.Point a74 = a.getPosition();
            int i8 = a74.y;
            java.awt.Point a75 = new java.awt.Point(i7, i8);
            SplayTreeIter$MoveMoveable a76 = new SplayTreeIter$MoveMoveable(this, (com.cim.AIA.Moveable)a10, a72, a75, false);
            a45.addElement((Object)a76);
            java.util.Vector a77 = this.tweenVectorVector;
            a77.addElement((Object)a45);
            if(i0 == 0)
            {
                java.util.Vector a78 = new java.util.Vector();
                this.tweenVectorVector = a78;
            }
            SplayTreeIterDataNode dummy8 = (SplayTreeIterDataNode)a;
            SplayTreeIterDataNode a79 = (SplayTreeIterDataNode)a;
            SplayTreeIterNode a80 = a10.getRight();
            a79.setLeft(a80);
            a10.setRight(a);
            a10.setParent(a7);
            label3: {
                if(a7 == null)
                {
                    break label3;
                }
                SplayTreeIterNode a81 = a7.getLeft();
                label4: {
                    if(a81 != a)
                    {
                        break label4;
                    }
                    a7.setLeft((SplayTreeIterNode)a10);
                    break label3;
                }
                SplayTreeIterNode a82 = a7.getRight();
                if(a82 == a)
                {
                    a7.setRight((SplayTreeIterNode)a10);
                }
            }
            a0 = a10;
        }
        return a0;
    }
    
    private SplayTreeIterDataNode rotateLeft(SplayTreeIterNode a, boolean b)
    {
        SplayTreeIterDataNode a0 = null;
        SplayTreeIterDataNode dummy = (SplayTreeIterDataNode)a;
        SplayTreeIterDataNode a1 = (SplayTreeIterDataNode)a;
        int i = b?1:0;
        SplayTreeIterNode a2 = a1.getRight();
        label1: {
            int i0 = 0;
            label0: {
                int i1 = a2 instanceof SplayTreeIterNullNode?1:0;
                if(i1 == 0)
                {
                    break label0;
                }
                SplayTreeIterDataNode dummy0 = (SplayTreeIterDataNode)a;
                SplayTreeIterDataNode a3 = (SplayTreeIterDataNode)a;
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
            SplayTreeIterDataNode a7 = a.getParent();
            SplayTreeIterDataNode dummy1 = (SplayTreeIterDataNode)a;
            SplayTreeIterDataNode a8 = (SplayTreeIterDataNode)a;
            SplayTreeIterNode a9 = a8.getRight();
            SplayTreeIterDataNode dummy2 = (SplayTreeIterDataNode)a9;
            SplayTreeIterDataNode a10 = (SplayTreeIterDataNode)a9;
            label2: {
                if(a7 == null)
                {
                    break label2;
                }
                SplayTreeIterNode a11 = a7.getLeft();
                if(a11 != a)
                {
                    com.cim.AIA.Line a12 = a7.getRightLink();
                    java.awt.Point a13 = a.getPosition();
                    java.awt.Point a14 = new java.awt.Point(a13);
                    java.awt.Point a15 = a10.getPosition();
                    java.awt.Point a16 = new java.awt.Point(a15);
                    SplayTreeIter$MoveMoveable a17 = new SplayTreeIter$MoveMoveable(this, (com.cim.AIA.Moveable)a12, a14, a16, false);
                    a6.addElement((Object)a17);
                }
                else
                {
                    com.cim.AIA.Line a18 = a7.getLeftLink();
                    java.awt.Point a19 = a.getPosition();
                    java.awt.Point a20 = new java.awt.Point(a19);
                    java.awt.Point a21 = a10.getPosition();
                    java.awt.Point a22 = new java.awt.Point(a21);
                    SplayTreeIter$MoveMoveable a23 = new SplayTreeIter$MoveMoveable(this, (com.cim.AIA.Moveable)a18, a20, a22, false);
                    a6.addElement((Object)a23);
                }
            }
            SplayTreeIterDataNode dummy3 = (SplayTreeIterDataNode)a;
            SplayTreeIterDataNode a24 = (SplayTreeIterDataNode)a;
            com.cim.AIA.Line a25 = a24.getRightLink();
            SplayTreeIterDataNode dummy4 = (SplayTreeIterDataNode)a;
            SplayTreeIterDataNode a26 = (SplayTreeIterDataNode)a;
            SplayTreeIterNode a27 = a26.getRight();
            java.awt.Point a28 = a27.getPosition();
            java.awt.Point a29 = new java.awt.Point(a28);
            SplayTreeIterNode a30 = a10.getLeft();
            java.awt.Point a31 = a30.getPosition();
            java.awt.Point a32 = new java.awt.Point(a31);
            SplayTreeIter$MoveMoveable a33 = new SplayTreeIter$MoveMoveable(this, (com.cim.AIA.Moveable)a25, a29, a32, false);
            a6.addElement((Object)a33);
            com.cim.AIA.Line a34 = a10.getLeftLink();
            SplayTreeIterNode a35 = a10.getLeft();
            java.awt.Point a36 = a35.getPosition();
            java.awt.Point a37 = new java.awt.Point(a36);
            java.awt.Point a38 = a.getPosition();
            java.awt.Point a39 = new java.awt.Point(a38);
            SplayTreeIter$MoveMoveable a40 = new SplayTreeIter$MoveMoveable(this, (com.cim.AIA.Moveable)a34, a37, a39, false);
            a6.addElement((Object)a40);
            java.util.Vector a41 = this.tweenVectorVector;
            a41.addElement((Object)a6);
            if(i != 0)
            {
                i0 = i;
            }
            else
            {
                java.util.Vector a42 = new java.util.Vector();
                this.tweenVectorVector = a42;
                i0 = 0;
            }
            int i2 = this.isTweenFast?1:0;
            this.animate("$This key doesn't exist###");
            this.isTweenFast = i2 != 0;
            java.util.Vector a43 = this.tweenVectorVector;
            if(a43 == null)
            {
                java.util.Vector a44 = new java.util.Vector();
                this.tweenVectorVector = a44;
            }
            java.util.Vector a45 = new java.util.Vector();
            java.awt.Point a46 = a.getPosition();
            java.awt.Point a47 = new java.awt.Point(a46);
            java.awt.Point a48 = a.getPosition();
            int i3 = a48.x;
            SplayTreeIterDataNode dummy5 = (SplayTreeIterDataNode)a;
            SplayTreeIterDataNode a49 = (SplayTreeIterDataNode)a;
            SplayTreeIterNode a50 = a49.getRight();
            java.awt.Point a51 = a50.getPosition();
            int i4 = a51.y;
            java.awt.Point a52 = new java.awt.Point(i3, i4);
            SplayTreeIter$MoveMoveable a53 = new SplayTreeIter$MoveMoveable(this, (com.cim.AIA.Moveable)a, a47, a52, false);
            a45.addElement((Object)a53);
            SplayTreeIterDataNode dummy6 = (SplayTreeIterDataNode)a;
            SplayTreeIterDataNode a54 = (SplayTreeIterDataNode)a;
            SplayTreeIterNode a55 = a54.getLeft();
            java.awt.Point a56 = a55.getPosition();
            java.awt.Point a57 = new java.awt.Point(a56);
            java.awt.Point a58 = a55.getPosition();
            SplayTreeIter$MoveMoveable a59 = new SplayTreeIter$MoveMoveable(this, (com.cim.AIA.Moveable)a55, a57, a58, true);
            a45.addElement((Object)a59);
            SplayTreeIterNode a60 = a10.getRight();
            SplayTreeIterNode a61 = a10.getRight();
            java.awt.Point a62 = a61.getPosition();
            java.awt.Point a63 = new java.awt.Point(a62);
            SplayTreeIterNode a64 = a10.getRight();
            java.awt.Point a65 = a64.getPosition();
            int i5 = a65.x;
            SplayTreeIterDataNode dummy7 = (SplayTreeIterDataNode)a;
            SplayTreeIterDataNode a66 = (SplayTreeIterDataNode)a;
            SplayTreeIterNode a67 = a66.getRight();
            java.awt.Point a68 = a67.getPosition();
            int i6 = a68.y;
            java.awt.Point a69 = new java.awt.Point(i5, i6);
            SplayTreeIter$MoveMoveable a70 = new SplayTreeIter$MoveMoveable(this, (com.cim.AIA.Moveable)a60, a63, a69, true);
            a45.addElement((Object)a70);
            java.awt.Point a71 = a10.getPosition();
            java.awt.Point a72 = new java.awt.Point(a71);
            java.awt.Point a73 = a10.getPosition();
            int i7 = a73.x;
            java.awt.Point a74 = a.getPosition();
            int i8 = a74.y;
            java.awt.Point a75 = new java.awt.Point(i7, i8);
            SplayTreeIter$MoveMoveable a76 = new SplayTreeIter$MoveMoveable(this, (com.cim.AIA.Moveable)a10, a72, a75, false);
            a45.addElement((Object)a76);
            java.util.Vector a77 = this.tweenVectorVector;
            a77.addElement((Object)a45);
            if(i0 == 0)
            {
                java.util.Vector a78 = new java.util.Vector();
                this.tweenVectorVector = a78;
            }
            SplayTreeIterDataNode dummy8 = (SplayTreeIterDataNode)a;
            SplayTreeIterDataNode a79 = (SplayTreeIterDataNode)a;
            SplayTreeIterNode a80 = a10.getLeft();
            a79.setRight(a80);
            a10.setLeft(a);
            a10.setParent(a7);
            label3: {
                if(a7 == null)
                {
                    break label3;
                }
                SplayTreeIterNode a81 = a7.getLeft();
                label4: {
                    if(a81 != a)
                    {
                        break label4;
                    }
                    a7.setLeft((SplayTreeIterNode)a10);
                    break label3;
                }
                SplayTreeIterNode a82 = a7.getRight();
                if(a82 == a)
                {
                    a7.setRight((SplayTreeIterNode)a10);
                }
            }
            a0 = a10;
        }
        return a0;
    }
    
    private SplayTreeIterNode insert(SplayTreeIterNode a, SplayTreeIterDataItem a0, Boolean a1)
    {
        SplayTreeIterNode a2 = null;
        this.switchPath = null;
        this.setPosition("3");
        if(a == null)
        {
            RuntimeException a3 = new RuntimeException("insert: Node should never be null");
            throw a3;
        }
        SplayTreeIterDataNode a4 = a.getParent();
        this.setPosition("3.1");
        label1: {
            label0: {
                int i = a instanceof SplayTreeIterNullNode?1:0;
                if(i == 0)
                {
                    break label0;
                }
                SplayTreeIterDataNode a5 = new SplayTreeIterDataNode(a0);
                this.linkEarly(a4, (SplayTreeIterNode)a5, a1);
                this.currentNode = a5;
                this.setPosition("3.1.1");
                this.switchPath = null;
                a2 = a5;
                break label1;
            }
            this.currentNode = a;
            this.setPosition("3.2.1.1");
            this.noMoveTween = false;
            while(true)
            {
                SplayTreeIterNode a6 = this.currentNode;
                int i0 = a6 instanceof SplayTreeIterNullNode?1:0;
                if(i0 != 0)
                {
                    break;
                }
                this.setPosition("3.2.2.1");
                SplayTreeIterNode a7 = this.currentNode;
                this.parentNode = a7;
                this.setPosition("3.2.2.1.1");
                this.setPosition("3.2.2.1.2");
                int i1 = a0.getKey();
                SplayTreeIterNode a8 = this.currentNode;
                SplayTreeIterDataNode dummy = (SplayTreeIterDataNode)a8;
                SplayTreeIterDataNode a9 = (SplayTreeIterDataNode)a8;
                int i2 = a9.getKey();
                if(i1 >= i2)
                {
                    this.setPosition("3.2.2.1.4");
                    SplayTreeIterNode a10 = this.currentNode;
                    SplayTreeIterDataNode dummy0 = (SplayTreeIterDataNode)a10;
                    SplayTreeIterDataNode a11 = (SplayTreeIterDataNode)a10;
                    SplayTreeIterNode a12 = a11.getRight();
                    this.currentNode = a12;
                    this.setPosition("3.2.2.1.5");
                }
                else
                {
                    SplayTreeIterNode a13 = this.currentNode;
                    SplayTreeIterDataNode dummy1 = (SplayTreeIterDataNode)a13;
                    SplayTreeIterDataNode a14 = (SplayTreeIterDataNode)a13;
                    SplayTreeIterNode a15 = a14.getLeft();
                    this.currentNode = a15;
                    this.setPosition("3.2.2.1.3");
                }
            }
            this.setPosition("3.2.2.1");
            this.setPosition("3.2.2.2");
            this.noMoveTween = true;
            this.setPosition("3.2.3.1");
            this.setPosition("3.2.3.2");
            SplayTreeIterDataNode a16 = new SplayTreeIterDataNode(a0);
            int i3 = a0.getKey();
            SplayTreeIterNode a17 = this.parentNode;
            SplayTreeIterDataNode dummy2 = (SplayTreeIterDataNode)a17;
            SplayTreeIterDataNode a18 = (SplayTreeIterDataNode)a17;
            int i4 = a18.getKey();
            if(i3 >= i4)
            {
                this.setPosition("3.2.3.4");
                SplayTreeIterNode a19 = this.parentNode;
                SplayTreeIterDataNode dummy3 = (SplayTreeIterDataNode)a19;
                SplayTreeIterDataNode a20 = (SplayTreeIterDataNode)a19;
                a20.setRight((SplayTreeIterNode)a16);
                this.setPosition("3.2.3.5");
            }
            else
            {
                SplayTreeIterNode a21 = this.parentNode;
                SplayTreeIterDataNode dummy4 = (SplayTreeIterDataNode)a21;
                SplayTreeIterDataNode a22 = (SplayTreeIterDataNode)a21;
                a22.setLeft((SplayTreeIterNode)a16);
                this.setPosition("3.2.3.3");
            }
            this.currentNode = a16;
            this.setPosition("3.2.3.6");
            this.noMoveTween = false;
            while(true)
            {
                SplayTreeIterNode a23 = this.currentNode;
                SplayTreeIterNode a24 = this.head;
                if(a23 == a24)
                {
                    break;
                }
                SplayTreeIterNode a25 = this.parentNode;
                SplayTreeIterNode a26 = this.head;
                if(a25 == a26)
                {
                    break;
                }
                this.drawSwitch = true;
                this.drawParentSwitch = true;
                int i5 = this.isExpanded("3.3.1")?1:0;
                label3: {
                    label2: {
                        if(i5 == 0)
                        {
                            break label2;
                        }
                        int i6 = this.isExpanded("3.3")?1:0;
                        if(i6 == 0)
                        {
                            break label2;
                        }
                        this.drawPointers = true;
                        break label3;
                    }
                    this.drawPointers = false;
                }
                this.setPosition("3.3.1.1");
                SplayTreeIterNode a27 = this.parentNode;
                SplayTreeIterDataNode a28 = a27.getParent();
                SplayTreeIterNode a29 = a28.getLeft();
                SplayTreeIterNode a30 = this.parentNode;
                label4: {
                    if(a29 != a30)
                    {
                        break label4;
                    }
                    SplayTreeIterNode a31 = this.parentNode;
                    SplayTreeIterDataNode dummy5 = (SplayTreeIterDataNode)a31;
                    SplayTreeIterDataNode a32 = (SplayTreeIterDataNode)a31;
                    SplayTreeIterNode a33 = a32.getLeft();
                    SplayTreeIterNode a34 = this.currentNode;
                    if(a33 != a34)
                    {
                        break label4;
                    }
                    this.setPosition("3.3.1.1.1");
                    int i7 = this.isExpanded("3.3.1")?1:0;
                    label6: {
                        label5: {
                            if(i7 == 0)
                            {
                                break label5;
                            }
                            int i8 = this.isExpanded("3.3.1.1.1")?1:0;
                            if(i8 == 0)
                            {
                                break label5;
                            }
                            int i9 = this.isExpanded("3.3")?1:0;
                            if(i9 == 0)
                            {
                                break label5;
                            }
                            SplayTreeIterNode a35 = this.parentNode;
                            SplayTreeIterDataNode a36 = a35.getParent();
                            SplayTreeIterDataNode a37 = this.rotateRight((SplayTreeIterNode)a36, true);
                            this.parentNode = a37;
                            break label6;
                        }
                        SplayTreeIterNode a38 = this.parentNode;
                        SplayTreeIterDataNode a39 = a38.getParent();
                        SplayTreeIterDataNode a40 = this.rotateRight((SplayTreeIterNode)a39, false);
                        this.parentNode = a40;
                    }
                    SplayTreeIterNode a41 = this.parentNode;
                    SplayTreeIterDataNode a42 = a41.getParent();
                    if(a42 == null)
                    {
                        SplayTreeIterNode a43 = this.parentNode;
                        this.head = a43;
                    }
                    this.setPosition("3.3.1.1.2");
                    int i10 = this.isExpanded("3.3.1")?1:0;
                    label8: {
                        label7: {
                            if(i10 == 0)
                            {
                                break label7;
                            }
                            int i11 = this.isExpanded("3.3.1.1.1")?1:0;
                            if(i11 == 0)
                            {
                                break label7;
                            }
                            int i12 = this.isExpanded("3.3")?1:0;
                            if(i12 == 0)
                            {
                                break label7;
                            }
                            SplayTreeIterNode a44 = this.parentNode;
                            SplayTreeIterDataNode dummy6 = (SplayTreeIterDataNode)a44;
                            SplayTreeIterDataNode a45 = (SplayTreeIterDataNode)a44;
                            SplayTreeIterDataNode a46 = this.rotateRight((SplayTreeIterNode)a45, true);
                            this.currentNode = a46;
                            break label8;
                        }
                        SplayTreeIterNode a47 = this.parentNode;
                        SplayTreeIterDataNode dummy7 = (SplayTreeIterDataNode)a47;
                        SplayTreeIterDataNode a48 = (SplayTreeIterDataNode)a47;
                        SplayTreeIterDataNode a49 = this.rotateRight((SplayTreeIterNode)a48, false);
                        this.currentNode = a49;
                    }
                    SplayTreeIterNode a50 = this.currentNode;
                    SplayTreeIterDataNode a51 = a50.getParent();
                    this.parentNode = a51;
                    SplayTreeIterNode a52 = this.parentNode;
                    if(a52 == null)
                    {
                        SplayTreeIterNode a53 = this.currentNode;
                        this.head = a53;
                    }
                    this.setPosition("3.3.1.1.3");
                    continue;
                }
                SplayTreeIterNode a54 = this.parentNode;
                SplayTreeIterDataNode a55 = a54.getParent();
                SplayTreeIterNode a56 = a55.getRight();
                SplayTreeIterNode a57 = this.parentNode;
                label9: {
                    if(a56 != a57)
                    {
                        break label9;
                    }
                    SplayTreeIterNode a58 = this.parentNode;
                    SplayTreeIterDataNode dummy8 = (SplayTreeIterDataNode)a58;
                    SplayTreeIterDataNode a59 = (SplayTreeIterDataNode)a58;
                    SplayTreeIterNode a60 = a59.getRight();
                    SplayTreeIterNode a61 = this.currentNode;
                    if(a60 != a61)
                    {
                        break label9;
                    }
                    this.setPosition("3.3.1.1.1");
                    int i13 = this.isExpanded("3.3.1")?1:0;
                    label11: {
                        label10: {
                            if(i13 == 0)
                            {
                                break label10;
                            }
                            int i14 = this.isExpanded("3.3.1.1.1")?1:0;
                            if(i14 == 0)
                            {
                                break label10;
                            }
                            int i15 = this.isExpanded("3.3")?1:0;
                            if(i15 == 0)
                            {
                                break label10;
                            }
                            SplayTreeIterNode a62 = this.parentNode;
                            SplayTreeIterDataNode a63 = a62.getParent();
                            SplayTreeIterDataNode a64 = this.rotateLeft((SplayTreeIterNode)a63, true);
                            this.parentNode = a64;
                            break label11;
                        }
                        SplayTreeIterNode a65 = this.parentNode;
                        SplayTreeIterDataNode a66 = a65.getParent();
                        SplayTreeIterDataNode a67 = this.rotateLeft((SplayTreeIterNode)a66, false);
                        this.parentNode = a67;
                    }
                    SplayTreeIterNode a68 = this.parentNode;
                    SplayTreeIterDataNode a69 = a68.getParent();
                    if(a69 == null)
                    {
                        SplayTreeIterNode a70 = this.parentNode;
                        this.head = a70;
                    }
                    this.setPosition("3.3.1.1.2");
                    int i16 = this.isExpanded("3.3.1")?1:0;
                    label13: {
                        label12: {
                            if(i16 == 0)
                            {
                                break label12;
                            }
                            int i17 = this.isExpanded("3.3.1.1.1")?1:0;
                            if(i17 == 0)
                            {
                                break label12;
                            }
                            int i18 = this.isExpanded("3.3")?1:0;
                            if(i18 == 0)
                            {
                                break label12;
                            }
                            SplayTreeIterNode a71 = this.parentNode;
                            SplayTreeIterDataNode dummy9 = (SplayTreeIterDataNode)a71;
                            SplayTreeIterDataNode a72 = (SplayTreeIterDataNode)a71;
                            SplayTreeIterDataNode a73 = this.rotateLeft((SplayTreeIterNode)a72, true);
                            this.currentNode = a73;
                            break label13;
                        }
                        SplayTreeIterNode a74 = this.parentNode;
                        SplayTreeIterDataNode dummy10 = (SplayTreeIterDataNode)a74;
                        SplayTreeIterDataNode a75 = (SplayTreeIterDataNode)a74;
                        SplayTreeIterDataNode a76 = this.rotateLeft((SplayTreeIterNode)a75, false);
                        this.currentNode = a76;
                    }
                    SplayTreeIterNode a77 = this.currentNode;
                    SplayTreeIterDataNode a78 = a77.getParent();
                    this.parentNode = a78;
                    SplayTreeIterNode a79 = this.parentNode;
                    if(a79 == null)
                    {
                        SplayTreeIterNode a80 = this.currentNode;
                        this.head = a80;
                    }
                    this.setPosition("3.3.1.1.3");
                    continue;
                }
                SplayTreeIterNode a81 = this.parentNode;
                SplayTreeIterDataNode a82 = a81.getParent();
                SplayTreeIterNode a83 = a82.getLeft();
                SplayTreeIterNode a84 = this.parentNode;
                label14: {
                    if(a83 != a84)
                    {
                        break label14;
                    }
                    SplayTreeIterNode a85 = this.parentNode;
                    SplayTreeIterDataNode dummy11 = (SplayTreeIterDataNode)a85;
                    SplayTreeIterDataNode a86 = (SplayTreeIterDataNode)a85;
                    SplayTreeIterNode a87 = a86.getRight();
                    SplayTreeIterNode a88 = this.currentNode;
                    if(a87 != a88)
                    {
                        break label14;
                    }
                    this.setPosition("3.3.1.1.1");
                    this.setPosition("3.3.1.1.4");
                    int i19 = this.isExpanded("3.3.1")?1:0;
                    label16: {
                        label15: {
                            if(i19 == 0)
                            {
                                break label15;
                            }
                            int i20 = this.isExpanded("3.3.1.1.4")?1:0;
                            if(i20 == 0)
                            {
                                break label15;
                            }
                            int i21 = this.isExpanded("3.3")?1:0;
                            if(i21 == 0)
                            {
                                break label15;
                            }
                            SplayTreeIterNode a89 = this.parentNode;
                            SplayTreeIterDataNode dummy12 = (SplayTreeIterDataNode)a89;
                            SplayTreeIterDataNode a90 = (SplayTreeIterDataNode)a89;
                            SplayTreeIterDataNode a91 = this.rotateLeft((SplayTreeIterNode)a90, true);
                            this.currentNode = a91;
                            break label16;
                        }
                        SplayTreeIterNode a92 = this.parentNode;
                        SplayTreeIterDataNode dummy13 = (SplayTreeIterDataNode)a92;
                        SplayTreeIterDataNode a93 = (SplayTreeIterDataNode)a92;
                        SplayTreeIterDataNode a94 = this.rotateLeft((SplayTreeIterNode)a93, false);
                        this.currentNode = a94;
                    }
                    SplayTreeIterNode a95 = this.currentNode;
                    SplayTreeIterDataNode a96 = a95.getParent();
                    this.parentNode = a96;
                    this.setPosition("3.3.1.1.5");
                    int i22 = this.isExpanded("3.3.1")?1:0;
                    label18: {
                        label17: {
                            if(i22 == 0)
                            {
                                break label17;
                            }
                            int i23 = this.isExpanded("3.3.1.1.4")?1:0;
                            if(i23 == 0)
                            {
                                break label17;
                            }
                            int i24 = this.isExpanded("3.3")?1:0;
                            if(i24 == 0)
                            {
                                break label17;
                            }
                            SplayTreeIterNode a97 = this.parentNode;
                            SplayTreeIterDataNode dummy14 = (SplayTreeIterDataNode)a97;
                            SplayTreeIterDataNode a98 = (SplayTreeIterDataNode)a97;
                            SplayTreeIterDataNode a99 = this.rotateRight((SplayTreeIterNode)a98, true);
                            this.currentNode = a99;
                            break label18;
                        }
                        SplayTreeIterNode a100 = this.parentNode;
                        SplayTreeIterDataNode dummy15 = (SplayTreeIterDataNode)a100;
                        SplayTreeIterDataNode a101 = (SplayTreeIterDataNode)a100;
                        SplayTreeIterDataNode a102 = this.rotateRight((SplayTreeIterNode)a101, false);
                        this.currentNode = a102;
                    }
                    SplayTreeIterNode a103 = this.currentNode;
                    SplayTreeIterDataNode a104 = a103.getParent();
                    this.parentNode = a104;
                    SplayTreeIterNode a105 = this.parentNode;
                    if(a105 == null)
                    {
                        SplayTreeIterNode a106 = this.currentNode;
                        this.head = a106;
                    }
                    this.setPosition("3.3.1.1.6");
                    continue;
                }
                SplayTreeIterNode a107 = this.parentNode;
                SplayTreeIterDataNode a108 = a107.getParent();
                SplayTreeIterNode a109 = a108.getRight();
                SplayTreeIterNode a110 = this.parentNode;
                if(a109 != a110)
                {
                    continue;
                }
                SplayTreeIterNode a111 = this.parentNode;
                SplayTreeIterDataNode dummy16 = (SplayTreeIterDataNode)a111;
                SplayTreeIterDataNode a112 = (SplayTreeIterDataNode)a111;
                SplayTreeIterNode a113 = a112.getLeft();
                SplayTreeIterNode a114 = this.currentNode;
                if(a113 != a114)
                {
                    continue;
                }
                this.setPosition("3.3.1.1.1");
                this.setPosition("3.3.1.1.4");
                int i25 = this.isExpanded("3.3.1")?1:0;
                label20: {
                    label19: {
                        if(i25 == 0)
                        {
                            break label19;
                        }
                        int i26 = this.isExpanded("3.3.1.1.4")?1:0;
                        if(i26 == 0)
                        {
                            break label19;
                        }
                        int i27 = this.isExpanded("3.3")?1:0;
                        if(i27 == 0)
                        {
                            break label19;
                        }
                        SplayTreeIterNode a115 = this.parentNode;
                        SplayTreeIterDataNode dummy17 = (SplayTreeIterDataNode)a115;
                        SplayTreeIterDataNode a116 = (SplayTreeIterDataNode)a115;
                        SplayTreeIterDataNode a117 = this.rotateRight((SplayTreeIterNode)a116, true);
                        this.currentNode = a117;
                        break label20;
                    }
                    SplayTreeIterNode a118 = this.parentNode;
                    SplayTreeIterDataNode dummy18 = (SplayTreeIterDataNode)a118;
                    SplayTreeIterDataNode a119 = (SplayTreeIterDataNode)a118;
                    SplayTreeIterDataNode a120 = this.rotateRight((SplayTreeIterNode)a119, false);
                    this.currentNode = a120;
                }
                SplayTreeIterNode a121 = this.currentNode;
                SplayTreeIterDataNode a122 = a121.getParent();
                this.parentNode = a122;
                this.setPosition("3.3.1.1.5");
                int i28 = this.isExpanded("3.3.1")?1:0;
                label22: {
                    label21: {
                        if(i28 == 0)
                        {
                            break label21;
                        }
                        int i29 = this.isExpanded("3.3.1.1.4")?1:0;
                        if(i29 == 0)
                        {
                            break label21;
                        }
                        int i30 = this.isExpanded("3.3")?1:0;
                        if(i30 == 0)
                        {
                            break label21;
                        }
                        SplayTreeIterNode a123 = this.parentNode;
                        SplayTreeIterDataNode dummy19 = (SplayTreeIterDataNode)a123;
                        SplayTreeIterDataNode a124 = (SplayTreeIterDataNode)a123;
                        SplayTreeIterDataNode a125 = this.rotateLeft((SplayTreeIterNode)a124, true);
                        this.currentNode = a125;
                        break label22;
                    }
                    SplayTreeIterNode a126 = this.parentNode;
                    SplayTreeIterDataNode dummy20 = (SplayTreeIterDataNode)a126;
                    SplayTreeIterDataNode a127 = (SplayTreeIterDataNode)a126;
                    SplayTreeIterDataNode a128 = this.rotateLeft((SplayTreeIterNode)a127, false);
                    this.currentNode = a128;
                }
                SplayTreeIterNode a129 = this.currentNode;
                SplayTreeIterDataNode a130 = a129.getParent();
                this.parentNode = a130;
                SplayTreeIterNode a131 = this.parentNode;
                if(a131 == null)
                {
                    SplayTreeIterNode a132 = this.currentNode;
                    this.head = a132;
                }
                this.setPosition("3.3.1.1.6");
            }
            this.drawPointers = true;
            this.drawSwitch = false;
            this.drawParentSwitch = false;
            this.setPosition("3.3.1.1");
            this.setPosition("3.3.1.2");
            this.drawSwitch = true;
            this.setPosition("3.3.2.1");
            SplayTreeIterNode a133 = this.currentNode;
            SplayTreeIterNode a134 = this.head;
            label23: {
                if(a133 == a134)
                {
                    break label23;
                }
                int i31 = this.isExpanded("3.3")?1:0;
                if(i31 != 0)
                {
                    this.drawPointers = true;
                }
                else
                {
                    this.drawPointers = false;
                }
                SplayTreeIterNode a135 = this.currentNode;
                SplayTreeIterNode a136 = this.parentNode;
                SplayTreeIterDataNode dummy21 = (SplayTreeIterDataNode)a136;
                SplayTreeIterDataNode a137 = (SplayTreeIterDataNode)a136;
                SplayTreeIterNode a138 = a137.getRight();
                label25: {
                    label24: {
                        if(a135 == a138)
                        {
                            break label24;
                        }
                        SplayTreeIterNode a139 = this.parentNode;
                        this.currentNode = a139;
                        int i32 = this.isExpanded("3.3")?1:0;
                        if(i32 != 0)
                        {
                            SplayTreeIterNode a140 = this.parentNode;
                            SplayTreeIterDataNode dummy22 = (SplayTreeIterDataNode)a140;
                            SplayTreeIterDataNode a141 = (SplayTreeIterDataNode)a140;
                            SplayTreeIterDataNode a142 = this.rotateRight((SplayTreeIterNode)a141, true);
                            this.currentNode = a142;
                        }
                        else
                        {
                            SplayTreeIterNode a143 = this.parentNode;
                            SplayTreeIterDataNode dummy23 = (SplayTreeIterDataNode)a143;
                            SplayTreeIterDataNode a144 = (SplayTreeIterDataNode)a143;
                            SplayTreeIterDataNode a145 = this.rotateRight((SplayTreeIterNode)a144, false);
                            this.currentNode = a145;
                        }
                        SplayTreeIterNode a146 = this.currentNode;
                        SplayTreeIterDataNode a147 = a146.getParent();
                        this.parentNode = a147;
                        SplayTreeIterNode a148 = this.currentNode;
                        this.head = a148;
                        break label25;
                    }
                    SplayTreeIterNode a149 = this.parentNode;
                    this.currentNode = a149;
                    int i33 = this.isExpanded("3.3")?1:0;
                    if(i33 != 0)
                    {
                        SplayTreeIterNode a150 = this.parentNode;
                        SplayTreeIterDataNode dummy24 = (SplayTreeIterDataNode)a150;
                        SplayTreeIterDataNode a151 = (SplayTreeIterDataNode)a150;
                        SplayTreeIterDataNode a152 = this.rotateLeft((SplayTreeIterNode)a151, true);
                        this.currentNode = a152;
                    }
                    else
                    {
                        SplayTreeIterNode a153 = this.parentNode;
                        SplayTreeIterDataNode dummy25 = (SplayTreeIterDataNode)a153;
                        SplayTreeIterDataNode a154 = (SplayTreeIterDataNode)a153;
                        SplayTreeIterDataNode a155 = this.rotateLeft((SplayTreeIterNode)a154, false);
                        this.currentNode = a155;
                    }
                    SplayTreeIterNode a156 = this.currentNode;
                    SplayTreeIterDataNode a157 = a156.getParent();
                    this.parentNode = a157;
                    SplayTreeIterNode a158 = this.currentNode;
                    this.head = a158;
                }
                this.setPosition("3.3.2.1.1");
            }
            this.drawPointers = true;
            this.drawSwitch = false;
            this.setPosition("3.3.2.2");
            this.setPosition("3.END");
            SplayTreeIterNode a159 = this.currentNode;
            a2 = a159;
        }
        return a2;
    }
    
    private SplayTreeIterNode search(SplayTreeIterNode a, SplayTreeIterDataItem a0, Boolean a1)
    {
        int i = 0;
        SplayTreeIterDataNode.clearNodeColors();
        this.currentNode = a;
        this.setPosition("5.1.1");
        this.setPosition("5.1.2");
        this.noMoveTween = false;
        if(a == null)
        {
            RuntimeException a2 = new RuntimeException("insert: Node should never be null");
            throw a2;
        }
        while(true)
        {
            SplayTreeIterNode a3 = this.currentNode;
            int i0 = a3 instanceof SplayTreeIterNullNode?1:0;
            if(i0 != 0)
            {
                i = 0;
                break;
            }
            this.setPosition("5.2.1");
            this.setPosition("5.2.1.2");
            int i1 = a0.getKey();
            SplayTreeIterNode a4 = this.currentNode;
            SplayTreeIterDataNode dummy = (SplayTreeIterDataNode)a4;
            SplayTreeIterDataNode a5 = (SplayTreeIterDataNode)a4;
            int i2 = a5.getKey();
            label0: {
                if(i1 != i2)
                {
                    break label0;
                }
                this.setPosition("5.2.1.2.1");
                this.setPosition("5.2.1.2.2");
                i = 1;
                break;
            }
            SplayTreeIterNode a6 = this.currentNode;
            this.parentNode = a6;
            this.setPosition("5.2.1.1");
            this.setPosition("5.2.1.3");
            int i3 = a0.getKey();
            SplayTreeIterNode a7 = this.currentNode;
            SplayTreeIterDataNode dummy0 = (SplayTreeIterDataNode)a7;
            SplayTreeIterDataNode a8 = (SplayTreeIterDataNode)a7;
            int i4 = a8.getKey();
            if(i3 >= i4)
            {
                this.setPosition("5.2.1.4");
                SplayTreeIterNode a9 = this.currentNode;
                SplayTreeIterDataNode dummy1 = (SplayTreeIterDataNode)a9;
                SplayTreeIterDataNode a10 = (SplayTreeIterDataNode)a9;
                SplayTreeIterNode a11 = a10.getRight();
                this.currentNode = a11;
                this.setPosition("5.2.1.4.1");
            }
            else
            {
                SplayTreeIterNode a12 = this.currentNode;
                SplayTreeIterDataNode dummy2 = (SplayTreeIterDataNode)a12;
                SplayTreeIterDataNode a13 = (SplayTreeIterDataNode)a12;
                SplayTreeIterNode a14 = a13.getLeft();
                this.currentNode = a14;
                this.setPosition("5.2.1.3.1");
            }
        }
        this.setPosition("5.2.1");
        this.setPosition("5.2.2");
        this.setPosition("5.2.3.1");
        SplayTreeIterNode a15 = this.currentNode;
        int i5 = a15 instanceof SplayTreeIterNullNode?1:0;
        if(i5 != 0)
        {
            SplayTreeIterNode a16 = this.parentNode;
            this.currentNode = a16;
            this.setPosition("5.2.3.2");
            SplayTreeIterNode a17 = this.parentNode;
            SplayTreeIterDataNode a18 = a17.getParent();
            this.parentNode = a18;
            this.setPosition("5.2.3.3");
        }
        this.setPosition("5.2.4");
        while(true)
        {
            SplayTreeIterNode a19 = this.currentNode;
            SplayTreeIterNode a20 = this.head;
            if(a19 == a20)
            {
                break;
            }
            SplayTreeIterNode a21 = this.parentNode;
            SplayTreeIterNode a22 = this.head;
            if(a21 == a22)
            {
                break;
            }
            this.drawSwitch = true;
            this.drawParentSwitch = true;
            this.setPosition("5.3.1.1");
            SplayTreeIterNode a23 = this.parentNode;
            SplayTreeIterDataNode a24 = a23.getParent();
            SplayTreeIterNode a25 = a24.getLeft();
            SplayTreeIterNode a26 = this.parentNode;
            label1: {
                if(a25 != a26)
                {
                    break label1;
                }
                SplayTreeIterNode a27 = this.parentNode;
                SplayTreeIterDataNode dummy3 = (SplayTreeIterDataNode)a27;
                SplayTreeIterDataNode a28 = (SplayTreeIterDataNode)a27;
                SplayTreeIterNode a29 = a28.getLeft();
                SplayTreeIterNode a30 = this.currentNode;
                if(a29 != a30)
                {
                    break label1;
                }
                this.setPosition("5.3.1.1.1");
                int i6 = this.isExpanded("5.3.1")?1:0;
                label3: {
                    label2: {
                        if(i6 == 0)
                        {
                            break label2;
                        }
                        int i7 = this.isExpanded("5.3.1.1.1")?1:0;
                        if(i7 == 0)
                        {
                            break label2;
                        }
                        int i8 = this.isExpanded("5.3")?1:0;
                        if(i8 == 0)
                        {
                            break label2;
                        }
                        SplayTreeIterNode a31 = this.parentNode;
                        SplayTreeIterDataNode a32 = a31.getParent();
                        SplayTreeIterDataNode a33 = this.rotateRight((SplayTreeIterNode)a32, true);
                        this.parentNode = a33;
                        break label3;
                    }
                    SplayTreeIterNode a34 = this.parentNode;
                    SplayTreeIterDataNode a35 = a34.getParent();
                    SplayTreeIterDataNode a36 = this.rotateRight((SplayTreeIterNode)a35, false);
                    this.parentNode = a36;
                }
                SplayTreeIterNode a37 = this.parentNode;
                SplayTreeIterDataNode a38 = a37.getParent();
                if(a38 == null)
                {
                    SplayTreeIterNode a39 = this.parentNode;
                    this.head = a39;
                }
                this.setPosition("5.3.1.1.2");
                int i9 = this.isExpanded("5.3.1")?1:0;
                label5: {
                    label4: {
                        if(i9 == 0)
                        {
                            break label4;
                        }
                        int i10 = this.isExpanded("5.3.1.1.1")?1:0;
                        if(i10 == 0)
                        {
                            break label4;
                        }
                        int i11 = this.isExpanded("5.3")?1:0;
                        if(i11 == 0)
                        {
                            break label4;
                        }
                        SplayTreeIterNode a40 = this.parentNode;
                        SplayTreeIterDataNode dummy4 = (SplayTreeIterDataNode)a40;
                        SplayTreeIterDataNode a41 = (SplayTreeIterDataNode)a40;
                        SplayTreeIterDataNode a42 = this.rotateRight((SplayTreeIterNode)a41, true);
                        this.currentNode = a42;
                        break label5;
                    }
                    SplayTreeIterNode a43 = this.parentNode;
                    SplayTreeIterDataNode dummy5 = (SplayTreeIterDataNode)a43;
                    SplayTreeIterDataNode a44 = (SplayTreeIterDataNode)a43;
                    SplayTreeIterDataNode a45 = this.rotateRight((SplayTreeIterNode)a44, false);
                    this.currentNode = a45;
                }
                SplayTreeIterNode a46 = this.currentNode;
                SplayTreeIterDataNode a47 = a46.getParent();
                this.parentNode = a47;
                SplayTreeIterNode a48 = this.parentNode;
                if(a48 == null)
                {
                    SplayTreeIterNode a49 = this.currentNode;
                    this.head = a49;
                }
                this.setPosition("5.3.1.1.3");
                continue;
            }
            SplayTreeIterNode a50 = this.parentNode;
            SplayTreeIterDataNode a51 = a50.getParent();
            SplayTreeIterNode a52 = a51.getRight();
            SplayTreeIterNode a53 = this.parentNode;
            label6: {
                if(a52 != a53)
                {
                    break label6;
                }
                SplayTreeIterNode a54 = this.parentNode;
                SplayTreeIterDataNode dummy6 = (SplayTreeIterDataNode)a54;
                SplayTreeIterDataNode a55 = (SplayTreeIterDataNode)a54;
                SplayTreeIterNode a56 = a55.getRight();
                SplayTreeIterNode a57 = this.currentNode;
                if(a56 != a57)
                {
                    break label6;
                }
                this.setPosition("5.3.1.1.1");
                int i12 = this.isExpanded("5.3.1")?1:0;
                label8: {
                    label7: {
                        if(i12 == 0)
                        {
                            break label7;
                        }
                        int i13 = this.isExpanded("5.3.1.1.1")?1:0;
                        if(i13 == 0)
                        {
                            break label7;
                        }
                        int i14 = this.isExpanded("5.3")?1:0;
                        if(i14 == 0)
                        {
                            break label7;
                        }
                        SplayTreeIterNode a58 = this.parentNode;
                        SplayTreeIterDataNode a59 = a58.getParent();
                        SplayTreeIterDataNode a60 = this.rotateLeft((SplayTreeIterNode)a59, true);
                        this.parentNode = a60;
                        break label8;
                    }
                    SplayTreeIterNode a61 = this.parentNode;
                    SplayTreeIterDataNode a62 = a61.getParent();
                    SplayTreeIterDataNode a63 = this.rotateLeft((SplayTreeIterNode)a62, false);
                    this.parentNode = a63;
                }
                SplayTreeIterNode a64 = this.parentNode;
                SplayTreeIterDataNode a65 = a64.getParent();
                if(a65 == null)
                {
                    SplayTreeIterNode a66 = this.parentNode;
                    this.head = a66;
                }
                this.setPosition("5.3.1.1.2");
                int i15 = this.isExpanded("5.3.1")?1:0;
                label10: {
                    label9: {
                        if(i15 == 0)
                        {
                            break label9;
                        }
                        int i16 = this.isExpanded("5.3.1.1.1")?1:0;
                        if(i16 == 0)
                        {
                            break label9;
                        }
                        int i17 = this.isExpanded("5.3")?1:0;
                        if(i17 == 0)
                        {
                            break label9;
                        }
                        SplayTreeIterNode a67 = this.parentNode;
                        SplayTreeIterDataNode dummy7 = (SplayTreeIterDataNode)a67;
                        SplayTreeIterDataNode a68 = (SplayTreeIterDataNode)a67;
                        SplayTreeIterDataNode a69 = this.rotateLeft((SplayTreeIterNode)a68, true);
                        this.currentNode = a69;
                        break label10;
                    }
                    SplayTreeIterNode a70 = this.parentNode;
                    SplayTreeIterDataNode dummy8 = (SplayTreeIterDataNode)a70;
                    SplayTreeIterDataNode a71 = (SplayTreeIterDataNode)a70;
                    SplayTreeIterDataNode a72 = this.rotateLeft((SplayTreeIterNode)a71, false);
                    this.currentNode = a72;
                }
                SplayTreeIterNode a73 = this.currentNode;
                SplayTreeIterDataNode a74 = a73.getParent();
                this.parentNode = a74;
                SplayTreeIterNode a75 = this.parentNode;
                if(a75 == null)
                {
                    SplayTreeIterNode a76 = this.currentNode;
                    this.head = a76;
                }
                this.setPosition("5.3.1.1.3");
                continue;
            }
            SplayTreeIterNode a77 = this.parentNode;
            SplayTreeIterDataNode a78 = a77.getParent();
            SplayTreeIterNode a79 = a78.getLeft();
            SplayTreeIterNode a80 = this.parentNode;
            label11: {
                if(a79 != a80)
                {
                    break label11;
                }
                SplayTreeIterNode a81 = this.parentNode;
                SplayTreeIterDataNode dummy9 = (SplayTreeIterDataNode)a81;
                SplayTreeIterDataNode a82 = (SplayTreeIterDataNode)a81;
                SplayTreeIterNode a83 = a82.getRight();
                SplayTreeIterNode a84 = this.currentNode;
                if(a83 != a84)
                {
                    break label11;
                }
                this.setPosition("5.3.1.1.1");
                this.setPosition("5.3.1.1.4");
                int i18 = this.isExpanded("5.3.1")?1:0;
                label13: {
                    label12: {
                        if(i18 == 0)
                        {
                            break label12;
                        }
                        int i19 = this.isExpanded("5.3.1.1.4")?1:0;
                        if(i19 == 0)
                        {
                            break label12;
                        }
                        int i20 = this.isExpanded("5.3")?1:0;
                        if(i20 == 0)
                        {
                            break label12;
                        }
                        SplayTreeIterNode a85 = this.parentNode;
                        SplayTreeIterDataNode dummy10 = (SplayTreeIterDataNode)a85;
                        SplayTreeIterDataNode a86 = (SplayTreeIterDataNode)a85;
                        SplayTreeIterDataNode a87 = this.rotateLeft((SplayTreeIterNode)a86, true);
                        this.currentNode = a87;
                        break label13;
                    }
                    SplayTreeIterNode a88 = this.parentNode;
                    SplayTreeIterDataNode dummy11 = (SplayTreeIterDataNode)a88;
                    SplayTreeIterDataNode a89 = (SplayTreeIterDataNode)a88;
                    SplayTreeIterDataNode a90 = this.rotateLeft((SplayTreeIterNode)a89, false);
                    this.currentNode = a90;
                }
                SplayTreeIterNode a91 = this.currentNode;
                SplayTreeIterDataNode a92 = a91.getParent();
                this.parentNode = a92;
                this.setPosition("5.3.1.1.5");
                int i21 = this.isExpanded("5.3.1")?1:0;
                label15: {
                    label14: {
                        if(i21 == 0)
                        {
                            break label14;
                        }
                        int i22 = this.isExpanded("5.3.1.1.4")?1:0;
                        if(i22 == 0)
                        {
                            break label14;
                        }
                        int i23 = this.isExpanded("5.3")?1:0;
                        if(i23 == 0)
                        {
                            break label14;
                        }
                        SplayTreeIterNode a93 = this.parentNode;
                        SplayTreeIterDataNode dummy12 = (SplayTreeIterDataNode)a93;
                        SplayTreeIterDataNode a94 = (SplayTreeIterDataNode)a93;
                        SplayTreeIterDataNode a95 = this.rotateRight((SplayTreeIterNode)a94, true);
                        this.currentNode = a95;
                        break label15;
                    }
                    SplayTreeIterNode a96 = this.parentNode;
                    SplayTreeIterDataNode dummy13 = (SplayTreeIterDataNode)a96;
                    SplayTreeIterDataNode a97 = (SplayTreeIterDataNode)a96;
                    SplayTreeIterDataNode a98 = this.rotateRight((SplayTreeIterNode)a97, false);
                    this.currentNode = a98;
                }
                SplayTreeIterNode a99 = this.currentNode;
                SplayTreeIterDataNode a100 = a99.getParent();
                this.parentNode = a100;
                SplayTreeIterNode a101 = this.parentNode;
                if(a101 == null)
                {
                    SplayTreeIterNode a102 = this.currentNode;
                    this.head = a102;
                }
                this.setPosition("5.3.1.1.6");
                continue;
            }
            SplayTreeIterNode a103 = this.parentNode;
            SplayTreeIterDataNode a104 = a103.getParent();
            SplayTreeIterNode a105 = a104.getRight();
            SplayTreeIterNode a106 = this.parentNode;
            if(a105 != a106)
            {
                continue;
            }
            SplayTreeIterNode a107 = this.parentNode;
            SplayTreeIterDataNode dummy14 = (SplayTreeIterDataNode)a107;
            SplayTreeIterDataNode a108 = (SplayTreeIterDataNode)a107;
            SplayTreeIterNode a109 = a108.getLeft();
            SplayTreeIterNode a110 = this.currentNode;
            if(a109 != a110)
            {
                continue;
            }
            this.setPosition("5.3.1.1.1");
            this.setPosition("5.3.1.1.4");
            int i24 = this.isExpanded("5.3.1")?1:0;
            label17: {
                label16: {
                    if(i24 == 0)
                    {
                        break label16;
                    }
                    int i25 = this.isExpanded("5.3.1.1.4")?1:0;
                    if(i25 == 0)
                    {
                        break label16;
                    }
                    int i26 = this.isExpanded("5.3")?1:0;
                    if(i26 == 0)
                    {
                        break label16;
                    }
                    SplayTreeIterNode a111 = this.parentNode;
                    SplayTreeIterDataNode dummy15 = (SplayTreeIterDataNode)a111;
                    SplayTreeIterDataNode a112 = (SplayTreeIterDataNode)a111;
                    SplayTreeIterDataNode a113 = this.rotateRight((SplayTreeIterNode)a112, true);
                    this.currentNode = a113;
                    break label17;
                }
                SplayTreeIterNode a114 = this.parentNode;
                SplayTreeIterDataNode dummy16 = (SplayTreeIterDataNode)a114;
                SplayTreeIterDataNode a115 = (SplayTreeIterDataNode)a114;
                SplayTreeIterDataNode a116 = this.rotateRight((SplayTreeIterNode)a115, false);
                this.currentNode = a116;
            }
            SplayTreeIterNode a117 = this.currentNode;
            SplayTreeIterDataNode a118 = a117.getParent();
            this.parentNode = a118;
            this.setPosition("5.3.1.1.5");
            int i27 = this.isExpanded("5.3.1")?1:0;
            label19: {
                label18: {
                    if(i27 == 0)
                    {
                        break label18;
                    }
                    int i28 = this.isExpanded("5.3.1.1.4")?1:0;
                    if(i28 == 0)
                    {
                        break label18;
                    }
                    int i29 = this.isExpanded("5.3")?1:0;
                    if(i29 == 0)
                    {
                        break label18;
                    }
                    SplayTreeIterNode a119 = this.parentNode;
                    SplayTreeIterDataNode dummy17 = (SplayTreeIterDataNode)a119;
                    SplayTreeIterDataNode a120 = (SplayTreeIterDataNode)a119;
                    SplayTreeIterDataNode a121 = this.rotateLeft((SplayTreeIterNode)a120, true);
                    this.currentNode = a121;
                    break label19;
                }
                SplayTreeIterNode a122 = this.parentNode;
                SplayTreeIterDataNode dummy18 = (SplayTreeIterDataNode)a122;
                SplayTreeIterDataNode a123 = (SplayTreeIterDataNode)a122;
                SplayTreeIterDataNode a124 = this.rotateLeft((SplayTreeIterNode)a123, false);
                this.currentNode = a124;
            }
            SplayTreeIterNode a125 = this.currentNode;
            SplayTreeIterDataNode a126 = a125.getParent();
            this.parentNode = a126;
            SplayTreeIterNode a127 = this.parentNode;
            if(a127 == null)
            {
                SplayTreeIterNode a128 = this.currentNode;
                this.head = a128;
            }
            this.setPosition("5.3.1.1.6");
        }
        this.drawSwitch = false;
        this.drawParentSwitch = false;
        this.setPosition("5.3.1.1");
        this.setPosition("5.3.1.2");
        this.drawSwitch = true;
        this.setPosition("5.3.2.1");
        SplayTreeIterNode a129 = this.currentNode;
        SplayTreeIterNode a130 = this.head;
        label20: {
            if(a129 == a130)
            {
                break label20;
            }
            SplayTreeIterNode a131 = this.currentNode;
            SplayTreeIterNode a132 = this.parentNode;
            SplayTreeIterDataNode dummy19 = (SplayTreeIterDataNode)a132;
            SplayTreeIterDataNode a133 = (SplayTreeIterDataNode)a132;
            SplayTreeIterNode a134 = a133.getRight();
            label22: {
                label21: {
                    if(a131 == a134)
                    {
                        break label21;
                    }
                    SplayTreeIterNode a135 = this.parentNode;
                    this.currentNode = a135;
                    int i30 = this.isExpanded("5.3")?1:0;
                    if(i30 != 0)
                    {
                        SplayTreeIterNode a136 = this.parentNode;
                        SplayTreeIterDataNode dummy20 = (SplayTreeIterDataNode)a136;
                        SplayTreeIterDataNode a137 = (SplayTreeIterDataNode)a136;
                        SplayTreeIterDataNode a138 = this.rotateRight((SplayTreeIterNode)a137, true);
                        this.currentNode = a138;
                    }
                    else
                    {
                        SplayTreeIterNode a139 = this.parentNode;
                        SplayTreeIterDataNode dummy21 = (SplayTreeIterDataNode)a139;
                        SplayTreeIterDataNode a140 = (SplayTreeIterDataNode)a139;
                        SplayTreeIterDataNode a141 = this.rotateRight((SplayTreeIterNode)a140, false);
                        this.currentNode = a141;
                    }
                    SplayTreeIterNode a142 = this.currentNode;
                    SplayTreeIterDataNode a143 = a142.getParent();
                    this.parentNode = a143;
                    SplayTreeIterNode a144 = this.currentNode;
                    this.head = a144;
                    break label22;
                }
                SplayTreeIterNode a145 = this.parentNode;
                this.currentNode = a145;
                int i31 = this.isExpanded("5.3")?1:0;
                if(i31 != 0)
                {
                    SplayTreeIterNode a146 = this.parentNode;
                    SplayTreeIterDataNode dummy22 = (SplayTreeIterDataNode)a146;
                    SplayTreeIterDataNode a147 = (SplayTreeIterDataNode)a146;
                    SplayTreeIterDataNode a148 = this.rotateLeft((SplayTreeIterNode)a147, true);
                    this.currentNode = a148;
                }
                else
                {
                    SplayTreeIterNode a149 = this.parentNode;
                    SplayTreeIterDataNode dummy23 = (SplayTreeIterDataNode)a149;
                    SplayTreeIterDataNode a150 = (SplayTreeIterDataNode)a149;
                    SplayTreeIterDataNode a151 = this.rotateLeft((SplayTreeIterNode)a150, false);
                    this.currentNode = a151;
                }
                SplayTreeIterNode a152 = this.currentNode;
                SplayTreeIterDataNode a153 = a152.getParent();
                this.parentNode = a153;
                SplayTreeIterNode a154 = this.currentNode;
                this.head = a154;
            }
            this.setPosition("5.3.2.1.1");
        }
        this.drawSwitch = false;
        this.setPosition("5.3.2.2");
        Boolean a155 = new Boolean(i != 0);
        this.wasFound = a155;
        SplayTreeIterNode a156 = this.currentNode;
        return a156;
    }
    
    private void linkEarly(SplayTreeIterDataNode a, SplayTreeIterNode a0, Boolean a1)
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
    
    private void printTree(SplayTreeIterNode a)
    {
        int i = a instanceof SplayTreeIterDataNode?1:0;
        if(i != 0)
        {
            SplayTreeIterDataNode dummy = (SplayTreeIterDataNode)a;
            SplayTreeIterDataNode a0 = (SplayTreeIterDataNode)a;
            SplayTreeIterNode a1 = a0.getLeft();
            this.printTree(a1);
            java.io.PrintStream a2 = System.out;
            SplayTreeIterDataNode dummy0 = (SplayTreeIterDataNode)a;
            SplayTreeIterDataNode a3 = (SplayTreeIterDataNode)a;
            int i0 = a3.getKey();
            a2.println(i0);
            SplayTreeIterDataNode dummy1 = (SplayTreeIterDataNode)a;
            SplayTreeIterDataNode a4 = (SplayTreeIterDataNode)a;
            SplayTreeIterNode a5 = a4.getRight();
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
        SplayTreeIterThread dummy = (SplayTreeIterThread)a;
        SplayTreeIterThread a0 = (SplayTreeIterThread)a;
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
        this.parentNode = null;
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
                SplayTreeIterThread dummy = (SplayTreeIterThread)a0;
                SplayTreeIterThread a1 = (SplayTreeIterThread)a0;
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
        String s = localization.Messages.getString("SplayTreeIter.0");
        SplayTreeIter.BUILD_MODE_LABEL = s;
        String s0 = localization.Messages.getString("SplayTreeIter.1");
        SplayTreeIter.SEARCH_MODE_LABEL = s0;
    }
}