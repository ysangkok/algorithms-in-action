package com.cim.AIA;

import java.awt.event.*;

class HelpableButton$1 implements MouseListener {
    public void mouseClicked(final MouseEvent e) {
    }
    
    public void mouseEntered(final MouseEvent e) {
        HelpableButton.this.help(new HelpEvent(this, HelpableButton.this.title, HelpableButton.this.instructions));
    }
    
    public void mouseExited(final MouseEvent e) {
    }
    
    public void mousePressed(final MouseEvent e) {
    }
    
    public void mouseReleased(final MouseEvent e) {
    }
}