package com.cim.AIA;

import java.awt.event.*;
import localization.*;
import java.util.*;
import java.awt.*;

public class ConfigurationManagerDialog extends Dialog
{
    private static final long serialVersionUID = -1467986213981708372L;
    protected static String DEFAULT_TITLE;
    protected static String[] fontNames;
    protected static int[] fontSizes;
    protected Vector<E> fontSelections;
    protected Vector<E> colorSelections;
    protected Choice colorAtributeChoice;
    protected Panel colorExamplePanel;
    protected Scrollbar colorSetupRedScrollbar;
    protected Scrollbar colorSetupGreenScrollbar;
    protected Scrollbar colorSetupBlueScrollbar;
    protected Choice fontAtributeChoice;
    protected Choice fontSetupNameChoice;
    protected Choice fontSetupSizeChoice;
    protected Checkbox fontSetupStyleBoldCheckbox;
    protected Checkbox fontSetupStyleItalicCheckbox;
    Label fontExampleLabel;
    protected Button okButton;
    protected Button defaultButton;
    protected Button cancelButton;
    protected boolean acceptChanges;
    
    public ConfigurationManagerDialog(final Frame frame, final String title, final boolean modal, final Vector<E> fontSelections, final Vector<E> colorSelections) {
        super(frame, title, modal);
        this.acceptChanges = false;
        this.fontSelections = fontSelections;
        this.colorSelections = colorSelections;
        this.setTitle(title);
        this.initialiseGUI();
        this.addFontSelections();
        this.addColorSelections();
        this.setupListeners();
        this.updateColor(false);
        this.updateFont(false);
        this.pack();
        final Dimension screenSize = this.getToolkit().getScreenSize();
        this.setLocation((screenSize.width - this.getSize().width) / 2, (screenSize.height - this.getSize().height) / 2);
    }
    
    public ConfigurationManagerDialog(final Vector<E> fontSelections, final Vector<E> colorSelections) {
        this(new Frame(), ConfigurationManagerDialog.DEFAULT_TITLE, true, fontSelections, colorSelections);
    }
    
    protected void accept() {
        this.acceptChanges = true;
        this.setVisible(false);
    }
    
    public boolean acceptChanges() {
        return this.acceptChanges;
    }
    
    protected void addColorSelections() {
        for (int i = 0; i < this.colorSelections.size(); ++i) {
            final ColorSelection colorSelection = (ColorSelection)((ColorSelection)this.colorSelections.elementAt(i));
            this.colorAtributeChoice.add(colorSelection.getAtributeName());
        }
    }
    
    protected void addFontSelections() {
        for (int i = 0; i < this.fontSelections.size(); ++i) {
            final FontSelection fontSelection = (FontSelection)((FontSelection)this.fontSelections.elementAt(i));
            this.fontAtributeChoice.add(fontSelection.getAtributeName());
        }
    }
    
    protected void displayColor(final ColorSelection colorSelection) {
        final Color color = colorSelection.getColor();
        this.colorExamplePanel.setBackground(color);
        this.colorSetupRedScrollbar.setValue(color.getRed());
        this.colorSetupGreenScrollbar.setValue(color.getGreen());
        this.colorSetupBlueScrollbar.setValue(color.getBlue());
    }
    
    protected void displayFont(final FontSelection fontSelection) {
        final Font font = fontSelection.getFont();
        this.fontExampleLabel.setFont(font);
        this.fontSetupNameChoice.select(font.getName());
        this.fontSetupSizeChoice.select("" + font.getSize());
        this.fontSetupStyleBoldCheckbox.setState(font.isBold());
        this.fontSetupStyleItalicCheckbox.setState(font.isItalic());
    }
    
    protected Panel getColorPanel() {
        final Panel mainColorPanel = new Panel();
        final GridBagLayout mainColorGridBag = new GridBagLayout();
        mainColorPanel.setLayout(mainColorGridBag);
        final GridBagConstraints mainColorConstraints = new GridBagConstraints();
        mainColorConstraints.fill = 1;
        mainColorConstraints.weightx = 0.0;
        mainColorConstraints.weighty = 0.0;
        mainColorConstraints.ipadx = 2;
        mainColorConstraints.ipady = 2;
        mainColorConstraints.anchor = 11;
        mainColorConstraints.gridwidth = 0;
        final Label colorTitleLabel = new Label(Messages.getString("ConfigurationManagerDialog.11"), 1);
        mainColorGridBag.setConstraints(colorTitleLabel, mainColorConstraints);
        mainColorPanel.add(colorTitleLabel);
        final Panel panel;
        final Panel colorTitlePanel = panel = new Panel();
        panel.setLayout(new GridLayout(1, 2));
        final Label colorAtributeLabel = new Label(Messages.getString("ConfigurationManagerDialog.12"), 0);
        colorTitlePanel.add(colorAtributeLabel);
        final Label colorExampleTitleLabel = new Label(Messages.getString("ConfigurationManagerDialog.13"), 0);
        colorTitlePanel.add(colorExampleTitleLabel);
        mainColorGridBag.setConstraints(colorTitlePanel, mainColorConstraints);
        mainColorPanel.add(colorTitlePanel);
        final Panel panel2;
        final Panel colorAtributePanel = panel2 = new Panel();
        panel2.setLayout(new GridLayout(1, 2));
        this.colorAtributeChoice = new Choice();
        colorAtributePanel.add(this.colorAtributeChoice);
        this.colorExamplePanel = new Panel();
        colorAtributePanel.add(this.colorExamplePanel);
        mainColorGridBag.setConstraints(colorAtributePanel, mainColorConstraints);
        mainColorPanel.add(colorAtributePanel);
        final Panel colorControlPanel = new Panel();
        final GridBagLayout colorControlGridBag = new GridBagLayout();
        colorControlPanel.setLayout(colorControlGridBag);
        final GridBagConstraints colorControlConstraints = new GridBagConstraints();
        colorControlConstraints.fill = 2;
        colorControlConstraints.weightx = 1.0;
        colorControlConstraints.weighty = 0.0;
        colorControlConstraints.ipadx = 2;
        colorControlConstraints.ipady = 2;
        colorControlConstraints.anchor = 10;
        final Panel colorRedPanel = new Panel();
        final GridBagLayout colorRedGridBag = new GridBagLayout();
        colorRedPanel.setLayout(colorRedGridBag);
        final GridBagConstraints colorRedConstraints = new GridBagConstraints();
        colorRedConstraints.fill = 2;
        colorRedConstraints.weightx = 0.0;
        colorRedConstraints.weighty = 0.0;
        colorRedConstraints.ipadx = 2;
        colorRedConstraints.ipady = 2;
        colorRedConstraints.anchor = 10;
        colorRedConstraints.gridwidth = 0;
        final Label colorRedTitleLabel = new Label(Messages.getString("ConfigurationManagerDialog.14"), 1);
        colorRedGridBag.setConstraints(colorRedTitleLabel, colorRedConstraints);
        colorRedPanel.add(colorRedTitleLabel);
        this.colorSetupRedScrollbar = new Scrollbar(0, 128, 1, 0, 256);
        colorRedGridBag.setConstraints(this.colorSetupRedScrollbar, colorRedConstraints);
        colorRedPanel.add(this.colorSetupRedScrollbar);
        colorControlGridBag.setConstraints(colorRedPanel, colorControlConstraints);
        colorControlPanel.add(colorRedPanel);
        final Panel colorGreenPanel = new Panel();
        final GridBagLayout colorGreenGridBag = new GridBagLayout();
        colorGreenPanel.setLayout(colorGreenGridBag);
        final GridBagConstraints colorGreenConstraints = new GridBagConstraints();
        colorGreenConstraints.fill = 2;
        colorGreenConstraints.weightx = 0.0;
        colorGreenConstraints.weighty = 0.0;
        colorGreenConstraints.ipadx = 2;
        colorGreenConstraints.ipady = 2;
        colorGreenConstraints.anchor = 10;
        colorGreenConstraints.gridwidth = 0;
        final Label colorGreenTitleLabel = new Label(Messages.getString("ConfigurationManagerDialog.15"), 1);
        colorGreenGridBag.setConstraints(colorGreenTitleLabel, colorGreenConstraints);
        colorGreenPanel.add(colorGreenTitleLabel);
        this.colorSetupGreenScrollbar = new Scrollbar(0, 128, 1, 0, 256);
        colorGreenGridBag.setConstraints(this.colorSetupGreenScrollbar, colorGreenConstraints);
        colorGreenPanel.add(this.colorSetupGreenScrollbar);
        colorControlGridBag.setConstraints(colorGreenPanel, colorControlConstraints);
        colorControlPanel.add(colorGreenPanel);
        final Panel colorBluePanel = new Panel();
        final GridBagLayout colorBlueGridBag = new GridBagLayout();
        colorBluePanel.setLayout(colorBlueGridBag);
        final GridBagConstraints colorBlueConstraints = new GridBagConstraints();
        colorBlueConstraints.fill = 2;
        colorBlueConstraints.weightx = 0.0;
        colorBlueConstraints.weighty = 0.0;
        colorBlueConstraints.ipadx = 2;
        colorBlueConstraints.ipady = 2;
        colorBlueConstraints.anchor = 10;
        colorBlueConstraints.gridwidth = 0;
        final Label colorBlueTitleLabel = new Label(Messages.getString("ConfigurationManagerDialog.16"), 1);
        colorBlueGridBag.setConstraints(colorBlueTitleLabel, colorBlueConstraints);
        colorBluePanel.add(colorBlueTitleLabel);
        this.colorSetupBlueScrollbar = new Scrollbar(0, 128, 1, 0, 256);
        colorBlueGridBag.setConstraints(this.colorSetupBlueScrollbar, colorBlueConstraints);
        colorBluePanel.add(this.colorSetupBlueScrollbar);
        colorControlGridBag.setConstraints(colorBluePanel, colorControlConstraints);
        colorControlPanel.add(colorBluePanel);
        mainColorGridBag.setConstraints(colorControlPanel, mainColorConstraints);
        mainColorPanel.add(colorControlPanel);
        return mainColorPanel;
    }
    
    protected Panel getControlPanel() {
        final Panel controlPanel = new Panel();
        this.okButton = new Button(Messages.getString("ConfigurationManagerDialog.27"));
        this.defaultButton = new Button(Messages.getString("ConfigurationManagerDialog.28"));
        this.cancelButton = new Button(Messages.getString("ConfigurationManagerDialog.29"));
        controlPanel.add(this.okButton);
        controlPanel.add(this.defaultButton);
        controlPanel.add(this.cancelButton);
        return controlPanel;
    }
    
    protected Panel getFontPanel() {
        final Panel mainFontPanel = new Panel();
        final GridBagLayout mainFontGridBag = new GridBagLayout();
        mainFontPanel.setLayout(mainFontGridBag);
        final GridBagConstraints mainFontConstraints = new GridBagConstraints();
        mainFontConstraints.fill = 1;
        mainFontConstraints.weightx = 0.0;
        mainFontConstraints.weighty = 0.0;
        mainFontConstraints.ipadx = 2;
        mainFontConstraints.ipady = 2;
        mainFontConstraints.anchor = 11;
        mainFontConstraints.gridwidth = 0;
        final Label fontTitleLabel = new Label(Messages.getString("ConfigurationManagerDialog.17"), 1);
        mainFontGridBag.setConstraints(fontTitleLabel, mainFontConstraints);
        mainFontPanel.add(fontTitleLabel);
        final Panel panel;
        final Panel fontTitlePanel = panel = new Panel();
        panel.setLayout(new GridLayout(1, 2));
        final Label fontAtributeLabel = new Label(Messages.getString("ConfigurationManagerDialog.18"), 0);
        fontTitlePanel.add(fontAtributeLabel);
        final Label fontExampleTitleLabel = new Label(Messages.getString("ConfigurationManagerDialog.19"), 0);
        fontTitlePanel.add(fontExampleTitleLabel);
        mainFontGridBag.setConstraints(fontTitlePanel, mainFontConstraints);
        mainFontPanel.add(fontTitlePanel);
        final Panel panel2;
        final Panel fontAtributePanel = panel2 = new Panel();
        panel2.setLayout(new GridLayout(1, 2));
        this.fontAtributeChoice = new Choice();
        fontAtributePanel.add(this.fontAtributeChoice);
        this.fontExampleLabel = new Label(" abc 123");
        fontAtributePanel.add(this.fontExampleLabel);
        mainFontGridBag.setConstraints(fontAtributePanel, mainFontConstraints);
        mainFontPanel.add(fontAtributePanel);
        final Panel fontControlPanel = new Panel();
        final Panel fontNamePanel = new Panel();
        final GridBagLayout fontNameGridBag = new GridBagLayout();
        fontNamePanel.setLayout(fontNameGridBag);
        final GridBagConstraints fontNameConstraints = new GridBagConstraints();
        fontNameConstraints.fill = 1;
        fontNameConstraints.weightx = 0.0;
        fontNameConstraints.weighty = 0.0;
        fontNameConstraints.ipadx = 2;
        fontNameConstraints.ipady = 2;
        fontNameConstraints.anchor = 10;
        fontNameConstraints.gridwidth = 0;
        final Label fontNameTitleLabel = new Label(Messages.getString("ConfigurationManagerDialog.21"), 0);
        fontNameGridBag.setConstraints(fontNameTitleLabel, fontNameConstraints);
        fontNamePanel.add(fontNameTitleLabel);
        this.fontSetupNameChoice = new Choice();
        for (int i = 0; i < ConfigurationManagerDialog.fontNames.length; ++i) {
            this.fontSetupNameChoice.add(ConfigurationManagerDialog.fontNames[i]);
        }
        fontNameGridBag.setConstraints(this.fontSetupNameChoice, fontNameConstraints);
        fontNamePanel.add(this.fontSetupNameChoice);
        fontControlPanel.add(fontNamePanel);
        final Panel fontSizePanel = new Panel();
        final GridBagLayout fontSizeGridBag = new GridBagLayout();
        fontSizePanel.setLayout(fontSizeGridBag);
        final GridBagConstraints fontSizeConstraints = new GridBagConstraints();
        fontSizeConstraints.fill = 1;
        fontSizeConstraints.weightx = 0.0;
        fontSizeConstraints.weighty = 0.0;
        fontSizeConstraints.ipadx = 2;
        fontSizeConstraints.ipady = 2;
        fontSizeConstraints.anchor = 10;
        fontSizeConstraints.gridwidth = 0;
        final Label fontSizeTitleLabel = new Label(Messages.getString("ConfigurationManagerDialog.22"), 0);
        fontSizeGridBag.setConstraints(fontSizeTitleLabel, fontSizeConstraints);
        fontSizePanel.add(fontSizeTitleLabel);
        this.fontSetupSizeChoice = new Choice();
        for (int j = 0; j < ConfigurationManagerDialog.fontSizes.length; ++j) {
            this.fontSetupSizeChoice.add("" + ConfigurationManagerDialog.fontSizes[j]);
        }
        fontSizeGridBag.setConstraints(this.fontSetupSizeChoice, fontSizeConstraints);
        fontSizePanel.add(this.fontSetupSizeChoice);
        fontControlPanel.add(fontSizePanel);
        final Panel fontStylePanel = new Panel();
        final GridBagLayout fontStyleGridBag = new GridBagLayout();
        fontStylePanel.setLayout(fontStyleGridBag);
        final GridBagConstraints fontStyleConstraints = new GridBagConstraints();
        fontStyleConstraints.fill = 1;
        fontStyleConstraints.weightx = 0.0;
        fontStyleConstraints.weighty = 0.0;
        fontStyleConstraints.ipadx = 2;
        fontStyleConstraints.ipady = 0;
        fontStyleConstraints.anchor = 10;
        fontStyleConstraints.gridwidth = 0;
        final Label fontStyleTitleLabel = new Label(Messages.getString("ConfigurationManagerDialog.24"), 0);
        fontStyleGridBag.setConstraints(fontStyleTitleLabel, fontStyleConstraints);
        fontStylePanel.add(fontStyleTitleLabel);
        final Panel optionPanel = new Panel();
        this.fontSetupStyleBoldCheckbox = new Checkbox(Messages.getString("ConfigurationManagerDialog.25"));
        optionPanel.add(this.fontSetupStyleBoldCheckbox);
        this.fontSetupStyleItalicCheckbox = new Checkbox(Messages.getString("ConfigurationManagerDialog.26"));
        optionPanel.add(this.fontSetupStyleItalicCheckbox);
        fontStyleGridBag.setConstraints(optionPanel, fontStyleConstraints);
        fontStylePanel.add(optionPanel);
        fontControlPanel.add(fontStylePanel);
        mainFontGridBag.setConstraints(fontControlPanel, mainFontConstraints);
        mainFontPanel.add(fontControlPanel);
        return mainFontPanel;
    }
    
    protected void initialiseGUI() {
        final Panel mainPanel = new Panel();
        final GridBagLayout mainGridBag = new GridBagLayout();
        mainPanel.setLayout(mainGridBag);
        final GridBagConstraints mainConstraints = new GridBagConstraints();
        mainConstraints.fill = 1;
        mainConstraints.weightx = 0.0;
        mainConstraints.weighty = 0.0;
        mainConstraints.ipadx = 2;
        mainConstraints.ipady = 2;
        mainConstraints.insets = new Insets(2, 2, 2, 2);
        mainConstraints.anchor = 11;
        mainConstraints.gridwidth = 0;
        final Panel color = this.getColorPanel();
        final Panel font = this.getFontPanel();
        final Panel colorAndFont = new Panel();
        colorAndFont.add(color);
        colorAndFont.add(font);
        mainGridBag.setConstraints(colorAndFont, mainConstraints);
        mainPanel.add(colorAndFont);
        final Panel control = this.getControlPanel();
        mainGridBag.setConstraints(control, mainConstraints);
        mainPanel.add(control);
        this.add(mainPanel);
    }
    
    protected void reject() {
        this.setVisible(false);
    }
    
    protected void restore() {
        for (int i = 0; i < this.fontSelections.size(); ++i) {
            final FontSelection fontSelection = (FontSelection)((FontSelection)this.fontSelections.elementAt(i));
            fontSelection.restore();
        }
        for (int i = 0; i < this.colorSelections.size(); ++i) {
            final ColorSelection colorSelection = (ColorSelection)((ColorSelection)this.colorSelections.elementAt(i));
            colorSelection.restore();
        }
        this.updateColor(false);
        this.updateFont(false);
    }
    
    protected void restoreDefault() {
        for (int i = 0; i < this.fontSelections.size(); ++i) {
            final FontSelection fontSelection = (FontSelection)((FontSelection)this.fontSelections.elementAt(i));
            fontSelection.restoreOriginal();
        }
        for (int i = 0; i < this.colorSelections.size(); ++i) {
            final ColorSelection colorSelection = (ColorSelection)((ColorSelection)this.colorSelections.elementAt(i));
            colorSelection.restoreOriginal();
        }
        this.updateColor(false);
        this.updateFont(false);
    }
    
    protected void setupListeners() {
        this.colorAtributeChoice.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent e) {
                ConfigurationManagerDialog.this.updateColor(false);
            }
        });
        this.colorSetupRedScrollbar.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(final AdjustmentEvent e) {
                ConfigurationManagerDialog.this.updateColor(true);
            }
        });
        this.colorSetupGreenScrollbar.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(final AdjustmentEvent e) {
                ConfigurationManagerDialog.this.updateColor(true);
            }
        });
        this.colorSetupBlueScrollbar.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(final AdjustmentEvent e) {
                ConfigurationManagerDialog.this.updateColor(true);
            }
        });
        this.fontAtributeChoice.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent e) {
                ConfigurationManagerDialog.this.updateFont(false);
            }
        });
        this.fontSetupNameChoice.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent e) {
                ConfigurationManagerDialog.this.updateFont(true);
            }
        });
        this.fontSetupSizeChoice.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent e) {
                ConfigurationManagerDialog.this.updateFont(true);
            }
        });
        this.fontSetupStyleBoldCheckbox.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent e) {
                ConfigurationManagerDialog.this.updateFont(true);
            }
        });
        this.fontSetupStyleItalicCheckbox.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent e) {
                ConfigurationManagerDialog.this.updateFont(true);
            }
        });
        this.okButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                ConfigurationManagerDialog.this.accept();
            }
        });
        this.defaultButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                ConfigurationManagerDialog.this.restoreDefault();
            }
        });
        this.cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                ConfigurationManagerDialog.this.reject();
            }
        });
    }
    
    protected void updateColor(final boolean adjusted) {
        final String selected = this.colorAtributeChoice.getSelectedItem();
        for (int i = 0; i < this.colorSelections.size(); ++i) {
            final ColorSelection colorSelection = (ColorSelection)((ColorSelection)this.colorSelections.elementAt(i));
            if (colorSelection.getAtributeName().equalsIgnoreCase(selected)) {
                if (adjusted) {
                    colorSelection.setColor(new Color(this.colorSetupRedScrollbar.getValue(), this.colorSetupGreenScrollbar.getValue(), this.colorSetupBlueScrollbar.getValue()));
                }
                this.displayColor(colorSelection);
                return;
            }
        }
    }
    
    protected void updateFont(final boolean adjusted) {
        final String selected = this.fontAtributeChoice.getSelectedItem();
        for (int i = 0; i < this.fontSelections.size(); ++i) {
            final FontSelection fontSelection = (FontSelection)((FontSelection)this.fontSelections.elementAt(i));
            if (fontSelection.getAtributeName().equalsIgnoreCase(selected)) {
                if (adjusted) {
                    int style = 0;
                    if (this.fontSetupStyleBoldCheckbox.getState() && this.fontSetupStyleItalicCheckbox.getState()) {
                        style = 3;
                    }
                    else if (this.fontSetupStyleBoldCheckbox.getState()) {
                        style = 1;
                    }
                    else if (this.fontSetupStyleItalicCheckbox.getState()) {
                        style = 2;
                    }
                    final String stringSize = this.fontSetupSizeChoice.getSelectedItem();
                    int size = 10;
                    int j = 0;
                    while (j < ConfigurationManagerDialog.fontSizes.length) {
                        if ((ConfigurationManagerDialog.fontSizes[j] + "").equalsIgnoreCase(stringSize)) {
                            size = ConfigurationManagerDialog.fontSizes[j];
                            break;
                        }
                        ++j;
                    }
                    fontSelection.setFont(new Font(this.fontSetupNameChoice.getSelectedItem(), style, size));
                }
                this.displayFont(fontSelection);
                return;
            }
        }
    }
    
    static {
        ConfigurationManagerDialog.DEFAULT_TITLE = Messages.getString("ConfigurationManagerDialog.0");
        ConfigurationManagerDialog.fontNames = new String[] { "Courier", "Dialog", "DialogInput", "Helvetica", "TimesRoman", "Monospaced", "SansSerif", "Serif" };
        ConfigurationManagerDialog.fontSizes = new int[] { 8, 10, 12, 14, 16, 18, 20 };
    }
}
