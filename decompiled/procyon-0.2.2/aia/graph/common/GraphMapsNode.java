package aia.graph.common;

import com.cim.AIA.*;

public class GraphMapsNode extends Node
{
    protected int m_NodeType;
    protected Object m_Value;
    protected int m_ToNode;
    protected int m_FromNode;
    public static final int GRAPH_NODE = 1;
    public static final int MATRIX_NODE = 2;
    public static final int STRUCTURE_NODE = 3;
    public static final int FLAG_NODE1 = 4;
    public static final int FLAG_NODE2 = 5;
    public static final int FLAG_NODE3 = 6;
    
    public GraphMapsNode(final Object value, final int p_SequenceNumber, final int p_NodeType) {
        super(value, p_SequenceNumber);
        this.m_NodeType = 0;
        this.m_Value = null;
        this.m_ToNode = -1;
        this.m_FromNode = -1;
        this.m_NodeType = p_NodeType;
        this.m_Value = value;
    }
    
    public int getNodeType() {
        return this.m_NodeType;
    }
    
    public int getIntValue() {
        return ((Integer)this.m_Value).intValue();
    }
    
    public String getStrValue() {
        return (String)this.m_Value;
    }
    
    public void setTo(final int to) {
        this.m_ToNode = to;
    }
    
    public void setFrom(final int from) {
        this.m_FromNode = from;
    }
    
    public int getTo() {
        return this.m_ToNode;
    }
    
    public int getFrom() {
        return this.m_FromNode;
    }
}
