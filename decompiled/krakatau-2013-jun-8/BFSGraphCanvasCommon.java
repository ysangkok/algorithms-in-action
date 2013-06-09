public class BFSGraphCanvasCommon extends aia.graph.common.GraphCanvasCommon {
    protected int GRAPH_X;
    protected int GRAPH_Y;
    protected java.util.Vector vecNodes;
    protected java.util.Vector vecLinks;
    protected int m_dragNodeIndex;
    protected java.awt.Frame m_frmEditLink;
    protected aia.graph.common.InputDialog m_dlgEditLink;
    protected java.awt.Frame m_frmKeyChange;
    protected aia.graph.common.InputDialog m_dlgKeyChange;
    protected aia.graph.common.GraphMapsNode m_KeyChangeNode;
    protected aia.graph.common.GraphMapsCanvas m_canvas;
    protected aia.graph.common.GraphMaps m_algorithm;
    
    BFSGraphCanvasCommon(aia.graph.common.GraphMapsCanvas a, aia.graph.common.GraphMaps a0)
    {
        super();
        this.GRAPH_X = 30;
        this.GRAPH_Y = 30;
        this.vecNodes = null;
        this.vecLinks = null;
        this.m_dragNodeIndex = -1;
        this.m_frmEditLink = null;
        this.m_dlgEditLink = null;
        this.m_frmKeyChange = null;
        this.m_dlgKeyChange = null;
        this.m_KeyChangeNode = null;
        this.m_canvas = a;
        this.m_algorithm = a0;
    }
    
    public int getGraphX()
    {
        int i = this.GRAPH_X;
        return i;
    }
    
    public int getGraphY()
    {
        int i = this.GRAPH_Y;
        return i;
    }
    
    public java.util.Vector getNodes()
    {
        java.util.Vector a = this.vecNodes;
        return a;
    }
    
    public java.util.Vector getLinks()
    {
        java.util.Vector a = this.vecLinks;
        return a;
    }
    
    public void setNodes(java.util.Vector a, int i)
    {
        this.vecNodes = a;
        this.GRAPH_X = i;
        java.util.Vector a0 = this.vecNodes;
        label0: {
            if(a0 == null)
            {
                break label0;
            }
            int i0 = 0;
            while(true)
            {
                java.util.Vector a1 = this.vecNodes;
                int i1 = a1.size();
                if(i0 >= i1)
                {
                    break;
                }
                else
                {
                    java.util.Vector a2 = this.vecNodes;
                    Object a3 = a2.elementAt(i0);
                    aia.graph.common.GraphMapsNode dummy = (aia.graph.common.GraphMapsNode)a3;
                    aia.graph.common.GraphMapsNode a4 = (aia.graph.common.GraphMapsNode)a3;
                    int i2 = a4.getX();
                    int i3 = this.GRAPH_X;
                    int i4 = i2 + i3;
                    int i5 = a4.getY();
                    int i6 = this.GRAPH_Y;
                    int i7 = i5 + i6;
                    java.awt.Point a5 = new java.awt.Point(i4, i7);
                    a4.setPosition(a5);
                    aia.graph.common.GraphMapsCanvas a6 = this.m_canvas;
                    a6.addSelectable((com.cim.AIA.Selectable)a4);
                    int i8 = i0 + 1;
                    i0 = i8;
                }
            }
        }
    }
    
    public void setLinks(java.util.Vector a)
    {
        this.vecLinks = a;
    }
    
    public void adjustCanvasSize()
    {
        aia.graph.common.GraphMapsCanvas a = this.m_canvas;
        a.setSize(440, 360);
        java.util.Vector a0 = this.vecNodes;
        label1: {
            int i = 0;
            int i0 = 0;
            label0: {
                if(a0 != null)
                {
                    break label0;
                }
                break label1;
            }
            int i1 = 0;
            int i2 = 0;
            int i3 = 0;
            while(true)
            {
                int i4 = 0;
                int i5 = 0;
                int i6 = 0;
                int i7 = 0;
                int i8 = 0;
                int i9 = 0;
                java.util.Vector a1 = this.vecNodes;
                int i10 = i1;
                int i11 = i2;
                int i12 = a1.size();
                i = i10;
                i0 = i11;
                int i13 = i;
                int i14 = i0;
                if(i3 >= i12)
                {
                    break;
                }
                else
                {
                    i4 = i13;
                    i5 = i14;
                }
                java.util.Vector a2 = this.vecNodes;
                int i15 = i4;
                int i16 = i5;
                Object a3 = a2.elementAt(i3);
                int i17 = i15;
                int i18 = i16;
                aia.graph.common.GraphMapsNode dummy = (aia.graph.common.GraphMapsNode)a3;
                aia.graph.common.GraphMapsNode a4 = (aia.graph.common.GraphMapsNode)a3;
                int i19 = i17;
                int i20 = i18;
                int i21 = a4.getX();
                int i22 = i19;
                int i23 = i20;
                aia.graph.common.GraphMapsCanvas a5 = this.m_canvas;
                int i24 = i22;
                int i25 = i23;
                int i26 = i21 + 20;
                int i27 = i25;
                int i28 = i24;
                int i29 = i25;
                if(i26 <= i24)
                {
                    i6 = i28;
                    i7 = i29;
                }
                else
                {
                    int i30 = i27;
                    int i31 = a4.getX();
                    int i32 = i30;
                    aia.graph.common.GraphMapsCanvas a6 = this.m_canvas;
                    int i33 = i32;
                    int i34 = i31 + 20;
                    i6 = i34;
                    i7 = i33;
                }
                int i35 = a4.getY();
                int i36 = i6;
                int i37 = i7;
                aia.graph.common.GraphMapsCanvas a7 = this.m_canvas;
                int i38 = i36;
                int i39 = i37;
                int i40 = i35 + 20;
                int i41 = i38;
                int i42 = i38;
                int i43 = i39;
                if(i40 <= i39)
                {
                    i8 = i42;
                    i9 = i43;
                }
                else
                {
                    int i44 = i41;
                    int i45 = a4.getY();
                    int i46 = i44;
                    aia.graph.common.GraphMapsCanvas a8 = this.m_canvas;
                    int i47 = i46;
                    int i48 = i45 + 20;
                    i8 = i47;
                    i9 = i48;
                }
                int i49 = i3 + 1;
                i1 = i8;
                i2 = i9;
                i3 = i49;
            }
            int i50 = i <= 440?440:i;
            int i51 = i0 <= 360?360:i0;
            aia.graph.common.GraphMapsCanvas a9 = this.m_canvas;
            a9.setSize(i50, i51);
        }
    }
    
    public void processDialogEvent(String s, String s0)
    {
        aia.graph.common.GraphMaps a = this.m_algorithm;
        label0: {
            if(a == null)
            {
                break label0;
            }
            aia.graph.common.GraphMaps a0 = this.m_algorithm;
            int i = a0.getIsRunning()?1:0;
            if(i != 0)
            {
                break label0;
            }
            if(s0 == null)
            {
                break label0;
            }
            String s1 = localization.Messages.getString("BFSGraphCanvasCommon.0");
            int i0 = s.equals((Object)s1)?1:0;
            label1: {
                if(i0 == 0)
                {
                    break label1;
                }
                aia.graph.common.GraphMaps a1 = this.m_algorithm;
                aia.graph.common.GraphMapsNode a2 = this.m_KeyChangeNode;
                int i1 = a2.getIntValue();
                int i2 = Integer.parseInt(s0);
                a1.keyChange(i1, i2);
                aia.graph.common.GraphMaps a3 = this.m_algorithm;
                a3.setKeyChangeMode(false);
                break label0;
            }
            String s2 = localization.Messages.getString("BFSGraphCanvasCommon.1");
            int i3 = s.equals((Object)s2)?1:0;
            if(i3 != 0)
            {
                aia.graph.common.GraphMaps a4 = this.m_algorithm;
                int i4 = Integer.parseInt(s0);
                a4.setLinkWeight(i4);
                aia.graph.common.GraphMaps a5 = this.m_algorithm;
                a5.endEditLink();
            }
        }
        aia.graph.common.GraphMaps a6 = this.m_algorithm;
        label2: {
            if(a6 == null)
            {
                break label2;
            }
            aia.graph.common.GraphMaps a7 = this.m_algorithm;
            int i5 = a7.getKeyChangeMode()?1:0;
            if(i5 != 0)
            {
                aia.graph.common.GraphMaps a8 = this.m_algorithm;
                a8.setKeyChangeMode(false);
            }
            aia.graph.common.GraphMaps a9 = this.m_algorithm;
            int i6 = a9.editLinkStarted()?1:0;
            if(i6 != 0)
            {
                aia.graph.common.GraphMaps a10 = this.m_algorithm;
                a10.endEditLink();
            }
            aia.graph.common.GraphMapsCanvas a11 = this.m_canvas;
            aia.graph.common.GraphMapsCanvas a12 = this.m_canvas;
            aia.graph.common.GraphMaps a13 = this.m_algorithm;
            com.cim.AIA.RepaintEvent a14 = new com.cim.AIA.RepaintEvent((Object)a12, (Object)a13);
            a11.processRepaintEvent(a14);
        }
    }
}