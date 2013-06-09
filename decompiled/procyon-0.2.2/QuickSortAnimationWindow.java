import java.awt.event.*;
import com.cim.common.*;
import java.awt.*;
import localization.*;
import java.applet.*;
import java.util.*;
import com.cim.AIA.*;

public class QuickSortAnimationWindow extends AnimationWindow
{
    private static final long serialVersionUID = 7836354882428936517L;
    protected static final String BUILD_DATE = "1/10/1999";
    protected static final String BUILD_BY = "T.Witham";
    protected static final String FRAME_TITLE;
    public static final String PARTITION;
    public static final String RIGHT_PARTITION;
    public static final String RANDOM_PARTITION;
    public static final String MIDDLE_OF_THREE_RANDOM_PARTITION;
    public static final String MIDDLE_OF_THREE_PARTITION;
    public static final int[] DEFAULT_DATA;
    public static final int[] SORTED_DATA;
    public static final int[] REVERSE_SORTED_DATA;
    protected Vector<PartitionMethod> partitionMethods;
    protected int minimumNumberOfElements;
    protected int maximumNumberOfElements;
    protected int maxSize;
    protected int minSize;
    
    public String getAlgorithmName() {
        return "AIA: Quicksort";
    }
    
    public QuickSortAnimationWindow(final AlgorithmCanvas canvas, final AlgorithmThread thread, final Applet applet) {
        super(canvas, thread, applet);
        this.partitionMethods = new Vector();
        this.minimumNumberOfElements = 3;
        this.maximumNumberOfElements = 20;
        this.maxSize = 99;
        this.minSize = 1;
        this.buildDate = "1/10/1999";
        this.buildBy = "T.Witham";
        this.frameTitle = QuickSortAnimationWindow.FRAME_TITLE;
        this.addDataSelection(new PresetIntArrayDataSelection(QuickSortAnimationWindow.DEFAULT_LABEL, true, this, QuickSortAnimationWindow.DEFAULT_DATA));
        this.addDataSelection(new PresetIntArrayDataSelection(QuickSortAnimationWindow.SORTED_LABEL, false, this, QuickSortAnimationWindow.SORTED_DATA));
        this.addDataSelection(new PresetIntArrayDataSelection(QuickSortAnimationWindow.REVERSE_SORTED_LABEL, false, this, QuickSortAnimationWindow.REVERSE_SORTED_DATA));
        this.addDataSelection(new RandomSameIntArrayDataSelection(QuickSortAnimationWindow.SAME_LABEL, false, this, this.minimumNumberOfElements, this.maximumNumberOfElements, this.minSize, this.maxSize));
        this.addDataSelection(new RandomIntArrayDataSelection(QuickSortAnimationWindow.RANDOM_LABEL, false, this, this.minimumNumberOfElements, this.maximumNumberOfElements, this.minSize, this.maxSize));
        this.addDataSelection(new UserSelectionIntArrayDataSelection(QuickSortAnimationWindow.USER_SELECTION_LABEL + "...", false, this, QuickSortAnimationWindow.USER_SELECTION_LABEL, this.minSize, this.maxSize, this.minimumNumberOfElements, this.maximumNumberOfElements));
        this.addControlButton(new StepButton(AnimationWindow.STEP_LABEL, AnimationWindow.STEP_INSTRUCTIONS, this, thread));
        this.addControlButton(new BackButton(AnimationWindow.BACK_LABEL, AnimationWindow.BACK_INSTRUCTIONS, this, thread));
        this.addControlButton(new RunButton(AnimationWindow.RUN_LABEL, AnimationWindow.RUN_INSTRUCTIONS, this, thread));
        this.addControlButton(new PauseButton(AnimationWindow.PAUSE_LABEL, AnimationWindow.PAUSE_INSTRUCTIONS, this, thread));
        this.addControlButton(new ResetButton(AnimationWindow.RESET_LABEL, AnimationWindow.RESET_INSTRUCTIONS, this, thread));
        this.addModeSelection(new QuizModeSelection(QuickSortAnimationWindow.QUIZ_MODE_LABEL + "...", false, this, thread, applet, Messages.getString("QuickSortAnimationWindow.6"), "quicksort.quiz", applet.getCodeBase().toString() + "images/", "correct.gif", "incorrect.gif"));
    }
    
    protected void initialiseMenuBar() {
        this.menuBar = new MenuBar();
        this.setMenuBar(this.menuBar);
        this.initialiseAlgorithmMenuItem();
        this.initialiseModeSelectionMenuItem();
        this.initialiseDataSelectionMenuItem();
        this.initialisePartitionMenuItem();
        this.initialiseHelpMenuItem();
    }
    
    protected void initialisePartitionMenuItem() {
        final RadioMenu partitionMethod = new RadioMenu(QuickSortAnimationWindow.PARTITION, true);
        final QuickSort quickSort = (QuickSort)((QuickSort)this.getAlgorithm());
        this.partitionMethods.addElement(new PartitionMethod(QuickSortAnimationWindow.RIGHT_PARTITION, true, quickSort, this.applet.getCodeBase().toString(), 10, QuickSortApplet.RightPartitionFileName, this, this.getAlgorithmWindow()));
        this.partitionMethods.addElement(new PartitionMethod(QuickSortAnimationWindow.RANDOM_PARTITION, false, quickSort, this.applet.getCodeBase().toString(), 11, QuickSortApplet.RandomPartitionFileName, this, this.getAlgorithmWindow()));
        this.partitionMethods.addElement(new PartitionMethod(QuickSortAnimationWindow.MIDDLE_OF_THREE_RANDOM_PARTITION, false, quickSort, this.applet.getCodeBase().toString(), 12, QuickSortApplet.MiddleOf3RandomPartitionFileName, this, this.getAlgorithmWindow()));
        this.partitionMethods.addElement(new PartitionMethod(QuickSortAnimationWindow.MIDDLE_OF_THREE_PARTITION, false, quickSort, this.applet.getCodeBase().toString(), 13, QuickSortApplet.MiddleOf3PartitionFileName, this, this.getAlgorithmWindow()));
        for (int i = 0; i < this.partitionMethods.size(); ++i) {
            partitionMethod.add((MenuItem)this.partitionMethods.elementAt(i));
        }
        partitionMethod.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                String cmd = e.paramString();
                final int i1 = cmd.indexOf("cmd=") + 4;
                final int i2 = cmd.indexOf(",", i1);
                cmd = cmd.substring(i1, i2);
                final Log l1 = new Log(4, 21, null, cmd);
                if (QuickSortAnimationWindow.this.getLogger() != null) {
                    QuickSortAnimationWindow.this.getLogger().addLog(l1);
                }
                if (Logger.DEBUG) {
                    System.err.println("Partition method action listener called.");
                    System.err.println("paramString=" + e.paramString());
                    System.err.println("cmd=" + cmd);
                }
            }
        });
        this.menuBar.add(partitionMethod);
    }
    
    public void resetPartitionButtons() {
        for (int i = 0; i < this.partitionMethods.size(); ++i) {
            ((PartitionMethod)this.partitionMethods.elementAt(i)).setState(false);
        }
    }
    
    public void resetThread() {
        ((QuickSortThread)this.thread).resetSeed();
        super.resetThread();
    }
    
    public void setNormalMode() {
        this.informModeListeners(new ModeEvent(this, 12356));
        this.thread.setHandleQuestions(false);
    }
    
    static {
        FRAME_TITLE = Messages.getString("QuickSortAnimationWindow.0");
        PARTITION = Messages.getString("QuickSortAnimationWindow.1");
        RIGHT_PARTITION = Messages.getString("QuickSortAnimationWindow.2");
        RANDOM_PARTITION = Messages.getString("QuickSortAnimationWindow.3");
        MIDDLE_OF_THREE_RANDOM_PARTITION = Messages.getString("QuickSortAnimationWindow.4");
        MIDDLE_OF_THREE_PARTITION = Messages.getString("QuickSortAnimationWindow.5");
        DEFAULT_DATA = new int[] { 75, 5, 70, 10, 65, 15, 60, 20, 55, 25, 50, 30, 45, 35, 40 };
        SORTED_DATA = new int[] { 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75 };
        REVERSE_SORTED_DATA = new int[] { 75, 70, 65, 60, 55, 50, 45, 40, 35, 30, 25, 20, 15, 10, 5 };
    }
}
