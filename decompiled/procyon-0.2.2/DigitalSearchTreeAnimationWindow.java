import localization.*;
import java.applet.*;
import com.cim.AIA.*;

public class DigitalSearchTreeAnimationWindow extends AnimationWindow
{
    protected static final String BUILD_DATE;
    protected static final String BUILD_BY;
    protected static final String FRAME_TITLE;
    protected static final int NUMBER_OF_ELEMENTS = 10;
    private static final int MINIMUM_NUMBER_OF_ELEMENTS = 5;
    private static final int MAXIMUM_NUMBER_OF_ELEMENTS = 31;
    public static final int[] DEFAULT_DATA;
    public static final int[] SORTED_DATA;
    public static final int[] SKEWED_DATA;
    public static final int[] EXPONENTIAL_DATA;
    public static final String EXPONENTIAL_LABEL;
    public static final String SKEWED_LABEL;
    protected static final int MAX_SIZE = 99;
    protected static final int MIN_SIZE = 1;
    
    public String getAlgorithmName() {
        return "AIA: DigitalSearchTree";
    }
    
    public DigitalSearchTreeAnimationWindow(final AlgorithmCanvas algorithmCanvas, final AlgorithmThread algorithmThread, final Applet applet) {
        super(algorithmCanvas, algorithmThread, applet);
        this.buildDate = DigitalSearchTreeAnimationWindow.BUILD_DATE;
        this.buildBy = DigitalSearchTreeAnimationWindow.BUILD_BY;
        this.frameTitle = DigitalSearchTreeAnimationWindow.FRAME_TITLE;
        final PresetIntArrayDataSelection defaultData = new PresetIntArrayDataSelection(DigitalSearchTreeAnimationWindow.DEFAULT_LABEL, true, this, DigitalSearchTreeAnimationWindow.DEFAULT_DATA);
        defaultData.setReinitialiseAlgorithm(false);
        defaultData.setClearAlgorithmState(true);
        this.addDataSelection(defaultData);
        final RandomIntArrayDataSelection randomData = new RandomIntArrayDataSelection(DigitalSearchTreeAnimationWindow.RANDOM_LABEL, false, this, 5, 31, 1, 99);
        randomData.setReinitialiseAlgorithm(false);
        randomData.setClearAlgorithmState(true);
        this.addDataSelection(randomData);
        final PresetIntArrayDataSelection sortedData = new PresetIntArrayDataSelection(DigitalSearchTreeAnimationWindow.SORTED_LABEL, false, this, DigitalSearchTreeAnimationWindow.SORTED_DATA);
        sortedData.setReinitialiseAlgorithm(false);
        sortedData.setClearAlgorithmState(true);
        this.addDataSelection(sortedData);
        final PresetIntArrayDataSelection skewedData = new PresetIntArrayDataSelection(DigitalSearchTreeAnimationWindow.SKEWED_LABEL, false, this, DigitalSearchTreeAnimationWindow.SKEWED_DATA);
        skewedData.setReinitialiseAlgorithm(false);
        skewedData.setClearAlgorithmState(true);
        this.addDataSelection(skewedData);
        final PresetIntArrayDataSelection exponentialData = new PresetIntArrayDataSelection(DigitalSearchTreeAnimationWindow.EXPONENTIAL_LABEL, false, this, DigitalSearchTreeAnimationWindow.EXPONENTIAL_DATA);
        exponentialData.setReinitialiseAlgorithm(false);
        exponentialData.setClearAlgorithmState(true);
        this.addDataSelection(exponentialData);
        final UserSelectionIntArrayDataSelection userData = new UserSelectionIntArrayDataSelection(DigitalSearchTreeAnimationWindow.USER_SELECTION_LABEL + "...", false, this, DigitalSearchTreeAnimationWindow.USER_SELECTION_LABEL, 1, 99, 5, 31);
        userData.setReinitialiseAlgorithm(false);
        userData.setClearAlgorithmState(true);
        this.addDataSelection(userData);
        this.addControlButton(new StepButton(AnimationWindow.STEP_LABEL, AnimationWindow.STEP_INSTRUCTIONS, this, algorithmThread));
        this.addControlButton(new BackButton(AnimationWindow.BACK_LABEL, AnimationWindow.BACK_INSTRUCTIONS, this, algorithmThread));
        this.addControlButton(new RunButton(AnimationWindow.RUN_LABEL, AnimationWindow.RUN_INSTRUCTIONS, this, algorithmThread));
        this.addControlButton(new PauseButton(AnimationWindow.PAUSE_LABEL, AnimationWindow.PAUSE_INSTRUCTIONS, this, algorithmThread));
        this.addControlButton(new ResetButton(AnimationWindow.RESET_LABEL, AnimationWindow.RESET_INSTRUCTIONS, this, algorithmThread));
        final RestartButton restartButton = new RestartButton(Messages.getString("DigitalSearchTreeAnimationWindow.6"), Messages.getString("DigitalSearchTreeAnimationWindow.7"), this, algorithmThread);
        restartButton.setReinitialiseAlgorithm(false);
        restartButton.setClearAlgorithmState(false);
        restartButton.setStoreAlgorithmState(true);
        restartButton.setForceAlgorithmStore(true);
        this.addControlButton(restartButton);
        this.addControlListener((ControlListener)((DigitalSearchTreeThread)algorithmThread).getAlgorithm());
    }
    
    static {
        BUILD_DATE = Messages.getString("DigitalSearchTreeAnimationWindow.0");
        BUILD_BY = Messages.getString("DigitalSearchTreeAnimationWindow.1");
        FRAME_TITLE = Messages.getString("DigitalSearchTreeAnimationWindow.2");
        DEFAULT_DATA = new int[] { 50, 25, 75, 12, 37, 62, 87, 6, 18, 31, 43, 56, 68, 81, 93 };
        SORTED_DATA = new int[31];
        SKEWED_DATA = new int[] { 57, 61, 54, 53, 49, 56, 52, 62, 58, 55, 48, 60, 50, 59, 51, 63 };
        EXPONENTIAL_DATA = new int[10];
        EXPONENTIAL_LABEL = Messages.getString("DigitalSearchTreeAnimationWindow.3");
        SKEWED_LABEL = Messages.getString("DigitalSearchTreeAnimationWindow.4");
        for (int i = 0; i < 31; ++i) {
            DigitalSearchTreeAnimationWindow.SORTED_DATA[i] = i + 1;
        }
        final int number = 1;
        for (int j = 0; j < 10; ++j) {
            DigitalSearchTreeAnimationWindow.EXPONENTIAL_DATA[j] = number << 10 - j - 1;
        }
    }
}
