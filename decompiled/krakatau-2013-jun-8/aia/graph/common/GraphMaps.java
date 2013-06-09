package aia.graph.common;

abstract public class GraphMaps extends com.cim.AIA.Algorithm {
    final protected static String HIGHLIGHT_COLOR;
    final protected static String NODE_COLOR;
    final public static int UNMARKED = -10;
    final public static String VISIT_MODE_LABEL;
    protected java.awt.Color textColor;
    protected java.awt.Color highlightColor;
    protected java.awt.Color highlightLinkColor;
    protected java.awt.Color unvisitedNodeColor;
    protected java.awt.Color adjacentNodesColor;
    protected java.awt.Color adjacentNodeColor;
    protected java.awt.Color backgroundColor;
    protected java.awt.Color visitedNodeColor;
    protected java.awt.Color flagUnvisitedColor;
    protected java.awt.Color flagVisitedColor;
    protected java.awt.Color queueNodeColor;
    protected java.awt.Color queueNodeTextColor;
    protected java.awt.Color waitingNodeColor;
    protected aia.graph.common.GraphAlgorithmCommon commons;
    protected boolean m_bIsRunning;
    protected int[] visit_sequence;
    protected int visit_counter;
    protected aia.graph.common.LinkContainer visited_links;
    private aia.graph.common.GraphMapsNode adj_node;
    protected int k_marker;
    protected aia.graph.common.Queue queue;
    private aia.graph.common.QueueMember current_queue_node;
    private java.util.Vector adjacent_nodes;
    protected java.util.Vector questions;
    protected java.util.Vector queue_requests;
    final protected int VISIBLE_QUEUE_NODES;
    protected boolean m_backMode;
    protected int[] m_stored_data;
    
    public GraphMaps(com.cim.AIA.AlgorithmThread a, Object a0)
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
        java.awt.Color a8 = java.awt.Color.gray;
        this.visitedNodeColor = a8;
        java.awt.Color a9 = java.awt.Color.lightGray;
        this.flagUnvisitedColor = a9;
        java.awt.Color a10 = java.awt.Color.gray;
        this.flagVisitedColor = a10;
        java.awt.Color a11 = new java.awt.Color(109, 234, 234);
        this.queueNodeColor = a11;
        java.awt.Color a12 = java.awt.Color.black;
        this.queueNodeTextColor = a12;
        java.awt.Color a13 = new java.awt.Color(109, 234, 234);
        this.waitingNodeColor = a13;
        this.m_bIsRunning = false;
        this.visit_sequence = null;
        this.visit_counter = -10;
        this.visited_links = null;
        this.adj_node = null;
        this.k_marker = -10;
        this.queue = null;
        this.current_queue_node = null;
        this.adjacent_nodes = null;
        java.util.Vector a14 = new java.util.Vector();
        this.questions = a14;
        java.util.Vector a15 = new java.util.Vector();
        this.queue_requests = a15;
        this.VISIBLE_QUEUE_NODES = 10;
        this.m_backMode = false;
        this.m_stored_data = null;
    }
    
    private void addQueueRequest(boolean b, int i)
    {
        java.util.Vector a = this.queue_requests;
        int i0 = b?1:0;
        if(a == null)
        {
            java.util.Vector a0 = new java.util.Vector();
            this.queue_requests = a0;
        }
        java.util.Vector a1 = this.queue_requests;
        aia.graph.common.QueueRequest a2 = new aia.graph.common.QueueRequest(i0 != 0, i);
        a1.addElement((Object)a2);
    }
    
    public void createAdjacentNodesVector(aia.graph.common.GraphNode a)
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
    
    public void deleteNode(int i)
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        a.deleteNode(i);
    }
    
    public void editLink(aia.graph.common.GraphMapsNode a, int i)
    {
        aia.graph.common.GraphAlgorithmCommon a0 = this.commons;
        a0.editLink(a, i);
    }
    
    public boolean editLinkStarted()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = a.editLinkStarted()?1:0;
        return i != 0;
    }
    
    public void endEditLink()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        a.endEditLink();
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
    
    public java.util.Vector getAdjacencyMatrix()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        java.util.Vector a0 = a.getAdjacencyMatrix();
        return a0;
    }
    
    public com.cim.AIA.ElementArray getAdjacencyStructure()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        com.cim.AIA.ElementArray a0 = a.getAdjacencyStructure();
        return a0;
    }
    
    public aia.graph.common.GraphNode getAdjacentNode(int i)
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        aia.graph.common.GraphNode a0 = a.getAdjacentNode(i);
        return a0;
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
    
    public aia.graph.common.GraphMapsNode getAdjNode()
    {
        aia.graph.common.GraphMapsNode a = this.adj_node;
        return a;
    }
    
    public String getClassName()
    {
        return "GraphMaps";
    }
    
    public int getCurrentLinkLinkWeight()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = a.getCurrentLinkLinkWeight();
        return i;
    }
    
    public aia.graph.common.GraphMapsNode getCurrentQueueNode()
    {
        aia.graph.common.GraphMapsNode a = null;
        aia.graph.common.QueueMember a0 = this.current_queue_node;
        if(a0 != null)
        {
            aia.graph.common.QueueMember a1 = this.current_queue_node;
            int i = a1.getKey();
            Integer a2 = new Integer(i);
            aia.graph.common.GraphMapsNode a3 = new aia.graph.common.GraphMapsNode((Object)a2, 0, 5);
            java.awt.Color a4 = this.queueNodeColor;
            a3.setBackgroundColor(a4);
            java.awt.Color a5 = this.queueNodeTextColor;
            a3.setTextColor(a5);
            a3.setHeight(20);
            a = a3;
        }
        else
        {
            a = null;
        }
        return a;
    }
    
    public Object getData()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        com.cim.AIA.IntArray a0 = a.getData();
        return a0;
    }
    
    public boolean getDeleteMode()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = a.getDeleteMode()?1:0;
        return i != 0;
    }
    
    public int getEditLinkStep()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = a.getEditLinkStep();
        return i;
    }
    
    public int getEndNode()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = a.getEndNode();
        return i;
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
    
    public boolean getInsertionMode()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = a.getInsertionMode()?1:0;
        return i != 0;
    }
    
    public boolean getIsRunning()
    {
        int i = this.m_bIsRunning?1:0;
        return i != 0;
    }
    
    public boolean getKeyChangeMode()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = a.getKeyChangeMode()?1:0;
        return i != 0;
    }
    
    public int getKMarker()
    {
        int i = this.k_marker;
        return i;
    }
    
    public java.util.Vector getLinks()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        java.util.Vector a0 = a.getLinks();
        return a0;
    }
    
    public boolean getMatrixShowing()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = a.getMatrixShowing()?1:0;
        return i != 0;
    }
    
    public java.util.Vector getNodes()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        java.util.Vector a0 = a.getNodes();
        return a0;
    }
    
    public int getNumberOfNodes()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = a.getNumberOfNodes();
        return i;
    }
    
    public com.cim.AIA.ElementArray getQueue()
    {
        com.cim.AIA.ElementArray a = null;
        aia.graph.common.Queue a0 = this.queue;
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
            a1.setElementWidth(20);
            aia.graph.common.Queue a2 = this.queue;
            java.util.Vector a3 = a2.getQueue();
            int i = 0;
            while(true)
            {
                int i0 = 0;
                int i1 = a3.size();
                if(i >= i1)
                {
                    break;
                }
                label2: {
                    if(i != 11)
                    {
                        break label2;
                    }
                    break;
                }
                Object a4 = a3.elementAt(i);
                aia.graph.common.QueueMember dummy = (aia.graph.common.QueueMember)a4;
                aia.graph.common.QueueMember a5 = (aia.graph.common.QueueMember)a4;
                int i2 = a5.getKey();
                Integer a6 = new Integer(i2);
                aia.graph.common.GraphMapsNode a7 = new aia.graph.common.GraphMapsNode((Object)a6, i, 5);
                java.awt.Color a8 = this.queueNodeColor;
                a7.setBackgroundColor(a8);
                java.awt.Color a9 = this.queueNodeTextColor;
                a7.setTextColor(a9);
                a7.setHeight(20);
                if(i != 10)
                {
                    i0 = i;
                }
                else
                {
                    a7.setIsVisible(false);
                    i0 = 10;
                }
                a1.setValue(i0, (com.cim.AIA.Element)a7);
                int i3 = i0 + 1;
                i = i3;
            }
            a = a1;
        }
        return a;
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
    
    public int getSeqNumber()
    {
        int i = this.visit_counter;
        return i;
    }
    
    public int getStartNode()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = a.getStartNode();
        return i;
    }
    
    public boolean getStructureShowing()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = a.getStructureShowing()?1:0;
        return i != 0;
    }
    
    public java.awt.Color getTextColor()
    {
        java.awt.Color a = this.textColor;
        return a;
    }
    
    public java.awt.Color getUnvisitedNodeColor()
    {
        java.awt.Color a = this.unvisitedNodeColor;
        return a;
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
    
    protected boolean hasQuestions()
    {
        java.util.Vector a = this.questions;
        int i = a.size();
        int i0 = i <= 0?0:1;
        return i0 != 0;
    }
    
    protected void initialise(Object a)
    {
        this.visit_sequence = null;
        this.visit_counter = -10;
        this.visited_links = null;
        this.adj_node = null;
        this.k_marker = -10;
        this.queue = null;
        java.util.Vector a0 = new java.util.Vector();
        this.questions = a0;
        java.util.Vector a1 = new java.util.Vector();
        this.queue_requests = a1;
        this.m_bIsRunning = false;
        this.adjacent_nodes = null;
        this.current_queue_node = null;
        aia.graph.common.GraphAlgorithmCommon a2 = this.commons;
        if(a2 != null)
        {
            aia.graph.common.GraphAlgorithmCommon a3 = this.commons;
            a3.setData(a);
        }
        else
        {
            aia.graph.common.GraphAlgorithmCommon a4 = new aia.graph.common.GraphAlgorithmCommon(this, a);
            this.commons = a4;
        }
        this.setStructureShowing(false);
        this.setMatrixShowing(false);
    }
    
    public void insertNewNode(int i, int i0)
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        a.insertNewNode(i, i0);
    }
    
    public boolean isQueueOverFlow()
    {
        int i = 0;
        aia.graph.common.Queue a = this.queue;
        label2: {
            label1: {
                label0: {
                    if(a == null)
                    {
                        break label0;
                    }
                    aia.graph.common.Queue a0 = this.queue;
                    int i0 = a0.getCount();
                    if(i0 > 10)
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
    
    public void keyChange(int i, int i0)
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        a.keyChange(i, i0);
    }
    
    protected void makeQuestion(int i)
    {
        java.util.Vector a = this.questions;
        Integer a0 = new Integer(i);
        a.addElement((Object)a0);
    }
    
    public boolean needPromptForLinkWeight()
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = a.needPromptForLinkWeight()?1:0;
        return i != 0;
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
            String s2 = aia.graph.common.GraphMaps.HIGHLIGHT_COLOR;
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
            String s3 = aia.graph.common.GraphMaps.NODE_COLOR;
            int i2 = s.equalsIgnoreCase(s3)?1:0;
            if(i2 != 0)
            {
                java.awt.Color a3 = a.getColor();
                this.unvisitedNodeColor = a3;
            }
        }
    }
    
    public void processMethodSelectionEvent(com.cim.AIA.MethodSelectionEvent a)
    {
    }
    
    public void reInitialise(Object a)
    {
        this.initialise(a);
    }
    
    public void removeAllAnimationRequests()
    {
        java.util.Vector a = new java.util.Vector();
        this.queue_requests = a;
    }
    
    protected void removeAllQuestions()
    {
        java.util.Vector a = new java.util.Vector();
        this.questions = a;
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
    
    public void setDeleteMode(boolean b)
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = b?1:0;
        a.setDeleteMode(i != 0);
    }
    
    public void setEndNode(int i)
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        a.setEndNode(i);
    }
    
    public void setInsertionMode(boolean b)
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = b?1:0;
        a.setInsertionMode(i != 0);
    }
    
    public void setKeyChangeMode(boolean b)
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = b?1:0;
        a.setKeyChangeMode(i != 0);
    }
    
    public void setLinkAppearance(aia.graph.common.Link a)
    {
        a.setUseWeight(false);
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
    
    public void setLinkWeight(int i)
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        a.setLinkWeight(i);
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
    
    public void setMatrixShowing(boolean b)
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = b?1:0;
        a.setMatrixShowing(i != 0);
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
    
    public void setNodePosition(int i, int i0, int i1)
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        a.setNodePosition(i, i0, i1);
    }
    
    public void setOmniDirectional(boolean b)
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = b?1:0;
        a.setOmniDirectional(i != 0);
    }
    
    public void setStartNode(int i)
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        a.setStartNode(i);
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
    
    public void setStructureShowing(boolean b)
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        int i = b?1:0;
        a.setStructureShowing(i != 0);
    }
    
    public void startEditLink(Integer a)
    {
        aia.graph.common.GraphAlgorithmCommon a0 = this.commons;
        a0.startEditLink(a);
    }
    
    protected void visit(int i)
    {
        aia.graph.common.GraphAlgorithmCommon a = this.commons;
        aia.graph.common.GraphNode[] a0 = a.getAdjacencyStructureArray();
        this.k_marker = i;
        aia.graph.common.Queue a1 = this.queue;
        aia.graph.common.QueueMember a2 = new aia.graph.common.QueueMember(i, -1);
        a1.put((Object)a2);
        this.addQueueRequest(true, i);
        this.setPosition("5");
        label0: while(true)
        {
            aia.graph.common.Queue a3 = this.queue;
            int i0 = a3.isEmpty()?1:0;
            if(i0 != 0)
            {
                this.setPosition("6");
                this.setPosition("7");
                return;
            }
            this.setPosition("6");
            aia.graph.common.Queue a4 = this.queue;
            Object a5 = a4.peek();
            aia.graph.common.QueueMember dummy = (aia.graph.common.QueueMember)a5;
            aia.graph.common.QueueMember a6 = (aia.graph.common.QueueMember)a5;
            int i1 = a6.getKey();
            this.k_marker = i1;
            this.addQueueRequest(false, i1);
            this.setPosition("6.1");
            aia.graph.common.Queue a7 = this.queue;
            Object a8 = a7.get();
            aia.graph.common.QueueMember dummy0 = (aia.graph.common.QueueMember)a8;
            aia.graph.common.QueueMember a9 = (aia.graph.common.QueueMember)a8;
            aia.graph.common.QueueMember a10 = a9.copy();
            this.current_queue_node = a10;
            this.makeQuestion(i1);
            this.askQuestionsWithoutSetPosition();
            int[] a11 = this.visit_sequence;
            int i2 = this.visit_counter;
            int i3 = i2 + 1;
            this.visit_counter = i3;
            a11[i1] = i3;
            int i4 = a9.getParent();
            if(i4 != -1)
            {
                aia.graph.common.LinkContainer a12 = this.visited_links;
                int i5 = a9.getParent();
                aia.graph.common.NodeInfo a13 = new aia.graph.common.NodeInfo(i5, 0, 0);
                aia.graph.common.NodeInfo a14 = new aia.graph.common.NodeInfo(i1, 0, 0);
                aia.graph.common.GraphAlgorithmCommon a15 = this.commons;
                int i6 = a15.getOmniDirectional()?1:0;
                aia.graph.common.Link a16 = a12.addLink(a13, a14, i6 != 0, 0);
            }
            this.setPosition("6.2.1");
            aia.graph.common.GraphNode a17 = a0[i1];
            Integer a18 = new Integer(0);
            aia.graph.common.GraphMapsNode a19 = new aia.graph.common.GraphMapsNode((Object)a18, 0, 0);
            this.adj_node = a19;
            aia.graph.common.GraphMapsNode a20 = this.adj_node;
            a20.setFrom(i1);
            aia.graph.common.GraphMapsNode a21 = this.adj_node;
            int i7 = a17.getKey();
            a21.setTo(i7);
            this.createAdjacentNodesVector(a17);
            this.setPosition("6.3.1");
            aia.graph.common.GraphNode a22 = a17;
            while(true)
            {
                int i8 = a22.getKey();
                if(i8 == -1)
                {
                    this.setPosition("6.3.2");
                    continue label0;
                }
                this.setPosition("6.3.2");
                this.setPosition("6.4.1");
                int[] a23 = this.visit_sequence;
                int i9 = a22.getKey();
                int i10 = a23[i9];
                if(i10 == 0)
                {
                    aia.graph.common.Queue a24 = this.queue;
                    int i11 = a22.getKey();
                    aia.graph.common.QueueMember a25 = new aia.graph.common.QueueMember(i11, i1);
                    a24.put((Object)a25);
                    int i12 = a22.getKey();
                    this.addQueueRequest(true, i12);
                    this.setPosition("6.5.1");
                    int[] a26 = this.visit_sequence;
                    int i13 = a22.getKey();
                    a26[i13] = -1;
                    this.setPosition("6.5.2.1");
                }
                aia.graph.common.GraphNode a27 = a22.getNext();
                Integer a28 = new Integer(0);
                aia.graph.common.GraphMapsNode a29 = new aia.graph.common.GraphMapsNode((Object)a28, 0, 0);
                this.adj_node = a29;
                aia.graph.common.GraphMapsNode a30 = this.adj_node;
                a30.setFrom(i1);
                aia.graph.common.GraphMapsNode a31 = this.adj_node;
                int i14 = a27.getKey();
                a31.setTo(i14);
                this.createAdjacentNodesVector(a27);
                this.setPosition("6.6");
                a22 = a27;
            }
        }
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
    
    static
    {
        String s = localization.Messages.getString("GraphMaps.0");
        aia.graph.common.GraphMaps.HIGHLIGHT_COLOR = s;
        String s0 = localization.Messages.getString("GraphMaps.1");
        aia.graph.common.GraphMaps.NODE_COLOR = s0;
        String s1 = localization.Messages.getString("GraphMaps.2");
        aia.graph.common.GraphMaps.VISIT_MODE_LABEL = s1;
    }
}