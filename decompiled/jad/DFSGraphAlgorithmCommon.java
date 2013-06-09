// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DFSGraphAlgorithmCommon.java

import aia.graph.common.*;
import com.cim.AIA.ElementArray;
import com.cim.AIA.IntArray;
import com.cim.common.MessageDialog;
import java.awt.Point;
import java.io.PrintStream;
import java.util.Vector;
import localization.Messages;

public class DFSGraphAlgorithmCommon
{

    DFSGraphAlgorithmCommon(GraphMaps p_algorithm, Object p_data)
    {
        m_bOmniDirectional = true;
        m_nZoomFactor = 1;
        m_bInsertionMode = false;
        m_bDeleteMode = false;
        m_bKeyChangeMode = false;
        m_structureShowing = true;
        m_matrixShowing = true;
        adj_matrix = (int[][])null;
        adj_structure = null;
        m_editLinkStep = 0;
        m_startNode = -10;
        m_endNode = -10;
        m_linkWeight = null;
        m_editLinkStarted = false;
        m_algorithm = p_algorithm;
        data = (int[])(int[])p_data;
        generateNodeInfo();
        generateAdjacencyMatrix();
        generateAdjacencyStructure();
        outputDataArray();
        m_structureShowing = true;
        m_matrixShowing = true;
    }

    public void algorithmStartRunInitialisations()
    {
        endEditLink();
        setKeyChangeMode(false);
        setInsertionMode(false);
        setDeleteMode(false);
    }

    public void setData(Object p_data)
    {
        data = (int[])(int[])p_data;
        generateNodeInfo();
        generateAdjacencyMatrix();
        generateAdjacencyStructure();
        outputDataArray();
    }

    public void outputDataArray()
    {
        System.out.println("******Graph Data******");
        for(int i = 0; i < data.length; i++)
        {
            System.out.print(data[i]);
            if(i < data.length - 1)
                System.out.print(", ");
        }

        System.out.println();
        System.out.println("----------------------");
        System.out.println();
    }

    public void startEditLink(Integer p_link_weight)
    {
        if(m_algorithm.getIsRunning())
            return;
        m_editLinkStarted = true;
        m_linkWeight = p_link_weight;
        m_startNode = -10;
        m_endNode = -10;
        if(messageDialog != null)
            messageDialog.dispose();
        messageDialog = new MessageDialog(Messages.getString("DFSGraphAlgorithmCommon.3"), false, false);
        messageDialog.setTitle(Messages.getString("DFSGraphAlgorithmCommon.4"));
        messageDialog.setVisible(true);
        m_editLinkStep = 1;
    }

    public void setStartNode(int p_key)
    {
        m_startNode = p_key;
        if(messageDialog != null)
            messageDialog.dispose();
        messageDialog = new MessageDialog(Messages.getString("DFSGraphAlgorithmCommon.5"), false, false);
        messageDialog.setTitle(Messages.getString("DFSGraphAlgorithmCommon.6"));
        messageDialog.setVisible(true);
        m_editLinkStep++;
        m_algorithm.repaint();
    }

    public int getStartNode()
    {
        return m_startNode;
    }

    public void setEndNode(int p_key)
    {
        m_endNode = p_key;
        m_editLinkStep++;
        if(!needPromptForLinkWeight())
            setLinkWeight(m_linkWeight.intValue());
        m_algorithm.repaint();
    }

    public int getEndNode()
    {
        return m_endNode;
    }

    public int getCurrentLinkLinkWeight()
    {
        return adj_matrix[m_startNode][m_endNode];
    }

    public void setLinkWeight(int p_link_weight)
    {
        GraphMapsNode node = new GraphMapsNode(new Integer(getCurrentLinkLinkWeight()), 0, 0);
        node.setFrom(m_startNode);
        node.setTo(m_endNode);
        editLink(node, p_link_weight);
        endEditLink();
    }

    public void endEditLink()
    {
        m_editLinkStarted = false;
        m_startNode = -10;
        m_endNode = -10;
        if(messageDialog != null)
            messageDialog.dispose();
        messageDialog = null;
        m_algorithm.repaint();
    }

    public boolean needPromptForLinkWeight()
    {
        return m_linkWeight == null;
    }

    public boolean editLinkStarted()
    {
        return m_editLinkStarted;
    }

    public int getEditLinkStep()
    {
        return m_editLinkStep;
    }

    public void setStructureShowing(boolean p_structureShowing)
    {
        m_structureShowing = p_structureShowing;
        m_algorithm.repaint();
    }

    public void setMatrixShowing(boolean p_matrixShowing)
    {
        m_matrixShowing = p_matrixShowing;
        m_algorithm.repaint();
    }

    public boolean getStructureShowing()
    {
        return m_structureShowing;
    }

    public boolean getMatrixShowing()
    {
        return m_matrixShowing;
    }

    public void generateAdjacencyMatrix()
    {
        adj_matrix = new int[m_nNumberOfNodes][m_nNumberOfNodes];
        for(int i = 0; i < m_nNumberOfNodes; i++)
        {
            for(int j = 0; j < m_nNumberOfNodes; j++)
                adj_matrix[i][j] = 0;

        }

        for(int pos = m_nNumberOfNodes * 2 + 1; pos + 2 < data.length; pos += 3)
        {
            int x = data[pos];
            int y = data[pos + 1];
            int weight = data[pos + 2];
            adj_matrix[x][y] = weight;
            if(m_bOmniDirectional)
                adj_matrix[y][x] = weight;
        }

    }

    public void generateAdjacencyStructure()
    {
        int pos = 0;
        adj_structure = new GraphNode[m_nNumberOfNodes];
        for(int i = 0; i < m_nNumberOfNodes; i++)
        {
            GraphNode tempGraphNode = new GraphNode();
            tempGraphNode.setNext(tempGraphNode);
            tempGraphNode.setKey(-1);
            adj_structure[i] = tempGraphNode;
        }

        for(int i = 0; i < m_nNumberOfNodes; i++)
        {
            for(int j = 0; j < m_nNumberOfNodes; j++)
                if(j != i && adj_matrix[i][j] != 0)
                {
                    GraphNode tempGraphNode = new GraphNode();
                    tempGraphNode.setKey(j);
                    tempGraphNode.setNext(adj_structure[i]);
                    adj_structure[i] = tempGraphNode;
                }

        }

    }

    public void setNodePosition(int p_nodeIndex, int x, int y)
    {
        int nx = x / m_nZoomFactor;
        int ny = y / m_nZoomFactor;
        nx = (int)(Math.floor(x / (m_nZoomFactor * 5)) * 5D);
        ny = (int)(Math.floor(y / (m_nZoomFactor * 5)) * 5D);
        NodeInfo nodeInfo = findNodeInfo(p_nodeIndex);
        if(nodeInfo != null)
        {
            nodeInfo.setX(nx);
            nodeInfo.setY(ny);
            data[nodeInfo.getKey() * 2 + 1] = nx;
            data[nodeInfo.getKey() * 2 + 2] = ny;
            m_algorithm.repaint();
        }
    }

    public void setOmniDirectional(boolean p_omniDir)
    {
        if(m_algorithm.getIsRunning())
        {
            return;
        } else
        {
            m_bOmniDirectional = p_omniDir;
            setData(data);
            m_algorithm.repaint();
            return;
        }
    }

    public void zoomIn()
    {
        if(m_nZoomFactor == 10)
        {
            return;
        } else
        {
            m_nZoomFactor++;
            m_algorithm.repaint();
            return;
        }
    }

    public void zoomOut()
    {
        if(m_nZoomFactor == 1)
        {
            return;
        } else
        {
            m_nZoomFactor--;
            m_algorithm.repaint();
            return;
        }
    }

    public void setKeyChangeMode(boolean p_keyChange)
    {
        if(m_algorithm.getIsRunning())
            return;
        m_bKeyChangeMode = p_keyChange;
        if(messageDialog != null)
            messageDialog.dispose();
        if(m_bKeyChangeMode)
        {
            messageDialog = new MessageDialog(Messages.getString("DFSGraphAlgorithmCommon.7"), false, false);
            messageDialog.setTitle(Messages.getString("DFSGraphAlgorithmCommon.8"));
            messageDialog.setVisible(true);
        }
    }

    public boolean getKeyChangeMode()
    {
        return m_bKeyChangeMode;
    }

    public void keyChange(int p_from_key, int p_to_key)
    {
        if(p_to_key >= m_nNumberOfNodes || p_to_key < 0)
            return;
        int x = data[p_from_key * 2 + 1];
        int y = data[p_from_key * 2 + 2];
        data[p_from_key * 2 + 1] = data[p_to_key * 2 + 1];
        data[p_from_key * 2 + 2] = data[p_to_key * 2 + 2];
        data[p_to_key * 2 + 1] = x;
        data[p_to_key * 2 + 2] = y;
        for(int pos = m_nNumberOfNodes * 2 + 1; pos + 2 < data.length; pos += 3)
        {
            if(data[pos] == p_from_key)
                data[pos] = p_to_key;
            else
            if(data[pos] == p_to_key)
                data[pos] = p_from_key;
            if(data[pos + 1] == p_from_key)
            {
                data[pos + 1] = p_to_key;
                continue;
            }
            if(data[pos + 1] == p_to_key)
                data[pos + 1] = p_from_key;
        }

        setData(data);
        m_algorithm.repaint();
    }

    public void setInsertionMode(boolean p_insertion)
    {
        if(m_algorithm.getIsRunning())
            return;
        m_bInsertionMode = p_insertion;
        if(messageDialog != null)
            messageDialog.dispose();
        if(m_bInsertionMode)
        {
            messageDialog = new MessageDialog(Messages.getString("DFSGraphAlgorithmCommon.9"), false, false);
            messageDialog.setTitle(Messages.getString("DFSGraphAlgorithmCommon.10"));
            messageDialog.setVisible(true);
        }
    }

    public boolean getInsertionMode()
    {
        return m_bInsertionMode;
    }

    public void insertNewNode(int x, int y)
    {
        int nx = x / m_nZoomFactor;
        int ny = y / m_nZoomFactor;
        nx = (int)(Math.floor(x / (m_nZoomFactor * 5)) * 5D);
        ny = (int)(Math.floor(y / (m_nZoomFactor * 5)) * 5D);
        if(m_nodeInfo == null)
        {
            m_nodeInfo = new Vector();
            m_nNumberOfNodes = 0;
            data = new int[1];
            data[0] = 0;
        }
        m_nodeInfo.addElement(new NodeInfo(m_nNumberOfNodes, x / m_nZoomFactor, y / m_nZoomFactor));
        int tempInt[] = new int[data.length + 2];
        for(int i = 0; i < m_nNumberOfNodes; i++)
        {
            tempInt[i * 2 + 1] = data[i * 2 + 1];
            tempInt[i * 2 + 2] = data[i * 2 + 2];
        }

        tempInt[m_nNumberOfNodes * 2 + 1] = nx;
        tempInt[m_nNumberOfNodes * 2 + 2] = ny;
        for(int i = m_nNumberOfNodes * 2 + 1; i < data.length; i++)
            tempInt[i + 2] = data[i];

        m_nNumberOfNodes++;
        tempInt[0] = m_nNumberOfNodes;
        data = new int[tempInt.length];
        for(int i = 0; i < tempInt.length; i++)
            data[i] = tempInt[i];

        setData(data);
        m_algorithm.repaint();
    }

    public void setDeleteMode(boolean p_deleteMode)
    {
        if(m_algorithm.getIsRunning())
            return;
        m_bDeleteMode = p_deleteMode;
        if(messageDialog != null)
            messageDialog.dispose();
        if(m_bDeleteMode)
        {
            messageDialog = new MessageDialog(Messages.getString("DFSGraphAlgorithmCommon.11"), false, false);
            messageDialog.setTitle(Messages.getString("DFSGraphAlgorithmCommon.12"));
            messageDialog.setVisible(true);
        }
    }

    public boolean getDeleteMode()
    {
        return m_bDeleteMode;
    }

    public void deleteNode(int p_node)
    {
        if(m_algorithm.getIsRunning())
            return;
        Vector tempData = new Vector();
        if(m_nNumberOfNodes == 0 || p_node >= m_nNumberOfNodes || p_node < 0)
            return;
        NodeInfo nodeInfo = findNodeInfo(p_node);
        m_nodeInfo.removeElement(nodeInfo);
        int tempInt[] = new int[data.length - 2];
        for(int i = 0; i < m_nNumberOfNodes; i++)
        {
            if(i == p_node)
                continue;
            if(i > p_node)
            {
                tempInt[i * 2 - 1] = data[i * 2 + 1];
                tempInt[i * 2] = data[i * 2 + 2];
            } else
            {
                tempInt[i * 2 + 1] = data[i * 2 + 1];
                tempInt[i * 2 + 2] = data[i * 2 + 2];
            }
        }

        for(int i = m_nNumberOfNodes * 2 + 1; i < data.length; i++)
            tempInt[i - 2] = data[i];

        tempInt[0] = m_nNumberOfNodes - 1;
        data = new int[tempInt.length];
        for(int i = 0; i < tempInt.length; i++)
            data[i] = tempInt[i];

        m_nNumberOfNodes--;
        for(int pos = m_nNumberOfNodes * 2 + 1; pos + 2 < data.length; pos += 3)
        {
            if(data[pos] == p_node || data[pos + 1] == p_node)
                continue;
            if(data[pos] > p_node)
                tempData.addElement(new Integer(data[pos] - 1));
            else
                tempData.addElement(new Integer(data[pos]));
            if(data[pos + 1] > p_node)
                tempData.addElement(new Integer(data[pos + 1] - 1));
            else
                tempData.addElement(new Integer(data[pos + 1]));
            tempData.addElement(new Integer(data[pos + 2]));
        }

        tempInt = new int[m_nNumberOfNodes * 2 + 1 + tempData.size()];
        for(int i = 0; i < m_nNumberOfNodes * 2 + 1; i++)
            tempInt[i] = data[i];

        for(int i = 0; i < tempData.size(); i++)
        {
            Integer tempInteger = (Integer)tempData.elementAt(i);
            tempInt[m_nNumberOfNodes * 2 + 1 + i] = tempInteger.intValue();
        }

        data = new int[tempInt.length];
        for(int i = 0; i < tempInt.length; i++)
            data[i] = tempInt[i];

        setData(data);
        m_algorithm.repaint();
    }

    public void editLink(GraphMapsNode p_node, int p_value)
    {
        if(p_node.getIntValue() == 0)
        {
            if(p_value > 0)
            {
                int tempInt[] = new int[data.length + 3];
                for(int i = 0; i < data.length; i++)
                    tempInt[i] = data[i];

                tempInt[data.length] = p_node.getFrom();
                tempInt[data.length + 1] = p_node.getTo();
                tempInt[data.length + 2] = p_value;
                data = tempInt;
                setData(data);
            }
        } else
        if(p_value > 0)
        {
            for(int pos = m_nNumberOfNodes * 2 + 1; pos + 2 < data.length; pos += 3)
            {
                if(data[pos] == p_node.getFrom() && data[pos + 1] == p_node.getTo())
                {
                    data[pos + 2] = p_value;
                    continue;
                }
                if(m_bOmniDirectional && data[pos] == p_node.getTo() && data[pos + 1] == p_node.getFrom())
                    data[pos + 2] = p_value;
            }

            setData(data);
        } else
        {
            Vector tempData = new Vector();
            for(int pos = m_nNumberOfNodes * 2 + 1; pos + 2 < data.length; pos += 3)
            {
                if(m_bOmniDirectional)
                {
                    if((data[pos] != p_node.getFrom() || data[pos + 1] != p_node.getTo()) && (data[pos] != p_node.getTo() || data[pos + 1] != p_node.getFrom()))
                    {
                        tempData.addElement(new Integer(data[pos]));
                        tempData.addElement(new Integer(data[pos + 1]));
                        tempData.addElement(new Integer(data[pos + 2]));
                    }
                    continue;
                }
                if(data[pos] != p_node.getFrom() || data[pos + 1] != p_node.getTo())
                {
                    tempData.addElement(new Integer(data[pos]));
                    tempData.addElement(new Integer(data[pos + 1]));
                    tempData.addElement(new Integer(data[pos + 2]));
                }
            }

            int tempInt[] = new int[m_nNumberOfNodes * 2 + 1 + tempData.size()];
            for(int i = 0; i < m_nNumberOfNodes * 2 + 1; i++)
                tempInt[i] = data[i];

            for(int i = 0; i < tempData.size(); i++)
            {
                Integer tempInteger = (Integer)tempData.elementAt(i);
                tempInt[m_nNumberOfNodes * 2 + 1 + i] = tempInteger.intValue();
            }

            data = new int[tempInt.length];
            for(int i = 0; i < data.length; i++)
                data[i] = tempInt[i];

            setData(data);
        }
        m_algorithm.repaint();
    }

    public IntArray getData()
    {
        if(data == null)
            return null;
        else
            return new IntArray(data);
    }

    public void generateNodeInfo()
    {
        m_nodeInfo = new Vector();
        if(data.length > 0)
        {
            m_nNumberOfNodes = data[0];
            if(data.length >= m_nNumberOfNodes * 2 + 1)
            {
                for(int i = 0; i < m_nNumberOfNodes; i++)
                    m_nodeInfo.addElement(new NodeInfo(i, data[i * 2 + 1], data[i * 2 + 2]));

            }
        }
    }

    public boolean getOmniDirectional()
    {
        return m_bOmniDirectional;
    }

    public Vector getNodes()
    {
        if(m_nodeInfo == null)
            return null;
        Vector nodeArray = new Vector();
        for(int i = 0; i < m_nodeInfo.size(); i++)
        {
            NodeInfo nodeInfo = ((NodeInfo)m_nodeInfo.elementAt(i)).copy();
            nodeInfo.setX(nodeInfo.getX() * m_nZoomFactor);
            nodeInfo.setY(nodeInfo.getY() * m_nZoomFactor);
            GraphMapsNode node = new GraphMapsNode(new Integer(nodeInfo.getKey()), nodeInfo.getKey(), 1);
            m_algorithm.setNodeAppearance(node);
            node.setWidth(20);
            node.setIsVisible(true);
            node.setHeight(20);
            if(nodeInfo.getKey() == m_startNode || nodeInfo.getKey() == m_endNode)
                node.setBackgroundColor(m_algorithm.getHighlightColor());
            node.setPosition(new Point(nodeInfo.getX(), nodeInfo.getY()));
            nodeArray.addElement(node);
        }

        return nodeArray;
    }

    public Vector getLinks()
    {
        LinkContainer links = new LinkContainer();
        if(adj_structure == null || adj_structure.length == 0)
            return null;
        for(int i = 0; i < adj_structure.length; i++)
        {
            GraphNode graphNode = adj_structure[i];
            NodeInfo start = findNodeInfo(i);
            for(; graphNode.getKey() != -1; graphNode = graphNode.getNext())
            {
                NodeInfo end = findNodeInfo(graphNode.getKey());
                NodeInfo startDup = start.copy();
                NodeInfo endDup = end.copy();
                startDup.setX(startDup.getX() * m_nZoomFactor);
                startDup.setY(startDup.getY() * m_nZoomFactor);
                endDup.setX(endDup.getX() * m_nZoomFactor);
                endDup.setY(endDup.getY() * m_nZoomFactor);
                aia.graph.common.Link link = links.addLink(startDup, endDup, m_bOmniDirectional, adj_matrix[start.getKey()][end.getKey()]);
                m_algorithm.setLinkAppearance(link);
                m_algorithm.setLinkHighlighted(link);
            }

        }

        return links.getLinks();
    }

    private NodeInfo findNodeInfo(int p_nodeIndex)
    {
        if(m_nodeInfo == null || m_nodeInfo.size() == 0)
            return null;
        for(int i = 0; i < m_nodeInfo.size(); i++)
        {
            NodeInfo tempNodeInfo = (NodeInfo)m_nodeInfo.elementAt(i);
            if(tempNodeInfo.getKey() == p_nodeIndex)
                return tempNodeInfo;
        }

        return null;
    }

    public GraphNode getAdjacentNode(int p_index)
    {
        if(adj_structure == null || adj_structure.length == 0)
            return null;
        else
            return adj_structure[p_index];
    }

    public int getNumberOfNodes()
    {
        return m_nNumberOfNodes;
    }

    public Vector getAdjacencyMatrix()
    {
        Vector vecAdjacencyMatrix = new Vector();
        if(adj_matrix == null)
            return null;
        for(int i = 0; i < m_nNumberOfNodes; i++)
        {
            ElementArray elementArray = new ElementArray(0, 0);
            elementArray.setColumGap(0);
            elementArray.setElementWidth(20);
            for(int j = 0; j < m_nNumberOfNodes; j++)
            {
                GraphMapsNode node = new GraphMapsNode(new Integer(adj_matrix[i][j]), j, 2);
                node.setFrom(i);
                node.setTo(j);
                m_algorithm.setMatrixNodeAppearance(node);
                node.setHeight(20);
                elementArray.setValue(j, node);
            }

            vecAdjacencyMatrix.addElement(elementArray);
        }

        return vecAdjacencyMatrix;
    }

    public ElementArray getAdjacencyStructure()
    {
        if(adj_structure == null)
            return null;
        ElementArray elementArray = new ElementArray(0, 0);
        elementArray.setColumGap(10);
        elementArray.setElementWidth(20);
        for(int i = 0; i < m_nNumberOfNodes; i++)
        {
            GraphMapsNode node = new GraphMapsNode(new Integer(i), i, 3);
            m_algorithm.setStructureNodeAppearance(node);
            node.setHeight(20);
            elementArray.setValue(i, node);
        }

        return elementArray;
    }

    public int[][] getAdjacencyMatrixArray()
    {
        return adj_matrix;
    }

    public void setAdjacencyMatrixArray(int p_adj_matrix[][])
    {
        adj_matrix = p_adj_matrix;
    }

    public GraphNode[] getAdjacencyStructureArray()
    {
        return adj_structure;
    }

    protected boolean m_bOmniDirectional;
    protected int m_nZoomFactor;
    protected boolean m_bInsertionMode;
    protected boolean m_bDeleteMode;
    protected boolean m_bKeyChangeMode;
    protected boolean m_structureShowing;
    protected boolean m_matrixShowing;
    protected int data[];
    protected GraphMaps m_algorithm;
    protected int adj_matrix[][];
    protected int m_nNumberOfNodes;
    protected GraphNode adj_structure[];
    protected Vector m_nodeInfo;
    protected static MessageDialog messageDialog = null;
    protected int m_editLinkStep;
    protected int m_startNode;
    protected int m_endNode;
    protected Integer m_linkWeight;
    protected boolean m_editLinkStarted;

}
