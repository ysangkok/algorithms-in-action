public class DFSGraphMapsCanvas extends com.cim.AIA.AlgorithmCanvas implements aia.graph.common.GraphDialogEventHandler {
    protected DFSGraphMaps graphMaps;
    protected DFSGraphCanvasCommon commons;
    protected java.awt.Font normalFont;
    protected java.awt.Color textColor;
    final public static int GRAPH_X = 30;
    final public static int GRAPH_Y = 30;
    final public static int NODE_WIDTH = 20;
    final public static int NODE_HEIGHT = 20;
    
    DFSGraphMapsCanvas()
    {
        super();
        this.commons = null;
        com.cim.AIA.MultipleTween a = new com.cim.AIA.MultipleTween((com.cim.AIA.Paintable)this);
        this.tweens = a;
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
        DFSGraphCanvasCommon a = this.commons;
        a.processDialogEvent(s, s0);
    }
    
    public void processMouseMotionEvent(java.awt.event.MouseEvent a)
    {
        DFSGraphCanvasCommon a0 = this.commons;
        int i = a.getX();
        int i0 = a.getY();
        a0.mouseMoved(i, i0);
    }
    
    public void mouseReleased(java.awt.event.MouseEvent a)
    {
        DFSGraphCanvasCommon a0 = this.commons;
        a0.mouseReleased();
    }
    
    public void mousePressed(java.awt.event.MouseEvent a)
    {
        DFSGraphCanvasCommon a0 = this.commons;
        int i = a.getX();
        int i0 = a.getY();
        a0.mousePressed(i, i0);
        ((com.cim.AIA.AlgorithmCanvas)this).mousePressed(a);
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
        DFSGraphCanvasCommon a4 = this.commons;
        if(a4 != null)
        {
            DFSGraphCanvasCommon a5 = this.commons;
            a5.drawLinks(a);
            DFSGraphCanvasCommon a6 = this.commons;
            a6.drawNodes(a);
        }
    }
    
    public java.awt.Color getTextColor()
    {
        java.awt.Color a = this.textColor;
        return a;
    }
    
    public void drawLinkAssociatedElements(java.awt.Graphics a, aia.graph.common.Link a0, com.cim.AIA.Line a1)
    {
    }
    
    public void drawNodeAssociatedElements(java.awt.Graphics a, aia.graph.common.GraphMapsNode a0)
    {
        DFSGraphMaps a1 = this.graphMaps;
        label1: {
            label0: {
                if(a1 != null)
                {
                    break label0;
                }
                break label1;
            }
            java.awt.Font a2 = a.getFont();
            java.awt.Font a3 = new java.awt.Font("Arial", 1, 10);
            a.setFont(a3);
            int i = a0.getX();
            int i0 = a0.getY();
            DFSGraphCanvasCommon a4 = this.commons;
            int i1 = a4.getGraphX();
            DFSGraphCanvasCommon a5 = this.commons;
            int i2 = a5.getGraphY();
            java.awt.FontMetrics a6 = a.getFontMetrics();
            DFSGraphMaps a7 = this.graphMaps;
            int i3 = a7.getKMarker();
            int i4 = a0.getIntValue();
            if(i3 == i4)
            {
                java.awt.Color a8 = java.awt.Color.black;
                a.setColor(a8);
                a.drawString("k", i1, i2);
                java.awt.Color a9 = java.awt.Color.lightGray;
                a.setColor(a9);
                int i5 = a6.stringWidth("k");
                int i6 = i5 / 2;
                int i7 = i1 + i6;
                com.cim.AIA.Line a10 = new com.cim.AIA.Line(i7, i2, i, i0);
                java.awt.Color a11 = java.awt.Color.lightGray;
                a10.setColor(a11);
                a10.showArrowHead(true);
                a10.setDistanceFromStartForArrowHead(-3);
                a10.draw(a);
            }
            DFSGraphMaps a12 = this.graphMaps;
            aia.graph.common.GraphMapsNode a13 = a12.getAdjNode();
            label2: {
                if(a13 == null)
                {
                    break label2;
                }
                DFSGraphMaps a14 = this.graphMaps;
                aia.graph.common.GraphMapsNode a15 = a14.getAdjNode();
                int i8 = a15.getTo();
                int i9 = a0.getIntValue();
                if(i8 == i9)
                {
                    java.awt.Color a16 = java.awt.Color.black;
                    a.setColor(a16);
                    int i10 = a6.stringWidth("adj_node");
                    int i11 = i - i10;
                    int i12 = i11 - 10;
                    a.drawString("adj_node", i12, i0);
                    java.awt.Color a17 = java.awt.Color.lightGray;
                    a.setColor(a17);
                    int i13 = i - 10;
                    com.cim.AIA.Line a18 = new com.cim.AIA.Line(i13, i0, i, i0);
                    java.awt.Color a19 = java.awt.Color.lightGray;
                    a18.setColor(a19);
                    a18.showArrowHead(true);
                    a18.setDistanceFromStartForArrowHead(-3);
                    a18.draw(a);
                }
            }
            a.setFont(a2);
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
            DFSGraphMaps dummy = (DFSGraphMaps)a2;
            DFSGraphMaps a3 = (DFSGraphMaps)a2;
            this.graphMaps = a3;
            DFSGraphCanvasCommon a4 = this.commons;
            if(a4 == null)
            {
                DFSGraphMaps a5 = this.graphMaps;
                DFSGraphCanvasCommon a6 = new DFSGraphCanvasCommon(this, a5);
                this.commons = a6;
            }
            this.removeAllSelectables();
            DFSGraphCanvasCommon a7 = this.commons;
            DFSGraphMaps a8 = this.graphMaps;
            java.util.Vector a9 = a8.getNodes();
            a7.setNodes(a9, 30);
            DFSGraphCanvasCommon a10 = this.commons;
            DFSGraphMaps a11 = this.graphMaps;
            java.util.Vector a12 = a11.getLinks();
            a10.setLinks(a12);
            DFSGraphCanvasCommon a13 = this.commons;
            a13.adjustCanvasSize();
            DFSGraphMaps a14 = this.graphMaps;
            a14.removeAllAnimationRequests();
            this.repaint();
        }
    }
}