import localization.*;
import java.applet.*;
import com.cim.AIA.*;

public class DigitalSearchTreeApplet extends AlgorithmApplet
{
    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String ALGORITHM_FILE_NAME;
    protected static final String EXPLANATION_TITLE;
    protected static final String EXPLANATION_FILE_NAME;
    protected static AlgorithmWindow lastWindow;
    
    public DigitalSearchTreeApplet() {
        super();
        this.imageDirectory = "images/";
        this.algorithmFileName = DigitalSearchTreeApplet.ALGORITHM_FILE_NAME;
        this.explanationTitle = DigitalSearchTreeApplet.EXPLANATION_FILE_NAME;
        this.explanationFileName = DigitalSearchTreeApplet.EXPLANATION_FILE_NAME;
        DigitalSearchTreeApplet.lastWindow = this.window;
    }
    
    public AlgorithmCanvas getAlgorithmCanvas() {
        return new DigitalSearchTreeCanvas();
    }
    
    public AlgorithmWindow getAlgorithmWindow() {
        return DigitalSearchTreeApplet.lastWindow;
    }
    
    public AlgorithmThread getAlgorithmThread(final Copyable copyable, final AlgorithmWindow algorithmWindow) {
        return new DigitalSearchTreeThread(copyable, (SplitAlgorithmWindow)algorithmWindow);
    }
    
    protected AlgorithmWindow getAlgorithmWindow(final String string, final CodeCanvas codeCanvas) {
        return new SplitAlgorithmWindow(string, codeCanvas);
    }
    
    public AnimationWindow getAnimationWindow(final AlgorithmCanvas algorithmCanvas, final AlgorithmThread algorithmThread, final Applet applet) {
        return new DigitalSearchTreeAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }
    
    public Copyable getInitialData() {
        return new IntArray(DigitalSearchTreeAnimationWindow.DEFAULT_DATA);
    }
    
    protected void otherInitialisation() {
        ((DigitalSearchTree)this.algorithm).initialiseMethods(this.getCodeBase().toString(), (SplitAlgorithmWindow)this.window);
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
        ALGORITHM_FILE_NAME = Messages.getString("DigitalSearchTreeApplet.1");
        EXPLANATION_TITLE = Messages.getString("DigitalSearchTreeApplet.2");
        EXPLANATION_FILE_NAME = Messages.getString("DigitalSearchTreeApplet.3");
        DigitalSearchTreeApplet.lastWindow = null;
    }
}
