// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MSTKGraphMaps.java

import aia.graph.common.*;
import com.cim.AIA.*;
import java.awt.Color;
import java.awt.Point;
import java.util.Vector;
import localization.Messages;

public class MSTKGraphMaps extends GraphMaps
    implements ColorSelectionListener, MethodSelectionListener
{

    public MSTKGraphMaps(AlgorithmThread algorithmThread, Object data)
    {
        super(algorithmThread, data);
        textColor = Color.black;
        highlightColor = Color.yellow;
        highlightLinkColor = Color.red;
        unvisitedNodeColor = new Color(123, 202, 123);
        backgroundColor = Color.white;
        flagUnvisitedColor = Color.lightGray;
        flagVisitedColor = Color.gray;
        queueNodeColorLeft = new Color(109, 234, 234);
        queueNodeColorRight = new Color(109, 234, 234);
        waitingNodeColor = new Color(109, 234, 234);
        connectedNodesColor = new Color(255, 221, 51);
        activeVerticesColor = new Color(255, 123, 123);
        commons = null;
        m_bIsRunning = false;
        edges_added = -10;
        visited_links = null;
        v1_marker = -10;
        v2_marker = -10;
        pqueue = null;
        current_edge = null;
        connected_nodes = null;
        questions = new Vector();
        queue_requests = new Vector();
        VISIBLE_QUEUE_NODES = 5;
        m_backMode = false;
        m_stored_data = null;
        initialise(data);
        ConfigurationManager cm = ConfigurationManager.getConfigurationManager();
        ColorSelection highlightSelection = new ColorSelection(highlightColor, HIGHLIGHT_COLOR);
        highlightSelection.addClass(this);
        cm.addColorSelection(highlightSelection);
        ColorSelection nodeSelection = new ColorSelection(unvisitedNodeColor, NODE_COLOR);
        nodeSelection.addClass(this);
        cm.addColorSelection(nodeSelection);
        cm.addColorSelectionListener(this);
    }

    public void initialiseMethods(String dataDir, MethodSelectable methodSelectable)
    {
        MethodSelection build = new MethodSelection(BUILD_MODE_LABEL, dataDir, Messages.getString("MSTKGraphMaps.0"), "4", getLogger(), getBreakPoint());
        methodSelectable.addMethodSelection(build, true);
        methodSelectable.addMethodSelectionListener(this);
    }

    public void processMethodSelectionEvent(MethodSelectionEvent methodselectionevent)
    {
    }

    public void setBackMode()
    {
        if(commons == null)
        {
            return;
        } else
        {
            m_backMode = true;
            m_stored_data = (int[])(int[])commons.getData().copy();
            return;
        }
    }

    public void reInitialise(Object data)
    {
        initialise(data);
    }

    protected void initialise(Object data)
    {
        visited_links = null;
        pqueue = null;
        questions = new Vector();
        queue_requests = new Vector();
        current_edge = null;
        connected_nodes = null;
        edges_added = -10;
        v1_marker = -10;
        v2_marker = -10;
        m_bIsRunning = false;
        if(commons == null)
            commons = new MSTKGraphAlgorithmCommon(this, data);
        else
            commons.setData(data);
        setStructureShowing(false);
        setMatrixShowing(false);
    }

    public void setNodePosition(int p_nodeIndex, int x, int y)
    {
        commons.setNodePosition(p_nodeIndex, x, y);
    }

    public int getV1Marker()
    {
        return v1_marker;
    }

    public int getV2Marker()
    {
        return v2_marker;
    }

    public void setOmniDirectional(boolean p_omniDir)
    {
        commons.setOmniDirectional(p_omniDir);
    }

    public void zoomIn()
    {
        commons.zoomIn();
    }

    public void zoomOut()
    {
        commons.zoomOut();
    }

    public void setInsertionMode(boolean p_insertion)
    {
        commons.setInsertionMode(p_insertion);
    }

    public boolean getInsertionMode()
    {
        return commons.getInsertionMode();
    }

    public void setKeyChangeMode(boolean p_keyChange)
    {
        commons.setKeyChangeMode(p_keyChange);
    }

    public boolean getKeyChangeMode()
    {
        return commons.getKeyChangeMode();
    }

    public void keyChange(int p_key_from, int p_key_to)
    {
        commons.keyChange(p_key_from, p_key_to);
    }

    public void startEditLink(Integer p_link_weight)
    {
        commons.startEditLink(p_link_weight);
    }

    public void endEditLink()
    {
        commons.endEditLink();
    }

    public void setStartNode(int p_key)
    {
        commons.setStartNode(p_key);
    }

    public void setEndNode(int p_key)
    {
        commons.setEndNode(p_key);
    }

    public void setLinkWeight(int p_link_weight)
    {
        commons.setLinkWeight(p_link_weight);
    }

    public boolean needPromptForLinkWeight()
    {
        return commons.needPromptForLinkWeight();
    }

    public int getCurrentLinkLinkWeight()
    {
        return commons.getCurrentLinkLinkWeight();
    }

    public boolean editLinkStarted()
    {
        return commons.editLinkStarted();
    }

    public int getEditLinkStep()
    {
        return commons.getEditLinkStep();
    }

    public int getStartNode()
    {
        return commons.getStartNode();
    }

    public int getEndNode()
    {
        return commons.getEndNode();
    }

    public void insertNewNode(int x, int y)
    {
        commons.insertNewNode(x, y);
    }

    public void deleteNode(int p_node)
    {
        commons.deleteNode(p_node);
    }

    public void setDeleteMode(boolean p_delete)
    {
        commons.setDeleteMode(p_delete);
    }

    public boolean getDeleteMode()
    {
        return commons.getDeleteMode();
    }

    public void editLink(GraphMapsNode p_node, int p_value)
    {
        commons.editLink(p_node, p_value);
    }

    public Object getData()
    {
        return commons.getData();
    }

    public Vector getNodes()
    {
        return commons.getNodes();
    }

    public void setNodeAppearance(GraphMapsNode node)
    {
        node.setBackgroundColor(unvisitedNodeColor);
        node.setTextColor(textColor);
        if(connected_nodes != null)
        {
            int i = 0;
            do
            {
                if(i >= connected_nodes.size())
                    break;
                Integer tempInt = (Integer)connected_nodes.elementAt(i);
                if(tempInt.intValue() == node.getIntValue())
                {
                    node.setBackgroundColor(connectedNodesColor);
                    break;
                }
                i++;
            } while(true);
        }
        if(node.getIntValue() == v1_marker || node.getIntValue() == v2_marker)
            node.setBackgroundColor(activeVerticesColor);
    }

    public void setLinkHighlighted(Link link)
    {
        if(visited_links != null && visited_links.findLink(link.getStart(), link.getEnd()) != null)
        {
            link.setHighlighted(true);
            link.setLinkColor(highlightLinkColor);
        }
    }

    public void setLinkAppearance(Link link)
    {
        if(m_bIsRunning)
            link.setLinkColor(Color.lightGray);
        Link link2 = new Link(new NodeInfo(v1_marker, 0, 0), new NodeInfo(v2_marker, 0, 0), commons.getOmniDirectional());
        if(link.isEqualTo(link2))
            link.setLinkColor(new Color(255, 192, 192));
    }

    public TwinNode getCurrentEdgeNode()
    {
        if(current_edge == null)
        {
            return null;
        } else
        {
            TwinNode node = new TwinNode(new Integer(current_edge.getFromKey()), new Integer(current_edge.getToKey()), 0);
            node.setBackgroundColorLeft(queueNodeColorLeft);
            node.setBackgroundColorRight(queueNodeColorRight);
            node.setTextColor(textColor);
            node.setHeight(20);
            node.setPriority(current_edge.getPriority());
            return node;
        }
    }

    public Vector getLinks()
    {
        return commons.getLinks();
    }

    public int getEdgesAdded()
    {
        return edges_added;
    }

    public Color getQueueNodeColorLeft()
    {
        return queueNodeColorLeft;
    }

    public Color getQueueNodeColorRight()
    {
        return queueNodeColorRight;
    }

    public Color getUnvisitedNodeColor()
    {
        return unvisitedNodeColor;
    }

    public Color getHighlightColor()
    {
        return highlightColor;
    }

    public Color getHighlightLinkColor()
    {
        return highlightLinkColor;
    }

    public Color getTextColor()
    {
        return textColor;
    }

    public GraphNode getAdjacentNode(int p_index)
    {
        return commons.getAdjacentNode(p_index);
    }

    public int getNumberOfNodes()
    {
        return commons.getNumberOfNodes();
    }

    public boolean getIsRunning()
    {
        return m_bIsRunning;
    }

    public Vector getAdjacencyMatrix()
    {
        return commons.getAdjacencyMatrix();
    }

    public void setMatrixNodeAppearance(GraphMapsNode node)
    {
        node.setBackgroundColor(unvisitedNodeColor);
        node.setTextColor(textColor);
    }

    public ElementArray getAdjacencyStructure()
    {
        return commons.getAdjacencyStructure();
    }

    public void setStructureNodeAppearance(GraphMapsNode node)
    {
        node.setBackgroundColor(unvisitedNodeColor);
        node.setTextColor(textColor);
    }

    public void setMatrixShowing(boolean p_matrixShowing)
    {
        commons.setMatrixShowing(p_matrixShowing);
    }

    public void setStructureShowing(boolean p_structureShowing)
    {
        commons.setStructureShowing(p_structureShowing);
    }

    public boolean getMatrixShowing()
    {
        return commons.getMatrixShowing();
    }

    public boolean getStructureShowing()
    {
        return commons.getStructureShowing();
    }

    public ElementArray getQueue()
    {
        if(pqueue == null)
            return null;
        ElementArray elementArray = new ElementArray(0, 0);
        elementArray.setColumGap(5);
        elementArray.setElementWidth(40);
        Vector queueItems = pqueue.getQueue();
        for(int i = 0; i < queueItems.size() && i != VISIBLE_QUEUE_NODES + 1; i++)
        {
            MSTKPriorityQueueMember queueMember = (MSTKPriorityQueueMember)queueItems.elementAt(i);
            TwinNode node = new TwinNode(new Integer(queueMember.getFromKey()), new Integer(queueMember.getToKey()), i);
            node.setBackgroundColorLeft(queueNodeColorLeft);
            node.setBackgroundColorRight(queueNodeColorRight);
            node.setTextColor(textColor);
            node.setHeight(20);
            node.setPriority(queueMember.getPriority());
            if(i == VISIBLE_QUEUE_NODES)
            {
                node.setWidth(0);
                node.setIsVisible(false);
            }
            elementArray.setValue(i, node);
        }

        return elementArray;
    }

    public boolean isOverFlow()
    {
        return pqueue != null && pqueue.size() > VISIBLE_QUEUE_NODES;
    }

    private boolean contains(Vector connectedSet, int key)
    {
        for(int i = 0; i < connectedSet.size(); i++)
        {
            Integer tempInt = (Integer)connectedSet.elementAt(i);
            if(tempInt.intValue() == key)
                return true;
        }

        return false;
    }

    private void findConnectedSet(Vector connectedSet, int u)
    {
        for(GraphNode adj_node = getAdjacentNode(u); adj_node != null && adj_node.getKey() != -1; adj_node = adj_node.getNext())
            if(visited_links != null && visited_links.findLink(new NodeInfo(u, 0, 0), new NodeInfo(adj_node.getKey(), 0, 0)) != null && !contains(connectedSet, adj_node.getKey()))
            {
                connectedSet.addElement(new Integer(adj_node.getKey()));
                findConnectedSet(connectedSet, adj_node.getKey());
            }

    }

    private boolean isInset(int u, int v)
    {
        Vector connectedSet = new Vector();
        findConnectedSet(connectedSet, u);
        for(int i = 0; i < connectedSet.size(); i++)
        {
            Integer tempInt = (Integer)connectedSet.elementAt(i);
            if(tempInt.intValue() == v)
                return true;
        }

        return false;
    }

    private void createConnectedNodesVector(int u, int v)
    {
        Vector connectedSet = new Vector();
        connected_nodes = new Vector();
        findConnectedSet(connectedSet, v);
        for(int i = 0; i < connectedSet.size(); i++)
        {
            Integer tempInt = (Integer)connectedSet.elementAt(i);
            connected_nodes.addElement(tempInt);
        }

        findConnectedSet(connectedSet, u);
        for(int i = 0; i < connectedSet.size(); i++)
        {
            Integer tempInt = (Integer)connectedSet.elementAt(i);
            connected_nodes.addElement(tempInt);
        }

    }

    private void sortedges()
    {
        int adj_matrix[][] = commons.getAdjacencyMatrixArray();
        for(int i = 0; i < commons.getNumberOfNodes(); i++)
        {
            for(GraphNode node = getAdjacentNode(i); node != null && node.getKey() != -1; node = node.getNext())
                pqueue.update(i, node.getKey(), adj_matrix[i][node.getKey()]);

        }

    }

    protected void run()
    {
        commons.algorithmStartRunInitialisations();
        if(m_backMode)
        {
            m_backMode = false;
            initialise(m_stored_data);
            m_stored_data = null;
        }
        visited_links = new LinkContainer();
        m_bIsRunning = true;
        setPosition("1");
        edges_added = 0;
        setPosition("2.1.1");
        pqueue = new MSTKPriorityQueue();
        sortedges();
        setPosition("2.2.1");
        setPosition("2.3");
        setPosition("4");
        build();
        setPosition("7");
        m_bIsRunning = false;
    }

    private void build()
    {
        while(!pqueue.isEmpty()) 
        {
            setPosition("5");
            MSTKPriorityQueueMember pqmember = pqueue.peek();
            addQueueRequest(2, pqmember.getFromKey(), pqmember.getToKey(), 0);
            setPosition("5.2");
            pqmember = pqueue.remove();
            current_edge = pqmember;
            createConnectedNodesVector(current_edge.getFromKey(), current_edge.getToKey());
            v1_marker = current_edge.getFromKey();
            v2_marker = current_edge.getToKey();
            setPosition("5.3");
            if(!isInset(pqmember.getToKey(), pqmember.getFromKey()) && !isInset(pqmember.getFromKey(), pqmember.getToKey()))
            {
                makeQuestion(pqmember.getFromKey(), pqmember.getToKey());
                askQuestionsWithoutSetPosition();
                visited_links.addLink(new NodeInfo(pqmember.getFromKey(), 0, 0), new NodeInfo(pqmember.getToKey(), 0, 0), commons.getOmniDirectional(), 0);
                edges_added++;
                setPosition("5.4");
                setPosition("5.6");
                if(edges_added == commons.getNumberOfNodes() - 1)
                {
                    setPosition("5.6.1");
                    setPosition("6");
                    return;
                }
            }
        }
        setPosition("5");
        setPosition("6");
    }

    public void processColorSelection(ColorSelection colorSelection)
    {
        String atribute = colorSelection.getAtributeName();
        if(atribute.equalsIgnoreCase(ColorSelection.BACKGROUND))
            backgroundColor = colorSelection.getColor();
        else
        if(atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR))
            textColor = colorSelection.getColor();
        else
        if(atribute.equalsIgnoreCase(HIGHLIGHT_COLOR))
            highlightColor = colorSelection.getColor();
        else
        if(atribute.equalsIgnoreCase(NODE_COLOR))
            unvisitedNodeColor = colorSelection.getColor();
    }

    private void addQueueRequest(int p_type, int p_from_key, int p_to_key, int p_param)
    {
        if(queue_requests == null)
            queue_requests = new Vector();
        QueueRequest queueRequest = new QueueRequest(p_type, p_from_key, p_to_key);
        if(p_type == 3)
            queueRequest.setParam(p_param);
        queue_requests.addElement(queueRequest);
    }

    public MultipleTween generateTweens(Paintable paintable, Object parameter, int numberOfSteps)
    {
        if(queue_requests == null || queue_requests.size() == 0)
            return null;
        ElementArray elementArray = (ElementArray)parameter;
        if(elementArray == null)
            return null;
        TwinNode node = null;
        TwinNode node2 = null;
        MultipleTween multipleTween = new MultipleTween(paintable);
        for(int i1 = 0; i1 < queue_requests.size(); i1++)
        {
            QueueRequest queueRequest = (QueueRequest)queue_requests.elementAt(i1);
            for(int i2 = 0; i2 < elementArray.getSize(); i2++)
            {
                node = (TwinNode)elementArray.getElement(i2);
                if(node.getLValue() != queueRequest.getFromKey() || node.getRValue() != queueRequest.getToKey())
                    continue;
                if(queueRequest.getType() == 1)
                {
                    multipleTween.add(new InsertTween(paintable, node, numberOfSteps));
                    for(int i3 = i2 + 1; i3 < elementArray.getSize(); i3++)
                    {
                        node2 = (TwinNode)elementArray.getElement(i3);
                        multipleTween.add(new MoveTween(null, node2, node.getPosition(), node2.getPosition(), false, numberOfSteps));
                        node = node2;
                    }

                    continue;
                }
                if(queueRequest.getType() == 2)
                {
                    multipleTween.add(new MoveTween(null, node, node.getPosition(), new Point(GraphMapsCanvasExt.QUEUE_X - 50, GraphMapsCanvasExt.QUEUE_Y), false, numberOfSteps));
                    for(int i3 = i2 + 1; i3 < elementArray.getSize(); i3++)
                    {
                        node2 = (TwinNode)elementArray.getElement(i3);
                        multipleTween.add(new MoveTween(null, node2, node2.getPosition(), node.getPosition(), false, numberOfSteps));
                        node = node2;
                    }

                    if(node2 != null && isOverFlow())
                    {
                        node2.setIsVisible(true);
                        multipleTween.add(new InsertTween(paintable, node2, numberOfSteps));
                    }
                    continue;
                }
                if(queueRequest.getType() != 3)
                    continue;
                node2 = (TwinNode)elementArray.getElement(queueRequest.getParam());
                multipleTween.add(new MoveTween(null, node, node2.getPosition(), node.getPosition(), false, numberOfSteps));
                for(int i3 = i2; i3 < queueRequest.getParam(); i3++)
                {
                    node = (TwinNode)elementArray.getElement(i3);
                    node2 = (TwinNode)elementArray.getElement(i3 + 1);
                    multipleTween.add(new MoveTween(null, node2, node.getPosition(), node2.getPosition(), false, numberOfSteps));
                }

            }

        }

        if(multipleTween.getNumberOfTweens() == 0)
            return null;
        else
            return multipleTween;
    }

    public String getClassName()
    {
        return "GraphMaps";
    }

    protected Vector generateQuestions()
    {
        return questions;
    }

    protected boolean hasQuestions()
    {
        return questions.size() > 0;
    }

    protected void makeQuestion(int p_from_node, int p_to_node)
    {
        questions.addElement(new NextNewLinkQuestion(p_from_node, p_to_node));
    }

    protected void removeAllQuestions()
    {
        questions = new Vector();
    }

    public void removeAllAnimationRequests()
    {
        queue_requests = new Vector();
    }

    protected Color textColor;
    protected Color highlightColor;
    protected Color highlightLinkColor;
    protected Color unvisitedNodeColor;
    protected Color backgroundColor;
    protected Color flagUnvisitedColor;
    protected Color flagVisitedColor;
    protected Color queueNodeColorLeft;
    protected Color queueNodeColorRight;
    protected Color waitingNodeColor;
    protected Color connectedNodesColor;
    protected Color activeVerticesColor;
    protected static final String HIGHLIGHT_COLOR = Messages.getString("MSTKGraphMaps.3");
    protected static final String NODE_COLOR = Messages.getString("MSTKGraphMaps.2");
    protected GraphAlgorithmCommon commons;
    protected boolean m_bIsRunning;
    private int edges_added;
    private LinkContainer visited_links;
    private int v1_marker;
    private int v2_marker;
    private MSTKPriorityQueue pqueue;
    private MSTKPriorityQueueMember current_edge;
    private Vector connected_nodes;
    protected Vector questions;
    protected Vector queue_requests;
    public static final int UNMARKED = -10;
    public static final String BUILD_MODE_LABEL = Messages.getString("MSTKGraphMaps.1");
    protected int VISIBLE_QUEUE_NODES;
    protected boolean m_backMode;
    protected int m_stored_data[];

}
