package com.cim.AIA;

import com.cim.common.*;
import localization.*;
import java.awt.event.*;
import java.awt.*;

public class UserSelectionStringDataDialog extends Dialog implements ExitListener, KeyListener, MouseMotionListener
{
    private static final long serialVersionUID = -2946624226568940176L;
    public static final String DEFAULT_TITLE;
    public static final String DEFAULT_STRING1;
    public static final String DEFAULT_STRING2;
    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 300;
    protected Button ok;
    protected Button cancel;
    protected String string1;
    protected String string2;
    protected TextField str1;
    protected TextField str2;
    protected boolean cancelWasPressed;
    protected int minLength;
    protected int maxLength;
    protected int param;
    protected Frame frame;
    
    public UserSelectionStringDataDialog(final Frame parent, final String title, final String[] items, final int minLength, final int maxLength, final int width, final int height, final String stringLbl1, final String stringLbl2) {
        this(parent, title, items, minLength, maxLength, width, height, stringLbl1, stringLbl2, 0);
    }
    
    public UserSelectionStringDataDialog(final Frame parent, final String title, final String[] items, final int minLength, final int maxLength, final int width, final int height, final String stringLbl1, final String stringLbl2, final int param) {
        super(parent, title, true);
        this.ok = new Button(Messages.getString("UserSelectionStringDataDialog.0"));
        this.cancel = new Button(Messages.getString("UserSelectionStringDataDialog.1"));
        this.cancelWasPressed = false;
        this.minLength = 0;
        this.maxLength = 0;
        this.param = 0;
        this.addKeyListener(this);
        this.addMouseMotionListener(this);
        this.param = param;
        this.frame = parent;
        if (items != null) {
            if (items.length > 0 && items[0] != null) {
                this.string1 = items[0];
            }
            else {
                this.string1 = Messages.getString("UserSelectionStringDataDialog.5");
            }
            if (items.length > 1 && items[1] != null) {
                this.string2 = items[1];
            }
            else {
                this.string2 = Messages.getString("UserSelectionStringDataDialog.6");
            }
        }
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.setBackground(Color.lightGray);
        final GridBagLayout gbLayout = new GridBagLayout();
        final GridBagConstraints c = new GridBagConstraints();
        this.setLayout(gbLayout);
        final Label instruction = new Label(Messages.getString("UserSelectionStringDataDialog.7"));
        final Label stringLabel1 = new Label(stringLbl1);
        final Label stringLabel2 = new Label(stringLbl2);
        this.str1 = new TextField(this.string1, 30);
        this.str2 = new TextField(this.string2, 30);
        this.buildConstraints(c, 0, 0, 2, 1, 0, 0);
        c.anchor = 17;
        c.insets = new Insets(5, 10, 0, 0);
        gbLayout.setConstraints(instruction, c);
        this.add(instruction);
        this.buildConstraints(c, 0, 1, 1, 1, 30, 0);
        c.anchor = 10;
        c.fill = 10;
        c.insets = new Insets(10, 10, 0, 0);
        gbLayout.setConstraints(stringLabel1, c);
        this.add(stringLabel1);
        this.buildConstraints(c, 0, 2, 1, 1, 30, 0);
        gbLayout.setConstraints(stringLabel2, c);
        this.add(stringLabel2);
        c.insets = new Insets(10, 10, 0, 10);
        this.buildConstraints(c, 1, 1, 1, 1, 70, 0);
        gbLayout.setConstraints(this.str1, c);
        this.add(this.str1);
        this.buildConstraints(c, 1, 2, 1, 1, 70, 0);
        gbLayout.setConstraints(this.str2, c);
        this.add(this.str2);
        final Panel btnPanel = new Panel();
        btnPanel.add(this.ok);
        btnPanel.add(this.cancel);
        c.insets = new Insets(10, 10, 5, 10);
        this.buildConstraints(c, 0, 3, 2, 1, 0, 0);
        gbLayout.setConstraints(btnPanel, c);
        this.add(btnPanel);
        this.ok.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                UserSelectionStringDataDialog.this.cancelWasPressed = false;
                if (UserSelectionStringDataDialog.this.checkLength() && UserSelectionStringDataDialog.this.checkParams()) {
                    UserSelectionStringDataDialog.this.setVisible(false);
                }
            }
        });
        this.cancel.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                UserSelectionStringDataDialog.this.cancelWasPressed = true;
                UserSelectionStringDataDialog.this.setVisible(false);
            }
        });
        this.setSize(width, height);
        this.setResizable(false);
        this.pack();
        final Dimension screenSize = this.getToolkit().getScreenSize();
    }
    
    public UserSelectionStringDataDialog(final int maxLength) {
        this(UserSelectionStringDataDialog.DEFAULT_TITLE, maxLength, 400, 300);
    }
    
    public UserSelectionStringDataDialog(final String title, final int maxLength, final int width, final int height) {
        this(new Frame(), title, null, 0, maxLength, width, height, UserSelectionStringDataDialog.DEFAULT_STRING1, UserSelectionStringDataDialog.DEFAULT_STRING2);
        this.frame.setSize(width, height);
    }
    
    public UserSelectionStringDataDialog(final String title, final String[] items, final int minLength, final int maxLength) {
        this(new Frame(), title, items, minLength, maxLength, 400, 300, UserSelectionStringDataDialog.DEFAULT_STRING1, UserSelectionStringDataDialog.DEFAULT_STRING2);
    }
    
    public UserSelectionStringDataDialog(final String title, final String[] items, final int minLength, final int maxLength, final int param) {
        this(new Frame(), title, items, minLength, maxLength, 400, 300, UserSelectionStringDataDialog.DEFAULT_STRING1, UserSelectionStringDataDialog.DEFAULT_STRING2, param);
    }
    
    public UserSelectionStringDataDialog(final String title, final String[] items, final int minLength, final int maxLength, final String label1, final String label2) {
        this(new Frame(), title, items, minLength, maxLength, 400, 300, label1, label2);
    }
    
    public UserSelectionStringDataDialog(final String title, final String[] items, final int minLength, final int maxLength, final String label1, final String label2, final int param) {
        this(new Frame(), title, items, minLength, maxLength, 400, 300, label1, label2, param);
    }
    
    private void buildConstraints(final GridBagConstraints gbc, final int gx, final int gy, final int gw, final int gh, final int wx, final int wy) {
        gbc.gridx = gx;
        gbc.gridy = gy;
        gbc.gridwidth = gw;
        gbc.gridheight = gh;
        gbc.weightx = (double)wx;
        gbc.weighty = (double)wy;
    }
    
    public boolean cancelPressed() {
        return this.cancelWasPressed;
    }
    
    protected boolean checkLength() {
        final int i1 = this.str1.getText().length();
        final int i2 = this.str2.getText().length();
        if (i1 < this.minLength || i1 > this.maxLength || i2 < this.minLength || i2 > this.maxLength) {
            final MessageDialog msg = new MessageDialog(Messages.getString("UserSelectionStringDataDialog.24") + this.minLength + Messages.getString("UserSelectionStringDataDialog.25") + this.maxLength, true, true);
            msg.setTitle(Messages.getString("UserSelectionStringDataDialog.26"));
            msg.setVisible(true);
            return false;
        }
        return true;
    }
    
    protected boolean checkParams() {
        MessageDialog msg;
        for (str = this.str1.getText(), i = 0; i < str.length(); ++i) {
            final String str;
            final int i;
            if (Character.isLetter(str.charAt(i)) && (this.param & 1) != 1) {
                msg = new MessageDialog(Messages.getString("UserSelectionStringDataDialog.8"), true, true);
                msg.setTitle(Messages.getString("UserSelectionStringDataDialog.9"));
                msg.setVisible(true);
                return false;
            }
            if (Character.isDigit(str.charAt(i)) && (this.param & 2) != 2) {
                msg = new MessageDialog(Messages.getString("UserSelectionStringDataDialog.10"), true, true);
                msg.setTitle(Messages.getString("UserSelectionStringDataDialog.11"));
                msg.setVisible(true);
                return false;
            }
            if (Character.isSpaceChar(str.charAt(i)) && (this.param & 32) != 32) {
                msg = new MessageDialog(Messages.getString("UserSelectionStringDataDialog.12"), true, true);
                msg.setTitle(Messages.getString("UserSelectionStringDataDialog.13"));
                msg.setVisible(true);
                return false;
            }
            if (!Character.isLetter(str.charAt(i)) && !Character.isDigit(str.charAt(i)) && !Character.isSpaceChar(str.charAt(i)) && (this.param & 4) != 4) {
                msg = new MessageDialog(Messages.getString("UserSelectionStringDataDialog.14"), true, true);
                msg.setTitle(Messages.getString("UserSelectionStringDataDialog.15"));
                msg.setVisible(true);
                return false;
            }
        }
        for (str = this.str2.getText(), i = 0; i < str.length(); ++i) {
            final String str;
            final int i;
            if (Character.isLetter(str.charAt(i)) && (this.param & 1) != 1) {
                msg = new MessageDialog(Messages.getString("UserSelectionStringDataDialog.16"), true, true);
                msg.setTitle(Messages.getString("UserSelectionStringDataDialog.17"));
                msg.setVisible(true);
                return false;
            }
            if (Character.isDigit(str.charAt(i)) && (this.param & 2) != 2) {
                msg = new MessageDialog(Messages.getString("UserSelectionStringDataDialog.18"), true, true);
                msg.setTitle(Messages.getString("UserSelectionStringDataDialog.19"));
                msg.setVisible(true);
                return false;
            }
            if (Character.isSpaceChar(str.charAt(i)) && (this.param & 32) != 32) {
                msg = new MessageDialog(Messages.getString("UserSelectionStringDataDialog.20"), true, true);
                msg.setTitle(Messages.getString("UserSelectionStringDataDialog.21"));
                msg.setVisible(true);
                return false;
            }
            if (!Character.isLetter(str.charAt(i)) && !Character.isDigit(str.charAt(i)) && !Character.isSpaceChar(str.charAt(i)) && (this.param & 4) != 4) {
                msg = new MessageDialog(Messages.getString("UserSelectionStringDataDialog.22"), true, true);
                msg.setTitle(Messages.getString("UserSelectionStringDataDialog.23"));
                msg.setVisible(true);
                return false;
            }
        }
        if ((this.param & 16) == 16) {
            this.str1.setText(this.str1.getText().toUpperCase());
            this.str2.setText(this.str2.getText().toUpperCase());
        }
        if ((this.param & 8) == 8) {
            this.str1.setText(this.str1.getText().toLowerCase());
            this.str2.setText(this.str2.getText().toLowerCase());
        }
        return true;
    }
    
    public String[] getData() {
        if (this.cancelPressed()) {
            return new String[0];
        }
        return new String[] { this.str1.getText(), this.str2.getText() };
    }
    
    protected boolean handleAKeyEvent(final KeyEvent e, final boolean requestFocus) {
        final int code = e.getKeyCode();
        return false;
    }
    
    protected void initialiseComponents() {
    }
    
    public void keyPressed(final KeyEvent e) {
        this.handleAKeyEvent(e, true);
    }
    
    public void keyReleased(final KeyEvent e) {
    }
    
    public void keyTyped(final KeyEvent e) {
    }
    
    public void mouseDragged(final MouseEvent e) {
    }
    
    public void mouseMoved(final MouseEvent e) {
    }
    
    public void processExitEvent(final ExitEvent e) {
        this.setVisible(false);
        this.removeAll();
        this.dispose();
    }
    
    static {
        DEFAULT_TITLE = Messages.getString("UserSelectionStringDataDialog.2");
        DEFAULT_STRING1 = Messages.getString("UserSelectionStringDataDialog.3");
        DEFAULT_STRING2 = Messages.getString("UserSelectionStringDataDialog.4");
    }
}
