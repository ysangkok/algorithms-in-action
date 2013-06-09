package aia.graph.common;

public class QueueRequest
{
    public static final int QUEUE_INSERT = 1;
    public static final int QUEUE_DELETE = 2;
    public static final int QUEUE_CHANGE = 3;
    private boolean m_insert;
    private int m_key;
    private int m_type;
    private int m_from_key;
    private int m_to_key;
    private int m_param;
    
    public QueueRequest(final boolean p_insert, final int p_key) {
        super();
        this.m_insert = false;
        this.m_key = -1;
        this.m_type = 1;
        this.m_from_key = -1;
        this.m_to_key = -1;
        this.m_param = 0;
        this.m_insert = p_insert;
        this.m_key = p_key;
    }
    
    public QueueRequest(final int p_type, final int p_key) {
        super();
        this.m_insert = false;
        this.m_key = -1;
        this.m_type = 1;
        this.m_from_key = -1;
        this.m_to_key = -1;
        this.m_param = 0;
        this.m_type = p_type;
        this.m_from_key = p_key;
        this.m_to_key = p_key;
    }
    
    public QueueRequest(final int p_type, final int p_from_key, final int p_to_key) {
        super();
        this.m_insert = false;
        this.m_key = -1;
        this.m_type = 1;
        this.m_from_key = -1;
        this.m_to_key = -1;
        this.m_param = 0;
        this.m_type = p_type;
        this.m_from_key = p_from_key;
        this.m_to_key = p_to_key;
    }
    
    public int getType() {
        return this.m_type;
    }
    
    public int getToKey() {
        return this.m_to_key;
    }
    
    public int getFromKey() {
        return this.m_from_key;
    }
    
    public void setParam(final int p_param) {
        this.m_param = p_param;
    }
    
    public int getKey() {
        return this.m_key;
    }
    
    public boolean isInsert() {
        return this.m_insert;
    }
    
    public int getParam() {
        return this.m_param;
    }
}
