public class SplayTreeNullNode extends SplayTreeNode {
    final static int NODE_Y_GAP = 0;
    final static int NODE_X_GAP = 0;
    final static int NODE_WIDTH = 0;
    final static int NODE_HEIGHT = 5;
    private java.awt.Point position;
    private SplayTreeDataNode parent;
    
    public SplayTreeNullNode()
    {
        super();
        java.awt.Point a = new java.awt.Point();
        this.position = a;
    }
    
    public SplayTreeNode getCopy()
    {
        SplayTreeNullNode a = new SplayTreeNullNode();
        java.awt.Point a0 = this.position;
        java.awt.Point a1 = new java.awt.Point(a0);
        a.position = a1;
        return a;
    }
    
    public void setParent(SplayTreeDataNode a)
    {
        this.parent = a;
    }
    
    public SplayTreeDataNode getParent()
    {
        SplayTreeDataNode a = this.parent;
        return a;
    }
    
    public void setNodePosition(java.awt.Point a)
    {
        java.awt.Point a0 = this.position;
        int i = a.x;
        a0.x = i;
        java.awt.Point a1 = this.position;
        int i0 = a.y;
        a1.y = i0;
    }
    
    public java.awt.Point getPosition()
    {
        java.awt.Point a = this.position;
        return a;
    }
    
    public void setUpperLeft(java.awt.Point a)
    {
        this.setPosition(a);
    }
    
    public void setPosition(java.awt.Point a)
    {
        this.position = a;
        java.awt.Point a0 = new java.awt.Point(a);
        SplayTreeNullNode.lowerRight = a0;
        java.awt.Point a1 = new java.awt.Point(a);
        SplayTreeNullNode.upperLeft = a1;
    }
    
    protected java.awt.Point layout(int i, int i0)
    {
        java.awt.Point a = this.position;
        int i1 = i + 0;
        a.x = i1;
        java.awt.Point a0 = this.position;
        int i2 = i0 + 5;
        a0.y = i2;
        java.awt.Point a1 = this.position;
        int i3 = a1.y;
        java.awt.Point a2 = SplayTreeNullNode.lowerRight;
        int i4 = a2.y;
        if(i3 > i4)
        {
            java.awt.Point a3 = SplayTreeNullNode.lowerRight;
            java.awt.Point a4 = this.position;
            int i5 = a4.y;
            a3.y = i5;
        }
        int i6 = i + 0;
        java.awt.Point a5 = new java.awt.Point(i6, i0);
        return a5;
    }
    
    public void draw(java.awt.Graphics a)
    {
        this.drawNode(a);
    }
    
    public void drawNode(java.awt.Graphics a)
    {
    }
    
    public void shiftEntire(int i, int i0)
    {
        java.awt.Point a = this.position;
        int i1 = a.x;
        int i2 = i1 + i;
        java.awt.Point a0 = this.position;
        int i3 = a0.y;
        int i4 = i3 + i0;
        java.awt.Point a1 = new java.awt.Point(i2, i4);
        this.setPosition(a1);
    }
    
    public int getY()
    {
        java.awt.Point a = this.position;
        int i = a.y;
        return i;
    }
    
    public int getX()
    {
        java.awt.Point a = this.position;
        int i = a.x;
        return i;
    }
    
    public void setY(int i)
    {
        java.awt.Point a = this.position;
        a.y = i;
        this.updateParentLink();
    }
    
    public void setX(int i)
    {
        java.awt.Point a = this.position;
        a.x = i;
        this.updateParentLink();
    }
    
    private void updateParentLink()
    {
        SplayTreeDataNode a = this.parent;
        label0: {
            if(a == null)
            {
                break label0;
            }
            SplayTreeDataNode a0 = this.parent;
            SplayTreeNode a1 = a0.getLeft();
            if(a1 != this)
            {
                SplayTreeDataNode a2 = this.parent;
                com.cim.AIA.Line a3 = a2.getRightLink();
                java.awt.Point a4 = this.getPosition();
                a3.setEndPosition(a4);
            }
            else
            {
                SplayTreeDataNode a5 = this.parent;
                com.cim.AIA.Line a6 = a5.getLeftLink();
                java.awt.Point a7 = this.getPosition();
                a6.setEndPosition(a7);
            }
        }
    }
}