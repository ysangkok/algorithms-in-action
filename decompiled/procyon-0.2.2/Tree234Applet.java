import localization.*;
import java.applet.*;
import com.cim.AIA.*;

public class Tree234Applet extends AlgorithmApplet
{
    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String ALGORITHM_FILE_NAME;
    protected static final String EXPLANATION_TITLE;
    protected static final String EXPLANATION_FILE_NAME;
    
    public Tree234Applet() {
        super();
        this.imageDirectory = "images/";
        this.algorithmFileName = Tree234Applet.ALGORITHM_FILE_NAME;
        this.explanationTitle = Tree234Applet.EXPLANATION_TITLE;
        this.explanationFileName = Tree234Applet.EXPLANATION_FILE_NAME;
    }
    
    public Copyable getInitialData() {
        return new IntArray(Tree234AnimationWindow.DEFAULT_DATA);
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
    
    protected void otherInitialisation() {
        ((Tree234Tree)((Tree234Tree)this.algorithm)).initialiseMethods(this.getCodeBase().toString(), (SplitAlgorithmWindow)((SplitAlgorithmWindow)this.window));
    }
    
    protected AlgorithmWindow getAlgorithmWindow(final String algorithmWindowName, final CodeCanvas codeCanvas) {
        return new SplitAlgorithmWindow(algorithmWindowName, codeCanvas);
    }
    
    public AlgorithmThread getAlgorithmThread(final Copyable copyable, final AlgorithmWindow algorithmWindow) {
        return new Tree234Thread(copyable, (SplitAlgorithmWindow)algorithmWindow);
    }
    
    public AlgorithmCanvas getAlgorithmCanvas() {
        return new Tree234Canvas();
    }
    
    public AnimationWindow getAnimationWindow(final AlgorithmCanvas algorithmCanvas, final AlgorithmThread algorithmThread, final Applet applet) {
        return new Tree234AnimationWindow(algorithmCanvas, algorithmThread, applet);
    }
    
    static {
        ALGORITHM_FILE_NAME = Messages.getString("Tree234Applet.1");
        EXPLANATION_TITLE = Messages.getString("Tree234Applet.2");
        EXPLANATION_FILE_NAME = Messages.getString("Tree234Applet.3");
    }
}
