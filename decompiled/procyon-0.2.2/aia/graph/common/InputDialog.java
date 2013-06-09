package aia.graph.common;

import java.awt.event.*;
import localization.*;
import java.awt.*;

public class InputDialog extends Dialog
{
    private int m_value;
    private Button m_btnOK;
    private Button m_btnCancel;
    private Label m_lblText;
    private TextField m_txtValue;
    private GraphDialogEventHandler m_handler;
    private String m_prompt;
    private String m_title;
    private Frame m_parent;
    
    public InputDialog(final GraphDialogEventHandler p_handler, final Frame p_frame, final String p_title, final boolean p_model, final String p_prompt, final int p_value) {
        super(p_frame, p_title, p_model);
        this.m_prompt = null;
        this.m_parent = null;
        this.m_value = p_value;
        this.m_handler = p_handler;
        this.m_prompt = p_prompt;
        this.m_title = p_title;
        this.m_parent = p_frame;
        this.initGui();
    }
    
    private void initGui() {
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        this.setSize(200, 140);
        this.m_btnOK = new Button(Messages.getString("InputDialog.0"));
        this.m_btnCancel = new Button(Messages.getString("InputDialog.1"));
        this.m_lblText = new Label(this.m_prompt);
        this.m_txtValue = new TextField(Integer.toString(this.m_value));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = 0;
        constraints.fill = 2;
        constraints.anchor = 17;
        constraints.weightx = 1.0;
        layout.setConstraints(this.m_lblText, constraints);
        this.add(this.m_lblText);
        constraints = new GridBagConstraints();
        constraints.gridwidth = 0;
        constraints.fill = 2;
        constraints.weightx = 1.0;
        layout.setConstraints(this.m_txtValue, constraints);
        this.add(this.m_txtValue);
        constraints = new GridBagConstraints();
        constraints.gridwidth = 2;
        layout.setConstraints(this.m_btnOK, constraints);
        this.add(this.m_btnOK);
        constraints.gridwidth = 2;
        layout.setConstraints(this.m_btnCancel, constraints);
        this.add(this.m_btnCancel);
        this.m_btnOK.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                InputDialog.this.btnOK_Clicked();
            }
        });
        this.m_btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                InputDialog.this.btnCancel_Clicked();
            }
        });
        this.m_txtValue.addKeyListener(new KeyListener() {
            public void keyTyped(final KeyEvent evt) {
                InputDialog.this.txtValue_KeyTyped(evt);
            }
            
            public void keyPressed(final KeyEvent event) {
            }
            
            public void keyReleased(final KeyEvent event) {
            }
        });
        this.pack();
        final Dimension size = this.m_parent.getSize();
        final Dimension screen_size = this.getToolkit().getScreenSize();
        this.setLocation((screen_size.width - this.getSize().width) / 2, (screen_size.height - this.getSize().height) / 2);
    }
    
    public void btnOK_Clicked() {
        this.m_handler.processDialogEvent(this.m_title, this.m_txtValue.getText());
        this.dispose();
    }
    
    public void btnCancel_Clicked() {
        this.m_handler.processDialogEvent(this.m_title, null);
        this.dispose();
    }
    
    public void txtValue_KeyTyped(final KeyEvent evt) {
        if (evt.getKeyChar() == '\n') {
            this.btnOK_Clicked();
        }
    }
}
