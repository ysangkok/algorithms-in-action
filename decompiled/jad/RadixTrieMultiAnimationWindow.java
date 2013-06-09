// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RadixTrieMultiAnimationWindow.java

import com.cim.AIA.*;
import com.cim.common.RadioMenu;
import java.applet.Applet;
import java.awt.CheckboxMenuItem;
import java.awt.MenuBar;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import localization.Messages;

public class RadixTrieMultiAnimationWindow extends AnimationWindow
{

    public String getAlgorithmName()
    {
        return "AIA: RadixTrieMulti";
    }

    public RadixTrieMultiAnimationWindow(AlgorithmCanvas canvas, AlgorithmThread thread, Applet applet)
    {
        super(canvas, thread, applet);
        mnu1Bit = new CheckboxMenuItem("1 Bit");
        mnu2Bit = new CheckboxMenuItem("2 Bit");
        mnu3Bit = new CheckboxMenuItem("3 Bit");
        buildDate = "Build Date";
        buildBy = "Your Initial.Your Surname";
        frameTitle = FRAME_TITLE;
        addControlButton(new StepButton(AnimationWindow.STEP_LABEL, AnimationWindow.STEP_INSTRUCTIONS, this, thread));
        addControlButton(new BackButton(AnimationWindow.BACK_LABEL, AnimationWindow.BACK_INSTRUCTIONS, this, thread));
        addControlButton(new RunButton(AnimationWindow.RUN_LABEL, AnimationWindow.RUN_INSTRUCTIONS, this, thread));
        addControlButton(new PauseButton(AnimationWindow.PAUSE_LABEL, AnimationWindow.PAUSE_INSTRUCTIONS, this, thread));
        addControlButton(new ResetButton(AnimationWindow.RESET_LABEL, AnimationWindow.RESET_INSTRUCTIONS, this, thread));
        RestartButton restartButton = new RestartButton(Messages.getString("RadixTrieMultiAnimationWindow.1"), Messages.getString("RadixTrieMultiAnimationWindow.2"), this, thread);
        restartButton.setReinitialiseAlgorithm(false);
        restartButton.setClearAlgorithmState(false);
        restartButton.setStoreAlgorithmState(true);
        restartButton.setForceAlgorithmStore(true);
        addControlButton(restartButton);
        PresetIntArrayDataSelection defaultData = new PresetIntArrayDataSelection(DEFAULT_LABEL, true, this, DEFAULT_DATA);
        defaultData.setReinitialiseAlgorithm(true);
        defaultData.setClearAlgorithmState(true);
        addDataSelection(defaultData);
        RandomIntArrayDataSelection randomData = new RandomIntArrayDataSelection(RANDOM_LABEL, false, this, 5, 20, 1, 99);
        randomData.setReinitialiseAlgorithm(true);
        randomData.setClearAlgorithmState(true);
        addDataSelection(randomData);
        UserSelectionIntArrayDataSelection userData = new UserSelectionIntArrayDataSelection((new StringBuilder()).append(USER_SELECTION_LABEL).append("...").toString(), false, this, USER_SELECTION_LABEL, 1, 99, 5, 20);
        userData.setReinitialiseAlgorithm(true);
        userData.setClearAlgorithmState(true);
        addDataSelection(userData);
        addControlListener((ControlListener)((RadixTrieMultiThread)thread).getAlgorithm());
    }

    protected void initialiseMenuBar()
    {
        menuBar = new MenuBar();
        setMenuBar(menuBar);
        initialiseAlgorithmMenuItem();
        initialiseModeSelectionMenuItem();
        initialiseDataSelectionMenuItem();
        initialiseDataBitsMenuItem();
        initialiseHelpMenuItem();
    }

    protected void initialiseDataBitsMenuItem()
    {
        RadixTrieMulti.mnuLink = new RadioMenu(Messages.getString("RadixTrieMultiAnimationWindow.3"), true);
        mnu1Bit.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e)
            {
                oneBit();
            }

            final RadixTrieMultiAnimationWindow this$0;

            
            {
                this$0 = RadixTrieMultiAnimationWindow.this;
                super();
            }
        });
        mnu2Bit.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e)
            {
                twoBit();
            }

            final RadixTrieMultiAnimationWindow this$0;

            
            {
                this$0 = RadixTrieMultiAnimationWindow.this;
                super();
            }
        });
        mnu3Bit.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e)
            {
                threeBit();
            }

            final RadixTrieMultiAnimationWindow this$0;

            
            {
                this$0 = RadixTrieMultiAnimationWindow.this;
                super();
            }
        });
        mnu2Bit.setState(true);
        RadixTrieMulti.mnuLink.add(mnu1Bit);
        RadixTrieMulti.mnuLink.add(mnu2Bit);
        RadixTrieMulti.mnuLink.add(mnu3Bit);
        menuBar.add(RadixTrieMulti.mnuLink);
    }

    protected void oneBit()
    {
        mnu2Bit.setState(false);
        mnu3Bit.setState(false);
        resetThread(true, true, false, false);
        RadixTrieMulti.NO_OF_NODES = 2;
        RadixTrieMulti.NO_OF_BITS = 1;
        resetThread(true, true, false, false);
    }

    protected void twoBit()
    {
        mnu1Bit.setState(false);
        mnu3Bit.setState(false);
        resetThread(true, true, false, false);
        RadixTrieMulti.NO_OF_NODES = 4;
        RadixTrieMulti.NO_OF_BITS = 2;
        resetThread(true, true, false, false);
    }

    protected void threeBit()
    {
        mnu1Bit.setState(false);
        mnu2Bit.setState(false);
        resetThread(true, true, false, false);
        RadixTrieMulti.NO_OF_NODES = 8;
        RadixTrieMulti.NO_OF_BITS = 3;
        resetThread(true, true, false, false);
    }

    protected static final String BUILD_DATE = "Build Date";
    protected static final String BUILD_BY = "Your Initial.Your Surname";
    protected static final String FRAME_TITLE = Messages.getString("RadixTrieMultiAnimationWindow.0");
    public static final int DEFAULT_DATA[] = {
        1, 21, 20, 16, 17, 31, 30, 15
    };
    private static final int MINIMUM_NUMBER_OF_ELEMENTS = 5;
    private static final int MAXIMUM_NUMBER_OF_ELEMENTS = 20;
    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 99;
    CheckboxMenuItem mnu1Bit;
    CheckboxMenuItem mnu2Bit;
    CheckboxMenuItem mnu3Bit;

}
