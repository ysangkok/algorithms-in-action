package com.cim.AIA;

import localization.*;
import java.awt.*;

class KeyPadPanel extends Panel
{
    private static final long serialVersionUID = 1013169127766911662L;
    protected DigitButton[] buttons;
    protected TextField label;
    protected int maximumValue;
    protected int minimumValue;
    
    public KeyPadPanel(final TextField lab, final int minValue, final int maxValue) {
        super();
        this.buttons = new DigitButton[10];
        this.maximumValue = 0;
        this.minimumValue = 0;
        this.label = lab;
        this.minimumValue = minValue;
        this.maximumValue = maxValue;
        this.setLayout(new GridLayout(1, 10));
        for (int i = 0; i < this.buttons.length; ++i) {
            this.buttons[i] = this$0.new DigitButton(i, this);
            this.add(this.buttons[i]);
        }
    }
    
    public void adjustButtons() {
        if (this.getCurrentValue() <= this.maximumValue) {
            this.allowInput(true);
        }
        else {
            this.allowInput(false);
        }
    }
    
    protected void allowInput(final boolean state) {
        for (int i = 0; i < this.buttons.length; ++i) {
            this.buttons[i].setEnabled(state);
        }
    }
    
    public int getCurrentValue() {
        final String input = this.label.getText();
        int temp = 0;
        try {
            temp = Integer.parseInt(input);
        }
        catch (NumberFormatException ex) {}
        return temp;
    }
    
    public boolean isValid() {
        if (this.label == null) {
            return false;
        }
        final String input = this.label.getText();
        int temp = 0;
        try {
            temp = Integer.parseInt(input);
        }
        catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    public void setCurrentValue(final int currentValue, final int newAddition) {
        final int old = this.label.getCaretPosition();
        if (currentValue != 0 || newAddition != 0) {
            final String currentText = this.label.getText();
            final String firstHalf = currentText.substring(0, old);
            final String secondHalf = currentText.substring(old, currentText.length());
            this.label.setText(Messages.getString("UserSelectionDataDialog.15") + firstHalf + newAddition + secondHalf);
        }
        else {
            this.label.setText(Messages.getString("UserSelectionDataDialog.16"));
        }
        this.label.setCaretPosition(old + 1);
        this.adjustButtons();
    }
}
