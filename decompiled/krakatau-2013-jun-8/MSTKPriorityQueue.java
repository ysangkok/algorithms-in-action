public class MSTKPriorityQueue {
    java.util.Vector queue_array;
    
    MSTKPriorityQueue()
    {
        super();
        java.util.Vector a = new java.util.Vector();
        this.queue_array = a;
    }
    
    MSTKPriorityQueue(java.util.Vector a)
    {
        super();
        this.queue_array = a;
    }
    
    private int searchRight(int i, int i0)
    {
        int i1 = 0;
        int i2 = i;
        while(true)
        {
            java.util.Vector a = this.queue_array;
            int i3 = a.size();
            if(i2 >= i3)
            {
                i1 = 0;
                break;
            }
            java.util.Vector a0 = this.queue_array;
            Object a1 = a0.elementAt(i2);
            MSTKPriorityQueueMember dummy = (MSTKPriorityQueueMember)a1;
            MSTKPriorityQueueMember a2 = (MSTKPriorityQueueMember)a1;
            int i4 = a2.getPriority();
            if(i4 < i0)
            {
                int i5 = i2 + 1;
                i2 = i5;
                continue;
            }
            i1 = 1;
            break;
        }
        int i6 = i1 == 0?-1:i2;
        return i6;
    }
    
    private int searchLeft(int i, int i0)
    {
        int i1 = 0;
        int i2 = 0;
        int i3 = i;
        while(true)
        {
            if(i3 < 0)
            {
                i1 = 0;
                break;
            }
            java.util.Vector a = this.queue_array;
            Object a0 = a.elementAt(i3);
            MSTKPriorityQueueMember dummy = (MSTKPriorityQueueMember)a0;
            MSTKPriorityQueueMember a1 = (MSTKPriorityQueueMember)a0;
            int i4 = a1.getPriority();
            if(i4 > i0)
            {
                int i5 = i3 + -1;
                i3 = i5;
                continue;
            }
            i1 = 1;
            break;
        }
        if(i1 == 0)
        {
            i2 = -1;
        }
        else
        {
            int i6 = i3 + 1;
            i2 = i6;
        }
        return i2;
    }
    
    public int size()
    {
        java.util.Vector a = this.queue_array;
        int i = a.size();
        return i;
    }
    
    public void insert(int i, int i0, int i1)
    {
        int i2 = this.searchRight(0, i1);
        if(i2 == -1)
        {
            java.util.Vector a = this.queue_array;
            MSTKPriorityQueueMember a0 = new MSTKPriorityQueueMember(i, i0, i1);
            a.addElement((Object)a0);
        }
        else
        {
            java.util.Vector a1 = this.queue_array;
            MSTKPriorityQueueMember a2 = new MSTKPriorityQueueMember(i, i0, i1);
            a1.insertElementAt((Object)a2, i2);
        }
    }
    
    public void delete(int i, int i0)
    {
        int i1 = this.findMember(i, i0);
        if(i1 != -1)
        {
            java.util.Vector a = this.queue_array;
            a.removeElementAt(i1);
        }
    }
    
    private int findMember(int i, int i0)
    {
        int i1 = 0;
        while(true)
        {
            java.util.Vector a = this.queue_array;
            int i2 = a.size();
            label3: {
                int i3 = 0;
                label1: {
                    label0: {
                        if(i1 < i2)
                        {
                            break label0;
                        }
                        i3 = -1;
                        break label1;
                    }
                    java.util.Vector a0 = this.queue_array;
                    Object a1 = a0.elementAt(i1);
                    MSTKPriorityQueueMember dummy = (MSTKPriorityQueueMember)a1;
                    MSTKPriorityQueueMember a2 = (MSTKPriorityQueueMember)a1;
                    int i4 = a2.getFromKey();
                    label2: {
                        if(i4 != i)
                        {
                            break label2;
                        }
                        int i5 = a2.getToKey();
                        if(i5 != i0)
                        {
                            break label2;
                        }
                        i3 = i1;
                        break label1;
                    }
                    int i6 = a2.getToKey();
                    if(i6 != i)
                    {
                        break label3;
                    }
                    int i7 = a2.getFromKey();
                    if(i7 != i0)
                    {
                        break label3;
                    }
                    i3 = i1;
                }
                return i3;
            }
            int i8 = i1 + 1;
            i1 = i8;
        }
    }
    
    public boolean isEmpty()
    {
        java.util.Vector a = this.queue_array;
        int i = a.size();
        int i0 = i != 0?0:1;
        return i0 != 0;
    }
    
    public MSTKPriorityQueueMember remove()
    {
        MSTKPriorityQueueMember a = null;
        java.util.Vector a0 = this.queue_array;
        int i = a0.size();
        if(i != 0)
        {
            java.util.Vector a1 = this.queue_array;
            Object a2 = a1.elementAt(0);
            MSTKPriorityQueueMember dummy = (MSTKPriorityQueueMember)a2;
            MSTKPriorityQueueMember a3 = (MSTKPriorityQueueMember)a2;
            MSTKPriorityQueueMember a4 = a3.copy();
            java.util.Vector a5 = this.queue_array;
            a5.removeElementAt(0);
            a = a4;
        }
        else
        {
            a = null;
        }
        return a;
    }
    
    public MSTKPriorityQueueMember peek()
    {
        MSTKPriorityQueueMember a = null;
        java.util.Vector a0 = this.queue_array;
        int i = a0.size();
        if(i != 0)
        {
            java.util.Vector a1 = this.queue_array;
            Object a2 = a1.elementAt(0);
            MSTKPriorityQueueMember dummy = (MSTKPriorityQueueMember)a2;
            MSTKPriorityQueueMember a3 = (MSTKPriorityQueueMember)a2;
            a = a3;
        }
        else
        {
            a = null;
        }
        return a;
    }
    
    public java.util.Vector getQueue()
    {
        java.util.Vector a = this.queue_array;
        return a;
    }
    
    public int update(int i, int i0, int i1)
    {
        int i2 = 0;
        int i3 = this.findMember(i, i0);
        label1: {
            label0: {
                if(i3 != -1)
                {
                    break label0;
                }
                this.insert(i, i0, i1);
                i2 = -2;
                break label1;
            }
            java.util.Vector a = this.queue_array;
            Object a0 = a.elementAt(i3);
            MSTKPriorityQueueMember dummy = (MSTKPriorityQueueMember)a0;
            MSTKPriorityQueueMember a1 = (MSTKPriorityQueueMember)a0;
            int i4 = a1.getPriority();
            label2: {
                if(i4 > i1)
                {
                    break label2;
                }
                i2 = -1;
                break label1;
            }
            int i5 = i3 - 1;
            int i6 = this.searchLeft(i5, i1);
            java.util.Vector a2 = this.queue_array;
            a2.removeElementAt(i3);
            if(i6 == -1)
            {
                java.util.Vector a3 = this.queue_array;
                MSTKPriorityQueueMember a4 = new MSTKPriorityQueueMember(i, i0, i1);
                a3.insertElementAt((Object)a4, 0);
            }
            else
            {
                java.util.Vector a5 = this.queue_array;
                MSTKPriorityQueueMember a6 = new MSTKPriorityQueueMember(i, i0, i1);
                a5.insertElementAt((Object)a6, i6);
            }
            i2 = i3;
        }
        return i2;
    }
}