public class MinSpanTreePrimPriorityQueueMember
{
    private int m_key;
    private int m_parent;
    private int m_priority;
    
    MinSpanTreePrimPriorityQueueMember(final int p_key, final int p_parent, final int p_priority) {
        super();
        this.m_key = p_key;
        this.m_parent = p_parent;
        this.m_priority = p_priority;
    }
    
    public MinSpanTreePrimPriorityQueueMember copy() {
        return new MinSpanTreePrimPriorityQueueMember(this.m_key, this.m_parent, this.m_priority);
    }
    
    public int getKey() {
        return this.m_key;
    }
    
    public int getParent() {
        return this.m_parent;
    }
    
    public int getPriority() {
        return this.m_priority;
    }
    
    public void setKey(final int p_key) {
        this.m_key = p_key;
    }
    
    public void setParent(final int p_parent) {
        this.m_parent = p_parent;
    }
    
    public void setPriority(final int p_priority) {
        this.m_priority = p_priority;
    }
}
