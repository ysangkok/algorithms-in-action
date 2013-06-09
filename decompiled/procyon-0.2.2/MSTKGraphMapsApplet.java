import java.applet.*;
import java.awt.event.*;
import java.awt.*;
import localization.*;
import com.cim.AIA.*;

public class MSTKGraphMapsApplet extends AlgorithmApplet
{
    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String ALGORITHM_FILE_NAME;
    protected static final String EXPLANATION_TITLE;
    protected static final String EXPLANATION_FILE_NAME;
    protected boolean debug;
    
    public MSTKGraphMapsApplet() {
        super();
        this.debug = false;
        this.imageDirectory = "images/";
        this.algorithmFileName = MSTKGraphMapsApplet.ALGORITHM_FILE_NAME;
        this.explanationTitle = MSTKGraphMapsApplet.EXPLANATION_TITLE;
        this.explanationFileName = MSTKGraphMapsApplet.EXPLANATION_FILE_NAME;
        if (this.debug) {
            final Button button;
            final Button b = button = new Button(Messages.getString("MSTKGraphMapsApplet.4"));
            button.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    MSTKGraphMapsApplet.this.displayWindowLocations();
                }
            });
            this.add(b);
        }
    }
    
    public Copyable getInitialData() {
        final int[] tmp = MSTKGraphMapsAnimationWindow.DEFAULT_DATA;
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
    
    protected void otherInitialisation() {
        ((MSTKGraphMaps)((MSTKGraphMaps)this.algorithm)).initialiseMethods(this.getCodeBase().toString(), (SplitAlgorithmWindow)((SplitAlgorithmWindow)this.window));
    }
    
    public AlgorithmWindow getAlgorithmWindow(final String algorithmWindowName, final CodeCanvas codeCanvas) {
        return new SplitAlgorithmWindow(algorithmWindowName, codeCanvas);
    }
    
    public AlgorithmThread getAlgorithmThread(final Copyable copyable, final AlgorithmWindow algorithmWindow) {
        return new MSTKGraphMapsThread(copyable, algorithmWindow);
    }
    
    public AlgorithmCanvas getAlgorithmCanvas() {
        return new MSTKGraphMapsCanvas();
    }
    
    public AnimationWindow getAnimationWindow(final AlgorithmCanvas algorithmCanvas, final AlgorithmThread algorithmThread, final Applet applet) {
        return new MSTKGraphMapsAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }
    
    static {
        ALGORITHM_FILE_NAME = Messages.getString("MSTKGraphMapsApplet.1");
        EXPLANATION_TITLE = Messages.getString("MSTKGraphMapsApplet.2");
        EXPLANATION_FILE_NAME = Messages.getString("MSTKGraphMapsApplet.3");
    }
}
