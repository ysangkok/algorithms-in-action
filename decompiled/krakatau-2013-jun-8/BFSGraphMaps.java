public class BFSGraphMaps extends aia.graph.common.GraphMaps implements com.cim.AIA.ColorSelectionListener, com.cim.AIA.MethodSelectionListener {
    public BFSGraphMaps(com.cim.AIA.AlgorithmThread a, Object a0)
    {
        super(a, a0);
        this.initialise(a0);
        com.cim.AIA.ConfigurationManager a1 = com.cim.AIA.ConfigurationManager.getConfigurationManager();
        java.awt.Color a2 = this.highlightColor;
        String s = BFSGraphMaps.HIGHLIGHT_COLOR;
        com.cim.AIA.ColorSelection a3 = new com.cim.AIA.ColorSelection(a2, s);
        a3.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        a1.addColorSelection(a3);
        java.awt.Color a4 = this.unvisitedNodeColor;
        String s0 = BFSGraphMaps.NODE_COLOR;
        com.cim.AIA.ColorSelection a5 = new com.cim.AIA.ColorSelection(a4, s0);
        a5.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        a1.addColorSelection(a5);
        a1.addColorSelectionListener((com.cim.AIA.ColorSelectionListener)this);
    }
    
    protected void run()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        a.algorithmStartRunInitialisations();
        int i = this.m_backMode?1:0;
        if(i != 0)
        {
            this.m_backMode = false;
            int[] a0 = this.m_stored_data;
            this.initialise((Object)a0);
            this.m_stored_data = null;
        }
        aia.graph.common.LinkContainer a1 = new aia.graph.common.LinkContainer();
        this.visited_links = a1;
        this.m_bIsRunning = true;
        this.setPosition("1");
        aia.graph.common.GraphAlgorithmCommon a2 = this.commons;
        int i0 = a2.getNumberOfNodes();
        int[] a3 = new int[i0];
        this.visit_sequence = a3;
        int i1 = 0;
        while(true)
        {
            aia.graph.common.GraphAlgorithmCommon a4 = this.commons;
            int i2 = a4.getNumberOfNodes();
            if(i1 >= i2)
            {
                break;
            }
            else
            {
                int[] a5 = this.visit_sequence;
                a5[i1] = -1;
                int i3 = i1 + 1;
                i1 = i3;
            }
        }
        this.setPosition("1.2.1");
        int i4 = 0;
        while(true)
        {
            aia.graph.common.GraphAlgorithmCommon a6 = this.commons;
            int i5 = a6.getNumberOfNodes();
            if(i4 >= i5)
            {
                break;
            }
            else
            {
                this.k_marker = i4;
                this.setPosition("1.2.3");
                int[] a7 = this.visit_sequence;
                a7[i4] = 0;
                this.setPosition("1.2.4");
                int i6 = i4 + 1;
                i4 = i6;
            }
        }
        this.setPosition("1.2.3");
        this.setPosition("1.2.5");
        this.visit_counter = 0;
        this.setPosition("1.3.1");
        aia.graph.common.Queue a8 = new aia.graph.common.Queue();
        this.queue = a8;
        this.setPosition("1.4.1");
        this.setPosition("1.5");
        int i7 = 0;
        while(true)
        {
            aia.graph.common.GraphAlgorithmCommon a9 = this.commons;
            int i8 = a9.getNumberOfNodes();
            if(i7 >= i8)
            {
                this.setPosition("2.1");
                this.setPosition("8");
                this.m_bIsRunning = false;
                return;
            }
            this.k_marker = i7;
            this.setPosition("2.1");
            this.setPosition("3.1");
            int[] a10 = this.visit_sequence;
            int i9 = a10[i7];
            if(i9 == 0)
            {
                this.setPosition("4");
                this.visit(i7);
            }
            int i10 = i7 + 1;
            i7 = i10;
        }
    }
    
    public void initialiseMethods(String s, com.cim.AIA.MethodSelectable a)
    {
        Object a0 = a;
        String s0 = BFSGraphMaps.VISIT_MODE_LABEL;
        com.cim.AIA.Logger a1 = this.getLogger();
        com.cim.AIA.BreakPoint a2 = this.getBreakPoint();
        com.cim.AIA.MethodSelection a3 = new com.cim.AIA.MethodSelection(s0, s, "BreadthFirstSearch.visit", "4", a1, a2);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelection(a3, true);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelectionListener((com.cim.AIA.MethodSelectionListener)this);
    }
    
    public com.cim.AIA.MultipleTween generateTweens(com.cim.AIA.Paintable a, Object a0, int i)
    {
        com.cim.AIA.MultipleTween a1 = null;
        java.util.Vector a2 = this.queue_requests;
        Object a3 = a;
        label2: {
            label1: {
                label0: {
                    if(a2 == null)
                    {
                        break label0;
                    }
                    java.util.Vector a4 = this.queue_requests;
                    int i0 = a4.size();
                    if(i0 != 0)
                    {
                        break label1;
                    }
                }
                a1 = null;
                break label2;
            }
            com.cim.AIA.ElementArray dummy = (com.cim.AIA.ElementArray)a0;
            com.cim.AIA.ElementArray a5 = (com.cim.AIA.ElementArray)a0;
            label3: {
                if(a5 != null)
                {
                    break label3;
                }
                a1 = null;
                break label2;
            }
            com.cim.AIA.MultipleTween a6 = new com.cim.AIA.MultipleTween((com.cim.AIA.Paintable)a3);
            aia.graph.common.GraphMapsNode a7 = null;
            int i1 = 0;
            label4: while(true)
            {
                aia.graph.common.GraphMapsNode a8 = null;
                java.util.Vector a9 = this.queue_requests;
                aia.graph.common.GraphMapsNode a10 = a7;
                int i2 = a9.size();
                aia.graph.common.GraphMapsNode a11 = a10;
                aia.graph.common.GraphMapsNode a12 = a11;
                if(i1 >= i2)
                {
                    break;
                }
                else
                {
                    a8 = a12;
                }
                java.util.Vector a13 = this.queue_requests;
                aia.graph.common.GraphMapsNode a14 = a8;
                Object a15 = a13.elementAt(i1);
                aia.graph.common.GraphMapsNode a16 = a14;
                aia.graph.common.QueueRequest dummy0 = (aia.graph.common.QueueRequest)a15;
                aia.graph.common.QueueRequest a17 = (aia.graph.common.QueueRequest)a15;
                aia.graph.common.GraphMapsNode a18 = a16;
                aia.graph.common.GraphMapsNode a19 = a18;
                int i3 = 0;
                while(true)
                {
                    aia.graph.common.GraphMapsNode a20 = null;
                    aia.graph.common.GraphMapsNode a21 = null;
                    int i4 = a5.getSize();
                    aia.graph.common.GraphMapsNode a22 = a19;
                    aia.graph.common.GraphMapsNode a23 = a22;
                    aia.graph.common.GraphMapsNode a24 = a22;
                    if(i3 >= i4)
                    {
                        aia.graph.common.GraphMapsNode a25 = a24;
                        int i5 = i1 + 1;
                        a7 = a25;
                        i1 = i5;
                        continue label4;
                    }
                    else
                    {
                        a20 = a23;
                    }
                    com.cim.AIA.Element a26 = a5.getElement(i3);
                    aia.graph.common.GraphMapsNode a27 = a20;
                    aia.graph.common.GraphMapsNode dummy1 = (aia.graph.common.GraphMapsNode)a26;
                    aia.graph.common.GraphMapsNode a28 = (aia.graph.common.GraphMapsNode)a26;
                    aia.graph.common.GraphMapsNode a29 = a27;
                    int i6 = a28.getIntValue();
                    aia.graph.common.GraphMapsNode a30 = a29;
                    int i7 = a17.getKey();
                    aia.graph.common.GraphMapsNode a31 = a30;
                    label5: {
                        aia.graph.common.GraphMapsNode a32 = null;
                        aia.graph.common.GraphMapsNode a33 = null;
                        aia.graph.common.GraphMapsNode a34 = null;
                        aia.graph.common.GraphMapsNode a35 = null;
                        aia.graph.common.GraphMapsNode a36 = a31;
                        aia.graph.common.GraphMapsNode a37 = a31;
                        if(i6 != i7)
                        {
                            a21 = a37;
                            break label5;
                        }
                        else
                        {
                            a32 = a36;
                        }
                        int i8 = a17.isInsert()?1:0;
                        aia.graph.common.GraphMapsNode a38 = a32;
                        label6: {
                            aia.graph.common.GraphMapsNode a39 = null;
                            aia.graph.common.GraphMapsNode a40 = a38;
                            aia.graph.common.GraphMapsNode a41 = a38;
                            if(i8 == 0)
                            {
                                a33 = a41;
                                break label6;
                            }
                            else
                            {
                                a39 = a40;
                            }
                            aia.graph.common.GraphMapsNode a42 = a39;
                            com.cim.AIA.InsertTween a43 = new com.cim.AIA.InsertTween((com.cim.AIA.Paintable)a3, (com.cim.AIA.Sizeable)a28, i);
                            aia.graph.common.GraphMapsNode a44 = a42;
                            a6.add((com.cim.AIA.Tween)a43);
                            aia.graph.common.GraphMapsNode a45 = a44;
                            a21 = a45;
                            break label5;
                        }
                        aia.graph.common.GraphMapsNode a46 = a33;
                        java.awt.Point a47 = a28.getPosition();
                        aia.graph.common.GraphMapsNode a48 = a46;
                        aia.graph.common.GraphMapsNode a49 = a48;
                        int i9 = aia.graph.common.GraphMapsCanvasExt.QUEUE_X;
                        aia.graph.common.GraphMapsNode a50 = a49;
                        int i10 = i9 - 50;
                        int i11 = aia.graph.common.GraphMapsCanvasExt.QUEUE_Y;
                        aia.graph.common.GraphMapsNode a51 = a50;
                        java.awt.Point a52 = new java.awt.Point(i10, i11);
                        aia.graph.common.GraphMapsNode a53 = a51;
                        com.cim.AIA.MoveTween a54 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Moveable)a28, a47, a52, false, i);
                        aia.graph.common.GraphMapsNode a55 = a53;
                        a6.add((com.cim.AIA.Tween)a54);
                        aia.graph.common.GraphMapsNode a56 = a55;
                        int i12 = i3 + 1;
                        aia.graph.common.GraphMapsNode a57 = a28;
                        aia.graph.common.GraphMapsNode a58 = a56;
                        int i13 = i12;
                        while(true)
                        {
                            int i14 = a5.getSize();
                            aia.graph.common.GraphMapsNode a59 = a58;
                            aia.graph.common.GraphMapsNode a60 = a59;
                            if(i13 >= i14)
                            {
                                a34 = a60;
                                break;
                            }
                            else
                            {
                                com.cim.AIA.Element a61 = a5.getElement(i13);
                                aia.graph.common.GraphMapsNode dummy2 = (aia.graph.common.GraphMapsNode)a61;
                                aia.graph.common.GraphMapsNode a62 = (aia.graph.common.GraphMapsNode)a61;
                                java.awt.Point a63 = a62.getPosition();
                                java.awt.Point a64 = a57.getPosition();
                                com.cim.AIA.MoveTween a65 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Moveable)a62, a63, a64, false, i);
                                a6.add((com.cim.AIA.Tween)a65);
                                int i15 = i13 + 1;
                                a57 = a62;
                                a58 = a62;
                                i13 = i15;
                            }
                        }
                        aia.graph.common.GraphMapsNode a66 = a34;
                        aia.graph.common.GraphMapsNode a67 = a34;
                        if(a34 == null)
                        {
                            a21 = a67;
                            break label5;
                        }
                        else
                        {
                            a35 = a66;
                        }
                        int i16 = this.isQueueOverFlow()?1:0;
                        aia.graph.common.GraphMapsNode a68 = a35;
                        aia.graph.common.GraphMapsNode a69 = a68;
                        aia.graph.common.GraphMapsNode a70 = a68;
                        if(i16 == 0)
                        {
                            a21 = a70;
                        }
                        else
                        {
                            aia.graph.common.GraphMapsNode a71 = a69;
                            a71.setIsVisible(true);
                            aia.graph.common.GraphMapsNode a72 = a71;
                            aia.graph.common.GraphMapsNode a73 = a72;
                            com.cim.AIA.InsertTween a74 = new com.cim.AIA.InsertTween((com.cim.AIA.Paintable)a3, (com.cim.AIA.Sizeable)a73, i);
                            aia.graph.common.GraphMapsNode a75 = a73;
                            a6.add((com.cim.AIA.Tween)a74);
                            a21 = a75;
                        }
                    }
                    int i17 = i3 + 1;
                    a19 = a21;
                    i3 = i17;
                }
            }
            int i18 = a6.getNumberOfTweens();
            a1 = i18 != 0?a6:null;
        }
        return a1;
    }
}