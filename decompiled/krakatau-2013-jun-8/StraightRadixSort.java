public class StraightRadixSort extends com.cim.AIA.Algorithm implements com.cim.AIA.ColorSelectionListener {
    final public static int MARKER_SPACING = 4;
    final public static int BORDER_THICKNESS = 2;
    final public static int SCALE_FACTOR = 5;
    final public static int COLUM_GAP = 5;
    final public static int COLUM_WIDTH = 20;
    final public static int ADDITIONAL_MARKERS = 2;
    final protected static int CLEAR = 1;
    final protected static int WAITING = 2;
    final protected static int ACTIVE = 3;
    final protected static int PROCESSED = 4;
    final public static int EMPTY_MARKER = -10;
    final protected static String HIGHLIGHT_COLOR;
    final protected static String NODE_COLOR;
    final protected static String ACTIVE_COLOR;
    final protected static String WAITING_COLOR;
    final protected static String PROCESSED_COLOR;
    protected boolean allowGetAuxiliaryArray;
    protected int[] data;
    protected int[][] dataToSort;
    protected int[] symbolArray;
    protected int[] auxiliary;
    protected int dataArrow;
    protected int auxiliaryArrow;
    protected int dataMarker;
    protected int symbolMarker;
    protected int auxiliaryMarker;
    protected java.awt.Color nodeColor;
    protected java.awt.Color textColor;
    protected java.awt.Color highlightColor;
    protected java.awt.Color activeColor;
    protected java.awt.Color waitingColor;
    protected java.awt.Color processedColor;
    protected java.awt.Color background;
    protected boolean drawSymbolArrayAndLines;
    protected String[] dataDuplicateLabels;
    protected String[] auxiliaryDuplicateLabels;
    protected int[] extraAuxiliaryData;
    protected int[][] auxiliaryDataToSort;
    protected int bit;
    protected int[] colors;
    protected int bitLength;
    protected String[] colorStrings;
    protected java.util.Vector splitMarkers;
    
    public static void main(String[] a)
    {
        int[] a0 = new int[8];
        a0[0] = 2;
        a0[1] = 4;
        a0[2] = 5;
        a0[3] = 6;
        a0[4] = 6;
        a0[5] = 3;
        a0[6] = 2;
        a0[7] = 1;
        StraightRadixSort a1 = new StraightRadixSort((com.cim.AIA.AlgorithmThread)null, (Object)a0);
        a1.run();
    }
    
    public StraightRadixSort(com.cim.AIA.AlgorithmThread a, Object a0)
    {
        super(a, a0);
        this.allowGetAuxiliaryArray = false;
        this.dataArrow = -10;
        this.auxiliaryArrow = -10;
        this.dataMarker = -10;
        this.symbolMarker = -10;
        this.auxiliaryMarker = -10;
        java.awt.Color a1 = java.awt.Color.orange;
        this.nodeColor = a1;
        java.awt.Color a2 = java.awt.Color.black;
        this.textColor = a2;
        java.awt.Color a3 = java.awt.Color.yellow;
        this.highlightColor = a3;
        java.awt.Color a4 = java.awt.Color.red;
        this.activeColor = a4;
        java.awt.Color a5 = java.awt.Color.pink;
        this.waitingColor = a5;
        java.awt.Color a6 = java.awt.Color.gray;
        this.processedColor = a6;
        java.awt.Color a7 = java.awt.Color.white;
        this.background = a7;
        this.drawSymbolArrayAndLines = true;
        this.bit = -10;
        this.bitLength = 0;
        String[] a8 = new String[5];
        String s = StraightRadixSort.NODE_COLOR;
        a8[0] = s;
        String s0 = StraightRadixSort.HIGHLIGHT_COLOR;
        a8[1] = s0;
        String s1 = StraightRadixSort.WAITING_COLOR;
        a8[2] = s1;
        String s2 = StraightRadixSort.ACTIVE_COLOR;
        a8[3] = s2;
        String s3 = StraightRadixSort.PROCESSED_COLOR;
        a8[4] = s3;
        this.colorStrings = a8;
        java.util.Vector a9 = new java.util.Vector();
        this.splitMarkers = a9;
        int[] dummy = (int[])a0;
        int[] a10 = (int[])a0;
        this.data = a10;
        java.awt.Color[] a11 = new java.awt.Color[5];
        java.awt.Color a12 = this.nodeColor;
        a11[0] = a12;
        java.awt.Color a13 = this.highlightColor;
        a11[1] = a13;
        java.awt.Color a14 = this.waitingColor;
        a11[2] = a14;
        java.awt.Color a15 = this.activeColor;
        a11[3] = a15;
        java.awt.Color a16 = this.processedColor;
        a11[4] = a16;
        this.initialise();
        com.cim.AIA.ConfigurationManager a17 = com.cim.AIA.ConfigurationManager.getConfigurationManager();
        int i = 0;
        while(true)
        {
            String[] a18 = this.colorStrings;
            int i0 = a18.length;
            if(i >= i0)
            {
                a17.addColorSelectionListener((com.cim.AIA.ColorSelectionListener)this);
                return;
            }
            else
            {
                java.awt.Color a19 = a11[i];
                String[] a20 = this.colorStrings;
                String s4 = a20[i];
                com.cim.AIA.ColorSelection a21 = new com.cim.AIA.ColorSelection(a19, s4);
                a21.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
                a17.addColorSelection(a21);
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    public synchronized void calculateLines(com.cim.AIA.Paintable a, com.cim.AIA.ElementArray a0, com.cim.AIA.ElementArray a1)
    {
        int i = this.drawSymbolArrayAndLines?1:0;
        Object a2 = a;
        label1: {
            label0: {
                if(i != 0)
                {
                    break label0;
                }
                break label1;
            }
            int i0 = 0;
            while(true)
            {
                int i1 = a0.getSize();
                if(i0 >= i1)
                {
                    break;
                }
                com.cim.AIA.Element a3 = a0.getElement(i0);
                com.cim.AIA.Node dummy = (com.cim.AIA.Node)a3;
                com.cim.AIA.Node a4 = (com.cim.AIA.Node)a3;
                label2: {
                    if(a4 == null)
                    {
                        break label2;
                    }
                    int i2 = a4.getShowObject()?1:0;
                    if(i2 == 0)
                    {
                        break label2;
                    }
                    Object a5 = a4.getObject();
                    Integer dummy0 = (Integer)a5;
                    Integer a6 = (Integer)a5;
                    int i3 = a6.intValue();
                    com.cim.AIA.Element a7 = a1.getElement(i3);
                    if(a7 == null)
                    {
                        break label2;
                    }
                    com.cim.AIA.Line a8 = new com.cim.AIA.Line(0, 0, 0, 0);
                    java.awt.Point a9 = a4.getPosition();
                    int i4 = a9.x;
                    int i5 = a4.getWidth();
                    int i6 = i5 / 2;
                    int i7 = i4 + i6;
                    java.awt.Point a10 = a4.getPosition();
                    int i8 = a10.y;
                    int i9 = a4.getHeight();
                    int i10 = i8 + i9;
                    java.awt.Point a11 = new java.awt.Point(i7, i10);
                    java.awt.Point a12 = a7.getPosition();
                    int i11 = a12.x;
                    int i12 = a7.getWidth();
                    int i13 = i12 / 2;
                    int i14 = i11 + i13;
                    java.awt.Point a13 = a7.getPosition();
                    int i15 = a13.y;
                    java.awt.Point a14 = new java.awt.Point(i14, i15);
                    a8.setStartPosition(a11);
                    a8.setEndPosition(a14);
                    a8.showArrowHead(true);
                    a8.setDistanceFromStartForArrowHead(-3);
                    if(a2 != null)
                    {
                        ((com.cim.AIA.Paintable)a2).addDrawable((com.cim.AIA.Drawable)a8);
                    }
                }
                int i16 = i0 + 1;
                i0 = i16;
            }
        }
    }
    
    protected java.util.Vector generateQuestions()
    {
        return null;
    }
    
    public com.cim.AIA.MultipleTween generateTweens(com.cim.AIA.Paintable a, Object a0, int i)
    {
        return null;
    }
    
    public int getAuxiliaryArrow()
    {
        int i = this.auxiliaryArrow;
        return i;
    }
    
    public com.cim.AIA.ElementArray getAuxiliaryElementArray()
    {
        com.cim.AIA.ElementArray a = null;
        int i = this.allowGetAuxiliaryArray?1:0;
        label1: {
            label0: {
                if(i != 0)
                {
                    break label0;
                }
                a = null;
                break label1;
            }
            int[] a0 = this.auxiliary;
            label3: {
                label2: {
                    if(a0 == null)
                    {
                        break label2;
                    }
                    int[] a1 = this.auxiliary;
                    int i0 = a1.length;
                    if(i0 > 0)
                    {
                        break label3;
                    }
                }
                a = null;
                break label1;
            }
            com.cim.AIA.ElementArray a2 = new com.cim.AIA.ElementArray(0, 0);
            a2.setColumGap(0);
            a2.setElementWidth(25);
            int i1 = 0;
            while(true)
            {
                int[] a3 = this.auxiliary;
                int i2 = a3.length;
                if(i1 >= i2)
                {
                    break;
                }
                Integer a4 = new Integer(0);
                com.cim.AIA.Node a5 = new com.cim.AIA.Node((Object)a4, i1);
                java.awt.Color a6 = this.nodeColor;
                a5.setBackgroundColor(a6);
                java.awt.Color a7 = this.textColor;
                a5.setTextColor(a7);
                a5.setAdditionalMarkerSpacing(4);
                int[] a8 = this.extraAuxiliaryData;
                label4: {
                    if(a8 == null)
                    {
                        break label4;
                    }
                    int[] a9 = this.extraAuxiliaryData;
                    int i3 = a9[i1];
                    if(i3 != -10)
                    {
                        int[] a10 = this.extraAuxiliaryData;
                        int i4 = a10[i1];
                        Integer a11 = new Integer(i4);
                        a5.setObject((Object)a11);
                    }
                }
                int[] a12 = this.auxiliary;
                int i5 = a12[i1];
                if(i5 == -10)
                {
                    a5.showObject(false);
                }
                int i6 = this.auxiliaryMarker;
                label5: {
                    if(i6 == -10)
                    {
                        break label5;
                    }
                    int i7 = this.auxiliaryMarker;
                    if(i7 == i1)
                    {
                        java.awt.Color a13 = this.highlightColor;
                        a5.setHighlightColor(a13);
                        a5.highlight();
                    }
                }
                StringBuilder a14 = new StringBuilder();
                StringBuilder a15 = a14.append(i1);
                StringBuilder a16 = a15.append("");
                String s = a16.toString();
                java.awt.Point a17 = new java.awt.Point();
                java.awt.Color a18 = this.textColor;
                java.awt.Color a19 = this.background;
                com.cim.AIA.StringMarker a20 = new com.cim.AIA.StringMarker(s, a17, a18, a19);
                a5.addMarker(a20);
                String[] a21 = this.auxiliaryDuplicateLabels;
                label6: {
                    if(a21 == null)
                    {
                        break label6;
                    }
                    String[] a22 = this.auxiliaryDuplicateLabels;
                    String s0 = a22[i1];
                    if(s0 != null)
                    {
                        StringBuilder a23 = new StringBuilder();
                        String[] a24 = this.auxiliaryDuplicateLabels;
                        String s1 = a24[i1];
                        StringBuilder a25 = a23.append(s1);
                        StringBuilder a26 = a25.append("");
                        String s2 = a26.toString();
                        java.awt.Point a27 = new java.awt.Point();
                        java.awt.Color a28 = this.textColor;
                        java.awt.Color a29 = this.background;
                        com.cim.AIA.StringMarker a30 = new com.cim.AIA.StringMarker(s2, a27, a28, a29);
                        a5.addMarker(a30);
                    }
                }
                int[] a31 = this.extraAuxiliaryData;
                label7: {
                    if(a31 == null)
                    {
                        break label7;
                    }
                    int[] a32 = this.extraAuxiliaryData;
                    int i8 = a32[i1];
                    if(i8 == -10)
                    {
                        break label7;
                    }
                    int i9 = 0;
                    while(true)
                    {
                        int[][] a33 = this.auxiliaryDataToSort;
                        int[] a34 = a33[i1];
                        int i10 = a34.length;
                        if(i9 >= i10)
                        {
                            break;
                        }
                        else
                        {
                            java.awt.Color a35 = this.getColor(i9);
                            StringBuilder a36 = new StringBuilder();
                            int[][] a37 = this.auxiliaryDataToSort;
                            int[] a38 = a37[i1];
                            int i11 = a38[i9];
                            StringBuilder a39 = a36.append(i11);
                            StringBuilder a40 = a39.append("");
                            String s3 = a40.toString();
                            java.awt.Point a41 = new java.awt.Point();
                            java.awt.Color a42 = this.textColor;
                            com.cim.AIA.StringMarker a43 = new com.cim.AIA.StringMarker(s3, a41, a42, a35);
                            a5.addMarker(a43);
                            int i12 = i9 + 1;
                            i9 = i12;
                        }
                    }
                }
                a2.setValue(i1, (com.cim.AIA.Element)a5);
                int i13 = i1 + 1;
                i1 = i13;
            }
            a = a2;
        }
        return a;
    }
    
    public int getBit()
    {
        int i = this.bit;
        return i;
    }
    
    protected int[] getBit(int i, int i0)
    {
        String s = null;
        int[] a = null;
        String s0 = "";
        int i1 = 0;
        while(true)
        {
            if(i1 >= i0)
            {
                break;
            }
            else
            {
                StringBuilder a0 = new StringBuilder();
                StringBuilder a1 = a0.append(s0);
                StringBuilder a2 = a1.append("0");
                String s1 = a2.toString();
                int i2 = i1 + 1;
                s0 = s1;
                i1 = i2;
            }
        }
        String s2 = Integer.toBinaryString(i);
        StringBuilder a3 = new StringBuilder();
        int i3 = s0.length();
        int i4 = s2.length();
        int i5 = i3 - i4;
        String s3 = s0.substring(0, i5);
        StringBuilder a4 = a3.append(s3);
        StringBuilder a5 = a4.append(s2);
        String s4 = a5.toString();
        int[] a6 = new int[i0];
        int i6 = 0;
        try
        {
            while(true)
            {
                int i7 = s4.length();
                if(i6 >= i7)
                {
                    break;
                }
                else
                {
                    int i8 = s4.charAt(i6);
                    Character a7 = new Character((char)i8);
                    s = a7.toString();
                    int i9 = Integer.parseInt(s);
                    a6[i6] = i9;
                    int i10 = i6 + 1;
                    i6 = i10;
                }
            }
            a = a6;
        }
        catch(NumberFormatException a8)
        {
            StringBuilder a9 = new StringBuilder();
            StringBuilder a10 = a9.append("RadixExchangeSort.getBit Error in ");
            StringBuilder a11 = a10.append(s);
            StringBuilder a12 = a11.append(" ");
            StringBuilder a13 = a12.append((Object)a8);
            String s5 = a13.toString();
            this.displayMessage(s5);
            a = null;
        }
        return a;
    }
    
    protected int getBit(int i, int i0, int i1)
    {
        int i2 = 0;
        int[] a = this.getBit(i, i1);
        label2: {
            label1: {
                label0: {
                    if(a == null)
                    {
                        break label0;
                    }
                    if(i0 < 0)
                    {
                        break label0;
                    }
                    int i3 = a.length;
                    if(i0 < i3)
                    {
                        break label1;
                    }
                }
                i2 = -1;
                break label2;
            }
            int i4 = a[i0];
            i2 = i4;
        }
        return i2;
    }
    
    public int getBitLength()
    {
        int i = this.bitLength;
        return i;
    }
    
    public String getClassName()
    {
        return "StraightRadixSort";
    }
    
    public java.awt.Color getColor(int i)
    {
        java.awt.Color a = null;
        java.awt.Color a0 = this.background;
        label0: {
            java.awt.Color a1 = null;
            if(i < 0)
            {
                a = a0;
                break label0;
            }
            int[] a2 = this.colors;
            int i0 = a2.length;
            if(i >= i0)
            {
                a = a0;
                break label0;
            }
            int[] a3 = this.colors;
            int i1 = a3[i];
            switch(i1){
                case 4: {
                    a1 = this.waitingColor;
                    break;
                }
                case 3: {
                    java.awt.Color a4 = this.activeColor;
                    a = a4;
                    break label0;
                }
                case 2: {
                    java.awt.Color a5 = this.waitingColor;
                    a = a5;
                    break label0;
                }
                case 1: {
                    java.awt.Color a6 = this.background;
                    a = a6;
                    break label0;
                }
                default: {
                    a = a0;
                    break label0;
                }
            }
            int i2 = this.bit;
            if(i2 >= 0)
            {
                a = a1;
            }
            else
            {
                java.awt.Color a7 = this.processedColor;
                a = a7;
            }
        }
        return a;
    }
    
    public int getDataArrow()
    {
        int i = this.dataArrow;
        return i;
    }
    
    public com.cim.AIA.ElementArray getDataElementArray()
    {
        com.cim.AIA.ElementArray a = null;
        int[] a0 = this.data;
        label2: {
            label1: {
                label0: {
                    if(a0 == null)
                    {
                        break label0;
                    }
                    int[] a1 = this.data;
                    int i = a1.length;
                    if(i > 0)
                    {
                        break label1;
                    }
                }
                a = null;
                break label2;
            }
            com.cim.AIA.ElementArray a2 = new com.cim.AIA.ElementArray(0, 0);
            a2.setColumGap(5);
            a2.setElementWidth(20);
            int i0 = 0;
            while(true)
            {
                int[][] a3 = this.dataToSort;
                int i1 = a3.length;
                if(i0 >= i1)
                {
                    break;
                }
                int[] a4 = this.data;
                int i2 = a4[i0];
                java.awt.Color a5 = this.nodeColor;
                java.awt.Color a6 = java.awt.Color.black;
                java.awt.Point a7 = new java.awt.Point(0, 0);
                com.cim.AIA.VerticalBar a8 = new com.cim.AIA.VerticalBar(i0, i2, a5, a6, a7);
                java.awt.Color a9 = this.textColor;
                a8.setTextColor(a9);
                a8.setDrawValueMarker(false);
                a8.setAdditionalMarkerSpacing(4);
                a8.setThickness(2);
                a8.setScaleFactor(5.0f);
                int i3 = this.dataMarker;
                label3: {
                    if(i3 == -10)
                    {
                        break label3;
                    }
                    int i4 = this.dataMarker;
                    if(i0 == i4)
                    {
                        java.awt.Color a10 = this.highlightColor;
                        a8.setColor(a10);
                    }
                }
                StringBuilder a11 = new StringBuilder();
                int[] a12 = this.data;
                int i5 = a12[i0];
                StringBuilder a13 = a11.append(i5);
                StringBuilder a14 = a13.append("");
                String s = a14.toString();
                java.awt.Point a15 = new java.awt.Point();
                java.awt.Color a16 = this.textColor;
                java.awt.Color a17 = this.background;
                com.cim.AIA.StringMarker a18 = new com.cim.AIA.StringMarker(s, a15, a16, a17);
                a8.addMarker(a18);
                String[] a19 = this.dataDuplicateLabels;
                label4: {
                    if(a19 == null)
                    {
                        break label4;
                    }
                    String[] a20 = this.dataDuplicateLabels;
                    String s0 = a20[i0];
                    if(s0 != null)
                    {
                        StringBuilder a21 = new StringBuilder();
                        String[] a22 = this.dataDuplicateLabels;
                        String s1 = a22[i0];
                        StringBuilder a23 = a21.append(s1);
                        StringBuilder a24 = a23.append("");
                        String s2 = a24.toString();
                        java.awt.Point a25 = new java.awt.Point();
                        java.awt.Color a26 = this.textColor;
                        java.awt.Color a27 = this.background;
                        com.cim.AIA.StringMarker a28 = new com.cim.AIA.StringMarker(s2, a25, a26, a27);
                        a8.addMarker(a28);
                    }
                }
                int i6 = 0;
                while(true)
                {
                    int[][] a29 = this.dataToSort;
                    int[] a30 = a29[i0];
                    int i7 = a30.length;
                    if(i6 >= i7)
                    {
                        a2.setValue(i0, (com.cim.AIA.Element)a8);
                        int i8 = i0 + 1;
                        i0 = i8;
                    }
                    else
                    {
                        java.awt.Color a31 = this.getColor(i6);
                        StringBuilder a32 = new StringBuilder();
                        int[][] a33 = this.dataToSort;
                        int[] a34 = a33[i0];
                        int i9 = a34[i6];
                        StringBuilder a35 = a32.append(i9);
                        StringBuilder a36 = a35.append("");
                        String s3 = a36.toString();
                        java.awt.Point a37 = new java.awt.Point();
                        java.awt.Color a38 = this.textColor;
                        com.cim.AIA.StringMarker a39 = new com.cim.AIA.StringMarker(s3, a37, a38, a31);
                        a8.addMarker(a39);
                        int i10 = i6 + 1;
                        i6 = i10;
                    }
                }
            }
            a = a2;
        }
        return a;
    }
    
    public java.util.Vector getSplitValues()
    {
        java.util.Vector a = this.splitMarkers;
        return a;
    }
    
    public com.cim.AIA.ElementArray getSymbolElementArray()
    {
        com.cim.AIA.ElementArray a = null;
        int[] a0 = this.symbolArray;
        label2: {
            label1: {
                label0: {
                    if(a0 == null)
                    {
                        break label0;
                    }
                    int[] a1 = this.symbolArray;
                    int i = a1.length;
                    if(i > 0)
                    {
                        break label1;
                    }
                }
                a = null;
                break label2;
            }
            int i0 = this.drawSymbolArrayAndLines?1:0;
            label3: {
                if(i0 != 0)
                {
                    break label3;
                }
                a = null;
                break label2;
            }
            com.cim.AIA.ElementArray a2 = new com.cim.AIA.ElementArray(0, 0);
            a2.setColumGap(0);
            a2.setElementWidth(20);
            int i1 = 0;
            while(true)
            {
                int[] a3 = this.symbolArray;
                int i2 = a3.length;
                if(i1 >= i2)
                {
                    break;
                }
                int[] a4 = this.symbolArray;
                int i3 = a4[i1];
                Integer a5 = new Integer(i3);
                com.cim.AIA.Node a6 = new com.cim.AIA.Node((Object)a5, i1);
                java.awt.Color a7 = this.nodeColor;
                a6.setBackgroundColor(a7);
                java.awt.Color a8 = this.textColor;
                a6.setTextColor(a8);
                int i4 = this.symbolMarker;
                label4: {
                    if(i4 == -10)
                    {
                        break label4;
                    }
                    int i5 = this.symbolMarker;
                    if(i5 == i1)
                    {
                        java.awt.Color a9 = this.highlightColor;
                        a6.setHighlightColor(a9);
                        a6.highlight();
                    }
                }
                int[] a10 = this.symbolArray;
                int i6 = a10[i1];
                if(i6 == -10)
                {
                    a6.showObject(false);
                }
                StringBuilder a11 = new StringBuilder();
                StringBuilder a12 = a11.append(i1);
                StringBuilder a13 = a12.append("");
                String s = a13.toString();
                java.awt.Point a14 = new java.awt.Point();
                java.awt.Color a15 = this.textColor;
                java.awt.Color a16 = this.background;
                com.cim.AIA.StringMarker a17 = new com.cim.AIA.StringMarker(s, a14, a15, a16);
                a6.addMarker(a17);
                a6.setMarkersBelowValue(false);
                a2.setValue(i1, (com.cim.AIA.Element)a6);
                int i7 = i1 + 1;
                i1 = i7;
            }
            a = a2;
        }
        return a;
    }
    
    protected boolean hasQuestions()
    {
        return false;
    }
    
    protected void initialise()
    {
        int i = 0;
        this.allowGetAuxiliaryArray = false;
        java.util.Vector a = new java.util.Vector();
        this.splitMarkers = a;
        int[] a0 = this.data;
        label0: {
            if(a0 == null)
            {
                i = 0;
                break label0;
            }
            int[] a1 = this.data;
            int i0 = a1.length;
            if(i0 <= 0)
            {
                i = 0;
                break label0;
            }
            int i1 = 0;
            int i2 = 0;
            while(true)
            {
                int i3 = 0;
                int[] a2 = this.data;
                int i4 = i1;
                int i5 = a2.length;
                int i6 = i4;
                int i7 = i6;
                if(i2 >= i5)
                {
                    i = i6;
                    break;
                }
                else
                {
                    i3 = i7;
                }
                int[] a3 = this.data;
                int i8 = i3;
                int i9 = a3[i2];
                int i10 = i8;
                int[] a4 = this.data;
                int i11 = i10;
                int i12 = a4[i11];
                int i13 = i11;
                int i14 = i13;
                int i15 = i9 <= i12?i14:i2;
                int i16 = i2 + 1;
                i1 = i15;
                i2 = i16;
            }
        }
        int[] a5 = new int[2];
        this.symbolArray = a5;
        int[] a6 = this.data;
        int i17 = a6.length;
        if(i17 > 0)
        {
            int[] a7 = this.data;
            int i18 = a7[i];
            String s = Integer.toBinaryString(i18);
            int i19 = s.length();
            this.bitLength = i19;
        }
        int[] a8 = this.data;
        int i20 = a8.length;
        int[][] a9 = new int[i20][];
        this.dataToSort = a9;
        int i21 = 0;
        while(true)
        {
            int[][] a10 = this.dataToSort;
            int i22 = a10.length;
            if(i21 >= i22)
            {
                break;
            }
            else
            {
                int[][] a11 = this.dataToSort;
                int i23 = this.bitLength;
                int[] a12 = new int[i23];
                a11[i21] = a12;
                int i24 = i21 + 1;
                i21 = i24;
            }
        }
        int i25 = 0;
        while(true)
        {
            int[][] a13 = this.dataToSort;
            int i26 = a13.length;
            if(i25 >= i26)
            {
                break;
            }
            else
            {
                int[][] a14 = this.dataToSort;
                int[] a15 = this.data;
                int i27 = a15[i25];
                int i28 = this.bitLength;
                int[] a16 = this.getBit(i27, i28);
                a14[i25] = a16;
                int i29 = i25 + 1;
                i25 = i29;
            }
        }
        int i30 = 0;
        while(true)
        {
            int[] a17 = this.symbolArray;
            int i31 = a17.length;
            if(i30 >= i31)
            {
                break;
            }
            else
            {
                int[] a18 = this.symbolArray;
                a18[i30] = -10;
                int i32 = i30 + 1;
                i30 = i32;
            }
        }
        int[] a19 = this.data;
        int i33 = a19.length;
        int[] a20 = new int[i33];
        this.auxiliary = a20;
        int i34 = 0;
        while(true)
        {
            int[] a21 = this.auxiliary;
            int i35 = a21.length;
            if(i34 >= i35)
            {
                break;
            }
            else
            {
                int[] a22 = this.auxiliary;
                a22[i34] = -10;
                int i36 = i34 + 1;
                i34 = i36;
            }
        }
        this.drawSymbolArrayAndLines = true;
        int[] a23 = this.data;
        int i37 = a23.length;
        Integer[] a24 = new Integer[i37];
        int i38 = 0;
        while(true)
        {
            int[] a25 = this.data;
            int i39 = a25.length;
            if(i38 >= i39)
            {
                break;
            }
            else
            {
                int[] a26 = this.data;
                int i40 = a26[i38];
                Integer a27 = new Integer(i40);
                a24[i38] = a27;
                int i41 = i38 + 1;
                i38 = i41;
            }
        }
        String[] a28 = com.cim.AIA.DuplicateLabel.createDuplicateLabels((Object[])a24, true);
        this.dataDuplicateLabels = a28;
        this.bit = -10;
        int i42 = this.bitLength;
        int[] a29 = new int[i42];
        this.colors = a29;
        int i43 = 0;
        while(true)
        {
            int[] a30 = this.colors;
            int i44 = a30.length;
            if(i43 >= i44)
            {
                return;
            }
            else
            {
                int[] a31 = this.colors;
                a31[i43] = 1;
                int i45 = i43 + 1;
                i43 = i45;
            }
        }
    }
    
    public void processColorSelection(com.cim.AIA.ColorSelection a)
    {
        String s = a.getAtributeName();
        int i = 0;
        while(true)
        {
            String[] a0 = this.colorStrings;
            int i0 = a0.length;
            if(i >= i0)
            {
                break;
            }
            String[] a1 = this.colorStrings;
            String s0 = a1[i];
            int i1 = s.equalsIgnoreCase(s0)?1:0;
            label0: {
                if(i1 == 0)
                {
                    break label0;
                }
                switch(i){
                    case 4: {
                        java.awt.Color a2 = a.getColor();
                        this.processedColor = a2;
                        break;
                    }
                    case 3: {
                        java.awt.Color a3 = a.getColor();
                        this.activeColor = a3;
                        break;
                    }
                    case 2: {
                        java.awt.Color a4 = a.getColor();
                        this.waitingColor = a4;
                        break;
                    }
                    case 1: {
                        java.awt.Color a5 = a.getColor();
                        this.highlightColor = a5;
                        break;
                    }
                    case 0: {
                        java.awt.Color a6 = a.getColor();
                        this.nodeColor = a6;
                        break;
                    }
                }
            }
            int i2 = i + 1;
            i = i2;
        }
        String s1 = com.cim.AIA.ColorSelection.BACKGROUND;
        int i3 = s.equalsIgnoreCase(s1)?1:0;
        label2: {
            label1: {
                if(i3 == 0)
                {
                    break label1;
                }
                java.awt.Color a7 = a.getColor();
                this.background = a7;
                break label2;
            }
            String s2 = com.cim.AIA.ColorSelection.TEXT_COLOR;
            int i4 = s.equalsIgnoreCase(s2)?1:0;
            if(i4 != 0)
            {
                java.awt.Color a8 = a.getColor();
                this.textColor = a8;
            }
        }
    }
    
    public void reInitialise(Object a)
    {
        int[] dummy = (int[])a;
        int[] a0 = (int[])a;
        this.data = a0;
        this.initialise();
    }
    
    protected void removeAllAnimationRequests()
    {
    }
    
    protected void removeAllQuestions()
    {
    }
    
    protected void run()
    {
        int i = 0;
        while(true)
        {
            int[] a = this.colors;
            int i0 = a.length;
            if(i >= i0)
            {
                break;
            }
            else
            {
                int[] a0 = this.colors;
                a0[i] = 2;
                int i1 = i + 1;
                i = i1;
            }
        }
        this.setPosition("0");
        int i2 = this.bitLength;
        int i3 = i2 - 1;
        this.bit = i3;
        label0: while(true)
        {
            int i4 = this.bit;
            if(i4 < 0)
            {
                this.setPosition("8");
                return;
            }
            int[] a1 = this.colors;
            int i5 = this.bit;
            a1[i5] = 3;
            this.drawSymbolArrayAndLines = true;
            this.allowGetAuxiliaryArray = false;
            String[] a2 = this.dataDuplicateLabels;
            int i6 = a2.length;
            String[] a3 = new String[i6];
            this.auxiliaryDuplicateLabels = a3;
            String[] a4 = this.dataDuplicateLabels;
            int i7 = a4.length;
            int[] a5 = new int[i7];
            this.extraAuxiliaryData = a5;
            String[] a6 = this.dataDuplicateLabels;
            int i8 = a6.length;
            int[][] a7 = new int[i8][];
            this.auxiliaryDataToSort = a7;
            int i9 = 0;
            while(true)
            {
                int[][] a8 = this.auxiliaryDataToSort;
                int i10 = a8.length;
                if(i9 >= i10)
                {
                    break;
                }
                else
                {
                    int[][] a9 = this.auxiliaryDataToSort;
                    int i11 = this.bitLength;
                    int[] a10 = new int[i11];
                    a9[i9] = a10;
                    int i12 = i9 + 1;
                    i9 = i12;
                }
            }
            int i13 = 0;
            while(true)
            {
                int[] a11 = this.extraAuxiliaryData;
                int i14 = a11.length;
                if(i13 >= i14)
                {
                    break;
                }
                else
                {
                    int[] a12 = this.extraAuxiliaryData;
                    a12[i13] = -10;
                    int i15 = i13 + 1;
                    i13 = i15;
                }
            }
            this.setPosition("6");
            this.setPosition("6.1");
            this.setPosition("1");
            this.setPosition("1.1");
            int i16 = 0;
            while(true)
            {
                int[] a13 = this.symbolArray;
                int i17 = a13.length;
                if(i16 >= i17)
                {
                    break;
                }
                else
                {
                    this.symbolMarker = i16;
                    this.setPosition("1.1.1");
                    int[] a14 = this.symbolArray;
                    a14[i16] = 0;
                    this.setPosition("1.1.1.1");
                    int i18 = i16 + 1;
                    i16 = i18;
                }
            }
            this.symbolMarker = -10;
            this.setPosition("1.1.2");
            this.setPosition("1.2");
            int i19 = 0;
            while(true)
            {
                int[][] a15 = this.dataToSort;
                int i20 = a15.length;
                if(i19 >= i20)
                {
                    break;
                }
                else
                {
                    this.dataArrow = i19;
                    this.setPosition("1.2.1");
                    int[][] a16 = this.dataToSort;
                    int[] a17 = a16[i19];
                    int i21 = this.bit;
                    int i22 = a17[i21];
                    this.symbolMarker = i22;
                    int[] a18 = this.symbolArray;
                    int[][] a19 = this.dataToSort;
                    int[] a20 = a19[i19];
                    int i23 = this.bit;
                    int i24 = a20[i23];
                    int i25 = a18[i24];
                    int i26 = i25 + 1;
                    a18[i24] = i26;
                    this.setPosition("1.2.1.1");
                    this.symbolMarker = -10;
                    int i27 = i19 + 1;
                    i19 = i27;
                }
            }
            this.dataArrow = -10;
            this.setPosition("1.2.2");
            this.setPosition("1.3");
            int i28 = 1;
            while(true)
            {
                int[] a21 = this.symbolArray;
                int i29 = a21.length;
                if(i28 >= i29)
                {
                    break;
                }
                else
                {
                    this.symbolMarker = i28;
                    this.setPosition("1.3.1");
                    int[] a22 = this.symbolArray;
                    int[] a23 = this.symbolArray;
                    int i30 = i28 - 1;
                    int i31 = a23[i30];
                    int[] a24 = this.symbolArray;
                    int i32 = a24[i28];
                    int i33 = i31 + i32;
                    a22[i28] = i33;
                    this.setPosition("1.3.1.1");
                    int i34 = i28 + 1;
                    i28 = i34;
                }
            }
            this.symbolMarker = -10;
            this.setPosition("1.3.2");
            this.setPosition("1.4");
            int[] a25 = this.symbolArray;
            int[] a26 = this.symbolArray;
            int i35 = a26[0];
            a25[1] = i35;
            this.symbolMarker = 1;
            this.setPosition("1.4.1");
            int[] a27 = this.symbolArray;
            a27[0] = 0;
            this.symbolMarker = 0;
            this.setPosition("1.4.2");
            this.symbolMarker = -10;
            this.allowGetAuxiliaryArray = true;
            this.setPosition("2");
            int i36 = 0;
            while(true)
            {
                int[][] a28 = this.dataToSort;
                int i37 = a28.length;
                if(i36 >= i37)
                {
                    break;
                }
                this.dataArrow = i36;
                this.setPosition("2.1");
                this.setPosition("3.1");
                int[][] a29 = this.dataToSort;
                int[] a30 = a29[i36];
                int i38 = this.bit;
                int i39 = a30[i38];
                this.symbolMarker = i39;
                this.setPosition("3.1.1");
                int[] a31 = this.auxiliary;
                int[] a32 = this.symbolArray;
                int[][] a33 = this.dataToSort;
                int[] a34 = a33[i36];
                int i40 = this.bit;
                int i41 = a34[i40];
                int i42 = a32[i41];
                int[][] a35 = this.dataToSort;
                int[] a36 = a35[i36];
                int i43 = this.bit;
                int i44 = a36[i43];
                a31[i42] = i44;
                int[] a37 = this.symbolArray;
                int[][] a38 = this.dataToSort;
                int[] a39 = a38[i36];
                int i45 = this.bit;
                int i46 = a39[i45];
                int i47 = a37[i46];
                this.auxiliaryMarker = i47;
                String[] a40 = this.auxiliaryDuplicateLabels;
                int i48 = this.auxiliaryMarker;
                String[] a41 = this.dataDuplicateLabels;
                String s = a41[i36];
                a40[i48] = s;
                int[] a42 = this.extraAuxiliaryData;
                int i49 = this.auxiliaryMarker;
                int[] a43 = this.data;
                int i50 = a43[i36];
                a42[i49] = i50;
                int i51 = 0;
                while(true)
                {
                    int[][] a44 = this.dataToSort;
                    int[] a45 = a44[i36];
                    int i52 = a45.length;
                    if(i51 >= i52)
                    {
                        this.setPosition("3.1.1.1");
                        this.auxiliaryMarker = -10;
                        this.setPosition("3.2");
                        this.setPosition("3.2.1");
                        int[] a46 = this.symbolArray;
                        int[][] a47 = this.dataToSort;
                        int[] a48 = a47[i36];
                        int i53 = this.bit;
                        int i54 = a48[i53];
                        int i55 = a46[i54];
                        int i56 = i55 + 1;
                        a46[i54] = i56;
                        this.setPosition("3.2.1.1");
                        this.symbolMarker = -10;
                        int i57 = i36 + 1;
                        i36 = i57;
                    }
                    else
                    {
                        int[][] a49 = this.auxiliaryDataToSort;
                        int i58 = this.auxiliaryMarker;
                        int[] a50 = a49[i58];
                        int[][] a51 = this.dataToSort;
                        int[] a52 = a51[i36];
                        int i59 = a52[i51];
                        a50[i51] = i59;
                        int i60 = i51 + 1;
                        i51 = i60;
                    }
                }
            }
            this.dataArrow = -10;
            this.dataMarker = -10;
            this.drawSymbolArrayAndLines = false;
            int i61 = 0;
            while(true)
            {
                int[] a53 = this.symbolArray;
                int i62 = a53.length;
                if(i61 >= i62)
                {
                    break;
                }
                else
                {
                    int[] a54 = this.symbolArray;
                    a54[i61] = -10;
                    int i63 = i61 + 1;
                    i61 = i63;
                }
            }
            java.util.Vector a55 = this.splitMarkers;
            a55.removeAllElements();
            this.setPosition("5");
            int i64 = 0;
            while(true)
            {
                int[] a56 = this.auxiliary;
                int i65 = a56.length;
                if(i64 >= i65)
                {
                    break;
                }
                else
                {
                    this.auxiliaryMarker = i64;
                    this.auxiliaryArrow = i64;
                    this.setPosition("5.1");
                    this.dataMarker = i64;
                    int[][] a57 = this.dataToSort;
                    int[] a58 = this.extraAuxiliaryData;
                    int i66 = a58[i64];
                    int i67 = this.bitLength;
                    int[] a59 = this.getBit(i66, i67);
                    a57[i64] = a59;
                    String[] a60 = this.dataDuplicateLabels;
                    String[] a61 = this.auxiliaryDuplicateLabels;
                    String s0 = a61[i64];
                    a60[i64] = s0;
                    int[] a62 = this.data;
                    int[] a63 = this.extraAuxiliaryData;
                    int i68 = a63[i64];
                    a62[i64] = i68;
                    this.setPosition("5.1.1");
                    this.auxiliaryMarker = -10;
                    this.dataMarker = -10;
                    int i69 = i64 + 1;
                    i64 = i69;
                }
            }
            this.auxiliaryArrow = -10;
            this.auxiliaryMarker = -10;
            this.setPosition("5.2");
            int i70 = 0;
            while(true)
            {
                int[] a64 = this.auxiliary;
                int i71 = a64.length;
                if(i70 >= i71)
                {
                    break;
                }
                else
                {
                    int[] a65 = this.auxiliary;
                    a65[i70] = -10;
                    int i72 = i70 + 1;
                    i70 = i72;
                }
            }
            int[] a66 = this.colors;
            int i73 = this.bit;
            a66[i73] = 4;
            this.allowGetAuxiliaryArray = false;
            int i74 = this.bit;
            int i75 = i74;
            label1: while(true)
            {
                int i76 = this.bitLength;
                if(i75 >= i76)
                {
                    this.setPosition("7");
                    int i77 = this.bit;
                    int i78 = i77 - 1;
                    this.bit = i78;
                    continue label0;
                }
                int i79 = -1;
                int i80 = 0;
                while(true)
                {
                    int i81 = 0;
                    int i82 = 0;
                    int[][] a67 = this.dataToSort;
                    int i83 = i79;
                    int i84 = a67.length;
                    int i85 = i83;
                    int i86 = i85;
                    if(i80 >= i84)
                    {
                        int i87 = i75 + 1;
                        i75 = i87;
                        continue label1;
                    }
                    else
                    {
                        i81 = i86;
                    }
                    label3: {
                        int i88 = 0;
                        label2: {
                            int i89 = i81;
                            if(i81 != -1)
                            {
                                i88 = i89;
                                break label2;
                            }
                            int[][] a68 = this.dataToSort;
                            int[] a69 = a68[i80];
                            int i90 = a69[i75];
                            i82 = i90;
                            break label3;
                        }
                        int[][] a70 = this.dataToSort;
                        int i91 = i88;
                        int[] a71 = a70[i80];
                        int i92 = i91;
                        int i93 = a71[i75];
                        int i94 = i92;
                        int i95 = i94;
                        if(i93 == i94)
                        {
                            i82 = i95;
                        }
                        else
                        {
                            java.util.Vector a72 = this.splitMarkers;
                            SplitMarker a73 = new SplitMarker(i75, i80);
                            a72.addElement((Object)a73);
                            int[][] a74 = this.dataToSort;
                            int[] a75 = a74[i80];
                            int i96 = a75[i75];
                            i82 = i96;
                        }
                    }
                    int i97 = i80 + 1;
                    i79 = i82;
                    i80 = i97;
                }
            }
        }
    }
    
    static
    {
        String s = localization.Messages.getString("StraightRadixSort.0");
        StraightRadixSort.HIGHLIGHT_COLOR = s;
        String s0 = localization.Messages.getString("StraightRadixSort.1");
        StraightRadixSort.NODE_COLOR = s0;
        String s1 = localization.Messages.getString("StraightRadixSort.2");
        StraightRadixSort.ACTIVE_COLOR = s1;
        String s2 = localization.Messages.getString("StraightRadixSort.3");
        StraightRadixSort.WAITING_COLOR = s2;
        String s3 = localization.Messages.getString("StraightRadixSort.4");
        StraightRadixSort.PROCESSED_COLOR = s3;
    }
}