// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SplayTreeAnimationWindow.java

import com.cim.AIA.*;
import java.applet.Applet;
import java.awt.Checkbox;
import java.awt.Panel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import localization.Messages;

public class SplayTreeAnimationWindow extends AnimationWindow
    implements ItemListener
{

    public String getAlgorithmName()
    {
        return "AIA: SplayTree";
    }

    public SplayTreeAnimationWindow(AlgorithmCanvas canvas, AlgorithmThread thread, Applet applet)
    {
        super(canvas, thread, applet);
        buildDate = "Build Date";
        buildBy = "Your Initial.Your Surname";
        frameTitle = FRAME_TITLE;
        addControlButton(new StepButton(AnimationWindow.STEP_LABEL, AnimationWindow.STEP_INSTRUCTIONS, this, thread));
        addControlButton(new BackButton(AnimationWindow.BACK_LABEL, AnimationWindow.BACK_INSTRUCTIONS, this, thread));
        addControlButton(new RunButton(AnimationWindow.RUN_LABEL, AnimationWindow.RUN_INSTRUCTIONS, this, thread));
        addControlButton(new PauseButton(AnimationWindow.PAUSE_LABEL, AnimationWindow.PAUSE_INSTRUCTIONS, this, thread));
        addControlButton(new ResetButton(AnimationWindow.RESET_LABEL, AnimationWindow.RESET_INSTRUCTIONS, this, thread));
        RestartButton restartButton = new RestartButton(Messages.getString("SplayTreeAnimationWindow.4"), Messages.getString("SplayTreeAnimationWindow.5"), this, thread);
        restartButton.setReinitialiseAlgorithm(false);
        restartButton.setClearAlgorithmState(false);
        restartButton.setStoreAlgorithmState(true);
        restartButton.setForceAlgorithmStore(true);
        addControlButton(restartButton);
        Checkbox animateCheckbox = new Checkbox(Messages.getString("SplayTreeAnimationWindow.6"), true);
        animateCheckbox.addItemListener(this);
        controlPanel.add(animateCheckbox);
        PresetIntArrayDataSelection defaultData = new PresetIntArrayDataSelection(DEFAULT_LABEL, true, this, DEFAULT_DATA);
        defaultData.setReinitialiseAlgorithm(true);
        defaultData.setClearAlgorithmState(true);
        addDataSelection(defaultData);
        PresetIntArrayDataSelection sortedData = new PresetIntArrayDataSelection(SORTED_LABEL, false, this, SORTED_DATA);
        defaultData.setReinitialiseAlgorithm(true);
        defaultData.setClearAlgorithmState(true);
        addDataSelection(sortedData);
        RandomIntArrayDataSelection randomData = new RandomIntArrayDataSelection(RANDOM_LABEL, false, this, 5, 20, 3, 99);
        randomData.setReinitialiseAlgorithm(true);
        randomData.setClearAlgorithmState(true);
        addDataSelection(randomData);
        UserSelectionIntArrayDataSelection userData = new UserSelectionIntArrayDataSelection((new StringBuilder()).append(USER_SELECTION_LABEL).append("...").toString(), false, this, USER_SELECTION_LABEL, 3, 99, 5, 20);
        userData.setReinitialiseAlgorithm(true);
        userData.setClearAlgorithmState(true);
        addDataSelection(userData);
        addControlListener((ControlListener)((SplayTreeThread)thread).getAlgorithm());
    }

    public void itemStateChanged(ItemEvent e)
    {
        if(e.getStateChange() == 1)
            ((SplayTreeCanvas)canvas).setIsShowTween(true);
        else
        if(e.getStateChange() == 2)
            ((SplayTreeCanvas)canvas).setIsShowTween(false);
    }

    protected static final String BUILD_DATE = "Build Date";
    protected static final String BUILD_BY = "Your Initial.Your Surname";
    protected static final String FRAME_TITLE = Messages.getString("SplayTreeAnimationWindow.2");
    public static final int DEFAULT_DATA[] = {
        4, 7, 15, 1, 30, 10, 1, 2, 3
    };
    public static final int SORTED_DATA[] = {
        4, 7, 8, 10, 13, 15, 16, 17, 18, 20, 
        21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 
        31, 32, 1, 5, 6, 9, 14, 19, 3
    };
    public static final String SORTED_LABAL = Messages.getString("SplayTreeAnimationWindow.3");
    private static final int MINIMUM_NUMBER_OF_ELEMENTS = 5;
    private static final int MAXIMUM_NUMBER_OF_ELEMENTS = 20;
    private static final int MIN_SIZE = 3;
    private static final int MAX_SIZE = 99;

}
