public class MinSpanTreePrimGraphMaps extends aia.graph.common.GraphMaps implements com.cim.AIA.ColorSelectionListener, com.cim.AIA.MethodSelectionListener {
    protected java.awt.Color textColor;
    protected java.awt.Color highlightColor;
    protected java.awt.Color highlightLinkColor;
    protected java.awt.Color unvisitedNodeColor;
    protected java.awt.Color adjacentNodesColor;
    protected java.awt.Color adjacentNodeColor;
    protected java.awt.Color backgroundColor;
    protected java.awt.Color flagUnvisitedColor;
    protected java.awt.Color flagVisitedColor;
    protected java.awt.Color visitedNodeColor;
    protected java.awt.Color queueNodeColorLeft;
    protected java.awt.Color queueNodeColorRight;
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
    private MinSpanTreePrimPriorityQueue pqueue;
    private MinSpanTreePrimPriorityQueueMember current_node;
    protected java.util.Vector adjacent_nodes;
    protected java.util.Vector questions;
    protected java.util.Vector queue_requests;
    protected int VISIBLE_QUEUE_NODES;
    final public static int UNMARKED = -10;
    final public static String VISIT_MODE_LABEL;
    protected boolean m_backMode;
    protected int[] m_stored_data;
    
    public MinSpanTreePrimGraphMaps(com.cim.AIA.AlgorithmThread a, Object a0)
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
        java.awt.Color a7 = java.awt.Color.white;
        this.backgroundColor = a7;
        java.awt.Color a8 = java.awt.Color.lightGray;
        this.flagUnvisitedColor = a8;
        java.awt.Color a9 = java.awt.Color.gray;
        this.flagVisitedColor = a9;
        java.awt.Color a10 = java.awt.Color.gray;
        this.visitedNodeColor = a10;
        java.awt.Color a11 = new java.awt.Color(109, 234, 234);
        this.queueNodeColorLeft = a11;
        java.awt.Color a12 = new java.awt.Color(84, 183, 183);
        this.queueNodeColorRight = a12;
        java.awt.Color a13 = new java.awt.Color(109, 234, 234);
        this.waitingNodeColor = a13;
        this.commons = null;
        this.m_bIsRunning = false;
        this.visit_sequence = null;
        this.visit_counter = -10;
        this.visited_links = null;
        this.adj_node = null;
        this.k_marker = -10;
        this.pqueue = null;
        this.current_node = null;
        this.adjacent_nodes = null;
        java.util.Vector a14 = new java.util.Vector();
        this.questions = a14;
        java.util.Vector a15 = new java.util.Vector();
        this.queue_requests = a15;
        this.VISIBLE_QUEUE_NODES = 5;
        this.m_backMode = false;
        this.m_stored_data = null;
        this.initialise(a0);
        com.cim.AIA.ConfigurationManager a16 = com.cim.AIA.ConfigurationManager.getConfigurationManager();
        java.awt.Color a17 = this.highlightColor;
        String s = MinSpanTreePrimGraphMaps.HIGHLIGHT_COLOR;
        com.cim.AIA.ColorSelection a18 = new com.cim.AIA.ColorSelection(a17, s);
        a18.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        a16.addColorSelection(a18);
        java.awt.Color a19 = this.unvisitedNodeColor;
        String s0 = MinSpanTreePrimGraphMaps.NODE_COLOR;
        com.cim.AIA.ColorSelection a20 = new com.cim.AIA.ColorSelection(a19, s0);
        a20.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        a16.addColorSelection(a20);
        a16.addColorSelectionListener((com.cim.AIA.ColorSelectionListener)this);
    }
    
    public void initialiseMethods(String s, com.cim.AIA.MethodSelectable a)
    {
        Object a0 = a;
        String s0 = MinSpanTreePrimGraphMaps.VISIT_MODE_LABEL;
        String s1 = localization.Messages.getString("MinSpanTreePrimGraphMaps.2");
        com.cim.AIA.Logger a1 = this.getLogger();
        com.cim.AIA.BreakPoint a2 = this.getBreakPoint();
        com.cim.AIA.MethodSelection a3 = new com.cim.AIA.MethodSelection(s0, s, s1, "4", a1, a2);
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
        this.current_node = null;
        this.adj_node = null;
        this.k_marker = -10;
        this.pqueue = null;
        java.util.Vector a0 = new java.util.Vector();
        this.questions = a0;
        java.util.Vector a1 = new java.util.Vector();
        this.queue_requests = a1;
        this.adjacent_nodes = null;
        this.m_bIsRunning = false;
        aia.graph.common.GraphAlgorithmCommon a2 = this.commons;
        if(a2 != null)
        {
            aia.graph.common.GraphAlgorithmCommon a3 = this.commons;
            a3.setData(a);
        }
        else
        {
            aia.graph.common.GraphAlgorithmCommon a4 = new aia.graph.common.GraphAlgorithmCommon((aia.graph.common.GraphMaps)this, a);
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
    
    public int getKMarker()
    {
        int i = this.k_marker;
        return i;
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
                int i1 = a.getIntValue();
                int i2 = a6.intValue();
                if(i1 != i2)
                {
                    int i3 = i + 1;
                    i = i3;
                    continue;
                }
                java.awt.Color a7 = this.adjacentNodesColor;
                a.setBackgroundColor(a7);
                break;
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
        label2: {
            if(a11 == null)
            {
                break label2;
            }
            int[] a12 = this.visit_sequence;
            int i6 = a.getIntValue();
            int i7 = a12[i6];
            label3: {
                if(i7 <= 0)
                {
                    break label3;
                }
                java.awt.Color a13 = this.visitedNodeColor;
                a.setBackgroundColor(a13);
                break label2;
            }
            int[] a14 = this.visit_sequence;
            int i8 = a.getIntValue();
            int i9 = a14[i8];
            if(i9 < 0)
            {
                java.awt.Color a15 = this.waitingNodeColor;
                a.setBackgroundColor(a15);
            }
        }
        int i10 = a.getIntValue();
        int i11 = this.k_marker;
        if(i10 == i11)
        {
            java.awt.Color a16 = this.highlightColor;
            a.setBackgroundColor(a16);
        }
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
    }
    
    public java.util.Vector getLinks()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        java.util.Vector a0 = a.getLinks();
        return a0;
    }
    
    public aia.graph.common.TwinNode getCurrentPQNode()
    {
        aia.graph.common.TwinNode a = null;
        MinSpanTreePrimPriorityQueueMember a0 = this.current_node;
        if(a0 != null)
        {
            MinSpanTreePrimPriorityQueueMember a1 = this.current_node;
            int i = a1.getKey();
            Integer a2 = new Integer(i);
            MinSpanTreePrimPriorityQueueMember a3 = this.current_node;
            int i0 = a3.getParent();
            Integer a4 = new Integer(i0);
            aia.graph.common.TwinNode a5 = new aia.graph.common.TwinNode((Object)a2, (Object)a4, 0);
            java.awt.Color a6 = this.queueNodeColorLeft;
            a5.setBackgroundColorLeft(a6);
            java.awt.Color a7 = this.queueNodeColorRight;
            a5.setBackgroundColorRight(a7);
            java.awt.Color a8 = this.textColor;
            a5.setTextColor(a8);
            a5.setHeight(20);
            MinSpanTreePrimPriorityQueueMember a9 = this.current_node;
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
    
    public java.awt.Color getAdjacentNodeColor()
    {
        java.awt.Color a = this.adjacentNodeColor;
        return a;
    }
    
    public java.awt.Color getAdjacentNodesColor()
    {
        java.awt.Color a = this.adjacentNodesColor;
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
                java.awt.Color a17 = this.textColor;
                a2.setTextColor(a17);
                int i5 = this.k_marker;
                if(i == i5)
                {
                    java.awt.Color a18 = this.highlightColor;
                    a2.setBackgroundColor(a18);
                }
                a2.setHeight(20);
                a1.setValue(i, (com.cim.AIA.Element)a2);
                int i6 = i + 1;
                i = i6;
            }
            a = a1;
        }
        return a;
    }
    
    public com.cim.AIA.ElementArray getQueue()
    {
        com.cim.AIA.ElementArray a = null;
        MinSpanTreePrimPriorityQueue a0 = this.pqueue;
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
            MinSpanTreePrimPriorityQueue a2 = this.pqueue;
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
                MinSpanTreePrimPriorityQueueMember dummy = (MinSpanTreePrimPriorityQueueMember)a4;
                MinSpanTreePrimPriorityQueueMember a5 = (MinSpanTreePrimPriorityQueueMember)a4;
                int i3 = a5.getKey();
                Integer a6 = new Integer(i3);
                int i4 = a5.getParent();
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
        MinSpanTreePrimPriorityQueue a = this.pqueue;
        label2: {
            label1: {
                label0: {
                    if(a == null)
                    {
                        break label0;
                    }
                    MinSpanTreePrimPriorityQueue a0 = this.pqueue;
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
            int[] a4 = this.visit_sequence;
            int i2 = a4.length;
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
        this.setPosition("2.1.1");
        int i4 = 0;
        while(true)
        {
            int[] a6 = this.visit_sequence;
            int i5 = a6.length;
            if(i4 >= i5)
            {
                break;
            }
            else
            {
                this.k_marker = i4;
                this.setPosition("2.1.2");
                int[] a7 = this.visit_sequence;
                a7[i4] = 0;
                this.setPosition("2.1.2.1");
                int i6 = i4 + 1;
                i4 = i6;
            }
        }
        this.setPosition("2.1.2");
        this.setPosition("2.1.3");
        this.visit_counter = 0;
        this.setPosition("2.2.1");
        MinSpanTreePrimPriorityQueue a8 = new MinSpanTreePrimPriorityQueue();
        this.pqueue = a8;
        this.setPosition("2.3.1");
        this.setPosition("2.4");
        int i7 = 0;
        while(true)
        {
            aia.graph.common.GraphAlgorithmCommon a9 = this.commons;
            int i8 = a9.getNumberOfNodes();
            if(i7 >= i8)
            {
                this.setPosition("3.1.1");
                this.setPosition("4.1");
                this.setPosition("9");
                this.m_bIsRunning = false;
                return;
            }
            this.k_marker = i7;
            this.setPosition("3.1.1");
            this.setPosition("3.3");
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
    
    protected void visit(int i)
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        aia.graph.common.GraphNode[] a0 = a.getAdjacencyStructureArray();
        aia.graph.common.GraphAlgorithmCommon a1 = this.commons;
        int[][] a2 = a1.getAdjacencyMatrixArray();
        this.k_marker = i;
        MinSpanTreePrimPriorityQueue a3 = this.pqueue;
        int i0 = a3.update(i, -1, 0);
        this.addQueueRequest(1, i, 0);
        this.setPosition("5");
        label2: while(true)
        {
            MinSpanTreePrimPriorityQueue a4 = this.pqueue;
            int i1 = a4.isEmpty()?1:0;
            if(i1 != 0)
            {
                this.setPosition("7");
                this.setPosition("8");
                return;
            }
            this.setPosition("7");
            MinSpanTreePrimPriorityQueue a5 = this.pqueue;
            MinSpanTreePrimPriorityQueueMember a6 = a5.peek();
            int i2 = a6.getKey();
            this.k_marker = i2;
            this.addQueueRequest(2, i2, 0);
            this.setPosition("7.2");
            MinSpanTreePrimPriorityQueue a7 = this.pqueue;
            MinSpanTreePrimPriorityQueueMember a8 = a7.remove();
            this.current_node = a8;
            this.makeQuestion(i2);
            this.askQuestionsWithoutSetPosition();
            this.setPosition("7.3.1");
            int[] a9 = this.visit_sequence;
            int i3 = a9[i2];
            label1: {
                label0: {
                    if(i3 == 0)
                    {
                        break label0;
                    }
                    int[] a10 = this.visit_sequence;
                    int i4 = a10[i2];
                    if(i4 != -1)
                    {
                        break label1;
                    }
                }
                int i5 = a8.getParent();
                if(i5 != -1)
                {
                    aia.graph.common.LinkContainer a11 = this.visited_links;
                    int i6 = a8.getParent();
                    aia.graph.common.NodeInfo a12 = new aia.graph.common.NodeInfo(i6, 0, 0);
                    aia.graph.common.NodeInfo a13 = new aia.graph.common.NodeInfo(i2, 0, 0);
                    aia.graph.common.GraphAlgorithmCommon a14 = this.commons;
                    int i7 = a14.getOmniDirectional()?1:0;
                    aia.graph.common.Link a15 = a11.addLink(a12, a13, i7 != 0, 0);
                }
                this.setPosition("7.3.1.1");
                int[] a16 = this.visit_sequence;
                int i8 = this.visit_counter;
                int i9 = i8 + 1;
                this.visit_counter = i9;
                a16[i2] = i9;
                this.setPosition("7.3.1.3");
            }
            aia.graph.common.GraphNode a17 = a0[i2];
            Integer a18 = new Integer(0);
            aia.graph.common.GraphMapsNode a19 = new aia.graph.common.GraphMapsNode((Object)a18, 0, 0);
            this.adj_node = a19;
            aia.graph.common.GraphMapsNode a20 = this.adj_node;
            a20.setFrom(i2);
            aia.graph.common.GraphMapsNode a21 = this.adj_node;
            int i10 = a17.getKey();
            a21.setTo(i10);
            this.createAdjacentNodeVector(a17);
            this.setPosition("7.4.1");
            aia.graph.common.GraphNode a22 = a17;
            while(true)
            {
                int i11 = a22.getKey();
                if(i11 == -1)
                {
                    this.setPosition("7.4.2");
                    continue label2;
                }
                this.setPosition("7.4.2");
                this.setPosition("7.5.1");
                int[] a23 = this.visit_sequence;
                int i12 = a22.getKey();
                int i13 = a23[i12];
                label4: {
                    label3: {
                        if(i13 == 0)
                        {
                            break label3;
                        }
                        int[] a24 = this.visit_sequence;
                        int i14 = a22.getKey();
                        int i15 = a24[i14];
                        if(i15 != -1)
                        {
                            break label4;
                        }
                    }
                    this.setPosition("7.5.3");
                    MinSpanTreePrimPriorityQueue a25 = this.pqueue;
                    int i16 = a22.getKey();
                    int[] a26 = a2[i2];
                    int i17 = a22.getKey();
                    int i18 = a26[i17];
                    int i19 = a25.update(i16, i2, i18);
                    if(i19 == -1)
                    {
                        break label4;
                    }
                    int[] a27 = this.visit_sequence;
                    int i20 = a22.getKey();
                    a27[i20] = -1;
                    if(i19 != -2)
                    {
                        int i21 = a22.getKey();
                        this.addQueueRequest(3, i21, i19);
                    }
                    else
                    {
                        int i22 = a22.getKey();
                        this.addQueueRequest(1, i22, 0);
                    }
                    this.setPosition("7.5.4");
                    this.setPosition("7.5.6");
                }
                aia.graph.common.GraphNode a28 = a22.getNext();
                Integer a29 = new Integer(0);
                aia.graph.common.GraphMapsNode a30 = new aia.graph.common.GraphMapsNode((Object)a29, 0, 0);
                this.adj_node = a30;
                aia.graph.common.GraphMapsNode a31 = this.adj_node;
                a31.setFrom(i2);
                aia.graph.common.GraphMapsNode a32 = this.adj_node;
                int i23 = a28.getKey();
                a32.setTo(i23);
                this.createAdjacentNodeVector(a28);
                this.setPosition("7.5.7");
                a22 = a28;
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
            String s2 = MinSpanTreePrimGraphMaps.HIGHLIGHT_COLOR;
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
            String s3 = MinSpanTreePrimGraphMaps.NODE_COLOR;
            int i2 = s.equalsIgnoreCase(s3)?1:0;
            if(i2 != 0)
            {
                java.awt.Color a3 = a.getColor();
                this.unvisitedNodeColor = a3;
            }
        }
    }
    
    private void addQueueRequest(int i, int i0, int i1)
    {
        java.util.Vector a = this.queue_requests;
        if(a == null)
        {
            java.util.Vector a0 = new java.util.Vector();
            this.queue_requests = a0;
        }
        aia.graph.common.QueueRequest a1 = new aia.graph.common.QueueRequest(i, i0);
        if(i == 3)
        {
            a1.setParam(i1);
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
                        int i8 = 0;
                        aia.graph.common.TwinNode a35 = a31;
                        aia.graph.common.TwinNode a36 = a31;
                        if(i6 != i7)
                        {
                            a21 = a36;
                            break label5;
                        }
                        else
                        {
                            a32 = a35;
                        }
                        int i9 = a17.getType();
                        aia.graph.common.TwinNode a37 = a32;
                        label6: {
                            aia.graph.common.TwinNode a38 = null;
                            aia.graph.common.TwinNode a39 = a37;
                            aia.graph.common.TwinNode a40 = a37;
                            if(i9 != 1)
                            {
                                a33 = a40;
                                break label6;
                            }
                            else
                            {
                                a38 = a39;
                            }
                            aia.graph.common.TwinNode a41 = a38;
                            com.cim.AIA.InsertTween a42 = new com.cim.AIA.InsertTween((com.cim.AIA.Paintable)a3, (com.cim.AIA.Sizeable)a28, i);
                            aia.graph.common.TwinNode a43 = a41;
                            a6.add((com.cim.AIA.Tween)a42);
                            aia.graph.common.TwinNode a44 = a43;
                            int i10 = i3 + 1;
                            aia.graph.common.TwinNode a45 = a28;
                            aia.graph.common.TwinNode a46 = a44;
                            int i11 = i10;
                            while(true)
                            {
                                int i12 = a5.getSize();
                                aia.graph.common.TwinNode a47 = a46;
                                aia.graph.common.TwinNode a48 = a47;
                                if(i11 >= i12)
                                {
                                    a21 = a48;
                                    break label5;
                                }
                                else
                                {
                                    com.cim.AIA.Element a49 = a5.getElement(i11);
                                    aia.graph.common.TwinNode dummy2 = (aia.graph.common.TwinNode)a49;
                                    aia.graph.common.TwinNode a50 = (aia.graph.common.TwinNode)a49;
                                    java.awt.Point a51 = a45.getPosition();
                                    java.awt.Point a52 = a50.getPosition();
                                    com.cim.AIA.MoveTween a53 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Moveable)a50, a51, a52, false, i);
                                    a6.add((com.cim.AIA.Tween)a53);
                                    int i13 = i11 + 1;
                                    a45 = a50;
                                    a46 = a50;
                                    i11 = i13;
                                }
                            }
                        }
                        int i14 = a17.getType();
                        aia.graph.common.TwinNode a54 = a33;
                        label7: {
                            aia.graph.common.TwinNode a55 = null;
                            aia.graph.common.TwinNode a56 = null;
                            aia.graph.common.TwinNode a57 = null;
                            aia.graph.common.TwinNode a58 = a54;
                            aia.graph.common.TwinNode a59 = a54;
                            if(i14 != 2)
                            {
                                a34 = a59;
                                break label7;
                            }
                            else
                            {
                                a55 = a58;
                            }
                            aia.graph.common.TwinNode a60 = a55;
                            java.awt.Point a61 = a28.getPosition();
                            aia.graph.common.TwinNode a62 = a60;
                            aia.graph.common.TwinNode a63 = a62;
                            int i15 = aia.graph.common.GraphMapsCanvasExt.QUEUE_X;
                            aia.graph.common.TwinNode a64 = a63;
                            int i16 = i15 - 50;
                            int i17 = aia.graph.common.GraphMapsCanvasExt.QUEUE_Y;
                            aia.graph.common.TwinNode a65 = a64;
                            java.awt.Point a66 = new java.awt.Point(i16, i17);
                            aia.graph.common.TwinNode a67 = a65;
                            com.cim.AIA.MoveTween a68 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Moveable)a28, a61, a66, false, i);
                            aia.graph.common.TwinNode a69 = a67;
                            a6.add((com.cim.AIA.Tween)a68);
                            aia.graph.common.TwinNode a70 = a69;
                            int i18 = i3 + 1;
                            aia.graph.common.TwinNode a71 = a28;
                            aia.graph.common.TwinNode a72 = a70;
                            int i19 = i18;
                            while(true)
                            {
                                int i20 = a5.getSize();
                                aia.graph.common.TwinNode a73 = a72;
                                aia.graph.common.TwinNode a74 = a73;
                                if(i19 >= i20)
                                {
                                    a56 = a74;
                                    break;
                                }
                                else
                                {
                                    com.cim.AIA.Element a75 = a5.getElement(i19);
                                    aia.graph.common.TwinNode dummy3 = (aia.graph.common.TwinNode)a75;
                                    aia.graph.common.TwinNode a76 = (aia.graph.common.TwinNode)a75;
                                    java.awt.Point a77 = a76.getPosition();
                                    java.awt.Point a78 = a71.getPosition();
                                    com.cim.AIA.MoveTween a79 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Moveable)a76, a77, a78, false, i);
                                    a6.add((com.cim.AIA.Tween)a79);
                                    int i21 = i19 + 1;
                                    a71 = a76;
                                    a72 = a76;
                                    i19 = i21;
                                }
                            }
                            aia.graph.common.TwinNode a80 = a56;
                            aia.graph.common.TwinNode a81 = a56;
                            if(a56 == null)
                            {
                                a21 = a81;
                                break label5;
                            }
                            else
                            {
                                a57 = a80;
                            }
                            int i22 = this.isOverFlow()?1:0;
                            aia.graph.common.TwinNode a82 = a57;
                            aia.graph.common.TwinNode a83 = a82;
                            aia.graph.common.TwinNode a84 = a82;
                            if(i22 == 0)
                            {
                                a21 = a84;
                                break label5;
                            }
                            else
                            {
                                aia.graph.common.TwinNode a85 = a83;
                                a85.setIsVisible(true);
                                aia.graph.common.TwinNode a86 = a85;
                                aia.graph.common.TwinNode a87 = a86;
                                com.cim.AIA.InsertTween a88 = new com.cim.AIA.InsertTween((com.cim.AIA.Paintable)a3, (com.cim.AIA.Sizeable)a87, i);
                                aia.graph.common.TwinNode a89 = a87;
                                a6.add((com.cim.AIA.Tween)a88);
                                aia.graph.common.TwinNode a90 = a89;
                                a21 = a90;
                                break label5;
                            }
                        }
                        int i23 = a17.getType();
                        aia.graph.common.TwinNode a91 = a34;
                        aia.graph.common.TwinNode a92 = a91;
                        if(i23 != 3)
                        {
                            a21 = a92;
                            break label5;
                        }
                        int i24 = a17.getParam();
                        int i25 = this.isOverFlow()?1:0;
                        if(i25 == 0)
                        {
                            i8 = i24;
                        }
                        else
                        {
                            int i26 = this.VISIBLE_QUEUE_NODES;
                            i8 = i26;
                        }
                        com.cim.AIA.Element a93 = a5.getElement(i8);
                        aia.graph.common.TwinNode dummy4 = (aia.graph.common.TwinNode)a93;
                        aia.graph.common.TwinNode a94 = (aia.graph.common.TwinNode)a93;
                        java.awt.Point a95 = a94.getPosition();
                        java.awt.Point a96 = a28.getPosition();
                        com.cim.AIA.MoveTween a97 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Moveable)a28, a95, a96, false, i);
                        a6.add((com.cim.AIA.Tween)a97);
                        aia.graph.common.TwinNode a98 = a94;
                        int i27 = i3;
                        while(true)
                        {
                            if(i27 >= i8)
                            {
                                a21 = a98;
                                break;
                            }
                            else
                            {
                                com.cim.AIA.Element a99 = a5.getElement(i27);
                                aia.graph.common.TwinNode dummy5 = (aia.graph.common.TwinNode)a99;
                                aia.graph.common.TwinNode a100 = (aia.graph.common.TwinNode)a99;
                                int i28 = i27 + 1;
                                com.cim.AIA.Element a101 = a5.getElement(i28);
                                aia.graph.common.TwinNode dummy6 = (aia.graph.common.TwinNode)a101;
                                aia.graph.common.TwinNode a102 = (aia.graph.common.TwinNode)a101;
                                java.awt.Point a103 = a100.getPosition();
                                java.awt.Point a104 = a102.getPosition();
                                com.cim.AIA.MoveTween a105 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Moveable)a102, a103, a104, false, i);
                                a6.add((com.cim.AIA.Tween)a105);
                                int i29 = i27 + 1;
                                a98 = a102;
                                i27 = i29;
                            }
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
        java.util.Vector a = new java.util.Vector();
        this.queue_requests = a;
    }
    
    static
    {
        String s = localization.Messages.getString("MinSpanTreePrimGraphMaps.0");
        MinSpanTreePrimGraphMaps.HIGHLIGHT_COLOR = s;
        String s0 = localization.Messages.getString("MinSpanTreePrimGraphMaps.1");
        MinSpanTreePrimGraphMaps.NODE_COLOR = s0;
        String s1 = localization.Messages.getString("MinSpanTreePrimGraphMaps.3");
        MinSpanTreePrimGraphMaps.VISIT_MODE_LABEL = s1;
    }
}