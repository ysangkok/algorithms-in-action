package aia.graph.common;

public class GraphNode
{
    protected int m_key;
    protected GraphNode m_next;
    protected boolean m_bVisited;
    
    public GraphNode() {
        super();
        this.m_key = -1;
        this.m_next = null;
    }
    
    GraphNode(final int p_key, final GraphNode p_next) {
        super();
        this.m_key = p_key;
        this.m_next = p_next;
    }
    
    public int getKey() {
        return this.m_key;
    }
    
    public GraphNode getNext() {
        return this.m_next;
    }
    
    public boolean getVisited() {
        return this.m_bVisited;
    }
    
    public void setKey(final int p_key) {
        this.m_key = p_key;
    }
    
    public void setNext(final GraphNode p_next) {
        this.m_next = p_next;
    }
    
    public void setVisited(final boolean p_visited) {
        this.m_bVisited = p_visited;
    }
}
