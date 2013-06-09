package aia.graph.common;

public class GraphCanvasCommon {
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
    
    GraphCanvasCommon(aia.graph.common.GraphMapsCanvas a, aia.graph.common.GraphMaps a0)
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
    
    public GraphCanvasCommon()
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
                int i24 = i21 + 20;
                int i25 = i23;
                int i26 = i22;
                int i27 = i23;
                if(i24 <= i22)
                {
                    i6 = i26;
                    i7 = i27;
                }
                else
                {
                    int i28 = i25;
                    int i29 = a4.getX();
                    int i30 = i28;
                    int i31 = i29 + 20;
                    i6 = i31;
                    i7 = i30;
                }
                int i32 = a4.getY();
                int i33 = i6;
                int i34 = i7;
                int i35 = i32 + 20;
                int i36 = i33;
                int i37 = i33;
                int i38 = i34;
                if(i35 <= i34)
                {
                    i8 = i37;
                    i9 = i38;
                }
                else
                {
                    int i39 = i36;
                    int i40 = a4.getY();
                    int i41 = i39;
                    int i42 = i40 + 20;
                    i8 = i41;
                    i9 = i42;
                }
                int i43 = i3 + 1;
                i1 = i8;
                i2 = i9;
                i3 = i43;
            }
            int i44 = i <= 440?440:i;
            int i45 = i0 <= 360?360:i0;
            aia.graph.common.GraphMapsCanvas a5 = this.m_canvas;
            a5.setSize(i44, i45);
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
                int i3 = i2 + 10;
                int i4 = this.GRAPH_X;
                int i5 = i3 + i4;
                int i6 = a6.getY();
                int i7 = i6 + 10;
                int i8 = this.GRAPH_Y;
                int i9 = i7 + i8;
                int i10 = a7.getX();
                int i11 = i10 + 10;
                int i12 = this.GRAPH_X;
                int i13 = i11 + i12;
                int i14 = a7.getY();
                int i15 = i14 + 10;
                int i16 = this.GRAPH_Y;
                int i17 = i15 + i16;
                com.cim.AIA.Line a8 = new com.cim.AIA.Line(i5, i9, i13, i17);
                int i18 = a5.getOmniDirectional()?1:0;
                if(i18 == 0)
                {
                    a8.showArrowHead(true);
                    int i19 = a8.getLength();
                    int i20 = i19 - 15;
                    a8.setDistanceFromStartForArrowHead(i20);
                }
                java.awt.Color a9 = a5.getLinkColor();
                a8.setColor(a9);
                int i21 = a5.getHighlighted()?1:0;
                if(i21 != 0)
                {
                    a8.showAsThick(true);
                }
                int i22 = a5.useWeight()?1:0;
                if(i22 != 0)
                {
                    int i23 = a5.getWeight();
                    String s = Integer.toString(i23);
                    a8.setLabel(s);
                    a8.setXLabelOffset(5);
                    a8.setYLabelOffset(5);
                    java.awt.Color a10 = java.awt.Color.blue;
                    a8.setTextColor(a10);
                }
                a8.setDistanceFromStartForLabel(-2);
                a8.draw(a);
                int i24 = i0 + 1;
                i0 = i24;
            }
            int i25 = 0;
            while(true)
            {
                java.util.Vector a11 = this.vecLinks;
                int i26 = a11.size();
                if(i25 >= i26)
                {
                    break;
                }
                else
                {
                    java.util.Vector a12 = this.vecLinks;
                    Object a13 = a12.elementAt(i25);
                    aia.graph.common.Link dummy0 = (aia.graph.common.Link)a13;
                    aia.graph.common.Link a14 = (aia.graph.common.Link)a13;
                    aia.graph.common.NodeInfo a15 = a14.getStart();
                    aia.graph.common.NodeInfo a16 = a14.getEnd();
                    int i27 = a15.getX();
                    int i28 = i27 + 10;
                    int i29 = this.GRAPH_X;
                    int i30 = i28 + i29;
                    int i31 = a15.getY();
                    int i32 = i31 + 10;
                    int i33 = this.GRAPH_Y;
                    int i34 = i32 + i33;
                    int i35 = a16.getX();
                    int i36 = i35 + 10;
                    int i37 = this.GRAPH_X;
                    int i38 = i36 + i37;
                    int i39 = a16.getY();
                    int i40 = i39 + 10;
                    int i41 = this.GRAPH_Y;
                    int i42 = i40 + i41;
                    com.cim.AIA.Line a17 = new com.cim.AIA.Line(i30, i34, i38, i42);
                    aia.graph.common.GraphMapsCanvas a18 = this.m_canvas;
                    a18.drawLinkAssociatedElements(a, a14, a17);
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
                        aia.graph.common.GraphMaps a6 = this.m_algorithm;
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
                    aia.graph.common.GraphMapsCanvas a12 = this.m_canvas;
                    a12.drawNodeAssociatedElements(a, a11);
                    int i8 = i6 + 1;
                    i6 = i8;
                }
            }
        }
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
    
    public java.util.Vector getLinks()
    {
        java.util.Vector a = this.vecLinks;
        return a;
    }
    
    public java.util.Vector getNodes()
    {
        java.util.Vector a = this.vecNodes;
        return a;
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
            int i2 = i - 10;
            int i3 = i0 - 10;
            aia.graph.common.GraphMaps a = this.m_algorithm;
            label2: {
                if(a == null)
                {
                    break label2;
                }
                aia.graph.common.GraphMaps a0 = this.m_algorithm;
                int i4 = a0.getIsRunning()?1:0;
                if(i4 == 0)
                {
                    aia.graph.common.GraphMaps a1 = this.m_algorithm;
                    int i5 = this.m_dragNodeIndex;
                    int i6 = this.GRAPH_X;
                    int i7 = i2 - i6;
                    int i8 = this.GRAPH_Y;
                    int i9 = i3 - i8;
                    a1.setNodePosition(i5, i7, i9);
                }
            }
        }
    }
    
    public void mousePressed(int i, int i0)
    {
        aia.graph.common.GraphMaps a = this.m_algorithm;
        label4: {
            aia.graph.common.GraphMapsNode a0 = null;
            label3: {
                label1: {
                    label0: {
                        if(a == null)
                        {
                            break label0;
                        }
                        aia.graph.common.GraphMaps a1 = this.m_algorithm;
                        int i1 = a1.getIsRunning()?1:0;
                        if(i1 != 0)
                        {
                            break label0;
                        }
                        aia.graph.common.GraphMaps a2 = this.m_algorithm;
                        int i2 = a2.getInsertionMode()?1:0;
                        if(i2 == 0)
                        {
                            break label0;
                        }
                        aia.graph.common.GraphMaps a3 = this.m_algorithm;
                        int i3 = this.GRAPH_X;
                        int i4 = i - i3;
                        int i5 = this.GRAPH_Y;
                        int i6 = i0 - i5;
                        a3.insertNewNode(i4, i6);
                        aia.graph.common.GraphMaps a4 = this.m_algorithm;
                        a4.setInsertionMode(false);
                        break label1;
                    }
                    aia.graph.common.GraphMaps a5 = this.m_algorithm;
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
            aia.graph.common.GraphMaps a10 = this.m_algorithm;
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
                    aia.graph.common.GraphMapsCanvas a13 = this.m_canvas;
                    java.awt.Frame a14 = this.m_frmKeyChange;
                    String s = localization.Messages.getString("GraphCanvasCommon.2");
                    String s0 = localization.Messages.getString("GraphCanvasCommon.3");
                    int i19 = a0.getIntValue();
                    aia.graph.common.InputDialog a15 = new aia.graph.common.InputDialog((aia.graph.common.GraphDialogEventHandler)a13, a14, s, true, s0, i19);
                    this.m_dlgKeyChange = a15;
                    aia.graph.common.InputDialog a16 = this.m_dlgKeyChange;
                    a16.show();
                    break label6;
                }
                aia.graph.common.GraphMaps a17 = this.m_algorithm;
                int i20 = a17.getDeleteMode()?1:0;
                label7: {
                    if(i20 == 0)
                    {
                        break label7;
                    }
                    aia.graph.common.GraphMaps a18 = this.m_algorithm;
                    int i21 = a0.getIntValue();
                    a18.deleteNode(i21);
                    aia.graph.common.GraphMaps a19 = this.m_algorithm;
                    a19.setDeleteMode(false);
                    break label6;
                }
                aia.graph.common.GraphMaps a20 = this.m_algorithm;
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
                aia.graph.common.GraphMaps a21 = this.m_algorithm;
                int i24 = a21.getEditLinkStep();
                label9: {
                    aia.graph.common.GraphMaps a22 = null;
                    switch(i24){
                        case 2: {
                            a22 = this.m_algorithm;
                            break;
                        }
                        case 1: {
                            aia.graph.common.GraphMaps a23 = this.m_algorithm;
                            int i25 = a0.getIntValue();
                            a23.setStartNode(i25);
                            break label9;
                        }
                    }
                    int i26 = a0.getIntValue();
                    a22.setEndNode(i26);
                    aia.graph.common.GraphMaps a24 = this.m_algorithm;
                    int i27 = a24.needPromptForLinkWeight()?1:0;
                    if(i27 != 0)
                    {
                        java.awt.Frame a25 = new java.awt.Frame();
                        this.m_frmEditLink = a25;
                        java.awt.Frame a26 = this.m_frmEditLink;
                        a26.setSize(100, 20);
                        aia.graph.common.GraphMapsCanvas a27 = this.m_canvas;
                        java.awt.Frame a28 = this.m_frmEditLink;
                        String s1 = localization.Messages.getString("GraphCanvasCommon.4");
                        String s2 = localization.Messages.getString("GraphCanvasCommon.5");
                        aia.graph.common.GraphMaps a29 = this.m_algorithm;
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
    
    public void mouseReleased()
    {
        int i = this.m_dragNodeIndex;
        if(i != -1)
        {
            this.m_dragNodeIndex = -1;
            aia.graph.common.GraphMapsCanvas a = this.m_canvas;
            aia.graph.common.GraphMapsCanvas a0 = this.m_canvas;
            aia.graph.common.GraphMaps a1 = this.m_algorithm;
            com.cim.AIA.RepaintEvent a2 = new com.cim.AIA.RepaintEvent((Object)a0, (Object)a1);
            a.processRepaintEvent(a2);
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
            String s1 = localization.Messages.getString("GraphCanvasCommon.0");
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
            String s2 = localization.Messages.getString("GraphCanvasCommon.1");
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
    
    public void setLinks(java.util.Vector a)
    {
        this.vecLinks = a;
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
}