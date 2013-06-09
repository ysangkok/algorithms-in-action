import localization.*;
import java.applet.*;
import com.cim.AIA.*;

public class PatriciaTreeIterApplet extends AlgorithmApplet
{
    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String ALGORITHM_FILE_NAME;
    protected static final String EXPLANATION_TITLE;
    protected static final String EXPLANATION_FILE_NAME;
    
    public PatriciaTreeIterApplet() {
        super();
        this.imageDirectory = "images/";
        this.algorithmFileName = PatriciaTreeIterApplet.ALGORITHM_FILE_NAME;
        this.explanationTitle = PatriciaTreeIterApplet.EXPLANATION_TITLE;
        this.explanationFileName = PatriciaTreeIterApplet.EXPLANATION_FILE_NAME;
    }
    
    protected void otherInitialisation() {
        ((PatriciaTreeIter)this.algorithm).initialiseMethods(this.getCodeBase().toString(), (SplitAlgorithmWindow)this.window);
    }
    
    public Copyable getInitialData() {
        return new IntArray(PatriciaTreeIterAnimationWindow.DEFAULT_DATA);
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
    
    public AlgorithmThread getAlgorithmThread(final Copyable copyable, final AlgorithmWindow algorithmWindow) {
        return new PatriciaTreeIterThread(copyable, (SplitAlgorithmWindow)algorithmWindow);
    }
    
    public AlgorithmCanvas getAlgorithmCanvas() {
        return new PatriciaTreeIterCanvas();
    }
    
    public AnimationWindow getAnimationWindow(final AlgorithmCanvas algorithmCanvas, final AlgorithmThread algorithmThread, final Applet applet) {
        return new PatriciaTreeIterAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }
    
    protected AlgorithmWindow getAlgorithmWindow(final String algorithmWindowName, final CodeCanvas codeCanvas) {
        return new SplitAlgorithmWindow(algorithmWindowName, codeCanvas);
    }
    
    static {
        ALGORITHM_FILE_NAME = Messages.getString("PatriciaTreeIterApplet.1");
        EXPLANATION_TITLE = Messages.getString("PatriciaTreeIterApplet.2");
        EXPLANATION_FILE_NAME = Messages.getString("PatriciaTreeIterApplet.3");
    }
}
