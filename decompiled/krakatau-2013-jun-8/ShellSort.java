public class ShellSort extends com.cim.AIA.Algorithm implements com.cim.AIA.ColorSelectionListener {
    final public static int MARKER_NOT_READY = -10;
    final public static int H_SUBFILES = 10;
    final public static int SINGLE_PASS = 11;
    protected static int currentComparisonOrder;
    protected static String[] duplicateLabels;
    public static java.util.Vector drawLine;
    public static java.awt.Color activeArrayColor;
    final protected static String ARRAY_COLOR;
    final protected static String ACTIVE_ARRAY_COLOR;
    final protected static String PROCESSED_ARRAY_COLOR;
    final protected static String FINISHED_ARRAY_COLOR;
    protected static java.util.Vector swapRequests;
    protected static boolean dontPaintVerticalBarI;
    protected static boolean dontPaintVerticalBarJ;
    protected static boolean finished;
    protected static boolean paintProcessedArrayI;
    protected int[] data;
    protected java.awt.Color[] colors;
    protected int i;
    protected int j;
    protected int h;
    protected int val;
    protected int firstPos;
    protected int finalPosition;
    protected int recordOfI;
    protected int recordOfH;
    protected java.awt.Color textColor;
    protected java.awt.Color arrayColor;
    protected java.awt.Color processedArrayColor;
    protected java.awt.Color finishedArrayColor;
    protected java.awt.Color backgroundColor;
    
    public ShellSort(com.cim.AIA.AlgorithmThread a, Object a0)
    {
        super(a, a0);
        this.i = -10;
        this.j = -10;
        this.h = -10;
        this.val = -10;
        this.firstPos = -10;
        this.finalPosition = -10;
        this.recordOfI = -10;
        this.recordOfH = -10;
        java.awt.Color a1 = java.awt.Color.black;
        this.textColor = a1;
        java.awt.Color a2 = java.awt.Color.pink;
        this.arrayColor = a2;
        java.awt.Color a3 = java.awt.Color.red;
        java.awt.Color a4 = a3.darker();
        this.processedArrayColor = a4;
        java.awt.Color a5 = java.awt.Color.gray;
        this.finishedArrayColor = a5;
        java.awt.Color a6 = java.awt.Color.white;
        this.backgroundColor = a6;
        int[] dummy = (int[])a0;
        int[] a7 = (int[])a0;
        this.initialise(a7);
        com.cim.AIA.ConfigurationManager a8 = com.cim.AIA.ConfigurationManager.getConfigurationManager();
        java.awt.Color a9 = this.arrayColor;
        String s = ShellSort.ARRAY_COLOR;
        com.cim.AIA.ColorSelection a10 = new com.cim.AIA.ColorSelection(a9, s);
        a10.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        java.awt.Color a11 = ShellSort.activeArrayColor;
        String s0 = ShellSort.ACTIVE_ARRAY_COLOR;
        com.cim.AIA.ColorSelection a12 = new com.cim.AIA.ColorSelection(a11, s0);
        a12.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        java.awt.Color a13 = this.processedArrayColor;
        String s1 = ShellSort.PROCESSED_ARRAY_COLOR;
        com.cim.AIA.ColorSelection a14 = new com.cim.AIA.ColorSelection(a13, s1);
        a14.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        java.awt.Color a15 = this.finishedArrayColor;
        String s2 = ShellSort.FINISHED_ARRAY_COLOR;
        com.cim.AIA.ColorSelection a16 = new com.cim.AIA.ColorSelection(a15, s2);
        a16.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        a8.addColorSelection(a10);
        a8.addColorSelection(a12);
        a8.addColorSelection(a14);
        a8.addColorSelection(a16);
        a8.addColorSelectionListener((com.cim.AIA.ColorSelectionListener)this);
    }
    
    protected void createDuplicateLabels()
    {
        int[] a = this.data;
        int i = a.length;
        Integer[] a0 = new Integer[i];
        int i0 = 0;
        while(true)
        {
            int i1 = a0.length;
            if(i0 >= i1)
            {
                String[] a1 = com.cim.AIA.DuplicateLabel.createDuplicateLabels((Object[])a0);
                ShellSort.duplicateLabels = a1;
                return;
            }
            else
            {
                int[] a2 = this.data;
                int i2 = a2[i0];
                Integer a3 = new Integer(i2);
                a0[i0] = a3;
                int i3 = i0 + 1;
                i0 = i3;
            }
        }
    }
    
    protected java.util.Vector generateQuestions()
    {
        return null;
    }
    
    protected void generateTweens(com.cim.AIA.MultipleTween a, com.cim.AIA.ElementArray a0, int i)
    {
        int i0 = 0;
        while(true)
        {
            java.util.Vector a1 = ShellSort.swapRequests;
            int i1 = a1.size();
            if(i0 >= i1)
            {
                return;
            }
            java.util.Vector a2 = ShellSort.swapRequests;
            Object a3 = a2.elementAt(i0);
            com.cim.AIA.SwapRequest dummy = (com.cim.AIA.SwapRequest)a3;
            com.cim.AIA.SwapRequest a4 = (com.cim.AIA.SwapRequest)a3;
            int i2 = a4.getFirstId();
            com.cim.AIA.Element a5 = a0.getElement(i2);
            com.cim.AIA.VerticalBar dummy0 = (com.cim.AIA.VerticalBar)a5;
            com.cim.AIA.VerticalBar a6 = (com.cim.AIA.VerticalBar)a5;
            com.cim.AIA.AlgorithmThread a7 = this.algorithmThread;
            ShellSortThread dummy1 = (ShellSortThread)a7;
            ShellSortThread a8 = (ShellSortThread)a7;
            com.cim.AIA.AlgorithmWindow a9 = a8.getWindow();
            int i3 = a9.isExpanded("7.2")?1:0;
            if(i3 != 0)
            {
                java.awt.Color a10 = this.processedArrayColor;
                a6.setColor(a10);
            }
            int i4 = a4.getSecondId();
            com.cim.AIA.Element a11 = a0.getElement(i4);
            com.cim.AIA.VerticalBar dummy2 = (com.cim.AIA.VerticalBar)a11;
            com.cim.AIA.VerticalBar a12 = (com.cim.AIA.VerticalBar)a11;
            com.cim.AIA.AlgorithmThread a13 = this.algorithmThread;
            ShellSortThread dummy3 = (ShellSortThread)a13;
            ShellSortThread a14 = (ShellSortThread)a13;
            com.cim.AIA.AlgorithmWindow a15 = a14.getWindow();
            int i5 = a15.isExpanded("7.2")?1:0;
            label0: {
                if(i5 == 0)
                {
                    break label0;
                }
                java.util.Vector a16 = ShellSort.swapRequests;
                int i6 = a16.size();
                int i7 = i6 - 1;
                if(i0 == i7)
                {
                    a12.setHeight(0);
                    a12.setThickness(0);
                    a12.setDrawValueMarker(false);
                }
            }
            java.awt.Point a17 = a12.getPosition();
            java.awt.Point a18 = a6.getPosition();
            com.cim.AIA.MoveTween a19 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Moveable)a6, a17, a18, false, i);
            a.add((com.cim.AIA.Tween)a19);
            java.awt.Point a20 = a6.getPosition();
            java.awt.Point a21 = a12.getPosition();
            com.cim.AIA.MoveTween a22 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Moveable)a12, a20, a21, false, i);
            a.add((com.cim.AIA.Tween)a22);
            int i8 = i0 + 1;
            i0 = i8;
        }
    }
    
    public com.cim.AIA.MultipleTween generateTweens(com.cim.AIA.Paintable a, Object a0, int i)
    {
        Object a1 = a;
        com.cim.AIA.MultipleTween a2 = new com.cim.AIA.MultipleTween((com.cim.AIA.Paintable)a1);
        com.cim.AIA.ElementArray dummy = (com.cim.AIA.ElementArray)a0;
        com.cim.AIA.ElementArray a3 = (com.cim.AIA.ElementArray)a0;
        this.generateTweens(a2, a3, i);
        return a2;
    }
    
    public String getClassName()
    {
        String s = localization.Messages.getString("ShellSort.4");
        return s;
    }
    
    public int getComparisonOrder()
    {
        int i = ShellSort.currentComparisonOrder;
        return i;
    }
    
    public com.cim.AIA.ElementArray getElementArray(java.awt.Graphics a)
    {
        com.cim.AIA.ElementArray a0 = new com.cim.AIA.ElementArray(0, 0);
        java.awt.FontMetrics a1 = a.getFontMetrics();
        int i = a1.stringWidth("8");
        int i0 = i + 2;
        a0.setElementWidth(i0);
        a0.setAllHaveSameWidth(true);
        int i1 = 0;
        while(true)
        {
            com.cim.AIA.VerticalBar a2 = null;
            int[] a3 = this.data;
            int i2 = a3.length;
            if(i1 >= i2)
            {
                return a0;
            }
            java.awt.Color a4 = this.backgroundColor;
            java.awt.Color a5 = this.backgroundColor;
            java.awt.Point a6 = new java.awt.Point(0, 0);
            com.cim.AIA.VerticalBar a7 = new com.cim.AIA.VerticalBar(i1, -10, a4, a5, a6);
            int i3 = ShellSort.dontPaintVerticalBarJ?1:0;
            label2: {
                label0: {
                    if(i3 == 0)
                    {
                        break label0;
                    }
                    int i4 = this.getJ();
                    label1: {
                        if(i1 != i4)
                        {
                            break label1;
                        }
                        a7.setDrawValueMarker(false);
                        a2 = a7;
                        break label2;
                    }
                    int[] a8 = this.data;
                    int i5 = a8[i1];
                    java.awt.Color[] a9 = this.colors;
                    java.awt.Color a10 = a9[i1];
                    java.awt.Point a11 = new java.awt.Point(0, 0);
                    com.cim.AIA.VerticalBar a12 = new com.cim.AIA.VerticalBar(i1, i5, a10, a11);
                    int i6 = this.recordOfI;
                    label3: {
                        if(i6 == 1)
                        {
                            break label3;
                        }
                        int i7 = this.getH();
                        if(i7 != 1)
                        {
                            break label3;
                        }
                        int i8 = this.recordOfI;
                        if(i1 < i8)
                        {
                            java.awt.Color a13 = this.processedArrayColor;
                            a12.setColor(a13);
                        }
                    }
                    int i9 = this.getH();
                    label4: {
                        if(i9 != 1)
                        {
                            break label4;
                        }
                        int i10 = this.recordOfI;
                        if(i1 != i10)
                        {
                            break label4;
                        }
                        int i11 = ShellSort.paintProcessedArrayI?1:0;
                        if(i11 != 0)
                        {
                            java.awt.Color a14 = this.processedArrayColor;
                            a12.setColor(a14);
                        }
                    }
                    int i12 = this.getFinalPosition();
                    if(i1 == i12)
                    {
                        java.awt.Color a15 = ShellSort.activeArrayColor;
                        a12.setColor(a15);
                    }
                    int i13 = ShellSort.finished?1:0;
                    if(i13 != 0)
                    {
                        java.awt.Color a16 = this.finishedArrayColor;
                        a12.setColor(a16);
                    }
                    a12.setMarkersAboveValue(false);
                    java.awt.Color a17 = this.textColor;
                    a12.setTextColor(a17);
                    a2 = a12;
                    break label2;
                }
                int i14 = ShellSort.dontPaintVerticalBarI?1:0;
                label5: {
                    if(i14 != 0)
                    {
                        break label5;
                    }
                    int[] a18 = this.data;
                    int i15 = a18[i1];
                    java.awt.Color[] a19 = this.colors;
                    java.awt.Color a20 = a19[i1];
                    java.awt.Point a21 = new java.awt.Point(0, 0);
                    com.cim.AIA.VerticalBar a22 = new com.cim.AIA.VerticalBar(i1, i15, a20, a21);
                    int i16 = this.recordOfI;
                    label6: {
                        if(i16 == 1)
                        {
                            break label6;
                        }
                        int i17 = this.getH();
                        if(i17 != 1)
                        {
                            break label6;
                        }
                        int i18 = this.recordOfI;
                        if(i1 < i18)
                        {
                            java.awt.Color a23 = this.processedArrayColor;
                            a22.setColor(a23);
                        }
                    }
                    int i19 = this.getH();
                    label7: {
                        if(i19 != 1)
                        {
                            break label7;
                        }
                        int i20 = this.recordOfI;
                        if(i1 != i20)
                        {
                            break label7;
                        }
                        int i21 = ShellSort.paintProcessedArrayI?1:0;
                        if(i21 != 0)
                        {
                            java.awt.Color a24 = this.processedArrayColor;
                            a22.setColor(a24);
                        }
                    }
                    int i22 = this.getFinalPosition();
                    if(i1 == i22)
                    {
                        java.awt.Color a25 = ShellSort.activeArrayColor;
                        a22.setColor(a25);
                    }
                    int i23 = ShellSort.finished?1:0;
                    if(i23 != 0)
                    {
                        java.awt.Color a26 = this.finishedArrayColor;
                        a22.setColor(a26);
                    }
                    a22.setMarkersAboveValue(false);
                    java.awt.Color a27 = this.textColor;
                    a22.setTextColor(a27);
                    a2 = a22;
                    break label2;
                }
                int i24 = this.getI();
                label8: {
                    if(i1 != i24)
                    {
                        break label8;
                    }
                    a7.setDrawValueMarker(false);
                    a2 = a7;
                    break label2;
                }
                int[] a28 = this.data;
                int i25 = a28[i1];
                java.awt.Color[] a29 = this.colors;
                java.awt.Color a30 = a29[i1];
                java.awt.Point a31 = new java.awt.Point(0, 0);
                com.cim.AIA.VerticalBar a32 = new com.cim.AIA.VerticalBar(i1, i25, a30, a31);
                int i26 = this.recordOfI;
                label9: {
                    if(i26 == 1)
                    {
                        break label9;
                    }
                    int i27 = this.getH();
                    if(i27 != 1)
                    {
                        break label9;
                    }
                    int i28 = this.recordOfI;
                    if(i1 < i28)
                    {
                        java.awt.Color a33 = this.processedArrayColor;
                        a32.setColor(a33);
                    }
                }
                int i29 = this.getH();
                label10: {
                    if(i29 != 1)
                    {
                        break label10;
                    }
                    int i30 = this.recordOfI;
                    if(i1 != i30)
                    {
                        break label10;
                    }
                    int i31 = ShellSort.paintProcessedArrayI?1:0;
                    if(i31 != 0)
                    {
                        java.awt.Color a34 = this.processedArrayColor;
                        a32.setColor(a34);
                    }
                }
                int i32 = this.getFinalPosition();
                if(i1 == i32)
                {
                    java.awt.Color a35 = ShellSort.activeArrayColor;
                    a32.setColor(a35);
                }
                int i33 = ShellSort.finished?1:0;
                if(i33 != 0)
                {
                    java.awt.Color a36 = this.finishedArrayColor;
                    a32.setColor(a36);
                }
                a32.setMarkersAboveValue(false);
                java.awt.Color a37 = this.textColor;
                a32.setTextColor(a37);
                a2 = a32;
            }
            a0.setValue(i1, (com.cim.AIA.Element)a2);
            String[] a38 = ShellSort.duplicateLabels;
            String s = a38[i1];
            if(s != null)
            {
                StringBuilder a39 = new StringBuilder();
                StringBuilder a40 = a39.append("");
                int[] a41 = this.data;
                int i34 = a41[i1];
                StringBuilder a42 = a40.append(i34);
                String[] a43 = ShellSort.duplicateLabels;
                String s0 = a43[i1];
                StringBuilder a44 = a42.append(s0);
                String s1 = a44.toString();
                com.cim.AIA.StringMarker a45 = new com.cim.AIA.StringMarker(s1);
                java.awt.Color a46 = this.backgroundColor;
                a45.setBackgroundColor(a46);
                java.awt.Color a47 = this.textColor;
                a45.setColor(a47);
                com.cim.AIA.Element a48 = a0.getElement(i1);
                a48.addMarker(a45);
            }
            int i35 = i1 + 1;
            i1 = i35;
        }
    }
    
    public int getFinalPosition()
    {
        int i = this.finalPosition;
        return i;
    }
    
    public int getFirstPos()
    {
        int i = this.firstPos;
        return i;
    }
    
    public int getH()
    {
        int i = this.h;
        return i;
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
    
    public int getVal()
    {
        int i = this.val;
        return i;
    }
    
    protected boolean hasQuestions()
    {
        return false;
    }
    
    protected void initialise(int[] a)
    {
        this.data = a;
        int i = a.length;
        java.awt.Color[] a0 = new java.awt.Color[i];
        this.colors = a0;
        int i0 = 0;
        while(true)
        {
            java.awt.Color[] a1 = this.colors;
            int i1 = a1.length;
            if(i0 >= i1)
            {
                this.createDuplicateLabels();
                this.i = -10;
                this.j = -10;
                this.recordOfI = -10;
                this.recordOfH = -10;
                ShellSort.paintProcessedArrayI = false;
                this.finalPosition = -10;
                ShellSort.finished = false;
                return;
            }
            else
            {
                java.awt.Color[] a2 = this.colors;
                java.awt.Color a3 = this.arrayColor;
                a2[i0] = a3;
                int i2 = i0 + 1;
                i0 = i2;
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
                this.backgroundColor = a0;
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
            String s2 = ShellSort.ARRAY_COLOR;
            int i1 = s.equalsIgnoreCase(s2)?1:0;
            label3: {
                if(i1 == 0)
                {
                    break label3;
                }
                int i2 = 0;
                while(true)
                {
                    java.awt.Color[] a2 = this.colors;
                    int i3 = a2.length;
                    if(i2 >= i3)
                    {
                        break;
                    }
                    java.awt.Color[] a3 = this.colors;
                    java.awt.Color a4 = a3[i2];
                    java.awt.Color a5 = this.arrayColor;
                    if(a4 == a5)
                    {
                        java.awt.Color[] a6 = this.colors;
                        java.awt.Color a7 = a.getColor();
                        a6[i2] = a7;
                    }
                    int i4 = i2 + 1;
                    i2 = i4;
                    continue;
                }
                java.awt.Color a8 = a.getColor();
                this.arrayColor = a8;
                break label1;
            }
            String s3 = ShellSort.ACTIVE_ARRAY_COLOR;
            int i5 = s.equalsIgnoreCase(s3)?1:0;
            label4: {
                if(i5 == 0)
                {
                    break label4;
                }
                int i6 = 0;
                while(true)
                {
                    java.awt.Color[] a9 = this.colors;
                    int i7 = a9.length;
                    if(i6 >= i7)
                    {
                        break;
                    }
                    java.awt.Color[] a10 = this.colors;
                    java.awt.Color a11 = a10[i6];
                    java.awt.Color a12 = ShellSort.activeArrayColor;
                    if(a11 == a12)
                    {
                        java.awt.Color[] a13 = this.colors;
                        java.awt.Color a14 = a.getColor();
                        a13[i6] = a14;
                    }
                    int i8 = i6 + 1;
                    i6 = i8;
                    continue;
                }
                java.awt.Color a15 = a.getColor();
                ShellSort.activeArrayColor = a15;
                break label1;
            }
            String s4 = ShellSort.PROCESSED_ARRAY_COLOR;
            int i9 = s.equalsIgnoreCase(s4)?1:0;
            label5: {
                if(i9 == 0)
                {
                    break label5;
                }
                int i10 = 0;
                while(true)
                {
                    java.awt.Color[] a16 = this.colors;
                    int i11 = a16.length;
                    if(i10 >= i11)
                    {
                        break;
                    }
                    java.awt.Color[] a17 = this.colors;
                    java.awt.Color a18 = a17[i10];
                    java.awt.Color a19 = this.processedArrayColor;
                    if(a18 == a19)
                    {
                        java.awt.Color[] a20 = this.colors;
                        java.awt.Color a21 = a.getColor();
                        a20[i10] = a21;
                    }
                    int i12 = i10 + 1;
                    i10 = i12;
                    continue;
                }
                java.awt.Color a22 = a.getColor();
                this.processedArrayColor = a22;
                break label1;
            }
            String s5 = ShellSort.FINISHED_ARRAY_COLOR;
            int i13 = s.equalsIgnoreCase(s5)?1:0;
            if(i13 == 0)
            {
                break label1;
            }
            int i14 = 0;
            while(true)
            {
                java.awt.Color[] a23 = this.colors;
                int i15 = a23.length;
                if(i14 >= i15)
                {
                    break;
                }
                java.awt.Color[] a24 = this.colors;
                java.awt.Color a25 = a24[i14];
                java.awt.Color a26 = this.finishedArrayColor;
                if(a25 == a26)
                {
                    java.awt.Color[] a27 = this.colors;
                    java.awt.Color a28 = a.getColor();
                    a27[i14] = a28;
                }
                int i16 = i14 + 1;
                i14 = i16;
            }
            java.awt.Color a29 = a.getColor();
            this.finishedArrayColor = a29;
        }
    }
    
    public void reInitialise(Object a)
    {
        int[] dummy = (int[])a;
        int[] a0 = (int[])a;
        this.initialise(a0);
        this.val = -10;
        this.j = -10;
        this.i = -10;
    }
    
    protected void removeAllAnimationRequests()
    {
        java.util.Vector a = ShellSort.swapRequests;
        a.removeAllElements();
    }
    
    protected void removeAllQuestions()
    {
    }
    
    protected void run()
    {
        this.setPosition("1");
        this.h = 1;
        while(true)
        {
            int i = this.h;
            int[] a = this.data;
            int i0 = a.length;
            int i1 = i0 / 4;
            if(i > i1)
            {
                break;
            }
            else
            {
                int i2 = this.h;
                int i3 = 3 * i2;
                int i4 = i3 + 1;
                this.h = i4;
            }
        }
        this.setPosition("5");
        while(true)
        {
            int i5 = this.h;
            if(i5 <= 0)
            {
                this.h = -10;
                ShellSort.finished = true;
                return;
            }
            int i6 = 0;
            while(true)
            {
                java.awt.Color[] a0 = this.colors;
                int i7 = a0.length;
                if(i6 >= i7)
                {
                    break;
                }
                else
                {
                    java.awt.Color[] a1 = this.colors;
                    java.awt.Color a2 = this.arrayColor;
                    a1[i6] = a2;
                    int i8 = i6 + 1;
                    i6 = i8;
                }
            }
            this.recordOfI = 0;
            this.setPosition("7");
            int i9 = ShellSort.currentComparisonOrder;
            label1: {
                int i10 = 0;
                label0: {
                    int i11 = 0;
                    switch(i9){
                        case 11: {
                            i10 = this.h;
                            break label0;
                        }
                        case 10: {
                            i11 = this.h;
                            break;
                        }
                    }
                    this.recordOfH = i11;
                    this.firstPos = 0;
                    int i12 = ShellSort.currentComparisonOrder;
                    label2: {
                        if(i12 == 10)
                        {
                            break label2;
                        }
                        int i13 = this.h;
                        int i14 = 3 * i13;
                        int i15 = i14 + 1;
                        this.h = i15;
                        this.firstPos = -10;
                        break label1;
                    }
                    this.setPosition("7.5");
                    label3: {
                        label14: {
                            while(true)
                            {
                                int i16 = this.firstPos;
                                int i17 = this.h;
                                if(i16 >= i17)
                                {
                                    break label3;
                                }
                                int i18 = this.firstPos;
                                int i19 = this.h;
                                int i20 = i18 + i19;
                                this.i = i20;
                                int i21 = this.i;
                                this.recordOfI = i21;
                                int i22 = ShellSort.currentComparisonOrder;
                                label4: {
                                    if(i22 == 10)
                                    {
                                        break label4;
                                    }
                                    int i23 = this.h;
                                    int i24 = 3 * i23;
                                    int i25 = i24 + 1;
                                    this.h = i25;
                                    this.firstPos = -10;
                                    break label3;
                                }
                                this.setPosition("7.5.2");
                                label5: {
                                    label13: {
                                        while(true)
                                        {
                                            int i26 = this.i;
                                            int[] a3 = this.data;
                                            int i27 = a3.length;
                                            if(i26 >= i27)
                                            {
                                                break label5;
                                            }
                                            ShellSort.paintProcessedArrayI = false;
                                            int[] a4 = this.data;
                                            int i28 = this.i;
                                            int i29 = a4[i28];
                                            this.val = i29;
                                            java.awt.Color[] a5 = this.colors;
                                            int i30 = this.i;
                                            java.awt.Color a6 = ShellSort.activeArrayColor;
                                            a5[i30] = a6;
                                            int i31 = ShellSort.currentComparisonOrder;
                                            label6: {
                                                if(i31 == 10)
                                                {
                                                    break label6;
                                                }
                                                int i32 = this.h;
                                                int i33 = 3 * i32;
                                                int i34 = i33 + 1;
                                                this.h = i34;
                                                this.firstPos = -10;
                                                break label5;
                                            }
                                            this.setPosition("7.5.3.1");
                                            int i35 = this.i;
                                            this.j = i35;
                                            int i36 = ShellSort.currentComparisonOrder;
                                            label7: {
                                                if(i36 == 10)
                                                {
                                                    break label7;
                                                }
                                                int i37 = this.h;
                                                int i38 = 3 * i37;
                                                int i39 = i38 + 1;
                                                this.h = i39;
                                                this.firstPos = -10;
                                                break label5;
                                            }
                                            this.setPosition("7.5.3.2");
                                            java.util.Vector a7 = ShellSort.drawLine;
                                            int i40 = this.j;
                                            Integer a8 = new Integer(i40);
                                            a7.addElement((Object)a8);
                                            java.util.Vector a9 = ShellSort.drawLine;
                                            int i41 = this.j;
                                            int i42 = this.h;
                                            int i43 = i41 - i42;
                                            Integer a10 = new Integer(i43);
                                            a9.addElement((Object)a10);
                                            int i44 = ShellSort.currentComparisonOrder;
                                            label8: {
                                                if(i44 == 10)
                                                {
                                                    break label8;
                                                }
                                                int i45 = this.h;
                                                int i46 = 3 * i45;
                                                int i47 = i46 + 1;
                                                this.h = i47;
                                                this.firstPos = -10;
                                                break label5;
                                            }
                                            this.setPosition("7.5.3.3");
                                            while(true)
                                            {
                                                int i48 = this.j;
                                                int i49 = this.h;
                                                if(i48 < i49)
                                                {
                                                    break;
                                                }
                                                int[] a11 = this.data;
                                                int i50 = this.j;
                                                int i51 = this.h;
                                                int i52 = i50 - i51;
                                                int i53 = a11[i52];
                                                int i54 = this.val;
                                                if(i53 <= i54)
                                                {
                                                    break;
                                                }
                                                int i55 = this.j;
                                                int i56 = this.j;
                                                int i57 = this.h;
                                                int i58 = i56 - i57;
                                                this.swap(i55, i58);
                                                int i59 = ShellSort.currentComparisonOrder;
                                                label9: {
                                                    if(i59 == 10)
                                                    {
                                                        break label9;
                                                    }
                                                    int i60 = this.h;
                                                    int i61 = 3 * i60;
                                                    int i62 = i61 + 1;
                                                    this.h = i62;
                                                    this.firstPos = -10;
                                                    break;
                                                }
                                                this.setPosition("7.5.3.4.1");
                                                int i63 = this.j;
                                                int i64 = this.h;
                                                int i65 = i63 - i64;
                                                this.j = i65;
                                                com.cim.AIA.AlgorithmThread a12 = this.algorithmThread;
                                                ShellSortThread dummy = (ShellSortThread)a12;
                                                ShellSortThread a13 = (ShellSortThread)a12;
                                                com.cim.AIA.AlgorithmWindow a14 = a13.getWindow();
                                                int i66 = a14.isExpanded("7.5.1")?1:0;
                                                if(i66 != 0)
                                                {
                                                    ShellSort.dontPaintVerticalBarJ = true;
                                                }
                                                ShellSort.paintProcessedArrayI = true;
                                                int i67 = ShellSort.currentComparisonOrder;
                                                label10: {
                                                    if(i67 == 10)
                                                    {
                                                        break label10;
                                                    }
                                                    int i68 = this.h;
                                                    int i69 = 3 * i68;
                                                    int i70 = i69 + 1;
                                                    this.h = i70;
                                                    this.firstPos = -10;
                                                    break;
                                                }
                                                this.setPosition("7.5.3.4.2");
                                                int i71 = this.j;
                                                int i72 = this.h;
                                                if(i71 >= i72)
                                                {
                                                    java.util.Vector a15 = ShellSort.drawLine;
                                                    int i73 = this.j;
                                                    int i74 = this.h;
                                                    int i75 = i73 - i74;
                                                    Integer a16 = new Integer(i75);
                                                    a15.addElement((Object)a16);
                                                }
                                                int i76 = ShellSort.currentComparisonOrder;
                                                label11: {
                                                    if(i76 == 10)
                                                    {
                                                        break label11;
                                                    }
                                                    int i77 = this.h;
                                                    int i78 = 3 * i77;
                                                    int i79 = i78 + 1;
                                                    this.h = i79;
                                                    this.firstPos = -10;
                                                    break;
                                                }
                                                this.setPosition("7.5.3.3");
                                                int i80 = ShellSort.dontPaintVerticalBarJ?1:0;
                                                if(i80 != 1)
                                                {
                                                }
                                                else
                                                {
                                                    ShellSort.dontPaintVerticalBarJ = false;
                                                }
                                            }
                                            int i81 = this.j;
                                            int i82 = this.h;
                                            label12: {
                                                if(i81 < i82)
                                                {
                                                    break label12;
                                                }
                                                int[] a17 = this.data;
                                                int i83 = this.j;
                                                int i84 = this.h;
                                                int i85 = i83 - i84;
                                                int i86 = a17[i85];
                                                int i87 = this.val;
                                                if(i86 <= i87)
                                                {
                                                    java.awt.Color[] a18 = this.colors;
                                                    int i88 = this.j;
                                                    int i89 = this.h;
                                                    int i90 = i88 - i89;
                                                    java.awt.Color a19 = this.processedArrayColor;
                                                    a18[i90] = a19;
                                                }
                                            }
                                            int[] a20 = this.data;
                                            int i91 = this.j;
                                            int i92 = this.val;
                                            a20[i91] = i92;
                                            int i93 = this.j;
                                            this.finalPosition = i93;
                                            int i94 = ShellSort.currentComparisonOrder;
                                            if(i94 != 10)
                                            {
                                                break label13;
                                            }
                                            this.setPosition("7.5.3.6");
                                            this.finalPosition = -10;
                                            java.awt.Color[] a21 = this.colors;
                                            int i95 = this.j;
                                            java.awt.Color a22 = this.processedArrayColor;
                                            a21[i95] = a22;
                                            this.j = -10;
                                            this.val = -10;
                                            java.util.Vector a23 = ShellSort.drawLine;
                                            a23.removeAllElements();
                                            int i96 = this.i;
                                            int i97 = this.h;
                                            int i98 = i96 + i97;
                                            this.i = i98;
                                            int i99 = this.i;
                                            this.recordOfI = i99;
                                            ShellSort.paintProcessedArrayI = false;
                                            int i100 = ShellSort.currentComparisonOrder;
                                            if(i100 != 10)
                                            {
                                                break;
                                            }
                                            int i101 = this.i;
                                            int[] a24 = this.data;
                                            int i102 = a24.length;
                                            if(i101 >= i102)
                                            {
                                                continue;
                                            }
                                            else
                                            {
                                                this.setPosition("7.5.2");
                                                continue;
                                            }
                                        }
                                        int i103 = this.h;
                                        int i104 = this.recordOfH;
                                        if(i103 == i104)
                                        {
                                            int i105 = this.h;
                                            int i106 = 3 * i105;
                                            int i107 = i106 + 1;
                                            this.h = i107;
                                        }
                                        java.util.Vector a25 = ShellSort.drawLine;
                                        int i108 = a25.size();
                                        if(i108 > 0)
                                        {
                                            java.util.Vector a26 = ShellSort.drawLine;
                                            a26.removeAllElements();
                                        }
                                        this.firstPos = -10;
                                        break label5;
                                    }
                                    int i109 = this.h;
                                    int i110 = this.recordOfH;
                                    if(i109 == i110)
                                    {
                                        int i111 = this.h;
                                        int i112 = 3 * i111;
                                        int i113 = i112 + 1;
                                        this.h = i113;
                                    }
                                    java.util.Vector a27 = ShellSort.drawLine;
                                    int i114 = a27.size();
                                    if(i114 > 0)
                                    {
                                        java.util.Vector a28 = ShellSort.drawLine;
                                        a28.removeAllElements();
                                    }
                                    this.firstPos = -10;
                                }
                                int i115 = ShellSort.currentComparisonOrder;
                                if(i115 != 10)
                                {
                                    break label14;
                                }
                                this.setPosition("7.5.4");
                                int[] a29 = this.data;
                                int i116 = a29.length;
                                this.recordOfI = i116;
                                this.i = -10;
                                int i117 = this.firstPos;
                                int i118 = i117 + 1;
                                this.firstPos = i118;
                                int i119 = ShellSort.currentComparisonOrder;
                                if(i119 != 10)
                                {
                                    break;
                                }
                                int i120 = this.firstPos;
                                int i121 = this.h;
                                if(i120 == i121)
                                {
                                }
                                else
                                {
                                    this.setPosition("7.5");
                                }
                            }
                            int i122 = this.h;
                            int i123 = this.recordOfH;
                            if(i122 == i123)
                            {
                                int i124 = this.h;
                                int i125 = 3 * i124;
                                int i126 = i125 + 1;
                                this.h = i126;
                            }
                            java.util.Vector a30 = ShellSort.drawLine;
                            int i127 = a30.size();
                            if(i127 > 0)
                            {
                                java.util.Vector a31 = ShellSort.drawLine;
                                a31.removeAllElements();
                            }
                            this.firstPos = -10;
                            this.i = -10;
                            this.j = -10;
                            this.finalPosition = -10;
                            int i128 = ShellSort.dontPaintVerticalBarJ?1:0;
                            if(i128 != 1)
                            {
                                break label3;
                            }
                            else
                            {
                                ShellSort.dontPaintVerticalBarJ = false;
                                break label3;
                            }
                        }
                        int i129 = this.h;
                        int i130 = this.recordOfH;
                        if(i129 == i130)
                        {
                            int i131 = this.h;
                            int i132 = 3 * i131;
                            int i133 = i132 + 1;
                            this.h = i133;
                        }
                        java.util.Vector a32 = ShellSort.drawLine;
                        int i134 = a32.size();
                        if(i134 > 0)
                        {
                            java.util.Vector a33 = ShellSort.drawLine;
                            a33.removeAllElements();
                        }
                        this.firstPos = -10;
                        this.i = -10;
                        this.j = -10;
                        this.finalPosition = -10;
                        int i135 = ShellSort.dontPaintVerticalBarJ?1:0;
                        if(i135 == 1)
                        {
                            ShellSort.dontPaintVerticalBarJ = false;
                        }
                    }
                    this.setPosition("7.6");
                    this.firstPos = -10;
                    break label1;
                }
                this.recordOfH = i10;
                int i136 = this.h;
                this.i = i136;
                int i137 = this.i;
                this.recordOfI = i137;
                int i138 = ShellSort.currentComparisonOrder;
                label15: {
                    if(i138 == 11)
                    {
                        break label15;
                    }
                    int i139 = this.h;
                    int i140 = 3 * i139;
                    int i141 = i140 + 1;
                    this.h = i141;
                    break label1;
                }
                this.setPosition("7.4");
                label16: {
                    label24: {
                        while(true)
                        {
                            int i142 = this.i;
                            int[] a34 = this.data;
                            int i143 = a34.length;
                            if(i142 >= i143)
                            {
                                break label16;
                            }
                            ShellSort.paintProcessedArrayI = false;
                            int[] a35 = this.data;
                            int i144 = this.i;
                            int i145 = a35[i144];
                            this.val = i145;
                            java.awt.Color[] a36 = this.colors;
                            int i146 = this.i;
                            java.awt.Color a37 = ShellSort.activeArrayColor;
                            a36[i146] = a37;
                            int i147 = ShellSort.currentComparisonOrder;
                            label17: {
                                if(i147 == 11)
                                {
                                    break label17;
                                }
                                int i148 = this.h;
                                int i149 = 3 * i148;
                                int i150 = i149 + 1;
                                this.h = i150;
                                break label16;
                            }
                            this.setPosition("7.4.2.1");
                            int i151 = this.i;
                            this.j = i151;
                            int i152 = ShellSort.currentComparisonOrder;
                            label18: {
                                if(i152 == 11)
                                {
                                    break label18;
                                }
                                int i153 = this.h;
                                int i154 = 3 * i153;
                                int i155 = i154 + 1;
                                this.h = i155;
                                break label16;
                            }
                            this.setPosition("7.4.2.2");
                            java.util.Vector a38 = ShellSort.drawLine;
                            int i156 = this.j;
                            Integer a39 = new Integer(i156);
                            a38.addElement((Object)a39);
                            java.util.Vector a40 = ShellSort.drawLine;
                            int i157 = this.j;
                            int i158 = this.h;
                            int i159 = i157 - i158;
                            Integer a41 = new Integer(i159);
                            a40.addElement((Object)a41);
                            int i160 = ShellSort.currentComparisonOrder;
                            label19: {
                                if(i160 == 11)
                                {
                                    break label19;
                                }
                                int i161 = this.h;
                                int i162 = 3 * i161;
                                int i163 = i162 + 1;
                                this.h = i163;
                                break label16;
                            }
                            this.setPosition("7.4.2.3");
                            while(true)
                            {
                                int i164 = this.j;
                                int i165 = this.h;
                                if(i164 < i165)
                                {
                                    break;
                                }
                                int[] a42 = this.data;
                                int i166 = this.j;
                                int i167 = this.h;
                                int i168 = i166 - i167;
                                int i169 = a42[i168];
                                int i170 = this.val;
                                if(i169 <= i170)
                                {
                                    break;
                                }
                                int i171 = this.j;
                                int i172 = this.j;
                                int i173 = this.h;
                                int i174 = i172 - i173;
                                this.swap(i171, i174);
                                int i175 = ShellSort.currentComparisonOrder;
                                label20: {
                                    if(i175 == 11)
                                    {
                                        break label20;
                                    }
                                    int i176 = this.h;
                                    int i177 = 3 * i176;
                                    int i178 = i177 + 1;
                                    this.h = i178;
                                    break;
                                }
                                this.setPosition("7.4.2.4.1");
                                int i179 = this.j;
                                int i180 = this.h;
                                int i181 = i179 - i180;
                                this.j = i181;
                                com.cim.AIA.AlgorithmThread a43 = this.algorithmThread;
                                ShellSortThread dummy0 = (ShellSortThread)a43;
                                ShellSortThread a44 = (ShellSortThread)a43;
                                com.cim.AIA.AlgorithmWindow a45 = a44.getWindow();
                                int i182 = a45.isExpanded("7.4.1")?1:0;
                                if(i182 != 0)
                                {
                                    ShellSort.dontPaintVerticalBarJ = true;
                                }
                                ShellSort.paintProcessedArrayI = true;
                                int i183 = ShellSort.currentComparisonOrder;
                                label21: {
                                    if(i183 == 11)
                                    {
                                        break label21;
                                    }
                                    int i184 = this.h;
                                    int i185 = 3 * i184;
                                    int i186 = i185 + 1;
                                    this.h = i186;
                                    break;
                                }
                                this.setPosition("7.4.2.4.2");
                                int i187 = this.j;
                                int i188 = this.h;
                                if(i187 >= i188)
                                {
                                    java.util.Vector a46 = ShellSort.drawLine;
                                    int i189 = this.j;
                                    int i190 = this.h;
                                    int i191 = i189 - i190;
                                    Integer a47 = new Integer(i191);
                                    a46.addElement((Object)a47);
                                }
                                int i192 = ShellSort.currentComparisonOrder;
                                label22: {
                                    if(i192 == 11)
                                    {
                                        break label22;
                                    }
                                    int i193 = this.h;
                                    int i194 = 3 * i193;
                                    int i195 = i194 + 1;
                                    this.h = i195;
                                    break;
                                }
                                this.setPosition("7.4.2.3");
                                int i196 = ShellSort.dontPaintVerticalBarJ?1:0;
                                if(i196 != 1)
                                {
                                }
                                else
                                {
                                    ShellSort.dontPaintVerticalBarJ = false;
                                }
                            }
                            int i197 = this.j;
                            int i198 = this.h;
                            label23: {
                                if(i197 < i198)
                                {
                                    break label23;
                                }
                                int[] a48 = this.data;
                                int i199 = this.j;
                                int i200 = this.h;
                                int i201 = i199 - i200;
                                int i202 = a48[i201];
                                int i203 = this.val;
                                if(i202 <= i203)
                                {
                                    java.awt.Color[] a49 = this.colors;
                                    int i204 = this.j;
                                    int i205 = this.h;
                                    int i206 = i204 - i205;
                                    java.awt.Color a50 = this.processedArrayColor;
                                    a49[i206] = a50;
                                }
                            }
                            int[] a51 = this.data;
                            int i207 = this.j;
                            int i208 = this.val;
                            a51[i207] = i208;
                            int i209 = this.j;
                            this.finalPosition = i209;
                            int i210 = ShellSort.currentComparisonOrder;
                            if(i210 != 11)
                            {
                                break label24;
                            }
                            this.setPosition("7.4.2.6");
                            this.finalPosition = -10;
                            java.awt.Color[] a52 = this.colors;
                            int i211 = this.j;
                            java.awt.Color a53 = this.processedArrayColor;
                            a52[i211] = a53;
                            this.j = -10;
                            this.val = -10;
                            java.util.Vector a54 = ShellSort.drawLine;
                            a54.removeAllElements();
                            int i212 = this.i;
                            int i213 = i212 + 1;
                            this.i = i213;
                            int i214 = this.i;
                            this.recordOfI = i214;
                            ShellSort.paintProcessedArrayI = false;
                            int i215 = ShellSort.currentComparisonOrder;
                            if(i215 != 11)
                            {
                                break;
                            }
                            int i216 = this.i;
                            int[] a55 = this.data;
                            int i217 = a55.length;
                            if(i216 == i217)
                            {
                            }
                            else
                            {
                                this.setPosition("7.4");
                            }
                        }
                        int i218 = this.h;
                        int i219 = this.recordOfH;
                        if(i218 == i219)
                        {
                            int i220 = this.h;
                            int i221 = 3 * i220;
                            int i222 = i221 + 1;
                            this.h = i222;
                        }
                        java.util.Vector a56 = ShellSort.drawLine;
                        int i223 = a56.size();
                        if(i223 <= 0)
                        {
                            break label16;
                        }
                        else
                        {
                            java.util.Vector a57 = ShellSort.drawLine;
                            a57.removeAllElements();
                            break label16;
                        }
                    }
                    int i224 = this.h;
                    int i225 = this.recordOfH;
                    if(i224 == i225)
                    {
                        int i226 = this.h;
                        int i227 = 3 * i226;
                        int i228 = i227 + 1;
                        this.h = i228;
                    }
                    java.util.Vector a58 = ShellSort.drawLine;
                    int i229 = a58.size();
                    if(i229 > 0)
                    {
                        java.util.Vector a59 = ShellSort.drawLine;
                        a59.removeAllElements();
                    }
                }
                int i230 = ShellSort.currentComparisonOrder;
                label25: {
                    if(i230 != 11)
                    {
                        break label25;
                    }
                    this.setPosition("7.5");
                    int[] a60 = this.data;
                    int i231 = a60.length;
                    this.recordOfI = i231;
                    this.i = -10;
                    break label1;
                }
                int i232 = this.h;
                int i233 = this.recordOfH;
                if(i232 == i233)
                {
                    int i234 = this.h;
                    int i235 = 3 * i234;
                    int i236 = i235 + 1;
                    this.h = i236;
                }
                java.util.Vector a61 = ShellSort.drawLine;
                int i237 = a61.size();
                if(i237 > 0)
                {
                    java.util.Vector a62 = ShellSort.drawLine;
                    a62.removeAllElements();
                }
                this.i = -10;
                this.j = -10;
                this.finalPosition = -10;
                int i238 = ShellSort.dontPaintVerticalBarJ?1:0;
                if(i238 == 1)
                {
                    ShellSort.dontPaintVerticalBarJ = false;
                }
            }
            int i239 = this.h;
            int i240 = i239 / 3;
            this.h = i240;
        }
    }
    
    public void setComparisonOrder(int i)
    {
        ShellSort.currentComparisonOrder = i;
    }
    
    protected void swap(int i, int i0)
    {
        int[] a = this.data;
        int i1 = a[i0];
        int[] a0 = this.data;
        int[] a1 = this.data;
        int i2 = a1[i];
        a0[i0] = i2;
        int[] a2 = this.data;
        a2[i] = i1;
        java.awt.Color[] a3 = this.colors;
        java.awt.Color[] a4 = this.colors;
        java.awt.Color a5 = this.processedArrayColor;
        a4[i] = a5;
        a3[i0] = a5;
        String[] a6 = ShellSort.duplicateLabels;
        String s = a6[i0];
        String[] a7 = ShellSort.duplicateLabels;
        String[] a8 = ShellSort.duplicateLabels;
        String s0 = a8[i];
        a7[i0] = s0;
        String[] a9 = ShellSort.duplicateLabels;
        a9[i] = s;
        if(i0 != i)
        {
            java.util.Vector a10 = ShellSort.swapRequests;
            com.cim.AIA.SwapRequest a11 = new com.cim.AIA.SwapRequest(i, i0);
            a10.addElement((Object)a11);
        }
    }
    
    static
    {
        ShellSort.currentComparisonOrder = 10;
        java.util.Vector a = new java.util.Vector();
        ShellSort.drawLine = a;
        java.awt.Color a0 = java.awt.Color.red;
        ShellSort.activeArrayColor = a0;
        String s = localization.Messages.getString("ShellSort.0");
        ShellSort.ARRAY_COLOR = s;
        String s0 = localization.Messages.getString("ShellSort.1");
        ShellSort.ACTIVE_ARRAY_COLOR = s0;
        String s1 = localization.Messages.getString("ShellSort.2");
        ShellSort.PROCESSED_ARRAY_COLOR = s1;
        String s2 = localization.Messages.getString("ShellSort.3");
        ShellSort.FINISHED_ARRAY_COLOR = s2;
        java.util.Vector a1 = new java.util.Vector();
        ShellSort.swapRequests = a1;
        ShellSort.dontPaintVerticalBarI = false;
        ShellSort.dontPaintVerticalBarJ = false;
        ShellSort.finished = false;
        ShellSort.paintProcessedArrayI = false;
    }
}