import com.cim.common.*;
import java.awt.event.*;
import localization.*;
import java.applet.*;
import java.awt.*;
import com.cim.AIA.*;

public class RedBlackTreeAnimationWindow extends AnimationWindow implements ControlListener
{
    private static final long serialVersionUID = 4700934169171786038L;
    protected static final String BUILD_DATE = "28/05/99";
    protected static final String BUILD_BY = "T.Witham";
    protected static final String FRAME_TITLE;
    public static final int[] DEFAULT_DATA;
    public static final int[] SORTED_DATA;
    public static final int[] REVERSE_SORTED_DATA;
    protected int minimumNumberOfElements;
    protected int maximumNumberOfElements;
    protected int maxSize;
    protected int minSize;
    private boolean everStarted;
    private boolean isRunning;
    private int maxDuration;
    protected Dialog tuteDialog;
    protected RedBlackTreeCanvas tuteCanvas;
    protected HierarchyTree tuteTree;
    protected Panel panel;
    protected RedBlackTreeCanvas codeCanvas;
    protected Panel controlPanel;
    protected Panel speedPanel;
    protected Scrollbar speedBar;
    Button rotateLeft;
    Button rotateRight;
    Button updateTree;
    Button closeBtn;
    protected Dialog dialog;
    protected RedBlackTreeCanvas algorithmCanvas;
    protected HierarchyTree hierarchyTree;
    
    public String getAlgorithmName() {
        return "AIA: RedBlackTree";
    }
    
    public RedBlackTreeAnimationWindow(final AlgorithmCanvas canvas, final AlgorithmThread thread, final Applet applet) {
        super(canvas, thread, applet);
        this.minimumNumberOfElements = 1;
        this.maximumNumberOfElements = 20;
        this.maxSize = 99;
        this.minSize = 0;
        this.everStarted = false;
        this.isRunning = false;
        this.maxDuration = 6000;
        this.panel = new Panel();
        this.codeCanvas = new RedBlackTreeCanvas();
        this.controlPanel = new Panel();
        this.speedPanel = new Panel();
        this.rotateLeft = new Button(Messages.getString("RedBlackTreeAnimationWindow.10"));
        this.rotateRight = new Button(Messages.getString("RedBlackTreeAnimationWindow.11"));
        this.updateTree = new Button(Messages.getString("RedBlackTreeAnimationWindow.12"));
        this.closeBtn = new Button(Messages.getString("RedBlackTreeAnimationWindow.13"));
        this.buildDate = "28/05/99";
        this.buildBy = "T.Witham";
        this.frameTitle = RedBlackTreeAnimationWindow.FRAME_TITLE;
        this.selfTestMode.setEnabled(false);
        this.addControlButton(new StepButton(AnimationWindow.STEP_LABEL, AnimationWindow.STEP_INSTRUCTIONS, this, thread));
        this.addControlButton(new BackButton(AnimationWindow.BACK_LABEL, AnimationWindow.BACK_INSTRUCTIONS, this, thread));
        this.addControlButton(new RunButton(AnimationWindow.RUN_LABEL, AnimationWindow.RUN_INSTRUCTIONS, this, thread));
        this.addControlButton(new PauseButton(AnimationWindow.PAUSE_LABEL, AnimationWindow.PAUSE_INSTRUCTIONS, this, thread));
        final ResetButton reset = new ResetButton(AnimationWindow.RESET_LABEL, AnimationWindow.RESET_INSTRUCTIONS, this, thread);
        this.addControlButton(reset);
        final RestartButton restart = new RestartButton(Messages.getString("RedBlackTreeAnimationWindow.3"), Messages.getString("RedBlackTreeAnimationWindow.4"), this, thread);
        restart.setReinitialiseAlgorithm(false);
        restart.setClearAlgorithmState(false);
        restart.setStoreAlgorithmState(true);
        restart.setForceAlgorithmStore(true);
        this.addControlButton(restart);
        final PresetIntArrayDataSelection defaultData = new PresetIntArrayDataSelection(RedBlackTreeAnimationWindow.DEFAULT_LABEL, true, this, RedBlackTreeAnimationWindow.DEFAULT_DATA);
        defaultData.setReinitialiseAlgorithm(true);
        defaultData.setClearAlgorithmState(true);
        this.addDataSelection(defaultData);
        final PresetIntArrayDataSelection sortedData = new PresetIntArrayDataSelection(RedBlackTreeAnimationWindow.SORTED_LABEL, false, this, RedBlackTreeAnimationWindow.SORTED_DATA);
        sortedData.setReinitialiseAlgorithm(true);
        sortedData.setClearAlgorithmState(true);
        this.addDataSelection(sortedData);
        final PresetIntArrayDataSelection reverseData = new PresetIntArrayDataSelection(RedBlackTreeAnimationWindow.REVERSE_SORTED_LABEL, false, this, RedBlackTreeAnimationWindow.REVERSE_SORTED_DATA);
        reverseData.setReinitialiseAlgorithm(true);
        reverseData.setClearAlgorithmState(true);
        this.addDataSelection(reverseData);
        final RandomSameIntArrayDataSelection randomSameData = new RandomSameIntArrayDataSelection(RedBlackTreeAnimationWindow.SAME_LABEL, false, this, this.minimumNumberOfElements, this.maximumNumberOfElements, this.minSize, this.maxSize);
        randomSameData.setReinitialiseAlgorithm(true);
        randomSameData.setClearAlgorithmState(true);
        this.addDataSelection(randomSameData);
        final RandomIntArrayDataSelection randomData = new RandomIntArrayDataSelection(RedBlackTreeAnimationWindow.RANDOM_LABEL, false, this, this.minimumNumberOfElements, this.maximumNumberOfElements, this.minSize, this.maxSize);
        randomData.setReinitialiseAlgorithm(true);
        randomData.setClearAlgorithmState(true);
        this.addDataSelection(randomData);
        final UserSelectionIntArrayDataSelection userData = new UserSelectionIntArrayDataSelection(RedBlackTreeAnimationWindow.USER_SELECTION_LABEL + "...", false, this, RedBlackTreeAnimationWindow.USER_SELECTION_LABEL, this.minSize, this.maxSize, this.minimumNumberOfElements, this.maximumNumberOfElements);
        userData.setReinitialiseAlgorithm(true);
        userData.setClearAlgorithmState(true);
        this.addDataSelection(userData);
        this.addControlListener(this);
    }
    
    private void buildConstraints(final GridBagConstraints gbc, final int gx, final int gy, final int gw, final int gh, final int wx, final int wy) {
        gbc.gridx = gx;
        gbc.gridy = gy;
        gbc.gridwidth = gw;
        gbc.gridheight = gh;
        gbc.weightx = (double)wx;
        gbc.weighty = (double)wy;
    }
    
    public void controlBack(final ControlEvent e) {
    }
    
    public void controlOther(final ControlEvent e) {
    }
    
    public void controlPause(final ControlEvent e) {
        this.isRunning = false;
        this.everStarted = true;
    }
    
    public void controlReset(final ControlEvent e) {
        this.isRunning = false;
        this.everStarted = false;
    }
    
    public void controlRestart(final ControlEvent e) {
        this.isRunning = false;
        this.everStarted = false;
    }
    
    public void controlRun(final ControlEvent e) {
        this.isRunning = true;
        this.everStarted = false;
    }
    
    public void controlStep(final ControlEvent e) {
        this.everStarted = true;
    }
    
    protected void initialiseMenuBar() {
        this.menuBar = new MenuBar();
        this.setMenuBar(this.menuBar);
        this.initialiseAlgorithmMenuItem();
        this.initialiseModeSelectionMenuItem();
        this.initialiseDataSelectionMenuItem();
        this.initialiseViewMenuItem();
        this.initialiseTuteMenuItem();
        this.initialiseHelpMenuItem();
    }
    
    protected void initialiseTuteMenuItem() {
        final Menu tutorial = new Menu(Messages.getString("RedBlackTreeAnimationWindow.6"));
        final MenuItem menuItem;
        final MenuItem rotationTute = menuItem = new MenuItem(Messages.getString("RedBlackTreeAnimationWindow.7"));
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                RedBlackTreeAnimationWindow.this.showTute();
            }
        });
        tutorial.add(rotationTute);
        this.menuBar.add(tutorial);
    }
    
    protected void initialiseViewMenuItem() {
        final Menu display = new Menu(Messages.getString("RedBlackTreeAnimationWindow.8"));
        final MenuItem menuItem;
        final MenuItem viewAs234Tree = menuItem = new MenuItem(Messages.getString("RedBlackTreeAnimationWindow.1"));
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                RedBlackTreeAnimationWindow.this.showAs234Tree();
            }
        });
        display.add(viewAs234Tree);
        this.menuBar.add(display);
    }
    
    protected void showAs234Tree() {
        if ((RedBlackTree)this.getAlgorithm() != null) {
            if (this.dialog == null) {
                this.dialog = new Dialog(new Frame(), Messages.getString("RedBlackTreeAnimationWindow.25"), false);
                this.dialog.setSize(400, 400);
                this.algorithmCanvas = new RedBlackTreeCanvas();
                this.algorithmCanvas.setShowAs234Only(true);
                this.algorithmCanvas.setShowTute(false);
                final ScrollPane scrollPane = new ScrollPane();
                this.algorithmCanvas.setParent(scrollPane);
                scrollPane.add(this.algorithmCanvas);
                this.dialog.add(scrollPane);
                this.dialog.addWindowListener(new WindowListener() {
                    public void windowActivated(final WindowEvent e) {
                    }
                    
                    public void windowClosed(final WindowEvent e) {
                    }
                    
                    public void windowClosing(final WindowEvent e) {
                        RedBlackTreeAnimationWindow.this.dialog.setVisible(false);
                    }
                    
                    public void windowDeactivated(final WindowEvent e) {
                    }
                    
                    public void windowDeiconified(final WindowEvent e) {
                    }
                    
                    public void windowIconified(final WindowEvent e) {
                    }
                    
                    public void windowOpened(final WindowEvent e) {
                    }
                });
                this.thread.addRepaintListener(this.algorithmCanvas);
                this.thread.repaint();
            }
            this.dialog.setVisible(true);
        }
    }
    
    protected void showTute() {
        final RedBlackTree redBlackTree = (RedBlackTree)this.getAlgorithm();
        if (redBlackTree != null) {
            if (this.tuteDialog == null) {
                final AlgorithmThread t1 = this.thread;
                this.rotateLeft.addActionListener(new ActionListener() {
                    public void actionPerformed(final ActionEvent e) {
                        MessageDialog msg;
                        if (RedBlackTreeAnimationWindow.this.isRunning) {
                            msg = new MessageDialog(Messages.getString("RedBlackTreeAnimationWindow.14"), true, true);
                            msg.setTitle(Messages.getString("RedBlackTreeAnimationWindow.15"));
                            msg.setVisible(true);
                            return;
                        }
                        if (RedBlackTreeAnimationWindow.this.everStarted) {
                            redBlackTree.leftRotateRequest();
                            return;
                        }
                        msg = new MessageDialog(Messages.getString("RedBlackTreeAnimationWindow.16"), true, true);
                        msg.setTitle(Messages.getString("RedBlackTreeAnimationWindow.17"));
                        msg.setVisible(true);
                    }
                });
                this.rotateRight.addActionListener(new ActionListener() {
                    public void actionPerformed(final ActionEvent e) {
                        MessageDialog msg;
                        if (RedBlackTreeAnimationWindow.this.isRunning) {
                            msg = new MessageDialog(Messages.getString("RedBlackTreeAnimationWindow.18"), true, true);
                            msg.setTitle(Messages.getString("RedBlackTreeAnimationWindow.19"));
                            msg.setVisible(true);
                            return;
                        }
                        if (RedBlackTreeAnimationWindow.this.everStarted) {
                            redBlackTree.rightRotateRequest();
                            return;
                        }
                        msg = new MessageDialog(Messages.getString("RedBlackTreeAnimationWindow.20"), true, true);
                        msg.setTitle(Messages.getString("RedBlackTreeAnimationWindow.21"));
                        msg.setVisible(true);
                    }
                });
                this.updateTree.addActionListener(new ActionListener() {
                    public void actionPerformed(final ActionEvent e) {
                        redBlackTree.updateTuteTree();
                        t1.repaint();
                    }
                });
                this.closeBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(final ActionEvent e) {
                        RedBlackTreeAnimationWindow.this.tuteDialog.setVisible(false);
                    }
                });
                this.controlPanel.add(this.rotateLeft);
                this.controlPanel.add(this.rotateRight);
                this.controlPanel.add(this.updateTree);
                this.controlPanel.add(this.closeBtn);
                this.controlPanel.setBackground(Color.lightGray);
                this.tuteDialog = new Dialog(new Frame(), Messages.getString("RedBlackTreeAnimationWindow.22"), false);
                this.tuteDialog.setSize(400, 600);
                this.tuteCanvas = new RedBlackTreeCanvas();
                this.tuteCanvas.setShowAs234Only(false);
                this.tuteCanvas.setShowTute(true);
                final ScrollPane scrollPane = new ScrollPane();
                this.tuteCanvas.setParent(scrollPane);
                this.tuteCanvas.setSize(400, 400);
                scrollPane.add(this.tuteCanvas);
                this.codeCanvas.setShowAs234Only(false);
                this.codeCanvas.setShowTute(false);
                this.codeCanvas.setShowCode(true);
                final ScrollPane codePane = new ScrollPane();
                this.codeCanvas.setParent(codePane);
                this.codeCanvas.setSize(400, 400);
                codePane.add(this.codeCanvas);
                this.speedPanel.setLayout(new BorderLayout());
                this.speedPanel.setBackground(Color.lightGray);
                final Label slow = new Label(Messages.getString("RedBlackTreeAnimationWindow.23"), 1);
                this.speedPanel.add(slow, "West");
                this.speedBar = new Scrollbar(0, this.maxDuration / 2, 1, 1, this.maxDuration);
                this.speedBar.addAdjustmentListener(new AdjustmentListener() {
                    public void adjustmentValueChanged(final AdjustmentEvent e) {
                        redBlackTree.setAnimDuration(RedBlackTreeAnimationWindow.this.maxDuration - RedBlackTreeAnimationWindow.this.speedBar.getValue());
                        RedBlackTreeAnimationWindow.this.tuteCanvas.setTweenSpeed(RedBlackTreeAnimationWindow.this.maxDuration - RedBlackTreeAnimationWindow.this.speedBar.getValue());
                    }
                });
                this.speedPanel.add(this.speedBar, "Center");
                final Label fast = new Label(Messages.getString("RedBlackTreeAnimationWindow.24"), 1);
                this.speedPanel.add(fast, "East");
                final GridBagLayout gbLayout = new GridBagLayout();
                final GridBagConstraints constraints = new GridBagConstraints();
                constraints.fill = 1;
                this.panel.setLayout(gbLayout);
                this.buildConstraints(constraints, 0, 0, 1, 1, 100, 9);
                gbLayout.setConstraints(this.controlPanel, constraints);
                this.panel.add(this.controlPanel);
                this.buildConstraints(constraints, 0, 1, 1, 1, 100, 60);
                gbLayout.setConstraints(scrollPane, constraints);
                this.panel.add(scrollPane);
                this.buildConstraints(constraints, 0, 2, 1, 1, 100, 30);
                gbLayout.setConstraints(codePane, constraints);
                this.panel.add(codePane);
                this.buildConstraints(constraints, 0, 3, 1, 1, 100, 1);
                gbLayout.setConstraints(this.speedPanel, constraints);
                this.panel.add(this.speedPanel);
                this.tuteDialog.add(this.panel);
                this.tuteDialog.addWindowListener(new WindowListener() {
                    public void windowActivated(final WindowEvent e) {
                    }
                    
                    public void windowClosed(final WindowEvent e) {
                    }
                    
                    public void windowClosing(final WindowEvent e) {
                        RedBlackTreeAnimationWindow.this.tuteDialog.setVisible(false);
                    }
                    
                    public void windowDeactivated(final WindowEvent e) {
                    }
                    
                    public void windowDeiconified(final WindowEvent e) {
                    }
                    
                    public void windowIconified(final WindowEvent e) {
                    }
                    
                    public void windowOpened(final WindowEvent e) {
                    }
                });
                this.thread.addRepaintListener(this.codeCanvas);
                this.thread.addRepaintListener(this.tuteCanvas);
                this.thread.repaint();
            }
            redBlackTree.buildTuteTree();
            this.tuteDialog.setVisible(true);
            this.thread.repaint();
        }
    }
    
    static {
        FRAME_TITLE = Messages.getString("RedBlackTreeAnimationWindow.2");
        DEFAULT_DATA = new int[] { 75, 5, 70, 10, 65, 15, 60, 20, 55, 25, 50, 30, 45, 35, 40 };
        SORTED_DATA = new int[] { 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75 };
        REVERSE_SORTED_DATA = new int[] { 75, 70, 65, 60, 55, 50, 45, 40, 35, 30, 25, 20, 15, 10, 5 };
    }
}
