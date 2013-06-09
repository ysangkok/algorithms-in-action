public class RadixExchangeSortCanvas extends com.cim.AIA.AlgorithmCanvas {
    final private static long serialVersionUID = 3757319251057068421L;
    protected int xBuffer;
    protected int yBuffer;
    protected int gapBetweenArrayAndRadix;
    protected java.awt.Color textColor;
    protected RadixExchangeSort radixExchangeSort;
    protected com.cim.AIA.ElementArray elementArray;
    protected boolean tweening;
    protected com.cim.AIA.StringMarker iMarker;
    protected com.cim.AIA.StringMarker jMarker;
    
    public RadixExchangeSortCanvas()
    {
        super();
        this.xBuffer = 60;
        this.yBuffer = 150;
        this.gapBetweenArrayAndRadix = 70;
        java.awt.Color a = java.awt.Color.black;
        this.textColor = a;
        this.tweening = false;
    }
    
    public void displayAlgorithm(java.awt.Graphics a)
    {
        RadixExchangeSort a0 = this.radixExchangeSort;
        label0: {
            if(a0 == null)
            {
                break label0;
            }
            com.cim.AIA.ElementArray a1 = this.elementArray;
            if(a1 == null)
            {
                break label0;
            }
            this.layoutAlgorithm(a);
            com.cim.AIA.StringMarker a2 = this.iMarker;
            if(a2 != null)
            {
                com.cim.AIA.StringMarker a3 = this.iMarker;
                a3.draw(a);
            }
            com.cim.AIA.StringMarker a4 = this.jMarker;
            if(a4 != null)
            {
                com.cim.AIA.StringMarker a5 = this.jMarker;
                a5.draw(a);
            }
            RadixExchangeSort a6 = this.radixExchangeSort;
            int i = this.xBuffer;
            int i0 = this.yBuffer;
            int i1 = this.gapBetweenArrayAndRadix;
            int i2 = i0 + i1;
            java.awt.Point a7 = new java.awt.Point(i, i2);
            a6.draw(a, a7);
            com.cim.AIA.ElementArray a8 = this.elementArray;
            a8.draw(a);
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
            int i0 = this.xBuffer;
            RadixExchangeSort a0 = this.radixExchangeSort;
            int i1 = a0.getWidth(a);
            int i2 = i0 + i1;
            int i3 = this.yBuffer;
            int i4 = this.gapBetweenArrayAndRadix;
            int i5 = i3 + i4;
            RadixExchangeSort a1 = this.radixExchangeSort;
            int i6 = a1.getHeight(a);
            int i7 = i5 + i6;
            this.setSize(i2, i7);
            java.awt.Dimension a2 = this.getSize();
            int i8 = a2.width;
            int i9 = i8 / 2;
            RadixExchangeSort a3 = this.radixExchangeSort;
            int i10 = a3.getWidth(a);
            int i11 = i10 / 2;
            int i12 = i9 - i11;
            this.xBuffer = i12;
            com.cim.AIA.ElementArray a4 = this.elementArray;
            RadixExchangeSort a5 = this.radixExchangeSort;
            int i13 = a5.getArrayBuffer(a);
            int i14 = this.xBuffer;
            int i15 = i13 + i14;
            int i16 = this.yBuffer;
            a4.setLocation(i15, i16);
        }
    }
    
    public void processRepaintEvent(com.cim.AIA.RepaintEvent a)
    {
        Object a0 = a.paramObj;
        label0: {
            if(a0 == null)
            {
                break label0;
            }
            Object a1 = a.paramObj;
            RadixExchangeSort dummy = (RadixExchangeSort)a1;
            RadixExchangeSort a2 = (RadixExchangeSort)a1;
            this.radixExchangeSort = a2;
            java.awt.Graphics a3 = this.getGraphics();
            int i = this.xBuffer;
            int i0 = this.yBuffer;
            java.awt.Point a4 = new java.awt.Point(i, i0);
            if(a3 != null)
            {
                int i1 = a4.x;
                RadixExchangeSort a5 = this.radixExchangeSort;
                int i2 = a5.getArrayBuffer(a3);
                int i3 = i1 + i2;
                a4.x = i3;
            }
            RadixExchangeSort a6 = this.radixExchangeSort;
            com.cim.AIA.ElementArray a7 = a6.getElementArray(a4);
            this.elementArray = a7;
            RadixExchangeSort a8 = this.radixExchangeSort;
            com.cim.AIA.ElementArray a9 = this.elementArray;
            int i4 = this.numberOfTweenSteps;
            com.cim.AIA.MultipleTween a10 = a8.generateTweens((com.cim.AIA.Paintable)this, (Object)a9, i4);
            this.addTween((com.cim.AIA.Tween)a10);
            RadixExchangeSort a11 = this.radixExchangeSort;
            a11.removeAllAnimationRequests();
            this.jMarker = null;
            this.iMarker = null;
            RadixExchangeSort a12 = this.radixExchangeSort;
            RadixExchangeSort a13 = a12.getActive();
            label1: {
                if(a13 == null)
                {
                    break label1;
                }
                if(a3 == null)
                {
                    break label1;
                }
                int i5 = a13.getI();
                label2: {
                    if(i5 == -10)
                    {
                        break label2;
                    }
                    com.cim.AIA.ElementArray a14 = this.elementArray;
                    int i6 = a13.getI();
                    com.cim.AIA.Element a15 = a14.getElement(i6);
                    if(a15 != null)
                    {
                        java.awt.Point a16 = a15.getPosition();
                        int i7 = a16.x;
                        int i8 = a15.getWidth();
                        int i9 = i8 / 2;
                        int i10 = i7 + i9;
                        int i11 = a16.y;
                        java.awt.FontMetrics a17 = a3.getFontMetrics();
                        int i12 = a17.getHeight();
                        int i13 = 3 * i12;
                        int i14 = i11 + i13;
                        java.awt.Point a18 = new java.awt.Point(i10, i14);
                        com.cim.AIA.StringMarker a19 = new com.cim.AIA.StringMarker("i", a18);
                        this.iMarker = a19;
                        com.cim.AIA.StringMarker a20 = this.iMarker;
                        java.awt.Color a21 = this.getBackground();
                        a20.setBackgroundColor(a21);
                        com.cim.AIA.StringMarker a22 = this.iMarker;
                        java.awt.Color a23 = this.textColor;
                        a22.setColor(a23);
                    }
                }
                int i15 = a13.getJ();
                if(i15 == -10)
                {
                    break label1;
                }
                com.cim.AIA.ElementArray a24 = this.elementArray;
                int i16 = a13.getJ();
                com.cim.AIA.Element a25 = a24.getElement(i16);
                if(a25 != null)
                {
                    java.awt.Point a26 = a25.getPosition();
                    int i17 = a26.x;
                    int i18 = a25.getWidth();
                    int i19 = i18 / 2;
                    int i20 = i17 + i19;
                    int i21 = a26.y;
                    java.awt.FontMetrics a27 = a3.getFontMetrics();
                    int i22 = a27.getHeight();
                    int i23 = 4 * i22;
                    int i24 = i21 + i23;
                    java.awt.Point a28 = new java.awt.Point(i20, i24);
                    com.cim.AIA.StringMarker a29 = new com.cim.AIA.StringMarker("J", a28);
                    this.jMarker = a29;
                    com.cim.AIA.StringMarker a30 = this.jMarker;
                    java.awt.Color a31 = this.getBackground();
                    a30.setBackgroundColor(a31);
                    com.cim.AIA.StringMarker a32 = this.jMarker;
                    java.awt.Color a33 = this.textColor;
                    a32.setColor(a33);
                }
            }
            this.tweening = true;
            com.cim.AIA.MultipleTween a34 = this.tweens;
            a34.run();
            this.tweening = false;
        }
        this.repaint();
    }
}