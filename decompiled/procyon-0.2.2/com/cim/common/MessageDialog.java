package com.cim.common;

import java.awt.event.*;
import java.awt.*;

public class MessageDialog extends Dialog implements ActionListener
{
    private static final long serialVersionUID = -5964950788549835537L;
    protected static Frame parent;
    protected MultiLineLabel label;
    protected String stringLabel;
    
    public MessageDialog(final String message, final boolean mode, final boolean needOk) {
        super(MessageDialog.parent, mode);
        this.label = new MultiLineLabel(message);
        this.stringLabel = message;
        final Panel panel = new Panel();
        final Button button = new Button("Ok");
        button.addActionListener(this);
        panel.add(button);
        this.add(this.label, "Center");
        if (needOk) {
            this.add(panel, "South");
        }
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent e) {
                MessageDialog.this.setVisible(false);
                MessageDialog.this.dispose();
            }
        });
        this.pack();
        final Point point = MessageDialog.parent.getLocation();
        final Dimension size = MessageDialog.parent.getSize();
        final Dimension screen_size = this.getToolkit().getScreenSize();
        this.setLocation((screen_size.width - this.getSize().width) / 2, (screen_size.height - this.getSize().height) / 2);
    }
    
    public void actionPerformed(final ActionEvent event) {
        this.setVisible(false);
        this.dispose();
    }
    
    public MessageDialog createNew(final String message, final boolean mode, final boolean needOk) {
        return new MessageDialog(message, mode, needOk);
    }
    
    static {
        MessageDialog.parent = new Frame();
    }
}
