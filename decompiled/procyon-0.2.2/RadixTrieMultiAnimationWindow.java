import java.awt.event.*;
import com.cim.common.*;
import localization.*;
import java.applet.*;
import java.awt.*;
import com.cim.AIA.*;

public class RadixTrieMultiAnimationWindow extends AnimationWindow
{
    protected static final String BUILD_DATE = "Build Date";
    protected static final String BUILD_BY = "Your Initial.Your Surname";
    protected static final String FRAME_TITLE;
    public static final int[] DEFAULT_DATA;
    private static final int MINIMUM_NUMBER_OF_ELEMENTS = 5;
    private static final int MAXIMUM_NUMBER_OF_ELEMENTS = 20;
    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 99;
    CheckboxMenuItem mnu1Bit;
    CheckboxMenuItem mnu2Bit;
    CheckboxMenuItem mnu3Bit;
    
    public String getAlgorithmName() {
        return "AIA: RadixTrieMulti";
    }
    
    public RadixTrieMultiAnimationWindow(final AlgorithmCanvas canvas, final AlgorithmThread thread, final Applet applet) {
        super(canvas, thread, applet);
        this.mnu1Bit = new CheckboxMenuItem("1 Bit");
        this.mnu2Bit = new CheckboxMenuItem("2 Bit");
        this.mnu3Bit = new CheckboxMenuItem("3 Bit");
        this.buildDate = "Build Date";
        this.buildBy = "Your Initial.Your Surname";
        this.frameTitle = RadixTrieMultiAnimationWindow.FRAME_TITLE;
        this.addControlButton(new StepButton(AnimationWindow.STEP_LABEL, AnimationWindow.STEP_INSTRUCTIONS, this, thread));
        this.addControlButton(new BackButton(AnimationWindow.BACK_LABEL, AnimationWindow.BACK_INSTRUCTIONS, this, thread));
        this.addControlButton(new RunButton(AnimationWindow.RUN_LABEL, AnimationWindow.RUN_INSTRUCTIONS, this, thread));
        this.addControlButton(new PauseButton(AnimationWindow.PAUSE_LABEL, AnimationWindow.PAUSE_INSTRUCTIONS, this, thread));
        this.addControlButton(new ResetButton(AnimationWindow.RESET_LABEL, AnimationWindow.RESET_INSTRUCTIONS, this, thread));
        final RestartButton restartButton = new RestartButton(Messages.getString("RadixTrieMultiAnimationWindow.1"), Messages.getString("RadixTrieMultiAnimationWindow.2"), this, thread);
        restartButton.setReinitialiseAlgorithm(false);
        restartButton.setClearAlgorithmState(false);
        restartButton.setStoreAlgorithmState(true);
        restartButton.setForceAlgorithmStore(true);
        this.addControlButton(restartButton);
        final PresetIntArrayDataSelection defaultData = new PresetIntArrayDataSelection(RadixTrieMultiAnimationWindow.DEFAULT_LABEL, true, this, RadixTrieMultiAnimationWindow.DEFAULT_DATA);
        defaultData.setReinitialiseAlgorithm(true);
        defaultData.setClearAlgorithmState(true);
        this.addDataSelection(defaultData);
        final RandomIntArrayDataSelection randomData = new RandomIntArrayDataSelection(RadixTrieMultiAnimationWindow.RANDOM_LABEL, false, this, 5, 20, 1, 99);
        randomData.setReinitialiseAlgorithm(true);
        randomData.setClearAlgorithmState(true);
        this.addDataSelection(randomData);
        final UserSelectionIntArrayDataSelection userData = new UserSelectionIntArrayDataSelection(RadixTrieMultiAnimationWindow.USER_SELECTION_LABEL + "...", false, this, RadixTrieMultiAnimationWindow.USER_SELECTION_LABEL, 1, 99, 5, 20);
        userData.setReinitialiseAlgorithm(true);
        userData.setClearAlgorithmState(true);
        this.addDataSelection(userData);
        this.addControlListener((ControlListener)((RadixTrieMultiThread)thread).getAlgorithm());
    }
    
    protected void initialiseMenuBar() {
        this.menuBar = new MenuBar();
        this.setMenuBar(this.menuBar);
        this.initialiseAlgorithmMenuItem();
        this.initialiseModeSelectionMenuItem();
        this.initialiseDataSelectionMenuItem();
        this.initialiseDataBitsMenuItem();
        this.initialiseHelpMenuItem();
    }
    
    protected void initialiseDataBitsMenuItem() {
        RadixTrieMulti.mnuLink = new RadioMenu(Messages.getString("RadixTrieMultiAnimationWindow.3"), true);
        this.mnu1Bit.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent e) {
                RadixTrieMultiAnimationWindow.this.oneBit();
            }
        });
        this.mnu2Bit.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent e) {
                RadixTrieMultiAnimationWindow.this.twoBit();
            }
        });
        this.mnu3Bit.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent e) {
                RadixTrieMultiAnimationWindow.this.threeBit();
            }
        });
        this.mnu2Bit.setState(true);
        RadixTrieMulti.mnuLink.add(this.mnu1Bit);
        RadixTrieMulti.mnuLink.add(this.mnu2Bit);
        RadixTrieMulti.mnuLink.add(this.mnu3Bit);
        this.menuBar.add(RadixTrieMulti.mnuLink);
    }
    
    protected void oneBit() {
        this.mnu2Bit.setState(false);
        this.mnu3Bit.setState(false);
        this.resetThread(true, true, false, false);
        RadixTrieMulti.NO_OF_NODES = 2;
        RadixTrieMulti.NO_OF_BITS = 1;
        this.resetThread(true, true, false, false);
    }
    
    protected void twoBit() {
        this.mnu1Bit.setState(false);
        this.mnu3Bit.setState(false);
        this.resetThread(true, true, false, false);
        RadixTrieMulti.NO_OF_NODES = 4;
        RadixTrieMulti.NO_OF_BITS = 2;
        this.resetThread(true, true, false, false);
    }
    
    protected void threeBit() {
        this.mnu1Bit.setState(false);
        this.mnu2Bit.setState(false);
        this.resetThread(true, true, false, false);
        RadixTrieMulti.NO_OF_NODES = 8;
        RadixTrieMulti.NO_OF_BITS = 3;
        this.resetThread(true, true, false, false);
    }
    
    static {
        FRAME_TITLE = Messages.getString("RadixTrieMultiAnimationWindow.0");
        DEFAULT_DATA = new int[] { 1, 21, 20, 16, 17, 31, 30, 15 };
    }
}
