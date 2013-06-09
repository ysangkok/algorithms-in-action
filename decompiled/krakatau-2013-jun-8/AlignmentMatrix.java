public class AlignmentMatrix implements com.cim.AIA.Drawable, com.cim.AIA.Selectable {
    final public static int NEG_INF = -5000;
    final public static int POS_INF = 5000;
    final protected int DEFAULT_BUFFER_HEIGHT;
    final protected int DEFAULT_X_GAP;
    final protected int DEFAULT_Y_GAP;
    final protected int DEFAULT_ARROW_X;
    final protected int DEFAULT_ARROW_Y;
    final protected int DEFAULT_SHIFT_X;
    final protected int DEFAULT_SHIFT_Y;
    final protected java.awt.Color DEFAULT_NAMECOLOR;
    final protected java.awt.Color DEFAULT_HIGHLIGHTCOLOR;
    final protected java.awt.Color DEFAULT_SELECTCOLOR;
    final protected java.awt.Color DEFAULT_LABELCOLOR;
    final protected int MATRIX_X_GAP;
    protected com.cim.AIA.Node[] xNodes;
    protected com.cim.AIA.Node[] yNodes;
    protected String matrixName;
    protected int xIndexHighlight;
    protected int yIndexHighlight;
    protected java.awt.Color nodeBackgroundColor;
    protected java.awt.Color nodeHighlightColor;
    protected java.awt.Color labelColor;
    protected java.awt.Color highlightColor;
    protected java.awt.Color selectColor;
    protected AlignmentNode[][] saveMatrix;
    protected AlignmentNode[][] theMatrix;
    protected int nodeHeight;
    protected int nodeWidth;
    protected int aNodeHeight;
    protected int aNodeWidth;
    protected int arrowHeight;
    protected int arrowWidth;
    protected int xSize;
    protected int ySize;
    protected String xLabel;
    protected String yLabel;
    protected int shiftX;
    protected int shiftY;
    protected int xGap;
    protected int yGap;
    protected java.awt.Point location;
    protected int sequenceNumber;
    protected int internalBoxX;
    protected int internalBoxY;
    protected int internalBoxWidth;
    protected int internalBoxHeight;
    protected int currentMax;
    protected int currentMaxX;
    protected int currentMaxY;
    protected boolean drawNull;
    protected boolean drawIndex;
    protected boolean isHighlight;
    protected boolean isGapMode;
    protected boolean isSelected;
    protected boolean drawName;
    protected boolean drawInternalBox;
    
    public AlignmentMatrix(int i, int i0)
    {
        this(i, i0, (String)null, (String)null);
    }
    
    public AlignmentMatrix(int i, int i0, String s, String s0)
    {
        super();
        this.DEFAULT_BUFFER_HEIGHT = 10;
        this.DEFAULT_X_GAP = 5;
        this.DEFAULT_Y_GAP = 5;
        this.DEFAULT_ARROW_X = 10;
        this.DEFAULT_ARROW_Y = 10;
        this.DEFAULT_SHIFT_X = 1;
        this.DEFAULT_SHIFT_Y = 1;
        java.awt.Color a = java.awt.Color.black;
        this.DEFAULT_NAMECOLOR = a;
        java.awt.Color a0 = java.awt.Color.red;
        this.DEFAULT_HIGHLIGHTCOLOR = a0;
        java.awt.Color a1 = java.awt.Color.blue;
        this.DEFAULT_SELECTCOLOR = a1;
        java.awt.Color a2 = new java.awt.Color(0, 200, 0);
        this.DEFAULT_LABELCOLOR = a2;
        this.MATRIX_X_GAP = 5;
        String s1 = new String();
        this.matrixName = s1;
        java.awt.Point a3 = new java.awt.Point();
        this.location = a3;
        this.sequenceNumber = 1;
        AlignmentNode[][] a4 = new AlignmentNode[i][i0];
        this.theMatrix = a4;
        AlignmentNode[][] a5 = new AlignmentNode[i][i0];
        this.saveMatrix = a5;
        com.cim.AIA.Node[] a6 = new com.cim.AIA.Node[i];
        this.xNodes = a6;
        com.cim.AIA.Node[] a7 = new com.cim.AIA.Node[i0];
        this.yNodes = a7;
        this.xSize = i;
        this.ySize = i0;
        this.currentMax = 0;
        this.currentMaxX = -1;
        this.currentMaxY = -1;
        this.setShiftX(1);
        this.setShiftY(1);
        java.awt.Color a8 = this.DEFAULT_HIGHLIGHTCOLOR;
        this.setHighlightColor(a8);
        java.awt.Color a9 = this.DEFAULT_LABELCOLOR;
        this.setLabelColor(a9);
        java.awt.Color a10 = Alignment.DEFAULT_NODE_COLOR;
        this.setNodeBackgroundColor(a10);
        java.awt.Color a11 = Alignment.DEFAULT_HIGHLIGHT_COLOR;
        this.setNodeHighlightColor(a11);
        this.setXLabel(s);
        this.setYLabel(s0);
        this.setArrowHeight(10);
        this.setArrowWidth(10);
        this.setXGap(5);
        this.setYGap(5);
        this.setLocation(0, 0);
        this.setDrawNull(true);
        this.setDrawIndex(true);
        this.setXIndexHighlight(-1);
        this.setYIndexHighlight(-1);
        this.isHighlight = false;
        this.isSelected = false;
        java.awt.Color a12 = this.DEFAULT_SELECTCOLOR;
        this.selectColor = a12;
        this.drawName = false;
        this.drawInternalBox = false;
    }
    
    public void add(AlignmentNode a, int i)
    {
        int i0 = this.xSize;
        int i1 = i % i0;
        int i2 = this.xSize;
        int i3 = i / i2;
        this.add(a, i1, i3);
    }
    
    public void add(AlignmentNode a, int i, int i0)
    {
        int i1 = 0;
        Object a0 = a.getObject();
        label1: {
            label0: {
                int i2 = a0 instanceof Integer?1:0;
                if(i2 == 0)
                {
                    break label0;
                }
                Object a1 = a.getObject();
                Integer dummy = (Integer)a1;
                Integer a2 = (Integer)a1;
                int i3 = a2.intValue();
                i1 = i3;
                break label1;
            }
            String dummy0 = (String)a0;
            String s = (String)a0;
            int i4 = s.equals((Object)"-Inf")?1:0;
            i1 = i4 == 0?5000:-5000;
        }
        int i5 = this.currentMaxX;
        label3: {
            label2: {
                if(i5 == -1)
                {
                    break label2;
                }
                int i6 = this.currentMaxY;
                if(i6 == -1)
                {
                    break label2;
                }
                int i7 = this.currentMax;
                if(i7 > i1)
                {
                    break label3;
                }
            }
            this.currentMax = i1;
            this.currentMaxX = i;
            this.currentMaxY = i0;
        }
        AlignmentNode[][] a3 = this.theMatrix;
        AlignmentNode[] a4 = a3[i];
        a4[i0] = a;
    }
    
    public void add(int i, int i0)
    {
        AlignmentNode a = null;
        label1: {
            label0: {
                if(i != -5000)
                {
                    break label0;
                }
                String s = new String("-Inf");
                AlignmentNode a0 = new AlignmentNode((Object)s, i0);
                a = a0;
                break label1;
            }
            if(i != 5000)
            {
                Integer a1 = new Integer(i);
                AlignmentNode a2 = new AlignmentNode((Object)a1, i0);
                a = a2;
            }
            else
            {
                String s0 = new String("Inf");
                AlignmentNode a3 = new AlignmentNode((Object)s0, i0);
                a = a3;
            }
        }
        java.awt.Color a4 = this.nodeBackgroundColor;
        a.setBackgroundColor(a4);
        java.awt.Color a5 = this.nodeHighlightColor;
        a.setHighlightColor(a5);
        this.add(a, i0);
    }
    
    public void add(int i, int i0, int i1)
    {
        int i2 = this.xSize;
        int i3 = i2 * i1;
        int i4 = i3 + i0;
        this.add(i, i4);
    }
    
    public void draw(java.awt.Graphics a)
    {
        this.saveMatrix();
        int i = this.drawNull?1:0;
        label0: {
            if(i == 0)
            {
                break label0;
            }
            int i0 = 0;
            label1: while(true)
            {
                int i1 = this.ySize;
                if(i0 >= i1)
                {
                    break;
                }
                int i2 = 0;
                while(true)
                {
                    int i3 = this.xSize;
                    if(i2 >= i3)
                    {
                        int i4 = i0 + 1;
                        i0 = i4;
                        continue label1;
                    }
                    AlignmentNode[][] a0 = this.theMatrix;
                    AlignmentNode[] a1 = a0[i2];
                    AlignmentNode a2 = a1[i0];
                    label2: {
                        if(a2 != null)
                        {
                            break label2;
                        }
                        AlignmentNode a3 = new AlignmentNode((Object)"", 0);
                        java.awt.Color a4 = this.nodeBackgroundColor;
                        a3.setBackgroundColor(a4);
                        java.awt.Color a5 = this.nodeHighlightColor;
                        a3.setHighlightColor(a5);
                        int i5 = this.isHighlight?1:0;
                        if(i5 != 0)
                        {
                            a3.highlight();
                        }
                        AlignmentNode[][] a6 = this.theMatrix;
                        AlignmentNode[] a7 = a6[i2];
                        a7[i0] = a3;
                    }
                    int i6 = i2 + 1;
                    i2 = i6;
                }
            }
        }
        this.setAllArrows();
        this.positionXLabel();
        this.positionYLabel();
        this.positionMatrix();
        int i7 = this.isSelected?1:0;
        if(i7 != 0)
        {
            java.awt.Color a8 = this.selectColor;
            a.setColor(a8);
            java.awt.Point a9 = this.location;
            int i8 = a9.x;
            java.awt.Point a10 = this.location;
            int i9 = a10.y;
            int i10 = this.getWidth();
            int i11 = this.getHeight();
            a.drawRect(i8, i9, i10, i11);
            java.awt.Point a11 = this.location;
            int i12 = a11.x;
            int i13 = i12 - 1;
            java.awt.Point a12 = this.location;
            int i14 = a12.y;
            int i15 = i14 - 1;
            int i16 = this.getWidth();
            int i17 = i16 + 2;
            int i18 = this.getHeight();
            int i19 = i18 + 2;
            a.drawRect(i13, i15, i17, i19);
        }
        this.drawXLabel(a);
        this.drawYLabel(a);
        this.drawMatrix(a);
        int i20 = this.drawIndex?1:0;
        if(i20 != 0)
        {
            this.drawXIndex(a);
            this.drawYIndex(a);
        }
        int i21 = this.drawName?1:0;
        if(i21 != 0)
        {
            this.drawMatrixName(a);
        }
        int i22 = this.drawInternalBox?1:0;
        if(i22 != 0)
        {
            this.drawInternalBox(a);
        }
        this.restoreMatrix();
    }
    
    public void draw(java.awt.Graphics a, java.awt.Point a0)
    {
        this.setLocation(a0);
        this.draw(a);
    }
    
    protected void drawInternalBox(java.awt.Graphics a)
    {
        int i = this.internalBoxX;
        int i0 = this.xSize;
        label2: {
            int i1 = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            label1: {
                label0: {
                    if(i >= i0)
                    {
                        break label0;
                    }
                    int i5 = this.internalBoxY;
                    int i6 = this.ySize;
                    if(i5 < i6)
                    {
                        break label1;
                    }
                }
                break label2;
            }
            AlignmentNode[][] a0 = this.theMatrix;
            int i7 = this.internalBoxX;
            AlignmentNode[] a1 = a0[i7];
            int i8 = this.internalBoxY;
            AlignmentNode a2 = a1[i8];
            java.awt.Point a3 = a2.getPosition();
            int i9 = a3.x;
            AlignmentNode[][] a4 = this.theMatrix;
            int i10 = this.internalBoxX;
            AlignmentNode[] a5 = a4[i10];
            int i11 = this.internalBoxY;
            AlignmentNode a6 = a5[i11];
            int i12 = a6.getRealWidth();
            int i13 = i9 + i12;
            AlignmentNode[][] a7 = this.theMatrix;
            int i14 = this.internalBoxX;
            AlignmentNode[] a8 = a7[i14];
            int i15 = this.internalBoxY;
            AlignmentNode a9 = a8[i15];
            java.awt.Point a10 = a9.getPosition();
            int i16 = a10.y;
            AlignmentNode[][] a11 = this.theMatrix;
            int i17 = this.internalBoxX;
            AlignmentNode[] a12 = a11[i17];
            int i18 = this.internalBoxY;
            AlignmentNode a13 = a12[i18];
            int i19 = a13.getRealHeight();
            int i20 = i16 + i19;
            AlignmentNode[][] a14 = this.theMatrix;
            int i21 = this.internalBoxX;
            AlignmentNode[] a15 = a14[i21];
            int i22 = this.internalBoxY;
            AlignmentNode a16 = a15[i22];
            int i23 = a16.getBufferHeight();
            int i24 = i20 - i23;
            int i25 = this.internalBoxX;
            int i26 = this.internalBoxWidth;
            int i27 = i25 - i26;
            if(i27 >= 0)
            {
                int i28 = this.internalBoxX;
                int i29 = this.internalBoxWidth;
                int i30 = i28 - i29;
                i1 = i30;
                i2 = 1;
            }
            else
            {
                i1 = 0;
                i2 = 0;
            }
            int i31 = this.internalBoxY;
            int i32 = this.internalBoxHeight;
            int i33 = i31 - i32;
            if(i33 >= 0)
            {
                int i34 = this.internalBoxY;
                int i35 = this.internalBoxHeight;
                int i36 = i34 - i35;
                i3 = i36;
                i4 = 1;
            }
            else
            {
                i3 = 0;
                i4 = 0;
            }
            AlignmentNode[][] a17 = this.theMatrix;
            AlignmentNode[] a18 = a17[i1];
            AlignmentNode a19 = a18[i3];
            java.awt.Point a20 = a19.getPosition();
            int i37 = a20.x;
            AlignmentNode[][] a21 = this.theMatrix;
            AlignmentNode[] a22 = a21[i1];
            AlignmentNode a23 = a22[i3];
            int i38 = a23.getArrowWidth();
            int i39 = i37 + i38;
            AlignmentNode[][] a24 = this.theMatrix;
            AlignmentNode[] a25 = a24[i1];
            AlignmentNode a26 = a25[i3];
            java.awt.Point a27 = a26.getPosition();
            int i40 = a27.y;
            AlignmentNode[][] a28 = this.theMatrix;
            AlignmentNode[] a29 = a28[i1];
            AlignmentNode a30 = a29[i3];
            int i41 = a30.getArrowHeight();
            int i42 = i40 + i41;
            a.drawLine(i13, i42, i13, i24);
            a.drawLine(i13, i24, i39, i24);
            if(i2 != 0)
            {
                a.drawLine(i39, i24, i39, i42);
            }
            if(i4 != 0)
            {
                a.drawLine(i39, i42, i13, i42);
            }
        }
    }
    
    protected void drawMatrix(java.awt.Graphics a)
    {
        int i = 0;
        label0: while(true)
        {
            int i0 = this.ySize;
            if(i >= i0)
            {
                return;
            }
            int i1 = 0;
            while(true)
            {
                int i2 = this.xSize;
                if(i1 >= i2)
                {
                    int i3 = i + 1;
                    i = i3;
                    continue label0;
                }
                AlignmentNode[][] a0 = this.theMatrix;
                AlignmentNode[] a1 = a0[i1];
                AlignmentNode a2 = a1[i];
                if(a2 != null)
                {
                    a2.draw(a);
                }
                int i4 = i1 + 1;
                i1 = i4;
            }
        }
    }
    
    protected void drawMatrixName(java.awt.Graphics a)
    {
        java.awt.Color a0 = this.DEFAULT_NAMECOLOR;
        a.setColor(a0);
        java.awt.Point a1 = this.location;
        int i = a1.x;
        int i0 = i + 5;
        java.awt.Point a2 = this.location;
        int i1 = a2.y;
        java.awt.FontMetrics a3 = a.getFontMetrics();
        int i2 = a3.getAscent();
        int i3 = i1 + i2;
        String s = this.matrixName;
        a.drawString(s, i0, i3);
    }
    
    protected void drawXIndex(java.awt.Graphics a)
    {
        java.awt.Point a0 = this.location;
        int i = a0.x;
        int i0 = this.nodeWidth;
        int i1 = i + i0;
        int i2 = this.xGap;
        int i3 = i1 + i2;
        int i4 = this.aNodeWidth;
        int i5 = this.nodeWidth;
        int i6 = i4 - i5;
        int i7 = i3 + i6;
        int i8 = this.nodeWidth;
        int i9 = i8 / 2;
        int i10 = i7 + i9;
        java.awt.Point a1 = this.location;
        int i11 = a1.y;
        java.awt.FontMetrics a2 = a.getFontMetrics();
        int i12 = a2.getAscent();
        int i13 = i12 / 2;
        int i14 = i11 + i13;
        int i15 = this.nodeHeight;
        int i16 = i14 + i15;
        int i17 = this.yGap;
        int i18 = i16 + i17;
        int i19 = i10;
        int i20 = 0;
        while(true)
        {
            int i21 = this.xSize;
            if(i20 >= i21)
            {
                return;
            }
            int i22 = this.xIndexHighlight;
            if(i20 == i22)
            {
                java.awt.Color a3 = this.highlightColor;
                a.setColor(a3);
            }
            else
            {
                java.awt.Color a4 = java.awt.Color.black;
                a.setColor(a4);
            }
            StringBuilder a5 = new StringBuilder();
            StringBuilder a6 = a5.append("");
            StringBuilder a7 = a6.append(i20);
            String s = a7.toString();
            java.awt.FontMetrics a8 = a.getFontMetrics();
            StringBuilder a9 = new StringBuilder();
            StringBuilder a10 = a9.append("");
            StringBuilder a11 = a10.append(i20);
            String s0 = a11.toString();
            int i23 = a8.stringWidth(s0);
            int i24 = i23 / 2;
            int i25 = i19 - i24;
            a.drawString(s, i25, i18);
            int i26 = this.aNodeWidth;
            int i27 = i19 + i26;
            int i28 = i20 + 1;
            i19 = i27;
            i20 = i28;
        }
    }
    
    protected void drawXLabel(java.awt.Graphics a)
    {
        int i = 0;
        while(true)
        {
            int i0 = this.xSize;
            if(i >= i0)
            {
                return;
            }
            else
            {
                com.cim.AIA.Node[] a0 = this.xNodes;
                com.cim.AIA.Node a1 = a0[i];
                a1.draw(a);
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    protected void drawYIndex(java.awt.Graphics a)
    {
        java.awt.Point a0 = this.location;
        int i = a0.x;
        int i0 = this.nodeWidth;
        int i1 = i + i0;
        int i2 = this.xGap;
        int i3 = i1 + i2;
        java.awt.Point a1 = this.location;
        int i4 = a1.y;
        int i5 = this.nodeHeight;
        int i6 = i4 + i5;
        int i7 = this.yGap;
        int i8 = i6 + i7;
        int i9 = this.aNodeHeight;
        int i10 = this.nodeHeight;
        int i11 = i9 - i10;
        int i12 = i8 + i11;
        int i13 = this.nodeHeight;
        int i14 = i13 / 2;
        int i15 = i12 + i14;
        java.awt.FontMetrics a2 = a.getFontMetrics();
        int i16 = a2.getAscent();
        int i17 = i16 / 2;
        int i18 = i15 + i17;
        int i19 = i18;
        int i20 = 0;
        while(true)
        {
            int i21 = this.ySize;
            if(i20 >= i21)
            {
                return;
            }
            int i22 = this.yIndexHighlight;
            if(i20 == i22)
            {
                java.awt.Color a3 = this.highlightColor;
                a.setColor(a3);
            }
            else
            {
                java.awt.Color a4 = java.awt.Color.black;
                a.setColor(a4);
            }
            StringBuilder a5 = new StringBuilder();
            StringBuilder a6 = a5.append("");
            StringBuilder a7 = a6.append(i20);
            String s = a7.toString();
            java.awt.FontMetrics a8 = a.getFontMetrics();
            StringBuilder a9 = new StringBuilder();
            StringBuilder a10 = a9.append("");
            StringBuilder a11 = a10.append(i20);
            String s0 = a11.toString();
            int i23 = a8.stringWidth(s0);
            int i24 = i23 / 2;
            int i25 = i3 - i24;
            a.drawString(s, i25, i19);
            int i26 = this.aNodeHeight;
            int i27 = i19 + i26;
            int i28 = i20 + 1;
            i19 = i27;
            i20 = i28;
        }
    }
    
    protected void drawYLabel(java.awt.Graphics a)
    {
        int i = 0;
        while(true)
        {
            int i0 = this.ySize;
            if(i >= i0)
            {
                return;
            }
            else
            {
                com.cim.AIA.Node[] a0 = this.yNodes;
                com.cim.AIA.Node a1 = a0[i];
                a1.draw(a);
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    public boolean equals(com.cim.AIA.Selectable a)
    {
        int i = 0;
        label1: {
            Object a0 = null;
            label0: {
                int i0 = a instanceof AlignmentMatrix?1:0;
                a0 = a;
                if(i0 != 0)
                {
                    break label0;
                }
                i = 0;
                break label1;
            }
            AlignmentMatrix dummy = (AlignmentMatrix)a0;
            AlignmentMatrix a1 = (AlignmentMatrix)a0;
            int i1 = a1.getIdentifier();
            int i2 = this.getIdentifier();
            label2: {
                if(i1 == i2)
                {
                    break label2;
                }
                i = 0;
                break label1;
            }
            int i3 = a1.xSize;
            int i4 = this.xSize;
            label3: {
                if(i3 == i4)
                {
                    break label3;
                }
                i = 0;
                break label1;
            }
            int i5 = a1.ySize;
            int i6 = this.ySize;
            label4: {
                if(i5 == i6)
                {
                    break label4;
                }
                i = 0;
                break label1;
            }
            int i7 = 0;
            label6: while(true)
            {
                int i8 = this.ySize;
                label5: {
                    if(i7 < i8)
                    {
                        break label5;
                    }
                    i = 1;
                    break;
                }
                int i9 = 0;
                while(true)
                {
                    int i10 = this.xSize;
                    if(i9 >= i10)
                    {
                        int i11 = i7 + 1;
                        i7 = i11;
                        continue label6;
                    }
                    AlignmentNode a2 = this.get(i9, i7);
                    AlignmentNode a3 = a1.get(i9, i7);
                    label9: {
                        label8: {
                            label7: {
                                if(a2 == null)
                                {
                                    break label7;
                                }
                                if(a3 == null)
                                {
                                    break label7;
                                }
                                int i12 = a2.equals((com.cim.AIA.Selectable)a3)?1:0;
                                if(i12 != 0)
                                {
                                    break label8;
                                }
                                else
                                {
                                    break label9;
                                }
                            }
                            label11: {
                                label10: {
                                    if(a2 != null)
                                    {
                                        break label10;
                                    }
                                    if(a3 == null)
                                    {
                                        break label11;
                                    }
                                }
                                i = 0;
                                break label6;
                            }
                        }
                        int i13 = i9 + 1;
                        i9 = i13;
                        continue;
                    }
                    i = 0;
                    break label6;
                }
            }
        }
        return i != 0;
    }
    
    public AlignmentNode get(int i)
    {
        AlignmentNode[][] a = this.theMatrix;
        int i0 = this.xSize;
        int i1 = i % i0;
        AlignmentNode[] a0 = a[i1];
        int i2 = this.xSize;
        int i3 = i / i2;
        AlignmentNode a1 = a0[i3];
        return a1;
    }
    
    public AlignmentNode get(int i, int i0)
    {
        AlignmentNode[][] a = this.theMatrix;
        AlignmentNode[] a0 = a[i];
        AlignmentNode a1 = a0[i0];
        return a1;
    }
    
    public int getArrowHeight()
    {
        int i = this.arrowHeight;
        return i;
    }
    
    public int getArrowWidth()
    {
        int i = this.arrowWidth;
        return i;
    }
    
    public boolean getDrawIndex()
    {
        int i = this.drawIndex?1:0;
        return i != 0;
    }
    
    public boolean getDrawNull()
    {
        int i = this.drawNull?1:0;
        return i != 0;
    }
    
    public int getHeight()
    {
        AlignmentNode a = new AlignmentNode((Object)"", 0);
        int i = this.isGapMode?1:0;
        if(i != 0)
        {
            a.setBufferHeight(10);
        }
        com.cim.AIA.Node a0 = new com.cim.AIA.Node((Object)"", 0);
        int i0 = a0.getHeight();
        int i1 = this.yGap;
        int i2 = i0 + i1;
        int i3 = this.ySize;
        int i4 = a.getRealHeight();
        int i5 = i3 * i4;
        int i6 = i2 + i5;
        return i6;
    }
    
    public java.awt.Color getHighlightColor()
    {
        java.awt.Color a = this.highlightColor;
        return a;
    }
    
    public int getIdentifier()
    {
        int i = this.sequenceNumber;
        return i;
    }
    
    public boolean getIsGapMode()
    {
        int i = this.isGapMode?1:0;
        return i != 0;
    }
    
    public com.cim.AIA.Selectable getItemAt(java.awt.Point a)
    {
        int i = 0;
        label2: while(true)
        {
            AlignmentNode a0 = null;
            int i0 = this.ySize;
            label1: {
                label0: {
                    if(i < i0)
                    {
                        break label0;
                    }
                    a0 = null;
                    break label1;
                }
                int i1 = 0;
                while(true)
                {
                    int i2 = this.xSize;
                    if(i1 >= i2)
                    {
                        int i3 = i + 1;
                        i = i3;
                        continue label2;
                    }
                    AlignmentNode[][] a1 = this.theMatrix;
                    AlignmentNode[] a2 = a1[i1];
                    AlignmentNode a3 = a2[i];
                    label3: {
                        if(a3 == null)
                        {
                            break label3;
                        }
                        AlignmentNode[][] a4 = this.theMatrix;
                        AlignmentNode[] a5 = a4[i1];
                        AlignmentNode a6 = a5[i];
                        Object a7 = a6.getItemAt(a);
                        if(a7 != null)
                        {
                            break;
                        }
                    }
                    int i4 = i1 + 1;
                    i1 = i4;
                }
                AlignmentNode[][] a8 = this.theMatrix;
                AlignmentNode[] a9 = a8[i1];
                AlignmentNode a10 = a9[i];
                a0 = a10;
            }
            return (com.cim.AIA.Selectable)a0;
        }
    }
    
    public java.awt.Point getLocation()
    {
        java.awt.Point a = this.location;
        return a;
    }
    
    public int getMax()
    {
        int i = this.currentMax;
        return i;
    }
    
    public int getMaxX()
    {
        int i = this.currentMaxX;
        return i;
    }
    
    public int getMaxY()
    {
        int i = this.currentMaxY;
        return i;
    }
    
    public int getShiftX()
    {
        int i = this.shiftX;
        return i;
    }
    
    public int getShiftY()
    {
        int i = this.shiftY;
        return i;
    }
    
    public int getWidth()
    {
        AlignmentNode a = new AlignmentNode((Object)"", 0);
        com.cim.AIA.Node a0 = new com.cim.AIA.Node((Object)"", 0);
        int i = a0.getWidth();
        int i0 = this.xGap;
        int i1 = i + i0;
        int i2 = this.xSize;
        int i3 = a.getRealWidth();
        int i4 = i2 * i3;
        int i5 = i1 + i4;
        return i5;
    }
    
    public int getXGap()
    {
        int i = this.xGap;
        return i;
    }
    
    public String getXLabel()
    {
        String s = this.xLabel;
        return s;
    }
    
    public int getXSize()
    {
        int i = this.xSize;
        return i;
    }
    
    public int getYGap()
    {
        int i = this.yGap;
        return i;
    }
    
    public String getYLabel()
    {
        String s = this.yLabel;
        return s;
    }
    
    public int getYSize()
    {
        int i = this.ySize;
        return i;
    }
    
    public void highlight()
    {
        int i = 0;
        label0: while(true)
        {
            int i0 = this.ySize;
            if(i >= i0)
            {
                this.isHighlight = true;
                return;
            }
            int i1 = 0;
            while(true)
            {
                int i2 = this.xSize;
                if(i1 >= i2)
                {
                    int i3 = i + 1;
                    i = i3;
                    continue label0;
                }
                AlignmentNode[][] a = this.theMatrix;
                AlignmentNode[] a0 = a[i1];
                AlignmentNode a1 = a0[i];
                if(a1 != null)
                {
                    AlignmentNode[][] a2 = this.theMatrix;
                    AlignmentNode[] a3 = a2[i1];
                    AlignmentNode a4 = a3[i];
                    a4.highlight();
                }
                int i4 = i1 + 1;
                i1 = i4;
            }
        }
    }
    
    protected void positionMatrix()
    {
        java.awt.Point a = this.location;
        int i = a.x;
        int i0 = this.nodeWidth;
        int i1 = i + i0;
        int i2 = this.aNodeWidth;
        int i3 = this.nodeWidth;
        int i4 = i2 - i3;
        int i5 = this.arrowWidth;
        int i6 = i4 - i5;
        int i7 = i1 + i6;
        int i8 = this.xGap;
        int i9 = i7 + i8;
        java.awt.Point a0 = this.location;
        int i10 = a0.y;
        int i11 = this.nodeHeight;
        int i12 = i10 + i11;
        int i13 = this.aNodeHeight;
        int i14 = this.nodeHeight;
        int i15 = i13 - i14;
        int i16 = this.arrowHeight;
        int i17 = i15 - i16;
        int i18 = i12 + i17;
        int i19 = this.yGap;
        int i20 = i18 + i19;
        int i21 = i9;
        int i22 = i20;
        int i23 = 0;
        label0: while(true)
        {
            int i24 = this.ySize;
            if(i23 >= i24)
            {
                return;
            }
            int i25 = i21;
            int i26 = 0;
            while(true)
            {
                int i27 = this.xSize;
                if(i26 >= i27)
                {
                    java.awt.Point a1 = this.location;
                    int i28 = a1.x;
                    int i29 = this.nodeWidth;
                    int i30 = i28 + i29;
                    int i31 = this.aNodeWidth;
                    int i32 = this.nodeWidth;
                    int i33 = i31 - i32;
                    int i34 = this.arrowWidth;
                    int i35 = i33 - i34;
                    int i36 = i30 + i35;
                    int i37 = this.xGap;
                    int i38 = i36 + i37;
                    int i39 = this.aNodeHeight;
                    int i40 = i22 + i39;
                    int i41 = i23 + 1;
                    i21 = i38;
                    i22 = i40;
                    i23 = i41;
                    continue label0;
                }
                AlignmentNode[][] a2 = this.theMatrix;
                AlignmentNode[] a3 = a2[i26];
                AlignmentNode a4 = a3[i23];
                if(a4 != null)
                {
                    a4.setX(i25);
                    a4.setY(i22);
                }
                int i42 = this.aNodeWidth;
                int i43 = i25 + i42;
                int i44 = i26 + 1;
                i25 = i43;
                i26 = i44;
            }
        }
    }
    
    protected void positionXLabel()
    {
        java.awt.Point a = this.location;
        int i = a.y;
        java.awt.Point a0 = this.location;
        int i0 = a0.x;
        int i1 = this.aNodeWidth;
        int i2 = i0 + i1;
        int i3 = this.xGap;
        int i4 = i2 + i3;
        int i5 = i4;
        int i6 = 0;
        while(true)
        {
            int i7 = this.xSize;
            if(i6 >= i7)
            {
                return;
            }
            else
            {
                com.cim.AIA.Node[] a1 = this.xNodes;
                com.cim.AIA.Node a2 = a1[i6];
                java.awt.Point a3 = new java.awt.Point(i5, i);
                a2.setPosition(a3);
                int i8 = this.aNodeWidth;
                int i9 = i5 + i8;
                int i10 = i6 + 1;
                i5 = i9;
                i6 = i10;
            }
        }
    }
    
    protected void positionYLabel()
    {
        java.awt.Point a = this.location;
        int i = a.x;
        java.awt.Point a0 = this.location;
        int i0 = a0.y;
        int i1 = this.aNodeHeight;
        int i2 = i0 + i1;
        int i3 = this.yGap;
        int i4 = i2 + i3;
        int i5 = i4;
        int i6 = 0;
        while(true)
        {
            int i7 = this.ySize;
            if(i6 >= i7)
            {
                return;
            }
            else
            {
                com.cim.AIA.Node[] a1 = this.yNodes;
                com.cim.AIA.Node a2 = a1[i6];
                java.awt.Point a3 = new java.awt.Point(i, i5);
                a2.setPosition(a3);
                int i8 = this.aNodeHeight;
                int i9 = i5 + i8;
                int i10 = i6 + 1;
                i5 = i9;
                i6 = i10;
            }
        }
    }
    
    public void printMatrix()
    {
        java.io.PrintStream a = System.out;
        StringBuilder a0 = new StringBuilder();
        StringBuilder a1 = a0.append("X Label = ");
        String s = this.xLabel;
        StringBuilder a2 = a1.append(s);
        String s0 = a2.toString();
        a.println(s0);
        java.io.PrintStream a3 = System.out;
        StringBuilder a4 = new StringBuilder();
        StringBuilder a5 = a4.append("Y Label = ");
        String s1 = this.yLabel;
        StringBuilder a6 = a5.append(s1);
        String s2 = a6.toString();
        a3.println(s2);
        java.io.PrintStream a7 = System.out;
        StringBuilder a8 = new StringBuilder();
        StringBuilder a9 = a8.append("X Size = ");
        int i = this.xSize;
        StringBuilder a10 = a9.append(i);
        String s3 = a10.toString();
        a7.println(s3);
        java.io.PrintStream a11 = System.out;
        StringBuilder a12 = new StringBuilder();
        StringBuilder a13 = a12.append("Y Size = ");
        int i0 = this.ySize;
        StringBuilder a14 = a13.append(i0);
        String s4 = a14.toString();
        a11.println(s4);
        int i1 = 0;
        label0: while(true)
        {
            int i2 = this.ySize;
            if(i1 >= i2)
            {
                return;
            }
            int i3 = 0;
            while(true)
            {
                int i4 = this.xSize;
                if(i3 >= i4)
                {
                    java.io.PrintStream a15 = System.out;
                    a15.println();
                    int i5 = i1 + 1;
                    i1 = i5;
                    continue label0;
                }
                AlignmentNode a16 = this.get(i3, i1);
                label2: {
                    label1: {
                        if(a16 != null)
                        {
                            break label1;
                        }
                        java.io.PrintStream a17 = System.out;
                        a17.print("   X ");
                        break label2;
                    }
                    int i6 = a16.getTraceUp()?1:0;
                    if(i6 == 0)
                    {
                        java.io.PrintStream a18 = System.out;
                        a18.print(" ");
                    }
                    else
                    {
                        java.io.PrintStream a19 = System.out;
                        a19.print("|");
                    }
                    int i7 = a16.getTraceLeft()?1:0;
                    if(i7 == 0)
                    {
                        java.io.PrintStream a20 = System.out;
                        a20.print(" ");
                    }
                    else
                    {
                        java.io.PrintStream a21 = System.out;
                        a21.print("-");
                    }
                    int i8 = a16.getTraceDiag()?1:0;
                    if(i8 == 0)
                    {
                        java.io.PrintStream a22 = System.out;
                        a22.print(" ");
                    }
                    else
                    {
                        java.io.PrintStream a23 = System.out;
                        a23.print("\\");
                    }
                    java.io.PrintStream a24 = System.out;
                    StringBuilder a25 = new StringBuilder();
                    StringBuilder a26 = a25.append("");
                    int i9 = this.valueAt(i3, i1);
                    StringBuilder a27 = a26.append(i9);
                    StringBuilder a28 = a27.append(" ");
                    String s5 = a28.toString();
                    a24.print(s5);
                }
                int i10 = i3 + 1;
                i3 = i10;
            }
        }
    }
    
    protected void restoreMatrix()
    {
        int i = 0;
        while(true)
        {
            int i0 = this.ySize;
            if(i >= i0)
            {
                return;
            }
            int i1 = 0;
            while(true)
            {
                int i2 = this.xSize;
                if(i1 >= i2)
                {
                    int i3 = i + 1;
                    i = i3;
                }
                else
                {
                    AlignmentNode[][] a = this.theMatrix;
                    AlignmentNode[] a0 = a[i1];
                    AlignmentNode[][] a1 = this.saveMatrix;
                    AlignmentNode[] a2 = a1[i1];
                    AlignmentNode a3 = a2[i];
                    a0[i] = a3;
                    int i4 = i1 + 1;
                    i1 = i4;
                }
            }
        }
    }
    
    protected void saveMatrix()
    {
        int i = 0;
        while(true)
        {
            int i0 = this.ySize;
            if(i >= i0)
            {
                return;
            }
            int i1 = 0;
            while(true)
            {
                int i2 = this.xSize;
                if(i1 >= i2)
                {
                    int i3 = i + 1;
                    i = i3;
                }
                else
                {
                    AlignmentNode[][] a = this.saveMatrix;
                    AlignmentNode[] a0 = a[i1];
                    AlignmentNode[][] a1 = this.theMatrix;
                    AlignmentNode[] a2 = a1[i1];
                    AlignmentNode a3 = a2[i];
                    a0[i] = a3;
                    int i4 = i1 + 1;
                    i1 = i4;
                }
            }
        }
    }
    
    protected void setAllArrows()
    {
        com.cim.AIA.Node a = new com.cim.AIA.Node((Object)"", 0);
        int i = a.getWidth();
        this.nodeWidth = i;
        int i0 = a.getHeight();
        this.nodeHeight = i0;
        int i1 = 0;
        label0: while(true)
        {
            int i2 = this.ySize;
            if(i1 >= i2)
            {
                AlignmentNode[][] a0 = this.theMatrix;
                AlignmentNode[] a1 = a0[0];
                AlignmentNode a2 = a1[0];
                int i3 = a2.getRealHeight();
                this.aNodeHeight = i3;
                int i4 = a2.getRealWidth();
                this.aNodeWidth = i4;
                return;
            }
            int i5 = 0;
            while(true)
            {
                int i6 = this.xSize;
                if(i5 >= i6)
                {
                    int i7 = i1 + 1;
                    i1 = i7;
                    continue label0;
                }
                AlignmentNode[][] a3 = this.theMatrix;
                AlignmentNode[] a4 = a3[i5];
                AlignmentNode a5 = a4[i1];
                label1: {
                    if(a5 == null)
                    {
                        break label1;
                    }
                    int i8 = this.arrowWidth;
                    a5.setArrowWidth(i8);
                    int i9 = this.arrowHeight;
                    a5.setArrowHeight(i9);
                    int i10 = this.isGapMode?1:0;
                    if(i10 != 0)
                    {
                        a5.setHaveChoice(true);
                        a5.setBufferHeight(10);
                    }
                }
                int i11 = i5 + 1;
                i5 = i11;
            }
        }
    }
    
    public void setAllColor(java.awt.Color a, java.awt.Color a0)
    {
        int i = 0;
        label0: while(true)
        {
            int i0 = this.ySize;
            if(i >= i0)
            {
                return;
            }
            int i1 = 0;
            while(true)
            {
                int i2 = this.xSize;
                if(i1 >= i2)
                {
                    int i3 = i + 1;
                    i = i3;
                    continue label0;
                }
                AlignmentNode[][] a1 = this.theMatrix;
                AlignmentNode[] a2 = a1[i1];
                AlignmentNode a3 = a2[i];
                if(a3 != null)
                {
                    AlignmentNode[][] a4 = this.theMatrix;
                    AlignmentNode[] a5 = a4[i1];
                    AlignmentNode a6 = a5[i];
                    a6.setBackgroundColor(a);
                    AlignmentNode[][] a7 = this.theMatrix;
                    AlignmentNode[] a8 = a7[i1];
                    AlignmentNode a9 = a8[i];
                    a9.setArrowColor(a0);
                }
                int i4 = i1 + 1;
                i1 = i4;
            }
        }
    }
    
    public void setArrowHeight(int i)
    {
        this.arrowHeight = i;
    }
    
    public void setArrowWidth(int i)
    {
        this.arrowWidth = i;
    }
    
    public void setDrawIndex(boolean b)
    {
        this.drawIndex = b;
    }
    
    public void setDrawInternalBox(boolean b)
    {
        this.drawInternalBox = b;
    }
    
    public void setDrawName(boolean b)
    {
        this.drawName = b;
    }
    
    public void setDrawNull(boolean b)
    {
        this.drawNull = b;
    }
    
    public void setHighlightColor(java.awt.Color a)
    {
        this.highlightColor = a;
    }
    
    public void setInternalBoxCoords(int i, int i0)
    {
        this.internalBoxX = i;
        this.internalBoxY = i0;
    }
    
    public void setInternalBoxHeight(int i)
    {
        this.internalBoxHeight = i;
    }
    
    public void setInternalBoxSize(int i, int i0)
    {
        this.internalBoxWidth = i;
        this.internalBoxHeight = i0;
    }
    
    public void setInternalBoxWidth(int i)
    {
        this.internalBoxWidth = i;
    }
    
    public void setInternalBoxX(int i)
    {
        this.internalBoxX = i;
    }
    
    public void setInternalBoxY(int i)
    {
        this.internalBoxY = i;
    }
    
    public void setIsGapMode(boolean b)
    {
        this.isGapMode = b;
    }
    
    public void setIsSelected(boolean b)
    {
        this.isSelected = b;
    }
    
    public void setLabelColor(java.awt.Color a)
    {
        this.labelColor = a;
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
    
    public void setMatrixName(String s)
    {
        String s0 = new String(s);
        this.matrixName = s0;
    }
    
    public void setNodeBackgroundColor(java.awt.Color a)
    {
        this.nodeBackgroundColor = a;
    }
    
    public void setNodeHighlightColor(java.awt.Color a)
    {
        this.nodeHighlightColor = a;
    }
    
    public void setSequence(int i)
    {
        this.sequenceNumber = i;
    }
    
    public void setShiftX(int i)
    {
        this.shiftX = i;
    }
    
    public void setShiftY(int i)
    {
        this.shiftY = i;
    }
    
    public void setXGap(int i)
    {
        this.xGap = i;
    }
    
    public void setXIndexHighlight(int i)
    {
        this.xIndexHighlight = i;
    }
    
    public void setXLabel(String s)
    {
        this.xLabel = s;
        int i = 0;
        while(true)
        {
            int i0 = this.shiftX;
            if(i >= i0)
            {
                break;
            }
            else
            {
                com.cim.AIA.Node[] a = this.xNodes;
                com.cim.AIA.Node a0 = new com.cim.AIA.Node((Object)"", 0);
                a[i] = a0;
                com.cim.AIA.Node[] a1 = this.xNodes;
                com.cim.AIA.Node a2 = a1[i];
                java.awt.Color a3 = this.labelColor;
                a2.setBackgroundColor(a3);
                int i1 = i + 1;
                i = i1;
            }
        }
        int i2 = this.shiftX;
        int i3 = i2;
        while(true)
        {
            com.cim.AIA.Node a4 = null;
            int i4 = this.xSize;
            if(i3 >= i4)
            {
                return;
            }
            int i5 = this.shiftX;
            int i6 = i3 - i5;
            int i7 = s.length();
            if(i6 >= i7)
            {
                com.cim.AIA.Node a5 = new com.cim.AIA.Node((Object)"", 0);
                a4 = a5;
            }
            else
            {
                StringBuilder a6 = new StringBuilder();
                StringBuilder a7 = a6.append("");
                int i8 = this.shiftX;
                int i9 = i3 - i8;
                int i10 = s.charAt(i9);
                StringBuilder a8 = a7.append((char)i10);
                String s0 = a8.toString();
                com.cim.AIA.Node a9 = new com.cim.AIA.Node((Object)s0, 0);
                a4 = a9;
            }
            java.awt.Color a10 = this.labelColor;
            a4.setBackgroundColor(a10);
            com.cim.AIA.Node[] a11 = this.xNodes;
            a11[i3] = a4;
            int i11 = i3 + 1;
            i3 = i11;
        }
    }
    
    public void setYGap(int i)
    {
        this.yGap = i;
    }
    
    public void setYIndexHighlight(int i)
    {
        this.yIndexHighlight = i;
    }
    
    public void setYLabel(String s)
    {
        this.yLabel = s;
        int i = 0;
        while(true)
        {
            int i0 = this.shiftY;
            if(i >= i0)
            {
                break;
            }
            else
            {
                com.cim.AIA.Node[] a = this.yNodes;
                com.cim.AIA.Node a0 = new com.cim.AIA.Node((Object)"", 0);
                a[i] = a0;
                com.cim.AIA.Node[] a1 = this.yNodes;
                com.cim.AIA.Node a2 = a1[i];
                java.awt.Color a3 = this.labelColor;
                a2.setBackgroundColor(a3);
                int i1 = i + 1;
                i = i1;
            }
        }
        int i2 = this.shiftY;
        int i3 = i2;
        while(true)
        {
            com.cim.AIA.Node a4 = null;
            int i4 = this.ySize;
            if(i3 >= i4)
            {
                return;
            }
            int i5 = this.shiftY;
            int i6 = i3 - i5;
            int i7 = s.length();
            if(i6 >= i7)
            {
                com.cim.AIA.Node a5 = new com.cim.AIA.Node((Object)"", 0);
                a4 = a5;
            }
            else
            {
                StringBuilder a6 = new StringBuilder();
                StringBuilder a7 = a6.append("");
                int i8 = this.shiftY;
                int i9 = i3 - i8;
                int i10 = s.charAt(i9);
                StringBuilder a8 = a7.append((char)i10);
                String s0 = a8.toString();
                com.cim.AIA.Node a9 = new com.cim.AIA.Node((Object)s0, 0);
                a4 = a9;
            }
            java.awt.Color a10 = this.labelColor;
            a4.setBackgroundColor(a10);
            com.cim.AIA.Node[] a11 = this.yNodes;
            a11[i3] = a4;
            int i11 = i3 + 1;
            i3 = i11;
        }
    }
    
    public void unHighlight()
    {
        int i = 0;
        label0: while(true)
        {
            int i0 = this.ySize;
            if(i >= i0)
            {
                this.isHighlight = false;
                return;
            }
            int i1 = 0;
            while(true)
            {
                int i2 = this.xSize;
                if(i1 >= i2)
                {
                    int i3 = i + 1;
                    i = i3;
                    continue label0;
                }
                AlignmentNode[][] a = this.theMatrix;
                AlignmentNode[] a0 = a[i1];
                AlignmentNode a1 = a0[i];
                if(a1 != null)
                {
                    AlignmentNode[][] a2 = this.theMatrix;
                    AlignmentNode[] a3 = a2[i1];
                    AlignmentNode a4 = a3[i];
                    a4.unHighlight();
                }
                int i4 = i1 + 1;
                i1 = i4;
            }
        }
    }
    
    public void unHighlightTrace()
    {
        int i = 0;
        label0: while(true)
        {
            int i0 = this.ySize;
            if(i >= i0)
            {
                return;
            }
            int i1 = 0;
            while(true)
            {
                int i2 = this.xSize;
                if(i1 >= i2)
                {
                    int i3 = i + 1;
                    i = i3;
                    continue label0;
                }
                AlignmentNode[][] a = this.theMatrix;
                AlignmentNode[] a0 = a[i1];
                AlignmentNode a1 = a0[i];
                if(a1 != null)
                {
                    AlignmentNode[][] a2 = this.theMatrix;
                    AlignmentNode[] a3 = a2[i1];
                    AlignmentNode a4 = a3[i];
                    a4.unHighlightLeft();
                    AlignmentNode[][] a5 = this.theMatrix;
                    AlignmentNode[] a6 = a5[i1];
                    AlignmentNode a7 = a6[i];
                    a7.unHighlightUp();
                    AlignmentNode[][] a8 = this.theMatrix;
                    AlignmentNode[] a9 = a8[i1];
                    AlignmentNode a10 = a9[i];
                    a10.unHighlightDiag();
                    AlignmentNode[][] a11 = this.theMatrix;
                    AlignmentNode[] a12 = a11[i1];
                    AlignmentNode a13 = a12[i];
                    a13.unHighlightA();
                    AlignmentNode[][] a14 = this.theMatrix;
                    AlignmentNode[] a15 = a14[i1];
                    AlignmentNode a16 = a15[i];
                    a16.unHighlightB();
                    AlignmentNode[][] a17 = this.theMatrix;
                    AlignmentNode[] a18 = a17[i1];
                    AlignmentNode a19 = a18[i];
                    a19.unHighlightC();
                    AlignmentNode[][] a20 = this.theMatrix;
                    AlignmentNode[] a21 = a20[i1];
                    AlignmentNode a22 = a21[i];
                    a22.setIsDisabled(false);
                }
                int i4 = i1 + 1;
                i1 = i4;
            }
        }
    }
    
    public int valueAt(int i)
    {
        int i0 = 0;
        AlignmentNode a = this.get(i);
        label1: {
            label0: {
                if(a != null)
                {
                    break label0;
                }
                i0 = 0;
                break label1;
            }
            Object a0 = a.getObject();
            label2: {
                int i1 = a0 instanceof Integer?1:0;
                if(i1 == 0)
                {
                    break label2;
                }
                Object a1 = a.getObject();
                Integer dummy = (Integer)a1;
                Integer a2 = (Integer)a1;
                int i2 = a2.intValue();
                i0 = i2;
                break label1;
            }
            String dummy0 = (String)a0;
            String s = (String)a0;
            int i3 = s.equals((Object)"-Inf")?1:0;
            i0 = i3 == 0?5000:-5000;
        }
        return i0;
    }
    
    public int valueAt(int i, int i0)
    {
        int i1 = 0;
        AlignmentNode a = this.get(i, i0);
        label1: {
            label0: {
                if(a != null)
                {
                    break label0;
                }
                i1 = 0;
                break label1;
            }
            Object a0 = a.getObject();
            label2: {
                int i2 = a0 instanceof Integer?1:0;
                if(i2 == 0)
                {
                    break label2;
                }
                Object a1 = a.getObject();
                Integer dummy = (Integer)a1;
                Integer a2 = (Integer)a1;
                int i3 = a2.intValue();
                i1 = i3;
                break label1;
            }
            String dummy0 = (String)a0;
            String s = (String)a0;
            int i4 = s.equals((Object)"-Inf")?1:0;
            i1 = i4 == 0?5000:-5000;
        }
        return i1;
    }
}