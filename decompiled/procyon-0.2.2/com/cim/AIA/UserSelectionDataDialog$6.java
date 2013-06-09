package com.cim.AIA;

import java.awt.*;
import java.awt.event.*;

class UserSelectionDataDialog$6 implements KeyListener {
    public void keyPressed(final KeyEvent e) {
        if (e.getKeyCode() == 10) {
            if (UserSelectionDataDialog.this.elementArrayCanvas.getNumberOfElements() == UserSelectionDataDialog.this.maxNumberOfElements) {
                UserSelectionDataDialog.this.modifyInput(false);
            }
            else {
                UserSelectionDataDialog.this.addInput(false, true);
            }
        }
        else if (!UserSelectionDataDialog.this.handleAKeyEvent(e, false)) {
            final char charInput = e.getKeyChar();
            final int code = e.getKeyCode();
            if (!Character.isDigit(charInput) && code != 37 && code != 39 && code != 8 && code != 127) {
                Toolkit.getDefaultToolkit().beep();
                e.consume();
            }
        }
    }
    
    public void keyReleased(final KeyEvent e) {
    }
    
    public void keyTyped(final KeyEvent e) {
    }
}