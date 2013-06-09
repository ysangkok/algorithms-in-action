abstract public class RadixTrieMultiNode implements com.cim.AIA.Drawable {
    final static int NODE_Y_GAP = 40;
    protected static java.awt.Point upperLeft;
    protected static java.awt.Point lowerRight;
    final static int NULL_WIDTH = 8;
    final static int NULL_HEIGHT = 13;
    private boolean isLinkedIn;
    private boolean isOnPath;
    protected java.awt.Point position;
    protected RadixTrieMultiInternalNode parent;
    
    public RadixTrieMultiNode()
    {
        super();
        this.isLinkedIn = false;
        this.isOnPath = false;
    }
    
    abstract public RadixTrieMultiInternalNode getParent();
    
    
    final public void setIsLinkedIn()
    {
        this.isLinkedIn = true;
    }
    
    final public boolean getIsLinkedIn()
    {
        int i = this.isLinkedIn?1:0;
        return i != 0;
    }
    
    public void setIsOnPath(boolean b)
    {
        this.isOnPath = b;
    }
    
    public boolean getIsOnPath()
    {
        int i = this.isOnPath?1:0;
        return i != 0;
    }
    
    abstract public int getRadius();
    
    
    abstract public void setPosition(java.awt.Point arg);
    
    
    abstract public java.awt.Point getPosition();
    
    
    abstract protected java.awt.Point layout(int arg, int arg0);
    
    
    abstract public java.awt.Rectangle getRectangle();
    
    
    abstract public com.cim.AIA.HierarchyTree getHierarchyTree();
    
    
    abstract public void setBackgroundColor(java.awt.Color arg);
    
    
    abstract public void draw(java.awt.Graphics arg);
    
    
    public void draw(java.awt.Graphics a, java.awt.Point a0)
    {
        this.setPosition(a0);
        this.draw(a);
    }
    
    abstract public void drawNode(java.awt.Graphics arg);
}