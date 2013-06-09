public class MinSpanTreePrimPriorityQueueMember {
    private int m_key;
    private int m_parent;
    private int m_priority;
    
    MinSpanTreePrimPriorityQueueMember(int i, int i0, int i1)
    {
        super();
        this.m_key = i;
        this.m_parent = i0;
        this.m_priority = i1;
    }
    
    public MinSpanTreePrimPriorityQueueMember copy()
    {
        int i = this.m_key;
        int i0 = this.m_parent;
        int i1 = this.m_priority;
        MinSpanTreePrimPriorityQueueMember a = new MinSpanTreePrimPriorityQueueMember(i, i0, i1);
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
    
    public int getPriority()
    {
        int i = this.m_priority;
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
    
    public void setPriority(int i)
    {
        this.m_priority = i;
    }
}