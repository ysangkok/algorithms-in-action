// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MinSpanTreePrimPriorityQueueMember.java


public class MinSpanTreePrimPriorityQueueMember
{

    MinSpanTreePrimPriorityQueueMember(int p_key, int p_parent, int p_priority)
    {
        m_key = p_key;
        m_parent = p_parent;
        m_priority = p_priority;
    }

    public MinSpanTreePrimPriorityQueueMember copy()
    {
        return new MinSpanTreePrimPriorityQueueMember(m_key, m_parent, m_priority);
    }

    public int getKey()
    {
        return m_key;
    }

    public int getParent()
    {
        return m_parent;
    }

    public int getPriority()
    {
        return m_priority;
    }

    public void setKey(int p_key)
    {
        m_key = p_key;
    }

    public void setParent(int p_parent)
    {
        m_parent = p_parent;
    }

    public void setPriority(int p_priority)
    {
        m_priority = p_priority;
    }

    private int m_key;
    private int m_parent;
    private int m_priority;
}
