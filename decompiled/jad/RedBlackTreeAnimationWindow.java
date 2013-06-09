// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RedBlackTreeAnimationWindow.java

import com.cim.AIA.*;
import com.cim.common.MessageDialog;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import localization.Messages;

public class RedBlackTreeAnimationWindow extends AnimationWindow
    implements ControlListener
{

    public String getAlgorithmName()
    {
        return "AIA: RedBlackTree";
    }

    public RedBlackTreeAnimationWindow(AlgorithmCanvas canvas, AlgorithmThread thread, Applet applet)
    {
        super(canvas, thread, applet);
        minimumNumberOfElements = 1;
        maximumNumberOfElements = 20;
        maxSize = 99;
        minSize = 0;
        everStarted = false;
        isRunning = false;
        maxDuration = 6000;
        panel = new Panel();
        codeCanvas = new RedBlackTreeCanvas();
        controlPanel = new Panel();
        speedPanel = new Panel();
        rotateLeft = new Button(Messages.getString("RedBlackTreeAnimationWindow.10"));
        rotateRight = new Button(Messages.getString("RedBlackTreeAnimationWindow.11"));
        updateTree = new Button(Messages.getString("RedBlackTreeAnimationWindow.12"));
        closeBtn = new Button(Messages.getString("RedBlackTreeAnimationWindow.13"));
        buildDate = "28/05/99";
        buildBy = "T.Witham";
        frameTitle = FRAME_TITLE;
        selfTestMode.setEnabled(false);
        addControlButton(new StepButton(AnimationWindow.STEP_LABEL, AnimationWindow.STEP_INSTRUCTIONS, this, thread));
        addControlButton(new BackButton(AnimationWindow.BACK_LABEL, AnimationWindow.BACK_INSTRUCTIONS, this, thread));
        addControlButton(new RunButton(AnimationWindow.RUN_LABEL, AnimationWindow.RUN_INSTRUCTIONS, this, thread));
        addControlButton(new PauseButton(AnimationWindow.PAUSE_LABEL, AnimationWindow.PAUSE_INSTRUCTIONS, this, thread));
        ResetButton reset = new ResetButton(AnimationWindow.RESET_LABEL, AnimationWindow.RESET_INSTRUCTIONS, this, thread);
        addControlButton(reset);
        RestartButton restart = new RestartButton(Messages.getString("RedBlackTreeAnimationWindow.3"), Messages.getString("RedBlackTreeAnimationWindow.4"), this, thread);
        restart.setReinitialiseAlgorithm(false);
        restart.setClearAlgorithmState(false);
        restart.setStoreAlgorithmState(true);
        restart.setForceAlgorithmStore(true);
        addControlButton(restart);
        PresetIntArrayDataSelection defaultData = new PresetIntArrayDataSelection(DEFAULT_LABEL, true, this, DEFAULT_DATA);
        defaultData.setReinitialiseAlgorithm(true);
        defaultData.setClearAlgorithmState(true);
        addDataSelection(defaultData);
        PresetIntArrayDataSelection sortedData = new PresetIntArrayDataSelection(SORTED_LABEL, false, this, SORTED_DATA);
        sortedData.setReinitialiseAlgorithm(true);
        sortedData.setClearAlgorithmState(true);
        addDataSelection(sortedData);
        PresetIntArrayDataSelection reverseData = new PresetIntArrayDataSelection(REVERSE_SORTED_LABEL, false, this, REVERSE_SORTED_DATA);
        reverseData.setReinitialiseAlgorithm(true);
        reverseData.setClearAlgorithmState(true);
        addDataSelection(reverseData);
        RandomSameIntArrayDataSelection randomSameData = new RandomSameIntArrayDataSelection(SAME_LABEL, false, this, minimumNumberOfElements, maximumNumberOfElements, minSize, maxSize);
        randomSameData.setReinitialiseAlgorithm(true);
        randomSameData.setClearAlgorithmState(true);
        addDataSelection(randomSameData);
        RandomIntArrayDataSelection randomData = new RandomIntArrayDataSelection(RANDOM_LABEL, false, this, minimumNumberOfElements, maximumNumberOfElements, minSize, maxSize);
        randomData.setReinitialiseAlgorithm(true);
        randomData.setClearAlgorithmState(true);
        addDataSelection(randomData);
        UserSelectionIntArrayDataSelection userData = new UserSelectionIntArrayDataSelection((new StringBuilder()).append(USER_SELECTION_LABEL).append("...").toString(), false, this, USER_SELECTION_LABEL, minSize, maxSize, minimumNumberOfElements, maximumNumberOfElements);
        userData.setReinitialiseAlgorithm(true);
        userData.setClearAlgorithmState(true);
        addDataSelection(userData);
        addControlListener(this);
    }

    private void buildConstraints(GridBagConstraints gbc, int gx, int gy, int gw, int gh, int wx, int wy)
    {
        gbc.gridx = gx;
        gbc.gridy = gy;
        gbc.gridwidth = gw;
        gbc.gridheight = gh;
        gbc.weightx = wx;
        gbc.weighty = wy;
    }

    public void controlBack(ControlEvent controlevent)
    {
    }

    public void controlOther(ControlEvent controlevent)
    {
    }

    public void controlPause(ControlEvent e)
    {
        isRunning = false;
        everStarted = true;
    }

    public void controlReset(ControlEvent e)
    {
        isRunning = false;
        everStarted = false;
    }

    public void controlRestart(ControlEvent e)
    {
        isRunning = false;
        everStarted = false;
    }

    public void controlRun(ControlEvent e)
    {
        isRunning = true;
        everStarted = false;
    }

    public void controlStep(ControlEvent e)
    {
        everStarted = true;
    }

    protected void initialiseMenuBar()
    {
        menuBar = new MenuBar();
        setMenuBar(menuBar);
        initialiseAlgorithmMenuItem();
        initialiseModeSelectionMenuItem();
        initialiseDataSelectionMenuItem();
        initialiseViewMenuItem();
        initialiseTuteMenuItem();
        initialiseHelpMenuItem();
    }

    protected void initialiseTuteMenuItem()
    {
        Menu tutorial = new Menu(Messages.getString("RedBlackTreeAnimationWindow.6"));
        MenuItem rotationTute = new MenuItem(Messages.getString("RedBlackTreeAnimationWindow.7"));
        rotationTute.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                showTute();
            }

            final RedBlackTreeAnimationWindow this$0;

            
            {
                this$0 = RedBlackTreeAnimationWindow.this;
                super();
            }
        });
        tutorial.add(rotationTute);
        menuBar.add(tutorial);
    }

    protected void initialiseViewMenuItem()
    {
        Menu display = new Menu(Messages.getString("RedBlackTreeAnimationWindow.8"));
        MenuItem viewAs234Tree = new MenuItem(Messages.getString("RedBlackTreeAnimationWindow.1"));
        viewAs234Tree.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                showAs234Tree();
            }

            final RedBlackTreeAnimationWindow this$0;

            
            {
                this$0 = RedBlackTreeAnimationWindow.this;
                super();
            }
        });
        display.add(viewAs234Tree);
        menuBar.add(display);
    }

    protected void showAs234Tree()
    {
        RedBlackTree redBlackTree = (RedBlackTree)getAlgorithm();
        if(redBlackTree != null)
        {
            if(dialog == null)
            {
                dialog = new Dialog(new Frame(), Messages.getString("RedBlackTreeAnimationWindow.25"), false);
                dialog.setSize(400, 400);
                algorithmCanvas = new RedBlackTreeCanvas();
                algorithmCanvas.setShowAs234Only(true);
                algorithmCanvas.setShowTute(false);
                ScrollPane scrollPane = new ScrollPane();
                algorithmCanvas.setParent(scrollPane);
                scrollPane.add(algorithmCanvas);
                dialog.add(scrollPane);
                dialog.addWindowListener(new WindowListener() {

                    public void windowActivated(WindowEvent windowevent)
                    {
                    }

                    public void windowClosed(WindowEvent windowevent)
                    {
                    }

                    public void windowClosing(WindowEvent e)
                    {
                        dialog.setVisible(false);
                    }

                    public void windowDeactivated(WindowEvent windowevent)
                    {
                    }

                    public void windowDeiconified(WindowEvent windowevent)
                    {
                    }

                    public void windowIconified(WindowEvent windowevent)
                    {
                    }

                    public void windowOpened(WindowEvent windowevent)
                    {
                    }

                    final RedBlackTreeAnimationWindow this$0;

            
            {
                this$0 = RedBlackTreeAnimationWindow.this;
                super();
            }
                });
                thread.addRepaintListener(algorithmCanvas);
                thread.repaint();
            }
            dialog.setVisible(true);
        }
    }

    protected void showTute()
    {
        final RedBlackTree redBlackTree = (RedBlackTree)getAlgorithm();
        if(redBlackTree != null)
        {
            if(tuteDialog == null)
            {
                final AlgorithmThread t1 = thread;
                rotateLeft.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        if(isRunning)
                        {
                            MessageDialog msg = new MessageDialog(Messages.getString("RedBlackTreeAnimationWindow.14"), true, true);
                            msg.setTitle(Messages.getString("RedBlackTreeAnimationWindow.15"));
                            msg.setVisible(true);
                            return;
                        }
                        if(everStarted)
                        {
                            redBlackTree.leftRotateRequest();
                        } else
                        {
                            MessageDialog msg = new MessageDialog(Messages.getString("RedBlackTreeAnimationWindow.16"), true, true);
                            msg.setTitle(Messages.getString("RedBlackTreeAnimationWindow.17"));
                            msg.setVisible(true);
                            return;
                        }
                    }

                    final RedBlackTree val$redBlackTree;
                    final RedBlackTreeAnimationWindow this$0;

            
            {
                this$0 = RedBlackTreeAnimationWindow.this;
                redBlackTree = redblacktree;
                super();
            }
                });
                rotateRight.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        if(isRunning)
                        {
                            MessageDialog msg = new MessageDialog(Messages.getString("RedBlackTreeAnimationWindow.18"), true, true);
                            msg.setTitle(Messages.getString("RedBlackTreeAnimationWindow.19"));
                            msg.setVisible(true);
                            return;
                        }
                        if(everStarted)
                        {
                            redBlackTree.rightRotateRequest();
                        } else
                        {
                            MessageDialog msg = new MessageDialog(Messages.getString("RedBlackTreeAnimationWindow.20"), true, true);
                            msg.setTitle(Messages.getString("RedBlackTreeAnimationWindow.21"));
                            msg.setVisible(true);
                            return;
                        }
                    }

                    final RedBlackTree val$redBlackTree;
                    final RedBlackTreeAnimationWindow this$0;

            
            {
                this$0 = RedBlackTreeAnimationWindow.this;
                redBlackTree = redblacktree;
                super();
            }
                });
                updateTree.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        redBlackTree.updateTuteTree();
                        t1.repaint();
                    }

                    final RedBlackTree val$redBlackTree;
                    final AlgorithmThread val$t1;
                    final RedBlackTreeAnimationWindow this$0;

            
            {
                this$0 = RedBlackTreeAnimationWindow.this;
                redBlackTree = redblacktree;
                t1 = algorithmthread;
                super();
            }
                });
                closeBtn.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        tuteDialog.setVisible(false);
                    }

                    final RedBlackTreeAnimationWindow this$0;

            
            {
                this$0 = RedBlackTreeAnimationWindow.this;
                super();
            }
                });
                controlPanel.add(rotateLeft);
                controlPanel.add(rotateRight);
                controlPanel.add(updateTree);
                controlPanel.add(closeBtn);
                controlPanel.setBackground(Color.lightGray);
                tuteDialog = new Dialog(new Frame(), Messages.getString("RedBlackTreeAnimationWindow.22"), false);
                tuteDialog.setSize(400, 600);
                tuteCanvas = new RedBlackTreeCanvas();
                tuteCanvas.setShowAs234Only(false);
                tuteCanvas.setShowTute(true);
                ScrollPane scrollPane = new ScrollPane();
                tuteCanvas.setParent(scrollPane);
                tuteCanvas.setSize(400, 400);
                scrollPane.add(tuteCanvas);
                codeCanvas.setShowAs234Only(false);
                codeCanvas.setShowTute(false);
                codeCanvas.setShowCode(true);
                ScrollPane codePane = new ScrollPane();
                codeCanvas.setParent(codePane);
                codeCanvas.setSize(400, 400);
                codePane.add(codeCanvas);
                speedPanel.setLayout(new BorderLayout());
                speedPanel.setBackground(Color.lightGray);
                Label slow = new Label(Messages.getString("RedBlackTreeAnimationWindow.23"), 1);
                speedPanel.add(slow, "West");
                speedBar = new Scrollbar(0, maxDuration / 2, 1, 1, maxDuration);
                speedBar.addAdjustmentListener(new AdjustmentListener() {

                    public void adjustmentValueChanged(AdjustmentEvent e)
                    {
                        redBlackTree.setAnimDuration(maxDuration - speedBar.getValue());
                        tuteCanvas.setTweenSpeed(maxDuration - speedBar.getValue());
                    }

                    final RedBlackTree val$redBlackTree;
                    final RedBlackTreeAnimationWindow this$0;

            
            {
                this$0 = RedBlackTreeAnimationWindow.this;
                redBlackTree = redblacktree;
                super();
            }
                });
                speedPanel.add(speedBar, "Center");
                Label fast = new Label(Messages.getString("RedBlackTreeAnimationWindow.24"), 1);
                speedPanel.add(fast, "East");
                GridBagLayout gbLayout = new GridBagLayout();
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.fill = 1;
                panel.setLayout(gbLayout);
                buildConstraints(constraints, 0, 0, 1, 1, 100, 9);
                gbLayout.setConstraints(controlPanel, constraints);
                panel.add(controlPanel);
                buildConstraints(constraints, 0, 1, 1, 1, 100, 60);
                gbLayout.setConstraints(scrollPane, constraints);
                panel.add(scrollPane);
                buildConstraints(constraints, 0, 2, 1, 1, 100, 30);
                gbLayout.setConstraints(codePane, constraints);
                panel.add(codePane);
                buildConstraints(constraints, 0, 3, 1, 1, 100, 1);
                gbLayout.setConstraints(speedPanel, constraints);
                panel.add(speedPanel);
                tuteDialog.add(panel);
                tuteDialog.addWindowListener(new WindowListener() {

                    public void windowActivated(WindowEvent windowevent)
                    {
                    }

                    public void windowClosed(WindowEvent windowevent)
                    {
                    }

                    public void windowClosing(WindowEvent e)
                    {
                        tuteDialog.setVisible(false);
                    }

                    public void windowDeactivated(WindowEvent windowevent)
                    {
                    }

                    public void windowDeiconified(WindowEvent windowevent)
                    {
                    }

                    public void windowIconified(WindowEvent windowevent)
                    {
                    }

                    public void windowOpened(WindowEvent windowevent)
                    {
                    }

                    final RedBlackTreeAnimationWindow this$0;

            
            {
                this$0 = RedBlackTreeAnimationWindow.this;
                super();
            }
                });
                thread.addRepaintListener(codeCanvas);
                thread.addRepaintListener(tuteCanvas);
                thread.repaint();
            }
            redBlackTree.buildTuteTree();
            tuteDialog.setVisible(true);
            thread.repaint();
        }
    }

    private static final long serialVersionUID = 0x413d12b861faf936L;
    protected static final String BUILD_DATE = "28/05/99";
    protected static final String BUILD_BY = "T.Witham";
    protected static final String FRAME_TITLE = Messages.getString("RedBlackTreeAnimationWindow.2");
    public static final int DEFAULT_DATA[] = {
        75, 5, 70, 10, 65, 15, 60, 20, 55, 25, 
        50, 30, 45, 35, 40
    };
    public static final int SORTED_DATA[] = {
        5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 
        55, 60, 65, 70, 75
    };
    public static final int REVERSE_SORTED_DATA[] = {
        75, 70, 65, 60, 55, 50, 45, 40, 35, 30, 
        25, 20, 15, 10, 5
    };
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




}
