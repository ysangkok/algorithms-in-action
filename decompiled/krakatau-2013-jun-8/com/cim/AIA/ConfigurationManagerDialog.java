package com.cim.AIA;

public class ConfigurationManagerDialog extends java.awt.Dialog {
    final private static long serialVersionUID = -1467986213981708372L;
    protected static String DEFAULT_TITLE;
    protected static String[] fontNames;
    protected static int[] fontSizes;
    protected java.util.Vector fontSelections;
    protected java.util.Vector colorSelections;
    protected java.awt.Choice colorAtributeChoice;
    protected java.awt.Panel colorExamplePanel;
    protected java.awt.Scrollbar colorSetupRedScrollbar;
    protected java.awt.Scrollbar colorSetupGreenScrollbar;
    protected java.awt.Scrollbar colorSetupBlueScrollbar;
    protected java.awt.Choice fontAtributeChoice;
    protected java.awt.Choice fontSetupNameChoice;
    protected java.awt.Choice fontSetupSizeChoice;
    protected java.awt.Checkbox fontSetupStyleBoldCheckbox;
    protected java.awt.Checkbox fontSetupStyleItalicCheckbox;
    java.awt.Label fontExampleLabel;
    protected java.awt.Button okButton;
    protected java.awt.Button defaultButton;
    protected java.awt.Button cancelButton;
    protected boolean acceptChanges;
    
    public ConfigurationManagerDialog(java.awt.Frame a, String s, boolean b, java.util.Vector a0, java.util.Vector a1)
    {
        super(a, s, b);
        this.acceptChanges = false;
        this.fontSelections = a0;
        this.colorSelections = a1;
        this.setTitle(s);
        this.initialiseGUI();
        this.addFontSelections();
        this.addColorSelections();
        this.setupListeners();
        this.updateColor(false);
        this.updateFont(false);
        this.pack();
        java.awt.Toolkit a2 = this.getToolkit();
        java.awt.Dimension a3 = a2.getScreenSize();
        int i = a3.width;
        java.awt.Dimension a4 = this.getSize();
        int i0 = a4.width;
        int i1 = i - i0;
        int i2 = i1 / 2;
        int i3 = a3.height;
        java.awt.Dimension a5 = this.getSize();
        int i4 = a5.height;
        int i5 = i3 - i4;
        int i6 = i5 / 2;
        this.setLocation(i2, i6);
    }
    
    public ConfigurationManagerDialog(java.util.Vector a, java.util.Vector a0)
    {
        java.awt.Frame a1 = new java.awt.Frame();
        String s = com.cim.AIA.ConfigurationManagerDialog.DEFAULT_TITLE;
        this(a1, s, true, a, a0);
    }
    
    protected void accept()
    {
        this.acceptChanges = true;
        this.setVisible(false);
    }
    
    public boolean acceptChanges()
    {
        int i = this.acceptChanges?1:0;
        return i != 0;
    }
    
    protected void addColorSelections()
    {
        int i = 0;
        while(true)
        {
            java.util.Vector a = this.colorSelections;
            int i0 = a.size();
            if(i >= i0)
            {
                return;
            }
            else
            {
                java.util.Vector a0 = this.colorSelections;
                Object a1 = a0.elementAt(i);
                com.cim.AIA.ColorSelection dummy = (com.cim.AIA.ColorSelection)a1;
                com.cim.AIA.ColorSelection a2 = (com.cim.AIA.ColorSelection)a1;
                java.awt.Choice a3 = this.colorAtributeChoice;
                String s = a2.getAtributeName();
                a3.add(s);
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    protected void addFontSelections()
    {
        int i = 0;
        while(true)
        {
            java.util.Vector a = this.fontSelections;
            int i0 = a.size();
            if(i >= i0)
            {
                return;
            }
            else
            {
                java.util.Vector a0 = this.fontSelections;
                Object a1 = a0.elementAt(i);
                com.cim.AIA.FontSelection dummy = (com.cim.AIA.FontSelection)a1;
                com.cim.AIA.FontSelection a2 = (com.cim.AIA.FontSelection)a1;
                java.awt.Choice a3 = this.fontAtributeChoice;
                String s = a2.getAtributeName();
                a3.add(s);
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    protected void displayColor(com.cim.AIA.ColorSelection a)
    {
        java.awt.Color a0 = a.getColor();
        java.awt.Panel a1 = this.colorExamplePanel;
        a1.setBackground(a0);
        java.awt.Scrollbar a2 = this.colorSetupRedScrollbar;
        int i = a0.getRed();
        a2.setValue(i);
        java.awt.Scrollbar a3 = this.colorSetupGreenScrollbar;
        int i0 = a0.getGreen();
        a3.setValue(i0);
        java.awt.Scrollbar a4 = this.colorSetupBlueScrollbar;
        int i1 = a0.getBlue();
        a4.setValue(i1);
    }
    
    protected void displayFont(com.cim.AIA.FontSelection a)
    {
        java.awt.Font a0 = a.getFont();
        java.awt.Label a1 = this.fontExampleLabel;
        a1.setFont(a0);
        java.awt.Choice a2 = this.fontSetupNameChoice;
        String s = a0.getName();
        a2.select(s);
        java.awt.Choice a3 = this.fontSetupSizeChoice;
        StringBuilder a4 = new StringBuilder();
        StringBuilder a5 = a4.append("");
        int i = a0.getSize();
        StringBuilder a6 = a5.append(i);
        String s0 = a6.toString();
        a3.select(s0);
        java.awt.Checkbox a7 = this.fontSetupStyleBoldCheckbox;
        int i0 = a0.isBold()?1:0;
        a7.setState(i0 != 0);
        java.awt.Checkbox a8 = this.fontSetupStyleItalicCheckbox;
        int i1 = a0.isItalic()?1:0;
        a8.setState(i1 != 0);
    }
    
    protected java.awt.Panel getColorPanel()
    {
        java.awt.Panel a = new java.awt.Panel();
        java.awt.GridBagLayout a0 = new java.awt.GridBagLayout();
        a.setLayout((java.awt.LayoutManager)a0);
        java.awt.GridBagConstraints a1 = new java.awt.GridBagConstraints();
        a1.fill = 1;
        a1.weightx = 0.0;
        a1.weighty = 0.0;
        a1.ipadx = 2;
        a1.ipady = 2;
        a1.anchor = 11;
        a1.gridwidth = 0;
        String s = localization.Messages.getString("ConfigurationManagerDialog.11");
        java.awt.Label a2 = new java.awt.Label(s, 1);
        a0.setConstraints((java.awt.Component)a2, a1);
        java.awt.Component a3 = a.add((java.awt.Component)a2);
        java.awt.Panel a4 = new java.awt.Panel();
        java.awt.GridLayout a5 = new java.awt.GridLayout(1, 2);
        a4.setLayout((java.awt.LayoutManager)a5);
        String s0 = localization.Messages.getString("ConfigurationManagerDialog.12");
        java.awt.Label a6 = new java.awt.Label(s0, 0);
        java.awt.Component a7 = a4.add((java.awt.Component)a6);
        String s1 = localization.Messages.getString("ConfigurationManagerDialog.13");
        java.awt.Label a8 = new java.awt.Label(s1, 0);
        java.awt.Component a9 = a4.add((java.awt.Component)a8);
        a0.setConstraints((java.awt.Component)a4, a1);
        java.awt.Component a10 = a.add((java.awt.Component)a4);
        java.awt.Panel a11 = new java.awt.Panel();
        java.awt.GridLayout a12 = new java.awt.GridLayout(1, 2);
        a11.setLayout((java.awt.LayoutManager)a12);
        java.awt.Choice a13 = new java.awt.Choice();
        this.colorAtributeChoice = a13;
        java.awt.Choice a14 = this.colorAtributeChoice;
        java.awt.Component a15 = a11.add((java.awt.Component)a14);
        java.awt.Panel a16 = new java.awt.Panel();
        this.colorExamplePanel = a16;
        java.awt.Panel a17 = this.colorExamplePanel;
        java.awt.Component a18 = a11.add((java.awt.Component)a17);
        a0.setConstraints((java.awt.Component)a11, a1);
        java.awt.Component a19 = a.add((java.awt.Component)a11);
        java.awt.Panel a20 = new java.awt.Panel();
        java.awt.GridBagLayout a21 = new java.awt.GridBagLayout();
        a20.setLayout((java.awt.LayoutManager)a21);
        java.awt.GridBagConstraints a22 = new java.awt.GridBagConstraints();
        a22.fill = 2;
        a22.weightx = 1.0;
        a22.weighty = 0.0;
        a22.ipadx = 2;
        a22.ipady = 2;
        a22.anchor = 10;
        java.awt.Panel a23 = new java.awt.Panel();
        java.awt.GridBagLayout a24 = new java.awt.GridBagLayout();
        a23.setLayout((java.awt.LayoutManager)a24);
        java.awt.GridBagConstraints a25 = new java.awt.GridBagConstraints();
        a25.fill = 2;
        a25.weightx = 0.0;
        a25.weighty = 0.0;
        a25.ipadx = 2;
        a25.ipady = 2;
        a25.anchor = 10;
        a25.gridwidth = 0;
        String s2 = localization.Messages.getString("ConfigurationManagerDialog.14");
        java.awt.Label a26 = new java.awt.Label(s2, 1);
        a24.setConstraints((java.awt.Component)a26, a25);
        java.awt.Component a27 = a23.add((java.awt.Component)a26);
        java.awt.Scrollbar a28 = new java.awt.Scrollbar(0, 128, 1, 0, 256);
        this.colorSetupRedScrollbar = a28;
        java.awt.Scrollbar a29 = this.colorSetupRedScrollbar;
        a24.setConstraints((java.awt.Component)a29, a25);
        java.awt.Scrollbar a30 = this.colorSetupRedScrollbar;
        java.awt.Component a31 = a23.add((java.awt.Component)a30);
        a21.setConstraints((java.awt.Component)a23, a22);
        java.awt.Component a32 = a20.add((java.awt.Component)a23);
        java.awt.Panel a33 = new java.awt.Panel();
        java.awt.GridBagLayout a34 = new java.awt.GridBagLayout();
        a33.setLayout((java.awt.LayoutManager)a34);
        java.awt.GridBagConstraints a35 = new java.awt.GridBagConstraints();
        a35.fill = 2;
        a35.weightx = 0.0;
        a35.weighty = 0.0;
        a35.ipadx = 2;
        a35.ipady = 2;
        a35.anchor = 10;
        a35.gridwidth = 0;
        String s3 = localization.Messages.getString("ConfigurationManagerDialog.15");
        java.awt.Label a36 = new java.awt.Label(s3, 1);
        a34.setConstraints((java.awt.Component)a36, a35);
        java.awt.Component a37 = a33.add((java.awt.Component)a36);
        java.awt.Scrollbar a38 = new java.awt.Scrollbar(0, 128, 1, 0, 256);
        this.colorSetupGreenScrollbar = a38;
        java.awt.Scrollbar a39 = this.colorSetupGreenScrollbar;
        a34.setConstraints((java.awt.Component)a39, a35);
        java.awt.Scrollbar a40 = this.colorSetupGreenScrollbar;
        java.awt.Component a41 = a33.add((java.awt.Component)a40);
        a21.setConstraints((java.awt.Component)a33, a22);
        java.awt.Component a42 = a20.add((java.awt.Component)a33);
        java.awt.Panel a43 = new java.awt.Panel();
        java.awt.GridBagLayout a44 = new java.awt.GridBagLayout();
        a43.setLayout((java.awt.LayoutManager)a44);
        java.awt.GridBagConstraints a45 = new java.awt.GridBagConstraints();
        a45.fill = 2;
        a45.weightx = 0.0;
        a45.weighty = 0.0;
        a45.ipadx = 2;
        a45.ipady = 2;
        a45.anchor = 10;
        a45.gridwidth = 0;
        String s4 = localization.Messages.getString("ConfigurationManagerDialog.16");
        java.awt.Label a46 = new java.awt.Label(s4, 1);
        a44.setConstraints((java.awt.Component)a46, a45);
        java.awt.Component a47 = a43.add((java.awt.Component)a46);
        java.awt.Scrollbar a48 = new java.awt.Scrollbar(0, 128, 1, 0, 256);
        this.colorSetupBlueScrollbar = a48;
        java.awt.Scrollbar a49 = this.colorSetupBlueScrollbar;
        a44.setConstraints((java.awt.Component)a49, a45);
        java.awt.Scrollbar a50 = this.colorSetupBlueScrollbar;
        java.awt.Component a51 = a43.add((java.awt.Component)a50);
        a21.setConstraints((java.awt.Component)a43, a22);
        java.awt.Component a52 = a20.add((java.awt.Component)a43);
        a0.setConstraints((java.awt.Component)a20, a1);
        java.awt.Component a53 = a.add((java.awt.Component)a20);
        return a;
    }
    
    protected java.awt.Panel getControlPanel()
    {
        java.awt.Panel a = new java.awt.Panel();
        String s = localization.Messages.getString("ConfigurationManagerDialog.27");
        java.awt.Button a0 = new java.awt.Button(s);
        this.okButton = a0;
        String s0 = localization.Messages.getString("ConfigurationManagerDialog.28");
        java.awt.Button a1 = new java.awt.Button(s0);
        this.defaultButton = a1;
        String s1 = localization.Messages.getString("ConfigurationManagerDialog.29");
        java.awt.Button a2 = new java.awt.Button(s1);
        this.cancelButton = a2;
        java.awt.Button a3 = this.okButton;
        java.awt.Component a4 = a.add((java.awt.Component)a3);
        java.awt.Button a5 = this.defaultButton;
        java.awt.Component a6 = a.add((java.awt.Component)a5);
        java.awt.Button a7 = this.cancelButton;
        java.awt.Component a8 = a.add((java.awt.Component)a7);
        return a;
    }
    
    protected java.awt.Panel getFontPanel()
    {
        java.awt.Panel a = new java.awt.Panel();
        java.awt.GridBagLayout a0 = new java.awt.GridBagLayout();
        a.setLayout((java.awt.LayoutManager)a0);
        java.awt.GridBagConstraints a1 = new java.awt.GridBagConstraints();
        a1.fill = 1;
        a1.weightx = 0.0;
        a1.weighty = 0.0;
        a1.ipadx = 2;
        a1.ipady = 2;
        a1.anchor = 11;
        a1.gridwidth = 0;
        String s = localization.Messages.getString("ConfigurationManagerDialog.17");
        java.awt.Label a2 = new java.awt.Label(s, 1);
        a0.setConstraints((java.awt.Component)a2, a1);
        java.awt.Component a3 = a.add((java.awt.Component)a2);
        java.awt.Panel a4 = new java.awt.Panel();
        java.awt.GridLayout a5 = new java.awt.GridLayout(1, 2);
        a4.setLayout((java.awt.LayoutManager)a5);
        String s0 = localization.Messages.getString("ConfigurationManagerDialog.18");
        java.awt.Label a6 = new java.awt.Label(s0, 0);
        java.awt.Component a7 = a4.add((java.awt.Component)a6);
        String s1 = localization.Messages.getString("ConfigurationManagerDialog.19");
        java.awt.Label a8 = new java.awt.Label(s1, 0);
        java.awt.Component a9 = a4.add((java.awt.Component)a8);
        a0.setConstraints((java.awt.Component)a4, a1);
        java.awt.Component a10 = a.add((java.awt.Component)a4);
        java.awt.Panel a11 = new java.awt.Panel();
        java.awt.GridLayout a12 = new java.awt.GridLayout(1, 2);
        a11.setLayout((java.awt.LayoutManager)a12);
        java.awt.Choice a13 = new java.awt.Choice();
        this.fontAtributeChoice = a13;
        java.awt.Choice a14 = this.fontAtributeChoice;
        java.awt.Component a15 = a11.add((java.awt.Component)a14);
        java.awt.Label a16 = new java.awt.Label(" abc 123");
        this.fontExampleLabel = a16;
        java.awt.Label a17 = this.fontExampleLabel;
        java.awt.Component a18 = a11.add((java.awt.Component)a17);
        a0.setConstraints((java.awt.Component)a11, a1);
        java.awt.Component a19 = a.add((java.awt.Component)a11);
        java.awt.Panel a20 = new java.awt.Panel();
        java.awt.Panel a21 = new java.awt.Panel();
        java.awt.GridBagLayout a22 = new java.awt.GridBagLayout();
        a21.setLayout((java.awt.LayoutManager)a22);
        java.awt.GridBagConstraints a23 = new java.awt.GridBagConstraints();
        a23.fill = 1;
        a23.weightx = 0.0;
        a23.weighty = 0.0;
        a23.ipadx = 2;
        a23.ipady = 2;
        a23.anchor = 10;
        a23.gridwidth = 0;
        String s2 = localization.Messages.getString("ConfigurationManagerDialog.21");
        java.awt.Label a24 = new java.awt.Label(s2, 0);
        a22.setConstraints((java.awt.Component)a24, a23);
        java.awt.Component a25 = a21.add((java.awt.Component)a24);
        java.awt.Choice a26 = new java.awt.Choice();
        this.fontSetupNameChoice = a26;
        int i = 0;
        while(true)
        {
            String[] a27 = com.cim.AIA.ConfigurationManagerDialog.fontNames;
            int i0 = a27.length;
            if(i >= i0)
            {
                break;
            }
            else
            {
                java.awt.Choice a28 = this.fontSetupNameChoice;
                String[] a29 = com.cim.AIA.ConfigurationManagerDialog.fontNames;
                String s3 = a29[i];
                a28.add(s3);
                int i1 = i + 1;
                i = i1;
            }
        }
        java.awt.Choice a30 = this.fontSetupNameChoice;
        a22.setConstraints((java.awt.Component)a30, a23);
        java.awt.Choice a31 = this.fontSetupNameChoice;
        java.awt.Component a32 = a21.add((java.awt.Component)a31);
        java.awt.Component a33 = a20.add((java.awt.Component)a21);
        java.awt.Panel a34 = new java.awt.Panel();
        java.awt.GridBagLayout a35 = new java.awt.GridBagLayout();
        a34.setLayout((java.awt.LayoutManager)a35);
        java.awt.GridBagConstraints a36 = new java.awt.GridBagConstraints();
        a36.fill = 1;
        a36.weightx = 0.0;
        a36.weighty = 0.0;
        a36.ipadx = 2;
        a36.ipady = 2;
        a36.anchor = 10;
        a36.gridwidth = 0;
        String s4 = localization.Messages.getString("ConfigurationManagerDialog.22");
        java.awt.Label a37 = new java.awt.Label(s4, 0);
        a35.setConstraints((java.awt.Component)a37, a36);
        java.awt.Component a38 = a34.add((java.awt.Component)a37);
        java.awt.Choice a39 = new java.awt.Choice();
        this.fontSetupSizeChoice = a39;
        int i2 = 0;
        while(true)
        {
            int[] a40 = com.cim.AIA.ConfigurationManagerDialog.fontSizes;
            int i3 = a40.length;
            if(i2 >= i3)
            {
                java.awt.Choice a41 = this.fontSetupSizeChoice;
                a35.setConstraints((java.awt.Component)a41, a36);
                java.awt.Choice a42 = this.fontSetupSizeChoice;
                java.awt.Component a43 = a34.add((java.awt.Component)a42);
                java.awt.Component a44 = a20.add((java.awt.Component)a34);
                java.awt.Panel a45 = new java.awt.Panel();
                java.awt.GridBagLayout a46 = new java.awt.GridBagLayout();
                a45.setLayout((java.awt.LayoutManager)a46);
                java.awt.GridBagConstraints a47 = new java.awt.GridBagConstraints();
                a47.fill = 1;
                a47.weightx = 0.0;
                a47.weighty = 0.0;
                a47.ipadx = 2;
                a47.ipady = 0;
                a47.anchor = 10;
                a47.gridwidth = 0;
                String s5 = localization.Messages.getString("ConfigurationManagerDialog.24");
                java.awt.Label a48 = new java.awt.Label(s5, 0);
                a46.setConstraints((java.awt.Component)a48, a47);
                java.awt.Component a49 = a45.add((java.awt.Component)a48);
                java.awt.Panel a50 = new java.awt.Panel();
                String s6 = localization.Messages.getString("ConfigurationManagerDialog.25");
                java.awt.Checkbox a51 = new java.awt.Checkbox(s6);
                this.fontSetupStyleBoldCheckbox = a51;
                java.awt.Checkbox a52 = this.fontSetupStyleBoldCheckbox;
                java.awt.Component a53 = a50.add((java.awt.Component)a52);
                String s7 = localization.Messages.getString("ConfigurationManagerDialog.26");
                java.awt.Checkbox a54 = new java.awt.Checkbox(s7);
                this.fontSetupStyleItalicCheckbox = a54;
                java.awt.Checkbox a55 = this.fontSetupStyleItalicCheckbox;
                java.awt.Component a56 = a50.add((java.awt.Component)a55);
                a46.setConstraints((java.awt.Component)a50, a47);
                java.awt.Component a57 = a45.add((java.awt.Component)a50);
                java.awt.Component a58 = a20.add((java.awt.Component)a45);
                a0.setConstraints((java.awt.Component)a20, a1);
                java.awt.Component a59 = a.add((java.awt.Component)a20);
                return a;
            }
            else
            {
                java.awt.Choice a60 = this.fontSetupSizeChoice;
                StringBuilder a61 = new StringBuilder();
                StringBuilder a62 = a61.append("");
                int[] a63 = com.cim.AIA.ConfigurationManagerDialog.fontSizes;
                int i4 = a63[i2];
                StringBuilder a64 = a62.append(i4);
                String s8 = a64.toString();
                a60.add(s8);
                int i5 = i2 + 1;
                i2 = i5;
            }
        }
    }
    
    protected void initialiseGUI()
    {
        java.awt.Panel a = new java.awt.Panel();
        java.awt.GridBagLayout a0 = new java.awt.GridBagLayout();
        a.setLayout((java.awt.LayoutManager)a0);
        java.awt.GridBagConstraints a1 = new java.awt.GridBagConstraints();
        a1.fill = 1;
        a1.weightx = 0.0;
        a1.weighty = 0.0;
        a1.ipadx = 2;
        a1.ipady = 2;
        java.awt.Insets a2 = new java.awt.Insets(2, 2, 2, 2);
        a1.insets = a2;
        a1.anchor = 11;
        a1.gridwidth = 0;
        java.awt.Panel a3 = this.getColorPanel();
        java.awt.Panel a4 = this.getFontPanel();
        java.awt.Panel a5 = new java.awt.Panel();
        java.awt.Component a6 = a5.add((java.awt.Component)a3);
        java.awt.Component a7 = a5.add((java.awt.Component)a4);
        a0.setConstraints((java.awt.Component)a5, a1);
        java.awt.Component a8 = a.add((java.awt.Component)a5);
        java.awt.Panel a9 = this.getControlPanel();
        a0.setConstraints((java.awt.Component)a9, a1);
        java.awt.Component a10 = a.add((java.awt.Component)a9);
        java.awt.Component a11 = this.add((java.awt.Component)a);
    }
    
    protected void reject()
    {
        this.setVisible(false);
    }
    
    protected void restore()
    {
        int i = 0;
        while(true)
        {
            java.util.Vector a = this.fontSelections;
            int i0 = a.size();
            if(i >= i0)
            {
                break;
            }
            else
            {
                java.util.Vector a0 = this.fontSelections;
                Object a1 = a0.elementAt(i);
                com.cim.AIA.FontSelection dummy = (com.cim.AIA.FontSelection)a1;
                com.cim.AIA.FontSelection a2 = (com.cim.AIA.FontSelection)a1;
                a2.restore();
                int i1 = i + 1;
                i = i1;
            }
        }
        int i2 = 0;
        while(true)
        {
            java.util.Vector a3 = this.colorSelections;
            int i3 = a3.size();
            if(i2 >= i3)
            {
                this.updateColor(false);
                this.updateFont(false);
                return;
            }
            else
            {
                java.util.Vector a4 = this.colorSelections;
                Object a5 = a4.elementAt(i2);
                com.cim.AIA.ColorSelection dummy0 = (com.cim.AIA.ColorSelection)a5;
                com.cim.AIA.ColorSelection a6 = (com.cim.AIA.ColorSelection)a5;
                a6.restore();
                int i4 = i2 + 1;
                i2 = i4;
            }
        }
    }
    
    protected void restoreDefault()
    {
        int i = 0;
        while(true)
        {
            java.util.Vector a = this.fontSelections;
            int i0 = a.size();
            if(i >= i0)
            {
                break;
            }
            else
            {
                java.util.Vector a0 = this.fontSelections;
                Object a1 = a0.elementAt(i);
                com.cim.AIA.FontSelection dummy = (com.cim.AIA.FontSelection)a1;
                com.cim.AIA.FontSelection a2 = (com.cim.AIA.FontSelection)a1;
                a2.restoreOriginal();
                int i1 = i + 1;
                i = i1;
            }
        }
        int i2 = 0;
        while(true)
        {
            java.util.Vector a3 = this.colorSelections;
            int i3 = a3.size();
            if(i2 >= i3)
            {
                this.updateColor(false);
                this.updateFont(false);
                return;
            }
            else
            {
                java.util.Vector a4 = this.colorSelections;
                Object a5 = a4.elementAt(i2);
                com.cim.AIA.ColorSelection dummy0 = (com.cim.AIA.ColorSelection)a5;
                com.cim.AIA.ColorSelection a6 = (com.cim.AIA.ColorSelection)a5;
                a6.restoreOriginal();
                int i4 = i2 + 1;
                i2 = i4;
            }
        }
    }
    
    protected void setupListeners()
    {
        java.awt.Choice a = this.colorAtributeChoice;
        com.cim.AIA.ConfigurationManagerDialog$1 a0 = new com.cim.AIA.ConfigurationManagerDialog$1(this);
        a.addItemListener((java.awt.event.ItemListener)a0);
        java.awt.Scrollbar a1 = this.colorSetupRedScrollbar;
        com.cim.AIA.ConfigurationManagerDialog$2 a2 = new com.cim.AIA.ConfigurationManagerDialog$2(this);
        a1.addAdjustmentListener((java.awt.event.AdjustmentListener)a2);
        java.awt.Scrollbar a3 = this.colorSetupGreenScrollbar;
        com.cim.AIA.ConfigurationManagerDialog$3 a4 = new com.cim.AIA.ConfigurationManagerDialog$3(this);
        a3.addAdjustmentListener((java.awt.event.AdjustmentListener)a4);
        java.awt.Scrollbar a5 = this.colorSetupBlueScrollbar;
        com.cim.AIA.ConfigurationManagerDialog$4 a6 = new com.cim.AIA.ConfigurationManagerDialog$4(this);
        a5.addAdjustmentListener((java.awt.event.AdjustmentListener)a6);
        java.awt.Choice a7 = this.fontAtributeChoice;
        com.cim.AIA.ConfigurationManagerDialog$5 a8 = new com.cim.AIA.ConfigurationManagerDialog$5(this);
        a7.addItemListener((java.awt.event.ItemListener)a8);
        java.awt.Choice a9 = this.fontSetupNameChoice;
        com.cim.AIA.ConfigurationManagerDialog$6 a10 = new com.cim.AIA.ConfigurationManagerDialog$6(this);
        a9.addItemListener((java.awt.event.ItemListener)a10);
        java.awt.Choice a11 = this.fontSetupSizeChoice;
        com.cim.AIA.ConfigurationManagerDialog$7 a12 = new com.cim.AIA.ConfigurationManagerDialog$7(this);
        a11.addItemListener((java.awt.event.ItemListener)a12);
        java.awt.Checkbox a13 = this.fontSetupStyleBoldCheckbox;
        com.cim.AIA.ConfigurationManagerDialog$8 a14 = new com.cim.AIA.ConfigurationManagerDialog$8(this);
        a13.addItemListener((java.awt.event.ItemListener)a14);
        java.awt.Checkbox a15 = this.fontSetupStyleItalicCheckbox;
        com.cim.AIA.ConfigurationManagerDialog$9 a16 = new com.cim.AIA.ConfigurationManagerDialog$9(this);
        a15.addItemListener((java.awt.event.ItemListener)a16);
        java.awt.Button a17 = this.okButton;
        com.cim.AIA.ConfigurationManagerDialog$10 a18 = new com.cim.AIA.ConfigurationManagerDialog$10(this);
        a17.addActionListener((java.awt.event.ActionListener)a18);
        java.awt.Button a19 = this.defaultButton;
        com.cim.AIA.ConfigurationManagerDialog$11 a20 = new com.cim.AIA.ConfigurationManagerDialog$11(this);
        a19.addActionListener((java.awt.event.ActionListener)a20);
        java.awt.Button a21 = this.cancelButton;
        com.cim.AIA.ConfigurationManagerDialog$12 a22 = new com.cim.AIA.ConfigurationManagerDialog$12(this);
        a21.addActionListener((java.awt.event.ActionListener)a22);
    }
    
    protected void updateColor(boolean b)
    {
        java.awt.Choice a = this.colorAtributeChoice;
        int i = b?1:0;
        String s = a.getSelectedItem();
        int i0 = 0;
        label1: {
            com.cim.AIA.ColorSelection a0 = null;
            label0: {
                while(true)
                {
                    java.util.Vector a1 = this.colorSelections;
                    int i1 = a1.size();
                    if(i0 >= i1)
                    {
                        break;
                    }
                    java.util.Vector a2 = this.colorSelections;
                    Object a3 = a2.elementAt(i0);
                    com.cim.AIA.ColorSelection dummy = (com.cim.AIA.ColorSelection)a3;
                    a0 = (com.cim.AIA.ColorSelection)a3;
                    String s0 = a0.getAtributeName();
                    int i2 = s0.equalsIgnoreCase(s)?1:0;
                    if(i2 == 0)
                    {
                        int i3 = i0 + 1;
                        i0 = i3;
                        continue;
                    }
                    else
                    {
                        break label0;
                    }
                }
                break label1;
            }
            if(i != 0)
            {
                java.awt.Scrollbar a4 = this.colorSetupRedScrollbar;
                int i4 = a4.getValue();
                java.awt.Scrollbar a5 = this.colorSetupGreenScrollbar;
                int i5 = a5.getValue();
                java.awt.Scrollbar a6 = this.colorSetupBlueScrollbar;
                int i6 = a6.getValue();
                java.awt.Color a7 = new java.awt.Color(i4, i5, i6);
                a0.setColor(a7);
            }
            this.displayColor(a0);
        }
    }
    
    protected void updateFont(boolean b)
    {
        java.awt.Choice a = this.fontAtributeChoice;
        int i = b?1:0;
        String s = a.getSelectedItem();
        int i0 = 0;
        label1: {
            com.cim.AIA.FontSelection a0 = null;
            label0: {
                while(true)
                {
                    java.util.Vector a1 = this.fontSelections;
                    int i1 = a1.size();
                    if(i0 >= i1)
                    {
                        break;
                    }
                    java.util.Vector a2 = this.fontSelections;
                    Object a3 = a2.elementAt(i0);
                    com.cim.AIA.FontSelection dummy = (com.cim.AIA.FontSelection)a3;
                    a0 = (com.cim.AIA.FontSelection)a3;
                    String s0 = a0.getAtributeName();
                    int i2 = s0.equalsIgnoreCase(s)?1:0;
                    if(i2 == 0)
                    {
                        int i3 = i0 + 1;
                        i0 = i3;
                        continue;
                    }
                    else
                    {
                        break label0;
                    }
                }
                break label1;
            }
            label2: {
                int i4 = 0;
                int i5 = 0;
                if(i == 0)
                {
                    break label2;
                }
                java.awt.Checkbox a4 = this.fontSetupStyleBoldCheckbox;
                int i6 = a4.getState()?1:0;
                label4: {
                    label3: {
                        if(i6 == 0)
                        {
                            break label3;
                        }
                        java.awt.Checkbox a5 = this.fontSetupStyleItalicCheckbox;
                        int i7 = a5.getState()?1:0;
                        if(i7 == 0)
                        {
                            break label3;
                        }
                        i4 = 3;
                        break label4;
                    }
                    java.awt.Checkbox a6 = this.fontSetupStyleBoldCheckbox;
                    int i8 = a6.getState()?1:0;
                    label5: {
                        if(i8 == 0)
                        {
                            break label5;
                        }
                        i4 = 1;
                        break label4;
                    }
                    java.awt.Checkbox a7 = this.fontSetupStyleItalicCheckbox;
                    int i9 = a7.getState()?1:0;
                    i4 = i9 == 0?0:2;
                }
                java.awt.Choice a8 = this.fontSetupSizeChoice;
                String s1 = a8.getSelectedItem();
                int i10 = 0;
                while(true)
                {
                    int[] a9 = com.cim.AIA.ConfigurationManagerDialog.fontSizes;
                    int i11 = a9.length;
                    if(i10 >= i11)
                    {
                        i5 = 10;
                        break;
                    }
                    StringBuilder a10 = new StringBuilder();
                    int[] a11 = com.cim.AIA.ConfigurationManagerDialog.fontSizes;
                    int i12 = a11[i10];
                    StringBuilder a12 = a10.append(i12);
                    StringBuilder a13 = a12.append("");
                    String s2 = a13.toString();
                    int i13 = s2.equalsIgnoreCase(s1)?1:0;
                    if(i13 == 0)
                    {
                        int i14 = i10 + 1;
                        i10 = i14;
                        continue;
                    }
                    int[] a14 = com.cim.AIA.ConfigurationManagerDialog.fontSizes;
                    int i15 = a14[i10];
                    i5 = i15;
                    break;
                }
                java.awt.Choice a15 = this.fontSetupNameChoice;
                String s3 = a15.getSelectedItem();
                java.awt.Font a16 = new java.awt.Font(s3, i4, i5);
                a0.setFont(a16);
            }
            this.displayFont(a0);
        }
    }
    
    static
    {
        String s = localization.Messages.getString("ConfigurationManagerDialog.0");
        com.cim.AIA.ConfigurationManagerDialog.DEFAULT_TITLE = s;
        String[] a = new String[8];
        a[0] = "Courier";
        a[1] = "Dialog";
        a[2] = "DialogInput";
        a[3] = "Helvetica";
        a[4] = "TimesRoman";
        a[5] = "Monospaced";
        a[6] = "SansSerif";
        a[7] = "Serif";
        com.cim.AIA.ConfigurationManagerDialog.fontNames = a;
        int[] a0 = new int[7];
        a0[0] = 8;
        a0[1] = 10;
        a0[2] = 12;
        a0[3] = 14;
        a0[4] = 16;
        a0[5] = 18;
        a0[6] = 20;
        com.cim.AIA.ConfigurationManagerDialog.fontSizes = a0;
    }
}