// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SelectionSortAnimationWindow.java

import com.cim.AIA.*;
import java.applet.Applet;
import java.net.URL;
import localization.Messages;

public class SelectionSortAnimationWindow extends AnimationWindow
{

    public String getAlgorithmName()
    {
        return "AIA: SelectionSort";
    }

    public SelectionSortAnimationWindow(AlgorithmCanvas canvas, AlgorithmThread thread, Applet applet)
    {
        super(canvas, thread, applet);
        minimumNumberOfElements = 3;
        maximumNumberOfElements = 20;
        maxSize = 99;
        minSize = 1;
        buildDate = "10 June 99";
        buildBy = "T.Witham";
        frameTitle = FRAME_TITLE;
        addModeSelection(new QuizModeSelection((new StringBuilder()).append(QUIZ_MODE_LABEL).append("...").toString(), false, this, thread, applet, Messages.getString("SelectionSortAnimationWindow.1"), "selectionsort.quiz", (new StringBuilder()).append(applet.getCodeBase().toString()).append("images/").toString(), "correct.gif", "incorrect.gif"));
        addDataSelection(new PresetIntArrayDataSelection(DEFAULT_LABEL, true, this, DEFAULT_DATA));
        addDataSelection(new PresetIntArrayDataSelection(SORTED_LABEL, false, this, SORTED_DATA));
        addDataSelection(new PresetIntArrayDataSelection(REVERSE_SORTED_LABEL, false, this, REVERSE_SORTED_DATA));
        addDataSelection(new RandomSameIntArrayDataSelection(SAME_LABEL, false, this, minimumNumberOfElements, maximumNumberOfElements, minSize, maxSize));
        addDataSelection(new RandomIntArrayDataSelection(RANDOM_LABEL, false, this, minimumNumberOfElements, maximumNumberOfElements, minSize, maxSize));
        addDataSelection(new UserSelectionIntArrayDataSelection((new StringBuilder()).append(USER_SELECTION_LABEL).append("...").toString(), false, this, USER_SELECTION_LABEL, minSize, maxSize, minimumNumberOfElements, maximumNumberOfElements));
        addControlButton(new StepButton(AnimationWindow.STEP_LABEL, AnimationWindow.STEP_INSTRUCTIONS, this, thread));
        addControlButton(new BackButton(AnimationWindow.BACK_LABEL, AnimationWindow.BACK_INSTRUCTIONS, this, thread));
        addControlButton(new RunButton(AnimationWindow.RUN_LABEL, AnimationWindow.RUN_INSTRUCTIONS, this, thread));
        addControlButton(new PauseButton(AnimationWindow.PAUSE_LABEL, AnimationWindow.PAUSE_INSTRUCTIONS, this, thread));
        addControlButton(new ResetButton(AnimationWindow.RESET_LABEL, AnimationWindow.RESET_INSTRUCTIONS, this, thread));
    }

    private static final long serialVersionUID = 0x983cdb766053f14bL;
    protected static final String QUIZ_FILE_NAME = "selectionsort.quiz";
    protected static final String BUILD_DATE = "10 June 99";
    protected static final String BUILD_BY = "T.Witham";
    protected static final String FRAME_TITLE = Messages.getString("SelectionSortAnimationWindow.0");
    public static final int DEFAULT_DATA[] = {
        40, 35, 45, 30, 50, 25, 55, 20, 60, 15, 
        65, 10, 70, 5, 75
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
