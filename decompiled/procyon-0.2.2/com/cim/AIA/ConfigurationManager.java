package com.cim.AIA;

import java.util.*;

public class ConfigurationManager
{
    private static ConfigurationManager configurationManager;
    protected Vector<FontSelectionListener> fontSelectionListeners;
    protected Vector<ColorSelectionListener> colorSelectionListeners;
    protected Vector<FontSelection> fontSelections;
    protected Vector<ColorSelection> colorSelections;
    
    public static ConfigurationManager getConfigurationManager() {
        if (ConfigurationManager.configurationManager == null) {
            ConfigurationManager.configurationManager = new ConfigurationManager();
        }
        return ConfigurationManager.configurationManager;
    }
    
    public static void reset() {
        ConfigurationManager.configurationManager = null;
    }
    
    private ConfigurationManager() {
        super();
        this.fontSelectionListeners = new Vector();
        this.colorSelectionListeners = new Vector();
        this.fontSelections = new Vector();
        this.colorSelections = new Vector();
    }
    
    public void addColorSelection(final ColorSelection colorSelection) {
        this.colorSelections.addElement(colorSelection);
    }
    
    public void addColorSelectionListener(final ColorSelectionListener colorSelectionListener) {
        if (!this.colorSelectionListeners.contains(colorSelectionListener)) {
            this.colorSelectionListeners.addElement(colorSelectionListener);
        }
    }
    
    public void addFontSelection(final FontSelection fontSelection) {
        this.fontSelections.addElement(fontSelection);
    }
    
    public void addFontSelectionListener(final FontSelectionListener fontSelectionListener) {
        if (!this.fontSelectionListeners.contains(fontSelectionListener)) {
            this.fontSelectionListeners.addElement(fontSelectionListener);
        }
    }
    
    public void askUserForChanges() {
        for (int i = 0; i < this.fontSelections.size(); ++i) {
            final FontSelection fontSelection = (FontSelection)this.fontSelections.elementAt(i);
            fontSelection.backup();
        }
        for (int i = 0; i < this.colorSelections.size(); ++i) {
            final ColorSelection colorSelection = (ColorSelection)this.colorSelections.elementAt(i);
            colorSelection.backup();
        }
        final ConfigurationManagerDialog cmd = new ConfigurationManagerDialog(this.fontSelections, this.colorSelections);
        cmd.setVisible(true);
        this.informAll(cmd.acceptChanges());
        cmd.dispose();
    }
    
    protected void informAll(final boolean update) {
        int i;
        int j;
        for (i = 0; i < this.fontSelections.size(); ++i) {
            final FontSelection fontSelection = (FontSelection)this.fontSelections.elementAt(i);
            if (fontSelection.changed() && update) {
                j = 0;
                while (j < this.fontSelectionListeners.size()) {
                    final FontSelectionListener fontSelectionListener = (FontSelectionListener)this.fontSelectionListeners.elementAt(j);
                    if (fontSelection.appliesTo(fontSelectionListener)) {
                        fontSelectionListener.processFontSelection(fontSelection);
                    }
                    ++j;
                }
                fontSelection.accept();
            }
            else {
                fontSelection.restore();
            }
        }
        i = 0;
        int k = 0;
        while (i < this.colorSelections.size()) {
            final ColorSelection colorSelection = (ColorSelection)this.colorSelections.elementAt(i);
            if (colorSelection.changed() && update) {
                for (j = 0; j < this.colorSelectionListeners.size(); ++j) {
                    final ColorSelectionListener colorSelectionListener = (ColorSelectionListener)this.colorSelectionListeners.elementAt(j);
                    if (colorSelection.appliesTo(colorSelectionListener)) {
                        colorSelectionListener.processColorSelection(colorSelection);
                    }
                }
                colorSelection.accept();
            }
            colorSelection.restore();
            ++k;
        }
        if (update) {
            for (k = 0; k < this.fontSelectionListeners.size(); ++k) {
                final FontSelectionListener fontSelectionListener2 = (FontSelectionListener)this.fontSelectionListeners.elementAt(k);
                fontSelectionListener2.repaint();
            }
            for (k = 0; k < this.colorSelectionListeners.size(); ++k) {
                final ColorSelectionListener colorSelectionListener2 = (ColorSelectionListener)this.colorSelectionListeners.elementAt(k);
                colorSelectionListener2.repaint();
            }
        }
    }
    
    public void removeColorSelection(final ColorSelection colorSelection) {
        this.colorSelections.removeElement(colorSelection);
    }
    
    public void removeColorSelectionListener(final ColorSelectionListener colorSelectionListener) {
        if (this.colorSelectionListeners.contains(colorSelectionListener)) {
            this.colorSelectionListeners.removeElement(colorSelectionListener);
        }
    }
    
    public void removeFontSelection(final FontSelection fontSelection) {
        this.fontSelections.removeElement(fontSelection);
    }
    
    public void removeFontSelectionListener(final FontSelectionListener fontSelectionListener) {
        if (this.fontSelectionListeners.contains(fontSelectionListener)) {
            this.fontSelectionListeners.removeElement(fontSelectionListener);
        }
    }
}
