import java.applet.*;
import com.cim.AIA.*;

public class StraightRadixSortAnimationWindow extends AnimationWindow
{
    private static final long serialVersionUID = 5645322659574813897L;
    protected static final String BUILD_DATE = "1/10/1999";
    protected static final String BUILD_BY = "T.Witham";
    protected static final String FRAME_TITLE = "AIA: Straight Radix Sort";
    public static final int MINIMUM_NUMBER_OF_ELEMENTS = 3;
    public static final int MAXIMUM_NUMBER_OF_ELEMENTS = 20;
    public static final int MAX_SIZE = 15;
    public static final int MIN_SIZE = 0;
    
    public String getAlgorithmName() {
        return "AIA: Straight Radix Sort";
    }
    
    public StraightRadixSortAnimationWindow(final AlgorithmCanvas canvas, final AlgorithmThread thread, final Applet applet) {
        super(canvas, thread, applet);
        this.buildDate = "1/10/1999";
        this.buildBy = "T.Witham";
        this.frameTitle = "AIA: Straight Radix Sort";
        this.addDataSelection(new RandomIntArrayDataSelection(StraightRadixSortAnimationWindow.RANDOM_LABEL, true, this, 3, 20, 0, 15));
        this.addDataSelection(new UserSelectionIntArrayDataSelection(StraightRadixSortAnimationWindow.USER_SELECTION_LABEL + "...", false, this, StraightRadixSortAnimationWindow.USER_SELECTION_LABEL, 0, 15, 3, 20));
        this.addControlButton(new StepButton(AnimationWindow.STEP_LABEL, AnimationWindow.STEP_INSTRUCTIONS, this, thread));
        this.addControlButton(new BackButton(AnimationWindow.BACK_LABEL, AnimationWindow.BACK_INSTRUCTIONS, this, thread));
        this.addControlButton(new RunButton(AnimationWindow.RUN_LABEL, AnimationWindow.RUN_INSTRUCTIONS, this, thread));
        this.addControlButton(new PauseButton(AnimationWindow.PAUSE_LABEL, AnimationWindow.PAUSE_INSTRUCTIONS, this, thread));
        this.addControlButton(new ResetButton(AnimationWindow.RESET_LABEL, AnimationWindow.RESET_INSTRUCTIONS, this, thread));
        this.selfTestMode.setEnabled(false);
    }
}
