package com.cim.AIA;

import java.awt.event.*;

class UserSelectionStringDataDialog$1 implements ActionListener {
    public void actionPerformed(final ActionEvent e) {
        UserSelectionStringDataDialog.this.cancelWasPressed = false;
        if (UserSelectionStringDataDialog.this.checkLength() && UserSelectionStringDataDialog.this.checkParams()) {
            UserSelectionStringDataDialog.this.setVisible(false);
        }
    }
}