package aia.graph.common;

public class GraphMapsNode extends com.cim.AIA.Node {
    protected int m_NodeType;
    protected Object m_Value;
    protected int m_ToNode;
    protected int m_FromNode;
    final public static int GRAPH_NODE = 1;
    final public static int MATRIX_NODE = 2;
    final public static int STRUCTURE_NODE = 3;
    final public static int FLAG_NODE1 = 4;
    final public static int FLAG_NODE2 = 5;
    final public static int FLAG_NODE3 = 6;
    
    public GraphMapsNode(Object a, int i, int i0)
    {
        super(a, i);
        this.m_NodeType = 0;
        this.m_Value = null;
        this.m_ToNode = -1;
        this.m_FromNode = -1;
        this.m_NodeType = i0;
        this.m_Value = a;
    }
    
    public int getNodeType()
    {
        int i = this.m_NodeType;
        return i;
    }
    
    public int getIntValue()
    {
        Object a = this.m_Value;
        Integer dummy = (Integer)a;
        Integer a0 = (Integer)a;
        int i = a0.intValue();
        return i;
    }
    
    public String getStrValue()
    {
        Object a = this.m_Value;
        String dummy = (String)a;
        String s = (String)a;
        return s;
    }
    
    public void setTo(int i)
    {
        this.m_ToNode = i;
    }
    
    public void setFrom(int i)
    {
        this.m_FromNode = i;
    }
    
    public int getTo()
    {
        int i = this.m_ToNode;
        return i;
    }
    
    public int getFrom()
    {
        int i = this.m_FromNode;
        return i;
    }
}