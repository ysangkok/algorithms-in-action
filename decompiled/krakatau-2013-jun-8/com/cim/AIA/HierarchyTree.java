package com.cim.AIA;

public class HierarchyTree extends com.cim.AIA.Tree implements com.cim.AIA.Drawable, com.cim.AIA.Moveable, com.cim.AIA.Selectable {
    final private static long serialVersionUID = -7957106999101569662L;
    final protected static java.awt.Color BORDER_COLOR;
    final protected static java.awt.Color PARENT_LINE_COLOR;
    final protected static int BASE_HEIGHT = 20;
    protected static java.awt.FontMetrics metrics;
    protected java.awt.Color borderColor;
    protected boolean drawBorder;
    protected java.awt.Color parentLineColor;
    protected java.awt.Color mainHighlightColor;
    protected java.util.Vector elements;
    protected int height;
    protected int border;
    protected int parentDistance;
    protected java.awt.Point offset;
    protected com.cim.AIA.Polygon contour;
    protected java.awt.Point pos;
    protected com.cim.AIA.Line line;
    protected boolean drawParentLine;
    protected boolean recalculateLine;
    protected java.awt.Point prevPos;
    protected int sequenceNumber;
    protected com.cim.AIA.WTFactory factory;
    
    protected static boolean compare(com.cim.AIA.HierarchyTree a, com.cim.AIA.HierarchyTree a0)
    {
        int i = 0;
        int i0 = a.getIdentifier();
        int i1 = a0.getIdentifier();
        label1: {
            label0: {
                if(i0 == i1)
                {
                    break label0;
                }
                i = 0;
                break label1;
            }
            int i2 = 0;
            label2: {
                while(true)
                {
                    java.util.Vector a1 = a.elements;
                    int i3 = a1.size();
                    if(i2 >= i3)
                    {
                        break label2;
                    }
                    java.util.Vector a2 = a.elements;
                    Object a3 = a2.elementAt(i2);
                    com.cim.AIA.Node dummy = (com.cim.AIA.Node)a3;
                    com.cim.AIA.Node a4 = (com.cim.AIA.Node)a3;
                    java.util.Vector a5 = a0.elements;
                    Object a6 = a5.elementAt(i2);
                    com.cim.AIA.Selectable dummy0 = (com.cim.AIA.Selectable)a6;
                    int i4 = a4.equals((com.cim.AIA.Selectable)a6)?1:0;
                    if(i4 != 0)
                    {
                        int i5 = i2 + 1;
                        i2 = i5;
                        continue;
                    }
                    else
                    {
                        break;
                    }
                }
                i = 0;
                break label1;
            }
            int i6 = 0;
            while(true)
            {
                java.util.Vector a7 = a.children;
                int i7 = a7.size();
                label3: {
                    if(i6 < i7)
                    {
                        break label3;
                    }
                    i = 1;
                    break;
                }
                java.util.Vector a8 = a.children;
                Object a9 = a8.elementAt(i6);
                com.cim.AIA.HierarchyTree dummy1 = (com.cim.AIA.HierarchyTree)a9;
                com.cim.AIA.HierarchyTree a10 = (com.cim.AIA.HierarchyTree)a9;
                java.util.Vector a11 = a0.children;
                Object a12 = a11.elementAt(i6);
                com.cim.AIA.HierarchyTree dummy2 = (com.cim.AIA.HierarchyTree)a12;
                com.cim.AIA.HierarchyTree a13 = (com.cim.AIA.HierarchyTree)a12;
                int i8 = a10.equals((com.cim.AIA.Selectable)a13)?1:0;
                if(i8 != 0)
                {
                    int i9 = i6 + 1;
                    i6 = i9;
                    continue;
                }
                i = 0;
                break;
            }
        }
        return i != 0;
    }
    
    public HierarchyTree()
    {
        super((com.cim.AIA.Tree)null);
        java.awt.Color a = com.cim.AIA.HierarchyTree.BORDER_COLOR;
        this.borderColor = a;
        this.drawBorder = true;
        java.awt.Color a0 = com.cim.AIA.HierarchyTree.PARENT_LINE_COLOR;
        this.parentLineColor = a0;
        this.mainHighlightColor = null;
        java.util.Vector a1 = new java.util.Vector();
        this.elements = a1;
        this.height = 20;
        this.border = 10;
        this.parentDistance = 5;
        java.awt.Point a2 = new java.awt.Point(0, 0);
        this.offset = a2;
        com.cim.AIA.Polygon a3 = new com.cim.AIA.Polygon();
        this.contour = a3;
        java.awt.Point a4 = new java.awt.Point();
        this.pos = a4;
        com.cim.AIA.Line a5 = new com.cim.AIA.Line(0, 0, 0, 0);
        this.line = a5;
        this.drawParentLine = true;
        this.recalculateLine = true;
        java.awt.Point a6 = new java.awt.Point();
        this.prevPos = a6;
        this.sequenceNumber = 1;
        com.cim.AIA.WTFactory a7 = new com.cim.AIA.WTFactory();
        this.factory = a7;
        this.initialiseLine();
    }
    
    public HierarchyTree(com.cim.AIA.Node a)
    {
        super((com.cim.AIA.Tree)null);
        java.awt.Color a0 = com.cim.AIA.HierarchyTree.BORDER_COLOR;
        this.borderColor = a0;
        this.drawBorder = true;
        java.awt.Color a1 = com.cim.AIA.HierarchyTree.PARENT_LINE_COLOR;
        this.parentLineColor = a1;
        this.mainHighlightColor = null;
        java.util.Vector a2 = new java.util.Vector();
        this.elements = a2;
        this.height = 20;
        this.border = 10;
        this.parentDistance = 5;
        java.awt.Point a3 = new java.awt.Point(0, 0);
        this.offset = a3;
        com.cim.AIA.Polygon a4 = new com.cim.AIA.Polygon();
        this.contour = a4;
        java.awt.Point a5 = new java.awt.Point();
        this.pos = a5;
        com.cim.AIA.Line a6 = new com.cim.AIA.Line(0, 0, 0, 0);
        this.line = a6;
        this.drawParentLine = true;
        this.recalculateLine = true;
        java.awt.Point a7 = new java.awt.Point();
        this.prevPos = a7;
        this.sequenceNumber = 1;
        com.cim.AIA.WTFactory a8 = new com.cim.AIA.WTFactory();
        this.factory = a8;
        java.util.Vector a9 = this.elements;
        a9.addElement((Object)a);
        this.initialiseLine();
    }
    
    public HierarchyTree(com.cim.AIA.Tree a)
    {
        super(a);
        java.awt.Color a0 = com.cim.AIA.HierarchyTree.BORDER_COLOR;
        this.borderColor = a0;
        this.drawBorder = true;
        java.awt.Color a1 = com.cim.AIA.HierarchyTree.PARENT_LINE_COLOR;
        this.parentLineColor = a1;
        this.mainHighlightColor = null;
        java.util.Vector a2 = new java.util.Vector();
        this.elements = a2;
        this.height = 20;
        this.border = 10;
        this.parentDistance = 5;
        java.awt.Point a3 = new java.awt.Point(0, 0);
        this.offset = a3;
        com.cim.AIA.Polygon a4 = new com.cim.AIA.Polygon();
        this.contour = a4;
        java.awt.Point a5 = new java.awt.Point();
        this.pos = a5;
        com.cim.AIA.Line a6 = new com.cim.AIA.Line(0, 0, 0, 0);
        this.line = a6;
        this.drawParentLine = true;
        this.recalculateLine = true;
        java.awt.Point a7 = new java.awt.Point();
        this.prevPos = a7;
        this.sequenceNumber = 1;
        com.cim.AIA.WTFactory a8 = new com.cim.AIA.WTFactory();
        this.factory = a8;
        this.initialiseLine();
    }
    
    public HierarchyTree(com.cim.AIA.Tree a, com.cim.AIA.Node a0)
    {
        super(a);
        java.awt.Color a1 = com.cim.AIA.HierarchyTree.BORDER_COLOR;
        this.borderColor = a1;
        this.drawBorder = true;
        java.awt.Color a2 = com.cim.AIA.HierarchyTree.PARENT_LINE_COLOR;
        this.parentLineColor = a2;
        this.mainHighlightColor = null;
        java.util.Vector a3 = new java.util.Vector();
        this.elements = a3;
        this.height = 20;
        this.border = 10;
        this.parentDistance = 5;
        java.awt.Point a4 = new java.awt.Point(0, 0);
        this.offset = a4;
        com.cim.AIA.Polygon a5 = new com.cim.AIA.Polygon();
        this.contour = a5;
        java.awt.Point a6 = new java.awt.Point();
        this.pos = a6;
        com.cim.AIA.Line a7 = new com.cim.AIA.Line(0, 0, 0, 0);
        this.line = a7;
        this.drawParentLine = true;
        this.recalculateLine = true;
        java.awt.Point a8 = new java.awt.Point();
        this.prevPos = a8;
        this.sequenceNumber = 1;
        com.cim.AIA.WTFactory a9 = new com.cim.AIA.WTFactory();
        this.factory = a9;
        java.util.Vector a10 = this.elements;
        a10.addElement((Object)a0);
        this.initialiseLine();
    }
    
    public void add(com.cim.AIA.Node a)
    {
        java.util.Vector a0 = this.elements;
        a0.addElement((Object)a);
    }
    
    public void add(com.cim.AIA.Node a, int i)
    {
        label2: {
            label1: {
                label0: {
                    if(i < 0)
                    {
                        break label0;
                    }
                    java.util.Vector a0 = this.elements;
                    int i0 = a0.size();
                    if(i <= i0)
                    {
                        break label1;
                    }
                }
                java.util.Vector a1 = this.elements;
                a1.addElement((Object)a);
                break label2;
            }
            java.util.Vector a2 = this.elements;
            a2.insertElementAt((Object)a, i);
        }
    }
    
    protected void calculateLine()
    {
        java.awt.Point a = this.getParentConnectPoint();
        if(a != null)
        {
            com.cim.AIA.Line a0 = this.line;
            int i = a.x;
            a0.setIntX1(i);
            com.cim.AIA.Line a1 = this.line;
            int i0 = a.y;
            int i1 = i0 + 1;
            a1.setIntY1(i1);
            com.cim.AIA.Line a2 = this.line;
            java.awt.Point a3 = this.pos;
            int i2 = a3.x;
            int i3 = this.getWidth();
            int i4 = i3 / 2;
            int i5 = i2 + i4;
            a2.setIntX2(i5);
            com.cim.AIA.Line a4 = this.line;
            java.awt.Point a5 = this.pos;
            int i6 = a5.y;
            a4.setIntY2(i6);
        }
        com.cim.AIA.Line a6 = this.line;
        java.awt.Color a7 = this.parentLineColor;
        a6.setColor(a7);
    }
    
    public boolean contains(int i, int i0)
    {
        java.awt.Point a = this.pos;
        int i1 = a.x;
        java.awt.Point a0 = this.pos;
        int i2 = a0.y;
        int i3 = this.getWidth();
        int i4 = this.height;
        java.awt.Rectangle a1 = new java.awt.Rectangle(i1, i2, i3, i4);
        int i5 = a1.contains(i, i0)?1:0;
        return i5 != 0;
    }
    
    public void displayNode()
    {
        int i = 0;
        while(true)
        {
            java.util.Vector a = this.elements;
            int i0 = a.size();
            if(i >= i0)
            {
                return;
            }
            else
            {
                java.util.Vector a0 = this.elements;
                Object a1 = a0.elementAt(i);
                com.cim.AIA.Node dummy = (com.cim.AIA.Node)a1;
                com.cim.AIA.Node a2 = (com.cim.AIA.Node)a1;
                java.io.PrintStream a3 = System.out;
                StringBuilder a4 = new StringBuilder();
                Object a5 = a2.getObject();
                StringBuilder a6 = a4.append(a5);
                StringBuilder a7 = a6.append(" ");
                String s = a7.toString();
                a3.print(s);
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    public void draw(java.awt.Graphics a)
    {
        com.cim.AIA.WTFactory a0 = this.factory;
        a0.paintTree(a, this);
    }
    
    public void draw(java.awt.Graphics a, java.awt.Point a0)
    {
        int i = a0.x;
        int i0 = a0.y;
        this.plantTree(i, i0);
        this.draw(a);
    }
    
    public void drawPartial(java.awt.Graphics a)
    {
        java.awt.Color a0 = a.getColor();
        java.awt.Point a1 = new java.awt.Point(0, 0);
        java.awt.Point a2 = this.pos;
        int i = a2.x;
        a1.x = i;
        java.awt.Point a3 = this.pos;
        int i0 = a3.y;
        a1.y = i0;
        java.awt.Color a4 = this.mainHighlightColor;
        if(a4 != null)
        {
            java.awt.Color a5 = this.mainHighlightColor;
            a.setColor(a5);
            java.awt.Point a6 = this.pos;
            int i1 = a6.x;
            int i2 = i1 - 2;
            java.awt.Point a7 = this.pos;
            int i3 = a7.y;
            int i4 = i3 - 2;
            int i5 = this.getWidth();
            int i6 = i5 + 6;
            int i7 = this.height;
            int i8 = i7 + 6;
            a.fill3DRect(i2, i4, i6, i8, false);
        }
        int i9 = this.nodesAreVisible()?1:0;
        java.util.Vector a8 = this.elements;
        int i10 = a8.size();
        label0: {
            if(i10 <= 0)
            {
                break label0;
            }
            if(i9 == 0)
            {
                break label0;
            }
            int i11 = this.drawBorder?1:0;
            if(i11 != 0)
            {
                java.awt.Color a9 = this.borderColor;
                a.setColor(a9);
                java.awt.Point a10 = this.pos;
                int i12 = a10.x;
                java.awt.Point a11 = this.pos;
                int i13 = a11.y;
                int i14 = this.getWidth();
                int i15 = i14 + 1;
                int i16 = this.height;
                int i17 = i16 + 1;
                a.draw3DRect(i12, i13, i15, i17, false);
            }
        }
        label1: {
            if(i9 == 0)
            {
                break label1;
            }
            int i18 = 0;
            while(true)
            {
                java.util.Vector a12 = this.elements;
                int i19 = a12.size();
                if(i18 >= i19)
                {
                    break;
                }
                else
                {
                    java.util.Vector a13 = this.elements;
                    Object a14 = a13.elementAt(i18);
                    com.cim.AIA.Node dummy = (com.cim.AIA.Node)a14;
                    com.cim.AIA.Node a15 = (com.cim.AIA.Node)a14;
                    a15.draw(a, a1);
                    int i20 = a1.x;
                    int i21 = a15.getWidth();
                    int i22 = i20 + i21;
                    a1.x = i22;
                    int i23 = i18 + 1;
                    i18 = i23;
                }
            }
        }
        a.setColor(a0);
    }
    
    public com.cim.AIA.Node elementAt(int i)
    {
        java.util.Vector a = this.elements;
        Object a0 = a.elementAt(i);
        com.cim.AIA.Node dummy = (com.cim.AIA.Node)a0;
        com.cim.AIA.Node a1 = (com.cim.AIA.Node)a0;
        return a1;
    }
    
    public String elementsString()
    {
        String s = "";
        int i = 0;
        while(true)
        {
            java.util.Vector a = this.elements;
            int i0 = a.size();
            if(i >= i0)
            {
                return s;
            }
            else
            {
                java.util.Vector a0 = this.elements;
                Object a1 = a0.elementAt(i);
                com.cim.AIA.Node dummy = (com.cim.AIA.Node)a1;
                com.cim.AIA.Node a2 = (com.cim.AIA.Node)a1;
                StringBuilder a3 = new StringBuilder();
                StringBuilder a4 = a3.append(s);
                Object a5 = a2.getObject();
                String s0 = a5.toString();
                StringBuilder a6 = a4.append(s0);
                StringBuilder a7 = a6.append(" ");
                String s1 = a7.toString();
                int i1 = i + 1;
                s = s1;
                i = i1;
            }
        }
    }
    
    public boolean equals(com.cim.AIA.Selectable a)
    {
        int i = 0;
        int i0 = a instanceof com.cim.AIA.HierarchyTree?1:0;
        Object a0 = a;
        if(i0 == 0)
        {
            i = 0;
        }
        else
        {
            com.cim.AIA.HierarchyTree dummy = (com.cim.AIA.HierarchyTree)a0;
            com.cim.AIA.HierarchyTree a1 = (com.cim.AIA.HierarchyTree)a0;
            int i1 = com.cim.AIA.HierarchyTree.compare(this, a1)?1:0;
            i = i1;
        }
        return i != 0;
    }
    
    public boolean getDrawParentLine()
    {
        int i = this.drawParentLine?1:0;
        return i != 0;
    }
    
    public com.cim.AIA.Node getFirstElement()
    {
        com.cim.AIA.Node a = null;
        java.util.Vector a0 = this.elements;
        int i = a0.size();
        if(0 >= i)
        {
            a = null;
        }
        else
        {
            java.util.Vector a1 = this.elements;
            Object a2 = a1.elementAt(0);
            com.cim.AIA.Node dummy = (com.cim.AIA.Node)a2;
            com.cim.AIA.Node a3 = (com.cim.AIA.Node)a2;
            a = a3;
        }
        return a;
    }
    
    public int getFirstElementPosition()
    {
        java.util.Vector a = this.elements;
        int i = a.size();
        if(0 < i)
        {
        }
        return 0;
    }
    
    public int getHeight()
    {
        int i = this.height;
        return i;
    }
    
    public int getIdentifier()
    {
        int i = this.sequenceNumber;
        return i;
    }
    
    public com.cim.AIA.Selectable getItemAt(java.awt.Point a)
    {
        Object a0 = null;
        int i = 0;
        label1: {
            label0: {
                Object a1 = null;
                while(true)
                {
                    java.util.Vector a2 = this.elements;
                    int i0 = a2.size();
                    if(i >= i0)
                    {
                        break label0;
                    }
                    java.util.Vector a3 = this.elements;
                    Object a4 = a3.elementAt(i);
                    com.cim.AIA.Node dummy = (com.cim.AIA.Node)a4;
                    com.cim.AIA.Node a5 = (com.cim.AIA.Node)a4;
                    a1 = a5.getItemAt(a);
                    if(a1 == null)
                    {
                        int i1 = i + 1;
                        i = i1;
                        continue;
                    }
                    else
                    {
                        break;
                    }
                }
                a0 = a1;
                break label1;
            }
            int i2 = 0;
            while(true)
            {
                java.util.Vector a6 = this.children;
                int i3 = a6.size();
                label2: {
                    if(i2 < i3)
                    {
                        break label2;
                    }
                    a0 = null;
                    break;
                }
                java.util.Vector a7 = this.children;
                Object a8 = a7.elementAt(i2);
                com.cim.AIA.HierarchyTree dummy0 = (com.cim.AIA.HierarchyTree)a8;
                com.cim.AIA.HierarchyTree a9 = (com.cim.AIA.HierarchyTree)a8;
                Object a10 = a9.getItemAt(a);
                if(a10 == null)
                {
                    int i4 = i2 + 1;
                    i2 = i4;
                    continue;
                }
                a0 = a10;
                break;
            }
        }
        return (com.cim.AIA.Selectable)a0;
    }
    
    public com.cim.AIA.Node getLastElement()
    {
        int i = 0;
        while(true)
        {
            com.cim.AIA.Node a = null;
            java.util.Vector a0 = this.elements;
            int i0 = a0.size();
            label1: {
                label0: {
                    if(i < i0)
                    {
                        break label0;
                    }
                    a = null;
                    break label1;
                }
                java.util.Vector a1 = this.elements;
                Object a2 = a1.elementAt(i);
                com.cim.AIA.Node dummy = (com.cim.AIA.Node)a2;
                com.cim.AIA.Node a3 = (com.cim.AIA.Node)a2;
                java.util.Vector a4 = this.elements;
                int i1 = a4.size();
                int i2 = i1 - 1;
                if(i != i2)
                {
                    int i3 = i + 1;
                    i = i3;
                    continue;
                }
                a = a3;
            }
            return a;
        }
    }
    
    public int getLastElementPosition()
    {
        int i = 0;
        while(true)
        {
            int i0 = 0;
            java.util.Vector a = this.elements;
            int i1 = a.size();
            label1: {
                label0: {
                    if(i < i1)
                    {
                        break label0;
                    }
                    java.util.Vector a0 = this.elements;
                    int i2 = a0.size();
                    i0 = i2;
                    break label1;
                }
                java.util.Vector a1 = this.elements;
                int i3 = a1.size();
                int i4 = i3 - 1;
                if(i != i4)
                {
                    int i5 = i + 1;
                    i = i5;
                    continue;
                }
                i0 = i;
            }
            return i0;
        }
    }
    
    public com.cim.AIA.HierarchyTree getLeftChild()
    {
        com.cim.AIA.HierarchyTree a = null;
        java.util.Vector a0 = this.children;
        int i = a0.size();
        if(0 >= i)
        {
            a = null;
        }
        else
        {
            java.util.Vector a1 = this.children;
            Object a2 = a1.elementAt(0);
            com.cim.AIA.HierarchyTree dummy = (com.cim.AIA.HierarchyTree)a2;
            com.cim.AIA.HierarchyTree a3 = (com.cim.AIA.HierarchyTree)a2;
            a = a3;
        }
        return a;
    }
    
    public com.cim.AIA.HierarchyTree getLeftSibling()
    {
        com.cim.AIA.HierarchyTree a = null;
        com.cim.AIA.Tree a0 = this.getParent();
        com.cim.AIA.HierarchyTree dummy = (com.cim.AIA.HierarchyTree)a0;
        com.cim.AIA.HierarchyTree a1 = (com.cim.AIA.HierarchyTree)a0;
        label2: {
            com.cim.AIA.HierarchyTree a2 = null;
            label1: {
                label0: {
                    if(a1 == null)
                    {
                        break label0;
                    }
                    java.util.Vector a3 = a1.children;
                    int i = a3.size();
                    int i0 = -5;
                    int i1 = i;
                    while(true)
                    {
                        int i2 = 0;
                        int i3 = i0;
                        if(i1 < 0)
                        {
                            break;
                        }
                        else
                        {
                            i2 = i3;
                        }
                        java.util.Vector a4 = a1.children;
                        int i4 = i2;
                        int i5 = i1 - 1;
                        Object a5 = a4.elementAt(i5);
                        int i6 = i4;
                        com.cim.AIA.HierarchyTree dummy0 = (com.cim.AIA.HierarchyTree)a5;
                        a2 = (com.cim.AIA.HierarchyTree)a5;
                        int i7 = i6;
                        int i8 = i7;
                        int i9 = a2 != this?i8:i1;
                        int i10 = i9 - 1;
                        int i11 = i9;
                        if(i1 != i10)
                        {
                            int i12 = i11;
                            int i13 = i1 + -1;
                            i0 = i12;
                            i1 = i13;
                        }
                        else
                        {
                            break label1;
                        }
                    }
                }
                a = null;
                break label2;
            }
            a = a2;
        }
        return a;
    }
    
    public com.cim.AIA.Line getLine()
    {
        com.cim.AIA.Line a = this.line;
        com.cim.AIA.Line a0 = new com.cim.AIA.Line(0, 0, 0, 0);
        int i = a.equals(a0)?1:0;
        label1: {
            label0: {
                if(i != 0)
                {
                    break label0;
                }
                int i0 = this.recalculateLine?1:0;
                if(i0 == 0)
                {
                    break label1;
                }
            }
            java.awt.Point a1 = this.getParentConnectPoint();
            if(a1 != null)
            {
                com.cim.AIA.Line a2 = this.line;
                int i1 = a1.x;
                a2.setIntX1(i1);
                com.cim.AIA.Line a3 = this.line;
                int i2 = a1.y;
                int i3 = i2 + 1;
                a3.setIntY1(i3);
                com.cim.AIA.Line a4 = this.line;
                java.awt.Point a5 = this.pos;
                int i4 = a5.x;
                int i5 = this.getWidth();
                int i6 = i5 / 2;
                int i7 = i4 + i6;
                a4.setIntX2(i7);
                com.cim.AIA.Line a6 = this.line;
                java.awt.Point a7 = this.pos;
                int i8 = a7.y;
                a6.setIntY2(i8);
            }
            com.cim.AIA.Line a8 = this.line;
            java.awt.Color a9 = this.parentLineColor;
            a8.setColor(a9);
        }
        com.cim.AIA.Line a10 = this.line;
        return a10;
    }
    
    public com.cim.AIA.Node getMiddleElement()
    {
        int i = 0;
        while(true)
        {
            java.util.Vector a = this.elements;
            int i0 = a.size();
            label2: {
                com.cim.AIA.Node a0 = null;
                label1: {
                    label0: {
                        if(i < i0)
                        {
                            break label0;
                        }
                        a0 = null;
                        break label1;
                    }
                    java.util.Vector a1 = this.elements;
                    Object a2 = a1.elementAt(i);
                    com.cim.AIA.Node dummy = (com.cim.AIA.Node)a2;
                    com.cim.AIA.Node a3 = (com.cim.AIA.Node)a2;
                    if(i == 0)
                    {
                        break label2;
                    }
                    java.util.Vector a4 = this.elements;
                    int i1 = a4.size();
                    int i2 = i1 - 1;
                    if(i == i2)
                    {
                        break label2;
                    }
                    a0 = a3;
                }
                return a0;
            }
            int i3 = i + 1;
            i = i3;
        }
    }
    
    public int getMiddleElementPosition()
    {
        int i = 0;
        while(true)
        {
            java.util.Vector a = this.elements;
            int i0 = a.size();
            label2: {
                int i1 = 0;
                label1: {
                    label0: {
                        if(i < i0)
                        {
                            break label0;
                        }
                        i1 = 1;
                        break label1;
                    }
                    if(i == 0)
                    {
                        break label2;
                    }
                    java.util.Vector a0 = this.elements;
                    int i2 = a0.size();
                    int i3 = i2 - 1;
                    if(i == i3)
                    {
                        break label2;
                    }
                    i1 = i;
                }
                return i1;
            }
            int i4 = i + 1;
            i = i4;
        }
    }
    
    public com.cim.AIA.HierarchyTree getMiddleLeftChild()
    {
        int i = 0;
        while(true)
        {
            java.util.Vector a = this.children;
            int i0 = a.size();
            label2: {
                com.cim.AIA.HierarchyTree a0 = null;
                label1: {
                    label0: {
                        if(i < i0)
                        {
                            break label0;
                        }
                        a0 = null;
                        break label1;
                    }
                    java.util.Vector a1 = this.children;
                    Object a2 = a1.elementAt(i);
                    com.cim.AIA.HierarchyTree dummy = (com.cim.AIA.HierarchyTree)a2;
                    com.cim.AIA.HierarchyTree a3 = (com.cim.AIA.HierarchyTree)a2;
                    if(i == 0)
                    {
                        break label2;
                    }
                    java.util.Vector a4 = this.children;
                    int i1 = a4.size();
                    int i2 = i1 - 1;
                    if(i == i2)
                    {
                        break label2;
                    }
                    a0 = a3;
                }
                return a0;
            }
            int i3 = i + 1;
            i = i3;
        }
    }
    
    public com.cim.AIA.HierarchyTree getMiddleRightChild()
    {
        int i = 0;
        while(true)
        {
            java.util.Vector a = this.children;
            int i0 = a.size();
            label2: {
                com.cim.AIA.HierarchyTree a0 = null;
                label1: {
                    label0: {
                        if(i < i0)
                        {
                            break label0;
                        }
                        a0 = null;
                        break label1;
                    }
                    java.util.Vector a1 = this.children;
                    Object a2 = a1.elementAt(i);
                    com.cim.AIA.HierarchyTree dummy = (com.cim.AIA.HierarchyTree)a2;
                    com.cim.AIA.HierarchyTree a3 = (com.cim.AIA.HierarchyTree)a2;
                    if(i == 0)
                    {
                        break label2;
                    }
                    java.util.Vector a4 = this.children;
                    int i1 = a4.size();
                    int i2 = i1 - 1;
                    if(i == i2)
                    {
                        break label2;
                    }
                    if(i == 1)
                    {
                        break label2;
                    }
                    a0 = a3;
                }
                return a0;
            }
            int i3 = i + 1;
            i = i3;
        }
    }
    
    public com.cim.AIA.Node getNodeAt(int i)
    {
        com.cim.AIA.Node a = null;
        label2: {
            int i0 = 0;
            label1: {
                label0: {
                    i0 = i + -1;
                    if(i0 < 0)
                    {
                        break label0;
                    }
                    java.util.Vector a0 = this.elements;
                    int i1 = a0.size();
                    if(i0 < i1)
                    {
                        break label1;
                    }
                }
                a = null;
                break label2;
            }
            java.util.Vector a1 = this.elements;
            Object a2 = a1.elementAt(i0);
            com.cim.AIA.Node dummy = (com.cim.AIA.Node)a2;
            com.cim.AIA.Node a3 = (com.cim.AIA.Node)a2;
            a = a3;
        }
        return a;
    }
    
    public java.util.Vector getNodes()
    {
        java.util.Vector a = new java.util.Vector();
        int i = 0;
        while(true)
        {
            java.util.Vector a0 = this.elements;
            int i0 = a0.size();
            if(i >= i0)
            {
                return a;
            }
            else
            {
                java.util.Vector a1 = this.elements;
                Object a2 = a1.elementAt(i);
                a.addElement(a2);
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    public java.awt.Point getParentConnectPoint()
    {
        java.awt.Point a = null;
        com.cim.AIA.Tree a0 = this.parentTree;
        label2: {
            label1: {
                label0: {
                    if(a0 == null)
                    {
                        break label0;
                    }
                    java.util.Vector a1 = this.elements;
                    int i = a1.size();
                    if(i != 0)
                    {
                        break label1;
                    }
                }
                a = null;
                break label2;
            }
            com.cim.AIA.Tree a2 = this.parentTree;
            com.cim.AIA.HierarchyTree dummy = (com.cim.AIA.HierarchyTree)a2;
            com.cim.AIA.HierarchyTree a3 = (com.cim.AIA.HierarchyTree)a2;
            java.awt.Point a4 = a3.pos;
            int i0 = a4.x;
            int i1 = a3.getWidth();
            int i2 = i1 / 2;
            int i3 = i0 + i2;
            java.awt.Point a5 = a3.pos;
            int i4 = a5.y;
            int i5 = a3.getHeight();
            int i6 = i4 + i5;
            java.awt.Point a6 = new java.awt.Point(i3, i6);
            java.util.Vector a7 = a3.children;
            int i7 = a7.size();
            label3: {
                if(i7 != 1)
                {
                    break label3;
                }
                a = a6;
                break label2;
            }
            int i8 = 0;
            while(true)
            {
                java.util.Vector a8 = a3.children;
                int i9 = a8.size();
                if(i8 >= i9)
                {
                    break;
                }
                java.util.Vector a9 = a3.children;
                Object a10 = a9.elementAt(i8);
                com.cim.AIA.HierarchyTree dummy0 = (com.cim.AIA.HierarchyTree)a10;
                com.cim.AIA.HierarchyTree a11 = (com.cim.AIA.HierarchyTree)a10;
                if(a11 != this)
                {
                    int i10 = i8 + 1;
                    i8 = i10;
                    continue;
                }
                java.awt.Point a12 = a3.pos;
                int i11 = a12.x;
                int i12 = a3.getWidth();
                int i13 = i12 * i8;
                java.util.Vector a13 = a3.children;
                int i14 = a13.size();
                int i15 = i14 - 1;
                int i16 = i13 / i15;
                int i17 = i11 + i16;
                a6.x = i17;
                break;
            }
            a = a6;
        }
        return a;
    }
    
    public java.awt.Color getParentLineColor()
    {
        java.awt.Color a = this.parentLineColor;
        return a;
    }
    
    public java.awt.Point getPosition()
    {
        java.awt.Point a = this.pos;
        return a;
    }
    
    public int getPositionOfNode(com.cim.AIA.Node a)
    {
        int i = 0;
        while(true)
        {
            int i0 = 0;
            java.util.Vector a0 = this.elements;
            int i1 = a0.size();
            label1: {
                label0: {
                    if(i < i1)
                    {
                        break label0;
                    }
                    i0 = 0;
                    break label1;
                }
                java.util.Vector a1 = this.elements;
                Object a2 = a1.elementAt(i);
                if(a != a2)
                {
                    int i2 = i + 1;
                    i = i2;
                    continue;
                }
                i0 = i;
            }
            return i0;
        }
    }
    
    public java.awt.Rectangle getRectangle()
    {
        java.awt.Point a = this.pos;
        int i = a.x;
        java.awt.Point a0 = this.pos;
        int i0 = a0.y;
        java.awt.Point a1 = new java.awt.Point(i, i0);
        java.awt.Point a2 = this.pos;
        int i1 = a2.x;
        int i2 = this.getWidth();
        int i3 = i1 + i2;
        java.awt.Point a3 = this.pos;
        int i4 = a3.y;
        int i5 = this.height;
        int i6 = i4 + i5;
        java.awt.Point a4 = new java.awt.Point(i3, i6);
        java.awt.Point a5 = this.pos;
        int i7 = a5.x;
        java.awt.Point a6 = this.pos;
        int i8 = a6.y;
        java.awt.Point a7 = this.pos;
        int i9 = a7.x;
        int i10 = this.getWidth();
        int i11 = i9 + i10;
        java.awt.Point a8 = this.pos;
        int i12 = a8.y;
        int i13 = this.height;
        int i14 = i12 + i13;
        java.awt.Rectangle a9 = this.getTotalDimensions(i7, i8, i11, i14);
        int i15 = a9.width;
        int i16 = a9.x;
        int i17 = i15 - i16;
        a9.width = i17;
        int i18 = a9.height;
        int i19 = a9.y;
        int i20 = i18 - i19;
        a9.height = i20;
        return a9;
    }
    
    public com.cim.AIA.HierarchyTree getRightChild()
    {
        int i = 0;
        while(true)
        {
            com.cim.AIA.HierarchyTree a = null;
            java.util.Vector a0 = this.children;
            int i0 = a0.size();
            label1: {
                label0: {
                    if(i < i0)
                    {
                        break label0;
                    }
                    a = null;
                    break label1;
                }
                java.util.Vector a1 = this.children;
                Object a2 = a1.elementAt(i);
                com.cim.AIA.HierarchyTree dummy = (com.cim.AIA.HierarchyTree)a2;
                com.cim.AIA.HierarchyTree a3 = (com.cim.AIA.HierarchyTree)a2;
                java.util.Vector a4 = this.children;
                int i1 = a4.size();
                int i2 = i1 - 1;
                if(i != i2)
                {
                    int i3 = i + 1;
                    i = i3;
                    continue;
                }
                a = a3;
            }
            return a;
        }
    }
    
    public com.cim.AIA.HierarchyTree getRightSibling()
    {
        com.cim.AIA.HierarchyTree a = null;
        com.cim.AIA.Tree a0 = this.getParent();
        com.cim.AIA.HierarchyTree dummy = (com.cim.AIA.HierarchyTree)a0;
        com.cim.AIA.HierarchyTree a1 = (com.cim.AIA.HierarchyTree)a0;
        label2: {
            com.cim.AIA.HierarchyTree a2 = null;
            label1: {
                label0: {
                    if(a1 == null)
                    {
                        break label0;
                    }
                    int i = -5;
                    int i0 = 0;
                    while(true)
                    {
                        int i1 = 0;
                        java.util.Vector a3 = a1.children;
                        int i2 = i;
                        int i3 = a3.size();
                        int i4 = i2;
                        int i5 = i4;
                        if(i0 >= i3)
                        {
                            break;
                        }
                        else
                        {
                            i1 = i5;
                        }
                        java.util.Vector a4 = a1.children;
                        int i6 = i1;
                        Object a5 = a4.elementAt(i0);
                        int i7 = i6;
                        com.cim.AIA.HierarchyTree dummy0 = (com.cim.AIA.HierarchyTree)a5;
                        a2 = (com.cim.AIA.HierarchyTree)a5;
                        int i8 = i7;
                        int i9 = i8;
                        int i10 = a2 != this?i9:i0;
                        int i11 = i10 + 1;
                        int i12 = i10;
                        if(i0 != i11)
                        {
                            int i13 = i12;
                            int i14 = i0 + 1;
                            i = i13;
                            i0 = i14;
                        }
                        else
                        {
                            break label1;
                        }
                    }
                }
                a = null;
                break label2;
            }
            a = a2;
        }
        return a;
    }
    
    public com.cim.AIA.HierarchyTree getRoot()
    {
        com.cim.AIA.HierarchyTree a = this;
        while(true)
        {
            com.cim.AIA.Tree a0 = a.getParent();
            if(a0 == null)
            {
                return a;
            }
            else
            {
                com.cim.AIA.Tree a1 = a.getParent();
                com.cim.AIA.HierarchyTree dummy = (com.cim.AIA.HierarchyTree)a1;
                com.cim.AIA.HierarchyTree a2 = (com.cim.AIA.HierarchyTree)a1;
                a = a2;
            }
        }
    }
    
    protected java.awt.Rectangle getTotalDimensions(int i, int i0, int i1, int i2)
    {
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        java.awt.Point a = this.pos;
        int i7 = a.x;
        if(i7 >= i)
        {
            i3 = i;
        }
        else
        {
            java.awt.Point a0 = this.pos;
            int i8 = a0.x;
            i3 = i8;
        }
        java.awt.Point a1 = this.pos;
        int i9 = a1.y;
        if(i9 >= i0)
        {
            i4 = i0;
        }
        else
        {
            java.awt.Point a2 = this.pos;
            int i10 = a2.y;
            i4 = i10;
        }
        java.awt.Point a3 = this.pos;
        int i11 = a3.x;
        int i12 = this.getWidth();
        int i13 = i11 + i12;
        if(i13 <= i1)
        {
            i5 = i1;
        }
        else
        {
            java.awt.Point a4 = this.pos;
            int i14 = a4.x;
            int i15 = this.getWidth();
            int i16 = i14 + i15;
            i5 = i16;
        }
        java.awt.Point a5 = this.pos;
        int i17 = a5.y;
        int i18 = this.height;
        int i19 = i17 + i18;
        if(i19 <= i2)
        {
            i6 = i2;
        }
        else
        {
            java.awt.Point a6 = this.pos;
            int i20 = a6.y;
            int i21 = this.height;
            int i22 = i20 + i21;
            i6 = i22;
        }
        int i23 = i3;
        int i24 = i4;
        int i25 = i5;
        int i26 = i6;
        int i27 = 0;
        while(true)
        {
            java.util.Vector a7 = this.children;
            int i28 = a7.size();
            if(i27 >= i28)
            {
                java.awt.Rectangle a8 = new java.awt.Rectangle(i23, i24, i25, i26);
                return a8;
            }
            else
            {
                java.util.Vector a9 = this.children;
                Object a10 = a9.elementAt(i27);
                com.cim.AIA.HierarchyTree dummy = (com.cim.AIA.HierarchyTree)a10;
                com.cim.AIA.HierarchyTree a11 = (com.cim.AIA.HierarchyTree)a10;
                java.awt.Rectangle a12 = a11.getTotalDimensions(i23, i24, i25, i26);
                int i29 = a12.x;
                int i30 = a12.y;
                int i31 = a12.width;
                int i32 = a12.height;
                int i33 = i27 + 1;
                i23 = i29;
                i24 = i30;
                i25 = i31;
                i26 = i32;
                i27 = i33;
            }
        }
    }
    
    public int getTotalHeight()
    {
        int i = 0;
        java.util.Vector a = this.children;
        int i0 = a.size();
        if(i0 <= 0)
        {
            int i1 = this.height;
            i = i1;
        }
        else
        {
            int i2 = this.height;
            int i3 = this.parentDistance;
            int i4 = i2 + i3;
            int i5 = this.border;
            int i6 = i4 + i5;
            java.util.Vector a0 = this.children;
            Object a1 = a0.elementAt(0);
            com.cim.AIA.HierarchyTree dummy = (com.cim.AIA.HierarchyTree)a1;
            com.cim.AIA.HierarchyTree a2 = (com.cim.AIA.HierarchyTree)a1;
            int i7 = a2.getTotalHeight();
            int i8 = i6 + i7;
            i = i8;
        }
        return i;
    }
    
    public int getWidth()
    {
        int i = 0;
        int i0 = 0;
        while(true)
        {
            java.util.Vector a = this.elements;
            int i1 = a.size();
            if(i0 >= i1)
            {
                return i;
            }
            else
            {
                com.cim.AIA.Node a0 = this.elementAt(i0);
                int i2 = a0.getWidth();
                int i3 = i + i2;
                int i4 = i0 + 1;
                i = i3;
                i0 = i4;
            }
        }
    }
    
    public synchronized int getWidthOfNode(int i)
    {
        int i0 = 0;
        label2: {
            label1: {
                label0: {
                    if(i < 0)
                    {
                        break label0;
                    }
                    java.util.Vector a = this.elements;
                    int i1 = a.size();
                    if(i < i1)
                    {
                        break label1;
                    }
                }
                i0 = 0;
                break label2;
            }
            java.util.Vector a0 = this.elements;
            Object a1 = a0.elementAt(i);
            com.cim.AIA.Node dummy = (com.cim.AIA.Node)a1;
            com.cim.AIA.Node a2 = (com.cim.AIA.Node)a1;
            int i2 = a2.getWidth();
            i0 = i2;
        }
        return i0;
    }
    
    public int getX()
    {
        java.awt.Point a = this.pos;
        int i = a.x;
        return i;
    }
    
    public synchronized int getX(int i)
    {
        java.awt.Point a = this.pos;
        int i0 = a.x;
        int i1 = i0;
        int i2 = 0;
        while(true)
        {
            int i3 = 0;
            java.util.Vector a0 = this.elements;
            int i4 = a0.size();
            label1: {
                label0: {
                    if(i2 < i4)
                    {
                        break label0;
                    }
                    i3 = 0;
                    break label1;
                }
                java.util.Vector a1 = this.elements;
                Object a2 = a1.elementAt(i2);
                com.cim.AIA.Node dummy = (com.cim.AIA.Node)a2;
                com.cim.AIA.Node a3 = (com.cim.AIA.Node)a2;
                if(i2 != i)
                {
                    int i5 = a3.getWidth();
                    int i6 = i1 + i5;
                    int i7 = i2 + 1;
                    i1 = i6;
                    i2 = i7;
                    continue;
                }
                i3 = i1;
            }
            return i3;
        }
    }
    
    public synchronized int getX(com.cim.AIA.Node a)
    {
        java.awt.Point a0 = this.pos;
        int i = a0.x;
        int i0 = i;
        int i1 = 0;
        while(true)
        {
            int i2 = 0;
            java.util.Vector a1 = this.elements;
            int i3 = a1.size();
            label1: {
                label0: {
                    if(i1 < i3)
                    {
                        break label0;
                    }
                    i2 = 0;
                    break label1;
                }
                java.util.Vector a2 = this.elements;
                Object a3 = a2.elementAt(i1);
                com.cim.AIA.Node dummy = (com.cim.AIA.Node)a3;
                com.cim.AIA.Node a4 = (com.cim.AIA.Node)a3;
                if(a4 != a)
                {
                    int i4 = a4.getWidth();
                    int i5 = i0 + i4;
                    int i6 = i1 + 1;
                    i0 = i5;
                    i1 = i6;
                    continue;
                }
                i2 = i0;
            }
            return i2;
        }
    }
    
    public int getXPos()
    {
        java.awt.Point a = this.pos;
        int i = a.x;
        return i;
    }
    
    public int getY()
    {
        java.awt.Point a = this.pos;
        int i = a.y;
        return i;
    }
    
    public int getY(com.cim.AIA.Node a)
    {
        java.awt.Point a0 = this.pos;
        int i = a0.y;
        int i0 = 0;
        while(true)
        {
            int i1 = 0;
            java.util.Vector a1 = this.elements;
            int i2 = a1.size();
            label1: {
                label0: {
                    if(i0 < i2)
                    {
                        break label0;
                    }
                    i1 = 0;
                    break label1;
                }
                java.util.Vector a2 = this.elements;
                Object a3 = a2.elementAt(i0);
                com.cim.AIA.Node dummy = (com.cim.AIA.Node)a3;
                com.cim.AIA.Node a4 = (com.cim.AIA.Node)a3;
                if(a4 != a)
                {
                    int i3 = i0 + 1;
                    i0 = i3;
                    continue;
                }
                i1 = i;
            }
            return i1;
        }
    }
    
    public int getYPos()
    {
        java.awt.Point a = this.pos;
        int i = a.y;
        return i;
    }
    
    public void highlight()
    {
        int i = 0;
        while(true)
        {
            java.util.Vector a = this.elements;
            int i0 = a.size();
            if(i >= i0)
            {
                break;
            }
            else
            {
                java.util.Vector a0 = this.elements;
                Object a1 = a0.elementAt(i);
                com.cim.AIA.Node dummy = (com.cim.AIA.Node)a1;
                com.cim.AIA.Node a2 = (com.cim.AIA.Node)a1;
                a2.highlight();
                int i1 = i + 1;
                i = i1;
            }
        }
        int i2 = 0;
        while(true)
        {
            java.util.Vector a3 = this.children;
            int i3 = a3.size();
            if(i2 >= i3)
            {
                return;
            }
            else
            {
                java.util.Vector a4 = this.children;
                Object a5 = a4.elementAt(i2);
                com.cim.AIA.HierarchyTree dummy0 = (com.cim.AIA.HierarchyTree)a5;
                com.cim.AIA.HierarchyTree a6 = (com.cim.AIA.HierarchyTree)a5;
                a6.highlight();
                int i4 = i2 + 1;
                i2 = i4;
            }
        }
    }
    
    protected void initialiseLine()
    {
        com.cim.AIA.Line a = this.line;
        a.showArrowHead(true);
        com.cim.AIA.Line a0 = this.line;
        a0.setDistanceFromStartForArrowHead(-3);
        com.cim.AIA.Line a1 = this.line;
        java.awt.Color a2 = this.parentLineColor;
        a1.setColor(a2);
    }
    
    public boolean nodesAreVisible()
    {
        int i = 0;
        int i0 = 0;
        while(true)
        {
            int i1 = 0;
            java.util.Vector a = this.elements;
            int i2 = i;
            int i3 = a.size();
            int i4 = i2;
            int i5 = i4;
            if(i0 >= i3)
            {
                return i4 != 0;
            }
            else
            {
                i1 = i5;
            }
            java.util.Vector a0 = this.elements;
            int i6 = i1;
            Object a1 = a0.elementAt(i0);
            int i7 = i6;
            com.cim.AIA.Node dummy = (com.cim.AIA.Node)a1;
            com.cim.AIA.Node a2 = (com.cim.AIA.Node)a1;
            int i8 = i7;
            int i9 = a2.getIsVisible()?1:0;
            int i10 = i8;
            int i11 = i10;
            int i12 = i9 == 0?i11:1;
            int i13 = i0 + 1;
            i = i12;
            i0 = i13;
        }
    }
    
    public int numberOfElements()
    {
        java.util.Vector a = this.elements;
        int i = a.size();
        return i;
    }
    
    public void plantTree(int i, int i0)
    {
        com.cim.AIA.WTFactory a = this.factory;
        a.layout(this);
        com.cim.AIA.WTFactory a0 = this.factory;
        java.awt.Point a1 = a0.plantTree(this, i, i0);
    }
    
    public void recalculateLine(boolean b)
    {
        this.recalculateLine = b;
    }
    
    public void remove(com.cim.AIA.Node a)
    {
        java.util.Vector a0 = this.elements;
        int i = a0.removeElement((Object)a)?1:0;
    }
    
    public void setBorderColor(java.awt.Color a)
    {
        this.borderColor = a;
    }
    
    public void setDrawBorder(boolean b)
    {
        this.drawBorder = b;
    }
    
    public void setDrawParentLine(boolean b)
    {
        this.drawParentLine = b;
    }
    
    public void setHighlight(java.awt.Color a)
    {
        this.mainHighlightColor = a;
    }
    
    public void setParentLineColor(java.awt.Color a)
    {
        this.parentLineColor = a;
    }
    
    public void setPosition(java.awt.Point a)
    {
        java.awt.Point a0 = this.pos;
        int i = a.x;
        a0.x = i;
        java.awt.Point a1 = this.pos;
        int i0 = a.y;
        a1.y = i0;
    }
    
    public int setX()
    {
        java.awt.Point a = this.pos;
        int i = a.x;
        return i;
    }
    
    public void setX(int i)
    {
        java.awt.Point a = this.pos;
        a.x = i;
    }
    
    public void setY(int i)
    {
        java.awt.Point a = this.pos;
        a.y = i;
    }
    
    public void setYEntire(int i)
    {
        java.awt.Point a = this.pos;
        a.y = i;
        int i0 = 0;
        while(true)
        {
            java.util.Vector a0 = this.children;
            int i1 = a0.size();
            if(i0 >= i1)
            {
                return;
            }
            else
            {
                java.util.Vector a1 = this.children;
                Object a2 = a1.elementAt(i0);
                com.cim.AIA.HierarchyTree dummy = (com.cim.AIA.HierarchyTree)a2;
                com.cim.AIA.HierarchyTree a3 = (com.cim.AIA.HierarchyTree)a2;
                int i2 = this.height;
                int i3 = i + i2;
                int i4 = this.border;
                int i5 = i3 + i4;
                int i6 = this.parentDistance;
                int i7 = i5 + i6;
                a3.setY(i7);
                int i8 = i0 + 1;
                i0 = i8;
            }
        }
    }
    
    public void shiftEntire(int i, int i0)
    {
        this.shiftX(i);
        this.shiftY(i0);
    }
    
    public void shiftX(int i)
    {
        java.awt.Point a = this.pos;
        java.awt.Point a0 = this.pos;
        int i0 = a0.x;
        int i1 = i0 + i;
        a.x = i1;
        int i2 = 0;
        while(true)
        {
            java.util.Vector a1 = this.children;
            int i3 = a1.size();
            if(i2 >= i3)
            {
                return;
            }
            else
            {
                java.util.Vector a2 = this.children;
                Object a3 = a2.elementAt(i2);
                com.cim.AIA.HierarchyTree dummy = (com.cim.AIA.HierarchyTree)a3;
                com.cim.AIA.HierarchyTree a4 = (com.cim.AIA.HierarchyTree)a3;
                a4.shiftX(i);
                int i4 = i2 + 1;
                i2 = i4;
            }
        }
    }
    
    public void shiftY(int i)
    {
        java.awt.Point a = this.pos;
        java.awt.Point a0 = this.pos;
        int i0 = a0.y;
        int i1 = i0 + i;
        a.y = i1;
        int i2 = 0;
        while(true)
        {
            java.util.Vector a1 = this.children;
            int i3 = a1.size();
            if(i2 >= i3)
            {
                return;
            }
            else
            {
                java.util.Vector a2 = this.children;
                Object a3 = a2.elementAt(i2);
                com.cim.AIA.HierarchyTree dummy = (com.cim.AIA.HierarchyTree)a3;
                com.cim.AIA.HierarchyTree a4 = (com.cim.AIA.HierarchyTree)a3;
                a4.shiftY(i);
                int i4 = i2 + 1;
                i2 = i4;
            }
        }
    }
    
    public void unHighlight()
    {
        int i = 0;
        while(true)
        {
            java.util.Vector a = this.elements;
            int i0 = a.size();
            if(i >= i0)
            {
                break;
            }
            else
            {
                java.util.Vector a0 = this.elements;
                Object a1 = a0.elementAt(i);
                com.cim.AIA.Node dummy = (com.cim.AIA.Node)a1;
                com.cim.AIA.Node a2 = (com.cim.AIA.Node)a1;
                a2.unHighlight();
                int i1 = i + 1;
                i = i1;
            }
        }
        int i2 = 0;
        while(true)
        {
            java.util.Vector a3 = this.children;
            int i3 = a3.size();
            if(i2 >= i3)
            {
                return;
            }
            else
            {
                java.util.Vector a4 = this.children;
                Object a5 = a4.elementAt(i2);
                com.cim.AIA.HierarchyTree dummy0 = (com.cim.AIA.HierarchyTree)a5;
                com.cim.AIA.HierarchyTree a6 = (com.cim.AIA.HierarchyTree)a5;
                a6.unHighlight();
                int i4 = i2 + 1;
                i2 = i4;
            }
        }
    }
    
    static
    {
        java.awt.Color a = java.awt.Color.black;
        com.cim.AIA.HierarchyTree.BORDER_COLOR = a;
        java.awt.Color a0 = java.awt.Color.black;
        com.cim.AIA.HierarchyTree.PARENT_LINE_COLOR = a0;
        com.cim.AIA.HierarchyTree.metrics = null;
    }
}