package com.cim.AIA;

import java.awt.event.*;

class AlgorithmWindow$2 implements ActionListener {
    public void actionPerformed(final ActionEvent e) {
        if (AlgorithmWindow.this.breakpoint != null) {
            AlgorithmWindow.this.breakpoint.add();
        }
        else {
            System.err.println("Warning: null breakpoint");
        }
    }
}