package com.cim.AIA;

public class DuplicateLabel {
    final protected static String[] DUPLICATE_LABELS;
    
    public DuplicateLabel()
    {
        super();
    }
    
    public static String[] createDuplicateLabels(Object[] a)
    {
        String[] a0 = com.cim.AIA.DuplicateLabel.createDuplicateLabels(a, false);
        return a0;
    }
    
    public static String[] createDuplicateLabels(Object[] a, boolean b)
    {
        int i = a.length;
        int i0 = b?1:0;
        String[] a0 = new String[i];
        int i1 = a0.length;
        java.util.Hashtable a1 = new java.util.Hashtable(i1);
        int i2 = 0;
        while(true)
        {
            int i3 = a.length;
            if(i2 >= i3)
            {
                break;
            }
            Object a2 = a[i2];
            Object a3 = a1.get(a2);
            if(a3 != null)
            {
                Integer dummy = (Integer)a3;
                Integer a4 = (Integer)a3;
                int i4 = a4.intValue();
                int i5 = i4 + 1;
                Object a5 = a[i2];
                Integer a6 = new Integer(i5);
                Object a7 = a1.put(a5, (Object)a6);
            }
            else
            {
                Object a8 = a[i2];
                Integer a9 = new Integer(1);
                Object a10 = a1.put(a8, (Object)a9);
            }
            int i6 = i2 + 1;
            i2 = i6;
        }
        label0: {
            if(i0 != 0)
            {
                break label0;
            }
            int i7 = 0;
            while(true)
            {
                int i8 = a.length;
                if(i7 >= i8)
                {
                    break;
                }
                Object a11 = a[i7];
                Object a12 = a1.get(a11);
                label1: {
                    if(a12 == null)
                    {
                        break label1;
                    }
                    Integer dummy0 = (Integer)a12;
                    Integer a13 = (Integer)a12;
                    int i9 = a13.intValue();
                    if(i9 == 1)
                    {
                        Object a14 = a[i7];
                        Object a15 = a1.remove(a14);
                    }
                }
                int i10 = i7 + 1;
                i7 = i10;
            }
        }
        int i11 = a.length;
        int i12 = i11 - 1;
        int i13 = i12;
        while(true)
        {
            if(i13 < 0)
            {
                return a0;
            }
            Object a16 = a[i13];
            Object a17 = a1.get(a16);
            label2: {
                if(a17 == null)
                {
                    break label2;
                }
                Integer dummy1 = (Integer)a17;
                Integer a18 = (Integer)a17;
                int i14 = a18.intValue();
                String[] a19 = com.cim.AIA.DuplicateLabel.DUPLICATE_LABELS;
                int i15 = a19.length;
                if(i14 <= i15)
                {
                    String[] a20 = com.cim.AIA.DuplicateLabel.DUPLICATE_LABELS;
                    int i16 = i14 - 1;
                    String s = a20[i16];
                    a0[i13] = s;
                }
                Object a21 = a[i13];
                int i17 = i14 - 1;
                Integer a22 = new Integer(i17);
                Object a23 = a1.put(a21, (Object)a22);
            }
            int i18 = i13 + -1;
            i13 = i18;
        }
    }
    
    static
    {
        String[] a = new String[31];
        a[0] = "a";
        a[1] = "b";
        a[2] = "c";
        a[3] = "d";
        a[4] = "e";
        a[5] = "f";
        a[6] = "g";
        a[7] = "h";
        a[8] = "i";
        a[9] = "j";
        a[10] = "k";
        a[11] = "l";
        a[12] = "m";
        a[13] = "n";
        a[14] = "o";
        a[15] = "q";
        a[16] = "r";
        a[17] = "s";
        a[18] = "t";
        a[19] = "u";
        a[20] = "v";
        a[21] = "q";
        a[22] = "r";
        a[23] = "s";
        a[24] = "t";
        a[25] = "u";
        a[26] = "v";
        a[27] = "w";
        a[28] = "x";
        a[29] = "y";
        a[30] = "z";
        com.cim.AIA.DuplicateLabel.DUPLICATE_LABELS = a;
    }
}