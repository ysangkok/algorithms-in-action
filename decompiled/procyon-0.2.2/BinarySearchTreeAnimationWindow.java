import localization.*;
import java.applet.*;
import com.cim.AIA.*;

public class BinarySearchTreeAnimationWindow extends AnimationWindow
{
    private static final long serialVersionUID = -8803789268810674726L;
    protected static final String BUILD_DATE = "Build Date";
    protected static final String BUILD_BY = "Your Initial.Your Surname";
    protected static final String FRAME_TITLE;
    protected static final int NUMBER_OF_ELEMENTS = 10;
    private static final int MINIMUM_OF_ELEMENTS = 5;
    private static final int MAXIMUM_OF_ELEMENTS = 20;
    public static final int[] DEFAULT_DATA;
    public static final int[] SORTED_DATA;
    protected static final int MAX_SIZE = 99;
    protected static final int MIN_SIZE = 1;
    
    public String getAlgorithmName() {
        return "AIA: BinarySearchTree";
    }
    
    public BinarySearchTreeAnimationWindow(final AlgorithmCanvas algorithmCanvas, final AlgorithmThread algorithmThread, final Applet applet) {
        super(algorithmCanvas, algorithmThread, applet);
        this.buildDate = "Build Date";
        this.buildBy = "Your Initial.Your Surname";
        this.frameTitle = BinarySearchTreeAnimationWindow.FRAME_TITLE;
        final PresetIntArrayDataSelection defaultData = new PresetIntArrayDataSelection(BinarySearchTreeAnimationWindow.DEFAULT_LABEL, true, this, BinarySearchTreeAnimationWindow.DEFAULT_DATA);
        this.addDataSelection(defaultData);
        final RandomIntArrayDataSelection randomData = new RandomIntArrayDataSelection(BinarySearchTreeAnimationWindow.RANDOM_LABEL, false, this, 5, 20, 1, 99);
        this.addDataSelection(randomData);
        final PresetIntArrayDataSelection sortedData = new PresetIntArrayDataSelection(BinarySearchTreeAnimationWindow.SORTED_LABEL, false, this, BinarySearchTreeAnimationWindow.SORTED_DATA);
        this.addDataSelection(sortedData);
        final UserSelectionIntArrayDataSelection userData = new UserSelectionIntArrayDataSelection(BinarySearchTreeAnimationWindow.USER_SELECTION_LABEL + "...", false, this, BinarySearchTreeAnimationWindow.USER_SELECTION_LABEL, 1, 99, 5, 20);
        this.addDataSelection(userData);
        this.addControlButton(new StepButton(AnimationWindow.STEP_LABEL, AnimationWindow.STEP_INSTRUCTIONS, this, algorithmThread));
        BinarySearchTree.backButton = new NewBackButton(AnimationWindow.BACK_LABEL, AnimationWindow.BACK_INSTRUCTIONS, this, algorithmThread);
        this.addControlButton(BinarySearchTree.backButton);
        this.addControlButton(new RunButton(AnimationWindow.RUN_LABEL, AnimationWindow.RUN_INSTRUCTIONS, this, algorithmThread));
        this.addControlButton(new PauseButton(AnimationWindow.PAUSE_LABEL, AnimationWindow.PAUSE_INSTRUCTIONS, this, algorithmThread));
        this.addControlButton(new ResetButton(AnimationWindow.RESET_LABEL, AnimationWindow.RESET_INSTRUCTIONS, this, algorithmThread));
        final RestartButton restartButton = new RestartButton(Messages.getString("BinarySearchTreeAnimationWindow.4"), Messages.getString("BinarySearchTreeAnimationWindow.5"), this, algorithmThread);
        restartButton.setReinitialiseAlgorithm(false);
        restartButton.setClearAlgorithmState(false);
        restartButton.setStoreAlgorithmState(true);
        restartButton.setForceAlgorithmStore(true);
        this.addControlButton(restartButton);
        this.addControlListener((ControlListener)((BinarySearchTreeThread)algorithmThread).getAlgorithm());
    }
    
    static {
        FRAME_TITLE = Messages.getString("BinarySearchTreeAnimationWindow.2");
        DEFAULT_DATA = new int[] { 50, 25, 75, 12, 37, 62, 87, 6, 18, 31, 43, 56, 68, 81, 93 };
        SORTED_DATA = new int[10];
        for (int i = 0; i < 10; ++i) {
            BinarySearchTreeAnimationWindow.SORTED_DATA[i] = i * 5 + 1;
        }
    }
}
