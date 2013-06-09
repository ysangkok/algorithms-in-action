public class AlignmentMinimum implements com.cim.AIA.Drawable {
    final protected int DEFAULT_Y_GAP;
    final protected int DEFAULT_X_GAP;
    final protected int DEFAULT_LABEL_X_GAP;
    final protected int DEFAULT_LINE_WIDTH;
    final protected java.awt.Color DEFAULT_NODE_COLOR;
    final protected java.awt.Color DEFAULT_HIGHLIGHT_COLOR;
    protected String theLabel;
    protected java.util.Vector theData;
    protected java.util.Vector theString;
    protected int yGap;
    protected int xGap;
    protected int labelXGap;
    protected int lineWidth;
    protected java.awt.Color nodeColor;
    protected java.awt.Color highlightColor;
    protected int nodeWidth;
    protected int nodeHeight;
    protected int labelWidth;
    protected java.awt.Point location;
    protected java.awt.Point topLine;
    protected java.awt.Point bottomLine;
    
    public AlignmentMinimum()
    {
        this("");
    }
    
    public AlignmentMinimum(String s)
    {
        super();
        this.DEFAULT_Y_GAP = 5;
        this.DEFAULT_X_GAP = 5;
        this.DEFAULT_LABEL_X_GAP = 2;
        this.DEFAULT_LINE_WIDTH = 4;
        java.awt.Color a = new java.awt.Color(0, 200, 0);
        this.DEFAULT_NODE_COLOR = a;
        java.awt.Color a0 = java.awt.Color.green;
        this.DEFAULT_HIGHLIGHT_COLOR = a0;
        this.yGap = 5;
        this.xGap = 5;
        this.labelXGap = 2;
        this.lineWidth = 4;
        java.awt.Color a1 = this.DEFAULT_NODE_COLOR;
        this.nodeColor = a1;
        java.awt.Color a2 = this.DEFAULT_HIGHLIGHT_COLOR;
        this.highlightColor = a2;
        this.nodeWidth = 0;
        this.nodeHeight = 0;
        java.awt.Point a3 = new java.awt.Point();
        this.location = a3;
        java.awt.Point a4 = new java.awt.Point();
        this.topLine = a4;
        java.awt.Point a5 = new java.awt.Point();
        this.bottomLine = a5;
        java.util.Vector a6 = new java.util.Vector();
        this.theData = a6;
        java.util.Vector a7 = new java.util.Vector();
        this.theString = a7;
        this.theLabel = s;
    }
    
    public void add()
    {
        com.cim.AIA.Node a = new com.cim.AIA.Node((Object)"", 0);
        int i = a.getWidth();
        this.nodeWidth = i;
        this.add(a, "");
    }
    
    public void add(int i, int i0)
    {
        com.cim.AIA.Node a = null;
        String s = null;
        label1: {
            label0: {
                if(i != -5000)
                {
                    break label0;
                }
                String s0 = new String("-Inf");
                com.cim.AIA.Node a0 = new com.cim.AIA.Node((Object)s0, 0);
                if(i0 < 0)
                {
                    StringBuilder a1 = new StringBuilder();
                    StringBuilder a2 = a1.append("- ");
                    int i1 = 0 - i0;
                    StringBuilder a3 = a2.append(i1);
                    StringBuilder a4 = a3.append(" = -Inf");
                    String s1 = a4.toString();
                    a = a0;
                    s = s1;
                    break label1;
                }
                else
                {
                    StringBuilder a5 = new StringBuilder();
                    StringBuilder a6 = a5.append("+ ");
                    StringBuilder a7 = a6.append(i0);
                    StringBuilder a8 = a7.append(" = -Inf");
                    String s2 = a8.toString();
                    a = a0;
                    s = s2;
                    break label1;
                }
            }
            label2: {
                if(i == 5000)
                {
                    break label2;
                }
                Integer a9 = new Integer(i);
                com.cim.AIA.Node a10 = new com.cim.AIA.Node((Object)a9, 0);
                if(i0 < 0)
                {
                    StringBuilder a11 = new StringBuilder();
                    StringBuilder a12 = a11.append("- ");
                    int i2 = 0 - i0;
                    StringBuilder a13 = a12.append(i2);
                    StringBuilder a14 = a13.append(" = ");
                    int i3 = i0 + i;
                    StringBuilder a15 = a14.append(i3);
                    String s3 = a15.toString();
                    a = a10;
                    s = s3;
                    break label1;
                }
                else
                {
                    StringBuilder a16 = new StringBuilder();
                    StringBuilder a17 = a16.append("+ ");
                    StringBuilder a18 = a17.append(i0);
                    StringBuilder a19 = a18.append(" = ");
                    int i4 = i0 + i;
                    StringBuilder a20 = a19.append(i4);
                    String s4 = a20.toString();
                    a = a10;
                    s = s4;
                    break label1;
                }
            }
            String s5 = new String("Inf");
            com.cim.AIA.Node a21 = new com.cim.AIA.Node((Object)s5, 0);
            if(i0 < 0)
            {
                StringBuilder a22 = new StringBuilder();
                StringBuilder a23 = a22.append("- ");
                int i5 = 0 - i0;
                StringBuilder a24 = a23.append(i5);
                StringBuilder a25 = a24.append(" = Inf");
                String s6 = a25.toString();
                a = a21;
                s = s6;
            }
            else
            {
                StringBuilder a26 = new StringBuilder();
                StringBuilder a27 = a26.append("+ ");
                StringBuilder a28 = a27.append(i0);
                StringBuilder a29 = a28.append(" = Inf");
                String s7 = a29.toString();
                a = a21;
                s = s7;
            }
        }
        int i6 = a.getWidth();
        this.nodeWidth = i6;
        int i7 = a.getHeight();
        this.nodeHeight = i7;
        this.add(a, s);
    }
    
    public void add(com.cim.AIA.Node a, String s)
    {
        a.setSequence(0);
        java.awt.Color a0 = this.nodeColor;
        a.setBackgroundColor(a0);
        java.awt.Color a1 = this.highlightColor;
        a.setHighlightColor(a1);
        java.util.Vector a2 = this.theData;
        a2.addElement((Object)a);
        java.util.Vector a3 = this.theString;
        a3.addElement((Object)s);
    }
    
    public void draw(java.awt.Graphics a)
    {
        this.setLabelWidth(a);
        this.positionNodes();
        this.drawNodes(a);
        this.drawStrings(a);
        this.drawLines(a);
        this.drawLabel(a);
    }
    
    public void draw(java.awt.Graphics a, java.awt.Point a0)
    {
        this.setLocation(a0);
        this.draw(a);
    }
    
    protected void drawLabel(java.awt.Graphics a)
    {
        java.util.Vector a0 = this.theData;
        int i = a0.size();
        if(i > 0)
        {
            String s = this.theLabel;
            java.awt.Point a1 = this.location;
            int i0 = a1.x;
            java.awt.Point a2 = this.topLine;
            int i1 = a2.y;
            java.awt.Point a3 = this.bottomLine;
            int i2 = a3.y;
            java.awt.Point a4 = this.topLine;
            int i3 = a4.y;
            int i4 = i2 - i3;
            int i5 = i4 / 2;
            int i6 = i1 + i5;
            java.awt.FontMetrics a5 = a.getFontMetrics();
            int i7 = a5.getAscent();
            int i8 = i7 / 2;
            int i9 = i6 + i8;
            a.drawString(s, i0, i9);
        }
    }
    
    protected void drawLines(java.awt.Graphics a)
    {
        java.awt.Point a0 = this.location;
        int i = a0.x;
        int i0 = this.labelWidth;
        int i1 = i + i0;
        int i2 = this.labelXGap;
        int i3 = i1 + i2;
        java.util.Vector a1 = this.theData;
        int i4 = a1.size();
        label0: {
            if(i4 <= 0)
            {
                break label0;
            }
            java.util.Vector a2 = this.theData;
            Object a3 = a2.elementAt(0);
            com.cim.AIA.Node dummy = (com.cim.AIA.Node)a3;
            com.cim.AIA.Node a4 = (com.cim.AIA.Node)a3;
            java.awt.Point a5 = this.topLine;
            int i5 = a4.getY();
            a5.y = i5;
            java.awt.Point a6 = this.topLine;
            java.awt.Point a7 = this.topLine;
            int i6 = a7.y;
            int i7 = a4.getHeight();
            int i8 = i7 / 2;
            int i9 = i6 + i8;
            a6.y = i9;
            java.util.Vector a8 = this.theData;
            java.util.Vector a9 = this.theData;
            int i10 = a9.size();
            int i11 = i10 - 1;
            Object a10 = a8.elementAt(i11);
            com.cim.AIA.Node dummy0 = (com.cim.AIA.Node)a10;
            com.cim.AIA.Node a11 = (com.cim.AIA.Node)a10;
            java.awt.Point a12 = this.bottomLine;
            int i12 = a11.getY();
            a12.y = i12;
            java.awt.Point a13 = this.bottomLine;
            java.awt.Point a14 = this.bottomLine;
            int i13 = a14.y;
            int i14 = a11.getHeight();
            int i15 = i14 / 2;
            int i16 = i13 + i15;
            a13.y = i16;
            java.awt.Point a15 = this.topLine;
            int i17 = a15.y;
            java.awt.Point a16 = this.bottomLine;
            int i18 = a16.y;
            java.awt.Point a17 = this.topLine;
            int i19 = a17.y;
            int i20 = i18 - i19;
            int i21 = i20 / 2;
            int i22 = i17 + i21;
            int i23 = this.lineWidth;
            int i24 = i3 + i23;
            java.awt.Point a18 = this.topLine;
            int i25 = a18.y;
            java.awt.Point a19 = this.bottomLine;
            int i26 = a19.y;
            java.awt.Point a20 = this.topLine;
            int i27 = a20.y;
            int i28 = i26 - i27;
            int i29 = i28 / 2;
            int i30 = i25 + i29;
            a.drawLine(i3, i22, i24, i30);
            int i31 = this.lineWidth;
            int i32 = i3 + i31;
            java.awt.Point a21 = this.topLine;
            int i33 = a21.y;
            java.awt.Point a22 = this.bottomLine;
            int i34 = a22.y;
            a.drawLine(i32, i33, i32, i34);
            int i35 = 0;
            while(true)
            {
                java.util.Vector a23 = this.theData;
                int i36 = a23.size();
                if(i35 >= i36)
                {
                    break;
                }
                else
                {
                    java.util.Vector a24 = this.theData;
                    Object a25 = a24.elementAt(i35);
                    com.cim.AIA.Node dummy1 = (com.cim.AIA.Node)a25;
                    com.cim.AIA.Node a26 = (com.cim.AIA.Node)a25;
                    int i37 = a26.getY();
                    int i38 = a26.getHeight();
                    int i39 = i38 / 2;
                    int i40 = i37 + i39;
                    int i41 = this.lineWidth;
                    int i42 = i32 + i41;
                    a.drawLine(i32, i40, i42, i40);
                    int i43 = i35 + 1;
                    i35 = i43;
                }
            }
        }
    }
    
    protected void drawNodes(java.awt.Graphics a)
    {
        int i = 0;
        while(true)
        {
            java.util.Vector a0 = this.theData;
            int i0 = a0.size();
            if(i >= i0)
            {
                return;
            }
            java.util.Vector a1 = this.theData;
            Object a2 = a1.elementAt(i);
            com.cim.AIA.Node dummy = (com.cim.AIA.Node)a2;
            com.cim.AIA.Node a3 = (com.cim.AIA.Node)a2;
            int i1 = a3.getIdentifier();
            if(i1 != 1)
            {
                a3.unHighlight();
            }
            else
            {
                a3.highlight();
            }
            a3.draw(a);
            int i2 = i + 1;
            i = i2;
        }
    }
    
    protected void drawStrings(java.awt.Graphics a)
    {
        int i = 0;
        while(true)
        {
            java.util.Vector a0 = this.theString;
            int i0 = a0.size();
            if(i >= i0)
            {
                return;
            }
            java.util.Vector a1 = this.theData;
            Object a2 = a1.elementAt(i);
            com.cim.AIA.Node dummy = (com.cim.AIA.Node)a2;
            com.cim.AIA.Node a3 = (com.cim.AIA.Node)a2;
            java.util.Vector a4 = this.theString;
            Object a5 = a4.elementAt(i);
            String dummy0 = (String)a5;
            String s = (String)a5;
            int i1 = a3.getX();
            int i2 = a3.getY();
            int i3 = a3.getHeight();
            int i4 = i3 / 2;
            int i5 = i2 + i4;
            int i6 = a3.getIdentifier();
            if(i6 == 1)
            {
                java.awt.Color a6 = this.highlightColor;
                a.setColor(a6);
                int i7 = this.nodeWidth;
                int i8 = i1 + i7;
                int i9 = this.xGap;
                int i10 = i8 + i9;
                java.awt.FontMetrics a7 = a.getFontMetrics();
                int i11 = a7.getAscent();
                int i12 = i11 / 2;
                int i13 = i5 - i12;
                java.awt.FontMetrics a8 = a.getFontMetrics();
                int i14 = a8.stringWidth(s);
                java.awt.FontMetrics a9 = a.getFontMetrics();
                int i15 = a9.getAscent();
                a.fillRect(i10, i13, i14, i15);
            }
            java.awt.Color a10 = java.awt.Color.black;
            a.setColor(a10);
            int i16 = this.nodeWidth;
            int i17 = i1 + i16;
            int i18 = this.xGap;
            int i19 = i17 + i18;
            java.awt.FontMetrics a11 = a.getFontMetrics();
            int i20 = a11.getAscent();
            int i21 = i20 / 2;
            int i22 = i5 + i21;
            a.drawString(s, i19, i22);
            int i23 = i + 1;
            i = i23;
        }
    }
    
    public int getHeight()
    {
        int i = this.nodeHeight;
        java.util.Vector a = this.theData;
        int i0 = a.size();
        int i1 = i * i0;
        int i2 = this.yGap;
        java.util.Vector a0 = this.theData;
        int i3 = a0.size();
        int i4 = i3 - 1;
        int i5 = i2 * i4;
        int i6 = i1 + i5;
        return i6;
    }
    
    public java.awt.Point getLocation()
    {
        java.awt.Point a = this.location;
        return a;
    }
    
    public int getSize()
    {
        java.util.Vector a = this.theData;
        int i = a.size();
        return i;
    }
    
    public void highlight(int i)
    {
        java.util.Vector a = this.theData;
        Object a0 = a.elementAt(i);
        com.cim.AIA.Node dummy = (com.cim.AIA.Node)a0;
        com.cim.AIA.Node a1 = (com.cim.AIA.Node)a0;
        a1.setSequence(1);
    }
    
    protected void positionNodes()
    {
        java.awt.Point a = this.location;
        int i = a.x;
        java.awt.Point a0 = this.location;
        int i0 = a0.y;
        int i1 = 0;
        while(true)
        {
            java.util.Vector a1 = this.theData;
            int i2 = a1.size();
            if(i1 >= i2)
            {
                return;
            }
            else
            {
                java.util.Vector a2 = this.theData;
                Object a3 = a2.elementAt(i1);
                com.cim.AIA.Node dummy = (com.cim.AIA.Node)a3;
                com.cim.AIA.Node a4 = (com.cim.AIA.Node)a3;
                int i3 = this.labelWidth;
                int i4 = i + i3;
                int i5 = this.labelXGap;
                int i6 = i4 + i5;
                int i7 = this.lineWidth;
                int i8 = i6 + i7;
                int i9 = this.lineWidth;
                int i10 = i8 + i9;
                int i11 = this.xGap;
                int i12 = i10 + i11;
                a4.setX(i12);
                int i13 = this.yGap;
                int i14 = a4.getHeight();
                int i15 = i13 + i14;
                int i16 = i1 * i15;
                int i17 = i0 + i16;
                a4.setY(i17);
                int i18 = i1 + 1;
                i1 = i18;
            }
        }
    }
    
    public void set(int i)
    {
        com.cim.AIA.Node a = new com.cim.AIA.Node((Object)"", 0);
        int i0 = a.getWidth();
        this.nodeWidth = i0;
        this.set(i, a, "");
    }
    
    public void set(int i, int i0, int i1)
    {
        com.cim.AIA.Node a = null;
        String s = null;
        label1: {
            label0: {
                if(i0 != -5000)
                {
                    break label0;
                }
                String s0 = new String("-Inf");
                com.cim.AIA.Node a0 = new com.cim.AIA.Node((Object)s0, 0);
                if(i1 < 0)
                {
                    StringBuilder a1 = new StringBuilder();
                    StringBuilder a2 = a1.append("- ");
                    int i2 = 0 - i1;
                    StringBuilder a3 = a2.append(i2);
                    StringBuilder a4 = a3.append(" = -Inf");
                    String s1 = a4.toString();
                    a = a0;
                    s = s1;
                    break label1;
                }
                else
                {
                    StringBuilder a5 = new StringBuilder();
                    StringBuilder a6 = a5.append("+ ");
                    StringBuilder a7 = a6.append(i1);
                    StringBuilder a8 = a7.append(" = -Inf");
                    String s2 = a8.toString();
                    a = a0;
                    s = s2;
                    break label1;
                }
            }
            label2: {
                if(i0 == 5000)
                {
                    break label2;
                }
                Integer a9 = new Integer(i0);
                com.cim.AIA.Node a10 = new com.cim.AIA.Node((Object)a9, 0);
                if(i1 < 0)
                {
                    StringBuilder a11 = new StringBuilder();
                    StringBuilder a12 = a11.append("- ");
                    int i3 = 0 - i1;
                    StringBuilder a13 = a12.append(i3);
                    StringBuilder a14 = a13.append(" = ");
                    int i4 = i1 + i0;
                    StringBuilder a15 = a14.append(i4);
                    String s3 = a15.toString();
                    a = a10;
                    s = s3;
                    break label1;
                }
                else
                {
                    StringBuilder a16 = new StringBuilder();
                    StringBuilder a17 = a16.append("+ ");
                    StringBuilder a18 = a17.append(i1);
                    StringBuilder a19 = a18.append(" = ");
                    int i5 = i1 + i0;
                    StringBuilder a20 = a19.append(i5);
                    String s4 = a20.toString();
                    a = a10;
                    s = s4;
                    break label1;
                }
            }
            String s5 = new String("Inf");
            com.cim.AIA.Node a21 = new com.cim.AIA.Node((Object)s5, 0);
            if(i1 < 0)
            {
                StringBuilder a22 = new StringBuilder();
                StringBuilder a23 = a22.append("- ");
                int i6 = 0 - i1;
                StringBuilder a24 = a23.append(i6);
                StringBuilder a25 = a24.append(" = Inf");
                String s6 = a25.toString();
                a = a21;
                s = s6;
            }
            else
            {
                StringBuilder a26 = new StringBuilder();
                StringBuilder a27 = a26.append("+ ");
                StringBuilder a28 = a27.append(i1);
                StringBuilder a29 = a28.append(" = Inf");
                String s7 = a29.toString();
                a = a21;
                s = s7;
            }
        }
        int i7 = a.getWidth();
        this.nodeWidth = i7;
        int i8 = a.getHeight();
        this.nodeHeight = i8;
        this.set(i, a, s);
    }
    
    public void set(int i, com.cim.AIA.Node a, String s)
    {
        a.setSequence(0);
        java.awt.Color a0 = this.nodeColor;
        a.setBackgroundColor(a0);
        java.awt.Color a1 = this.highlightColor;
        a.setHighlightColor(a1);
        java.util.Vector a2 = this.theData;
        a2.setElementAt((Object)a, i);
        java.util.Vector a3 = this.theString;
        a3.setElementAt((Object)s, i);
    }
    
    protected void setLabelWidth(java.awt.Graphics a)
    {
        java.awt.FontMetrics a0 = a.getFontMetrics();
        String s = this.theLabel;
        int i = a0.stringWidth(s);
        this.labelWidth = i;
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
    
    public void unHighlight()
    {
        int i = 0;
        while(true)
        {
            java.util.Vector a = this.theData;
            int i0 = a.size();
            if(i >= i0)
            {
                return;
            }
            else
            {
                java.util.Vector a0 = this.theData;
                Object a1 = a0.elementAt(i);
                com.cim.AIA.Node dummy = (com.cim.AIA.Node)a1;
                com.cim.AIA.Node a2 = (com.cim.AIA.Node)a1;
                a2.setSequence(0);
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    public void unHighlight(int i)
    {
        java.util.Vector a = this.theData;
        Object a0 = a.elementAt(i);
        com.cim.AIA.Node dummy = (com.cim.AIA.Node)a0;
        com.cim.AIA.Node a1 = (com.cim.AIA.Node)a0;
        a1.setSequence(0);
    }
}