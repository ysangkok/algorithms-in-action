public class TransclosureGraphMapsCanvas extends aia.graph.common.GraphMapsCanvas implements aia.graph.common.GraphDialogEventHandler {
    protected TransclosureGraphMaps graphMaps;
    protected TransclosureGraphCanvasCommon commons;
    protected java.awt.Font normalFont;
    protected java.awt.Color textColor;
    final public static int GRAPH_X = 30;
    final public static int GRAPH_Y = 30;
    final public static int NODE_WIDTH = 20;
    final public static int NODE_HEIGHT = 20;
    protected java.util.Vector vecNodes;
    protected com.cim.AIA.Line m_linkJustAdded;
    protected boolean m_linkJustAddedVisible;
    
    TransclosureGraphMapsCanvas()
    {
        super();
        this.commons = null;
        this.vecNodes = null;
        com.cim.AIA.Line a = new com.cim.AIA.Line(0, 0, 0, 0);
        this.m_linkJustAdded = a;
        this.m_linkJustAddedVisible = false;
        com.cim.AIA.MultipleTween a0 = new com.cim.AIA.MultipleTween((com.cim.AIA.Paintable)this);
        this.tweens = a0;
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
        TransclosureGraphCanvasCommon a = this.commons;
        a.processDialogEvent(s, s0);
    }
    
    public void processMouseMotionEvent(java.awt.event.MouseEvent a)
    {
        TransclosureGraphCanvasCommon a0 = this.commons;
        int i = a.getX();
        int i0 = a.getY();
        a0.mouseMoved(i, i0);
    }
    
    public void mouseReleased(java.awt.event.MouseEvent a)
    {
        TransclosureGraphCanvasCommon a0 = this.commons;
        a0.mouseReleased();
    }
    
    public void mousePressed(java.awt.event.MouseEvent a)
    {
        TransclosureGraphCanvasCommon a0 = this.commons;
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
        TransclosureGraphCanvasCommon a4 = this.commons;
        label1: {
            label0: {
                if(a4 != null)
                {
                    break label0;
                }
                break label1;
            }
            TransclosureGraphCanvasCommon a5 = this.commons;
            a5.drawLinks(a);
            int i = this.m_linkJustAddedVisible?1:0;
            if(i != 0)
            {
                com.cim.AIA.Line a6 = this.m_linkJustAdded;
                a6.draw(a);
            }
            TransclosureGraphCanvasCommon a7 = this.commons;
            a7.drawNodes(a);
        }
    }
    
    private aia.graph.common.GraphMapsNode findNode(int i)
    {
        int i0 = 0;
        while(true)
        {
            aia.graph.common.GraphMapsNode a = null;
            java.util.Vector a0 = this.vecNodes;
            int i1 = a0.size();
            label1: {
                label0: {
                    if(i0 < i1)
                    {
                        break label0;
                    }
                    a = null;
                    break label1;
                }
                java.util.Vector a1 = this.vecNodes;
                Object a2 = a1.elementAt(i0);
                aia.graph.common.GraphMapsNode dummy = (aia.graph.common.GraphMapsNode)a2;
                aia.graph.common.GraphMapsNode a3 = (aia.graph.common.GraphMapsNode)a2;
                int i2 = a3.getIntValue();
                if(i2 != i)
                {
                    int i3 = i0 + 1;
                    i0 = i3;
                    continue;
                }
                a = a3;
            }
            return a;
        }
    }
    
    public void drawLinkAssociatedElements(java.awt.Graphics a, aia.graph.common.Link a0, com.cim.AIA.Line a1)
    {
    }
    
    public void drawNodeAssociatedElements(java.awt.Graphics a, aia.graph.common.GraphMapsNode a0)
    {
        TransclosureGraphMaps a1 = this.graphMaps;
        label1: {
            String s = null;
            String s0 = null;
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
            int i1 = a0.getWidth();
            TransclosureGraphCanvasCommon a4 = this.commons;
            int i2 = a4.getGraphX();
            TransclosureGraphCanvasCommon a5 = this.commons;
            int i3 = a5.getGraphY();
            java.awt.FontMetrics a6 = a.getFontMetrics();
            TransclosureGraphMaps a7 = this.graphMaps;
            int i4 = a7.getX();
            int i5 = a0.getIntValue();
            String s1 = null;
            String s2 = i4 != i5?s1:"x";
            TransclosureGraphMaps a8 = this.graphMaps;
            int i6 = a8.getY();
            int i7 = a0.getIntValue();
            label2: {
                if(i6 != i7)
                {
                    s = s2;
                    break label2;
                }
                if(s2 == null)
                {
                    s = "y";
                }
                else
                {
                    StringBuilder a9 = new StringBuilder();
                    StringBuilder a10 = a9.append(s2);
                    StringBuilder a11 = a10.append(",y");
                    String s3 = a11.toString();
                    s = s3;
                }
            }
            TransclosureGraphMaps a12 = this.graphMaps;
            int i8 = a12.getJ();
            int i9 = a0.getIntValue();
            label3: {
                if(i8 != i9)
                {
                    s0 = s;
                    break label3;
                }
                if(s == null)
                {
                    s0 = "j";
                }
                else
                {
                    StringBuilder a13 = new StringBuilder();
                    StringBuilder a14 = a13.append(s);
                    StringBuilder a15 = a14.append(",j");
                    String s4 = a15.toString();
                    s0 = s4;
                }
            }
            if(s0 != null)
            {
                java.awt.Color a16 = java.awt.Color.black;
                a.setColor(a16);
                int i10 = a6.stringWidth(s0);
                int i11 = i - i10;
                int i12 = i11 - 10;
                a.drawString(s0, i12, i0);
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
            this.m_linkJustAddedVisible = false;
            Object a2 = a.paramObj;
            TransclosureGraphMaps dummy = (TransclosureGraphMaps)a2;
            TransclosureGraphMaps a3 = (TransclosureGraphMaps)a2;
            this.graphMaps = a3;
            TransclosureGraphCanvasCommon a4 = this.commons;
            if(a4 == null)
            {
                TransclosureGraphMaps a5 = this.graphMaps;
                TransclosureGraphCanvasCommon a6 = new TransclosureGraphCanvasCommon((aia.graph.common.GraphMapsCanvas)this, (aia.graph.common.GraphMaps)a5);
                this.commons = a6;
            }
            this.removeAllSelectables();
            TransclosureGraphMaps a7 = this.graphMaps;
            java.util.Vector a8 = a7.getNodes();
            this.vecNodes = a8;
            TransclosureGraphCanvasCommon a9 = this.commons;
            java.util.Vector a10 = this.vecNodes;
            a9.setNodes(a10, 30);
            TransclosureGraphCanvasCommon a11 = this.commons;
            java.util.Vector a12 = a11.getNodes();
            this.vecNodes = a12;
            TransclosureGraphCanvasCommon a13 = this.commons;
            TransclosureGraphMaps a14 = this.graphMaps;
            java.util.Vector a15 = a14.getLinks();
            a13.setLinks(a15);
            TransclosureGraphCanvasCommon a16 = this.commons;
            a16.adjustCanvasSize();
            TransclosureGraphMaps a17 = this.graphMaps;
            com.cim.AIA.Line a18 = this.m_linkJustAdded;
            java.util.Vector a19 = this.vecNodes;
            int i = this.numberOfTweenSteps;
            com.cim.AIA.MultipleTween a20 = a17.generateTweens((com.cim.AIA.Paintable)this, a18, a19, i);
            if(a20 != null)
            {
                this.addTween((com.cim.AIA.Tween)a20);
            }
            com.cim.AIA.MultipleTween a21 = this.tweens;
            int i0 = a21.getNumberOfTweens();
            if(i0 > 0)
            {
                this.m_linkJustAddedVisible = true;
                com.cim.AIA.MultipleTween a22 = this.tweens;
                a22.run();
            }
            TransclosureGraphMaps a23 = this.graphMaps;
            a23.removeAllAnimationRequests();
            this.repaint();
        }
    }
}