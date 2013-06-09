import localization.*;
import java.applet.*;
import com.cim.AIA.*;

public class RadixTrieApplet extends AlgorithmApplet
{
    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String ALGORITHM_FILE_NAME;
    protected static final String EXPLANATION_TITLE;
    protected static final String EXPLANATION_FILE_NAME;
    
    public RadixTrieApplet() {
        super();
        this.imageDirectory = "images/";
        this.algorithmFileName = RadixTrieApplet.ALGORITHM_FILE_NAME;
        this.explanationTitle = RadixTrieApplet.EXPLANATION_TITLE;
        this.explanationFileName = RadixTrieApplet.EXPLANATION_FILE_NAME;
    }
    
    public Copyable getInitialData() {
        return new IntArray(RadixTrieAnimationWindow.DEFAULT_DATA);
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
        return new RadixTrieThread(copyable, algorithmWindow);
    }
    
    protected AlgorithmWindow getAlgorithmWindow(final String string, final CodeCanvas codeCanvas) {
        return new SplitAlgorithmWindow(string, codeCanvas);
    }
    
    public AlgorithmCanvas getAlgorithmCanvas() {
        return new RadixTrieCanvas();
    }
    
    public AnimationWindow getAnimationWindow(final AlgorithmCanvas algorithmCanvas, final AlgorithmThread algorithmThread, final Applet applet) {
        return new RadixTrieAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }
    
    protected void otherInitialisation() {
        ((RadixTrie)this.algorithm).initialiseMethods(this.getCodeBase().toString(), (SplitAlgorithmWindow)this.window);
    }
    
    static {
        ALGORITHM_FILE_NAME = Messages.getString("RadixTrieApplet.1");
        EXPLANATION_TITLE = Messages.getString("RadixTrieApplet.2");
        EXPLANATION_FILE_NAME = Messages.getString("RadixTrieApplet.3");
    }
}
