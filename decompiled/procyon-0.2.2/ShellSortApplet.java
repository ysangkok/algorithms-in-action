import localization.*;
import java.applet.*;
import com.cim.AIA.*;

public class ShellSortApplet extends AlgorithmApplet
{
    private static final long serialVersionUID = -9037452906449427848L;
    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String HSubfileComparisonFileName;
    protected static final String SinglePassComparisonFileName;
    protected static final String EXPLANATION_TITLE;
    protected static final String EXPLANATION_FILE_NAME;
    
    public ShellSortApplet() {
        super();
        this.imageDirectory = "images/";
        this.algorithmFileName = ShellSortApplet.HSubfileComparisonFileName;
        this.explanationTitle = ShellSortApplet.EXPLANATION_TITLE;
        this.explanationFileName = ShellSortApplet.EXPLANATION_FILE_NAME;
    }
    
    public AlgorithmCanvas getAlgorithmCanvas() {
        return new ShellSortCanvas();
    }
    
    public AlgorithmThread getAlgorithmThread(final Copyable copyable, final AlgorithmWindow algorithmWindow) {
        return new ShellSortThread(copyable, algorithmWindow);
    }
    
    public AnimationWindow getAnimationWindow(final AlgorithmCanvas algorithmCanvas, final AlgorithmThread algorithmThread, final Applet applet) {
        return new ShellSortAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }
    
    public Copyable getInitialData() {
        return new IntArray(ShellSortAnimationWindow.DEFAULT_DATA);
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
        HSubfileComparisonFileName = Messages.getString("ShellSortApplet.3");
        SinglePassComparisonFileName = Messages.getString("ShellSortApplet.2");
        EXPLANATION_TITLE = Messages.getString("ShellSortApplet.1");
        EXPLANATION_FILE_NAME = Messages.getString("ShellSortApplet.4");
    }
}
