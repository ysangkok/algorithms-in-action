package com.cim.AIA;

class Exercise$2 implements java.awt.event.ActionListener {
    final com.cim.AIA.Exercise this$0;
    
    Exercise$2(com.cim.AIA.Exercise a)
    {
        this.this$0 = a;
        super();
    }
    
    public void actionPerformed(java.awt.event.ActionEvent a)
    {
        com.cim.AIA.Exercise a0 = this.this$0;
        com.cim.AIA.ExerciseQuestion a1 = a0.currentQuestion;
        label0: {
            if(a1 == null)
            {
                break label0;
            }
            com.cim.AIA.Exercise a2 = this.this$0;
            com.cim.AIA.ExerciseQuestion a3 = a2.currentQuestion;
            a3.removeAllActionListeners();
            com.cim.AIA.Exercise a4 = this.this$0;
            java.awt.Button a5 = a4.ok;
            a5.setEnabled(false);
            com.cim.AIA.Exercise a6 = this.this$0;
            int i = a6.questionPosition;
            com.cim.AIA.Exercise a7 = this.this$0;
            java.util.Vector a8 = a7.exerciseQuestions;
            int i0 = a8.size();
            int i1 = i0 - 1;
            if(i >= i1)
            {
                com.cim.AIA.Exercise a9 = this.this$0;
                java.awt.Button a10 = a9.next;
                a10.setEnabled(false);
            }
            else
            {
                com.cim.AIA.Exercise a11 = this.this$0;
                java.awt.Button a12 = a11.next;
                a12.setEnabled(true);
            }
            com.cim.AIA.Exercise a13 = this.this$0;
            a13.init(true);
            com.cim.AIA.Exercise a14 = this.this$0;
            a14.showDialog();
        }
    }
}