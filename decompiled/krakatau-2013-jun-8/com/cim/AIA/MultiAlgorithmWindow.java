package com.cim.AIA;

public class MultiAlgorithmWindow extends com.cim.AIA.AlgorithmWindow implements com.cim.AIA.MethodSelectable, java.awt.event.ItemListener, com.cim.AIA.ControlListener, java.awt.event.MouseMotionListener {
    final private static long serialVersionUID = -7641714727443148790L;
    private static String IMAGE_DIRECTORY;
    protected java.awt.CheckboxGroup methodCheckboxGroup;
    protected java.util.Vector methodRadioCheckboxes;
    protected java.util.Vector methodSelections;
    private java.util.Vector additionalCanvas;
    private java.util.Hashtable canvasLookup;
    private java.awt.event.WindowListener standardWindowListener;
    private java.awt.event.MouseListener commonMouseListener;
    protected java.awt.Panel methodPanel;
    protected java.util.Vector methodSelectionListeners;
    protected com.cim.AIA.MethodSelection currentMethodSelection;
    protected boolean enabledCheckboxes;
    protected com.cim.AIA.AlgorithmApplet algorithmApplet;
    
    public MultiAlgorithmWindow(com.cim.AIA.AlgorithmApplet a, String s, com.cim.AIA.CodeCanvas a0)
    {
        java.awt.Dimension a1 = new java.awt.Dimension(500, 200);
        this(a, s, a0, a1);
    }
    
    public MultiAlgorithmWindow(com.cim.AIA.AlgorithmApplet a, String s, com.cim.AIA.CodeCanvas a0, java.awt.Dimension a1)
    {
        this(a, s, a0, a1, true);
    }
    
    protected MultiAlgorithmWindow(com.cim.AIA.AlgorithmApplet a, String s, com.cim.AIA.CodeCanvas a0, java.awt.Dimension a1, boolean b)
    {
        super(s, a0, a1, false);
        int i = b?1:0;
        this.enabledCheckboxes = true;
        java.util.Vector a2 = new java.util.Vector();
        this.additionalCanvas = a2;
        java.util.Hashtable a3 = new java.util.Hashtable();
        this.canvasLookup = a3;
        this.algorithmApplet = a;
        this.theCodeCanvas = a0;
        this.setTitle(s);
        com.cim.AIA.MultiAlgorithmWindow$1 a4 = new com.cim.AIA.MultiAlgorithmWindow$1(this);
        this.addMouseListener((java.awt.event.MouseListener)a4);
        com.cim.AIA.MultiAlgorithmWindow$2 a5 = new com.cim.AIA.MultiAlgorithmWindow$2(this);
        this.commonMouseListener = (java.awt.event.MouseListener)a5;
        com.cim.AIA.MultiAlgorithmWindow$3 a6 = new com.cim.AIA.MultiAlgorithmWindow$3(this);
        this.standardWindowListener = (java.awt.event.WindowListener)a6;
        this.addMouseMotionListener((java.awt.event.MouseMotionListener)this);
        com.cim.AIA.CodeCanvas a7 = this.theCodeCanvas;
        Object a8 = this.commonMouseListener;
        a7.addMouseListener((java.awt.event.MouseListener)a8);
        com.cim.AIA.CodeCanvas a9 = this.theCodeCanvas;
        a9.addMouseMotionListener((java.awt.event.MouseMotionListener)this);
        if(i != 0)
        {
            this.initGui(a1);
        }
    }
    
    public void addCodeCanvas(String s, String s0, String s1, String s2)
    {
        java.util.Vector a = this.additionalCanvas;
        com.cim.AIA.MultiAlgorithmWindow$MultiCanvas a0 = new com.cim.AIA.MultiAlgorithmWindow$MultiCanvas(this, s0, s, s0, s1, s2, (com.cim.AIA.ExplainationListener)this, (com.cim.AIA.HelpListener)this);
        a.addElement((Object)a0);
        java.util.Hashtable a1 = this.canvasLookup;
        java.util.Vector a2 = this.additionalCanvas;
        int i = a2.size();
        int i0 = i - 1;
        Integer a3 = new Integer(i0);
        Object a4 = a1.put((Object)s0, (Object)a3);
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
    
    protected void closeAll()
    {
        com.cim.AIA.CodeCanvas a = this.theCodeCanvas;
        a.collapseEntireTree();
        int i = 0;
        while(true)
        {
            java.util.Vector a0 = this.additionalCanvas;
            int i0 = a0.size();
            if(i >= i0)
            {
                return;
            }
            else
            {
                java.util.Vector a1 = this.additionalCanvas;
                Object a2 = a1.elementAt(i);
                com.cim.AIA.MultiAlgorithmWindow$MultiCanvas dummy = (com.cim.AIA.MultiAlgorithmWindow$MultiCanvas)a2;
                com.cim.AIA.MultiAlgorithmWindow$MultiCanvas a3 = (com.cim.AIA.MultiAlgorithmWindow$MultiCanvas)a2;
                com.cim.AIA.CodeCanvas a4 = a3.canvas;
                a4.collapseEntireTree();
                a3.isVisible = false;
                a3.setVisible(false);
                int i1 = i + 1;
                i = i1;
            }
        }
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
        this.methodSelections = a1;
        java.util.Vector a2 = new java.util.Vector();
        this.methodRadioCheckboxes = a2;
        java.util.Vector a3 = new java.util.Vector();
        this.methodSelectionListeners = a3;
        java.awt.Panel a4 = new java.awt.Panel();
        this.methodPanel = a4;
        java.awt.ScrollPane a5 = new java.awt.ScrollPane(0);
        this.scrollPane = a5;
        java.awt.GridBagLayout a6 = new java.awt.GridBagLayout();
        java.awt.GridBagConstraints a7 = new java.awt.GridBagConstraints();
        java.awt.Panel a8 = new java.awt.Panel();
        a8.setLayout((java.awt.LayoutManager)a6);
        java.awt.Panel a9 = this.getControlPanel();
        java.awt.Insets a10 = new java.awt.Insets(0, 0, 0, 0);
        a7.insets = a10;
        a7.gridwidth = 0;
        a7.anchor = 11;
        a7.fill = 1;
        a7.weighty = 0.0;
        java.awt.Panel a11 = this.methodPanel;
        java.awt.Color a12 = java.awt.Color.gray;
        java.awt.Color a13 = a12.brighter();
        a11.setBackground(a13);
        java.awt.Panel a14 = this.methodPanel;
        a6.setConstraints((java.awt.Component)a14, a7);
        java.awt.Panel a15 = this.methodPanel;
        java.awt.Component a16 = a8.add((java.awt.Component)a15);
        a6.setConstraints((java.awt.Component)a9, a7);
        java.awt.Component a17 = a8.add((java.awt.Component)a9);
        a7.weightx = 10.0;
        java.awt.Insets a18 = new java.awt.Insets(2, 2, 2, 2);
        a7.insets = a18;
        a7.weighty = 10.0;
        a7.fill = 1;
        com.cim.AIA.CodeCanvas a19 = this.theCodeCanvas;
        java.awt.ScrollPane a20 = this.scrollPane;
        a19.setParent((java.awt.Container)a20);
        java.awt.ScrollPane a21 = this.scrollPane;
        com.cim.AIA.CodeCanvas a22 = this.theCodeCanvas;
        java.awt.Component a23 = a21.add((java.awt.Component)a22);
        java.awt.ScrollPane a24 = this.scrollPane;
        a6.setConstraints((java.awt.Component)a24, a7);
        java.awt.ScrollPane a25 = this.scrollPane;
        java.awt.Component a26 = a8.add((java.awt.Component)a25);
        java.awt.Component a27 = this.add((java.awt.Component)a8);
        this.setSize(a);
        com.cim.AIA.CodeCanvas a28 = this.theCodeCanvas;
        a28.addExplainationListener((com.cim.AIA.ExplainationListener)this);
        com.cim.AIA.CodeCanvas a29 = this.theCodeCanvas;
        a29.addHelpListener((com.cim.AIA.HelpListener)this);
    }
    
    public boolean isExpandable(String s)
    {
        int i = ((com.cim.AIA.AlgorithmWindow)this).isExpandable(s)?1:0;
        return i != 0;
    }
    
    public boolean isExpanded(String s)
    {
        com.cim.AIA.CodeCanvas a = this.theCodeCanvas;
        int i = a.isExpanded(s)?1:0;
        int i0 = i;
        int i1 = 0;
        while(true)
        {
            int i2 = 0;
            java.util.Vector a0 = this.additionalCanvas;
            int i3 = a0.size();
            if(i1 >= i3)
            {
                return i0 != 0;
            }
            java.util.Vector a1 = this.additionalCanvas;
            Object a2 = a1.elementAt(i1);
            com.cim.AIA.MultiAlgorithmWindow$MultiCanvas dummy = (com.cim.AIA.MultiAlgorithmWindow$MultiCanvas)a2;
            com.cim.AIA.MultiAlgorithmWindow$MultiCanvas a3 = (com.cim.AIA.MultiAlgorithmWindow$MultiCanvas)a2;
            com.cim.AIA.CodeCanvas a4 = a3.canvas;
            int i4 = a4.isExpanded(s)?1:0;
            label2: {
                label1: {
                    label0: {
                        if(i4 == 0)
                        {
                            break label0;
                        }
                        int i5 = a3.isVisible?1:0;
                        if(i5 != 0)
                        {
                            break label1;
                        }
                    }
                    i2 = 0;
                    break label2;
                }
                i2 = 1;
            }
            int i6 = i2 | i0;
            int i7 = i1 + 1;
            i0 = i6;
            i1 = i7;
        }
    }
    
    public boolean isVisible(String s)
    {
        com.cim.AIA.CodeCanvas a = this.theCodeCanvas;
        int i = a.isVisible(s)?1:0;
        int i0 = i == 0?0:1;
        int i1 = i0;
        int i2 = 0;
        while(true)
        {
            int i3 = 0;
            int i4 = 0;
            java.util.Vector a0 = this.additionalCanvas;
            int i5 = i1;
            int i6 = a0.size();
            int i7 = i5;
            int i8 = i7;
            if(i2 >= i6)
            {
                return i7 != 0;
            }
            else
            {
                i3 = i8;
            }
            java.util.Vector a1 = this.additionalCanvas;
            int i9 = i3;
            Object a2 = a1.elementAt(i2);
            int i10 = i9;
            com.cim.AIA.MultiAlgorithmWindow$MultiCanvas dummy = (com.cim.AIA.MultiAlgorithmWindow$MultiCanvas)a2;
            com.cim.AIA.MultiAlgorithmWindow$MultiCanvas a3 = (com.cim.AIA.MultiAlgorithmWindow$MultiCanvas)a2;
            int i11 = i10;
            com.cim.AIA.CodeCanvas a4 = a3.canvas;
            int i12 = i11;
            int i13 = a4.isVisible(s)?1:0;
            int i14 = i12;
            label0: {
                int i15 = 0;
                int i16 = i14;
                int i17 = i14;
                if(i13 == 0)
                {
                    i4 = i17;
                    break label0;
                }
                else
                {
                    i15 = i16;
                }
                int i18 = a3.isVisible?1:0;
                int i19 = i15;
                int i20 = i19;
                i4 = i18 == 0?i20:1;
            }
            int i21 = i2 + 1;
            i1 = i4;
            i2 = i21;
        }
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
                    int i = 0;
                    while(true)
                    {
                        java.util.Vector a3 = this.methodSelections;
                        int i0 = a3.size();
                        if(i >= i0)
                        {
                            break;
                        }
                        java.util.Vector a4 = this.methodSelections;
                        Object a5 = a4.elementAt(i);
                        com.cim.AIA.MethodSelection dummy = (com.cim.AIA.MethodSelection)a5;
                        a2 = (com.cim.AIA.MethodSelection)a5;
                        String s = a2.getName();
                        String s0 = a1.getLabel();
                        int i1 = s.equalsIgnoreCase(s0)?1:0;
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
            com.cim.AIA.MethodSelection a6 = this.currentMethodSelection;
            if(a2 != a6)
            {
                this.closeAll();
                this.setMethod(a2);
                this.informMethodSelectionListeners(a2);
            }
        }
    }
    
    protected void openAll()
    {
        com.cim.AIA.CodeCanvas a = this.theCodeCanvas;
        this.openAll(a);
    }
    
    protected void openAll(com.cim.AIA.CodeCanvas a)
    {
        a.expandEntireTree();
        int i = 0;
        while(true)
        {
            java.util.Vector a0 = this.additionalCanvas;
            int i0 = a0.size();
            if(i >= i0)
            {
                return;
            }
            java.util.Vector a1 = this.additionalCanvas;
            Object a2 = a1.elementAt(i);
            com.cim.AIA.MultiAlgorithmWindow$MultiCanvas dummy = (com.cim.AIA.MultiAlgorithmWindow$MultiCanvas)a2;
            com.cim.AIA.MultiAlgorithmWindow$MultiCanvas a3 = (com.cim.AIA.MultiAlgorithmWindow$MultiCanvas)a2;
            String s = a3.triggerKey;
            int i1 = a.isExpanded(s)?1:0;
            if(i1 != 0)
            {
                a3.isVisible = true;
                a3.setVisible(true);
                com.cim.AIA.CodeCanvas a4 = a3.canvas;
                this.openAll(a4);
            }
            int i2 = i + 1;
            i = i2;
        }
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
    
    public void setLocation(java.awt.Point a, String s)
    {
        java.util.Vector a0 = this.additionalCanvas;
        java.util.Hashtable a1 = this.canvasLookup;
        Object a2 = a1.get((Object)s);
        Integer dummy = (Integer)a2;
        Integer a3 = (Integer)a2;
        int i = a3.intValue();
        Object a4 = a0.elementAt(i);
        com.cim.AIA.MultiAlgorithmWindow$MultiCanvas dummy0 = (com.cim.AIA.MultiAlgorithmWindow$MultiCanvas)a4;
        com.cim.AIA.MultiAlgorithmWindow$MultiCanvas a5 = (com.cim.AIA.MultiAlgorithmWindow$MultiCanvas)a4;
        a5.setLocation(a);
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
            com.cim.AIA.CodeCanvas a6 = this.theCodeCanvas;
            com.cim.AIA.LadderTree a7 = a.getLadderTree();
            a6.setLadderTree(a7);
        }
    }
    
    public void setPosition(String s)
    {
        com.cim.AIA.CodeCanvas a = this.theCodeCanvas;
        int i = a.containsKey(s)?1:0;
        if(i != 0)
        {
            com.cim.AIA.CodeCanvas a0 = this.theCodeCanvas;
            a0.setPosition(s);
        }
        int i0 = 0;
        while(true)
        {
            java.util.Vector a1 = this.additionalCanvas;
            int i1 = a1.size();
            if(i0 >= i1)
            {
                return;
            }
            java.util.Vector a2 = this.additionalCanvas;
            Object a3 = a2.elementAt(i0);
            com.cim.AIA.MultiAlgorithmWindow$MultiCanvas dummy = (com.cim.AIA.MultiAlgorithmWindow$MultiCanvas)a3;
            com.cim.AIA.MultiAlgorithmWindow$MultiCanvas a4 = (com.cim.AIA.MultiAlgorithmWindow$MultiCanvas)a3;
            int i2 = a4.isVisible()?1:0;
            int i3 = a4.isVisible?1:0;
            int i4 = i2 ^ i3;
            if(i4 != 0)
            {
                int i5 = a4.isVisible?1:0;
                a4.setVisible(i5 != 0);
            }
            com.cim.AIA.CodeCanvas a5 = a4.canvas;
            int i6 = a5.containsKey(s)?1:0;
            if(i6 != 0)
            {
                com.cim.AIA.CodeCanvas a6 = a4.canvas;
                a6.setPosition(s);
            }
            String s0 = a4.unHighlight;
            int i7 = s.compareTo(s0);
            if(i7 == 0)
            {
                com.cim.AIA.CodeCanvas a7 = a4.canvas;
                a7.setPosition("");
            }
            int i8 = i0 + 1;
            i0 = i8;
        }
    }
    
    public void setSize(java.awt.Dimension a, String s)
    {
        java.util.Vector a0 = this.additionalCanvas;
        java.util.Hashtable a1 = this.canvasLookup;
        Object a2 = a1.get((Object)s);
        Integer dummy = (Integer)a2;
        Integer a3 = (Integer)a2;
        int i = a3.intValue();
        Object a4 = a0.elementAt(i);
        com.cim.AIA.MultiAlgorithmWindow$MultiCanvas dummy0 = (com.cim.AIA.MultiAlgorithmWindow$MultiCanvas)a4;
        com.cim.AIA.MultiAlgorithmWindow$MultiCanvas a5 = (com.cim.AIA.MultiAlgorithmWindow$MultiCanvas)a4;
        a5.setSize(a);
    }
    
    public boolean shouldRepaint(String s)
    {
        com.cim.AIA.CodeCanvas a = this.theCodeCanvas;
        int i = ((com.cim.AIA.AlgorithmWindow)this).shouldRepaint(s)?1:0;
        int i0 = i;
        int i1 = 0;
        while(true)
        {
            int i2 = 0;
            int i3 = 0;
            java.util.Vector a0 = this.additionalCanvas;
            int i4 = i0;
            int i5 = a0.size();
            int i6 = i4;
            int i7 = i6;
            if(i1 >= i5)
            {
                this.theCodeCanvas = a;
                return i6 != 0;
            }
            else
            {
                i2 = i7;
            }
            java.util.Vector a1 = this.additionalCanvas;
            int i8 = i2;
            Object a2 = a1.elementAt(i1);
            int i9 = i8;
            com.cim.AIA.MultiAlgorithmWindow$MultiCanvas dummy = (com.cim.AIA.MultiAlgorithmWindow$MultiCanvas)a2;
            com.cim.AIA.MultiAlgorithmWindow$MultiCanvas a3 = (com.cim.AIA.MultiAlgorithmWindow$MultiCanvas)a2;
            int i10 = i9;
            int i11 = a3.isVisible?1:0;
            int i12 = i10;
            int i13 = i12;
            if(i11 == 0)
            {
                i3 = i13;
            }
            else
            {
                java.util.Vector a4 = this.additionalCanvas;
                Object a5 = a4.elementAt(i1);
                com.cim.AIA.MultiAlgorithmWindow$MultiCanvas dummy0 = (com.cim.AIA.MultiAlgorithmWindow$MultiCanvas)a5;
                com.cim.AIA.MultiAlgorithmWindow$MultiCanvas a6 = (com.cim.AIA.MultiAlgorithmWindow$MultiCanvas)a5;
                com.cim.AIA.CodeCanvas a7 = a6.canvas;
                this.theCodeCanvas = a7;
                int i14 = ((com.cim.AIA.AlgorithmWindow)this).shouldRepaint(s)?1:0;
                int i15 = i14 | i12;
                i3 = i15;
            }
            int i16 = i1 + 1;
            i0 = i3;
            i1 = i16;
        }
    }
    
    private void updateVisibleCanvases(java.awt.event.MouseEvent a)
    {
        Object a0 = a.getSource();
        com.cim.AIA.CodeCanvas dummy = (com.cim.AIA.CodeCanvas)a0;
        com.cim.AIA.CodeCanvas a1 = (com.cim.AIA.CodeCanvas)a0;
        int i = 0;
        while(true)
        {
            java.util.Vector a2 = this.additionalCanvas;
            int i0 = a2.size();
            if(i >= i0)
            {
                return;
            }
            java.util.Vector a3 = this.additionalCanvas;
            Object a4 = a3.elementAt(i);
            com.cim.AIA.MultiAlgorithmWindow$MultiCanvas dummy0 = (com.cim.AIA.MultiAlgorithmWindow$MultiCanvas)a4;
            com.cim.AIA.MultiAlgorithmWindow$MultiCanvas a5 = (com.cim.AIA.MultiAlgorithmWindow$MultiCanvas)a4;
            String s = a5.triggerKey;
            int i1 = a1.isExpanded(s)?1:0;
            label1: {
                label0: {
                    if(i1 == 0)
                    {
                        break label0;
                    }
                    a5.isVisible = true;
                    int i2 = a5.isVisible()?1:0;
                    int i3 = a5.isVisible?1:0;
                    int i4 = i2 ^ i3;
                    if(i4 == 0)
                    {
                        break label1;
                    }
                    else
                    {
                        a5.setVisible(true);
                        break label1;
                    }
                }
                String s0 = a5.triggerKey;
                int i5 = a1.containsKey(s0)?1:0;
                if(i5 == 0)
                {
                    break label1;
                }
                a5.isVisible = false;
                int i6 = a5.isVisible()?1:0;
                int i7 = a5.isVisible?1:0;
                int i8 = i6 ^ i7;
                if(i8 != 0)
                {
                    a5.setVisible(false);
                    a5.dispose();
                }
            }
            int i9 = i + 1;
            i = i9;
        }
    }
    
    static String access$000()
    {
        String s = com.cim.AIA.MultiAlgorithmWindow.IMAGE_DIRECTORY;
        return s;
    }
    
    static java.awt.event.MouseListener access$100(com.cim.AIA.MultiAlgorithmWindow a)
    {
        Object a0 = a.commonMouseListener;
        return (java.awt.event.MouseListener)a0;
    }
    
    static java.awt.event.WindowListener access$200(com.cim.AIA.MultiAlgorithmWindow a)
    {
        Object a0 = a.standardWindowListener;
        return (java.awt.event.WindowListener)a0;
    }
    
    static void access$300(com.cim.AIA.MultiAlgorithmWindow a, java.awt.event.MouseEvent a0)
    {
        a.updateVisibleCanvases(a0);
    }
    
    static
    {
        com.cim.AIA.MultiAlgorithmWindow.IMAGE_DIRECTORY = "images/";
    }
}