package aia.graph.common;

public class NodeInfo {
    protected int m_key;
    protected int m_x;
    protected int m_y;
    
    public NodeInfo(int i, int i0, int i1)
    {
        super();
        this.m_key = i;
        this.m_x = i0;
        this.m_y = i1;
    }
    
    public aia.graph.common.NodeInfo copy()
    {
        int i = this.m_key;
        int i0 = this.m_x;
        int i1 = this.m_y;
        aia.graph.common.NodeInfo a = new aia.graph.common.NodeInfo(i, i0, i1);
        return a;
    }
    
    public int getKey()
    {
        int i = this.m_key;
        return i;
    }
    
    public int getX()
    {
        int i = this.m_x;
        return i;
    }
    
    public int getY()
    {
        int i = this.m_y;
        return i;
    }
    
    public void setKey(int i)
    {
        this.m_key = i;
    }
    
    public void setX(int i)
    {
        this.m_x = i;
    }
    
    public void setY(int i)
    {
        this.m_y = i;
    }
}