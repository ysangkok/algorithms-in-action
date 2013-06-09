// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Tree234AnimationWindow.java

import com.cim.AIA.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import localization.Messages;

public class Tree234AnimationWindow extends AnimationWindow
{

    public String getAlgorithmName()
    {
        return "AIA: 234 Tree";
    }

    public Tree234AnimationWindow(AlgorithmCanvas canvas, AlgorithmThread thread, Applet applet)
    {
        super(canvas, thread, applet);
        minimumNumberOfElements = 1;
        maximumNumberOfElements = 20;
        maxSize = 99;
        minSize = 0;
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
        RestartButton restart = new RestartButton(Messages.getString("Tree234AnimationWindow.3"), Messages.getString("Tree234AnimationWindow.4"), this, thread);
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
        addControlListener((ControlListener)((Tree234Thread)thread).getAlgorithm());
    }

    protected void initialiseMenuBar()
    {
        menuBar = new MenuBar();
        setMenuBar(menuBar);
        initialiseAlgorithmMenuItem();
        initialiseModeSelectionMenuItem();
        initialiseDataSelectionMenuItem();
        initialiseViewMenuItem();
        initialiseHelpMenuItem();
    }

    protected void initialiseViewMenuItem()
    {
        Menu display = new Menu(Messages.getString("Tree234AnimationWindow.6"));
        MenuItem viewAs234Tree = new MenuItem(Messages.getString("Tree234AnimationWindow.7"));
        viewAs234Tree.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                showAs234Tree();
            }

            final Tree234AnimationWindow this$0;

            
            {
                this$0 = Tree234AnimationWindow.this;
                super();
            }
        });
        display.add(viewAs234Tree);
        menuBar.add(display);
    }

    protected void showAs234Tree()
    {
        Tree234Tree redBlackTree = (Tree234Tree)getAlgorithm();
        if(redBlackTree != null)
        {
            if(dialog == null)
            {
                dialog = new Dialog(new Frame(), Messages.getString("Tree234AnimationWindow.8"), false);
                dialog.setSize(400, 400);
                algorithmCanvas = new Tree234Canvas();
                algorithmCanvas.setShowAs234Only(false);
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

                    final Tree234AnimationWindow this$0;

            
            {
                this$0 = Tree234AnimationWindow.this;
                super();
            }
                });
                thread.addRepaintListener(algorithmCanvas);
                thread.repaint();
            }
            dialog.setVisible(true);
        }
    }

    protected static final String BUILD_DATE = "28/05/99";
    protected static final String BUILD_BY = "T.Witham";
    protected static final String FRAME_TITLE = Messages.getString("Tree234AnimationWindow.2");
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
    protected Dialog dialog;
    protected Tree234Canvas algorithmCanvas;
    protected HierarchyTree hierarchyTree;

}
