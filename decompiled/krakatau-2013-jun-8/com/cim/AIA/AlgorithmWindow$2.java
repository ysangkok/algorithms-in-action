package com.cim.AIA;

class AlgorithmWindow$2 implements java.awt.event.ActionListener {
    final com.cim.AIA.AlgorithmWindow this$0;
    
    AlgorithmWindow$2(com.cim.AIA.AlgorithmWindow a)
    {
        this.this$0 = a;
        super();
    }
    
    public void actionPerformed(java.awt.event.ActionEvent a)
    {
        com.cim.AIA.AlgorithmWindow a0 = this.this$0;
        com.cim.AIA.BreakPoint a1 = a0.breakpoint;
        if(a1 == null)
        {
            java.io.PrintStream a2 = System.err;
            a2.println("Warning: null breakpoint");
        }
        else
        {
            com.cim.AIA.AlgorithmWindow a3 = this.this$0;
            com.cim.AIA.BreakPoint a4 = a3.breakpoint;
            a4.add();
        }
    }
}