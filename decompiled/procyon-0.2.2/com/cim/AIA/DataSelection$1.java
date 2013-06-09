package com.cim.AIA;

import java.awt.event.*;

class DataSelection$1 implements ItemListener {
    public void itemStateChanged(final ItemEvent e) {
        DataSelection.this.animationWindow.resetDataSelectionCheckBoxes();
        final Copyable temp = DataSelection.this.getData();
        if (!temp.isEmpty()) {
            DataSelection.this.setState(true);
            DataSelection.this.animationWindow.setCurrentData(temp, DataSelection.this.reInitialiseAlgorithm, DataSelection.this.clearAlgorithmState);
        }
        String cmd = e.paramString();
        final int i1 = cmd.indexOf("item=") + 5;
        final int i2 = cmd.indexOf(",", i1);
        cmd = cmd.substring(i1, i2);
        final Log l1 = new Log(4, 9, null, this.val$name);
        if (DataSelection.this.animationWindow.getThread().getLogger() != null) {
            DataSelection.this.animationWindow.getThread().getLogger().addLog(l1);
        }
        if (Logger.DEBUG) {
            System.err.println("Logging data selection item=" + cmd);
        }
    }
}