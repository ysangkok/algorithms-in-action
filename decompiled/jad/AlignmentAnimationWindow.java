// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AlignmentAnimationWindow.java

import com.cim.AIA.*;
import com.cim.common.RadioMenu;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.Vector;
import localization.Messages;

public class AlignmentAnimationWindow extends AnimationWindow
{
    public class VariationMethod extends CheckboxMenuItem
    {

        private static final long serialVersionUID = 0xce64be2ef3f94f2aL;
        protected LadderTree ladderTree;
        protected Alignment alignment;
        protected int variationMethod;
        protected AlignmentAnimationWindow animWindow;
        protected AlgorithmWindow algoWindow;
        protected String expName;
        final AlignmentAnimationWindow this$0;

        public VariationMethod(final String name, boolean state, Alignment aAlignment, String dataDir, int aVariationMethod, String filename, 
                String expFileName, AlignmentAnimationWindow alignmentAnimationWindow, AlgorithmWindow algorithmWindow)
        {
            this$0 = AlignmentAnimationWindow.this;
            super(name, state);
            alignment = aAlignment;
            variationMethod = aVariationMethod;
            animWindow = alignmentAnimationWindow;
            algoWindow = algorithmWindow;
            expName = expFileName;
            ladderTree = CodeCanvas.getLadderTreeFromFile(dataDir, filename, getLogger(), getBreakPoint());
            addItemListener(new ItemListener() {

                public void itemStateChanged(ItemEvent e)
                {
                    animWindow.resetVariationButtons();
                    if(Alignment.runningMode == 1 && (ds1.getState() || ds2.getState()))
                        if(Alignment.currentVariation == 1)
                        {
                            ds1.setState(false);
                            com.cim.AIA.Copyable temp = ds2.getData();
                            ds2.setState(true);
                            setCurrentData(temp, true, true);
                        } else
                        {
                            ds2.setState(false);
                            com.cim.AIA.Copyable temp = ds1.getData();
                            ds1.setState(true);
                            setCurrentData(temp, true, true);
                        }
                    if(Alignment.runningMode == 2 && (ds3.getState() || ds4.getState()))
                        if(Alignment.currentVariation == 1)
                        {
                            ds3.setState(false);
                            com.cim.AIA.Copyable temp = ds4.getData();
                            ds4.setState(true);
                            setCurrentData(temp, true, true);
                        } else
                        {
                            ds4.setState(false);
                            com.cim.AIA.Copyable temp = ds3.getData();
                            ds3.setState(true);
                            setCurrentData(temp, true, true);
                        }
                    setState(true);
                    AlignmentApplet.expWin.initialiseMainData(AlignmentApplet.codeBaseString, expName);
                    alignment.setVariation(variationMethod);
                    resetThread(true, true, false, false);
                    algoWindow.setLadderTree(ladderTree);
                    Log log = new Log((byte)4, (byte)21, null, name);
                    if(getLogger() != null)
                        getLogger().addLog(log);
                }

                final AlignmentAnimationWindow val$this$0;
                final String val$name;
                final VariationMethod this$1;


// JavaClassFileOutputException: Invalid index accessing method local variables table of <init>
            });
        }
    }


    public String getAlgorithmName()
    {
        return "Introduction to Alignment";
    }

    public AlignmentAnimationWindow(AlgorithmCanvas canvas, AlgorithmThread thread, Applet applet)
    {
        super(canvas, thread, applet);
        variationMethods = new Vector();
        frameTitle = FRAME_TITLE;
        ds1 = new PresetStringArrayDataSelection(Messages.getString("AlignmentAnimationWindow.22"), Alignment.runningMode == 1, this, GLOBAL_DIST_NOGAP_DATA);
        ds2 = new PresetStringArrayDataSelection(Messages.getString("AlignmentAnimationWindow.23"), false, this, GLOBAL_DIST_GAP_DATA);
        ds3 = new PresetStringArrayDataSelection(Messages.getString("AlignmentAnimationWindow.24"), Alignment.runningMode == 2, this, GLOBAL_SIM_NOGAP_DATA);
        ds4 = new PresetStringArrayDataSelection(Messages.getString("AlignmentAnimationWindow.25"), false, this, GLOBAL_SIM_GAP_DATA);
        ds5 = new PresetStringArrayDataSelection(Messages.getString("AlignmentAnimationWindow.26"), Alignment.runningMode == 3, this, LOCAL_SIM_NOGAP_DATA);
        ds6 = new PresetStringArrayDataSelection(Messages.getString("AlignmentAnimationWindow.27"), false, this, LOCAL_SIM_GAP_DATA);
        addDataSelection(ds1);
        addDataSelection(ds2);
        addDataSelection(ds3);
        addDataSelection(ds4);
        addDataSelection(ds5);
        addDataSelection(ds6);
        addDataSelection(new UserSelectionStringDataSelection((new StringBuilder()).append(USER_SELECTION_LABEL).append("...").toString(), false, this, USER_SELECTION_LABEL, 3, 9));
        addControlButton(new StepButton(AnimationWindow.STEP_LABEL, AnimationWindow.STEP_INSTRUCTIONS, this, thread));
        addControlButton(new BackButton(AnimationWindow.BACK_LABEL, AnimationWindow.BACK_INSTRUCTIONS, this, thread));
        addControlButton(new RunButton(AnimationWindow.RUN_LABEL, AnimationWindow.RUN_INSTRUCTIONS, this, thread));
        addControlButton(new PauseButton(AnimationWindow.PAUSE_LABEL, AnimationWindow.PAUSE_LABEL, this, thread));
        addControlButton(new ResetButton(AnimationWindow.RESET_LABEL, AnimationWindow.RESET_INSTRUCTIONS, this, thread));
        selfTestMode.setEnabled(false);
    }

    protected void edit3D()
    {
        Alignment alignment = (Alignment)(Alignment)getAlgorithm();
        Alignment3DEditDialog dialog = new Alignment3DEditDialog("Rotation parameters", alignment);
        dialog.setVisible(true);
    }

    protected void editCost()
    {
        Alignment alignment = (Alignment)(Alignment)getAlgorithm();
        String costs[] = new String[5];
        costs[0] = (new StringBuilder()).append(alignment.getMatchCost()).append("").toString();
        costs[1] = (new StringBuilder()).append(alignment.getMismatchCost()).append("").toString();
        costs[2] = (new StringBuilder()).append(alignment.getSpaceCost()).append("").toString();
        costs[3] = (new StringBuilder()).append(alignment.getInitialGapCost()).append("").toString();
        costs[4] = (new StringBuilder()).append(alignment.getRunningGapCost()).append("").toString();
        AlignmentCostDialog dialog = new AlignmentCostDialog(AlignmentCostDialog.DEFAULT_TITLE, costs, -10, 10, Alignment.currentVariation);
        dialog.setVisible(true);
        int newCost[] = dialog.getData();
        if(newCost.length == 5)
        {
            alignment.setMatchCost(newCost[0]);
            alignment.setMismatchCost(newCost[1]);
            alignment.setSpaceCost(newCost[2]);
            alignment.setInitialGapCost(newCost[3]);
            alignment.setRunningGapCost(newCost[4]);
            resetThread(true, true, false, false);
        }
        StringBuffer datastr = new StringBuffer();
        datastr.append("[");
        for(int i = 0; i < newCost.length; i++)
        {
            if(i > 0)
                datastr.append(",");
            datastr.append(newCost[i]);
        }

        datastr.append("]");
        Log log = new Log((byte)4, (byte)22, null, datastr.toString());
        if(getLogger() != null)
            getLogger().addLog(log);
    }

    protected void initialise3DMenuItem()
    {
        Menu edit3D = new Menu(Messages.getString("AlignmentAnimationWindow.39"));
        MenuItem edit3DItem = new MenuItem("Edit...");
        edit3D.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                edit3D();
            }

            final AlignmentAnimationWindow this$0;

            
            {
                this$0 = AlignmentAnimationWindow.this;
                super();
            }
        });
        edit3D.add(edit3DItem);
        menuBar.add(edit3D);
    }

    protected void initialiseCostMenuItem()
    {
        Menu cost = new Menu(Messages.getString("AlignmentAnimationWindow.29"));
        MenuItem editCost = new MenuItem(Messages.getString("AlignmentAnimationWindow.30"));
        editCost.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                editCost();
            }

            final AlignmentAnimationWindow this$0;

            
            {
                this$0 = AlignmentAnimationWindow.this;
                super();
            }
        });
        cost.add(editCost);
        menuBar.add(cost);
    }

    protected void initialiseMenuBar()
    {
        menuBar = new MenuBar();
        setMenuBar(menuBar);
        initialiseAlgorithmMenuItem();
        initialiseModeSelectionMenuItem();
        initialiseDataSelectionMenuItem();
        initialiseVariationMenuItem();
        initialiseCostMenuItem();
        initialiseHelpMenuItem();
    }

    protected void initialiseVariationMenuItem()
    {
        RadioMenu variationMethod = new RadioMenu(VARIATION, true);
        Alignment alignment = (Alignment)(Alignment)getAlgorithm();
        variationMethods.addElement(new VariationMethod(FIRST, true, alignment, applet.getCodeBase().toString(), 1, AlignmentApplet.FIRST_FILE_NAME, AlignmentApplet.FIRST_EXPLANATION_NAME, this, getAlgorithmWindow()));
        if(Alignment.runningMode != 3)
            variationMethods.addElement(new VariationMethod(SECOND, false, alignment, applet.getCodeBase().toString(), 2, AlignmentApplet.SECOND_FILE_NAME, AlignmentApplet.SECOND_EXPLANATION_NAME, this, getAlgorithmWindow()));
        for(int i = 0; i < variationMethods.size(); i++)
            variationMethod.add((MenuItem)variationMethods.elementAt(i));

        menuBar.add(variationMethod);
    }

    public void resetVariationButtons()
    {
        for(int i = 0; i < variationMethods.size(); i++)
            ((VariationMethod)variationMethods.elementAt(i)).setState(false);

    }

    private static final long serialVersionUID = 0x7d5c8f152b175253L;
    protected static final String FRAME_TITLE = Messages.getString("AlignmentAnimationWindow.0");
    protected static final String VARIATION = Messages.getString("AlignmentAnimationWindow.1");
    protected static final String FIRST = Messages.getString("AlignmentAnimationWindow.2");
    protected static final String SECOND = Messages.getString("AlignmentAnimationWindow.3");
    public static final String GLOBAL_DIST_NOGAP_DATA[] = {
        "writers", "vintner"
    };
    public static final String GLOBAL_DIST_GAP_DATA[] = {
        "abcqdew", "zxvcdel"
    };
    public static final String GLOBAL_SIM_NOGAP_DATA[] = {
        "writers", "vintner"
    };
    public static final String GLOBAL_SIM_GAP_DATA[] = {
        "abcqdew", "zxvcdel"
    };
    public static final String LOCAL_SIM_NOGAP_DATA[] = {
        "abcxdex", "zzzcdey"
    };
    public static final String LOCAL_SIM_GAP_DATA[] = {
        "praxabcst", "xyaxbacsl"
    };
    public static final String GAP_DEFAULT_DATA[] = {
        "abcqdew", "zxvcdel"
    };
    public static final String GLOBAL_DEFAULT_DATA[] = {
        "writers", "vintner"
    };
    public static final String LOCAL_DEFAULT_DATA[] = {
        "abcxdex", "yyycdey"
    };
    public static final int MAX_LENGTH = 9;
    public static final int MIN_LENGTH = 3;
    protected PresetStringArrayDataSelection ds1;
    protected PresetStringArrayDataSelection ds2;
    protected PresetStringArrayDataSelection ds3;
    protected PresetStringArrayDataSelection ds4;
    protected PresetStringArrayDataSelection ds5;
    protected PresetStringArrayDataSelection ds6;
    protected Vector variationMethods;

}
