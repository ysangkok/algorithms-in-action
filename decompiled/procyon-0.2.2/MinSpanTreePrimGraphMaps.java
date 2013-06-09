import localization.*;
import java.util.*;
import java.awt.*;
import com.cim.AIA.*;
import aia.graph.common.*;

public class MinSpanTreePrimGraphMaps extends GraphMaps implements ColorSelectionListener, MethodSelectionListener
{
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
    protected static final String HIGHLIGHT_COLOR;
    protected static final String NODE_COLOR;
    protected GraphAlgorithmCommon commons;
    protected boolean m_bIsRunning;
    private int[] visit_sequence;
    private int visit_counter;
    private LinkContainer visited_links;
    private GraphMapsNode adj_node;
    private int k_marker;
    private MinSpanTreePrimPriorityQueue pqueue;
    private MinSpanTreePrimPriorityQueueMember current_node;
    protected Vector<E> adjacent_nodes;
    protected Vector<E> questions;
    protected Vector<E> queue_requests;
    protected int VISIBLE_QUEUE_NODES;
    public static final int UNMARKED = -10;
    public static final String VISIT_MODE_LABEL;
    protected boolean m_backMode;
    protected int[] m_stored_data;
    
    public MinSpanTreePrimGraphMaps(final AlgorithmThread algorithmThread, final Object data) {
        super(algorithmThread, data);
        this.textColor = Color.black;
        this.highlightColor = Color.yellow;
        this.highlightLinkColor = Color.red;
        this.unvisitedNodeColor = new Color(123, 202, 123);
        this.adjacentNodesColor = new Color(0, 172, 0);
        this.adjacentNodeColor = new Color(174, 255, 174);
        this.backgroundColor = Color.white;
        this.flagUnvisitedColor = Color.lightGray;
        this.flagVisitedColor = Color.gray;
        this.visitedNodeColor = Color.gray;
        this.queueNodeColorLeft = new Color(109, 234, 234);
        this.queueNodeColorRight = new Color(84, 183, 183);
        this.waitingNodeColor = new Color(109, 234, 234);
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
        this.questions = new Vector();
        this.queue_requests = new Vector();
        this.VISIBLE_QUEUE_NODES = 5;
        this.m_backMode = false;
        this.m_stored_data = null;
        this.initialise(data);
        final ConfigurationManager cm = ConfigurationManager.getConfigurationManager();
        final ColorSelection highlightSelection = new ColorSelection(this.highlightColor, MinSpanTreePrimGraphMaps.HIGHLIGHT_COLOR);
        highlightSelection.addClass(this);
        cm.addColorSelection(highlightSelection);
        final ColorSelection nodeSelection = new ColorSelection(this.unvisitedNodeColor, MinSpanTreePrimGraphMaps.NODE_COLOR);
        nodeSelection.addClass(this);
        cm.addColorSelection(nodeSelection);
        cm.addColorSelectionListener(this);
    }
    
    public void initialiseMethods(final String dataDir, final MethodSelectable methodSelectable) {
        final MethodSelection visit = new MethodSelection(MinSpanTreePrimGraphMaps.VISIT_MODE_LABEL, dataDir, Messages.getString("MinSpanTreePrimGraphMaps.2"), "4", this.getLogger(), this.getBreakPoint());
        methodSelectable.addMethodSelection(visit, true);
        methodSelectable.addMethodSelectionListener(this);
    }
    
    public void processMethodSelectionEvent(final MethodSelectionEvent e) {
    }
    
    public void reInitialise(final Object data) {
        this.initialise(data);
    }
    
    public void setBackMode() {
        if (this.commons == null) {
            return;
        }
        this.m_backMode = true;
        this.m_stored_data = (int[])((int[])this.commons.getData().copy());
    }
    
    protected void initialise(final Object data) {
        this.visit_sequence = null;
        this.visit_counter = -10;
        this.visited_links = null;
        this.current_node = null;
        this.adj_node = null;
        this.k_marker = -10;
        this.pqueue = null;
        this.questions = new Vector();
        this.queue_requests = new Vector();
        this.adjacent_nodes = null;
        this.m_bIsRunning = false;
        if (this.commons == null) {
            this.commons = new GraphAlgorithmCommon(this, data);
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
    
    public int getKMarker() {
        return this.k_marker;
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
        if (this.adjacent_nodes != null) {
            for (int i = 0; i < this.adjacent_nodes.size(); ++i) {
                final Integer adjacent_node = (Integer)this.adjacent_nodes.elementAt(i);
                if (node.getIntValue() == adjacent_node) {
                    node.setBackgroundColor(this.adjacentNodesColor);
                    break;
                }
            }
        }
        if (this.adj_node != null && this.adj_node.getTo() == node.getIntValue()) {
            node.setBackgroundColor(this.adjacentNodeColor);
        }
        if (this.visit_sequence != null) {
            if (this.visit_sequence[node.getIntValue()] > 0) {
                node.setBackgroundColor(this.visitedNodeColor);
            }
            else if (this.visit_sequence[node.getIntValue()] < 0) {
                node.setBackgroundColor(this.waitingNodeColor);
            }
        }
        if (node.getIntValue() == this.k_marker) {
            node.setBackgroundColor(this.highlightColor);
        }
    }
    
    private void createAdjacentNodeVector(GraphNode p_node) {
        this.adjacent_nodes = new Vector();
        for (; p_node != null && p_node.getKey() != -1; p_node = p_node.getNext()) {
            this.adjacent_nodes.addElement(new Integer(p_node.getKey()));
        }
    }
    
    public void setLinkHighlighted(final Link link) {
        if (this.visited_links != null && this.visited_links.findLink(link.getStart(), link.getEnd()) != null) {
            link.setHighlighted(true);
            link.setLinkColor(this.highlightLinkColor);
        }
    }
    
    public void setLinkAppearance(final Link link) {
    }
    
    public Vector<E> getLinks() {
        return this.commons.getLinks();
    }
    
    public TwinNode getCurrentPQNode() {
        if (this.current_node == null) {
            return null;
        }
        final TwinNode node = new TwinNode(new Integer(this.current_node.getKey()), new Integer(this.current_node.getParent()), 0);
        node.setBackgroundColorLeft(this.queueNodeColorLeft);
        node.setBackgroundColorRight(this.queueNodeColorRight);
        node.setTextColor(this.textColor);
        node.setHeight(20);
        node.setPriority(this.current_node.getPriority());
        return node;
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
    
    public Color getAdjacentNodeColor() {
        return this.adjacentNodeColor;
    }
    
    public Color getAdjacentNodesColor() {
        return this.adjacentNodesColor;
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
    
    public int getSeqNumber() {
        return this.visit_counter;
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
        if (this.adj_node != null) {
            if (node.getFrom() == this.adj_node.getFrom() && node.getTo() == this.adj_node.getTo()) {
                node.setBackgroundColor(this.adjacentNodeColor);
            }
            else if (node.getFrom() == this.adj_node.getFrom()) {
                node.setBackgroundColor(this.adjacentNodesColor);
            }
        }
    }
    
    public ElementArray getAdjacencyStructure() {
        return this.commons.getAdjacencyStructure();
    }
    
    public void setStructureNodeAppearance(final GraphMapsNode node) {
        node.setBackgroundColor(this.unvisitedNodeColor);
        node.setTextColor(this.textColor);
        if (this.adj_node != null && node.getIntValue() == this.adj_node.getFrom()) {
            node.setBackgroundColor(this.highlightColor);
        }
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
            final MinSpanTreePrimPriorityQueueMember queueMember = (MinSpanTreePrimPriorityQueueMember)queueItems.elementAt(i);
            final TwinNode node = new TwinNode(new Integer(queueMember.getKey()), new Integer(queueMember.getParent()), i);
            node.setBackgroundColorLeft(this.queueNodeColorLeft);
            node.setBackgroundColorRight(this.queueNodeColorRight);
            node.setTextColor(this.textColor);
            node.setHeight(20);
            node.setPriority(queueMember.getPriority());
            if (i == this.VISIBLE_QUEUE_NODES) {
                node.setIsVisible(false);
            }
            elementArray.setValue(i, node);
        }
        return elementArray;
    }
    
    public boolean isOverFlow() {
        return this.pqueue != null && this.pqueue.size() > this.VISIBLE_QUEUE_NODES;
    }
    
    public GraphMapsNode getAdjNode() {
        return this.adj_node;
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
        this.visit_sequence = new int[this.commons.getNumberOfNodes()];
        for (int k = 0; k < this.visit_sequence.length; ++k) {
            this.visit_sequence[k] = -1;
        }
        this.setPosition("2.1.1");
        for (int k = 0; k < this.visit_sequence.length; ++k) {
            this.k_marker = k;
            this.setPosition("2.1.2");
            this.visit_sequence[k] = 0;
            this.setPosition("2.1.2.1");
        }
        this.setPosition("2.1.2");
        this.setPosition("2.1.3");
        this.visit_counter = 0;
        this.setPosition("2.2.1");
        this.pqueue = new MinSpanTreePrimPriorityQueue();
        this.setPosition("2.3.1");
        this.setPosition("2.4");
        for (int k = 0; k < this.commons.getNumberOfNodes(); ++k) {
            this.k_marker = k;
            this.setPosition("3.1.1");
            this.setPosition("3.3");
            if (this.visit_sequence[k] == 0) {
                this.setPosition("4");
                this.visit(k);
            }
        }
        this.setPosition("3.1.1");
        this.setPosition("4.1");
        this.setPosition("9");
        this.m_bIsRunning = false;
    }
    
    protected void visit(int k) {
        final GraphNode[] adj_nodes = this.commons.getAdjacencyStructureArray();
        final int[][] adj_matrix = this.commons.getAdjacencyMatrixArray();
        this.k_marker = k;
        this.pqueue.update(k, -1, 0);
        this.addQueueRequest(1, k, 0);
        this.setPosition("5");
        while (!this.pqueue.isEmpty()) {
            this.setPosition("7");
            MinSpanTreePrimPriorityQueueMember pqmember = this.pqueue.peek();
            k = pqmember.getKey();
            this.k_marker = k;
            this.addQueueRequest(2, k, 0);
            this.setPosition("7.2");
            pqmember = this.pqueue.remove();
            this.current_node = pqmember;
            this.makeQuestion(k);
            this.askQuestionsWithoutSetPosition();
            this.setPosition("7.3.1");
            if (this.visit_sequence[k] == 0 || this.visit_sequence[k] == -1) {
                if (pqmember.getParent() != -1) {
                    this.visited_links.addLink(new NodeInfo(pqmember.getParent(), 0, 0), new NodeInfo(k, 0, 0), this.commons.getOmniDirectional(), 0);
                }
                this.setPosition("7.3.1.1");
                final int[] visit_sequence = this.visit_sequence;
                final int n = k;
                final int visit_counter = this.visit_counter + 1;
                this.visit_counter = visit_counter;
                visit_sequence[n] = visit_counter;
                this.setPosition("7.3.1.3");
            }
            GraphNode adjNode = adj_nodes[k];
            this.adj_node = new GraphMapsNode(new Integer(0), 0, 0);
            this.adj_node.setFrom(k);
            this.adj_node.setTo(adjNode.getKey());
            this.createAdjacentNodeVector(adjNode);
            this.setPosition("7.4.1");
            for (; adjNode.getKey() != -1; adjNode = adjNode.getNext(), this.adj_node = new GraphMapsNode(new Integer(0), 0, 0), this.adj_node.setFrom(k), this.adj_node.setTo(adjNode.getKey()), this.createAdjacentNodeVector(adjNode), this.setPosition("7.5.7")) {
                this.setPosition("7.4.2");
                this.setPosition("7.5.1");
                if (this.visit_sequence[adjNode.getKey()] == 0 || this.visit_sequence[adjNode.getKey()] == -1) {
                    this.setPosition("7.5.3");
                    final int nUpdateReturnValue = this.pqueue.update(adjNode.getKey(), k, adj_matrix[k][adjNode.getKey()]);
                    if (nUpdateReturnValue != -1) {
                        this.visit_sequence[adjNode.getKey()] = -1;
                        if (nUpdateReturnValue == -2) {
                            this.addQueueRequest(1, adjNode.getKey(), 0);
                        }
                        else {
                            this.addQueueRequest(3, adjNode.getKey(), nUpdateReturnValue);
                        }
                        this.setPosition("7.5.4");
                        this.setPosition("7.5.6");
                    }
                }
            }
            this.setPosition("7.4.2");
        }
        this.setPosition("7");
        this.setPosition("8");
    }
    
    public void processColorSelection(final ColorSelection colorSelection) {
        final String atribute = colorSelection.getAtributeName();
        if (atribute.equalsIgnoreCase(ColorSelection.BACKGROUND)) {
            this.backgroundColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR)) {
            this.textColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(MinSpanTreePrimGraphMaps.HIGHLIGHT_COLOR)) {
            this.highlightColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(MinSpanTreePrimGraphMaps.NODE_COLOR)) {
            this.unvisitedNodeColor = colorSelection.getColor();
        }
    }
    
    private void addQueueRequest(final int p_type, final int p_key, final int p_param) {
        if (this.queue_requests == null) {
            this.queue_requests = new Vector();
        }
        final QueueRequest queueRequest = new QueueRequest(p_type, p_key);
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
                if (node.getLValue() == queueRequest.getFromKey()) {
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
                        int tempParam = queueRequest.getParam();
                        if (this.isOverFlow()) {
                            tempParam = this.VISIBLE_QUEUE_NODES;
                        }
                        node2 = (TwinNode)elementArray.getElement(tempParam);
                        multipleTween.add(new MoveTween(null, node, node2.getPosition(), node.getPosition(), false, numberOfSteps));
                        int i3 = i2;
                        while (i3 < tempParam) {
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
        final Vector<E> questionsToAsk = new Vector();
        for (int i = 0; i < this.questions.size(); ++i) {
            final Integer tempInt = (Integer)this.questions.elementAt(i);
            questionsToAsk.addElement(new NextVisitQuestion(tempInt.intValue()));
        }
        return questionsToAsk;
    }
    
    protected boolean hasQuestions() {
        return this.questions.size() > 0;
    }
    
    protected void makeQuestion(final int nodeKey) {
        this.questions.addElement(new Integer(nodeKey));
    }
    
    protected void removeAllQuestions() {
        this.questions = new Vector();
    }
    
    public void removeAllAnimationRequests() {
        this.queue_requests = new Vector();
    }
    
    static {
        HIGHLIGHT_COLOR = Messages.getString("MinSpanTreePrimGraphMaps.0");
        NODE_COLOR = Messages.getString("MinSpanTreePrimGraphMaps.1");
        VISIT_MODE_LABEL = Messages.getString("MinSpanTreePrimGraphMaps.3");
    }
}
