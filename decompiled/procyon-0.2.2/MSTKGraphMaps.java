import localization.*;
import java.util.*;
import java.awt.*;
import com.cim.AIA.*;
import aia.graph.common.*;

public class MSTKGraphMaps extends GraphMaps implements ColorSelectionListener, MethodSelectionListener
{
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
    protected static final String HIGHLIGHT_COLOR;
    protected static final String NODE_COLOR;
    protected GraphAlgorithmCommon commons;
    protected boolean m_bIsRunning;
    private int edges_added;
    private LinkContainer visited_links;
    private int v1_marker;
    private int v2_marker;
    private MSTKPriorityQueue pqueue;
    private MSTKPriorityQueueMember current_edge;
    private Vector<E> connected_nodes;
    protected Vector<E> questions;
    protected Vector<E> queue_requests;
    public static final int UNMARKED = -10;
    public static final String BUILD_MODE_LABEL;
    protected int VISIBLE_QUEUE_NODES;
    protected boolean m_backMode;
    protected int[] m_stored_data;
    
    public MSTKGraphMaps(final AlgorithmThread algorithmThread, final Object data) {
        super(algorithmThread, data);
        this.textColor = Color.black;
        this.highlightColor = Color.yellow;
        this.highlightLinkColor = Color.red;
        this.unvisitedNodeColor = new Color(123, 202, 123);
        this.backgroundColor = Color.white;
        this.flagUnvisitedColor = Color.lightGray;
        this.flagVisitedColor = Color.gray;
        this.queueNodeColorLeft = new Color(109, 234, 234);
        this.queueNodeColorRight = new Color(109, 234, 234);
        this.waitingNodeColor = new Color(109, 234, 234);
        this.connectedNodesColor = new Color(255, 221, 51);
        this.activeVerticesColor = new Color(255, 123, 123);
        this.commons = null;
        this.m_bIsRunning = false;
        this.edges_added = -10;
        this.visited_links = null;
        this.v1_marker = -10;
        this.v2_marker = -10;
        this.pqueue = null;
        this.current_edge = null;
        this.connected_nodes = null;
        this.questions = new Vector();
        this.queue_requests = new Vector();
        this.VISIBLE_QUEUE_NODES = 5;
        this.m_backMode = false;
        this.m_stored_data = null;
        this.initialise(data);
        final ConfigurationManager cm = ConfigurationManager.getConfigurationManager();
        final ColorSelection highlightSelection = new ColorSelection(this.highlightColor, MSTKGraphMaps.HIGHLIGHT_COLOR);
        highlightSelection.addClass(this);
        cm.addColorSelection(highlightSelection);
        final ColorSelection nodeSelection = new ColorSelection(this.unvisitedNodeColor, MSTKGraphMaps.NODE_COLOR);
        nodeSelection.addClass(this);
        cm.addColorSelection(nodeSelection);
        cm.addColorSelectionListener(this);
    }
    
    public void initialiseMethods(final String dataDir, final MethodSelectable methodSelectable) {
        final MethodSelection build = new MethodSelection(MSTKGraphMaps.BUILD_MODE_LABEL, dataDir, Messages.getString("MSTKGraphMaps.0"), "4", this.getLogger(), this.getBreakPoint());
        methodSelectable.addMethodSelection(build, true);
        methodSelectable.addMethodSelectionListener(this);
    }
    
    public void processMethodSelectionEvent(final MethodSelectionEvent e) {
    }
    
    public void setBackMode() {
        if (this.commons == null) {
            return;
        }
        this.m_backMode = true;
        this.m_stored_data = (int[])((int[])this.commons.getData().copy());
    }
    
    public void reInitialise(final Object data) {
        this.initialise(data);
    }
    
    protected void initialise(final Object data) {
        this.visited_links = null;
        this.pqueue = null;
        this.questions = new Vector();
        this.queue_requests = new Vector();
        this.current_edge = null;
        this.connected_nodes = null;
        this.edges_added = -10;
        this.v1_marker = -10;
        this.v2_marker = -10;
        this.m_bIsRunning = false;
        if (this.commons == null) {
            this.commons = new MSTKGraphAlgorithmCommon(this, data);
        }
        else {
            this.commons.setData(data);
        }
        this.setStructureShowing(false);
        this.setMatrixShowing(false);
    }
    
    public void setNodePosition(final int p_nodeIndex, final int x, final int y) {
        this.commons.setNodePosition(p_nodeIndex, x, y);
    }
    
    public int getV1Marker() {
        return this.v1_marker;
    }
    
    public int getV2Marker() {
        return this.v2_marker;
    }
    
    public void setOmniDirectional(final boolean p_omniDir) {
        this.commons.setOmniDirectional(p_omniDir);
    }
    
    public void zoomIn() {
        this.commons.zoomIn();
    }
    
    public void zoomOut() {
        this.commons.zoomOut();
    }
    
    public void setInsertionMode(final boolean p_insertion) {
        this.commons.setInsertionMode(p_insertion);
    }
    
    public boolean getInsertionMode() {
        return this.commons.getInsertionMode();
    }
    
    public void setKeyChangeMode(final boolean p_keyChange) {
        this.commons.setKeyChangeMode(p_keyChange);
    }
    
    public boolean getKeyChangeMode() {
        return this.commons.getKeyChangeMode();
    }
    
    public void keyChange(final int p_key_from, final int p_key_to) {
        this.commons.keyChange(p_key_from, p_key_to);
    }
    
    public void startEditLink(final Integer p_link_weight) {
        this.commons.startEditLink(p_link_weight);
    }
    
    public void endEditLink() {
        this.commons.endEditLink();
    }
    
    public void setStartNode(final int p_key) {
        this.commons.setStartNode(p_key);
    }
    
    public void setEndNode(final int p_key) {
        this.commons.setEndNode(p_key);
    }
    
    public void setLinkWeight(final int p_link_weight) {
        this.commons.setLinkWeight(p_link_weight);
    }
    
    public boolean needPromptForLinkWeight() {
        return this.commons.needPromptForLinkWeight();
    }
    
    public int getCurrentLinkLinkWeight() {
        return this.commons.getCurrentLinkLinkWeight();
    }
    
    public boolean editLinkStarted() {
        return this.commons.editLinkStarted();
    }
    
    public int getEditLinkStep() {
        return this.commons.getEditLinkStep();
    }
    
    public int getStartNode() {
        return this.commons.getStartNode();
    }
    
    public int getEndNode() {
        return this.commons.getEndNode();
    }
    
    public void insertNewNode(final int x, final int y) {
        this.commons.insertNewNode(x, y);
    }
    
    public void deleteNode(final int p_node) {
        this.commons.deleteNode(p_node);
    }
    
    public void setDeleteMode(final boolean p_delete) {
        this.commons.setDeleteMode(p_delete);
    }
    
    public boolean getDeleteMode() {
        return this.commons.getDeleteMode();
    }
    
    public void editLink(final GraphMapsNode p_node, final int p_value) {
        this.commons.editLink(p_node, p_value);
    }
    
    public Object getData() {
        return this.commons.getData();
    }
    
    public Vector<E> getNodes() {
        return this.commons.getNodes();
    }
    
    public void setNodeAppearance(final GraphMapsNode node) {
        node.setBackgroundColor(this.unvisitedNodeColor);
        node.setTextColor(this.textColor);
        if (this.connected_nodes != null) {
            for (int i = 0; i < this.connected_nodes.size(); ++i) {
                final Integer tempInt = (Integer)this.connected_nodes.elementAt(i);
                if (tempInt == node.getIntValue()) {
                    node.setBackgroundColor(this.connectedNodesColor);
                    break;
                }
            }
        }
        if (node.getIntValue() == this.v1_marker || node.getIntValue() == this.v2_marker) {
            node.setBackgroundColor(this.activeVerticesColor);
        }
    }
    
    public void setLinkHighlighted(final Link link) {
        if (this.visited_links != null && this.visited_links.findLink(link.getStart(), link.getEnd()) != null) {
            link.setHighlighted(true);
            link.setLinkColor(this.highlightLinkColor);
        }
    }
    
    public void setLinkAppearance(final Link link) {
        if (this.m_bIsRunning) {
            link.setLinkColor(Color.lightGray);
        }
        final Link link2 = new Link(new NodeInfo(this.v1_marker, 0, 0), new NodeInfo(this.v2_marker, 0, 0), this.commons.getOmniDirectional());
        if (link.isEqualTo(link2)) {
            link.setLinkColor(new Color(255, 192, 192));
        }
    }
    
    public TwinNode getCurrentEdgeNode() {
        if (this.current_edge == null) {
            return null;
        }
        final TwinNode node = new TwinNode(new Integer(this.current_edge.getFromKey()), new Integer(this.current_edge.getToKey()), 0);
        node.setBackgroundColorLeft(this.queueNodeColorLeft);
        node.setBackgroundColorRight(this.queueNodeColorRight);
        node.setTextColor(this.textColor);
        node.setHeight(20);
        node.setPriority(this.current_edge.getPriority());
        return node;
    }
    
    public Vector<E> getLinks() {
        return this.commons.getLinks();
    }
    
    public int getEdgesAdded() {
        return this.edges_added;
    }
    
    public Color getQueueNodeColorLeft() {
        return this.queueNodeColorLeft;
    }
    
    public Color getQueueNodeColorRight() {
        return this.queueNodeColorRight;
    }
    
    public Color getUnvisitedNodeColor() {
        return this.unvisitedNodeColor;
    }
    
    public Color getHighlightColor() {
        return this.highlightColor;
    }
    
    public Color getHighlightLinkColor() {
        return this.highlightLinkColor;
    }
    
    public Color getTextColor() {
        return this.textColor;
    }
    
    public GraphNode getAdjacentNode(final int p_index) {
        return this.commons.getAdjacentNode(p_index);
    }
    
    public int getNumberOfNodes() {
        return this.commons.getNumberOfNodes();
    }
    
    public boolean getIsRunning() {
        return this.m_bIsRunning;
    }
    
    public Vector<E> getAdjacencyMatrix() {
        return this.commons.getAdjacencyMatrix();
    }
    
    public void setMatrixNodeAppearance(final GraphMapsNode node) {
        node.setBackgroundColor(this.unvisitedNodeColor);
        node.setTextColor(this.textColor);
    }
    
    public ElementArray getAdjacencyStructure() {
        return this.commons.getAdjacencyStructure();
    }
    
    public void setStructureNodeAppearance(final GraphMapsNode node) {
        node.setBackgroundColor(this.unvisitedNodeColor);
        node.setTextColor(this.textColor);
    }
    
    public void setMatrixShowing(final boolean p_matrixShowing) {
        this.commons.setMatrixShowing(p_matrixShowing);
    }
    
    public void setStructureShowing(final boolean p_structureShowing) {
        this.commons.setStructureShowing(p_structureShowing);
    }
    
    public boolean getMatrixShowing() {
        return this.commons.getMatrixShowing();
    }
    
    public boolean getStructureShowing() {
        return this.commons.getStructureShowing();
    }
    
    public ElementArray getQueue() {
        if (this.pqueue == null) {
            return null;
        }
        final ElementArray elementArray = new ElementArray(0, 0);
        elementArray.setColumGap(5);
        elementArray.setElementWidth(40);
        final Vector<E> queueItems = this.pqueue.getQueue();
        for (int i = 0; i < queueItems.size(); ++i) {
            if (i == this.VISIBLE_QUEUE_NODES + 1) {
                break;
            }
            final MSTKPriorityQueueMember queueMember = (MSTKPriorityQueueMember)queueItems.elementAt(i);
            final TwinNode node = new TwinNode(new Integer(queueMember.getFromKey()), new Integer(queueMember.getToKey()), i);
            node.setBackgroundColorLeft(this.queueNodeColorLeft);
            node.setBackgroundColorRight(this.queueNodeColorRight);
            node.setTextColor(this.textColor);
            node.setHeight(20);
            node.setPriority(queueMember.getPriority());
            if (i == this.VISIBLE_QUEUE_NODES) {
                node.setWidth(0);
                node.setIsVisible(false);
            }
            elementArray.setValue(i, node);
        }
        return elementArray;
    }
    
    public boolean isOverFlow() {
        return this.pqueue != null && this.pqueue.size() > this.VISIBLE_QUEUE_NODES;
    }
    
    private boolean contains(final Vector<E> connectedSet, final int key) {
        for (int i = 0; i < connectedSet.size(); ++i) {
            final Integer tempInt = (Integer)connectedSet.elementAt(i);
            if (tempInt == key) {
                return true;
            }
        }
        return false;
    }
    
    private void findConnectedSet(final Vector<E> connectedSet, final int u) {
        for (GraphNode adj_node = this.getAdjacentNode(u); adj_node != null && adj_node.getKey() != -1; adj_node = adj_node.getNext()) {
            if (this.visited_links != null && this.visited_links.findLink(new NodeInfo(u, 0, 0), new NodeInfo(adj_node.getKey(), 0, 0)) != null && !this.contains(connectedSet, adj_node.getKey())) {
                connectedSet.addElement(new Integer(adj_node.getKey()));
                this.findConnectedSet(connectedSet, adj_node.getKey());
            }
        }
    }
    
    private boolean isInset(final int u, final int v) {
        final Vector<E> connectedSet = new Vector();
        this.findConnectedSet(connectedSet, u);
        for (int i = 0; i < connectedSet.size(); ++i) {
            final Integer tempInt = (Integer)connectedSet.elementAt(i);
            if (tempInt == v) {
                return true;
            }
        }
        return false;
    }
    
    private void createConnectedNodesVector(final int u, final int v) {
        final Vector<E> connectedSet = new Vector();
        this.connected_nodes = new Vector();
        this.findConnectedSet(connectedSet, v);
        Integer tempInt;
        for (int i = 0; i < connectedSet.size(); ++i) {
            tempInt = (Integer)connectedSet.elementAt(i);
            this.connected_nodes.addElement(tempInt);
        }
        this.findConnectedSet(connectedSet, u);
        for (int i = 0; i < connectedSet.size(); ++i) {
            tempInt = (Integer)connectedSet.elementAt(i);
            this.connected_nodes.addElement(tempInt);
        }
    }
    
    private void sortedges() {
        final int[][] adj_matrix = this.commons.getAdjacencyMatrixArray();
        for (int i = 0; i < this.commons.getNumberOfNodes(); ++i) {
            GraphNode node = this.getAdjacentNode(i);
            while (node != null && node.getKey() != -1) {
                this.pqueue.update(i, node.getKey(), adj_matrix[i][node.getKey()]);
                node = node.getNext();
            }
        }
    }
    
    protected void run() {
        this.commons.algorithmStartRunInitialisations();
        if (this.m_backMode) {
            this.m_backMode = false;
            this.initialise(this.m_stored_data);
            this.m_stored_data = null;
        }
        this.visited_links = new LinkContainer();
        this.m_bIsRunning = true;
        this.setPosition("1");
        this.edges_added = 0;
        this.setPosition("2.1.1");
        this.pqueue = new MSTKPriorityQueue();
        this.sortedges();
        this.setPosition("2.2.1");
        this.setPosition("2.3");
        this.setPosition("4");
        this.build();
        this.setPosition("7");
        this.m_bIsRunning = false;
    }
    
    private void build() {
        while (!this.pqueue.isEmpty()) {
            this.setPosition("5");
            MSTKPriorityQueueMember pqmember = this.pqueue.peek();
            this.addQueueRequest(2, pqmember.getFromKey(), pqmember.getToKey(), 0);
            this.setPosition("5.2");
            pqmember = this.pqueue.remove();
            this.current_edge = pqmember;
            this.createConnectedNodesVector(this.current_edge.getFromKey(), this.current_edge.getToKey());
            this.v1_marker = this.current_edge.getFromKey();
            this.v2_marker = this.current_edge.getToKey();
            this.setPosition("5.3");
            if (!this.isInset(pqmember.getToKey(), pqmember.getFromKey()) && !this.isInset(pqmember.getFromKey(), pqmember.getToKey())) {
                this.makeQuestion(pqmember.getFromKey(), pqmember.getToKey());
                this.askQuestionsWithoutSetPosition();
                this.visited_links.addLink(new NodeInfo(pqmember.getFromKey(), 0, 0), new NodeInfo(pqmember.getToKey(), 0, 0), this.commons.getOmniDirectional(), 0);
                this.edges_added = this.edges_added + 1;
                this.setPosition("5.4");
                this.setPosition("5.6");
                if (this.edges_added == this.commons.getNumberOfNodes() - 1) {
                    this.setPosition("5.6.1");
                    this.setPosition("6");
                    return;
                }
            }
        }
        this.setPosition("5");
        this.setPosition("6");
    }
    
    public void processColorSelection(final ColorSelection colorSelection) {
        final String atribute = colorSelection.getAtributeName();
        if (atribute.equalsIgnoreCase(ColorSelection.BACKGROUND)) {
            this.backgroundColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR)) {
            this.textColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(MSTKGraphMaps.HIGHLIGHT_COLOR)) {
            this.highlightColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(MSTKGraphMaps.NODE_COLOR)) {
            this.unvisitedNodeColor = colorSelection.getColor();
        }
    }
    
    private void addQueueRequest(final int p_type, final int p_from_key, final int p_to_key, final int p_param) {
        if (this.queue_requests == null) {
            this.queue_requests = new Vector();
        }
        final QueueRequest queueRequest = new QueueRequest(p_type, p_from_key, p_to_key);
        if (p_type == 3) {
            queueRequest.setParam(p_param);
        }
        this.queue_requests.addElement(queueRequest);
    }
    
    public MultipleTween generateTweens(final Paintable paintable, final Object parameter, final int numberOfSteps) {
        if (this.queue_requests == null || this.queue_requests.size() == 0) {
            return null;
        }
        final ElementArray elementArray = (ElementArray)parameter;
        if (elementArray == null) {
            return null;
        }
        TwinNode node = null;
        TwinNode node2 = null;
        final MultipleTween multipleTween = new MultipleTween(paintable);
        for (int i1 = 0; i1 < this.queue_requests.size(); ++i1) {
            final QueueRequest queueRequest = (QueueRequest)this.queue_requests.elementAt(i1);
            int i2 = 0;
            while (i2 < elementArray.getSize()) {
                node = (TwinNode)elementArray.getElement(i2);
                if (node.getLValue() == queueRequest.getFromKey() && node.getRValue() == queueRequest.getToKey()) {
                    if (queueRequest.getType() == 1) {
                        multipleTween.add(new InsertTween(paintable, node, numberOfSteps));
                        int i3 = i2 + 1;
                        while (i3 < elementArray.getSize()) {
                            node2 = (TwinNode)elementArray.getElement(i3);
                            multipleTween.add(new MoveTween(null, node2, node.getPosition(), node2.getPosition(), false, numberOfSteps));
                            node = node2;
                            ++i3;
                        }
                    }
                    else if (queueRequest.getType() == 2) {
                        multipleTween.add(new MoveTween(null, node, node.getPosition(), new Point(GraphMapsCanvasExt.QUEUE_X - 50, GraphMapsCanvasExt.QUEUE_Y), false, numberOfSteps));
                        int i3 = i2 + 1;
                        while (i3 < elementArray.getSize()) {
                            node2 = (TwinNode)elementArray.getElement(i3);
                            multipleTween.add(new MoveTween(null, node2, node2.getPosition(), node.getPosition(), false, numberOfSteps));
                            node = node2;
                            ++i3;
                        }
                        if (node2 != null && this.isOverFlow()) {
                            node2.setIsVisible(true);
                            multipleTween.add(new InsertTween(paintable, node2, numberOfSteps));
                        }
                    }
                    else if (queueRequest.getType() == 3) {
                        node2 = (TwinNode)elementArray.getElement(queueRequest.getParam());
                        multipleTween.add(new MoveTween(null, node, node2.getPosition(), node.getPosition(), false, numberOfSteps));
                        int i3 = i2;
                        while (i3 < queueRequest.getParam()) {
                            node = (TwinNode)elementArray.getElement(i3);
                            node2 = (TwinNode)elementArray.getElement(i3 + 1);
                            multipleTween.add(new MoveTween(null, node2, node.getPosition(), node2.getPosition(), false, numberOfSteps));
                            ++i3;
                        }
                    }
                }
                ++i2;
            }
        }
        if (multipleTween.getNumberOfTweens() == 0) {
            return null;
        }
        return multipleTween;
    }
    
    public String getClassName() {
        return "GraphMaps";
    }
    
    protected Vector<E> generateQuestions() {
        return this.questions;
    }
    
    protected boolean hasQuestions() {
        return this.questions.size() > 0;
    }
    
    protected void makeQuestion(final int p_from_node, final int p_to_node) {
        this.questions.addElement(new NextNewLinkQuestion(p_from_node, p_to_node));
    }
    
    protected void removeAllQuestions() {
        this.questions = new Vector();
    }
    
    public void removeAllAnimationRequests() {
        this.queue_requests = new Vector();
    }
    
    static {
        HIGHLIGHT_COLOR = Messages.getString("MSTKGraphMaps.3");
        NODE_COLOR = Messages.getString("MSTKGraphMaps.2");
        BUILD_MODE_LABEL = Messages.getString("MSTKGraphMaps.1");
    }
}
