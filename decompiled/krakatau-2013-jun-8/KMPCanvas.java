public class KMPCanvas extends com.cim.AIA.AlgorithmCanvas {
    final private static long serialVersionUID = 496615175370539607L;
    protected KMPString theString1;
    protected KMPString theString2;
    protected KMPString testKMPString1;
    protected KMPString testKMPString2;
    protected KMPNextTable theNextTable;
    protected com.cim.AIA.Node jPointer;
    protected com.cim.AIA.Node iPointer;
    protected com.cim.AIA.Node animNode;
    protected int kmpResult;
    protected int xBuffer;
    protected int yBuffer;
    protected int windowHeight;
    protected java.awt.Font normalFont;
    protected java.awt.Color textColor;
    protected KMP theKMP;
    
    public KMPCanvas()
    {
        super();
        this.xBuffer = 100;
        this.yBuffer = 10;
    }
    
    public void displayAlgorithm(java.awt.Graphics a)
    {
        KMP a0 = this.theKMP;
        label1: {
            label0: {
                if(a0 != null)
                {
                    break label0;
                }
                break label1;
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
            KMPString a5 = this.theString1;
            if(a5 != null)
            {
                String s = localization.Messages.getString("KMPCanvas.0");
                int i = this.xBuffer;
                java.awt.FontMetrics a6 = a.getFontMetrics();
                int i0 = a6.stringWidth(s);
                int i1 = i - i0;
                int i2 = this.yBuffer;
                int i3 = i2 + 30;
                a.drawString(s, i1, i3);
                KMPString a7 = this.theString1;
                a7.draw(a);
            }
            KMPString a8 = this.theString2;
            if(a8 != null)
            {
                String s0 = localization.Messages.getString("KMPCanvas.1");
                int i4 = this.xBuffer;
                java.awt.FontMetrics a9 = a.getFontMetrics();
                int i5 = a9.stringWidth(s0);
                int i6 = i4 - i5;
                int i7 = this.yBuffer;
                int i8 = i7 + 50;
                a.drawString(s0, i6, i8);
                KMPString a10 = this.theString2;
                a10.draw(a);
            }
            KMPString a11 = this.testKMPString1;
            if(a11 != null)
            {
                String s1 = localization.Messages.getString("KMPCanvas.2");
                int i9 = this.xBuffer;
                java.awt.FontMetrics a12 = a.getFontMetrics();
                int i10 = a12.stringWidth(s1);
                int i11 = i9 - i10;
                int i12 = this.yBuffer;
                int i13 = i12 + 90;
                a.drawString(s1, i11, i13);
                KMPString a13 = this.testKMPString1;
                a13.draw(a);
            }
            KMPString a14 = this.testKMPString2;
            if(a14 != null)
            {
                String s2 = localization.Messages.getString("KMPCanvas.3");
                int i14 = this.xBuffer;
                java.awt.FontMetrics a15 = a.getFontMetrics();
                int i15 = a15.stringWidth(s2);
                int i16 = i14 - i15;
                int i17 = this.yBuffer;
                int i18 = i17 + 110;
                a.drawString(s2, i16, i18);
                KMPString a16 = this.testKMPString2;
                a16.draw(a);
            }
            KMPNextTable a17 = this.theNextTable;
            if(a17 != null)
            {
                String s3 = localization.Messages.getString("KMPCanvas.4");
                int i19 = this.xBuffer;
                java.awt.FontMetrics a18 = a.getFontMetrics();
                int i20 = a18.stringWidth(s3);
                int i21 = i19 - i20;
                KMPNextTable a19 = this.theNextTable;
                int i22 = a19.getY();
                a.drawString(s3, i21, i22);
                KMPNextTable a20 = this.theNextTable;
                a20.draw(a);
            }
            com.cim.AIA.Node a21 = this.jPointer;
            if(a21 != null)
            {
                int i23 = this.xBuffer;
                java.awt.FontMetrics a22 = a.getFontMetrics();
                int i24 = a22.stringWidth("j = ");
                int i25 = i23 - i24;
                com.cim.AIA.Node a23 = this.jPointer;
                int i26 = a23.getY();
                java.awt.FontMetrics a24 = a.getFontMetrics();
                int i27 = a24.getHeight();
                int i28 = i26 + i27;
                a.drawString("j = ", i25, i28);
                com.cim.AIA.Node a25 = this.jPointer;
                a25.draw(a);
            }
            com.cim.AIA.Node a26 = this.iPointer;
            if(a26 != null)
            {
                int i29 = this.xBuffer;
                java.awt.FontMetrics a27 = a.getFontMetrics();
                int i30 = a27.stringWidth("i = ");
                int i31 = i29 - i30;
                com.cim.AIA.Node a28 = this.iPointer;
                int i32 = a28.getY();
                java.awt.FontMetrics a29 = a.getFontMetrics();
                int i33 = a29.getHeight();
                int i34 = i32 + i33;
                a.drawString("i = ", i31, i34);
                com.cim.AIA.Node a30 = this.iPointer;
                a30.draw(a);
            }
            com.cim.AIA.Node a31 = this.animNode;
            if(a31 != null)
            {
                com.cim.AIA.Node a32 = this.animNode;
                a32.draw(a);
            }
            int i35 = this.kmpResult;
            if(i35 == 0)
            {
                java.awt.Color a33 = a.getColor();
                java.awt.Color a34 = java.awt.Color.red;
                a.setColor(a34);
                String s4 = localization.Messages.getString("KMPCanvas.7");
                int i36 = this.xBuffer;
                a.drawString(s4, i36, 90);
                a.setColor(a33);
            }
            int i37 = this.kmpResult;
            if(i37 == 1)
            {
                java.awt.Color a35 = a.getColor();
                java.awt.Color a36 = java.awt.Color.green;
                a.setColor(a36);
                String s5 = localization.Messages.getString("KMPCanvas.8");
                int i38 = this.xBuffer;
                a.drawString(s5, i38, 90);
                a.setColor(a35);
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
    }
    
    public void processRepaintEvent(com.cim.AIA.RepaintEvent a)
    {
        Object a0 = a.paramObj;
        label1: {
            label0: {
                if(a0 != null)
                {
                    break label0;
                }
                break label1;
            }
            java.util.Vector a1 = this.drawables;
            a1.removeAllElements();
            Object a2 = a.paramObj;
            KMP dummy = (KMP)a2;
            KMP a3 = (KMP)a2;
            this.theKMP = a3;
            KMP a4 = this.theKMP;
            KMPString a5 = a4.getKMPString1();
            this.theString1 = a5;
            KMPString a6 = this.theString1;
            if(a6 != null)
            {
                KMPString a7 = this.theString1;
                int i = this.xBuffer;
                int i0 = this.yBuffer;
                int i1 = i0 + 30;
                a7.setPosition(i, i1);
            }
            KMP a8 = this.theKMP;
            KMPString a9 = a8.getKMPString2();
            this.theString2 = a9;
            KMPString a10 = this.theString2;
            if(a10 != null)
            {
                KMPString a11 = this.theString2;
                int i2 = this.xBuffer;
                int i3 = this.yBuffer;
                int i4 = i3 + 50;
                a11.setPosition(i2, i4);
            }
            KMP a12 = this.theKMP;
            KMPString a13 = a12.getTestKMPString1();
            this.testKMPString1 = a13;
            KMPString a14 = this.testKMPString1;
            if(a14 != null)
            {
                KMPString a15 = this.testKMPString1;
                int i5 = this.xBuffer;
                int i6 = this.yBuffer;
                int i7 = i6 + 90;
                a15.setPosition(i5, i7);
            }
            KMP a16 = this.theKMP;
            KMPString a17 = a16.getTestKMPString2();
            this.testKMPString2 = a17;
            KMPString a18 = this.testKMPString2;
            if(a18 != null)
            {
                KMPString a19 = this.testKMPString2;
                int i8 = this.xBuffer;
                int i9 = this.yBuffer;
                int i10 = i9 + 110;
                a19.setPosition(i8, i10);
            }
            KMP a20 = this.theKMP;
            int i11 = a20.getKMPResult();
            this.kmpResult = i11;
            KMP a21 = this.theKMP;
            com.cim.AIA.Node a22 = a21.getJPointer();
            this.jPointer = a22;
            com.cim.AIA.Node a23 = this.jPointer;
            label2: {
                if(a23 == null)
                {
                    break label2;
                }
                KMPString a24 = this.testKMPString2;
                if(a24 != null)
                {
                    com.cim.AIA.Node a25 = this.jPointer;
                    int i12 = this.xBuffer;
                    int i13 = this.yBuffer;
                    int i14 = i13 + 160;
                    java.awt.Point a26 = new java.awt.Point(i12, i14);
                    a25.setPosition(a26);
                }
                else
                {
                    com.cim.AIA.Node a27 = this.jPointer;
                    int i15 = this.xBuffer;
                    int i16 = this.yBuffer;
                    int i17 = i16 + 90;
                    java.awt.Point a28 = new java.awt.Point(i15, i17);
                    a27.setPosition(a28);
                }
            }
            KMP a29 = this.theKMP;
            com.cim.AIA.Node a30 = a29.getIPointer();
            this.iPointer = a30;
            com.cim.AIA.Node a31 = this.iPointer;
            label3: {
                if(a31 == null)
                {
                    break label3;
                }
                KMPString a32 = this.testKMPString2;
                if(a32 != null)
                {
                    com.cim.AIA.Node a33 = this.iPointer;
                    int i18 = this.xBuffer;
                    int i19 = this.yBuffer;
                    int i20 = i19 + 130;
                    java.awt.Point a34 = new java.awt.Point(i18, i20);
                    a33.setPosition(a34);
                }
                else
                {
                    com.cim.AIA.Node a35 = this.iPointer;
                    int i21 = this.xBuffer;
                    int i22 = this.yBuffer;
                    int i23 = i22 + 110;
                    java.awt.Point a36 = new java.awt.Point(i21, i23);
                    a35.setPosition(a36);
                }
            }
            KMP a37 = this.theKMP;
            KMPNextTable a38 = a37.getNextTable();
            this.theNextTable = a38;
            KMPNextTable a39 = this.theNextTable;
            label4: {
                if(a39 == null)
                {
                    break label4;
                }
                KMPString a40 = this.testKMPString2;
                label5: {
                    if(a40 == null)
                    {
                        break label5;
                    }
                    com.cim.AIA.Node a41 = this.iPointer;
                    if(a41 == null)
                    {
                        break label5;
                    }
                    KMPNextTable a42 = this.theNextTable;
                    int i24 = this.xBuffer;
                    int i25 = this.yBuffer;
                    int i26 = i25 + 200;
                    a42.setPosition(i24, i26);
                    break label4;
                }
                KMPNextTable a43 = this.theNextTable;
                int i27 = this.xBuffer;
                int i28 = this.yBuffer;
                int i29 = i28 + 140;
                a43.setPosition(i27, i29);
                int i30 = this.yBuffer;
                int i31 = i30 + 160;
                KMPNextTable a44 = this.theNextTable;
                int i32 = a44.getHeight();
                int i33 = i31 + i32;
                this.windowHeight = i33;
            }
            KMP a45 = this.theKMP;
            com.cim.AIA.Node a46 = a45.getAnimNode();
            this.animNode = a46;
            KMP a47 = this.theKMP;
            int i34 = this.numberOfTweenSteps;
            com.cim.AIA.MultipleTween a48 = a47.generateTweens((com.cim.AIA.Paintable)this, (Object)null, i34);
            this.addTween((com.cim.AIA.Tween)a48);
            com.cim.AIA.MultipleTween a49 = this.tweens;
            int i35 = a49.getNumberOfTweens();
            if(i35 > 0)
            {
                com.cim.AIA.MultipleTween a50 = this.tweens;
                a50.run();
            }
            KMP a51 = this.theKMP;
            a51.removeAllAnimationRequests();
            this.repaint();
        }
    }
}