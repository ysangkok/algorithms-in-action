package aia.graph.common;

public class Queue {
    protected java.util.Vector m_queueItems;
    
    public Queue()
    {
        super();
        java.util.Vector a = new java.util.Vector();
        this.m_queueItems = a;
    }
    
    public void empty()
    {
        java.util.Vector a = new java.util.Vector();
        this.m_queueItems = a;
    }
    
    public Object get()
    {
        Object a = null;
        java.util.Vector a0 = this.m_queueItems;
        int i = a0.size();
        Object a1 = null;
        if(i <= 0)
        {
            a = a1;
        }
        else
        {
            java.util.Vector a2 = this.m_queueItems;
            Object a3 = a2.elementAt(0);
            java.util.Vector a4 = this.m_queueItems;
            a4.removeElementAt(0);
            a = a3;
        }
        return a;
    }
    
    public int getCount()
    {
        java.util.Vector a = this.m_queueItems;
        int i = a.size();
        return i;
    }
    
    public java.util.Vector getQueue()
    {
        java.util.Vector a = this.m_queueItems;
        return a;
    }
    
    public boolean isEmpty()
    {
        java.util.Vector a = this.m_queueItems;
        int i = a.size();
        int i0 = i != 0?0:1;
        return i0 != 0;
    }
    
    public Object peek()
    {
        Object a = null;
        java.util.Vector a0 = this.m_queueItems;
        int i = a0.size();
        Object a1 = null;
        if(i <= 0)
        {
            a = a1;
        }
        else
        {
            java.util.Vector a2 = this.m_queueItems;
            Object a3 = a2.elementAt(0);
            a = a3;
        }
        return a;
    }
    
    public void put(Object a)
    {
        java.util.Vector a0 = this.m_queueItems;
        a0.addElement(a);
    }
}