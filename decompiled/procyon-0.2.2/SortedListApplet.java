import localization.*;
import java.applet.*;
import com.cim.AIA.*;

public class SortedListApplet extends AlgorithmApplet
{
    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String ALGORITHM_FILE_NAME;
    protected static final String EXPLANATION_TITLE;
    protected static final String EXPLANATION_FILE_NAME;
    protected static AlgorithmWindow lastWindow;
    
    public SortedListApplet() {
        super();
        this.imageDirectory = "images/";
        this.algorithmFileName = SortedListApplet.ALGORITHM_FILE_NAME;
        this.explanationTitle = SortedListApplet.EXPLANATION_TITLE;
        this.explanationFileName = SortedListApplet.EXPLANATION_FILE_NAME;
        SortedListApplet.lastWindow = this.window;
    }
    
    public Copyable getInitialData() {
        return new IntArray(SortedListAnimationWindow.DEFAULT_DATA);
    }
    
    protected void otherInitialisation() {
        ((SortedList)this.algorithm).initialiseMethods(this.getCodeBase().toString(), (SplitAlgorithmWindow)this.window);
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
        return new SortedListThread(copyable, (SplitAlgorithmWindow)algorithmWindow);
    }
    
    public AlgorithmCanvas getAlgorithmCanvas() {
        return new SortedListCanvas();
    }
    
    public AlgorithmWindow getAlgorithmWindow() {
        return SortedListApplet.lastWindow;
    }
    
    public AnimationWindow getAnimationWindow(final AlgorithmCanvas algorithmCanvas, final AlgorithmThread algorithmThread, final Applet applet) {
        return new SortedListAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }
    
    protected AlgorithmWindow getAlgorithmWindow(final String string, final CodeCanvas codeCanvas) {
        return new SplitAlgorithmWindow(string, codeCanvas);
    }
    
    static {
        ALGORITHM_FILE_NAME = Messages.getString("SortedListApplet.1");
        EXPLANATION_TITLE = Messages.getString("SortedListApplet.2");
        EXPLANATION_FILE_NAME = Messages.getString("SortedListApplet.3");
        SortedListApplet.lastWindow = null;
    }
}
