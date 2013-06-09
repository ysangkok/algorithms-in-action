public class InsertionSort extends com.cim.AIA.Algorithm implements com.cim.AIA.ColorSelectionListener {
    final public static int MARKER_NOT_READY = -10;
    public static java.awt.Color activeArrayColor;
    final protected static String ARRAY_COLOR;
    final protected static String ACTIVE_ARRAY_COLOR;
    final protected static String PROCESSED_ARRAY_COLOR;
    final protected static String FINISHED_ARRAY_COLOR;
    protected static boolean dontPaintJMarker;
    protected static boolean dontPaintValMarker;
    protected static boolean dontPaintElementMarker;
    protected static boolean dontPaintVerticalBarJ;
    protected static boolean paintProcessedArrayI;
    protected static boolean paintActiveValEarly;
    protected static java.util.Vector moveRequests;
    protected int[] data;
    protected int recordOfI;
    protected int recordOfJ;
    protected String[] duplicateLabels;
    protected int i;
    protected int j;
    protected int val;
    protected int valPosition;
    protected int finalPosition;
    protected int elementPosition;
    protected java.awt.Color textColor;
    protected java.awt.Color arrayColor;
    protected java.awt.Color processedArrayColor;
    protected java.awt.Color finishedArrayColor;
    protected java.awt.Color backgroundColor;
    protected boolean finished;
    
    public InsertionSort(com.cim.AIA.AlgorithmThread a, Object a0)
    {
        super(a, a0);
        this.recordOfI = 0;
        this.recordOfJ = 0;
        this.i = -10;
        this.j = -10;
        this.val = -10;
        this.valPosition = -10;
        this.finalPosition = -10;
        this.elementPosition = -10;
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
        this.finished = false;
        int[] dummy = (int[])a0;
        int[] a7 = (int[])a0;
        this.initialise(a7);
        com.cim.AIA.ConfigurationManager a8 = com.cim.AIA.ConfigurationManager.getConfigurationManager();
        java.awt.Color a9 = this.arrayColor;
        String s = InsertionSort.ARRAY_COLOR;
        com.cim.AIA.ColorSelection a10 = new com.cim.AIA.ColorSelection(a9, s);
        a10.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        java.awt.Color a11 = InsertionSort.activeArrayColor;
        String s0 = InsertionSort.ACTIVE_ARRAY_COLOR;
        com.cim.AIA.ColorSelection a12 = new com.cim.AIA.ColorSelection(a11, s0);
        a10.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        java.awt.Color a13 = this.processedArrayColor;
        String s1 = InsertionSort.PROCESSED_ARRAY_COLOR;
        com.cim.AIA.ColorSelection a14 = new com.cim.AIA.ColorSelection(a13, s1);
        a14.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        java.awt.Color a15 = this.finishedArrayColor;
        String s2 = InsertionSort.FINISHED_ARRAY_COLOR;
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
                this.duplicateLabels = a1;
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
            java.util.Vector a1 = InsertionSort.moveRequests;
            int i1 = a1.size();
            if(i0 >= i1)
            {
                return;
            }
            java.util.Vector a2 = InsertionSort.moveRequests;
            Object a3 = a2.elementAt(i0);
            com.cim.AIA.SwapRequest dummy = (com.cim.AIA.SwapRequest)a3;
            com.cim.AIA.SwapRequest a4 = (com.cim.AIA.SwapRequest)a3;
            int i2 = a4.getFirstId();
            com.cim.AIA.Element a5 = a0.getElement(i2);
            com.cim.AIA.VerticalBar dummy0 = (com.cim.AIA.VerticalBar)a5;
            com.cim.AIA.VerticalBar a6 = (com.cim.AIA.VerticalBar)a5;
            java.awt.Color a7 = this.processedArrayColor;
            a6.setColor(a7);
            int i3 = a4.getSecondId();
            com.cim.AIA.Element a8 = a0.getElement(i3);
            com.cim.AIA.VerticalBar dummy1 = (com.cim.AIA.VerticalBar)a8;
            com.cim.AIA.VerticalBar a9 = (com.cim.AIA.VerticalBar)a8;
            java.util.Vector a10 = InsertionSort.moveRequests;
            int i4 = a10.size();
            int i5 = i4 - 1;
            if(i0 == i5)
            {
                a9.setHeight(0);
                a9.setThickness(0);
                a9.setDrawValueMarker(false);
            }
            java.awt.Point a11 = a9.getPosition();
            java.awt.Point a12 = a6.getPosition();
            com.cim.AIA.MoveTween a13 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Moveable)a6, a11, a12, false, i);
            a.add((com.cim.AIA.Tween)a13);
            java.awt.Point a14 = a6.getPosition();
            java.awt.Point a15 = a9.getPosition();
            com.cim.AIA.MoveTween a16 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Moveable)a9, a14, a15, false, i);
            a.add((com.cim.AIA.Tween)a16);
            int i6 = i0 + 1;
            i0 = i6;
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
        String s = localization.Messages.getString("InsertionSort.4");
        return s;
    }
    
    public boolean getDontPaintElementMarker()
    {
        int i = InsertionSort.dontPaintElementMarker?1:0;
        return i != 0;
    }
    
    public boolean getDontPaintJMarker()
    {
        int i = InsertionSort.dontPaintJMarker?1:0;
        return i != 0;
    }
    
    public boolean getDontPaintValMarker()
    {
        int i = InsertionSort.dontPaintValMarker?1:0;
        return i != 0;
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
            int i3 = InsertionSort.dontPaintVerticalBarJ?1:0;
            label4: {
                label0: {
                    if(i3 != 0)
                    {
                        break label0;
                    }
                    int[] a8 = this.data;
                    int i4 = a8[i1];
                    java.awt.Color a9 = this.arrayColor;
                    java.awt.Point a10 = new java.awt.Point(0, 0);
                    com.cim.AIA.VerticalBar a11 = new com.cim.AIA.VerticalBar(i1, i4, a9, a10);
                    int i5 = this.recordOfI;
                    label1: {
                        if(i5 == 1)
                        {
                            break label1;
                        }
                        int i6 = this.recordOfI;
                        if(i1 < i6)
                        {
                            java.awt.Color a12 = this.processedArrayColor;
                            a11.setColor(a12);
                        }
                    }
                    int i7 = this.recordOfI;
                    label2: {
                        if(i1 != i7)
                        {
                            break label2;
                        }
                        int i8 = InsertionSort.paintProcessedArrayI?1:0;
                        if(i8 != 0)
                        {
                            java.awt.Color a13 = this.processedArrayColor;
                            a11.setColor(a13);
                        }
                    }
                    int i9 = InsertionSort.paintActiveValEarly?1:0;
                    label3: {
                        if(i9 == 0)
                        {
                            break label3;
                        }
                        int i10 = this.getI();
                        if(i1 == i10)
                        {
                            java.awt.Color a14 = InsertionSort.activeArrayColor;
                            a11.setColor(a14);
                        }
                    }
                    int i11 = this.getFinalPosition();
                    if(i1 == i11)
                    {
                        java.awt.Color a15 = InsertionSort.activeArrayColor;
                        a11.setColor(a15);
                    }
                    int i12 = this.getValPosition();
                    if(i1 == i12)
                    {
                        java.awt.Color a16 = InsertionSort.activeArrayColor;
                        a11.setColor(a16);
                    }
                    int i13 = this.finished?1:0;
                    if(i13 != 0)
                    {
                        java.awt.Color a17 = this.finishedArrayColor;
                        a11.setColor(a17);
                    }
                    a11.setMarkersAboveValue(false);
                    java.awt.Color a18 = this.textColor;
                    a11.setTextColor(a18);
                    a2 = a11;
                    break label4;
                }
                int i14 = this.getJ();
                label5: {
                    if(i1 != i14)
                    {
                        break label5;
                    }
                    a7.setDrawValueMarker(false);
                    a2 = a7;
                    break label4;
                }
                int[] a19 = this.data;
                int i15 = a19[i1];
                java.awt.Color a20 = this.arrayColor;
                java.awt.Point a21 = new java.awt.Point(0, 0);
                com.cim.AIA.VerticalBar a22 = new com.cim.AIA.VerticalBar(i1, i15, a20, a21);
                int i16 = this.recordOfI;
                label6: {
                    if(i16 == 1)
                    {
                        break label6;
                    }
                    int i17 = this.recordOfI;
                    if(i1 < i17)
                    {
                        java.awt.Color a23 = this.processedArrayColor;
                        a22.setColor(a23);
                    }
                }
                int i18 = this.recordOfI;
                label7: {
                    if(i1 != i18)
                    {
                        break label7;
                    }
                    int i19 = InsertionSort.paintProcessedArrayI?1:0;
                    if(i19 != 0)
                    {
                        java.awt.Color a24 = this.processedArrayColor;
                        a22.setColor(a24);
                    }
                }
                int i20 = InsertionSort.paintActiveValEarly?1:0;
                label8: {
                    if(i20 == 0)
                    {
                        break label8;
                    }
                    int i21 = this.getI();
                    if(i1 == i21)
                    {
                        java.awt.Color a25 = InsertionSort.activeArrayColor;
                        a22.setColor(a25);
                    }
                }
                int i22 = this.getFinalPosition();
                if(i1 == i22)
                {
                    java.awt.Color a26 = InsertionSort.activeArrayColor;
                    a22.setColor(a26);
                }
                int i23 = this.getValPosition();
                if(i1 == i23)
                {
                    java.awt.Color a27 = InsertionSort.activeArrayColor;
                    a22.setColor(a27);
                }
                int i24 = this.finished?1:0;
                if(i24 != 0)
                {
                    java.awt.Color a28 = this.finishedArrayColor;
                    a22.setColor(a28);
                }
                a22.setMarkersAboveValue(false);
                java.awt.Color a29 = this.textColor;
                a22.setTextColor(a29);
                a2 = a22;
            }
            a0.setValue(i1, (com.cim.AIA.Element)a2);
            String[] a30 = this.duplicateLabels;
            String s = a30[i1];
            if(s != null)
            {
                StringBuilder a31 = new StringBuilder();
                StringBuilder a32 = a31.append("");
                int[] a33 = this.data;
                int i25 = a33[i1];
                StringBuilder a34 = a32.append(i25);
                String[] a35 = this.duplicateLabels;
                String s0 = a35[i1];
                StringBuilder a36 = a34.append(s0);
                String s1 = a36.toString();
                com.cim.AIA.StringMarker a37 = new com.cim.AIA.StringMarker(s1);
                java.awt.Color a38 = this.backgroundColor;
                a37.setBackgroundColor(a38);
                java.awt.Color a39 = this.textColor;
                a37.setColor(a39);
                com.cim.AIA.Element a40 = a0.getElement(i1);
                a40.addMarker(a37);
            }
            int i26 = i1 + 1;
            i1 = i26;
        }
    }
    
    public int getElementPosition()
    {
        int i = this.elementPosition;
        return i;
    }
    
    public int getFinalPosition()
    {
        int i = this.finalPosition;
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
    
    public int getValPosition()
    {
        int i = this.valPosition;
        return i;
    }
    
    protected boolean hasQuestions()
    {
        return false;
    }
    
    protected void initialise(int[] a)
    {
        this.data = a;
        this.createDuplicateLabels();
        this.i = -10;
        this.j = -10;
        this.recordOfI = 0;
        this.recordOfJ = 0;
        this.val = -10;
        InsertionSort.dontPaintVerticalBarJ = false;
        InsertionSort.paintProcessedArrayI = false;
        InsertionSort.paintActiveValEarly = false;
        InsertionSort.dontPaintValMarker = false;
        this.valPosition = -10;
        this.finalPosition = -10;
        this.elementPosition = -10;
        this.finished = false;
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
            String s2 = InsertionSort.ARRAY_COLOR;
            int i1 = s.equalsIgnoreCase(s2)?1:0;
            label3: {
                if(i1 == 0)
                {
                    break label3;
                }
                java.awt.Color a2 = a.getColor();
                this.arrayColor = a2;
                break label1;
            }
            String s3 = InsertionSort.ACTIVE_ARRAY_COLOR;
            int i2 = s.equalsIgnoreCase(s3)?1:0;
            label4: {
                if(i2 == 0)
                {
                    break label4;
                }
                java.awt.Color a3 = a.getColor();
                InsertionSort.activeArrayColor = a3;
                break label1;
            }
            String s4 = InsertionSort.PROCESSED_ARRAY_COLOR;
            int i3 = s.equalsIgnoreCase(s4)?1:0;
            label5: {
                if(i3 == 0)
                {
                    break label5;
                }
                java.awt.Color a4 = a.getColor();
                this.processedArrayColor = a4;
                break label1;
            }
            String s5 = InsertionSort.FINISHED_ARRAY_COLOR;
            int i4 = s.equalsIgnoreCase(s5)?1:0;
            if(i4 != 0)
            {
                java.awt.Color a5 = a.getColor();
                this.finishedArrayColor = a5;
            }
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
        this.elementPosition = -10;
        this.finalPosition = -10;
        this.valPosition = -10;
        this.setPosition("");
    }
    
    protected void removeAllAnimationRequests()
    {
        java.util.Vector a = InsertionSort.moveRequests;
        a.removeAllElements();
    }
    
    protected void removeAllQuestions()
    {
    }
    
    protected void run()
    {
        this.setPosition("1");
        this.i = 1;
        int i = this.i;
        this.recordOfI = i;
        InsertionSort.paintActiveValEarly = false;
        InsertionSort.dontPaintValMarker = true;
        com.cim.AIA.AlgorithmThread a = this.algorithmThread;
        InsertionSortThread dummy = (InsertionSortThread)a;
        InsertionSortThread a0 = (InsertionSortThread)a;
        com.cim.AIA.AlgorithmWindow a1 = a0.getWindow();
        int i0 = a1.isExpanded("3.1")?1:0;
        if(i0 == 0)
        {
            InsertionSort.paintActiveValEarly = true;
        }
        this.setPosition("3");
        while(true)
        {
            int i1 = this.i;
            int[] a2 = this.data;
            int i2 = a2.length;
            if(i1 >= i2)
            {
                int[] a3 = this.data;
                int i3 = a3.length;
                this.recordOfI = i3;
                this.i = -10;
                this.finished = true;
                return;
            }
            InsertionSort.paintProcessedArrayI = false;
            this.j = -10;
            com.cim.AIA.AlgorithmThread a4 = this.algorithmThread;
            InsertionSortThread dummy0 = (InsertionSortThread)a4;
            InsertionSortThread a5 = (InsertionSortThread)a4;
            com.cim.AIA.AlgorithmWindow a6 = a5.getWindow();
            int i4 = a6.isExpanded("3.1")?1:0;
            if(i4 == 0)
            {
                InsertionSort.dontPaintJMarker = true;
            }
            int[] a7 = this.data;
            int i5 = this.i;
            int i6 = a7[i5];
            this.val = i6;
            int i7 = this.i;
            this.valPosition = i7;
            this.setPosition("3.1.1.1");
            int i8 = this.i;
            this.j = i8;
            com.cim.AIA.AlgorithmThread a8 = this.algorithmThread;
            InsertionSortThread dummy1 = (InsertionSortThread)a8;
            InsertionSortThread a9 = (InsertionSortThread)a8;
            com.cim.AIA.AlgorithmWindow a10 = a9.getWindow();
            int i9 = a10.isExpanded("3.1.2")?1:0;
            label2: {
                label1: {
                    label0: {
                        if(i9 == 0)
                        {
                            break label0;
                        }
                        com.cim.AIA.AlgorithmThread a11 = this.algorithmThread;
                        InsertionSortThread dummy2 = (InsertionSortThread)a11;
                        InsertionSortThread a12 = (InsertionSortThread)a11;
                        com.cim.AIA.AlgorithmWindow a13 = a12.getWindow();
                        int i10 = a13.isExpanded("3.1")?1:0;
                        if(i10 != 0)
                        {
                            break label1;
                        }
                    }
                    InsertionSort.dontPaintJMarker = true;
                    break label2;
                }
                InsertionSort.dontPaintJMarker = false;
            }
            InsertionSort.dontPaintVerticalBarJ = false;
            this.setPosition("3.1.2.1");
            com.cim.AIA.AlgorithmThread a14 = this.algorithmThread;
            InsertionSortThread dummy3 = (InsertionSortThread)a14;
            InsertionSortThread a15 = (InsertionSortThread)a14;
            com.cim.AIA.AlgorithmWindow a16 = a15.getWindow();
            int i11 = a16.isExpanded("3.1.2.2")?1:0;
            label5: {
                label4: {
                    label3: {
                        if(i11 == 0)
                        {
                            break label3;
                        }
                        com.cim.AIA.AlgorithmThread a17 = this.algorithmThread;
                        InsertionSortThread dummy4 = (InsertionSortThread)a17;
                        InsertionSortThread a18 = (InsertionSortThread)a17;
                        com.cim.AIA.AlgorithmWindow a19 = a18.getWindow();
                        int i12 = a19.isExpanded("3.1")?1:0;
                        if(i12 != 0)
                        {
                            break label4;
                        }
                    }
                    InsertionSort.dontPaintJMarker = true;
                    break label5;
                }
                InsertionSort.dontPaintJMarker = false;
            }
            InsertionSort.dontPaintValMarker = false;
            int i13 = this.j;
            int i14 = i13 - 1;
            this.elementPosition = i14;
            this.setPosition("3.1.2.2.a");
            while(true)
            {
                int i15 = this.j;
                if(i15 <= 0)
                {
                    break;
                }
                int[] a20 = this.data;
                int i16 = this.j;
                int i17 = i16 - 1;
                int i18 = a20[i17];
                int i19 = this.val;
                if(i18 <= i19)
                {
                    break;
                }
                int i20 = InsertionSort.dontPaintVerticalBarJ?1:0;
                if(i20 == 1)
                {
                    InsertionSort.dontPaintVerticalBarJ = false;
                }
                int i21 = this.j;
                int i22 = this.j;
                int i23 = i22 - 1;
                this.swap(i21, i23);
                this.valPosition = -10;
                InsertionSort.dontPaintValMarker = true;
                this.elementPosition = -10;
                this.setPosition("3.1.2.2.1");
                int i24 = this.j;
                int i25 = i24 - 1;
                this.j = i25;
                com.cim.AIA.AlgorithmThread a21 = this.algorithmThread;
                InsertionSortThread dummy5 = (InsertionSortThread)a21;
                InsertionSortThread a22 = (InsertionSortThread)a21;
                com.cim.AIA.AlgorithmWindow a23 = a22.getWindow();
                int i26 = a23.isExpanded("3.1.2.2")?1:0;
                if(i26 != 0)
                {
                    InsertionSort.dontPaintVerticalBarJ = true;
                }
                InsertionSort.paintProcessedArrayI = true;
                this.setPosition("3.1.2.2.2");
                InsertionSort.dontPaintValMarker = false;
                int i27 = this.j;
                int i28 = i27 - 1;
                this.elementPosition = i28;
                this.setPosition("3.1.2.2.a");
            }
            InsertionSort.dontPaintValMarker = true;
            this.elementPosition = -10;
            this.setPosition("3.1.2.2.3");
            InsertionSort.dontPaintVerticalBarJ = false;
            int i29 = this.j;
            this.finalPosition = i29;
            int[] a24 = this.data;
            int i30 = this.j;
            int i31 = this.val;
            a24[i30] = i31;
            com.cim.AIA.AlgorithmThread a25 = this.algorithmThread;
            InsertionSortThread dummy6 = (InsertionSortThread)a25;
            InsertionSortThread a26 = (InsertionSortThread)a25;
            com.cim.AIA.AlgorithmWindow a27 = a26.getWindow();
            int i32 = a27.isExpanded("3.1.3")?1:0;
            label8: {
                label7: {
                    label6: {
                        if(i32 == 0)
                        {
                            break label6;
                        }
                        com.cim.AIA.AlgorithmThread a28 = this.algorithmThread;
                        InsertionSortThread dummy7 = (InsertionSortThread)a28;
                        InsertionSortThread a29 = (InsertionSortThread)a28;
                        com.cim.AIA.AlgorithmWindow a30 = a29.getWindow();
                        int i33 = a30.isExpanded("3.1")?1:0;
                        if(i33 != 0)
                        {
                            break label7;
                        }
                    }
                    InsertionSort.dontPaintJMarker = true;
                    break label8;
                }
                InsertionSort.dontPaintJMarker = false;
            }
            this.setPosition("3.1.3.1");
            this.finalPosition = -10;
            this.val = -10;
            this.j = -10;
            int i34 = this.i;
            int i35 = i34 + 1;
            this.i = i35;
            int i36 = this.i;
            this.recordOfI = i36;
            InsertionSort.paintProcessedArrayI = false;
            com.cim.AIA.AlgorithmThread a31 = this.algorithmThread;
            InsertionSortThread dummy8 = (InsertionSortThread)a31;
            InsertionSortThread a32 = (InsertionSortThread)a31;
            com.cim.AIA.AlgorithmWindow a33 = a32.getWindow();
            int i37 = a33.isExpanded("3.1")?1:0;
            if(i37 != 0)
            {
                InsertionSort.paintActiveValEarly = false;
            }
            else
            {
                InsertionSort.paintActiveValEarly = true;
            }
            int i38 = this.i;
            int[] a34 = this.data;
            int i39 = a34.length;
            if(i38 == i39)
            {
            }
            else
            {
                this.setPosition("3");
            }
        }
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
        String[] a3 = this.duplicateLabels;
        String s = a3[i0];
        String[] a4 = this.duplicateLabels;
        String[] a5 = this.duplicateLabels;
        String s0 = a5[i];
        a4[i0] = s0;
        String[] a6 = this.duplicateLabels;
        a6[i] = s;
        if(i0 != i)
        {
            java.util.Vector a7 = InsertionSort.moveRequests;
            com.cim.AIA.SwapRequest a8 = new com.cim.AIA.SwapRequest(i, i0);
            a7.addElement((Object)a8);
        }
    }
    
    static
    {
        java.awt.Color a = java.awt.Color.red;
        InsertionSort.activeArrayColor = a;
        String s = localization.Messages.getString("InsertionSort.0");
        InsertionSort.ARRAY_COLOR = s;
        String s0 = localization.Messages.getString("InsertionSort.1");
        InsertionSort.ACTIVE_ARRAY_COLOR = s0;
        String s1 = localization.Messages.getString("InsertionSort.2");
        InsertionSort.PROCESSED_ARRAY_COLOR = s1;
        String s2 = localization.Messages.getString("InsertionSort.3");
        InsertionSort.FINISHED_ARRAY_COLOR = s2;
        InsertionSort.dontPaintJMarker = false;
        InsertionSort.dontPaintValMarker = false;
        InsertionSort.dontPaintElementMarker = false;
        InsertionSort.dontPaintVerticalBarJ = false;
        InsertionSort.paintProcessedArrayI = false;
        InsertionSort.paintActiveValEarly = false;
        java.util.Vector a0 = new java.util.Vector();
        InsertionSort.moveRequests = a0;
    }
}