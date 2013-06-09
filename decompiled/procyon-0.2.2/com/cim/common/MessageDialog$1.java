package com.cim.common;

import java.awt.event.*;

class MessageDialog$1 extends WindowAdapter {
    public void windowClosing(final WindowEvent e) {
        MessageDialog.this.setVisible(false);
        MessageDialog.this.dispose();
    }
}