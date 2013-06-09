public class MSTKGraphMapsCanvas extends aia.graph.common.GraphMapsCanvas implements aia.graph.common.GraphDialogEventHandler {
    protected MSTKGraphMaps graphMaps;
    protected MSTKGraphCanvasCommon commons;
    protected java.awt.Font normalFont;
    protected java.awt.Color textColor;
    final public static int GRAPH_X = 30;
    final public static int GRAPH_Y = 330;
    final public static int NODE_WIDTH = 20;
    final public static int NODE_HEIGHT = 20;
    
    MSTKGraphMapsCanvas()
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
        MSTKGraphCanvasCommon a = this.commons;
        a.processDialogEvent(s, s0);
    }
    
    public void processMouseMotionEvent(java.awt.event.MouseEvent a)
    {
        MSTKGraphCanvasCommon a0 = this.commons;
        int i = a.getX();
        int i0 = a.getY();
        a0.mouseMoved(i, i0);
    }
    
    public void mouseReleased(java.awt.event.MouseEvent a)
    {
        MSTKGraphCanvasCommon a0 = this.commons;
        a0.mouseReleased();
    }
    
    public void mousePressed(java.awt.event.MouseEvent a)
    {
        MSTKGraphCanvasCommon a0 = this.commons;
        int i = a.getX();
        int i0 = a.getY();
        a0.mousePressed(i, i0);
        ((aia.graph.common.GraphMapsCanvas)this).mousePressed(a);
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
        MSTKGraphCanvasCommon a4 = this.commons;
        if(a4 != null)
        {
            MSTKGraphCanvasCommon a5 = this.commons;
            a5.drawLinks(a);
            MSTKGraphCanvasCommon a6 = this.commons;
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
            MSTKGraphCanvasCommon a4 = this.commons;
            if(a4 == null)
            {
                MSTKGraphMaps a5 = this.graphMaps;
                MSTKGraphCanvasCommon a6 = new MSTKGraphCanvasCommon(this, (aia.graph.common.GraphMaps)a5);
                this.commons = a6;
            }
            this.removeAllSelectables();
            MSTKGraphCanvasCommon a7 = this.commons;
            MSTKGraphMaps a8 = this.graphMaps;
            java.util.Vector a9 = a8.getNodes();
            a7.setNodes(a9, 30);
            MSTKGraphCanvasCommon a10 = this.commons;
            MSTKGraphMaps a11 = this.graphMaps;
            java.util.Vector a12 = a11.getLinks();
            a10.setLinks(a12);
            MSTKGraphCanvasCommon a13 = this.commons;
            a13.adjustCanvasSize();
            MSTKGraphMaps a14 = this.graphMaps;
            a14.removeAllAnimationRequests();
            this.repaint();
        }
    }
}