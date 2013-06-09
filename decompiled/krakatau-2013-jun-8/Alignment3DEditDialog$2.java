class Alignment3DEditDialog$2 implements java.awt.event.ActionListener {
    final Alignment3DEditDialog this$0;
    
    Alignment3DEditDialog$2(Alignment3DEditDialog a)
    {
        this.this$0 = a;
        super();
    }
    
    public void actionPerformed(java.awt.event.ActionEvent a)
    {
        java.io.PrintStream a0 = System.out;
        StringBuilder a1 = new StringBuilder();
        String s = localization.Messages.getString("Alignment3DEditDialog.2");
        StringBuilder a2 = a1.append(s);
        Alignment3DEditDialog a3 = this.this$0;
        java.awt.Scrollbar a4 = a3.xScroll;
        int i = a4.getValue();
        StringBuilder a5 = a2.append(i);
        String s0 = a5.toString();
        a0.println(s0);
        java.io.PrintStream a6 = System.out;
        StringBuilder a7 = new StringBuilder();
        String s1 = localization.Messages.getString("Alignment3DEditDialog.3");
        StringBuilder a8 = a7.append(s1);
        Alignment3DEditDialog a9 = this.this$0;
        java.awt.Scrollbar a10 = a9.yScroll;
        int i0 = a10.getValue();
        StringBuilder a11 = a8.append(i0);
        String s2 = a11.toString();
        a6.println(s2);
        java.io.PrintStream a12 = System.out;
        StringBuilder a13 = new StringBuilder();
        String s3 = localization.Messages.getString("Alignment3DEditDialog.4");
        StringBuilder a14 = a13.append(s3);
        Alignment3DEditDialog a15 = this.this$0;
        java.awt.Scrollbar a16 = a15.zScroll;
        int i1 = a16.getValue();
        StringBuilder a17 = a14.append(i1);
        String s4 = a17.toString();
        a12.println(s4);
        java.io.PrintStream a18 = System.out;
        StringBuilder a19 = new StringBuilder();
        String s5 = localization.Messages.getString("Alignment3DEditDialog.5");
        StringBuilder a20 = a19.append(s5);
        Alignment3DEditDialog a21 = this.this$0;
        java.awt.Scrollbar a22 = a21.xTranslate;
        int i2 = a22.getValue();
        StringBuilder a23 = a20.append(i2);
        String s6 = a23.toString();
        a18.println(s6);
        java.io.PrintStream a24 = System.out;
        StringBuilder a25 = new StringBuilder();
        String s7 = localization.Messages.getString("Alignment3DEditDialog.6");
        StringBuilder a26 = a25.append(s7);
        Alignment3DEditDialog a27 = this.this$0;
        java.awt.Scrollbar a28 = a27.yTranslate;
        int i3 = a28.getValue();
        StringBuilder a29 = a26.append(i3);
        String s8 = a29.toString();
        a24.println(s8);
        java.io.PrintStream a30 = System.out;
        StringBuilder a31 = new StringBuilder();
        String s9 = localization.Messages.getString("Alignment3DEditDialog.7");
        StringBuilder a32 = a31.append(s9);
        Alignment3DEditDialog a33 = this.this$0;
        java.awt.Scrollbar a34 = a33.zTranslate;
        int i4 = a34.getValue();
        StringBuilder a35 = a32.append(i4);
        String s10 = a35.toString();
        a30.println(s10);
    }
}