import localization.*;
import java.applet.*;
import com.cim.AIA.*;

public class BoyerMooreAnimationWindow extends AnimationWindow
{
    private static final long serialVersionUID = -3974653996398494171L;
    protected static final String FRAME_TITLE;
    public static final int MINIMUM_NUMBER_OF_ELEMENTS = 5;
    public static final int MAXIMUM_NUMBER_OF_ELEMENTS = 5;
    public static final int MAX_SIZE = 4;
    public static final int MIN_SIZE = 0;
    public static final String[] DEFAULT_DATA_1;
    public static final String[] DEFAULT_DATA_2;
    public static final String[] DEFAULT_DATA_3;
    public static final int MAX_LENGTH = 25;
    public static final int MIN_LENGTH = 3;
    protected PresetStringArrayDataSelection ds1;
    protected PresetStringArrayDataSelection ds2;
    protected PresetStringArrayDataSelection ds3;
    
    public String getAlgorithmName() {
        return "BoyerMoore String Search";
    }
    
    public BoyerMooreAnimationWindow(final AlgorithmCanvas canvas, final AlgorithmThread thread, final Applet applet) {
        super(canvas, thread, applet);
        this.frameTitle = BoyerMooreAnimationWindow.FRAME_TITLE;
        this.ds1 = new PresetStringArrayDataSelection(Messages.getString("BoyerMooreAnimationWindow.7"), true, this, BoyerMooreAnimationWindow.DEFAULT_DATA_1);
        this.ds2 = new PresetStringArrayDataSelection(Messages.getString("BoyerMooreAnimationWindow.8"), false, this, BoyerMooreAnimationWindow.DEFAULT_DATA_2);
        this.addDataSelection(this.ds1);
        this.addDataSelection(this.ds2);
        this.addDataSelection(new UserSelectionStringDataSelection(BoyerMooreAnimationWindow.USER_SELECTION_LABEL + "...", false, this, BoyerMooreAnimationWindow.USER_SELECTION_LABEL, 3, 25, Messages.getString("BoyerMooreAnimationWindow.10"), Messages.getString("BoyerMooreAnimationWindow.11"), 49));
        this.addControlButton(new StepButton(AnimationWindow.STEP_LABEL, AnimationWindow.STEP_INSTRUCTIONS, this, thread));
        this.addControlButton(new BackButton(AnimationWindow.BACK_LABEL, AnimationWindow.BACK_INSTRUCTIONS, this, thread));
        this.addControlButton(new RunButton(AnimationWindow.RUN_LABEL, AnimationWindow.RUN_INSTRUCTIONS, this, thread));
        this.addControlButton(new PauseButton(AnimationWindow.PAUSE_LABEL, AnimationWindow.PAUSE_LABEL, this, thread));
        this.addControlButton(new ResetButton(AnimationWindow.RESET_LABEL, AnimationWindow.RESET_INSTRUCTIONS, this, thread));
        this.selfTestMode.setEnabled(false);
    }
    
    static {
        FRAME_TITLE = Messages.getString("BoyerMooreAnimationWindow.0");
        DEFAULT_DATA_1 = new String[] { Messages.getString("BoyerMooreAnimationWindow.1"), Messages.getString("BoyerMooreAnimationWindow.2") };
        DEFAULT_DATA_2 = new String[] { Messages.getString("BoyerMooreAnimationWindow.3"), Messages.getString("BoyerMooreAnimationWindow.4") };
        DEFAULT_DATA_3 = new String[] { Messages.getString("BoyerMooreAnimationWindow.5"), Messages.getString("BoyerMooreAnimationWindow.6") };
    }
}
