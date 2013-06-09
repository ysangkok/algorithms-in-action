import java.applet.*;
import java.awt.event.*;
import java.awt.*;
import localization.*;
import com.cim.AIA.*;

public class StraightRadixSortApplet extends AlgorithmApplet
{
    private static final long serialVersionUID = -8911613141982869933L;
    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String ALGORITHM_FILE_NAME;
    protected static final String EXPLANATION_TITLE;
    protected static final String EXPLANATION_FILE_NAME;
    protected boolean debug;
    
    public StraightRadixSortApplet() {
        super();
        this.debug = false;
        this.imageDirectory = "images/";
        this.algorithmFileName = StraightRadixSortApplet.ALGORITHM_FILE_NAME;
        this.explanationTitle = StraightRadixSortApplet.EXPLANATION_TITLE;
        this.explanationFileName = StraightRadixSortApplet.EXPLANATION_FILE_NAME;
        if (this.debug) {
            final Button button;
            final Button b = button = new Button(Messages.getString("StraightRadixSortApplet.0"));
            button.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    StraightRadixSortApplet.this.displayWindowLocations();
                }
            });
            this.add(b);
        }
    }
    
    public AlgorithmCanvas getAlgorithmCanvas() {
        return new StraightRadixSortCanvas();
    }
    
    public AlgorithmThread getAlgorithmThread(final Copyable copyable, final AlgorithmWindow algorithmWindow) {
        return new StraightRadixSortThread(copyable, algorithmWindow);
    }
    
    public AnimationWindow getAnimationWindow(final AlgorithmCanvas algorithmCanvas, final AlgorithmThread algorithmThread, final Applet applet) {
        return new StraightRadixSortAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }
    
    public Copyable getInitialData() {
        return new RandomIntArrayDataSelection("", false, this.animWindow, 3, 20, 0, 15).getData();
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
        ALGORITHM_FILE_NAME = Messages.getString("StraightRadixSortApplet.3");
        EXPLANATION_TITLE = Messages.getString("StraightRadixSortApplet.1");
        EXPLANATION_FILE_NAME = Messages.getString("StraightRadixSortApplet.2");
    }
}
