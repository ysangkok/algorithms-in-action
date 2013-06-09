public class DFSGraphMaps extends aia.graph.common.GraphMaps implements com.cim.AIA.ColorSelectionListener, com.cim.AIA.MethodSelectionListener {
    protected java.awt.Color textColor;
    protected java.awt.Color highlightColor;
    protected java.awt.Color highlightLinkColor;
    protected java.awt.Color unvisitedNodeColor;
    protected java.awt.Color adjacentNodesColor;
    protected java.awt.Color adjacentNodeColor;
    protected java.awt.Color visitedNodeColor;
    protected java.awt.Color backgroundColor;
    protected java.awt.Color flagUnvisitedColor;
    protected java.awt.Color flagVisitedColor;
    protected java.awt.Color waitingNodeColor;
    final protected static String HIGHLIGHT_COLOR;
    final protected static String NODE_COLOR;
    protected aia.graph.common.GraphAlgorithmCommon commons;
    protected boolean m_bIsRunning;
    private int[] visit_sequence;
    private int visit_counter;
    private aia.graph.common.LinkContainer visited_links;
    private aia.graph.common.GraphMapsNode adj_node;
    private int k_marker;
    private java.util.Vector adjacent_nodes;
    protected java.util.Vector questions;
    final public static int UNMARKED = -10;
    final public static String VISIT_MODE_LABEL = "Visit";
    protected boolean m_backMode;
    protected int[] m_stored_data;
    
    public DFSGraphMaps(com.cim.AIA.AlgorithmThread a, Object a0)
    {
        super(a, a0);
        java.awt.Color a1 = java.awt.Color.black;
        this.textColor = a1;
        java.awt.Color a2 = java.awt.Color.yellow;
        this.highlightColor = a2;
        java.awt.Color a3 = java.awt.Color.red;
        this.highlightLinkColor = a3;
        java.awt.Color a4 = new java.awt.Color(123, 202, 123);
        this.unvisitedNodeColor = a4;
        java.awt.Color a5 = new java.awt.Color(0, 172, 0);
        this.adjacentNodesColor = a5;
        java.awt.Color a6 = new java.awt.Color(174, 255, 174);
        this.adjacentNodeColor = a6;
        java.awt.Color a7 = java.awt.Color.gray;
        this.visitedNodeColor = a7;
        java.awt.Color a8 = java.awt.Color.white;
        this.backgroundColor = a8;
        java.awt.Color a9 = java.awt.Color.lightGray;
        this.flagUnvisitedColor = a9;
        java.awt.Color a10 = java.awt.Color.gray;
        this.flagVisitedColor = a10;
        java.awt.Color a11 = java.awt.Color.blue;
        this.waitingNodeColor = a11;
        this.m_bIsRunning = false;
        this.visit_sequence = null;
        this.visit_counter = -10;
        this.visited_links = null;
        this.adj_node = null;
        this.k_marker = -10;
        this.adjacent_nodes = null;
        java.util.Vector a12 = new java.util.Vector();
        this.questions = a12;
        this.m_backMode = false;
        this.m_stored_data = null;
        this.initialise(a0);
        com.cim.AIA.ConfigurationManager a13 = com.cim.AIA.ConfigurationManager.getConfigurationManager();
        java.awt.Color a14 = this.highlightColor;
        String s = DFSGraphMaps.HIGHLIGHT_COLOR;
        com.cim.AIA.ColorSelection a15 = new com.cim.AIA.ColorSelection(a14, s);
        a15.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        a13.addColorSelection(a15);
        java.awt.Color a16 = this.unvisitedNodeColor;
        String s0 = DFSGraphMaps.NODE_COLOR;
        com.cim.AIA.ColorSelection a17 = new com.cim.AIA.ColorSelection(a16, s0);
        a17.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        a13.addColorSelection(a17);
        a13.addColorSelectionListener((com.cim.AIA.ColorSelectionListener)this);
    }
    
    public void initialiseMethods(String s, com.cim.AIA.MethodSelectable a)
    {
        Object a0 = a;
        String s0 = localization.Messages.getString("DFSGraphMaps.1");
        com.cim.AIA.Logger a1 = this.getLogger();
        com.cim.AIA.BreakPoint a2 = this.getBreakPoint();
        com.cim.AIA.MethodSelection a3 = new com.cim.AIA.MethodSelection("Visit", s, s0, "4", a1, a2);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelection(a3, true);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelectionListener((com.cim.AIA.MethodSelectionListener)this);
    }
    
    public void processMethodSelectionEvent(com.cim.AIA.MethodSelectionEvent a)
    {
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
        this.visit_sequence = null;
        this.visit_counter = -10;
        this.visited_links = null;
        this.adj_node = null;
        this.k_marker = -10;
        this.m_bIsRunning = false;
        java.util.Vector a0 = new java.util.Vector();
        this.questions = a0;
        this.adjacent_nodes = null;
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
        this.setMatrixShowing(false);
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
    
    public boolean getStructureShowing()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = a.getStructureShowing()?1:0;
        return i != 0;
    }
    
    public boolean getMatrixShowing()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = a.getMatrixShowing()?1:0;
        return i != 0;
    }
    
    public int getKMarker()
    {
        int i = this.k_marker;
        return i;
    }
    
    private void createAdjacentNodeVector(aia.graph.common.GraphNode a)
    {
        java.util.Vector a0 = new java.util.Vector();
        this.adjacent_nodes = a0;
        aia.graph.common.GraphNode a1 = a;
        while(true)
        {
            label0: {
                if(a1 == null)
                {
                    break label0;
                }
                int i = a1.getKey();
                if(i != -1)
                {
                    java.util.Vector a2 = this.adjacent_nodes;
                    int i0 = a1.getKey();
                    Integer a3 = new Integer(i0);
                    a2.addElement((Object)a3);
                    aia.graph.common.GraphNode a4 = a1.getNext();
                    a1 = a4;
                    continue;
                }
            }
            return;
        }
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
    
    public java.util.Vector getNodes()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        java.util.Vector a0 = a.getNodes();
        return a0;
    }
    
    public void setNodeAppearance(aia.graph.common.GraphMapsNode a)
    {
        java.awt.Color a0 = this.textColor;
        a.setTextColor(a0);
        java.awt.Color a1 = this.unvisitedNodeColor;
        a.setBackgroundColor(a1);
        java.util.Vector a2 = this.adjacent_nodes;
        label0: {
            if(a2 == null)
            {
                break label0;
            }
            int i = 0;
            while(true)
            {
                java.util.Vector a3 = this.adjacent_nodes;
                int i0 = a3.size();
                if(i >= i0)
                {
                    break;
                }
                java.util.Vector a4 = this.adjacent_nodes;
                Object a5 = a4.elementAt(i);
                Integer dummy = (Integer)a5;
                Integer a6 = (Integer)a5;
                int i1 = a6.intValue();
                int i2 = a.getIntValue();
                if(i1 == i2)
                {
                    java.awt.Color a7 = this.adjacentNodesColor;
                    a.setBackgroundColor(a7);
                }
                int i3 = i + 1;
                i = i3;
            }
        }
        aia.graph.common.GraphMapsNode a8 = this.adj_node;
        label1: {
            if(a8 == null)
            {
                break label1;
            }
            aia.graph.common.GraphMapsNode a9 = this.adj_node;
            int i4 = a9.getTo();
            int i5 = a.getIntValue();
            if(i4 == i5)
            {
                java.awt.Color a10 = this.adjacentNodeColor;
                a.setBackgroundColor(a10);
            }
        }
        int[] a11 = this.visit_sequence;
        label3: {
            label2: {
                if(a11 == null)
                {
                    break label2;
                }
                int[] a12 = this.visit_sequence;
                int i6 = a.getIntValue();
                int i7 = a12[i6];
                if(i7 <= 0)
                {
                    break label2;
                }
                java.awt.Color a13 = this.visitedNodeColor;
                a.setBackgroundColor(a13);
                break label3;
            }
            int[] a14 = this.visit_sequence;
            if(a14 == null)
            {
                break label3;
            }
            int[] a15 = this.visit_sequence;
            int i8 = a.getIntValue();
            int i9 = a15[i8];
            if(i9 < 0)
            {
                java.awt.Color a16 = this.waitingNodeColor;
                a.setBackgroundColor(a16);
            }
        }
        int i10 = a.getIntValue();
        int i11 = this.k_marker;
        if(i10 == i11)
        {
            java.awt.Color a17 = this.highlightColor;
            a.setBackgroundColor(a17);
        }
    }
    
    public java.util.Vector getLinks()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        java.util.Vector a0 = a.getLinks();
        return a0;
    }
    
    public void setLinkHighlighted(aia.graph.common.Link a)
    {
        aia.graph.common.LinkContainer a0 = this.visited_links;
        label0: {
            if(a0 == null)
            {
                break label0;
            }
            aia.graph.common.LinkContainer a1 = this.visited_links;
            aia.graph.common.NodeInfo a2 = a.getStart();
            aia.graph.common.NodeInfo a3 = a.getEnd();
            aia.graph.common.Link a4 = a1.findLink(a2, a3);
            if(a4 != null)
            {
                a.setHighlighted(true);
                java.awt.Color a5 = this.highlightLinkColor;
                a.setLinkColor(a5);
            }
        }
    }
    
    public void setLinkAppearance(aia.graph.common.Link a)
    {
        a.setUseWeight(false);
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
    
    public java.awt.Color getAdjacentNodesColor()
    {
        java.awt.Color a = this.adjacentNodesColor;
        return a;
    }
    
    public java.awt.Color getAdjacentNodeColor()
    {
        java.awt.Color a = this.adjacentNodeColor;
        return a;
    }
    
    public java.awt.Color getUnvisitedNodeColor()
    {
        java.awt.Color a = this.unvisitedNodeColor;
        return a;
    }
    
    public aia.graph.common.GraphNode getAdjacentNode(int i)
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        aia.graph.common.GraphNode a0 = a.getAdjacentNode(i);
        return a0;
    }
    
    public String getVisitStatus(int i)
    {
        String s = null;
        int[] a = this.visit_sequence;
        label2: {
            label1: {
                label0: {
                    if(a == null)
                    {
                        break label0;
                    }
                    if(i < 0)
                    {
                        break label0;
                    }
                    int[] a0 = this.visit_sequence;
                    int i0 = a0.length;
                    if(i < i0)
                    {
                        break label1;
                    }
                }
                s = "";
                break label2;
            }
            int[] a1 = this.visit_sequence;
            int i1 = a1[i];
            label3: {
                if(i1 <= 0)
                {
                    break label3;
                }
                s = "T";
                break label2;
            }
            int[] a2 = this.visit_sequence;
            int i2 = a2[i];
            s = i2 >= 0?"F":"P";
        }
        return s;
    }
    
    public int getSeqNumber()
    {
        int i = this.visit_counter;
        return i;
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
        java.awt.Color a0 = this.unvisitedNodeColor;
        a.setBackgroundColor(a0);
        java.awt.Color a1 = this.textColor;
        a.setTextColor(a1);
        aia.graph.common.GraphMapsNode a2 = this.adj_node;
        label0: {
            if(a2 == null)
            {
                break label0;
            }
            int i = a.getFrom();
            aia.graph.common.GraphMapsNode a3 = this.adj_node;
            int i0 = a3.getFrom();
            label1: {
                if(i != i0)
                {
                    break label1;
                }
                int i1 = a.getTo();
                aia.graph.common.GraphMapsNode a4 = this.adj_node;
                int i2 = a4.getTo();
                if(i1 != i2)
                {
                    break label1;
                }
                java.awt.Color a5 = this.adjacentNodeColor;
                a.setBackgroundColor(a5);
                break label0;
            }
            int i3 = a.getFrom();
            aia.graph.common.GraphMapsNode a6 = this.adj_node;
            int i4 = a6.getFrom();
            if(i3 == i4)
            {
                java.awt.Color a7 = this.adjacentNodesColor;
                a.setBackgroundColor(a7);
            }
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
        java.awt.Color a0 = this.unvisitedNodeColor;
        a.setBackgroundColor(a0);
        java.awt.Color a1 = this.textColor;
        a.setTextColor(a1);
        aia.graph.common.GraphMapsNode a2 = this.adj_node;
        label0: {
            if(a2 == null)
            {
                break label0;
            }
            int i = a.getIntValue();
            aia.graph.common.GraphMapsNode a3 = this.adj_node;
            int i0 = a3.getFrom();
            if(i == i0)
            {
                java.awt.Color a4 = this.highlightColor;
                a.setBackgroundColor(a4);
            }
        }
    }
    
    public com.cim.AIA.ElementArray getSeqArray()
    {
        com.cim.AIA.ElementArray a = null;
        int[] a0 = this.visit_sequence;
        label1: {
            label0: {
                if(a0 != null)
                {
                    break label0;
                }
                a = null;
                break label1;
            }
            com.cim.AIA.ElementArray a1 = new com.cim.AIA.ElementArray(0, 0);
            a1.setColumGap(0);
            a1.setElementWidth(20);
            int i = 0;
            while(true)
            {
                aia.graph.common.GraphMapsNode a2 = null;
                int[] a3 = this.visit_sequence;
                int i0 = a3.length;
                if(i >= i0)
                {
                    break;
                }
                int[] a4 = this.visit_sequence;
                int i1 = a4[i];
                Integer a5 = new Integer(i1);
                aia.graph.common.GraphMapsNode a6 = new aia.graph.common.GraphMapsNode((Object)a5, i, 4);
                java.awt.Color a7 = this.flagUnvisitedColor;
                a6.setBackgroundColor(a7);
                aia.graph.common.GraphMapsNode a8 = this.adj_node;
                label2: {
                    if(a8 == null)
                    {
                        break label2;
                    }
                    aia.graph.common.GraphMapsNode a9 = this.adj_node;
                    int i2 = a9.getTo();
                    if(i2 == i)
                    {
                        java.awt.Color a10 = this.adjacentNodeColor;
                        a6.setBackgroundColor(a10);
                    }
                }
                int[] a11 = this.visit_sequence;
                int i3 = a11[i];
                label4: {
                    label3: {
                        if(i3 <= 0)
                        {
                            break label3;
                        }
                        java.awt.Color a12 = this.flagVisitedColor;
                        a6.setBackgroundColor(a12);
                        a2 = a6;
                        break label4;
                    }
                    int[] a13 = this.visit_sequence;
                    int i4 = a13[i];
                    if(i4 >= 0)
                    {
                        a2 = a6;
                    }
                    else
                    {
                        Integer a14 = new Integer(0);
                        aia.graph.common.GraphMapsNode a15 = new aia.graph.common.GraphMapsNode((Object)a14, i, 4);
                        java.awt.Color a16 = this.waitingNodeColor;
                        a15.setBackgroundColor(a16);
                        a2 = a15;
                    }
                }
                int i5 = this.k_marker;
                if(i == i5)
                {
                    java.awt.Color a17 = this.highlightColor;
                    a2.setBackgroundColor(a17);
                }
                java.awt.Color a18 = this.textColor;
                a2.setTextColor(a18);
                a2.setHeight(20);
                a1.setValue(i, (com.cim.AIA.Element)a2);
                int i6 = i + 1;
                i = i6;
            }
            a = a1;
        }
        return a;
    }
    
    public aia.graph.common.GraphMapsNode getAdjNode()
    {
        aia.graph.common.GraphMapsNode a = this.adj_node;
        return a;
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
        }
        this.visit_sequence = null;
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
        this.setPosition("1.4");
        int i7 = 0;
        while(true)
        {
            aia.graph.common.GraphAlgorithmCommon a8 = this.commons;
            int i8 = a8.getNumberOfNodes();
            if(i7 >= i8)
            {
                this.setPosition("2.1");
                this.setPosition("9");
                this.m_bIsRunning = false;
                return;
            }
            this.k_marker = i7;
            this.setPosition("2.1");
            this.setPosition("3.1");
            int[] a9 = this.visit_sequence;
            int i9 = a9[i7];
            if(i9 == 0)
            {
                this.setPosition("4");
                this.visit(i7);
            }
            int i10 = i7 + 1;
            i7 = i10;
        }
    }
    
    protected void visit(int i)
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        aia.graph.common.GraphNode[] a0 = a.getAdjacencyStructureArray();
        this.k_marker = i;
        int[] a1 = this.visit_sequence;
        int i0 = this.visit_counter;
        int i1 = i0 + 1;
        this.visit_counter = i1;
        a1[i] = i1;
        this.setPosition("5.1");
        aia.graph.common.GraphNode a2 = a0[i];
        Integer a3 = new Integer(0);
        aia.graph.common.GraphMapsNode a4 = new aia.graph.common.GraphMapsNode((Object)a3, 0, 0);
        this.adj_node = a4;
        aia.graph.common.GraphMapsNode a5 = this.adj_node;
        a5.setFrom(i);
        aia.graph.common.GraphMapsNode a6 = this.adj_node;
        int i2 = a2.getKey();
        a6.setTo(i2);
        this.createAdjacentNodeVector(a2);
        this.setPosition("6.1");
        aia.graph.common.GraphNode a7 = a2;
        while(true)
        {
            int i3 = a7.getKey();
            if(i3 == -1)
            {
                this.setPosition("6.2");
                this.setPosition("8");
                return;
            }
            this.setPosition("6.2");
            this.setPosition("7.1.1");
            int[] a8 = this.visit_sequence;
            int i4 = a7.getKey();
            int i5 = a8[i4];
            if(i5 == 0)
            {
                int i6 = a7.getKey();
                this.makeQuestion(i6);
                this.askQuestionsWithoutSetPosition();
                this.setPosition("7.2");
                aia.graph.common.LinkContainer a9 = this.visited_links;
                aia.graph.common.NodeInfo a10 = new aia.graph.common.NodeInfo(i, 0, 0);
                int i7 = a7.getKey();
                aia.graph.common.NodeInfo a11 = new aia.graph.common.NodeInfo(i7, 0, 0);
                aia.graph.common.GraphAlgorithmCommon a12 = this.commons;
                int i8 = a12.getOmniDirectional()?1:0;
                aia.graph.common.Link a13 = a9.addLink(a10, a11, i8 != 0, 0);
                int i9 = a7.getKey();
                this.visit(i9);
                this.k_marker = i;
                Integer a14 = new Integer(0);
                aia.graph.common.GraphMapsNode a15 = new aia.graph.common.GraphMapsNode((Object)a14, 0, 0);
                this.adj_node = a15;
                aia.graph.common.GraphMapsNode a16 = this.adj_node;
                a16.setFrom(i);
                aia.graph.common.GraphMapsNode a17 = this.adj_node;
                int i10 = a7.getKey();
                a17.setTo(i10);
                this.createAdjacentNodeVector(a7);
            }
            aia.graph.common.GraphNode a18 = a7.getNext();
            Integer a19 = new Integer(0);
            aia.graph.common.GraphMapsNode a20 = new aia.graph.common.GraphMapsNode((Object)a19, 0, 0);
            this.adj_node = a20;
            aia.graph.common.GraphMapsNode a21 = this.adj_node;
            a21.setFrom(i);
            aia.graph.common.GraphMapsNode a22 = this.adj_node;
            int i11 = a18.getKey();
            a22.setTo(i11);
            this.setPosition("7.3");
            a7 = a18;
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
            String s2 = DFSGraphMaps.HIGHLIGHT_COLOR;
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
            String s3 = DFSGraphMaps.NODE_COLOR;
            int i2 = s.equalsIgnoreCase(s3)?1:0;
            if(i2 != 0)
            {
                java.awt.Color a3 = a.getColor();
                this.unvisitedNodeColor = a3;
            }
        }
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
        java.util.Vector a = new java.util.Vector();
        int i = 0;
        while(true)
        {
            java.util.Vector a0 = this.questions;
            int i0 = a0.size();
            if(i >= i0)
            {
                return a;
            }
            else
            {
                java.util.Vector a1 = this.questions;
                Object a2 = a1.elementAt(i);
                Integer dummy = (Integer)a2;
                Integer a3 = (Integer)a2;
                int i1 = a3.intValue();
                aia.graph.common.NextVisitQuestion a4 = new aia.graph.common.NextVisitQuestion(i1);
                a.addElement((Object)a4);
                int i2 = i + 1;
                i = i2;
            }
        }
    }
    
    protected boolean hasQuestions()
    {
        java.util.Vector a = this.questions;
        int i = a.size();
        int i0 = i <= 0?0:1;
        return i0 != 0;
    }
    
    protected void makeQuestion(int i)
    {
        java.util.Vector a = this.questions;
        Integer a0 = new Integer(i);
        a.addElement((Object)a0);
    }
    
    protected void removeAllQuestions()
    {
        java.util.Vector a = new java.util.Vector();
        this.questions = a;
    }
    
    public void removeAllAnimationRequests()
    {
    }
    
    public Object getData()
    {
        com.cim.AIA.IntArray a = this.getData();
        return a;
    }
    
    static
    {
        String s = localization.Messages.getString("DFSGraphMaps.0");
        DFSGraphMaps.HIGHLIGHT_COLOR = s;
        String s0 = localization.Messages.getString("DFSGraphMaps.2");
        DFSGraphMaps.NODE_COLOR = s0;
    }
}