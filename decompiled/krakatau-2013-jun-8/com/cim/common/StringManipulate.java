package com.cim.common;

public class StringManipulate {
    String string;
    String separator;
    
    public StringManipulate(String s)
    {
        super();
        this.separator = "\n";
        this.string = s;
    }
    
    protected String getNext(int i)
    {
        String s = null;
        label1: {
            int i0 = 0;
            int i1 = 0;
            int i2 = 0;
            label3: try
            {
                String s0 = this.string;
                int i3 = s0.length();
                label0: {
                    if(i != i3)
                    {
                        break label0;
                    }
                    String s1 = this.string;
                    int i4 = s1.length();
                    s = "";
                    break label1;
                }
                String s2 = this.string;
                int i5 = s2.indexOf(" ", i);
                label2: {
                    if(i5 == -1)
                    {
                        break label2;
                    }
                    i0 = i5 + 1;
                    String s3 = this.string;
                    i1 = s3.indexOf(" ", i0);
                    break label3;
                }
                String s4 = this.string;
                int i6 = s4.length();
                s = "";
                break label1;
            }
            catch(Throwable a)
            {
                String s5 = this.string;
                int i7 = s5.length();
                throw a;
            }
            if(i1 != -1)
            {
                i2 = i1;
            }
            else
            {
                String s6 = this.string;
                int i8 = s6.length();
                i2 = i8;
            }
            String s7 = this.string;
            String s8 = s7.substring(i0, i2);
            String s9 = s8.trim();
            s = s9;
        }
        return s;
    }
    
    protected String getNth(int i)
    {
        String s = null;
        try
        {
            String s0 = this.string;
            String s1 = this.separator;
            java.util.StringTokenizer a = new java.util.StringTokenizer(s0, s1);
            int i0 = i;
            while(true)
            {
                int i1 = i0 + -1;
                if(i1 < 0)
                {
                    break;
                }
                else
                {
                    Object a0 = a.nextElement();
                    i0 = i1;
                }
            }
            String s2 = a.nextToken();
            String s3 = s2.trim();
            s = s3;
        }
        catch(java.util.NoSuchElementException ignoredException)
        {
            s = "";
        }
        return s;
    }
    
    protected String getPrevious(int i)
    {
        String s = null;
        label1: {
            label0: {
                if(i != 0)
                {
                    break label0;
                }
                s = "";
                break label1;
            }
            int i0 = i - 2;
            String s0 = this.string;
            int i1 = s0.lastIndexOf(" ", i0);
            int i2 = i1 != -1?i1:0;
            if(i0 > 0)
            {
                String s1 = this.string;
                int i3 = i0 + 1;
                String s2 = s1.substring(i2, i3);
                String s3 = s2.trim();
                s = s3;
            }
            else
            {
                s = "";
            }
        }
        return s;
    }
    
    protected int getTokenCount()
    {
        String s = this.string;
        String s0 = this.separator;
        java.util.StringTokenizer a = new java.util.StringTokenizer(s, s0);
        int i = a.countTokens();
        return i;
    }
    
    protected String replace(String s, int i)
    {
        Integer a = new Integer(i);
        String s0 = a.toString();
        String s1 = this.replace(s, s0);
        return s1;
    }
    
    protected String replace(String s, String s0)
    {
        String s1 = this.replace(s, s0, 0);
        return s1;
    }
    
    private String replace(String s, String s0, int i)
    {
        String s1 = this.replace(s, s0, 0, false);
        return s1;
    }
    
    protected String replace(String s, String s0, int i, boolean b)
    {
        String s1 = null;
        int i0 = b?1:0;
        StringBuffer a = new StringBuffer();
        label2: {
            try
            {
                int i1 = 0;
                label1: {
                    label0: {
                        if(i0 != 0)
                        {
                            break label0;
                        }
                        String s2 = this.string;
                        int i2 = s2.indexOf(s, i);
                        if(i2 != -1)
                        {
                            i1 = i2;
                            break label1;
                        }
                        String s3 = this.string;
                        s1 = s3;
                        break label2;
                    }
                    String s4 = this.string;
                    com.cim.common.StringManipulate a0 = new com.cim.common.StringManipulate(s4);
                    int i3 = a0.wordIndex(s, i);
                    label3: {
                        if(i3 == -1)
                        {
                            break label3;
                        }
                        i1 = i3;
                        break label1;
                    }
                    String s5 = this.string;
                    s1 = s5;
                    break label2;
                }
                int i4 = s.length();
                int i5 = 0;
                while(true)
                {
                    String s6 = this.string;
                    int i6 = s6.length();
                    if(i5 >= i6)
                    {
                        break;
                    }
                    label5: {
                        label4: {
                            if(i5 < i1)
                            {
                                break label4;
                            }
                            int i7 = i1 + i4;
                            if(i5 >= i7)
                            {
                                break label4;
                            }
                            break label5;
                        }
                        String s7 = this.string;
                        int i8 = s7.charAt(i5);
                        StringBuffer a1 = a.append((char)i8);
                    }
                    int i9 = i5 + 1;
                    i5 = i9;
                }
                StringBuffer a2 = a.insert(i1, s0);
            }
            catch(StringIndexOutOfBoundsException a3)
            {
                java.io.PrintStream a4 = System.out;
                a4.println((Object)a3);
            }
            String s8 = a.toString();
            this.string = s8;
            String s9 = a.toString();
            s1 = s9;
        }
        return s1;
    }
    
    protected void setSeparator(String s)
    {
        this.separator = s;
    }
    
    protected String shrink()
    {
        String s = this.shrink(false);
        return s;
    }
    
    protected String shrink(boolean b)
    {
        String s = null;
        int i = b?1:0;
        StringBuffer a = new StringBuffer();
        String s0 = this.string;
        label1: {
            label0: {
                if(s0 != null)
                {
                    break label0;
                }
                String s1 = this.string;
                s = s1;
                break label1;
            }
            int i0 = i;
            int i1 = 0;
            int i2 = 0;
            while(true)
            {
                int i3 = 0;
                int i4 = 0;
                int i5 = 0;
                int i6 = 0;
                int i7 = 0;
                int i8 = 0;
                int i9 = 0;
                int i10 = 0;
                int i11 = 0;
                String s2 = this.string;
                int i12 = i0;
                int i13 = s2.length();
                int i14 = i12;
                int i15 = i14;
                if(i2 >= i13)
                {
                    break;
                }
                else
                {
                    i3 = i15;
                }
                String s3 = this.string;
                int i16 = i3;
                int i17 = s3.charAt(i2);
                int i18 = i16;
                label2: {
                    int i19 = 0;
                    int i20 = i18;
                    int i21 = i18;
                    if(i17 != 10)
                    {
                        i4 = i21;
                        i5 = i1;
                        i6 = i2;
                        break label2;
                    }
                    else
                    {
                        i19 = i20;
                    }
                    int i22 = i19;
                    if(i19 != 0)
                    {
                        int i23 = i22;
                        int i24 = i2 + 1;
                        i4 = i23;
                        i5 = i1;
                        i6 = i24;
                    }
                    else
                    {
                        int i25 = i1 + 2;
                        StringBuffer a0 = a.append(" ");
                        i4 = 0;
                        i5 = i25;
                        i6 = i2;
                    }
                }
                String s4 = this.string;
                int i26 = i4;
                int i27 = s4.charAt(i6);
                int i28 = i26;
                label3: {
                    int i29 = 0;
                    int i30 = i28;
                    int i31 = i28;
                    if(i27 != 9)
                    {
                        i7 = i31;
                        i8 = i5;
                        break label3;
                    }
                    else
                    {
                        i29 = i30;
                    }
                    int i32 = i5 + 2;
                    int i33 = i29;
                    if(i29 != 0)
                    {
                        i7 = i33;
                        i8 = i32;
                    }
                    else
                    {
                        StringBuffer a1 = a.append(" ");
                        i7 = 0;
                        i8 = i32;
                    }
                }
                String s5 = this.string;
                int i34 = i7;
                int i35 = s5.charAt(i6);
                int i36 = i34;
                label6: {
                    int i37 = 0;
                    label5: {
                        int i38 = 0;
                        label4: {
                            int i39 = 0;
                            int i40 = 0;
                            int i41 = i36;
                            int i42 = i36;
                            if(i35 == 32)
                            {
                                i38 = i42;
                                break label4;
                            }
                            else
                            {
                                i39 = i41;
                            }
                            String s6 = this.string;
                            int i43 = i39;
                            int i44 = s6.charAt(i6);
                            int i45 = i43;
                            int i46 = i45;
                            int i47 = i45;
                            if(i44 == 10)
                            {
                                i38 = i47;
                                break label4;
                            }
                            else
                            {
                                i40 = i46;
                            }
                            String s7 = this.string;
                            int i48 = i40;
                            int i49 = s7.charAt(i6);
                            int i50 = i48;
                            int i51 = i50;
                            int i52 = i50;
                            if(i49 != 9)
                            {
                                i37 = i52;
                                break label5;
                            }
                            else
                            {
                                i38 = i51;
                            }
                        }
                        int i53 = i8 + 1;
                        i9 = i38;
                        i10 = i53;
                        break label6;
                    }
                    i9 = i37;
                    i10 = 0;
                }
                int i54 = i9;
                int i55 = i9;
                if(i10 > 1)
                {
                    i11 = i55;
                }
                else
                {
                    int i56 = i54;
                    String s8 = this.string;
                    int i57 = i56;
                    int i58 = s8.charAt(i6);
                    int i59 = i57;
                    StringBuffer a2 = a.append((char)i58);
                    int i60 = i59;
                    i11 = i60;
                }
                int i61 = i6 + 1;
                i0 = i11;
                i1 = i10;
                i2 = i61;
            }
            String s9 = a.toString();
            s = s9;
        }
        return s;
    }
    
    public String splitLine(java.awt.Font a, int i, boolean b, int i0)
    {
        int i1 = i - 20;
        int i2 = b?1:0;
        String s = this.string;
        StringBuffer a0 = new StringBuffer(s);
        String s0 = this.string;
        java.awt.Toolkit a1 = java.awt.Toolkit.getDefaultToolkit();
        java.awt.FontMetrics a2 = a1.getFontMetrics(a);
        int i3 = i0 != 0?i0:2147483647;
        int i4 = i2;
        int i5 = i3;
        String s1 = s0;
        int i6 = 0;
        int i7 = 0;
        while(true)
        {
            int i8 = 0;
            int i9 = s1.length();
            int i10 = i4;
            int i11 = i5;
            String s2 = s1;
            int i12 = i6;
            label0: {
                int i13 = 0;
                int i14 = 0;
                String s3 = null;
                int i15 = 0;
                int i16 = 0;
                int i17 = 0;
                String s4 = null;
                int i18 = 0;
                int i19 = 0;
                int i20 = 0;
                String s5 = null;
                int i21 = 0;
                int i22 = 0;
                int i23 = i10;
                int i24 = i11;
                String s6 = s2;
                int i25 = i12;
                if(i7 >= i9)
                {
                    i8 = i7;
                    break label0;
                }
                else
                {
                    i13 = i23;
                    i14 = i24;
                    s3 = s6;
                    i15 = i25;
                }
                label1: {
                    int i26 = i13;
                    int i27 = i14;
                    String s7 = s3;
                    int i28 = i15;
                    if(i14 != 0)
                    {
                        i16 = i26;
                        i17 = i27;
                        s4 = s7;
                        i18 = i28;
                        break label1;
                    }
                    i8 = i7;
                    break label0;
                }
                int i29 = s4.charAt(i7);
                int i30 = i16;
                int i31 = i17;
                String s8 = s4;
                int i32 = i18;
                int i33 = i30;
                int i34 = i31;
                String s9 = s8;
                int i35 = i30;
                int i36 = i31;
                String s10 = s8;
                int i37 = i32;
                if(i29 != 10)
                {
                    i19 = i35;
                    i20 = i36;
                    s5 = s10;
                    i21 = i37;
                }
                else
                {
                    int i38 = i33;
                    int i39 = i34;
                    String s11 = s9;
                    i19 = i38;
                    i20 = i39;
                    s5 = s11;
                    i21 = i7;
                }
                String s12 = s5.substring(i21, i7);
                int i40 = i19;
                int i41 = i20;
                String s13 = s5;
                int i42 = i21;
                int i43 = a2.stringWidth(s12);
                int i44 = i40;
                int i45 = i41;
                String s14 = s13;
                int i46 = i42;
                label6: {
                    int i47 = 0;
                    int i48 = 0;
                    String s15 = null;
                    int i49 = 0;
                    int i50 = 0;
                    label2: {
                        int i51 = 0;
                        int i52 = 0;
                        int i53 = 0;
                        int i54 = 0;
                        int i55 = i44;
                        int i56 = i44;
                        int i57 = i45;
                        String s16 = s14;
                        int i58 = i46;
                        if(i43 < i1)
                        {
                            i47 = i56;
                            i48 = i57;
                            s15 = s16;
                            i49 = i58;
                            i50 = i7;
                            break label2;
                        }
                        else
                        {
                            i51 = i55;
                        }
                        label4: {
                            int i59 = 0;
                            int i60 = 0;
                            int i61 = 0;
                            int i62 = 0;
                            label3: {
                                int i63 = i51;
                                if(i51 != 0)
                                {
                                    i59 = i63;
                                    break label3;
                                }
                                int i64 = i7 - 1;
                                StringBuffer a3 = a0.insert(i64, "\n");
                                int i65 = i45 + -1;
                                i52 = 0;
                                i53 = i65;
                                i22 = i7;
                                break label4;
                            }
                            String s17 = s14.substring(i46, i7);
                            int i66 = i59;
                            int i67 = s17.lastIndexOf(" ");
                            int i68 = i66;
                            label5: {
                                int i69 = 0;
                                int i70 = i68;
                                int i71 = i68;
                                if(i67 != -1)
                                {
                                    i60 = i71;
                                    break label5;
                                }
                                else
                                {
                                    i69 = i70;
                                }
                                int i72 = i7 - 1;
                                StringBuffer a4 = a0.insert(i72, "\n");
                                int i73 = i69;
                                int i74 = i45 + -1;
                                int i75 = i7 - 1;
                                String s18 = s14.substring(i46, i75);
                                int i76 = i73;
                                int i77 = a2.stringWidth(s18);
                                int i78 = i76;
                                int i79 = i78;
                                int i80 = i78;
                                if(i77 <= i1)
                                {
                                    i52 = i80;
                                    i53 = i74;
                                    i22 = i7;
                                    break label4;
                                }
                                else
                                {
                                    int i81 = i79;
                                    i52 = i81;
                                    i53 = i74;
                                    i22 = i7;
                                    break label4;
                                }
                            }
                            int i82 = i46 + i67;
                            int i83 = i82 + 1;
                            StringBuffer a5 = a0.insert(i83, "\n");
                            int i84 = i60;
                            int i85 = i46 + i67;
                            int i86 = i85 + 1;
                            int i87 = i45 + -1;
                            int i88 = i84;
                            int i89 = i84;
                            if(i46 <= 0)
                            {
                                i52 = i89;
                                i53 = i87;
                                i22 = i86;
                                break label4;
                            }
                            else
                            {
                                i61 = i88;
                            }
                            int i90 = i61;
                            int i91 = i61;
                            if(i67 <= i46)
                            {
                                i52 = i91;
                                i53 = i87;
                                i22 = i86;
                                break label4;
                            }
                            else
                            {
                                i62 = i90;
                            }
                            int i92 = i62;
                            StringBuilder a6 = new StringBuilder();
                            int i93 = i92;
                            StringBuilder a7 = a6.append("DEBUG ");
                            int i94 = i93;
                            StringBuilder a8 = a7.append(i46);
                            int i95 = i94;
                            StringBuilder a9 = a8.append(" ");
                            int i96 = i95;
                            StringBuilder a10 = a9.append(i67);
                            int i97 = i96;
                            StringBuilder a11 = a10.append(" ");
                            int i98 = i97;
                            StringBuilder a12 = a11.append(i1);
                            int i99 = i98;
                            String s19 = a12.toString();
                            int i100 = i99;
                            com.cim.common.Common.debug(s19, 2);
                            int i101 = i100;
                            int i102 = i101;
                            StringBuilder a13 = new StringBuilder();
                            int i103 = i102;
                            StringBuilder a14 = a13.append("LEN ");
                            int i104 = i103;
                            int i105 = s14.length();
                            int i106 = i104;
                            StringBuilder a15 = a14.append(i105);
                            int i107 = i106;
                            String s20 = a15.toString();
                            int i108 = i107;
                            com.cim.common.Common.debug(s20, 2);
                            int i109 = i108;
                            int i110 = i109;
                            StringBuilder a16 = new StringBuilder();
                            int i111 = i110;
                            StringBuilder a17 = a16.append("L    ");
                            int i112 = i111;
                            String s21 = s14.substring(i46, i67);
                            int i113 = i112;
                            StringBuilder a18 = a17.append(s21);
                            int i114 = i113;
                            String s22 = a18.toString();
                            int i115 = i114;
                            com.cim.common.Common.debug(s22, 2);
                            int i116 = i115;
                            String s23 = s14.substring(i46, i67);
                            int i117 = i116;
                            int i118 = a2.stringWidth(s23);
                            int i119 = i117;
                            int i120 = i119;
                            int i121 = i119;
                            if(i118 <= i1)
                            {
                                i52 = i121;
                                i53 = i87;
                                i22 = i86;
                            }
                            else
                            {
                                int i122 = i120;
                                i52 = i122;
                                i53 = i87;
                                i22 = i86;
                            }
                        }
                        int i123 = i52;
                        if(i53 != 0)
                        {
                            i54 = i123;
                        }
                        else
                        {
                            break label6;
                        }
                        int i124 = i22 + 1;
                        String s24 = a0.toString();
                        int i125 = i54;
                        i47 = i125;
                        i48 = i53;
                        s15 = s24;
                        i49 = i124;
                        i50 = i22;
                    }
                    int i126 = i50 + 1;
                    i4 = i47;
                    i5 = i48;
                    s1 = s15;
                    i6 = i49;
                    i7 = i126;
                    continue;
                }
                i8 = i22;
            }
            String s25 = a0.toString();
            String s26 = s25.substring(0, i8);
            return s26;
        }
    }
    
    protected String stripHeader()
    {
        String s = this.string;
        String s0 = this.string;
        String s1 = this.separator;
        int i = s0.indexOf(s1);
        int i0 = i + 1;
        String s2 = this.string;
        int i1 = s2.length();
        String s3 = s.substring(i0, i1);
        return s3;
    }
    
    public String toString()
    {
        String s = this.string;
        return s;
    }
    
    protected int wordCount()
    {
        int i = 0;
        int i0 = 0;
        try
        {
            i = 0;
            String s = this.string;
            i = 0;
            String s0 = this.separator;
            i = 0;
            java.util.StringTokenizer a = new java.util.StringTokenizer(s, s0);
            int i1 = 0;
            while(true)
            {
                i = i1;
                int i2 = a.hasMoreTokens()?1:0;
                if(i2 == 0)
                {
                    break;
                }
                else
                {
                    i = i1;
                    Object a0 = a.nextElement();
                    int i3 = i1 + 1;
                    i1 = i3;
                }
            }
            i0 = i1;
        }
        catch(java.util.NoSuchElementException a1)
        {
            java.io.PrintStream a2 = System.out;
            a2.println((Object)a1);
            i0 = i;
        }
        return i0;
    }
    
    protected int wordIndex(String s, int i)
    {
        int i0 = 0;
        String s0 = this.string;
        int i1 = s0.length();
        label1: {
            int i2 = 0;
            label0: {
                if(i < i1)
                {
                    break label0;
                }
                i0 = -1;
                break label1;
            }
            label2: {
                if(i >= 0)
                {
                    break label2;
                }
                i0 = -1;
                break label1;
            }
            String s1 = this.string;
            String s2 = this.string;
            int i3 = s2.length();
            String s3 = s1.substring(i, i3);
            int i4 = s3.equalsIgnoreCase(s)?1:0;
            label3: {
                if(i4 == 0)
                {
                    break label3;
                }
                i0 = 0;
                break label1;
            }
            int i5 = i;
            while(true)
            {
                String s4 = this.string;
                int i6 = s4.length();
                int i7 = s.length();
                int i8 = i6 - i7;
                if(i5 > i8)
                {
                    i2 = -1;
                    break;
                }
                String s5 = this.string;
                int i9 = s.length();
                int i10 = i5 + i9;
                String s6 = s5.substring(i5, i10);
                int i11 = s6.equalsIgnoreCase(s)?1:0;
                if(i11 == 0)
                {
                    int i12 = i5 + 1;
                    i5 = i12;
                    continue;
                }
                i2 = i5;
                break;
            }
            label4: {
                if(i2 != -1)
                {
                    break label4;
                }
                i0 = -1;
                break label1;
            }
            label5: {
                if(i2 == 0)
                {
                    break label5;
                }
                String s7 = this.string;
                int i13 = i2 - 1;
                String s8 = s7.substring(i13, i2);
                int i14 = s8.equals((Object)" ")?1:0;
                if(i14 != 0)
                {
                    break label5;
                }
                int i15 = i2 + 1;
                int i16 = this.wordIndex(s, i15);
                i0 = i16;
                break label1;
            }
            String s9 = this.string;
            int i17 = s9.length();
            int i18 = s.length();
            label6: {
                int i19 = i2 + i18;
                if(i17 != i19)
                {
                    break label6;
                }
                i0 = i2;
                break label1;
            }
            String s10 = this.string;
            int i20 = s.length();
            int i21 = i2 + i20;
            int i22 = s.length();
            int i23 = i2 + i22;
            int i24 = i23 + 1;
            String s11 = s10.substring(i21, i24);
            int i25 = s11.equals((Object)" ")?1:0;
            if(i25 == 0)
            {
                int i26 = i2 + 1;
                int i27 = this.wordIndex(s, i26);
                i0 = i27;
            }
            else
            {
                i0 = i2;
            }
        }
        return i0;
    }
}