public class MSTKPriorityQueueMember {
    private int m_from_key;
    private int m_to_key;
    private int m_priority;
    
    MSTKPriorityQueueMember(int i, int i0, int i1)
    {
        super();
        this.m_from_key = i;
        this.m_to_key = i0;
        this.m_priority = i1;
    }
    
    public MSTKPriorityQueueMember copy()
    {
        int i = this.m_from_key;
        int i0 = this.m_to_key;
        int i1 = this.m_priority;
        MSTKPriorityQueueMember a = new MSTKPriorityQueueMember(i, i0, i1);
        return a;
    }
    
    public int getFromKey()
    {
        int i = this.m_from_key;
        return i;
    }
    
    public int getToKey()
    {
        int i = this.m_to_key;
        return i;
    }
    
    public int getPriority()
    {
        int i = this.m_priority;
        return i;
    }
    
    public void setFromKey(int i)
    {
        this.m_from_key = i;
    }
    
    public void setToKey(int i)
    {
        this.m_to_key = i;
    }
    
    public void setPriority(int i)
    {
        this.m_priority = i;
    }
}