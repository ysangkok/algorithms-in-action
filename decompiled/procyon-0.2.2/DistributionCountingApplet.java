import java.applet.*;
import java.awt.event.*;
import java.awt.*;
import localization.*;
import com.cim.AIA.*;

public class DistributionCountingApplet extends AlgorithmApplet
{
    private static final long serialVersionUID = 8370727374155796990L;
    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String ALGORITHM_FILE_NAME;
    protected static final String EXPLANATION_TITLE;
    protected static final String EXPLANATION_FILE_NAME;
    protected boolean debug;
    
    public DistributionCountingApplet() {
        super();
        this.debug = false;
        this.imageDirectory = "images/";
        this.algorithmFileName = DistributionCountingApplet.ALGORITHM_FILE_NAME;
        this.explanationTitle = DistributionCountingApplet.EXPLANATION_TITLE;
        this.explanationFileName = DistributionCountingApplet.EXPLANATION_FILE_NAME;
        if (this.debug) {
            final Button button;
            final Button b = button = new Button(Messages.getString("DistributionCountingApplet.0"));
            button.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    DistributionCountingApplet.this.displayWindowLocations();
                }
            });
            this.add(b);
        }
    }
    
    public AlgorithmCanvas getAlgorithmCanvas() {
        return new DistributionCountingCanvas();
    }
    
    public AlgorithmThread getAlgorithmThread(final Copyable copyable, final AlgorithmWindow algorithmWindow) {
        return new DistributionCountingThread(copyable, algorithmWindow);
    }
    
    public AnimationWindow getAnimationWindow(final AlgorithmCanvas algorithmCanvas, final AlgorithmThread algorithmThread, final Applet applet) {
        return new DistributionCountingAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }
    
    public Copyable getInitialData() {
        return new RandomIntArrayDataSelection("", false, this.animWindow, 3, 20, 0, 4).getData();
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
        ALGORITHM_FILE_NAME = Messages.getString("DistributionCountingApplet.3");
        EXPLANATION_TITLE = Messages.getString("DistributionCountingApplet.1");
        EXPLANATION_FILE_NAME = Messages.getString("DistributionCountingApplet.2");
    }
}
