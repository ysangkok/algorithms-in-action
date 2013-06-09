import java.applet.*;
import java.awt.event.*;
import java.awt.*;
import localization.*;
import com.cim.AIA.*;

public class KMPApplet extends AlgorithmApplet
{
    private static final long serialVersionUID = 8471561699476367630L;
    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String ALGORITHM_FILE_NAME;
    protected static final String EXPLANATION_TITLE;
    protected static final String EXPLANATION_FILE_NAME;
    public static KMPCanvas theKMPCanvas;
    public static String FIRST_FILE_NAME;
    public static String SECOND_FILE_NAME;
    protected boolean debug;
    protected StringArray initialData;
    
    public KMPApplet() {
        super();
        this.debug = false;
        this.imageDirectory = "images/";
        this.algorithmFileName = KMPApplet.FIRST_FILE_NAME;
        this.explanationTitle = KMPApplet.EXPLANATION_TITLE;
        this.explanationFileName = KMPApplet.EXPLANATION_FILE_NAME;
        if (this.debug) {
            final Button button;
            final Button b = button = new Button(Messages.getString("KMPApplet.1"));
            button.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    KMPApplet.this.displayWindowLocations();
                }
            });
            this.add(b);
        }
    }
    
    public AlgorithmCanvas getAlgorithmCanvas() {
        KMPApplet.theKMPCanvas = new KMPCanvas();
        return KMPApplet.theKMPCanvas;
    }
    
    public AlgorithmThread getAlgorithmThread(final Copyable copyable, final AlgorithmWindow algorithmWindow) {
        return new KMPThread(copyable, algorithmWindow);
    }
    
    public AnimationWindow getAnimationWindow(final AlgorithmCanvas algorithmCanvas, final AlgorithmThread algorithmThread, final Applet applet) {
        return new KMPAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }
    
    public Copyable getInitialData() {
        this.initialData = new StringArray(KMPAnimationWindow.DEFAULT_DATA_1);
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
        ALGORITHM_FILE_NAME = Messages.getString("KMPApplet.5");
        EXPLANATION_TITLE = Messages.getString("KMPApplet.0");
        EXPLANATION_FILE_NAME = Messages.getString("KMPApplet.4");
        KMPApplet.FIRST_FILE_NAME = Messages.getString("KMPApplet.3");
        KMPApplet.SECOND_FILE_NAME = Messages.getString("KMPApplet.2");
    }
}
