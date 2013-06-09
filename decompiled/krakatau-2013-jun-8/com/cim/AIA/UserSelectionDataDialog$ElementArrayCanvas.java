package com.cim.AIA;

class UserSelectionDataDialog$ElementArrayCanvas extends com.cim.AIA.AlgorithmCanvas implements com.cim.AIA.SelectionListener {
    final private static long serialVersionUID = -3891669761144149335L;
    protected com.cim.AIA.ElementArray elementArray;
    protected int position;
    protected com.cim.AIA.Node selectedNode;
    protected boolean doingTweens;
    protected java.awt.TextField label;
    final com.cim.AIA.UserSelectionDataDialog this$0;
    
    public UserSelectionDataDialog$ElementArrayCanvas(com.cim.AIA.UserSelectionDataDialog a, java.awt.TextField a0)
    {
        this.this$0 = a;
        super();
        com.cim.AIA.ElementArray a1 = new com.cim.AIA.ElementArray(10, 30);
        this.elementArray = a1;
        this.position = 0;
        this.selectedNode = null;
        this.doingTweens = false;
        com.cim.AIA.MultipleTween a2 = this.tweens;
        a2.setSleep(1);
        com.cim.AIA.ElementArray a3 = this.elementArray;
        this.addSelectable((com.cim.AIA.Selectable)a3);
        this.addSelectionListener((com.cim.AIA.SelectionListener)this);
        com.cim.AIA.ElementArray a4 = this.elementArray;
        a4.setColumGap(1);
        com.cim.AIA.ElementArray a5 = this.elementArray;
        a5.setElementWidth(20);
        com.cim.AIA.ElementArray a6 = this.elementArray;
        a6.setAllHaveSameWidth(false);
        this.label = a0;
    }
    
    public void add(Object a, int i, boolean b)
    {
        int i0 = 0;
        int i1 = b?1:0;
        com.cim.AIA.Node a0 = new com.cim.AIA.Node(a, i);
        int i2 = i + 1;
        com.cim.AIA.ElementArray a1 = this.elementArray;
        int i3 = a1.getSize();
        label1: {
            label0: {
                if(i2 > i3)
                {
                    break label0;
                }
                com.cim.AIA.ElementArray a2 = this.elementArray;
                int i4 = a2.getSize();
                if(i4 != 0)
                {
                    i0 = i2;
                    break label1;
                }
            }
            com.cim.AIA.ElementArray a3 = this.elementArray;
            int i5 = a3.getSize();
            i0 = i5;
        }
        com.cim.AIA.ElementArray a4 = this.elementArray;
        a4.setValue(i0, (com.cim.AIA.Element)a0);
        com.cim.AIA.Node a5 = this.selectedNode;
        label2: {
            if(a5 != null)
            {
                break label2;
            }
            com.cim.AIA.ElementArray a6 = this.elementArray;
            int i6 = a6.getSize();
            if(i6 != 1)
            {
                int i7 = this.position;
                int i8 = i7 + 1;
                this.position = i8;
            }
            else
            {
                this.position = 0;
            }
        }
        if(i1 != 0)
        {
            com.cim.AIA.MultipleTween a7 = this.tweens;
            int i9 = this.numberOfTweenSteps;
            com.cim.AIA.InsertTween a8 = new com.cim.AIA.InsertTween((com.cim.AIA.Paintable)this, (com.cim.AIA.Sizeable)a0, i9);
            a7.add((com.cim.AIA.Tween)a8);
        }
    }
    
    public void additem(Object a, int i, boolean b)
    {
        int i0 = 0;
        int i1 = 0;
        int i2 = 0;
        int i3 = b?1:0;
        if(!b)
        {
            int i4 = i - 1;
            this.add(a, i4, true);
            i0 = i3;
        }
        else
        {
            this.add(a, i, true);
            i0 = 1;
        }
        com.cim.AIA.Node a0 = this.selectedNode;
        label0: {
            if(a0 == null)
            {
                i1 = i0;
                break label0;
            }
            if(i0 != 1)
            {
                i1 = i0;
            }
            else
            {
                this.increment();
                this.repaint();
                i1 = 1;
            }
        }
        com.cim.AIA.Node a1 = this.selectedNode;
        label1: {
            if(a1 != null)
            {
                i2 = i1;
                break label1;
            }
            if(i1 != 0)
            {
                i2 = i1;
            }
            else
            {
                this.decriment();
                this.repaint();
                i2 = 0;
            }
        }
        com.cim.AIA.Node a2 = this.selectedNode;
        label2: {
            if(a2 == null)
            {
                break label2;
            }
            if(i2 == 0)
            {
                com.cim.AIA.Node a3 = this.selectedNode;
                a3.unHighlight();
                this.repaint();
            }
        }
    }
    
    public void decriment()
    {
        int i = this.position;
        int i0 = i - 1;
        if(i0 >= 0)
        {
            int i1 = this.position;
            int i2 = i1 - 1;
            this.position = i2;
        }
        else
        {
            com.cim.AIA.ElementArray a = this.elementArray;
            int i3 = a.getSize();
            int i4 = i3 - 1;
            this.position = i4;
        }
        com.cim.AIA.Node a0 = this.selectedNode;
        if(a0 != null)
        {
            com.cim.AIA.Node a1 = this.selectedNode;
            a1.unHighlight();
        }
        com.cim.AIA.ElementArray a2 = this.elementArray;
        int i5 = this.position;
        com.cim.AIA.Element a3 = a2.getElement(i5);
        com.cim.AIA.Node dummy = (com.cim.AIA.Node)a3;
        com.cim.AIA.Node a4 = (com.cim.AIA.Node)a3;
        this.selectedNode = a4;
        com.cim.AIA.Node a5 = this.selectedNode;
        a5.highlight();
    }
    
    public void displayAlgorithm(java.awt.Graphics a)
    {
        int i = this.doingTweens?1:0;
        label0: {
            if(i != 0)
            {
                break label0;
            }
            com.cim.AIA.ElementArray a0 = this.elementArray;
            if(a0 != null)
            {
                com.cim.AIA.ElementArray a1 = this.elementArray;
                int i0 = a1.getWidth();
                int i1 = i0 + 50;
                java.awt.Dimension a2 = this.getSize();
                int i2 = a2.height;
                this.setSize(i1, i2);
            }
        }
        com.cim.AIA.ElementArray a3 = this.elementArray;
        if(a3 != null)
        {
            com.cim.AIA.ElementArray a4 = this.elementArray;
            java.awt.Dimension a5 = this.getSize();
            int i3 = a5.width;
            int i4 = i3 / 2;
            com.cim.AIA.ElementArray a6 = this.elementArray;
            int i5 = a6.getWidth();
            int i6 = i5 / 2;
            int i7 = i4 - i6;
            a4.setLocation(i7, 20);
            com.cim.AIA.ElementArray a7 = this.elementArray;
            a7.draw(a);
        }
        int i8 = this.position;
        label1: {
            if(i8 == -1)
            {
                break label1;
            }
            com.cim.AIA.ElementArray a8 = this.elementArray;
            int i9 = this.position;
            com.cim.AIA.Element a9 = a8.getElement(i9);
            com.cim.AIA.Node dummy = (com.cim.AIA.Node)a9;
            com.cim.AIA.Node a10 = (com.cim.AIA.Node)a9;
            if(a10 != null)
            {
                java.awt.Point a11 = a10.getPosition();
                int i10 = a11.x;
                int i11 = a10.getWidth();
                int i12 = i11 / 2;
                int i13 = i10 + i12;
                com.cim.AIA.Line a12 = new com.cim.AIA.Line(i13, 0, i13, 20);
                a12.showArrowHead(true);
                a12.setDistanceFromStartForArrowHead(-3);
                a12.draw(a);
            }
        }
        int i14 = this.doingTweens?1:0;
        label2: {
            if(i14 != 0)
            {
                break label2;
            }
            com.cim.AIA.MultipleTween a13 = this.tweens;
            int i15 = a13.getNumberOfTweens();
            if(i15 > 0)
            {
                this.doingTweens = true;
                com.cim.AIA.MultipleTween a14 = this.tweens;
                a14.run();
                com.cim.AIA.UserSelectionDataDialog a15 = this.this$0;
                a15.initialiseComponents();
                this.doingTweens = false;
            }
        }
    }
    
    public int getCurrentPosition()
    {
        int i = this.position;
        return i;
    }
    
    public int getNumberOfElements()
    {
        com.cim.AIA.ElementArray a = this.elementArray;
        int i = a.getSize();
        return i;
    }
    
    public java.util.Vector getObjects()
    {
        java.util.Vector a = new java.util.Vector();
        int i = 0;
        while(true)
        {
            com.cim.AIA.ElementArray a0 = this.elementArray;
            int i0 = a0.getSize();
            if(i >= i0)
            {
                return a;
            }
            else
            {
                com.cim.AIA.ElementArray a1 = this.elementArray;
                com.cim.AIA.Element a2 = a1.getElement(i);
                com.cim.AIA.Node dummy = (com.cim.AIA.Node)a2;
                com.cim.AIA.Node a3 = (com.cim.AIA.Node)a2;
                Object a4 = a3.getObject();
                a.addElement(a4);
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    protected void handleColorSelection(com.cim.AIA.ColorSelection a)
    {
    }
    
    protected void handleFontSelection(com.cim.AIA.FontSelection a)
    {
    }
    
    public boolean haveSelected()
    {
        com.cim.AIA.Node a = this.selectedNode;
        int i = a == null?0:1;
        return i != 0;
    }
    
    public void increment()
    {
        int i = this.position;
        int i0 = i + 1;
        com.cim.AIA.ElementArray a = this.elementArray;
        int i1 = a.getSize();
        if(i0 < i1)
        {
            int i2 = this.position;
            int i3 = i2 + 1;
            this.position = i3;
        }
        else
        {
            this.position = 0;
        }
        com.cim.AIA.Node a0 = this.selectedNode;
        if(a0 != null)
        {
            com.cim.AIA.Node a1 = this.selectedNode;
            a1.unHighlight();
        }
        com.cim.AIA.ElementArray a2 = this.elementArray;
        int i4 = this.position;
        com.cim.AIA.Element a3 = a2.getElement(i4);
        com.cim.AIA.Node dummy = (com.cim.AIA.Node)a3;
        com.cim.AIA.Node a4 = (com.cim.AIA.Node)a3;
        this.selectedNode = a4;
        com.cim.AIA.Node a5 = this.selectedNode;
        a5.highlight();
    }
    
    public void modify(Object a, int i)
    {
        com.cim.AIA.ElementArray a0 = this.elementArray;
        com.cim.AIA.Element a1 = a0.getElement(i);
        com.cim.AIA.Node dummy = (com.cim.AIA.Node)a1;
        com.cim.AIA.Node a2 = (com.cim.AIA.Node)a1;
        if(a2 != null)
        {
            a2.setObject(a);
            this.increment();
        }
    }
    
    public void paint()
    {
        java.awt.Graphics a = this.getGraphics();
        this.paint(a);
    }
    
    public void processRepaintEvent(com.cim.AIA.RepaintEvent a)
    {
        this.repaint();
    }
    
    public void processSelectionEvent(com.cim.AIA.SelectionEvent a)
    {
        Object a0 = a.paramObj;
        label0: {
            if(a0 == null)
            {
                break label0;
            }
            com.cim.AIA.Node a1 = this.selectedNode;
            label2: {
                label1: {
                    if(a1 == null)
                    {
                        break label1;
                    }
                    com.cim.AIA.Node a2 = this.selectedNode;
                    Object a3 = a.paramObj;
                    com.cim.AIA.Node dummy = (com.cim.AIA.Node)a3;
                    com.cim.AIA.Node a4 = (com.cim.AIA.Node)a3;
                    if(a2 != a4)
                    {
                        break label1;
                    }
                    break label2;
                }
                com.cim.AIA.Node a5 = this.selectedNode;
                if(a5 != null)
                {
                    com.cim.AIA.Node a6 = this.selectedNode;
                    a6.unHighlight();
                }
                Object a7 = a.paramObj;
                com.cim.AIA.Node dummy0 = (com.cim.AIA.Node)a7;
                com.cim.AIA.Node a8 = (com.cim.AIA.Node)a7;
                this.selectedNode = a8;
                int i = 0;
                while(true)
                {
                    com.cim.AIA.ElementArray a9 = this.elementArray;
                    int i0 = a9.getSize();
                    if(i >= i0)
                    {
                        break;
                    }
                    com.cim.AIA.Node a10 = this.selectedNode;
                    com.cim.AIA.ElementArray a11 = this.elementArray;
                    com.cim.AIA.Element a12 = a11.getElement(i);
                    com.cim.AIA.Node dummy1 = (com.cim.AIA.Node)a12;
                    com.cim.AIA.Node a13 = (com.cim.AIA.Node)a12;
                    if(a10 == a13)
                    {
                        this.position = i;
                    }
                    int i1 = i + 1;
                    i = i1;
                }
            }
            java.awt.TextField a14 = this.label;
            a14.requestFocus();
        }
    }
    
    public void remove(int i)
    {
        com.cim.AIA.ElementArray a = this.elementArray;
        com.cim.AIA.Element a0 = a.getElement(i);
        com.cim.AIA.Node dummy = (com.cim.AIA.Node)a0;
        com.cim.AIA.Node a1 = (com.cim.AIA.Node)a0;
        label0: {
            if(a1 == null)
            {
                break label0;
            }
            com.cim.AIA.Node a2 = this.selectedNode;
            if(a1 == a2)
            {
                this.selectedNode = null;
            }
            java.awt.Color a3 = java.awt.Color.gray;
            a1.setBackgroundColor(a3);
            com.cim.AIA.MultipleTween a4 = this.tweens;
            com.cim.AIA.ElementArray a5 = this.elementArray;
            int i0 = this.numberOfTweenSteps;
            com.cim.AIA.DeleteFromTween a6 = new com.cim.AIA.DeleteFromTween((com.cim.AIA.Paintable)this, (com.cim.AIA.Sizeable)a1, (com.cim.AIA.ObjectContainer)a5, i0);
            a4.add((com.cim.AIA.Tween)a6);
            int i1 = this.position;
            com.cim.AIA.ElementArray a7 = this.elementArray;
            int i2 = a7.getSize();
            int i3 = i2 - 1;
            if(i1 >= i3)
            {
                int i4 = this.position;
                int i5 = i4 - 1;
                this.position = i5;
            }
        }
    }
}