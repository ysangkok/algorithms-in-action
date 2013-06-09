import localization.*;
import java.applet.*;
import com.cim.AIA.*;

public class SkipListApplet extends AlgorithmApplet
{
    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String ALGORITHM_FILE_NAME;
    protected static final String EXPLANATION_TITLE;
    protected static final String EXPLANATION_FILE_NAME;
    protected static AlgorithmWindow lastWindow;
    
    public SkipListApplet() {
        super();
        this.imageDirectory = "images/";
        this.algorithmFileName = SkipListApplet.ALGORITHM_FILE_NAME;
        this.explanationTitle = SkipListApplet.EXPLANATION_TITLE;
        this.explanationFileName = SkipListApplet.EXPLANATION_FILE_NAME;
        SkipListApplet.lastWindow = this.window;
    }
    
    public Copyable getInitialData() {
        return new IntArray(SkipListAnimationWindow.DEFAULT_DATA);
    }
    
    protected void otherInitialisation() {
        ((SkipList)this.algorithm).initialiseMethods(this.getCodeBase().toString(), (SplitAlgorithmWindow)this.window);
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
        return new SkipListThread(copyable, (SplitAlgorithmWindow)algorithmWindow);
    }
    
    public AlgorithmCanvas getAlgorithmCanvas() {
        return new SkipListCanvas();
    }
    
    public AlgorithmWindow getAlgorithmWindow() {
        return SkipListApplet.lastWindow;
    }
    
    public AnimationWindow getAnimationWindow(final AlgorithmCanvas algorithmCanvas, final AlgorithmThread algorithmThread, final Applet applet) {
        return new SkipListAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }
    
    protected AlgorithmWindow getAlgorithmWindow(final String string, final CodeCanvas codeCanvas) {
        return new SplitAlgorithmWindow(string, codeCanvas);
    }
    
    static {
        ALGORITHM_FILE_NAME = Messages.getString("SkipListApplet.1");
        EXPLANATION_TITLE = Messages.getString("SkipListApplet.2");
        EXPLANATION_FILE_NAME = Messages.getString("SkipListApplet.3");
        SkipListApplet.lastWindow = null;
    }
}
