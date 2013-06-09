package com.cim.AIA;

public class SplitAlgorithmWindow extends com.cim.AIA.AlgorithmWindow implements com.cim.AIA.MethodSelectable, java.awt.event.ItemListener, com.cim.AIA.ControlListener {
    final private static long serialVersionUID = -8703106793389666711L;
    protected java.awt.CheckboxGroup methodCheckboxGroup;
    protected java.util.Vector methodRadioCheckboxes;
    protected java.awt.Panel methodPanel;
    protected java.util.Vector methodSelections;
    protected java.util.Vector methodSelectionListeners;
    protected com.cim.AIA.CodeCanvas secondCodeCanvas;
    protected java.awt.ScrollPane secondScrollPane;
    protected com.cim.AIA.MethodSelection currentMethodSelection;
    protected boolean enabledCheckboxes;
    protected int ratioTopAndBottom;
    
    public SplitAlgorithmWindow(String s, com.cim.AIA.CodeCanvas a)
    {
        java.awt.Dimension a0 = new java.awt.Dimension(500, 200);
        super(s, a, a0, false);
        this.enabledCheckboxes = true;
        this.ratioTopAndBottom = 33;
        java.awt.Dimension a1 = new java.awt.Dimension(500, 200);
        this.initGui(a1);
    }
    
    public SplitAlgorithmWindow(String s, com.cim.AIA.CodeCanvas a, java.awt.Dimension a0)
    {
        super(s, a, a0, false);
        this.enabledCheckboxes = true;
        this.ratioTopAndBottom = 33;
        this.initGui(a0);
    }
    
    public SplitAlgorithmWindow(String s, com.cim.AIA.CodeCanvas a, java.awt.Dimension a0, int i)
    {
        super(s, a, a0, false);
        this.enabledCheckboxes = true;
        label2: {
            label1: {
                label0: {
                    if(i <= 0)
                    {
                        break label0;
                    }
                    if(i < 100)
                    {
                        break label1;
                    }
                }
                this.ratioTopAndBottom = 33;
                break label2;
            }
            this.ratioTopAndBottom = i;
        }
        this.initGui(a0);
    }
    
    public void addMethodSelection(com.cim.AIA.MethodSelection a, boolean b)
    {
        java.util.Vector a0 = this.methodSelections;
        int i = b?1:0;
        a0.addElement((Object)a);
        String s = a.getName();
        java.awt.CheckboxGroup a1 = this.methodCheckboxGroup;
        java.awt.Checkbox a2 = new java.awt.Checkbox(s, i != 0, a1);
        a2.addItemListener((java.awt.event.ItemListener)this);
        java.util.Vector a3 = this.methodRadioCheckboxes;
        a3.addElement((Object)a2);
        java.awt.Panel a4 = this.methodPanel;
        java.awt.Component a5 = a4.add((java.awt.Component)a2);
        label0: {
            if(i == 0)
            {
                break label0;
            }
            com.cim.AIA.MethodSelection a6 = this.currentMethodSelection;
            if(a != a6)
            {
                this.setMethod(a);
                this.informMethodSelectionListeners(a);
            }
        }
    }
    
    public void addMethodSelectionListener(com.cim.AIA.MethodSelectionListener a)
    {
        java.util.Vector a0 = this.methodSelectionListeners;
        Object a1 = a;
        a0.addElement(a1);
    }
    
    private void buildConstraints(java.awt.GridBagConstraints a, int i, int i0, int i1, int i2, int i3, int i4)
    {
        a.gridx = i;
        a.gridy = i0;
        a.gridwidth = i1;
        a.gridheight = i2;
        double d = (double)i3;
        a.weightx = d;
        double d0 = (double)i4;
        a.weighty = d0;
    }
    
    public void cleanUp()
    {
        ((com.cim.AIA.AlgorithmWindow)this).cleanUp();
        this.methodCheckboxGroup = null;
        java.util.Vector a = this.methodRadioCheckboxes;
        if(a != null)
        {
            java.util.Vector a0 = this.methodRadioCheckboxes;
            a0.removeAllElements();
        }
        this.methodRadioCheckboxes = null;
        java.awt.Panel a1 = this.methodPanel;
        if(a1 != null)
        {
            java.awt.Panel a2 = this.methodPanel;
            a2.removeAll();
        }
        this.methodPanel = null;
        java.util.Vector a3 = this.methodSelections;
        if(a3 != null)
        {
            java.util.Vector a4 = this.methodSelections;
            a4.removeAllElements();
        }
        this.methodSelections = null;
        com.cim.AIA.CodeCanvas a5 = this.secondCodeCanvas;
        if(a5 != null)
        {
            com.cim.AIA.CodeCanvas a6 = this.secondCodeCanvas;
            a6.cleanUp();
        }
        this.secondCodeCanvas = null;
        java.awt.ScrollPane a7 = this.secondScrollPane;
        if(a7 != null)
        {
            java.awt.ScrollPane a8 = this.secondScrollPane;
            a8.removeAll();
        }
        this.secondScrollPane = null;
        this.currentMethodSelection = null;
    }
    
    protected void closeAll()
    {
        com.cim.AIA.CodeCanvas a = this.theCodeCanvas;
        a.collapseEntireTree();
        com.cim.AIA.CodeCanvas a0 = this.secondCodeCanvas;
        a0.collapseEntireTree();
    }
    
    public void controlBack(com.cim.AIA.ControlEvent a)
    {
    }
    
    public void controlOther(com.cim.AIA.ControlEvent a)
    {
        int i = a.getType();
        label1: {
            label0: {
                if(i != 123128)
                {
                    break label0;
                }
                this.setEnableCheckboxes(true);
                break label1;
            }
            int i0 = a.getType();
            if(i0 == 123129)
            {
                this.setEnableCheckboxes(true);
            }
        }
    }
    
    public void controlPause(com.cim.AIA.ControlEvent a)
    {
    }
    
    public void controlReset(com.cim.AIA.ControlEvent a)
    {
        this.setEnableCheckboxes(true);
    }
    
    public void controlRestart(com.cim.AIA.ControlEvent a)
    {
        this.setEnableCheckboxes(true);
    }
    
    public void controlRun(com.cim.AIA.ControlEvent a)
    {
        int i = this.enabledCheckboxes?1:0;
        if(i != 0)
        {
            this.setEnableCheckboxes(false);
        }
    }
    
    public void controlStep(com.cim.AIA.ControlEvent a)
    {
        int i = this.enabledCheckboxes?1:0;
        if(i != 0)
        {
            this.setEnableCheckboxes(false);
        }
    }
    
    public String getPosition()
    {
        String s = null;
        com.cim.AIA.CodeCanvas a = this.secondCodeCanvas;
        String s0 = a.getPosition();
        label1: {
            label0: {
                if(s0 == null)
                {
                    break label0;
                }
                if(s0 == "")
                {
                    break label0;
                }
                s = s0;
                break label1;
            }
            com.cim.AIA.CodeCanvas a0 = this.theCodeCanvas;
            String s1 = a0.getPosition();
            s = s1;
        }
        return s;
    }
    
    protected void informMethodSelectionListeners(com.cim.AIA.MethodSelection a)
    {
        String s = a.getName();
        com.cim.AIA.MethodSelectionEvent a0 = new com.cim.AIA.MethodSelectionEvent((Object)this, s);
        int i = 0;
        while(true)
        {
            java.util.Vector a1 = this.methodSelectionListeners;
            int i0 = a1.size();
            if(i >= i0)
            {
                return;
            }
            else
            {
                java.util.Vector a2 = this.methodSelectionListeners;
                Object a3 = a2.elementAt(i);
                com.cim.AIA.MethodSelectionListener dummy = (com.cim.AIA.MethodSelectionListener)a3;
                ((com.cim.AIA.MethodSelectionListener)a3).processMethodSelectionEvent(a0);
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    protected void initGui(java.awt.Dimension a)
    {
        java.awt.CheckboxGroup a0 = new java.awt.CheckboxGroup();
        this.methodCheckboxGroup = a0;
        java.util.Vector a1 = new java.util.Vector();
        this.methodRadioCheckboxes = a1;
        java.awt.Panel a2 = new java.awt.Panel();
        this.methodPanel = a2;
        java.util.Vector a3 = new java.util.Vector();
        this.methodSelections = a3;
        java.util.Vector a4 = new java.util.Vector();
        this.methodSelectionListeners = a4;
        com.cim.AIA.CodeCanvas a5 = this.theCodeCanvas;
        java.awt.Image a6 = a5.getExpandedImage();
        com.cim.AIA.CodeCanvas a7 = this.theCodeCanvas;
        java.awt.Image a8 = a7.getCollapsedImage();
        com.cim.AIA.Logger a9 = this.getLogger();
        com.cim.AIA.BreakPoint a10 = this.getBreakPoint();
        com.cim.AIA.CodeCanvas a11 = new com.cim.AIA.CodeCanvas(a6, a8, a9, a10);
        this.secondCodeCanvas = a11;
        com.cim.AIA.CodeCanvas a12 = this.secondCodeCanvas;
        com.cim.AIA.CodeCanvas a13 = this.theCodeCanvas;
        String s = a13.dataPath;
        a12.dataPath = s;
        java.awt.Color a14 = java.awt.Color.white;
        this.setBackground(a14);
        com.cim.AIA.CodeCanvas a15 = this.theCodeCanvas;
        java.awt.Image a16 = a15.logoImage;
        com.cim.AIA.CodeCanvas a17 = this.theCodeCanvas;
        java.awt.Dimension a18 = a17.logoImageSize;
        com.cim.AIA.ImageCanvas a19 = new com.cim.AIA.ImageCanvas(a16, a18);
        a19.addHelpListener((com.cim.AIA.HelpListener)this);
        java.awt.ScrollPane a20 = new java.awt.ScrollPane(0);
        this.scrollPane = a20;
        java.awt.GridBagLayout a21 = new java.awt.GridBagLayout();
        java.awt.GridBagConstraints a22 = new java.awt.GridBagConstraints();
        java.awt.Panel a23 = new java.awt.Panel();
        a23.setLayout((java.awt.LayoutManager)a21);
        java.awt.Panel a24 = this.getControlPanel();
        java.awt.Panel a25 = new java.awt.Panel();
        java.awt.GridBagLayout a26 = new java.awt.GridBagLayout();
        java.awt.GridBagConstraints a27 = new java.awt.GridBagConstraints();
        a25.setLayout((java.awt.LayoutManager)a26);
        java.awt.Color a28 = java.awt.Color.gray;
        java.awt.Color a29 = a28.brighter();
        a25.setBackground(a29);
        java.awt.Panel a30 = this.methodPanel;
        java.awt.Color a31 = java.awt.Color.gray;
        java.awt.Color a32 = a31.brighter();
        a30.setBackground(a32);
        this.buildConstraints(a27, 0, 0, 1, 2, 15, 100);
        java.awt.Insets a33 = new java.awt.Insets(0, 5, 5, 0);
        a27.insets = a33;
        a27.anchor = 17;
        a27.fill = 0;
        a26.setConstraints((java.awt.Component)a19, a27);
        java.awt.Component a34 = a25.add((java.awt.Component)a19);
        this.buildConstraints(a27, 1, 0, 1, 1, 85, 100);
        java.awt.Insets a35 = new java.awt.Insets(0, 0, 0, 0);
        a27.insets = a35;
        a27.anchor = 10;
        java.awt.Panel a36 = this.methodPanel;
        a26.setConstraints((java.awt.Component)a36, a27);
        java.awt.Panel a37 = this.methodPanel;
        java.awt.Component a38 = a25.add((java.awt.Component)a37);
        this.buildConstraints(a27, 1, 1, 1, 1, 85, 100);
        a27.anchor = 10;
        a26.setConstraints((java.awt.Component)a24, a27);
        java.awt.Component a39 = a25.add((java.awt.Component)a24);
        java.awt.Insets a40 = new java.awt.Insets(0, 0, 0, 0);
        a22.insets = a40;
        a22.gridwidth = 0;
        a22.anchor = 11;
        a22.fill = 1;
        a22.weighty = 0.0;
        a21.setConstraints((java.awt.Component)a25, a22);
        java.awt.Component a41 = a23.add((java.awt.Component)a25);
        a22.weightx = 10.0;
        java.awt.Insets a42 = new java.awt.Insets(2, 2, 2, 2);
        a22.insets = a42;
        int i = this.ratioTopAndBottom;
        double d = (double)i;
        a22.weighty = d;
        a22.fill = 1;
        com.cim.AIA.CodeCanvas a43 = this.theCodeCanvas;
        java.awt.ScrollPane a44 = this.scrollPane;
        a43.setParent((java.awt.Container)a44);
        java.awt.ScrollPane a45 = this.scrollPane;
        com.cim.AIA.CodeCanvas a46 = this.theCodeCanvas;
        java.awt.Component a47 = a45.add((java.awt.Component)a46);
        java.awt.ScrollPane a48 = this.scrollPane;
        a21.setConstraints((java.awt.Component)a48, a22);
        java.awt.ScrollPane a49 = this.scrollPane;
        java.awt.Component a50 = a23.add((java.awt.Component)a49);
        java.awt.ScrollPane a51 = new java.awt.ScrollPane(0);
        this.secondScrollPane = a51;
        com.cim.AIA.CodeCanvas a52 = this.secondCodeCanvas;
        java.awt.ScrollPane a53 = this.secondScrollPane;
        a52.setParent((java.awt.Container)a53);
        java.awt.ScrollPane a54 = this.secondScrollPane;
        com.cim.AIA.CodeCanvas a55 = this.secondCodeCanvas;
        java.awt.Component a56 = a54.add((java.awt.Component)a55);
        int i0 = this.ratioTopAndBottom;
        int i1 = 100 - i0;
        double d0 = (double)i1;
        a22.weighty = d0;
        a22.gridheight = 0;
        java.awt.ScrollPane a57 = this.secondScrollPane;
        a21.setConstraints((java.awt.Component)a57, a22);
        java.awt.ScrollPane a58 = this.secondScrollPane;
        java.awt.Component a59 = a23.add((java.awt.Component)a58);
        java.awt.Component a60 = this.add((java.awt.Component)a23);
        this.setSize(a);
        com.cim.AIA.CodeCanvas a61 = this.theCodeCanvas;
        a61.addExplainationListener((com.cim.AIA.ExplainationListener)this);
        com.cim.AIA.CodeCanvas a62 = this.theCodeCanvas;
        a62.addHelpListener((com.cim.AIA.HelpListener)this);
        com.cim.AIA.CodeCanvas a63 = this.secondCodeCanvas;
        com.cim.AIA.CodeCanvas a64 = this.theCodeCanvas;
        java.util.Vector a65 = a64.helpListeners;
        a63.helpListeners = a65;
        com.cim.AIA.CodeCanvas a66 = this.secondCodeCanvas;
        com.cim.AIA.CodeCanvas a67 = this.theCodeCanvas;
        java.util.Vector a68 = a67.explainationListeners;
        a66.explainationListeners = a68;
    }
    
    public boolean isExpandable(String s)
    {
        int i = 0;
        com.cim.AIA.CodeCanvas a = this.theCodeCanvas;
        int i0 = a.isExpandable(s)?1:0;
        label2: {
            label1: {
                label0: {
                    if(i0 != 0)
                    {
                        break label0;
                    }
                    com.cim.AIA.CodeCanvas a0 = this.secondCodeCanvas;
                    int i1 = a0.isExpandable(s)?1:0;
                    if(i1 == 0)
                    {
                        break label1;
                    }
                }
                i = 1;
                break label2;
            }
            i = 0;
        }
        return i != 0;
    }
    
    public boolean isExpanded(String s)
    {
        int i = 0;
        com.cim.AIA.CodeCanvas a = this.theCodeCanvas;
        int i0 = a.isExpanded(s)?1:0;
        label2: {
            label1: {
                label0: {
                    if(i0 != 0)
                    {
                        break label0;
                    }
                    com.cim.AIA.CodeCanvas a0 = this.secondCodeCanvas;
                    int i1 = a0.isExpanded(s)?1:0;
                    if(i1 == 0)
                    {
                        break label1;
                    }
                }
                i = 1;
                break label2;
            }
            i = 0;
        }
        return i != 0;
    }
    
    public boolean isVisible(String s)
    {
        int i = 0;
        com.cim.AIA.CodeCanvas a = this.theCodeCanvas;
        int i0 = a.isVisible(s)?1:0;
        label2: {
            label1: {
                label0: {
                    if(i0 != 0)
                    {
                        break label0;
                    }
                    com.cim.AIA.CodeCanvas a0 = this.secondCodeCanvas;
                    int i1 = a0.isVisible(s)?1:0;
                    if(i1 == 0)
                    {
                        break label1;
                    }
                }
                i = 1;
                break label2;
            }
            i = 0;
        }
        return i != 0;
    }
    
    public void itemStateChanged(java.awt.event.ItemEvent a)
    {
        java.awt.CheckboxGroup a0 = this.methodCheckboxGroup;
        java.awt.Checkbox a1 = a0.getSelectedCheckbox();
        label2: {
            com.cim.AIA.MethodSelection a2 = null;
            label1: {
                label0: {
                    if(a1 == null)
                    {
                        break label0;
                    }
                    String s = this.getPosition();
                    String s0 = a1.getLabel();
                    com.cim.AIA.Log a3 = new com.cim.AIA.Log((byte)5, (byte)18, s, s0);
                    com.cim.AIA.Logger a4 = this.getLogger();
                    if(a4 != null)
                    {
                        com.cim.AIA.Logger a5 = this.getLogger();
                        a5.addLog(a3);
                    }
                    int i = 0;
                    while(true)
                    {
                        java.util.Vector a6 = this.methodSelections;
                        int i0 = a6.size();
                        if(i >= i0)
                        {
                            break;
                        }
                        java.util.Vector a7 = this.methodSelections;
                        Object a8 = a7.elementAt(i);
                        com.cim.AIA.MethodSelection dummy = (com.cim.AIA.MethodSelection)a8;
                        a2 = (com.cim.AIA.MethodSelection)a8;
                        String s1 = a2.getName();
                        String s2 = a1.getLabel();
                        int i1 = s1.equalsIgnoreCase(s2)?1:0;
                        if(i1 == 0)
                        {
                            int i2 = i + 1;
                            i = i2;
                        }
                        else
                        {
                            break label1;
                        }
                    }
                }
                break label2;
            }
            com.cim.AIA.MethodSelection a9 = this.currentMethodSelection;
            if(a2 != a9)
            {
                this.setMethod(a2);
                this.informMethodSelectionListeners(a2);
            }
        }
    }
    
    protected void openAll()
    {
        com.cim.AIA.CodeCanvas a = this.theCodeCanvas;
        a.expandEntireTree();
        com.cim.AIA.CodeCanvas a0 = this.secondCodeCanvas;
        a0.expandEntireTree();
    }
    
    public void processExitEvent(com.cim.AIA.ExitEvent a)
    {
        this.setVisible(false);
        com.cim.AIA.CodeCanvas a0 = this.theCodeCanvas;
        com.cim.AIA.CodeCanvas a1 = this.theCodeCanvas;
        a0.removeMouseMotionListener((java.awt.event.MouseMotionListener)a1);
        com.cim.AIA.CodeCanvas a2 = this.theCodeCanvas;
        com.cim.AIA.CodeCanvas a3 = this.theCodeCanvas;
        a2.removeMouseListener((java.awt.event.MouseListener)a3);
        com.cim.AIA.CodeCanvas a4 = this.secondCodeCanvas;
        com.cim.AIA.CodeCanvas a5 = this.secondCodeCanvas;
        a4.removeMouseMotionListener((java.awt.event.MouseMotionListener)a5);
        com.cim.AIA.CodeCanvas a6 = this.secondCodeCanvas;
        com.cim.AIA.CodeCanvas a7 = this.secondCodeCanvas;
        a6.removeMouseListener((java.awt.event.MouseListener)a7);
        this.removeAll();
        this.dispose();
        this.cleanUp();
    }
    
    public void removeMethodSelection(com.cim.AIA.MethodSelection a)
    {
        com.cim.AIA.MethodSelection a0 = null;
        int i = 0;
        label0: {
            java.awt.Checkbox a1 = null;
            while(true)
            {
                java.util.Vector a2 = this.methodRadioCheckboxes;
                int i0 = a2.size();
                if(i >= i0)
                {
                    a0 = a;
                    break label0;
                }
                java.util.Vector a3 = this.methodRadioCheckboxes;
                Object a4 = a3.elementAt(i);
                java.awt.Checkbox dummy = (java.awt.Checkbox)a4;
                a1 = (java.awt.Checkbox)a4;
                String s = a1.getLabel();
                String s0 = a.getName();
                int i1 = s.equalsIgnoreCase(s0)?1:0;
                if(i1 == 0)
                {
                    int i2 = i + 1;
                    i = i2;
                }
                else
                {
                    break;
                }
            }
            a1.removeItemListener((java.awt.event.ItemListener)this);
            a1.setCheckboxGroup((java.awt.CheckboxGroup)null);
            java.util.Vector a5 = this.methodRadioCheckboxes;
            int i3 = a5.removeElement((Object)a1)?1:0;
            java.awt.Panel a6 = this.methodPanel;
            a6.remove((java.awt.Component)a1);
            com.cim.AIA.MethodSelection a7 = this.currentMethodSelection;
            a0 = a != a7?a:null;
        }
        java.util.Vector a8 = this.methodSelections;
        int i4 = a8.removeElement((Object)a0)?1:0;
    }
    
    public void removeMethodSelectionListener(com.cim.AIA.MethodSelectionListener a)
    {
        java.util.Vector a0 = this.methodSelectionListeners;
        Object a1 = a;
        int i = a0.removeElement(a1)?1:0;
    }
    
    protected void setEnableCheckboxes(boolean b)
    {
        this.enabledCheckboxes = b;
        int i = b?1:0;
        int i0 = 0;
        while(true)
        {
            java.util.Vector a = this.methodSelections;
            int i1 = a.size();
            if(i0 >= i1)
            {
                return;
            }
            else
            {
                java.util.Vector a0 = this.methodRadioCheckboxes;
                Object a1 = a0.elementAt(i0);
                java.awt.Checkbox dummy = (java.awt.Checkbox)a1;
                java.awt.Checkbox a2 = (java.awt.Checkbox)a1;
                a2.setEnabled(i != 0);
                int i2 = i0 + 1;
                i0 = i2;
            }
        }
    }
    
    protected void setMethod(com.cim.AIA.MethodSelection a)
    {
        com.cim.AIA.LadderTree a0 = a.getLadderTree();
        if(a0 == null)
        {
            java.io.PrintStream a1 = System.out;
            StringBuilder a2 = new StringBuilder();
            StringBuilder a3 = a2.append("com.cim.AIA.SplitAlgorithmWindow.setMethod:  LadderTree for MethodSelection '");
            String s = a.getName();
            StringBuilder a4 = a3.append(s);
            StringBuilder a5 = a4.append("' is null");
            String s0 = a5.toString();
            a1.println(s0);
        }
        else
        {
            this.currentMethodSelection = a;
            com.cim.AIA.CodeCanvas a6 = this.secondCodeCanvas;
            com.cim.AIA.LadderTree a7 = a.getLadderTree();
            a6.setLadderTree(a7);
        }
    }
    
    public void setPosition(String s)
    {
        com.cim.AIA.CodeCanvas a = this.theCodeCanvas;
        int i = a.containsKey(s)?1:0;
        label1: {
            label0: {
                if(i == 0)
                {
                    break label0;
                }
                com.cim.AIA.CodeCanvas a0 = this.theCodeCanvas;
                a0.setPosition(s);
                break label1;
            }
            com.cim.AIA.MethodSelection a1 = this.currentMethodSelection;
            if(a1 != null)
            {
                com.cim.AIA.CodeCanvas a2 = this.theCodeCanvas;
                com.cim.AIA.MethodSelection a3 = this.currentMethodSelection;
                String s0 = a3.getKey();
                a2.setPosition(s0);
            }
        }
        com.cim.AIA.CodeCanvas a4 = this.secondCodeCanvas;
        int i0 = a4.containsKey(s)?1:0;
        if(i0 != 0)
        {
            com.cim.AIA.CodeCanvas a5 = this.secondCodeCanvas;
            a5.setPosition(s);
        }
    }
    
    public boolean shouldRepaint(String s)
    {
        int i = 0;
        com.cim.AIA.CodeCanvas a = this.theCodeCanvas;
        int i0 = a.shouldRepaint(s)?1:0;
        label2: {
            label1: {
                label0: {
                    if(i0 != 0)
                    {
                        break label0;
                    }
                    com.cim.AIA.CodeCanvas a0 = this.secondCodeCanvas;
                    int i1 = a0.shouldRepaint(s)?1:0;
                    if(i1 == 0)
                    {
                        break label1;
                    }
                }
                i = 1;
                break label2;
            }
            i = 0;
        }
        return i != 0;
    }
}