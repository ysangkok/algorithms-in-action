import localization.*;
import java.applet.*;
import com.cim.AIA.*;

public class PatriciaTreeAnimationWindow extends AnimationWindow
{
    private static final long serialVersionUID = -3663749469815675406L;
    protected static final String BUILD_DATE = "Build Date";
    protected static final String BUILD_BY = "Your Initial.Your Surname";
    protected static final String FRAME_TITLE;
    private static final int MINIMUM_NUMBER_OF_ELEMENTS = 5;
    private static final int MAXIMUM_NUMBER_OF_ELEMENTS = 30;
    private static final int MIN_SIZE = 3;
    private static final int MAX_SIZE = 99;
    public static final int[] DEFAULT_DATA;
    
    public String getAlgorithmName() {
        return "AIA: PatriciaTree";
    }
    
    public PatriciaTreeAnimationWindow(final AlgorithmCanvas canvas, final AlgorithmThread thread, final Applet applet) {
        super(canvas, thread, applet);
        this.buildDate = "Build Date";
        this.buildBy = "Your Initial.Your Surname";
        this.frameTitle = PatriciaTreeAnimationWindow.FRAME_TITLE;
        this.addControlButton(new StepButton(AnimationWindow.STEP_LABEL, AnimationWindow.STEP_INSTRUCTIONS, this, thread));
        final BackButton back = new BackButton(AnimationWindow.BACK_LABEL, AnimationWindow.BACK_INSTRUCTIONS, this, thread);
        this.addControlButton(back);
        this.addControlButton(new RunButton(AnimationWindow.RUN_LABEL, AnimationWindow.RUN_INSTRUCTIONS, this, thread));
        this.addControlButton(new PauseButton(AnimationWindow.PAUSE_LABEL, AnimationWindow.PAUSE_INSTRUCTIONS, this, thread));
        this.addControlButton(new ResetButton(AnimationWindow.RESET_LABEL, AnimationWindow.RESET_INSTRUCTIONS, this, thread));
        final RestartButton restart = new RestartButton(Messages.getString("PatriciaTreeAnimationWindow.1"), Messages.getString("PatriciaTreeAnimationWindow.0"), this, thread);
        restart.setReinitialiseAlgorithm(false);
        restart.setClearAlgorithmState(false);
        restart.setStoreAlgorithmState(true);
        restart.setForceAlgorithmStore(true);
        this.addControlButton(restart);
        this.addControlListener((ControlListener)((PatriciaTreeThread)thread).getAlgorithm());
        final PresetIntArrayDataSelection defaultData = new PresetIntArrayDataSelection(PatriciaTreeAnimationWindow.DEFAULT_LABEL, true, this, PatriciaTreeAnimationWindow.DEFAULT_DATA);
        defaultData.setReinitialiseAlgorithm(false);
        defaultData.setClearAlgorithmState(true);
        this.addDataSelection(defaultData);
        final RandomIntArrayDataSelection randomData = new RandomIntArrayDataSelection(PatriciaTreeAnimationWindow.RANDOM_LABEL, false, this, 5, 30, 3, 99);
        randomData.setReinitialiseAlgorithm(false);
        randomData.setClearAlgorithmState(true);
        this.addDataSelection(randomData);
        final UserSelectionIntArrayDataSelection userData = new UserSelectionIntArrayDataSelection(PatriciaTreeAnimationWindow.USER_SELECTION_LABEL + "...", false, this, PatriciaTreeAnimationWindow.USER_SELECTION_LABEL, 3, 99, 5, 30);
        userData.setReinitialiseAlgorithm(false);
        userData.setClearAlgorithmState(true);
        this.addDataSelection(userData);
    }
    
    static {
        FRAME_TITLE = Messages.getString("PatriciaTreeAnimationWindow.2");
        DEFAULT_DATA = new int[] { 1, 31, 30, 28, 27, 21, 24, 23, 20, 17, 19, 25, 18, 29, 26, 2, 4, 5 };
    }
}
