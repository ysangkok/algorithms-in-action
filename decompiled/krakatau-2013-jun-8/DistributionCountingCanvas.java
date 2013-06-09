public class DistributionCountingCanvas extends com.cim.AIA.AlgorithmCanvas {
    final private static long serialVersionUID = -1002901147034945420L;
    protected com.cim.AIA.ElementArray dataElementArray;
    protected com.cim.AIA.ElementArray symbolElementArray;
    protected com.cim.AIA.ElementArray auxiliaryElementArray;
    protected int xBuffer;
    protected int yBuffer;
    protected int spacingBetweenArrays;
    protected String dataElementArrayLabel;
    protected String symbolElementArrayLabel;
    protected String auxiliaryElementArrayLabel;
    protected int xTextBuffer;
    protected int yTextBuffer;
    protected java.awt.Font normalFont;
    protected java.awt.Color textColor;
    protected DistributionCounting distributionCounting;
    
    public DistributionCountingCanvas()
    {
        super();
        this.xBuffer = 30;
        this.yBuffer = 20;
        this.spacingBetweenArrays = 100;
        String s = localization.Messages.getString("DistributionCountingCanvas.0");
        this.dataElementArrayLabel = s;
        String s0 = localization.Messages.getString("DistributionCountingCanvas.1");
        this.symbolElementArrayLabel = s0;
        String s1 = localization.Messages.getString("DistributionCountingCanvas.2");
        this.auxiliaryElementArrayLabel = s1;
        this.xTextBuffer = 10;
        this.yTextBuffer = 5;
    }
    
    public void displayAlgorithm(java.awt.Graphics a)
    {
        DistributionCounting a0 = this.distributionCounting;
        label0: {
            if(a0 == null)
            {
                break label0;
            }
            java.awt.Font a1 = this.normalFont;
            if(a1 != null)
            {
                java.awt.Font a2 = this.normalFont;
                a.setFont(a2);
            }
            java.awt.Color a3 = this.textColor;
            if(a3 != null)
            {
                java.awt.Color a4 = this.textColor;
                a.setColor(a4);
            }
            com.cim.AIA.ElementArray a5 = this.dataElementArray;
            if(a5 != null)
            {
                com.cim.AIA.ElementArray a6 = this.dataElementArray;
                java.awt.Point a7 = a6.getLocation();
                String s = this.dataElementArrayLabel;
                int i = a7.x;
                com.cim.AIA.ElementArray a8 = this.dataElementArray;
                int i0 = a8.getWidth();
                int i1 = i + i0;
                int i2 = this.xTextBuffer;
                int i3 = i1 + i2;
                int i4 = a7.y;
                com.cim.AIA.ElementArray a9 = this.dataElementArray;
                int i5 = a9.getHeight();
                int i6 = i5 / 2;
                int i7 = i4 + i6;
                java.awt.FontMetrics a10 = a.getFontMetrics();
                int i8 = a10.getHeight();
                int i9 = i8 / 3;
                int i10 = i7 + i9;
                a.drawString(s, i3, i10);
                com.cim.AIA.ElementArray a11 = this.dataElementArray;
                int i11 = a11.getWidth();
                int i12 = this.xBuffer;
                int i13 = i11 + i12;
                java.awt.FontMetrics a12 = a.getFontMetrics();
                String s0 = this.auxiliaryElementArrayLabel;
                int i14 = a12.stringWidth(s0);
                int i15 = i13 + i14;
                int i16 = this.xTextBuffer;
                int i17 = i15 + i16;
                java.awt.Dimension a13 = this.getSize();
                int i18 = a13.height;
                this.setSize(i17, i18);
                com.cim.AIA.ElementArray a14 = this.dataElementArray;
                a14.draw(a);
            }
            com.cim.AIA.ElementArray a15 = this.symbolElementArray;
            if(a15 != null)
            {
                com.cim.AIA.ElementArray a16 = this.symbolElementArray;
                java.awt.Point a17 = a16.getLocation();
                String s1 = this.symbolElementArrayLabel;
                int i19 = a17.x;
                com.cim.AIA.ElementArray a18 = this.symbolElementArray;
                int i20 = a18.getWidth();
                int i21 = i19 + i20;
                int i22 = this.xTextBuffer;
                int i23 = i21 + i22;
                int i24 = a17.y;
                com.cim.AIA.ElementArray a19 = this.symbolElementArray;
                int i25 = a19.getHeight();
                int i26 = i25 / 2;
                int i27 = i24 + i26;
                java.awt.FontMetrics a20 = a.getFontMetrics();
                int i28 = a20.getHeight();
                int i29 = i28 / 3;
                int i30 = i27 + i29;
                a.drawString(s1, i23, i30);
                com.cim.AIA.ElementArray a21 = this.symbolElementArray;
                a21.draw(a);
            }
            com.cim.AIA.ElementArray a22 = this.auxiliaryElementArray;
            if(a22 != null)
            {
                com.cim.AIA.ElementArray a23 = this.auxiliaryElementArray;
                java.awt.Point a24 = a23.getLocation();
                String s2 = this.auxiliaryElementArrayLabel;
                int i31 = a24.x;
                com.cim.AIA.ElementArray a25 = this.auxiliaryElementArray;
                int i32 = a25.getWidth();
                int i33 = i31 + i32;
                int i34 = this.xTextBuffer;
                int i35 = i33 + i34;
                int i36 = a24.y;
                com.cim.AIA.ElementArray a26 = this.auxiliaryElementArray;
                int i37 = a26.getHeight();
                int i38 = i37 / 2;
                int i39 = i36 + i38;
                java.awt.FontMetrics a27 = a.getFontMetrics();
                int i40 = a27.getHeight();
                int i41 = i40 / 3;
                int i42 = i39 + i41;
                a.drawString(s2, i35, i42);
                com.cim.AIA.ElementArray a28 = this.auxiliaryElementArray;
                a28.draw(a);
            }
            DistributionCounting a29 = this.distributionCounting;
            int i43 = a29.getDataArrow();
            if(i43 == -10)
            {
                break label0;
            }
            com.cim.AIA.ElementArray a30 = this.dataElementArray;
            com.cim.AIA.Element a31 = a30.getElement(i43);
            if(a31 != null)
            {
                java.awt.Point a32 = a31.getPosition();
                int i44 = a32.x;
                int i45 = a31.getWidth();
                int i46 = i45 / 2;
                int i47 = i44 + i46;
                int i48 = a32.y;
                int i49 = i48 - 20;
                int i50 = a32.x;
                int i51 = a31.getWidth();
                int i52 = i51 / 2;
                int i53 = i50 + i52;
                int i54 = a32.y;
                com.cim.AIA.Line a33 = new com.cim.AIA.Line(i47, i49, i53, i54);
                a33.showArrowHead(true);
                a33.setDistanceFromStartForArrowHead(-3);
                a33.draw(a);
            }
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
        String s = a.getAtributeName();
        int i = s.equalsIgnoreCase("Normal Font")?1:0;
        if(i != 0)
        {
            java.awt.Font a0 = a.getFont();
            this.normalFont = a0;
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
            java.util.Vector a1 = this.drawables;
            a1.removeAllElements();
            Object a2 = a.paramObj;
            DistributionCounting dummy = (DistributionCounting)a2;
            DistributionCounting a3 = (DistributionCounting)a2;
            this.distributionCounting = a3;
            DistributionCounting a4 = this.distributionCounting;
            com.cim.AIA.ElementArray a5 = a4.getDataElementArray();
            this.dataElementArray = a5;
            com.cim.AIA.ElementArray a6 = this.dataElementArray;
            if(a6 != null)
            {
                com.cim.AIA.ElementArray a7 = this.dataElementArray;
                int i = this.xBuffer;
                int i0 = this.yBuffer;
                a7.setLocation(i, i0);
            }
            DistributionCounting a8 = this.distributionCounting;
            com.cim.AIA.ElementArray a9 = a8.getAuxiliaryElementArray();
            this.auxiliaryElementArray = a9;
            com.cim.AIA.ElementArray a10 = this.auxiliaryElementArray;
            if(a10 != null)
            {
                com.cim.AIA.ElementArray a11 = this.auxiliaryElementArray;
                int i1 = this.xBuffer;
                int i2 = this.yBuffer;
                int i3 = this.spacingBetweenArrays;
                int i4 = 2 * i3;
                int i5 = i2 + i4;
                a11.setLocation(i1, i5);
            }
            DistributionCounting a12 = this.distributionCounting;
            com.cim.AIA.ElementArray a13 = a12.getSymbolElementArray();
            this.symbolElementArray = a13;
            com.cim.AIA.ElementArray a14 = this.symbolElementArray;
            label1: {
                if(a14 == null)
                {
                    break label1;
                }
                com.cim.AIA.ElementArray a15 = this.dataElementArray;
                if(a15 != null)
                {
                    com.cim.AIA.ElementArray a16 = this.symbolElementArray;
                    int i6 = this.xBuffer;
                    com.cim.AIA.ElementArray a17 = this.dataElementArray;
                    int i7 = a17.getWidth();
                    int i8 = i7 / 2;
                    com.cim.AIA.ElementArray a18 = this.symbolElementArray;
                    int i9 = a18.getWidth();
                    int i10 = i9 / 2;
                    int i11 = i8 - i10;
                    int i12 = i6 + i11;
                    int i13 = this.yBuffer;
                    int i14 = this.spacingBetweenArrays;
                    int i15 = i13 + i14;
                    a16.setLocation(i12, i15);
                }
            }
            com.cim.AIA.ElementArray a19 = this.symbolElementArray;
            label2: {
                if(a19 == null)
                {
                    break label2;
                }
                com.cim.AIA.ElementArray a20 = this.auxiliaryElementArray;
                if(a20 != null)
                {
                    DistributionCounting a21 = this.distributionCounting;
                    com.cim.AIA.ElementArray a22 = this.symbolElementArray;
                    com.cim.AIA.ElementArray a23 = this.auxiliaryElementArray;
                    a21.calculateLines((com.cim.AIA.Paintable)this, a22, a23);
                }
            }
            this.repaint();
        }
    }
}