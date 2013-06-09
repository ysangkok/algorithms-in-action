package aia.graph.common;

public class QueueRequest {
    final public static int QUEUE_INSERT = 1;
    final public static int QUEUE_DELETE = 2;
    final public static int QUEUE_CHANGE = 3;
    private boolean m_insert;
    private int m_key;
    private int m_type;
    private int m_from_key;
    private int m_to_key;
    private int m_param;
    
    public QueueRequest(boolean b, int i)
    {
        super();
        int i0 = b?1:0;
        this.m_insert = false;
        this.m_key = -1;
        this.m_type = 1;
        this.m_from_key = -1;
        this.m_to_key = -1;
        this.m_param = 0;
        this.m_insert = i0 != 0;
        this.m_key = i;
    }
    
    public QueueRequest(int i, int i0)
    {
        super();
        this.m_insert = false;
        this.m_key = -1;
        this.m_type = 1;
        this.m_from_key = -1;
        this.m_to_key = -1;
        this.m_param = 0;
        this.m_type = i;
        this.m_from_key = i0;
        this.m_to_key = i0;
    }
    
    public QueueRequest(int i, int i0, int i1)
    {
        super();
        this.m_insert = false;
        this.m_key = -1;
        this.m_type = 1;
        this.m_from_key = -1;
        this.m_to_key = -1;
        this.m_param = 0;
        this.m_type = i;
        this.m_from_key = i0;
        this.m_to_key = i1;
    }
    
    public int getType()
    {
        int i = this.m_type;
        return i;
    }
    
    public int getToKey()
    {
        int i = this.m_to_key;
        return i;
    }
    
    public int getFromKey()
    {
        int i = this.m_from_key;
        return i;
    }
    
    public void setParam(int i)
    {
        this.m_param = i;
    }
    
    public int getKey()
    {
        int i = this.m_key;
        return i;
    }
    
    public boolean isInsert()
    {
        int i = this.m_insert?1:0;
        return i != 0;
    }
    
    public int getParam()
    {
        int i = this.m_param;
        return i;
    }
}