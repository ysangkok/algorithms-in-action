import java.awt.*;
import localization.*;
import java.applet.*;
import java.awt.event.*;
import com.cim.AIA.*;

public class SplayTreeIterAnimationWindow extends AnimationWindow implements ItemListener
{
    protected static final String BUILD_DATE = "Build Date";
    protected static final String BUILD_BY = "Your Initial.Your Surname";
    protected static final String FRAME_TITLE;
    public static final int[] DEFAULT_DATA;
    public static final int[] SORTED_DATA;
    public static final String SORTED_LABAL;
    private static final int MINIMUM_NUMBER_OF_ELEMENTS = 5;
    private static final int MAXIMUM_NUMBER_OF_ELEMENTS = 20;
    private static final int MIN_SIZE = 3;
    private static final int MAX_SIZE = 99;
    
    public String getAlgorithmName() {
        return "AIA: SplayTree";
    }
    
    public SplayTreeIterAnimationWindow(final AlgorithmCanvas canvas, final AlgorithmThread thread, final Applet applet) {
        super(canvas, thread, applet);
        this.buildDate = "Build Date";
        this.buildBy = "Your Initial.Your Surname";
        this.frameTitle = SplayTreeIterAnimationWindow.FRAME_TITLE;
        this.addControlButton(new StepButton(AnimationWindow.STEP_LABEL, AnimationWindow.STEP_INSTRUCTIONS, this, thread));
        this.addControlButton(new BackButton(AnimationWindow.BACK_LABEL, AnimationWindow.BACK_INSTRUCTIONS, this, thread));
        this.addControlButton(new RunButton(AnimationWindow.RUN_LABEL, AnimationWindow.RUN_INSTRUCTIONS, this, thread));
        this.addControlButton(new PauseButton(AnimationWindow.PAUSE_LABEL, AnimationWindow.PAUSE_INSTRUCTIONS, this, thread));
        this.addControlButton(new ResetButton(AnimationWindow.RESET_LABEL, AnimationWindow.RESET_INSTRUCTIONS, this, thread));
        final RestartButton restartButton = new RestartButton(Messages.getString("SplayTreeIterAnimationWindow.4"), Messages.getString("SplayTreeIterAnimationWindow.5"), this, thread);
        restartButton.setReinitialiseAlgorithm(false);
        restartButton.setClearAlgorithmState(false);
        restartButton.setStoreAlgorithmState(true);
        restartButton.setForceAlgorithmStore(true);
        this.addControlButton(restartButton);
        final Checkbox animateCheckbox = new Checkbox(Messages.getString("SplayTreeIterAnimationWindow.6"), true);
        animateCheckbox.addItemListener(this);
        this.controlPanel.add(animateCheckbox);
        final PresetIntArrayDataSelection defaultData = new PresetIntArrayDataSelection(SplayTreeIterAnimationWindow.DEFAULT_LABEL, true, this, SplayTreeIterAnimationWindow.DEFAULT_DATA);
        defaultData.setReinitialiseAlgorithm(true);
        defaultData.setClearAlgorithmState(true);
        this.addDataSelection(defaultData);
        final PresetIntArrayDataSelection sortedData = new PresetIntArrayDataSelection(SplayTreeIterAnimationWindow.SORTED_LABEL, false, this, SplayTreeIterAnimationWindow.SORTED_DATA);
        defaultData.setReinitialiseAlgorithm(true);
        defaultData.setClearAlgorithmState(true);
        this.addDataSelection(sortedData);
        final RandomIntArrayDataSelection randomData = new RandomIntArrayDataSelection(SplayTreeIterAnimationWindow.RANDOM_LABEL, false, this, 5, 20, 3, 99);
        randomData.setReinitialiseAlgorithm(true);
        randomData.setClearAlgorithmState(true);
        this.addDataSelection(randomData);
        final UserSelectionIntArrayDataSelection userData = new UserSelectionIntArrayDataSelection(SplayTreeIterAnimationWindow.USER_SELECTION_LABEL + "...", false, this, SplayTreeIterAnimationWindow.USER_SELECTION_LABEL, 3, 99, 5, 20);
        userData.setReinitialiseAlgorithm(true);
        userData.setClearAlgorithmState(true);
        this.addDataSelection(userData);
        this.addControlListener((ControlListener)((SplayTreeIterThread)thread).getAlgorithm());
    }
    
    public void itemStateChanged(final ItemEvent e) {
        if (e.getStateChange() == 1) {
            ((SplayTreeIterCanvas)this.canvas).setIsShowTween(true);
        }
        else if (e.getStateChange() == 2) {
            ((SplayTreeIterCanvas)this.canvas).setIsShowTween(false);
        }
    }
    
    static {
        FRAME_TITLE = Messages.getString("SplayTreeIterAnimationWindow.2");
        DEFAULT_DATA = new int[] { 4, 7, 15, 1, 30, 10, 1, 2, 3 };
        SORTED_DATA = new int[] { 4, 7, 8, 10, 13, 15, 16, 17, 18, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 1, 5, 6, 9, 14, 19, 3 };
        SORTED_LABAL = Messages.getString("SplayTreeIterAnimationWindow.3");
    }
}
