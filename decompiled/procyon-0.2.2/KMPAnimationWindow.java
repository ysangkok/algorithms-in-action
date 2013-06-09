import java.awt.event.*;
import com.cim.common.*;
import java.awt.*;
import localization.*;
import java.applet.*;
import java.util.*;
import com.cim.AIA.*;

public class KMPAnimationWindow extends AnimationWindow
{
    private static final long serialVersionUID = 717328955772392850L;
    protected static final String FRAME_TITLE;
    protected static final String VARIATION;
    protected static final String FIRST;
    protected static final String SECOND;
    public static final int MINIMUM_NUMBER_OF_ELEMENTS = 5;
    public static final int MAXIMUM_NUMBER_OF_ELEMENTS = 5;
    public static final int MAX_SIZE = 4;
    public static final int MIN_SIZE = 0;
    public static final String[] DEFAULT_DATA_1;
    public static final String[] DEFAULT_DATA_2;
    public static final String[] DEFAULT_DATA_3;
    public static final int MAX_LENGTH = 12;
    public static final int MIN_LENGTH = 3;
    protected PresetStringArrayDataSelection ds1;
    protected PresetStringArrayDataSelection ds2;
    protected PresetStringArrayDataSelection ds3;
    protected Vector<VariationMethod> variationMethods;
    
    public String getAlgorithmName() {
        return "KMP String Search";
    }
    
    public KMPAnimationWindow(final AlgorithmCanvas canvas, final AlgorithmThread thread, final Applet applet) {
        super(canvas, thread, applet);
        this.variationMethods = new Vector();
        this.frameTitle = KMPAnimationWindow.FRAME_TITLE;
        this.ds1 = new PresetStringArrayDataSelection(Messages.getString("KMPAnimationWindow.10"), true, this, KMPAnimationWindow.DEFAULT_DATA_1);
        this.ds2 = new PresetStringArrayDataSelection(Messages.getString("KMPAnimationWindow.11"), false, this, KMPAnimationWindow.DEFAULT_DATA_2);
        this.ds3 = new PresetStringArrayDataSelection(Messages.getString("KMPAnimationWindow.12"), false, this, KMPAnimationWindow.DEFAULT_DATA_3);
        this.addDataSelection(this.ds1);
        this.addDataSelection(this.ds2);
        this.addDataSelection(this.ds3);
        this.addDataSelection(new UserSelectionStringDataSelection(KMPAnimationWindow.USER_SELECTION_LABEL + "...", false, this, KMPAnimationWindow.USER_SELECTION_LABEL, 3, 12, Messages.getString("KMPAnimationWindow.14"), Messages.getString("KMPAnimationWindow.15")));
        this.addControlButton(new StepButton(AnimationWindow.STEP_LABEL, AnimationWindow.STEP_INSTRUCTIONS, this, thread));
        this.addControlButton(new BackButton(AnimationWindow.BACK_LABEL, AnimationWindow.BACK_INSTRUCTIONS, this, thread));
        this.addControlButton(new RunButton(AnimationWindow.RUN_LABEL, AnimationWindow.RUN_INSTRUCTIONS, this, thread));
        this.addControlButton(new PauseButton(AnimationWindow.PAUSE_LABEL, AnimationWindow.PAUSE_LABEL, this, thread));
        this.addControlButton(new ResetButton(AnimationWindow.RESET_LABEL, AnimationWindow.RESET_INSTRUCTIONS, this, thread));
        this.selfTestMode.setEnabled(false);
    }
    
    protected void initialiseMenuBar() {
        this.menuBar = new MenuBar();
        this.setMenuBar(this.menuBar);
        this.initialiseAlgorithmMenuItem();
        this.initialiseModeSelectionMenuItem();
        this.initialiseDataSelectionMenuItem();
        this.initialiseVariationMenuItem();
        this.initialiseHelpMenuItem();
    }
    
    protected void initialiseVariationMenuItem() {
        final RadioMenu variationMethod = new RadioMenu(KMPAnimationWindow.VARIATION);
        final KMP kmp = (KMP)((KMP)this.getAlgorithm());
        this.variationMethods.addElement(new VariationMethod(KMPAnimationWindow.FIRST, true, kmp, this.applet.getCodeBase().toString(), 1, KMPApplet.FIRST_FILE_NAME, this, this.getAlgorithmWindow()));
        this.variationMethods.addElement(new VariationMethod(KMPAnimationWindow.SECOND, false, kmp, this.applet.getCodeBase().toString(), 2, KMPApplet.SECOND_FILE_NAME, this, this.getAlgorithmWindow()));
        for (int i = 0; i < this.variationMethods.size(); ++i) {
            variationMethod.add((MenuItem)this.variationMethods.elementAt(i));
        }
        this.menuBar.add(variationMethod);
    }
    
    public void resetVariationButtons() {
        for (int i = 0; i < this.variationMethods.size(); ++i) {
            ((VariationMethod)this.variationMethods.elementAt(i)).setState(false);
        }
    }
    
    static {
        FRAME_TITLE = Messages.getString("KMPAnimationWindow.0");
        VARIATION = Messages.getString("KMPAnimationWindow.1");
        FIRST = Messages.getString("KMPAnimationWindow.2");
        SECOND = Messages.getString("KMPAnimationWindow.3");
        DEFAULT_DATA_1 = new String[] { Messages.getString("KMPAnimationWindow.4"), Messages.getString("KMPAnimationWindow.5") };
        DEFAULT_DATA_2 = new String[] { Messages.getString("KMPAnimationWindow.6"), Messages.getString("KMPAnimationWindow.7") };
        DEFAULT_DATA_3 = new String[] { Messages.getString("KMPAnimationWindow.8"), Messages.getString("KMPAnimationWindow.9") };
    }
}
