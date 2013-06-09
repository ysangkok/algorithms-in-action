// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MinSpanTreePrimGraphMaps.java

import aia.graph.common.*;
import com.cim.AIA.*;
import java.awt.Color;
import java.awt.Point;
import java.util.Vector;
import localization.Messages;

public class MinSpanTreePrimGraphMaps extends GraphMaps
    implements ColorSelectionListener, MethodSelectionListener
{

    public MinSpanTreePrimGraphMaps(AlgorithmThread algorithmThread, Object data)
    {
        super(algorithmThread, data);
        textColor = Color.black;
        highlightColor = Color.yellow;
        highlightLinkColor = Color.red;
        unvisitedNodeColor = new Color(123, 202, 123);
        adjacentNodesColor = new Color(0, 172, 0);
        adjacentNodeColor = new Color(174, 255, 174);
        backgroundColor = Color.white;
        flagUnvisitedColor = Color.lightGray;
        flagVisitedColor = Color.gray;
        visitedNodeColor = Color.gray;
        queueNodeColorLeft = new Color(109, 234, 234);
        queueNodeColorRight = new Color(84, 183, 183);
        waitingNodeColor = new Color(109, 234, 234);
        commons = null;
        m_bIsRunning = false;
        visit_sequence = null;
        visit_counter = -10;
        visited_links = null;
        adj_node = null;
        k_marker = -10;
        pqueue = null;
        current_node = null;
        adjacent_nodes = null;
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
        MethodSelection visit = new MethodSelection(VISIT_MODE_LABEL, dataDir, Messages.getString("MinSpanTreePrimGraphMaps.2"), "4", getLogger(), getBreakPoint());
        methodSelectable.addMethodSelection(visit, true);
        methodSelectable.addMethodSelectionListener(this);
    }

    public void processMethodSelectionEvent(MethodSelectionEvent methodselectionevent)
    {
    }

    public void reInitialise(Object data)
    {
        initialise(data);
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

    protected void initialise(Object data)
    {
        visit_sequence = null;
        visit_counter = -10;
        visited_links = null;
        current_node = null;
        adj_node = null;
        k_marker = -10;
        pqueue = null;
        questions = new Vector();
        queue_requests = new Vector();
        adjacent_nodes = null;
        m_bIsRunning = false;
        if(commons == null)
            commons = new GraphAlgorithmCommon(this, data);
        else
            commons.setData(data);
        setStructureShowing(false);
        setMatrixShowing(false);
    }

    public void setNodePosition(int p_nodeIndex, int x, int y)
    {
        commons.setNodePosition(p_nodeIndex, x, y);
    }

    public int getKMarker()
    {
        return k_marker;
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
        if(adjacent_nodes != null)
        {
            int i = 0;
            do
            {
                if(i >= adjacent_nodes.size())
                    break;
                Integer adjacent_node = (Integer)adjacent_nodes.elementAt(i);
                if(node.getIntValue() == adjacent_node.intValue())
                {
                    node.setBackgroundColor(adjacentNodesColor);
                    break;
                }
                i++;
            } while(true);
        }
        if(adj_node != null && adj_node.getTo() == node.getIntValue())
            node.setBackgroundColor(adjacentNodeColor);
        if(visit_sequence != null)
            if(visit_sequence[node.getIntValue()] > 0)
                node.setBackgroundColor(visitedNodeColor);
            else
            if(visit_sequence[node.getIntValue()] < 0)
                node.setBackgroundColor(waitingNodeColor);
        if(node.getIntValue() == k_marker)
            node.setBackgroundColor(highlightColor);
    }

    private void createAdjacentNodeVector(GraphNode p_node)
    {
        adjacent_nodes = new Vector();
        for(; p_node != null && p_node.getKey() != -1; p_node = p_node.getNext())
            adjacent_nodes.addElement(new Integer(p_node.getKey()));

    }

    public void setLinkHighlighted(Link link)
    {
        if(visited_links != null && visited_links.findLink(link.getStart(), link.getEnd()) != null)
        {
            link.setHighlighted(true);
            link.setLinkColor(highlightLinkColor);
        }
    }

    public void setLinkAppearance(Link link1)
    {
    }

    public Vector getLinks()
    {
        return commons.getLinks();
    }

    public TwinNode getCurrentPQNode()
    {
        if(current_node == null)
        {
            return null;
        } else
        {
            TwinNode node = new TwinNode(new Integer(current_node.getKey()), new Integer(current_node.getParent()), 0);
            node.setBackgroundColorLeft(queueNodeColorLeft);
            node.setBackgroundColorRight(queueNodeColorRight);
            node.setTextColor(textColor);
            node.setHeight(20);
            node.setPriority(current_node.getPriority());
            return node;
        }
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

    public Color getAdjacentNodeColor()
    {
        return adjacentNodeColor;
    }

    public Color getAdjacentNodesColor()
    {
        return adjacentNodesColor;
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

    public String getVisitStatus(int p_key)
    {
        if(visit_sequence == null || p_key < 0 || p_key >= visit_sequence.length)
            return "";
        if(visit_sequence[p_key] > 0)
            return "T";
        if(visit_sequence[p_key] < 0)
            return "P";
        else
            return "F";
    }

    public int getSeqNumber()
    {
        return visit_counter;
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
        if(adj_node != null)
            if(node.getFrom() == adj_node.getFrom() && node.getTo() == adj_node.getTo())
                node.setBackgroundColor(adjacentNodeColor);
            else
            if(node.getFrom() == adj_node.getFrom())
                node.setBackgroundColor(adjacentNodesColor);
    }

    public ElementArray getAdjacencyStructure()
    {
        return commons.getAdjacencyStructure();
    }

    public void setStructureNodeAppearance(GraphMapsNode node)
    {
        node.setBackgroundColor(unvisitedNodeColor);
        node.setTextColor(textColor);
        if(adj_node != null && node.getIntValue() == adj_node.getFrom())
            node.setBackgroundColor(highlightColor);
    }

    public ElementArray getSeqArray()
    {
        GraphMapsNode node = null;
        if(visit_sequence == null)
            return null;
        ElementArray elementArray = new ElementArray(0, 0);
        elementArray.setColumGap(0);
        elementArray.setElementWidth(20);
        for(int i = 0; i < visit_sequence.length; i++)
        {
            node = new GraphMapsNode(new Integer(visit_sequence[i]), i, 4);
            node.setBackgroundColor(flagUnvisitedColor);
            if(adj_node != null && adj_node.getTo() == i)
                node.setBackgroundColor(adjacentNodeColor);
            if(visit_sequence[i] > 0)
                node.setBackgroundColor(flagVisitedColor);
            else
            if(visit_sequence[i] < 0)
            {
                node = new GraphMapsNode(new Integer(0), i, 4);
                node.setBackgroundColor(waitingNodeColor);
            }
            node.setTextColor(textColor);
            if(i == k_marker)
                node.setBackgroundColor(highlightColor);
            node.setHeight(20);
            elementArray.setValue(i, node);
        }

        return elementArray;
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
            MinSpanTreePrimPriorityQueueMember queueMember = (MinSpanTreePrimPriorityQueueMember)queueItems.elementAt(i);
            TwinNode node = new TwinNode(new Integer(queueMember.getKey()), new Integer(queueMember.getParent()), i);
            node.setBackgroundColorLeft(queueNodeColorLeft);
            node.setBackgroundColorRight(queueNodeColorRight);
            node.setTextColor(textColor);
            node.setHeight(20);
            node.setPriority(queueMember.getPriority());
            if(i == VISIBLE_QUEUE_NODES)
                node.setIsVisible(false);
            elementArray.setValue(i, node);
        }

        return elementArray;
    }

    public boolean isOverFlow()
    {
        return pqueue != null && pqueue.size() > VISIBLE_QUEUE_NODES;
    }

    public GraphMapsNode getAdjNode()
    {
        return adj_node;
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
        visit_sequence = new int[commons.getNumberOfNodes()];
        for(int k = 0; k < visit_sequence.length; k++)
            visit_sequence[k] = -1;

        setPosition("2.1.1");
        for(int k = 0; k < visit_sequence.length; k++)
        {
            k_marker = k;
            setPosition("2.1.2");
            visit_sequence[k] = 0;
            setPosition("2.1.2.1");
        }

        setPosition("2.1.2");
        setPosition("2.1.3");
        visit_counter = 0;
        setPosition("2.2.1");
        pqueue = new MinSpanTreePrimPriorityQueue();
        setPosition("2.3.1");
        setPosition("2.4");
        for(int k = 0; k < commons.getNumberOfNodes(); k++)
        {
            k_marker = k;
            setPosition("3.1.1");
            setPosition("3.3");
            if(visit_sequence[k] == 0)
            {
                setPosition("4");
                visit(k);
            }
        }

        setPosition("3.1.1");
        setPosition("4.1");
        setPosition("9");
        m_bIsRunning = false;
    }

    protected void visit(int k)
    {
        GraphNode adj_nodes[] = commons.getAdjacencyStructureArray();
        int adj_matrix[][] = commons.getAdjacencyMatrixArray();
        k_marker = k;
        pqueue.update(k, -1, 0);
        addQueueRequest(1, k, 0);
        setPosition("5");
        for(; !pqueue.isEmpty(); setPosition("7.4.2"))
        {
            setPosition("7");
            MinSpanTreePrimPriorityQueueMember pqmember = pqueue.peek();
            k = pqmember.getKey();
            k_marker = k;
            addQueueRequest(2, k, 0);
            setPosition("7.2");
            pqmember = pqueue.remove();
            current_node = pqmember;
            makeQuestion(k);
            askQuestionsWithoutSetPosition();
            setPosition("7.3.1");
            if(visit_sequence[k] == 0 || visit_sequence[k] == -1)
            {
                if(pqmember.getParent() != -1)
                    visited_links.addLink(new NodeInfo(pqmember.getParent(), 0, 0), new NodeInfo(k, 0, 0), commons.getOmniDirectional(), 0);
                setPosition("7.3.1.1");
                visit_sequence[k] = ++visit_counter;
                setPosition("7.3.1.3");
            }
            GraphNode adjNode = adj_nodes[k];
            adj_node = new GraphMapsNode(new Integer(0), 0, 0);
            adj_node.setFrom(k);
            adj_node.setTo(adjNode.getKey());
            createAdjacentNodeVector(adjNode);
            setPosition("7.4.1");
            for(; adjNode.getKey() != -1; setPosition("7.5.7"))
            {
                setPosition("7.4.2");
                setPosition("7.5.1");
                if(visit_sequence[adjNode.getKey()] == 0 || visit_sequence[adjNode.getKey()] == -1)
                {
                    setPosition("7.5.3");
                    int nUpdateReturnValue = pqueue.update(adjNode.getKey(), k, adj_matrix[k][adjNode.getKey()]);
                    if(nUpdateReturnValue != -1)
                    {
                        visit_sequence[adjNode.getKey()] = -1;
                        if(nUpdateReturnValue == -2)
                            addQueueRequest(1, adjNode.getKey(), 0);
                        else
                            addQueueRequest(3, adjNode.getKey(), nUpdateReturnValue);
                        setPosition("7.5.4");
                        setPosition("7.5.6");
                    }
                }
                adjNode = adjNode.getNext();
                adj_node = new GraphMapsNode(new Integer(0), 0, 0);
                adj_node.setFrom(k);
                adj_node.setTo(adjNode.getKey());
                createAdjacentNodeVector(adjNode);
            }

        }

        setPosition("7");
        setPosition("8");
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

    private void addQueueRequest(int p_type, int p_key, int p_param)
    {
        if(queue_requests == null)
            queue_requests = new Vector();
        QueueRequest queueRequest = new QueueRequest(p_type, p_key);
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
                if(node.getLValue() != queueRequest.getFromKey())
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
                int tempParam = queueRequest.getParam();
                if(isOverFlow())
                    tempParam = VISIBLE_QUEUE_NODES;
                node2 = (TwinNode)elementArray.getElement(tempParam);
                multipleTween.add(new MoveTween(null, node, node2.getPosition(), node.getPosition(), false, numberOfSteps));
                for(int i3 = i2; i3 < tempParam; i3++)
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
        Vector questionsToAsk = new Vector();
        for(int i = 0; i < questions.size(); i++)
        {
            Integer tempInt = (Integer)questions.elementAt(i);
            questionsToAsk.addElement(new NextVisitQuestion(tempInt.intValue()));
        }

        return questionsToAsk;
    }

    protected boolean hasQuestions()
    {
        return questions.size() > 0;
    }

    protected void makeQuestion(int nodeKey)
    {
        questions.addElement(new Integer(nodeKey));
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
    protected Color adjacentNodesColor;
    protected Color adjacentNodeColor;
    protected Color backgroundColor;
    protected Color flagUnvisitedColor;
    protected Color flagVisitedColor;
    protected Color visitedNodeColor;
    protected Color queueNodeColorLeft;
    protected Color queueNodeColorRight;
    protected Color waitingNodeColor;
    protected static final String HIGHLIGHT_COLOR = Messages.getString("MinSpanTreePrimGraphMaps.0");
    protected static final String NODE_COLOR = Messages.getString("MinSpanTreePrimGraphMaps.1");
    protected GraphAlgorithmCommon commons;
    protected boolean m_bIsRunning;
    private int visit_sequence[];
    private int visit_counter;
    private LinkContainer visited_links;
    private GraphMapsNode adj_node;
    private int k_marker;
    private MinSpanTreePrimPriorityQueue pqueue;
    private MinSpanTreePrimPriorityQueueMember current_node;
    protected Vector adjacent_nodes;
    protected Vector questions;
    protected Vector queue_requests;
    protected int VISIBLE_QUEUE_NODES;
    public static final int UNMARKED = -10;
    public static final String VISIT_MODE_LABEL = Messages.getString("MinSpanTreePrimGraphMaps.3");
    protected boolean m_backMode;
    protected int m_stored_data[];

}
