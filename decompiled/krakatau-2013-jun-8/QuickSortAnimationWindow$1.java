class QuickSortAnimationWindow$1 implements java.awt.event.ActionListener {
    final QuickSortAnimationWindow this$0;
    
    QuickSortAnimationWindow$1(QuickSortAnimationWindow a)
    {
        this.this$0 = a;
        super();
    }
    
    public void actionPerformed(java.awt.event.ActionEvent a)
    {
        String s = a.paramString();
        int i = s.indexOf("cmd=");
        int i0 = i + 4;
        int i1 = s.indexOf(",", i0);
        String s0 = s.substring(i0, i1);
        com.cim.AIA.Log a0 = new com.cim.AIA.Log((byte)4, (byte)21, (String)null, s0);
        QuickSortAnimationWindow a1 = this.this$0;
        com.cim.AIA.Logger a2 = a1.getLogger();
        if(a2 != null)
        {
            QuickSortAnimationWindow a3 = this.this$0;
            com.cim.AIA.Logger a4 = a3.getLogger();
            a4.addLog(a0);
        }
        int i2 = com.cim.AIA.Logger.DEBUG?1:0;
        if(i2 != 0)
        {
            java.io.PrintStream a5 = System.err;
            a5.println("Partition method action listener called.");
            java.io.PrintStream a6 = System.err;
            StringBuilder a7 = new StringBuilder();
            StringBuilder a8 = a7.append("paramString=");
            String s1 = a.paramString();
            StringBuilder a9 = a8.append(s1);
            String s2 = a9.toString();
            a6.println(s2);
            java.io.PrintStream a10 = System.err;
            StringBuilder a11 = new StringBuilder();
            StringBuilder a12 = a11.append("cmd=");
            StringBuilder a13 = a12.append(s0);
            String s3 = a13.toString();
            a10.println(s3);
        }
    }
}