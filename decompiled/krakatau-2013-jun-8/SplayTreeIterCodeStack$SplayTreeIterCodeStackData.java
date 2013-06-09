class SplayTreeIterCodeStack$SplayTreeIterCodeStackData {
    protected int currentLineNumber;
    protected SplayTreeIterCodeStack$SplayTreeIterCodeStackData nextOnStack;
    protected String[] code;
    protected java.awt.Point position;
    protected int codeTop;
    protected int codeBlockWidth;
    final SplayTreeIterCodeStack this$0;
    
    protected SplayTreeIterCodeStack$SplayTreeIterCodeStackData(SplayTreeIterCodeStack a, String s)
    {
        this.this$0 = a;
        super();
        java.awt.Point a0 = new java.awt.Point();
        this.position = a0;
        this.setCode(s);
    }
    
    protected void setCode(String s)
    {
        java.util.Hashtable a = SplayTreeIterCodeStack.access$000();
        Object a0 = a.get((Object)s);
        String[] dummy = (String[])a0;
        String[] a1 = (String[])a0;
        this.code = a1;
        String[] a2 = this.code;
        if(a2 != null)
        {
            this.currentLineNumber = 0;
            return;
        }
        else
        {
            StringBuilder a3 = new StringBuilder();
            String s0 = localization.Messages.getString("SplayTreeIterCodeStack.71");
            StringBuilder a4 = a3.append(s0);
            StringBuilder a5 = a4.append(s);
            String s1 = localization.Messages.getString("SplayTreeIterCodeStack.72");
            StringBuilder a6 = a5.append(s1);
            String s2 = a6.toString();
            RuntimeException a7 = new RuntimeException(s2);
            throw a7;
        }
    }
    
    protected void draw(java.awt.Graphics a)
    {
        int i = 0;
        java.awt.Point a0 = this.position;
        int i0 = a0.y;
        java.awt.FontMetrics a1 = a.getFontMetrics();
        int i1 = a1.getHeight();
        SplayTreeIterCodeStack$SplayTreeIterCodeStackData a2 = this.nextOnStack;
        if(a2 == null)
        {
            i = i0;
        }
        else
        {
            SplayTreeIterCodeStack$SplayTreeIterCodeStackData a3 = this.nextOnStack;
            java.awt.Point a4 = this.position;
            int i2 = a4.x;
            java.awt.Point a5 = new java.awt.Point(i2, i0);
            a3.setPosition(a5);
            SplayTreeIterCodeStack$SplayTreeIterCodeStackData a6 = this.nextOnStack;
            a6.draw(a);
            SplayTreeIterCodeStack$SplayTreeIterCodeStackData a7 = this.nextOnStack;
            int i3 = a7.codeTop;
            int i4 = i3 - 10;
            java.awt.Point a8 = this.position;
            a8.y = i4;
            i = i4;
        }
        String[] a9 = this.code;
        int i5 = a9.length;
        int i6 = i5 - 1;
        int i7 = i;
        int i8 = i6;
        while(true)
        {
            if(i8 < 0)
            {
                this.codeTop = i7;
                java.awt.Color a10 = SplayTreeIterColors.CODE_STACK_BOX_COLOR;
                a.setColor(a10);
                java.awt.Point a11 = this.position;
                int i9 = a11.x;
                int i10 = this.codeBlockWidth;
                java.awt.Point a12 = this.position;
                int i11 = a12.y;
                int i12 = i11 - i7;
                a.drawRect(i9, i7, i10, i12);
                return;
            }
            java.awt.FontMetrics a13 = a.getFontMetrics();
            String[] a14 = this.code;
            String s = a14[i8];
            int i13 = a13.stringWidth(s);
            int i14 = this.codeBlockWidth;
            if(i13 > i14)
            {
                java.awt.FontMetrics a15 = a.getFontMetrics();
                String[] a16 = this.code;
                String s0 = a16[i8];
                int i15 = a15.stringWidth(s0);
                this.codeBlockWidth = i15;
            }
            int i16 = this.currentLineNumber;
            label0: {
                if(i8 != i16)
                {
                    break label0;
                }
                SplayTreeIterCodeStack a17 = this.this$0;
                SplayTreeIterCodeStack$SplayTreeIterCodeStackData a18 = SplayTreeIterCodeStack.access$100(a17);
                if(this != a18)
                {
                    java.awt.Color a19 = SplayTreeIterColors.CODE_STACK_SUSPENDED_COLOR;
                    a.setColor(a19);
                }
                else
                {
                    java.awt.Color a20 = SplayTreeIterColors.CODE_STACK_ACTIVE_COLOR;
                    a.setColor(a20);
                }
                java.awt.Point a21 = this.position;
                int i17 = a21.x;
                int i18 = i7 - i1;
                int i19 = this.codeBlockWidth;
                a.fillRect(i17, i18, i19, i1);
            }
            String[] a22 = this.code;
            String s1 = a22[i8];
            String s2 = SplayTreeIterCodeStack.access$200();
            int i20 = s1.startsWith(s2)?1:0;
            if(i20 == 0)
            {
                java.awt.Color a23 = SplayTreeIterColors.CODE_STACK_CODE_COLOR;
                a.setColor(a23);
            }
            else
            {
                java.awt.Color a24 = SplayTreeIterColors.CODE_STACK_COMMENT_COLOR;
                a.setColor(a24);
            }
            String[] a25 = this.code;
            String s3 = a25[i8];
            java.awt.Point a26 = this.position;
            int i21 = a26.x;
            a.drawString(s3, i21, i7);
            int i22 = i7 - i1;
            int i23 = i8 + -1;
            i7 = i22;
            i8 = i23;
        }
    }
    
    protected void setPosition(java.awt.Point a)
    {
        java.awt.Point a0 = this.position;
        int i = a.x;
        a0.x = i;
        java.awt.Point a1 = this.position;
        int i0 = a.y;
        a1.y = i0;
    }
    
    protected int getMaxWidth()
    {
        int i = 0;
        SplayTreeIterCodeStack$SplayTreeIterCodeStackData a = this.nextOnStack;
        label1: {
            label0: {
                if(a != null)
                {
                    break label0;
                }
                int i0 = this.codeBlockWidth;
                i = i0;
                break label1;
            }
            SplayTreeIterCodeStack$SplayTreeIterCodeStackData a0 = this.nextOnStack;
            int i1 = a0.getMaxWidth();
            int i2 = this.codeBlockWidth;
            if(i2 <= i1)
            {
                i = i1;
            }
            else
            {
                int i3 = this.codeBlockWidth;
                i = i3;
            }
        }
        return i;
    }
}