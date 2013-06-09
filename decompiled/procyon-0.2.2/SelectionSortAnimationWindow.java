import localization.*;
import java.applet.*;
import com.cim.AIA.*;

public class SelectionSortAnimationWindow extends AnimationWindow
{
    private static final long serialVersionUID = -7476859979873128117L;
    protected static final String QUIZ_FILE_NAME = "selectionsort.quiz";
    protected static final String BUILD_DATE = "10 June 99";
    protected static final String BUILD_BY = "T.Witham";
    protected static final String FRAME_TITLE;
    public static final int[] DEFAULT_DATA;
    public static final int[] SORTED_DATA;
    public static final int[] REVERSE_SORTED_DATA;
    protected int minimumNumberOfElements;
    protected int maximumNumberOfElements;
    protected int maxSize;
    protected int minSize;
    
    public String getAlgorithmName() {
        return "AIA: SelectionSort";
    }
    
    public SelectionSortAnimationWindow(final AlgorithmCanvas canvas, final AlgorithmThread thread, final Applet applet) {
        super(canvas, thread, applet);
        this.minimumNumberOfElements = 3;
        this.maximumNumberOfElements = 20;
        this.maxSize = 99;
        this.minSize = 1;
        this.buildDate = "10 June 99";
        this.buildBy = "T.Witham";
        this.frameTitle = SelectionSortAnimationWindow.FRAME_TITLE;
        this.addModeSelection(new QuizModeSelection(SelectionSortAnimationWindow.QUIZ_MODE_LABEL + "...", false, this, thread, applet, Messages.getString("SelectionSortAnimationWindow.1"), "selectionsort.quiz", applet.getCodeBase().toString() + "images/", "correct.gif", "incorrect.gif"));
        this.addDataSelection(new PresetIntArrayDataSelection(SelectionSortAnimationWindow.DEFAULT_LABEL, true, this, SelectionSortAnimationWindow.DEFAULT_DATA));
        this.addDataSelection(new PresetIntArrayDataSelection(SelectionSortAnimationWindow.SORTED_LABEL, false, this, SelectionSortAnimationWindow.SORTED_DATA));
        this.addDataSelection(new PresetIntArrayDataSelection(SelectionSortAnimationWindow.REVERSE_SORTED_LABEL, false, this, SelectionSortAnimationWindow.REVERSE_SORTED_DATA));
        this.addDataSelection(new RandomSameIntArrayDataSelection(SelectionSortAnimationWindow.SAME_LABEL, false, this, this.minimumNumberOfElements, this.maximumNumberOfElements, this.minSize, this.maxSize));
        this.addDataSelection(new RandomIntArrayDataSelection(SelectionSortAnimationWindow.RANDOM_LABEL, false, this, this.minimumNumberOfElements, this.maximumNumberOfElements, this.minSize, this.maxSize));
        this.addDataSelection(new UserSelectionIntArrayDataSelection(SelectionSortAnimationWindow.USER_SELECTION_LABEL + "...", false, this, SelectionSortAnimationWindow.USER_SELECTION_LABEL, this.minSize, this.maxSize, this.minimumNumberOfElements, this.maximumNumberOfElements));
        this.addControlButton(new StepButton(AnimationWindow.STEP_LABEL, AnimationWindow.STEP_INSTRUCTIONS, this, thread));
        this.addControlButton(new BackButton(AnimationWindow.BACK_LABEL, AnimationWindow.BACK_INSTRUCTIONS, this, thread));
        this.addControlButton(new RunButton(AnimationWindow.RUN_LABEL, AnimationWindow.RUN_INSTRUCTIONS, this, thread));
        this.addControlButton(new PauseButton(AnimationWindow.PAUSE_LABEL, AnimationWindow.PAUSE_INSTRUCTIONS, this, thread));
        this.addControlButton(new ResetButton(AnimationWindow.RESET_LABEL, AnimationWindow.RESET_INSTRUCTIONS, this, thread));
    }
    
    static {
        FRAME_TITLE = Messages.getString("SelectionSortAnimationWindow.0");
        DEFAULT_DATA = new int[] { 40, 35, 45, 30, 50, 25, 55, 20, 60, 15, 65, 10, 70, 5, 75 };
        SORTED_DATA = new int[] { 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75 };
        REVERSE_SORTED_DATA = new int[] { 75, 70, 65, 60, 55, 50, 45, 40, 35, 30, 25, 20, 15, 10, 5 };
    }
}
