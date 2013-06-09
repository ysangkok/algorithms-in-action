import java.awt.event.*;
import localization.*;
import java.awt.*;
import com.cim.common.*;
import java.applet.*;
import java.util.*;
import com.cim.AIA.*;

public class ShellSortAnimationWindow extends AnimationWindow
{
    private static final long serialVersionUID = 8532643583836625136L;
    protected static final String BUILD_DATE = "Build Date";
    protected static final String BUILD_BY = "Your Initial.Your Surname";
    protected static final String FRAME_TITLE;
    public static final String COMPARISON;
    public static final String H_SUBFILES;
    public static final String SINGLE_PASS;
    public static final int[] DEFAULT_DATA;
    public static final int[] SORTED_DATA;
    public static final int[] REVERSE_SORTED_DATA;
    protected Vector<ComparisonOrder> comparisonOrders;
    protected int minimumNumberOfElements;
    protected int maximumNumberOfElements;
    protected int maxSize;
    protected int minSize;
    
    public String getAlgorithmName() {
        return "AIA: ShellSort";
    }
    
    public ShellSortAnimationWindow(final AlgorithmCanvas canvas, final AlgorithmThread thread, final Applet applet) {
        super(canvas, thread, applet);
        this.comparisonOrders = new Vector();
        this.minimumNumberOfElements = 3;
        this.maximumNumberOfElements = 20;
        this.maxSize = 99;
        this.minSize = 1;
        this.buildDate = "Build Date";
        this.buildBy = "Your Initial.Your Surname";
        this.frameTitle = ShellSortAnimationWindow.FRAME_TITLE;
        this.addDataSelection(new PresetIntArrayDataSelection(ShellSortAnimationWindow.DEFAULT_LABEL, true, this, ShellSortAnimationWindow.DEFAULT_DATA));
        this.addDataSelection(new PresetIntArrayDataSelection(ShellSortAnimationWindow.SORTED_LABEL, false, this, ShellSortAnimationWindow.SORTED_DATA));
        this.addDataSelection(new PresetIntArrayDataSelection(ShellSortAnimationWindow.REVERSE_SORTED_LABEL, false, this, ShellSortAnimationWindow.REVERSE_SORTED_DATA));
        this.addDataSelection(new RandomSameIntArrayDataSelection(ShellSortAnimationWindow.SAME_LABEL, false, this, this.minimumNumberOfElements, this.maximumNumberOfElements, this.minSize, this.maxSize));
        this.addDataSelection(new RandomIntArrayDataSelection(ShellSortAnimationWindow.RANDOM_LABEL, false, this, this.minimumNumberOfElements, this.maximumNumberOfElements, this.minSize, this.maxSize));
        this.addDataSelection(new UserSelectionIntArrayDataSelection(ShellSortAnimationWindow.USER_SELECTION_LABEL + "...", false, this, ShellSortAnimationWindow.USER_SELECTION_LABEL, this.minSize, this.maxSize, this.minimumNumberOfElements, this.maximumNumberOfElements));
        this.addControlButton(new StepButton(AnimationWindow.STEP_LABEL, AnimationWindow.STEP_INSTRUCTIONS, this, thread));
        this.addControlButton(new BackButton(AnimationWindow.BACK_LABEL, AnimationWindow.BACK_INSTRUCTIONS, this, thread));
        this.addControlButton(new RunButton(AnimationWindow.RUN_LABEL, AnimationWindow.RUN_INSTRUCTIONS, this, thread));
        this.addControlButton(new PauseButton(AnimationWindow.PAUSE_LABEL, AnimationWindow.PAUSE_INSTRUCTIONS, this, thread));
        this.addControlButton(new ResetButton(AnimationWindow.RESET_LABEL, AnimationWindow.RESET_INSTRUCTIONS, this, thread));
    }
    
    protected void initialiseComparisonMenuItem() {
        final RadioMenu comparisonOrder = new RadioMenu(ShellSortAnimationWindow.COMPARISON, true);
        final ShellSort shellSort = (ShellSort)((ShellSort)this.getAlgorithm());
        this.comparisonOrders.addElement(new ComparisonOrder(ShellSortAnimationWindow.H_SUBFILES, true, shellSort, this.applet.getCodeBase().toString(), 10, ShellSortApplet.HSubfileComparisonFileName, this, this.getAlgorithmWindow()));
        this.comparisonOrders.addElement(new ComparisonOrder(ShellSortAnimationWindow.SINGLE_PASS, false, shellSort, this.applet.getCodeBase().toString(), 11, ShellSortApplet.SinglePassComparisonFileName, this, this.getAlgorithmWindow()));
        for (int i = 0; i < this.comparisonOrders.size(); ++i) {
            comparisonOrder.add((MenuItem)this.comparisonOrders.elementAt(i));
        }
        this.menuBar.add(comparisonOrder);
    }
    
    protected void initialiseMenuBar() {
        this.menuBar = new MenuBar();
        this.setMenuBar(this.menuBar);
        this.initialiseAlgorithmMenuItem();
        this.initialiseModeSelectionMenuItem();
        this.initialiseDataSelectionMenuItem();
        this.initialiseComparisonMenuItem();
        this.initialiseHelpMenuItem();
    }
    
    public void resetComparisonButtons() {
        for (int i = 0; i < this.comparisonOrders.size(); ++i) {
            ((ComparisonOrder)this.comparisonOrders.elementAt(i)).setState(false);
        }
    }
    
    static {
        FRAME_TITLE = Messages.getString("ShellSortAnimationWindow.2");
        COMPARISON = Messages.getString("ShellSortAnimationWindow.3");
        H_SUBFILES = Messages.getString("ShellSortAnimationWindow.4");
        SINGLE_PASS = Messages.getString("ShellSortAnimationWindow.5");
        DEFAULT_DATA = new int[] { 75, 5, 70, 10, 65, 15, 60, 20, 55, 25, 50, 30, 45, 35, 40 };
        SORTED_DATA = new int[] { 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75 };
        REVERSE_SORTED_DATA = new int[] { 75, 70, 65, 60, 55, 50, 45, 40, 35, 30, 25, 20, 15, 10, 5 };
    }
}
