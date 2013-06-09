import java.applet.*;
import java.awt.event.*;
import java.awt.*;
import localization.*;
import com.cim.AIA.*;

public class BoyerMooreApplet extends AlgorithmApplet
{
    private static final long serialVersionUID = -5980630599693714535L;
    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String ALGORITHM_FILE_NAME;
    protected static final String EXPLANATION_TITLE;
    protected static final String EXPLANATION_FILE_NAME;
    public static BoyerMooreCanvas theBoyerMooreCanvas;
    protected boolean debug;
    protected StringArray initialData;
    
    public BoyerMooreApplet() {
        super();
        this.debug = false;
        this.imageDirectory = "images/";
        this.algorithmFileName = BoyerMooreApplet.ALGORITHM_FILE_NAME;
        this.explanationTitle = BoyerMooreApplet.EXPLANATION_TITLE;
        this.explanationFileName = BoyerMooreApplet.EXPLANATION_FILE_NAME;
        if (this.debug) {
            final Button button;
            final Button b = button = new Button(Messages.getString("BoyerMooreApplet.4"));
            button.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    BoyerMooreApplet.this.displayWindowLocations();
                }
            });
            this.add(b);
        }
    }
    
    public AlgorithmCanvas getAlgorithmCanvas() {
        BoyerMooreApplet.theBoyerMooreCanvas = new BoyerMooreCanvas();
        return BoyerMooreApplet.theBoyerMooreCanvas;
    }
    
    public AlgorithmThread getAlgorithmThread(final Copyable copyable, final AlgorithmWindow algorithmWindow) {
        return new BoyerMooreThread(copyable, algorithmWindow);
    }
    
    public AnimationWindow getAnimationWindow(final AlgorithmCanvas algorithmCanvas, final AlgorithmThread algorithmThread, final Applet applet) {
        return new BoyerMooreAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }
    
    public Copyable getInitialData() {
        this.initialData = new StringArray(BoyerMooreAnimationWindow.DEFAULT_DATA_1);
        return this.initialData;
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
        ALGORITHM_FILE_NAME = Messages.getString("BoyerMooreApplet.0");
        EXPLANATION_TITLE = Messages.getString("BoyerMooreApplet.2");
        EXPLANATION_FILE_NAME = Messages.getString("BoyerMooreApplet.1");
    }
}
