public class BoyerMoore extends com.cim.AIA.Algorithm implements com.cim.AIA.ColorSelectionListener {
    final public static int EMPTY_MARKER = -10;
    final protected static String HIGHLIGHT_COLOR;
    final protected static String NODE_COLOR;
    protected String[] data;
    protected BoyerMooreString theBoyerMooreString1;
    protected BoyerMooreString theBoyerMooreString2;
    protected BoyerMooreString testBoyerMooreString1;
    protected BoyerMooreString testBoyerMooreString2;
    protected String skipTableS;
    protected com.cim.AIA.Node skipTableN;
    protected com.cim.AIA.Node patternN;
    protected com.cim.AIA.Node skipN;
    protected com.cim.AIA.Node mi1N;
    protected BoyerMooreNextTable theNextTable;
    protected BoyerMooreSkipTable theSkipTable;
    protected int BoyerMooreResult;
    protected int[] nextTable;
    protected java.util.Vector BoyerMooreMoveRequest;
    protected java.awt.Color textColor;
    protected java.awt.Color highlightColor;
    protected java.awt.Color nodeColor;
    protected java.awt.Color background;
    protected boolean drawSymbolArrayAndLines;
    
    public BoyerMoore(com.cim.AIA.AlgorithmThread a, Object a0)
    {
        super(a, a0);
        java.util.Vector a1 = new java.util.Vector();
        this.BoyerMooreMoveRequest = a1;
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
        com.cim.AIA.ConfigurationManager a7 = com.cim.AIA.ConfigurationManager.getConfigurationManager();
        java.awt.Color a8 = this.highlightColor;
        String s = BoyerMoore.HIGHLIGHT_COLOR;
        com.cim.AIA.ColorSelection a9 = new com.cim.AIA.ColorSelection(a8, s);
        a9.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        a7.addColorSelection(a9);
        java.awt.Color a10 = this.nodeColor;
        String s0 = BoyerMoore.NODE_COLOR;
        com.cim.AIA.ColorSelection a11 = new com.cim.AIA.ColorSelection(a10, s0);
        a11.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        a7.addColorSelection(a11);
        a7.addColorSelectionListener((com.cim.AIA.ColorSelectionListener)this);
    }
    
    protected void BoyerMooreMove(BoyerMooreString a, int i, int i0)
    {
        int i1 = a.getX();
        int i2 = a.getY();
        int i3 = a.getX();
        int i4 = i3 + i;
        int i5 = a.getY();
        int i6 = i5 + i0;
        BoyerMooreMoveRequest a0 = new BoyerMooreMoveRequest(a, i1, i2, i4, i6);
        int i7 = a.getX();
        int i8 = i7 + i;
        int i9 = a.getY();
        int i10 = i9 + i0;
        a.setLocation(i8, i10);
        java.util.Vector a1 = this.BoyerMooreMoveRequest;
        a1.addElement((Object)a0);
    }
    
    protected void calculateNextTable(String s)
    {
        int i = s.length();
        int[] a = this.nextTable;
        a[0] = -1;
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
                        int[] a0 = this.nextTable;
                        int i5 = a0[i2];
                        i2 = i5;
                        continue;
                    }
                }
                int i6 = i0 + 1;
                int i7 = i2 + 1;
                int[] a1 = this.nextTable;
                a1[i6] = i7;
                i0 = i6;
                i1 = i7;
            }
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
            java.util.Vector a3 = this.BoyerMooreMoveRequest;
            int i1 = a3.size();
            if(i0 >= i1)
            {
                return a2;
            }
            else
            {
                java.util.Vector a4 = this.BoyerMooreMoveRequest;
                Object a5 = a4.elementAt(i0);
                BoyerMooreMoveRequest dummy = (BoyerMooreMoveRequest)a5;
                BoyerMooreMoveRequest a6 = (BoyerMooreMoveRequest)a5;
                BoyerMooreString a7 = a6.theString;
                java.awt.Point a8 = a6.from;
                java.awt.Point a9 = a6.to;
                com.cim.AIA.MoveTween a10 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Moveable)a7, a8, a9, true, i);
                a2.add((com.cim.AIA.Tween)a10);
                int i2 = i0 + 1;
                i0 = i2;
            }
        }
    }
    
    public int getBoyerMooreResult()
    {
        int i = this.BoyerMooreResult;
        return i;
    }
    
    public BoyerMooreString getBoyerMooreString1()
    {
        BoyerMooreString a = this.theBoyerMooreString1;
        return a;
    }
    
    public BoyerMooreString getBoyerMooreString2()
    {
        BoyerMooreString a = this.theBoyerMooreString2;
        return a;
    }
    
    public String getClassName()
    {
        return "BoyerMoore";
    }
    
    public com.cim.AIA.Node getMi1N()
    {
        com.cim.AIA.Node a = this.mi1N;
        return a;
    }
    
    public BoyerMooreNextTable getNextTable()
    {
        BoyerMooreNextTable a = this.theNextTable;
        return a;
    }
    
    public com.cim.AIA.Node getPatternN()
    {
        com.cim.AIA.Node a = this.patternN;
        return a;
    }
    
    public com.cim.AIA.Node getSkipN()
    {
        com.cim.AIA.Node a = this.skipN;
        return a;
    }
    
    public BoyerMooreSkipTable getSkipTable()
    {
        BoyerMooreSkipTable a = this.theSkipTable;
        return a;
    }
    
    public com.cim.AIA.Node getSkipTableN()
    {
        com.cim.AIA.Node a = this.skipTableN;
        return a;
    }
    
    public String getSkipTableS()
    {
        String s = this.skipTableS;
        return s;
    }
    
    public BoyerMooreString getTestBoyerMooreString1()
    {
        BoyerMooreString a = this.testBoyerMooreString1;
        return a;
    }
    
    public BoyerMooreString getTestBoyerMooreString2()
    {
        BoyerMooreString a = this.testBoyerMooreString2;
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
        BoyerMooreString a3 = new BoyerMooreString(s0);
        this.theBoyerMooreString1 = a3;
        StringBuilder a4 = new StringBuilder();
        String[] a5 = this.data;
        String s1 = a5[1];
        StringBuilder a6 = a4.append(s1);
        StringBuilder a7 = a6.append(" ");
        String s2 = a7.toString();
        BoyerMooreString a8 = new BoyerMooreString(s2);
        this.theBoyerMooreString2 = a8;
        this.theNextTable = null;
        this.theSkipTable = null;
        this.testBoyerMooreString1 = null;
        this.testBoyerMooreString1 = null;
        this.BoyerMooreResult = -1;
        this.skipTableN = null;
        this.skipTableS = null;
        this.patternN = null;
        this.skipN = null;
        this.mi1N = null;
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
            String s2 = BoyerMoore.HIGHLIGHT_COLOR;
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
            String s3 = BoyerMoore.NODE_COLOR;
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
        java.util.Vector a = this.BoyerMooreMoveRequest;
        a.removeAllElements();
    }
    
    protected void removeAllQuestions()
    {
    }
    
    protected void run()
    {
        String[] a = this.data;
        String s = a[1];
        String[] a0 = this.data;
        String s0 = a0[0];
        int i = s0.length();
        int i0 = s.length();
        int i1 = s0.length();
        this.setPosition("0");
        BoyerMooreSkipTable a1 = new BoyerMooreSkipTable(27);
        this.theSkipTable = a1;
        this.setPosition("1.1");
        int i2 = 0;
        while(true)
        {
            if(i2 >= 27)
            {
                break;
            }
            else
            {
                BoyerMooreSkipTable a2 = this.theSkipTable;
                a2.set(i2, i1);
                int i3 = i2 + 1;
                i2 = i3;
            }
        }
        this.setPosition("1.2");
        BoyerMooreString a3 = this.theBoyerMooreString1;
        a3.setLabel("j");
        BoyerMooreString a4 = this.theBoyerMooreString1;
        a4.setArrowLabelIndex(0);
        BoyerMooreString a5 = this.theBoyerMooreString1;
        a5.setArrowLabelTarget(0);
        BoyerMooreString a6 = this.theBoyerMooreString1;
        a6.setLabelIndex(0);
        StringBuilder a7 = new StringBuilder();
        StringBuilder a8 = a7.append("");
        int i4 = i1 - 0;
        int i5 = i4 - 1;
        StringBuilder a9 = a8.append(i5);
        String s1 = a9.toString();
        com.cim.AIA.Node a10 = new com.cim.AIA.Node((Object)s1, 0);
        this.mi1N = a10;
        this.setPosition("1.3");
        int i6 = 0;
        while(true)
        {
            if(i6 >= i1)
            {
                break;
            }
            else
            {
                BoyerMooreSkipTable a11 = this.theSkipTable;
                int i7 = s0.charAt(i6);
                a11.highlight(i7);
                BoyerMooreString a12 = this.theBoyerMooreString1;
                a12.highlight(i6);
                this.setPosition("1.4");
                BoyerMooreSkipTable a13 = this.theSkipTable;
                int i8 = s0.charAt(i6);
                int i9 = i1 - i6;
                int i10 = i9 - 1;
                a13.set(i8, i10);
                BoyerMooreSkipTable a14 = this.theSkipTable;
                int i11 = s0.charAt(i6);
                a14.highlight(i11);
                this.setPosition("1.4.1");
                BoyerMooreSkipTable a15 = this.theSkipTable;
                int i12 = s0.charAt(i6);
                a15.unHighlight(i12);
                BoyerMooreString a16 = this.theBoyerMooreString1;
                a16.unHighlightAll();
                BoyerMooreString a17 = this.theBoyerMooreString1;
                int i13 = i6 + 1;
                a17.setArrowLabelTarget(i13);
                BoyerMooreString a18 = this.theBoyerMooreString1;
                int i14 = i6 + 1;
                a18.setArrowLabelIndex(i14);
                StringBuilder a19 = new StringBuilder();
                StringBuilder a20 = a19.append("");
                int i15 = i1 - i6;
                int i16 = i15 - 2;
                StringBuilder a21 = a20.append(i16);
                String s2 = a21.toString();
                com.cim.AIA.Node a22 = new com.cim.AIA.Node((Object)s2, 0);
                this.mi1N = a22;
                this.setPosition("1.3");
                int i17 = i6 + 1;
                i6 = i17;
            }
        }
        BoyerMooreString a23 = this.theBoyerMooreString1;
        a23.setArrowLabelIndex(-1);
        BoyerMooreString a24 = this.theBoyerMooreString1;
        a24.setArrowLabelTarget(-1);
        BoyerMooreString a25 = this.theBoyerMooreString1;
        a25.setShowLabel(false);
        this.mi1N = null;
        this.setPosition("1.5");
        BoyerMooreString a26 = this.theBoyerMooreString2;
        a26.setLabel("i");
        BoyerMooreString a27 = this.theBoyerMooreString2;
        a27.setHighLabel(false);
        int i18 = i1 - 1;
        BoyerMooreString a28 = this.theBoyerMooreString2;
        a28.setArrowLabelIndex(i18);
        BoyerMooreString a29 = this.theBoyerMooreString2;
        a29.setArrowLabelTarget(i18);
        this.setPosition("3.1.1");
        BoyerMooreString a30 = this.theBoyerMooreString1;
        a30.setLabel("j");
        int i19 = i1 - 1;
        BoyerMooreString a31 = this.theBoyerMooreString1;
        a31.setArrowLabelIndex(i19);
        BoyerMooreString a32 = this.theBoyerMooreString1;
        a32.setArrowLabelTarget(i19);
        this.setPosition("3.1.2");
        int i20 = i18;
        int i21 = i19;
        while(true)
        {
            int i22 = 0;
            int i23 = 0;
            label4: {
                label2: {
                    int i24 = 0;
                    int i25 = 0;
                    label3: {
                        label1: {
                            label0: {
                                if(i21 >= 0)
                                {
                                    break label0;
                                }
                                this.setPosition("3.3");
                                this.setPosition("3.3.3");
                                this.removeAllAnimationRequests();
                                BoyerMooreString a33 = this.theBoyerMooreString1;
                                java.awt.Color a34 = java.awt.Color.green;
                                a33.setHighlightColor(a34);
                                BoyerMooreString a35 = this.theBoyerMooreString2;
                                java.awt.Color a36 = java.awt.Color.green;
                                a35.setHighlightColor(a36);
                                this.BoyerMooreResult = 1;
                                this.setPosition("3.4");
                                this.setPosition("4");
                                break label1;
                            }
                            this.setPosition("3.3");
                            int i26 = s.charAt(i20);
                            int i27 = s0.charAt(i21);
                            if(i26 == i27)
                            {
                                break label2;
                            }
                            BoyerMooreString a37 = this.theBoyerMooreString1;
                            a37.unHighlightAll();
                            BoyerMooreString a38 = this.theBoyerMooreString2;
                            a38.unHighlightAll();
                            BoyerMooreString a39 = this.theBoyerMooreString1;
                            java.awt.Color a40 = new java.awt.Color(255, 90, 90);
                            a39.setHighlightColor(a40);
                            BoyerMooreString a41 = this.theBoyerMooreString2;
                            java.awt.Color a42 = new java.awt.Color(255, 90, 90);
                            a41.setHighlightColor(a42);
                            BoyerMooreString a43 = this.theBoyerMooreString1;
                            a43.highlight(i21);
                            BoyerMooreString a44 = this.theBoyerMooreString2;
                            a44.highlight(i20);
                            this.setPosition("3.3.1");
                            BoyerMooreSkipTable a45 = this.theSkipTable;
                            int i28 = s.charAt(i20);
                            a45.highlight(i28);
                            BoyerMooreSkipTable a46 = this.theSkipTable;
                            int i29 = s.charAt(i20);
                            int i30 = a46.get(i29);
                            StringBuilder a47 = new StringBuilder();
                            int i31 = s.charAt(i20);
                            StringBuilder a48 = a47.append((char)i31);
                            StringBuilder a49 = a48.append("");
                            String s3 = a49.toString();
                            this.skipTableS = s3;
                            StringBuilder a50 = new StringBuilder();
                            StringBuilder a51 = a50.append("");
                            StringBuilder a52 = a51.append(i30);
                            String s4 = a52.toString();
                            com.cim.AIA.Node a53 = new com.cim.AIA.Node((Object)s4, 0);
                            this.skipTableN = a53;
                            this.setPosition("3.3.1.3");
                            StringBuilder a54 = new StringBuilder();
                            StringBuilder a55 = a54.append("");
                            int i32 = i1 - i21;
                            StringBuilder a56 = a55.append(i32);
                            String s5 = a56.toString();
                            com.cim.AIA.Node a57 = new com.cim.AIA.Node((Object)s5, 0);
                            this.patternN = a57;
                            this.setPosition("3.3.1.5");
                            BoyerMooreSkipTable a58 = this.theSkipTable;
                            int i33 = s.charAt(i20);
                            int i34 = a58.get(i33);
                            int i35 = i1 - i21;
                            if(i35 <= i34)
                            {
                                com.cim.AIA.Node a59 = this.skipTableN;
                                a59.highlight();
                                i24 = i34;
                            }
                            else
                            {
                                com.cim.AIA.Node a60 = this.patternN;
                                a60.highlight();
                                int i36 = i1 - i21;
                                i24 = i36;
                            }
                            StringBuilder a61 = new StringBuilder();
                            StringBuilder a62 = a61.append("");
                            StringBuilder a63 = a62.append(i24);
                            String s6 = a63.toString();
                            com.cim.AIA.Node a64 = new com.cim.AIA.Node((Object)s6, 0);
                            this.skipN = a64;
                            this.setPosition("3.3.1.2");
                            i25 = i20 + i24;
                            BoyerMooreString a65 = this.theBoyerMooreString2;
                            int i37 = i1 - i21;
                            int i38 = i37 - 1;
                            int i39 = i24 - i38;
                            a65.jumpArrowLabelIndex(i39);
                            BoyerMooreString a66 = this.theBoyerMooreString2;
                            a66.setArrowLabelTarget(i25);
                            BoyerMooreString a67 = this.theBoyerMooreString1;
                            int i40 = i1 - i21;
                            int i41 = i40 - 1;
                            int i42 = i24 - i41;
                            a67.jumpArrowLabelIndex(i42);
                            BoyerMooreString a68 = this.theBoyerMooreString1;
                            a68.setArrowLabelTarget(i21);
                            this.setPosition("3.3.1.6");
                            this.skipN = null;
                            this.patternN = null;
                            this.skipTableS = null;
                            this.skipTableN = null;
                            this.setPosition("3.3.1.7");
                            if(i25 < i0)
                            {
                                break label3;
                            }
                            this.BoyerMooreResult = 0;
                            this.setPosition("3.3.1.8");
                            this.setPosition("4");
                        }
                        return;
                    }
                    int i43 = i1 - 1;
                    BoyerMooreString a69 = this.theBoyerMooreString1;
                    a69.setArrowLabelTarget(i43);
                    BoyerMooreString a70 = this.theBoyerMooreString1;
                    int i44 = i1 - i21;
                    int i45 = i44 - 1;
                    int i46 = i24 - i45;
                    int i47 = i46 * 20;
                    this.BoyerMooreMove(a70, i47, 0);
                    this.setPosition("3.3.1.9");
                    BoyerMooreSkipTable a71 = this.theSkipTable;
                    a71.unHighlightAll();
                    BoyerMooreString a72 = this.theBoyerMooreString1;
                    a72.unHighlightAll();
                    BoyerMooreString a73 = this.theBoyerMooreString2;
                    a73.unHighlightAll();
                    i22 = i25;
                    i23 = i43;
                    break label4;
                }
                BoyerMooreString a74 = this.theBoyerMooreString1;
                java.awt.Color a75 = java.awt.Color.green;
                a74.setHighlightColor(a75);
                BoyerMooreString a76 = this.theBoyerMooreString2;
                java.awt.Color a77 = java.awt.Color.green;
                a76.setHighlightColor(a77);
                BoyerMooreString a78 = this.theBoyerMooreString1;
                a78.highlight(i21);
                BoyerMooreString a79 = this.theBoyerMooreString2;
                a79.highlight(i20);
                this.setPosition("3.3.1");
                this.setPosition("3.3.2");
                int i48 = i20 + -1;
                BoyerMooreString a80 = this.theBoyerMooreString2;
                a80.setArrowLabelTarget(i48);
                this.setPosition("3.3.2.1.1");
                int i49 = i21 + -1;
                BoyerMooreString a81 = this.theBoyerMooreString1;
                a81.setArrowLabelTarget(i49);
                this.setPosition("3.3.2.1.2");
                i22 = i48;
                i23 = i49;
            }
            this.setPosition("3.3.3");
            i20 = i22;
            i21 = i23;
        }
    }
    
    static
    {
        String s = localization.Messages.getString("BoyerMoore.0");
        BoyerMoore.HIGHLIGHT_COLOR = s;
        String s0 = localization.Messages.getString("BoyerMoore.1");
        BoyerMoore.NODE_COLOR = s0;
    }
}