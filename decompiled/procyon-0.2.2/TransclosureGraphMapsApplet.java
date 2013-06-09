import java.applet.*;
import java.awt.event.*;
import java.awt.*;
import localization.*;
import com.cim.AIA.*;

public class TransclosureGraphMapsApplet extends AlgorithmApplet
{
    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String ALGORITHM_FILE_NAME;
    protected static final String EXPLANATION_TITLE;
    protected static final String EXPLANATION_FILE_NAME;
    protected boolean debug;
    
    public TransclosureGraphMapsApplet() {
        super();
        this.debug = false;
        this.imageDirectory = "images/";
        this.algorithmFileName = TransclosureGraphMapsApplet.ALGORITHM_FILE_NAME;
        this.explanationTitle = TransclosureGraphMapsApplet.EXPLANATION_TITLE;
        this.explanationFileName = TransclosureGraphMapsApplet.EXPLANATION_FILE_NAME;
        if (this.debug) {
            final Button button;
            final Button b = button = new Button(Messages.getString("TransclosureGraphMapsApplet.4"));
            button.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    TransclosureGraphMapsApplet.this.displayWindowLocations();
                }
            });
            this.add(b);
        }
    }
    
    public Copyable getInitialData() {
        final int[] tmp = TransclosureGraphMapsAnimationWindow.DEFAULT_DATA;
        final IntArray tmptmp = new IntArray(tmp);
        return tmptmp;
    }
    
    public void setLocationAndSize() {
        this.explainationWindow.setLocation(0, 0);
        this.explainationWindow.setSize(235, 735);
        this.window.setLocation(235, 135);
        this.window.setSize(350, 600);
        this.helpWindow.setLocation(235, 0);
        this.helpWindow.setSize(350, 135);
        this.animWindow.setLocation(585, 0);
        this.animWindow.setSize(440, 367);
    }
    
    public AlgorithmThread getAlgorithmThread(final Copyable copyable, final AlgorithmWindow algorithmWindow) {
        return new TransclosureGraphMapsThread(copyable, algorithmWindow);
    }
    
    public AlgorithmCanvas getAlgorithmCanvas() {
        return new TransclosureGraphMapsCanvas();
    }
    
    public AnimationWindow getAnimationWindow(final AlgorithmCanvas algorithmCanvas, final AlgorithmThread algorithmThread, final Applet applet) {
        return new TransclosureGraphMapsAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }
    
    static {
        ALGORITHM_FILE_NAME = Messages.getString("TransclosureGraphMapsApplet.1");
        EXPLANATION_TITLE = Messages.getString("TransclosureGraphMapsApplet.2");
        EXPLANATION_FILE_NAME = Messages.getString("TransclosureGraphMapsApplet.3");
    }
}
