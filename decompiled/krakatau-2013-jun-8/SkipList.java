public class SkipList extends com.cim.AIA.Algorithm implements com.cim.AIA.MethodSelectionListener, com.cim.AIA.ControlListener {
    private int[] insertData;
    private int[] newInsertData;
    private int insertPos;
    private int[] searchData;
    private int[] newSearchData;
    private java.awt.Color[] insertDataColor;
    private java.awt.Color[] searchDataColor;
    private com.cim.AIA.ElementArray insertArray;
    private com.cim.AIA.ElementArray searchArray;
    private boolean isBackMode;
    private boolean drawBoxLevel;
    private boolean drawBoxFinalLevel;
    private boolean drawIPointer;
    private boolean drawJPointer;
    private boolean drawNewElement;
    private boolean runTween;
    private boolean dataCondition;
    private boolean displayDataCondition;
    private boolean nextNull;
    private int iPointer;
    private int jPointer;
    private int newNodeLevel;
    private Integer dataItem;
    private Integer nextData;
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
    final private static java.awt.Color NODE_HIGHLIGHT_COLOR;
    final private static java.awt.Color NODE_BORDER_COLOR;
    final private static String BUILD_MODE_LABEL;
    final private static String SEARCH_MODE_LABEL;
    final private static String DELETE_MODE_LABEL;
    final public static int MINIMUM_NUMBER_OF_ELEMENTS = 5;
    final public static int MAXIMUM_NUMBER_OF_ELEMENTS = 31;
    private com.cim.AIA.Node ptrNode;
    private com.cim.AIA.Node tailptrNode;
    private com.cim.AIA.Node searchNode;
    private static SkipNode head;
    private SkipNode newElem;
    private SkipNode newElement;
    private SkipNode[] last;
    private com.cim.AIA.Node finalNode;
    private com.cim.AIA.Node dataNode;
    private int executingMode;
    private int nextMode;
    private SkipNode savedSkipList;
    private float prob;
    private int maxLevel;
    private static int levels;
    private java.util.Random randomLevel;
    private static boolean needToResetSeed;
    final private static long aSeed = 87654321L;
    protected java.util.Vector searchNodeVector;
    protected int searchCnt;
    protected int backSearchCnt;
    private boolean previousModeIsBack;
    
    public SkipList(com.cim.AIA.AlgorithmThread a, Object a0)
    {
        super(a, a0);
        this.isBackMode = false;
        this.drawBoxLevel = false;
        this.drawBoxFinalLevel = false;
        this.drawIPointer = false;
        this.drawJPointer = false;
        this.drawNewElement = false;
        this.runTween = false;
        this.dataCondition = false;
        this.displayDataCondition = false;
        this.nextNull = false;
        this.dataNode = null;
        this.savedSkipList = null;
        this.prob = 0.6000000238418579f;
        java.util.Random a1 = new java.util.Random();
        this.randomLevel = a1;
        java.util.Vector a2 = new java.util.Vector();
        this.searchNodeVector = a2;
        this.previousModeIsBack = false;
        double d = Math.log(31.0);
        float f = this.prob;
        float f0 = 1.0f / f;
        double d0 = (double)f0;
        double d1 = Math.log(d0);
        double d2 = d / d1;
        long j = Math.round(d2);
        int i = (int)j;
        int i0 = i - 1;
        this.maxLevel = i0;
        this.executingMode = 11;
        int i1 = this.executingMode;
        this.nextMode = i1;
        int[] dummy = (int[])a0;
        int[] a3 = (int[])a0;
        this.insertData = a3;
        this.newInsertData = a3;
        java.util.Random a4 = new java.util.Random();
        int[] a5 = this.insertData;
        int i2 = a5.length;
        int[] a6 = new int[i2];
        this.searchData = a6;
        int i3 = 0;
        while(true)
        {
            int[] a7 = this.insertData;
            int i4 = a7.length;
            if(i3 >= i4)
            {
                int[] a8 = this.searchData;
                this.newSearchData = a8;
                SkipList.head = null;
                this.initialise();
                return;
            }
            int i5 = i3 % 2;
            if(i5 != 0)
            {
                int[] a9 = this.searchData;
                int[] a10 = this.insertData;
                int i6 = a10[i3];
                a9[i3] = i6;
            }
            else
            {
                int[] a11 = this.searchData;
                int[] a12 = this.insertData;
                int i7 = a12[i3];
                int i8 = i7 + 2;
                a11[i3] = i8;
            }
            int i9 = i3 + 1;
            i3 = i9;
        }
    }
    
    public void reInitialise(Object a)
    {
        int[] a0 = this.newInsertData;
        this.insertData = a0;
        int[] a1 = this.newSearchData;
        this.searchData = a1;
        int i = this.executingMode;
        if(i == 11)
        {
            this.savedSkipList = null;
        }
        SkipNode a2 = this.savedSkipList;
        SkipList.head = a2;
        this.insertPos = 0;
        int[] a3 = this.insertData;
        int i0 = a3.length;
        java.awt.Color[] a4 = new java.awt.Color[i0];
        this.insertDataColor = a4;
        int i1 = 0;
        while(true)
        {
            int[] a5 = this.insertData;
            int i2 = a5.length;
            if(i1 >= i2)
            {
                break;
            }
            else
            {
                java.awt.Color[] a6 = this.insertDataColor;
                java.awt.Color a7 = SkipList.INSERT_ACTIVE_COLOR;
                a6[i1] = a7;
                int i3 = i1 + 1;
                i1 = i3;
            }
        }
        com.cim.AIA.ElementArray a8 = new com.cim.AIA.ElementArray(0, 0);
        this.insertArray = a8;
        com.cim.AIA.ElementArray a9 = this.insertArray;
        a9.setColumGap(0);
        com.cim.AIA.ElementArray a10 = this.insertArray;
        a10.setElementWidth(20);
        int i4 = 0;
        while(true)
        {
            int[] a11 = this.insertData;
            int i5 = a11.length;
            if(i4 >= i5)
            {
                break;
            }
            else
            {
                int[] a12 = this.insertData;
                int i6 = a12[i4];
                Integer a13 = new Integer(i6);
                com.cim.AIA.Node a14 = new com.cim.AIA.Node((Object)a13, i4);
                java.awt.Color[] a15 = this.insertDataColor;
                java.awt.Color a16 = a15[i4];
                a14.setBackgroundColor(a16);
                java.awt.Color a17 = SkipList.INSERT_HIGHLIGHT_COLOR;
                a14.setHighlightColor(a17);
                java.awt.Color a18 = SkipList.TEXT_COLOR;
                a14.setTextColor(a18);
                com.cim.AIA.ElementArray a19 = this.insertArray;
                a19.setValue(i4, (com.cim.AIA.Element)a14);
                int i7 = i4 + 1;
                i4 = i7;
            }
        }
        int[] a20 = this.searchData;
        int i8 = a20.length;
        java.awt.Color[] a21 = new java.awt.Color[i8];
        this.searchDataColor = a21;
        int i9 = 0;
        while(true)
        {
            int[] a22 = this.searchData;
            int i10 = a22.length;
            if(i9 >= i10)
            {
                break;
            }
            else
            {
                java.awt.Color[] a23 = this.searchDataColor;
                java.awt.Color a24 = SkipList.SEARCH_ACTIVE_COLOR;
                a23[i9] = a24;
                int i11 = i9 + 1;
                i9 = i11;
            }
        }
        com.cim.AIA.ElementArray a25 = new com.cim.AIA.ElementArray(0, 0);
        this.searchArray = a25;
        com.cim.AIA.ElementArray a26 = this.searchArray;
        a26.setColumGap(0);
        com.cim.AIA.ElementArray a27 = this.searchArray;
        a27.setElementWidth(20);
        int i12 = 0;
        while(true)
        {
            int[] a28 = this.searchData;
            int i13 = a28.length;
            if(i12 >= i13)
            {
                this.initialise();
                return;
            }
            else
            {
                int[] a29 = this.searchData;
                int i14 = a29[i12];
                Integer a30 = new Integer(i14);
                com.cim.AIA.Node a31 = new com.cim.AIA.Node((Object)a30, i12);
                java.awt.Color[] a32 = this.searchDataColor;
                java.awt.Color a33 = a32[i12];
                a31.setBackgroundColor(a33);
                java.awt.Color a34 = SkipList.SEARCH_HIGHLIGHT_COLOR;
                a31.setHighlightColor(a34);
                java.awt.Color a35 = SkipList.TEXT_COLOR;
                a31.setTextColor(a35);
                com.cim.AIA.ElementArray a36 = this.searchArray;
                a36.setValue(i12, (com.cim.AIA.Element)a31);
                int i15 = i12 + 1;
                i12 = i15;
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
                int[] a8 = this.insertData;
                int i4 = a8[i0];
                int i5 = i4 + 2;
                a7[i0] = i5;
            }
            int i6 = i0 + 1;
            i0 = i6;
        }
    }
    
    private void initialise()
    {
        this.tailptrNode = null;
        this.ptrNode = null;
    }
    
    protected void initialiseMethods(String s, com.cim.AIA.MethodSelectable a)
    {
        Object a0 = a;
        String s0 = SkipList.BUILD_MODE_LABEL;
        String s1 = localization.Messages.getString("SkipList.3");
        com.cim.AIA.Logger a1 = this.getLogger();
        com.cim.AIA.BreakPoint a2 = this.getBreakPoint();
        com.cim.AIA.MethodSelection a3 = new com.cim.AIA.MethodSelection(s0, s, s1, "3.1", a1, a2);
        String s2 = SkipList.SEARCH_MODE_LABEL;
        String s3 = localization.Messages.getString("SkipList.5");
        com.cim.AIA.Logger a4 = this.getLogger();
        com.cim.AIA.BreakPoint a5 = this.getBreakPoint();
        com.cim.AIA.MethodSelection a6 = new com.cim.AIA.MethodSelection(s2, s, s3, "5", a4, a5);
        String s4 = SkipList.DELETE_MODE_LABEL;
        String s5 = localization.Messages.getString("SkipList.7");
        com.cim.AIA.Logger a7 = this.getLogger();
        com.cim.AIA.BreakPoint a8 = this.getBreakPoint();
        com.cim.AIA.MethodSelection a9 = new com.cim.AIA.MethodSelection(s4, s, s5, "7", a7, a8);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelection(a3, true);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelection(a6, false);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelectionListener((com.cim.AIA.MethodSelectionListener)this);
    }
    
    public void processMethodSelectionEvent(com.cim.AIA.MethodSelectionEvent a)
    {
        String s = SkipList.BUILD_MODE_LABEL;
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
            String s1 = SkipList.SEARCH_MODE_LABEL;
            String s2 = a.getMethodName();
            int i0 = s1.compareTo(s2);
            if(i0 == 0)
            {
                this.nextMode = 12;
            }
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
                    java.awt.Color a9 = SkipList.INSERT_HIGHLIGHT_COLOR;
                    a8.setHighlightColor(a9);
                    com.cim.AIA.ElementArray a10 = this.insertArray;
                    com.cim.AIA.Element a11 = a10.getElement(i);
                    com.cim.AIA.Node dummy1 = (com.cim.AIA.Node)a11;
                    com.cim.AIA.Node a12 = (com.cim.AIA.Node)a11;
                    java.awt.Color a13 = SkipList.TEXT_COLOR;
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
                    java.awt.Color a9 = SkipList.SEARCH_HIGHLIGHT_COLOR;
                    a8.setHighlightColor(a9);
                    com.cim.AIA.ElementArray a10 = this.searchArray;
                    com.cim.AIA.Element a11 = a10.getElement(i);
                    com.cim.AIA.Node dummy1 = (com.cim.AIA.Node)a11;
                    com.cim.AIA.Node a12 = (com.cim.AIA.Node)a11;
                    java.awt.Color a13 = SkipList.TEXT_COLOR;
                    a12.setTextColor(a13);
                    int i1 = i + 1;
                    i = i1;
                }
            }
        }
        com.cim.AIA.ElementArray a14 = this.searchArray;
        return a14;
    }
    
    public SkipNode getHead()
    {
        SkipNode a = SkipList.head;
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
    
    public SkipNode getNewElement()
    {
        SkipNode a = this.newElem;
        return a;
    }
    
    public SkipNode getFinalNewElement()
    {
        SkipNode a = this.newElement;
        return a;
    }
    
    public com.cim.AIA.Node getDataNode()
    {
        com.cim.AIA.Node a = this.dataNode;
        return a;
    }
    
    public Integer getDataItem()
    {
        Integer a = this.dataItem;
        return a;
    }
    
    public Integer getNextData()
    {
        Integer a = this.nextData;
        return a;
    }
    
    public boolean getNextNull()
    {
        int i = this.nextNull?1:0;
        return i != 0;
    }
    
    public int getLevels()
    {
        int i = SkipList.levels;
        return i;
    }
    
    public int getIPointer()
    {
        int i = this.iPointer;
        return i;
    }
    
    public int getJPointer()
    {
        int i = this.jPointer;
        return i;
    }
    
    public int getMaxLevel()
    {
        int i = this.maxLevel;
        return i;
    }
    
    public int getNewNodeLevel()
    {
        int i = this.newNodeLevel;
        return i;
    }
    
    public boolean getDataCondition()
    {
        int i = this.dataCondition?1:0;
        return i != 0;
    }
    
    public boolean doDisplayDataCondition()
    {
        int i = this.displayDataCondition?1:0;
        return i != 0;
    }
    
    public boolean doDrawBoxLevel()
    {
        int i = this.drawBoxLevel?1:0;
        return i != 0;
    }
    
    public boolean doDrawBoxFinalLevel()
    {
        int i = this.drawBoxFinalLevel?1:0;
        return i != 0;
    }
    
    public boolean doDrawIPointer()
    {
        int i = this.drawIPointer?1:0;
        return i != 0;
    }
    
    public boolean doDrawJPointer()
    {
        int i = this.drawJPointer?1:0;
        return i != 0;
    }
    
    public boolean doDrawNewElement()
    {
        int i = this.drawNewElement?1:0;
        return i != 0;
    }
    
    public boolean doRunTween()
    {
        int i = this.runTween?1:0;
        return i != 0;
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
    
    protected int calculateLevels()
    {
        int i = 0;
        int i0 = 0;
        int i1 = 0;
        while(true)
        {
            java.util.Random a = this.randomLevel;
            float f = a.nextFloat();
            float f0 = this.prob;
            int i2 = f < f0?-1:f == f0?0:1;
            if(i2 > 0)
            {
                break;
            }
            else
            {
                int i3 = i1 + 1;
                i1 = i3;
            }
        }
        int i4 = this.maxLevel;
        if(i1 >= i4)
        {
            i = i1;
        }
        else
        {
            int i5 = i1 + 1;
            i = i5;
        }
        int i6 = this.maxLevel;
        if(i <= i6)
        {
            i0 = i;
        }
        else
        {
            int i7 = this.maxLevel;
            int i8 = i % i7;
            i0 = i8;
        }
        return i0;
    }
    
    protected void storeState(boolean b)
    {
        if(b)
        {
            this.save();
        }
    }
    
    private void save()
    {
        SkipNode a = SkipList.head;
        this.savedSkipList = a;
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
            int i0 = SkipList.needToResetSeed?1:0;
            if(i0 != 0)
            {
                java.util.Random a = this.randomLevel;
                a.setSeed(87654321L);
                SkipList.needToResetSeed = false;
            }
            int i1 = this.executingMode;
            label3: {
                label2: {
                    if(i1 != 11)
                    {
                        break label2;
                    }
                    this.insert();
                    break label3;
                }
                int i2 = this.executingMode;
                if(i2 != 12)
                {
                    break label3;
                }
                this.setPosition("5");
                this.searchCnt = 0;
                this.backSearchCnt = 0;
                while(true)
                {
                    int i3 = this.enabled?1:0;
                    if(i3 == 0)
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
        SkipNode a = SkipList.head;
        label2: {
            SkipNode a0 = null;
            label15: {
                label16: {
                    label5: {
                        label0: {
                            if(a == null)
                            {
                                break label0;
                            }
                            int i = this.enabled?1:0;
                            label1: {
                                if(i != 0)
                                {
                                    break label1;
                                }
                                break label2;
                            }
                            int i0 = this.isBackMode?1:0;
                            label4: {
                                label3: {
                                    if(i0 == 0)
                                    {
                                        break label3;
                                    }
                                    java.util.Vector a1 = this.searchNodeVector;
                                    int i1 = this.backSearchCnt;
                                    Object a2 = a1.elementAt(i1);
                                    com.cim.AIA.Node dummy = (com.cim.AIA.Node)a2;
                                    com.cim.AIA.Node a3 = (com.cim.AIA.Node)a2;
                                    this.searchNode = a3;
                                    int i2 = this.backSearchCnt;
                                    int i3 = i2 + 1;
                                    this.backSearchCnt = i3;
                                    break label4;
                                }
                                int i4 = this.previousModeIsBack?1:0;
                                if(i4 != 0)
                                {
                                    this.searchCnt = 0;
                                    this.previousModeIsBack = false;
                                }
                                com.cim.AIA.SearchSelection.setEnabled(true);
                                while(true)
                                {
                                    com.cim.AIA.Node a4 = com.cim.AIA.SearchSelection.getNode();
                                    if(a4 != null)
                                    {
                                        break;
                                    }
                                    int i5 = this.enabled?1:0;
                                    if(i5 == 0)
                                    {
                                        break label5;
                                    }
                                    try
                                    {
                                        Thread.sleep(1000L);
                                    }
                                    catch(Exception ignoredException)
                                    {
                                    }
                                }
                                com.cim.AIA.Node a5 = com.cim.AIA.SearchSelection.getNode();
                                this.searchNode = a5;
                                java.util.Vector a6 = this.searchNodeVector;
                                com.cim.AIA.Node a7 = this.searchNode;
                                int i6 = this.searchCnt;
                                a6.insertElementAt((Object)a7, i6);
                                com.cim.AIA.SearchSelection.setEnabled(false);
                                int i7 = this.searchCnt;
                                int i8 = i7 + 1;
                                this.searchCnt = i8;
                            }
                            int i9 = this.enabled?1:0;
                            label6: {
                                if(i9 != 0)
                                {
                                    break label6;
                                }
                                break label2;
                            }
                            java.awt.Color[] a8 = this.searchDataColor;
                            com.cim.AIA.Node a9 = this.searchNode;
                            int i10 = a9.getIdentifier();
                            java.awt.Color a10 = SkipList.SEARCH_HIGHLIGHT_COLOR;
                            a8[i10] = a10;
                            com.cim.AIA.Node a11 = this.searchNode;
                            String s = a11.getDisplayString();
                            Integer a12 = Integer.valueOf(s);
                            int i11 = a12.intValue();
                            this.setPosition("5.0");
                            SkipNode a13 = SkipList.head;
                            SkipNode a14 = SkipList.head;
                            com.cim.AIA.Node[] a15 = a14.nodes;
                            com.cim.AIA.Node a16 = a15[0];
                            this.ptrNode = a16;
                            this.dataNode = null;
                            this.setPosition("5.1.1");
                            int i12 = SkipList.levels;
                            int i13 = i12 - 1;
                            a0 = a13;
                            int i14 = i13;
                            label11: while(true)
                            {
                                SkipNode a17 = null;
                                SkipNode a18 = null;
                                SkipNode a19 = a0;
                                if(i14 < 0)
                                {
                                    break;
                                }
                                else
                                {
                                    a17 = a19;
                                }
                                this.drawIPointer = true;
                                SkipNode a20 = a17;
                                this.iPointer = i14;
                                SkipNode a21 = a20;
                                this.setPosition("5.1.2.1.1");
                                SkipNode a22 = a21;
                                com.cim.AIA.Node[] a23 = a22.nodes;
                                SkipNode a24 = a22;
                                int i15 = i14 + 1;
                                com.cim.AIA.Node a25 = a23[i15];
                                SkipNode a26 = a24;
                                java.awt.Color a27 = java.awt.Color.red;
                                SkipNode a28 = a26;
                                a25.setBackgroundColor(a27);
                                SkipNode a29 = a28;
                                SkipNode[] a30 = a29.next;
                                SkipNode a31 = a29;
                                SkipNode a32 = a30[i14];
                                SkipNode a33 = a31;
                                label8: {
                                    SkipNode a34 = null;
                                    label7: {
                                        SkipNode a35 = null;
                                        SkipNode a36 = a33;
                                        SkipNode a37 = a33;
                                        if(a32 == null)
                                        {
                                            a35 = a37;
                                        }
                                        else
                                        {
                                            a34 = a36;
                                            break label7;
                                        }
                                        this.nextData = null;
                                        SkipNode a38 = a35;
                                        SkipNode a39 = a38;
                                        Integer a40 = new Integer(i11);
                                        SkipNode a41 = a39;
                                        this.dataItem = a40;
                                        SkipNode a42 = a41;
                                        this.nextNull = true;
                                        a18 = a42;
                                        break label8;
                                    }
                                    this.nextNull = false;
                                    SkipNode a43 = a34;
                                    SkipNode[] a44 = a43.next;
                                    SkipNode a45 = a43;
                                    SkipNode a46 = a44[i14];
                                    SkipNode a47 = a45;
                                    com.cim.AIA.Node[] a48 = a46.nodes;
                                    SkipNode a49 = a47;
                                    com.cim.AIA.Node a50 = a48[0];
                                    SkipNode a51 = a49;
                                    this.dataNode = a50;
                                    SkipNode a52 = a51;
                                    SkipNode[] a53 = a52.next;
                                    SkipNode a54 = a52;
                                    SkipNode a55 = a53[i14];
                                    SkipNode a56 = a54;
                                    Object a57 = a55.data;
                                    SkipNode a58 = a56;
                                    Integer dummy0 = (Integer)a57;
                                    Integer a59 = (Integer)a57;
                                    SkipNode a60 = a58;
                                    this.nextData = a59;
                                    SkipNode a61 = a60;
                                    SkipNode a62 = a61;
                                    Integer a63 = new Integer(i11);
                                    SkipNode a64 = a62;
                                    this.dataItem = a63;
                                    SkipNode a65 = a64;
                                    SkipNode[] a66 = a65.next;
                                    SkipNode a67 = a65;
                                    SkipNode a68 = a66[i14];
                                    SkipNode a69 = a67;
                                    Object a70 = a68.data;
                                    SkipNode a71 = a69;
                                    Integer dummy1 = (Integer)a70;
                                    Integer a72 = (Integer)a70;
                                    SkipNode a73 = a71;
                                    int i16 = a72.intValue();
                                    SkipNode a74 = a73;
                                    SkipNode a75 = a74;
                                    SkipNode a76 = a74;
                                    if(i16 >= i11)
                                    {
                                        SkipNode a77 = a76;
                                        this.dataCondition = false;
                                        SkipNode a78 = a77;
                                        a18 = a78;
                                    }
                                    else
                                    {
                                        SkipNode a79 = a75;
                                        this.dataCondition = true;
                                        SkipNode a80 = a79;
                                        a18 = a80;
                                    }
                                }
                                this.displayDataCondition = true;
                                SkipNode a81 = a18;
                                this.setPosition("5.1.2.2.1.1");
                                SkipNode a82 = a81;
                                while(true)
                                {
                                    SkipNode a83 = null;
                                    SkipNode[] a84 = a82.next;
                                    SkipNode a85 = a82;
                                    SkipNode a86 = a84[i14];
                                    SkipNode a87 = a85;
                                    label10: {
                                        SkipNode a88 = null;
                                        label9: {
                                            SkipNode a89 = null;
                                            SkipNode a90 = a87;
                                            SkipNode a91 = a87;
                                            if(a86 == null)
                                            {
                                                a88 = a91;
                                                break label9;
                                            }
                                            else
                                            {
                                                a89 = a90;
                                            }
                                            SkipNode[] a92 = a89.next;
                                            SkipNode a93 = a89;
                                            SkipNode a94 = a92[i14];
                                            SkipNode a95 = a93;
                                            Object a96 = a94.data;
                                            SkipNode a97 = a95;
                                            Integer dummy2 = (Integer)a96;
                                            Integer a98 = (Integer)a96;
                                            SkipNode a99 = a97;
                                            int i17 = a98.intValue();
                                            a83 = a99;
                                            SkipNode a100 = a83;
                                            if(i17 >= i11)
                                            {
                                                a88 = a100;
                                            }
                                            else
                                            {
                                                break label10;
                                            }
                                        }
                                        this.nextNull = false;
                                        SkipNode a101 = a88;
                                        this.displayDataCondition = false;
                                        SkipNode a102 = a101;
                                        int i18 = i14 + -1;
                                        a0 = a102;
                                        i14 = i18;
                                        continue label11;
                                    }
                                    this.nextNull = false;
                                    SkipNode[] a103 = a83.next;
                                    SkipNode a104 = a103[i14];
                                    com.cim.AIA.Node[] a105 = a104.nodes;
                                    com.cim.AIA.Node a106 = a105[0];
                                    this.dataNode = a106;
                                    SkipNode[] a107 = a83.next;
                                    SkipNode a108 = a107[i14];
                                    Object a109 = a108.data;
                                    Integer dummy3 = (Integer)a109;
                                    Integer a110 = (Integer)a109;
                                    this.nextData = a110;
                                    Integer a111 = new Integer(i11);
                                    this.dataItem = a111;
                                    SkipNode[] a112 = a83.next;
                                    SkipNode a113 = a112[i14];
                                    Object a114 = a113.data;
                                    Integer dummy4 = (Integer)a114;
                                    Integer a115 = (Integer)a114;
                                    int i19 = a115.intValue();
                                    if(i19 >= i11)
                                    {
                                        this.dataCondition = false;
                                    }
                                    else
                                    {
                                        this.dataCondition = true;
                                    }
                                    com.cim.AIA.Line[] a116 = a83.nextNodesLines;
                                    int i20 = i14 + 1;
                                    com.cim.AIA.Line a117 = a116[i20];
                                    java.awt.Color a118 = java.awt.Color.red;
                                    a117.setColor(a118);
                                    SkipNode[] a119 = a83.next;
                                    SkipNode a120 = a119[i14];
                                    com.cim.AIA.Node[] a121 = a120.nodes;
                                    com.cim.AIA.Node a122 = a121[0];
                                    this.ptrNode = a122;
                                    SkipNode[] a123 = a83.next;
                                    SkipNode a124 = a123[i14];
                                    com.cim.AIA.Node[] a125 = a124.nodes;
                                    int i21 = i14 + 1;
                                    com.cim.AIA.Node a126 = a125[i21];
                                    java.awt.Color a127 = java.awt.Color.red;
                                    a126.setBackgroundColor(a127);
                                    this.setPosition("5.1.2.2.1.2.1");
                                    SkipNode[] a128 = a124.next;
                                    SkipNode a129 = a128[i14];
                                    label13: {
                                        label12: {
                                            if(a129 != null)
                                            {
                                                break label12;
                                            }
                                            this.nextNull = true;
                                            this.nextData = null;
                                            Integer a130 = new Integer(i11);
                                            this.dataItem = a130;
                                            break label13;
                                        }
                                        this.nextNull = false;
                                        SkipNode[] a131 = a124.next;
                                        SkipNode a132 = a131[i14];
                                        com.cim.AIA.Node[] a133 = a132.nodes;
                                        com.cim.AIA.Node a134 = a133[0];
                                        this.dataNode = a134;
                                        SkipNode[] a135 = a124.next;
                                        SkipNode a136 = a135[i14];
                                        Object a137 = a136.data;
                                        Integer dummy5 = (Integer)a137;
                                        Integer a138 = (Integer)a137;
                                        this.nextData = a138;
                                        Integer a139 = new Integer(i11);
                                        this.dataItem = a139;
                                        SkipNode[] a140 = a124.next;
                                        SkipNode a141 = a140[i14];
                                        Object a142 = a141.data;
                                        Integer dummy6 = (Integer)a142;
                                        Integer a143 = (Integer)a142;
                                        int i22 = a143.intValue();
                                        if(i22 >= i11)
                                        {
                                            this.dataCondition = false;
                                        }
                                        else
                                        {
                                            this.dataCondition = true;
                                        }
                                    }
                                    this.setPosition("5.1.2.2.1.1");
                                    a82 = a124;
                                }
                            }
                            this.drawIPointer = false;
                            this.setPosition("5.1.2.3");
                            this.setPosition("5.1.3.1");
                            SkipNode[] a144 = a0.next;
                            SkipNode a145 = a144[0];
                            label14: {
                                if(a145 == null)
                                {
                                    break label14;
                                }
                                SkipNode[] a146 = a0.next;
                                SkipNode a147 = a146[0];
                                Object a148 = a147.data;
                                Integer dummy7 = (Integer)a148;
                                Integer a149 = (Integer)a148;
                                int i23 = a149.intValue();
                                if(i23 == i11)
                                {
                                    break label15;
                                }
                            }
                            this.setPosition("5.1.3.2");
                            int i24 = this.enabled?1:0;
                            if(i24 == 0)
                            {
                                break label16;
                            }
                            com.cim.AIA.Node a150 = this.searchNode;
                            com.cim.AIA.StringMarker a151 = new com.cim.AIA.StringMarker("N");
                            a150.addMarker(a151);
                            com.cim.AIA.Node a152 = this.searchNode;
                            com.cim.AIA.StringMarker a153 = new com.cim.AIA.StringMarker("o");
                            a152.addMarker(a153);
                            com.cim.AIA.Node a154 = this.searchNode;
                            com.cim.AIA.StringMarker a155 = new com.cim.AIA.StringMarker("t");
                            a154.addMarker(a155);
                            com.cim.AIA.Node a156 = this.searchNode;
                            com.cim.AIA.StringMarker a157 = new com.cim.AIA.StringMarker(" ");
                            a156.addMarker(a157);
                            com.cim.AIA.Node a158 = this.searchNode;
                            com.cim.AIA.StringMarker a159 = new com.cim.AIA.StringMarker("F");
                            a158.addMarker(a159);
                            com.cim.AIA.Node a160 = this.searchNode;
                            com.cim.AIA.StringMarker a161 = new com.cim.AIA.StringMarker("o");
                            a160.addMarker(a161);
                            com.cim.AIA.Node a162 = this.searchNode;
                            com.cim.AIA.StringMarker a163 = new com.cim.AIA.StringMarker("u");
                            a162.addMarker(a163);
                            com.cim.AIA.Node a164 = this.searchNode;
                            com.cim.AIA.StringMarker a165 = new com.cim.AIA.StringMarker("n");
                            a164.addMarker(a165);
                            com.cim.AIA.Node a166 = this.searchNode;
                            com.cim.AIA.StringMarker a167 = new com.cim.AIA.StringMarker("d");
                            a166.addMarker(a167);
                            java.awt.Color[] a168 = this.searchDataColor;
                            com.cim.AIA.Node a169 = this.searchNode;
                            int i25 = a169.getIdentifier();
                            java.awt.Color a170 = SkipList.SEARCH_DONE_COLOR;
                            a168[i25] = a170;
                            this.setPosition("5.1.3.2.1");
                            this.dataNode = null;
                            this.recolorSkipList();
                        }
                        this.dataNode = null;
                        break label2;
                    }
                    com.cim.AIA.SearchSelection.setEnabled(false);
                    break label2;
                }
                java.awt.Color[] a171 = this.searchDataColor;
                com.cim.AIA.Node a172 = this.searchNode;
                int i26 = a172.getIdentifier();
                java.awt.Color a173 = SkipList.SEARCH_ACTIVE_COLOR;
                a171[i26] = a173;
                this.recolorSkipList();
                break label2;
            }
            int i27 = this.enabled?1:0;
            if(i27 != 0)
            {
                com.cim.AIA.Line[] a174 = a0.nextNodesLines;
                com.cim.AIA.Line a175 = a174[1];
                java.awt.Color a176 = java.awt.Color.red;
                a175.setColor(a176);
                com.cim.AIA.Node a177 = this.searchNode;
                com.cim.AIA.StringMarker a178 = new com.cim.AIA.StringMarker("F");
                a177.addMarker(a178);
                com.cim.AIA.Node a179 = this.searchNode;
                com.cim.AIA.StringMarker a180 = new com.cim.AIA.StringMarker("o");
                a179.addMarker(a180);
                com.cim.AIA.Node a181 = this.searchNode;
                com.cim.AIA.StringMarker a182 = new com.cim.AIA.StringMarker("u");
                a181.addMarker(a182);
                com.cim.AIA.Node a183 = this.searchNode;
                com.cim.AIA.StringMarker a184 = new com.cim.AIA.StringMarker("n");
                a183.addMarker(a184);
                com.cim.AIA.Node a185 = this.searchNode;
                com.cim.AIA.StringMarker a186 = new com.cim.AIA.StringMarker("d");
                a185.addMarker(a186);
                java.awt.Color[] a187 = this.searchDataColor;
                com.cim.AIA.Node a188 = this.searchNode;
                int i28 = a188.getIdentifier();
                java.awt.Color a189 = SkipList.SEARCH_DONE_COLOR;
                a187[i28] = a189;
                this.setPosition("5.1.3.1.1");
                this.recolorSkipList();
            }
            else
            {
                java.awt.Color[] a190 = this.searchDataColor;
                com.cim.AIA.Node a191 = this.searchNode;
                int i29 = a191.getIdentifier();
                java.awt.Color a192 = SkipList.SEARCH_ACTIVE_COLOR;
                a190[i29] = a192;
                this.recolorSkipList();
            }
        }
    }
    
    protected void insert()
    {
        int i = this.isBackMode?1:0;
        if(i != 0)
        {
            SkipList.levels = 1;
        }
        int[] a = this.insertData;
        int i0 = a.length;
        {
            label2: {
                {
                    label0: {
                        if(i0 <= 0)
                        {
                            break label0;
                        }
                        int i1 = this.insertPos;
                        label1: {
                            if(i1 != 0)
                            {
                                break label1;
                            }
                            java.awt.Color[] a0 = this.insertDataColor;
                            java.awt.Color a1 = SkipList.INSERT_HIGHLIGHT_COLOR;
                            a0[0] = a1;
                            Integer a2 = new Integer(-1);
                            int i2 = this.maxLevel;
                            SkipNode a3 = new SkipNode((Object)a2, -1, i2);
                            SkipList.head = a3;
                            int i3 = 0;
                            while(true)
                            {
                                SkipNode a4 = SkipList.head;
                                com.cim.AIA.Node[] a5 = a4.nodes;
                                int i4 = a5.length;
                                if(i3 >= i4)
                                {
                                    break;
                                }
                                else
                                {
                                    SkipNode a6 = SkipList.head;
                                    com.cim.AIA.Node[] a7 = a6.nodes;
                                    com.cim.AIA.Node a8 = a7[i3];
                                    java.awt.Color a9 = SkipList.INSERT_DONE_COLOR;
                                    a8.setBackgroundColor(a9);
                                    SkipNode a10 = SkipList.head;
                                    com.cim.AIA.Node[] a11 = a10.nodes;
                                    com.cim.AIA.Node a12 = a11[i3];
                                    a12.setMarkersBelowValue(false);
                                    SkipNode a13 = SkipList.head;
                                    com.cim.AIA.Node[] a14 = a13.nodes;
                                    com.cim.AIA.Node a15 = a14[i3];
                                    a15.removeAllMarkers();
                                    SkipNode a16 = SkipList.head;
                                    com.cim.AIA.Node[] a17 = a16.nodes;
                                    com.cim.AIA.Node a18 = a17[i3];
                                    a18.showObject(false);
                                    int i5 = i3 + 1;
                                    i3 = i5;
                                }
                            }
                            this.setPosition("2.1");
                        }
                        int i6 = this.enabled?1:0;
                        if(i6 == 0)
                        {
                            break label2;
                        }
                        this.setPosition("3");
                        int i7 = this.insertPos;
                        int i8 = i7;
                        while(true)
                        {
                            int[] a19 = this.insertData;
                            int i9 = a19.length;
                            if(i8 >= i9)
                            {
                                break;
                            }
                            int i10 = this.enabled?1:0;
                            if(i10 != 0)
                            {
                                java.awt.Color[] a20 = this.insertDataColor;
                                java.awt.Color a21 = java.awt.Color.red;
                                a20[i8] = a21;
                                this.setPosition("3.1");
                                this.insertItem(i8);
                                int i11 = this.insertPos;
                                int i12 = i11 + 1;
                                this.insertPos = i12;
                                java.awt.Color[] a22 = this.insertDataColor;
                                java.awt.Color a23 = SkipList.INSERT_DONE_COLOR;
                                a22[i8] = a23;
                                this.setPosition("3");
                                int i13 = i8 + 1;
                                i8 = i13;
                            }
                            else
                            {
                            }
                        }
                    }
                }
            }
        }
    }
    
    protected void insertItem(int i)
    {
        int i0 = 0;
        this.setPosition("3.1.0");
        SkipNode a = SkipList.head;
        int i1 = this.maxLevel;
        SkipNode[] a0 = new SkipNode[i1];
        this.last = a0;
        SkipNode a1 = SkipList.head;
        com.cim.AIA.Node[] a2 = a1.nodes;
        com.cim.AIA.Node a3 = a2[0];
        this.ptrNode = a3;
        this.setPosition("3.1.1.1");
        int i2 = SkipList.levels;
        int i3 = i2 - 1;
        SkipNode a4 = a;
        int i4 = i3;
        label4: while(true)
        {
            SkipNode a5 = null;
            SkipNode a6 = null;
            SkipNode a7 = a4;
            if(i4 < 0)
            {
                break;
            }
            else
            {
                a5 = a7;
            }
            this.drawIPointer = true;
            SkipNode a8 = a5;
            this.iPointer = i4;
            SkipNode a9 = a8;
            this.setPosition("3.1.2.1.1");
            SkipNode a10 = a9;
            com.cim.AIA.Node[] a11 = a10.nodes;
            SkipNode a12 = a10;
            int i5 = i4 + 1;
            com.cim.AIA.Node a13 = a11[i5];
            SkipNode a14 = a12;
            java.awt.Color a15 = java.awt.Color.red;
            SkipNode a16 = a14;
            a13.setBackgroundColor(a15);
            SkipNode a17 = a16;
            SkipNode[] a18 = a17.next;
            SkipNode a19 = a17;
            SkipNode a20 = a18[i4];
            SkipNode a21 = a19;
            label1: {
                SkipNode a22 = null;
                label0: {
                    SkipNode a23 = null;
                    SkipNode a24 = a21;
                    SkipNode a25 = a21;
                    if(a20 == null)
                    {
                        a23 = a25;
                    }
                    else
                    {
                        a22 = a24;
                        break label0;
                    }
                    this.nextData = null;
                    SkipNode a26 = a23;
                    SkipNode a27 = a26;
                    int[] a28 = this.insertData;
                    SkipNode a29 = a27;
                    int i6 = a28[i];
                    SkipNode a30 = a29;
                    Integer a31 = new Integer(i6);
                    SkipNode a32 = a30;
                    this.dataItem = a31;
                    SkipNode a33 = a32;
                    this.nextNull = true;
                    a6 = a33;
                    break label1;
                }
                this.nextNull = false;
                SkipNode a34 = a22;
                SkipNode[] a35 = a34.next;
                SkipNode a36 = a34;
                SkipNode a37 = a35[i4];
                SkipNode a38 = a36;
                com.cim.AIA.Node[] a39 = a37.nodes;
                SkipNode a40 = a38;
                com.cim.AIA.Node a41 = a39[0];
                SkipNode a42 = a40;
                this.dataNode = a41;
                SkipNode a43 = a42;
                SkipNode[] a44 = a43.next;
                SkipNode a45 = a43;
                SkipNode a46 = a44[i4];
                SkipNode a47 = a45;
                Object a48 = a46.data;
                SkipNode a49 = a47;
                Integer dummy = (Integer)a48;
                Integer a50 = (Integer)a48;
                SkipNode a51 = a49;
                this.nextData = a50;
                SkipNode a52 = a51;
                SkipNode a53 = a52;
                int[] a54 = this.insertData;
                SkipNode a55 = a53;
                int i7 = a54[i];
                SkipNode a56 = a55;
                Integer a57 = new Integer(i7);
                SkipNode a58 = a56;
                this.dataItem = a57;
                SkipNode a59 = a58;
                SkipNode[] a60 = a59.next;
                SkipNode a61 = a59;
                SkipNode a62 = a60[i4];
                SkipNode a63 = a61;
                Object a64 = a62.data;
                SkipNode a65 = a63;
                Integer dummy0 = (Integer)a64;
                Integer a66 = (Integer)a64;
                SkipNode a67 = a65;
                int i8 = a66.intValue();
                SkipNode a68 = a67;
                int[] a69 = this.insertData;
                SkipNode a70 = a68;
                int i9 = a69[i];
                SkipNode a71 = a70;
                SkipNode a72 = a71;
                SkipNode a73 = a71;
                if(i8 >= i9)
                {
                    SkipNode a74 = a73;
                    this.dataCondition = false;
                    SkipNode a75 = a74;
                    a6 = a75;
                }
                else
                {
                    SkipNode a76 = a72;
                    this.dataCondition = true;
                    SkipNode a77 = a76;
                    a6 = a77;
                }
            }
            this.displayDataCondition = true;
            SkipNode a78 = a6;
            this.setPosition("3.1.2.2.1.1");
            SkipNode a79 = a78;
            while(true)
            {
                SkipNode a80 = null;
                SkipNode[] a81 = a79.next;
                SkipNode a82 = a79;
                SkipNode a83 = a81[i4];
                SkipNode a84 = a82;
                label3: {
                    SkipNode a85 = null;
                    label2: {
                        SkipNode a86 = null;
                        SkipNode a87 = a84;
                        SkipNode a88 = a84;
                        if(a83 == null)
                        {
                            a85 = a88;
                            break label2;
                        }
                        else
                        {
                            a86 = a87;
                        }
                        SkipNode[] a89 = a86.next;
                        SkipNode a90 = a86;
                        SkipNode a91 = a89[i4];
                        SkipNode a92 = a90;
                        Object a93 = a91.data;
                        SkipNode a94 = a92;
                        Integer dummy1 = (Integer)a93;
                        Integer a95 = (Integer)a93;
                        SkipNode a96 = a94;
                        int i10 = a95.intValue();
                        SkipNode a97 = a96;
                        int[] a98 = this.insertData;
                        SkipNode a99 = a97;
                        int i11 = a98[i];
                        a80 = a99;
                        SkipNode a100 = a80;
                        if(i10 >= i11)
                        {
                            a85 = a100;
                        }
                        else
                        {
                            break label3;
                        }
                    }
                    this.nextNull = false;
                    SkipNode a101 = a85;
                    this.dataNode = null;
                    SkipNode a102 = a101;
                    this.displayDataCondition = false;
                    SkipNode a103 = a102;
                    SkipNode[] a104 = this.last;
                    SkipNode a105 = a103;
                    a104[i4] = a105;
                    SkipNode a106 = a105;
                    SkipNode[] a107 = this.last;
                    SkipNode a108 = a106;
                    SkipNode a109 = a107[i4];
                    SkipNode a110 = a108;
                    com.cim.AIA.Node[] a111 = a109.nodes;
                    SkipNode a112 = a110;
                    int i12 = i4 + 1;
                    com.cim.AIA.Node a113 = a111[i12];
                    SkipNode a114 = a112;
                    java.awt.Color a115 = java.awt.Color.red;
                    SkipNode a116 = a114;
                    a113.setBackgroundColor(a115);
                    SkipNode a117 = a116;
                    SkipNode[] a118 = this.last;
                    SkipNode a119 = a117;
                    SkipNode a120 = a118[i4];
                    SkipNode a121 = a119;
                    com.cim.AIA.Node[] a122 = a120.nodes;
                    SkipNode a123 = a121;
                    int i13 = i4 + 1;
                    com.cim.AIA.Node a124 = a122[i13];
                    SkipNode a125 = a123;
                    a124.highlight();
                    SkipNode a126 = a125;
                    this.setPosition("3.1.2.2.1.4");
                    SkipNode a127 = a126;
                    int i14 = i4 + -1;
                    a4 = a127;
                    i4 = i14;
                    continue label4;
                }
                this.nextNull = false;
                SkipNode[] a128 = a80.next;
                SkipNode a129 = a128[i4];
                com.cim.AIA.Node[] a130 = a129.nodes;
                com.cim.AIA.Node a131 = a130[0];
                this.dataNode = a131;
                SkipNode[] a132 = a80.next;
                SkipNode a133 = a132[i4];
                Object a134 = a133.data;
                Integer dummy2 = (Integer)a134;
                Integer a135 = (Integer)a134;
                this.nextData = a135;
                int[] a136 = this.insertData;
                int i15 = a136[i];
                Integer a137 = new Integer(i15);
                this.dataItem = a137;
                SkipNode[] a138 = a80.next;
                SkipNode a139 = a138[i4];
                Object a140 = a139.data;
                Integer dummy3 = (Integer)a140;
                Integer a141 = (Integer)a140;
                int i16 = a141.intValue();
                int[] a142 = this.insertData;
                int i17 = a142[i];
                if(i16 >= i17)
                {
                    this.dataCondition = false;
                }
                else
                {
                    this.dataCondition = true;
                }
                com.cim.AIA.Line[] a143 = a80.nextNodesLines;
                int i18 = i4 + 1;
                com.cim.AIA.Line a144 = a143[i18];
                java.awt.Color a145 = java.awt.Color.red;
                a144.setColor(a145);
                SkipNode[] a146 = a80.next;
                SkipNode a147 = a146[i4];
                com.cim.AIA.Node[] a148 = a147.nodes;
                com.cim.AIA.Node a149 = a148[0];
                this.ptrNode = a149;
                SkipNode[] a150 = a80.next;
                SkipNode a151 = a150[i4];
                com.cim.AIA.Node[] a152 = a151.nodes;
                int i19 = i4 + 1;
                com.cim.AIA.Node a153 = a152[i19];
                java.awt.Color a154 = java.awt.Color.red;
                a153.setBackgroundColor(a154);
                this.setPosition("3.1.2.2.1.2.1");
                SkipNode[] a155 = a151.next;
                SkipNode a156 = a155[i4];
                label6: {
                    label5: {
                        if(a156 != null)
                        {
                            break label5;
                        }
                        this.nextNull = true;
                        this.nextData = null;
                        int[] a157 = this.insertData;
                        int i20 = a157[i];
                        Integer a158 = new Integer(i20);
                        this.dataItem = a158;
                        break label6;
                    }
                    this.nextNull = false;
                    SkipNode[] a159 = a151.next;
                    SkipNode a160 = a159[i4];
                    com.cim.AIA.Node[] a161 = a160.nodes;
                    com.cim.AIA.Node a162 = a161[0];
                    this.dataNode = a162;
                    SkipNode[] a163 = a151.next;
                    SkipNode a164 = a163[i4];
                    Object a165 = a164.data;
                    Integer dummy4 = (Integer)a165;
                    Integer a166 = (Integer)a165;
                    this.nextData = a166;
                    int[] a167 = this.insertData;
                    int i21 = a167[i];
                    Integer a168 = new Integer(i21);
                    this.dataItem = a168;
                    SkipNode[] a169 = a151.next;
                    SkipNode a170 = a169[i4];
                    Object a171 = a170.data;
                    Integer dummy5 = (Integer)a171;
                    Integer a172 = (Integer)a171;
                    int i22 = a172.intValue();
                    int[] a173 = this.insertData;
                    int i23 = a173[i];
                    if(i22 >= i23)
                    {
                        this.dataCondition = false;
                    }
                    else
                    {
                        this.dataCondition = true;
                    }
                }
                this.setPosition("3.1.2.2.1.1");
                a79 = a151;
            }
        }
        this.drawIPointer = false;
        this.setPosition("3.1.2.3");
        int i24 = this.calculateLevels();
        this.newNodeLevel = i24;
        int i25 = this.newNodeLevel;
        int i26 = SkipList.levels;
        label7: {
            if(i25 <= i26)
            {
                i0 = i;
                break label7;
            }
            if(i == 0)
            {
                int i27 = SkipList.levels;
                this.newNodeLevel = i27;
                i0 = 0;
            }
            else
            {
                int i28 = SkipList.levels;
                int i29 = i28 + 1;
                SkipList.levels = i29;
                this.newNodeLevel = i29;
                SkipNode[] a174 = this.last;
                int i30 = this.newNodeLevel;
                int i31 = i30 - 1;
                SkipNode a175 = SkipList.head;
                a174[i31] = a175;
                SkipNode[] a176 = this.last;
                int i32 = this.newNodeLevel;
                int i33 = i32 - 1;
                SkipNode a177 = a176[i33];
                com.cim.AIA.Node[] a178 = a177.nodes;
                int i34 = this.newNodeLevel;
                com.cim.AIA.Node a179 = a178[i34];
                java.awt.Color a180 = java.awt.Color.red;
                a179.setBackgroundColor(a180);
                SkipNode[] a181 = this.last;
                int i35 = this.newNodeLevel;
                int i36 = i35 - 1;
                SkipNode a182 = a181[i36];
                com.cim.AIA.Node[] a183 = a182.nodes;
                int i37 = this.newNodeLevel;
                com.cim.AIA.Node a184 = a183[i37];
                a184.highlight();
                i0 = i;
            }
        }
        this.drawBoxLevel = true;
        this.setPosition("3.1.3.1.1");
        this.drawBoxLevel = false;
        this.drawBoxFinalLevel = true;
        int[] a185 = this.insertData;
        int i38 = a185[i0];
        Integer a186 = new Integer(i38);
        int i39 = this.newNodeLevel;
        SkipNode a187 = new SkipNode((Object)a186, i0, i39);
        this.newElement = a187;
        SkipNode a188 = this.newElement;
        com.cim.AIA.Node[] a189 = a188.nodes;
        com.cim.AIA.Node a190 = a189[0];
        java.awt.Color a191 = SkipList.LIST_ACTIVE_COLOR;
        a190.setBackgroundColor(a191);
        int i40 = 1;
        while(true)
        {
            SkipNode a192 = this.newElement;
            com.cim.AIA.Node[] a193 = a192.nodes;
            int i41 = a193.length;
            if(i40 >= i41)
            {
                break;
            }
            else
            {
                SkipNode a194 = this.newElement;
                com.cim.AIA.Node[] a195 = a194.nodes;
                com.cim.AIA.Node a196 = a195[i40];
                java.awt.Color a197 = SkipList.LIST_NULL_COLOR;
                a196.setBackgroundColor(a197);
                int i42 = i40 + 1;
                i40 = i42;
            }
        }
        int i43 = this.newNodeLevel;
        SkipNode a198 = new SkipNode((Object)a186, i0, i43);
        this.newElem = a198;
        SkipNode a199 = this.newElem;
        com.cim.AIA.Node[] a200 = a199.nodes;
        com.cim.AIA.Node a201 = a200[0];
        java.awt.Color a202 = SkipList.LIST_ACTIVE_COLOR;
        a201.setBackgroundColor(a202);
        int i44 = 1;
        while(true)
        {
            SkipNode a203 = this.newElem;
            com.cim.AIA.Node[] a204 = a203.nodes;
            int i45 = a204.length;
            if(i44 >= i45)
            {
                break;
            }
            else
            {
                SkipNode a205 = this.newElem;
                com.cim.AIA.Node[] a206 = a205.nodes;
                com.cim.AIA.Node a207 = a206[i44];
                java.awt.Color a208 = java.awt.Color.white;
                a207.setBackgroundColor(a208);
                int i46 = i44 + 1;
                i44 = i46;
            }
        }
        this.drawNewElement = true;
        this.setPosition("3.1.3.2.1");
        int i47 = this.enabled?1:0;
        label9: {
            label8: {
                if(i47 != 0)
                {
                    break label8;
                }
                this.ptrNode = null;
                this.drawBoxFinalLevel = false;
                this.drawNewElement = false;
                this.recolorSkipList();
                break label9;
            }
            SkipNode a209 = this.newElement;
            com.cim.AIA.Node[] a210 = a209.nodes;
            com.cim.AIA.Node a211 = a210[0];
            a211.setIsVisible(false);
            int i48 = 0;
            while(true)
            {
                int i49 = this.newNodeLevel;
                if(i48 >= i49)
                {
                    break;
                }
                else
                {
                    SkipNode a212 = this.newElement;
                    SkipNode[] a213 = a212.next;
                    SkipNode[] a214 = this.last;
                    SkipNode a215 = a214[i48];
                    SkipNode[] a216 = a215.next;
                    SkipNode a217 = a216[i48];
                    a213[i48] = a217;
                    SkipNode a218 = this.newElement;
                    com.cim.AIA.Node[] a219 = a218.nodes;
                    int i50 = i48 + 1;
                    com.cim.AIA.Node a220 = a219[i50];
                    a220.setIsVisible(false);
                    SkipNode a221 = this.newElement;
                    com.cim.AIA.Line[] a222 = a221.nextNodesLines;
                    int i51 = i48 + 1;
                    com.cim.AIA.Line a223 = a222[i51];
                    java.awt.Color a224 = java.awt.Color.white;
                    a223.setColor(a224);
                    SkipNode a225 = this.newElement;
                    com.cim.AIA.Line[] a226 = a225.nextNodesLines;
                    int i52 = i48 + 1;
                    com.cim.AIA.Line a227 = a226[i52];
                    a227.setArrowHeadWidth(0);
                    SkipNode[] a228 = this.last;
                    SkipNode a229 = a228[i48];
                    SkipNode[] a230 = a229.next;
                    SkipNode a231 = this.newElement;
                    a230[i48] = a231;
                    SkipNode[] a232 = this.last;
                    SkipNode a233 = a232[i48];
                    com.cim.AIA.Line[] a234 = a233.nextNodesLines;
                    int i53 = i48 + 1;
                    com.cim.AIA.Line a235 = a234[i53];
                    java.awt.Color a236 = java.awt.Color.white;
                    a235.setColor(a236);
                    SkipNode[] a237 = this.last;
                    SkipNode a238 = a237[i48];
                    com.cim.AIA.Line[] a239 = a238.nextNodesLines;
                    int i54 = i48 + 1;
                    com.cim.AIA.Line a240 = a239[i54];
                    a240.setArrowHeadWidth(0);
                    int i55 = i48 + 1;
                    i48 = i55;
                }
            }
            int i56 = this.enabled?1:0;
            label10: {
                if(i56 != 0)
                {
                    break label10;
                }
                this.ptrNode = null;
                this.drawBoxFinalLevel = false;
                this.drawNewElement = false;
                this.recolorSkipList();
                break label9;
            }
            int i57 = 0;
            while(true)
            {
                int i58 = 0;
                int i59 = 0;
                int i60 = this.newNodeLevel;
                if(i57 >= i60)
                {
                    break;
                }
                this.drawJPointer = true;
                this.jPointer = i57;
                this.setPosition("3.1.3.3.1");
                label11: {
                    if(i57 != 0)
                    {
                        i58 = i57;
                        break label11;
                    }
                    SkipNode a241 = this.newElement;
                    com.cim.AIA.Node[] a242 = a241.nodes;
                    com.cim.AIA.Node a243 = a242[0];
                    a243.setIsVisible(true);
                    int i61 = 0;
                    while(true)
                    {
                        int i62 = this.newNodeLevel;
                        if(i61 >= i62)
                        {
                            i58 = 0;
                            break;
                        }
                        else
                        {
                            SkipNode a244 = this.newElement;
                            com.cim.AIA.Node[] a245 = a244.nodes;
                            int i63 = i61 + 1;
                            com.cim.AIA.Node a246 = a245[i63];
                            java.awt.Color a247 = java.awt.Color.white;
                            a246.setBackgroundColor(a247);
                            SkipNode a248 = this.newElement;
                            com.cim.AIA.Node[] a249 = a248.nodes;
                            int i64 = i61 + 1;
                            com.cim.AIA.Node a250 = a249[i64];
                            a250.setIsVisible(true);
                            int i65 = i61 + 1;
                            i61 = i65;
                        }
                    }
                }
                SkipNode a251 = this.newElement;
                com.cim.AIA.Node[] a252 = a251.nodes;
                int i66 = i58 + 1;
                com.cim.AIA.Node a253 = a252[i66];
                java.awt.Color a254 = java.awt.Color.red;
                a253.setBackgroundColor(a254);
                SkipNode a255 = this.newElement;
                com.cim.AIA.Line[] a256 = a255.nextNodesLines;
                int i67 = i58 + 1;
                com.cim.AIA.Line a257 = a256[i67];
                java.awt.Color a258 = java.awt.Color.red;
                a257.setColor(a258);
                SkipNode a259 = this.newElement;
                com.cim.AIA.Line[] a260 = a259.nextNodesLines;
                int i68 = i58 + 1;
                com.cim.AIA.Line a261 = a260[i68];
                a261.setArrowHeadWidth(5);
                SkipNode a262 = this.newElement;
                com.cim.AIA.Line[] a263 = a262.nextNodesLines;
                int i69 = i58 + 1;
                com.cim.AIA.Line a264 = a263[i69];
                a264.showAsDotted(false);
                SkipNode[] a265 = this.last;
                SkipNode a266 = a265[i58];
                com.cim.AIA.Line[] a267 = a266.nextNodesLines;
                int i70 = i58 + 1;
                com.cim.AIA.Line a268 = a267[i70];
                java.awt.Color a269 = java.awt.Color.red;
                a268.setColor(a269);
                SkipNode[] a270 = this.last;
                SkipNode a271 = a270[i58];
                com.cim.AIA.Line[] a272 = a271.nextNodesLines;
                int i71 = i58 + 1;
                com.cim.AIA.Line a273 = a272[i71];
                a273.setArrowHeadWidth(5);
                SkipNode[] a274 = this.last;
                SkipNode a275 = a274[i58];
                com.cim.AIA.Line[] a276 = a275.nextNodesLines;
                int i72 = i58 + 1;
                com.cim.AIA.Line a277 = a276[i72];
                a277.showAsDotted(false);
                if(i58 != 0)
                {
                    this.runTween = false;
                    i59 = i58;
                }
                else
                {
                    this.runTween = true;
                    i59 = 0;
                }
                this.setPosition("3.1.3.3.2.1");
                int i73 = this.runTween?1:0;
                if(i73 != 0)
                {
                    this.runTween = false;
                }
                int i74 = i59 + 1;
                i57 = i74;
            }
            this.drawJPointer = false;
            this.drawBoxFinalLevel = false;
            this.drawNewElement = false;
            int i75 = this.enabled?1:0;
            label12: {
                if(i75 != 0)
                {
                    break label12;
                }
                this.ptrNode = null;
                this.drawBoxFinalLevel = false;
                this.drawNewElement = false;
                this.recolorSkipList();
                break label9;
            }
            this.recolorSkipList();
            SkipNode a278 = this.newElement;
            com.cim.AIA.Node[] a279 = a278.nodes;
            com.cim.AIA.Node a280 = a279[0];
            java.awt.Color a281 = SkipList.LIST_DONE_COLOR;
            a280.setBackgroundColor(a281);
            this.setPosition("3.1.3.3.3");
            int i76 = this.enabled?1:0;
            if(i76 != 0)
            {
                this.ptrNode = null;
            }
            else
            {
                this.ptrNode = null;
                this.drawBoxFinalLevel = false;
                this.drawNewElement = false;
                this.recolorSkipList();
            }
        }
    }
    
    protected void recolorSkipList()
    {
        int i = 0;
        while(true)
        {
            int i0 = this.newNodeLevel;
            if(i >= i0)
            {
                break;
            }
            else
            {
                SkipNode[] a = this.last;
                SkipNode a0 = a[i];
                com.cim.AIA.Node[] a1 = a0.nodes;
                int i1 = i + 1;
                com.cim.AIA.Node a2 = a1[i1];
                a2.unHighlight();
                int i2 = i + 1;
                i = i2;
            }
        }
        SkipNode a3 = SkipList.head;
        SkipNode[] a4 = a3.next;
        SkipNode a5 = a4[0];
        SkipNode a6 = a5;
        while(true)
        {
            if(a6 == null)
            {
                break;
            }
            int i3 = 1;
            while(true)
            {
                com.cim.AIA.Node[] a7 = a6.nodes;
                int i4 = a7.length;
                if(i3 >= i4)
                {
                    SkipNode[] a8 = a6.next;
                    SkipNode a9 = a8[0];
                    a6 = a9;
                }
                else
                {
                    com.cim.AIA.Node[] a10 = a6.nodes;
                    com.cim.AIA.Node a11 = a10[i3];
                    java.awt.Color a12 = SkipList.LIST_NULL_COLOR;
                    a11.setBackgroundColor(a12);
                    com.cim.AIA.Line[] a13 = a6.nextNodesLines;
                    com.cim.AIA.Line a14 = a13[i3];
                    java.awt.Color a15 = SkipList.TEXT_COLOR;
                    a14.setColor(a15);
                    int i5 = i3 + 1;
                    i3 = i5;
                }
            }
        }
        int i6 = 1;
        while(true)
        {
            SkipNode a16 = SkipList.head;
            com.cim.AIA.Node[] a17 = a16.nodes;
            int i7 = a17.length;
            if(i6 >= i7)
            {
                return;
            }
            else
            {
                SkipNode a18 = SkipList.head;
                com.cim.AIA.Node[] a19 = a18.nodes;
                com.cim.AIA.Node a20 = a19[i6];
                java.awt.Color a21 = SkipList.INSERT_DONE_COLOR;
                a20.setBackgroundColor(a21);
                SkipNode a22 = SkipList.head;
                com.cim.AIA.Line[] a23 = a22.nextNodesLines;
                com.cim.AIA.Line a24 = a23[i6];
                java.awt.Color a25 = SkipList.TEXT_COLOR;
                a24.setColor(a25);
                int i8 = i6 + 1;
                i6 = i8;
            }
        }
    }
    
    public void controlStep(com.cim.AIA.ControlEvent a)
    {
        this.isBackMode = false;
    }
    
    public void controlBack(com.cim.AIA.ControlEvent a)
    {
        this.isBackMode = true;
        SkipList.needToResetSeed = true;
    }
    
    public void controlPause(com.cim.AIA.ControlEvent a)
    {
        this.isBackMode = false;
    }
    
    public void controlReset(com.cim.AIA.ControlEvent a)
    {
        this.isBackMode = false;
        SkipList.needToResetSeed = true;
    }
    
    public void controlRestart(com.cim.AIA.ControlEvent a)
    {
        this.isBackMode = false;
        SkipList.needToResetSeed = true;
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
        SkipList.TEXT_COLOR = a;
        java.awt.Color a0 = new java.awt.Color(0, 210, 0);
        SkipList.INSERT_HIGHLIGHT_COLOR = a0;
        java.awt.Color a1 = new java.awt.Color(64, 191, 64);
        SkipList.INSERT_ACTIVE_COLOR = a1;
        java.awt.Color a2 = new java.awt.Color(96, 127, 96);
        SkipList.INSERT_DONE_COLOR = a2;
        java.awt.Color a3 = new java.awt.Color(64, 255, 255);
        SkipList.SEARCH_HIGHLIGHT_COLOR = a3;
        java.awt.Color a4 = new java.awt.Color(64, 191, 191);
        SkipList.SEARCH_ACTIVE_COLOR = a4;
        java.awt.Color a5 = new java.awt.Color(0, 127, 127);
        SkipList.SEARCH_DONE_COLOR = a5;
        java.awt.Color a6 = java.awt.Color.pink;
        SkipList.LIST_ACTIVE_COLOR = a6;
        java.awt.Color a7 = java.awt.Color.orange;
        SkipList.LIST_NULL_COLOR = a7;
        java.awt.Color a8 = SkipList.INSERT_HIGHLIGHT_COLOR;
        SkipList.LIST_DONE_COLOR = a8;
        java.awt.Color a9 = java.awt.Color.green;
        SkipList.NODE_HIGHLIGHT_COLOR = a9;
        java.awt.Color a10 = java.awt.Color.black;
        SkipList.NODE_BORDER_COLOR = a10;
        String s = localization.Messages.getString("SkipList.0");
        SkipList.BUILD_MODE_LABEL = s;
        String s0 = localization.Messages.getString("SkipList.1");
        SkipList.SEARCH_MODE_LABEL = s0;
        String s1 = localization.Messages.getString("SkipList.2");
        SkipList.DELETE_MODE_LABEL = s1;
        SkipList.levels = 1;
        SkipList.needToResetSeed = false;
    }
}