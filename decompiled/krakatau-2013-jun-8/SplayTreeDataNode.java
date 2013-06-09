public class SplayTreeDataNode extends SplayTreeNode {
    final static int NODE_Y_GAP = 7;
    final static int NODE_X_GAP = 0;
    final static int NODE_WIDTH = 15;
    final static int NODE_HEIGHT = 15;
    private java.awt.Point position;
    private static SplayTreeNode newestNode;
    private static SplayTreeNode foundNode;
    private java.awt.Color currentBackgroundColor;
    final private static java.awt.Color BACKGROUND_COLOR;
    private SplayTreeDataItem dataItem;
    private SplayTreeDataNode parent;
    private SplayTreeNode left;
    private SplayTreeNode right;
    private com.cim.AIA.Line leftLink;
    private com.cim.AIA.Line rightLink;
    
    public SplayTreeDataNode(SplayTreeDataItem a)
    {
        super();
        java.awt.Color a0 = SplayTreeDataNode.BACKGROUND_COLOR;
        this.currentBackgroundColor = a0;
        this.dataItem = a;
        SplayTreeDataNode.newestNode = this;
        java.awt.Point a1 = new java.awt.Point();
        this.position = a1;
        SplayTreeNullNode a2 = new SplayTreeNullNode();
        this.left = a2;
        SplayTreeNode a3 = this.left;
        a3.setParent(this);
        java.awt.Point a4 = this.position;
        java.awt.Point a5 = new java.awt.Point();
        com.cim.AIA.Line a6 = new com.cim.AIA.Line(a4, a5);
        this.leftLink = a6;
        SplayTreeNullNode a7 = new SplayTreeNullNode();
        this.right = a7;
        SplayTreeNode a8 = this.right;
        a8.setParent(this);
        java.awt.Point a9 = this.position;
        java.awt.Point a10 = new java.awt.Point();
        com.cim.AIA.Line a11 = new com.cim.AIA.Line(a9, a10);
        this.rightLink = a11;
    }
    
    public SplayTreeNode getCopy()
    {
        SplayTreeDataItem a = this.dataItem;
        SplayTreeDataNode a0 = new SplayTreeDataNode(a);
        java.awt.Point a1 = this.position;
        java.awt.Point a2 = new java.awt.Point(a1);
        a0.position = a2;
        SplayTreeNode a3 = this.left;
        SplayTreeNode a4 = a3.getCopy();
        a0.left = a4;
        SplayTreeNode a5 = a0.left;
        a5.setParent(a0);
        java.awt.Point a6 = a0.position;
        java.awt.Point a7 = new java.awt.Point();
        com.cim.AIA.Line a8 = new com.cim.AIA.Line(a6, a7);
        a0.rightLink = a8;
        SplayTreeNode a9 = this.right;
        SplayTreeNode a10 = a9.getCopy();
        a0.right = a10;
        SplayTreeNode a11 = a0.right;
        a11.setParent(a0);
        java.awt.Point a12 = a0.position;
        java.awt.Point a13 = new java.awt.Point();
        com.cim.AIA.Line a14 = new com.cim.AIA.Line(a12, a13);
        a0.rightLink = a14;
        return a0;
    }
    
    public void setIsFound()
    {
        SplayTreeDataNode.foundNode = this;
    }
    
    public static void clearNodeColors()
    {
        SplayTreeDataNode.foundNode = null;
        SplayTreeDataNode.newestNode = null;
    }
    
    public int getKey()
    {
        SplayTreeDataItem a = this.dataItem;
        int i = a.getKey();
        return i;
    }
    
    public com.cim.AIA.Line getLeftLink()
    {
        com.cim.AIA.Line a = this.leftLink;
        return a;
    }
    
    public com.cim.AIA.Line getRightLink()
    {
        com.cim.AIA.Line a = this.rightLink;
        return a;
    }
    
    public SplayTreeNode getLeft()
    {
        SplayTreeNode a = this.left;
        return a;
    }
    
    public void setLeft(SplayTreeNode a)
    {
        this.left = a;
        a.setParent(this);
    }
    
    public SplayTreeNode getRight()
    {
        SplayTreeNode a = this.right;
        return a;
    }
    
    public void setRight(SplayTreeNode a)
    {
        this.right = a;
        a.setParent(this);
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
    
    public java.awt.Point getPosition()
    {
        java.awt.Point a = this.position;
        return a;
    }
    
    public void setUpperLeft(java.awt.Point a)
    {
        int i = a.x;
        int i0 = a.y;
        java.awt.Point a0 = this.layout(i, i0);
        java.awt.Point a1 = new java.awt.Point(a);
        SplayTreeDataNode.lowerRight = a1;
        int i1 = a.x;
        int i2 = a.y;
        java.awt.Point a2 = new java.awt.Point(i1, i2);
        SplayTreeDataNode.upperLeft = a2;
        java.awt.Point a3 = SplayTreeDataNode.lowerRight;
        int i3 = a.x;
        int i4 = a.y;
        java.awt.Point a4 = this.layout(i3, i4);
        int i5 = a4.x;
        a3.x = i5;
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
    
    public void setPosition(java.awt.Point a)
    {
        int i = a.x;
        int i0 = a.y;
        java.awt.Point a0 = this.layout(i, i0);
        int i1 = a.x;
        java.awt.Point a1 = this.position;
        int i2 = a1.x;
        int i3 = a.x;
        int i4 = i2 - i3;
        int i5 = i1 - i4;
        int i6 = a.y;
        java.awt.Point a2 = this.position;
        int i7 = a2.y;
        int i8 = a.y;
        int i9 = i7 - i8;
        int i10 = i6 - i9;
        java.awt.Point a3 = new java.awt.Point(i5, i10);
        this.setUpperLeft(a3);
    }
    
    protected java.awt.Point layout(int i, int i0)
    {
        java.awt.Point a = null;
        java.awt.Point a0 = null;
        java.awt.Point a1 = new java.awt.Point(i, i0);
        SplayTreeNode a2 = this.left;
        if(a2 == null)
        {
            a = a1;
        }
        else
        {
            SplayTreeNode a3 = this.left;
            int i1 = i0 + 7;
            int i2 = i1 + 15;
            java.awt.Point a4 = a3.layout(i, i2);
            int i3 = a4.x;
            int i4 = i3 + 0;
            a4.x = i4;
            a = a4;
        }
        a.y = i0;
        int i5 = a.x;
        int i6 = i5 + 15;
        a.x = i6;
        java.awt.Point a5 = this.position;
        int i7 = a.x;
        int i8 = i7 - 7;
        a5.x = i8;
        java.awt.Point a6 = this.position;
        int i9 = a.y;
        int i10 = i9 + 7;
        a6.y = i10;
        SplayTreeNode a7 = this.right;
        label1: {
            label0: {
                if(a7 == null)
                {
                    break label0;
                }
                SplayTreeNode a8 = this.right;
                int i11 = a.x;
                int i12 = i11 + 0;
                int i13 = a.y;
                int i14 = i13 + 15;
                int i15 = i14 + 7;
                java.awt.Point a9 = a8.layout(i12, i15);
                a0 = a9;
                break label1;
            }
            SplayTreeNode a10 = this.left;
            if(a10 != null)
            {
                a0 = a;
                break label1;
            }
            java.awt.Point a11 = this.position;
            int i16 = a11.y;
            int i17 = i16 + 7;
            java.awt.Point a12 = SplayTreeDataNode.lowerRight;
            int i18 = a12.y;
            if(i17 <= i18)
            {
                a0 = a;
            }
            else
            {
                java.awt.Point a13 = SplayTreeDataNode.lowerRight;
                java.awt.Point a14 = this.position;
                int i19 = a14.y;
                int i20 = i19 + 7;
                a13.y = i20;
                a0 = a;
            }
        }
        com.cim.AIA.Line a15 = this.leftLink;
        java.awt.Point a16 = this.position;
        a15.setStartPosition(a16);
        com.cim.AIA.Line a17 = this.leftLink;
        SplayTreeNode a18 = this.left;
        java.awt.Point a19 = a18.getPosition();
        a17.setEndPosition(a19);
        com.cim.AIA.Line a20 = this.rightLink;
        java.awt.Point a21 = this.position;
        a20.setStartPosition(a21);
        com.cim.AIA.Line a22 = this.rightLink;
        SplayTreeNode a23 = this.right;
        java.awt.Point a24 = a23.getPosition();
        a22.setEndPosition(a24);
        int i21 = a0.x;
        java.awt.Point a25 = this.position;
        int i22 = a25.y;
        java.awt.Point a26 = new java.awt.Point(i21, i22);
        return a26;
    }
    
    public void draw(java.awt.Graphics a)
    {
        SplayTreeNode a0 = this.left;
        label0: {
            if(a0 == null)
            {
                break label0;
            }
            SplayTreeNode a1 = this.left;
            int i = a1.getIsOnPath()?1:0;
            label2: {
                label1: {
                    if(i == 0)
                    {
                        break label1;
                    }
                    com.cim.AIA.Line a2 = this.leftLink;
                    java.awt.Color a3 = java.awt.Color.red;
                    a2.setColor(a3);
                    com.cim.AIA.Line a4 = this.leftLink;
                    a4.showAsThick(true);
                    break label2;
                }
                SplayTreeNode a5 = this.left;
                int i0 = a5.getIsOnSwitchPath()?1:0;
                if(i0 == 0)
                {
                    com.cim.AIA.Line a6 = this.leftLink;
                    java.awt.Color a7 = java.awt.Color.black;
                    a6.setColor(a7);
                    com.cim.AIA.Line a8 = this.leftLink;
                    a8.showAsThick(false);
                }
                else
                {
                    com.cim.AIA.Line a9 = this.leftLink;
                    java.awt.Color a10 = java.awt.Color.blue;
                    a9.setColor(a10);
                    com.cim.AIA.Line a11 = this.leftLink;
                    a11.showAsThick(true);
                }
            }
            com.cim.AIA.Line a12 = this.leftLink;
            a12.draw(a);
            SplayTreeNode a13 = this.left;
            a13.draw(a);
        }
        SplayTreeNode a14 = this.right;
        label3: {
            if(a14 == null)
            {
                break label3;
            }
            SplayTreeNode a15 = this.right;
            int i1 = a15.getIsOnPath()?1:0;
            label5: {
                label4: {
                    if(i1 == 0)
                    {
                        break label4;
                    }
                    com.cim.AIA.Line a16 = this.rightLink;
                    java.awt.Color a17 = java.awt.Color.red;
                    a16.setColor(a17);
                    com.cim.AIA.Line a18 = this.rightLink;
                    a18.showAsThick(true);
                    break label5;
                }
                SplayTreeNode a19 = this.right;
                int i2 = a19.getIsOnSwitchPath()?1:0;
                if(i2 == 0)
                {
                    com.cim.AIA.Line a20 = this.rightLink;
                    java.awt.Color a21 = java.awt.Color.black;
                    a20.setColor(a21);
                    com.cim.AIA.Line a22 = this.rightLink;
                    a22.showAsThick(false);
                }
                else
                {
                    com.cim.AIA.Line a23 = this.rightLink;
                    java.awt.Color a24 = java.awt.Color.blue;
                    a23.setColor(a24);
                    com.cim.AIA.Line a25 = this.rightLink;
                    a25.showAsThick(true);
                }
            }
            com.cim.AIA.Line a26 = this.rightLink;
            a26.draw(a);
            SplayTreeNode a27 = this.right;
            a27.draw(a);
        }
        this.drawNode(a);
    }
    
    public void drawNode(java.awt.Graphics a)
    {
        StringBuilder a0 = new StringBuilder();
        StringBuilder a1 = a0.append("");
        SplayTreeDataItem a2 = this.dataItem;
        int i = a2.getKey();
        StringBuilder a3 = a1.append(i);
        String s = a3.toString();
        SplayTreeNode a4 = SplayTreeDataNode.newestNode;
        label1: {
            label0: {
                if(a4 != this)
                {
                    break label0;
                }
                java.awt.Color a5 = SplayTreeColors.NEW_NODE_COLOR;
                this.currentBackgroundColor = a5;
                break label1;
            }
            SplayTreeNode a6 = SplayTreeDataNode.foundNode;
            if(a6 != this)
            {
                java.awt.Color a7 = SplayTreeDataNode.BACKGROUND_COLOR;
                this.currentBackgroundColor = a7;
            }
            else
            {
                java.awt.Color a8 = SplayTreeColors.FOUND_NODE_COLOR;
                this.currentBackgroundColor = a8;
            }
        }
        java.awt.Color a9 = this.currentBackgroundColor;
        java.awt.Color a10 = a9.brighter();
        a.setColor(a10);
        java.awt.Point a11 = this.position;
        int i0 = a11.x;
        int i1 = i0 - 7;
        int i2 = i1 - 1;
        java.awt.Point a12 = this.position;
        int i3 = a12.y;
        int i4 = i3 - 7;
        int i5 = i4 - 1;
        a.fillOval(i2, i5, 15, 15);
        java.awt.Color a13 = this.currentBackgroundColor;
        java.awt.Color a14 = a13.darker();
        a.setColor(a14);
        java.awt.Point a15 = this.position;
        int i6 = a15.x;
        int i7 = i6 - 7;
        int i8 = i7 + 1;
        java.awt.Point a16 = this.position;
        int i9 = a16.y;
        int i10 = i9 - 7;
        int i11 = i10 + 1;
        a.fillOval(i8, i11, 15, 15);
        java.awt.Color a17 = this.currentBackgroundColor;
        a.setColor(a17);
        java.awt.Point a18 = this.position;
        int i12 = a18.x;
        int i13 = i12 - 7;
        int i14 = i13 + 1;
        java.awt.Point a19 = this.position;
        int i15 = a19.y;
        int i16 = i15 - 7;
        int i17 = i16 + 1;
        a.fillOval(i14, i17, 13, 13);
        java.awt.Color a20 = java.awt.Color.black;
        a.setColor(a20);
        java.awt.Point a21 = this.position;
        int i18 = a21.x;
        int i19 = i18 - 7;
        int i20 = i19 - 1;
        java.awt.Point a22 = this.position;
        int i21 = a22.y;
        int i22 = i21 - 7;
        int i23 = i22 - 1;
        a.drawOval(i20, i23, 17, 17);
        java.awt.Point a23 = this.position;
        int i24 = a23.x;
        java.awt.FontMetrics a24 = a.getFontMetrics();
        int i25 = a24.stringWidth(s);
        int i26 = i25 / 2;
        int i27 = i24 - i26;
        java.awt.Point a25 = this.position;
        int i28 = a25.y;
        java.awt.FontMetrics a26 = a.getFontMetrics();
        int i29 = a26.getHeight();
        int i30 = i29 / 3;
        int i31 = i28 + i30;
        a.drawString(s, i27, i31);
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
        com.cim.AIA.Line a0 = this.leftLink;
        a0.setIntY1(i);
        com.cim.AIA.Line a1 = this.rightLink;
        a1.setIntY1(i);
        com.cim.AIA.Line a2 = this.leftLink;
        SplayTreeNode a3 = this.left;
        java.awt.Point a4 = a3.getPosition();
        a2.setEndPosition(a4);
        com.cim.AIA.Line a5 = this.rightLink;
        SplayTreeNode a6 = this.right;
        java.awt.Point a7 = a6.getPosition();
        a5.setEndPosition(a7);
        this.updateParentLink();
    }
    
    public void setX(int i)
    {
        java.awt.Point a = this.position;
        a.x = i;
        com.cim.AIA.Line a0 = this.leftLink;
        a0.setIntX1(i);
        com.cim.AIA.Line a1 = this.rightLink;
        a1.setIntX1(i);
        com.cim.AIA.Line a2 = this.leftLink;
        SplayTreeNode a3 = this.left;
        java.awt.Point a4 = a3.getPosition();
        a2.setEndPosition(a4);
        com.cim.AIA.Line a5 = this.rightLink;
        SplayTreeNode a6 = this.right;
        java.awt.Point a7 = a6.getPosition();
        a5.setEndPosition(a7);
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
            SplayTreeNode a1 = a0.left;
            if(a1 != this)
            {
                SplayTreeDataNode a2 = this.parent;
                com.cim.AIA.Line a3 = a2.rightLink;
                java.awt.Point a4 = this.getPosition();
                a3.setEndPosition(a4);
            }
            else
            {
                SplayTreeDataNode a5 = this.parent;
                com.cim.AIA.Line a6 = a5.leftLink;
                java.awt.Point a7 = this.getPosition();
                a6.setEndPosition(a7);
            }
        }
    }
    
    static
    {
        java.awt.Color a = SplayTreeColors.DEFAULT_NODE_COLOR;
        SplayTreeDataNode.BACKGROUND_COLOR = a;
    }
}