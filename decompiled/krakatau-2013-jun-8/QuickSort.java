public class QuickSort extends com.cim.AIA.Algorithm implements com.cim.AIA.ColorSelectionListener {
    protected static String[] duplicateLabels;
    final public static String I_MARKER = "i";
    final public static String J_MARKER = "J";
    final protected static String selectionLabel;
    final protected static String highlightLabel;
    final protected static String ACTIVE;
    final protected static String PROCESSED;
    final protected static String FINISHED;
    final protected static String WAITING;
    final protected static String PARTITION;
    protected static java.awt.Color selectionColor;
    protected static java.awt.Color BACKGROUND;
    protected static java.awt.Color TEXT_COLOR;
    public static java.awt.Color PARTITION_COLOR;
    public static java.awt.Color WAITING_COLOR;
    public static java.awt.Color HIGHLIGHT_COLOR;
    public static java.awt.Color DOING_CHILDREN_COLOR;
    public static java.awt.Color ACTIVE_COLOR;
    public static java.awt.Color FINISHED_COLOR;
    final public static int MARKER_NOT_READY = -10;
    final public static int RIGHT_PARTITION = 10;
    final public static int RANDOM_PARTITION = 11;
    final public static int MIDDLE_OF_THREE_RANDOM = 12;
    final public static int MIDDLE_OF_THREE = 13;
    final protected static boolean DEBUG_MODE = 0;
    protected static int currentPartitionMethod;
    final protected static long aSeed = 12345678L;
    protected static java.util.Vector swapRequests;
    protected static java.util.Vector questions;
    protected static MyElementArrayQS elementArray;
    protected static java.util.Hashtable highlightedElements;
    protected static boolean needtoresetSeed;
    protected static double percentageChanceForQuestion;
    protected static double percentageChanceForSwapQuestion;
    protected int columSpacing;
    protected int columWidth;
    protected int xTextBuffer;
    protected int yTextBuffer;
    protected int xBoxPadding;
    protected int yBoxPadding;
    protected java.util.Random random;
    protected int[] data;
    protected int left;
    protected int right;
    protected int P;
    protected int PPosition;
    protected int i;
    protected int j;
    protected int numberOfComparisions;
    protected int numberOfSwaps;
    protected QuickSort leftChild;
    protected QuickSort rightChild;
    protected boolean started;
    protected boolean active;
    protected boolean processed;
    protected boolean doingChildren;
    private boolean drawRectangles;
    
    public QuickSort(com.cim.AIA.AlgorithmThread a, int[] a0, int i, int i0)
    {
        super(a, (Object)a0);
        this.columSpacing = 12;
        this.columWidth = 7;
        this.xTextBuffer = 20;
        this.yTextBuffer = 2;
        this.xBoxPadding = 4;
        this.yBoxPadding = 2;
        java.util.Random a1 = new java.util.Random(12345678L);
        this.random = a1;
        this.P = -10;
        this.PPosition = -10;
        this.i = -10;
        this.j = -10;
        this.numberOfComparisions = 0;
        this.numberOfSwaps = 0;
        this.started = false;
        this.active = false;
        this.processed = false;
        this.doingChildren = false;
        this.drawRectangles = false;
        this.data = a0;
        this.left = i;
        this.right = i0;
        this.debugMode = false;
    }
    
    public QuickSort(com.cim.AIA.AlgorithmThread a, Object a0)
    {
        super(a, a0);
        this.columSpacing = 12;
        this.columWidth = 7;
        this.xTextBuffer = 20;
        this.yTextBuffer = 2;
        this.xBoxPadding = 4;
        this.yBoxPadding = 2;
        java.util.Random a1 = new java.util.Random(12345678L);
        this.random = a1;
        this.P = -10;
        this.PPosition = -10;
        this.i = -10;
        this.j = -10;
        this.numberOfComparisions = 0;
        this.numberOfSwaps = 0;
        this.started = false;
        this.active = false;
        this.processed = false;
        this.doingChildren = false;
        this.drawRectangles = false;
        int[] dummy = (int[])a0;
        int[] a2 = (int[])a0;
        this.data = a2;
        com.cim.AIA.ConfigurationManager a3 = com.cim.AIA.ConfigurationManager.getConfigurationManager();
        java.awt.Color a4 = QuickSort.PARTITION_COLOR;
        String s = QuickSort.PARTITION;
        com.cim.AIA.ColorSelection a5 = new com.cim.AIA.ColorSelection(a4, s);
        a5.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        a3.addColorSelection(a5);
        a3.addColorSelectionListener((com.cim.AIA.ColorSelectionListener)this);
        java.awt.Color a6 = QuickSort.ACTIVE_COLOR;
        String s0 = QuickSort.ACTIVE;
        com.cim.AIA.ColorSelection a7 = new com.cim.AIA.ColorSelection(a6, s0);
        a7.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        a3.addColorSelection(a7);
        java.awt.Color a8 = QuickSort.DOING_CHILDREN_COLOR;
        String s1 = QuickSort.PROCESSED;
        com.cim.AIA.ColorSelection a9 = new com.cim.AIA.ColorSelection(a8, s1);
        a9.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        a3.addColorSelection(a9);
        java.awt.Color a10 = QuickSort.FINISHED_COLOR;
        String s2 = QuickSort.FINISHED;
        com.cim.AIA.ColorSelection a11 = new com.cim.AIA.ColorSelection(a10, s2);
        a11.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        a3.addColorSelection(a11);
        java.awt.Color a12 = QuickSort.WAITING_COLOR;
        String s3 = QuickSort.WAITING;
        com.cim.AIA.ColorSelection a13 = new com.cim.AIA.ColorSelection(a12, s3);
        a13.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        a3.addColorSelection(a13);
        java.awt.Color a14 = QuickSort.HIGHLIGHT_COLOR;
        String s4 = QuickSort.highlightLabel;
        com.cim.AIA.ColorSelection a15 = new com.cim.AIA.ColorSelection(a14, s4);
        a15.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        a3.addColorSelection(a15);
        a3.addColorSelectionListener((com.cim.AIA.ColorSelectionListener)this);
        java.awt.Color a16 = QuickSort.selectionColor;
        String s5 = QuickSort.selectionLabel;
        com.cim.AIA.ColorSelection a17 = new com.cim.AIA.ColorSelection(a16, s5);
        a17.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        a3.addColorSelection(a17);
        a3.addColorSelectionListener((com.cim.AIA.ColorSelectionListener)this);
        this.initialise(true);
    }
    
    protected void addHighlight(int i)
    {
        java.util.Hashtable a = QuickSort.highlightedElements;
        Integer a0 = new Integer(i);
        Boolean a1 = new Boolean(true);
        Object a2 = a.put((Object)a0, (Object)a1);
    }
    
    protected void calculateColors(java.awt.Color[] a, int[] a0, int i)
    {
        int i0 = this.left;
        int i1 = i0;
        while(true)
        {
            int i2 = this.right;
            if(i1 > i2)
            {
                break;
            }
            label0: {
                if(i1 < 0)
                {
                    break label0;
                }
                int i3 = a.length;
                if(i1 >= i3)
                {
                    break label0;
                }
                java.awt.Color a1 = a[i1];
                label1: {
                    if(a1 != null)
                    {
                        break label1;
                    }
                    java.awt.Color a2 = this.getColor();
                    a[i1] = a2;
                    int i4 = this.PPosition;
                    label2: {
                        if(i1 != i4)
                        {
                            break label2;
                        }
                        int i5 = this.isDoingChildren()?1:0;
                        if(i5 != 0)
                        {
                            break label2;
                        }
                        int i6 = this.isFinished()?1:0;
                        if(i6 == 0)
                        {
                            java.awt.Color a3 = QuickSort.PARTITION_COLOR;
                            a[i1] = a3;
                        }
                    }
                    int i7 = a0.length;
                    if(i1 >= i7)
                    {
                        break label0;
                    }
                    else
                    {
                        a0[i1] = i;
                        break label0;
                    }
                }
                int i8 = a0.length;
                label3: {
                    if(i1 >= i8)
                    {
                        break label3;
                    }
                    int i9 = a0[i1];
                    if(i > i9)
                    {
                        java.awt.Color a4 = this.getColor();
                        a[i1] = a4;
                        a0[i1] = i;
                    }
                }
                int i10 = this.PPosition;
                if(i1 != i10)
                {
                    break label0;
                }
                int i11 = this.isDoingChildren()?1:0;
                if(i11 != 0)
                {
                    break label0;
                }
                int i12 = this.isFinished()?1:0;
                if(i12 == 0)
                {
                    java.awt.Color a5 = QuickSort.PARTITION_COLOR;
                    a[i1] = a5;
                }
            }
            int i13 = i1 + 1;
            i1 = i13;
        }
        QuickSort a6 = this.leftChild;
        if(a6 != null)
        {
            QuickSort a7 = this.leftChild;
            int i14 = i + 1;
            a7.calculateColors(a, a0, i14);
        }
        QuickSort a8 = this.rightChild;
        if(a8 != null)
        {
            QuickSort a9 = this.rightChild;
            int i15 = i + 1;
            a9.calculateColors(a, a0, i15);
        }
    }
    
    protected void createDuplicateLabels(int[] a)
    {
        int i = a.length;
        Integer[] a0 = new Integer[i];
        int i0 = 0;
        while(true)
        {
            int i1 = a.length;
            if(i0 >= i1)
            {
                String[] a1 = com.cim.AIA.DuplicateLabel.createDuplicateLabels((Object[])a0);
                QuickSort.duplicateLabels = a1;
                return;
            }
            else
            {
                int i2 = a[i0];
                Integer a2 = new Integer(i2);
                a0[i0] = a2;
                int i3 = i0 + 1;
                i0 = i3;
            }
        }
    }
    
    public void display()
    {
        java.io.PrintStream a = System.out;
        StringBuilder a0 = new StringBuilder();
        String s = localization.Messages.getString("QuickSort.11");
        StringBuilder a1 = a0.append(s);
        int i = this.left;
        StringBuilder a2 = a1.append(i);
        String s0 = localization.Messages.getString("QuickSort.12");
        StringBuilder a3 = a2.append(s0);
        int i0 = this.right;
        StringBuilder a4 = a3.append(i0);
        StringBuilder a5 = a4.append(" || ");
        String s1 = a5.toString();
        a.print(s1);
        int i1 = 0;
        while(true)
        {
            int[] a6 = this.data;
            int i2 = a6.length;
            if(i1 >= i2)
            {
                java.io.PrintStream a7 = System.out;
                a7.println("");
                return;
            }
            else
            {
                java.io.PrintStream a8 = System.out;
                StringBuilder a9 = new StringBuilder();
                int[] a10 = this.data;
                int i3 = a10[i1];
                StringBuilder a11 = a9.append(i3);
                StringBuilder a12 = a11.append(" ");
                String s2 = a12.toString();
                a8.print(s2);
                int i4 = i1 + 1;
                i1 = i4;
            }
        }
    }
    
    public boolean doDrawRectangles()
    {
        int i = this.drawRectangles?1:0;
        return i != 0;
    }
    
    protected void draw(java.awt.Graphics a, java.awt.Point a0)
    {
        java.awt.Color a1 = this.getColor();
        a.setColor(a1);
        int i = this.getLeft();
        int i0 = this.columWidth;
        int i1 = this.columSpacing;
        int i2 = i0 + i1;
        int i3 = i * i2;
        int i4 = a0.x;
        int i5 = i3 + i4;
        int i6 = this.columWidth;
        int i7 = i6 / 2;
        int i8 = i5 - i7;
        int i9 = a0.y;
        java.awt.FontMetrics a2 = a.getFontMetrics();
        int i10 = a2.getHeight();
        int i11 = i9 - i10;
        int i12 = this.getRight();
        int i13 = 1 + i12;
        int i14 = this.getLeft();
        int i15 = i13 - i14;
        int i16 = this.columWidth;
        int i17 = this.columSpacing;
        int i18 = i16 + i17;
        int i19 = i15 * i18;
        java.awt.FontMetrics a3 = a.getFontMetrics();
        int i20 = a3.getHeight();
        a.fillRect(i8, i11, i19, i20);
        java.awt.Color a4 = QuickSort.TEXT_COLOR;
        a.setColor(a4);
        int i21 = this.getLeft();
        int i22 = i21;
        while(true)
        {
            int i23 = this.getRight();
            if(i22 > i23)
            {
                break;
            }
            else
            {
                StringBuilder a5 = new StringBuilder();
                int[] a6 = this.data;
                int i24 = a6[i22];
                StringBuilder a7 = a5.append(i24);
                StringBuilder a8 = a7.append("");
                String s = a8.toString();
                int i25 = this.columWidth;
                int i26 = this.columSpacing;
                int i27 = i25 + i26;
                int i28 = i22 * i27;
                int i29 = a0.x;
                int i30 = i28 + i29;
                int i31 = this.columWidth;
                int i32 = i31 / 2;
                int i33 = i30 + i32;
                java.awt.FontMetrics a9 = a.getFontMetrics();
                StringBuilder a10 = new StringBuilder();
                int[] a11 = this.data;
                int i34 = a11[i22];
                StringBuilder a12 = a10.append(i34);
                StringBuilder a13 = a12.append("");
                String s0 = a13.toString();
                int i35 = a9.stringWidth(s0);
                int i36 = i35 / 2;
                int i37 = i33 - i36;
                int i38 = a0.y;
                a.drawString(s, i37, i38);
                int i39 = i22 + 1;
                i22 = i39;
            }
        }
        int i40 = this.isActive()?1:0;
        label0: {
            if(i40 == 0)
            {
                break label0;
            }
            int i41 = this.isDoingChildren()?1:0;
            if(i41 != 0)
            {
                break label0;
            }
            int i42 = this.isFinished()?1:0;
            if(i42 != 0)
            {
                break label0;
            }
            int i43 = this.getRight();
            int i44 = this.columWidth;
            int i45 = this.columSpacing;
            int i46 = a0.x;
            int i47 = this.columWidth;
            int i48 = this.getLeft();
            int i49 = this.columWidth;
            int i50 = this.columSpacing;
            int i51 = a0.x;
            int i52 = this.columWidth;
            int i53 = this.getI();
            if(i53 != -10)
            {
                java.awt.Color a14 = QuickSort.TEXT_COLOR;
                a.setColor(a14);
                int i54 = this.getI();
                int i55 = this.columWidth;
                int i56 = this.columSpacing;
                int i57 = i55 + i56;
                int i58 = i54 * i57;
                int i59 = a0.x;
                int i60 = i58 + i59;
                int i61 = this.columWidth;
                int i62 = i61 / 2;
                int i63 = i60 + i62;
                java.awt.FontMetrics a15 = a.getFontMetrics();
                int i64 = a15.stringWidth("i");
                int i65 = i64 / 2;
                int i66 = i63 - i65;
                int i67 = a0.y;
                java.awt.FontMetrics a16 = a.getFontMetrics();
                int i68 = a16.getHeight();
                int i69 = i67 + i68;
                int i70 = this.yTextBuffer;
                int i71 = i69 + i70;
                a.drawString("i", i66, i71);
            }
            int i72 = this.getJ();
            if(i72 != -10)
            {
                java.awt.Color a17 = QuickSort.TEXT_COLOR;
                a.setColor(a17);
                int i73 = this.getJ();
                int i74 = this.columWidth;
                int i75 = this.columSpacing;
                int i76 = i74 + i75;
                int i77 = i73 * i76;
                int i78 = a0.x;
                int i79 = i77 + i78;
                int i80 = this.columWidth;
                int i81 = i80 / 2;
                int i82 = i79 + i81;
                java.awt.FontMetrics a18 = a.getFontMetrics();
                int i83 = a18.stringWidth("J");
                int i84 = i83 / 2;
                int i85 = i82 - i84;
                int i86 = a0.y;
                int i87 = this.yTextBuffer;
                int i88 = 2 * i87;
                int i89 = i86 + i88;
                java.awt.FontMetrics a19 = a.getFontMetrics();
                int i90 = a19.getHeight();
                int i91 = 2 * i90;
                int i92 = i89 + i91;
                a.drawString("J", i85, i92);
            }
        }
        int i93 = this.getPPosition();
        if(i93 != -10)
        {
            java.awt.Color a20 = java.awt.Color.black;
            a.setColor(a20);
            int i94 = this.getPPosition();
            int i95 = this.columWidth;
            int i96 = this.columSpacing;
            int i97 = i95 + i96;
            int i98 = i94 * i97;
            int i99 = a0.x;
            int i100 = i98 + i99;
            int i101 = this.xBoxPadding;
            int i102 = i100 - i101;
            int i103 = a0.y;
            int i104 = this.yBoxPadding;
            int i105 = i103 - i104;
            java.awt.FontMetrics a21 = a.getFontMetrics();
            int i106 = a21.getHeight();
            int i107 = i105 - i106;
            int i108 = this.columWidth;
            int i109 = this.xBoxPadding;
            int i110 = 2 * i109;
            int i111 = i108 + i110;
            java.awt.FontMetrics a22 = a.getFontMetrics();
            int i112 = a22.getHeight();
            int i113 = this.yBoxPadding;
            int i114 = 2 * i113;
            int i115 = i112 + i114;
            a.drawRect(i102, i107, i111, i115);
        }
        QuickSort a23 = this.getLeftChild();
        if(a23 != null)
        {
            QuickSort a24 = this.getLeftChild();
            int i116 = a0.x;
            int i117 = a0.y;
            java.awt.FontMetrics a25 = a.getFontMetrics();
            int i118 = a25.getHeight();
            int i119 = 2 * i118;
            int i120 = i117 + i119;
            int i121 = this.yTextBuffer;
            int i122 = 2 * i121;
            int i123 = i120 + i122;
            java.awt.Point a26 = new java.awt.Point(i116, i123);
            a24.draw(a, a26);
        }
        QuickSort a27 = this.getRightChild();
        if(a27 != null)
        {
            QuickSort a28 = this.getRightChild();
            int i124 = a0.x;
            int i125 = a0.y;
            java.awt.FontMetrics a29 = a.getFontMetrics();
            int i126 = a29.getHeight();
            int i127 = 2 * i126;
            int i128 = i125 + i127;
            int i129 = this.yTextBuffer;
            int i130 = 2 * i129;
            int i131 = i128 + i130;
            java.awt.Point a30 = new java.awt.Point(i124, i131);
            a28.draw(a, a30);
        }
    }
    
    public java.util.Vector generateQuestions()
    {
        java.util.Vector a = new java.util.Vector();
        int i = 0;
        while(true)
        {
            java.util.Vector a0 = QuickSort.questions;
            int i0 = a0.size();
            if(i >= i0)
            {
                return a;
            }
            else
            {
                java.util.Vector a1 = QuickSort.questions;
                Object a2 = a1.elementAt(i);
                a.addElement(a2);
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    protected void generateTweens(com.cim.AIA.MultipleTween a, MyElementArrayQS a0, int i)
    {
        int i0 = 0;
        while(true)
        {
            java.util.Vector a1 = QuickSort.swapRequests;
            int i1 = a1.size();
            if(i0 >= i1)
            {
                break;
            }
            else
            {
                java.util.Vector a2 = QuickSort.swapRequests;
                Object a3 = a2.elementAt(i0);
                com.cim.AIA.SwapRequest dummy = (com.cim.AIA.SwapRequest)a3;
                com.cim.AIA.SwapRequest a4 = (com.cim.AIA.SwapRequest)a3;
                int i2 = a4.getFirstId();
                com.cim.AIA.Element a5 = a0.getElement(i2);
                com.cim.AIA.VerticalBar dummy0 = (com.cim.AIA.VerticalBar)a5;
                com.cim.AIA.VerticalBar a6 = (com.cim.AIA.VerticalBar)a5;
                int i3 = a4.getSecondId();
                com.cim.AIA.Element a7 = a0.getElement(i3);
                com.cim.AIA.VerticalBar dummy1 = (com.cim.AIA.VerticalBar)a7;
                com.cim.AIA.VerticalBar a8 = (com.cim.AIA.VerticalBar)a7;
                java.awt.Point a9 = a8.getPosition();
                java.awt.Point a10 = a6.getPosition();
                com.cim.AIA.MoveTween a11 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Moveable)a6, a9, a10, false, i);
                a.add((com.cim.AIA.Tween)a11);
                java.awt.Point a12 = a6.getPosition();
                java.awt.Point a13 = a8.getPosition();
                com.cim.AIA.MoveTween a14 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Moveable)a8, a12, a13, false, i);
                a.add((com.cim.AIA.Tween)a14);
                int i4 = i0 + 1;
                i0 = i4;
            }
        }
        QuickSort a15 = this.leftChild;
        if(a15 != null)
        {
            QuickSort a16 = this.leftChild;
            a16.generateTweens(a, a0, i);
        }
        QuickSort a17 = this.rightChild;
        if(a17 != null)
        {
            QuickSort a18 = this.rightChild;
            a18.generateTweens(a, a0, i);
        }
    }
    
    public com.cim.AIA.MultipleTween generateTweens(com.cim.AIA.Paintable a, Object a0, int i)
    {
        Object a1 = a;
        com.cim.AIA.MultipleTween a2 = new com.cim.AIA.MultipleTween((com.cim.AIA.Paintable)a1);
        MyElementArrayQS dummy = (MyElementArrayQS)a0;
        MyElementArrayQS a3 = (MyElementArrayQS)a0;
        this.generateTweens(a2, a3, i);
        return a2;
    }
    
    public QuickSort getActive()
    {
        QuickSort a = null;
        int i = this.isActive()?1:0;
        label1: {
            QuickSort a0 = null;
            QuickSort a1 = null;
            label0: {
                if(i == 0)
                {
                    break label0;
                }
                int i0 = this.isDoingChildren()?1:0;
                if(i0 != 0)
                {
                    break label0;
                }
                a = this;
                break label1;
            }
            QuickSort a2 = this.leftChild;
            QuickSort a3 = null;
            if(a2 == null)
            {
                a0 = a3;
            }
            else
            {
                QuickSort a4 = this.leftChild;
                QuickSort a5 = a4.getActive();
                a0 = a5;
            }
            label2: {
                if(a0 == null)
                {
                    break label2;
                }
                a = a0;
                break label1;
            }
            QuickSort a6 = this.rightChild;
            QuickSort a7 = null;
            if(a6 == null)
            {
                a1 = a7;
            }
            else
            {
                QuickSort a8 = this.rightChild;
                QuickSort a9 = a8.getActive();
                a1 = a9;
            }
            a = a1 == null?null:a1;
        }
        return a;
    }
    
    public String getClassName()
    {
        String s = localization.Messages.getString("QuickSort.10");
        return s;
    }
    
    protected java.awt.Color getColor()
    {
        java.awt.Color a = null;
        int i = this.isFinished()?1:0;
        label1: {
            label0: {
                if(i == 0)
                {
                    break label0;
                }
                java.awt.Color a0 = QuickSort.FINISHED_COLOR;
                a = a0;
                break label1;
            }
            int i0 = this.isDoingChildren()?1:0;
            label2: {
                if(i0 == 0)
                {
                    break label2;
                }
                java.awt.Color a1 = QuickSort.DOING_CHILDREN_COLOR;
                a = a1;
                break label1;
            }
            int i1 = this.isActive()?1:0;
            if(i1 == 0)
            {
                java.awt.Color a2 = QuickSort.WAITING_COLOR;
                a = a2;
            }
            else
            {
                java.awt.Color a3 = QuickSort.ACTIVE_COLOR;
                a = a3;
            }
        }
        return a;
    }
    
    public int[] getData()
    {
        int[] a = this.data;
        return a;
    }
    
    protected int getDepth(int i)
    {
        int i0 = 0;
        QuickSort a = this.leftChild;
        label1: {
            label0: {
                if(a == null)
                {
                    break label0;
                }
                QuickSort a0 = this.rightChild;
                if(a0 == null)
                {
                    break label0;
                }
                QuickSort a1 = this.leftChild;
                int i1 = i + 1;
                int i2 = a1.getDepth(i1);
                QuickSort a2 = this.rightChild;
                int i3 = i + 1;
                int i4 = a2.getDepth(i3);
                int i5 = Math.max(i2, i4);
                i0 = i5;
                break label1;
            }
            QuickSort a3 = this.leftChild;
            label2: {
                if(a3 == null)
                {
                    break label2;
                }
                QuickSort a4 = this.leftChild;
                int i6 = i + 1;
                int i7 = a4.getDepth(i6);
                i0 = i7;
                break label1;
            }
            QuickSort a5 = this.rightChild;
            if(a5 == null)
            {
                i0 = i;
            }
            else
            {
                QuickSort a6 = this.rightChild;
                int i8 = i + 1;
                int i9 = a6.getDepth(i8);
                i0 = i9;
            }
        }
        return i0;
    }
    
    public MyElementArrayQS getElementArray(java.awt.Point a)
    {
        int i = a.x;
        int i0 = a.y;
        MyElementArrayQS a0 = new MyElementArrayQS(i, i0);
        QuickSort.elementArray = a0;
        MyElementArrayQS a1 = QuickSort.elementArray;
        int i1 = this.columSpacing;
        a1.setColumGap(i1);
        MyElementArrayQS a2 = QuickSort.elementArray;
        int i2 = this.columWidth;
        a2.setElementWidth(i2);
        int[] a3 = this.data;
        int i3 = a3.length;
        java.awt.Color[] a4 = new java.awt.Color[i3];
        int[] a5 = this.data;
        int i4 = a5.length;
        int[] a6 = new int[i4];
        this.calculateColors(a4, a6, 0);
        int i5 = 0;
        while(true)
        {
            int[] a7 = this.data;
            int i6 = a7.length;
            if(i5 >= i6)
            {
                MyElementArrayQS a8 = QuickSort.elementArray;
                return a8;
            }
            int i7 = this.isHighlighted(i5)?1:0;
            if(i7 == 0)
            {
                int[] a9 = this.data;
                int i8 = a9[i5];
                java.awt.Color a10 = a4[i5];
                java.awt.Point a11 = new java.awt.Point(0, 0);
                com.cim.AIA.VerticalBar a12 = new com.cim.AIA.VerticalBar(i5, i8, a10, a11);
                java.awt.Color a13 = QuickSort.TEXT_COLOR;
                a12.setTextColor(a13);
                java.awt.Color a14 = QuickSort.selectionColor;
                a12.setHighlightColor(a14);
                MyElementArrayQS a15 = QuickSort.elementArray;
                a15.setValue(i5, (com.cim.AIA.Element)a12);
            }
            else
            {
                int[] a16 = this.data;
                int i9 = a16[i5];
                java.awt.Color a17 = a4[i5];
                java.awt.Color a18 = QuickSort.HIGHLIGHT_COLOR;
                java.awt.Point a19 = new java.awt.Point(0, 0);
                com.cim.AIA.VerticalBar a20 = new com.cim.AIA.VerticalBar(i5, i9, a17, a18, a19);
                java.awt.Color a21 = QuickSort.TEXT_COLOR;
                a20.setTextColor(a21);
                java.awt.Color a22 = QuickSort.selectionColor;
                a20.setHighlightColor(a22);
                MyElementArrayQS a23 = QuickSort.elementArray;
                a23.setValue(i5, (com.cim.AIA.Element)a20);
            }
            MyElementArrayQS a24 = QuickSort.elementArray;
            com.cim.AIA.Element a25 = a24.getElement(i5);
            com.cim.AIA.VerticalBar dummy = (com.cim.AIA.VerticalBar)a25;
            com.cim.AIA.VerticalBar a26 = (com.cim.AIA.VerticalBar)a25;
            a26.setMarkersAboveValue(false);
            MyElementArrayQS a27 = QuickSort.elementArray;
            com.cim.AIA.Element a28 = a27.getElement(i5);
            com.cim.AIA.VerticalBar dummy0 = (com.cim.AIA.VerticalBar)a28;
            com.cim.AIA.VerticalBar a29 = (com.cim.AIA.VerticalBar)a28;
            a29.setOffsetForValue(1);
            String[] a30 = QuickSort.duplicateLabels;
            String s = a30[i5];
            if(s != null)
            {
                StringBuilder a31 = new StringBuilder();
                StringBuilder a32 = a31.append("");
                int[] a33 = this.data;
                int i10 = a33[i5];
                StringBuilder a34 = a32.append(i10);
                String[] a35 = QuickSort.duplicateLabels;
                String s0 = a35[i5];
                StringBuilder a36 = a34.append(s0);
                String s1 = a36.toString();
                com.cim.AIA.StringMarker a37 = new com.cim.AIA.StringMarker(s1);
                java.awt.Color a38 = QuickSort.TEXT_COLOR;
                a37.setColor(a38);
                java.awt.Color a39 = QuickSort.BACKGROUND;
                a37.setBackgroundColor(a39);
                MyElementArrayQS a40 = QuickSort.elementArray;
                com.cim.AIA.Element a41 = a40.getElement(i5);
                a41.addMarker(a37);
            }
            int i11 = i5 + 1;
            i5 = i11;
        }
    }
    
    public int getHeight(java.awt.Graphics a)
    {
        int i = this.getDepth(1);
        java.awt.FontMetrics a0 = a.getFontMetrics();
        int i0 = a0.getHeight();
        int i1 = this.yTextBuffer;
        int i2 = i0 + i1;
        int i3 = 2 * i2;
        int i4 = i * i3;
        return i4;
    }
    
    public int getI()
    {
        int i = this.i;
        return i;
    }
    
    public int getJ()
    {
        int i = this.j;
        return i;
    }
    
    public int getLeft()
    {
        int i = this.left;
        return i;
    }
    
    public QuickSort getLeftChild()
    {
        QuickSort a = this.leftChild;
        return a;
    }
    
    public int getNumberOfComparisions()
    {
        int i = 0;
        int i0 = 0;
        int i1 = this.numberOfComparisions;
        QuickSort a = this.leftChild;
        if(a == null)
        {
            i = i1;
        }
        else
        {
            QuickSort a0 = this.leftChild;
            int i2 = a0.getNumberOfComparisions();
            int i3 = i1 + i2;
            i = i3;
        }
        QuickSort a1 = this.rightChild;
        if(a1 == null)
        {
            i0 = i;
        }
        else
        {
            QuickSort a2 = this.rightChild;
            int i4 = a2.getNumberOfComparisions();
            int i5 = i + i4;
            i0 = i5;
        }
        return i0;
    }
    
    public int getNumberOfSwaps()
    {
        int i = 0;
        int i0 = 0;
        int i1 = this.numberOfSwaps;
        QuickSort a = this.leftChild;
        if(a == null)
        {
            i = i1;
        }
        else
        {
            QuickSort a0 = this.leftChild;
            int i2 = a0.getNumberOfSwaps();
            int i3 = i1 + i2;
            i = i3;
        }
        QuickSort a1 = this.rightChild;
        if(a1 == null)
        {
            i0 = i;
        }
        else
        {
            QuickSort a2 = this.rightChild;
            int i4 = a2.getNumberOfSwaps();
            int i5 = i + i4;
            i0 = i5;
        }
        return i0;
    }
    
    public int getPPosition()
    {
        int i = this.PPosition;
        return i;
    }
    
    public int getPValue()
    {
        int i = this.P;
        return i;
    }
    
    public int getRight()
    {
        int i = this.right;
        return i;
    }
    
    public QuickSort getRightChild()
    {
        QuickSort a = this.rightChild;
        return a;
    }
    
    public int getWidth(java.awt.Graphics a)
    {
        int i = this.getRight();
        int i0 = 1 + i;
        int i1 = this.getLeft();
        int i2 = i0 - i1;
        int i3 = this.columWidth;
        int i4 = this.columSpacing;
        int i5 = i3 + i4;
        int i6 = i2 * i5;
        return i6;
    }
    
    protected boolean hasQuestions()
    {
        java.util.Vector a = QuickSort.questions;
        int i = a.size();
        int i0 = i < 1?0:1;
        return i0 != 0;
    }
    
    public boolean haveStarted()
    {
        int i = this.started?1:0;
        return i != 0;
    }
    
    protected void initialise(boolean b)
    {
        this.debugMode = false;
        int i = b?1:0;
        this.active = false;
        this.processed = false;
        this.leftChild = null;
        this.rightChild = null;
        this.P = -10;
        this.PPosition = -10;
        this.i = -10;
        this.j = -10;
        this.numberOfComparisions = 0;
        this.numberOfSwaps = 0;
        this.drawRectangles = false;
        if(i != 0)
        {
            this.left = 0;
            int[] a = this.data;
            int i0 = a.length;
            int i1 = i0 - 1;
            this.right = i1;
            int[] a0 = this.data;
            this.createDuplicateLabels(a0);
        }
    }
    
    public boolean isActive()
    {
        int i = 0;
        int i0 = this.isFinished()?1:0;
        if(i0 == 0)
        {
            int i1 = this.active?1:0;
            i = i1;
        }
        else
        {
            i = 0;
        }
        return i != 0;
    }
    
    public boolean isDoingChildren()
    {
        int i = 0;
        int i0 = this.isActive()?1:0;
        if(i0 == 0)
        {
            i = 0;
        }
        else
        {
            int i1 = this.doingChildren?1:0;
            i = i1;
        }
        return i != 0;
    }
    
    public boolean isExpanded(String s)
    {
        com.cim.AIA.AlgorithmThread a = this.algorithmThread;
        QuickSortThread dummy = (QuickSortThread)a;
        QuickSortThread a0 = (QuickSortThread)a;
        com.cim.AIA.AlgorithmWindow a1 = a0.getWindow();
        int i = a1.isExpanded(s)?1:0;
        return i != 0;
    }
    
    public boolean isFinished()
    {
        int i = this.processed?1:0;
        return i != 0;
    }
    
    protected boolean isHighlighted(int i)
    {
        java.util.Hashtable a = QuickSort.highlightedElements;
        Integer a0 = new Integer(i);
        Object a1 = a.get((Object)a0);
        int i0 = a1 == null?0:1;
        return i0 != 0;
    }
    
    protected void makeQuestion()
    {
        MyElementArrayQS a = QuickSort.elementArray;
        {
            label2: {
                label1: {
                    int i = 0;
                    int i0 = 0;
                    int i1 = 0;
                    int i2 = 0;
                    int i3 = 0;
                    int i4 = 0;
                    label0: {
                        if(a != null)
                        {
                            break label0;
                        }
                        java.io.PrintStream a0 = System.out;
                        a0.println("Element Array is null, not adding question");
                        break label1;
                    }
                    java.util.Random a1 = this.random;
                    double d = a1.nextDouble();
                    double d0 = QuickSort.percentageChanceForQuestion;
                    int i5 = d < d0?-1:d == d0?0:1;
                    if(i5 > 0)
                    {
                        break label2;
                    }
                    java.util.Random a2 = this.random;
                    double d1 = a2.nextDouble();
                    double d2 = QuickSort.percentageChanceForSwapQuestion;
                    int i6 = d1 < d2?-1:d1 == d2?0:1;
                    int i7 = i6 > 0?1:0;
                    int i8 = this.i;
                    int i9 = this.j;
                    int i10 = i8;
                    while(true)
                    {
                        int[] a3 = this.data;
                        i = i10 + 1;
                        int i11 = a3[i];
                        int i12 = this.P;
                        if(i11 > i12)
                        {
                            i0 = i9;
                            break;
                        }
                        int i13 = this.right;
                        if(i != i13)
                        {
                            i10 = i;
                            continue;
                        }
                        i0 = i9;
                        break;
                    }
                    while(true)
                    {
                        int[] a4 = this.data;
                        i1 = i0 + -1;
                        int i14 = a4[i1];
                        int i15 = this.P;
                        if(i14 < i15)
                        {
                            break;
                        }
                        int i16 = this.left;
                        if(i1 != i16)
                        {
                            i0 = i1;
                            continue;
                        }
                        break;
                    }
                    label3: {
                        if(i < i1)
                        {
                            i2 = i7;
                            i3 = i1;
                            break label3;
                        }
                        if(i7 != 0)
                        {
                            i2 = i7;
                            i3 = i1;
                        }
                        else
                        {
                            int i17 = this.right;
                            i2 = 0;
                            i3 = i17;
                        }
                    }
                    IAndJPositionQuestionQS a5 = new IAndJPositionQuestionQS();
                    if(i2 == 0)
                    {
                        String s = IAndJPositionQuestionQS.QUESTION_SWAP_LABEL;
                        a5.setInstructions(s);
                        i4 = 0;
                    }
                    else
                    {
                        String s0 = IAndJPositionQuestionQS.QUESTION_END_UP_LABEL;
                        a5.setInstructions(s0);
                        i4 = 1;
                    }
                    if(i4 == 0)
                    {
                    }
                    Integer a6 = new Integer(i);
                    a5.addAnswer(a6);
                    Integer a7 = new Integer(i3);
                    a5.addAnswer(a7);
                    java.util.Vector a8 = QuickSort.questions;
                    a8.addElement((Object)a5);
                }
            }
        }
    }
    
    public void processColorSelection(com.cim.AIA.ColorSelection a)
    {
        String s = a.getAtributeName();
        String s0 = com.cim.AIA.ColorSelection.BACKGROUND;
        int i = s.equalsIgnoreCase(s0)?1:0;
        label1: {
            label0: {
                if(i == 0)
                {
                    break label0;
                }
                java.awt.Color a0 = a.getColor();
                QuickSort.BACKGROUND = a0;
                break label1;
            }
            String s1 = com.cim.AIA.ColorSelection.TEXT_COLOR;
            int i0 = s.equalsIgnoreCase(s1)?1:0;
            label2: {
                if(i0 == 0)
                {
                    break label2;
                }
                java.awt.Color a1 = a.getColor();
                QuickSort.TEXT_COLOR = a1;
                break label1;
            }
            String s2 = QuickSort.PARTITION;
            int i1 = s.equalsIgnoreCase(s2)?1:0;
            label3: {
                if(i1 == 0)
                {
                    break label3;
                }
                java.awt.Color a2 = a.getColor();
                QuickSort.PARTITION_COLOR = a2;
                break label1;
            }
            String s3 = QuickSort.ACTIVE;
            int i2 = s.equalsIgnoreCase(s3)?1:0;
            label4: {
                if(i2 == 0)
                {
                    break label4;
                }
                java.awt.Color a3 = a.getColor();
                QuickSort.ACTIVE_COLOR = a3;
                break label1;
            }
            String s4 = QuickSort.PROCESSED;
            int i3 = s.equalsIgnoreCase(s4)?1:0;
            label5: {
                if(i3 == 0)
                {
                    break label5;
                }
                java.awt.Color a4 = a.getColor();
                QuickSort.DOING_CHILDREN_COLOR = a4;
                break label1;
            }
            String s5 = QuickSort.FINISHED;
            int i4 = s.equalsIgnoreCase(s5)?1:0;
            label6: {
                if(i4 == 0)
                {
                    break label6;
                }
                java.awt.Color a5 = a.getColor();
                QuickSort.FINISHED_COLOR = a5;
                break label1;
            }
            String s6 = QuickSort.WAITING;
            int i5 = s.equalsIgnoreCase(s6)?1:0;
            label7: {
                if(i5 == 0)
                {
                    break label7;
                }
                java.awt.Color a6 = a.getColor();
                QuickSort.WAITING_COLOR = a6;
                break label1;
            }
            String s7 = QuickSort.highlightLabel;
            int i6 = s.equalsIgnoreCase(s7)?1:0;
            label8: {
                if(i6 == 0)
                {
                    break label8;
                }
                java.awt.Color a7 = a.getColor();
                QuickSort.HIGHLIGHT_COLOR = a7;
                break label1;
            }
            String s8 = QuickSort.selectionLabel;
            int i7 = s.equalsIgnoreCase(s8)?1:0;
            if(i7 != 0)
            {
                java.awt.Color a8 = a.getColor();
                QuickSort.selectionColor = a8;
            }
        }
    }
    
    public void reInitialise(Object a)
    {
        int[] dummy = (int[])a;
        int[] a0 = (int[])a;
        this.data = a0;
        this.initialise(true);
        this.setPosition("");
    }
    
    protected void removeAllAnimationRequests()
    {
        java.util.Vector a = QuickSort.swapRequests;
        a.removeAllElements();
    }
    
    protected void removeAllHighlight()
    {
        java.util.Hashtable a = QuickSort.highlightedElements;
        a.clear();
    }
    
    protected void removeAllQuestions()
    {
        java.util.Vector a = QuickSort.questions;
        a.removeAllElements();
    }
    
    public void resetRandomSeed()
    {
        QuickSort.needtoresetSeed = true;
    }
    
    public void run()
    {
        int i = QuickSort.needtoresetSeed?1:0;
        if(i != 0)
        {
            java.util.Random a = this.random;
            a.setSeed(12345678L);
            QuickSort.needtoresetSeed = false;
        }
        this.runQuickSort(true);
    }
    
    public void runQuickSort(boolean b)
    {
        this.started = true;
        int i = b?1:0;
        int i0 = this.enabled?1:0;
        label1: {
            label0: {
                if(i0 != 0)
                {
                    break label0;
                }
                this.processed = true;
                break label1;
            }
            this.active = true;
            this.setPosition("1", i != 0);
            this.setPosition("3", i != 0);
            int i1 = this.right;
            int i2 = this.left;
            label2: {
                if(i1 <= i2)
                {
                    break label2;
                }
                int i3 = this.isExpanded("4")?1:0;
                if(i3 != 0)
                {
                    this.drawRectangles = true;
                }
                int i4 = QuickSort.currentPartitionMethod;
                label4: {
                    java.util.Random a = null;
                    label3: {
                        int i5 = 0;
                        switch(i4){
                            case 13: {
                                i5 = this.left;
                                break;
                            }
                            case 12: {
                                a = this.random;
                                break label3;
                            }
                            case 11: {
                                java.util.Random a0 = this.random;
                                double d = a0.nextDouble();
                                int i6 = this.right;
                                int i7 = this.left;
                                int i8 = i6 - i7;
                                double d0 = (double)i8;
                                double d1 = d * d0;
                                int i9 = this.left;
                                double d2 = (double)i9;
                                double d3 = d1 + d2;
                                int i10 = (int)d3;
                                this.PPosition = i10;
                                int[] a1 = this.data;
                                int i11 = this.PPosition;
                                int i12 = a1[i11];
                                this.P = i12;
                                this.setPosition("4.1", i != 0);
                                int i13 = this.PPosition;
                                int i14 = this.right;
                                this.swapValues(i13, i14);
                                int i15 = this.right;
                                this.PPosition = i15;
                                this.setPosition("4.2", i != 0);
                                break label4;
                            }
                            case 10: {
                                int i16 = this.right;
                                this.PPosition = i16;
                                int[] a2 = this.data;
                                int i17 = this.PPosition;
                                int i18 = a2[i17];
                                this.P = i18;
                                this.setPosition("4.1", i != 0);
                                break label4;
                            }
                        }
                        int i19 = this.right;
                        int i20 = this.left;
                        int i21 = i19 + i20;
                        int i22 = i21 / 2;
                        int i23 = this.right;
                        int[] a3 = new int[3];
                        a3[0] = i5;
                        a3[1] = i22;
                        a3[2] = i23;
                        int i24 = a3[0];
                        this.addHighlight(i24);
                        this.setPosition("4.1", i != 0);
                        int i25 = a3[1];
                        this.addHighlight(i25);
                        this.setPosition("4.2", i != 0);
                        int i26 = a3[2];
                        this.addHighlight(i26);
                        this.setPosition("4.3", i != 0);
                        int i27 = 0;
                        label5: while(true)
                        {
                            if(i27 >= 3)
                            {
                                break;
                            }
                            int i28 = i27;
                            while(true)
                            {
                                if(i28 >= 3)
                                {
                                    int i29 = i27 + 1;
                                    i27 = i29;
                                    continue label5;
                                }
                                int[] a4 = this.data;
                                int i30 = a3[i28];
                                int i31 = a4[i30];
                                int[] a5 = this.data;
                                int i32 = a3[i27];
                                int i33 = a5[i32];
                                if(i31 < i33)
                                {
                                    int i34 = a3[i27];
                                    int i35 = a3[i28];
                                    this.swapValues(i34, i35);
                                }
                                int i36 = i28 + 1;
                                i28 = i36;
                            }
                        }
                        this.setPosition("4.4", i != 0);
                        this.removeAllHighlight();
                        int i37 = a3[1];
                        this.addHighlight(i37);
                        int i38 = a3[1];
                        this.PPosition = i38;
                        int[] a6 = this.data;
                        int i39 = this.PPosition;
                        int i40 = a6[i39];
                        this.P = i40;
                        this.setPosition("4.5", i != 0);
                        this.removeAllHighlight();
                        int i41 = this.PPosition;
                        int i42 = this.right;
                        this.swapValues(i41, i42);
                        int i43 = this.right;
                        this.PPosition = i43;
                        this.setPosition("4.6", i != 0);
                        break label4;
                    }
                    double d4 = a.nextDouble();
                    int i44 = this.right;
                    int i45 = this.left;
                    int i46 = i44 - i45;
                    double d5 = (double)i46;
                    double d6 = d4 * d5;
                    int i47 = this.left;
                    double d7 = (double)i47;
                    double d8 = d6 + d7;
                    int i48 = (int)d8;
                    java.util.Random a7 = this.random;
                    double d9 = a7.nextDouble();
                    int i49 = this.right;
                    int i50 = this.left;
                    int i51 = i49 - i50;
                    double d10 = (double)i51;
                    double d11 = d9 * d10;
                    int i52 = this.left;
                    double d12 = (double)i52;
                    double d13 = d11 + d12;
                    int i53 = (int)d13;
                    java.util.Random a8 = this.random;
                    double d14 = a8.nextDouble();
                    int i54 = this.right;
                    int i55 = this.left;
                    int i56 = i54 - i55;
                    double d15 = (double)i56;
                    double d16 = d14 * d15;
                    int i57 = this.left;
                    double d17 = (double)i57;
                    double d18 = d16 + d17;
                    int i58 = (int)d18;
                    int[] a9 = new int[3];
                    a9[0] = i48;
                    a9[1] = i53;
                    a9[2] = i58;
                    int i59 = 0;
                    label6: while(true)
                    {
                        if(i59 >= 3)
                        {
                            break;
                        }
                        int i60 = i59;
                        while(true)
                        {
                            if(i60 >= 3)
                            {
                                int i61 = i59 + 1;
                                i59 = i61;
                                continue label6;
                            }
                            int i62 = a9[i60];
                            int i63 = a9[i59];
                            if(i62 < i63)
                            {
                                int i64 = a9[i59];
                                int i65 = a9[i60];
                                a9[i59] = i65;
                                a9[i60] = i64;
                            }
                            int i66 = i60 + 1;
                            i60 = i66;
                        }
                    }
                    int i67 = a9[0];
                    this.addHighlight(i67);
                    this.setPosition("4.1", i != 0);
                    int i68 = a9[1];
                    this.addHighlight(i68);
                    this.setPosition("4.2", i != 0);
                    int i69 = a9[2];
                    this.addHighlight(i69);
                    this.setPosition("4.3", i != 0);
                    int i70 = 0;
                    label7: while(true)
                    {
                        if(i70 >= 3)
                        {
                            break;
                        }
                        int i71 = i70;
                        while(true)
                        {
                            if(i71 >= 3)
                            {
                                int i72 = i70 + 1;
                                i70 = i72;
                                continue label7;
                            }
                            int[] a10 = this.data;
                            int i73 = a9[i71];
                            int i74 = a10[i73];
                            int[] a11 = this.data;
                            int i75 = a9[i70];
                            int i76 = a11[i75];
                            if(i74 < i76)
                            {
                                int i77 = a9[i70];
                                int i78 = a9[i71];
                                this.swapValues(i77, i78);
                            }
                            int i79 = i71 + 1;
                            i71 = i79;
                        }
                    }
                    this.setPosition("4.4", i != 0);
                    this.removeAllHighlight();
                    int i80 = a9[1];
                    this.addHighlight(i80);
                    int i81 = a9[1];
                    this.PPosition = i81;
                    int[] a12 = this.data;
                    int i82 = this.PPosition;
                    int i83 = a12[i82];
                    this.P = i83;
                    this.setPosition("4.5", i != 0);
                    this.removeAllHighlight();
                    int i84 = this.PPosition;
                    int i85 = this.right;
                    this.swapValues(i84, i85);
                    int i86 = this.right;
                    this.PPosition = i86;
                    this.setPosition("4.6", i != 0);
                }
                this.drawRectangles = false;
                int i87 = this.isExpanded("5")?1:0;
                if(i87 != 0)
                {
                    this.drawRectangles = true;
                }
                int i88 = this.left;
                int i89 = i88 - 1;
                this.i = i89;
                this.setPosition("5.1.1", i != 0);
                int i90 = this.right;
                this.j = i90;
                this.setPosition("5.1.2", i != 0);
                this.makeQuestion();
                this.setPosition("5.2", i != 0);
                while(true)
                {
                    int i91 = this.i;
                    int i92 = i91 + 1;
                    this.i = i92;
                    this.setPosition("5.2.1.1", i != 0);
                    while(true)
                    {
                        int[] a13 = this.data;
                        int i93 = this.i;
                        int i94 = a13[i93];
                        int i95 = this.P;
                        if(i94 >= i95)
                        {
                            break;
                        }
                        int i96 = this.i;
                        int i97 = i96 + 1;
                        this.i = i97;
                        int i98 = this.i;
                        int i99 = this.right;
                        if(i98 != i99)
                        {
                            int i100 = this.numberOfComparisions;
                            int i101 = i100 + 1;
                            this.numberOfComparisions = i101;
                            this.setPosition("5.2.1.1", i != 0);
                            continue;
                        }
                        break;
                    }
                    int i102 = this.numberOfComparisions;
                    int i103 = i102 + 1;
                    this.numberOfComparisions = i103;
                    int i104 = this.j;
                    int i105 = i104 - 1;
                    this.j = i105;
                    this.setPosition("5.2.2.1", i != 0);
                    while(true)
                    {
                        int[] a14 = this.data;
                        int i106 = this.j;
                        int i107 = a14[i106];
                        int i108 = this.P;
                        if(i107 <= i108)
                        {
                            break;
                        }
                        int i109 = this.j;
                        int i110 = i109 - 1;
                        this.j = i110;
                        int i111 = this.j;
                        int i112 = this.left;
                        int i113 = i112 - 1;
                        if(i111 != i113)
                        {
                            int i114 = this.numberOfComparisions;
                            int i115 = i114 + 1;
                            this.numberOfComparisions = i115;
                            this.setPosition("5.2.2.1", i != 0);
                            continue;
                        }
                        break;
                    }
                    this.setPosition("5.2.3.1", i != 0);
                    int i116 = this.i;
                    int i117 = this.j;
                    if(i116 < i117)
                    {
                        int i118 = this.i;
                        int i119 = this.j;
                        this.swapValues(i118, i119);
                        this.setPosition("5.2.5.1", i != 0);
                        this.makeQuestion();
                        this.setPosition("5.2", i != 0);
                    }
                    else
                    {
                        break;
                    }
                }
                int i120 = this.i;
                int i121 = this.right;
                this.swapValues(i120, i121);
                com.cim.AIA.AlgorithmThread a15 = this.algorithmThread;
                int[] a16 = this.data;
                int i122 = this.left;
                int i123 = this.i;
                int i124 = i123 - 1;
                QuickSort a17 = new QuickSort(a15, a16, i122, i124);
                this.leftChild = a17;
                com.cim.AIA.AlgorithmThread a18 = this.algorithmThread;
                int[] a19 = this.data;
                int i125 = this.i;
                int i126 = i125 + 1;
                int i127 = this.right;
                QuickSort a20 = new QuickSort(a18, a19, i126, i127);
                this.rightChild = a20;
                this.setPosition("5.4.1", i != 0);
                int i128 = this.isExpanded("6")?1:0;
                if(i128 != 0)
                {
                    this.setPosition("6.1", i != 0);
                }
                this.doingChildren = true;
                int i129 = this.isExpanded("6")?1:0;
                label9: {
                    label8: {
                        if(i129 == 0)
                        {
                            break label8;
                        }
                        if(i == 0)
                        {
                            break label8;
                        }
                        this.drawRectangles = true;
                        QuickSort a21 = this.leftChild;
                        a21.runQuickSort(true);
                        break label9;
                    }
                    this.drawRectangles = false;
                    QuickSort a22 = this.leftChild;
                    a22.runQuickSort(false);
                }
                QuickSort a23 = this.leftChild;
                a23.processed = true;
                int i130 = this.isExpanded("6")?1:0;
                if(i130 == 0)
                {
                    this.setPosition("6.1", i != 0);
                }
                int i131 = this.isExpanded("7")?1:0;
                if(i131 != 0)
                {
                    this.setPosition("7.1", i != 0);
                }
                int i132 = this.isExpanded("7")?1:0;
                label11: {
                    label10: {
                        if(i132 == 0)
                        {
                            break label10;
                        }
                        if(i == 0)
                        {
                            break label10;
                        }
                        this.drawRectangles = true;
                        QuickSort a24 = this.rightChild;
                        a24.runQuickSort(true);
                        break label11;
                    }
                    this.drawRectangles = false;
                    QuickSort a25 = this.rightChild;
                    a25.runQuickSort(false);
                }
                QuickSort a26 = this.rightChild;
                a26.processed = true;
                int i133 = this.isExpanded("7")?1:0;
                if(i133 == 0)
                {
                    this.setPosition("7.1", i != 0);
                }
                this.doingChildren = false;
                this.processed = true;
            }
            this.drawRectangles = false;
            this.processed = true;
        }
    }
    
    public void setPartitionMethod(int i)
    {
        QuickSort.currentPartitionMethod = i;
    }
    
    public void setPosition(String s, boolean b)
    {
        if(b)
        {
            this.setPosition(s);
        }
    }
    
    protected void swapValues(int i, int i0)
    {
        int i1 = this.PPosition;
        if(i == i1)
        {
            this.PPosition = i0;
        }
        int i2 = this.PPosition;
        if(i0 == i2)
        {
            this.PPosition = i;
        }
        int i3 = this.numberOfSwaps;
        int i4 = i3 + 1;
        this.numberOfSwaps = i4;
        int[] a = this.data;
        int i5 = a[i];
        int[] a0 = this.data;
        int[] a1 = this.data;
        int i6 = a1[i0];
        a0[i] = i6;
        int[] a2 = this.data;
        a2[i0] = i5;
        String[] a3 = QuickSort.duplicateLabels;
        String s = a3[i];
        String[] a4 = QuickSort.duplicateLabels;
        String[] a5 = QuickSort.duplicateLabels;
        String s0 = a5[i0];
        a4[i] = s0;
        String[] a6 = QuickSort.duplicateLabels;
        a6[i0] = s;
        if(i != i0)
        {
            java.util.Vector a7 = QuickSort.swapRequests;
            com.cim.AIA.SwapRequest a8 = new com.cim.AIA.SwapRequest(i0, i);
            a7.addElement((Object)a8);
        }
    }
    
    static
    {
        String s = localization.Messages.getString("QuickSort.2");
        QuickSort.selectionLabel = s;
        String s0 = localization.Messages.getString("QuickSort.3");
        QuickSort.highlightLabel = s0;
        String s1 = localization.Messages.getString("QuickSort.4");
        QuickSort.ACTIVE = s1;
        String s2 = localization.Messages.getString("QuickSort.5");
        QuickSort.PROCESSED = s2;
        String s3 = localization.Messages.getString("QuickSort.6");
        QuickSort.FINISHED = s3;
        String s4 = localization.Messages.getString("QuickSort.7");
        QuickSort.WAITING = s4;
        String s5 = localization.Messages.getString("QuickSort.8");
        QuickSort.PARTITION = s5;
        java.awt.Color a = java.awt.Color.yellow;
        QuickSort.selectionColor = a;
        java.awt.Color a0 = java.awt.Color.white;
        QuickSort.BACKGROUND = a0;
        java.awt.Color a1 = java.awt.Color.black;
        QuickSort.TEXT_COLOR = a1;
        java.awt.Color a2 = java.awt.Color.yellow;
        QuickSort.PARTITION_COLOR = a2;
        java.awt.Color a3 = java.awt.Color.pink;
        QuickSort.WAITING_COLOR = a3;
        java.awt.Color a4 = java.awt.Color.green;
        QuickSort.HIGHLIGHT_COLOR = a4;
        java.awt.Color a5 = java.awt.Color.red;
        java.awt.Color a6 = a5.darker();
        QuickSort.DOING_CHILDREN_COLOR = a6;
        java.awt.Color a7 = java.awt.Color.red;
        QuickSort.ACTIVE_COLOR = a7;
        java.awt.Color a8 = java.awt.Color.gray;
        QuickSort.FINISHED_COLOR = a8;
        QuickSort.currentPartitionMethod = 10;
        java.util.Vector a9 = new java.util.Vector();
        QuickSort.swapRequests = a9;
        java.util.Vector a10 = new java.util.Vector();
        QuickSort.questions = a10;
        java.util.Hashtable a11 = new java.util.Hashtable();
        QuickSort.highlightedElements = a11;
        QuickSort.needtoresetSeed = false;
        QuickSort.percentageChanceForQuestion = 0.25;
        QuickSort.percentageChanceForSwapQuestion = 0.5;
    }
}