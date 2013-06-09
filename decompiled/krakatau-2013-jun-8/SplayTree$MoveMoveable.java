class SplayTree$MoveMoveable {
    private java.awt.Point from;
    private java.awt.Point to;
    private com.cim.AIA.Moveable moveable;
    private boolean isEntire;
    final SplayTree this$0;
    
    protected SplayTree$MoveMoveable(SplayTree a, com.cim.AIA.Moveable a0, java.awt.Point a1, java.awt.Point a2, boolean b)
    {
        this.this$0 = a;
        Object a3 = a0;
        int i = b?1:0;
        super();
        this.moveable = (com.cim.AIA.Moveable)a3;
        this.from = a1;
        this.to = a2;
        this.isEntire = i != 0;
    }
    
    static com.cim.AIA.Moveable access$000(SplayTree$MoveMoveable a)
    {
        Object a0 = a.moveable;
        return (com.cim.AIA.Moveable)a0;
    }
    
    static java.awt.Point access$100(SplayTree$MoveMoveable a)
    {
        java.awt.Point a0 = a.from;
        return a0;
    }
    
    static java.awt.Point access$200(SplayTree$MoveMoveable a)
    {
        java.awt.Point a0 = a.to;
        return a0;
    }
    
    static boolean access$300(SplayTree$MoveMoveable a)
    {
        int i = a.isEntire?1:0;
        return i != 0;
    }
}