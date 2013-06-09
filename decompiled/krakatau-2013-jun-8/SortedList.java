public class SortedList extends com.cim.AIA.Algorithm implements com.cim.AIA.MethodSelectionListener, com.cim.AIA.ControlListener {
    private int[] insertData;
    private int[] newInsertData;
    private int[] searchData;
    private int[] newSearchData;
    private java.awt.Color[] insertDataColor;
    private java.awt.Color[] searchDataColor;
    private com.cim.AIA.ElementArray insertArray;
    private com.cim.AIA.ElementArray searchArray;
    private boolean isBackMode;
    final private static int BUILD_MODE = 11;
    final private static int SEARCH_MODE = 12;
    final private static int DELETE_MODE = 13;
    final private static java.awt.Color TEXT_COLOR;
    final private static java.awt.Color INSERT_HIGHLIGHT_COLOR;
    final private static java.awt.Color INSERT_ACTIVE_COLOR;
    final private static java.awt.Color INSERT_DONE_COLOR;
    final private static java.awt.Color SEARCH_HIGHLIGHT_COLOR;
    final private static java.awt.Color SEARCH_ACTIVE_COLOR;
    final private static java.awt.Color SEARCH_DONE_COLOR;
    final private static java.awt.Color LIST_ACTIVE_COLOR;
    final private static java.awt.Color LIST_NULL_COLOR;
    final private static java.awt.Color LIST_DONE_COLOR;
    final private static String BUILD_MODE_LABEL;
    final private static String SEARCH_MODE_LABEL;
    final private static String DELETE_MODE_LABEL;
    private com.cim.AIA.Node ptrNode;
    private com.cim.AIA.Node tailptrNode;
    private SList head;
    private int executingMode;
    private int nextMode;
    
    public SortedList(com.cim.AIA.AlgorithmThread a, Object a0)
    {
        super(a, a0);
        this.isBackMode = false;
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
                this.head = null;
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
    
    public void reInitialise(Object a)
    {
        int[] a0 = this.newInsertData;
        this.insertData = a0;
        int[] a1 = this.newSearchData;
        this.searchData = a1;
        int[] a2 = this.insertData;
        int i = a2.length;
        java.awt.Color[] a3 = new java.awt.Color[i];
        this.insertDataColor = a3;
        int i0 = 0;
        while(true)
        {
            int[] a4 = this.insertData;
            int i1 = a4.length;
            if(i0 >= i1)
            {
                break;
            }
            else
            {
                java.awt.Color[] a5 = this.insertDataColor;
                java.awt.Color a6 = SortedList.INSERT_ACTIVE_COLOR;
                a5[i0] = a6;
                int i2 = i0 + 1;
                i0 = i2;
            }
        }
        com.cim.AIA.ElementArray a7 = new com.cim.AIA.ElementArray(0, 0);
        this.insertArray = a7;
        com.cim.AIA.ElementArray a8 = this.insertArray;
        a8.setColumGap(0);
        com.cim.AIA.ElementArray a9 = this.insertArray;
        a9.setElementWidth(20);
        int i3 = 0;
        while(true)
        {
            int[] a10 = this.insertData;
            int i4 = a10.length;
            if(i3 >= i4)
            {
                break;
            }
            else
            {
                int[] a11 = this.insertData;
                int i5 = a11[i3];
                Integer a12 = new Integer(i5);
                com.cim.AIA.Node a13 = new com.cim.AIA.Node((Object)a12, i3);
                java.awt.Color[] a14 = this.insertDataColor;
                java.awt.Color a15 = a14[i3];
                a13.setBackgroundColor(a15);
                java.awt.Color a16 = SortedList.INSERT_HIGHLIGHT_COLOR;
                a13.setHighlightColor(a16);
                java.awt.Color a17 = SortedList.TEXT_COLOR;
                a13.setTextColor(a17);
                com.cim.AIA.ElementArray a18 = this.insertArray;
                a18.setValue(i3, (com.cim.AIA.Element)a13);
                int i6 = i3 + 1;
                i3 = i6;
            }
        }
        int[] a19 = this.searchData;
        int i7 = a19.length;
        java.awt.Color[] a20 = new java.awt.Color[i7];
        this.searchDataColor = a20;
        int i8 = 0;
        while(true)
        {
            int[] a21 = this.searchData;
            int i9 = a21.length;
            if(i8 >= i9)
            {
                break;
            }
            else
            {
                java.awt.Color[] a22 = this.searchDataColor;
                java.awt.Color a23 = SortedList.SEARCH_ACTIVE_COLOR;
                a22[i8] = a23;
                int i10 = i8 + 1;
                i8 = i10;
            }
        }
        com.cim.AIA.ElementArray a24 = new com.cim.AIA.ElementArray(0, 0);
        this.searchArray = a24;
        com.cim.AIA.ElementArray a25 = this.searchArray;
        a25.setColumGap(0);
        com.cim.AIA.ElementArray a26 = this.searchArray;
        a26.setElementWidth(20);
        int i11 = 0;
        while(true)
        {
            int[] a27 = this.searchData;
            int i12 = a27.length;
            if(i11 >= i12)
            {
                this.head = null;
                this.initialise();
                return;
            }
            else
            {
                int[] a28 = this.searchData;
                int i13 = a28[i11];
                Integer a29 = new Integer(i13);
                com.cim.AIA.Node a30 = new com.cim.AIA.Node((Object)a29, i11);
                java.awt.Color[] a31 = this.searchDataColor;
                java.awt.Color a32 = a31[i11];
                a30.setBackgroundColor(a32);
                java.awt.Color a33 = SortedList.SEARCH_HIGHLIGHT_COLOR;
                a30.setHighlightColor(a33);
                java.awt.Color a34 = SortedList.TEXT_COLOR;
                a30.setTextColor(a34);
                com.cim.AIA.ElementArray a35 = this.searchArray;
                a35.setValue(i11, (com.cim.AIA.Element)a30);
                int i14 = i11 + 1;
                i11 = i14;
            }
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
    
    private void initialise()
    {
        this.tailptrNode = null;
        this.ptrNode = null;
    }
    
    protected void initialiseMethods(String s, com.cim.AIA.MethodSelectable a)
    {
        Object a0 = a;
        String s0 = SortedList.BUILD_MODE_LABEL;
        String s1 = localization.Messages.getString("SortedList.3");
        com.cim.AIA.Logger a1 = this.getLogger();
        com.cim.AIA.BreakPoint a2 = this.getBreakPoint();
        com.cim.AIA.MethodSelection a3 = new com.cim.AIA.MethodSelection(s0, s, s1, "3.1", a1, a2);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelection(a3, true);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelectionListener((com.cim.AIA.MethodSelectionListener)this);
    }
    
    public void processMethodSelectionEvent(com.cim.AIA.MethodSelectionEvent a)
    {
        String s = SortedList.BUILD_MODE_LABEL;
        String s0 = a.getMethodName();
        int i = s.compareTo(s0);
        if(i == 0)
        {
            this.nextMode = 11;
        }
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
                    java.awt.Color a9 = SortedList.INSERT_HIGHLIGHT_COLOR;
                    a8.setHighlightColor(a9);
                    com.cim.AIA.ElementArray a10 = this.insertArray;
                    com.cim.AIA.Element a11 = a10.getElement(i);
                    com.cim.AIA.Node dummy1 = (com.cim.AIA.Node)a11;
                    com.cim.AIA.Node a12 = (com.cim.AIA.Node)a11;
                    java.awt.Color a13 = SortedList.TEXT_COLOR;
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
                    java.awt.Color a9 = SortedList.SEARCH_HIGHLIGHT_COLOR;
                    a8.setHighlightColor(a9);
                    com.cim.AIA.ElementArray a10 = this.searchArray;
                    com.cim.AIA.Element a11 = a10.getElement(i);
                    com.cim.AIA.Node dummy1 = (com.cim.AIA.Node)a11;
                    com.cim.AIA.Node a12 = (com.cim.AIA.Node)a11;
                    java.awt.Color a13 = SortedList.TEXT_COLOR;
                    a12.setTextColor(a13);
                    int i1 = i + 1;
                    i = i1;
                }
            }
        }
        com.cim.AIA.ElementArray a14 = this.searchArray;
        return a14;
    }
    
    public SList getHead()
    {
        SList a = this.head;
        return a;
    }
    
    public com.cim.AIA.Node getPtrNode()
    {
        com.cim.AIA.Node a = this.ptrNode;
        return a;
    }
    
    public com.cim.AIA.Node getTailPtrNode()
    {
        com.cim.AIA.Node a = this.tailptrNode;
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
    
    protected void run()
    {
        this.setPosition("1");
        int i = this.executingMode;
        label1: {
            label0: {
                if(i != 11)
                {
                    break label0;
                }
                this.insert();
                break label1;
            }
            int i0 = this.executingMode;
            if(i0 == 12)
            {
                this.setPosition("5");
                this.search();
            }
        }
    }
    
    protected void insert()
    {
        this.setPosition("1");
        this.setPosition("2");
        int[] a = this.insertData;
        int i = a.length;
        label0: {
            if(i <= 0)
            {
                break label0;
            }
            this.setPosition("2.1");
            java.awt.Color[] a0 = this.insertDataColor;
            java.awt.Color a1 = SortedList.INSERT_HIGHLIGHT_COLOR;
            a0[0] = a1;
            Integer a2 = new Integer(-1);
            SList a3 = new SList((Object)a2, -1);
            this.head = a3;
            SList a4 = this.head;
            com.cim.AIA.Node a5 = a4.node;
            java.awt.Color a6 = SortedList.LIST_NULL_COLOR;
            a5.setBackgroundColor(a6);
            SList a7 = this.head;
            com.cim.AIA.Node a8 = a7.node;
            a8.setMarkersBelowValue(false);
            SList a9 = this.head;
            com.cim.AIA.Node a10 = a9.node;
            a10.removeAllMarkers();
            SList a11 = this.head;
            com.cim.AIA.Node a12 = a11.node;
            a12.showObject(false);
            SList a13 = this.head;
            com.cim.AIA.Node a14 = a13.nextNode;
            java.awt.Color a15 = SortedList.LIST_NULL_COLOR;
            a14.setBackgroundColor(a15);
            SList a16 = this.head;
            a16.next = null;
            int i0 = 0;
            while(true)
            {
                int[] a17 = this.insertData;
                int i1 = a17.length;
                if(i0 >= i1)
                {
                    break;
                }
                else
                {
                    this.insertItem(i0);
                    java.awt.Color[] a18 = this.insertDataColor;
                    java.awt.Color a19 = SortedList.INSERT_DONE_COLOR;
                    a18[i0] = a19;
                    int i2 = i0 + 1;
                    i0 = i2;
                }
            }
        }
    }
    
    protected void search()
    {
    }
    
    protected void insertItem(int i)
    {
        java.awt.Color[] a = this.insertDataColor;
        java.awt.Color a0 = SortedList.INSERT_HIGHLIGHT_COLOR;
        a[i] = a0;
        SList a1 = this.head;
        SList a2 = this.head;
        com.cim.AIA.Node a3 = a2.node;
        this.tailptrNode = a3;
        this.ptrNode = a3;
        this.setPosition("3.1.1.1");
        int[] a4 = this.insertData;
        int i0 = a4[i];
        Integer a5 = new Integer(i0);
        SList a6 = a1;
        SList a7 = a1;
        while(true)
        {
            SList a8 = null;
            label1: {
                label0: {
                    if(a6 == null)
                    {
                        break label0;
                    }
                    Object a9 = a6.data;
                    Integer dummy = (Integer)a9;
                    Integer a10 = (Integer)a9;
                    int i1 = a10.intValue();
                    int i2 = a5.intValue();
                    if(i1 < i2)
                    {
                        break label1;
                    }
                }
                this.setPosition("3.1.2.3");
                int[] a11 = this.insertData;
                int i3 = a11[i];
                Integer a12 = new Integer(i3);
                SList a13 = new SList((Object)a12, i);
                com.cim.AIA.Node a14 = a13.node;
                java.awt.Color a15 = SortedList.LIST_ACTIVE_COLOR;
                a14.setBackgroundColor(a15);
                com.cim.AIA.Node a16 = a13.nextNode;
                java.awt.Color a17 = SortedList.LIST_NULL_COLOR;
                a16.setBackgroundColor(a17);
                a13.next = null;
                this.setPosition("3.1.3.1");
                a13.next = a6;
                this.setPosition("3.1.3.2");
                a7.next = a13;
                this.setPosition("3.1.3.3");
                this.tailptrNode = null;
                this.ptrNode = null;
                com.cim.AIA.Node a18 = a13.node;
                java.awt.Color a19 = SortedList.LIST_DONE_COLOR;
                a18.setBackgroundColor(a19);
                return;
            }
            this.setPosition("3.1.2.1");
            com.cim.AIA.Node a20 = this.ptrNode;
            this.tailptrNode = a20;
            this.setPosition("3.1.2.2.1");
            SList a21 = a6.next;
            if(a21 == null)
            {
                com.cim.AIA.Node a22 = a6.nextNode;
                this.ptrNode = a22;
                a8 = null;
            }
            else
            {
                com.cim.AIA.Node a23 = a21.node;
                this.ptrNode = a23;
                a8 = a21;
            }
            this.setPosition("3.1.2.2.2");
            a6 = a8;
            a7 = a6;
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
    
    static
    {
        java.awt.Color a = java.awt.Color.black;
        SortedList.TEXT_COLOR = a;
        java.awt.Color a0 = new java.awt.Color(0, 210, 0);
        SortedList.INSERT_HIGHLIGHT_COLOR = a0;
        java.awt.Color a1 = new java.awt.Color(64, 191, 64);
        SortedList.INSERT_ACTIVE_COLOR = a1;
        java.awt.Color a2 = new java.awt.Color(96, 127, 96);
        SortedList.INSERT_DONE_COLOR = a2;
        java.awt.Color a3 = new java.awt.Color(64, 255, 255);
        SortedList.SEARCH_HIGHLIGHT_COLOR = a3;
        java.awt.Color a4 = new java.awt.Color(64, 191, 191);
        SortedList.SEARCH_ACTIVE_COLOR = a4;
        java.awt.Color a5 = new java.awt.Color(0, 127, 127);
        SortedList.SEARCH_DONE_COLOR = a5;
        java.awt.Color a6 = java.awt.Color.red;
        SortedList.LIST_ACTIVE_COLOR = a6;
        java.awt.Color a7 = SortedList.INSERT_DONE_COLOR;
        SortedList.LIST_NULL_COLOR = a7;
        java.awt.Color a8 = SortedList.INSERT_HIGHLIGHT_COLOR;
        SortedList.LIST_DONE_COLOR = a8;
        String s = localization.Messages.getString("SortedList.0");
        SortedList.BUILD_MODE_LABEL = s;
        String s0 = localization.Messages.getString("SortedList.1");
        SortedList.SEARCH_MODE_LABEL = s0;
        String s1 = localization.Messages.getString("SortedList.2");
        SortedList.DELETE_MODE_LABEL = s1;
    }
}