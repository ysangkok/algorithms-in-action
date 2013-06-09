package com.cim.AIA;

import java.awt.event.*;

class AnimationWindow$3 implements ActionListener {
    public void actionPerformed(final ActionEvent e) {
        ConfigurationManager.getConfigurationManager().askUserForChanges();
    }
}