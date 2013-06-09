// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StraightRadixSortAnimationWindow.java

import com.cim.AIA.*;
import java.applet.Applet;

public class StraightRadixSortAnimationWindow extends AnimationWindow
{

    public String getAlgorithmName()
    {
        return "AIA: Straight Radix Sort";
    }

    public StraightRadixSortAnimationWindow(AlgorithmCanvas canvas, AlgorithmThread thread, Applet applet)
    {
        super(canvas, thread, applet);
        buildDate = "1/10/1999";
        buildBy = "T.Witham";
        frameTitle = "AIA: Straight Radix Sort";
        addDataSelection(new RandomIntArrayDataSelection(RANDOM_LABEL, true, this, 3, 20, 0, 15));
        addDataSelection(new UserSelectionIntArrayDataSelection((new StringBuilder()).append(USER_SELECTION_LABEL).append("...").toString(), false, this, USER_SELECTION_LABEL, 0, 15, 3, 20));
        addControlButton(new StepButton(AnimationWindow.STEP_LABEL, AnimationWindow.STEP_INSTRUCTIONS, this, thread));
        addControlButton(new BackButton(AnimationWindow.BACK_LABEL, AnimationWindow.BACK_INSTRUCTIONS, this, thread));
        addControlButton(new RunButton(AnimationWindow.RUN_LABEL, AnimationWindow.RUN_INSTRUCTIONS, this, thread));
        addControlButton(new PauseButton(AnimationWindow.PAUSE_LABEL, AnimationWindow.PAUSE_INSTRUCTIONS, this, thread));
        addControlButton(new ResetButton(AnimationWindow.RESET_LABEL, AnimationWindow.RESET_INSTRUCTIONS, this, thread));
        selfTestMode.setEnabled(false);
    }

    private static final long serialVersionUID = 0x4e58370c766b94c9L;
    protected static final String BUILD_DATE = "1/10/1999";
    protected static final String BUILD_BY = "T.Witham";
    protected static final String FRAME_TITLE = "AIA: Straight Radix Sort";
    public static final int MINIMUM_NUMBER_OF_ELEMENTS = 3;
    public static final int MAXIMUM_NUMBER_OF_ELEMENTS = 20;
    public static final int MAX_SIZE = 15;
    public static final int MIN_SIZE = 0;
}
