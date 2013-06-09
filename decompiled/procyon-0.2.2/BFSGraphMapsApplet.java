import java.applet.*;
import java.awt.event.*;
import java.awt.*;
import localization.*;
import com.cim.AIA.*;

public class BFSGraphMapsApplet extends AlgorithmApplet
{
    private static final long serialVersionUID = -7246428204529059112L;
    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String ALGORITHM_FILE_NAME;
    protected static final String EXPLANATION_TITLE;
    protected static final String EXPLANATION_FILE_NAME;
    protected boolean debug;
    
    public BFSGraphMapsApplet() {
        super();
        this.debug = false;
        this.imageDirectory = "images/";
        this.algorithmFileName = BFSGraphMapsApplet.ALGORITHM_FILE_NAME;
        this.explanationTitle = BFSGraphMapsApplet.EXPLANATION_TITLE;
        this.explanationFileName = BFSGraphMapsApplet.EXPLANATION_FILE_NAME;
        if (this.debug) {
            final Button button;
            final Button b = button = new Button(Messages.getString("GraphMapsApplet.4"));
            button.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    BFSGraphMapsApplet.this.displayWindowLocations();
                }
            });
            this.add(b);
        }
    }
    
    public AlgorithmCanvas getAlgorithmCanvas() {
        return new BFSGraphMapsCanvas();
    }
    
    public AlgorithmThread getAlgorithmThread(final Copyable copyable, final AlgorithmWindow algorithmWindow) {
        return new BFSGraphMapsThread(copyable, algorithmWindow);
    }
    
    public AlgorithmWindow getAlgorithmWindow(final String algorithmWindowName, final CodeCanvas codeCanvas) {
        return new SplitAlgorithmWindow(algorithmWindowName, codeCanvas);
    }
    
    public AnimationWindow getAnimationWindow(final AlgorithmCanvas algorithmCanvas, final AlgorithmThread algorithmThread, final Applet applet) {
        return new BFSGraphMapsAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }
    
    public Copyable getInitialData() {
        final int[] tmp = BFSGraphMapsAnimationWindow.DEFAULT_DATA;
        final IntArray tmptmp = new IntArray(tmp);
        return tmptmp;
    }
    
    protected void otherInitialisation() {
        ((BFSGraphMaps)((BFSGraphMaps)this.algorithm)).initialiseMethods(this.getCodeBase().toString(), (SplitAlgorithmWindow)((SplitAlgorithmWindow)this.window));
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
    
    static {
        ALGORITHM_FILE_NAME = Messages.getString("GraphMapsApplet.1");
        EXPLANATION_TITLE = Messages.getString("GraphMapsApplet.2");
        EXPLANATION_FILE_NAME = Messages.getString("GraphMapsApplet.0");
    }
}
