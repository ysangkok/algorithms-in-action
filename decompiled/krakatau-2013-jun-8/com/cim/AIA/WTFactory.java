package com.cim.AIA;

public class WTFactory extends Thread {
    final protected static int FIXED_FONT_HEIGHT = 10;
    final protected static int FIXED_FONT_WIDTH = 30;
    final protected static int FIXED_FONT_ASCENT = 3;
    protected int leftBound;
    
    public WTFactory()
    {
        super();
        this.leftBound = 0;
    }
    
    protected void attachParent(com.cim.AIA.HierarchyTree a, int i)
    {
        int i0 = a.border;
        int i1 = a.parentDistance;
        int i2 = i0 + i1;
        int i3 = a.getWidth();
        int i4 = i - i3;
        int i5 = i4 / 2;
        int i6 = a.border;
        int i7 = i5 - i6;
        int i8 = a.getWidth();
        int i9 = i7 + i8;
        int i10 = a.border;
        int i11 = 2 * i10;
        int i12 = i9 + i11;
        int i13 = i12 - i;
        com.cim.AIA.Tree a0 = a.getChild(0);
        com.cim.AIA.HierarchyTree dummy = (com.cim.AIA.HierarchyTree)a0;
        com.cim.AIA.HierarchyTree a1 = (com.cim.AIA.HierarchyTree)a0;
        java.awt.Point a2 = a1.offset;
        int i14 = a.height;
        int i15 = i2 + i14;
        a2.y = i15;
        com.cim.AIA.Tree a3 = a.getChild(0);
        com.cim.AIA.HierarchyTree dummy0 = (com.cim.AIA.HierarchyTree)a3;
        com.cim.AIA.HierarchyTree a4 = (com.cim.AIA.HierarchyTree)a3;
        java.awt.Point a5 = a4.offset;
        a5.x = i13;
        com.cim.AIA.Polygon a6 = a.contour;
        int i16 = a.height;
        com.cim.AIA.Polygon a7 = a.contour;
        com.cim.AIA.PolyLine a8 = a7.upper_head;
        com.cim.AIA.PolyLine a9 = new com.cim.AIA.PolyLine(i13, i2, a8);
        com.cim.AIA.PolyLine a10 = new com.cim.AIA.PolyLine(0, i16, a9);
        a6.upper_head = a10;
        com.cim.AIA.Polygon a11 = a.contour;
        int i17 = a.height;
        com.cim.AIA.Polygon a12 = a.contour;
        com.cim.AIA.PolyLine a13 = a12.lower_head;
        com.cim.AIA.PolyLine a14 = new com.cim.AIA.PolyLine(i7, i2, a13);
        com.cim.AIA.PolyLine a15 = new com.cim.AIA.PolyLine(0, i17, a14);
        a11.lower_head = a15;
    }
    
    protected com.cim.AIA.PolyLine bridge(com.cim.AIA.PolyLine a, int i, int i0, com.cim.AIA.PolyLine a0, int i1, int i2)
    {
        int i3 = 0;
        int i4 = a0.dy;
        int i5 = i2 + i4;
        int i6 = i5 - i0;
        int i7 = a0.dy;
        if(i7 != 0)
        {
            int i8 = a0.dx;
            int i9 = i6 * i8;
            int i10 = a0.dy;
            int i11 = i9 / i10;
            i3 = i11;
        }
        else
        {
            int i12 = a0.dx;
            i3 = i12;
        }
        com.cim.AIA.PolyLine a1 = a0.link;
        com.cim.AIA.PolyLine a2 = new com.cim.AIA.PolyLine(i3, i6, a1);
        int i13 = a0.dx;
        int i14 = i1 + i13;
        int i15 = i14 - i3;
        int i16 = i15 - i;
        com.cim.AIA.PolyLine a3 = new com.cim.AIA.PolyLine(i16, 0, a2);
        a.link = a3;
        return a2;
    }
    
    protected int join(com.cim.AIA.HierarchyTree a)
    {
        com.cim.AIA.Tree a0 = a.getChild(0);
        com.cim.AIA.HierarchyTree dummy = (com.cim.AIA.HierarchyTree)a0;
        com.cim.AIA.HierarchyTree a1 = (com.cim.AIA.HierarchyTree)a0;
        com.cim.AIA.Polygon a2 = a1.contour;
        a.contour = a2;
        int i = a1.getWidth();
        int i0 = a1.border;
        int i1 = 2 * i0;
        int i2 = i + i1;
        int i3 = i2;
        int i4 = i2;
        int i5 = 1;
        while(true)
        {
            java.util.Vector a3 = a.children;
            int i6 = a3.size();
            if(i5 >= i6)
            {
                return i4;
            }
            else
            {
                com.cim.AIA.Tree a4 = a.getChild(i5);
                com.cim.AIA.HierarchyTree dummy0 = (com.cim.AIA.HierarchyTree)a4;
                com.cim.AIA.HierarchyTree a5 = (com.cim.AIA.HierarchyTree)a4;
                com.cim.AIA.Polygon a6 = a.contour;
                com.cim.AIA.Polygon a7 = a5.contour;
                int i7 = this.merge(a6, a7);
                java.awt.Point a8 = a5.offset;
                int i8 = i7 + i3;
                a8.x = i8;
                java.awt.Point a9 = a5.offset;
                a9.y = 0;
                int i9 = a5.getWidth();
                int i10 = a5.border;
                int i11 = 2 * i10;
                int i12 = i9 + i11;
                int i13 = i7 + i12;
                int i14 = i4 + i13;
                int i15 = i5 + 1;
                i3 = i12;
                i4 = i14;
                i5 = i15;
            }
        }
    }
    
    public void layout(com.cim.AIA.HierarchyTree a)
    {
        label1: {
            label0: {
                if(a != null)
                {
                    break label0;
                }
                break label1;
            }
            java.util.Vector a0 = a.children;
            int i = a0.size();
            label3: {
                label2: {
                    if(i > 0)
                    {
                        break label2;
                    }
                    this.layoutLeaf(a);
                    break label3;
                }
                int i0 = 0;
                while(true)
                {
                    java.util.Vector a1 = a.children;
                    int i1 = a1.size();
                    if(i0 >= i1)
                    {
                        break;
                    }
                    else
                    {
                        com.cim.AIA.Tree a2 = a.getChild(i0);
                        com.cim.AIA.HierarchyTree dummy = (com.cim.AIA.HierarchyTree)a2;
                        com.cim.AIA.HierarchyTree a3 = (com.cim.AIA.HierarchyTree)a2;
                        this.layout(a3);
                        int i2 = i0 + 1;
                        i0 = i2;
                    }
                }
                int i3 = this.join(a);
                this.attachParent(a, i3);
            }
        }
    }
    
    protected void layoutLeaf(com.cim.AIA.HierarchyTree a)
    {
        com.cim.AIA.Polygon a0 = a.contour;
        int i = a.getWidth();
        int i0 = a.border;
        int i1 = 2 * i0;
        int i2 = i + i1;
        com.cim.AIA.PolyLine a1 = new com.cim.AIA.PolyLine(i2, 0, (com.cim.AIA.PolyLine)null);
        a0.upper_tail = a1;
        com.cim.AIA.Polygon a2 = a.contour;
        com.cim.AIA.Polygon a3 = a.contour;
        com.cim.AIA.PolyLine a4 = a3.upper_tail;
        a2.upper_head = a4;
        com.cim.AIA.Polygon a5 = a.contour;
        int i3 = a.height;
        int i4 = 0 - i3;
        int i5 = a.border;
        int i6 = 2 * i5;
        int i7 = i4 - i6;
        com.cim.AIA.PolyLine a6 = new com.cim.AIA.PolyLine(0, i7, (com.cim.AIA.PolyLine)null);
        a5.lower_tail = a6;
        com.cim.AIA.Polygon a7 = a.contour;
        int i8 = a.getWidth();
        int i9 = a.border;
        int i10 = 2 * i9;
        int i11 = i8 + i10;
        com.cim.AIA.Polygon a8 = a.contour;
        com.cim.AIA.PolyLine a9 = a8.lower_tail;
        com.cim.AIA.PolyLine a10 = new com.cim.AIA.PolyLine(i11, 0, a9);
        a7.lower_head = a10;
    }
    
    protected int merge(com.cim.AIA.Polygon a, com.cim.AIA.Polygon a0)
    {
        com.cim.AIA.PolyLine a1 = null;
        com.cim.AIA.PolyLine a2 = null;
        com.cim.AIA.PolyLine a3 = a.lower_head;
        com.cim.AIA.PolyLine a4 = a0.upper_head;
        int i = 0;
        int i0 = 0;
        int i1 = 0;
        com.cim.AIA.PolyLine a5 = a4;
        com.cim.AIA.PolyLine a6 = a3;
        while(true)
        {
            com.cim.AIA.PolyLine a7 = null;
            com.cim.AIA.PolyLine a8 = null;
            com.cim.AIA.PolyLine a9 = null;
            com.cim.AIA.PolyLine a10 = null;
            com.cim.AIA.PolyLine a11 = a5;
            com.cim.AIA.PolyLine a12 = a6;
            if(a5 == null)
            {
                a1 = a5;
                a2 = a6;
                break;
            }
            else
            {
                a7 = a11;
                a8 = a12;
            }
            com.cim.AIA.PolyLine a13 = a7;
            com.cim.AIA.PolyLine a14 = a8;
            if(a8 == null)
            {
                a1 = a7;
                a2 = a8;
                break;
            }
            else
            {
                a9 = a13;
                a10 = a14;
            }
            int i2 = a9.dx;
            com.cim.AIA.PolyLine a15 = a9;
            com.cim.AIA.PolyLine a16 = a10;
            int i3 = a15.dy;
            com.cim.AIA.PolyLine a17 = a15;
            com.cim.AIA.PolyLine a18 = a16;
            int i4 = a18.dx;
            com.cim.AIA.PolyLine a19 = a17;
            com.cim.AIA.PolyLine a20 = a18;
            int i5 = a20.dy;
            com.cim.AIA.PolyLine a21 = a19;
            com.cim.AIA.PolyLine a22 = a20;
            int i6 = this.offset(i, i0, i2, i3, i4, i5);
            com.cim.AIA.PolyLine a23 = a21;
            com.cim.AIA.PolyLine a24 = a22;
            int i7 = i + i6;
            int i8 = i1 + i6;
            int i9 = a23.dy;
            com.cim.AIA.PolyLine a25 = a23;
            com.cim.AIA.PolyLine a26 = a24;
            int i10 = i0 + i9;
            int i11 = a26.dy;
            com.cim.AIA.PolyLine a27 = a25;
            com.cim.AIA.PolyLine a28 = a26;
            com.cim.AIA.PolyLine a29 = a28;
            com.cim.AIA.PolyLine a30 = a27;
            if(i10 > i11)
            {
                com.cim.AIA.PolyLine a31 = a30;
                int i12 = a28.dx;
                com.cim.AIA.PolyLine a32 = a31;
                int i13 = i7 - i12;
                int i14 = a28.dy;
                com.cim.AIA.PolyLine a33 = a32;
                int i15 = i0 - i14;
                com.cim.AIA.PolyLine a34 = a28.link;
                com.cim.AIA.PolyLine a35 = a33;
                i = i13;
                i0 = i15;
                i1 = i8;
                a5 = a35;
                a6 = a34;
            }
            else
            {
                com.cim.AIA.PolyLine a36 = a29;
                int i16 = a27.dx;
                com.cim.AIA.PolyLine a37 = a36;
                int i17 = i7 + i16;
                int i18 = a27.dy;
                com.cim.AIA.PolyLine a38 = a37;
                int i19 = i0 + i18;
                com.cim.AIA.PolyLine a39 = a27.link;
                com.cim.AIA.PolyLine a40 = a38;
                i = i17;
                i0 = i19;
                i1 = i8;
                a5 = a39;
                a6 = a40;
            }
        }
        label1: {
            com.cim.AIA.PolyLine a41 = null;
            label0: {
                if(a1 != null)
                {
                    break label0;
                }
                com.cim.AIA.PolyLine a42 = a0.lower_tail;
                com.cim.AIA.PolyLine a43 = this.bridge(a42, i, i0, a2, 0, 0);
                com.cim.AIA.PolyLine a44 = a43.link;
                if(a44 != null)
                {
                    break label1;
                }
                else
                {
                    a.lower_tail = a43;
                    break label1;
                }
            }
            com.cim.AIA.PolyLine a45 = a.upper_tail;
            com.cim.AIA.PolyLine a46 = this.bridge(a45, 0, 0, a1, i, i0);
            com.cim.AIA.PolyLine a47 = a46.link;
            if(a47 == null)
            {
                a41 = a46;
            }
            else
            {
                com.cim.AIA.PolyLine a48 = a0.upper_tail;
                a41 = a48;
            }
            a.upper_tail = a41;
            com.cim.AIA.PolyLine a49 = a0.lower_tail;
            a.lower_tail = a49;
        }
        com.cim.AIA.PolyLine a50 = a0.lower_head;
        a.lower_head = a50;
        return i1;
    }
    
    protected int offset(int i, int i0, int i1, int i2, int i3, int i4)
    {
        int i5 = 0;
        label2: {
            int i6 = 0;
            label1: {
                label0: {
                    if(i4 <= i0)
                    {
                        break label0;
                    }
                    int i7 = i0 + i2;
                    if(i7 > 0)
                    {
                        break label1;
                    }
                }
                i5 = 0;
                break label2;
            }
            label5: {
                label3: {
                    int i8 = i4 * i1;
                    int i9 = i2 * i3;
                    int i10 = i8 - i9;
                    if(i10 > 0)
                    {
                        break label3;
                    }
                    label4: {
                        int i11 = i0 + i2;
                        if(i4 >= i11)
                        {
                            break label4;
                        }
                        int i12 = i4 - i0;
                        int i13 = i12 * i1;
                        int i14 = i13 / i2;
                        int i15 = i + i14;
                        int i16 = i3 - i15;
                        i6 = i16;
                        break label5;
                    }
                    int i17 = i0 + i2;
                    if(i4 <= i17)
                    {
                        int i18 = i + i1;
                        int i19 = i3 - i18;
                        i6 = i19;
                        break label5;
                    }
                    else
                    {
                        int i20 = i2 + i0;
                        int i21 = i20 * i3;
                        int i22 = i21 / i4;
                        int i23 = i + i1;
                        int i24 = i22 - i23;
                        i6 = i24;
                        break label5;
                    }
                }
                label6: {
                    if(i0 >= 0)
                    {
                        break label6;
                    }
                    int i25 = i0 * i1;
                    int i26 = i25 / i2;
                    int i27 = i26 - i;
                    i6 = i27;
                    break label5;
                }
                if(i0 <= 0)
                {
                    int i28 = 0 - i;
                    i6 = i28;
                }
                else
                {
                    int i29 = i0 * i3;
                    int i30 = i29 / i4;
                    int i31 = i30 - i;
                    i6 = i31;
                }
            }
            i5 = i6 <= 0?0:i6;
        }
        return i5;
    }
    
    public void paintTree(java.awt.Graphics a, com.cim.AIA.HierarchyTree a0)
    {
        label1: {
            label0: {
                if(a0 != null)
                {
                    break label0;
                }
                break label1;
            }
            a0.drawPartial(a);
            int i = 0;
            while(true)
            {
                java.util.Vector a1 = a0.children;
                int i0 = a1.size();
                if(i >= i0)
                {
                    break;
                }
                com.cim.AIA.Tree a2 = a0.getChild(i);
                com.cim.AIA.HierarchyTree dummy = (com.cim.AIA.HierarchyTree)a2;
                com.cim.AIA.HierarchyTree a3 = (com.cim.AIA.HierarchyTree)a2;
                this.paintTree(a, a3);
                java.awt.Color a4 = a.getColor();
                java.awt.Color a5 = a3.getParentLineColor();
                a.setColor(a5);
                int i1 = a3.nodesAreVisible()?1:0;
                label2: {
                    if(i1 == 0)
                    {
                        break label2;
                    }
                    int i2 = a3.getDrawParentLine()?1:0;
                    if(i2 != 0)
                    {
                        com.cim.AIA.Line a6 = a3.getLine();
                        a6.draw(a);
                    }
                }
                int i3 = i + 1;
                i = i3;
            }
        }
    }
    
    public java.awt.Point plantTree(com.cim.AIA.HierarchyTree a, int i, int i0)
    {
        this.leftBound = 2147483647;
        java.awt.Point a0 = new java.awt.Point(0, 0);
        java.awt.Point a1 = this.plantTree(a, i, i0, a0);
        return a1;
    }
    
    protected java.awt.Point plantTree(com.cim.AIA.HierarchyTree a, int i, int i0, java.awt.Point a0)
    {
        java.awt.Point a1 = a.pos;
        int i1 = a1.x;
        label1: {
            label0: {
                if(i1 <= 0)
                {
                    break label0;
                }
                java.awt.Point a2 = a.pos;
                int i2 = a2.y;
                if(i2 <= 0)
                {
                    break label0;
                }
                java.awt.Point a3 = a.pos;
                java.awt.Point a4 = new java.awt.Point(a3);
                a.prevPos = a4;
                break label1;
            }
            java.awt.Point a5 = a.prevPos;
            java.awt.Point a6 = a.offset;
            int i3 = a6.x;
            int i4 = i + i3;
            a5.x = i4;
            java.awt.Point a7 = a.prevPos;
            java.awt.Point a8 = a.offset;
            int i5 = a8.y;
            int i6 = i0 + i5;
            a7.y = i6;
        }
        java.awt.Point a9 = a.pos;
        java.awt.Point a10 = a.offset;
        int i7 = a10.x;
        int i8 = i + i7;
        a9.x = i8;
        java.awt.Point a11 = a.pos;
        java.awt.Point a12 = a.offset;
        int i9 = a12.y;
        int i10 = i0 + i9;
        a11.y = i10;
        a.calculateLine();
        java.awt.Point a13 = a.pos;
        int i11 = a13.x;
        int i12 = this.leftBound;
        if(i11 < i12)
        {
            java.awt.Point a14 = a.pos;
            int i13 = a14.x;
            this.leftBound = i13;
        }
        java.awt.Point a15 = a.pos;
        int i14 = a15.x;
        int i15 = a.getWidth();
        int i16 = i14 + i15;
        int i17 = a0.x;
        if(i16 > i17)
        {
            java.awt.Point a16 = a.pos;
            int i18 = a16.x;
            int i19 = a.getWidth();
            int i20 = i18 + i19;
            a0.x = i20;
        }
        java.awt.Point a17 = a.pos;
        int i21 = a17.y;
        int i22 = a0.y;
        if(i21 > i22)
        {
            java.awt.Point a18 = a.pos;
            int i23 = a18.y;
            a0.y = i23;
        }
        com.cim.AIA.Tree a19 = a.getChild(0);
        com.cim.AIA.HierarchyTree dummy = (com.cim.AIA.HierarchyTree)a19;
        com.cim.AIA.HierarchyTree a20 = (com.cim.AIA.HierarchyTree)a19;
        label2: {
            if(a20 == null)
            {
                break label2;
            }
            java.awt.Point a21 = a.pos;
            int i24 = a21.x;
            java.awt.Point a22 = a.pos;
            int i25 = a22.y;
            java.awt.Point a23 = this.plantTree(a20, i24, i25, a0);
            java.awt.Point a24 = a.pos;
            int i26 = a24.x;
            java.awt.Point a25 = a20.offset;
            int i27 = a25.x;
            int i28 = i26 + i27;
            int i29 = i28;
            int i30 = 1;
            while(true)
            {
                java.util.Vector a26 = a.children;
                int i31 = a26.size();
                if(i30 >= i31)
                {
                    break;
                }
                else
                {
                    com.cim.AIA.Tree a27 = a.getChild(i30);
                    com.cim.AIA.HierarchyTree dummy0 = (com.cim.AIA.HierarchyTree)a27;
                    com.cim.AIA.HierarchyTree a28 = (com.cim.AIA.HierarchyTree)a27;
                    java.awt.Point a29 = a.pos;
                    int i32 = a29.y;
                    java.awt.Point a30 = a20.offset;
                    int i33 = a30.y;
                    int i34 = i32 + i33;
                    java.awt.Point a31 = this.plantTree(a28, i29, i34, a0);
                    java.awt.Point a32 = a28.offset;
                    int i35 = a32.x;
                    int i36 = i29 + i35;
                    int i37 = i30 + 1;
                    i29 = i36;
                    i30 = i37;
                }
            }
        }
        return a0;
    }
}