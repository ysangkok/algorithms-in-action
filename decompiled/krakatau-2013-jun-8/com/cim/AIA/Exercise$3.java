package com.cim.AIA;

class Exercise$3 implements java.awt.event.ActionListener {
    final com.cim.AIA.Exercise this$0;
    
    Exercise$3(com.cim.AIA.Exercise a)
    {
        this.this$0 = a;
        super();
    }
    
    public void actionPerformed(java.awt.event.ActionEvent a)
    {
        com.cim.AIA.Exercise a0 = this.this$0;
        int i = a0.questionPosition;
        com.cim.AIA.Exercise a1 = this.this$0;
        java.util.Vector a2 = a1.exerciseQuestions;
        int i0 = a2.size();
        int i1 = i0 - 1;
        if(i < i1)
        {
            com.cim.AIA.Exercise a3 = this.this$0;
            int i2 = a3.questionPosition;
            int i3 = i2 + 1;
            a3.questionPosition = i3;
            com.cim.AIA.Exercise a4 = this.this$0;
            java.awt.Button a5 = a4.ok;
            a5.setEnabled(false);
            com.cim.AIA.Exercise a6 = this.this$0;
            java.awt.Button a7 = a6.next;
            a7.setEnabled(false);
            com.cim.AIA.Exercise a8 = this.this$0;
            a8.init(false);
            com.cim.AIA.Exercise a9 = this.this$0;
            a9.showDialog();
        }
    }
}