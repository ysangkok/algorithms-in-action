package com.cim.AIA;

import java.awt.event.*;
import localization.*;
import java.awt.*;

class DigitButton extends Button
{
    private static final long serialVersionUID = -1509632018780586504L;
    protected int value;
    protected KeyPadPanel keyPadPanel;
    protected TextField label;
    
    public DigitButton(final int val, final KeyPadPanel keyPad) {
        super(Messages.getString("UserSelectionDataDialog.17") + val);
        this.value = 0;
        this.value = val;
        this.keyPadPanel = keyPad;
        this.label = keyPad.label;
        this.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                DigitButton.this.keyPadPanel.setCurrentValue(DigitButton.this.keyPadPanel.getCurrentValue(), DigitButton.this.value);
                DigitButton.this.label.requestFocus();
            }
        });
    }
}
