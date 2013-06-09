// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MSTKPriorityQueueMember.java


public class MSTKPriorityQueueMember
{

    MSTKPriorityQueueMember(int p_from_key, int p_to_key, int p_priority)
    {
        m_from_key = p_from_key;
        m_to_key = p_to_key;
        m_priority = p_priority;
    }

    public MSTKPriorityQueueMember copy()
    {
        return new MSTKPriorityQueueMember(m_from_key, m_to_key, m_priority);
    }

    public int getFromKey()
    {
        return m_from_key;
    }

    public int getToKey()
    {
        return m_to_key;
    }

    public int getPriority()
    {
        return m_priority;
    }

    public void setFromKey(int p_from_key)
    {
        m_from_key = p_from_key;
    }

    public void setToKey(int p_to_key)
    {
        m_to_key = p_to_key;
    }

    public void setPriority(int p_priority)
    {
        m_priority = p_priority;
    }

    private int m_from_key;
    private int m_to_key;
    private int m_priority;
}
