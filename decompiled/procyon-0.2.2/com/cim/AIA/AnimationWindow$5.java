package com.cim.AIA;

import com.cim.common.*;
import java.awt.event.*;

class AnimationWindow$5 implements ActionListener {
    public void actionPerformed(final ActionEvent e) {
        final MessageDialog messageDialog;
        final MessageDialog aboutMessage = messageDialog = new MessageDialog(AnimationWindow.this.getBuildInfo(), true, true);
        messageDialog.setTitle(AnimationWindow.this.aboutLabel + " " + AnimationWindow.this.frameTitle);
        aboutMessage.setVisible(true);
    }
}