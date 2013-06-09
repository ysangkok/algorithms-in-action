package com.cim.AIA;

public class AlgorithmWindow extends javax.swing.JFrame implements com.cim.AIA.ExplainationListener, com.cim.AIA.Explainable, com.cim.AIA.ExitListener, com.cim.AIA.Helpable, com.cim.AIA.HelpListener, java.awt.event.MouseMotionListener {
    final private static long serialVersionUID = -3775287959600581277L;
    final protected static String OPEN_ALL_TITLE;
    final protected static String OPEN_ALL_INSTRUCTIONS;
    final protected static String CLOSE_ALL_TITLE;
    final protected static String CLOSE_ALL_INSTRUCTIONS;
    final protected static String ADD_BREAKPOINT_TITLE;
    final protected static String ADD_BREAKPOINT_INSTRUCTIONS;
    final protected static String DEL_BREAKPOINT_TITLE;
    final protected static String DEL_BREAKPOINT_INSTRUCTIONS;
    protected java.awt.ScrollPane scrollPane;
    protected com.cim.AIA.HelpableButton openAll;
    protected com.cim.AIA.HelpableButton closeAll;
    protected com.cim.AIA.HelpableButton addBreakPoint;
    protected com.cim.AIA.HelpableButton delBreakPoint;
    int nlines;
    protected com.cim.AIA.CodeCanvas theCodeCanvas;
    protected java.util.Vector explainationListeners;
    protected java.util.Vector helpListeners;
    protected com.cim.AIA.Logger logger;
    protected com.cim.AIA.BreakPoint breakpoint;
    
    public AlgorithmWindow(String s, com.cim.AIA.CodeCanvas a)
    {
        java.awt.Dimension a0 = new java.awt.Dimension(500, 200);
        this(s, a, a0);
    }
    
    public AlgorithmWindow(String s, com.cim.AIA.CodeCanvas a, java.awt.Dimension a0)
    {
        this(s, a, a0, true);
    }
    
    protected AlgorithmWindow(String s, com.cim.AIA.CodeCanvas a, java.awt.Dimension a0, boolean b)
    {
        super(s);
        int i = b?1:0;
        java.util.Vector a1 = new java.util.Vector();
        this.explainationListeners = a1;
        java.util.Vector a2 = new java.util.Vector();
        this.helpListeners = a2;
        this.theCodeCanvas = a;
        com.cim.AIA.AlgorithmWindow$1 a3 = new com.cim.AIA.AlgorithmWindow$1(this);
        this.addMouseListener((java.awt.event.MouseListener)a3);
        this.addMouseMotionListener((java.awt.event.MouseMotionListener)this);
        a.addMouseMotionListener((java.awt.event.MouseMotionListener)this);
        if(i != 0)
        {
            this.initGui(a0);
        }
    }
    
    public void addExplainationListener(com.cim.AIA.ExplainationListener a)
    {
        java.util.Vector a0 = this.explainationListeners;
        Object a1 = a;
        a0.addElement(a1);
    }
    
    public void addHelpListener(com.cim.AIA.HelpListener a)
    {
        com.cim.AIA.HelpableButton a0 = this.closeAll;
        Object a1 = a;
        a0.addHelpListener((com.cim.AIA.HelpListener)a1);
        com.cim.AIA.HelpableButton a2 = this.openAll;
        a2.addHelpListener((com.cim.AIA.HelpListener)a1);
        com.cim.AIA.HelpableButton a3 = this.addBreakPoint;
        a3.addHelpListener((com.cim.AIA.HelpListener)a1);
        com.cim.AIA.HelpableButton a4 = this.delBreakPoint;
        a4.addHelpListener((com.cim.AIA.HelpListener)a1);
        java.util.Vector a5 = this.helpListeners;
        a5.addElement(a1);
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
        java.awt.ScrollPane a = this.scrollPane;
        if(a != null)
        {
            java.awt.ScrollPane a0 = this.scrollPane;
            a0.removeAll();
        }
        this.scrollPane = null;
        com.cim.AIA.HelpableButton a1 = this.openAll;
        if(a1 != null)
        {
            com.cim.AIA.HelpableButton a2 = this.openAll;
            a2.removeAllHelpListeners();
        }
        this.openAll = null;
        com.cim.AIA.HelpableButton a3 = this.closeAll;
        if(a3 != null)
        {
            com.cim.AIA.HelpableButton a4 = this.closeAll;
            a4.removeAllHelpListeners();
        }
        this.closeAll = null;
        com.cim.AIA.HelpableButton a5 = this.addBreakPoint;
        if(a5 != null)
        {
            com.cim.AIA.HelpableButton a6 = this.addBreakPoint;
            a6.removeAllHelpListeners();
        }
        this.addBreakPoint = null;
        com.cim.AIA.HelpableButton a7 = this.delBreakPoint;
        if(a7 != null)
        {
            com.cim.AIA.HelpableButton a8 = this.delBreakPoint;
            a8.removeAllHelpListeners();
        }
        this.delBreakPoint = null;
        com.cim.AIA.CodeCanvas a9 = this.theCodeCanvas;
        if(a9 != null)
        {
            com.cim.AIA.CodeCanvas a10 = this.theCodeCanvas;
            a10.cleanUp();
        }
        this.theCodeCanvas = null;
        java.util.Vector a11 = this.explainationListeners;
        if(a11 != null)
        {
            java.util.Vector a12 = this.explainationListeners;
            a12.removeAllElements();
        }
        this.explainationListeners = null;
        java.util.Vector a13 = this.helpListeners;
        if(a13 != null)
        {
            java.util.Vector a14 = this.helpListeners;
            a14.removeAllElements();
        }
        this.helpListeners = null;
        this.breakpoint = null;
    }
    
    protected void closeAll()
    {
        com.cim.AIA.CodeCanvas a = this.theCodeCanvas;
        a.collapseEntireTree();
    }
    
    public com.cim.AIA.BreakPoint getBreakPoint()
    {
        com.cim.AIA.BreakPoint a = this.breakpoint;
        return a;
    }
    
    protected java.awt.Panel getControlPanel()
    {
        java.awt.Panel a = null;
        String s = com.cim.AIA.AlgorithmWindow.ADD_BREAKPOINT_TITLE;
        String s0 = com.cim.AIA.AlgorithmWindow.ADD_BREAKPOINT_INSTRUCTIONS;
        com.cim.AIA.HelpableButton a0 = new com.cim.AIA.HelpableButton(s, s0);
        this.addBreakPoint = a0;
        com.cim.AIA.HelpableButton a1 = this.addBreakPoint;
        com.cim.AIA.AlgorithmWindow$2 a2 = new com.cim.AIA.AlgorithmWindow$2(this);
        a1.addActionListener((java.awt.event.ActionListener)a2);
        String s1 = com.cim.AIA.AlgorithmWindow.DEL_BREAKPOINT_TITLE;
        String s2 = com.cim.AIA.AlgorithmWindow.DEL_BREAKPOINT_INSTRUCTIONS;
        com.cim.AIA.HelpableButton a3 = new com.cim.AIA.HelpableButton(s1, s2);
        this.delBreakPoint = a3;
        com.cim.AIA.HelpableButton a4 = this.delBreakPoint;
        com.cim.AIA.AlgorithmWindow$3 a5 = new com.cim.AIA.AlgorithmWindow$3(this);
        a4.addActionListener((java.awt.event.ActionListener)a5);
        String s3 = com.cim.AIA.AlgorithmWindow.OPEN_ALL_TITLE;
        String s4 = com.cim.AIA.AlgorithmWindow.OPEN_ALL_INSTRUCTIONS;
        com.cim.AIA.HelpableButton a6 = new com.cim.AIA.HelpableButton(s3, s4);
        this.openAll = a6;
        com.cim.AIA.HelpableButton a7 = this.openAll;
        com.cim.AIA.AlgorithmWindow$4 a8 = new com.cim.AIA.AlgorithmWindow$4(this);
        a7.addActionListener((java.awt.event.ActionListener)a8);
        String s5 = com.cim.AIA.AlgorithmWindow.CLOSE_ALL_TITLE;
        String s6 = com.cim.AIA.AlgorithmWindow.CLOSE_ALL_INSTRUCTIONS;
        com.cim.AIA.HelpableButton a9 = new com.cim.AIA.HelpableButton(s5, s6);
        this.closeAll = a9;
        com.cim.AIA.HelpableButton a10 = this.closeAll;
        com.cim.AIA.AlgorithmWindow$5 a11 = new com.cim.AIA.AlgorithmWindow$5(this);
        a10.addActionListener((java.awt.event.ActionListener)a11);
        java.awt.Panel a12 = new java.awt.Panel();
        java.awt.Panel a13 = new java.awt.Panel();
        java.awt.Panel a14 = new java.awt.Panel();
        java.awt.Color a15 = java.awt.Color.gray;
        java.awt.Color a16 = a15.brighter();
        a12.setBackground(a16);
        java.awt.Color a17 = java.awt.Color.gray;
        java.awt.Color a18 = a17.brighter();
        a13.setBackground(a18);
        java.awt.Color a19 = java.awt.Color.gray;
        java.awt.Color a20 = a19.brighter();
        a14.setBackground(a20);
        com.cim.AIA.HelpableButton a21 = this.openAll;
        java.awt.Component a22 = a13.add((java.awt.Component)a21);
        com.cim.AIA.HelpableButton a23 = this.closeAll;
        java.awt.Component a24 = a13.add((java.awt.Component)a23);
        com.cim.AIA.HelpableButton a25 = this.addBreakPoint;
        java.awt.Component a26 = a14.add((java.awt.Component)a25);
        com.cim.AIA.HelpableButton a27 = this.delBreakPoint;
        java.awt.Component a28 = a14.add((java.awt.Component)a27);
        java.awt.GridBagLayout a29 = new java.awt.GridBagLayout();
        java.awt.GridBagConstraints a30 = new java.awt.GridBagConstraints();
        a12.setLayout((java.awt.LayoutManager)a29);
        this.buildConstraints(a30, 0, 0, 1, 1, 100, 50);
        java.awt.Insets a31 = new java.awt.Insets(0, 0, 0, 0);
        a30.insets = a31;
        a30.anchor = 11;
        a30.fill = 0;
        a29.setConstraints((java.awt.Component)a13, a30);
        java.awt.Component a32 = a12.add((java.awt.Component)a13);
        com.cim.AIA.BreakPoint a33 = this.breakpoint;
        label1: {
            label0: {
                if(a33 == null)
                {
                    break label0;
                }
                com.cim.AIA.BreakPoint a34 = this.breakpoint;
                int i = a34.enabled?1:0;
                if(i != 0)
                {
                    break label0;
                }
                a = a13;
                break label1;
            }
            this.buildConstraints(a30, 0, 1, 1, 1, 100, 50);
            java.awt.Insets a35 = new java.awt.Insets(0, 0, 0, 0);
            a30.insets = a35;
            a30.anchor = 15;
            a30.fill = 0;
            a29.setConstraints((java.awt.Component)a14, a30);
            java.awt.Component a36 = a12.add((java.awt.Component)a14);
            a = a12;
        }
        return a;
    }
    
    public com.cim.AIA.LadderTree getLadderTree(String s)
    {
        com.cim.AIA.CodeCanvas a = this.theCodeCanvas;
        com.cim.AIA.LadderTree a0 = a.getLadderTree(s);
        return a0;
    }
    
    public com.cim.AIA.Logger getLogger()
    {
        com.cim.AIA.Logger a = this.logger;
        return a;
    }
    
    public String getPosition()
    {
        com.cim.AIA.CodeCanvas a = this.theCodeCanvas;
        String s = a.getPosition();
        return s;
    }
    
    protected void initGui(java.awt.Dimension a)
    {
        java.awt.Color a0 = java.awt.Color.white;
        this.setBackground(a0);
        com.cim.AIA.CodeCanvas a1 = this.theCodeCanvas;
        java.awt.Image a2 = a1.logoImage;
        com.cim.AIA.CodeCanvas a3 = this.theCodeCanvas;
        java.awt.Dimension a4 = a3.logoImageSize;
        com.cim.AIA.ImageCanvas a5 = new com.cim.AIA.ImageCanvas(a2, a4);
        a5.addHelpListener((com.cim.AIA.HelpListener)this);
        java.awt.ScrollPane a6 = new java.awt.ScrollPane(0);
        this.scrollPane = a6;
        java.awt.GridBagLayout a7 = new java.awt.GridBagLayout();
        java.awt.GridBagConstraints a8 = new java.awt.GridBagConstraints();
        java.awt.Panel a9 = new java.awt.Panel();
        a9.setLayout((java.awt.LayoutManager)a7);
        java.awt.Panel a10 = this.getControlPanel();
        java.awt.Panel a11 = new java.awt.Panel();
        java.awt.GridBagLayout a12 = new java.awt.GridBagLayout();
        java.awt.GridBagConstraints a13 = new java.awt.GridBagConstraints();
        a11.setLayout((java.awt.LayoutManager)a12);
        java.awt.Color a14 = java.awt.Color.gray;
        java.awt.Color a15 = a14.brighter();
        a11.setBackground(a15);
        this.buildConstraints(a13, 0, 0, 1, 1, 15, 100);
        java.awt.Insets a16 = new java.awt.Insets(0, 5, 5, 0);
        a13.insets = a16;
        a13.anchor = 17;
        a13.fill = 0;
        a12.setConstraints((java.awt.Component)a5, a13);
        java.awt.Component a17 = a11.add((java.awt.Component)a5);
        this.buildConstraints(a13, 1, 0, 1, 1, 85, 100);
        java.awt.Insets a18 = new java.awt.Insets(0, 0, 0, 0);
        a13.insets = a18;
        a13.anchor = 10;
        a12.setConstraints((java.awt.Component)a10, a13);
        java.awt.Component a19 = a11.add((java.awt.Component)a10);
        java.awt.Insets a20 = new java.awt.Insets(0, 0, 0, 0);
        a8.insets = a20;
        a8.gridwidth = 0;
        a8.anchor = 11;
        a8.fill = 1;
        a8.weighty = 0.0;
        a7.setConstraints((java.awt.Component)a11, a8);
        java.awt.Component a21 = a9.add((java.awt.Component)a11);
        a8.weightx = 10.0;
        java.awt.Insets a22 = new java.awt.Insets(2, 2, 2, 2);
        a8.insets = a22;
        a8.weighty = 10.0;
        a8.fill = 1;
        a8.gridheight = 0;
        com.cim.AIA.CodeCanvas a23 = this.theCodeCanvas;
        java.awt.ScrollPane a24 = this.scrollPane;
        a23.setParent((java.awt.Container)a24);
        java.awt.ScrollPane a25 = this.scrollPane;
        com.cim.AIA.CodeCanvas a26 = this.theCodeCanvas;
        java.awt.Component a27 = a25.add((java.awt.Component)a26);
        java.awt.ScrollPane a28 = this.scrollPane;
        a7.setConstraints((java.awt.Component)a28, a8);
        java.awt.ScrollPane a29 = this.scrollPane;
        java.awt.Component a30 = a9.add((java.awt.Component)a29);
        java.awt.Component a31 = this.add((java.awt.Component)a9);
        this.setSize(a);
        com.cim.AIA.CodeCanvas a32 = this.theCodeCanvas;
        a32.addExplainationListener((com.cim.AIA.ExplainationListener)this);
        com.cim.AIA.CodeCanvas a33 = this.theCodeCanvas;
        a33.addHelpListener((com.cim.AIA.HelpListener)this);
    }
    
    public boolean isExpandable(String s)
    {
        com.cim.AIA.CodeCanvas a = this.theCodeCanvas;
        int i = a.isExpandable(s)?1:0;
        return i != 0;
    }
    
    public boolean isExpanded(String s)
    {
        com.cim.AIA.CodeCanvas a = this.theCodeCanvas;
        int i = a.isExpanded(s)?1:0;
        return i != 0;
    }
    
    public boolean isVisible(String s)
    {
        com.cim.AIA.CodeCanvas a = this.theCodeCanvas;
        int i = a.isVisible(s)?1:0;
        return i != 0;
    }
    
    public void mouseDragged(java.awt.event.MouseEvent a)
    {
    }
    
    public void mouseMoved(java.awt.event.MouseEvent a)
    {
        this.requestFocus();
    }
    
    protected void openAll()
    {
        com.cim.AIA.CodeCanvas a = this.theCodeCanvas;
        a.expandEntireTree();
    }
    
    public void processEndMutex()
    {
        com.cim.AIA.HelpableButton a = this.addBreakPoint;
        a.setEnabled(true);
        com.cim.AIA.HelpableButton a0 = this.delBreakPoint;
        a0.setEnabled(true);
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
        this.removeAll();
        this.dispose();
        this.cleanUp();
    }
    
    public void processExplainationEvent(com.cim.AIA.ExplainationEvent a)
    {
        int i = 0;
        while(true)
        {
            java.util.Vector a0 = this.explainationListeners;
            int i0 = a0.size();
            if(i >= i0)
            {
                return;
            }
            else
            {
                java.util.Vector a1 = this.explainationListeners;
                Object a2 = a1.elementAt(i);
                com.cim.AIA.ExplainationListener dummy = (com.cim.AIA.ExplainationListener)a2;
                ((com.cim.AIA.ExplainationListener)a2).processExplainationEvent(a);
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    public void processHelpEvent(com.cim.AIA.HelpEvent a)
    {
        int i = 0;
        while(true)
        {
            java.util.Vector a0 = this.helpListeners;
            int i0 = a0.size();
            if(i >= i0)
            {
                return;
            }
            else
            {
                java.util.Vector a1 = this.helpListeners;
                Object a2 = a1.elementAt(i);
                com.cim.AIA.HelpListener dummy = (com.cim.AIA.HelpListener)a2;
                ((com.cim.AIA.HelpListener)a2).processHelpEvent(a);
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    public void processRun()
    {
        com.cim.AIA.HelpableButton a = this.addBreakPoint;
        a.setEnabled(false);
        com.cim.AIA.HelpableButton a0 = this.delBreakPoint;
        a0.setEnabled(false);
    }
    
    public void processStartMutex()
    {
        com.cim.AIA.HelpableButton a = this.addBreakPoint;
        a.setEnabled(false);
        com.cim.AIA.HelpableButton a0 = this.delBreakPoint;
        a0.setEnabled(false);
    }
    
    public void processStopRun()
    {
        com.cim.AIA.HelpableButton a = this.addBreakPoint;
        a.setEnabled(true);
        com.cim.AIA.HelpableButton a0 = this.delBreakPoint;
        a0.setEnabled(true);
    }
    
    public void removeExplainationListener(com.cim.AIA.ExplainationListener a)
    {
        java.util.Vector a0 = this.explainationListeners;
        Object a1 = a;
        int i = a0.removeElement(a1)?1:0;
    }
    
    public void removeHelpListener(com.cim.AIA.HelpListener a)
    {
        com.cim.AIA.HelpableButton a0 = this.closeAll;
        Object a1 = a;
        a0.removeHelpListener((com.cim.AIA.HelpListener)a1);
        com.cim.AIA.HelpableButton a2 = this.openAll;
        a2.removeHelpListener((com.cim.AIA.HelpListener)a1);
        java.util.Vector a3 = this.helpListeners;
        int i = a3.removeElement(a1)?1:0;
    }
    
    public void setBreakPoint(com.cim.AIA.BreakPoint a)
    {
        this.breakpoint = a;
    }
    
    public void setLadderTree(com.cim.AIA.LadderTree a)
    {
        com.cim.AIA.CodeCanvas a0 = this.theCodeCanvas;
        a0.setLadderTree(a);
    }
    
    public void setLogger(com.cim.AIA.Logger a)
    {
        this.logger = a;
    }
    
    public void setPosition(String s)
    {
        com.cim.AIA.CodeCanvas a = this.theCodeCanvas;
        a.setPosition(s);
    }
    
    public boolean shouldRepaint(String s)
    {
        com.cim.AIA.CodeCanvas a = this.theCodeCanvas;
        int i = a.shouldRepaint(s)?1:0;
        return i != 0;
    }
    
    static
    {
        String s = localization.Messages.getString("AlgorithmWindow.0");
        com.cim.AIA.AlgorithmWindow.OPEN_ALL_TITLE = s;
        String s0 = localization.Messages.getString("AlgorithmWindow.1");
        com.cim.AIA.AlgorithmWindow.OPEN_ALL_INSTRUCTIONS = s0;
        String s1 = localization.Messages.getString("AlgorithmWindow.2");
        com.cim.AIA.AlgorithmWindow.CLOSE_ALL_TITLE = s1;
        String s2 = localization.Messages.getString("AlgorithmWindow.3");
        com.cim.AIA.AlgorithmWindow.CLOSE_ALL_INSTRUCTIONS = s2;
        String s3 = localization.Messages.getString("AlgorithmWindow.4");
        com.cim.AIA.AlgorithmWindow.ADD_BREAKPOINT_TITLE = s3;
        StringBuilder a = new StringBuilder();
        String s4 = localization.Messages.getString("AlgorithmWindow.5");
        StringBuilder a0 = a.append(s4);
        String s5 = localization.Messages.getString("AlgorithmWindow.6");
        StringBuilder a1 = a0.append(s5);
        String s6 = localization.Messages.getString("AlgorithmWindow.7");
        StringBuilder a2 = a1.append(s6);
        String s7 = a2.toString();
        com.cim.AIA.AlgorithmWindow.ADD_BREAKPOINT_INSTRUCTIONS = s7;
        String s8 = localization.Messages.getString("AlgorithmWindow.8");
        com.cim.AIA.AlgorithmWindow.DEL_BREAKPOINT_TITLE = s8;
        StringBuilder a3 = new StringBuilder();
        String s9 = localization.Messages.getString("AlgorithmWindow.9");
        StringBuilder a4 = a3.append(s9);
        String s10 = localization.Messages.getString("AlgorithmWindow.10");
        StringBuilder a5 = a4.append(s10);
        String s11 = localization.Messages.getString("AlgorithmWindow.11");
        StringBuilder a6 = a5.append(s11);
        String s12 = a6.toString();
        com.cim.AIA.AlgorithmWindow.DEL_BREAKPOINT_INSTRUCTIONS = s12;
    }
}