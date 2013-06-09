import localization.*;
import java.applet.*;
import com.cim.AIA.*;

public class InsertionSortApplet extends AlgorithmApplet
{
    private static final long serialVersionUID = 8666293375487043708L;
    public static final String IMAGE_DIRECTORY = "images/";
    protected static final String ALGORITHM_FILE_NAME;
    protected static final String EXPLANATION_TITLE;
    protected static final String EXPLANATION_FILE_NAME;
    
    public InsertionSortApplet() {
        super();
        this.imageDirectory = "images/";
        this.algorithmFileName = InsertionSortApplet.ALGORITHM_FILE_NAME;
        this.explanationTitle = InsertionSortApplet.EXPLANATION_TITLE;
        this.explanationFileName = InsertionSortApplet.EXPLANATION_FILE_NAME;
    }
    
    public AlgorithmCanvas getAlgorithmCanvas() {
        return new InsertionSortCanvas();
    }
    
    public AlgorithmThread getAlgorithmThread(final Copyable copyable, final AlgorithmWindow algorithmWindow) {
        return new InsertionSortThread(copyable, algorithmWindow);
    }
    
    public AnimationWindow getAnimationWindow(final AlgorithmCanvas algorithmCanvas, final AlgorithmThread algorithmThread, final Applet applet) {
        return new InsertionSortAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }
    
    public Copyable getInitialData() {
        return new IntArray(InsertionSortAnimationWindow.DEFAULT_DATA);
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
        ALGORITHM_FILE_NAME = Messages.getString("InsertionSortApplet.0");
        EXPLANATION_TITLE = Messages.getString("InsertionSortApplet.2");
        EXPLANATION_FILE_NAME = Messages.getString("InsertionSortApplet.1");
    }
}
