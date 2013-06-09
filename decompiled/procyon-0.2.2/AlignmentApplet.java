import localization.*;
import java.applet.*;
import java.awt.event.*;
import java.awt.*;
import com.cim.AIA.*;

public class AlignmentApplet extends AlgorithmApplet
{
    private static final long serialVersionUID = 5436130069584190965L;
    protected static final String IMAGE_DIRECTORY = "images/";
    public static final String GLOBAL_DIST_NOGAP_NAME;
    public static final String GLOBAL_DIST_GAP_NAME;
    public static final String GLOBAL_SIM_NOGAP_NAME;
    public static final String GLOBAL_SIM_GAP_NAME;
    public static final String LOCAL_SIM_NOGAP_NAME;
    public static final String LOCAL_SIM_GAP_NAME;
    public static String FIRST_FILE_NAME;
    public static String SECOND_FILE_NAME;
    public static AlignmentCanvas theAlignmentCanvas;
    protected static final String EXPLANATION_TITLE = " Introduction to Alignment";
    protected static final String EXPLANATION_DIST_NOGAP;
    protected static final String EXPLANATION_DIST_GAP;
    protected static final String EXPLANATION_SIM_NOGAP;
    protected static final String EXPLANATION_SIM_GAP;
    protected static final String EXPLANATION_SIMLT_NOGAP;
    public static ExplainationWindow expWin;
    public static String FIRST_EXPLANATION_NAME;
    public static String SECOND_EXPLANATION_NAME;
    public static String codeBaseString;
    protected static final String EXPLANATION_FILE_NAME;
    protected boolean debug;
    protected StringArray initialData;
    
    public AlignmentApplet() {
        super();
        this.debug = false;
        this.imageDirectory = "images/";
        this.explanationTitle = " Introduction to Alignment";
        this.explanationFileName = AlignmentApplet.EXPLANATION_FILE_NAME;
        if (this.debug) {
            final Button button;
            final Button b = button = new Button("Display Positions");
            button.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    AlignmentApplet.this.displayWindowLocations();
                }
            });
            this.add(b);
        }
    }
    
    public AlgorithmCanvas getAlgorithmCanvas() {
        AlignmentApplet.theAlignmentCanvas = new AlignmentCanvas();
        return AlignmentApplet.theAlignmentCanvas;
    }
    
    public AlgorithmThread getAlgorithmThread(final Copyable copyable, final AlgorithmWindow algorithmWindow) {
        return new AlignmentThread(copyable, algorithmWindow);
    }
    
    public AnimationWindow getAnimationWindow(final AlgorithmCanvas algorithmCanvas, final AlgorithmThread algorithmThread, final Applet applet) {
        return new AlignmentAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }
    
    public Copyable getInitialData() {
        return this.initialData;
    }
    
    protected void otherInitialisation() {
        AlignmentApplet.expWin = this.explainationWindow;
        AlignmentApplet.codeBaseString = this.getCodeBase().toString();
    }
    
    public void parseParameter(final String parameter) {
        Alignment.setRunningMode(parameter);
        if (Alignment.runningMode == 1) {
            AlignmentApplet.FIRST_FILE_NAME = AlignmentApplet.GLOBAL_DIST_NOGAP_NAME;
            AlignmentApplet.SECOND_FILE_NAME = AlignmentApplet.GLOBAL_DIST_GAP_NAME;
            AlignmentApplet.FIRST_EXPLANATION_NAME = AlignmentApplet.EXPLANATION_DIST_NOGAP;
            AlignmentApplet.SECOND_EXPLANATION_NAME = AlignmentApplet.EXPLANATION_DIST_GAP;
            this.initialData = new StringArray(AlignmentAnimationWindow.GLOBAL_DIST_NOGAP_DATA);
        }
        if (Alignment.runningMode == 2) {
            AlignmentApplet.FIRST_FILE_NAME = AlignmentApplet.GLOBAL_SIM_NOGAP_NAME;
            AlignmentApplet.SECOND_FILE_NAME = AlignmentApplet.GLOBAL_SIM_GAP_NAME;
            AlignmentApplet.FIRST_EXPLANATION_NAME = AlignmentApplet.EXPLANATION_SIM_NOGAP;
            AlignmentApplet.SECOND_EXPLANATION_NAME = AlignmentApplet.EXPLANATION_SIM_GAP;
            this.initialData = new StringArray(AlignmentAnimationWindow.GLOBAL_SIM_NOGAP_DATA);
        }
        if (Alignment.runningMode == 3) {
            AlignmentApplet.FIRST_FILE_NAME = AlignmentApplet.LOCAL_SIM_NOGAP_NAME;
            AlignmentApplet.SECOND_FILE_NAME = AlignmentApplet.LOCAL_SIM_GAP_NAME;
            AlignmentApplet.FIRST_EXPLANATION_NAME = AlignmentApplet.EXPLANATION_SIMLT_NOGAP;
            this.initialData = new StringArray(AlignmentAnimationWindow.LOCAL_SIM_NOGAP_DATA);
        }
        this.algorithmFileName = AlignmentApplet.FIRST_FILE_NAME;
        this.explanationFileName = AlignmentApplet.FIRST_EXPLANATION_NAME;
    }
    
    public void setLocationAndSize() {
        this.explainationWindow.setLocation(0, 0);
        this.explainationWindow.setSize(235, 735);
        this.window.setLocation(235, 135);
        this.window.setSize(350, 600);
        this.helpWindow.setLocation(235, 0);
        this.helpWindow.setSize(350, 135);
        this.animWindow.setLocation(585, 0);
        this.animWindow.setSize(440, 720);
    }
    
    static {
        GLOBAL_DIST_NOGAP_NAME = Messages.getString("AlignmentApplet.1");
        GLOBAL_DIST_GAP_NAME = Messages.getString("AlignmentApplet.2");
        GLOBAL_SIM_NOGAP_NAME = Messages.getString("AlignmentApplet.3");
        GLOBAL_SIM_GAP_NAME = Messages.getString("AlignmentApplet.4");
        LOCAL_SIM_NOGAP_NAME = Messages.getString("AlignmentApplet.5");
        LOCAL_SIM_GAP_NAME = Messages.getString("AlignmentApplet.6");
        AlignmentApplet.FIRST_FILE_NAME = "";
        AlignmentApplet.SECOND_FILE_NAME = "";
        EXPLANATION_DIST_NOGAP = Messages.getString("AlignmentApplet.10");
        EXPLANATION_DIST_GAP = Messages.getString("AlignmentApplet.11");
        EXPLANATION_SIM_NOGAP = Messages.getString("AlignmentApplet.12");
        EXPLANATION_SIM_GAP = Messages.getString("AlignmentApplet.13");
        EXPLANATION_SIMLT_NOGAP = Messages.getString("AlignmentApplet.14");
        AlignmentApplet.FIRST_EXPLANATION_NAME = "";
        AlignmentApplet.SECOND_EXPLANATION_NAME = "";
        EXPLANATION_FILE_NAME = Messages.getString("AlignmentApplet.17");
    }
}
