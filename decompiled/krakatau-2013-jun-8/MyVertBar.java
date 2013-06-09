public class MyVertBar extends com.cim.AIA.VerticalBar {
    private boolean _isEmpty;
    
    public MyVertBar(int i, int i0, java.awt.Color a, java.awt.Point a0)
    {
        super(i, i0, a, a0);
        this._isEmpty = false;
    }
    
    public MyVertBar(int i, Integer a, java.awt.Color a0, java.awt.Point a1)
    {
        int i0 = a.intValue();
        this(i, i0, a0, a1);
    }
    
    public void draw(java.awt.Graphics a)
    {
        ((com.cim.AIA.VerticalBar)this).draw(a);
    }
    
    public boolean isEmpty()
    {
        int i = this._isEmpty?1:0;
        return i != 0;
    }
    
    public void setIsEmpty(boolean b)
    {
        this._isEmpty = b;
    }
}