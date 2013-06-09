package aia.graph.common;

public class GraphNode {
    protected int m_key;
    protected aia.graph.common.GraphNode m_next;
    protected boolean m_bVisited;
    
    public GraphNode()
    {
        super();
        this.m_key = -1;
        this.m_next = null;
    }
    
    GraphNode(int i, aia.graph.common.GraphNode a)
    {
        super();
        this.m_key = i;
        this.m_next = a;
    }
    
    public int getKey()
    {
        int i = this.m_key;
        return i;
    }
    
    public aia.graph.common.GraphNode getNext()
    {
        aia.graph.common.GraphNode a = this.m_next;
        return a;
    }
    
    public boolean getVisited()
    {
        int i = this.m_bVisited?1:0;
        return i != 0;
    }
    
    public void setKey(int i)
    {
        this.m_key = i;
    }
    
    public void setNext(aia.graph.common.GraphNode a)
    {
        this.m_next = a;
    }
    
    public void setVisited(boolean b)
    {
        this.m_bVisited = b;
    }
}