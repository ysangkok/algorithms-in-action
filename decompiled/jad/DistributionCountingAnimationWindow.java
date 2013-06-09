// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DistributionCountingAnimationWindow.java

import com.cim.AIA.*;
import java.applet.Applet;
import localization.Messages;

public class DistributionCountingAnimationWindow extends AnimationWindow
{

    public String getAlgorithmName()
    {
        return "AIA: Distribution Counting";
    }

    public DistributionCountingAnimationWindow(AlgorithmCanvas canvas, AlgorithmThread thread, Applet applet)
    {
        super(canvas, thread, applet);
        buildDate = "15/06/1999";
        buildBy = "T.Witham";
        frameTitle = FRAME_TITLE;
        addDataSelection(new RandomIntArrayDataSelection(RANDOM_LABEL, true, this, 3, 20, 0, 4));
        addDataSelection(new UserSelectionIntArrayDataSelection((new StringBuilder()).append(USER_SELECTION_LABEL).append("...").toString(), false, this, USER_SELECTION_LABEL, 0, 4, 3, 20));
        addControlButton(new StepButton(AnimationWindow.STEP_LABEL, AnimationWindow.STEP_INSTRUCTIONS, this, thread));
        addControlButton(new BackButton(AnimationWindow.BACK_LABEL, AnimationWindow.BACK_INSTRUCTIONS, this, thread));
        addControlButton(new RunButton(AnimationWindow.RUN_LABEL, AnimationWindow.RUN_INSTRUCTIONS, this, thread));
        addControlButton(new PauseButton(AnimationWindow.PAUSE_LABEL, AnimationWindow.PAUSE_INSTRUCTIONS, this, thread));
        addControlButton(new ResetButton(AnimationWindow.RESET_LABEL, AnimationWindow.RESET_INSTRUCTIONS, this, thread));
        selfTestMode.setEnabled(false);
    }

    private static final long serialVersionUID = 0x9a931dd159ab8adcL;
    protected static final String BUILD_DATE = "15/06/1999";
    protected static final String BUILD_BY = "T.Witham";
    protected static final String FRAME_TITLE = Messages.getString("DistributionCountingAnimationWindow.2");
    public static final int MINIMUM_NUMBER_OF_ELEMENTS = 3;
    public static final int MAXIMUM_NUMBER_OF_ELEMENTS = 20;
    public static final int MAX_SIZE = 4;
    public static final int MIN_SIZE = 0;

}
