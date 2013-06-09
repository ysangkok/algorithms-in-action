import localization.*;
import java.util.*;
import java.awt.*;
import com.cim.AIA.*;
import aia.graph.common.*;

public class TransclosureGraphMaps extends GraphMaps implements ColorSelectionListener
{
    protected Color textColor;
    protected Color highlightLinkColor;
    protected Color highlightColor;
    protected Color unvisitedNodeColor;
    protected Color backgroundColor;
    protected Color xColor;
    protected Color yColor;
    protected Color jColor;
    protected Color xyColor;
    protected Color yjColor;
    protected Color xjColor;
    protected static final String HIGHLIGHT_COLOR;
    protected static final String NODE_COLOR;
    protected GraphAlgorithmCommon commons;
    protected boolean m_bIsRunning;
    protected int m_x;
    protected int m_y;
    protected int m_j;
    protected boolean m_bInSecondLoop;
    protected Link link_just_added;
    protected LinkContainer added_links;
    protected Vector<E> questions;
    public static final int UNMARKED = -10;
    protected boolean m_backMode;
    protected int[] m_stored_data;
    
    public TransclosureGraphMaps(final AlgorithmThread algorithmThread, final Object data) {
        super(algorithmThread, data);
        this.textColor = Color.black;
        this.highlightLinkColor = Color.red;
        this.highlightColor = Color.yellow;
        this.unvisitedNodeColor = new Color(123, 202, 123);
        this.backgroundColor = Color.white;
        this.xColor = new Color(255, 128, 128);
        this.yColor = new Color(128, 255, 128);
        this.jColor = new Color(128, 128, 255);
        this.xyColor = new Color(25, 35, 209);
        this.yjColor = new Color(0, 187, 255);
        this.xjColor = new Color(0, 255, 255);
        this.m_bIsRunning = false;
        this.m_x = -10;
        this.m_y = -10;
        this.m_j = -10;
        this.m_bInSecondLoop = false;
        this.link_just_added = null;
        this.added_links = null;
        this.questions = null;
        this.m_backMode = false;
        this.m_stored_data = null;
        this.initialise(data);
        final ConfigurationManager cm = ConfigurationManager.getConfigurationManager();
        final ColorSelection highlightSelection = new ColorSelection(this.highlightColor, TransclosureGraphMaps.HIGHLIGHT_COLOR);
        highlightSelection.addClass(this);
        cm.addColorSelection(highlightSelection);
        final ColorSelection nodeSelection = new ColorSelection(this.unvisitedNodeColor, TransclosureGraphMaps.NODE_COLOR);
        nodeSelection.addClass(this);
        cm.addColorSelection(nodeSelection);
        cm.addColorSelectionListener(this);
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
        this.m_x = -10;
        this.m_y = -10;
        this.m_j = -10;
        this.m_bInSecondLoop = false;
        this.added_links = null;
        this.questions = new Vector();
        this.link_just_added = null;
        this.m_bIsRunning = false;
        if (this.commons == null) {
            this.commons = new GraphAlgorithmCommon(this, data);
        }
        else {
            this.commons.setData(data);
        }
        this.setStructureShowing(false);
    }
    
    public void setNodePosition(final int p_nodeIndex, final int x, final int y) {
        this.commons.setNodePosition(p_nodeIndex, x, y);
    }
    
    public void setStructureShowing(final boolean p_structureShowing) {
        this.commons.setStructureShowing(p_structureShowing);
    }
    
    public void setMatrixShowing(final boolean m_matrixShowing) {
        this.commons.setMatrixShowing(m_matrixShowing);
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
    
    public void setKeyChangeMode(final boolean key_change) {
        this.commons.setKeyChangeMode(key_change);
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
    
    public void setNodeAppearance(final GraphMapsNode node) {
        node.setTextColor(this.textColor);
        node.setBackgroundColor(this.unvisitedNodeColor);
    }
    
    public boolean isInSecondLoop() {
        return this.m_bInSecondLoop;
    }
    
    public int getX() {
        return this.m_x;
    }
    
    public int getY() {
        return this.m_y;
    }
    
    public int getJ() {
        return this.m_j;
    }
    
    public Vector<E> getNodes() {
        return this.commons.getNodes();
    }
    
    private boolean isLinkEqual(final Link link, final int p_start, final int p_end) {
        return (link.getStart().getKey() == p_start && link.getEnd().getKey() == p_end) || (link.getOmniDirectional() && link.getEnd().getKey() == p_start && link.getStart().getKey() == p_end);
    }
    
    public void setLinkHighlighted(final Link link) {
        if (this.isLinkEqual(link, this.m_x, this.m_y)) {
            link.setHighlighted(true);
            link.setLinkColor(this.xyColor);
        }
        if (this.isLinkEqual(link, this.m_y, this.m_j)) {
            link.setHighlighted(true);
            link.setLinkColor(this.yjColor);
            if (this.isLinkEqual(link, this.m_x, this.m_y)) {
                link.setLinkColor(this.xyColor);
            }
        }
    }
    
    public void setLinkAppearance(final Link link) {
        link.setUseWeight(false);
        if (this.link_just_added != null && link.isEqualTo(this.link_just_added)) {
            link.setLinkColor(this.backgroundColor);
        }
    }
    
    public Vector<E> getLinks() {
        return this.commons.getLinks();
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
    
    public Color getXColor() {
        return this.xColor;
    }
    
    public Color getYColor() {
        return this.yColor;
    }
    
    public Color getJColor() {
        return this.jColor;
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
        Color matrixNodeColor = this.unvisitedNodeColor;
        node.setTextColor(this.textColor);
        if (node.getFrom() == this.m_x && node.getTo() == this.m_y) {
            matrixNodeColor = this.xyColor;
        }
        if (node.getFrom() == this.m_y && node.getTo() == this.m_j) {
            matrixNodeColor = this.yjColor;
        }
        if (node.getFrom() == this.m_x && node.getTo() == this.m_j) {
            matrixNodeColor = this.xjColor;
        }
        node.setBackgroundColor(matrixNodeColor);
        if (node.getFrom() == this.m_x && node.getTo() == this.m_y) {
            node.setTextColor(Color.white);
            if ((node.getFrom() == this.m_x && node.getTo() == this.m_y) || (node.getFrom() == this.m_y && node.getTo() == this.m_j)) {
                node.setTextColor(this.textColor);
            }
        }
    }
    
    public ElementArray getAdjacencyStructure() {
        return this.commons.getAdjacencyStructure();
    }
    
    public void setStructureNodeAppearance(final GraphMapsNode node) {
    }
    
    protected void run() {
        this.commons.algorithmStartRunInitialisations();
        if (this.m_backMode) {
            this.m_backMode = false;
            this.initialise(this.m_stored_data);
            this.m_stored_data = null;
        }
        final int[][] adj_matrix = this.commons.getAdjacencyMatrixArray();
        this.added_links = new LinkContainer();
        this.m_bIsRunning = true;
        this.setPosition("1");
        this.m_y = 0;
        while (this.m_y < this.commons.getNumberOfNodes()) {
            this.setPosition("2");
            this.m_x = 0;
            while (this.m_x < this.commons.getNumberOfNodes()) {
                this.setPosition("3");
                this.setPosition("5");
                if (adj_matrix[this.m_x][this.m_y] != 0) {
                    this.m_j = 0;
                    while (this.m_j < this.commons.getNumberOfNodes()) {
                        this.m_bInSecondLoop = true;
                        this.link_just_added = null;
                        this.setPosition("6");
                        this.setPosition("8");
                        if (adj_matrix[this.m_y][this.m_j] != 0) {
                            this.setPosition("9.1");
                            if (this.m_x != this.m_j) {
                                if (adj_matrix[this.m_x][this.m_j] != 1) {
                                    this.added_links.addLink(new NodeInfo(this.m_x, 0, 0), new NodeInfo(this.m_j, 0, 0), this.commons.getOmniDirectional(), 0);
                                    this.link_just_added = new Link(new NodeInfo(this.m_x, 0, 0), new NodeInfo(this.m_j, 0, 0), this.commons.getOmniDirectional());
                                    this.makeQuestion(this.m_x, this.m_j);
                                    this.askQuestionsWithoutSetPosition();
                                    adj_matrix[this.m_x][this.m_j] = 1;
                                }
                                if (this.commons.getOmniDirectional() && adj_matrix[this.m_j][this.m_x] != 1) {
                                    adj_matrix[this.m_j][this.m_x] = 1;
                                }
                                this.commons.setAdjacencyMatrixArray(adj_matrix);
                                this.commons.generateAdjacencyStructure();
                                this.setPosition("9.2");
                            }
                        }
                        this.m_j = this.m_j + 1;
                    }
                    this.link_just_added = null;
                    this.m_bInSecondLoop = false;
                    this.setPosition("6");
                }
                this.m_x = this.m_x + 1;
            }
            this.setPosition("3");
            this.m_y = this.m_y + 1;
        }
        this.setPosition("2");
        this.setPosition("10");
        this.m_bIsRunning = false;
    }
    
    public void processColorSelection(final ColorSelection colorSelection) {
        final String atribute = colorSelection.getAtributeName();
        if (atribute.equalsIgnoreCase(ColorSelection.BACKGROUND)) {
            this.backgroundColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR)) {
            this.textColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(TransclosureGraphMaps.HIGHLIGHT_COLOR)) {
            this.highlightColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(TransclosureGraphMaps.NODE_COLOR)) {
            this.unvisitedNodeColor = colorSelection.getColor();
        }
    }
    
    public MultipleTween generateTweens(final Paintable paintable, final Line p_LinkJustAdded, final Vector<E> p_nodes, final int numberOfSteps) {
        Point p1 = null;
        Point p2 = null;
        if (this.link_just_added == null) {
            return null;
        }
        final MultipleTween tweens = new MultipleTween(paintable);
        for (int i = 0; i < p_nodes.size(); ++i) {
            final GraphMapsNode node = (GraphMapsNode)p_nodes.elementAt(i);
            if (node.getIntValue() == this.link_just_added.getStart().getKey()) {
                p1 = new Point(node.getX() + 10, node.getY() + 10);
            }
            if (node.getIntValue() == this.link_just_added.getEnd().getKey()) {
                p2 = new Point(node.getX() + 10, node.getY() + 10);
            }
        }
        if (p1 == null || p2 == null) {
            return null;
        }
        p_LinkJustAdded.setStartPosition(p1);
        p_LinkJustAdded.setEndPosition(p2);
        p_LinkJustAdded.setColor(this.xjColor);
        p_LinkJustAdded.showAsThick(true);
        if (!this.commons.getOmniDirectional()) {
            p_LinkJustAdded.showArrowHead(true);
            p_LinkJustAdded.setDistanceFromStartForArrowHead(p_LinkJustAdded.getLength() - 15);
        }
        tweens.add(new MoveTween(paintable, p_LinkJustAdded, p1, p2, false, numberOfSteps));
        return tweens;
    }
    
    public MultipleTween generateTweens(final Paintable paintable, final Object param, final int numberOfSteps) {
        return null;
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
        this.link_just_added = null;
    }
    
    static {
        HIGHLIGHT_COLOR = Messages.getString("TransclosureGraphMaps.0");
        NODE_COLOR = Messages.getString("TransclosureGraphMaps.1");
    }
}
