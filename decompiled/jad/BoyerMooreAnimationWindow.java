// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BoyerMooreAnimationWindow.java

import com.cim.AIA.*;
import java.applet.Applet;
import localization.Messages;

public class BoyerMooreAnimationWindow extends AnimationWindow
{

    public String getAlgorithmName()
    {
        return "BoyerMoore String Search";
    }

    public BoyerMooreAnimationWindow(AlgorithmCanvas canvas, AlgorithmThread thread, Applet applet)
    {
        super(canvas, thread, applet);
        frameTitle = FRAME_TITLE;
        ds1 = new PresetStringArrayDataSelection(Messages.getString("BoyerMooreAnimationWindow.7"), true, this, DEFAULT_DATA_1);
        ds2 = new PresetStringArrayDataSelection(Messages.getString("BoyerMooreAnimationWindow.8"), false, this, DEFAULT_DATA_2);
        addDataSelection(ds1);
        addDataSelection(ds2);
        addDataSelection(new UserSelectionStringDataSelection((new StringBuilder()).append(USER_SELECTION_LABEL).append("...").toString(), false, this, USER_SELECTION_LABEL, 3, 25, Messages.getString("BoyerMooreAnimationWindow.10"), Messages.getString("BoyerMooreAnimationWindow.11"), 49));
        addControlButton(new StepButton(AnimationWindow.STEP_LABEL, AnimationWindow.STEP_INSTRUCTIONS, this, thread));
        addControlButton(new BackButton(AnimationWindow.BACK_LABEL, AnimationWindow.BACK_INSTRUCTIONS, this, thread));
        addControlButton(new RunButton(AnimationWindow.RUN_LABEL, AnimationWindow.RUN_INSTRUCTIONS, this, thread));
        addControlButton(new PauseButton(AnimationWindow.PAUSE_LABEL, AnimationWindow.PAUSE_LABEL, this, thread));
        addControlButton(new ResetButton(AnimationWindow.RESET_LABEL, AnimationWindow.RESET_INSTRUCTIONS, this, thread));
        selfTestMode.setEnabled(false);
    }

    private static final long serialVersionUID = 0xc8d7313fb794be25L;
    protected static final String FRAME_TITLE = Messages.getString("BoyerMooreAnimationWindow.0");
    public static final int MINIMUM_NUMBER_OF_ELEMENTS = 5;
    public static final int MAXIMUM_NUMBER_OF_ELEMENTS = 5;
    public static final int MAX_SIZE = 4;
    public static final int MIN_SIZE = 0;
    public static final String DEFAULT_DATA_1[] = {
        Messages.getString("BoyerMooreAnimationWindow.1"), Messages.getString("BoyerMooreAnimationWindow.2")
    };
    public static final String DEFAULT_DATA_2[] = {
        Messages.getString("BoyerMooreAnimationWindow.3"), Messages.getString("BoyerMooreAnimationWindow.4")
    };
    public static final String DEFAULT_DATA_3[] = {
        Messages.getString("BoyerMooreAnimationWindow.5"), Messages.getString("BoyerMooreAnimationWindow.6")
    };
    public static final int MAX_LENGTH = 25;
    public static final int MIN_LENGTH = 3;
    protected PresetStringArrayDataSelection ds1;
    protected PresetStringArrayDataSelection ds2;
    protected PresetStringArrayDataSelection ds3;

}
