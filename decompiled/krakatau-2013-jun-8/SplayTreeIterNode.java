abstract public class SplayTreeIterNode implements com.cim.AIA.Drawable, com.cim.AIA.Moveable {
    protected static java.awt.Point upperLeft;
    protected static java.awt.Point lowerRight;
    protected static int NO_OF_ELEMENTS;
    protected int elementNumber;
    private boolean isOnPath;
    private boolean isOnSwitchPath;
    
    public SplayTreeIterNode()
    {
        super();
        this.isOnPath = false;
        this.isOnSwitchPath = false;
    }
    
    abstract public SplayTreeIterNode getCopy();
    
    
    abstract public void setUpperLeft(java.awt.Point arg);
    
    
    abstract public void setNodePosition(java.awt.Point arg);
    
    
    abstract public void setPosition(java.awt.Point arg);
    
    
    abstract public java.awt.Point getPosition();
    
    
    abstract public void setParent(SplayTreeIterDataNode arg);
    
    
    abstract public SplayTreeIterDataNode getParent();
    
    
    final public java.awt.Rectangle getRectangle()
    {
        java.awt.Rectangle a = null;
        java.awt.Point a0 = SplayTreeIterNode.upperLeft;
        label2: {
            label1: {
                label0: {
                    if(a0 == null)
                    {
                        break label0;
                    }
                    java.awt.Point a1 = SplayTreeIterNode.lowerRight;
                    if(a1 != null)
                    {
                        break label1;
                    }
                }
                a = null;
                break label2;
            }
            java.awt.Point a2 = SplayTreeIterNode.upperLeft;
            int i = a2.x;
            java.awt.Point a3 = SplayTreeIterNode.upperLeft;
            int i0 = a3.y;
            java.awt.Point a4 = SplayTreeIterNode.lowerRight;
            int i1 = a4.x;
            java.awt.Point a5 = SplayTreeIterNode.upperLeft;
            int i2 = a5.x;
            int i3 = i1 - i2;
            java.awt.Point a6 = SplayTreeIterNode.lowerRight;
            int i4 = a6.y;
            java.awt.Point a7 = SplayTreeIterNode.upperLeft;
            int i5 = a7.y;
            int i6 = i4 - i5;
            java.awt.Rectangle a8 = new java.awt.Rectangle(i, i0, i3, i6);
            a = a8;
        }
        return a;
    }
    
    final public boolean getIsOnSwitchPath()
    {
        int i = this.isOnSwitchPath?1:0;
        return i != 0;
    }
    
    final public void setIsOnSwitchPath(boolean b)
    {
        this.isOnSwitchPath = b;
    }
    
    final public boolean getIsOnPath()
    {
        int i = this.isOnPath?1:0;
        return i != 0;
    }
    
    final public void setIsOnPath(boolean b)
    {
        this.isOnPath = b;
    }
    
    final public int getIdentifier()
    {
        int i = this.elementNumber;
        return i;
    }
    
    abstract protected java.awt.Point layout(int arg, int arg0);
    
    
    abstract public void draw(java.awt.Graphics arg);
    
    
    final public void draw(java.awt.Graphics a, java.awt.Point a0)
    {
        this.setPosition(a0);
        this.draw(a);
    }
    
    abstract public void drawNode(java.awt.Graphics arg);
}