package com.cim.AIA;

class DataSelection$1 implements java.awt.event.ItemListener {
    final String val$name;
    final com.cim.AIA.DataSelection this$0;
    
    DataSelection$1(com.cim.AIA.DataSelection a, String s)
    {
        this.this$0 = a;
        this.val$name = s;
        super();
    }
    
    public void itemStateChanged(java.awt.event.ItemEvent a)
    {
        com.cim.AIA.DataSelection a0 = this.this$0;
        com.cim.AIA.AnimationWindow a1 = a0.animationWindow;
        a1.resetDataSelectionCheckBoxes();
        com.cim.AIA.DataSelection a2 = this.this$0;
        Object a3 = a2.getData();
        int i = ((com.cim.AIA.Copyable)a3).isEmpty()?1:0;
        if(i == 0)
        {
            com.cim.AIA.DataSelection a4 = this.this$0;
            a4.setState(true);
            com.cim.AIA.DataSelection a5 = this.this$0;
            com.cim.AIA.AnimationWindow a6 = a5.animationWindow;
            com.cim.AIA.DataSelection a7 = this.this$0;
            int i0 = a7.reInitialiseAlgorithm?1:0;
            com.cim.AIA.DataSelection a8 = this.this$0;
            int i1 = a8.clearAlgorithmState?1:0;
            a6.setCurrentData((com.cim.AIA.Copyable)a3, i0 != 0, i1 != 0);
        }
        String s = a.paramString();
        int i2 = s.indexOf("item=");
        int i3 = i2 + 5;
        int i4 = s.indexOf(",", i3);
        String s0 = s.substring(i3, i4);
        String s1 = this.val$name;
        com.cim.AIA.Log a9 = new com.cim.AIA.Log((byte)4, (byte)9, (String)null, s1);
        com.cim.AIA.DataSelection a10 = this.this$0;
        com.cim.AIA.AnimationWindow a11 = a10.animationWindow;
        com.cim.AIA.AlgorithmThread a12 = a11.getThread();
        com.cim.AIA.Logger a13 = a12.getLogger();
        if(a13 != null)
        {
            com.cim.AIA.DataSelection a14 = this.this$0;
            com.cim.AIA.AnimationWindow a15 = a14.animationWindow;
            com.cim.AIA.AlgorithmThread a16 = a15.getThread();
            com.cim.AIA.Logger a17 = a16.getLogger();
            a17.addLog(a9);
        }
        int i5 = com.cim.AIA.Logger.DEBUG?1:0;
        if(i5 != 0)
        {
            java.io.PrintStream a18 = System.err;
            StringBuilder a19 = new StringBuilder();
            StringBuilder a20 = a19.append("Logging data selection item=");
            StringBuilder a21 = a20.append(s0);
            String s2 = a21.toString();
            a18.println(s2);
        }
    }
}