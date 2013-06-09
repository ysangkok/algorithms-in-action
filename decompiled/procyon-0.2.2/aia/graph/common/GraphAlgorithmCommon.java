package aia.graph.common;

import localization.*;
import java.awt.*;
import com.cim.AIA.*;
import java.util.*;
import com.cim.common.*;

public class GraphAlgorithmCommon
{
    protected static MessageDialog messageDialog;
    protected boolean m_bOmniDirectional;
    protected int m_nZoomFactor;
    protected boolean m_bInsertionMode;
    protected boolean m_bDeleteMode;
    protected boolean m_bKeyChangeMode;
    protected boolean m_structureShowing;
    protected boolean m_matrixShowing;
    protected int[] data;
    protected GraphMaps m_algorithm;
    protected int[][] adj_matrix;
    protected int m_nNumberOfNodes;
    protected GraphNode[] adj_structure;
    protected Vector<NodeInfo> m_nodeInfo;
    protected int m_editLinkStep;
    protected int m_startNode;
    protected int m_endNode;
    protected Integer m_linkWeight;
    protected boolean m_editLinkStarted;
    
    public GraphAlgorithmCommon() {
        super();
        this.m_bOmniDirectional = true;
        this.m_nZoomFactor = 1;
        this.m_bInsertionMode = false;
        this.m_bDeleteMode = false;
        this.m_bKeyChangeMode = false;
        this.m_structureShowing = true;
        this.m_matrixShowing = true;
        this.adj_matrix = (int[][])null;
        this.adj_structure = null;
        this.m_editLinkStep = 0;
        this.m_startNode = -10;
        this.m_endNode = -10;
        this.m_linkWeight = null;
        this.m_editLinkStarted = false;
    }
    
    public GraphAlgorithmCommon(final GraphMaps p_algorithm, final Object p_data) {
        super();
        this.m_bOmniDirectional = true;
        this.m_nZoomFactor = 1;
        this.m_bInsertionMode = false;
        this.m_bDeleteMode = false;
        this.m_bKeyChangeMode = false;
        this.m_structureShowing = true;
        this.m_matrixShowing = true;
        this.adj_matrix = (int[][])null;
        this.adj_structure = null;
        this.m_editLinkStep = 0;
        this.m_startNode = -10;
        this.m_endNode = -10;
        this.m_linkWeight = null;
        this.m_editLinkStarted = false;
        this.m_algorithm = p_algorithm;
        this.data = (int[])((int[])p_data);
        this.generateNodeInfo();
        this.generateAdjacencyMatrix();
        this.generateAdjacencyStructure();
        this.outputDataArray();
        this.m_structureShowing = true;
        this.m_matrixShowing = true;
    }
    
    public void algorithmStartRunInitialisations() {
        this.endEditLink();
        this.setKeyChangeMode(false);
        this.setInsertionMode(false);
        this.setDeleteMode(false);
    }
    
    public void deleteNode(final int p_node) {
        if (this.m_algorithm.getIsRunning()) {
            return;
        }
        final Vector<Integer> tempData = new Vector();
        if (this.m_nNumberOfNodes == 0 || p_node >= this.m_nNumberOfNodes || p_node < 0) {
            return;
        }
        final NodeInfo nodeInfo = this.findNodeInfo(p_node);
        this.m_nodeInfo.removeElement(nodeInfo);
        int[] tempInt = new int[this.data.length - 2];
        for (int i = 0; i < this.m_nNumberOfNodes; ++i) {
            if (i != p_node) {
                if (i > p_node) {
                    tempInt[i * 2 - 1] = this.data[i * 2 + 1];
                    tempInt[i * 2] = this.data[i * 2 + 2];
                }
                else {
                    tempInt[i * 2 + 1] = this.data[i * 2 + 1];
                    tempInt[i * 2 + 2] = this.data[i * 2 + 2];
                }
            }
        }
        for (int i = this.m_nNumberOfNodes * 2 + 1; i < this.data.length; ++i) {
            tempInt[i - 2] = this.data[i];
        }
        tempInt[0] = this.m_nNumberOfNodes - 1;
        this.data = new int[tempInt.length];
        for (int i = 0; i < tempInt.length; ++i) {
            this.data[i] = tempInt[i];
        }
        this.m_nNumberOfNodes = this.m_nNumberOfNodes - 1;
        for (int pos = this.m_nNumberOfNodes * 2 + 1; pos + 2 < this.data.length; pos += 3) {
            if (this.data[pos] != p_node && this.data[pos + 1] != p_node) {
                if (this.data[pos] > p_node) {
                    tempData.addElement(new Integer(this.data[pos] - 1));
                }
                else {
                    tempData.addElement(new Integer(this.data[pos]));
                }
                if (this.data[pos + 1] > p_node) {
                    tempData.addElement(new Integer(this.data[pos + 1] - 1));
                }
                else {
                    tempData.addElement(new Integer(this.data[pos + 1]));
                }
                tempData.addElement(new Integer(this.data[pos + 2]));
            }
        }
        tempInt = new int[this.m_nNumberOfNodes * 2 + 1 + tempData.size()];
        for (int i = 0; i < this.m_nNumberOfNodes * 2 + 1; ++i) {
            tempInt[i] = this.data[i];
        }
        for (int i = 0; i < tempData.size(); ++i) {
            final Integer tempInteger = (Integer)tempData.elementAt(i);
            tempInt[this.m_nNumberOfNodes * 2 + 1 + i] = tempInteger.intValue();
        }
        this.data = new int[tempInt.length];
        for (int i = 0; i < tempInt.length; ++i) {
            this.data[i] = tempInt[i];
        }
        this.setData(this.data);
        this.m_algorithm.repaint();
    }
    
    public void editLink(final GraphMapsNode p_node, final int p_value) {
        if (p_node.getIntValue() == 0) {
            if (p_value > 0) {
                final int[] tempInt = new int[this.data.length + 3];
                for (int i = 0; i < this.data.length; ++i) {
                    tempInt[i] = this.data[i];
                }
                tempInt[this.data.length] = p_node.getFrom();
                tempInt[this.data.length + 1] = p_node.getTo();
                tempInt[this.data.length + 2] = p_value;
                this.data = tempInt;
                this.setData(this.data);
            }
        }
        else if (p_value > 0) {
            for (int pos = this.m_nNumberOfNodes * 2 + 1; pos + 2 < this.data.length; pos += 3) {
                if (this.data[pos] == p_node.getFrom() && this.data[pos + 1] == p_node.getTo()) {
                    this.data[pos + 2] = p_value;
                }
                else if (this.m_bOmniDirectional && this.data[pos] == p_node.getTo() && this.data[pos + 1] == p_node.getFrom()) {
                    this.data[pos + 2] = p_value;
                }
            }
            this.setData(this.data);
        }
        else {
            final Vector<Integer> tempData = new Vector();
            for (int pos = this.m_nNumberOfNodes * 2 + 1; pos + 2 < this.data.length; pos += 3) {
                if (this.m_bOmniDirectional) {
                    if ((this.data[pos] != p_node.getFrom() || this.data[pos + 1] != p_node.getTo()) && (this.data[pos] != p_node.getTo() || this.data[pos + 1] != p_node.getFrom())) {
                        tempData.addElement(new Integer(this.data[pos]));
                        tempData.addElement(new Integer(this.data[pos + 1]));
                        tempData.addElement(new Integer(this.data[pos + 2]));
                    }
                }
                else if (this.data[pos] != p_node.getFrom() || this.data[pos + 1] != p_node.getTo()) {
                    tempData.addElement(new Integer(this.data[pos]));
                    tempData.addElement(new Integer(this.data[pos + 1]));
                    tempData.addElement(new Integer(this.data[pos + 2]));
                }
            }
            final int[] tempInt2 = new int[this.m_nNumberOfNodes * 2 + 1 + tempData.size()];
            for (int j = 0; j < this.m_nNumberOfNodes * 2 + 1; ++j) {
                tempInt2[j] = this.data[j];
            }
            for (int j = 0; j < tempData.size(); ++j) {
                final Integer tempInteger = (Integer)tempData.elementAt(j);
                tempInt2[this.m_nNumberOfNodes * 2 + 1 + j] = tempInteger.intValue();
            }
            this.data = new int[tempInt2.length];
            for (int j = 0; j < this.data.length; ++j) {
                this.data[j] = tempInt2[j];
            }
            this.setData(this.data);
        }
        this.m_algorithm.repaint();
    }
    
    public boolean editLinkStarted() {
        return this.m_editLinkStarted;
    }
    
    public void endEditLink() {
        this.m_editLinkStarted = false;
        this.m_startNode = -10;
        this.m_endNode = -10;
        if (GraphAlgorithmCommon.messageDialog != null) {
            GraphAlgorithmCommon.messageDialog.dispose();
        }
        GraphAlgorithmCommon.messageDialog = null;
        this.m_algorithm.repaint();
    }
    
    private NodeInfo findNodeInfo(final int p_nodeIndex) {
        if (this.m_nodeInfo == null || this.m_nodeInfo.size() == 0) {
            return null;
        }
        for (int i = 0; i < this.m_nodeInfo.size(); ++i) {
            final NodeInfo tempNodeInfo = (NodeInfo)this.m_nodeInfo.elementAt(i);
            if (tempNodeInfo.getKey() == p_nodeIndex) {
                return tempNodeInfo;
            }
        }
        return null;
    }
    
    public void generateAdjacencyMatrix() {
        this.adj_matrix = new int[this.m_nNumberOfNodes][this.m_nNumberOfNodes];
        for (int i = 0; i < this.m_nNumberOfNodes; ++i) {
            int j = 0;
            while (j < this.m_nNumberOfNodes) {
                this.adj_matrix[i][j] = 0;
                ++j;
            }
        }
        for (int pos = this.m_nNumberOfNodes * 2 + 1; pos + 2 < this.data.length; pos += 3) {
            final int x = this.data[pos];
            final int y = this.data[pos + 1];
            final int weight = this.data[pos + 2];
            this.adj_matrix[x][y] = weight;
            if (this.m_bOmniDirectional) {
                this.adj_matrix[y][x] = weight;
            }
        }
    }
    
    public void generateAdjacencyStructure() {
        this.adj_structure = new GraphNode[this.m_nNumberOfNodes];
        GraphNode tempGraphNode;
        for (int i = 0; i < this.m_nNumberOfNodes; ++i) {
            tempGraphNode = new GraphNode();
            tempGraphNode.setNext(tempGraphNode);
            tempGraphNode.setKey(-1);
            this.adj_structure[i] = tempGraphNode;
        }
        for (int i = 0; i < this.m_nNumberOfNodes; ++i) {
            int j = 0;
            while (j < this.m_nNumberOfNodes) {
                if (j != i && this.adj_matrix[i][j] != 0) {
                    tempGraphNode = new GraphNode();
                    tempGraphNode.setKey(j);
                    tempGraphNode.setNext(this.adj_structure[i]);
                    this.adj_structure[i] = tempGraphNode;
                }
                ++j;
            }
        }
    }
    
    public void generateNodeInfo() {
        this.m_nodeInfo = new Vector();
        if (this.data.length > 0) {
            this.m_nNumberOfNodes = this.data[0];
            if (this.data.length >= this.m_nNumberOfNodes * 2 + 1) {
                for (int i = 0; i < this.m_nNumberOfNodes; ++i) {
                    this.m_nodeInfo.addElement(new NodeInfo(i, this.data[i * 2 + 1], this.data[i * 2 + 2]));
                }
            }
        }
    }
    
    public Vector<ElementArray> getAdjacencyMatrix() {
        final Vector<ElementArray> vecAdjacencyMatrix = new Vector();
        if (this.adj_matrix == null) {
            return null;
        }
        for (int i = 0; i < this.m_nNumberOfNodes; ++i) {
            final ElementArray elementArray = new ElementArray(0, 0);
            elementArray.setColumGap(0);
            elementArray.setElementWidth(20);
            int j = 0;
            while (j < this.m_nNumberOfNodes) {
                final GraphMapsNode node = new GraphMapsNode(new Integer(this.adj_matrix[i][j]), j, 2);
                node.setFrom(i);
                node.setTo(j);
                this.m_algorithm.setMatrixNodeAppearance(node);
                node.setHeight(20);
                elementArray.setValue(j, node);
                ++j;
            }
            vecAdjacencyMatrix.addElement(elementArray);
        }
        return vecAdjacencyMatrix;
    }
    
    public int[][] getAdjacencyMatrixArray() {
        return this.adj_matrix;
    }
    
    public ElementArray getAdjacencyStructure() {
        if (this.adj_structure == null) {
            return null;
        }
        final ElementArray elementArray = new ElementArray(0, 0);
        elementArray.setColumGap(10);
        elementArray.setElementWidth(20);
        for (int i = 0; i < this.m_nNumberOfNodes; ++i) {
            final GraphMapsNode node = new GraphMapsNode(new Integer(i), i, 3);
            this.m_algorithm.setStructureNodeAppearance(node);
            node.setHeight(20);
            elementArray.setValue(i, node);
        }
        return elementArray;
    }
    
    public GraphNode[] getAdjacencyStructureArray() {
        return this.adj_structure;
    }
    
    public GraphNode getAdjacentNode(final int p_index) {
        if (this.adj_structure == null || this.adj_structure.length == 0) {
            return null;
        }
        return this.adj_structure[p_index];
    }
    
    public int getCurrentLinkLinkWeight() {
        return this.adj_matrix[this.m_startNode][this.m_endNode];
    }
    
    public IntArray getData() {
        if (this.data == null) {
            return null;
        }
        return new IntArray(this.data);
    }
    
    public boolean getDeleteMode() {
        return this.m_bDeleteMode;
    }
    
    public int getEditLinkStep() {
        return this.m_editLinkStep;
    }
    
    public int getEndNode() {
        return this.m_endNode;
    }
    
    public boolean getInsertionMode() {
        return this.m_bInsertionMode;
    }
    
    public boolean getKeyChangeMode() {
        return this.m_bKeyChangeMode;
    }
    
    public Vector<Link> getLinks() {
        final LinkContainer links = new LinkContainer();
        if (this.adj_structure == null || this.adj_structure.length == 0) {
            return null;
        }
        for (int i = 0; i < this.adj_structure.length; ++i) {
            GraphNode graphNode = this.adj_structure[i];
            final NodeInfo start = this.findNodeInfo(i);
            while (graphNode.getKey() != -1) {
                final NodeInfo end = this.findNodeInfo(graphNode.getKey());
                final NodeInfo startDup = start.copy();
                final NodeInfo endDup = end.copy();
                startDup.setX(startDup.getX() * this.m_nZoomFactor);
                startDup.setY(startDup.getY() * this.m_nZoomFactor);
                endDup.setX(endDup.getX() * this.m_nZoomFactor);
                endDup.setY(endDup.getY() * this.m_nZoomFactor);
                final Link link = links.addLink(startDup, endDup, this.m_bOmniDirectional, this.adj_matrix[start.getKey()][end.getKey()]);
                this.m_algorithm.setLinkAppearance(link);
                this.m_algorithm.setLinkHighlighted(link);
                graphNode = graphNode.getNext();
            }
        }
        return links.getLinks();
    }
    
    public boolean getMatrixShowing() {
        return this.m_matrixShowing;
    }
    
    public Vector<GraphMapsNode> getNodes() {
        if (this.m_nodeInfo == null) {
            return null;
        }
        final Vector<GraphMapsNode> nodeArray = new Vector();
        for (int i = 0; i < this.m_nodeInfo.size(); ++i) {
            final NodeInfo nodeInfo = ((NodeInfo)this.m_nodeInfo.elementAt(i)).copy();
            nodeInfo.setX(nodeInfo.getX() * this.m_nZoomFactor);
            nodeInfo.setY(nodeInfo.getY() * this.m_nZoomFactor);
            final GraphMapsNode node = new GraphMapsNode(new Integer(nodeInfo.getKey()), nodeInfo.getKey(), 1);
            this.m_algorithm.setNodeAppearance(node);
            node.setWidth(20);
            node.setIsVisible(true);
            node.setHeight(20);
            if (nodeInfo.getKey() == this.m_startNode || nodeInfo.getKey() == this.m_endNode) {
                node.setBackgroundColor(this.m_algorithm.getHighlightColor());
            }
            node.setPosition(new Point(nodeInfo.getX(), nodeInfo.getY()));
            nodeArray.addElement(node);
        }
        return nodeArray;
    }
    
    public int getNumberOfNodes() {
        return this.m_nNumberOfNodes;
    }
    
    public boolean getOmniDirectional() {
        return this.m_bOmniDirectional;
    }
    
    public int getStartNode() {
        return this.m_startNode;
    }
    
    public boolean getStructureShowing() {
        return this.m_structureShowing;
    }
    
    public void insertNewNode(final int x, final int y) {
        int nx = x / this.m_nZoomFactor;
        int ny = y / this.m_nZoomFactor;
        nx = (int)(Math.floor((double)(x / (this.m_nZoomFactor * 5))) * 5.0);
        ny = (int)(Math.floor((double)(y / (this.m_nZoomFactor * 5))) * 5.0);
        if (this.m_nodeInfo == null) {
            this.m_nodeInfo = new Vector();
            this.m_nNumberOfNodes = 0;
            this.data = new int[1];
            this.data[0] = 0;
        }
        this.m_nodeInfo.addElement(new NodeInfo(this.m_nNumberOfNodes, x / this.m_nZoomFactor, y / this.m_nZoomFactor));
        final int[] tempInt = new int[this.data.length + 2];
        for (int i = 0; i < this.m_nNumberOfNodes; ++i) {
            tempInt[i * 2 + 1] = this.data[i * 2 + 1];
            tempInt[i * 2 + 2] = this.data[i * 2 + 2];
        }
        tempInt[this.m_nNumberOfNodes * 2 + 1] = nx;
        tempInt[this.m_nNumberOfNodes * 2 + 2] = ny;
        for (int i = this.m_nNumberOfNodes * 2 + 1; i < this.data.length; ++i) {
            tempInt[i + 2] = this.data[i];
        }
        this.m_nNumberOfNodes = this.m_nNumberOfNodes + 1;
        tempInt[0] = this.m_nNumberOfNodes;
        this.data = new int[tempInt.length];
        for (int i = 0; i < tempInt.length; ++i) {
            this.data[i] = tempInt[i];
        }
        this.setData(this.data);
        this.m_algorithm.repaint();
    }
    
    public void keyChange(final int p_from_key, final int p_to_key) {
        if (p_to_key >= this.m_nNumberOfNodes || p_to_key < 0) {
            return;
        }
        final int x = this.data[p_from_key * 2 + 1];
        final int y = this.data[p_from_key * 2 + 2];
        this.data[p_from_key * 2 + 1] = this.data[p_to_key * 2 + 1];
        this.data[p_from_key * 2 + 2] = this.data[p_to_key * 2 + 2];
        this.data[p_to_key * 2 + 1] = x;
        this.data[p_to_key * 2 + 2] = y;
        for (int pos = this.m_nNumberOfNodes * 2 + 1; pos + 2 < this.data.length; pos += 3) {
            if (this.data[pos] == p_from_key) {
                this.data[pos] = p_to_key;
            }
            else if (this.data[pos] == p_to_key) {
                this.data[pos] = p_from_key;
            }
            if (this.data[pos + 1] == p_from_key) {
                this.data[pos + 1] = p_to_key;
            }
            else if (this.data[pos + 1] == p_to_key) {
                this.data[pos + 1] = p_from_key;
            }
        }
        this.setData(this.data);
        this.m_algorithm.repaint();
    }
    
    public boolean needPromptForLinkWeight() {
        return this.m_linkWeight == null;
    }
    
    public void outputDataArray() {
        System.out.println("******Graph Data******");
        for (int i = 0; i < this.data.length; ++i) {
            System.out.print(this.data[i]);
            if (i < this.data.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
        System.out.println("----------------------");
        System.out.println();
    }
    
    public void setAdjacencyMatrixArray(final int[][] p_adj_matrix) {
        this.adj_matrix = p_adj_matrix;
    }
    
    public void setData(final Object p_data) {
        this.data = (int[])((int[])p_data);
        this.generateNodeInfo();
        this.generateAdjacencyMatrix();
        this.generateAdjacencyStructure();
        this.outputDataArray();
    }
    
    public void setDeleteMode(final boolean p_deleteMode) {
        if (this.m_algorithm.getIsRunning()) {
            return;
        }
        this.m_bDeleteMode = p_deleteMode;
        if (GraphAlgorithmCommon.messageDialog != null) {
            GraphAlgorithmCommon.messageDialog.dispose();
        }
        if (this.m_bDeleteMode) {
            GraphAlgorithmCommon.messageDialog = new MessageDialog(Messages.getString("GraphAlgorithmCommon.11"), false, false);
            GraphAlgorithmCommon.messageDialog.setTitle(Messages.getString("GraphAlgorithmCommon.12"));
            GraphAlgorithmCommon.messageDialog.setVisible(true);
        }
    }
    
    public void setEndNode(final int p_key) {
        this.m_endNode = p_key;
        this.m_editLinkStep = this.m_editLinkStep + 1;
        if (!this.needPromptForLinkWeight()) {
            this.setLinkWeight(this.m_linkWeight.intValue());
        }
        this.m_algorithm.repaint();
    }
    
    public void setInsertionMode(final boolean p_insertion) {
        if (this.m_algorithm.getIsRunning()) {
            return;
        }
        this.m_bInsertionMode = p_insertion;
        if (GraphAlgorithmCommon.messageDialog != null) {
            GraphAlgorithmCommon.messageDialog.dispose();
        }
        if (this.m_bInsertionMode) {
            GraphAlgorithmCommon.messageDialog = new MessageDialog(Messages.getString("GraphAlgorithmCommon.9"), false, false);
            GraphAlgorithmCommon.messageDialog.setTitle(Messages.getString("GraphAlgorithmCommon.10"));
            GraphAlgorithmCommon.messageDialog.setVisible(true);
        }
    }
    
    public void setKeyChangeMode(final boolean p_keyChange) {
        if (this.m_algorithm.getIsRunning()) {
            return;
        }
        this.m_bKeyChangeMode = p_keyChange;
        if (GraphAlgorithmCommon.messageDialog != null) {
            GraphAlgorithmCommon.messageDialog.dispose();
        }
        if (this.m_bKeyChangeMode) {
            GraphAlgorithmCommon.messageDialog = new MessageDialog(Messages.getString("GraphAlgorithmCommon.7"), false, false);
            GraphAlgorithmCommon.messageDialog.setTitle(Messages.getString("GraphAlgorithmCommon.8"));
            GraphAlgorithmCommon.messageDialog.setVisible(true);
        }
    }
    
    public void setLinkWeight(final int p_link_weight) {
        final GraphMapsNode node = new GraphMapsNode(new Integer(this.getCurrentLinkLinkWeight()), 0, 0);
        node.setFrom(this.m_startNode);
        node.setTo(this.m_endNode);
        this.editLink(node, p_link_weight);
        this.endEditLink();
    }
    
    public void setMatrixShowing(final boolean p_matrixShowing) {
        this.m_matrixShowing = p_matrixShowing;
        this.m_algorithm.repaint();
    }
    
    public void setNodePosition(final int p_nodeIndex, final int x, final int y) {
        int nx = x / this.m_nZoomFactor;
        int ny = y / this.m_nZoomFactor;
        nx = (int)(Math.floor((double)(x / (this.m_nZoomFactor * 5))) * 5.0);
        ny = (int)(Math.floor((double)(y / (this.m_nZoomFactor * 5))) * 5.0);
        final NodeInfo nodeInfo = this.findNodeInfo(p_nodeIndex);
        if (nodeInfo != null) {
            nodeInfo.setX(nx);
            nodeInfo.setY(ny);
            this.data[nodeInfo.getKey() * 2 + 1] = nx;
            this.data[nodeInfo.getKey() * 2 + 2] = ny;
            this.m_algorithm.repaint();
        }
    }
    
    public void setOmniDirectional(final boolean p_omniDir) {
        if (this.m_algorithm.getIsRunning()) {
            return;
        }
        this.m_bOmniDirectional = p_omniDir;
        this.setData(this.data);
        this.m_algorithm.repaint();
    }
    
    public void setStartNode(final int p_key) {
        this.m_startNode = p_key;
        if (GraphAlgorithmCommon.messageDialog != null) {
            GraphAlgorithmCommon.messageDialog.dispose();
        }
        GraphAlgorithmCommon.messageDialog = new MessageDialog(Messages.getString("GraphAlgorithmCommon.5"), false, false);
        GraphAlgorithmCommon.messageDialog.setTitle(Messages.getString("GraphAlgorithmCommon.6"));
        GraphAlgorithmCommon.messageDialog.setVisible(true);
        this.m_editLinkStep = this.m_editLinkStep + 1;
        this.m_algorithm.repaint();
    }
    
    public void setStructureShowing(final boolean p_structureShowing) {
        this.m_structureShowing = p_structureShowing;
        this.m_algorithm.repaint();
    }
    
    public void startEditLink(final Integer p_link_weight) {
        if (this.m_algorithm.getIsRunning()) {
            return;
        }
        this.m_editLinkStarted = true;
        this.m_linkWeight = p_link_weight;
        this.m_startNode = -10;
        this.m_endNode = -10;
        if (GraphAlgorithmCommon.messageDialog != null) {
            GraphAlgorithmCommon.messageDialog.dispose();
        }
        GraphAlgorithmCommon.messageDialog = new MessageDialog(Messages.getString("GraphAlgorithmCommon.3"), false, false);
        GraphAlgorithmCommon.messageDialog.setTitle(Messages.getString("GraphAlgorithmCommon.4"));
        GraphAlgorithmCommon.messageDialog.setVisible(true);
        this.m_editLinkStep = 1;
    }
    
    public void zoomIn() {
        if (this.m_nZoomFactor == 10) {
            return;
        }
        this.m_nZoomFactor = this.m_nZoomFactor + 1;
        this.m_algorithm.repaint();
    }
    
    public void zoomOut() {
        if (this.m_nZoomFactor == 1) {
            return;
        }
        this.m_nZoomFactor = this.m_nZoomFactor - 1;
        this.m_algorithm.repaint();
    }
    
    static {
        GraphAlgorithmCommon.messageDialog = null;
    }
}
