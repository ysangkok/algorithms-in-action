public class LineMoveRequest {
    final public static int IS_X1_POSITION_INSERTED_ID = -45;
    final public static int IS_X2_POSITION_INSERTED_ID = -46;
    final public static int LEFT_NULL_CHILD = -50;
    final public static int RIGHT_NULL_CHILD = -51;
    protected int fromPositionInsertedId;
    protected int toPositionInsertedId;
    protected int parentPositionInsertedId;
    protected int x1PositionInsertedId;
    protected int x2PositionInsertedId;
    protected java.awt.Point fromPoint;
    protected java.awt.Point toPoint;
    protected java.awt.Point basePoint;
    protected String label;
    
    public LineMoveRequest()
    {
        super();
        this.fromPositionInsertedId = -1;
        this.toPositionInsertedId = -1;
        this.parentPositionInsertedId = -1;
        this.x1PositionInsertedId = -1;
        this.x2PositionInsertedId = -1;
        this.fromPoint = null;
        this.toPoint = null;
        this.basePoint = null;
    }
    
    public LineMoveRequest(int i, int i0)
    {
        super();
        this.fromPositionInsertedId = -1;
        this.toPositionInsertedId = -1;
        this.parentPositionInsertedId = -1;
        this.x1PositionInsertedId = -1;
        this.x2PositionInsertedId = -1;
        this.fromPoint = null;
        this.toPoint = null;
        this.basePoint = null;
        this.fromPositionInsertedId = i;
        this.toPositionInsertedId = i0;
    }
    
    public LineMoveRequest(int i, int i0, int i1)
    {
        super();
        this.fromPositionInsertedId = -1;
        this.toPositionInsertedId = -1;
        this.parentPositionInsertedId = -1;
        this.x1PositionInsertedId = -1;
        this.x2PositionInsertedId = -1;
        this.fromPoint = null;
        this.toPoint = null;
        this.basePoint = null;
        this.fromPositionInsertedId = i;
        this.toPositionInsertedId = i0;
        this.parentPositionInsertedId = i1;
    }
    
    public LineMoveRequest(java.awt.Point a, java.awt.Point a0)
    {
        super();
        this.fromPositionInsertedId = -1;
        this.toPositionInsertedId = -1;
        this.parentPositionInsertedId = -1;
        this.x1PositionInsertedId = -1;
        this.x2PositionInsertedId = -1;
        this.fromPoint = null;
        this.toPoint = null;
        this.basePoint = null;
        this.fromPoint = a;
        this.toPoint = a0;
    }
    
    public java.awt.Point getBasePoint()
    {
        java.awt.Point a = this.basePoint;
        return a;
    }
    
    public java.awt.Point getFromPoint()
    {
        java.awt.Point a = this.fromPoint;
        return a;
    }
    
    public int getFromPositionInsertedId()
    {
        int i = this.fromPositionInsertedId;
        return i;
    }
    
    public String getLabel()
    {
        String s = this.label;
        return s;
    }
    
    public int getParentPositionInsertedId()
    {
        int i = this.parentPositionInsertedId;
        return i;
    }
    
    public java.awt.Point getToPoint()
    {
        java.awt.Point a = this.toPoint;
        return a;
    }
    
    public int getToPositionInsertedId()
    {
        int i = this.toPositionInsertedId;
        return i;
    }
    
    public int getX1PositionInsertedId()
    {
        int i = this.x1PositionInsertedId;
        return i;
    }
    
    public int getX2PositionInsertedId()
    {
        int i = this.x2PositionInsertedId;
        return i;
    }
    
    public void setBasePoint(java.awt.Point a)
    {
        this.basePoint = a;
    }
    
    public void setFromPoint(java.awt.Point a)
    {
        this.fromPoint = a;
    }
    
    public void setFromPositionInsertedId(int i)
    {
        this.fromPositionInsertedId = i;
    }
    
    public void setLabel(String s)
    {
        this.label = s;
    }
    
    public void setToPoint(java.awt.Point a)
    {
        this.toPoint = a;
    }
    
    public void setToPositionInsertedId(int i)
    {
        this.toPositionInsertedId = i;
    }
    
    public void setX1PositionInsertedId(int i)
    {
        this.x1PositionInsertedId = i;
    }
    
    public void setX2PositionInsertedId(int i)
    {
        this.x2PositionInsertedId = i;
    }
}