public class PatriciaTreeIterCanvas$NewNode implements com.cim.AIA.Moveable {
    java.awt.Point currentPosition;
    com.cim.AIA.Node body;
    com.cim.AIA.Node dataNode;
    Boolean isSelfLinkLeft;
    Boolean isSelfLinkRight;
    com.cim.AIA.Node destinationBody;
    com.cim.AIA.Node destinationDataNode;
    PatriciaTreeIterNode patriciaNode;
    boolean isDrawLabel;
    boolean hasBeenTweened;
    final PatriciaTreeIterCanvas this$0;
    
    PatriciaTreeIterCanvas$NewNode(PatriciaTreeIterCanvas a, PatriciaTreeIterNode a0, java.awt.Point a1)
    {
        this.this$0 = a;
        super();
        this.isDrawLabel = true;
        this.init(a0, a1);
    }
    
    private void init(PatriciaTreeIterNode a, java.awt.Point a0)
    {
        this.hasBeenTweened = false;
        this.patriciaNode = a;
        com.cim.AIA.Node a1 = a.getBody();
        this.destinationBody = a1;
        PatriciaTreeIterDataItem a2 = a.getDataItem();
        com.cim.AIA.Node a3 = a2.getNode();
        this.destinationDataNode = a3;
        java.awt.Point a4 = new java.awt.Point(a0);
        this.currentPosition = a4;
        com.cim.AIA.Node a5 = a.getBody();
        Object a6 = a5.getObject();
        com.cim.AIA.Node a7 = new com.cim.AIA.Node(a6, 0);
        this.body = a7;
        com.cim.AIA.Node a8 = this.body;
        a8.setHeight(14);
        com.cim.AIA.Node a9 = this.body;
        a9.setWidth(15);
        com.cim.AIA.Node a10 = this.body;
        java.awt.Color a11 = PatriciaTreeIterColors.DIFFERENTIATING_COLOR;
        a10.setBackgroundColor(a11);
        PatriciaTreeIterDataItem a12 = a.getDataItem();
        com.cim.AIA.Node a13 = a12.getNode();
        Object a14 = a13.getObject();
        com.cim.AIA.Node a15 = new com.cim.AIA.Node(a14, 0);
        this.dataNode = a15;
        com.cim.AIA.Node a16 = this.dataNode;
        java.awt.Color a17 = PatriciaTreeIterColors.INSERT_BIT_ACTIVE_COLOR;
        a16.setBackgroundColor(a17);
        PatriciaTreeIterNode a18 = a.getLeft();
        label1: {
            label0: {
                if(a18 != a)
                {
                    break label0;
                }
                Boolean a19 = new Boolean(true);
                this.isSelfLinkLeft = a19;
                break label1;
            }
            PatriciaTreeIterNode a20 = a.getLeft();
            if(a20 != null)
            {
                Boolean a21 = new Boolean(false);
                this.isSelfLinkLeft = a21;
            }
        }
        PatriciaTreeIterNode a22 = a.getRight();
        label3: {
            label2: {
                if(a22 != a)
                {
                    break label2;
                }
                Boolean a23 = new Boolean(true);
                this.isSelfLinkRight = a23;
                break label3;
            }
            PatriciaTreeIterNode a24 = a.getRight();
            if(a24 != null)
            {
                Boolean a25 = new Boolean(false);
                this.isSelfLinkRight = a25;
            }
        }
    }
    
    public void setNode(PatriciaTreeIterNode a, java.awt.Point a0)
    {
        PatriciaTreeIterNode a1 = this.patriciaNode;
        if(a1 != a)
        {
            this.init(a, a0);
        }
    }
    
    public com.cim.AIA.HierarchyTree getHierarchyTree()
    {
        PatriciaTreeIterNode a = this.patriciaNode;
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
            String s = localization.Messages.getString("PatriciaTreeIterCanvas.16");
            int i4 = a3.x;
            java.awt.FontMetrics a7 = a.getFontMetrics();
            String s0 = localization.Messages.getString("PatriciaTreeIterCanvas.17");
            int i5 = a7.stringWidth(s0);
            int i6 = i5 / 2;
            int i7 = i4 - i6;
            int i8 = a3.y;
            a.drawString(s, i7, i8);
        }
        com.cim.AIA.Node a8 = this.body;
        int i9 = a8.getWidth();
        int i10 = i9 + 5;
        com.cim.AIA.Node a9 = this.body;
        int i11 = a9.getHeight();
        int i12 = i11 + 6;
        Boolean a10 = this.isSelfLinkLeft;
        label0: {
            if(a10 == null)
            {
                break label0;
            }
            Boolean a11 = this.isSelfLinkLeft;
            int i13 = a11.booleanValue()?1:0;
            if(i13 == 0)
            {
                int i14 = a0.x;
                int i15 = a0.y;
                int i16 = i15 + i12;
                int i17 = a0.x;
                int i18 = i10 / 2;
                int i19 = i17 - i18;
                int i20 = a0.y;
                int i21 = i20 + i12;
                int i22 = i10 / 2;
                int i23 = i21 + i22;
                com.cim.AIA.Line a12 = new com.cim.AIA.Line(i14, i16, i19, i23);
                a12.showArrowHead(true);
                a12.setDistanceFromStartForArrowHead(-3);
                a12.draw(a);
            }
            else
            {
                int i24 = a0.x;
                int i25 = i10 / 2;
                int i26 = i24 - i25;
                int i27 = a0.y;
                int i28 = i12 / 2;
                int i29 = i27 + i28;
                PatriciaTreeIterCanvas a13 = this.this$0;
                PatriciaTreeIterCanvas.access$000(a13, a, false, i26, i29, i10, i12, 90, 270);
                PatriciaTreeIterCanvas a14 = this.this$0;
                int i30 = i10 / 2;
                int i31 = i26 + i30;
                java.awt.Point a15 = new java.awt.Point(i31, i29);
                PatriciaTreeIterCanvas.access$100(a14, a, a15, 15);
            }
        }
        Boolean a16 = this.isSelfLinkRight;
        label1: {
            if(a16 == null)
            {
                break label1;
            }
            Boolean a17 = this.isSelfLinkRight;
            int i32 = a17.booleanValue()?1:0;
            if(i32 == 0)
            {
                int i33 = a3.x;
                int i34 = i33 + i10;
                int i35 = a3.y;
                int i36 = i35 + i12;
                int i37 = a3.x;
                int i38 = i37 + i10;
                int i39 = i10 / 2;
                int i40 = i38 + i39;
                int i41 = a3.y;
                int i42 = i41 + i12;
                int i43 = i10 / 2;
                int i44 = i42 + i43;
                com.cim.AIA.Line a18 = new com.cim.AIA.Line(i34, i36, i40, i44);
                a18.showArrowHead(true);
                a18.setDistanceFromStartForArrowHead(-3);
                a18.draw(a);
            }
            else
            {
                int i45 = a3.x;
                com.cim.AIA.Node a19 = this.dataNode;
                int i46 = a19.getWidth();
                int i47 = i45 + i46;
                int i48 = i10 / 2;
                int i49 = i47 - i48;
                int i50 = a3.y;
                int i51 = i12 / 2;
                int i52 = i50 + i51;
                PatriciaTreeIterCanvas a20 = this.this$0;
                int i53 = i10 / 2;
                int i54 = i49 + i53;
                java.awt.Point a21 = new java.awt.Point(i54, i52);
                PatriciaTreeIterCanvas.access$100(a20, a, a21, 175);
                PatriciaTreeIterCanvas a22 = this.this$0;
                PatriciaTreeIterCanvas.access$000(a22, a, false, i49, i52, i10, i12, 180, 270);
            }
        }
    }
}