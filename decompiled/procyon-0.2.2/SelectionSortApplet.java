import localization.*;
import java.applet.*;
import com.cim.AIA.*;

public class SelectionSortApplet extends AlgorithmApplet
{
    private static final long serialVersionUID = -6057409737717709991L;
    public static final String IMAGE_DIRECTORY = "images/";
    protected static final String ALGORITHM_FILE_NAME;
    protected static final String EXPLANATION_TITLE;
    protected static final String EXPLANATION_FILE_NAME;
    
    public SelectionSortApplet() {
        super();
        this.imageDirectory = "images/";
        this.algorithmFileName = SelectionSortApplet.ALGORITHM_FILE_NAME;
        this.explanationTitle = SelectionSortApplet.EXPLANATION_TITLE;
        this.explanationFileName = SelectionSortApplet.EXPLANATION_FILE_NAME;
    }
    
    public AlgorithmCanvas getAlgorithmCanvas() {
        return new SelectionSortCanvas();
    }
    
    public AlgorithmThread getAlgorithmThread(final Copyable copyable, final AlgorithmWindow algorithmWindow) {
        return new SelectionSortThread(copyable, algorithmWindow);
    }
    
    public AnimationWindow getAnimationWindow(final AlgorithmCanvas algorithmCanvas, final AlgorithmThread algorithmThread, final Applet applet) {
        return new SelectionSortAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }
    
    public Copyable getInitialData() {
        return new IntArray(SelectionSortAnimationWindow.DEFAULT_DATA);
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
        ALGORITHM_FILE_NAME = Messages.getString("SelectionSortApplet.1");
        EXPLANATION_TITLE = Messages.getString("SelectionSortApplet.0");
        EXPLANATION_FILE_NAME = Messages.getString("SelectionSortApplet.2");
    }
}
