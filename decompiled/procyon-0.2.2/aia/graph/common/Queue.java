package aia.graph.common;

import java.util.*;

public class Queue
{
    protected Vector<Object> m_queueItems;
    
    public Queue() {
        super();
        this.m_queueItems = new Vector();
    }
    
    public void empty() {
        this.m_queueItems = new Vector();
    }
    
    public Object get() {
        Object tempObj = null;
        if (this.m_queueItems.size() > 0) {
            tempObj = this.m_queueItems.elementAt(0);
            this.m_queueItems.removeElementAt(0);
        }
        return tempObj;
    }
    
    public int getCount() {
        return this.m_queueItems.size();
    }
    
    public Vector<Object> getQueue() {
        return this.m_queueItems;
    }
    
    public boolean isEmpty() {
        return this.m_queueItems.size() == 0;
    }
    
    public Object peek() {
        Object tempObj = null;
        if (this.m_queueItems.size() > 0) {
            tempObj = this.m_queueItems.elementAt(0);
        }
        return tempObj;
    }
    
    public void put(final Object p_queueItem) {
        this.m_queueItems.addElement(p_queueItem);
    }
}
