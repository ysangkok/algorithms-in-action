import localization.*;
import java.awt.*;
import java.applet.*;
import java.util.*;
import com.cim.AIA.*;

public class MergeSortAnimationWindow extends AnimationWindow
{
    protected static final String BUILD_DATE = "15/2/2007";
    protected static final String BUILD_BY = "M. Tabbara";
    protected static final String FRAME_TITLE;
    protected Vector<E> partitionMethods;
    protected int minimumNumberOfElements;
    protected int maximumNumberOfElements;
    protected int maxSize;
    protected int minSize;
    public static final int[] DEFAULT_DATA;
    public static final int[] SORTED_DATA;
    public static final int[] REVERSE_SORTED_DATA;
    
    public String getAlgorithmName() {
        return "AIA: Mergesort";
    }
    
    public MergeSortAnimationWindow(final AlgorithmCanvas canvas, final AlgorithmThread thread, final Applet applet) {
        super(canvas, thread, applet);
        this.partitionMethods = new Vector();
        this.minimumNumberOfElements = 3;
        this.maximumNumberOfElements = 20;
        this.maxSize = 99;
        this.minSize = 1;
        this.buildDate = "15/2/2007";
        this.buildBy = "M. Tabbara";
        this.frameTitle = MergeSortAnimationWindow.FRAME_TITLE;
        this.addDataSelection(new PresetIntArrayDataSelection(MergeSortAnimationWindow.DEFAULT_LABEL, true, this, MergeSortAnimationWindow.DEFAULT_DATA));
        this.addDataSelection(new PresetIntArrayDataSelection(MergeSortAnimationWindow.SORTED_LABEL, false, this, MergeSortAnimationWindow.SORTED_DATA));
        this.addDataSelection(new PresetIntArrayDataSelection(MergeSortAnimationWindow.REVERSE_SORTED_LABEL, false, this, MergeSortAnimationWindow.REVERSE_SORTED_DATA));
        this.addDataSelection(new RandomSameIntArrayDataSelection(MergeSortAnimationWindow.SAME_LABEL, false, this, this.minimumNumberOfElements, this.maximumNumberOfElements, this.minSize, this.maxSize));
        this.addDataSelection(new RandomIntArrayDataSelection(MergeSortAnimationWindow.RANDOM_LABEL, false, this, this.minimumNumberOfElements, this.maximumNumberOfElements, this.minSize, this.maxSize));
        this.addDataSelection(new UserSelectionIntArrayDataSelection(MergeSortAnimationWindow.USER_SELECTION_LABEL + "...", false, this, MergeSortAnimationWindow.USER_SELECTION_LABEL, this.minSize, this.maxSize, this.minimumNumberOfElements, this.maximumNumberOfElements));
        this.addControlButton(new StepButton(AnimationWindow.STEP_LABEL, AnimationWindow.STEP_INSTRUCTIONS, this, thread));
        this.addControlButton(new BackButton(AnimationWindow.BACK_LABEL, AnimationWindow.BACK_INSTRUCTIONS, this, thread));
        this.addControlButton(new RunButton(AnimationWindow.RUN_LABEL, AnimationWindow.RUN_INSTRUCTIONS, this, thread));
        this.addControlButton(new PauseButton(AnimationWindow.PAUSE_LABEL, AnimationWindow.PAUSE_INSTRUCTIONS, this, thread));
        this.addControlButton(new ResetButton(AnimationWindow.RESET_LABEL, AnimationWindow.RESET_INSTRUCTIONS, this, thread));
    }
    
    public void setNormalMode() {
        this.informModeListeners(new ModeEvent(this, 12356));
        this.thread.setHandleQuestions(false);
    }
    
    protected void initialiseMenuBar() {
        this.menuBar = new MenuBar();
        this.setMenuBar(this.menuBar);
        this.initialiseAlgorithmMenuItem();
        this.initialiseDataSelectionMenuItem();
        this.initialiseHelpMenuItem();
    }
    
    public void resetThread() {
        ((MergeSortThread)this.thread).resetSeed();
        super.resetThread();
    }
    
    static {
        FRAME_TITLE = Messages.getString("MergeSortAnimationWindow.2");
        DEFAULT_DATA = new int[] { 75, 5, 70, 10, 65, 15, 60, 20, 55, 25, 50, 30, 45, 35, 40 };
        SORTED_DATA = new int[] { 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75 };
        REVERSE_SORTED_DATA = new int[] { 75, 70, 65, 60, 55, 50, 45, 40, 35, 30, 25, 20, 15, 10, 5 };
    }
}
