// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HeapSortAnimationWindow.java

import com.cim.AIA.*;
import com.cim.common.RadioMenu;
import java.applet.Applet;
import java.awt.CheckboxMenuItem;
import java.awt.MenuBar;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import localization.Messages;

public class HeapSortAnimationWindow extends AnimationWindow
{

    public String getAlgorithmName()
    {
        return "AIA: Heapsort";
    }

    public HeapSortAnimationWindow(AlgorithmCanvas canvas, AlgorithmThread thread, Applet applet)
    {
        super(canvas, thread, applet);
        easyMode = true;
        buildDate = "25/05/1999";
        buildBy = "T.Witham";
        frameTitle = FRAME_TITLE;
        dataMenu.setLabel(DATA_LABEL);
        addDataSelection(new PresetIntArrayDataSelection(SORTED_LABEL, false, this, SORTED_DATA));
        addDataSelection(new PresetIntArrayDataSelection(REVERSE_SORTED_LABEL, false, this, REVERSE_SORTED_DATA));
        addDataSelection(new RandomSameIntArrayDataSelection(SAME_LABEL, false, this, 3, 20, 1, 99));
        addDataSelection(new RandomIntArrayDataSelection(RANDOM_LABEL, true, this, 3, 20, 1, 99));
        addDataSelection(new UserSelectionIntArrayDataSelection((new StringBuilder()).append(USER_SELECTION_LABEL).append("...").toString(), false, this, USER_SELECTION_LABEL, 1, 99, 3, 20));
        addControlButton(new StepButton(AnimationWindow.STEP_LABEL, AnimationWindow.STEP_INSTRUCTIONS, this, thread));
        addControlButton(new BackButton(AnimationWindow.BACK_LABEL, AnimationWindow.BACK_INSTRUCTIONS, this, thread));
        addControlButton(new RunButton(AnimationWindow.RUN_LABEL, AnimationWindow.RUN_INSTRUCTIONS, this, thread));
        addControlButton(new PauseButton(AnimationWindow.PAUSE_LABEL, AnimationWindow.PAUSE_INSTRUCTIONS, this, thread));
        addControlButton(new ResetButton(AnimationWindow.RESET_LABEL, AnimationWindow.RESET_INSTRUCTIONS, this, thread));
    }

    protected void easyMode()
    {
        easyMode = true;
        hard.setState(!easyMode);
        easy.setState(easyMode);
        if(selfTestMode.getState())
            heapSortCanvas.setMarkersEnabled(easyMode);
    }

    protected void hardMode()
    {
        easyMode = false;
        hard.setState(!easyMode);
        easy.setState(easyMode);
        if(selfTestMode.getState())
            heapSortCanvas.setMarkersEnabled(easyMode);
    }

    protected void initialiseMenuBar()
    {
        menuBar = new MenuBar();
        setMenuBar(menuBar);
        initialiseAlgorithmMenuItem();
        initialiseModeSelectionMenuItem();
        initialiseQuestionLevel();
        initialiseDataSelectionMenuItem();
        initialiseHelpMenuItem();
    }

    protected void initialiseQuestionLevel()
    {
        RadioMenu radioDifficultyMenu = new RadioMenu(PREDICITION_DIFFICULTY_LABEL);
        easy = new CheckboxMenuItem(EASY_LABEL, easyMode);
        hard = new CheckboxMenuItem(HARD_LABEL, !easyMode);
        heapSortCanvas = (HeapSortCanvas)(HeapSortCanvas)canvas;
        easy.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e)
            {
                easyMode();
            }

            final HeapSortAnimationWindow this$0;

            
            {
                this$0 = HeapSortAnimationWindow.this;
                super();
            }
        });
        hard.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e)
            {
                hardMode();
            }

            final HeapSortAnimationWindow this$0;

            
            {
                this$0 = HeapSortAnimationWindow.this;
                super();
            }
        });
        selfTestMode.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e)
            {
                heapSortCanvas.setMarkersEnabled(easyMode);
            }

            final HeapSortAnimationWindow this$0;

            
            {
                this$0 = HeapSortAnimationWindow.this;
                super();
            }
        });
        if(selfTestMode.getState())
            heapSortCanvas.setMarkersEnabled(easyMode);
        radioDifficultyMenu.add(easy);
        radioDifficultyMenu.add(hard);
        modeMenu.addSeparator();
        modeMenu.add(radioDifficultyMenu);
    }

    public void setNormalMode()
    {
        informModeListeners(new ModeEvent(this, 12356));
        ((HeapSortCanvas)(HeapSortCanvas)canvas).setMarkersEnabled(true);
        thread.setHandleQuestions(false);
    }

    private static final long serialVersionUID = 0x850d35b19fa0b7ddL;
    protected static final String BUILD_DATE = "25/05/1999";
    protected static final String BUILD_BY = "T.Witham";
    public static final String FRAME_TITLE = Messages.getString("HeapSortAnimationWindow.2");
    public static final int MINIMUM_NUMBER_OF_ELEMENTS = 3;
    public static final int MAXIMUM_NUMBER_OF_ELEMENTS = 20;
    public static final int MAX_SIZE = 99;
    public static final int MIN_SIZE = 1;
    public static final int DEFAULT_DATA[] = {
        5, 95, 10, 90, 15, 85, 20, 80, 25, 75, 
        30, 70, 35, 65, 40
    };
    public static final int SORTED_DATA[] = {
        5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 
        55, 60, 65, 70, 75
    };
    public static final int REVERSE_SORTED_DATA[] = {
        75, 70, 65, 60, 55, 50, 45, 40, 35, 30, 
        25, 20, 15, 10, 5
    };
    protected static final String DATA_LABEL = Messages.getString("HeapSortAnimationWindow.3");
    protected static final String PREDICITION_DIFFICULTY_LABEL = Messages.getString("HeapSortAnimationWindow.4");
    protected static final String EASY_LABEL = Messages.getString("HeapSortAnimationWindow.5");
    protected static final String HARD_LABEL = Messages.getString("HeapSortAnimationWindow.6");
    protected CheckboxMenuItem easy;
    protected CheckboxMenuItem hard;
    protected HeapSortCanvas heapSortCanvas;
    protected boolean easyMode;

}
