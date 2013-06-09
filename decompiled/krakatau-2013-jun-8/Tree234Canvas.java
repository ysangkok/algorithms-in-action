public class Tree234Canvas extends com.cim.AIA.AlgorithmCanvas {
    protected Tree234Tree redBlackTree;
    protected com.cim.AIA.HierarchyTree hierarchyTree;
    protected com.cim.AIA.HierarchyTree balancedHierarchyTree;
    protected com.cim.AIA.Node animationNode;
    protected com.cim.AIA.Node growNode;
    protected com.cim.AIA.ElementArray elementArray;
    protected java.util.Hashtable oldPositions;
    protected int nodeWidth;
    protected int xTextPadding;
    protected int yBuffer;
    protected int xBuffer;
    protected com.cim.AIA.Line ptrLine;
    protected com.cim.AIA.Line parentLine;
    protected com.cim.AIA.Line grandParentLine;
    protected com.cim.AIA.Line greatGrandParentLine;
    protected boolean showAs234Only;
    final private int SPEED_UP_FACTOR;
    final protected static String PTR_LABEL = "Ptr";
    final protected static String PARENT_LABEL;
    final protected static String GRAND_PARENT_LABEL;
    final protected static String GREAT_GRAND_PARENT_LABEL;
    
    public Tree234Canvas()
    {
        super();
        this.nodeWidth = 20;
        this.xTextPadding = 30;
        this.yBuffer = 30;
        this.xBuffer = 30;
        this.showAs234Only = true;
        this.SPEED_UP_FACTOR = 3;
    }
    
    public void setShowAs234Only(boolean b)
    {
        this.showAs234Only = b;
    }
    
    public void display234(java.awt.Graphics a)
    {
        com.cim.AIA.ElementArray a0 = this.elementArray;
        if(a0 != null)
        {
            com.cim.AIA.ElementArray a1 = this.elementArray;
            a1.draw(a);
        }
        com.cim.AIA.Node a2 = this.growNode;
        if(a2 != null)
        {
            com.cim.AIA.Node a3 = this.growNode;
            a3.draw(a);
        }
        com.cim.AIA.HierarchyTree a4 = this.balancedHierarchyTree;
        if(a4 != null)
        {
            com.cim.AIA.HierarchyTree a5 = this.balancedHierarchyTree;
            a5.draw(a);
        }
        com.cim.AIA.Node a6 = this.animationNode;
        if(a6 != null)
        {
            com.cim.AIA.Node a7 = this.animationNode;
            a7.draw(a);
        }
    }
    
    public void displayRB(java.awt.Graphics a)
    {
        com.cim.AIA.Line a0 = this.ptrLine;
        if(a0 != null)
        {
            com.cim.AIA.Line a1 = this.ptrLine;
            a1.draw(a);
        }
        com.cim.AIA.Line a2 = this.parentLine;
        if(a2 != null)
        {
            com.cim.AIA.Line a3 = this.parentLine;
            a3.draw(a);
        }
        com.cim.AIA.Line a4 = this.grandParentLine;
        if(a4 != null)
        {
            com.cim.AIA.Line a5 = this.grandParentLine;
            a5.draw(a);
        }
        com.cim.AIA.HierarchyTree a6 = this.hierarchyTree;
        if(a6 != null)
        {
            com.cim.AIA.HierarchyTree a7 = this.hierarchyTree;
            a7.draw(a);
        }
    }
    
    public void displayAlgorithm(java.awt.Graphics a)
    {
        Tree234Tree a0 = this.redBlackTree;
        label0: {
            if(a0 == null)
            {
                break label0;
            }
            int i = this.showAs234Only?1:0;
            label1: {
                if(i != 0)
                {
                    break label1;
                }
                this.displayRB(a);
                break label0;
            }
            Tree234Tree a1 = this.redBlackTree;
            int i0 = a1.update234?1:0;
            if(i0 == 0)
            {
                String s = localization.Messages.getString("Tree234Canvas.4");
                a.drawString(s, 20, 20);
            }
            else
            {
                this.display234(a);
            }
        }
    }
    
    protected com.cim.AIA.MultipleTween getMoveTweens(com.cim.AIA.HierarchyTree a, java.util.Hashtable a0, java.util.Hashtable a1)
    {
        com.cim.AIA.MultipleTween a2 = new com.cim.AIA.MultipleTween((com.cim.AIA.Paintable)this);
        this.getMoveTweens(a, a0, a1, a2);
        return a2;
    }
    
    protected void getMoveTweens(com.cim.AIA.HierarchyTree a, java.util.Hashtable a0, java.util.Hashtable a1, com.cim.AIA.MultipleTween a2)
    {
        label0: {
            if(a == null)
            {
                break label0;
            }
            com.cim.AIA.Node a3 = a.getNodeAt(1);
            com.cim.AIA.Node a4 = a.getNodeAt(2);
            label1: {
                if(a3 == null)
                {
                    break label1;
                }
                if(a4 != null)
                {
                    break label1;
                }
                int i = a3.getIdentifier();
                Integer a5 = new Integer(i);
                Object a6 = a0.get((Object)a5);
                java.awt.Point dummy = (java.awt.Point)a6;
                java.awt.Point a7 = (java.awt.Point)a6;
                int i0 = a3.getIdentifier();
                Integer a8 = new Integer(i0);
                Object a9 = a1.get((Object)a8);
                java.awt.Point dummy0 = (java.awt.Point)a9;
                java.awt.Point a10 = (java.awt.Point)a9;
                if(a7 == null)
                {
                    break label1;
                }
                if(a10 == null)
                {
                    break label1;
                }
                int i1 = a7.x;
                int i2 = a10.x;
                label2: {
                    if(i1 != i2)
                    {
                        break label2;
                    }
                    int i3 = a7.y;
                    int i4 = a10.y;
                    if(i3 == i4)
                    {
                        break label1;
                    }
                }
                int i5 = this.numberOfTweenSteps;
                int i6 = i5 / 3;
                com.cim.AIA.MoveTween a11 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Moveable)a, a7, a10, false, i6);
                a2.add((com.cim.AIA.Tween)a11);
            }
            java.util.Vector a12 = a.getChildren();
            int i7 = 0;
            while(true)
            {
                int i8 = a12.size();
                if(i7 >= i8)
                {
                    break;
                }
                else
                {
                    Object a13 = a12.elementAt(i7);
                    com.cim.AIA.HierarchyTree dummy1 = (com.cim.AIA.HierarchyTree)a13;
                    com.cim.AIA.HierarchyTree a14 = (com.cim.AIA.HierarchyTree)a13;
                    this.getMoveTweens(a14, a0, a1, a2);
                    int i9 = i7 + 1;
                    i7 = i9;
                }
            }
        }
    }
    
    protected java.util.Hashtable getPositions(com.cim.AIA.HierarchyTree a)
    {
        java.util.Hashtable a0 = new java.util.Hashtable();
        this.getPositions(a, a0);
        return a0;
    }
    
    protected void getPositions(com.cim.AIA.HierarchyTree a, java.util.Hashtable a0)
    {
        label0: {
            if(a == null)
            {
                break label0;
            }
            java.util.Vector a1 = a.getNodes();
            int i = 0;
            while(true)
            {
                int i0 = a1.size();
                if(i >= i0)
                {
                    break;
                }
                Object a2 = a1.elementAt(i);
                com.cim.AIA.Node dummy = (com.cim.AIA.Node)a2;
                com.cim.AIA.Node a3 = (com.cim.AIA.Node)a2;
                label1: {
                    if(a3 == null)
                    {
                        break label1;
                    }
                    int i1 = a3.getIsVisible()?1:0;
                    if(i1 != 0)
                    {
                        int i2 = a3.getWidth();
                        int i3 = a3.getIdentifier();
                        Integer a4 = new Integer(i3);
                        int i4 = a.getX();
                        int i5 = i * i2;
                        int i6 = i4 + i5;
                        int i7 = a.getY();
                        java.awt.Point a5 = new java.awt.Point(i6, i7);
                        Object a6 = a0.put((Object)a4, (Object)a5);
                    }
                }
                int i8 = i + 1;
                i = i8;
            }
            java.util.Vector a7 = a.getChildren();
            int i9 = 0;
            while(true)
            {
                int i10 = a7.size();
                if(i9 >= i10)
                {
                    break;
                }
                else
                {
                    Object a8 = a7.elementAt(i9);
                    com.cim.AIA.HierarchyTree dummy0 = (com.cim.AIA.HierarchyTree)a8;
                    com.cim.AIA.HierarchyTree a9 = (com.cim.AIA.HierarchyTree)a8;
                    this.getPositions(a9, a0);
                    int i11 = i9 + 1;
                    i9 = i11;
                }
            }
        }
    }
    
    protected com.cim.AIA.Line initialiseLine(java.awt.Graphics a, java.awt.Point a0, com.cim.AIA.HierarchyTree a1, String s)
    {
        com.cim.AIA.Line a2 = null;
        if(a1 == null)
        {
            a2 = null;
        }
        else
        {
            int i = a0.x;
            int i0 = a0.y;
            java.awt.Point a3 = a1.getPosition();
            int i1 = a3.x;
            int i2 = this.nodeWidth;
            int i3 = i2 / 2;
            int i4 = i1 + i3;
            java.awt.Point a4 = a1.getPosition();
            int i5 = a4.y;
            com.cim.AIA.Line a5 = new com.cim.AIA.Line(i, i0, i4, i5);
            a5.showArrowHead(true);
            a5.setLabel(s);
            a5.setDistanceFromStartForArrowHead(-3);
            a5.setDistanceFromStartForLabel(-1);
            java.awt.FontMetrics a6 = a.getFontMetrics();
            int i6 = a6.stringWidth(s);
            int i7 = i6 / 2;
            int i8 = -1 * i7;
            a5.setXLabelOffset(i8);
            java.awt.Color a7 = java.awt.Color.gray;
            a5.setColor(a7);
            a2 = a5;
        }
        return a2;
    }
    
    protected void initaliseLines(java.awt.Graphics a, int i)
    {
        java.awt.Dimension a0 = this.getSize();
        int i0 = a0.width;
        int i1 = i0 / 2;
        java.awt.FontMetrics a1 = a.getFontMetrics();
        StringBuilder a2 = new StringBuilder();
        StringBuilder a3 = a2.append("Ptr");
        String s = Tree234Canvas.PARENT_LABEL;
        StringBuilder a4 = a3.append(s);
        String s0 = Tree234Canvas.GRAND_PARENT_LABEL;
        StringBuilder a5 = a4.append(s0);
        String s1 = Tree234Canvas.GREAT_GRAND_PARENT_LABEL;
        StringBuilder a6 = a5.append(s1);
        String s2 = a6.toString();
        int i2 = a1.stringWidth(s2);
        int i3 = this.xTextPadding;
        int i4 = 3 * i3;
        int i5 = i2 + i4;
        int i6 = i5 / 2;
        int i7 = i1 - i6;
        this.greatGrandParentLine = null;
        this.grandParentLine = null;
        this.parentLine = null;
        this.ptrLine = null;
        java.awt.Point a7 = new java.awt.Point(i7, i);
        Tree234Tree a8 = this.redBlackTree;
        com.cim.AIA.HierarchyTree a9 = a8.getPtrNode();
        com.cim.AIA.Line a10 = this.initialiseLine(a, a7, a9, "Ptr");
        this.ptrLine = a10;
        java.awt.FontMetrics a11 = a.getFontMetrics();
        int i8 = a11.stringWidth("Ptr");
        int i9 = this.xTextPadding;
        int i10 = i8 + i9;
        int i11 = i7 + i10;
        java.awt.Point a12 = new java.awt.Point(i11, i);
        Tree234Tree a13 = this.redBlackTree;
        com.cim.AIA.HierarchyTree a14 = a13.getParentNode();
        String s3 = Tree234Canvas.PARENT_LABEL;
        com.cim.AIA.Line a15 = this.initialiseLine(a, a12, a14, s3);
        this.parentLine = a15;
        java.awt.FontMetrics a16 = a.getFontMetrics();
        String s4 = Tree234Canvas.PARENT_LABEL;
        int i12 = a16.stringWidth(s4);
        int i13 = this.xTextPadding;
        int i14 = i12 + i13;
        int i15 = i11 + i14;
        java.awt.Point a17 = new java.awt.Point(i15, i);
        Tree234Tree a18 = this.redBlackTree;
        com.cim.AIA.HierarchyTree a19 = a18.getGrandParentNode();
        String s5 = Tree234Canvas.GRAND_PARENT_LABEL;
        com.cim.AIA.Line a20 = this.initialiseLine(a, a17, a19, s5);
        this.grandParentLine = a20;
        java.awt.FontMetrics a21 = a.getFontMetrics();
        String s6 = Tree234Canvas.GRAND_PARENT_LABEL;
        int i16 = a21.stringWidth(s6);
        int i17 = this.xTextPadding;
    }
    
    public void processRB()
    {
        int i = 0;
        int i0 = 0;
        Tree234Tree a = this.redBlackTree;
        int i1 = this.nodeWidth;
        com.cim.AIA.HierarchyTree a0 = a.getHierarchyTree(i1);
        this.hierarchyTree = a0;
        com.cim.AIA.HierarchyTree a1 = this.hierarchyTree;
        if(a1 != null)
        {
            com.cim.AIA.HierarchyTree a2 = this.hierarchyTree;
            java.awt.Dimension a3 = this.getSize();
            int i2 = a3.width;
            int i3 = i2 / 2;
            int i4 = this.nodeWidth;
            int i5 = i4 / 2;
            int i6 = i3 - i5;
            int i7 = this.yBuffer;
            a2.plantTree(i6, i7);
        }
        com.cim.AIA.HierarchyTree a4 = this.hierarchyTree;
        if(a4 == null)
        {
            i = 0;
            i0 = 0;
        }
        else
        {
            com.cim.AIA.HierarchyTree a5 = this.hierarchyTree;
            java.awt.Rectangle a6 = a5.getRectangle();
            int i8 = a6.width;
            int i9 = a6.height;
            i = i8;
            i0 = i9;
        }
        int i10 = this.xBuffer;
        int i11 = 2 * i10;
        int i12 = i + i11;
        int i13 = this.yBuffer;
        int i14 = 2 * i13;
        int i15 = i0 + i14;
        this.setSize(i12, i15);
        com.cim.AIA.HierarchyTree a7 = this.hierarchyTree;
        if(a7 != null)
        {
            com.cim.AIA.HierarchyTree a8 = this.hierarchyTree;
            java.awt.Dimension a9 = this.getSize();
            int i16 = a9.width;
            int i17 = i16 / 2;
            int i18 = this.nodeWidth;
            int i19 = i18 / 2;
            int i20 = i17 - i19;
            int i21 = this.yBuffer;
            int i22 = 2 * i21;
            a8.plantTree(i20, i22);
        }
    }
    
    public void animateRB()
    {
        java.awt.Graphics a = this.getGraphics();
        if(a != null)
        {
            int i = this.yBuffer;
            this.initaliseLines(a, i);
        }
        com.cim.AIA.HierarchyTree a0 = this.hierarchyTree;
        java.util.Hashtable a1 = this.getPositions(a0);
        java.util.Hashtable a2 = this.oldPositions;
        label0: {
            if(a2 == null)
            {
                break label0;
            }
            com.cim.AIA.HierarchyTree a3 = this.hierarchyTree;
            java.util.Hashtable a4 = this.oldPositions;
            com.cim.AIA.MultipleTween a5 = this.getMoveTweens(a3, a4, a1);
            int i0 = a5.getNumberOfTweens();
            if(i0 > 0)
            {
                this.addTween((com.cim.AIA.Tween)a5);
            }
        }
        this.oldPositions = a1;
        com.cim.AIA.MultipleTween a6 = this.tweens;
        int i1 = a6.getNumberOfTweens();
        if(i1 > 0)
        {
            com.cim.AIA.MultipleTween a7 = this.tweens;
            a7.run();
        }
    }
    
    public void process234()
    {
        int i = 0;
        int i0 = 0;
        Tree234Tree a = this.redBlackTree;
        com.cim.AIA.ElementArray a0 = a.getDataElementArray();
        this.elementArray = a0;
        com.cim.AIA.ElementArray a1 = this.elementArray;
        a1.setAllHaveSameWidth(true);
        com.cim.AIA.ElementArray a2 = this.elementArray;
        int i1 = this.nodeWidth;
        a2.setElementWidth(i1);
        com.cim.AIA.ElementArray a3 = this.elementArray;
        a3.setColumGap(0);
        Tree234Tree a4 = this.redBlackTree;
        int i2 = this.nodeWidth;
        com.cim.AIA.HierarchyTree a5 = a4.getBalancedHierarchyTree(i2);
        this.balancedHierarchyTree = a5;
        Tree234Tree a6 = this.redBlackTree;
        com.cim.AIA.Node a7 = a6.getAnimationNode();
        this.animationNode = a7;
        Tree234Tree a8 = this.redBlackTree;
        com.cim.AIA.Node a9 = a8.getGrowNode();
        this.growNode = a9;
        com.cim.AIA.HierarchyTree a10 = this.balancedHierarchyTree;
        if(a10 == null)
        {
            com.cim.AIA.ElementArray a11 = this.elementArray;
            int i3 = a11.getWidth();
            int i4 = this.yBuffer;
            com.cim.AIA.ElementArray a12 = this.elementArray;
            int i5 = a12.getHeight();
            int i6 = i4 + i5;
            i = i3;
            i0 = i6;
        }
        else
        {
            com.cim.AIA.HierarchyTree a13 = this.balancedHierarchyTree;
            java.awt.Rectangle a14 = a13.getRectangle();
            com.cim.AIA.ElementArray a15 = this.elementArray;
            int i7 = a15.getWidth();
            int i8 = a14.width;
            int i9 = Math.max(i7, i8);
            int i10 = this.yBuffer;
            int i11 = 3 * i10;
            int i12 = a14.height;
            int i13 = i11 + i12;
            i = i9;
            i0 = i13;
        }
        int i14 = this.xBuffer;
        int i15 = 2 * i14;
        int i16 = i + i15;
        int i17 = this.yBuffer;
        int i18 = 2 * i17;
        int i19 = i0 + i18;
        this.setSize(i16, i19);
        com.cim.AIA.ElementArray a16 = this.elementArray;
        java.awt.Dimension a17 = this.getSize();
        int i20 = a17.width;
        int i21 = i20 / 2;
        com.cim.AIA.ElementArray a18 = this.elementArray;
        int i22 = a18.getWidth();
        int i23 = i22 / 2;
        int i24 = i21 - i23;
        int i25 = this.yBuffer;
        a16.setLocation(i24, i25);
        com.cim.AIA.HierarchyTree a19 = this.balancedHierarchyTree;
        if(a19 != null)
        {
            com.cim.AIA.HierarchyTree a20 = this.balancedHierarchyTree;
            java.awt.Dimension a21 = this.getSize();
            int i26 = a21.width;
            int i27 = i26 / 2;
            int i28 = this.nodeWidth;
            int i29 = i28 / 2;
            int i30 = i27 - i29;
            int i31 = this.yBuffer;
            int i32 = 3 * i31;
            com.cim.AIA.ElementArray a22 = this.elementArray;
            int i33 = a22.getHeight();
            int i34 = i32 + i33;
            a20.plantTree(i30, i34);
        }
    }
    
    public void animate234()
    {
        java.awt.Graphics a = this.getGraphics();
        com.cim.AIA.HierarchyTree a0 = this.balancedHierarchyTree;
        java.util.Hashtable a1 = this.getPositions(a0);
        java.util.Hashtable a2 = this.oldPositions;
        label0: {
            if(a2 == null)
            {
                break label0;
            }
            com.cim.AIA.Node a3 = this.growNode;
            if(a3 != null)
            {
                java.util.Hashtable a4 = this.oldPositions;
                com.cim.AIA.Node a5 = this.growNode;
                int i = a5.getIdentifier();
                Integer a6 = new Integer(i);
                Object a7 = a4.get((Object)a6);
                java.awt.Point dummy = (java.awt.Point)a7;
                java.awt.Point a8 = (java.awt.Point)a7;
                com.cim.AIA.Node a9 = this.growNode;
                a9.setPosition(a8);
                com.cim.AIA.Node a10 = this.growNode;
                int i0 = this.numberOfTweenSteps;
                int i1 = i0 / 3;
                com.cim.AIA.DeleteTween a11 = new com.cim.AIA.DeleteTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Sizeable)a10, i1);
                this.addTween((com.cim.AIA.Tween)a11);
            }
            com.cim.AIA.Node a12 = this.animationNode;
            if(a12 == null)
            {
                break label0;
            }
            java.util.Hashtable a13 = this.oldPositions;
            com.cim.AIA.Node a14 = this.animationNode;
            int i2 = a14.getIdentifier();
            Integer a15 = new Integer(i2);
            Object a16 = a13.get((Object)a15);
            java.awt.Point dummy0 = (java.awt.Point)a16;
            java.awt.Point a17 = (java.awt.Point)a16;
            com.cim.AIA.Node a18 = this.animationNode;
            int i3 = a18.getIdentifier();
            Integer a19 = new Integer(i3);
            Object a20 = a1.get((Object)a19);
            java.awt.Point dummy1 = (java.awt.Point)a20;
            java.awt.Point a21 = (java.awt.Point)a20;
            if(a17 == null)
            {
                break label0;
            }
            if(a21 == null)
            {
                break label0;
            }
            int i4 = a17.x;
            int i5 = a21.x;
            label1: {
                if(i4 != i5)
                {
                    break label1;
                }
                int i6 = a17.y;
                int i7 = a21.y;
                if(i6 == i7)
                {
                    break label0;
                }
            }
            com.cim.AIA.Node a22 = this.animationNode;
            int i8 = this.numberOfTweenSteps;
            int i9 = i8 / 3;
            com.cim.AIA.MoveTween a23 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Moveable)a22, a17, a21, false, i9);
            this.addTween((com.cim.AIA.Tween)a23);
        }
        java.util.Hashtable a24 = this.oldPositions;
        label2: {
            if(a24 == null)
            {
                break label2;
            }
            com.cim.AIA.HierarchyTree a25 = this.balancedHierarchyTree;
            java.util.Hashtable a26 = this.oldPositions;
            com.cim.AIA.MultipleTween a27 = this.getMoveTweens(a25, a26, a1);
            int i10 = a27.getNumberOfTweens();
            if(i10 > 0)
            {
                this.addTween((com.cim.AIA.Tween)a27);
            }
        }
        this.oldPositions = a1;
        Tree234Tree a28 = this.redBlackTree;
        com.cim.AIA.HierarchyTree a29 = this.balancedHierarchyTree;
        int i11 = this.numberOfTweenSteps;
        int i12 = i11 / 3;
        com.cim.AIA.MultipleTween a30 = a28.generateTweens((com.cim.AIA.Paintable)this, (Object)a29, i12);
        if(a30 != null)
        {
            this.addTween((com.cim.AIA.Tween)a30);
        }
        com.cim.AIA.MultipleTween a31 = this.tweens;
        int i13 = a31.getNumberOfTweens();
        if(i13 > 0)
        {
            com.cim.AIA.MultipleTween a32 = this.tweens;
            a32.run();
        }
    }
    
    public void processRepaintEvent(com.cim.AIA.RepaintEvent a)
    {
        Object a0 = a.paramObj;
        label0: {
            if(a0 == null)
            {
                break label0;
            }
            Object a1 = a.paramObj;
            Tree234Tree dummy = (Tree234Tree)a1;
            Tree234Tree a2 = (Tree234Tree)a1;
            this.redBlackTree = a2;
            int i = this.showAs234Only?1:0;
            if(i != 0)
            {
                this.process234();
                this.animate234();
            }
            else
            {
                this.processRB();
            }
            Tree234Tree a3 = this.redBlackTree;
            a3.removeAllAnimationRequests();
            this.repaint();
        }
    }
    
    protected void handleColorSelection(com.cim.AIA.ColorSelection a)
    {
    }
    
    protected void handleFontSelection(com.cim.AIA.FontSelection a)
    {
    }
    
    static
    {
        String s = localization.Messages.getString("Tree234Canvas.1");
        Tree234Canvas.PARENT_LABEL = s;
        String s0 = localization.Messages.getString("Tree234Canvas.2");
        Tree234Canvas.GRAND_PARENT_LABEL = s0;
        String s1 = localization.Messages.getString("Tree234Canvas.3");
        Tree234Canvas.GREAT_GRAND_PARENT_LABEL = s1;
    }
}