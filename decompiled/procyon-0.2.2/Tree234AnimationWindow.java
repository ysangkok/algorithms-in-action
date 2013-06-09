import java.awt.event.*;
import localization.*;
import java.applet.*;
import java.awt.*;
import com.cim.AIA.*;

public class Tree234AnimationWindow extends AnimationWindow
{
    protected static final String BUILD_DATE = "28/05/99";
    protected static final String BUILD_BY = "T.Witham";
    protected static final String FRAME_TITLE;
    public static final int[] DEFAULT_DATA;
    public static final int[] SORTED_DATA;
    public static final int[] REVERSE_SORTED_DATA;
    protected int minimumNumberOfElements;
    protected int maximumNumberOfElements;
    protected int maxSize;
    protected int minSize;
    protected Dialog dialog;
    protected Tree234Canvas algorithmCanvas;
    protected HierarchyTree hierarchyTree;
    
    public String getAlgorithmName() {
        return "AIA: 234 Tree";
    }
    
    public Tree234AnimationWindow(final AlgorithmCanvas canvas, final AlgorithmThread thread, final Applet applet) {
        super(canvas, thread, applet);
        this.minimumNumberOfElements = 1;
        this.maximumNumberOfElements = 20;
        this.maxSize = 99;
        this.minSize = 0;
        this.buildDate = "28/05/99";
        this.buildBy = "T.Witham";
        this.frameTitle = Tree234AnimationWindow.FRAME_TITLE;
        this.selfTestMode.setEnabled(false);
        this.addControlButton(new StepButton(AnimationWindow.STEP_LABEL, AnimationWindow.STEP_INSTRUCTIONS, this, thread));
        this.addControlButton(new BackButton(AnimationWindow.BACK_LABEL, AnimationWindow.BACK_INSTRUCTIONS, this, thread));
        this.addControlButton(new RunButton(AnimationWindow.RUN_LABEL, AnimationWindow.RUN_INSTRUCTIONS, this, thread));
        this.addControlButton(new PauseButton(AnimationWindow.PAUSE_LABEL, AnimationWindow.PAUSE_INSTRUCTIONS, this, thread));
        final ResetButton reset = new ResetButton(AnimationWindow.RESET_LABEL, AnimationWindow.RESET_INSTRUCTIONS, this, thread);
        this.addControlButton(reset);
        final RestartButton restart = new RestartButton(Messages.getString("Tree234AnimationWindow.3"), Messages.getString("Tree234AnimationWindow.4"), this, thread);
        restart.setReinitialiseAlgorithm(false);
        restart.setClearAlgorithmState(false);
        restart.setStoreAlgorithmState(true);
        restart.setForceAlgorithmStore(true);
        this.addControlButton(restart);
        final PresetIntArrayDataSelection defaultData = new PresetIntArrayDataSelection(Tree234AnimationWindow.DEFAULT_LABEL, true, this, Tree234AnimationWindow.DEFAULT_DATA);
        defaultData.setReinitialiseAlgorithm(true);
        defaultData.setClearAlgorithmState(true);
        this.addDataSelection(defaultData);
        final PresetIntArrayDataSelection sortedData = new PresetIntArrayDataSelection(Tree234AnimationWindow.SORTED_LABEL, false, this, Tree234AnimationWindow.SORTED_DATA);
        sortedData.setReinitialiseAlgorithm(true);
        sortedData.setClearAlgorithmState(true);
        this.addDataSelection(sortedData);
        final PresetIntArrayDataSelection reverseData = new PresetIntArrayDataSelection(Tree234AnimationWindow.REVERSE_SORTED_LABEL, false, this, Tree234AnimationWindow.REVERSE_SORTED_DATA);
        reverseData.setReinitialiseAlgorithm(true);
        reverseData.setClearAlgorithmState(true);
        this.addDataSelection(reverseData);
        final RandomSameIntArrayDataSelection randomSameData = new RandomSameIntArrayDataSelection(Tree234AnimationWindow.SAME_LABEL, false, this, this.minimumNumberOfElements, this.maximumNumberOfElements, this.minSize, this.maxSize);
        randomSameData.setReinitialiseAlgorithm(true);
        randomSameData.setClearAlgorithmState(true);
        this.addDataSelection(randomSameData);
        final RandomIntArrayDataSelection randomData = new RandomIntArrayDataSelection(Tree234AnimationWindow.RANDOM_LABEL, false, this, this.minimumNumberOfElements, this.maximumNumberOfElements, this.minSize, this.maxSize);
        randomData.setReinitialiseAlgorithm(true);
        randomData.setClearAlgorithmState(true);
        this.addDataSelection(randomData);
        final UserSelectionIntArrayDataSelection userData = new UserSelectionIntArrayDataSelection(Tree234AnimationWindow.USER_SELECTION_LABEL + "...", false, this, Tree234AnimationWindow.USER_SELECTION_LABEL, this.minSize, this.maxSize, this.minimumNumberOfElements, this.maximumNumberOfElements);
        userData.setReinitialiseAlgorithm(true);
        userData.setClearAlgorithmState(true);
        this.addDataSelection(userData);
        this.addControlListener((ControlListener)((Tree234Thread)thread).getAlgorithm());
    }
    
    protected void initialiseMenuBar() {
        this.menuBar = new MenuBar();
        this.setMenuBar(this.menuBar);
        this.initialiseAlgorithmMenuItem();
        this.initialiseModeSelectionMenuItem();
        this.initialiseDataSelectionMenuItem();
        this.initialiseViewMenuItem();
        this.initialiseHelpMenuItem();
    }
    
    protected void initialiseViewMenuItem() {
        final Menu display = new Menu(Messages.getString("Tree234AnimationWindow.6"));
        final MenuItem menuItem;
        final MenuItem viewAs234Tree = menuItem = new MenuItem(Messages.getString("Tree234AnimationWindow.7"));
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                Tree234AnimationWindow.this.showAs234Tree();
            }
        });
        display.add(viewAs234Tree);
        this.menuBar.add(display);
    }
    
    protected void showAs234Tree() {
        if ((Tree234Tree)this.getAlgorithm() != null) {
            if (this.dialog == null) {
                this.dialog = new Dialog(new Frame(), Messages.getString("Tree234AnimationWindow.8"), false);
                this.dialog.setSize(400, 400);
                this.algorithmCanvas = new Tree234Canvas();
                this.algorithmCanvas.setShowAs234Only(false);
                final ScrollPane scrollPane = new ScrollPane();
                this.algorithmCanvas.setParent(scrollPane);
                scrollPane.add(this.algorithmCanvas);
                this.dialog.add(scrollPane);
                this.dialog.addWindowListener(new WindowListener() {
                    public void windowActivated(final WindowEvent e) {
                    }
                    
                    public void windowClosed(final WindowEvent e) {
                    }
                    
                    public void windowClosing(final WindowEvent e) {
                        Tree234AnimationWindow.this.dialog.setVisible(false);
                    }
                    
                    public void windowDeactivated(final WindowEvent e) {
                    }
                    
                    public void windowDeiconified(final WindowEvent e) {
                    }
                    
                    public void windowIconified(final WindowEvent e) {
                    }
                    
                    public void windowOpened(final WindowEvent e) {
                    }
                });
                this.thread.addRepaintListener(this.algorithmCanvas);
                this.thread.repaint();
            }
            this.dialog.setVisible(true);
        }
    }
    
    static {
        FRAME_TITLE = Messages.getString("Tree234AnimationWindow.2");
        DEFAULT_DATA = new int[] { 75, 5, 70, 10, 65, 15, 60, 20, 55, 25, 50, 30, 45, 35, 40 };
        SORTED_DATA = new int[] { 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75 };
        REVERSE_SORTED_DATA = new int[] { 75, 70, 65, 60, 55, 50, 45, 40, 35, 30, 25, 20, 15, 10, 5 };
    }
}
