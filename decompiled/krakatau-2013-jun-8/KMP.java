public class KMP extends com.cim.AIA.Algorithm implements com.cim.AIA.ColorSelectionListener {
    final public static int EMPTY_MARKER = -10;
    final public static int NOOPT = 1;
    final public static int WITHOPT = 2;
    public static int currentVariation;
    final protected static String HIGHLIGHT_COLOR;
    final protected static String NODE_COLOR;
    protected String[] data;
    protected KMPString theKMPString1;
    protected KMPString theKMPString2;
    protected KMPString testKMPString1;
    protected KMPString testKMPString2;
    protected KMPNextTable theNextTable;
    protected com.cim.AIA.Node jPointer;
    protected com.cim.AIA.Node iPointer;
    protected com.cim.AIA.Node animNode;
    protected int kmpResult;
    protected int theJ;
    protected int theI;
    protected boolean showIPointer;
    protected boolean showJPointer;
    protected boolean highlightJPointer;
    protected int[] nextTable;
    protected int[] fakeNextTable;
    protected java.util.Vector kmpMoveRequest;
    protected boolean willAnim;
    protected java.awt.Point animFrom;
    protected java.awt.Point animTo;
    protected java.awt.Color textColor;
    protected java.awt.Color highlightColor;
    protected java.awt.Color nodeColor;
    protected java.awt.Color background;
    protected boolean drawSymbolArrayAndLines;
    
    public KMP(com.cim.AIA.AlgorithmThread a, Object a0)
    {
        super(a, a0);
        java.util.Vector a1 = new java.util.Vector();
        this.kmpMoveRequest = a1;
        java.awt.Color a2 = java.awt.Color.black;
        this.textColor = a2;
        java.awt.Color a3 = java.awt.Color.yellow;
        this.highlightColor = a3;
        java.awt.Color a4 = java.awt.Color.orange;
        this.nodeColor = a4;
        java.awt.Color a5 = java.awt.Color.white;
        this.background = a5;
        this.drawSymbolArrayAndLines = true;
        String[] dummy = (String[])a0;
        String[] a6 = (String[])a0;
        this.data = a6;
        this.initialise();
        KMP.currentVariation = 1;
        com.cim.AIA.ConfigurationManager a7 = com.cim.AIA.ConfigurationManager.getConfigurationManager();
        java.awt.Color a8 = this.highlightColor;
        String s = KMP.HIGHLIGHT_COLOR;
        com.cim.AIA.ColorSelection a9 = new com.cim.AIA.ColorSelection(a8, s);
        a9.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        a7.addColorSelection(a9);
        java.awt.Color a10 = this.nodeColor;
        String s0 = KMP.NODE_COLOR;
        com.cim.AIA.ColorSelection a11 = new com.cim.AIA.ColorSelection(a10, s0);
        a11.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        a7.addColorSelection(a11);
        a7.addColorSelectionListener((com.cim.AIA.ColorSelectionListener)this);
    }
    
    protected void animMove(java.awt.Point a, java.awt.Point a0)
    {
        this.willAnim = true;
        this.animFrom = a;
        this.animTo = a0;
    }
    
    protected void calculateNextTable(String s)
    {
        int i = s.length();
        int[] a = this.fakeNextTable;
        a[0] = -1;
        int[] a0 = this.nextTable;
        a0[0] = -1;
        int i0 = 0;
        int i1 = -1;
        while(true)
        {
            int i2 = 0;
            if(i0 >= i)
            {
                return;
            }
            else
            {
                i2 = i1;
            }
            while(true)
            {
                label0: {
                    if(i2 < 0)
                    {
                        break label0;
                    }
                    int i3 = s.charAt(i0);
                    int i4 = s.charAt(i2);
                    if(i3 != i4)
                    {
                        int[] a1 = this.nextTable;
                        int i5 = a1[i2];
                        i2 = i5;
                        continue;
                    }
                }
                int i6 = i0 + 1;
                int i7 = i2 + 1;
                int[] a2 = this.nextTable;
                a2[i6] = i7;
                int[] a3 = this.fakeNextTable;
                a3[i6] = i7;
                i0 = i6;
                i1 = i7;
            }
        }
    }
    
    protected void calculateOptNextTable(String s)
    {
        int i = s.length();
        int[] a = this.nextTable;
        a[0] = -1;
        int i0 = 0;
        int i1 = -1;
        while(true)
        {
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            if(i0 >= i)
            {
                return;
            }
            else
            {
                i2 = i1;
            }
            while(true)
            {
                if(i2 < 0)
                {
                    break;
                }
                int i5 = s.charAt(i0);
                int i6 = s.charAt(i2);
                if(i5 == i6)
                {
                    break;
                }
                else
                {
                    int[] a0 = this.nextTable;
                    int i7 = a0[i2];
                    i2 = i7;
                }
            }
            label0: {
                i3 = i0 + 1;
                i4 = i2 + 1;
                if(i3 >= i)
                {
                    break label0;
                }
                int i8 = s.charAt(i3);
                int i9 = s.charAt(i4);
                if(i8 == i9)
                {
                    int[] a1 = this.nextTable;
                    int[] a2 = this.nextTable;
                    int i10 = a2[i4];
                    a1[i3] = i10;
                    i0 = i3;
                    i1 = i4;
                    continue;
                }
            }
            int[] a3 = this.nextTable;
            a3[i3] = i4;
            i0 = i3;
            i1 = i4;
        }
    }
    
    protected java.util.Vector generateQuestions()
    {
        return null;
    }
    
    public com.cim.AIA.MultipleTween generateTweens(com.cim.AIA.Paintable a, Object a0, int i)
    {
        Object a1 = a;
        com.cim.AIA.MultipleTween a2 = new com.cim.AIA.MultipleTween((com.cim.AIA.Paintable)a1);
        int i0 = 0;
        while(true)
        {
            java.util.Vector a3 = this.kmpMoveRequest;
            int i1 = a3.size();
            if(i0 >= i1)
            {
                break;
            }
            else
            {
                java.util.Vector a4 = this.kmpMoveRequest;
                Object a5 = a4.elementAt(i0);
                KMPMoveRequest dummy = (KMPMoveRequest)a5;
                KMPMoveRequest a6 = (KMPMoveRequest)a5;
                KMPString a7 = a6.theString;
                java.awt.Point a8 = a6.from;
                java.awt.Point a9 = a6.to;
                com.cim.AIA.MoveTween a10 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Moveable)a7, a8, a9, true, i);
                a2.add((com.cim.AIA.Tween)a10);
                int i2 = i0 + 1;
                i0 = i2;
            }
        }
        int i3 = this.willAnim?1:0;
        if(i3 != 0)
        {
            com.cim.AIA.Node a11 = this.animNode;
            java.awt.Point a12 = this.animFrom;
            java.awt.Point a13 = this.animTo;
            com.cim.AIA.MoveTween a14 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Moveable)a11, a12, a13, true, i);
            a2.add((com.cim.AIA.Tween)a14);
        }
        return a2;
    }
    
    public com.cim.AIA.Node getAnimNode()
    {
        com.cim.AIA.Node a = this.animNode;
        return a;
    }
    
    public String getClassName()
    {
        return "KMP";
    }
    
    public com.cim.AIA.Node getIPointer()
    {
        com.cim.AIA.Node a = null;
        int i = this.showIPointer?1:0;
        if(i == 0)
        {
            a = null;
        }
        else
        {
            StringBuilder a0 = new StringBuilder();
            StringBuilder a1 = a0.append("");
            int i0 = this.theI;
            StringBuilder a2 = a1.append(i0);
            String s = a2.toString();
            com.cim.AIA.Node a3 = new com.cim.AIA.Node((Object)s, 0);
            this.iPointer = a3;
            com.cim.AIA.Node a4 = this.iPointer;
            a = a4;
        }
        return a;
    }
    
    public com.cim.AIA.Node getJPointer()
    {
        com.cim.AIA.Node a = null;
        int i = this.showJPointer?1:0;
        label1: {
            label0: {
                if(i != 0)
                {
                    break label0;
                }
                a = null;
                break label1;
            }
            StringBuilder a0 = new StringBuilder();
            StringBuilder a1 = a0.append("");
            int i0 = this.theJ;
            StringBuilder a2 = a1.append(i0);
            String s = a2.toString();
            com.cim.AIA.Node a3 = new com.cim.AIA.Node((Object)s, 0);
            this.jPointer = a3;
            int i1 = this.highlightJPointer?1:0;
            if(i1 != 0)
            {
                com.cim.AIA.Node a4 = this.jPointer;
                a4.highlight();
            }
            com.cim.AIA.Node a5 = this.jPointer;
            a = a5;
        }
        return a;
    }
    
    public int getKMPResult()
    {
        int i = this.kmpResult;
        return i;
    }
    
    public KMPString getKMPString1()
    {
        KMPString a = this.theKMPString1;
        return a;
    }
    
    public KMPString getKMPString2()
    {
        KMPString a = this.theKMPString2;
        return a;
    }
    
    public KMPNextTable getNextTable()
    {
        KMPNextTable a = this.theNextTable;
        return a;
    }
    
    public KMPString getTestKMPString1()
    {
        KMPString a = this.testKMPString1;
        return a;
    }
    
    public KMPString getTestKMPString2()
    {
        KMPString a = this.testKMPString2;
        return a;
    }
    
    protected boolean hasQuestions()
    {
        return false;
    }
    
    protected void initialise()
    {
        StringBuilder a = new StringBuilder();
        String[] a0 = this.data;
        String s = a0[0];
        StringBuilder a1 = a.append(s);
        StringBuilder a2 = a1.append(" ");
        String s0 = a2.toString();
        KMPString a3 = new KMPString(s0);
        this.theKMPString1 = a3;
        StringBuilder a4 = new StringBuilder();
        String[] a5 = this.data;
        String s1 = a5[1];
        StringBuilder a6 = a4.append(s1);
        StringBuilder a7 = a6.append(" ");
        String s2 = a7.toString();
        KMPString a8 = new KMPString(s2);
        this.theKMPString2 = a8;
        this.theNextTable = null;
        this.testKMPString1 = null;
        this.testKMPString1 = null;
        this.jPointer = null;
        this.iPointer = null;
        this.animNode = null;
        this.showJPointer = false;
        this.showIPointer = false;
        this.highlightJPointer = false;
        this.willAnim = false;
        this.kmpResult = -1;
    }
    
    protected void kmpMove(KMPString a, int i, int i0)
    {
        int i1 = a.getX();
        int i2 = a.getY();
        int i3 = a.getX();
        int i4 = i3 + i;
        int i5 = a.getY();
        int i6 = i5 + i0;
        KMPMoveRequest a0 = new KMPMoveRequest(a, i1, i2, i4, i6);
        int i7 = a.getX();
        int i8 = i7 + i;
        int i9 = a.getY();
        int i10 = i9 + i0;
        a.setLocation(i8, i10);
        java.util.Vector a1 = this.kmpMoveRequest;
        a1.addElement((Object)a0);
    }
    
    protected void kmpNoOpt()
    {
        String[] a = this.data;
        String s = a[1];
        String[] a0 = this.data;
        String s0 = a0[0];
        int i = s0.length();
        int i0 = s.length();
        this.setPosition("0");
        KMPNextTable a1 = new KMPNextTable(i);
        this.theNextTable = a1;
        int i1 = i + 1;
        int[] a2 = new int[i1];
        this.fakeNextTable = a2;
        int i2 = i + 1;
        int[] a3 = new int[i2];
        this.nextTable = a3;
        this.calculateNextTable(s0);
        KMPNextTable a4 = this.theNextTable;
        a4.highlightIndex();
        KMPNextTable a5 = this.theNextTable;
        a5.setLabelIndex(0);
        KMPNextTable a6 = this.theNextTable;
        a6.set(0, -1);
        this.setPosition("1.2.1");
        int i3 = 1;
        while(true)
        {
            if(i3 >= i)
            {
                KMPNextTable a7 = this.theNextTable;
                a7.unHighlightIndex();
                this.setPosition("1.2.2");
                KMPString a8 = this.theKMPString1;
                a8.unHighlightAll();
                this.testKMPString1 = null;
                this.testKMPString2 = null;
                this.removeAllAnimationRequests();
                this.setPosition("1.2.3");
                return;
            }
            KMPNextTable a9 = this.theNextTable;
            a9.setLabelIndex(i3);
            this.setPosition("1.2.2");
            String s1 = s0.substring(0, i3);
            KMPString a10 = new KMPString(s1);
            this.testKMPString1 = a10;
            String s2 = s0.substring(0, i3);
            KMPString a11 = new KMPString(s2);
            this.testKMPString2 = a11;
            KMPString a12 = this.theKMPString1;
            int i4 = i3 - 1;
            a12.highlight(i4);
            this.setPosition("1.2.2.1");
            KMPString a13 = this.testKMPString2;
            this.kmpMove(a13, 20, 0);
            this.setPosition("1.2.2.2.0");
            KMPString a14 = this.testKMPString1;
            java.awt.Color a15 = new java.awt.Color(255, 90, 90);
            a14.setHighlightColor(a15);
            KMPString a16 = this.testKMPString2;
            java.awt.Color a17 = new java.awt.Color(255, 90, 90);
            a16.setHighlightColor(a17);
            int i5 = 1;
            while(true)
            {
                int[] a18 = this.fakeNextTable;
                int i6 = a18[i3];
                int i7 = i3 - i6;
                if(i5 >= i7)
                {
                    break;
                }
                else
                {
                    KMPString a19 = this.testKMPString2;
                    KMPString a20 = this.testKMPString2;
                    int i8 = a20.length;
                    int i9 = i8 - i5;
                    a19.highlight(0, i9);
                    KMPString a21 = this.testKMPString1;
                    KMPString a22 = this.testKMPString1;
                    int i10 = a22.length;
                    a21.highlight(i5, i10);
                    this.setPosition("1.2.2.2.1");
                    KMPString a23 = this.testKMPString2;
                    this.kmpMove(a23, 20, 0);
                    KMPString a24 = this.testKMPString1;
                    a24.unHighlightAll();
                    KMPString a25 = this.testKMPString2;
                    a25.unHighlightAll();
                    this.setPosition("1.2.2.2.2");
                    int i11 = i5 + 1;
                    i5 = i11;
                }
            }
            KMPString a26 = this.testKMPString1;
            java.awt.Color a27 = java.awt.Color.green;
            a26.setHighlightColor(a27);
            KMPString a28 = this.testKMPString2;
            java.awt.Color a29 = java.awt.Color.green;
            a28.setHighlightColor(a29);
            int[] a30 = this.nextTable;
            int i12 = a30[i3];
            if(i12 > 0)
            {
                KMPString a31 = this.testKMPString2;
                int[] a32 = this.nextTable;
                int i13 = a32[i3];
                a31.highlight(0, i13);
                KMPString a33 = this.testKMPString1;
                KMPString a34 = this.testKMPString1;
                int i14 = a34.length();
                int[] a35 = this.nextTable;
                int i15 = a35[i3];
                int i16 = i14 - i15;
                KMPString a36 = this.testKMPString1;
                int i17 = a36.length();
                a33.highlight(i16, i17);
            }
            this.setPosition("1.2.2.2.1");
            this.setPosition("1.2.2.2.3");
            KMPNextTable a37 = this.theNextTable;
            int[] a38 = this.nextTable;
            int i18 = a38[i3];
            a37.set(i3, i18);
            KMPString a39 = this.testKMPString1;
            a39.unHighlightAll();
            KMPString a40 = this.testKMPString2;
            a40.unHighlightAll();
            this.setPosition("1.2.2.3");
            int i19 = i3 + 1;
            i3 = i19;
        }
    }
    
    protected void kmpSearch()
    {
        int i = 0;
        String[] a = this.data;
        String s = a[1];
        String[] a0 = this.data;
        String s0 = a0[0];
        int i0 = s0.length();
        int i1 = s.length();
        int i2 = s0.length();
        KMPString a1 = this.theKMPString2;
        a1.setLabel("i");
        KMPString a2 = this.theKMPString2;
        a2.setShowLabel(true);
        KMPString a3 = this.theKMPString2;
        a3.setHighLabel(false);
        KMPString a4 = this.theKMPString2;
        a4.setLabelIndex(0);
        this.setPosition("2.1.1");
        KMPString a5 = this.theKMPString1;
        a5.setLabel("j");
        KMPString a6 = this.theKMPString1;
        a6.setShowLabel(true);
        KMPString a7 = this.theKMPString1;
        a7.setLabelIndex(0);
        this.showJPointer = true;
        this.theJ = 0;
        this.setPosition("2.1.2");
        int i3 = 0;
        int i4 = 0;
        while(true)
        {
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            int i8 = 0;
            int i9 = i3;
            if(i3 >= i1)
            {
                i = i3;
                break;
            }
            else
            {
                i5 = i9;
            }
            int i10 = i5;
            if(i4 >= i2)
            {
                i = i5;
                break;
            }
            else
            {
                i6 = i10;
            }
            this.setPosition("2.3");
            int i11 = i6;
            int i12 = s.charAt(i11);
            int i13 = i11;
            int i14 = s0.charAt(i4);
            int i15 = i13;
            label1: {
                int i16 = 0;
                int i17 = 0;
                int i18 = 0;
                int i19 = 0;
                label0: {
                    int i20 = i15;
                    if(i12 != i14)
                    {
                        i16 = i20;
                        break label0;
                    }
                    KMPString a8 = this.theKMPString1;
                    java.awt.Color a9 = java.awt.Color.green;
                    a8.setHighlightColor(a9);
                    KMPString a10 = this.theKMPString2;
                    java.awt.Color a11 = java.awt.Color.green;
                    a10.setHighlightColor(a11);
                    KMPString a12 = this.theKMPString1;
                    a12.highlight(i4);
                    KMPString a13 = this.theKMPString2;
                    a13.highlight(i15);
                    this.setPosition("2.3.1");
                    this.setPosition("2.3.2");
                    int i21 = i15 + 1;
                    KMPString a14 = this.theKMPString2;
                    a14.setLabelIndex(i21);
                    int i22 = i4 + 1;
                    this.theJ = i22;
                    KMPString a15 = this.theKMPString1;
                    a15.setLabelIndex(i22);
                    this.setPosition("2.3.2.1.1");
                    i7 = i21;
                    i8 = i22;
                    break label1;
                }
                KMPNextTable a16 = this.theNextTable;
                int i23 = i16;
                a16.highlightIndex();
                int i24 = i23;
                KMPNextTable a17 = this.theNextTable;
                int i25 = i24;
                a17.setLabelIndex(i4);
                int i26 = i25;
                KMPString a18 = this.theKMPString1;
                int i27 = i26;
                int i28 = i27;
                java.awt.Color a19 = new java.awt.Color(255, 90, 90);
                int i29 = i28;
                a18.setHighlightColor(a19);
                int i30 = i29;
                KMPString a20 = this.theKMPString2;
                int i31 = i30;
                int i32 = i31;
                java.awt.Color a21 = new java.awt.Color(255, 90, 90);
                int i33 = i32;
                a20.setHighlightColor(a21);
                int i34 = i33;
                KMPString a22 = this.theKMPString1;
                int i35 = i34;
                a22.highlight(i4);
                int i36 = i35;
                KMPString a23 = this.theKMPString2;
                int i37 = i36;
                a23.highlight(i37);
                int i38 = i37;
                this.setPosition("2.3.1");
                int i39 = i38;
                KMPNextTable a24 = this.theNextTable;
                int i40 = i39;
                a24.highlight(i4);
                int i41 = i40;
                int[] a25 = this.nextTable;
                int i42 = i41;
                int i43 = a25[i4];
                int i44 = i42;
                this.theJ = i43;
                int i45 = i44;
                KMPString a26 = this.theKMPString1;
                int i46 = i45;
                a26.setLabelIndex(i43);
                int i47 = i46;
                int i48 = i47;
                int i49 = i48;
                StringBuilder a27 = new StringBuilder();
                int i50 = i49;
                StringBuilder a28 = a27.append("");
                int i51 = i50;
                StringBuilder a29 = a28.append(i43);
                int i52 = i51;
                String s1 = a29.toString();
                int i53 = i52;
                com.cim.AIA.Node a30 = new com.cim.AIA.Node((Object)s1, 0);
                int i54 = i53;
                this.animNode = a30;
                int i55 = i54;
                com.cim.AIA.Node a31 = this.animNode;
                int i56 = i55;
                KMPNextTable a32 = this.theNextTable;
                int i57 = i56;
                java.awt.Point a33 = a32.getNodePosition(i4);
                int i58 = i57;
                a31.setPosition(a33);
                int i59 = i58;
                com.cim.AIA.Node a34 = this.jPointer;
                int i60 = i59;
                int i61 = i60;
                int i62 = i60;
                if(a34 == null)
                {
                    i17 = i62;
                }
                else
                {
                    int i63 = i61;
                    KMPNextTable a35 = this.theNextTable;
                    int i64 = i63;
                    java.awt.Point a36 = a35.getNodePosition(i4);
                    int i65 = i64;
                    com.cim.AIA.Node a37 = this.jPointer;
                    int i66 = i65;
                    java.awt.Point a38 = a37.getPosition();
                    int i67 = i66;
                    this.animMove(a36, a38);
                    i17 = i67;
                }
                this.setPosition("2.3.1.2.1");
                int i68 = i17;
                KMPString a39 = this.theKMPString1;
                int i69 = i68;
                int i70 = i4 - i43;
                int i71 = i70 * 20;
                this.kmpMove(a39, i71, 0);
                int i72 = i69;
                KMPString a40 = this.theKMPString1;
                int i73 = i72;
                a40.unHighlightAll();
                int i74 = i73;
                KMPString a41 = this.theKMPString2;
                int i75 = i74;
                a41.unHighlightAll();
                int i76 = i75;
                KMPNextTable a42 = this.theNextTable;
                int i77 = i76;
                a42.setLabelIndex(i43);
                int i78 = i77;
                KMPNextTable a43 = this.theNextTable;
                int i79 = i78;
                a43.unHighlightAll();
                int i80 = i79;
                this.setPosition("2.3.1.2.2");
                int i81 = i80;
                int i82 = i81;
                if(i43 != -1)
                {
                    i18 = i82;
                    i19 = i43;
                }
                else
                {
                    this.theJ = 0;
                    int i83 = i81 + 1;
                    KMPString a44 = this.theKMPString1;
                    a44.setLabelIndex(0);
                    KMPString a45 = this.theKMPString2;
                    a45.setLabelIndex(i83);
                    KMPNextTable a46 = this.theNextTable;
                    a46.setLabelIndex(0);
                    i18 = i83;
                    i19 = 0;
                }
                this.setPosition("2.3.1.3");
                int i84 = i18;
                i7 = i84;
                i8 = i19;
            }
            KMPNextTable a47 = this.theNextTable;
            int i85 = i7;
            a47.unHighlightIndex();
            int i86 = i85;
            KMPString a48 = this.theKMPString1;
            int i87 = i86;
            a48.unHighlightAll();
            int i88 = i87;
            KMPString a49 = this.theKMPString2;
            int i89 = i88;
            a49.unHighlightAll();
            int i90 = i89;
            i3 = i90;
            i4 = i8;
        }
        this.setPosition("2.3");
        KMPNextTable a50 = this.theNextTable;
        a50.unHighlightIndex();
        this.setPosition("2.3.3");
        this.removeAllAnimationRequests();
        KMPString a51 = this.theKMPString1;
        java.awt.Color a52 = java.awt.Color.green;
        a51.setHighlightColor(a52);
        KMPString a53 = this.theKMPString2;
        java.awt.Color a54 = java.awt.Color.green;
        a53.setHighlightColor(a54);
        if(i4 != i2)
        {
            this.setPosition("2.4.1");
            this.setPosition("2.4.2");
            this.kmpResult = 0;
            this.setPosition("2.4.2.1");
        }
        else
        {
            this.setPosition("2.4.1");
            KMPString a55 = this.theKMPString2;
            int i91 = i - i2;
            a55.highlight(i91, i);
            this.kmpResult = 1;
            this.setPosition("2.4.1.1");
        }
        this.showJPointer = false;
        this.setPosition("3");
    }
    
    protected void kmpWithOpt()
    {
        String[] a = this.data;
        String s = a[1];
        String[] a0 = this.data;
        String s0 = a0[0];
        int i = s0.length();
        int i0 = s.length();
        int i1 = s0.length();
        this.setPosition("0");
        KMPNextTable a1 = new KMPNextTable(i);
        this.theNextTable = a1;
        int i2 = i + 1;
        int[] a2 = new int[i2];
        this.fakeNextTable = a2;
        int i3 = i + 1;
        int[] a3 = new int[i3];
        this.nextTable = a3;
        this.calculateNextTable(s0);
        this.calculateOptNextTable(s0);
        KMPNextTable a4 = this.theNextTable;
        a4.highlightIndex();
        KMPNextTable a5 = this.theNextTable;
        a5.setLabelIndex(0);
        KMPNextTable a6 = this.theNextTable;
        a6.set(0, -1);
        this.setPosition("1.2.1");
        KMPNextTable a7 = this.theNextTable;
        java.awt.Color a8 = com.cim.AIA.Node.DEFAULT_HIGHLIGHT_COLOR;
        a7.setHighlightColor(a8);
        int i4 = 1;
        while(true)
        {
            if(i4 >= i)
            {
                KMPNextTable a9 = this.theNextTable;
                a9.unHighlightIndex();
                this.setPosition("1.2.2");
                KMPString a10 = this.theKMPString1;
                a10.unHighlightAll();
                this.testKMPString1 = null;
                this.testKMPString2 = null;
                this.removeAllAnimationRequests();
                this.showJPointer = false;
                this.showIPointer = false;
                this.setPosition("1.2.3");
                return;
            }
            KMPNextTable a11 = this.theNextTable;
            a11.setLabelIndex(i4);
            this.theI = i4;
            this.setPosition("1.2.2");
            String s1 = s0.substring(0, i4);
            KMPString a12 = new KMPString(s1);
            this.testKMPString1 = a12;
            String s2 = s0.substring(0, i4);
            KMPString a13 = new KMPString(s2);
            this.testKMPString2 = a13;
            KMPString a14 = this.theKMPString1;
            a14.highlight(0, i4);
            this.showIPointer = true;
            this.setPosition("1.2.2.1");
            KMPString a15 = this.testKMPString2;
            this.kmpMove(a15, 20, 0);
            this.setPosition("1.2.2.2.0");
            KMPString a16 = this.testKMPString1;
            java.awt.Color a17 = new java.awt.Color(255, 90, 90);
            a16.setHighlightColor(a17);
            KMPString a18 = this.testKMPString2;
            java.awt.Color a19 = new java.awt.Color(255, 90, 90);
            a18.setHighlightColor(a19);
            int i5 = 1;
            while(true)
            {
                int[] a20 = this.fakeNextTable;
                int i6 = a20[i4];
                int i7 = i4 - i6;
                if(i5 >= i7)
                {
                    break;
                }
                else
                {
                    KMPString a21 = this.testKMPString2;
                    KMPString a22 = this.testKMPString2;
                    int i8 = a22.length;
                    int i9 = i8 - i5;
                    a21.highlight(0, i9);
                    KMPString a23 = this.testKMPString1;
                    KMPString a24 = this.testKMPString1;
                    int i10 = a24.length;
                    a23.highlight(i5, i10);
                    this.setPosition("1.2.2.2.1");
                    KMPString a25 = this.testKMPString2;
                    this.kmpMove(a25, 20, 0);
                    KMPString a26 = this.testKMPString1;
                    a26.unHighlightAll();
                    KMPString a27 = this.testKMPString2;
                    a27.unHighlightAll();
                    this.setPosition("1.2.2.2.2");
                    int i11 = i5 + 1;
                    i5 = i11;
                }
            }
            KMPString a28 = this.testKMPString1;
            java.awt.Color a29 = java.awt.Color.green;
            a28.setHighlightColor(a29);
            KMPString a30 = this.testKMPString2;
            java.awt.Color a31 = java.awt.Color.green;
            a30.setHighlightColor(a31);
            int[] a32 = this.fakeNextTable;
            int i12 = a32[i4];
            if(i12 > 0)
            {
                KMPString a33 = this.testKMPString2;
                int[] a34 = this.fakeNextTable;
                int i13 = a34[i4];
                a33.highlight(0, i13);
                KMPString a35 = this.testKMPString1;
                KMPString a36 = this.testKMPString1;
                int i14 = a36.length();
                int[] a37 = this.fakeNextTable;
                int i15 = a37[i4];
                int i16 = i14 - i15;
                KMPString a38 = this.testKMPString1;
                int i17 = a38.length();
                a35.highlight(i16, i17);
            }
            this.setPosition("1.2.2.2.1");
            this.setPosition("1.2.2.2.3");
            int[] a39 = this.fakeNextTable;
            int i18 = a39[i4];
            this.theJ = i18;
            this.showJPointer = true;
            this.theI = i4;
            this.setPosition("1.2.2.3");
            KMPString a40 = this.theKMPString1;
            a40.unHighlightAll();
            int i19 = this.theI;
            int i20 = s0.charAt(i19);
            int i21 = this.theJ;
            int i22 = s0.charAt(i21);
            if(i20 != i22)
            {
                KMPString a41 = this.theKMPString1;
                java.awt.Color a42 = new java.awt.Color(255, 90, 90);
                a41.setHighlightColor(a42);
                KMPString a43 = this.theKMPString1;
                a43.highlight(i4);
                KMPString a44 = this.theKMPString1;
                int i23 = this.theJ;
                a44.highlight(i23);
                this.setPosition("1.2.2.5");
                this.setPosition("1.2.2.7");
                KMPNextTable a45 = this.theNextTable;
                int[] a46 = this.nextTable;
                int i24 = a46[i4];
                a45.set(i4, i24);
                KMPNextTable a47 = this.theNextTable;
                a47.highlight(i4);
                this.highlightJPointer = true;
                this.setPosition("1.2.2.8");
            }
            else
            {
                KMPString a48 = this.theKMPString1;
                java.awt.Color a49 = java.awt.Color.green;
                a48.setHighlightColor(a49);
                KMPString a50 = this.theKMPString1;
                a50.highlight(i4);
                KMPString a51 = this.theKMPString1;
                int i25 = this.theJ;
                a51.highlight(i25);
                this.setPosition("1.2.2.5");
                KMPNextTable a52 = this.theNextTable;
                int[] a53 = this.nextTable;
                int i26 = a53[i4];
                a52.set(i4, i26);
                KMPNextTable a54 = this.theNextTable;
                a54.highlight(i4);
                KMPNextTable a55 = this.theNextTable;
                int i27 = this.theJ;
                a55.highlight(i27);
                this.setPosition("1.2.2.6");
            }
            this.showJPointer = false;
            this.highlightJPointer = false;
            KMPNextTable a56 = this.theNextTable;
            a56.unHighlightAll();
            KMPString a57 = this.theKMPString1;
            java.awt.Color a58 = java.awt.Color.green;
            a57.setHighlightColor(a58);
            KMPString a59 = this.theKMPString1;
            a59.unHighlightAll();
            int i28 = i4 + 1;
            i4 = i28;
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
                this.background = a0;
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
                this.textColor = a1;
                break label1;
            }
            String s2 = KMP.HIGHLIGHT_COLOR;
            int i1 = s.equalsIgnoreCase(s2)?1:0;
            label3: {
                if(i1 == 0)
                {
                    break label3;
                }
                java.awt.Color a2 = a.getColor();
                this.highlightColor = a2;
                break label1;
            }
            String s3 = KMP.NODE_COLOR;
            int i2 = s.equalsIgnoreCase(s3)?1:0;
            if(i2 != 0)
            {
                java.awt.Color a3 = a.getColor();
                this.nodeColor = a3;
            }
        }
    }
    
    public void reInitialise(Object a)
    {
        String[] dummy = (String[])a;
        String[] a0 = (String[])a;
        this.data = a0;
        this.initialise();
    }
    
    protected void removeAllAnimationRequests()
    {
        java.util.Vector a = this.kmpMoveRequest;
        a.removeAllElements();
        this.willAnim = false;
        this.animNode = null;
    }
    
    protected void removeAllQuestions()
    {
    }
    
    protected void run()
    {
        int i = KMP.currentVariation;
        if(i == 1)
        {
            this.kmpNoOpt();
        }
        int i0 = KMP.currentVariation;
        if(i0 == 2)
        {
            this.kmpWithOpt();
        }
        this.kmpSearch();
    }
    
    public void setVariation(int i)
    {
        KMP.currentVariation = i;
    }
    
    static
    {
        String s = localization.Messages.getString("KMP.0");
        KMP.HIGHLIGHT_COLOR = s;
        String s0 = localization.Messages.getString("KMP.1");
        KMP.NODE_COLOR = s0;
    }
}