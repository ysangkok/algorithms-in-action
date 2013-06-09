public class TransclosureGraphMapsCanvasExt extends aia.graph.common.GraphMapsCanvasExt implements aia.graph.common.GraphDialogEventHandler {
    protected TransclosureGraphMaps graphMaps;
    protected TransclosureGraphCanvasCommonExt commons;
    protected int MATRIX_X;
    protected int MATRIX_Y;
    protected java.awt.Font normalFont;
    protected java.awt.Color textColor;
    final public static int NODE_WIDTH = 20;
    final public static int NODE_HEIGHT = 20;
    protected java.util.Vector vecNodes;
    
    TransclosureGraphMapsCanvasExt()
    {
        super();
        this.commons = null;
        this.MATRIX_X = 50;
        this.MATRIX_Y = 50;
        this.vecNodes = null;
        com.cim.AIA.MultipleTween a = new com.cim.AIA.MultipleTween((com.cim.AIA.Paintable)this);
        this.tweens = a;
        aia.graph.common.DragSelectionListener a0 = new aia.graph.common.DragSelectionListener((aia.graph.common.GraphMapsCanvasExt)this);
        this.addSelectionListener((com.cim.AIA.SelectionListener)a0);
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
        TransclosureGraphCanvasCommonExt a = this.commons;
        a.processDialogEvent(s, s0);
    }
    
    public void editMatrix(aia.graph.common.GraphMapsNode a)
    {
        TransclosureGraphCanvasCommonExt a0 = this.commons;
        a0.editMatrix(a);
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
        TransclosureGraphCanvasCommonExt a4 = this.commons;
        if(a4 != null)
        {
            TransclosureGraphCanvasCommonExt a5 = this.commons;
            a5.drawAdjacencyMatrix(a);
            this.drawLabel(a);
        }
    }
    
    private void drawLabel(java.awt.Graphics a)
    {
        TransclosureGraphCanvasCommonExt a0 = this.commons;
        java.util.Vector a1 = a0.getMatrix();
        label2: {
            label1: {
                label0: {
                    if(a1 == null)
                    {
                        break label0;
                    }
                    TransclosureGraphMaps a2 = this.graphMaps;
                    if(a2 != null)
                    {
                        break label1;
                    }
                }
                break label2;
            }
            TransclosureGraphMaps a3 = this.graphMaps;
            int i = a3.getMatrixShowing()?1:0;
            label3: {
                if(i != 0)
                {
                    break label3;
                }
                break label2;
            }
            java.awt.Font a4 = a.getFont();
            java.awt.Font a5 = new java.awt.Font("Arial", 1, 8);
            a.setFont(a5);
            java.awt.FontMetrics a6 = a.getFontMetrics();
            java.awt.Color a7 = java.awt.Color.black;
            a.setColor(a7);
            TransclosureGraphMaps a8 = this.graphMaps;
            int i0 = a8.getX();
            TransclosureGraphMaps a9 = this.graphMaps;
            int i1 = a9.getY();
            TransclosureGraphMaps a10 = this.graphMaps;
            int i2 = a10.getJ();
            TransclosureGraphMaps a11 = this.graphMaps;
            int i3 = a11.getNumberOfNodes();
            label5: {
                label4: {
                    if(i0 != i3)
                    {
                        break label4;
                    }
                    TransclosureGraphMaps a12 = this.graphMaps;
                    int i4 = a12.getNumberOfNodes();
                    if(i1 != i4)
                    {
                        break label4;
                    }
                    int i5 = this.MATRIX_X;
                    TransclosureGraphCanvasCommonExt a13 = this.commons;
                    int i6 = a13.getMatrixWidth();
                    int i7 = i5 + i6;
                    int i8 = i7 + 5;
                    int i9 = this.MATRIX_Y;
                    int i10 = 20 * i0;
                    int i11 = i9 + i10;
                    int i12 = a6.getHeight();
                    int i13 = 20 + i12;
                    int i14 = i13 / 2;
                    int i15 = i11 + i14;
                    a.drawString("x  y", i8, i15);
                    break label5;
                }
                int i16 = this.MATRIX_X;
                TransclosureGraphCanvasCommonExt a14 = this.commons;
                int i17 = a14.getMatrixWidth();
                int i18 = i16 + i17;
                int i19 = i18 + 5;
                int i20 = this.MATRIX_Y;
                int i21 = 20 * i0;
                int i22 = i20 + i21;
                int i23 = a6.getHeight();
                int i24 = 20 + i23;
                int i25 = i24 / 2;
                int i26 = i22 + i25;
                a.drawString("x", i19, i26);
                int i27 = this.MATRIX_X;
                int i28 = 20 * i1;
                int i29 = i27 + i28;
                int i30 = a6.stringWidth("y");
                int i31 = 20 - i30;
                int i32 = i31 / 2;
                int i33 = i29 + i32;
                int i34 = this.MATRIX_Y;
                TransclosureGraphCanvasCommonExt a15 = this.commons;
                int i35 = a15.getMatrixHeight();
                int i36 = i34 + i35;
                int i37 = a6.getHeight();
                int i38 = i36 + i37;
                a.drawString("y", i33, i38);
            }
            TransclosureGraphMaps a16 = this.graphMaps;
            int i39 = a16.isInSecondLoop()?1:0;
            label6: {
                if(i39 == 0)
                {
                    break label6;
                }
                if(i2 == i1)
                {
                    int i40 = this.MATRIX_X;
                    int i41 = 20 * i2;
                    int i42 = i40 + i41;
                    int i43 = a6.stringWidth("y");
                    int i44 = 20 - i43;
                    int i45 = i44 / 2;
                    int i46 = i42 + i45;
                    int i47 = this.MATRIX_Y;
                    TransclosureGraphCanvasCommonExt a17 = this.commons;
                    int i48 = a17.getMatrixHeight();
                    int i49 = i47 + i48;
                    int i50 = a6.getHeight();
                    int i51 = i49 + i50;
                    a.drawString("y", i46, i51);
                    int i52 = this.MATRIX_X;
                    int i53 = 20 * i2;
                    int i54 = i52 + i53;
                    int i55 = a6.stringWidth("j");
                    int i56 = 20 - i55;
                    int i57 = i56 / 2;
                    int i58 = i54 + i57;
                    int i59 = this.MATRIX_Y;
                    TransclosureGraphCanvasCommonExt a18 = this.commons;
                    int i60 = a18.getMatrixHeight();
                    int i61 = i59 + i60;
                    int i62 = a6.getHeight();
                    int i63 = 2 * i62;
                    int i64 = i61 + i63;
                    a.drawString("j", i58, i64);
                }
                else
                {
                    int i65 = this.MATRIX_X;
                    int i66 = 20 * i2;
                    int i67 = i65 + i66;
                    int i68 = a6.stringWidth("j");
                    int i69 = 20 - i68;
                    int i70 = i69 / 2;
                    int i71 = i67 + i70;
                    int i72 = this.MATRIX_Y;
                    TransclosureGraphCanvasCommonExt a19 = this.commons;
                    int i73 = a19.getMatrixHeight();
                    int i74 = i72 + i73;
                    int i75 = a6.getHeight();
                    int i76 = i74 + i75;
                    a.drawString("j", i71, i76);
                }
                if(i0 == i1)
                {
                    int i77 = this.MATRIX_X;
                    TransclosureGraphCanvasCommonExt a20 = this.commons;
                    int i78 = a20.getMatrixWidth();
                    int i79 = i77 + i78;
                    int i80 = i79 + 5;
                    int i81 = this.MATRIX_Y;
                    int i82 = 20 * i1;
                    int i83 = i81 + i82;
                    int i84 = a6.getHeight();
                    int i85 = 20 + i84;
                    int i86 = i85 / 2;
                    int i87 = i83 + i86;
                    a.drawString("x  y", i80, i87);
                }
                else
                {
                    int i88 = this.MATRIX_X;
                    TransclosureGraphCanvasCommonExt a21 = this.commons;
                    int i89 = a21.getMatrixWidth();
                    int i90 = i88 + i89;
                    int i91 = i90 + 5;
                    int i92 = this.MATRIX_Y;
                    int i93 = 20 * i1;
                    int i94 = i92 + i93;
                    int i95 = a6.getHeight();
                    int i96 = 20 + i95;
                    int i97 = i96 / 2;
                    int i98 = i94 + i97;
                    a.drawString("y", i91, i98);
                }
            }
            a.setFont(a4);
        }
    }
    
    public void drawMatrixAssociatedElements(java.awt.Graphics a, com.cim.AIA.ElementArray a0, int i)
    {
    }
    
    public void setStructureNodeAppearance(aia.graph.common.GraphMapsNode a, aia.graph.common.GraphMapsNode a0)
    {
    }
    
    public void drawStructureAssociatedElements(java.awt.Graphics a, aia.graph.common.GraphMapsNode a0, aia.graph.common.GraphMapsNode a1)
    {
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
            TransclosureGraphMaps dummy = (TransclosureGraphMaps)a2;
            TransclosureGraphMaps a3 = (TransclosureGraphMaps)a2;
            this.graphMaps = a3;
            TransclosureGraphCanvasCommonExt a4 = this.commons;
            if(a4 == null)
            {
                TransclosureGraphMaps a5 = this.graphMaps;
                TransclosureGraphCanvasCommonExt a6 = new TransclosureGraphCanvasCommonExt((aia.graph.common.GraphMapsCanvasExt)this, (aia.graph.common.GraphMaps)a5);
                this.commons = a6;
            }
            this.removeAllSelectables();
            TransclosureGraphCanvasCommonExt a7 = this.commons;
            TransclosureGraphMaps a8 = this.graphMaps;
            java.util.Vector a9 = a8.getAdjacencyMatrix();
            int i = this.MATRIX_X;
            int i0 = this.MATRIX_Y;
            a7.setMatrix(a9, i, i0);
            TransclosureGraphCanvasCommonExt a10 = this.commons;
            a10.adjustCanvasSize();
            this.repaint();
        }
    }
}