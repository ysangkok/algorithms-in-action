public class MSTKGraphMapsCanvasExt extends aia.graph.common.GraphMapsCanvasExt implements aia.graph.common.GraphDialogEventHandler {
    protected MSTKGraphMaps graphMaps;
    protected MSTKGraphCanvasCommonExt commons;
    protected int MATRIX_X;
    protected int MATRIX_Y;
    protected int STRUCTURE_X;
    protected int STRUCTURE_Y;
    protected int LINK_LENGTH;
    protected int EDGES_ADDED_X;
    protected int EDGES_ADDED_Y;
    protected com.cim.AIA.ElementArray earrQueue;
    public static int QUEUE_X;
    public static int QUEUE_Y;
    protected int SAMP_QUEUE_MEM_X;
    protected int SAMP_QUEUE_MEM_Y;
    protected java.awt.Font normalFont;
    protected java.awt.Color textColor;
    final public static int NODE_WIDTH = 20;
    final public static int NODE_HEIGHT = 20;
    
    MSTKGraphMapsCanvasExt()
    {
        super();
        this.commons = null;
        this.MATRIX_X = 50;
        this.MATRIX_Y = 145;
        this.STRUCTURE_X = 30;
        this.STRUCTURE_Y = 145;
        this.LINK_LENGTH = 20;
        this.EDGES_ADDED_X = 30;
        this.EDGES_ADDED_Y = 90;
        this.earrQueue = null;
        this.SAMP_QUEUE_MEM_X = 180;
        this.SAMP_QUEUE_MEM_Y = 7;
        com.cim.AIA.MultipleTween a = new com.cim.AIA.MultipleTween((com.cim.AIA.Paintable)this);
        this.tweens = a;
        aia.graph.common.DragSelectionListener a0 = new aia.graph.common.DragSelectionListener((aia.graph.common.GraphMapsCanvasExt)this);
        this.addSelectionListener((com.cim.AIA.SelectionListener)a0);
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
        MSTKGraphCanvasCommonExt a = this.commons;
        a.processDialogEvent(s, s0);
    }
    
    public void editMatrix(aia.graph.common.GraphMapsNode a)
    {
        MSTKGraphCanvasCommonExt a0 = this.commons;
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
        MSTKGraphCanvasCommonExt a4 = this.commons;
        if(a4 != null)
        {
            MSTKGraphCanvasCommonExt a5 = this.commons;
            a5.drawAdjacencyMatrix(a);
            MSTKGraphCanvasCommonExt a6 = this.commons;
            a6.drawAdjacencyStructure(a);
            this.drawCurrentEdge(a);
            this.drawQueue(a);
            this.drawEdgesAddedCounter(a);
        }
    }
    
    private void drawCurrentEdge(java.awt.Graphics a)
    {
        MSTKGraphMaps a0 = this.graphMaps;
        label1: {
            label0: {
                if(a0 != null)
                {
                    break label0;
                }
                break label1;
            }
            MSTKGraphMaps a1 = this.graphMaps;
            aia.graph.common.TwinNode a2 = a1.getCurrentEdgeNode();
            if(a2 != null)
            {
                java.awt.FontMetrics a3 = a.getFontMetrics();
                int i = MSTKGraphMapsCanvasExt.QUEUE_X;
                int i0 = i - 50;
                int i1 = MSTKGraphMapsCanvasExt.QUEUE_Y;
                java.awt.Point a4 = new java.awt.Point(i0, i1);
                a2.setPosition(a4);
                a2.draw(a);
            }
        }
    }
    
    public java.awt.Color getTextColor()
    {
        java.awt.Color a = this.textColor;
        return a;
    }
    
    public void drawMatrixAssociatedElements(java.awt.Graphics a, com.cim.AIA.ElementArray a0, int i)
    {
    }
    
    public void drawStructureAssociatedElements(java.awt.Graphics a, aia.graph.common.GraphMapsNode a0, aia.graph.common.GraphMapsNode a1)
    {
    }
    
    public void setStructureNodeAppearance(aia.graph.common.GraphMapsNode a, aia.graph.common.GraphMapsNode a0)
    {
        int i = a.getIntValue();
        if(i == -1)
        {
            java.awt.Color a1 = java.awt.Color.white;
            a.setBackgroundColor(a1);
            java.awt.Color a2 = java.awt.Color.white;
            a.setTextColor(a2);
        }
        else
        {
            MSTKGraphMaps a3 = this.graphMaps;
            java.awt.Color a4 = a3.getUnvisitedNodeColor();
            a.setBackgroundColor(a4);
            MSTKGraphMaps a5 = this.graphMaps;
            java.awt.Color a6 = a5.getTextColor();
            a.setTextColor(a6);
        }
    }
    
    private void drawQueue(java.awt.Graphics a)
    {
        java.awt.FontMetrics a0 = a.getFontMetrics();
        java.awt.Color a1 = java.awt.Color.black;
        a.setColor(a1);
        String s = localization.Messages.getString("MSTKGraphMapsCanvasExt.0");
        int i = MSTKGraphMapsCanvasExt.QUEUE_X;
        int i0 = MSTKGraphMapsCanvasExt.QUEUE_Y;
        int i1 = i0 + 20;
        int i2 = a0.getMaxDescent();
        int i3 = i1 - i2;
        a.drawString(s, i, i3);
        String s0 = localization.Messages.getString("MSTKGraphMapsCanvasExt.1");
        int i4 = MSTKGraphMapsCanvasExt.QUEUE_X;
        String s1 = localization.Messages.getString("MSTKGraphMapsCanvasExt.2");
        int i5 = a0.stringWidth(s1);
        int i6 = i4 + i5;
        int i7 = MSTKGraphMapsCanvasExt.QUEUE_Y;
        int i8 = i7 + 20;
        int i9 = a0.getHeight();
        int i10 = i8 + i9;
        a.drawString(s0, i6, i10);
        com.cim.AIA.ElementArray a2 = this.earrQueue;
        label0: {
            if(a2 == null)
            {
                break label0;
            }
            com.cim.AIA.ElementArray a3 = this.earrQueue;
            a3.draw(a);
            MSTKGraphMaps a4 = this.graphMaps;
            int i11 = a4.isOverFlow()?1:0;
            if(i11 != 0)
            {
                java.awt.Color a5 = java.awt.Color.black;
                a.setColor(a5);
                int i12 = MSTKGraphMapsCanvasExt.QUEUE_X;
                int i13 = i12 + 100;
                com.cim.AIA.ElementArray a6 = this.earrQueue;
                int i14 = a6.getWidth();
                int i15 = i13 + i14;
                com.cim.AIA.ElementArray a7 = this.earrQueue;
                int i16 = a7.getElementWidth();
                int i17 = i15 - i16;
                int i18 = i17 + 10;
                int i19 = MSTKGraphMapsCanvasExt.QUEUE_Y;
                int i20 = i19 + 20;
                a.drawString("...", i18, i20);
            }
        }
    }
    
    private void drawEdgesAddedCounter(java.awt.Graphics a)
    {
        java.awt.FontMetrics a0 = a.getFontMetrics();
        java.awt.Color a1 = java.awt.Color.black;
        a.setColor(a1);
        String s = localization.Messages.getString("MSTKGraphMapsCanvasExt.4");
        int i = this.EDGES_ADDED_X;
        int i0 = this.EDGES_ADDED_Y;
        int i1 = a0.getHeight();
        int i2 = i0 + i1;
        a.drawString(s, i, i2);
        MSTKGraphMaps a2 = this.graphMaps;
        int i3 = a2.getEdgesAdded();
        MSTKGraphMaps a3 = this.graphMaps;
        if(i3 != -10)
        {
            MSTKGraphMaps a4 = this.graphMaps;
            int i4 = a4.getEdgesAdded();
            String s0 = Integer.toString(i4);
            int i5 = this.EDGES_ADDED_X;
            String s1 = localization.Messages.getString("MSTKGraphMapsCanvasExt.5");
            int i6 = a0.stringWidth(s1);
            int i7 = i5 + i6;
            int i8 = this.EDGES_ADDED_Y;
            int i9 = a0.getHeight();
            int i10 = i8 + i9;
            a.drawString(s0, i7, i10);
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
            MSTKGraphMaps dummy = (MSTKGraphMaps)a2;
            MSTKGraphMaps a3 = (MSTKGraphMaps)a2;
            this.graphMaps = a3;
            MSTKGraphCanvasCommonExt a4 = this.commons;
            if(a4 == null)
            {
                MSTKGraphMaps a5 = this.graphMaps;
                MSTKGraphCanvasCommonExt a6 = new MSTKGraphCanvasCommonExt(this, a5);
                this.commons = a6;
            }
            this.removeAllSelectables();
            MSTKGraphCanvasCommonExt a7 = this.commons;
            MSTKGraphMaps a8 = this.graphMaps;
            java.util.Vector a9 = a8.getAdjacencyMatrix();
            int i = this.MATRIX_X;
            int i0 = this.MATRIX_Y;
            a7.setMatrix(a9, i, i0);
            MSTKGraphCanvasCommonExt a10 = this.commons;
            MSTKGraphMaps a11 = this.graphMaps;
            com.cim.AIA.ElementArray a12 = a11.getAdjacencyStructure();
            int i1 = this.STRUCTURE_X;
            int i2 = this.STRUCTURE_Y;
            int i3 = this.LINK_LENGTH;
            a10.setStructure(a12, i1, i2, i3);
            MSTKGraphMaps a13 = this.graphMaps;
            com.cim.AIA.ElementArray a14 = a13.getQueue();
            this.earrQueue = a14;
            com.cim.AIA.ElementArray a15 = this.earrQueue;
            if(a15 != null)
            {
                com.cim.AIA.ElementArray a16 = this.earrQueue;
                int i4 = MSTKGraphMapsCanvasExt.QUEUE_X;
                int i5 = i4 + 100;
                int i6 = MSTKGraphMapsCanvasExt.QUEUE_Y;
                a16.setLocation(i5, i6);
            }
            MSTKGraphCanvasCommonExt a17 = this.commons;
            a17.adjustCanvasSize();
            MSTKGraphMaps a18 = this.graphMaps;
            com.cim.AIA.ElementArray a19 = this.earrQueue;
            int i7 = this.numberOfTweenSteps;
            com.cim.AIA.MultipleTween a20 = a18.generateTweens((com.cim.AIA.Paintable)this, (Object)a19, i7);
            if(a20 != null)
            {
                this.addTween((com.cim.AIA.Tween)a20);
            }
            com.cim.AIA.MultipleTween a21 = this.tweens;
            int i8 = a21.getNumberOfTweens();
            if(i8 > 0)
            {
                com.cim.AIA.MultipleTween a22 = this.tweens;
                a22.run();
            }
            MSTKGraphMaps a23 = this.graphMaps;
            a23.removeAllAnimationRequests();
            this.repaint();
        }
    }
    
    static
    {
        MSTKGraphMapsCanvasExt.QUEUE_X = 80;
        MSTKGraphMapsCanvasExt.QUEUE_Y = 40;
    }
}