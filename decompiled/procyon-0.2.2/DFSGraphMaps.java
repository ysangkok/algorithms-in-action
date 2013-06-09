import localization.*;
import java.util.*;
import java.awt.*;
import com.cim.AIA.*;
import aia.graph.common.*;

public class DFSGraphMaps extends GraphMaps implements ColorSelectionListener, MethodSelectionListener
{
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
    protected static final String HIGHLIGHT_COLOR;
    protected static final String NODE_COLOR;
    protected GraphAlgorithmCommon commons;
    protected boolean m_bIsRunning;
    private int[] visit_sequence;
    private int visit_counter;
    private LinkContainer visited_links;
    private GraphMapsNode adj_node;
    private int k_marker;
    private Vector<E> adjacent_nodes;
    protected Vector<E> questions;
    public static final int UNMARKED = -10;
    public static final String VISIT_MODE_LABEL = "Visit";
    protected boolean m_backMode;
    protected int[] m_stored_data;
    
    public DFSGraphMaps(final AlgorithmThread algorithmThread, final Object data) {
        super(algorithmThread, data);
        this.textColor = Color.black;
        this.highlightColor = Color.yellow;
        this.highlightLinkColor = Color.red;
        this.unvisitedNodeColor = new Color(123, 202, 123);
        this.adjacentNodesColor = new Color(0, 172, 0);
        this.adjacentNodeColor = new Color(174, 255, 174);
        this.visitedNodeColor = Color.gray;
        this.backgroundColor = Color.white;
        this.flagUnvisitedColor = Color.lightGray;
        this.flagVisitedColor = Color.gray;
        this.waitingNodeColor = Color.blue;
        this.m_bIsRunning = false;
        this.visit_sequence = null;
        this.visit_counter = -10;
        this.visited_links = null;
        this.adj_node = null;
        this.k_marker = -10;
        this.adjacent_nodes = null;
        this.questions = new Vector();
        this.m_backMode = false;
        this.m_stored_data = null;
        this.initialise(data);
        final ConfigurationManager cm = ConfigurationManager.getConfigurationManager();
        final ColorSelection highlightSelection = new ColorSelection(this.highlightColor, DFSGraphMaps.HIGHLIGHT_COLOR);
        highlightSelection.addClass(this);
        cm.addColorSelection(highlightSelection);
        final ColorSelection nodeSelection = new ColorSelection(this.unvisitedNodeColor, DFSGraphMaps.NODE_COLOR);
        nodeSelection.addClass(this);
        cm.addColorSelection(nodeSelection);
        cm.addColorSelectionListener(this);
    }
    
    public void initialiseMethods(final String dataDir, final MethodSelectable methodSelectable) {
        final MethodSelection visit = new MethodSelection("Visit", dataDir, Messages.getString("DFSGraphMaps.1"), "4", this.getLogger(), this.getBreakPoint());
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
        this.adj_node = null;
        this.k_marker = -10;
        this.m_bIsRunning = false;
        this.questions = new Vector();
        this.adjacent_nodes = null;
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
    
    public void setStructureShowing(final boolean p_structureShowing) {
        this.commons.setStructureShowing(p_structureShowing);
    }
    
    public void setMatrixShowing(final boolean p_matrixShowing) {
        this.commons.setMatrixShowing(p_matrixShowing);
    }
    
    public boolean getStructureShowing() {
        return this.commons.getStructureShowing();
    }
    
    public boolean getMatrixShowing() {
        return this.commons.getMatrixShowing();
    }
    
    public int getKMarker() {
        return this.k_marker;
    }
    
    private void createAdjacentNodeVector(final GraphNode p_adjnode) {
        GraphNode node = p_adjnode;
        this.adjacent_nodes = new Vector();
        for (; node != null && node.getKey() != -1; node = node.getNext()) {
            this.adjacent_nodes.addElement(new Integer(node.getKey()));
        }
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
    
    public void setKeyChangeMode(final boolean keyChange) {
        this.commons.setKeyChangeMode(keyChange);
    }
    
    public boolean getKeyChangeMode() {
        return this.commons.getKeyChangeMode();
    }
    
    public void keyChange(final int p_from_key, final int p_to_key) {
        this.commons.keyChange(p_from_key, p_to_key);
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
    
    public void setInsertionMode(final boolean p_insertion) {
        this.commons.setInsertionMode(p_insertion);
    }
    
    public boolean getInsertionMode() {
        return this.commons.getInsertionMode();
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
    
    public IntArray getData() {
        return this.commons.getData();
    }
    
    public Vector<E> getNodes() {
        return this.commons.getNodes();
    }
    
    public void setNodeAppearance(final GraphMapsNode node) {
        node.setTextColor(this.textColor);
        node.setBackgroundColor(this.unvisitedNodeColor);
        if (this.adjacent_nodes != null) {
            for (int i = 0; i < this.adjacent_nodes.size(); ++i) {
                final Integer tempInt = (Integer)this.adjacent_nodes.elementAt(i);
                if (tempInt == node.getIntValue()) {
                    node.setBackgroundColor(this.adjacentNodesColor);
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
    
    public Vector<E> getLinks() {
        return this.commons.getLinks();
    }
    
    public void setLinkHighlighted(final Link link) {
        if (this.visited_links != null && this.visited_links.findLink(link.getStart(), link.getEnd()) != null) {
            link.setHighlighted(true);
            link.setLinkColor(this.highlightLinkColor);
        }
    }
    
    public void setLinkAppearance(final Link link) {
        link.setUseWeight(false);
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
    
    public Color getAdjacentNodesColor() {
        return this.adjacentNodesColor;
    }
    
    public Color getAdjacentNodeColor() {
        return this.adjacentNodeColor;
    }
    
    public Color getUnvisitedNodeColor() {
        return this.unvisitedNodeColor;
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
            if (i == this.k_marker) {
                node.setBackgroundColor(this.highlightColor);
            }
            node.setTextColor(this.textColor);
            node.setHeight(20);
            elementArray.setValue(i, node);
        }
        return elementArray;
    }
    
    public GraphMapsNode getAdjNode() {
        return this.adj_node;
    }
    
    protected void run() {
        this.commons.algorithmStartRunInitialisations();
        if (this.m_backMode) {
            this.m_backMode = false;
            this.initialise(this.m_stored_data);
        }
        this.visit_sequence = null;
        this.visited_links = new LinkContainer();
        this.m_bIsRunning = true;
        this.setPosition("1");
        this.visit_sequence = new int[this.commons.getNumberOfNodes()];
        for (int k = 0; k < this.commons.getNumberOfNodes(); ++k) {
            this.visit_sequence[k] = -1;
        }
        this.setPosition("1.2.1");
        for (int k = 0; k < this.commons.getNumberOfNodes(); ++k) {
            this.k_marker = k;
            this.setPosition("1.2.3");
            this.visit_sequence[k] = 0;
            this.setPosition("1.2.4");
        }
        this.setPosition("1.2.3");
        this.setPosition("1.2.5");
        this.visit_counter = 0;
        this.setPosition("1.3.1");
        this.setPosition("1.4");
        for (int k = 0; k < this.commons.getNumberOfNodes(); ++k) {
            this.k_marker = k;
            this.setPosition("2.1");
            this.setPosition("3.1");
            if (this.visit_sequence[k] == 0) {
                this.setPosition("4");
                this.visit(k);
            }
        }
        this.setPosition("2.1");
        this.setPosition("9");
        this.m_bIsRunning = false;
    }
    
    protected void visit(final int k) {
        final GraphNode[] adj_structure = this.commons.getAdjacencyStructureArray();
        this.k_marker = k;
        final int[] visit_sequence = this.visit_sequence;
        final int visit_counter = this.visit_counter + 1;
        this.visit_counter = visit_counter;
        visit_sequence[k] = visit_counter;
        this.setPosition("5.1");
        GraphNode adjNode = adj_structure[k];
        this.adj_node = new GraphMapsNode(new Integer(0), 0, 0);
        this.adj_node.setFrom(k);
        this.adj_node.setTo(adjNode.getKey());
        this.createAdjacentNodeVector(adjNode);
        this.setPosition("6.1");
        for (; adjNode.getKey() != -1; adjNode = adjNode.getNext(), this.adj_node = new GraphMapsNode(new Integer(0), 0, 0), this.adj_node.setFrom(k), this.adj_node.setTo(adjNode.getKey()), this.setPosition("7.3")) {
            this.setPosition("6.2");
            this.setPosition("7.1.1");
            if (this.visit_sequence[adjNode.getKey()] == 0) {
                this.makeQuestion(adjNode.getKey());
                this.askQuestionsWithoutSetPosition();
                this.setPosition("7.2");
                this.visited_links.addLink(new NodeInfo(k, 0, 0), new NodeInfo(adjNode.getKey(), 0, 0), this.commons.getOmniDirectional(), 0);
                this.visit(adjNode.getKey());
                this.k_marker = k;
                this.adj_node = new GraphMapsNode(new Integer(0), 0, 0);
                this.adj_node.setFrom(k);
                this.adj_node.setTo(adjNode.getKey());
                this.createAdjacentNodeVector(adjNode);
            }
        }
        this.setPosition("6.2");
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
        else if (atribute.equalsIgnoreCase(DFSGraphMaps.HIGHLIGHT_COLOR)) {
            this.highlightColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(DFSGraphMaps.NODE_COLOR)) {
            this.unvisitedNodeColor = colorSelection.getColor();
        }
    }
    
    public MultipleTween generateTweens(final Paintable paintable, final Object parameter, final int numberOfSteps) {
        return null;
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
    }
    
    static {
        HIGHLIGHT_COLOR = Messages.getString("DFSGraphMaps.0");
        NODE_COLOR = Messages.getString("DFSGraphMaps.2");
    }
}
