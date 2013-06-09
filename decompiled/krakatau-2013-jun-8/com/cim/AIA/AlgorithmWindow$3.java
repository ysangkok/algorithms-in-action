package com.cim.AIA;

class AlgorithmWindow$3 implements java.awt.event.ActionListener {
    final com.cim.AIA.AlgorithmWindow this$0;
    
    AlgorithmWindow$3(com.cim.AIA.AlgorithmWindow a)
    {
        this.this$0 = a;
        super();
    }
    
    public void actionPerformed(java.awt.event.ActionEvent a)
    {
        com.cim.AIA.AlgorithmWindow a0 = this.this$0;
        com.cim.AIA.BreakPoint a1 = a0.breakpoint;
        if(a1 != null)
        {
            com.cim.AIA.AlgorithmWindow a2 = this.this$0;
            com.cim.AIA.BreakPoint a3 = a2.breakpoint;
            a3.del();
        }
    }
}