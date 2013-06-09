public class TransclosureGraphMaps extends aia.graph.common.GraphMaps implements com.cim.AIA.ColorSelectionListener {
    protected java.awt.Color textColor;
    protected java.awt.Color highlightLinkColor;
    protected java.awt.Color highlightColor;
    protected java.awt.Color unvisitedNodeColor;
    protected java.awt.Color backgroundColor;
    protected java.awt.Color xColor;
    protected java.awt.Color yColor;
    protected java.awt.Color jColor;
    protected java.awt.Color xyColor;
    protected java.awt.Color yjColor;
    protected java.awt.Color xjColor;
    final protected static String HIGHLIGHT_COLOR;
    final protected static String NODE_COLOR;
    protected aia.graph.common.GraphAlgorithmCommon commons;
    protected boolean m_bIsRunning;
    protected int m_x;
    protected int m_y;
    protected int m_j;
    protected boolean m_bInSecondLoop;
    protected aia.graph.common.Link link_just_added;
    protected aia.graph.common.LinkContainer added_links;
    protected java.util.Vector questions;
    final public static int UNMARKED = -10;
    protected boolean m_backMode;
    protected int[] m_stored_data;
    
    public TransclosureGraphMaps(com.cim.AIA.AlgorithmThread a, Object a0)
    {
        super(a, a0);
        java.awt.Color a1 = java.awt.Color.black;
        this.textColor = a1;
        java.awt.Color a2 = java.awt.Color.red;
        this.highlightLinkColor = a2;
        java.awt.Color a3 = java.awt.Color.yellow;
        this.highlightColor = a3;
        java.awt.Color a4 = new java.awt.Color(123, 202, 123);
        this.unvisitedNodeColor = a4;
        java.awt.Color a5 = java.awt.Color.white;
        this.backgroundColor = a5;
        java.awt.Color a6 = new java.awt.Color(255, 128, 128);
        this.xColor = a6;
        java.awt.Color a7 = new java.awt.Color(128, 255, 128);
        this.yColor = a7;
        java.awt.Color a8 = new java.awt.Color(128, 128, 255);
        this.jColor = a8;
        java.awt.Color a9 = new java.awt.Color(25, 35, 209);
        this.xyColor = a9;
        java.awt.Color a10 = new java.awt.Color(0, 187, 255);
        this.yjColor = a10;
        java.awt.Color a11 = new java.awt.Color(0, 255, 255);
        this.xjColor = a11;
        this.m_bIsRunning = false;
        this.m_x = -10;
        this.m_y = -10;
        this.m_j = -10;
        this.m_bInSecondLoop = false;
        this.link_just_added = null;
        this.added_links = null;
        this.questions = null;
        this.m_backMode = false;
        this.m_stored_data = null;
        this.initialise(a0);
        com.cim.AIA.ConfigurationManager a12 = com.cim.AIA.ConfigurationManager.getConfigurationManager();
        java.awt.Color a13 = this.highlightColor;
        String s = TransclosureGraphMaps.HIGHLIGHT_COLOR;
        com.cim.AIA.ColorSelection a14 = new com.cim.AIA.ColorSelection(a13, s);
        a14.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        a12.addColorSelection(a14);
        java.awt.Color a15 = this.unvisitedNodeColor;
        String s0 = TransclosureGraphMaps.NODE_COLOR;
        com.cim.AIA.ColorSelection a16 = new com.cim.AIA.ColorSelection(a15, s0);
        a16.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        a12.addColorSelection(a16);
        a12.addColorSelectionListener((com.cim.AIA.ColorSelectionListener)this);
    }
    
    public void reInitialise(Object a)
    {
        this.initialise(a);
    }
    
    public void setBackMode()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        if(a != null)
        {
            this.m_backMode = true;
            aia.graph.common.GraphAlgorithmCommon a0 = this.commons;
            com.cim.AIA.IntArray a1 = a0.getData();
            Object a2 = a1.copy();
            int[] dummy = (int[])a2;
            int[] a3 = (int[])a2;
            this.m_stored_data = a3;
        }
    }
    
    protected void initialise(Object a)
    {
        this.m_x = -10;
        this.m_y = -10;
        this.m_j = -10;
        this.m_bInSecondLoop = false;
        this.added_links = null;
        java.util.Vector a0 = new java.util.Vector();
        this.questions = a0;
        this.link_just_added = null;
        this.m_bIsRunning = false;
        aia.graph.common.GraphAlgorithmCommon a1 = this.commons;
        if(a1 != null)
        {
            aia.graph.common.GraphAlgorithmCommon a2 = this.commons;
            a2.setData(a);
        }
        else
        {
            aia.graph.common.GraphAlgorithmCommon a3 = new aia.graph.common.GraphAlgorithmCommon((aia.graph.common.GraphMaps)this, a);
            this.commons = a3;
        }
        this.setStructureShowing(false);
    }
    
    public void setNodePosition(int i, int i0, int i1)
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        a.setNodePosition(i, i0, i1);
    }
    
    public void setStructureShowing(boolean b)
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = b?1:0;
        a.setStructureShowing(i != 0);
    }
    
    public void setMatrixShowing(boolean b)
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = b?1:0;
        a.setMatrixShowing(i != 0);
    }
    
    public boolean getMatrixShowing()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = a.getMatrixShowing()?1:0;
        return i != 0;
    }
    
    public boolean getStructureShowing()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = a.getStructureShowing()?1:0;
        return i != 0;
    }
    
    public void setOmniDirectional(boolean b)
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = b?1:0;
        a.setOmniDirectional(i != 0);
    }
    
    public void zoomIn()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        a.zoomIn();
    }
    
    public void zoomOut()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        a.zoomOut();
    }
    
    public void setKeyChangeMode(boolean b)
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = b?1:0;
        a.setKeyChangeMode(i != 0);
    }
    
    public boolean getKeyChangeMode()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = a.getKeyChangeMode()?1:0;
        return i != 0;
    }
    
    public void keyChange(int i, int i0)
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        a.keyChange(i, i0);
    }
    
    public void startEditLink(Integer a)
    {
        aia.graph.common.GraphAlgorithmCommon a0 = this.commons;
        a0.startEditLink(a);
    }
    
    public void endEditLink()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        a.endEditLink();
    }
    
    public void setStartNode(int i)
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        a.setStartNode(i);
    }
    
    public void setEndNode(int i)
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        a.setEndNode(i);
    }
    
    public void setLinkWeight(int i)
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        a.setLinkWeight(i);
    }
    
    public boolean needPromptForLinkWeight()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = a.needPromptForLinkWeight()?1:0;
        return i != 0;
    }
    
    public int getCurrentLinkLinkWeight()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = a.getCurrentLinkLinkWeight();
        return i;
    }
    
    public boolean editLinkStarted()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = a.editLinkStarted()?1:0;
        return i != 0;
    }
    
    public int getEditLinkStep()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = a.getEditLinkStep();
        return i;
    }
    
    public int getStartNode()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = a.getStartNode();
        return i;
    }
    
    public int getEndNode()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = a.getEndNode();
        return i;
    }
    
    public void setInsertionMode(boolean b)
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = b?1:0;
        a.setInsertionMode(i != 0);
    }
    
    public boolean getInsertionMode()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = a.getInsertionMode()?1:0;
        return i != 0;
    }
    
    public void insertNewNode(int i, int i0)
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        a.insertNewNode(i, i0);
    }
    
    public void deleteNode(int i)
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        a.deleteNode(i);
    }
    
    public void setDeleteMode(boolean b)
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = b?1:0;
        a.setDeleteMode(i != 0);
    }
    
    public boolean getDeleteMode()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = a.getDeleteMode()?1:0;
        return i != 0;
    }
    
    public void editLink(aia.graph.common.GraphMapsNode a, int i)
    {
        aia.graph.common.GraphAlgorithmCommon a0 = this.commons;
        a0.editLink(a, i);
    }
    
    public com.cim.AIA.IntArray getData()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        com.cim.AIA.IntArray a0 = a.getData();
        return a0;
    }
    
    public void setNodeAppearance(aia.graph.common.GraphMapsNode a)
    {
        java.awt.Color a0 = this.textColor;
        a.setTextColor(a0);
        java.awt.Color a1 = this.unvisitedNodeColor;
        a.setBackgroundColor(a1);
    }
    
    public boolean isInSecondLoop()
    {
        int i = this.m_bInSecondLoop?1:0;
        return i != 0;
    }
    
    public int getX()
    {
        int i = this.m_x;
        return i;
    }
    
    public int getY()
    {
        int i = this.m_y;
        return i;
    }
    
    public int getJ()
    {
        int i = this.m_j;
        return i;
    }
    
    public java.util.Vector getNodes()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        java.util.Vector a0 = a.getNodes();
        return a0;
    }
    
    private boolean isLinkEqual(aia.graph.common.Link a, int i, int i0)
    {
        int i1 = 0;
        aia.graph.common.NodeInfo a0 = a.getStart();
        int i2 = a0.getKey();
        label1: {
            label0: {
                if(i2 != i)
                {
                    break label0;
                }
                aia.graph.common.NodeInfo a1 = a.getEnd();
                int i3 = a1.getKey();
                if(i3 != i0)
                {
                    break label0;
                }
                i1 = 1;
                break label1;
            }
            int i4 = a.getOmniDirectional()?1:0;
            label3: {
                label2: {
                    if(i4 == 0)
                    {
                        break label2;
                    }
                    aia.graph.common.NodeInfo a2 = a.getEnd();
                    int i5 = a2.getKey();
                    if(i5 != i)
                    {
                        break label2;
                    }
                    aia.graph.common.NodeInfo a3 = a.getStart();
                    int i6 = a3.getKey();
                    if(i6 == i0)
                    {
                        break label3;
                    }
                }
                i1 = 0;
                break label1;
            }
            i1 = 1;
        }
        return i1 != 0;
    }
    
    public void setLinkHighlighted(aia.graph.common.Link a)
    {
        int i = this.m_x;
        int i0 = this.m_y;
        int i1 = this.isLinkEqual(a, i, i0)?1:0;
        if(i1 != 0)
        {
            a.setHighlighted(true);
            java.awt.Color a0 = this.xyColor;
            a.setLinkColor(a0);
        }
        int i2 = this.m_y;
        int i3 = this.m_j;
        int i4 = this.isLinkEqual(a, i2, i3)?1:0;
        label0: {
            if(i4 == 0)
            {
                break label0;
            }
            a.setHighlighted(true);
            java.awt.Color a1 = this.yjColor;
            a.setLinkColor(a1);
            int i5 = this.m_x;
            int i6 = this.m_y;
            int i7 = this.isLinkEqual(a, i5, i6)?1:0;
            if(i7 != 0)
            {
                java.awt.Color a2 = this.xyColor;
                a.setLinkColor(a2);
            }
        }
    }
    
    public void setLinkAppearance(aia.graph.common.Link a)
    {
        a.setUseWeight(false);
        aia.graph.common.Link a0 = this.link_just_added;
        label0: {
            if(a0 == null)
            {
                break label0;
            }
            aia.graph.common.Link a1 = this.link_just_added;
            int i = a.isEqualTo(a1)?1:0;
            if(i != 0)
            {
                java.awt.Color a2 = this.backgroundColor;
                a.setLinkColor(a2);
            }
        }
    }
    
    public java.util.Vector getLinks()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        java.util.Vector a0 = a.getLinks();
        return a0;
    }
    
    public java.awt.Color getUnvisitedNodeColor()
    {
        java.awt.Color a = this.unvisitedNodeColor;
        return a;
    }
    
    public java.awt.Color getHighlightColor()
    {
        java.awt.Color a = this.highlightColor;
        return a;
    }
    
    public java.awt.Color getHighlightLinkColor()
    {
        java.awt.Color a = this.highlightLinkColor;
        return a;
    }
    
    public java.awt.Color getTextColor()
    {
        java.awt.Color a = this.textColor;
        return a;
    }
    
    public java.awt.Color getXColor()
    {
        java.awt.Color a = this.xColor;
        return a;
    }
    
    public java.awt.Color getYColor()
    {
        java.awt.Color a = this.yColor;
        return a;
    }
    
    public java.awt.Color getJColor()
    {
        java.awt.Color a = this.jColor;
        return a;
    }
    
    public aia.graph.common.GraphNode getAdjacentNode(int i)
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        aia.graph.common.GraphNode a0 = a.getAdjacentNode(i);
        return a0;
    }
    
    public int getNumberOfNodes()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = a.getNumberOfNodes();
        return i;
    }
    
    public boolean getIsRunning()
    {
        int i = this.m_bIsRunning?1:0;
        return i != 0;
    }
    
    public java.util.Vector getAdjacencyMatrix()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        java.util.Vector a0 = a.getAdjacencyMatrix();
        return a0;
    }
    
    public void setMatrixNodeAppearance(aia.graph.common.GraphMapsNode a)
    {
        java.awt.Color a0 = null;
        java.awt.Color a1 = null;
        java.awt.Color a2 = null;
        java.awt.Color a3 = this.unvisitedNodeColor;
        java.awt.Color a4 = this.textColor;
        a.setTextColor(a4);
        int i = a.getFrom();
        int i0 = this.m_x;
        label0: {
            if(i != i0)
            {
                a0 = a3;
                break label0;
            }
            int i1 = a.getTo();
            int i2 = this.m_y;
            if(i1 != i2)
            {
                a0 = a3;
            }
            else
            {
                java.awt.Color a5 = this.xyColor;
                a0 = a5;
            }
        }
        int i3 = a.getFrom();
        int i4 = this.m_y;
        label1: {
            if(i3 != i4)
            {
                a1 = a0;
                break label1;
            }
            int i5 = a.getTo();
            int i6 = this.m_j;
            if(i5 != i6)
            {
                a1 = a0;
            }
            else
            {
                java.awt.Color a6 = this.yjColor;
                a1 = a6;
            }
        }
        int i7 = a.getFrom();
        int i8 = this.m_x;
        label2: {
            if(i7 != i8)
            {
                a2 = a1;
                break label2;
            }
            int i9 = a.getTo();
            int i10 = this.m_j;
            if(i9 != i10)
            {
                a2 = a1;
            }
            else
            {
                java.awt.Color a7 = this.xjColor;
                a2 = a7;
            }
        }
        a.setBackgroundColor(a2);
        int i11 = a.getFrom();
        int i12 = this.m_x;
        label3: {
            if(i11 != i12)
            {
                break label3;
            }
            int i13 = a.getTo();
            int i14 = this.m_y;
            if(i13 != i14)
            {
                break label3;
            }
            java.awt.Color a8 = java.awt.Color.white;
            a.setTextColor(a8);
            int i15 = a.getFrom();
            int i16 = this.m_x;
            label5: {
                label4: {
                    if(i15 != i16)
                    {
                        break label4;
                    }
                    int i17 = a.getTo();
                    int i18 = this.m_y;
                    if(i17 == i18)
                    {
                        break label5;
                    }
                }
                int i19 = a.getFrom();
                int i20 = this.m_y;
                if(i19 != i20)
                {
                    break label3;
                }
                int i21 = a.getTo();
                int i22 = this.m_j;
                if(i21 != i22)
                {
                    break label3;
                }
            }
            java.awt.Color a9 = this.textColor;
            a.setTextColor(a9);
        }
    }
    
    public com.cim.AIA.ElementArray getAdjacencyStructure()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        com.cim.AIA.ElementArray a0 = a.getAdjacencyStructure();
        return a0;
    }
    
    public void setStructureNodeAppearance(aia.graph.common.GraphMapsNode a)
    {
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
        aia.graph.common.GraphAlgorithmCommon a1 = this.commons;
        int[][] a2 = a1.getAdjacencyMatrixArray();
        aia.graph.common.LinkContainer a3 = new aia.graph.common.LinkContainer();
        this.added_links = a3;
        this.m_bIsRunning = true;
        this.setPosition("1");
        this.m_y = 0;
        label0: while(true)
        {
            int i0 = this.m_y;
            aia.graph.common.GraphAlgorithmCommon a4 = this.commons;
            int i1 = a4.getNumberOfNodes();
            if(i0 >= i1)
            {
                this.setPosition("2");
                this.setPosition("10");
                this.m_bIsRunning = false;
                return;
            }
            this.setPosition("2");
            this.m_x = 0;
            while(true)
            {
                int i2 = this.m_x;
                aia.graph.common.GraphAlgorithmCommon a5 = this.commons;
                int i3 = a5.getNumberOfNodes();
                if(i2 >= i3)
                {
                    this.setPosition("3");
                    int i4 = this.m_y;
                    int i5 = i4 + 1;
                    this.m_y = i5;
                    continue label0;
                }
                this.setPosition("3");
                this.setPosition("5");
                int i6 = this.m_x;
                int[] a6 = a2[i6];
                int i7 = this.m_y;
                int i8 = a6[i7];
                label1: {
                    if(i8 == 0)
                    {
                        break label1;
                    }
                    this.m_j = 0;
                    while(true)
                    {
                        int i9 = this.m_j;
                        aia.graph.common.GraphAlgorithmCommon a7 = this.commons;
                        int i10 = a7.getNumberOfNodes();
                        if(i9 >= i10)
                        {
                            break;
                        }
                        this.m_bInSecondLoop = true;
                        this.link_just_added = null;
                        this.setPosition("6");
                        this.setPosition("8");
                        int i11 = this.m_y;
                        int[] a8 = a2[i11];
                        int i12 = this.m_j;
                        int i13 = a8[i12];
                        label2: {
                            if(i13 == 0)
                            {
                                break label2;
                            }
                            this.setPosition("9.1");
                            int i14 = this.m_x;
                            int i15 = this.m_j;
                            if(i14 == i15)
                            {
                                break label2;
                            }
                            int i16 = this.m_x;
                            int[] a9 = a2[i16];
                            int i17 = this.m_j;
                            int i18 = a9[i17];
                            if(i18 != 1)
                            {
                                aia.graph.common.LinkContainer a10 = this.added_links;
                                int i19 = this.m_x;
                                aia.graph.common.NodeInfo a11 = new aia.graph.common.NodeInfo(i19, 0, 0);
                                int i20 = this.m_j;
                                aia.graph.common.NodeInfo a12 = new aia.graph.common.NodeInfo(i20, 0, 0);
                                aia.graph.common.GraphAlgorithmCommon a13 = this.commons;
                                int i21 = a13.getOmniDirectional()?1:0;
                                aia.graph.common.Link a14 = a10.addLink(a11, a12, i21 != 0, 0);
                                int i22 = this.m_x;
                                aia.graph.common.NodeInfo a15 = new aia.graph.common.NodeInfo(i22, 0, 0);
                                int i23 = this.m_j;
                                aia.graph.common.NodeInfo a16 = new aia.graph.common.NodeInfo(i23, 0, 0);
                                aia.graph.common.GraphAlgorithmCommon a17 = this.commons;
                                int i24 = a17.getOmniDirectional()?1:0;
                                aia.graph.common.Link a18 = new aia.graph.common.Link(a15, a16, i24 != 0);
                                this.link_just_added = a18;
                                int i25 = this.m_x;
                                int i26 = this.m_j;
                                this.makeQuestion(i25, i26);
                                this.askQuestionsWithoutSetPosition();
                                int i27 = this.m_x;
                                int[] a19 = a2[i27];
                                int i28 = this.m_j;
                                a19[i28] = 1;
                            }
                            aia.graph.common.GraphAlgorithmCommon a20 = this.commons;
                            int i29 = a20.getOmniDirectional()?1:0;
                            label3: {
                                if(i29 == 0)
                                {
                                    break label3;
                                }
                                int i30 = this.m_j;
                                int[] a21 = a2[i30];
                                int i31 = this.m_x;
                                int i32 = a21[i31];
                                if(i32 != 1)
                                {
                                    int i33 = this.m_j;
                                    int[] a22 = a2[i33];
                                    int i34 = this.m_x;
                                    a22[i34] = 1;
                                }
                            }
                            aia.graph.common.GraphAlgorithmCommon a23 = this.commons;
                            a23.setAdjacencyMatrixArray(a2);
                            aia.graph.common.GraphAlgorithmCommon a24 = this.commons;
                            a24.generateAdjacencyStructure();
                            this.setPosition("9.2");
                        }
                        int i35 = this.m_j;
                        int i36 = i35 + 1;
                        this.m_j = i36;
                    }
                    this.link_just_added = null;
                    this.m_bInSecondLoop = false;
                    this.setPosition("6");
                }
                int i37 = this.m_x;
                int i38 = i37 + 1;
                this.m_x = i38;
            }
        }
    }
    
    public void processColorSelection(com.cim.AIA.ColorSelection a)
    {
        String s = a.getAtributeName();
        String s0 = com.cim.AIA.ColorSelection.BACKGROUND;
        int i = s.equalsIgnoreCase(s0)?1:0;
        label1: {
            label0: {
                if(i == 0)
                {
                    break label0;
                }
                java.awt.Color a0 = a.getColor();
                this.backgroundColor = a0;
                break label1;
            }
            String s1 = com.cim.AIA.ColorSelection.TEXT_COLOR;
            int i0 = s.equalsIgnoreCase(s1)?1:0;
            label2: {
                if(i0 == 0)
                {
                    break label2;
                }
                java.awt.Color a1 = a.getColor();
                this.textColor = a1;
                break label1;
            }
            String s2 = TransclosureGraphMaps.HIGHLIGHT_COLOR;
            int i1 = s.equalsIgnoreCase(s2)?1:0;
            label3: {
                if(i1 == 0)
                {
                    break label3;
                }
                java.awt.Color a2 = a.getColor();
                this.highlightColor = a2;
                break label1;
            }
            String s3 = TransclosureGraphMaps.NODE_COLOR;
            int i2 = s.equalsIgnoreCase(s3)?1:0;
            if(i2 != 0)
            {
                java.awt.Color a3 = a.getColor();
                this.unvisitedNodeColor = a3;
            }
        }
    }
    
    public com.cim.AIA.MultipleTween generateTweens(com.cim.AIA.Paintable a, com.cim.AIA.Line a0, java.util.Vector a1, int i)
    {
        com.cim.AIA.MultipleTween a2 = null;
        aia.graph.common.Link a3 = this.link_just_added;
        Object a4 = a;
        label1: {
            java.awt.Point a5 = null;
            java.awt.Point a6 = null;
            label0: {
                if(a3 != null)
                {
                    break label0;
                }
                a2 = null;
                break label1;
            }
            com.cim.AIA.MultipleTween a7 = new com.cim.AIA.MultipleTween((com.cim.AIA.Paintable)a4);
            java.awt.Point a8 = null;
            java.awt.Point a9 = null;
            int i0 = 0;
            while(true)
            {
                java.awt.Point a10 = null;
                java.awt.Point a11 = null;
                java.awt.Point a12 = null;
                java.awt.Point a13 = null;
                java.awt.Point a14 = null;
                java.awt.Point a15 = null;
                int i1 = a1.size();
                a5 = a8;
                a6 = a9;
                java.awt.Point a16 = a5;
                java.awt.Point a17 = a6;
                if(i0 >= i1)
                {
                    break;
                }
                else
                {
                    a10 = a16;
                    a11 = a17;
                }
                Object a18 = a1.elementAt(i0);
                java.awt.Point a19 = a10;
                java.awt.Point a20 = a11;
                aia.graph.common.GraphMapsNode dummy = (aia.graph.common.GraphMapsNode)a18;
                aia.graph.common.GraphMapsNode a21 = (aia.graph.common.GraphMapsNode)a18;
                java.awt.Point a22 = a19;
                java.awt.Point a23 = a20;
                int i2 = a21.getIntValue();
                java.awt.Point a24 = a22;
                java.awt.Point a25 = a23;
                aia.graph.common.Link a26 = this.link_just_added;
                java.awt.Point a27 = a24;
                java.awt.Point a28 = a25;
                aia.graph.common.NodeInfo a29 = a26.getStart();
                java.awt.Point a30 = a27;
                java.awt.Point a31 = a28;
                int i3 = a29.getKey();
                java.awt.Point a32 = a30;
                java.awt.Point a33 = a31;
                java.awt.Point a34 = a33;
                java.awt.Point a35 = a32;
                java.awt.Point a36 = a33;
                if(i2 != i3)
                {
                    a12 = a35;
                    a13 = a36;
                }
                else
                {
                    java.awt.Point a37 = a34;
                    java.awt.Point a38 = a37;
                    int i4 = a21.getX();
                    java.awt.Point a39 = a38;
                    int i5 = i4 + 10;
                    int i6 = a21.getY();
                    java.awt.Point a40 = a39;
                    int i7 = i6 + 10;
                    java.awt.Point a41 = new java.awt.Point(i5, i7);
                    java.awt.Point a42 = a40;
                    a12 = a41;
                    a13 = a42;
                }
                int i8 = a21.getIntValue();
                java.awt.Point a43 = a12;
                java.awt.Point a44 = a13;
                aia.graph.common.Link a45 = this.link_just_added;
                java.awt.Point a46 = a43;
                java.awt.Point a47 = a44;
                aia.graph.common.NodeInfo a48 = a45.getEnd();
                java.awt.Point a49 = a46;
                java.awt.Point a50 = a47;
                int i9 = a48.getKey();
                java.awt.Point a51 = a49;
                java.awt.Point a52 = a50;
                java.awt.Point a53 = a51;
                java.awt.Point a54 = a51;
                java.awt.Point a55 = a52;
                if(i8 != i9)
                {
                    a14 = a54;
                    a15 = a55;
                }
                else
                {
                    java.awt.Point a56 = a53;
                    java.awt.Point a57 = a56;
                    int i10 = a21.getX();
                    java.awt.Point a58 = a57;
                    int i11 = i10 + 10;
                    int i12 = a21.getY();
                    java.awt.Point a59 = a58;
                    int i13 = i12 + 10;
                    java.awt.Point a60 = new java.awt.Point(i11, i13);
                    java.awt.Point a61 = a59;
                    a14 = a61;
                    a15 = a60;
                }
                int i14 = i0 + 1;
                a8 = a14;
                a9 = a15;
                i0 = i14;
            }
            label3: {
                label2: {
                    if(a5 == null)
                    {
                        break label2;
                    }
                    if(a6 != null)
                    {
                        break label3;
                    }
                }
                a2 = null;
                break label1;
            }
            a0.setStartPosition(a5);
            a0.setEndPosition(a6);
            java.awt.Color a62 = this.xjColor;
            a0.setColor(a62);
            a0.showAsThick(true);
            aia.graph.common.GraphAlgorithmCommon a63 = this.commons;
            int i15 = a63.getOmniDirectional()?1:0;
            if(i15 == 0)
            {
                a0.showArrowHead(true);
                int i16 = a0.getLength();
                int i17 = i16 - 15;
                a0.setDistanceFromStartForArrowHead(i17);
            }
            com.cim.AIA.MoveTween a64 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)a4, (com.cim.AIA.Moveable)a0, a5, a6, false, i);
            a7.add((com.cim.AIA.Tween)a64);
            a2 = a7;
        }
        return a2;
    }
    
    public com.cim.AIA.MultipleTween generateTweens(com.cim.AIA.Paintable a, Object a0, int i)
    {
        return null;
    }
    
    public String getClassName()
    {
        return "GraphMaps";
    }
    
    protected java.util.Vector generateQuestions()
    {
        java.util.Vector a = this.questions;
        return a;
    }
    
    protected boolean hasQuestions()
    {
        java.util.Vector a = this.questions;
        int i = a.size();
        int i0 = i <= 0?0:1;
        return i0 != 0;
    }
    
    protected void makeQuestion(int i, int i0)
    {
        java.util.Vector a = this.questions;
        aia.graph.common.NextNewLinkQuestion a0 = new aia.graph.common.NextNewLinkQuestion(i, i0);
        a.addElement((Object)a0);
    }
    
    protected void removeAllQuestions()
    {
        java.util.Vector a = new java.util.Vector();
        this.questions = a;
    }
    
    public void removeAllAnimationRequests()
    {
        this.link_just_added = null;
    }
    
    public Object getData()
    {
        com.cim.AIA.IntArray a = this.getData();
        return a;
    }
    
    static
    {
        String s = localization.Messages.getString("TransclosureGraphMaps.0");
        TransclosureGraphMaps.HIGHLIGHT_COLOR = s;
        String s0 = localization.Messages.getString("TransclosureGraphMaps.1");
        TransclosureGraphMaps.NODE_COLOR = s0;
    }
}