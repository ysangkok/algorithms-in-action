package aia.graph.common;

abstract public class GraphMapsCanvasExt extends com.cim.AIA.AlgorithmCanvas implements aia.graph.common.GraphDialogEventHandler {
    public static int QUEUE_X;
    public static int QUEUE_Y;
    final public static int NODE_WIDTH = 20;
    final public static int NODE_HEIGHT = 20;
    protected aia.graph.common.GraphMaps graphMaps;
    protected aia.graph.common.GraphCanvasCommonExt commons;
    protected int MATRIX_X;
    protected int MATRIX_Y;
    protected int STRUCTURE_X;
    protected int STRUCTURE_Y;
    protected int LINK_LENGTH;
    protected com.cim.AIA.ElementArray earrSeqArray;
    protected int SEQ_ARRAY_X;
    protected int SEQ_ARRAY_Y;
    protected int SEQ_NUM_X;
    protected int SEQ_NUM_Y;
    protected com.cim.AIA.ElementArray earrQueue;
    protected int SAMP_QUEUE_MEM_X;
    protected int SAMP_QUEUE_MEM_Y;
    protected java.awt.Font normalFont;
    protected java.awt.Color textColor;
    
    public GraphMapsCanvasExt()
    {
        super();
        this.commons = null;
        this.MATRIX_X = 50;
        this.MATRIX_Y = 182;
        this.STRUCTURE_X = 30;
        this.STRUCTURE_Y = 182;
        this.LINK_LENGTH = 20;
        this.earrSeqArray = null;
        this.SEQ_ARRAY_X = 140;
        this.SEQ_ARRAY_Y = 72;
        this.SEQ_NUM_X = 140;
        this.SEQ_NUM_Y = 142;
        this.earrQueue = null;
        this.SAMP_QUEUE_MEM_X = 180;
        this.SAMP_QUEUE_MEM_Y = 7;
    }
    
    public void displayAlgorithm(java.awt.Graphics a)
    {
        java.awt.Font a0 = this.normalFont;
        if(a0 != null)
        {
            java.awt.Font a1 = this.normalFont;
            a.setFont(a1);
        }
        java.awt.Color a2 = this.textColor;
        if(a2 != null)
        {
            java.awt.Color a3 = this.textColor;
            a.setColor(a3);
        }
        aia.graph.common.GraphCanvasCommonExt a4 = this.commons;
        if(a4 != null)
        {
            aia.graph.common.GraphCanvasCommonExt a5 = this.commons;
            a5.drawAdjacencyMatrix(a);
            aia.graph.common.GraphCanvasCommonExt a6 = this.commons;
            a6.drawAdjacencyStructure(a);
            this.drawCurrentQueueNode(a);
            this.drawQueue(a);
            this.drawSeqArray(a);
            this.drawSeqNumber(a);
        }
    }
    
    private void drawAdjacencyStructure(java.awt.Graphics a)
    {
    }
    
    private void drawAdjacencyMatrix(java.awt.Graphics a)
    {
    }
    
    public void drawCurrentQueueNode(java.awt.Graphics a)
    {
        aia.graph.common.GraphMaps a0 = this.graphMaps;
        label1: {
            label0: {
                if(a0 != null)
                {
                    break label0;
                }
                break label1;
            }
            aia.graph.common.GraphMaps a1 = this.graphMaps;
            aia.graph.common.GraphMapsNode a2 = a1.getCurrentQueueNode();
            if(a2 != null)
            {
                int i = aia.graph.common.GraphMapsCanvasExt.QUEUE_X;
                int i0 = i - 50;
                int i1 = aia.graph.common.GraphMapsCanvasExt.QUEUE_Y;
                java.awt.Point a3 = new java.awt.Point(i0, i1);
                a2.setPosition(a3);
                a2.draw(a);
            }
        }
    }
    
    public void drawMatrixAssociatedElements(java.awt.Graphics a, com.cim.AIA.ElementArray a0, int i)
    {
    }
    
    private void drawQueue(java.awt.Graphics a)
    {
        java.awt.FontMetrics a0 = a.getFontMetrics();
        java.awt.Color a1 = java.awt.Color.black;
        a.setColor(a1);
        String s = localization.Messages.getString("GraphMapsCanvasExt.2");
        int i = aia.graph.common.GraphMapsCanvasExt.QUEUE_X;
        int i0 = aia.graph.common.GraphMapsCanvasExt.QUEUE_Y;
        int i1 = i0 + 20;
        int i2 = a0.getMaxDescent();
        int i3 = i1 - i2;
        a.drawString(s, i, i3);
        com.cim.AIA.ElementArray a2 = this.earrQueue;
        label0: {
            if(a2 == null)
            {
                break label0;
            }
            com.cim.AIA.ElementArray a3 = this.earrQueue;
            a3.draw(a);
            aia.graph.common.GraphMaps a4 = this.graphMaps;
            if(a4 == null)
            {
                break label0;
            }
            aia.graph.common.GraphMaps a5 = this.graphMaps;
            int i4 = a5.isQueueOverFlow()?1:0;
            if(i4 != 0)
            {
                java.awt.Color a6 = java.awt.Color.black;
                a.setColor(a6);
                com.cim.AIA.ElementArray a7 = this.earrQueue;
                java.awt.Point a8 = a7.getLocation();
                int i5 = a8.x;
                com.cim.AIA.ElementArray a9 = this.earrQueue;
                int i6 = a9.getWidth();
                int i7 = i5 + i6;
                com.cim.AIA.ElementArray a10 = this.earrQueue;
                int i8 = a10.getElementWidth();
                int i9 = i7 - i8;
                int i10 = i9 + 10;
                com.cim.AIA.ElementArray a11 = this.earrQueue;
                java.awt.Point a12 = a11.getLocation();
                int i11 = a12.y;
                int i12 = i11 + 20;
                a.drawString("...", i10, i12);
            }
        }
    }
    
    private void drawSeqArray(java.awt.Graphics a)
    {
        java.awt.FontMetrics a0 = a.getFontMetrics();
        java.awt.Color a1 = java.awt.Color.black;
        a.setColor(a1);
        String s = localization.Messages.getString("GraphMapsCanvasExt.4");
        int i = this.SEQ_ARRAY_X;
        String s0 = localization.Messages.getString("GraphMapsCanvasExt.5");
        int i0 = a0.stringWidth(s0);
        int i1 = i - i0;
        int i2 = this.SEQ_ARRAY_Y;
        int i3 = i2 + 20;
        int i4 = a0.getMaxDescent();
        int i5 = i3 - i4;
        a.drawString(s, i1, i5);
        com.cim.AIA.ElementArray a2 = this.earrSeqArray;
        label0: {
            int i6 = 0;
            if(a2 == null)
            {
                break label0;
            }
            com.cim.AIA.ElementArray a3 = this.earrSeqArray;
            a3.draw(a);
            java.awt.Color a4 = java.awt.Color.black;
            a.setColor(a4);
            int i7 = 0;
            while(true)
            {
                com.cim.AIA.ElementArray a5 = this.earrSeqArray;
                int i8 = a5.getSize();
                if(i7 >= i8)
                {
                    break;
                }
                else
                {
                    com.cim.AIA.ElementArray a6 = this.earrSeqArray;
                    com.cim.AIA.Element a7 = a6.getElement(i7);
                    aia.graph.common.GraphMapsNode dummy = (aia.graph.common.GraphMapsNode)a7;
                    aia.graph.common.GraphMapsNode a8 = (aia.graph.common.GraphMapsNode)a7;
                    String s1 = Integer.toString(i7);
                    int i9 = a8.getX();
                    String s2 = Integer.toString(i7);
                    int i10 = a0.stringWidth(s2);
                    int i11 = 20 - i10;
                    int i12 = i11 / 2;
                    int i13 = i9 + i12;
                    int i14 = a8.getY();
                    int i15 = a0.getMaxDescent();
                    int i16 = i14 - i15;
                    a.drawString(s1, i13, i16);
                    int i17 = i7 + 1;
                    i7 = i17;
                }
            }
            int i18 = 0;
            while(true)
            {
                com.cim.AIA.ElementArray a9 = this.earrSeqArray;
                int i19 = a9.getSize();
                if(i18 >= i19)
                {
                    break;
                }
                else
                {
                    com.cim.AIA.ElementArray a10 = this.earrSeqArray;
                    com.cim.AIA.Element a11 = a10.getElement(i18);
                    aia.graph.common.GraphMapsNode dummy0 = (aia.graph.common.GraphMapsNode)a11;
                    aia.graph.common.GraphMapsNode a12 = (aia.graph.common.GraphMapsNode)a11;
                    aia.graph.common.GraphMaps a13 = this.graphMaps;
                    String s3 = a13.getVisitStatus(i18);
                    int i20 = a12.getX();
                    int i21 = a0.stringWidth(s3);
                    int i22 = 20 - i21;
                    int i23 = i22 / 2;
                    int i24 = i20 + i23;
                    int i25 = a12.getY();
                    int i26 = i25 + 20;
                    int i27 = a0.getHeight();
                    int i28 = i26 + i27;
                    a.drawString(s3, i24, i28);
                    int i29 = i18 + 1;
                    i18 = i29;
                }
            }
            aia.graph.common.GraphMaps a14 = this.graphMaps;
            int i30 = a14.getKMarker();
            if(i30 == -10)
            {
                i6 = -1;
            }
            else
            {
                aia.graph.common.GraphMaps a15 = this.graphMaps;
                int i31 = a15.getKMarker();
                com.cim.AIA.ElementArray a16 = this.earrSeqArray;
                com.cim.AIA.Element a17 = a16.getElement(i31);
                aia.graph.common.GraphMapsNode dummy1 = (aia.graph.common.GraphMapsNode)a17;
                aia.graph.common.GraphMapsNode a18 = (aia.graph.common.GraphMapsNode)a17;
                java.awt.Color a19 = java.awt.Color.black;
                a.setColor(a19);
                int i32 = a18.getX();
                int i33 = i32 + 10;
                int i34 = a18.getY();
                int i35 = i34 + 20;
                int i36 = i35 + 10;
                int i37 = a0.getHeight();
                int i38 = i37 * 2;
                int i39 = i36 + i38;
                a.drawString("k", i33, i39);
                int i40 = a18.getX();
                int i41 = i40 + 10;
                int i42 = a18.getY();
                int i43 = i42 + 20;
                int i44 = i43 + 10;
                int i45 = a0.getHeight();
                int i46 = i44 + i45;
                int i47 = a18.getX();
                int i48 = i47 + 10;
                int i49 = a18.getY();
                int i50 = i49 + 20;
                int i51 = a0.getHeight();
                int i52 = i50 + i51;
                com.cim.AIA.Line a20 = new com.cim.AIA.Line(i41, i46, i48, i52);
                a20.showArrowHead(true);
                a20.setDistanceFromStartForArrowHead(-3);
                a20.draw(a);
                i6 = i31;
            }
            aia.graph.common.GraphMaps a21 = this.graphMaps;
            aia.graph.common.GraphMapsNode a22 = a21.getAdjNode();
            if(a22 == null)
            {
                break label0;
            }
            aia.graph.common.GraphMaps a23 = this.graphMaps;
            aia.graph.common.GraphMapsNode a24 = a23.getAdjNode();
            int i53 = a24.getTo();
            if(i53 < 0)
            {
                break label0;
            }
            com.cim.AIA.ElementArray a25 = this.earrSeqArray;
            aia.graph.common.GraphMaps a26 = this.graphMaps;
            aia.graph.common.GraphMapsNode a27 = a26.getAdjNode();
            int i54 = a27.getTo();
            com.cim.AIA.Element a28 = a25.getElement(i54);
            aia.graph.common.GraphMapsNode dummy2 = (aia.graph.common.GraphMapsNode)a28;
            aia.graph.common.GraphMapsNode a29 = (aia.graph.common.GraphMapsNode)a28;
            java.awt.Color a30 = java.awt.Color.black;
            a.setColor(a30);
            label3: {
                label2: {
                    label1: {
                        if(i6 == -1)
                        {
                            break label1;
                        }
                        aia.graph.common.GraphMaps a31 = this.graphMaps;
                        aia.graph.common.GraphMapsNode a32 = a31.getAdjNode();
                        int i55 = a32.getTo();
                        if(i6 == i55)
                        {
                            break label2;
                        }
                    }
                    int i56 = a29.getX();
                    int i57 = i56 + 10;
                    int i58 = a29.getY();
                    int i59 = i58 + 20;
                    int i60 = i59 + 10;
                    int i61 = a0.getHeight();
                    int i62 = i61 * 2;
                    int i63 = i60 + i62;
                    a.drawString("a", i57, i63);
                    break label3;
                }
                int i64 = a29.getX();
                int i65 = i64 + 10;
                int i66 = a0.stringWidth("k");
                int i67 = i65 + i66;
                int i68 = a29.getY();
                int i69 = i68 + 20;
                int i70 = i69 + 10;
                int i71 = a0.getHeight();
                int i72 = i71 * 2;
                int i73 = i70 + i72;
                a.drawString(",a", i67, i73);
            }
            int i74 = a29.getX();
            int i75 = i74 + 10;
            int i76 = a29.getY();
            int i77 = i76 + 20;
            int i78 = i77 + 10;
            int i79 = a0.getHeight();
            int i80 = i78 + i79;
            int i81 = a29.getX();
            int i82 = i81 + 10;
            int i83 = a29.getY();
            int i84 = i83 + 20;
            int i85 = a0.getHeight();
            int i86 = i84 + i85;
            com.cim.AIA.Line a33 = new com.cim.AIA.Line(i75, i80, i82, i86);
            a33.showArrowHead(true);
            a33.setDistanceFromStartForArrowHead(-3);
            a33.draw(a);
        }
    }
    
    private void drawSeqNumber(java.awt.Graphics a)
    {
        java.awt.FontMetrics a0 = a.getFontMetrics();
        java.awt.Color a1 = java.awt.Color.black;
        a.setColor(a1);
        aia.graph.common.GraphMaps a2 = this.graphMaps;
        label1: {
            label0: {
                if(a2 != null)
                {
                    break label0;
                }
                break label1;
            }
            aia.graph.common.GraphMaps a3 = this.graphMaps;
            int i = a3.getSeqNumber();
            if(i != -10)
            {
                StringBuilder a4 = new StringBuilder();
                String s = localization.Messages.getString("GraphMapsCanvasExt.0");
                StringBuilder a5 = a4.append(s);
                String s0 = Integer.toString(i);
                StringBuilder a6 = a5.append(s0);
                String s1 = a6.toString();
                int i0 = this.SEQ_NUM_X;
                String s2 = localization.Messages.getString("GraphMapsCanvasExt.1");
                int i1 = a0.stringWidth(s2);
                int i2 = i0 - i1;
                int i3 = this.SEQ_NUM_Y;
                a.drawString(s1, i2, i3);
            }
        }
    }
    
    public void drawStructureAssociatedElements(java.awt.Graphics a, aia.graph.common.GraphMapsNode a0, aia.graph.common.GraphMapsNode a1)
    {
    }
    
    public void editMatrix(aia.graph.common.GraphMapsNode a)
    {
        aia.graph.common.GraphCanvasCommonExt a0 = this.commons;
        a0.editMatrix(a);
    }
    
    public java.awt.Color getTextColor()
    {
        java.awt.Color a = this.textColor;
        return a;
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
    
    public void processDialogEvent(String s, String s0)
    {
        aia.graph.common.GraphCanvasCommonExt a = this.commons;
        a.processDialogEvent(s, s0);
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
            aia.graph.common.GraphMaps dummy = (aia.graph.common.GraphMaps)a2;
            aia.graph.common.GraphMaps a3 = (aia.graph.common.GraphMaps)a2;
            this.graphMaps = a3;
            aia.graph.common.GraphCanvasCommonExt a4 = this.commons;
            if(a4 == null)
            {
                aia.graph.common.GraphMaps a5 = this.graphMaps;
                aia.graph.common.GraphCanvasCommonExt a6 = new aia.graph.common.GraphCanvasCommonExt(this, a5);
                this.commons = a6;
            }
            this.removeAllSelectables();
            aia.graph.common.GraphCanvasCommonExt a7 = this.commons;
            aia.graph.common.GraphMaps a8 = this.graphMaps;
            java.util.Vector a9 = a8.getAdjacencyMatrix();
            int i = this.MATRIX_X;
            int i0 = this.MATRIX_Y;
            a7.setMatrix(a9, i, i0);
            aia.graph.common.GraphCanvasCommonExt a10 = this.commons;
            aia.graph.common.GraphMaps a11 = this.graphMaps;
            com.cim.AIA.ElementArray a12 = a11.getAdjacencyStructure();
            int i1 = this.STRUCTURE_X;
            int i2 = this.STRUCTURE_Y;
            int i3 = this.LINK_LENGTH;
            a10.setStructure(a12, i1, i2, i3);
            aia.graph.common.GraphMaps a13 = this.graphMaps;
            com.cim.AIA.ElementArray a14 = a13.getQueue();
            this.earrQueue = a14;
            com.cim.AIA.ElementArray a15 = this.earrQueue;
            if(a15 != null)
            {
                com.cim.AIA.ElementArray a16 = this.earrQueue;
                int i4 = aia.graph.common.GraphMapsCanvasExt.QUEUE_X;
                int i5 = i4 + 40;
                int i6 = aia.graph.common.GraphMapsCanvasExt.QUEUE_Y;
                a16.setLocation(i5, i6);
            }
            aia.graph.common.GraphCanvasCommonExt a17 = this.commons;
            a17.adjustCanvasSize();
            aia.graph.common.GraphMaps a18 = this.graphMaps;
            com.cim.AIA.ElementArray a19 = a18.getSeqArray();
            this.earrSeqArray = a19;
            com.cim.AIA.ElementArray a20 = this.earrSeqArray;
            label1: {
                if(a20 == null)
                {
                    break label1;
                }
                com.cim.AIA.ElementArray a21 = this.earrSeqArray;
                int i7 = this.SEQ_ARRAY_X;
                int i8 = this.SEQ_ARRAY_Y;
                a21.setLocation(i7, i8);
                com.cim.AIA.ElementArray a22 = this.earrSeqArray;
                int i9 = a22.getWidth();
                int i10 = this.SEQ_ARRAY_X;
                int i11 = i9 + i10;
                java.awt.Dimension a23 = this.getSize();
                int i12 = a23.width;
                if(i11 > i12)
                {
                    com.cim.AIA.ElementArray a24 = this.earrSeqArray;
                    int i13 = a24.getWidth();
                    int i14 = this.SEQ_ARRAY_X;
                    int i15 = i13 + i14;
                    java.awt.Dimension a25 = this.getSize();
                    int i16 = a25.height;
                    this.setSize(i15, i16);
                }
            }
            aia.graph.common.GraphMaps a26 = this.graphMaps;
            com.cim.AIA.ElementArray a27 = this.earrQueue;
            int i17 = this.numberOfTweenSteps;
            com.cim.AIA.MultipleTween a28 = a26.generateTweens((com.cim.AIA.Paintable)this, (Object)a27, i17);
            if(a28 != null)
            {
                this.addTween((com.cim.AIA.Tween)a28);
            }
            com.cim.AIA.MultipleTween a29 = this.tweens;
            int i18 = a29.getNumberOfTweens();
            if(i18 > 0)
            {
                com.cim.AIA.MultipleTween a30 = this.tweens;
                a30.run();
            }
            aia.graph.common.GraphMaps a31 = this.graphMaps;
            a31.removeAllAnimationRequests();
            this.repaint();
        }
    }
    
    private void adjustCanvasSize()
    {
    }
    
    private void setStructure(com.cim.AIA.ElementArray a, int i, int i0, int i1)
    {
    }
    
    private void setMatrix(java.util.Vector a, int i, int i0)
    {
    }
    
    public void setStructureNodeAppearance(aia.graph.common.GraphMapsNode a, aia.graph.common.GraphMapsNode a0)
    {
        aia.graph.common.GraphMaps a1 = this.graphMaps;
        label1: {
            label0: {
                if(a1 != null)
                {
                    break label0;
                }
                break label1;
            }
            int i = a.getIntValue();
            label3: {
                label2: {
                    if(i != -1)
                    {
                        break label2;
                    }
                    java.awt.Color a2 = java.awt.Color.white;
                    a.setBackgroundColor(a2);
                    java.awt.Color a3 = java.awt.Color.white;
                    a.setTextColor(a3);
                    break label3;
                }
                aia.graph.common.GraphMaps a4 = this.graphMaps;
                java.awt.Color a5 = a4.getUnvisitedNodeColor();
                a.setBackgroundColor(a5);
                aia.graph.common.GraphMaps a6 = this.graphMaps;
                java.awt.Color a7 = a6.getTextColor();
                a.setTextColor(a7);
                aia.graph.common.GraphMaps a8 = this.graphMaps;
                aia.graph.common.GraphMapsNode a9 = a8.getAdjNode();
                label4: {
                    if(a9 == null)
                    {
                        break label4;
                    }
                    int i0 = a9.getFrom();
                    int i1 = a0.getIntValue();
                    label5: {
                        if(i0 != i1)
                        {
                            break label5;
                        }
                        int i2 = a9.getTo();
                        int i3 = a.getIntValue();
                        if(i2 != i3)
                        {
                            break label5;
                        }
                        aia.graph.common.GraphMaps a10 = this.graphMaps;
                        java.awt.Color a11 = a10.getAdjacentNodeColor();
                        a.setBackgroundColor(a11);
                        break label4;
                    }
                    int i4 = a9.getFrom();
                    int i5 = a0.getIntValue();
                    if(i4 == i5)
                    {
                        aia.graph.common.GraphMaps a12 = this.graphMaps;
                        java.awt.Color a13 = a12.getAdjacentNodesColor();
                        a.setBackgroundColor(a13);
                    }
                }
            }
        }
    }
    
    static
    {
        aia.graph.common.GraphMapsCanvasExt.QUEUE_X = 80;
        aia.graph.common.GraphMapsCanvasExt.QUEUE_Y = 32;
    }
}