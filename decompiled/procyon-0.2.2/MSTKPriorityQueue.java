import java.util.*;

public class MSTKPriorityQueue
{
    Vector<E> queue_array;
    
    MSTKPriorityQueue() {
        super();
        this.queue_array = new Vector();
    }
    
    MSTKPriorityQueue(final Vector<E> p_queueArray) {
        super();
        this.queue_array = p_queueArray;
    }
    
    private int searchRight(final int p_initIndex, final int p_priority) {
        boolean bFound = false;
        int i;
        for (i = p_initIndex; i < this.queue_array.size(); ++i) {
            final MSTKPriorityQueueMember queueMember = (MSTKPriorityQueueMember)this.queue_array.elementAt(i);
            if (queueMember.getPriority() >= p_priority) {
                bFound = true;
                break;
            }
        }
        if (bFound) {
            return i;
        }
        return -1;
    }
    
    private int searchLeft(final int p_initIndex, final int p_priority) {
        boolean bFound = false;
        int i;
        for (i = p_initIndex; i >= 0; --i) {
            final MSTKPriorityQueueMember queueMember = (MSTKPriorityQueueMember)this.queue_array.elementAt(i);
            if (queueMember.getPriority() <= p_priority) {
                bFound = true;
                break;
            }
        }
        if (bFound) {
            return i + 1;
        }
        return -1;
    }
    
    public int size() {
        return this.queue_array.size();
    }
    
    public void insert(final int p_from_key, final int p_to_key, final int p_priority) {
        final int insert_index = this.searchRight(0, p_priority);
        if (insert_index != -1) {
            this.queue_array.insertElementAt(new MSTKPriorityQueueMember(p_from_key, p_to_key, p_priority), insert_index);
        }
        else {
            this.queue_array.addElement(new MSTKPriorityQueueMember(p_from_key, p_to_key, p_priority));
        }
    }
    
    public void delete(final int p_from_key, final int p_to_key) {
        final int member_index = this.findMember(p_from_key, p_to_key);
        if (member_index == -1) {
            return;
        }
        this.queue_array.removeElementAt(member_index);
    }
    
    private int findMember(final int p_from_key, final int p_to_key) {
        for (int i = 0; i < this.queue_array.size(); ++i) {
            final MSTKPriorityQueueMember queueMember = (MSTKPriorityQueueMember)this.queue_array.elementAt(i);
            if (queueMember.getFromKey() == p_from_key && queueMember.getToKey() == p_to_key) {
                return i;
            }
            if (queueMember.getToKey() == p_from_key && queueMember.getFromKey() == p_to_key) {
                return i;
            }
        }
        return -1;
    }
    
    public boolean isEmpty() {
        return this.queue_array.size() == 0;
    }
    
    public MSTKPriorityQueueMember remove() {
        if (this.queue_array.size() == 0) {
            return null;
        }
        final MSTKPriorityQueueMember queueMember = ((MSTKPriorityQueueMember)this.queue_array.elementAt(0)).copy();
        this.queue_array.removeElementAt(0);
        return queueMember;
    }
    
    public MSTKPriorityQueueMember peek() {
        if (this.queue_array.size() == 0) {
            return null;
        }
        final MSTKPriorityQueueMember queueMember = (MSTKPriorityQueueMember)this.queue_array.elementAt(0);
        return queueMember;
    }
    
    public Vector<E> getQueue() {
        return this.queue_array;
    }
    
    public int update(final int p_from_key, final int p_to_key, final int p_priority) {
        final int member_index = this.findMember(p_from_key, p_to_key);
        if (member_index == -1) {
            this.insert(p_from_key, p_to_key, p_priority);
            return -2;
        }
        final MSTKPriorityQueueMember queueMember = (MSTKPriorityQueueMember)this.queue_array.elementAt(member_index);
        if (queueMember.getPriority() > p_priority) {
            final int insert_index = this.searchLeft(member_index - 1, p_priority);
            this.queue_array.removeElementAt(member_index);
            if (insert_index != -1) {
                this.queue_array.insertElementAt(new MSTKPriorityQueueMember(p_from_key, p_to_key, p_priority), insert_index);
            }
            else {
                this.queue_array.insertElementAt(new MSTKPriorityQueueMember(p_from_key, p_to_key, p_priority), 0);
            }
            return member_index;
        }
        return -1;
    }
}
