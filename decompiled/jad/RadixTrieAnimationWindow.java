// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RadixTrieAnimationWindow.java

import com.cim.AIA.*;
import java.applet.Applet;
import localization.Messages;

public class RadixTrieAnimationWindow extends AnimationWindow
{

    public String getAlgorithmName()
    {
        return "AIA: RadixTrie";
    }

    public RadixTrieAnimationWindow(AlgorithmCanvas canvas, AlgorithmThread thread, Applet applet)
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
        RestartButton restartButton = new RestartButton(Messages.getString("RadixTrieAnimationWindow.3"), Messages.getString("RadixTrieAnimationWindow.4"), this, thread);
        restartButton.setReinitialiseAlgorithm(false);
        restartButton.setClearAlgorithmState(false);
        restartButton.setStoreAlgorithmState(true);
        restartButton.setForceAlgorithmStore(true);
        addControlButton(restartButton);
        PresetIntArrayDataSelection defaultData = new PresetIntArrayDataSelection(DEFAULT_LABEL, true, this, DEFAULT_DATA);
        defaultData.setReinitialiseAlgorithm(true);
        defaultData.setClearAlgorithmState(true);
        addDataSelection(defaultData);
        RandomIntArrayDataSelection randomData = new RandomIntArrayDataSelection(RANDOM_LABEL, false, this, 5, 20, 3, 99);
        randomData.setReinitialiseAlgorithm(true);
        randomData.setClearAlgorithmState(true);
        addDataSelection(randomData);
        UserSelectionIntArrayDataSelection userData = new UserSelectionIntArrayDataSelection((new StringBuilder()).append(USER_SELECTION_LABEL).append("...").toString(), false, this, USER_SELECTION_LABEL, 3, 99, 5, 20);
        userData.setReinitialiseAlgorithm(true);
        userData.setClearAlgorithmState(true);
        addDataSelection(userData);
        addControlListener((ControlListener)((RadixTrieThread)thread).getAlgorithm());
    }

    protected static final String BUILD_DATE = "Build Date";
    protected static final String BUILD_BY = "Your Initial.Your Surname";
    protected static final String FRAME_TITLE = Messages.getString("RadixTrieAnimationWindow.2");
    public static final int DEFAULT_DATA[] = {
        1, 21, 20, 16, 17, 31, 30, 15
    };
    private static final int MINIMUM_NUMBER_OF_ELEMENTS = 5;
    private static final int MAXIMUM_NUMBER_OF_ELEMENTS = 20;
    private static final int MIN_SIZE = 3;
    private static final int MAX_SIZE = 99;

}
