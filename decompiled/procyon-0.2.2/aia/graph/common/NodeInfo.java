package aia.graph.common;

public class NodeInfo
{
    protected int m_key;
    protected int m_x;
    protected int m_y;
    
    public NodeInfo(final int p_key, final int p_x, final int p_y) {
        super();
        this.m_key = p_key;
        this.m_x = p_x;
        this.m_y = p_y;
    }
    
    public NodeInfo copy() {
        final NodeInfo nodeInfo = new NodeInfo(this.m_key, this.m_x, this.m_y);
        return nodeInfo;
    }
    
    public int getKey() {
        return this.m_key;
    }
    
    public int getX() {
        return this.m_x;
    }
    
    public int getY() {
        return this.m_y;
    }
    
    public void setKey(final int p_key) {
        this.m_key = p_key;
    }
    
    public void setX(final int p_x) {
        this.m_x = p_x;
    }
    
    public void setY(final int p_y) {
        this.m_y = p_y;
    }
}
