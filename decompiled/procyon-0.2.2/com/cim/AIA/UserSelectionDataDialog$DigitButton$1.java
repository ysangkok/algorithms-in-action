package com.cim.AIA;

import java.awt.event.*;

class UserSelectionDataDialog$DigitButton$1 implements ActionListener {
    public void actionPerformed(final ActionEvent e) {
        DigitButton.this.keyPadPanel.setCurrentValue(DigitButton.this.keyPadPanel.getCurrentValue(), DigitButton.this.value);
        DigitButton.this.label.requestFocus();
    }
}