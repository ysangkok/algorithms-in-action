public class AlignmentKey implements com.cim.AIA.Drawable {
    final protected int X_GAP;
    final protected int numOfEntries;
    final protected int numOfColumns;
    final protected int numPerColumn;
    protected java.awt.Point location;
    protected String[] data;
    protected com.cim.AIA.Node[] dataNode;
    protected int[] runningWidth;
    
    public AlignmentKey()
    {
        super();
        this.X_GAP = 10;
        this.numOfEntries = 6;
        this.numOfColumns = 2;
        double d = Math.ceil(3.0);
        int i = (int)d;
        this.numPerColumn = i;
        java.awt.Point a = new java.awt.Point();
        this.location = a;
        String[] a0 = new String[6];
        this.data = a0;
        com.cim.AIA.Node[] a1 = new com.cim.AIA.Node[6];
        this.dataNode = a1;
        int[] a2 = new int[2];
        this.runningWidth = a2;
        this.setLocation(0, 0);
        String[] a3 = this.data;
        String s = localization.Messages.getString("AlignmentKey.0");
        String s0 = new String(s);
        a3[0] = s0;
        com.cim.AIA.Node[] a4 = this.dataNode;
        com.cim.AIA.Node a5 = new com.cim.AIA.Node((Object)"", 0);
        a4[0] = a5;
        com.cim.AIA.Node[] a6 = this.dataNode;
        com.cim.AIA.Node a7 = a6[0];
        java.awt.Color a8 = AlignmentNode.DEFAULT_ARRAY_DISABLE;
        a7.setBackgroundColor(a8);
        String[] a9 = this.data;
        String s1 = localization.Messages.getString("AlignmentKey.2");
        String s2 = new String(s1);
        a9[1] = s2;
        com.cim.AIA.Node[] a10 = this.dataNode;
        com.cim.AIA.Node a11 = new com.cim.AIA.Node((Object)"", 0);
        a10[1] = a11;
        com.cim.AIA.Node[] a12 = this.dataNode;
        com.cim.AIA.Node a13 = a12[1];
        java.awt.Color a14 = Alignment.DEFAULT_CURRENT_COLOR;
        a13.setBackgroundColor(a14);
        com.cim.AIA.Node[] a15 = this.dataNode;
        com.cim.AIA.Node a16 = a15[1];
        java.awt.Color a17 = Alignment.HIGHLIGHT_RING_COLOR;
        a16.setRingColor(a17);
        String[] a18 = this.data;
        String s3 = localization.Messages.getString("AlignmentKey.4");
        String s4 = new String(s3);
        a18[2] = s4;
        com.cim.AIA.Node[] a19 = this.dataNode;
        com.cim.AIA.Node a20 = new com.cim.AIA.Node((Object)"", 0);
        a19[2] = a20;
        com.cim.AIA.Node[] a21 = this.dataNode;
        com.cim.AIA.Node a22 = a21[2];
        java.awt.Color a23 = Alignment.IRRELEVANT_COLOR;
        a22.setBackgroundColor(a23);
        com.cim.AIA.Node[] a24 = this.dataNode;
        com.cim.AIA.Node a25 = a24[2];
        java.awt.Color a26 = Alignment.HIGHLIGHT_RING_COLOR;
        a25.setRingColor(a26);
        String[] a27 = this.data;
        String s5 = localization.Messages.getString("AlignmentKey.6");
        String s6 = new String(s5);
        a27[3] = s6;
        com.cim.AIA.Node[] a28 = this.dataNode;
        com.cim.AIA.Node a29 = new com.cim.AIA.Node((Object)"", 0);
        a28[3] = a29;
        com.cim.AIA.Node[] a30 = this.dataNode;
        com.cim.AIA.Node a31 = a30[3];
        java.awt.Color a32 = Alignment.DEFAULT_NODE_COLOR;
        a31.setBackgroundColor(a32);
        String[] a33 = this.data;
        String s7 = localization.Messages.getString("AlignmentKey.8");
        String s8 = new String(s7);
        a33[4] = s8;
        com.cim.AIA.Node[] a34 = this.dataNode;
        com.cim.AIA.Node a35 = new com.cim.AIA.Node((Object)"", 0);
        a34[4] = a35;
        com.cim.AIA.Node[] a36 = this.dataNode;
        com.cim.AIA.Node a37 = a36[4];
        java.awt.Color a38 = Alignment.DEFAULT_SELECTED_COLOR;
        a37.setBackgroundColor(a38);
        String[] a39 = this.data;
        String s9 = localization.Messages.getString("AlignmentKey.10");
        String s10 = new String(s9);
        a39[5] = s10;
        com.cim.AIA.Node[] a40 = this.dataNode;
        com.cim.AIA.Node a41 = new com.cim.AIA.Node((Object)"", 0);
        a40[5] = a41;
        com.cim.AIA.Node[] a42 = this.dataNode;
        com.cim.AIA.Node a43 = a42[5];
        java.awt.Color a44 = Alignment.DEFAULT_HIGHLIGHT_COLOR;
        a43.setBackgroundColor(a44);
    }
    
    public void draw(java.awt.Graphics a)
    {
        java.awt.Point a0 = this.location;
        int i = a0.x;
        java.awt.Color a1 = java.awt.Color.black;
        a.setColor(a1);
        int i0 = 0;
        int i1 = i;
        int i2 = 0;
        while(true)
        {
            int i3 = 0;
            int i4 = i0;
            if(i2 >= 2)
            {
                return;
            }
            else
            {
                i3 = i4;
            }
            java.awt.Point a2 = this.location;
            int i5 = i3;
            int i6 = a2.y;
            int i7 = i5;
            int i8 = i7;
            int i9 = i6;
            int i10 = 0;
            while(true)
            {
                int i11 = 0;
                int i12 = this.numPerColumn;
                int i13 = i8;
                label0: {
                    int i14 = 0;
                    int i15 = 0;
                    int i16 = i13;
                    int i17 = i13;
                    if(i10 >= i12)
                    {
                        i11 = i17;
                        break label0;
                    }
                    else
                    {
                        i14 = i16;
                    }
                    int i18 = i14;
                    if(i14 < 6)
                    {
                        com.cim.AIA.Node[] a3 = this.dataNode;
                        com.cim.AIA.Node a4 = a3[i14];
                        java.awt.Point a5 = new java.awt.Point(i1, i9);
                        a4.setPosition(a5);
                        com.cim.AIA.Node[] a6 = this.dataNode;
                        com.cim.AIA.Node a7 = a6[i14];
                        a7.draw(a);
                        String[] a8 = this.data;
                        String s = a8[i14];
                        int i19 = i1 + 10;
                        com.cim.AIA.Node[] a9 = this.dataNode;
                        com.cim.AIA.Node a10 = a9[i14];
                        int i20 = a10.getWidth();
                        int i21 = i19 + i20;
                        java.awt.FontMetrics a11 = a.getFontMetrics();
                        int i22 = a11.getAscent();
                        int i23 = i22 / 2;
                        int i24 = i9 + i23;
                        com.cim.AIA.Node[] a12 = this.dataNode;
                        com.cim.AIA.Node a13 = a12[i14];
                        int i25 = a13.getHeight();
                        int i26 = i25 / 2;
                        int i27 = i24 + i26;
                        a.drawString(s, i21, i27);
                        com.cim.AIA.Node[] a14 = this.dataNode;
                        com.cim.AIA.Node a15 = a14[i14];
                        int i28 = a15.getHeight();
                        int i29 = i9 + i28;
                        int i30 = i14 + 1;
                        int i31 = i10 + 1;
                        i8 = i30;
                        i9 = i29;
                        i10 = i31;
                        continue;
                    }
                    else
                    {
                        i15 = i18;
                    }
                    i11 = i15;
                }
                int i32 = i1 + 10;
                int[] a16 = this.runningWidth;
                int i33 = i11;
                int i34 = a16[i2];
                int i35 = i33;
                int i36 = i32 + i34;
                int i37 = i2 + 1;
                i0 = i35;
                i1 = i36;
                i2 = i37;
            }
        }
    }
    
    public void draw(java.awt.Graphics a, java.awt.Point a0)
    {
        this.setLocation(a0);
        this.draw(a);
    }
    
    public int getHeight()
    {
        com.cim.AIA.Node a = new com.cim.AIA.Node((Object)"", 0);
        int i = a.getHeight();
        int i0 = this.numPerColumn;
        int i1 = i * i0;
        return i1;
    }
    
    public java.awt.Point getLocation()
    {
        java.awt.Point a = this.location;
        return a;
    }
    
    public int getWidth()
    {
        int i = 0;
        int i0 = 0;
        int i1 = 0;
        label2: while(true)
        {
            int i2 = 0;
            int i3 = i;
            if(i1 >= 2)
            {
                return i0;
            }
            else
            {
                i2 = i3;
            }
            int i4 = i2;
            int i5 = 0;
            while(true)
            {
                int i6 = 0;
                int i7 = this.numPerColumn;
                int i8 = i4;
                label1: {
                    int i9 = 0;
                    label0: {
                        int i10 = 0;
                        int i11 = i8;
                        int i12 = i8;
                        if(i5 >= i7)
                        {
                            i9 = i12;
                            break label0;
                        }
                        else
                        {
                            i6 = i11;
                        }
                        int i13 = i6;
                        if(i6 < 6)
                        {
                            break label1;
                        }
                        else
                        {
                            i10 = i13;
                        }
                        i9 = i10;
                    }
                    int[] a = this.runningWidth;
                    int i14 = i9;
                    int i15 = a[i1];
                    int i16 = i14;
                    int i17 = i0 + i15;
                    int i18 = i1 + 1;
                    i = i16;
                    i0 = i17;
                    i1 = i18;
                    continue label2;
                }
                com.cim.AIA.Node[] a0 = this.dataNode;
                com.cim.AIA.Node a1 = a0[i6];
                int i19 = a1.getWidth();
                AlignmentCanvas a2 = AlignmentApplet.theAlignmentCanvas;
                java.awt.Graphics a3 = a2.getGraphics();
                java.awt.FontMetrics a4 = a3.getFontMetrics();
                String[] a5 = this.data;
                String s = a5[i6];
                int i20 = a4.stringWidth(s);
                int i21 = i19 + i20;
                int i22 = i21 + 10;
                int[] a6 = this.runningWidth;
                int i23 = a6[i1];
                if(i22 > i23)
                {
                    int[] a7 = this.runningWidth;
                    a7[i1] = i22;
                }
                int i24 = i6 + 1;
                int i25 = i5 + 1;
                i4 = i24;
                i5 = i25;
            }
        }
    }
    
    public void setLocation(int i, int i0)
    {
        java.awt.Point a = this.location;
        a.x = i;
        java.awt.Point a0 = this.location;
        a0.y = i0;
    }
    
    public void setLocation(java.awt.Point a)
    {
        this.location = a;
    }
}