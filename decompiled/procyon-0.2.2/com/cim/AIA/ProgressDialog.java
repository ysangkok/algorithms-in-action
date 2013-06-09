package com.cim.AIA;

import java.awt.event.*;
import java.awt.*;

public class ProgressDialog extends Dialog implements ActionListener
{
    private static final long serialVersionUID = 1331451304714434269L;
    protected boolean interactive;
    protected Button bt_dismiss;
    protected String msg;
    protected Frame component;
    protected ProgressBar progressbar;
    protected int max;
    protected int step;
    protected boolean with_progress;
    
    public ProgressDialog(final Frame component, final String title, final String msg, final boolean interactive) {
        super(component, title, interactive);
        this.interactive = true;
        this.with_progress = false;
        this.setBackground(Color.white);
        this.component = component;
        this.msg = msg;
        this.interactive = interactive;
        this.initGUI();
        this.pack();
    }
    
    public ProgressDialog(final Frame component, final String title, final String msg, final boolean interactive, final int max_progress, final int step_progress) {
        super(component, title, interactive);
        this.interactive = true;
        this.with_progress = false;
        this.setBackground(Color.white);
        this.component = component;
        this.msg = msg;
        this.interactive = interactive;
        this.max = max_progress;
        this.step = step_progress;
        this.with_progress = true;
        this.initGUI();
        this.pack();
    }
    
    public void actionPerformed(final ActionEvent aevt) {
        if (aevt.getSource() == this.bt_dismiss) {
            this.close();
        }
    }
    
    public void close() {
        this.setVisible(false);
        this.removeAll();
        this.dispose();
    }
    
    protected void initGUI() {
        final Label lbl_msg = new Label(this.msg, 1);
        lbl_msg.setBackground(Color.white);
        final BorderLayout borderlayout = new BorderLayout();
        borderlayout.setHgap(5);
        this.setLayout(borderlayout);
        this.add("North", lbl_msg);
        this.progressbar = new ProgressBar(this.max, this.step);
        this.progressbar.setSize(10, 20);
        if (this.with_progress) {
            this.add("Center", this.progressbar);
        }
        if (this.interactive) {
            this.bt_dismiss = new Button("Dismiss");
            this.bt_dismiss.addActionListener(this);
            final Panel panel;
            final Panel pn_buttons = panel = new Panel();
            panel.setLayout(new FlowLayout());
            pn_buttons.setBackground(Color.white);
            pn_buttons.add(this.bt_dismiss);
            this.add("Center", pn_buttons);
            this.bt_dismiss.requestFocus();
        }
        else {
            final Label lbl_dummy = new Label("", 1);
            lbl_dummy.setBackground(Color.white);
            this.add("South", lbl_dummy);
        }
    }
    
    public void paint() {
        if (this.progressbar != null) {
            this.progressbar.repaint();
        }
    }
    
    public void setProgressMark(final int mark) {
        this.progressbar.setProgressMark(mark);
        this.progressbar.repaint();
    }
}
