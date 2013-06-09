package com.cim.AIA;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class MultiAlgorithmWindow extends AlgorithmWindow implements MethodSelectable, ItemListener, ControlListener, MouseMotionListener
{
    private static final long serialVersionUID = -7641714727443148790L;
    private static String IMAGE_DIRECTORY;
    protected CheckboxGroup methodCheckboxGroup;
    protected Vector<Checkbox> methodRadioCheckboxes;
    protected Vector<MethodSelection> methodSelections;
    private Vector<MultiCanvas> additionalCanvas;
    private Hashtable<String, Integer> canvasLookup;
    private WindowListener standardWindowListener;
    private MouseListener commonMouseListener;
    protected Panel methodPanel;
    protected Vector<MethodSelectionListener> methodSelectionListeners;
    protected MethodSelection currentMethodSelection;
    protected boolean enabledCheckboxes;
    protected AlgorithmApplet algorithmApplet;
    
    public MultiAlgorithmWindow(final AlgorithmApplet algorithmApplet, final String title, final CodeCanvas codeCanvas) {
        this(algorithmApplet, title, codeCanvas, new Dimension(500, 200));
    }
    
    public MultiAlgorithmWindow(final AlgorithmApplet algorithmApplet, final String title, final CodeCanvas codeCanvas, final Dimension size) {
        this(algorithmApplet, title, codeCanvas, size, true);
    }
    
    protected MultiAlgorithmWindow(final AlgorithmApplet algorithmApplet, final String title, final CodeCanvas codeCanvas, final Dimension size, final boolean initGuiAlso) {
        super(title, codeCanvas, size, false);
        this.enabledCheckboxes = true;
        this.additionalCanvas = new Vector();
        this.canvasLookup = new Hashtable();
        this.algorithmApplet = algorithmApplet;
        this.theCodeCanvas = codeCanvas;
        this.setTitle(title);
        this.addMouseListener(new MouseListener() {
            public void mouseClicked(final MouseEvent e) {
            }
            
            public void mouseEntered(final MouseEvent e) {
                MultiAlgorithmWindow.this.requestFocus();
            }
            
            public void mouseExited(final MouseEvent e) {
            }
            
            public void mousePressed(final MouseEvent e) {
            }
            
            public void mouseReleased(final MouseEvent e) {
            }
        });
        this.commonMouseListener = new MouseListener() {
            public void mouseClicked(final MouseEvent e) {
            }
            
            public void mouseEntered(final MouseEvent e) {
            }
            
            public void mouseExited(final MouseEvent e) {
            }
            
            public void mousePressed(final MouseEvent e) {
                MultiAlgorithmWindow.access$300(MultiAlgorithmWindow.this, e);
            }
            
            public void mouseReleased(final MouseEvent e) {
            }
        };
        this.standardWindowListener = new WindowAdapter() {
            public void windowClosed(final WindowEvent e) {
            }
            
            public void windowClosing(final WindowEvent e) {
            }
            
            public void windowDeactivated(final WindowEvent e) {
            }
            
            public void windowDeiconified(final WindowEvent e) {
            }
            
            public void windowIconified(final WindowEvent e) {
            }
            
            public void windowOpened(final WindowEvent e) {
            }
        };
        this.addMouseMotionListener(this);
        this.theCodeCanvas.addMouseListener(this.commonMouseListener);
        this.theCodeCanvas.addMouseMotionListener(this);
        if (initGuiAlso) {
            this.initGui(size);
        }
    }
    
    public void addCodeCanvas(final String dataDir, final String filename, final String triggerKey, final String unHighlight) {
        this.additionalCanvas.addElement(new MultiCanvas(filename, dataDir, filename, triggerKey, unHighlight, this, this));
        this.canvasLookup.put(filename, new Integer(this.additionalCanvas.size() - 1));
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
    
    protected void closeAll() {
        this.theCodeCanvas.collapseEntireTree();
        for (int i = 0; i < this.additionalCanvas.size(); ++i) {
            final MultiCanvas currentCanvas = (MultiCanvas)this.additionalCanvas.elementAt(i);
            currentCanvas.canvas.collapseEntireTree();
            currentCanvas.isVisible = false;
            currentCanvas.setVisible(false);
        }
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
    
    protected void informMethodSelectionListeners(final MethodSelection methodSelection) {
        final MethodSelectionEvent methodSelectionEvent = new MethodSelectionEvent(this, methodSelection.getName());
        for (int i = 0; i < this.methodSelectionListeners.size(); ++i) {
            ((MethodSelectionListener)this.methodSelectionListeners.elementAt(i)).processMethodSelectionEvent(methodSelectionEvent);
        }
    }
    
    protected void initGui(final Dimension size) {
        this.methodCheckboxGroup = new CheckboxGroup();
        this.methodSelections = new Vector();
        this.methodRadioCheckboxes = new Vector();
        this.methodSelectionListeners = new Vector();
        this.methodPanel = new Panel();
        this.scrollPane = new ScrollPane(0);
        final GridBagLayout gbLayout = new GridBagLayout();
        final GridBagConstraints c = new GridBagConstraints();
        final Panel main = new Panel();
        main.setLayout(gbLayout);
        final Panel panel = this.getControlPanel();
        c.insets = new Insets(0, 0, 0, 0);
        c.gridwidth = 0;
        c.anchor = 11;
        c.fill = 1;
        c.weighty = 0.0;
        this.methodPanel.setBackground(Color.gray.brighter());
        gbLayout.setConstraints(this.methodPanel, c);
        main.add(this.methodPanel);
        gbLayout.setConstraints(panel, c);
        main.add(panel);
        c.weightx = 10.0;
        c.insets = new Insets(2, 2, 2, 2);
        c.weighty = 10.0;
        c.fill = 1;
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
        return super.isExpandable(key);
    }
    
    public boolean isExpanded(final String key) {
        boolean isExpanded = this.theCodeCanvas.isExpanded(key);
        for (int i = 0; i < this.additionalCanvas.size(); ++i) {
            final MultiCanvas currentCanvas = (MultiCanvas)this.additionalCanvas.elementAt(i);
            isExpanded = (((!currentCanvas.canvas.isExpanded(key) || !currentCanvas.isVisible) ? false : true) | isExpanded);
        }
        return isExpanded;
    }
    
    public boolean isVisible(final String tag) {
        boolean isVisible = false;
        if (this.theCodeCanvas.isVisible(tag)) {
            isVisible = true;
        }
        for (int i = 0; i < this.additionalCanvas.size(); ++i) {
            final MultiCanvas currentCanvas = (MultiCanvas)this.additionalCanvas.elementAt(i);
            if (currentCanvas.canvas.isVisible(tag) && currentCanvas.isVisible) {
                isVisible = true;
            }
        }
        return isVisible;
    }
    
    public void itemStateChanged(final ItemEvent e) {
        final Checkbox checkbox = this.methodCheckboxGroup.getSelectedCheckbox();
        if (checkbox != null) {
            for (int i = 0; i < this.methodSelections.size(); ++i) {
                final MethodSelection methodSelection = (MethodSelection)this.methodSelections.elementAt(i);
                if (methodSelection.getName().equalsIgnoreCase(checkbox.getLabel())) {
                    if (methodSelection != this.currentMethodSelection) {
                        this.closeAll();
                        this.setMethod(methodSelection);
                        this.informMethodSelectionListeners(methodSelection);
                    }
                    return;
                }
            }
        }
    }
    
    protected void openAll() {
        this.openAll(this.theCodeCanvas);
    }
    
    protected void openAll(final CodeCanvas sourceCanvas) {
        sourceCanvas.expandEntireTree();
        for (int i = 0; i < this.additionalCanvas.size(); ++i) {
            final MultiCanvas currentCanvas = (MultiCanvas)this.additionalCanvas.elementAt(i);
            if (sourceCanvas.isExpanded(currentCanvas.triggerKey)) {
                currentCanvas.isVisible = true;
                currentCanvas.setVisible(true);
                this.openAll(currentCanvas.canvas);
            }
        }
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
    
    public void setLocation(final Point point, final String filename) {
        ((MultiCanvas)this.additionalCanvas.elementAt(((Integer)this.canvasLookup.get(filename)).intValue())).setLocation(point);
    }
    
    protected void setMethod(final MethodSelection methodSelection) {
        if (methodSelection.getLadderTree() != null) {
            this.currentMethodSelection = methodSelection;
            this.theCodeCanvas.setLadderTree(methodSelection.getLadderTree());
        }
        else {
            System.out.println("com.cim.AIA.SplitAlgorithmWindow.setMethod:  LadderTree for MethodSelection '" + methodSelection.getName() + "' is null");
        }
    }
    
    public void setPosition(final String key) {
        if (this.theCodeCanvas.containsKey(key)) {
            this.theCodeCanvas.setPosition(key);
        }
        for (int i = 0; i < this.additionalCanvas.size(); ++i) {
            final MultiCanvas currentCanvas = (MultiCanvas)this.additionalCanvas.elementAt(i);
            if (currentCanvas.isVisible() ^ currentCanvas.isVisible) {
                currentCanvas.setVisible(currentCanvas.isVisible);
            }
            if (currentCanvas.canvas.containsKey(key)) {
                currentCanvas.canvas.setPosition(key);
            }
            if (key.compareTo(currentCanvas.unHighlight) == 0) {
                currentCanvas.canvas.setPosition("");
            }
        }
    }
    
    public void setSize(final Dimension size, final String filename) {
        ((MultiCanvas)this.additionalCanvas.elementAt(((Integer)this.canvasLookup.get(filename)).intValue())).setSize(size);
    }
    
    public boolean shouldRepaint(final String key) {
        final CodeCanvas temp = this.theCodeCanvas;
        boolean isRepaint = super.shouldRepaint(key);
        for (int i = 0; i < this.additionalCanvas.size(); ++i) {
            if (((MultiCanvas)this.additionalCanvas.elementAt(i)).isVisible) {
                this.theCodeCanvas = ((MultiCanvas)this.additionalCanvas.elementAt(i)).canvas;
                isRepaint = (super.shouldRepaint(key) | isRepaint);
            }
        }
        this.theCodeCanvas = temp;
        return isRepaint;
    }
    
    private void updateVisibleCanvases(final MouseEvent e) {
        final CodeCanvas sourceCanvas = (CodeCanvas)e.getSource();
        for (int i = 0; i < this.additionalCanvas.size(); ++i) {
            final MultiCanvas currentCanvas = (MultiCanvas)this.additionalCanvas.elementAt(i);
            if (sourceCanvas.isExpanded(currentCanvas.triggerKey)) {
                currentCanvas.isVisible = true;
                if (currentCanvas.isVisible() ^ currentCanvas.isVisible) {
                    currentCanvas.setVisible(true);
                }
            }
            else if (sourceCanvas.containsKey(currentCanvas.triggerKey)) {
                currentCanvas.isVisible = false;
                if (currentCanvas.isVisible() ^ currentCanvas.isVisible) {
                    currentCanvas.setVisible(false);
                    currentCanvas.dispose();
                }
            }
        }
    }
    
    static {
        MultiAlgorithmWindow.IMAGE_DIRECTORY = "images/";
    }
}
