// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MergeBUSortAnimationWindow.java

import com.cim.AIA.*;
import java.applet.Applet;
import java.awt.MenuBar;
import java.util.Vector;
import localization.Messages;

public class MergeBUSortAnimationWindow extends AnimationWindow
{

    public String getAlgorithmName()
    {
        return "AIA: Bottom-up Mergesort";
    }

    public MergeBUSortAnimationWindow(AlgorithmCanvas canvas, AlgorithmThread thread, Applet applet)
    {
        super(canvas, thread, applet);
        partitionMethods = new Vector();
        minimumNumberOfElements = 3;
        maximumNumberOfElements = 20;
        maxSize = 99;
        minSize = 1;
        buildDate = "15/2/2007";
        buildBy = "M. Tabbara";
        frameTitle = FRAME_TITLE;
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

    public void setNormalMode()
    {
        informModeListeners(new ModeEvent(this, 12356));
        thread.setHandleQuestions(false);
    }

    protected void initialiseMenuBar()
    {
        menuBar = new MenuBar();
        setMenuBar(menuBar);
        initialiseAlgorithmMenuItem();
        initialiseDataSelectionMenuItem();
        initialiseHelpMenuItem();
    }

    public void resetThread()
    {
        ((MergeBUSortThread)thread).resetSeed();
        super.resetThread();
    }

    private static final long serialVersionUID = 1L;
    protected static final String BUILD_DATE = "15/2/2007";
    protected static final String BUILD_BY = "M. Tabbara";
    protected static final String FRAME_TITLE = Messages.getString("MergeBUSortAnimationWindow.2");
    protected Vector partitionMethods;
    protected int minimumNumberOfElements;
    protected int maximumNumberOfElements;
    protected int maxSize;
    protected int minSize;
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

}
