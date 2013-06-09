import localization.*;
import java.applet.*;
import com.cim.AIA.*;

public class SplayTreeIterApplet extends AlgorithmApplet
{
    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String ALGORITHM_FILE_NAME;
    protected static final String EXPLANATION_TITLE;
    protected static final String EXPLANATION_FILE_NAME;
    
    public SplayTreeIterApplet() {
        super();
        this.imageDirectory = "images/";
        this.algorithmFileName = SplayTreeIterApplet.ALGORITHM_FILE_NAME;
        this.explanationTitle = SplayTreeIterApplet.EXPLANATION_TITLE;
        this.explanationFileName = SplayTreeIterApplet.EXPLANATION_FILE_NAME;
    }
    
    public Copyable getInitialData() {
        return new IntArray(SplayTreeIterAnimationWindow.DEFAULT_DATA);
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
        return new SplayTreeIterThread(copyable, algorithmWindow);
    }
    
    public AlgorithmCanvas getAlgorithmCanvas() {
        return new SplayTreeIterCanvas();
    }
    
    protected AlgorithmWindow getAlgorithmWindow(final String string, final CodeCanvas codeCanvas) {
        return new SplitAlgorithmWindow(string, codeCanvas);
    }
    
    public AnimationWindow getAnimationWindow(final AlgorithmCanvas algorithmCanvas, final AlgorithmThread algorithmThread, final Applet applet) {
        return new SplayTreeIterAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }
    
    protected void otherInitialisation() {
        ((SplayTreeIter)this.algorithm).initialiseMethods(this.getCodeBase().toString(), (SplitAlgorithmWindow)this.window);
    }
    
    static {
        ALGORITHM_FILE_NAME = Messages.getString("SplayTreeIterApplet.1");
        EXPLANATION_TITLE = Messages.getString("SplayTreeIterApplet.2");
        EXPLANATION_FILE_NAME = Messages.getString("SplayTreeIterApplet.3");
    }
}
