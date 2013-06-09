// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KMPAnimationWindow.java

import com.cim.AIA.*;
import com.cim.common.RadioMenu;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;
import java.util.Vector;
import localization.Messages;

public class KMPAnimationWindow extends AnimationWindow
{
    public class VariationMethod extends CheckboxMenuItem
    {

        private static final long serialVersionUID = 0x13ca060206b63498L;
        protected LadderTree ladderTree;
        protected KMP kmp;
        protected int variationMethod;
        protected KMPAnimationWindow animWindow;
        protected AlgorithmWindow algoWindow;
        final KMPAnimationWindow this$0;

        public VariationMethod(String name, boolean state, KMP aKmp, String dataDir, int aVariationMethod, String filename, 
                KMPAnimationWindow kmpAnimationWindow, AlgorithmWindow algorithmWindow)
        {
            this$0 = KMPAnimationWindow.this;
            super(name, state);
            kmp = aKmp;
            variationMethod = aVariationMethod;
            animWindow = kmpAnimationWindow;
            algoWindow = algorithmWindow;
            ladderTree = CodeCanvas.getLadderTreeFromFile(dataDir, filename, getLogger(), getBreakPoint());
            addItemListener(new ItemListener() {

                public void itemStateChanged(ItemEvent e)
                {
                    animWindow.resetVariationButtons();
                    setState(true);
                    kmp.setVariation(variationMethod);
                    resetThread(true, true, false, false);
                    algoWindow.setLadderTree(ladderTree);
                }

                final KMPAnimationWindow val$this$0;
                final VariationMethod this$1;


// JavaClassFileOutputException: Invalid index accessing method local variables table of <init>
            });
        }
    }


    public String getAlgorithmName()
    {
        return "KMP String Search";
    }

    public KMPAnimationWindow(AlgorithmCanvas canvas, AlgorithmThread thread, Applet applet)
    {
        super(canvas, thread, applet);
        variationMethods = new Vector();
        frameTitle = FRAME_TITLE;
        ds1 = new PresetStringArrayDataSelection(Messages.getString("KMPAnimationWindow.10"), true, this, DEFAULT_DATA_1);
        ds2 = new PresetStringArrayDataSelection(Messages.getString("KMPAnimationWindow.11"), false, this, DEFAULT_DATA_2);
        ds3 = new PresetStringArrayDataSelection(Messages.getString("KMPAnimationWindow.12"), false, this, DEFAULT_DATA_3);
        addDataSelection(ds1);
        addDataSelection(ds2);
        addDataSelection(ds3);
        addDataSelection(new UserSelectionStringDataSelection((new StringBuilder()).append(USER_SELECTION_LABEL).append("...").toString(), false, this, USER_SELECTION_LABEL, 3, 12, Messages.getString("KMPAnimationWindow.14"), Messages.getString("KMPAnimationWindow.15")));
        addControlButton(new StepButton(AnimationWindow.STEP_LABEL, AnimationWindow.STEP_INSTRUCTIONS, this, thread));
        addControlButton(new BackButton(AnimationWindow.BACK_LABEL, AnimationWindow.BACK_INSTRUCTIONS, this, thread));
        addControlButton(new RunButton(AnimationWindow.RUN_LABEL, AnimationWindow.RUN_INSTRUCTIONS, this, thread));
        addControlButton(new PauseButton(AnimationWindow.PAUSE_LABEL, AnimationWindow.PAUSE_LABEL, this, thread));
        addControlButton(new ResetButton(AnimationWindow.RESET_LABEL, AnimationWindow.RESET_INSTRUCTIONS, this, thread));
        selfTestMode.setEnabled(false);
    }

    protected void initialiseMenuBar()
    {
        menuBar = new MenuBar();
        setMenuBar(menuBar);
        initialiseAlgorithmMenuItem();
        initialiseModeSelectionMenuItem();
        initialiseDataSelectionMenuItem();
        initialiseVariationMenuItem();
        initialiseHelpMenuItem();
    }

    protected void initialiseVariationMenuItem()
    {
        RadioMenu variationMethod = new RadioMenu(VARIATION);
        KMP kmp = (KMP)(KMP)getAlgorithm();
        variationMethods.addElement(new VariationMethod(FIRST, true, kmp, applet.getCodeBase().toString(), 1, KMPApplet.FIRST_FILE_NAME, this, getAlgorithmWindow()));
        variationMethods.addElement(new VariationMethod(SECOND, false, kmp, applet.getCodeBase().toString(), 2, KMPApplet.SECOND_FILE_NAME, this, getAlgorithmWindow()));
        for(int i = 0; i < variationMethods.size(); i++)
            variationMethod.add((MenuItem)variationMethods.elementAt(i));

        menuBar.add(variationMethod);
    }

    public void resetVariationButtons()
    {
        for(int i = 0; i < variationMethods.size(); i++)
            ((VariationMethod)variationMethods.elementAt(i)).setState(false);

    }

    private static final long serialVersionUID = 0x9f476e27bea5592L;
    protected static final String FRAME_TITLE = Messages.getString("KMPAnimationWindow.0");
    protected static final String VARIATION = Messages.getString("KMPAnimationWindow.1");
    protected static final String FIRST = Messages.getString("KMPAnimationWindow.2");
    protected static final String SECOND = Messages.getString("KMPAnimationWindow.3");
    public static final int MINIMUM_NUMBER_OF_ELEMENTS = 5;
    public static final int MAXIMUM_NUMBER_OF_ELEMENTS = 5;
    public static final int MAX_SIZE = 4;
    public static final int MIN_SIZE = 0;
    public static final String DEFAULT_DATA_1[] = {
        Messages.getString("KMPAnimationWindow.4"), Messages.getString("KMPAnimationWindow.5")
    };
    public static final String DEFAULT_DATA_2[] = {
        Messages.getString("KMPAnimationWindow.6"), Messages.getString("KMPAnimationWindow.7")
    };
    public static final String DEFAULT_DATA_3[] = {
        Messages.getString("KMPAnimationWindow.8"), Messages.getString("KMPAnimationWindow.9")
    };
    public static final int MAX_LENGTH = 12;
    public static final int MIN_LENGTH = 3;
    protected PresetStringArrayDataSelection ds1;
    protected PresetStringArrayDataSelection ds2;
    protected PresetStringArrayDataSelection ds3;
    protected Vector variationMethods;

}
