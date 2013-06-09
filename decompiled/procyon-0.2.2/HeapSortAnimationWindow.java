import localization.*;
import java.awt.event.*;
import com.cim.common.*;
import java.applet.*;
import java.awt.*;
import com.cim.AIA.*;

public class HeapSortAnimationWindow extends AnimationWindow
{
    private static final long serialVersionUID = -8859365854964303907L;
    protected static final String BUILD_DATE = "25/05/1999";
    protected static final String BUILD_BY = "T.Witham";
    public static final String FRAME_TITLE;
    public static final int MINIMUM_NUMBER_OF_ELEMENTS = 3;
    public static final int MAXIMUM_NUMBER_OF_ELEMENTS = 20;
    public static final int MAX_SIZE = 99;
    public static final int MIN_SIZE = 1;
    public static final int[] DEFAULT_DATA;
    public static final int[] SORTED_DATA;
    public static final int[] REVERSE_SORTED_DATA;
    protected static final String DATA_LABEL;
    protected static final String PREDICITION_DIFFICULTY_LABEL;
    protected static final String EASY_LABEL;
    protected static final String HARD_LABEL;
    protected CheckboxMenuItem easy;
    protected CheckboxMenuItem hard;
    protected HeapSortCanvas heapSortCanvas;
    protected boolean easyMode;
    
    public String getAlgorithmName() {
        return "AIA: Heapsort";
    }
    
    public HeapSortAnimationWindow(final AlgorithmCanvas canvas, final AlgorithmThread thread, final Applet applet) {
        super(canvas, thread, applet);
        this.easyMode = true;
        this.buildDate = "25/05/1999";
        this.buildBy = "T.Witham";
        this.frameTitle = HeapSortAnimationWindow.FRAME_TITLE;
        this.dataMenu.setLabel(HeapSortAnimationWindow.DATA_LABEL);
        this.addDataSelection(new PresetIntArrayDataSelection(HeapSortAnimationWindow.SORTED_LABEL, false, this, HeapSortAnimationWindow.SORTED_DATA));
        this.addDataSelection(new PresetIntArrayDataSelection(HeapSortAnimationWindow.REVERSE_SORTED_LABEL, false, this, HeapSortAnimationWindow.REVERSE_SORTED_DATA));
        this.addDataSelection(new RandomSameIntArrayDataSelection(HeapSortAnimationWindow.SAME_LABEL, false, this, 3, 20, 1, 99));
        this.addDataSelection(new RandomIntArrayDataSelection(HeapSortAnimationWindow.RANDOM_LABEL, true, this, 3, 20, 1, 99));
        this.addDataSelection(new UserSelectionIntArrayDataSelection(HeapSortAnimationWindow.USER_SELECTION_LABEL + "...", false, this, HeapSortAnimationWindow.USER_SELECTION_LABEL, 1, 99, 3, 20));
        this.addControlButton(new StepButton(AnimationWindow.STEP_LABEL, AnimationWindow.STEP_INSTRUCTIONS, this, thread));
        this.addControlButton(new BackButton(AnimationWindow.BACK_LABEL, AnimationWindow.BACK_INSTRUCTIONS, this, thread));
        this.addControlButton(new RunButton(AnimationWindow.RUN_LABEL, AnimationWindow.RUN_INSTRUCTIONS, this, thread));
        this.addControlButton(new PauseButton(AnimationWindow.PAUSE_LABEL, AnimationWindow.PAUSE_INSTRUCTIONS, this, thread));
        this.addControlButton(new ResetButton(AnimationWindow.RESET_LABEL, AnimationWindow.RESET_INSTRUCTIONS, this, thread));
    }
    
    protected void easyMode() {
        this.easyMode = true;
        this.hard.setState(!this.easyMode);
        this.easy.setState(this.easyMode);
        if (this.selfTestMode.getState()) {
            this.heapSortCanvas.setMarkersEnabled(this.easyMode);
        }
    }
    
    protected void hardMode() {
        this.easyMode = false;
        this.hard.setState(!this.easyMode);
        this.easy.setState(this.easyMode);
        if (this.selfTestMode.getState()) {
            this.heapSortCanvas.setMarkersEnabled(this.easyMode);
        }
    }
    
    protected void initialiseMenuBar() {
        this.menuBar = new MenuBar();
        this.setMenuBar(this.menuBar);
        this.initialiseAlgorithmMenuItem();
        this.initialiseModeSelectionMenuItem();
        this.initialiseQuestionLevel();
        this.initialiseDataSelectionMenuItem();
        this.initialiseHelpMenuItem();
    }
    
    protected void initialiseQuestionLevel() {
        final RadioMenu radioDifficultyMenu = new RadioMenu(HeapSortAnimationWindow.PREDICITION_DIFFICULTY_LABEL);
        this.easy = new CheckboxMenuItem(HeapSortAnimationWindow.EASY_LABEL, this.easyMode);
        this.hard = new CheckboxMenuItem(HeapSortAnimationWindow.HARD_LABEL, !this.easyMode);
        this.heapSortCanvas = (HeapSortCanvas)((HeapSortCanvas)this.canvas);
        this.easy.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent e) {
                HeapSortAnimationWindow.this.easyMode();
            }
        });
        this.hard.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent e) {
                HeapSortAnimationWindow.this.hardMode();
            }
        });
        this.selfTestMode.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent e) {
                HeapSortAnimationWindow.this.heapSortCanvas.setMarkersEnabled(HeapSortAnimationWindow.this.easyMode);
            }
        });
        if (this.selfTestMode.getState()) {
            this.heapSortCanvas.setMarkersEnabled(this.easyMode);
        }
        radioDifficultyMenu.add(this.easy);
        radioDifficultyMenu.add(this.hard);
        this.modeMenu.addSeparator();
        this.modeMenu.add(radioDifficultyMenu);
    }
    
    public void setNormalMode() {
        this.informModeListeners(new ModeEvent(this, 12356));
        ((HeapSortCanvas)((HeapSortCanvas)this.canvas)).setMarkersEnabled(true);
        this.thread.setHandleQuestions(false);
    }
    
    static {
        FRAME_TITLE = Messages.getString("HeapSortAnimationWindow.2");
        DEFAULT_DATA = new int[] { 5, 95, 10, 90, 15, 85, 20, 80, 25, 75, 30, 70, 35, 65, 40 };
        SORTED_DATA = new int[] { 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75 };
        REVERSE_SORTED_DATA = new int[] { 75, 70, 65, 60, 55, 50, 45, 40, 35, 30, 25, 20, 15, 10, 5 };
        DATA_LABEL = Messages.getString("HeapSortAnimationWindow.3");
        PREDICITION_DIFFICULTY_LABEL = Messages.getString("HeapSortAnimationWindow.4");
        EASY_LABEL = Messages.getString("HeapSortAnimationWindow.5");
        HARD_LABEL = Messages.getString("HeapSortAnimationWindow.6");
    }
}
