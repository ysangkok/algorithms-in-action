// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DigitalSearchTreeAnimationWindow.java

import com.cim.AIA.*;
import java.applet.Applet;
import localization.Messages;

public class DigitalSearchTreeAnimationWindow extends AnimationWindow
{

    public String getAlgorithmName()
    {
        return "AIA: DigitalSearchTree";
    }

    public DigitalSearchTreeAnimationWindow(AlgorithmCanvas algorithmCanvas, AlgorithmThread algorithmThread, Applet applet)
    {
        super(algorithmCanvas, algorithmThread, applet);
        buildDate = BUILD_DATE;
        buildBy = BUILD_BY;
        frameTitle = FRAME_TITLE;
        PresetIntArrayDataSelection defaultData = new PresetIntArrayDataSelection(DEFAULT_LABEL, true, this, DEFAULT_DATA);
        defaultData.setReinitialiseAlgorithm(false);
        defaultData.setClearAlgorithmState(true);
        addDataSelection(defaultData);
        RandomIntArrayDataSelection randomData = new RandomIntArrayDataSelection(RANDOM_LABEL, false, this, 5, 31, 1, 99);
        randomData.setReinitialiseAlgorithm(false);
        randomData.setClearAlgorithmState(true);
        addDataSelection(randomData);
        PresetIntArrayDataSelection sortedData = new PresetIntArrayDataSelection(SORTED_LABEL, false, this, SORTED_DATA);
        sortedData.setReinitialiseAlgorithm(false);
        sortedData.setClearAlgorithmState(true);
        addDataSelection(sortedData);
        PresetIntArrayDataSelection skewedData = new PresetIntArrayDataSelection(SKEWED_LABEL, false, this, SKEWED_DATA);
        skewedData.setReinitialiseAlgorithm(false);
        skewedData.setClearAlgorithmState(true);
        addDataSelection(skewedData);
        PresetIntArrayDataSelection exponentialData = new PresetIntArrayDataSelection(EXPONENTIAL_LABEL, false, this, EXPONENTIAL_DATA);
        exponentialData.setReinitialiseAlgorithm(false);
        exponentialData.setClearAlgorithmState(true);
        addDataSelection(exponentialData);
        UserSelectionIntArrayDataSelection userData = new UserSelectionIntArrayDataSelection((new StringBuilder()).append(USER_SELECTION_LABEL).append("...").toString(), false, this, USER_SELECTION_LABEL, 1, 99, 5, 31);
        userData.setReinitialiseAlgorithm(false);
        userData.setClearAlgorithmState(true);
        addDataSelection(userData);
        addControlButton(new StepButton(AnimationWindow.STEP_LABEL, AnimationWindow.STEP_INSTRUCTIONS, this, algorithmThread));
        addControlButton(new BackButton(AnimationWindow.BACK_LABEL, AnimationWindow.BACK_INSTRUCTIONS, this, algorithmThread));
        addControlButton(new RunButton(AnimationWindow.RUN_LABEL, AnimationWindow.RUN_INSTRUCTIONS, this, algorithmThread));
        addControlButton(new PauseButton(AnimationWindow.PAUSE_LABEL, AnimationWindow.PAUSE_INSTRUCTIONS, this, algorithmThread));
        addControlButton(new ResetButton(AnimationWindow.RESET_LABEL, AnimationWindow.RESET_INSTRUCTIONS, this, algorithmThread));
        RestartButton restartButton = new RestartButton(Messages.getString("DigitalSearchTreeAnimationWindow.6"), Messages.getString("DigitalSearchTreeAnimationWindow.7"), this, algorithmThread);
        restartButton.setReinitialiseAlgorithm(false);
        restartButton.setClearAlgorithmState(false);
        restartButton.setStoreAlgorithmState(true);
        restartButton.setForceAlgorithmStore(true);
        addControlButton(restartButton);
        addControlListener((ControlListener)((DigitalSearchTreeThread)algorithmThread).getAlgorithm());
    }

    protected static final String BUILD_DATE = Messages.getString("DigitalSearchTreeAnimationWindow.0");
    protected static final String BUILD_BY = Messages.getString("DigitalSearchTreeAnimationWindow.1");
    protected static final String FRAME_TITLE = Messages.getString("DigitalSearchTreeAnimationWindow.2");
    protected static final int NUMBER_OF_ELEMENTS = 10;
    private static final int MINIMUM_NUMBER_OF_ELEMENTS = 5;
    private static final int MAXIMUM_NUMBER_OF_ELEMENTS = 31;
    public static final int DEFAULT_DATA[] = {
        50, 25, 75, 12, 37, 62, 87, 6, 18, 31, 
        43, 56, 68, 81, 93
    };
    public static final int SORTED_DATA[];
    public static final int SKEWED_DATA[] = {
        57, 61, 54, 53, 49, 56, 52, 62, 58, 55, 
        48, 60, 50, 59, 51, 63
    };
    public static final int EXPONENTIAL_DATA[];
    public static final String EXPONENTIAL_LABEL = Messages.getString("DigitalSearchTreeAnimationWindow.3");
    public static final String SKEWED_LABEL = Messages.getString("DigitalSearchTreeAnimationWindow.4");
    protected static final int MAX_SIZE = 99;
    protected static final int MIN_SIZE = 1;

    static 
    {
        SORTED_DATA = new int[31];
        EXPONENTIAL_DATA = new int[10];
        for(int i = 0; i < 31; i++)
            SORTED_DATA[i] = i + 1;

        int number = 1;
        for(int i = 0; i < 10; i++)
            EXPONENTIAL_DATA[i] = number << 10 - i - 1;

    }
}
