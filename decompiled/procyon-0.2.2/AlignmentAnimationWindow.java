import com.cim.common.*;
import java.awt.event.*;
import java.awt.*;
import localization.*;
import java.applet.*;
import java.util.*;
import com.cim.AIA.*;

public class AlignmentAnimationWindow extends AnimationWindow
{
    private static final long serialVersionUID = 9033252273678406227L;
    protected static final String FRAME_TITLE;
    protected static final String VARIATION;
    protected static final String FIRST;
    protected static final String SECOND;
    public static final String[] GLOBAL_DIST_NOGAP_DATA;
    public static final String[] GLOBAL_DIST_GAP_DATA;
    public static final String[] GLOBAL_SIM_NOGAP_DATA;
    public static final String[] GLOBAL_SIM_GAP_DATA;
    public static final String[] LOCAL_SIM_NOGAP_DATA;
    public static final String[] LOCAL_SIM_GAP_DATA;
    public static final String[] GAP_DEFAULT_DATA;
    public static final String[] GLOBAL_DEFAULT_DATA;
    public static final String[] LOCAL_DEFAULT_DATA;
    public static final int MAX_LENGTH = 9;
    public static final int MIN_LENGTH = 3;
    protected PresetStringArrayDataSelection ds1;
    protected PresetStringArrayDataSelection ds2;
    protected PresetStringArrayDataSelection ds3;
    protected PresetStringArrayDataSelection ds4;
    protected PresetStringArrayDataSelection ds5;
    protected PresetStringArrayDataSelection ds6;
    protected Vector<VariationMethod> variationMethods;
    
    public String getAlgorithmName() {
        return "Introduction to Alignment";
    }
    
    public AlignmentAnimationWindow(final AlgorithmCanvas canvas, final AlgorithmThread thread, final Applet applet) {
        super(canvas, thread, applet);
        this.variationMethods = new Vector();
        this.frameTitle = AlignmentAnimationWindow.FRAME_TITLE;
        this.ds1 = new PresetStringArrayDataSelection(Messages.getString("AlignmentAnimationWindow.22"), Alignment.runningMode == 1, this, AlignmentAnimationWindow.GLOBAL_DIST_NOGAP_DATA);
        this.ds2 = new PresetStringArrayDataSelection(Messages.getString("AlignmentAnimationWindow.23"), false, this, AlignmentAnimationWindow.GLOBAL_DIST_GAP_DATA);
        this.ds3 = new PresetStringArrayDataSelection(Messages.getString("AlignmentAnimationWindow.24"), Alignment.runningMode == 2, this, AlignmentAnimationWindow.GLOBAL_SIM_NOGAP_DATA);
        this.ds4 = new PresetStringArrayDataSelection(Messages.getString("AlignmentAnimationWindow.25"), false, this, AlignmentAnimationWindow.GLOBAL_SIM_GAP_DATA);
        this.ds5 = new PresetStringArrayDataSelection(Messages.getString("AlignmentAnimationWindow.26"), Alignment.runningMode == 3, this, AlignmentAnimationWindow.LOCAL_SIM_NOGAP_DATA);
        this.ds6 = new PresetStringArrayDataSelection(Messages.getString("AlignmentAnimationWindow.27"), false, this, AlignmentAnimationWindow.LOCAL_SIM_GAP_DATA);
        this.addDataSelection(this.ds1);
        this.addDataSelection(this.ds2);
        this.addDataSelection(this.ds3);
        this.addDataSelection(this.ds4);
        this.addDataSelection(this.ds5);
        this.addDataSelection(this.ds6);
        this.addDataSelection(new UserSelectionStringDataSelection(AlignmentAnimationWindow.USER_SELECTION_LABEL + "...", false, this, AlignmentAnimationWindow.USER_SELECTION_LABEL, 3, 9));
        this.addControlButton(new StepButton(AnimationWindow.STEP_LABEL, AnimationWindow.STEP_INSTRUCTIONS, this, thread));
        this.addControlButton(new BackButton(AnimationWindow.BACK_LABEL, AnimationWindow.BACK_INSTRUCTIONS, this, thread));
        this.addControlButton(new RunButton(AnimationWindow.RUN_LABEL, AnimationWindow.RUN_INSTRUCTIONS, this, thread));
        this.addControlButton(new PauseButton(AnimationWindow.PAUSE_LABEL, AnimationWindow.PAUSE_LABEL, this, thread));
        this.addControlButton(new ResetButton(AnimationWindow.RESET_LABEL, AnimationWindow.RESET_INSTRUCTIONS, this, thread));
        this.selfTestMode.setEnabled(false);
    }
    
    protected void edit3D() {
        final Alignment alignment = (Alignment)((Alignment)this.getAlgorithm());
        final Alignment3DEditDialog dialog = new Alignment3DEditDialog("Rotation parameters", alignment);
        dialog.setVisible(true);
    }
    
    protected void editCost() {
        final Alignment alignment = (Alignment)((Alignment)this.getAlgorithm());
        final String[] array;
        final String[] costs = array = new String[5];
        array[0] = alignment.getMatchCost() + "";
        costs[1] = alignment.getMismatchCost() + "";
        costs[2] = alignment.getSpaceCost() + "";
        costs[3] = alignment.getInitialGapCost() + "";
        costs[4] = alignment.getRunningGapCost() + "";
        final AlignmentCostDialog dialog = new AlignmentCostDialog(AlignmentCostDialog.DEFAULT_TITLE, costs, -10, 10, Alignment.currentVariation);
        dialog.setVisible(true);
        final int[] newCost = dialog.getData();
        if (newCost.length == 5) {
            alignment.setMatchCost(newCost[0]);
            alignment.setMismatchCost(newCost[1]);
            alignment.setSpaceCost(newCost[2]);
            alignment.setInitialGapCost(newCost[3]);
            alignment.setRunningGapCost(newCost[4]);
            this.resetThread(true, true, false, false);
        }
        final StringBuffer datastr = new StringBuffer();
        datastr.append("[");
        for (int i = 0; i < newCost.length; ++i) {
            if (i > 0) {
                datastr.append(",");
            }
            datastr.append(newCost[i]);
        }
        datastr.append("]");
        final Log log = new Log(4, 22, null, datastr.toString());
        if (this.getLogger() != null) {
            this.getLogger().addLog(log);
        }
    }
    
    protected void initialise3DMenuItem() {
        final Menu edit3D = new Menu(Messages.getString("AlignmentAnimationWindow.39"));
        final MenuItem edit3DItem = new MenuItem("Edit...");
        edit3D.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                AlignmentAnimationWindow.this.edit3D();
            }
        });
        edit3D.add(edit3DItem);
        this.menuBar.add(edit3D);
    }
    
    protected void initialiseCostMenuItem() {
        final Menu cost = new Menu(Messages.getString("AlignmentAnimationWindow.29"));
        final MenuItem menuItem;
        final MenuItem editCost = menuItem = new MenuItem(Messages.getString("AlignmentAnimationWindow.30"));
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                AlignmentAnimationWindow.this.editCost();
            }
        });
        cost.add(editCost);
        this.menuBar.add(cost);
    }
    
    protected void initialiseMenuBar() {
        this.menuBar = new MenuBar();
        this.setMenuBar(this.menuBar);
        this.initialiseAlgorithmMenuItem();
        this.initialiseModeSelectionMenuItem();
        this.initialiseDataSelectionMenuItem();
        this.initialiseVariationMenuItem();
        this.initialiseCostMenuItem();
        this.initialiseHelpMenuItem();
    }
    
    protected void initialiseVariationMenuItem() {
        final RadioMenu variationMethod = new RadioMenu(AlignmentAnimationWindow.VARIATION, true);
        final Alignment alignment = (Alignment)((Alignment)this.getAlgorithm());
        this.variationMethods.addElement(new VariationMethod(AlignmentAnimationWindow.FIRST, true, alignment, this.applet.getCodeBase().toString(), 1, AlignmentApplet.FIRST_FILE_NAME, AlignmentApplet.FIRST_EXPLANATION_NAME, this, this.getAlgorithmWindow()));
        if (Alignment.runningMode != 3) {
            this.variationMethods.addElement(new VariationMethod(AlignmentAnimationWindow.SECOND, false, alignment, this.applet.getCodeBase().toString(), 2, AlignmentApplet.SECOND_FILE_NAME, AlignmentApplet.SECOND_EXPLANATION_NAME, this, this.getAlgorithmWindow()));
        }
        for (int i = 0; i < this.variationMethods.size(); ++i) {
            variationMethod.add((MenuItem)this.variationMethods.elementAt(i));
        }
        this.menuBar.add(variationMethod);
    }
    
    public void resetVariationButtons() {
        for (int i = 0; i < this.variationMethods.size(); ++i) {
            ((VariationMethod)this.variationMethods.elementAt(i)).setState(false);
        }
    }
    
    static {
        FRAME_TITLE = Messages.getString("AlignmentAnimationWindow.0");
        VARIATION = Messages.getString("AlignmentAnimationWindow.1");
        FIRST = Messages.getString("AlignmentAnimationWindow.2");
        SECOND = Messages.getString("AlignmentAnimationWindow.3");
        GLOBAL_DIST_NOGAP_DATA = new String[] { "writers", "vintner" };
        GLOBAL_DIST_GAP_DATA = new String[] { "abcqdew", "zxvcdel" };
        GLOBAL_SIM_NOGAP_DATA = new String[] { "writers", "vintner" };
        GLOBAL_SIM_GAP_DATA = new String[] { "abcqdew", "zxvcdel" };
        LOCAL_SIM_NOGAP_DATA = new String[] { "abcxdex", "zzzcdey" };
        LOCAL_SIM_GAP_DATA = new String[] { "praxabcst", "xyaxbacsl" };
        GAP_DEFAULT_DATA = new String[] { "abcqdew", "zxvcdel" };
        GLOBAL_DEFAULT_DATA = new String[] { "writers", "vintner" };
        LOCAL_DEFAULT_DATA = new String[] { "abcxdex", "yyycdey" };
    }
}
