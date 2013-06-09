public class MinSpanTreePrimTestMinSpanTree {
    public MinSpanTreePrimTestMinSpanTree()
    {
        super();
    }
    
    static int getNumber()
    {
        int i = 0;
        byte[] a = new byte[20];
        label2: {
            int i0 = 0;
            label1: {
                label0: {
                    java.io.InputStream a0 = null;
                    try
                    {
                        a0 = System.in;
                    }
                    catch(java.io.IOException ignoredException)
                    {
                        break label0;
                    }
                    try
                    {
                        i0 = a0.read(a);
                        break label1;
                    }
                    catch(java.io.IOException ignoredException0)
                    {
                    }
                }
                i = 0;
                break label2;
            }
            i = i0;
        }
        int i1 = i - 1;
        String s = new String(a, 0, i1);
        int i2 = Integer.parseInt(s);
        return i2;
    }
    
    public static void main(String[] a)
    {
        MinSpanTreePrimPriorityQueue a0 = new MinSpanTreePrimPriorityQueue();
        int i = 32;
        while(true)
        {
            int i0 = 0;
            int i1 = 0;
            int i2 = 0;
            int i3 = i;
            if(i == 113)
            {
                return;
            }
            else
            {
                i0 = i3;
            }
            label2: {
                int i4 = 0;
                label1: {
                    int i5 = 0;
                    label0: {
                        int i6 = 0;
                        java.io.InputStream a1 = null;
                        int i7 = 0;
                        int i8 = 0;
                        try
                        {
                            i6 = i0;
                            a1 = System.in;
                            i7 = i0;
                        }
                        catch(java.io.IOException ignoredException)
                        {
                            i5 = i6;
                            break label0;
                        }
                        try
                        {
                            i8 = i7;
                            i4 = a1.read();
                            break label1;
                        }
                        catch(java.io.IOException ignoredException0)
                        {
                            i5 = i8;
                        }
                    }
                    i1 = i5;
                    break label2;
                }
                i1 = i4;
            }
            label4: {
                int i9 = 0;
                int i10 = 0;
                int i11 = 0;
                label3: {
                    int i12 = i1;
                    if(i1 != 105)
                    {
                        i9 = i12;
                        break label3;
                    }
                    int i13 = MinSpanTreePrimTestMinSpanTree.getNumber();
                    int i14 = MinSpanTreePrimTestMinSpanTree.getNumber();
                    a0.insert(i13, 0, i14);
                    i2 = 105;
                    break label4;
                }
                label5: {
                    int i15 = i9;
                    if(i9 != 117)
                    {
                        i10 = i15;
                        break label5;
                    }
                    int i16 = MinSpanTreePrimTestMinSpanTree.getNumber();
                    int i17 = MinSpanTreePrimTestMinSpanTree.getNumber();
                    int i18 = a0.update(i16, 0, i17);
                    i2 = 117;
                    break label4;
                }
                label6: {
                    int i19 = i10;
                    if(i10 != 100)
                    {
                        i11 = i19;
                        break label6;
                    }
                    int i20 = MinSpanTreePrimTestMinSpanTree.getNumber();
                    a0.delete(i20);
                    i2 = 100;
                    break label4;
                }
                int i21 = i11;
                if(i11 != 114)
                {
                    i2 = i21;
                }
                else
                {
                    MinSpanTreePrimPriorityQueueMember a2 = a0.remove();
                    int i22 = a2.getKey();
                    java.io.PrintStream a3 = System.out;
                    a3.print("poped: ");
                    java.io.PrintStream a4 = System.out;
                    a4.println(i22);
                    i2 = 114;
                }
            }
            java.util.Vector a5 = a0.getQueue();
            int i23 = i2;
            java.io.PrintStream a6 = System.out;
            int i24 = i23;
            a6.println("---------");
            int i25 = i24;
            int i26 = 0;
            int i27 = i25;
            while(true)
            {
                int i28 = a5.size();
                int i29 = i27;
                int i30 = i29;
                int i31 = i29;
                if(i26 >= i28)
                {
                    int i32 = i31;
                    java.io.PrintStream a7 = System.out;
                    int i33 = i32;
                    a7.println("---------");
                    int i34 = i33;
                    i = i34;
                }
                else
                {
                    int i35 = i30;
                    java.io.PrintStream a8 = System.out;
                    int i36 = i35;
                    Object a9 = a5.elementAt(i26);
                    int i37 = i36;
                    Integer dummy = (Integer)a9;
                    Integer a10 = (Integer)a9;
                    int i38 = i37;
                    int i39 = a10.intValue();
                    int i40 = i38;
                    a8.println(i39);
                    int i41 = i40;
                    int i42 = i26 + 1;
                    i26 = i42;
                    i27 = i41;
                }
            }
        }
    }
}