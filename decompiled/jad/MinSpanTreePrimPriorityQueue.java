// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MinSpanTreePrimPriorityQueue.java

import java.util.Vector;

public class MinSpanTreePrimPriorityQueue
{

    MinSpanTreePrimPriorityQueue()
    {
        queue_array = new Vector();
    }

    MinSpanTreePrimPriorityQueue(Vector p_queueArray)
    {
        queue_array = p_queueArray;
    }

    public int size()
    {
        return queue_array.size();
    }

    private int searchRight(int p_initIndex, int p_priority)
    {
        boolean bFound = false;
        int i = p_initIndex;
        do
        {
            if(i >= queue_array.size())
                break;
            MinSpanTreePrimPriorityQueueMember queueMember = (MinSpanTreePrimPriorityQueueMember)queue_array.elementAt(i);
            if(queueMember.getPriority() >= p_priority)
            {
                bFound = true;
                break;
            }
            i++;
        } while(true);
        if(bFound)
            return i;
        else
            return -1;
    }

    private int searchLeft(int p_initIndex, int p_priority)
    {
        boolean bFound = false;
        int i = p_initIndex;
        do
        {
            if(i < 0)
                break;
            MinSpanTreePrimPriorityQueueMember queueMember = (MinSpanTreePrimPriorityQueueMember)queue_array.elementAt(i);
            if(queueMember.getPriority() <= p_priority)
            {
                bFound = true;
                break;
            }
            i--;
        } while(true);
        if(bFound)
            return i + 1;
        else
            return -1;
    }

    public void insert(int p_key, int p_parent, int p_priority)
    {
        int insert_index = searchRight(0, p_priority);
        if(insert_index != -1)
            queue_array.insertElementAt(new MinSpanTreePrimPriorityQueueMember(p_key, p_parent, p_priority), insert_index);
        else
            queue_array.addElement(new MinSpanTreePrimPriorityQueueMember(p_key, p_parent, p_priority));
    }

    public void delete(int p_member)
    {
        int member_index = findMember(p_member);
        if(member_index == -1)
        {
            return;
        } else
        {
            queue_array.removeElementAt(member_index);
            return;
        }
    }

    private int findMember(int p_member)
    {
        for(int i = 0; i < queue_array.size(); i++)
        {
            MinSpanTreePrimPriorityQueueMember queueMember = (MinSpanTreePrimPriorityQueueMember)queue_array.elementAt(i);
            if(queueMember.getKey() == p_member)
                return i;
        }

        return -1;
    }

    public boolean isEmpty()
    {
        return queue_array.size() == 0;
    }

    public MinSpanTreePrimPriorityQueueMember remove()
    {
        if(queue_array.size() == 0)
        {
            return null;
        } else
        {
            MinSpanTreePrimPriorityQueueMember queueMember = ((MinSpanTreePrimPriorityQueueMember)queue_array.elementAt(0)).copy();
            queue_array.removeElementAt(0);
            return queueMember;
        }
    }

    public MinSpanTreePrimPriorityQueueMember peek()
    {
        if(queue_array.size() == 0)
        {
            return null;
        } else
        {
            MinSpanTreePrimPriorityQueueMember queueMember = (MinSpanTreePrimPriorityQueueMember)queue_array.elementAt(0);
            return queueMember;
        }
    }

    public Vector getQueue()
    {
        return queue_array;
    }

    public int update(int p_member, int p_parent, int p_priority)
    {
        int member_index = findMember(p_member);
        if(member_index == -1)
        {
            insert(p_member, p_parent, p_priority);
            return -2;
        }
        MinSpanTreePrimPriorityQueueMember queueMember = (MinSpanTreePrimPriorityQueueMember)queue_array.elementAt(member_index);
        if(queueMember.getPriority() > p_priority)
        {
            int insert_index = searchLeft(member_index - 1, p_priority);
            queue_array.removeElementAt(member_index);
            if(insert_index != -1)
                queue_array.insertElementAt(new MinSpanTreePrimPriorityQueueMember(p_member, p_parent, p_priority), insert_index);
            else
                queue_array.insertElementAt(new MinSpanTreePrimPriorityQueueMember(p_member, p_parent, p_priority), 0);
            return member_index;
        } else
        {
            return -1;
        }
    }

    Vector queue_array;
}
