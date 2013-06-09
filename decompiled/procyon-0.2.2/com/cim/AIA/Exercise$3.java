package com.cim.AIA;

import java.awt.event.*;

class Exercise$3 implements ActionListener {
    public void actionPerformed(final ActionEvent e) {
        if (Exercise.this.questionPosition < Exercise.this.exerciseQuestions.size() - 1) {
            final Exercise this$0 = Exercise.this;
            this$0.questionPosition = this$0.questionPosition + 1;
            Exercise.this.ok.setEnabled(false);
            Exercise.this.next.setEnabled(false);
            Exercise.this.init(false);
            Exercise.this.showDialog();
        }
    }
}