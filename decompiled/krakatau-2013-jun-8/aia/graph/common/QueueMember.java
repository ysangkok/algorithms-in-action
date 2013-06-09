package aia.graph.common;

public class QueueMember {
    protected int m_key;
    protected int m_parent;
    
    public QueueMember()
    {
        super();
    }
    
    QueueMember(int i, int i0)
    {
        super();
        this.m_key = i;
        this.m_parent = i0;
    }
    
    public aia.graph.common.QueueMember copy()
    {
        int i = this.m_key;
        int i0 = this.m_parent;
        aia.graph.common.QueueMember a = new aia.graph.common.QueueMember(i, i0);
        return a;
    }
    
    public int getKey()
    {
        int i = this.m_key;
        return i;
    }
    
    public int getParent()
    {
        int i = this.m_parent;
        return i;
    }
    
    public void setKey(int i)
    {
        this.m_key = i;
    }
    
    public void setParent(int i)
    {
        this.m_parent = i;
    }
}