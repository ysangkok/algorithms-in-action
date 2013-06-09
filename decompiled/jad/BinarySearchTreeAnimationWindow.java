// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BinarySearchTreeAnimationWindow.java

import com.cim.AIA.*;
import java.applet.Applet;
import localization.Messages;

public class BinarySearchTreeAnimationWindow extends AnimationWindow
{

    public String getAlgorithmName()
    {
        return "AIA: BinarySearchTree";
    }

    public BinarySearchTreeAnimationWindow(AlgorithmCanvas algorithmCanvas, AlgorithmThread algorithmThread, Applet applet)
    {
        super(algorithmCanvas, algorithmThread, applet);
        buildDate = "Build Date";
        buildBy = "Your Initial.Your Surname";
        frameTitle = FRAME_TITLE;
        PresetIntArrayDataSelection defaultData = new PresetIntArrayDataSelection(DEFAULT_LABEL, true, this, DEFAULT_DATA);
        addDataSelection(defaultData);
        RandomIntArrayDataSelection randomData = new RandomIntArrayDataSelection(RANDOM_LABEL, false, this, 5, 20, 1, 99);
        addDataSelection(randomData);
        PresetIntArrayDataSelection sortedData = new PresetIntArrayDataSelection(SORTED_LABEL, false, this, SORTED_DATA);
        addDataSelection(sortedData);
        UserSelectionIntArrayDataSelection userData = new UserSelectionIntArrayDataSelection((new StringBuilder()).append(USER_SELECTION_LABEL).append("...").toString(), false, this, USER_SELECTION_LABEL, 1, 99, 5, 20);
        addDataSelection(userData);
        addControlButton(new StepButton(AnimationWindow.STEP_LABEL, AnimationWindow.STEP_INSTRUCTIONS, this, algorithmThread));
        BinarySearchTree.backButton = new NewBackButton(AnimationWindow.BACK_LABEL, AnimationWindow.BACK_INSTRUCTIONS, this, algorithmThread);
        addControlButton(BinarySearchTree.backButton);
        addControlButton(new RunButton(AnimationWindow.RUN_LABEL, AnimationWindow.RUN_INSTRUCTIONS, this, algorithmThread));
        addControlButton(new PauseButton(AnimationWindow.PAUSE_LABEL, AnimationWindow.PAUSE_INSTRUCTIONS, this, algorithmThread));
        addControlButton(new ResetButton(AnimationWindow.RESET_LABEL, AnimationWindow.RESET_INSTRUCTIONS, this, algorithmThread));
        RestartButton restartButton = new RestartButton(Messages.getString("BinarySearchTreeAnimationWindow.4"), Messages.getString("BinarySearchTreeAnimationWindow.5"), this, algorithmThread);
        restartButton.setReinitialiseAlgorithm(false);
        restartButton.setClearAlgorithmState(false);
        restartButton.setStoreAlgorithmState(true);
        restartButton.setForceAlgorithmStore(true);
        addControlButton(restartButton);
        addControlListener((ControlListener)((BinarySearchTreeThread)algorithmThread).getAlgorithm());
    }

    private static final long serialVersionUID = 0x85d2a84df324cddaL;
    protected static final String BUILD_DATE = "Build Date";
    protected static final String BUILD_BY = "Your Initial.Your Surname";
    protected static final String FRAME_TITLE = Messages.getString("BinarySearchTreeAnimationWindow.2");
    protected static final int NUMBER_OF_ELEMENTS = 10;
    private static final int MINIMUM_OF_ELEMENTS = 5;
    private static final int MAXIMUM_OF_ELEMENTS = 20;
    public static final int DEFAULT_DATA[] = {
        50, 25, 75, 12, 37, 62, 87, 6, 18, 31, 
        43, 56, 68, 81, 93
    };
    public static final int SORTED_DATA[];
    protected static final int MAX_SIZE = 99;
    protected static final int MIN_SIZE = 1;

    static 
    {
        SORTED_DATA = new int[10];
        for(int i = 0; i < 10; i++)
            SORTED_DATA[i] = i * 5 + 1;

    }
}
