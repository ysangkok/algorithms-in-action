package com.cim.AIA;

import java.awt.event.*;

class AlgorithmWindow$4 implements ActionListener {
    public void actionPerformed(final ActionEvent e) {
        final Log l1 = new Log(5, 4, AlgorithmWindow.this.getPosition());
        if (AlgorithmWindow.this.logger != null) {
            AlgorithmWindow.this.logger.addLog(l1);
        }
        AlgorithmWindow.this.openAll();
    }
}