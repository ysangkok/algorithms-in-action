public class DFSGraphCanvasCommon extends aia.graph.common.GraphCanvasCommon {
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
    protected DFSGraphMapsCanvas m_canvas;
    protected DFSGraphMaps m_algorithm;
    
    DFSGraphCanvasCommon(DFSGraphMapsCanvas a, DFSGraphMaps a0)
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
                    DFSGraphMapsCanvas a6 = this.m_canvas;
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
        DFSGraphMapsCanvas a = this.m_canvas;
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
                DFSGraphMapsCanvas a5 = this.m_canvas;
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
                    DFSGraphMapsCanvas a6 = this.m_canvas;
                    int i33 = i32;
                    int i34 = i31 + 20;
                    i6 = i34;
                    i7 = i33;
                }
                int i35 = a4.getY();
                int i36 = i6;
                int i37 = i7;
                DFSGraphMapsCanvas a7 = this.m_canvas;
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
                    DFSGraphMapsCanvas a8 = this.m_canvas;
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
            DFSGraphMapsCanvas a9 = this.m_canvas;
            a9.setSize(i50, i51);
        }
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
            String s1 = localization.Messages.getString("DFSGraphCanvasCommon.0");
            int i0 = s.equals((Object)s1)?1:0;
            label1: {
                if(i0 == 0)
                {
                    break label1;
                }
                DFSGraphMaps a1 = this.m_algorithm;
                aia.graph.common.GraphMapsNode a2 = this.m_KeyChangeNode;
                int i1 = a2.getIntValue();
                int i2 = Integer.parseInt(s0);
                a1.keyChange(i1, i2);
                DFSGraphMaps a3 = this.m_algorithm;
                a3.setKeyChangeMode(false);
                break label0;
            }
            String s2 = localization.Messages.getString("DFSGraphCanvasCommon.1");
            int i3 = s.equals((Object)s2)?1:0;
            if(i3 != 0)
            {
                DFSGraphMaps a4 = this.m_algorithm;
                int i4 = Integer.parseInt(s0);
                a4.setLinkWeight(i4);
                DFSGraphMaps a5 = this.m_algorithm;
                a5.endEditLink();
            }
        }
        DFSGraphMaps a6 = this.m_algorithm;
        label2: {
            if(a6 == null)
            {
                break label2;
            }
            DFSGraphMaps a7 = this.m_algorithm;
            int i5 = a7.getKeyChangeMode()?1:0;
            if(i5 != 0)
            {
                DFSGraphMaps a8 = this.m_algorithm;
                a8.setKeyChangeMode(false);
            }
            DFSGraphMaps a9 = this.m_algorithm;
            int i6 = a9.editLinkStarted()?1:0;
            if(i6 != 0)
            {
                DFSGraphMaps a10 = this.m_algorithm;
                a10.endEditLink();
            }
            DFSGraphMapsCanvas a11 = this.m_canvas;
            DFSGraphMapsCanvas a12 = this.m_canvas;
            DFSGraphMaps a13 = this.m_algorithm;
            com.cim.AIA.RepaintEvent a14 = new com.cim.AIA.RepaintEvent((Object)a12, (Object)a13);
            a11.processRepaintEvent(a14);
        }
    }
    
    public void mouseMoved(int i, int i0)
    {
        int i1 = this.m_dragNodeIndex;
        label1: {
            label0: {
                if(i1 != -1)
                {
                    break label0;
                }
                break label1;
            }
            DFSGraphMapsCanvas a = this.m_canvas;
            int i2 = i - 10;
            DFSGraphMapsCanvas a0 = this.m_canvas;
            int i3 = i0 - 10;
            DFSGraphMaps a1 = this.m_algorithm;
            label2: {
                if(a1 == null)
                {
                    break label2;
                }
                DFSGraphMaps a2 = this.m_algorithm;
                int i4 = a2.getIsRunning()?1:0;
                if(i4 == 0)
                {
                    DFSGraphMaps a3 = this.m_algorithm;
                    int i5 = this.m_dragNodeIndex;
                    int i6 = this.GRAPH_X;
                    int i7 = i2 - i6;
                    int i8 = this.GRAPH_Y;
                    int i9 = i3 - i8;
                    a3.setNodePosition(i5, i7, i9);
                }
            }
        }
    }
    
    public void mouseReleased()
    {
        int i = this.m_dragNodeIndex;
        if(i != -1)
        {
            this.m_dragNodeIndex = -1;
            DFSGraphMapsCanvas a = this.m_canvas;
            DFSGraphMapsCanvas a0 = this.m_canvas;
            DFSGraphMaps a1 = this.m_algorithm;
            com.cim.AIA.RepaintEvent a2 = new com.cim.AIA.RepaintEvent((Object)a0, (Object)a1);
            a.processRepaintEvent(a2);
        }
    }
    
    public void mousePressed(int i, int i0)
    {
        DFSGraphMaps a = this.m_algorithm;
        label4: {
            aia.graph.common.GraphMapsNode a0 = null;
            label3: {
                label1: {
                    label0: {
                        if(a == null)
                        {
                            break label0;
                        }
                        DFSGraphMaps a1 = this.m_algorithm;
                        int i1 = a1.getIsRunning()?1:0;
                        if(i1 != 0)
                        {
                            break label0;
                        }
                        DFSGraphMaps a2 = this.m_algorithm;
                        int i2 = a2.getInsertionMode()?1:0;
                        if(i2 == 0)
                        {
                            break label0;
                        }
                        DFSGraphMaps a3 = this.m_algorithm;
                        int i3 = this.GRAPH_X;
                        int i4 = i - i3;
                        int i5 = this.GRAPH_Y;
                        int i6 = i0 - i5;
                        a3.insertNewNode(i4, i6);
                        DFSGraphMaps a4 = this.m_algorithm;
                        a4.setInsertionMode(false);
                        break label1;
                    }
                    DFSGraphMaps a5 = this.m_algorithm;
                    if(a5 == null)
                    {
                        break label1;
                    }
                    java.util.Vector a6 = this.vecNodes;
                    if(a6 == null)
                    {
                        break label1;
                    }
                    int i7 = 0;
                    while(true)
                    {
                        java.util.Vector a7 = this.vecNodes;
                        int i8 = a7.size();
                        if(i7 >= i8)
                        {
                            break;
                        }
                        java.util.Vector a8 = this.vecNodes;
                        Object a9 = a8.elementAt(i7);
                        aia.graph.common.GraphMapsNode dummy = (aia.graph.common.GraphMapsNode)a9;
                        a0 = (aia.graph.common.GraphMapsNode)a9;
                        int i9 = a0.getX();
                        label2: {
                            if(i <= i9)
                            {
                                break label2;
                            }
                            int i10 = a0.getX();
                            int i11 = a0.getWidth();
                            int i12 = i10 + i11;
                            if(i >= i12)
                            {
                                break label2;
                            }
                            int i13 = a0.getY();
                            if(i0 <= i13)
                            {
                                break label2;
                            }
                            int i14 = a0.getY();
                            int i15 = a0.getHeight();
                            int i16 = i14 + i15;
                            if(i0 < i16)
                            {
                                break label3;
                            }
                        }
                        int i17 = i7 + 1;
                        i7 = i17;
                    }
                }
                break label4;
            }
            DFSGraphMaps a10 = this.m_algorithm;
            int i18 = a10.getKeyChangeMode()?1:0;
            label6: {
                label5: {
                    if(i18 == 0)
                    {
                        break label5;
                    }
                    this.m_KeyChangeNode = a0;
                    java.awt.Frame a11 = new java.awt.Frame();
                    this.m_frmKeyChange = a11;
                    java.awt.Frame a12 = this.m_frmKeyChange;
                    a12.setSize(100, 20);
                    DFSGraphMapsCanvas a13 = this.m_canvas;
                    java.awt.Frame a14 = this.m_frmKeyChange;
                    String s = localization.Messages.getString("DFSGraphCanvasCommon.2");
                    String s0 = localization.Messages.getString("DFSGraphCanvasCommon.3");
                    int i19 = a0.getIntValue();
                    aia.graph.common.InputDialog a15 = new aia.graph.common.InputDialog((aia.graph.common.GraphDialogEventHandler)a13, a14, s, true, s0, i19);
                    this.m_dlgKeyChange = a15;
                    aia.graph.common.InputDialog a16 = this.m_dlgKeyChange;
                    a16.show();
                    break label6;
                }
                DFSGraphMaps a17 = this.m_algorithm;
                int i20 = a17.getDeleteMode()?1:0;
                label7: {
                    if(i20 == 0)
                    {
                        break label7;
                    }
                    DFSGraphMaps a18 = this.m_algorithm;
                    int i21 = a0.getIntValue();
                    a18.deleteNode(i21);
                    DFSGraphMaps a19 = this.m_algorithm;
                    a19.setDeleteMode(false);
                    break label6;
                }
                DFSGraphMaps a20 = this.m_algorithm;
                int i22 = a20.editLinkStarted()?1:0;
                label8: {
                    if(i22 != 0)
                    {
                        break label8;
                    }
                    int i23 = a0.getIntValue();
                    this.m_dragNodeIndex = i23;
                    break label6;
                }
                DFSGraphMaps a21 = this.m_algorithm;
                int i24 = a21.getEditLinkStep();
                label9: {
                    DFSGraphMaps a22 = null;
                    switch(i24){
                        case 2: {
                            a22 = this.m_algorithm;
                            break;
                        }
                        case 1: {
                            DFSGraphMaps a23 = this.m_algorithm;
                            int i25 = a0.getIntValue();
                            a23.setStartNode(i25);
                            break label9;
                        }
                    }
                    int i26 = a0.getIntValue();
                    a22.setEndNode(i26);
                    DFSGraphMaps a24 = this.m_algorithm;
                    int i27 = a24.needPromptForLinkWeight()?1:0;
                    if(i27 != 0)
                    {
                        java.awt.Frame a25 = new java.awt.Frame();
                        this.m_frmEditLink = a25;
                        java.awt.Frame a26 = this.m_frmEditLink;
                        a26.setSize(100, 20);
                        DFSGraphMapsCanvas a27 = this.m_canvas;
                        java.awt.Frame a28 = this.m_frmEditLink;
                        String s1 = localization.Messages.getString("DFSGraphCanvasCommon.4");
                        String s2 = localization.Messages.getString("DFSGraphCanvasCommon.5");
                        DFSGraphMaps a29 = this.m_algorithm;
                        int i28 = a29.getCurrentLinkLinkWeight();
                        aia.graph.common.InputDialog a30 = new aia.graph.common.InputDialog((aia.graph.common.GraphDialogEventHandler)a27, a28, s1, true, s2, i28);
                        this.m_dlgEditLink = a30;
                        aia.graph.common.InputDialog a31 = this.m_dlgEditLink;
                        a31.show();
                    }
                }
            }
        }
    }
    
    public void drawLinks(java.awt.Graphics a)
    {
        java.util.Vector a0 = this.vecLinks;
        label2: {
            label1: {
                label0: {
                    if(a0 == null)
                    {
                        break label0;
                    }
                    java.util.Vector a1 = this.vecLinks;
                    int i = a1.size();
                    if(i != 0)
                    {
                        break label1;
                    }
                }
                break label2;
            }
            int i0 = 0;
            while(true)
            {
                java.util.Vector a2 = this.vecLinks;
                int i1 = a2.size();
                if(i0 >= i1)
                {
                    break;
                }
                java.util.Vector a3 = this.vecLinks;
                Object a4 = a3.elementAt(i0);
                aia.graph.common.Link dummy = (aia.graph.common.Link)a4;
                aia.graph.common.Link a5 = (aia.graph.common.Link)a4;
                aia.graph.common.NodeInfo a6 = a5.getStart();
                aia.graph.common.NodeInfo a7 = a5.getEnd();
                int i2 = a6.getX();
                DFSGraphMapsCanvas a8 = this.m_canvas;
                int i3 = i2 + 10;
                int i4 = this.GRAPH_X;
                int i5 = i3 + i4;
                int i6 = a6.getY();
                DFSGraphMapsCanvas a9 = this.m_canvas;
                int i7 = i6 + 10;
                int i8 = this.GRAPH_Y;
                int i9 = i7 + i8;
                int i10 = a7.getX();
                DFSGraphMapsCanvas a10 = this.m_canvas;
                int i11 = i10 + 10;
                int i12 = this.GRAPH_X;
                int i13 = i11 + i12;
                int i14 = a7.getY();
                DFSGraphMapsCanvas a11 = this.m_canvas;
                int i15 = i14 + 10;
                int i16 = this.GRAPH_Y;
                int i17 = i15 + i16;
                com.cim.AIA.Line a12 = new com.cim.AIA.Line(i5, i9, i13, i17);
                int i18 = a5.getOmniDirectional()?1:0;
                if(i18 == 0)
                {
                    a12.showArrowHead(true);
                    int i19 = a12.getLength();
                    int i20 = i19 - 15;
                    a12.setDistanceFromStartForArrowHead(i20);
                }
                java.awt.Color a13 = a5.getLinkColor();
                a12.setColor(a13);
                int i21 = a5.getHighlighted()?1:0;
                if(i21 != 0)
                {
                    a12.showAsThick(true);
                }
                int i22 = a5.useWeight()?1:0;
                if(i22 != 0)
                {
                    int i23 = a5.getWeight();
                    String s = Integer.toString(i23);
                    a12.setLabel(s);
                    a12.setXLabelOffset(5);
                    a12.setYLabelOffset(5);
                    java.awt.Color a14 = java.awt.Color.blue;
                    a12.setTextColor(a14);
                }
                a12.setDistanceFromStartForLabel(-2);
                a12.draw(a);
                int i24 = i0 + 1;
                i0 = i24;
            }
            int i25 = 0;
            while(true)
            {
                java.util.Vector a15 = this.vecLinks;
                int i26 = a15.size();
                if(i25 >= i26)
                {
                    break;
                }
                else
                {
                    java.util.Vector a16 = this.vecLinks;
                    Object a17 = a16.elementAt(i25);
                    aia.graph.common.Link dummy0 = (aia.graph.common.Link)a17;
                    aia.graph.common.Link a18 = (aia.graph.common.Link)a17;
                    aia.graph.common.NodeInfo a19 = a18.getStart();
                    aia.graph.common.NodeInfo a20 = a18.getEnd();
                    int i27 = a19.getX();
                    DFSGraphMapsCanvas a21 = this.m_canvas;
                    int i28 = i27 + 10;
                    int i29 = this.GRAPH_X;
                    int i30 = i28 + i29;
                    int i31 = a19.getY();
                    DFSGraphMapsCanvas a22 = this.m_canvas;
                    int i32 = i31 + 10;
                    int i33 = this.GRAPH_Y;
                    int i34 = i32 + i33;
                    int i35 = a20.getX();
                    DFSGraphMapsCanvas a23 = this.m_canvas;
                    int i36 = i35 + 10;
                    int i37 = this.GRAPH_X;
                    int i38 = i36 + i37;
                    int i39 = a20.getY();
                    DFSGraphMapsCanvas a24 = this.m_canvas;
                    int i40 = i39 + 10;
                    int i41 = this.GRAPH_Y;
                    int i42 = i40 + i41;
                    com.cim.AIA.Line a25 = new com.cim.AIA.Line(i30, i34, i38, i42);
                    DFSGraphMapsCanvas a26 = this.m_canvas;
                    a26.drawLinkAssociatedElements(a, a18, a25);
                    int i43 = i25 + 1;
                    i25 = i43;
                }
            }
        }
    }
    
    public void drawNodes(java.awt.Graphics a)
    {
        java.util.Vector a0 = this.vecNodes;
        label2: {
            label1: {
                label0: {
                    if(a0 == null)
                    {
                        break label0;
                    }
                    java.util.Vector a1 = this.vecNodes;
                    int i = a1.size();
                    if(i != 0)
                    {
                        break label1;
                    }
                }
                break label2;
            }
            int i0 = 0;
            while(true)
            {
                java.util.Vector a2 = this.vecNodes;
                int i1 = a2.size();
                if(i0 >= i1)
                {
                    break;
                }
                java.util.Vector a3 = this.vecNodes;
                Object a4 = a3.elementAt(i0);
                aia.graph.common.GraphMapsNode dummy = (aia.graph.common.GraphMapsNode)a4;
                aia.graph.common.GraphMapsNode a5 = (aia.graph.common.GraphMapsNode)a4;
                int i2 = this.m_dragNodeIndex;
                label3: {
                    if(i2 == -1)
                    {
                        break label3;
                    }
                    int i3 = a5.getIntValue();
                    int i4 = this.m_dragNodeIndex;
                    if(i3 == i4)
                    {
                        DFSGraphMaps a6 = this.m_algorithm;
                        java.awt.Color a7 = a6.getHighlightColor();
                        a5.setBackgroundColor(a7);
                    }
                }
                a5.draw(a);
                int i5 = i0 + 1;
                i0 = i5;
            }
            int i6 = 0;
            while(true)
            {
                java.util.Vector a8 = this.vecNodes;
                int i7 = a8.size();
                if(i6 >= i7)
                {
                    break;
                }
                else
                {
                    java.util.Vector a9 = this.vecNodes;
                    Object a10 = a9.elementAt(i6);
                    aia.graph.common.GraphMapsNode dummy0 = (aia.graph.common.GraphMapsNode)a10;
                    aia.graph.common.GraphMapsNode a11 = (aia.graph.common.GraphMapsNode)a10;
                    DFSGraphMapsCanvas a12 = this.m_canvas;
                    a12.drawNodeAssociatedElements(a, a11);
                    int i8 = i6 + 1;
                    i6 = i8;
                }
            }
        }
    }
}