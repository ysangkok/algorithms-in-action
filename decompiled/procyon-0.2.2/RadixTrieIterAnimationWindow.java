import localization.*;
import java.applet.*;
import com.cim.AIA.*;

public class RadixTrieIterAnimationWindow extends AnimationWindow
{
    protected static final String BUILD_DATE = "Build Date";
    protected static final String BUILD_BY = "Your Initial.Your Surname";
    protected static final String FRAME_TITLE;
    public static final int[] DEFAULT_DATA;
    private static final int MINIMUM_NUMBER_OF_ELEMENTS = 5;
    private static final int MAXIMUM_NUMBER_OF_ELEMENTS = 20;
    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 99;
    
    public String getAlgorithmName() {
        return "AIA: RadixTrie";
    }
    
    public RadixTrieIterAnimationWindow(final AlgorithmCanvas canvas, final AlgorithmThread thread, final Applet applet) {
        super(canvas, thread, applet);
        this.buildDate = "Build Date";
        this.buildBy = "Your Initial.Your Surname";
        this.frameTitle = RadixTrieIterAnimationWindow.FRAME_TITLE;
        this.addControlButton(new StepButton(AnimationWindow.STEP_LABEL, AnimationWindow.STEP_INSTRUCTIONS, this, thread));
        this.addControlButton(new BackButton(AnimationWindow.BACK_LABEL, AnimationWindow.BACK_INSTRUCTIONS, this, thread));
        this.addControlButton(new RunButton(AnimationWindow.RUN_LABEL, AnimationWindow.RUN_INSTRUCTIONS, this, thread));
        this.addControlButton(new PauseButton(AnimationWindow.PAUSE_LABEL, AnimationWindow.PAUSE_INSTRUCTIONS, this, thread));
        this.addControlButton(new ResetButton(AnimationWindow.RESET_LABEL, AnimationWindow.RESET_INSTRUCTIONS, this, thread));
        final RestartButton restartButton = new RestartButton(Messages.getString("RadixTrieIterAnimationWindow.3"), Messages.getString("RadixTrieIterAnimationWindow.4"), this, thread);
        restartButton.setReinitialiseAlgorithm(false);
        restartButton.setClearAlgorithmState(false);
        restartButton.setStoreAlgorithmState(true);
        restartButton.setForceAlgorithmStore(true);
        this.addControlButton(restartButton);
        final PresetIntArrayDataSelection defaultData = new PresetIntArrayDataSelection(RadixTrieIterAnimationWindow.DEFAULT_LABEL, true, this, RadixTrieIterAnimationWindow.DEFAULT_DATA);
        defaultData.setReinitialiseAlgorithm(true);
        defaultData.setClearAlgorithmState(true);
        this.addDataSelection(defaultData);
        final RandomIntArrayDataSelection randomData = new RandomIntArrayDataSelection(RadixTrieIterAnimationWindow.RANDOM_LABEL, false, this, 5, 20, 1, 99);
        randomData.setReinitialiseAlgorithm(true);
        randomData.setClearAlgorithmState(true);
        this.addDataSelection(randomData);
        final UserSelectionIntArrayDataSelection userData = new UserSelectionIntArrayDataSelection(RadixTrieIterAnimationWindow.USER_SELECTION_LABEL + "...", false, this, RadixTrieIterAnimationWindow.USER_SELECTION_LABEL, 1, 99, 5, 20);
        userData.setReinitialiseAlgorithm(true);
        userData.setClearAlgorithmState(true);
        this.addDataSelection(userData);
        this.addControlListener((ControlListener)((RadixTrieIterThread)thread).getAlgorithm());
    }
    
    static {
        FRAME_TITLE = Messages.getString("RadixTrieIterAnimationWindow.2");
        DEFAULT_DATA = new int[] { 1, 21, 20, 16, 17, 31, 30, 15 };
    }
}
