public class RedBlackTreeCanvas extends com.cim.AIA.AlgorithmCanvas {
    final private static long serialVersionUID = -3993390322636833261L;
    final protected static String PTR_LABEL;
    final protected static String PARENT_LABEL;
    final protected static String GRAND_PARENT_LABEL;
    final protected static String GREAT_GRAND_PARENT_LABEL;
    final protected static String[] rotateRight;
    final protected static String[] rotateLeft;
    protected RedBlackTree redBlackTree;
    protected com.cim.AIA.HierarchyTree hierarchyTree;
    protected com.cim.AIA.HierarchyTree tuteTree;
    protected com.cim.AIA.HierarchyTree balancedHierarchyTree;
    protected java.util.Vector saveLine;
    protected java.util.Vector animateLine;
    protected java.util.Vector lineMoveRequests;
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
    protected boolean showTute;
    protected boolean showCode;
    protected int highLightedLine;
    
    public RedBlackTreeCanvas()
    {
        super();
        java.util.Vector a = new java.util.Vector();
        this.saveLine = a;
        java.util.Vector a0 = new java.util.Vector();
        this.animateLine = a0;
        this.nodeWidth = 20;
        this.xTextPadding = 30;
        this.yBuffer = 30;
        this.xBuffer = 30;
        this.showAs234Only = false;
        this.showTute = false;
        this.showCode = false;
        this.highLightedLine = 0;
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
                com.cim.AIA.DeleteTween a11 = new com.cim.AIA.DeleteTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Sizeable)a10, i0);
                this.addTween((com.cim.AIA.Tween)a11);
            }
            com.cim.AIA.Node a12 = this.animationNode;
            if(a12 == null)
            {
                break label0;
            }
            java.util.Hashtable a13 = this.oldPositions;
            com.cim.AIA.Node a14 = this.animationNode;
            int i1 = a14.getIdentifier();
            Integer a15 = new Integer(i1);
            Object a16 = a13.get((Object)a15);
            java.awt.Point dummy0 = (java.awt.Point)a16;
            java.awt.Point a17 = (java.awt.Point)a16;
            com.cim.AIA.Node a18 = this.animationNode;
            int i2 = a18.getIdentifier();
            Integer a19 = new Integer(i2);
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
            int i3 = a17.x;
            int i4 = a21.x;
            label1: {
                if(i3 != i4)
                {
                    break label1;
                }
                int i5 = a17.y;
                int i6 = a21.y;
                if(i5 == i6)
                {
                    break label0;
                }
            }
            com.cim.AIA.Node a22 = this.animationNode;
            int i7 = this.numberOfTweenSteps;
            com.cim.AIA.MoveTween a23 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Moveable)a22, a17, a21, false, i7);
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
            int i8 = a27.getNumberOfTweens();
            if(i8 > 0)
            {
                this.addTween((com.cim.AIA.Tween)a27);
            }
        }
        this.oldPositions = a1;
        RedBlackTree a28 = this.redBlackTree;
        com.cim.AIA.HierarchyTree a29 = this.balancedHierarchyTree;
        int i9 = this.numberOfTweenSteps;
        com.cim.AIA.MultipleTween a30 = a28.generateTweens((com.cim.AIA.Paintable)this, (Object)a29, i9);
        if(a30 != null)
        {
            this.addTween((com.cim.AIA.Tween)a30);
        }
        com.cim.AIA.MultipleTween a31 = this.tweens;
        int i10 = a31.getNumberOfTweens();
        if(i10 > 0)
        {
            com.cim.AIA.MultipleTween a32 = this.tweens;
            a32.run();
        }
    }
    
    public void animateRB()
    {
        java.awt.Graphics a = this.getGraphics();
        if(a != null)
        {
            int i = this.yBuffer;
            int i0 = 2 * i;
            com.cim.AIA.ElementArray a0 = this.elementArray;
            int i1 = a0.getHeight();
            int i2 = i0 + i1;
            this.initaliseLines(a, i2);
        }
        com.cim.AIA.HierarchyTree a1 = this.hierarchyTree;
        java.util.Hashtable a2 = this.getPositions(a1);
        java.util.Hashtable a3 = this.oldPositions;
        label0: {
            if(a3 == null)
            {
                break label0;
            }
            com.cim.AIA.HierarchyTree a4 = this.hierarchyTree;
            java.util.Hashtable a5 = this.oldPositions;
            com.cim.AIA.MultipleTween a6 = this.getMoveTweens(a4, a5, a2);
            int i3 = a6.getNumberOfTweens();
            if(i3 > 0)
            {
                this.addTween((com.cim.AIA.Tween)a6);
            }
        }
        this.oldPositions = a2;
        com.cim.AIA.MultipleTween a7 = this.tweens;
        int i4 = a7.getNumberOfTweens();
        if(i4 > 0)
        {
            com.cim.AIA.MultipleTween a8 = this.tweens;
            a8.run();
        }
    }
    
    public void animateTute()
    {
        java.awt.Graphics a = this.getGraphics();
        com.cim.AIA.HierarchyTree a0 = this.tuteTree;
        java.util.Hashtable a1 = this.getPositions(a0);
        java.util.Hashtable a2 = this.oldPositions;
        label0: {
            if(a2 == null)
            {
                break label0;
            }
            com.cim.AIA.HierarchyTree a3 = this.tuteTree;
            java.util.Hashtable a4 = this.oldPositions;
            com.cim.AIA.MultipleTween a5 = this.getLineTweens(a3, a4, a1);
            int i = a5.getNumberOfTweens();
            if(i > 0)
            {
                this.addTween((com.cim.AIA.Tween)a5);
            }
            com.cim.AIA.HierarchyTree a6 = this.tuteTree;
            java.util.Hashtable a7 = this.oldPositions;
            com.cim.AIA.MultipleTween a8 = this.getMoveTweens(a6, a7, a1);
            int i0 = a8.getNumberOfTweens();
            if(i0 > 0)
            {
                this.addTween((com.cim.AIA.Tween)a8);
            }
        }
        com.cim.AIA.MultipleTween a9 = this.tweens;
        int i1 = a9.getNumberOfTweens();
        if(i1 > 0)
        {
            com.cim.AIA.MultipleTween a10 = this.tweens;
            a10.run();
        }
        this.oldPositions = a1;
    }
    
    public void display234(java.awt.Graphics a)
    {
        com.cim.AIA.Node a0 = this.growNode;
        if(a0 != null)
        {
            com.cim.AIA.Node a1 = this.growNode;
            a1.draw(a);
        }
        com.cim.AIA.HierarchyTree a2 = this.balancedHierarchyTree;
        if(a2 != null)
        {
            com.cim.AIA.HierarchyTree a3 = this.balancedHierarchyTree;
            a3.draw(a);
        }
        com.cim.AIA.Node a4 = this.animationNode;
        if(a4 != null)
        {
            com.cim.AIA.Node a5 = this.animationNode;
            a5.draw(a);
        }
    }
    
    public void displayAlgorithm(java.awt.Graphics a)
    {
        RedBlackTree a0 = this.redBlackTree;
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
                int i0 = this.showTute?1:0;
                if(i0 != 0)
                {
                    break label1;
                }
                int i1 = this.showCode?1:0;
                if(i1 == 0)
                {
                    this.displayRB(a);
                }
            }
            int i2 = this.showAs234Only?1:0;
            label2: {
                if(i2 == 0)
                {
                    break label2;
                }
                int i3 = this.showTute?1:0;
                if(i3 != 0)
                {
                    break label2;
                }
                RedBlackTree a1 = this.redBlackTree;
                int i4 = a1.update234?1:0;
                if(i4 == 0)
                {
                    String s = localization.Messages.getString("RedBlackTreeCanvas.22");
                    a.drawString(s, 20, 20);
                }
                else
                {
                    this.display234(a);
                }
            }
            int i5 = this.showAs234Only?1:0;
            label3: {
                if(i5 != 0)
                {
                    break label3;
                }
                int i6 = this.showTute?1:0;
                if(i6 != 0)
                {
                    this.displayTute(a);
                }
            }
            int i7 = this.showCode?1:0;
            if(i7 != 0)
            {
                this.displayCode(a);
            }
        }
    }
    
    public void displayCode(java.awt.Graphics a)
    {
        int i = 0;
        int i0 = 30;
        int i1 = 30;
        int i2 = 1;
        int i3 = 0;
        while(true)
        {
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            String[] a0 = RedBlackTreeCanvas.rotateLeft;
            int i8 = i0;
            int i9 = a0.length;
            i = i8;
            int i10 = i;
            if(i3 >= i9)
            {
                break;
            }
            else
            {
                i4 = i10;
            }
            String[] a1 = RedBlackTreeCanvas.rotateLeft;
            int i11 = i4;
            String s = a1[i3];
            int i12 = i11;
            int i13 = s.equals((Object)"}")?1:0;
            int i14 = i12;
            int i15 = i14;
            if(i13 == 0)
            {
                i5 = i15;
            }
            else
            {
                int i16 = i14 - 30;
                i5 = i16;
            }
            int i17 = this.highLightedLine;
            int i18 = i5;
            int i19 = i18;
            int i20 = i18;
            if(i2 != i17)
            {
                i6 = i20;
            }
            else
            {
                int i21 = i19;
                java.awt.Color a2 = com.cim.AIA.CodeCanvas.DEFAULT_HIGHLIGHT_COLOR;
                int i22 = i21;
                a.setColor(a2);
                int i23 = i22;
                int i24 = i1 - 15;
                java.awt.FontMetrics a3 = a.getFontMetrics();
                int i25 = i23;
                int i26 = a3.stringWidth(s);
                int i27 = i25;
                java.awt.FontMetrics a4 = a.getFontMetrics();
                int i28 = i27;
                int i29 = a4.getHeight();
                int i30 = i28;
                a.fillRect(i23, i24, i26, i29);
                int i31 = i30;
                java.awt.Color a5 = java.awt.Color.black;
                int i32 = i31;
                a.setColor(a5);
                i6 = i32;
            }
            a.drawString(s, i6, i1);
            int i33 = i6;
            int i34 = s.equals((Object)"{")?1:0;
            int i35 = i33;
            int i36 = i35;
            if(i34 == 0)
            {
                i7 = i36;
            }
            else
            {
                int i37 = i35 + 30;
                i7 = i37;
            }
            int i38 = i1 + 15;
            int i39 = i2 + 1;
            int i40 = i3 + 1;
            i0 = i7;
            i1 = i38;
            i2 = i39;
            i3 = i40;
        }
        int i41 = i1 + 15;
        int i42 = i;
        int i43 = i41;
        int i44 = i2;
        int i45 = 0;
        while(true)
        {
            int i46 = 0;
            int i47 = 0;
            int i48 = 0;
            int i49 = 0;
            String[] a6 = RedBlackTreeCanvas.rotateRight;
            int i50 = i42;
            int i51 = a6.length;
            int i52 = i50;
            int i53 = i52;
            if(i45 >= i51)
            {
                return;
            }
            else
            {
                i46 = i53;
            }
            String[] a7 = RedBlackTreeCanvas.rotateRight;
            int i54 = i46;
            String s0 = a7[i45];
            int i55 = i54;
            int i56 = s0.equals((Object)"}")?1:0;
            int i57 = i55;
            int i58 = i57;
            if(i56 == 0)
            {
                i47 = i58;
            }
            else
            {
                int i59 = i57 - 30;
                i47 = i59;
            }
            int i60 = this.highLightedLine;
            int i61 = i47;
            int i62 = i61;
            int i63 = i61;
            if(i44 != i60)
            {
                i48 = i63;
            }
            else
            {
                int i64 = i62;
                java.awt.Color a8 = com.cim.AIA.CodeCanvas.DEFAULT_HIGHLIGHT_COLOR;
                int i65 = i64;
                a.setColor(a8);
                int i66 = i65;
                int i67 = i43 - 15;
                java.awt.FontMetrics a9 = a.getFontMetrics();
                int i68 = i66;
                int i69 = a9.stringWidth(s0);
                int i70 = i68;
                java.awt.FontMetrics a10 = a.getFontMetrics();
                int i71 = i70;
                int i72 = a10.getHeight();
                int i73 = i71;
                a.fillRect(i66, i67, i69, i72);
                int i74 = i73;
                java.awt.Color a11 = java.awt.Color.black;
                int i75 = i74;
                a.setColor(a11);
                i48 = i75;
            }
            a.drawString(s0, i48, i43);
            int i76 = i48;
            int i77 = s0.equals((Object)"{")?1:0;
            int i78 = i76;
            int i79 = i78;
            if(i77 == 0)
            {
                i49 = i79;
            }
            else
            {
                int i80 = i78 + 30;
                i49 = i80;
            }
            int i81 = i43 + 15;
            int i82 = i44 + 1;
            int i83 = i45 + 1;
            i42 = i49;
            i43 = i81;
            i44 = i82;
            i45 = i83;
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
        com.cim.AIA.ElementArray a6 = this.elementArray;
        if(a6 != null)
        {
            com.cim.AIA.ElementArray a7 = this.elementArray;
            a7.draw(a);
        }
        com.cim.AIA.HierarchyTree a8 = this.hierarchyTree;
        if(a8 != null)
        {
            com.cim.AIA.HierarchyTree a9 = this.hierarchyTree;
            a9.draw(a);
        }
    }
    
    public void displayTute(java.awt.Graphics a)
    {
        com.cim.AIA.HierarchyTree a0 = this.tuteTree;
        if(a0 != null)
        {
            com.cim.AIA.HierarchyTree a1 = this.tuteTree;
            a1.draw(a);
        }
        int i = 0;
        while(true)
        {
            java.util.Vector a2 = this.animateLine;
            int i0 = a2.size();
            if(i >= i0)
            {
                return;
            }
            else
            {
                java.util.Vector a3 = this.animateLine;
                Object a4 = a3.elementAt(i);
                com.cim.AIA.Line dummy = (com.cim.AIA.Line)a4;
                com.cim.AIA.Line a5 = (com.cim.AIA.Line)a4;
                a5.draw(a);
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    protected com.cim.AIA.HierarchyTree findTree(com.cim.AIA.HierarchyTree a, int i)
    {
        com.cim.AIA.HierarchyTree a0 = null;
        label1: {
            label0: {
                if(a != null)
                {
                    break label0;
                }
                a0 = null;
                break label1;
            }
            com.cim.AIA.Node a1 = a.getNodeAt(1);
            int i0 = a1.getIdentifier();
            label2: {
                if(i0 != i)
                {
                    break label2;
                }
                a0 = a;
                break label1;
            }
            com.cim.AIA.HierarchyTree a2 = a.getLeftChild();
            com.cim.AIA.HierarchyTree a3 = this.findTree(a2, i);
            label3: {
                if(a3 == null)
                {
                    break label3;
                }
                a0 = a3;
                break label1;
            }
            com.cim.AIA.HierarchyTree a4 = a.getRightChild();
            com.cim.AIA.HierarchyTree a5 = this.findTree(a4, i);
            a0 = a5 == null?null:a5;
        }
        return a0;
    }
    
    protected com.cim.AIA.MultipleTween getLineTweens(com.cim.AIA.HierarchyTree a, java.util.Hashtable a0, java.util.Hashtable a1)
    {
        com.cim.AIA.MultipleTween a2 = new com.cim.AIA.MultipleTween((com.cim.AIA.Paintable)this);
        java.util.Vector a3 = this.animateLine;
        a3.removeAllElements();
        RedBlackTree a4 = this.redBlackTree;
        int i = a4.getRemoveSaveLine()?1:0;
        if(i != 0)
        {
            java.util.Vector a5 = this.saveLine;
            a5.removeAllElements();
        }
        int i0 = 0;
        while(true)
        {
            java.util.Vector a6 = this.saveLine;
            int i1 = a6.size();
            if(i0 >= i1)
            {
                this.getLineTweens(a, a0, a1, a2);
                return a2;
            }
            else
            {
                java.util.Vector a7 = this.animateLine;
                java.util.Vector a8 = this.saveLine;
                Object a9 = a8.elementAt(i0);
                a7.addElement(a9);
                int i2 = i0 + 1;
                i0 = i2;
            }
        }
    }
    
    protected void getLineTweens(com.cim.AIA.HierarchyTree a, java.util.Hashtable a0, java.util.Hashtable a1, com.cim.AIA.MultipleTween a2)
    {
        label0: {
            if(a == null)
            {
                break label0;
            }
            int i = 0;
            while(true)
            {
                java.util.Vector a3 = this.lineMoveRequests;
                int i0 = a3.size();
                if(i >= i0)
                {
                    break;
                }
                java.util.Vector a4 = this.lineMoveRequests;
                Object a5 = a4.elementAt(i);
                LineMoveRequest dummy = (LineMoveRequest)a5;
                LineMoveRequest a6 = (LineMoveRequest)a5;
                int i1 = a6.getToPositionInsertedId();
                int i2 = a6.getFromPositionInsertedId();
                int i3 = a6.getParentPositionInsertedId();
                label2: {
                    int i4 = 0;
                    com.cim.AIA.Line a7 = null;
                    com.cim.AIA.Line a8 = null;
                    java.awt.Point a9 = null;
                    label1: {
                        java.awt.Point a10 = null;
                        if(i1 >= 0)
                        {
                            break label1;
                        }
                        Integer a11 = new Integer(i2);
                        Object a12 = a0.get((Object)a11);
                        java.awt.Point dummy0 = (java.awt.Point)a12;
                        com.cim.AIA.HierarchyTree a13 = this.findTree(a, i2);
                        com.cim.AIA.Line a14 = a13.getLine();
                        com.cim.AIA.Line a15 = a14.copy();
                        com.cim.AIA.Node a16 = a13.getNodeAt(1);
                        int i5 = a15.getIntX2();
                        int i6 = a15.getIntY2();
                        java.awt.Point a17 = new java.awt.Point(i5, i6);
                        if(i1 != -50)
                        {
                            int i7 = a15.getIntX2();
                            int i8 = a16.getWidth();
                            int i9 = i7 + i8;
                            int i10 = a15.getIntY2();
                            int i11 = i10 + 35;
                            java.awt.Point a18 = new java.awt.Point(i9, i11);
                            a10 = a18;
                        }
                        else
                        {
                            int i12 = a15.getIntX2();
                            int i13 = a16.getWidth();
                            int i14 = i12 - i13;
                            int i15 = a15.getIntY2();
                            int i16 = i15 + 35;
                            java.awt.Point a19 = new java.awt.Point(i14, i16);
                            a10 = a19;
                        }
                        com.cim.AIA.Line a20 = a13.getLine();
                        com.cim.AIA.Line a21 = a20.copy();
                        a21.setEndPosition(a10);
                        int i17 = this.numberOfTweenSteps;
                        com.cim.AIA.MoveTween a22 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Moveable)a15, a17, a10, false, i17);
                        a2.add((com.cim.AIA.Tween)a22);
                        java.util.Vector a23 = this.animateLine;
                        a23.addElement((Object)a15);
                        java.util.Vector a24 = this.saveLine;
                        a24.addElement((Object)a21);
                        break label2;
                    }
                    label3: {
                        if(i2 < 0)
                        {
                            break label3;
                        }
                        int i18 = a6.getFromPositionInsertedId();
                        Integer a25 = new Integer(i18);
                        Object a26 = a0.get((Object)a25);
                        java.awt.Point dummy1 = (java.awt.Point)a26;
                        int i19 = a6.getToPositionInsertedId();
                        Integer a27 = new Integer(i19);
                        Object a28 = a0.get((Object)a27);
                        java.awt.Point dummy2 = (java.awt.Point)a28;
                        java.awt.Point a29 = (java.awt.Point)a28;
                        int i20 = a6.getFromPositionInsertedId();
                        com.cim.AIA.HierarchyTree a30 = this.findTree(a, i20);
                        com.cim.AIA.Line a31 = a30.getLine();
                        com.cim.AIA.Line a32 = a31.copy();
                        com.cim.AIA.Node a33 = a30.getNodeAt(1);
                        int i21 = a32.getIntX2();
                        int i22 = a32.getIntY2();
                        java.awt.Point a34 = new java.awt.Point(i21, i22);
                        int i23 = a29.x;
                        int i24 = a33.getWidth();
                        int i25 = i24 / 2;
                        int i26 = i23 + i25;
                        int i27 = a29.y;
                        java.awt.Point a35 = new java.awt.Point(i26, i27);
                        com.cim.AIA.Line a36 = a30.getLine();
                        com.cim.AIA.Line a37 = a36.copy();
                        a37.setEndPosition(a35);
                        int i28 = this.numberOfTweenSteps;
                        com.cim.AIA.MoveTween a38 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Moveable)a32, a34, a35, false, i28);
                        a2.add((com.cim.AIA.Tween)a38);
                        java.util.Vector a39 = this.animateLine;
                        a39.addElement((Object)a32);
                        java.util.Vector a40 = this.saveLine;
                        a40.addElement((Object)a37);
                        break label2;
                    }
                    Integer a41 = new Integer(i1);
                    Object a42 = a0.get((Object)a41);
                    java.awt.Point dummy3 = (java.awt.Point)a42;
                    java.awt.Point a43 = (java.awt.Point)a42;
                    com.cim.AIA.HierarchyTree a44 = this.findTree(a, i3);
                    if(i2 != -50)
                    {
                        com.cim.AIA.HierarchyTree a45 = a44.getRightChild();
                        com.cim.AIA.Line a46 = a45.getLine();
                        com.cim.AIA.Line a47 = a46.copy();
                        com.cim.AIA.HierarchyTree a48 = a44.getRightChild();
                        com.cim.AIA.Line a49 = a48.getLine();
                        com.cim.AIA.Line a50 = a49.copy();
                        i4 = i2;
                        a7 = a47;
                        a8 = a50;
                    }
                    else
                    {
                        com.cim.AIA.HierarchyTree a51 = a44.getLeftChild();
                        com.cim.AIA.Line a52 = a51.getLine();
                        com.cim.AIA.Line a53 = a52.copy();
                        com.cim.AIA.HierarchyTree a54 = a44.getLeftChild();
                        com.cim.AIA.Line a55 = a54.getLine();
                        com.cim.AIA.Line a56 = a55.copy();
                        i4 = -50;
                        a7 = a53;
                        a8 = a56;
                    }
                    com.cim.AIA.Node a57 = a44.getNodeAt(1);
                    com.cim.AIA.Line a58 = a44.getLine();
                    com.cim.AIA.Line a59 = a58.copy();
                    if(i4 != -50)
                    {
                        int i29 = a59.getIntX2();
                        int i30 = a57.getWidth();
                        int i31 = i29 + i30;
                        int i32 = a59.getIntY2();
                        int i33 = i32 + 40;
                        java.awt.Point a60 = new java.awt.Point(i31, i33);
                        a9 = a60;
                    }
                    else
                    {
                        int i34 = a59.getIntX2();
                        int i35 = a57.getWidth();
                        int i36 = i34 - i35;
                        int i37 = a59.getIntY2();
                        int i38 = i37 + 40;
                        java.awt.Point a61 = new java.awt.Point(i36, i38);
                        a9 = a61;
                    }
                    int i39 = a43.x;
                    int i40 = a57.getWidth();
                    int i41 = i40 / 2;
                    int i42 = i39 + i41;
                    int i43 = a43.y;
                    java.awt.Point a62 = new java.awt.Point(i42, i43);
                    a7.setEndPosition(a9);
                    a8.setEndPosition(a62);
                    java.awt.Color a63 = RedBlackTree.tuteHighLightColor;
                    a7.setColor(a63);
                    java.awt.Color a64 = RedBlackTree.tuteHighLightColor;
                    a8.setColor(a64);
                    int i44 = this.numberOfTweenSteps;
                    com.cim.AIA.MoveTween a65 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Moveable)a7, a9, a62, false, i44);
                    a2.add((com.cim.AIA.Tween)a65);
                    java.util.Vector a66 = this.animateLine;
                    a66.addElement((Object)a7);
                    java.util.Vector a67 = this.saveLine;
                    a67.addElement((Object)a8);
                }
                int i45 = i + 1;
                i = i45;
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
                com.cim.AIA.MoveTween a11 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Moveable)a, a7, a10, false, i5);
                a2.add((com.cim.AIA.Tween)a11);
            }
            java.util.Vector a12 = a.getChildren();
            int i6 = 0;
            while(true)
            {
                int i7 = a12.size();
                if(i6 >= i7)
                {
                    break;
                }
                else
                {
                    Object a13 = a12.elementAt(i6);
                    com.cim.AIA.HierarchyTree dummy1 = (com.cim.AIA.HierarchyTree)a13;
                    com.cim.AIA.HierarchyTree a14 = (com.cim.AIA.HierarchyTree)a13;
                    this.getMoveTweens(a14, a0, a1, a2);
                    int i8 = i6 + 1;
                    i6 = i8;
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
    
    protected void handleColorSelection(com.cim.AIA.ColorSelection a)
    {
    }
    
    protected void handleFontSelection(com.cim.AIA.FontSelection a)
    {
    }
    
    protected void initaliseLines(java.awt.Graphics a, int i)
    {
        java.awt.Dimension a0 = this.getSize();
        int i0 = a0.width;
        int i1 = i0 / 2;
        java.awt.FontMetrics a1 = a.getFontMetrics();
        StringBuilder a2 = new StringBuilder();
        String s = RedBlackTreeCanvas.PTR_LABEL;
        StringBuilder a3 = a2.append(s);
        String s0 = RedBlackTreeCanvas.PARENT_LABEL;
        StringBuilder a4 = a3.append(s0);
        String s1 = RedBlackTreeCanvas.GRAND_PARENT_LABEL;
        StringBuilder a5 = a4.append(s1);
        String s2 = RedBlackTreeCanvas.GREAT_GRAND_PARENT_LABEL;
        StringBuilder a6 = a5.append(s2);
        String s3 = a6.toString();
        int i2 = a1.stringWidth(s3);
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
        RedBlackTree a8 = this.redBlackTree;
        com.cim.AIA.HierarchyTree a9 = a8.getPtrNode();
        String s4 = RedBlackTreeCanvas.PTR_LABEL;
        com.cim.AIA.Line a10 = this.initialiseLine(a, a7, a9, s4);
        this.ptrLine = a10;
        java.awt.FontMetrics a11 = a.getFontMetrics();
        String s5 = RedBlackTreeCanvas.PTR_LABEL;
        int i8 = a11.stringWidth(s5);
        int i9 = this.xTextPadding;
        int i10 = i8 + i9;
        int i11 = i7 + i10;
        java.awt.Point a12 = new java.awt.Point(i11, i);
        RedBlackTree a13 = this.redBlackTree;
        com.cim.AIA.HierarchyTree a14 = a13.getParentNode();
        String s6 = RedBlackTreeCanvas.PARENT_LABEL;
        com.cim.AIA.Line a15 = this.initialiseLine(a, a12, a14, s6);
        this.parentLine = a15;
        java.awt.FontMetrics a16 = a.getFontMetrics();
        String s7 = RedBlackTreeCanvas.PARENT_LABEL;
        int i12 = a16.stringWidth(s7);
        int i13 = this.xTextPadding;
        int i14 = i12 + i13;
        int i15 = i11 + i14;
        java.awt.Point a17 = new java.awt.Point(i15, i);
        RedBlackTree a18 = this.redBlackTree;
        com.cim.AIA.HierarchyTree a19 = a18.getGrandParentNode();
        String s8 = RedBlackTreeCanvas.GRAND_PARENT_LABEL;
        com.cim.AIA.Line a20 = this.initialiseLine(a, a17, a19, s8);
        this.grandParentLine = a20;
        java.awt.FontMetrics a21 = a.getFontMetrics();
        String s9 = RedBlackTreeCanvas.GRAND_PARENT_LABEL;
        int i16 = a21.stringWidth(s9);
        int i17 = this.xTextPadding;
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
    
    public void process234()
    {
        int i = 0;
        int i0 = 0;
        RedBlackTree a = this.redBlackTree;
        int i1 = this.nodeWidth;
        com.cim.AIA.HierarchyTree a0 = a.getBalancedHierarchyTree(i1);
        this.balancedHierarchyTree = a0;
        com.cim.AIA.HierarchyTree a1 = this.balancedHierarchyTree;
        if(a1 == null)
        {
            i = 0;
            i0 = 0;
        }
        else
        {
            com.cim.AIA.HierarchyTree a2 = this.balancedHierarchyTree;
            java.awt.Rectangle a3 = a2.getRectangle();
            int i2 = a3.width;
            int i3 = a3.height;
            i = i2;
            i0 = i3;
        }
        int i4 = this.xBuffer;
        int i5 = 2 * i4;
        int i6 = i + i5;
        int i7 = this.yBuffer;
        int i8 = 2 * i7;
        int i9 = i0 + i8;
        this.setSize(i6, i9);
        com.cim.AIA.HierarchyTree a4 = this.balancedHierarchyTree;
        if(a4 != null)
        {
            com.cim.AIA.HierarchyTree a5 = this.balancedHierarchyTree;
            java.awt.Dimension a6 = this.getSize();
            int i10 = a6.width;
            int i11 = i10 / 2;
            int i12 = this.nodeWidth;
            int i13 = i12 / 2;
            int i14 = i11 - i13;
            int i15 = this.yBuffer;
            a5.plantTree(i14, i15);
        }
    }
    
    public void processCode()
    {
        RedBlackTree a = this.redBlackTree;
        Thread a0 = a.getAnimThread();
        RotateAnimation dummy = (RotateAnimation)a0;
        RotateAnimation a1 = (RotateAnimation)a0;
        int i = a1.getHighLight();
        this.highLightedLine = i;
    }
    
    public void processRB()
    {
        int i = 0;
        int i0 = 0;
        RedBlackTree a = this.redBlackTree;
        com.cim.AIA.ElementArray a0 = a.getDataElementArray();
        this.elementArray = a0;
        com.cim.AIA.ElementArray a1 = this.elementArray;
        a1.setAllHaveSameWidth(true);
        com.cim.AIA.ElementArray a2 = this.elementArray;
        int i1 = this.nodeWidth;
        a2.setElementWidth(i1);
        com.cim.AIA.ElementArray a3 = this.elementArray;
        a3.setColumGap(0);
        RedBlackTree a4 = this.redBlackTree;
        int i2 = this.nodeWidth;
        com.cim.AIA.HierarchyTree a5 = a4.getHierarchyTree(i2);
        this.hierarchyTree = a5;
        com.cim.AIA.HierarchyTree a6 = this.hierarchyTree;
        if(a6 != null)
        {
            com.cim.AIA.HierarchyTree a7 = this.hierarchyTree;
            java.awt.Dimension a8 = this.getSize();
            int i3 = a8.width;
            int i4 = i3 / 2;
            int i5 = this.nodeWidth;
            int i6 = i5 / 2;
            int i7 = i4 - i6;
            int i8 = this.yBuffer;
            int i9 = 3 * i8;
            com.cim.AIA.ElementArray a9 = this.elementArray;
            int i10 = a9.getHeight();
            int i11 = i9 + i10;
            a7.plantTree(i7, i11);
        }
        com.cim.AIA.HierarchyTree a10 = this.hierarchyTree;
        if(a10 == null)
        {
            com.cim.AIA.ElementArray a11 = this.elementArray;
            int i12 = a11.getWidth();
            int i13 = this.yBuffer;
            com.cim.AIA.ElementArray a12 = this.elementArray;
            int i14 = a12.getHeight();
            int i15 = i13 + i14;
            i = i12;
            i0 = i15;
        }
        else
        {
            com.cim.AIA.HierarchyTree a13 = this.hierarchyTree;
            java.awt.Rectangle a14 = a13.getRectangle();
            com.cim.AIA.ElementArray a15 = this.elementArray;
            int i16 = a15.getWidth();
            int i17 = a14.width;
            int i18 = Math.max(i16, i17);
            int i19 = this.yBuffer;
            int i20 = 3 * i19;
            int i21 = a14.height;
            int i22 = i20 + i21;
            i = i18;
            i0 = i22;
        }
        int i23 = this.xBuffer;
        int i24 = 2 * i23;
        int i25 = i + i24;
        int i26 = this.yBuffer;
        int i27 = 2 * i26;
        int i28 = i0 + i27;
        this.setSize(i25, i28);
        com.cim.AIA.ElementArray a16 = this.elementArray;
        java.awt.Dimension a17 = this.getSize();
        int i29 = a17.width;
        int i30 = i29 / 2;
        com.cim.AIA.ElementArray a18 = this.elementArray;
        int i31 = a18.getWidth();
        int i32 = i31 / 2;
        int i33 = i30 - i32;
        int i34 = this.yBuffer;
        a16.setLocation(i33, i34);
        com.cim.AIA.HierarchyTree a19 = this.hierarchyTree;
        if(a19 != null)
        {
            com.cim.AIA.HierarchyTree a20 = this.hierarchyTree;
            java.awt.Dimension a21 = this.getSize();
            int i35 = a21.width;
            int i36 = i35 / 2;
            int i37 = this.nodeWidth;
            int i38 = i37 / 2;
            int i39 = i36 - i38;
            int i40 = this.yBuffer;
            int i41 = 3 * i40;
            com.cim.AIA.ElementArray a22 = this.elementArray;
            int i42 = a22.getHeight();
            int i43 = i41 + i42;
            a20.plantTree(i39, i43);
        }
    }
    
    public void processRepaintEvent(com.cim.AIA.RepaintEvent a)
    {
        this.removeAllSelectables();
        Object a0 = a.paramObj;
        label0: {
            if(a0 == null)
            {
                break label0;
            }
            Object a1 = a.paramObj;
            RedBlackTree dummy = (RedBlackTree)a1;
            RedBlackTree a2 = (RedBlackTree)a1;
            this.redBlackTree = a2;
            RedBlackTree a3 = this.redBlackTree;
            NodeSelection a4 = NodeSelection.getInstance(a3);
            this.removeSelectionListener((com.cim.AIA.SelectionListener)a4);
            RedBlackTree a5 = this.redBlackTree;
            NodeSelection a6 = NodeSelection.getInstance(a5);
            this.addSelectionListener((com.cim.AIA.SelectionListener)a6);
            int i = this.showAs234Only?1:0;
            label1: {
                if(i != 0)
                {
                    break label1;
                }
                int i0 = this.showTute?1:0;
                if(i0 != 0)
                {
                    break label1;
                }
                int i1 = this.showCode?1:0;
                if(i1 == 0)
                {
                    this.processRB();
                    this.animateRB();
                }
            }
            int i2 = this.showAs234Only?1:0;
            label2: {
                if(i2 == 0)
                {
                    break label2;
                }
                int i3 = this.showTute?1:0;
                if(i3 == 0)
                {
                    this.process234();
                }
            }
            int i4 = this.showAs234Only?1:0;
            label3: {
                if(i4 != 0)
                {
                    break label3;
                }
                int i5 = this.showTute?1:0;
                if(i5 != 0)
                {
                    this.processTute();
                    this.animateTute();
                }
            }
            int i6 = this.showCode?1:0;
            if(i6 != 0)
            {
                this.processCode();
            }
            RedBlackTree a7 = this.redBlackTree;
            a7.removeAllAnimationRequests();
            this.repaint();
        }
    }
    
    public void processTute()
    {
        int i = 0;
        int i0 = 0;
        RedBlackTree a = this.redBlackTree;
        int i1 = this.nodeWidth;
        com.cim.AIA.HierarchyTree a0 = a.getTuteTree(i1);
        this.tuteTree = a0;
        RedBlackTree a1 = this.redBlackTree;
        java.util.Vector a2 = a1.getLineMoveRequests();
        this.lineMoveRequests = a2;
        com.cim.AIA.HierarchyTree a3 = this.tuteTree;
        if(a3 != null)
        {
            com.cim.AIA.HierarchyTree a4 = this.tuteTree;
            java.awt.Dimension a5 = this.getSize();
            int i2 = a5.width;
            int i3 = i2 / 2;
            int i4 = this.nodeWidth;
            int i5 = i4 / 2;
            int i6 = i3 - i5;
            int i7 = this.yBuffer;
            a4.plantTree(i6, i7);
        }
        com.cim.AIA.HierarchyTree a6 = this.tuteTree;
        if(a6 == null)
        {
            i = 0;
            i0 = 0;
        }
        else
        {
            com.cim.AIA.HierarchyTree a7 = this.tuteTree;
            java.awt.Rectangle a8 = a7.getRectangle();
            int i8 = a8.width;
            int i9 = a8.height;
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
        com.cim.AIA.HierarchyTree a9 = this.tuteTree;
        if(a9 != null)
        {
            com.cim.AIA.HierarchyTree a10 = this.tuteTree;
            java.awt.Dimension a11 = this.getSize();
            int i16 = a11.width;
            int i17 = i16 / 2;
            int i18 = this.nodeWidth;
            int i19 = i18 / 2;
            int i20 = i17 - i19;
            int i21 = this.yBuffer;
            a10.plantTree(i20, i21);
        }
        com.cim.AIA.HierarchyTree a12 = this.tuteTree;
        if(a12 != null)
        {
            com.cim.AIA.HierarchyTree a13 = this.tuteTree;
            this.addSelectable((com.cim.AIA.Selectable)a13);
        }
    }
    
    public void setShowAs234Only(boolean b)
    {
        this.showAs234Only = b;
    }
    
    public void setShowCode(boolean b)
    {
        this.showCode = b;
    }
    
    public void setShowTute(boolean b)
    {
        this.showTute = b;
    }
    
    static
    {
        String s = localization.Messages.getString("RedBlackTreeCanvas.0");
        RedBlackTreeCanvas.PTR_LABEL = s;
        String s0 = localization.Messages.getString("RedBlackTreeCanvas.1");
        RedBlackTreeCanvas.PARENT_LABEL = s0;
        String s1 = localization.Messages.getString("RedBlackTreeCanvas.2");
        RedBlackTreeCanvas.GRAND_PARENT_LABEL = s1;
        String s2 = localization.Messages.getString("RedBlackTreeCanvas.3");
        RedBlackTreeCanvas.GREAT_GRAND_PARENT_LABEL = s2;
        String[] a = new String[7];
        String s3 = localization.Messages.getString("RedBlackTreeCanvas.4");
        a[0] = s3;
        a[1] = "{";
        String s4 = localization.Messages.getString("RedBlackTreeCanvas.6");
        a[2] = s4;
        String s5 = localization.Messages.getString("RedBlackTreeCanvas.7");
        a[3] = s5;
        String s6 = localization.Messages.getString("RedBlackTreeCanvas.8");
        a[4] = s6;
        String s7 = localization.Messages.getString("RedBlackTreeCanvas.9");
        a[5] = s7;
        a[6] = "}";
        RedBlackTreeCanvas.rotateRight = a;
        String[] a0 = new String[7];
        String s8 = localization.Messages.getString("RedBlackTreeCanvas.11");
        a0[0] = s8;
        a0[1] = "{";
        String s9 = localization.Messages.getString("RedBlackTreeCanvas.13");
        a0[2] = s9;
        String s10 = localization.Messages.getString("RedBlackTreeCanvas.14");
        a0[3] = s10;
        String s11 = localization.Messages.getString("RedBlackTreeCanvas.15");
        a0[4] = s11;
        String s12 = localization.Messages.getString("RedBlackTreeCanvas.16");
        a0[5] = s12;
        a0[6] = "}";
        RedBlackTreeCanvas.rotateLeft = a0;
    }
}