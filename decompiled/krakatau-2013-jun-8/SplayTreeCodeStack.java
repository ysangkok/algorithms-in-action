public class SplayTreeCodeStack {
    private SplayTreeCodeStack$SplayTreeCodeStackData top;
    private java.awt.Point position;
    private int width;
    private static java.util.Hashtable codeHashtable;
    final private static String COMMENT = "//";
    final public static int gap = 10;
    final private static String[][][] allCode;
    
    public SplayTreeCodeStack()
    {
        super();
        java.awt.Point a = new java.awt.Point();
        this.position = a;
        this.top = null;
    }
    
    public void setCode(String s)
    {
        SplayTreeCodeStack$SplayTreeCodeStackData a = this.top;
        a.setCode(s);
    }
    
    public void push(String s)
    {
        SplayTreeCodeStack$SplayTreeCodeStackData a = new SplayTreeCodeStack$SplayTreeCodeStackData(this, s);
        SplayTreeCodeStack$SplayTreeCodeStackData a0 = this.top;
        a.nextOnStack = a0;
        this.top = a;
    }
    
    public boolean isEmpty()
    {
        SplayTreeCodeStack$SplayTreeCodeStackData a = this.top;
        int i = a != null?0:1;
        return i != 0;
    }
    
    public void pop()
    {
        SplayTreeCodeStack$SplayTreeCodeStackData a = this.top;
        SplayTreeCodeStack$SplayTreeCodeStackData a0 = a.nextOnStack;
        this.top = a0;
    }
    
    private void setCurrentLineNumber(int i)
    {
        SplayTreeCodeStack$SplayTreeCodeStackData a = this.top;
        if(a != null)
        {
            SplayTreeCodeStack$SplayTreeCodeStackData a0 = this.top;
            a0.currentLineNumber = i;
        }
    }
    
    public void stepCodeLine()
    {
        SplayTreeCodeStack$SplayTreeCodeStackData a = this.top;
        label0: {
            if(a == null)
            {
                break label0;
            }
            SplayTreeCodeStack$SplayTreeCodeStackData a0 = this.top;
            int i = a0.currentLineNumber;
            int i0 = i + 1;
            int i1 = i0;
            while(true)
            {
                SplayTreeCodeStack$SplayTreeCodeStackData a1 = this.top;
                String[] a2 = a1.code;
                int i2 = a2.length;
                if(i1 >= i2)
                {
                    break;
                }
                SplayTreeCodeStack$SplayTreeCodeStackData a3 = this.top;
                String[] a4 = a3.code;
                String s = a4[i1];
                int i3 = s.length();
                label1: {
                    if(i3 == 0)
                    {
                        break label1;
                    }
                    SplayTreeCodeStack$SplayTreeCodeStackData a5 = this.top;
                    String[] a6 = a5.code;
                    String s0 = a6[i1];
                    int i4 = s0.startsWith("//")?1:0;
                    if(i4 == 0)
                    {
                        break;
                    }
                }
                int i5 = i1 + 1;
                i1 = i5;
            }
            SplayTreeCodeStack$SplayTreeCodeStackData a7 = this.top;
            String[] a8 = a7.code;
            int i6 = a8.length;
            if(i1 != i6)
            {
                SplayTreeCodeStack$SplayTreeCodeStackData a9 = this.top;
                a9.currentLineNumber = i1;
            }
            else
            {
                SplayTreeCodeStack$SplayTreeCodeStackData a10 = this.top;
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
        SplayTreeCodeStack$SplayTreeCodeStackData a = this.top;
        label0: {
            if(a == null)
            {
                break label0;
            }
            int i = this.width;
            SplayTreeCodeStack$SplayTreeCodeStackData a0 = this.top;
            int i0 = a0.getMaxWidth();
            if(i < i0)
            {
                SplayTreeCodeStack$SplayTreeCodeStackData a1 = this.top;
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
        SplayTreeCodeStack$SplayTreeCodeStackData a1 = this.top;
        label0: {
            if(a1 == null)
            {
                i = i0;
                break label0;
            }
            SplayTreeCodeStack$SplayTreeCodeStackData a2 = this.top;
            java.awt.Point a3 = this.position;
            a2.setPosition(a3);
            SplayTreeCodeStack$SplayTreeCodeStackData a4 = this.top;
            a4.draw(a);
            SplayTreeCodeStack$SplayTreeCodeStackData a5 = this.top;
            if(a5 == null)
            {
                i = i0;
            }
            else
            {
                SplayTreeCodeStack$SplayTreeCodeStackData a6 = this.top;
                int i1 = a6.codeTop;
                i = i1;
            }
        }
        SplayTreeCodeStack$SplayTreeCodeStackData a7 = this.top;
        if(a7 != null)
        {
            String s = localization.Messages.getString("SplayTreeCodeStack.47");
            java.awt.Color a8 = SplayTreeColors.CODE_STACK_LABEL_COLOR;
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
        java.util.Hashtable a = SplayTreeCodeStack.codeHashtable;
        return a;
    }
    
    static SplayTreeCodeStack$SplayTreeCodeStackData access$100(SplayTreeCodeStack a)
    {
        SplayTreeCodeStack$SplayTreeCodeStackData a0 = a.top;
        return a0;
    }
    
    static
    {
        String[][][] a = new String[9][][];
        String[][] a0 = new String[2][];
        String[] a1 = new String[1];
        String s = localization.Messages.getString("SplayTreeCodeStack.1");
        a1[0] = s;
        a0[0] = a1;
        String[] a2 = new String[4];
        String s0 = localization.Messages.getString("SplayTreeCodeStack.2");
        a2[0] = s0;
        String s1 = localization.Messages.getString("SplayTreeCodeStack.3");
        a2[1] = s1;
        String s2 = localization.Messages.getString("SplayTreeCodeStack.4");
        a2[2] = s2;
        String s3 = localization.Messages.getString("SplayTreeCodeStack.5");
        a2[3] = s3;
        a0[1] = a2;
        a[0] = a0;
        String[][] a3 = new String[2][];
        String[] a4 = new String[1];
        String s4 = localization.Messages.getString("SplayTreeCodeStack.6");
        a4[0] = s4;
        a3[0] = a4;
        String[] a5 = new String[4];
        String s5 = localization.Messages.getString("SplayTreeCodeStack.7");
        a5[0] = s5;
        String s6 = localization.Messages.getString("SplayTreeCodeStack.8");
        a5[1] = s6;
        a5[2] = "";
        String s7 = localization.Messages.getString("SplayTreeCodeStack.10");
        a5[3] = s7;
        a3[1] = a5;
        a[1] = a3;
        String[][] a6 = new String[2][];
        String[] a7 = new String[1];
        String s8 = localization.Messages.getString("SplayTreeCodeStack.11");
        a7[0] = s8;
        a6[0] = a7;
        String[] a8 = new String[4];
        String s9 = localization.Messages.getString("SplayTreeCodeStack.12");
        a8[0] = s9;
        String s10 = localization.Messages.getString("SplayTreeCodeStack.13");
        a8[1] = s10;
        String s11 = localization.Messages.getString("SplayTreeCodeStack.14");
        a8[2] = s11;
        String s12 = localization.Messages.getString("SplayTreeCodeStack.15");
        a8[3] = s12;
        a6[1] = a8;
        a[2] = a6;
        String[][] a9 = new String[2][];
        String[] a10 = new String[1];
        String s13 = localization.Messages.getString("SplayTreeCodeStack.16");
        a10[0] = s13;
        a9[0] = a10;
        String[] a11 = new String[4];
        String s14 = localization.Messages.getString("SplayTreeCodeStack.17");
        a11[0] = s14;
        String s15 = localization.Messages.getString("SplayTreeCodeStack.18");
        a11[1] = s15;
        String s16 = localization.Messages.getString("SplayTreeCodeStack.19");
        a11[2] = s16;
        String s17 = localization.Messages.getString("SplayTreeCodeStack.20");
        a11[3] = s17;
        a9[1] = a11;
        a[3] = a9;
        String[][] a12 = new String[2][];
        String[] a13 = new String[1];
        String s18 = localization.Messages.getString("SplayTreeCodeStack.21");
        a13[0] = s18;
        a12[0] = a13;
        String[] a14 = new String[4];
        String s19 = localization.Messages.getString("SplayTreeCodeStack.22");
        a14[0] = s19;
        String s20 = localization.Messages.getString("SplayTreeCodeStack.23");
        a14[1] = s20;
        String s21 = localization.Messages.getString("SplayTreeCodeStack.24");
        a14[2] = s21;
        String s22 = localization.Messages.getString("SplayTreeCodeStack.25");
        a14[3] = s22;
        a12[1] = a14;
        a[4] = a12;
        String[][] a15 = new String[2][];
        String[] a16 = new String[1];
        String s23 = localization.Messages.getString("SplayTreeCodeStack.26");
        a16[0] = s23;
        a15[0] = a16;
        String[] a17 = new String[4];
        String s24 = localization.Messages.getString("SplayTreeCodeStack.27");
        a17[0] = s24;
        String s25 = localization.Messages.getString("SplayTreeCodeStack.28");
        a17[1] = s25;
        String s26 = localization.Messages.getString("SplayTreeCodeStack.29");
        a17[2] = s26;
        String s27 = localization.Messages.getString("SplayTreeCodeStack.30");
        a17[3] = s27;
        a15[1] = a17;
        a[5] = a15;
        String[][] a18 = new String[2][];
        String[] a19 = new String[1];
        String s28 = localization.Messages.getString("SplayTreeCodeStack.31");
        a19[0] = s28;
        a18[0] = a19;
        String[] a20 = new String[4];
        String s29 = localization.Messages.getString("SplayTreeCodeStack.32");
        a20[0] = s29;
        String s30 = localization.Messages.getString("SplayTreeCodeStack.33");
        a20[1] = s30;
        a20[2] = "";
        String s31 = localization.Messages.getString("SplayTreeCodeStack.35");
        a20[3] = s31;
        a18[1] = a20;
        a[6] = a18;
        String[][] a21 = new String[2][];
        String[] a22 = new String[1];
        String s32 = localization.Messages.getString("SplayTreeCodeStack.36");
        a22[0] = s32;
        a21[0] = a22;
        String[] a23 = new String[4];
        String s33 = localization.Messages.getString("SplayTreeCodeStack.37");
        a23[0] = s33;
        String s34 = localization.Messages.getString("SplayTreeCodeStack.38");
        a23[1] = s34;
        String s35 = localization.Messages.getString("SplayTreeCodeStack.39");
        a23[2] = s35;
        String s36 = localization.Messages.getString("SplayTreeCodeStack.40");
        a23[3] = s36;
        a21[1] = a23;
        a[7] = a21;
        String[][] a24 = new String[2][];
        String[] a25 = new String[1];
        String s37 = localization.Messages.getString("SplayTreeCodeStack.41");
        a25[0] = s37;
        a24[0] = a25;
        String[] a26 = new String[4];
        String s38 = localization.Messages.getString("SplayTreeCodeStack.42");
        a26[0] = s38;
        String s39 = localization.Messages.getString("SplayTreeCodeStack.43");
        a26[1] = s39;
        String s40 = localization.Messages.getString("SplayTreeCodeStack.44");
        a26[2] = s40;
        String s41 = localization.Messages.getString("SplayTreeCodeStack.45");
        a26[3] = s41;
        a24[1] = a26;
        a[8] = a24;
        SplayTreeCodeStack.allCode = a;
        java.util.Hashtable a27 = new java.util.Hashtable();
        SplayTreeCodeStack.codeHashtable = a27;
        int i = 0;
        while(true)
        {
            String[][][] a28 = SplayTreeCodeStack.allCode;
            int i0 = a28.length;
            if(i >= i0)
            {
                return;
            }
            String[][][] a29 = SplayTreeCodeStack.allCode;
            String[][] a30 = a29[i];
            int i1 = a30.length;
            label0: {
                if(i1 == 2)
                {
                    break label0;
                }
                String[][][] a31 = SplayTreeCodeStack.allCode;
                String[][] a32 = a31[i];
                String[] a33 = a32[0];
                int i2 = a33.length;
                if(i2 != 1)
                {
                    RuntimeException a34 = new RuntimeException("SplayTreeCodeStack: Invalid code");
                    throw a34;
                }
            }
            java.util.Hashtable a35 = SplayTreeCodeStack.codeHashtable;
            String[][][] a36 = SplayTreeCodeStack.allCode;
            String[][] a37 = a36[i];
            String[] a38 = a37[0];
            String s42 = a38[0];
            String[][][] a39 = SplayTreeCodeStack.allCode;
            String[][] a40 = a39[i];
            String[] a41 = a40[1];
            Object a42 = a35.put((Object)s42, (Object)a41);
            int i3 = i + 1;
            i = i3;
        }
    }
}