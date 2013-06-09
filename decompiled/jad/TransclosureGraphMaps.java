// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TransclosureGraphMaps.java

import aia.graph.common.*;
import com.cim.AIA.*;
import java.awt.Color;
import java.awt.Point;
import java.util.Vector;
import localization.Messages;

public class TransclosureGraphMaps extends GraphMaps
    implements ColorSelectionListener
{

    public TransclosureGraphMaps(AlgorithmThread algorithmThread, Object data)
    {
        super(algorithmThread, data);
        textColor = Color.black;
        highlightLinkColor = Color.red;
        highlightColor = Color.yellow;
        unvisitedNodeColor = new Color(123, 202, 123);
        backgroundColor = Color.white;
        xColor = new Color(255, 128, 128);
        yColor = new Color(128, 255, 128);
        jColor = new Color(128, 128, 255);
        xyColor = new Color(25, 35, 209);
        yjColor = new Color(0, 187, 255);
        xjColor = new Color(0, 255, 255);
        m_bIsRunning = false;
        m_x = -10;
        m_y = -10;
        m_j = -10;
        m_bInSecondLoop = false;
        link_just_added = null;
        added_links = null;
        questions = null;
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
        m_x = -10;
        m_y = -10;
        m_j = -10;
        m_bInSecondLoop = false;
        added_links = null;
        questions = new Vector();
        link_just_added = null;
        m_bIsRunning = false;
        if(commons == null)
            commons = new GraphAlgorithmCommon(this, data);
        else
            commons.setData(data);
        setStructureShowing(false);
    }

    public void setNodePosition(int p_nodeIndex, int x, int y)
    {
        commons.setNodePosition(p_nodeIndex, x, y);
    }

    public void setStructureShowing(boolean p_structureShowing)
    {
        commons.setStructureShowing(p_structureShowing);
    }

    public void setMatrixShowing(boolean m_matrixShowing)
    {
        commons.setMatrixShowing(m_matrixShowing);
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

    public void setKeyChangeMode(boolean key_change)
    {
        commons.setKeyChangeMode(key_change);
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

    public void setNodeAppearance(GraphMapsNode node)
    {
        node.setTextColor(textColor);
        node.setBackgroundColor(unvisitedNodeColor);
    }

    public boolean isInSecondLoop()
    {
        return m_bInSecondLoop;
    }

    public int getX()
    {
        return m_x;
    }

    public int getY()
    {
        return m_y;
    }

    public int getJ()
    {
        return m_j;
    }

    public Vector getNodes()
    {
        return commons.getNodes();
    }

    private boolean isLinkEqual(Link link, int p_start, int p_end)
    {
        if(link.getStart().getKey() == p_start && link.getEnd().getKey() == p_end)
            return true;
        return link.getOmniDirectional() && link.getEnd().getKey() == p_start && link.getStart().getKey() == p_end;
    }

    public void setLinkHighlighted(Link link)
    {
        if(isLinkEqual(link, m_x, m_y))
        {
            link.setHighlighted(true);
            link.setLinkColor(xyColor);
        }
        if(isLinkEqual(link, m_y, m_j))
        {
            link.setHighlighted(true);
            link.setLinkColor(yjColor);
            if(isLinkEqual(link, m_x, m_y))
                link.setLinkColor(xyColor);
        }
    }

    public void setLinkAppearance(Link link)
    {
        link.setUseWeight(false);
        if(link_just_added != null && link.isEqualTo(link_just_added))
            link.setLinkColor(backgroundColor);
    }

    public Vector getLinks()
    {
        return commons.getLinks();
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

    public Color getXColor()
    {
        return xColor;
    }

    public Color getYColor()
    {
        return yColor;
    }

    public Color getJColor()
    {
        return jColor;
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
        Color matrixNodeColor = unvisitedNodeColor;
        node.setTextColor(textColor);
        if(node.getFrom() == m_x && node.getTo() == m_y)
            matrixNodeColor = xyColor;
        if(node.getFrom() == m_y && node.getTo() == m_j)
            matrixNodeColor = yjColor;
        if(node.getFrom() == m_x && node.getTo() == m_j)
            matrixNodeColor = xjColor;
        node.setBackgroundColor(matrixNodeColor);
        if(node.getFrom() == m_x && node.getTo() == m_y)
        {
            node.setTextColor(Color.white);
            if(node.getFrom() == m_x && node.getTo() == m_y || node.getFrom() == m_y && node.getTo() == m_j)
                node.setTextColor(textColor);
        }
    }

    public ElementArray getAdjacencyStructure()
    {
        return commons.getAdjacencyStructure();
    }

    public void setStructureNodeAppearance(GraphMapsNode graphmapsnode)
    {
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
        int adj_matrix[][] = commons.getAdjacencyMatrixArray();
        added_links = new LinkContainer();
        m_bIsRunning = true;
        setPosition("1");
        for(m_y = 0; m_y < commons.getNumberOfNodes(); m_y++)
        {
            setPosition("2");
            for(m_x = 0; m_x < commons.getNumberOfNodes(); m_x++)
            {
                setPosition("3");
                setPosition("5");
                if(adj_matrix[m_x][m_y] == 0)
                    continue;
                for(m_j = 0; m_j < commons.getNumberOfNodes(); m_j++)
                {
                    m_bInSecondLoop = true;
                    link_just_added = null;
                    setPosition("6");
                    setPosition("8");
                    if(adj_matrix[m_y][m_j] == 0)
                        continue;
                    setPosition("9.1");
                    if(m_x == m_j)
                        continue;
                    if(adj_matrix[m_x][m_j] != 1)
                    {
                        added_links.addLink(new NodeInfo(m_x, 0, 0), new NodeInfo(m_j, 0, 0), commons.getOmniDirectional(), 0);
                        link_just_added = new Link(new NodeInfo(m_x, 0, 0), new NodeInfo(m_j, 0, 0), commons.getOmniDirectional());
                        makeQuestion(m_x, m_j);
                        askQuestionsWithoutSetPosition();
                        adj_matrix[m_x][m_j] = 1;
                    }
                    if(commons.getOmniDirectional() && adj_matrix[m_j][m_x] != 1)
                        adj_matrix[m_j][m_x] = 1;
                    commons.setAdjacencyMatrixArray(adj_matrix);
                    commons.generateAdjacencyStructure();
                    setPosition("9.2");
                }

                link_just_added = null;
                m_bInSecondLoop = false;
                setPosition("6");
            }

            setPosition("3");
        }

        setPosition("2");
        setPosition("10");
        m_bIsRunning = false;
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

    public MultipleTween generateTweens(Paintable paintable, Line p_LinkJustAdded, Vector p_nodes, int numberOfSteps)
    {
        Point p1 = null;
        Point p2 = null;
        if(link_just_added == null)
            return null;
        MultipleTween tweens = new MultipleTween(paintable);
        for(int i = 0; i < p_nodes.size(); i++)
        {
            GraphMapsNode node = (GraphMapsNode)p_nodes.elementAt(i);
            if(node.getIntValue() == link_just_added.getStart().getKey())
                p1 = new Point(node.getX() + 10, node.getY() + 10);
            if(node.getIntValue() == link_just_added.getEnd().getKey())
                p2 = new Point(node.getX() + 10, node.getY() + 10);
        }

        if(p1 == null || p2 == null)
            return null;
        p_LinkJustAdded.setStartPosition(p1);
        p_LinkJustAdded.setEndPosition(p2);
        p_LinkJustAdded.setColor(xjColor);
        p_LinkJustAdded.showAsThick(true);
        if(!commons.getOmniDirectional())
        {
            p_LinkJustAdded.showArrowHead(true);
            p_LinkJustAdded.setDistanceFromStartForArrowHead(p_LinkJustAdded.getLength() - 15);
        }
        tweens.add(new MoveTween(paintable, p_LinkJustAdded, p1, p2, false, numberOfSteps));
        return tweens;
    }

    public MultipleTween generateTweens(Paintable paintable, Object param, int numberOfSteps)
    {
        return null;
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
        link_just_added = null;
    }

    public volatile Object getData()
    {
        return getData();
    }

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
    protected static final String HIGHLIGHT_COLOR = Messages.getString("TransclosureGraphMaps.0");
    protected static final String NODE_COLOR = Messages.getString("TransclosureGraphMaps.1");
    protected GraphAlgorithmCommon commons;
    protected boolean m_bIsRunning;
    protected int m_x;
    protected int m_y;
    protected int m_j;
    protected boolean m_bInSecondLoop;
    protected Link link_just_added;
    protected LinkContainer added_links;
    protected Vector questions;
    public static final int UNMARKED = -10;
    protected boolean m_backMode;
    protected int m_stored_data[];

}
