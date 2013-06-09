package aia.graph.common;

public class QueueMember
{
    protected int m_key;
    protected int m_parent;
    
    public QueueMember() {
        super();
    }
    
    QueueMember(final int p_key, final int p_parent) {
        super();
        this.m_key = p_key;
        this.m_parent = p_parent;
    }
    
    public QueueMember copy() {
        return new QueueMember(this.m_key, this.m_parent);
    }
    
    public int getKey() {
        return this.m_key;
    }
    
    public int getParent() {
        return this.m_parent;
    }
    
    public void setKey(final int p_key) {
        this.m_key = p_key;
    }
    
    public void setParent(final int p_parent) {
        this.m_parent = p_parent;
    }
}
