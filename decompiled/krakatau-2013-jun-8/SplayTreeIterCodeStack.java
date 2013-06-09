public class SplayTreeIterCodeStack {
    private SplayTreeIterCodeStack$SplayTreeIterCodeStackData top;
    private java.awt.Point position;
    private int width;
    private static java.util.Hashtable codeHashtable;
    final private static String COMMENT;
    final public static int gap = 10;
    final private static String[][][] allCode;
    final private static String[] searchCode;
    final private static String[] zigZigSearchCode;
    final private static String[] zigZagSearchCode;
    final private static String[] insertCode;
    final private static String[] terminalCode;
    final private static String[] zigZigCode;
    final private static String[] zigZagCode;
    
    public SplayTreeIterCodeStack()
    {
        super();
        java.awt.Point a = new java.awt.Point();
        this.position = a;
        this.top = null;
    }
    
    public void setCode(String s)
    {
        SplayTreeIterCodeStack$SplayTreeIterCodeStackData a = this.top;
        a.setCode(s);
    }
    
    public void push(String s)
    {
        SplayTreeIterCodeStack$SplayTreeIterCodeStackData a = new SplayTreeIterCodeStack$SplayTreeIterCodeStackData(this, s);
        SplayTreeIterCodeStack$SplayTreeIterCodeStackData a0 = this.top;
        a.nextOnStack = a0;
        this.top = a;
    }
    
    public boolean isEmpty()
    {
        SplayTreeIterCodeStack$SplayTreeIterCodeStackData a = this.top;
        int i = a != null?0:1;
        return i != 0;
    }
    
    public void pop()
    {
        SplayTreeIterCodeStack$SplayTreeIterCodeStackData a = this.top;
        SplayTreeIterCodeStack$SplayTreeIterCodeStackData a0 = a.nextOnStack;
        this.top = a0;
    }
    
    private void setCurrentLineNumber(int i)
    {
        SplayTreeIterCodeStack$SplayTreeIterCodeStackData a = this.top;
        if(a != null)
        {
            SplayTreeIterCodeStack$SplayTreeIterCodeStackData a0 = this.top;
            a0.currentLineNumber = i;
        }
    }
    
    public void stepCodeLine()
    {
        SplayTreeIterCodeStack$SplayTreeIterCodeStackData a = this.top;
        label0: {
            if(a == null)
            {
                break label0;
            }
            SplayTreeIterCodeStack$SplayTreeIterCodeStackData a0 = this.top;
            int i = a0.currentLineNumber;
            int i0 = i + 1;
            int i1 = i0;
            while(true)
            {
                SplayTreeIterCodeStack$SplayTreeIterCodeStackData a1 = this.top;
                String[] a2 = a1.code;
                int i2 = a2.length;
                if(i1 >= i2)
                {
                    break;
                }
                SplayTreeIterCodeStack$SplayTreeIterCodeStackData a3 = this.top;
                String[] a4 = a3.code;
                String s = a4[i1];
                int i3 = s.length();
                label1: {
                    if(i3 == 0)
                    {
                        break label1;
                    }
                    SplayTreeIterCodeStack$SplayTreeIterCodeStackData a5 = this.top;
                    String[] a6 = a5.code;
                    String s0 = a6[i1];
                    String s1 = SplayTreeIterCodeStack.COMMENT;
                    int i4 = s0.startsWith(s1)?1:0;
                    if(i4 == 0)
                    {
                        break;
                    }
                }
                int i5 = i1 + 1;
                i1 = i5;
            }
            SplayTreeIterCodeStack$SplayTreeIterCodeStackData a7 = this.top;
            String[] a8 = a7.code;
            int i6 = a8.length;
            if(i1 != i6)
            {
                SplayTreeIterCodeStack$SplayTreeIterCodeStackData a9 = this.top;
                a9.currentLineNumber = i1;
            }
            else
            {
                SplayTreeIterCodeStack$SplayTreeIterCodeStackData a10 = this.top;
                int i7 = i1 - 1;
                a10.currentLineNumber = i7;
            }
        }
    }
    
    public void setPosition(java.awt.Point a)
    {
        java.awt.Point a0 = this.position;
        int i = a.x;
        a0.x = i;
        java.awt.Point a1 = this.position;
        int i0 = a.y;
        a1.y = i0;
    }
    
    public int getWidth()
    {
        SplayTreeIterCodeStack$SplayTreeIterCodeStackData a = this.top;
        label0: {
            if(a == null)
            {
                break label0;
            }
            int i = this.width;
            SplayTreeIterCodeStack$SplayTreeIterCodeStackData a0 = this.top;
            int i0 = a0.getMaxWidth();
            if(i < i0)
            {
                SplayTreeIterCodeStack$SplayTreeIterCodeStackData a1 = this.top;
                int i1 = a1.getMaxWidth();
                this.width = i1;
            }
        }
        int i2 = this.width;
        return i2;
    }
    
    public void draw(java.awt.Graphics a)
    {
        int i = 0;
        java.awt.Point a0 = this.position;
        int i0 = a0.y;
        SplayTreeIterCodeStack$SplayTreeIterCodeStackData a1 = this.top;
        label0: {
            if(a1 == null)
            {
                i = i0;
                break label0;
            }
            SplayTreeIterCodeStack$SplayTreeIterCodeStackData a2 = this.top;
            java.awt.Point a3 = this.position;
            a2.setPosition(a3);
            SplayTreeIterCodeStack$SplayTreeIterCodeStackData a4 = this.top;
            a4.draw(a);
            SplayTreeIterCodeStack$SplayTreeIterCodeStackData a5 = this.top;
            if(a5 == null)
            {
                i = i0;
            }
            else
            {
                SplayTreeIterCodeStack$SplayTreeIterCodeStackData a6 = this.top;
                int i1 = a6.codeTop;
                i = i1;
            }
        }
        SplayTreeIterCodeStack$SplayTreeIterCodeStackData a7 = this.top;
        if(a7 != null)
        {
            String s = localization.Messages.getString("SplayTreeIterCodeStack.70");
            java.awt.Color a8 = SplayTreeIterColors.CODE_STACK_LABEL_COLOR;
            a.setColor(a8);
            java.awt.Point a9 = this.position;
            int i2 = a9.x;
            java.awt.FontMetrics a10 = a.getFontMetrics();
            int i3 = a10.stringWidth(s);
            int i4 = i2 - i3;
            int i5 = i4 - 20;
            java.awt.Point a11 = this.position;
            int i6 = a11.y;
            int i7 = i - i6;
            java.awt.FontMetrics a12 = a.getFontMetrics();
            int i8 = a12.getHeight();
            int i9 = i7 - i8;
            int i10 = i9 / 2;
            int i11 = i - i10;
            a.drawString(s, i5, i11);
            java.awt.Point a13 = this.position;
            int i12 = a13.x;
            int i13 = i12 - 10;
            java.awt.Point a14 = this.position;
            int i14 = a14.y;
            java.awt.Point a15 = this.position;
            int i15 = a15.x;
            int i16 = i15 - 5;
            java.awt.Point a16 = this.position;
            int i17 = a16.y;
            a.drawLine(i13, i14, i16, i17);
            java.awt.Point a17 = this.position;
            int i18 = a17.x;
            int i19 = i18 - 10;
            java.awt.Point a18 = this.position;
            int i20 = a18.y;
            java.awt.Point a19 = this.position;
            int i21 = a19.x;
            int i22 = i21 - 10;
            a.drawLine(i19, i20, i22, i);
            java.awt.Point a20 = this.position;
            int i23 = a20.x;
            int i24 = i23 - 10;
            java.awt.Point a21 = this.position;
            int i25 = a21.x;
            int i26 = i25 - 5;
            a.drawLine(i24, i, i26, i);
        }
    }
    
    static java.util.Hashtable access$000()
    {
        java.util.Hashtable a = SplayTreeIterCodeStack.codeHashtable;
        return a;
    }
    
    static SplayTreeIterCodeStack$SplayTreeIterCodeStackData access$100(SplayTreeIterCodeStack a)
    {
        SplayTreeIterCodeStack$SplayTreeIterCodeStackData a0 = a.top;
        return a0;
    }
    
    static String access$200()
    {
        String s = SplayTreeIterCodeStack.COMMENT;
        return s;
    }
    
    static
    {
        String s = localization.Messages.getString("SplayTreeIterCodeStack.0");
        SplayTreeIterCodeStack.COMMENT = s;
        String[][][] a = new String[9][][];
        String[][] a0 = new String[2][];
        String[] a1 = new String[1];
        String s0 = localization.Messages.getString("SplayTreeIterCodeStack.1");
        a1[0] = s0;
        a0[0] = a1;
        String[] a2 = new String[4];
        String s1 = localization.Messages.getString("SplayTreeIterCodeStack.2");
        a2[0] = s1;
        String s2 = localization.Messages.getString("SplayTreeIterCodeStack.3");
        a2[1] = s2;
        String s3 = localization.Messages.getString("SplayTreeIterCodeStack.4");
        a2[2] = s3;
        String s4 = localization.Messages.getString("SplayTreeIterCodeStack.5");
        a2[3] = s4;
        a0[1] = a2;
        a[0] = a0;
        String[][] a3 = new String[2][];
        String[] a4 = new String[1];
        String s5 = localization.Messages.getString("SplayTreeIterCodeStack.6");
        a4[0] = s5;
        a3[0] = a4;
        String[] a5 = new String[4];
        String s6 = localization.Messages.getString("SplayTreeIterCodeStack.7");
        a5[0] = s6;
        String s7 = localization.Messages.getString("SplayTreeIterCodeStack.8");
        a5[1] = s7;
        String s8 = localization.Messages.getString("SplayTreeIterCodeStack.9");
        a5[2] = s8;
        String s9 = localization.Messages.getString("SplayTreeIterCodeStack.10");
        a5[3] = s9;
        a3[1] = a5;
        a[1] = a3;
        String[][] a6 = new String[2][];
        String[] a7 = new String[1];
        String s10 = localization.Messages.getString("SplayTreeIterCodeStack.11");
        a7[0] = s10;
        a6[0] = a7;
        String[] a8 = new String[4];
        String s11 = localization.Messages.getString("SplayTreeIterCodeStack.12");
        a8[0] = s11;
        String s12 = localization.Messages.getString("SplayTreeIterCodeStack.13");
        a8[1] = s12;
        String s13 = localization.Messages.getString("SplayTreeIterCodeStack.14");
        a8[2] = s13;
        String s14 = localization.Messages.getString("SplayTreeIterCodeStack.15");
        a8[3] = s14;
        a6[1] = a8;
        a[2] = a6;
        String[][] a9 = new String[2][];
        String[] a10 = new String[1];
        String s15 = localization.Messages.getString("SplayTreeIterCodeStack.16");
        a10[0] = s15;
        a9[0] = a10;
        String[] a11 = new String[4];
        String s16 = localization.Messages.getString("SplayTreeIterCodeStack.17");
        a11[0] = s16;
        String s17 = localization.Messages.getString("SplayTreeIterCodeStack.18");
        a11[1] = s17;
        String s18 = localization.Messages.getString("SplayTreeIterCodeStack.19");
        a11[2] = s18;
        String s19 = localization.Messages.getString("SplayTreeIterCodeStack.20");
        a11[3] = s19;
        a9[1] = a11;
        a[3] = a9;
        String[][] a12 = new String[2][];
        String[] a13 = new String[1];
        String s20 = localization.Messages.getString("SplayTreeIterCodeStack.21");
        a13[0] = s20;
        a12[0] = a13;
        String[] a14 = new String[4];
        String s21 = localization.Messages.getString("SplayTreeIterCodeStack.22");
        a14[0] = s21;
        String s22 = localization.Messages.getString("SplayTreeIterCodeStack.23");
        a14[1] = s22;
        String s23 = localization.Messages.getString("SplayTreeIterCodeStack.24");
        a14[2] = s23;
        String s24 = localization.Messages.getString("SplayTreeIterCodeStack.25");
        a14[3] = s24;
        a12[1] = a14;
        a[4] = a12;
        String[][] a15 = new String[2][];
        String[] a16 = new String[1];
        String s25 = localization.Messages.getString("SplayTreeIterCodeStack.26");
        a16[0] = s25;
        a15[0] = a16;
        String[] a17 = new String[4];
        String s26 = localization.Messages.getString("SplayTreeIterCodeStack.27");
        a17[0] = s26;
        String s27 = localization.Messages.getString("SplayTreeIterCodeStack.28");
        a17[1] = s27;
        String s28 = localization.Messages.getString("SplayTreeIterCodeStack.29");
        a17[2] = s28;
        String s29 = localization.Messages.getString("SplayTreeIterCodeStack.30");
        a17[3] = s29;
        a15[1] = a17;
        a[5] = a15;
        String[][] a18 = new String[2][];
        String[] a19 = new String[1];
        String s30 = localization.Messages.getString("SplayTreeIterCodeStack.31");
        a19[0] = s30;
        a18[0] = a19;
        String[] a20 = new String[4];
        String s31 = localization.Messages.getString("SplayTreeIterCodeStack.32");
        a20[0] = s31;
        String s32 = localization.Messages.getString("SplayTreeIterCodeStack.33");
        a20[1] = s32;
        String s33 = localization.Messages.getString("SplayTreeIterCodeStack.34");
        a20[2] = s33;
        String s34 = localization.Messages.getString("SplayTreeIterCodeStack.35");
        a20[3] = s34;
        a18[1] = a20;
        a[6] = a18;
        String[][] a21 = new String[2][];
        String[] a22 = new String[1];
        String s35 = localization.Messages.getString("SplayTreeIterCodeStack.36");
        a22[0] = s35;
        a21[0] = a22;
        String[] a23 = new String[4];
        String s36 = localization.Messages.getString("SplayTreeIterCodeStack.37");
        a23[0] = s36;
        String s37 = localization.Messages.getString("SplayTreeIterCodeStack.38");
        a23[1] = s37;
        String s38 = localization.Messages.getString("SplayTreeIterCodeStack.39");
        a23[2] = s38;
        String s39 = localization.Messages.getString("SplayTreeIterCodeStack.40");
        a23[3] = s39;
        a21[1] = a23;
        a[7] = a21;
        String[][] a24 = new String[2][];
        String[] a25 = new String[1];
        String s40 = localization.Messages.getString("SplayTreeIterCodeStack.41");
        a25[0] = s40;
        a24[0] = a25;
        String[] a26 = new String[4];
        String s41 = localization.Messages.getString("SplayTreeIterCodeStack.42");
        a26[0] = s41;
        String s42 = localization.Messages.getString("SplayTreeIterCodeStack.43");
        a26[1] = s42;
        String s43 = localization.Messages.getString("SplayTreeIterCodeStack.44");
        a26[2] = s43;
        String s44 = localization.Messages.getString("SplayTreeIterCodeStack.45");
        a26[3] = s44;
        a24[1] = a26;
        a[8] = a24;
        SplayTreeIterCodeStack.allCode = a;
        String[] a27 = new String[2];
        String s45 = localization.Messages.getString("SplayTreeIterCodeStack.46");
        a27[0] = s45;
        String s46 = localization.Messages.getString("SplayTreeIterCodeStack.47");
        a27[1] = s46;
        SplayTreeIterCodeStack.searchCode = a27;
        String[] a28 = new String[4];
        String s47 = localization.Messages.getString("SplayTreeIterCodeStack.48");
        a28[0] = s47;
        String s48 = localization.Messages.getString("SplayTreeIterCodeStack.49");
        a28[1] = s48;
        String s49 = localization.Messages.getString("SplayTreeIterCodeStack.50");
        a28[2] = s49;
        String s50 = localization.Messages.getString("SplayTreeIterCodeStack.51");
        a28[3] = s50;
        SplayTreeIterCodeStack.zigZigSearchCode = a28;
        String[] a29 = new String[4];
        String s51 = localization.Messages.getString("SplayTreeIterCodeStack.52");
        a29[0] = s51;
        String s52 = localization.Messages.getString("SplayTreeIterCodeStack.53");
        a29[1] = s52;
        String s53 = localization.Messages.getString("SplayTreeIterCodeStack.54");
        a29[2] = s53;
        String s54 = localization.Messages.getString("SplayTreeIterCodeStack.55");
        a29[3] = s54;
        SplayTreeIterCodeStack.zigZagSearchCode = a29;
        String[] a30 = new String[2];
        String s55 = localization.Messages.getString("SplayTreeIterCodeStack.56");
        a30[0] = s55;
        String s56 = localization.Messages.getString("SplayTreeIterCodeStack.57");
        a30[1] = s56;
        SplayTreeIterCodeStack.insertCode = a30;
        String[] a31 = new String[3];
        String s57 = localization.Messages.getString("SplayTreeIterCodeStack.58");
        a31[0] = s57;
        String s58 = localization.Messages.getString("SplayTreeIterCodeStack.59");
        a31[1] = s58;
        String s59 = localization.Messages.getString("SplayTreeIterCodeStack.60");
        a31[2] = s59;
        SplayTreeIterCodeStack.terminalCode = a31;
        String[] a32 = new String[4];
        String s60 = localization.Messages.getString("SplayTreeIterCodeStack.61");
        a32[0] = s60;
        String s61 = localization.Messages.getString("SplayTreeIterCodeStack.62");
        a32[1] = s61;
        String s62 = localization.Messages.getString("SplayTreeIterCodeStack.63");
        a32[2] = s62;
        String s63 = localization.Messages.getString("SplayTreeIterCodeStack.64");
        a32[3] = s63;
        SplayTreeIterCodeStack.zigZigCode = a32;
        String[] a33 = new String[4];
        String s64 = localization.Messages.getString("SplayTreeIterCodeStack.65");
        a33[0] = s64;
        String s65 = localization.Messages.getString("SplayTreeIterCodeStack.66");
        a33[1] = s65;
        String s66 = localization.Messages.getString("SplayTreeIterCodeStack.67");
        a33[2] = s66;
        String s67 = localization.Messages.getString("SplayTreeIterCodeStack.68");
        a33[3] = s67;
        SplayTreeIterCodeStack.zigZagCode = a33;
        java.util.Hashtable a34 = new java.util.Hashtable();
        SplayTreeIterCodeStack.codeHashtable = a34;
        int i = 0;
        while(true)
        {
            String[][][] a35 = SplayTreeIterCodeStack.allCode;
            int i0 = a35.length;
            if(i >= i0)
            {
                return;
            }
            String[][][] a36 = SplayTreeIterCodeStack.allCode;
            String[][] a37 = a36[i];
            int i1 = a37.length;
            label0: {
                if(i1 == 2)
                {
                    break label0;
                }
                String[][][] a38 = SplayTreeIterCodeStack.allCode;
                String[][] a39 = a38[i];
                String[] a40 = a39[0];
                int i2 = a40.length;
                if(i2 != 1)
                {
                    String s68 = localization.Messages.getString("SplayTreeIterCodeStack.69");
                    RuntimeException a41 = new RuntimeException(s68);
                    throw a41;
                }
            }
            java.util.Hashtable a42 = SplayTreeIterCodeStack.codeHashtable;
            String[][][] a43 = SplayTreeIterCodeStack.allCode;
            String[][] a44 = a43[i];
            String[] a45 = a44[0];
            String s69 = a45[0];
            String[][][] a46 = SplayTreeIterCodeStack.allCode;
            String[][] a47 = a46[i];
            String[] a48 = a47[1];
            Object a49 = a42.put((Object)s69, (Object)a48);
            int i3 = i + 1;
            i = i3;
        }
    }
}