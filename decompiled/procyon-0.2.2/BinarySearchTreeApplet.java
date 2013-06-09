import localization.*;
import java.applet.*;
import com.cim.AIA.*;

public class BinarySearchTreeApplet extends AlgorithmApplet
{
    private static final long serialVersionUID = 803737462844962440L;
    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String ALGORITHM_FILE_NAME;
    protected static final String EXPLANATION_TITLE;
    protected static final String EXPLANATION_FILE_NAME;
    
    public BinarySearchTreeApplet() {
        super();
        this.imageDirectory = "images/";
        this.algorithmFileName = BinarySearchTreeApplet.ALGORITHM_FILE_NAME;
        this.explanationTitle = BinarySearchTreeApplet.EXPLANATION_TITLE;
        this.explanationFileName = BinarySearchTreeApplet.EXPLANATION_FILE_NAME;
    }
    
    public AlgorithmCanvas getAlgorithmCanvas() {
        return new BinarySearchTreeCanvas();
    }
    
    public AlgorithmThread getAlgorithmThread(final Copyable copyable, final AlgorithmWindow algorithmWindow) {
        return new BinarySearchTreeThread(copyable, algorithmWindow);
    }
    
    protected AlgorithmWindow getAlgorithmWindow(final String string, final CodeCanvas codeCanvas) {
        return new SplitAlgorithmWindow(string, codeCanvas);
    }
    
    public AnimationWindow getAnimationWindow(final AlgorithmCanvas algorithmCanvas, final AlgorithmThread algorithmThread, final Applet applet) {
        return new BinarySearchTreeAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }
    
    public Copyable getInitialData() {
        return new IntArray(BinarySearchTreeAnimationWindow.DEFAULT_DATA);
    }
    
    protected void otherInitialisation() {
        ((BinarySearchTree)this.algorithm).initialiseMethods(this.getCodeBase().toString(), (SplitAlgorithmWindow)this.window);
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
        ALGORITHM_FILE_NAME = Messages.getString("BinarySearchTreeApplet.1");
        EXPLANATION_TITLE = Messages.getString("BinarySearchTreeApplet.2");
        EXPLANATION_FILE_NAME = Messages.getString("BinarySearchTreeApplet.3");
    }
}
