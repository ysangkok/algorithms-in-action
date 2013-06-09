public class DFSGraphCanvasCommonExt {
    protected java.util.Vector vecAdjacencyMatrix;
    protected int MATRIX_X;
    protected int MATRIX_Y;
    protected int MATRIX_WIDTH;
    protected int MATRIX_HEIGHT;
    protected com.cim.AIA.ElementArray earrAdjacencyStructure;
    protected int STRUCTURE_X;
    protected int STRUCTURE_Y;
    protected int STRUCTURE_HEIGHT;
    protected int STRUCTURE_WIDTH;
    protected int LINK_LENGTH;
    protected java.awt.Frame m_frmMatrixEdit;
    protected aia.graph.common.InputDialog m_dlgMatrixEdit;
    protected aia.graph.common.GraphMapsNode m_MatrixEditNode;
    protected DFSGraphMapsCanvasExt m_canvas;
    protected DFSGraphMaps m_algorithm;
    
    DFSGraphCanvasCommonExt(DFSGraphMapsCanvasExt a, DFSGraphMaps a0)
    {
        super();
        this.vecAdjacencyMatrix = null;
        this.MATRIX_X = 50;
        this.MATRIX_Y = 130;
        this.MATRIX_WIDTH = 0;
        this.MATRIX_HEIGHT = 0;
        this.earrAdjacencyStructure = null;
        this.STRUCTURE_X = 30;
        this.STRUCTURE_Y = 130;
        this.STRUCTURE_HEIGHT = 0;
        this.STRUCTURE_WIDTH = 0;
        this.LINK_LENGTH = 20;
        this.m_frmMatrixEdit = null;
        this.m_dlgMatrixEdit = null;
        this.m_MatrixEditNode = null;
        this.m_canvas = a;
        this.m_algorithm = a0;
    }
    
    public void setMatrix(java.util.Vector a, int i, int i0)
    {
        this.MATRIX_X = i;
        this.MATRIX_Y = i0;
        this.vecAdjacencyMatrix = a;
        java.util.Vector a0 = this.vecAdjacencyMatrix;
        label0: {
            if(a0 == null)
            {
                break label0;
            }
            int i1 = 0;
            while(true)
            {
                java.util.Vector a1 = this.vecAdjacencyMatrix;
                int i2 = a1.size();
                if(i1 >= i2)
                {
                    break;
                }
                java.util.Vector a2 = this.vecAdjacencyMatrix;
                Object a3 = a2.elementAt(i1);
                com.cim.AIA.ElementArray dummy = (com.cim.AIA.ElementArray)a3;
                com.cim.AIA.ElementArray a4 = (com.cim.AIA.ElementArray)a3;
                int i3 = a4.getWidth();
                this.MATRIX_WIDTH = i3;
                int i4 = this.MATRIX_X;
                int i5 = this.MATRIX_Y;
                int i6 = a4.getHeight();
                int i7 = i6 * i1;
                int i8 = i5 + i7;
                a4.setLocation(i4, i8);
                DFSGraphMaps a5 = this.m_algorithm;
                int i9 = a5.getMatrixShowing()?1:0;
                if(i9 != 0)
                {
                    DFSGraphMapsCanvasExt a6 = this.m_canvas;
                    a6.addSelectable((com.cim.AIA.Selectable)a4);
                }
                int i10 = i1 + 1;
                i1 = i10;
            }
            java.util.Vector a7 = this.vecAdjacencyMatrix;
            int i11 = a7.size();
            DFSGraphMapsCanvasExt a8 = this.m_canvas;
            int i12 = i11 * 20;
            this.MATRIX_HEIGHT = i12;
        }
    }
    
    public java.util.Vector getMatrix()
    {
        java.util.Vector a = this.vecAdjacencyMatrix;
        return a;
    }
    
    public int getMatrixWidth()
    {
        int i = this.MATRIX_WIDTH;
        return i;
    }
    
    public int getMatrixHeight()
    {
        int i = this.MATRIX_HEIGHT;
        return i;
    }
    
    public int getMatrixX()
    {
        int i = this.MATRIX_X;
        return i;
    }
    
    public int getMatrixY()
    {
        int i = this.MATRIX_Y;
        return i;
    }
    
    public int getStructureX()
    {
        int i = this.STRUCTURE_X;
        return i;
    }
    
    public int getStructureY()
    {
        int i = this.STRUCTURE_Y;
        return i;
    }
    
    public void setStructure(com.cim.AIA.ElementArray a, int i, int i0, int i1)
    {
        DFSGraphMaps a0 = this.m_algorithm;
        int i2 = a0.getMatrixShowing()?1:0;
        label1: {
            label0: {
                if(i2 != 0)
                {
                    break label0;
                }
                int i3 = this.MATRIX_X;
                this.STRUCTURE_X = i3;
                break label1;
            }
            this.STRUCTURE_X = i;
            int i4 = this.STRUCTURE_X;
            int i5 = this.MATRIX_WIDTH;
            int i6 = i4 + i5;
            if(i6 < 120)
            {
                int i7 = this.MATRIX_WIDTH;
                int i8 = 120 - i7;
                this.STRUCTURE_X = i8;
            }
        }
        this.STRUCTURE_Y = i0;
        this.LINK_LENGTH = i1;
        this.earrAdjacencyStructure = a;
        com.cim.AIA.ElementArray a1 = this.earrAdjacencyStructure;
        label2: {
            if(a1 == null)
            {
                break label2;
            }
            com.cim.AIA.ElementArray a2 = this.earrAdjacencyStructure;
            int i9 = a2.getWidth();
            this.STRUCTURE_WIDTH = i9;
            java.util.Vector a3 = this.vecAdjacencyMatrix;
            label3: {
                if(a3 == null)
                {
                    break label3;
                }
                DFSGraphMaps a4 = this.m_algorithm;
                int i10 = a4.getMatrixShowing()?1:0;
                if(i10 == 0)
                {
                    com.cim.AIA.ElementArray a5 = this.earrAdjacencyStructure;
                    int i11 = this.STRUCTURE_X;
                    int i12 = this.STRUCTURE_Y;
                    a5.setLocation(i11, i12);
                }
                else
                {
                    com.cim.AIA.ElementArray a6 = this.earrAdjacencyStructure;
                    int i13 = this.MATRIX_WIDTH;
                    int i14 = this.MATRIX_X;
                    int i15 = i13 + i14;
                    int i16 = this.STRUCTURE_X;
                    int i17 = i15 + i16;
                    int i18 = this.STRUCTURE_Y;
                    a6.setLocation(i17, i18);
                }
            }
            com.cim.AIA.ElementArray a7 = this.earrAdjacencyStructure;
            java.awt.Point a8 = a7.getLocation();
            int i19 = a8.x;
            this.STRUCTURE_X = i19;
            this.calculateStructureHeight();
        }
    }
    
    public int getStructureHeight()
    {
        int i = this.STRUCTURE_HEIGHT;
        return i;
    }
    
    public int getStructureWeight()
    {
        int i = this.STRUCTURE_WIDTH;
        return i;
    }
    
    private void calculateStructureHeight()
    {
        this.STRUCTURE_HEIGHT = 0;
        com.cim.AIA.ElementArray a = this.earrAdjacencyStructure;
        label1: {
            int i = 0;
            label0: {
                if(a != null)
                {
                    break label0;
                }
                break label1;
            }
            int i0 = 0;
            int i1 = 0;
            while(true)
            {
                int i2 = 0;
                int i3 = 0;
                int i4 = 0;
                DFSGraphMaps a0 = this.m_algorithm;
                int i5 = i1;
                int i6 = a0.getNumberOfNodes();
                i = i5;
                int i7 = i;
                if(i0 >= i6)
                {
                    break;
                }
                else
                {
                    i2 = i7;
                }
                DFSGraphMaps a1 = this.m_algorithm;
                int i8 = i2;
                aia.graph.common.GraphNode a2 = a1.getAdjacentNode(i0);
                int i9 = i8;
                DFSGraphMapsCanvasExt a3 = this.m_canvas;
                int i10 = i9;
                int i11 = 20;
                int i12 = i10;
                aia.graph.common.GraphNode a4 = a2;
                while(true)
                {
                    int i13 = 0;
                    int i14 = 0;
                    int i15 = i12;
                    int i16 = i12;
                    if(a4 == null)
                    {
                        i3 = i11;
                        i4 = i16;
                        break;
                    }
                    else
                    {
                        i13 = i15;
                    }
                    DFSGraphMapsCanvasExt a5 = this.m_canvas;
                    int i17 = i13;
                    int i18 = this.LINK_LENGTH;
                    int i19 = i17;
                    int i20 = 20 + i18;
                    int i21 = i11 + i20;
                    int i22 = a4.getKey();
                    int i23 = i19;
                    int i24 = i23;
                    int i25 = i23;
                    if(i22 != -1)
                    {
                        int i26 = i25;
                        aia.graph.common.GraphNode a6 = a4.getNext();
                        int i27 = i26;
                        i11 = i21;
                        i12 = i27;
                        a4 = a6;
                        continue;
                    }
                    else
                    {
                        i14 = i24;
                    }
                    i3 = i21;
                    i4 = i14;
                    break;
                }
                int i28 = i4;
                int i29 = i3 <= i4?i28:i3;
                int i30 = i0 + 1;
                i0 = i30;
                i1 = i29;
            }
            this.STRUCTURE_HEIGHT = i;
        }
    }
    
    public com.cim.AIA.ElementArray getStructure()
    {
        com.cim.AIA.ElementArray a = this.earrAdjacencyStructure;
        return a;
    }
    
    public void adjustCanvasSize()
    {
        int i = 0;
        int i0 = 0;
        int i1 = 0;
        int i2 = 0;
        java.util.Vector a = this.vecAdjacencyMatrix;
        label0: {
            int i3 = 0;
            if(a == null)
            {
                i = 440;
                i0 = 360;
                break label0;
            }
            DFSGraphMaps a0 = this.m_algorithm;
            int i4 = a0.getMatrixShowing()?1:0;
            if(i4 == 0)
            {
                i = 440;
                i0 = 360;
                break label0;
            }
            int i5 = this.MATRIX_X;
            int i6 = this.MATRIX_WIDTH;
            int i7 = i5 + i6;
            int i8 = i7 + 20;
            if(i8 <= 440)
            {
                i3 = 440;
            }
            else
            {
                int i9 = this.MATRIX_X;
                int i10 = this.MATRIX_WIDTH;
                int i11 = i9 + i10;
                int i12 = i11 + 20;
                i3 = i12;
            }
            int i13 = this.MATRIX_Y;
            int i14 = this.MATRIX_HEIGHT;
            int i15 = i13 + i14;
            int i16 = i15 + 20;
            if(i16 <= 360)
            {
                i = i3;
                i0 = 360;
            }
            else
            {
                int i17 = this.MATRIX_Y;
                int i18 = this.MATRIX_HEIGHT;
                int i19 = i17 + i18;
                int i20 = i19 + 20;
                i = i3;
                i0 = i20;
            }
        }
        com.cim.AIA.ElementArray a1 = this.earrAdjacencyStructure;
        label1: {
            int i21 = 0;
            if(a1 == null)
            {
                i1 = i;
                i2 = i0;
                break label1;
            }
            DFSGraphMaps a2 = this.m_algorithm;
            int i22 = a2.getStructureShowing()?1:0;
            if(i22 == 0)
            {
                i1 = i;
                i2 = i0;
                break label1;
            }
            int i23 = this.STRUCTURE_X;
            int i24 = this.STRUCTURE_WIDTH;
            int i25 = i23 + i24;
            int i26 = i25 + 20;
            if(i26 <= i)
            {
                i21 = i;
            }
            else
            {
                int i27 = this.STRUCTURE_X;
                int i28 = this.STRUCTURE_WIDTH;
                int i29 = i27 + i28;
                int i30 = i29 + 20;
                i21 = i30;
            }
            int i31 = this.STRUCTURE_Y;
            int i32 = this.STRUCTURE_HEIGHT;
            int i33 = i31 + i32;
            int i34 = i33 + 20;
            if(i34 <= i0)
            {
                i1 = i21;
                i2 = i0;
            }
            else
            {
                int i35 = this.STRUCTURE_Y;
                int i36 = this.STRUCTURE_HEIGHT;
                int i37 = i35 + i36;
                int i38 = i37 + 20;
                i1 = i21;
                i2 = i38;
            }
        }
        DFSGraphMapsCanvasExt a3 = this.m_canvas;
        a3.setSize(i1, i2);
    }
    
    public void processDialogEvent(String s, String s0)
    {
        DFSGraphMaps a = this.m_algorithm;
        label0: {
            if(a == null)
            {
                break label0;
            }
            DFSGraphMaps a0 = this.m_algorithm;
            int i = a0.getIsRunning()?1:0;
            if(i != 0)
            {
                break label0;
            }
            if(s0 == null)
            {
                break label0;
            }
            String s1 = localization.Messages.getString("DFSGraphCanvasCommonExt.0");
            int i0 = s.equals((Object)s1)?1:0;
            if(i0 != 0)
            {
                DFSGraphMaps a1 = this.m_algorithm;
                aia.graph.common.GraphMapsNode a2 = this.m_MatrixEditNode;
                int i1 = Integer.parseInt(s0);
                a1.editLink(a2, i1);
            }
        }
        DFSGraphMaps a3 = this.m_algorithm;
        if(a3 != null)
        {
            DFSGraphMapsCanvasExt a4 = this.m_canvas;
            DFSGraphMapsCanvasExt a5 = this.m_canvas;
            DFSGraphMaps a6 = this.m_algorithm;
            com.cim.AIA.RepaintEvent a7 = new com.cim.AIA.RepaintEvent((Object)a5, (Object)a6);
            a4.processRepaintEvent(a7);
        }
    }
    
    public void editMatrix(aia.graph.common.GraphMapsNode a)
    {
        DFSGraphMaps a0 = this.m_algorithm;
        label0: {
            if(a0 == null)
            {
                break label0;
            }
            DFSGraphMaps a1 = this.m_algorithm;
            int i = a1.getIsRunning()?1:0;
            if(i == 0)
            {
                this.m_MatrixEditNode = a;
                java.awt.Frame a2 = new java.awt.Frame();
                this.m_frmMatrixEdit = a2;
                java.awt.Frame a3 = this.m_frmMatrixEdit;
                a3.setSize(100, 70);
                java.awt.Frame a4 = this.m_frmMatrixEdit;
                a4.setLocation(0, 0);
                DFSGraphMapsCanvasExt a5 = this.m_canvas;
                java.awt.Frame a6 = this.m_frmMatrixEdit;
                String s = localization.Messages.getString("DFSGraphCanvasCommonExt.1");
                String s0 = localization.Messages.getString("DFSGraphCanvasCommonExt.2");
                int i0 = a.getIntValue();
                aia.graph.common.InputDialog a7 = new aia.graph.common.InputDialog((aia.graph.common.GraphDialogEventHandler)a5, a6, s, true, s0, i0);
                this.m_dlgMatrixEdit = a7;
                aia.graph.common.InputDialog a8 = this.m_dlgMatrixEdit;
                a8.show();
            }
        }
    }
    
    public void drawAdjacencyMatrix(java.awt.Graphics a)
    {
        java.util.Vector a0 = this.vecAdjacencyMatrix;
        label1: {
            label0: {
                if(a0 != null)
                {
                    break label0;
                }
                break label1;
            }
            DFSGraphMaps a1 = this.m_algorithm;
            int i = a1.getMatrixShowing()?1:0;
            label2: {
                if(i != 0)
                {
                    break label2;
                }
                break label1;
            }
            java.awt.FontMetrics a2 = a.getFontMetrics();
            java.awt.Color a3 = java.awt.Color.blue;
            a.setColor(a3);
            String s = localization.Messages.getString("DFSGraphCanvasCommonExt.3");
            int i0 = this.MATRIX_X;
            int i1 = this.MATRIX_Y;
            int i2 = a2.getHeight();
            int i3 = i1 - i2;
            a.drawString(s, i0, i3);
            String s0 = localization.Messages.getString("DFSGraphCanvasCommonExt.4");
            int i4 = this.MATRIX_X;
            String s1 = localization.Messages.getString("DFSGraphCanvasCommonExt.5");
            int i5 = a2.stringWidth(s1);
            int i6 = i4 - i5;
            int i7 = this.MATRIX_Y;
            DFSGraphMapsCanvasExt a4 = this.m_canvas;
            int i8 = i7 + 20;
            int i9 = a2.getMaxDescent();
            int i10 = i8 - i9;
            a.drawString(s0, i6, i10);
            String s2 = localization.Messages.getString("DFSGraphCanvasCommonExt.6");
            int i11 = this.MATRIX_X;
            int i12 = this.MATRIX_Y;
            int i13 = a2.getHeight();
            int i14 = i13 * 2;
            int i15 = i12 - i14;
            a.drawString(s2, i11, i15);
            DFSGraphMapsCanvasExt a5 = this.m_canvas;
            java.awt.Color a6 = a5.getTextColor();
            a.setColor(a6);
            int i16 = 0;
            while(true)
            {
                java.util.Vector a7 = this.vecAdjacencyMatrix;
                int i17 = a7.size();
                if(i16 >= i17)
                {
                    break;
                }
                else
                {
                    String s3 = Integer.toString(i16);
                    int i18 = this.MATRIX_X;
                    DFSGraphMapsCanvasExt a8 = this.m_canvas;
                    int i19 = 20 * i16;
                    int i20 = i18 + i19;
                    DFSGraphMapsCanvasExt a9 = this.m_canvas;
                    int i21 = a2.stringWidth(s3);
                    int i22 = 20 - i21;
                    int i23 = i22 / 2;
                    int i24 = i20 + i23;
                    int i25 = this.MATRIX_Y;
                    int i26 = a2.getMaxDescent();
                    int i27 = i25 - i26;
                    a.drawString(s3, i24, i27);
                    int i28 = this.MATRIX_X;
                    int i29 = a2.stringWidth(s3);
                    int i30 = i28 - i29;
                    int i31 = this.MATRIX_Y;
                    DFSGraphMapsCanvasExt a10 = this.m_canvas;
                    int i32 = i16 + 1;
                    int i33 = 20 * i32;
                    int i34 = i31 + i33;
                    int i35 = a2.getMaxDescent();
                    int i36 = i34 - i35;
                    a.drawString(s3, i30, i36);
                    java.util.Vector a11 = this.vecAdjacencyMatrix;
                    Object a12 = a11.elementAt(i16);
                    com.cim.AIA.ElementArray dummy = (com.cim.AIA.ElementArray)a12;
                    com.cim.AIA.ElementArray a13 = (com.cim.AIA.ElementArray)a12;
                    a13.draw(a);
                    DFSGraphMapsCanvasExt a14 = this.m_canvas;
                    a14.drawMatrixAssociatedElements(a, a13, i16);
                    int i37 = i16 + 1;
                    i16 = i37;
                }
            }
        }
    }
    
    public void drawAdjacencyStructure(java.awt.Graphics a)
    {
        com.cim.AIA.ElementArray a0 = this.earrAdjacencyStructure;
        label1: {
            label0: {
                if(a0 != null)
                {
                    break label0;
                }
                break label1;
            }
            DFSGraphMaps a1 = this.m_algorithm;
            int i = a1.getStructureShowing()?1:0;
            label2: {
                if(i != 0)
                {
                    break label2;
                }
                break label1;
            }
            java.awt.Color a2 = java.awt.Color.blue;
            a.setColor(a2);
            java.awt.FontMetrics a3 = a.getFontMetrics();
            String s = localization.Messages.getString("DFSGraphCanvasCommonExt.7");
            com.cim.AIA.ElementArray a4 = this.earrAdjacencyStructure;
            java.awt.Point a5 = a4.getLocation();
            int i0 = a5.x;
            int i1 = this.STRUCTURE_Y;
            int i2 = a3.getHeight();
            int i3 = i2 * 2;
            int i4 = i1 - i3;
            a.drawString(s, i0, i4);
            DFSGraphMapsCanvasExt a6 = this.m_canvas;
            java.awt.Color a7 = a6.getTextColor();
            a.setColor(a7);
            com.cim.AIA.ElementArray a8 = this.earrAdjacencyStructure;
            a8.draw(a);
            int i5 = 0;
            while(true)
            {
                DFSGraphMaps a9 = this.m_algorithm;
                int i6 = a9.getNumberOfNodes();
                if(i5 >= i6)
                {
                    break;
                }
                com.cim.AIA.ElementArray a10 = this.earrAdjacencyStructure;
                com.cim.AIA.Element a11 = a10.getElement(i5);
                aia.graph.common.GraphMapsNode dummy = (aia.graph.common.GraphMapsNode)a11;
                aia.graph.common.GraphMapsNode a12 = (aia.graph.common.GraphMapsNode)a11;
                DFSGraphMaps a13 = this.m_algorithm;
                aia.graph.common.GraphNode a14 = a13.getAdjacentNode(i5);
                int i7 = 1;
                aia.graph.common.GraphNode a15 = a14;
                while(true)
                {
                    label3: {
                        if(a15 == null)
                        {
                            break label3;
                        }
                        int i8 = a12.getX();
                        DFSGraphMapsCanvasExt a16 = this.m_canvas;
                        int i9 = i8 + 10;
                        int i10 = a12.getY();
                        DFSGraphMapsCanvasExt a17 = this.m_canvas;
                        int i11 = this.LINK_LENGTH;
                        int i12 = 20 + i11;
                        int i13 = i7 - 1;
                        int i14 = i12 * i13;
                        int i15 = i10 + i14;
                        DFSGraphMapsCanvasExt a18 = this.m_canvas;
                        int i16 = i15 + 20;
                        int i17 = a12.getX();
                        DFSGraphMapsCanvasExt a19 = this.m_canvas;
                        int i18 = i17 + 10;
                        int i19 = a12.getY();
                        DFSGraphMapsCanvasExt a20 = this.m_canvas;
                        int i20 = this.LINK_LENGTH;
                        int i21 = 20 + i20;
                        int i22 = i21 * i7;
                        int i23 = i19 + i22;
                        com.cim.AIA.Line a21 = new com.cim.AIA.Line(i9, i16, i18, i23);
                        a21.showArrowHead(true);
                        a21.setDistanceFromStartForArrowHead(-3);
                        a21.draw(a);
                        int i24 = a15.getKey();
                        Integer a22 = new Integer(i24);
                        int i25 = a15.getKey();
                        aia.graph.common.GraphMapsNode a23 = new aia.graph.common.GraphMapsNode((Object)a22, i25, 3);
                        int i26 = a12.getX();
                        int i27 = a12.getY();
                        DFSGraphMapsCanvasExt a24 = this.m_canvas;
                        int i28 = this.LINK_LENGTH;
                        int i29 = 20 + i28;
                        int i30 = i29 * i7;
                        int i31 = i27 + i30;
                        java.awt.Point a25 = new java.awt.Point(i26, i31);
                        a23.setPosition(a25);
                        DFSGraphMapsCanvasExt a26 = this.m_canvas;
                        a26.setStructureNodeAppearance(a23, a12);
                        DFSGraphMapsCanvasExt a27 = this.m_canvas;
                        a27.drawStructureAssociatedElements(a, a23, a12);
                        a23.draw(a);
                        int i32 = i7 + 1;
                        int i33 = a15.getKey();
                        if(i33 != -1)
                        {
                            aia.graph.common.GraphNode a28 = a15.getNext();
                            i7 = i32;
                            a15 = a28;
                            continue;
                        }
                    }
                    int i34 = i5 + 1;
                    i5 = i34;
                }
            }
        }
    }
}