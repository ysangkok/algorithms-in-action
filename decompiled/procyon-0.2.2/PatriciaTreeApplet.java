import localization.*;
import java.applet.*;
import com.cim.AIA.*;

public class PatriciaTreeApplet extends AlgorithmApplet
{
    private static final long serialVersionUID = -3797081612876957752L;
    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String ALGORITHM_FILE_NAME;
    protected static final String EXPLANATION_TITLE;
    protected static final String EXPLANATION_FILE_NAME;
    
    public PatriciaTreeApplet() {
        super();
        this.imageDirectory = "images/";
        this.algorithmFileName = PatriciaTreeApplet.ALGORITHM_FILE_NAME;
        this.explanationTitle = PatriciaTreeApplet.EXPLANATION_TITLE;
        this.explanationFileName = PatriciaTreeApplet.EXPLANATION_FILE_NAME;
    }
    
    public AlgorithmCanvas getAlgorithmCanvas() {
        return new PatriciaTreeCanvas();
    }
    
    public AlgorithmThread getAlgorithmThread(final Copyable copyable, final AlgorithmWindow algorithmWindow) {
        return new PatriciaTreeThread(copyable, algorithmWindow);
    }
    
    protected AlgorithmWindow getAlgorithmWindow(final String algorithmWindowName, final CodeCanvas codeCanvas) {
        return new MultiAlgorithmWindow(this, algorithmWindowName, codeCanvas);
    }
    
    public AnimationWindow getAnimationWindow(final AlgorithmCanvas algorithmCanvas, final AlgorithmThread algorithmThread, final Applet applet) {
        return new PatriciaTreeAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }
    
    public Copyable getInitialData() {
        return new IntArray(PatriciaTreeAnimationWindow.DEFAULT_DATA);
    }
    
    protected void otherInitialisation() {
        ((PatriciaTree)this.algorithm).initialiseMethods(this.getCodeBase().toString(), (MultiAlgorithmWindow)this.window);
        ((PatriciaTree)this.algorithm).initialiseCanvases(this.getCodeBase().toString(), (MultiAlgorithmWindow)this.window);
    }
    
    public void setLocationAndSize() {
        this.explainationWindow.setLocation(0, 0);
        this.explainationWindow.setSize(235, 735);
        this.window.setLocation(235, 0);
        this.window.setSize(350, 735);
        this.helpWindow.setLocation(235, 0);
        this.helpWindow.setSize(350, 135);
        this.animWindow.setLocation(585, 0);
        this.animWindow.setSize(440, 720);
    }
    
    static {
        ALGORITHM_FILE_NAME = Messages.getString("PatriciaTreeApplet.0");
        EXPLANATION_TITLE = Messages.getString("PatriciaTreeApplet.2");
        EXPLANATION_FILE_NAME = Messages.getString("PatriciaTreeApplet.3");
    }
}
