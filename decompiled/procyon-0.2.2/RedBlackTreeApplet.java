import localization.*;
import java.applet.*;
import com.cim.AIA.*;

public class RedBlackTreeApplet extends AlgorithmApplet
{
    private static final long serialVersionUID = -6694171910480087104L;
    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String ALGORITHM_FILE_NAME;
    protected static final String EXPLANATION_TITLE;
    protected static final String EXPLANATION_FILE_NAME;
    
    public RedBlackTreeApplet() {
        super();
        this.imageDirectory = "images/";
        this.algorithmFileName = RedBlackTreeApplet.ALGORITHM_FILE_NAME;
        this.explanationTitle = RedBlackTreeApplet.EXPLANATION_TITLE;
        this.explanationFileName = RedBlackTreeApplet.EXPLANATION_FILE_NAME;
    }
    
    public AlgorithmCanvas getAlgorithmCanvas() {
        return new RedBlackTreeCanvas();
    }
    
    public AlgorithmThread getAlgorithmThread(final Copyable copyable, final AlgorithmWindow algorithmWindow) {
        return new RedBlackTreeThread(copyable, algorithmWindow);
    }
    
    protected AlgorithmWindow getAlgorithmWindow(final String algorithmWindowName, final CodeCanvas codeCanvas) {
        return new SplitAlgorithmWindow(algorithmWindowName, codeCanvas);
    }
    
    public AnimationWindow getAnimationWindow(final AlgorithmCanvas algorithmCanvas, final AlgorithmThread algorithmThread, final Applet applet) {
        return new RedBlackTreeAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }
    
    public Copyable getInitialData() {
        return new IntArray(RedBlackTreeAnimationWindow.DEFAULT_DATA);
    }
    
    protected void otherInitialisation() {
        ((RedBlackTree)((RedBlackTree)this.algorithm)).initialiseMethods(this.getCodeBase().toString(), (SplitAlgorithmWindow)((SplitAlgorithmWindow)this.window));
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
        ALGORITHM_FILE_NAME = Messages.getString("RedBlackTreeApplet.2");
        EXPLANATION_TITLE = Messages.getString("RedBlackTreeApplet.0");
        EXPLANATION_FILE_NAME = Messages.getString("RedBlackTreeApplet.1");
    }
}
