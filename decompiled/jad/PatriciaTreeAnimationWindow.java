// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PatriciaTreeAnimationWindow.java

import com.cim.AIA.*;
import java.applet.Applet;
import localization.Messages;

public class PatriciaTreeAnimationWindow extends AnimationWindow
{

    public String getAlgorithmName()
    {
        return "AIA: PatriciaTree";
    }

    public PatriciaTreeAnimationWindow(AlgorithmCanvas canvas, AlgorithmThread thread, Applet applet)
    {
        super(canvas, thread, applet);
        buildDate = "Build Date";
        buildBy = "Your Initial.Your Surname";
        frameTitle = FRAME_TITLE;
        addControlButton(new StepButton(AnimationWindow.STEP_LABEL, AnimationWindow.STEP_INSTRUCTIONS, this, thread));
        BackButton back = new BackButton(AnimationWindow.BACK_LABEL, AnimationWindow.BACK_INSTRUCTIONS, this, thread);
        addControlButton(back);
        addControlButton(new RunButton(AnimationWindow.RUN_LABEL, AnimationWindow.RUN_INSTRUCTIONS, this, thread));
        addControlButton(new PauseButton(AnimationWindow.PAUSE_LABEL, AnimationWindow.PAUSE_INSTRUCTIONS, this, thread));
        addControlButton(new ResetButton(AnimationWindow.RESET_LABEL, AnimationWindow.RESET_INSTRUCTIONS, this, thread));
        RestartButton restart = new RestartButton(Messages.getString("PatriciaTreeAnimationWindow.1"), Messages.getString("PatriciaTreeAnimationWindow.0"), this, thread);
        restart.setReinitialiseAlgorithm(false);
        restart.setClearAlgorithmState(false);
        restart.setStoreAlgorithmState(true);
        restart.setForceAlgorithmStore(true);
        addControlButton(restart);
        addControlListener((ControlListener)((PatriciaTreeThread)thread).getAlgorithm());
        PresetIntArrayDataSelection defaultData = new PresetIntArrayDataSelection(DEFAULT_LABEL, true, this, DEFAULT_DATA);
        defaultData.setReinitialiseAlgorithm(false);
        defaultData.setClearAlgorithmState(true);
        addDataSelection(defaultData);
        RandomIntArrayDataSelection randomData = new RandomIntArrayDataSelection(RANDOM_LABEL, false, this, 5, 30, 3, 99);
        randomData.setReinitialiseAlgorithm(false);
        randomData.setClearAlgorithmState(true);
        addDataSelection(randomData);
        UserSelectionIntArrayDataSelection userData = new UserSelectionIntArrayDataSelection((new StringBuilder()).append(USER_SELECTION_LABEL).append("...").toString(), false, this, USER_SELECTION_LABEL, 3, 99, 5, 30);
        userData.setReinitialiseAlgorithm(false);
        userData.setClearAlgorithmState(true);
        addDataSelection(userData);
    }

    private static final long serialVersionUID = 0xcd27bf44c19c65f2L;
    protected static final String BUILD_DATE = "Build Date";
    protected static final String BUILD_BY = "Your Initial.Your Surname";
    protected static final String FRAME_TITLE = Messages.getString("PatriciaTreeAnimationWindow.2");
    private static final int MINIMUM_NUMBER_OF_ELEMENTS = 5;
    private static final int MAXIMUM_NUMBER_OF_ELEMENTS = 30;
    private static final int MIN_SIZE = 3;
    private static final int MAX_SIZE = 99;
    public static final int DEFAULT_DATA[] = {
        1, 31, 30, 28, 27, 21, 24, 23, 20, 17, 
        19, 25, 18, 29, 26, 2, 4, 5
    };

}
