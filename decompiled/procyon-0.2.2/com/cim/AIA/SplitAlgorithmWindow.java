package com.cim.AIA;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class SplitAlgorithmWindow extends AlgorithmWindow implements MethodSelectable, ItemListener, ControlListener
{
    private static final long serialVersionUID = -8703106793389666711L;
    protected CheckboxGroup methodCheckboxGroup;
    protected Vector<Checkbox> methodRadioCheckboxes;
    protected Panel methodPanel;
    protected Vector<MethodSelection> methodSelections;
    protected Vector<MethodSelectionListener> methodSelectionListeners;
    protected CodeCanvas secondCodeCanvas;
    protected ScrollPane secondScrollPane;
    protected MethodSelection currentMethodSelection;
    protected boolean enabledCheckboxes;
    protected int ratioTopAndBottom;
    
    public SplitAlgorithmWindow(final String title, final CodeCanvas codeCanvas) {
        super(title, codeCanvas, new Dimension(500, 200), false);
        this.enabledCheckboxes = true;
        this.ratioTopAndBottom = 33;
        this.initGui(new Dimension(500, 200));
    }
    
    public SplitAlgorithmWindow(final String title, final CodeCanvas codeCanvas, final Dimension size) {
        super(title, codeCanvas, size, false);
        this.enabledCheckboxes = true;
        this.ratioTopAndBottom = 33;
        this.initGui(size);
    }
    
    public SplitAlgorithmWindow(final String title, final CodeCanvas codeCanvas, final Dimension size, final int ratioTopAndBottom) {
        super(title, codeCanvas, size, false);
        this.enabledCheckboxes = true;
        if (ratioTopAndBottom > 0 && ratioTopAndBottom < 100) {
            this.ratioTopAndBottom = ratioTopAndBottom;
        }
        else {
            this.ratioTopAndBottom = 33;
        }
        this.initGui(size);
    }
    
    public void addMethodSelection(final MethodSelection methodSelection, final boolean initialState) {
        this.methodSelections.addElement(methodSelection);
        final Checkbox checkbox = new Checkbox(methodSelection.getName(), initialState, this.methodCheckboxGroup);
        checkbox.addItemListener(this);
        this.methodRadioCheckboxes.addElement(checkbox);
        this.methodPanel.add(checkbox);
        if (initialState && methodSelection != this.currentMethodSelection) {
            this.setMethod(methodSelection);
            this.informMethodSelectionListeners(methodSelection);
        }
    }
    
    public void addMethodSelectionListener(final MethodSelectionListener methodListener) {
        this.methodSelectionListeners.addElement(methodListener);
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
        super.cleanUp();
        this.methodCheckboxGroup = null;
        if (this.methodRadioCheckboxes != null) {
            this.methodRadioCheckboxes.removeAllElements();
        }
        this.methodRadioCheckboxes = null;
        if (this.methodPanel != null) {
            this.methodPanel.removeAll();
        }
        this.methodPanel = null;
        if (this.methodSelections != null) {
            this.methodSelections.removeAllElements();
        }
        this.methodSelections = null;
        if (this.secondCodeCanvas != null) {
            this.secondCodeCanvas.cleanUp();
        }
        this.secondCodeCanvas = null;
        if (this.secondScrollPane != null) {
            this.secondScrollPane.removeAll();
        }
        this.secondScrollPane = null;
        this.currentMethodSelection = null;
    }
    
    protected void closeAll() {
        this.theCodeCanvas.collapseEntireTree();
        this.secondCodeCanvas.collapseEntireTree();
    }
    
    public void controlBack(final ControlEvent e) {
    }
    
    public void controlOther(final ControlEvent e) {
        if (e.getType() == 123128) {
            this.setEnableCheckboxes(true);
        }
        else if (e.getType() == 123129) {
            this.setEnableCheckboxes(true);
        }
    }
    
    public void controlPause(final ControlEvent e) {
    }
    
    public void controlReset(final ControlEvent e) {
        this.setEnableCheckboxes(true);
    }
    
    public void controlRestart(final ControlEvent e) {
        this.setEnableCheckboxes(true);
    }
    
    public void controlRun(final ControlEvent e) {
        if (this.enabledCheckboxes) {
            this.setEnableCheckboxes(false);
        }
    }
    
    public void controlStep(final ControlEvent e) {
        if (this.enabledCheckboxes) {
            this.setEnableCheckboxes(false);
        }
    }
    
    public String getPosition() {
        final String temp = this.secondCodeCanvas.getPosition();
        if (temp != null && temp != "") {
            return temp;
        }
        return this.theCodeCanvas.getPosition();
    }
    
    protected void informMethodSelectionListeners(final MethodSelection methodSelection) {
        final MethodSelectionEvent methodSelectionEvent = new MethodSelectionEvent(this, methodSelection.getName());
        for (int i = 0; i < this.methodSelectionListeners.size(); ++i) {
            ((MethodSelectionListener)this.methodSelectionListeners.elementAt(i)).processMethodSelectionEvent(methodSelectionEvent);
        }
    }
    
    protected void initGui(final Dimension size) {
        this.methodCheckboxGroup = new CheckboxGroup();
        this.methodRadioCheckboxes = new Vector();
        this.methodPanel = new Panel();
        this.methodSelections = new Vector();
        this.methodSelectionListeners = new Vector();
        this.secondCodeCanvas = new CodeCanvas(this.theCodeCanvas.getExpandedImage(), this.theCodeCanvas.getCollapsedImage(), this.getLogger(), this.getBreakPoint());
        this.secondCodeCanvas.dataPath = this.theCodeCanvas.dataPath;
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
        this.methodPanel.setBackground(Color.gray.brighter());
        this.buildConstraints(constraints, 0, 0, 1, 2, 15, 100);
        constraints.insets = new Insets(0, 5, 5, 0);
        constraints.anchor = 17;
        constraints.fill = 0;
        gbLayout2.setConstraints(logoCanvas, constraints);
        logoControlPanel.add(logoCanvas);
        this.buildConstraints(constraints, 1, 0, 1, 1, 85, 100);
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.anchor = 10;
        gbLayout2.setConstraints(this.methodPanel, constraints);
        logoControlPanel.add(this.methodPanel);
        this.buildConstraints(constraints, 1, 1, 1, 1, 85, 100);
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
        c.weighty = (double)this.ratioTopAndBottom;
        c.fill = 1;
        this.theCodeCanvas.setParent(this.scrollPane);
        this.scrollPane.add(this.theCodeCanvas);
        gbLayout.setConstraints(this.scrollPane, c);
        main.add(this.scrollPane);
        this.secondScrollPane = new ScrollPane(0);
        this.secondCodeCanvas.setParent(this.secondScrollPane);
        this.secondScrollPane.add(this.secondCodeCanvas);
        c.weighty = (double)(100 - this.ratioTopAndBottom);
        c.gridheight = 0;
        gbLayout.setConstraints(this.secondScrollPane, c);
        main.add(this.secondScrollPane);
        this.add(main);
        this.setSize(size);
        this.theCodeCanvas.addExplainationListener(this);
        this.theCodeCanvas.addHelpListener(this);
        this.secondCodeCanvas.helpListeners = this.theCodeCanvas.helpListeners;
        this.secondCodeCanvas.explainationListeners = this.theCodeCanvas.explainationListeners;
    }
    
    public boolean isExpandable(final String key) {
        return this.theCodeCanvas.isExpandable(key) || this.secondCodeCanvas.isExpandable(key);
    }
    
    public boolean isExpanded(final String key) {
        return this.theCodeCanvas.isExpanded(key) || this.secondCodeCanvas.isExpanded(key);
    }
    
    public boolean isVisible(final String tag) {
        return this.theCodeCanvas.isVisible(tag) || this.secondCodeCanvas.isVisible(tag);
    }
    
    public void itemStateChanged(final ItemEvent e) {
        final Checkbox checkbox = this.methodCheckboxGroup.getSelectedCheckbox();
        if (checkbox != null) {
            final Log l1 = new Log(5, 18, this.getPosition(), checkbox.getLabel());
            if (this.getLogger() != null) {
                this.getLogger().addLog(l1);
            }
            for (int i = 0; i < this.methodSelections.size(); ++i) {
                final MethodSelection methodSelection = (MethodSelection)this.methodSelections.elementAt(i);
                if (methodSelection.getName().equalsIgnoreCase(checkbox.getLabel())) {
                    if (methodSelection != this.currentMethodSelection) {
                        this.setMethod(methodSelection);
                        this.informMethodSelectionListeners(methodSelection);
                    }
                    return;
                }
            }
        }
    }
    
    protected void openAll() {
        this.theCodeCanvas.expandEntireTree();
        this.secondCodeCanvas.expandEntireTree();
    }
    
    public void processExitEvent(final ExitEvent e) {
        this.setVisible(false);
        this.theCodeCanvas.removeMouseMotionListener(this.theCodeCanvas);
        this.theCodeCanvas.removeMouseListener(this.theCodeCanvas);
        this.secondCodeCanvas.removeMouseMotionListener(this.secondCodeCanvas);
        this.secondCodeCanvas.removeMouseListener(this.secondCodeCanvas);
        this.removeAll();
        this.dispose();
        this.cleanUp();
    }
    
    public void removeMethodSelection(MethodSelection methodSelection) {
        int i = 0;
        while (i < this.methodRadioCheckboxes.size()) {
            final Checkbox checkbox = (Checkbox)this.methodRadioCheckboxes.elementAt(i);
            if (checkbox.getLabel().equalsIgnoreCase(methodSelection.getName())) {
                checkbox.removeItemListener(this);
                checkbox.setCheckboxGroup(null);
                this.methodRadioCheckboxes.removeElement(checkbox);
                this.methodPanel.remove(checkbox);
                if (methodSelection == this.currentMethodSelection) {
                    methodSelection = null;
                    break;
                }
                break;
            }
            else {
                ++i;
            }
        }
        this.methodSelections.removeElement(methodSelection);
    }
    
    public void removeMethodSelectionListener(final MethodSelectionListener methodListener) {
        this.methodSelectionListeners.removeElement(methodListener);
    }
    
    protected void setEnableCheckboxes(final boolean state) {
        this.enabledCheckboxes = state;
        for (int i = 0; i < this.methodSelections.size(); ++i) {
            final Checkbox methodRadioCheckbox = (Checkbox)this.methodRadioCheckboxes.elementAt(i);
            methodRadioCheckbox.setEnabled(state);
        }
    }
    
    protected void setMethod(final MethodSelection methodSelection) {
        if (methodSelection.getLadderTree() != null) {
            this.currentMethodSelection = methodSelection;
            this.secondCodeCanvas.setLadderTree(methodSelection.getLadderTree());
        }
        else {
            System.out.println("com.cim.AIA.SplitAlgorithmWindow.setMethod:  LadderTree for MethodSelection '" + methodSelection.getName() + "' is null");
        }
    }
    
    public void setPosition(final String key) {
        if (this.theCodeCanvas.containsKey(key)) {
            this.theCodeCanvas.setPosition(key);
        }
        else if (this.currentMethodSelection != null) {
            this.theCodeCanvas.setPosition(this.currentMethodSelection.getKey());
        }
        if (this.secondCodeCanvas.containsKey(key)) {
            this.secondCodeCanvas.setPosition(key);
        }
    }
    
    public boolean shouldRepaint(final String key) {
        return this.theCodeCanvas.shouldRepaint(key) || this.secondCodeCanvas.shouldRepaint(key);
    }
}
