import localization.*;
import java.applet.*;
import com.cim.AIA.*;

public class RadixExchangeSortAnimationWindow extends AnimationWindow
{
    private static final long serialVersionUID = 5946860209465611136L;
    protected static final String BUILD_DATE = "25/05/1999";
    protected static final String BUILD_BY = "T.Witham";
    protected static final String FRAME_TITLE;
    public static final int[] DEFAULT_DATA;
    public static final int[] SORTED_DATA;
    public static final int[] REVERSE_SORTED_DATA;
    protected int minimumNumberOfElements;
    protected int maximumNumberOfElements;
    protected int minSize;
    protected int maxSize;
    
    public String getAlgorithmName() {
        return "AIA: Radix Exchange Sort";
    }
    
    public RadixExchangeSortAnimationWindow(final AlgorithmCanvas canvas, final AlgorithmThread thread, final Applet applet) {
        super(canvas, thread, applet);
        this.minimumNumberOfElements = 3;
        this.maximumNumberOfElements = 20;
        this.minSize = 1;
        this.maxSize = 99;
        this.buildDate = "25/05/1999";
        this.buildBy = "T.Witham";
        this.frameTitle = RadixExchangeSortAnimationWindow.FRAME_TITLE;
        this.addDataSelection(new PresetIntArrayDataSelection(RadixExchangeSortAnimationWindow.DEFAULT_LABEL, true, this, RadixExchangeSortAnimationWindow.DEFAULT_DATA));
        this.addDataSelection(new PresetIntArrayDataSelection(RadixExchangeSortAnimationWindow.SORTED_LABEL, false, this, RadixExchangeSortAnimationWindow.SORTED_DATA));
        this.addDataSelection(new PresetIntArrayDataSelection(RadixExchangeSortAnimationWindow.REVERSE_SORTED_LABEL, false, this, RadixExchangeSortAnimationWindow.REVERSE_SORTED_DATA));
        this.addDataSelection(new RandomSameIntArrayDataSelection(RadixExchangeSortAnimationWindow.SAME_LABEL, false, this, this.minimumNumberOfElements, this.maximumNumberOfElements, this.minSize, this.maxSize));
        this.addDataSelection(new RandomIntArrayDataSelection(RadixExchangeSortAnimationWindow.RANDOM_LABEL, false, this, this.minimumNumberOfElements, this.maximumNumberOfElements, this.minSize, this.maxSize));
        this.addDataSelection(new UserSelectionIntArrayDataSelection(RadixExchangeSortAnimationWindow.USER_SELECTION_LABEL + "...", false, this, RadixExchangeSortAnimationWindow.USER_SELECTION_LABEL, this.minSize, this.maxSize, this.minimumNumberOfElements, this.maximumNumberOfElements));
        this.addControlButton(new StepButton(AnimationWindow.STEP_LABEL, AnimationWindow.STEP_INSTRUCTIONS, this, thread));
        this.addControlButton(new BackButton(AnimationWindow.BACK_LABEL, AnimationWindow.BACK_INSTRUCTIONS, this, thread));
        this.addControlButton(new RunButton(AnimationWindow.RUN_LABEL, AnimationWindow.RUN_INSTRUCTIONS, this, thread));
        this.addControlButton(new PauseButton(AnimationWindow.PAUSE_LABEL, AnimationWindow.PAUSE_INSTRUCTIONS, this, thread));
        this.addControlButton(new ResetButton(AnimationWindow.RESET_LABEL, AnimationWindow.RESET_INSTRUCTIONS, this, thread));
        this.selfTestMode.setEnabled(false);
    }
    
    static {
        FRAME_TITLE = Messages.getString("RadixExchangeSortAnimationWindow.2");
        DEFAULT_DATA = new int[] { 75, 5, 70, 10, 65, 15, 60, 20, 55, 25, 50, 30, 45, 35, 40 };
        SORTED_DATA = new int[] { 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75 };
        REVERSE_SORTED_DATA = new int[] { 75, 70, 65, 60, 55, 50, 45, 40, 35, 30, 25, 20, 15, 10, 5 };
    }
}
