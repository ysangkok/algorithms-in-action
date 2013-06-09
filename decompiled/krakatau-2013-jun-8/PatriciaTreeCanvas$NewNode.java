public class PatriciaTreeCanvas$NewNode implements com.cim.AIA.Moveable {
    java.awt.Point currentPosition;
    com.cim.AIA.Node body;
    com.cim.AIA.Node dataNode;
    Boolean isSelfLinkLeft;
    Boolean isSelfLinkRight;
    com.cim.AIA.Node destinationBody;
    com.cim.AIA.Node destinationDataNode;
    PatriciaNode patriciaNode;
    boolean isDrawLabel;
    boolean hasBeenTweened;
    final PatriciaTreeCanvas this$0;
    
    PatriciaTreeCanvas$NewNode(PatriciaTreeCanvas a, PatriciaNode a0, java.awt.Point a1)
    {
        this.this$0 = a;
        super();
        this.isDrawLabel = true;
        this.init(a0, a1);
    }
    
    protected void drawNewNode(java.awt.Graphics a)
    {
        java.awt.Point a0 = this.currentPosition;
        com.cim.AIA.Node a1 = this.body;
        a1.setPosition(a0);
        com.cim.AIA.Node a2 = this.body;
        a2.draw(a);
        java.awt.Point a3 = new java.awt.Point();
        int i = a0.x;
        com.cim.AIA.Node a4 = this.body;
        int i0 = a4.getWidth();
        int i1 = i + i0;
        a3.x = i1;
        int i2 = a0.y;
        a3.y = i2;
        com.cim.AIA.Node a5 = this.dataNode;
        a5.setPosition(a3);
        com.cim.AIA.Node a6 = this.dataNode;
        a6.draw(a);
        int i3 = this.isDrawLabel?1:0;
        if(i3 != 0)
        {
            String s = localization.Messages.getString("PatriciaTreeCanvas.16");
            int i4 = a3.x;
            java.awt.FontMetrics a7 = a.getFontMetrics();
            String s0 = localization.Messages.getString("PatriciaTreeCanvas.17");
            int i5 = a7.stringWidth(s0);
            int i6 = i5 / 2;
            int i7 = i4 - i6;
            int i8 = a3.y;
            a.drawString(s, i7, i8);
        }
        com.cim.AIA.Node a8 = this.body;
        int i9 = a8.getWidth();
        com.cim.AIA.Node a9 = this.body;
        int i10 = a9.getHeight();
        Boolean a10 = this.isSelfLinkLeft;
        label0: {
            if(a10 == null)
            {
                break label0;
            }
            Boolean a11 = this.isSelfLinkLeft;
            int i11 = a11.booleanValue()?1:0;
            if(i11 == 0)
            {
                int i12 = a0.x;
                int i13 = a0.y;
                int i14 = i13 + i10;
                int i15 = a0.x;
                int i16 = i9 / 2;
                int i17 = i15 - i16;
                int i18 = a0.y;
                int i19 = i18 + i10;
                int i20 = i9 / 2;
                int i21 = i19 + i20;
                com.cim.AIA.Line a12 = new com.cim.AIA.Line(i12, i14, i17, i21);
                a12.showArrowHead(true);
                a12.setDistanceFromStartForArrowHead(-3);
                a12.draw(a);
            }
            else
            {
                int i22 = a0.x;
                int i23 = i9 / 2;
                int i24 = i22 - i23;
                int i25 = a0.y;
                int i26 = i10 / 2;
                int i27 = i25 + i26;
                PatriciaTreeCanvas a13 = this.this$0;
                PatriciaTreeCanvas.access$000(a13, a, false, i24, i27, i9, i10, 90, 270);
                PatriciaTreeCanvas a14 = this.this$0;
                int i28 = i9 / 2;
                int i29 = i24 + i28;
                java.awt.Point a15 = new java.awt.Point(i29, i27);
                PatriciaTreeCanvas.access$100(a14, a, a15, 15);
            }
        }
        Boolean a16 = this.isSelfLinkRight;
        label1: {
            if(a16 == null)
            {
                break label1;
            }
            Boolean a17 = this.isSelfLinkRight;
            int i30 = a17.booleanValue()?1:0;
            if(i30 == 0)
            {
                int i31 = a3.x;
                int i32 = i31 + i9;
                int i33 = a3.y;
                int i34 = i33 + i10;
                int i35 = a3.x;
                int i36 = i35 + i9;
                int i37 = i9 / 2;
                int i38 = i36 + i37;
                int i39 = a3.y;
                int i40 = i39 + i10;
                int i41 = i9 / 2;
                int i42 = i40 + i41;
                com.cim.AIA.Line a18 = new com.cim.AIA.Line(i32, i34, i38, i42);
                a18.showArrowHead(true);
                a18.setDistanceFromStartForArrowHead(-3);
                a18.draw(a);
            }
            else
            {
                int i43 = a3.x;
                com.cim.AIA.Node a19 = this.dataNode;
                int i44 = a19.getWidth();
                int i45 = i43 + i44;
                int i46 = i9 / 2;
                int i47 = i45 - i46;
                int i48 = a3.y;
                int i49 = i10 / 2;
                int i50 = i48 + i49;
                PatriciaTreeCanvas a20 = this.this$0;
                int i51 = i9 / 2;
                int i52 = i47 + i51;
                java.awt.Point a21 = new java.awt.Point(i52, i50);
                PatriciaTreeCanvas.access$100(a20, a, a21, 175);
                PatriciaTreeCanvas a22 = this.this$0;
                PatriciaTreeCanvas.access$000(a22, a, false, i47, i50, i9, i10, 180, 270);
            }
        }
    }
    
    public com.cim.AIA.HierarchyTree getHierarchyTree()
    {
        PatriciaNode a = this.patriciaNode;
        com.cim.AIA.HierarchyTree a0 = a.getHierarchyTree();
        return a0;
    }
    
    public java.awt.Point getPosition()
    {
        java.awt.Point a = this.currentPosition;
        return a;
    }
    
    public int getX()
    {
        java.awt.Point a = this.currentPosition;
        int i = a.x;
        return i;
    }
    
    public int getY()
    {
        java.awt.Point a = this.currentPosition;
        int i = a.y;
        return i;
    }
    
    private void init(PatriciaNode a, java.awt.Point a0)
    {
        this.hasBeenTweened = false;
        this.patriciaNode = a;
        com.cim.AIA.Node a1 = a.getBody();
        this.destinationBody = a1;
        PatriciaTreeDataItem a2 = a.getDataItem();
        com.cim.AIA.Node a3 = a2.getNode();
        this.destinationDataNode = a3;
        java.awt.Point a4 = new java.awt.Point(a0);
        this.currentPosition = a4;
        com.cim.AIA.Node a5 = a.getBody();
        Object a6 = a5.getObject();
        com.cim.AIA.Node a7 = new com.cim.AIA.Node(a6, 0);
        this.body = a7;
        com.cim.AIA.Node a8 = this.body;
        java.awt.Color a9 = PatriciaTreeColors.DIFFERENTIATING_COLOR;
        a8.setBackgroundColor(a9);
        PatriciaTreeDataItem a10 = a.getDataItem();
        com.cim.AIA.Node a11 = a10.getNode();
        Object a12 = a11.getObject();
        com.cim.AIA.Node a13 = new com.cim.AIA.Node(a12, 0);
        this.dataNode = a13;
        com.cim.AIA.Node a14 = this.dataNode;
        java.awt.Color a15 = PatriciaTreeColors.INSERT_BIT_ACTIVE_COLOR;
        a14.setBackgroundColor(a15);
        PatriciaNode a16 = a.getLeft();
        label1: {
            label0: {
                if(a16 != a)
                {
                    break label0;
                }
                Boolean a17 = new Boolean(true);
                this.isSelfLinkLeft = a17;
                break label1;
            }
            PatriciaNode a18 = a.getLeft();
            if(a18 != null)
            {
                Boolean a19 = new Boolean(false);
                this.isSelfLinkLeft = a19;
            }
        }
        PatriciaNode a20 = a.getRight();
        label3: {
            label2: {
                if(a20 != a)
                {
                    break label2;
                }
                Boolean a21 = new Boolean(true);
                this.isSelfLinkRight = a21;
                break label3;
            }
            PatriciaNode a22 = a.getRight();
            if(a22 != null)
            {
                Boolean a23 = new Boolean(false);
                this.isSelfLinkRight = a23;
            }
        }
    }
    
    public void setNode(PatriciaNode a, java.awt.Point a0)
    {
        PatriciaNode a1 = this.patriciaNode;
        if(a1 != a)
        {
            this.init(a, a0);
        }
    }
    
    public void setX(int i)
    {
        this.isDrawLabel = false;
        java.awt.Point a = this.currentPosition;
        a.x = i;
    }
    
    public void setY(int i)
    {
        this.isDrawLabel = false;
        java.awt.Point a = this.currentPosition;
        a.y = i;
    }
    
    public void shiftEntire(int i, int i0)
    {
        this.isDrawLabel = false;
        java.awt.Point a = this.currentPosition;
        int i1 = a.x;
        int i2 = i1 + i;
        a.x = i2;
        java.awt.Point a0 = this.currentPosition;
        int i3 = a0.y;
        int i4 = i3 + i;
        a0.y = i4;
    }
}