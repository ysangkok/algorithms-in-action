public class PatriciaTreeIterExtendedHierarchyTree extends com.cim.AIA.HierarchyTree {
    public PatriciaTreeIterExtendedHierarchyTree()
    {
        super();
        java.awt.Color a = java.awt.Color.white;
        this.borderColor = a;
    }
    
    public PatriciaTreeIterExtendedHierarchyTree(com.cim.AIA.Tree a)
    {
        super(a);
    }
    
    public PatriciaTreeIterExtendedHierarchyTree(com.cim.AIA.Tree a, com.cim.AIA.Node a0)
    {
        super(a, a0);
    }
    
    public PatriciaTreeIterExtendedHierarchyTree(com.cim.AIA.Node a)
    {
        super(a);
    }
    
    public java.awt.Point getParentConnectPoint()
    {
        java.awt.Point a = null;
        com.cim.AIA.Tree a0 = this.parentTree;
        label2: {
            int i = 0;
            label1: {
                label0: {
                    if(a0 == null)
                    {
                        break label0;
                    }
                    java.util.Vector a1 = this.elements;
                    int i0 = a1.size();
                    if(i0 != 0)
                    {
                        break label1;
                    }
                }
                a = null;
                break label2;
            }
            com.cim.AIA.Tree a2 = this.parentTree;
            PatriciaTreeIterExtendedHierarchyTree dummy = (PatriciaTreeIterExtendedHierarchyTree)a2;
            PatriciaTreeIterExtendedHierarchyTree a3 = (PatriciaTreeIterExtendedHierarchyTree)a2;
            java.awt.Point a4 = a3.pos;
            int i1 = a4.x;
            int i2 = a3.getWidth();
            int i3 = i2 / 2;
            int i4 = i1 + i3;
            java.awt.Point a5 = a3.pos;
            int i5 = a5.y;
            int i6 = a3.getHeight();
            int i7 = i5 + i6;
            java.awt.Point a6 = new java.awt.Point(i4, i7);
            java.util.Vector a7 = a3.children;
            int i8 = a7.size();
            label3: {
                if(i8 != 1)
                {
                    break label3;
                }
                a = a6;
                break label2;
            }
            int i9 = 0;
            while(true)
            {
                int i10 = 0;
                java.util.Vector a8 = this.elements;
                int i11 = a8.size();
                if(i9 >= i11)
                {
                    i = 0;
                    break;
                }
                label4: try
                {
                    java.util.Vector a9 = this.elements;
                    Object a10 = a9.elementAt(i9);
                    com.cim.AIA.Node dummy0 = (com.cim.AIA.Node)a10;
                    com.cim.AIA.Node a11 = (com.cim.AIA.Node)a10;
                    Object a12 = a11.getObject();
                    Integer dummy1 = (Integer)a12;
                    Integer a13 = (Integer)a12;
                    i10 = a13.intValue();
                    break label4;
                }
                catch(ClassCastException ignoredException)
                {
                    int i12 = i9 + 1;
                    i9 = i12;
                    continue;
                }
                i = i10;
                break;
            }
            int i13 = 0;
            while(true)
            {
                int i14 = 0;
                java.util.Vector a14 = a3.children;
                int i15 = a14.size();
                if(i13 >= i15)
                {
                    break;
                }
                java.util.Vector a15 = a3.children;
                Object a16 = a15.elementAt(i13);
                PatriciaTreeIterExtendedHierarchyTree dummy2 = (PatriciaTreeIterExtendedHierarchyTree)a16;
                PatriciaTreeIterExtendedHierarchyTree a17 = (PatriciaTreeIterExtendedHierarchyTree)a16;
                label6: {
                    label5: {
                        if(a17 == this)
                        {
                            break label5;
                        }
                        i14 = i13;
                        break label6;
                    }
                    int i16 = 0;
                    while(true)
                    {
                        int i17 = 0;
                        int i18 = 0;
                        java.util.Vector a18 = a3.elements;
                        int i19 = a18.size();
                        if(i16 >= i19)
                        {
                            i14 = i13;
                            break;
                        }
                        try
                        {
                            java.util.Vector a19 = a3.elements;
                            Object a20 = a19.elementAt(i16);
                            com.cim.AIA.Node dummy3 = (com.cim.AIA.Node)a20;
                            com.cim.AIA.Node a21 = (com.cim.AIA.Node)a20;
                            Object a22 = a21.getObject();
                            Integer dummy4 = (Integer)a22;
                            Integer a23 = (Integer)a22;
                            int i20 = a23.intValue();
                            i17 = i20;
                        }
                        catch(ClassCastException ignoredException0)
                        {
                            i17 = 0;
                        }
                        label7: {
                            if(i13 != 0)
                            {
                                break label7;
                            }
                            java.awt.Point a24 = a3.pos;
                            int i21 = a24.x;
                            a6.x = i21;
                            java.awt.Point a25 = a3.pos;
                            int i22 = a25.y;
                            com.cim.AIA.Node a26 = a3.elementAt(0);
                            int i23 = a26.getHeight();
                            int i24 = i22 + i23;
                            a6.y = i24;
                            i14 = 0;
                            break;
                        }
                        java.util.Vector a27 = a3.children;
                        int i25 = a27.size();
                        label8: {
                            int i26 = i25 - 1;
                            if(i13 != i26)
                            {
                                break label8;
                            }
                            java.awt.Point a28 = a3.pos;
                            int i27 = a28.x;
                            int i28 = a3.getWidth();
                            int i29 = i27 + i28;
                            a6.x = i29;
                            i14 = i13;
                            break;
                        }
                        if(i16 != 0)
                        {
                            i18 = i16;
                        }
                        else
                        {
                            java.awt.Point a29 = a3.pos;
                            int i30 = a29.x;
                            a6.x = i30;
                            i18 = 0;
                        }
                        label9: {
                            if(i <= i17)
                            {
                                break label9;
                            }
                            if(i17 != 0)
                            {
                                int i31 = a6.x;
                                int i32 = a3.getWidthOfNode(i18);
                                int i33 = i31 + i32;
                                a6.x = i33;
                            }
                        }
                        int i34 = i18 + 1;
                        i16 = i34;
                    }
                }
                int i35 = i14 + 1;
                i13 = i35;
            }
            a = a6;
        }
        return a;
    }
}