public class InsertionSortCanvas extends com.cim.AIA.AlgorithmCanvas {
    final private static long serialVersionUID = -2960790327721856295L;
    final protected static String I_MARKER = "i";
    final protected static String J_MARKER = "J";
    final protected static String VAL_MARKER;
    final protected static String FINAL_MARKER;
    final protected static String ELEMENT_MARKER = "A[j-1]";
    protected int xBuffer;
    protected int yBuffer;
    protected int boxWidth;
    protected InsertionSort insertionSort;
    protected com.cim.AIA.ElementArray elementArray;
    protected com.cim.AIA.StringMarker iMarker;
    protected com.cim.AIA.Line finalLine;
    protected com.cim.AIA.Line iLine;
    protected com.cim.AIA.Line elementLine;
    protected com.cim.AIA.StringMarker jMarker;
    protected int value;
    protected java.awt.Color backgroundColor;
    protected java.awt.Color textColor;
    protected boolean needInitialisation;
    protected boolean tweening;
    
    public InsertionSortCanvas()
    {
        super();
        this.xBuffer = 80;
        this.yBuffer = 180;
        this.boxWidth = 20;
        this.value = -1;
        java.awt.Color a = java.awt.Color.white;
        this.backgroundColor = a;
        java.awt.Color a0 = java.awt.Color.black;
        this.textColor = a0;
        this.needInitialisation = false;
        this.tweening = false;
    }
    
    public void displayAlgorithm(java.awt.Graphics a)
    {
        int i = this.needInitialisation?1:0;
        label1: {
            label0: {
                if(i == 0)
                {
                    break label0;
                }
                InsertionSort a0 = this.insertionSort;
                com.cim.AIA.RepaintEvent a1 = new com.cim.AIA.RepaintEvent((Object)this, (Object)a0);
                this.processRepaintEvent(a1);
                this.needInitialisation = false;
                break label1;
            }
            InsertionSort a2 = this.insertionSort;
            label2: {
                if(a2 == null)
                {
                    break label2;
                }
                com.cim.AIA.ElementArray a3 = this.elementArray;
                a3.draw(a);
                com.cim.AIA.StringMarker a4 = this.iMarker;
                if(a4 != null)
                {
                    com.cim.AIA.StringMarker a5 = this.iMarker;
                    a5.draw(a);
                }
                com.cim.AIA.StringMarker a6 = this.jMarker;
                label3: {
                    if(a6 == null)
                    {
                        break label3;
                    }
                    InsertionSort a7 = this.insertionSort;
                    int i0 = a7.getDontPaintJMarker()?1:0;
                    if(i0 == 0)
                    {
                        com.cim.AIA.StringMarker a8 = this.jMarker;
                        a8.draw(a);
                    }
                }
                com.cim.AIA.Line a9 = this.iLine;
                if(a9 != null)
                {
                    com.cim.AIA.Line a10 = this.iLine;
                    a10.draw(a);
                }
                com.cim.AIA.Line a11 = this.finalLine;
                if(a11 != null)
                {
                    com.cim.AIA.Line a12 = this.finalLine;
                    a12.draw(a);
                }
                com.cim.AIA.Line a13 = this.elementLine;
                if(a13 != null)
                {
                    com.cim.AIA.Line a14 = this.elementLine;
                    a14.draw(a);
                }
                com.cim.AIA.ElementArray a15 = this.elementArray;
                java.awt.Point a16 = a15.getLocation();
                int i1 = a16.x;
                com.cim.AIA.ElementArray a17 = this.elementArray;
                int i2 = a17.getWidth();
                int i3 = i1 + i2;
                com.cim.AIA.ElementArray a18 = this.elementArray;
                int i4 = a18.getElementWidth();
                int i5 = i3 + i4;
                com.cim.AIA.ElementArray a19 = this.elementArray;
                int i6 = a19.getColumGap();
                int i7 = i5 + i6;
                int i8 = this.yBuffer;
                java.awt.FontMetrics a20 = a.getFontMetrics();
                int i9 = a20.getHeight();
                int i10 = i8 + i9;
                java.awt.Point a21 = new java.awt.Point(i7, i10);
                int i11 = a21.x;
                int i12 = a21.y;
                int i13 = this.boxWidth;
                int i14 = this.boxWidth;
                a.drawRect(i11, i12, i13, i14);
                java.awt.Color a22 = this.textColor;
                a.setColor(a22);
                String s = localization.Messages.getString("InsertionSortCanvas.2");
                int i15 = a21.x;
                int i16 = this.boxWidth;
                int i17 = i16 / 2;
                int i18 = i15 + i17;
                java.awt.FontMetrics a23 = a.getFontMetrics();
                String s0 = localization.Messages.getString("InsertionSortCanvas.3");
                int i19 = a23.stringWidth(s0);
                int i20 = i19 / 2;
                int i21 = i18 - i20;
                int i22 = a21.y;
                a.drawString(s, i21, i22);
                InsertionSort a24 = this.insertionSort;
                int i23 = a24.getVal();
                if(i23 == -10)
                {
                    break label2;
                }
                java.awt.Color a25 = InsertionSort.activeArrayColor;
                a.setColor(a25);
                int i24 = a21.x;
                int i25 = a21.y;
                int i26 = this.boxWidth;
                int i27 = this.boxWidth;
                a.fillRect(i24, i25, i26, i27);
                java.awt.Color a26 = this.textColor;
                a.setColor(a26);
                InsertionSort a27 = this.insertionSort;
                int i28 = a27.getVal();
                this.value = i28;
                StringBuilder a28 = new StringBuilder();
                StringBuilder a29 = a28.append("");
                int i29 = this.value;
                StringBuilder a30 = a29.append(i29);
                String s1 = a30.toString();
                int i30 = a21.x;
                int i31 = this.boxWidth;
                int i32 = i31 / 2;
                int i33 = i30 + i32;
                java.awt.FontMetrics a31 = a.getFontMetrics();
                StringBuilder a32 = new StringBuilder();
                StringBuilder a33 = a32.append("");
                int i34 = this.value;
                StringBuilder a34 = a33.append(i34);
                String s2 = a34.toString();
                int i35 = a31.stringWidth(s2);
                int i36 = i35 / 2;
                int i37 = i33 - i36;
                int i38 = a21.y;
                int i39 = this.boxWidth;
                int i40 = i39 / 2;
                int i41 = i38 + i40;
                java.awt.FontMetrics a35 = a.getFontMetrics();
                int i42 = a35.getHeight();
                int i43 = i42 / 2;
                int i44 = i41 + i43;
                a.drawString(s1, i37, i44);
                InsertionSort a36 = this.insertionSort;
                int i45 = a36.getDontPaintValMarker()?1:0;
                if(i45 != 0)
                {
                    break label2;
                }
                com.cim.AIA.Line a37 = this.elementLine;
                if(a37 != null)
                {
                    int i46 = a21.x;
                    int i47 = this.boxWidth;
                    int i48 = i47 / 2;
                    int i49 = i46 + i48;
                    java.awt.Point a38 = new java.awt.Point(i49, 50);
                    int i50 = a21.x;
                    int i51 = this.boxWidth;
                    int i52 = i51 / 2;
                    int i53 = i50 + i52;
                    int i54 = a21.y;
                    int i55 = this.boxWidth;
                    int i56 = i54 - i55;
                    java.awt.Point a39 = new java.awt.Point(i53, i56);
                    com.cim.AIA.Line a40 = new com.cim.AIA.Line(a38, a39);
                    a40.showArrowHead(true);
                    a40.setDistanceFromStartForArrowHead(-3);
                    a40.draw(a);
                }
            }
        }
    }
    
    protected void handleColorSelection(com.cim.AIA.ColorSelection a)
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
            if(i0 != 0)
            {
                java.awt.Color a1 = a.getColor();
                this.textColor = a1;
            }
        }
    }
    
    protected void handleFontSelection(com.cim.AIA.FontSelection a)
    {
    }
    
    protected void initialise()
    {
        java.awt.Dimension a = this.getSize();
        com.cim.AIA.ElementArray a0 = this.elementArray;
        int i = a0.getWidth();
        int i0 = i + 50;
        a.width = i0;
        this.setSize(a);
        com.cim.AIA.ElementArray a1 = this.elementArray;
        java.awt.Dimension a2 = this.getSize();
        int i1 = a2.width;
        int i2 = i1 / 2;
        com.cim.AIA.ElementArray a3 = this.elementArray;
        int i3 = a3.getWidth();
        int i4 = i3 / 2;
        int i5 = i2 - i4;
        a1.setLocation(i5, 200);
        InsertionSort a4 = this.insertionSort;
        int i6 = a4.getI();
        com.cim.AIA.StringMarker a5 = this.initialiseMarker(i6, "i", 3);
        this.iMarker = a5;
        InsertionSort a6 = this.insertionSort;
        int i7 = a6.getJ();
        com.cim.AIA.StringMarker a7 = this.initialiseMarker(i7, "J", 4);
        this.jMarker = a7;
        InsertionSort a8 = this.insertionSort;
        int i8 = a8.getI();
        com.cim.AIA.Line a9 = this.initialiseLine(i8, "i");
        this.iLine = a9;
        InsertionSort a10 = this.insertionSort;
        int i9 = a10.getFinalPosition();
        String s = InsertionSortCanvas.FINAL_MARKER;
        com.cim.AIA.Line a11 = this.initialiseLine(i9, s);
        this.finalLine = a11;
        InsertionSort a12 = this.insertionSort;
        int i10 = a12.getElementPosition();
        com.cim.AIA.Line a13 = this.initialiseLine(i10, "A[j-1]");
        this.elementLine = a13;
    }
    
    protected com.cim.AIA.Line initialiseLine(int i, String s)
    {
        com.cim.AIA.Line a = null;
        label2: {
            com.cim.AIA.VerticalBar a0 = null;
            java.awt.Point a1 = null;
            java.awt.Point a2 = null;
            label1: {
                label0: {
                    if(i == -10)
                    {
                        break label0;
                    }
                    com.cim.AIA.ElementArray a3 = this.elementArray;
                    com.cim.AIA.Element a4 = a3.getElement(i);
                    com.cim.AIA.VerticalBar dummy = (com.cim.AIA.VerticalBar)a4;
                    a0 = (com.cim.AIA.VerticalBar)a4;
                    if(a0 != null)
                    {
                        break label1;
                    }
                }
                a = null;
                break label2;
            }
            if(s != "i")
            {
                java.awt.Point a5 = a0.getPosition();
                int i0 = a5.x;
                int i1 = a0.getWidth();
                int i2 = i1 / 2;
                int i3 = i0 + i2;
                java.awt.Point a6 = a0.getPosition();
                int i4 = a6.y;
                int i5 = a0.getHeight();
                int i6 = i4 - i5;
                java.awt.Point a7 = new java.awt.Point(i3, i6);
                java.awt.Point a8 = a0.getPosition();
                int i7 = a8.x;
                int i8 = a0.getWidth();
                int i9 = i8 / 2;
                int i10 = i7 + i9;
                java.awt.Point a9 = new java.awt.Point(i10, 50);
                a1 = a7;
                a2 = a9;
            }
            else
            {
                java.awt.Point a10 = a0.getPosition();
                int i11 = a10.x;
                int i12 = a0.getWidth();
                int i13 = i12 / 2;
                int i14 = i11 + i13;
                java.awt.Point a11 = a0.getPosition();
                int i15 = a11.y;
                int i16 = i15 + 20;
                java.awt.Point a12 = new java.awt.Point(i14, i16);
                java.awt.Point a13 = a0.getPosition();
                int i17 = a13.x;
                int i18 = a0.getWidth();
                int i19 = i18 / 2;
                int i20 = i17 + i19;
                java.awt.Point a14 = a0.getPosition();
                int i21 = a14.y;
                int i22 = i21 + 30;
                java.awt.Point a15 = new java.awt.Point(i20, i22);
                a1 = a12;
                a2 = a15;
            }
            com.cim.AIA.Line a16 = new com.cim.AIA.Line(a2, a1);
            a16.showArrowHead(true);
            a16.setDistanceFromStartForArrowHead(-3);
            if(s != "i")
            {
                a16.setLabel(s);
            }
            a16.setDistanceFromStartForLabel(-1);
            java.awt.Graphics a17 = this.getGraphics();
            java.awt.FontMetrics a18 = a17.getFontMetrics();
            int i23 = a18.stringWidth(s);
            int i24 = -1 * i23;
            int i25 = i24 / 2;
            a16.setXLabelOffset(i25);
            java.awt.Graphics a19 = this.getGraphics();
            java.awt.FontMetrics a20 = a19.getFontMetrics();
            int i26 = a20.getHeight();
            int i27 = -1 * i26;
            a16.setYLabelOffset(i27);
            a = a16;
        }
        return a;
    }
    
    protected com.cim.AIA.StringMarker initialiseMarker(int i, String s, int i0)
    {
        com.cim.AIA.StringMarker a = null;
        label2: {
            com.cim.AIA.VerticalBar a0 = null;
            label1: {
                label0: {
                    if(i == -10)
                    {
                        break label0;
                    }
                    com.cim.AIA.ElementArray a1 = this.elementArray;
                    com.cim.AIA.Element a2 = a1.getElement(i);
                    com.cim.AIA.VerticalBar dummy = (com.cim.AIA.VerticalBar)a2;
                    a0 = (com.cim.AIA.VerticalBar)a2;
                    if(a0 != null)
                    {
                        break label1;
                    }
                }
                a = null;
                break label2;
            }
            java.awt.Point a3 = a0.getPosition();
            int i1 = a3.x;
            int i2 = a0.getWidth();
            int i3 = i2 / 2;
            int i4 = i1 + i3;
            java.awt.Point a4 = a0.getPosition();
            int i5 = a4.y;
            int i6 = i5 + 5;
            java.awt.Graphics a5 = this.getGraphics();
            java.awt.FontMetrics a6 = a5.getFontMetrics();
            int i7 = a6.getHeight();
            int i8 = i0 * i7;
            int i9 = i6 + i8;
            java.awt.Point a7 = new java.awt.Point(i4, i9);
            com.cim.AIA.StringMarker a8 = new com.cim.AIA.StringMarker(s, a7);
            java.awt.Color a9 = this.textColor;
            a8.setColor(a9);
            java.awt.Color a10 = this.backgroundColor;
            a8.setBackgroundColor(a10);
            a = a8;
        }
        return a;
    }
    
    public void processRepaintEvent(com.cim.AIA.RepaintEvent a)
    {
        Object a0 = a.paramObj;
        label2: {
            label1: {
                label0: {
                    if(a0 == null)
                    {
                        break label0;
                    }
                    Object a1 = a.paramObj;
                    InsertionSort dummy = (InsertionSort)a1;
                    InsertionSort a2 = (InsertionSort)a1;
                    this.insertionSort = a2;
                    this.iMarker = null;
                    this.jMarker = null;
                    java.awt.Graphics a3 = this.getGraphics();
                    if(a3 == null)
                    {
                        break label1;
                    }
                    InsertionSort a4 = this.insertionSort;
                    java.awt.Graphics a5 = this.getGraphics();
                    com.cim.AIA.ElementArray a6 = a4.getElementArray(a5);
                    this.elementArray = a6;
                    this.initialise();
                    InsertionSort a7 = this.insertionSort;
                    com.cim.AIA.ElementArray a8 = this.elementArray;
                    int i = this.numberOfTweenSteps;
                    com.cim.AIA.MultipleTween a9 = a7.generateTweens((com.cim.AIA.Paintable)this, (Object)a8, i);
                    this.addTween((com.cim.AIA.Tween)a9);
                    this.tweening = true;
                    com.cim.AIA.MultipleTween a10 = this.tweens;
                    int i0 = a10.getNumberOfTweens();
                    if(i0 > 0)
                    {
                        com.cim.AIA.MultipleTween a11 = this.tweens;
                        a11.run();
                    }
                    this.tweening = false;
                    InsertionSort a12 = this.insertionSort;
                    a12.removeAllAnimationRequests();
                }
                this.repaint();
                break label2;
            }
            this.needInitialisation = true;
        }
    }
    
    static
    {
        String s = localization.Messages.getString("InsertionSortCanvas.0");
        InsertionSortCanvas.VAL_MARKER = s;
        String s0 = localization.Messages.getString("InsertionSortCanvas.1");
        InsertionSortCanvas.FINAL_MARKER = s0;
    }
}