package com.cim.AIA;

public class HelpWindow extends java.awt.Frame implements com.cim.AIA.HelpListener, com.cim.AIA.ExitListener, com.cim.AIA.ColorSelectionListener, com.cim.AIA.FontSelectionListener {
    final private static long serialVersionUID = -1197005685030021893L;
    protected java.awt.TextArea infoTextArea;
    protected java.awt.ScrollPane sp;
    protected java.awt.Label label;
    
    public HelpWindow(String s)
    {
        super(s);
        java.awt.Color a = java.awt.Color.white;
        this.setBackground(a);
        java.awt.ScrollPane a0 = new java.awt.ScrollPane(0);
        this.sp = a0;
        java.awt.ScrollPane a1 = this.sp;
        java.awt.Component a2 = this.add((java.awt.Component)a1);
        java.awt.Panel a3 = new java.awt.Panel();
        java.awt.ScrollPane a4 = this.sp;
        java.awt.Component a5 = a4.add((java.awt.Component)a3);
        java.awt.GridBagLayout a6 = new java.awt.GridBagLayout();
        a3.setLayout((java.awt.LayoutManager)a6);
        java.awt.GridBagConstraints a7 = new java.awt.GridBagConstraints();
        a7.gridwidth = 0;
        a7.anchor = 10;
        a7.fill = 1;
        a7.ipadx = 0;
        a7.ipady = 0;
        a7.weightx = 0.0;
        a7.weighty = 0.0;
        java.awt.Font a8 = new java.awt.Font("Courier", 1, 12);
        java.awt.Label a9 = new java.awt.Label(" ", 0);
        this.label = a9;
        java.awt.Label a10 = this.label;
        a10.setFont(a8);
        java.awt.Label a11 = this.label;
        a6.setConstraints((java.awt.Component)a11, a7);
        java.awt.Label a12 = this.label;
        java.awt.Component a13 = a3.add((java.awt.Component)a12);
        a7.weightx = 4.0;
        a7.weighty = 4.0;
        java.awt.TextArea a14 = new java.awt.TextArea("", 2, 30, 1);
        this.infoTextArea = a14;
        java.awt.TextArea a15 = this.infoTextArea;
        a15.setEditable(false);
        java.awt.TextArea a16 = this.infoTextArea;
        a6.setConstraints((java.awt.Component)a16, a7);
        java.awt.TextArea a17 = this.infoTextArea;
        java.awt.Component a18 = a3.add((java.awt.Component)a17);
        com.cim.AIA.ConfigurationManager a19 = com.cim.AIA.ConfigurationManager.getConfigurationManager();
        a19.addColorSelectionListener((com.cim.AIA.ColorSelectionListener)this);
        com.cim.AIA.ConfigurationManager a20 = com.cim.AIA.ConfigurationManager.getConfigurationManager();
        a20.addFontSelectionListener((com.cim.AIA.FontSelectionListener)this);
    }
    
    public void cleanUp()
    {
        java.awt.ScrollPane a = this.sp;
        if(a != null)
        {
            java.awt.ScrollPane a0 = this.sp;
            a0.removeAll();
        }
        this.sp = null;
        this.infoTextArea = null;
        this.label = null;
    }
    
    public void clear()
    {
        java.awt.Label a = this.label;
        a.setText("");
        java.awt.TextArea a0 = this.infoTextArea;
        a0.setText("");
    }
    
    public String getClassName()
    {
        return "HelpWindow";
    }
    
    public void processColorSelection(com.cim.AIA.ColorSelection a)
    {
        String s = a.getAtributeName();
        String s0 = com.cim.AIA.ColorSelection.BACKGROUND;
        int i = s.equalsIgnoreCase(s0)?1:0;
        label1: {
            label0: {
                if(i == 0)
                {
                    break label0;
                }
                java.awt.TextArea a0 = this.infoTextArea;
                java.awt.Color a1 = a.getColor();
                a0.setBackground(a1);
                java.awt.Label a2 = this.label;
                java.awt.Color a3 = a.getColor();
                a2.setBackground(a3);
                break label1;
            }
            String s1 = com.cim.AIA.ColorSelection.TEXT_COLOR;
            int i0 = s.equalsIgnoreCase(s1)?1:0;
            if(i0 != 0)
            {
                java.awt.TextArea a4 = this.infoTextArea;
                java.awt.Color a5 = a.getColor();
                a4.setForeground(a5);
                java.awt.Label a6 = this.label;
                java.awt.Color a7 = a.getColor();
                a6.setForeground(a7);
            }
        }
    }
    
    public void processExitEvent(com.cim.AIA.ExitEvent a)
    {
        this.setVisible(false);
        this.removeAll();
        this.dispose();
        this.cleanUp();
    }
    
    public void processFontSelection(com.cim.AIA.FontSelection a)
    {
        String s = a.getAtributeName();
        int i = s.equalsIgnoreCase("Normal Font")?1:0;
        if(i != 0)
        {
            java.awt.TextArea a0 = this.infoTextArea;
            java.awt.Font a1 = a.getFont();
            a0.setFont(a1);
            java.awt.Font a2 = a.getFont();
            java.awt.Label a3 = this.label;
            String s0 = a2.getName();
            int i0 = a2.getSize();
            java.awt.Font a4 = new java.awt.Font(s0, 1, i0);
            a3.setFont(a4);
        }
    }
    
    public void processHelpEvent(com.cim.AIA.HelpEvent a)
    {
        String s = a.getTopic();
        this.setAction(s);
        String s0 = a.getInstructions();
        this.setInfoText(s0);
    }
    
    public void setAction(String s)
    {
        java.awt.Label a = this.label;
        String s0 = a.getText();
        int i = s0.compareTo(s);
        if(i != 0)
        {
            java.awt.Label a0 = this.label;
            StringBuilder a1 = new StringBuilder();
            StringBuilder a2 = a1.append(" ");
            StringBuilder a3 = a2.append(s);
            String s1 = a3.toString();
            a0.setText(s1);
        }
    }
    
    public void setInfoText(String s)
    {
        java.awt.TextArea a = this.infoTextArea;
        String s0 = a.getText();
        int i = s0.compareTo(s);
        if(i != 0)
        {
            java.awt.TextArea a0 = this.infoTextArea;
            a0.setText(s);
        }
    }
}