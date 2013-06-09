public class BoyerMooreCanvas extends com.cim.AIA.AlgorithmCanvas {
    final private static long serialVersionUID = -2145781309980138119L;
    final public static int xBuffer = 100;
    protected BoyerMooreString theString1;
    protected BoyerMooreString theString2;
    protected BoyerMooreString testBoyerMooreString1;
    protected BoyerMooreString testBoyerMooreString2;
    protected BoyerMooreNextTable theNextTable;
    protected BoyerMooreSkipTable theSkipTable;
    protected String skipTableS;
    protected com.cim.AIA.Node skipTableN;
    protected com.cim.AIA.Node patternN;
    protected com.cim.AIA.Node mi1N;
    protected com.cim.AIA.Node skipN;
    protected int BoyerMooreResult;
    protected com.cim.AIA.ElementArray dataElementArray;
    protected int yBuffer;
    protected int windowHeight;
    protected int windowWidth;
    protected java.awt.Font normalFont;
    protected java.awt.Color textColor;
    protected BoyerMoore theBoyerMoore;
    
    public BoyerMooreCanvas()
    {
        super();
        this.yBuffer = 10;
    }
    
    public void displayAlgorithm(java.awt.Graphics a)
    {
        BoyerMoore a0 = this.theBoyerMoore;
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
            BoyerMooreString a5 = this.theString1;
            if(a5 != null)
            {
                String s = localization.Messages.getString("BoyerMooreCanvas.0");
                java.awt.FontMetrics a6 = a.getFontMetrics();
                int i = a6.stringWidth(s);
                int i0 = 100 - i;
                int i1 = this.yBuffer;
                int i2 = i1 + 40;
                a.drawString(s, i0, i2);
                BoyerMooreString a7 = this.theString1;
                a7.draw(a);
            }
            BoyerMooreString a8 = this.theString2;
            if(a8 != null)
            {
                String s0 = localization.Messages.getString("BoyerMooreCanvas.1");
                java.awt.FontMetrics a9 = a.getFontMetrics();
                int i3 = a9.stringWidth(s0);
                int i4 = 100 - i3;
                int i5 = this.yBuffer;
                int i6 = i5 + 60;
                a.drawString(s0, i4, i6);
                BoyerMooreString a10 = this.theString2;
                a10.draw(a);
            }
            BoyerMooreString a11 = this.testBoyerMooreString1;
            if(a11 != null)
            {
                String s1 = localization.Messages.getString("BoyerMooreCanvas.2");
                java.awt.FontMetrics a12 = a.getFontMetrics();
                int i7 = a12.stringWidth(s1);
                int i8 = 100 - i7;
                int i9 = this.yBuffer;
                int i10 = i9 + 90;
                a.drawString(s1, i8, i10);
                BoyerMooreString a13 = this.testBoyerMooreString1;
                a13.draw(a);
            }
            BoyerMooreString a14 = this.testBoyerMooreString2;
            if(a14 != null)
            {
                String s2 = localization.Messages.getString("BoyerMooreCanvas.3");
                java.awt.FontMetrics a15 = a.getFontMetrics();
                int i11 = a15.stringWidth(s2);
                int i12 = 100 - i11;
                int i13 = this.yBuffer;
                int i14 = i13 + 110;
                a.drawString(s2, i12, i14);
                BoyerMooreString a16 = this.testBoyerMooreString2;
                a16.draw(a);
            }
            BoyerMooreNextTable a17 = this.theNextTable;
            if(a17 != null)
            {
                String s3 = localization.Messages.getString("BoyerMooreCanvas.4");
                java.awt.FontMetrics a18 = a.getFontMetrics();
                int i15 = a18.stringWidth(s3);
                int i16 = 100 - i15;
                int i17 = this.yBuffer;
                int i18 = i17 + 140;
                a.drawString(s3, i16, i18);
                BoyerMooreNextTable a19 = this.theNextTable;
                a19.draw(a);
            }
            BoyerMooreSkipTable a20 = this.theSkipTable;
            label3: {
                label2: {
                    if(a20 == null)
                    {
                        break label2;
                    }
                    BoyerMooreNextTable a21 = this.theNextTable;
                    if(a21 == null)
                    {
                        break label2;
                    }
                    String s4 = localization.Messages.getString("BoyerMooreCanvas.5");
                    BoyerMooreNextTable a22 = this.theNextTable;
                    int i19 = a22.getWidth();
                    int i20 = 100 + i19;
                    int i21 = i20 + 30;
                    int i22 = this.yBuffer;
                    int i23 = i22 + 140;
                    a.drawString(s4, i21, i23);
                    BoyerMooreSkipTable a23 = this.theSkipTable;
                    a23.draw(a);
                    break label3;
                }
                BoyerMooreSkipTable a24 = this.theSkipTable;
                if(a24 != null)
                {
                    String s5 = localization.Messages.getString("BoyerMooreCanvas.6");
                    java.awt.FontMetrics a25 = a.getFontMetrics();
                    int i24 = a25.stringWidth(s5);
                    int i25 = 100 - i24;
                    int i26 = this.yBuffer;
                    int i27 = i26 + 140;
                    a.drawString(s5, i25, i27);
                    BoyerMooreSkipTable a26 = this.theSkipTable;
                    a26.draw(a);
                }
            }
            com.cim.AIA.Node a27 = this.skipTableN;
            if(a27 != null)
            {
                StringBuilder a28 = new StringBuilder();
                String s6 = localization.Messages.getString("BoyerMooreCanvas.7");
                StringBuilder a29 = a28.append(s6);
                String s7 = this.skipTableS;
                StringBuilder a30 = a29.append(s7);
                StringBuilder a31 = a30.append("]");
                String s8 = a31.toString();
                com.cim.AIA.Node a32 = this.skipTableN;
                java.awt.Point a33 = a32.getPosition();
                int i28 = a33.y;
                com.cim.AIA.Node a34 = this.skipTableN;
                int i29 = a34.getHeight();
                int i30 = i28 + i29;
                a.drawString(s8, 100, i30);
                com.cim.AIA.Node a35 = this.skipTableN;
                a35.draw(a);
            }
            com.cim.AIA.Node a36 = this.patternN;
            if(a36 != null)
            {
                String s9 = localization.Messages.getString("BoyerMooreCanvas.9");
                com.cim.AIA.Node a37 = this.patternN;
                java.awt.Point a38 = a37.getPosition();
                int i31 = a38.y;
                com.cim.AIA.Node a39 = this.patternN;
                int i32 = a39.getHeight();
                int i33 = i31 + i32;
                a.drawString(s9, 100, i33);
                com.cim.AIA.Node a40 = this.patternN;
                a40.draw(a);
            }
            com.cim.AIA.Node a41 = this.skipN;
            if(a41 != null)
            {
                String s10 = localization.Messages.getString("BoyerMooreCanvas.10");
                int i34 = this.yBuffer;
                int i35 = i34 + 105;
                a.drawString(s10, 100, i35);
                com.cim.AIA.Node a42 = this.skipN;
                a42.draw(a);
            }
            com.cim.AIA.Node a43 = this.mi1N;
            if(a43 != null)
            {
                String s11 = localization.Messages.getString("BoyerMooreCanvas.11");
                int i36 = this.yBuffer;
                int i37 = i36 + 105;
                a.drawString(s11, 100, i37);
                com.cim.AIA.Node a44 = this.mi1N;
                a44.draw(a);
            }
            int i38 = this.BoyerMooreResult;
            if(i38 == 0)
            {
                java.awt.Color a45 = a.getColor();
                java.awt.Color a46 = java.awt.Color.red;
                a.setColor(a46);
                String s12 = localization.Messages.getString("BoyerMooreCanvas.12");
                a.drawString(s12, 100, 90);
                a.setColor(a45);
            }
            int i39 = this.BoyerMooreResult;
            if(i39 == 1)
            {
                java.awt.Color a47 = a.getColor();
                java.awt.Color a48 = java.awt.Color.green;
                a.setColor(a48);
                String s13 = localization.Messages.getString("BoyerMooreCanvas.13");
                a.drawString(s13, 100, 90);
                a.setColor(a47);
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
            BoyerMoore dummy = (BoyerMoore)a2;
            BoyerMoore a3 = (BoyerMoore)a2;
            this.theBoyerMoore = a3;
            BoyerMoore a4 = this.theBoyerMoore;
            BoyerMooreString a5 = a4.getBoyerMooreString1();
            this.theString1 = a5;
            BoyerMooreString a6 = this.theString1;
            if(a6 != null)
            {
                BoyerMooreString a7 = this.theString1;
                int i = this.yBuffer;
                int i0 = i + 40;
                a7.setPosition(100, i0);
                BoyerMooreString a8 = this.theString1;
                int i1 = a8.getWidth();
                int i2 = 100 + i1;
                this.windowWidth = i2;
            }
            BoyerMoore a9 = this.theBoyerMoore;
            BoyerMooreString a10 = a9.getBoyerMooreString2();
            this.theString2 = a10;
            BoyerMooreString a11 = this.theString2;
            label2: {
                if(a11 == null)
                {
                    break label2;
                }
                BoyerMooreString a12 = this.theString2;
                int i3 = this.yBuffer;
                int i4 = i3 + 60;
                a12.setPosition(100, i4);
                BoyerMooreString a13 = this.theString2;
                int i5 = a13.getWidth();
                int i6 = 100 + i5;
                int i7 = this.windowWidth;
                if(i6 > i7)
                {
                    this.windowWidth = i6;
                }
            }
            BoyerMoore a14 = this.theBoyerMoore;
            BoyerMooreNextTable a15 = a14.getNextTable();
            this.theNextTable = a15;
            BoyerMooreNextTable a16 = this.theNextTable;
            if(a16 != null)
            {
                BoyerMooreNextTable a17 = this.theNextTable;
                int i8 = this.yBuffer;
                int i9 = i8 + 140;
                a17.setPosition(100, i9);
                int i10 = this.yBuffer;
                int i11 = i10 + 160;
                BoyerMooreNextTable a18 = this.theNextTable;
                int i12 = a18.getHeight();
                int i13 = i11 + i12;
                this.windowHeight = i13;
            }
            BoyerMoore a19 = this.theBoyerMoore;
            BoyerMooreSkipTable a20 = a19.getSkipTable();
            this.theSkipTable = a20;
            BoyerMooreSkipTable a21 = this.theSkipTable;
            label4: {
                label3: {
                    if(a21 == null)
                    {
                        break label3;
                    }
                    BoyerMooreNextTable a22 = this.theNextTable;
                    if(a22 == null)
                    {
                        break label3;
                    }
                    BoyerMooreSkipTable a23 = this.theSkipTable;
                    BoyerMooreNextTable a24 = this.theNextTable;
                    int i14 = a24.getWidth();
                    int i15 = 100 + i14;
                    int i16 = i15 + 30;
                    int i17 = this.yBuffer;
                    int i18 = i17 + 140;
                    a23.setPosition(i16, i18);
                    int i19 = this.yBuffer;
                    int i20 = i19 + 160;
                    BoyerMooreSkipTable a25 = this.theSkipTable;
                    int i21 = a25.getHeight();
                    int i22 = i20 + i21;
                    this.windowHeight = i22;
                    break label4;
                }
                BoyerMooreSkipTable a26 = this.theSkipTable;
                if(a26 != null)
                {
                    BoyerMooreSkipTable a27 = this.theSkipTable;
                    int i23 = this.yBuffer;
                    int i24 = i23 + 140;
                    a27.setPosition(100, i24);
                    int i25 = this.yBuffer;
                    int i26 = i25 + 160;
                    BoyerMooreSkipTable a28 = this.theSkipTable;
                    int i27 = a28.getHeight();
                    int i28 = i26 + i27;
                    this.windowHeight = i28;
                }
            }
            BoyerMoore a29 = this.theBoyerMoore;
            BoyerMooreString a30 = a29.getTestBoyerMooreString1();
            this.testBoyerMooreString1 = a30;
            BoyerMooreString a31 = this.testBoyerMooreString1;
            if(a31 != null)
            {
                BoyerMooreString a32 = this.testBoyerMooreString1;
                int i29 = this.yBuffer;
                int i30 = i29 + 90;
                a32.setPosition(100, i30);
            }
            BoyerMoore a33 = this.theBoyerMoore;
            BoyerMooreString a34 = a33.getTestBoyerMooreString2();
            this.testBoyerMooreString2 = a34;
            BoyerMooreString a35 = this.testBoyerMooreString2;
            if(a35 != null)
            {
                BoyerMooreString a36 = this.testBoyerMooreString2;
                int i31 = this.yBuffer;
                int i32 = i31 + 110;
                a36.setPosition(100, i32);
            }
            BoyerMoore a37 = this.theBoyerMoore;
            com.cim.AIA.Node a38 = a37.getSkipTableN();
            this.skipTableN = a38;
            com.cim.AIA.Node a39 = this.skipTableN;
            if(a39 != null)
            {
                com.cim.AIA.Node a40 = this.skipTableN;
                int i33 = this.windowHeight;
                java.awt.Point a41 = new java.awt.Point(220, i33);
                a40.setPosition(a41);
                int i34 = this.windowHeight;
                com.cim.AIA.Node a42 = this.skipTableN;
                int i35 = a42.getHeight();
                int i36 = i34 + i35;
                this.windowHeight = i36;
                BoyerMoore a43 = this.theBoyerMoore;
                String s = a43.getSkipTableS();
                this.skipTableS = s;
            }
            BoyerMoore a44 = this.theBoyerMoore;
            com.cim.AIA.Node a45 = a44.getPatternN();
            this.patternN = a45;
            com.cim.AIA.Node a46 = this.patternN;
            if(a46 != null)
            {
                com.cim.AIA.Node a47 = this.patternN;
                int i37 = this.windowHeight;
                java.awt.Point a48 = new java.awt.Point(220, i37);
                a47.setPosition(a48);
                int i38 = this.windowHeight;
                com.cim.AIA.Node a49 = this.patternN;
                int i39 = a49.getHeight();
                int i40 = i38 + i39;
                this.windowHeight = i40;
            }
            BoyerMoore a50 = this.theBoyerMoore;
            com.cim.AIA.Node a51 = a50.getSkipN();
            this.skipN = a51;
            com.cim.AIA.Node a52 = this.skipN;
            if(a52 != null)
            {
                com.cim.AIA.Node a53 = this.skipN;
                int i41 = this.yBuffer;
                int i42 = i41 + 90;
                java.awt.Point a54 = new java.awt.Point(140, i42);
                a53.setPosition(a54);
            }
            BoyerMoore a55 = this.theBoyerMoore;
            com.cim.AIA.Node a56 = a55.getMi1N();
            this.mi1N = a56;
            com.cim.AIA.Node a57 = this.mi1N;
            if(a57 != null)
            {
                com.cim.AIA.Node a58 = this.mi1N;
                int i43 = this.yBuffer;
                int i44 = i43 + 90;
                java.awt.Point a59 = new java.awt.Point(240, i44);
                a58.setPosition(a59);
            }
            BoyerMoore a60 = this.theBoyerMoore;
            int i45 = a60.getBoyerMooreResult();
            this.BoyerMooreResult = i45;
            BoyerMoore a61 = this.theBoyerMoore;
            int i46 = this.numberOfTweenSteps;
            com.cim.AIA.MultipleTween a62 = a61.generateTweens((com.cim.AIA.Paintable)this, (Object)null, i46);
            this.addTween((com.cim.AIA.Tween)a62);
            com.cim.AIA.MultipleTween a63 = this.tweens;
            int i47 = a63.getNumberOfTweens();
            if(i47 > 0)
            {
                com.cim.AIA.MultipleTween a64 = this.tweens;
                a64.run();
            }
            BoyerMoore a65 = this.theBoyerMoore;
            a65.removeAllAnimationRequests();
            int i48 = this.windowWidth;
            int i49 = this.windowHeight;
            this.setSize(i48, i49);
            this.repaint();
        }
    }
}