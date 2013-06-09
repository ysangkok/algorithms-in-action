package com.cim.AIA;

import java.util.*;
import localization.*;
import java.awt.event.*;
import java.awt.*;

public class UserSelectionDataDialog extends Dialog implements ExitListener, KeyListener, MouseMotionListener
{
    private static final long serialVersionUID = 3553395302039363907L;
    public static final String DEFAULT_TITLE;
    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 300;
    protected ElementArrayCanvas elementArrayCanvas;
    protected KeyPadPanel keyPad;
    protected TextField displayLabel;
    protected Button add;
    protected Button delete;
    protected Button modify;
    protected Button ok;
    protected Button cancel;
    protected boolean cancelWasPressed;
    protected int minNumberOfElements;
    protected int maxNumberOfElements;
    protected int maximumValue;
    protected int minimumValue;
    protected Frame frame;
    
    public UserSelectionDataDialog(final Frame parent, final String title, final int[] items, final int minValue, final int maxValue, final int minNumberOfItems, final int maxNumberOfItems, final int width, final int height) {
        super(parent, title, true);
        this.add = new Button(Messages.getString("UserSelectionDataDialog.0"));
        this.delete = new Button(Messages.getString("UserSelectionDataDialog.1"));
        this.modify = new Button(Messages.getString("UserSelectionDataDialog.2"));
        this.ok = new Button(Messages.getString("UserSelectionDataDialog.3"));
        this.cancel = new Button(Messages.getString("UserSelectionDataDialog.4"));
        this.cancelWasPressed = false;
        this.minNumberOfElements = 0;
        this.maxNumberOfElements = 0;
        this.maximumValue = 0;
        this.minimumValue = 0;
        this.addKeyListener(this);
        this.addMouseMotionListener(this);
        this.frame = parent;
        this.minimumValue = minValue;
        this.maximumValue = maxValue;
        this.minNumberOfElements = minNumberOfItems;
        this.maxNumberOfElements = maxNumberOfItems;
        final ScrollPane scrollPane = new ScrollPane(0);
        scrollPane.setSize(width, height / 4);
        final int tempSize = (Messages.getString("UserSelectionDataDialog.6") + this.maximumValue).length() + 3;
        this.displayLabel = new TextField(Messages.getString("UserSelectionDataDialog.7"), tempSize);
        this.displayLabel.setEditable(true);
        this.elementArrayCanvas = new ElementArrayCanvas(this.displayLabel);
        this.elementArrayCanvas.setParent(scrollPane);
        this.elementArrayCanvas.setSize(width, height / 4 - 20);
        this.elementArrayCanvas.addMouseMotionListener(this);
        if (items != null) {
            for (int i = 0; i < items.length; ++i) {
                this.elementArrayCanvas.additem(new Integer(items[i]), i, true);
            }
        }
        this.elementArrayCanvas.increment();
        this.elementArrayCanvas.repaint();
        this.keyPad = new KeyPadPanel(this.displayLabel, this.minimumValue, this.maximumValue);
        final GridBagLayout gbLayout = new GridBagLayout();
        final GridBagConstraints c = new GridBagConstraints();
        this.setLayout(gbLayout);
        c.ipadx = 2;
        c.ipady = 2;
        c.fill = 1;
        c.anchor = 10;
        c.gridwidth = 0;
        scrollPane.add(this.elementArrayCanvas);
        gbLayout.setConstraints(scrollPane, c);
        this.add(scrollPane);
        this.initialiseComponents();
        this.add.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                UserSelectionDataDialog.this.addInput(true, true);
            }
        });
        this.delete.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                UserSelectionDataDialog.this.deleteInput(true);
            }
        });
        this.modify.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                UserSelectionDataDialog.this.modifyInput(true);
            }
        });
        this.ok.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                UserSelectionDataDialog.this.setVisible(false);
            }
        });
        this.cancel.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                UserSelectionDataDialog.this.cancelWasPressed = true;
                UserSelectionDataDialog.this.setVisible(false);
            }
        });
        this.displayLabel.addKeyListener(new KeyListener() {
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
        });
        this.displayLabel.addTextListener(new TextListener() {
            public void textValueChanged(final TextEvent e) {
                UserSelectionDataDialog.this.keyPad.adjustButtons();
                UserSelectionDataDialog.this.initialiseComponents();
            }
        });
        final Panel panel;
        final Panel controlPanel = panel = new Panel();
        panel.setLayout(new BorderLayout());
        final Panel textAreaPanel = new Panel();
        textAreaPanel.add(this.displayLabel);
        final Label textData = new Label(Messages.getString("UserSelectionDataDialog.8"));
        c.gridwidth = 1;
        c.insets = new Insets(0, 20, 0, 0);
        gbLayout.setConstraints(textData, c);
        this.add(textData);
        final Panel space = new Panel();
        c.weightx = 4.0;
        c.gridwidth = 1;
        c.insets = new Insets(0, 0, 0, 0);
        gbLayout.setConstraints(space, c);
        this.add(space);
        final Label textHot = new Label(Messages.getString("UserSelectionDataDialog.9"));
        c.gridwidth = 0;
        gbLayout.setConstraints(textHot, c);
        this.add(textHot);
        final TextArea text = new TextArea(Messages.getString("UserSelectionDataDialog.10"), 5, 30, 3);
        text.setEditable(false);
        text.append(Messages.getString("UserSelectionDataDialog.11"));
        text.append(Messages.getString("UserSelectionDataDialog.12"));
        text.append(Messages.getString("UserSelectionDataDialog.13"));
        text.append(Messages.getString("UserSelectionDataDialog.14"));
        c.gridwidth = 1;
        gbLayout.setConstraints(textAreaPanel, c);
        this.add(textAreaPanel);
        final Panel space2 = new Panel();
        c.weightx = 4.0;
        c.gridwidth = 1;
        gbLayout.setConstraints(space2, c);
        this.add(space2);
        c.gridwidth = 0;
        c.insets = new Insets(0, 0, 10, 20);
        gbLayout.setConstraints(text, c);
        this.add(text);
        final Panel controlButtonsPanel = new Panel();
        controlButtonsPanel.add(this.modify);
        controlButtonsPanel.add(this.add);
        controlButtonsPanel.add(this.delete);
        final Panel systemButtonsPanel = new Panel();
        systemButtonsPanel.add(this.ok);
        systemButtonsPanel.add(this.cancel);
        c.insets = new Insets(0, 5, 0, 0);
        c.gridwidth = 0;
        gbLayout.setConstraints(this.keyPad, c);
        this.add(this.keyPad);
        c.gridwidth = 1;
        c.insets = new Insets(0, 40, 0, 0);
        gbLayout.setConstraints(controlButtonsPanel, c);
        this.add(controlButtonsPanel);
        c.insets = new Insets(0, 0, 0, 0);
        c.gridwidth = 0;
        gbLayout.setConstraints(systemButtonsPanel, c);
        this.add(systemButtonsPanel);
        this.setSize(width, height);
        this.setResizable(false);
        this.pack();
        final Dimension screenSize = this.getToolkit().getScreenSize();
        this.setLocation((screenSize.width - this.getSize().width) / 2, (screenSize.height - this.getSize().height) / 2);
        this.displayLabel.requestFocus();
    }
    
    public UserSelectionDataDialog(final int maxValue, final int maxNumberOfItems) {
        this(UserSelectionDataDialog.DEFAULT_TITLE, maxValue, maxNumberOfItems, 400, 300);
    }
    
    public UserSelectionDataDialog(final String title, final int maxValue, final int maxNumberOfItems, final int width, final int height) {
        this(new Frame(), title, null, 0, maxValue, 0, maxNumberOfItems, width, height);
        this.frame.setSize(width, height);
    }
    
    public UserSelectionDataDialog(final String title, final int[] items, final int minValue, final int maxValue, final int minNumberOfItems, final int maxNumberOfItems) {
        this(new Frame(), title, items, minValue, maxValue, minNumberOfItems, maxNumberOfItems, 400, 300);
    }
    
    protected void addInput(final boolean requestFocus, final boolean append) {
        if (this.valid() && this.elementArrayCanvas.getNumberOfElements() < this.maxNumberOfElements) {
            this.elementArrayCanvas.additem(new Integer(this.keyPad.getCurrentValue()), this.elementArrayCanvas.getCurrentPosition(), append);
            this.elementArrayCanvas.repaint();
            this.keyPad.setCurrentValue(0, 0);
            this.initialiseComponents();
        }
        if (requestFocus) {
            this.displayLabel.requestFocus();
        }
    }
    
    public boolean cancelPressed() {
        return this.cancelWasPressed;
    }
    
    protected void deleteInput(final boolean requestFocus) {
        if (this.elementArrayCanvas.getNumberOfElements() > this.minNumberOfElements && this.elementArrayCanvas.getNumberOfElements() > 0) {
            this.elementArrayCanvas.remove(this.elementArrayCanvas.getCurrentPosition());
            this.elementArrayCanvas.repaint();
            this.initialiseComponents();
        }
        if (requestFocus) {
            this.displayLabel.requestFocus();
        }
    }
    
    public int[] getData() {
        if (this.cancelPressed()) {
            return new int[0];
        }
        final Vector<Object> integers = this.elementArrayCanvas.getObjects();
        final int[] data = new int[integers.size()];
        for (int i = 0; i < integers.size(); ++i) {
            data[i] = ((Integer)((Integer)integers.elementAt(i))).intValue();
        }
        return data;
    }
    
    protected boolean handleAKeyEvent(final KeyEvent e, final boolean requestFocus) {
        final int code = e.getKeyCode();
        if (code == 38) {
            this.elementArrayCanvas.decriment();
            this.elementArrayCanvas.repaint();
            e.consume();
            return true;
        }
        if (code == 40) {
            this.elementArrayCanvas.increment();
            this.elementArrayCanvas.repaint();
            e.consume();
            return true;
        }
        if (code == 65) {
            this.addInput(requestFocus, true);
            e.consume();
            return true;
        }
        if (code == 68) {
            this.deleteInput(requestFocus);
            e.consume();
            return true;
        }
        if (code == 77) {
            this.modifyInput(requestFocus);
            e.consume();
            return true;
        }
        if (code == 73) {
            this.addInput(requestFocus, false);
            e.consume();
            return true;
        }
        return false;
    }
    
    protected void initialiseComponents() {
        if (this.elementArrayCanvas.getNumberOfElements() >= this.maxNumberOfElements || !this.valid()) {
            this.add.setEnabled(false);
        }
        else {
            this.add.setEnabled(true);
        }
        this.displayLabel.setEnabled(true);
        if (this.elementArrayCanvas.getNumberOfElements() > this.minNumberOfElements && this.elementArrayCanvas.getNumberOfElements() > 0) {
            this.delete.setEnabled(true);
        }
        else {
            this.delete.setEnabled(false);
        }
        if (this.elementArrayCanvas.getNumberOfElements() > 0 && this.valid()) {
            this.modify.setEnabled(true);
        }
        else {
            this.modify.setEnabled(false);
        }
        if (this.elementArrayCanvas.getNumberOfElements() < this.minNumberOfElements) {
            this.ok.setEnabled(false);
        }
        else {
            this.ok.setEnabled(true);
        }
        this.cancel.setEnabled(true);
    }
    
    public void keyPressed(final KeyEvent e) {
        this.handleAKeyEvent(e, true);
    }
    
    public void keyReleased(final KeyEvent e) {
    }
    
    public void keyTyped(final KeyEvent e) {
    }
    
    protected void modifyInput(final boolean requestFocus) {
        if (this.valid() && this.elementArrayCanvas.getNumberOfElements() > 0) {
            this.elementArrayCanvas.modify(new Integer(this.keyPad.getCurrentValue()), this.elementArrayCanvas.getCurrentPosition());
            this.elementArrayCanvas.repaint();
            this.keyPad.setCurrentValue(0, 0);
            this.initialiseComponents();
        }
        if (requestFocus) {
            this.displayLabel.requestFocus();
        }
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
    
    protected boolean valid() {
        if (!this.keyPad.isValid()) {
            return false;
        }
        final int currentValue = this.keyPad.getCurrentValue();
        return currentValue >= this.minimumValue && currentValue <= this.maximumValue;
    }
    
    static {
        DEFAULT_TITLE = Messages.getString("UserSelectionDataDialog.5");
    }
}
