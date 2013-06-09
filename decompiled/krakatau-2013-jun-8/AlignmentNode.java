public class AlignmentNode extends com.cim.AIA.Node {
    final public static java.awt.Color DEFAULT_ARROW_COLOR;
    final public static java.awt.Color DEFAULT_ARROW_HIGHLIGHT;
    final public static java.awt.Color DEFAULT_ARRAY_HIGHLIGHT;
    final public static java.awt.Color DEFAULT_ARRAY_ACTIVE;
    final public static java.awt.Color DEFAULT_ARRAY_INACTIVE;
    final public static java.awt.Color DEFAULT_ARRAY_DISABLE;
    private static java.awt.Font originalFont;
    private static java.awt.Font newFont;
    protected boolean traceUp;
    protected boolean highlightUp;
    protected boolean traceLeft;
    protected boolean highlightLeft;
    protected boolean traceDiag;
    protected boolean highlightDiag;
    protected boolean drawTraceBacks;
    protected boolean haveChoice;
    protected boolean traceA;
    protected boolean highlightA;
    protected boolean traceB;
    protected boolean highlightB;
    protected boolean traceC;
    protected boolean highlightC;
    protected java.awt.Color arrayHighlightColor;
    protected java.awt.Color arrayActiveColor;
    protected java.awt.Color arrayInactiveColor;
    protected java.awt.Color arrowHighlightColor;
    protected java.awt.Color arrowColor;
    protected java.awt.Color arrayDisabledColor;
    protected int bufferHeight;
    protected int bufferWidth;
    protected int arrowHeight;
    protected int arrowWidth;
    protected boolean isDisabled;
    protected java.awt.Color saveColor;
    protected int fontSizeDelta;
    private int AWidth;
    private int BWidth;
    
    public AlignmentNode(Object a, int i)
    {
        this(a, i, 10, 10);
    }
    
    public AlignmentNode(Object a, int i, int i0, int i1)
    {
        super(a, i);
        this.AWidth = 0;
        this.BWidth = 0;
        this.traceUp = false;
        this.highlightUp = false;
        this.traceLeft = false;
        this.highlightLeft = false;
        this.traceDiag = false;
        this.highlightDiag = false;
        this.traceA = false;
        this.highlightA = false;
        this.traceB = false;
        this.highlightB = false;
        this.traceC = false;
        this.highlightC = false;
        this.arrowHeight = i1;
        this.arrowWidth = i0;
        this.drawTraceBacks = true;
        java.awt.Color a0 = AlignmentNode.DEFAULT_ARROW_HIGHLIGHT;
        this.arrowHighlightColor = a0;
        java.awt.Color a1 = AlignmentNode.DEFAULT_ARROW_COLOR;
        this.arrowColor = a1;
        java.awt.Color a2 = AlignmentNode.DEFAULT_ARRAY_HIGHLIGHT;
        this.arrayHighlightColor = a2;
        java.awt.Color a3 = AlignmentNode.DEFAULT_ARRAY_ACTIVE;
        this.arrayActiveColor = a3;
        java.awt.Color a4 = AlignmentNode.DEFAULT_ARRAY_INACTIVE;
        this.arrayInactiveColor = a4;
        java.awt.Color a5 = AlignmentNode.DEFAULT_ARRAY_DISABLE;
        this.arrayDisabledColor = a5;
        this.isDisabled = false;
        this.bufferHeight = 0;
        this.bufferWidth = 0;
        this.haveChoice = false;
        this.fontSizeDelta = 4;
        java.awt.Color a6 = this.backgroundColor;
        this.saveColor = a6;
    }
    
    public void draw(java.awt.Graphics a)
    {
        int i = this.arrowWidth;
        int i0 = this.arrowHeight;
        this.arrowWidth = 0;
        this.arrowHeight = 0;
        java.awt.Point a0 = this.pos;
        int i1 = a0.x;
        int i2 = i1 + i;
        a0.x = i2;
        java.awt.Point a1 = this.pos;
        int i3 = a1.y;
        int i4 = i3 + i0;
        a1.y = i4;
        int i5 = this.isVisible?1:0;
        label1: {
            label0: {
                if(i5 != 0)
                {
                    break label0;
                }
                break label1;
            }
            java.awt.Color a2 = this.backgroundColor;
            int i6 = a2.getRed();
            java.awt.Color a3 = this.backgroundColor;
            int i7 = a3.getGreen();
            java.awt.Color a4 = this.backgroundColor;
            int i8 = a4.getBlue();
            float[] a5 = java.awt.Color.RGBtoHSB(i6, i7, i8, (float[])null);
            float f = a5[2];
            double d = (double)f;
            int i9 = d < 0.65?-1:d == 0.65?0:1;
            if(i9 >= 0)
            {
                java.awt.Color a6 = java.awt.Color.black;
                this.setTextColor(a6);
            }
            else
            {
                java.awt.Color a7 = java.awt.Color.white;
                this.setTextColor(a7);
            }
            ((com.cim.AIA.Node)this).draw(a);
            java.awt.Point a8 = this.pos;
            int i10 = a8.x;
            int i11 = this.getWidth();
            int i12 = i11 / 2;
            int i13 = i10 + i12;
            java.awt.Point a9 = this.pos;
            int i14 = a9.y;
            int i15 = this.getHeight();
            int i16 = i15 / 2;
            int i17 = i14 + i16;
            java.awt.Font a10 = AlignmentNode.originalFont;
            if(a10 == null)
            {
                java.awt.Font a11 = a.getFont();
                String s = a11.getName();
                java.awt.Font a12 = a.getFont();
                int i18 = a12.getStyle();
                java.awt.Font a13 = a.getFont();
                int i19 = a13.getSize();
                java.awt.Font a14 = new java.awt.Font(s, i18, i19);
                AlignmentNode.originalFont = a14;
            }
            java.awt.Font a15 = AlignmentNode.newFont;
            if(a15 == null)
            {
                java.awt.Font a16 = a.getFont();
                String s0 = a16.getName();
                java.awt.Font a17 = a.getFont();
                int i20 = a17.getStyle();
                java.awt.Font a18 = a.getFont();
                int i21 = a18.getSize();
                int i22 = this.fontSizeDelta;
                int i23 = i21 - i22;
                java.awt.Font a19 = new java.awt.Font(s0, i20, i23);
                AlignmentNode.newFont = a19;
            }
            java.awt.Font a20 = AlignmentNode.newFont;
            a.setFont(a20);
            int i24 = this.AWidth;
            if(i24 == 0)
            {
                java.awt.FontMetrics a21 = a.getFontMetrics();
                int i25 = a21.stringWidth("A");
                this.AWidth = i25;
            }
            int i26 = this.BWidth;
            if(i26 == 0)
            {
                java.awt.FontMetrics a22 = a.getFontMetrics();
                int i27 = a22.stringWidth("B");
                this.BWidth = i27;
            }
            int i28 = this.haveChoice?1:0;
            label2: {
                if(i28 == 0)
                {
                    break label2;
                }
                int i29 = this.highlightA?1:0;
                label4: {
                    label3: {
                        if(i29 == 0)
                        {
                            break label3;
                        }
                        java.awt.Color a23 = this.arrayHighlightColor;
                        a.setColor(a23);
                        break label4;
                    }
                    int i30 = this.traceA?1:0;
                    if(i30 == 0)
                    {
                        java.awt.Color a24 = this.arrayInactiveColor;
                        a.setColor(a24);
                    }
                    else
                    {
                        java.awt.Color a25 = this.arrayActiveColor;
                        a.setColor(a25);
                    }
                }
                java.awt.Point a26 = this.pos;
                int i31 = a26.x;
                int i32 = this.AWidth;
                int i33 = i32 / 2;
                int i34 = i31 + i33;
                java.awt.Point a27 = this.pos;
                int i35 = a27.y;
                a.drawString("A", i34, i35);
                int i36 = this.highlightB?1:0;
                label6: {
                    label5: {
                        if(i36 == 0)
                        {
                            break label5;
                        }
                        java.awt.Color a28 = this.arrayHighlightColor;
                        a.setColor(a28);
                        break label6;
                    }
                    int i37 = this.traceB?1:0;
                    if(i37 == 0)
                    {
                        java.awt.Color a29 = this.arrayInactiveColor;
                        a.setColor(a29);
                    }
                    else
                    {
                        java.awt.Color a30 = this.arrayActiveColor;
                        a.setColor(a30);
                    }
                }
                java.awt.Point a31 = this.pos;
                int i38 = a31.x;
                int i39 = this.AWidth;
                int i40 = i39 / 2;
                int i41 = i38 + i40;
                int i42 = this.AWidth;
                int i43 = i41 + i42;
                java.awt.Point a32 = this.pos;
                int i44 = a32.y;
                a.drawString("B", i43, i44);
                int i45 = this.highlightC?1:0;
                label8: {
                    label7: {
                        if(i45 == 0)
                        {
                            break label7;
                        }
                        java.awt.Color a33 = this.arrayHighlightColor;
                        a.setColor(a33);
                        break label8;
                    }
                    int i46 = this.traceC?1:0;
                    if(i46 == 0)
                    {
                        java.awt.Color a34 = this.arrayInactiveColor;
                        a.setColor(a34);
                    }
                    else
                    {
                        java.awt.Color a35 = this.arrayActiveColor;
                        a.setColor(a35);
                    }
                }
                java.awt.Point a36 = this.pos;
                int i47 = a36.x;
                int i48 = this.AWidth;
                int i49 = i48 / 2;
                int i50 = i47 + i49;
                int i51 = this.AWidth;
                int i52 = i50 + i51;
                int i53 = this.BWidth;
                int i54 = i52 + i53;
                java.awt.Point a37 = this.pos;
                int i55 = a37.y;
                a.drawString("C", i54, i55);
            }
            java.awt.Color a38 = java.awt.Color.black;
            a.setColor(a38);
            java.awt.Font a39 = AlignmentNode.originalFont;
            a.setFont(a39);
            int i56 = this.drawTraceBacks?1:0;
            label9: {
                if(i56 == 0)
                {
                    break label9;
                }
                int i57 = this.traceUp?1:0;
                label10: {
                    if(i57 == 0)
                    {
                        break label10;
                    }
                    java.awt.Point a40 = this.pos;
                    int i58 = a40.y;
                    int i59 = this.bufferHeight;
                    int i60 = i58 - i59;
                    java.awt.Point a41 = this.pos;
                    int i61 = a41.y;
                    int i62 = i61 - i0;
                    int i63 = this.bufferHeight;
                    int i64 = i62 - i63;
                    com.cim.AIA.Line a42 = new com.cim.AIA.Line(i13, i60, i13, i64);
                    int i65 = this.highlightUp?1:0;
                    if(i65 == 0)
                    {
                        java.awt.Color a43 = this.arrowColor;
                        a42.setColor(a43);
                    }
                    else
                    {
                        java.awt.Color a44 = this.arrowHighlightColor;
                        a42.setColor(a44);
                    }
                    a42.showArrowHead(true);
                    a42.setDistanceFromStartForArrowHead(-3);
                    a42.draw(a);
                }
                int i66 = this.traceLeft?1:0;
                label11: {
                    if(i66 == 0)
                    {
                        break label11;
                    }
                    java.awt.Point a45 = this.pos;
                    int i67 = a45.x;
                    int i68 = this.bufferWidth;
                    int i69 = i67 - i68;
                    java.awt.Point a46 = this.pos;
                    int i70 = a46.x;
                    int i71 = i70 - i;
                    int i72 = this.bufferWidth;
                    int i73 = i71 - i72;
                    com.cim.AIA.Line a47 = new com.cim.AIA.Line(i69, i17, i73, i17);
                    int i74 = this.highlightLeft?1:0;
                    if(i74 == 0)
                    {
                        java.awt.Color a48 = this.arrowColor;
                        a47.setColor(a48);
                    }
                    else
                    {
                        java.awt.Color a49 = this.arrowHighlightColor;
                        a47.setColor(a49);
                    }
                    a47.showArrowHead(true);
                    a47.setDistanceFromStartForArrowHead(-3);
                    a47.draw(a);
                }
                int i75 = this.traceDiag?1:0;
                if(i75 == 0)
                {
                    break label9;
                }
                java.awt.Point a50 = this.pos;
                int i76 = a50.x;
                java.awt.Point a51 = this.pos;
                int i77 = a51.y;
                java.awt.Point a52 = this.pos;
                int i78 = a52.x;
                int i79 = i78 - i;
                int i80 = this.bufferWidth;
                int i81 = i79 - i80;
                java.awt.Point a53 = this.pos;
                int i82 = a53.y;
                int i83 = i82 - i0;
                int i84 = this.bufferHeight;
                int i85 = i83 - i84;
                com.cim.AIA.Line a54 = new com.cim.AIA.Line(i76, i77, i81, i85);
                int i86 = this.highlightDiag?1:0;
                if(i86 == 0)
                {
                    java.awt.Color a55 = this.arrowColor;
                    a54.setColor(a55);
                }
                else
                {
                    java.awt.Color a56 = this.arrowHighlightColor;
                    a54.setColor(a56);
                }
                a54.showArrowHead(true);
                a54.setDistanceFromStartForArrowHead(-3);
                a54.draw(a);
            }
            java.awt.Point a57 = this.pos;
            int i87 = a57.x;
            int i88 = i87 - i;
            a57.x = i88;
            java.awt.Point a58 = this.pos;
            int i89 = a58.y;
            int i90 = i89 - i0;
            a58.y = i90;
            this.arrowWidth = i;
            this.arrowHeight = i0;
        }
    }
    
    public java.awt.Color getArrayActiveColor()
    {
        java.awt.Color a = this.arrayActiveColor;
        return a;
    }
    
    public java.awt.Color getArrayHighlightColor()
    {
        java.awt.Color a = this.arrayHighlightColor;
        return a;
    }
    
    public java.awt.Color getArrayInactiveColor()
    {
        java.awt.Color a = this.arrayInactiveColor;
        return a;
    }
    
    public java.awt.Color getArrowColor()
    {
        java.awt.Color a = this.arrowColor;
        return a;
    }
    
    public int getArrowHeight()
    {
        int i = this.arrowHeight;
        return i;
    }
    
    public java.awt.Color getArrowHighlightColor()
    {
        java.awt.Color a = this.arrowHighlightColor;
        return a;
    }
    
    public int getArrowWidth()
    {
        int i = this.arrowWidth;
        return i;
    }
    
    public int getBufferHeight()
    {
        int i = this.bufferHeight;
        return i;
    }
    
    public int getBufferWidth()
    {
        int i = this.bufferWidth;
        return i;
    }
    
    public boolean getDrawTraceBacks()
    {
        int i = this.drawTraceBacks?1:0;
        return i != 0;
    }
    
    public int getHeight()
    {
        int i = ((com.cim.AIA.Node)this).getHeight();
        int i0 = this.arrowHeight;
        int i1 = i + i0;
        return i1;
    }
    
    public int getRealHeight()
    {
        int i = ((com.cim.AIA.Node)this).getHeight();
        int i0 = this.arrowHeight;
        int i1 = i + i0;
        int i2 = this.bufferHeight;
        int i3 = i1 + i2;
        return i3;
    }
    
    public int getRealWidth()
    {
        int i = ((com.cim.AIA.Node)this).getWidth();
        int i0 = this.arrowWidth;
        int i1 = i + i0;
        int i2 = this.bufferWidth;
        int i3 = i1 + i2;
        return i3;
    }
    
    public boolean getTraceA()
    {
        int i = this.traceA?1:0;
        return i != 0;
    }
    
    public boolean getTraceB()
    {
        int i = this.traceB?1:0;
        return i != 0;
    }
    
    public boolean getTraceC()
    {
        int i = this.traceC?1:0;
        return i != 0;
    }
    
    public boolean getTraceDiag()
    {
        int i = this.traceDiag?1:0;
        return i != 0;
    }
    
    public boolean getTraceLeft()
    {
        int i = this.traceLeft?1:0;
        return i != 0;
    }
    
    public boolean getTraceUp()
    {
        int i = this.traceUp?1:0;
        return i != 0;
    }
    
    public int getWidth()
    {
        int i = ((com.cim.AIA.Node)this).getWidth();
        int i0 = this.arrowWidth;
        int i1 = i + i0;
        return i1;
    }
    
    public void highlightA()
    {
        this.highlightA = true;
    }
    
    public void highlightB()
    {
        this.highlightB = true;
    }
    
    public void highlightC()
    {
        this.highlightC = true;
    }
    
    public void highlightDiag()
    {
        this.highlightDiag = true;
    }
    
    public void highlightLeft()
    {
        this.highlightLeft = true;
    }
    
    public void highlightUp()
    {
        this.highlightUp = true;
    }
    
    public void setArrayActiveColor(java.awt.Color a)
    {
        this.arrayActiveColor = a;
    }
    
    public void setArrayHighlightColor(java.awt.Color a)
    {
        this.arrayHighlightColor = a;
    }
    
    public void setArrayInactiveColor(java.awt.Color a)
    {
        this.arrayInactiveColor = a;
    }
    
    public void setArrowColor(java.awt.Color a)
    {
        this.arrowColor = a;
    }
    
    public void setArrowHeight(int i)
    {
        this.arrowHeight = i;
    }
    
    public void setArrowHighlightColor(java.awt.Color a)
    {
        this.arrowHighlightColor = a;
    }
    
    public void setArrowWidth(int i)
    {
        this.arrowWidth = i;
    }
    
    public void setBackgroundColor(java.awt.Color a)
    {
        ((com.cim.AIA.Node)this).setBackgroundColor(a);
    }
    
    public void setBufferHeight(int i)
    {
        this.bufferHeight = i;
    }
    
    public void setBufferWidth(int i)
    {
        this.bufferWidth = i;
    }
    
    public void setDrawTraceBacks(boolean b)
    {
        this.drawTraceBacks = b;
    }
    
    public void setFontSizeDelta(int i)
    {
        this.fontSizeDelta = i;
    }
    
    public void setHaveChoice(boolean b)
    {
        this.haveChoice = b;
    }
    
    public void setIsDisabled(boolean b)
    {
        this.isDisabled = b;
        int i = b?1:0;
        if(i != 1)
        {
            java.awt.Color a = this.saveColor;
            this.setBackgroundColor(a);
        }
        else
        {
            java.awt.Color a0 = this.backgroundColor;
            this.saveColor = a0;
            java.awt.Color a1 = this.arrayDisabledColor;
            this.setBackgroundColor(a1);
        }
    }
    
    public void setTraceA(boolean b)
    {
        this.traceA = b;
    }
    
    public void setTraceB(boolean b)
    {
        this.traceB = b;
    }
    
    public void setTraceC(boolean b)
    {
        this.traceC = b;
    }
    
    public void setTraceDiag(boolean b)
    {
        this.traceDiag = b;
    }
    
    public void setTraceLeft(boolean b)
    {
        this.traceLeft = b;
    }
    
    public void setTraceUp(boolean b)
    {
        this.traceUp = b;
    }
    
    public void unHighlightA()
    {
        this.highlightA = false;
    }
    
    public void unHighlightB()
    {
        this.highlightB = false;
    }
    
    public void unHighlightC()
    {
        this.highlightC = false;
    }
    
    public void unHighlightDiag()
    {
        this.highlightDiag = false;
    }
    
    public void unHighlightLeft()
    {
        this.highlightLeft = false;
    }
    
    public void unHighlightUp()
    {
        this.highlightUp = false;
    }
    
    static
    {
        java.awt.Color a = java.awt.Color.black;
        AlignmentNode.DEFAULT_ARROW_COLOR = a;
        java.awt.Color a0 = java.awt.Color.red;
        AlignmentNode.DEFAULT_ARROW_HIGHLIGHT = a0;
        java.awt.Color a1 = java.awt.Color.red;
        AlignmentNode.DEFAULT_ARRAY_HIGHLIGHT = a1;
        java.awt.Color a2 = java.awt.Color.black;
        AlignmentNode.DEFAULT_ARRAY_ACTIVE = a2;
        java.awt.Color a3 = java.awt.Color.lightGray;
        AlignmentNode.DEFAULT_ARRAY_INACTIVE = a3;
        java.awt.Color a4 = java.awt.Color.gray;
        AlignmentNode.DEFAULT_ARRAY_DISABLE = a4;
        AlignmentNode.originalFont = null;
        AlignmentNode.newFont = null;
    }
}