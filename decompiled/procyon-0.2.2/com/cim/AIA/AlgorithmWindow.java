package com.cim.AIA;

import localization.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AlgorithmWindow extends JFrame implements ExplainationListener, Explainable, ExitListener, Helpable, HelpListener, MouseMotionListener
{
    private static final long serialVersionUID = -3775287959600581277L;
    protected static final String OPEN_ALL_TITLE;
    protected static final String OPEN_ALL_INSTRUCTIONS;
    protected static final String CLOSE_ALL_TITLE;
    protected static final String CLOSE_ALL_INSTRUCTIONS;
    protected static final String ADD_BREAKPOINT_TITLE;
    protected static final String ADD_BREAKPOINT_INSTRUCTIONS;
    protected static final String DEL_BREAKPOINT_TITLE;
    protected static final String DEL_BREAKPOINT_INSTRUCTIONS;
    protected ScrollPane scrollPane;
    protected HelpableButton openAll;
    protected HelpableButton closeAll;
    protected HelpableButton addBreakPoint;
    protected HelpableButton delBreakPoint;
    int nlines;
    protected CodeCanvas theCodeCanvas;
    protected Vector<ExplainationListener> explainationListeners;
    protected Vector<HelpListener> helpListeners;
    protected Logger logger;
    protected BreakPoint breakpoint;
    
    public AlgorithmWindow(final String title, final CodeCanvas codeCanvas) {
        this(title, codeCanvas, new Dimension(500, 200));
    }
    
    public AlgorithmWindow(final String title, final CodeCanvas codeCanvas, final Dimension size) {
        this(title, codeCanvas, size, true);
    }
    
    protected AlgorithmWindow(final String title, final CodeCanvas codeCanvas, final Dimension size, final boolean initGuiAlso) {
        super(title);
        this.explainationListeners = new Vector();
        this.helpListeners = new Vector();
        this.theCodeCanvas = codeCanvas;
        this.addMouseListener(new MouseListener() {
            public void mouseClicked(final MouseEvent e) {
            }
            
            public void mouseEntered(final MouseEvent e) {
                AlgorithmWindow.this.requestFocus();
            }
            
            public void mouseExited(final MouseEvent e) {
            }
            
            public void mousePressed(final MouseEvent e) {
            }
            
            public void mouseReleased(final MouseEvent e) {
            }
        });
        this.addMouseMotionListener(this);
        codeCanvas.addMouseMotionListener(this);
        if (initGuiAlso) {
            this.initGui(size);
        }
    }
    
    public void addExplainationListener(final ExplainationListener explainationListener) {
        this.explainationListeners.addElement(explainationListener);
    }
    
    public void addHelpListener(final HelpListener helpListener) {
        this.closeAll.addHelpListener(helpListener);
        this.openAll.addHelpListener(helpListener);
        this.addBreakPoint.addHelpListener(helpListener);
        this.delBreakPoint.addHelpListener(helpListener);
        this.helpListeners.addElement(helpListener);
    }
    
    private void buildConstraints(final GridBagConstraints gbc, final int gx, final int gy, final int gw, final int gh, final int wx, final int wy) {
        gbc.gridx = gx;
        gbc.gridy = gy;
        gbc.gridwidth = gw;
        gbc.gridheight = gh;
        gbc.weightx = (double)wx;
        gbc.weighty = (double)wy;
    }
    
    public void cleanUp() {
        if (this.scrollPane != null) {
            this.scrollPane.removeAll();
        }
        this.scrollPane = null;
        if (this.openAll != null) {
            this.openAll.removeAllHelpListeners();
        }
        this.openAll = null;
        if (this.closeAll != null) {
            this.closeAll.removeAllHelpListeners();
        }
        this.closeAll = null;
        if (this.addBreakPoint != null) {
            this.addBreakPoint.removeAllHelpListeners();
        }
        this.addBreakPoint = null;
        if (this.delBreakPoint != null) {
            this.delBreakPoint.removeAllHelpListeners();
        }
        this.delBreakPoint = null;
        if (this.theCodeCanvas != null) {
            this.theCodeCanvas.cleanUp();
        }
        this.theCodeCanvas = null;
        if (this.explainationListeners != null) {
            this.explainationListeners.removeAllElements();
        }
        this.explainationListeners = null;
        if (this.helpListeners != null) {
            this.helpListeners.removeAllElements();
        }
        this.helpListeners = null;
        this.breakpoint = null;
    }
    
    protected void closeAll() {
        this.theCodeCanvas.collapseEntireTree();
    }
    
    public BreakPoint getBreakPoint() {
        return this.breakpoint;
    }
    
    protected Panel getControlPanel() {
        this.addBreakPoint = new HelpableButton(AlgorithmWindow.ADD_BREAKPOINT_TITLE, AlgorithmWindow.ADD_BREAKPOINT_INSTRUCTIONS);
        this.addBreakPoint.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (AlgorithmWindow.this.breakpoint != null) {
                    AlgorithmWindow.this.breakpoint.add();
                }
                else {
                    System.err.println("Warning: null breakpoint");
                }
            }
        });
        this.delBreakPoint = new HelpableButton(AlgorithmWindow.DEL_BREAKPOINT_TITLE, AlgorithmWindow.DEL_BREAKPOINT_INSTRUCTIONS);
        this.delBreakPoint.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (AlgorithmWindow.this.breakpoint != null) {
                    AlgorithmWindow.this.breakpoint.del();
                }
            }
        });
        this.openAll = new HelpableButton(AlgorithmWindow.OPEN_ALL_TITLE, AlgorithmWindow.OPEN_ALL_INSTRUCTIONS);
        this.openAll.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final Log l1 = new Log(5, 4, AlgorithmWindow.this.getPosition());
                if (AlgorithmWindow.this.logger != null) {
                    AlgorithmWindow.this.logger.addLog(l1);
                }
                AlgorithmWindow.this.openAll();
            }
        });
        this.closeAll = new HelpableButton(AlgorithmWindow.CLOSE_ALL_TITLE, AlgorithmWindow.CLOSE_ALL_INSTRUCTIONS);
        this.closeAll.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final Log l1 = new Log(5, 5, AlgorithmWindow.this.getPosition());
                if (AlgorithmWindow.this.logger != null) {
                    AlgorithmWindow.this.logger.addLog(l1);
                }
                AlgorithmWindow.this.closeAll();
            }
        });
        final Panel panel = new Panel();
        final Panel panel2 = new Panel();
        final Panel panel3 = new Panel();
        panel.setBackground(Color.gray.brighter());
        panel2.setBackground(Color.gray.brighter());
        panel3.setBackground(Color.gray.brighter());
        panel2.add(this.openAll);
        panel2.add(this.closeAll);
        panel3.add(this.addBreakPoint);
        panel3.add(this.delBreakPoint);
        final GridBagLayout gbLayout = new GridBagLayout();
        final GridBagConstraints constraints = new GridBagConstraints();
        panel.setLayout(gbLayout);
        this.buildConstraints(constraints, 0, 0, 1, 1, 100, 50);
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.anchor = 11;
        constraints.fill = 0;
        gbLayout.setConstraints(panel2, constraints);
        panel.add(panel2);
        if (this.breakpoint != null && !this.breakpoint.enabled) {
            return panel2;
        }
        this.buildConstraints(constraints, 0, 1, 1, 1, 100, 50);
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.anchor = 15;
        constraints.fill = 0;
        gbLayout.setConstraints(panel3, constraints);
        panel.add(panel3);
        return panel;
    }
    
    public LadderTree getLadderTree(final String key) {
        return this.theCodeCanvas.getLadderTree(key);
    }
    
    public Logger getLogger() {
        return this.logger;
    }
    
    public String getPosition() {
        return this.theCodeCanvas.getPosition();
    }
    
    protected void initGui(final Dimension size) {
        this.setBackground(Color.white);
        final ImageCanvas logoCanvas = new ImageCanvas(this.theCodeCanvas.logoImage, this.theCodeCanvas.logoImageSize);
        logoCanvas.addHelpListener(this);
        this.scrollPane = new ScrollPane(0);
        final GridBagLayout gbLayout = new GridBagLayout();
        final GridBagConstraints c = new GridBagConstraints();
        final Panel main = new Panel();
        main.setLayout(gbLayout);
        final Panel panel = this.getControlPanel();
        final Panel logoControlPanel = new Panel();
        final GridBagLayout gbLayout2 = new GridBagLayout();
        final GridBagConstraints constraints = new GridBagConstraints();
        logoControlPanel.setLayout(gbLayout2);
        logoControlPanel.setBackground(Color.gray.brighter());
        this.buildConstraints(constraints, 0, 0, 1, 1, 15, 100);
        constraints.insets = new Insets(0, 5, 5, 0);
        constraints.anchor = 17;
        constraints.fill = 0;
        gbLayout2.setConstraints(logoCanvas, constraints);
        logoControlPanel.add(logoCanvas);
        this.buildConstraints(constraints, 1, 0, 1, 1, 85, 100);
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.anchor = 10;
        gbLayout2.setConstraints(panel, constraints);
        logoControlPanel.add(panel);
        c.insets = new Insets(0, 0, 0, 0);
        c.gridwidth = 0;
        c.anchor = 11;
        c.fill = 1;
        c.weighty = 0.0;
        gbLayout.setConstraints(logoControlPanel, c);
        main.add(logoControlPanel);
        c.weightx = 10.0;
        c.insets = new Insets(2, 2, 2, 2);
        c.weighty = 10.0;
        c.fill = 1;
        c.gridheight = 0;
        this.theCodeCanvas.setParent(this.scrollPane);
        this.scrollPane.add(this.theCodeCanvas);
        gbLayout.setConstraints(this.scrollPane, c);
        main.add(this.scrollPane);
        this.add(main);
        this.setSize(size);
        this.theCodeCanvas.addExplainationListener(this);
        this.theCodeCanvas.addHelpListener(this);
    }
    
    public boolean isExpandable(final String key) {
        return this.theCodeCanvas.isExpandable(key);
    }
    
    public boolean isExpanded(final String key) {
        return this.theCodeCanvas.isExpanded(key);
    }
    
    public boolean isVisible(final String tag) {
        return this.theCodeCanvas.isVisible(tag);
    }
    
    public void mouseDragged(final MouseEvent e) {
    }
    
    public void mouseMoved(final MouseEvent e) {
        this.requestFocus();
    }
    
    protected void openAll() {
        this.theCodeCanvas.expandEntireTree();
    }
    
    public void processEndMutex() {
        this.addBreakPoint.setEnabled(true);
        this.delBreakPoint.setEnabled(true);
    }
    
    public void processExitEvent(final ExitEvent e) {
        this.setVisible(false);
        this.theCodeCanvas.removeMouseMotionListener(this.theCodeCanvas);
        this.theCodeCanvas.removeMouseListener(this.theCodeCanvas);
        this.removeAll();
        this.dispose();
        this.cleanUp();
    }
    
    public void processExplainationEvent(final ExplainationEvent e) {
        for (int i = 0; i < this.explainationListeners.size(); ++i) {
            ((ExplainationListener)this.explainationListeners.elementAt(i)).processExplainationEvent(e);
        }
    }
    
    public void processHelpEvent(final HelpEvent e) {
        for (int i = 0; i < this.helpListeners.size(); ++i) {
            ((HelpListener)this.helpListeners.elementAt(i)).processHelpEvent(e);
        }
    }
    
    public void processRun() {
        this.addBreakPoint.setEnabled(false);
        this.delBreakPoint.setEnabled(false);
    }
    
    public void processStartMutex() {
        this.addBreakPoint.setEnabled(false);
        this.delBreakPoint.setEnabled(false);
    }
    
    public void processStopRun() {
        this.addBreakPoint.setEnabled(true);
        this.delBreakPoint.setEnabled(true);
    }
    
    public void removeExplainationListener(final ExplainationListener explainationListener) {
        this.explainationListeners.removeElement(explainationListener);
    }
    
    public void removeHelpListener(final HelpListener helpListener) {
        this.closeAll.removeHelpListener(helpListener);
        this.openAll.removeHelpListener(helpListener);
        this.helpListeners.removeElement(helpListener);
    }
    
    public void setBreakPoint(final BreakPoint bp) {
        this.breakpoint = bp;
    }
    
    public void setLadderTree(final LadderTree ladderTree) {
        this.theCodeCanvas.setLadderTree(ladderTree);
    }
    
    public void setLogger(final Logger logger) {
        this.logger = logger;
    }
    
    public void setPosition(final String key) {
        this.theCodeCanvas.setPosition(key);
    }
    
    public boolean shouldRepaint(final String key) {
        return this.theCodeCanvas.shouldRepaint(key);
    }
    
    static {
        OPEN_ALL_TITLE = Messages.getString("AlgorithmWindow.0");
        OPEN_ALL_INSTRUCTIONS = Messages.getString("AlgorithmWindow.1");
        CLOSE_ALL_TITLE = Messages.getString("AlgorithmWindow.2");
        CLOSE_ALL_INSTRUCTIONS = Messages.getString("AlgorithmWindow.3");
        ADD_BREAKPOINT_TITLE = Messages.getString("AlgorithmWindow.4");
        ADD_BREAKPOINT_INSTRUCTIONS = Messages.getString("AlgorithmWindow.5") + Messages.getString("AlgorithmWindow.6") + Messages.getString("AlgorithmWindow.7");
        DEL_BREAKPOINT_TITLE = Messages.getString("AlgorithmWindow.8");
        DEL_BREAKPOINT_INSTRUCTIONS = Messages.getString("AlgorithmWindow.9") + Messages.getString("AlgorithmWindow.10") + Messages.getString("AlgorithmWindow.11");
    }
}
