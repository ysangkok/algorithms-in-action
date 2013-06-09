import java.util.*;

public class MinSpanTreePrimPriorityQueue
{
    Vector<E> queue_array;
    
    MinSpanTreePrimPriorityQueue() {
        super();
        this.queue_array = new Vector();
    }
    
    MinSpanTreePrimPriorityQueue(final Vector<E> p_queueArray) {
        super();
        this.queue_array = p_queueArray;
    }
    
    public int size() {
        return this.queue_array.size();
    }
    
    private int searchRight(final int p_initIndex, final int p_priority) {
        boolean bFound = false;
        int i;
        for (i = p_initIndex; i < this.queue_array.size(); ++i) {
            final MinSpanTreePrimPriorityQueueMember queueMember = (MinSpanTreePrimPriorityQueueMember)this.queue_array.elementAt(i);
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
            final MinSpanTreePrimPriorityQueueMember queueMember = (MinSpanTreePrimPriorityQueueMember)this.queue_array.elementAt(i);
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
    
    public void insert(final int p_key, final int p_parent, final int p_priority) {
        final int insert_index = this.searchRight(0, p_priority);
        if (insert_index != -1) {
            this.queue_array.insertElementAt(new MinSpanTreePrimPriorityQueueMember(p_key, p_parent, p_priority), insert_index);
        }
        else {
            this.queue_array.addElement(new MinSpanTreePrimPriorityQueueMember(p_key, p_parent, p_priority));
        }
    }
    
    public void delete(final int p_member) {
        final int member_index = this.findMember(p_member);
        if (member_index == -1) {
            return;
        }
        this.queue_array.removeElementAt(member_index);
    }
    
    private int findMember(final int p_member) {
        for (int i = 0; i < this.queue_array.size(); ++i) {
            final MinSpanTreePrimPriorityQueueMember queueMember = (MinSpanTreePrimPriorityQueueMember)this.queue_array.elementAt(i);
            if (queueMember.getKey() == p_member) {
                return i;
            }
        }
        return -1;
    }
    
    public boolean isEmpty() {
        return this.queue_array.size() == 0;
    }
    
    public MinSpanTreePrimPriorityQueueMember remove() {
        if (this.queue_array.size() == 0) {
            return null;
        }
        final MinSpanTreePrimPriorityQueueMember queueMember = ((MinSpanTreePrimPriorityQueueMember)this.queue_array.elementAt(0)).copy();
        this.queue_array.removeElementAt(0);
        return queueMember;
    }
    
    public MinSpanTreePrimPriorityQueueMember peek() {
        if (this.queue_array.size() == 0) {
            return null;
        }
        final MinSpanTreePrimPriorityQueueMember queueMember = (MinSpanTreePrimPriorityQueueMember)this.queue_array.elementAt(0);
        return queueMember;
    }
    
    public Vector<E> getQueue() {
        return this.queue_array;
    }
    
    public int update(final int p_member, final int p_parent, final int p_priority) {
        final int member_index = this.findMember(p_member);
        if (member_index == -1) {
            this.insert(p_member, p_parent, p_priority);
            return -2;
        }
        final MinSpanTreePrimPriorityQueueMember queueMember = (MinSpanTreePrimPriorityQueueMember)this.queue_array.elementAt(member_index);
        if (queueMember.getPriority() > p_priority) {
            final int insert_index = this.searchLeft(member_index - 1, p_priority);
            this.queue_array.removeElementAt(member_index);
            if (insert_index != -1) {
                this.queue_array.insertElementAt(new MinSpanTreePrimPriorityQueueMember(p_member, p_parent, p_priority), insert_index);
            }
            else {
                this.queue_array.insertElementAt(new MinSpanTreePrimPriorityQueueMember(p_member, p_parent, p_priority), 0);
            }
            return member_index;
        }
        return -1;
    }
}
