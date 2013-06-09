public class QuickSortCanvas extends com.cim.AIA.AlgorithmCanvas {
    final private static long serialVersionUID = 3008138147945152127L;
    protected java.awt.Color textColor;
    protected int xBuffer;
    protected int yBuffer;
    protected int gapBetweenArrayAndQuickSort;
    protected int spacingBetweenInformationAndQuickSort;
    protected int boxWidth;
    protected int activeYBuffer;
    protected QuickSort quickSort;
    protected MyElementArrayQS elementArray;
    protected int iWidth;
    protected int jWidth;
    protected java.awt.Point iPoint;
    protected java.awt.Point jPoint;
    protected int pValue;
    protected boolean pValueReady;
    protected java.awt.Point activeLeft;
    protected java.awt.Point activeRight;
    protected java.awt.Point oldIPoint;
    protected java.awt.Point oldJPoint;
    protected boolean tweening;
    
    public QuickSortCanvas()
    {
        super();
        java.awt.Color a = java.awt.Color.black;
        this.textColor = a;
        this.xBuffer = 60;
        this.yBuffer = 150;
        this.gapBetweenArrayAndQuickSort = 120;
        this.spacingBetweenInformationAndQuickSort = 10;
        this.boxWidth = 20;
        this.activeYBuffer = 5;
        this.iWidth = 0;
        this.jWidth = 0;
        this.iPoint = null;
        this.jPoint = null;
        this.pValue = -1;
        this.pValueReady = false;
        this.activeLeft = null;
        this.activeRight = null;
        this.tweening = false;
    }
    
    protected void calculateMarkerPosition()
    {
        this.jPoint = null;
        this.iPoint = null;
        this.activeRight = null;
        this.activeLeft = null;
        QuickSort a = this.quickSort;
        QuickSort a0 = a.getActive();
        label0: {
            if(a0 == null)
            {
                break label0;
            }
            int i = a0.getI();
            label1: {
                if(i == -10)
                {
                    break label1;
                }
                MyElementArrayQS a1 = this.elementArray;
                int i0 = a0.getI();
                com.cim.AIA.Element a2 = a1.getElement(i0);
                if(a2 != null)
                {
                    int i1 = a2.getWidth();
                    this.iWidth = i1;
                    java.awt.Point a3 = a2.getPosition();
                    int i2 = a3.x;
                    int i3 = a3.y;
                    java.awt.Point a4 = new java.awt.Point(i2, i3);
                    this.iPoint = a4;
                }
            }
            int i4 = a0.getJ();
            label2: {
                if(i4 == -10)
                {
                    break label2;
                }
                MyElementArrayQS a5 = this.elementArray;
                int i5 = a0.getJ();
                com.cim.AIA.Element a6 = a5.getElement(i5);
                if(a6 != null)
                {
                    int i6 = a6.getWidth();
                    this.jWidth = i6;
                    java.awt.Point a7 = a6.getPosition();
                    int i7 = a7.x;
                    int i8 = a7.y;
                    java.awt.Point a8 = new java.awt.Point(i7, i8);
                    this.jPoint = a8;
                }
            }
            int i9 = a0.getRight();
            label3: {
                if(i9 == -10)
                {
                    break label3;
                }
                MyElementArrayQS a9 = this.elementArray;
                int i10 = a0.getRight();
                com.cim.AIA.Element a10 = a9.getElement(i10);
                if(a10 != null)
                {
                    java.awt.Point a11 = a10.getPosition();
                    int i11 = a11.x;
                    MyElementArrayQS a12 = this.elementArray;
                    int i12 = a12.getElementWidth();
                    int i13 = i11 + i12;
                    MyElementArrayQS a13 = this.elementArray;
                    int i14 = a13.getColumGap();
                    int i15 = i14 / 2;
                    int i16 = i13 + i15;
                    int i17 = a11.y;
                    java.awt.Point a14 = new java.awt.Point(i16, i17);
                    this.activeRight = a14;
                }
            }
            int i18 = a0.getLeft();
            if(i18 == -10)
            {
                break label0;
            }
            MyElementArrayQS a15 = this.elementArray;
            int i19 = a0.getLeft();
            com.cim.AIA.Element a16 = a15.getElement(i19);
            if(a16 != null)
            {
                java.awt.Point a17 = a16.getPosition();
                int i20 = a17.x;
                MyElementArrayQS a18 = this.elementArray;
                int i21 = a18.getColumGap();
                int i22 = i21 / 2;
                int i23 = i20 - i22;
                int i24 = a17.y;
                java.awt.Point a19 = new java.awt.Point(i23, i24);
                this.activeLeft = a19;
            }
        }
    }
    
    public void displayAlgorithm(java.awt.Graphics a)
    {
        QuickSort a0 = this.quickSort;
        label0: {
            if(a0 == null)
            {
                break label0;
            }
            MyElementArrayQS a1 = this.elementArray;
            if(a1 == null)
            {
                break label0;
            }
            QuickSort a2 = this.quickSort;
            int i = a2.doDrawRectangles()?1:0;
            label1: {
                if(i == 0)
                {
                    break label1;
                }
                this.layoutAlgorithm(a);
                java.awt.Color a3 = this.textColor;
                a.setColor(a3);
                java.awt.Point a4 = this.iPoint;
                if(a4 != null)
                {
                    int i0 = a4.x;
                    int i1 = this.iWidth;
                    int i2 = i1 / 2;
                    int i3 = i0 + i2;
                    java.awt.FontMetrics a5 = a.getFontMetrics();
                    int i4 = a5.stringWidth("i");
                    int i5 = i4 / 2;
                    int i6 = i3 - i5;
                    int i7 = a4.y;
                    java.awt.FontMetrics a6 = a.getFontMetrics();
                    int i8 = a6.getHeight();
                    int i9 = 3 * i8;
                    int i10 = i7 + i9;
                    a.drawString("i", i6, i10);
                }
                java.awt.Point a7 = this.jPoint;
                if(a7 != null)
                {
                    int i11 = a7.x;
                    int i12 = this.jWidth;
                    int i13 = i12 / 2;
                    int i14 = i11 + i13;
                    java.awt.FontMetrics a8 = a.getFontMetrics();
                    int i15 = a8.stringWidth("J");
                    int i16 = i15 / 2;
                    int i17 = i14 - i16;
                    int i18 = a7.y;
                    java.awt.FontMetrics a9 = a.getFontMetrics();
                    int i19 = a9.getHeight();
                    int i20 = 4 * i19;
                    int i21 = i18 + i20;
                    a.drawString("J", i17, i21);
                }
                StringBuilder a10 = new StringBuilder();
                String s = localization.Messages.getString("QuickSortCanvas.0");
                StringBuilder a11 = a10.append(s);
                QuickSort a12 = this.quickSort;
                int i22 = a12.getNumberOfSwaps();
                StringBuilder a13 = a11.append(i22);
                String s0 = localization.Messages.getString("QuickSortCanvas.6");
                StringBuilder a14 = a13.append(s0);
                QuickSort a15 = this.quickSort;
                int i23 = a15.getNumberOfComparisions();
                StringBuilder a16 = a14.append(i23);
                String s1 = a16.toString();
                int i24 = this.xBuffer;
                int i25 = this.yBuffer;
                int i26 = this.gapBetweenArrayAndQuickSort;
                int i27 = i25 + i26;
                java.awt.FontMetrics a17 = a.getFontMetrics();
                int i28 = a17.getHeight();
                int i29 = i27 - i28;
                int i30 = this.spacingBetweenInformationAndQuickSort;
                int i31 = i29 - i30;
                a.drawString(s1, i24, i31);
                java.awt.Color a18 = java.awt.Color.black;
                a.setColor(a18);
                java.awt.Point a19 = this.activeLeft;
                label2: {
                    if(a19 == null)
                    {
                        break label2;
                    }
                    java.awt.Point a20 = this.activeRight;
                    if(a20 == null)
                    {
                        break label2;
                    }
                    java.awt.Point a21 = this.activeRight;
                    int i32 = a21.x;
                    java.awt.Point a22 = this.activeLeft;
                    int i33 = a22.x;
                    if(i32 != i33)
                    {
                        java.awt.Point a23 = this.activeLeft;
                        int i34 = a23.x;
                        int i35 = this.activeYBuffer;
                        java.awt.Point a24 = this.activeRight;
                        int i36 = a24.x;
                        java.awt.Point a25 = this.activeLeft;
                        int i37 = a25.x;
                        int i38 = i36 - i37;
                        int i39 = this.yBuffer;
                        int i40 = i39 - 2;
                        a.drawRect(i34, i35, i38, i40);
                    }
                }
                int i41 = this.xBuffer;
                MyElementArrayQS a26 = this.elementArray;
                int i42 = a26.getWidth();
                int i43 = i41 + i42;
                MyElementArrayQS a27 = this.elementArray;
                int i44 = a27.getElementWidth();
                int i45 = i43 + i44;
                MyElementArrayQS a28 = this.elementArray;
                int i46 = a28.getColumGap();
                int i47 = i45 + i46;
                int i48 = this.yBuffer;
                int i49 = this.gapBetweenArrayAndQuickSort;
                int i50 = i48 + i49;
                java.awt.FontMetrics a29 = a.getFontMetrics();
                int i51 = a29.getHeight();
                int i52 = i50 - i51;
                java.awt.Point a30 = new java.awt.Point(i47, i52);
                int i53 = a30.x;
                int i54 = a30.y;
                int i55 = this.boxWidth;
                int i56 = this.boxWidth;
                a.drawRect(i53, i54, i55, i56);
                java.awt.Color a31 = this.textColor;
                a.setColor(a31);
                int i57 = a30.x;
                int i58 = this.boxWidth;
                int i59 = i58 / 2;
                int i60 = i57 + i59;
                java.awt.FontMetrics a32 = a.getFontMetrics();
                int i61 = a32.stringWidth("P");
                int i62 = i61 / 2;
                int i63 = i60 - i62;
                int i64 = a30.y;
                a.drawString("P", i63, i64);
                int i65 = this.pValueReady?1:0;
                if(i65 != 0)
                {
                    StringBuilder a33 = new StringBuilder();
                    StringBuilder a34 = a33.append("");
                    int i66 = this.pValue;
                    StringBuilder a35 = a34.append(i66);
                    String s2 = a35.toString();
                    int i67 = a30.x;
                    int i68 = this.boxWidth;
                    int i69 = i68 / 2;
                    int i70 = i67 + i69;
                    java.awt.FontMetrics a36 = a.getFontMetrics();
                    StringBuilder a37 = new StringBuilder();
                    StringBuilder a38 = a37.append("");
                    int i71 = this.pValue;
                    StringBuilder a39 = a38.append(i71);
                    String s3 = a39.toString();
                    int i72 = a36.stringWidth(s3);
                    int i73 = i72 / 2;
                    int i74 = i70 - i73;
                    int i75 = a30.y;
                    int i76 = this.boxWidth;
                    int i77 = i76 / 2;
                    int i78 = i75 + i77;
                    java.awt.FontMetrics a40 = a.getFontMetrics();
                    int i79 = a40.getHeight();
                    int i80 = i79 / 2;
                    int i81 = i78 + i80;
                    a.drawString(s2, i74, i81);
                }
                QuickSort a41 = this.quickSort;
                int i82 = this.xBuffer;
                int i83 = this.yBuffer;
                int i84 = this.gapBetweenArrayAndQuickSort;
                int i85 = i83 + i84;
                java.awt.Point a42 = new java.awt.Point(i82, i85);
                a41.draw(a, a42);
            }
            MyElementArrayQS a43 = this.elementArray;
            a43.draw(a);
        }
    }
    
    protected void handleColorSelection(com.cim.AIA.ColorSelection a)
    {
        String s = a.getAtributeName();
        String s0 = com.cim.AIA.ColorSelection.TEXT_COLOR;
        int i = s.equalsIgnoreCase(s0)?1:0;
        if(i != 0)
        {
            java.awt.Color a0 = a.getColor();
            this.textColor = a0;
        }
    }
    
    protected void handleFontSelection(com.cim.AIA.FontSelection a)
    {
    }
    
    protected void layoutAlgorithm(java.awt.Graphics a)
    {
        int i = this.tweening?1:0;
        if(i == 0)
        {
            QuickSort a0 = this.quickSort;
            int i0 = a0.getWidth(a);
            MyElementArrayQS a1 = this.elementArray;
            int i1 = a1.getElementWidth();
            int i2 = i0 + i1;
            MyElementArrayQS a2 = this.elementArray;
            int i3 = a2.getColumGap();
            int i4 = i2 + i3;
            int i5 = this.boxWidth;
            int i6 = i4 + i5;
            int i7 = this.yBuffer;
            int i8 = this.gapBetweenArrayAndQuickSort;
            int i9 = i7 + i8;
            QuickSort a3 = this.quickSort;
            int i10 = a3.getHeight(a);
            int i11 = i9 + i10;
            this.setSize(i6, i11);
            java.awt.Dimension a4 = this.getSize();
            int i12 = a4.width;
            int i13 = i12 / 2;
            QuickSort a5 = this.quickSort;
            int i14 = a5.getWidth(a);
            int i15 = i14 / 2;
            int i16 = i13 - i15;
            this.xBuffer = i16;
            MyElementArrayQS a6 = this.elementArray;
            int i17 = this.xBuffer;
            int i18 = this.yBuffer;
            a6.setLocation(i17, i18);
        }
        this.pValue = -1;
        this.pValueReady = false;
        QuickSort a7 = this.quickSort;
        QuickSort a8 = a7.getActive();
        label0: {
            if(a8 == null)
            {
                break label0;
            }
            int i19 = a8.getPValue();
            if(i19 != -10)
            {
                this.pValueReady = true;
                int i20 = a8.getPValue();
                this.pValue = i20;
            }
        }
    }
    
    public void processRepaintEvent(com.cim.AIA.RepaintEvent a)
    {
        Object a0 = a.paramObj;
        if(a0 != null)
        {
            Object a1 = a.paramObj;
            QuickSort dummy = (QuickSort)a1;
            QuickSort a2 = (QuickSort)a1;
            this.quickSort = a2;
            this.removeAllSelectables();
            QuickSort a3 = this.quickSort;
            int i = this.xBuffer;
            int i0 = this.yBuffer;
            java.awt.Point a4 = new java.awt.Point(i, i0);
            MyElementArrayQS a5 = a3.getElementArray(a4);
            this.elementArray = a5;
            MyElementArrayQS a6 = this.elementArray;
            this.addSelectable((com.cim.AIA.Selectable)a6);
            QuickSort a7 = this.quickSort;
            MyElementArrayQS a8 = this.elementArray;
            int i1 = this.numberOfTweenSteps;
            com.cim.AIA.MultipleTween a9 = a7.generateTweens((com.cim.AIA.Paintable)this, (Object)a8, i1);
            this.addTween((com.cim.AIA.Tween)a9);
            QuickSort a10 = this.quickSort;
            a10.removeAllAnimationRequests();
            this.calculateMarkerPosition();
            this.tweening = true;
            com.cim.AIA.MultipleTween a11 = this.tweens;
            a11.run();
            this.tweening = false;
        }
        this.repaint();
    }
}