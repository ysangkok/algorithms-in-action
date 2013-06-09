package com.cim.AIA;

import java.awt.*;

public class HelpWindow extends Frame implements HelpListener, ExitListener, ColorSelectionListener, FontSelectionListener
{
    private static final long serialVersionUID = -1197005685030021893L;
    protected TextArea infoTextArea;
    protected ScrollPane sp;
    protected Label label;
    
    public HelpWindow(final String title) {
        super(title);
        this.setBackground(Color.white);
        this.sp = new ScrollPane(0);
        this.add(this.sp);
        final Panel panel = new Panel();
        this.sp.add(panel);
        final GridBagLayout gbl = new GridBagLayout();
        panel.setLayout(gbl);
        final GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 0;
        c.anchor = 10;
        c.fill = 1;
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        final Font font = new Font("Courier", 1, 12);
        this.label = new Label(" ", 0);
        this.label.setFont(font);
        gbl.setConstraints(this.label, c);
        panel.add(this.label);
        c.weightx = 4.0;
        c.weighty = 4.0;
        this.infoTextArea = new TextArea("", 2, 30, 1);
        this.infoTextArea.setEditable(false);
        gbl.setConstraints(this.infoTextArea, c);
        panel.add(this.infoTextArea);
        ConfigurationManager.getConfigurationManager().addColorSelectionListener(this);
        ConfigurationManager.getConfigurationManager().addFontSelectionListener(this);
    }
    
    public void cleanUp() {
        if (this.sp != null) {
            this.sp.removeAll();
        }
        this.sp = null;
        this.infoTextArea = null;
        this.label = null;
    }
    
    public void clear() {
        this.label.setText("");
        this.infoTextArea.setText("");
    }
    
    public String getClassName() {
        return "HelpWindow";
    }
    
    public void processColorSelection(final ColorSelection colorSelection) {
        final String atribute = colorSelection.getAtributeName();
        if (atribute.equalsIgnoreCase(ColorSelection.BACKGROUND)) {
            this.infoTextArea.setBackground(colorSelection.getColor());
            this.label.setBackground(colorSelection.getColor());
        }
        else if (atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR)) {
            this.infoTextArea.setForeground(colorSelection.getColor());
            this.label.setForeground(colorSelection.getColor());
        }
    }
    
    public void processExitEvent(final ExitEvent e) {
        this.setVisible(false);
        this.removeAll();
        this.dispose();
        this.cleanUp();
    }
    
    public void processFontSelection(final FontSelection fontSelection) {
        final String atribute = fontSelection.getAtributeName();
        if (atribute.equalsIgnoreCase("Normal Font")) {
            this.infoTextArea.setFont(fontSelection.getFont());
            final Font temp = fontSelection.getFont();
            this.label.setFont(new Font(temp.getName(), 1, temp.getSize()));
        }
    }
    
    public void processHelpEvent(final HelpEvent e) {
        this.setAction(e.getTopic());
        this.setInfoText(e.getInstructions());
    }
    
    public void setAction(final String string) {
        if (this.label.getText().compareTo(string) != 0) {
            this.label.setText(" " + string);
        }
    }
    
    public void setInfoText(final String string) {
        if (this.infoTextArea.getText().compareTo(string) != 0) {
            this.infoTextArea.setText(string);
        }
    }
}
