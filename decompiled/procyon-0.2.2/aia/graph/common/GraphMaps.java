package aia.graph.common;

import localization.*;
import java.util.*;
import java.awt.*;
import com.cim.AIA.*;

public abstract class GraphMaps extends Algorithm
{
    protected static final String HIGHLIGHT_COLOR;
    protected static final String NODE_COLOR;
    public static final int UNMARKED = -10;
    public static final String VISIT_MODE_LABEL;
    protected Color textColor;
    protected Color highlightColor;
    protected Color highlightLinkColor;
    protected Color unvisitedNodeColor;
    protected Color adjacentNodesColor;
    protected Color adjacentNodeColor;
    protected Color backgroundColor;
    protected Color visitedNodeColor;
    protected Color flagUnvisitedColor;
    protected Color flagVisitedColor;
    protected Color queueNodeColor;
    protected Color queueNodeTextColor;
    protected Color waitingNodeColor;
    protected GraphAlgorithmCommon commons;
    protected boolean m_bIsRunning;
    protected int[] visit_sequence;
    protected int visit_counter;
    protected LinkContainer visited_links;
    private GraphMapsNode adj_node;
    protected int k_marker;
    protected Queue queue;
    private QueueMember current_queue_node;
    private Vector<Integer> adjacent_nodes;
    protected Vector<Integer> questions;
    protected Vector<QueueRequest> queue_requests;
    protected final int VISIBLE_QUEUE_NODES = 10;
    protected boolean m_backMode;
    protected int[] m_stored_data;
    
    public GraphMaps(final AlgorithmThread algorithmThread, final Object data) {
        super(algorithmThread, data);
        this.textColor = Color.black;
        this.highlightColor = Color.yellow;
        this.highlightLinkColor = Color.red;
        this.unvisitedNodeColor = new Color(123, 202, 123);
        this.adjacentNodesColor = new Color(0, 172, 0);
        this.adjacentNodeColor = new Color(174, 255, 174);
        this.backgroundColor = Color.white;
        this.visitedNodeColor = Color.gray;
        this.flagUnvisitedColor = Color.lightGray;
        this.flagVisitedColor = Color.gray;
        this.queueNodeColor = new Color(109, 234, 234);
        this.queueNodeTextColor = Color.black;
        this.waitingNodeColor = new Color(109, 234, 234);
        this.m_bIsRunning = false;
        this.visit_sequence = null;
        this.visit_counter = -10;
        this.visited_links = null;
        this.adj_node = null;
        this.k_marker = -10;
        this.queue = null;
        this.current_queue_node = null;
        this.adjacent_nodes = null;
        this.questions = new Vector();
        this.queue_requests = new Vector();
        this.VISIBLE_QUEUE_NODES = 10;
        this.m_backMode = false;
        this.m_stored_data = null;
    }
    
    private void addQueueRequest(final boolean p_insert, final int p_key) {
        if (this.queue_requests == null) {
            this.queue_requests = new Vector();
        }
        this.queue_requests.addElement(new QueueRequest(p_insert, p_key));
    }
    
    public void createAdjacentNodesVector(final GraphNode p_node) {
        GraphNode node = p_node;
        this.adjacent_nodes = new Vector();
        for (; node != null && node.getKey() != -1; node = node.getNext()) {
            this.adjacent_nodes.addElement(new Integer(node.getKey()));
        }
    }
    
    public void deleteNode(final int p_node) {
        this.commons.deleteNode(p_node);
    }
    
    public void editLink(final GraphMapsNode p_node, final int p_value) {
        this.commons.editLink(p_node, p_value);
    }
    
    public boolean editLinkStarted() {
        return this.commons.editLinkStarted();
    }
    
    public void endEditLink() {
        this.commons.endEditLink();
    }
    
    protected Vector<NextVisitQuestion> generateQuestions() {
        final Vector<NextVisitQuestion> questionsToAsk = new Vector();
        for (int i = 0; i < this.questions.size(); ++i) {
            final Integer tempInt = (Integer)this.questions.elementAt(i);
            questionsToAsk.addElement(new NextVisitQuestion(tempInt.intValue()));
        }
        return questionsToAsk;
    }
    
    public Vector<ElementArray> getAdjacencyMatrix() {
        return this.commons.getAdjacencyMatrix();
    }
    
    public ElementArray getAdjacencyStructure() {
        return this.commons.getAdjacencyStructure();
    }
    
    public GraphNode getAdjacentNode(final int p_index) {
        return this.commons.getAdjacentNode(p_index);
    }
    
    public Color getAdjacentNodeColor() {
        return this.adjacentNodeColor;
    }
    
    public Color getAdjacentNodesColor() {
        return this.adjacentNodesColor;
    }
    
    public GraphMapsNode getAdjNode() {
        return this.adj_node;
    }
    
    public String getClassName() {
        return "GraphMaps";
    }
    
    public int getCurrentLinkLinkWeight() {
        return this.commons.getCurrentLinkLinkWeight();
    }
    
    public GraphMapsNode getCurrentQueueNode() {
        if (this.current_queue_node == null) {
            return null;
        }
        final GraphMapsNode node = new GraphMapsNode(new Integer(this.current_queue_node.getKey()), 0, 5);
        node.setBackgroundColor(this.queueNodeColor);
        node.setTextColor(this.queueNodeTextColor);
        node.setHeight(20);
        return node;
    }
    
    public Object getData() {
        return this.commons.getData();
    }
    
    public boolean getDeleteMode() {
        return this.commons.getDeleteMode();
    }
    
    public int getEditLinkStep() {
        return this.commons.getEditLinkStep();
    }
    
    public int getEndNode() {
        return this.commons.getEndNode();
    }
    
    public Color getHighlightColor() {
        return this.highlightColor;
    }
    
    public Color getHighlightLinkColor() {
        return this.highlightLinkColor;
    }
    
    public boolean getInsertionMode() {
        return this.commons.getInsertionMode();
    }
    
    public boolean getIsRunning() {
        return this.m_bIsRunning;
    }
    
    public boolean getKeyChangeMode() {
        return this.commons.getKeyChangeMode();
    }
    
    public int getKMarker() {
        return this.k_marker;
    }
    
    public Vector<Link> getLinks() {
        return this.commons.getLinks();
    }
    
    public boolean getMatrixShowing() {
        return this.commons.getMatrixShowing();
    }
    
    public Vector<GraphMapsNode> getNodes() {
        return this.commons.getNodes();
    }
    
    public int getNumberOfNodes() {
        return this.commons.getNumberOfNodes();
    }
    
    public ElementArray getQueue() {
        if (this.queue == null) {
            return null;
        }
        final ElementArray elementArray = new ElementArray(0, 0);
        elementArray.setColumGap(5);
        elementArray.setElementWidth(20);
        final Vector<Object> queueItems = this.queue.getQueue();
        for (int i = 0; i < queueItems.size(); ++i) {
            if (i == 11) {
                break;
            }
            final QueueMember queueMember = (QueueMember)queueItems.elementAt(i);
            final GraphMapsNode node = new GraphMapsNode(new Integer(queueMember.getKey()), i, 5);
            node.setBackgroundColor(this.queueNodeColor);
            node.setTextColor(this.queueNodeTextColor);
            node.setHeight(20);
            if (i == 10) {
                node.setIsVisible(false);
            }
            elementArray.setValue(i, node);
        }
        return elementArray;
    }
    
    public ElementArray getSeqArray() {
        GraphMapsNode node = null;
        if (this.visit_sequence == null) {
            return null;
        }
        final ElementArray elementArray = new ElementArray(0, 0);
        elementArray.setColumGap(0);
        elementArray.setElementWidth(20);
        for (int i = 0; i < this.visit_sequence.length; ++i) {
            node = new GraphMapsNode(new Integer(this.visit_sequence[i]), i, 4);
            node.setBackgroundColor(this.flagUnvisitedColor);
            if (this.adj_node != null && this.adj_node.getTo() == i) {
                node.setBackgroundColor(this.adjacentNodeColor);
            }
            if (this.visit_sequence[i] > 0) {
                node.setBackgroundColor(this.flagVisitedColor);
            }
            else if (this.visit_sequence[i] < 0) {
                node = new GraphMapsNode(new Integer(0), i, 4);
                node.setBackgroundColor(this.waitingNodeColor);
            }
            node.setTextColor(this.textColor);
            if (i == this.k_marker) {
                node.setBackgroundColor(this.highlightColor);
            }
            node.setHeight(20);
            elementArray.setValue(i, node);
        }
        return elementArray;
    }
    
    public int getSeqNumber() {
        return this.visit_counter;
    }
    
    public int getStartNode() {
        return this.commons.getStartNode();
    }
    
    public boolean getStructureShowing() {
        return this.commons.getStructureShowing();
    }
    
    public Color getTextColor() {
        return this.textColor;
    }
    
    public Color getUnvisitedNodeColor() {
        return this.unvisitedNodeColor;
    }
    
    public String getVisitStatus(final int p_key) {
        if (this.visit_sequence == null || p_key < 0 || p_key >= this.visit_sequence.length) {
            return "";
        }
        if (this.visit_sequence[p_key] > 0) {
            return "T";
        }
        if (this.visit_sequence[p_key] < 0) {
            return "P";
        }
        return "F";
    }
    
    protected boolean hasQuestions() {
        return this.questions.size() > 0;
    }
    
    protected void initialise(final Object data) {
        this.visit_sequence = null;
        this.visit_counter = -10;
        this.visited_links = null;
        this.adj_node = null;
        this.k_marker = -10;
        this.queue = null;
        this.questions = new Vector();
        this.queue_requests = new Vector();
        this.m_bIsRunning = false;
        this.adjacent_nodes = null;
        this.current_queue_node = null;
        if (this.commons == null) {
            this.commons = new GraphAlgorithmCommon(this, data);
        }
        else {
            this.commons.setData(data);
        }
        this.setStructureShowing(false);
        this.setMatrixShowing(false);
    }
    
    public void insertNewNode(final int x, final int y) {
        this.commons.insertNewNode(x, y);
    }
    
    public boolean isQueueOverFlow() {
        return this.queue != null && this.queue.getCount() > 10;
    }
    
    public void keyChange(final int p_from_key, final int p_to_key) {
        this.commons.keyChange(p_from_key, p_to_key);
    }
    
    protected void makeQuestion(final int nodeKey) {
        this.questions.addElement(new Integer(nodeKey));
    }
    
    public boolean needPromptForLinkWeight() {
        return this.commons.needPromptForLinkWeight();
    }
    
    public void processColorSelection(final ColorSelection colorSelection) {
        final String atribute = colorSelection.getAtributeName();
        if (atribute.equalsIgnoreCase(ColorSelection.BACKGROUND)) {
            this.backgroundColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR)) {
            this.textColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(GraphMaps.HIGHLIGHT_COLOR)) {
            this.highlightColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(GraphMaps.NODE_COLOR)) {
            this.unvisitedNodeColor = colorSelection.getColor();
        }
    }
    
    public void processMethodSelectionEvent(final MethodSelectionEvent e) {
    }
    
    public void reInitialise(final Object data) {
        this.initialise(data);
    }
    
    public void removeAllAnimationRequests() {
        this.queue_requests = new Vector();
    }
    
    protected void removeAllQuestions() {
        this.questions = new Vector();
    }
    
    public void setBackMode() {
        if (this.commons == null) {
            return;
        }
        this.m_backMode = true;
        this.m_stored_data = (int[])((int[])this.commons.getData().copy());
    }
    
    public void setDeleteMode(final boolean p_delete) {
        this.commons.setDeleteMode(p_delete);
    }
    
    public void setEndNode(final int p_key) {
        this.commons.setEndNode(p_key);
    }
    
    public void setInsertionMode(final boolean p_insertion) {
        this.commons.setInsertionMode(p_insertion);
    }
    
    public void setKeyChangeMode(final boolean keyChange) {
        this.commons.setKeyChangeMode(keyChange);
    }
    
    public void setLinkAppearance(final Link link) {
        link.setUseWeight(false);
    }
    
    public void setLinkHighlighted(final Link link) {
        if (this.visited_links != null && this.visited_links.findLink(link.getStart(), link.getEnd()) != null) {
            link.setHighlighted(true);
            link.setLinkColor(this.highlightLinkColor);
        }
    }
    
    public void setLinkWeight(final int p_link_weight) {
        this.commons.setLinkWeight(p_link_weight);
    }
    
    public void setMatrixNodeAppearance(final GraphMapsNode node) {
        node.setBackgroundColor(this.unvisitedNodeColor);
        node.setTextColor(this.textColor);
        if (this.adj_node != null) {
            if (node.getFrom() == this.adj_node.getFrom() && node.getTo() == this.adj_node.getTo()) {
                node.setBackgroundColor(this.adjacentNodeColor);
            }
            else if (node.getFrom() == this.adj_node.getFrom()) {
                node.setBackgroundColor(this.adjacentNodesColor);
            }
        }
    }
    
    public void setMatrixShowing(final boolean p_matrixShowing) {
        this.commons.setMatrixShowing(p_matrixShowing);
    }
    
    public void setNodeAppearance(final GraphMapsNode node) {
        node.setTextColor(this.textColor);
        node.setBackgroundColor(this.unvisitedNodeColor);
        if (this.adjacent_nodes != null) {
            for (int i = 0; i < this.adjacent_nodes.size(); ++i) {
                final Integer tempInt = (Integer)this.adjacent_nodes.elementAt(i);
                if (tempInt == node.getIntValue()) {
                    node.setBackgroundColor(this.adjacentNodesColor);
                    break;
                }
            }
        }
        if (this.adj_node != null && this.adj_node.getTo() == node.getIntValue()) {
            node.setBackgroundColor(this.adjacentNodeColor);
        }
        if (this.visit_sequence != null && this.visit_sequence[node.getIntValue()] > 0) {
            node.setBackgroundColor(this.visitedNodeColor);
        }
        else if (this.visit_sequence != null && this.visit_sequence[node.getIntValue()] < 0) {
            node.setBackgroundColor(this.waitingNodeColor);
        }
        if (node.getIntValue() == this.k_marker) {
            node.setBackgroundColor(this.highlightColor);
        }
    }
    
    public void setNodePosition(final int p_nodeIndex, final int x, final int y) {
        this.commons.setNodePosition(p_nodeIndex, x, y);
    }
    
    public void setOmniDirectional(final boolean p_omniDir) {
        this.commons.setOmniDirectional(p_omniDir);
    }
    
    public void setStartNode(final int p_key) {
        this.commons.setStartNode(p_key);
    }
    
    public void setStructureNodeAppearance(final GraphMapsNode node) {
        node.setBackgroundColor(this.unvisitedNodeColor);
        node.setTextColor(this.textColor);
        if (this.adj_node != null && node.getIntValue() == this.adj_node.getFrom()) {
            node.setBackgroundColor(this.highlightColor);
        }
    }
    
    public void setStructureShowing(final boolean p_structureShowing) {
        this.commons.setStructureShowing(p_structureShowing);
    }
    
    public void startEditLink(final Integer p_link_weight) {
        this.commons.startEditLink(p_link_weight);
    }
    
    protected void visit(int k) {
        final GraphNode[] adj_nodes = this.commons.getAdjacencyStructureArray();
        this.k_marker = k;
        this.queue.put(new QueueMember(k, -1));
        this.addQueueRequest(true, k);
        this.setPosition("5");
        while (!this.queue.isEmpty()) {
            this.setPosition("6");
            QueueMember queueMember = (QueueMember)this.queue.peek();
            k = queueMember.getKey();
            this.k_marker = k;
            this.addQueueRequest(false, k);
            this.setPosition("6.1");
            queueMember = (QueueMember)this.queue.get();
            this.current_queue_node = queueMember.copy();
            this.makeQuestion(k);
            this.askQuestionsWithoutSetPosition();
            final int[] visit_sequence = this.visit_sequence;
            final int n = k;
            final int visit_counter = this.visit_counter + 1;
            this.visit_counter = visit_counter;
            visit_sequence[n] = visit_counter;
            if (queueMember.getParent() != -1) {
                this.visited_links.addLink(new NodeInfo(queueMember.getParent(), 0, 0), new NodeInfo(k, 0, 0), this.commons.getOmniDirectional(), 0);
            }
            this.setPosition("6.2.1");
            GraphNode adjNode = adj_nodes[k];
            this.adj_node = new GraphMapsNode(new Integer(0), 0, 0);
            this.adj_node.setFrom(k);
            this.adj_node.setTo(adjNode.getKey());
            this.createAdjacentNodesVector(adjNode);
            this.setPosition("6.3.1");
            for (; adjNode.getKey() != -1; adjNode = adjNode.getNext(), this.adj_node = new GraphMapsNode(new Integer(0), 0, 0), this.adj_node.setFrom(k), this.adj_node.setTo(adjNode.getKey()), this.createAdjacentNodesVector(adjNode), this.setPosition("6.6")) {
                this.setPosition("6.3.2");
                this.setPosition("6.4.1");
                if (this.visit_sequence[adjNode.getKey()] == 0) {
                    this.queue.put(new QueueMember(adjNode.getKey(), k));
                    this.addQueueRequest(true, adjNode.getKey());
                    this.setPosition("6.5.1");
                    this.visit_sequence[adjNode.getKey()] = -1;
                    this.setPosition("6.5.2.1");
                }
            }
            this.setPosition("6.3.2");
        }
        this.setPosition("6");
        this.setPosition("7");
    }
    
    public void zoomIn() {
        this.commons.zoomIn();
    }
    
    public void zoomOut() {
        this.commons.zoomOut();
    }
    
    static {
        HIGHLIGHT_COLOR = Messages.getString("GraphMaps.0");
        NODE_COLOR = Messages.getString("GraphMaps.1");
        VISIT_MODE_LABEL = Messages.getString("GraphMaps.2");
    }
}
