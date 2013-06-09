public class DistributionCounting extends com.cim.AIA.Algorithm implements com.cim.AIA.ColorSelectionListener {
    final public static int EMPTY_MARKER = -10;
    final protected static String HIGHLIGHT_COLOR;
    final protected static String NODE_COLOR;
    protected boolean allowGetAuxiliaryArray;
    protected int[] data;
    protected int[] symbolArray;
    protected int[] auxiliary;
    protected int dataArrow;
    protected int dataMarker;
    protected int symbolMarker;
    protected int auxiliaryMarker;
    protected java.awt.Color textColor;
    protected java.awt.Color highlightColor;
    protected java.awt.Color nodeColor;
    protected java.awt.Color background;
    protected boolean drawSymbolArrayAndLines;
    protected String[] dataDuplicateLabels;
    protected String[] auxiliaryDuplicateLabels;
    
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
        DistributionCounting a1 = new DistributionCounting((com.cim.AIA.AlgorithmThread)null, (Object)a0);
        a1.run();
    }
    
    public DistributionCounting(com.cim.AIA.AlgorithmThread a, Object a0)
    {
        super(a, a0);
        this.allowGetAuxiliaryArray = false;
        this.dataArrow = -10;
        this.dataMarker = -10;
        this.symbolMarker = -10;
        this.auxiliaryMarker = -10;
        java.awt.Color a1 = java.awt.Color.black;
        this.textColor = a1;
        java.awt.Color a2 = java.awt.Color.yellow;
        this.highlightColor = a2;
        java.awt.Color a3 = java.awt.Color.orange;
        this.nodeColor = a3;
        java.awt.Color a4 = java.awt.Color.white;
        this.background = a4;
        this.drawSymbolArrayAndLines = true;
        int[] dummy = (int[])a0;
        int[] a5 = (int[])a0;
        this.data = a5;
        this.initialise();
        com.cim.AIA.ConfigurationManager a6 = com.cim.AIA.ConfigurationManager.getConfigurationManager();
        java.awt.Color a7 = this.highlightColor;
        String s = DistributionCounting.HIGHLIGHT_COLOR;
        com.cim.AIA.ColorSelection a8 = new com.cim.AIA.ColorSelection(a7, s);
        a8.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        a6.addColorSelection(a8);
        java.awt.Color a9 = this.nodeColor;
        String s0 = DistributionCounting.NODE_COLOR;
        com.cim.AIA.ColorSelection a10 = new com.cim.AIA.ColorSelection(a9, s0);
        a10.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        a6.addColorSelection(a10);
        a6.addColorSelectionListener((com.cim.AIA.ColorSelectionListener)this);
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
            a2.setElementWidth(20);
            int i1 = 0;
            while(true)
            {
                int[] a3 = this.auxiliary;
                int i2 = a3.length;
                if(i1 >= i2)
                {
                    break;
                }
                int[] a4 = this.auxiliary;
                int i3 = a4[i1];
                Integer a5 = new Integer(i3);
                com.cim.AIA.Node a6 = new com.cim.AIA.Node((Object)a5, i1);
                java.awt.Color a7 = this.nodeColor;
                a6.setBackgroundColor(a7);
                java.awt.Color a8 = this.textColor;
                a6.setTextColor(a8);
                int[] a9 = this.auxiliary;
                int i4 = a9[i1];
                if(i4 == -10)
                {
                    a6.showObject(false);
                }
                int i5 = this.auxiliaryMarker;
                label4: {
                    if(i5 == -10)
                    {
                        break label4;
                    }
                    int i6 = this.auxiliaryMarker;
                    if(i6 == i1)
                    {
                        java.awt.Color a10 = this.highlightColor;
                        a6.setHighlightColor(a10);
                        a6.highlight();
                    }
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
                String[] a18 = this.auxiliaryDuplicateLabels;
                String s0 = a18[i1];
                if(s0 != null)
                {
                    StringBuilder a19 = new StringBuilder();
                    String[] a20 = this.auxiliaryDuplicateLabels;
                    String s1 = a20[i1];
                    StringBuilder a21 = a19.append(s1);
                    StringBuilder a22 = a21.append("");
                    String s2 = a22.toString();
                    java.awt.Point a23 = new java.awt.Point();
                    java.awt.Color a24 = this.textColor;
                    java.awt.Color a25 = this.background;
                    com.cim.AIA.StringMarker a26 = new com.cim.AIA.StringMarker(s2, a23, a24, a25);
                    a6.addMarker(a26);
                }
                a2.setValue(i1, (com.cim.AIA.Element)a6);
                int i7 = i1 + 1;
                i1 = i7;
            }
            a = a2;
        }
        return a;
    }
    
    public String getClassName()
    {
        String s = localization.Messages.getString("DistributionCounting.2");
        return s;
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
            a2.setColumGap(0);
            a2.setElementWidth(20);
            int i0 = 0;
            while(true)
            {
                int[] a3 = this.data;
                int i1 = a3.length;
                if(i0 >= i1)
                {
                    break;
                }
                int[] a4 = this.data;
                int i2 = a4[i0];
                Integer a5 = new Integer(i2);
                com.cim.AIA.Node a6 = new com.cim.AIA.Node((Object)a5, i0);
                java.awt.Color a7 = this.nodeColor;
                a6.setBackgroundColor(a7);
                java.awt.Color a8 = this.textColor;
                a6.setTextColor(a8);
                int[] a9 = this.data;
                int i3 = a9[i0];
                if(i3 == -10)
                {
                    a6.showObject(false);
                }
                int i4 = this.dataMarker;
                label3: {
                    if(i4 == -10)
                    {
                        break label3;
                    }
                    int i5 = this.dataMarker;
                    if(i5 == i0)
                    {
                        java.awt.Color a10 = this.highlightColor;
                        a6.setHighlightColor(a10);
                        a6.highlight();
                    }
                }
                StringBuilder a11 = new StringBuilder();
                StringBuilder a12 = a11.append(i0);
                StringBuilder a13 = a12.append("");
                String s = a13.toString();
                java.awt.Point a14 = new java.awt.Point();
                java.awt.Color a15 = this.textColor;
                java.awt.Color a16 = this.background;
                com.cim.AIA.StringMarker a17 = new com.cim.AIA.StringMarker(s, a14, a15, a16);
                a6.addMarker(a17);
                String[] a18 = this.dataDuplicateLabels;
                String s0 = a18[i0];
                if(s0 != null)
                {
                    StringBuilder a19 = new StringBuilder();
                    String[] a20 = this.dataDuplicateLabels;
                    String s1 = a20[i0];
                    StringBuilder a21 = a19.append(s1);
                    StringBuilder a22 = a21.append("");
                    String s2 = a22.toString();
                    java.awt.Point a23 = new java.awt.Point();
                    java.awt.Color a24 = this.textColor;
                    java.awt.Color a25 = this.background;
                    com.cim.AIA.StringMarker a26 = new com.cim.AIA.StringMarker(s2, a23, a24, a25);
                    a6.addMarker(a26);
                }
                a2.setValue(i0, (com.cim.AIA.Element)a6);
                int i6 = i0 + 1;
                i0 = i6;
            }
            a = a2;
        }
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
        this.allowGetAuxiliaryArray = false;
        int[] a = this.data;
        label0: {
            int i = 0;
            if(a == null)
            {
                break label0;
            }
            int[] a0 = this.data;
            int i0 = a0.length;
            if(i0 <= 0)
            {
                break label0;
            }
            int[] a1 = this.data;
            int i1 = a1[0];
            int i2 = i1;
            int i3 = 0;
            while(true)
            {
                int i4 = 0;
                int i5 = 0;
                int[] a2 = this.data;
                int i6 = i2;
                int i7 = a2.length;
                i = i6;
                int i8 = i;
                if(i3 >= i7)
                {
                    break;
                }
                else
                {
                    i4 = i8;
                }
                int[] a3 = this.data;
                int i9 = i4;
                int i10 = a3[i3];
                int i11 = i9;
                int i12 = i11;
                if(i10 <= i11)
                {
                    i5 = i12;
                }
                else
                {
                    int[] a4 = this.data;
                    int i13 = a4[i3];
                    i5 = i13;
                }
                int i14 = i3 + 1;
                i2 = i5;
                i3 = i14;
            }
            int i15 = i + 1;
            int[] a5 = new int[i15];
            this.symbolArray = a5;
        }
        int i16 = 0;
        while(true)
        {
            int[] a6 = this.symbolArray;
            int i17 = a6.length;
            if(i16 >= i17)
            {
                break;
            }
            else
            {
                int[] a7 = this.symbolArray;
                a7[i16] = -10;
                int i18 = i16 + 1;
                i16 = i18;
            }
        }
        int[] a8 = this.data;
        int i19 = a8.length;
        int[] a9 = new int[i19];
        this.auxiliary = a9;
        int i20 = 0;
        while(true)
        {
            int[] a10 = this.auxiliary;
            int i21 = a10.length;
            if(i20 >= i21)
            {
                break;
            }
            else
            {
                int[] a11 = this.auxiliary;
                a11[i20] = -10;
                int i22 = i20 + 1;
                i20 = i22;
            }
        }
        this.drawSymbolArrayAndLines = true;
        int[] a12 = this.data;
        int i23 = a12.length;
        Integer[] a13 = new Integer[i23];
        int i24 = 0;
        while(true)
        {
            int[] a14 = this.data;
            int i25 = a14.length;
            if(i24 >= i25)
            {
                String[] a15 = com.cim.AIA.DuplicateLabel.createDuplicateLabels((Object[])a13, true);
                this.dataDuplicateLabels = a15;
                String[] a16 = this.dataDuplicateLabels;
                int i26 = a16.length;
                String[] a17 = new String[i26];
                this.auxiliaryDuplicateLabels = a17;
                return;
            }
            else
            {
                int[] a18 = this.data;
                int i27 = a18[i24];
                Integer a19 = new Integer(i27);
                a13[i24] = a19;
                int i28 = i24 + 1;
                i24 = i28;
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
            String s2 = DistributionCounting.HIGHLIGHT_COLOR;
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
            String s3 = DistributionCounting.NODE_COLOR;
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
        this.setPosition("0");
        this.setPosition("1");
        this.setPosition("1.1");
        int i = 0;
        while(true)
        {
            int[] a = this.symbolArray;
            int i0 = a.length;
            if(i >= i0)
            {
                break;
            }
            else
            {
                this.symbolMarker = i;
                this.setPosition("1.1.1");
                int[] a0 = this.symbolArray;
                a0[i] = 0;
                this.setPosition("1.1.1.1");
                int i1 = i + 1;
                i = i1;
            }
        }
        this.symbolMarker = -10;
        this.setPosition("1.1.2");
        this.setPosition("1.2");
        int i2 = 0;
        while(true)
        {
            int[] a1 = this.data;
            int i3 = a1.length;
            if(i2 >= i3)
            {
                break;
            }
            else
            {
                this.dataArrow = i2;
                this.setPosition("1.2.1");
                int[] a2 = this.data;
                int i4 = a2[i2];
                this.symbolMarker = i4;
                int[] a3 = this.symbolArray;
                int[] a4 = this.data;
                int i5 = a4[i2];
                int i6 = a3[i5];
                int i7 = i6 + 1;
                a3[i5] = i7;
                this.setPosition("1.2.1.1");
                this.symbolMarker = -10;
                int i8 = i2 + 1;
                i2 = i8;
            }
        }
        this.dataArrow = -10;
        this.setPosition("1.2.2");
        this.setPosition("1.3");
        int i9 = 1;
        while(true)
        {
            int[] a5 = this.symbolArray;
            int i10 = a5.length;
            if(i9 >= i10)
            {
                break;
            }
            else
            {
                this.symbolMarker = i9;
                this.setPosition("1.3.1");
                int[] a6 = this.symbolArray;
                int[] a7 = this.symbolArray;
                int i11 = i9 - 1;
                int i12 = a7[i11];
                int[] a8 = this.symbolArray;
                int i13 = a8[i9];
                int i14 = i12 + i13;
                a6[i9] = i14;
                this.setPosition("1.3.1.1");
                int i15 = i9 + 1;
                i9 = i15;
            }
        }
        this.setPosition("1.3.2");
        this.setPosition("1.4");
        int[] a9 = this.symbolArray;
        int i16 = a9.length;
        int i17 = i16 - 1;
        int i18 = i17;
        while(true)
        {
            if(i18 <= 0)
            {
                break;
            }
            else
            {
                this.symbolMarker = i18;
                this.setPosition("1.4.1");
                int[] a10 = this.symbolArray;
                int[] a11 = this.symbolArray;
                int i19 = i18 - 1;
                int i20 = a11[i19];
                a10[i18] = i20;
                this.setPosition("1.4.1.1");
                int i21 = i18 + -1;
                i18 = i21;
            }
        }
        this.symbolMarker = 0;
        int[] a12 = this.symbolArray;
        a12[0] = 0;
        this.setPosition("1.4.2");
        this.symbolMarker = -10;
        this.allowGetAuxiliaryArray = true;
        this.setPosition("2");
        int i22 = 0;
        while(true)
        {
            int[] a13 = this.data;
            int i23 = a13.length;
            if(i22 >= i23)
            {
                break;
            }
            else
            {
                this.dataMarker = i22;
                this.setPosition("2.1");
                this.setPosition("3.1");
                int[] a14 = this.data;
                int i24 = a14[i22];
                this.symbolMarker = i24;
                this.setPosition("3.1.1");
                int[] a15 = this.auxiliary;
                int[] a16 = this.symbolArray;
                int[] a17 = this.data;
                int i25 = a17[i22];
                int i26 = a16[i25];
                int[] a18 = this.data;
                int i27 = a18[i22];
                a15[i26] = i27;
                int[] a19 = this.symbolArray;
                int[] a20 = this.data;
                int i28 = a20[i22];
                int i29 = a19[i28];
                this.auxiliaryMarker = i29;
                String[] a21 = this.auxiliaryDuplicateLabels;
                int i30 = this.auxiliaryMarker;
                String[] a22 = this.dataDuplicateLabels;
                String s = a22[i22];
                a21[i30] = s;
                this.setPosition("3.1.1.1");
                this.auxiliaryMarker = -10;
                this.setPosition("3.2");
                this.setPosition("3.2.1");
                int[] a23 = this.symbolArray;
                int[] a24 = this.data;
                int i31 = a24[i22];
                int i32 = a23[i31];
                int i33 = i32 + 1;
                a23[i31] = i33;
                this.setPosition("3.2.1.1");
                this.symbolMarker = -10;
                int i34 = i22 + 1;
                i22 = i34;
            }
        }
        this.dataMarker = -10;
        this.drawSymbolArrayAndLines = false;
        this.setPosition("5");
        int i35 = 0;
        while(true)
        {
            int[] a25 = this.auxiliary;
            int i36 = a25.length;
            if(i35 >= i36)
            {
                this.auxiliaryMarker = -10;
                this.setPosition("5.2");
                return;
            }
            else
            {
                this.auxiliaryMarker = i35;
                this.setPosition("5.1");
                this.dataMarker = i35;
                int[] a26 = this.data;
                int[] a27 = this.auxiliary;
                int i37 = a27[i35];
                a26[i35] = i37;
                String[] a28 = this.dataDuplicateLabels;
                String[] a29 = this.auxiliaryDuplicateLabels;
                String s0 = a29[i35];
                a28[i35] = s0;
                this.setPosition("5.1.1");
                this.dataMarker = -10;
                int i38 = i35 + 1;
                i35 = i38;
            }
        }
    }
    
    static
    {
        String s = localization.Messages.getString("DistributionCounting.0");
        DistributionCounting.HIGHLIGHT_COLOR = s;
        String s0 = localization.Messages.getString("DistributionCounting.1");
        DistributionCounting.NODE_COLOR = s0;
    }
}