// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SkipListAnimationWindow.java

import com.cim.AIA.*;
import java.applet.Applet;
import localization.Messages;

public class SkipListAnimationWindow extends AnimationWindow
{

    public String getAlgorithmName()
    {
        return "AIA: SkipList";
    }

    public SkipListAnimationWindow(AlgorithmCanvas algorithmCanvas, AlgorithmThread algorithmThread, Applet algorithmApplet)
    {
        super(algorithmCanvas, algorithmThread, algorithmApplet);
        minimumNumberOfElements = 1;
        maximumNumberOfElements = 20;
        maxSize = 99;
        minSize = 0;
        buildDate = "Build Date";
        buildBy = "Your Initial.Your Surname";
        frameTitle = FRAME_TITLE;
        SkipList _tmp = (SkipList)((SkipListThread)algorithmThread).getAlgorithm();
        minNoOfElements = 5;
        SkipList _tmp1 = (SkipList)((SkipListThread)algorithmThread).getAlgorithm();
        maxNoOfElements = 31;
        addControlButton(new StepButton(AnimationWindow.STEP_LABEL, AnimationWindow.STEP_INSTRUCTIONS, this, algorithmThread));
        addControlButton(new BackButton(AnimationWindow.BACK_LABEL, AnimationWindow.BACK_INSTRUCTIONS, this, algorithmThread));
        addControlButton(new RunButton(AnimationWindow.RUN_LABEL, AnimationWindow.RUN_INSTRUCTIONS, this, algorithmThread));
        addControlButton(new PauseButton(AnimationWindow.PAUSE_LABEL, AnimationWindow.PAUSE_INSTRUCTIONS, this, algorithmThread));
        addControlButton(new ResetButton(AnimationWindow.RESET_LABEL, AnimationWindow.RESET_INSTRUCTIONS, this, algorithmThread));
        RestartButton restartButton = new RestartButton(Messages.getString("SkipListAnimationWindow.3"), Messages.getString("SkipListAnimationWindow.4"), this, algorithmThread);
        restartButton.setReinitialiseAlgorithm(false);
        restartButton.setClearAlgorithmState(false);
        restartButton.setStoreAlgorithmState(true);
        restartButton.setForceAlgorithmStore(true);
        addControlButton(restartButton);
        addControlListener((ControlListener)((SkipListThread)algorithmThread).getAlgorithm());
        PresetIntArrayDataSelection defaultData = new PresetIntArrayDataSelection(DEFAULT_LABEL, true, this, DEFAULT_DATA);
        defaultData.setReinitialiseAlgorithm(false);
        defaultData.setClearAlgorithmState(true);
        addDataSelection(defaultData);
        RandomIntArrayDataSelection randomData = new RandomIntArrayDataSelection(RANDOM_LABEL, false, this, minNoOfElements, maxNoOfElements, 1, 99);
        randomData.setReinitialiseAlgorithm(false);
        randomData.setClearAlgorithmState(true);
        addDataSelection(randomData);
        PresetIntArrayDataSelection sortedData = new PresetIntArrayDataSelection(SORTED_LABEL, false, this, SORTED_DATA);
        sortedData.setReinitialiseAlgorithm(false);
        sortedData.setClearAlgorithmState(true);
        addDataSelection(sortedData);
        UserSelectionIntArrayDataSelection userData = new UserSelectionIntArrayDataSelection((new StringBuilder()).append(USER_SELECTION_LABEL).append("...").toString(), false, this, USER_SELECTION_LABEL, 1, 99, minNoOfElements, maxNoOfElements);
        userData.setReinitialiseAlgorithm(false);
        userData.setClearAlgorithmState(true);
        addDataSelection(userData);
    }

    public void printHello()
    {
    }

    protected static final String BUILD_DATE = "Build Date";
    protected static final String BUILD_BY = "Your Initial.Your Surname";
    protected static final String FRAME_TITLE = Messages.getString("SkipListAnimationWindow.2");
    protected static final int NUMBER_OF_ELEMENTS = 10;
    private static int minNoOfElements;
    private static int maxNoOfElements;
    protected static final int MAX_SIZE = 99;
    protected static final int MIN_SIZE = 1;
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

}
