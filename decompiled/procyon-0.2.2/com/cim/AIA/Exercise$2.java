package com.cim.AIA;

import java.awt.event.*;

class Exercise$2 implements ActionListener {
    public void actionPerformed(final ActionEvent e) {
        if (Exercise.this.currentQuestion != null) {
            Exercise.this.currentQuestion.removeAllActionListeners();
            Exercise.this.ok.setEnabled(false);
            if (Exercise.this.questionPosition < Exercise.this.exerciseQuestions.size() - 1) {
                Exercise.this.next.setEnabled(true);
            }
            else {
                Exercise.this.next.setEnabled(false);
            }
            Exercise.this.init(true);
            Exercise.this.showDialog();
        }
    }
}