// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DFSGraphMaps.java

import aia.graph.common.*;
import com.cim.AIA.*;
import java.awt.Color;
import java.util.Vector;
import localization.Messages;

public class DFSGraphMaps extends GraphMaps
    implements ColorSelectionListener, MethodSelectionListener
{

    public DFSGraphMaps(AlgorithmThread algorithmThread, Object data)
    {
        super(algorithmThread, data);
        textColor = Color.black;
        highlightColor = Color.yellow;
        highlightLinkColor = Color.red;
        unvisitedNodeColor = new Color(123, 202, 123);
        adjacentNodesColor = new Color(0, 172, 0);
        adjacentNodeColor = new Color(174, 255, 174);
        visitedNodeColor = Color.gray;
        backgroundColor = Color.white;
        flagUnvisitedColor = Color.lightGray;
        flagVisitedColor = Color.gray;
        waitingNodeColor = Color.blue;
        m_bIsRunning = false;
        visit_sequence = null;
        visit_counter = -10;
        visited_links = null;
        adj_node = null;
        k_marker = -10;
        adjacent_nodes = null;
        questions = new Vector();
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
        MethodSelection visit = new MethodSelection("Visit", dataDir, Messages.getString("DFSGraphMaps.1"), "4", getLogger(), getBreakPoint());
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
        adj_node = null;
        k_marker = -10;
        m_bIsRunning = false;
        questions = new Vector();
        adjacent_nodes = null;
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

    public void setStructureShowing(boolean p_structureShowing)
    {
        commons.setStructureShowing(p_structureShowing);
    }

    public void setMatrixShowing(boolean p_matrixShowing)
    {
        commons.setMatrixShowing(p_matrixShowing);
    }

    public boolean getStructureShowing()
    {
        return commons.getStructureShowing();
    }

    public boolean getMatrixShowing()
    {
        return commons.getMatrixShowing();
    }

    public int getKMarker()
    {
        return k_marker;
    }

    private void createAdjacentNodeVector(GraphNode p_adjnode)
    {
        GraphNode node = p_adjnode;
        adjacent_nodes = new Vector();
        for(; node != null && node.getKey() != -1; node = node.getNext())
            adjacent_nodes.addElement(new Integer(node.getKey()));

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

    public void setKeyChangeMode(boolean keyChange)
    {
        commons.setKeyChangeMode(keyChange);
    }

    public boolean getKeyChangeMode()
    {
        return commons.getKeyChangeMode();
    }

    public void keyChange(int p_from_key, int p_to_key)
    {
        commons.keyChange(p_from_key, p_to_key);
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

    public void setInsertionMode(boolean p_insertion)
    {
        commons.setInsertionMode(p_insertion);
    }

    public boolean getInsertionMode()
    {
        return commons.getInsertionMode();
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

    public IntArray getData()
    {
        return commons.getData();
    }

    public Vector getNodes()
    {
        return commons.getNodes();
    }

    public void setNodeAppearance(GraphMapsNode node)
    {
        node.setTextColor(textColor);
        node.setBackgroundColor(unvisitedNodeColor);
        if(adjacent_nodes != null)
        {
            for(int i = 0; i < adjacent_nodes.size(); i++)
            {
                Integer tempInt = (Integer)adjacent_nodes.elementAt(i);
                if(tempInt.intValue() == node.getIntValue())
                    node.setBackgroundColor(adjacentNodesColor);
            }

        }
        if(adj_node != null && adj_node.getTo() == node.getIntValue())
            node.setBackgroundColor(adjacentNodeColor);
        if(visit_sequence != null && visit_sequence[node.getIntValue()] > 0)
            node.setBackgroundColor(visitedNodeColor);
        else
        if(visit_sequence != null && visit_sequence[node.getIntValue()] < 0)
            node.setBackgroundColor(waitingNodeColor);
        if(node.getIntValue() == k_marker)
            node.setBackgroundColor(highlightColor);
    }

    public Vector getLinks()
    {
        return commons.getLinks();
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
        link.setUseWeight(false);
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

    public Color getAdjacentNodesColor()
    {
        return adjacentNodesColor;
    }

    public Color getAdjacentNodeColor()
    {
        return adjacentNodeColor;
    }

    public Color getUnvisitedNodeColor()
    {
        return unvisitedNodeColor;
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
            if(i == k_marker)
                node.setBackgroundColor(highlightColor);
            node.setTextColor(textColor);
            node.setHeight(20);
            elementArray.setValue(i, node);
        }

        return elementArray;
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
        }
        visit_sequence = null;
        visited_links = new LinkContainer();
        m_bIsRunning = true;
        setPosition("1");
        visit_sequence = new int[commons.getNumberOfNodes()];
        for(int k = 0; k < commons.getNumberOfNodes(); k++)
            visit_sequence[k] = -1;

        setPosition("1.2.1");
        for(int k = 0; k < commons.getNumberOfNodes(); k++)
        {
            k_marker = k;
            setPosition("1.2.3");
            visit_sequence[k] = 0;
            setPosition("1.2.4");
        }

        setPosition("1.2.3");
        setPosition("1.2.5");
        visit_counter = 0;
        setPosition("1.3.1");
        setPosition("1.4");
        for(int k = 0; k < commons.getNumberOfNodes(); k++)
        {
            k_marker = k;
            setPosition("2.1");
            setPosition("3.1");
            if(visit_sequence[k] == 0)
            {
                setPosition("4");
                visit(k);
            }
        }

        setPosition("2.1");
        setPosition("9");
        m_bIsRunning = false;
    }

    protected void visit(int k)
    {
        GraphNode adj_structure[] = commons.getAdjacencyStructureArray();
        k_marker = k;
        visit_sequence[k] = ++visit_counter;
        setPosition("5.1");
        GraphNode adjNode = adj_structure[k];
        adj_node = new GraphMapsNode(new Integer(0), 0, 0);
        adj_node.setFrom(k);
        adj_node.setTo(adjNode.getKey());
        createAdjacentNodeVector(adjNode);
        setPosition("6.1");
        for(; adjNode.getKey() != -1; setPosition("7.3"))
        {
            setPosition("6.2");
            setPosition("7.1.1");
            if(visit_sequence[adjNode.getKey()] == 0)
            {
                makeQuestion(adjNode.getKey());
                askQuestionsWithoutSetPosition();
                setPosition("7.2");
                visited_links.addLink(new NodeInfo(k, 0, 0), new NodeInfo(adjNode.getKey(), 0, 0), commons.getOmniDirectional(), 0);
                visit(adjNode.getKey());
                k_marker = k;
                adj_node = new GraphMapsNode(new Integer(0), 0, 0);
                adj_node.setFrom(k);
                adj_node.setTo(adjNode.getKey());
                createAdjacentNodeVector(adjNode);
            }
            adjNode = adjNode.getNext();
            adj_node = new GraphMapsNode(new Integer(0), 0, 0);
            adj_node.setFrom(k);
            adj_node.setTo(adjNode.getKey());
        }

        setPosition("6.2");
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

    public MultipleTween generateTweens(Paintable paintable, Object parameter, int numberOfSteps)
    {
        return null;
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
    }

    public volatile Object getData()
    {
        return getData();
    }

    protected Color textColor;
    protected Color highlightColor;
    protected Color highlightLinkColor;
    protected Color unvisitedNodeColor;
    protected Color adjacentNodesColor;
    protected Color adjacentNodeColor;
    protected Color visitedNodeColor;
    protected Color backgroundColor;
    protected Color flagUnvisitedColor;
    protected Color flagVisitedColor;
    protected Color waitingNodeColor;
    protected static final String HIGHLIGHT_COLOR = Messages.getString("DFSGraphMaps.0");
    protected static final String NODE_COLOR = Messages.getString("DFSGraphMaps.2");
    protected GraphAlgorithmCommon commons;
    protected boolean m_bIsRunning;
    private int visit_sequence[];
    private int visit_counter;
    private LinkContainer visited_links;
    private GraphMapsNode adj_node;
    private int k_marker;
    private Vector adjacent_nodes;
    protected Vector questions;
    public static final int UNMARKED = -10;
    public static final String VISIT_MODE_LABEL = "Visit";
    protected boolean m_backMode;
    protected int m_stored_data[];

}
