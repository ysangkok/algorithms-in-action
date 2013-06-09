package com.cim.AIA;

import java.awt.event.*;

class ImageCanvas$1 implements MouseListener {
    public void mouseClicked(final MouseEvent e) {
    }
    
    public void mouseEntered(final MouseEvent e) {
        ImageCanvas.this.help(new HelpEvent(this, "Algorithms in Action", ImageCanvas.this.instructions));
    }
    
    public void mouseExited(final MouseEvent e) {
    }
    
    public void mousePressed(final MouseEvent e) {
    }
    
    public void mouseReleased(final MouseEvent e) {
    }
}