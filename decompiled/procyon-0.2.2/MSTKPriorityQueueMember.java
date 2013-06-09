public class MSTKPriorityQueueMember
{
    private int m_from_key;
    private int m_to_key;
    private int m_priority;
    
    MSTKPriorityQueueMember(final int p_from_key, final int p_to_key, final int p_priority) {
        super();
        this.m_from_key = p_from_key;
        this.m_to_key = p_to_key;
        this.m_priority = p_priority;
    }
    
    public MSTKPriorityQueueMember copy() {
        return new MSTKPriorityQueueMember(this.m_from_key, this.m_to_key, this.m_priority);
    }
    
    public int getFromKey() {
        return this.m_from_key;
    }
    
    public int getToKey() {
        return this.m_to_key;
    }
    
    public int getPriority() {
        return this.m_priority;
    }
    
    public void setFromKey(final int p_from_key) {
        this.m_from_key = p_from_key;
    }
    
    public void setToKey(final int p_to_key) {
        this.m_to_key = p_to_key;
    }
    
    public void setPriority(final int p_priority) {
        this.m_priority = p_priority;
    }
}
