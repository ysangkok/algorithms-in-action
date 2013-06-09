package com.cim.AIA;

class AlgorithmWindow$5 implements java.awt.event.ActionListener {
    final com.cim.AIA.AlgorithmWindow this$0;
    
    AlgorithmWindow$5(com.cim.AIA.AlgorithmWindow a)
    {
        this.this$0 = a;
        super();
    }
    
    public void actionPerformed(java.awt.event.ActionEvent a)
    {
        com.cim.AIA.AlgorithmWindow a0 = this.this$0;
        String s = a0.getPosition();
        com.cim.AIA.Log a1 = new com.cim.AIA.Log((byte)5, (byte)5, s);
        com.cim.AIA.AlgorithmWindow a2 = this.this$0;
        com.cim.AIA.Logger a3 = a2.logger;
        if(a3 != null)
        {
            com.cim.AIA.AlgorithmWindow a4 = this.this$0;
            com.cim.AIA.Logger a5 = a4.logger;
            a5.addLog(a1);
        }
        com.cim.AIA.AlgorithmWindow a6 = this.this$0;
        a6.closeAll();
    }
}