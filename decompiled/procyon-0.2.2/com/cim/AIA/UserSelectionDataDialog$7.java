package com.cim.AIA;

import java.awt.event.*;

class UserSelectionDataDialog$7 implements TextListener {
    public void textValueChanged(final TextEvent e) {
        UserSelectionDataDialog.this.keyPad.adjustButtons();
        UserSelectionDataDialog.this.initialiseComponents();
    }
}