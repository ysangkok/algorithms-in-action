import localization.*;
import java.applet.*;
import com.cim.AIA.*;

public class SkipListAnimationWindow extends AnimationWindow
{
    protected static final String BUILD_DATE = "Build Date";
    protected static final String BUILD_BY = "Your Initial.Your Surname";
    protected static final String FRAME_TITLE;
    protected static final int NUMBER_OF_ELEMENTS = 10;
    private static int minNoOfElements;
    private static int maxNoOfElements;
    protected static final int MAX_SIZE = 99;
    protected static final int MIN_SIZE = 1;
    public static final int[] DEFAULT_DATA;
    public static final int[] SORTED_DATA;
    public static final int[] REVERSE_SORTED_DATA;
    protected int minimumNumberOfElements;
    protected int maximumNumberOfElements;
    protected int maxSize;
    protected int minSize;
    
    public String getAlgorithmName() {
        return "AIA: SkipList";
    }
    
    public SkipListAnimationWindow(final AlgorithmCanvas algorithmCanvas, final AlgorithmThread algorithmThread, final Applet algorithmApplet) {
        super(algorithmCanvas, algorithmThread, algorithmApplet);
        this.minimumNumberOfElements = 1;
        this.maximumNumberOfElements = 20;
        this.maxSize = 99;
        this.minSize = 0;
        this.buildDate = "Build Date";
        this.buildBy = "Your Initial.Your Surname";
        this.frameTitle = SkipListAnimationWindow.FRAME_TITLE;
        final SkipList list = (SkipList)((SkipListThread)algorithmThread).getAlgorithm();
        SkipListAnimationWindow.minNoOfElements = 5;
        final SkipList list2 = (SkipList)((SkipListThread)algorithmThread).getAlgorithm();
        SkipListAnimationWindow.maxNoOfElements = 31;
        this.addControlButton(new StepButton(AnimationWindow.STEP_LABEL, AnimationWindow.STEP_INSTRUCTIONS, this, algorithmThread));
        this.addControlButton(new BackButton(AnimationWindow.BACK_LABEL, AnimationWindow.BACK_INSTRUCTIONS, this, algorithmThread));
        this.addControlButton(new RunButton(AnimationWindow.RUN_LABEL, AnimationWindow.RUN_INSTRUCTIONS, this, algorithmThread));
        this.addControlButton(new PauseButton(AnimationWindow.PAUSE_LABEL, AnimationWindow.PAUSE_INSTRUCTIONS, this, algorithmThread));
        this.addControlButton(new ResetButton(AnimationWindow.RESET_LABEL, AnimationWindow.RESET_INSTRUCTIONS, this, algorithmThread));
        final RestartButton restartButton = new RestartButton(Messages.getString("SkipListAnimationWindow.3"), Messages.getString("SkipListAnimationWindow.4"), this, algorithmThread);
        restartButton.setReinitialiseAlgorithm(false);
        restartButton.setClearAlgorithmState(false);
        restartButton.setStoreAlgorithmState(true);
        restartButton.setForceAlgorithmStore(true);
        this.addControlButton(restartButton);
        this.addControlListener((ControlListener)((SkipListThread)algorithmThread).getAlgorithm());
        final PresetIntArrayDataSelection defaultData = new PresetIntArrayDataSelection(SkipListAnimationWindow.DEFAULT_LABEL, true, this, SkipListAnimationWindow.DEFAULT_DATA);
        defaultData.setReinitialiseAlgorithm(false);
        defaultData.setClearAlgorithmState(true);
        this.addDataSelection(defaultData);
        final RandomIntArrayDataSelection randomData = new RandomIntArrayDataSelection(SkipListAnimationWindow.RANDOM_LABEL, false, this, SkipListAnimationWindow.minNoOfElements, SkipListAnimationWindow.maxNoOfElements, 1, 99);
        randomData.setReinitialiseAlgorithm(false);
        randomData.setClearAlgorithmState(true);
        this.addDataSelection(randomData);
        final PresetIntArrayDataSelection sortedData = new PresetIntArrayDataSelection(SkipListAnimationWindow.SORTED_LABEL, false, this, SkipListAnimationWindow.SORTED_DATA);
        sortedData.setReinitialiseAlgorithm(false);
        sortedData.setClearAlgorithmState(true);
        this.addDataSelection(sortedData);
        final UserSelectionIntArrayDataSelection userData = new UserSelectionIntArrayDataSelection(SkipListAnimationWindow.USER_SELECTION_LABEL + "...", false, this, SkipListAnimationWindow.USER_SELECTION_LABEL, 1, 99, SkipListAnimationWindow.minNoOfElements, SkipListAnimationWindow.maxNoOfElements);
        userData.setReinitialiseAlgorithm(false);
        userData.setClearAlgorithmState(true);
        this.addDataSelection(userData);
    }
    
    public void printHello() {
    }
    
    static {
        FRAME_TITLE = Messages.getString("SkipListAnimationWindow.2");
        DEFAULT_DATA = new int[] { 75, 5, 70, 10, 65, 15, 60, 20, 55, 25, 50, 30, 45, 35, 40 };
        SORTED_DATA = new int[] { 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75 };
        REVERSE_SORTED_DATA = new int[] { 75, 70, 65, 60, 55, 50, 45, 40, 35, 30, 25, 20, 15, 10, 5 };
    }
}
