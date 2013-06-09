public class SplayTreeIterCanvas extends com.cim.AIA.AlgorithmCanvas {
    private int tweenSpeed;
    protected java.util.Hashtable oldPositions;
    final private static int XGAP = 10;
    final private static int YGAP = 5;
    private SplayTreeIterCodeStack codeStack;
    private SplayTreeIterNode head;
    private com.cim.AIA.ElementArray insertData;
    private com.cim.AIA.ElementArray searchData;
    private SplayTreeIterNode currentNode;
    private SplayTreeIterNode parentNode;
    private boolean isShowTween;
    private boolean noMoveTween;
    private int currentLevel;
    
    public SplayTreeIterCanvas()
    {
        super();
        this.isShowTween = true;
    }
    
    protected java.util.Hashtable getPositions(SplayTreeIterNode a)
    {
        java.util.Hashtable a0 = null;
        int i = a instanceof SplayTreeIterNullNode?1:0;
        if(i == 0)
        {
            java.util.Hashtable a1 = new java.util.Hashtable();
            this.getPositions(a, a1);
            a0 = a1;
        }
        else
        {
            a0 = null;
        }
        return a0;
    }
    
    protected void getPositions(SplayTreeIterNode a, java.util.Hashtable a0)
    {
        java.awt.Point a1 = a.getPosition();
        java.awt.Point a2 = new java.awt.Point(a1);
        Object a3 = a0.put((Object)a, (Object)a2);
        int i = a instanceof SplayTreeIterNullNode?1:0;
        if(i == 0)
        {
            SplayTreeIterDataNode dummy = (SplayTreeIterDataNode)a;
            SplayTreeIterDataNode a4 = (SplayTreeIterDataNode)a;
            SplayTreeIterNode a5 = a4.getLeft();
            this.getPositions(a5, a0);
            SplayTreeIterDataNode dummy0 = (SplayTreeIterDataNode)a;
            SplayTreeIterDataNode a6 = (SplayTreeIterDataNode)a;
            SplayTreeIterNode a7 = a6.getRight();
            this.getPositions(a7, a0);
        }
    }
    
    protected com.cim.AIA.MultipleTween getMoveTweens(SplayTreeIterNode a, java.util.Hashtable a0, java.util.Hashtable a1)
    {
        com.cim.AIA.MultipleTween a2 = new com.cim.AIA.MultipleTween((com.cim.AIA.Paintable)this);
        this.getMoveTweens(a, a0, a1, a2);
        return a2;
    }
    
    protected void getMoveTweens(SplayTreeIterNode a, java.util.Hashtable a0, java.util.Hashtable a1, com.cim.AIA.MultipleTween a2)
    {
        Object a3 = a0.get((Object)a);
        java.awt.Point dummy = (java.awt.Point)a3;
        java.awt.Point a4 = (java.awt.Point)a3;
        Object a5 = a1.get((Object)a);
        java.awt.Point dummy0 = (java.awt.Point)a5;
        java.awt.Point a6 = (java.awt.Point)a5;
        label0: {
            if(a4 == null)
            {
                break label0;
            }
            if(a6 == null)
            {
                break label0;
            }
            int i = a4.x;
            int i0 = a6.x;
            label1: {
                if(i != i0)
                {
                    break label1;
                }
                int i1 = a4.y;
                int i2 = a6.y;
                if(i1 == i2)
                {
                    break label0;
                }
            }
            int i3 = this.numberOfTweenSteps;
            com.cim.AIA.MoveTween a7 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Moveable)a, a4, a6, false, i3);
            a2.add((com.cim.AIA.Tween)a7);
        }
        int i4 = a instanceof SplayTreeIterNullNode?1:0;
        if(i4 == 0)
        {
            SplayTreeIterDataNode dummy1 = (SplayTreeIterDataNode)a;
            SplayTreeIterDataNode a8 = (SplayTreeIterDataNode)a;
            SplayTreeIterNode a9 = a8.getLeft();
            this.getMoveTweens(a9, a0, a1, a2);
            SplayTreeIterDataNode dummy2 = (SplayTreeIterDataNode)a;
            SplayTreeIterDataNode a10 = (SplayTreeIterDataNode)a;
            SplayTreeIterNode a11 = a10.getRight();
            this.getMoveTweens(a11, a0, a1, a2);
        }
    }
    
    public void displayAlgorithm(java.awt.Graphics a)
    {
        int i = 0;
        com.cim.AIA.ElementArray a0 = this.insertData;
        if(a0 == null)
        {
            i = 15;
        }
        else
        {
            com.cim.AIA.ElementArray a1 = this.insertData;
            a1.draw(a);
            com.cim.AIA.ElementArray a2 = this.insertData;
            java.awt.Point a3 = a2.getLocation();
            int i0 = a3.y;
            com.cim.AIA.ElementArray a4 = this.insertData;
            int i1 = a4.getHeight();
            int i2 = i0 + i1;
            int i3 = i2 + 15;
            i = i3;
        }
        SplayTreeIterNode a5 = this.head;
        if(a5 != null)
        {
            SplayTreeIterNode a6 = this.head;
            a6.draw(a);
        }
        com.cim.AIA.ElementArray a7 = this.searchData;
        if(a7 != null)
        {
            com.cim.AIA.ElementArray a8 = this.searchData;
            a8.draw(a);
        }
        int i4 = i + 5;
        SplayTreeIterNode a9 = this.parentNode;
        if(a9 != null)
        {
            String s = localization.Messages.getString("SplayTreeIterCanvas.0");
            java.awt.FontMetrics a10 = a.getFontMetrics();
            int i5 = a10.stringWidth(s);
            int i6 = i5 / 2;
            int i7 = i6 + 10;
            java.awt.Point a11 = new java.awt.Point(i7, i4);
            SplayTreeIterNode a12 = this.parentNode;
            java.awt.Point a13 = a12.getPosition();
            this.drawLine(a, a11, a13, s);
        }
        java.awt.FontMetrics a14 = a.getFontMetrics();
        int i8 = a14.getHeight();
        int i9 = i4 + i8;
        int i10 = i9 + 5;
        SplayTreeIterNode a15 = this.currentNode;
        if(a15 != null)
        {
            String s0 = localization.Messages.getString("SplayTreeIterCanvas.1");
            java.awt.FontMetrics a16 = a.getFontMetrics();
            int i11 = a16.stringWidth(s0);
            int i12 = i11 / 2;
            int i13 = i12 + 10;
            java.awt.Point a17 = new java.awt.Point(i13, i10);
            SplayTreeIterNode a18 = this.currentNode;
            java.awt.Point a19 = a18.getPosition();
            this.drawLine(a, a17, a19, s0);
        }
        java.awt.FontMetrics a20 = a.getFontMetrics();
        int i14 = a20.getHeight();
        int i15 = i10 + i14;
        int i16 = i15 + 5;
        int i17 = this.currentLevel;
        if(i17 != -1)
        {
            String s1 = localization.Messages.getString("SplayTreeIterCanvas.2");
            a.drawString(s1, 10, i16);
            int i18 = this.currentLevel;
            Integer a21 = new Integer(i18);
            com.cim.AIA.Node a22 = new com.cim.AIA.Node((Object)a21, -1);
            java.awt.FontMetrics a23 = a.getFontMetrics();
            String s2 = localization.Messages.getString("SplayTreeIterCanvas.3");
            int i19 = a23.stringWidth(s2);
            int i20 = 10 + i19;
            java.awt.FontMetrics a24 = a.getFontMetrics();
            int i21 = a24.getHeight();
            int i22 = i16 - i21;
            java.awt.Point a25 = new java.awt.Point(i20, i22);
            a22.setPosition(a25);
            a22.draw(a);
        }
        SplayTreeIterCodeStack a26 = this.codeStack;
        if(a26 != null)
        {
            SplayTreeIterCodeStack a27 = this.codeStack;
            a27.draw(a);
        }
    }
    
    public void paint()
    {
        this.repaint();
    }
    
    public void processRepaintEvent(com.cim.AIA.RepaintEvent a)
    {
        Object a0 = a.paramObj;
        label0: {
            int i = 0;
            int i0 = 0;
            if(a0 == null)
            {
                break label0;
            }
            java.awt.Dimension a1 = this.getParentSize();
            int i1 = a1.width;
            int i2 = i1 / 2;
            Object a2 = a.paramObj;
            SplayTreeIter dummy = (SplayTreeIter)a2;
            SplayTreeIter a3 = (SplayTreeIter)a2;
            com.cim.AIA.ElementArray a4 = a3.getInsertData();
            this.insertData = a4;
            com.cim.AIA.ElementArray a5 = this.insertData;
            if(a5 == null)
            {
                i = 25;
            }
            else
            {
                com.cim.AIA.ElementArray a6 = this.insertData;
                com.cim.AIA.ElementArray a7 = this.insertData;
                int i3 = a7.getWidth();
                int i4 = i3 / 2;
                int i5 = i2 - i4;
                a6.setLocation(i5, 25);
                com.cim.AIA.ElementArray a8 = this.insertData;
                int i6 = a8.getHeight();
                int i7 = i6 + 25;
                int i8 = 25 + i7;
                i = i8;
            }
            SplayTreeIterNode a9 = a3.getHead();
            this.head = a9;
            SplayTreeIterNode a10 = this.head;
            if(a10 == null)
            {
                i0 = i;
            }
            else
            {
                SplayTreeIterNode a11 = this.head;
                java.awt.Point a12 = new java.awt.Point(20, i);
                a11.setUpperLeft(a12);
                SplayTreeIterNode a13 = this.head;
                SplayTreeIterNode a14 = this.head;
                java.awt.Rectangle a15 = a14.getRectangle();
                int i9 = a15.width;
                int i10 = i9 / 2;
                int i11 = i2 - i10;
                java.awt.Point a16 = new java.awt.Point(i11, i);
                a13.setUpperLeft(a16);
                SplayTreeIterNode a17 = this.head;
                java.awt.Rectangle a18 = a17.getRectangle();
                int i12 = a18.height;
                int i13 = i12 + 25;
                int i14 = i + i13;
                i0 = i14;
            }
            com.cim.AIA.ElementArray a19 = a3.getSearchData();
            this.searchData = a19;
            com.cim.AIA.ElementArray a20 = this.searchData;
            if(a20 != null)
            {
                com.cim.AIA.ElementArray a21 = this.searchData;
                com.cim.AIA.ElementArray a22 = this.searchData;
                int i15 = a22.getWidth();
                int i16 = i15 / 2;
                int i17 = i2 - i16;
                a21.setLocation(i17, i0);
                com.cim.AIA.ElementArray a23 = this.searchData;
                int i18 = a23.getHeight();
            }
            SplayTreeIterNode a24 = a3.getCurrentNode();
            this.currentNode = a24;
            SplayTreeIterNode a25 = a3.getParentNode();
            this.parentNode = a25;
            int i19 = a3.getCurrentLevel();
            this.currentLevel = i19;
            SplayTreeIterCodeStack a26 = a3.getCodeStack();
            this.codeStack = a26;
            SplayTreeIterCodeStack a27 = this.codeStack;
            if(a27 != null)
            {
                SplayTreeIterCodeStack a28 = this.codeStack;
                java.awt.Dimension a29 = this.getParentSize();
                int i20 = a29.width;
                SplayTreeIterCodeStack a30 = this.codeStack;
                int i21 = a30.getWidth();
                int i22 = i20 - i21;
                java.awt.Dimension a31 = this.getParentSize();
                int i23 = a31.height;
                int i24 = i23 - 25;
                java.awt.Point a32 = new java.awt.Point(i22, i24);
                a28.setPosition(a32);
            }
            this.repaint();
            int i25 = this.numberOfTweenSteps;
            java.util.Vector a33 = a3.getTweens((com.cim.AIA.Paintable)this, (Object)null, i25);
            int i26 = a3.isTweenFast()?1:0;
            if(i26 == 0)
            {
                int i27 = this.tweenSpeed;
                ((com.cim.AIA.AlgorithmCanvas)this).setTweenSpeed(i27);
            }
            else
            {
                int i28 = this.tweenSpeed;
                int i29 = i28 / 3;
                ((com.cim.AIA.AlgorithmCanvas)this).setTweenSpeed(i29);
            }
            int i30 = a3.getNoMoveTween()?1:0;
            this.noMoveTween = i30 != 0;
            SplayTreeIterNode a34 = this.head;
            java.util.Hashtable a35 = this.getPositions(a34);
            java.util.Hashtable a36 = this.oldPositions;
            label1: {
                if(a36 == null)
                {
                    break label1;
                }
                int i31 = this.noMoveTween?1:0;
                if(i31 != 0)
                {
                    break label1;
                }
                SplayTreeIterNode a37 = this.head;
                java.util.Hashtable a38 = this.oldPositions;
                com.cim.AIA.MultipleTween a39 = this.getMoveTweens(a37, a38, a35);
                int i32 = a39.getNumberOfTweens();
                if(i32 > 0)
                {
                    this.addTween((com.cim.AIA.Tween)a39);
                }
            }
            this.oldPositions = a35;
            int i33 = this.isShowTween?1:0;
            label2: {
                if(i33 == 0)
                {
                    break label2;
                }
                if(a33 == null)
                {
                    break label2;
                }
                int i34 = 0;
                while(true)
                {
                    int i35 = a33.size();
                    if(i34 >= i35)
                    {
                        break;
                    }
                    Object a40 = a33.elementAt(i34);
                    label4: {
                        label3: {
                            int i36 = a40 instanceof java.util.Hashtable?1:0;
                            if(i36 == 0)
                            {
                                break label3;
                            }
                            break label4;
                        }
                        int i37 = a40 instanceof com.cim.AIA.MultipleTween?1:0;
                        if(i37 == 0)
                        {
                            String s = localization.Messages.getString("SplayTreeIterCanvas.4");
                            RuntimeException a41 = new RuntimeException(s);
                            throw a41;
                        }
                        com.cim.AIA.MultipleTween dummy0 = (com.cim.AIA.MultipleTween)a40;
                        com.cim.AIA.MultipleTween a42 = (com.cim.AIA.MultipleTween)a40;
                        this.addTween((com.cim.AIA.Tween)a42);
                    }
                    int i38 = i34 + 1;
                    i34 = i38;
                }
            }
            com.cim.AIA.MultipleTween a43 = this.tweens;
            int i39 = a43.getNumberOfTweens();
            if(i39 > 0)
            {
                com.cim.AIA.MultipleTween a44 = this.tweens;
                a44.run();
            }
        }
    }
    
    private void drawLine(java.awt.Graphics a, java.awt.Point a0, java.awt.Point a1, String s)
    {
        int i = a0.x;
        int i0 = a0.y;
        int i1 = a1.x;
        int i2 = a1.y;
        com.cim.AIA.Line a2 = new com.cim.AIA.Line(i, i0, i1, i2);
        a2.setLabel(s);
        a2.setDistanceFromStartForArrowHead(-3);
        a2.setDistanceFromStartForLabel(-1);
        java.awt.FontMetrics a3 = a.getFontMetrics();
        int i3 = a3.stringWidth(s);
        int i4 = 0 - i3;
        int i5 = i4 / 2;
        a2.setXLabelOffset(i5);
        a2.showArrowHead(true);
        java.awt.Color a4 = SplayTreeIterColors.POINTER_COLOR;
        a2.setColor(a4);
        a2.draw(a);
    }
    
    protected void handleColorSelection(com.cim.AIA.ColorSelection a)
    {
    }
    
    protected void handleFontSelection(com.cim.AIA.FontSelection a)
    {
    }
    
    public void setTweenSpeed(int i)
    {
        this.tweenSpeed = i;
        ((com.cim.AIA.AlgorithmCanvas)this).setTweenSpeed(i);
    }
    
    public void setIsShowTween(boolean b)
    {
        this.isShowTween = b;
    }
}