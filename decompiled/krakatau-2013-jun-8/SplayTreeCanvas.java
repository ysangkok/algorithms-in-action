public class SplayTreeCanvas extends com.cim.AIA.AlgorithmCanvas {
    private int tweenSpeed;
    private SplayTreeCodeStack codeStack;
    private SplayTreeNode head;
    private com.cim.AIA.ElementArray insertData;
    private com.cim.AIA.ElementArray searchData;
    private SplayTreeNode currentNode;
    private boolean isShowTween;
    
    public SplayTreeCanvas()
    {
        super();
        this.isShowTween = true;
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
        SplayTreeNode a5 = this.head;
        if(a5 != null)
        {
            SplayTreeNode a6 = this.head;
            a6.draw(a);
        }
        com.cim.AIA.ElementArray a7 = this.searchData;
        if(a7 != null)
        {
            com.cim.AIA.ElementArray a8 = this.searchData;
            a8.draw(a);
        }
        SplayTreeNode a9 = this.currentNode;
        if(a9 != null)
        {
            String s = localization.Messages.getString("SplayTreeCanvas.0");
            java.awt.FontMetrics a10 = a.getFontMetrics();
            int i4 = a10.stringWidth(s);
            java.awt.Point a11 = new java.awt.Point(i4, i);
            SplayTreeNode a12 = this.currentNode;
            java.awt.Point a13 = a12.getPosition();
            this.drawLine(a, a11, a13, s);
        }
        SplayTreeCodeStack a14 = this.codeStack;
        if(a14 != null)
        {
            SplayTreeCodeStack a15 = this.codeStack;
            a15.draw(a);
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
            SplayTree dummy = (SplayTree)a2;
            SplayTree a3 = (SplayTree)a2;
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
            SplayTreeNode a9 = a3.getHead();
            this.head = a9;
            SplayTreeNode a10 = this.head;
            if(a10 == null)
            {
                i0 = i;
            }
            else
            {
                SplayTreeNode a11 = this.head;
                java.awt.Point a12 = new java.awt.Point(20, i);
                a11.setUpperLeft(a12);
                SplayTreeNode a13 = this.head;
                SplayTreeNode a14 = this.head;
                java.awt.Rectangle a15 = a14.getRectangle();
                int i9 = a15.width;
                int i10 = i9 / 2;
                int i11 = i2 - i10;
                java.awt.Point a16 = new java.awt.Point(i11, i);
                a13.setUpperLeft(a16);
                SplayTreeNode a17 = this.head;
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
            SplayTreeNode a24 = a3.getCurrentNode();
            this.currentNode = a24;
            SplayTreeCodeStack a25 = a3.getCodeStack();
            this.codeStack = a25;
            SplayTreeCodeStack a26 = this.codeStack;
            if(a26 != null)
            {
                SplayTreeCodeStack a27 = this.codeStack;
                java.awt.Dimension a28 = this.getParentSize();
                int i19 = a28.width;
                SplayTreeCodeStack a29 = this.codeStack;
                int i20 = a29.getWidth();
                int i21 = i19 - i20;
                java.awt.Dimension a30 = this.getParentSize();
                int i22 = a30.height;
                int i23 = i22 - 25;
                java.awt.Point a31 = new java.awt.Point(i21, i23);
                a27.setPosition(a31);
            }
            this.repaint();
            int i24 = this.numberOfTweenSteps;
            java.util.Vector a32 = a3.getTweens((com.cim.AIA.Paintable)this, (Object)null, i24);
            int i25 = a3.isTweenFast()?1:0;
            if(i25 == 0)
            {
                int i26 = this.tweenSpeed;
                ((com.cim.AIA.AlgorithmCanvas)this).setTweenSpeed(i26);
            }
            else
            {
                int i27 = this.tweenSpeed;
                int i28 = i27 / 3;
                ((com.cim.AIA.AlgorithmCanvas)this).setTweenSpeed(i28);
            }
            int i29 = this.isShowTween?1:0;
            if(i29 == 0)
            {
                break label0;
            }
            if(a32 == null)
            {
                break label0;
            }
            int i30 = 0;
            while(true)
            {
                int i31 = a32.size();
                if(i30 >= i31)
                {
                    break;
                }
                Object a33 = a32.elementAt(i30);
                label2: {
                    label1: {
                        int i32 = a33 instanceof java.util.Hashtable?1:0;
                        if(i32 == 0)
                        {
                            break label1;
                        }
                        break label2;
                    }
                    int i33 = a33 instanceof com.cim.AIA.MultipleTween?1:0;
                    if(i33 == 0)
                    {
                        RuntimeException a34 = new RuntimeException("Vector of unexpected type");
                        throw a34;
                    }
                    com.cim.AIA.MultipleTween dummy0 = (com.cim.AIA.MultipleTween)a33;
                    com.cim.AIA.MultipleTween a35 = (com.cim.AIA.MultipleTween)a33;
                    this.addTween((com.cim.AIA.Tween)a35);
                }
                com.cim.AIA.MultipleTween a36 = this.tweens;
                int i34 = a36.getNumberOfTweens();
                if(i34 > 0)
                {
                    com.cim.AIA.MultipleTween a37 = this.tweens;
                    a37.run();
                }
                int i35 = i30 + 1;
                i30 = i35;
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
        java.awt.Color a4 = SplayTreeColors.POINTER_COLOR;
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