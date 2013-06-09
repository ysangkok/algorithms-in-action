public class MSTKGraphMaps extends aia.graph.common.GraphMaps implements com.cim.AIA.ColorSelectionListener, com.cim.AIA.MethodSelectionListener {
    protected java.awt.Color textColor;
    protected java.awt.Color highlightColor;
    protected java.awt.Color highlightLinkColor;
    protected java.awt.Color unvisitedNodeColor;
    protected java.awt.Color backgroundColor;
    protected java.awt.Color flagUnvisitedColor;
    protected java.awt.Color flagVisitedColor;
    protected java.awt.Color queueNodeColorLeft;
    protected java.awt.Color queueNodeColorRight;
    protected java.awt.Color waitingNodeColor;
    protected java.awt.Color connectedNodesColor;
    protected java.awt.Color activeVerticesColor;
    final protected static String HIGHLIGHT_COLOR;
    final protected static String NODE_COLOR;
    protected aia.graph.common.GraphAlgorithmCommon commons;
    protected boolean m_bIsRunning;
    private int edges_added;
    private aia.graph.common.LinkContainer visited_links;
    private int v1_marker;
    private int v2_marker;
    private MSTKPriorityQueue pqueue;
    private MSTKPriorityQueueMember current_edge;
    private java.util.Vector connected_nodes;
    protected java.util.Vector questions;
    protected java.util.Vector queue_requests;
    final public static int UNMARKED = -10;
    final public static String BUILD_MODE_LABEL;
    protected int VISIBLE_QUEUE_NODES;
    protected boolean m_backMode;
    protected int[] m_stored_data;
    
    public MSTKGraphMaps(com.cim.AIA.AlgorithmThread a, Object a0)
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
        java.awt.Color a5 = java.awt.Color.white;
        this.backgroundColor = a5;
        java.awt.Color a6 = java.awt.Color.lightGray;
        this.flagUnvisitedColor = a6;
        java.awt.Color a7 = java.awt.Color.gray;
        this.flagVisitedColor = a7;
        java.awt.Color a8 = new java.awt.Color(109, 234, 234);
        this.queueNodeColorLeft = a8;
        java.awt.Color a9 = new java.awt.Color(109, 234, 234);
        this.queueNodeColorRight = a9;
        java.awt.Color a10 = new java.awt.Color(109, 234, 234);
        this.waitingNodeColor = a10;
        java.awt.Color a11 = new java.awt.Color(255, 221, 51);
        this.connectedNodesColor = a11;
        java.awt.Color a12 = new java.awt.Color(255, 123, 123);
        this.activeVerticesColor = a12;
        this.commons = null;
        this.m_bIsRunning = false;
        this.edges_added = -10;
        this.visited_links = null;
        this.v1_marker = -10;
        this.v2_marker = -10;
        this.pqueue = null;
        this.current_edge = null;
        this.connected_nodes = null;
        java.util.Vector a13 = new java.util.Vector();
        this.questions = a13;
        java.util.Vector a14 = new java.util.Vector();
        this.queue_requests = a14;
        this.VISIBLE_QUEUE_NODES = 5;
        this.m_backMode = false;
        this.m_stored_data = null;
        this.initialise(a0);
        com.cim.AIA.ConfigurationManager a15 = com.cim.AIA.ConfigurationManager.getConfigurationManager();
        java.awt.Color a16 = this.highlightColor;
        String s = MSTKGraphMaps.HIGHLIGHT_COLOR;
        com.cim.AIA.ColorSelection a17 = new com.cim.AIA.ColorSelection(a16, s);
        a17.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        a15.addColorSelection(a17);
        java.awt.Color a18 = this.unvisitedNodeColor;
        String s0 = MSTKGraphMaps.NODE_COLOR;
        com.cim.AIA.ColorSelection a19 = new com.cim.AIA.ColorSelection(a18, s0);
        a19.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        a15.addColorSelection(a19);
        a15.addColorSelectionListener((com.cim.AIA.ColorSelectionListener)this);
    }
    
    public void initialiseMethods(String s, com.cim.AIA.MethodSelectable a)
    {
        Object a0 = a;
        String s0 = MSTKGraphMaps.BUILD_MODE_LABEL;
        String s1 = localization.Messages.getString("MSTKGraphMaps.0");
        com.cim.AIA.Logger a1 = this.getLogger();
        com.cim.AIA.BreakPoint a2 = this.getBreakPoint();
        com.cim.AIA.MethodSelection a3 = new com.cim.AIA.MethodSelection(s0, s, s1, "4", a1, a2);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelection(a3, true);
        ((com.cim.AIA.MethodSelectable)a0).addMethodSelectionListener((com.cim.AIA.MethodSelectionListener)this);
    }
    
    public void processMethodSelectionEvent(com.cim.AIA.MethodSelectionEvent a)
    {
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
    
    public void reInitialise(Object a)
    {
        this.initialise(a);
    }
    
    protected void initialise(Object a)
    {
        this.visited_links = null;
        this.pqueue = null;
        java.util.Vector a0 = new java.util.Vector();
        this.questions = a0;
        java.util.Vector a1 = new java.util.Vector();
        this.queue_requests = a1;
        this.current_edge = null;
        this.connected_nodes = null;
        this.edges_added = -10;
        this.v1_marker = -10;
        this.v2_marker = -10;
        this.m_bIsRunning = false;
        aia.graph.common.GraphAlgorithmCommon a2 = this.commons;
        if(a2 != null)
        {
            aia.graph.common.GraphAlgorithmCommon a3 = this.commons;
            a3.setData(a);
        }
        else
        {
            MSTKGraphAlgorithmCommon a4 = new MSTKGraphAlgorithmCommon(this, a);
            this.commons = a4;
        }
        this.setStructureShowing(false);
        this.setMatrixShowing(false);
    }
    
    public void setNodePosition(int i, int i0, int i1)
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        a.setNodePosition(i, i0, i1);
    }
    
    public int getV1Marker()
    {
        int i = this.v1_marker;
        return i;
    }
    
    public int getV2Marker()
    {
        int i = this.v2_marker;
        return i;
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
    
    public Object getData()
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
        java.awt.Color a0 = this.unvisitedNodeColor;
        a.setBackgroundColor(a0);
        java.awt.Color a1 = this.textColor;
        a.setTextColor(a1);
        java.util.Vector a2 = this.connected_nodes;
        label0: {
            if(a2 == null)
            {
                break label0;
            }
            int i = 0;
            while(true)
            {
                java.util.Vector a3 = this.connected_nodes;
                int i0 = a3.size();
                if(i >= i0)
                {
                    break;
                }
                java.util.Vector a4 = this.connected_nodes;
                Object a5 = a4.elementAt(i);
                Integer dummy = (Integer)a5;
                Integer a6 = (Integer)a5;
                int i1 = a6.intValue();
                int i2 = a.getIntValue();
                if(i1 != i2)
                {
                    int i3 = i + 1;
                    i = i3;
                    continue;
                }
                java.awt.Color a7 = this.connectedNodesColor;
                a.setBackgroundColor(a7);
                break;
            }
        }
        int i4 = a.getIntValue();
        int i5 = this.v1_marker;
        label2: {
            label1: {
                if(i4 == i5)
                {
                    break label1;
                }
                int i6 = a.getIntValue();
                int i7 = this.v2_marker;
                if(i6 != i7)
                {
                    break label2;
                }
            }
            java.awt.Color a8 = this.activeVerticesColor;
            a.setBackgroundColor(a8);
        }
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
        int i = this.m_bIsRunning?1:0;
        if(i != 0)
        {
            java.awt.Color a0 = java.awt.Color.lightGray;
            a.setLinkColor(a0);
        }
        int i0 = this.v1_marker;
        aia.graph.common.NodeInfo a1 = new aia.graph.common.NodeInfo(i0, 0, 0);
        int i1 = this.v2_marker;
        aia.graph.common.NodeInfo a2 = new aia.graph.common.NodeInfo(i1, 0, 0);
        aia.graph.common.GraphAlgorithmCommon a3 = this.commons;
        int i2 = a3.getOmniDirectional()?1:0;
        aia.graph.common.Link a4 = new aia.graph.common.Link(a1, a2, i2 != 0);
        int i3 = a.isEqualTo(a4)?1:0;
        if(i3 != 0)
        {
            java.awt.Color a5 = new java.awt.Color(255, 192, 192);
            a.setLinkColor(a5);
        }
    }
    
    public aia.graph.common.TwinNode getCurrentEdgeNode()
    {
        aia.graph.common.TwinNode a = null;
        MSTKPriorityQueueMember a0 = this.current_edge;
        if(a0 != null)
        {
            MSTKPriorityQueueMember a1 = this.current_edge;
            int i = a1.getFromKey();
            Integer a2 = new Integer(i);
            MSTKPriorityQueueMember a3 = this.current_edge;
            int i0 = a3.getToKey();
            Integer a4 = new Integer(i0);
            aia.graph.common.TwinNode a5 = new aia.graph.common.TwinNode((Object)a2, (Object)a4, 0);
            java.awt.Color a6 = this.queueNodeColorLeft;
            a5.setBackgroundColorLeft(a6);
            java.awt.Color a7 = this.queueNodeColorRight;
            a5.setBackgroundColorRight(a7);
            java.awt.Color a8 = this.textColor;
            a5.setTextColor(a8);
            a5.setHeight(20);
            MSTKPriorityQueueMember a9 = this.current_edge;
            int i1 = a9.getPriority();
            a5.setPriority(i1);
            a = a5;
        }
        else
        {
            a = null;
        }
        return a;
    }
    
    public java.util.Vector getLinks()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        java.util.Vector a0 = a.getLinks();
        return a0;
    }
    
    public int getEdgesAdded()
    {
        int i = this.edges_added;
        return i;
    }
    
    public java.awt.Color getQueueNodeColorLeft()
    {
        java.awt.Color a = this.queueNodeColorLeft;
        return a;
    }
    
    public java.awt.Color getQueueNodeColorRight()
    {
        java.awt.Color a = this.queueNodeColorRight;
        return a;
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
        java.awt.Color a0 = this.unvisitedNodeColor;
        a.setBackgroundColor(a0);
        java.awt.Color a1 = this.textColor;
        a.setTextColor(a1);
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
    }
    
    public void setMatrixShowing(boolean b)
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = b?1:0;
        a.setMatrixShowing(i != 0);
    }
    
    public void setStructureShowing(boolean b)
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = b?1:0;
        a.setStructureShowing(i != 0);
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
    
    public com.cim.AIA.ElementArray getQueue()
    {
        com.cim.AIA.ElementArray a = null;
        MSTKPriorityQueue a0 = this.pqueue;
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
            a1.setColumGap(5);
            a1.setElementWidth(40);
            MSTKPriorityQueue a2 = this.pqueue;
            java.util.Vector a3 = a2.getQueue();
            int i = 0;
            while(true)
            {
                int i0 = a3.size();
                if(i >= i0)
                {
                    break;
                }
                int i1 = this.VISIBLE_QUEUE_NODES;
                label2: {
                    int i2 = i1 + 1;
                    if(i != i2)
                    {
                        break label2;
                    }
                    break;
                }
                Object a4 = a3.elementAt(i);
                MSTKPriorityQueueMember dummy = (MSTKPriorityQueueMember)a4;
                MSTKPriorityQueueMember a5 = (MSTKPriorityQueueMember)a4;
                int i3 = a5.getFromKey();
                Integer a6 = new Integer(i3);
                int i4 = a5.getToKey();
                Integer a7 = new Integer(i4);
                aia.graph.common.TwinNode a8 = new aia.graph.common.TwinNode((Object)a6, (Object)a7, i);
                java.awt.Color a9 = this.queueNodeColorLeft;
                a8.setBackgroundColorLeft(a9);
                java.awt.Color a10 = this.queueNodeColorRight;
                a8.setBackgroundColorRight(a10);
                java.awt.Color a11 = this.textColor;
                a8.setTextColor(a11);
                a8.setHeight(20);
                int i5 = a5.getPriority();
                a8.setPriority(i5);
                int i6 = this.VISIBLE_QUEUE_NODES;
                if(i == i6)
                {
                    a8.setWidth(0);
                    a8.setIsVisible(false);
                }
                a1.setValue(i, (com.cim.AIA.Element)a8);
                int i7 = i + 1;
                i = i7;
            }
            a = a1;
        }
        return a;
    }
    
    public boolean isOverFlow()
    {
        int i = 0;
        MSTKPriorityQueue a = this.pqueue;
        label2: {
            label1: {
                label0: {
                    if(a == null)
                    {
                        break label0;
                    }
                    MSTKPriorityQueue a0 = this.pqueue;
                    int i0 = a0.size();
                    int i1 = this.VISIBLE_QUEUE_NODES;
                    if(i0 > i1)
                    {
                        break label1;
                    }
                }
                i = 0;
                break label2;
            }
            i = 1;
        }
        return i != 0;
    }
    
    private boolean contains(java.util.Vector a, int i)
    {
        int i0 = 0;
        while(true)
        {
            int i1 = 0;
            int i2 = a.size();
            label1: {
                label0: {
                    if(i0 < i2)
                    {
                        break label0;
                    }
                    i1 = 0;
                    break label1;
                }
                Object a0 = a.elementAt(i0);
                Integer dummy = (Integer)a0;
                Integer a1 = (Integer)a0;
                int i3 = a1.intValue();
                if(i3 != i)
                {
                    int i4 = i0 + 1;
                    i0 = i4;
                    continue;
                }
                i1 = 1;
            }
            return i1 != 0;
        }
    }
    
    private void findConnectedSet(java.util.Vector a, int i)
    {
        aia.graph.common.GraphNode a0 = this.getAdjacentNode(i);
        aia.graph.common.GraphNode a1 = a0;
        while(true)
        {
            label1: {
                label0: {
                    if(a1 == null)
                    {
                        break label0;
                    }
                    int i0 = a1.getKey();
                    if(i0 != -1)
                    {
                        break label1;
                    }
                }
                return;
            }
            aia.graph.common.LinkContainer a2 = this.visited_links;
            label2: {
                if(a2 == null)
                {
                    break label2;
                }
                aia.graph.common.LinkContainer a3 = this.visited_links;
                aia.graph.common.NodeInfo a4 = new aia.graph.common.NodeInfo(i, 0, 0);
                int i1 = a1.getKey();
                aia.graph.common.NodeInfo a5 = new aia.graph.common.NodeInfo(i1, 0, 0);
                aia.graph.common.Link a6 = a3.findLink(a4, a5);
                if(a6 == null)
                {
                    break label2;
                }
                int i2 = a1.getKey();
                int i3 = this.contains(a, i2)?1:0;
                if(i3 == 0)
                {
                    int i4 = a1.getKey();
                    Integer a7 = new Integer(i4);
                    a.addElement((Object)a7);
                    int i5 = a1.getKey();
                    this.findConnectedSet(a, i5);
                }
            }
            aia.graph.common.GraphNode a8 = a1.getNext();
            a1 = a8;
        }
    }
    
    private boolean isInset(int i, int i0)
    {
        java.util.Vector a = new java.util.Vector();
        this.findConnectedSet(a, i);
        int i1 = 0;
        while(true)
        {
            int i2 = 0;
            int i3 = a.size();
            label1: {
                label0: {
                    if(i1 < i3)
                    {
                        break label0;
                    }
                    i2 = 0;
                    break label1;
                }
                Object a0 = a.elementAt(i1);
                Integer dummy = (Integer)a0;
                Integer a1 = (Integer)a0;
                int i4 = a1.intValue();
                if(i4 != i0)
                {
                    int i5 = i1 + 1;
                    i1 = i5;
                    continue;
                }
                i2 = 1;
            }
            return i2 != 0;
        }
    }
    
    private void createConnectedNodesVector(int i, int i0)
    {
        java.util.Vector a = new java.util.Vector();
        java.util.Vector a0 = new java.util.Vector();
        this.connected_nodes = a0;
        this.findConnectedSet(a, i0);
        int i1 = 0;
        while(true)
        {
            int i2 = a.size();
            if(i1 >= i2)
            {
                break;
            }
            else
            {
                Object a1 = a.elementAt(i1);
                Integer dummy = (Integer)a1;
                Integer a2 = (Integer)a1;
                java.util.Vector a3 = this.connected_nodes;
                a3.addElement((Object)a2);
                int i3 = i1 + 1;
                i1 = i3;
            }
        }
        this.findConnectedSet(a, i);
        int i4 = 0;
        while(true)
        {
            int i5 = a.size();
            if(i4 >= i5)
            {
                return;
            }
            else
            {
                Object a4 = a.elementAt(i4);
                Integer dummy0 = (Integer)a4;
                Integer a5 = (Integer)a4;
                java.util.Vector a6 = this.connected_nodes;
                a6.addElement((Object)a5);
                int i6 = i4 + 1;
                i4 = i6;
            }
        }
    }
    
    private void sortedges()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int[][] a0 = a.getAdjacencyMatrixArray();
        int i = 0;
        while(true)
        {
            aia.graph.common.GraphAlgorithmCommon a1 = this.commons;
            int i0 = a1.getNumberOfNodes();
            if(i >= i0)
            {
                return;
            }
            aia.graph.common.GraphNode a2 = this.getAdjacentNode(i);
            aia.graph.common.GraphNode a3 = a2;
            while(true)
            {
                label0: {
                    if(a3 == null)
                    {
                        break label0;
                    }
                    int i1 = a3.getKey();
                    if(i1 != -1)
                    {
                        MSTKPriorityQueue a4 = this.pqueue;
                        int i2 = a3.getKey();
                        int[] a5 = a0[i];
                        int i3 = a3.getKey();
                        int i4 = a5[i3];
                        int i5 = a4.update(i, i2, i4);
                        aia.graph.common.GraphNode a6 = a3.getNext();
                        a3 = a6;
                        continue;
                    }
                }
                int i6 = i + 1;
                i = i6;
            }
        }
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
        this.edges_added = 0;
        this.setPosition("2.1.1");
        MSTKPriorityQueue a2 = new MSTKPriorityQueue();
        this.pqueue = a2;
        this.sortedges();
        this.setPosition("2.2.1");
        this.setPosition("2.3");
        this.setPosition("4");
        this.build();
        this.setPosition("7");
        this.m_bIsRunning = false;
    }
    
    private void build()
    {
        while(true)
        {
            MSTKPriorityQueue a = this.pqueue;
            int i = a.isEmpty()?1:0;
            label1: {
                label0: {
                    if(i == 0)
                    {
                        break label0;
                    }
                    this.setPosition("5");
                    this.setPosition("6");
                    break label1;
                }
                this.setPosition("5");
                MSTKPriorityQueue a0 = this.pqueue;
                MSTKPriorityQueueMember a1 = a0.peek();
                int i0 = a1.getFromKey();
                int i1 = a1.getToKey();
                this.addQueueRequest(2, i0, i1, 0);
                this.setPosition("5.2");
                MSTKPriorityQueue a2 = this.pqueue;
                MSTKPriorityQueueMember a3 = a2.remove();
                this.current_edge = a3;
                MSTKPriorityQueueMember a4 = this.current_edge;
                int i2 = a4.getFromKey();
                MSTKPriorityQueueMember a5 = this.current_edge;
                int i3 = a5.getToKey();
                this.createConnectedNodesVector(i2, i3);
                MSTKPriorityQueueMember a6 = this.current_edge;
                int i4 = a6.getFromKey();
                this.v1_marker = i4;
                MSTKPriorityQueueMember a7 = this.current_edge;
                int i5 = a7.getToKey();
                this.v2_marker = i5;
                this.setPosition("5.3");
                int i6 = a3.getToKey();
                int i7 = a3.getFromKey();
                int i8 = this.isInset(i6, i7)?1:0;
                if(i8 != 0)
                {
                    continue;
                }
                int i9 = a3.getFromKey();
                int i10 = a3.getToKey();
                int i11 = this.isInset(i9, i10)?1:0;
                if(i11 != 0)
                {
                    continue;
                }
                int i12 = a3.getFromKey();
                int i13 = a3.getToKey();
                this.makeQuestion(i12, i13);
                this.askQuestionsWithoutSetPosition();
                aia.graph.common.LinkContainer a8 = this.visited_links;
                int i14 = a3.getFromKey();
                aia.graph.common.NodeInfo a9 = new aia.graph.common.NodeInfo(i14, 0, 0);
                int i15 = a3.getToKey();
                aia.graph.common.NodeInfo a10 = new aia.graph.common.NodeInfo(i15, 0, 0);
                aia.graph.common.GraphAlgorithmCommon a11 = this.commons;
                int i16 = a11.getOmniDirectional()?1:0;
                aia.graph.common.Link a12 = a8.addLink(a9, a10, i16 != 0, 0);
                int i17 = this.edges_added;
                int i18 = i17 + 1;
                this.edges_added = i18;
                this.setPosition("5.4");
                this.setPosition("5.6");
                int i19 = this.edges_added;
                aia.graph.common.GraphAlgorithmCommon a13 = this.commons;
                int i20 = a13.getNumberOfNodes();
                int i21 = i20 - 1;
                if(i19 != i21)
                {
                    continue;
                }
                this.setPosition("5.6.1");
                this.setPosition("6");
            }
            return;
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
            String s2 = MSTKGraphMaps.HIGHLIGHT_COLOR;
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
            String s3 = MSTKGraphMaps.NODE_COLOR;
            int i2 = s.equalsIgnoreCase(s3)?1:0;
            if(i2 != 0)
            {
                java.awt.Color a3 = a.getColor();
                this.unvisitedNodeColor = a3;
            }
        }
    }
    
    private void addQueueRequest(int i, int i0, int i1, int i2)
    {
        java.util.Vector a = this.queue_requests;
        if(a == null)
        {
            java.util.Vector a0 = new java.util.Vector();
            this.queue_requests = a0;
        }
        aia.graph.common.QueueRequest a1 = new aia.graph.common.QueueRequest(i, i0, i1);
        if(i == 3)
        {
            a1.setParam(i2);
        }
        java.util.Vector a2 = this.queue_requests;
        a2.addElement((Object)a1);
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
            aia.graph.common.TwinNode a7 = null;
            int i1 = 0;
            label4: while(true)
            {
                aia.graph.common.TwinNode a8 = null;
                java.util.Vector a9 = this.queue_requests;
                aia.graph.common.TwinNode a10 = a7;
                int i2 = a9.size();
                aia.graph.common.TwinNode a11 = a10;
                aia.graph.common.TwinNode a12 = a11;
                if(i1 >= i2)
                {
                    break;
                }
                else
                {
                    a8 = a12;
                }
                java.util.Vector a13 = this.queue_requests;
                aia.graph.common.TwinNode a14 = a8;
                Object a15 = a13.elementAt(i1);
                aia.graph.common.TwinNode a16 = a14;
                aia.graph.common.QueueRequest dummy0 = (aia.graph.common.QueueRequest)a15;
                aia.graph.common.QueueRequest a17 = (aia.graph.common.QueueRequest)a15;
                aia.graph.common.TwinNode a18 = a16;
                aia.graph.common.TwinNode a19 = a18;
                int i3 = 0;
                while(true)
                {
                    aia.graph.common.TwinNode a20 = null;
                    aia.graph.common.TwinNode a21 = null;
                    int i4 = a5.getSize();
                    aia.graph.common.TwinNode a22 = a19;
                    aia.graph.common.TwinNode a23 = a22;
                    aia.graph.common.TwinNode a24 = a22;
                    if(i3 >= i4)
                    {
                        aia.graph.common.TwinNode a25 = a24;
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
                    aia.graph.common.TwinNode a27 = a20;
                    aia.graph.common.TwinNode dummy1 = (aia.graph.common.TwinNode)a26;
                    aia.graph.common.TwinNode a28 = (aia.graph.common.TwinNode)a26;
                    aia.graph.common.TwinNode a29 = a27;
                    int i6 = a28.getLValue();
                    aia.graph.common.TwinNode a30 = a29;
                    int i7 = a17.getFromKey();
                    aia.graph.common.TwinNode a31 = a30;
                    label5: {
                        aia.graph.common.TwinNode a32 = null;
                        aia.graph.common.TwinNode a33 = null;
                        aia.graph.common.TwinNode a34 = null;
                        aia.graph.common.TwinNode a35 = null;
                        aia.graph.common.TwinNode a36 = null;
                        aia.graph.common.TwinNode a37 = null;
                        aia.graph.common.TwinNode a38 = a31;
                        aia.graph.common.TwinNode a39 = a31;
                        if(i6 != i7)
                        {
                            a21 = a39;
                            break label5;
                        }
                        else
                        {
                            a32 = a38;
                        }
                        int i8 = a28.getRValue();
                        aia.graph.common.TwinNode a40 = a32;
                        int i9 = a17.getToKey();
                        aia.graph.common.TwinNode a41 = a40;
                        aia.graph.common.TwinNode a42 = a41;
                        aia.graph.common.TwinNode a43 = a41;
                        if(i8 != i9)
                        {
                            a21 = a43;
                            break label5;
                        }
                        else
                        {
                            a33 = a42;
                        }
                        int i10 = a17.getType();
                        aia.graph.common.TwinNode a44 = a33;
                        label6: {
                            aia.graph.common.TwinNode a45 = null;
                            aia.graph.common.TwinNode a46 = a44;
                            aia.graph.common.TwinNode a47 = a44;
                            if(i10 != 1)
                            {
                                a34 = a47;
                                break label6;
                            }
                            else
                            {
                                a45 = a46;
                            }
                            aia.graph.common.TwinNode a48 = a45;
                            com.cim.AIA.InsertTween a49 = new com.cim.AIA.InsertTween((com.cim.AIA.Paintable)a3, (com.cim.AIA.Sizeable)a28, i);
                            aia.graph.common.TwinNode a50 = a48;
                            a6.add((com.cim.AIA.Tween)a49);
                            aia.graph.common.TwinNode a51 = a50;
                            int i11 = i3 + 1;
                            aia.graph.common.TwinNode a52 = a28;
                            aia.graph.common.TwinNode a53 = a51;
                            int i12 = i11;
                            while(true)
                            {
                                int i13 = a5.getSize();
                                aia.graph.common.TwinNode a54 = a53;
                                aia.graph.common.TwinNode a55 = a54;
                                if(i12 >= i13)
                                {
                                    a21 = a55;
                                    break label5;
                                }
                                else
                                {
                                    com.cim.AIA.Element a56 = a5.getElement(i12);
                                    aia.graph.common.TwinNode dummy2 = (aia.graph.common.TwinNode)a56;
                                    aia.graph.common.TwinNode a57 = (aia.graph.common.TwinNode)a56;
                                    java.awt.Point a58 = a52.getPosition();
                                    java.awt.Point a59 = a57.getPosition();
                                    com.cim.AIA.MoveTween a60 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Moveable)a57, a58, a59, false, i);
                                    a6.add((com.cim.AIA.Tween)a60);
                                    int i14 = i12 + 1;
                                    a52 = a57;
                                    a53 = a57;
                                    i12 = i14;
                                }
                            }
                        }
                        int i15 = a17.getType();
                        aia.graph.common.TwinNode a61 = a34;
                        label7: {
                            aia.graph.common.TwinNode a62 = null;
                            aia.graph.common.TwinNode a63 = a61;
                            aia.graph.common.TwinNode a64 = a61;
                            if(i15 != 2)
                            {
                                a62 = a64;
                            }
                            else
                            {
                                a35 = a63;
                                break label7;
                            }
                            int i16 = a17.getType();
                            aia.graph.common.TwinNode a65 = a62;
                            aia.graph.common.TwinNode a66 = a65;
                            if(i16 != 3)
                            {
                                a21 = a66;
                                break label5;
                            }
                            int i17 = a17.getParam();
                            com.cim.AIA.Element a67 = a5.getElement(i17);
                            aia.graph.common.TwinNode dummy3 = (aia.graph.common.TwinNode)a67;
                            aia.graph.common.TwinNode a68 = (aia.graph.common.TwinNode)a67;
                            java.awt.Point a69 = a68.getPosition();
                            java.awt.Point a70 = a28.getPosition();
                            com.cim.AIA.MoveTween a71 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Moveable)a28, a69, a70, false, i);
                            a6.add((com.cim.AIA.Tween)a71);
                            aia.graph.common.TwinNode a72 = a68;
                            int i18 = i3;
                            while(true)
                            {
                                int i19 = a17.getParam();
                                if(i18 >= i19)
                                {
                                    a21 = a72;
                                    break label5;
                                }
                                else
                                {
                                    com.cim.AIA.Element a73 = a5.getElement(i18);
                                    aia.graph.common.TwinNode dummy4 = (aia.graph.common.TwinNode)a73;
                                    aia.graph.common.TwinNode a74 = (aia.graph.common.TwinNode)a73;
                                    int i20 = i18 + 1;
                                    com.cim.AIA.Element a75 = a5.getElement(i20);
                                    aia.graph.common.TwinNode dummy5 = (aia.graph.common.TwinNode)a75;
                                    aia.graph.common.TwinNode a76 = (aia.graph.common.TwinNode)a75;
                                    java.awt.Point a77 = a74.getPosition();
                                    java.awt.Point a78 = a76.getPosition();
                                    com.cim.AIA.MoveTween a79 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Moveable)a76, a77, a78, false, i);
                                    a6.add((com.cim.AIA.Tween)a79);
                                    int i21 = i18 + 1;
                                    a72 = a76;
                                    i18 = i21;
                                }
                            }
                        }
                        aia.graph.common.TwinNode a80 = a35;
                        java.awt.Point a81 = a28.getPosition();
                        aia.graph.common.TwinNode a82 = a80;
                        aia.graph.common.TwinNode a83 = a82;
                        int i22 = aia.graph.common.GraphMapsCanvasExt.QUEUE_X;
                        aia.graph.common.TwinNode a84 = a83;
                        int i23 = i22 - 50;
                        int i24 = aia.graph.common.GraphMapsCanvasExt.QUEUE_Y;
                        aia.graph.common.TwinNode a85 = a84;
                        java.awt.Point a86 = new java.awt.Point(i23, i24);
                        aia.graph.common.TwinNode a87 = a85;
                        com.cim.AIA.MoveTween a88 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Moveable)a28, a81, a86, false, i);
                        aia.graph.common.TwinNode a89 = a87;
                        a6.add((com.cim.AIA.Tween)a88);
                        aia.graph.common.TwinNode a90 = a89;
                        int i25 = i3 + 1;
                        aia.graph.common.TwinNode a91 = a28;
                        aia.graph.common.TwinNode a92 = a90;
                        int i26 = i25;
                        while(true)
                        {
                            int i27 = a5.getSize();
                            aia.graph.common.TwinNode a93 = a92;
                            aia.graph.common.TwinNode a94 = a93;
                            if(i26 >= i27)
                            {
                                a36 = a94;
                                break;
                            }
                            else
                            {
                                com.cim.AIA.Element a95 = a5.getElement(i26);
                                aia.graph.common.TwinNode dummy6 = (aia.graph.common.TwinNode)a95;
                                aia.graph.common.TwinNode a96 = (aia.graph.common.TwinNode)a95;
                                java.awt.Point a97 = a96.getPosition();
                                java.awt.Point a98 = a91.getPosition();
                                com.cim.AIA.MoveTween a99 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Moveable)a96, a97, a98, false, i);
                                a6.add((com.cim.AIA.Tween)a99);
                                int i28 = i26 + 1;
                                a91 = a96;
                                a92 = a96;
                                i26 = i28;
                            }
                        }
                        aia.graph.common.TwinNode a100 = a36;
                        aia.graph.common.TwinNode a101 = a36;
                        if(a36 == null)
                        {
                            a21 = a101;
                            break label5;
                        }
                        else
                        {
                            a37 = a100;
                        }
                        int i29 = this.isOverFlow()?1:0;
                        aia.graph.common.TwinNode a102 = a37;
                        aia.graph.common.TwinNode a103 = a102;
                        aia.graph.common.TwinNode a104 = a102;
                        if(i29 == 0)
                        {
                            a21 = a104;
                        }
                        else
                        {
                            aia.graph.common.TwinNode a105 = a103;
                            a105.setIsVisible(true);
                            aia.graph.common.TwinNode a106 = a105;
                            aia.graph.common.TwinNode a107 = a106;
                            com.cim.AIA.InsertTween a108 = new com.cim.AIA.InsertTween((com.cim.AIA.Paintable)a3, (com.cim.AIA.Sizeable)a107, i);
                            aia.graph.common.TwinNode a109 = a107;
                            a6.add((com.cim.AIA.Tween)a108);
                            aia.graph.common.TwinNode a110 = a109;
                            a21 = a110;
                        }
                    }
                    int i30 = i3 + 1;
                    a19 = a21;
                    i3 = i30;
                }
            }
            int i31 = a6.getNumberOfTweens();
            a1 = i31 != 0?a6:null;
        }
        return a1;
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
        java.util.Vector a = new java.util.Vector();
        this.queue_requests = a;
    }
    
    static
    {
        String s = localization.Messages.getString("MSTKGraphMaps.3");
        MSTKGraphMaps.HIGHLIGHT_COLOR = s;
        String s0 = localization.Messages.getString("MSTKGraphMaps.2");
        MSTKGraphMaps.NODE_COLOR = s0;
        String s1 = localization.Messages.getString("MSTKGraphMaps.1");
        MSTKGraphMaps.BUILD_MODE_LABEL = s1;
    }
}